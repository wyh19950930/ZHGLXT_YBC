package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEntity
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEnumBean
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionFile
import com.jymj.zhglxt.zjd.bean.jsjb.SysDepartEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface AjDetailActContract {
    interface Model : BaseModel {
        fun getEnvironmental(proId: String): String
    }

    interface View : BaseView {
        fun returnFanJianDetail(renovated: Renovated)
        fun returnJsjbInfo(renovated: OpinionEntity)
        fun returnJsjbDeleteFile(renovated: String,opinionFiles:ArrayList<OpinionFile>,opinionFile:OpinionFile)
        fun returnJsjbSave(renovated: String)
        fun returnJsjbUpdate(renovated: String)
        fun returnOpinionGetDepart(renovated: List<SysDepartEntity>)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getFanJianDetail(id:Long)//详情
            abstract fun getJsjbInfo(id:Long)
            abstract fun getJsjbDeleteFile(id:Long, opinionFiles:ArrayList<OpinionFile>, opinionFile: OpinionFile)
            abstract fun getJsjbSave(opinionEntity:OpinionEntity)
            abstract fun getJsjbUpdate(opinionEntity:OpinionEntity)
            abstract fun getOpinionGetDepart()//
        }
    }
}