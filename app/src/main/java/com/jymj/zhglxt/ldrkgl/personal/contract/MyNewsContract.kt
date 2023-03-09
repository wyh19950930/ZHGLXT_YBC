package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface MyNewsContract {
    interface Model :BaseModel{
        fun getMessageList(limit:Int,page:Int): PostRequest<BaseRespose<PageUtils<FlowMassageEntity>>>
        fun getAddMessage(flowMassageEntity: FlowMassageEntity):PostRequest<BaseRespose<String>>
    }
    interface View:BaseView{
        fun returnMessageList(msg:List<FlowMassageEntity>)
        fun returnAddMessage(msg: String)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getMessageList(limit:Int,page:Int)
            abstract fun getAddMessage(flowMassageEntity: FlowMassageEntity)
        }
    }
}
