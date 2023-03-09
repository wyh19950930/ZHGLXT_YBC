package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.contract.FrameYewuContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class FrameYewuModel : FrameYewuContract.Model{

    //扫描二维码查找院落
    override fun getSmewmcyl(qrcode: String): PostRequest<BaseRespose<YlFolwEntity>> {
        val jsonObject = JSONObject()
        jsonObject.put("qrcode", qrcode)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<YlFolwEntity>>(ApiConstants.FLOW_GET_RRCODE).upJson(jsonObject)
    }

}