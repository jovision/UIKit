package com.jovision.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;


import com.jovision.basekit.utilcode.SizeUtils;
import com.jovision.uikit.R;

import androidx.annotation.Nullable;

/**
 * Created by zhp on 2020/11/24
 */
public class RedRadioButton extends androidx.appcompat.widget.AppCompatRadioButton {

    public static final int RED_TIP_INVISIBLE = 0;
    public static final int RED_TIP_VISIBLE = 1;
    public static final int RED_TIP_GONE = 2;

    /**
     * 适用于底部菜单栏:菜单icon图片右上角红点
     */
    public static final int RED_TIP_TOP_VISIBLE = 3;
    private int tipVisibility = 0;

    /**
     * 适用于底部菜单栏: icon的尺寸，高 == 宽. 固定值 22dp
     */
    private final int drawableSize = SizeUtils.dp2px(22);
    /**
     * 适用于底部菜单栏: 红色圆点的尺寸, 直径. 固定值 6dp
     */
    private final int dotSize = SizeUtils.dp2px(6);

    private final Paint mPaint = new Paint();

    public RedRadioButton(Context context) {
        super(context);
        init(null);
    }

    public RedRadioButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RedRadioButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RedView);
            tipVisibility = array.getInt(R.styleable.RedView_redTipsVisibility, 0);
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tipVisibility == RED_TIP_VISIBLE) {
            int width = getWidth();
            mPaint.setColor(getResources().getColor(R.color.color_FF4C3C));
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(width - SizeUtils.dp2px(2), SizeUtils.dp2px(2), SizeUtils.dp2px(2), mPaint);
        } else if (tipVisibility == RED_TIP_TOP_VISIBLE) {
            // 底部菜单栏-菜单icon右上角的红点. View的高度为wrap_content
            mPaint.setColor(getResources().getColor(R.color.color_FF4C3C));
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

            // 圆点半径
            int dotRadius = dotSize / 2;
            // 坐标x: 图片右边+圆点半径
            int x = getWidth() / 2 + drawableSize / 2 + dotRadius;
            // 坐标y: 图片上边(0)+圆点半径
            int y = dotRadius;

            canvas.drawCircle(x, y, dotRadius, mPaint);
        }
    }

    public void setRedVisibility(int visibility) {
        tipVisibility = visibility;
        invalidate();
    }
}
