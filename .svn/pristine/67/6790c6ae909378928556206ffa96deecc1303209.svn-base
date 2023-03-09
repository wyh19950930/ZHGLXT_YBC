package com.jymj.zhglxt.rjhj.contract

import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface RjhjDwckContract {
    interface Model :BaseModel{
        fun getWclCount(): PostRequest<BaseRespose<Int>>
    }
    interface View:BaseView{
        fun returnWclCount(msg: Int)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getWclCount()
        }
    }
}