package com.jymj.zhglxt.zjd.activity.yzt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.yzt.TjfxDetailBean
import com.jymj.zhglxt.zjd.bean.yzt.layerListBean
import com.jymj.zhglxt.zjd.contract.TjfxActContract
import com.jymj.zhglxt.zjd.presenter.TjfxActPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_tjfx_detail.*

class TjfxDetailActivity : BaseActivity<TjfxActPresenter, TjfxActContract.Model>(), TjfxActContract.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_tjfx_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        ic_act_tjfx_detail_back.setOnClickListener {
            finish()
        }
        mtl1_act_tjfx_detail_tdly2009.setOnTitleListLister {
            if (ll_act_tjfx_detail_tdly2009.visibility == View.VISIBLE){
                ll_act_tjfx_detail_tdly2009.visibility = View.GONE
            }else{
                ll_act_tjfx_detail_tdly2009.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_tdly2018.setOnTitleListLister {///土地利用/2018年
            if (ll_act_tjfx_detail_tdly2018.visibility == View.VISIBLE){
                ll_act_tjfx_detail_tdly2018.visibility = View.GONE
            }else{
                ll_act_tjfx_detail_tdly2018.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_tdly2020.setOnTitleListLister {///土地利用/2020年
            if (ll_act_tjfx_detail_tdly2020.visibility == View.VISIBLE){
                ll_act_tjfx_detail_tdly2020.visibility = View.GONE
            }else{
                ll_act_tjfx_detail_tdly2020.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_fqgh.setOnTitleListLister {///规划情况/分区规划
            if (tll_act_tjfx_detail_fqgh.visibility == View.VISIBLE){
                tll_act_tjfx_detail_fqgh.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_fqgh.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_zygh.setOnTitleListLister {///规划情况/镇域规划
            if (tll_act_tjfx_detail_zygh.visibility == View.VISIBLE){
                tll_act_tjfx_detail_zygh.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_zygh.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_gbkj.setOnTitleListLister {///耕保空间
            if (tll_act_tjfx_detail_gbkj.visibility == View.VISIBLE){
                tll_act_tjfx_detail_gbkj.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_gbkj.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_stjs.setOnTitleListLister {///生态建设
            if (tll_act_tjfx_detail_stjs.visibility == View.VISIBLE){
                tll_act_tjfx_detail_stjs.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_stjs.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_qytt.setOnTitleListLister {///企业腾退
            if (tll_act_tjfx_detail_qytt.visibility == View.VISIBLE){
                tll_act_tjfx_detail_qytt.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_qytt.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_tdqs.setOnTitleListLister {///土地权属
            if (tll_act_tjfx_detail_tdqs.visibility == View.VISIBLE){
                tll_act_tjfx_detail_tdqs.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_tdqs.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_zjd.setOnTitleListLister {///宅基地
            if (tll_act_tjfx_detail_zjd.visibility == View.VISIBLE){
                tll_act_tjfx_detail_zjd.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_zjd.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_fz.setOnTitleListLister {///非宅
            if (tll_act_tjfx_detail_fz.visibility == View.VISIBLE){
                tll_act_tjfx_detail_fz.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_fz.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_rk.setOnTitleListLister {///人口
            if (tll_act_tjfx_detail_rk.visibility == View.VISIBLE){
                tll_act_tjfx_detail_rk.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_rk.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_flzltz.setOnTitleListLister {///分类治理台账
            if (tll_act_tjfx_detail_flzltz.visibility == View.VISIBLE){
                tll_act_tjfx_detail_flzltz.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_flzltz.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_cwtdtz.setOnTitleListLister {///拆违腾地台账
            if (tll_act_tjfx_detail_cwtdtz.visibility == View.VISIBLE){
                tll_act_tjfx_detail_cwtdtz.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_cwtdtz.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_tdlz.setOnTitleListLister {///拆违腾地台账
            if (tll_act_tjfx_detail_tdlz.visibility == View.VISIBLE){
                tll_act_tjfx_detail_tdlz.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_tdlz.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_nyxm.setOnTitleListLister {///农业项目
            if (tll_act_tjfx_detail_nyxm.visibility == View.VISIBLE){
                tll_act_tjfx_detail_nyxm.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_nyxm.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_tdfk.setOnTitleListLister {///土地复垦
            if (tll_act_tjfx_detail_tdfk.visibility == View.VISIBLE){
                tll_act_tjfx_detail_tdfk.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_tdfk.visibility = View.VISIBLE
            }
        }
        mtl1_act_tjfx_detail_zcfz.setOnTitleListLister {///资产负债
            if (tll_act_tjfx_detail_zcfz.visibility == View.VISIBLE){
                tll_act_tjfx_detail_zcfz.visibility = View.GONE
            }else{
                tll_act_tjfx_detail_zcfz.visibility = View.VISIBLE
            }
        }

    }

    override fun initDatas() {
        val cunCode = intent.getStringExtra("cunCode")
        val cun = intent.getStringExtra("cun")
        val tcList = intent.getIntegerArrayListExtra("tcList1")
        tv_act_tjfx_detail_fxjg.setText(cun+"分析结果")
        mPresenter.getTjfx(cunCode,tcList)

        //分区规划
        val fqghDatas = java.util.ArrayList<TextViewsEntity>()
        fqghDatas.add(TextViewsEntity("建设空间:", ""))
        fqghDatas.add(TextViewsEntity("非建设空间:", ""))
        tll_act_tjfx_detail_fqgh.setValueTexts(fqghDatas, arrayOf(0, 1))

        //规划空间
        val gbkjDatas = java.util.ArrayList<TextViewsEntity>()
        gbkjDatas.add(TextViewsEntity("永久基本农田面积:", ""))
        gbkjDatas.add(TextViewsEntity("永久基本农田储备区面积:", ""))
        gbkjDatas.add(TextViewsEntity("耕地保有量面积:", ""))
        tll_act_tjfx_detail_gbkj.setValueTexts(gbkjDatas, arrayOf(0,1,2))
        //生态建设
        val stjsDatas = java.util.ArrayList<TextViewsEntity>()
        stjsDatas.add(TextViewsEntity("历年临时绿化面积:", ""))
        stjsDatas.add(TextViewsEntity("历年平原造林面积:", ""))
        tll_act_tjfx_detail_stjs.setValueTexts(stjsDatas, arrayOf(0,1,2))

        //人口
        val rkDatas = java.util.ArrayList<TextViewsEntity>()
        rkDatas.add(TextViewsEntity("人口总数:", ""))
        rkDatas.add(TextViewsEntity("农业人口:", ""))
        rkDatas.add(TextViewsEntity("非农业人口:", ""))
        rkDatas.add(TextViewsEntity("流动人口:", ""))
        tll_act_tjfx_detail_rk.setValueTexts(rkDatas, arrayOf(0,1,2,3))
        //分类治理台账
        val tlzltzDatas = java.util.ArrayList<TextViewsEntity>()
        tlzltzDatas.add(TextViewsEntity("台账分类进展:", ""))
        tlzltzDatas.add(TextViewsEntity("台账处置进展:", ""))
        tll_act_tjfx_detail_flzltz.setValueTexts(tlzltzDatas, arrayOf(0,1,2,3))
        //拆违腾地台账
        val cwtdtzDatas = java.util.ArrayList<TextViewsEntity>()
        cwtdtzDatas.add(TextViewsEntity("台账分类进展:", ""))
        cwtdtzDatas.add(TextViewsEntity("台账处置进展:", ""))
        tll_act_tjfx_detail_cwtdtz.setValueTexts(cwtdtzDatas, arrayOf(0,1,2,3))
        //土地流转
        val tdlzDatas = java.util.ArrayList<TextViewsEntity>()
        tdlzDatas.add(TextViewsEntity("土地流转面积:", ""))
        tll_act_tjfx_detail_tdlz.setValueTexts(tdlzDatas, arrayOf(0,1,2,3))
        //农业项目
        val nyxmDatas = java.util.ArrayList<TextViewsEntity>()
        nyxmDatas.add(TextViewsEntity("农业项目面积:", ""))
        tll_act_tjfx_detail_nyxm.setValueTexts(nyxmDatas, arrayOf(0,1,2,3))
        //土地复垦
        val tdfkDatas = java.util.ArrayList<TextViewsEntity>()
        tdfkDatas.add(TextViewsEntity("土地复垦面积:", ""))
        tll_act_tjfx_detail_tdfk.setValueTexts(tdfkDatas, arrayOf(0,1,2,3))


    }

    override fun returnTjfx(renovated: TjfxDetailBean) {
        if (renovated!=null){
            val tdly2009 = renovated.getTdly2009()
            if (tdly2009!=null){
                ll1_act_tjfx_detail_tdly2009.visibility = View.VISIBLE
                tv_act_tjfx_nyd_gd.setText(tdly2009.nydgd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_yd.setText(tdly2009.nydyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_ld.setText(tdly2009.nydld.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_cd.setText(tdly2009.nydcd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_jtyd.setText(tdly2009.nydjttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_syjsjssyd.setText(tdly2009.nydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_qtyd.setText(tdly2009.nydqttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_jsyd.setText(tdly2009.jsyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_jtysyd.setText(tdly2009.jsydjtyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_syjslssyd.setText(tdly2009.jsydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_qttd.setText(tdly2009.jsydqttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_cd.setText(tdly2009.wlydcd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_syjslssyd.setText(tdly2009.wlydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_qttd.setText(tdly2009.wlydqttd.stripTrailingZeros().toPlainString()+"亩")
            }
            val tdly2018 = renovated.getTdly2018()
            if (tdly2018!=null){
                ll1_act_tjfx_detail_tdly2018.visibility = View.VISIBLE
                tv_act_tjfx_nyd_gd1.setText(tdly2018.nydgd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_yd1.setText(tdly2018.nydyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_ld1.setText(tdly2018.nydld.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_cd1.setText(tdly2018.nydcd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_jtyd1.setText(tdly2018.nydjttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_syjsjssyd1.setText(tdly2018.nydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_qtyd1.setText(tdly2018.nydqttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_jsyd1.setText(tdly2018.jsyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_jtysyd1.setText(tdly2018.jsydjtyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_syjslssyd1.setText(tdly2018.jsydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_qttd1.setText(tdly2018.jsydqttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_cd1.setText(tdly2018.wlydcd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_syjslssyd1.setText(tdly2018.wlydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_qttd1.setText(tdly2018.wlydqttd.stripTrailingZeros().toPlainString()+"亩")
            }
            val tdly2020 = renovated.getTdly2020()
            if (tdly2020!=null){
                ll1_act_tjfx_detail_tdly2020.visibility = View.VISIBLE
                tv_act_tjfx_nyd_gd2.setText(tdly2020.nydgd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_zzyyd2.setText(tdly2020.nydyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_ld2.setText(tdly2020.nydld.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_cd2.setText(tdly2020.nydcd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_jttd2.setText(tdly2020.nydjttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_syjsjssyd2.setText(tdly2020.nydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_nyd_qtyd2.setText(tdly2020.nydqttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_sfyd2.setText(tdly2020.jsydsfyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_gkyd2.setText(tdly2020.jsydgkyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_zzyd2.setText(tdly2020.jsydzzyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_ggglyggfw2.setText(tdly2020.jsydggyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_tsyd2.setText(tdly2020.jsydtsyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_jtysyd2.setText(tdly2020.jsydjtyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_syjslssyd2.setText(tdly2020.jsydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_jsyd_qttd2.setText(tdly2020.jsydqttd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_sd2.setText(tdly2020.wlydsd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_cd2.setText(tdly2020.wlydcd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_syjslssyd2.setText(tdly2020.wlydslyd.stripTrailingZeros().toPlainString()+"亩")
                tv_act_tjfx_wlyd_qttd2.setText(tdly2020.wlydqttd.stripTrailingZeros().toPlainString()+"亩")
            }
            val gh = renovated.getGh()
            if (gh.size>0){
                ll1_act_tjfx_detail_zygh.visibility = View.VISIBLE
                //镇域规划
                val intList = arrayOfNulls<Int>(gh.size)
                val zyghDatas = java.util.ArrayList<TextViewsEntity>()
                for (i in gh.indices){
                    val get = gh.get(i)
                    intList[i] = i
                    zyghDatas.add(TextViewsEntity(get.getName(), get.getArea().stripTrailingZeros().toPlainString()+"㎡"))
                }
                tll_act_tjfx_detail_zygh.setValueTexts(zyghDatas, intList)//arrayOf(0)
            }
            val cctt = renovated.getCctt()
            if (cctt!=null){
                ll1_act_tjfx_detail_qytt.visibility = View.VISIBLE
                //企业腾退
                val qyttDatas = java.util.ArrayList<TextViewsEntity>()
                qyttDatas.add(TextViewsEntity("历年腾退企业宗数:", "${cctt.getCounts()}"))
                qyttDatas.add(TextViewsEntity("历年腾退企业面积:", cctt.getArea().stripTrailingZeros().toPlainString()+"㎡"))//.stripTrailingZeros().toPlainString()+"㎡"
                qyttDatas.add(TextViewsEntity("历年腾退企业建筑面积:", cctt.getJzmj().stripTrailingZeros().toPlainString()+"万㎡"))
                tll_act_tjfx_detail_qytt.setValueTexts(qyttDatas, arrayOf(0,1,2))
            }
            val qs = renovated.getQs()
            if (qs.size>0){
                ll1_act_tjfx_detail_tdqs.visibility = View.VISIBLE
                //土地权属
                val intList = arrayOfNulls<Int>(qs.size)
                val tdqsDatas = java.util.ArrayList<TextViewsEntity>()
                for (i in qs.indices){
                    val get = qs.get(i)
                    intList[i] = i
                    tdqsDatas.add(TextViewsEntity(get.getName(), get.getArea().stripTrailingZeros().toPlainString()+"㎡"))
                }
                tll_act_tjfx_detail_tdqs.setValueTexts(tdqsDatas, intList)

            }
            val zhai = renovated.getZhai()
            if (zhai.size>0){
                ll1_act_tjfx_detail_zjd.visibility = View.VISIBLE
                //宅基地
                val zjdDatas = java.util.ArrayList<TextViewsEntity>()
                val get = zhai.get(0)
                zjdDatas.add(TextViewsEntity("宗数:", "${get.getCounts()}"))
                zjdDatas.add(TextViewsEntity("占地面积:", get.getArea().stripTrailingZeros().toPlainString()+"㎡"))
                zjdDatas.add(TextViewsEntity("建筑面积:", get.getJzmj().stripTrailingZeros().toPlainString()+"万㎡"))
                tll_act_tjfx_detail_zjd.setValueTexts(zjdDatas, arrayOf(0,1,2))
            }
            val feiZhai = renovated.getFeiZhai()
            if (feiZhai.size>0){
                ll1_act_tjfx_detail_fz.visibility = View.VISIBLE
                val get = feiZhai.get(0)
                //非宅
                val fzDatas = java.util.ArrayList<TextViewsEntity>()
                fzDatas.add(TextViewsEntity("宗数:", "${get.getCounts()}"))
                fzDatas.add(TextViewsEntity("占地面积:", get.getArea().stripTrailingZeros().toPlainString()+"㎡"))
                fzDatas.add(TextViewsEntity("建筑面积:", get.getJzmj().stripTrailingZeros().toPlainString()+"万㎡"))
                tll_act_tjfx_detail_fz.setValueTexts(fzDatas, arrayOf(0,1,2))
            }
            val lh = renovated.getLh()
            if (lh.size>0){
                ll1_act_tjfx_detail_stjs.visibility = View.VISIBLE
                //生态建设
                val intList = arrayOfNulls<Int>(lh.size)
                val tdqsDatas = java.util.ArrayList<TextViewsEntity>()
                for (i in lh.indices){
                    val get = lh.get(i)
                    intList[i] = i
                    tdqsDatas.add(TextViewsEntity(get.getName(), get.getArea().stripTrailingZeros().toPlainString()+"㎡"))
                }
                tll_act_tjfx_detail_stjs.setValueTexts(tdqsDatas, intList)
            }
            val skt = renovated.getSkt()
            if (skt.size>0){
                ll1_act_tjfx_detail_gbkj.visibility = View.VISIBLE
                //耕保空间
                val intList = arrayOfNulls<Int>(skt.size)
                val tdqsDatas = ArrayList<TextViewsEntity>()
                for (i in skt.indices){
                    val get = skt.get(i)
                    intList[i] = i
                    tdqsDatas.add(TextViewsEntity(get.getName(), get.getArea().stripTrailingZeros().toPlainString()+"㎡"))
                }
                tll_act_tjfx_detail_gbkj.setValueTexts(tdqsDatas, intList)
            }
            val cjEntity = renovated.getCjEntity()
            if (cjEntity!=null){
                ll1_act_tjfx_detail_zcfz.visibility = View.VISIBLE
                //非宅
                val fzDatas = java.util.ArrayList<TextViewsEntity>()
                fzDatas.add(TextViewsEntity("经营性收益（前三项收入之和）:", "${cjEntity.jyxsy.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("主营业务收入:", "${cjEntity.zyywsr.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("其他业务收入:", "${cjEntity.qtywsr.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("投资收益:", "${cjEntity.tzsy.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("营业外收入:", "${cjEntity.yywsr.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("资产总计:", "${cjEntity.zczj.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("负债合计:", "${cjEntity.fzhj.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("所有者权益合计:", "${cjEntity.syzqyhj.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("人均所得:", "${cjEntity.rjsd.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("汇总农户总人口:", "${cjEntity.hznhzrk.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("劳动力总人数:", "${cjEntity.ldlzrs.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("已就业劳动力:", "${cjEntity.yjyldlrs.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("第一产业从业人数:", "${cjEntity.dycycyrs.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("第二产业从业人数:", "${cjEntity.decycyrs.stripTrailingZeros().toPlainString()}"))
                fzDatas.add(TextViewsEntity("第三产业从业人数:", "${cjEntity.dscycyrs.stripTrailingZeros().toPlainString()}"))
                tll_act_tjfx_detail_zcfz.setValueTexts(fzDatas, arrayOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16))
            }
        }
    }

    override fun returnFanJianDetail(renovated: Renovated) {
        
    }

    override fun returnSysXzqQueryXzqList(renovated: ArrayList<SysXzqEntity>) {
        
    }

    override fun returnSysXzqList(renovated: List<layerListBean>) {
        
    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

}
