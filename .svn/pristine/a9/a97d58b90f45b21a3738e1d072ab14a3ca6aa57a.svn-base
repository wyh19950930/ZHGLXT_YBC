package com.jymj.zhglxt.zjd.contract

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

interface ZjdTdqsContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnBcjcYears(msg: List<String>)
        fun returnBcjcAddZhengdqk(msg: String)
        fun returnBchcZhengdqk(msg: List<BczdqkEntity>)
        fun returnBchcZhengdqkAll(msg: List<BczdqkEntity>)
        fun returnBchcGetKnzd(msg: List<BczdqkEntity>)
        fun returnBcjcAddZhandqk(msg: String)
        fun returnBchcZhandqk(msg: List<BczhandqkEntity>)
        fun returnBchcZhandqkAll(msg: List<BczhandqkEntity>)
        fun returnBchcGetInzhand(msg: List<BczhandqkEntity>)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getBcjcYears()
            abstract fun getBcjcAddZhengdqk(edtity:BczdqkEntity)
            abstract fun getBchcZhengdqk(code:String,years:List<String>)
            abstract fun getBchcZhengdqkAll(code:String,years:List<String>)
            abstract fun getBchcGetKnzd(code:String,years:List<String>)
            abstract fun getBcjcAddZhandqk(edtity:BczhandqkEntity)
            abstract fun getBchcZhandqk(code:String,years:List<String>)
            abstract fun getBchcZhandqkAll(code:String,years:List<String>)
            abstract fun getBchcGetInzhand(code:String,years:List<String>)
        }
    }
}