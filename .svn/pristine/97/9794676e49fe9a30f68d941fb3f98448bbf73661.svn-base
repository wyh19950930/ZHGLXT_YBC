package com.jymj.zhglxt.zjd.activity.zjdgl

import android.graphics.Color
import android.graphics.Matrix
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.util.FileUtil
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.zjd.adapter.CeSuanAdapter
import com.jymj.zhglxt.zjd.bean.BaseDataChartBean
import com.jymj.zhglxt.zjd.bean.yzt.cs.*
import com.setsuna.common.base.BaseActivity
//import com.jymj.huairou.ui.main.activity.MapActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.commonutils.*
import kotlinx.android.synthetic.main.activity_basedata.*
import kotlinx.android.synthetic.main.fragment_zjd_yqgl.*
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

class BaseDataActivity : BaseActivity<BasePresenter<*, *>, BaseModel>() {
    /* override fun initDataBinding() {
         setContentView(R.layout.activity_basedata)
     }*/
    var data : XzDateEntity? = null
    var csEntity : CSEntity? = null
    var csEntity2 : CSEntity? = null
    private var data1 = ArrayList<MultiItemEntity>()
    var ceSuanAdapter: CeSuanAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_basedata
    }

    override fun initPresenter() {

    }

    override fun initViews() {
        basedata_rg.setOnCheckedChangeListener(myCheckChangeListener())
        acib_act_basedata_back.setOnClickListener { finish() }
    }

    override fun initDatas() {
//         data = intent.getSerializableExtra("data") as XzDateEntity
//        csEntity = intent.getSerializableExtra("cs") as CSEntity

        csEntity = intent.getSerializableExtra("ceSuna") as CSEntity
        csEntity2 = intent.getSerializableExtra("ceSuna2") as CSEntity
        data = csEntity!!.xzDateEntity

        tv_base_data_zrk.setText(data!!.rk.toString())
        val i = data!!.rk.toFloat() / data!!.zs.toFloat()
        if(i.equals("NAN")){
            tv_base_data_hjrk.setText((BigDecimal(i.toString()).setScale(2, RoundingMode.HALF_UP)).toString())
        }else{
            tv_base_data_hjrk.setText("")
        }

        tv_base_data_zs.setText(data!!.zs.toString())
        tv_base_data_gzfw.setText(data!!.czfw.toString())
        tv_base_data_zyzdmj.setText(data!!.zhzdmj.toString())
        tv_base_data_jzmj.setText(data!!.zhjzmj.toString())
        tv_base_data_wjfmj.setText(data!!.wjfmj.toString())

        tv_base_data_zs1.setText(data!!.count4.toString())
        tv_base_data_zdmj1.setText(data!!.count4zd.toString())
        tv_base_data_jzmj1.setText(data!!.count4jz.toString())

        tv_base_data_zs2.setText(data!!.count46.toString())
        tv_base_data_zdmj2.setText(data!!.count46zd.toString())
        tv_base_data_jzmj2.setText(data!!.count46jz.toString())

        tv_base_data_zs3.setText(data!!.count68.toString())
        tv_base_data_zdmj3.setText(data!!.count68zd.toString())
        tv_base_data_jzmj3.setText(data!!.count68jz.toString())

        tv_base_data_zs4.setText(data!!.count81.toString())
        tv_base_data_zdmj4.setText(data!!.count81zd.toString())
        tv_base_data_jzmj4.setText(data!!.count81jz.toString())

        tv_base_data_zs5.setText(data!!.count112.toString())
        tv_base_data_zdmj5.setText(data!!.count112zd.toString())
        tv_base_data_jzmj5.setText(data!!.count112jz.toString())

        tv_base_data_zs6.setText(data!!.count12.toString())
        tv_base_data_zdmj6.setText(data!!.count12zd.toString())
        tv_base_data_jzmj6.setText(data!!.count12jz.toString())

        tv_base_data_zydgq.setText(data!!.zhzdmj.toString())
        tv_base_data_hj.setText((data!!.zhzdmj.toFloat() / data!!.zs * 10000).toString())
        tv_base_data_hg.setText("")//data.zhzdmj.toString()

        tv_base_data_gzyd2.setText(data!!.ff1.toString())
        tv_base_data_zydgq2.setText(data!!.ff1ghmj.toString())
        tv_base_data_hj2.setText(data!!.ff1hjmj.toString())
        tv_base_data_hg2.setText((BigDecimal(data!!.ff1hjmj.toFloat() * 0.6).setScale(2, RoundingMode.HALF_UP)).toString())

        tv_base_data_gzyd3.setText(data!!.ff2.toString())
        tv_base_data_zydgq3.setText(data!!.ff2ghmj.toString())
        tv_base_data_hj3.setText(data!!.ff2hjmj.toString())
        tv_base_data_hg3.setText((BigDecimal(data!!.ff2hjmj.toFloat() * 0.6).setScale(2, RoundingMode.HALF_UP)).toString())

        tv_base_data_gzyd4.setText(data!!.ff3.toString())
        tv_base_data_zydgq4.setText(data!!.ff3ghmj.toString())
        tv_base_data_hj4.setText(data!!.ff3hjmj.toString())
        tv_base_data_hg4.setText((BigDecimal(data!!.ff3hjmj.toFloat() * 0.6).setScale(2, RoundingMode.HALF_UP)).toString())

        tv_base_data_gzyd5.setText(data!!.ff4.toString())
        tv_base_data_zydgq5.setText(data!!.ff4ghmj.toString())
        tv_base_data_hj5.setText(data!!.ff4hjmj.toString())
        tv_base_data_hg5.setText((BigDecimal(data!!.ff4hjmj.toFloat() * 0.6).setScale(2, RoundingMode.HALF_UP)).toString())

        tv_base_data_gzyd6.setText(data!!.ff5.toString())
        tv_base_data_zydgq6.setText(data!!.ff5ghmj.toString())
        tv_base_data_hj6.setText(data!!.ff5hjmj.toString())
        tv_base_data_hg6.setText((BigDecimal(data!!.ff5hjmj.toFloat() * 0.6).setScale(2, RoundingMode.HALF_UP)).toString())

        tv_base_data_gzyd7.setText(data!!.ff51.toString())
        tv_base_data_zydgq7.setText(data!!.ff51ghmj.toString())
        tv_base_data_hj7.setText(data!!.ff51hjmj.toString())
        tv_base_data_hg7.setText((BigDecimal(data!!.ff51hjmj.toFloat() * 0.6).setScale(2, RoundingMode.HALF_UP)).toString())

        val listBean = ArrayList<BaseDataChartBean>()

        val ff1bar = BaseDataChartBean(data!!.dlss, data!!.ff1ghmj, data!!.phzj12, data!!.sy1, "方法一")
        val ff2bar = BaseDataChartBean(data!!.dlss, data!!.ff2ghmj, data!!.phzj12, data!!.sy2, "方法二")
        val ff3bar = BaseDataChartBean(data!!.dlss, data!!.ff3ghmj, data!!.phzj34, data!!.sy3, "方法三")
        val ff4bar = BaseDataChartBean(data!!.dlss, data!!.ff4ghmj, data!!.phzj34, data!!.sy4, "方法四")
        val ff5bar = BaseDataChartBean(data!!.dlss, data!!.ff5ghmj, data!!.phzj5, data!!.sy5, "方法五")
//        val ssnybar = CJBarChartBean(ssnyzdmj, ssnyjzmj, "设施农业");

        listBean.add(ff1bar)
        listBean.add(ff2bar)
        listBean.add(ff3bar)
        listBean.add(ff4bar)
        listBean.add(ff5bar)

        val xAxis = basedata_kfms_bct.xAxis
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

        val description = basedata_kfms_bct.getDescription()
        description.setText("")
        basedata_kfms_bct.setDescription(description)
        // y 数据
        val yValues = java.util.ArrayList<BarEntry>()
        // y2 数据
        val yValues2 = java.util.ArrayList<BarEntry>()
        // y3 数据
        val yValues3 = java.util.ArrayList<BarEntry>()
        val yValues4 = java.util.ArrayList<BarEntry>()

        val yVals1 = ArrayList<BarEntry>()

        for (x in listBean.indices) {
            val ob1 = listBean.get(x).ob1
            yValues.add(BarEntry(x.toFloat() + 0.5f, ob1.toFloat()))

            val ob2 = listBean.get(x).ob2
            yValues2.add(BarEntry(x.toFloat() + 0.5f, ob2.toFloat()))

            val ob3 = listBean.get(x).ob3
            yValues3.add(BarEntry(x.toFloat() + 0.5f, ob3.toFloat()))

            val ob4 = listBean.get(x).ob4
            yValues4.add(BarEntry(x.toFloat() + 0.5f, ob4.toFloat()))

            yVals1.add(BarEntry(x.toFloat() + 0.5f, floatArrayOf(ob1.toFloat(), ob2.toFloat(), ob3.toFloat(), ob4.toFloat())))
        }

        // y 轴数据集
        val barDataSet = BarDataSet(yValues, "道路及工服设施用地(公顷)")
        barDataSet.color = Color.parseColor("#4570F4")

        // y2 轴数据集
        val barDataSet2 = BarDataSet(yValues2, "宅院用地(公顷)")
        barDataSet2.color = Color.parseColor("#FF5F60")

        // y3 轴数据集
        val barDataSet3 = BarDataSet(yValues3, "平衡资金用地(公顷)")
        barDataSet3.color = Color.parseColor("#F79D30")

        val barDataSet4 = BarDataSet(yValues4, "剩余集体产业用地(公顷)")
        barDataSet4.color = Color.parseColor("#5BCB88")

        //val mBarData = BarData(barDataSet, barDataSet2,barDataSet3,barDataSet4)
        val mBarData = BarDataSet(yVals1, "柱状图")
        mBarData.setColors(getColors())

        mBarData.setStackLabels(arrayOf("道路及工服设施用地(公顷)","宅院用地(公顷)","平衡资金用地(公顷)","剩余集体产业用地(公顷)"))
        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(mBarData)
        val groupSpace = 0.04f
        val barSpace = 0.03f
        val barWidth = 0.21f
        // 设置 柱子宽度
        //mBarData!!.barWidth = barWidth
        val datas = BarData(dataSets)
        basedata_kfms_bct.data = datas
//        basedata_kfms_bct.groupBars(0.0f, groupSpace, barSpace)
        basedata_kfms_bct.invalidate()
        val mMatrix = Matrix()
        mMatrix.postScale(1f, 1f)
        basedata_kfms_bct.getXAxis().setDrawAxisLine(false)
        basedata_kfms_bct.getXAxis().setDrawGridLines(false)
        basedata_kfms_bct.viewPortHandler.refresh(mMatrix, basedata_kfms_bct, false)
        basedata_kfms_bct.animateY(800)
        basedata_kfms_bct!!.setScaleEnabled(false)//启用/禁用两个轴上的图表缩放
        basedata_kfms_bct!!.setScaleXEnabled(false)//启用/禁用x轴缩放
        basedata_kfms_bct!!.setScaleYEnabled(false)//启用/禁用y轴缩放
        basedata_kfms_bct!!.setPinchZoom(false)//如果设置为true，则启用缩放缩放。如果禁用，则可以单独缩放x轴和y轴
        basedata_kfms_bct!!.setDoubleTapToZoomEnabled(false)//
        xAxis.axisMinimum = -0.5f
        xAxis.axisMaximum = basedata_kfms_bct!!.getData().getXMax() + 1f
        recyCeSuan.layoutManager = object : LinearLayoutManager(this@BaseDataActivity, LinearLayoutManager.VERTICAL,
                false) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val markerView = NewMarkerView(this, R.layout.custom_marker_view_layout)
        markerView.setCallBack(NewMarkerView.CallBack { x, value ->
            val index = x.toInt()
            if (index < 0) {
                return@CallBack
            }
            if (index > listBean.size) {
                return@CallBack
            }
            var textTemp = ""

            if (index <= listBean.size) {
                if (!StringUtils.isEmpty(textTemp)) {
                }
                val get = listBean.get(index)
//                val get1 = stringList.get(index)
                textTemp = get.name//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                textTemp += "\n"
                textTemp += "道路及工服设施用地:" + get.ob1
                textTemp += "\n"
                textTemp += "宅院用地:" + get.ob2
                textTemp += "\n"
                textTemp += "平衡资金用地:" + get.ob3
                textTemp += "\n"
                textTemp += "剩余集体产业用地:" + get.ob4
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(basedata_kfms_bct)
        basedata_kfms_bct.setMarker(markerView)
        /*ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
        recyCeSuan.adapter = ceSuanAdapter
        ceSuanAdapter!!.expandAll()
        recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
        clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()*/

        /* tv_basedata_2_1.text = data.hs.toString()
         tv_basedata_2_2.text = data.ncount.toString()
         tv_basedata_2_3.text = data.feincount.toString()
         tv_basedata_2_4.text = data.jjzz.toString()
         tv_basedata_2_5.text = data.ztrk.toString()

         tv_basedata_4_2.text = data.jsydmj.stripTrailingZeros().toPlainString()
         tv_basedata_4_3.text = data.nydmj.stripTrailingZeros().toPlainString()
         tv_basedata_4_4.text = data.gdmj.stripTrailingZeros().toPlainString()
         tv_basedata_4_5.text = data.wlydmj.stripTrailingZeros().toPlainString()
         tv_basedata_4_6.text = data.txhj.stripTrailingZeros().toPlainString()
         tv_basedata_5_2.text = data.gyjsydmj.stripTrailingZeros().toPlainString()
         tv_basedata_5_3.text = data.gynydmj.stripTrailingZeros().toPlainString()
         tv_basedata_5_4.text = data.gygdmj.stripTrailingZeros().toPlainString()
         tv_basedata_5_5.text =data.gywlydmj.stripTrailingZeros().toPlainString()
         tv_basedata_5_6.text = data.gytxhj.stripTrailingZeros().toPlainString()

         tv_basedata_7_2.text = data.zhaicount.toString()
         tv_basedata_7_3.text = data.feicount.toString()
         tv_basedata_7_4.text = data.jtwycount.toString()
         tv_basedata_7_5.text = data.hjcount.toString()
         tv_basedata_8_2.text = data.zhaimj.stripTrailingZeros().toPlainString()
         tv_basedata_8_3.text = data.feimj.stripTrailingZeros().toPlainString()
         tv_basedata_8_4.text = data.jtwyzdmj.stripTrailingZeros().toPlainString()
         tv_basedata_8_5.text = data.hjzdmj.stripTrailingZeros().toPlainString()
         tv_basedata_9_2.text = data.zhaijzmj.stripTrailingZeros().toPlainString()
         tv_basedata_9_3.text = data.feijzmj.stripTrailingZeros().toPlainString()
         tv_basedata_9_4.text = data.jtwyjzmj.stripTrailingZeros().toPlainString()
         tv_basedata_9_5.text = data.hjjzmj.stripTrailingZeros().toPlainString()
         tv_basedata_10_2.text = ""
         tv_basedata_10_3.text = ""
         tv_basedata_10_4.text = ""
         tv_basedata_10_5.text = ""

         tv_basedata_12_2.text = data.pgjs
         tv_basedata_12_3.text = data.zzsljs
         tv_basedata_12_4.text = data.mlxcjs
         tv_basedata_12_5.text = data.ztgzjs


         tv_basedata_13_2.text = data.pggh.stripTrailingZeros().toPlainString()
         tv_basedata_13_2_.text = data.pgzdgh.stripTrailingZeros().toPlainString()
         tv_basedata_13_3.text = data.zzslgh.stripTrailingZeros().toPlainString()
         tv_basedata_13_3_.text = data.zzslzdgh.stripTrailingZeros().toPlainString()
         tv_basedata_13_4.text = data.mlxcgh.stripTrailingZeros().toPlainString()
         tv_basedata_13_4_.text = data.mlxczdgh.stripTrailingZeros().toPlainString()
         tv_basedata_13_5.text = data.ztgzgh.stripTrailingZeros().toPlainString()
         tv_basedata_13_5_.text = data.ztgzzdgh.stripTrailingZeros().toPlainString()

         tv_basedata_15_2.text = data.mbdjqw
         tv_basedata_15_3.text = data.mbxzfj.stripTrailingZeros().toPlainString()
         tv_basedata_15_4.text = data.mbjzdj.stripTrailingZeros().toPlainString()
         tv_basedata_15_5.text = data.mbksxdj.stripTrailingZeros().toPlainString()
         tv_basedata_15_6.text = ""
         tv_basedata_16_2.text = data.bzdjqw
         tv_basedata_16_3.text = data.bzxzfj.stripTrailingZeros().toPlainString()
         tv_basedata_16_4.text = data.bzjzdj.stripTrailingZeros().toPlainString()
         tv_basedata_16_5.text = data.bzksxdj.stripTrailingZeros().toPlainString()
         tv_basedata_16_6.text = ""

         tv_basedata_18_2.text = "1.50"
         tv_basedata_18_3.text = (data.jsydmj.multiply(0.65.toBigDecimal().multiply(1.5.toBigDecimal()))).stripTrailingZeros().toPlainString()
         tv_basedata_18_4.text = (data.jsydmj.multiply(0.65.toBigDecimal())).stripTrailingZeros().toPlainString()

         tv_basedata_19_2.text = "1.80"
         tv_basedata_19_3.text = data.pggh.stripTrailingZeros().toPlainString()
         tv_basedata_19_4.text = (data.pggh.divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN)).stripTrailingZeros().toPlainString()
         tv_basedata_20_2.text = "1.80"
         tv_basedata_20_3.text = csEntity.getmCList1()[csEntity.getmCList1().size-1].sumcost
         tv_basedata_20_4.text = (csEntity.getmCList1()[csEntity.getmCList1().size-1].sumcost.toBigDecimal().divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN)).stripTrailingZeros().toPlainString()
         tv_basedata_21_3.text = ((data.jsydmj.multiply(0.65.toBigDecimal().multiply(1.5.toBigDecimal()))).subtract(data.pggh)).subtract(csEntity.getmCList1()[csEntity.getmCList1().size-1].sumcost.toBigDecimal()).stripTrailingZeros().toPlainString()
         tv_basedata_21_4.text = ((data.jsydmj.multiply(0.65.toBigDecimal())).subtract((data.pggh.divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN)))).subtract((csEntity.getmCList1()[csEntity.getmCList1().size-1].sumcost.toBigDecimal().divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN))).stripTrailingZeros().toPlainString()

         tv_basedata_22_2.text = "1.80"
         tv_basedata_22_3.text = data.zzslgh.stripTrailingZeros().toPlainString()
         tv_basedata_22_4.text = data.zzslgh.divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
         tv_basedata_23_2.text = "1.80"
         tv_basedata_23_3.text = csEntity.getmCList2()[csEntity.getmCList2().size-1].sumcost
         tv_basedata_23_4.text = csEntity.getmCList2()[csEntity.getmCList2().size-1].sumcost.toBigDecimal().divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
         tv_basedata_24_3.text = ((data.jsydmj.multiply(0.65.toBigDecimal().multiply(1.5.toBigDecimal()))).subtract(data.zzslgh)).subtract(csEntity.getmCList2()[csEntity.getmCList2().size-1].sumcost.toBigDecimal()).stripTrailingZeros().toPlainString()
         tv_basedata_24_4.text = ((data.jsydmj.multiply(0.65.toBigDecimal())).subtract((data.zzslgh.divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN)))).subtract((csEntity.getmCList2()[csEntity.getmCList2().size-1].sumcost.toBigDecimal().divide(1.8.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN))).stripTrailingZeros().toPlainString()

         tv_basedata_25_2.text = "1.00"
         tv_basedata_25_3.text = data.mlxcgh.stripTrailingZeros().toPlainString()
         tv_basedata_25_4.text = data.mlxcgh.divide(1.0.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
         tv_basedata_26_2.text = "1.50"
         tv_basedata_26_3.text = csEntity.getmCList3()[csEntity.getmCList3().size-1].sumcost
         tv_basedata_26_4.text = csEntity.getmCList3()[csEntity.getmCList3().size-1].sumcost.toBigDecimal().divide(1.5.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
         tv_basedata_27_3.text = ((data.mlxcgh.multiply(0.65.toBigDecimal().multiply(1.5.toBigDecimal()))).subtract(data.mlxcgh)).subtract(csEntity.getmCList3()[csEntity.getmCList3().size-1].sumcost.toBigDecimal()).stripTrailingZeros().toPlainString()
         tv_basedata_27_4.text = ((data.mlxcgh.multiply(0.65.toBigDecimal())).subtract((data.mlxcgh.divide(1.0.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN)))).subtract((csEntity.getmCList3()[csEntity.getmCList3().size-1].sumcost.toBigDecimal().divide(1.5.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN))).stripTrailingZeros().toPlainString()
         //判断是否是通州的
         if (ApiConstants.NAME.equals("szh")||ApiConstants.NAME.equals("th")||ApiConstants.NAME.equals("hx")||ApiConstants.NAME.equals("zjw")||ApiConstants.NAME.equals("yjw")){
             tv_basedata_28.visibility = View.GONE
             tv_basedata_28_1.visibility = View.GONE
             tv_basedata_28_2.visibility = View.GONE
             tv_basedata_28_3.visibility = View.GONE
             tv_basedata_28_4.visibility = View.GONE
             tv_basedata_28_5.visibility = View.GONE
             tv_basedata_29_1.visibility = View.GONE
             tv_basedata_29_2.visibility = View.GONE
             tv_basedata_29_3.visibility = View.GONE
             tv_basedata_29_4.visibility = View.GONE
             tv_basedata_29_5.visibility = View.GONE
             tv_basedata_30_1.visibility = View.GONE
             tv_basedata_30_2.visibility = View.GONE
             tv_basedata_30_3.visibility = View.GONE
             tv_basedata_30_4.visibility = View.GONE
             tv_basedata_30_5.visibility = View.GONE

             tv_basedata_31.visibility = View.GONE
             tv_basedata_31_1.visibility = View.GONE
             tv_basedata_31_2.visibility = View.GONE
             tv_basedata_31_3.visibility = View.GONE
             tv_basedata_31_4.visibility = View.GONE
             tv_basedata_31_5.visibility = View.GONE
             tv_basedata_32_2.visibility = View.GONE
             tv_basedata_32_1.visibility = View.GONE
             tv_basedata_32_3.visibility = View.GONE
             tv_basedata_32_4.visibility = View.GONE
             tv_basedata_32_5.visibility = View.GONE
             tv_basedata_33_1.visibility = View.GONE
             tv_basedata_33_2.visibility = View.GONE
             tv_basedata_33_3.visibility = View.GONE
             tv_basedata_33_4.visibility = View.GONE
             tv_basedata_33_5.visibility = View.GONE
         }else{
             tv_basedata_28_2.text = "1.00"
             tv_basedata_28_3.text = data.mlxcgh.stripTrailingZeros().toPlainString()
             tv_basedata_28_4.text = data.mlxcgh.divide(1.0.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
             tv_basedata_29_2.text = "1.50"
             tv_basedata_29_3.text = csEntity.getmCList4()[csEntity.getmCList4().size-1].sumcost
             tv_basedata_29_4.text = csEntity.getmCList4()[csEntity.getmCList4().size-1].sumcost.toBigDecimal().divide(1.5.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
             tv_basedata_30_3.text = ((data.mlxcgh.multiply(0.65.toBigDecimal().multiply(1.5.toBigDecimal()))).subtract(data.mlxcgh)).subtract(csEntity.getmCList4()[csEntity.getmCList4().size-1].sumcost.toBigDecimal()).stripTrailingZeros().toPlainString()
             tv_basedata_30_4.text = ((data.mlxcgh.multiply(0.65.toBigDecimal())).subtract((data.mlxcgh.divide(1.0.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN)))).subtract((csEntity.getmCList4()[csEntity.getmCList4().size-1].sumcost.toBigDecimal().divide(1.5.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN))).stripTrailingZeros().toPlainString()

             tv_basedata_31_2.text = "1.00"
             tv_basedata_31_3.text = data.mlxcgh.stripTrailingZeros().toPlainString()
             tv_basedata_31_4.text = data.mlxcgh.divide(1.0.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
             tv_basedata_32_2.text = "1.50"
             tv_basedata_32_3.text = csEntity.getmCList5()[csEntity.getmCList5().size-1].sumcost
             tv_basedata_32_4.text = csEntity.getmCList5()[csEntity.getmCList5().size-1].sumcost.toBigDecimal().divide(1.5.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString()
             tv_basedata_33_3.text = ((data.mlxcgh.multiply(0.65.toBigDecimal().multiply(1.5.toBigDecimal()))).subtract(data.mlxcgh)).subtract(csEntity.getmCList5()[csEntity.getmCList5().size-1].sumcost.toBigDecimal()).stripTrailingZeros().toPlainString()
             tv_basedata_33_4.text = ((data.mlxcgh.multiply(0.65.toBigDecimal())).subtract((data.mlxcgh.divide(1.0.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN)))).subtract((csEntity.getmCList5()[csEntity.getmCList5().size-1].sumcost.toBigDecimal().divide(1.5.toBigDecimal(),2,BigDecimal.ROUND_HALF_DOWN))).stripTrailingZeros().toPlainString()

         }*/

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
                colors.add(Color.argb(255, 0, 255, 255))
                colors.add(Color.argb(255,99,88,94))
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

    fun toPDF(view: View) {
        val pdfPath = AppMenus.getInstance().cardPath + "/jymj/" + AppUtils.getAppName() + "-基本信息.pdf"
        val success = PDFUtil.createPdfFromView(pdf_basedata, pdf_basedata.measuredWidth, pdf_basedata.measuredHeight, pdfPath)
        if (success) {
            val content = TextView(this@BaseDataActivity)
            content.text = "是否打开导出的文件？"
            SweetAlertDialog(this@BaseDataActivity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("打开文件")
                    .setCustomView(content)
                    .setConfirmButton("确定") { sweetAlertDialog ->
                        FileUtil.openFile(this@BaseDataActivity, File(pdfPath))
                    }
                    .setCancelButton(getString(R.string.cancel), { sweetAlertDialog ->
                        sweetAlertDialog?.dismiss()
                    })
                    .show()
        } else {
            ToastUtils.showLong("导出失败！")
        }
    }

    private inner class myCheckChangeListener : RadioGroup.OnCheckedChangeListener {

        override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
            when (checkedId) {
                R.id.basedata_cxz -> {
                    basedata_cxz_ll.visibility = View.VISIBLE
                    basedata_kfms_ll.visibility = View.GONE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.GONE
                    clCeSuanPDF.visibility = View.GONE
                    ll_ce_suan_fwlx.visibility = View.GONE
                    ll_ce_suan_fwlx2.visibility = View.GONE
                }
                R.id.basedata_kfms -> {
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.GONE
                    clCeSuanPDF.visibility = View.GONE
                    ll_ce_suan_fwlx.visibility = View.GONE
                    ll_ce_suan_fwlx2.visibility = View.GONE
                }
                R.id.basedata_tdzs -> {
                    dataFormat(csEntity2!!.getmCList1())
                    ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
                    recyCeSuan.adapter = ceSuanAdapter
                    ceSuanAdapter!!.expandAll()
//                    recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
//                    clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.GONE
                    clCeSuanPDF.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.GONE
                    ll_ce_suan_fwlx.visibility = View.GONE
                    ll_ce_suan_fwlx2.visibility = View.GONE

                }
                R.id.basedata_zzsl -> {
                    dataFormat(csEntity2!!.getmCList2())
                    ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
                    recyCeSuan.adapter = ceSuanAdapter
                    ceSuanAdapter!!.expandAll()
//                    recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
//                    clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.GONE
                    clCeSuanPDF.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.GONE
                    ll_ce_suan_fwlx.visibility = View.GONE
                    ll_ce_suan_fwlx2.visibility = View.GONE

                }

                R.id.basedata_ffyi -> {
                    dataFormat(csEntity!!.getmCList1())
                    ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
                    recyCeSuan.adapter = ceSuanAdapter
                    ceSuanAdapter!!.expandAll()
//                    recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
//                    clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.GONE
                    clCeSuanPDF.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.VISIBLE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.GONE
                    ll_ce_suan_fwlx.visibility = View.GONE
                    ll_ce_suan_fwlx2.visibility = View.GONE

                }
                R.id.basedata_ffer -> {
                    dataFormat(csEntity!!.getmCList1())
                    ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
                    recyCeSuan.adapter = ceSuanAdapter
                    ceSuanAdapter!!.expandAll()
//                    recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
//                    clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.GONE
                    clCeSuanPDF.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.VISIBLE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.GONE
                    ll_ce_suan_fwlx.visibility = View.GONE
                    ll_ce_suan_fwlx2.visibility = View.GONE

                }
                R.id.basedata_ffsan -> {
                    initDataFfSan()
                    dataFormat(csEntity!!.getmCList2())
                    ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
                    recyCeSuan.adapter = ceSuanAdapter
                    ceSuanAdapter!!.expandAll()
//                    recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
//                    clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.GONE
                    clCeSuanPDF.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.VISIBLE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.GONE


                }
                R.id.basedata_ffsi -> {
                    initDataFfSan()
                    dataFormat(csEntity!!.getmCList2())
                    ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
                    recyCeSuan.adapter = ceSuanAdapter
                    ceSuanAdapter!!.expandAll()
//                    recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
//                    clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.GONE
                    clCeSuanPDF.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.VISIBLE
                    ll_cesuan_ms5.visibility = View.GONE

                }
                R.id.basedata_ffwu -> {
                    initDataFfWu()
                    dataFormat(csEntity!!.getmCList3())
                    ceSuanAdapter = CeSuanAdapter(this@BaseDataActivity, data1)
                    recyCeSuan.adapter = ceSuanAdapter
                    ceSuanAdapter!!.expandAll()
//                    recyCeSuan.addItemDecoration(DividerItemDecoration(this@BaseDataActivity, DividerItemDecoration.VERTICAL))
//                    clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this@BaseDataActivity, (data1.size + 1) * 41f).toInt()
                    basedata_cxz_ll.visibility = View.GONE
                    basedata_kfms_ll.visibility = View.GONE
                    clCeSuanPDF.visibility = View.VISIBLE
                    ll_cesuan_ms1.visibility = View.GONE
                    ll_cesuan_ms2.visibility = View.GONE
                    ll_cesuan_ms3.visibility = View.GONE
                    ll_cesuan_ms4.visibility = View.GONE
                    ll_cesuan_ms5.visibility = View.VISIBLE

                }
            }
        }
    }

    private fun dataFormat(list: MutableList<MoveCost2>) {
        data1.clear()
        for (l in list) {
            if (l.count == "") {
                val level0 = MoveCostLevel0()
                level0.count = l.count
                level0.project = l.project
                level0.quantitie = l.quantitie
                level0.unit = l.unit
                level0.bilprice = l.bilprice
                level0.quaunit = l.quaunit
                level0.sumcost = l.sumcost
                level0.isHasChild = false
                data1.add(level0)
            } else if (l.count.length <= 2) {
                var count = l.count
                val level0 = MoveCostLevel0()
                level0.count = count
                level0.project = l.project
                level0.quantitie = l.quantitie
                level0.unit = l.unit
                level0.bilprice = l.bilprice
                level0.quaunit = l.quaunit
                level0.sumcost = l.sumcost
                for (i in list) {
                    if (i.count != null) {
                        var count2 = i.count
                        val split = count2.split(".")
                        if (split.size == 2 && count2.startsWith(count)) {
                            val level1 = MoveCostLevel1()
                            level1.count = count2
                            level1.project = i.project
                            level1.quantitie = i.quantitie
                            level1.unit = i.unit
                            level1.bilprice = i.bilprice
                            level1.quaunit = i.quaunit
                            level1.sumcost = i.sumcost

                            for (s in list) {
                                if (s.count != null) {
                                    var count3 = s.count
                                    val split2 = count3.split(".")
                                    if (split2.size == 3 && count3.startsWith(count2)) {
                                        val level2 = MoveCostLevel2()
                                        level2.count = count3
                                        level2.project = s.project
                                        level2.quantitie = s.quantitie
                                        level2.unit = s.unit
                                        level2.bilprice = s.bilprice
                                        level2.quaunit = s.quaunit
                                        level2.sumcost = s.sumcost
                                        level1.isHasChild = true
                                        level1.addSubItem(level2)
                                    }
                                }
                            }
                            level0.isHasChild = true
                            level0.addSubItem(level1)
                        }
                    }
                }
                data1.add(level0)
            }
        }
    }

    private fun initDataFfWu() {
        ll_ce_suan_fwlx.visibility = View.GONE
        ll_ce_suan_fwlx2.visibility = View.VISIBLE
        tv_base_data_bfgbf_jzmj1.setText(data!!.dlzz5200.toString())
        tv_base_data_bfgbf_xsdj1.setText("0")
        tv_base_data_bfgbf_je1.setText("0")

        tv_base_data_bfgbf_jzmj2.setText(data!!.dlzz575n.toString())
        tv_base_data_bfgbf_xsdj2.setText(data!!.dlzz575ndj.toString())
        tv_base_data_bfgbf_je2.setText(data!!.dlzz575nje.toString())

        tv_base_data_bfgbf_jzmj3.setText(data!!.dlzz575w.toString())
        tv_base_data_bfgbf_xsdj3.setText(data!!.dlzz575wdj.toString())//
        tv_base_data_bfgbf_je3.setText(data!!.dlzz575wje.toString())

        tv_base_data_bfgbf_jzmj4.setText(data!!.dlzz5xj.toString())
        tv_base_data_bfgbf_xsdj4.setText("0")
        tv_base_data_bfgbf_je4.setText(data!!.dlzz575wje.toString())

        tv_base_data_fhbf_jzmj1.setText(data!!.dlzz200.toString())
        tv_base_data_fhbf_xsdj1.setText("0")
        tv_base_data_fhbf_je1.setText("0")

        tv_base_data_fhbf_jzmj2.setText(data!!.dlzz75n.toString())
        tv_base_data_fhbf_xsdj2.setText(data!!.dlzz75ndj.toString())
        tv_base_data_fhbf_je2.setText(data!!.dlzz75nje.toString())

        tv_base_data_fhbf_jzmj3.setText(data!!.dlzz75w.toString())
        tv_base_data_fhbf_xsdj3.setText(data!!.dlzz75wdj.toString())
        tv_base_data_fhbf_je3.setText(data!!.dlzz75wje.toString())

        tv_base_data_fhbf_jzmj4.setText(data!!.dlzzxj.toString())
        tv_base_data_fhbf_xsdj4.setText("")
        tv_base_data_fhbf_je4.setText(data!!.dlzzxjje.toString())

        tv_base_data_hj_jzmj.setText(data!!.dlzzhj.toString())
        tv_base_data_hj_xsdj.setText("")
        tv_base_data_hj_je.setText(data!!.dlzzhjje.toString())

        tv_base_data_cmzzl_jzmj.setText(data!!.zzl.toString())
        tv_base_data_cmzzl_xsdj.setText(data!!.zzldj.toString())
        tv_base_data_cmzzl_je.setText(data!!.zzlje.toString())

        tv_base_data_cm_cjtzc_jzmj.setText("")
        tv_base_data_cm_cjtzc_xsdj.setText(data!!.zch.toString())
        tv_base_data_cm_cjtzc_je.setText(data!!.zch.multiply(BigDecimal(100)).multiply(data!!.dlzz34200).setScale(2,RoundingMode.HALF_UP).toString())///////这个暂时不对
    }

    private fun initDataFfSan() {
        ll_ce_suan_fwlx.visibility = View.VISIBLE
        ll_ce_suan_fwlx2.visibility = View.GONE
        tv_base_data_dlszz_jzmj1.setText(data!!.dlzz34200.toString())
        tv_base_data_dlszz_xsdj1.setText("0")/////xzDateEntity.dlzz34200.toString()
        tv_base_data_dlszz_je1.setText("0")///////

        tv_base_data_dlszz_jzmj2.setText(data!!.dlzz3475n.toString())//dlzz3475n
        tv_base_data_dlszz_xsdj2.setText(data!!.dlzz3475ndj.toString())
        tv_base_data_dlszz_je2.setText(data!!.dlzz3475nje.toString())//

        tv_base_data_dlszz_jzmj3.setText(data!!.dlzz3475w.toString())
        tv_base_data_dlszz_xsdj3.setText(data!!.dlzz3475wdj.toString())
        tv_base_data_dlszz_je3.setText(data!!.dlzz3475wje.toString())

        tv_base_data_dlszz_jzmj4.setText(data!!.dlzz34xj.toString())
        tv_base_data_dlszz_xsdj4.setText("")/////xzDateEntity.zzldj.toString()
        tv_base_data_dlszz_je4.setText(data!!.dlzz34xjje.toString())

        tv_base_data_dlszz_jzmj5.setText(data!!.zzl.toString())
        tv_base_data_dlszz_xsdj5.setText(data!!.zzldj.toString())
        tv_base_data_dlszz_je5.setText(data!!.zzlje.toString())

        tv_base_data_dlszz_jzmj6.setText("")//////
        tv_base_data_dlszz_xsdj6.setText(data!!.zch.toString())/////
        tv_base_data_dlszz_je6.setText(data!!.zch.multiply(BigDecimal(100)).multiply(data!!.dlzz34200).setScale(2,RoundingMode.HALF_UP).toString() + "")////////这个暂时不对
    }


}