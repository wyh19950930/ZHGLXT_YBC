package com.jymj.zhglxt.xm.contract

import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.bean.CjVO
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.util.ArrayList

interface XmIssueContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnAddUser(message: String)
        fun returnByPoint(message: CjVO,point: String)
        fun returnProject(message: BcProjectEntity)
        fun returnProjectGetLabel(message: List<JsjbBean>)
        fun returnSaveLabel(message: String)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getAddXmIssue(message: BcProjectEntity)
            abstract fun getByPoint(point: String)
            abstract fun getProject(id: Long)
            abstract fun getProjectGetLabel()
            abstract fun getSaveLabel(message: JsjbBean)

        }
    }
}