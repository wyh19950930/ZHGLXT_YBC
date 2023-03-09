package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R.drawable.point
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.BcjjzsrqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtjjsrqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtjjzcqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.SrZcBean
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTj2Bean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbTjBean
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.YqglContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class YqglPresenter : YqglContract.Companion.Presenter(){
    override fun getBcjcUpdateCjtjjzyzcqk(entity: BcjtjjzcqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(entity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_CJTJJZYZCQKUPDATE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.code==0){
                        if (json.msg!=null){
                            mView.returnBcjcUpdateCjtjjzyzcqk(json.msg)
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

    override fun getBcjcUpdateCjtjjzysrqk(entity: BcjtjjsrqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(entity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_CJTJJZYSRQKUPDATE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.code==0){
                        if (json.msg!=null){
                            mView.returnBcjcUpdateCjtjjzysrqk(json.msg)
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

    override fun getBcjcUpdateCjjzsrqk(entity: BcjjzsrqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(entity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_CJJZSRQKUPDATE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.code==0){
                        if (json.msg!=null){
                            mView.returnBcjcUpdateCjjzsrqk(json.msg)
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

    override fun getBcjcCjjzsrqkAll(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETCJJZSRQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcjjzsrqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcjjzsrqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBcjcCjjzsrqkAll(json.data)
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

    override fun getBcjcCjtjjzysrqkAll(code: String, years: List<String>) {val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETCJTJJZYSRQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcjtjjsrqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcjtjjsrqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBcjcCjtjjzysrqkAll(json.data)
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

    override fun getBcjcCjtjjzyzcqkAll(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETCJTJJZYZCQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcjtjjzcqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcjtjjzcqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBcjcCjtjjzyzcqkAll(json.data)
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

    override fun getBcjcCjtjjzyzcqk(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETCJTJJZYZCQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcjtjjzcqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcjtjjzcqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            if (json.data.size==0){
                                val arrayList = ArrayList<BcjtjjzcqkEntity>()
                                arrayList.add(BcjtjjzcqkEntity())
                                mView.returnBcjcCjtjjzyzcqk(arrayList)
                            }else{
                                mView.returnBcjcCjtjjzyzcqk(json.data)
                            }
                        }else{
                            mView.showErrorTip("数据加载为空")
                            val arrayList = ArrayList<BcjtjjzcqkEntity>()
                            arrayList.add(BcjtjjzcqkEntity())
                            mView.returnBcjcCjtjjzyzcqk(arrayList)
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        val arrayList = ArrayList<BcjtjjzcqkEntity>()
                        arrayList.add(BcjtjjzcqkEntity())
                        mView.returnBcjcCjtjjzyzcqk(arrayList)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                    val arrayList = ArrayList<BcjtjjzcqkEntity>()
                    arrayList.add(BcjtjjzcqkEntity())
                    mView.returnBcjcCjtjjzyzcqk(arrayList)
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
                val arrayList = ArrayList<BcjtjjzcqkEntity>()
                arrayList.add(BcjtjjzcqkEntity())
                mView.returnBcjcCjtjjzyzcqk(arrayList)
            }

        })
    }

    override fun getBcjcAddCjtjjzyzcqk(entity: BcjtjjzcqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(entity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_CJTJJZYZCQKSAVE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.code==0){
                        if (json.msg!=null){
                            mView.returnBcjcAddCjtjjzyzcqk(json.msg)
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

    override fun getBcjcCjtjjzysrqk(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETCJTJJZYSRQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcjtjjsrqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcjtjjsrqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            if (json.data.size==0){
                                val arrayList = ArrayList<BcjtjjsrqkEntity>()
                                arrayList.add(BcjtjjsrqkEntity())
                                mView.returnBcjcCjtjjzysrqk(arrayList)
                            }else{
                                mView.returnBcjcCjtjjzysrqk(json.data)
                            }
                        }else{
                            mView.showErrorTip("数据加载为空")
                            val arrayList = ArrayList<BcjtjjsrqkEntity>()
                            arrayList.add(BcjtjjsrqkEntity())
                            mView.returnBcjcCjtjjzysrqk(arrayList)
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        val arrayList = ArrayList<BcjtjjsrqkEntity>()
                        arrayList.add(BcjtjjsrqkEntity())
                        mView.returnBcjcCjtjjzysrqk(arrayList)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                    val arrayList = ArrayList<BcjtjjsrqkEntity>()
                    arrayList.add(BcjtjjsrqkEntity())
                    mView.returnBcjcCjtjjzysrqk(arrayList)
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
                val arrayList = ArrayList<BcjtjjsrqkEntity>()
                arrayList.add(BcjtjjsrqkEntity())
                mView.returnBcjcCjtjjzysrqk(arrayList)
            }

        })
    }
    override fun getBcjcGetCjjjsrzc(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETCJJJSRZC).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<SrZcBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<SrZcBean>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBcjcGetCjjjsrzc(json.data)
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

    override fun getBcjcAddCjtjjzysrqk(entity: BcjtjjsrqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(entity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_CJTJJZYSRQKSAVE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.code==0){
                        if (json.msg!=null){
                            mView.returnBcjcAddCjtjjzysrqk(json.msg)
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

    //获取村经济总收入情况
    override fun getBcjcCjjzsrqk(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETCJJZSRQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BcjjzsrqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcjjzsrqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            if (json.data.size==0){
                                val arrayList = ArrayList<BcjjzsrqkEntity>()
                                arrayList.add(BcjjzsrqkEntity())
                                mView.returnBcjcCjjzsrqk(arrayList)
                            }else{
                                mView.returnBcjcCjjzsrqk(json.data)
                            }
                        }else{
                            mView.showErrorTip("数据加载为空")
                            val arrayList = ArrayList<BcjjzsrqkEntity>()
                            arrayList.add(BcjjzsrqkEntity())
                            mView.returnBcjcCjjzsrqk(arrayList)
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        val arrayList = ArrayList<BcjjzsrqkEntity>()
                        arrayList.add(BcjjzsrqkEntity())
                        mView.returnBcjcCjjzsrqk(arrayList)
                    }

                } else {
                    mView.showErrorTip("数据加载为空")
                    val arrayList = ArrayList<BcjjzsrqkEntity>()
                    arrayList.add(BcjjzsrqkEntity())
                    mView.returnBcjcCjjzsrqk(arrayList)
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
                val arrayList = ArrayList<BcjjzsrqkEntity>()
                arrayList.add(BcjjzsrqkEntity())
                mView.returnBcjcCjjzsrqk(arrayList)
            }

        })
    }

    //添加村经济总收入情况
    override fun getBcjcAddCjjzsrqk(entity: BcjjzsrqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(entity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_CJJZSRQKSAVE).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    if (json.code==0){
                        if (json.msg!=null){
                            mView.returnBcjcAddCjjzsrqk(json.msg)
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

    override fun getBcjcYears() {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt("{}".toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETYEARS).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<String>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<String>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBcjcYears(json.data)
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

    override fun getNyyqItemList(code: String) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.NYD_GETNydLIST).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<YztNyyqEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<YztNyyqEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnNyyItemList(json.data)
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

    override fun getNyyqCunList() {
        val jsonObject = JSONObject()
//        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.NYD_GETCUNLIST).upJson("{}").execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<NydVo>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<NydVo>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnNyyqCunList(json.data)
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
    override fun getOpinionGetCount() {
        val jsonObject = JSONObject()
//        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.OPINION_GET_COUNT).upJson("{}").execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<JsjbTjBean>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<JsjbTjBean>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnOpinionGetCount(json.data)
                        }else{
                            mView.returnOpinionGetCount(ArrayList<JsjbTjBean>())
//                            mView.showErrorTip("数据加载为空")
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
    override fun getOpinionGetStatistical() {
        val jsonObject = JSONObject()
//        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.OPINION_GET_STATISTICAL).upJson("{}").execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<JsjbTj2Bean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<JsjbTj2Bean>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnOpinionGetStatistical(json.data)
                        }else{
//                            mView.returnOpinionGetCount(ArrayList<JsjbTjBean>())
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