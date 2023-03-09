package com.jymj.zhglxt.rjhj.contract

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.jymj.zhglxt.rjhj.bean.*
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose

interface EnvironmentalActContract {
    interface Model : BaseModel {
        fun getEnvironmental(proId: String): String
    }

    interface View : BaseView {
        fun returnEnvironmental(cash: List<PjEnviorSupvsEntity>)
        fun returnAddEnvironmental(cash: String)
        fun returnTuiEnvironmental(cash: BaseRespose <String>)
        fun returnDeleteEnvironmental(cash: BaseRespose <*>)
        fun returnDeletesEnvironmental(cash: BaseRespose <*>)
        fun returnDeleteFileEnvironmental(cash: BaseRespose <*>)
        fun returnUpdateEnvironmental(cash: BaseRespose <*>)
        fun returnUploadEnvironmental(cash: BaseRespose <*>)
        fun returnListEnvironmental(updateEntity: PjEnviorSupvsEntity)
        fun returnRyEnvironmental(updateEntity: List<SysUserEntity>)
        fun returnInfoEnvironmental(updateEntity: RjhjInfoBean)
        fun returnRjhjPoint(ylPointEntity: RjhjBean)
        fun returnQueryCun(message: List<CjEntity>, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText)
        fun returnFallBackQutype(message: String)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getFallBackQutype(id: Int,qutype: String,comment: String)
            abstract fun getEnvironmental(qutype: String,roleid: String)
            abstract fun getListEnvironmental(rtInvestEntity: PjEnviorSupvsEntity)
            abstract fun addEnvironmental(enviorSupvsEntity: PjEnviorSupvsEntity)
            abstract fun tuiEnvironmental(enviorSupvsEntity: PjRoleEnviorEntity)
            abstract fun deleteEnvironmental(id: Int,pjid :String)
            abstract fun deletesEnvironmental(ids: ArrayList<Int>)
            abstract fun deleteFileEnvironmental(id: Int)//,pjid :String
            abstract fun uploadFileEnvironmental(id: Int)
            abstract fun undateEnvironmental(enviorSupvsEntity: PjEnviorSupvsEntity)
            abstract fun ryEnvironmental(code :String)
            abstract fun infoEnvironmental(id :String)
            abstract fun getRjhjPoint(point: String)
            abstract fun getQueryCun(name:String, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText)

        }
    }
}