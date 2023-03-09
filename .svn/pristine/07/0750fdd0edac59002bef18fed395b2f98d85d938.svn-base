package com.jymj.zhglxt.ui.main.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.ui.main.contract.QyglActContract
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.cygl.CyyqZztEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class QyglActPresenter : QyglActContract.Companion.Presenter(){
    override fun getCyyqPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENTERPRISE_QUERYBYPOINT).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<EnterpriseBasisEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<EnterpriseBasisEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnQyglPoint(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getCyyqZzt() {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"

        OkGo.post<String> (ApiConstants.ENTERPRISE_GETXZQCOUNT).upJson("{}").execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<CyyqZztEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<CyyqZztEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnCyyqZzt(json.data)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getQyglList(page: Int, limit: Int, qylx: Int, garden: String, zcdate: String, zcdz: String, qymc: String,jyzt:Int) {
        val jsonObject = JSONObject()
        jsonObject.put("page", page)
        jsonObject.put("limit", limit)
        if (garden.equals("全部")||garden.equals("")){
            jsonObject.put("garden", "")
        }else{
            jsonObject.put("garden", garden)
        }
        if (qylx!=-1)
        jsonObject.put("hylx", qylx)//行业类型
        if (jyzt!=-1)
        jsonObject.put("jyzt", jyzt)//经营状态
        if (!zcdate.equals(""))
        jsonObject.put("zcdate", zcdate)//注册时间
        jsonObject.put("zcdz", zcdz)//注册地址
        jsonObject.put("qymc", qymc)//企业名称
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENTRPRIE_QUERY_LIST).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<PageUtils<EnterpriseBasisEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<EnterpriseBasisEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnQyglList(json.data.list)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })
    }

}