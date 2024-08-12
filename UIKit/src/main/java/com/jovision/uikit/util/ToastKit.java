package com.jovision.uikit.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.jovision.uikit.widget.ToastView;

/**
 * @ProjectName: UIKit
 * @Package: com.jovision.uikit.util
 * @ClassName: ToastKit
 * @Description: java类作用描述
 * @CreateDate: 2024/7/31 15:59
 * @Version: 1.0
 */
public class ToastKit {

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(Context context, final int resId) {
        showToastSafe(context, getString(context, resId));
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(Context context, final String str) {
        if (isRunInMainThread()) {
            showToast(context,str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(context,str);
                }
            });
        }
    }

    public static void showToast(Context context, String str) {
        if (context != null) {
            ToastView toastView = new ToastView();
            toastView.showCenter(str, context);
        }
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showBottomToastSafe(Context context, final String str) {
        if (isRunInMainThread()) {
            showBottomToast(context,str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showBottomToast(context,str);
                }
            });
        }
    }

    public static void showBottomToast(Context context, String str) {
        if (context != null) {
            ToastView toastView = new ToastView();
            toastView.show(str, context);
        }
    }


    // 判断当前的线程是不是在主线程
    private static boolean isRunInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 获取文字
     */
    public static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    /**
     * 在主线程执行runnable
     */
    private static boolean post(Runnable runnable) {
        return new Handler().post(runnable);
    }
}
