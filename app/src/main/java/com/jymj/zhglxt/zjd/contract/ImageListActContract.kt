package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.EnviorFileFhEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface ImageListActContract {
    interface Model : BaseModel {
        fun getEnvironmental(proId: String): String
    }

    interface View : BaseView {
        fun returnImageList(cash: List<EnviorFileFhEntity>)

    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getImageList(limit: Int,page: Int,type: String,xmin: String,xmax: String,ymin: String,ymax: String,name: String,location: String,sjfl: String)


        }
    }
}