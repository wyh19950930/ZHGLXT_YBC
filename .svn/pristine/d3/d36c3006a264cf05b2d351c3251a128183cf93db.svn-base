package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.bcjc.BcjjzsrqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtjjsrqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtjjzcqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.SrZcBean
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTj2Bean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTjBean
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface YqglContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnNyyqCunList(entity: List<NydVo>)
        fun returnOpinionGetCount(entity: List<JsjbTjBean>)
        fun returnNyyItemList(entity: List<YztNyyqEntity>)
        fun returnOpinionGetStatistical(entity: JsjbTj2Bean)

        fun returnBcjcYears(msg: List<String>)
        fun returnBcjcAddCjjzsrqk(msg: String)
        fun returnBcjcUpdateCjjzsrqk(msg: String)
        fun returnBcjcCjjzsrqk(msg: List<BcjjzsrqkEntity>)
        fun returnBcjcCjjzsrqkAll(msg: List<BcjjzsrqkEntity>)
        fun returnBcjcAddCjtjjzysrqk(msg: String)
        fun returnBcjcUpdateCjtjjzysrqk(msg: String)
        fun returnBcjcCjtjjzysrqk(msg: List<BcjtjjsrqkEntity>)
        fun returnBcjcCjtjjzysrqkAll(msg: List<BcjtjjsrqkEntity>)
        fun returnBcjcGetCjjjsrzc(msg: SrZcBean)
        fun returnBcjcAddCjtjjzyzcqk(msg: String)
        fun returnBcjcUpdateCjtjjzyzcqk(msg: String)
        fun returnBcjcCjtjjzyzcqk(msg: List<BcjtjjzcqkEntity>)
        fun returnBcjcCjtjjzyzcqkAll(msg: List<BcjtjjzcqkEntity>)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getNyyqCunList()
            abstract fun getOpinionGetCount()
            abstract fun getNyyqItemList(code:String)
            abstract fun getOpinionGetStatistical()

            abstract fun getBcjcYears()
            abstract fun getBcjcAddCjjzsrqk(entity: BcjjzsrqkEntity)
            abstract fun getBcjcUpdateCjjzsrqk(entity: BcjjzsrqkEntity)
            abstract fun getBcjcCjjzsrqk(code:String,years:List<String>)
            abstract fun getBcjcCjjzsrqkAll(code:String,years:List<String>)
            abstract fun getBcjcAddCjtjjzysrqk(entity: BcjtjjsrqkEntity)
            abstract fun getBcjcUpdateCjtjjzysrqk(entity: BcjtjjsrqkEntity)
            abstract fun getBcjcCjtjjzysrqk(code:String,years:List<String>)
            abstract fun getBcjcCjtjjzysrqkAll(code:String,years:List<String>)
            abstract fun getBcjcAddCjtjjzyzcqk(entity: BcjtjjzcqkEntity)
            abstract fun getBcjcUpdateCjtjjzyzcqk(entity: BcjtjjzcqkEntity)
            abstract fun getBcjcCjtjjzyzcqk(code:String,years:List<String>)
            abstract fun getBcjcCjtjjzyzcqkAll(code:String,years:List<String>)
            abstract fun getBcjcGetCjjjsrzc(code: String, years: List<String>)
        }
    }
}