package com.jovision.uikit.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jovision.uikit.R;

public class CircleProgressDialog extends Dialog {

    private Context context = null;
    private static CircleProgressDialog mDialog = null;

    private CircleProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    private CircleProgressDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;

    }

    /***
     * 创建对话框对象
     *
     * @param context
     * @return
     */
    public static CircleProgressDialog createDialog(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mDialog = new CircleProgressDialog(context, R.style.CustomDialog);
        View layout = inflater.inflate(R.layout.dialog_circle_progress, null);
        mDialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return mDialog;
    }

    /**
     * 更新下载进度
     *
     * @param progress
     */
    public void updateProgress(int progress){
        CircleProgressBar circleProgressBar = (CircleProgressBar) mDialog.findViewById(R.id.progress);
        circleProgressBar.setProgress(progress);
    }

}