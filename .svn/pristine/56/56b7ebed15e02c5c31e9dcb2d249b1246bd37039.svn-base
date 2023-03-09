package com.jymj.zhglxt.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity;
import com.jymj.zhglxt.widget.zdybj.ZdyEditLayout;
import com.jymj.zhglxt.widget.zdybj.ZdyTextLayout;
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionDelayEntity;
import com.jymj.zhglxt.zjd.bean.yzt.layerListBean;
import com.setsuna.common.baseapp.AppCache;

import java.util.List;

/**
 * 接诉即办
 */
public class PopuJsjbUtils {
    public interface OnTdLinear{
        void onClick(String sysXzqEntities);
    }
    public interface OnSqysLinear{
        void onClick(OpinionDelayEntity sysXzqEntities);
    }
    public void initPopuSqys(Activity activity1, View view, OnSqysLinear onSqysLinear) {
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_sqys, view);//pop_point
        View contentView = mPointPopu.getContentView();
        ImageView iv_pop_sqys_error = contentView.findViewById(R.id.iv_pop_sqys_error);
        TextView tv_pop_sqys_sure = contentView.findViewById(R.id.tv_pop_sqys_sure);
        ZdyEditLayout zel_pop_sqys_ajh = contentView.findViewById(R.id.zel_pop_sqys_ajh);
        ZdyTextLayout zel_pop_sqys_czr = contentView.findViewById(R.id.zel_pop_sqys_czr);
        ZdyEditLayout zel_pop_sqys_yqts = contentView.findViewById(R.id.zel_pop_sqys_yqts);
        ZdyEditLayout zel_pop_qygl_yqyy = contentView.findViewById(R.id.zel_pop_qygl_yqyy);
        zel_pop_sqys_czr.setText(AppCache.getInstance().getName());

        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        iv_pop_sqys_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        tv_pop_sqys_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpinionDelayEntity opinionDelayEntity = new OpinionDelayEntity();
                opinionDelayEntity.setCaseNumber(zel_pop_sqys_ajh.getValue());
                opinionDelayEntity.setUserId(AppCache.getInstance().getUserId());
                if (!zel_pop_sqys_yqts.getValue().equals("")){
                    opinionDelayEntity.setDelay(Integer.parseInt(zel_pop_sqys_yqts.getValue()));
                }
                opinionDelayEntity.setCause(zel_pop_qygl_yqyy.getValue());
                opinionDelayEntity.setThrough(0);

                onSqysLinear.onClick(opinionDelayEntity);
                mPointPopu.dismiss();
            }
        });
    }
    public void initPopuDkwj(Activity activity1, View view, int requestCode) {//选择文件类型
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_dawj, view);//pop_point
        View contentView = mPointPopu.getContentView();
        ImageView iv_pop_dkwj_error = contentView.findViewById(R.id.iv_pop_dkwj_error);
        LinearLayout ll_pop_dawj_scwd = contentView.findViewById(R.id.ll_pop_dawj_scwd);
        LinearLayout ll_pop_dawj_scyp = contentView.findViewById(R.id.ll_pop_dawj_scyp);
        TextView tv_pop_dawj_sure = contentView.findViewById(R.id.tv_pop_dawj_sure);
        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        iv_pop_dkwj_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        ll_pop_dawj_scwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWdFileManager(activity1,requestCode);
                mPointPopu.dismiss();
            }
        });
        ll_pop_dawj_scyp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileManager(activity1,requestCode);
                mPointPopu.dismiss();
            }
        });
        tv_pop_dawj_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });

    }
    // 打开文件管理器选择文件  打开音频文件
    private void openFileManager(Activity activity1,int requestCode) {
        // 打开文件管理器选择文件
        Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType(“image/*”);//选择图片
        intent.setType("audio/*"); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
//        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity1.startActivityForResult(intent, requestCode);
    }
    final String DOC = "application/msword";
    final String XLS = "application/vnd.ms-excel";
    final String PPT = "application/vnd.ms-powerpoint";
    final String DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    final String XLS1 = "application/x-excel";
    final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    final String PPTX = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
    final String PDF = "application/pdf";
    // 打开文件管理器选择文件  打开文档
    //https://www.jianshu.com/p/dc560ff4280d
    private void openWdFileManager(Activity activity1,int requestCode) {
        //application/pdf*;application/msword
        // 打开文件管理器选择文件
        /*Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf*;application/msword"); //选择文档
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity1.startActivityForResult(intent, requestCode);*/
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        String[] mimeTypes = {DOC, DOCX, PDF, PPT, PPTX, XLS, XLS1, XLSX};
        intent.setType("application/*");

        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        activity1.startActivityForResult(intent, requestCode);
    }

    public void initPopuTd(Activity activity1, View view, String title, OnTdLinear onSqysLinear) {
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_td, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_pop_td_tdsq = contentView.findViewById(R.id.tv_pop_td_tdsq);
        ImageView iv_pop_td_error = contentView.findViewById(R.id.iv_pop_td_error);
        TextView tv_pop_td_tdyy = contentView.findViewById(R.id.tv_pop_td_tdyy);
        EditText et_pop_td_tdyy = contentView.findViewById(R.id.et_pop_td_tdyy);
        TextView tv_pop_td_sure = contentView.findViewById(R.id.tv_pop_td_sure);

        tv_pop_td_tdsq.setText(title);
        if (title.equals("退单申请")){
            tv_pop_td_tdyy.setText("退单原因:");
        } else if (title.equals("退单驳回")){
            tv_pop_td_tdyy.setText("驳回原因:");
        } else if (title.equals("延时申请处理")){
            tv_pop_td_tdyy.setText("拒绝原因:");
        } else if (title.equals("归档驳回")){
            tv_pop_td_tdyy.setText("驳回原因:");
        }
        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        iv_pop_td_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        tv_pop_td_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSqysLinear.onClick(et_pop_td_tdyy.getText().toString());
                mPointPopu.dismiss();
            }
        });
    }

}