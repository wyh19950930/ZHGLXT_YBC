package com.jymj.basemessagesystem.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.zjd.bean.yzt.tg.TgDetail
import java.text.DecimalFormat

/**
 * Administrator on 2018/1/11 create this class
 */

class TgDetailAdapter(layoutResId: Int, data: MutableList<TgDetail.TgEntityListBean>?) : BaseQuickAdapter<TgDetail.TgEntityListBean, BaseViewHolder>(layoutResId, data){
    private val mDf = DecimalFormat("#0")
    override fun convert(helper: BaseViewHolder?, item: TgDetail.TgEntityListBean?) {
        helper?.setText(R.id.tv_mph_polygon_detail,item?.gid.toString())
                ?.setText(R.id.tv_rk_polygon_detail,item?.xzqmc)
                ?.setText(R.id.tv_hz_polygon_detail,item?.landName)
                ?.setText(R.id.tv_mj_polygon_detail,mDf.format(item?.area).toString()+"公顷")
    }

}
