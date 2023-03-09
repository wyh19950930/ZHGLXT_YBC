package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.rjhj.bean.yl.ApplyFileEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.contract.ZjdfjglActContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import java.util.ArrayList

class ZjdfjglActPresenter : ZjdfjglActContract.Companion.Presenter(){

    override fun getApplyByPoint(point: String) {
        var httpParams = HttpParams()
        httpParams.put("point", point)

        OkGo.post<BaseRespose<ApplyEntity>>(ApiConstants.APPLY_BY_POINT1).params(httpParams).execute(object ://
                BaseNet<BaseRespose<ApplyEntity>>(){
            override fun onStart(request: Request<BaseRespose<ApplyEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<ApplyEntity>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnApplyByPoint(body.data)

                        }else{
                            mView?.showErrorTip(body.getMsg())
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

            override fun onError(response: Response<BaseRespose<ApplyEntity>>?) {
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
    override fun getApplySave(applyEntity: ApplyEntity) {
//        var httpParams = HttpParams()
//        httpParams.put("point", point)
        val toJson = Gson().toJson(applyEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_SAVE1).upJson(sss).execute(object ://
                BaseNet<String>(){
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
                            mView?.returnApplySave("添加成功")

                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("添加失败")
                    }
                }else{
                    mView?.showErrorTip("添加失败")
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
                                mView.showErrorTip("添加失败")
                        }else{
                            mView.showErrorTip("添加失败")
                        }

                    }else{
                        mView.showErrorTip("添加失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getApplyUpdate(applyEntity: ApplyEntity) {
        val toJson = Gson().toJson(applyEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_UPDATE1).upJson(sss).execute(object ://
                BaseNet<String>(){
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
                            mView?.returnApplyUpdate("修改成功")

                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("修改失败")
                    }
                }else{
                    mView?.showErrorTip("修改失败")
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
                                mView.showErrorTip("修改失败")
                        }else{
                            mView.showErrorTip("修改失败")
                        }

                    }else{
                        mView.showErrorTip("修改失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getApplyDeleteHuji(id: Int,position: Int) {//户籍删除

        val encrypt = AesEncryptUtils.encrypt("[${id}]".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_DELETE_HUJI).upJson(sss).execute(object ://
                BaseNet<String>(){
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
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null  body.data
                            mView?.returnApplyDeleteHuji("删除成功",position)

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
                                mView.showErrorTip("删除失败")
                        }else{
                            mView.showErrorTip("删除失败")
                        }

                    }else{
                        mView.showErrorTip("删除失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getApplyDeleteFjfw(id: Int,position: Int) {//翻建房屋

        val encrypt = AesEncryptUtils.encrypt("[${id}]".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_DELETE_FJFW).upJson(sss).execute(object ://
                BaseNet<String>(){
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
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null  body.data
                            mView?.returnApplyDeleteFjfw("删除成功",position)

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
                                mView.showErrorTip("删除失败")
                        }else{
                            mView.showErrorTip("删除失败")
                        }

                    }else{
                        mView.showErrorTip("删除失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getApplyFileDelete(id: Int, applyEntityList: ArrayList<ApplyFileEntity>, position: Int) {

        val encrypt = AesEncryptUtils.encrypt("[${id}]".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_FILE_DELETE_FILE).upJson(sss).execute(object ://
                BaseNet<String>(){
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
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null  body.data
                            mView?.returnApplyFileDelete("删除成功",applyEntityList,position)

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
                                mView.showErrorTip("删除失败")
                        }else{
                            mView.showErrorTip("删除失败")
                        }

                    }else{
                        mView.showErrorTip("删除失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getApplyDelete(id: Int) {

        val encrypt = AesEncryptUtils.encrypt("[${id}]".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.APPLY_DELETE).upJson(sss).execute(object ://
                BaseNet<String>(){
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
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null  body.data
                            mView?.returnApplyDelete("删除成功")

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
                                mView.showErrorTip("删除失败")
                        }else{
                            mView.showErrorTip("删除失败")
                        }

                    }else{
                        mView.showErrorTip("删除失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

}