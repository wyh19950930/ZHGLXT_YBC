package com.jymj.zhglxt.rjhj.presenter

import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.contract.RjhjDwckContract
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class RjhjDwckPresenter : RjhjDwckContract.Companion.Presenter() {
    //未处理
    override fun getWclCount() {
        mModel.getWclCount().execute(object : BaseNet<BaseRespose<Int>>() {
            override fun onStart(request: Request<BaseRespose<Int>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<Int>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnWclCount(body.data)
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
//                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<Int>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })

    }

}