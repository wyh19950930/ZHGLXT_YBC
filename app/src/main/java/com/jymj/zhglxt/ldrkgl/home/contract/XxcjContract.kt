package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest

/**
 * 信息采集
 */
interface XxcjContract {
    interface Model : BaseModel {
        fun getFlowGetInfo(idCard: String,ylid:Long):PostRequest<String>
        fun getFlowUpload(flowInfoEntity: FlowInfoEntity):PostRequest<String>
        fun getLcldry(ids:List<Int>):PostRequest<String>
    }

    interface View : BaseView {
        fun returnFlowGetInfo(entity: FlowInfoEntity)
        fun returnFlowUpload(entity: String)
        fun returnLcldry(msg: String)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getFlowGetInfo(idCard: String,ylid:Long)
            abstract fun getFlowUpload(flowInfoEntity: FlowInfoEntity)
            abstract fun getLcldry(ids:List<Int>)
        }
    }
}