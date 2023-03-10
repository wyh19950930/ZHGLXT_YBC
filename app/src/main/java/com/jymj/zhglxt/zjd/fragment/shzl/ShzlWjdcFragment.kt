package com.jymj.zhglxt.zjd.fragment.shzl

import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
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
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.jymj.zhglxt.zjd.activity.AjlbActivity
import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.fj.ZjdfjTjBean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTj2Bean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTjBean
import com.jymj.zhglxt.zjd.contract.WjdcContract
import com.jymj.zhglxt.zjd.contract.YqglContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.WjdcPresenter
import com.jymj.zhglxt.zjd.presenter.YqglPresenter
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.StringUtils
import com.setsuna.common.commonutils.TimeUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.fragment_zjd_wjdc.*
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
 * (??????????????????  ????????????) ????????????-????????????
 */
class ShzlWjdcFragment : BaseFragment<WjdcPresenter, BaseModel>(), WjdcContract.View{

    companion object {
        /**
         * ?????????????????????????????????????????????????????????
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ShzlWjdcFragment {
            return ShzlWjdcFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }
    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_wjdc
    }
    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1){
            tv_frag_zjd_wjdc_update.visibility = View.VISIBLE
        }else{
            tv_frag_zjd_wjdc_update.visibility = View.GONE
        }
        tv_frag_zjd_wjdc_update.setOnClickListener {
            if (tv_frag_zjd_wjdc_update.text.toString().equals("??????")){
                tv_frag_zjd_wjdc_update.setText("??????")
                tv_frag_zjd_wjdc_isupdate.visibility = View.VISIBLE
                but_frag_zjd_wjdc_save.visibility = View.GONE
                but_frag_zjd_wjdc_tj.visibility = View.GONE
            }else{
                tv_frag_zjd_wjdc_update.setText("??????")
                tv_frag_zjd_wjdc_isupdate.visibility = View.GONE
                but_frag_zjd_wjdc_save.visibility = View.VISIBLE
                but_frag_zjd_wjdc_tj.visibility = View.VISIBLE
            }
        }
        tv_frag_zjd_wjdc_isupdate.setOnClickListener {  }
        but_frag_zjd_wjdc_save.setOnClickListener {
            val arrayList = ArrayList<BcfzyyEntity>()
            /*for (i in zyysList){
                for (f in zyXuanList){
                    if (i.id == f.id){
                        f.remark = i.remark
                    }
                }
            }
            for (i in wljhfzList){
                for (f in wljhXuanList){
                    if (i.id == f.id){
                        f.remark = i.remark
                    }
                }
            }
            for (i in nfmzcList){
                for (f in nxzcXuanList){
                    if (i.id == f.id){
                        f.remark = i.remark
                    }
                }
            }*/
            if (!et_frag_zjd_wjdc_tbr.text.toString().equals("")){
                for (i in zyXuanList.indices){
                    val get = zyXuanList.get(i)
                    val bcfzyyEntity = BcfzyyEntity()
                    bcfzyyEntity.options = get.id.toInt()
                    bcfzyyEntity.type = get.type
                    bcfzyyEntity.sorting = i
                    bcfzyyEntity.area = get.area
                    bcfzyyEntity.remark = get.remark
                    bcfzyyEntity.code = AppCache.getInstance().code
                    bcfzyyEntity.process = 1
                    bcfzyyEntity.zhen = et_frag_zjd_wjdc_xz!!.text.toString()
                    bcfzyyEntity.xzqmc = et_frag_zjd_wjdc_cun!!.text.toString()
                    bcfzyyEntity.cunname = et_frag_zjd_wjdc_tbr.text.toString()
                    bcfzyyEntity.zhenname = et_frag_zjd_wjdc_fhr.text.toString()
                    bcfzyyEntity.years = TimeUtils.getNowString().split("-")[0]+"???"
                    /*if (AppCache.getInstance().cunCode.length==15){
                        bcfzyyEntity.process = 1
                    }else if (AppCache.getInstance().cunCode.length==15){
                        bcfzyyEntity.process = 2
                    }else if (AppCache.getInstance().cunCode.length==15){
                        bcfzyyEntity.process = 3
                    }*/
                    if (idList.size>0){
                        bcfzyyEntity.ids = idList
                    }
                    arrayList.add(bcfzyyEntity)
                }
                for (i in wljhXuanList.indices){
                    val get = wljhXuanList.get(i)
                    val bcfzyyEntity = BcfzyyEntity()
                    bcfzyyEntity.options = get.id.toInt()
                    bcfzyyEntity.type = get.type
                    bcfzyyEntity.sorting = i
                    bcfzyyEntity.area = get.area
                    bcfzyyEntity.remark = get.remark
                    bcfzyyEntity.code = AppCache.getInstance().code
                    bcfzyyEntity.process = 1
                    bcfzyyEntity.zhen = et_frag_zjd_wjdc_xz!!.text.toString()
                    bcfzyyEntity.xzqmc = et_frag_zjd_wjdc_cun!!.text.toString()
                    bcfzyyEntity.cunname = et_frag_zjd_wjdc_tbr.text.toString()
                    bcfzyyEntity.zhenname = et_frag_zjd_wjdc_fhr.text.toString()
                    bcfzyyEntity.years = TimeUtils.getNowString().split("-")[0]+"???"
                    if (idList.size>0){
                        bcfzyyEntity.ids = idList
                    }
                    arrayList.add(bcfzyyEntity)
                }
                for (i in nxzcXuanList.indices){
                    val get = nxzcXuanList.get(i)
                    val bcfzyyEntity = BcfzyyEntity()
                    bcfzyyEntity.options = get.id.toInt()
                    bcfzyyEntity.type = get.type
                    bcfzyyEntity.sorting = i
                    bcfzyyEntity.area = get.area
                    bcfzyyEntity.remark = get.remark
                    bcfzyyEntity.code = AppCache.getInstance().code
                    bcfzyyEntity.process = 1
                    bcfzyyEntity.zhen = et_frag_zjd_wjdc_xz!!.text.toString()
                    bcfzyyEntity.xzqmc = et_frag_zjd_wjdc_cun!!.text.toString()
                    bcfzyyEntity.cunname = et_frag_zjd_wjdc_tbr.text.toString()
                    bcfzyyEntity.zhenname = et_frag_zjd_wjdc_fhr.text.toString()
                    bcfzyyEntity.years = TimeUtils.getNowString().split("-")[0]+"???"
                    if (idList.size>0){
                        bcfzyyEntity.ids = idList
                    }
                    arrayList.add(bcfzyyEntity)
                }
                mPresenter.getBcjcFzyySave(arrayList)
            }else{
                ToastUtils.showShort("??????????????????")
            }
        }
        but_frag_zjd_wjdc_tj.setOnClickListener {
            val arrayList = ArrayList<BcfzyyEntity>()
            /*for (i in zyysList){
                for (f in zyXuanList){
                    if (i.id == f.id){
                        f.remark = i.remark
                    }
                }
            }
            for (i in wljhfzList){
                for (f in wljhXuanList){
                    if (i.id == f.id){
                        f.remark = i.remark
                    }
                }
            }
            for (i in nfmzcList){
                for (f in nxzcXuanList){
                    if (i.id == f.id){
                        f.remark = i.remark
                    }
                }
            }*/

            if (!et_frag_zjd_wjdc_tbr.text.toString().equals("")){
                for (i in zyXuanList.indices){
                    val get = zyXuanList.get(i)
                    val bcfzyyEntity = BcfzyyEntity()
                    bcfzyyEntity.options = get.id.toInt()
                    bcfzyyEntity.type = get.type
                    bcfzyyEntity.sorting = i
                    bcfzyyEntity.area = get.area
                    bcfzyyEntity.remark = get.remark
                    bcfzyyEntity.code = AppCache.getInstance().code
                    bcfzyyEntity.process = 4
                    bcfzyyEntity.zhen = et_frag_zjd_wjdc_xz!!.text.toString()
                    bcfzyyEntity.xzqmc = et_frag_zjd_wjdc_cun!!.text.toString()
                    bcfzyyEntity.cunname = et_frag_zjd_wjdc_tbr.text.toString()
                    bcfzyyEntity.zhenname = et_frag_zjd_wjdc_fhr.text.toString()
                    bcfzyyEntity.years = TimeUtils.getNowString().split("-")[0]+"???"
                    /*if (AppCache.getInstance().cunCode.length==15){
                        bcfzyyEntity.process = 1
                    }else if (AppCache.getInstance().cunCode.length==15){
                        bcfzyyEntity.process = 2
                    }else if (AppCache.getInstance().cunCode.length==15){
                        bcfzyyEntity.process = 3
                    }*/
                    if (idList.size>0){
                        bcfzyyEntity.ids = idList
                    }
                    arrayList.add(bcfzyyEntity)
                }
                for (i in wljhXuanList.indices){
                    val get = wljhXuanList.get(i)
                    val bcfzyyEntity = BcfzyyEntity()
                    bcfzyyEntity.options = get.id.toInt()
                    bcfzyyEntity.type = get.type
                    bcfzyyEntity.sorting = i
                    bcfzyyEntity.area = get.area
                    bcfzyyEntity.remark = get.remark
                    bcfzyyEntity.code = AppCache.getInstance().code
                    bcfzyyEntity.process = 4
                    bcfzyyEntity.zhen = et_frag_zjd_wjdc_xz!!.text.toString()
                    bcfzyyEntity.xzqmc = et_frag_zjd_wjdc_cun!!.text.toString()
                    bcfzyyEntity.cunname = et_frag_zjd_wjdc_tbr.text.toString()
                    bcfzyyEntity.zhenname = et_frag_zjd_wjdc_fhr.text.toString()
                    bcfzyyEntity.years = TimeUtils.getNowString().split("-")[0]+"???"
                    if (idList.size>0){
                        bcfzyyEntity.ids = idList
                    }
                    arrayList.add(bcfzyyEntity)
                }
                for (i in nxzcXuanList.indices){
                    val get = nxzcXuanList.get(i)
                    val bcfzyyEntity = BcfzyyEntity()
                    bcfzyyEntity.options = get.id.toInt()
                    bcfzyyEntity.type = get.type
                    bcfzyyEntity.sorting = i
                    bcfzyyEntity.area = get.area
                    bcfzyyEntity.remark = get.remark
                    bcfzyyEntity.code = AppCache.getInstance().code
                    bcfzyyEntity.process = 4
                    bcfzyyEntity.zhen = et_frag_zjd_wjdc_xz!!.text.toString()
                    bcfzyyEntity.xzqmc = et_frag_zjd_wjdc_cun!!.text.toString()
                    bcfzyyEntity.cunname = et_frag_zjd_wjdc_tbr.text.toString()
                    bcfzyyEntity.zhenname = et_frag_zjd_wjdc_fhr.text.toString()
                    bcfzyyEntity.years = TimeUtils.getNowString().split("-")[0]+"???"
                    if (idList.size>0){
                        bcfzyyEntity.ids = idList
                    }
                    arrayList.add(bcfzyyEntity)
                }
                mPresenter.getBcjcFzyySave(arrayList)
            }else{
                ToastUtils.showShort("??????????????????")
            }
        }
    }

    override fun initDatas() {
        mPresenter.getBcjcGetOptions()
        mPresenter.getBcjcYears()

    }

    fun diaoyong(){
        if (supl_frag_zjdgl1!=null&&slv_frag_zjd_wjdc!=null){
            supl_frag_zjdgl1?.setScrollableView(slv_frag_zjd_wjdc)
            tv_frag_zjd_wjdc.setText(year+"???")
            if (AppCache.getInstance().type==4){
                et_frag_zjd_wjdc_xz.setText(AppCache.getInstance().xzqZhenName)
                et_frag_zjd_wjdc_cun.setText(AppCache.getInstance().xzqName)
            }
            mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,year)
        }
    }
    var zyysList = ArrayList<OptionsEntity>()//????????????
    var wljhfzList = ArrayList<OptionsEntity>()//??????????????????
    var nfmzcList = ArrayList<OptionsEntity>()//?????????????????????
    var zyXuanList = ArrayList<OptionsEntity>()
    var wljhXuanList = ArrayList<OptionsEntity>()
    var nxzcXuanList = ArrayList<OptionsEntity>()
    var zyysAdapter: BaseQuickAdapter<OptionsEntity, BaseViewHolder>? = null
    var wljhfzAdapter:BaseQuickAdapter<OptionsEntity, BaseViewHolder>? = null
    var nfmzcAdapter:BaseQuickAdapter<OptionsEntity, BaseViewHolder>? = null
    override fun returnBcjcGetOptions(msg: List<OptionsEntity>) {
        for (i in msg){
            if (i.type==1){
                zyysList.add(i)
            }else if (i.type==2){
                wljhfzList.add(i)
            }else if (i.type==3){
                nfmzcList.add(i)
            }
        }
        rlv_frag_zjd_wjdc_zyys.layoutManager = object:GridLayoutManager(activity,2){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rlv_frag_zjd_wjdc_zyys.isFocusable = false
        zyysAdapter = object : BaseQuickAdapter<OptionsEntity, BaseViewHolder>(R.layout.item_shzl_wjdc_xuan, zyysList) {
            override fun convert(helper: BaseViewHolder?, item: OptionsEntity?) {
                helper!!.setIsRecyclable(false)
                if (item!!.options.equals("A ????????????????????????")) {
                    helper!!.getView<LinearLayout>(R.id.ll_item_wjdc_xuan_remark).visibility = View.VISIBLE
                }
                if (helper!!.adapterPosition == zyysList.size - 1) {
                    helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_qt).visibility = View.VISIBLE
                }
                helper.getView<CheckBox>(R.id.tv_item_wjdc_xuan_name).isChecked = item.isCheck
                helper.setText(R.id.tv_item_wjdc_xuan_name, item.options)
                val wjdc_xuan_qt = helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_qt)
                wjdc_xuan_qt.setText(item.remark)
                wjdc_xuan_qt.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!wjdc_xuan_qt.text.toString().equals("")) {
                            item.remark = wjdc_xuan_qt.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                var view = helper.getView<CheckBox>(R.id.tv_item_wjdc_xuan_name)

                view.setOnClickListener {
                    if (zyXuanList.size < 3 || item.isCheck) {
                        item.isCheck = view.isChecked
                        if (view.isChecked) {
                            zyXuanList.add(item)

                        } else {
                            removeList(zyXuanList, item)
                        }
                        var string = ""
                        for (i in zyXuanList) {
                            string = string + i.options.split(" ")[0] + "???"
                        }
                        if (!string.equals("")) {
                            ev_frag_zjd_wjdc_zyzy.setText("1.?????????????????????????????????????????????????????????:" + string.substring(0, string.length - 1))
                        } else {
                            ev_frag_zjd_wjdc_zyzy.setText("1.?????????????????????????????????????????????????????????:")
                        }

                    } else {
                        ToastUtils.showShort("??????????????????")
                        view.isChecked = false
                    }

                }
            }
        }
        rlv_frag_zjd_wjdc_zyys.adapter = zyysAdapter
        rlv_frag_zjd_wjdc_wljhfz.layoutManager = object:GridLayoutManager(activity,2){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rlv_frag_zjd_wjdc_wljhfz.isFocusable = false
        wljhfzAdapter = object : BaseQuickAdapter<OptionsEntity, BaseViewHolder>(R.layout.item_shzl_wjdc_xuan, wljhfzList) {
            override fun convert(helper: BaseViewHolder?, item: OptionsEntity?) {
                helper!!.setIsRecyclable(false)
                if (item!!.options.equals("A ????????????????????????")) {
                    helper!!.getView<LinearLayout>(R.id.ll_item_wjdc_xuan_remark).visibility = View.VISIBLE
                }
                if (helper!!.adapterPosition == wljhfzList.size - 1) {
                    helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_qt).visibility = View.VISIBLE
                }
                helper.getView<CheckBox>(R.id.tv_item_wjdc_xuan_name).isChecked = item.isCheck
                helper.setText(R.id.tv_item_wjdc_xuan_name, item.options)
                val wjdc_xuan_qt = helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_qt)
                wjdc_xuan_qt.setText(item.remark)
                wjdc_xuan_qt.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!wjdc_xuan_qt.text.toString().equals("")) {
                            item.remark = wjdc_xuan_qt.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                var view = helper.getView<CheckBox>(R.id.tv_item_wjdc_xuan_name)

                view.setOnClickListener {
                    item.isCheck = view.isChecked
                    if (view.isChecked) {
                        wljhXuanList.add(item)

                    } else {
                        removeList(wljhXuanList, item)
                    }
                    var string = ""
                    for (i in wljhXuanList) {
                        string = string + i.options.split(" ")[0] + "???"
                    }
                    if (string.contains("A")) {
                        ll_frag_zjd_wjdc_cyfz.visibility = View.GONE
                    } else {
                        ll_frag_zjd_wjdc_cyfz.visibility = View.VISIBLE
                    }
                    if (!string.equals("")) {
                        tv_frag_zjd_wjdc_wljhfz.setText("2.????????????????????????????????????:" + string.substring(0, string.length - 1))
                    } else {
                        tv_frag_zjd_wjdc_wljhfz.setText("2.????????????????????????????????????:")
                    }

                }
            }
        }
        rlv_frag_zjd_wjdc_wljhfz.adapter = wljhfzAdapter
        rlv_frag_zjd_wjdc_nfmzc.layoutManager = object:GridLayoutManager(activity,1){
            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        rlv_frag_zjd_wjdc_nfmzc.isFocusable = false
        nfmzcAdapter = object : BaseQuickAdapter<OptionsEntity, BaseViewHolder>(R.layout.item_shzl_wjdc_xuan, nfmzcList) {
            override fun convert(helper: BaseViewHolder?, item: OptionsEntity?) {
                helper!!.setIsRecyclable(false)
                if (item!!.options.contains("????????????????????????")) {
                    helper!!.getView<LinearLayout>(R.id.ll_item_wjdc_xuan_remark).visibility = View.VISIBLE
                }
                val wjdc_xuan_qt = helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_qt)
                val wjdc_xuan_area = helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_area)
                val wjdc_xuan_remark = helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_remark)
                if (helper!!.adapterPosition == nfmzcList.size - 1) {
                    helper!!.getView<EditText>(R.id.et_item_wjdc_xuan_qt).visibility = View.VISIBLE
                }
                wjdc_xuan_qt.setText(item.remark)
                wjdc_xuan_area.setText(item.area.toString())
                wjdc_xuan_remark.setText(item.remark)
                helper.getView<CheckBox>(R.id.tv_item_wjdc_xuan_name).isChecked = item.isCheck
                helper.setText(R.id.tv_item_wjdc_xuan_name, item.options)
                wjdc_xuan_qt.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!wjdc_xuan_qt.text.toString().equals("")) {
                            item.remark = wjdc_xuan_qt.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                wjdc_xuan_area.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!wjdc_xuan_area.text.toString().equals("")) {
                            item.area = TdlyUtils.getSum(wjdc_xuan_area)
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                wjdc_xuan_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!wjdc_xuan_remark.text.toString().equals("")) {
                            item.remark = wjdc_xuan_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                var view = helper.getView<CheckBox>(R.id.tv_item_wjdc_xuan_name)

                view.setOnClickListener {
                    if (nxzcXuanList.size < 3 || item.isCheck) {
                        item.isCheck = view.isChecked
                        if (view.isChecked) {
                            nxzcXuanList.add(item)

                        } else {
                            removeList(nxzcXuanList, item)
                        }
                        var string = ""
                        for (i in nxzcXuanList) {
                            string = string + i.options.split(" ")[0] + "???"
                        }
                        if (!string.equals("")) {
                            tv_frag_zjd_wjdc_nfmzc.setText("3.???????????????????????????????????????????????????:" + string.substring(0, string.length - 1))
                        } else {
                            tv_frag_zjd_wjdc_nfmzc.setText("3.???????????????????????????????????????????????????:")
                        }

                    } else {
                        ToastUtils.showShort("??????????????????")
                        view.isChecked = false
                    }

                }


            }
        }
        rlv_frag_zjd_wjdc_nfmzc.adapter = nfmzcAdapter

//        year = TimeUtils.getNowString().split("-")[0]
//        tv_frag_zjd_wjdc.setText(year+"???")
//        mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,year)

    }
    private fun removeList(list:ArrayList<OptionsEntity>,optionsEntity: OptionsEntity){
        var index = -1
        for (i in list.indices){
            val get = list.get(i)
            if (get.id == optionsEntity.id){
                index = i
            }
        }
        if (index!=-1){
            list.removeAt(index)
        }
    }
    var year = ""
    override fun onBcjcYears(tdlyDetail: List<String>) {

        if (tdlyDetail.size>0){
            year = tdlyDetail.get(0).replace("???","")
            tv_frag_zjd_wjdc.setText(year+"???")
//            year = TimeUtils.getNowString().split("-")[0]
            mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,year)
        }
        tv_frag_zjd_wjdc.setOnClickListener {
            val arrayList = ArrayList<String>()
            arrayList.addAll(tdlyDetail)


                //???????????????
                val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                    //                        setCheck(quList,options1)

                    tv_frag_zjd_wjdc.setText(arrayList.get(options1))
                    year = arrayList.get(options1).replace("???","")
                    mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,year)


//                        getZhenList(xzqList,quList.get(options1).code)
                })
                        .isDialog(true)
                        .isAlphaGradient(true)
                        .build<String>()
                pvOptions.setSelectOptions(arrayList.indexOf(tv_frag_zjd_wjdc.value.toString()))
                pvOptions.setPicker(arrayList)

                val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                params.leftMargin = 0
                params.rightMargin = 0
                val contentContainer = pvOptions.getDialogContainerLayout()
                contentContainer.setLayoutParams(params)
                pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                pvOptions.show()

            /*PopuPointUtils.initPopuSelectYear(activity,ll_frag_zjd_wjdc,arrayList,object: PopuPointUtils.OnSelectYearLinster{
                override fun onClick(years: String) {
                    tv_frag_zjd_wjdc.setText(years)
                    year = years.replace("???","")
                    mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,year)
                }
            })*/
        }
    }
    //????????????
    override fun onBcjcFzyySave(tdlyDetail: String) {
        mPresenter.getBcjcCjtjjzyzcqk(AppCache.getInstance().code,year)
    }

    var idList = ArrayList<Long>()

    override fun returnBcjcCjtjjzyzcqk(msg: List<BcfzyyEntity>) {

        tv_frag_zjd_wjdc_update.setText("??????")
        tv_frag_zjd_wjdc_isupdate.visibility = View.VISIBLE
        but_frag_zjd_wjdc_save.visibility = View.GONE
        but_frag_zjd_wjdc_tj.visibility = View.GONE

        idList.clear()
        zyXuanList.clear()
        wljhXuanList.clear()
        nxzcXuanList.clear()
        /*if (msg.size>0){
            tv_frag_zjd_wjdc_update.visibility = View.VISIBLE
        }else{
            tv_frag_zjd_wjdc_update.visibility = View.GONE
        }*/

        for (i in zyysList) {
            i.isCheck = false
            i.area = BigDecimal.ZERO
            i.remark = ""
            for (f in msg) {
                if (i.id.toInt() == f.options) {
                    idList.add(f.id)
                    i.area = f.area
                    i.remark = f.remark
                    i.isCheck = true
                    zyXuanList.add(i)
                }
            }
        }
        for (i in wljhfzList) {
            i.isCheck = false
            i.area = BigDecimal.ZERO
            i.remark = ""
            for (f in msg) {
                if (i.id.toInt() == f.options) {
                    idList.add(f.id)
                    i.area = f.area
                    i.remark = f.remark
                    i.isCheck = true
                    wljhXuanList.add(i)
                }
            }
        }
        for (i in nfmzcList) {
            i.isCheck = false
            i.area = BigDecimal.ZERO
            i.remark = ""
            for (f in msg) {
                if (i.id.toInt() == f.options) {
                    idList.add(f.id)
                    i.area = f.area
                    i.remark = f.remark
                    i.isCheck = true
                    nxzcXuanList.add(i)
                }
            }
        }
        if (msg.size>0){
            val get = msg.get(0)

            /*if (AppCache.getInstance().type==4||(AppCache.getInstance().type!=4&&get.process==4)) {
                for (i in zyysList) {
                    i.isCheck = false
                    i.area = BigDecimal.ZERO
                    i.remark = ""
                    for (f in msg) {
                        if (i.id.toInt() == f.options) {
                            idList.add(f.id)
                            i.area = f.area
                            i.remark = f.remark
                            i.isCheck = true
                            zyXuanList.add(i)
                        }
                    }
                }
                for (i in wljhfzList) {
                    i.isCheck = false
                    i.area = BigDecimal.ZERO
                    i.remark = ""
                    for (f in msg) {
                        if (i.id.toInt() == f.options) {
                            idList.add(f.id)
                            i.area = f.area
                            i.remark = f.remark
                            i.isCheck = true
                            wljhXuanList.add(i)
                        }
                    }
                }
                for (i in nfmzcList) {
                    i.isCheck = false
                    i.area = BigDecimal.ZERO
                    i.remark = ""
                    for (f in msg) {
                        if (i.id.toInt() == f.options) {
                            idList.add(f.id)
                            i.area = f.area
                            i.remark = f.remark
                            i.isCheck = true
                            nxzcXuanList.add(i)
                        }
                    }
                }
            }*/

            if (!get.zhen.equals(""))
            et_frag_zjd_wjdc_xz.setText(get.zhen)
            if (!get.xzqmc.equals(""))
            et_frag_zjd_wjdc_cun.setText(get.xzqmc)
            et_frag_zjd_wjdc_tbr.setText(get.cunname)
            et_frag_zjd_wjdc_fhr.setText(get.zhenname)
            if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1){
                if (get.process==4){
                    tv_frag_zjd_wjdc_update.visibility = View.GONE
                    but_frag_zjd_wjdc_save.visibility = View.GONE
                    but_frag_zjd_wjdc_tj.visibility = View.GONE
                    tv_frag_zjd_wjdc_isupdate.visibility = View.VISIBLE
                }else{
                    tv_frag_zjd_wjdc_update.visibility = View.VISIBLE
                }
            }else{
                tv_frag_zjd_wjdc_update.visibility = View.GONE
                but_frag_zjd_wjdc_save.visibility = View.GONE
                but_frag_zjd_wjdc_tj.visibility = View.GONE
                tv_frag_zjd_wjdc_isupdate.visibility = View.VISIBLE
            }
        }else{
            if (AppCache.getInstance().type!=4){
                tv_frag_zjd_wjdc_update.visibility = View.GONE
                but_frag_zjd_wjdc_save.visibility = View.GONE
                but_frag_zjd_wjdc_tj.visibility = View.GONE
                tv_frag_zjd_wjdc_isupdate.visibility = View.VISIBLE
                et_frag_zjd_wjdc_xz.setText("")
                et_frag_zjd_wjdc_cun.setText("")
            }
            et_frag_zjd_wjdc_tbr.setText("")
            et_frag_zjd_wjdc_fhr.setText("")
        }

        var string1 = ""
        for (i in zyXuanList){
            string1 = string1+i.options.split(" ")[0]+"???"
        }
        if (!string1.equals("")){
            ev_frag_zjd_wjdc_zyzy.setText("1.?????????????????????????????????????????????????????????:"+string1.substring(0,string1.length-1))
        }else{
            ev_frag_zjd_wjdc_zyzy.setText("1.?????????????????????????????????????????????????????????:")
        }
        var string = ""
        for (i in wljhXuanList){
            string = string+i.options.split(" ")[0]+"???"
        }
        if (!string.equals("")){
            tv_frag_zjd_wjdc_wljhfz.setText("2.????????????????????????????????????:"+string.substring(0,string.length-1))
        }else{
            tv_frag_zjd_wjdc_wljhfz.setText("2.????????????????????????????????????:")
        }
        if (string.contains("A")){
            ll_frag_zjd_wjdc_cyfz.visibility = View.GONE
        }else{
            ll_frag_zjd_wjdc_cyfz.visibility = View.VISIBLE
        }
        var string3 = ""
        for (i in nxzcXuanList){
            string3 = string3+i.options.split(" ")[0]+"???"
        }
        if (!string3.equals("")){
            tv_frag_zjd_wjdc_nfmzc.setText("3.???????????????????????????????????????????????????:"+string3.substring(0,string3.length-1))
        }else{
            tv_frag_zjd_wjdc_nfmzc.setText("3.???????????????????????????????????????????????????:")
        }
        zyysAdapter?.setNewData(zyysList)
        zyysAdapter?.notifyDataSetChanged()
        wljhfzAdapter?.setNewData(wljhfzList)
        wljhfzAdapter?.notifyDataSetChanged()
        nfmzcAdapter?.setNewData(nfmzcList)
        nfmzcAdapter?.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()

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
