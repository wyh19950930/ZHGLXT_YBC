package com.jymj.zhglxt.login.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.FirstTjBean
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.login.contract.CzlbContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class CzlbPresenter : CzlbContract.Companion.Presenter() {
    //查询村庄情况列表  lgyname:流管员名称   xzqmc:村名
    override fun getCxczqk(lgyname:String,xzqmc:String) {
        mModel.getCxczqk(lgyname,xzqmc).execute(object : BaseNet<String>() {//BaseRespose<PageUtils<XzqInfoEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<XzqInfoEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<XzqInfoEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnCxczqk(json.data.list)
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
//                Log.e("------",response?.exception?.message)
            }

        })
    }
    //查询首页基本情况统计
    override fun getJbqktj() {
        mModel.getJbqktj(AppCache.getInstance().code).execute(object : BaseNet<String>() {//BaseRespose<FirstTjBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<FirstTjBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<FirstTjBean>?>() {}.type)

                    when (json.code) {
                        0 -> {
                            mView.returnJbqktj(json.data)
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