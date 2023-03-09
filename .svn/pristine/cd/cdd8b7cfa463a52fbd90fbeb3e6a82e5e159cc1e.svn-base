package com.jymj.zhglxt.login.model

import com.google.gson.Gson
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.contract.RjhjContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.BccyjgEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.baseapp.AppCache
import org.json.JSONObject
import java.math.BigDecimal

/**
 * Created by setsuna on 18-3-23.
 */
class RjhjModel : RjhjContract.Model{
    override fun getBchcUpdateCyjg(edtity: List<BccyjgEntity>): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_CYJGUPDATE).upJson(sss)
    }

    override fun getBchcAddCyjg(edtity: List<BccyjgEntity>): PostRequest<String> {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_CYJGSAVE).upJson(sss)
    }

    override fun getBcjcCyjg(code: String, years: List<String>): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETSAVE).upJson(sss)
    }


    override fun getBcjcYears(): PostRequest<String> {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt("{}".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.BCJC_GETYEARS).upJson(sss)
    }

    override fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage: Int, code: String, hjzzlx: Int): PostRequest<String> {
        val pointAndTypeEntity = PointAndTypeEntity()
        pointAndTypeEntity.type = type.toInt()
        pointAndTypeEntity.xmax = xmax
        pointAndTypeEntity.xmin = xmin
        pointAndTypeEntity.ymin = ymin
        pointAndTypeEntity.ymax = ymax
        pointAndTypeEntity.code = code
        if (AppCache.getInstance().type!=4&&AppCache.getInstance().type!=2&&AppCache.getInstance().type!=3)
            pointAndTypeEntity.hjzzlx = hjzzlx
        val toJson = Gson().toJson(pointAndTypeEntity)

        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPVS_GETENVIORS_BYXY).upJson(sss)
    }

    override fun getDdList(userId: String, page: Int): PostRequest<String> {
        val httpParams = JSONObject()
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){
            httpParams.put("userId",userId.toInt())
        }
        httpParams.put("limit",20)
        httpParams.put("page",page)
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPV_QUERY_POINT_LIST).upJson(sss)
    }

    override fun getHbjcListCode(roleid: Int, limit: Int, page: Int, code: String, hjzzsj: Int, date: String, point: String, zdwtCha: Int, hjzzlx: Int): PostRequest<String> {

        val jsonObject = JSONObject()
        if (roleid==50){
            jsonObject.put("newtype", 0)//当前页数，从1开始
        }
        if (roleid!=-1)
            jsonObject.put("qutype", roleid)//当前页数，从1开始
        jsonObject.put("limit", limit)//当前页数，从1开始
        jsonObject.put("page", page)//当前页数，从1开始
        val split = date.split(" - ")
        /*if (split.size>1){//stDate  enDate
            jsonObject.put("startDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("endDate", split[1])//当前页数，从1开始  "["+code+"]"
        }*/
        jsonObject.put("date", date)//当前页数，从1开始  "["+code+"]"
        jsonObject.put("point", point)//当前页数，从1开始  "["+code+"]"
        if (hjzzsj!=0){
            jsonObject.put("hjzzsj", hjzzsj)//
        }
        if (zdwtCha!=-1){
            jsonObject.put("zdwt", zdwtCha)//重大问题
        }
        if (AppCache.getInstance().type!=4&&hjzzlx==2&&AppCache.getInstance().type!=2&&AppCache.getInstance().type!=3){
            jsonObject.put("hjzzlx",hjzzlx)
            jsonObject.put("gddwid",code)
        }else{
            jsonObject.put("code", ""+code+"")//当前页数，从1开始  "["+code+"]"
        }
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPVSLIST).upJson(sss)
    }

    override fun getDdDetel(ids: String): PostRequest<String> {
        val toJson = "[" + ids.toInt() + "]"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPV_DELETE_POINT).upJson(sss)
    }

    override fun getDdLr(enviorSupvsEntity: PointRecordEntity): PostRequest<String> {
        val toJson = Gson().toJson(enviorSupvsEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPV_SAVE_POINT).upJson(sss)
    }

    override fun getDdUpdate(enviorSupvsEntity: PointRecordEntity): PostRequest<String> {
        val toJson = Gson().toJson(enviorSupvsEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPV_UPDATE_POINT).upJson(sss)
    }

    override fun getRjhjjcPoint(point: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        jsonObject.put("type", 3)//BaseRespose<YLEntity>
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPVS_ILLEGAL).upJson(sss)
    }

    override fun getHbjcZl(date: String, startDate: String, endDate: String, code: String): PostRequest<String> {
        var ssss="{}"
        if (!date.equals("")){
            ssss="{\"date\": \""+date+"\"}"
        }
        if (!code.equals("")){
            ssss="{\"code\": \""+code+"\"}"
        }
        if (!date.equals("")&&!code.equals("")){
            ssss="{\"date\": \""+date+"\",\"code\": \""+code+"\"}"
        }
        val encrypt = AesEncryptUtils.encrypt(ssss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPV_QUERY_CUN).upJson(sss)
    }

    override fun getCountCyCode(code: String, hjzzlx: Int, gddwid: String, date: String, roleid: Int, hjzzsj: Int, no: Int, zdwtCha: Int): PostRequest<String> {
        val jsonObject = JSONObject()
        if (!code.equals(""))
            jsonObject.put("code",code)
        /* if (AppCache.getInstance().type!=4&& AppCache.getInstance().type!=2&& AppCache.getInstance().type!=3)
             jsonObject.put("hjzzlx",hjz+zlx)*/
        val split = date.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
//        jsonObject.put("date",date)
        jsonObject.put("hjzzlx",hjzzlx)
        if (roleid!=-1)
            jsonObject.put("roleid",roleid)
        if (hjzzsj!=0)
            jsonObject.put("hjzzsj",hjzzsj)
        if (no!=null&&no!=0)
            jsonObject.put("no",no)
        if (zdwtCha!=-1)
            jsonObject.put("zdwt",zdwtCha)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
//        ToastUtils.showShort(jsonObject.toString())
        return OkGo.post<String>(ApiConstants.ENVIORSUPVS_COUNT_BY_CODE).upJson(sss)
    }

    override fun getQueryCun(name: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("name",name)//date   BaseResposeDate<>
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPV_QUERY_CUN).upJson(sss)
    }

    override fun getHbLr(enviorSupvsEntity: PjEnviorSupvsEntity): PostRequest<String> {
        val toJson = Gson().toJson(enviorSupvsEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPVSAVE).upJson(sss)
    }

    override fun getHbjcList(roleid: Int, limit: Int, page: Int, code: String, hjzzsj: Int, date: String, no: Int, zdwtCha: Int, hjzzlx: Int): PostRequest<String> {
        val jsonObject = JSONObject()
        if (roleid==50){
            jsonObject.put("newtype", 0)//当前页数，从1开始
        }
        if (roleid!=-1)
            jsonObject.put("qutype", roleid)//当前页数，从1开始
        jsonObject.put("limit", limit)//当前页数，从1开始
        jsonObject.put("page", page)//当前页数，从1开始
        if (code.equals("0")){
            jsonObject.put("code", "")//当前页数，从1开始  "["+code+"]"
        }else{
            jsonObject.put("code", ""+code+"")//当前页数，从1开始  "["+code+"]"
        }
        val split = date.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
//        jsonObject.put("date", date)//当前页数，从1开始  "["+code+"]"
        if (AppCache.getInstance().type == ApiConstants.LDRK_ZZF||AppCache.getInstance().type == ApiConstants.RJHJ_NY){
            jsonObject.put("hjzzlx", hjzzlx)//当前页数，从1开始  "["+code+"]"
        }
        if (no!=null&&no!=0)
            jsonObject.put("no", no)//当前页数，从1开始  "["+code+"]"
        if (hjzzsj!=0){
            jsonObject.put("hjzzsj", hjzzsj)//当前页数，从1开始  "["+code+"]"
        }
        if (zdwtCha!=-1){
            jsonObject.put("zdwt", zdwtCha)//当前页数，从1开始  "["+code+"]"
        }
//        jsonObject.put("status", 0)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIORSUPVSLIST).upJson(sss)
    }

    override fun getEnviorSupvsQueryPoint(point: String): PostRequest<String> {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<String>(ApiConstants.ENVIOR_SUPVS_QUERY_BY_POINT).upJson(sss)
    }

}