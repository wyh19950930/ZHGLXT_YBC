package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.HousingEntity
import com.jymj.zhglxt.zjd.bean.wy.HousingRoomFile
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface PropertyAddFzContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnWwjPoint(entity: WwjEntity)
        fun returnEwmsmZjdBd(msg: YlFolwEntity)
        fun returnPropertySaveOrUpdate(msg: String)
        fun returnHtYlFileDetel(message:String,position:Int)
        fun returnRoomFileDetel(message:String,enterpriseFileEntity: HousingRoomFile,type:Int)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getWwjPoint(point:String)
            abstract fun getEwmsmZjdBd(objectid:Long,qrcode:String)
            abstract fun getPropertySaveOrUpdate(entity: HousingEntity)
            abstract fun getHtYlFileDetel(ids:Long,position:Int)
            abstract fun getRoomFileDetel(ids:Long, enterpriseFileEntity: HousingRoomFile, type:Int)
        }
    }
}