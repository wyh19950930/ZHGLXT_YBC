package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.util.ArrayList

interface AddUserContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnAddUser(message: String)
        fun returnUpdateUser(message: String)
        fun returnUserInfo(message: SysUserEntity)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getAddUser(message: SysUserEntity)
//            abstract fun getUpdateUser(message: SysUserEntity)
            abstract fun getUserInfo(userId:String)

        }
    }
}