package com.jymj.zhglxt.zjd.fragment.yzt


import android.graphics.Color
import android.graphics.Matrix
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

import com.jymj.zhglxt.R
import com.jymj.zhglxt.zjd.bean.CTGLZhenBean
import com.jymj.zhglxt.zjd.bean.PQListBean
import kotlinx.android.synthetic.main.fragment_ctglzl.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CTGLZLFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ctglzl, container, false)
    }


    fun getData(data: CTGLZhenBean.DataBean){
        if (data != null){//ctglzl_pct

var zong = 0.0
            zong =  data.object1 + data.object3
            //1 未拆除 2拆除中 3已拆除 4总数 5未拆除+拆除中 6已支付7未支付

            //val yzf = data.object6
            val wzf = data.object7
            val count = java.util.ArrayList<Float>()
            val countName = java.util.ArrayList<String>()
            count.add((data.object1/zong).toFloat())
            //count.add(data.object2.toFloat())
            count.add((data.object3/zong).toFloat())
            //count.add(data.object4.toFloat())
            //count.add(data.object5.toFloat())
            //count.add(yzf.toFloat())
            //count.add(wzf.toFloat())
            //count.add(data.object8.toFloat())
            countName.add("未拆除" +" "+ data.object1)
            //countName.add("拆除中")
            countName.add("已拆除"+" "+ data.object3)
            //countName.add("总数")
            //countName.add("未拆除或拆除中")
            //countName.add("已支付")
            //countName.add("未支付")
            //countName.add("总规模")
//            if (ctglzl_pct!=null) {
                zfinitPie(ctglzl_pct,"拆腾镇概况图",count,countName)
//            }



        }

    }
    //饼状图
    private fun zfinitPie(pie: PieChart, title: String, datas: java.util.ArrayList<Float>, lables: java.util.ArrayList<String>) {
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

    //柱状图
     fun getBctData(data : List<PQListBean.DataBean>){

        if (data.size > 0){

            val xAxis = ctglzl_bct.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.labelRotationAngle = 50f
            xAxis.setDrawLabels(true)
            xAxis.granularity = 1f
            xAxis.labelCount = data.size
            xAxis.setCenterAxisLabels(true)//设置标签居中

            xAxis.setValueFormatter(object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()
                    return if (index < 0 || index >= data.size) {
                        ""
                    } else {
                        data.get(index).xmmc
                    }

                }
            })

            val description = ctglzl_bct.getDescription()
            description.setText("")
            ctglzl_bct.setDescription(description)

            val yVals1 = ArrayList<BarEntry>()
            for (x in data.indices){
                val yc = data.get(x).keyValueEntity.object3
                val wc = data.get(x).keyValueEntity.object1
                yVals1.add(BarEntry(x.toFloat() + 0.5f, floatArrayOf(yc.toFloat(), wc.toFloat())))


                val mBarData = BarDataSet(yVals1, "柱状图")
                mBarData.setColors(getColors())

                mBarData.setStackLabels(arrayOf("已拆","未拆"))
                val dataSets = ArrayList<IBarDataSet>()
                dataSets.add(mBarData)
                val groupSpace = 0.04f
                val barSpace = 0.03f
                val barWidth = 0.21f
                // 设置 柱子宽度
                //mBarData!!.barWidth = barWidth
                val datas = BarData(dataSets)
                ctglzl_bct.data = datas
//        ctglzl_bct.groupBars(0.0f, groupSpace, barSpace)
                ctglzl_bct.invalidate()
                val mMatrix = Matrix()
                mMatrix.postScale(1.5f, 1f)
                ctglzl_bct.getXAxis().setDrawAxisLine(false)
                ctglzl_bct.getXAxis().setDrawGridLines(false)
                ctglzl_bct.viewPortHandler.refresh(mMatrix, ctglzl_bct, false)
                ctglzl_bct.animateY(800)
                ctglzl_bct!!.setScaleEnabled(false)//启用/禁用两个轴上的图表缩放
                ctglzl_bct!!.setScaleXEnabled(false)//启用/禁用x轴缩放
                ctglzl_bct!!.setScaleYEnabled(false)//启用/禁用y轴缩放
                ctglzl_bct!!.setPinchZoom(false)//如果设置为true，则启用缩放缩放。如果禁用，则可以单独缩放x轴和y轴
                ctglzl_bct!!.setDoubleTapToZoomEnabled(false)//
                xAxis.axisMinimum = -0.5f
                xAxis.axisMaximum = ctglzl_bct!!.getData().getXMax() + 1f
                

            }
        }
        
    }
    private fun getColors(): java.util.ArrayList<Int> {
        //        var stacksize = 3;
        //有尽可能多的颜色每项堆栈值
        val colors = java.util.ArrayList<Int>()
        /*for (i in 0..4) {
            colors.add(ColorTemplate.VORDIPLOM_COLORS[i])
        }
        for (i in 0..4) {
            colors.add(ColorTemplate.COLORFUL_COLORS[i])
        }
        for (i in 0..4) {
            colors.add(ColorTemplate.PASTEL_COLORS[i])
        }*/
        colors.add(Color.argb(255, 255, 0, 0))
        colors.add(Color.argb(255, 255, 255, 0))
        /*colors.add(Color.argb(255, 0, 255, 255))
        colors.add(Color.argb(255,99,88,94))*/
//
        //colors.add(Color.argb(255,128,128,128))
        //        colors.add(Color.argb(255,255,128,128))
        //        colors.add(Color.argb(255,255,255,128))
        //        colors.add(Color.argb(255,0,255,64))
        //        colors.add(Color.argb(255,0,255,255))
        //        colors.add(Color.argb(255,0,255,255))
        //        colors.add(Color.argb(255,0,255,255))
        //        colors.add(Color.argb(255,0,255,255))
        //        colors.add(Color.argb(255,0,255,255))
        //        colors.add(Color.argb(255,0,255,255))
        return colors
    }
}
