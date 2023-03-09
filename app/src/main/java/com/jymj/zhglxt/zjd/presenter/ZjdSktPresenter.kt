package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R.drawable.point
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.BcSktEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtzcEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczhandqkEntity
import com.jymj.zhglxt.zjd.contract.ZjdSktContract
import com.jymj.zhglxt.zjd.contract.ZjdTdqsContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class ZjdSktPresenter : ZjdSktContract.Companion.Presenter(){
    override fun getBcjcAddSkt(edtity: BcSktEntity) {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_SKTSAVE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.code==0){
                        if (json.msg!=null){
                            mView.returnBcjcAddSkt(json.msg)
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

    override fun getBchcSkt(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETSKT).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcSktEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcSktEntity>>?>() {}.type)
                    if (json.code==0){

                        if (json.data.size == 0){
                            val arrayList = ArrayList<BcSktEntity>()
                            arrayList.add(BcSktEntity())
                            mView.returnBchcSkt(arrayList)
                        }else{
                            mView.returnBchcSkt(json.data)
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

    override fun getBcjcYears() {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt("{}".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETYEARS).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<String>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<String>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBcjcYears(json.data)
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
}