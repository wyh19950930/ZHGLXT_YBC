package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.LeaseDto
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.wy.RentEntity
import com.jymj.zhglxt.zjd.bean.wy.WithdrawalLeaseDto
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface PropertyRzxxContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnWyPoint(entity: PropertyDto)
        fun returnPropertyAddLeaseInfo(msg: String)
        fun returnPropertyRenewalLeaseInfo(msg: String)
        fun returnPropertyWithdrawalLeaseInfo(msg: String)
        fun returnZlFileDetel(message:String,position:Int)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getWyPoint(point:String,type:Int)
            abstract fun getPropertyAddLeaseInfo(entity: LeaseDto)
            abstract fun getPropertyRenewalLeaseInfo(entity: LeaseDto)
            abstract fun getPropertyWithdrawalLeaseInfo(entity: WithdrawalLeaseDto)
            abstract fun getZlFileDetel(ids:Long,position:Int)
        }
    }
}