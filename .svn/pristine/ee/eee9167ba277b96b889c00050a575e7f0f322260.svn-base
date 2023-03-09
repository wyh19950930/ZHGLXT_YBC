package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.fj.*
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import java.math.BigDecimal

interface FjxcContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnFanJian(fjBean: FjBean)
        fun returnFjxcList(fjBean:List<Renovated>, qutype:Int, count:Int)
        fun returnFanJianZb(fjBean:List<StatisticalEntity>)
        fun returnSteps(fjNumBean: FjNumBean)
        fun returnEnviorsByxy(fjNumBean: List<FwglJhBean>, type:String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getFanJian(point: String)
            abstract fun getFanJianZb(date: String)
            abstract fun getFjxcList(lastSelect:Int,limit:Int,page:Int,cunCode:String,date:String,bh:Int)
            abstract fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, code:String)
            abstract fun getSteps(lastSelect:Int,limit:Int,page:Int,cunCode:String,date:String,bh:Int)
        }
    }
}