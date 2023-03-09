package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.rjhj.contract.RjhjDwckContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class RjhjDwckModel : RjhjDwckContract.Model{

    //获取未处理数量
    override fun getWclCount(): PostRequest<BaseRespose<Int>> {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<Int>>(ApiConstants.MESSAGE_GET_COUNT).upJson("{}")//jsonObject
    }
}