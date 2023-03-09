package com.jymj.zhglxt.login.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.bean.UploadSuccess
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.login.contract.XxcjContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class XxcjPresenter : XxcjContract.Companion.Presenter() {


    //流出流动人员
    override fun getLcldry(ids: List<Int>) {
        mModel.getLcldry(ids).execute(object : BaseNet<String>() {//UploadSuccess
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: UploadSuccess = Gson().fromJson(decrypt, object : TypeToken<UploadSuccess?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnLcldry("流出成功")
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
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
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getFlowUpload(flowInfoEntity: FlowInfoEntity) {
        mModel.getFlowUpload(flowInfoEntity).execute(object :BaseNet<String>(){//UploadSuccess
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在请求。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (body!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: UploadSuccess = Gson().fromJson(decrypt, object : TypeToken<UploadSuccess?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnFlowUpload("提交成功")
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
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
                mView.showErrorTip(response?.exception?.message)
            }
        })
    }

    //通过身份证号查询该用户是否上传过信息
    override fun getFlowGetInfo(idCard: String,ylid:Long) {
        mModel.getFlowGetInfo(idCard,ylid).execute(object : BaseNet<String>() {//BaseRespose<FlowInfoEntity>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在请求。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<FlowInfoEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<FlowInfoEntity>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnFlowGetInfo(json.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
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
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

}