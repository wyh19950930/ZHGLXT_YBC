package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.jsjb.LightBean
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEntity
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEnumBean
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface AjlbActContract {
    interface Model : BaseModel {
        fun getEnvironmental(proId: String): String
    }

    interface View : BaseView {
        fun returnFanJianDetail(renovated: Renovated)
        fun returnEnumGetOpinion(renovated: OpinionEnumBean)
        fun returnOpinionGetList(renovated: List<OpinionEntity>)
        fun returnLightGetList(renovated: List<LightBean>)
        fun returnSysXzqList(renovated: List<SysXzqEntity>)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getFanJianDetail(id:Long)//详情
            abstract fun getEnumGetOpinion()//
            abstract fun getOpinionGetList(page:Int,limit:Int,title:String,qutype:Int)//详情
            abstract fun getLightGetList()//
            abstract fun getSysXzqList(code:String,type:Int)
        }
    }
}