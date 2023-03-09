package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.LdrkDetailContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class LdrkDetailModel : LdrkDetailContract.Model{
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


    override fun getCxldry(ylId:Long): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("ylId", ylId)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_QUERY_LIST).upJson(sss)
    }
    //扫描二维码和宅基地进行绑定
    override fun getEwmsmZjdBd(objectid: Long, qrcode: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("objectid", objectid)
        jsonObject.put("qrcode", qrcode)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_SAVE_ZHAIRQ).upJson(sss)
    }


}