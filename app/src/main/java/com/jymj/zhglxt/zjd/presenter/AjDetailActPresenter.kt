package com.jymj.zhglxt.zjd.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.contract.FjxcActContract
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEntity
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEnumBean
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionFile
import com.jymj.zhglxt.zjd.bean.jsjb.SysDepartEntity
import com.jymj.zhglxt.zjd.contract.AjDetailActContract
import com.jymj.zhglxt.zjd.contract.AjlbActContract
import com.jymj.zhglxt.zjd.contract.AjlbSearchActContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class AjDetailActPresenter : AjDetailActContract.Companion.Presenter(){

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
    //详情
    override fun getJsjbInfo(id:Long) {
//        mView.returnFanJian(point)
        val httpParams = JSONObject()
        httpParams.put("id",id)
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.OPINION_GET_INFO).upJson(sss).execute(object :
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
                    val json: BaseRespose<OpinionEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<OpinionEntity>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnJsjbInfo(json.data)
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
    //文件删除
    override fun getJsjbDeleteFile(id:Long,opinionFiles:ArrayList<OpinionFile>,opinionFile:OpinionFile) {
//        mView.returnFanJian(point)
        /*val httpParams = JSONObject()
        httpParams.put("id",id)*/
        val encrypt = AesEncryptUtils.encrypt("[$id]".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.OPINION_DELETE_FILE_BYID).upJson(sss).execute(object :
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
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.getCode()==0){
                        mView.returnJsjbDeleteFile(json.getMsg(),opinionFiles,opinionFile)
                       /* if (json.data!=null){
                            mView.returnJsjbDeleteFile(json.data,position)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }*/
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
    //保存 添加
    override fun getJsjbSave(opinionEntity:OpinionEntity) {
        /*val httpParams = JSONObject()
        httpParams.put("id",id)*/
        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(opinionEntity))//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.OPINION_SAVE).upJson(sss).execute(object :
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
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.getCode()==0){
                        mView.returnJsjbSave(json.getMsg())
                        /*if (json.data!=null){
                            mView.returnJsjbSave(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }*/
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
    //修改步骤 工单
    override fun getJsjbUpdate(opinionEntity:OpinionEntity) {
        /*val httpParams = JSONObject()
        httpParams.put("id",id)*/
        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(opinionEntity))//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.OPINION_UPDATE).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<Renovated>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.getCode()==0){
                        mView.returnJsjbUpdate(json.getMsg())
                        /*if (json.data!=null){
                            mView.returnJsjbUpdate(json.data)
                        }else{
                            mView.showErrorTip(json.getMsg())
                        }*/
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
    //获取部门
    override fun getOpinionGetDepart() {
        val httpParams = JSONObject()
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.OPINION_GET_DEPART).upJson(sss).execute(object :
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
                    val json: BaseRespose<List<SysDepartEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<SysDepartEntity>>?>() {}.type)
                    if (json.getCode()==0){
                        if (json.data!=null){
                            mView.returnOpinionGetDepart(json.data)
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