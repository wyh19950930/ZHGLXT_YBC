package com.jymj.zhglxt.xm.contract

import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO
import com.jymj.zhglxt.zjd.bean.tdxg.GhVO
import com.jymj.zhglxt.zjd.bean.xm.BcXmfwEntity
import com.jymj.zhglxt.zjd.bean.xm.CountVo
import com.jymj.zhglxt.zjd.bean.xm.ProjectDto
import com.jymj.zhglxt.zjd.bean.xm.ProjectEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface XmJbqkContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{

        fun returnDkfPoint(cash: List<DkfVO>)
        fun onPointKjgh(tdlyEntity: GhVO)
        fun returnPorjectSave(msg: String)
        fun returnPorjectList(msg: List<BcXmfwEntity>)
        fun returnPorjectInfo(msg: ProjectEntity)
        fun returnPorjectUpdate(msg: String)
        fun returnPorjectStatistics(msg: CountVo)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){

            abstract fun getDkfPoint(point: String)
            abstract fun getKjghPoint(point: String)
            abstract fun getPorjectSave(entity: ProjectEntity)
            abstract fun getPorjectList(code:String)
            abstract fun getPorjectInfo(id:Long)
            abstract fun getPorjectUpdate(entity: ProjectEntity)
            abstract fun getPorjectStatistics(code:String)
            abstract fun getXmList()
        }
    }
}