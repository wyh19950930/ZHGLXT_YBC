package com.jymj.zhglxt.zjd.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.zjd.contract.UserListContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.*
import org.json.JSONObject
import java.util.ArrayList

class UserListPresenter : UserListContract.Companion.Presenter(){

    override fun getXzqZhen(code:String,type:Int) {
        val httpParams1 = JSONObject()
        httpParams1.put("code",code)
//        httpParams1.put("type",type)
        OkGo.post<BaseResposeXzqList<SysXzqEntity>>(ApiConstants.SYS_XZQ_PERMS).upJson(httpParams1).execute(object : BaseNet<BaseResposeXzqList<SysXzqEntity>>(){
            override fun onStart(request: Request<BaseResposeXzqList<SysXzqEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseResposeXzqList<SysXzqEntity>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnXzqZhen(body.getXzqList(),type)
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

            override fun onError(response: Response<BaseResposeXzqList<SysXzqEntity>>?) {
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
    override fun getUserQueryRy(code: String,name: String) {
        var httpParams = JSONObject()
        httpParams.put("page", 1)
        httpParams.put("limit", 1000)
        httpParams.put("order", "asc")
        httpParams.put("sidx", "")
        if (name.equals("")){
            httpParams.put("code", code)
        }
        httpParams.put("username", name)

        OkGo.post<BaseRespose<PageUtils<SysUserEntity>>>(ApiConstants.SYS_USER_GETUSERLST).upJson(httpParams).execute(object ://BaseResposePage<PageUtils<SysUserEntity>>
                BaseNet<BaseRespose<PageUtils<SysUserEntity>>>(){
            override fun onStart(request: Request<BaseRespose<PageUtils<SysUserEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<PageUtils<SysUserEntity>>>?) {//BaseResposeSysUser<SysUserEntity>
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data==null){
                                mView?.returnUserList(ArrayList<SysUserEntity>())
                            }else{
                                mView?.returnUserList(body.data.list)
                            }
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

            override fun onError(response: Response<BaseRespose<PageUtils<SysUserEntity>>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.showErrorTip(response.exception.message)
                            else
                                mView.showErrorTip("查询失败")
                        }else{
                            mView.showErrorTip("查询失败")
                        }

                    }else{
                        mView.showErrorTip("查询失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

}