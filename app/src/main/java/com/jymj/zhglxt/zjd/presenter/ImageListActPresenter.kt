package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.EnviorFileFhEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.contract.ImageListActContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class ImageListActPresenter : ImageListActContract.Companion.Presenter(){

    //qutype  roleid
    override fun getImageList(limit: Int,page: Int,type: String,xmin: String,xmax: String,ymin: String,ymax: String,name: String,location: String,sjfl: String) {//请求数据
        val httpParams = JSONObject()
        httpParams.put("limit",limit)
        httpParams.put("page",page)
        httpParams.put("type",type)
        httpParams.put("xmin",xmin)
        httpParams.put("xmax",xmax)
        httpParams.put("ymin",ymin)
        httpParams.put("ymax",ymax)
        httpParams.put("name",name)
        httpParams.put("hjzzsj",sjfl)
        httpParams.put("location",location)
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIOR_FILE_INFO).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){//BaseRespose<PageUtils<EnviorFileFhEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
//                    val json: EnviorListEntity = Gson().fromJson(cash, object : TypeToken<EnviorListEntity?>() {}.type)
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<EnviorFileFhEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<EnviorFileFhEntity>>?>() {}.type)
                    if (json.code==0){
                        mView.returnImageList(json.data.list)

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