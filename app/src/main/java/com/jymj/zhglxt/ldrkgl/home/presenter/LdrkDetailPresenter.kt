package com.jymj.zhglxt.login.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.bean.UploadSuccess
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.contract.LdrkDetailContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class LdrkDetailPresenter : LdrkDetailContract.Companion.Presenter() {
    override fun getDcylxx(point: String, objectid: Long) {

        mModel.getDcylxx(point, objectid).execute(
                object : BaseNet<String>() {//BaseRespose<YlFolwEntity>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<YlFolwEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YlFolwEntity>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnDcylxx(json.data)
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


    //扫描二维码和宅基地进行绑定  objectid:院落id  qrcode:二维码code
    override fun getEwmsmZjdBd(objectid:Long,qrcode:String) {
        mModel.getEwmsmZjdBd(objectid,qrcode).execute(object : BaseNet<String>() {//BaseRespose<YlFolwEntity>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<YlFolwEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YlFolwEntity>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnEwmsmZjdBd(json.data)
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
    //流动人员列表   ylId
    override fun getCxldry(ylId:Long) {
        mModel.getCxldry(ylId).execute(object : BaseNet<String>() {//BaseRespose<PageUtils<FlowInfoEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<FlowInfoEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<FlowInfoEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnCxldry(json.data.list)
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