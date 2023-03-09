package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.bcjc.BcSktEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcrkbdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczhandqkEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface ZjdSktContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnBcjcYears(msg: List<String>)
        fun returnBcjcAddSkt(msg: String)
        fun returnBchcSkt(msg: List<BcSktEntity>)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getBcjcYears()
            abstract fun getBcjcAddSkt(edtity:BcSktEntity)
            abstract fun getBchcSkt(code:String,years:List<String>)
        }
    }
}