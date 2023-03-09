package com.jymj.zhglxt.xm.contract

import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.util.ArrayList

interface XmTzDetailContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnAddUser(message: String)
        fun returnXmList(message: List<BcProjectEntity>)
        fun returnXmDelete(message: String)
        fun returnProjectSaveCollection(message: String)
        fun returnProjectDeleteCollection(message: String)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getAddUser(message: SysUserEntity)
            abstract fun getXmList(id:Long)
            abstract fun getXmDelete(id: Long)
            abstract fun getProjectSaveCollection(id: Long)
            abstract fun getProjectDeleteCollection(id: Long)

        }
    }
}