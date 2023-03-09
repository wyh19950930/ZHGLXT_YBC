package com.jymj.zhglxt.login.model

import com.google.gson.Gson
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.login.contract.MyNewsContract
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class MyNewsModel : MyNewsContract.Model{

    //发布消息/处理or修改消息
    override fun getAddMessage(flowMassageEntity: FlowMassageEntity): PostRequest<BaseRespose<String>> {
        val gson = Gson()
        val flowMassageEntity = gson.toJson(flowMassageEntity)
        val encrypt = AesEncryptUtils.encrypt(flowMassageEntity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<String>>(ApiConstants.MESSAGE_SAVE_ORUPDATE).upJson(flowMassageEntity)
    }
    //消息列表
    override fun getMessageList(limit:Int,page:Int): PostRequest<BaseRespose<PageUtils<FlowMassageEntity>>> {
        val jsonObject = JSONObject()
        jsonObject.put("limit", limit)
        jsonObject.put("page", page)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<PageUtils<FlowMassageEntity>>>(ApiConstants.MESSAGE_QUERY_LIST).upJson(jsonObject)
    }

}