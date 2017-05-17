package top.littlerich.virtuallocation.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import top.littlerich.virtuallocation.R;


/**
 * Created by xuqingfu on 2017/4/15.
 */
public abstract class BaseDialog extends Dialog {
    protected View mView;
    protected Context mContext;

    public BaseDialog(Context context) {
        this(context, R.style.clashDialog);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        mView = mLayoutInflater.inflate(getContentViewId(), null);
        this.setContentView(mView);
        InitView();
        IniListener();
        InitData();
    }

    /**
     * 初始化view变量,用于显示
     */
    protected abstract int getContentViewId();

    /**
     * 初始化所有控件
     */
    protected abstract void InitView();

    /**
     * 设置事件
     */
    protected abstract void IniListener();

    /**
     * 初始化所有数据
     */
    protected abstract void InitData();

}
