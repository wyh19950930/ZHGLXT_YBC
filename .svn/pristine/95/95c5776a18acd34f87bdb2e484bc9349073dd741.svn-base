package com.jymj.zhglxt.ui.main.contract

import com.jymj.zhglxt.zjd.bean.cygl.CyyqZztEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface QyglActContract {
    interface Model : BaseModel {
        fun getQyglList(proId: String): String
        fun getCyyqZzt()
    }

    interface View : BaseView {
        fun returnQyglList(cash: List<EnterpriseBasisEntity>)
        fun returnQyglPoint(cash: EnterpriseBasisEntity)
        fun returnCyyqZzt(cash: CyyqZztEntity)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getQyglList(page: Int, limit: Int, qylx: Int, garden: String, zcdate: String, zcdz: String, qymc: String,jyzt:Int)
            abstract fun getCyyqPoint(point: String)
            abstract fun getCyyqZzt()
        }
    }
}