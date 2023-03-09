package com.jymj.zhglxt.zjd.fragment.yzt

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Point
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.text.Html
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.tabs.TabLayout

import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.rjhj.bean.PjEnviorSupvsEntity
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.rjhj.bean.YLEntity
import com.jymj.zhglxt.rjhj.bean.yl.*
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyEntity
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.InitYLRadar
import com.jymj.zhglxt.widget.gxt.bean.AtmanRelation
import com.jymj.zhglxt.widget.gxt.bean.RelationParent
import com.jymj.zhglxt.widget.gxt.bean.ZhengjuBean
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.widget.zdybj.TextListLayout
import com.jymj.zhglxt.zjd.activity.CzdwDetailActivity
import com.jymj.zhglxt.zjd.activity.HjgxtActivity
import com.jymj.zhglxt.zjd.activity.PDFActivity
import com.jymj.zhglxt.zjd.bean.CJBarChartBean
import com.jymj.zhglxt.zjd.bean.FrameJcxxBean
import com.jymj.zhglxt.zjd.bean.YeWuFrameBean
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBusiness
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseFileEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseInfoEntity
import com.jymj.zhglxt.zjd.bean.yzt.HujiEntity
import com.jymj.zhglxt.zjd.bean.yzt.QsEntity
import com.jymj.zhglxt.zjd.bean.yzt.YztDpBean
import com.jymj.zhglxt.zjd.bean.yzt.nyd.ZzyNydEntity
import com.jymj.zhglxt.zjd.bean.yzt.tg.TgEntity
import com.jymj.zhglxt.zjd.contract.YztDcContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.YztDcPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.nineoldandroids.view.ViewHelper
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.baseapp.BaseApplication
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_qygl_detail.*
import kotlinx.android.synthetic.main.frag_yzt_dc.*
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_jbqk_iv
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_jbqk_ll
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_ldrk_mj
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_ldrk_rk
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_rjzjdzmj
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_sjc
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_zhs
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_zjdjzmj
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_zjdzdmj
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_zjdzs
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_jcxxnew_zrks
import kotlinx.android.synthetic.main.frag_yzt_dc.frame_nfn_pct
import kotlinx.android.synthetic.main.frag_yzt_dc.hvScrollView
import kotlinx.android.synthetic.main.frag_yzt_dc.shaderImage
import kotlinx.android.synthetic.main.frag_zjdgl.*
import java.io.File
import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *f
 */
class YztDcFragment : BaseFragment<YztDcPresenter, BaseModel>(), YztDcContract.View, View.OnClickListener{

    var jbqk_flag: Int = 0
    var fwxx_flag: Int = 0
    var txxx_flag: Int = 0
    var tgxx_flag: Int = 0
    var cgxx_flag: Int = 0
    var fzxx_flag: Int = 0
    var zysy_flag: Int = 0
    var ldrk_flag: Int = 0
    //业务
    var zjdflFlag: Int = 0
    var hbjcFlag: Int = 0
    var zjdxcFlag: Int = 0
    var tdxcFlag: Int = 0
    var entityList: YeWuFrameBean.DataBean? = null
    var sbs: String = ""
    private var pointTdlyEntity: TdlyEntity? = null //点查土现

    var detailEntity: FrameJcxxBean.DataBean? = null

    /*
    * 记录暂时按下的位置
    * */
    internal var fingerX = 0
    internal var fingerY = 0
    private var scale = 1f
    private var preScale = 1f// 默认前一次缩放比例为1
    private var showWhat = 3;//当前显示的几度关系

    private var moreFinger = false//多指操作
    private var mScaleGestureDetector: ScaleGestureDetector? = null;
    private var clickAtmanRelation: AtmanRelation? = null
    private var sourceList = ArrayList<AtmanRelation>()
    private var zhaiList = ArrayList<ZhaiEntity>()//HujiEntity
    private val mhandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                11 ->{
                    if (hvScrollView!=null){
                        hvScrollView.scrollTo(DisplayUtil.dip2px(2500f) - DisplayUtil.getWidth() / 2,
                                DisplayUtil.dip2px(2500f) - DisplayUtil.getHeight() / 2)
                    }
                }
                12 -> if (scale < 0.7) {
                    tryScale(scale, 0.7f)
                } else if (scale > 1.3) {
                    tryScale(scale, 1.3f)
                }
            }
        }
    }
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): YztDcFragment {
            return YztDcFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.frag_yzt_dc
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        if (supl_frag_zjdgl!=null){
            supl_frag_zjdgl1?.setScrollableView(sv_frag_yzt_dc)
        }
        frame_jcxxnew_jbqk_iv.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (jbqk_flag == 0) {
                    frame_jcxxnew_jbqk_ll.visibility = View.GONE
                    jbqk_flag = 1
                } else {
                    frame_jcxxnew_jbqk_ll.visibility = View.VISIBLE
                    jbqk_flag = 0
                }
            }
        })
        frame_jcxxnew_fwxx_iv.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (fwxx_flag == 0) {
                    frame_jcxxnew_fwxx_ll.visibility = View.GONE
                    fwxx_flag = 1
                } else {
                    frame_jcxxnew_fwxx_ll.visibility = View.VISIBLE
                    fwxx_flag = 0
                }
            }
        })
        frame_jcxxnew_txxx_iv.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (txxx_flag == 0) {
                    frame_jcxxnew_txxx_ll.visibility = View.GONE
                    txxx_flag = 1
                } else {
                    frame_jcxxnew_txxx_ll.visibility = View.VISIBLE
                    txxx_flag = 0
                }
            }
        })
        frame_jcxxnew_tgxx_iv.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (tgxx_flag == 0) {
                    frame_jcxxnew_tgxx_ll.visibility = View.GONE
                    tgxx_flag = 1
                } else {
                    frame_jcxxnew_tgxx_ll.visibility = View.VISIBLE
                    tgxx_flag = 0
                }
            }
        })
        frame_jcxxnew_fzxx_iv.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (fzxx_flag == 0) {
                    frame_jcxxnew_fzxx_ll.visibility = View.GONE
                    fzxx_flag = 1
                } else {
                    frame_jcxxnew_fzxx_ll.visibility = View.VISIBLE
                    fzxx_flag = 0
                }
            }
        })
        frame_jcxxnew_cgxx_iv.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (cgxx_flag == 0) {
                    frame_cgxx_zwxx.visibility = View.GONE
                    cgxx_flag = 1
                } else {
                    frame_cgxx_zwxx.visibility = View.VISIBLE
                    cgxx_flag = 0
                }
            }
        })
        frame_jcxxnew_zysy_iv.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (zysy_flag == 0) {
                    frame_jcxxnew_zysy_ll.visibility = View.GONE
                    zysy_flag = 1
                } else {
                    frame_jcxxnew_zysy_ll.visibility = View.VISIBLE
                    zysy_flag = 0
                }
            }
        })
        frame_jcxxnew_ldrk_iv.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ldrk_flag == 0) {
                    frame_jcxxnew_ldrk_ll.visibility = View.GONE
                    ldrk_flag = 1
                } else {
                    frame_jcxxnew_ldrk_ll.visibility = View.VISIBLE
                    ldrk_flag = 0
                }
            }
        })
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
        tv_frag_yzt_dc_ylxx.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name:String) {
                if (ll_frag_yzt_dc_ylxx.isShown){
                    ll_frag_yzt_dc_ylxx.visibility = View.GONE
                }else{
                    ll_frag_yzt_dc_ylxx.visibility = View.VISIBLE
                }
            }

        })

    }

    override fun initDatas() {
        kxxyjbg_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//关闭手势滑动
        iv_kxxyjbg_ml.setOnClickListener {
            kxxyjbg_drawer_layout.openDrawer(Gravity.LEFT)
        }
        var data = ArrayList<String>()
        data.add("一、基础信息")
        data.add("  1.基本情况")
        data.add("  2.房屋信息")
        data.add("  3.土现信息")
        data.add("  4.空间规划")
        data.add("  5.非宅信息")
        data.add("  6.城规信息")
        data.add("  7.资源使用")
        data.add("  8.流动人口")
       /* data.add("二、业务信息")
        data.add("  1.宅基地管理信息")
        data.add("  2.环保监察信息")
        data.add("  3.土地巡查信息")*/
        rlv_kxxyjbg_ml.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_kxxyjbg_ml.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_tdkybg_text, data) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                val textTitle = helper!!.getView<TextView>(R.id.tdkybg_text_title)

                if (item!!.contains("不利因素")) {
                    textTitle.visibility = View.GONE
                } else if (item!!.contains(".")) {
                    textTitle.setPadding(100, 0, 0, 0)
//                    textTitle.layoutParams
                } /*else if (item.contains("不利因素")) {
                    textTitle.visibility = View.GONE
                }*/
                val text = helper!!.setText(R.id.tdkybg_text_title, item)
                val view = helper.getView<TextView>(R.id.tdkybg_text_title)
                view.setOnClickListener {
                    val field = R.id::class.java.getField("frame_scrool" + helper.layoutPosition)
                    val textView = activity!!.findViewById<LinearLayout>((field.getInt(null)))
                    val top = textView.top
                    sv_frag_yzt_dc.smoothScrollTo(0, top)
                    kxxyjbg_drawer_layout.closeDrawer(Gravity.LEFT)
                }
            }
        }
        shaderImage.setListener(this)
        hvScrollView.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            Log.e("这里该怎么解决", "=====2")
            if (moreFinger) {
                mScaleGestureDetector?.onTouchEvent(motionEvent)
            }

            when (motionEvent.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    //第一个手指按下事件
                    fingerX = motionEvent.x.toInt()
                    fingerY = motionEvent.y.toInt()
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    //第二个手指按下事件
                    Log.e("我看看那这里的操作哈", "第二个手指按下=====")

                    moreFinger = true
                }
                MotionEvent.ACTION_MOVE -> Log.e("我看看那这里的操作哈", "手指移动1111")
                MotionEvent.ACTION_UP -> {
                    if (!moreFinger && clickAtmanRelation != null) {//单指 并且是点击状态
                        val x_cha = (fingerX - motionEvent.x).toInt()
                        val y_cha = (fingerY - motionEvent.y).toInt()
                        if (Math.abs(x_cha) + Math.abs(y_cha) < 10) {
                            shaderImage.setShowCount(showWhat)
                            clickAtmanRelation = null
                        }

                    }
                    moreFinger = false
                    Log.e("我看看那这里的操作哈", "手指移动upupupupupup")
                    mhandler.sendEmptyMessage(12)//手指放开来个回弹效果
                }
            }
//                        Log.e("这个判断一点没用？", moreFinger + "==============")
            moreFinger
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hvScrollView.setOnScrollChangeListener(View.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (shaderImage.canScrollBean != null) {
                    val left_x = shaderImage.canScrollBean.left_x.x
                    val lefy_y = shaderImage.canScrollBean.left_top_y.y
                    val right_x = shaderImage.canScrollBean.rigth_x.x
                    val right_y = shaderImage.canScrollBean.right_bottom_y.y
                    var adddisX_left = 0
                    var adddisX_right = 0
                    var adddisY_left = 0
                    var adddisY_right = 0
                    if (scale >= 1) {
                        if (scale <= 1.3) {
                            adddisX_left = (left_x - (left_x * scale - left_x) / 4).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x + (right_x * scale - right_x) / 4).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y - (lefy_y * scale - lefy_y) / 4).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y + (right_y * scale - right_y) / 4).toInt() + DisplayUtil.getHeight() / 2
                        } else {
                            adddisX_left = (left_x - (left_x * 1.3 - left_x) / 4).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x + (right_x * 1.3 - right_x) / 4).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y - (lefy_y * 1.3 - lefy_y) / 4).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y + (right_y * 1.3 - right_y) / 4).toInt() + DisplayUtil.getHeight() / 2
                        }

                    } else {
                        if (scale >= 0.7) {
                            adddisX_left = (left_x + (left_x - left_x * scale) / 4.5).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x - (right_x - right_x * scale) / 4.5).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y + (lefy_y - lefy_y * scale) / 4.5).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y - (right_y - right_y * scale) / 4.5).toInt() + DisplayUtil.getHeight() / 2
                        } else {
                            adddisX_left = (left_x + (left_x - left_x * 0.7) / 4.5).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x - (right_x - right_x * 0.7) / 4.5).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y + (lefy_y - lefy_y * 0.7) / 4.5).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y - (right_y - right_y * 0.7) / 4.5).toInt() + DisplayUtil.getHeight() / 2
                        }

                    }


                    val minX_left = DisplayUtil.dip2px( 2500f) - DisplayUtil.getWidth() / 2
                    val minY_left = DisplayUtil.dip2px( 2500f) - DisplayUtil.getHeight() / 2

                    val minX_right = DisplayUtil.dip2px( 2500f) + DisplayUtil.getWidth() / 2
                    val minY_right = DisplayUtil.dip2px( 2500f) + DisplayUtil.getHeight() / 2


                    if (adddisX_left > minX_left) {
                        adddisX_left = minX_left
                    }
                    if (adddisY_left > minY_left) {
                        adddisY_left = minY_left
                    }

                    if (adddisX_right < minX_right) {
                        adddisX_right = minX_right
                    }

                    if (adddisY_right < minY_right) {
                        adddisY_right = minY_right
                    }


                    if (scrollX <= adddisX_left && scrollY <= adddisY_left) {
                        hvScrollView.smoothScrollTo(adddisX_left, adddisY_left)
                    } else if (scrollX <= adddisX_left && scrollY >= adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_left, adddisY_right - DisplayUtil.getHeight())
                    } else if (scrollX <= adddisX_left && adddisY_left < scrollY && scrollY < adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_left, scrollY)
                    } else if (scrollX >= adddisX_right - DisplayUtil.getWidth() && scrollY <= adddisY_left) {
                        hvScrollView.smoothScrollTo(adddisX_right - DisplayUtil.getWidth(), adddisY_left)
                    } else if (scrollX >= adddisX_right - DisplayUtil.getWidth() && scrollY >= adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_right - DisplayUtil.getWidth(), adddisY_right - DisplayUtil.getHeight())
                    } else if (scrollX >= adddisX_right - DisplayUtil.getWidth() && adddisY_left < scrollY && scrollY < adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_right - DisplayUtil.getWidth(), scrollY)
                    } else if (scrollY <= adddisY_left && adddisX_left < scrollX && scrollX < adddisX_right - DisplayUtil.getWidth()) {
                        hvScrollView.smoothScrollTo(scrollX, adddisY_left)
                    } else if (scrollY >= adddisY_right - DisplayUtil.getHeight() && adddisX_left < scrollX && scrollX < adddisX_right - DisplayUtil.getWidth()) {
                        hvScrollView.smoothScrollTo(scrollX, adddisY_right - DisplayUtil.getHeight())
                    }
                } else {
                    //这里是只有一个头像的情况

                }
            })
        }/*
        hvScrollView.smoothScrollTo(DisplayUtil.dip2px(2500f) - DisplayUtil.getWidth() / 2,
                DisplayUtil.dip2px(2500f) -  DisplayUtil.getHeight()/ 2)*/
        mScaleGestureDetector = ScaleGestureDetector(activity,
                ScaleGestureListener())
        mhandler.sendEmptyMessageDelayed(11, 500)
    }
    fun setYztYlDc(YLPoint: YztPointEntity){
        hintPointView()
        diaoyong()
        ll_frag_yzt_dc.visibility = View.VISIBLE//点查显示
        ll_frag_yzt_kc.visibility = View.GONE//框查隐藏
        iv_kxxyjbg_ml.visibility = View.GONE
        tvs_act_yzt_hjgxt.visibility = View.GONE
        tv_frag_yzt_dc_ylxx_ckxq.visibility = View.GONE

        tvs_frag_yzt_dc_qyxx2.visibility = View.GONE
        tvs_frag_yzt_dc_qyxx3.visibility = View.GONE
        tvs_frag_yzt_dc_zjxx.visibility = View.GONE
        ll_jcxx_zjxx_zjzl.visibility = View.GONE
        tab_jcxx_qyxx.visibility = View.GONE//
        hsv_frag_yzt_dc_zjxx.visibility = View.GONE

        if (YLPoint.ylList.size > 0) {//YLPoint.yztFhEntity.ylEntities
            //宅基地
            if (YLPoint.ylList.get(0).fl2.equals("宅基地")){
                ll_frag_yzt_dc_ylxx.visibility = View.VISIBLE
                rc_frag_yzt_dc_ylxx.visibility = View.VISIBLE
                tvs_frag_yzt_dc_ylxx.visibility = View.VISIBLE
                tv_frag_yzt_dc_ylxx.visibility = View.VISIBLE

                val radarData = ArrayList<RadarEntry>()
                //雷达图的数据
                radarData.add(RadarEntry(88f))//YLPoint.ylList.get(0)!!.ldArea

                radarData.add(RadarEntry(33f))//YLPoint.ylList.get(0)!!.ldRk
                //radarData.add(RadarEntry(YLPoint.ylEntity!!.ldQdfs))
                radarData.add(RadarEntry(55f))//YLPoint.ylList.get(0)!!.ldRjl
                radarData.add(RadarEntry(100f))//YLPoint.ylList.get(0)!!.ldDjgx
                //向雷达图里添加数据
                InitYLRadar.getInstance().creatRadar(context, rc_frag_yzt_dc_ylxx, radarData)
                rc_frag_yzt_dc_ylxx.visibility = View.GONE

                val df = DecimalFormat("#.00")
                //院落数据（在雷达图上边）
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", YLPoint.ylList.get(0)?.xzqmc))
                if (AppCache.getInstance().code == "110111009022"){
                    tvDatas.add(TextViewsEntity("名称:", YLPoint.ylList.get(0).ct + ""))
                    if (YLPoint.ylList.get(0).getArea().compareTo(BigDecimal(10000)) > 0) {
                        tvDatas.add(TextViewsEntity("占地面积:", YLPoint.ylList.get(0).getArea().divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡"))
                    } else {
                        tvDatas.add(TextViewsEntity("占地面积:",  "${YLPoint.ylList.get(0).getArea().toInt()}㎡"))
                    }
                    tvDatas.add(TextViewsEntity("进度:", YLPoint.ylList.get(0).getCtps() + ""))
                    tvDatas.add(TextViewsEntity("用途:", YLPoint.ylList.get(0).getYt() + ""))
                    tvs_frag_yzt_dc_ylxx.setValueTexts(tvDatas, arrayOf(0, 1, 2, 3))
                }else{
                    tvDatas.add(TextViewsEntity("户主:", YLPoint.ylList.get(0)?.hzmc))
                    tvDatas.add(TextViewsEntity("门牌号:", YLPoint.ylList.get(0)?.mph))
                    tvDatas.add(TextViewsEntity("一级分类:", YLPoint.ylList.get(0)?.ylTypeText))
                    tvDatas.add(TextViewsEntity("二级分类:", YLPoint.ylList.get(0)?.fl2))
//                tvDatas.add(TextViewsEntity("多少套房:", "暂无数据"))

                    if (YLPoint.ylList.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                        tvDatas.add(TextViewsEntity("占地面积:", YLPoint.ylList.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                    }else{
                        tvDatas.add(TextViewsEntity("占地面积:", YLPoint.ylList.get(0)?.area!!.toInt().toString()+"㎡"))
                    }
                    if (YLPoint.ylList.get(0)?.jianzhuArea!!.compareTo(BigDecimal(10000))>0){
                        tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.ylList.get(0)?.jianzhuArea!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                    }else{
                        tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.ylList.get(0)?.jianzhuArea!!.toInt().toString()+"㎡"))
                    }

                    tvDatas.add(TextViewsEntity("人口总数:", YLPoint.ylList.get(0)?.rk.toString()))
//                tvDatas.add(TextViewsEntity("流动人口:", YLPoint.ylList.get(0)?.ldRk.toString()))
                    tvDatas.add(TextViewsEntity("农业人口:", YLPoint.ylList.get(0)?.nongcount.toString()))
                    tvDatas.add(TextViewsEntity("非农人口:", YLPoint.ylList.get(0)?.feinongcount.toString()))
                    tvDatas.add(TextViewsEntity("流动人口:", YLPoint.ylList.get(0)?.ldrks.toString()))
//                tvDatas.add(TextViewsEntity("户数:", YLPoint.ylList.get(0)?.hucount.toString()))
//                tvDatas.add(TextViewsEntity("特殊情况:", YLPoint.ylList.get(0)?.remark))
                    //tvDatas.add(TextViewsEntity("流动面积:", YLPoint.ylList.get(0)?.ldArea.toString()))
                    tvs_frag_yzt_dc_ylxx.setValueTexts(tvDatas, arrayOf(0,1,2))
                }

                /*tvs_frag_yzt_dc_ylxx?.setDatas(tvDatas)
                recyPointhb1?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))*/

                //红本
                val hbList=ArrayList<YLEntity>()
                hbList.add(YLPoint.ylList.get(0))

                recyPointhb1?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                recyPointhb1?.adapter = object : BaseQuickAdapter<YLEntity, BaseViewHolder>(R.layout.item_point_hb,hbList) {
                    override fun convert(helper: BaseViewHolder, item: YLEntity) {

                        helper.setText(R.id.hbbh_item, "088-003-00025-001")
                                .setText(R.id.hbcm_item, item.xzqmc)
                                .setText(R.id.hbxm_item, item.hzmc)
                                .setText(R.id.hbtm_item, "")
                                .setText(R.id.hbzrz_item, "")
                                .setText(R.id.hbzdmj_item, item.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString())
                                .setText(R.id.hbjzmj_item, item.jianzhuArea!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString())
                                .setText(R.id.hbxcsj_item, "")
                                .setText(R.id.hbgdnd_item, "1993")
                        helper.getView<TextView>(R.id.hbcz_item).setOnClickListener {
                            ToastUtils.showShort("暂无红本信息！")
                        }
                    }

                }


                if (YLPoint.zhaiList.size>0){//huji  YLPoint.huji  zhaiList
//                    tv_frag_yzt_dc_ylxx_ckxq.visibility = View.VISIBLE
                    rlt_yzt_shaderImage.visibility = View.VISIBLE
                    tv_frag_yzt_dc_ylxx_ckxq.setOnClickListener {
                        val intent = Intent(activity, HjgxtActivity::class.java)
                        intent.putExtra("huji",YLPoint.zhaiList as Serializable)//YLPoint.zhaiList as Serializable
                        startActivity(intent)

                    }
                    zhaiList = YLPoint.zhaiList as ArrayList<ZhaiEntity>//HujiEntity
                    sourceList = parseData(zhaiList)
                    shaderImage.setSourceList(sourceList)

                }else{
                    tv_frag_yzt_dc_ylxx_ckxq.visibility = View.GONE
                    rlt_yzt_shaderImage.visibility = View.GONE
                }

            }else{
                tv_frag_yzt_dc_ylxx.visibility = View.GONE
                rc_frag_yzt_dc_ylxx.visibility = View.GONE
                tvs_frag_yzt_dc_ylxx.visibility = View.GONE

                //企业
                if (YLPoint.enterprise!=null){
                    ll_jcxx_qyxx.visibility = View.VISIBLE
                    ll_jcxx_zjxx.visibility = View.GONE
//            tv_jcxx_qyxx.setText("产业用地信息")
                    ll_jcxx_tuxian.visibility = View.VISIBLE
                    tab_jcxx_qyxx.removeAllTabs()
                    tab_jcxx_qyxx.visibility = View.VISIBLE
                    tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("用地管理"))
                    tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业经营"))
                    tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("承租列表"))
                    ll_jcxx_qyxx2.visibility = View.VISIBLE
                    /* if (YLPoint.landinfo.size>0){
         //                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业列表"))
                     }*/
                    val enterprise = YLPoint.enterprise
                    val enterpriseInfoEntities = enterprise.enterpriseInfoEntitie//enterpriseInfoEntities
                    val tvDatas = ArrayList<TextViewsEntity>()
                    tvDatas.add(TextViewsEntity("编号:", enterprise.bh))
                    tvDatas.add(TextViewsEntity("现状企业名称:", enterprise.name))
                    tvDatas.add(TextViewsEntity("土地使用人:", enterprise.tdsyr))
                    tvDatas.add(TextViewsEntity("用地权属:", enterprise.qs))
                    tvDatas.add(TextViewsEntity("占地面积:", enterprise.zdmj.toString()))
                    tvDatas.add(TextViewsEntity("建筑面积:", enterprise.jzmj.toString()))
                    val enterpriseBuilding1 = enterprise.enterpriseBuilding

                    tvDatas.add(TextViewsEntity("房屋产权单位:", enterpriseBuilding1.fwcqdw))
                    tvDatas.add(TextViewsEntity("已建建筑面积(㎡):", enterpriseBuilding1.yjjzmj.toString()))
                    tvDatas.add(TextViewsEntity("有无地下空间:", enterpriseBuilding1.dxkj))
                    tvDatas.add(TextViewsEntity("空置建筑面积(㎡):", enterpriseBuilding1.kzjzmj.toString()))
                    tvDatas.add(TextViewsEntity("闲置楼宇面积(㎡):", enterpriseBuilding1.fwcqdw))
                    tvDatas.add(TextViewsEntity("空置厂房建筑面积(㎡):", enterpriseBuilding1.kzcfjzmj.toString()))
                    tvDatas.add(TextViewsEntity("是否分期建设:", enterpriseBuilding1.fqjsText))
                    tvDatas.add(TextViewsEntity("未建建筑面积(㎡):", enterpriseBuilding1.wjjzmj.toString()))
                    tvDatas.add(TextViewsEntity("空置厂房建设情况:", enterpriseBuilding1.kzcfjsqk))
                    tvDatas.add(TextViewsEntity("转让情况:", enterpriseBuilding1.zrqkText))

                    if (enterprise.enterpriseFileEntities.size>0){//.size>0
                        ll_jcxx_zjxx_zjzl.visibility = View.VISIBLE
                        for (i in enterprise.enterpriseInfoEntities){
                            enterprise.enterpriseFileEntities.addAll(i.enterpriseFileEntities)
                        }
                        rlv_jcxx_zjxx.layoutManager = GridLayoutManager(activity,3)//enterpriseInfo.enterpriseFileEntities
                        rlv_jcxx_zjxx.adapter = object : BaseQuickAdapter<EnterpriseFileEntity,BaseViewHolder>(R.layout.item_jcxx_tp,enterprise.enterpriseFileEntities){
                            override fun convert(helper: BaseViewHolder?, item: EnterpriseFileEntity?) {
                                val icoId: Int = GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path))
                                val ivItemJcxxPic = helper!!.getView<ImageView>(R.id.iv_item_jcxx_pic)
                                Glide.with(BaseApplication.getAppContext()).load(icoId)
                                        .into(ivItemJcxxPic)//.fitCenter()
                                helper!!.setText(R.id.tv_item_jcxx_name,item.filename)
                                helper.itemView.setOnClickListener {
                                    val pic = item.url//ApiConstants.BASE_URL + "tdzFile/" + item.folderid + "/" + item.path.trim()//YLPoint.ylList.get(0).tdzmc
                                    val intent = Intent(context, PDFActivity::class.java)
                                    intent.putExtra("pdf", pic.trim())
                                    startActivity(intent)
                                }
                            }
                        }
                    }else{
                        ll_jcxx_zjxx_zjzl.visibility = View.GONE
                    }
                    tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                    tvs_frag_yzt_dc_qyxx2.setValueTexts(tvDatas,arrayOf(0,1,2,5,6,7,8,9,10,11,12,13,14))//,4
                    if (enterprise!!.enterpriseInfoEntities.size>0){
                        hsv_frag_yzt_dc_zjxx.visibility = View.VISIBLE
                    }
                    rlv_act_qygl_yzt_dc_ydqk.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                    var ydqkAdapter = object : BaseQuickAdapter<EnterpriseInfoEntity, BaseViewHolder>(R.layout.item_text_list_ydqk, enterprise!!.enterpriseInfoEntities) {
                        override fun convert(helper: BaseViewHolder?, item: EnterpriseInfoEntity?) {
                            helper!!.setText(R.id.tv_qygl_item_list_xh, "${helper.adapterPosition + 1}")
                                    .setText(R.id.tv_qygl_item_list_tdsyr, item!!.tdsyr)
                                    .setText(R.id.tv_qygl_item_list_tdxz, item!!.tdxz)
                                    .setText(R.id.tv_qygl_item_list_qsqk, item!!.qs)
                                    .setText(R.id.tv_qygl_item_list_tdqdnx, item!!.tdqddate)
                                    .setText(R.id.tv_qygl_item_list_tdsynx, item!!.tdsynx)
                                    .setText(R.id.tv_qygl_item_list_tdjg, item!!.tdjg)
                                    .setText(R.id.tv_qygl_item_list_tdzmj, item!!.tdzmj.toString())
                                    .setText(R.id.tv_qygl_item_list_tdqdfs, item!!.tdqdfs)
                            helper.getView<TextView>(R.id.tv_qygl_item_list_wjsc).visibility = View.GONE
                            helper.getView<TextView>(R.id.tv_qygl_item_list_delete).visibility = View.GONE
                        }
                    }
                    rlv_act_qygl_yzt_dc_ydqk.adapter = ydqkAdapter


                    val enterpriseBuilding = enterprise.enterpriseBuilding
                    val tvDatas1 = ArrayList<TextViewsEntity>()
                    val enterpriseEntity1 = enterprise.enterpriseEntity
                    tvDatas1.add(TextViewsEntity("现状企业名称:", enterpriseEntity1.qyname.toString()))
                    tvDatas1.add(TextViewsEntity("注册地址:", enterpriseEntity1.zcdz.toString()))
                    tvDatas1.add(TextViewsEntity("实际经营地址:", enterpriseEntity1.sjjydz.toString()))
                    tvDatas1.add(TextViewsEntity("入住日期:", enterpriseEntity1.rzdate.toString()))
                    tvDatas1.add(TextViewsEntity("营业执照注册日期:", enterpriseEntity1.zcdate.toString()))
                    tvDatas1.add(TextViewsEntity("税务登记注册地:", enterpriseEntity1.swzcdz.toString()))

                    tvDatas1.add(TextViewsEntity("企业类型:", enterpriseEntity1.qylxText.toString()))
                    tvDatas1.add(TextViewsEntity("注册类型:", enterpriseEntity1.zclxText.toString()))
                    tvDatas1.add(TextViewsEntity("行业类型:", enterpriseEntity1.hylxText.toString()))
                    tvDatas1.add(TextViewsEntity("注册资金:", enterpriseEntity1.zczj.toString()))
                    tvDatas1.add(TextViewsEntity("行政联系人:", enterpriseEntity1.xzlxr.toString()))
                    tvDatas1.add(TextViewsEntity("联系电话:", enterpriseEntity1.lxdh.toString()))
                    if (enterpriseEntity1.czqk==0){
                        tvDatas1.add(TextViewsEntity("是否转租:", "否"))
                    }else{
                        tvDatas1.add(TextViewsEntity("是否转租:", "是"))
                    }
                    tvDatas1.add(TextViewsEntity("高新企业:", enterpriseEntity1.gxqyText.toString()))
                    /*tvDatas1.add(TextViewsEntity("房屋产权单位:", enterpriseBuilding.fwcqdw.toString()))
                    tvDatas1.add(TextViewsEntity("已建建筑面积:", enterpriseBuilding.yjjzmj.toString()+"㎡"))
                    tvDatas1.add(TextViewsEntity("空置建筑面积:", enterpriseBuilding.kzjzmj.toString()+"㎡"))
                    tvDatas1.add(TextViewsEntity("空置厂房建筑面积:", enterpriseBuilding.kzcfjzmj.toString()+"㎡"))
                    tvDatas1.add(TextViewsEntity("未建建筑面积:", enterpriseBuilding.wjjzmj.toString()+"㎡"))
                    tvDatas1.add(TextViewsEntity("闲置楼宇面积:", enterpriseBuilding.xzlymj.toString()+"㎡"))
                    tvDatas1.add(TextViewsEntity("是否分期建设:", enterpriseBuilding.fqjsText.toString()))
                    tvDatas1.add(TextViewsEntity("有无地下空间:", enterpriseBuilding.dxkj.toString()))
                    tvDatas1.add(TextViewsEntity("转让情况:", enterpriseBuilding.zrqkText.toString()))
                    tvDatas1.add(TextViewsEntity("空置厂房建设情况:", enterpriseBuilding.kzcfjsqk.toString()))
                    val enterpriseEntity = enterprise.enterpriseEntity
                    if (enterpriseEntity!=null){
                        tvDatas1.add(TextViewsEntity("现状企业名称:", enterpriseEntity.qyname.toString()))
                        tvDatas1.add(TextViewsEntity("行政联系人:", enterpriseEntity.xzlxr.toString()))
                        tvDatas1.add(TextViewsEntity("高新企业:", enterpriseEntity.gxqyText.toString()))
                        tvDatas1.add(TextViewsEntity("企业类型:", enterpriseEntity.hylxText))
                        tvDatas1.add(TextViewsEntity("注册地址:", enterpriseEntity.zcdz.toString()))
                        tvDatas1.add(TextViewsEntity("联系电话:", enterpriseEntity.lxdh.toString()))
                        tvDatas1.add(TextViewsEntity("实际经营地址:", enterpriseEntity.sjjydz.toString()))
                        if (enterpriseEntity.czqk==0){
                            tvDatas1.add(TextViewsEntity("是否转租:", "否"))
                        }else{
                            tvDatas1.add(TextViewsEntity("是否转租:", "是"))
                        }
                        tvDatas1.add(TextViewsEntity("营业执照注册日期:", enterpriseEntity.zcdate.toString()))
                        tvDatas1.add(TextViewsEntity("税务登记证注册地:", enterpriseEntity.swzcdz.toString()))
                        tvDatas1.add(TextViewsEntity("企业类型:", enterpriseEntity.qylxText.toString()))
                        tvDatas1.add(TextViewsEntity("注册资金:", enterpriseEntity.zczj.toString()))
                        tvDatas1.add(TextViewsEntity("入驻日期:", enterpriseEntity.rzdate.toString()))
                        tvDatas1.add(TextViewsEntity("行业类型:", enterpriseEntity.hylxText.toString()))
                        tvDatas1.add(TextViewsEntity("经营状态:", enterpriseEntity.jyztText.toString()))
                        tvDatas1.add(TextViewsEntity("经营收入:", enterpriseEntity.yysr.toString()))
                        tvDatas1.add(TextViewsEntity("利润总额:", enterpriseEntity.lrze.toString()))
                        tvDatas1.add(TextViewsEntity("产值:", enterpriseEntity.cz.toString()))
                        tvDatas1.add(TextViewsEntity("税收:", enterpriseEntity.hj.toString()))
                        tvDatas1.add(TextViewsEntity("本地员工数量:", enterpriseEntity.bdgrs.toString()))
                        tvDatas1.add(TextViewsEntity("外地员工数量:", enterpriseEntity.wdgrs.toString()))
                        tvDatas1.add(TextViewsEntity("就业人口居住区域(单位宿舍):", enterpriseEntity.dwss.toString()))
                        tvDatas1.add(TextViewsEntity("就业人口居住区域(本村):", enterpriseEntity.bc.toString()))
                        tvDatas1.add(TextViewsEntity("就业人口居住区域(其他):", enterpriseEntity.qt.toString()))
                    }*/
                    tvs_frag_yzt_dc_qyxx3.visibility = View.VISIBLE
                    tvs_frag_yzt_dc_qyxx3.setValueTexts(tvDatas1,arrayOf(0,1,4,9,12,13,16,17,19,20,21,22,25))

                    val tvDatas2 = ArrayList<TextViewsEntity>()
                    val enterpriseBusinesse = enterpriseEntity1.enterpriseBusinesse
                    tvDatas2.add(TextViewsEntity("利润总额:", enterpriseBusinesse.lrze.toString()))
                    tvDatas2.add(TextViewsEntity("营业收入:", enterpriseBusinesse.yysr.toString()))
                    tvDatas2.add(TextViewsEntity("税收:", enterpriseBusinesse.hj.toString()))
                    tvDatas2.add(TextViewsEntity("产值:", enterpriseBusinesse.cz.toString()))
                    tvDatas2.add(TextViewsEntity("经营状态:", enterpriseBusinesse.jyztText.toString()))
                    tvDatas2.add(TextViewsEntity("本地员工数量:", enterpriseBusinesse.bdgrs.toString()))
                    tvDatas2.add(TextViewsEntity("外地工人数量:", enterpriseBusinesse.wdgrs.toString()))
                    tvDatas2.add(TextViewsEntity("就业人口居住区域单位宿舍:", enterpriseBusinesse.dwss.toString()))
                    tvDatas2.add(TextViewsEntity("就业人口居住区域本村:", enterpriseBusinesse.bc.toString()))
                    tvDatas2.add(TextViewsEntity("就业人口居住区域其他:", enterpriseBusinesse.qt.toString()))

                    tll_frag_yzt_dc_jyqk.setValueTexts(tvDatas2,arrayOf(4,5,6,7,8,9))
                    mtl_frag_yzt_dc_jyqk.setAddText("")
                    if (enterpriseEntity1.id!=null){
                        mPresenter.getBusiness(enterpriseEntity1.id, "")//id
                    }
                    mtl_frag_yzt_dc_jyqk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                        override fun onClick(name: String?) {
                            if (name.equals("添加")){
                                if (SingleOnClickUtil1.isFastClick()){
                                    JyqkPickerUtil.initPickerView(activity,"经营历史列表",stringList,mtl_frag_yzt_dc_jyqk.addText,object:JyqkPickerUtil.OnPickerLinerar{
                                        override fun onClick(name: String?) {
                                            mtl_frag_yzt_dc_jyqk.setAddText(name)
                                            mPresenter.getBusiness(enterpriseEntity1!!.id, name!!)
                                        }
                                    })
                                }
                                /*if (SingleOnClickUtil1.isFastClick())
                                    TimePickerUtil.initTimePickerViewNy(activity,mtl_frag_yzt_dc_jyqk.addText.toString(),object: TimePickerUtil.OnTimePickerLister{
                                        override fun onClick(data: String?) {
                                            //时间筛选
                                            mtl_frag_yzt_dc_jyqk.setAddText(data)
                                            mPresenter.getBusiness(enterpriseEntity1.id, data!!)//id
                                        }
                                    })*/
                            }else{
                                if (tll_frag_yzt_dc_jyqk.isShown){
                                    tll_frag_yzt_dc_jyqk.visibility = View.GONE
                                }else{
                                    tll_frag_yzt_dc_jyqk.visibility = View.VISIBLE
                                }
                            }
                        }
                    })


                    tv_jcxx_qyxx.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
                        override fun onClick(name:String) {
                            if (ll_jcxx_qyxx1.isShown){
                                ll_jcxx_qyxx1.visibility = View.GONE
                            }else{
                                ll_jcxx_qyxx1.visibility = View.VISIBLE
                            }                }
                    })
                    rlv_frag_yzt_dc_czlb.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                    rlv_frag_yzt_dc_czlb.adapter = object:BaseQuickAdapter<EnterpriseEntity,BaseViewHolder>(R.layout.item_qygl_detail_czdwmc,enterprise.enterpriseEntities){
                        override fun convert(helper: BaseViewHolder?, item: EnterpriseEntity?) {
                            helper!!.setText(R.id.tv_item_qygl_detail_czdw_qymc, item!!.qyname)
                                    .setText(R.id.tv_item_qygl_detail_czdw_lxr, item!!.xzlxr)
                                    .setText(R.id.tv_item_qygl_detail_czdw_zclx, item!!.zcdz)

                            helper!!.itemView.setOnClickListener {
                                if (SingleOnClickUtil1.isFastClick()){
                                    val intent = Intent(activity, CzdwDetailActivity::class.java)
                                    intent.putExtra("enterpriseEntity",item)
                                    intent.putExtra("isUpdate",false)
                                    startActivity(intent)
                                }
                            }
                        }
                    }

                    /* rlv_frag_yzt_dc_czlb.adapter = object:BaseQuickAdapter<EnterpriseEntity, BaseViewHolder>(R.layout.item_yzt_dc_czlb, enterprise.enterpriseEntities){
                        override fun convert(helper: BaseViewHolder?, item: EnterpriseEntity?) {
                            val llItemYztDcCzlb = helper!!.getView<LinearLayout>(R.id.ll_item_yzt_dc_czlb)
                            val llItemYztDcCzlb1 = helper!!.getView<LinearLayout>(R.id.ll_item_yzt_dc_czlb1)
                            val ivItemYztDcCzlb = helper!!.getView<ImageView>(R.id.iv_item_yzt_dc_czlb)
                            helper.setText(R.id.tv_item_yzt_dc_czlb_xzqymc, item!!.qyname)
                                    .setText(R.id.tv_item_yzt_dc_czlb_xzlxr, item!!.xzlxr)
                                    .setText(R.id.tv_item_yzt_dc_czlb_zcdz, item!!.zcdz)
                                    .setText(R.id.tv_item_yzt_dc_czlb_jyzt, item!!.jyztText)
                            val tllItemYztDcCzlb = helper.getView<TextListLayout>(R.id.tll_item_yzt_dc_czlb)
                            val tvDatas1 = ArrayList<TextViewsEntity>()
                            tvDatas1.add(TextViewsEntity("现状企业名称:", item.qyname.toString()))
                            tvDatas1.add(TextViewsEntity("行政联系人:", item.xzlxr.toString()))
                            tvDatas1.add(TextViewsEntity("高新企业:", item.gxqyText.toString()))
                            tvDatas1.add(TextViewsEntity("注册类型:", item.zclxText.toString()))
                            tvDatas1.add(TextViewsEntity("注册地址:", item.zcdz.toString()))
                            tvDatas1.add(TextViewsEntity("联系电话:", item.lxdh.toString()))
                            tvDatas1.add(TextViewsEntity("实际经营地址:", item.sjjydz.toString()))
                            if (item.czqk==0){
                                tvDatas1.add(TextViewsEntity("是否转租:", "否"))
                            }else{
                                tvDatas1.add(TextViewsEntity("是否转租:", "是"))
                            }
                            tvDatas1.add(TextViewsEntity("营业执照注册日期:", item.zcdate.toString()))
                            tvDatas1.add(TextViewsEntity("税务登记注册地:", item.swzcdz.toString()))
                            tvDatas1.add(TextViewsEntity("企业类型:", item.qylxText.toString()))
                            tvDatas1.add(TextViewsEntity("注册资金:", item.zczj.toString()))
                            tvDatas1.add(TextViewsEntity("入住日期:", item.rzdate.toString()))
                            tvDatas1.add(TextViewsEntity("行业类型:", item.hylxText.toString()))
                            tvDatas1.add(TextViewsEntity("经营状态:", item.jyztText.toString()))
                            tvDatas1.add(TextViewsEntity("营业收入:", item.yysr.toString()))
                            tvDatas1.add(TextViewsEntity("利润总额:", item.lrze.toString()))
                            tvDatas1.add(TextViewsEntity("产值:", item.cz.toString()))
                            tvDatas1.add(TextViewsEntity("税收:", item.hj.toString()))
                            tvDatas1.add(TextViewsEntity("本地员工数量:", item.bdgrs.toString()))
                            tvDatas1.add(TextViewsEntity("外地工人数量:", item.wdgrs.toString()))
                            tvDatas1.add(TextViewsEntity("就业人口居住区域(单位宿舍):", item.dwss.toString()))
                            tvDatas1.add(TextViewsEntity("就业人口居住区域(本村):", item.bc.toString()))
                            tvDatas1.add(TextViewsEntity("就业人口居住区域(其他):", item.qt.toString()))

                            tllItemYztDcCzlb.setValueTexts(tvDatas1,arrayOf(0,1,4,9,12,13,16,17,19,20,21,22,25))
                            llItemYztDcCzlb!!.setOnClickListener {
                                if (llItemYztDcCzlb1!!.isShown){
                                    llItemYztDcCzlb1.visibility = View.GONE
                                    ivItemYztDcCzlb.setImageResource(R.drawable.back_right_icon)
                                }else{
                                    llItemYztDcCzlb1.visibility = View.VISIBLE
                                    ivItemYztDcCzlb.setImageResource(R.drawable.back_down_icon)

                                }
                            }


                        }
                    }
*/
                }else{
                    if (YLPoint.yzt!=null&&YLPoint.yzt.size>0){//(YLPoint.ylList.size==0||!YLPoint.ylList.get(0).fl2.equals("宅基地"))
                        ll_jcxx_qyxx.visibility = View.VISIBLE
                        ll_jcxx_qyxx2.visibility = View.VISIBLE
//                ll_jcxx_zjxx.visibility = View.VISIBLE

                        val tvDatas = ArrayList<TextViewsEntity>()
                        val yztEntity = YLPoint.yzt.get(0)
                        /*if (yztEntity.landType.equals("01")&&yztEntity.fl2.equals("宅基地")){
                            //宅基地点击查询  不需要写

                        }else if (yztEntity.landType.equals("03")&&yztEntity.fl2.equals("企业")){
                            //企业点击查询  不需要写

                        }else*/ if ((yztEntity.landType.equals("01")&&!yztEntity.fl2.equals("宅基地"))||(yztEntity.landType.equals("03")&&!yztEntity.fl2.equals("企业"))||yztEntity.landType.equals("05")){
                            //其他地上物点击查询(只到二级分类)
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("名称:", YLPoint.yzt.get(0)?.name))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                            tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            if (YLPoint.yzt.get(0)?.jzmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                            }else{
                                tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj.toString()+"㎡"))
                            }
                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,1,2))

                        }else if (yztEntity.landType.equals("02")){
                            //其他地上物点击查询(到三级分类)
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("名称:", YLPoint.yzt.get(0)?.name))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                            tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            tvDatas.add(TextViewsEntity("三级分类:", YLPoint.yzt.get(0)?.fl3))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            if (YLPoint.yzt.get(0)?.jzmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                            }else{
                                tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj.toString()+"㎡"))
                            }
                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,1,2,5))
                        }else if (yztEntity.landType.equals("07")&&yztEntity.fl2.equals("其他林地")){
                            //林地点击查询
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                            tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            tvDatas.add(TextViewsEntity("种植类型:", ""))
                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3))
                        }else if (yztEntity.landType.equals("07")&&yztEntity.fl2.equals("绿化项目")){
                            //绿化点击查询
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                            tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
//                            tvDatas.add(TextViewsEntity("项目类型:", ""))
                            tvDatas.add(TextViewsEntity("项目名称:", YLPoint.yzt.get(0)?.name))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3,4,5))//0,3,4,5
                        }else if (yztEntity.landType.equals("08")&&yztEntity.fl2.equals("农业项目")){
                            //农业项目点查
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                            tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                            tvDatas.add(TextViewsEntity("项目名称:", YLPoint.yzt.get(0)?.name))
                            tvDatas.add(TextViewsEntity("单位名称:", ""))
                            tvDatas.add(TextViewsEntity("行业:", ""))
                            tvDatas.add(TextViewsEntity("项目编号:", ""))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("用地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("用地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            tvDatas.add(TextViewsEntity("生产设施亩:", ""))
                            tvDatas.add(TextViewsEntity("附属设施㎡:", ""))
                            tvDatas.add(TextViewsEntity("联系人:", ""))
                            tvDatas.add(TextViewsEntity("电话:", ""))
                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3,4,7,12))
                        }else if (yztEntity.landType.equals("08")&&yztEntity.fl2.equals("土地整治项目")){
                            //土地整治项目
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                            tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                            tvDatas.add(TextViewsEntity("项目名称:", YLPoint.yzt.get(0)?.name))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("项目面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("项目面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            tvDatas.add(TextViewsEntity("新增耕地面积:", ""))
                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3,4))
                        }else if (yztEntity.landType.equals("08")&&!yztEntity.fl2.equals("农业项目")&&!yztEntity.fl2.equals("土地整治项目")){
                            //农用地点击查询
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                            tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            tvDatas.add(TextViewsEntity("种植类型:", ""))
                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,5))
                        }else{
                            tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                            tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
//                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                            tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
//                        tvDatas.add(TextViewsEntity("项目类型:", ""))
                            if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                                tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                            }else{
                                tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                            }
                            /* if (YLPoint.yzt.get(0)?.jzmj!!.compareTo(BigDecimal(10000))==1){
                                 tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                             }else{
                                 tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj.toString()+"㎡"))
                             }*/

                            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                            tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3))
                        }
                    }
                }

            }
        }else{
            //企业
            if (YLPoint.enterprise!=null){
                ll_jcxx_qyxx.visibility = View.VISIBLE
                ll_jcxx_zjxx.visibility = View.GONE
//            tv_jcxx_qyxx.setText("产业用地信息")
                ll_jcxx_tuxian.visibility = View.VISIBLE
                tab_jcxx_qyxx.removeAllTabs()
                tab_jcxx_qyxx.visibility = View.VISIBLE
                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("用地管理"))
                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业经营"))
                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("承租列表"))
                ll_jcxx_qyxx2.visibility = View.VISIBLE
                /* if (YLPoint.landinfo.size>0){
     //                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业列表"))
                 }*/
                val enterprise = YLPoint.enterprise
                val enterpriseInfoEntities = enterprise.enterpriseInfoEntitie//enterpriseInfoEntities
                val tvDatas = ArrayList<TextViewsEntity>()
               /* tvDatas.add(TextViewsEntity("现状企业名称:", enterprise.name))
                tvDatas.add(TextViewsEntity("现状土地使用人:", enterprise.fwcqr.toString()))
                tvDatas.add(TextViewsEntity("占地面积:", enterprise.zdmj.toString()))
                tvDatas.add(TextViewsEntity("建筑面积:", enterprise.jzmj.toString()))
                if (enterpriseInfoEntities!=null){//.size>0
                    val enterpriseInfo = enterpriseInfoEntities
                    tvDatas.add(TextViewsEntity("土地使用人:", enterpriseInfo.tdsyr.toString()))
                    tvDatas.add(TextViewsEntity("土地性质:", enterpriseInfo.tdxz.toString()))
                    tvDatas.add(TextViewsEntity("权属情况:", enterpriseInfo.qs.toString()))
                    tvDatas.add(TextViewsEntity("土地取得年限:", enterpriseInfo.tdqddate.toString()))
                    tvDatas.add(TextViewsEntity("土地剩余使用年限:", enterpriseInfo.tdsynx.toString()))
                    tvDatas.add(TextViewsEntity("土地价格(万元/亩):", enterpriseInfo.tdjg.toString()))
                    tvDatas.add(TextViewsEntity("土地证面积(公顷):", enterpriseInfo.tdzmj.toString()))
                    tvDatas.add(TextViewsEntity("土地取得方式:", enterpriseInfo.tdqdfs.toString()))
                    ll_jcxx_zjxx_zjzl.visibility = View.VISIBLE
                    rlv_jcxx_zjxx.layoutManager = GridLayoutManager(activity,3)//enterpriseInfo.enterpriseFileEntities
                    rlv_jcxx_zjxx.adapter = object : BaseQuickAdapter<EnterpriseFileEntity,BaseViewHolder>(R.layout.item_jcxx_tp,enterprise.enterpriseFileEntities){
                        override fun convert(helper: BaseViewHolder?, item: EnterpriseFileEntity?) {
                            val icoId: Int = GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path))
                            val ivItemJcxxPic = helper!!.getView<ImageView>(R.id.iv_item_jcxx_pic)
                            Glide.with(BaseApplication.getAppContext()).load(icoId)
                                    .into(ivItemJcxxPic)//.fitCenter()
                            helper!!.setText(R.id.tv_item_jcxx_name,item.filename)
                            helper.itemView.setOnClickListener {
                                val pic = item.url//ApiConstants.BASE_URL + "tdzFile/" + item.folderid + "/" + item.path.trim()//YLPoint.ylList.get(0).tdzmc
                                val intent = Intent(context, PDFActivity::class.java)
                                intent.putExtra("pdf", pic.trim())
                                startActivity(intent)
                            }
                        }
                    }

                }*/
                tvDatas.add(TextViewsEntity("编号:", enterprise.bh))
                tvDatas.add(TextViewsEntity("现状企业名称:", enterprise.name))
                tvDatas.add(TextViewsEntity("土地使用人:", enterprise.tdsyr))
                tvDatas.add(TextViewsEntity("用地权属:", enterprise.qs))
                tvDatas.add(TextViewsEntity("占地面积:", enterprise.zdmj.toString()))
                tvDatas.add(TextViewsEntity("建筑面积:", enterprise.jzmj.toString()))
                val enterpriseBuilding1 = enterprise.enterpriseBuilding

                tvDatas.add(TextViewsEntity("房屋产权单位:", enterpriseBuilding1.fwcqdw))
                tvDatas.add(TextViewsEntity("已建建筑面积(㎡):", enterpriseBuilding1.yjjzmj.toString()))
                tvDatas.add(TextViewsEntity("有无地下空间:", enterpriseBuilding1.dxkj))
                tvDatas.add(TextViewsEntity("空置建筑面积(㎡):", enterpriseBuilding1.kzjzmj.toString()))
                tvDatas.add(TextViewsEntity("闲置楼宇面积(㎡):", enterpriseBuilding1.fwcqdw))
                tvDatas.add(TextViewsEntity("空置厂房建筑面积(㎡):", enterpriseBuilding1.kzcfjzmj.toString()))
                tvDatas.add(TextViewsEntity("是否分期建设:", enterpriseBuilding1.fqjsText))
                tvDatas.add(TextViewsEntity("未建建筑面积(㎡):", enterpriseBuilding1.wjjzmj.toString()))
                tvDatas.add(TextViewsEntity("空置厂房建设情况:", enterpriseBuilding1.kzcfjsqk))
                tvDatas.add(TextViewsEntity("转让情况:", enterpriseBuilding1.zrqkText))

                if (enterprise.enterpriseFileEntities.size>0){//.size>0
                    ll_jcxx_zjxx_zjzl.visibility = View.VISIBLE
                    for (i in enterprise.enterpriseInfoEntities){
                        enterprise.enterpriseFileEntities.addAll(i.enterpriseFileEntities)
                    }
                    rlv_jcxx_zjxx.layoutManager = GridLayoutManager(activity,3)//enterpriseInfo.enterpriseFileEntities
                    rlv_jcxx_zjxx.adapter = object : BaseQuickAdapter<EnterpriseFileEntity,BaseViewHolder>(R.layout.item_jcxx_tp,enterprise.enterpriseFileEntities){
                        override fun convert(helper: BaseViewHolder?, item: EnterpriseFileEntity?) {
                            val icoId: Int = GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path))
                            val ivItemJcxxPic = helper!!.getView<ImageView>(R.id.iv_item_jcxx_pic)
                            Glide.with(BaseApplication.getAppContext()).load(icoId)
                                    .into(ivItemJcxxPic)//.fitCenter()
                            helper!!.setText(R.id.tv_item_jcxx_name,item.filename)
                            helper.itemView.setOnClickListener {
                                val pic = item.url//ApiConstants.BASE_URL + "tdzFile/" + item.folderid + "/" + item.path.trim()//YLPoint.ylList.get(0).tdzmc
                                val intent = Intent(context, PDFActivity::class.java)
                                intent.putExtra("pdf", pic.trim())
                                startActivity(intent)
                            }
                        }
                    }
                }else{
                    ll_jcxx_zjxx_zjzl.visibility = View.GONE
                }
                tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                tvs_frag_yzt_dc_qyxx2.setValueTexts(tvDatas,arrayOf(0,1,2,5,6,7,8,9,10,11,12,13,14))//,4
                rlv_act_qygl_yzt_dc_ydqk.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                if (enterprise!!.enterpriseInfoEntities.size>0){
                    hsv_frag_yzt_dc_zjxx.visibility = View.VISIBLE
                }
                var ydqkAdapter = object : BaseQuickAdapter<EnterpriseInfoEntity, BaseViewHolder>(R.layout.item_text_list_ydqk, enterprise!!.enterpriseInfoEntities) {
                    override fun convert(helper: BaseViewHolder?, item: EnterpriseInfoEntity?) {
                        helper!!.setText(R.id.tv_qygl_item_list_xh, "${helper.adapterPosition + 1}")
                                .setText(R.id.tv_qygl_item_list_tdsyr, item!!.tdsyr)
                                .setText(R.id.tv_qygl_item_list_tdxz, item!!.tdxz)
                                .setText(R.id.tv_qygl_item_list_qsqk, item!!.qs)
                                .setText(R.id.tv_qygl_item_list_tdqdnx, item!!.tdqddate)
                                .setText(R.id.tv_qygl_item_list_tdsynx, item!!.tdsynx)
                                .setText(R.id.tv_qygl_item_list_tdjg, item!!.tdjg)
                                .setText(R.id.tv_qygl_item_list_tdzmj, item!!.tdzmj.toString())
                                .setText(R.id.tv_qygl_item_list_tdqdfs, item!!.tdqdfs)
                        helper.getView<TextView>(R.id.tv_qygl_item_list_wjsc).visibility = View.GONE
                        helper.getView<TextView>(R.id.tv_qygl_item_list_delete).visibility = View.GONE
                    }
                }
                rlv_act_qygl_yzt_dc_ydqk.adapter = ydqkAdapter
                val enterpriseBuilding = enterprise.enterpriseBuilding
                val tvDatas1 = ArrayList<TextViewsEntity>()
                /*tvDatas1.add(TextViewsEntity("房屋产权单位:", enterpriseBuilding.fwcqdw.toString()))
                tvDatas1.add(TextViewsEntity("已建建筑面积:", enterpriseBuilding.yjjzmj.toString()+"㎡"))
                tvDatas1.add(TextViewsEntity("空置建筑面积:", enterpriseBuilding.kzjzmj.toString()+"㎡"))
                tvDatas1.add(TextViewsEntity("空置厂房建筑面积:", enterpriseBuilding.kzcfjzmj.toString()+"㎡"))
                tvDatas1.add(TextViewsEntity("未建建筑面积:", enterpriseBuilding.wjjzmj.toString()+"㎡"))
                tvDatas1.add(TextViewsEntity("闲置楼宇面积:", enterpriseBuilding.xzlymj.toString()+"㎡"))
                tvDatas1.add(TextViewsEntity("是否分期建设:", enterpriseBuilding.fqjsText.toString()))
                tvDatas1.add(TextViewsEntity("有无地下空间:", enterpriseBuilding.dxkj.toString()))
                tvDatas1.add(TextViewsEntity("转让情况:", enterpriseBuilding.zrqkText.toString()))
                tvDatas1.add(TextViewsEntity("空置厂房建设情况:", enterpriseBuilding.kzcfjsqk.toString()))
                val enterpriseEntity = enterprise.enterpriseEntity
                if (enterpriseEntity!=null){
                    tvDatas1.add(TextViewsEntity("现状企业名称:", enterpriseEntity.qyname.toString()))
                    tvDatas1.add(TextViewsEntity("行政联系人:", enterpriseEntity.xzlxr.toString()))
                    tvDatas1.add(TextViewsEntity("高新企业:", enterpriseEntity.gxqyText.toString()))
                    tvDatas1.add(TextViewsEntity("企业类型:", enterpriseEntity.hylxText))
                    tvDatas1.add(TextViewsEntity("注册地址:", enterpriseEntity.zcdz.toString()))
                    tvDatas1.add(TextViewsEntity("联系电话:", enterpriseEntity.lxdh.toString()))
                    tvDatas1.add(TextViewsEntity("实际经营地址:", enterpriseEntity.sjjydz.toString()))
                    if (enterpriseEntity.czqk==0){
                        tvDatas1.add(TextViewsEntity("是否转租:", "否"))
                    }else{
                        tvDatas1.add(TextViewsEntity("是否转租:", "是"))
                    }
                    tvDatas1.add(TextViewsEntity("营业执照注册日期:", enterpriseEntity.zcdate.toString()))
                    tvDatas1.add(TextViewsEntity("税务登记证注册地:", enterpriseEntity.swzcdz.toString()))
                    tvDatas1.add(TextViewsEntity("企业类型:", enterpriseEntity.qylxText.toString()))
                    tvDatas1.add(TextViewsEntity("注册资金:", enterpriseEntity.zczj.toString()))
                    tvDatas1.add(TextViewsEntity("入驻日期:", enterpriseEntity.rzdate.toString()))
                    tvDatas1.add(TextViewsEntity("行业类型:", enterpriseEntity.hylxText.toString()))
                    tvDatas1.add(TextViewsEntity("经营状态:", enterpriseEntity.jyztText.toString()))
                    tvDatas1.add(TextViewsEntity("经营收入:", enterpriseEntity.yysr.toString()))
                    tvDatas1.add(TextViewsEntity("利润总额:", enterpriseEntity.lrze.toString()))
                    tvDatas1.add(TextViewsEntity("产值:", enterpriseEntity.cz.toString()))
                    tvDatas1.add(TextViewsEntity("税收:", enterpriseEntity.hj.toString()))
                    tvDatas1.add(TextViewsEntity("本地员工数量:", enterpriseEntity.bdgrs.toString()))
                    tvDatas1.add(TextViewsEntity("外地员工数量:", enterpriseEntity.wdgrs.toString()))
                    tvDatas1.add(TextViewsEntity("就业人口居住区域(单位宿舍):", enterpriseEntity.dwss.toString()))
                    tvDatas1.add(TextViewsEntity("就业人口居住区域(本村):", enterpriseEntity.bc.toString()))
                    tvDatas1.add(TextViewsEntity("就业人口居住区域(其他):", enterpriseEntity.qt.toString()))
                }*/
                val enterpriseEntity1 = enterprise.enterpriseEntity
                tvDatas1.add(TextViewsEntity("现状企业名称:", enterpriseEntity1.qyname.toString()))
                tvDatas1.add(TextViewsEntity("注册地址:", enterpriseEntity1.zcdz.toString()))
                tvDatas1.add(TextViewsEntity("实际经营地址:", enterpriseEntity1.sjjydz.toString()))
                tvDatas1.add(TextViewsEntity("入住日期:", enterpriseEntity1.rzdate.toString()))
                tvDatas1.add(TextViewsEntity("营业执照注册日期:", enterpriseEntity1.zcdate.toString()))
                tvDatas1.add(TextViewsEntity("税务登记注册地:", enterpriseEntity1.swzcdz.toString()))

                tvDatas1.add(TextViewsEntity("企业类型:", enterpriseEntity1.qylxText.toString()))
                tvDatas1.add(TextViewsEntity("注册类型:", enterpriseEntity1.zclxText.toString()))
                tvDatas1.add(TextViewsEntity("行业类型:", enterpriseEntity1.hylxText.toString()))
                tvDatas1.add(TextViewsEntity("注册资金:", enterpriseEntity1.zczj.toString()))
                tvDatas1.add(TextViewsEntity("行政联系人:", enterpriseEntity1.xzlxr.toString()))
                tvDatas1.add(TextViewsEntity("联系电话:", enterpriseEntity1.lxdh.toString()))
                if (enterpriseEntity1.czqk==0){
                    tvDatas1.add(TextViewsEntity("是否转租:", "否"))
                }else{
                    tvDatas1.add(TextViewsEntity("是否转租:", "是"))
                }
                tvDatas1.add(TextViewsEntity("高新企业:", enterpriseEntity1.gxqyText.toString()))
                tvs_frag_yzt_dc_qyxx3.visibility = View.VISIBLE
                tvs_frag_yzt_dc_qyxx3.setValueTexts(tvDatas1,arrayOf(0,1,4,9,12,13,16,17,19,20,21,22,25))
                val tvDatas2 = ArrayList<TextViewsEntity>()
                val enterpriseBusinesse = enterpriseEntity1.enterpriseBusinesse
                tvDatas2.add(TextViewsEntity("利润总额:", enterpriseBusinesse.lrze.toString()))
                tvDatas2.add(TextViewsEntity("营业收入:", enterpriseBusinesse.yysr.toString()))
                tvDatas2.add(TextViewsEntity("税收:", enterpriseBusinesse.hj.toString()))
                tvDatas2.add(TextViewsEntity("产值:", enterpriseBusinesse.cz.toString()))
                tvDatas2.add(TextViewsEntity("经营状态:", enterpriseBusinesse.jyztText.toString()))
                tvDatas2.add(TextViewsEntity("本地员工数量:", enterpriseBusinesse.bdgrs.toString()))
                tvDatas2.add(TextViewsEntity("外地工人数量:", enterpriseBusinesse.wdgrs.toString()))
                tvDatas2.add(TextViewsEntity("就业人口居住区域单位宿舍:", enterpriseBusinesse.dwss.toString()))
                tvDatas2.add(TextViewsEntity("就业人口居住区域本村:", enterpriseBusinesse.bc.toString()))
                tvDatas2.add(TextViewsEntity("就业人口居住区域其他:", enterpriseBusinesse.qt.toString()))

                tll_frag_yzt_dc_jyqk.setValueTexts(tvDatas2,arrayOf(4,5,6,7,8,9))
                mtl_frag_yzt_dc_jyqk.setAddText("")
                if (enterpriseEntity1.id!=null){
                    mPresenter.getBusiness(enterpriseEntity1.id, "")//id
                }
                mtl_frag_yzt_dc_jyqk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                    override fun onClick(name: String?) {
                        if (name.equals("添加")){
                            if (SingleOnClickUtil1.isFastClick()){
                                JyqkPickerUtil.initPickerView(activity,"经营历史列表",stringList,mtl_frag_yzt_dc_jyqk.addText,object:JyqkPickerUtil.OnPickerLinerar{
                                    override fun onClick(name: String?) {
                                        mtl_frag_yzt_dc_jyqk.setAddText(name)
                                        mPresenter.getBusiness(enterpriseEntity1!!.id, name!!)
                                    }
                                })
                            }
                                /*TimePickerUtil.initTimePickerViewNy(activity,mtl_frag_yzt_dc_jyqk.addText.toString(),object: TimePickerUtil.OnTimePickerLister{
                                    override fun onClick(data: String?) {
                                        //时间筛选
                                        mtl_frag_yzt_dc_jyqk.setAddText(data)
                                        mPresenter.getBusiness(enterpriseEntity1.id, data!!)//id
                                    }
                                })*/
                        }else{
                            if (tll_frag_yzt_dc_jyqk.isShown){
                                tll_frag_yzt_dc_jyqk.visibility = View.GONE
                            }else{
                                tll_frag_yzt_dc_jyqk.visibility = View.VISIBLE
                            }
                        }
                    }
                })


                tv_jcxx_qyxx.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
                    override fun onClick(name:String) {
                        if (ll_jcxx_qyxx1.isShown){
                            ll_jcxx_qyxx1.visibility = View.GONE
                        }else{
                            ll_jcxx_qyxx1.visibility = View.VISIBLE
                        }                }
                })
                rlv_frag_yzt_dc_czlb.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                rlv_frag_yzt_dc_czlb.adapter = object:BaseQuickAdapter<EnterpriseEntity,BaseViewHolder>(R.layout.item_qygl_detail_czdwmc,enterprise.enterpriseEntities){
                    override fun convert(helper: BaseViewHolder?, item: EnterpriseEntity?) {
                        helper!!.setText(R.id.tv_item_qygl_detail_czdw_qymc, item!!.qyname)
                                .setText(R.id.tv_item_qygl_detail_czdw_lxr, item!!.xzlxr)
                                .setText(R.id.tv_item_qygl_detail_czdw_zclx, item!!.zcdz)

                        helper!!.itemView.setOnClickListener {
                            if (SingleOnClickUtil1.isFastClick()){
                                val intent = Intent(activity, CzdwDetailActivity::class.java)
                                intent.putExtra("enterpriseEntity",item)
                                intent.putExtra("isUpdate",false)
                                startActivity(intent)
                            }
                        }
                    }
                }

                /*rlv_frag_yzt_dc_czlb.adapter = object:BaseQuickAdapter<EnterpriseEntity, BaseViewHolder>(R.layout.item_yzt_dc_czlb, enterprise.enterpriseEntities){
                    override fun convert(helper: BaseViewHolder?, item: EnterpriseEntity?) {
                        val llItemYztDcCzlb = helper!!.getView<LinearLayout>(R.id.ll_item_yzt_dc_czlb)
                        val llItemYztDcCzlb1 = helper!!.getView<LinearLayout>(R.id.ll_item_yzt_dc_czlb1)
                        val ivItemYztDcCzlb = helper!!.getView<ImageView>(R.id.iv_item_yzt_dc_czlb)
                        helper.setText(R.id.tv_item_yzt_dc_czlb_xzqymc, item!!.qyname)
                                .setText(R.id.tv_item_yzt_dc_czlb_xzlxr, item!!.xzlxr)
                                .setText(R.id.tv_item_yzt_dc_czlb_zcdz, item!!.zcdz)
                                .setText(R.id.tv_item_yzt_dc_czlb_jyzt, item!!.jyztText)
                        val tllItemYztDcCzlb = helper.getView<TextListLayout>(R.id.tll_item_yzt_dc_czlb)
                        val tvDatas1 = ArrayList<TextViewsEntity>()
                        tvDatas1.add(TextViewsEntity("现状企业名称:", item.qyname.toString()))
                        tvDatas1.add(TextViewsEntity("行政联系人:", item.xzlxr.toString()))
                        tvDatas1.add(TextViewsEntity("高新企业:", item.gxqyText.toString()))
                        tvDatas1.add(TextViewsEntity("注册类型:", item.zclxText.toString()))
                        tvDatas1.add(TextViewsEntity("注册地址:", item.zcdz.toString()))
                        tvDatas1.add(TextViewsEntity("联系电话:", item.lxdh.toString()))
                        tvDatas1.add(TextViewsEntity("实际经营地址:", item.sjjydz.toString()))
                        if (item.czqk==0){
                            tvDatas1.add(TextViewsEntity("是否转租:", "否"))
                        }else{
                            tvDatas1.add(TextViewsEntity("是否转租:", "是"))
                        }
                        tvDatas1.add(TextViewsEntity("营业执照注册日期:", item.zcdate.toString()))
                        tvDatas1.add(TextViewsEntity("税务登记注册地:", item.swzcdz.toString()))
                        tvDatas1.add(TextViewsEntity("企业类型:", item.qylxText.toString()))
                        tvDatas1.add(TextViewsEntity("注册资金:", item.zczj.toString()))
                        tvDatas1.add(TextViewsEntity("入住日期:", item.rzdate.toString()))
                        tvDatas1.add(TextViewsEntity("行业类型:", item.hylxText.toString()))
                        tvDatas1.add(TextViewsEntity("经营状态:", item.jyztText.toString()))
                        tvDatas1.add(TextViewsEntity("营业收入:", item.yysr.toString()))
                        tvDatas1.add(TextViewsEntity("利润总额:", item.lrze.toString()))
                        tvDatas1.add(TextViewsEntity("产值:", item.cz.toString()))
                        tvDatas1.add(TextViewsEntity("税收:", item.hj.toString()))
                        tvDatas1.add(TextViewsEntity("本地员工数量:", item.bdgrs.toString()))
                        tvDatas1.add(TextViewsEntity("外地工人数量:", item.wdgrs.toString()))
                        tvDatas1.add(TextViewsEntity("就业人口居住区域(单位宿舍):", item.dwss.toString()))
                        tvDatas1.add(TextViewsEntity("就业人口居住区域(本村):", item.bc.toString()))
                        tvDatas1.add(TextViewsEntity("就业人口居住区域(其他):", item.qt.toString()))

                        tllItemYztDcCzlb.setValueTexts(tvDatas1,arrayOf(0,1,4,9,12,13,16,17,19,20,21,22,25))
                        llItemYztDcCzlb!!.setOnClickListener {
                            if (llItemYztDcCzlb1!!.isShown){
                                llItemYztDcCzlb1.visibility = View.GONE
                                ivItemYztDcCzlb.setImageResource(R.drawable.back_right_icon)
                            }else{
                                llItemYztDcCzlb1.visibility = View.VISIBLE
                                ivItemYztDcCzlb.setImageResource(R.drawable.back_down_icon)

                            }
                        }


                    }
                }
*/
            }else{
                if (YLPoint.yzt!=null&&YLPoint.yzt.size>0){//(YLPoint.ylList.size==0||!YLPoint.ylList.get(0).fl2.equals("宅基地"))
                    ll_jcxx_qyxx.visibility = View.VISIBLE
                    ll_jcxx_qyxx2.visibility = View.VISIBLE
//                ll_jcxx_zjxx.visibility = View.VISIBLE

                    val tvDatas = ArrayList<TextViewsEntity>()
                    val yztEntity = YLPoint.yzt.get(0)
                    /*if (yztEntity.landType.equals("01")&&yztEntity.fl2.equals("宅基地")){
                        //宅基地点击查询  不需要写

                    }else if (yztEntity.landType.equals("03")&&yztEntity.fl2.equals("企业")){
                        //企业点击查询  不需要写

                    }else*/ if ((yztEntity.landType.equals("01")&&!yztEntity.fl2.equals("宅基地"))||
                            (yztEntity.landType.equals("03")&&!yztEntity.fl2.equals("企业"))||yztEntity.landType.equals("05")){
                        //其他地上物点击查询(只到二级分类)
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("名称:", YLPoint.yzt.get(0)?.name))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                        if (YLPoint.yzt.get(0)?.jzmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                        }else{
                            tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj.toString()+"㎡"))
                        }
                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,1,2))

                    }else if (yztEntity.landType.equals("02")){
                        //其他地上物点击查询(到三级分类)
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("名称:", YLPoint.yzt.get(0)?.name))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        tvDatas.add(TextViewsEntity("三级分类:", YLPoint.yzt.get(0)?.fl3))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                        if (YLPoint.yzt.get(0)?.jzmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                        }else{
                            tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj.toString()+"㎡"))
                        }
                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,1,2,5))
                    }else if (yztEntity.landType.equals("07")&&yztEntity.fl2.equals("其他林地")){
                        //林地点击查询
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                        tvDatas.add(TextViewsEntity("种植类型:", ""))
                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3))
                    }else if (yztEntity.landType.equals("07")&&yztEntity.fl2.equals("绿化项目")){
                        //绿化点击查询
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
//                        tvDatas.add(TextViewsEntity("项目类型:", ""))
                        tvDatas.add(TextViewsEntity("项目名称:", YLPoint.yzt.get(0)?.name))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3,4,5))//0,3,4,5
                    }else if (yztEntity.landType.equals("08")&&yztEntity.fl2.equals("农业项目")){
                        //农业项目点查
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                        tvDatas.add(TextViewsEntity("项目名称:", YLPoint.yzt.get(0)?.name))
                        tvDatas.add(TextViewsEntity("单位名称:", ""))
                        tvDatas.add(TextViewsEntity("行业:", ""))
                        tvDatas.add(TextViewsEntity("项目编号:", ""))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("用地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("用地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                        tvDatas.add(TextViewsEntity("生产设施亩:", ""))
                        tvDatas.add(TextViewsEntity("附属设施㎡:", ""))
                        tvDatas.add(TextViewsEntity("联系人:", ""))
                        tvDatas.add(TextViewsEntity("电话:", ""))
                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3,4,7,12))
                    }else if (yztEntity.landType.equals("08")&&yztEntity.fl2.equals("土地整治项目")){
                        //土地整治项目
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                        tvDatas.add(TextViewsEntity("项目名称:", YLPoint.yzt.get(0)?.name))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("项目面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("项目面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                        tvDatas.add(TextViewsEntity("新增耕地面积:", ""))
                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3,4))
                    }else if (yztEntity.landType.equals("08")&&!yztEntity.fl2.equals("农业项目")&&!yztEntity.fl2.equals("土地整治项目")){
                        //农用地点击查询
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                        tvDatas.add(TextViewsEntity("种植类型:", ""))
                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,5))
                    }else{
                        tvDatas.add(TextViewsEntity("村名:", YLPoint.yzt.get(0)?.xzqmc))
                        tvDatas.add(TextViewsEntity("一级分类:", YLPoint.yzt.get(0)?.landName))
//                        tvDatas.add(TextViewsEntity("二级分类:", YLPoint.yzt.get(0)?.fl2))
                        tvDatas.add(TextViewsEntity("权属:", YLPoint.yzt.get(0)?.qs))
//                        tvDatas.add(TextViewsEntity("项目类型:", ""))
                        if (YLPoint.yzt.get(0)?.zdmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                        }else{
                            tvDatas.add(TextViewsEntity("占地面积:", YLPoint.yzt.get(0)?.zdmj.toString()+"㎡"))
                        }
                       /* if (YLPoint.yzt.get(0)?.jzmj!!.compareTo(BigDecimal(10000))==1){
                            tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                        }else{
                            tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.yzt.get(0)?.jzmj.toString()+"㎡"))
                        }*/

                        tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                        tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,3))
                    }
                }
            }
        }

        if (YLPoint.feiZhaiList.size>0){
            /*ll_jcxx_qyxx.visibility = View.VISIBLE
            ll_jcxx_zjxx.visibility = View.GONE
//            tv_jcxx_qyxx.setText("产业用地信息")

            tab_jcxx_qyxx.removeAllTabs()
            tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("用地管理"))
            tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业经营"))
            tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业列表"))
            if (YLPoint.landinfo.size>0){
                ll_jcxx_qyxx2.visibility = View.VISIBLE
//                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业列表"))
            }//YLPoint.yztfz


            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("权属:", YLPoint.feiZhaiList.get(0).tdxz))
            tvDatas.add(TextViewsEntity("土地使用人:", YLPoint.feiZhaiList.get(0).tdsyr.toString()))
            tvDatas.add(TextViewsEntity("地上物所有权人:", YLPoint.feiZhaiList.get(0).dswsyq.toString()))
            tvDatas.add(TextViewsEntity("名称:", YLPoint.feiZhaiList.get(0).qiyeName.toString()))
            tvDatas.add(TextViewsEntity("占地面积:", YLPoint.feiZhaiList.get(0).zdmj.toString()+"㎡"))
            tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.feiZhaiList.get(0).jzmj.toString()+"㎡"))
            tvDatas.add(TextViewsEntity("房屋建成年代:", YLPoint.feiZhaiList.get(0).fwjcnd.toString()))
//            tvDatas.add(TextViewsEntity("用地期限:", YLPoint.feiZhaiList.get(0).ydqx.toString()))
            tvDatas.add(TextViewsEntity("备注:", YLPoint.feiZhaiList.get(0).text.toString()))
            tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
            tvs_frag_yzt_dc_qyxx2.setValueTexts(tvDatas,arrayOf(0,3))


            val tvDatas1 = ArrayList<TextViewsEntity>()
            tvDatas1.add(TextViewsEntity("企业名称:", YLPoint.feiZhaiList.get(0).qiyeName.toString()))
            tvDatas1.add(TextViewsEntity("营业执照:", YLPoint.feiZhaiList.get(0).yyzzh.toString()))
            tvDatas1.add(TextViewsEntity("央企/国企/市属:", YLPoint.feiZhaiList.get(0).qyxz.toString()))
            tvDatas1.add(TextViewsEntity("高薪企业:", YLPoint.feiZhaiList.get(0).gxqy.toString()))
            tvDatas1.add(TextViewsEntity("注册地址:", YLPoint.feiZhaiList.get(0).zcdz.toString()))
            tvDatas1.add(TextViewsEntity("是否上市:", YLPoint.feiZhaiList.get(0).ifss.toString()))
            tvDatas1.add(TextViewsEntity("法人姓名:", YLPoint.feiZhaiList.get(0).frxm.toString()))
            tvDatas1.add(TextViewsEntity("联系方式:", YLPoint.feiZhaiList.get(0).lxfs.toString()))
            tvDatas1.add(TextViewsEntity("注册资金(万元):", YLPoint.feiZhaiList.get(0).zczj.toString()))
            tvDatas1.add(TextViewsEntity("外资企业注册资金(万元):", YLPoint.feiZhaiList.get(0).wzzc.toString()))
            tvDatas1.add(TextViewsEntity("公司员工(本市):", YLPoint.feiZhaiList.get(0).gsbs.toString()))
            tvDatas1.add(TextViewsEntity("公司员工(外阜):", YLPoint.feiZhaiList.get(0).gswq.toString()))
            tvDatas1.add(TextViewsEntity("就业人口居住地(单位宿舍):", YLPoint.feiZhaiList.get(0).jydw.toString()))
            tvDatas1.add(TextViewsEntity("就业人口居住地(各村):", YLPoint.feiZhaiList.get(0).jygc.toString()))
            tvDatas1.add(TextViewsEntity("经营状态:", YLPoint.feiZhaiList.get(0).jystatus.toString()))
            tvDatas1.add(TextViewsEntity("企业负责人:", YLPoint.feiZhaiList.get(0).qyfzr.toString()))
            tvDatas1.add(TextViewsEntity("联系方式:", YLPoint.feiZhaiList.get(0).fzrlxfs.toString()))
            tvDatas1.add(TextViewsEntity("邮箱地址:", YLPoint.feiZhaiList.get(0).yxdz.toString()))
            tvDatas1.add(TextViewsEntity("行业类别:", YLPoint.feiZhaiList.get(0).industry.toString()))
            tvDatas1.add(TextViewsEntity("主要产品:", YLPoint.feiZhaiList.get(0).zycp.toString()))
            tvDatas1.add(TextViewsEntity("2019营业收入(万元):", YLPoint.feiZhaiList.get(0).yysr2019.toString()))
            tvDatas1.add(TextViewsEntity("2019工业资产总值(万元):", YLPoint.feiZhaiList.get(0).cz2019.toString()))
            tvDatas1.add(TextViewsEntity("经营地址:", YLPoint.feiZhaiList.get(0).jydz.toString()))
            tvDatas1.add(TextViewsEntity("使用面积(㎡):", YLPoint.feiZhaiList.get(0).symj.toString()))
            tvDatas1.add(TextViewsEntity("出租面积(㎡):", YLPoint.feiZhaiList.get(0).czmj.toString()))
            tvDatas1.add(TextViewsEntity("剩余面积(㎡):", YLPoint.feiZhaiList.get(0).yxmj.toString()))
            tvs_frag_yzt_dc_qyxx3.visibility = View.VISIBLE
            tvs_frag_yzt_dc_qyxx3.setValueTexts(tvDatas1,arrayOf(0,1,4,9,12,13,16,17,19,20,21,22,25))

            tv_jcxx_qyxx.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
                override fun onClick(name:String) {
                    if (ll_jcxx_qyxx1.isShown){
                        ll_jcxx_qyxx1.visibility = View.GONE
                    }else{
                        ll_jcxx_qyxx1.visibility = View.VISIBLE
                    }                }
            })*/

        }else{
           /* if (YLPoint.landinfo.size>0){
//                tab_jcxx_qyxx.removeAllTabs()
//                ll_jcxx_qyxx.visibility = View.VISIBLE
//                ll_jcxx_zjxx.visibility = View.VISIBLE
//                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业列表"))
            }else{
                ll_jcxx_qyxx.visibility = View.GONE
            }
            tab_jcxx_qyxx.removeAllTabs()
            tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("用地管理"))
            tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业经营"))
            tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业列表"))
            if (YLPoint.ylList.size>0)
            if (!YLPoint.ylList.get(0).fl2.equals("宅基地")&&YLPoint.feiZhaiList.size==0){//
                ll_jcxx_qyxx.visibility = View.VISIBLE
                ll_jcxx_qyxx2.visibility = View.VISIBLE
//                ll_jcxx_zjxx.visibility = View.VISIBLE

                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("名称:", YLPoint.ylList.get(0)?.name))
                tvDatas.add(TextViewsEntity("村名:", YLPoint.ylList.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("一级分类:", YLPoint.ylList.get(0)?.ylTypeText))
                tvDatas.add(TextViewsEntity("二级分类:", YLPoint.ylList.get(0)?.fl2))
                tvDatas.add(TextViewsEntity("土地使用性质(权属):", YLPoint.ylList.get(0)?.qs))
//                tvDatas.add(TextViewsEntity("类型:", ylPointEntity.ylList.get(0)?.ylTypeText))
//                tvDatas.add(TextViewsEntity("产权人:", ylPointEntity.ylList.get(0)?.cqr))
//                tvDatas.add(TextViewsEntity("户主名称:", ylPointEntity.ylList.get(0)?.hzmc))
                if (YLPoint.ylList.get(0)?.area!!.compareTo(BigDecimal(10000))==1){
                    tvDatas.add(TextViewsEntity("占地面积:", YLPoint.ylList.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", YLPoint.ylList.get(0)?.area.toString()+"㎡"))
                }
                if (YLPoint.ylList.get(0)?.jianzhuArea!!.compareTo(BigDecimal(10000))==1){
                    tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.ylList.get(0)?.jianzhuArea!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.ylList.get(0)?.jianzhuArea.toString()+"㎡"))
                }
//            tvDatas.add(TextViewsEntity("建筑面积(万㎡):", ylPointEntity.ylList.get(0)?.dlmc))
//            tvDatas.add(TextViewsEntity("证件序号:", ylPointEntity.ylList.get(0)?.bh))
                tvDatas.add(TextViewsEntity("备注:", YLPoint.ylList.get(0)?.remark))
                tvs_frag_yzt_dc_qyxx2.visibility = View.VISIBLE
                tvs_frag_yzt_dc_qyxx2?.setValueTexts(tvDatas,arrayOf(0,1,4,9))

            }else{
//                ll_jcxx_gy.visibility = View.GONE
            }*/

        }
        tab_jcxx_qyxx.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0!!.text.toString().equals("用地管理")){
                    ll_jcxx_qyxx2.visibility = View.VISIBLE
                    ll_jcxx_qyxx3.visibility = View.GONE
                    ll_jcxx_zjxx.visibility = View.GONE
                    ll_jcxx_tuxian.visibility = View.VISIBLE

                }else if (p0!!.text.toString().equals("企业经营")) {
                    ll_jcxx_qyxx2.visibility = View.GONE
                    ll_jcxx_qyxx3.visibility = View.VISIBLE
                    ll_jcxx_zjxx.visibility = View.GONE
                    ll_jcxx_tuxian.visibility = View.GONE
                }else{
                    ll_jcxx_qyxx2.visibility = View.GONE
                    ll_jcxx_qyxx3.visibility = View.GONE
                    ll_jcxx_zjxx.visibility = View.VISIBLE
                    ll_jcxx_tuxian.visibility = View.GONE
                }
            }
        })
        tab_jcxx_tuxian.removeAllTabs()
        tab_jcxx_tuxian.addTab(tab_jcxx_tuxian.newTab().setText("土现2009"))
        tab_jcxx_tuxian.addTab(tab_jcxx_tuxian.newTab().setText("土现2018"))
        if (!AppCache.getInstance().code.equals("110111009022")){
            tab_jcxx_tuxian.addTab(tab_jcxx_tuxian.newTab().setText("土现2020"))
        }

        tab_jcxx_tuxian.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text.toString().equals("土现2009")){
                    rlv_jcxx_tuxian.visibility = View.VISIBLE
                    rlv_jcxx_tuxian2018.visibility = View.GONE
                    rlv_jcxx_tuxian2020.visibility = View.GONE
                }else if (tab!!.text.toString().equals("土现2018")){
                    rlv_jcxx_tuxian.visibility = View.GONE
                    rlv_jcxx_tuxian2018.visibility = View.VISIBLE
                    rlv_jcxx_tuxian2020.visibility = View.GONE
                }else if (tab!!.text.toString().equals("土现2020")){
                    rlv_jcxx_tuxian.visibility = View.GONE
                    rlv_jcxx_tuxian2018.visibility = View.GONE
                    rlv_jcxx_tuxian2020.visibility = View.VISIBLE
                }
            }
        })
        tab_jcxx_tuxian.visibility = View.GONE
        tab_jcxx_tuxian.getTabAt(1)!!.select()
        //rlv_jcxx_zjxx
        /*if (YLPoint.landinfo.size>0){

            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("企业现状名称:", YLPoint.landinfo.get(0).qyname.toString()))
            tvDatas.add(TextViewsEntity("土地使用者:", YLPoint.landinfo.get(0).tdsyz.toString()))
            tvDatas.add(TextViewsEntity("土地用途:", YLPoint.landinfo.get(0).tdyt.toString()))
            tvDatas.add(TextViewsEntity("使用权类型:", YLPoint.landinfo.get(0).syqlx.toString()))
            tvDatas.add(TextViewsEntity("使用权面积:", YLPoint.landinfo.get(0).syqmj.toString()))
            tvDatas.add(TextViewsEntity("独用面积:", YLPoint.landinfo.get(0).area.toString()))
            tvDatas.add(TextViewsEntity("终止日期:", YLPoint.landinfo.get(0).zzdate.toString()))
            tvs_frag_yzt_dc_zjxx.visibility = View.VISIBLE
            tvs_frag_yzt_dc_zjxx.setValueTexts(tvDatas,arrayOf(0,1,2))
            ll_jcxx_zjxx_zjzl.visibility = View.VISIBLE
            rlv_jcxx_zjxx.layoutManager = GridLayoutManager(activity,3)
            rlv_jcxx_zjxx.adapter = object : BaseQuickAdapter<LandFileEntity,BaseViewHolder>(R.layout.item_jcxx_tp,YLPoint.tdzFile){
                override fun convert(helper: BaseViewHolder?, item: LandFileEntity?) {
                    val icoId: Int = GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path))
                    val ivItemJcxxPic = helper!!.getView<ImageView>(R.id.iv_item_jcxx_pic)
                    Glide.with(BaseApplication.getAppContext()).load(icoId)
                            .into(ivItemJcxxPic)//.fitCenter()
                    helper!!.setText(R.id.tv_item_jcxx_name,item.name)
                    helper.itemView.setOnClickListener {
                        val pic = ApiConstants.BASE_URL + "tdzFile/" + item.folderid + "/" + item.path.trim()//YLPoint.ylList.get(0).tdzmc
                        val intent = Intent(context, PDFActivity::class.java)
                        intent.putExtra("pdf", pic.trim())
                        startActivity(intent)
                    }
                }
            }
        }*/ //        }
        if (YLPoint.yztHzEntities.size>0) {
            point_dkxx_ll.visibility = View.VISIBLE
            point_dkxx_hsv.visibility = View.VISIBLE
            point_dkxx_zwsj.visibility = View.GONE
            tv_point_dkxx_tx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (point_dkxx_hsv.visibility == View.GONE) {
                        point_dkxx_hsv.visibility = View.VISIBLE
                    } else {
                        point_dkxx_hsv.visibility = View.GONE
                    }
                }
            })

            point_dkxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            point_dkxx_rlv.adapter = object : BaseQuickAdapter<TdlyEntity, BaseViewHolder>(R.layout.item_pointjcxx_tx_rlv, YLPoint.yztHzEntities) {
                override fun convert(helper: BaseViewHolder, item: TdlyEntity) {
                    if (item.area1.compareTo(BigDecimal(10000))==1 ){
                        helper.setText(R.id.pointjcxx_tx_mj, ""+item.area.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP)+"万公顷")
                    }else{
                        helper.setText(R.id.pointjcxx_tx_mj, ""+item.area+"公顷")
                    }
                    helper.setText(R.id.pointjcxx_tx_cm, item.xzqmc)
                            .setText(R.id.pointjcxx_tx_dlbm, item.lx)
                            .setText(R.id.pointjcxx_tx_qsxz, item.qsxz)
                            .setText(R.id.pointjcxx_tx_dlmc, item.layer)
                }
            }
        } else {
            point_dkxx_ll.visibility = View.GONE
            /*point_dkxx_hsv.visibility = View.GONE
            point_dkxx_zwsj.visibility = View.VISIBLE*/
        }
        /*if (YLPoint.floatPopulats.size>0){
            ll_jcxx_ldrk.visibility = View.VISIBLE
            tv_jcxx_ldrk.setOnClickListener {
                if (ll_jcxx_ldrk1.isShown){
                    ll_jcxx_ldrk1.visibility = View.GONE
                }else{
                    ll_jcxx_ldrk1.visibility = View.VISIBLE
                }
            }
            rlv_jcxx_ldrk.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            val ldrkSelectAdapter = LdrkSelectAdapter(context, YLPoint.floatPopulats)
            rlv_jcxx_ldrk.adapter = ldrkSelectAdapter
            ldrkSelectAdapter.setOnLdrkSelectLinear(object :LdrkSelectAdapter.OnLdrkSelectLinear{
                override fun onDeleteClick(floatPopulat: FloatPopulat?, positon:Int) {
//                    getLdrkDelete(floatPopulat!!.id)
                }

                override fun onClick(floatPopulat: FloatPopulat?, positon:Int) {
//                    getLdrkDetail(floatPopulat!!.ylEntity.center1)
                }
            })

        }else{
            ll_jcxx_ldrk.visibility = View.GONE
        }*/
        if (YLPoint.qs.size>0){//qs
            ll_jcxx_quanshu.visibility = View.VISIBLE
            tv_jcxx_quanshu.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (ll_jcxx_quanshu1.isShown){
                        ll_jcxx_quanshu1.visibility = View.GONE
                    }else{
                        ll_jcxx_quanshu1.visibility = View.VISIBLE
                    }
                }
            })
            rlv_jcxx_quanshu.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            rlv_jcxx_quanshu.adapter = object:BaseQuickAdapter<QsEntity,BaseViewHolder>(R.layout.item_yzt_quanshu,YLPoint.qs){
                override fun convert(helper: BaseViewHolder?, item: QsEntity?) {
                    helper!!.setText(R.id.tv_yzt_qs_qsdw, item?.zldwmc)
                            .setText(R.id.tv_yzt_qs_qs, item?.zldwmc)
                            .setText(R.id.tv_yzt_qs_mj, item?.area.toString())

                }
            }

        }else{
            ll_jcxx_quanshu.visibility = View.GONE

        }
        if (YLPoint.tdly2009.size>0){//tdly
            ll_jcxx_tuxian.visibility = View.VISIBLE
            tv_jcxx_tuxian.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (ll_jcxx_tuxian1.isShown){
                        ll_jcxx_tuxian1.visibility = View.GONE
                    }else{
                        ll_jcxx_tuxian1.visibility = View.VISIBLE
                    }
                }
            })
            rlv_jcxx_tuxian.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            rlv_jcxx_tuxian.adapter = object:BaseQuickAdapter<TdlyEntity,BaseViewHolder>(R.layout.item_yzt_tuxian,YLPoint.tdly2009){
                override fun convert(helper: BaseViewHolder?, item: TdlyEntity?) {
                    helper!!.setText(R.id.tv_yzt_tx_cm, item?.xzqmc)
                            .setText(R.id.tv_yzt_tx_dlbm, item?.dlbm)
                            .setText(R.id.tv_yzt_tx_dlmc, item?.dlmc.toString())
                            .setText(R.id.tv_yzt_tx_mj, item?.area1.toString()+"亩")
                    val view = helper.getView<LinearLayout>(R.id.ll_item_yzt_tuxina)
                    if (item!!.xzqmc.equals("总计")){
                        view.setBackgroundColor(Color.parseColor("#331AF0E1"))
                        helper.getView<TextView>(R.id.tv_yzt_tx_dlbm).visibility = View.GONE
                        helper.getView<TextView>(R.id.tv_yzt_tx_dlmc).visibility = View.GONE
                    }
                }
            }

        }
        if (YLPoint.tdly2018.size>0){//tdly
            ll_jcxx_tuxian.visibility = View.VISIBLE
            tv_jcxx_tuxian.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (ll_jcxx_tuxian1.isShown){
                        ll_jcxx_tuxian1.visibility = View.GONE
                    }else{
                        ll_jcxx_tuxian1.visibility = View.VISIBLE
                    }
                }
            })
            rlv_jcxx_tuxian2018.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            rlv_jcxx_tuxian2018.adapter = object:BaseQuickAdapter<TdlyEntity,BaseViewHolder>(R.layout.item_yzt_tuxian,YLPoint.tdly2018){
                override fun convert(helper: BaseViewHolder?, item: TdlyEntity?) {
                    helper!!.setText(R.id.tv_yzt_tx_cm, item?.xzqmc)
                            .setText(R.id.tv_yzt_tx_dlbm, item?.dlbm)
                            .setText(R.id.tv_yzt_tx_dlmc, item?.dlmc.toString())
                            .setText(R.id.tv_yzt_tx_mj, item?.area1.toString()+"亩")
                    val view = helper.getView<LinearLayout>(R.id.ll_item_yzt_tuxina)
                    if (item!!.xzqmc.equals("总计")){
                        view.setBackgroundColor(Color.parseColor("#331AF0E1"))
                        helper.getView<TextView>(R.id.tv_yzt_tx_dlbm).visibility = View.GONE
                        helper.getView<TextView>(R.id.tv_yzt_tx_dlmc).visibility = View.GONE
                    }
                }
            }

        }
        if (YLPoint.tdly2020.size>0){//tdly
            ll_jcxx_tuxian.visibility = View.VISIBLE
            tv_jcxx_tuxian.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (ll_jcxx_tuxian1.isShown){
                        ll_jcxx_tuxian1.visibility = View.GONE
                    }else{
                        ll_jcxx_tuxian1.visibility = View.VISIBLE
                    }
                }
            })
            rlv_jcxx_tuxian2020.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            rlv_jcxx_tuxian2020.adapter = object:BaseQuickAdapter<TdlyEntity,BaseViewHolder>(R.layout.item_yzt_tuxian,YLPoint.tdly2020){
                override fun convert(helper: BaseViewHolder?, item: TdlyEntity?) {
                    helper!!.setText(R.id.tv_yzt_tx_cm, item?.xzqmc)
                            .setText(R.id.tv_yzt_tx_dlbm, item?.dlbm)
                            .setText(R.id.tv_yzt_tx_dlmc, item?.dlmc.toString())
                            .setText(R.id.tv_yzt_tx_mj, item?.area1.toString()+"亩")
                    val view = helper.getView<LinearLayout>(R.id.ll_item_yzt_tuxina)
                    if (item!!.xzqmc.equals("总计")){
                        view.setBackgroundColor(Color.parseColor("#331AF0E1"))
                        helper.getView<TextView>(R.id.tv_yzt_tx_dlbm).visibility = View.GONE
                        helper.getView<TextView>(R.id.tv_yzt_tx_dlmc).visibility = View.GONE
                    }
                }
            }

        }
        if (YLPoint.tdLYEntity.size>0){//tdly
//            ll_jcxx_tuxian.visibility = View.VISIBLE
            tv_jcxx_tuxian.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (ll_jcxx_tuxian1.isShown){
                        ll_jcxx_tuxian1.visibility = View.GONE
                    }else{
                        ll_jcxx_tuxian1.visibility = View.VISIBLE
                    }
                }
            })
            rlv_jcxx_tuxian.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            rlv_jcxx_tuxian.adapter = object:BaseQuickAdapter<TdlyEntity,BaseViewHolder>(R.layout.item_yzt_tuxian,YLPoint.tdLYEntity){
                override fun convert(helper: BaseViewHolder?, item: TdlyEntity?) {
                    helper!!.setText(R.id.tv_yzt_tx_cm, item?.xzqmc)
                            .setText(R.id.tv_yzt_tx_dlbm, item?.dlbm)
                            .setText(R.id.tv_yzt_tx_dlmc, item?.dlmc.toString())
                            .setText(R.id.tv_yzt_tx_mj, item?.area1.toString()+"亩")
                }
            }

        }else{
//            ll_jcxx_tuxian.visibility = View.GONE

        }
        if (YLPoint.yztdp.size>0){//yztdp
            tv_frag_yzt_dc_ssnyzgs.setText("总个数"+YLPoint.yztdpcount.toString())
            tv_frag_yzt_dc_ssnyzmj.setText("总面积(㎡)"+YLPoint.yztdpmj.toString())
            ll_jcxx_dapeng.visibility = View.VISIBLE
            tv_jcxx_dapeng.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (ll_jcxx_dapeng1.isShown){
                        ll_jcxx_dapeng1.visibility = View.GONE
                    }else{
                        ll_jcxx_dapeng1.visibility = View.VISIBLE
                    }
                }
            })
            rlv_jcxx_dapeng.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            rlv_jcxx_dapeng.adapter = object:BaseQuickAdapter<YztDpBean,BaseViewHolder>(R.layout.item_yzt_dapeng,YLPoint.yztdp){
                override fun convert(helper: BaseViewHolder?, item: YztDpBean?) {
                    helper!!.setText(R.id.tv_yzt_dp_xh, "${helper.layoutPosition+1}")
                            .setText(R.id.tv_yzt_dp_jyztmc, item?.jyztmc)
                            .setText(R.id.tv_yzt_dp_sslx, item?.sslx)
                            .setText(R.id.tv_yzt_dp_jssj, item?.jssj)
                            .setText(R.id.tv_yzt_dp_pnmj, item?.ptmj.toString())
                            .setText(R.id.tv_yzt_dp_baqk, item?.baqk)

                }
            }

        }else{
            ll_jcxx_dapeng.visibility = View.GONE

        }

    }

    private var stringList = ArrayList<String>()
    override fun returnBusiness(message: List<EnterpriseBusiness>,month:String) {
        if (month.equals("")){
            stringList.clear()
            for (i in message){
                if (!stringList.contains(i.createDate))
                stringList.add(i.createDate)
            }
        }
        if (message.size>0){
            val enterpriseBisiness = message.get(0)
            val tvDatas2 = ArrayList<TextViewsEntity>()
//        val enterpriseBusinesse = enterpriseEntity1.enterpriseBusinesse
            tvDatas2.add(TextViewsEntity("利润总额:", enterpriseBisiness.lrze.toString()))
            tvDatas2.add(TextViewsEntity("营业收入:", enterpriseBisiness.yysr.toString()))
            tvDatas2.add(TextViewsEntity("税收:", enterpriseBisiness.hj.toString()))
            tvDatas2.add(TextViewsEntity("产值:", enterpriseBisiness.cz.toString()))
            tvDatas2.add(TextViewsEntity("经营状态:", enterpriseBisiness.jyztText.toString()))
            tvDatas2.add(TextViewsEntity("本地员工数量:", enterpriseBisiness.bdgrs.toString()))
            tvDatas2.add(TextViewsEntity("外地工人数量:", enterpriseBisiness.wdgrs.toString()))
            tvDatas2.add(TextViewsEntity("就业人口居住区域单位宿舍:", enterpriseBisiness.dwss.toString()))
            tvDatas2.add(TextViewsEntity("就业人口居住区域本村:", enterpriseBisiness.bc.toString()))
            tvDatas2.add(TextViewsEntity("就业人口居住区域其他:", enterpriseBisiness.qt.toString()))

            tll_frag_yzt_dc_jyqk.setValueTexts(tvDatas2,arrayOf(4,5,6,7,8,9))
        }

    }

    fun txData(result: YztPointEntity) {
        ll_frag_yzt_dc.visibility = View.VISIBLE//点查显示
        ll_frag_yzt_kc.visibility = View.GONE//框查隐藏
        iv_kxxyjbg_ml.visibility = View.GONE
//        tv_frag_yzt_dc_ylxx_ckxq.visibility = View.GONE
        if (result.tdLYEntity .size>0) {
            point_tx_ll.visibility = View.VISIBLE
            point_tx_hsv.visibility = View.VISIBLE
            point_tx_zwsj.visibility = View.GONE
            tv_point_jcxx_tx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (point_tx_hsv.visibility == View.GONE) {
                        point_tx_hsv.visibility = View.VISIBLE
                    } else {
                        point_tx_hsv.visibility = View.GONE
                    }
                }
            })

            point_tx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            point_tx_rlv.adapter = object : BaseQuickAdapter<TdlyEntity, BaseViewHolder>(R.layout.item_pointjcxx_tx_rlv, result.tdLYEntity) {
                override fun convert(helper: BaseViewHolder, item: TdlyEntity) {
                    if (item.area1.compareTo(BigDecimal(1000))==1 ){
                        helper.setText(R.id.pointjcxx_tx_mj, ""+item.area1.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP)+"公顷")
                    }else{
                        helper.setText(R.id.pointjcxx_tx_mj, ""+item.area1+"㎡")
                    }
                    helper.setText(R.id.pointjcxx_tx_cm, item.xzqmc)
                            .setText(R.id.pointjcxx_tx_dlbm, item.dlbm)
                            .setText(R.id.pointjcxx_tx_qsxz, item.lx)
                            .setText(R.id.pointjcxx_tx_dlmc, item.dlmc)
                }
            }
        } else {
            point_tx_ll.visibility = View.GONE
            point_tx_hsv.visibility = View.GONE
            point_tx_zwsj.visibility = View.VISIBLE
        }
        /*if (result != null) {
            if (result.tdLYEntity.size>0){
                pointTdlyEntity = result.tdLYEntity.get(0)
                point_tx_cm_tv.text = pointTdlyEntity!!.xzqmc
                point_tx_mj_tv.text = pointTdlyEntity!!.area.toString()
                point_tx_dlbm_tv.text = pointTdlyEntity!!.dlbm
                point_tx_qsxz_tv.text = pointTdlyEntity!!.qsxz
                point_tx_dlmc_tv.text = pointTdlyEntity!!.dlmc
            }
        } else {
            point_tx_cm_tv.text = "xxx"
            point_tx_mj_tv.text = "xxx"
            point_tx_dlbm_tv.text = "xxx"
            point_tx_qsxz_tv.text = "xxx"
            point_tx_dlmc_tv.text = "xxx"
        }*/
    }

    fun hintPointView(){
        if (tv_frag_yzt_dc_ylxx!=null){


            ll_jcxx_qyxx2.visibility = View.GONE
            ll_jcxx_qyxx3.visibility = View.GONE
            ll_jcxx_zjxx.visibility = View.GONE

            tv_frag_yzt_dc_ylxx.visibility = View.GONE//院落信息
            ll_frag_yzt_dc_ylxx.visibility = View.GONE//院落信息
            ll_jcxx_qyxx.visibility = View.GONE//企业信息
            ll_jcxx_ldrk.visibility = View.GONE//流动人口
            ll_jcxx_dc.visibility = View.GONE//待拆信息
            ll_jcxx_yc.visibility = View.GONE//已拆信息
            ll_jcxx_sd.visibility = View.GONE//湿地信息
            ll_jcxx_lh.visibility = View.GONE//绿化
            ll_jcxx_chaiteng.visibility = View.GONE//拆腾信息
            ll_jcxx_ct.visibility = View.GONE//拆腾信息
            ll_jcxx_sy.visibility = View.GONE//农用地信息
            ll_jcxx_fk.visibility = View.GONE//复垦信息
            ll_jcxx_gy.visibility = View.GONE//国有信息
            ll_jcxx_fz.visibility = View.GONE//非宅信息
            point_dkxx_ll.visibility = View.GONE//地块信息
            point_tx_ll.visibility = View.GONE//土现信息
            ll_point_zjdglxx1.visibility = View.GONE//宅基地管理信息
            point_tg_ll.visibility = View.GONE//空间规划
            point_cg_ll.visibility = View.GONE//城规信息
            ll_jcxx_quanshu.visibility = View.GONE//权属
            ll_jcxx_tuxian.visibility = View.GONE//土现
            ll_jcxx_dapeng.visibility = View.GONE//设施农业信息
        }

    }

    fun tgData(result: TgEntity) {
        if (result != null) {
            point_tg_cm_tv.text = result.xzqmc
            point_tg_dkbm_tv.text = result.gid.toString()
            point_tg_dlmc_tv.text = result.landName
            point_tg_mj_tv.text = result.area.toString()
        } else {
            point_tg_cm_tv.text = "xxx"
            point_tg_dkbm_tv.text = "xxx"
            point_tg_dlmc_tv.text = "xxx"
            point_tg_mj_tv.text = "xxx"
        }
    }

    fun setData(ylPointEntity: YztPointEntity) {
        ll_frag_yzt_dc.visibility = View.VISIBLE//点查显示
        ll_frag_yzt_kc.visibility = View.GONE//框查隐藏
        iv_kxxyjbg_ml.visibility = View.GONE
//        tv_frag_yzt_dc_ylxx_ckxq.visibility = View.GONE
        if (ylPointEntity.yztFhEntity.yztdcEntities.size>0){
            ll_jcxx_dc.visibility = View.VISIBLE
            tv_jcxx_dc.setName("待拆信息")
            tv_jcxx_dc.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (tvs_jcxx_dc.visibility==View.GONE){
                        tvs_jcxx_dc.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_dc.visibility = View.GONE
                    }                }
            })
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.xzqmc))
            tvDatas.add(TextViewsEntity("类型:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.lx))
            if (ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
            }else{
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.area.toString()+"㎡"))
            }

            tvs_jcxx_dc?.setValueTexts(tvDatas,arrayOf(0,1))
//            tvs_jcxx_dc?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_dc.visibility = View.GONE
        }
        if (ylPointEntity.yztFhEntity.yztYcEntities.size>0){
            ll_jcxx_yc.visibility = View.VISIBLE
            tv_jcxx_yc.setName("已拆信息")
            tv_jcxx_yc.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (tvs_jcxx_yc.visibility==View.GONE){
                        tvs_jcxx_yc.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_yc.visibility = View.GONE
                    }
                }
            })
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.xzqmc))
            tvDatas.add(TextViewsEntity("类型:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.lx))
            if (ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
            }else{
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.area.toString()+"㎡"))
            }
            tvs_jcxx_yc?.setValueTexts(tvDatas,arrayOf(0,1))
//            tvs_jcxx_yc?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_yc.visibility = View.GONE
        }
        if (ylPointEntity.yztFhEntity.yztSdgyEnties.size>0){
            ll_jcxx_sd.visibility = View.VISIBLE
            tv_jcxx_sd.setName("湿地信息")
            tv_jcxx_sd.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (tvs_jcxx_sd.visibility==View.GONE){
                        tvs_jcxx_sd.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_sd.visibility = View.GONE
                    }                }
            })
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.xzqmc))
            tvDatas.add(TextViewsEntity("成交时间:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.cjrq))
            if (ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
            }else{
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.area.toString()+"㎡"))
            }
            tvs_jcxx_sd?.setValueTexts(tvDatas,arrayOf(0,1))
//            tvs_jcxx_sd?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_sd.visibility = View.GONE
        }
        if (ylPointEntity.yztLh.size>0){//之前的绿化 yztFhEntity.yztLhEntities
            ll_jcxx_lh.visibility = View.VISIBLE
            tv_jcxx_lh.setName("绿化信息")
            tv_jcxx_lh.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (tvs_jcxx_lh.visibility==View.GONE){
                        tvs_jcxx_lh.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_lh.visibility = View.GONE
                    }                }
            })
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztLh.get(0)?.xzqmc))
            tvDatas.add(TextViewsEntity("林地名称:", ylPointEntity.yztLh.get(0)?.layer))
            if (ylPointEntity.yztLh.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztLh.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
            }else{
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztLh.get(0)?.area.toString()+"㎡"))
            }
            tvDatas.add(TextViewsEntity("土地现状:", ylPointEntity.yztLh.get(0)?.tdxz))
            tvDatas.add(TextViewsEntity("地块编号:", ylPointEntity.yztLh.get(0)?.dkbh))
            tvDatas.add(TextViewsEntity("备注:", ylPointEntity.yztLh.get(0)?.remark))

            tvs_jcxx_lh?.setValueTexts(tvDatas,arrayOf(0,1,6))
//            tvs_jcxx_lh?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_lh.visibility = View.GONE
        }
        if (ylPointEntity.yztLh.size>0){//现在的绿化
            ll_jcxx_lh.visibility = View.VISIBLE
            tv_jcxx_lh.setName(ylPointEntity.yztLh.get(0)?.lx+"信息")
            tv_jcxx_lh.setOnClickListener {
                if (tvs_jcxx_lh.visibility==View.GONE){
                    tvs_jcxx_lh.visibility = View.VISIBLE
                }else{
                    tvs_jcxx_lh.visibility = View.GONE
                }
            }
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("项目名称:", ylPointEntity.yztLh.get(0)?.layer))
            tvDatas.add(TextViewsEntity("项目类型:", ylPointEntity.yztLh.get(0)?.lx))
            tvDatas.add(TextViewsEntity("面积:", ylPointEntity.yztLh.get(0)?.area.toString()+"亩"))
            /* if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                 tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
             }else{
                 tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
             }*/

            tvs_jcxx_lh?.setValueTexts(tvDatas,arrayOf(0))
//            tvs_jcxx_lh?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_lh.visibility = View.GONE
        }
        if (ylPointEntity.yztcctt!=null){//拆腾信息
            ll_jcxx_chaiteng.visibility = View.VISIBLE
            tv_jcxx_chaiteng.setName("拆腾信息")
            tv_jcxx_chaiteng.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (tvs_jcxx_chaiteng.visibility==View.GONE){
                        tvs_jcxx_chaiteng.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_chaiteng.visibility = View.GONE
                    }                }
            })
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("产权人:", ylPointEntity.yztcctt?.name))
            tvDatas.add(TextViewsEntity("是否拆除:", ylPointEntity.yztcctt?.sfcc))
            tvDatas.add(TextViewsEntity("占地面积(㎡):", ylPointEntity.yztcctt?.area.toString()))
            tvDatas.add(TextViewsEntity("建筑面积(㎡):", ylPointEntity.yztcctt?.jzmj.toString()))



            /* if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                 tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
             }else{
                 tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
             }*/

            tvs_jcxx_chaiteng?.setValueTexts(tvDatas,arrayOf(0,1))
//            tvs_jcxx_chaiteng?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_chaiteng.visibility = View.GONE
        }
        if (ylPointEntity.yztCt.size>0){//拆腾
            ll_jcxx_ct.visibility = View.VISIBLE
            tv_jcxx_ct.setName(ylPointEntity.yztCt.get(0)?.lx+"信息")
            tv_jcxx_ct.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (tvs_jcxx_ct.visibility==View.GONE){
                        tvs_jcxx_ct.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_ct.visibility = View.GONE
                    }                }

            })
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztCt.get(0)?.xzqmc))
            tvDatas.add(TextViewsEntity("编号:", ylPointEntity.yztCt.get(0)?.layer))
            tvDatas.add(TextViewsEntity("产权人:", ylPointEntity.yztCt.get(0)?.cqr))
            tvDatas.add(TextViewsEntity("拆除状态:", ylPointEntity.yztCt.get(0)?.lx))
            tvDatas.add(TextViewsEntity("面积:", ylPointEntity.yztCt.get(0)?.area.toString()+"亩"))
            tvDatas.add(TextViewsEntity("备注:", ylPointEntity.yztCt.get(0)?.remark))
            /* if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                 tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
             }else{
                 tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
             }*/


            tvs_jcxx_ct?.setValueTexts(tvDatas,arrayOf(0,5))
//            tvs_jcxx_ct?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_ct.visibility = View.GONE
        }
        if (ylPointEntity.yztSy.size>0){// ylPointEntity.yztSy
            ll_jcxx_sy.visibility = View.VISIBLE
            tv_jcxx_sy.setName(ylPointEntity.yztSy.get(0)?.layer+"信息")
            tv_jcxx_sy.setOnClickListener {
                if (tvs_jcxx_sy.visibility==View.GONE){
                    tvs_jcxx_sy.visibility = View.VISIBLE
                }else{
                    tvs_jcxx_sy.visibility = View.GONE
                }
            }
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztSy.get(0)?.xzqmc))
            tvDatas.add(TextViewsEntity("面积:", ylPointEntity.yztSy.get(0)?.area.toString()+"亩"))
            tvDatas.add(TextViewsEntity("现状使用情况:", ylPointEntity.yztSy.get(0)?.layer))
            /*if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
            }else{
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
            }*/


            tvs_jcxx_sy?.setValueTexts(tvDatas,arrayOf(0,1,2))
//            tvs_jcxx_sy?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_sy.visibility = View.GONE
        }
        if (ylPointEntity.yztFhEntity.yztFkEntities.size>0){
            ll_jcxx_fk.visibility = View.VISIBLE
            tv_jcxx_fk.setName(ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.dlmc+"信息")
            tv_jcxx_fk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
                override fun onClick(name: String?) {
                    if (tvs_jcxx_fk.visibility==View.GONE){
                        tvs_jcxx_fk.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_fk.visibility = View.GONE
                    }
                }
            })
            val tvDatas = ArrayList<TextViewsEntity>()
            tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.jfmc))
            tvDatas.add(TextViewsEntity("地块编号:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.dkbh))
            if (ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
            }else{
                tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.area.toString()+"㎡"))
            }
            tvDatas.add(TextViewsEntity("地类名称:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.dlmc))
            tvDatas.add(TextViewsEntity("分类名称:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.flmc))
            tvDatas.add(TextViewsEntity("是否整治:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.sfzz))
            tvDatas.add(TextViewsEntity("整治年份:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.zznf.toString()))
            tvDatas.add(TextViewsEntity("现状情况:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.xzqk))
            tvDatas.add(TextViewsEntity("备注:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.remark))

            tvs_jcxx_fk?.setValueTexts(tvDatas,arrayOf(0,1))
//            tvs_jcxx_fk?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        }else{
            ll_jcxx_fk.visibility = View.GONE
        }
        if (ylPointEntity.ylList.size>0){
            ll_jcxx_gy.visibility = View.GONE
            ll_jcxx_fz.visibility = View.GONE
           /* if (!ylPointEntity.ylList.get(0).fl2.equals("宅基地")&&ylPointEntity.feiZhaiList.size==0){//

                ll_jcxx_gy.visibility = View.VISIBLE
                tv_jcxx_gy.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
                    override fun onClick(name: String?) {
                        if (tvs_jcxx_gy.visibility==View.GONE){
                            tvs_jcxx_gy.visibility = View.VISIBLE
                        }else{
                            tvs_jcxx_gy.visibility = View.GONE
                        }
                    }
                })


                tab_jcxx_gyxx.removeAllTabs()
                tab_jcxx_gyxx.addTab(tab_jcxx_gyxx.newTab().setText("用地管理"))
                tab_jcxx_gyxx.addTab(tab_jcxx_gyxx.newTab().setText("企业经营"))
                tab_jcxx_gyxx.addTab(tab_jcxx_gyxx.newTab().setText("企业列表"))

                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("名称:", ylPointEntity.ylList.get(0)?.name))
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.ylList.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("一级分类:", ylPointEntity.ylList.get(0)?.ylTypeText))
                tvDatas.add(TextViewsEntity("二级分类:", ylPointEntity.ylList.get(0)?.fl2))
                tvDatas.add(TextViewsEntity("土地使用性质(权属):", ylPointEntity.ylList.get(0)?.qs))
//                tvDatas.add(TextViewsEntity("类型:", ylPointEntity.ylList.get(0)?.ylTypeText))
//                tvDatas.add(TextViewsEntity("产权人:", ylPointEntity.ylList.get(0)?.cqr))
//                tvDatas.add(TextViewsEntity("户主名称:", ylPointEntity.ylList.get(0)?.hzmc))
                if (ylPointEntity.ylList.get(0)?.area!!.compareTo(BigDecimal(10000))==1){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"公顷"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area.toString()+"㎡"))
                }
                if (ylPointEntity.ylList.get(0)?.jianzhuArea!!.compareTo(BigDecimal(10000))==1){
                    tvDatas.add(TextViewsEntity("建筑面积:", ylPointEntity.ylList.get(0)?.jianzhuArea!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("建筑面积:", ylPointEntity.ylList.get(0)?.jianzhuArea.toString()+"㎡"))
                }
//            tvDatas.add(TextViewsEntity("建筑面积(万㎡):", ylPointEntity.ylList.get(0)?.dlmc))
//            tvDatas.add(TextViewsEntity("证件序号:", ylPointEntity.ylList.get(0)?.bh))
                tvDatas.add(TextViewsEntity("备注:", ylPointEntity.ylList.get(0)?.remark))

                tvs_jcxx_gy?.setValueTexts(tvDatas,arrayOf(0,1,4,9))
//                tvs_jcxx_gy?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

                tab_jcxx_gyxx.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                    override fun onTabReselected(p0: TabLayout.Tab?) {

                    }

                    override fun onTabUnselected(p0: TabLayout.Tab?) {
                    }

                    override fun onTabSelected(p0: TabLayout.Tab?) {
                        if (p0!!.text.toString().equals("用地管理")){
                            tvs_jcxx_gy.visibility = View.VISIBLE
                            ll_jcxx_tuxian.visibility = View.VISIBLE
                        }else if (p0!!.text.toString().equals("企业经营")) {
                            tvs_jcxx_gy.visibility = View.GONE
                            ll_jcxx_tuxian.visibility = View.GONE
                        }else{
                            tvs_jcxx_gy.visibility = View.GONE
                            ll_jcxx_tuxian.visibility = View.GONE
                        }
                    }
                })

            }else{
                ll_jcxx_gy.visibility = View.GONE
            }*/
            /*if (ylPointEntity.ylList.get(0).ylTypeText.equals("非宅")&&ylPointEntity.feiZhaiList.size==0){

                ll_jcxx_fz.visibility = View.VISIBLE
                ll_jcxx_fz.setOnClickListener {
                    if (tvs_jcxx_fz.visibility==View.GONE){
                        tvs_jcxx_fz.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_fz.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.ylList.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("类型:", ylPointEntity.ylList.get(0)?.ylTypeText))
                if (ylPointEntity.ylList.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area.toString()+"㎡"))
                }
//            tvDatas.add(TextViewsEntity("建筑面积(万㎡):", ylPointEntity.ylList.get(0)?.dlmc))
                tvDatas.add(TextViewsEntity("名称:", ylPointEntity.ylList.get(0)?.name))
                tvDatas.add(TextViewsEntity("证件序号:", ylPointEntity.ylList.get(0)?.bh))
                tvDatas.add(TextViewsEntity("备注:", ylPointEntity.ylList.get(0)?.remark))

                tvs_jcxx_fz?.setDatas(tvDatas)
                tvs_jcxx_fz?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_fz.visibility = View.GONE
            }*/
        }else{
            ll_jcxx_gy.visibility = View.GONE
            ll_jcxx_fz.visibility = View.GONE
        }
        var zjdglList = ArrayList<ZjdglBean>()
        if (ylPointEntity.applyEntity.size>0) {//ylPointEntity.applyEntity.id != 0
            for (i in ylPointEntity.applyEntity){
                zjdglList.add(ZjdglBean(i.name, i.iphone, i.sptypeText))
            }
        }
        if (ylPointEntity.zjdSqEntity.size>0) {//ylPointEntity.zjdSqEntity.id != 0
            for (i in ylPointEntity.zjdSqEntity){
                zjdglList.add(ZjdglBean(i.sqName, i.iphone, i.sptypeText))
            }
        }
        if (ylPointEntity.zjdLzEntity.size>0) {//ylPointEntity.zjdLzEntity.id != 0
            for (i in ylPointEntity.zjdLzEntity){
                zjdglList.add(ZjdglBean(i.sqName, i.iphone, i.sptypeText))
            }
        }
        if (ylPointEntity.zjdTcEntity.size>0) {
            for (i in ylPointEntity.zjdTcEntity){
                zjdglList.add(ZjdglBean(i.sqName, i.iphone, i.sptypeText))
            }
        }
        point_zjdglxx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_point_zjdglxx.visibility == View.GONE) {
                    ll_point_zjdglxx.visibility = View.VISIBLE
                } else {
                    ll_point_zjdglxx.visibility = View.GONE
                }
            }
        })
        if (zjdglList.size == 0) {
            point_zjdglxx.visibility = View.GONE
            ll_point_zjdglxx.visibility = View.GONE
        } else {
            point_zjdglxx.visibility = View.VISIBLE
            ll_point_zjdglxx.visibility = View.VISIBLE
        }
        rlv_zjdglxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rlv_zjdglxx.adapter = object : BaseQuickAdapter<ZjdglBean, BaseViewHolder>(R.layout.item_ywxx_zjdglxx, zjdglList) {
            override fun convert(helper: BaseViewHolder?, item: ZjdglBean?) {
                helper!!.setText(R.id.tv_ywxx_zjdglxx_dsrxm, item!!.dsrxm)
                        .setText(R.id.tv_ywxx_zjdglxx_dsrdh, item!!.dsrdh)
                        .setText(R.id.tv_ywxx_zjdglxx_qk, item!!.qk)
            }

        }
        //环保监察
        point_hbjcxx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_point_hbjcxx.visibility == View.GONE) {
                    ll_point_hbjcxx.visibility = View.VISIBLE
                } else {
                    ll_point_hbjcxx.visibility = View.GONE
                }
            }
        })
        if (ylPointEntity.pjEnviorSupvsEntity.size == 0) {
            point_hbjcxx.visibility = View.GONE
            ll_point_hbjcxx.visibility = View.GONE
        } else {
            point_hbjcxx.visibility = View.VISIBLE
            ll_point_hbjcxx.visibility = View.VISIBLE
        }
        rlv_hbjcxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rlv_hbjcxx.adapter = object : BaseQuickAdapter<PjEnviorSupvsEntity, BaseViewHolder>(R.layout.item_ywxx_hbjcxx, ylPointEntity.pjEnviorSupvsEntity) {
            override fun convert(helper: BaseViewHolder?, item: PjEnviorSupvsEntity?) {
                helper!!.setText(R.id.tv_ywxx_hbjcxx_bh, "")//item!!.projcode
                        .setText(R.id.tv_ywxx_hbjcxx_fpbm, "")//分配问题//item!!.typeText
                        .setText(R.id.tv_ywxx_hbjcxx_jlsj, item!!.jltime)
                if (item!!.warning == 0) {
                    helper.setText(R.id.tv_ywxx_hbjcxx_yj, "正常")
                } else {
                    helper.setText(R.id.tv_ywxx_hbjcxx_yj, "超时")
                }


            }
        }
        point_zjdxcxx.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_point_zjdxcxx.visibility == View.GONE) {
                    ll_point_zjdxcxx.visibility = View.VISIBLE
                } else {
                    ll_point_zjdxcxx.visibility = View.GONE
                }
            }
        })
        if (ylPointEntity.zjdIllegal.size == 0) {
            point_zjdxcxx.visibility = View.GONE
            ll_point_zjdxcxx.visibility = View.GONE
        } else {
            point_zjdxcxx.visibility = View.VISIBLE
            ll_point_zjdxcxx.visibility = View.VISIBLE
        }
        rlv_zjdxcxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rlv_zjdxcxx.adapter = object : BaseQuickAdapter<IllegalEntity, BaseViewHolder>(R.layout.item_ywxx_zjdxcxx, ylPointEntity.zjdIllegal) {
            override fun convert(helper: BaseViewHolder?, item: IllegalEntity?) {
                helper!!.setText(R.id.rv_ywxx_zjdxcxx_cm, item!!.xzqmc)
                        .setText(R.id.rv_ywxx_zjdxcxx_mph, item!!.bh)
                        .setText(R.id.rv_ywxx_zjdxcxx_hzmc, item!!.hzmc)
                        .setText(R.id.rv_ywxx_zjdxcxx_wfqk, item!!.situation)
                        .setText(R.id.rv_ywxx_zjdxcxx_mqzt, item!!.status)
                        .setText(R.id.rv_ywxx_zjdxcxx_remark, item!!.remark)
            }
        }
        point_tdxcxx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_point_tdxcxx.visibility == View.GONE) {
                    ll_point_tdxcxx.visibility = View.VISIBLE
                } else {
                    ll_point_tdxcxx.visibility = View.GONE
                }
            }
        })
        if (ylPointEntity.tdIllegal.size == 0) {
            point_tdxcxx.visibility = View.GONE
            ll_point_tdxcxx.visibility = View.GONE
        } else {
            point_tdxcxx.visibility = View.VISIBLE
            ll_point_tdxcxx.visibility = View.VISIBLE
        }
        rlv_tdxcxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rlv_tdxcxx.adapter = object : BaseQuickAdapter<IllegalEntity, BaseViewHolder>(R.layout.item_ywxx_zjdxcxx, ylPointEntity.tdIllegal) {
            override fun convert(helper: BaseViewHolder?, item: IllegalEntity?) {
                helper!!.setText(R.id.rv_ywxx_zjdxcxx_cm, item!!.xzqmc)
                        .setText(R.id.rv_ywxx_zjdxcxx_mph, item!!.bh)
                        .setText(R.id.rv_ywxx_zjdxcxx_hzmc, item!!.hzmc)
                        .setText(R.id.rv_ywxx_zjdxcxx_wfqk, item!!.situation)
                        .setText(R.id.rv_ywxx_zjdxcxx_mqzt, item!!.status)
                        .setText(R.id.rv_ywxx_zjdxcxx_remark, item!!.remark)
            }

        }

    }


    //*************************-----点查/框查分割线-----*******************************************************************************************************************************************************************

    fun setYztYlKc(data: FrameJcxxBean.DataBean){
        diaoyong()
        detailEntity = data
        var cunList: String = ""
        ll_frag_yzt_dc.visibility = View.GONE//点查隐藏
        ll_frag_yzt_kc.visibility = View.VISIBLE//框查显示
        iv_kxxyjbg_ml.visibility = View.VISIBLE
        if (detailEntity!!.ylEntityList.size>0) {
            for (data in detailEntity!!.ylEntityList) {
                if (data!=null){
                    cunList = cunList + data.xzqmc + "、"
                }
            }

            frame_jcxxnew_sjc.text = cunList.replace("、总计、", "") //涉及村
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
            if (ncount == 0 && feincount == 0) {
                frame_jcxxnew_jbqk_ll.visibility = View.GONE
                jbqk_flag = -1
            } else {
                frame_jcxxnew_jbqk_ll.visibility = View.VISIBLE
                jbqk_flag = 0
            }
        }else{
            frame_jcxxnew_sjc.text = "某某村"
            frame_jcxxnew_zjdzs.text = "xxx宗"
            frame_jcxxnew_zjdjzmj.text = "xxx㎡"
            frame_jcxxnew_zjdzdmj.text = "xxx㎡"
            frame_jcxxnew_zrks.text = "xxx人"
            frame_jcxxnew_zhs.text = "xxx户"
            frame_jcxxnew_rjzjdzmj.text = "xxx㎡"
            frame_jcxxnew_ldrk_rk.text = "xxx人"
            frame_jcxxnew_ldrk_mj.text = "xxx㎡"
            val count = java.util.ArrayList<Float>()
            val countName = java.util.ArrayList<String>()
            count.add(0.toFloat())
            count.add(0.toFloat())
            countName.add("农业人口")
            countName.add("非农业人口")
            rkinitPie(frame_nfn_pct, "户籍人口比", count, countName)

        }
        //房屋柱状图
        if (detailEntity!!.keyValueEntity != null) {
            FWBar(detailEntity!!.keyValueEntity, frame_fwxx_bct)
        }
        //房屋信息
        if (detailEntity!!.ylEntityList.size>0) {
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
            /*if (fdq4count.compareTo(BigDecimal(0)) == 0 && fdq46count.compareTo(BigDecimal(0)) == 0 &&fdq68count.compareTo(BigDecimal(0)) == 0 &&fdq8count.compareTo(BigDecimal(0)) == 0 ){
                frame_jcxxnew_fwxx_ll.visibility = View.GONE
                fwxx_flag = 1
            }else{
                frame_jcxxnew_fwxx_ll.visibility = View.VISIBLE
                fwxx_flag = 0
            }*/

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

        }else{
            val count = java.util.ArrayList<Float>()
            val countName = java.util.ArrayList<String>()
            count.add(0.toFloat())
            count.add(0.toFloat())
            count.add(0.toFloat())
            count.add(0.toFloat())
            countName.add("四分及以下")
            countName.add("四分-六分")
            countName.add("六分-八分")
            countName.add("八分及以上")
            //房屋饼状图
            rkinitPie(frame_fwxx_pct, "宅基地面积宗数占比", count, countName)
            frame_fwxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            frame_fwxx_rlv?.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.YlEntityListBean, BaseViewHolder>(R.layout.item_frame_fwxx_rlv, detailEntity!!.ylEntityList) {
                override fun convert(helper: BaseViewHolder, item: FrameJcxxBean.DataBean.YlEntityListBean) {
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
                    /*if (item!!.lx.equals("总计")) {

                        if (areaCount > 10000.0) {
                            helper!!.setText(R.id.tdlyxzdet_hj, (df.format(areaCount / 10000.0)).toString() + "万㎡")
                        } else {
                            helper!!.setText(R.id.tdlyxzdet_hj, areaCount.toString() + "㎡")
                        }
                    } else {*/
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

        }else{
            var dataList = ArrayList<FrameJcxxBean.DataBean.TdlyEntitiesBean.ChildrenBean>()
            var areaCount: Double = 0.0
            val count = java.util.ArrayList<Float>()
            val countName = java.util.ArrayList<String>()
            TXXXBar(dataList, frame_txxx_bct)
            rkinitPie(frame_txxx_pct, "一级分类占比统计图", count, countName)
            val df = DecimalFormat("#.00")
            frame_txxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            frame_txxx_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.TdlyEntitiesBean, BaseViewHolder>(R.layout.item_tdlyxzdet, detailEntity!!.tdlyEntities) {
                override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.TdlyEntitiesBean?) {
                    /*if (item!!.lx.equals("总计")) {

                        if (areaCount > 10000.0) {
                            helper!!.setText(R.id.tdlyxzdet_hj, (df.format(areaCount / 10000.0)).toString() + "万㎡")
                        } else {
                            helper!!.setText(R.id.tdlyxzdet_hj, areaCount.toString() + "㎡")
                        }
                    } else {*/
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
//空间规划
        //柱状图
        if (detailEntity!!.tgEntityList.size > 0) {
            TGXXBar(detailEntity!!.tgEntityList, frame_tgxx_bct)
        }else{
            TGXXBar(detailEntity!!.tgEntityList, frame_tgxx_bct)
        }
        val df = DecimalFormat("#.00")
        //土规列表
        if (detailEntity!!.tgEntities.size > 0) {//新项目里边没有土规了所以 隐藏掉
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
        if (detailEntity!!.feizhai!=null) {//detailEntity!!.feizhai.size > 0
            FZBar(detailEntity!!.feizhai, frame_fzxx_bct)

            frame_fzxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            frame_fzxx_rlv.adapter = object : BaseQuickAdapter<FrameJcxxBean.DataBean.FeizhaiBean, BaseViewHolder>(R.layout.item_fzxx_rlv, detailEntity!!.feizhai) {
                override fun convert(helper: BaseViewHolder?, item: FrameJcxxBean.DataBean.FeizhaiBean?) {
                    if (item!!.object1.compareTo(BigDecimal(10000)) > 0) {
                        helper!!.setText(R.id.item_fzxx_zdmj, item.object1.divide(BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡")
                    } else {
                        helper!!.setText(R.id.item_fzxx_zdmj, item.object1.setScale(2, RoundingMode.HALF_UP).toString() + "㎡")
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
        if (detailEntity!!.ldrk!=null){//E:\svn\ZHGLXT\app\src\main\java\com\jymj\zhglxt\zjd\fragment\yzt\YztDcFragment.kt
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

        colors.add(ColorTemplate.getHoloBlue())*/

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

        /*   val ssnyzdmj = list.ssnyzdmj
           val ssnyjzmj = list.ssnyjzmj*/

        val listBean = java.util.ArrayList<CJBarChartBean>()

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

            /*  val area3 = message.get(x).list.get(3).area
              yValues3.add(BarEntry(x.toFloat(),area3.toFloat()))*/

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
    private fun TXXXBar(message: java.util.ArrayList<FrameJcxxBean.DataBean.TdlyEntitiesBean.ChildrenBean>, barChart: BarChart) {

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

            /*val area2 = message.get(x).price
            yValues2.add(BarEntry(x.toFloat(), area2.toFloat()))*/

            /* val area3 = message.get(x).list.get(3).area
             yValues3.add(BarEntry(x.toFloat(),area3.toFloat()))*/

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

            /*val area2 = message.get(x).price
            yValues2.add(BarEntry(x.toFloat(), area2.toFloat()))*/

            /* val area3 = message.get(x).list.get(3).area
             yValues3.add(BarEntry(x.toFloat(),area3.toFloat()))*/

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
    private fun FZBar(list: List<FrameJcxxBean.DataBean.FeizhaiBean>, barChart: BarChart) {

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


    fun setYztYlKc1(entity: YeWuFrameBean.DataBean, sb: String){
        diaoyong()
        ll_frag_yzt_dc.visibility = View.GONE//点查隐藏
        ll_frag_yzt_kc.visibility = View.VISIBLE//框查显示
        iv_kxxyjbg_ml.visibility = View.VISIBLE
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

    override fun returnFanJianApplyList(fjBean: List<ApplyEntity>, sptype: Int, count: Int) {

    }

    fun tryScale(current: Float, target: Float) {

        val animator_shadow = ValueAnimator.ofFloat(current, target)
        animator_shadow.duration = 200
        animator_shadow.addUpdateListener { valueAnimator ->
            scale = valueAnimator.animatedValue as Float
            preScale = scale
            ViewHelper.setScaleX(shaderImage, scale)// x方向上缩放
            ViewHelper.setScaleY(shaderImage, scale)// y方向上缩放
        }
        animator_shadow.start()

    }
    inner class ScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {

        override fun onScale(detector: ScaleGestureDetector): Boolean {

            val previousSpan = detector.previousSpan
            val currentSpan = detector.currentSpan
            if (currentSpan < previousSpan) {
                // 缩小
                // scale = preScale-detector.getScaleFactor()/3;
                scale = preScale - (previousSpan - currentSpan) / 1000
                if (scale < 0.5) {
                    scale = 0.5f
                }
            } else {
                // 放大
                // scale = preScale+detector.getScaleFactor()/3;
                scale = preScale + (currentSpan - previousSpan) / 1000
                if (scale > 1.5) {
                    scale = 1.5f
                }
            }

            // 缩放view
            ViewHelper.setScaleX(shaderImage, scale)// x方向上缩小
            ViewHelper.setScaleY(shaderImage, scale)// y方向上缩小
            return false
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            // 一定要返回true才会进入onScale()这个函数

            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            preScale = scale//记录本次缩放比例
            var thisb = 40
            if (scale >= 1.3) {
                thisb = 25
            } else if (scale < 1.3 && scale >= 1.2) {
                thisb = 30
            } else if (scale < 1.2 && scale >= 1.1) {
                thisb = 35
            } else if (scale < 1.1 && scale >= 1.0) {
                thisb = 40
            } else if (scale < 1.0 && scale >= 0.9) {
                thisb = 45
            } else if (scale < 0.9 && scale >= 0.8) {
                thisb = 50
            } else if (scale < 0.8 && scale >= 0.7) {
                thisb = 55
            } else {
                thisb = 55
            }
            if (clickAtmanRelation != null) {
                shaderImage.setHaveClickPic(thisb, false)
                //得到当前所点击的item,拿出他的 子空间和父控件。
                val clickList = java.util.ArrayList<AtmanRelation>()
                clickList.add(clickAtmanRelation!!)
                for (i in sourceList.indices) {
                    for (v in 0 until sourceList[i].parentGroups.size) {
                        if (clickAtmanRelation!!.getGroup() === sourceList[i].parentGroups[v].group) {
                            clickList.add(sourceList[i])
                        }
                    }

                    for (j in 0 until clickAtmanRelation!!.getParentGroups().size) {
                        if (clickAtmanRelation!!.getParentGroups()[j].group === sourceList[i].group) {
                            clickList.add(sourceList[i])
                        }
                    }
                }

                shaderImage.setClickListScale(clickList, clickAtmanRelation!!.getDegree(), clickAtmanRelation!!.getGroup(), clickAtmanRelation)
            } else {
                shaderImage.setHavePic(thisb)
            }

        }
    }
    fun parseData(result: List<ZhaiEntity>): ArrayList<AtmanRelation> {//Gson 解析
        val detail = ArrayList<AtmanRelation>()
        val atmanRelation = AtmanRelation()
        atmanRelation.group = result.get(0).zhaiid
        atmanRelation.userId = "${result.get(0).zhaiid}"
        atmanRelation.lable = "#${result.get(0).zhaiid}"
        atmanRelation.headerUrl = "starsInTheSky/0/0.png"
        atmanRelation.degree = 0
        atmanRelation.sonCount = result.size-1
        atmanRelation.nickName = result.get(0).housecount
        detail.add(atmanRelation)
        if (result.size>1){
            for (i in 1 until  result.size){
                val atmanRelation = AtmanRelation()
                atmanRelation.group = result.get(i).zhaiid
                atmanRelation.userId = "${result.get(i).zhaiid}"
                atmanRelation.lable = "#${result.get(i).zhaiid}"
                atmanRelation.headerUrl = "starsInTheSky/0/0.png"
                atmanRelation.degree = 1//1//result.get(i).hierarchy-1
                atmanRelation.sonCount = 1//1//result.get(i).hujiRelationEntities.size
                atmanRelation.nickName = result.get(i).housecount
                val arrayList = ArrayList<RelationParent>()
                /* for (f in result.get(i).hujiRelationEntities){
                     val relationParent = RelationParent()
                     relationParent.group = f.parentid//result.get(i).zhaiid
                     relationParent.pictureCount = 1//result.size
                     relationParent.relation = f.relation
                     arrayList.add(relationParent)
                 }*/
                val relationParent = RelationParent()
                relationParent.group = result.get(0).zhaiid
                relationParent.pictureCount = 1//result.size
                relationParent.relation = result.get(i).socialrelatText
                arrayList.add(relationParent)
                atmanRelation.parentGroups = arrayList
                detail.add(atmanRelation)

            }
        }
        /*try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity = gson.fromJson<AtmanRelation>(data.optJSONObject(i).toString(), AtmanRelation::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
//        Log.e("detail", Gson().toJson(result))
//        Log.e("detail", Gson().toJson(detail))
        return detail
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser&&isResumed){
            //TODO now it's visible to user
            onResume()
        } else if (!isVisibleToUser) {
            //TODO now it's invisible to user
            onPause()
        }
    }
    fun diaoyong(){

        if (supl_frag_zjdgl1!=null){
            Log.e("***********","sv_frag_yzt_dc")
            supl_frag_zjdgl1?.setScrollableView(sv_frag_yzt_dc)
        }
    }
    override fun onResume() {
        super.onResume()
        if(userVisibleHint){

        }
    }

    override fun onPause() {
        super.onPause()
    }
    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

    override fun onClick(view: View?) {

        when (view?.getId()) {
            R.id.image_theOne ->
                if (sourceList != null && sourceList.size > 0) {
                    val atmanRelation = view!!.getTag(R.id.image_theOne) as AtmanRelation
                    if (clickAtmanRelation != null) {
                        if (clickAtmanRelation!!.getGroup() === atmanRelation.group) {
                            var zhaiEntity = ZhaiEntity()//HujiEntity
                            for (i in zhaiList){
                                if (i.zhaiid == atmanRelation.group){
                                    zhaiEntity = i
                                    break
                                }
                            }
//                            tvs_act_yzt_hjgxt
                            val tvDatas = ArrayList<TextViewsEntity>()
                            tvDatas.add(TextViewsEntity("村名:", zhaiEntity.xzqmc))
                            tvDatas.add(TextViewsEntity("姓名:", zhaiEntity.housecount))
                            tvDatas.add(TextViewsEntity("性别:", zhaiEntity.sexText))
                            tvDatas.add(TextViewsEntity("年龄:", zhaiEntity.age.toString()))
                            tvDatas.add(TextViewsEntity("户别:", zhaiEntity.huTypeText.toString()))
                            tvDatas.add(TextViewsEntity("社会关系:", zhaiEntity.socialrelatText.toString()))

                            tvs_act_yzt_hjgxt.visibility = View.VISIBLE
                            tvs_act_yzt_hjgxt?.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
                            tvs_act_yzt_hjgxt?.setDatas(tvDatas)
                            ToastUtils.showShort(atmanRelation.nickName)
//                        Toast.makeText(this@YztDcF, "点击头像", Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                    clickAtmanRelation = atmanRelation
                    //得到当前所点击的item,拿出他的 子空间和父控件。
                    val clickList = java.util.ArrayList<AtmanRelation>()
                    clickList.add(atmanRelation)
                    for (i in sourceList.indices) {
                        for (v in 0 until sourceList.get(i).getParentGroups().size) {
                            if (atmanRelation.group == sourceList.get(i).getParentGroups().get(v).getGroup()) {
                                clickList.add(sourceList.get(i))
                            }
                        }

                        for (j in 0 until atmanRelation.parentGroups.size) {
                            if (atmanRelation.parentGroups[j].group == sourceList.get(i).getGroup()) {
                                clickList.add(sourceList.get(i))
                            }
                        }
                    }

                    shaderImage.setClickList(clickList, atmanRelation.degree, atmanRelation.group, atmanRelation)
                    val animation = AnimationUtils.loadAnimation(activity, R.anim.scale_tip_point)
                    view!!.startAnimation(animation)
                }

            R.id.zhengju_image -> {
                val zhengjuBean = view!!.getTag() as ZhengjuBean
                ToastUtils.showShort(zhengjuBean.getGroupId3())
//                Toast.makeText(this@ReleaseActivity, "" + zhengjuBean.getGroupId3(), Toast.LENGTH_SHORT).show()
            }
        }//                Toast.makeText(ReleaseActivity.this, "点击证据:：：GroupId1=======" + zhengjuBean.getGroupId1() + "------>" + "GroupId2=======" + zhengjuBean.getGroupId2(), Toast.LENGTH_SHORT).show();
    }

}
