package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandFile
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import java.util.ArrayList

interface YdlrActContract {
    interface Model:BaseModel {
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnApplyLandGetHuji(message: List<ZhaiEntity>)
        fun returnApplyByPoint(message: ApplyEntity)
        fun returnApplySave(message: String)
        fun returnApplyUpdate(message: String)
        fun returnApplyDeleteHuji(message: String,position: Int)
        fun returnApplyDeleteFjfw(message: String,position: Int)
        fun returnApplyFileDelete(message: String, applyEntityList: ArrayList<ApplyLandFile>, view: Int)
        fun returnApplyDelete(message: String)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getApplyLandGetHuji(ylId: Int,applyLandId:Int)
            abstract fun getApplyByPoint(point: String)
            abstract fun getApplySave(applyEntity: List<ApplyLandEntity>)
            abstract fun getApplyUpdate(applyEntity: List<ApplyLandEntity>)
            abstract fun getApplyDeleteHuji(id: Int,position: Int)
            abstract fun getApplyDeleteFjfw(id: Int,position: Int)
            abstract fun getApplyFileDelete(id: Int, applyEntityList: ArrayList<ApplyLandFile>, view: Int)
            abstract fun getApplyDelete(id: Int)

        }
    }
}