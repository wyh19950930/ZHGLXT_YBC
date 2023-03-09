package com.jymj.zhglxt.login.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.login.contract.ZjdYztContract
import com.jymj.zhglxt.rjhj.bean.yl.YztPointEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.AnalysisEnty
import com.jymj.zhglxt.zjd.bean.FrameJcxxBean
import com.jymj.zhglxt.zjd.bean.YeWuFrameBean
import com.jymj.zhglxt.zjd.bean.yzt.cs.CSEntity
import com.jymj.zhglxt.zjd.bean.yzt.cs.XzDateEntity
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
class ZjdYztPresenter : ZjdYztContract.Companion.Presenter() {
    override fun getCS(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String) {
        mModel.getCS(points, codeList, zch, jzgm, qwbcj, dlzz75n,dlzz75w,zzl,khf).execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView?.showLoading("正在努力加载")
            }
            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                                T fromJson = new Gson().fromJson(s, T);
                        val json: BaseRespose<CSEntity> = Gson().fromJson(response.body(), object : TypeToken<BaseRespose<CSEntity>?>() {}.type)
                        if (json.data!=null){
                            mView?.onCS(json.data)
                        }else{
                            mView?.showErrorTip("当前区域无可用数据")
                        }
                    }else{
                        mView?.showErrorTip("当前区域无可用数据")
                    }
                }else{
                    mView?.showErrorTip("当前区域无可用数据")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView?.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }
        })
    }

    override fun getBaseDataCe(points: String, codeList: String, zch: String, jzgm: String, qwbcj: String, dlzz75n: String, dlzz75w: String, zzl: String, khf: String) {

        mModel.getBaseDataCe(points, codeList,zch,jzgm,qwbcj,dlzz75n,dlzz75w,zzl,khf).execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView?.showLoading("正在努力加载")
            }

            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                                T fromJson = new Gson().fromJson(s, T);
                        val json: BaseRespose<XzDateEntity> = Gson().fromJson(response.body(), object : TypeToken<BaseRespose<XzDateEntity>?>() {}.type)
                        if (json.data!=null){
                            mView?.onBaseDataCe(json.data)
                        }else{
                            mView?.showErrorTip("当前区域无可用数据")
                        }
                    }else{
                        mView?.showErrorTip("当前区域无可用数据")
                    }
                }else{
                    mView?.showErrorTip("当前区域无可用数据")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView?.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }
        })
    }

    override fun getYztList(page: Int, limit: Int, type: Int, projectName: String) {

        mModel.getYztList(page,limit,type,projectName).execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<String>?) {
                if (response != null) {
                    if (response.body() != null) {
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<PageUtils<AnalysisEnty>> = Gson().fromJson(response.body(), object : TypeToken<BaseRespose<PageUtils<AnalysisEnty>>?>() {}.type)
                        if (json.code == 0) {//&& response.body().data.ylEntity!=null
                            mView?.onYztList(json.data.list)
                        } else {
                            mView?.showErrorTip("添加失败")
                        }
                    } else {
                        mView?.showErrorTip("添加失败")
                    }
                } else {
                    mView?.showErrorTip("添加失败")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }
        })
    }

    override fun getYewuFrame(point: String) {

        mModel.getYewuFrame(point).execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView?.showLoading("正在加载")
            }
            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        var json = Gson().fromJson(response.body(), YeWuFrameBean::class.java)
                        /*val json: BaseRespose<YLPointEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YLPointEntity>?>() {}.type)*/
                        if (json.data!=null){
                            mView?.onYewuFrame(json.data)
                        }else{
//                    view?.onYewuFrame(json.data)
                            mView?.showErrorTip("当前区域无可用数据")
                        }
                    }else{
                        // view?.showErrorTip("当前区域无可用数据")
                    }
                }else{
                    // view?.showErrorTip("当前区域无可用数据")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView?.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }
        })
    }

    override fun getFrameJcxx(string: String) {
        mModel.getFrameJcxx(string).execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView!!.showLoading("正在加载")
            }

            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())

//                val toJson = Gson().toJson(decrypt)
//                val json: BaseRespose<String> = Gson().fromJson(decrypt, BaseRespose<String>::class.java)
                        val json: BaseRespose<FrameJcxxBean.DataBean> = Gson().fromJson(response.body(), object : TypeToken<BaseRespose<FrameJcxxBean.DataBean>?>() {}.type)

                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
//                    val json1: FrameJcxxBean.DataBean = Gson().fromJson(json.data, FrameJcxxBean.DataBean::class.java)
                            mView?.onFrameJcxx(json.data)
                        }else{
                            mView?.showErrorTip("暂无数据")
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView!!.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
            }
        })
    }

    override fun getYztPoint(point: String) {
        mModel.getYztPoint(point).execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView?.showLoading("正在加载")
            }
            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
//                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                                T fromJson = new Gson().fromJson(s, T);
                        val json: BaseRespose<YztPointEntity> = Gson().fromJson(response.body(), object : TypeToken<BaseRespose<YztPointEntity>?>() {}.type)
                        if (json.data!=null ){//&& json.data.ylEntity!=null
                            mView?.onYztPoint(json.data)
                        }else{
//                    view?.onYztPoint(json.data)
                            mView?.showErrorTip("当前区域无可用数据")
                        }
                    }else{
                        mView?.showErrorTip("当前区域无可用数据")
                    }
                }else{
                    mView?.showErrorTip("当前区域无可用数据")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView?.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip(response?.exception?.message)
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

}