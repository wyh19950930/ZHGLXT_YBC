package com.jymj.zhglxt.zjd.fragment.tx


import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.utils.ColorTemplate

import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.PieTdxgUtil
import com.jymj.zhglxt.util.TdlyUtils
import com.jymj.zhglxt.util.TimePickerUtil
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtzcEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczhandqkEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.ZjdTdqsContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.NyyqPresenter
import com.jymj.zhglxt.zjd.presenter.ZjdTdqsPresenter
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjd_ldrk.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_tdqs.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ZjdTdqsFragment : BaseFragment<ZjdTdqsPresenter, BaseModel>(), ZjdTdqsContract.View{



    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdTdqsFragment {
            return ZjdTdqsFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_tdqs
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {



    }

    override fun initDatas() {

    }

    fun diaoyong(){
        if (supl_frag_zjdgl1!=null){
            supl_frag_zjdgl1?.setScrollableView(slv_frag_zjd_tdqs)
        }
        mPresenter.getBcjcYears()
        /*mPresenter.getBchcZhengdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcZhengdqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcGetInzhand(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcZhandqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcZhandqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcGetKnzd(AppCache.getInstance().code,yearsList)*/
    }

    override fun returnBcjcYears(msg: List<String>) {

    }

    override fun returnBchcGetKnzd(msg: List<BczdqkEntity>) {

    }

    override fun returnBchcGetInzhand(msg: List<BczhandqkEntity>) {
    }

    override fun returnBcjcAddZhengdqk(msg: String) {
    }
    override fun returnBchcZhengdqk(msg: List<BczdqkEntity>) {
    }

    override fun returnBcjcAddZhandqk(msg: String) {
    }
    override fun returnBchcZhandqk(msg: List<BczhandqkEntity>) {
    }


    var bczdqkEntitys = ArrayList<BczdqkEntity>()
    override fun returnBchcZhengdqkAll(msg: List<BczdqkEntity>) {
        bczdqkEntitys.clear()
        bczdqkEntitys.addAll(msg)
    }

    var bczhandqkEntitys = ArrayList<BczhandqkEntity>()
    override fun returnBchcZhandqkAll(msg: List<BczhandqkEntity>) {
        bczhandqkEntitys.clear()
        bczhandqkEntitys.addAll(msg)
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
