package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface NyyqContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnNyyqCunList(entity: List<NydVo>)
        fun returnNyyItemList(entity: List<YztNyyqEntity>)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getNyyqCunList()
            abstract fun getNyyqItemList(code:String)
        }
    }
}