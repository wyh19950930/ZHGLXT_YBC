package com.jymj.zhglxt.rjhj.presenter

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.SuccessBean
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.contract.EnvironmentalActContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposeDate
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject

class EnvironmentalActPresenter : EnvironmentalActContract.Companion.Presenter(){

    //qutype  roleid
    override fun getFallBackQutype(id: Int,qutype: String,comment: String) {//请求数据
//        val httpParams = HttpParams()
        val httpParams = JSONObject()
        httpParams.put("id", id)
        httpParams.put("qutype", qutype)//当前页数，从1开始
        httpParams.put("comment", comment)//当前页数，从1开始
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.FALL_BACK_QUTYPE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
//                    val json: EnviorListEntity = Gson().fromJson(cash, object : TypeToken<EnviorListEntity?>() {}.type)
                    if (json.code==0){
                        mView.returnFallBackQutype(json.getMsg())
                        /*if (cash.data!=null){
                            mView.returnFallBackQutype(cash)
                        }else{
                            mView.showErrorTip("数据加载为空")
                        }*/
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
    override fun getQueryCun(name:String, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText) {
//        val httpParams = HttpParams()
        val jsonObject = JSONObject()
        jsonObject.put("name",name)//date   BaseResposeDate<>
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPV_QUERY_CUN).upJson(sss).execute(object ://httpParams  .upJson("{\"name\":\""+name+"\"}")
                BaseNet<String>(){//BaseResposeDate<List<CjEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseResposeDate<List<CjEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseResposeDate<List<CjEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json!!.date!=null){
                            mView.returnQueryCun(json!!.date,pjEnviorSupvsEntity,rlv_xzxzq,tvWzenvironmental)
                        }else {
                            mView.showErrorTip("数据为空")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!!.exception!=null){
                    if (response.exception.message!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("请求失败")
                    }
                }else{
                    mView.showErrorTip("请求失败")
                }

            }

        })
    }
    //qutype  roleid
    override fun getEnvironmental(qutype: String,roleid: String) {//请求数据
        /*val httpParams = HttpParams()
//        httpParams.put("pid", proId)
        httpParams.put("qutype", roleid)//当前页数，从1开始*/
        val jsonObject = JSONObject()
        jsonObject.put("qutype", roleid)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18,Gson().toJson(jsonObject).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORSUPVSLIST).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: EnviorListEntity = Gson().fromJson(decrypt, object : TypeToken<EnviorListEntity?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnEnvironmental(json.data.list)
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
    //环保检查详情页修改
    override fun addEnvironmental(enviorSupvsEntity: PjEnviorSupvsEntity) {//请求数据
        val jsonObject = JSONObject()
//        jsonObject.put("qutype", roleid)//当前页数，从1开始
        val toJson = Gson().toJson(enviorSupvsEntity)//.substring(18,Gson().toJson(enviorSupvsEntity).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORSUPVSUPDATE).upJson(sss).execute(object ://Gson().toJson(enviorSupvsEntity)
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {

                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json = Gson().fromJson(decrypt, SuccessBean::class.java)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView.returnAddEnvironmental("修改成功")
                            EventBus.getDefault().post("修改成功")
                        }else{
                            mView.showErrorTip(json.msg)
                        }
                    }else{
                        mView.showErrorTip("修改成功")
                    }
                }else{
                    mView.showErrorTip("修改失败")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!!.exception!=null){
                    if (response!!.exception.message!=null){
                        mView.showErrorTip(response!!.exception.message)
                    }else{
                        mView.showErrorTip("修改失败")
                    }
                }else{
                    mView.showErrorTip("修改失败")
                }

            }

        })

    }

    override fun tuiEnvironmental(enviorSupvsEntity: PjRoleEnviorEntity) {//政府主管推送
        val toJson = Gson().toJson(enviorSupvsEntity)//.substring(18,Gson().toJson(enviorSupvsEntity).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.PJROLEENVIORSAVE).upJson(sss).execute(object ://Gson().toJson(enviorSupvsEntity) BaseRespose <String>
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose <String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <String>?>() {}.type)
                    if (json.code==0){
                        mView.returnTuiEnvironmental(json)
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("修改失败")
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
    override fun deleteEnvironmental(id: Int,pjid :String) {//请求数据
       /* val httpParams = HttpParams()
        httpParams.put("id",id)
        httpParams.put("pjid",pjid)*/
        val jsonObject = JSONObject()
        jsonObject.put("id", id)//当前页数，从1开始
        jsonObject.put("pjid", pjid)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18,Gson().toJson(jsonObject).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORSUPVSDELETE).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose <*> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <*>?>() {}.type)
                    if (json.code==0){
//                        if (cash.data!=null){
                            mView.returnDeleteEnvironmental(json)
                        /*}else{
                            mView.showErrorTip("数据加载为空")
                        }*/
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
    override fun deletesEnvironmental(ids: ArrayList<Int>) {//请求数据
       /* val httpParams = HttpParams()
        httpParams.put("ids",ids.toString().substring(1, ids.toString().length - 1))*/
        val jsonObject = JSONObject()
        jsonObject.put("ids", ids)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18,Gson().toJson(jsonObject).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORSUPVSDELETES).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()

                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose <*> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <*>?>() {}.type)
                    if (json.code==0){
//                        if (cash.data!=null){
                            mView.returnDeletesEnvironmental(json)
                       /* }else{
                            mView.showErrorTip("数据加载为空")
                        }*/
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
    override fun undateEnvironmental(enviorSupvsEntity: PjEnviorSupvsEntity) {//请求数据
//        val toJson = Gson().toJson(enviorSupvsEntity)
        val toJson = Gson().toJson(enviorSupvsEntity).substring(18,Gson().toJson(enviorSupvsEntity).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORSUPVSUPDATE).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose <String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose <String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <String>?>() {}.type)
                    if (json.code==0){
                        mView.returnUpdateEnvironmental(json)
                    }else{
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
                mView.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!!.exception!=null){
                    if (response!!.exception.message!=null){
                        mView.showErrorTip(response!!.exception.message)
                    }else{
                        mView.showErrorTip("修改失败")
                    }
                }else{
                    mView.showErrorTip("修改失败")
                }
            }

        })

    }
    override fun deleteFileEnvironmental(id: Int) {//请求数据  ,pjid :String
       /* val httpParams = HttpParams()
        httpParams.put("id",id)
        httpParams.put("pjid",pjid)*/
        val jsonObject = JSONObject()
        jsonObject.put("id", id)//当前页数，从1开始
//        jsonObject.put("pjid",pjid)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18,Gson().toJson(jsonObject).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(id.toString().toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORFILEDELETE).upJson("[${sss}]").execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose <*> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <*>?>() {}.type)
                    if (json.code==0){
                        mView.returnDeleteFileEnvironmental(json)
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
    override fun uploadFileEnvironmental(id: Int) {//请求数据
      /*  val httpParams = HttpParams()
        httpParams.put("id",id)*/
        val jsonObject = JSONObject()
        jsonObject.put("id", id)//当前页数，从1开始
        val toJson = Gson().toJson(jsonObject).substring(18,Gson().toJson(jsonObject).toString().length - 1)
//        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
//        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORFILEUPLOAD).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose <*> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <*>?>() {}.type)
                    if (json.code==0){
//                        if (cash.data!=null){
                            mView.returnUploadEnvironmental(json)
                        /*}else{
                            mView.showErrorTip("数据加载为空")
                        }*/
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
    override fun getListEnvironmental(rtInvestEntity: PjEnviorSupvsEntity) {//修改
        OkGo.post<PjEnviorSupvsEntity>(ApiConstants.UPDATE_CASH).execute(object :
                BaseNet<PjEnviorSupvsEntity>(){
            override fun onStart(request: Request<PjEnviorSupvsEntity, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<PjEnviorSupvsEntity>?) {
                val cash = response?.body()
                if (cash!=null){
                    mView.returnListEnvironmental(cash)
                }else {
                    mView.showErrorTip("数据加载出错")
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<PjEnviorSupvsEntity>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }

        })

    }
    override fun ryEnvironmental(code :String) {//请求数据
//        val jsonObject = HttpParams()
        val jsonObject = JSONObject()
        jsonObject.put("code",code)//当前页数，从1开始  110112029215
        OkGo.post<BaseResposeDate<List<SysUserEntity>>> (ApiConstants.ENVIORSUPV_QUERY_RY).upJson(jsonObject).execute(object :
                BaseNet<BaseResposeDate<List<SysUserEntity>>>(){
            override fun onStart(request: Request<BaseResposeDate<List<SysUserEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseResposeDate<List<SysUserEntity>>>?) {
                val cash = response?.body()
                if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
//                    val json: BaseRespose <*> = Gson().fromJson(cash, object : TypeToken<BaseRespose <*>?>() {}.type)
                    if (cash.code==0){
                        if (cash.date !=null){
                            mView.returnRyEnvironmental(cash.date)
                        }else{
                            mView.showErrorTip("请求错误")
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseResposeDate<List<SysUserEntity>>>?) {
                super.onError(response)
                if (response!!.exception!=null){
                    if (response!!.exception.message!=null){
                        mView.showErrorTip(response!!.exception.message)
                    }else{
                        mView.showErrorTip("请求错误")
                    }

                }else{
                    mView.showErrorTip("请求错误")
                }
            }

        })

    }
    override fun infoEnvironmental(id :String) {//请求数据
//        val jsonObject = HttpParams()
        val jsonObject = JSONObject()
        jsonObject.put("id",id)//当前页数，从1开始  110112029215
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.ENVIORSUPVS_INFO).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<RjhjInfoBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose <RjhjInfoBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <RjhjInfoBean>?>() {}.type)
                    if (json.code==0){
                        if (json.data !=null){
                            mView.returnInfoEnvironmental(json.data)
                        }else{
                            mView.showErrorTip("请求错误")
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
                if (response!!.exception!=null){
                    if (response!!.exception.message!=null){
                        mView.showErrorTip(response!!.exception.message)
                    }else{
                        mView.showErrorTip("请求错误")
                    }
                }else{
                    mView.showErrorTip("请求错误")
                }
            }

        })

    }
    override fun getRjhjPoint(point: String) {
        val jsonObject = JSONObject()
//        val jsonObject = HttpParams()
        jsonObject.put("point", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.POINT_QUERY_DKDL).upJson(sss).execute(object :
                BaseNet<String>(){//BaseRespose<RjhjBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose <RjhjBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose <RjhjBean>?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null){
                            mView.returnRjhjPoint(json.data)
                        }else {
                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.message()!=null){
                    mView.showErrorTip(response?.message())
                }else{
                    mView.showErrorTip("请求失败")
                }

            }

        })
    }


}