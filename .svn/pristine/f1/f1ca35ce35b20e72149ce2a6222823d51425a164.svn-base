package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest

/**
 * Created by setsuna on 18-3-23.
 */
interface LdrkDetailContract {
    interface Model :BaseModel{
        fun getEwmsmZjdBd(objectid:Long,qrcode:String):PostRequest<String>
        fun getCxldry(ylId:Long):PostRequest<String>
        fun getLcldry(ids:List<Int>):PostRequest<String>
        fun getDcylxx(point:String,objectid:Long):PostRequest<String>
    }
    interface View:BaseView{
        fun returnEwmsmZjdBd(msg: YlFolwEntity)
        fun returnCxldry(msg: List<FlowInfoEntity>)
        fun returnLcldry(msg: String)
        fun returnDcylxx(msg: YlFolwEntity)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getEwmsmZjdBd(objectid:Long,qrcode:String)
            abstract fun getCxldry(ylId:Long)
            abstract fun getLcldry(ids:List<Int>)
            abstract fun getDcylxx(point:String,objectid:Long)
        }
    }
}