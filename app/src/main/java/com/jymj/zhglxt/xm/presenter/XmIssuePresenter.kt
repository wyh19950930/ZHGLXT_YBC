package com.jymj.zhglxt.xm.presenter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.bean.CjVO
import com.jymj.zhglxt.xm.contract.XmIssueContract
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean
import com.jymj.zhglxt.zjd.contract.AddUserContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposeUser
import com.setsuna.common.commonutils.ToastUtils
import org.json.JSONObject
import java.util.ArrayList

class XmIssuePresenter : XmIssueContract.Companion.Presenter(){

    override fun getAddXmIssue(message: BcProjectEntity) {//添加/修改用户
        val toJson = Gson().toJson(message)//.substring(18, Gson().toJson(enviorSupvsEntity).toString().length - 1)
//        BaseRespose<PageUtils<ScoreEntity>>

        OkGo.post<BaseRespose<String>>(ApiConstants.PROJECT_SAVE_ORUPDATE).upJson(toJson).execute(object ://
                BaseNet<BaseRespose<String>>(){
            override fun onStart(request: Request<BaseRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<String>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnAddUser("保存成功")

                        }else{
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("保存失败")
                    }
                }else{
                    mView?.showErrorTip("保存失败")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)//response.exception.message
                            else
                                mView.showErrorTip("保存失败")
                        }else{
                            mView.showErrorTip("保存失败")
                        }

                    }else{
                        mView.showErrorTip("添加失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getByPoint(point: String) {//获取点位

        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        jsonObject.put("code", AppCache.getInstance().cunCode)
        OkGo.post<BaseRespose<CjVO>>(ApiConstants.PROJECT_GETBUPOINT).upJson(jsonObject).execute(object ://
                BaseNet<BaseRespose<CjVO>>(){
            override fun onStart(request: Request<BaseRespose<CjVO>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<CjVO>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.returnByPoint(body.data,point)
                            }else{
                                ToastUtils.showShort("请选择行政区范围内")
                            }

                        }else{
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("请选择行政区范围内")
                    }
                }else{
                    mView?.showErrorTip("请选择行政区范围内")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<CjVO>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip("请选择行政区范围内")//response.exception.message
                            else
                                mView.showErrorTip("请选择行政区范围内")
                        }else{
                            mView.showErrorTip("请选择行政区范围内")
                        }

                    }else{
                        mView.showErrorTip("请选择行政区范围内")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getProject(id: Long) {//获取点位
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("code", AppCache.getInstance().cunCode)
        OkGo.post<BaseRespose<PageUtils<BcProjectEntity>>>(ApiConstants.PROJECT_PROJECT).upJson(jsonObject).execute(object ://
                BaseNet<BaseRespose<PageUtils<BcProjectEntity>>>(){
            override fun onStart(request: Request<BaseRespose<PageUtils<BcProjectEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<PageUtils<BcProjectEntity>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                if (body.data.list.size>0){
                                    mView?.returnProject(body.data.list.get(0))
                                }else{
                                    ToastUtils.showShort("暂无数据")
                                }
                            }else{
                                ToastUtils.showShort("暂无数据")
                            }

                        }else{
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.showErrorTip("请选择行政区范围内")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<PageUtils<BcProjectEntity>>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)//response.exception.message
                            else
                                mView.showErrorTip("暂无数据")
                        }else{
                            mView.showErrorTip("暂无数据")
                        }

                    }else{
                        mView.showErrorTip("暂无数据")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getProjectGetLabel() {//获取点位
        /*val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("code", AppCache.getInstance().cunCode)*/
        OkGo.post<BaseRespose<List<JsjbBean>>>(ApiConstants.PROJECT_GET_LABEL).upJson("[]").execute(object ://
                BaseNet<BaseRespose<List<JsjbBean>>>(){
            override fun onStart(request: Request<BaseRespose<List<JsjbBean>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<List<JsjbBean>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.returnProjectGetLabel(body.data)
                            }else{
                                mView?.returnProjectGetLabel(ArrayList<JsjbBean>())
                                ToastUtils.showShort("暂无数据")
                            }

                        }else{
                            mView?.returnProjectGetLabel(ArrayList<JsjbBean>())
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.returnProjectGetLabel(ArrayList<JsjbBean>())
                        mView?.showErrorTip("暂无数据")
                    }
                }else{
                    mView?.returnProjectGetLabel(ArrayList<JsjbBean>())
                    mView?.showErrorTip("暂无数据")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<List<JsjbBean>>>?) {
                super.onError(response)
                mView?.returnProjectGetLabel(ArrayList<JsjbBean>())
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)//response.exception.message
                            else
                                mView.showErrorTip("暂无数据")
                        }else{
                            mView.showErrorTip("暂无数据")
                        }

                    }else{
                        mView.showErrorTip("暂无数据")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getSaveLabel(message: JsjbBean) {//添加/修改用户
        val toJson = Gson().toJson(message)//.substring(18, Gson().toJson(enviorSupvsEntity).toString().length - 1)
//        BaseRespose<PageUtils<ScoreEntity>>

        OkGo.post<BaseRespose<String>>(ApiConstants.PROJECT_SAVE_LABEL).upJson(toJson).execute(object ://
                BaseNet<BaseRespose<String>>(){
            override fun onStart(request: Request<BaseRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<String>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnSaveLabel("添加成功")

                        }else{
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("添加失败")
                    }
                }else{
                    mView?.showErrorTip("添加失败")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)//response.exception.message
                            else
                                mView.showErrorTip("添加失败")
                        }else{
                            mView.showErrorTip("添加失败")
                        }

                    }else{
                        mView.showErrorTip("添加失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

}