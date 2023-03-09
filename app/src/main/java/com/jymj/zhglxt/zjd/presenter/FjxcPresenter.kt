package com.jymj.zhglxt.zjd.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.zjd.bean.fj.*
import com.jymj.zhglxt.zjd.contract.FjxcContract
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.PointAndTypeEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject
import java.math.BigDecimal

class FjxcPresenter : FjxcContract.Companion.Presenter(){
    //翻建点查
    override fun getFanJian(point: String) {
//        mView.returnFanJian(point)
        val httpParams = JSONObject()
        httpParams.put("point",point)
        OkGo.post<BaseRespose<FjBean>>(ApiConstants.YL_GET_RENOVATED).upJson(httpParams).execute(object :
                BaseNet<BaseRespose<FjBean>>(){
            override fun onStart(request: Request<BaseRespose<FjBean>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<FjBean>>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.getCode()==0){
                        if (cash.data!=null){
                            mView.returnFanJian(cash.data)
                        }else{
                            mView.showErrorTip(cash.getMsg())
                        }
                    }else{
                        mView.showErrorTip(cash.getMsg())
                    }
                }else{
                    mView.showErrorTip("点位不存在")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<FjBean>>?) {
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

        val split = jltime.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
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
            pointAndTypeEntity.code = code//.upJson(Gson().toJson(pointAndTypeEntity))   .params(httpParams)
        OkGo.post<BaseRespose<List<FwglJhBean>>>(ApiConstants.RENOVATED_GET_RENOVATED_BYXY).upJson(Gson().toJson(pointAndTypeEntity)).execute(object : BaseNet<BaseRespose<List<FwglJhBean>>>(){
            override fun onStart(request: Request<BaseRespose<List<FwglJhBean>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<BaseRespose<List<FwglJhBean>>>?) {
                val body = response?.body()
                if (body != null){
                    if (body.code == 0){
                        if (body.data != null){
                            mView.returnEnviorsByxy(body.data,type,xmax,xmin,ymin,ymax)
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

            override fun onError(response: Response<BaseRespose<List<FwglJhBean>>>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }
        })
    }
    override fun getSteps(qutype:Int,limit:Int,page:Int,cunCode:String,jltime:String,bh:Int){//还需要修改
        val jsonObject = JSONObject()
//        jsonObject.put("qutype", qutype)//当前页数，从1开始
//        jsonObject.put("limit", limit)//当前页数，从1开始
//        jsonObject.put("page", page)//当前页数，从1开始
        jsonObject.put("code", ""+cunCode+"")//当前页数，从1开始  "["+code+"]"
        val split = jltime.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
        if (bh!=null&&bh!=0)
            jsonObject.put("bh", bh)//当前页数，从1开始  "["+code+"]"
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.RENOVATED_GET_STEPS).upJson(sss).execute(object :
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
                        if (json.data!=null){//cash.data.list,qutype,cash.data.total.toInt()
                            mView.returnSteps(json!!.data)
                        }else {
                            mView.returnSteps(FjNumBean())
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
        val jsonObject = JSONObject()
//        jsonObject.put("date",date)
        val split = date.split(" - ")
        if (split.size>1){//stDate  enDate
            jsonObject.put("stDate", split[0])//当前页数，从1开始  "["+code+"]"
            jsonObject.put("enDate", split[1])//当前页数，从1开始  "["+code+"]"
        }
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
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