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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R
import com.jymj.zhglxt.R.id.*
import com.jymj.zhglxt.R.id.zjdfjgl_frag_xzsj

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.adapter.RightArrowAdapter
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.jymj.zhglxt.util.TimePickerUtil
import com.jymj.zhglxt.widget.RecyclerViewNoBugLinearLayoutManager
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.zjd.activity.FjxcActivity
import com.jymj.zhglxt.zjd.adapter.CunAdapter
import com.jymj.zhglxt.zjd.adapter.FjxcAdapter
import com.jymj.zhglxt.zjd.adapter.MainSpinnerAdapter
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.zjd.contract.FjxcContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.FjxcPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.SnackbarUtils
import com.setsuna.common.commonutils.StringUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_fj.*
import kotlinx.android.synthetic.main.fragment_zjd_xc.*
import org.json.JSONObject
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ZjdXcFragment : BaseFragment<FjxcPresenter, BaseModel>(),  FjxcContract.View, View.OnClickListener {
    private var date ="";//选择时间
    var fjxcList = ArrayList<Renovated>()//翻建列表
    private var bh = 0
    private var zhenCode = ""
    private var cunCode = ""
    private var fjxcPosition = 0
    var fjxcAdapter1: FjxcAdapter? = null
    private var adapterFlag = 0
    private var lastSelect = 1
    private var page = 1
    private var limit = 30
    private var cunAdapter: CunAdapter? =null
    private var sysXzqEntityListQu = ArrayList<SysXzqEntity>()
    private var sysXzqEntityListZhen = ArrayList<SysXzqEntity>()
    private var sysXzqEntityListCun= ArrayList<SysXzqEntity>()
    private var sysXzqEntityList = ArrayList<SysXzqEntity>()

    private val hbjcTitles = arrayListOf<String>("巡查", "整改", "审核", "销账")//, "下发"  , "待审批"  , "待处理"

    var optionPickerView: OptionsPickerView<Any>? = null//条件选择器
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdXcFragment {
            return ZjdXcFragment().apply {
                //                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_xc
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_ydlr_frag_xzsj.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()){
                //自己业务
                if (recy_fjxc_environmental_show.isShown)
                    setTime(fjxc_frag_xzsj)
                if (ll_fjxc_hz.isShown){
                    TimePickerUtil.initTimePickerView(activity,object: TimePickerUtil.OnTimePickerLister{
                        override fun onClick(data: String?) {
                            fjxc_frag_xzsj.setText(data)
                        }
                    })
                }
//                                showDatePicker()
            }
        }

        /*fjxc_frag_xzsj.setOnTouchListener(object:View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event?.getAction()){
                    MotionEvent.ACTION_DOWN->{//按下
                    }
                    MotionEvent.ACTION_UP->{ //抬起
                        if (SingleOnClickUtil1.isFastClick()){
                            //自己业务
                            if (recy_fjxc_environmental_show.isShown)
                                setTime(fjxc_frag_xzsj)
                            if (ll_fjxc_hz.isShown){
                                TimePickerUtil.initTimePickerView(activity,object: TimePickerUtil.OnTimePickerLister{
                                    override fun onClick(data: String?) {
                                        fjxc_frag_xzsj.setText(data)
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
        fjxc_frag_xzsj.setInputType(InputType.TYPE_NULL)
        fjxc_frag_xzsj.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                date = fjxc_frag_xzsj.text.toString()
                if (recy_fjxc_environmental_show.isShown){
                    fjxcList.clear()
                    page = 1
                    mPresenter.getFjxcList(lastSelect,limit,page,cunCode,date, bh)
                    mPresenter.getSteps(lastSelect,limit,page,cunCode,date,bh)
                }
                if (recy_fjxc_environmental_all.isShown){
                    mPresenter.getFanJianZb(date)
                }
            }
        })*/
        fjxc_item_tv.setOnClickListener {
            ll_fjxc_yx.visibility = View.VISIBLE
            ll_fjxc_hz.visibility = View.VISIBLE
            fjxc_item_ll.visibility = View.GONE
            fjxc_frag_xzsj.setText(date)
            tv_frag_zjd_xc_cmsx.text = "村名筛选"
            tv_frag_zjd_xc_cmsx.setTextColor(Color.parseColor("#666666"))
        }

        for (i in 0..hbjcTitles.size - 1) {
            if (((AppCache.getInstance().type==ApiConstants.LDRK_ZZF||
                            AppCache.getInstance().type==ApiConstants.RJHJ_NY)&&(i!=-1))||
                    (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR&&(i==0||i==1))){
                tab_fjxc_map_hbjc1.addTab(tab_fjxc_map_hbjc1.newTab().setText(hbjcTitles[i]))
            }
        }

        /*tab_fjxc_map_hbjc1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                recy_fjxc_environmental_show.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0f, 0f, 0));
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text!!.equals("巡查")){
                    lastSelect = 1
                }else if (tab!!.text!!.equals("整改")){
                    lastSelect = 2
                }else if (tab!!.text!!.equals("待处理")){
                    lastSelect = 3
                }else if (tab!!.text!!.equals("审核")){
                    lastSelect = 4
                }else if (tab!!.text!!.equals("销账")){
                    lastSelect = 5
                }

                limit = 100
                page = 1
                adapterFlag = 0
                fjxcList.clear()
                mPresenter.getFjxcList(lastSelect,limit,page, cunCode,date,bh)//AppCache.getInstance().type.toString(),
                mPresenter.getSteps(lastSelect,limit,page, cunCode,date,bh)//AppCache.getInstance().type.toString(),

            }
        })
        if (AppCache.getInstance().type==ApiConstants.LDRK_ZZF){
//            lastSelect = 2
            lastSelect = 1
            fjxc_ll_xuncha.visibility = View.VISIBLE
            fjxc_ll_zhenggai.visibility = View.VISIBLE
            fjxc_ll_shenhe.visibility = View.VISIBLE
            fjxc_ll_xiaozhang.visibility = View.VISIBLE
            fjxc_ll_xuncha.setOnClickListener {
                tab_fjxc_map_hbjc1.getTabAt(0)!!.select()
                view_frag_zjd_xc_xc_tab.visibility = View.VISIBLE
                view_frag_zjd_xc_zg_tab.visibility = View.GONE
                view_frag_zjd_xc_sh_tab.visibility = View.GONE
                view_frag_zjd_xc_xz_tab.visibility = View.GONE
            }
            fjxc_ll_zhenggai.setOnClickListener {
                tab_fjxc_map_hbjc1.getTabAt(1)!!.select()
                view_frag_zjd_xc_xc_tab.visibility = View.GONE
                view_frag_zjd_xc_zg_tab.visibility = View.VISIBLE
                view_frag_zjd_xc_sh_tab.visibility = View.GONE
                view_frag_zjd_xc_xz_tab.visibility = View.GONE
            }

            fjxc_ll_shenhe.setOnClickListener {
                tab_fjxc_map_hbjc1.getTabAt(2)!!.select()
                view_frag_zjd_xc_xc_tab.visibility = View.GONE
                view_frag_zjd_xc_zg_tab.visibility = View.GONE
                view_frag_zjd_xc_sh_tab.visibility = View.VISIBLE
                view_frag_zjd_xc_xz_tab.visibility = View.GONE
            }
            fjxc_ll_xiaozhang.setOnClickListener {
                tab_fjxc_map_hbjc1.getTabAt(3)!!.select()
                view_frag_zjd_xc_xc_tab.visibility = View.GONE
                view_frag_zjd_xc_zg_tab.visibility = View.GONE
                view_frag_zjd_xc_sh_tab.visibility = View.GONE
                view_frag_zjd_xc_xz_tab.visibility = View.VISIBLE
            }
        }else if (AppCache.getInstance().type==2){
            lastSelect = 3
            fjxc_ll_xuncha.visibility = View.GONE
            fjxc_ll_zhenggai.visibility = View.VISIBLE
            fjxc_ll_shenhe.visibility = View.GONE
            fjxc_ll_xiaozhang.visibility = View.GONE
            fjxc_ll_zhenggai.setOnClickListener {
                tab_fjxc_map_hbjc1.getTabAt(0)!!.select()
            }

        }

        recy_fjxc_environmental_show.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView!!.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rlv_frag_zjd_xc_jt.scrollBy(dx, dy)
                }
            }
        })

        rlv_frag_zjd_xc_jt.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView!!.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    recy_fjxc_environmental_show.scrollBy(dx, dy)
                }
            }

        })*/

    }

    override fun initDatas() {
        getData()
        tv_frag_zjd_xc_cmsx.setOnClickListener { 
            initoptionPickerView("村庄名称")
        }
    }
    fun getData(){

        /*if (supl_frag_zjdgl1!=null){
            supl_frag_zjdgl1?.setScrollableView(null)
        }
        if (recy_fjxc_environmental_show.isShown){
            fjxcList.clear()
            page = 1
            mPresenter.getFjxcList(lastSelect,limit,page,cunCode,date, bh)
            mPresenter.getSteps(lastSelect,limit,page,cunCode,date,bh)
        }
        if (recy_fjxc_environmental_all.isShown){
            mPresenter.getFanJianZb(date)
        }*/
    }
    override fun returnFanJian(fjBean: FjBean) {
        if (fjBean.id!=-1L){
            AppCache.getInstance().isFjList = 1
            val intent = Intent(activity, FjxcActivity::class.java)
            intent.putExtra("id", fjBean.id)
            startActivity(intent)
        }
    }

    override fun returnFjxcList(fjBean: List<Renovated>, qutype: Int, count: Int) {//翻建列表
        if (recy_fjxc_environmental_show!=null){
            if (page == 1){
                fjxcList.clear()
            }
            fjxcList.addAll(fjBean)
            recy_fjxc_environmental_show.layoutManager = RecyclerViewNoBugLinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            recy_fjxc_environmental_show.setHasFixedSize(false)
            recy_fjxc_environmental_show.setFocusable(false)
            if (recy_fjxc_environmental_show.isShown){
                if (adapterFlag==0){
                    fjxcAdapter1 = FjxcAdapter(context, fjxcList, lastSelect)
                    recy_fjxc_environmental_show!!.adapter = fjxcAdapter1
                    adapterFlag = 1
                }
                fjxcAdapter1!!.setOnClickEnvironLister(object : FjxcAdapter.OnClickFjxcLister {
                    override fun onClick(position: Int) {
                        if (SingleOnClickUtil.isFastClick()){
                            this@ZjdXcFragment.fjxcPosition = position
                            AppCache.getInstance().isFjList = 1//FjxcActivity
                            val intent = Intent(activity, FjxcActivity::class.java)
                            intent.putExtra("id", fjxcList.get(position).id)
                            startActivity(intent)
//                val type = AppCache.getInstance().type
                            /*    val intent = Intent(activity, HBJCDetailActivity::class.java)
                                intent.putExtra("pjenvior", enviorListEntity.get(position))
                                intent.putExtra("code", enviorListEntity.get(position).code)//selectCode
                                startActivity(intent)*/
                        }
                    }

                    override fun onDeleteClick(position: Int) {
                        /* var dialog = SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                         dialog?.titleText = "是否删除？"
                         dialog?.confirmText = "确认"
                         dialog?.cancelText = "取消"
                         dialog?.showCancelButton(true)
                         dialog?.showContentText(false)
                         dialog?.show()
                         dialog?.setConfirmClickListener {
                             val encrypt = "[" + enviorListEntity.get(position).id + "]"//{"ids":}
     //                    var sss = "{\"requestData\":\"" + encrypt + "\"}"
                             OkGo.post<String>(ApiConstants.ENVIORSUPVSDELETES).upJson(encrypt).execute(object :
                                     BaseNet<String>() {
                                 override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                     super.onStart(request)
                                     LoadingDialog.showDialogForLoading(activity)
                                 }
                                 override fun onSuccess(response: Response<String>?) {
                                     val cash = response?.body()
                                     if (cash != null) {
     //                                val decrypt = AesEncryptUtils.decrypt(cash)
                                         val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                         if (json.code == 0) {
     //                                    mPresenter.getHbjcList(lastSelect,MapCache.getInstance().user1.user.xzqs.get(sphbjcList!!.getSelectedItemPosition()).code)
                                             if (stringhbjcList!!.get(sphbjcList!!.getSelectedItemPosition()).equals("全部")) {
                                                 mPresenter.getHbjcList(lastSelect, zhenCode)//AppCache.getInstance().type.toString(),
                                             } else {
                                             limit = 100
                                             page = 1
                                             enviorListEntity.clear()
                                             mPresenter.getHbjcList(lastSelect, limit,page,cunCode,hjzzsjCha,date,bh)//AppCache.getInstance().type.toString(),
     //                                    }
                                             ToastUtils.showShort("删除成功")
                                             dialog.dismiss()
                                         } else {
                                             ToastUtils.showShort("删除失败")
                                             dialog.dismiss()
                                         }
                                     } else {
                                         ToastUtils.showShort("删除失败")
                                         dialog.dismiss()
                                     }
                                 }
                                 override fun onFinish() {
                                     super.onFinish()
                                     LoadingDialog.cancelDialogForLoading()
                                 }
                                 override fun onError(response: Response<String>?) {
                                     super.onError(response)
                                     ToastUtils.showShort("删除失败")
                                     dialog.dismiss()
                                 }
                             })//rjhj_sllv
                         }*/

                    }

                })
                recy_fjxc_environmental_show.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager

                        val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                        if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&fjxcList.size==page*limit) {


                            if (fjxcList.size !=0 && fjxcList.size%limit == 0){
                                page++
//                                mPresenter.getFjxcList(lastSelect,limit,page,cunCode,date,bh)
//                                mPresenter.getSteps(lastSelect,limit,page,cunCode,date,bh)

                            }else{
                                ToastUtils.showShort("滑动到底部了")
                            }
                        }

//                            Log.i(TAG, "滑动到底部")
                    }
                })
                if (page!=1){
                    recy_fjxc_environmental_show.scrollToPosition((page-1)*limit)
                }

            }

            var stringList = ArrayList<String>()
            for(i in 0..fjxcList.size-1){
                stringList.add("")
            }
            //enviorListEntity
            //RightArrowAdapter
            rlv_frag_zjd_xc_jt.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            rlv_frag_zjd_xc_jt.adapter = RightArrowAdapter(activity,stringList)

        }



    }

    override fun returnFanJianZb(fjBean: List<StatisticalEntity>) {//翻建统计

       if (recy_fjxc_environmental_all!=null){
           val bhgsCount = ArrayList<Float>()
           val bhgsName = ArrayList<String>()
           if (fjBean!=null&&fjBean.size>0){
               for (i in fjBean){
                   val l = i.amount - i.xzcount - i.xccount
                   if (!i.xzqmc.equals("总计")&&l!=0L){
                       bhgsCount.add(l.toFloat())
                       bhgsName.add(i.xzqmc)
                   }
               }
               /*if (hbjc_fjxc_pct != null) {
                   rkinitPie(hbjc_fjxc_pct, "总数 ${ fjBean.get(0).amount-fjBean.get(0).xzcount-fjBean.get(0).xccount}", bhgsCount, bhgsName)
               }*/
               zjdSpBar(fjBean,bct_frag_zjd_xc)

               recy_fjxc_environmental_all.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
               recy_fjxc_environmental_all.adapter = object: BaseQuickAdapter<StatisticalEntity, BaseViewHolder>(R.layout.item_fjxc_list,fjBean){
                   override fun convert(helper: BaseViewHolder?, item: StatisticalEntity?) {
                       helper?.setText(R.id.tv_fjxc_list_szd, item?.xzqmc)
                               ?.setText(R.id.tv_fjxc_list_szsl, "${item!!.amount}")//-item.xzcount-item.xccount
                               ?.setText(R.id.tv_fjxc_list_xcl, item?.xccount.toString())
                               ?.setText(R.id.tv_fjxc_list_zgl, item?.zgcount.toString())
                               ?.setText(R.id.tv_fjxc_list_dcl, item?.dclcount.toString())
                               ?.setText(R.id.tv_fjxc_list_shl, item?.shcount.toString())
                               ?.setText(R.id.tv_fjxc_list_ywc, item?.xzcount.toString())
                               ?.setText(R.id.tv_fjxc_list_yql, item?.yqcount.toString())
                               ?.setText(R.id.tv_fjxc_list_bhl, item?.counts.toString())

                       helper?.itemView?.setOnClickListener {

                           ll_fjxc_yx.visibility = View.GONE
                           ll_fjxc_hz.visibility = View.GONE
                           fjxc_item_ll.visibility = View.VISIBLE
                           zhenCode = item!!.code
                           cunCode = item!!.code
                           /*if (item.object1.equals("总计")){
                               code = "110112"
                               zhenCode = ""
                           }else{
                               code = item.object5
                               zhenCode = item.object5
                           }*/
                           getDanXzqList(zhenCode,3)
                           fjxc_frag_xzsj.setText(date)
//                        mPresenter.getFjxcList(lastSelect,limit,page,cunCode,date,bh)


                       }
                   }

               }
           }
       }


    }


    val stringList = ArrayList<String>()
    //柱状图
    private fun zjdSpBar(message: List<StatisticalEntity>, barChart: BarChart) {
//        message.clear()
        if (barChart != null&&message.size>0){

            stringList.clear()
            stringList.add("待整改")
            stringList.add("待审核")
            stringList.add("销账")
            stringList.add("逾期")
//            stringList.add("驳回")
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
            leftAxis.valueFormatter = object :ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return super.getFormattedValue(value.toInt().toFloat())
                }
            }
//            leftAxis.setValueFormatter(MonthlyIntegerYValueFormatter())
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
                val yoyListEntity = message.get(0)
                val arrayList1 = ArrayList<Float>()
                if (stringList.get(i).equals("待整改")){
                    arrayList1.add(yoyListEntity.zgcount.toFloat())
                }else if (stringList.get(i).equals("待审核")){
                    arrayList1.add(yoyListEntity.shcount.toFloat())
                }else if (stringList.get(i).equals("销账")){
                    arrayList1.add(yoyListEntity.xzcount.toFloat())
                }else if (stringList.get(i).equals("逾期")){
                    arrayList1.add(yoyListEntity.yqcount.toFloat())
                }else if (stringList.get(i).equals("驳回")){
                    arrayList1.add(yoyListEntity.counts.toFloat())
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
                        return value.toInt().toString()///super.getFormattedValue(value.toInt().toFloat())
                    }

            })
//            data.setValueTextColor(Color.WHITE)

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
        barChart.data.setDrawValues(true)//用于控制柱状图顶部是否显示数字

        barChart.setFitBars(false);
        barChart.invalidate();
    }

    private fun getColorsSP(): IntArray? {
        val stacksize = 5
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#fAF6D0")
        colors[1] = Color.parseColor("#FFB74A")
        colors[2] = Color.parseColor("#6BC4FF")
        colors[3] = Color.parseColor("#F06000")
        colors[4] = Color.parseColor("#FF6000")
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
    }


    override fun returnSteps(fjNumBean: FjNumBean) {
        fjxc_count_xuncha.setText("${fjNumBean.object1}")
        fjxc_count_zhenggai.setText("${fjNumBean.object2}")
        fjxc_count_shenhe.setText("${fjNumBean.object4}")
        fjxc_count_xiaozhang.setText("${fjNumBean.object5}")
    }

    override fun returnEnviorsByxy(fjNumBean: List<FwglJhBean>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal) {
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
                fjxc_frag_xzsj.setText(myyear.toString() + "-0" + (monthOfYear + 1))
            }else{
                //mPresenter.getNewDaily(year.toString() + "-" + (month + 1))
                fjxc_frag_xzsj.setText(myyear.toString() + "-" + (monthOfYear + 1))
            }
            //更新日期
//            updateDate()

        }
    }
    //单独村
    fun getDanXzqList(code:String,type:Int){
        LoadingDialog.showDialogForLoading(activity)

        val httpParams1 = JSONObject()
        httpParams1.put("code",code);
        httpParams1.put("type",type);
        val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).upJson(sss).execute(
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
                            /* sysXzqEntity.center = sysXzqEntityListZhen.get(zhenPosition).center*/
                            sysXzqEntityListCun.add(sysXzqEntity)
                            sysXzqEntityListCun.addAll(data)

                            fjxc_but_cha.setOnClickListener {

                                if (tv_frag_zjd_xc_cmsx.text.toString().equals("全部")||tv_frag_zjd_xc_cmsx.text.toString().equals("村名筛选")) {
                                    limit = 100
                                    page = 1
                                    fjxcList.clear()
                                    cunCode = code

                                    if (sp_fjxc_hbjctj_list_bh.text.toString().equals("")){
                                        bh = 0
                                    }else{
                                        bh = sp_fjxc_hbjctj_list_bh.text.toString().toInt()
                                    }
                                    mPresenter.getFjxcList(lastSelect,limit,page, zhenCode,date,bh)//AppCache.getInstance().type.toString(),
                                    mPresenter.getSteps(lastSelect,limit,page, zhenCode,date,bh)//AppCache.getInstance().type.toString(),
                                    
                                } else {
                                    limit = 100
                                    page = 1
                                    fjxcList.clear()
                                    for (i in sysXzqEntityListCun){
                                        if (tv_frag_zjd_xc_cmsx.text.toString().equals(i.name)){
                                            cunCode = i.code
                                        }
                                    }

                                    if (sp_fjxc_hbjctj_list_bh.text.toString().equals("")){
                                        bh = 0
                                    }else{
                                        bh = sp_fjxc_hbjctj_list_bh.text.toString().toInt()
                                    }
                                    mPresenter.getFjxcList(lastSelect,limit,page, cunCode,date,bh)//AppCache.getInstance().type.toString(),
                                    mPresenter.getSteps(lastSelect,limit,page, cunCode,date,bh)//AppCache.getInstance().type.toString(),
                                    
                                }
                            }
                            sp_fjxc_hbjctj_list_bh.addTextChangedListener(object :TextWatcher{
                                override fun afterTextChanged(p0: Editable?) {
                                }

                                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                }

                                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                    if (sp_fjxc_hbjctj_list_bh.text.toString().equals("")){
                                        bh = 0
                                    }else{
                                        bh = sp_fjxc_hbjctj_list_bh.text.toString().toInt()
                                    }
                                }

                            })

                        }
                    }
                }else{
                    SnackbarUtils.with(rjhj_cl_map_act).setMessage("项目初始化失败！").show()
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
        })

    }


    //初始化选择器
    private fun initoptionPickerView(type: String) {
        var cunList = ArrayList<String>()
        //sysXzqEntityList
        for (i in sysXzqEntityListCun){
            cunList.add(i.name)
        }
        val indexOf = if (cunList.indexOf(type)==null) 0 else cunList.indexOf(type)

        optionPickerView = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
//            if (type.equals("村庄名称")) {
                fjxcList.clear()
                tv_frag_zjd_xc_cmsx.text = sysXzqEntityListCun.get(options1).name
                tv_frag_zjd_xc_cmsx.setTextColor(Color.parseColor("#4CA2FE"))
                page = 1
                cunCode = sysXzqEntityListCun.get(options1).code
                if (tv_frag_zjd_xc_cmsx.text.toString().equals("全部")){
                    limit = 100
                    page = 1
                    if (sp_fjxc_hbjctj_list_bh.text.toString().equals("")){
                        bh = 0
                    }else{
                        bh = sp_fjxc_hbjctj_list_bh.text.toString().toInt()
                    }
                    mPresenter.getFjxcList(lastSelect,limit,page, zhenCode,date,bh)
                    mPresenter.getSteps(lastSelect,limit,page, zhenCode,date,bh)

                }else{
                    limit = 100
                    page = 1
                    if (sp_fjxc_hbjctj_list_bh.text.toString().equals("")){
                        bh = 0
                    }else{
                        bh = sp_fjxc_hbjctj_list_bh.text.toString().toInt()
                    }
                    mPresenter.getFjxcList(lastSelect,limit,page, cunCode,date,bh)
                    mPresenter.getSteps(lastSelect,limit,page, cunCode,date,bh)

                }

//            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(type)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setContentTextSize(20)
                .setSelectOptions(indexOf)
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
    
    //翻建统计图
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

        var zs = 0f
        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(),"", lables[i]))
            zs = zs+datas[i]
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.valueFormatter = object: ValueFormatter(){
            override fun getPieLabel(value: Float, pieEntry: PieEntry?): String {
                val format = DecimalFormat("###.#")
                val format1 = DecimalFormat("###")
                return pieEntry!!.data.toString()+"(${format.format(value/zs*100)}%-${format1.format(value)})"
            }
            /*override fun getFormattedValue(value: Float): String {
                val format = DecimalFormat("###.#")
                val format1 = DecimalFormat("###")
                return format1.format(value)+"(${format.format(value/zs*100)}%)"//super.getFormattedValue(value)
            }*/
        }

        // add a lot of colors

        val colors = java.util.ArrayList<Int>()
        /*colors.add(Color.argb(199, 131, 69, 38))
        colors.add(Color.argb(199, 1, 112, 255))
        colors.add(Color.argb(199, 203, 203, 203))*/
        colors.add(Color.argb(199, 60, 178, 239))
        colors.add(Color.argb(199, 174, 253, 202))
        colors.add(Color.argb(199, 255, 240, 101))
        colors.add(Color.argb(199, 143, 166, 188))
        colors.add(Color.argb(199, 201, 85, 1))
        colors.add(Color.argb(199, 255, 191, 191))

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

        val data = com.github.mikephil.charting.data.PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data

        // undo all highlights
        pie.highlightValues(null)

        pie.invalidate()
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
    override fun onResume() {
        super.onResume()
        if (userVisibleHint){
            if (AppCache.getInstance().isFjList==1){
                AppCache.getInstance().isFjList = 0
                if (AppCache.getInstance().isZjdUpdate == 1){
                    AppCache.getInstance().isZjdUpdate = 0
                    page = 1
                    fjxcList.clear()
//                    mPresenter.getFjxcList(lastSelect,limit,page,cunCode,date,bh)
//                    mPresenter.getSteps(lastSelect,limit,page,cunCode,date,bh)
                    /*if (AppCache.getInstance().type!=4){
                        if (recy_environmental_all.isShown){// recy_environmental_all_zhen.isShown||
                            mPresenter.getHbjcZl(date,code)
                        }else{
                            mPresenter.getHbjcZhen(date,code)

                        }
                    }*/
                    AppCache.getInstance().isUpdateWt = 0
                }else{
                    /*if (fjxcPosition!=-1){
                        recy_fjxc_environmental_show.scrollToPosition(fjxcPosition)
                        fjxcPosition = -1
                    }*/
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }
    fun diaoyong(){
        if (supl_frag_zjdgl1!=null){
            supl_frag_zjdgl1?.setScrollableView(null)
        }
    }
}
