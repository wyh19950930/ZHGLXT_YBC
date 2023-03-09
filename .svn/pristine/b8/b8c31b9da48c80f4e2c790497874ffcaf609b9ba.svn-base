package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.CzlbContract
import com.jymj.zhglxt.login.contract.WyContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class WyModel : WyContract.Model{
    //查询首页基本情况统计
    override fun getJbqktj(code:String): PostRequest<String> {

        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_HOME_COUNT).upJson(sss)
    }

    //查询村庄情况列表
    override fun getCxczqk(lgyname: String, xzqmc: String): PostRequest<String> {//FLOW_XZQ_LIST
        val jsonObject = JSONObject()
        jsonObject.put("key", lgyname)
//        jsonObject.put("xzqmc", xzqmc)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.PROPERTY_QUERYBYCUN).upJson(sss)
    }
}