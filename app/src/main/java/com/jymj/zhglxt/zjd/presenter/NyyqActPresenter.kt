package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R.drawable.point
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.ApplyFileEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.cygl.NydHtEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.NydZjEntity
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqActContract
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject
import java.util.ArrayList

class NyyqActPresenter : NyyqActContract.Companion.Presenter(){

    //添加修改租金信息
    override fun getNyyqDetailUploadZj(entity: NydZjEntity) {
        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(entity))
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.NYD_SAVEZj).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<NydZjEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<NydZjEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnNyyqDetailUploadZj(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
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

    //添加修改合同信息
    override fun getNyyqDetailUploadHt(entity: NydHtEntity) {

        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(entity))
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.NYD_SAVEORUPDATE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<NydHtEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<NydHtEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnNyyqDetailUploadHt(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
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

    //农用地详情
    override fun getNyyqDetail(id: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.NYD_GETNydINFO).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<YztNyyqEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YztNyyqEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnNyyqDetail(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
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

    //农用地详情合同文件删除
    override fun getFileDelete(ids: Int) {
//        val idss = "{\"ids\":[" + ids.toInt() + "]}"
        val idss = "[" + ids.toInt() + "]"

        val encrypt = AesEncryptUtils.encrypt(idss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.NYD_DELETEFILE).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<String>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnFileDelete("删除成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("删除失败")
                    }
                }else{
                    mView?.showErrorTip("删除失败")
                }
                mView!!.stopLoading()
                /*val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            if (cash.data.list.size>0){
                                mView.returnRjhjjcList(cash.data.list)
                            }else{
                                mView.showErrorTip("数据加载出错")
                            }
                        }else {
                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                    }
                }*/

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("网络错误")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    //农用地详情租金删除
    override fun getNyyqZjDelete(ids: Int) {
//        val idss = "{\"ids\":[" + ids.toInt() + "]}"
        val idss = "[" + ids.toInt() + "]"

        val encrypt = AesEncryptUtils.encrypt(idss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.NYD_DELETEZj).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<String>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnNyyqZjDelete("删除成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("删除失败")
                    }
                }else{
                    mView?.showErrorTip("删除失败")
                }
                mView!!.stopLoading()
                /*val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            if (cash.data.list.size>0){
                                mView.returnRjhjjcList(cash.data.list)
                            }else{
                                mView.showErrorTip("数据加载出错")
                            }
                        }else {
                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                    }
                }*/

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("网络错误")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
}