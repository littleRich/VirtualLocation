package top.littlerich.virtuallocation.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import top.littlerich.virtuallocation.R;
import top.littlerich.virtuallocation.presenter.JellyInterpolator;
import top.littlerich.virtuallocation.util.LocationUtil;
import top.littlerich.virtuallocation.view.TopBanner;

public class PreciseLocationActivity extends AppCompatActivity {

    private EditText mLongitudeValue;
    private EditText mLatitudeValue;
    private AppCompatButton mBeginLocation;
    private TopBanner mTopbanner;
    private ProgressBar mPbLocating;
    private TextView mTip;
    float width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precise_location);
        initViews();
        bindListener();
    }

    private void bindListener() {
        mBeginLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    LocationUtil.setLongitudeAndLatitude(Double.valueOf(mLongitudeValue.getText().toString()),
                            Double.valueOf(mLatitudeValue.getText().toString()));
                    width = mBeginLocation.getMeasuredWidth();
                    float height = mBeginLocation.getMeasuredHeight();
                    inputAnimator(mBeginLocation, width, height);
                } catch (NumberFormatException e) {
                    Toast.makeText(PreciseLocationActivity.this, "请输入正确的经纬度!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mTopbanner.setTopBannerListener(new TopBanner.OnTopBannerListener() {
            @Override
            public void leftClick(View v) {
               finish();
            }

            @Override
            public void rightClick(View v) {

            }
        });
        mPbLocating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTip("精确定位", View.GONE);
                mPbLocating.setVisibility(View.GONE);
                mBeginLocation.setVisibility(View.VISIBLE);
                repaintView(mBeginLocation);
            }
        });
    }

    /**
     * 使组件变回原有形状
     * @param view
     */
    private void repaintView(final View view){
        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(width, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mBeginLocation
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view,
                "scaleX", 0.5f, 1f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
    }

    private void initViews() {
        mLatitudeValue = (EditText)findViewById(R.id.et_latitude);
        mLongitudeValue = (EditText)findViewById(R.id.et_longitude);
        mBeginLocation = (AppCompatButton)findViewById(R.id.btn_precise_location);
        mTopbanner = (TopBanner) findViewById(R.id.topbanner);
        mPbLocating = (ProgressBar)findViewById(R.id.pb_locating);
        mTip = (TextView)findViewById(R.id.tv_tip);
    }


    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mBeginLocation
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                showTip("穿越完成", View.VISIBLE);
                mBeginLocation.setVisibility(View.GONE);
                mPbLocating.setVisibility(View.VISIBLE);
                progressAnimator(mPbLocating);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void showTip(String title, int isVisible) {
        mTopbanner.setTitleText(title);
        mTip.setVisibility(isVisible);
    }

    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();
    }

    public static void openActivity(Context context){
        Intent intent = new Intent(context, PreciseLocationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPbLocating.clearAnimation();
        mBeginLocation.clearAnimation();
    }
}
