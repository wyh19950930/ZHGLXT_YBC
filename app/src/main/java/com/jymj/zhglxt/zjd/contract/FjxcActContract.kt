package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface FjxcActContract {
    interface Model : BaseModel {
        fun getEnvironmental(proId: String): String
    }

    interface View : BaseView {
        fun returnFanJianDetail(renovated: Renovated)
        fun returnFanJianDelete(renovated: String)
        fun returnFanJianUpdate(renovated: String)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getFanJianDetail(id:Long)//详情
            abstract fun getFanJianDelete(id: Renovated)//删除翻建
            abstract fun getFanJianUpdate(point: Renovated)//翻建修改
        }
    }
}