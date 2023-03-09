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
import com.jymj.zhglxt.xm.contract.ScListContract
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

class ScListPresenter : ScListContract.Companion.Presenter(){
    override fun getProjectGetCallection(page: Int, limit: Int,xmlb:Int,name:String) {
        val jsonObject = JSONObject()
        jsonObject.put("code", AppCache.getInstance().code)
        jsonObject.put("page", page)
        jsonObject.put("limit", limit)
        jsonObject.put("xmlb", xmlb)
        jsonObject.put("name", name)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PROJECT_GET_CALLECTION).upJson(sss).execute(object ://EnviorListEntity
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
                            mView.returnProjectGetCallection(json.data.list)
                        }else{
                            mView.returnProjectGetCallection(ArrayList<BcProjectEntity>())
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.returnProjectGetCallection(ArrayList<BcProjectEntity>())
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
                mView.returnProjectGetCallection(ArrayList<BcProjectEntity>())
                mView.showErrorTip("网络错误")
//                mView.returnPorjectList(ArrayList<BcXmfwEntity>())
            }

        })
    }

}