package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBusiness
import com.jymj.zhglxt.zjd.contract.YztDcContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class YztDcPresenter : YztDcContract.Companion.Presenter(){


    override fun getBusiness(id: Long,month:String) {//参数 ylId
        var httpParams = JSONObject()
        httpParams.put("id", id)
        httpParams.put("month", month)

        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENTERPRISE_GET_BUSINESS).upJson(sss).execute(object ://
                BaseNet<String>(){//BaseRespose<List<ZhaiEntity>>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
//                Log.e("getBusiness",body)
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<List<EnterpriseBusiness>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<EnterpriseBusiness>>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data.size>0){
                                mView?.returnBusiness(json.data,month)
                            }else{
                                mView?.returnBusiness(ArrayList<EnterpriseBusiness>(),month)
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
    override fun getFanJianApplyList(sptype: Int,limit:Int,page:Int, code: String, date: String, name: String) {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("date", date)
        jsonObject.put("sptype", sptype)
        jsonObject.put("code", ""+code+"")
        jsonObject.put("limit", limit)//当前页数，从1开始
        jsonObject.put("page", page)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<PageUtils<ApplyEntity>>>(ApiConstants.APPLY_LAND_LIST ).upJson(toJson).execute(object ://APPLY_LIST1
                BaseNet<BaseRespose<PageUtils<ApplyEntity>>>(){
            override fun onStart(request: Request<BaseRespose<PageUtils<ApplyEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<ApplyEntity>>>?) {
                val cash = response?.body()
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            mView.returnFanJianApplyList(cash.data.list,sptype,cash.data.total.toInt())
                        }else {
                            mView.showErrorTip("数据为空")
                            mView.stopLoading()
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                        mView.stopLoading()
                    }
                }
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<BaseRespose<PageUtils<ApplyEntity>>>?) {
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
}