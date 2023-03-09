package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.CjEntity
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcjcBbBean
import com.jymj.zhglxt.zjd.bean.bcjc.OptionsEntity
import com.jymj.zhglxt.zjd.bean.bcjc.ReportDto
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.util.ArrayList
import java.util.HashMap

interface CbbsjsxContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnAddUser(message: String)
        fun returnUpdateUser(message: String)
        fun returnUserInfo(message: SysUserEntity)
        fun returnBcjcYears(msg: List<String>)
        fun returnQueryCun(message: ArrayList<SysXzqEntity>)//
        fun returnXzqZhen(message: List<SysXzqEntity>)//
        fun returnBcjcGetReprot(message: HashMap<String, ArrayList<Object>>)////BcjcBbBean
        fun returnBcjcGetOptions(message: List<OptionsEntity>)//

    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getAddUser(message: SysUserEntity)
//            abstract fun getUpdateUser(message: SysUserEntity)
            abstract fun getUserInfo(userId:String)
            abstract fun getBcjcYears()
            abstract fun getQueryCun(name:String)
            abstract fun getXzqZhen()
            abstract fun getBcjcGetReprot(message: ReportDto)
            abstract fun getBcjcGetOptions()

        }
    }
}