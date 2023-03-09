package com.jymj.zhglxt.zjd.fragment.shzl


import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

import com.jymj.zhglxt.R
import com.jymj.zhglxt.home.activity.YllbActivity
import com.jymj.zhglxt.ldrkgl.home.bean.FirstTjBean
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.login.contract.CzlbContract
import com.jymj.zhglxt.login.contract.WyContract
import com.jymj.zhglxt.login.model.CzlbModel
import com.jymj.zhglxt.login.model.WyModel
import com.jymj.zhglxt.login.presenter.CzlbPresenter
import com.jymj.zhglxt.login.presenter.WyPresenter
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.zjd.activity.zjdgl.PropertyActivity
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.StringUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_cz.*
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * 现 物业fragment
 */
class ShzlLdrkFragment : BaseFragment<WyPresenter, WyModel>(), WyContract.View {


    private var position = 0
    private var cnLgy = ""
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ShzlLdrkFragment {
            return ShzlLdrkFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_cz
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

    }

    override fun initDatas() {

    }


    override fun returnCxczqk(msg: List<XzqInfoEntity>) {

        var count = ArrayList<Int>()
        if (msg != null&&msg.size>0) {
            count.add(msg.get(0).zhaicount)
            count.add(msg.get(0).roomcount)
            tv_frag_zjd_cz_wyhgl.text = msg.size.toString()
            tv_frag_zjd_cz_ylzs.text = msg.get(0).zhaicount.toString()
            tv_frag_zjd_cz_fjs.text = msg.get(0).roomTotal.toString()
            tv_frag_zjd_cz_yczf.text = msg.get(0).roomcount.toString()
            tv_frag_zjd_cz_zlrk.text = msg.get(0).flowcount.toString()

        }
        zjdCzBar(count,bct_frag_zjd_cz)

        rlv_frag_zjd_cz.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        /*val zlglAdapter = CzqkAdapter(activity, msg)
        rlv_frag_zjd_cz.adapter =zlglAdapter
        zlglAdapter.setOnItemClick(object: CzqkAdapter.OnItemClick{
            override fun onClick(pjtaskFile: XzqInfoEntity?) {
                if (SingleOnClickUtil1.isFastClick()){
                    position = msg.indexOf(pjtaskFile)
                    val intent = Intent(activity, YllbActivity::class.java)
                    intent.putExtra("code", pjtaskFile?.code)
                    intent.putExtra("xzqmc", pjtaskFile?.xzqmc)
                    startActivity(intent)
                }
            }
        })*/

        rlv_frag_zjd_cz.adapter = object: BaseQuickAdapter<XzqInfoEntity, BaseViewHolder>(R.layout.item_fra_zjd_cz,msg){
            override fun convert(helper: BaseViewHolder?, item: XzqInfoEntity?) {
                    helper!!.setText(R.id.tv_frag_zjd_cz_cm,item!!.xzqmc)
                    .setText(R.id.tv_frag_zjd_cz_czzjd,item.zhaicount.toString())
                    .setText(R.id.tv_frag_zjd_cz_czfw,item.roomcount.toString())
                    .setText(R.id.tv_frag_zjd_cz_ldrk,item.flowcount.toString())

                helper.itemView.setOnClickListener {
                    position = helper.adapterPosition
                    val intent = Intent(activity, PropertyActivity::class.java)
                    AppCache.getInstance().wyCode = item?.code
                    AppCache.getInstance().wyXzqmc = item?.xzqmc
                    /*intent.putExtra("code", item?.code)
                    intent.putExtra("xzqmc", item?.xzqmc)*/
                    startActivity(intent)
                }

            }
        }


        rlv_frag_zjd_cz.scrollToPosition(position)

        if (msg.isNotEmpty()){
            include_frag_zjd_cz.visibility = View.GONE
        }
    }
    override fun returnJbqktj(msg: FirstTjBean) {
        var count = ArrayList<Int>()
        if (msg.xzqInfo != null) {
            count.add(msg.xzqInfo.zhaicount)
            count.add(msg.xzqInfo.roomcount)
        }
        zjdCzBar(count,bct_frag_zjd_cz)
    }

    fun diaoyong(){
        if (supl_frag_zjdgl1!=null){
            Log.e("***********","空")
            supl_frag_zjdgl1?.setScrollableView(slv_frag_zjd_wy)
        }
    }


    val stringList = ArrayList<String>()
    //柱状图
    private fun zjdCzBar(message: ArrayList<Int>, barChart: BarChart) {

        if (barChart != null&&message.size>1){

            stringList.clear()
            stringList.add("宅基地（宗）")
            stringList.add("房屋数（间）")
//            barChart.setOnChartValueSelectedListener(this);
            barChart.getDescription().setEnabled(false);
            barChart.setMaxVisibleValueCount(40);
            // 扩展现在只能分别在x轴和y轴
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(true)
            barChart.setHighlightFullBarEnabled(false)
            barChart.setScaleEnabled(false)
            barChart.setDoubleTapToZoomEnabled(false)
            // 改变y标签的位置
            var leftAxis = barChart.getAxisLeft()
            leftAxis.setValueFormatter(object:ValueFormatter(){})
            leftAxis.valueFormatter = object :ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return super.getFormattedValue(value.toInt().toFloat())
                }
            }
            leftAxis.setAxisMinimum(0f);
            // 取消 横向 网格线
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

                val arrayList1 = ArrayList<Float>()
                if (stringList.get(i).equals("宅基地（宗）")){
                    arrayList1.add(message.get(0).toFloat())
                }else if (stringList.get(i).equals("房屋数（间）")){
                    arrayList1.add(message.get(1).toFloat())
                }
                if (aMax< Collections.max(arrayList1)){
                    aMax = Collections.max(arrayList1)
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
        val stacksize = 4
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#12C483")
        colors[1] = Color.parseColor("#FDB139")
        /*colors[2] = Color.parseColor("#6BC4FF")
        colors[3] = Color.parseColor("#FF6000")*/
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
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

    override fun onResume() {
        super.onResume()

    }

}
