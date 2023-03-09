package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.rjhj.bean.TownCountEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.SwitchTimeEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.TownTypeEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface RjhjFirstContract {
    interface Model :BaseModel{
        fun getDcylxx(point:String,objectid:Long):PostRequest<BaseRespose<YlFolwEntity>>
        fun getQueryImproveRate(code: String):PostRequest<SwitchTimeEntity>
        fun getByPoint(code: String,type: Int):PostRequest<BaseRespose<TownCountEntity>>
        fun getRjhjjcMap(code: String,type: Int):PostRequest<BaseRespose<List<TownCountEntity>>>
        fun getTownType(code: String,type: Int):PostRequest<BaseRespose<TownTypeEntity>>
    }
    interface View:BaseView{
        fun returnDcylxx(msg: YlFolwEntity)
        fun returnQueryImproveRate(cash: SwitchTimeEntity)
        fun returnByPoint(cash: TownCountEntity)
        fun returnRjhjjcMap(cash: List<TownCountEntity>)
        fun returnTownType(cash: TownTypeEntity)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getDcylxx(point:String,objectid:Long)
            abstract fun getQueryImproveRate(code: String)
            abstract fun getByPoint(code: String,type: Int)
            abstract fun getRjhjjcMap(code: String,type: Int)
            abstract fun getTownType(code: String,type: Int)
        }
    }
}