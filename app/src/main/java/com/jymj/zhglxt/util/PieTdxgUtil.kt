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
        pie.isRotationEnabled = false//??????????????????
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

    //?????????????????????
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



    //?????????  https://blog.csdn.net/scuff/article/details/124914200
    public fun setZzt(xValues:List<String>,colors:List<Int>,
                      chartDataMap:LinkedHashMap<String, ArrayList<Float>>,barChart: BarChart){
        initBarChart(barChart)
        //??????????????? ???????????????????????????????????????????????? ??????????????????
        showBarChart(xValues, chartDataMap, colors,barChart)
        //???????????????????????????
        barChart.invalidate()
    }
    var xAxis: XAxis? = null
    var legend: Legend? = null
    var leftAxis: YAxis? = null
    var rightAxis: YAxis? = null
    /**
     * ?????????BarChart?????? ??????
     */
    private fun initBarChart( barChart: BarChart) {
        /***????????????***/
        //????????????
        barChart.setBackgroundColor(Color.WHITE);
        //?????????????????????
        barChart.setDrawGridBackground(false);
        //????????????
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);

        barChart.setDoubleTapToZoomEnabled(false);
        //??????????????????
        barChart.setDragEnabled(true);
        //X??????Y???????????????
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleEnabled(false);
        //???????????????
        barChart.setDrawBorders(false);

        //??????????????????????????????
        var description = Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        /***XY????????????***/
        //X??????????????????????????????
        xAxis = barChart.getXAxis();
        xAxis?.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis?.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //????????? Y?????????
        rightAxis?.setDrawAxisLine(false);
        //?????????X????????????
        xAxis?.setDrawGridLines(false);
        leftAxis?.setDrawGridLines(false);
        rightAxis?.setDrawGridLines(false);


        /***???????????? ?????? ??????***/
        legend = barChart.getLegend();
        legend?.setForm(Legend.LegendForm.SQUARE);
        legend?.setTextSize(11f);
        //????????????
        legend?.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend?.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend?.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //???????????????????????????
        legend?.setDrawInside(false);
//        legend?.formToTextSpace = 15f
    }
    /**
     * ????????????????????? ??????BarDataSet ?????????????????????
     *
     * @param barDataSet ?????????
     * @param color      ???????????????
     */
    private fun initBarDataSet(barDataSet: BarDataSet, color:Int) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15f);

    }
    /**
     * @param xValues   X?????????
     * @param dataLists LinkedHashMap<String, List<Float>>
     *                  key?????????????????????  List<Float> ????????????????????????Y???
     * @param colors
     */
    public fun showBarChart(xValues:List<String> , dataLists:LinkedHashMap<String, ArrayList<Float>> ,
                            colors:List<Int>,barChart:BarChart ) {

        var dataSets = ArrayList<IBarDataSet>();
        var currentPosition = 0;//??????????????????????????????index

        for ( entry in dataLists.entries) {//LinkedHashMap. Entry<String, List<Float>>
            var name = entry.key;
            var yValueList = entry.value;//List<Float>

            var entries = ArrayList<BarEntry>();//List<BarEntry>

            for (i in yValueList.indices) {
            /**
             *  ??????????????????TAG?????? ???????????????????????????
             *  BarEntry(float x, float y, Object data)
             *  e.getData()
             */
            entries.add(BarEntry(i.toFloat(), yValueList.get(i)));
        }
            // ?????????BarDataSet?????????????????????
            var barDataSet = BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);

            currentPosition++;
        }

//X???????????????
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
        xAxis?.setLabelRotationAngle(-30f);//x?????????????????????
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
         * float groupSpace = 0.3f;   //???????????????????????????
         * float barSpace =  0.05f;  //??????????????????????????????  ?????????????????????
         * float barWidth = 0.3f;    //????????????????????????     ?????????????????????
         * (barWidth + barSpace) * 2 + groupSpace = (0.3 + 0.05) * 2 + 0.3 = 1.00
         * 3????????? ????????? ???????????? 1 ???100% ???????????????????????? ????????? ??????????????? ???????????????
         */
        var barAmount = dataLists.size; //?????????????????????????????? ??????
        //?????????????????????30% ??????????????????????????? 70% /barAmount  ????????????????????? 0%
        var groupSpace = 0.3f; //???????????????????????????
        var barWidth = (1f - groupSpace) / barAmount;
        var barSpace = 0f;
        //?????????????????????
        data.setBarWidth(barWidth);
        //(??????????????????????????????????????????????????????)
        data.groupBars(0f, groupSpace, barSpace);
        barChart.setData(data);
        //????????????????????????????????????(0-5)
        barChart.setVisibleXRange(0f,5f);
        //??????x????????????????????????????????????
        barChart.notifyDataSetChanged();

        xAxis?.setAxisMinimum(0f);
        xAxis?.setAxisMaximum(xValues.size.toFloat());
        //???X????????????????????????
        xAxis?.setCenterAxisLabels(true);
    }

    fun getStringToFloat(string:String):Float{
        if (string.equals("")){
            return 0f
        }else{
            return string.toFloat()
        }
    }


    //?????????  ??????
    public fun zjdSpBar(activity:Activity,message: ArrayList<Float>,stringList: ArrayList<String>, barChart: BarChart) {
//        message.clear()
        if (barChart != null&&message.size>0){
//            barChart.setOnChartValueSelectedListener(this);
            barChart.getDescription().setEnabled(false);
            barChart.setMaxVisibleValueCount(40);
            // ???????????????????????????x??????y???
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(true);
            barChart.setHighlightFullBarEnabled(false);
            barChart.setScaleEnabled(false);
            barChart.setDoubleTapToZoomEnabled(false);


            // ??????y???????????????
            var leftAxis = barChart.getAxisLeft()
            leftAxis.setValueFormatter(object:ValueFormatter(){})
            leftAxis.valueFormatter = object :ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return super.getFormattedValue(value.toInt().toFloat())
                }
            }
            leftAxis.setAxisMinimum(0f);
            // ?????? ?????????
            leftAxis.setDrawGridLines(true)
            barChart.getAxisRight().setEnabled(false)
            var xLabels = barChart.xAxis
            xLabels.setPosition(XAxis.XAxisPosition.BOTTOM)
            xLabels.setGranularity(1f)
            // ?????? ?????? ?????????
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
                if (stringList.get(i).equals("??????")){
                    arrayList1.add(yoyListEntity.object3.toFloat())
                }else if (stringList.get(i).equals("??????")){
                    arrayList1.add(yoyListEntity.object4.toFloat())
                }else if (stringList.get(i).equals("??????")){
                    arrayList1.add(yoyListEntity.object5.toFloat())//yoyListEntity.object5.toFloat() 20f
                }else if (stringList.get(i).equals("??????")){
                    arrayList1.add(yoyListEntity.object6.toFloat())
                }else if (stringList.get(i).equals("??????")){
                    arrayList1.add(yoyListEntity.object7.toFloat())
                }else if (stringList.get(i).equals("??????")){
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
    //?????????
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
            set1 = BarDataSet(yVals1, "")//???????????????????????????
            set1.setColors(getColorsSP()!!.toList())
            set1.setStackLabels( arrayOf("????????????", "????????????", "????????????", "????????????", "????????????"))

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
                textTemp += "?????????:" + get1.get(0).toInt()+""
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
        //???????????????????????????????????????
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

    //LineChartInViewPager  ???????????????
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
        //X???
        lineChart.getAxisLeft().gridColor = Color.parseColor("#d7c3ff")
//        lineChart.setExtraOffsets(5f, 10f, 5f, 5f)
        //        toggleFilled(lineChartEntity, drawables, colors)
        //??????x?????????
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
         * ???????????????????????????????????????
         */
//         lineChartEntity.setLineMode(LineDataSet.Mode.LINEAR);

        var xAxis = lineChart.getXAxis()
        xAxis.setLabelRotationAngle(30f) //????????????
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
            // ???????????????????????????x??????y???
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(false);
            barChart.setHighlightFullBarEnabled(false);
            barChart.setScaleEnabled(false);
            barChart.setDoubleTapToZoomEnabled(false);

            // ??????y???????????????
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

    //?????????
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
            set1 = BarDataSet(yVals1, "")//???????????????????????????
            set1.setColors(getColors()!!.toList())
            set1.setStackLabels( arrayOf("????????????", "????????????", "????????????", "?????????"))

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
                textTemp += "????????????:" + get1.get(0).toInt()
                textTemp += "\n"
                textTemp += "????????????:" + get1.get(1).toInt()
                textTemp += "\n"
                textTemp += "????????????:" + get1.get(2).toInt()
                textTemp += "\n"
                textTemp += "?????????:" + get1.get(3).toInt()
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
        //???????????????????????????????????????
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