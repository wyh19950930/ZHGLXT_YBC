package com.jymj.zhglxt.zjd.fragment.cy

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.CameraPosition
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout

import com.jymj.zhglxt.R
import com.jymj.zhglxt.R.id.*
import com.jymj.zhglxt.zjd.activity.yqgl.NyyqDetailActivity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.yzt.nyd.ZzyNydEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.NyyqPresenter
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_zjd_nyyq.*
import java.math.BigDecimal

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ZjdNyyqFragment : BaseFragment<NyyqPresenter, BaseModel>(), NyyqContract.View{

    private val titles = arrayListOf<String>("村统计", "列表", "地块详情")

    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdNyyqFragment {
            return ZjdNyyqFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_zjd_nyyq
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        ll_frag_nyyq_return.setOnClickListener {
            ll_frag_nyyq_cunlist.visibility = View.VISIBLE
            ll_frag_nyyq_itemlist.visibility = View.GONE
            ll_frag_nyyq_return.visibility = View.GONE
            tv_frag_nyyq_xzqmc.text = "各村统计"
        }
    }

    override fun initDatas() {

    }

    fun diaoyong(){
        if (supl_frag_zjdgl1!=null){
            supl_frag_zjdgl1?.setScrollableView(null)
        }

//        mPresenter.getNyyqCunList()

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

    override fun returnNyyqCunList(entity: List<NydVo>) {
        if (entity!=null&&entity.size>1){

            rlv_map_tj.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            rlv_map_tj.adapter = object : BaseQuickAdapter<NydVo, BaseViewHolder>(R.layout.item_act_tj, entity) {
            override fun convert(helper: BaseViewHolder?, item: NydVo?) {
                helper!!.setText(R.id.tv_tj_cm, item!!.xzqmc)
                        .setText(R.id.tv_tj_lz, item!!.area.toString())
                        .setText(R.id.tv_tj_grzl, item!!.ydmj.toString())
                        .setText(R.id.tv_tj_cjt, item!!.scss.toString())
                        .setText(R.id.tv_tj_hj, item!!.fsss.toString())

                helper.itemView.setOnClickListener {
                    tv_frag_nyyq_xzqmc.text = item.xzqmc
                    mPresenter.getNyyqItemList(item.code)
                }

                if (helper.layoutPosition % 2 == 1) {
                    helper.getView<LinearLayout>(R.id.ll_item_tj).setBackgroundColor(resources.getColor(R.color.gray))
                }


            }
        }
        }
    }

    override fun returnNyyItemList(entity: List<YztNyyqEntity>) {
        rlv_map_clb.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        if (entity!=null&&entity.size>1) {

            ll_frag_nyyq_cunlist.visibility = View.GONE
            ll_frag_nyyq_itemlist.visibility = View.VISIBLE
            ll_frag_nyyq_return.visibility = View.VISIBLE
            tv_frag_nyyq_zwsj.visibility = View.GONE

            rlv_map_clb.adapter = object : BaseQuickAdapter<YztNyyqEntity, BaseViewHolder>(R.layout.item_map_clb, entity) {
                override fun convert(helper: BaseViewHolder?, item: YztNyyqEntity?) {
                    helper!!.setText(R.id.tv_clb_cm, item!!.xzqmc)
                            .setText(R.id.tv_clb_czf, item!!.xmmc)
                            .setText(R.id.tv_clb_area, item!!.ydmj.toString())
                    helper.itemView.setOnClickListener {
                            val intent = Intent(activity,NyyqDetailActivity::class.java)
                            intent.putExtra("nydId",item.gid)
                            startActivity(intent)
                    }

                }
            }
        }else{
            tv_frag_nyyq_zwsj.visibility = View.VISIBLE
        }
    }

}
