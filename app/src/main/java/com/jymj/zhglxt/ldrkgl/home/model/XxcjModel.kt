package com.jymj.zhglxt.login.model

import com.google.gson.Gson
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.login.contract.XxcjContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class XxcjModel : XxcjContract.Model{
    override fun getLcldry(ids: List<Int>): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("ids", "[" + ids.toString().substring(1, ids.toString().length - 1) + "]")
        val encrypt = AesEncryptUtils.encrypt("{\"ids\":[${ids.get(0)}]}".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"//jsonObject
        return OkGo.post<String>(ApiConstants.FLOW_OUT_FLOW).upJson(sss)
    }

    override fun getFlowUpload(flowInfoEntity: FlowInfoEntity): PostRequest<String> {
        val gson = Gson()
        val toJson = gson.toJson(flowInfoEntity)

        val encrypt = AesEncryptUtils.encrypt(toJson)//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_SAVE_FLOW).upJson(sss)
    }

    override fun getFlowGetInfo(idCard: String,ylid:Long): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("idCard", idCard)
        jsonObject.put("ylId", ylid)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_INFO).upJson(sss)
    }


}