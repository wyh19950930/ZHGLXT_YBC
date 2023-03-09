package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.MeContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class MeModel : MeContract.Model{

    //获取未处理数量
    override fun getWclCount(): PostRequest<BaseRespose<Int>> {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<Int>>(ApiConstants.MESSAGE_GET_COUNT).upJson("{}")//jsonObject
    }
    //修改密码接口
    override fun changeUser(username: String, password: String, newPassword: String, deviceCode: String): PostRequest<BaseRespose<String>> {

        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        jsonObject.put("newPassword", newPassword)
        jsonObject.put("deviceCode", "866174010481386")//deviceCode
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<String>>(ApiConstants.MODIFYPWD_URL).upJson(jsonObject)
    }
    //登陆接口
    override fun login(uName: String, uPwd: String,deviceId:String): HttpParams {
        val params= HttpParams()
        params.put("username", uName)
        params.put("password", uPwd)
        params.put("deviceCode", "13934339")//deviceId
        return params
    }

}