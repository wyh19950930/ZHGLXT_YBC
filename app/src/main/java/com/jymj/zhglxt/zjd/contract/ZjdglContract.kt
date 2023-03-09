package com.jymj.zhglxt.zjd.contract

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.bean.yl.ApplyCountEntity
import com.jymj.zhglxt.rjhj.bean.yl.YztPointEntity
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyEntity
import com.jymj.zhglxt.zjd.bean.CscsBean
import com.jymj.zhglxt.zjd.bean.FrameJcxxBean
import com.jymj.zhglxt.zjd.bean.YeWuFrameBean
import com.jymj.zhglxt.zjd.bean.YlEntity
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.tdxg.GhVO
import com.jymj.zhglxt.zjd.bean.tdxg.QsVO
import com.jymj.zhglxt.zjd.bean.tdxg.TdlyVO
import com.jymj.zhglxt.zjd.bean.tdxg.YztSktVO
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.yzt.cs.CSEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.setsuna.common.basebean.BaseRespose
import java.math.BigDecimal

interface ZjdglContract {
    interface Model :BaseModel{
        fun getSmewmcyl(qrcode:String): PostRequest<BaseRespose<YlFolwEntity>>
        fun getRlt(code:String, type:Int, xmax: BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal):PostRequest<BaseRespose<List<XzqInfoEntity>>>
        fun getDcylxx(point:String,objectid:Long?):PostRequest<BaseRespose<YlFolwEntity>>
        fun getEnviorsRjhj(type: String,xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal,isImage:Int,code:String,hjzzlx:Int):PostRequest<BaseRespose<YlFolwEntity>>
        fun getYewuFrame(point: String): PostRequest<String>//业务框查
        fun getFrameJcxx(string: String): PostRequest<String>

    }
    interface View:BaseView{
        fun returnRjhjjcPoint(ylPointEntity: WtlrBean)
        fun returnFanJian(fjBean: FjBean)
        fun returnSmewmcyl(msg: YlFolwEntity)
        fun returnRlt(msg: List<XzqInfoEntity>,type:Int)
        fun returnYzt(msg: List<XzqInfoEntity>,type:Int)
        fun returnZjdRlt(msg: List<ApplyCountEntity>,type:Int)
        fun returnDcylxx(msg: YlFolwEntity)
        fun returnEnviorsByxy(fjNumBean: List<FwglJhBean>,type:String,xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal)
        fun returnApplyByPoint(fjBean: List<ApplyLandEntity>)
        fun returnPointGetYl(fjBean: YLEntity)
        fun returnYztByPoint(yztPointEntity: YztPointEntity)
        fun returnFanJianAdd(message: String)//翻建添加返回
        fun returnFanJianDelete(message: String)//删除翻建返回
        fun returnEnviorsRjhj(message: List<EnviorFileFhEntity>, type:String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage:Int)
        fun returnHbjcListCode(ylPointEntity: List<PjEnviorSupvsEntity>, roleid:Int, totalCount:Int)
        fun onHbLr(ylPointEntity: String)
        fun onDdLr(enviorSupvsEntity: String)//固定点位录入
        fun onDdDetel(message: String)//固定点位删除
        fun returnQueryCun(message: List<CjEntity>, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText)//
        fun returnEnviorSupvsQueryPoint(ylPointEntity: WtlrBean)
        fun onYewuFrame(entity: YeWuFrameBean.DataBean)
        fun onFrameJcxx(isSave: FrameJcxxBean.DataBean)
        fun onPointTx(tdlyEntity: TdlyVO)
        fun onPointTxSkt(tdlyEntity: YztSktVO)
        fun onPointQs(tdlyEntity: QsVO)
        fun onPointKjgh(tdlyEntity: GhVO)
        fun returnQyglPoint(cash: EnterpriseBasisEntity)
        fun returnNyglPoint(cash: List<YztNyyqEntity>)
        fun returnDkfPoint(cash: List<DkfVO>)
        fun returnWwjPoint(entity: WwjEntity)
        fun returnSysXzqQueryXzqList(renovated: ArrayList<SysXzqEntity>)
        fun returnSysCeSuna(renovated: CSEntity,cscsBean: CscsBean)
        fun returnSysCeSuna2(renovated: CSEntity)

    }
    companion object {
        abstract class Presenter:BasePresenter<View,BaseModel>(){
            abstract fun getRjhjjcPoint(point: String)//人居环境点查接口
            abstract fun getHbjcListCode(roleid: Int,limit:Int,page:Int, code:String,hjzzsj:Int,date:String,point:String,zdwtCha:Int,hjzzlx:Int)
            abstract fun getEnviorsRjhj(type: String,xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal,isImage:Int,code:String,hjzzlx:Int)
            abstract fun getFanJian(point: String)
            abstract fun getSmewmcyl(qrcode:String)
            abstract fun getYzt(code:String,type:Int,xmax:BigDecimal,xmin:BigDecimal,ymax:BigDecimal,ymin:BigDecimal)
            abstract fun getRlt(code:String,type:Int,xmax:BigDecimal,xmin:BigDecimal,ymax:BigDecimal,ymin:BigDecimal)
            abstract fun getZjdRlt(code:String, type: Int, xmax:BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal)
            abstract fun getDcylxx(point:String, objectid: Long?)
            abstract fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, code:String)
            abstract fun getApplyByPoint(point: String)
            abstract fun getPointGetYl(point: String)
            abstract fun getYztByPoint(point: String)
            abstract fun getFanJianAdd(point: FjBean)//这个也没有
            abstract fun getFanJianDelete(id: FjBean)//这个也没有
            abstract fun getHbLr(enviorSupvsEntity: PjEnviorSupvsEntity)
            abstract fun getDdLr(enviorSupvsEntity: PointRecordEntity)
            abstract fun getDdUpdate(enviorSupvsEntity: PointRecordEntity)
            abstract fun getQueryCun(name:String, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText)
            abstract fun getEnviorSupvsQueryPoint(point: String,type: Int)//这个应该是问题录入弹出框上边显示的附近问题
            abstract fun getDdDetel(ids:String)
            abstract fun getYewuFrame(point: String)
            abstract fun getFrameJcxx(string: String)
            abstract fun getTxPoint(point: String,type:Int)
            abstract fun getTdghPoint(point: String)
            abstract fun getTdqsPoint(point: String)
            abstract fun getKjghPoint(point: String)
            abstract fun getCyyqPoint(point: String)
            abstract fun getNyyqPoint(point: String)
            abstract fun getDkfPoint(point: String)
            abstract fun getWwjPoint(point:String)
            abstract fun getSysXzqQueryXzqList(code:String,type:Int)
            abstract fun getSysCeSuna(cscsBean: CscsBean)
            abstract fun getSysCeSuna2(cscsBean: CscsBean)


        }
    }
}