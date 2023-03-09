package com.jymj.zhglxt.zjd.fragment.tx

import android.graphics.Color
import android.text.Editable
import android.text.Html
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.tabs.TabLayout
import com.jymj.basemessagesystem.ui.adapter.GhDetailAdapter
import com.jymj.basemessagesystem.ui.adapter.QsDetailAdapter
import com.jymj.basemessagesystem.ui.adapter.SktDetailAdapter
import com.jymj.basemessagesystem.ui.adapter.TxDetailAdapter

import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.EnableLinearLayout
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.zjd.bean.ZhuShiEnum
import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.bean.tdxg.GhVO
import com.jymj.zhglxt.zjd.bean.tdxg.QsVO
import com.jymj.zhglxt.zjd.bean.tdxg.TdlyVO
import com.jymj.zhglxt.zjd.bean.tdxg.YztSktVO
import com.jymj.zhglxt.zjd.contract.TdlyContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.TdlyPresenter
import com.loc.bc
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fragment_zjd_tdly.*
import java.math.BigDecimal
import java.math.RoundingMode

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ZjdTdlyFragment : BaseFragment<TdlyPresenter, BaseModel>(), TdlyContract.View{

    private var onZjdTdlyLister: OnZjdTdlyLister? = null
    var linearLayoutManager: LinearLayoutManager? =null
    var flowInfoList = ArrayList<TdlyVO>()
    var sktflowInfoList = ArrayList<YztSktVO>()
    var qsflowInfoList = ArrayList<QsVO>()
    var ghflowInfoList = ArrayList<GhVO>()
    var year = ""
    var points = ""
    var limit = 20
    var page = 1

    private var yearUpPopu: CommenPop? = null
    private var addZhengdqkUpPopu: CommenPop? = null
    private var addZhandqkUpPopu: CommenPop? = null
    var mUploadPopu:CommenPop? = null
    var bczdqkEntitys = ArrayList<BczzdqkEntity>()
    private var process = 1//土地利用表的步骤

    var isQq = 0 //判断是否是第一次请求数据
    var split=""//当前年
    var sptypeList = ArrayList<String>()

    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdTdlyFragment {
            return ZjdTdlyFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_tdly
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        /*if (AppCache.getInstance().type==1){
            tv_frag_tdly_xzsj.visibility = View.GONE
        }*/
        val dateAfter = TimeUtil.getDateAfter(0)
//        split = dateAfter.split("-")[0]
//        year = split+"年"
//        tv_frag_tdly_xzsj.setText(year)
        ll1_frag_zjd_tdly.setOnClickListener {
            ll_frag_zjd_tdly_pie.visibility = View.VISIBLE
            ll_frag_zjd_tdly_list.visibility = View.GONE
            v1_frag_zjd_tdly.visibility = View.VISIBLE
            v2_frag_zjd_tdly.visibility = View.GONE
        }
        ll2_frag_zjd_tdly.setOnClickListener {
            ll_frag_zjd_tdly_pie.visibility = View.GONE
            ll_frag_zjd_tdly_list.visibility = View.VISIBLE
            v1_frag_zjd_tdly.visibility = View.GONE
            v2_frag_zjd_tdly.visibility = View.VISIBLE
        }
        //这些空的点击事件是控制是否可以修改的
        tv_frag_zjd_tdly_zgnyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zggd.setOnClickListener {

        }
        tv_frag_tdly_zgyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgld.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgssnyyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgqtnyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgjsyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgjzyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgggglfwyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgjtjyjsyd.setOnClickListener {

        }
        tv_frag_zjd_tdly_zgtdzmj.setOnClickListener {

        }

        ll_frag_zjd_ydlx_gd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_gd.text.toString(),tv_frag_zjd_ydlx_gd)
        }
        ll_frag_zjd_ydlx_yd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_yd.text.toString(),tv_frag_zjd_ydlx_yd)
        }
        ll_frag_zjd_ydlx_ld.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_ld.text.toString(),tv_frag_zjd_ydlx_ld)
        }
        tv_frag_zjd_ydlx_ssnyyd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_ssnyyd.text.toString(),tv_frag_zjd_ydlx_ssnyyd)
        }
        tv_frag_zjd_ydlx_qtnyd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_qtnyd.text.toString(),tv_frag_zjd_ydlx_qtnyd)
        }
        ll_frag_zjd_ydlx_jzyd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_jzyd.text.toString(),tv_frag_zjd_ydlx_jzyd)
        }
        tv_frag_zjd_ydlx_ggglfwyd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_ggglfwyd.text.toString(),tv_frag_zjd_ydlx_ggglfwyd)
        }
        tv_frag_zjd_ydlx_jtjyyd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_ydlx_jtjyyd.text.toString(),tv_frag_zjd_ydlx_jtjyyd)
        }

        et_frag_tdly_nyd_remarkqqqd.addTextChangedListener(listener(et_frag_tdly_nyd_remarkqqqd))
        et_frag_tdly_nyd_remarkqqql.addTextChangedListener(listener(et_frag_tdly_nyd_remarkqqql))
        et_frag_tdly_nyd_remarkqqqg.addTextChangedListener(listener(et_frag_tdly_nyd_remarkqqqg))
        et_frag_tdly_gd_zdmj.addTextChangedListener(listener(et_frag_tdly_gd_zdmj))//计算合计
        et_frag_tdly_yd_zdmj.addTextChangedListener(listener(et_frag_tdly_yd_zdmj))//计算合计
        et_frag_tdly_ld_zdmj.addTextChangedListener(listener(et_frag_tdly_ld_zdmj))//计算合计
        et_frag_tdly_nyssjs_zdmj.addTextChangedListener(listener(et_frag_tdly_nyssjs_zdmj))//计算合计
        et_frag_tdly_qtnyd_zdmj.addTextChangedListener(listener(et_frag_tdly_qtnyd_zdmj))//计算合计

        et_frag_tdly_jzyd_zdmj.addTextChangedListener(listener(et_frag_tdly_jzyd_zdmj))//计算合计
        et_frag_tdly_gggl_zdmj.addTextChangedListener(listener(et_frag_tdly_gggl_zdmj))//计算合计
        et_frag_tdly_jtjyjs_zdmj.addTextChangedListener(listener(et_frag_tdly_jtjyjs_zdmj))//计算合计

        et_frag_tdly_gd_syqkzz.addTextChangedListener(listener(et_frag_tdly_gd_syqkzz))
        et_frag_tdly_gd_syqklz.addTextChangedListener(listener(et_frag_tdly_gd_syqklz))
        et_frag_tdly_gd_syqkqt.addTextChangedListener(listener(et_frag_tdly_gd_syqkqt))
        et_frag_tdly_gd_pjlz.addTextChangedListener(listener(et_frag_tdly_gd_pjlz))
        et_frag_tdly_gd_remark.addTextChangedListener(listener(et_frag_tdly_gd_remark))
        et_frag_tdly_yd_syqkzz.addTextChangedListener(listener(et_frag_tdly_yd_syqkzz))
        et_frag_tdly_yd_syqklz.addTextChangedListener(listener(et_frag_tdly_yd_syqklz))
        et_frag_tdly_yd_syqkqt.addTextChangedListener(listener(et_frag_tdly_yd_syqkqt))
        et_frag_tdly_yd_pjlz.addTextChangedListener(listener(et_frag_tdly_yd_pjlz))
        et_frag_tdly_yd_remark.addTextChangedListener(listener(et_frag_tdly_yd_remark))
        et_frag_tdly_ld_syqkwjy.addTextChangedListener(listener(et_frag_tdly_ld_syqkwjy))
        et_frag_tdly_ld_syqklxzyz.addTextChangedListener(listener(et_frag_tdly_ld_syqklxzyz))
        et_frag_tdly_ld_pjlz.addTextChangedListener(listener(et_frag_tdly_ld_pjlz))
        et_frag_tdly_ld_remark.addTextChangedListener(listener(et_frag_tdly_ld_remark))
//        et_frag_tdly_nyssjs_syqk.addTextChangedListener(listener(et_frag_tdly_nyssjs_syqk))
//        et_frag_tdly_nyssjs_pjlz.addTextChangedListener(listener(et_frag_tdly_nyssjs_pjlz))
        et_frag_tdly_nyssjs_remark.addTextChangedListener(listener(et_frag_tdly_nyssjs_remark))
//        et_frag_tdly_qtnyd_syqk.addTextChangedListener(listener(et_frag_tdly_qtnyd_syqk))
//        et_frag_tdly_qtnyd_pjlz.addTextChangedListener(listener(et_frag_tdly_qtnyd_pjlz))
        et_frag_tdly_qtnyd_remark.addTextChangedListener(listener(et_frag_tdly_qtnyd_remark))
//        et_frag_tdly_jsyd_syqk.addTextChangedListener(listener(et_frag_tdly_jsyd_syqk))
//        et_frag_tdly_jsyd_qjlz.addTextChangedListener(listener(et_frag_tdly_jsyd_qjlz))
        et_frag_tdly_jsyd_remarkghpf.addTextChangedListener(listener(et_frag_tdly_jsyd_remarkghpf))
        et_frag_tdly_jzyd_syqkzz.addTextChangedListener(listener(et_frag_tdly_jzyd_syqkzz))
        et_frag_tdly_jzyd_syqklz.addTextChangedListener(listener(et_frag_tdly_jzyd_syqklz))
        et_frag_tdly_jzyd_syqkqt.addTextChangedListener(listener(et_frag_tdly_jzyd_syqkqt))
        et_frag_tdly_jzyd_pjlz.addTextChangedListener(listener(et_frag_tdly_jzyd_pjlz))
        et_frag_tdly_jzyd_remarkqzgj.addTextChangedListener(listener(et_frag_tdly_jzyd_remarkqzgj))
//        et_frag_tdly_gggl_syqk.addTextChangedListener(listener(et_frag_tdly_gggl_syqk))
        et_frag_tdly_gggl_syqkzy.addTextChangedListener(listener(et_frag_tdly_gggl_syqkzy))
        et_frag_tdly_gggl_syqkcz.addTextChangedListener(listener(et_frag_tdly_gggl_syqkcz))
        et_frag_tdly_gggl_syqkqt.addTextChangedListener(listener(et_frag_tdly_gggl_syqkqt))
        et_frag_tdly_gggl_pjlz.addTextChangedListener(listener(et_frag_tdly_gggl_pjlz))
        et_frag_tdly_gggl_remark.addTextChangedListener(listener(et_frag_tdly_gggl_remark))
//        et_frag_tdly_jtjyjs_syqk.addTextChangedListener(listener(et_frag_tdly_jtjyjs_syqk))
        et_frag_tdly_jtjyjs_syqkzy.addTextChangedListener(listener(et_frag_tdly_jtjyjs_syqkzy))
        et_frag_tdly_jtjyjs_syqkcz.addTextChangedListener(listener(et_frag_tdly_jtjyjs_syqkcz))
        et_frag_tdly_jtjyjs_syqkqt.addTextChangedListener(listener(et_frag_tdly_jtjyjs_syqkqt))
        et_frag_tdly_jtjyjs_pjlz.addTextChangedListener(listener(et_frag_tdly_jtjyjs_pjlz))
        et_frag_tdly_jtjyjs_remark.addTextChangedListener(listener(et_frag_tdly_jtjyjs_remark))
        et_frag_tdly_tdzmj_gyd.addTextChangedListener(listener(et_frag_tdly_tdzmj_gyd))
//        et_frag_tdly_tdzmj_yjjbnt.addTextChangedListener(listener(et_frag_tdly_tdzmj_yjjbnt))
        et_frag_tdly_tdzmj_pyzl.addTextChangedListener(listener(et_frag_tdly_tdzmj_pyzl))
        et_frag_tdly_tdzmj_jtjy.addTextChangedListener(listener(et_frag_tdly_tdzmj_jtjy))
        et_frag_tdly_tdzmj_jtjy1.addTextChangedListener(listener(et_frag_tdly_tdzmj_jtjy1))
        et_frag_tdly_tdzmj_dhcb.addTextChangedListener(listener(et_frag_tdly_tdzmj_dhcb))
        et_frag_tdly_tdzmj_dwzl.addTextChangedListener(listener(et_frag_tdly_tdzmj_dwzl))
        et_frag_tdly_tdzmj_qt.addTextChangedListener(listener(et_frag_tdly_tdzmj_qt))
        et_frag_tdly_tdzmj_zdmj.addTextChangedListener(listener(et_frag_tdly_tdzmj_zdmj))


        mtl_frag_zjd_tdly_ydlx.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_zjd_tdly_ydlx1.isShown){
                        ll_frag_zjd_tdly_ydlx1.visibility = View.GONE
                        mtl_frag_zjd_tdly_ydlx.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_zjd_tdly_ydlx1.visibility = View.VISIBLE
                        mtl_frag_zjd_tdly_ydlx.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        mtl_frag_zjd_tdly_nyjsyd.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_zjd_tdly_nyjsyd.isShown){
                        ll_frag_zjd_tdly_nyjsyd.visibility = View.GONE
                        mtl_frag_zjd_tdly_nyjsyd.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_zjd_tdly_nyjsyd.visibility = View.VISIBLE
                        mtl_frag_zjd_tdly_nyjsyd.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        /*tv_frag_tdly_xzsj.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(activity,tv_frag_tdly_xzsj.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    //时间筛选
                    tv_frag_tdly_xzsj.setText(data)
                }
            })
        }*/

    }

    override fun initDatas() {
        tlt_frag_zjd_tdly_zdqk.addTab(tlt_frag_zjd_tdly_zdqk.newTab().setText("历年征地"))
        tlt_frag_zjd_tdly_zdqk.addTab(tlt_frag_zjd_tdly_zdqk.newTab().setText("历年征地补偿"))

        //注掉 zjdglfragment调用
        tlt_frag_zjd_tdly_zdqk.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab!!.text.toString()!!.equals("历年征地")){
                    if (ll_frag_tdqs_lnzdqk.isShown){
                        ll_frag_tdqs_lnzdqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzdqk.visibility = View.VISIBLE
                    }
                    ll_frag_tdqs_lnzdbcqk.visibility = View.GONE
                }else if (tab!!.text.toString()!!.equals("历年征地补偿")){
                    ll_frag_tdqs_lnzdqk.visibility = View.GONE
                    if (ll_frag_tdqs_lnzdbcqk.isShown){
                        ll_frag_tdqs_lnzdbcqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzdbcqk.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text.toString()!!.equals("历年征地")){
                    if (ll_frag_tdqs_lnzdqk.isShown){
                        ll_frag_tdqs_lnzdqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzdqk.visibility = View.VISIBLE
                    }
                    ll_frag_tdqs_lnzdbcqk.visibility = View.GONE
                }else if (tab!!.text.toString()!!.equals("历年征地补偿")){
                    ll_frag_tdqs_lnzdqk.visibility = View.GONE
                    if (ll_frag_tdqs_lnzdbcqk.isShown){
                        ll_frag_tdqs_lnzdbcqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzdbcqk.visibility = View.VISIBLE
                    }
                }

            }
        })
        ll_frag_zjd_tdly_zdmj.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_tdly_zdmj.text.toString(),tv_frag_zjd_tdly_zdmj)
        }
        ll_frag_zjd_tdqs_gyxzd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_tdqs_gyxzd.text.toString(),tv_frag_zjd_tdqs_gyxzd)
        }
        ll_frag_zjd_tdqs_kfjszd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_tdqs_kfjszd.text.toString(),tv_frag_zjd_tdqs_kfjszd)
        }
        ll_frag_zjd_tdqs_qtzd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_tdqs_qtzd.text.toString(),tv_frag_zjd_tdqs_qtzd)
        }
        ll_frag_zjd_tdly_zdsj.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_zjd_tdly_zdsj.text.toString(),tv_frag_zjd_tdly_zdsj)
        }
        tlt_frag_zjd_tdly_zdqk1.addTab(tlt_frag_zjd_tdly_zdqk1.newTab().setText("历年占地"))
        tlt_frag_zjd_tdly_zdqk1.addTab(tlt_frag_zjd_tdly_zdqk1.newTab().setText("历年占地补偿"))
        tlt_frag_zjd_tdly_zdqk1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab!!.text.toString()!!.equals("历年占地")){
                    if (ll_frag_tdqs_lnzhdqk.isShown){
                        ll_frag_tdqs_lnzhdqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzhdqk.visibility = View.VISIBLE
                    }
                    ll_frag_tdqs_lnzhdbcqk.visibility = View.GONE
                }else if (tab!!.text.toString()!!.equals("历年占地补偿")){
                    ll_frag_tdqs_lnzhdqk.visibility = View.GONE
                    if (ll_frag_tdqs_lnzhdbcqk.isShown){
                        ll_frag_tdqs_lnzhdbcqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzhdbcqk.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text.toString()!!.equals("历年占地")){
                    if (ll_frag_tdqs_lnzhdqk.isShown){
                        ll_frag_tdqs_lnzhdqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzhdqk.visibility = View.VISIBLE
                    }
                    ll_frag_tdqs_lnzhdbcqk.visibility = View.GONE
                }else if (tab!!.text.toString()!!.equals("历年占地补偿")){
                    ll_frag_tdqs_lnzhdqk.visibility = View.GONE
                    if (ll_frag_tdqs_lnzhdbcqk.isShown){
                        ll_frag_tdqs_lnzhdbcqk.visibility = View.GONE
                    }else{
                        ll_frag_tdqs_lnzhdbcqk.visibility = View.VISIBLE
                    }
                }
            }
        })



        /*tv_frag_tdly_tdzzqk.setOnClickListener {
            if (yearUpPopu!=null) {
                CommenPop.backgroundAlpha(0.5f, activity)
                yearUpPopu!!.showAtLocation(slv_frag_zjd_tdly, Gravity.CENTER, 0, 0)
            }

        }*/

        if (AppCache.getInstance().type == 4){
            ll_frag_zjd_tdly_gzjdd.visibility = View.VISIBLE
        }else{
            ll_frag_zjd_tdly_gzjdd.visibility = View.GONE
        }
        
    }
    public fun setOnZjdTdlyLister(onZjdTdlyLister: OnZjdTdlyLister){
        this.onZjdTdlyLister = onZjdTdlyLister
    }
//    var tdly_gd_zdmj = false
    //监听所有信息选项的状态
    private fun listener(view: View): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isQq>0){//判断是否是新请求的数据
                    when (view) {
                        et_frag_tdly_nyd_remarkqqqd -> {//
                            et_frag_tdly_nyd_remarkqqqd.setTextColor(Color.parseColor("#FF8514"))
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_nyd_remarkqqql -> {//
                            et_frag_tdly_nyd_remarkqqql.setTextColor(Color.parseColor("#FF8514"))
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_nyd_remarkqqqg -> {//
                            et_frag_tdly_nyd_remarkqqqg.setTextColor(Color.parseColor("#FF8514"))
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_gd_zdmj -> {//
                            et_frag_tdly_gd_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_nyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_gd_zdmj,et_frag_tdly_yd_zdmj
                                    ,et_frag_tdly_ld_zdmj,et_frag_tdly_nyssjs_zdmj,et_frag_tdly_qtnyd_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_yd_zdmj -> {//
                            et_frag_tdly_yd_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_nyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_gd_zdmj,et_frag_tdly_yd_zdmj
                                    ,et_frag_tdly_ld_zdmj,et_frag_tdly_nyssjs_zdmj,et_frag_tdly_qtnyd_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_ld_zdmj -> {//
                            et_frag_tdly_ld_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_nyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_gd_zdmj,et_frag_tdly_yd_zdmj
                                    ,et_frag_tdly_ld_zdmj,et_frag_tdly_nyssjs_zdmj,et_frag_tdly_qtnyd_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_nyssjs_zdmj -> {//
                            et_frag_tdly_nyssjs_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_nyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_gd_zdmj,et_frag_tdly_yd_zdmj
                                    ,et_frag_tdly_ld_zdmj,et_frag_tdly_nyssjs_zdmj,et_frag_tdly_qtnyd_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_qtnyd_zdmj -> {//
                            et_frag_tdly_qtnyd_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_nyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_gd_zdmj,et_frag_tdly_yd_zdmj
                                    ,et_frag_tdly_ld_zdmj,et_frag_tdly_nyssjs_zdmj,et_frag_tdly_qtnyd_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_jzyd_zdmj -> {//
                            et_frag_tdly_jzyd_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_jsyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_jzyd_zdmj,et_frag_tdly_gggl_zdmj
                                    ,et_frag_tdly_jtjyjs_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_gggl_zdmj -> {//
                            et_frag_tdly_gggl_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_jsyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_jzyd_zdmj,et_frag_tdly_gggl_zdmj
                                    ,et_frag_tdly_jtjyjs_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_jtjyjs_zdmj -> {//
                            et_frag_tdly_jtjyjs_zdmj.setTextColor(Color.parseColor("#FF8514"))
                            et_frag_tdly_jsyd_zdmj.setText(TdlyUtils.getSum(et_frag_tdly_jzyd_zdmj,et_frag_tdly_gggl_zdmj
                                    ,et_frag_tdly_jtjyjs_zdmj).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                        }
                        et_frag_tdly_gd_syqkzz -> {//
                            et_frag_tdly_gd_syqkzz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_gd_syqklz -> {//
                            et_frag_tdly_gd_syqklz.setTextColor(Color.parseColor("#FF8514"))
                        }

                        et_frag_tdly_gd_syqkqt -> {//
                            et_frag_tdly_gd_syqkqt.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_gd_pjlz -> {//
                            et_frag_tdly_gd_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_gd_remark -> {//
                            et_frag_tdly_gd_remark.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_yd_syqkzz -> {//
                            et_frag_tdly_yd_syqkzz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_yd_syqklz -> {//
                            et_frag_tdly_yd_syqklz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_yd_syqkqt -> {//
                            et_frag_tdly_yd_syqkqt.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_yd_pjlz -> {//
                            et_frag_tdly_yd_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_yd_remark -> {//
                            et_frag_tdly_yd_remark.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_ld_syqkwjy -> {//
                            et_frag_tdly_ld_syqkwjy.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_ld_syqklxzyz -> {//
                            et_frag_tdly_ld_syqklxzyz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_ld_pjlz -> {//
                            et_frag_tdly_ld_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_ld_remark -> {//
                            et_frag_tdly_ld_remark.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_nyssjs_syqk -> {//
                            et_frag_tdly_nyssjs_syqk.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_nyssjs_pjlz -> {//
                            et_frag_tdly_nyssjs_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_nyssjs_remark -> {//
                            et_frag_tdly_nyssjs_remark.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_qtnyd_syqk -> {//
                            et_frag_tdly_qtnyd_syqk.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_qtnyd_pjlz -> {//
                            et_frag_tdly_qtnyd_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_qtnyd_remark -> {//
                            et_frag_tdly_qtnyd_remark.setTextColor(Color.parseColor("#FF8514"))
                        }
                        /*et_frag_tdly_jsyd_syqk -> {//
                            et_frag_tdly_jsyd_syqk.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jsyd_qjlz -> {//
                            et_frag_tdly_jsyd_qjlz.setTextColor(Color.parseColor("#FF8514"))
                        }*/
                        et_frag_tdly_jsyd_remarkghpf -> {//
                            et_frag_tdly_jsyd_remarkghpf.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jzyd_syqkzz -> {//
                            et_frag_tdly_jzyd_syqkzz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jzyd_syqklz -> {//
                            et_frag_tdly_jzyd_syqklz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jzyd_syqkqt -> {//
                            et_frag_tdly_jzyd_syqkqt.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jzyd_pjlz -> {//
                            et_frag_tdly_jzyd_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jzyd_remarkqzgj -> {//
                            et_frag_tdly_jzyd_remarkqzgj.setTextColor(Color.parseColor("#FF8514"))
                        }
                        /*et_frag_tdly_gggl_syqk -> {//
                            et_frag_tdly_gggl_syqk.setTextColor(Color.parseColor("#FF8514"))
                        }*/
                        et_frag_tdly_gggl_syqkzy -> {//
                            et_frag_tdly_gggl_syqkzy.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_gggl_syqkcz -> {//
                            et_frag_tdly_gggl_syqkcz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_gggl_syqkqt -> {//
                            et_frag_tdly_gggl_syqkqt.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_gggl_pjlz -> {//
                            et_frag_tdly_gggl_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_gggl_remark -> {//
                            et_frag_tdly_gggl_remark.setTextColor(Color.parseColor("#FF8514"))
                        }
                        /*et_frag_tdly_jtjyjs_syqk -> {//
                            et_frag_tdly_jtjyjs_syqk.setTextColor(Color.parseColor("#FF8514"))
                        }*/
                        et_frag_tdly_jtjyjs_syqkzy -> {//
                            et_frag_tdly_jtjyjs_syqkzy.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jtjyjs_syqkcz -> {//
                            et_frag_tdly_jtjyjs_syqkcz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jtjyjs_syqkqt -> {//
                            et_frag_tdly_jtjyjs_syqkqt.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jtjyjs_pjlz -> {//
                            et_frag_tdly_jtjyjs_pjlz.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_jtjyjs_remark -> {//
                            et_frag_tdly_jtjyjs_remark.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_gyd -> {//
                            et_frag_tdly_tdzmj_gyd.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_yjjbnt -> {//
                            et_frag_tdly_tdzmj_yjjbnt.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_pyzl -> {//
                            et_frag_tdly_tdzmj_pyzl.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_jtjy -> {//
                            et_frag_tdly_tdzmj_jtjy.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_jtjy1 -> {//
                            et_frag_tdly_tdzmj_jtjy1.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_dhcb -> {//
                            et_frag_tdly_tdzmj_dhcb.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_dwzl -> {//
                            et_frag_tdly_tdzmj_dwzl.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_qt -> {//
                            et_frag_tdly_tdzmj_qt.setTextColor(Color.parseColor("#FF8514"))
                        }
                        et_frag_tdly_tdzmj_zdmj -> {//
                            et_frag_tdly_tdzmj_zdmj.setTextColor(Color.parseColor("#FF8514"))
                        }

                    }
                }


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }


    private val yearsList = ArrayList<String>()
    private val yearsListNull = ArrayList<String>()
    private fun yearPopup(yearList:List<String>){
        yearUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_years, slv_frag_zjd_tdly)
        val contentView: View = yearUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_years_rlv)
        val confirm = contentView.findViewById<TextView>(R.id.pop_bcjc_years_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_years_close)


        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_bcjc_years,yearList){
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
            mPresenter.getBchcZhengdqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBchcGetInzhand(AppCache.getInstance().code,yearsList)
//            mPresenter.getBchcZhandqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBchcGetKnzd(AppCache.getInstance().code,yearsList)
        }
        close.setOnClickListener {
            yearUpPopu!!.dismiss()
        }
    }
//    var bczdqkEntity = BczzdqkEntity()
    var bczdqkEntity = BczzdqkEntity()
    var bczdqkEntityList = ArrayList<BczzdqkEntity>()
    var bczhandqkEntity = BczhandqkEntity()
    var bczdqkEntitysId = 0L
    var bczdqkEntitysProcess = 0
    var bczhandqkEntitysId = 0L
    var bczhandqkEntitysProcess = 0
    var bcTdlyEntity = BcTdlyEntity()
    var mBhPopu:CommenPop? = null
    var tylySaveType = 0
    override fun onBcjcYears(tdlyDetail: List<String>) {



        yearsList.clear()
        if (AppCache.getInstance().duties!=1&&AppCache.getInstance().type!=1){
            yearsList.add(tdlyDetail.get(0))
            split = tdlyDetail.get(0).substring(0,tdlyDetail.get(0).length-1)
            year = tdlyDetail.get(0)
        }

        but_frag_zjd_tdly_save.setOnClickListener {
            tylySaveType = 1
            tdlySave()
        }
        //下一步暂时隐藏，和其他两个表一起提交下一步
        but_frag_zjd_tdly_xyb.setOnClickListener {
            if (bcTdlyEntity.id==0L){
                mBhPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject, view)//pop_point
                val contentView = mBhPopu?.getContentView()
                val tv_bcjc_reject_title = contentView?.findViewById<TextView>(R.id.tv_bcjc_reject_title)//标题
                val et_pop_bcjc_reject_clr = contentView?.findViewById<EditText>(R.id.et_pop_bcjc_reject_clr)//处理人
                val pop_bcjc_reject_et_reject = contentView?.findViewById<EditText>(R.id.pop_bcjc_reject_et_reject)//驳回原因
                val ll_pop_bcjc_reject_bhyy = contentView?.findViewById<LinearLayout>(R.id.ll_pop_bcjc_reject_bhyy)//驳回原因
                val pop_bcjc_reject_confirm = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_confirm)//确定
                val pop_bcjc_reject_close = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_close)//取消
                ll_pop_bcjc_reject_bhyy?.visibility = View.GONE
                tv_bcjc_reject_title?.setText("添加")
                pop_bcjc_reject_confirm?.setOnClickListener {
                    bcTdlyEntity.code = AppCache.getInstance().code
                    bcTdlyEntity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                    bcTdlyEntity.nydmj = et_frag_tdly_nyd_zdmj.text.toString()//农用地-占地面积
                    bcTdlyEntity.nydqd = et_frag_tdly_nyd_remarkqqqd.text.toString()//农用地-确权确地面积
                    bcTdlyEntity.nydql = et_frag_tdly_nyd_remarkqqql.text.toString()//农用地-确权确利面积
                    bcTdlyEntity.nydqg = et_frag_tdly_nyd_remarkqqqg.text.toString()//农用地-确权确股面积
//                    bcTdlyEntity.gydmj = et_frag_tdly_nyd_remark.text.toString()//农用地-备注
                    bcTdlyEntity.gdmj = et_frag_tdly_gd_zdmj.text.toString()//耕地-占地面积
                    bcTdlyEntity.gdzzmj = et_frag_tdly_gd_syqkzz.text.toString()//耕地-自种面积
                    bcTdlyEntity.gdlzmj = et_frag_tdly_gd_syqklz.text.toString()//耕地-流转面积
                    bcTdlyEntity.gdqtmj = et_frag_tdly_gd_syqkqt.text.toString()//耕地-其他面积
                    bcTdlyEntity.gdjg = et_frag_tdly_gd_pjlz.text.toString()//耕地-平均流转
                    bcTdlyEntity.gdbz = et_frag_tdly_gd_remark.text.toString()//耕地-备注
//                    bcTdlyEntity.gdqd = et_frag_tdly_gd_remarkqqqd.text.toString()//耕地-确权确地面积
//                    bcTdlyEntity.gdql = et_frag_tdly_gd_remarkqqql.text.toString()//耕地-确权确利面积
//                    bcTdlyEntity.gdqg = et_frag_tdly_gd_remarkqqqg.text.toString()//耕地-确权确股面积
                    bcTdlyEntity.ydmj = et_frag_tdly_yd_zdmj.text.toString()//园地-占地面积
                    bcTdlyEntity.ydzzmj = et_frag_tdly_yd_syqkzz.text.toString()//园地-自种面积
                    bcTdlyEntity.ydlzmj = et_frag_tdly_yd_syqklz.text.toString()//园地-流转面积
                    bcTdlyEntity.ydqtmj = et_frag_tdly_yd_syqkqt.text.toString()//园地-其他面积
                    bcTdlyEntity.ydjg = et_frag_tdly_yd_pjlz.text.toString()//园地-平均流转
                    bcTdlyEntity.ydbz = et_frag_tdly_yd_remark.text.toString()//园地-备注
//                    bcTdlyEntity.ydqd = et_frag_tdly_yd_remarkqqqd.text.toString()//园地-确权确地面积
//                    bcTdlyEntity.ydql = et_frag_tdly_yd_remarkqqql.text.toString()//园地-确权确利面积
//                    bcTdlyEntity.ydqg = et_frag_tdly_yd_remarkqqqg.text.toString()//园地-确权确股面积
                    bcTdlyEntity.ldmj = et_frag_tdly_ld_zdmj.text.toString()//林地-占地面积(亩)
                    bcTdlyEntity.ldwjy = et_frag_tdly_ld_syqkwjy.text.toString()//林地-无经营面积
                    bcTdlyEntity.ldlxzyz = et_frag_tdly_ld_syqklxzyz.text.toString()//林地-林下种养殖面积
                    bcTdlyEntity.ldjg = et_frag_tdly_ld_pjlz.text.toString()//林地-平均流转
                    bcTdlyEntity.ldbz = et_frag_tdly_ld_remark.text.toString()//林地-备注
//                    bcTdlyEntity.ldqd = et_frag_tdly_ld_remarkqqqd.text.toString()//林地-确权确地面积
//                    bcTdlyEntity.ldql = et_frag_tdly_ld_remarkqqql.text.toString()//林地-确权确利面积
//                    bcTdlyEntity.ldqg = et_frag_tdly_ld_remarkqqqg.text.toString()//林地-确权确股面积
                    bcTdlyEntity.nyjsmj = et_frag_tdly_nyssjs_zdmj.text.toString() //农业设施建设-面积
//                    bcTdlyEntity.nyjssyqk = et_frag_tdly_nyssjs_syqk.text.toString() //农业设施建设-使用情况
//                    bcTdlyEntity.nyjszzmj = et_frag_tdly_nyssjs_syqkzz.text.toString()//农业设施建设-自种面积
//                    bcTdlyEntity.nyjslzmj = et_frag_tdly_nyssjs_syqklz.text.toString()//农业设施建设-流转面积
//                    bcTdlyEntity.nyjsqtmj = et_frag_tdly_nyssjs_syqkqt.text.toString()//农业设施建设-其他面积
//                    bcTdlyEntity.nyjsjg = et_frag_tdly_nyssjs_pjlz.text.toString()//农业设施建设-平均流转
                    bcTdlyEntity.nyjsbz = et_frag_tdly_nyssjs_remark.text.toString()//农业设施建设-备注
                    bcTdlyEntity.qtnydmj = et_frag_tdly_qtnyd_zdmj.text.toString()//其他农用地-面积
//                    bcTdlyEntity.qtnydsyqk = et_frag_tdly_qtnyd_syqk.text.toString()//其他农用地-使用情况
//                    bcTdlyEntity.qtnydjg = et_frag_tdly_qtnyd_pjlz.text.toString()//其他农用地-平均流转
                    bcTdlyEntity.qtnydbz = et_frag_tdly_qtnyd_remark.text.toString()//其他农用地-备注
                    bcTdlyEntity.jsydmj = et_frag_tdly_jsyd_zdmj.text.toString()//建设用地-占地面积
//                    bcTdlyEntity.jsydsyqk = et_frag_tdly_jsyd_syqk.text.toString()//建设用地-使用情况
//                    bcTdlyEntity.jsydjg = et_frag_tdly_jsyd_qjlz.text.toString()//建设用地-平均流转
                    bcTdlyEntity.jsydpf = et_frag_tdly_jsyd_remarkghpf.text.toString()//建设用地-规划批复使用面积
                    bcTdlyEntity.jzydmj = et_frag_tdly_jzyd_zdmj.text.toString()//居住用地-占地面积
                    bcTdlyEntity.jzydzz = et_frag_tdly_jzyd_syqkzz.text.toString()//居住用地-自住
                    bcTdlyEntity.jzydcz = et_frag_tdly_jzyd_syqklz.text.toString()//居住用地-出租
                    bcTdlyEntity.jzydqt = et_frag_tdly_jzyd_syqkqt.text.toString()//居住用地-其他
                    bcTdlyEntity.jzydjg = et_frag_tdly_jzyd_pjlz.text.toString()//居住用地-平均流转
                    bcTdlyEntity.jzydms = et_frag_tdly_jzyd_remarkqzgj.text.toString()//居住用地-民宿
                    bcTdlyEntity.ggydmj = et_frag_tdly_gggl_zdmj.text.toString()//公共管理与公共服务用地-占地面积
//                    bcTdlyEntity.ggydsyqk = et_frag_tdly_gggl_syqk.text.toString()//公共管理与公共服务用地-使用情况
                    bcTdlyEntity.ggydzz = et_frag_tdly_gggl_syqkzy.text.toString()//公共管理与公共服务用地-使用情况
                    bcTdlyEntity.ggydcz = et_frag_tdly_gggl_syqkcz.text.toString()//公共管理与公共服务用地-使用情况
                    bcTdlyEntity.ggydqt = et_frag_tdly_gggl_syqkqt.text.toString()//公共管理与公共服务用地-使用情况
                    bcTdlyEntity.ggydlzjg = et_frag_tdly_gggl_pjlz.text.toString()//公共管理与公共服务用地-使用情况
                    bcTdlyEntity.ggydbz = et_frag_tdly_gggl_remark.text.toString()//公共管理与公共服务用地-备注
                    bcTdlyEntity.jtydmj = et_frag_tdly_jtjyjs_zdmj.text.toString()//集体经营性建设用地-占地面积
//                    bcTdlyEntity.jtydsyqk = et_frag_tdly_jtjyjs_syqk.text.toString()//集体经营性建设用地-使用情况
                    bcTdlyEntity.jtydzz = et_frag_tdly_jtjyjs_syqkzy.text.toString()//集体经营性建设用地-使用情况自用
                    bcTdlyEntity.jtydcz = et_frag_tdly_jtjyjs_syqkcz.text.toString()//集体经营性建设用地-使用情况出租
                    bcTdlyEntity.jtydqt = et_frag_tdly_jtjyjs_syqkqt.text.toString()//集体经营性建设用地-使用情况其他
                    bcTdlyEntity.jtydjg = et_frag_tdly_jtjyjs_pjlz.text.toString()//集体经营性建设用地-平均流转
                    bcTdlyEntity.jtydbz = et_frag_tdly_jtjyjs_remark.text.toString()//集体经营性建设用地-备注
                    bcTdlyEntity.tdzmj = et_frag_tdly_tdzmj_zdmj.text.toString()//土地总面积
                    bcTdlyEntity.gymj = et_frag_tdly_tdzmj_gyd.text.toString()//国有面积
//                    bcTdlyEntity.yjjbnt = et_frag_tdly_tdzmj_yjjbnt.text.toString()//基本永久农田
                    bcTdlyEntity.pyzl = et_frag_tdly_tdzmj_pyzl.text.toString()//平原造林
                    bcTdlyEntity.jtjy = et_frag_tdly_tdzmj_jtjy.text.toString()//家庭经营
                    bcTdlyEntity.jtjy1 = et_frag_tdly_tdzmj_jtjy1.text.toString()//集体经营
                    bcTdlyEntity.dhcb = et_frag_tdly_tdzmj_dhcb.text.toString()//大户承包
                    bcTdlyEntity.dwzl = et_frag_tdly_tdzmj_dwzl.text.toString()//对外租赁
                    bcTdlyEntity.qtmj = et_frag_tdly_tdzmj_qt.text.toString()//其他面积
//                    bcTdlyEntity.zjmj = et_frag_tdly_tdzmj_total.text.toString()//总计面积
                    bcTdlyEntity.process = 2
                    bcTdlyEntity.years = tv_frag_tdly_xzsj.addText
                    val clr = et_pop_bcjc_reject_clr?.text.toString()//处理人
                    if (clr.equals("")){
                        ToastUtils.showShort("请输入处理人")
                    }else {
                        if (AppCache.getInstance().type==4){
                            bcTdlyEntity.cunname = clr
                        }
                        if (AppCache.getInstance().type==3){
                            bcTdlyEntity.zhenname = clr
                        }
                        if (AppCache.getInstance().type==2){
                            bcTdlyEntity.qvname = clr
                        }
                        mPresenter.getBcjcTdlySave(bcTdlyEntity)
                    }

                }

                pop_bcjc_reject_close?.setOnClickListener {
                    mBhPopu?.dismiss()
                }
                CommenPop.backgroundAlpha(0.5f, activity)
                mBhPopu?.showAtLocation(view, Gravity.CENTER, 0, 0)
            }else{//process

                mBhPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject, view)//pop_point
                val contentView = mBhPopu?.getContentView()
                val tv_bcjc_reject_title = contentView?.findViewById<TextView>(R.id.tv_bcjc_reject_title)//标题
                val et_pop_bcjc_reject_clr = contentView?.findViewById<EditText>(R.id.et_pop_bcjc_reject_clr)//处理人
                val pop_bcjc_reject_et_reject = contentView?.findViewById<EditText>(R.id.pop_bcjc_reject_et_reject)//驳回原因
                val ll_pop_bcjc_reject_bhyy = contentView?.findViewById<LinearLayout>(R.id.ll_pop_bcjc_reject_bhyy)//驳回原因
                val pop_bcjc_reject_confirm = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_confirm)//确定
                val pop_bcjc_reject_close = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_close)//取消
                ll_pop_bcjc_reject_bhyy?.visibility = View.GONE
                tv_bcjc_reject_title?.setText("下一步")
                pop_bcjc_reject_confirm?.setOnClickListener {
                    val clr = et_pop_bcjc_reject_clr?.text.toString()//处理人
                    if (clr.equals("")){
                        ToastUtils.showShort("请输入处理人")
                    }else {
                        if (bcTdlyEntity.process==1||bcTdlyEntity.process==0){

                            bcTdlyEntity.code = AppCache.getInstance().code
                            bcTdlyEntity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                            bcTdlyEntity.nydmj = et_frag_tdly_nyd_zdmj.text.toString()//农用地-占地面积
                            bcTdlyEntity.nydqd = et_frag_tdly_nyd_remarkqqqd.text.toString()//农用地-确权确地面积
                            bcTdlyEntity.nydql = et_frag_tdly_nyd_remarkqqql.text.toString()//农用地-确权确利面积
                            bcTdlyEntity.nydqg = et_frag_tdly_nyd_remarkqqqg.text.toString()//农用地-确权确股面积
//                    bcTdlyEntity.gydmj = et_frag_tdly_nyd_remark.text.toString()//农用地-备注
                            bcTdlyEntity.gdmj = et_frag_tdly_gd_zdmj.text.toString()//耕地-占地面积
                            bcTdlyEntity.gdzzmj = et_frag_tdly_gd_syqkzz.text.toString()//耕地-自种面积
                            bcTdlyEntity.gdlzmj = et_frag_tdly_gd_syqklz.text.toString()//耕地-流转面积
                            bcTdlyEntity.gdqtmj = et_frag_tdly_gd_syqkqt.text.toString()//耕地-其他面积
                            bcTdlyEntity.gdjg = et_frag_tdly_gd_pjlz.text.toString()//耕地-平均流转
                            bcTdlyEntity.gdbz = et_frag_tdly_gd_remark.text.toString()//耕地-备注
//                    bcTdlyEntity.gdqd = et_frag_tdly_gd_remarkqqqd.text.toString()//耕地-确权确地面积
//                    bcTdlyEntity.gdql = et_frag_tdly_gd_remarkqqql.text.toString()//耕地-确权确利面积
//                    bcTdlyEntity.gdqg = et_frag_tdly_gd_remarkqqqg.text.toString()//耕地-确权确股面积
                            bcTdlyEntity.ydmj = et_frag_tdly_yd_zdmj.text.toString()//园地-占地面积
                            bcTdlyEntity.ydzzmj = et_frag_tdly_yd_syqkzz.text.toString()//园地-自种面积
                            bcTdlyEntity.ydlzmj = et_frag_tdly_yd_syqklz.text.toString()//园地-流转面积
                            bcTdlyEntity.ydqtmj = et_frag_tdly_yd_syqkqt.text.toString()//园地-其他面积
                            bcTdlyEntity.ydjg = et_frag_tdly_yd_pjlz.text.toString()//园地-平均流转
                            bcTdlyEntity.ydbz = et_frag_tdly_yd_remark.text.toString()//园地-备注
//                    bcTdlyEntity.ydqd = et_frag_tdly_yd_remarkqqqd.text.toString()//园地-确权确地面积
//                    bcTdlyEntity.ydql = et_frag_tdly_yd_remarkqqql.text.toString()//园地-确权确利面积
//                    bcTdlyEntity.ydqg = et_frag_tdly_yd_remarkqqqg.text.toString()//园地-确权确股面积
                            bcTdlyEntity.ldmj = et_frag_tdly_ld_zdmj.text.toString()//林地-占地面积(亩)
                            bcTdlyEntity.ldwjy = et_frag_tdly_ld_syqkwjy.text.toString()//林地-无经营面积
                            bcTdlyEntity.ldlxzyz = et_frag_tdly_ld_syqklxzyz.text.toString()//林地-林下种养殖面积
                            bcTdlyEntity.ldjg = et_frag_tdly_ld_pjlz.text.toString()//林地-平均流转
                            bcTdlyEntity.ldbz = et_frag_tdly_ld_remark.text.toString()//林地-备注
//                    bcTdlyEntity.ldqd = et_frag_tdly_ld_remarkqqqd.text.toString()//林地-确权确地面积
//                    bcTdlyEntity.ldql = et_frag_tdly_ld_remarkqqql.text.toString()//林地-确权确利面积
//                    bcTdlyEntity.ldqg = et_frag_tdly_ld_remarkqqqg.text.toString()//林地-确权确股面积
                            bcTdlyEntity.nyjsmj = et_frag_tdly_nyssjs_zdmj.text.toString() //农业设施建设-面积
//                            bcTdlyEntity.nyjssyqk = et_frag_tdly_nyssjs_syqk.text.toString() //农业设施建设-使用情况
//                    bcTdlyEntity.nyjszzmj = et_frag_tdly_nyssjs_syqkzz.text.toString()//农业设施建设-自种面积
//                    bcTdlyEntity.nyjslzmj = et_frag_tdly_nyssjs_syqklz.text.toString()//农业设施建设-流转面积
//                    bcTdlyEntity.nyjsqtmj = et_frag_tdly_nyssjs_syqkqt.text.toString()//农业设施建设-其他面积
//                            bcTdlyEntity.nyjsjg = et_frag_tdly_nyssjs_pjlz.text.toString()//农业设施建设-平均流转
                            bcTdlyEntity.nyjsbz = et_frag_tdly_nyssjs_remark.text.toString()//农业设施建设-备注
                            bcTdlyEntity.qtnydmj = et_frag_tdly_qtnyd_zdmj.text.toString()//其他农用地-面积
//                            bcTdlyEntity.qtnydsyqk = et_frag_tdly_qtnyd_syqk.text.toString()//其他农用地-使用情况
//                            bcTdlyEntity.qtnydjg = et_frag_tdly_qtnyd_pjlz.text.toString()//其他农用地-平均流转
                            bcTdlyEntity.qtnydbz = et_frag_tdly_qtnyd_remark.text.toString()//其他农用地-备注
                            bcTdlyEntity.jsydmj = et_frag_tdly_jsyd_zdmj.text.toString()//建设用地-占地面积
//                            bcTdlyEntity.jsydsyqk = et_frag_tdly_jsyd_syqk.text.toString()//建设用地-使用情况
//                            bcTdlyEntity.jsydjg = et_frag_tdly_jsyd_qjlz.text.toString()//建设用地-平均流转
                            bcTdlyEntity.jsydpf = et_frag_tdly_jsyd_remarkghpf.text.toString()//建设用地-规划批复使用面积
                            bcTdlyEntity.jzydmj = et_frag_tdly_jzyd_zdmj.text.toString()//居住用地-占地面积
                            bcTdlyEntity.jzydzz = et_frag_tdly_jzyd_syqkzz.text.toString()//居住用地-自住
                            bcTdlyEntity.jzydcz = et_frag_tdly_jzyd_syqklz.text.toString()//居住用地-出租
                            bcTdlyEntity.jzydqt = et_frag_tdly_jzyd_syqkqt.text.toString()//居住用地-其他
                            bcTdlyEntity.jzydjg = et_frag_tdly_jzyd_pjlz.text.toString()//居住用地-平均流转
                            bcTdlyEntity.jzydms = et_frag_tdly_jzyd_remarkqzgj.text.toString()//居住用地-民宿
                            bcTdlyEntity.ggydmj = et_frag_tdly_gggl_zdmj.text.toString()//公共管理与公共服务用地-占地面积
//                            bcTdlyEntity.ggydsyqk = et_frag_tdly_gggl_syqk.text.toString()//公共管理与公共服务用地-使用情况
                            bcTdlyEntity.ggydzz = et_frag_tdly_gggl_syqkzy.text.toString()//公共管理与公共服务用地-使用情况
                            bcTdlyEntity.ggydcz = et_frag_tdly_gggl_syqkcz.text.toString()//公共管理与公共服务用地-使用情况
                            bcTdlyEntity.ggydqt = et_frag_tdly_gggl_syqkqt.text.toString()//公共管理与公共服务用地-使用情况
                            bcTdlyEntity.ggydlzjg = et_frag_tdly_gggl_pjlz.text.toString()//公共管理与公共服务用地-使用情况
                            bcTdlyEntity.ggydbz = et_frag_tdly_gggl_remark.text.toString()//公共管理与公共服务用地-备注
                            bcTdlyEntity.jtydmj = et_frag_tdly_jtjyjs_zdmj.text.toString()//集体经营性建设用地-占地面积
//                            bcTdlyEntity.jtydsyqk = et_frag_tdly_jtjyjs_syqk.text.toString()//集体经营性建设用地-使用情况
                            bcTdlyEntity.jtydzz = et_frag_tdly_jtjyjs_syqkzy.text.toString()//集体经营性建设用地-使用情况自用
                            bcTdlyEntity.jtydcz = et_frag_tdly_jtjyjs_syqkcz.text.toString()//集体经营性建设用地-使用情况出租
                            bcTdlyEntity.jtydqt = et_frag_tdly_jtjyjs_syqkqt.text.toString()//集体经营性建设用地-使用情况其他
                            bcTdlyEntity.jtydjg = et_frag_tdly_jtjyjs_pjlz.text.toString()//集体经营性建设用地-平均流转
                            bcTdlyEntity.jtydbz = et_frag_tdly_jtjyjs_remark.text.toString()//集体经营性建设用地-备注
                            bcTdlyEntity.tdzmj = et_frag_tdly_tdzmj_zdmj.text.toString()//土地总面积
                            bcTdlyEntity.gymj = et_frag_tdly_tdzmj_gyd.text.toString()//国有面积
//                            bcTdlyEntity.yjjbnt = et_frag_tdly_tdzmj_yjjbnt.text.toString()//基本永久农田
                            bcTdlyEntity.pyzl = et_frag_tdly_tdzmj_pyzl.text.toString()//平原造林
                            bcTdlyEntity.jtjy = et_frag_tdly_tdzmj_jtjy.text.toString()//家庭经营
                            bcTdlyEntity.jtjy1 = et_frag_tdly_tdzmj_jtjy1.text.toString()//集体经营
                            bcTdlyEntity.dhcb = et_frag_tdly_tdzmj_dhcb.text.toString()//大户承包
                            bcTdlyEntity.dwzl = et_frag_tdly_tdzmj_dwzl.text.toString()//对外租赁
                            bcTdlyEntity.qtmj = et_frag_tdly_tdzmj_qt.text.toString()//其他面积
                        }
                        if (AppCache.getInstance().type==4){
                            bcTdlyEntity.cunname = clr
                        }
                        if (AppCache.getInstance().type==3){
                            bcTdlyEntity.zhenname = clr
                        }
                        if (AppCache.getInstance().type==2){
                            bcTdlyEntity.qvname = clr
                        }
                        bcTdlyEntity.process = bcTdlyEntity.process + 1
                        mPresenter.getBcjcTdlyUpdate(bcTdlyEntity)
                    }
                }

                pop_bcjc_reject_close?.setOnClickListener {
                    mBhPopu?.dismiss()
                }
                CommenPop.backgroundAlpha(0.5f, activity)
                mBhPopu?.showAtLocation(view, Gravity.CENTER, 0, 0)
            }

        }
        //驳回暂时隐藏，和其他两个表一起驳回
        but_frag_zjd_tdly_bh.setOnClickListener {
            mBhPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject, view)//pop_point
            val contentView = mBhPopu?.getContentView()
            val tv_bcjc_reject_title = contentView?.findViewById<TextView>(R.id.tv_bcjc_reject_title)//标题
            val et_pop_bcjc_reject_clr = contentView?.findViewById<EditText>(R.id.et_pop_bcjc_reject_clr)//处理人
            val pop_bcjc_reject_et_reject = contentView?.findViewById<EditText>(R.id.pop_bcjc_reject_et_reject)//驳回原因
            val pop_bcjc_reject_confirm = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_confirm)//确定
            val pop_bcjc_reject_close = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_close)//取消
            tv_bcjc_reject_title?.setText("驳回")

            pop_bcjc_reject_confirm?.setOnClickListener {
                val clr = et_pop_bcjc_reject_clr?.text.toString()//处理人
                val bhyy = pop_bcjc_reject_et_reject?.text.toString()//驳回原因
                if (clr.equals("")){
                    ToastUtils.showShort("请输入处理人")
                }else if (bhyy.equals("")){
                    ToastUtils.showShort("请输入驳回原因")
                }else {
                    if (AppCache.getInstance().type==4){
                        bcTdlyEntity.cunname = clr
                    }
                    if (AppCache.getInstance().type==3){
                        bcTdlyEntity.zhenname = clr
                    }
                    if (AppCache.getInstance().type==2){
                        bcTdlyEntity.qvname = clr
                    }
                    val bcrejectedEntity = BcrejectedEntity()
                    bcrejectedEntity.content = bhyy
                    bcTdlyEntity.bcrejectedEntities.add(bcrejectedEntity)
                    bcTdlyEntity.process = 1//bcTdlyEntity.process - 1
                    mPresenter.getBcjcTdlyUpdate(bcTdlyEntity)
                }

            }
            pop_bcjc_reject_close?.setOnClickListener {
                mBhPopu?.dismiss()
            }
            CommenPop.backgroundAlpha(0.5f, activity)
            mBhPopu?.showAtLocation(view, Gravity.CENTER, 0, 0)
        }
        tv_frag_zjd_tdly_add.setOnClickListener {
            if (tv_frag_zjd_tdly_add.text.toString().equals("操作")||tv_frag_zjd_tdly_add.text.toString().equals("取消")){
                onClickUpdate()
            }
        }
        tv_frag_tdly_xzsj.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("添加")){

                    val arrayList = ArrayList<String>()
                    arrayList.addAll(tdlyDetail)

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_frag_tdly_xzsj.addText = arrayList.get(options1)
//                tv_frag_tdly_xzsj.setText(arrayList.get(options1))
                        split = arrayList.get(options1).substring(0,arrayList.get(options1).length-1)
                        year = arrayList.get(options1)
                        mPresenter.getBcjcGetTdly(AppCache.getInstance().code,year)
                        if (AppCache.getInstance().type!=1&&AppCache.getInstance().duties!=1){

                            yearsList.clear()
                            yearsList.add(year)
                            mPresenter.getBchcZhengdqk(AppCache.getInstance().code,yearsList)
                            mPresenter.getBchcGetInzhand(AppCache.getInstance().code,yearsList)
//                            mPresenter.getBchcZhandqk(AppCache.getInstance().code,yearsList)
                            mPresenter.getBchcGetKnzd(AppCache.getInstance().code,yearsList)
                            mPresenter.getBchcZhengdqkAll(AppCache.getInstance().code,yearsListNull)
//                            mPresenter.getBchcZhandqkAll(AppCache.getInstance().code,yearsListNull)
                        }

//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(arrayList.indexOf(tv_frag_tdly_xzsj.addText))
                    pvOptions.setPicker(arrayList)

                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                    params.leftMargin = 0
                    params.rightMargin = 0
                    val contentContainer = pvOptions.getDialogContainerLayout()
                    contentContainer.setLayoutParams(params)
                    pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                    pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    pvOptions.show()

                    /*PopuPointUtils.initPopuSelectYear(activity,ll_zjd_tdly_year,arrayList,object: PopuPointUtils.OnSelectYearLinster{
                        override fun onClick(years: String) {
                            tv_frag_tdly_xzsj.setText(years)
                            year = years
                            mPresenter.getBcjcGetTdly(AppCache.getInstance().code,year)
                        }
                    })*/
                }
            }
        })
        tv_frag_tdly_xzsj.setOnClickListener {
        }

        mPresenter.getBcjcGetTdly(AppCache.getInstance().code,tdlyDetail.get(0))//year
        mPresenter.getBchcZhengdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcGetInzhand(AppCache.getInstance().code,yearsList)
//        mPresenter.getBchcZhandqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcGetKnzd(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcZhengdqkAll(AppCache.getInstance().code,yearsListNull)
//        mPresenter.getBchcZhandqkAll(AppCache.getInstance().code,yearsListNull)
        yearPopup(tdlyDetail)
        tv_frag_tdly_xzsj.addText = tdlyDetail.get(0)
        /*if (AppCache.getInstance().type == 4){
            tv_frag_tdqs_zhengdqk_add.text = "操作"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_tdqs_zhengdqk_add.visibility = View.GONE
        }else{
            tv_frag_tdqs_zhengdqk_add.text = "操作"
        }*/

        //土地征占操作
        tv_frag_tdqs_zhengdqk_add.setOnClickListener {
            if (tv_frag_tdqs_zhengdqk_add.text.toString().equals("操作")){
            addZhengdqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_zhengdqk, slv_frag_zjd_tdly)
            val contentView: View = addZhengdqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_tdqs_spr)
            val tv_pop_add_zheng = contentView.findViewById<TextView>(R.id.tv_pop_add_zheng)
            val popAddTdqsEtZdsj = contentView.findViewById<TextView>(R.id.pop_add_tdqs_et_zdsj)
            val et_zdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_zdmj)
            val et_gyxzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_gyxzdmj)
            val et_gyxzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_gyxzdbcf)
            val et_kfjszdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_kfjszdmj)
            val et_kfjszdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_kfjszdbcf)
            val et_qtzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_qtzdmj)
            val et_qtzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_qtzdbcf)

                val et_zdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_zdmj)
                val et_jcsszdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jcsszdmj)
                val et_jcsszdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jcsszdbcf)
                val et_jdzfzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jdzfzdmj)
                val et_jdzfzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jdzfzdbcf)
                val et_kfqzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_kfqzdmj)
                val et_kfqzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_kfqzdbcf)
                val et_gysyzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_gysyzdmj)
                val et_gysyzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_gysyzdbcf)
                val et_qtzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_qtzdmj)
                val et_qtzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_qtzdbcf)

            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_tdqs_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_tdqs_confirm)
            val reject = contentView.findViewById<TextView>(R.id.pop_add_tdqs_reject)//驳回
            val close = contentView.findViewById<TextView>(R.id.pop_add_tdqs_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_tdqs_ell)

            ll_reject.visibility = View.GONE
            popAddTdqsEtZdsj.setOnClickListener {
                TimePickerUtil.initTimePickerViewNy(activity,popAddTdqsEtZdsj.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                    override fun onClick(data: String?) { //时间筛选
                        popAddTdqsEtZdsj.setText(data)
                    }
                })
                

            }
            CommenPop.backgroundAlpha(0.5f, activity)
            addZhengdqkUpPopu!!.showAtLocation(slv_frag_zjd_tdly, Gravity.CENTER, 0, 0)

            /*val sprAdapter = com.jymj.zhglxt.widget.SpinnerAdapter(activity)
            spinner.adapter = sprAdapter
            sprAdapter.setDatas(tdlyDetail)
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                }

            }*/
                tv_pop_add_zheng.setText(tv_frag_tdly_xzsj.addText)//tdlyDetail.get(0)

                for (i in bczdqkEntitys ){
                    if (tv_frag_tdly_xzsj.addText.equals(i.years)){//tdlyDetail.get(0)   i.years
                        popAddTdqsEtZdsj.setText(i.zdsj)
                        et_zdmj.setText(i.zdmj)
                        et_gyxzdmj.setText(i.gyzdmj)
                        et_gyxzdbcf.setText(i.gybcf)
                        et_kfjszdmj.setText(i.kfjsmj)
                        et_kfjszdbcf.setText(i.kfjsbcf)
                        et_qtzdmj.setText(i.qtzdmj)
                        et_qtzdbcf.setText(i.qtzdbcf)


                        et_zdmj_zhan.setText(i.zhandmj)
                        et_jcsszdmj_zhan.setText(i.jcmj)
                        et_jcsszdbcf_zhan.setText(i.jcbcf)
                        et_jdzfzdmj_zhan.setText(i.jdmj)
                        et_jdzfzdbcf_zhan.setText(i.jdbcf)
                        et_kfqzdmj_zhan.setText(i.kfqmj)
                        et_kfqzdbcf_zhan.setText(i.kfqbcf)
                        et_gysyzdmj_zhan.setText(i.gysymj)
                        et_gysyzdbcf_zhan.setText(i.gysybcf)
                        et_qtzdmj_zhan.setText(i.qtmj)
                        et_qtzdbcf_zhan.setText(i.qtbcf)

                        if (AppCache.getInstance().type==1){
                            et_czrname.setText(i.qvname)
                        }else if (AppCache.getInstance().type==2){
                            et_czrname.setText(i.qvname)
                        }else if (AppCache.getInstance().type==3){
                            et_czrname.setText(i.zhenname)
                        }else if (AppCache.getInstance().type==4){
                            et_czrname.setText(i.cunname)
                        }
                        bczdqkEntitysId = i.id
                        bczdqkEntitysProcess = i.process
                        if (AppCache.getInstance().type == 4){
//                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bczdqkEntitysProcess){
                                1->{
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }
                                2->{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }
                        else{
                            enableLl.setNoClick(true)
                            if (AppCache.getInstance().type == 3){
//                                et_czrname.setText(i.zhenname)
                                if (bczdqkEntitysProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
//                                et_czrname.setText(i.qvname)
                                if (bczdqkEntitysProcess == 3){
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
                        popAddTdqsEtZdsj.setText("")
                        et_zdmj.setText("")
                        et_gyxzdmj.setText("")
                        et_gyxzdbcf.setText("")
                        et_kfjszdmj.setText("")
                        et_kfjszdbcf.setText("")
                        et_qtzdmj.setText("")
                        et_qtzdbcf.setText("")

                        et_zdmj_zhan.setText("")
                        et_jcsszdmj_zhan.setText("")
                        et_jcsszdbcf_zhan.setText("")
                        et_jdzfzdmj_zhan.setText("")
                        et_jdzfzdbcf_zhan.setText("")
                        et_kfqzdmj_zhan.setText("")
                        et_kfqzdbcf_zhan.setText("")
                        et_gysyzdmj_zhan.setText("")
                        et_gysyzdbcf_zhan.setText("")
                        et_qtzdmj_zhan.setText("")
                        et_qtzdbcf_zhan.setText("")

                        bczdqkEntitysId = 0L
                        bczdqkEntitysProcess = 0
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


            when(AppCache.getInstance().type){
                /*2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {
                        val entity = BczzdqkEntity()
                        entity.zdsj = popAddTdqsEtZdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.gyzdmj = et_gyxzdmj.text.toString()
                        entity.gybcf = et_gyxzdbcf.text.toString()
                        entity.kfjsmj = et_kfjszdmj.text.toString()
                        entity.kfjsbcf = et_kfjszdbcf.text.toString()
                        entity.qtzdmj = et_qtzdmj.text.toString()
                        entity.qtzdbcf = et_qtzdbcf.text.toString()


//                        entity.years = spinner.selectedItem.toString()
                        entity.years = tv_pop_add_zheng.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bczdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateZhengdqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        val entity = BczdqkEntity()
                        entity.zdsj = popAddTdqsEtZdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.gyzdmj = et_gyxzdmj.text.toString()
                        entity.gybcf = et_gyxzdbcf.text.toString()
                        entity.kfjsmj = et_kfjszdmj.text.toString()
                        entity.kfjsbcf = et_kfjszdbcf.text.toString()
                        entity.qtzdmj = et_qtzdmj.text.toString()
                        entity.qtzdbcf = et_qtzdbcf.text.toString()
//                        entity.years = spinner.selectedItem.toString()
                        entity.years = tv_pop_add_zheng.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bczdqkEntitysId
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
                            mPresenter.getBcjcUpdateZhengdqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        val entity = BczdqkEntity()
                        entity.zdsj = popAddTdqsEtZdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.gyzdmj = et_gyxzdmj.text.toString()
                        entity.gybcf = et_gyxzdbcf.text.toString()
                        entity.kfjsmj = et_kfjszdmj.text.toString()
                        entity.kfjsbcf = et_kfjszdbcf.text.toString()
                        entity.qtzdmj = et_qtzdmj.text.toString()
                        entity.qtzdbcf = et_qtzdbcf.text.toString()
//                        entity.years = spinner.selectedItem.toString()
                        entity.years = tv_pop_add_zheng.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bczdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateZhengdqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        val entity = BczdqkEntity()
                        entity.zdsj = popAddTdqsEtZdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.gyzdmj = et_gyxzdmj.text.toString()
                        entity.gybcf = et_gyxzdbcf.text.toString()
                        entity.kfjsmj = et_kfjszdmj.text.toString()
                        entity.kfjsbcf = et_kfjszdbcf.text.toString()
                        entity.qtzdmj = et_qtzdmj.text.toString()
                        entity.qtzdbcf = et_qtzdbcf.text.toString()
//                        entity.years = spinner.selectedItem.toString()
                        entity.years = tv_pop_add_zheng.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bczdqkEntitysId
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
                            mPresenter.getBcjcUpdateZhengdqk(entity)
                        }
                    }
                }*/
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        val entity = BczzdqkEntity()
                        entity.zdsj = popAddTdqsEtZdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.gyzdmj = et_gyxzdmj.text.toString()
                        entity.gybcf = et_gyxzdbcf.text.toString()
                        entity.kfjsmj = et_kfjszdmj.text.toString()
                        entity.kfjsbcf = et_kfjszdbcf.text.toString()
                        entity.qtzdmj = et_qtzdmj.text.toString()
                        entity.qtzdbcf = et_qtzdbcf.text.toString()

                        entity.zhandmj = et_zdmj_zhan.text.toString()
                        entity.jcmj = et_jcsszdmj_zhan.text.toString()
                        entity.jcbcf = et_jcsszdbcf_zhan.text.toString()
                        entity.jdmj = et_jdzfzdmj_zhan.text.toString()
                        entity.jdbcf = et_jdzfzdbcf_zhan.text.toString()
                        entity.kfqmj = et_kfqzdmj_zhan.text.toString()
                        entity.kfqbcf = et_kfqzdbcf_zhan.text.toString()
                        entity.gysymj = et_gysyzdmj_zhan.text.toString()
                        entity.gysybcf = et_gysyzdbcf_zhan.text.toString()
                        entity.qtmj = et_qtzdmj_zhan.text.toString()
                        entity.qtbcf = et_qtzdbcf_zhan.text.toString()

//                        entity.years = spinner.selectedItem.toString()
                        entity.years = tv_pop_add_zheng.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (popAddTdqsEtZdsj.text.toString().equals("")){
                            ToastUtils.showShort("请选择征占地时间")
                        }/*else if (TdlyUtils.setEditToast(et_zdmj,et_gyxzdmj,et_gyxzdbcf,et_kfjszdmj,et_kfjszdbcf,et_qtzdmj,et_qtzdbcf)){
                        }*/else{
                            entity.cunname = et_czrname.text.toString()
                            val arrayList = ArrayList<BczzdqkEntity>()
                            arrayList.add(entity)
                            mPresenter.getBcjcAddZhengdqk(arrayList)

                           /* if (bczdqkEntitysId == 0L){
                                bczdqkEntity = entity
                                mPresenter.getBcjcAddZhengdqk(entity)
                            }else{
                                entity.id = bczdqkEntitysId
                                bczdqkEntity = entity
                                mPresenter.getBcjcUpdateZhengdqk(entity)
                            }*/
                        }

                    }
                    //村 下一步
                    /*confirm.setOnClickListener {
                        val entity = BczdqkEntity()
                        entity.zdsj = popAddTdqsEtZdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.gyzdmj = et_gyxzdmj.text.toString()
                        entity.gybcf = et_gyxzdbcf.text.toString()
                        entity.kfjsmj = et_kfjszdmj.text.toString()
                        entity.kfjsbcf = et_kfjszdbcf.text.toString()
                        entity.qtzdmj = et_qtzdmj.text.toString()
                        entity.qtzdbcf = et_qtzdbcf.text.toString()
//                        entity.years = spinner.selectedItem.toString()
                        entity.years = tv_pop_add_zheng.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (popAddTdqsEtZdsj.text.toString().equals("")){
                            ToastUtils.showShort("请选择征地时间")
                        }else if (TdlyUtils.setEditToast(et_zdmj,et_gyxzdmj,et_gyxzdbcf,et_kfjszdmj,et_kfjszdbcf,et_qtzdmj,et_qtzdbcf)){
                        }else{
                            entity.cunname = et_czrname.text.toString()
                            if (bczdqkEntitysId == 0L){
                                mPresenter.getBcjcAddZhengdqk(entity)
                            }else{
                                entity.id = bczdqkEntitysId
                                mPresenter.getBcjcUpdateZhengdqk(entity)
                            }
                        }
                    }*/
                }
            }

            close.setOnClickListener {
                addZhengdqkUpPopu!!.dismiss()
            }
            }
        }
        /*if (AppCache.getInstance().type == 4){
            tv_frag_tdqs_zhandqk_add.text = "操作"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_tdqs_zhandqk_add.visibility = View.GONE
        }else{
            tv_frag_tdqs_zhandqk_add.text = "操作"
        }*/
        //占地情况操作
        tv_frag_tdqs_zhandqk_add.setOnClickListener {
            if (tv_frag_tdqs_zhandqk_add.text.toString().equals("操作")){

            addZhandqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_zhandqk, slv_frag_zjd_tdly)
            val contentView: View = addZhandqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_tdqszd_spr)
            val tv_pop_add_zhan = contentView.findViewById<TextView>(R.id.tv_pop_add_zhan)
            val tv_zdsj = contentView.findViewById<TextView>(R.id.pop_add_tdqszd_et_zdsj)

            val et_zdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_zdmj)
            val et_jcsszdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jcsszdmj)
            val et_jcsszdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jcsszdbcf)
            val et_jdzfzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jdzfzdmj)
            val et_jdzfzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jdzfzdbcf)
            val et_kfqzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_kfqzdmj)
            val et_kfqzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_kfqzdbcf)
            val et_gysyzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_gysyzdmj)
            val et_gysyzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_gysyzdbcf)
            val et_qtzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_qtzdmj)
            val et_qtzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_qtzdbcf)

            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_tdqszd_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_tdqszd_confirm)
            val reject = contentView.findViewById<TextView>(R.id.pop_add_tdqszd_reject)//驳回
            val close = contentView.findViewById<TextView>(R.id.pop_add_tdqszd_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_tdqszd_ell)

            ll_reject.visibility = View.GONE
            tv_zdsj.setOnClickListener {
                TimePickerUtil.initTimePickerViewNy(activity,tv_zdsj.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                    override fun onClick(data: String?) {
                        //时间筛选
                        tv_zdsj.setText(data)
                    }
                })
            }
            CommenPop.backgroundAlpha(0.5f, activity)
            addZhandqkUpPopu!!.showAtLocation(slv_frag_zjd_tdly, Gravity.CENTER, 0, 0)

                tv_pop_add_zhan.setText(tv_frag_tdly_xzsj.addText)//tdlyDetail.get(0)

                for (i in bczhandqkEntitys){
                    if (tv_frag_tdly_xzsj.addText.equals(i.years)){//tdlyDetail.get(0)
                        tv_zdsj.setText(i.zdsj)
                        et_zdmj.setText(i.zdmj)
                        et_jcsszdmj.setText(i.jcmj)
                        et_jcsszdbcf.setText(i.jcbcf)
                        et_jdzfzdmj.setText(i.jdmj)
                        et_jdzfzdbcf.setText(i.jdbcf)
                        et_kfqzdmj.setText(i.kfqmj)
                        et_kfqzdbcf.setText(i.kfqbcf)
                        et_gysyzdmj.setText(i.gysymj)
                        et_gysyzdbcf.setText(i.gysybcf)
                        et_qtzdmj.setText(i.qtmj)
                        et_qtzdbcf.setText(i.qtbcf)
                        if (AppCache.getInstance().type==1){
                            et_czrname.setText(i.qvname)
                        }else if (AppCache.getInstance().type==2){
                            et_czrname.setText(i.qvname)
                        }else if (AppCache.getInstance().type==3){
                            et_czrname.setText(i.zhenname)
                        }else if (AppCache.getInstance().type==4){
                            et_czrname.setText(i.cunname)
                        }
                        bczhandqkEntitysId = i.id
                        bczhandqkEntitysProcess = i.process
                        if (AppCache.getInstance().type == 4){
//                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bczhandqkEntitysProcess){
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
//                                et_czrname.setText(i.zhenname)
                                if (bczhandqkEntitysProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
//                                et_czrname.setText(i.qvname)
                                if (bczhandqkEntitysProcess == 3){
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
                        tv_zdsj.setText("")
                        et_zdmj.setText("")
                        et_jcsszdmj.setText("")
                        et_jcsszdbcf.setText("")
                        et_jdzfzdmj.setText("")
                        et_jdzfzdbcf.setText("")
                        et_kfqzdmj.setText("")
                        et_kfqzdbcf.setText("")
                        et_gysyzdmj.setText("")
                        et_gysyzdbcf.setText("")
                        et_qtzdmj.setText("")
                        et_qtzdbcf.setText("")
                        bczhandqkEntitysId = 0L
                        bczhandqkEntitysProcess = 0
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

            when(AppCache.getInstance().type){
                2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {
                        val entity = BczhandqkEntity()
                        entity.zdsj = tv_zdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.jcmj = et_jcsszdmj.text.toString()
                        entity.jcbcf = et_jcsszdbcf.text.toString()
                        entity.jdmj = et_jdzfzdmj.text.toString()
                        entity.jdbcf = et_jdzfzdbcf.text.toString()
                        entity.kfqmj = et_kfqzdmj.text.toString()
                        entity.kfqbcf = et_kfqzdbcf.text.toString()
                        entity.gysymj = et_gysyzdmj.text.toString()
                        entity.gysybcf = et_gysyzdbcf.text.toString()
                        entity.qtmj = et_qtzdmj.text.toString()
                        entity.qtbcf = et_qtzdbcf.text.toString()
                        entity.years = tv_pop_add_zhan.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bczhandqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateZhandqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        val entity = BczhandqkEntity()
                        entity.zdsj = tv_zdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.jcmj = et_jcsszdmj.text.toString()
                        entity.jcbcf = et_jcsszdbcf.text.toString()
                        entity.jdmj = et_jdzfzdmj.text.toString()
                        entity.jdbcf = et_jdzfzdbcf.text.toString()
                        entity.kfqmj = et_kfqzdmj.text.toString()
                        entity.kfqbcf = et_kfqzdbcf.text.toString()
                        entity.gysymj = et_gysyzdmj.text.toString()
                        entity.gysybcf = et_gysyzdbcf.text.toString()
                        entity.qtmj = et_qtzdmj.text.toString()
                        entity.qtbcf = et_qtzdbcf.text.toString()
                        entity.years = tv_pop_add_zhan.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bczhandqkEntitysId
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
                            mPresenter.getBcjcUpdateZhandqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        val entity = BczhandqkEntity()
                        entity.zdsj = tv_zdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.jcmj = et_jcsszdmj.text.toString()
                        entity.jcbcf = et_jcsszdbcf.text.toString()
                        entity.jdmj = et_jdzfzdmj.text.toString()
                        entity.jdbcf = et_jdzfzdbcf.text.toString()
                        entity.kfqmj = et_kfqzdmj.text.toString()
                        entity.kfqbcf = et_kfqzdbcf.text.toString()
                        entity.gysymj = et_gysyzdmj.text.toString()
                        entity.gysybcf = et_gysyzdbcf.text.toString()
                        entity.qtmj = et_qtzdmj.text.toString()
                        entity.qtbcf = et_qtzdbcf.text.toString()
                        entity.years = tv_pop_add_zhan.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bczhandqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateZhandqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        val entity = BczhandqkEntity()
                        entity.zdsj = tv_zdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.jcmj = et_jcsszdmj.text.toString()
                        entity.jcbcf = et_jcsszdbcf.text.toString()
                        entity.jdmj = et_jdzfzdmj.text.toString()
                        entity.jdbcf = et_jdzfzdbcf.text.toString()
                        entity.kfqmj = et_kfqzdmj.text.toString()
                        entity.kfqbcf = et_kfqzdbcf.text.toString()
                        entity.gysymj = et_gysyzdmj.text.toString()
                        entity.gysybcf = et_gysyzdbcf.text.toString()
                        entity.qtmj = et_qtzdmj.text.toString()
                        entity.qtbcf = et_qtzdbcf.text.toString()
                        entity.years = tv_pop_add_zhan.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bczhandqkEntitysId
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
                            mPresenter.getBcjcUpdateZhandqk(entity)
                        }
                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        val entity = BczhandqkEntity()
                        entity.zdsj = tv_zdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.jcmj = et_jcsszdmj.text.toString()
                        entity.jcbcf = et_jcsszdbcf.text.toString()
                        entity.jdmj = et_jdzfzdmj.text.toString()
                        entity.jdbcf = et_jdzfzdbcf.text.toString()
                        entity.kfqmj = et_kfqzdmj.text.toString()
                        entity.kfqbcf = et_kfqzdbcf.text.toString()
                        entity.gysymj = et_gysyzdmj.text.toString()
                        entity.gysybcf = et_gysyzdbcf.text.toString()
                        entity.qtmj = et_qtzdmj.text.toString()
                        entity.qtbcf = et_qtzdbcf.text.toString()
                        entity.years = tv_pop_add_zhan.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (tv_zdsj.text.toString().equals("")){
                            ToastUtils.showShort("请选择占地时间")
                        }else if (TdlyUtils.setEditToast(et_zdmj,et_jcsszdmj,et_jcsszdbcf,et_jdzfzdmj,et_jdzfzdbcf,et_kfqzdmj
                                        ,et_kfqzdbcf,et_gysyzdmj,et_gysyzdbcf,et_qtzdmj,et_qtzdbcf)){
                        }else{
                            entity.cunname = et_czrname.text.toString()
                            if (bczhandqkEntitysId == 0L){
                                bczhandqkEntity = entity
                                mPresenter.getBcjcAddZhandqk(entity)
                            }else{
                                entity.id = bczhandqkEntitysId
                                bczhandqkEntity = entity
                                mPresenter.getBcjcUpdateZhandqk(entity)
                            }
                        }

                    }
                    //村 下一步
                    confirm.setOnClickListener {
                        val entity = BczhandqkEntity()
                        entity.zdsj = tv_zdsj.text.toString()
                        entity.zdmj = et_zdmj.text.toString()
                        entity.jcmj = et_jcsszdmj.text.toString()
                        entity.jcbcf = et_jcsszdbcf.text.toString()
                        entity.jdmj = et_jdzfzdmj.text.toString()
                        entity.jdbcf = et_jdzfzdbcf.text.toString()
                        entity.kfqmj = et_kfqzdmj.text.toString()
                        entity.kfqbcf = et_kfqzdbcf.text.toString()
                        entity.gysymj = et_gysyzdmj.text.toString()
                        entity.gysybcf = et_gysyzdbcf.text.toString()
                        entity.qtmj = et_qtzdmj.text.toString()
                        entity.qtbcf = et_qtzdbcf.text.toString()
                        entity.years = tv_pop_add_zhan.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (tv_zdsj.text.toString().equals("")){
                            ToastUtils.showShort("请选择占地时间")
                        }else if (TdlyUtils.setEditToast(et_zdmj,et_jcsszdmj,et_jcsszdbcf,et_jdzfzdmj,et_jdzfzdbcf,et_kfqzdmj
                                        ,et_kfqzdbcf,et_gysyzdmj,et_gysyzdbcf,et_qtzdmj,et_qtzdbcf)){
                        }else{
                            entity.cunname = et_czrname.text.toString()
                            if (bczhandqkEntitysId == 0L){
                                mPresenter.getBcjcAddZhandqk(entity)
                            }else{
                                entity.id = bczhandqkEntitysId
                                mPresenter.getBcjcUpdateZhandqk(entity)
                            }
                        }
                    }
                }
            }


            close.setOnClickListener {
                addZhandqkUpPopu!!.dismiss()
            }
            }
        }

        //提交上报or通过
        bt_frag_zjd_tdly_xyb.setOnClickListener {
            when(AppCache.getInstance().type){
                2->{
                    uploadOrRejectData(1,4)
                }
                3->{
                    uploadOrRejectData(1,3)
                }
                4->{
                    if (tylySaveType == 0){
                        ToastUtils.showShort("请先保存村集体土地资源情况表")
                    }else{
                        uploadOrRejectData(1,2)
                    }
                }
            }
        }

        //驳回
        bt_frag_zjd_tdly_bh.setOnClickListener {
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


    //土地利用保存
    fun tdlySave(){
        /*val arrayList = ArrayList<String>()
            arrayList.addAll(tdlyDetail)
            PopuPointUtils.initPopuSelectYear(activity,ll_zjd_tdly_year,arrayList,object: PopuPointUtils.OnSelectYearLinster{
                override fun onClick(years: String) {*/
        val bcTdlyEntitys = BcTdlyEntity()

        bcTdlyEntitys.code = AppCache.getInstance().code
        bcTdlyEntitys.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()

        bcTdlyEntitys.nydmj = et_frag_tdly_nyd_zdmj.text.toString()//农用地-占地面积
        bcTdlyEntitys.nydqd = et_frag_tdly_nyd_remarkqqqd.text.toString()//农用地-确权确地面积
        bcTdlyEntitys.nydql = et_frag_tdly_nyd_remarkqqql.text.toString()//农用地-确权确利面积
        bcTdlyEntitys.nydqg = et_frag_tdly_nyd_remarkqqqg.text.toString()//农用地-确权确股面积
//                    bcTdlyEntity.gydmj = et_frag_tdly_nyd_remark.text.toString()//农用地-备注
        bcTdlyEntitys.gdmj = et_frag_tdly_gd_zdmj.text.toString()//耕地-占地面积
        bcTdlyEntitys.gdzzmj = et_frag_tdly_gd_syqkzz.text.toString()//耕地-自种面积
        bcTdlyEntitys.gdlzmj = et_frag_tdly_gd_syqklz.text.toString()//耕地-流转面积
        bcTdlyEntitys.gdqtmj = et_frag_tdly_gd_syqkqt.text.toString()//耕地-其他面积
        bcTdlyEntitys.gdjg = et_frag_tdly_gd_pjlz.text.toString()//耕地-平均流转
        bcTdlyEntitys.gdbz = et_frag_tdly_gd_remark.text.toString()//耕地-备注
//                    bcTdlyEntity.gdqd = et_frag_tdly_gd_remarkqqqd.text.toString()//耕地-确权确地面积
//                    bcTdlyEntity.gdql = et_frag_tdly_gd_remarkqqql.text.toString()//耕地-确权确利面积
//                    bcTdlyEntity.gdqg = et_frag_tdly_gd_remarkqqqg.text.toString()//耕地-确权确股面积
        bcTdlyEntitys.ydmj = et_frag_tdly_yd_zdmj.text.toString()//园地-占地面积
        bcTdlyEntitys.ydzzmj = et_frag_tdly_yd_syqkzz.text.toString()//园地-自种面积
        bcTdlyEntitys.ydlzmj = et_frag_tdly_yd_syqklz.text.toString()//园地-流转面积
        bcTdlyEntitys.ydqtmj = et_frag_tdly_yd_syqkqt.text.toString()//园地-其他面积
        bcTdlyEntitys.ydjg = et_frag_tdly_yd_pjlz.text.toString()//园地-平均流转
        bcTdlyEntitys.ydbz = et_frag_tdly_yd_remark.text.toString()//园地-备注
//                    bcTdlyEntity.ydqd = et_frag_tdly_yd_remarkqqqd.text.toString()//园地-确权确地面积
//                    bcTdlyEntity.ydql = et_frag_tdly_yd_remarkqqql.text.toString()//园地-确权确利面积
//                    bcTdlyEntity.ydqg = et_frag_tdly_yd_remarkqqqg.text.toString()//园地-确权确股面积
        bcTdlyEntitys.ldmj = et_frag_tdly_ld_zdmj.text.toString()//林地-占地面积(亩)
        bcTdlyEntitys.ldwjy = et_frag_tdly_ld_syqkwjy.text.toString()//林地-无经营面积
        bcTdlyEntitys.ldlxzyz = et_frag_tdly_ld_syqklxzyz.text.toString()//林地-林下种养殖面积
        bcTdlyEntitys.ldjg = et_frag_tdly_ld_pjlz.text.toString()//林地-平均流转
        bcTdlyEntitys.ldbz = et_frag_tdly_ld_remark.text.toString()//林地-备注
//                    bcTdlyEntity.ldqd = et_frag_tdly_ld_remarkqqqd.text.toString()//林地-确权确地面积
//                    bcTdlyEntity.ldql = et_frag_tdly_ld_remarkqqql.text.toString()//林地-确权确利面积
//                    bcTdlyEntity.ldqg = et_frag_tdly_ld_remarkqqqg.text.toString()//林地-确权确股面积
        bcTdlyEntitys.nyjsmj = et_frag_tdly_nyssjs_zdmj.text.toString() //农业设施建设-面积
//        bcTdlyEntitys.nyjssyqk = et_frag_tdly_nyssjs_syqk.text.toString() //农业设施建设-使用情况
//                    bcTdlyEntity.nyjszzmj = et_frag_tdly_nyssjs_syqkzz.text.toString()//农业设施建设-自种面积
//                    bcTdlyEntity.nyjslzmj = et_frag_tdly_nyssjs_syqklz.text.toString()//农业设施建设-流转面积
//                    bcTdlyEntity.nyjsqtmj = et_frag_tdly_nyssjs_syqkqt.text.toString()//农业设施建设-其他面积
//        bcTdlyEntitys.nyjsjg = et_frag_tdly_nyssjs_pjlz.text.toString()//农业设施建设-平均流转
        bcTdlyEntitys.nyjsbz = et_frag_tdly_nyssjs_remark.text.toString()//农业设施建设-备注
        bcTdlyEntitys.qtnydmj = et_frag_tdly_qtnyd_zdmj.text.toString()//其他农用地-面积
//        bcTdlyEntitys.qtnydsyqk = et_frag_tdly_qtnyd_syqk.text.toString()//其他农用地-使用情况
//        bcTdlyEntitys.qtnydjg = et_frag_tdly_qtnyd_pjlz.text.toString()//其他农用地-平均流转
        bcTdlyEntitys.qtnydbz = et_frag_tdly_qtnyd_remark.text.toString()//其他农用地-备注
        bcTdlyEntitys.jsydmj = et_frag_tdly_jsyd_zdmj.text.toString()//建设用地-占地面积
//                    bcTdlyEntity.jsydsyqk = et_frag_tdly_jsyd_syqk.text.toString()//建设用地-使用情况
//                    bcTdlyEntity.jsydjg = et_frag_tdly_jsyd_qjlz.text.toString()//建设用地-平均流转
        bcTdlyEntitys.jsydpf = et_frag_tdly_jsyd_remarkghpf.text.toString()//建设用地-规划批复使用面积
        bcTdlyEntitys.jzydmj = et_frag_tdly_jzyd_zdmj.text.toString()//居住用地-占地面积
        bcTdlyEntitys.jzydzz = et_frag_tdly_jzyd_syqkzz.text.toString()//居住用地-自住
        bcTdlyEntitys.jzydcz = et_frag_tdly_jzyd_syqklz.text.toString()//居住用地-出租
        bcTdlyEntitys.jzydqt = et_frag_tdly_jzyd_syqkqt.text.toString()//居住用地-其他
        bcTdlyEntitys.jzydjg = et_frag_tdly_jzyd_pjlz.text.toString()//居住用地-平均流转
        bcTdlyEntitys.jzydms = et_frag_tdly_jzyd_remarkqzgj.text.toString()//居住用地-民宿
        bcTdlyEntitys.ggydmj = et_frag_tdly_gggl_zdmj.text.toString()//公共管理与公共服务用地-占地面积
//        bcTdlyEntitys.ggydsyqk = et_frag_tdly_gggl_syqk.text.toString()//公共管理与公共服务用地-使用情况
        bcTdlyEntitys.ggydzz = et_frag_tdly_gggl_syqkzy.text.toString()//公共管理与公共服务用地-使用情况
        bcTdlyEntitys.ggydcz = et_frag_tdly_gggl_syqkcz.text.toString()//公共管理与公共服务用地-使用情况
        bcTdlyEntitys.ggydqt = et_frag_tdly_gggl_syqkqt.text.toString()//公共管理与公共服务用地-使用情况
        bcTdlyEntitys.ggydlzjg = et_frag_tdly_gggl_pjlz.text.toString()//公共管理与公共服务用地-使用情况
        bcTdlyEntitys.ggydbz = et_frag_tdly_gggl_remark.text.toString()//公共管理与公共服务用地-备注
        bcTdlyEntitys.jtydmj = et_frag_tdly_jtjyjs_zdmj.text.toString()//集体经营性建设用地-占地面积
//        bcTdlyEntitys.jtydsyqk = et_frag_tdly_jtjyjs_syqk.text.toString()//集体经营性建设用地-使用情况
        bcTdlyEntitys.jtydzz = et_frag_tdly_jtjyjs_syqkzy.text.toString()//集体经营性建设用地-使用情况自用
        bcTdlyEntitys.jtydcz = et_frag_tdly_jtjyjs_syqkcz.text.toString()//集体经营性建设用地-使用情况出租
        bcTdlyEntitys.jtydqt = et_frag_tdly_jtjyjs_syqkqt.text.toString()//集体经营性建设用地-使用情况其他
        bcTdlyEntitys.jtydjg = et_frag_tdly_jtjyjs_pjlz.text.toString()//集体经营性建设用地-平均流转
        bcTdlyEntitys.jtydbz = et_frag_tdly_jtjyjs_remark.text.toString()//集体经营性建设用地-备注
        bcTdlyEntitys.tdzmj = et_frag_tdly_tdzmj_zdmj.text.toString()//土地总面积
        bcTdlyEntitys.gymj = et_frag_tdly_tdzmj_gyd.text.toString()//国有面积
//        bcTdlyEntitys.yjjbnt = et_frag_tdly_tdzmj_yjjbnt.text.toString()//基本永久农田
        bcTdlyEntitys.pyzl = et_frag_tdly_tdzmj_pyzl.text.toString()//平原造林
        bcTdlyEntitys.jtjy = et_frag_tdly_tdzmj_jtjy.text.toString()//家庭经营
        bcTdlyEntitys.jtjy1 = et_frag_tdly_tdzmj_jtjy1.text.toString()//集体经营
        bcTdlyEntitys.dhcb = et_frag_tdly_tdzmj_dhcb.text.toString()//大户承包
        bcTdlyEntitys.dwzl = et_frag_tdly_tdzmj_dwzl.text.toString()//对外租赁
        bcTdlyEntitys.qtmj = et_frag_tdly_tdzmj_qt.text.toString()//其他面积
//                    bcTdlyEntity.zjmj = et_frag_tdly_tdzmj_total.text.toString()//总计面积
        bcTdlyEntitys.process = 1
        bcTdlyEntitys.years = tv_frag_tdly_xzsj.addText
//                    tv_frag_tdly_xzsj.setText(years)
//                    year = years
        if (bcTdlyEntityId == 0L){
            bcTdlyEntity = bcTdlyEntitys
            mPresenter.getBcjcTdlySave(bcTdlyEntitys)
        }else{
            bcTdlyEntitys.id = bcTdlyEntityId
            //修改接口
            bcTdlyEntity = bcTdlyEntitys
            mPresenter.getBcjcTdlyUpdate(bcTdlyEntitys)
        }

        /*}
    })*/
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
                var iszzd = false

                for (i in bczdqkEntityList){
                    if (i.years.equals(tv_frag_tdly_xzsj.addText)){
                        if (TdlyUtils.setObjectToast(i.zdsj,i.zdmj,i.gyzdmj,i.gybcf,i.kfjsmj,i.kfjsbcf,i.qtzdmj,i.qtzdbcf,i.zhandmj,i.jcmj,i.jcbcf,i.jdmj,i.jdbcf
                                        ,i.kfqmj,i.kfqbcf,i.gysymj,i.gysybcf,i.qtmj,i.qtbcf
                                        )){
                            iszzd = true
                        }
                    }
                }
                if (iszzd){
                    ToastUtils.showShort("请完善征占地表")
                }else if (TdlyUtils.setObjectToast(bcTdlyEntity.nydmj,bcTdlyEntity.nydqd,bcTdlyEntity.nydql,bcTdlyEntity.nydqg,bcTdlyEntity.gdmj
                                ,bcTdlyEntity.gdzzmj ,bcTdlyEntity.gdlzmj ,bcTdlyEntity.gdjg,bcTdlyEntity.gdbz,bcTdlyEntity.ydmj,bcTdlyEntity.ydzzmj
                                ,bcTdlyEntity.ydlzmj ,bcTdlyEntity.ydqtmj,bcTdlyEntity.ydjg,bcTdlyEntity.ydbz
                                ,bcTdlyEntity.ldmj,bcTdlyEntity.ldwjy,bcTdlyEntity.ldjg,bcTdlyEntity.ldbz,bcTdlyEntity.nyjsmj,bcTdlyEntity.nyjsbz
                                ,bcTdlyEntity.qtnydmj,bcTdlyEntity.qtnydbz,bcTdlyEntity.jsydmj
                                ,bcTdlyEntity.jsydpf ,bcTdlyEntity.jzydmj,bcTdlyEntity.jzydzz,bcTdlyEntity.jzydcz,bcTdlyEntity.jzydqt,bcTdlyEntity.jzydjg
                                ,bcTdlyEntity.jzydms,bcTdlyEntity.ggydmj,bcTdlyEntity.ggydzz,bcTdlyEntity.ggydcz,bcTdlyEntity.ggydqt,bcTdlyEntity.ggydlzjg
                                ,bcTdlyEntity.ggydbz ,bcTdlyEntity.jtydmj,bcTdlyEntity.jtydzz,bcTdlyEntity.jtydcz,bcTdlyEntity.jtydqt,bcTdlyEntity.jtydjg
                                ,bcTdlyEntity.jtydbz,bcTdlyEntity.tdzmj,bcTdlyEntity.gymj
                                ,bcTdlyEntity.pyzl,bcTdlyEntity.jtjy,bcTdlyEntity.jtjy1,bcTdlyEntity.dhcb,bcTdlyEntity.dwzl,bcTdlyEntity.qtmj)){
                    ToastUtils.showShort("请完善土地利用表")
                }else {
                    when(AppCache.getInstance().type){
                        2->{
                            bcTdlyEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                            for (i in bczdqkEntityList){
                                i.qvname = et_pop_bcjc_reject_clr.text.toString()
                            }
//                        bczdqkEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                            bczhandqkEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                            bcTdlyEntity.id = bcTdlyEntityId
//                        bczdqkEntity.id = bczdqkEntitysId
                            bczhandqkEntity.id = bczhandqkEntitysId
                            if (type == 1){
                                bcTdlyEntity.process = process

                                for (i in bczdqkEntityList){
                                    i.process = process
                                }
                                bczhandqkEntity.process = process
                                mPresenter.getBcjcTdlyUpdate(bcTdlyEntity)
                                mPresenter.getBcjcUpdateZhengdqk(bczdqkEntityList)
//                            mPresenter.getBcjcUpdateZhandqk(bczhandqkEntity)
                            }else{
                                val bcrejectedEntity = BcrejectedEntity()
                                bcrejectedEntity.content = pop_bcjc_reject_et_reject!!.text.toString()
                                bcTdlyEntity.process = 1
                                for (i in bczdqkEntityList){
                                    i .process = 1
                                    i.bcrejectedEntities.add(bcrejectedEntity)
                                }
//                            bczdqkEntity.process = 1
                                bczhandqkEntity.process = 1
                                bcTdlyEntity.bcrejectedEntities.add(bcrejectedEntity)
                                bczhandqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                mPresenter.getBcjcTdlyUpdate(bcTdlyEntity)
                                mPresenter.getBcjcUpdateZhengdqk(bczdqkEntityList)
//                            mPresenter.getBcjcUpdateZhandqk(bczhandqkEntity)
                            }
                        }
                        3->{
                            bcTdlyEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                            for (i in bczdqkEntityList){
                                i.zhenname = et_pop_bcjc_reject_clr.text.toString()
                            }
                            bczhandqkEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                            bcTdlyEntity.id = bcTdlyEntityId
//                        bczdqkEntity.id = bczdqkEntitysId
                            bczhandqkEntity.id = bczhandqkEntitysId
                            if (type == 1){
                                bcTdlyEntity.process = process
                                for (i in bczdqkEntityList){
                                    i.process = process
                                }
                                bczhandqkEntity.process = process
                                mPresenter.getBcjcTdlyUpdate(bcTdlyEntity)
                                mPresenter.getBcjcUpdateZhengdqk(bczdqkEntityList)
//                            mPresenter.getBcjcUpdateZhandqk(bczhandqkEntity)
                            }else{
                                val bcrejectedEntity = BcrejectedEntity()
                                bcrejectedEntity.content = pop_bcjc_reject_et_reject!!.text.toString()
                                bcTdlyEntity.process = 1
                                for (i in bczdqkEntityList){
                                    i.process = 1
                                    i.bcrejectedEntities.add(bcrejectedEntity)
                                }
                                bczhandqkEntity.process = 1
                                bcTdlyEntity.bcrejectedEntities.add(bcrejectedEntity)
                                bczhandqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                mPresenter.getBcjcTdlyUpdate(bcTdlyEntity)
                                mPresenter.getBcjcUpdateZhengdqk(bczdqkEntityList)
//                            mPresenter.getBcjcUpdateZhandqk(bczhandqkEntity)
                            }
                        }
                        4->{
                            tylySaveType = 0
                            bcTdlyEntity.process = process
                            for (i in bczdqkEntityList){
                                i.process = process
                                i.cunname = et_pop_bcjc_reject_clr.text.toString()
                            }
//                        bczdqkEntity.process = process
                            bczhandqkEntity.process = process
                            bcTdlyEntity.id = bcTdlyEntityId
//                        bczdqkEntity.id = bczdqkEntitysId
                            bczhandqkEntity.id = bczhandqkEntitysId
                            bcTdlyEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                            bczhandqkEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                            if (bcTdlyEntityId == 0L){
                                mPresenter.getBcjcTdlySave(bcTdlyEntity)
                            }else{
                                mPresenter.getBcjcTdlyUpdate(bcTdlyEntity)
                            }
                            if (bczdqkEntitysId==0L){
                                mPresenter.getBcjcAddZhengdqk(bczdqkEntityList)
                            }else{
                                mPresenter.getBcjcUpdateZhengdqk(bczdqkEntityList)
                            }
                            /*if (bczhandqkEntitysId==0L){
                                mPresenter.getBcjcAddZhandqk(bczhandqkEntity)
                            }else{
                                mPresenter.getBcjcUpdateZhandqk(bczhandqkEntity)
                            }*/
                        }
                    }
                }
            }


        }
    }


    private fun onClickUpdate() {
        if (tv_frag_zjd_tdly_add.text.toString().equals("取消")) {
            ll_frag_zjd_tdly_qkjcb.visibility = View.GONE//显示按钮
            tv_frag_zjd_tdly_add.setText("操作")
    //                tv_frag_zjd_tdly_zg.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgnyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zggd.visibility = View.VISIBLE
            tv_frag_tdly_zgyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgld.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgssnyyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgqtnyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgjsyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgjzyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgggglfwyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgjtjyjsyd.visibility = View.VISIBLE
            tv_frag_zjd_tdly_zgtdzmj.visibility = View.VISIBLE
            but_frag_zjd_tdly_save.visibility = View.GONE//保存
            but_frag_zjd_tdly_xyb.visibility = View.GONE//下一步
            but_frag_zjd_tdly_bh.visibility = View.GONE//驳回

        } else {
            ll_frag_zjd_tdly_qkjcb.visibility = View.VISIBLE//显示按钮
            tv_frag_zjd_tdly_add.setText("取消")
            isQq = 1
    //                tv_frag_zjd_tdly_zg.visibility = View.GONE
            if (process == 1||process == 0) {
                tv_frag_zjd_tdly_zgnyd.visibility = View.GONE
                tv_frag_zjd_tdly_zggd.visibility = View.GONE
                tv_frag_tdly_zgyd.visibility = View.GONE
                tv_frag_zjd_tdly_zgld.visibility = View.GONE
                tv_frag_zjd_tdly_zgssnyyd.visibility = View.GONE
                tv_frag_zjd_tdly_zgqtnyd.visibility = View.GONE
                tv_frag_zjd_tdly_zgjsyd.visibility = View.GONE
                tv_frag_zjd_tdly_zgjzyd.visibility = View.GONE
                tv_frag_zjd_tdly_zgggglfwyd.visibility = View.GONE
                tv_frag_zjd_tdly_zgjtjyjsyd.visibility = View.GONE
                tv_frag_zjd_tdly_zgtdzmj.visibility = View.GONE
                but_frag_zjd_tdly_save.visibility = View.VISIBLE
//                but_frag_zjd_tdly_xyb.visibility = View.VISIBLE
            } else if (process == 2) {
//                but_frag_zjd_tdly_xyb.visibility = View.VISIBLE
//                but_frag_zjd_tdly_bh.visibility = View.VISIBLE
            } else if (process == 3) {
//                but_frag_zjd_tdly_xyb.visibility = View.VISIBLE
//                but_frag_zjd_tdly_bh.visibility = View.VISIBLE
            }

    //                but_frag_zjd_tdly_save.visibility = View.VISIBLE

        }
    }

    override fun onPolygonTx(list: MutableList<TdlyVO>) {
        diaoyong()
        if (list.size > 0) {

            supl_frag_zjdgl1!!.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl1!!.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            if (list[0] != null) {
                val ylDatas = ArrayList<Float>()
                val ylLables = ArrayList<String>()
                for (i in list){
                    if (i.lx.equals("")){
                        ylLables.add(i.dlmc)
                    }else{
                        ylLables.add(i.lx)
                    }
                    ylDatas.add(i.area1.toFloat())
                }
                /* ylDatas.add(list[0].jsydmj.toInt())
                 ylLables.add("建设用地")
                 ylDatas.add(list[0].nydmj.toInt())
                 ylLables.add("农用地")
                 ylDatas.add(list[0].tjsmj.toInt())
                 ylLables.add("特交水")
                 ylDatas.add(list[0].wlydmj.toInt())
                 ylLables.add("未利用")*/

                PieTdxgUtil.initPie(pie_frag_zjd_tdly!!, "用地状况", ylDatas, ylLables)
            }
        }
    }
    override fun onPolygonDetailTx(tdlyDetail: List<TdlyVO>) {//TdlyListBean
//        rlv_frag_zjd_tdly
        if (page == 1){
            flowInfoList.clear()
        }
        var firstVisibleItem = 0
        if (linearLayoutManager!=null){
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        flowInfoList.addAll(tdlyDetail)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_frag_zjd_tdly.layoutManager = linearLayoutManager
        val zlglAdapter = TxDetailAdapter(R.layout.item_fwqs_detail, flowInfoList)
        rlv_frag_zjd_tdly.adapter =zlglAdapter
        zlglAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val result = adapter?.getItem(position) as TdlyVO
//                onZjdTdlyLister?.onClickTdly(item,position)
                val tvDatas = ArrayList<TextViewsEntity>()
                if (!result.xzqmc.equals(""))
                    tvDatas.add(TextViewsEntity("村名:", result.xzqmc))
                if (!result.dlmc.equals(""))
                    tvDatas.add(TextViewsEntity("地类名称:", result.dlmc))
                if (!result.dlbm.equals(""))
                    tvDatas.add(TextViewsEntity("地类编码:", result.dlbm))
                if (!result.qsdwmc.equals(""))
                    tvDatas.add(TextViewsEntity("权属名称:", result.qsdwmc))
                if (!result.qsxz.equals(""))
                    tvDatas.add(TextViewsEntity("权属性质:", result.qsxz))
                if (result.area.compareTo(BigDecimal(10000))> 0){
                    tvDatas.add(TextViewsEntity("面积:", result.area.divide(BigDecimal(10000),2, RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                }else{
                    tvDatas.add(TextViewsEntity("面积:", result.area.toInt().toString()+"㎡"))//(公顷).setScale(2,RoundingMode.HALF_UP)
                }
                if (!result.dlmc.equals("")){//如果有村名显示弹出框
                    ZjdglFragment.kuangGeomentLine(result.geometry)
                    PopuPointUtils.startPopuPoint(supl_frag_zjdgl1,tvDatas,"", arrayOf(0,1,2,3,4,5))
                }
               /* clearAllMarker()
                aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(item.center, 19f))
                aMap?.addMarker(MarkerOptions().position(item.center))
                this@MainActivity.position = position
                botDialog?.dismiss()
                queryByPoint(item.center)*/
            }

        })
        rlv_frag_zjd_tdly.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&flowInfoList.size==page*limit) {
                    if (flowInfoList.size !=0 && flowInfoList.size%limit == 0){
                        page++
                        mPresenter.getTxPolygonDetail(points,limit,page)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_frag_zjd_tdly.scrollToPosition(firstVisibleItem+1)//(page-1)*limit
        }
    }
    override fun onPolygonSkt(list: MutableList<YztSktVO>) {
        diaoyong()
        if (list.size > 0) {
            supl_frag_zjdgl1!!.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl1!!.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            if (list[0] != null) {
                val ylDatas = ArrayList<Float>()
                val ylLables = ArrayList<String>()
                for (i in list){
                    ylLables.add(i.skt)
                    ylDatas.add(i.area1.toFloat())
                }
                /* ylDatas.add(list[0].jsydmj.toInt())
                 ylLables.add("建设用地")
                 ylDatas.add(list[0].nydmj.toInt())
                 ylLables.add("农用地")
                 ylDatas.add(list[0].tjsmj.toInt())
                 ylLables.add("特交水")
                 ylDatas.add(list[0].wlydmj.toInt())
                 ylLables.add("未利用")*/

                PieTdxgUtil.initPie(pie_frag_zjd_tdly!!, "三块田状况", ylDatas, ylLables)
            }
        }
    }
    override fun onPolygonDetailSkt(tdlyDetail: List<YztSktVO>) {
        if (page == 1){
            sktflowInfoList.clear()
        }
        var firstVisibleItem = 0
        if (linearLayoutManager!=null){
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        sktflowInfoList.addAll(tdlyDetail)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_frag_zjd_tdly.layoutManager = linearLayoutManager
        val zlglAdapter = SktDetailAdapter(R.layout.item_fwqs_detail, sktflowInfoList)
        rlv_frag_zjd_tdly.adapter =zlglAdapter
        zlglAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val tdlyEntity = adapter?.getItem(position) as YztSktVO
//                onZjdTdlyLister?.onClickYztSkt(item,position)
                val tvDatas = ArrayList<TextViewsEntity>()
                if (tdlyEntity != null) {
                    if (!tdlyEntity.xzqmc.equals(""))
                        tvDatas.add(TextViewsEntity("村名:", tdlyEntity.xzqmc))
                    if (!tdlyEntity.skt.equals(""))
                        tvDatas.add(TextViewsEntity("名称:", tdlyEntity.skt))//三块田
                    if (tdlyEntity.area.compareTo(BigDecimal(10000))> 0){
                        tvDatas.add(TextViewsEntity("面积:", tdlyEntity.area.divide(BigDecimal(10000),2,RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                    }else{
                        tvDatas.add(TextViewsEntity("面积:", tdlyEntity.area.toInt().toString()+"㎡"))//(公顷).setScale(2,RoundingMode.HALF_UP)
                    }
                    /* tvDatas.add(TextViewsEntity("地类编码:", tdlyEntity.dlbm))
                     tvDatas.add(TextViewsEntity("地类名称:", tdlyEntity.dlmc))
                     tvDatas.add(TextViewsEntity("一级类:", tdlyEntity.yjfl))
                     tvDatas.add(TextViewsEntity("二级类:", tdlyEntity.ejfl))*/
//            tvDatas.add(TextViewsEntity("权属单位名称:", result.qsdwmc))
                }
                if (!tdlyEntity.skt.equals("")){//如果有村名显示弹出框
                    ZjdglFragment.kuangGeomentLine(tdlyEntity.geometry)
                    PopuPointUtils.startPopuPoint(supl_frag_zjdgl1,tvDatas,tdlyEntity.skt, arrayOf(0,1,2,3,4))//,5,6
                }
                /* clearAllMarker()
                 aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(item.center, 19f))
                 aMap?.addMarker(MarkerOptions().position(item.center))
                 this@MainActivity.position = position
                 botDialog?.dismiss()
                 queryByPoint(item.center)*/
            }
        })
        rlv_frag_zjd_tdly.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&sktflowInfoList.size==page*limit) {
                    if (sktflowInfoList.size !=0 && sktflowInfoList.size%limit == 0){
                        page++
                        mPresenter.getSktPolygonDetail(points,limit,page)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_frag_zjd_tdly.scrollToPosition(firstVisibleItem+1)//(page-1)*limit
        }
    }
    override fun onPolygonQs(list: MutableList<QsVO>) {
        diaoyong()
        if (list.size > 0) {

            supl_frag_zjdgl1!!.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl1!!.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            if (list[0] != null) {
                val ylDatas = ArrayList<Float>()
                val ylLables = ArrayList<String>()
                for (i in list){
                    ylLables.add(i.qs)
                    ylDatas.add(i.area1.toFloat())
                }
                /* ylDatas.add(list[0].jsydmj.toInt())
                 ylLables.add("建设用地")
                 ylDatas.add(list[0].nydmj.toInt())
                 ylLables.add("农用地")
                 ylDatas.add(list[0].tjsmj.toInt())
                 ylLables.add("特交水")
                 ylDatas.add(list[0].wlydmj.toInt())
                 ylLables.add("未利用")*/

                PieTdxgUtil.initPie(pie_frag_zjd_tdly!!, "权属状况", ylDatas, ylLables)
            }
        }
    }
    override fun onPolygonDetailQs(tdlyDetail: List<QsVO>) {
        if (page == 1){
            qsflowInfoList.clear()
        }
        var firstVisibleItem = 0
        if (linearLayoutManager!=null){
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        qsflowInfoList.addAll(tdlyDetail)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_frag_zjd_tdly.layoutManager = linearLayoutManager
        val zlglAdapter = QsDetailAdapter(R.layout.item_fwqs_detail, qsflowInfoList)
        rlv_frag_zjd_tdly.adapter =zlglAdapter
        zlglAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val result = adapter?.getItem(position) as QsVO
//                onZjdTdlyLister?.onClickQs(item,position)
                val tvDatas = ArrayList<TextViewsEntity>()
                if (result != null) {
                    if (!result.xzqmc.equals(""))
                        tvDatas.add(TextViewsEntity("村名:", result.xzqmc))
                    if (!result.dlmc.equals(""))
                        tvDatas.add(TextViewsEntity("地类名称:", result.dlmc))
                    if (!result.dlbm.equals(""))
                        tvDatas.add(TextViewsEntity("地类编码:", result.dlbm))
                    if (!result.qsdwmc.equals(""))
                        tvDatas.add(TextViewsEntity("权属名称:", result.qsdwmc))
                    if (!result.qsxz.equals(""))
                        tvDatas.add(TextViewsEntity("权属性质:", result.qsxz))
                    if (result.area.compareTo(BigDecimal(10000))> 0){
                        tvDatas.add(TextViewsEntity("面积:", result.area.divide(BigDecimal(10000),2,RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                    }else{
                        tvDatas.add(TextViewsEntity("面积:", result.area.toInt().toString()+"㎡"))//(公顷).setScale(2,RoundingMode.HALF_UP)
                    }
//            tvDatas.add(TextViewsEntity("权属单位名称:", result.qsdwmc))
                }
                if (!result.dlmc.equals("")){//如果有村名显示弹出框
                    ZjdglFragment.kuangGeomentLine(result.geometry)
                    PopuPointUtils.startPopuPoint(supl_frag_zjdgl1,tvDatas,"", arrayOf(0,1,2,3,4,5))
                }
                /* clearAllMarker()
                 aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(item.center, 19f))
                 aMap?.addMarker(MarkerOptions().position(item.center))
                 this@MainActivity.position = position
                 botDialog?.dismiss()
                 queryByPoint(item.center)*/
            }

        })
        rlv_frag_zjd_tdly.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&qsflowInfoList.size==page*limit) {
                    if (qsflowInfoList.size !=0 && qsflowInfoList.size%limit == 0){
                        page++
                        mPresenter.getQsPolygonDetail(points,limit,page)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_frag_zjd_tdly.scrollToPosition(firstVisibleItem+1)//(page-1)*limit
        }
    }
    override fun onPolygonGh(list: MutableList<GhVO>) {
        diaoyong()
        if (list.size > 0) {

            supl_frag_zjdgl1!!.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl1!!.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            if (list[0] != null) {
                val ylDatas = ArrayList<Float>()
                val ylLables = ArrayList<String>()
                for (i in list){
                    ylLables.add(i.fqmc)
                    ylDatas.add(i.area1.toFloat())
                }
                /* ylDatas.add(list[0].jsydmj.toInt())
                 ylLables.add("建设用地")
                 ylDatas.add(list[0].nydmj.toInt())
                 ylLables.add("农用地")
                 ylDatas.add(list[0].tjsmj.toInt())
                 ylLables.add("特交水")
                 ylDatas.add(list[0].wlydmj.toInt())
                 ylLables.add("未利用")*/

                PieTdxgUtil.initPie(pie_frag_zjd_tdly!!, "规划状况", ylDatas, ylLables)
            }
        }
    }
    override fun onPolygonDetailGh(tdlyDetail: List<GhVO>) {
        if (page == 1){
            ghflowInfoList.clear()
        }
        var firstVisibleItem = 0
        if (linearLayoutManager!=null){
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        ghflowInfoList.addAll(tdlyDetail)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_frag_zjd_tdly.layoutManager = linearLayoutManager
        val zlglAdapter = GhDetailAdapter(R.layout.item_fwqs_detail, ghflowInfoList)
        rlv_frag_zjd_tdly.adapter =zlglAdapter
        zlglAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val tdlyEntity = adapter?.getItem(position) as GhVO
//                onZjdTdlyLister?.onClickGh(item,position)
                val tvDatas = ArrayList<TextViewsEntity>()
                if (tdlyEntity != null) {
                    if (!tdlyEntity.xzqmc.equals(""))
                        tvDatas.add(TextViewsEntity("村名:", tdlyEntity.xzqmc))
//                tvDatas.add(TextViewsEntity("分区代码:", tdlyEntity.fqdm))
                    if (!tdlyEntity.ghmc.equals(""))
                        tvDatas.add(TextViewsEntity("规划名称:", tdlyEntity.ghmc))
                    if (tdlyEntity.ydmj.compareTo(BigDecimal(10000))> 0){
                        tvDatas.add(TextViewsEntity("用地面积:", tdlyEntity.ydmj.divide(BigDecimal(10000),2,RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                    }else{
                        tvDatas.add(TextViewsEntity("用地面积:", tdlyEntity.ydmj.toInt().toString()+"㎡"))//(公顷).setScale(2,RoundingMode.HALF_UP)
                    }
//                tvDatas.add(TextViewsEntity("规划期限:", tdlyEntity.ghqx))
                }
                if (!tdlyEntity.ghmc.equals("")){//如果有村名显示弹出框
                    ZjdglFragment.kuangGeomentLine(tdlyEntity.geometry)
//                ToastUtils.showShort(""+tdlyEntity.geometry)
                    PopuPointUtils.startPopuPoint(supl_frag_zjdgl1,tvDatas,tdlyEntity.ghmc, arrayOf(0,1,2,3,4))//,5,6
                }
                /* clearAllMarker()
                 aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(item.center, 19f))
                 aMap?.addMarker(MarkerOptions().position(item.center))
                 this@MainActivity.position = position
                 botDialog?.dismiss()
                 queryByPoint(item.center)*/
            }

        })
        rlv_frag_zjd_tdly.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&ghflowInfoList.size==page*limit) {
                    if (ghflowInfoList.size !=0 && ghflowInfoList.size%limit == 0){
                        page++
                        mPresenter.getGhPolygonDetail(points,limit,page)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_frag_zjd_tdly.scrollToPosition(firstVisibleItem+1)//(page-1)*limit
        }
    }
    var bcTdlyEntityId = 0L
    override fun onBcjcGetTdly(tdlyDetail: BcTdlyEntity) {
//        isQq = 0
        if (tdlyDetail!=null){
            tylySaveType = 1
            bcTdlyEntity = tdlyDetail
            process = tdlyDetail.process//(split+"年").equals(tv_frag_tdly_xzsj.addText)
            if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(tdlyDetail.process==1||tdlyDetail.process==0)&&((split+"年")
                            .equals(tdlyDetail.years)||tdlyDetail.years.equals(""))){
                tv_frag_zjd_tdly_add.visibility = View.VISIBLE
                tv_frag_zjd_tdly_add.setText("操作")
            }else
                if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&tdlyDetail.process==2&&(split+"年").equals(tdlyDetail.years)){
                tv_frag_zjd_tdly_add.visibility = View.VISIBLE
                    tv_frag_zjd_tdly_add.setText("操作")
            }else
                    if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&tdlyDetail.process==3&&(split+"年").equals(tdlyDetail.years)){
                tv_frag_zjd_tdly_add.visibility = View.VISIBLE
                        tv_frag_zjd_tdly_add.setText("操作")
            }else{
//                tv_frag_zjd_tdly_add.visibility = View.GONE
                        if (tdlyDetail.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                            tv_frag_zjd_tdly_add.setText("已上报")
                            tv_frag_zjd_tdly_zgnyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zggd.visibility = View.VISIBLE
                            tv_frag_tdly_zgyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgld.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgssnyyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgqtnyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgjsyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgjzyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgggglfwyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgjtjyjsyd.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_zgtdzmj.visibility = View.VISIBLE
                            but_frag_zjd_tdly_save.visibility = View.GONE//保存
                            but_frag_zjd_tdly_xyb.visibility = View.GONE//下一步
                            but_frag_zjd_tdly_bh.visibility = View.GONE//驳回
                        }else{
                            tv_frag_zjd_tdly_add.setText("未上报")
                        }
                        if (AppCache.getInstance().type==1){
                            tv_frag_zjd_tdly_add.visibility = View.GONE
                        }
            }


            bcTdlyEntityId = tdlyDetail.id
            var bhgsCount2 = ArrayList<Float>()
            var bhgsName2 = ArrayList<String>()
            val colors2 = java.util.ArrayList<Int>()//
            colors2.add(Color.parseColor("#FFFF00"))
            colors2.add(Color.parseColor("#FFBEBE"))
            colors2.add(Color.parseColor("#E1E1E1"))
//            colors2.add(Color.parseColor("#FFC000"))
            bhgsCount2.add(PieTdxgUtil.getStringToFloat(tdlyDetail.nydmj))
            bhgsCount2.add(PieTdxgUtil.getStringToFloat(tdlyDetail.jsydmj))
            bhgsCount2.add(PieTdxgUtil.getStringToFloat(tdlyDetail.tdzmj))
            bhgsName2.add("农用地")
            bhgsName2.add("建设用地")
            bhgsName2.add("其他土地")
            val s = PieTdxgUtil.getStringToFloat(tdlyDetail.nydmj) + PieTdxgUtil.getStringToFloat(tdlyDetail.jsydmj) +
                    PieTdxgUtil.getStringToFloat(tdlyDetail.tdzmj)
            if (s<=0f){
                ll_frag_zjd_tdly_ydlx.visibility = View.GONE
                mtl_frag_zjd_tdly_nyjsyd.visibility = View.GONE
                ll_frag_zjd_tdly_nyjsyd.visibility = View.GONE
                mtl_frag_zjd_tdly_nyjsyd.setImageView(R.drawable.item_title_right)
            }else{
                if (AppCache.getInstance().type!=4){
                    ll_frag_zjd_tdly_ydlx.visibility = View.VISIBLE
                }
//                ll_frag_zjd_tdly_nyjsyd.visibility = View.VISIBLE
            }
            // 是不是很简单
            /*pie_frag_zjd_tdly_ydlx.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                Log.e("",""+i+entry+highlight);  //打印日志
            }

            @Override
            public void onNothingSelected() {
					//整体扇形的监听
            }
        });*/

//            PieTdxgUtil.rkinitPie1(pie_frag_zjd_tdly_ydlx, "", bhgsCount2, bhgsName2, colors2)//用地类型
            PieTdxgUtil.pieRlv(pie_frag_zjd_tdly_ydlx, "", bhgsCount2, bhgsName2, colors2,rlv_frag_zjd_tdly_ydlx,activity!!)//用地类型

            pie_frag_zjd_tdly_ydlx.setOnChartValueSelectedListener(object: OnChartValueSelectedListener {
                override fun onNothingSelected() {

                }

                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val pieEntry = e as PieEntry
//                    ToastUtils.showShort(pieEntry?.value.toString())
                    if (pieEntry?.label.toString().equals("农用地")){
                        ll_frag_zjd_tdly_nyjsyd.visibility = View.VISIBLE
                        mtl_frag_zjd_tdly_nyjsyd.setImageView(R.drawable.item_title_down)
                        mtl_frag_zjd_tdly_nyjsyd.visibility = View.VISIBLE
                        ll_frag_zjd_tdly_nyydqk.visibility = View.VISIBLE
                        ll_frag_zjd_tdly_jsydqk.visibility = View.GONE

                    }else if (pieEntry?.label.toString().equals("建设用地")){
                        ll_frag_zjd_tdly_nyjsyd.visibility = View.VISIBLE
                        mtl_frag_zjd_tdly_nyjsyd.setImageView(R.drawable.item_title_down)
                        mtl_frag_zjd_tdly_nyjsyd.visibility = View.VISIBLE
                        ll_frag_zjd_tdly_nyydqk.visibility = View.GONE
                        ll_frag_zjd_tdly_jsydqk.visibility = View.VISIBLE

                    }else if (pieEntry?.label.toString().equals("其他土地")){
                        ll_frag_zjd_tdly_nyjsyd.visibility = View.GONE
                        mtl_frag_zjd_tdly_nyjsyd.setImageView(R.drawable.item_title_right)
                        mtl_frag_zjd_tdly_nyjsyd.visibility = View.GONE
                        ll_frag_zjd_tdly_nyydqk.visibility = View.GONE
                        ll_frag_zjd_tdly_jsydqk.visibility = View.GONE

                    }
                }
            })
            var bhgsCount1 = ArrayList<Float>()
            var bhgsName1 = ArrayList<String>()
            val colors1 = java.util.ArrayList<Int>()//
            colors1.add(Color.parseColor("#FFFF96"))
            colors1.add(Color.parseColor("#F5D228"))
            colors1.add(Color.parseColor("#288C00"))
            colors1.add(Color.parseColor("#DCB482"))
            colors1.add(Color.parseColor("#E1E1E1"))
            bhgsCount1.add(PieTdxgUtil.getStringToFloat(tdlyDetail.gdmj))
            bhgsCount1.add(PieTdxgUtil.getStringToFloat(tdlyDetail.ydmj))
            bhgsCount1.add(PieTdxgUtil.getStringToFloat(tdlyDetail.ldmj))
            bhgsCount1.add(PieTdxgUtil.getStringToFloat(tdlyDetail.nyjsmj))
            bhgsCount1.add(PieTdxgUtil.getStringToFloat(tdlyDetail.qtnydmj))
            bhgsName1.add("耕地")
            bhgsName1.add("园地")
            bhgsName1.add("林地")
            bhgsName1.add("设施农业用地")
            bhgsName1.add("其他农用地")
            /*val s1 = PieTdxgUtil.getStringToFloat(tdlyDetail.nydmj) + PieTdxgUtil.getStringToFloat(tdlyDetail.jsydmj) +
                    PieTdxgUtil.getStringToFloat(tdlyDetail.tdzmj)
            if (s1<=0f){
                ll_frag_zjd_tdly_ydlx.visibility = View.GONE
            }else{
                ll_frag_zjd_tdly_ydlx.visibility = View.VISIBLE
            }*/
//            PieTdxgUtil.rkinitPie1(pie_frag_zjd_tdly_nyyd, "", bhgsCount1, bhgsName1, colors1)//农业用地
            PieTdxgUtil.pieRlv(pie_frag_zjd_tdly_nyyd, "", bhgsCount1, bhgsName1, colors1,rlv_frag_zjd_tdly_nyyd,activity!!)//农业用地
            var bhgsCount = ArrayList<Float>()
            var bhgsName = ArrayList<String>()
            val colors = java.util.ArrayList<Int>()//
            colors.add(Color.parseColor("#5B9BD5"))
            colors.add(Color.parseColor("#ED7D31"))
            colors.add(Color.parseColor("#A5A5A5"))
            bhgsCount.add(PieTdxgUtil.getStringToFloat(tdlyDetail.jzydmj))
            bhgsCount.add(PieTdxgUtil.getStringToFloat(tdlyDetail.ggydmj))
            bhgsCount.add(PieTdxgUtil.getStringToFloat(tdlyDetail.jtydmj))
            bhgsName.add("居住用地")
            bhgsName.add("公共管理与公共服务用地")
            bhgsName.add("集体经营性建设用地")
//            PieTdxgUtil.rkinitPie1(pie_frag_zjd_tdly_jsyd, "", bhgsCount, bhgsName, colors)//建设用地
            PieTdxgUtil.pieRlv(pie_frag_zjd_tdly_jsyd, "", bhgsCount, bhgsName, colors,rlv_frag_zjd_tdly_jsyd,activity!!)//建设用地
            
            tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,tdlyDetail.process-1,tdlyDetail.process-1)
            if (tdlyDetail.bcrejectedEntities.size>0&&
                    ((tdlyDetail.process==1&&AppCache.getInstance().type==4)||
                            (tdlyDetail.process==2&&AppCache.getInstance().type==3))){//tdlyDetail

                var html = "<font><strong color='#000000'>驳回原因:</strong></font>"
                for (f in 0..tdlyDetail.bcrejectedEntities.size-1){
                    val i = tdlyDetail.bcrejectedEntities.get(f)
                    if (f<4){
                        html = html + "<br/><font><strong color='#ED2D2D'>" + TypeLxEnum.getName(i.process)+
                                "</strong>("+i.updateTime+"):"+i.content+"</font>"
                    }
                    /*if (!html.contains(TypeLxEnum.getName(i.type))){
                        html = html + "<font><strong color='#ED2D2D'>" + TypeLxEnum.getName(i.type) + "</strong></font><br/>"
                        html = html + "<font>"+i.updateTime+":"+i.content+"</font><br/>"
                    }else{
                        html = html + "<font>"+i.updateTime+":"+i.content+"</font><br/>"
                    }*/

                }
                tv_frag_zjd_tdly_bhyy.ellipsize = TextUtils.TruncateAt.END
                tv_frag_zjd_tdly_bhyy.maxLines = 2
                tv_frag_zjd_tdly_bhyy.setText(Html.fromHtml(html))
                tv_frag_zjd_tdly_bhyy.setOnClickListener {
                    if (tv_frag_zjd_tdly_bhyy.maxLines==2){
                        tv_frag_zjd_tdly_bhyy.maxLines = 100
                    }else{
                        tv_frag_zjd_tdly_bhyy.maxLines = 2
                    }
                }
//                tv_frag_zjd_tdly_bhyy.setText("驳回原因:"+tdlyDetail.bcrejectedEntities.get(0).content)
                tv_frag_zjd_tdly_bhyy.visibility = View.VISIBLE
            }else{
                tv_frag_zjd_tdly_bhyy.visibility = View.GONE
            }
            et_frag_tdly_nyd_zdmj.setText(tdlyDetail.nydmj)//农用地-占地面积
            et_frag_tdly_nyd_remarkqqqd.setText(tdlyDetail.nydqd)//农用地-确权确地面积
            et_frag_tdly_nyd_remarkqqql.setText(tdlyDetail.nydql)//农用地-确权确利面积
            et_frag_tdly_nyd_remarkqqqg.setText(tdlyDetail.nydqg)//农用地-确权确股面积
//            et_frag_tdly_nyd_remark.setText(tdlyDetail.gydmj)//农用地-备注
            et_frag_tdly_gd_zdmj.setText(tdlyDetail.gdmj)//耕地-占地面积
            et_frag_tdly_gd_syqkzz.setText(tdlyDetail.gdzzmj)//耕地-自种面积
            et_frag_tdly_gd_syqklz.setText(tdlyDetail.gdlzmj)//耕地-流转面积
            et_frag_tdly_gd_syqkqt.setText(tdlyDetail.gdqtmj)//耕地-其他面积
            et_frag_tdly_gd_pjlz.setText(tdlyDetail.gdjg)//耕地-平均流转
            et_frag_tdly_gd_remark.setText(tdlyDetail.gdbz)//耕地-备注
//            et_frag_tdly_gd_remarkqqqd.setText(tdlyDetail.gdqd)//耕地-确权确地面积
//            et_frag_tdly_gd_remarkqqql.setText(tdlyDetail.gdql)//耕地-确权确利面积
//            et_frag_tdly_gd_remarkqqqg.setText(tdlyDetail.gdqg)//耕地-确权确股面积
            et_frag_tdly_yd_zdmj.setText(tdlyDetail.ydmj)//园地-占地面积
            et_frag_tdly_yd_syqkzz.setText(tdlyDetail.ydzzmj)//园地-自种面积
            et_frag_tdly_yd_syqklz.setText(tdlyDetail.ydlzmj)//园地-流转面积
            et_frag_tdly_yd_syqkqt.setText(tdlyDetail.ydqtmj)//园地-其他面积
            et_frag_tdly_yd_pjlz.setText(tdlyDetail.ydjg)//园地-平均流转
            et_frag_tdly_yd_remark.setText(tdlyDetail.ydbz)//园地-备注
//            et_frag_tdly_yd_remarkqqqd.setText(tdlyDetail.ydqd)//园地-确权确地面积
//            et_frag_tdly_yd_remarkqqql.setText(tdlyDetail.ydql)//园地-确权确利面积
//            et_frag_tdly_yd_remarkqqqg.setText(tdlyDetail.ydqg)//园地-确权确股面积
            et_frag_tdly_ld_zdmj.setText(tdlyDetail.ldmj)//林地-占地面积(亩)
            et_frag_tdly_ld_syqkwjy.setText(tdlyDetail.ldwjy)//林地-无经营面积
            et_frag_tdly_ld_syqklxzyz.setText(tdlyDetail.ldlxzyz)//林地-林下种养殖面积
            et_frag_tdly_ld_pjlz.setText(tdlyDetail.ldjg)//林地-平均流转
            et_frag_tdly_ld_remark.setText(tdlyDetail.ldbz)//林地-备注
//            et_frag_tdly_ld_remarkqqqd.setText(tdlyDetail.ldqd)//林地-确权确地面积
//            et_frag_tdly_ld_remarkqqql.setText(tdlyDetail.ldql)//林地-确权确利面积
//            et_frag_tdly_ld_remarkqqqg.setText(tdlyDetail.ldqg)//林地-确权确股面积
            et_frag_tdly_nyssjs_zdmj.setText(tdlyDetail.nyjsmj)//农业设施建设-面积
//            et_frag_tdly_nyssjs_syqk.setText(tdlyDetail.nyjssyqk)//农业设施建设-使用情况
//            et_frag_tdly_nyssjs_syqkzz.setText(tdlyDetail.nyjszzmj)//农业设施建设-自种面积
//            et_frag_tdly_nyssjs_syqklz.setText(tdlyDetail.nyjslzmj)//农业设施建设-流转面积
//            et_frag_tdly_nyssjs_syqkqt.setText(tdlyDetail.nyjsqtmj)//农业设施建设-其他面积
//            et_frag_tdly_nyssjs_pjlz.setText(tdlyDetail.nyjsjg)//农业设施建设-平均流转
            et_frag_tdly_nyssjs_remark.setText(tdlyDetail.nyjsbz)//农业设施建设-备注
            et_frag_tdly_qtnyd_zdmj.setText(tdlyDetail.qtnydmj)//其他农用地-面积
//            et_frag_tdly_qtnyd_syqk.setText(tdlyDetail.qtnydsyqk)//其他农用地-使用情况
//            et_frag_tdly_qtnyd_pjlz.setText(tdlyDetail.qtnydjg)//其他农用地-平均流转
            et_frag_tdly_qtnyd_remark.setText(tdlyDetail.qtnydbz)//其他农用地-备注
            et_frag_tdly_jsyd_zdmj.setText(tdlyDetail.jsydmj)//建设用地-占地面积
//            et_frag_tdly_jsyd_syqk.setText(tdlyDetail.jsydsyqk)//建设用地-使用情况
//            et_frag_tdly_jsyd_qjlz.setText(tdlyDetail.jsydjg)//建设用地-平均流转
            et_frag_tdly_jsyd_remarkghpf.setText(tdlyDetail.jsydpf)//建设用地-规划批复使用面积
            et_frag_tdly_jzyd_zdmj.setText(tdlyDetail.jzydmj)//居住用地-占地面积
            et_frag_tdly_jzyd_syqkzz.setText(tdlyDetail.jzydzz)//居住用地-自住
            et_frag_tdly_jzyd_syqklz.setText(tdlyDetail.jzydcz)//居住用地-出租
            et_frag_tdly_jzyd_syqkqt.setText(tdlyDetail.jzydqt)//居住用地-其他
            et_frag_tdly_jzyd_pjlz.setText(tdlyDetail.jzydjg)//居住用地-平均流转
            et_frag_tdly_jzyd_remarkqzgj.setText(tdlyDetail.jzydms)//居住用地-民宿
            et_frag_tdly_gggl_zdmj.setText(tdlyDetail.ggydmj)//公共管理与公共服务用地-占地面积
//            et_frag_tdly_gggl_syqk.setText(tdlyDetail.ggydsyqk)//公共管理与公共服务用地-使用情况
            et_frag_tdly_gggl_syqkzy.setText(tdlyDetail.ggydzz)//公共管理与公共服务用地-使用情况
            et_frag_tdly_gggl_syqkcz.setText(tdlyDetail.ggydcz)//公共管理与公共服务用地-使用情况
            et_frag_tdly_gggl_syqkqt.setText(tdlyDetail.ggydqt)//公共管理与公共服务用地-使用情况
            et_frag_tdly_gggl_pjlz.setText(tdlyDetail.ggydlzjg)//公共管理与公共服务用地-使用情况
            et_frag_tdly_gggl_remark.setText(tdlyDetail.ggydbz)//公共管理与公共服务用地-备注
            et_frag_tdly_jtjyjs_zdmj.setText(tdlyDetail.jtydmj)//集体经营性建设用地-占地面积
//            et_frag_tdly_jtjyjs_syqk.setText(tdlyDetail.jtydsyqk)//集体经营性建设用地-使用情况
            et_frag_tdly_jtjyjs_syqkzy.setText(tdlyDetail.jtydzz)//集体经营性建设用地-使用情况自用
            et_frag_tdly_jtjyjs_syqkcz.setText(tdlyDetail.jtydcz)//集体经营性建设用地-使用情况出租
            et_frag_tdly_jtjyjs_syqkqt.setText(tdlyDetail.jtydqt)//集体经营性建设用地-使用情况其他
            et_frag_tdly_jtjyjs_pjlz.setText(tdlyDetail.jtydjg)//集体经营性建设用地-平均流转
            et_frag_tdly_jtjyjs_remark.setText(tdlyDetail.jtydbz)//集体经营性建设用地-备注
            et_frag_tdly_tdzmj_zdmj.setText(tdlyDetail.tdzmj)//土地总面积
            et_frag_tdly_tdzmj_gyd.setText(tdlyDetail.gymj)//国有面积
//            et_frag_tdly_tdzmj_yjjbnt.setText(tdlyDetail.yjjbnt)//基本永久农田
            et_frag_tdly_tdzmj_pyzl.setText(tdlyDetail.pyzl)//平原造林
            et_frag_tdly_tdzmj_jtjy.setText(tdlyDetail.jtjy)//家庭经营
            et_frag_tdly_tdzmj_jtjy1.setText(tdlyDetail.jtjy1)//集体经营
            et_frag_tdly_tdzmj_dhcb.setText(tdlyDetail.dhcb)//大户承包
            et_frag_tdly_tdzmj_dwzl.setText(tdlyDetail.dwzl)//对外租赁
            et_frag_tdly_tdzmj_qt.setText(tdlyDetail.qtmj)//其他面积
//            et_frag_tdly_tdzmj_total.setText(tdlyDetail.zjmj)//总计面积
            /*if (!tdlyDetail.years.equals("")){
            }*/


            tv_frag_zjd_tdly_year.setText(tdlyDetail.years)//setText(tdlyDetail.years)


            if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
                bt_frag_zjd_tdly_xyb.text = "提交上报"
                if (bczdqkEntitysProcess == 1&&process == 1){//bczhandqkEntitysProcess == 1&&   bczdqkEntitysProcess
                    bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
                }else{
                    bt_frag_zjd_tdly_xyb.visibility = View.GONE
                }
            }else{
                bt_frag_zjd_tdly_xyb.text = "通过"
                if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                    if (bczdqkEntitysProcess == 2&&process == 2){
                        bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
                        bt_frag_zjd_tdly_bh.visibility = View.VISIBLE
                    }else{
                        bt_frag_zjd_tdly_xyb.visibility = View.GONE
                        bt_frag_zjd_tdly_bh.visibility = View.GONE
                    }
                }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                    if (bczdqkEntitysProcess == 3&&process == 3){
                        bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
                        bt_frag_zjd_tdly_bh.visibility = View.VISIBLE
                    }else{
                        bt_frag_zjd_tdly_xyb.visibility = View.GONE
                        bt_frag_zjd_tdly_bh.visibility = View.GONE
                    }
                }else{
                    bt_frag_zjd_tdly_xyb.visibility = View.GONE
                    bt_frag_zjd_tdly_bh.visibility = View.GONE
                }
            }


        }else{
            bcTdlyEntityId = 0L
            ll_frag_zjd_tdly_ydlx.visibility = View.GONE
            mtl_frag_zjd_tdly_nyjsyd.visibility = View.GONE
            ll_frag_zjd_tdly_nyjsyd.visibility = View.GONE
            mtl_frag_zjd_tdly_nyjsyd.setImageView(R.drawable.item_title_right)
        }
//        Color.parseColor("#2E2E2E")
        et_frag_tdly_nyd_remarkqqqd.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_nyd_remarkqqql.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_nyd_remarkqqqg.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gd_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计
        et_frag_tdly_yd_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计
        et_frag_tdly_ld_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计
        et_frag_tdly_nyssjs_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计
        et_frag_tdly_qtnyd_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计

        et_frag_tdly_jzyd_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计
        et_frag_tdly_gggl_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计
        et_frag_tdly_jtjyjs_zdmj.setTextColor(Color.parseColor("#5CA3E5"))//计算合计

        et_frag_tdly_gd_syqkzz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gd_syqklz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gd_syqkqt.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gd_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gd_remark.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_yd_syqkzz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_yd_syqklz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_yd_syqkqt.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_yd_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_yd_remark.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_ld_syqkwjy.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_ld_syqklxzyz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_ld_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_ld_remark.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_nyssjs_syqk.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_nyssjs_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_nyssjs_remark.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_qtnyd_syqk.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_qtnyd_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_qtnyd_remark.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_jsyd_syqk.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_jsyd_qjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jsyd_remarkghpf.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jzyd_syqkzz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jzyd_syqklz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jzyd_syqkqt.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jzyd_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jzyd_remarkqzgj.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_gggl_syqk.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gggl_syqkzy.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gggl_syqkcz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gggl_syqkqt.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gggl_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_gggl_remark.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_jtjyjs_syqk.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jtjyjs_syqkzy.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jtjyjs_syqkcz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jtjyjs_syqkqt.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jtjyjs_pjlz.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_jtjyjs_remark.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_gyd.setTextColor(Color.parseColor("#5CA3E5"))
//        et_frag_tdly_tdzmj_yjjbnt.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_pyzl.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_jtjy.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_jtjy1.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_dhcb.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_dwzl.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_qt.setTextColor(Color.parseColor("#5CA3E5"))
        et_frag_tdly_tdzmj_zdmj.setTextColor(Color.parseColor("#5CA3E5"))
    }
    override fun onBcjcTdlySave(tdlyDetail: String) {
        if (tdlyDetail.equals("添加成功")){
            if (mBhPopu!=null)
            mBhPopu?.dismiss()
            onClickUpdate()
        }
        mPresenter.getBcjcGetTdly(AppCache.getInstance().code,year)
        ToastUtils.showShort(tdlyDetail)
    }

    override fun onBcjcTdlyUpdate(tdlyDetail: String) {
        if (tdlyDetail.equals("修改成功")){
            if (mBhPopu!=null)
            mBhPopu?.dismiss()
            onClickUpdate()
        }else{
            bcTdlyEntity.process = process
        }
        mPresenter.getBcjcGetTdly(AppCache.getInstance().code,year)
        ToastUtils.showShort(tdlyDetail)
    }
    fun getSearch(points :String){
        this.points = points
        page = 1
        mPresenter.getTxPolygon(points)
        mPresenter.getTxPolygonDetail(points,limit,page)
    }
    fun getSearchTG(points :String){
        this.points = points
        page = 1
        mPresenter.getSktPolygon(points)
        mPresenter.getSktPolygonDetail(points,limit,page)
    }
    fun getSearchQS(points :String){
        this.points = points
        page = 1
        mPresenter.getQsPolygon(points)
        mPresenter.getQsPolygonDetail(points,limit,page)
    }
    fun getSearchKJGH(points :String){
        this.points = points
        page = 1
        mPresenter.getGhPolygon(points)
        mPresenter.getGhPolygonDetail(points,limit,page)
    }
    fun diaoyong(){
        if (supl_frag_zjdgl1!=null){//&&slv_frag_zjd_tdly!=null
            sptypeList.clear()
            sptypeList.add("上报")
            sptypeList.add("乡镇审核")
            sptypeList.add("区级确认")
            sptypeList.add("提交成功")
            tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,-1,-1)
//            tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,-1,-1)
//            tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,-1,-1)
            supl_frag_zjdgl1?.setScrollableView(slv_frag_zjd_tdly)

            mPresenter.getBcjcYears()

        }

    }
    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }
    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }
    override fun showErrorTip(msg: String?) {
        supl_frag_zjdgl1?.panelHeight = DisplayUtil.dip2px(0f)
        ToastUtils.showShort(msg)
    }


    override fun returnBcjcAddZhengdqk(msg: String) {
        ToastUtils.showShort(msg)
        if (addZhengdqkUpPopu!=null){
            addZhengdqkUpPopu!!.dismiss()
        }
        mPresenter.getBchcZhengdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcZhengdqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcGetKnzd(AppCache.getInstance().code,yearsList)

    }
    override fun returnBcjcUpdateZhengdqk(msg: String) {
        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){//addZhengdqkUpPopu
            mUploadPopu!!.dismiss()
        }
        mPresenter.getBchcZhengdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcZhengdqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcGetKnzd(AppCache.getInstance().code,yearsList)
    }
    override fun returnBchcZhengdqk(msg: List<BczzdqkEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addZhengdqkUpPopu!=null){
            addZhengdqkUpPopu!!.dismiss()
        }
        if (msg!=null){
            /*if (msg.size>0){
                if (AppCache.getInstance().type==4&&tdlyDetail.process==1){
                    tv_frag_zjd_tdly_add.visibility = View.VISIBLE
                }else if (AppCache.getInstance().type==3&&tdlyDetail.process==2){
                    tv_frag_zjd_tdly_add.visibility = View.VISIBLE
                }else if (AppCache.getInstance().type==2&&tdlyDetail.process==3){
                    tv_frag_zjd_tdly_add.visibility = View.VISIBLE
                }else{
                    tv_frag_zjd_tdly_add.visibility = View.GONE
                }
            }*/
            val arrayList1 = ArrayList<BczzdqkEntity>()
            for (i in msg){
                if (i.years.contains(tv_frag_tdly_xzsj.addText)){
                    arrayList1.add(i)
                }
            }
            if (rlv_frag_tdqs_zhengdqk!=null){
                if (arrayList1.size>0){//tv_frag_tdqs_zhengdqk_add
                    val get = arrayList1.get(0)
                    if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                        tv_frag_tdqs_zhengdqk_add.visibility = View.VISIBLE
                        tv_frag_tdqs_zhengdqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                            tv_frag_tdqs_zhengdqk_add.visibility = View.VISIBLE
                            tv_frag_tdqs_zhengdqk_add.setText("操作")
                        }else
                            if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                                tv_frag_tdqs_zhengdqk_add.visibility = View.VISIBLE
                                tv_frag_tdqs_zhengdqk_add.setText("操作")
                            }else{
//                                tv_frag_tdqs_zhengdqk_add.visibility = View.GONE
                                if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                    tv_frag_tdqs_zhengdqk_add.setText("已上报")
                                }else{
                                    tv_frag_tdqs_zhengdqk_add.setText("未上报")
                                }
                                if (AppCache.getInstance().type==1){
                                    tv_frag_tdqs_zhengdqk_add.visibility = View.GONE
                                }

                            }
                    when(get.process){
                        1->{
                            tv_frag_zjd_tdly_gzjddsb.setTextColor(Color.parseColor("#1B96EE"))
                            tv_frag_zjd_tdly_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                        }
                        2->{
                            tv_frag_zjd_tdly_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                            tv_frag_zjd_tdly_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                        }
                        3->{
                            tv_frag_zjd_tdly_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                            tv_frag_zjd_tdly_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                        }
                        4->{
                            tv_frag_zjd_tdly_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                            tv_frag_zjd_tdly_gzjdtjcg.setTextColor(Color.parseColor("#1B96EE"))
                            tv_frag_zjd_tdly_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                        }
                    }
                    tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,get.process-1,get.process-1)
                }else if (AppCache.getInstance().type==4){
                    tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,-1,-1)
                    tv_frag_tdqs_zhengdqk_add.visibility = View.VISIBLE
                    tv_frag_tdqs_zhengdqk_add.setText("操作")
                }else if (AppCache.getInstance().type!=4){
                    tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,-1,-1)
                    if (AppCache.getInstance().type==1){
                        tv_frag_tdqs_zhengdqk_add.visibility = View.GONE
                    }
                    tv_frag_tdqs_zhengdqk_add.setText("未上报")
                }
                rlv_frag_tdqs_zhengdqk.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                rlv_frag_tdqs_zhengdqk.adapter = object :BaseQuickAdapter<BczzdqkEntity, BaseViewHolder>(R.layout.item_bcjc_zhengdqk,arrayList1){
                    override fun convert(helper: BaseViewHolder?, item: BczzdqkEntity?) {
                        helper!!.setText(R.id.item_bcjc_zhengdqk_year,item!!.years.replace("年",""))
                                .setText(R.id.item_bcjc_zhengdqk_zdsj,item.zdsj)
                                .setText(R.id.item_bcjc_zhengdqk_zdmj,item.zdmj)
                                .setText(R.id.item_bcjc_zhengdqk_gyzdmj,item.gyzdmj)
                                .setText(R.id.item_bcjc_zhengdqk_gybcf,item.gybcf)
                                .setText(R.id.item_bcjc_zhengdqk_kfmj,item.kfjsmj)
                                .setText(R.id.item_bcjc_zhengdqk_kfbcf,item.kfjsbcf)
                                .setText(R.id.item_bcjc_zhengdqk_qtmj,item.qtzdmj)
                                .setText(R.id.item_bcjc_zhengdqk_qtbcf,item.qtzdbcf)

                                .setText(R.id.item_bcjc_zhandqk_zdsj,item.zdsj)
                                .setText(R.id.item_bcjc_zhandqk_zdmj,item.zhandmj)
                                .setText(R.id.item_bcjc_zhandqk_jczdmj,item.jcmj)
                                .setText(R.id.item_bcjc_zhandqk_jcbcf,item.jcbcf)
                                .setText(R.id.item_bcjc_zhandqk_jdzfmj,item.jdmj)
                                .setText(R.id.item_bcjc_zhandqk_jdzfbcf,item.jdbcf)
                                .setText(R.id.item_bcjc_zhandqk_kfqmj,item.kfqmj)
                                .setText(R.id.item_bcjc_zhandqk_kfqbcf,item.kfqbcf)
                                .setText(R.id.item_bcjc_zhandqk_gyzfmj,item.gysymj)
                                .setText(R.id.item_bcjc_zhandqk_gybcf,item.gysybcf)
                                .setText(R.id.item_bcjc_zhandqk_qtmj,item.qtmj)
                                .setText(R.id.item_bcjc_zhandqk_qtbcf,item.qtbcf)


                        val years = helper.getView<TextView>(R.id.item_bcjc_zhengdqk_year)
                        val zhandqkDelete = helper.getView<TextView>(R.id.item_bcjc_zhandqk_delete)
                        if (item.id!=null&&item.id!=0L&&AppCache.getInstance().type == 4&&(item.process == 0||item.process == 1)){
                            zhandqkDelete.visibility = View.VISIBLE
                            zhandqkDelete.setTextColor(Color.RED)
                            tv_frag_zjd_tdly_delete.visibility = View.VISIBLE
                        }else{
                            zhandqkDelete.visibility = View.GONE
                            tv_frag_zjd_tdly_delete.visibility = View.GONE
                        }
                        zhandqkDelete.setOnClickListener {
                            val content = TextView(activity)
                            content.text = "是否删除？"

//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("删除"+item.zdsj+"数据")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        val arrayList = ArrayList<BczzdqkEntity>()
                                        for (i in arrayList1){
                                            i.ids.add(item.id)
                                            arrayList.add(i)
                                        }
                                        mPresenter.getBcjcAddZhengdqk(arrayList)
                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                        if (AppCache.getInstance().type == 4&&(item.process == 0||item.process == 1)) {
                            helper.itemView.setOnClickListener {

                                //                                if (tv_frag_tdqs_zhengdqk_add.text.toString().equals("操作")){
                                addZhengdqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_zhengdqk, slv_frag_zjd_tdly)
                                val contentView: View = addZhengdqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_tdqs_spr)
                                val tv_pop_add_zheng = contentView.findViewById<TextView>(R.id.tv_pop_add_zheng)
                                val popAddTdqsEtZdsj = contentView.findViewById<TextView>(R.id.pop_add_tdqs_et_zdsj)
                                val et_zdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_zdmj)
                                val et_gyxzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_gyxzdmj)
                                val et_gyxzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_gyxzdbcf)
                                val et_kfjszdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_kfjszdmj)
                                val et_kfjszdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_kfjszdbcf)
                                val et_qtzdmj = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_qtzdmj)
                                val et_qtzdbcf = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_qtzdbcf)

                                val et_zdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_zdmj)
                                val et_jcsszdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jcsszdmj)
                                val et_jcsszdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jcsszdbcf)
                                val et_jdzfzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jdzfzdmj)
                                val et_jdzfzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_jdzfzdbcf)
                                val et_kfqzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_kfqzdmj)
                                val et_kfqzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_kfqzdbcf)
                                val et_gysyzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_gysyzdmj)
                                val et_gysyzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_gysyzdbcf)
                                val et_qtzdmj_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_qtzdmj)
                                val et_qtzdbcf_zhan = contentView.findViewById<EditText>(R.id.pop_add_tdqszd_et_qtzdbcf)

                                val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_czrname)
                                val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_tdqs_ll_reject)
                                val et_reject = contentView.findViewById<EditText>(R.id.pop_add_tdqs_et_reject)
                                val confirm = contentView.findViewById<TextView>(R.id.pop_add_tdqs_confirm)
                                val reject = contentView.findViewById<TextView>(R.id.pop_add_tdqs_reject)//驳回
                                val close = contentView.findViewById<TextView>(R.id.pop_add_tdqs_close)
                                val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_tdqs_ell)

                                ll_reject.visibility = View.GONE
                                popAddTdqsEtZdsj.setOnClickListener {
                                    TimePickerUtil.initTimePickerViewNy(activity, popAddTdqsEtZdsj.text.toString(), object : TimePickerUtil.OnTimePickerLister {
                                        override fun onClick(data: String?) { //时间筛选
                                            popAddTdqsEtZdsj.setText(data)
                                        }
                                    })


                                }
                                CommenPop.backgroundAlpha(0.5f, activity)
                                addZhengdqkUpPopu!!.showAtLocation(slv_frag_zjd_tdly, Gravity.CENTER, 0, 0)

                                tv_pop_add_zheng.setText(tv_frag_tdly_xzsj.addText)//tdlyDetail.get(0)

                                for (i in bczdqkEntitys) {
                                    if (item.id == i.id) {//tdlyDetail.get(0)   i.years
                                        popAddTdqsEtZdsj.setText(i.zdsj)
                                        et_zdmj.setText(i.zdmj)
                                        et_gyxzdmj.setText(i.gyzdmj)
                                        et_gyxzdbcf.setText(i.gybcf)
                                        et_kfjszdmj.setText(i.kfjsmj)
                                        et_kfjszdbcf.setText(i.kfjsbcf)
                                        et_qtzdmj.setText(i.qtzdmj)
                                        et_qtzdbcf.setText(i.qtzdbcf)


                                        et_zdmj_zhan.setText(i.zhandmj)
                                        et_jcsszdmj_zhan.setText(i.jcmj)
                                        et_jcsszdbcf_zhan.setText(i.jcbcf)
                                        et_jdzfzdmj_zhan.setText(i.jdmj)
                                        et_jdzfzdbcf_zhan.setText(i.jdbcf)
                                        et_kfqzdmj_zhan.setText(i.kfqmj)
                                        et_kfqzdbcf_zhan.setText(i.kfqbcf)
                                        et_gysyzdmj_zhan.setText(i.gysymj)
                                        et_gysyzdbcf_zhan.setText(i.gysybcf)
                                        et_qtzdmj_zhan.setText(i.qtmj)
                                        et_qtzdbcf_zhan.setText(i.qtbcf)

                                        if (AppCache.getInstance().type == 1) {
                                            et_czrname.setText(i.qvname)
                                        } else if (AppCache.getInstance().type == 2) {
                                            et_czrname.setText(i.qvname)
                                        } else if (AppCache.getInstance().type == 3) {
                                            et_czrname.setText(i.zhenname)
                                        } else if (AppCache.getInstance().type == 4) {
                                            et_czrname.setText(i.cunname)
                                        }
                                        bczdqkEntitysId = i.id
                                        bczdqkEntitysProcess = i.process
                                        if (AppCache.getInstance().type == 4) {
//                            et_czrname.setText(i.cunname)
                                            enableLl.setNoClick(false)
                                            when (bczdqkEntitysProcess) {
                                                1 -> {
//                                    confirm.visibility = View.VISIBLE
                                                    reject.visibility = View.VISIBLE
                                                }
                                                2 -> {
                                                    confirm.visibility = View.GONE
                                                    reject.visibility = View.GONE
                                                }
                                            }
                                        } else {
                                            enableLl.setNoClick(true)
                                            if (AppCache.getInstance().type == 3) {
//                                et_czrname.setText(i.zhenname)
                                                if (bczdqkEntitysProcess == 2) {
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                                    confirm.visibility = View.GONE
                                                    reject.visibility = View.GONE
                                                } else {
                                                    confirm.visibility = View.GONE
                                                    reject.visibility = View.GONE
                                                }
                                            } else if (AppCache.getInstance().type == 2) {
//                                et_czrname.setText(i.qvname)
                                                if (bczdqkEntitysProcess == 3) {
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                                    confirm.visibility = View.GONE
                                                    reject.visibility = View.GONE
                                                } else {
                                                    confirm.visibility = View.GONE
                                                    reject.visibility = View.GONE
                                                }
                                            } else {
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }
                                        break
                                    } else {
                                        popAddTdqsEtZdsj.setText("")
                                        et_zdmj.setText("")
                                        et_gyxzdmj.setText("")
                                        et_gyxzdbcf.setText("")
                                        et_kfjszdmj.setText("")
                                        et_kfjszdbcf.setText("")
                                        et_qtzdmj.setText("")
                                        et_qtzdbcf.setText("")

                                        et_zdmj_zhan.setText("")
                                        et_jcsszdmj_zhan.setText("")
                                        et_jcsszdbcf_zhan.setText("")
                                        et_jdzfzdmj_zhan.setText("")
                                        et_jdzfzdbcf_zhan.setText("")
                                        et_kfqzdmj_zhan.setText("")
                                        et_kfqzdbcf_zhan.setText("")
                                        et_gysyzdmj_zhan.setText("")
                                        et_gysyzdbcf_zhan.setText("")
                                        et_qtzdmj_zhan.setText("")
                                        et_qtzdbcf_zhan.setText("")

                                        bczdqkEntitysId = 0L
                                        bczdqkEntitysProcess = 0
                                        if (AppCache.getInstance().type == 4) {
                                            enableLl.setNoClick(false)
//                            confirm.visibility = View.VISIBLE
                                            reject.visibility = View.VISIBLE
                                        } else {
                                            enableLl.setNoClick(true)
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                }


                                when (AppCache.getInstance().type) {
                                    /*2->{//区
                                            confirm.text = "通过"
                                            reject.text = "驳回"
                                            //区 下一步
                                            confirm.setOnClickListener {
                                                val entity = BczzdqkEntity()
                                                entity.zdsj = popAddTdqsEtZdsj.text.toString()
                                                entity.zdmj = et_zdmj.text.toString()
                                                entity.gyzdmj = et_gyxzdmj.text.toString()
                                                entity.gybcf = et_gyxzdbcf.text.toString()
                                                entity.kfjsmj = et_kfjszdmj.text.toString()
                                                entity.kfjsbcf = et_kfjszdbcf.text.toString()
                                                entity.qtzdmj = et_qtzdmj.text.toString()
                                                entity.qtzdbcf = et_qtzdbcf.text.toString()


                        //                        entity.years = spinner.selectedItem.toString()
                                                entity.years = tv_pop_add_zheng.text.toString()
                                                entity.code = AppCache.getInstance().code
                                                entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                                                entity.process = 4
                                                entity.id = bczdqkEntitysId
                                                //修改接口
                                                if (et_czrname.text.toString().equals("")){
                                                    ToastUtils.showShort("请输入操作员姓名！")
                                                }else{
                                                    entity.qvname = et_czrname.text.toString()
                                                    mPresenter.getBcjcUpdateZhengdqk(entity)
                                                }
                                            }
                                            //区 驳回
                                            reject.setOnClickListener {
                                                val entity = BczdqkEntity()
                                                entity.zdsj = popAddTdqsEtZdsj.text.toString()
                                                entity.zdmj = et_zdmj.text.toString()
                                                entity.gyzdmj = et_gyxzdmj.text.toString()
                                                entity.gybcf = et_gyxzdbcf.text.toString()
                                                entity.kfjsmj = et_kfjszdmj.text.toString()
                                                entity.kfjsbcf = et_kfjszdbcf.text.toString()
                                                entity.qtzdmj = et_qtzdmj.text.toString()
                                                entity.qtzdbcf = et_qtzdbcf.text.toString()
                        //                        entity.years = spinner.selectedItem.toString()
                                                entity.years = tv_pop_add_zheng.text.toString()
                                                entity.code = AppCache.getInstance().code
                                                entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                                                entity.process = 1
                                                entity.id = bczdqkEntitysId
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
                                                    mPresenter.getBcjcUpdateZhengdqk(entity)
                                                }
                                            }
                                        }
                                        3->{//镇
                                            confirm.text = "下一步"
                                            reject.text = "驳回"

                                            //镇 下一步
                                            confirm.setOnClickListener {
                                                val entity = BczdqkEntity()
                                                entity.zdsj = popAddTdqsEtZdsj.text.toString()
                                                entity.zdmj = et_zdmj.text.toString()
                                                entity.gyzdmj = et_gyxzdmj.text.toString()
                                                entity.gybcf = et_gyxzdbcf.text.toString()
                                                entity.kfjsmj = et_kfjszdmj.text.toString()
                                                entity.kfjsbcf = et_kfjszdbcf.text.toString()
                                                entity.qtzdmj = et_qtzdmj.text.toString()
                                                entity.qtzdbcf = et_qtzdbcf.text.toString()
                        //                        entity.years = spinner.selectedItem.toString()
                                                entity.years = tv_pop_add_zheng.text.toString()
                                                entity.code = AppCache.getInstance().code
                                                entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                                                entity.process = 3
                                                entity.id = bczdqkEntitysId
                                                //修改接口
                                                if (et_czrname.text.toString().equals("")){
                                                    ToastUtils.showShort("请输入操作员姓名！")
                                                }else{
                                                    entity.zhenname = et_czrname.text.toString()
                                                    mPresenter.getBcjcUpdateZhengdqk(entity)
                                                }
                                            }
                                            //镇 驳回
                                            reject.setOnClickListener {
                                                val entity = BczdqkEntity()
                                                entity.zdsj = popAddTdqsEtZdsj.text.toString()
                                                entity.zdmj = et_zdmj.text.toString()
                                                entity.gyzdmj = et_gyxzdmj.text.toString()
                                                entity.gybcf = et_gyxzdbcf.text.toString()
                                                entity.kfjsmj = et_kfjszdmj.text.toString()
                                                entity.kfjsbcf = et_kfjszdbcf.text.toString()
                                                entity.qtzdmj = et_qtzdmj.text.toString()
                                                entity.qtzdbcf = et_qtzdbcf.text.toString()
                        //                        entity.years = spinner.selectedItem.toString()
                                                entity.years = tv_pop_add_zheng.text.toString()
                                                entity.code = AppCache.getInstance().code
                                                entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                                                entity.process = 1
                                                entity.id = bczdqkEntitysId
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
                                                    mPresenter.getBcjcUpdateZhengdqk(entity)
                                                }
                                            }
                                        }*/
                                    4 -> {//村
                                        confirm.text = "下一步"
                                        reject.text = "保存"
                                        //村 添加和保存按钮
                                        reject.setOnClickListener {
                                            val entity = BczzdqkEntity()
                                            entity.zdsj = popAddTdqsEtZdsj.text.toString()
                                            entity.zdmj = et_zdmj.text.toString()
                                            entity.gyzdmj = et_gyxzdmj.text.toString()
                                            entity.gybcf = et_gyxzdbcf.text.toString()
                                            entity.kfjsmj = et_kfjszdmj.text.toString()
                                            entity.kfjsbcf = et_kfjszdbcf.text.toString()
                                            entity.qtzdmj = et_qtzdmj.text.toString()
                                            entity.qtzdbcf = et_qtzdbcf.text.toString()

                                            entity.zhandmj = et_zdmj_zhan.text.toString()
                                            entity.jcmj = et_jcsszdmj_zhan.text.toString()
                                            entity.jcbcf = et_jcsszdbcf_zhan.text.toString()
                                            entity.jdmj = et_jdzfzdmj_zhan.text.toString()
                                            entity.jdbcf = et_jdzfzdbcf_zhan.text.toString()
                                            entity.kfqmj = et_kfqzdmj_zhan.text.toString()
                                            entity.kfqbcf = et_kfqzdbcf_zhan.text.toString()
                                            entity.gysymj = et_gysyzdmj_zhan.text.toString()
                                            entity.gysybcf = et_gysyzdbcf_zhan.text.toString()
                                            entity.qtmj = et_qtzdmj_zhan.text.toString()
                                            entity.qtbcf = et_qtzdbcf_zhan.text.toString()

//                        entity.years = spinner.selectedItem.toString()
                                            entity.years = tv_pop_add_zheng.text.toString()
                                            entity.code = AppCache.getInstance().code
                                            entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                                            entity.process = 1
                                            if (et_czrname.text.toString().equals("")) {
                                                ToastUtils.showShort("请输入操作员姓名！")
                                            } else if (popAddTdqsEtZdsj.text.toString().equals("")) {
                                                ToastUtils.showShort("请选择征占地时间")
                                            }/*else if (TdlyUtils.setEditToast(et_zdmj,et_gyxzdmj,et_gyxzdbcf,et_kfjszdmj,et_kfjszdbcf,et_qtzdmj,et_qtzdbcf)){
                        }*/ else {
                                                entity.cunname = et_czrname.text.toString()
                                                val arrayList = ArrayList<BczzdqkEntity>()
                                                entity.id = bczdqkEntitysId
                                                arrayList.add(entity)
                                                mPresenter.getBcjcAddZhengdqk(arrayList)

                                                /* if (bczdqkEntitysId == 0L){
                                                         bczdqkEntity = entity
                                                         mPresenter.getBcjcAddZhengdqk(entity)
                                                     }else{
                                                         entity.id = bczdqkEntitysId
                                                         bczdqkEntity = entity
                                                         mPresenter.getBcjcUpdateZhengdqk(entity)
                                                     }*/
                                            }

                                        }
                                        //村 下一步
                                        /*confirm.setOnClickListener {
                                                val entity = BczdqkEntity()
                                                entity.zdsj = popAddTdqsEtZdsj.text.toString()
                                                entity.zdmj = et_zdmj.text.toString()
                                                entity.gyzdmj = et_gyxzdmj.text.toString()
                                                entity.gybcf = et_gyxzdbcf.text.toString()
                                                entity.kfjsmj = et_kfjszdmj.text.toString()
                                                entity.kfjsbcf = et_kfjszdbcf.text.toString()
                                                entity.qtzdmj = et_qtzdmj.text.toString()
                                                entity.qtzdbcf = et_qtzdbcf.text.toString()
                        //                        entity.years = spinner.selectedItem.toString()
                                                entity.years = tv_pop_add_zheng.text.toString()
                                                entity.code = AppCache.getInstance().code
                                                entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                                                entity.process = 2
                                                //修改接口
                                                if (et_czrname.text.toString().equals("")){
                                                    ToastUtils.showShort("请输入操作员姓名！")
                                                }else if (popAddTdqsEtZdsj.text.toString().equals("")){
                                                    ToastUtils.showShort("请选择征地时间")
                                                }else if (TdlyUtils.setEditToast(et_zdmj,et_gyxzdmj,et_gyxzdbcf,et_kfjszdmj,et_kfjszdbcf,et_qtzdmj,et_qtzdbcf)){
                                                }else{
                                                    entity.cunname = et_czrname.text.toString()
                                                    if (bczdqkEntitysId == 0L){
                                                        mPresenter.getBcjcAddZhengdqk(entity)
                                                    }else{
                                                        entity.id = bczdqkEntitysId
                                                        mPresenter.getBcjcUpdateZhengdqk(entity)
                                                    }
                                                }
                                            }*/
                                    }
                                }

                                close.setOnClickListener {
                                    addZhengdqkUpPopu!!.dismiss()
                                }
//                                }
//                            }
                            }
                        }
                        if (item.bcrejectedEntities.size>0){
                            tv_frag_zjd_tdly_gzjdbh.visibility = View.VISIBLE
                            iv_frag_zjd_tdly_gzjdbh.visibility = View.VISIBLE
                            tv_frag_zjd_tdly_gzjdbh.setTextColor(Color.parseColor("#1B96EE"))
                            /*years.setTextColor(Color.parseColor("#ff6000"))
                            years.isEnabled = true*/
                        }else{
                            tv_frag_zjd_tdly_gzjdbh.visibility = View.GONE
                            iv_frag_zjd_tdly_gzjdbh.visibility = View.GONE
                            tv_frag_zjd_tdly_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                            /*years.setTextColor(Color.parseColor("#000000"))
                            years.isEnabled = false*/
                        }
                        tv_frag_zjd_tdly_gzjdbh.setOnClickListener {
                            rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                        }

                    }

                }
            }
            //bc_frag_tdqs_lnzdqk


        }else{
            ToastUtils.showShort("该年份暂无数据")
        }

    }
    //驳回弹出框
    private var rejectUpPopu: CommenPop? = null
    fun rejectUpPopup(list :ArrayList<BcrejectedEntity>){
        rejectUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject_list, ll_zjd_tdly_year)
        val contentView: View = rejectUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_reject_list_rlv)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_reject_list_close)
        CommenPop.backgroundAlpha(0.5f, activity)
        rejectUpPopu!!.showAtLocation(ll_zjd_tdly_year, Gravity.CENTER, 0, 0)
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

    override fun returnBchcGetKnzd(msg: List<BczzdqkEntity>) {
        if (msg!=null){
            if (msg.size>0){
                if (AppCache.getInstance().type!=4){
                    tlt_frag_zjd_tdly_zdqk.visibility = View.VISIBLE
                }
//                ll_frag_tdqs_lnzdqk.visibility = View.VISIBLE
//                tlt_frag_zjd_tdly_zdqk.getTabAt(tlt_frag_zjd_tdly_zdqk.selectedTabPosition)!!.select()
//                ll_frag_tdqs_lnzdbcqk.visibility = View.VISIBLE
                val strings = ArrayList<String>()//
                val colors = ArrayList<Int>()//颜色
                val chartDataMap = LinkedHashMap<String, ArrayList<Float>>()//年
                for (i in ColorTemplate.LIBERTY_COLORS.indices) {
                    colors.add(ColorTemplate.LIBERTY_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.JOYFUL_COLORS.indices) {
                    colors.add(ColorTemplate.JOYFUL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.PASTEL_COLORS.indices) {
                    colors.add(ColorTemplate.PASTEL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in msg){
                    if (chartDataMap.get(i.years)==null){
                        chartDataMap.put(i.years,ArrayList<Float>())
                    }
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.gyzdmj))
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.kfjsmj))
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.qtzdmj))
                    /*floatList.add(PieTdxgUtil.getStringToFloat(i.gyzdmj))
                    floatList1.add(PieTdxgUtil.getStringToFloat(i.kfjsmj))
                    floatList2.add(PieTdxgUtil.getStringToFloat(i.qtzdmj))*/
                }

                strings.add("公益性征地")
                strings.add("开发建设征地")
                strings.add("其他征地")
                if (msg.size>1){
                    PieTdxgUtil.setZzt(strings,colors,chartDataMap,bc_frag_tdqs_lnzdqk)
                }else  if (msg.size==1){
                    var floatList = ArrayList<Float>()
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).gyzdmj))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).kfjsmj))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).qtzdmj))
                    PieTdxgUtil.zjdSpBar(activity!!,floatList,strings,bc_frag_tdqs_lnzdqk)
                }
                val strings1 = ArrayList<String>()//
                val colors1 = ArrayList<Int>()//颜色
                val chartDataMap1 = LinkedHashMap<String, ArrayList<Float>>()//年
                for (i in ColorTemplate.COLORFUL_COLORS.indices) {
                    colors1.add(ColorTemplate.COLORFUL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.VORDIPLOM_COLORS.indices) {
                    colors1.add(ColorTemplate.VORDIPLOM_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.MATERIAL_COLORS.indices) {
                    colors1.add(ColorTemplate.MATERIAL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in msg){
                    if (chartDataMap1.get(i.years)==null){
                        chartDataMap1.put(i.years,ArrayList<Float>())
                    }
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.gybcf))
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.kfjsbcf))
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.qtzdbcf))
                    /*floatList.add(PieTdxgUtil.getStringToFloat(i.gyzdmj))
                    floatList1.add(PieTdxgUtil.getStringToFloat(i.kfjsmj))
                    floatList2.add(PieTdxgUtil.getStringToFloat(i.qtzdmj))*/
                }

                strings1.add("公益性征地")
                strings1.add("开发建设征地")
                strings1.add("其他征地")
                if (msg.size>1){
                    PieTdxgUtil.setZzt(strings1,colors1,chartDataMap1,bc_frag_tdqs_lnzdbcqk)
                }else  if (msg.size==1){
                    var floatList = ArrayList<Float>()
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).gybcf))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).kfjsbcf))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).qtzdbcf))
                    PieTdxgUtil.zjdSpBar(activity!!,floatList,strings1,bc_frag_tdqs_lnzdbcqk)
                }

            }else{
                ll_frag_tdqs_lnzdqk.visibility = View.GONE
                ll_frag_tdqs_lnzdbcqk.visibility = View.GONE
                tlt_frag_zjd_tdly_zdqk.visibility = View.GONE
            }
        }else{
            ll_frag_tdqs_lnzdqk.visibility = View.GONE
            ll_frag_tdqs_lnzdbcqk.visibility = View.GONE
            tlt_frag_zjd_tdly_zdqk.visibility = View.GONE
        }

    }

    override fun returnBcjcAddZhandqk(msg: String) {

        ToastUtils.showShort(msg)
        if (addZhandqkUpPopu!=null){
            addZhandqkUpPopu!!.dismiss()
        }
//        mPresenter.getBchcZhandqk(AppCache.getInstance().code,yearsList)
//        mPresenter.getBchcZhandqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcGetInzhand(AppCache.getInstance().code,yearsList)
    }
    override fun returnBcjcUpdateZhandqk(msg: String) {
        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){//addZhandqkUpPopu
            mUploadPopu!!.dismiss()
        }
//        mPresenter.getBchcZhandqk(AppCache.getInstance().code,yearsList)
//        mPresenter.getBchcZhandqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcGetInzhand(AppCache.getInstance().code,yearsList)
    }

    override fun returnBchcZhandqk(msg: List<BczhandqkEntity>) {

        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addZhandqkUpPopu!=null){
            addZhandqkUpPopu!!.dismiss()
        }
        if (msg!=null){//&&msg.size>0
            if (msg.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = msg.get(0)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&
                        ((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_tdqs_zhandqk_add.visibility = View.VISIBLE
                    tv_frag_tdqs_zhandqk_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_tdqs_zhandqk_add.visibility = View.VISIBLE
                        tv_frag_tdqs_zhandqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_tdqs_zhandqk_add.visibility = View.VISIBLE
                            tv_frag_tdqs_zhandqk_add.setText("操作")
                        }else{

                            if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_tdqs_zhandqk_add.setText("已上报")
                            }else{
                                tv_frag_tdqs_zhandqk_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_tdqs_zhandqk_add.visibility = View.GONE
                            }
                        }
                tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,get.process-1,get.process-1)
            }else if (AppCache.getInstance().type==4){
                tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,-1,-1)
                tv_frag_tdqs_zhandqk_add.visibility = View.VISIBLE
            }else if (AppCache.getInstance().type!=4){
                tll_frag_zjd_tdly_qkjcb.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_tdqs_zhandqk_add.visibility = View.GONE
                }
                tv_frag_tdqs_zhandqk_add.setText("未上报")
            }
            rlv_frag_tdqs_zhandqk.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_tdqs_zhandqk.adapter = object :BaseQuickAdapter<BczhandqkEntity, BaseViewHolder>(R.layout.item_bcjc_zhandqk,msg){
                override fun convert(helper: BaseViewHolder?, item: BczhandqkEntity?) {
                    helper!!.setText(R.id.item_bcjc_zhandqk_year,item!!.years.replace("年",""))
                            .setText(R.id.item_bcjc_zhandqk_zdsj,item.zdsj)
                            .setText(R.id.item_bcjc_zhandqk_zdmj,item.zdmj)
                            .setText(R.id.item_bcjc_zhandqk_jczdmj,item.jcmj)
                            .setText(R.id.item_bcjc_zhandqk_jcbcf,item.jcbcf)
                            .setText(R.id.item_bcjc_zhandqk_jdzfmj,item.jdmj)
                            .setText(R.id.item_bcjc_zhandqk_jdzfbcf,item.jdbcf)
                            .setText(R.id.item_bcjc_zhandqk_kfqmj,item.kfqmj)
                            .setText(R.id.item_bcjc_zhandqk_kfqbcf,item.kfqbcf)
                            .setText(R.id.item_bcjc_zhandqk_gyzfmj,item.gysymj)
                            .setText(R.id.item_bcjc_zhandqk_gybcf,item.gysybcf)
                            .setText(R.id.item_bcjc_zhandqk_qtmj,item.qtmj)
                            .setText(R.id.item_bcjc_zhandqk_qtbcf,item.qtbcf)


                }

            }
        }else{
            ToastUtils.showShort("该年份暂无数据")
        }
    }

    override fun returnBchcZhengdqkAll(msg: List<BczzdqkEntity>) {
        bczdqkEntitys.clear()
        bczdqkEntitys.addAll(msg)
        if (msg.size == 0){
            bt_frag_zjd_tdly_xyb.visibility = View.GONE
            bt_frag_zjd_tdly_bh.visibility = View.GONE
            return
        }
        for (i in msg){
            if (i.years.contains(split)){

                /*bczdqkEntity.zdsj = i.zdsj
                bczdqkEntity.zdmj = i.zdmj
                bczdqkEntity.gyzdmj = i.gyzdmj
                bczdqkEntity.gybcf = i.gybcf
                bczdqkEntity.kfjsmj = i.kfjsmj
                bczdqkEntity.kfjsbcf = i.kfjsbcf
                bczdqkEntity.qtzdmj = i.qtzdmj
                bczdqkEntity.qtzdbcf = i.qtzdbcf
                bczdqkEntity.zhandmj = i.zdmj
                bczdqkEntity.jcmj = i.jcmj
                bczdqkEntity.jcbcf = i.jcbcf
                bczdqkEntity.jdmj = i.jdmj
                bczdqkEntity.jdbcf = i.jdbcf
                bczdqkEntity.kfqmj = i.kfqmj
                bczdqkEntity.kfqbcf = i.kfqbcf
                bczdqkEntity.gysymj = i.gysymj
                bczdqkEntity.gysybcf = i.gysybcf
                bczdqkEntity.qtmj = i.qtmj
                bczdqkEntity.qtbcf = i.qtbcf




                bczdqkEntity.years = i.years
                bczdqkEntity.code = AppCache.getInstance().code
                bczdqkEntity.xzqmc = i.xzqmc*/
                bczdqkEntityList.add(i)


                bczdqkEntitysId = i.id
                bczdqkEntitysProcess = i.process
            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_frag_zjd_tdly_xyb.text = "提交上报"
            if (bczdqkEntitysProcess == 1&&process == 1){
                bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
            }else{
                bt_frag_zjd_tdly_xyb.visibility = View.GONE
            }
        }else{
            bt_frag_zjd_tdly_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bczdqkEntitysProcess == 2&&process == 2){
                    bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
                    bt_frag_zjd_tdly_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_zjd_tdly_xyb.visibility = View.GONE
                    bt_frag_zjd_tdly_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bczdqkEntitysProcess == 3&&process == 3){
                    bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
                    bt_frag_zjd_tdly_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_zjd_tdly_xyb.visibility = View.GONE
                    bt_frag_zjd_tdly_bh.visibility = View.GONE
                }
            }else{
                bt_frag_zjd_tdly_xyb.visibility = View.GONE
                bt_frag_zjd_tdly_bh.visibility = View.GONE
            }
        }

    }
    var bczhandqkEntitys = ArrayList<BczhandqkEntity>()
    override fun returnBchcZhandqkAll(msg: List<BczhandqkEntity>) {
        bczhandqkEntitys.clear()
        bczhandqkEntitys.addAll(msg)
        if (msg.size == 0){
            bt_frag_zjd_tdly_xyb.visibility = View.GONE
            bt_frag_zjd_tdly_bh.visibility = View.GONE
            return
        }
        for (i in msg){
            if (i.years.contains(split)){

                bczhandqkEntity.zdsj = i.zdsj
                bczhandqkEntity.zdmj = i.zdmj
                bczhandqkEntity.jcmj = i.jcmj
                bczhandqkEntity.jcbcf = i.jcbcf
                bczhandqkEntity.jdmj = i.jdmj
                bczhandqkEntity.jdbcf = i.jdbcf
                bczhandqkEntity.kfqmj = i.kfqmj
                bczhandqkEntity.kfqbcf = i.kfqbcf
                bczhandqkEntity.gysymj = i.gysymj
                bczhandqkEntity.gysybcf = i.gysybcf
                bczhandqkEntity.qtmj = i.qtmj
                bczhandqkEntity.qtbcf = i.qtbcf
                bczhandqkEntity.years = i.years
                bczhandqkEntity.code = AppCache.getInstance().code
                bczhandqkEntity.xzqmc = i.xzqmc
                bczhandqkEntitysId = i.id
                bczhandqkEntitysProcess = i.process

            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_frag_zjd_tdly_xyb.text = "提交上报"
            if (bczdqkEntitysProcess == 1&&process == 1){
                bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
            }else{
                bt_frag_zjd_tdly_xyb.visibility = View.GONE
            }
        }else{
            bt_frag_zjd_tdly_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bczdqkEntitysProcess == 2&&process == 2){
                    bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
                    bt_frag_zjd_tdly_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_zjd_tdly_xyb.visibility = View.GONE
                    bt_frag_zjd_tdly_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bczdqkEntitysProcess == 3&&process == 3){
                    bt_frag_zjd_tdly_xyb.visibility = View.VISIBLE
                    bt_frag_zjd_tdly_bh.visibility = View.VISIBLE
                }else{
                    bt_frag_zjd_tdly_xyb.visibility = View.GONE
                    bt_frag_zjd_tdly_bh.visibility = View.GONE
                }
            }else{
                bt_frag_zjd_tdly_xyb.visibility = View.GONE
                bt_frag_zjd_tdly_bh.visibility = View.GONE
            }
        }

    }

    override fun returnBchcGetInzhand(msg: List<BczhandqkEntity>) {
        if (msg!=null){
            if (msg.size>0){
//                tlt_frag_zjd_tdly_zdqk1.getTabAt(tlt_frag_zjd_tdly_zdqk1.selectedTabPosition)!!.select()
                if (AppCache.getInstance().type!=4){
                    tlt_frag_zjd_tdly_zdqk1.visibility = View.VISIBLE
                }
//                ll_frag_tdqs_lnzhdqk.visibility = View.VISIBLE
//                ll_frag_tdqs_lnzhdbcqk.visibility = View.VISIBLE
                val strings = ArrayList<String>()//
                val colors = ArrayList<Int>()//颜色
                val chartDataMap = LinkedHashMap<String, ArrayList<Float>>()//年
                for (i in ColorTemplate.LIBERTY_COLORS.indices) {
                    colors.add(ColorTemplate.LIBERTY_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.JOYFUL_COLORS.indices) {
                    colors.add(ColorTemplate.JOYFUL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.PASTEL_COLORS.indices) {
                    colors.add(ColorTemplate.PASTEL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in msg){
                    if (chartDataMap.get(i.years)==null){
                        chartDataMap.put(i.years,ArrayList<Float>())
                    }
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.jcmj))
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.jdmj))
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.kfqmj))
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.gysymj))
                    chartDataMap.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.qtmj))
                    /*floatList.add(PieTdxgUtil.getStringToFloat(i.gyzdmj))
                    floatList1.add(PieTdxgUtil.getStringToFloat(i.kfjsmj))
                    floatList2.add(PieTdxgUtil.getStringToFloat(i.qtzdmj))*/
                }

                strings.add("基础设施")
                strings.add("军队、政府")
                strings.add("开发区")
                strings.add("公益事业")
                strings.add("其他")
                if (msg.size>1){
                    PieTdxgUtil.setZzt(strings,colors,chartDataMap,bc_frag_tdqs_lnzhdqk)
                }else  if (msg.size==1){
                    var floatList = ArrayList<Float>()
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).jcmj))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).jdmj))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).kfqmj))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).gysymj))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).qtmj))
                    PieTdxgUtil.zjdSpBar(activity!!,floatList,strings,bc_frag_tdqs_lnzhdqk)
                }
                val strings1 = ArrayList<String>()//
                val colors1 = ArrayList<Int>()//颜色
                val chartDataMap1 = LinkedHashMap<String, ArrayList<Float>>()//年
                for (i in ColorTemplate.COLORFUL_COLORS.indices) {
                    colors1.add(ColorTemplate.COLORFUL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.VORDIPLOM_COLORS.indices) {
                    colors1.add(ColorTemplate.VORDIPLOM_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in ColorTemplate.MATERIAL_COLORS.indices) {
                    colors1.add(ColorTemplate.MATERIAL_COLORS.get(i)) //MATERIAL_COLORS
                }
                for (i in msg){
                    if (chartDataMap1.get(i.years)==null){
                        chartDataMap1.put(i.years,ArrayList<Float>())
                    }
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.jcbcf))
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.jdbcf))
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.kfqbcf))
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.gysybcf))
                    chartDataMap1.get(i.years)!!.add(PieTdxgUtil.getStringToFloat(i.qtbcf))
                    /*floatList.add(PieTdxgUtil.getStringToFloat(i.gyzdmj))
                    floatList1.add(PieTdxgUtil.getStringToFloat(i.kfjsmj))
                    floatList2.add(PieTdxgUtil.getStringToFloat(i.qtzdmj))*/
                }

                strings1.add("基础设施")
                strings1.add("军队、政府")
                strings1.add("开发区")
                strings1.add("公益事业")
                strings1.add("其他")
                if (msg.size>1){
                    PieTdxgUtil.setZzt(strings1,colors1,chartDataMap1,bc_frag_tdqs_lnzhdbcqk)
                }else  if (msg.size==1){
                    var floatList = ArrayList<Float>()
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).jcbcf))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).jdbcf))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).kfqbcf))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).gysybcf))
                    floatList.add(PieTdxgUtil.getStringToFloat(msg.get(0).qtbcf))
                    PieTdxgUtil.zjdSpBar(activity!!,floatList,strings1,bc_frag_tdqs_lnzhdbcqk)
                }

            }else{
                ll_frag_tdqs_lnzhdqk.visibility = View.GONE
                ll_frag_tdqs_lnzhdbcqk.visibility = View.GONE
                tlt_frag_zjd_tdly_zdqk1.visibility = View.GONE
            }
        }else{
            ll_frag_tdqs_lnzhdqk.visibility = View.GONE
            ll_frag_tdqs_lnzhdbcqk.visibility = View.GONE
            tlt_frag_zjd_tdly_zdqk1.visibility = View.GONE
        }

    }

    //点击回调
    interface OnZjdTdlyLister {
        fun onClickTdly(timeLineBean: TdlyVO, position: Int)
        fun onClickYztSkt(timeLineBean: YztSktVO, position: Int)
        fun onClickQs(timeLineBean: QsVO, position: Int)
        fun onClickGh(timeLineBean: GhVO, position: Int)
    }

}
