package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.rjhj.bean.yl.YztPointEntity
import com.jymj.zhglxt.zjd.bean.AnalysisEnty
import com.jymj.zhglxt.zjd.bean.FrameJcxxBean
import com.jymj.zhglxt.zjd.bean.YeWuFrameBean
import com.jymj.zhglxt.zjd.bean.yzt.cs.CSEntity
import com.jymj.zhglxt.zjd.bean.yzt.cs.XzDateEntity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface ZjdYztContract {
    interface Model :BaseModel{
        fun getCxczqk(lgyname:String,xzqmc:String):PostRequest<BaseRespose<PageUtils<XzqInfoEntity>>>
        fun getYztPoint(point: String): PostRequest<String>
        fun getYewuFrame(point: String): PostRequest<String>//业务框查
        fun  getFrameJcxx(string: String): PostRequest<String>
        fun  getYztList(page: Int,limit:Int,type:Int,projectName:String): PostRequest<String>
        fun getBaseDataCe(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String): PostRequest<String>//
        fun getCS(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String): PostRequest<String>


    }
    interface View:BaseView{
        fun returnCxczqk(msg: List<XzqInfoEntity>)
        fun onYztPoint(yztPointEntity: YztPointEntity)//********************************************
        fun onYewuFrame(entity: YeWuFrameBean.DataBean)
        fun onFrameJcxx(isSave: FrameJcxxBean.DataBean)
        fun onYztList(isSave:List<AnalysisEnty>)////////////////********************
        fun onBaseDataCe(baseDataEntity: XzDateEntity)
        fun onCS(csEntity: CSEntity)


    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getCxczqk(lgyname:String,xzqmc:String)
            abstract fun getYztPoint(point: String)
            abstract fun getYewuFrame(point: String)
            abstract fun  getFrameJcxx(string: String)
            abstract fun  getYztList(page: Int,limit:Int,type:Int,projectName:String)
            abstract fun getBaseDataCe(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String)
            abstract fun getCS(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String)



        }
    }
}