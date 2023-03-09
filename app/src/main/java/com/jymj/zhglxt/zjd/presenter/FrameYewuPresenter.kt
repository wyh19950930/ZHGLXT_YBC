package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.contract.FrameYewuContract
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class FrameYewuPresenter : FrameYewuContract.Companion.Presenter() {

    override fun getSmewmcyl(qrcode:String) {
        mModel.getSmewmcyl(qrcode).execute(object : BaseNet<BaseRespose<YlFolwEntity>>() {
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
                            mView.returnSmewmcyl(body.data)
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

}