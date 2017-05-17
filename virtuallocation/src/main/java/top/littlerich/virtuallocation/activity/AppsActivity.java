package top.littlerich.virtuallocation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import top.littlerich.virtuallocation.R;
import top.littlerich.virtuallocation.adapter.AppAdapter;
import top.littlerich.virtuallocation.model.MyAppInfo;
import top.littlerich.virtuallocation.util.ApkTool;
import top.littlerich.virtuallocation.view.TopBanner;

public class AppsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    AppAdapter mAppAdapter;
    public Handler mHandler = new Handler();
    private ProgressBar mProgressBar;
    private TopBanner mTopbanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        initView();
        bindListener();

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        mAppAdapter = new AppAdapter(AppsActivity.this);
        mRecyclerView.setAdapter(mAppAdapter);
        initAppList(false);
    }

    private void bindListener() {
        mTopbanner.setTopBannerListener(new TopBanner.OnTopBannerListener() {
            @Override
            public void leftClick(View v) {
                finish();
            }

            @Override
            public void rightClick(View v) {
                if (mProgressBar.isShown()) {
                    Toast.makeText(AppsActivity.this, "数据未加载完成,无法过滤！", Toast.LENGTH_SHORT).show();
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    initAppList(true);
                }
            }
        });
    }

    private void initView() {
        mTopbanner = (TopBanner) findViewById(R.id.topbanner);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progressbar);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void initAppList(final boolean isFilter) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //扫描得到APP列表
                final List<MyAppInfo> appInfos = ApkTool.scanLocalInstallAppList(AppsActivity.this.getPackageManager(), isFilter);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAppAdapter.setData(appInfos);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                });
            }
        }.start();
    }

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, AppsActivity.class);
        context.startActivity(intent);
    }

}
