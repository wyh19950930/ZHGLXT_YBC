package com.jymj.zhglxt.login.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.SendMessageContract
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class SendMessagePresenter : SendMessageContract.Companion.Presenter() {
    //扫描二维码和宅基地进行绑定  objectid:院落id  qrcode:二维码code
    override fun getEwmsmZjdBd(objectid:Long,qrcode:String) {
        mModel.getEwmsmZjdBd(objectid,qrcode).execute(object : BaseNet<BaseRespose<YlFolwEntity>>() {
            override fun onStart(request: Request<BaseRespose<YlFolwEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<YlFolwEntity>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnEwmsmZjdBd(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(body.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<YlFolwEntity>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //获取流管员列表
    override fun getHqlgyList() {
        mModel.getHqlgyList().execute(object : BaseNet<BaseRespose<List<User>>>() {
            override fun onStart(request: Request<BaseRespose<List<User>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<List<User>>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnHqlgyList(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(body.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<List<User>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //发布消息/处理or修改消息
    override fun getAddMessage(flowMassageEntity: FlowMassageEntity) {
        mModel.getAddMessage(flowMassageEntity).execute(object : BaseNet<String>() {//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(body)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnAddMessage("发布成功")
                        }
                        500 -> {
                            mView.showErrorTip("村庄无流管员或其他问题")
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