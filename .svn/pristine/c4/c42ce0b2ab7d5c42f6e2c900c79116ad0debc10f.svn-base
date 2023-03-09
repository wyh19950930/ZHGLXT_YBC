package com.jymj.zhglxt.xm.contract

import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.util.ArrayList

interface ScListContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnProjectGetCallection(message: List<BcProjectEntity>)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getProjectGetCallection(page: Int, limit: Int,xmlb:Int,name:String)

        }
    }
}