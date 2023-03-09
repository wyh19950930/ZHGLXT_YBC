package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.contract.HomeContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject
import java.math.BigDecimal

/**
 * Created by setsuna on 18-3-23.
 */
class HomeModel : HomeContract.Model{

    //获取未处理数量
    override fun getWclCount(): PostRequest<BaseRespose<Int>> {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<Int>>(ApiConstants.MESSAGE_GET_COUNT).upJson("{}")//jsonObject
    }
    ///热力图
    override fun getRlt(code: String, type: Int, xmax: BigDecimal, xmin: BigDecimal, ymax: BigDecimal, ymin: BigDecimal): PostRequest<BaseRespose<List<XzqInfoEntity>>> {
        val jsonObject = JSONObject()
        /*if (AppCache.getInstance().zhqx==2)
        jsonObject.put("code", code)*/
        jsonObject.put("type", type)
        /*jsonObject.put("xmax", xmax)
        jsonObject.put("xmin", xmin)
        jsonObject.put("ymax", ymax)
        jsonObject.put("ymin", ymin)*/
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<List<XzqInfoEntity>>>(ApiConstants.FLOW_GET_HEAT_MAP).upJson(jsonObject)
    }

    //扫描二维码查找院落
    override fun getSmewmcyl(qrcode: String): PostRequest<BaseRespose<YlFolwEntity>> {
        val jsonObject = JSONObject()
        jsonObject.put("qrcode", qrcode)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<YlFolwEntity>>(ApiConstants.FLOW_GET_RRCODE).upJson(jsonObject)
    }

    //查询村庄情况列表
    override fun getCxczqk(lgyname: String, xzqmc: String): PostRequest<BaseRespose<PageUtils<XzqInfoEntity>>> {//FLOW_XZQ_LIST
        val jsonObject = JSONObject()
        jsonObject.put("key", "")
//        jsonObject.put("xzqmc", xzqmc)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<PageUtils<XzqInfoEntity>>>(ApiConstants.FLOW_XZQ_LIST).upJson(jsonObject)
    }
    //流动人员列表
    override fun getCxldry(code: String, name: String, xzqmc: String): PostRequest<BaseRespose<PageUtils<YlFolwEntity>>> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("key", name)
        jsonObject.put("limit", 5)
        jsonObject.put("page", 1)
//        jsonObject.put("xzqmc", xzqmc)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<PageUtils<YlFolwEntity>>>(ApiConstants.FLOW_GET_YLLIST).upJson(jsonObject)
    }
    //点查院落信息
    override fun getDcylxx(point: String,ylId:Long?): PostRequest<BaseRespose<YlFolwEntity>> {
        val jsonObject = JSONObject()
        if (!point.equals(""))
        jsonObject.put("point", point)
        if (ylId!=null)
        jsonObject.put("ylId", ylId)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<YlFolwEntity>>(ApiConstants.FLOW_GET_BY_POINT).upJson(jsonObject)
    }

    //查询首页行业类型统计
    override fun getHylxtj(code:String): PostRequest<BaseRespose<List<IndustryEntity>>> {

        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<List<IndustryEntity>>>(ApiConstants.FLOW_GET_INDUSTRY).upJson(jsonObject)
    }
    //查询首页基本情况统计
    override fun getJbqktj(code:String): PostRequest<BaseRespose<FirstTjBean>> {

        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<FirstTjBean>>(ApiConstants.FLOW_GET_HOME_COUNT).upJson(jsonObject)
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