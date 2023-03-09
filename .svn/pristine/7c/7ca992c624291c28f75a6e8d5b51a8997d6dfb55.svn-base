package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.util.ArrayList

interface UserListContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnUserList(message: List<SysUserEntity>)
        fun returnXzqZhen(message: List<SysXzqEntity>, type:Int)//ArrayList
        fun returnUserQueryRy(message: ArrayList<SysXzqEntity>,type:Int)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
//            abstract fun getUserList(userid: String)
            abstract fun getXzqZhen(code:String,type:Int)
            abstract fun getUserQueryRy(code: String,name: String)

        }
    }
}