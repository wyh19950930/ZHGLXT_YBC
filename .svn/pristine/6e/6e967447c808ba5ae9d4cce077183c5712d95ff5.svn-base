package com.jymj.zhglxt.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.bean.enums.IndustryEnum;
import com.jymj.zhglxt.bean.enums.IsTrueEnum;
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity;
import com.jymj.zhglxt.rjhj.bean.YLEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity;
import com.jymj.zhglxt.rjhj.bean.yl.enums.RegistraEnum;
import com.jymj.zhglxt.widget.TextViews;
import com.jymj.zhglxt.widget.gxt.HVScrollView;
import com.jymj.zhglxt.widget.gxt.StarAirLayout;
import com.jymj.zhglxt.widget.zdybj.TextListLayout;
import com.jymj.zhglxt.widget.zdybj.ZdyEditLayout;
import com.jymj.zhglxt.widget.zdybj.ZdyTextLayout;
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBusiness;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseEntity;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseGxEnum;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseInfoEntity;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseTypeEnum;
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity;
import com.jymj.zhglxt.zjd.bean.qyzt.JyztEnum;
import com.setsuna.common.commonutils.ToastUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PopJyqkUtils {
    private static CommenPop mPointPopu;
    private static Activity activity;

    public static void initPopuPoint(Activity activity1, View view,OnJyqkLinear onJyqkLinear) {
        activity = activity1;
        mPointPopu = CommenPop.getNormalPopu(activity, R.layout.pop_jyqk, view);//pop_point
        View contentView = mPointPopu.getContentView();
//        tllPopPointTdxg = contentView.findViewById(R.id.tll_pop_point_tdxg);
        ZdyEditLayout zel_pop_jyqk_lrze = contentView.findViewById(R.id.zel_pop_jyqk_lrze);//利润总额
        ZdyEditLayout zel_pop_jyqk_ss = contentView.findViewById(R.id.zel_pop_jyqk_ss);//税收
        ZdyEditLayout zel_pop_jyqk_yysr = contentView.findViewById(R.id.zel_pop_jyqk_yysr);//营业收入
        ZdyEditLayout zel_pop_jyqk_cz = contentView.findViewById(R.id.zel_pop_jyqk_cz);//产值
        ZdyTextLayout zel_pop_jyqk_ywdxkj = contentView.findViewById(R.id.zel_pop_jyqk_ywdxkj);//经营状态
        ZdyEditLayout zel_pop_jyqk_bdygsl = contentView.findViewById(R.id.zel_pop_jyqk_bdygsl);//本地员工数量
        ZdyEditLayout zel_pop_jyqk_wdygsl = contentView.findViewById(R.id.zel_pop_jyqk_wdygsl);//外地员工数量
        ZdyEditLayout zel_pop_jyqk_jyrkjzqy1 = contentView.findViewById(R.id.zel_pop_jyqk_jyrkjzqy1);//就业人口居住区域(单位宿舍)
        ZdyEditLayout zel_pop_jyqk_jyrkjzqy2 = contentView.findViewById(R.id.zel_pop_jyqk_jyrkjzqy2);//就业人口居住区域(本村)
        ZdyEditLayout zel_pop_jyqk_jyrkjzqy3 = contentView.findViewById(R.id.zel_pop_jyqk_jyrkjzqy3);//就业人口居住区域(其他)

        TextView but_pop_jyqk_sure = contentView.findViewById(R.id.but_pop_jyqk_sure);
        TextView but_pop_jyqk_close = contentView.findViewById(R.id.but_pop_jyqk_close);

        zel_pop_jyqk_ywdxkj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyqkPickerUtil.initNationPickerView(activity,"经营状态",zel_pop_jyqk_ywdxkj);
            }
        });
        but_pop_jyqk_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterpriseBusiness enterpriseBusiness = new EnterpriseBusiness();
                enterpriseBusiness.setLrze(zel_pop_jyqk_lrze.getValue().equals("")?BigDecimal.ZERO:new BigDecimal(zel_pop_jyqk_lrze.getValue()));
                enterpriseBusiness.setHj(zel_pop_jyqk_ss.getValue().equals("")?BigDecimal.ZERO:new BigDecimal(zel_pop_jyqk_ss.getValue()));
                enterpriseBusiness.setBdgrs(zel_pop_jyqk_bdygsl.getValue().equals("")?0:Integer.parseInt(zel_pop_jyqk_bdygsl.getValue()));
                enterpriseBusiness.setWdgrs(zel_pop_jyqk_wdygsl.getValue().equals("")?0:Integer.parseInt(zel_pop_jyqk_wdygsl.getValue()));
                enterpriseBusiness.setDwss(zel_pop_jyqk_jyrkjzqy1.getValue().equals("")?0:Integer.parseInt(zel_pop_jyqk_jyrkjzqy1.getValue()));
                enterpriseBusiness.setBc(zel_pop_jyqk_jyrkjzqy2.getValue().equals("")?0:Integer.parseInt(zel_pop_jyqk_jyrkjzqy2.getValue()));
                enterpriseBusiness.setQt(zel_pop_jyqk_jyrkjzqy3.getValue().equals("")?0:Integer.parseInt(zel_pop_jyqk_jyrkjzqy3.getValue()));
                enterpriseBusiness.setJyzt(zel_pop_jyqk_ywdxkj.getValue().equals("")?0: JyztEnum.getIndex(zel_pop_jyqk_ywdxkj.getValue()));
                enterpriseBusiness.setCz(zel_pop_jyqk_cz.getValue());
                enterpriseBusiness.setYysr(zel_pop_jyqk_yysr.getValue());

                onJyqkLinear.onClick(enterpriseBusiness);
                mPointPopu.dismiss();
            }
        });
        but_pop_jyqk_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
    public static void initCzdwPoint(Activity activity1, View view,EnterpriseEntity enterpriseEntity,OnCzdwLinear onCzdwLinear) {
        activity = activity1;
        mPointPopu = CommenPop.getNormalPopu(activity, R.layout.pop_czdw, view);//pop_point
        View contentView = mPointPopu.getContentView();
        ZdyEditLayout zel_pop_czdw_xzqymc = contentView.findViewById(R.id.zel_pop_czdw_xzqymc);//现状企业名称
        ZdyEditLayout zel_pop_czdw_xzcdz = contentView.findViewById(R.id.zel_pop_czdw_xzcdz);//注册地址
        ZdyEditLayout zel_pop_czdw_sjjydz = contentView.findViewById(R.id.zel_pop_czdw_sjjydz);//实际经营地址
        ZdyTextLayout zel_pop_czdw_rzrq = contentView.findViewById(R.id.zel_pop_czdw_rzrq);//入住日期
        ZdyTextLayout zel_pop_czdw_jyzzzcrq = contentView.findViewById(R.id.zel_pop_czdw_jyzzzcrq);//经营执照注册日期
        ZdyEditLayout zel_pop_czdw_swdjzzcd = contentView.findViewById(R.id.zel_pop_czdw_swdjzzcd);//税务登记证注册地
        ZdyTextLayout zel_act_czdw_qylx = contentView.findViewById(R.id.zel_act_czdw_qylx);//企业类型
        ZdyTextLayout zel_act_czdw_hylx = contentView.findViewById(R.id.zel_act_czdw_hylx);//行业类型
        ZdyEditLayout zel_pop_czdw_xzlxr = contentView.findViewById(R.id.zel_pop_czdw_xzlxr);//行政联系人
        ZdyTextLayout zel_act_czdw_sfzz = contentView.findViewById(R.id.zel_act_czdw_sfzz);//是否转租
        ZdyTextLayout zel_act_czdw_zclx = contentView.findViewById(R.id.zel_act_czdw_zclx);//注册类型
        ZdyEditLayout zel_pop_czdw_zczj = contentView.findViewById(R.id.zel_pop_czdw_zczj);//注册资金
        ZdyEditLayout zel_pop_zcdw_lxdw = contentView.findViewById(R.id.zel_pop_zcdw_lxdw);//联系电话
        ZdyTextLayout zel_act_czdw_gxqy = contentView.findViewById(R.id.zel_act_czdw_gxqy);//高新企业

        TextView but_pop_czdw_sure = contentView.findViewById(R.id.but_pop_czdw_sure);
        TextView but_pop_czdw_close = contentView.findViewById(R.id.but_pop_czdw_close);

        if (enterpriseEntity!=null){
            zel_pop_czdw_xzqymc.setText(enterpriseEntity.getQyname());
            zel_pop_czdw_xzcdz.setText(enterpriseEntity.getZcdz());
            zel_pop_czdw_sjjydz.setText(enterpriseEntity.getSjjydz());
            zel_pop_czdw_rzrq.setText(enterpriseEntity.getRzdate());
            zel_pop_czdw_jyzzzcrq.setText(enterpriseEntity.getZcdate());
            zel_pop_czdw_swdjzzcd.setText(enterpriseEntity.getSwzcdz());
            zel_act_czdw_qylx.setText(enterpriseEntity.getQylxText());
            zel_act_czdw_hylx.setText(enterpriseEntity.getHylxText());
            zel_pop_czdw_xzlxr.setText(enterpriseEntity.getXzlxr());
            if (enterpriseEntity.getCzqk()==0){
                zel_act_czdw_sfzz.setText("否");
            }else {
                zel_act_czdw_sfzz.setText("是");
            }
            zel_act_czdw_zclx.setText(enterpriseEntity.getZclxText());
            zel_pop_czdw_zczj.setText(enterpriseEntity.getZczj());
            zel_pop_zcdw_lxdw.setText(enterpriseEntity.getLxdh());
            zel_act_czdw_gxqy.setText(enterpriseEntity.getGxqyText());
        }
        zel_pop_czdw_rzrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerUtil.initTimePickerViewNyr(activity,zel_pop_czdw_rzrq.getValue(),new TimePickerUtil.OnTimePickerLister(){
                    @Override
                    public void onClick(String data) {
                        zel_pop_czdw_rzrq.setText(data);
                    }
                });
            }
        });
        zel_pop_czdw_jyzzzcrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerUtil.initTimePickerViewNyr(activity,zel_pop_czdw_jyzzzcrq.getValue(),new TimePickerUtil.OnTimePickerLister(){
                    @Override
                    public void onClick(String data) {
                        zel_pop_czdw_jyzzzcrq.setText(data);
                    }
                });
            }
        });
        zel_act_czdw_qylx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyqkPickerUtil.initNationPickerView(activity,"企业类型",zel_act_czdw_qylx);
            }
        });
        zel_act_czdw_hylx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyqkPickerUtil.initNationPickerView(activity,"行业类型",zel_act_czdw_hylx);
            }
        });
        zel_act_czdw_sfzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyqkPickerUtil.initNationPickerView(activity,"是否",zel_act_czdw_sfzz);
            }
        });
        zel_act_czdw_zclx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyqkPickerUtil.initNationPickerView(activity,"注册类型",zel_act_czdw_zclx);
            }
        });
        zel_act_czdw_gxqy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyqkPickerUtil.initNationPickerView(activity,"高新企业",zel_act_czdw_gxqy);
            }
        });
        but_pop_czdw_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
                enterpriseEntity.setQyname(zel_pop_czdw_xzqymc.getValue());
                enterpriseEntity.setZcdz(zel_pop_czdw_xzcdz.getValue());
                enterpriseEntity.setSjjydz(zel_pop_czdw_sjjydz.getValue());
                enterpriseEntity.setRzdate(zel_pop_czdw_rzrq.getValue());
                enterpriseEntity.setZcdate(zel_pop_czdw_jyzzzcrq.getValue());
                enterpriseEntity.setSwzcdz(zel_pop_czdw_swdjzzcd.getValue());
                enterpriseEntity.setQylx(EnterpriseTypeEnum.getIndex(zel_act_czdw_qylx.getValue()));
                enterpriseEntity.setHylx(IndustryEnum.getIndex(zel_act_czdw_hylx.getValue()));
                enterpriseEntity.setXzlxr(zel_pop_czdw_xzlxr.getValue());
                enterpriseEntity.setCzqk(IsTrueEnum.getIndex(zel_act_czdw_sfzz.getValue()));
                enterpriseEntity.setZclx(RegistraEnum.getIndex(zel_act_czdw_zclx.getValue()));
                enterpriseEntity.setZczj(zel_pop_czdw_zczj.getValue());
                enterpriseEntity.setLxdh(zel_pop_zcdw_lxdw.getValue());
                enterpriseEntity.setGxqy(EnterpriseGxEnum.getIndex(zel_act_czdw_gxqy.getValue()));
                onCzdwLinear.onClick(enterpriseEntity);
                mPointPopu.dismiss();
            }
        });
        but_pop_czdw_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
    public static void initYdqkPoint(Activity activity1, EnterpriseInfoEntity enterpriseInfoEntity, View view, OnYdqkLinear onJyqkLinear) {
        activity = activity1;
        mPointPopu = CommenPop.getNormalPopu(activity, R.layout.pop_ydqk, view);//pop_point
        View contentView = mPointPopu.getContentView();
        ZdyEditLayout zel_pop_ydqk_tdsyr = contentView.findViewById(R.id.zel_pop_ydqk_tdsyr);//土地使用人
        ZdyEditLayout zel_pop_ydqk_tdxz = contentView.findViewById(R.id.zel_pop_ydqk_tdxz);//土地性质
        ZdyTextLayout ztl_pop_ydqk_qsqk = contentView.findViewById(R.id.ztl_pop_ydqk_qsqk);//权属情况
        ZdyTextLayout zel_pop_ydqk_tdqdnd = contentView.findViewById(R.id.zel_pop_ydqk_tdqdnd);//土地取得年限
        ZdyTextLayout zel_pop_ydqk_tdsyqdnx = contentView.findViewById(R.id.zel_pop_ydqk_tdsyqdnx);//土地剩余使用年限
        ZdyEditLayout zel_pop_ydqk_tdjg = contentView.findViewById(R.id.zel_pop_ydqk_tdjg);//土地价格
        ZdyEditLayout zel_pop_ydqk_tdzmj = contentView.findViewById(R.id.zel_pop_ydqk_tdzmj);//土地证面积
        ZdyEditLayout zel_pop_ydqk_tdqdfs = contentView.findViewById(R.id.zel_pop_ydqk_tdqdfs);//土地取得方式

        TextView but_pop_ydqk_sure = contentView.findViewById(R.id.but_pop_ydqk_sure);
        TextView but_pop_ydqk_close = contentView.findViewById(R.id.but_pop_ydqk_close);
        zel_pop_ydqk_tdsyr.setText(enterpriseInfoEntity.getTdsyr());
        zel_pop_ydqk_tdxz.setText(enterpriseInfoEntity.getTdxz());
        ztl_pop_ydqk_qsqk.setText(enterpriseInfoEntity.getQs());
        zel_pop_ydqk_tdqdnd.setText(enterpriseInfoEntity.getTdqddate());
        zel_pop_ydqk_tdsyqdnx.setText(enterpriseInfoEntity.getTdsynx());
        zel_pop_ydqk_tdjg.setText(enterpriseInfoEntity.getTdjg());
        zel_pop_ydqk_tdzmj.setText(enterpriseInfoEntity.getTdzmj().toString());
        zel_pop_ydqk_tdqdfs.setText(enterpriseInfoEntity.getTdqdfs());
        zel_pop_ydqk_tdqdnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerUtil.initTimePickerViewNyr(activity,zel_pop_ydqk_tdqdnd.getValue(),new TimePickerUtil.OnTimePickerLister(){
                    @Override
                    public void onClick(String data) {
                        zel_pop_ydqk_tdqdnd.setText(data);
                    }
                });
            }
        });
        zel_pop_ydqk_tdsyqdnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerUtil.initTimePickerViewNyr(activity,zel_pop_ydqk_tdsyqdnx.getValue(),new TimePickerUtil.OnTimePickerLister(){
                    @Override
                    public void onClick(String data) {
                        zel_pop_ydqk_tdsyqdnx.setText(data);
                    }
                });
            }
        });
        ztl_pop_ydqk_qsqk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JyqkPickerUtil.initNationPickerView(activity,"用地权属",ztl_pop_ydqk_qsqk);
            }
        });
        but_pop_ydqk_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterpriseInfoEntity.setTdsyr(zel_pop_ydqk_tdsyr.getValue());
                enterpriseInfoEntity.setTdxz(zel_pop_ydqk_tdxz.getValue());
                enterpriseInfoEntity.setQs(ztl_pop_ydqk_qsqk.getValue());
                enterpriseInfoEntity.setTdqddate(zel_pop_ydqk_tdqdnd.getValue());
                enterpriseInfoEntity.setTdsynx(zel_pop_ydqk_tdsyqdnx.getValue());
                enterpriseInfoEntity.setTdjg(zel_pop_ydqk_tdjg.getValue());
                if(!zel_pop_ydqk_tdzmj.getValue().equals("")){
                    enterpriseInfoEntity.setTdzmj(new BigDecimal(zel_pop_ydqk_tdzmj.getValue()));
                }
                enterpriseInfoEntity.setTdqdfs(zel_pop_ydqk_tdqdfs.getValue());
                onJyqkLinear.onClick(enterpriseInfoEntity);
                mPointPopu.dismiss();
            }
        });
        but_pop_ydqk_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    public interface OnJyqkLinear{
        void onClick(EnterpriseBusiness enterpriseBusiness);
    }
    public interface OnCzdwLinear{
        void onClick(EnterpriseEntity enterpriseBusiness);
    }
    public interface OnYdqkLinear{
        void onClick(EnterpriseInfoEntity enterpriseInfoEntity);
    }

}