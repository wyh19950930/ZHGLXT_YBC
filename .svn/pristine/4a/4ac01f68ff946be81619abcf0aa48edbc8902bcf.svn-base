package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.rjhj.bean.yl.ApplyFileEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import java.util.ArrayList

interface ZjdfjglActContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnApplyByPoint(message: ApplyEntity)
        fun returnApplySave(message: String)
        fun returnApplyUpdate(message: String)
        fun returnApplyDeleteHuji(message: String,position: Int)
        fun returnApplyDeleteFjfw(message: String,position: Int)
        fun returnApplyFileDelete(message: String, applyEntityList: ArrayList<ApplyFileEntity>, view: Int)
        fun returnApplyDelete(message: String)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getApplyByPoint(point: String)
            abstract fun getApplySave(applyEntity: ApplyEntity)
            abstract fun getApplyUpdate(applyEntity: ApplyEntity)
            abstract fun getApplyDeleteHuji(id: Int,position: Int)
            abstract fun getApplyDeleteFjfw(id: Int,position: Int)
            abstract fun getApplyFileDelete(id: Int, applyEntityList: ArrayList<ApplyFileEntity>,view: Int)
            abstract fun getApplyDelete(id: Int)

        }
    }
}