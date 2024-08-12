package com.jovision.uikit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 添加触摸事件拦截事件
 * Created by ZHP on 2017/9/21.
 */
public class TouchInterceptLayout extends FrameLayout {
    private boolean hasIntercept = true;

    public TouchInterceptLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (hasIntercept) {
            return hasIntercept;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (hasIntercept) {
            return hasIntercept;
        }
        return super.onTouchEvent(event);
    }
}
