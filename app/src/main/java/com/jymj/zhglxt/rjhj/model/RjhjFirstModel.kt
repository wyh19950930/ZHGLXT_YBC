package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.contract.RjhjFirstContract
import com.jymj.zhglxt.rjhj.bean.TownCountEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.SwitchTimeEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.TownTypeEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class RjhjFirstModel : RjhjFirstContract.Model{
    override fun getTownType(code: String, type: Int): PostRequest<BaseRespose<TownTypeEntity>> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("type", type)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<TownTypeEntity>>(ApiConstants.TOWN_GETTOWNTYPE).upJson(jsonObject)
    }

    override fun getRjhjjcMap(code: String, type: Int): PostRequest<BaseRespose<List<TownCountEntity>>> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("type", type)//BaseRespose<YLEntity>
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<List<TownCountEntity>>>(ApiConstants.TOWN_GET_CUN_COUNT).upJson(jsonObject)
    }

    override fun getByPoint(code: String, type: Int): PostRequest<BaseRespose<TownCountEntity>> {//点查接口
        val jsonObject = JSONObject()
        jsonObject.put("code", AppCache.getInstance().code)
        jsonObject.put("point", code)
        jsonObject.put("type", type)//BaseRespose<YLEntity>
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<TownCountEntity>>(ApiConstants.TOWN_GET_BYPOINT).upJson(jsonObject)
    }

    override fun getQueryImproveRate(code: String): PostRequest<SwitchTimeEntity> {//获取 近一月  近一季  近一年
        val jsonObject = JSONObject()
        jsonObject.put("code", code)//BaseRespose<SwitchTimeEntity>  jsonObject
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<SwitchTimeEntity>(ApiConstants.TOWN_QUERY_IMPROVERATE).upJson(jsonObject)
    }

    //点查院落信息
    override fun getDcylxx(point: String,ylId:Long): PostRequest<BaseRespose<YlFolwEntity>> {
        val jsonObject = JSONObject()
        if (!point.equals(""))
        jsonObject.put("point", point)
        if (ylId!=null)
        jsonObject.put("ylId", ylId)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<YlFolwEntity>>(ApiConstants.FLOW_GET_BY_POINT).upJson(jsonObject)
    }
}