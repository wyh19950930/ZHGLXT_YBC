package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBusiness
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandFile
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import java.util.ArrayList

interface QyglDetailActContract {
    interface Model:BaseModel {
        fun getBaoBiao(proId:String):String
    }
    interface View:BaseView{
        fun returnBusiness(message: List<EnterpriseBusiness>,month:String)
        fun returnSaveOrUpdate(message: String)
        fun returnSaveEnterprise(message: String)
        fun returnSaveBusiness(message: EnterpriseBusiness)
        fun returnEnterDeleteFile(message: String)
        fun returnEnterDelete(message: String)
        fun returnDeleteEnterprise(message: String)
        fun returnEnterQueryInfo(message: EnterpriseBasisEntity)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getBusiness(id: Long,month:String)
            abstract fun getSaveOrUpdate(enterpriseBasisEntity: EnterpriseBasisEntity)
            abstract fun getSaveEnterprise(enterpriseBasisEntity: EnterpriseEntity)
            abstract fun getSaveBusiness(enterpriseBasisEntity: EnterpriseBusiness)
            abstract fun getEnterDeleteFile(id: Long)
            abstract fun getEnterDelete(id: Long)
            abstract fun getEnterDeleteEnterprise(id: Long)
            abstract fun getEnterQueryInfo(id: Long)

        }
    }
}