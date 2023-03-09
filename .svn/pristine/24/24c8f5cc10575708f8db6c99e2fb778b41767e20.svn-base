package com.jymj.zhglxt.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity;
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity;
import com.jymj.zhglxt.rjhj.bean.YLEntity;
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity;
import com.jymj.zhglxt.widget.BubblePopupWindow;
import com.jymj.zhglxt.widget.SpinnerAdapter;
import com.jymj.zhglxt.widget.TextViews;
import com.jymj.zhglxt.widget.gxt.HVScrollView;
import com.jymj.zhglxt.widget.gxt.StarAirLayout;
import com.jymj.zhglxt.widget.zdybj.TextListLayout;
import com.jymj.zhglxt.zjd.adapter.ZsdAdapter;
import com.jymj.zhglxt.zjd.bean.CscsBean;
import com.jymj.zhglxt.zjd.bean.YlEntity;
import com.jymj.zhglxt.zjd.bean.ZhuShiEnum;
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity;
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity;
import com.jymj.zhglxt.zjd.bean.jsjb.LightBean;
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity;
import com.jymj.zhglxt.zjd.bean.yzt.User1;
import com.setsuna.common.baseapp.AppCache;
import com.setsuna.common.commonutils.ToastUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPreview;

public class PopuPointUtils {
    private static CommenPop mPointPopu;
    private static TextListLayout tllPopPointTdxg;
    private static TextListLayout tllPopPointQyxx;
    private static TextListLayout tllPopPointNyyq;
    private static TextListLayout tllPopPointDkf;
    private static TextListLayout tllPopPointWwj;
    private static Activity activity;
    private static LinearLayout yl_ll;
    private static HorizontalScrollView hsvPopPointTdxgHbxx;
    private static TextListLayout sl_text;
    private static ImageView iv_pop_point_tdxg;
    private static LinearLayout ll_pop_point_tdxg_zhj;
    private static TextView tv_pop_point_tdxg_zhj;
    private static RelativeLayout rlt_yzt_shaderImage;
    private static TextViews tvs_act_yzt_hjgxt;
    private static StarAirLayout shaderImage;
    private static HVScrollView hvScrollView;
    private static RecyclerView recyPointhb1;
    public static List<LightBean> lightItems = new ArrayList<LightBean>();//指示灯
    public static List<SysXzqEntity> sysXzqEntities = new ArrayList<SysXzqEntity>();//指示灯

    public static void setLightItems(List<LightBean> lightItems) {
        PopuPointUtils.lightItems = lightItems;
    }

    public static void setSysXzqEntities(List<SysXzqEntity> sysXzqEntities) {
        PopuPointUtils.sysXzqEntities = sysXzqEntities;
    }

    public static void initPopuPoint(Activity activity1, View view) {
        activity = activity1;
        mPointPopu = CommenPop.getNormalPopu(activity, R.layout.pop_point_tdxg, view);//pop_point
        View contentView = mPointPopu.getContentView();
        tllPopPointTdxg = contentView.findViewById(R.id.tll_pop_point_tdxg);
        tllPopPointQyxx = contentView.findViewById(R.id.tll_pop_point_qyxx);
        tllPopPointNyyq = contentView.findViewById(R.id.tll_pop_point_nyyq);
        tllPopPointDkf = contentView.findViewById(R.id.tll_pop_point_dkf);
        tllPopPointWwj = contentView.findViewById(R.id.tll_pop_point_wwj);
        iv_pop_point_tdxg = contentView.findViewById(R.id.iv_pop_point_tdxg);
        ll_pop_point_tdxg_zhj = contentView.findViewById(R.id.ll_pop_point_tdxg_zhj);
        tv_pop_point_tdxg_zhj = contentView.findViewById(R.id.tv_pop_point_tdxg_zhj);

        //院落点查
        yl_ll = contentView.findViewById(R.id.ll_frag_yzt_dc_ylxx1);
        hsvPopPointTdxgHbxx = contentView.findViewById(R.id.hsv_pop_point_tdxg_hbxx);
        sl_text = contentView.findViewById(R.id.tvs_frag_yzt_dc_ylxx1);
        rlt_yzt_shaderImage = contentView.findViewById(R.id.rlt_yzt_shaderImage1);
        tvs_act_yzt_hjgxt = contentView.findViewById(R.id.tvs_act_yzt_hjgxt1);
        shaderImage = contentView.findViewById(R.id.shaderImage1);
        hvScrollView = contentView.findViewById(R.id.hvScrollView1);
        recyPointhb1 = contentView.findViewById(R.id.recyPointhb11);

        TextView butPopPointTdxgClose = contentView.findViewById(R.id.but_pop_point_tdxg_close);
        CommenPop.backgroundAlpha(1f, activity);
        butPopPointTdxgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        /*recyPoint?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyPoint?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))*/
    }

    public static void startPopuPoint(View view, ArrayList<TextViewsEntity> values,String name, Integer[] integers) {
        tllPopPointTdxg.setVisibility(View.VISIBLE);
        if (!name.equals("")&&!ZhuShiEnum.getName(name).equals("")){
            iv_pop_point_tdxg.setVisibility(View.GONE);
//            iv_pop_point_tdxg.setVisibility(View.VISIBLE);
            ll_pop_point_tdxg_zhj.setVisibility(View.VISIBLE);
            String[] split = ZhuShiEnum.getName(name).split("&");
            String html="<font></font><br/>";
            if (split.length>1){
                html = "<font><strong color='#000000'>"+name+"(解释):</strong>"+split[0]+"</font><br/>" +
                        "<font><strong color='#000000'>"+name+"(使用):</strong>"+split[1]+"</font>";
            }else {
                html = "<font><strong color='#000000'>"+name+":</strong>"+ZhuShiEnum.getName(name)+"</font><br/>";
            }

            tv_pop_point_tdxg_zhj.setText(Html.fromHtml(html));
        }else {
            ll_pop_point_tdxg_zhj.setVisibility(View.GONE);
            iv_pop_point_tdxg.setVisibility(View.GONE);
        }
        yl_ll.setVisibility(View.GONE);
        tllPopPointQyxx.setVisibility(View.GONE);
        tllPopPointNyyq.setVisibility(View.GONE);
        tllPopPointDkf.setVisibility(View.GONE);
        tllPopPointWwj.setVisibility(View.GONE);
        tllPopPointTdxg.setValueTexts(values, integers);
        iv_pop_point_tdxg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BubblePopupWindow leftTopWindow = new BubblePopupWindow(activity);
                View bubbleView = LayoutInflater.from(activity).inflate(R.layout.layout_popup_view, null);
                TextView tvContent = (TextView) bubbleView.findViewById(R.id.tvContent);
                tvContent.setText(name+":"+ZhuShiEnum.getName(name));
                leftTopWindow.setBubbleView(bubbleView); // 设置气泡内容
                leftTopWindow.show(iv_pop_point_tdxg, Gravity.BOTTOM, 0); // 显示弹窗

//                initPopuReminder(activity,view,"提示一下","注解一下");
            }
        });
        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static void startPopuZjdPoint(View view, YLEntity YLPoint, ArrayList<ZhaiEntity> zhaiEntities) {
        iv_pop_point_tdxg.setVisibility(View.GONE);
        tllPopPointTdxg.setVisibility(View.GONE);
        tllPopPointQyxx.setVisibility(View.GONE);
        tllPopPointNyyq.setVisibility(View.GONE);
        tllPopPointDkf.setVisibility(View.GONE);
        tllPopPointWwj.setVisibility(View.GONE);
        yl_ll.setVisibility(View.VISIBLE);

        ArrayList<TextViewsEntity> tvDatas = new ArrayList<>();
        tvDatas.add(new TextViewsEntity("村名:", YLPoint.getXzqmc()));
        if (AppCache.getInstance().getCode().equals("110111009022")){
            hsvPopPointTdxgHbxx.setVisibility(View.GONE);
            tvDatas.add(new TextViewsEntity("名称:", YLPoint.getCt() + ""));
            if (YLPoint.getArea().compareTo(new BigDecimal(10000)) > 0) {
                tvDatas.add(new TextViewsEntity("占地面积:", YLPoint.getArea().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡"));
            } else {
                tvDatas.add(new TextViewsEntity("占地面积:", YLPoint.getArea().intValue() + "㎡"));
            }
            tvDatas.add(new TextViewsEntity("进度:", YLPoint.getCtps() + ""));
            tvDatas.add(new TextViewsEntity("用途:", YLPoint.getYt() + ""));
            sl_text.setValueTexts(tvDatas, new Integer[]{0, 1, 2, 3, 4});
        }else {
            tvDatas.add(new TextViewsEntity("门牌号:", YLPoint.getMph()));
            tvDatas.add(new TextViewsEntity("户主:", YLPoint.getHzmc()));
            tvDatas.add(new TextViewsEntity("一级分类:", YLPoint.getYlTypeText()));
            tvDatas.add(new TextViewsEntity("二级分类:", YLPoint.getFl2()));
            tvDatas.add(new TextViewsEntity("多少套房:", "暂无数据"));

            if (YLPoint.getArea().compareTo(new BigDecimal(10000)) > 0) {
                tvDatas.add(new TextViewsEntity("占地面积:", YLPoint.getArea().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡"));
            } else {
                tvDatas.add(new TextViewsEntity("占地面积:", YLPoint.getArea().intValue() + "㎡"));
            }
            if (YLPoint.getJianzhuArea().compareTo(new BigDecimal(10000)) > 0) {
                tvDatas.add(new TextViewsEntity("建筑面积:", YLPoint.getJianzhuArea().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡"));
            } else {
                tvDatas.add(new TextViewsEntity("建筑面积:", YLPoint.getJianzhuArea().intValue() + "㎡"));
            }

            tvDatas.add(new TextViewsEntity("人口:", YLPoint.getRk() + ""));
//                tvDatas.add(TextViewsEntity("流动人口:", YLPoint.getYlList().get(0)?.ldRk.toString()))
            tvDatas.add(new TextViewsEntity("农业人口:", YLPoint.getNongcount() + ""));
            tvDatas.add(new TextViewsEntity("非农人口:", YLPoint.getFeinongcount() + ""));
            tvDatas.add(new TextViewsEntity("户数:", YLPoint.getHucount() + ""));
            tvDatas.add(new TextViewsEntity("特殊情况:", YLPoint.getRemark()));
            tvDatas.add(new TextViewsEntity("流动人口:", YLPoint.getLdrks() + ""));
            //tvDatas.add(TextViewsEntity("流动面积:", YLPoint.getYlList().get(0)?.ldArea.toString()))
            sl_text.setValueTexts(tvDatas, new Integer[]{0, 1, 2, 5});
        }


        //红本
        ArrayList<YLEntity> hbList = new ArrayList<>();
        hbList.add(YLPoint);
        recyPointhb1.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyPointhb1.setAdapter(new BaseQuickAdapter<YLEntity, BaseViewHolder>(R.layout.item_point_hb, hbList) {
            @Override
            protected void convert(BaseViewHolder helper, YLEntity item) {
                helper.setText(R.id.hbbh_item, "088-003-00025-001")
                        .setText(R.id.hbcm_item, item.getXzqmc())
                        .setText(R.id.hbxm_item, item.getHzmc())
                        .setText(R.id.hbtm_item, "")
                        .setText(R.id.hbzrz_item, "")
                        .setText(R.id.hbzdmj_item, item.getArea().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString())
                        .setText(R.id.hbjzmj_item, item.getJianzhuArea().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString())
                        .setText(R.id.hbxcsj_item, "")
                        .setText(R.id.hbgdnd_item, "1993");
                helper.getView(R.id.hbcz_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("暂无红本信息！");

                    }
                });
            }

        });

        tvs_act_yzt_hjgxt.setVisibility(View.GONE);
        //关系图
        if (zhaiEntities.size() > 0) {//huji  YLPoint.huji  zhaiList
//                    tv_frag_yzt_dc_ylxx_ckxq.visibility = View.VISIBLE
            rlt_yzt_shaderImage.setVisibility(View.VISIBLE);
            HjgxtUtil.INSTANCE.setGxtData(activity, tvs_act_yzt_hjgxt, shaderImage, hvScrollView, zhaiEntities);

        } else {
            rlt_yzt_shaderImage.setVisibility(View.GONE);
        }

        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static void startPopuQyxxPoint(View view, EnterpriseBasisEntity YLPoint) {
        iv_pop_point_tdxg.setVisibility(View.GONE);
        tllPopPointTdxg.setVisibility(View.GONE);
        yl_ll.setVisibility(View.GONE);
        tllPopPointQyxx.setVisibility(View.VISIBLE);
        tllPopPointNyyq.setVisibility(View.GONE);
        tllPopPointDkf.setVisibility(View.GONE);
        tllPopPointWwj.setVisibility(View.GONE);

        ArrayList<TextViewsEntity> tvDatas1 = new ArrayList<>();
        /*tvDatas1.add(new TextViewsEntity("企业名称:", YLPoint.getFeiZhaiList().get(0).getQiyeName()));
        tvDatas1.add(new TextViewsEntity("营业执照:", YLPoint.getFeiZhaiList().get(0).getYyzzh()));
        tvDatas1.add(new TextViewsEntity("央企/国企/市属:", YLPoint.getFeiZhaiList().get(0).getQyxz()));
        tvDatas1.add(new TextViewsEntity("高薪企业:", YLPoint.getFeiZhaiList().get(0).getGxqy()));
        tvDatas1.add(new TextViewsEntity("注册地址:", YLPoint.getFeiZhaiList().get(0).getZcdz()));
        tvDatas1.add(new TextViewsEntity("是否上市:", YLPoint.getFeiZhaiList().get(0).getIfss()));
        tvDatas1.add(new TextViewsEntity("法人姓名:", YLPoint.getFeiZhaiList().get(0).getFrxm()));
        tvDatas1.add(new TextViewsEntity("联系方式:", YLPoint.getFeiZhaiList().get(0).getLxfs()));
        tvDatas1.add(new TextViewsEntity("注册资金(万元):", YLPoint.getFeiZhaiList().get(0).getZczj()));
        tvDatas1.add(new TextViewsEntity("外资企业注册资金(万元):", YLPoint.getFeiZhaiList().get(0).getWzzc()));
        tvDatas1.add(new TextViewsEntity("公司员工(本市):", YLPoint.getFeiZhaiList().get(0).getGsbs().toString()));
        tvDatas1.add(new TextViewsEntity("公司员工(外阜):", YLPoint.getFeiZhaiList().get(0).getGswq().toString()));
        tvDatas1.add(new TextViewsEntity("就业人口居住地(单位宿舍):", YLPoint.getFeiZhaiList().get(0).getJydw()));
        tvDatas1.add(new TextViewsEntity("就业人口居住地(各村):", YLPoint.getFeiZhaiList().get(0).getJygc()));
        tvDatas1.add(new TextViewsEntity("经营状态:", YLPoint.getFeiZhaiList().get(0).getJystatus()));
        tvDatas1.add(new TextViewsEntity("企业负责人:", YLPoint.getFeiZhaiList().get(0).getQyfzr()));
        tvDatas1.add(new TextViewsEntity("联系方式:", YLPoint.getFeiZhaiList().get(0).getFzrlxfs()));
        tvDatas1.add(new TextViewsEntity("邮箱地址:", YLPoint.getFeiZhaiList().get(0).getYxdz()));
        tvDatas1.add(new TextViewsEntity("行业类别:", YLPoint.getFeiZhaiList().get(0).getIndustry()));
        tvDatas1.add(new TextViewsEntity("主要产品:", YLPoint.getFeiZhaiList().get(0).getZycp()));
        tvDatas1.add(new TextViewsEntity("2019营业收入(万元):", YLPoint.getFeiZhaiList().get(0).getYysr2019()));
        tvDatas1.add(new TextViewsEntity("2019工业资产总值(万元):", YLPoint.getFeiZhaiList().get(0).getCz2019()));
        tvDatas1.add(new TextViewsEntity("经营地址:", YLPoint.getFeiZhaiList().get(0).getJydz()));
        tvDatas1.add(new TextViewsEntity("使用面积(㎡):", YLPoint.getFeiZhaiList().get(0).getSymj()));
        tvDatas1.add(new TextViewsEntity("出租面积(㎡):", YLPoint.getFeiZhaiList().get(0).getCzmj()));
        tvDatas1.add(new TextViewsEntity("剩余面积(㎡):", YLPoint.getFeiZhaiList().get(0).getYxmj()));*/

        tvDatas1.add(new TextViewsEntity("企业名称:", YLPoint.getName()));
        tvDatas1.add(new TextViewsEntity("土地使用人:", YLPoint.getFwcqr()));
        tvDatas1.add(new TextViewsEntity("占地面积:", YLPoint.getZdmj().toString()));
        tvDatas1.add(new TextViewsEntity("建筑面积:", YLPoint.getJzmj().toString()));
        tvDatas1.add(new TextViewsEntity("用地权属:", YLPoint.getQs()));
        tvDatas1.add(new TextViewsEntity("行政区:", YLPoint.getXzqmc()));
        tvDatas1.add(new TextViewsEntity("地块编号:", YLPoint.getDkbh()));

        tllPopPointQyxx.setValueTexts(tvDatas1, new Integer[]{0,1,4});
        
        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static void startPopuNyyqPoint(View view, YztNyyqEntity YLPoint) {
        iv_pop_point_tdxg.setVisibility(View.GONE);
        tllPopPointTdxg.setVisibility(View.GONE);
        yl_ll.setVisibility(View.GONE);
        tllPopPointQyxx.setVisibility(View.GONE);
        tllPopPointNyyq.setVisibility(View.VISIBLE);
        tllPopPointDkf.setVisibility(View.GONE);
        tllPopPointWwj.setVisibility(View.GONE);
        ArrayList<TextViewsEntity> tvDatas1 = new ArrayList<>();
        tvDatas1.add(new TextViewsEntity("园区名称:", YLPoint.getXmmc()));
        tvDatas1.add(new TextViewsEntity("单位名称:", YLPoint.getDwmc()));
        tvDatas1.add(new TextViewsEntity("园区类型:", YLPoint.getLx()));
        tvDatas1.add(new TextViewsEntity("行政区:", YLPoint.getXzqmc()));
        tvDatas1.add(new TextViewsEntity("联系人:", YLPoint.getLxr()));
        tvDatas1.add(new TextViewsEntity("联系电话:", YLPoint.getPhone()));
        tvDatas1.add(new TextViewsEntity("确认编号:", YLPoint.getQrbh()));
        tvDatas1.add(new TextViewsEntity("用地面积(亩):", YLPoint.getYdmj().toString()));

        tllPopPointNyyq.setValueTexts(tvDatas1, new Integer[]{0,1});

        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static void startPopuDkfPoint(View view, DkfVO YLPoint) {
//        Log.e("startPopuDkfPoint",new Gson().toJson(YLPoint));
        iv_pop_point_tdxg.setVisibility(View.GONE);
        tllPopPointTdxg.setVisibility(View.GONE);
        yl_ll.setVisibility(View.GONE);
        tllPopPointQyxx.setVisibility(View.GONE);
        tllPopPointNyyq.setVisibility(View.GONE);
        tllPopPointDkf.setVisibility(View.VISIBLE);
        tllPopPointWwj.setVisibility(View.GONE);
        ArrayList<TextViewsEntity> tvDatas1 = new ArrayList<>();
        tvDatas1.add(new TextViewsEntity("规划名称:", YLPoint.getGhmc()));
        tvDatas1.add(new TextViewsEntity("行政区:", YLPoint.getXzqmc()));
        tvDatas1.add(new TextViewsEntity("面积:", YLPoint.getArea().toString()+"公顷"));
        if (!YLPoint.getRjl().equals(""))
        tvDatas1.add(new TextViewsEntity("容积率:", YLPoint.getRjl()));
        /*
        tvDatas1.add(new TextViewsEntity("行政区代码:", YLPoint.getXzqdm()));
        tvDatas1.add(new TextViewsEntity("占地面积(亩):", YLPoint.getZdmj()+""));
        tvDatas1.add(new TextViewsEntity("建筑面积(亩):", YLPoint.getJzmj()+""));*/

        tllPopPointDkf.setValueTexts(tvDatas1, new Integer[]{0,1,2,3});

        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static void startPopuWwjPoint(View view, WwjEntity wwjEntity){
        iv_pop_point_tdxg.setVisibility(View.GONE);
        tllPopPointTdxg.setVisibility(View.GONE);
        yl_ll.setVisibility(View.GONE);
        tllPopPointQyxx.setVisibility(View.GONE);
        tllPopPointNyyq.setVisibility(View.GONE);
        tllPopPointDkf.setVisibility(View.GONE);
        tllPopPointWwj.setVisibility(View.VISIBLE);

        ArrayList<TextViewsEntity> tvDatas1 = new ArrayList<>();
        tvDatas1.add(new TextViewsEntity("编码:", wwjEntity.getWybm()));
        tvDatas1.add(new TextViewsEntity("行政区:", wwjEntity.getXzqmc()));
        tvDatas1.add(new TextViewsEntity("占地面积:", wwjEntity.getZdmj().intValue()+"㎡"));//wwjEntity.getZdmj()
        tvDatas1.add(new TextViewsEntity("建设面积:", wwjEntity.getJsmj().intValue()+"㎡"));
        tvDatas1.add(new TextViewsEntity("图斑状态:", wwjEntity.getTbzt()));
        tvDatas1.add(new TextViewsEntity("一级分类:", wwjEntity.getYjfl()));
        tvDatas1.add(new TextViewsEntity("二级分类:", wwjEntity.getEjfl()));
        tvDatas1.add(new TextViewsEntity("三级分类:", wwjEntity.getSjfl()));
        tvDatas1.add(new TextViewsEntity("备注:", wwjEntity.getRemark()));

        tllPopPointWwj.setValueTexts(tvDatas1, new Integer[]{0,1,2,3,4,5,6,7,8});

        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    //point/getYl
    public static void initPointGetYl(Activity activity1, View view, YLEntity ylEntity) {//lightItems
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_reminder, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_pop_point_reminder_title = contentView.findViewById(R.id.tv_pop_point_reminder_title);
        ImageView iv_pop_point_reminder_error = contentView.findViewById(R.id.iv_pop_point_reminder_error);
        TextListLayout tll_pop_point_reminder = contentView.findViewById(R.id.tll_pop_point_reminder);
        RecyclerView rlv_pop_poitn_reminder = contentView.findViewById(R.id.rlv_pop_poitn_reminder);
        ArrayList<TextViewsEntity> tvDatas = new ArrayList<>();
        tvDatas.add(new TextViewsEntity("名称:", ylEntity.getName()));
//        tvDatas.add(new TextViewsEntity("用途:", ylEntity.getYt()));
        tvDatas.add(new TextViewsEntity("状态:", ylEntity.getCtps()));
        tvDatas.add(new TextViewsEntity("占地面积:", ylEntity.getArea().toString()));
        tvDatas.add(new TextViewsEntity("建筑规模:", ylEntity.getJianzhuArea().toString()));
        tll_pop_point_reminder.setValueTexts(tvDatas, new Integer[]{0,1,2,3,4,5,6,7,8});
        /*object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, PjEnviorFileList) {
            override fun convert(helper: BaseViewHolder?, item: PjEnviorFileEntity?) {
                val view = helper!!.getView<ImageView>(R.id.iv_teng_photo)
                        val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                        val pic: String = ApiConstants.BASE_URL + item!!.path
                val s1 = pic.replace("\\", "/")
                Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view)
                helper!!.setText(R.id.tv_teng_photo_name, item.name)
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(activity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            OkGo.post<String> (ApiConstants.ENVIORFILEDELETE).upJson(item.id.toString()+"").execute(object :
                            BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(activity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose <*> = Gson().fromJson(cash, object : TypeToken<BaseRespose <*>?>() {}.type)
                                        if (json.code==0){
                                            PjEnviorFileList.removeAt(position)
                                            notifyDataSetChanged()
//                                            mView.returnDeleteFileEnvironmental(json)
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("确定")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("网络错误")
                                }

                            })
                        }
                    })
                    // 设置一个NegativeButton
                    builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog!!.dismiss()
                        }
                    })
                    builder.show()
                }
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in PjEnviorFileList){
                        val pic = ApiConstants.BASE_URL + i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(activity!!)


                }
            }
        }*/
        rlv_pop_poitn_reminder.setLayoutManager(new GridLayoutManager(activity1,3));
        rlv_pop_poitn_reminder.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_teng_tui_photo, ylEntity.getFiles()) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                ImageView view = helper.getView(R.id.iv_teng_photo);
                ImageView iv_tent_tui_delete = helper.getView(R.id.iv_tent_tui_delete);
                LinearLayout ll_teng_tui = helper.getView(R.id.ll_teng_tui);
                       /* val pic: String = ApiConstants.BASE_URL + item!!.path*/
                String s1 = item.replace("\\", "/");
                Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view);
                iv_tent_tui_delete.setVisibility(View.GONE);
//                helper.setText(R.id.tv_teng_photo_name, item.name)
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String> pathList = new ArrayList<String>();
                        for (int i = 0; i < ylEntity.getFiles().size(); i++) {
                            String s1 = ylEntity.getFiles().get(i).replace("\\", "/");
                            pathList.add(s1);
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.getAdapterPosition())
                                .setShowDeleteButton(false)
                                .start(activity1);
                    }
                });
            }
        });
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        CommenPop.backgroundAlpha(0.5f, activity1);
        iv_pop_point_reminder_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        /*mPointPopu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });*/
    }
    //point/getYl
    public static void initPopuTdkf(Activity activity1, View view, List<SysXzqEntity> xzqBeans
            , String points, OnPopuTdkfLinster onPopuTdkfLinster) {//lightItems
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_tdkf, view);//pop_point
        View contentView = mPointPopu.getContentView();
        Switch mStTdkf = contentView.findViewById(R.id.st_tdkf);
        AppCompatButton bt_cancel_tdkf = contentView.findViewById(R.id.bt_cancel_tdkf);//取消
        AppCompatButton bt_ok_tdkf = contentView.findViewById(R.id.bt_ok_tdkf);//确定
        RecyclerView recy_cbs_tdkf = contentView.findViewById(R.id.recy_cbs_tdkf);//村名
        Spinner sp_jzmj_list = contentView.findViewById(R.id.sp_jzmj_list);//置换建筑面积
        EditText edt_quantitle = contentView.findViewById(R.id.edt_quantitle);//是否自筹,自筹金额(万元)
        EditText edt_bilprice = contentView.findViewById(R.id.edt_bilprice);//区位补偿价(元)
        EditText edt_czfw = contentView.findViewById(R.id.edt_czfw);//独立住宅75%以内
        EditText edt_dlzzyw = contentView.findViewById(R.id.edt_dlzzyw);//独立住宅75%以外
        EditText edt_zzl = contentView.findViewById(R.id.edt_zzl);//自主楼
        EditText edt_zjdzdmj = contentView.findViewById(R.id.edt_zjdzdmj);//场地看护费(元/月)

        recy_cbs_tdkf.setLayoutManager(new GridLayoutManager(activity1, 3));
        recy_cbs_tdkf.addItemDecoration(new SpacesItemDecoration(10));
        BaseQuickAdapter<SysXzqEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<SysXzqEntity, BaseViewHolder>(R.layout.item_cbs_tdkf, xzqBeans) {
            @Override
            protected void convert(BaseViewHolder helper, SysXzqEntity item) {
                CheckBox view1 = helper.getView(R.id.cb_item_tdkf);
                view1.setText(item.getName());
                view1.setChecked(item.isChecked());
                view1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    xzqBeans.get(helper.getAdapterPosition()).setChecked(isChecked);
                });
            }
        };
        recy_cbs_tdkf.setAdapter(baseQuickAdapter);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(activity1);//SpinnerAdapter
        sp_jzmj_list.setAdapter(spinnerAdapter);
        List<String> spztList = new ArrayList();
        spztList.add("");
        spztList.add("200");
        spztList.add("300");
        spinnerAdapter.setDatas(spztList);
        mStTdkf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recy_cbs_tdkf.setVisibility(View.VISIBLE);
                } else {
                    recy_cbs_tdkf.setVisibility(View.GONE);
                }
            }
        });

        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        CommenPop.backgroundAlpha(0.5f, activity1);
        bt_cancel_tdkf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
        bt_ok_tdkf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> mCodeList = new ArrayList<>();
                for (int i = 0; i < xzqBeans.size(); i++) {
                    SysXzqEntity xzqBean = xzqBeans.get(i);
                    if (xzqBean.isChecked()){
                        mCodeList.add(xzqBean.getCode());
                        xzqBean.setChecked(false);
                    }
                }
                String zc = edt_quantitle.getText().toString();
                String qwbcj = edt_bilprice.getText().toString();
                String dlzzyn = edt_czfw.getText().toString();
                String dlzzyw = edt_dlzzyw.getText().toString();
                String zzl = edt_zzl.getText().toString();
                String cdkhf = edt_zjdzdmj.getText().toString();
                String zhjzmj = sp_jzmj_list.getSelectedItem().toString();

                CscsBean cscsBean = new CscsBean();
                if (mStTdkf.isChecked()){
                    cscsBean.setCodeList(mCodeList);
                }else {
                    cscsBean.setPoints(points);
                }
                if (!zc.equals("")){
                    cscsBean.setZch(Integer.parseInt(zc));
                }
                if (!qwbcj.equals("")){
                    cscsBean.setQwbcj(Integer.parseInt(qwbcj));
                }else {
                    cscsBean.setQwbcj(6000);
                }
                if (!dlzzyn.equals("")){
                    cscsBean.setDlzz75n(Integer.parseInt(dlzzyn));
                }else {
                    cscsBean.setDlzz75n(6500);
                }
                if (!dlzzyw.equals("")){
                    cscsBean.setDlzz75w(Integer.parseInt(dlzzyw));
                }else {
                    cscsBean.setDlzz75w(8500);
                }
                if (!zzl.equals("")){
                    cscsBean.setZzl(Integer.parseInt(zzl));
                }else {
                    cscsBean.setZzl(5500);
                }
                if (!cdkhf.equals("")){
                    cscsBean.setKhf(Integer.parseInt(cdkhf));
                }else {
                    cscsBean.setKhf(4800);
                }
                if (!zhjzmj.equals("")){
                    cscsBean.setJzgm(Integer.parseInt(zhjzmj));
                }
                if (mCodeList.size()>0||!points.equals("")){
                    onPopuTdkfLinster.onClick(cscsBean);
                    mPointPopu.dismiss();
                }else {
                    ToastUtils.showShort("请框选或选择村");
                }
            }
        });
        /*mPointPopu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });*/
    }
    //指示灯
    public static void initPopuZsdzj(Activity activity1, View view) {//lightItems
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_zsdzj, view);//pop_point
        View contentView = mPointPopu.getContentView();
        ImageView iv_pop_point_zsdzj_error = contentView.findViewById(R.id.iv_pop_point_zsdzj_error);
        RecyclerView rlv_pop_point_zsdzj_sd = contentView.findViewById(R.id.rlv_pop_point_zsdzj_sd);
        RecyclerView rlv_pop_point_zsdzj_qd = contentView.findViewById(R.id.rlv_pop_point_zsdzj_qd);
        ArrayList<LightBean> shiLightBeans = new ArrayList<>();
        ArrayList<LightBean> quLightBeans = new ArrayList<>();
        shiLightBeans.clear();
        quLightBeans.clear();
        for (int i = 0; i < lightItems.size(); i++) {
            LightBean lightBean = lightItems.get(i);
            lightBean.setCheck(true);
            if (lightBean.getType() == 1){//市
                shiLightBeans.add(lightBean);
            }else if (lightBean.getType() == 2){//区
                quLightBeans.add(lightBean);
            }
        }
        /*lightItems.clear();
        lightItems.add(new LightBean("#000000","黑色"));
        lightItems.add(new LightBean("#00ff00","不清楚"));
        lightItems.add(new LightBean("#662814","不知道"));
        lightItems.add(new LightBean("#0000ff","呵呵"));*/
        rlv_pop_point_zsdzj_sd.setLayoutManager(new LinearLayoutManager(activity1,LinearLayoutManager.VERTICAL,false));
        rlv_pop_point_zsdzj_sd.setAdapter(new ZsdAdapter(activity1,shiLightBeans,2));
        rlv_pop_point_zsdzj_qd.setLayoutManager(new LinearLayoutManager(activity1,LinearLayoutManager.VERTICAL,false));
        rlv_pop_point_zsdzj_qd.setAdapter(new ZsdAdapter(activity1,quLightBeans,2));

        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        iv_pop_point_zsdzj_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
    }
    public static LightBean setSelectLight(int id){
        for (int i = 0; i < lightItems.size(); i++) {
            LightBean lightBean = lightItems.get(i);
            if (lightBean.id == id){
                return lightBean;
            }
        }
        return null;
    }
    public static void initPopuSelectNum(Activity activity1, View view, ArrayList<Integer> nums,OnSelectNumLinster onSelectNumLinster) {
//        activity = activity1;
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_select_num, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_point_select_num_cancel = contentView.findViewById(R.id.tv_point_select_num_cancel);
        RecyclerView rlv_select_num = contentView.findViewById(R.id.rlv_select_num);
        rlv_select_num.setLayoutManager(new LinearLayoutManager(activity1,LinearLayoutManager.VERTICAL,false));
        rlv_select_num.setAdapter(new BaseQuickAdapter<Integer,BaseViewHolder>(R.layout.item_select_num_layout,nums) {
            @Override
            protected void convert(BaseViewHolder helper, Integer item) {
                helper.setText(R.id.tv_item_select_num,""+item);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectNumLinster.onClick(item);
                        mPointPopu.dismiss();
                    }
                });
            }

        });

        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        tv_point_select_num_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
    }
    public static void initPopuSelectYear(Activity activity1, View view, ArrayList<String> nums,OnSelectYearLinster onSelectNumLinster) {
//        activity = activity1;
        CommenPop mPointPopu = CommenPop.getNormalPopu(activity1, R.layout.pop_point_select_num, view);//pop_point
        View contentView = mPointPopu.getContentView();
        TextView tv_point_select_num_cancel = contentView.findViewById(R.id.tv_point_select_num_cancel);
        TextView tv_pop_point_select_title = contentView.findViewById(R.id.tv_pop_point_select_title);
        RecyclerView rlv_select_num = contentView.findViewById(R.id.rlv_select_num);
        rlv_select_num.setLayoutManager(new LinearLayoutManager(activity1,LinearLayoutManager.VERTICAL,false));
        tv_pop_point_select_title.setText("请选择时间");
        rlv_select_num.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_select_num_layout,nums) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item_select_num,""+item);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectNumLinster.onClick(item);
                        mPointPopu.dismiss();
                    }
                });
            }

        });

        CommenPop.backgroundAlpha(0.5f, activity1);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        tv_point_select_num_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
    }

    public interface OnSelectNumLinster{
        void onClick(int num);
    }
    public interface OnSelectYearLinster{
        void onClick(String year);
    }
    public interface OnPopuTdkfLinster{
        void onClick(CscsBean cscsBean);
    }

}