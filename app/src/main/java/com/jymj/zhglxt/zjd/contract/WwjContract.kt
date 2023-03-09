package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.bcjc.BcjtzcEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface WwjContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnWwjPoint(entity: WwjEntity)
        fun returnBcjcYears(msg: List<String>)
        fun returnBcjcAddJtzc(msg: String)
        fun returnBcjcUpdateJtzc(msg: String)
        fun returnBcjcJtzc(msg: List<BcjtzcEntity>)
        fun returnBcjcJtzcAll(msg: List<BcjtzcEntity>)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getWwjPoint(point:String)
            abstract fun getBcjcYears()
            abstract fun getBcjcAddJtzc(entity: BcjtzcEntity)
            abstract fun getBcjcUpdateJtzc(entity: BcjtzcEntity)
            abstract fun getBcjcJtzc(code:String,years:List<String>)
            abstract fun getBcjcJtzcAll(code:String,years:List<String>)
        }
    }
}