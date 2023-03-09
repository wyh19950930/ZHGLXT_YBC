package com.jymj.zhglxt.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity;
import com.jymj.zhglxt.zjd.bean.SxsxBean;
import com.setsuna.common.commonutils.ScreenUtils;

import java.util.List;

public class CbbsjsxXzqPop {
    public static PopupWindow mPopupWindow = null;
    public static void showPopupWindow(View view,Activity activity, List<SysXzqEntity> xzqqList,OnCbbsjsxXzqLinear onCbbsjsxXzqLinear) {
//加载布局
        View inflate = LayoutInflater.from(activity).inflate(R.layout.pop_cbbsjsx_xzq, null);
//更改背景颜色透明
        setBackgroundAlpha(activity, 1f);
        PopupWindow mPopupWindow = new PopupWindow(inflate);

//必须设置宽和高
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕高度（像素）
        int sss = (int)(width/3)-30;
        mPopupWindow.setWidth(sss);
        mPopupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
//点击其他地方隐藏,false为无反应
        mPopupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//对他进行偏移
            mPopupWindow.showAsDropDown(view, 0, 0, Gravity.BOTTOM);
        }
        RecyclerView rlv_cbbsjsx_xzqlist = inflate.findViewById(R.id.rlv_cbbsjsx_xzqlist);
        rlv_cbbsjsx_xzqlist.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        rlv_cbbsjsx_xzqlist.setAdapter(new BaseQuickAdapter<SysXzqEntity, BaseViewHolder>(R.layout.item_pop_xzqmc, xzqqList) {
            @Override
            protected void convert(BaseViewHolder helper, SysXzqEntity item) {
                TextView tv_item_pop_xzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tv_item_pop_xzqmc.setText(item.getName());
                /*if (item.isCheck()){//判断是否有选中
                    tv_item_pop_xzqmc.setTextColor(Color.GRAY);
                }else {*/
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onCbbsjsxXzqLinear.onClick(helper.getAdapterPosition());
                            mPopupWindow.dismiss();
                        }
                    });
//                }
            }
        });
        //内容显示操作
        //对popupWindow进行显示
        mPopupWindow.update();
    }
    public static void showPopupWindowSxmc(View view, Activity activity, List<SxsxBean> xzqqList, OnCbbsjsxXzqLinear onCbbsjsxXzqLinear) {
//加载布局
        View inflate = LayoutInflater.from(activity).inflate(R.layout.pop_cbbsjsx_xzq, null);
//更改背景颜色透明
        setBackgroundAlpha(activity, 1f);
        PopupWindow mPopupWindow = new PopupWindow(inflate);

//必须设置宽和高
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕高度（像素）
        int sss = (int)(width/2)-30;
        mPopupWindow.setWidth(sss);
        mPopupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
//点击其他地方隐藏,false为无反应
        mPopupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//对他进行偏移
            mPopupWindow.showAsDropDown(view, 0, 0, Gravity.BOTTOM);
        }
        RecyclerView rlv_cbbsjsx_xzqlist = inflate.findViewById(R.id.rlv_cbbsjsx_xzqlist);
        rlv_cbbsjsx_xzqlist.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        rlv_cbbsjsx_xzqlist.setAdapter(new BaseQuickAdapter<SxsxBean, BaseViewHolder>(R.layout.item_pop_xzqmc, xzqqList) {
            @Override
            protected void convert(BaseViewHolder helper, SxsxBean item) {
                TextView tv_item_pop_xzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tv_item_pop_xzqmc.setText(item.getSxName());
                /*if (item.isCheck()){//判断是否有选中
                    tv_item_pop_xzqmc.setTextColor(Color.GRAY);
                }else {*/
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onCbbsjsxXzqLinear.onClick(helper.getAdapterPosition());
                            mPopupWindow.dismiss();
                        }
                    });
//                }
            }
        });
        //内容显示操作
        //对popupWindow进行显示
        mPopupWindow.update();
    }
    public static void showPopupWindowSxbj(View view,Activity activity, List<SxsxBean> xzqqList,OnCbbsjsxXzqLinear onCbbsjsxXzqLinear) {
//加载布局
        View inflate = LayoutInflater.from(activity).inflate(R.layout.pop_cbbsjsx_xzq, null);
//更改背景颜色透明
        setBackgroundAlpha(activity, 1f);
        PopupWindow mPopupWindow = new PopupWindow(inflate);

//必须设置宽和高
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕高度（像素）
        int sss = (int)(width/5)-30;
        mPopupWindow.setWidth(sss);
        mPopupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
//点击其他地方隐藏,false为无反应
        mPopupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//对他进行偏移
            mPopupWindow.showAsDropDown(view, 0, 0, Gravity.BOTTOM);
        }
        RecyclerView rlv_cbbsjsx_xzqlist = inflate.findViewById(R.id.rlv_cbbsjsx_xzqlist);
        rlv_cbbsjsx_xzqlist.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        rlv_cbbsjsx_xzqlist.setAdapter(new BaseQuickAdapter<SxsxBean, BaseViewHolder>(R.layout.item_pop_xzqmc, xzqqList) {
            @Override
            protected void convert(BaseViewHolder helper, SxsxBean item) {
                TextView tv_item_pop_xzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tv_item_pop_xzqmc.setText(item.getSxTj());
                /*if (item.isCheck()){//判断是否有选中
                    tv_item_pop_xzqmc.setTextColor(Color.GRAY);
                }else {*/
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onCbbsjsxXzqLinear.onClick(helper.getAdapterPosition());
                            mPopupWindow.dismiss();
                        }
                    });
//                }
            }
        });
        //内容显示操作
        //对popupWindow进行显示
        mPopupWindow.update();
    }
    public static void showPopupWindowCun(EditText view, Activity activity, List<SysXzqEntity> xzqqList, OnCbbsjsxXzqLinear onCbbsjsxXzqLinear) {
//加载布局
        View inflate = LayoutInflater.from(activity).inflate(R.layout.pop_cbbsjsx_xzq, null);
//更改背景颜色透明
        setBackgroundAlpha(activity, 1f);

        if (mPopupWindow!=null&&!mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }
        mPopupWindow = new PopupWindow(inflate);
//必须设置宽和高
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕高度（像素）
        int sss = (int)ScreenUtils.dpToPx(activity, 20f);
        mPopupWindow.setWidth(width-sss);//ScreenUtils.dpToPx(activity, 200f).toInt()
        mPopupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
//点击其他地方隐藏,false 为无反应 true 页面editview自动拾取焦点
        mPopupWindow.setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//对他进行偏移
            mPopupWindow.showAsDropDown(view, 0, 0, Gravity.BOTTOM);
        }
        RecyclerView rlv_cbbsjsx_xzqlist = inflate.findViewById(R.id.rlv_cbbsjsx_xzqlist);
        rlv_cbbsjsx_xzqlist.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        rlv_cbbsjsx_xzqlist.setAdapter(new BaseQuickAdapter<SysXzqEntity, BaseViewHolder>(R.layout.item_pop_xzqmc, xzqqList) {
            @Override
            protected void convert(BaseViewHolder helper, SysXzqEntity item) {
                TextView tv_item_pop_xzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tv_item_pop_xzqmc.setText(item.getName());
                /*if (item.isCheck()){//判断是否有选中
                    tv_item_pop_xzqmc.setTextColor(Color.GRAY);
                }else {*/
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onCbbsjsxXzqLinear.onClick(helper.getAdapterPosition());
                            mPopupWindow.dismiss();
                        }
                    });
//                }
            }
        });
        //内容显示操作
        //对popupWindow进行显示
        mPopupWindow.update();
        view.setFocusable(true);
    }




//设置屏幕背景透明效果

    private static void setBackgroundAlpha(Activity activity, float alpha) {

        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();

        lp.alpha = alpha;

        activity.getWindow().setAttributes(lp);

    }

    public interface OnCbbsjsxXzqLinear{
        void onClick(int positiobn);
    }

}
