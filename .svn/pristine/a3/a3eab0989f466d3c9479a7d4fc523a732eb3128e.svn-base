package com.jymj.zhglxt.zjd.contract

import com.jymj.zhglxt.zjd.bean.cygl.NydHtEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.NydZjEntity
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView

interface NyyqActContract {
    /*interface Model:BaseModel{
//        fun getCyfz(proId:String):String
    }*/
    interface View:BaseView{
        fun returnNyyqDetail(entity: YztNyyqEntity)
        fun returnNyyqDetailUploadHt(msg:NydHtEntity)
        fun returnFileDelete(msg:String)
        fun returnNyyqZjDelete(msg:String)
        fun returnNyyqDetailUploadZj(msg:NydZjEntity)
    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getNyyqDetail(id:Int)
            abstract fun getNyyqDetailUploadHt(entity:NydHtEntity)
            abstract fun getFileDelete(id: Int)
            abstract fun getNyyqZjDelete(id: Int)
            abstract fun getNyyqDetailUploadZj(entity:NydZjEntity)
        }
    }
}