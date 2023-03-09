package com.jymj.zhglxt.login.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.LoginContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.util.OkGoUtil
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class LoginPresenter : LoginContract.Companion.Presenter() {
    override fun changeUser(username: String, password: String, newPassword: String, deviceCode: String) {
        mModel.changeUser(username, password, newPassword, deviceCode).execute(object : BaseNet<BaseRespose<String>>() {
            override fun onStart(request: Request<BaseRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<String>>?) {
                mView.changeUser(response?.body()?.msg!!)
            }

            override fun onFinish() {
                super.onFinish()
                mView.showLoading("")
            }

            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getUserRequest(uName: String, uPwd: String, deviceId: String) {
//        val params = mModel.login(uName, uPwd, deviceId)
        //LoginBean
        /*val jsonObject = JSONObject()//LOGIN_URL
        jsonObject.put("username", uName)
        jsonObject.put("password", uPwd)//84b8adbdf3963643
        jsonObject.put("deviceCode", "866174010481386")//deviceIddeviceId
        OkGoUtil.put(ApiConstants.LOGIN_URL,jsonObject,object :OkGoUtil.OnOKHttp<LoginBean>{
            override fun onStart() {
                mView.showLoading("正在登录。。。")
            }

            override fun onSuccess(response: LoginBean) {

                if (response != null) {

                    when (response.code) {
                        0 -> {
                            AppCache.getInstance().token=response.token
                            val user = response.user
                            if (user != null) {
                                mView.returnUser(user)
                                //queryByCunEnviorSupvs
                                var sss="{}"
                                *//*OkGo.post<String>(ApiConstants.queryByCunEnviorSupvs).upJson(sss).execute(object : BaseNet<String>(){
                                    override fun onSuccess(response: Response<String>?) {
                                        ToastUtils.showShort("")
                                    }
                                })*//*
                            }
                        }
                        505 -> {
                            mView.showErrorTip(response.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip("${response.code}")//json.msg
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
                mView.stopLoading()
            }
            override fun onFinish() {
                mView.stopLoading()
            }

            override fun onError(response: String?) {
                mView.showErrorTip("网络错误")
                mView.stopLoading()
            }

        })*/
        mModel.login(uName, uPwd, deviceId).execute(object : BaseNet<LoginBean>() {//LoginBean
            override fun onStart(request: Request<LoginBean, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }

            override fun onSuccess(response: Response<LoginBean>?) {
                val loginBean = response?.body()
                if (loginBean != null) {

                    when (loginBean.code) {
                        0 -> {
                            AppCache.getInstance().token=loginBean.token
                            val user = loginBean.user
                            if (user != null) {
                                mView.returnUser(user)
                           }
                        }
                        505 -> {
                            mView.showErrorTip(loginBean.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip("${loginBean.code}")//json.msg
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
                mView.stopLoading()
            }
            //java.net.UnknownServiceException: CLEARTEXT communication to 192.168.3.14 not permitted by network security policy
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<LoginBean>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
                mView.stopLoading()
            }

        })

    }

}