package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface AjlbSearchActContract {
    interface Model : BaseModel {
        fun getEnvironmental(proId: String): String
    }

    interface View : BaseView {
        fun returnFanJianDetail(renovated: Renovated)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getFanJianDetail(id:Long)//详情
        }
    }
}