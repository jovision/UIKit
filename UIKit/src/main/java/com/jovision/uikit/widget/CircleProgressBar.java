package com.jovision.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jovision.uikit.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yinfj on 2017/3/21.
 * <p>
 * 环形进度条
 */

public class CircleProgressBar extends View {

    private final String TAG = "CircleProgressBar";

    private float mRadius;//整体半径
    private float mStrokeWidth;//显示进度宽度
    private float mTextSize;//进度值大小
    private int mColorInner;//中心颜色
    private int mColorStroke;//外环颜色
    private int mColorProgress;//进度条颜色
    private int mColorText;//进度值颜色
    private int mProgressMax;//进度值最大值
    private int mProgress;//当前进度值
    private String mProgressStr = "";

    private boolean isInited;

    private float x, y;//圆心
    private int mWidth, mHeight;//视图大小
    private int mTextHeight = 0;

    private Paint mPaintOut,//画大圆
            mPaintArc, //画扇形（表示进度）
            mPaintInner,//画小圆（遮挡中心，与大圆组圆环
            mPaintText;//画进度值

    private RectF mArcRect;

    private boolean autoEnd;

    private Context mContext;

    public CircleProgressBar(Context context) {
        super(context);
        mContext = context;
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getAttrs(attrs);

    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getAttrs(attrs);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        mRadius = ta.getDimension(R.styleable.CircleProgressBar_radius, 50);
        mStrokeWidth = ta.getDimension(R.styleable.CircleProgressBar_stroke_wide, 1);
        mTextSize = ta.getDimension(R.styleable.CircleProgressBar_text_size, 30);
        mColorInner = ta.getColor(R.styleable.CircleProgressBar_color_inner, Color.WHITE);
        mColorStroke = ta.getColor(R.styleable.CircleProgressBar_color_stroke, Color.GRAY);
        mColorProgress = ta.getColor(R.styleable.CircleProgressBar_color_progress, Color.GREEN);
        mColorText = ta.getColor(R.styleable.CircleProgressBar_text_color, Color.BLACK);
        mProgressMax = ta.getInteger(R.styleable.CircleProgressBar_progress_max, 100);
        mProgress = ta.getInteger(R.styleable.CircleProgressBar_progress, 0);
        ta.recycle();
        initView();
    }

    private void initView() {
        mPaintOut = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintOut.setColor(mColorStroke);
        mPaintOut.setStyle(Paint.Style.STROKE);
        mPaintOut.setStrokeWidth(mStrokeWidth);

        mPaintInner = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintInner.setColor(mColorInner);

        mPaintArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintArc.setColor(mColorProgress);
        mPaintArc.setStyle(Paint.Style.STROKE);
        mPaintArc.setStrokeWidth(mStrokeWidth);

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(mColorText);
        mPaintText.setTextSize(mTextSize);

        mArcRect = new RectF(0 + mStrokeWidth, 0 + mStrokeWidth, mWidth - mStrokeWidth, mHeight - mStrokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                mWidth = 100;
                break;
            case MeasureSpec.AT_MOST:
                mWidth = measureWidth / 2;
                break;
            case MeasureSpec.EXACTLY:
                mWidth = measureWidth;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                mHeight = 100;
                break;
            case MeasureSpec.AT_MOST:
                mHeight = measureHeight / 2;
                break;
            case MeasureSpec.EXACTLY:
                mHeight = measureHeight;
                break;
        }

        x = mWidth / 2;
        y = mHeight / 2;

        mRadius = mWidth > mHeight ? y : x;
        mArcRect.set(0 + mStrokeWidth, 0 + mStrokeWidth, mWidth - mStrokeWidth, mHeight - mStrokeWidth);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, mRadius - mStrokeWidth, mPaintOut);
        canvas.drawArc(mArcRect, 270, getAngle(), false, mPaintArc);
        canvas.drawText(mProgressStr, x - getStrWidth(mProgressStr) / 2, y + getStrHeight(mProgressStr) / 3, mPaintText);
    }

    private int getStrWidth(String str) {
        return (int) mPaintText.measureText(str);
    }

    private int getStrHeight(String str) {
        if (mTextHeight == 0) {
            Paint.FontMetrics fm = mPaintText.getFontMetrics();
            mTextHeight = (int) Math.ceil(fm.descent - fm.top);
        }
        return mTextHeight;
    }

    /**
     * 设置最大进度值
     */
    public void setMaxProgress(int progress) {
        if (this.mProgressMax == progress) {
            return;
        }
        this.mProgressMax = progress;
        this.invalidate();
    }

    /**
     * 获取当前设置的最大进度值
     */
    public int getMaxProgress() {
        return this.mProgressMax;
    }

    /**
     * 设置图形中间文字
     */
    public void setInnerText(String text) {
        this.mProgressStr = text;
        this.invalidate();
    }

    private float getAngle() {
        if (mProgressMax == 0) {
            return 0;
        }
        return mProgress * 360 / mProgressMax;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        if (this.mProgress == progress) {
            return;
        }
        this.mProgress = progress < 0 ? 0 : progress > mProgressMax ? mProgressMax : progress;
        this.mProgressStr = progress * 100 / mProgressMax + "%";
        this.invalidate();
    }

    /**
     * 快进到末尾
     *
     * @param duration 动画时间
     */
    public void gotoEnd(final int duration) {
        final int frame = mProgressMax - mProgress;
        if (frame == 0) {
            return;
        }
        final int time = duration / frame;

        final Timer timer = new Timer();
        try {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (null != getHandler()) {
                        getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                if (mProgress >= mProgressMax) {
                                    timer.cancel();
                                    timer.purge();
                                }
                                setProgress(++mProgress);
                            }
                        });
                    }

                }
            }, 0, time);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
