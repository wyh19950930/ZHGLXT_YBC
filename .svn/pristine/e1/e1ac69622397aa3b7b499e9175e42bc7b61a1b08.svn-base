package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.contract.FjxcActContract
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class FjxcActPresenter : FjxcActContract.Companion.Presenter(){


    //翻建点查
    override fun getFanJianDetail(id:Long) {
//        mView.returnFanJian(point)
        val httpParams = JSONObject()
        httpParams.put("id",id)
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.RENOVATED_QUERY_INFO).upJson(sss).execute(object :
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
                    val json: BaseRespose<Renovated> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<Renovated>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnFanJianDetail(json.data)
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

    //删除翻建点位
    override fun getFanJianDelete(id: Renovated) {//翻建删除
//        mView.returnFanJian(point)
        val gson = Gson()
        val toJson = gson.toJson(id)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.YL_DELETE_RENOVATED).upJson(sss).execute(object :
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
                        mView.returnFanJianDelete("删除成功")
                        /*if (cash.data!=null){
                            mView.returnFanJianDelete("删除成功")
                        }else{
                            mView.showErrorTip(cash.getMsg())
                        }*/
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

    //翻建添加点位
    override fun getFanJianUpdate(point: Renovated) {//returnFanJianAdd
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
                        mView.returnFanJianUpdate(json.getMsg())
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

}