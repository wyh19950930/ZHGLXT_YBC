package com.jymj.basemessagesystem.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.YLEntity
import java.text.DecimalFormat

/**
 * Administrator on 2018/1/11 create this class
 */

class PolygonDetailAdapter(layoutResId: Int, data: MutableList<YLEntity>?) : BaseQuickAdapter<YLEntity, BaseViewHolder>(layoutResId, data){
    private val mDf = DecimalFormat("#0")
    override fun convert(helper: BaseViewHolder?, item: YLEntity?) {
        helper?.setText(R.id.tv_mph_polygon_detail,item?.mph)
                ?.setText(R.id.tv_rk_polygon_detail,item?.rk.toString()+"人")
                ?.setText(R.id.tv_hz_polygon_detail,item?.hzmc)
                ?.setText(R.id.tv_mj_polygon_detail,"占地："+mDf.format(item?.area).toString()+"平方米")
                ?.setText(R.id.tv_xz_polygon_detail,"建筑："+mDf.format(item?.jianzhuArea).toString()+"平方米")
    }

}
