package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.zjd.bean.bcjc.BccgbqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcldlbdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcrkbdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.PostRequest
import java.math.BigDecimal

/**
 * Created by setsuna on 18-3-23.
 */
interface ZjdLdrkContract {
    interface Model :BaseModel{
        fun login(uName:String,uPwd:String,deviceId:String): HttpParams
        fun getHylxtj(code:String):PostRequest<String>
        fun getJbqktj(code:String):PostRequest<String>
        fun getDcylxx(point:String,objectid:Long?):PostRequest<String>
        fun getCxczqk(lgyname:String,xzqmc:String):PostRequest<String>
        fun getCxldry(code:String,name:String,xzqmc:String):PostRequest<String>
        fun getSmewmcyl(qrcode:String):PostRequest<String>
        fun getRlt(code:String, type:Int, xmax: BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal):PostRequest<String>
        fun getWclCount(): PostRequest<String>

        fun getBcjcYears(): PostRequest<String>
        fun getBchcAddRkbdqk(edtity:List<BcrkbdqkEntity>): PostRequest<String>//BcrkbdqkEntity
        fun getBchcUpdateRkbdqk(edtity:BcrkbdqkEntity): PostRequest<String>
        fun getBchcRkbdqk(code:String,years:List<String>): PostRequest<String>
        fun getBchcAddLdlbdqk(edtity:BcldlbdqkEntity): PostRequest<String>
        fun getBchcUpdateLdlbdqk(edtity:BcldlbdqkEntity): PostRequest<String>
        fun getBchcLdlbdqk(code:String,years:List<String>): PostRequest<String>
        fun getBchcAddWcwgbdqk(edtity:BcwcjybdqkEntity): PostRequest<String>
        fun getBchcUpdateWcwgbdqk(edtity:BcwcjybdqkEntity): PostRequest<String>
        fun getBchcWcwgbdqk(code:String,years:List<String>): PostRequest<String>
        fun getBcjcGetInwcry(code:String,years:List<String>): PostRequest<String>
        fun getBchcAddCgbqk(edtity:BccgbqkEntity): PostRequest<String>
        fun getBchcUpdateCgbqk(edtity:BccgbqkEntity): PostRequest<String>
        fun getBchcCgbqk(code:String,year: String): PostRequest<String>
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

        fun returnBcjcYears(msg: List<String>)
        fun returnBcjcAddRkbdqk(msg: String)
        fun returnBcjcUpdateRkbdqk(msg: String)
        fun returnBchcRkbdqk(msg: List<BcrkbdqkEntity>)
        fun returnBchcRkbdqkAll(msg: List<BcrkbdqkEntity>)
        fun returnBcjcAddLdlbdqk(msg: String)
        fun returnBcjcUpdateLdlbdqk(msg: String)
        fun returnBchcLdlbdqk(msg: List<BcldlbdqkEntity>)
        fun returnBchcLdlbdqkAll(msg: List<BcldlbdqkEntity>)
        fun returnBcjcAddWcwgbdqk(msg: String)
        fun returnBcjcUpdateWcwgbdqk(msg: String)
        fun returnBchcWcwgbdqk(msg: List<BcwcjybdqkEntity>)
        fun returnBchcWcwgbdqkAll(msg: List<BcwcjybdqkEntity>)
        fun returnBcjcGetInwcry(msg: List<BcwcjybdqkEntity>)
        fun returnBcjcAddCgbqk(msg: String)
        fun returnBcjcCgbqk(msg: List<BccgbqkEntity>)
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

            abstract fun getBcjcYears()
            abstract fun getBcjcAddRkbdqk(edtity:List<BcrkbdqkEntity>)
            abstract fun getBcjcUpdateRkbdqk(edtity:BcrkbdqkEntity)
            abstract fun getBchcRkbdqk(code:String,years:List<String>)
            abstract fun getBchcRkbdqkAll(code:String,years:List<String>)
            abstract fun getBcjcAddLdlbdqk(edtity:BcldlbdqkEntity)
            abstract fun getBcjcUpdateLdlbdqk(edtity:BcldlbdqkEntity)
            abstract fun getBchcLdlbdqk(code:String,years:List<String>)
            abstract fun getBchcLdlbdqkAll(code:String,years:List<String>)
            abstract fun getBcjcAddWcwgbdqk(edtity:BcwcjybdqkEntity)
            abstract fun getBcjcUpdateWcwgbdqk(edtity:BcwcjybdqkEntity)
            abstract fun getBchcWcwgbdqk(code:String,years:List<String>)
            abstract fun getBchcWcwgbdqkAll(code:String,years:List<String>)
            abstract fun getBcjcGetInwcry(code:String,years:List<String>)
            abstract fun getBcjcAddCgbqk(edtity:BccgbqkEntity)
            abstract fun getBchcUpdateCgbqk(edtity:BccgbqkEntity)
            abstract fun getBcjcCgbqk(code:String,year: String)
        }
    }
}