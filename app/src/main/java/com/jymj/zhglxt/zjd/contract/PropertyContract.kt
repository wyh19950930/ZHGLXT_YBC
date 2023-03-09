package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.wy.RentEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface PropertyContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnWyPoint(entity: PropertyDto)
        fun returnWyFzList(entity: List<RentEntity>)
        fun returnWyCzList(entity: List<RentEntity>)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getWyPoint(point:String,type:Int)
            abstract fun getWyFzList(code:String,name:String,xzqmc:String,limit:Int,page:Int)
            abstract fun getWyCzList(code:String,cqr:String,mph:String,limit:Int,page:Int)
        }
    }
}