package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTj2Bean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTjBean
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface WjdcContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnBcjcCjtjjzyzcqk(msg: List<BcfzyyEntity>)
        fun returnBcjcGetOptions(msg: List<OptionsEntity>)
        fun onBcjcYears(tdlyDetail: List<String>)
        fun onBcjcFzyySave(tdlyDetail: String)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getBcjcCjtjjzyzcqk(code:String,years:String)
            abstract fun getBcjcGetOptions()
            abstract fun getBcjcYears()
            abstract fun getBcjcFzyySave(bcfzyyEntitys:ArrayList<BcfzyyEntity>)
        }
    }
}