package com.jymj.zhglxt.login.model

import com.google.gson.Gson
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.*
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class SendMessageModel : SendMessageContract.Model{
    //获取流管员列表
    override fun getHqlgyList(): PostRequest<BaseRespose<List<User>>> {
//        val jsonObject = JSONObject()
        val jsonObject = ""
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<List<User>>>(ApiConstants.MESSAGE_GET_PERSONNEL).upJson(jsonObject)
    }

    //发布消息/处理or修改消息
    override fun getAddMessage(flowMassageEntity: FlowMassageEntity): PostRequest<String> {
        val gson = Gson()
        val flowMassageEntity = gson.toJson(flowMassageEntity)
        val encrypt = AesEncryptUtils.encrypt(flowMassageEntity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.MESSAGE_SAVE_ORUPDATE).upJson(sss)
    }

    //扫描二维码和宅基地进行绑定
    override fun getEwmsmZjdBd(objectid: Long, qrcode: String): PostRequest<BaseRespose<YlFolwEntity>> {
        val jsonObject = JSONObject()
        jsonObject.put("objectid", objectid)
        jsonObject.put("qrcode", qrcode)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<YlFolwEntity>>(ApiConstants.FLOW_SAVE_ZHAIRQ).upJson(jsonObject)
    }


}