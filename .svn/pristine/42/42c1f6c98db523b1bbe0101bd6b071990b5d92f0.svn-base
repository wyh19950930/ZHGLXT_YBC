package com.jymj.zhglxt.zjd.presenter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.zjd.contract.AddUserContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposeUser
import org.json.JSONObject
import java.util.ArrayList

class AddUserPresenter : AddUserContract.Companion.Presenter(){

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
    /*override fun getUpdateUser(message: SysUserEntity) {//修改用户
        val toJson = Gson().toJson(message)//.substring(18, Gson().toJson(enviorSupvsEntity).toString().length - 1)
//        BaseRespose<PageUtils<ScoreEntity>>

        OkGo.post<BaseRespose<String>>(ApiConstants.LOGIN_UPDATE_USER).upJson(toJson).execute(object ://
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
                            mView?.returnUpdateUser("修改成功")
                        }else{
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("修改失败")
                    }
                }else{
                    mView?.showErrorTip("修改失败")
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
                                mView.showErrorTip(response.exception.message)
                            else
                                mView.showErrorTip("修改失败")
                        }else{
                            mView.showErrorTip("修改失败")
                        }

                    }else{
                        mView.showErrorTip("修改失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }*/

    override fun getUserInfo(userId:String) {
        val httpParams1 = JSONObject()
        httpParams1.put("userId",userId)
        OkGo.post<BaseResposeUser<SysUserEntity>>(ApiConstants.SYS_USER_INFO).upJson(httpParams1).execute(object : BaseNet<BaseResposeUser<SysUserEntity>>(){
            override fun onStart(request: Request<BaseResposeUser<SysUserEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseResposeUser<SysUserEntity>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnUserInfo(body.getUser())
                        }else{
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("查询失败")
                    }
                }else{
                    mView?.showErrorTip("查询失败")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseResposeUser<SysUserEntity>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.message()!=null){
                        if (!response.message().equals(""))
                            mView.showErrorTip(response.message())
                        else
                            mView.showErrorTip("查询失败")
                    }else{
                        mView.showErrorTip("查询失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
}