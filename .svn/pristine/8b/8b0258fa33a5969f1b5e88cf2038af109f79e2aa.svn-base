package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.yl.ApplyCountEntity
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import java.math.BigDecimal

interface YdlrContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnApplyByPoint(fjBean: List<ApplyLandEntity>)


        fun returnFanJian(fjBean: FjBean)
        fun returnFjxcList(fjBean:List<Renovated>, qutype:Int, count:Int)
        fun returnFanJianZb(fjBean:List<StatisticalEntity>)
        fun returnSteps(fjNumBean: FjNumBean)
        fun returnEnviorsByxy(fjNumBean: List<ApplyCountEntity>, type:String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal)
        fun returnFanJianTjList(fjBean:List<ZjdfjTjBean>)//宅基地总览列表
        fun returnFanJianApplyList(fjBean:List<ApplyEntity>, sptype:Int, count:Int)//宅基地各镇列表


    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getApplyByPoint(point: String)
            abstract fun getApplyInfo(applyId: Int)


            abstract fun getFanJian(point: String)
            abstract fun getFanJianZb(date: String)
            abstract fun getFjxcList(lastSelect:Int,limit:Int,page:Int,cunCode:String,date:String,bh:Int)
            abstract fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, code:String)
            abstract fun getSteps(lastSelect:Int,limit:Int,page:Int,cunCode:String,date:String,name: String)
            abstract fun getFanJianTjList(date: String)
            abstract fun getFanJianApplyList(sptype: Int,limit:Int,page:Int,code: String, date: String, name: String)
        }
    }
}