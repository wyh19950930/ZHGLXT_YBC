package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface SendMessageContract {
    interface Model :BaseModel{
        fun getEwmsmZjdBd(objectid:Long,qrcode:String):PostRequest<BaseRespose<YlFolwEntity>>
        fun getAddMessage(flowMassageEntity: FlowMassageEntity):PostRequest<String>
        fun getHqlgyList():PostRequest<BaseRespose<List<User>>>
    }
    interface View:BaseView{
        fun returnEwmsmZjdBd(msg: YlFolwEntity)
        fun returnAddMessage(msg: String)
        fun returnHqlgyList(msg: List<User>)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getEwmsmZjdBd(objectid:Long,qrcode:String)
            abstract fun getAddMessage(flowMassageEntity: FlowMassageEntity)
            abstract fun getHqlgyList()
        }
    }
}