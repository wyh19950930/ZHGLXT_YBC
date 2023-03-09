package com.jymj.zhglxt.util

import android.app.Activity
import android.graphics.Color
import android.graphics.Matrix
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.mpandroidchart.LineChartEntity
import com.jymj.zhglxt.ldrkgl.home.adapter.HylxTlAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.LegendBean
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity
import com.setsuna.common.commonutils.StringUtils
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

object PieTdxgUtil {

    private var mFormat: DecimalFormat = DecimalFormat("#,###.##")

    public fun initPie(pie: PieChart, title: String, datas: ArrayList<Float>, lables: ArrayList<String>) {
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
        pie.isRotationEnabled = false//是否可以旋转
        pie.isHighlightPerTapEnabled = true
        pie.setEntryLabelColor(Color.BLACK)
        setData(pie, datas, lables)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    private fun setData(pie: PieChart, datas: List<Float>, lables: List<String>) {
        val entries = ArrayList<PieEntry>()

        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

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
    public fun pieRlv(pie: PieChart, title: String, datas: ArrayList<Float>,
                      lables: ArrayList<String>, colors: List<Int>,recyclerView: RecyclerView,activity: Activity){
        rkinitPie1(pie,title,datas,lables,colors)
        var legendList = ArrayList<LegendBean>()
        for (i in lables.indices){
            val get = lables.get(i)
            val get1 = datas.get(i)
            val get2 = colors.get(i)
            legendList.add(LegendBean(get,get1,get2))
        }
        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        val hylxTlAdapter = HylxTlAdapter(activity, legendList)
        recyclerView.adapter = hylxTlAdapter//LegendBean
    }

    //问题统计饼状图
    public fun rkinitPie1(pie: PieChart, title: String, datas: java.util.ArrayList<Float>, lables: java.util.ArrayList<String>, colors: List<Int>) {
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
        setRKData1(pie, datas, lables, colors)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(true)
        l.isEnabled = false
    }

    public fun setRKData1(pie: PieChart, datas: List<Float>, lables: List<String>, colors: List<Int>) {
        val entries = java.util.ArrayList<PieEntry>()

        var zs = 0f
        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
            zs = zs + datas[i]
        }
        val dataSet = PieDataSet(entries, "")//Election Results
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



    //柱状图  https://blog.csdn.net/scuff/article/details/124914200
    public fun setZzt(xValues:List<String>,colors:List<Int>,
                      chartDataMap:LinkedHashMap<String, ArrayList<Float>>,barChart: BarChart){
        initBarChart(barChart)
        //处理数据是 记得判断每条柱状图对应的数据集合 长度是否一致
        showBarChart(xValues, chartDataMap, colors,barChart)
        //请求数据变化时刷新
        barChart.invalidate()
    }
    var xAxis: XAxis? = null
    var legend: Legend? = null
    var leftAxis: YAxis? = null
    var rightAxis: YAxis? = null
    /**
     * 初始化BarChart图表 多条
     */
    private fun initBarChart( barChart: BarChart) {
        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);

        barChart.setDoubleTapToZoomEnabled(false);
        //支持左右滑动
        barChart.setDragEnabled(true);
        //X轴或Y轴禁止缩放
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleEnabled(false);
        //不显示边框
        barChart.setDrawBorders(false);

        //不显示右下角描述内容
        var description = Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis?.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis?.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //不绘制 Y轴线条
        rightAxis?.setDrawAxisLine(false);
        //不显示X轴网格线
        xAxis?.setDrawGridLines(false);
        leftAxis?.setDrawGridLines(false);
        rightAxis?.setDrawGridLines(false);


        /***折线图例 标签 设置***/
        legend = barChart.getLegend();
        legend?.setForm(Legend.LegendForm.SQUARE);
        legend?.setTextSize(11f);
        //显示位置
        legend?.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend?.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend?.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend?.setDrawInside(false);
//        legend?.formToTextSpace = 15f
    }
    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     *
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private fun initBarDataSet(barDataSet: BarDataSet, color:Int) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15f);

    }
    /**
     * @param xValues   X轴的值
     * @param dataLists LinkedHashMap<String, List<Float>>
     *                  key对应柱状图名字  List<Float> 对应每类柱状图的Y值
     * @param colors
     */
    public fun showBarChart(xValues:List<String> , dataLists:LinkedHashMap<String, ArrayList<Float>> ,
                            colors:List<Int>,barChart:BarChart ) {

        var dataSets = ArrayList<IBarDataSet>();
        var currentPosition = 0;//用于柱状图颜色集合的index

        for ( entry in dataLists.entries) {//LinkedHashMap. Entry<String, List<Float>>
            var name = entry.key;
            var yValueList = entry.value;//List<Float>

            var entries = ArrayList<BarEntry>();//List<BarEntry>

            for (i in yValueList.indices) {
            /**
             *  如果需要添加TAG标志 可使用以下构造方法
             *  BarEntry(float x, float y, Object data)
             *  e.getData()
             */
            entries.add(BarEntry(i.toFloat(), yValueList.get(i)));
        }
            // 每一个BarDataSet代表一类柱状图
            var barDataSet = BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);

            currentPosition++;
        }

//X轴自定义值
        xAxis?.setValueFormatter(
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {//(Math.abs(value) % xValues.size) as Int
                        if (value.toInt()<xValues.size&&value.toInt()>=0){
                            return xValues.get(value.toInt());
                        }else{
                            return ""
                        }
                    }
                }
                /*new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int) Math.abs(value) % xValues.size());
            }
        }*/)
        xAxis?.setLabelRotationAngle(-30f);//x轴文字斜体显示
        rightAxis?.setValueFormatter(
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return "";
                    }
                }
                /*new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }
        }*/);

        var data = BarData(dataSets);

        /**
         * float groupSpace = 0.3f;   //柱状图组之间的间距
         * float barSpace =  0.05f;  //每条柱状图之间的间距  一组两个柱状图
         * float barWidth = 0.3f;    //每条柱状图的宽度     一组两个柱状图
         * (barWidth + barSpace) * 2 + groupSpace = (0.3 + 0.05) * 2 + 0.3 = 1.00
         * 3个数值 加起来 必须等于 1 即100% 按照百分比来计算 组间距 柱状图间距 柱状图宽度
         */
        var barAmount = dataLists.size; //需要显示柱状图的类别 数量
        //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        var groupSpace = 0.3f; //柱状图组之间的间距
        var barWidth = (1f - groupSpace) / barAmount;
        var barSpace = 0f;
        //设置柱状图宽度
        data.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace);
        barChart.setData(data);
        //设置柱状图一页显示的范围(0-5)
        barChart.setVisibleXRange(0f,5f);
        //解决x轴标签斜体显示不全的问题
        barChart.notifyDataSetChanged();

        xAxis?.setAxisMinimum(0f);
        xAxis?.setAxisMaximum(xValues.size.toFloat());
        //将X轴的值显示在中央
        xAxis?.setCenterAxisLabels(true);
    }

    fun getStringToFloat(string:String):Float{
        if (string.equals("")){
            return 0f
        }else{
            return string.toFloat()
        }
    }


    //柱状图  一条
    public fun zjdSpBar(activity:Activity,message: ArrayList<Float>,stringList: ArrayList<String>, barChart: BarChart) {
//        message.clear()
        if (barChart != null&&message.size>0){
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
                val yoyListEntity = message.get(i)
                val arrayList1 = ArrayList<Float>()
                arrayList1.add(yoyListEntity)
                /*val zthj = yoyListEntity.object3
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
                }*/
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
            setDataSP(activity,arrayList,stringList,barChart)//homeEntities
        }
    }
    //初始化
    private fun setDataSP(activity:Activity,stringList:ArrayList<ArrayList<Float>>,homeEntityList:ArrayList<String>, barChart: BarChart) {
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
        val stacksize = 1
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#5B9BD5")
        /*colors[0] = Color.parseColor("#FA5D56")
        colors[1] = Color.parseColor("#04BDC2")
        colors[2] = Color.parseColor("#25C898")
        colors[3] = Color.parseColor("#2A8DFE")
        colors[4] = Color.parseColor("#25C898")
        colors[5] = Color.parseColor("#FFB638")*/
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
    }

    //LineChartInViewPager  线性统计图
    public fun updateLinehart1(activity:Activity, lineChart: LineChart, colors: IntArray,
                               unit: String, entries: Array<ArrayList<Entry>?>, labels: Array<String>, strings: ArrayList<String>) {
//        , values2: ArrayList<Entry>
//        val arrayList = Array<ArrayList<Entry>>(5)
        /*val entries = arrayOfNulls<ArrayList<Entry>>(2)
        entries[0] = values1
        entries[1] = values2*/
        val lineChartEntity = LineChartEntity(lineChart, entries, labels, colors, Color.BLACK, 12f)
        lineChartEntity.drawCircle(false)
        lineChart.setScaleMinima(1f, 1f)
        lineChart.getXAxis().setLabelRotationAngle(0f)
        //X轴
        lineChart.getAxisLeft().gridColor = Color.parseColor("#d7c3ff")
//        lineChart.setExtraOffsets(5f, 10f, 5f, 5f)
        //        toggleFilled(lineChartEntity, drawables, colors)
        //隐藏x轴描述
        var description = Description()
        description.setEnabled(false)
        lineChart.setDescription(description)
        val legend = lineChart.legend
        legend.isWordWrapEnabled = true
        legend.isEnabled = true
        legend.setForm(Legend.LegendForm.NONE)
//        lineChart.line
        lineChart.setGridBackgroundColor(Color.WHITE)//Color.WHITE  Color.parseColor("#d7c3ff")
        var maxy = 10f
        for (i in entries) {
            for (s in i!!) {
                if (maxy <= s.y) {
                    maxy = s.y + 3
                }
            }

        }
        val axisLeft = lineChart.axisLeft
        axisLeft.axisMinimum = 0f
        axisLeft.axisMaximum = maxy
        /**
         * 这里切换平滑曲线或者折现图
         */
//         lineChartEntity.setLineMode(LineDataSet.Mode.LINEAR);

        var xAxis = lineChart.getXAxis()
        xAxis.setLabelRotationAngle(30f) //标签倾斜
        xAxis.setDrawLabels(true)

        lineChart.axisLeft.setDrawLabels(true)

        lineChartEntity.setLineMode(LineDataSet.Mode.LINEAR)
        lineChartEntity.initLegend(Legend.LegendForm.CIRCLE, 12f, Color.BLACK)
        lineChartEntity.updateLegendOrientation(Legend.LegendVerticalAlignment.TOP,
                Legend.LegendHorizontalAlignment.RIGHT, Legend.LegendOrientation.HORIZONTAL)

        lineChartEntity.setAxisFormatter(
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val monthStr = mFormat.format((value - 1).toDouble())
                        val toInt = value.toInt()
                        return if (monthStr.contains(".") && toInt < 1 && toInt > -1) {
                            strings.get(toInt)
                        } else {
                            if (strings.size > toInt && toInt > -1) {//=   && toInt > 0
                                strings.get(toInt)
                                //homeList[toInt - 1].xzqmc//toInt - 1
                            } else {
//                                strings.get(toInt)
                                ""
                            }

                        }
                    }
                },
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return mFormat.format(value.toDouble()) + unit
                    }
                })

        lineChartEntity.setDataValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return mFormat.format(value.toDouble()) + unit

            }
        })
        lineChart.data.setDrawValues(true)
        val markerView = NewMarkerView(activity, R.layout.custom_marker_view_layout)
        markerView.setCallBack(NewMarkerView.CallBack { x, value ->
            val index = x.toInt()
            if (index < 0) {
                return@CallBack
            }
            if (index > strings.size) {
                return@CallBack
            }
            var textTemp = ""

            if (index <= strings.size) {
                if (!StringUtils.isEmpty(textTemp)) {
                }
                /*var get: Entry? = null
                var get2: Entry? = null
                if (values1.size>index){
                    get = values1.get(index)
                }
                if (values2.size>index){
                    get2 = values2.get(index)
                }*/
                val get1 = strings.get(index)
                textTemp = get1//get.name//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                /*if (get!=null){
                    textTemp += "\n"
                    textTemp += labels.get(0)+":" + get!!.y.toInt()+""
                }
                if (get2!=null){
                    textTemp += "\n"
                    textTemp += labels.get(1)+":" + get2!!.y.toInt()+""
                }*/
                for (i in entries.indices){
                    val get3 = entries.get(i)
                    for (f in get3!!){
                        val pieEntry = f
                        if (pieEntry.data.toString().equals(get1)){
                            textTemp += "\n"
                            textTemp += labels.get(i)+":" + pieEntry!!.y+""
                        }
                    }
                   /* if (get3!!.size>index){
                        //get1
                        var value = get3.get(index)
                        if (value!=null){
                            textTemp += "\n"
                            textTemp += labels.get(0)+":" + value!!.y.toInt()+""
                        }

                    }*/

                }
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(lineChart)
        lineChart.setMarker(markerView)

    }

    public fun setZztDs(activity: Activity,barChart: BarChart,homeEntitys: List<BcwcjybdqkEntity>){
        if (barChart != null){
//            barChart.setOnChartValueSelectedListener(this);
            barChart.getDescription().setEnabled(false);
            barChart.setMaxVisibleValueCount(40);
            // 扩展现在只能分别在x轴和y轴
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(false);
            barChart.setHighlightFullBarEnabled(false);
            barChart.setScaleEnabled(false);
            barChart.setDoubleTapToZoomEnabled(false);

            // 改变y标签的位置
            var leftAxis = barChart.getAxisLeft()
            leftAxis.setValueFormatter(object:ValueFormatter(){})
            leftAxis.setAxisMinimum(0f);
            barChart.getAxisRight().setEnabled(false)
            var xLabels = barChart.getXAxis()
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
                        homeEntitys.get(index).years
                    }
//                    return super.getFormattedValue(value)
                }
            }
            /*val mMatrix = Matrix()
            mMatrix.postScale(0.5f, 1f)
            barChart.viewPortHandler.refresh(mMatrix, barChart, false)*/


            var l = barChart.getLegend()
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
            l.setDrawInside(false)
            l.setFormSize(8f)
            l.setFormToTextSpace(4f)
            l.setXEntrySpace(6f)
            val arrayList = ArrayList<ArrayList<Float>>()
            for (i in 0..homeEntitys.size-1){//for (i in homeEntities.size-1 downTo 0){
                val yoyListEntity = homeEntitys.get(i)
                val arrayList1 = ArrayList<Float>()
                val zthj = getStringToFloat(yoyListEntity!!.bdjy)
                val gcwt = getStringToFloat(yoyListEntity!!.xwqn)
                val wswt = getStringToFloat(yoyListEntity!!.qwsn)
                val ljwt = getStringToFloat(yoyListEntity!!.wss)
//                val crwt = yoyListEntity!!.crwt
                arrayList1.add(zthj.toFloat())
                arrayList1.add(gcwt.toFloat())
                arrayList1.add(wswt.toFloat())
                arrayList1.add(ljwt.toFloat())
//                arrayList1.add(crwt.toFloat())
                arrayList.add(arrayList1)
            }
            setData2(activity,barChart,arrayList,homeEntitys);//homeEntities
        }
    }

    //初始化
    private fun setData2(activity: Activity,barChart: BarChart,stringList:ArrayList<ArrayList<Float>>,homeEntityList:List<BcwcjybdqkEntity>) {
        var yVals1 = ArrayList<BarEntry>()
        for (i in 0..stringList.size-1){
            val get = stringList.get(i)
            yVals1.add( BarEntry(i.toFloat(), floatArrayOf(get.get(0), get.get(1), get.get(2),get.get(3))))
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

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 =  barChart.getData().getDataSetByIndex(0) as BarDataSet
            set1.setValues(yVals1)
            barChart.getData().notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")//三年级一班期末考试
            set1.setColors(getColors()!!.toList())
            set1.setStackLabels( arrayOf("村外乡内", "乡外区内", "区外市内", "外省市"))

            var dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            var data = BarData(dataSets);//MyValueFormatter
            data.setValueFormatter(object:ValueFormatter(){})
            data.setValueTextColor(Color.WHITE)

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
                textTemp = get.xzqmc//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                textTemp += "\n"
                textTemp += "村外乡内:" + get1.get(0).toInt()
                textTemp += "\n"
                textTemp += "乡外区内:" + get1.get(1).toInt()
                textTemp += "\n"
                textTemp += "区外市内:" + get1.get(2).toInt()
                textTemp += "\n"
                textTemp += "外省市:" + get1.get(3).toInt()
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(barChart)
        barChart.setMarker(markerView)
        barChart.data.setDrawValues(false)

        barChart.setFitBars(true);
        barChart.invalidate();
    }

    private fun getColors(): IntArray? {
        val stacksize = 4
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#5B9BD5")
        colors[1] = Color.parseColor("#ED7D31")
        colors[2] = Color.parseColor("#A5A5A5")
        colors[3] = Color.parseColor("#FFC000")
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
    }

}