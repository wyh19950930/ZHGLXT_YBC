package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.yzt.TjfxDetailBean
import com.jymj.zhglxt.zjd.bean.yzt.layerListBean
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface TjfxActContract {
    interface Model : BaseModel {
        fun getEnvironmental(proId: String): String
    }

    interface View : BaseView {
        fun returnFanJianDetail(renovated: Renovated)
        fun returnSysXzqQueryXzqList(renovated: ArrayList<SysXzqEntity>)
        fun returnSysXzqList(renovated: List<layerListBean>)
        fun returnTjfx(renovated: TjfxDetailBean)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getFanJianDetail(id:Long)//详情
            abstract fun getSysXzqQueryXzqList(code:String,type:Int)
            abstract fun getSysXzqList()
            abstract fun getTjfx(cunCode:String,tcList:ArrayList<Int>)
        }
    }
}