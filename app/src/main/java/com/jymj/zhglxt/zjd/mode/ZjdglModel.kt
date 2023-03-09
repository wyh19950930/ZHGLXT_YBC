package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.contract.ZjdglContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject
import java.math.BigDecimal

/**
 * Created by setsuna on 18-3-23.
 */
class ZjdglModel : ZjdglContract.Model{


    override fun getYewuFrame(point: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("points", point);
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.URL_FRAME_YEWU).upJson(sss)
    }

    override fun getFrameJcxx(string: String): PostRequest<String> {

        val jsonObject = JSONObject()
        jsonObject.put("points", string)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.URL_FRAMENEW_JCXX).upJson(jsonObject)
    }
    override fun getEnviorsRjhj(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage: Int, code: String, hjzzlx: Int): PostRequest<BaseRespose<YlFolwEntity>> {

        return OkGo.post<BaseRespose<YlFolwEntity>>(ApiConstants.FLOW_GET_BY_POINT).upJson("")
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

}