package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.contract.FjxcActContract
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.jsjb.LightBean
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEntity
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEnumBean
import com.jymj.zhglxt.zjd.contract.AjlbActContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposePage
import org.json.JSONObject

class AjlbActPresenter : AjlbActContract.Companion.Presenter(){

    //翻建点查
    override fun getFanJianDetail(id:Long) {
//        mView.returnFanJian(point)
        val httpParams = JSONObject()
        httpParams.put("id",id)
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.RENOVATED_QUERY_INFO).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<Renovated> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<Renovated>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnFanJianDetail(json.data)
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
    //
    override fun getEnumGetOpinion() {
//        mView.returnFanJian(point)
        val httpParams = JSONObject()
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENUM_GET_OPINION_ENUM).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<OpinionEnumBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<OpinionEnumBean>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnEnumGetOpinion(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("请求出错")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("请求出错")//response?.exception?.message
                }else{
                    mView.showErrorTip("请求出错")
                }
            }

        })
    }
    //
    override fun getOpinionGetList(page:Int,limit:Int,title:String,qutype:Int) {
        val httpParams = JSONObject()
        httpParams.put("page",page)
        httpParams.put("limit",limit)
        httpParams.put("title",title)
        if (qutype!=-1){
            if (qutype==200){
                httpParams.put("beyond",qutype)
            }else{
                httpParams.put("qutype",qutype)
            }
        }
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.OPINION_GET_LIST).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<OpinionEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<OpinionEntity>>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnOpinionGetList(json.data.list)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("请求失败")//response?.exception?.message
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

        })
    }
    //
    override fun getLightGetList() {//page:Int,limit:Int

        val httpParams = JSONObject()
        httpParams.put("page",1)
        httpParams.put("limit",1000)
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.LIGHT_GET_LIST).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<LightBean>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<LightBean>>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnLightGetList(json.data.list)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView.showErrorTip(json.getMsg())
                    }
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    mView.showErrorTip("请求失败")//response?.exception?.message
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

        })
    }

    override fun getSysXzqList(code:String,type:Int) {
//        mView.returnFanJian(point)
        val httpParams1 = JSONObject()
        httpParams1.put("code",code)
        httpParams1.put("type",type)
        val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        /*OkGo.post<String>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<ArrayList<SysXzqEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<ArrayList<SysXzqEntity>>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnSysXzqList(json.data)
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

        })*/
    }

}