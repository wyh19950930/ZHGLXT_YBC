package com.jymj.zhglxt.zjd.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEntity
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEnumBean
import com.jymj.zhglxt.zjd.bean.jsjb.SysDepartEntity
import com.jymj.zhglxt.zjd.bean.yzt.TjfxBean
import com.jymj.zhglxt.zjd.bean.yzt.TjfxDetailBean
import com.jymj.zhglxt.zjd.bean.yzt.layerListBean
import com.jymj.zhglxt.zjd.contract.*
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONArray
import org.json.JSONObject

class TjfxActPresenter : TjfxActContract.Companion.Presenter(){

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
    override fun getSysXzqQueryXzqList(code:String,type:Int) {
//        mView.returnFanJian(point)
        val httpParams1 = JSONObject()
        httpParams1.put("code",code)
        httpParams1.put("type",type)
        val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).upJson(sss).execute(object :
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
                            mView.returnSysXzqQueryXzqList(json.data)
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
    override fun getSysXzqList() {//获取图层列表
//        mView.returnFanJian(point)
        val httpParams1 = JSONObject()
        httpParams1.put("page",1)
        httpParams1.put("limit",1000)
        httpParams1.put("order","asc")
        val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.SYS_XZQ_LIST).upJson(sss).execute(object :
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
                    val json: BaseRespose<PageUtils<layerListBean>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<layerListBean>>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnSysXzqList(json.data.list)
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
    override fun getTjfx(cunCode:String,tcList:ArrayList<Int>) {
//        mView.returnFanJian(point)
        /*val httpParams1 = JSONObject()
        httpParams1.put("code",cunCode)
        httpParams1.put("maps",tcList)
        val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())*///"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        val tjfxBean = TjfxBean(cunCode, tcList)
        var sss = "{\"requestData\":\"" + Gson().toJson(tjfxBean) + "\"}"
        OkGo.post<String>(ApiConstants.POINT_GET_BYMAP).upJson(sss).execute(object :
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
                    val json: BaseRespose<TjfxDetailBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<TjfxDetailBean>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnTjfx(json.data)
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

}