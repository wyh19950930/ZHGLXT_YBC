package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface FrameYewuContract {
    interface Model :BaseModel{
        fun getSmewmcyl(qrcode:String):PostRequest<BaseRespose<YlFolwEntity>>
    }
    interface View:BaseView{
        fun returnSmewmcyl(msg: YlFolwEntity)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getSmewmcyl(qrcode:String)
        }
    }
}