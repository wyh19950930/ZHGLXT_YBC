package com.jymj.basemessagesystem.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyDetail
import com.jymj.zhglxt.zjd.bean.tdxg.TdlyVO
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Administrator on 2018/1/11 create this class
 */

class TdghDetailAdapter(layoutResId: Int, data: MutableList<TdlyVO>?) : BaseQuickAdapter<TdlyVO, BaseViewHolder>(layoutResId, data){
    private val mDf = DecimalFormat("#0")
    override fun convert(helper: BaseViewHolder?, item: TdlyVO?) {
        helper?.setText(R.id.tv_mph_polygon_detail,item?.qsdwmc)
                ?.setText(R.id.tv_rk_polygon_detail,"村名:"+item?.xzqmc)
                ?.setText(R.id.tv_hz_polygon_detail,"地类名称:"+item?.dlmc)
//                ?.setText(R.id.tv_mj_polygon_detail,mDf.format(item?.area).toString()+"公顷")

        if (item?.area!!.compareTo(BigDecimal(10000)) > 0){
            helper?.setText(R.id.tv_mj_polygon_detail,"面积:"+mDf.format(item.area.divide(BigDecimal(10000),2, RoundingMode.HALF_UP)).toString()+"公顷")
        }else{
            helper?.setText(R.id.tv_mj_polygon_detail,"面积:"+mDf.format(item.area).toString()+"㎡")
        }
    }

}
