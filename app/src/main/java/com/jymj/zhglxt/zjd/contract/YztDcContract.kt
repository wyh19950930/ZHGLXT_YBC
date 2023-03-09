package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBusiness
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface YztDcContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnBusiness(message: List<EnterpriseBusiness>,month:String)
        fun returnFanJianApplyList(fjBean:List<ApplyEntity>, sptype:Int, count:Int)//宅基地各镇列表

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getBusiness(id: Long,month:String)
            abstract fun getFanJianApplyList(sptype: Int,limit:Int,page:Int,code: String, date: String, name: String)

        }
    }
}