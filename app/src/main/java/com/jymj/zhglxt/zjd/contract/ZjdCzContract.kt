package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.bean.User
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import java.math.BigDecimal

/**
 * Created by setsuna on 18-3-23.
 */
interface ZjdCzContract {
    interface Model :BaseModel{
        fun login(uName:String,uPwd:String,deviceId:String): HttpParams
        fun getHylxtj(code:String):PostRequest<BaseRespose<List<IndustryEntity>>>
        fun getJbqktj(code:String):PostRequest<BaseRespose<FirstTjBean>>
        fun getDcylxx(point:String,objectid:Long?):PostRequest<BaseRespose<YlFolwEntity>>
        fun getCxczqk(lgyname:String,xzqmc:String):PostRequest<BaseRespose<PageUtils<XzqInfoEntity>>>
        fun getCxldry(code:String,name:String,xzqmc:String):PostRequest<BaseRespose<PageUtils<YlFolwEntity>>>
        fun getSmewmcyl(qrcode:String):PostRequest<BaseRespose<YlFolwEntity>>
        fun getRlt(code:String, type:Int, xmax: BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal):PostRequest<BaseRespose<List<XzqInfoEntity>>>
        fun getWclCount(): PostRequest<BaseRespose<Int>>
    }
    interface View:BaseView{
        fun returnUser(user: User)
        fun changeUser()
        fun changeUser(msg:List<IndustryEntity>)
        fun returnJbqktj(msg: FirstTjBean)
        fun returnDcylxx(msg: YlFolwEntity)
        fun returnCxczqk(msg: List<XzqInfoEntity>)
        fun returnCxldry(msg: List<YlFolwEntity>,total:Long)
        fun returnSmewmcyl(msg: YlFolwEntity)
        fun returnRlt(msg: List<XzqInfoEntity>,type:Int)
        fun returnWclCount(count: Int)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getUserRequest(uName:String,uPwd:String,deviceId:String)
            abstract fun getHylxtj()
            abstract fun getJbqktj()
            abstract fun getDcylxx(point:String, objectid: Long?)
            abstract fun getCxczqk(lgyname:String,xzqmc:String)
            abstract fun getCxldry(code:String,name:String,xzqmc:String)
            abstract fun getSmewmcyl(qrcode:String)
            abstract fun getRlt(code:String,type:Int,xmax:BigDecimal,xmin:BigDecimal,ymax:BigDecimal,ymin:BigDecimal)
            abstract fun getWclCount()
        }
    }
}