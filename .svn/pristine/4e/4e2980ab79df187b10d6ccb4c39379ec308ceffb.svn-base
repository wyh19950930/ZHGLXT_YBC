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
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.contract.XmFirstContract
import com.jymj.zhglxt.xm.contract.XmIssueContract
import com.jymj.zhglxt.xm.contract.XmTzDetailContract
import com.jymj.zhglxt.zjd.contract.AddUserContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposeUser
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import org.json.JSONObject
import java.util.ArrayList

class XmTzDetailPresenter : XmTzDetailContract.Companion.Presenter(){
    override fun getXmList(id: Long) {
        val jsonObject = JSONObject()
        jsonObject.put("code", AppCache.getInstance().code)
        jsonObject.put("id", id)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PROJECT_PROJECT).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {//ProjectEntity
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<PageUtils<BcProjectEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<BcProjectEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnXmList(json.data.list)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
//                    mView.returnPorjectList(ArrayList<BcXmfwEntity>())
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
//                mView.returnPorjectList(ArrayList<BcXmfwEntity>())
            }

        })
    }

    override fun getAddUser(message: SysUserEntity) {//添加/修改用户
        val toJson = Gson().toJson(message)//.substring(18, Gson().toJson(enviorSupvsEntity).toString().length - 1)
//        BaseRespose<PageUtils<ScoreEntity>>

        OkGo.post<BaseRespose<String>>(ApiConstants.SYS_USER_SAVEUSER).upJson(toJson).execute(object ://
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
                                mView.showErrorTip("手机号/用户名已经存在")//response.exception.message
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
    override fun getXmDelete(id: Long) {
        var sss = "{\"requestData\":\"[" + id + "]\"}"//encrypt
        OkGo.post<String> (ApiConstants.PROJECT_DELETE_PROJECT).upJson("[" + id + "]").execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                    if (json.code==0){
                        mView?.returnXmDelete("删除成功")
                    }else{
                        mView?.showErrorTip(json.getMsg())
                    }

                } else {
                    mView?.showErrorTip("删除失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip("删除失败")
            }

        })
    }
    override fun getProjectSaveCollection(id: Long) {
        var sss = "{\"requestData\":\"[" + id + "]\"}"//encrypt
        OkGo.post<String> (ApiConstants.PROJECT_SAVE_COLLECTION).upJson("[" + id + "]").execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                    if (json.code==0){
                        mView?.returnProjectSaveCollection("收藏成功")
                    }else{
                        mView?.showErrorTip(json.getMsg())
                    }

                } else {
                    mView?.showErrorTip("收藏失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip("收藏失败")
            }

        })
    }
    override fun getProjectDeleteCollection(id: Long) {
        var sss = "{\"requestData\":\"[" + id + "]\"}"//encrypt
        OkGo.post<String> (ApiConstants.PROJECT_DELETE_COLLECTION).upJson("[" + id + "]").execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                    if (json.code==0){
                        mView?.returnProjectDeleteCollection("取消收藏")
                    }else{
                        mView?.showErrorTip(json.getMsg())
                    }

                } else {
                    mView?.showErrorTip("取消收藏失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView?.showErrorTip("取消收藏失败")
            }

        })
    }
}