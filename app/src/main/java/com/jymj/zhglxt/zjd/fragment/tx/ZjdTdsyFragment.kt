package com.jymj.zhglxt.zjd.fragment.tx


import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.zjd.bean.bcjc.BcSktEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczdqkEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.ZjdSktContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.NyyqPresenter
import com.jymj.zhglxt.zjd.presenter.ZjdSktPresenter
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_yzt_dc.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_tdsy.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *三块田
 */
class ZjdTdsyFragment : BaseFragment<ZjdSktPresenter, BaseModel>(), ZjdSktContract.View {
    private var yearUpPopu: CommenPop? = null
    private var addSktUpPopu: CommenPop? = null

    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdTdsyFragment {
            return ZjdTdsyFragment().apply {
                //                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_tdsy
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        /*tv_frag_tdly_xzsj.setOnClickListener {

            if (yearUpPopu != null) {
                CommenPop.backgroundAlpha(0.5f, activity)
                yearUpPopu!!.showAtLocation(slv_frag_zjd_skt, Gravity.CENTER, 0, 0)
            }
        }*/
    }

    override fun initDatas() {

    }

    fun diaoyong() {
        if (supl_frag_zjdgl1 != null) {
            supl_frag_zjdgl1?.setScrollableView(slv_frag_zjd_skt)
        }
        mPresenter.getBcjcYears()
        mPresenter.getBchcSkt(AppCache.getInstance().code, yearsList)
    }

    private val yearsList = ArrayList<String>()
    private fun yearPopup(yearList: List<String>) {
        yearUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_years, slv_frag_zjd_skt)
        val contentView: View = yearUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_years_rlv)
        val confirm = contentView.findViewById<TextView>(R.id.pop_bcjc_years_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_years_close)


        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_bcjc_years, yearList) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.item_bcjc_years_tv, item)
                val checkBox = helper.getView<CheckBox>(R.id.item_bcjc_years_cb)
                checkBox.setOnClickListener {
                    if (checkBox.isChecked) {
                        yearsList.add(item!!)
                    } else {
                        yearsList.remove(item)
                    }
                }

            }


        }

        confirm.setOnClickListener {
            mPresenter.getBchcSkt(AppCache.getInstance().code, yearsList)
        }
        close.setOnClickListener {
            yearUpPopu!!.dismiss()
        }
    }

    override fun returnBcjcYears(msg: List<String>) {

        yearPopup(msg)
        if (AppCache.getInstance().type == 4){
            tv_frag_skt_add.text = "添加/修改"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_skt_add.visibility = View.GONE
        }else{
            tv_frag_skt_add.text = "操作"
        }

        tv_frag_skt_add.setOnClickListener {
            addSktUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_skt, slv_frag_zjd_skt)
            val contentView: View = addSktUpPopu!!.getContentView()
            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_skt_spr)
            val et_mj = contentView.findViewById<EditText>(R.id.pop_add_skt_et_mj)
            val et_yjjbnt = contentView.findViewById<EditText>(R.id.pop_add_skt_et_yjjbnt)
            val et_yjjbntcbq = contentView.findViewById<EditText>(R.id.pop_add_skt_et_yjjbntcbq)
            val et_gdbylcbq = contentView.findViewById<EditText>(R.id.pop_add_skt_et_gdbylcbq)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_skt_confirm)
            val close = contentView.findViewById<TextView>(R.id.pop_add_skt_close)

            CommenPop.backgroundAlpha(0.5f, activity)
            addSktUpPopu!!.showAtLocation(slv_frag_zjd_skt, Gravity.CENTER, 0, 0)

            val sprAdapter = com.jymj.zhglxt.widget.SpinnerAdapter(activity)
            spinner.adapter = sprAdapter
            sprAdapter.setDatas(msg)
            spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    for (i in bcSktList){
                        if (msg.get(position).equals(i.years)){
                            et_mj.setText(i.area)
                            et_yjjbnt.setText(i.yjjbnt)
                            et_yjjbntcbq.setText(i.ntczq)
                            et_gdbylcbq.setText(i.gbczq)
                            return
                        }else{
                            et_mj.setText("")
                            et_yjjbnt.setText("")
                            et_yjjbntcbq.setText("")
                            et_gdbylcbq.setText("")
                        }
                    }
                }

            }

            confirm.setOnClickListener {
                val entity = BcSktEntity()
                entity.area = et_mj.text.toString()
                entity.yjjbnt = et_yjjbnt.text.toString()
                entity.ntczq = et_yjjbntcbq.text.toString()
                entity.gbczq = et_gdbylcbq.text.toString()
                entity.years = spinner.selectedItem.toString()
                entity.code = AppCache.getInstance().code
                entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                mPresenter.getBcjcAddSkt(entity)
            }

            close.setOnClickListener {
                addSktUpPopu!!.dismiss()
            }
        }
    }

    override fun returnBcjcAddSkt(msg: String) {
        ToastUtils.showShort(msg)
        if (addSktUpPopu != null) {
            addSktUpPopu!!.dismiss()
        }
        mPresenter.getBchcSkt(AppCache.getInstance().code, yearsList)
    }
    var bcSktList = ArrayList<BcSktEntity>()
    override fun returnBchcSkt(msg: List<BcSktEntity>) {
        bcSktList.clear()
        bcSktList.addAll(msg)
        if (yearUpPopu != null) {
            yearUpPopu!!.dismiss()
        }
        if (addSktUpPopu != null) {
            addSktUpPopu!!.dismiss()
        }
        if (msg != null) {
            rlv_frag_skt.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rlv_frag_skt.adapter = object : BaseQuickAdapter<BcSktEntity, BaseViewHolder>(R.layout.item_bcjc_skt, msg) {
                override fun convert(helper: BaseViewHolder?, item: BcSktEntity?) {
                    helper!!.setText(R.id.item_bcjc_skt_year, item!!.years)
                            .setText(R.id.item_bcjc_skt_mj, item.area)
                            .setText(R.id.item_bcjc_skt_yjjbnt, item.yjjbnt)
                            .setText(R.id.item_bcjc_skt_yjjbntcbq, item.ntczq)
                            .setText(R.id.item_bcjc_skt_gdbylcbq, item.gbczq)
                }
            }
        } else {
            ToastUtils.showShort("该年份暂无数据")
        }
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
