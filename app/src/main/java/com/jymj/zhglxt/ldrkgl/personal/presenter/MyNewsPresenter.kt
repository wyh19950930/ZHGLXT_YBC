package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.login.contract.MyNewsContract
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class MyNewsPresenter : MyNewsContract.Companion.Presenter() {

    //消息列表
    override fun getMessageList(limit: Int, page: Int) {//returnMessageList

        mModel.getMessageList(limit, page).execute(object : BaseNet<BaseRespose<PageUtils<FlowMassageEntity>>>() {
            override fun onStart(request: Request<BaseRespose<PageUtils<FlowMassageEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<FlowMassageEntity>>>?) {
                val body = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnMessageList(body.data.list)
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

            override fun onError(response: Response<BaseRespose<PageUtils<FlowMassageEntity>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //发布消息/处理or修改消息
    override fun getAddMessage(flowMassageEntity: FlowMassageEntity) {
        mModel.getAddMessage(flowMassageEntity).execute(object : BaseNet<BaseRespose<String>>() {
            override fun onStart(request: Request<BaseRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<String>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnAddMessage("发布成功")
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

            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
}
