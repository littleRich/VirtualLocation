package top.littlerich.virtuallocation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import top.littlerich.virtuallocation.R;


/**
 * 自定义Actionbar
 */
public class TopBanner extends RelativeLayout {
    //    private static int LEFT_BTN_ID = 1;
    //    private static int TITLE_ID = 2;
    //    private static int RIGHT_BTN_ID = 3;

    private ImageView leftButton;
    private ImageView rightButton;
    private TextView title;
    private LinearLayout leftLayout;
    private LinearLayout rightLayout;

    private OnTopBannerListener listener;

    @SuppressWarnings("deprecation")
    public TopBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        String leftButtonText;
        float leftButtonTextSize;
        Drawable leftButtonBackground;
        int leftButtonTextColor;
        boolean leftButtonVisiable;
        String rightButtonText;
        float rightButtonTextSize;
        Drawable rightButtonBackground;
        int rightButtonTextColor;
        boolean rightButtonVisiable;
        String titleText;
        float titleTextSize;
        int titleTextColor;
        float rightButtonHeight;
        float rightButtonWidth;
        float leftButtonHeight;
        float leftButtonWidth;
        LayoutParams leftButtonParams;
        LayoutParams rightButtonParams;
        LayoutParams titleButtonParams;

        LayoutParams leftParams;
        LayoutParams rightParams;
        // 获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.topBanner);

        leftButtonText = typedArray.getString(R.styleable.topBanner_leftButtonText);
        leftButtonTextSize = typedArray.getDimension(R.styleable.topBanner_leftButtonTextSize, 10);
        leftButtonBackground = typedArray.getDrawable(R.styleable.topBanner_leftButtonBackground);
        leftButtonTextColor = typedArray.getColor(R.styleable.topBanner_leftButtonTextColor, Color.BLACK);
        leftButtonVisiable = typedArray.getBoolean(R.styleable.topBanner_leftButtonVisiable, true);
        rightButtonText = typedArray.getString(R.styleable.topBanner_rightButtonText);
        rightButtonTextSize = typedArray.getDimension(R.styleable.topBanner_rightButtonTextSize, 10);
        rightButtonBackground = typedArray.getDrawable(R.styleable.topBanner_rightButtonBackground);
        rightButtonTextColor = typedArray.getColor(R.styleable.topBanner_rightButtonTextColor, Color.BLACK);
        rightButtonVisiable = typedArray.getBoolean(R.styleable.topBanner_rightButtonVisiable, true);

        titleText = typedArray.getString(R.styleable.topBanner_titleText);
        titleTextSize = typedArray.getDimension(R.styleable.topBanner_titleTextSize, 20);
        titleTextColor = typedArray.getColor(R.styleable.topBanner_titleTextColors, Color.WHITE);

        rightButtonHeight = typedArray.getDimension(R.styleable.topBanner_rightButtonHeight, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightButtonWidth = typedArray.getDimension(R.styleable.topBanner_rightButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftButtonHeight = typedArray.getDimension(R.styleable.topBanner_leftButtonHeight, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftButtonWidth = typedArray.getDimension(R.styleable.topBanner_leftButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 属性重用
        typedArray.recycle();

        leftButton = new ImageView(context);
        rightButton = new ImageView(context);
        title = new TextView(context);

        leftLayout = new LinearLayout(context);
        rightLayout = new LinearLayout(context);

        //        leftLayout.setPadding(30, 30, 30, 30);
        //        rightLayout.setPadding(30, 30, 30, 30);

        leftLayout.setBackgroundColor(0x2723bf);
        rightLayout.setBackgroundColor(0x2723bf);

        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);

        leftButtonParams = new LayoutParams((int) leftButtonWidth, (int) leftButtonHeight);
        rightButtonParams = new LayoutParams((int) rightButtonWidth, (int) rightButtonHeight);
        titleButtonParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);// 上下居中
        // lxh
        leftParams.setMargins(40, 0, 0, 0);// 左上右下
       // leftButton.setPadding(40, 10, 10, 10);
        leftButton.setPaddingRelative(40, 10, 10, 10);

        // leftButtonParams.addRule(RelativeLayout.CENTER_VERTICAL,
        // RelativeLayout.TRUE);

        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rightParams.setMargins(0, 0, 40, 0);// 左上右下
        rightButton.setPaddingRelative(10, 10, 40, 10);
        // rightButtonParams.addRule(RelativeLayout.CENTER_VERTICAL,
        // RelativeLayout.TRUE);

        titleButtonParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        // titleButtonParams.addRule(RelativeLayout.LEFT_OF, RIGHT_BTN_ID);
        // titleButtonParams.addRule(RelativeLayout.RIGHT_OF, LEFT_BTN_ID);

        leftLayout.addView(leftButton, leftButtonParams);
        rightLayout.addView(rightButton, rightButtonParams);

        addView(leftLayout, leftParams);
        addView(rightLayout, rightParams);
        addView(title, titleButtonParams);

        // 设置对应的属性值
        //        leftButton.setGravity(Gravity.CENTER);
        //        leftButton.setText(leftButtonText);
        //        leftButton.setTextColor(leftButtonTextColor);
        //        leftButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, leftButtonTextSize);
        leftButton.setBackgroundDrawable(leftButtonBackground);
        setLeftButtonVisibility(leftButtonVisiable);

        //        rightButton.setGravity(Gravity.CENTER);
        //        rightButton.setText(rightButtonText);
        //        rightButton.setTextColor(rightButtonTextColor);
        //        rightButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, rightButtonTextSize);
        rightButton.setBackgroundDrawable(rightButtonBackground);
        setRightButtonVisibility(rightButtonVisiable);

        title.setText(titleText);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titleTextSize);
        title.setTextColor(titleTextColor);
        title.setEllipsize(TruncateAt.MARQUEE);
        title.setGravity(Gravity.CENTER);
        title.setSingleLine(true);

        leftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.leftClick(v);
                }
            }
        });

        rightLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.rightClick(v);
                }
            }
        });
    }


    public void setTopBannerListener(OnTopBannerListener listener) {
        this.listener = listener;
    }

    private void setLeftButtonVisibility(Boolean isVisibility) {
        leftButton.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
    }

    private void setRightButtonVisibility(Boolean isVisibility) {
        rightButton.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
    }

    public String getTitleText() {
        return title.getText().toString().trim();
    }

    public void setTitleText(String titleText) {
        title.setText(titleText);
    }

    public ImageView getLeftButton() {
        return leftButton;
    }

    public ImageView getRightButton() {
        return rightButton;
    }

    public LinearLayout getLeftLayout() {
        return leftLayout;
    }

    public LinearLayout getRightLayout() {
        return rightLayout;
    }

    public interface OnTopBannerListener {
        void leftClick(View v);

        void rightClick(View v);
    }

}
