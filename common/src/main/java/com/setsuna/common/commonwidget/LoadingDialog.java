package com.setsuna.common.commonwidget;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.setsuna.common.R;


/**
 * description:弹窗浮动加载进度条
 * Created by xsf
 * on 2016.07.17:22
 */
public class LoadingDialog {
    /** 加载数据对话框 */
    private static Dialog mLoadingDialog;
    private static Activity mContext;
    /**
     * 显示加载对话框
     * @param context 上下文
     * @param msg 对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public static Dialog showDialogForLoading(Activity context, String msg, boolean cancelable) {
        if (mLoadingDialog==null&&mContext!=context){
            mContext = context;
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);
            TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
            loadingText.setText(msg);
            mLoadingDialog = new Dialog(mContext, R.style.CustomProgressDialog);
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        mLoadingDialog.show();
        return  mLoadingDialog;
    }

    public static Dialog showDialogForLoading(Activity context) {
        if (context!=null){
            if (mLoadingDialog!=null){
                if (mLoadingDialog.isShowing()&&!context.isFinishing()){

                }else {
                    View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
                    TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
                    loadingText.setText("加载中...");
                    mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
                    mLoadingDialog.setCancelable(true);
                    mLoadingDialog.setCanceledOnTouchOutside(false);
                    mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    if (!context.isFinishing())
                    mLoadingDialog.show();
                }
            }else {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
                TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
                loadingText.setText("加载中...");
                mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
                mLoadingDialog.setCancelable(true);
                mLoadingDialog.setCanceledOnTouchOutside(false);
                mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                if (!context.isFinishing())
                mLoadingDialog.show();
            }
            /*if (mLoadingDialog!=null)
            if (mLoadingDialog.isShowing()&&!context.isFinishing()){
                mLoadingDialog.cancel();
            }
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
                TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
                loadingText.setText("加载中...");
                mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
                mLoadingDialog.setCancelable(true);
                mLoadingDialog.setCanceledOnTouchOutside(false);
                mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            mLoadingDialog.show();*/
            /*if (!context.isFinishing()){
                if (mLoadingDialog!=null){
                    if (!mLoadingDialog.isShowing()){

                        mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
                        mLoadingDialog.setCancelable(true);
                        mLoadingDialog.setCanceledOnTouchOutside(false);
//                        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        if (!context.isFinishing())
                        mLoadingDialog.show();
                    }
                mLoadingDialog.cancel();
                }
                if (mLoadingDialog==null&&mContext!=context){
                    mContext = context;
                    View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);
                    TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
                    loadingText.setText("加载中...");
                    mLoadingDialog = new Dialog(mContext, R.style.CustomProgressDialog);
                    mLoadingDialog.setCancelable(true);
                    mLoadingDialog.setCanceledOnTouchOutside(false);
                    mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                }
                if (!mContext.isFinishing())
                    mLoadingDialog.show();
            }*/
        }
        return  mLoadingDialog;
    }

    /**
     * 关闭加载对话框
     */
    public static void cancelDialogForLoading() {
        if(mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()){
                try {
                    mLoadingDialog.cancel();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static boolean isShowing(){
        if(mLoadingDialog != null) {
            return mLoadingDialog.isShowing();
        }else {
            return false;
        }
    }
}
