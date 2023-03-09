package com.jymj.zhglxt.login.model

import android.util.Log
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.YllbContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class YllbModel : YllbContract.Model{
    //点查院落信息
    override fun getDcylxx(point: String,ylId:Long): PostRequest<String> {
        val jsonObject = JSONObject()
        if (!point.equals(""))
        jsonObject.put("point", point)
        if (ylId!=null)
        jsonObject.put("ylId", ylId)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_BY_POINT).upJson(sss)
    }
    override fun getLcldry(ids: List<Int>): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("ids", "[" + ids.toString().substring(1, ids.toString().length - 1) + "]")
        val encrypt = AesEncryptUtils.encrypt("{\"ids\":[${ids.get(0)}]}".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"//jsonObject
        return OkGo.post<String>(ApiConstants.FLOW_OUT_FLOW).upJson(sss)
    }
    //流动人员列表
    override fun getCxldry(code: String, name: String, xzqmc: String,limit:Int,page:Int): PostRequest<String> {
        val jsonObject = JSONObject()
        if (!code.equals("")){
            jsonObject.put("code", code)
        }
        jsonObject.put("key", name)
        jsonObject.put("limit", limit)
        jsonObject.put("page", page)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_YLLIST).upJson(sss)
    }

}