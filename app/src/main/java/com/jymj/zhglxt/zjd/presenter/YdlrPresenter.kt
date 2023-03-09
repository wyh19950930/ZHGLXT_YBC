package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.PointAndTypeEntity
import com.jymj.zhglxt.rjhj.bean.yl.ApplyCountEntity
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.YdlrContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject
import java.math.BigDecimal

class YdlrPresenter : YdlrContract.Companion.Presenter(){
    override fun getFanJianApplyList(sptype: Int,limit:Int,page:Int, code: String, date: String, name: String) {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
//        jsonObject.put("date", date)
        val split = date.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
        jsonObject.put("sptype", sptype)
        jsonObject.put("code", ""+code+"")
        jsonObject.put("limit", limit)//当前页数，从1开始
        jsonObject.put("page", page)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_LAND_LIST ).upJson(sss).execute(object ://APPLY_LIST1
                BaseNet<String>(){//BaseRespose<PageUtils<ApplyEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<ApplyEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<ApplyEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null){
                            mView.returnFanJianApplyList(json.data.list,sptype,json.data.total.toInt())
                        }else {
                            mView.showErrorTip("数据为空")
                            mView.stopLoading()
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        mView.stopLoading()
                    }
                }
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.message()!=null){
                    mView.showErrorTip(response?.message())
                }else{
                    mView.showErrorTip("请求失败")
                }
                mView.stopLoading()
            }
        })
    }

    override fun getFanJianTjList(date: String) {///*******************************************
        /*val httpParams = HttpParams()
        httpParams.put("date",date)*/
        var jsonObject = JSONObject()
//        jsonObject.put("date", date)
        val split = date.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_LAND_GET_SUMMARY).upJson(sss).execute(object ://APPLY_GETSUMMARY
                BaseNet<String>(){//BaseRespose<List<ZjdfjTjBean>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<ZjdfjTjBean>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ZjdfjTjBean>>?>() {}.type)

                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnFanJianTjList(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("查询为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("查询异常")//response?.exception?.message
                }else{
                    mView.showErrorTip("查询异常")
                }
            }

        })
    }


    override fun getApplyByPoint(point: String) {
        var jsonObject = JSONObject()
        jsonObject.put("point", point)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_LAND_GET_BY_POINT ).upJson(sss).execute(object ://APPLY_BY_POINT1
                BaseNet<String>(){//BaseRespose<List<ApplyLandEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<List<ApplyLandEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ApplyLandEntity>>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data!=null){
                                mView?.returnApplyByPoint(json.data)
                            }else{
                                mView?.showErrorTip("")
                            }
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("查询失败")
                    }
                }else{
                    mView?.showErrorTip("查询失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)
                            else
                                mView.showErrorTip("查询失败")
                        }else{
                            mView.showErrorTip("查询失败")
                        }
                    }else{
                        mView.showErrorTip("查询失败")
                    }
                }
//                mView.showErrorTip("网络错误")
            }
        })
    }
    override fun getApplyInfo(applyId: Int) {
        var jsonObject = JSONObject()//applyId
        jsonObject.put("id", applyId)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_LAND_INFO ).upJson(sss).execute(object ://APPLY_INFO1
                BaseNet<String>(){//BaseRespose<List<ApplyLandEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<List<ApplyLandEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ApplyLandEntity>>?>() {}.type)

                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data!=null){
                                mView?.returnApplyByPoint(json.data)
                            }/*else{
                                mView?.showErrorTip("")
                            }*/
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("查询失败")
                    }
                }else{
                    mView?.showErrorTip("查询失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)
                            else
                                mView.showErrorTip("查询失败")
                        }else{
                            mView.showErrorTip("查询失败")
                        }
                    }else{
                        mView.showErrorTip("查询失败")
                    }
                }
//                mView.showErrorTip("网络错误")
            }
        })
    }




    //翻建点查
    override fun getFanJian(point: String) {
//        mView.returnFanJian(point)
        val jsonObject = JSONObject()
        jsonObject.put("point",point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.YL_GET_RENOVATED).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<FjBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<FjBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<FjBean>?>() {}.type)

                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnFanJian(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("点位不存在")//response?.exception?.message
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

        })
    }
    //lastSelect,limit,page,cunCode,date,bh
    override fun getFjxcList(qutype:Int,limit:Int,page:Int,cunCode:String,jltime:String,bh:Int){//还需要修改
        val jsonObject = JSONObject()
        jsonObject.put("qutype", qutype)//当前页数，从1开始
        jsonObject.put("limit", limit)//当前页数，从1开始
        jsonObject.put("page", page)//当前页数，从1开始
        jsonObject.put("code", ""+cunCode+"")//当前页数，从1开始  "["+code+"]"
        if (!jltime.equals(""))
        jsonObject.put("jltime", jltime)//当前页数，从1开始  "["+code+"]"
        if (bh!=null&&bh!=0)
            jsonObject.put("bh", bh)//当前页数，从1开始  "["+code+"]"
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.RENOVATED_QUERY_LIST).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<PageUtils<Renovated>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<Renovated>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<Renovated>>?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null){
                            mView.returnFjxcList(json.data.list,qutype,json.data.total.toInt())
                        }else {
                            mView.showErrorTip("数据为空")
                            mView.stopLoading()
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        mView.stopLoading()
                    }
                }
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.message()!=null){
                    mView.showErrorTip(response?.message())
                }else{
                    mView.showErrorTip("请求失败")
                }
                mView.stopLoading()
            }
        })
    }

    //1  区  2镇  3 村  4 最小
    override fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, code:String) {//type  xmin  xmax  ymin  ymax
        val httpParams = HttpParams()
        httpParams.put("type",type.toInt())
        httpParams.put("xmax",xmax.toString())
        httpParams.put("xmin",xmin.toString())
        httpParams.put("ymin",ymin.toString())
        httpParams.put("ymax",ymax.toString())
        if (!code.equals("")&&!code.equals("110112")&&code.length==9)//BaseRespose<List<FwglJhBean>>
            httpParams.put("code",code.toString())
        val pointAndTypeEntity = PointAndTypeEntity()
        pointAndTypeEntity.type = type.toInt()
        pointAndTypeEntity.xmax = xmax
        pointAndTypeEntity.xmin = xmin
        pointAndTypeEntity.ymin = ymin
        pointAndTypeEntity.ymax = ymax
        if (!code.equals("")&&!code.equals("110112")&&code.length==9)
            pointAndTypeEntity.code = code//.upJson(Gson().toJson(pointAndTypeEntity))   .params(httpParams)   RENOVATED_GET_RENOVATED_BYXY
        val toJson = Gson().toJson(pointAndTypeEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_QUERY_COUNT).upJson(sss).execute(
                object : BaseNet<String>(){//BaseRespose<List<ApplyCountEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<ApplyCountEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ApplyCountEntity>>?>() {}.type)

                    if (json.code == 0){
                        if (json.data != null){
                            mView.returnEnviorsByxy(json.data,type,xmax,xmin,ymin,ymax)
                        }else {
                            mView.showErrorTip("数据为空")
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }
        })
    }

    override fun getSteps(qutype:Int,limit:Int,page:Int,cunCode:String,jltime:String,name:String){//还需要修改
        val jsonObject = JSONObject()
        jsonObject.put("qutype", qutype)//当前页数，从1开始
        jsonObject.put("limit", limit)//当前页数，从1开始
        jsonObject.put("page", page)//当前页数，从1开始
        jsonObject.put("code", ""+cunCode+"")//当前页数，从1开始  "["+code+"]"

        val split = jltime.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
//        if (bh!=null&&bh!=0)
            jsonObject.put("name", name)//当前页数，从1开始  "["+code+"]"
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)//RENOVATED_GET_STEPS
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLYLAND_QUERY_STATISTICAL).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<FjNumBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<FjNumBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<FjNumBean>?>() {}.type)

                    if (json.code==0){

                        if (json!!.data!=null){//cash.data.list,qutype,cash.data.total.toInt()
                            mView.returnSteps(json!!.data)
                        }else {
                            mView.showErrorTip("数据为空")
                            mView.stopLoading()
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        mView.stopLoading()
                    }
                }
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.message()!=null){
                    mView.showErrorTip(response?.message())
                }else{
                    mView.showErrorTip("请求失败")
                }
                mView.stopLoading()
            }
        })
    }

    //翻建点查
    override fun getFanJianZb(date: String) {
        val toJson = JSONObject()
        toJson.put("date",date)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.RENOVATED_GET_STATISTICAL).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<List<StatisticalEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){

                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<StatisticalEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<StatisticalEntity>>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnFanJianZb(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("查询为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("查询异常")//response?.exception?.message
                }else{
                    mView.showErrorTip("查询异常")
                }
            }

        })
    }
}