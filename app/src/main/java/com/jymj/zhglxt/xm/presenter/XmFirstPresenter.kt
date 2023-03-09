package com.jymj.zhglxt.xm.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.contract.XmFirstContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class XmFirstPresenter : XmFirstContract.Companion.Presenter(){
    override fun getXmList(page: Int, limit: Int,xmlb:Int,name:String) {
        val jsonObject = JSONObject()
        jsonObject.put("code", AppCache.getInstance().code)
        jsonObject.put("page", page)
        jsonObject.put("limit", limit)
        jsonObject.put("xmlb", xmlb)
        jsonObject.put("name", name)
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
}