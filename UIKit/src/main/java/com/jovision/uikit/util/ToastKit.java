package com.jovision.uikit.util;

import android.content.Context;

/**
 * @ProjectName: UIKit
 * @Package: com.jovision.uikit.util
 * @ClassName: ToastKit
 * @Description: java类作用描述
 * @CreateDate: 2024/7/31 15:59
 * @Version: 1.0
 */
public class ToastKit {

//    /**
//     * 对toast的简易封装。线程安全，可以在非UI线程调用。
//     */
//    public static void showToastSafe(Context context, final int resId) {
//        showToastSafe(getString(resId));
//    }
//
//    /**
//     * 对toast的简易封装。线程安全，可以在非UI线程调用。
//     */
//    public static void showToastSafe(final String str) {
//        if (isRunInMainThread()) {
//            showToast(str);
//        } else {
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    showToast(str);
//                }
//            });
//        }
//    }
//
//
//    // 判断当前的线程是不是在主线程
//    private static boolean isRunInMainThread() {
//        return android.os.Process.myTid() == getMainThreadId();
//    }
//
//    public static void runInMainThread(Runnable runnable) {
//        if (isRunInMainThread()) {
//            runnable.run();
//        } else {
//            post(runnable);
//        }
//    }
//
//    /**
//     * 获取文字
//     */
//    public static String getString(int resId) {
//        if (App.mForegroundActivity == null) {
//            return getContext().getResources().getString(resId);
//        } else {
//            return App.mForegroundActivity.getResources().getString(resId);
//        }
//    }
}
