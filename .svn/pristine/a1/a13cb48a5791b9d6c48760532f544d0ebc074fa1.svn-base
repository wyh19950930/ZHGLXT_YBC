package com.jymj.zhglxt.zjd.presenter

import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.bean.yl.ApplyCountEntity
import com.jymj.zhglxt.rjhj.bean.yl.YztPointEntity
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.CscsBean
import com.jymj.zhglxt.zjd.bean.FrameJcxxBean
import com.jymj.zhglxt.zjd.bean.YeWuFrameBean
import com.jymj.zhglxt.zjd.bean.YlEntity
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.tdxg.GhVO
import com.jymj.zhglxt.zjd.bean.tdxg.QsVO
import com.jymj.zhglxt.zjd.bean.tdxg.TdlyVO
import com.jymj.zhglxt.zjd.bean.tdxg.YztSktVO
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.yzt.cs.CSEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.ZjdglContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposeDate
import com.setsuna.common.commonutils.ToastUtils
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode

class ZjdglPresenter : ZjdglContract.Companion.Presenter(){

    //无违建点查
    override fun getWwjPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.WUWEIJIAN_CLICKQUERY).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<WwjEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<WwjEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnWwjPoint(json.data)
                        }else{
                            mView.showErrorTip("暂无数据")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })
    }

    //企业管理-待开发点查
    override fun getDkfPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.POINT_QUERYDKFBYPOINT).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<DkfVO>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<DkfVO>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnDkfPoint(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })
    }

    //企业管理-农业园区点查
    override fun getNyyqPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.POINT_QUERYNYYQBYPOINT).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<YztNyyqEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<YztNyyqEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnNyglPoint(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getCyyqPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENTERPRISE_QUERYBYPOINT).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<EnterpriseBasisEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<EnterpriseBasisEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnQyglPoint(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getTxPoint(point: String,type:Int) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        jsonObject.put("type", type)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<BaseRespose<TdlyVO>>(ApiConstants.POINT_QUERY_TDLY).upJson(sss).execute(object :
                BaseNet<BaseRespose<TdlyVO>>(){
            override fun onStart(request: Request<BaseRespose<TdlyVO>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<BaseRespose<TdlyVO>>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val body = response.body()
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPointTx(body.data)
                            }else{
                                mView?.showErrorTip("暂无数据")
                            }
                        }else{
                            mView?.showErrorTip("暂无数据")
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<TdlyVO>>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }

        })
    }
    override fun getTdghPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<BaseRespose<YztSktVO>>(ApiConstants.POINT_QUERY_TDGH).upJson(sss).execute(object :
                BaseNet<BaseRespose<YztSktVO>>(){
            override fun onStart(request: Request<BaseRespose<YztSktVO>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<BaseRespose<YztSktVO>>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val body = response.body()
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPointTxSkt(body.data)
                            }else{
                                mView?.showErrorTip("暂无数据")
                            }
                        }else{
                            mView?.showErrorTip("暂无数据")
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<YztSktVO>>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }

        })
    }
    override fun getTdqsPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<BaseRespose<QsVO>>(ApiConstants.POINT_QUERY_TDQS).upJson(sss).execute(object :
                BaseNet<BaseRespose<QsVO>>(){
            override fun onStart(request: Request<BaseRespose<QsVO>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<BaseRespose<QsVO>>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val body = response.body()
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPointQs(body.data)
                            }else{
                                mView?.showErrorTip("暂无数据")
                            }
                        }else{
                            mView?.showErrorTip("暂无数据")
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<QsVO>>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }

        })
    }
    override fun getKjghPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<BaseRespose<GhVO>>(ApiConstants.POINT_QUERY_KJGH).upJson(sss).execute(object :
                BaseNet<BaseRespose<GhVO>>(){
            override fun onStart(request: Request<BaseRespose<GhVO>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<BaseRespose<GhVO>>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val body = response.body()
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPointKjgh(body.data)
                            }else{
                                mView?.showErrorTip("暂无数据")
                            }
                        }else{
                            mView?.showErrorTip("暂无数据")
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<GhVO>>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }

        })
    }
    override fun getYewuFrame(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("points", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.URL_FRAME_YEWU).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: YeWuFrameBean = Gson().fromJson(decrypt, object : TypeToken<YeWuFrameBean?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data!=null){
                                mView?.onYewuFrame(json.data)
                            }else{
                                mView?.showErrorTip("暂无数据")
                            }
                        }else{
                            mView?.showErrorTip("暂无数据")
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getFrameJcxx(string: String) {
        val jsonObject = JSONObject()
        jsonObject.put("points", string)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.URL_FRAMENEW_JCXX).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                        var json = Gson().fromJson(decrypt, BaseRespose<FrameJcxxBean.DataBean>::class.java)
                        val json: BaseRespose<FrameJcxxBean.DataBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<FrameJcxxBean.DataBean>?>() {}.type)

                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
//                    val json1: FrameJcxxBean.DataBean = Gson().fromJson(json.data, FrameJcxxBean.DataBean::class.java)
                            mView?.onFrameJcxx(json.data)
                        }else{
                            mView?.showErrorTip("暂无数据")
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }
        })
    }

    override fun getDdDetel(ids:String){
        val idss = "{\"ids\":[" + ids.toInt() + "]}"
//        val idss = "[" + ids.toInt() + "]"

        val encrypt = AesEncryptUtils.encrypt(idss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPV_DELETE_POINT).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onDdLr("删除成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("删除失败")
                    }
                }else{
                    mView?.showErrorTip("删除失败")
                }
                mView!!.stopLoading()
                /*val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            if (cash.data.list.size>0){
                                mView.returnRjhjjcList(cash.data.list)
                            }else{
                                mView.showErrorTip("数据加载出错")
                            }
                        }else {
                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                    }
                }*/

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("网络错误")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getEnviorSupvsQueryPoint(point: String,type: Int) {//1:固定点位    2：非固定点位
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        jsonObject.put("type", type)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIOR_SUPVS_QUERY_BY_POINT).upJson(sss).execute(object ://RjhjPointBean
                BaseNet<String>(){//BaseRespose<WtlrBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){

                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<WtlrBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<WtlrBean>?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null){
                            mView.returnEnviorSupvsQueryPoint(json!!.data)
                        }else {
                            mView.showErrorTip("附近暂无问题数据")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.message()!=null){
                    mView.showErrorTip(response?.exception.message)
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

        })
    }
    //获取村庄
    override fun getQueryCun(name:String, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText) {
        val jsonObject = JSONObject()
        jsonObject.put("name",name)//date   BaseResposeDate<>

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPV_QUERY_CUN).upJson(sss).execute(object ://httpParams  .upJson("{\"name\":\""+name+"\"}")
                BaseNet<String>(){//BaseResposeDate<List<CjEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseResposeDate<List<CjEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseResposeDate<List<CjEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json!!.date!=null){
                            mView.returnQueryCun(json!!.date,pjEnviorSupvsEntity,rlv_xzxzq,tvWzenvironmental)
                        }else {
                            mView.showErrorTip("数据为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!!.exception!=null){
                    if (response.exception.message!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("请求失败")
                    }
                }else{
                    mView.showErrorTip("请求失败")
                }

            }

        })
    }
    override fun getHbLr(enviorSupvsEntity: PjEnviorSupvsEntity){
        val jsonObject = Gson().toJson(enviorSupvsEntity)//.substring(18, Gson().toJson(enviorSupvsEntity).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPVSAVE).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onHbLr("添加成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("添加失败")
                    }
                }else{
                    mView?.showErrorTip("添加失败")
                }
                mView!!.stopLoading()
                /*val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            if (cash.data.list.size>0){
                                mView.returnRjhjjcList(cash.data.list)
                            }else{
                                mView.showErrorTip("数据加载出错")
                            }
                        }else {
                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                    }
                }*/

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception.message!=null){
                        if (!response.exception.message.equals(""))
                            mView.showErrorTip(response.exception.message)
                        else
                            mView.showErrorTip("添加失败")
                    }else{
                        mView.showErrorTip("添加失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getDdLr(enviorSupvsEntity: PointRecordEntity){
        val jsonObject = Gson().toJson(enviorSupvsEntity)//.substring(18, Gson().toJson(enviorSupvsEntity).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPV_SAVE_POINT).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onDdLr("添加成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("添加失败")
                    }
                }else{
                    mView?.showErrorTip("添加失败")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("网络错误")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getDdUpdate(enviorSupvsEntity: PointRecordEntity){
        val toJson = Gson().toJson(enviorSupvsEntity)//.substring(18, Gson().toJson(enviorSupvsEntity).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPV_UPDATE_POINT).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onDdLr("修改成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("修改失败")
                    }
                }else{
                    mView?.showErrorTip("修改失败")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("修改失败")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getRjhjjcPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)//RjhjPointBean
        jsonObject.put("type", 3)//RjhjPointBean  ILLEGAL_QUERY_BY_POINT
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPVS_ILLEGAL).upJson(sss).execute(object ://RjhjPointBean
                BaseNet<String>(){//BaseRespose<WtlrBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<WtlrBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<WtlrBean>?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null||json!!.data.pointRecordEntity!=null){
                            mView.returnRjhjjcPoint(json!!.data)
                        }else {
                            mView.showErrorTip("数据为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.message()!=null){
                    mView.showErrorTip(response?.exception.message)
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

        })
    }
    override fun getHbjcListCode(roleid: Int,limit:Int,page:Int, code:String,hjzzsj:Int,date:String,point:String,zdwtCha:Int,hjzzlx:Int){
        val jsonObject = JSONObject()
        if (roleid==50){
            jsonObject.put("newtype", 0)//当前页数，从1开始
        }
        if (roleid!=-1)
            jsonObject.put("qutype", roleid)//当前页数，从1开始
        jsonObject.put("limit", limit)//当前页数，从1开始
        jsonObject.put("page", page)//当前页数，从1开始

        jsonObject.put("date", date)//当前页数，从1开始  "["+code+"]"
        jsonObject.put("point", point)//当前页数，从1开始  "["+code+"]"
        if (hjzzsj!=0){
            jsonObject.put("hjzzsj", hjzzsj)//
        }
        if (zdwtCha!=-1){
            jsonObject.put("zdwt", zdwtCha)//重大问题
        }
        if (AppCache.getInstance().type!=4&&hjzzlx==2&&AppCache.getInstance().type!=2&&AppCache.getInstance().type!=3){
            jsonObject.put("hjzzlx",hjzzlx)
            jsonObject.put("gddwid",code)
        }else{
            jsonObject.put("code", ""+code+"")//当前页数，从1开始  "["+code+"]"
        }
//        jsonObject.put("status", 0)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPVSLIST).upJson(sss).execute(object :
                BaseNet<String>(){//EnviorListEntity
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: EnviorListEntity = Gson().fromJson(decrypt, object : TypeToken<EnviorListEntity?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null){
                            mView.returnHbjcListCode(json.data.list,roleid,json.data.totalCount)
                        }else {
                            mView.showErrorTip("数据为空")
                            mView.stopLoading()
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        mView.stopLoading()
                    }
                }
//                mView.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.message()!=null){
                    mView.showErrorTip(response?.message())
                }else{
                    mView.showErrorTip("请求失败")
                }
                mView.stopLoading()
            }
        })
    }
    //1  区  2镇  3 村  4 最小
    override fun getEnviorsRjhj(type: String,xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal,isImage:Int,code:String,hjzzlx:Int) {//type  xmin  xmax  ymin  ymax
        val pointAndTypeEntity = PointAndTypeEntity()
        pointAndTypeEntity.type = type.toInt()
        pointAndTypeEntity.xmax = xmax
        pointAndTypeEntity.xmin = xmin
        pointAndTypeEntity.ymin = ymin
        pointAndTypeEntity.ymax = ymax
        if (AppCache.getInstance().type!=4&& AppCache.getInstance().type!=2&& AppCache.getInstance().type!=3)
            pointAndTypeEntity.hjzzlx = hjzzlx
        /*if (type.equals("4")){
            pointAndTypeEntity.xmax = xmax
            pointAndTypeEntity.xmin = xmin
            pointAndTypeEntity.ymin = ymin
            pointAndTypeEntity.ymax = ymax
        }*/
        if (!code.equals("")&&!code.equals("110112")&&code.length==9)
            pointAndTypeEntity.code = code
        val toJson = Gson().toJson(pointAndTypeEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPVS_GETENVIORS_BYXY)
                .upJson(sss).execute(object : BaseNet<String>(){//BaseRespose<List<EnviorFileFhEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<EnviorFileFhEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<EnviorFileFhEntity>>?>() {}.type)
                    if (json.code == 0){
                        if (json.data != null){
                            mView.returnEnviorsRjhj(json.data,type,xmin,xmax,ymin,ymax,isImage)
                        }else {
                            mView.showErrorTip("数据为空")
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }
        })
    }
    //翻建点查
    override fun getFanJian(point: String) {
//        mView.returnFanJian(point)
        val toJson = JSONObject()
        toJson.put("point",point)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.YL_GET_RENOVATED).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<FjBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<FjBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<FjBean>?>() {}.type)

                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnFanJian(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("点位不存在")//response?.exception?.message
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

        })
    }
    //扫描二维码查找院落  qrcode
    override fun getSmewmcyl(qrcode:String) {
        val jsonObject = JSONObject()
        jsonObject.put("qrcode", qrcode)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.FLOW_GET_RRCODE).upJson(sss).execute(
                object : BaseNet<String>() {//BaseRespose<YlFolwEntity>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<YlFolwEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YlFolwEntity>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnSmewmcyl(json.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //热力图  code  type  xmax  xmin  ymax  ymin
    override fun getYzt(code:String, type: Int, xmax:BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal) {
        val jsonObject = JSONObject()
        /*if (AppCache.getInstance().zhqx==2)*/
        jsonObject.put("code", code)
        jsonObject.put("type", type)
        jsonObject.put("xmax", xmin.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("xmin", xmax.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("ymax", ymin.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("ymin", ymax.setScale(5, RoundingMode.HALF_UP))
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.FLOW_GET_BCJH).upJson(sss).execute(
                object : BaseNet<String>() {//BaseRespose<List<XzqInfoEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<XzqInfoEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<XzqInfoEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnYzt(json.data,type)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //热力图  code  type  xmax  xmin  ymax  ymin
    override fun getRlt(code:String, type: Int, xmax:BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal) {
        val jsonObject = JSONObject()
        /*if (AppCache.getInstance().zhqx==2)
        jsonObject.put("code", code)*/
        jsonObject.put("type", type)
        /*jsonObject.put("xmax", xmax.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("xmin", xmin.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("ymax", ymax.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("ymin", ymin.setScale(5, RoundingMode.HALF_UP))*/
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.FLOW_GET_HEAT_MAP).upJson(sss).execute(
                object : BaseNet<String>() {//BaseRespose<List<XzqInfoEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<XzqInfoEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<XzqInfoEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnRlt(json.data,type)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //宅基地热力图  code  type  xmax  xmin  ymax  ymin
    override fun getZjdRlt(code:String, type: Int, xmin:BigDecimal, xmax:BigDecimal, ymin:BigDecimal, ymax:BigDecimal) {
        val jsonObject = JSONObject()
        /*if (AppCache.getInstance().zhqx==2)
        jsonObject.put("code", code)*/
        jsonObject.put("type", type)
        jsonObject.put("xmax", xmax.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("xmin", xmin.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("ymax", ymax.setScale(5, RoundingMode.HALF_UP))
        jsonObject.put("ymin", ymin.setScale(5, RoundingMode.HALF_UP))
//        Log.e("jsonObject","${Gson().toJson(jsonObject)}")
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_LAND_QUERY_COUNT).upJson(sss).execute(
                object : BaseNet<String>() {//BaseRespose<List<XzqInfoEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<ApplyCountEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ApplyCountEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnZjdRlt(json.data,type)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //点查院落信息
    override fun getDcylxx(point:String, ylId: Long?) {
        val jsonObject = JSONObject()
        if (!point.equals(""))
            jsonObject.put("point", point)
        if (ylId!=null)
            jsonObject.put("ylId", ylId)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.FLOW_GET_BY_POINT).upJson(sss).execute(
                object : BaseNet<String>() {//BaseRespose<YlFolwEntity>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<YlFolwEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YlFolwEntity>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnDcylxx(json.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("")//response?.exception?.message
            }

        })
    }
    override fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, code:String) {//type  xmin  xmax  ymin  ymax
        val httpParams = JSONObject()
        httpParams.put("type",type.toInt())
        httpParams.put("xmax",xmax.toString())
        httpParams.put("xmin",xmin.toString())
        httpParams.put("ymin",ymin.toString())
        httpParams.put("ymax",ymax.toString())
        if (!code.equals("")&&!code.equals("110112")&&code.length==9)//BaseRespose<List<FwglJhBean>>
            httpParams.put("code",code.toString())
        val pointAndTypeEntity = PointAndTypeEntity()
        pointAndTypeEntity.type = type.toInt()
        pointAndTypeEntity.xmax = xmax
        pointAndTypeEntity.xmin = xmin
        pointAndTypeEntity.ymin = ymin
        pointAndTypeEntity.ymax = ymax

        if (!code.equals("")&&!code.equals("110112")&&code.length==9)
            pointAndTypeEntity.code = code//.upJson(Gson().toJson(pointAndTypeEntity))   .params(httpParams)
        val toJson = Gson().toJson(pointAndTypeEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.RENOVATED_GET_RENOVATED_BYXY).upJson(sss).execute(
                object : BaseNet<String>(){//BaseRespose<List<FwglJhBean>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<FwglJhBean>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<FwglJhBean>>?>() {}.type)
                    if (json.code == 0){
                        if (json.data != null){
                            mView.returnEnviorsByxy(json.data,type,xmax,xmin,ymin,ymax)
                        }else {
                            mView.showErrorTip("数据为空")
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }
        })
    }
    override fun getApplyByPoint(point: String) {
        var jsonObject = JSONObject()
        jsonObject.put("point", point)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_LAND_GET_BY_POINT ).upJson(sss).execute(object ://APPLY_BY_POINT1
                BaseNet<String>(){//BaseRespose<List<ApplyLandEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<List<ApplyLandEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ApplyLandEntity>>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data!=null){
                                mView?.returnApplyByPoint(json.data)
                            }else{
                                mView?.showErrorTip("")
                            }
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("查询失败")
                    }
                }else{
                    mView?.showErrorTip("查询失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)
                            else
                                mView.showErrorTip("查询失败")
                        }else{
                            mView.showErrorTip("查询失败")
                        }
                    }else{
                        mView.showErrorTip("查询失败")
                    }
                }
//                mView.showErrorTip("网络错误")
            }
        })
    }
    override fun getPointGetYl(point: String) {//point/getYl
        var jsonObject = JSONObject()
        jsonObject.put("point", point)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.POINT_GETYL ).upJson(sss).execute(object ://APPLY_BY_POINT1
                BaseNet<String>(){//BaseRespose<List<ApplyLandEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<YLEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YLEntity>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data!=null){
                                mView?.returnPointGetYl(json.data)
                            }else{
                                mView?.showErrorTip("")
                            }
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("查询失败")
                    }
                }else{
                    mView?.showErrorTip("查询失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)
                            else
                                mView.showErrorTip("查询失败")
                        }else{
                            mView.showErrorTip("查询失败")
                        }
                    }else{
                        mView.showErrorTip("查询失败")
                    }
                }
//                mView.showErrorTip("网络错误")
            }
        })
    }
    //一张图点查
    override fun getYztByPoint(point: String) {
        var jsonObject = JSONObject()
        jsonObject.put("point", point)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.POINT_QUERY_BY_YZT ).upJson(sss).execute(object ://APPLY_BY_POINT1
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                        val json: BaseRespose<List<ApplyLandEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ApplyLandEntity>>?>() {}.type)

                        val json: BaseRespose<YztPointEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YztPointEntity>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data!=null){
                                mView?.returnYztByPoint(json.data)
                            }else{
                                mView?.showErrorTip("")
                            }
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("查询失败")
                    }
                }else{
                    mView?.showErrorTip("查询失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)
                            else
                                mView.showErrorTip("查询失败")
                        }else{
                            mView.showErrorTip("查询失败")
                        }
                    }else{
                        mView.showErrorTip("查询失败")
                    }
                }
//                mView.showErrorTip("网络错误")
            }
        })
    }
    //翻建添加点位
    override fun getFanJianAdd(point: FjBean) {//returnFanJianAdd
//        mView.returnFanJian(point)
        val gson = Gson()
        val toJson = gson.toJson(point)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.YL_RENOVATED_SAVE_OR_UPDATE).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)

                    if (json.getCode()==0){
                        mView.returnFanJianAdd(json.getMsg())
                        /*if (cash.data!=null){
                            mView.returnFanJianAdd("添加成功")
                        }else{
                            mView.showErrorTip(cash.getMsg())
                        }*/
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip(response?.exception?.message)
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

        })
    }
    //删除翻建点位
    override fun getFanJianDelete(id: FjBean) {//翻建删除
//        mView.returnFanJian(point)
        val gson = Gson()
        val toJson = gson.toJson(id)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.YL_DELETE_RENOVATED).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.getCode()==0){
                        mView.returnFanJianDelete("删除成功")
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("删除失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip(response?.exception?.message)
                }else{
                    mView.showErrorTip("删除失败")
                }
            }

        })
    }
    //请求村名
    override fun getSysXzqQueryXzqList(code:String,type:Int) {
//        mView.returnFanJian(point)
        val httpParams1 = JSONObject()
        httpParams1.put("code",code)
        httpParams1.put("type",type)
        val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<ArrayList<SysXzqEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<ArrayList<SysXzqEntity>>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnSysXzqQueryXzqList(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("点位不存在")//response?.exception?.message
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

        })
    }
    //请求测算
    override fun getSysCeSuna(cscsBean: CscsBean) {
        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(cscsBean))//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.MOVE_COST_GETFIVE_CS).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<CSEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<CSEntity>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnSysCeSuna(json.data,cscsBean)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("点位不存在")//response?.exception?.message
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

        })
    }
    //请求测算
    override fun getSysCeSuna2(cscsBean: CscsBean) {
        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(cscsBean))//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.MOVE_COST_TWO_CS).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<CSEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<CSEntity>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnSysCeSuna2(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("点位不存在")//response?.exception?.message
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

        })
    }

}