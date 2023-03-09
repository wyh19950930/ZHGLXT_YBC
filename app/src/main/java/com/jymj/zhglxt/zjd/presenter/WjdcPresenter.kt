package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R.drawable.point
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTj2Bean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTjBean
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.WjdcContract
import com.jymj.zhglxt.zjd.contract.YqglContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class WjdcPresenter : WjdcContract.Companion.Presenter(){

    override fun getBcjcCjtjjzyzcqk(code: String, years: String) {

        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("year", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"year\":${Gson().toJson(years+"年")}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GET_FZYY).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcfzyyEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcfzyyEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            if (json.data.size==0){

                                val arrayList = ArrayList<BcfzyyEntity>()
//                                arrayList.add(BcfzyyEntity())
                                mView.returnBcjcCjtjjzyzcqk(arrayList)
                            }else{

                                mView.returnBcjcCjtjjzyzcqk(json.data)
                            }
                        }else{
//                            mView.showErrorTip("数据加载为空")
                            val arrayList = ArrayList<BcfzyyEntity>()
//                            arrayList.add(BcfzyyEntity())
                            mView.returnBcjcCjtjjzyzcqk(arrayList)
                        }
                    }else{
                        val arrayList = ArrayList<BcfzyyEntity>()
//                        arrayList.add(BcfzyyEntity())
                        mView.returnBcjcCjtjjzyzcqk(arrayList)
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                    val arrayList = ArrayList<BcfzyyEntity>()
//                    arrayList.add(BcfzyyEntity())
                    mView.returnBcjcCjtjjzyzcqk(arrayList)
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
                val arrayList = ArrayList<BcfzyyEntity>()
//                arrayList.add(BcfzyyEntity())
                mView.returnBcjcCjtjjzyzcqk(arrayList)
            }

        })
    }
    override fun getBcjcGetOptions() {

        var ss = "{}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GET_OPTIONS).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<OptionsEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<OptionsEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBcjcGetOptions(json.data)
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

    override fun getBcjcYears() {
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
                            mView.onBcjcYears(json.data)
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
    override fun getBcjcFzyySave(bcfzyyEntitys:ArrayList<BcfzyyEntity>) {
        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(bcfzyyEntitys))//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_FZYY_SAVE).upJson(sss).execute(object ://EnviorListEntity
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
                        mView.onBcjcFzyySave("添加成功")
                    }else{
                        mView.showErrorTip("添加失败")
                    }
                } else {
                    mView.showErrorTip("添加失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("添加失败")
            }

        })
    }


}