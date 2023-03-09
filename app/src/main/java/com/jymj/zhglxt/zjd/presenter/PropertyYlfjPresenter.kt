package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R.drawable.point
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.HousingLeaseEntity
import com.jymj.zhglxt.zjd.bean.wy.LeaseDto
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.wy.RentEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.*
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class PropertyYlfjPresenter : PropertyYlfjContract.Companion.Presenter(){
    //历史租客
    override fun getHistoryTenant(housid: Long) {
        val jsonObject = JSONObject()
        jsonObject.put("id", housid)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PROPERTY_HISTORYLEASEINFO).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<HousingLeaseEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<HousingLeaseEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnHistoryTenant(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
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
    //已租信息
    override fun getLeaseInfo(roomId: Long) {
        val jsonObject = JSONObject()
        jsonObject.put("roomId", roomId)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PROPERTY_GETLEASEINFO).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<LeaseDto> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<LeaseDto>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnLeaseInfo(json.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
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
    //可入住流动人员列表
    override fun getFlowGetInfo(ylid: Long) {
        val jsonObject = JSONObject()
        jsonObject.put("ylId", ylid)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.FLOW_GET_INFOBYYLID).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<FlowInfoEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<FlowInfoEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnFlowGetInfo(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
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

}