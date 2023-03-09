package com.jymj.zhglxt.zjd.fragment.shzl

import android.content.Intent
import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ViewPortHandler

import com.jymj.zhglxt.R
import com.jymj.zhglxt.ldrkgl.home.adapter.HylxTlAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.LegendBean
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.PieTdxgUtil
import com.jymj.zhglxt.util.TdlyUtils
import com.jymj.zhglxt.util.TimeUtil
import com.jymj.zhglxt.widget.EnableLinearLayout
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.zjd.activity.AjlbActivity
import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.fj.ZjdfjTjBean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTj2Bean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTjBean
import com.jymj.zhglxt.zjd.contract.YqglContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.YqglPresenter
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.StringUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.fragment_zjd_yqgl.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * (原：舆情管理  接诉即办) 百村监测-集体收支
 */
class ShzlYqglFragment : BaseFragment<YqglPresenter, BaseModel>(), YqglContract.View{


    var colorList = ArrayList<Int>()//颜色
    private var yearUpPopu: CommenPop? = null
    private var rejectUpPopu: CommenPop? = null
    private var addCjjzsrqkUpPopu: CommenPop? = null
    private var addCjtjjzysrqkUpPopu: CommenPop? = null
    private var addCjtjjzyzcqkUpPopu: CommenPop? = null
    var mUploadPopu:CommenPop? = null
    var sptypeList = ArrayList<String>()
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ShzlYqglFragment {
            return ShzlYqglFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }
    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_yqgl
    }
    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        if (AppCache.getInstance().type==1){
            tv_frag_zjd_yqgl_xzsj.visibility = View.GONE
        }
        initColors()
        ll_frag_zjd_yqgl_qbaj.setOnClickListener {
//            ToastUtils.showShort("全部案件")
            var intent = Intent(activity,AjlbActivity::class.java)
            intent.putExtra("qutype",-1)
            startActivity(intent)
        }
        /*tv_frag_jtsz_xzsj.setOnClickListener {
            if (yearUpPopu!=null) {
                CommenPop.backgroundAlpha(0.5f, activity)
                yearUpPopu!!.showAtLocation(slv_frag_zjd_ydgl, Gravity.CENTER, 0, 0)
            }
            *//*TimePickerUtil.initTimePickerViewNyr(activity,tv_frag_jtsz_xzsj.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    //时间筛选
                    tv_frag_jtsz_xzsj.setText(data)
                }
            })*//*
        }*/
        ll_frag_jtsz_gyjj.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_gyjj.text.toString(),tv_frag_jtsz_gyjj)
        }
        ll_frag_jtsz_cjtqysr.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_cjtqysr.text.toString(),tv_frag_jtsz_cjtqysr)
        }
        ll_frag_jtsz_syqy.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_syqy.text.toString(),tv_frag_jtsz_syqy)
        }
        ll_frag_jtsz_zyywsr.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_zyywsr.text.toString(),tv_frag_jtsz_zyywsr)
        }
        ll_frag_jtsz_qtywsr.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_qtywsr.text.toString(),tv_frag_jtsz_qtywsr)
        }
        ll_frag_jtsz_tzsy.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_tzsy.text.toString(),tv_frag_jtsz_tzsy)
        }
        ll_frag_jtsz_btsr.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_btsr.text.toString(),tv_frag_jtsz_btsr)
        }
        ll_frag_jtsz_zywsr.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_zywsr.text.toString(),tv_frag_jtsz_zywsr)
        }
        ll_frag_jtsz_cgbbt.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_cgbbt.text.toString(),tv_frag_jtsz_cgbbt)
        }
        ll_frag_jtsz_cjzzbgf.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_cjzzbgf.text.toString(),tv_frag_jtsz_cjzzbgf)
        }
        ll_frag_jtsz_ggfwf.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_ggfwf.text.toString(),tv_frag_jtsz_ggfwf)
        }
        ll_frag_jtsz_cjfdf.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_jtsz_cjfdf.text.toString(),tv_frag_jtsz_cjfdf)
        }

    }
    var split = ""
    override fun initDatas() {

        val dateAfter = TimeUtil.getDateAfter(0)
//        split = dateAfter.split("-")[0]
//        mPresenter.getOpinionGetCount()
//        mPresenter.getOpinionGetStatistical()
        val arrayList = ArrayList<ZjdfjTjBean>()
        val zjdfjTjBean = ZjdfjTjBean()
        zjdfjTjBean.object1 = "通州"
        zjdfjTjBean.object3 = 3
        zjdfjTjBean.object4 = 5
        zjdfjTjBean.object5 = 7
        zjdfjTjBean.object6 = 9
        zjdfjTjBean.object7 = 11
        arrayList.add(zjdfjTjBean)
        val zjdfjTjBean1 = ZjdfjTjBean()
        zjdfjTjBean1.object1 = "大兴"
        zjdfjTjBean1.object3 = 3
        zjdfjTjBean1.object4 = 5
        zjdfjTjBean1.object5 = 7
        zjdfjTjBean1.object6 = 9
        zjdfjTjBean1.object7 = 11
        arrayList.add(zjdfjTjBean1)
        zjdSpBar(arrayList as ArrayList<ZjdfjTjBean>)//,bct_frag_zjd_yqgl_kstj
        /*var xAxisValue = ArrayList<String>()
        var yAxisValue1 = ArrayList<Float>()
        var yAxisValue2 = ArrayList<Float>()
        xAxisValue.add("通州")
        xAxisValue.add("大兴")
        xAxisValue.add("朝阳")
        xAxisValue.add("丰台")
        yAxisValue1.add(10f)
        yAxisValue1.add(20f)
        yAxisValue1.add(30f)
        yAxisValue1.add(40f)
        yAxisValue2.add(25f)
        yAxisValue2.add(55f)
        yAxisValue2.add(75f)
        yAxisValue2.add(100f)
        //KstjBean
        setTwoBarChart(bct_frag_zjd_yqgl_kstj,xAxisValue,yAxisValue1,yAxisValue2,"","")*/


        mtl_frag_yqgl_srzc.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_zjd_yqgl_srzc.isShown){
                        ll_frag_zjd_yqgl_srzc.visibility = View.GONE
                        mtl_frag_yqgl_srzc.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_zjd_yqgl_srzc.visibility = View.VISIBLE
                        mtl_frag_yqgl_srzc.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        
        
        if (AppCache.getInstance().type == 4){
            ll_frag_jtsz_gzjdd.visibility = View.VISIBLE
        }else{
            ll_frag_jtsz_gzjdd.visibility = View.GONE
        }
        
    }
    private fun zjdSpBar(homeEntitys:ArrayList<ZjdfjTjBean>){
        if (bct_frag_zjd_yqgl_kstj != null){
//            firstpage_ndqs_bc.setOnChartValueSelectedListener(this);
            bct_frag_zjd_yqgl_kstj.getDescription().setEnabled(false);
            bct_frag_zjd_yqgl_kstj.setMaxVisibleValueCount(40);
            // 扩展现在只能分别在x轴和y轴
            bct_frag_zjd_yqgl_kstj.setPinchZoom(false);
            bct_frag_zjd_yqgl_kstj.setDrawGridBackground(false);
            bct_frag_zjd_yqgl_kstj.setDrawBarShadow(false);
            bct_frag_zjd_yqgl_kstj.setDrawValueAboveBar(false);
            bct_frag_zjd_yqgl_kstj.setHighlightFullBarEnabled(false);
            bct_frag_zjd_yqgl_kstj.setScaleEnabled(false);
            bct_frag_zjd_yqgl_kstj.setDoubleTapToZoomEnabled(false);

            // 改变y标签的位置
            var leftAxis = bct_frag_zjd_yqgl_kstj.getAxisLeft()
            leftAxis.setValueFormatter(object:ValueFormatter(){})
            leftAxis.setAxisMinimum(0f);
            bct_frag_zjd_yqgl_kstj.getAxisRight().setEnabled(false)
            var xLabels = bct_frag_zjd_yqgl_kstj.getXAxis()
            xLabels.setPosition(XAxis.XAxisPosition.BOTTOM)
            xLabels.setGranularity(1f)
            xLabels.setLabelCount(homeEntitys.size)
            xLabels.setLabelRotationAngle(45f)
            xLabels.valueFormatter = object: ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()

                    return if (index < 0 || index >= homeEntitys.size) {
                        ""
                    } else {
                        homeEntitys.get(index).object1
                    }
//                    return super.getFormattedValue(value)
                }
            }
            /*val mMatrix = Matrix()
            mMatrix.postScale(0.5f, 1f)
            firstpage_ndqs_bc.viewPortHandler.refresh(mMatrix, firstpage_ndqs_bc, false)*/


            var l = bct_frag_zjd_yqgl_kstj.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            l.setFormSize(8f);
            l.setFormToTextSpace(4f);
            l.setXEntrySpace(6f);
            val arrayList = ArrayList<ArrayList<Float>>()
            for (i in 0..homeEntitys.size-1){//for (i in homeEntities.size-1 downTo 0){
                val yoyListEntity = homeEntitys.get(i)
                val arrayList1 = ArrayList<Float>()
                val zthj = yoyListEntity!!.object3
                val gcwt = yoyListEntity!!.object4
                val wswt = yoyListEntity!!.object5
                val ljwt = yoyListEntity!!.object6
                val crwt = yoyListEntity!!.object7
                arrayList1.add(zthj.toFloat())
                arrayList1.add(gcwt.toFloat())
                arrayList1.add(wswt.toFloat())
                arrayList1.add(ljwt.toFloat())
                arrayList1.add(crwt.toFloat())
                arrayList.add(arrayList1)
            }
            setData(arrayList,homeEntitys);//homeEntities
        }
    }

    //初始化
    private fun setData(stringList:ArrayList<ArrayList<Float>>,homeEntityList:ArrayList<ZjdfjTjBean>) {
        var yVals1 = ArrayList<BarEntry>()
        for (i in 0..stringList.size-1){
            val get = stringList.get(i)
            yVals1.add( BarEntry(i.toFloat(), floatArrayOf(get.get(0), get.get(1), get.get(2),get.get(3),get.get(4))))
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

        if (bct_frag_zjd_yqgl_kstj.getData() != null &&
                bct_frag_zjd_yqgl_kstj.getData().getDataSetCount() > 0) {
            set1 =  bct_frag_zjd_yqgl_kstj.getData().getDataSetByIndex(0) as BarDataSet
            set1.setValues(yVals1)
            bct_frag_zjd_yqgl_kstj.getData().notifyDataChanged()
            bct_frag_zjd_yqgl_kstj.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")//三年级一班期末考试
            set1.setColors(getColors()!!.toList())
            set1.setStackLabels( arrayOf("待签收", "待反馈", "待审核", "待退单", "已完成"))

            var dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            var data = BarData(dataSets);//MyValueFormatter
            data.setValueFormatter(object:ValueFormatter(){})
            data.setValueTextColor(Color.WHITE)

            bct_frag_zjd_yqgl_kstj.setData(data)
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
                textTemp = get.object1//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                textTemp += "\n"
                textTemp += "待签收:" + get1.get(0).toInt()
                textTemp += "\n"
                textTemp += "待反馈:" + get1.get(1).toInt()
                textTemp += "\n"
                textTemp += "待审核:" + get1.get(2).toInt()
                textTemp += "\n"
                textTemp += "待退单:" + get1.get(3).toInt()
                textTemp += "\n"
                textTemp += "已完成:" + get1.get(4).toInt()
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(bct_frag_zjd_yqgl_kstj)
        bct_frag_zjd_yqgl_kstj.setMarker(markerView)
        bct_frag_zjd_yqgl_kstj.data.setDrawValues(false)

        bct_frag_zjd_yqgl_kstj.setFitBars(true);
        bct_frag_zjd_yqgl_kstj.invalidate();
    }

    private fun getColors(): IntArray? {
        val stacksize = 5
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#FFB35F")
        colors[1] = Color.parseColor("#17D0EA")
        colors[2] = Color.parseColor("#6F9BF6")
        colors[3] = Color.parseColor("#F47457")
        colors[4] = Color.parseColor("#6ED9AF")
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
    }

    public fun setTwoBarChart( barChart:BarChart, xAxisValue:List<String> , yAxisValue1:List<Float>
                               , yAxisValue2:List<Float> , bartilte1:String , bartitle2:String ) {
        barChart.getDescription().setEnabled(false);//设置描述
        barChart.setPinchZoom(true);//设置按比例放缩柱状图
        barChart.setExtraBottomOffset(10f);
        barChart.setExtraTopOffset(30f);

        //x坐标轴设置
        var xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size);
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(IndexAxisValueFormatter(xAxisValue));

        //y轴设置
        var leftAxis = barChart.getAxisLeft();//YAxis
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setDrawLabels(false);
//        leftAxis.setDrawAxisLine(false);
        // 横向 网格线
        leftAxis.setDrawGridLines(true)
        leftAxis.setAxisMaximum(150f)//yMax
        leftAxis.setAxisMinimum(0f)//yMax
        val axisRight = barChart.axisRight
        axisRight.setAxisMaximum(100f)//yMax
        axisRight.setAxisMinimum(0f)//yMax
        axisRight.setEnabled(true)
        axisRight.valueFormatter = object:ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}%"//super.getFormattedValue(value)
            }
        }
        //设置坐标轴最大最小值
        var yMin1 = Collections.min(yAxisValue1)
        var yMin2 = Collections.min(yAxisValue2)
        var yMax1 = Collections.max(yAxisValue1)
        var yMax2 = Collections.max(yAxisValue2)
        var yMin = 0f//Double.valueOf((yMin1 < yMin2 ? yMin1 : yMin2) * 0.1).floatValue();
        var yMax = 0f//Double.valueOf((yMax1 > yMax2 ? yMax1 : yMax2) * 1.1).floatValue();

        if (yMin1 < yMin2){
            yMin = (yMin1 * 0.1).toFloat();
        }else{
            yMin = (yMin2 * 0.1).toFloat()
        }
        if (yMax1 < yMax2){
            yMax = (yMax1 * 1.1).toFloat();
        }else{
            yMax = (yMax2 * 1.1).toFloat();
        }
        //图例设置
        var legend = barChart.getLegend()
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER)
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        legend.setDrawInside(false)
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT)
        legend.setForm(Legend.LegendForm.SQUARE)
        legend.setTextSize(12f)

        //设置柱状图数据
        setTwoBarChartData(barChart, xAxisValue, yAxisValue1, yAxisValue2, bartilte1, bartitle2)

        barChart.animateX(1500)//数据显示动画，从左往右依次显示
        barChart.invalidate()
    }
    /**
     * 设置柱状图数据源
     */
    private fun setTwoBarChartData(barChart:BarChart , xAxisValue:List<String> , yAxisValue1:List<Float> , yAxisValue2:List<Float>
                                   , bartilte1:String , bartitle2:String ) {
        var groupSpace = 0.04f;
        var barSpace = 0.03f;
        var barWidth = 0.45f;
        // (0.45 + 0.03) * 2 + 0.04 = 1，即一个间隔为一组，包含两个柱图 -> interval per "group"

        var entries1 = ArrayList<BarEntry>();
        var entries2 = ArrayList<BarEntry>();
        for (i in 0..yAxisValue1.size-1){
            entries1.add(BarEntry(i.toFloat(), floatArrayOf(yAxisValue1.get(i),10f,20f,35f)));
            entries2.add(BarEntry(i.toFloat(), yAxisValue2.get(i)));
        }


        var dataset1: BarDataSet? = null
        var dataset2: BarDataSet? = null

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            dataset1 =  barChart.getData().getDataSetByIndex(0) as BarDataSet
            dataset2 =  barChart.getData().getDataSetByIndex(1) as BarDataSet
            dataset1.setValues(entries1)
            dataset2.setValues(entries2)
            barChart.getData().notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            dataset1 = BarDataSet(entries1, bartilte1)
            dataset2 = BarDataSet(entries2, bartitle2)
            dataset1.setStackLabels(arrayOf("待签收", "待反馈", "待审核", "待退单"))
//            dataset2.setStackLabels(arrayOf("已完成"))
            dataset2.label = "已完成"
            dataset1.setColors(getColorsSP())
            dataset2.setColors(getColorsSP1())

            var dataSets = ArrayList<IBarDataSet>()
            dataSets.add(dataset1)
            dataSets.add(dataset2)

            var data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.setBarWidth(0.9f)

            data.setValueFormatter(object : ValueFormatter() {
                override fun getFormattedValue(value: Float, entry: Entry?, dataSetIndex: Int, viewPortHandler: ViewPortHandler?): String {
                    return "${value}"//StringUtils.double2String(value, 2)
                }
                /*@Override
                public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    return StringUtils.double2String(value, 2);
                }*/
            })
            barChart.setData(data)
        }
        barChart.getBarData().setBarWidth(barWidth)
        barChart.getXAxis().setAxisMinimum(0f)
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * xAxisValue.size + 0)
        barChart.groupBars(0f, groupSpace, barSpace)
    }


    override fun returnOpinionGetCount(entity: List<JsjbTjBean>) {
        val arrayList = ArrayList<JsjbTjBean>()
        arrayList.add(JsjbTjBean(1,0))
        arrayList.add(JsjbTjBean(2,0))
        arrayList.add(JsjbTjBean(3,0))
        arrayList.add(JsjbTjBean(4,0))
        arrayList.add(JsjbTjBean(200,0))
        arrayList.add(JsjbTjBean(5,0))
        //        arrayList.add(JsjbTjBean(6,0))
        for (i in arrayList){
            for (f in entity){
                if (i.object1 == f.object1){
                    i.object2 = f.object2
                }
            }
        }

        //操作员没有 退单  5 否则 3
        rlv_frag_zjd_yqgl.layoutManager = GridLayoutManager(activity,3)
        rlv_frag_zjd_yqgl.adapter = object:BaseQuickAdapter<JsjbTjBean,BaseViewHolder>(R.layout.item_jsjbtj_list_layout,arrayList){
            override fun convert(helper: BaseViewHolder?, item: JsjbTjBean?) {
                //getObject1Name
                helper!!.setText(R.id.tv_item_jsjbtj_list_bzname, item!!.object1Name)
                        .setText(R.id.tv_item_jsjbtj_list_bzcount, "${item!!.getObject2()}")
                helper.itemView.setOnClickListener {
                    var intent = Intent(activity,AjlbActivity::class.java)
                    intent.putExtra("qutype",item.getObject1())
                    startActivity(intent)
                }
            }
        }

    }


    override fun returnNyyItemList(entity: List<YztNyyqEntity>) {

    }

    override fun returnNyyqCunList(entity: List<NydVo>) {

    }

    override fun returnBcjcGetCjjjsrzc(msg: SrZcBean) {
        if (msg!=null){
            var values1 = ArrayList<Entry>()
            var values2 = ArrayList<Entry>()
            var strings = ArrayList<String>()
            val callDurationColors = intArrayOf(Color.parseColor("#5B9BD5"), Color.parseColor("#ED7D31"))
            val labels = arrayOf("收入", "支出")
            val bcjtjjsrqkEntities = msg.getBcjtjjsrqkEntities()
            val bcjtjjzcqkEntitys = msg.getBcjtjjzcqkEntitys()
            if (bcjtjjsrqkEntities!=null&&bcjtjjsrqkEntities.size==0||bcjtjjzcqkEntitys!=null&&bcjtjjzcqkEntitys.size==0){
                return
            }
            /*if (bcjtjjsrqkEntities.size>bcjtjjzcqkEntitys.size){

            }else{

            }*/
            for (i in bcjtjjsrqkEntities.indices){
                val get = bcjtjjsrqkEntities.get(i)
                strings.add(get.years)
            }
            for (i in bcjtjjzcqkEntitys.indices){
                val get1 = bcjtjjzcqkEntitys.get(i)
                strings.add(get1.years)
            }
            try {
                strings = strings.toSortedSet().toList() as ArrayList<String>
            } catch (e: Exception) {
            }

            //按年龄排序 strings =  last
            val sortedByDescending = strings.sortedByDescending { it.first() }
            /*val numbers = listOf("one", "two", "three", "four")

            val sortedNumbers = numbers.sortedBy { it.length }
            println("Sorted by length ascending: $sortedNumbers")
            val sortedByLast = strings.sortedByDescending { it.last() }
            println("Sorted by the last letter descending: $sortedByLast")*/
            strings =  ArrayList<String>(sortedByDescending)
//            val arrayList = Array<ArrayList<Entry>>(strings.size)
            val arrayList = arrayOfNulls<ArrayList<Entry>>(labels.size)
            for (i in strings.indices){
                for (s in bcjtjjsrqkEntities){
                    if (strings.get(i).equals(s.years)){
                        values1.add(Entry(i.toFloat()+0.5f,s.hj.toFloat(),s.years))
                    }
                }
                for (s in bcjtjjzcqkEntitys){
                    if (strings.get(i).equals(s.years)){
                        values2.add(Entry(i.toFloat()+0.5f,s.hj.toFloat(),s.years))
                    }
                }
            }
            arrayList[0]=values1
            arrayList[1] = values2
            if (values1.size>0||values2.size>0){
                if (AppCache.getInstance().type!=4)
                mtl_frag_yqgl_srzc.visibility = View.VISIBLE
            }else{
                mtl_frag_yqgl_srzc.visibility = View.GONE
                ll_frag_zjd_yqgl_srzc.visibility = View.GONE
            }
            PieTdxgUtil.updateLinehart1(activity!!,lcivp_frag_zjd_yqgl_srzc,callDurationColors,"",arrayList,labels,strings)
        }
    }
    override fun returnOpinionGetStatistical(entity: JsjbTj2Bean) {
        if (entity!=null){
            val total = entity.getTotal()
            tv_frag_zjd_yqgl_wtzs.setText("${total.getObject1()}")
            tv_frag_zjd_yqgl_dbwt.setText("${total.getObject2()}")
            val num = total.getObject4().toBigDecimal().divide(total.getObject3().toBigDecimal(), 2, RoundingMode.HALF_UP)
            tv_frag_zjd_yqgl_myl.setText("${num.multiply(BigDecimal(100)).setScale(0, RoundingMode.HALF_UP)}%")


            val bhgsCount = ArrayList<Float>()//数量
            val bhgsName = ArrayList<String>()//类型
            var legendList = ArrayList<LegendBean>()
            val problemType = entity.getProblemType()
            for (i in problemType.indices){
                val problem = problemType.get(i)
                if (i<5){
                    legendList.add(LegendBean(problem.problemTypeText, problem.counts, colorList.get(i)))
                }
            }
            /*legendList.add(LegendBean("民事纠纷", 152, colorList.get(0)))
            legendList.add(LegendBean("生活管理", 336, colorList.get(1)))
            legendList.add(LegendBean("房屋产权", 452, colorList.get(2)))
            legendList.add(LegendBean("公共环境", 452, colorList.get(3)))
            legendList.add(LegendBean("其他问题", 452, colorList.get(4)))*/
            for ( i in legendList){
                bhgsCount.add(i.count.toFloat())
                bhgsName.add(i.name)
            }
            if (pie_frag_yqgl_msjfd != null) {
                rkinitPie(pie_frag_yqgl_msjfd, "", bhgsCount, bhgsName, colorList)//总数
            }
            rlv_frag_yqgl_msjfd.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)//图例显示
            val hylxTlAdapter = HylxTlAdapter(activity, legendList)
            rlv_frag_yqgl_msjfd.adapter = hylxTlAdapter//LegendBean

        }

    }

    private fun getColorsSP(): ArrayList<Int> {//IntArray
        val stacksize = 4
        //有尽可能多的颜色每项堆栈值
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#FFB35F"))
        colors.add(Color.parseColor("#17D0EA"))
        colors.add(Color.parseColor("#6F9BF6"))
        colors.add(Color.parseColor("#F47457"))
    return colors
}
    private fun getColorsSP1(): ArrayList<Int> {
        val stacksize = 1
        //有尽可能多的颜色每项堆栈值
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#6ED9AF"))
    return colors
}

    fun initColors(){
        colorList.add(Color.argb(199, 254, 102, 14))
        colorList.add(Color.argb(199, 64, 158, 254))
        colorList.add(Color.argb(199, 127, 143, 238))
        colorList.add(Color.argb(199, 47, 201, 251))
        colorList.add(Color.argb(199, 154, 151, 155))
    }
    //问题统计饼状图
    private fun rkinitPie(pie: PieChart, title: String, datas: java.util.ArrayList<Float>, lables: java.util.ArrayList<String>, colors: List<Int>) {
        pie.setUsePercentValues(false)
        pie.description.isEnabled = false
        pie.setExtraOffsets(5f, 10f, 5f, 5f)

        pie.dragDecelerationFrictionCoef = 0.95f

        pie.setExtraOffsets(10f, 0f, 10f, 0f)

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
        setRKData(pie, datas, lables, colors)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    private fun setRKData(pie: PieChart, datas: List<Float>, lables: List<String>, colors: List<Int>) {
        val entries = java.util.ArrayList<PieEntry>()

        var zs = 0f
        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
            zs = zs + datas[i]
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val format = DecimalFormat("###.#")
                val format1 = DecimalFormat("###")
                return "${format.format(value / zs * 100)}%"//super.getFormattedValue(value)  format1.format(value)+
            }
        }
        // add a lot of colors


        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = com.github.mikephil.charting.data.PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data
        // undo all highlights
        pie.highlightValues(null)
        pie.invalidate()
    }

    fun diaoyong(){
        if (supl_frag_zjdgl1!=null&&slv_frag_zjd_ydgl!=null){
            supl_frag_zjdgl1?.setScrollableView(slv_frag_zjd_ydgl)

            mPresenter.getBcjcYears()//百村监测获取年
            sptypeList.clear()
            sptypeList.add("上报")
            sptypeList.add("乡镇审核")
            sptypeList.add("区级确认")
            sptypeList.add("提交成功")
            tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,-1,-1)
            /*tll_frag_zjd_yqgl_cjtjjzysr.setValueText(sptypeList,-1,-1)
            tll_frag_zjd_yqgl_cjtjjzyzc.setValueText(sptypeList,-1,-1)*/
        }
    }

    private val yearsList = ArrayList<String>()
    private val yearsListNull = ArrayList<String>()
    private fun yearPopup(yearList:List<String>){
        yearUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_years, slv_frag_zjd_ydgl)
        val contentView: View = yearUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_years_rlv)
        val confirm = contentView.findViewById<TextView>(R.id.pop_bcjc_years_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_years_close)


        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter = object :BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_bcjc_years,yearList){
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.item_bcjc_years_tv,item)
                val checkBox = helper.getView<CheckBox>(R.id.item_bcjc_years_cb)
                checkBox.setOnClickListener {
                    if (checkBox.isChecked){
                        yearsList.add(item!!)
                    }else{
                        yearsList.remove(item)
                    }
                }

            }


        }

        confirm.setOnClickListener {
            mPresenter.getBcjcCjjzsrqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBcjcCjtjjzysrqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBcjcGetCjjjsrzc(AppCache.getInstance().code,yearsList)
        }
        close.setOnClickListener {
            yearUpPopu!!.dismiss()
        }
    }

    var bcjjzsrqkUploadType = 0//判断是否保存
    var bcjtjjsrqkUploadType = 0
    var bcjtjjzcqkUploadType = 0
    var bcjjzsrqkEntity = BcjjzsrqkEntity()
    var bcjtjjsrqkEntity = BcjtjjsrqkEntity()
    var bcjtjjzcqkEntity = BcjtjjzcqkEntity()

    var bcjjzsrqkEntitysId = 0L
    var bcjtjjsrqkEntitysId = 0L
    var bcjtjjzcqkEntitysId = 0L
    var bcjjzsrqkEntitysProcess = 0
    var bcjtjjsrqkEntitysProcess = 0
    var bcjtjjzcqkEntitysProcess = 0
    override fun returnBcjcYears(msg: List<String>) {
        yearsList.clear()
        if (AppCache.getInstance().duties!=1&&AppCache.getInstance().type!=1){
            yearsList.add(msg.get(0))
            split = yearsList.get(0).substring(0,yearsList.get(0).length-1)
        }
        mPresenter.getBcjcCjjzsrqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjtjjzysrqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcGetCjjjsrzc(AppCache.getInstance().code,yearsList)

        mPresenter.getBcjcCjjzsrqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcCjtjjzysrqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcCjtjjzyzcqkAll(AppCache.getInstance().code,yearsListNull)
        yearPopup(msg)
        tv_frag_zjd_yqgl_xzsj.addText = msg.get(0)
        tv_frag_zjd_yqgl_xzsj.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("添加")){

                    val arrayList = ArrayList<String>()
                    arrayList.addAll(msg)

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_frag_zjd_yqgl_xzsj.addText = arrayList.get(options1)
//                tv_frag_tdly_xzsj.setText(arrayList.get(options1))
                        split = arrayList.get(options1).substring(0,arrayList.get(options1).length-1)
//                        year = arrayList.get(options1)
                        yearsList.clear()
                        yearsList.add(split+"年")
                        mPresenter.getBcjcCjjzsrqk(AppCache.getInstance().code,yearsList)
                        mPresenter.getBcjcCjtjjzysrqk(AppCache.getInstance().code,yearsList)
                        mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,yearsList)
                        mPresenter.getBcjcGetCjjjsrzc(AppCache.getInstance().code,yearsList)

                        mPresenter.getBcjcCjjzsrqkAll(AppCache.getInstance().code,yearsListNull)
                        mPresenter.getBcjcCjtjjzysrqkAll(AppCache.getInstance().code,yearsListNull)
                        mPresenter.getBcjcCjtjjzyzcqkAll(AppCache.getInstance().code,yearsListNull)
//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(arrayList.indexOf(tv_frag_zjd_yqgl_xzsj.addText))
                    pvOptions.setPicker(arrayList)

                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                    params.leftMargin = 0
                    params.rightMargin = 0
                    val contentContainer = pvOptions.getDialogContainerLayout()
                    contentContainer.setLayoutParams(params)
                    pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                    pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    pvOptions.show()

                }
            }
        })
        /*if (AppCache.getInstance().type == 4){
            tv_frag_jtsz_cjjzsrqk_add.text = "添加/修改"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_jtsz_cjjzsrqk_add.visibility = View.GONE
        }else{
            tv_frag_jtsz_cjjzsrqk_add.text = "操作"
        }*/
        //添加村经济总收入情况
        tv_frag_jtsz_cjjzsrqk_add.setOnClickListener {
            if (tv_frag_jtsz_cjjzsrqk_add.text.toString().equals("操作")){

            addCjjzsrqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_jtszcjjzsrqk, slv_frag_zjd_ydgl)
            val contentView: View = addCjjzsrqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_cjjzsrqk_spr)
            val tv_pop_add_jtszcjjzsrqk = contentView.findViewById<TextView>(R.id.tv_pop_add_jtszcjjzsrqk)
            val et_zsr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_zsr)
            val et_gyjj = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_gyjj)
            val et_cjtzzsr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_cjtzzsr)
            val et_cjtqysr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_cjtqysr)
            val et_fgyjjsr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_fgyjjsr)
            val et_nhsr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_nhsr)
            val et_nmhzs = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_nmhzs)
            val et_syqysr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_syqysr)
            val et_gtjy = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_gtjy)
            val et_ycsr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_ycsr)
            val et_ecsr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_ecsr)
            val et_scsr = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_scsr)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_cjjzsrqk_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_cjjzsrqk_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_cjjzsrqk_confirm)
            val reject = contentView.findViewById<TextView>(R.id.pop_add_cjjzsrqk_reject)
            val close = contentView.findViewById<TextView>(R.id.pop_add_cjjzsrqk_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_cjjzsrqk_ell)
                et_zsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_gyjj.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cjtzzsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cjtqysr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_fgyjjsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_nhsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_nmhzs.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_syqysr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_gtjy.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_ycsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_ecsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_scsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);

            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addCjjzsrqkUpPopu!!.showAtLocation(slv_frag_zjd_ydgl, Gravity.CENTER, 0, 0)

//                tv_pop_add_jtszcjjzsrqk

                tv_pop_add_jtszcjjzsrqk.setText(tv_frag_zjd_yqgl_xzsj.addText)//msg.get(0)
                for (i in bcjjzsrqkEntitys){
                    if (tv_frag_zjd_yqgl_xzsj.addText.equals(i.years)){//msg.get(0)
                        et_zsr.setText(i.cjjzsr)
                        et_gyjj.setText(i.gyjj)
                        et_cjtzzsr.setText(i.cjtzzsr)
                        et_cjtqysr.setText(i.cjtqysr)
                        et_fgyjjsr.setText(i.fgysr)
                        et_nhsr.setText(i.nhsr)
                        et_nmhzs.setText(i.nmhzs)
                        et_syqysr.setText(i.syqysr)
                        et_gtjy.setText(i.gtjj)
                        et_ycsr.setText(i.ycsr)
                        et_ecsr.setText(i.ecsr)
                        et_scsr.setText(i.scsr)
                        bcjjzsrqkEntitysId = i.id
                        bcjjzsrqkEntitysProcess = i.process
                        if (AppCache.getInstance().type == 4){
                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bcjjzsrqkEntitysProcess){
                                1->{
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }
                                2->{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }else{
                            enableLl.setNoClick(true)
                            if (AppCache.getInstance().type == 3){
                                et_czrname.setText(i.zhenname)
                                if (bcjjzsrqkEntitysProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
                                et_czrname.setText(i.qvname)
                                if (bcjjzsrqkEntitysProcess == 3){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else{
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                        break
                    }else{
                        et_zsr.setText("")
                        et_gyjj.setText("")
                        et_cjtzzsr.setText("")
                        et_cjtqysr.setText("")
                        et_fgyjjsr.setText("")
                        et_nhsr.setText("")
                        et_nmhzs.setText("")
                        et_syqysr.setText("")
                        et_gtjy.setText("")
                        et_ycsr.setText("")
                        et_ecsr.setText("")
                        et_scsr.setText("")
                        bcjjzsrqkEntitysId = 0L
                        bcjjzsrqkEntitysProcess = 0
                        if (AppCache.getInstance().type == 4){
                            enableLl.setNoClick(false)
//                            confirm.visibility = View.VISIBLE
                            reject.visibility = View.VISIBLE
                        }else{
                            enableLl.setNoClick(true)
                            confirm.visibility = View.GONE
                            reject.visibility = View.GONE
                        }
                    }
                }
                /*tv_pop_add_jtszcjjzsrqk.setOnClickListener {

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_jtszcjjzsrqk.setText(msg.get(options1))

                        for (i in bcjjzsrqkEntitys){
                            if (msg.get(options1).equals(i.years)){
                                et_zsr.setText(i.cjjzsr)
                                et_gyjj.setText(i.gyjj)
                                et_cjtzzsr.setText(i.cjtzzsr)
                                et_cjtqysr.setText(i.cjtqysr)
                                et_fgyjjsr.setText(i.fgysr)
                                et_nhsr.setText(i.nhsr)
                                et_nmhzs.setText(i.nmhzs)
                                et_syqysr.setText(i.syqysr)
                                et_ycsr.setText(i.ycsr)
                                et_ecsr.setText(i.ecsr)
                                et_scsr.setText(i.scsr)
                                bcjjzsrqkEntitysId = i.id
                                bcjjzsrqkEntitysProcess = i.process
                                if (AppCache.getInstance().type == 4){
                                    et_czrname.setText(i.cunname)
                                    enableLl.setNoClick(false)
                                    when(bcjjzsrqkEntitysProcess){
                                        1->{
//                                            confirm.visibility = View.VISIBLE
                                            reject.visibility = View.VISIBLE
                                        }
                                        2->{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                }else{
                                    enableLl.setNoClick(true)
                                    if (AppCache.getInstance().type == 3){
                                        et_czrname.setText(i.zhenname)
                                        if (bcjjzsrqkEntitysProcess == 2){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else if (AppCache.getInstance().type == 2){
                                        et_czrname.setText(i.qvname)
                                        if (bcjjzsrqkEntitysProcess == 3){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else{
                                        et_czrname.setText(i.qvname)
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                                break
                            }else{
                                et_zsr.setText("")
                                et_gyjj.setText("")
                                et_cjtzzsr.setText("")
                                et_cjtqysr.setText("")
                                et_fgyjjsr.setText("")
                                et_nhsr.setText("")
                                et_nmhzs.setText("")
                                et_syqysr.setText("")
                                et_ycsr.setText("")
                                et_ecsr.setText("")
                                et_scsr.setText("")
                                bcjjzsrqkEntitysId = 0L
                                bcjjzsrqkEntitysProcess = 0
                                if (AppCache.getInstance().type == 4){
                                    enableLl.setNoClick(false)
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    enableLl.setNoClick(true)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }


//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_jtszcjjzsrqk.text.toString()))
                    pvOptions.setPicker(msg)

                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                    params.leftMargin = 0
                    params.rightMargin = 0
                    val contentContainer = pvOptions.getDialogContainerLayout()
                    contentContainer.setLayoutParams(params)
                    pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                    pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    pvOptions.show()

                }*/
            when(AppCache.getInstance().type){
                2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {
                        val entity = BcjjzsrqkEntity()
                        entity.cjjzsr = et_zsr.text.toString()
                        entity.gyjj = et_gyjj.text.toString()
                        entity.cjtzzsr = et_cjtzzsr.text.toString()
                        entity.cjtqysr = et_cjtqysr.text.toString()
                        entity.fgysr = et_fgyjjsr.text.toString()
                        entity.nhsr = et_nhsr.text.toString()
                        entity.nmhzs = et_nmhzs.text.toString()
                        entity.syqysr = et_syqysr.text.toString()
                        entity.gtjj = et_gtjy.text.toString()
                        entity.ycsr = et_ycsr.text.toString()
                        entity.ecsr = et_ecsr.text.toString()
                        entity.scsr = et_scsr.text.toString()
                        entity.years = tv_pop_add_jtszcjjzsrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bcjjzsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCjjzsrqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        val entity = BcjjzsrqkEntity()
                        entity.cjjzsr = et_zsr.text.toString()
                        entity.gyjj = et_gyjj.text.toString()
                        entity.cjtzzsr = et_cjtzzsr.text.toString()
                        entity.cjtqysr = et_cjtqysr.text.toString()
                        entity.fgysr = et_fgyjjsr.text.toString()
                        entity.nhsr = et_nhsr.text.toString()
                        entity.nmhzs = et_nmhzs.text.toString()
                        entity.syqysr = et_syqysr.text.toString()
                        entity.gtjj = et_gtjy.text.toString()
                        entity.ycsr = et_ycsr.text.toString()
                        entity.ecsr = et_ecsr.text.toString()
                        entity.scsr = et_scsr.text.toString()
                        entity.years = tv_pop_add_jtszcjjzsrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjjzsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCjjzsrqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        val entity = BcjjzsrqkEntity()
                        entity.cjjzsr = et_zsr.text.toString()
                        entity.gyjj = et_gyjj.text.toString()
                        entity.cjtzzsr = et_cjtzzsr.text.toString()
                        entity.cjtqysr = et_cjtqysr.text.toString()
                        entity.fgysr = et_fgyjjsr.text.toString()
                        entity.nhsr = et_nhsr.text.toString()
                        entity.nmhzs = et_nmhzs.text.toString()
                        entity.syqysr = et_syqysr.text.toString()
                        entity.gtjj = et_gtjy.text.toString()
                        entity.ycsr = et_ycsr.text.toString()
                        entity.ecsr = et_ecsr.text.toString()
                        entity.scsr = et_scsr.text.toString()
                        entity.years = tv_pop_add_jtszcjjzsrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bcjjzsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCjjzsrqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        val entity = BcjjzsrqkEntity()
                        entity.cjjzsr = et_zsr.text.toString()
                        entity.gyjj = et_gyjj.text.toString()
                        entity.cjtzzsr = et_cjtzzsr.text.toString()
                        entity.cjtqysr = et_cjtqysr.text.toString()
                        entity.fgysr = et_fgyjjsr.text.toString()
                        entity.nhsr = et_nhsr.text.toString()
                        entity.nmhzs = et_nmhzs.text.toString()
                        entity.syqysr = et_syqysr.text.toString()
                        entity.gtjj = et_gtjy.text.toString()
                        entity.ycsr = et_ycsr.text.toString()
                        entity.ecsr = et_ecsr.text.toString()
                        entity.scsr = et_scsr.text.toString()
                        entity.years = tv_pop_add_jtszcjjzsrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjjzsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCjjzsrqk(entity)
                        }
                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        val entity = BcjjzsrqkEntity()
                        entity.cjjzsr = et_zsr.text.toString()
                        entity.gyjj = et_gyjj.text.toString()
                        entity.cjtzzsr = et_cjtzzsr.text.toString()
                        entity.cjtqysr = et_cjtqysr.text.toString()
                        entity.fgysr = et_fgyjjsr.text.toString()
                        entity.nhsr = et_nhsr.text.toString()
                        entity.nmhzs = et_nmhzs.text.toString()
                        entity.syqysr = et_syqysr.text.toString()
                        entity.gtjj = et_gtjy.text.toString()
                        entity.ycsr = et_ycsr.text.toString()
                        entity.ecsr = et_ecsr.text.toString()
                        entity.scsr = et_scsr.text.toString()
                        entity.years = tv_pop_add_jtszcjjzsrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }/*else if (TdlyUtils.setEditToast(et_zsr,et_gyjj,et_cjtzzsr,et_cjtqysr,et_fgyjjsr,et_nhsr,et_nmhzs,et_gtjy,et_syqysr,et_ycsr,et_ecsr,et_scsr)){
                        }*/else{
                            //
                            entity.cunname = et_czrname.text.toString()
                        if (bcjjzsrqkEntitysId==0L){
                            bcjjzsrqkEntity = entity
                            mPresenter.getBcjcAddCjjzsrqk(entity)
                        }else{
                            //修改接口
                            entity.id = bcjjzsrqkEntitysId
                            bcjjzsrqkEntity = entity
                            mPresenter.getBcjcUpdateCjjzsrqk(entity)
                        }

                        }

                    }
                    //村 下一步
                    confirm.setOnClickListener {
                        val entity = BcjjzsrqkEntity()
                        entity.cjjzsr = et_zsr.text.toString()
                        entity.gyjj = et_gyjj.text.toString()
                        entity.cjtzzsr = et_cjtzzsr.text.toString()
                        entity.cjtqysr = et_cjtqysr.text.toString()
                        entity.fgysr = et_fgyjjsr.text.toString()
                        entity.nhsr = et_nhsr.text.toString()
                        entity.nmhzs = et_nmhzs.text.toString()
                        entity.syqysr = et_syqysr.text.toString()
                        entity.gtjj = et_gtjy.text.toString()
                        entity.ycsr = et_ycsr.text.toString()
                        entity.ecsr = et_ecsr.text.toString()
                        entity.scsr = et_scsr.text.toString()
                        entity.years = tv_pop_add_jtszcjjzsrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2

                        //修改接口
                        if (TdlyUtils.setEditToast(et_zsr,et_gyjj,et_cjtzzsr,et_cjtqysr,et_fgyjjsr,et_nhsr,et_nmhzs,et_gtjy,et_syqysr,et_ycsr,et_ecsr,et_scsr)){
                        }else{
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            if (bcjjzsrqkEntitysId == 0L){
                                entity.cunname = et_czrname.text.toString()
                                mPresenter.getBcjcAddCjjzsrqk(entity)
                            }else{
                                entity.id = bcjjzsrqkEntitysId
                                entity.cunname = et_czrname.text.toString()
                                mPresenter.getBcjcUpdateCjjzsrqk(entity)
                            }
                        }
                        }
                    }
                }
            }
            
            close.setOnClickListener {
                addCjjzsrqkUpPopu!!.dismiss()
            }
            }
        }
        /*if (AppCache.getInstance().type == 4){
            tv_frag_jtsz_cjtjjzysrqk_add.text = "添加/修改"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_jtsz_cjtjjzysrqk_add.visibility = View.GONE
        }else{
            tv_frag_jtsz_cjtjjzysrqk_add.text = "操作"
        }*/
        //添加村集体经济主要收入情况
        tv_frag_jtsz_cjtjjzysrqk_add.setOnClickListener {
            if (tv_frag_jtsz_cjtjjzysrqk_add.text.toString().equals("操作")){

            addCjtjjzysrqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_jtszcjtjjzysrqk, slv_frag_zjd_ydgl)
            val contentView: View = addCjtjjzysrqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_cjtjjzysrqk_spr)
            val tv_pop_add_jtszcjtjjzysrqk = contentView.findViewById<TextView>(R.id.tv_pop_add_jtszcjtjjzysrqk)
            val et_zyywsr = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_zyywsr)
            val et_sdlcf = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_sdlcf)
            val et_yywsr = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_yywsr)
            val et_cjgysy = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_cjgysy)
            val et_cdzzfw = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_cdzzfw)
            val et_mlxc = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_mlxc)
            val et_qtzcbz = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_qtzcbz)
            val et_tzsy = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_tzsy)
            val et_qtsy = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_qtsy)
            val et_hj = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_hj)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_cjtjjzysrqk_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzysrqk_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_cjtjjzysrqk_confirm)
            val reject = contentView.findViewById<TextView>(R.id.pop_add_cjtjjzysrqk_reject)
            val close = contentView.findViewById<TextView>(R.id.pop_add_cjtjjzysrqk_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_cjtjjzysrqk_ell)
                et_zyywsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_sdlcf.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_yywsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cjgysy.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cdzzfw.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_mlxc.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_qtzcbz.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_tzsy.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_hj.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);

            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addCjtjjzysrqkUpPopu!!.showAtLocation(slv_frag_zjd_ydgl, Gravity.CENTER, 0, 0)

//                tv_pop_add_jtszcjtjjzysrqk

                tv_pop_add_jtszcjtjjzysrqk.setText(tv_frag_zjd_yqgl_xzsj.addText)//msg.get(0)
                for (i in bcjtjjsrqkEntitys){
                    if (tv_frag_zjd_yqgl_xzsj.addText.equals(i.years)){//msg.get(0)
                        et_zyywsr.setText(i.zyywsr)
                        et_sdlcf.setText(i.qtywsr)
                        et_tzsy.setText(i.tzsy)
                        et_cjgysy.setText(i.gybzjf)
                        et_cdzzfw.setText(i.dzzfwjf)
                        et_mlxc.setText(i.mlxcjswh)
                        et_qtzcbz.setText(i.zcbz)
                        et_yywsr.setText(i.yywsr)
                        et_qtsy.setText(i.qt)
                        et_hj.setText(i.hj)
                        bcjtjjsrqkEntitysId = i.id
                        bcjtjjsrqkEntitysProcess = i.process
                        if (AppCache.getInstance().type == 4){
                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bcjtjjsrqkEntitysProcess){
                                1->{
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }
                                2->{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }else{
                            enableLl.setNoClick(true)
                            if (AppCache.getInstance().type == 3){
                                et_czrname.setText(i.zhenname)
                                if (bcjtjjsrqkEntitysProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
                                et_czrname.setText(i.qvname)
                                if (bcjtjjsrqkEntitysProcess == 3){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else{
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                        break
                    }else{
                        et_zyywsr.setText("")
                        et_sdlcf.setText("")
                        et_tzsy.setText("")
                        et_cjgysy.setText("")
                        et_cdzzfw.setText("")
                        et_mlxc.setText("")
                        et_qtzcbz.setText("")
                        et_yywsr.setText("")
                        et_qtsy.setText("")
                        et_hj.setText("")
                        bcjtjjsrqkEntitysId = 0L
                        bcjtjjsrqkEntitysProcess = 0
                        if (AppCache.getInstance().type == 4){
                            enableLl.setNoClick(false)
//                            confirm.visibility = View.VISIBLE
                            reject.visibility = View.VISIBLE
                        }else{
                            enableLl.setNoClick(true)
                            confirm.visibility = View.GONE
                            reject.visibility = View.GONE
                        }
                    }
                }
                /*tv_pop_add_jtszcjtjjzysrqk.setOnClickListener {

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_jtszcjtjjzysrqk.setText(msg.get(options1))

                        for (i in bcjtjjsrqkEntitys){
                            if (msg.get(options1).equals(i.years)){
                                et_zyywsr.setText(i.zyywsr)
                                et_sdlcf.setText(i.qtywsr)
                                et_tzsy.setText(i.tzsy)
                                et_cjgysy.setText(i.gybzjf)
                                et_cdzzfw.setText(i.dzzfwjf)
                                et_mlxc.setText(i.mlxcjswh)
                                et_qtzcbz.setText(i.zcbz)
                                et_yywsr.setText(i.yywsr)
                                et_qtsy.setText(i.qt)
                                et_hj.setText(i.hj)
                                bcjtjjsrqkEntitysId = i.id
                                bcjtjjsrqkEntitysProcess = i.process
                                if (AppCache.getInstance().type == 4){
                                    et_czrname.setText(i.cunname)
                                    enableLl.setNoClick(false)
                                    when(bcjtjjsrqkEntitysProcess){
                                        1->{
//                                            confirm.visibility = View.VISIBLE
                                            reject.visibility = View.VISIBLE
                                        }
                                        2->{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                }else{
                                    enableLl.setNoClick(true)
                                    if (AppCache.getInstance().type == 3){
                                        et_czrname.setText(i.zhenname)
                                        if (bcjtjjsrqkEntitysProcess == 2){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else if (AppCache.getInstance().type == 2){
                                        et_czrname.setText(i.qvname)
                                        if (bcjtjjsrqkEntitysProcess == 3){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                                break
                            }else{
                                et_zyywsr.setText("")
                                et_sdlcf.setText("")
                                et_tzsy.setText("")
                                et_cjgysy.setText("")
                                et_cdzzfw.setText("")
                                et_mlxc.setText("")
                                et_qtzcbz.setText("")
                                et_yywsr.setText("")
                                et_qtsy.setText("")
                                et_hj.setText("")
                                bcjtjjsrqkEntitysId = 0L
                                bcjtjjsrqkEntitysProcess = 0
                                if (AppCache.getInstance().type == 4){
                                    enableLl.setNoClick(false)
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    enableLl.setNoClick(true)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }


//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_jtszcjtjjzysrqk.text.toString()))
                    pvOptions.setPicker(msg)

                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                    params.leftMargin = 0
                    params.rightMargin = 0
                    val contentContainer = pvOptions.getDialogContainerLayout()
                    contentContainer.setLayoutParams(params)
                    pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                    pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    pvOptions.show()

                }*/
            when(AppCache.getInstance().type){
                2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {
                        val entity = BcjtjjsrqkEntity()
                        entity.zyywsr = et_zyywsr.text.toString()
                        entity.qtywsr = et_sdlcf.text.toString()
                        entity.tzsy = et_tzsy.text.toString()
                        entity.gybzjf = et_cjgysy.text.toString()
                        entity.dzzfwjf = et_cdzzfw.text.toString()
                        entity.mlxcjswh = et_mlxc.text.toString()
                        entity.zcbz = et_qtzcbz.text.toString()
                        entity.yywsr = et_yywsr.text.toString()
                        entity.qt = et_qtsy.text.toString()
                        entity.hj = TdlyUtils.getSum(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr).toString()//et_hj.text.toString()
                        entity.years = tv_pop_add_jtszcjtjjzysrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bcjtjjsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCjtjjzysrqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        val entity = BcjtjjsrqkEntity()
                        entity.zyywsr = et_zyywsr.text.toString()
                        entity.qtywsr = et_sdlcf.text.toString()
                        entity.tzsy = et_tzsy.text.toString()
                        entity.gybzjf = et_cjgysy.text.toString()
                        entity.dzzfwjf = et_cdzzfw.text.toString()
                        entity.mlxcjswh = et_mlxc.text.toString()
                        entity.zcbz = et_qtzcbz.text.toString()
                        entity.yywsr = et_yywsr.text.toString()
                        entity.qt = et_qtsy.text.toString()
                        entity.hj = TdlyUtils.getSum(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr).toString()
                        entity.years = tv_pop_add_jtszcjtjjzysrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjtjjsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCjtjjzysrqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        val entity = BcjtjjsrqkEntity()
                        entity.zyywsr = et_zyywsr.text.toString()
                        entity.qtywsr = et_sdlcf.text.toString()
                        entity.tzsy = et_tzsy.text.toString()
                        entity.gybzjf = et_cjgysy.text.toString()
                        entity.dzzfwjf = et_cdzzfw.text.toString()
                        entity.mlxcjswh = et_mlxc.text.toString()
                        entity.zcbz = et_qtzcbz.text.toString()
                        entity.yywsr = et_yywsr.text.toString()
                        entity.qt = et_qtsy.text.toString()
                        entity.hj = TdlyUtils.getSum(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr).toString()
                        entity.years = tv_pop_add_jtszcjtjjzysrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bcjtjjsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCjtjjzysrqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        val entity = BcjtjjsrqkEntity()
                        entity.zyywsr = et_zyywsr.text.toString()
                        entity.qtywsr = et_sdlcf.text.toString()
                        entity.tzsy = et_tzsy.text.toString()
                        entity.gybzjf = et_cjgysy.text.toString()
                        entity.dzzfwjf = et_cdzzfw.text.toString()
                        entity.mlxcjswh = et_mlxc.text.toString()
                        entity.zcbz = et_qtzcbz.text.toString()
                        entity.yywsr = et_yywsr.text.toString()
                        entity.qt = et_qtsy.text.toString()
                        entity.hj = TdlyUtils.getSum(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr).toString()
                        entity.years = tv_pop_add_jtszcjtjjzysrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjtjjsrqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCjtjjzysrqk(entity)
                        }
                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        val entity = BcjtjjsrqkEntity()
                        entity.zyywsr = et_zyywsr.text.toString()
                        entity.qtywsr = et_sdlcf.text.toString()
                        entity.tzsy = et_tzsy.text.toString()
                        entity.gybzjf = et_cjgysy.text.toString()
                        entity.dzzfwjf = et_cdzzfw.text.toString()
                        entity.mlxcjswh = et_mlxc.text.toString()
                        entity.zcbz = et_qtzcbz.text.toString()
                        entity.yywsr = et_yywsr.text.toString()
                        entity.qt = et_qtsy.text.toString()
                        entity.hj = TdlyUtils.getSum(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr).toString()
                        entity.years = tv_pop_add_jtszcjtjjzysrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }/*else if (TdlyUtils.setEditToast(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr,et_qtsy)){
                        }*/else{
                            entity.cunname = et_czrname.text.toString()
                        if (bcjtjjsrqkEntitysId==0L){
                            bcjtjjsrqkEntity = entity
                            mPresenter.getBcjcAddCjtjjzysrqk(entity)

                        }else{
                            //修改接口
                            entity.id = bcjtjjsrqkEntitysId
                            bcjtjjsrqkEntity = entity
                            mPresenter.getBcjcUpdateCjtjjzysrqk(entity)
                        }

                        }

                    }
                    //村 下一步
                    confirm.setOnClickListener {
                        val entity = BcjtjjsrqkEntity()
                        entity.zyywsr = et_zyywsr.text.toString()
                        entity.qtywsr = et_sdlcf.text.toString()
                        entity.tzsy = et_tzsy.text.toString()
                        entity.gybzjf = et_cjgysy.text.toString()
                        entity.dzzfwjf = et_cdzzfw.text.toString()
                        entity.mlxcjswh = et_mlxc.text.toString()
                        entity.zcbz = et_qtzcbz.text.toString()
                        entity.yywsr = et_yywsr.text.toString()
                        entity.qt = et_qtsy.text.toString()
                        entity.hj = TdlyUtils.getSum(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr).toString()
                        entity.years = tv_pop_add_jtszcjtjjzysrqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2

                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (TdlyUtils.setEditToast(et_zyywsr,et_sdlcf,et_tzsy,et_cjgysy,et_cdzzfw,et_mlxc,et_qtzcbz,et_yywsr,et_qtsy)){
                        }else {
                                //修改接口
                                entity.cunname = et_czrname.text.toString()
                            if (bcjtjjsrqkEntitysId == 0L) {
                                mPresenter.getBcjcAddCjtjjzysrqk(entity)
                            } else {
                                entity.id = bcjtjjsrqkEntitysId
                                mPresenter.getBcjcUpdateCjtjjzysrqk(entity)
                            }
                        }
                    }
                }
            }
            
            close.setOnClickListener {
                addCjtjjzysrqkUpPopu!!.dismiss()
            }
            }
        }
        /*if (AppCache.getInstance().type == 4){
            tv_frag_jtsz_cjtjjzyzcqk_add.text = "添加/修改"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_jtsz_cjtjjzyzcqk_add.visibility = View.GONE
        }else{
            tv_frag_jtsz_cjtjjzyzcqk_add.text = "操作"
        }*/
        tv_frag_jtsz_cjtjjzyzcqk_add.setOnClickListener {
            if (tv_frag_jtsz_cjtjjzyzcqk_add.text.toString().equals("操作")){

            addCjtjjzyzcqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_jtszcjtjjzyzcqk, slv_frag_zjd_ydgl)
            val contentView: View = addCjtjjzyzcqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_cjtjjzyzcqk_spr)
            val tv_pop_add_jtszcjtjjzyzcqk = contentView.findViewById<TextView>(R.id.tv_pop_add_jtszcjtjjzyzcqk)
            val et_cgbbt = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_cgbbt)
            val et_cjzzbgjf = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_cjzzbgjf)
            val et_ggfwyxwhf = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_ggfwyxwhf)
            val et_gyxjcssjstr = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_gyxjcssjstr)
            val et_cjfdqtsdf = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_cjfdqtsdf)
            val et_fl = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_fl)
            val et_qt = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_qt)
            val et_hj = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_hj)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_cjtjjzyzcqk_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_cjtjjzyzcqk_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_cjtjjzyzcqk_confirm)
            val reject = contentView.findViewById<TextView>(R.id.pop_add_cjtjjzyzcqk_reject)
            val close = contentView.findViewById<TextView>(R.id.pop_add_cjtjjzyzcqk_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_cjtjjzyzcqk_ell)
                et_cgbbt.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cjzzbgjf.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_ggfwyxwhf.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_gyxjcssjstr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cjfdqtsdf.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_fl.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_hj.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);

            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addCjtjjzyzcqkUpPopu!!.showAtLocation(slv_frag_zjd_ydgl, Gravity.CENTER, 0, 0)

//                tv_pop_add_jtszcjtjjzyzcqk

                tv_pop_add_jtszcjtjjzyzcqk.setText(tv_frag_zjd_yqgl_xzsj.addText)//msg.get(0)
                for (i in bcjtjjzcqkEntitys){
                    if (tv_frag_zjd_yqgl_xzsj.addText.equals(i.years)){//msg.get(0)
                        et_cgbbt.setText(i.cgbbt)
                        et_cjzzbgjf.setText(i.czzbgjf)
                        et_ggfwyxwhf.setText(i.ggyxwhf)
                        et_gyxjcssjstr.setText(i.gysstr)
                        et_cjfdqtsdf.setText(i.cjqtsdf)
                        et_fl.setText(i.fl)
                        et_qt.setText(i.qt)
                        et_hj.setText(i.hj)
                        bcjtjjzcqkEntitysId = i.id
                        bcjtjjzcqkEntitysProcess = i.process
                        if (AppCache.getInstance().type == 4){
                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bcjtjjzcqkEntitysProcess){
                                1->{
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }
                                2->{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }else{
                            enableLl.setNoClick(true)
                            if (AppCache.getInstance().type == 3){
                                et_czrname.setText(i.zhenname)
                                if (bcjtjjzcqkEntitysProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
                                et_czrname.setText(i.qvname)
                                if (bcjtjjzcqkEntitysProcess == 3){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else{
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                        break
                    }else{
                        et_cgbbt.setText("")
                        et_cjzzbgjf.setText("")
                        et_ggfwyxwhf.setText("")
                        et_gyxjcssjstr.setText("")
                        et_cjfdqtsdf.setText("")
                        et_fl.setText("")
                        et_qt.setText("")
                        et_hj.setText("")
                        bcjtjjzcqkEntitysId = 0L
                        bcjtjjzcqkEntitysProcess = 0
                        if (AppCache.getInstance().type == 4){
                            enableLl.setNoClick(false)
//                            confirm.visibility = View.VISIBLE
                            reject.visibility = View.VISIBLE
                        }else{
                            enableLl.setNoClick(true)
                            confirm.visibility = View.GONE
                            reject.visibility = View.GONE
                        }
                    }
                }
                /*tv_pop_add_jtszcjtjjzyzcqk.setOnClickListener {

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_jtszcjtjjzyzcqk.setText(msg.get(options1))

                        for (i in bcjtjjzcqkEntitys){
                            if (msg.get(options1).equals(i.years)){
                                et_cgbbt.setText(i.cgbbt)
                                et_cjzzbgjf.setText(i.czzbgjf)
                                et_ggfwyxwhf.setText(i.ggyxwhf)
                                et_gyxjcssjstr.setText(i.gysstr)
                                et_cjfdqtsdf.setText(i.cjqtsdf)
                                et_fl.setText(i.fl)
                                et_qt.setText(i.qt)
                                et_hj.setText(i.hj)
                                bcjtjjzcqkEntitysId = i.id
                                bcjtjjzcqkEntitysProcess = i.process
                                if (AppCache.getInstance().type == 4){
                                    et_czrname.setText(i.cunname)
                                    enableLl.setNoClick(false)
                                    when(bcjtjjzcqkEntitysProcess){
                                        1->{
//                                            confirm.visibility = View.VISIBLE
                                            reject.visibility = View.VISIBLE
                                        }
                                        2->{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                }else{
                                    enableLl.setNoClick(true)
                                    if (AppCache.getInstance().type == 3){
                                        et_czrname.setText(i.zhenname)
                                        if (bcjtjjzcqkEntitysProcess == 2){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else if (AppCache.getInstance().type == 2){
                                        et_czrname.setText(i.qvname)
                                        if (bcjtjjzcqkEntitysProcess == 3){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                                break
                            }else{
                                et_cgbbt.setText("")
                                et_cjzzbgjf.setText("")
                                et_ggfwyxwhf.setText("")
                                et_gyxjcssjstr.setText("")
                                et_cjfdqtsdf.setText("")
                                et_fl.setText("")
                                et_qt.setText("")
                                et_hj.setText("")
                                bcjtjjzcqkEntitysId = 0L
                                bcjtjjzcqkEntitysProcess = 0
                                if (AppCache.getInstance().type == 4){
                                    enableLl.setNoClick(false)
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    enableLl.setNoClick(true)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }


//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_jtszcjtjjzyzcqk.text.toString()))
                    pvOptions.setPicker(msg)

                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                    params.leftMargin = 0
                    params.rightMargin = 0
                    val contentContainer = pvOptions.getDialogContainerLayout()
                    contentContainer.setLayoutParams(params)
                    pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                    pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    pvOptions.show()

                }*/
            when(AppCache.getInstance().type){
                2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {

                        val entity = BcjtjjzcqkEntity()
                        entity.cgbbt = et_cgbbt.text.toString()
                        entity.czzbgjf = et_cjzzbgjf.text.toString()
                        entity.ggyxwhf = et_ggfwyxwhf.text.toString()
                        entity.gysstr = et_gyxjcssjstr.text.toString()
                        entity.cjqtsdf = et_cjfdqtsdf.text.toString()
                        entity.fl = et_fl.text.toString()
                        entity.qt = et_qt.text.toString()
                        entity.hj = TdlyUtils.getSum(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl).toString()//et_hj.text.toString()
                        entity.years = tv_pop_add_jtszcjtjjzyzcqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bcjtjjzcqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCjtjjzyzcqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {

                        val entity = BcjtjjzcqkEntity()
                        entity.cgbbt = et_cgbbt.text.toString()
                        entity.czzbgjf = et_cjzzbgjf.text.toString()
                        entity.ggyxwhf = et_ggfwyxwhf.text.toString()
                        entity.gysstr = et_gyxjcssjstr.text.toString()
                        entity.cjqtsdf = et_cjfdqtsdf.text.toString()
                        entity.fl = et_fl.text.toString()
                        entity.qt = et_qt.text.toString()
                        entity.hj = TdlyUtils.getSum(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl).toString()
                        entity.years = tv_pop_add_jtszcjtjjzyzcqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjtjjzcqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCjtjjzyzcqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {

                        val entity = BcjtjjzcqkEntity()
                        entity.cgbbt = et_cgbbt.text.toString()
                        entity.czzbgjf = et_cjzzbgjf.text.toString()
                        entity.ggyxwhf = et_ggfwyxwhf.text.toString()
                        entity.gysstr = et_gyxjcssjstr.text.toString()
                        entity.cjqtsdf = et_cjfdqtsdf.text.toString()
                        entity.fl = et_fl.text.toString()
                        entity.qt = et_qt.text.toString()
                        entity.hj = TdlyUtils.getSum(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl).toString()
                        entity.years = tv_pop_add_jtszcjtjjzyzcqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bcjtjjzcqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCjtjjzyzcqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {

                        val entity = BcjtjjzcqkEntity()
                        entity.cgbbt = et_cgbbt.text.toString()
                        entity.czzbgjf = et_cjzzbgjf.text.toString()
                        entity.ggyxwhf = et_ggfwyxwhf.text.toString()
                        entity.gysstr = et_gyxjcssjstr.text.toString()
                        entity.cjqtsdf = et_cjfdqtsdf.text.toString()
                        entity.fl = et_fl.text.toString()
                        entity.qt = et_qt.text.toString()
                        entity.hj = TdlyUtils.getSum(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl).toString()
                        entity.years = tv_pop_add_jtszcjtjjzyzcqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjtjjzcqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCjtjjzyzcqk(entity)
                        }
                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {

                        val entity = BcjtjjzcqkEntity()
                        entity.cgbbt = et_cgbbt.text.toString()
                        entity.czzbgjf = et_cjzzbgjf.text.toString()
                        entity.ggyxwhf = et_ggfwyxwhf.text.toString()
                        entity.gysstr = et_gyxjcssjstr.text.toString()
                        entity.cjqtsdf = et_cjfdqtsdf.text.toString()
                        entity.fl = et_fl.text.toString()
                        entity.qt = et_qt.text.toString()
                        entity.hj = TdlyUtils.getSum(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl).toString()
                        entity.years = tv_pop_add_jtszcjtjjzyzcqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }/*else if (TdlyUtils.setEditToast(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl,et_qt)){
                        }*/else{
                            entity.cunname = et_czrname.text.toString()
                        if (bcjtjjzcqkEntitysId==0L){
                            bcjtjjzcqkEntity = entity
                            mPresenter.getBcjcAddCjtjjzyzcqk(entity)
                        }else{
                            //修改接口
                            entity.id = bcjtjjzcqkEntitysId
                            bcjtjjzcqkEntity = entity
                            mPresenter.getBcjcUpdateCjtjjzyzcqk(entity)
                        }
                        }

                    }
                    //村 下一步
                    confirm.setOnClickListener {

                        val entity = BcjtjjzcqkEntity()
                        entity.cgbbt = et_cgbbt.text.toString()
                        entity.czzbgjf = et_cjzzbgjf.text.toString()
                        entity.ggyxwhf = et_ggfwyxwhf.text.toString()
                        entity.gysstr = et_gyxjcssjstr.text.toString()
                        entity.cjqtsdf = et_cjfdqtsdf.text.toString()
                        entity.fl = et_fl.text.toString()
                        entity.qt = et_qt.text.toString()
                        entity.hj = TdlyUtils.getSum(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl).toString()
                        entity.years = tv_pop_add_jtszcjtjjzyzcqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2

                        if (TdlyUtils.setEditToast(et_cgbbt,et_cjzzbgjf,et_ggfwyxwhf,et_gyxjcssjstr,et_cjfdqtsdf,et_fl,et_qt)){
                        }else {
                            //修改接口
                            if (et_czrname.text.toString().equals("")) {
                                ToastUtils.showShort("请输入操作员姓名！")
                            } else {
                                if (bcjtjjzcqkEntitysId == 0L) {
                                    entity.cunname = et_czrname.text.toString()
                                    mPresenter.getBcjcAddCjtjjzyzcqk(entity)
                                } else {
                                    entity.id = bcjtjjzcqkEntitysId
                                    entity.cunname = et_czrname.text.toString()
                                    mPresenter.getBcjcUpdateCjtjjzyzcqk(entity)
                                }
                            }
                        }
                    }
                }
            }
            close.setOnClickListener {
                addCjtjjzyzcqkUpPopu!!.dismiss()
            }
            }
        }

        //提交上报or通过
        bt_frag_jtsz_xyb.setOnClickListener {
            when(AppCache.getInstance().type){
                2->{
                    uploadOrRejectData(1,4)
                }
                3->{
                    uploadOrRejectData(1,3)
                }
                4->{
                    uploadOrRejectData(1,2)
                }
            }
        }

        //驳回
        bt_frag_jtsz_bh.setOnClickListener {
            when(AppCache.getInstance().type){
                2->{
                    uploadOrRejectData(2,4)
                }
                3->{
                    uploadOrRejectData(2,3)
                }
                4->{
                    uploadOrRejectData(2,2)
                }
            }
        }

    }

    //提交上报or通过or驳回逻辑方法
    /**
     * type 1下一步2驳回
     * process 2村3镇4区
     */
    fun uploadOrRejectData(type:Int,process:Int){

        mUploadPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject, view)//pop_point
        val contentView = mUploadPopu?.getContentView()
        val tv_bcjc_reject_title = contentView?.findViewById<TextView>(R.id.tv_bcjc_reject_title)//标题
        val et_pop_bcjc_reject_clr = contentView?.findViewById<EditText>(R.id.et_pop_bcjc_reject_clr)//处理人
        val pop_bcjc_reject_et_reject = contentView?.findViewById<EditText>(R.id.pop_bcjc_reject_et_reject)//驳回原因
        val ll_pop_bcjc_reject_bhyy = contentView?.findViewById<LinearLayout>(R.id.ll_pop_bcjc_reject_bhyy)//驳回原因
        val pop_bcjc_reject_confirm = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_confirm)//确定
        val pop_bcjc_reject_close = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_close)//取消


        pop_bcjc_reject_close?.setOnClickListener {
            mUploadPopu?.dismiss()
        }
        CommenPop.backgroundAlpha(0.5f, activity)
        mUploadPopu?.showAtLocation(view, Gravity.CENTER, 0, 0)
        if (type == 1){
            if (AppCache.getInstance().type == 4){
                tv_bcjc_reject_title?.setText("提交上报")
            }else{
                tv_bcjc_reject_title?.setText("通过")
            }
            ll_pop_bcjc_reject_bhyy?.visibility = View.GONE
        }else{
            tv_bcjc_reject_title?.setText("驳回")
            ll_pop_bcjc_reject_bhyy?.visibility = View.VISIBLE
        }


        pop_bcjc_reject_confirm!!.setOnClickListener {

            if (et_pop_bcjc_reject_clr!!.text.toString().equals("")){
                ToastUtils.showShort("请输入处理人")
            }else{
                if (type == 2){
                    if (pop_bcjc_reject_et_reject!!.text.toString().equals("")){
                        ToastUtils.showShort("请输入驳回原因")
                        return@setOnClickListener
                    }
                }
                if (TdlyUtils.setObjectToast(bcjjzsrqkEntity.cjjzsr,bcjjzsrqkEntity.gyjj,bcjjzsrqkEntity.cjtzzsr,bcjjzsrqkEntity.cjtqysr
                                ,bcjjzsrqkEntity.fgysr,bcjjzsrqkEntity.nhsr,bcjjzsrqkEntity.nmhzs,bcjjzsrqkEntity.syqysr,bcjjzsrqkEntity.gtjj
                                ,bcjjzsrqkEntity.ycsr,bcjjzsrqkEntity.ecsr,bcjjzsrqkEntity.scsr)){
                    ToastUtils.showShort("请完善村经济总收入情况")
                }else
                if (TdlyUtils.setObjectToast(bcjtjjsrqkEntity.zyywsr,bcjtjjsrqkEntity.qtywsr,bcjtjjsrqkEntity.tzsy,bcjtjjsrqkEntity.gybzjf
                                ,bcjtjjsrqkEntity.dzzfwjf,bcjtjjsrqkEntity.mlxcjswh,bcjtjjsrqkEntity.zcbz,bcjtjjsrqkEntity.yywsr,bcjtjjsrqkEntity.qt
                                ,bcjtjjsrqkEntity.hj)){
                    ToastUtils.showShort("请完善村集体经济组织主要收入情况表")
                }else
                if (TdlyUtils.setObjectToast(bcjtjjzcqkEntity.cgbbt,bcjtjjzcqkEntity.czzbgjf,bcjtjjzcqkEntity.ggyxwhf,bcjtjjzcqkEntity.gysstr
                                ,bcjtjjzcqkEntity.gysstr,bcjtjjzcqkEntity.cjqtsdf,bcjtjjzcqkEntity.fl,bcjtjjzcqkEntity.qt,bcjtjjzcqkEntity.hj)){
                    ToastUtils.showShort("请完善村集体经济组织主要支出情况表")
                }else{
                    when(AppCache.getInstance().type){
                        2->{
                            bcjjzsrqkEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                            bcjtjjsrqkEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                            bcjtjjzcqkEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                            bcjjzsrqkEntity.id = bcjjzsrqkEntitysId
                            bcjtjjsrqkEntity.id = bcjtjjsrqkEntitysId
                            bcjtjjzcqkEntity.id = bcjtjjzcqkEntitysId
                            if (type == 1){
                                bcjjzsrqkEntity.process = process
                                bcjtjjsrqkEntity.process = process
                                bcjtjjzcqkEntity.process = process
                                mPresenter.getBcjcUpdateCjjzsrqk(bcjjzsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzysrqk(bcjtjjsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzyzcqk(bcjtjjzcqkEntity)
                            }else{
                                bcjjzsrqkEntity.process = 1
                                bcjtjjsrqkEntity.process = 1
                                bcjtjjzcqkEntity.process = 1
                                val bcrejectedEntity = BcrejectedEntity()
                                bcrejectedEntity.content = pop_bcjc_reject_et_reject!!.text.toString()
                                bcjjzsrqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                bcjtjjsrqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                bcjtjjzcqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                mPresenter.getBcjcUpdateCjjzsrqk(bcjjzsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzysrqk(bcjtjjsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzyzcqk(bcjtjjzcqkEntity)
                            }
                        }
                        3->{
                            bcjjzsrqkEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                            bcjtjjsrqkEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                            bcjtjjzcqkEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                            bcjjzsrqkEntity.id = bcjjzsrqkEntitysId
                            bcjtjjsrqkEntity.id = bcjtjjsrqkEntitysId
                            bcjtjjzcqkEntity.id = bcjtjjzcqkEntitysId
                            if (type == 1){
                                bcjjzsrqkEntity.process = process
                                bcjtjjsrqkEntity.process = process
                                bcjtjjzcqkEntity.process = process
                                mPresenter.getBcjcUpdateCjjzsrqk(bcjjzsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzysrqk(bcjtjjsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzyzcqk(bcjtjjzcqkEntity)
                            }else{
                                bcjjzsrqkEntity.process = 1
                                bcjtjjsrqkEntity.process = 1
                                bcjtjjzcqkEntity.process = 1
                                val bcrejectedEntity = BcrejectedEntity()
                                bcrejectedEntity.content = pop_bcjc_reject_et_reject!!.text.toString()
                                bcjjzsrqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                bcjtjjsrqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                bcjtjjzcqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                mPresenter.getBcjcUpdateCjjzsrqk(bcjjzsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzysrqk(bcjtjjsrqkEntity)
                                mPresenter.getBcjcUpdateCjtjjzyzcqk(bcjtjjzcqkEntity)
                            }
                        }
                        4->{
                            bcjjzsrqkEntity.process = process
                            bcjtjjsrqkEntity.process = process
                            bcjtjjzcqkEntity.process = process
                            bcjjzsrqkEntity.id = bcjjzsrqkEntitysId
                            bcjtjjsrqkEntity.id = bcjtjjsrqkEntitysId
                            bcjtjjzcqkEntity.id = bcjtjjzcqkEntitysId
                            bcjjzsrqkEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                            bcjtjjsrqkEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                            bcjtjjzcqkEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                            if (bcjjzsrqkEntitysId==0L){
                                mPresenter.getBcjcAddCjjzsrqk(bcjjzsrqkEntity)
                            }else{
                                mPresenter.getBcjcUpdateCjjzsrqk(bcjjzsrqkEntity)
                            }
                            if (bcjtjjsrqkEntitysId==0L){
                                mPresenter.getBcjcAddCjtjjzysrqk(bcjtjjsrqkEntity)
                            }else{
                                mPresenter.getBcjcUpdateCjtjjzysrqk(bcjtjjsrqkEntity)
                            }
                            if (bcjtjjzcqkEntitysId==0L){
                                mPresenter.getBcjcAddCjtjjzyzcqk(bcjtjjzcqkEntity)
                            }else{
                                mPresenter.getBcjcUpdateCjtjjzyzcqk(bcjtjjzcqkEntity)
                            }
                        }
                    }
                }

            }


        }
    }

    override fun onResume() {
        super.onResume()
        if (AppCache.getInstance().isJsjbQuan){
            AppCache.getInstance().isJsjbQuan = false
//            mPresenter.getOpinionGetCount()
//            mPresenter.getOpinionGetStatistical()

        }
    }
    //驳回弹出框
    fun rejectUpPopup(list :ArrayList<BcrejectedEntity>){
        rejectUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject_list, slv_frag_zjd_ydgl)
        val contentView: View = rejectUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_reject_list_rlv)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_reject_list_close)
        CommenPop.backgroundAlpha(0.5f, activity)
        rejectUpPopu!!.showAtLocation(slv_frag_zjd_ydgl, Gravity.CENTER, 0, 0)
        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = object :BaseQuickAdapter<BcrejectedEntity, BaseViewHolder>(R.layout.item_bcjc_reject_rlv_item,list){
            override fun convert(helper: BaseViewHolder?, item: BcrejectedEntity?) {
                helper!!.setText(R.id.pop_bcjc_reject_reason,item!!.content)
                        .setText(R.id.pop_bcjc_reject_year,item.years)
            }


        }
        close.setOnClickListener {
            rejectUpPopu!!.dismiss()
        }
    }
    override fun returnBcjcCjtjjzyzcqk(msg: List<BcjtjjzcqkEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addCjtjjzyzcqkUpPopu!=null){
            addCjtjjzyzcqkUpPopu!!.dismiss()
        }
        if (msg!=null){//&&msg.size>0
            if (msg.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = msg.get(0)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_jtsz_cjtjjzyzcqk_add.visibility = View.VISIBLE
                    tv_frag_jtsz_cjtjjzyzcqk_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_jtsz_cjtjjzyzcqk_add.visibility = View.VISIBLE
                        tv_frag_jtsz_cjtjjzyzcqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_jtsz_cjtjjzyzcqk_add.visibility = View.VISIBLE
                            tv_frag_jtsz_cjtjjzyzcqk_add.setText("操作")
                        }else{
                            if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_jtsz_cjtjjzyzcqk_add.setText("已上报")
                            }else{
                                tv_frag_jtsz_cjtjjzyzcqk_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_jtsz_cjtjjzyzcqk_add.visibility = View.GONE
                            }
                        }

                when(get.process){
                    1->{
                        tv_frag_jtsz_gzjddsb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_jtsz_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    2->{
                        tv_frag_jtsz_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_jtsz_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    3->{
                        tv_frag_jtsz_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_jtsz_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    4->{
                        tv_frag_jtsz_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_jtsz_gzjdtjcg.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_jtsz_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                }

                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,get.process-1,get.process-1)
            }else if (AppCache.getInstance().type==4){
                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,-1,-1)
                tv_frag_jtsz_cjtjjzyzcqk_add.visibility = View.VISIBLE
            }else if (AppCache.getInstance().type!=4){
                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_jtsz_cjtjjzyzcqk_add.visibility = View.GONE
                }
                tv_frag_jtsz_cjtjjzyzcqk_add.setText("未上报")
            }

            rlv_frag_jtsz_cjtjjzyzcqk.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_jtsz_cjtjjzyzcqk.adapter = object :BaseQuickAdapter<BcjtjjzcqkEntity, BaseViewHolder>(R.layout.item_bcjc_cjtjjzyzcqk,msg){
                override fun convert(helper: BaseViewHolder?, item: BcjtjjzcqkEntity?) {
                    helper!!.setText(R.id.item_bcjc_cjtjjzyzcqk_year,item!!.years)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_cgbbt,item.cgbbt)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_cjzzbgjf,item.czzbgjf)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_ggfwyxwhf,item.ggyxwhf)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_gyxjcssjstr,item.gysstr)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_cjfddqtsdf,item.cjqtsdf)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_fl,item.fl)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_qt,item.qt)
                            .setText(R.id.item_bcjc_cjtjjzyzcqk_hj,item.hj)

                    val years = helper.getView<TextView>(R.id.item_bcjc_cjtjjzyzcqk_year)
                    if (item.bcrejectedEntities.size>0){
                        /*years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true*/
                        tv_frag_jtsz_gzjdbh.setTextColor(Color.parseColor("#ff6000"))
                        tv_frag_jtsz_gzjdbh.visibility = View.VISIBLE
                        iv_frag_jtsz_gzjdbh.visibility = View.VISIBLE
                    }else{
                        /*years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false*/
                        tv_frag_jtsz_gzjdbh.setTextColor(Color.parseColor("#ff6000"))
                        tv_frag_jtsz_gzjdbh.visibility = View.GONE
                        iv_frag_jtsz_gzjdbh.visibility = View.GONE
                    }
                    tv_frag_jtsz_gzjdbh.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }
                }

            }
        }else{
            ToastUtils.showShort("该年份暂无数据")
        }
    }

    override fun returnBcjcAddCjtjjzyzcqk(msg: String) {
        ToastUtils.showShort(msg)
        if (addCjtjjzyzcqkUpPopu!=null){
            addCjtjjzyzcqkUpPopu!!.dismiss()
        }
        mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjtjjzyzcqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcGetCjjjsrzc(AppCache.getInstance().code,yearsList)
    }
    override fun returnBcjcUpdateCjtjjzyzcqk(msg: String) {
        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){//addCjtjjzyzcqkUpPopu
            mUploadPopu!!.dismiss()
        }
        mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjtjjzyzcqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcGetCjjjsrzc(AppCache.getInstance().code,yearsList)
    }

    override fun returnBcjcCjtjjzysrqk(msg: List<BcjtjjsrqkEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addCjtjjzysrqkUpPopu!=null){
            addCjtjjzysrqkUpPopu!!.dismiss()
        }
        if (msg!=null){//&&msg.size>0
            if (msg.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = msg.get(0)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_jtsz_cjtjjzysrqk_add.visibility = View.VISIBLE
                    tv_frag_jtsz_cjtjjzysrqk_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_jtsz_cjtjjzysrqk_add.visibility = View.VISIBLE
                        tv_frag_jtsz_cjtjjzysrqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_jtsz_cjtjjzysrqk_add.visibility = View.VISIBLE
                            tv_frag_jtsz_cjtjjzysrqk_add.setText("操作")
                        }else{
                            if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_jtsz_cjtjjzysrqk_add.setText("已上报")
                            }else{
                                tv_frag_jtsz_cjtjjzysrqk_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_jtsz_cjtjjzysrqk_add.visibility = View.GONE
                            }
                        }

                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,get.process-1,get.process-1)
            }else if (AppCache.getInstance().type==4){
                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,-1,-1)
                tv_frag_jtsz_cjtjjzysrqk_add.visibility = View.VISIBLE
            }else if (AppCache.getInstance().type!=4){
                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_jtsz_cjtjjzysrqk_add.visibility = View.GONE
                }
                tv_frag_jtsz_cjtjjzysrqk_add.setText("未上报")
            }

            rlv_frag_jtsz_cjtjjzysrqk.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_jtsz_cjtjjzysrqk.adapter = object :BaseQuickAdapter<BcjtjjsrqkEntity, BaseViewHolder>(R.layout.item_bcjc_cjtjjzysrqk,msg){
                override fun convert(helper: BaseViewHolder?, item: BcjtjjsrqkEntity?) {
                    helper!!.setText(R.id.item_bcjc_cjtjjzysrqk_year,item!!.years)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_zyywsr,item.zyywsr)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_dslcf,item.qtywsr)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_tzsy,item.tzsy)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_cjgysyzx,item.gybzjf)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_cdzzfw,item.dzzfwjf)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_mlxc,item.mlxcjswh)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_qtzcxbz,item.zcbz)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_yywsr,item.yywsr)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_qt,item.qt)
                            .setText(R.id.item_bcjc_cjtjjzysrqk_hj,item.hj)

                    /*val years = helper.getView<TextView>(R.id.item_bcjc_cjtjjzysrqk_year)
                    if (item.bcrejectedEntities.size>0){
                        years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true
                    }else{
                        years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false
                    }
                    years.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }*/
                }

            }
        }else{
            ToastUtils.showShort("该年份暂无数据")
        }
    }
    override fun returnBcjcAddCjtjjzysrqk(msg: String) {
        ToastUtils.showShort(msg)
        if (addCjtjjzysrqkUpPopu!=null){
            addCjtjjzysrqkUpPopu!!.dismiss()
        }
        mPresenter.getBcjcCjtjjzysrqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjtjjzysrqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcGetCjjjsrzc(AppCache.getInstance().code,yearsList)
    }
    override fun returnBcjcUpdateCjtjjzysrqk(msg: String) {

        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){//addCjtjjzysrqkUpPopu
            mUploadPopu!!.dismiss()
        }
        mPresenter.getBcjcCjtjjzysrqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjtjjzysrqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcGetCjjjsrzc(AppCache.getInstance().code,yearsList)
    }

    override fun returnBcjcCjjzsrqk(msg: List<BcjjzsrqkEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addCjjzsrqkUpPopu!=null){
            addCjjzsrqkUpPopu!!.dismiss()
        }
        if (msg!=null){//&&msg.size>0
            if (msg.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = msg.get(0)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_jtsz_cjjzsrqk_add.visibility = View.VISIBLE
                    tv_frag_jtsz_cjjzsrqk_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_jtsz_cjjzsrqk_add.visibility = View.VISIBLE
                        tv_frag_jtsz_cjjzsrqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_jtsz_cjjzsrqk_add.visibility = View.VISIBLE
                            tv_frag_jtsz_cjjzsrqk_add.setText("操作")
                        }else{
                            if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_jtsz_cjjzsrqk_add.setText("已上报")
                            }else{
                                tv_frag_jtsz_cjjzsrqk_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_jtsz_cjjzsrqk_add.visibility = View.GONE
                            }
                        }
                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,get.process-1,get.process-1)

            }else if (AppCache.getInstance().type==4){
                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,-1,-1)
                tv_frag_jtsz_cjjzsrqk_add.visibility = View.VISIBLE
            }else if (AppCache.getInstance().type!=4){
                tll_frag_zjd_yqgl_cjjzsr.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_jtsz_cjjzsrqk_add.visibility = View.GONE
                }
                tv_frag_jtsz_cjjzsrqk_add.setText("未上报")
            }

            rlv_frag_jtsz_cjjzsrqk_cjjzsrqk.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_jtsz_cjjzsrqk_cjjzsrqk.adapter = object :BaseQuickAdapter<BcjjzsrqkEntity, BaseViewHolder>(R.layout.item_bcjc_cjjzsrqk,msg){
                override fun convert(helper: BaseViewHolder?, item: BcjjzsrqkEntity?) {
                    helper!!.setText(R.id.item_bcjc_cjjzsrqk_year,item!!.years)
                            .setText(R.id.item_bcjc_cjjzsrqk_zsr,item.cjjzsr)
                            .setText(R.id.item_bcjc_cjjzsrqk_gyjj,item.gyjj)
                            .setText(R.id.item_bcjc_cjjzsrqk_cjtzzsr,item.cjtzzsr)
                            .setText(R.id.item_bcjc_cjjzsrqk_cjtqysr,item.cjtqysr)
                            .setText(R.id.item_bcjc_cjjzsrqk_fgyjj,item.fgysr)
                            .setText(R.id.item_bcjc_cjjzsrqk_nhsr,item.nhsr)
                            .setText(R.id.item_bcjc_cjjzsrqk_nmhzs,item.nmhzs)
                            .setText(R.id.item_bcjc_cjjzsrqk_syqysr,item.syqysr)
                            .setText(R.id.item_bcjc_cjjzsrqk_gtjy,item.gtjj)
                            .setText(R.id.item_bcjc_cjjzsrqk_ycsr,item.ycsr)
                            .setText(R.id.item_bcjc_cjjzsrqk_ecsr,item.ecsr)
                            .setText(R.id.item_bcjc_cjjzsrqk_scsr,item.scsr)

                    val years = helper.getView<TextView>(R.id.item_bcjc_cjjzsrqk_year)
                    /*if (item.bcrejectedEntities.size>0){
                        years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true
                    }else{
                        years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false
                    }
                    years.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }*/
                }

            }
        }else{
            ToastUtils.showShort("该年份暂无数据")
        }
    }

    override fun returnBcjcAddCjjzsrqk(msg: String) {
        ToastUtils.showShort(msg)
        if (addCjjzsrqkUpPopu!=null){
            addCjjzsrqkUpPopu!!.dismiss()
        }
        mPresenter.getBcjcCjjzsrqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjjzsrqkAll(AppCache.getInstance().code,yearsListNull)
    }
    override fun returnBcjcUpdateCjjzsrqk(msg: String) {

        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){//addCjjzsrqkUpPopu
            mUploadPopu!!.dismiss()
        }
        mPresenter.getBcjcCjjzsrqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCjjzsrqkAll(AppCache.getInstance().code,yearsListNull)
    }



    var bcjjzsrqkEntitys = ArrayList<BcjjzsrqkEntity>()
    override fun returnBcjcCjjzsrqkAll(msg: List<BcjjzsrqkEntity>) {
        bcjjzsrqkEntitys.clear()
        bcjjzsrqkEntitys.addAll(msg)

        if (msg.size == 0){
            bt_frag_jtsz_xyb.visibility = View.GONE
            bt_frag_jtsz_bh.visibility = View.GONE
            return
        }
        for (i in msg){
            if (i.years.contains(split)){

                bcjjzsrqkEntity.cjjzsr = i.cjjzsr
                bcjjzsrqkEntity.gyjj = i.gyjj
                bcjjzsrqkEntity.cjtzzsr = i.cjtzzsr
                bcjjzsrqkEntity.cjtqysr = i.cjtqysr
                bcjjzsrqkEntity.fgysr = i.fgysr
                bcjjzsrqkEntity.nhsr = i.nhsr
                bcjjzsrqkEntity.nmhzs = i.nmhzs
                bcjjzsrqkEntity.syqysr = i.syqysr
                bcjjzsrqkEntity.gtjj = i.gtjj
                bcjjzsrqkEntity.ycsr = i.ycsr
                bcjjzsrqkEntity.ecsr = i.ecsr
                bcjjzsrqkEntity.scsr = i.scsr
                bcjjzsrqkEntity.years = i.years
                bcjjzsrqkEntity.code = AppCache.getInstance().code
                bcjjzsrqkEntity.xzqmc = i.xzqmc

                bcjjzsrqkEntitysId = i.id
                bcjjzsrqkEntitysProcess = i.process
            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_frag_jtsz_xyb.text = "提交上报"
            if (bcjjzsrqkEntitysProcess == 1&&bcjtjjsrqkEntitysProcess == 1&&bcjtjjzcqkEntitysProcess == 1){
                bt_frag_jtsz_xyb.visibility = View.VISIBLE
            }else{
                bt_frag_jtsz_xyb.visibility = View.GONE
            }
        }else{
            bt_frag_jtsz_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bcjjzsrqkEntitysProcess == 2&&bcjtjjsrqkEntitysProcess == 2&&bcjtjjzcqkEntitysProcess == 2){
                    bt_frag_jtsz_xyb.visibility = View.VISIBLE
                    bt_frag_jtsz_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_jtsz_xyb.visibility = View.GONE
                    bt_frag_jtsz_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bcjjzsrqkEntitysProcess == 3&&bcjtjjsrqkEntitysProcess == 3&&bcjtjjzcqkEntitysProcess == 3){
                    bt_frag_jtsz_xyb.visibility = View.VISIBLE
                    bt_frag_jtsz_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_jtsz_xyb.visibility = View.GONE
                    bt_frag_jtsz_bh.visibility = View.GONE
                }
            }else{
                bt_frag_jtsz_xyb.visibility = View.GONE
                bt_frag_jtsz_bh.visibility = View.GONE
            }
        }
    }

    var bcjtjjsrqkEntitys = ArrayList<BcjtjjsrqkEntity>()
    override fun returnBcjcCjtjjzysrqkAll(msg: List<BcjtjjsrqkEntity>) {
        bcjtjjsrqkEntitys.clear()
        bcjtjjsrqkEntitys.addAll(msg)
        if (msg.size == 0){
            bt_frag_jtsz_xyb.visibility = View.GONE
            bt_frag_jtsz_bh.visibility = View.GONE
            return
        }
        for (i in msg){
            if (i.years.contains(split)){

                bcjtjjsrqkEntity.zyywsr = i.zyywsr
                bcjtjjsrqkEntity.qtywsr = i.qtywsr
                bcjtjjsrqkEntity.tzsy = i.tzsy
                bcjtjjsrqkEntity.gybzjf = i.gybzjf
                bcjtjjsrqkEntity.dzzfwjf = i.dzzfwjf
                bcjtjjsrqkEntity.mlxcjswh = i.mlxcjswh
                bcjtjjsrqkEntity.zcbz = i.zcbz
                bcjtjjsrqkEntity.yywsr = i.yywsr
                bcjtjjsrqkEntity.qt = i.qt
                bcjtjjsrqkEntity.hj = i.hj/*(msg.get(0).zyywsr.toFloat()+msg.get(0).qtywsr.toFloat()+msg.get(0).tzsy.toFloat()+msg.get(0).gybzjf.toFloat()+msg.get(0).dzzfwjf.toFloat()+
                msg.get(0).mlxcjswh.toFloat()+msg.get(0).zcbz.toFloat()+msg.get(0).yywsr.toFloat()).toString()*/
                bcjtjjsrqkEntity.years =i.years
                bcjtjjsrqkEntity.code = AppCache.getInstance().code
                bcjtjjsrqkEntity.xzqmc = i.xzqmc

                bcjtjjsrqkEntitysId = i.id
                bcjtjjsrqkEntitysProcess = i.process
            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_frag_jtsz_xyb.text = "提交上报"
            if (bcjtjjsrqkEntitysProcess == 1&&bcjjzsrqkEntitysProcess == 1&&bcjtjjzcqkEntitysProcess == 1){
                bt_frag_jtsz_xyb.visibility = View.VISIBLE
            }else{
                bt_frag_jtsz_xyb.visibility = View.GONE
            }
        }else{
            bt_frag_jtsz_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bcjtjjsrqkEntitysProcess == 2&&bcjjzsrqkEntitysProcess == 2&&bcjtjjzcqkEntitysProcess == 2){
                    bt_frag_jtsz_xyb.visibility = View.VISIBLE
                    bt_frag_jtsz_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_jtsz_xyb.visibility = View.GONE
                    bt_frag_jtsz_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bcjtjjsrqkEntitysProcess == 3&&bcjjzsrqkEntitysProcess == 3&&bcjtjjzcqkEntitysProcess == 3){
                    bt_frag_jtsz_xyb.visibility = View.VISIBLE
                    bt_frag_jtsz_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_jtsz_xyb.visibility = View.GONE
                    bt_frag_jtsz_bh.visibility = View.GONE
                }
            }else{
                bt_frag_jtsz_xyb.visibility = View.GONE
                bt_frag_jtsz_bh.visibility = View.GONE
            }
        }
    }

    var bcjtjjzcqkEntitys = ArrayList<BcjtjjzcqkEntity>()
    override fun returnBcjcCjtjjzyzcqkAll(msg: List<BcjtjjzcqkEntity>) {
        bcjtjjzcqkEntitys.clear()
        bcjtjjzcqkEntitys.addAll(msg)

        if (msg.size == 0){
            bt_frag_jtsz_xyb.visibility = View.GONE
            bt_frag_jtsz_bh.visibility = View.GONE
            return
        }
        for (i in msg){
            if (i.years.contains(split)){

                bcjtjjzcqkEntity.cgbbt = i.cgbbt
                bcjtjjzcqkEntity.czzbgjf = i.czzbgjf
                bcjtjjzcqkEntity.ggyxwhf = i.ggyxwhf
                bcjtjjzcqkEntity.gysstr = i.gysstr
                bcjtjjzcqkEntity.cjqtsdf = i.cjqtsdf
                bcjtjjzcqkEntity.fl = i.fl
                bcjtjjzcqkEntity.qt = i.qt
                bcjtjjzcqkEntity.hj = i.hj/*(msg.get(0).cgbbt.toFloat()+msg.get(0).czzbgjf.toFloat()+msg.get(0).ggyxwhf.toFloat()+
                msg.get(0).gysstr.toFloat()+msg.get(0).cjqtsdf.toFloat()).toString()*/
                bcjtjjzcqkEntity.years = i.years
                bcjtjjzcqkEntity.code = AppCache.getInstance().code
                bcjtjjzcqkEntity.xzqmc = i.xzqmc
                bcjtjjzcqkEntitysId = i.id
                bcjtjjzcqkEntitysProcess = i.process
            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_frag_jtsz_xyb.text = "提交上报"
            if (bcjtjjzcqkEntitysProcess == 1&&bcjjzsrqkEntitysProcess == 1&&bcjtjjsrqkEntitysProcess == 1){
                bt_frag_jtsz_xyb.visibility = View.VISIBLE
            }else{
                bt_frag_jtsz_xyb.visibility = View.GONE
            }
        }else{
            bt_frag_jtsz_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bcjtjjzcqkEntitysProcess == 2&&bcjjzsrqkEntitysProcess == 2&&bcjtjjsrqkEntitysProcess == 2){
                    bt_frag_jtsz_xyb.visibility = View.VISIBLE
                    bt_frag_jtsz_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_jtsz_xyb.visibility = View.GONE
                    bt_frag_jtsz_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bcjtjjzcqkEntitysProcess == 3&&bcjjzsrqkEntitysProcess == 3&&bcjtjjsrqkEntitysProcess == 3){
                    bt_frag_jtsz_xyb.visibility = View.VISIBLE
                    bt_frag_jtsz_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_jtsz_xyb.visibility = View.GONE
                    bt_frag_jtsz_bh.visibility = View.GONE
                }
            }else{
                bt_frag_jtsz_xyb.visibility = View.GONE
                bt_frag_jtsz_bh.visibility = View.GONE
            }
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



}
