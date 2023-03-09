package com.jymj.zhglxt.util;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity;
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity;
import com.jymj.zhglxt.rjhj.bean.YLEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity;
import com.jymj.zhglxt.widget.CustomItemDecoration;
import com.jymj.zhglxt.widget.FastIndexView;
import com.jymj.zhglxt.widget.TextViews;
import com.jymj.zhglxt.widget.gxt.HVScrollView;
import com.jymj.zhglxt.widget.gxt.StarAirLayout;
import com.jymj.zhglxt.widget.zdybj.TextListLayout;
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity;
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity;
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity;
import com.jymj.zhglxt.zjd.bean.yzt.layerListBean;
import com.setsuna.common.commonutils.ToastUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PopuTjfxUtils {



    private static CommenPop mPointPopu;
    private static List<SysXzqEntity> cacheList;

    private static class SingletonHolder {
        private static final PopuTjfxUtils INSTANCE = new PopuTjfxUtils();
    }
    private PopuTjfxUtils (){}
    public static final PopuTjfxUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void initPopuPoint(Activity activity1, View view, List<SysXzqEntity> sysXzqEntities,String name, OnTjfxLinear onTjfxLinear) {
        final boolean[] isSure = {false};
        mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_tjfx, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_pop_tjfx = contentView.findViewById(R.id.tv_pop_tjfx);
        TextView tv_pop_tjfx_cencel = contentView.findViewById(R.id.tv_pop_tjfx_cencel);
        RecyclerView rlv_pop_tjfx = contentView.findViewById(R.id.rlv_pop_tjfx);
        FastIndexView fastIndexView = contentView.findViewById(R.id.fastIndexView);
        cacheList = new ArrayList<>();
        bindData(sysXzqEntities,name);
        /*if (cacheList == null){
            cacheList = new ArrayList<>();
            bindData(sysXzqEntities);
        }*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity1, LinearLayoutManager.VERTICAL, false);
        rlv_pop_tjfx.setLayoutManager(linearLayoutManager);
        rlv_pop_tjfx.setAdapter(new BaseQuickAdapter<SysXzqEntity,BaseViewHolder>(R.layout.item_pop_xzqmc,cacheList) {
            @Override
            protected void convert(BaseViewHolder helper, SysXzqEntity item) {
                TextView tvItemPopXzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tvItemPopXzqmc.setText(item.getName());
                tvItemPopXzqmc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < cacheList.size(); i++) {
                            cacheList.get(i).setCheck(false);
                        }
                        item.setCheck(!item.isCheck());

                        isSure[0] = true;
                        mPointPopu.dismiss();
                        notifyDataSetChanged();
                    }
                });
                if (item.isCheck()){
                    tvItemPopXzqmc.setTextColor(Color.parseColor("#12C483"));
                }else {
                    tvItemPopXzqmc.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        rlv_pop_tjfx.addItemDecoration(new CustomItemDecoration(activity1, new CustomItemDecoration.TitleDecorationCallback() {
            @Override
            public String getGroupId(int position) {
                //这个是用来比较是否是同一组数据的
                return cacheList.get(position).getSortId();
            }

            @Override
            public String getGroupName(int position) {
                SysXzqEntity cityInfoModel = cacheList.get(position);
                //拼音都是小写的
                return cityInfoModel.getSortId().toUpperCase();
            }
        }));
        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        mPointPopu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                CommenPop.backgroundAlpha(1f, activity1);
                if (isSure[0]){
                    onTjfxLinear.onClick(sysXzqEntities);
                }
            }
        });
        tv_pop_tjfx_cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSure[0] = false;
                mPointPopu.dismiss();
            }
        });
        tv_pop_tjfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        fastIndexView.setListener(new FastIndexView.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                moveToLetterPosition(letter,linearLayoutManager);
            }
        });
    }

    public void initPopuBg(Activity activity1, View view, List<String> stringList, OnBgListLinear onTjfxLinear) {
        mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_tjfx, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_pop_tjfx = contentView.findViewById(R.id.tv_pop_tjfx);
        TextView tv_pop_point_tjfx_title = contentView.findViewById(R.id.tv_pop_point_tjfx_title);
        TextView tv_pop_tjfx_cencel = contentView.findViewById(R.id.tv_pop_tjfx_cencel);
        RecyclerView rlv_pop_tjfx = contentView.findViewById(R.id.rlv_pop_tjfx);
        FastIndexView fastIndexView = contentView.findViewById(R.id.fastIndexView);
        fastIndexView.setVisibility(View.GONE);
//        tv_pop_point_tjfx_title.setText("导出列表");
        tv_pop_point_tjfx_title.setText(Html.fromHtml("<b>导出列表</b>(表名 时间.xls)"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity1, LinearLayoutManager.VERTICAL, false);
        rlv_pop_tjfx.setLayoutManager(linearLayoutManager);
        rlv_pop_tjfx.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_pop_xzqmc,stringList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                TextView tvItemPopXzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tvItemPopXzqmc.setText(item);
                tvItemPopXzqmc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onTjfxLinear.onClick(item);
                        mPointPopu.dismiss();
//                        notifyDataSetChanged();
                    }
                });

            }
        });
        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        tv_pop_tjfx_cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        tv_pop_tjfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
    }

    public void initPopuTime(Activity activity1, View view, List<String> stringList, OnBgListLinear onTjfxLinear) {
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_tjfx, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_pop_tjfx = contentView.findViewById(R.id.tv_pop_tjfx);
        TextView tv_pop_point_tjfx_title = contentView.findViewById(R.id.tv_pop_point_tjfx_title);
        TextView tv_pop_tjfx_cencel = contentView.findViewById(R.id.tv_pop_tjfx_cencel);
        RecyclerView rlv_pop_tjfx = contentView.findViewById(R.id.rlv_pop_tjfx);
        FastIndexView fastIndexView = contentView.findViewById(R.id.fastIndexView);
        fastIndexView.setVisibility(View.GONE);
//        tv_pop_point_tjfx_title.setText("导出列表");
        tv_pop_point_tjfx_title.setText(Html.fromHtml("<b>往年基础列表</b>(导入家庭户信息)"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity1, LinearLayoutManager.VERTICAL, false);
        rlv_pop_tjfx.setLayoutManager(linearLayoutManager);
        rlv_pop_tjfx.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_pop_xzqmc,stringList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                TextView tvItemPopXzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tvItemPopXzqmc.setText(item);
                tvItemPopXzqmc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onTjfxLinear.onClick(item);
                        mPointPopu.dismiss();
//                        notifyDataSetChanged();
                    }
                });

            }
        });
        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        tv_pop_tjfx_cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        tv_pop_tjfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
    }

    //滚动recyclerview
    private void moveToLetterPosition(String letter,LinearLayoutManager linearLayoutManager) {
        //这里主要是为了跳转到最顶端
        if ("#".equals(letter)) {
            letter = "*";
        }
        for (int i = 0; i < cacheList.size(); i++) {
            SysXzqEntity cityInfoModel = cacheList.get(i);
            if (cityInfoModel.getSortId().toUpperCase().equals(letter)) {
                linearLayoutManager.scrollToPositionWithOffset(i, 0);
                return;
            }
        }
    }

    /**
     * 给View绑定数据
     *
     * @param allCity 所有城市列表
     */
    private List<SysXzqEntity> bindData(List<SysXzqEntity> allCity,String name) {
        if (allCity != null) {
            for (SysXzqEntity cityModel : allCity) {
                try {
                    String pingYin = PinyinHelper.convertToPinyinString(cityModel.getName(), " ", PinyinFormat.WITHOUT_TONE);
                    cityModel.setSortId(pingYin.substring(0, 1));
                    cityModel.setSortName(pingYin);
                    if (cityModel.getName().equals(name)){
                        cityModel.setCheck(true);
                    }else {
                        cityModel.setCheck(false);
                    }

                    cacheList.add(cityModel);
//                    cacheList.add(new SysXzqEntity(cityModel.getName(), pingYin.substring(0, 1), pingYin));
                } catch (PinyinException e) {
                    e.printStackTrace();
                }
            }
            //排序
            Collections.sort(cacheList, new Comparator<SysXzqEntity>() {
                @Override
                public int compare(SysXzqEntity o1, SysXzqEntity o2) {
                    return o1.getSortName().compareTo(o2.getSortName());
                }
            });
            /*this.list.clear();
            this.list.addAll(cacheList);*/
        }
        return cacheList;
    }



    public interface OnTjfxLinear{
        void onClick(List<SysXzqEntity> sysXzqEntities);
    }
    public interface OnBgListLinear{
        void onClick(String bgName);
    }
    public void initPopuQttc(Activity activity1, View view, List<layerListBean> sysXzqEntities, OnQttcLinear onTjfxLinear) {
        mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_tjfx, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_pop_tjfx = contentView.findViewById(R.id.tv_pop_tjfx);
        RecyclerView rlv_pop_tjfx = contentView.findViewById(R.id.rlv_pop_tjfx);
        FastIndexView fastIndexView = contentView.findViewById(R.id.fastIndexView);
        fastIndexView.setVisibility(View.GONE);
        rlv_pop_tjfx.setLayoutManager(new LinearLayoutManager(activity1,LinearLayoutManager.VERTICAL,false));
        rlv_pop_tjfx.setAdapter(new BaseQuickAdapter<layerListBean,BaseViewHolder>(R.layout.item_pop_xzqmc,sysXzqEntities) {
            @Override
            protected void convert(BaseViewHolder helper, layerListBean item) {
                TextView tvItemPopXzqmc = helper.getView(R.id.tv_item_pop_xzqmc);
                tvItemPopXzqmc.setText(item.getTitle());
//                helper.setText(R.id.tv_item_pop_xzqmc,item.getName());
                tvItemPopXzqmc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setCheck(!item.isCheck());
                        notifyDataSetChanged();
                    }
                });
                if (item.isCheck()){
                    tvItemPopXzqmc.setTextColor(Color.parseColor("#12C483"));
                }else {
                    tvItemPopXzqmc.setTextColor(Color.parseColor("#333333"));
                }
            }
        });

        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        mPointPopu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                CommenPop.backgroundAlpha(1f, activity1);
                onTjfxLinear.onClick(sysXzqEntities);
            }
        });
        tv_pop_tjfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
    }
    public interface OnQttcLinear{
        void onClick(List<layerListBean> sysXzqEntities);
    }


}