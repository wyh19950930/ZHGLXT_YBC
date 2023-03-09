package com.jymj.basemessagesystem.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.yl.cg.CgDetail
import java.text.DecimalFormat

/**
 * Administrator on 2018/1/11 create this class
 */

class CgDetailAdapter(layoutResId: Int, data: MutableList<CgDetail.CgListBean>?) : BaseQuickAdapter<CgDetail.CgListBean, BaseViewHolder>(layoutResId, data){
    private val mDf = DecimalFormat("#0")
    override fun convert(helper: BaseViewHolder?, item: CgDetail.CgListBean?) {
        helper?.setText(R.id.tv_mph_polygon_detail,item?.gid.toString())
                ?.setText(R.id.tv_rk_polygon_detail,item?.rjl.toString())
                ?.setText(R.id.tv_xz_polygon_detail,item?.jzgm.toString())
                ?.setText(R.id.tv_hz_polygon_detail,item?.landName)
                ?.setText(R.id.tv_mj_polygon_detail,mDf.format(item?.area).toString()+"公顷")
    }

}
