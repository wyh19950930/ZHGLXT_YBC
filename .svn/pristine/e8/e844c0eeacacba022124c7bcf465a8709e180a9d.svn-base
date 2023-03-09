package com.jymj.zhglxt.rjhj.contract

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.zjd.bean.bcjc.BccyjgEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcrkbdqkEntity
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import java.math.BigDecimal

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/3/9 14:04
 */
 interface RjhjContract {

    interface Model :BaseModel{
        fun getHbjcList(roleid: Int,limit:Int,page:Int,code:String,hjzzsj:Int,date:String,no:Int,zdwtCha:Int,hjzzlx:Int): PostRequest<String>
        fun getEnviorSupvsQueryPoint(point: String): PostRequest<String>
        fun getHbLr(enviorSupvsEntity: PjEnviorSupvsEntity): PostRequest<String>
        fun getQueryCun(name:String): PostRequest<String>
        fun getCountCyCode(code: String,hjzzlx: Int,gddwid: String,date: String, roleid: Int,hjzzsj: Int,no: Int,zdwtCha: Int): PostRequest<String>
        fun getHbjcZl(date:String,startDate:String,endDate:String,code:String): PostRequest<String>
        fun getRjhjjcPoint(point: String): PostRequest<String>
        fun getDdUpdate(enviorSupvsEntity: PointRecordEntity): PostRequest<String>
        fun getDdLr(enviorSupvsEntity: PointRecordEntity): PostRequest<String>
        fun getDdDetel(ids:String): PostRequest<String>
        fun getHbjcListCode(roleid: Int,limit:Int,page:Int, code:String,hjzzsj:Int,date:String,point:String,zdwtCha:Int,hjzzlx:Int): PostRequest<String>
        fun getDdList(userId:String,page:Int): PostRequest<String>
        fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage:Int, code:String, hjzzlx:Int)
                : PostRequest<String>

        fun getBcjcYears(): PostRequest<String>
        fun getBchcAddCyjg(edtity: List<BccyjgEntity>): PostRequest<String>
        fun getBchcUpdateCyjg(edtity: List<BccyjgEntity>): PostRequest<String>
        fun getBcjcCyjg(code:String,years:List<String>): PostRequest<String>
    }

    interface View: BaseView {
        fun returnHbjcList(ylPointEntity: List<PjEnviorSupvsEntity>, roleid:Int, totalCount:Int)
        fun returnEnviorSupvsQueryPoint(ylPointEntity: WtlrBean)
        fun onHbLr(ylPointEntity: String)
        fun returnQueryCun(message: List<CjEntity>, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText)//
        fun returnCountCyCode(message: EnviorCunBean.DataBean)//
        fun onHbjcZl(ylPointEntity: List<EnviorCunBean.DataBean>)
        fun returnRjhjjcPoint(ylPointEntity: RjhjPointBean)
        fun onDdLr(enviorSupvsEntity: String)//固定点位录入
        fun returnHbjcListCode(ylPointEntity: List<PjEnviorSupvsEntity>,roleid:Int,totalCount:Int)
        fun onDdList(message: List<PointRecordEntity>)//固定点位列表
        fun returnEnviorFileQueryList(message: List<EnviorFileFhEntity>,type:String,xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal,isImage:Int)

        fun returnBcjcYears(msg: List<String>)
        fun returnBcjcAddCyjg(msg: String)
        fun returnBcjcUpdateCyjg(msg: String)
        fun returnBchcCyjg(msg: HashMap<String,ArrayList<BccyjgEntity>>)
        fun returnBchcCyjgAll(msg: HashMap<String,ArrayList<BccyjgEntity>>)
    }

    companion object {
        abstract class Presenter : BasePresenter<View, Model>() {
            abstract fun getHbjcList(roleid: Int,limit:Int,page:Int,code:String,hjzzsj:Int,date:String,no:Int,zdwtCha:Int,hjzzlx:Int)
            abstract fun getEnviorSupvsQueryPoint(point: String)
            abstract fun getHbLr(enviorSupvsEntity: PjEnviorSupvsEntity)
            abstract fun getQueryCun(name:String, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText)
            abstract fun getCountCyCode(code: String,hjzzlx: Int,gddwid: String,date: String, roleid: Int,hjzzsj: Int,no: Int,zdwtCha: Int)
            abstract fun getHbjcZl(date:String,startDate:String,endDate:String,code:String)
            abstract fun getRjhjjcPoint(point: String)
            abstract fun getDdUpdate(enviorSupvsEntity: PointRecordEntity)
            abstract fun getDdLr(enviorSupvsEntity: PointRecordEntity)
            abstract fun getDdDetel(ids:String)
            abstract fun getHbjcListCode(roleid: Int,limit:Int,page:Int, code:String,hjzzsj:Int,date:String,point:String,zdwtCha:Int,hjzzlx:Int)
            abstract fun getDdList(userId:String,page:Int)
            //热力图
            abstract fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage:Int, code:String, hjzzlx:Int)

            abstract fun getBcjcYears()
            abstract fun getBcjcAddCyjg(edtity:List<BccyjgEntity>)
            abstract fun getBcjcUpdateCyjg(edtity:List<BccyjgEntity>)
            abstract fun getBcjcCyjg(code:String,years:List<String>)
            abstract fun getBcjcCyjgAll(code:String,years:List<String>)

        }
    }
}
