package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest

/**
 * Created by setsuna on 18-3-23.
 */
interface YllbContract {
    interface Model :BaseModel{
        fun getCxldry(code:String,name:String,xzqmc:String,limit:Int,page:Int):PostRequest<String>
        fun getLcldry(ids:List<Int>):PostRequest<String>
        fun getDcylxx(point:String,objectid:Long):PostRequest<String>
    }
    interface View:BaseView{
        fun returnCxldry(msg: List<YlFolwEntity>)
        fun returnLcldry(msg: String)
        fun returnDcylxx(msg: YlFolwEntity)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getCxldry(code:String,name:String,xzqmc:String,limit:Int,page:Int)
            abstract fun getLcldry(ids:List<Int>)
            abstract fun getDcylxx(point:String,objectid:Long)
        }
    }
}