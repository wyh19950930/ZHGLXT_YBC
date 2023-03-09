package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.login.contract.ZjdYztContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class ZjdYztModel : ZjdYztContract.Model{
    override fun getCS(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String): PostRequest<String> {

        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("codeList", codeList)
        if (!zch.equals(""))
            jsonObject.put("zch", zch.toInt())
        else
            jsonObject.put("zch",0)
        jsonObject.put("jzgm", qwbcj)
        jsonObject.put("qwbcj", jzgm)
        jsonObject.put("dlzz75n", dlzz75n)
        jsonObject.put("dlzz75w", dlzz75w)
        jsonObject.put("zzl", zzl)
        jsonObject.put("khf", khf)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.MOVE_COST_CS).upJson(jsonObject)
    }

    override fun getBaseDataCe(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String): PostRequest<String> {
        val jsonObject = JSONObject()
        if (!zch.equals(""))
            jsonObject.put("zch", zch.toInt())
        else
            jsonObject.put("zch",0)
        jsonObject.put("jzgm", qwbcj)
        jsonObject.put("qwbcj", jzgm)
        jsonObject.put("dlzz75n", dlzz75n)
        jsonObject.put("dlzz75w", dlzz75w)
        jsonObject.put("zzl", zzl)
        jsonObject.put("khf", khf)
        jsonObject.put("points", points)
        jsonObject.put("codeList", codeList)//MOVE_COST_CH_YE_FZH
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.MOVE_COST_CH_YE_FZH).upJson(jsonObject)
    }

    override fun getYztList(page: Int,limit:Int,type:Int,projectName:String): PostRequest<String> {//
        val jsonObject = JSONObject()
        jsonObject.put("page", page)
        jsonObject.put("limit", limit)
        jsonObject.put("type", type)
        jsonObject.put("projectName", projectName)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.HUHOINQUERY_QUERY_RECORD).upJson(jsonObject)//.upJson(sss)SYS_LAYER_LIST
    }

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

    override fun getYztPoint(point: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("point", point);
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.POINT_QUERY_BY_YZT).upJson(jsonObject)
    }

    //查询村庄情况列表
    override fun getCxczqk(lgyname: String, xzqmc: String): PostRequest<BaseRespose<PageUtils<XzqInfoEntity>>> {//FLOW_XZQ_LIST
        val jsonObject = JSONObject()
        jsonObject.put("key", lgyname)
//        jsonObject.put("xzqmc", xzqmc)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<PageUtils<XzqInfoEntity>>>(ApiConstants.FLOW_XZQ_LIST).upJson(jsonObject)
    }
}