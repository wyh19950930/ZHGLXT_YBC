package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.HousingLeaseEntity
import com.jymj.zhglxt.zjd.bean.wy.LeaseDto
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.wy.RentEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface PropertyYlfjContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnWyPoint(entity: PropertyDto)
        fun returnFlowGetInfo(entity: List<FlowInfoEntity>)
        fun returnHistoryTenant(entity: List<HousingLeaseEntity>)
        fun returnLeaseInfo(entity: LeaseDto)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getWyPoint(point:String,type:Int)
            abstract fun getFlowGetInfo(ylid:Long)
            abstract fun getLeaseInfo(roomId:Long)
            abstract fun getHistoryTenant(housid:Long)
        }
    }
}