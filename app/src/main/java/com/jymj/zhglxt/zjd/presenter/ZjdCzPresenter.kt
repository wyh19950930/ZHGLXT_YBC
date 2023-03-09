package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.ZjdCzContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by setsuna on 18-3-23.
 */
class ZjdCzPresenter : ZjdCzContract.Companion.Presenter() {

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
                            mView.changeUser()
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

    //热力图  code  type  xmax  xmin  ymax  ymin
    override fun getRlt(code:String, type: Int, xmax:BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal) {
        mModel.getRlt(code,type,xmax.setScale(5,RoundingMode.HALF_UP),xmin.setScale(5,RoundingMode.HALF_UP)
                ,ymax.setScale(5,RoundingMode.HALF_UP),ymin.setScale(5,RoundingMode.HALF_UP)).execute(object : BaseNet<BaseRespose<List<XzqInfoEntity>>>() {
            override fun onStart(request: Request<BaseRespose<List<XzqInfoEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<List<XzqInfoEntity>>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnRlt(body.data,type)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
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

            override fun onError(response: Response<BaseRespose<List<XzqInfoEntity>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //扫描二维码查找院落  qrcode
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
                            mView.changeUser()
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
    //流动人员列表   code:登陆账号所关联的code  name:人员名称   xzqmc:村名
    override fun getCxldry(code:String,name:String,xzqmc:String) {
        mModel.getCxldry(code,name,xzqmc).execute(object : BaseNet<BaseRespose<PageUtils<YlFolwEntity>>>() {
            override fun onStart(request: Request<BaseRespose<PageUtils<YlFolwEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<YlFolwEntity>>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnCxldry(body.data.list,body.data.total)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
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

            override fun onError(response: Response<BaseRespose<PageUtils<YlFolwEntity>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //查询村庄情况列表  lgyname:流管员名称   xzqmc:村名
    override fun getCxczqk(lgyname:String,xzqmc:String) {
        mModel.getCxczqk(lgyname,xzqmc).execute(object : BaseNet<BaseRespose<PageUtils<XzqInfoEntity>>>() {
            override fun onStart(request: Request<BaseRespose<PageUtils<XzqInfoEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<XzqInfoEntity>>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnCxczqk(body.data.list)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
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

            override fun onError(response: Response<BaseRespose<PageUtils<XzqInfoEntity>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //点查院落信息
    override fun getDcylxx(point:String, objectId: Long?) {
        mModel.getDcylxx(point, objectId).execute(object : BaseNet<BaseRespose<YlFolwEntity>>() {
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
                            mView.returnDcylxx(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
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
                mView.showErrorTip("")//response?.exception?.message
            }

        })
    }
    //查询首页基本情况统计
    override fun getJbqktj() {
        mModel.getJbqktj(AppCache.getInstance().code).execute(object : BaseNet<BaseRespose<FirstTjBean>>() {
            override fun onStart(request: Request<BaseRespose<FirstTjBean>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<FirstTjBean>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnJbqktj(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
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

            override fun onError(response: Response<BaseRespose<FirstTjBean>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    //查询首页行业类型统计
    override fun getHylxtj() {//code: String
        mModel.getHylxtj(AppCache.getInstance().code).execute(object : BaseNet<BaseRespose<List<IndustryEntity>>>() {
            override fun onStart(request: Request<BaseRespose<List<IndustryEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<List<IndustryEntity>>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.changeUser(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip(body.msg)
                        }
                    }
                }
//                mView.changeUser(response?.body()?.msg!!)
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<List<IndustryEntity>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })

    }

    override fun getUserRequest(uName: String, uPwd: String, deviceId: String) {
        val params = mModel.login(uName, uPwd, deviceId)
        //LoginBean
        OkGo.post<LoginBean>(ApiConstants.LOGIN_URL).params(params).execute(object : BaseNet<LoginBean>() {
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
                                //queryByCunEnviorSupvs
                                var sss="{}"
                                /*OkGo.post<String>(ApiConstants.queryByCunEnviorSupvs).upJson(sss).execute(object : BaseNet<String>(){
                                    override fun onSuccess(response: Response<String>?) {
                                        ToastUtils.showShort("")
                                    }
                                })*/
                            }
                        }
                        505 -> {
                            mView.showErrorTip(loginBean.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip(loginBean.msg)
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
            }
//java.net.UnknownServiceException: CLEARTEXT communication to 192.168.3.14 not permitted by network security policy
            override fun onFinish() {
                super.onFinish()
                mView.showLoading("")
            }
            override fun onError(response: Response<LoginBean>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }
        })
    }

}