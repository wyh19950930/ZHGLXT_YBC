package com.jymj.zhglxt.zjd.fragment.cy


import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
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
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.jymj.zhglxt.ui.main.contract.QyglActContract
import com.jymj.zhglxt.ui.main.presenter.QyglActPresenter

import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.enums.IndustryEnum
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.JyqkPickerUtil
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.zjd.adapter.QyglDetailAdapter
import com.jymj.zhglxt.zjd.bean.cygl.CyyqZztEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseTypeEnum
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseVoEntity
import com.jymj.zhglxt.zjd.bean.qyzt.JyztEnum
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.StringUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_qygl_detail.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_cyyq.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ZjdCyyqFragment : BaseFragment<QyglActPresenter, QyglActContract.Model>(), QyglActContract.View {
    override fun returnQyglPoint(cash: EnterpriseBasisEntity) {

    }
    private var page = 1
    private var limit = 50
    private var qymc = ""//企业名称
    private var qylx = -1//企业类型
    private var jyzt =-1
    private var zcdz = ""//注册地址
    private var zcdate = ""//注册时间
    private var mPopQylx: CommenPop? = null
    var linearLayoutManager: LinearLayoutManager? = null

    var timePickerView: TimePickerView? = null//时间选择器
    var optionPickerView: OptionsPickerView<Any>? = null//条件选择器
    private var garden = ""
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdCyyqFragment {
            return ZjdCyyqFragment().apply {
                //                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_cyyq
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_act_qygl_detail.layoutManager = linearLayoutManager
        initTimePickerView()

        ll_act_qygl_detail_qylx.setOnClickListener {
            initoptionPickerView("行业类型")//,et_act_qygl_detail_qylx
//            JyqkPickerUtil.initNationPickerView(activity, "行业类型", et_act_qygl_detail_qylx)
        }
        ll_act_qygl_detail_qzcsj.setOnClickListener {
            initoptionPickerView("经营状态")//,et_act_qygl_detail_qylx
//            timePickerView!!.show()
        }
        tv_act_qygl_detail_search.setOnClickListener {
//            zcdate = et_act_qygl_detail_qzcsj.text.toString()
            qymc = et_act_qygl_detail_qymc.text.toString()
            cashList.clear()
            page = 1
            mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc,jyzt)
        }
        tv_act_qygl_detail_reset.setOnClickListener {
            et_act_qygl_detail_qymc.setText("")
            et_act_qygl_detail_qzcsj.setText("")
            et_act_qygl_detail_qylx.setText("")
//            et_act_qygl_detail_qylx.setTextColor(Color.parseColor("#666666"))
//            zcdate = et_act_qygl_detail_qzcsj.text.toString()
            qymc = et_act_qygl_detail_qymc.text.toString()
            qylx = -1
            jyzt = -1
            cashList.clear()
            page = 1
            mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc,jyzt)
        }
    }

    override fun initDatas() {
//        mPresenter.getQyglList(page, limit, qylx, zcdate, zcdz, qymc)

    }

    var cashList = ArrayList<EnterpriseBasisEntity>()
    override fun returnQyglList(cash: List<EnterpriseBasisEntity>) {
        if (page == 1){
            cashList.clear()
        }
        cashList.addAll(cash)
        val i = linearLayoutManager!!.findFirstVisibleItemPosition() + 1
        val qyglDetailAdapter = QyglDetailAdapter(activity, cashList)
        rlv_act_qygl_detail.adapter = qyglDetailAdapter

        rlv_act_qygl_detail.setHasFixedSize(false)
        rlv_act_qygl_detail.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1 && cashList.size == page * limit) {
                    if (cashList.size != 0 && cashList.size % limit == 0) {
                        page++
                        mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc,jyzt)
                    } else {
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
//                            Log.i(TAG, "滑动到底部")
            }
        })
        if (page != 1) {
//            rlv_act_qygl_detail.scrollToPosition((page-1)*limit)
            rlv_act_qygl_detail.scrollToPosition(i)
        }


    }

    //初始化时间选择器
    private fun initTimePickerView() {
        val df = SimpleDateFormat("yyyy-MM-dd")// HH:mm:ss
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate.set(1900, 1, 1)
        val endDate = Calendar.getInstance()
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH))
        timePickerView = TimePickerBuilder(activity, object : OnTimeSelectListener {
            @SuppressLint("SetTextI18n")
            override fun onTimeSelect(date1: Date?, v: View?) {
                et_act_qygl_detail_qzcsj.text = getTime(date1!!)
            }
        })
                .setTimeSelectChangeListener(object : OnTimeSelectChangeListener {
                    override fun onTimeSelectChanged(date: Date?) {

                    }
                })
                .isDialog(true)
                .setType(booleanArrayOf(true, true, true, false, false, false))
                .setItemVisibleCount(3)
                .setLineSpacingMultiplier(2.0f)
                .setCancelColor(Color.parseColor("#999999"))
                .setDate(selectedDate)
                .setRangDate(startDate,endDate)
                .isAlphaGradient(true)
                .build()
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = timePickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        timePickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        timePickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }
    //时间格式转换
    private fun getTime(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")// HH:mm:ss
        return format.format(date)
    }
    //初始化选择器
    private fun initoptionPickerView(type: String) {
        optionPickerView = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            if (type.equals("行业类型")) {//EnterpriseTypeEnum
                et_act_qygl_detail_qylx.text = getHylx()[options1]
                qylx = IndustryEnum.getIndex(getHylx()[options1])
                page = 1
                mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc,jyzt)
//                et_act_qygl_detail_qylx.setTextColor(Color.parseColor("#4CA2FE"))

            }else if (type.equals("经营状态")) {
                et_act_qygl_detail_qzcsj.text = getJyzt()[options1]
                jyzt = JyztEnum.getIndex(getJyzt()[options1])
                page = 1
                mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc,jyzt)
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
        if (type.equals("行业类型")) {
            optionPickerView!!.setPicker(getHylx() as List<Any>?)//一级选择器
//            optionPickerView!!.setSelectOptions(getQylxData().indexOf(textView.text.toString()))
        }else if (type.equals("经营状态")){
            optionPickerView!!.setPicker(getJyzt() as List<Any>?)//一级选择器
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

    /*//企业类型
    fun getQylxData(): ArrayList<String> {
        val sjwtList = ArrayList<String>()
        val values = EnterpriseTypeEnum.values()

        for (i in values) {
            sjwtList.add(i.getName())
        }
        return sjwtList
    }*/
    //企业类型
    private fun getHylx(): List<String> {
        val strings = ArrayList<String>()
        for (i in 0 until IndustryEnum.values().size) {
            strings.add(IndustryEnum.values()[i].getName())
        }
        return strings
    }

    private fun getJyzt(): List<String> {
        val strings = ArrayList<String>()
        for (i in 0 until JyztEnum.values().size) {
            strings.add(JyztEnum.values()[i].getName())
        }
        return strings
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed) {//可见
            //TODO now it's visible to user
            onResume()
        } else if (!isVisibleToUser) {//不可见
            //TODO now it's invisible to user
            onPause()
        }
    }
    fun diaoyong(){
        if (supl_frag_zjdgl1!=null){
            supl_frag_zjdgl1?.setScrollableView(null)
        }
//        cashList.clear()
//        page = 1
//        mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc)
//        mPresenter.getCyyqZzt()
    }
    override fun onResume() {
        super.onResume()
        if (userVisibleHint) {
            /*cashList.clear()
            page = 1
            mPresenter.getQyglList(page, limit, qylx, zcdate, zcdz, qymc)*/
        }

    }
    override fun returnCyyqZzt(cash: CyyqZztEntity) {
        if (cash!=null){
            rlv_frag_zjd_cyyq_hylx.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            val arrayList2 = ArrayList<EnterpriseVoEntity>()
            val enterpriseVoEntity = EnterpriseVoEntity()
            enterpriseVoEntity.garden = "全部"
            if (cash.enterpriseVoEntities.size>1){
                arrayList2.add(enterpriseVoEntity)
            }
            arrayList2.addAll(cash.enterpriseVoEntities)
            var count = 0

            for (i in cash.enterpriseVoEntities){
                    count += i.counts
                if (AppCache.getInstance().code.equals("110115015")){//黄村账号排序
                    if (i.garden.equals("孙村工业园区")){
                        arrayList2.set(1,i)
                    }else if (i.garden.equals("东南工业园区")){
                        arrayList2.set(2,i)
                    }else if (i.garden.equals("芦城工业园区")){
                        arrayList2.set(3,i)
                    }else if (i.garden.equals("园区外")){
                        arrayList2.set(4,i)
                    }
                }
            }

            if (arrayList2.size>0){
                arrayList2.get(0).counts = count
                arrayList2.get(0).isShow = true
                garden =  arrayList2.get(0).garden
                page = 1
                mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc,jyzt)
            }
            rlv_frag_zjd_cyyq_hylx.adapter = object:BaseQuickAdapter<EnterpriseVoEntity,BaseViewHolder>(R.layout.item_zjd_cyyq_hylx,arrayList2){
                override fun convert(helper: BaseViewHolder?, item: EnterpriseVoEntity?) {
                    helper!!.setText(R.id.tv_item_zjd_cyyq_gyyq_tab, item!!.garden)
                            .setText(R.id.tv_item_zjd_cyyq_gyyq_wxf, item!!.counts.toString())
                    val i = helper.adapterPosition % 4
                    val view = helper.getView<RelativeLayout>(R.id.rl_item_zjd_cyyq_gyyq)
                    if (i == 0){
                        view.setBackgroundResource(R.drawable.rjhj_zg_rlt)
                    }else if (i == 1){
                        view.setBackgroundResource(R.drawable.rjhj_xc_rlt)
                    }else if (i == 2){
                        view.setBackgroundResource(R.drawable.rjhj_sh_rlt)
                    }else if (i == 3){
                        view.setBackgroundResource(R.drawable.rjhj_xz_rlt)
                    }
                    if (item.isShow){
                        helper.getView<View>(R.id.view_item_zjd_cyyq_gyyq_tab).visibility = View.VISIBLE
                    }else{
                        helper.getView<View>(R.id.view_item_zjd_cyyq_gyyq_tab).visibility = View.GONE
                    }
                    helper!!.itemView.setOnClickListener {
                        garden = item.garden
                        page = 1
                        mPresenter.getQyglList(page, limit, qylx, garden, zcdate, zcdz, qymc,jyzt)
                        for ( i in 0..arrayList2.size-1){
                            arrayList2.get(i).isShow = false
                            if (i == helper.adapterPosition){
                                arrayList2.get(i).isShow = true
                            }
                        }
                        notifyDataSetChanged()
                    }
                }
            }
            if (arrayList2!=null&&arrayList2.size>0){
                if (bct_frag_cyyq!=null){
                    bct_frag_cyyq.getDescription().setEnabled(false);
                    bct_frag_cyyq.setMaxVisibleValueCount(40);
                    // 扩展现在只能分别在x轴和y轴
                    bct_frag_cyyq.setPinchZoom(false);
                    bct_frag_cyyq.setDrawGridBackground(false);
                    bct_frag_cyyq.setDrawBarShadow(false);
                    bct_frag_cyyq.setDrawValueAboveBar(false);
                    bct_frag_cyyq.setHighlightFullBarEnabled(false);
                    bct_frag_cyyq.setScaleEnabled(false);
                    bct_frag_cyyq.setDoubleTapToZoomEnabled(false);

                    // 改变y标签的位置
                    var leftAxis = bct_frag_cyyq.getAxisLeft()
                    leftAxis.setValueFormatter(object: ValueFormatter(){})
                    leftAxis.setAxisMinimum(0f);
                    bct_frag_cyyq.getAxisRight().setEnabled(false)
                    var xLabels = bct_frag_cyyq.getXAxis()
                    xLabels.setPosition(XAxis.XAxisPosition.BOTTOM)
                    xLabels.setGranularity(1f)
                    xLabels.setLabelCount(arrayList2.size)
//                    xLabels.setLabelRotationAngle(45f)
                    xLabels.valueFormatter = object: ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val index = value.toInt()

                            return if (index < 0 || index >= arrayList2.size) {
                                ""
                            } else {
                                arrayList2.get(index).garden
                            }
//                    return super.getFormattedValue(value)
                        }
                    }
                    /*val mMatrix = Matrix()
                    mMatrix.postScale(0.5f, 1f)
                    bct_frag_cyyq.viewPortHandler.refresh(mMatrix, bct_frag_cyyq, false)*/


                    var l = bct_frag_cyyq.getLegend();
                    l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                    l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                    l.setDrawInside(false);
                    l.setFormSize(8f);
                    l.setFormToTextSpace(4f);
                    l.setXEntrySpace(6f);
                    val arrayList = ArrayList<ArrayList<Float>>()
                    for (i in 0..arrayList2.size-1){//for (i in homeEntities.size-1 downTo 0){
                        val yoyListEntity = arrayList2.get(i)
                        val arrayList1 = ArrayList<Float>()
                        val gy = yoyListEntity!!.gycount
                        val jt = yoyListEntity!!.jtcount
                        arrayList1.add(gy.toFloat())
                        arrayList1.add(jt.toFloat())
                        arrayList.add(arrayList1)
                    }
                    setData(arrayList,arrayList2);//homeEntities
                }
            }
        }
    }
    //初始化
    private fun setData(stringList:ArrayList<ArrayList<Float>>,homeEntityList:List<EnterpriseVoEntity>) {
        var yVals1 = ArrayList<BarEntry>()
        for (i in 0..stringList.size-1){
            val get = stringList.get(i)
            yVals1.add( BarEntry(i.toFloat(), floatArrayOf(get.get(0), get.get(1))))
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

        if (bct_frag_cyyq.getData() != null &&
                bct_frag_cyyq.getData().getDataSetCount() > 0) {
            set1 =  bct_frag_cyyq.getData().getDataSetByIndex(0) as BarDataSet
            set1.setValues(yVals1)
            bct_frag_cyyq.getData().notifyDataChanged()
            bct_frag_cyyq.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")
            set1.setColors(getColors()!!.toList())
            set1.setStackLabels( arrayOf("国有", "集体"))

            var dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            var data = BarData(dataSets);//MyValueFormatter
            data.setValueFormatter(object:ValueFormatter(){})
            data.setValueTextColor(Color.WHITE)

            bct_frag_cyyq.setData(data)
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
                textTemp += "国有:" + get1.get(0).toInt()
                textTemp += "\n"
                textTemp += "集体:" + get1.get(1).toInt()
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(bct_frag_cyyq)
        bct_frag_cyyq.setMarker(markerView)
        bct_frag_cyyq.data.setDrawValues(false)

        bct_frag_cyyq.setFitBars(true);
        bct_frag_cyyq.invalidate();
    }

    private fun getColors(): IntArray? {
        val stacksize = 2
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)

        colors[0] = Color.parseColor("#4CA2FE")
        colors[1] = Color.parseColor("#12C483")
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


}
