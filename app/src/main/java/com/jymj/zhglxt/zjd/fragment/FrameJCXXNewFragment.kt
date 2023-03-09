/*
package com.jymj.newhczglxy.zjd.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcodecraeer.xrecyclerview.XRecyclerView

import com.jymj.newhczglxy.R
import com.jymj.newhczglxy.api.ApiConstants
import com.jymj.newhczglxy.bean.PageUtils
import com.jymj.newhczglxy.ldrkgl.base.BaseNet
import com.jymj.newhczglxy.ldrkgl.home.activity.LdrkDetailActivity
import com.jymj.newhczglxy.rjhj.bean.yl.FloatPopulat
import com.jymj.newhczglxy.rjhj.bean.yl.LdrkPointBean
import com.jymj.newhczglxy.util.*
import com.jymj.newhczglxy.zjd.activity.FrameYewuDeteil
import com.jymj.newhczglxy.zjd.activity.zjdgl.FrameMXActivity
import com.jymj.newhczglxy.zjd.adapter.*
import com.jymj.newhczglxy.zjd.bean.CJBarChartBean
import com.jymj.newhczglxy.zjd.bean.FrameJcxxBean
import com.jymj.newhczglxy.zjd.bean.YeWuFrameBean
import com.jymj.newhczglxy.zjd.bean.yzt.frame_mx.*
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.AppUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.fragment_frame_jcxxnew.*
import org.json.JSONObject
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

*/
/**
 * A simple [Fragment] subclass.
 *
 *//*

class FrameJCXXNewFragment : Fragment() {
    var detailEntity: FrameJcxxBean.DataBean? = null
    var jbqk_flag: Int = 0
    var fwxx_flag: Int = 0
    var txxx_flag: Int = 0
    var tgxx_flag: Int = 0
    var cgxx_flag: Int = 0
    var fzxx_flag: Int = 0
    var zysy_flag: Int = 0
    var ldrk_flag: Int = 0

    var zjdflFlag: Int = 0
    var hbjcFlag: Int = 0
    var zjdxcFlag: Int = 0
    var tdxcFlag: Int = 0
    var entityList: YeWuFrameBean.DataBean? = null
    var sbs: String = ""
    var page = 1
    var limit = 20

    var xrl_frame_rkmx: XRecyclerView? = null
    var xrl_frame_ylmx: XRecyclerView? = null
    var xrl_frame_txmx: XRecyclerView? = null
    var xrl_frame_tgmx: XRecyclerView? = null
    var xrl_frame_fzmx: XRecyclerView? = null
    var xrl_frame_ldrkmx: XRecyclerView? = null
    private var mRkmxPopu: CommenPop? = null
    private var mYlmxPopu: CommenPop? = null
    private var mTxmxPopu: CommenPop? = null
    private var mTgmxPopu: CommenPop? = null
    private var mFzmxPopu: CommenPop? = null
    private var mLdrkmxPopu: CommenPop? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frame_jcxxnew, container, false)
    }
    public fun getScrollView():ScrollView{
        return kxxyjbg_scrollView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        frame_jcxxnew_jbqk_iv.setOnClickListener {
            if (jbqk_flag == 0) {
                frame_jcxxnew_jbqk_ll.visibility = View.GONE
                jbqk_flag = 1
            } else {
                frame_jcxxnew_jbqk_ll.visibility = View.VISIBLE
                jbqk_flag = 0
            }
        }
        frame_jcxxnew_fwxx_iv.setOnClickListener {
            if (fwxx_flag == 0) {
                frame_jcxxnew_fwxx_ll.visibility = View.GONE
                fwxx_flag = 1
            } else {
                frame_jcxxnew_fwxx_ll.visibility = View.VISIBLE
                fwxx_flag = 0
            }
        }
        frame_jcxxnew_txxx_iv.setOnClickListener {
            if (txxx_flag == 0) {
                frame_jcxxnew_txxx_ll.visibility = View.GONE
                txxx_flag = 1
            } else {
                frame_jcxxnew_txxx_ll.visibility = View.VISIBLE
                txxx_flag = 0
            }
        }
        frame_jcxxnew_tgxx_iv.setOnClickListener {
            if (tgxx_flag == 0) {
                frame_jcxxnew_tgxx_ll.visibility = View.GONE
                tgxx_flag = 1
            } else {
                frame_jcxxnew_tgxx_ll.visibility = View.VISIBLE
                tgxx_flag = 0
            }
        }
        frame_jcxxnew_fzxx_iv.setOnClickListener {
            if (fzxx_flag == 0) {
                frame_jcxxnew_fzxx_ll.visibility = View.GONE
                fzxx_flag = 1
            } else {
                frame_jcxxnew_fzxx_ll.visibility = View.VISIBLE
                fzxx_flag = 0
            }
        }

        frame_jcxxnew_cgxx_iv.setOnClickListener {
            if (cgxx_flag == 0) {
                frame_cgxx_zwxx.visibility = View.GONE
                cgxx_flag = 1
            } else {
                frame_cgxx_zwxx.visibility = View.VISIBLE
                cgxx_flag = 0
            }
        }
        frame_jcxxnew_zysy_iv.setOnClickListener {
            if (zysy_flag == 0) {
                frame_jcxxnew_zysy_ll.visibility = View.GONE
                zysy_flag = 1
            } else {
                frame_jcxxnew_zysy_ll.visibility = View.VISIBLE
                zysy_flag = 0
            }
        }
        frame_jcxxnew_ldrk_iv.setOnClickListener {
            if (ldrk_flag == 0) {
                frame_jcxxnew_ldrk_ll.visibility = View.GONE
                ldrk_flag = 1
            } else {
                frame_jcxxnew_ldrk_ll.visibility = View.VISIBLE
                ldrk_flag = 0
            }
        }


        //业务
        frame_jcxxnew_zjdgl_iv.setOnClickListener {
            if (zjdflFlag == 0) {
                frame_jcxxnew_zjdgl_ll.visibility = View.GONE
                zjdflFlag = 1
            } else {
                frame_jcxxnew_zjdgl_ll.visibility = View.VISIBLE
                zjdflFlag = 0
            }

        }
        frame_jcxxnew_hbjc_iv.setOnClickListener {
            if (hbjcFlag == 0) {
                frame_hbjc_ll1.visibility = View.GONE
                hbjcFlag = 1
            } else {
                frame_hbjc_ll1.visibility = View.VISIBLE
                hbjcFlag = 0
            }
        }
        frame_jcxxnew_zjdxc_iv.setOnClickListener {
            if (zjdxcFlag == 0) {
                frame_zjdxc_ll1.visibility = View.GONE
                zjdxcFlag = 1
            } else {
                frame_zjdxc_ll1.visibility = View.VISIBLE
                zjdxcFlag = 0
            }
        }
        frame_jcxxnew_tdxc_iv.setOnClickListener {
            if (tdxcFlag == 0) {
                frame_tdxc_l2.visibility = View.GONE
                tdxcFlag = 1
            } else {
                frame_tdxc_l2.visibility = View.VISIBLE
                tdxcFlag = 0
            }
        }

        frame_jcxxnew_zjdgl_sp.setOnClickListener {
            if (entityList!!.sqzjd.ongoingCount > 0 || entityList!!.sqzjd.completeCount > 0 || entityList!!.sqzjd.rejectedCount > 0) {
                var intent = Intent(activity, FrameYewuDeteil::class.java)
                intent.putExtra("frameFlag", 1)
                intent.putExtra("framePoint", sbs)
                startActivity(intent)
            }
        }
        frame_jcxxnew_zjdgl_fj.setOnClickListener {
            if (entityList!!.fjzjd.ongoingCount > 0 || entityList!!.fjzjd.completeCount > 0 || entityList!!.fjzjd.rejectedCount > 0) {
                var intent = Intent(activity, FrameYewuDeteil::class.java)
                intent.putExtra("frameFlag", 2)
                intent.putExtra("framePoint", sbs)
                startActivity(intent)
            }
        }
        frame_jcxxnew_zjdgl_lz.setOnClickListener {
            if (entityList!!.lzzjd.ongoingCount > 0 || entityList!!.lzzjd.completeCount > 0 || entityList!!.lzzjd.rejectedCount > 0) {
                var intent = Intent(activity, FrameYewuDeteil::class.java)
                intent.putExtra("frameFlag", 3)
                intent.putExtra("framePoint", sbs)
                startActivity(intent)
            }
        }
        frame_jcxxnew_zjdgl_tc.setOnClickListener {
            if (entityList!!.tczjd.ongoingCount > 0 || entityList!!.tczjd.completeCount > 0 || entityList!!.tczjd.rejectedCount > 0) {
                var intent = Intent(activity, FrameYewuDeteil::class.java)
                intent.putExtra("frameFlag", 4)
                intent.putExtra("framePoint", sbs)
                startActivity(intent)
            }
        }
        frame_hbjc_ll1.setOnClickListener {
            if (entityList!!.envior.ongoingCount > 0 || entityList!!.envior.completeCount > 0) {
                var intent = Intent(activity, FrameYewuDeteil::class.java)
                intent.putExtra("frameFlag", 5)
                intent.putExtra("framePoint", sbs)
                startActivity(intent)
            }
        }
        frame_zjdxc_ll1.setOnClickListener {
            if (entityList!!.envior.ongoingCount > 0 || entityList!!.envior.completeCount > 0) {
                var intent = Intent(activity, FrameYewuDeteil::class.java)
                intent.putExtra("frameFlag", 6)
                intent.putExtra("framePoint", sbs)
                startActivity(intent)
            }
        }
        frame_tdxc_l2.setOnClickListener {
            if (entityList!!.envior.ongoingCount > 0 || entityList!!.envior.completeCount > 0) {
                var intent = Intent(activity, FrameYewuDeteil::class.java)
                intent.putExtra("frameFlag", 7)
                intent.putExtra("framePoint", sbs)
                startActivity(intent)
            }
        }

        //明细
        //基础信息（人口）
        frame_jcxxnew_jbqk_mx.setOnClickListener {
            if (sbs != null) {
                limit = 20
                getRKMXData(sbs, 1, limit)
            }
        }
        //房屋明细(院落)
        frame_jcxxnew_fwxx_mx.setOnClickListener {
            if (sbs != null) {
                limit = 20
                //getYLMXData(sbs, 1, limit)
                val intent = Intent(activity,FrameMXActivity::class.java)
                intent.putExtra("framemxFlag","fwmx")
                intent.putExtra("framemxSbs",sbs)
                startActivity(intent)
            }
        }
        //土现明细
        frame_jcxxnew_txxx_mx.setOnClickListener {
            if (sbs != null) {
                limit = 20
                getTXMXData(sbs, 1, limit)
                */
/*val intent = Intent(activity,FrameMXActivity::class.java)
                intent.putExtra("framemxFlag","txmx")
                intent.putExtra("framemxSbs",sbs)
                startActivity(intent)*//*

            }
        }
        //土规明细
        frame_jcxxnew_tgxx_mx.setOnClickListener {
            if (sbs != null) {
                limit = 20
                //getTGMXData(sbs, 0, limit)
                val intent = Intent(activity,FrameMXActivity::class.java)
                intent.putExtra("framemxFlag","tgmx")
                intent.putExtra("framemxSbs",sbs)
                startActivity(intent)
            }
        }
        //非宅明细
        frame_jcxxnew_fzxx_mx.setOnClickListener {
            if (sbs != null) {
                limit = 20
                //getFZMXData(sbs, 1, limit)
                val intent = Intent(activity,FrameMXActivity::class.java)
                intent.putExtra("framemxFlag","fzmx")
                intent.putExtra("framemxSbs",sbs)
                startActivity(intent)
            }
        }
        frame_jcxxnew_cgxx_mx.setOnClickListener {
            ToastUtils.showShort("暂无明细")
        }
        frame_jcxxnew_zysy_mx.setOnClickListener {
            ToastUtils.showShort("暂无明细")
        }
        frame_jcxxnew_ldrk_mx.setOnClickListener {
            if (sbs != null) {
                limit = 20
                getLDRKMXData(sbs, 1, limit)
            }
        }


        var simpleDateFormat = SimpleDateFormat("yyyy.MM.dd");// HH:mm:ss
//获取当前时间
        var date = Date(System.currentTimeMillis());
//        time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
        tv_txxyjbg_time.setText("生成日期 " + simpleDateFormat.format(date))

        iv_kxxyjbg_ml.setOnClickListener {
            kxxyjbg_drawer_layout.openDrawer(Gravity.LEFT)
        }
        var data = ArrayList<String>()
        data.add("一、基础信息")
        data.add("  1.基本情况")
        data.add("  2.房屋信息")
        data.add("  3.土现信息")
        data.add("  4.土规信息")
        data.add("  5.非宅信息")
        data.add("  6.城规信息")
        data.add("  7.资源使用")
        data.add("  8.流动人口")
        data.add("二、业务信息")
        data.add("  1.宅基地管理信息")
        data.add("  2.环保监察信息")
        data.add("  3.土地巡查信息")
        rlv_kxxyjbg_ml.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_kxxyjbg_ml.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_tdkybg_text, data) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                val textTitle = helper!!.getView<TextView>(R.id.tdkybg_text_title)

                if (item!!.contains("不利因素")) {
                    textTitle.visibility = View.GONE
                } else if (item!!.contains(".")) {
                    textTitle.setPadding(100, 0, 0, 0)
                    textTitle.layoutParams
                } */
/*else if (item.contains("不利因素")) {
                    textTitle.visibility = View.GONE
                }*//*

                val text = helper!!.setText(R.id.tdkybg_text_title, item)
                val view = helper.getView<TextView>(R.id.tdkybg_text_title)
                view.setOnClickListener {
                    val field = R.id::class.java.getField("frame_scrool" + helper.layoutPosition)
                    val textView = activity!!.findViewById<LinearLayout>((field.getInt(null)))
                    val top = textView.top
                    kxxyjbg_scrollView.smoothScrollTo(0, top)
                    kxxyjbg_drawer_layout.closeDrawer(Gravity.LEFT)
                }
            }
        }

        //初始化
        //人口明细弹框
        mRkmxPopu = CommenPop.getNormalPopu(activity, R.layout.pop_frame_rkmx, kxxyjbg_drawer_layout)
        val mRkmxView = mRkmxPopu?.contentView
        xrl_frame_rkmx = mRkmxView?.findViewById<XRecyclerView>(R.id.xrl_frame_rkmx)
        //院落明细弹框
        mYlmxPopu = CommenPop.getNormalPopu(activity, R.layout.pop_frame_ylmx, kxxyjbg_drawer_layout)
        val mYlmxView = mYlmxPopu?.contentView
        xrl_frame_ylmx = mYlmxView?.findViewById<XRecyclerView>(R.id.xrl_frame_ylmx)

        //土现明细弹框
        mTxmxPopu = CommenPop.getNormalPopu(activity, R.layout.pop_frame_txmx, kxxyjbg_drawer_layout)
        val mTxmxView = mTxmxPopu?.contentView
        xrl_frame_txmx = mTxmxView?.findViewById<XRecyclerView>(R.id.xrl_frame_txmx)

        //土规明细弹框
        mTgmxPopu = CommenPop.getNormalPopu(activity, R.layout.pop_frame_tgmx, kxxyjbg_drawer_layout)
        val mTgmxView = mTgmxPopu?.contentView
        xrl_frame_tgmx = mTgmxView?.findViewById<XRecyclerView>(R.id.xrl_frame_tgmx)

        //非宅明细弹框
        mFzmxPopu = CommenPop.getNormalPopu(activity, R.layout.pop_frame_fzmx, kxxyjbg_drawer_layout)
        val mFzmxView = mFzmxPopu?.contentView
        xrl_frame_fzmx = mFzmxView?.findViewById<XRecyclerView>(R.id.xrl_frame_fzmx)

        //流动人口明细弹框
        mLdrkmxPopu = CommenPop.getNormalPopu(activity, R.layout.pop_frame_ldrkmx, kxxyjbg_drawer_layout)
        val mLdrkmxView = mLdrkmxPopu?.contentView
        xrl_frame_ldrkmx = mLdrkmxView?.findViewById<XRecyclerView>(R.id.xrl_frame_ldrkmx)
    }

    fun frameData(data: FrameJcxxBean.DataBean) {
        detailEntity = data
        var cunList: String = ""
        if (detailEntity != null) {
            if (detailEntity!!.ylEntityList != null) {
                for (data in detailEntity!!.ylEntityList) {
                    cunList = cunList + data.xzqmc + "、"
                }

                frame_jcxxnew_sjc.text = cunList.replace("总计、", "") //涉及村
                frame_jcxxnew_zjdzs.text = "" + detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).zjdcount + "宗"//宅基地宗数
                if (detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).jianzhuArea.compareTo(BigDecimal(10000)) > 0) {
                    frame_jcxxnew_zjdjzmj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).jianzhuArea.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡"
                } else {
                    frame_jcxxnew_zjdjzmj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).jianzhuArea.toString() + "㎡"
                }
                //frame_jcxxnew_zjdjzmj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).jianzhuArea.toString() + "㎡"//建筑面积
                if (detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).area.compareTo(BigDecimal(10000)) > 0) {
                    frame_jcxxnew_zjdzdmj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).area.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡"
                } else {
                    frame_jcxxnew_zjdzdmj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).area.toString() + "㎡"
                }

//                frame_jcxxnew_zjdzdmj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).area.toString() + "㎡"//占地面积
                frame_jcxxnew_zrks.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).rk.toString() + "人"//总人口数
                frame_jcxxnew_zhs.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).hucount.toString() + "户"//总户数
                frame_jcxxnew_rjzjdzmj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).hjmj.toString() + "㎡"//户均宅基地总面积
                frame_jcxxnew_ldrk_rk.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).ldrks.toString() + "人"//流动人口
                frame_jcxxnew_ldrk_mj.text = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).ldArea.toString() + "㎡"//流动面积

                //户籍人口饼状图
                val ncount = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).nongcount
                val feincount = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).feinongcount
                val count = java.util.ArrayList<Float>()
                val countName = java.util.ArrayList<String>()
                count.add(ncount.toFloat())
                count.add(feincount.toFloat())
                countName.add("农业人口")
                countName.add("非农业人口")
                rkinitPie(frame_nfn_pct, "户籍人口比", count, countName)
                if (ncount == 0 && feincount == 0){
                    frame_jcxxnew_jbqk_ll.visibility = View.GONE
                    jbqk_flag = -1
                }else{
                    frame_jcxxnew_jbqk_ll.visibility = View.VISIBLE
                    jbqk_flag = 0
                }
                //男女饼状图

                //房屋柱状图
                if (detailEntity!!.keyValueEntity != null) {
                    FWBar(detailEntity!!.keyValueEntity, frame_fwxx_bct)
                }
                //房屋信息
                if (detailEntity!!.ylEntityList != null) {
                    val fdq4count = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq4
                    val fdq46count = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq46
                    val fdq68count = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq68
                    val fdq8count = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq8
                    val count = java.util.ArrayList<Float>()
                    val countName = java.util.ArrayList<String>()
                    count.add(fdq4count.toFloat())
                    count.add(fdq46count.toFloat())
                    count.add(fdq68count.toFloat())
                    count.add(fdq8count.toFloat())
                    countName.add("四分及以下")
                    countName.add("四分-六分")
                    countName.add("六分-八分")
                    countName.add("八分及以上")
                    //房屋饼状图
                    rkinitPie(frame_fwxx_pct, "宅基地面积宗数占比", count, countName)
                    if (fdq4count.compareTo(BigDecimal(0)) == 0 && fdq46count.compareTo(BigDecimal(0)) == 0 &&fdq68count.compareTo(BigDecimal(0)) == 0 &&fdq8count.compareTo(BigDecimal(0)) == 0 ){
                        frame_jcxxnew_fwxx_ll.visibility = View.GONE
                        fwxx_flag = -1
                    }else{
                        frame_jcxxnew_fwxx_ll.visibility = View.VISIBLE
                        fwxx_flag = 0
                    }

                    val fdq4count1 = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq4count
                    val fdq46count1 = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq46count
                    val fdq68count1 = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq68count
                    val fdq8count1 = detailEntity!!.ylEntityList.get(detailEntity!!.ylEntityList.size - 1).fdq8count
                    val df = DecimalFormat("#.00")
                    frame_fwxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    frame_fwxx_rlv?.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.YlEntityListBean, BaseViewHolder>(R.layout.item_frame_fwxx_rlv, detailEntity!!.ylEntityList) {
                        override fun convert(helper: BaseViewHolder, item: FrameJcxxBean.DataBean.YlEntityListBean) {
                            val count = item.fdq4count + item.fdq46count + item.fdq68count + item.fdq8count
                            helper.setText(R.id.frame_fwxx_rlv_cm, item.xzqmc)
                                    .setText(R.id.frame_fwxx_rlv_4, "" + item.fdq4count)
                                    .setText(R.id.frame_fwxx_rlv_4zb, df.format(item.fdq4count.toDouble() / fdq4count1 * 100) + "%")
                                    .setText(R.id.frame_fwxx_rlv_46, "" + item.fdq46count)
                                    .setText(R.id.frame_fwxx_rlv_46zb, df.format(item.fdq46count.toDouble() / fdq46count1 * 100) + "%")
                                    .setText(R.id.frame_fwxx_rlv_68, "" + item.fdq68count)
                                    .setText(R.id.frame_fwxx_rlv_68zb, df.format(item.fdq68count.toDouble() / fdq68count1 * 100) + "%")
                                    .setText(R.id.frame_fwxx_rlv_8, "" + item.fdq8count)
                                    .setText(R.id.frame_fwxx_rlv_8zb, df.format(item.fdq8count.toDouble() / fdq8count1 * 100) + "%")
                                    .setText(R.id.frame_fwxx_rlv_zj, "" + count)
                        }
                    }
                }
                //土现信息
                if (detailEntity!!.tdlyEntities.size > 0) {
                    var dataList = ArrayList<FrameJcxxBean.DataBean.TdlyEntitiesBean.ChildrenBean>()
                    var areaCount: Double = 0.0
                    val count = java.util.ArrayList<Float>()
                    val countName = java.util.ArrayList<String>()
                    for (data in detailEntity!!.tdlyEntities) {
                        areaCount += data.area1.toFloat()
                        count.add(data.area1.toFloat())
                        countName.add(data.lx)
                        if (data.children != null && data.children.size > 0) {
                            for (child in data.children) {
                                dataList.add(child)
                            }
                        }
                    }
                    TXXXBar(dataList, frame_txxx_bct)
                    rkinitPie(frame_txxx_pct, "一级分类占比统计图", count, countName)
                    val df = DecimalFormat("#.00")
                    frame_txxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    frame_txxx_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.TdlyEntitiesBean, BaseViewHolder>(R.layout.item_tdlyxzdet, detailEntity!!.tdlyEntities) {
                        override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.TdlyEntitiesBean?) {
                            */
/*if (item!!.lx.equals("总计")) {

                                if (areaCount > 10000.0) {
                                    helper!!.setText(R.id.tdlyxzdet_hj, (df.format(areaCount / 10000.0)).toString() + "万㎡")
                                } else {
                                    helper!!.setText(R.id.tdlyxzdet_hj, areaCount.toString() + "㎡")
                                }
                            } else {*//*

                                if (item!!.area1.compareTo(BigDecimal(10000)) > 0) {
                                    helper!!.setText(R.id.tdlyxzdet_hj, item.area1.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡")
                                } else {
                                    helper!!.setText(R.id.tdlyxzdet_hj, item.area1.toString() + "㎡")
                                }
                            //}
                            helper!!.setText(R.id.tdlyxzdet_one, item!!.lx)
                            val two_rlv = helper.getView<RecyclerView>(R.id.tdlyxzdet_two_rlv)


                            if (item.children != null && item.children.size > 0) {
                                //设置item高度
                                val count = item.children.size
                                //helper.getView<TextView>(R.id.tdlyxzdet_one).height = 300*count
                                val one_tv = helper.getView<TextView>(R.id.tdlyxzdet_one).getLayoutParams() as LinearLayout.LayoutParams
                                helper.getView<TextView>(R.id.tdlyxzdet_one).post(Runnable {

                                    one_tv.height = helper.getView<TextView>(R.id.tdlyxzdet_one).measuredHeight * count
                                    helper.getView<TextView>(R.id.tdlyxzdet_one).layoutParams = one_tv
                                })

                                val hj_tv = helper.getView<TextView>(R.id.tdlyxzdet_hj).getLayoutParams() as LinearLayout.LayoutParams
                                helper.getView<TextView>(R.id.tdlyxzdet_hj).post(Runnable {
                                    hj_tv.height = helper.getView<TextView>(R.id.tdlyxzdet_hj).measuredHeight * count
                                    helper.getView<TextView>(R.id.tdlyxzdet_hj).layoutParams = hj_tv
                                })
                            }
                            two_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            two_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.TdlyEntitiesBean.ChildrenBean, BaseViewHolder>(R.layout.item_tdlyxzdet_two, item.children) {
                                override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.TdlyEntitiesBean.ChildrenBean?) {
                                    helper!!.setText(R.id.tdlyxzdet_two, item!!.elx)
                                    //helper!!.setText(R.id.tdlyxzdet_two2, item!!.area.toString())
                                    if (item!!.area1.compareTo(BigDecimal(10000)) > 0) {
                                        helper!!.setText(R.id.tdlyxzdet_two2, item.area1.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡")
                                    } else {
                                        helper!!.setText(R.id.tdlyxzdet_two2, item.area1.toString() + "㎡")
                                    }
                                }

                            }
                        }

                    }

                }
                //土规信息
                //柱状图
                if (detailEntity!!.tgEntityList.size > 0) {
                    TGXXBar(detailEntity!!.tgEntityList, frame_tgxx_bct)
                }
                val df = DecimalFormat("#.00")
                //土规列表
                if (detailEntity!!.tgEntities.size > 0) {
                    frame_tgxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    frame_tgxx_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.TgFhEntity, BaseViewHolder>(R.layout.item_tgxx_rlv, detailEntity!!.tgEntities) {
                        override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.TgFhEntity?) {
                            var areaCount: Double = 0.0

                            if (item!!.tgEntities != null && item.tgEntities.size > 0) {
                                for (data2 in item.tgEntities) {
                                    areaCount += data2.area1.toFloat()
                                }
                            }

                            helper!!.setText(R.id.tgxx_rlv_one, item!!.proj)
                            if (areaCount > 10000.0) {
                                helper!!.setText(R.id.tgxx_rlv_one2, df.format((areaCount / 10000.0)).toString() + "万㎡")
                            } else {
                                helper!!.setText(R.id.tgxx_rlv_one2, areaCount.toString() + "㎡")
                            }

                            val two_rlv = helper.getView<RecyclerView>(R.id.tgxx_two_rlv)
                            if (item.tgEntities != null && item.tgEntities.size > 0) {
                                //设置item高度
                                val count = item.tgEntities.size
                                //helper.getView<TextView>(R.id.tdlyxzdet_one).height = 300*count
                                val one_tv = helper.getView<TextView>(R.id.tgxx_rlv_one).getLayoutParams() as LinearLayout.LayoutParams
                                helper.getView<TextView>(R.id.tgxx_rlv_one).post(Runnable {

                                    one_tv.height = helper.getView<TextView>(R.id.tgxx_rlv_one).measuredHeight * count
                                    helper.getView<TextView>(R.id.tgxx_rlv_one).layoutParams = one_tv
                                })
                                val one2_tv = helper.getView<TextView>(R.id.tgxx_rlv_one2).getLayoutParams() as LinearLayout.LayoutParams
                                helper.getView<TextView>(R.id.tgxx_rlv_one2).post(Runnable {

                                    one2_tv.height = helper.getView<TextView>(R.id.tgxx_rlv_one2).measuredHeight * count
                                    helper.getView<TextView>(R.id.tgxx_rlv_one2).layoutParams = one2_tv
                                })
                            }

                            two_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            two_rlv.adapter = object : BaseQuickAdapter<TgEntity, BaseViewHolder>(R.layout.item_tgxx_rlv_two, item.tgEntities) {
                                override fun convert(helper: BaseViewHolder?, item: TgEntity?) {
                                    helper!!.setText(R.id.tgxx_rlv_two, item!!.landName)
                                    if (item!!.area1.compareTo(BigDecimal(10000)) > 0) {
                                        helper!!.setText(R.id.tgxx_rlv_two2, item.area1.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡")
                                    } else {
                                        helper!!.setText(R.id.tgxx_rlv_two2, item.area1.toString() + "㎡")
                                    }
                                }

                            }
                        }

                    }
                }
                //非宅信息
                if (detailEntity!!.feizhai.size > 0) {
                    FZBar(detailEntity!!.feizhai, frame_fzxx_bct)

                    frame_fzxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    frame_fzxx_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.FeizhaiBean, BaseViewHolder>(R.layout.item_fzxx_rlv, detailEntity!!.feizhai) {
                        override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.FeizhaiBean?) {
                            if (item!!.object1.compareTo(BigDecimal(10000)) > 0) {
                                helper!!.setText(R.id.item_fzxx_zdmj, item.object1.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡")
                            } else {
                                helper!!.setText(R.id.item_fzxx_zdmj, item.object1.setScale(2,RoundingMode.HALF_UP).toString() + "㎡")
                            }

                            if (item.object2.compareTo(BigDecimal(10000)) > 0) {
                                helper.setText(R.id.item_fzxx_jzmj, item.object2.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡")
                            } else {
                                helper.setText(R.id.item_fzxx_jzmj, item.object2.setScale(2,RoundingMode.HALF_UP).toString() + "㎡")
                            }
                            helper.setText(R.id.item_fzxx_hylx, item.hylxText)
                                    .setText(R.id.item_fzxx_sl, item.object3.toString())

                        }

                    }

                }


                //资源使用
                if (detailEntity!!.zyjz != null) {
                    val count = java.util.ArrayList<Float>()
                    val countName = java.util.ArrayList<String>()
                    //饼状图
                    for (data in detailEntity!!.zyjz) {
                        count.add(data.object4.toFloat())
                        countName.add(data.object2)

                    }
                    rkinitPie(frame_zysy_pct, "土地资源占比", count, countName)
                    frame_zysy_rlv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    frame_zysy_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.ZyjzBean, BaseViewHolder>(R.layout.item_frame_zysy, detailEntity!!.zyjz) {
                        override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.ZyjzBean?) {
                            helper!!.setText(R.id.item_frame_zysy_lx,item!!.object2)
                                    .setText(R.id.item_frame_zysy_zb,item.object3.toString()+"%")
                            if (item.object4 > 10000.0) {
                                helper.setText(R.id.item_frame_zysy_area,df.format((item.object4 / 10000.0)).toString() + "万㎡")
                            } else {
                                helper.setText(R.id.item_frame_zysy_area,item.object4.toString() + "㎡")
                            }

                        }
                    }
                }

                //流动人口
                if (detailEntity!!.floatFhEntities != null){
                    val count = java.util.ArrayList<Float>()
                    val countName = java.util.ArrayList<String>()
                    //饼状图
                    for (data in detailEntity!!.floatFhEntities) {
                        count.add(data.cszy.toFloat())
                        countName.add(data.cszyText)

                    }
                    rkinitPie(frame_ldrk_pct, "流动人口占比", count, countName)

                }
                if (detailEntity!!.ldrk.size > 0){
                    frame_ldrk_rlv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    frame_ldrk_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.LdrkBeans, BaseViewHolder>(R.layout.item_frame_zysy, detailEntity!!.ldrk) {
                        override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.LdrkBeans?) {
                            val object4 = item!!.object4

                            helper!!.setText(R.id.item_frame_zysy_lx,item!!.object1)
                                    .setText(R.id.item_frame_zysy_zb,item.object2.toString())
                                .setText(R.id.item_frame_zysy_area,object4.multiply(BigDecimal(100)).setScale(1,BigDecimal.ROUND_HALF_UP).toString() + "%")


                        }
                    }
                }
            }
        }
    }

    //人口饼状图
    private fun rkinitPie(pie: PieChart, title: String, datas: java.util.ArrayList<Float>, lables: java.util.ArrayList<String>) {
        pie.setUsePercentValues(false)
        pie.description.isEnabled = false
        pie.setExtraOffsets(5f, 10f, 5f, 5f)

        pie.dragDecelerationFrictionCoef = 0.95f

        pie.setExtraOffsets(20f, 0f, 20f, 0f)

        pie.isDrawHoleEnabled = true
        pie.setHoleColor(Color.WHITE)

        pie.setTransparentCircleColor(Color.WHITE)
        pie.setTransparentCircleAlpha(110)

        pie.holeRadius = 58f
        pie.transparentCircleRadius = 61f

        pie.setDrawCenterText(true)

        pie.centerText = title
        pie.setCenterTextSize(20f)
        pie.rotationAngle = 0f
        // enable rotation of the chart by touch
        pie.isRotationEnabled = true
        pie.isHighlightPerTapEnabled = true
        pie.setEntryLabelColor(Color.BLACK)
        setRKData(pie, datas, lables)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    private fun setRKData(pie: PieChart, datas: List<Float>, lables: List<String>) {
        val entries = java.util.ArrayList<PieEntry>()

        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = java.util.ArrayList<Int>()
        colors.add(Color.argb(199, 1, 113, 209))
        colors.add(Color.argb(199, 201, 85, 1))
        colors.add(Color.argb(199, 164, 1, 1))
        colors.add(Color.argb(199, 1, 53, 103))
        colors.add(Color.argb(199, 1, 95, 21))
        */
/*for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())*//*


        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);


        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data

        // undo all highlights
        pie.highlightValues(null)

        pie.invalidate()
    }

    //房屋柱状图
    private fun FWBar(list: FrameJcxxBean.DataBean.KeyValueEntityBean, barChart: BarChart) {

        val zhzdmj = list.object1
        val zhjzmj = list.object5

        val feizdmj = list.object2
        val feijzmj = list.object6

        val gyzdmj = list.object3
        val gyjzmj = list.object7

        val gysszdmj = list.object4
        val gyssjzmj = list.object8

        */
/*   val ssnyzdmj = list.ssnyzdmj
           val ssnyjzmj = list.ssnyjzmj*//*


        val listBean = ArrayList<CJBarChartBean>()

        val zjdbar = CJBarChartBean(zhzdmj, zhjzmj, "宅基地");
        val fzjdbar = CJBarChartBean(feizdmj, feijzmj, "非宅");
        val gybar = CJBarChartBean(gyzdmj, gyjzmj, "国有");
        val gyssbar = CJBarChartBean(gysszdmj, gyssjzmj, "公益设施");
//        val ssnybar = CJBarChartBean(ssnyzdmj, ssnyjzmj, "设施农业");

        listBean.add(zjdbar)
        listBean.add(fzjdbar)
        listBean.add(gybar)
        listBean.add(gyssbar)
//        listBean.add(ssnybar)

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelRotationAngle = 50f
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelCount = listBean.size
        xAxis.setCenterAxisLabels(true)//设置标签居中


        xAxis.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                return if (index < 0 || index >= listBean.size) {
                    ""
                } else {
                    listBean.get(index).name
                }

            }
        })

        val description = barChart.getDescription()
        description.setText("")
        barChart.setDescription(description)
        // y 数据
        val yValues = java.util.ArrayList<BarEntry>()
        // y2 数据
        val yValues2 = java.util.ArrayList<BarEntry>()
        // y3 数据
        val yValues3 = java.util.ArrayList<BarEntry>()

        for (x in listBean.indices) {
            val zdmj = listBean.get(x).zdmj
            yValues.add(BarEntry(x.toFloat() + 0.5f, zdmj.toFloat()))

            val jzmj = listBean.get(x).jzmj
            yValues2.add(BarEntry(x.toFloat(), jzmj.toFloat()))

            */
/*  val area3 = message.get(x).list.get(3).area
              yValues3.add(BarEntry(x.toFloat(),area3.toFloat()))*//*


        }

        // y 轴数据集
        val barDataSet = BarDataSet(yValues, "占地面积(亩)")
        barDataSet.color = Color.parseColor("#36A3DB")

        // y2 轴数据集
        val barDataSet2 = BarDataSet(yValues2, "建筑面积(亩)")
        barDataSet2.color = Color.parseColor("#FFDB5C")

        // y3 轴数据集
        val barDataSet3 = BarDataSet(yValues3, "宅基地")
        barDataSet3.color = Color.parseColor("#FFDB5C")

        val mBarData = BarData(barDataSet, barDataSet2)
        val groupSpace = 0.04f
        val barSpace = 0.03f
        val barWidth = 0.45f
        // 设置 柱子宽度
        mBarData!!.barWidth = barWidth
        barChart.data = mBarData
        barChart.groupBars(0.0f, groupSpace, barSpace)
        barChart.invalidate()
        val mMatrix = Matrix()
        mMatrix.postScale(1f, 1f)
        barChart.viewPortHandler.refresh(mMatrix, barChart, false)
        barChart.animateY(800)
        barChart!!.setScaleEnabled(false)//启用/禁用两个轴上的图表缩放
        barChart!!.setScaleXEnabled(false)//启用/禁用x轴缩放
        barChart!!.setScaleYEnabled(false)//启用/禁用y轴缩放
        barChart!!.setPinchZoom(false)//如果设置为true，则启用缩放缩放。如果禁用，则可以单独缩放x轴和y轴
        barChart!!.setDoubleTapToZoomEnabled(false)//
        xAxis.axisMinimum = -0.5f
        xAxis.axisMaximum = barChart!!.getData().getXMax() + 1f
    }

    //土现柱状图
    private fun TXXXBar(message: ArrayList<FrameJcxxBean.DataBean.TdlyEntitiesBean.ChildrenBean>, barChart: BarChart) {

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelRotationAngle = 50f
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelCount = message.size
        xAxis.setCenterAxisLabels(true)//设置标签居中

        xAxis.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                return if (index < 0 || index >= message.size) {
                    ""
                } else {
                    message.get(index).elx
                }

            }
        })

        val description = barChart.getDescription()
        description.setText("")
        barChart.setDescription(description)
        // y 数据
        val yValues = java.util.ArrayList<BarEntry>()
        // y2 数据
        val yValues2 = java.util.ArrayList<BarEntry>()
        // y3 数据
        val yValues3 = java.util.ArrayList<BarEntry>()

        for (x in message.indices) {
            val area1 = message.get(x).area1
            yValues.add(BarEntry(x.toFloat() + 0.5f, area1.toFloat()))

            */
/*val area2 = message.get(x).price
            yValues2.add(BarEntry(x.toFloat(), area2.toFloat()))*//*


            */
/* val area3 = message.get(x).list.get(3).area
             yValues3.add(BarEntry(x.toFloat(),area3.toFloat()))*//*


        }

        // y 轴数据集
        val barDataSet = BarDataSet(yValues, "面积(亩)")
        barDataSet.color = Color.parseColor("#36A3DB")

        // y2 轴数据集
        val barDataSet2 = BarDataSet(yValues2, "资金(万元)")
        barDataSet2.color = Color.parseColor("#FFDB5C")

        // y3 轴数据集
        val barDataSet3 = BarDataSet(yValues3, "宅基地")
        barDataSet3.color = Color.parseColor("#FFDB5C")

        val mBarData = BarData(barDataSet)
        val groupSpace = 0.04f
        val barSpace = 0.03f
        val barWidth = 0.45f
        // 设置 柱子宽度
        mBarData!!.barWidth = barWidth
        barChart.data = mBarData
//        barChart.groupBars(0.0f, groupSpace, barSpace)
        barChart.invalidate()
        val mMatrix = Matrix()
        mMatrix.postScale(1f, 1f)
        barChart.viewPortHandler.refresh(mMatrix, barChart, false)
        barChart.animateY(800)
        barChart!!.setScaleEnabled(false)//启用/禁用两个轴上的图表缩放
        barChart!!.setScaleXEnabled(false)//启用/禁用x轴缩放
        barChart!!.setScaleYEnabled(false)//启用/禁用y轴缩放
        barChart!!.setPinchZoom(false)//如果设置为true，则启用缩放缩放。如果禁用，则可以单独缩放x轴和y轴
        barChart!!.setDoubleTapToZoomEnabled(false)//
        xAxis.axisMinimum = -0.5f
        xAxis.axisMaximum = barChart!!.getData().getXMax() + 1f
    }

    //土规柱状图
    private fun TGXXBar(message: List<FrameJcxxBean.DataBean.TgEntityListBean>, barChart: BarChart) {

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelRotationAngle = 50f
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelCount = message.size
        xAxis.setCenterAxisLabels(true)//设置标签居中

        xAxis.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                return if (index < 0 || index >= message.size) {
                    ""
                } else {
                    message.get(index).landName
                }

            }
        })

        val description = barChart.getDescription()
        description.setText("")
        barChart.setDescription(description)
        // y 数据
        val yValues = java.util.ArrayList<BarEntry>()
        // y2 数据
        val yValues2 = java.util.ArrayList<BarEntry>()
        // y3 数据
        val yValues3 = java.util.ArrayList<BarEntry>()

        for (x in message.indices) {
            val area1 = message.get(x).area
            yValues.add(BarEntry(x.toFloat() + 0.5f, area1.toFloat()))

            */
/*val area2 = message.get(x).price
            yValues2.add(BarEntry(x.toFloat(), area2.toFloat()))*//*


            */
/* val area3 = message.get(x).list.get(3).area
             yValues3.add(BarEntry(x.toFloat(),area3.toFloat()))*//*


        }

        // y 轴数据集
        val barDataSet = BarDataSet(yValues, "面积(亩)")
        barDataSet.color = Color.parseColor("#36A3DB")

        // y2 轴数据集
        val barDataSet2 = BarDataSet(yValues2, "资金(万元)")
        barDataSet2.color = Color.parseColor("#FFDB5C")

        // y3 轴数据集
        val barDataSet3 = BarDataSet(yValues3, "宅基地")
        barDataSet3.color = Color.parseColor("#FFDB5C")

        val mBarData = BarData(barDataSet)
        val groupSpace = 0.04f
        val barSpace = 0.03f
        val barWidth = 0.45f
        // 设置 柱子宽度
        mBarData!!.barWidth = barWidth
        barChart.data = mBarData
//        barChart.groupBars(0.0f, groupSpace, barSpace)
        barChart.invalidate()
        val mMatrix = Matrix()
        mMatrix.postScale(1f, 1f)
        barChart.viewPortHandler.refresh(mMatrix, barChart, false)
        barChart.animateY(800)
        barChart!!.setScaleEnabled(false)//启用/禁用两个轴上的图表缩放
        barChart!!.setScaleXEnabled(false)//启用/禁用x轴缩放
        barChart!!.setScaleYEnabled(false)//启用/禁用y轴缩放
        barChart!!.setPinchZoom(false)//如果设置为true，则启用缩放缩放。如果禁用，则可以单独缩放x轴和y轴
        barChart!!.setDoubleTapToZoomEnabled(false)//
        xAxis.axisMinimum = -0.5f
        xAxis.axisMaximum = barChart!!.getData().getXMax() + 1f
    }

    //非宅柱状图
    private fun FZBar(list: List<FrameJcxxBean.DataBean.FeizhaiBean>, barChart: BarChart
    ) {

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelRotationAngle = 50f
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelCount = list.size
        xAxis.setCenterAxisLabels(true)//设置标签居中


        xAxis.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                return if (index < 0 || index >= list.size) {
                    ""
                } else {
                    list.get(index).hylxText
                }

            }
        })

        val description = barChart.getDescription()
        description.setText("")
        barChart.setDescription(description)
        // y 数据
        val yValues = java.util.ArrayList<BarEntry>()
        // y2 数据
        val yValues2 = java.util.ArrayList<BarEntry>()
        // y3 数据
        val yValues3 = java.util.ArrayList<BarEntry>()

        for (x in list.indices) {
            val zdmj = list.get(x).object1
            yValues.add(BarEntry(x.toFloat() + 0.5f, zdmj.toFloat()))

            val jzmj = list.get(x).object2
            yValues2.add(BarEntry(x.toFloat(), jzmj.toFloat()))

        }

        // y 轴数据集
        val barDataSet = BarDataSet(yValues, "占地面积(亩)")
        barDataSet.color = Color.parseColor("#36A3DB")

        // y2 轴数据集
        val barDataSet2 = BarDataSet(yValues2, "建筑面积(亩)")
        barDataSet2.color = Color.parseColor("#FFDB5C")

        // y3 轴数据集
        val barDataSet3 = BarDataSet(yValues3, "宅基地")
        barDataSet3.color = Color.parseColor("#FFDB5C")

        val mBarData = BarData(barDataSet, barDataSet2)
        val groupSpace = 0.04f
        val barSpace = 0.03f
        val barWidth = 0.45f
        // 设置 柱子宽度
        mBarData!!.barWidth = barWidth
        barChart.data = mBarData
        barChart.groupBars(0.0f, groupSpace, barSpace)
        barChart.invalidate()
        val mMatrix = Matrix()
        mMatrix.postScale(1f, 1f)
        barChart.viewPortHandler.refresh(mMatrix, barChart, false)
        barChart.animateY(800)
        barChart!!.setScaleEnabled(false)//启用/禁用两个轴上的图表缩放
        barChart!!.setScaleXEnabled(false)//启用/禁用x轴缩放
        barChart!!.setScaleYEnabled(false)//启用/禁用y轴缩放
        barChart!!.setPinchZoom(false)//如果设置为true，则启用缩放缩放。如果禁用，则可以单独缩放x轴和y轴
        barChart!!.setDoubleTapToZoomEnabled(false)//
        xAxis.axisMinimum = -0.5f
        xAxis.axisMaximum = barChart!!.getData().getXMax() + 1f
    }


    fun getData(entity: YeWuFrameBean.DataBean, sb: String) {
        sbs = sb
        entityList = entity
        frame_zjdgl_sq11.text = "进行中:" + entity.sqzjd.ongoingCount + "宗"
        frame_zjdgl_sq21.text = "已完成:" + entity.sqzjd.completeCount + "宗"
        frame_zjdgl_sq31.text = "被驳回:" + entity.sqzjd.rejectedCount + "宗"

        frame_zjdgl_fj11.text = "进行中:" + entity.fjzjd.ongoingCount + "宗"
        frame_zjdgl_fj21.text = "已完成:" + entity.fjzjd.completeCount + "宗"
        frame_zjdgl_fj31.text = "被驳回:" + entity.fjzjd.rejectedCount + "宗"

        frame_zjdgl_lz11.text = "进行中:" + entity.lzzjd.ongoingCount + "宗"
        frame_zjdgl_lz21.text = "已完成:" + entity.lzzjd.completeCount + "宗"
        frame_zjdgl_lz31.text = "被驳回:" + entity.lzzjd.rejectedCount + "宗"

        frame_zjdgl_tc11.text = "进行中:" + entity.tczjd.ongoingCount + "宗"
        frame_zjdgl_tc21.text = "已完成:" + entity.tczjd.completeCount + "宗"
        frame_zjdgl_tc31.text = "被驳回:" + entity.tczjd.rejectedCount + "宗"

        frame_hbjc_tv11.text = "进行中:" + entity.envior.ongoingCount + "宗"
        frame_hbjc_tv21.text = "已完成:" + entity.envior.completeCount + "宗"

        frame_zjdxc_tv11.text = "进行中:" + entity.zjdillegal.ongoingCount + "宗"
        frame_zjdxc_tv21.text = "已完成:" + entity.zjdillegal.completeCount + "宗"

        frame_tdxc_tv11.text = "进行中:" + entity.tdillegal.ongoingCount + "宗"
        frame_tdxc_tv21.text = "已完成:" + entity.zjdillegal.completeCount + "宗"
    }

    //导出pdf
    fun outPDF() {
        iv_kxxyjbg_ml.visibility = View.GONE

        val pdfPath = GetFileUtil.getSDCardPath() + "/jymj/" + AppUtils.getAppName() + "-可行性研究报告.pdf"
        val pngPath = GetFileUtil.getSDCardPath() + "/jymj/" + AppUtils.getAppName() + "-可行性研究报告.png"
        val boolean = ScrollviewBitmap.getScrollViewBitmap(kxxyjbg_scrollView, pngPath)
        if (boolean) {
            iv_kxxyjbg_ml.visibility = View.VISIBLE
            val content = TextView(activity)
            content.text = "是否打开导出的文件？"
            SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("打开文件")
                    .setCustomView(content)
                    .setConfirmButton("确定") { sweetAlertDialog ->
                        FileUtil.openFile(activity, File(pngPath))
                        sweetAlertDialog?.dismiss()
                    }
                    .setCancelButton(getString(R.string.cancel), { sweetAlertDialog ->
                        sweetAlertDialog?.dismiss()
                    })
                    .show()
        } else {
            ToastUtils.showLong("导出失败！")
        }
        */
/*val success = PDFUtil.createPdfFromView(kxxyjbg_scrollView, kxxyjbg_scrollView.measuredWidth, kxxyjbg_scrollView.measuredHeight, pdfPath)
        if (success){
            iv_kxxyjbg_ml.visibility = View.VISIBLE
            val content = TextView(activity)
            content.text = "是否打开导出的文件？"
            SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("打开文件")
                    .setCustomView(content)
                    .setConfirmButton(getString(R.string.ok)) { sweetAlertDialog ->
                        FileUtil.openFile(activity, File(pdfPath))
                    }
                    .setCancelButton(getString(R.string.cancel), { sweetAlertDialog ->
                        sweetAlertDialog?.dismiss()
                    })
                    .show()
        }else{
            ToastUtils.showLong("导出失败！")
        }*//*

    }

    //人口明细
    fun getRKMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("pageSize", pageSize)
        jsonObject.put("currPage", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETZhaiQueryFrame).upJson(jsonObject).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameRKMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameRKMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.zhaiList.size > 0) {
                                val data = json.data.zhaiList
                                initRkmxPop(data)
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }

    //院落明细
    fun getYLMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("pageSize", pageSize)
        jsonObject.put("currPage", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETYlQueryFrame).upJson(jsonObject).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameYLMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameYLMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.ylEntityList.size > 0) {
                                val data = json.data.ylEntityList
                                initYlmxPop(data)
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }

    //土现明细
    fun getTXMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("point", points)
        jsonObject.put("page", currPage)
        jsonObject.put("limit", pageSize)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETTdlyQueryFrame).upJson(jsonObject).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val decrypt1 = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameTXMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameTXMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.list.size > 0) {
                                val data = json.data.list
                                initTxmxPop(data)
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }
                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }

    //土规明细
    fun getTGMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("pageSize", pageSize)
        jsonObject.put("currPage", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETTgQueryFrame).upJson(jsonObject).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameTGMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameTGMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.tgEntityList.size > 0) {
                                val data = json.data.tgEntityList
                                initTgmxPop(data)
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }

    //非宅明细
    fun getFZMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("pageSize", pageSize)
        jsonObject.put("currPage", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETTgqueryYlFei).upJson(jsonObject).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameFZMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameFZMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.ylEntityList.size > 0) {
                                val data = json.data.ylEntityList
                                initFzmxPop(data)
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }

    //流动人口明细
    fun getLDRKMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()

        jsonObject.put("points", points)
        jsonObject.put("page", currPage)
        jsonObject.put("limit", pageSize)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETLdrkqueryList).upJson(jsonObject).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
//                                T fromJson = new Gson().fromJson(s, T);
//                    val json: FrameLdrkMxBean = Gson().fromJson(decrypt, object : TypeToken<FrameLdrkMxBean?>() {}.type)
                    val json: BaseRespose<PageUtils<FloatPopulat>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<FloatPopulat>>?>() {}.type)

                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.list.size > 0) {
                                val data = json.data.list
                                initLdrkmxPop(data)
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }
                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }


    //人口明细弹框
    fun initRkmxPop(data: List<FrameRKMXBean.DataBean.ZhaiListBean>) {//基础情况明细
        xrl_frame_rkmx!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val rkmxAdapter = FrameRkmxAdapter(context, data)
        xrl_frame_rkmx!!.adapter = rkmxAdapter

        */
/*xrl_frame_rkmx!!.adapter = object : BaseQuickAdapter<FrameRKMXBean.DataBean.ZhaiListBean, BaseViewHolder>(R.layout.item_tdkybg_text, data) {
            override fun convert(helper: BaseViewHolder?, item: FrameRKMXBean.DataBean.ZhaiListBean?) {
                    helper!!.setText(R.id.item_frame_rkmx_cm,item!!.xzqmc)
                            .setText(R.id.item_frame_rkmx_xm,item.housecount)
                            .setText(R.id.item_frame_rkmx_sex,item.sexText)
                            .setText(R.id.item_frame_rkmx_age,item.age.toString())
                            .setText(R.id.item_frame_rkmx_address,item.hjdz)
            }
        }*//*

        if (limit % 20 != 0 || data.size < limit) {
            xrl_frame_rkmx!!.setLoadingMoreEnabled(false)
            ToastUtils.showShort("滑动到底部了")
        }
        if (limit != 20) {
            xrl_frame_rkmx!!.scrollToPosition(data.size)
        }

        xrl_frame_rkmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                limit += 20
                getRKMXData(sbs, 1, limit)
            }

            override fun onRefresh() {
                limit = 20
                getRKMXData(sbs, 1, limit)
                xrl_frame_rkmx!!.setLoadingMoreEnabled(true)
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        xrl_frame_rkmx!!.loadMoreComplete()
        xrl_frame_rkmx!!.refreshComplete()
        val showing = mRkmxPopu!!.isShowing
        if (!showing) {
            mRkmxPopu?.showAtLocation(kxxyjbg_drawer_layout, Gravity.CENTER, 0, 0)
        }

    }

    //院落明细弹框
    fun initYlmxPop(data: List<FrameYLMXBean.DataBean.YlEntityListBean>) {//基础情况明细
        xrl_frame_ylmx!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val ylmxAdapter = FrameYlmxAdapter(context, data)
        xrl_frame_ylmx!!.adapter = ylmxAdapter

        */
/*xrl_frame_rkmx!!.adapter = object : BaseQuickAdapter<FrameRKMXBean.DataBean.ZhaiListBean, BaseViewHolder>(R.layout.item_tdkybg_text, data) {
            override fun convert(helper: BaseViewHolder?, item: FrameRKMXBean.DataBean.ZhaiListBean?) {
                    helper!!.setText(R.id.item_frame_rkmx_cm,item!!.xzqmc)
                            .setText(R.id.item_frame_rkmx_xm,item.housecount)
                            .setText(R.id.item_frame_rkmx_sex,item.sexText)
                            .setText(R.id.item_frame_rkmx_age,item.age.toString())
                            .setText(R.id.item_frame_rkmx_address,item.hjdz)
            }
        }*//*

        if (limit % 20 != 0 || data.size < limit) {
            xrl_frame_ylmx!!.setLoadingMoreEnabled(false)
            ToastUtils.showShort("滑动到底部了")
        }
        if (limit != 20) {
            xrl_frame_ylmx!!.scrollToPosition(data.size)
        }

        xrl_frame_ylmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                limit += 20
                getYLMXData(sbs, 1, limit)
            }

            override fun onRefresh() {
                limit = 20
                getYLMXData(sbs, 1, limit)
                xrl_frame_ylmx!!.setLoadingMoreEnabled(true)
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        xrl_frame_ylmx!!.loadMoreComplete()
        xrl_frame_ylmx!!.refreshComplete()
        val showing = mYlmxPopu!!.isShowing
        if (!showing) {
            mYlmxPopu?.showAtLocation(kxxyjbg_drawer_layout, Gravity.CENTER, 0, 0)
        }

    }

    //土现明细弹框
    fun initTxmxPop(data: List<FrameTXMXBean.DataBean.ListBean>) {
        xrl_frame_txmx!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val ylmxAdapter = FrameTxmxAdapter(context, data)
        xrl_frame_txmx!!.adapter = ylmxAdapter
        ylmxAdapter.setOnClickLister {  }

        */
/*xrl_frame_rkmx!!.adapter = object : BaseQuickAdapter<FrameRKMXBean.DataBean.ZhaiListBean, BaseViewHolder>(R.layout.item_tdkybg_text, data) {
            override fun convert(helper: BaseViewHolder?, item: FrameRKMXBean.DataBean.ZhaiListBean?) {
                    helper!!.setText(R.id.item_frame_rkmx_cm,item!!.xzqmc)
                            .setText(R.id.item_frame_rkmx_xm,item.housecount)
                            .setText(R.id.item_frame_rkmx_sex,item.sexText)
                            .setText(R.id.item_frame_rkmx_age,item.age.toString())
                            .setText(R.id.item_frame_rkmx_address,item.hjdz)
            }
        }*//*

        if (limit % 20 != 0 || data.size < limit) {
            xrl_frame_txmx!!.setLoadingMoreEnabled(false)
            ToastUtils.showShort("滑动到底部了")
        }
        if (limit != 20) {
            xrl_frame_txmx!!.scrollToPosition(data.size)
        }

        xrl_frame_txmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                limit += 20
                getTXMXData(sbs, 1, limit)
            }

            override fun onRefresh() {
                limit = 20
                getTXMXData(sbs, 1, limit)
                xrl_frame_txmx!!.setLoadingMoreEnabled(true)
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        xrl_frame_txmx!!.loadMoreComplete()
        xrl_frame_txmx!!.refreshComplete()
        val showing = mTxmxPopu!!.isShowing
        if (!showing) {
            mTxmxPopu?.showAtLocation(kxxyjbg_drawer_layout, Gravity.CENTER, 0, 0)
        }

    }

    //土规明细弹框
    fun initTgmxPop(data: List<FrameTGMXBean.DataBean.TgEntityListBean>) {
        xrl_frame_tgmx!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val ylmxAdapter = FrameTgmxAdapter(context, data)
        xrl_frame_tgmx!!.adapter = ylmxAdapter

        */
/*xrl_frame_rkmx!!.adapter = object : BaseQuickAdapter<FrameRKMXBean.DataBean.ZhaiListBean, BaseViewHolder>(R.layout.item_tdkybg_text, data) {
            override fun convert(helper: BaseViewHolder?, item: FrameRKMXBean.DataBean.ZhaiListBean?) {
                    helper!!.setText(R.id.item_frame_rkmx_cm,item!!.xzqmc)
                            .setText(R.id.item_frame_rkmx_xm,item.housecount)
                            .setText(R.id.item_frame_rkmx_sex,item.sexText)
                            .setText(R.id.item_frame_rkmx_age,item.age.toString())
                            .setText(R.id.item_frame_rkmx_address,item.hjdz)
            }
        }*//*

        if (limit % 20 != 0 || data.size < limit) {
            xrl_frame_tgmx!!.setLoadingMoreEnabled(false)
            ToastUtils.showShort("滑动到底部了")
        }
        if (limit != 20) {
            xrl_frame_tgmx!!.scrollToPosition(data.size)
        }

        xrl_frame_tgmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                limit += 20
                getTGMXData(sbs, 0, limit)
            }

            override fun onRefresh() {
                limit = 20
                getTGMXData(sbs, 0, limit)
                xrl_frame_tgmx!!.setLoadingMoreEnabled(true)
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        xrl_frame_tgmx!!.loadMoreComplete()
        xrl_frame_tgmx!!.refreshComplete()
        val showing = mTgmxPopu!!.isShowing
        if (!showing) {
            mTgmxPopu?.showAtLocation(kxxyjbg_drawer_layout, Gravity.CENTER, 0, 0)
        }

    }

    //非宅明细弹框
    fun initFzmxPop(data: List<FrameFZMXBean.DataBean.YlEntityListBean>) {
        xrl_frame_fzmx!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val ylmxAdapter = FrameFzmxAdapter(context, data)
        xrl_frame_fzmx!!.adapter = ylmxAdapter

        */
/*xrl_frame_rkmx!!.adapter = object : BaseQuickAdapter<FrameRKMXBean.DataBean.ZhaiListBean, BaseViewHolder>(R.layout.item_tdkybg_text, data) {
            override fun convert(helper: BaseViewHolder?, item: FrameRKMXBean.DataBean.ZhaiListBean?) {
                    helper!!.setText(R.id.item_frame_rkmx_cm,item!!.xzqmc)
                            .setText(R.id.item_frame_rkmx_xm,item.housecount)
                            .setText(R.id.item_frame_rkmx_sex,item.sexText)
                            .setText(R.id.item_frame_rkmx_age,item.age.toString())
                            .setText(R.id.item_frame_rkmx_address,item.hjdz)
            }
        }*//*

        if (limit % 20 != 0 || data.size < limit) {
            xrl_frame_fzmx!!.setLoadingMoreEnabled(false)
            ToastUtils.showShort("滑动到底部了")
        }
        if (limit != 20) {
            xrl_frame_fzmx!!.scrollToPosition(data.size)
        }

        xrl_frame_fzmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                limit += 20
                getFZMXData(sbs, 1, limit)
            }

            override fun onRefresh() {
                limit = 20
                getFZMXData(sbs, 1, limit)
                xrl_frame_fzmx!!.setLoadingMoreEnabled(true)
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        xrl_frame_fzmx!!.loadMoreComplete()
        xrl_frame_fzmx!!.refreshComplete()
        val showing = mFzmxPopu!!.isShowing
        if (!showing) {
            mFzmxPopu?.showAtLocation(kxxyjbg_drawer_layout, Gravity.CENTER, 0, 0)
        }

    }

    //流动人口明细弹框
    fun initLdrkmxPop(data: List<FloatPopulat>) {
        xrl_frame_ldrkmx!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val ylmxAdapter = LdrkSelectAdapter(context, data)
        xrl_frame_ldrkmx!!.adapter = ylmxAdapter

        ylmxAdapter.setOnLdrkSelectLinear(object :LdrkSelectAdapter.OnLdrkSelectLinear{
            override fun onDeleteClick(floatPopulat: FloatPopulat?,positon:Int) {
                getLdrkDelete(floatPopulat!!.id)
            }

            override fun onClick(floatPopulat: FloatPopulat?,positon:Int) {
                getLdrkDetail(floatPopulat!!.ylEntity.center1)
            }
        })


        if (limit % 20 != 0 || data.size < limit) {
            xrl_frame_ldrkmx!!.setLoadingMoreEnabled(false)
            ToastUtils.showShort("滑动到底部了")
        }
        if (limit != 20) {
            xrl_frame_ldrkmx!!.scrollToPosition(data.size)
        }

        xrl_frame_ldrkmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                limit += 20
                getLDRKMXData(sbs, 1, limit)
            }

            override fun onRefresh() {
                limit = 20
                getLDRKMXData(sbs, 1, limit)
                xrl_frame_ldrkmx!!.setLoadingMoreEnabled(true)
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        xrl_frame_ldrkmx!!.loadMoreComplete()
        xrl_frame_ldrkmx!!.refreshComplete()
        val showing = mLdrkmxPopu!!.isShowing
        if (!showing) {
            mLdrkmxPopu?.showAtLocation(kxxyjbg_drawer_layout, Gravity.CENTER, 0, 0)
        }

    }
    fun getLdrkDetail(point:String){
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
//        OkGo.post<String>(ApiConstants.FLOAT_POPULAT_LIST).upJson(sss).execute(LdrkChaNetCallBack(mView))
        OkGo.post<String>(ApiConstants.FLOAT_POPULAT_LIST).upJson(jsonObject).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                                T fromJson = new Gson().fromJson(s, T);
                        val json: BaseRespose<LdrkPointBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<LdrkPointBean>?>() {}.type)

                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data.ylEntity.gid!=0){
                                val intent = Intent(activity, LdrkDetailActivity::class.java)
                                intent.putExtra("ldrkPointBean",json.data)
                                startActivity(intent)
                            }
                        }else{
                            showErrorTip("该条数据为空")
                        }
                    }else{
                        showErrorTip("该条数据为空")
                    }
                }else{
                    showErrorTip("该条数据为空")
                }
                stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                showErrorTip("请求失败")
            }

        })
    }
    fun getLdrkDelete(id:Int) {

        val jsonObject = JSONObject()
        jsonObject.put("id","["+id+"]")

//        val substring = jsonObject.toString().substring(0, jsonObject.toString().length)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"

        OkGo.post<String>(ApiConstants.FLOAT_POPULAT_DELETE).upJson(jsonObject).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(cash)
//                    val json = Gson().fromJson(decrypt, BaseRespose<LdrkBean>::class.java)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)

                    if (json.code==0){
                        if (json!!.data!=null){
                            ToastUtils.showShort("删除成功")
                        }else {
                            showErrorTip(json.getMsg())
                        }
                    }else{
                        showErrorTip(json.getMsg())
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                showErrorTip("请求失败")
            }

        })
    }
    fun showLoading(title: String?) { //正在请求数据
        LoadingDialog.showDialogForLoading(activity)
    }
    fun stopLoading() { //请求数据结束
        LoadingDialog.cancelDialogForLoading()
    }
    fun showErrorTip(msg: String?) { //请求信息失败，返回的失败原因
        ToastUtils.showShort(msg)
    }
}
*/
