package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.FirstTjBean
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest

/**
 * Created by setsuna on 18-3-23.
 */
interface CzlbContract {
    interface Model :BaseModel{
        fun getCxczqk(lgyname:String,xzqmc:String):PostRequest<String>
        fun getJbqktj(code:String):PostRequest<String>
    }
    interface View:BaseView{
        fun returnCxczqk(msg: List<XzqInfoEntity>)
        fun returnJbqktj(msg: FirstTjBean)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getCxczqk(lgyname:String,xzqmc:String)
            abstract fun getJbqktj()
        }
    }
}