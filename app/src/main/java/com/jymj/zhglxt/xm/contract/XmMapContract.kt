package com.jymj.zhglxt.xm.contract

import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.bean.CjVO
import com.jymj.zhglxt.xm.bean.ProjectCountVO
import com.jymj.zhglxt.zjd.bean.fj.FwglJhBean
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.math.BigDecimal
import java.util.ArrayList

interface XmMapContract {
    interface Model:BaseModel{
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnByPoint(message: List<BcProjectEntity>)
        fun returnEnviorsByxy(fjNumBean: List<ProjectCountVO>, type:String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getByPoint(point: String)
            abstract fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, code:String)

        }
    }
}