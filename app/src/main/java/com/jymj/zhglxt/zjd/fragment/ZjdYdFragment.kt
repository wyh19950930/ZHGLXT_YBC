package com.jymj.zhglxt.zjd.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ViewPortHandler
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
import com.jymj.zhglxt.zjd.activity.YdlrActivity
import com.jymj.zhglxt.zjd.adapter.MainSpinnerAdapter
import com.jymj.zhglxt.zjd.adapter.ZjdFjListAdapter
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.YdlrContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.YdlrPresenter
import com.loc.bh
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
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_yd.*
import org.json.JSONObject
import java.io.Serializable
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ZjdYdFragment : BaseFragment<YdlrPresenter, BaseModel>(),YdlrContract.View, View.OnClickListener{
    private var lastSelect = 1
    private var page = 1
    private var limit = 30
    private var date ="";//选择时间
    private var name = ""
    private var zhenCode = ""
    private var cunCode = ""
    var ydlrList = ArrayList<Renovated>()//翻建列表
    var ydlrList1 = ArrayList<ApplyEntity>()//翻建列表宅基地
    private var sysXzqEntityListQu = ArrayList<SysXzqEntity>()
    private var sysXzqEntityListZhen = ArrayList<SysXzqEntity>()
    private var sysXzqEntityListCun= ArrayList<SysXzqEntity>()
    var zjdfjListAdapter1: ZjdFjListAdapter? = null
    private var adapterFlag = 0
    private var ydlrPosition = 0
    private val hbjcTitles = arrayListOf<String>("申请", "审核", "审批", "通过", "完成", "驳回")//, "下发"  , "待审批"  , "验收", "登记"

    var optionPickerView: OptionsPickerView<Any>? = null//条件选择器
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdYdFragment {
            return ZjdYdFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_yd
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

    }

    override fun initDatas() {

    }

    override fun returnApplyByPoint(fjBean: List<ApplyLandEntity>) {
        if (activity!=null&&fjBean.size==1){
            if (fjBean.get(0).ylEntity!=null&&!fjBean.get(0).ylEntity.geometry.equals("")){
//                kuangGeomentLine(fjBean.get(0).ylEntity.geometry)
            }

            val intent = Intent(activity, YdlrActivity::class.java)
            intent.putExtra("applyEntity", fjBean as Serializable)
//            val bundle: Bundle = Bundle()
//            bundle.put("applyEntity", fjBean)
//            intent.putExtras(bundle)
            startActivity(intent)
        }else{
            val arrayList = ArrayList<String>()
//            val arrayListOf = arrayOf()<
            for (i in fjBean){
                arrayList.add(i.name)
            }
            val alertBuilder = AlertDialog.Builder(activity)

            alertBuilder.setTitle("此点位已分户")
            alertBuilder.setSingleChoiceItems(arrayList.toArray(arrayOfNulls(arrayList.size)), 0) { dialogInterface, i ->
                var applyLandEntitys= ArrayList<ApplyLandEntity>()
                applyLandEntitys.add(fjBean.get(i))
                val intent = Intent(activity, YdlrActivity::class.java)
                intent.putExtra("applyEntity", applyLandEntitys as Serializable)
//            val bundle: Bundle = Bundle()
//            bundle.put("applyEntity", fjBean)
//            intent.putExtras(bundle)
                startActivity(intent)
                dialogInterface!!.dismiss()
            }


            alertBuilder.setNegativeButton("取消") { dialogInterface, i ->
                dialogInterface!!.dismiss()
            }
            alertBuilder.show()
        }

    }

    override fun returnFanJian(fjBean: FjBean) {
    }

    override fun returnFjxcList(fjBean: List<Renovated>, qutype: Int, count: Int) {
    }

    override fun returnFanJianZb(fjBean: List<StatisticalEntity>) {
    }

    override fun returnSteps(fjNumBean: FjNumBean) {
        if (fjNumBean!=null){
            tv_frag_zjd_yd_sq.text = fjNumBean.object3.toString()
            tv_frag_zjd_yd_sh.text = fjNumBean.object4.toString()
            tv_frag_zjd_yd_sp.text = fjNumBean.object8.toString()
            tv_frag_zjd_yd_tg.text = fjNumBean.object5.toString()
            tv_frag_zjd_yd_bh.text = fjNumBean.object6.toString()
            tv_frag_zjd_wc_tg.text = fjNumBean.object7.toString()
        }

    }

    override fun returnEnviorsByxy(fjNumBean: List<ApplyCountEntity>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal) {
    }


    //宅基地翻建统计
    override fun returnFanJianTjList(fjBean: List<ZjdfjTjBean>) {
        if (fjBean!=null&&fjBean.size>0){
            if (recy_ydlr_environmental_all!=null){

                if (frag_first_lcvp != null){
//            firstpage_ndqs_bc.setOnChartValueSelectedListener(this);
                    frag_first_lcvp.getDescription().setEnabled(false);
                    frag_first_lcvp.setMaxVisibleValueCount(40);
                    // 扩展现在只能分别在x轴和y轴
                    frag_first_lcvp.setPinchZoom(false);
                    frag_first_lcvp.setDrawGridBackground(true);
                    frag_first_lcvp.setDrawBarShadow(false);
                    frag_first_lcvp.setDrawValueAboveBar(false);
                    frag_first_lcvp.setHighlightFullBarEnabled(false);
                    frag_first_lcvp.setScaleEnabled(false);
                    frag_first_lcvp.setDoubleTapToZoomEnabled(false);

                    // 改变y标签的位置
                    var leftAxis = frag_first_lcvp.getAxisLeft()
                    leftAxis.setValueFormatter(object: ValueFormatter(){})
                    leftAxis.setAxisMinimum(0f)
                    frag_first_lcvp.getAxisRight().setEnabled(false)

                    var xLabels = frag_first_lcvp.getXAxis()
                    xLabels.setPosition(XAxis.XAxisPosition.BOTTOM)
                    xLabels.setGranularity(1f)
                    xLabels.setLabelCount(fjBean.size)
                    xLabels.setLabelRotationAngle(45f)
                    xLabels.valueFormatter = object: ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val index = value.toInt()

                            return if (index < 0 || index >= fjBean.size-1) {
                                ""
                            } else {
                                fjBean.get(index+1).object1
                            }
//                    return super.getFormattedValue(value)
                        }
                    }

                    var l = frag_first_lcvp.getLegend()
                    l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
                    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
                    l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
                    l.setDrawInside(false)
                    l.setFormSize(8f)
                    l.setFormToTextSpace(4f)
                    l.setXEntrySpace(6f)
                    val arrayList = ArrayList<ArrayList<Float>>()
                    for (i in 0..fjBean.size-1){//for (i in homeEntities.size-1 downTo 0){
                        if (i!=0){
                            val yoyListEntity = fjBean.get(i)
                            val arrayList1 = ArrayList<Float>()
                            val zthj = yoyListEntity!!.object3+yoyListEntity.object4
                            val gcwt = yoyListEntity!!.object5
                            val wswt = yoyListEntity!!.object6
                            val wcwt = yoyListEntity.object7
                            val ljwt = yoyListEntity!!.object8
                            val crwt = yoyListEntity!!.object9
                            arrayList1.add(zthj.toFloat())
                            arrayList1.add(gcwt.toFloat())
                            arrayList1.add(wswt.toFloat())
                            arrayList1.add(wcwt.toFloat())
                            arrayList1.add(ljwt.toFloat())
                            arrayList1.add(crwt.toFloat())
                            arrayList.add(arrayList1)
                        }
                    }
                    setData(arrayList,fjBean);//homeEntities
                }



                zjdSpBar(fjBean as ArrayList<ZjdfjTjBean>,bct_frag_zjd_yd)

                recy_ydlr_environmental_all.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                recy_ydlr_environmental_all.adapter = object: BaseQuickAdapter<ZjdfjTjBean, BaseViewHolder>(R.layout.item_zjdfj_tj_list,fjBean){
                    override fun convert(helper: BaseViewHolder?, item: ZjdfjTjBean?) {
                        helper?.setText(R.id.tv_zjdfj_tj_list_z, item?.object1)
                                ?.setText(R.id.tv_zjdfj_tj_list_sh, item!!.object3.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_sp, item?.object4.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_tg, item?.object5.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_bh, item.object6.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_wc, item.object7.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_ys, item?.object8.toString())
                                ?.setText(R.id.tv_zjdfj_tj_list_dj, item?.object9.toString())

                        helper?.itemView?.setOnClickListener {
                            ll_ydlr_yx.visibility = View.GONE
                            ll_ydlr_hz.visibility = View.GONE
                            ydlr_item_ll.visibility = View.VISIBLE
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
                            ydlr_frag_xzsj.setText(date)//""

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
            yVals1.add( BarEntry(i.toFloat(), floatArrayOf(get.get(0), get.get(1), get.get(2))))//,get.get(3),get.get(4)
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
            set1.setStackLabels( arrayOf("申请", "审核", "审批"))//, "验收", "登记"

            var dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            var data = BarData(dataSets);//MyValueFormatter
            data.setValueFormatter(object:ValueFormatter(){})
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
                textTemp = get.object1+"(${get1.get(0).toInt()+get1.get(1).toInt()+get1.get(2).toInt()+get1.get(3).toInt()+get1.get(4).toInt()})"//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                textTemp += "\n"
                textTemp += "申请:" + get1.get(0).toInt()
                textTemp += "\n"
                textTemp += "审核:" + get1.get(1).toInt()
                textTemp += "\n"
                textTemp += "审批:" + get1.get(2).toInt()
                /*textTemp += "\n"
                textTemp += "验收:" + get1.get(3).toInt()
                textTemp += "\n"
                textTemp += "登记:" + get1.get(4).toInt()*/
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
    private fun zjdSpBar(message: ArrayList<ZjdfjTjBean>, barChart: BarChart) {
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
            stringList.add("申请")
            stringList.add("核实")
            stringList.add("审批")
            stringList.add("验收")
            stringList.add("完成")
            stringList.add("驳回")
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
                val zthj = yoyListEntity.object3
                if (stringList.get(i).equals("申请")){
                    arrayList1.add(yoyListEntity.object3.toFloat())
                }else if (stringList.get(i).equals("核实")){
                    arrayList1.add(yoyListEntity.object4.toFloat())
                }else if (stringList.get(i).equals("验收")){
                    arrayList1.add(yoyListEntity.object5.toFloat())//yoyListEntity.object5.toFloat() 20f
                }else if (stringList.get(i).equals("驳回")){
                    arrayList1.add(yoyListEntity.object6.toFloat())
                }else if (stringList.get(i).equals("完成")){
                    arrayList1.add(yoyListEntity.object7.toFloat())
                }else if (stringList.get(i).equals("审批")){
                    arrayList1.add(yoyListEntity.object8.toFloat())
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
            setDataSP(arrayList,stringList,barChart)//homeEntities
        }
        /*val xAxis = barChart.xAxis//获取x轴
        xAxis.position = XAxis.XAxisPosition.BOTTOM//设置X轴标签显示位置
        xAxis.setDrawGridLines(false)//不绘制格网线
        xAxis.granularity = 1f//设置最小间隔，防止当放大时，出现重复标签
        xAxis.labelCount = stringList.size//设置标签显示的个数


        xAxis.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                return if (index < 0 || index >= stringList.size) {
                    ""
                } else {
                    stringList.get(index)
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

        val yValues4 = java.util.ArrayList<BarEntry>()

//        for (x in message.indices) {
            val area1 = message.get(0).object3
            yValues.add(BarEntry(0.toFloat(), area1.toFloat()))

            val area2 = message.get(0).object4
            yValues2.add(BarEntry(1.toFloat(), area2.toFloat()))

             val area3 = message.get(0).object5
             yValues3.add(BarEntry(2.toFloat(),area3.toFloat()))

            val area4 = message.get(0).object6
            yValues4.add(BarEntry(3.toFloat(),area4.toFloat()))
//        }

        // y 轴数据集
        val barDataSet = BarDataSet(yValues, "申报")
        barDataSet.color = Color.parseColor("#4AF6D0")

        // y2 轴数据集
        val barDataSet2 = BarDataSet(yValues2, "审批")
        barDataSet2.color = Color.parseColor("#FFB74A")

        // y3 轴数据集
        val barDataSet3 = BarDataSet(yValues3, "通过")
        barDataSet3.color = Color.parseColor("#6BC4FF")

        // y4 轴数据集
        val barDataSet4 = BarDataSet(yValues4, "驳回")
        barDataSet4.color = Color.parseColor("#FF6000")

        val mBarData = BarData(barDataSet,barDataSet2,barDataSet3,barDataSet4)
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
        barChart.getXAxis().setDrawAxisLine(false)
        barChart.getXAxis().setDrawGridLines(false)
        barChart.viewPortHandler.refresh(mMatrix, barChart, false)
        barChart.animateY(800)
        barChart!!.setScaleEnabled(false)//启用/禁用两个轴上的图表缩放
        barChart!!.setScaleXEnabled(false)//启用/禁用x轴缩放
        barChart!!.setScaleYEnabled(false)//启用/禁用y轴缩放
        barChart!!.setPinchZoom(false)//如果设置为true，则启用缩放缩放。如果禁用，则可以单独缩放x轴和y轴
        barChart!!.setDoubleTapToZoomEnabled(false)//
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * stringList.size + 0)
        xAxis.setAxisMinimum(-0.5f);*/
//        xAxis.setAxisMaximum(barChart.data.getXMax() + 1f);
    }
    //初始化
    private fun setDataSP(stringList:ArrayList<ArrayList<Float>>,homeEntityList:ArrayList<String>, barChart: BarChart) {
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
            set1.setColors(getColorsSP()!!.toList())
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
            data.setValueTextColor(Color.BLACK)
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

    private fun getColorsSP(): IntArray? {
        val stacksize = 6
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#FA5D56")
        colors[1] = Color.parseColor("#04BDC2")
        colors[2] = Color.parseColor("#25C898")
        colors[3] = Color.parseColor("#2A8DFE")
        colors[4] = Color.parseColor("#25C898")
        colors[5] = Color.parseColor("#FFB638")
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
    }

    //单独村
    fun getDanXzqList(code:String,type:Int){
        LoadingDialog.showDialogForLoading(activity)
        if (sp_ydlr_hbjctj_list_bh!=null){
            val httpParams1 = JSONObject()
            httpParams1.put("code",code);
            httpParams1.put("type",type);
            val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
            var sss = "{\"requestData\":\"" + encrypt + "\"}"
          /*  OkGo.post<String>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).upJson(sss).execute(
                    object : BaseNet<String>(){//BaseRespose<ArrayList<SysXzqEntity>>
                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                    super.onStart(request)

                }
                override fun onSuccess(response: Response<String>?) {
                    val body = response?.body()
                    if (body!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<ArrayList<SysXzqEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<java.util.ArrayList<SysXzqEntity>>?>() {}.type)

                        if (json.getCode()==0){
                            val data = json.data
                            if (data.size>0){
                                sysXzqEntityListCun.clear()
                                val sysXzqEntity = SysXzqEntity()
                                sysXzqEntity.name = "全部"
                                sysXzqEntity.code = code//zhenCode
                                sysXzqEntityListCun.add(sysXzqEntity)
                                if ((AppCache.getInstance().type==1||AppCache.getInstance().type==5||AppCache.getInstance().type==6)&&code.equals("110112")){
                                    sysXzqEntityListCun.addAll(sysXzqEntityListZhen)
                                }
                                sysXzqEntityListCun.addAll(data)

                                ydlr_but_cha.setOnClickListener {
                                    ydlrList1.clear()
                                    if (tv_frag_zjd_yd_cmsx.text.toString().equals("全部")||tv_frag_zjd_yd_cmsx.text.toString().equals("村名筛选")) {
                                        limit = 30
                                        page = 1
                                        ydlrList.clear()
                                        cunCode = code
                                        if (sp_ydlr_hbjctj_list_bh.text.toString().equals("")){
                                            name = ""
                                        }else{
                                            name = sp_ydlr_hbjctj_list_bh.text.toString()
                                        }
                                        mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                                        mPresenter.getSteps(lastSelect,limit,page, zhenCode,date,name)//AppCache.getInstance().type.toString(),

                                    } else {
                                        limit = 30
                                        page = 1
                                        ydlrList.clear()
                                        for (i in sysXzqEntityListCun){
                                            if (tv_frag_zjd_yd_cmsx.text.toString().equals(i.name)){
                                                cunCode = i.code
                                            }
                                        }
                                        if (sp_ydlr_hbjctj_list_bh.text.toString().equals("")){
                                            name = ""
                                        }else{
                                            name = sp_ydlr_hbjctj_list_bh.text.toString()
                                        }
                                        mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
                                        mPresenter.getSteps(lastSelect,limit,page, cunCode,date,name)//AppCache.getInstance().type.toString(),
                                    }
                                }
                                sp_ydlr_hbjctj_list_bh.addTextChangedListener(object :TextWatcher{
                                    override fun afterTextChanged(p0: Editable?) {
                                    }

                                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                    }

                                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                        if (sp_ydlr_hbjctj_list_bh.text.toString().equals("")){
                                            name = ""
                                        }else{
                                            name = sp_ydlr_hbjctj_list_bh.text.toString()
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

    override fun returnFanJianApplyList(fjBean: List<ApplyEntity>, sptype: Int, count: Int) {
        if (recy_ydlr_environmental_show!=null){
            if (page == 1){
                ydlrList1.clear()
            }
            ydlrList1.addAll(fjBean)

            recy_ydlr_environmental_show.layoutManager = RecyclerViewNoBugLinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            recy_ydlr_environmental_show.setHasFixedSize(false)
            recy_ydlr_environmental_show.setFocusable(false)
            if (recy_ydlr_environmental_show.isShown){
                if (adapterFlag==0){
                    zjdfjListAdapter1 = ZjdFjListAdapter(context, ydlrList1, lastSelect)
                    recy_ydlr_environmental_show!!.adapter = zjdfjListAdapter1
                    adapterFlag = 1
                }
                zjdfjListAdapter1!!.setOnClickEnvironLister(object : ZjdFjListAdapter.OnClickFjxcLister {

                    override fun onClick(position: Int) {
                        if (SingleOnClickUtil.isFastClick()){
                            this@ZjdYdFragment.ydlrPosition = position
                            AppCache.getInstance().isFjList = 1//ydlrActivity
                            if (ydlrList1.size>position){
                                mPresenter.getApplyInfo(ydlrList1.get(position).id)
                            }

//                        mPresenter.getApplyByPoint(ydlrList1.get(position).getYLEntity().center1)
                            /*val intent = Intent(activity, FjxcActivity::class.java)
                            intent.putExtra("id", ydlrList1.get(position).id)
                            startActivity(intent)*/
                        }
                    }

                    override fun onDeleteClick(position: Int) {

                    }

                })
                recy_ydlr_environmental_show.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView!!, dx, dy)

                        val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager

                        val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                        if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&ydlrList1.size==page*limit) {


                            if (ydlrList1.size !=0 && ydlrList1.size%limit == 0){
                                page++
//                                mPresenter.getFanJianApplyList(lastSelect,limit,page,cunCode,date,name)
//                                mPresenter.getSteps(lastSelect,limit,page,cunCode,date,name)

                            }else{
                                ToastUtils.showShort("滑动到底部了")
                            }
                        }


                    }
                })
                if (page!=1){
                    recy_ydlr_environmental_show.scrollToPosition((page-1)*limit)
                }

            }
        }
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
            supl_frag_zjdgl1?.setScrollableView(null)
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

    override fun onClick(v: View?) {
    }


}
