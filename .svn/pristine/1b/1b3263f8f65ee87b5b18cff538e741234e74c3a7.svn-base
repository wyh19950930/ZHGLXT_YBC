package com.jymj.zhglxt.login.model

import com.google.gson.Gson
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.ZjdLdrkContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.BccgbqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcldlbdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcrkbdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject
import java.math.BigDecimal

/**
 * Created by setsuna on 18-3-23.
 */
class ZjdLdrkModel : ZjdLdrkContract.Model{
    override fun getBchcUpdateWcwgbdqk(edtity: BcwcjybdqkEntity): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_WCJYBDQKUPDATE).upJson(sss)
    }

    override fun getBchcUpdateLdlbdqk(edtity: BcldlbdqkEntity): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_LDLBDQKUPDATE).upJson(sss)
    }

    //修改、下一步、驳回、通过 人口
    override fun getBchcUpdateRkbdqk(edtity: BcrkbdqkEntity): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_RKBDQKUPDATE).upJson(sss)
    }

    override fun getBchcCgbqk(code: String,year: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        var ss = "{\"code\":${Gson().toJson(code)},\"year\":${Gson().toJson(year)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETCGBQK).upJson(sss)
    }

    override fun getBchcAddCgbqk(edtity: BccgbqkEntity): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_CGBQKSAVE).upJson(sss)
    }
    override fun getBchcUpdateCgbqk(edtity: BccgbqkEntity): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_CGBQKUPDATE).upJson(sss)
    }

    override fun getBcjcGetInwcry(code: String, years: List<String>): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETINWCRY).upJson(sss)
    }

    override fun getBchcWcwgbdqk(code: String, years: List<String>): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETWCWGBDQK).upJson(sss)
    }

    //添加外出务工
    override fun getBchcAddWcwgbdqk(edtity: BcwcjybdqkEntity): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_WCWGBDQKSAVE).upJson(sss)
    }

    //劳动力表格
    override fun getBchcLdlbdqk(code: String, years: List<String>): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETLDLBDQK).upJson(sss)
    }

    //添加劳动力
    override fun getBchcAddLdlbdqk(edtity: BcldlbdqkEntity): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_LDLBDQKSAVE).upJson(sss)
    }

    //人口变动表格
    override fun getBchcRkbdqk(code: String, years: List<String>): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETRKBDQK).upJson(sss)
    }

    //添加人口
    override fun getBchcAddRkbdqk(edtity: List<BcrkbdqkEntity>): PostRequest<String> {
        val gson = Gson()
//        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(gson.toJson(edtity).toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_RKBDQKSAVE).upJson(sss)
    }

    override fun getBcjcYears(): PostRequest<String> {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt("{}".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETYEARS).upJson(sss)
    }

    //获取未处理数量
    override fun getWclCount(): PostRequest<String> {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt("{}".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.MESSAGE_GET_COUNT).upJson(sss)//jsonObject
    }
    ///热力图
    override fun getRlt(code: String, type: Int, xmax: BigDecimal, xmin: BigDecimal, ymax: BigDecimal, ymin: BigDecimal): PostRequest<String> {
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
        return OkGo.post<String>(ApiConstants.FLOW_GET_HEAT_MAP).upJson(sss)
    }

    //扫描二维码查找院落
    override fun getSmewmcyl(qrcode: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("qrcode", qrcode)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_RRCODE).upJson(sss)
    }

    //查询村庄情况列表
    override fun getCxczqk(lgyname: String, xzqmc: String): PostRequest<String> {//FLOW_XZQ_LIST
        val jsonObject = JSONObject()
        jsonObject.put("key", "")
//        jsonObject.put("xzqmc", xzqmc)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_XZQ_LIST).upJson(sss)
    }
    //流动人员列表
    override fun getCxldry(code: String, name: String, xzqmc: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("key", name)
        jsonObject.put("limit", 5)
        jsonObject.put("page", 1)
//        jsonObject.put("xzqmc", xzqmc)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_YLLIST).upJson(sss)
    }
    //点查院落信息
    override fun getDcylxx(point: String,ylId:Long?): PostRequest<String> {
        val jsonObject = JSONObject()
        if (!point.equals(""))
        jsonObject.put("point", point)
        if (ylId!=null)
        jsonObject.put("ylId", ylId)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_BY_POINT).upJson(sss)
    }

    //查询首页行业类型统计
    override fun getHylxtj(code:String): PostRequest<String> {

        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_INDUSTRY).upJson(sss)
    }
    //查询首页基本情况统计
    override fun getJbqktj(code:String): PostRequest<String> {

        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.FLOW_GET_HOME_COUNT).upJson(sss)
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