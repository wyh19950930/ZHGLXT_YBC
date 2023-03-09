package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R.drawable.point
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.wy.RentEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.PropertyAddFzContract
import com.jymj.zhglxt.zjd.contract.PropertyContract
import com.jymj.zhglxt.zjd.contract.WwjContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class PropertyPresenter : PropertyContract.Companion.Presenter(){
    //物业点查
    override fun getWyPoint(point: String,type:Int) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        jsonObject.put("type", type)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PROPERTY_QUERYBYPOINT).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<PropertyDto> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PropertyDto>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnWyPoint(json.data)
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

    //返租列表
    override fun getWyFzList(code:String,cqr:String,mph:String,limit:Int,page:Int) {

        val jsonObject = JSONObject()
        if (!code.equals("")){
            jsonObject.put("code", code)
        }
        jsonObject.put("cqr", cqr)
        jsonObject.put("mph", mph)
        jsonObject.put("limit", limit)
        jsonObject.put("page", page)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PROPERTY_GETLEASEBACK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<PageUtils<RentEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<RentEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnWyFzList(json.data.list)
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
    //出租列表
    override fun getWyCzList(code:String,cqr:String,mph:String,limit:Int,page:Int) {

        val jsonObject = JSONObject()
        if (!code.equals("")){
            jsonObject.put("code", code)
        }
        jsonObject.put("cqr", cqr)
        jsonObject.put("mph", mph)
        jsonObject.put("limit", limit)
        jsonObject.put("page", page)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PROPERTY_GETRENTAILLIST).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<PageUtils<RentEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<RentEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnWyCzList(json.data.list)
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
}