package com.jymj.zhglxt.zjd.fragment


import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import android.os.SystemClock
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.EditText
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.rjhj.bean.yl.ApplyCountEntity
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.jymj.zhglxt.util.TimePickerUtil
import com.jymj.zhglxt.widget.RecyclerViewNoBugLinearLayoutManager
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.zjd.activity.FjxcActivity
import com.jymj.zhglxt.zjd.activity.ZjdfjglActivity
import com.jymj.zhglxt.zjd.adapter.CunAdapter
import com.jymj.zhglxt.zjd.adapter.MainSpinnerAdapter
import com.jymj.zhglxt.zjd.adapter.ZjdFjListAdapter
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.zjd.contract.ZjdfjglContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.ZjdfjglPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.StringUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjd_ldrk.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_fj.*
import kotlinx.android.synthetic.main.fragment_zjd_fj.frag_first_lcvp
import kotlinx.android.synthetic.main.fragment_zjd_yd.*
import org.json.JSONObject
import java.math.BigDecimal
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ZjdFjFragment : BaseFragment<ZjdfjglPresenter, BaseModel>(),ZjdfjglContract.View, View.OnClickListener{

    private var date ="";//选择时间
    var zjdfjglList = ArrayList<Renovated>()//翻建列表
    var zjdfjglList1 = ArrayList<ApplyEntity>()//翻建列表宅基地
    private var name = ""
    private var zhenCode = ""
    private var cunCode = ""
    private var page = 1
    private var limit = 30
    private var quPosition = 0;
    private var zhenPosition = 0;
    private var lastSelect = 4
    private var cunAdapter: CunAdapter? =null
    private var sysXzqEntityListQu = ArrayList<SysXzqEntity>()
    private var sysXzqEntityListZhen = ArrayList<SysXzqEntity>()
    private var sysXzqEntityListCun= ArrayList<SysXzqEntity>()
    private var sysXzqEntityList = ArrayList<SysXzqEntity>()
    var zjdfjListAdapter1: ZjdFjListAdapter? = null
    private var adapterFlag = 0
    private var zjdfjglPosition = 0
    private val hbjcTitles = arrayListOf<String>("待验收", "待确权", "完成")//, "下发"  , "待审批"

    var optionPickerView: OptionsPickerView<Any>? = null//条件选择器
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdFjFragment {
            return ZjdFjFragment().apply {
                //                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_fj
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

        zjdfjgl_frag_xzsj.setOnTouchListener(object:View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event?.getAction()){
                    MotionEvent.ACTION_DOWN->{//按下
                    }
                    MotionEvent.ACTION_UP->{ //抬起
                        if (SingleOnClickUtil1.isFastClick()){
                            //自己业务
                            if (recy_zjdfjgl_environmental_show.isShown)
                                setTime(zjdfjgl_frag_xzsj)
                            if (ll_zjdfjgl_hz.isShown){
                                TimePickerUtil.initTimePickerView(activity,object: TimePickerUtil.OnTimePickerLister{
                                    override fun onClick(data: String?) {
                                        zjdfjgl_frag_xzsj.setText(data)
                                    }
                                })
                            }
//                                showDatePicker()
                        }
                    }
                }

                return false;
            }

        })
        zjdfjgl_frag_xzsj.setInputType(InputType.TYPE_NULL);
        zjdfjgl_frag_xzsj.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                date = zjdfjgl_frag_xzsj.text.toString()
                if (recy_zjdfjgl_environmental_show.isShown){
//                    zjdfjglList.clear()
                    zjdfjglList1.clear()
                    page = 1
//                    mPresenter.getFjxcList(lastSelect,limit,page,cunCode,date,bh)
                    mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                    mPresenter.getSteps(lastSelect,limit,page,cunCode,date,name)
                }
                if (recy_zjdfjgl_environmental_all.isShown){
//                    mPresenter.getFanJianZb(date)
                    mPresenter.getFanJianTjList(date)
                }
            }
        })
        zjdfjgl_item_tv.setOnClickListener {
            ll_zjdfjgl_yx.visibility = View.VISIBLE
            ll_zjdfjgl_hz.visibility = View.VISIBLE
            zjdfjgl_item_ll.visibility = View.GONE
            zjdfjgl_frag_xzsj.setText("")
        }

        for (i in 0..hbjcTitles.size - 1) {
            if (AppCache.getInstance().type==1||AppCache.getInstance().type==5){//&&(i!=0))||(AppCache.getInstance().type==5&&(i!=0))||(AppCache.getInstance().type==2&&(i==2||i==3))
                tab_zjdfjgl_map_hbjc1.addTab(tab_zjdfjgl_map_hbjc1.newTab().setText(hbjcTitles[i]))
            }
        }

        tab_zjdfjgl_map_hbjc1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                recy_zjdfjgl_environmental_show.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0f, 0f, 0));


            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text!!.equals("基础填报")){
                    lastSelect = 1
                }else if (tab!!.text!!.equals("审核")){
                    lastSelect = 2
                }else if (tab!!.text!!.equals("审批")){
                    lastSelect = 3
                }else if (tab!!.text!!.equals("待验收")){
                    lastSelect = 4
                }else if (tab!!.text!!.equals("驳回")){
                    lastSelect = 5
                }else if (tab!!.text!!.equals("待确权")){
                    lastSelect = 6
                }else if (tab!!.text!!.equals("完成")){
                    lastSelect = 7
                }

                limit = 30
                page = 1
                adapterFlag = 0
//                zjdfjglList.clear()
                zjdfjglList1.clear()
//                mPresenter.getFjxcList(lastSelect,limit,page, cunCode,date,bh)//AppCache.getInstance().type.toString(),
                mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                mPresenter.getSteps(lastSelect,limit,page, cunCode,date,name)//AppCache.getInstance().type.toString(),

            }
        })
        if (AppCache.getInstance().type==1||AppCache.getInstance().type==5){
            lastSelect = 4
            zjdfjgl_ll_xuncha.visibility = View.VISIBLE
            zjdfjgl_ll_zhenggai.visibility = View.VISIBLE
            zjdfjgl_ll_daichuli.visibility = View.VISIBLE
            zjdfjgl_ll_shenhe.visibility = View.VISIBLE
            zjdfjgl_ll_xiaozhang.visibility = View.VISIBLE
            zjdfjgl_ll_xuncha.setOnClickListener {
                tab_zjdfjgl_map_hbjc1.getTabAt(0)!!.select()
            }
            zjdfjgl_ll_zhenggai.setOnClickListener {
                tab_zjdfjgl_map_hbjc1.getTabAt(1)!!.select()
            }
            zjdfjgl_ll_daichuli.setOnClickListener {
                tab_zjdfjgl_map_hbjc1.getTabAt(2)!!.select()
            }
            zjdfjgl_ll_shenhe.setOnClickListener {
                tab_zjdfjgl_map_hbjc1.getTabAt(3)!!.select()
            }
            zjdfjgl_ll_xiaozhang.setOnClickListener {
                tab_zjdfjgl_map_hbjc1.getTabAt(4)!!.select()
            }
        }else if (AppCache.getInstance().type==2){
            lastSelect = 4
            zjdfjgl_ll_xuncha.visibility = View.VISIBLE
            zjdfjgl_ll_zhenggai.visibility = View.VISIBLE
            zjdfjgl_ll_daichuli.visibility = View.VISIBLE
            zjdfjgl_ll_shenhe.visibility = View.GONE
            zjdfjgl_ll_xiaozhang.visibility = View.GONE
//            tv_zjdfjgl_jlr.visibility = View.GONE//如果为镇级角色隐藏录入人
            zjdfjgl_ll_zhenggai.setOnClickListener {
                tab_zjdfjgl_map_hbjc1.getTabAt(0)!!.select()
            }
            zjdfjgl_ll_daichuli.setOnClickListener {
                tab_zjdfjgl_map_hbjc1.getTabAt(1)!!.select()
            }
        }

        rl_frag_zjd_fj_dys.setOnClickListener {
            tab_zjdfjgl_map_hbjc1.getTabAt(0)!!.select()
            view_frag_zjd_fj_dys_tab.visibility = View.VISIBLE
            view_frag_zjd_fj_dqq_tab.visibility = View.GONE
            view_frag_zjd_fj_wc_tab.visibility = View.GONE
        }
        rl_frag_zjd_fj_dqq.setOnClickListener {
            tab_zjdfjgl_map_hbjc1.getTabAt(1)!!.select()
            view_frag_zjd_fj_dys_tab.visibility = View.GONE
            view_frag_zjd_fj_dqq_tab.visibility = View.VISIBLE
            view_frag_zjd_fj_wc_tab.visibility = View.GONE
        }
        rl_frag_zjd_fj_wc.setOnClickListener {
            tab_zjdfjgl_map_hbjc1.getTabAt(2)!!.select()
            view_frag_zjd_fj_dys_tab.visibility = View.GONE
            view_frag_zjd_fj_dqq_tab.visibility = View.GONE
            view_frag_zjd_fj_wc_tab.visibility = View.VISIBLE
        }

    }

    override fun initDatas() {
        tv_frag_zjd_fj_cmsx.setOnClickListener {
            initoptionPickerView("村庄名称")
        }
    }

    override fun returnApplyByPoint(fjBean: ApplyEntity) {
        if (activity!=null){


            val intent = Intent(activity, ZjdfjglActivity::class.java)
            intent.putExtra("applyEntity",fjBean)
            startActivity(intent)
        }
    }

    override fun returnFanJian(fjBean: FjBean) {
        if (activity!=null){
            if (fjBean.id!=-1L){
                AppCache.getInstance().isFjList = 1//zjdfjglActivity
                val intent = Intent(activity, FjxcActivity::class.java)
                intent.putExtra("id", fjBean.id)
                startActivity(intent)
            }
        }
    }

    override fun returnFjxcList(fjBean: List<Renovated>, qutype: Int, count: Int) {
    }

    override fun returnFanJianZb(fjBean: List<StatisticalEntity>) {
    }

    override fun returnSteps(fjNumBean: FjNumBean) {
        if (fjNumBean!=null){
            tv_frag_zjd_fj_dys.text = fjNumBean.object6.toString()
            tv_frag_zjd_fj_dqq.text = fjNumBean.object8.toString()
            tv_frag_zjd_fj_wc.text = fjNumBean.object9.toString()
        }
    }

    override fun returnEnviorsByxy(fjNumBean: List<ApplyCountEntity>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal) {
    }

    //宅基地翻建统计
    override fun returnFanJianTjList(fjBean: List<ZjdfjTjBean>) {
        if (fjBean!=null&&fjBean.size>0){
            if (recy_zjdfjgl_environmental_all!=null){

                if (frag_first_lcvp != null){
//            firstpage_ndqs_bc.setOnChartValueSelectedListener(this);
                    frag_first_lcvp.getDescription().setEnabled(false);
                    frag_first_lcvp.setMaxVisibleValueCount(40);
                    // 扩展现在只能分别在x轴和y轴
                    frag_first_lcvp.setPinchZoom(false);
                    frag_first_lcvp.setDrawGridBackground(false);
                    frag_first_lcvp.setDrawBarShadow(false);
                    frag_first_lcvp.setDrawValueAboveBar(false);
                    frag_first_lcvp.setHighlightFullBarEnabled(false);
                    frag_first_lcvp.setScaleEnabled(false);
                    frag_first_lcvp.setDoubleTapToZoomEnabled(false);

                    // 改变y标签的位置
                    var leftAxis = frag_first_lcvp.getAxisLeft()
                    leftAxis.setValueFormatter(object: ValueFormatter(){})
                    leftAxis.setAxisMinimum(0f);
                    frag_first_lcvp.getAxisRight().setEnabled(false)

                    var xLabels = frag_first_lcvp.getXAxis()
                    xLabels.setPosition(XAxis.XAxisPosition.BOTTOM)
                    xLabels.setGranularity(1f)
                    xLabels.setLabelCount(fjBean.size)
//                    xLabels.setLabelRotationAngle(45f)
                    xLabels.valueFormatter = object: ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val index = value.toInt()

                            return if (index < 0 || index >= fjBean.size) {
                                ""
                            } else {
                                fjBean.get(index).object1
                            }
//                    return super.getFormattedValue(value)
                        }
                    }

                    var l = frag_first_lcvp.getLegend();
                    l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                    l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                    l.setDrawInside(false);
                    l.setFormSize(8f);
                    l.setFormToTextSpace(4f);
                    l.setXEntrySpace(6f);
                    val arrayList = ArrayList<ArrayList<Float>>()
                    for (i in 0..fjBean.size-1){//for (i in homeEntities.size-1 downTo 0){
                        val yoyListEntity = fjBean.get(i)
                        val arrayList1 = ArrayList<Float>()
                        val zthj = yoyListEntity!!.object6
                        val gcwt = yoyListEntity!!.object8
                        val wswt = yoyListEntity!!.object9
                        arrayList1.add(zthj.toFloat())
                        arrayList1.add(gcwt.toFloat())
                        arrayList1.add(wswt.toFloat())
                        arrayList.add(arrayList1)
                    }
                    setData(arrayList,fjBean);//homeEntities
                }


                zjdFjBar(fjBean as ArrayList<ZjdfjTjBean>,bat_frag_zjd_fj)

                recy_zjdfjgl_environmental_all.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                recy_zjdfjgl_environmental_all.adapter = object: BaseQuickAdapter<ZjdfjTjBean, BaseViewHolder>(R.layout.item_zjdfj_tj_list,fjBean){
                    override fun convert(helper: BaseViewHolder?, item: ZjdfjTjBean?) {
                        helper?.setText(R.id.tv_zjdfj_tj_list_z, item?.object1)
                                ?.setText(R.id.tv_zjdfj_tj_list_sh, (item!!.object6+ item!!.object8+ item!!.object9).toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_sp, item?.object6.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_tg, item?.object8.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_bh, (item!!.object9).toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_ys, item?.object8.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_dj, item?.object9.toString())

                        helper?.itemView?.setOnClickListener {

                            ll_zjdfjgl_yx.visibility = View.GONE
                            ll_zjdfjgl_hz.visibility = View.GONE
                            zjdfjgl_item_ll.visibility = View.VISIBLE
                            if (item!!.object2.equals("")){
                                zhenCode = AppCache.getInstance().cunCode
                                cunCode = AppCache.getInstance().cunCode
                            }else{
                                zhenCode = item!!.object2
                                cunCode = item!!.object2
                            }

//                        cunCode = item!!.code
                            /*if (item.object1.equals("总计")){
                                code = "110112"
                                zhenCode = ""
                            }else{
                                code = item.object5
                                zhenCode = item.object5
                            }*/
                            getDanXzqList(zhenCode,3)
                            //选择镇之后根据时间和村去显示相应的列表数据
                            zjdfjgl_frag_xzsj.setText("")

                        }
                    }

                }

            }

        }
    }
    //初始化
    private fun setData(stringList:ArrayList<ArrayList<Float>>,homeEntityList:List<ZjdfjTjBean>) {
        var yVals1 = ArrayList<BarEntry>()
        for (i in 0..stringList.size-1){
            val get = stringList.get(i)
            yVals1.add( BarEntry(i.toFloat(), floatArrayOf(get.get(0), get.get(1), get.get(2))))
        }

        /* for (i in 0..30) {//firstpage_ndqs_lcp  var i = 0; i < 30 + 1; i++
             var mult = (50 + 1);
             var val1 =  ((Math.random() * mult) + mult / 3).toFloat();
             var val2 =  ((Math.random() * mult) + mult / 3f).toFloat();
             var val3 =  ((Math.random() * mult) + mult / 3f).toFloat();
             yVals1.add( BarEntry(i.toFloat(), floatArrayOf(val1, val2, val3)));
 //            floatArrayOf({val1})
         }*/

        var set1: BarDataSet? =null;

        if (frag_first_lcvp.getData() != null &&
                frag_first_lcvp.getData().getDataSetCount() > 0) {
            set1 =  frag_first_lcvp.getData().getDataSetByIndex(0) as BarDataSet
            set1.setValues(yVals1)
            frag_first_lcvp.getData().notifyDataChanged()
            frag_first_lcvp.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")//三年级一班期末考试
            set1.setColors(getColors()!!.toList())
            set1.setStackLabels( arrayOf("待验收", "待确权", "完成"))

            var dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            var data = BarData(dataSets);//MyValueFormatter
            data.setValueFormatter(object: ValueFormatter(){})
            data.setValueTextColor(Color.WHITE)

            frag_first_lcvp.setData(data)
        }


        val markerView = NewMarkerView(activity, R.layout.custom_marker_view_layout)
        markerView.setCallBack(NewMarkerView.CallBack { x, value ->
            val index = x.toInt()
            if (index < 0) {
                return@CallBack
            }
            if (index > stringList.size) {
                return@CallBack
            }
            var textTemp = ""

            if (index <= stringList.size) {
                if (!StringUtils.isEmpty(textTemp)) {
                }
                val get = homeEntityList.get(index)
                val get1 = stringList.get(index)
                textTemp = get.object1+"(${get1.get(0).toInt()+get1.get(1).toInt()+get1.get(2).toInt()})"//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                textTemp += "\n"
                textTemp += "待验收:" + get1.get(0).toInt()
                textTemp += "\n"
                textTemp += "待确权:" + get1.get(1).toInt()
                textTemp += "\n"
                textTemp += "完成:" + get1.get(2).toInt()
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(frag_first_lcvp)
        frag_first_lcvp.setMarker(markerView)
        frag_first_lcvp.data.setDrawValues(false)

        frag_first_lcvp.setFitBars(true);
        frag_first_lcvp.invalidate();
    }

    private fun getColors(): IntArray? {
        val stacksize = 3
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }
        return colors
    }


    val stringList = ArrayList<String>()
    //柱状图
    private fun zjdFjBar(message: ArrayList<ZjdfjTjBean>, barChart: BarChart) {
//        message.clear()
        if (barChart != null&&message.size>0){
            /*var homeEntitys = ArrayList<WtlxfbZztBean>()
            homeEntitys.add(WtlxfbZztBean("申报",message.get(0).object3,cash.ljwtzgs,cash.ljwtzgl,
                    cash.optimalljwt,cash.optimalljwtzgl,cash.differenceljwt,cash.differenceljwtzgl))
            homeEntitys.add(WtlxfbZztBean("审批",cash.wswt,cash.wswtzgs,cash.wswtzgl,
                    cash.optimalwswt,cash.optimalwswtzgl,cash.differencewswt,cash.differencewswtzgl))
            homeEntitys.add(WtlxfbZztBean("通过",cash.gcwt,cash.gcwtzgs,cash.gcwtzgl,
                    cash.optimalgcwt,cash.optimalgcwtzgl,cash.differencegcwt,cash.differencegcwtzgl))
            homeEntitys.add(WtlxfbZztBean("驳回",cash.crwt,cash.crwtzgs,cash.crwtzgl,
                    cash.optimalcrwt,cash.optimalcrwtzgl,cash.differencecrwt,cash.differencecrwtzgl))*/
            stringList.clear()
            stringList.add("待验收")
            stringList.add("待确权")
            stringList.add("完成")
//            barChart.setOnChartValueSelectedListener(this);
            barChart.getDescription().setEnabled(false);
            barChart.setMaxVisibleValueCount(40);
            // 扩展现在只能分别在x轴和y轴
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(true);
            barChart.setHighlightFullBarEnabled(false);
            barChart.setScaleEnabled(false);
            barChart.setDoubleTapToZoomEnabled(false);

            // 改变y标签的位置
            var leftAxis = barChart.getAxisLeft()
            leftAxis.setValueFormatter(object:ValueFormatter(){})
            leftAxis.valueFormatter = object :ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return super.getFormattedValue(value.toInt().toFloat())
                }
            }
            leftAxis.setAxisMinimum(0f);
            // 横向 网格线
            leftAxis.setDrawGridLines(true)
            barChart.getAxisRight().setEnabled(false)
            var xLabels = barChart.xAxis
            xLabels.setPosition(XAxis.XAxisPosition.BOTTOM)
            xLabels.setGranularity(1f)
            // 取消 垂直 网格线
            xLabels.setDrawGridLines(false)
            xLabels.setLabelCount(stringList.size)
//            xLabels.setLabelRotationAngle(45f)
            xLabels.valueFormatter = object: ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()

                    return if (index < 0 || index >= stringList.size) {
                        ""
                    } else {
                        stringList.get(index)
                    }
//                    return super.getFormattedValue(value)
                }
            }
            val mMatrix = Matrix()
            mMatrix.postScale(0.5f, 1f)
            barChart.viewPortHandler.refresh(mMatrix, barChart, false)


            var l = barChart.getLegend()
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
            l.setDrawInside(false)
            l.setFormSize(8f)
            l.setFormToTextSpace(4f)
            l.setXEntrySpace(6f)
            val legend = barChart.legend
            legend.isWordWrapEnabled = true
            legend.isEnabled = false
            legend.setForm(Legend.LegendForm.NONE)
            val arrayList = ArrayList<ArrayList<Float>>()
            var aMax = 0f
            for (i in 0..stringList.size-1){//for (i in homeEntities.size-1 downTo 0){
                val yoyListEntity = message.get(0)
                val arrayList1 = ArrayList<Float>()
                if (stringList.get(i).equals("待验收")){
                    arrayList1.add(yoyListEntity.object6.toFloat())
                }else if (stringList.get(i).equals("待确权")){
                    arrayList1.add(yoyListEntity.object8.toFloat())
                }else if (stringList.get(i).equals("完成")){
                    arrayList1.add(yoyListEntity.object9.toFloat())
                }
                if (aMax<Collections.max(arrayList1)){
                    aMax =Collections.max(arrayList1)
                }
                arrayList.add(arrayList1)
            }
            if (aMax<6){
                leftAxis.axisMaximum = 6f
            }else{
                var mmm = (aMax/6+1).toInt()
                leftAxis.axisMaximum = mmm * 6f
            }
            setDataFj(arrayList,stringList,barChart)//homeEntities
        }

    }
    //初始化
    private fun setDataFj(stringList:ArrayList<ArrayList<Float>>,homeEntityList:ArrayList<String>, barChart: BarChart) {
        var yVals1 = ArrayList<BarEntry>()
        for (i in 0..stringList.size-1){
            val get = stringList.get(i)
            yVals1.add( BarEntry(i.toFloat(), floatArrayOf(get.get(0))))
        }

        var set1: BarDataSet? =null;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 =  barChart.getData().getDataSetByIndex(0) as BarDataSet
            set1.setValues(yVals1)
            barChart.getData().notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")//三年级一班期末考试
            set1.setColors(getColorsFj()!!.toList())
            set1.setStackLabels( arrayOf("整体环境", "公厕问题", "污水问题", "垃圾问题", "村容问题"))

            var dataSets = ArrayList<IBarDataSet>()
            set1.valueTextColor = Color.BLACK
            set1.setValueTextSize(16f);
            dataSets.add(set1)

            var data = BarData(dataSets);//MyValueFormatter
            data.setValueFormatter(object :ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()//super.getFormattedValue(value.toInt().toFloat())
                }

            })
//            data.setValueTextColor(Color.BLACK)

            barChart.setData(data)
        }


        val markerView = NewMarkerView(activity, R.layout.custom_marker_view_layout)
        markerView.setCallBack(NewMarkerView.CallBack { x, value ->
            val index = x.toInt()
            if (index < 0) {
                return@CallBack
            }
            if (index > stringList.size) {
                return@CallBack
            }
            var textTemp = ""

            if (index <= stringList.size) {
                if (!StringUtils.isEmpty(textTemp)) {
                }
                val get = homeEntityList.get(index)
                val get1 = stringList.get(index)
                textTemp = get//get.name//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                textTemp += "\n"
                textTemp += "总数量:" + get1.get(0).toInt()+""
                /*textTemp += "\n"
                textTemp += "整改率:" + get.zgl+"%"
                textTemp += "\n"
                textTemp += "最优村:" + get.zyc+" "+get.zyczgl+"%"
                textTemp += "\n"
                textTemp += "最差村:" + get.zcc+" "+get.zcczgl+"%"*/
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(barChart)
        barChart.setMarker(markerView)
        barChart.data.setDrawValues(true)

        barChart.setFitBars(true);
        barChart.invalidate();
    }

    private fun getColorsFj(): IntArray? {
        val stacksize = 4
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#fAF6D0")
        colors[1] = Color.parseColor("#FFB74A")
        colors[2] = Color.parseColor("#6BC4FF")
        colors[3] = Color.parseColor("#FF6000")
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
    }



    override fun returnFanJianApplyList(fjBean: List<ApplyEntity>, sptype: Int, count: Int) {
        if (recy_zjdfjgl_environmental_show!=null){
            zjdfjglList1.addAll(fjBean)

            recy_zjdfjgl_environmental_show.layoutManager = RecyclerViewNoBugLinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            recy_zjdfjgl_environmental_show.setHasFixedSize(false)
            recy_zjdfjgl_environmental_show.setFocusable(false)
            if (recy_zjdfjgl_environmental_show.isShown){
                if (adapterFlag==0){
                    zjdfjListAdapter1 = ZjdFjListAdapter(context, zjdfjglList1, lastSelect)
                    recy_zjdfjgl_environmental_show!!.adapter = zjdfjListAdapter1
                    adapterFlag = 1
                }
                zjdfjListAdapter1!!.setOnClickEnvironLister(object : ZjdFjListAdapter.OnClickFjxcLister {
                    override fun onClick(position: Int) {
                        if (SingleOnClickUtil.isFastClick()){
                            this@ZjdFjFragment.zjdfjglPosition = position
                            AppCache.getInstance().isFjList = 1//zjdfjglActivity
                            if (zjdfjglList1.size>position){
                                mPresenter.getApplyInfo(zjdfjglList1.get(position).id)
                            }

//                        mPresenter.getApplyByPoint(zjdfjglList1.get(position).getYLEntity().center1)
                            /*val intent = Intent(activity, FjxcActivity::class.java)
                            intent.putExtra("id", zjdfjglList1.get(position).id)
                            startActivity(intent)*/
                        }
                    }

                    override fun onDeleteClick(position: Int) {

                    }

                })
                recy_zjdfjgl_environmental_show.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager

                        val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                        if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&zjdfjglList1.size==page*limit) {


                            if (zjdfjglList1.size !=0 && zjdfjglList1.size%limit == 0){
                                page++
//                            mPresenter.getFjxcList(lastSelect,limit,page,cunCode,date,bh)
                                mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                                mPresenter.getSteps(lastSelect,limit,page,cunCode,date,name)

                            }else{
                                ToastUtils.showShort("滑动到底部了")
                            }
                        }
                    }
                })
                if (page!=1){
                    recy_zjdfjgl_environmental_show.scrollToPosition((page-1)*limit)
                }

            }
        }

    }

    //单独村
    fun getDanXzqList(code:String,type:Int){
        LoadingDialog.showDialogForLoading(activity)
        if (sp_zjdfjgl_hbjctj_list_bh!=null){
            val httpParams1 = JSONObject()
            httpParams1.put("code",code);
            httpParams1.put("type",type);
            val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
            var sss = "{\"requestData\":\"" + encrypt + "\"}"
            /*OkGo.post<String>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).upJson(sss).execute(
                    object : BaseNet<String>(){//BaseRespose<ArrayList<SysXzqEntity>>
                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                    super.onStart(request)

                }
                override fun onSuccess(response: Response<String>?) {
                    val body = response?.body()
                    if (body!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<ArrayList<SysXzqEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<ArrayList<SysXzqEntity>>?>() {}.type)

                        if (json.getCode()==0){
                            val data = json.data
                            if (data.size>0){

                                sysXzqEntityListCun.clear()
                                val sysXzqEntity = SysXzqEntity()
                                sysXzqEntity.name = "全部"
                                sysXzqEntity.code = code//zhenCode
                                *//* sysXzqEntity.center = sysXzqEntityListZhen.get(zhenPosition).center*//*
                                sysXzqEntityListCun.add(sysXzqEntity)
                                if ((AppCache.getInstance().type==1||AppCache.getInstance().type==5||AppCache.getInstance().type==6)&&code.equals("110112")){
                                    sysXzqEntityListCun.addAll(sysXzqEntityListZhen)
                                }
                                sysXzqEntityListCun.addAll(data)
                                

                              

                                zjdfjgl_but_cha.setOnClickListener {
                                    zjdfjglList1.clear()
                                    if (tv_frag_zjd_fj_cmsx.text.toString().equals("全部")||tv_frag_zjd_fj_cmsx.text.toString().equals("村名筛选")) {
                                        limit = 30
                                        page = 1
                                        zjdfjglList.clear()
                                        cunCode = code

                                        if (sp_zjdfjgl_hbjctj_list_bh.text.toString().equals("")){
                                            name = ""
                                        }else{
                                            name = sp_zjdfjgl_hbjctj_list_bh.text.toString()
                                        }
//                                        mPresenter.getFjxcList(lastSelect,limit,page, zhenCode,date,bh)//AppCache.getInstance().type.toString(),
                                        mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                                        mPresenter.getSteps(lastSelect,limit,page, zhenCode,date,name)//AppCache.getInstance().type.toString(),
                                       
                                    } else {
                                        limit = 30
                                        page = 1
                                        zjdfjglList.clear()
                                        for (i in sysXzqEntityListCun){
                                            if (tv_frag_zjd_fj_cmsx.text.toString().equals(i.name)){
                                                cunCode = i.code
                                            }
                                        }
                                        if (sp_zjdfjgl_hbjctj_list_bh.text.toString().equals("")){
                                            name = ""
                                        }else{
                                            name = sp_zjdfjgl_hbjctj_list_bh.text.toString()
                                        }
//                                        mPresenter.getFjxcList(lastSelect,limit,page, cunCode,date,bh)//AppCache.getInstance().type.toString(),
                                        mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                                        mPresenter.getSteps(lastSelect,limit,page, cunCode,date,name)//AppCache.getInstance().type.toString(),
                                       
                                    }
                                }
                                sp_zjdfjgl_hbjctj_list_bh.addTextChangedListener(object :TextWatcher{
                                    override fun afterTextChanged(p0: Editable?) {
                                    }

                                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                    }

                                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                        if (sp_zjdfjgl_hbjctj_list_bh.text.toString().equals("")){
                                            name = ""
                                        }else{
                                            name = sp_zjdfjgl_hbjctj_list_bh.text.toString()
                                        }
                                    }

                                })

                            }
                        }
                    }else{
                        ToastUtils.showShort("项目初始化失败！")
//                    SnackbarUtils.with(rjhj_cl_map_act).setMessage("项目初始化失败！").show()
                    }

                }

                override fun onFinish() {
                    super.onFinish()
                    LoadingDialog.cancelDialogForLoading()

                }
                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    LoadingDialog.cancelDialogForLoading()
                }
            })*/

        }

    }
    private fun setTime(editText: EditText) {//选择时间
        val year = Calendar.getInstance()[Calendar.YEAR]
        val month = Calendar.getInstance()[Calendar.MONTH]
        val day = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
            var nian = ""
            var yue = ""
            var ri = ""
            nian = i.toString() + ""
            yue = if (i1 < 9) {
                "0" + (i1 + 1)
            } else {
                "" + (i1 + 1)
            }
            ri = if (i2 < 10) {
                "0$i2"
            } else {
                "" + i2
            }
            editText.setText(nian+"-"+yue+"-"+ri)
        }, year, month, day)
        val datePicker = datePickerDialog.datePicker
        datePicker.maxDate = System.currentTimeMillis()+1000 ///< 设置日期的上限日期
        datePickerDialog.show()
    }
    //月份弹出框
    private fun showDatePicker() {

        //获取当前日期
        val calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        //创建并显示DatePickerDialog
//        val dialog = DatePickerDialog(activity,android.R.style.Theme_Holo_Light_Panel, Datelistener, year, month, day)
        val dialog = DatePickerDialog(activity!!,android.R.style.Theme_Holo_Light_Dialog_NoActionBar, Datelistener, year, month, day)
        dialog.show()

        //只显示年月，隐藏掉日
        val dp = findDatePicker(dialog.window!!.decorView as ViewGroup)
        if (dp != null) {
            ((dp!!.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup)
                    .getChildAt(2).visibility = View.GONE
            //如果想隐藏掉年，将getChildAt(2)改为getChildAt(0)
        }
    }
    private fun findDatePicker(group: ViewGroup?): DatePicker? {
        if (group != null) {
            var i = 0
            val j = group.childCount
            while (i < j) {
                val child = group.getChildAt(i)
                if (child is DatePicker) {
                    return child
                } else if (child is ViewGroup) {
                    val result = findDatePicker(child)
                    if (result != null)
                        return result
                }
                i++
            }
        }
        return null
    }
    private val Datelistener = object : DatePickerDialog.OnDateSetListener {
        /**params：view：该事件关联的组件
         * params：myyear：当前选择的年
         * params：monthOfYear：当前选择的月
         * params：dayOfMonth：当前选择的日
         */
        override fun onDateSet(view: DatePicker, myyear: Int, monthOfYear: Int, dayOfMonth: Int) {

            //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
//            year = myyear
//            month = monthOfYear
//            day = dayOfMonth
            if (monthOfYear<9){
                //mPresenter.getNewDaily(year.toString() + "-0" + (month + 1))
                zjdfjgl_frag_xzsj.setText(myyear.toString() + "-0" + (monthOfYear + 1))
            }else{
                //mPresenter.getNewDaily(year.toString() + "-" + (month + 1))
                zjdfjgl_frag_xzsj.setText(myyear.toString() + "-" + (monthOfYear + 1))
            }
            //更新日期
//            updateDate()

        }
    }
    override fun onPause() {
        super.onPause()
    }
    fun diaoyong(){

        if (supl_frag_zjdgl1!=null){
            supl_frag_zjdgl1?.setScrollableView(null)
        }
        if (recy_zjdfjgl_environmental_all.isShown){
            mPresenter.getFanJianTjList(date)
        }
        if (!recy_zjdfjgl_environmental_all.isShown) {
            if (AppCache.getInstance().isZjdUpdate == 1) {
                AppCache.getInstance().isZjdUpdate = 0
                zjdfjglList1.clear()
                mPresenter.getFanJianApplyList(lastSelect, limit, page, cunCode, date, name)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        if (getUserVisibleHint()){
            if (!recy_zjdfjgl_environmental_all.isShown) {
                if (AppCache.getInstance().isZjdUpdate == 1) {
                    AppCache.getInstance().isZjdUpdate = 0
                    zjdfjglList1.clear()
                    mPresenter.getFanJianApplyList(lastSelect, limit, page, cunCode, date, name)
                }
            }
        }
    }

    //初始化选择器
    private fun initoptionPickerView(type: String) {
        var cunList = ArrayList<String>()
        //sysXzqEntityList
        for (i in sysXzqEntityListCun){
            cunList.add(i.name)
        }
        optionPickerView = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            if (type.equals("村庄名称")) {
                zjdfjglList1.clear()
                zjdfjglList.clear()
                tv_frag_zjd_fj_cmsx.text = sysXzqEntityListCun.get(options1).name
                tv_frag_zjd_fj_cmsx.setTextColor(Color.parseColor("#4CA2FE"))
                page = 1
                cunCode = sysXzqEntityListCun.get(options1).code
                if (tv_frag_zjd_fj_cmsx.text.toString().equals("全部")){
                    limit = 30
                    page = 1
                    if (sp_zjdfjgl_hbjctj_list_bh.text.toString().equals("")){
                        name = ""
                    }else{
                        name = sp_zjdfjgl_hbjctj_list_bh.text.toString()
                    }
                    mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                    mPresenter.getSteps(lastSelect,limit,page, zhenCode,date,name)
                }else{
                    limit = 30
                    page = 1
                    if (sp_zjdfjgl_hbjctj_list_bh.text.toString().equals("")){
                        name = ""
                    }else{
                        name = sp_zjdfjgl_hbjctj_list_bh.text.toString()
                    }
                    mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                    mPresenter.getSteps(lastSelect,limit,page, cunCode,date,name)
                }

            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(type)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setContentTextSize(20)
                .isDialog(true)
                .build<Any>()
        if (type.equals("村庄名称")) {
            optionPickerView!!.setPicker(cunList as List<Any>?)//一级选择器
        }

        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = optionPickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        optionPickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        optionPickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        optionPickerView!!.show()
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

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

    override fun onClick(v: View?) {
    }

}
