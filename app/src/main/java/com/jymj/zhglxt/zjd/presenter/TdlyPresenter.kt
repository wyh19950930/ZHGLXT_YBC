package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyDetail
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.BcTdlyEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczhandqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BczzdqkEntity
import com.jymj.zhglxt.zjd.bean.tdxg.GhVO
import com.jymj.zhglxt.zjd.bean.tdxg.QsVO
import com.jymj.zhglxt.zjd.bean.tdxg.TdlyVO
import com.jymj.zhglxt.zjd.bean.tdxg.YztSktVO
import com.jymj.zhglxt.zjd.bean.yzt.tx.TxPolygonEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.TdlyContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class TdlyPresenter : TdlyContract.Companion.Presenter(){
    override fun getBcjcUpdateZhandqk(edtity: BczhandqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_ZHANQKUPDATE).upJson(sss).execute(object ://EnviorListEntity
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
                            mView.returnBcjcUpdateZhandqk(json.msg)
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

    override fun getBcjcUpdateZhengdqk(edtity: ArrayList<BczzdqkEntity>) {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_ZZDQK_SAVE_OR_UPDATE).upJson(sss).execute(object ://EnviorListEntity
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
                            mView.returnBcjcUpdateZhengdqk(json.msg)
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

    override fun getBchcZhengdqkAll(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETZZDQK).upJson(sss).execute(object ://EnviorListEntity  BCJC_GETZDQK
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BczzdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BczzdqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBchcZhengdqkAll(json.data)
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

    override fun getBchcZhandqkAll(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETZHANDQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BczhandqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BczhandqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBchcZhandqkAll(json.data)
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

    override fun getBcjcAddZhandqk(edtity: BczhandqkEntity) {
        val gson = Gson()
        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(edtity.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_ZHANQKSAVE).upJson(sss).execute(object ://EnviorListEntity
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
                            mView.returnBcjcAddZhandqk(json.msg)
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

    override fun getBchcZhandqk(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETZHANDQK).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BczhandqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BczhandqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            if (json.data.size==0){
                                val arrayList = ArrayList<BczhandqkEntity>()
                                arrayList.add(BczhandqkEntity())
                                mView.returnBchcZhandqk(arrayList)
                            }else{
                                mView.returnBchcZhandqk(json.data)
                            }
                        }else{
                            val arrayList = ArrayList<BczhandqkEntity>()
                            arrayList.add(BczhandqkEntity())
                            mView.returnBchcZhandqk(arrayList)
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        val arrayList = ArrayList<BczhandqkEntity>()
                        arrayList.add(BczhandqkEntity())
                        mView.returnBchcZhandqk(arrayList)
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    val arrayList = ArrayList<BczhandqkEntity>()
                    arrayList.add(BczhandqkEntity())
                    mView.returnBchcZhandqk(arrayList)
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                val arrayList = ArrayList<BczhandqkEntity>()
                arrayList.add(BczhandqkEntity())
                mView.returnBchcZhandqk(arrayList)
                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getBchcGetInzhand(code: String, years: List<String>) {
        val jsonObject = JSONObject()//占地
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETLNZHAND).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BczhandqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BczhandqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBchcGetInzhand(json.data)
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
    override fun getBcjcAddZhengdqk(edtity: ArrayList<BczzdqkEntity>) {//BczdqkEntity  BczzdqkEntity
        val gson = Gson()
//        val edtity = gson.toJson(edtity)
        val encrypt = AesEncryptUtils.encrypt(gson.toJson(edtity).toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_ZZDQK_SAVE_OR_UPDATE).upJson(sss).execute(object ://EnviorListEntity
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
                            mView.returnBcjcAddZhengdqk(json.msg)
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

    override fun getBchcZhengdqk(code: String, years: List<String>) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        if (years.size>0){

            jsonObject.put("year", years.get(0))
        }
//        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETZZDQK).upJson(Gson().toJson(jsonObject)).execute(object ://EnviorListEntity   BCJC_GETZDQK
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BczzdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BczzdqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            if (json.data.size==0){
                                val arrayList = ArrayList<BczzdqkEntity>()
                                arrayList.add(BczzdqkEntity())
                                mView.returnBchcZhengdqk(arrayList)
                            }else{
                                mView.returnBchcZhengdqk(json.data)
                            }
                        }else{
                            val arrayList = ArrayList<BczzdqkEntity>()
                            arrayList.add(BczzdqkEntity())
                            mView.returnBchcZhengdqk(arrayList)
                            mView.showErrorTip("数据加载为空")
                        }
                    }else{
                        val arrayList = ArrayList<BczzdqkEntity>()
                        arrayList.add(BczzdqkEntity())
                        mView.returnBchcZhengdqk(arrayList)
                        mView.showErrorTip(json.msg)
                    }

                } else {
                    val arrayList = ArrayList<BczzdqkEntity>()
                    arrayList.add(BczzdqkEntity())
                    mView.returnBchcZhengdqk(arrayList)
                    mView.showErrorTip("数据加载为空")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                val arrayList = ArrayList<BczzdqkEntity>()
                arrayList.add(BczzdqkEntity())
                mView.returnBchcZhengdqk(arrayList)
                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getBchcGetKnzd(code: String, years: List<String>) {
        val jsonObject = JSONObject()//征地
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.BCJC_GETLNZD).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<List<BczzdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BczzdqkEntity>>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnBchcGetKnzd(json.data)
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


    override fun getTxPolygonDetail(points: String, pageSize: Int, currPage: Int) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        jsonObject.put("limit", pageSize)
        jsonObject.put("page", currPage)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<PageUtils<TdlyVO>>>(ApiConstants.POINT_GET_TDLY_QUERY_FRAME ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<PageUtils<TdlyVO>>>(){
            override fun onStart(request: Request<BaseRespose<PageUtils<TdlyVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<TdlyVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonDetailTx(response.body().data.list)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<PageUtils<TdlyVO>>>?) {
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
            }
        })
    }

    override fun getTxPolygon(points: String) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<MutableList<TdlyVO>>>(ApiConstants.POINT_GET_TX_KXHZ ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<MutableList<TdlyVO>>>(){
            override fun onStart(request: Request<BaseRespose<MutableList<TdlyVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<MutableList<TdlyVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonTx(response.body().data)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<MutableList<TdlyVO>>>?) {
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
            }
        })
    }
    override fun getSktPolygonDetail(points: String, pageSize: Int, currPage: Int) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        jsonObject.put("limit", pageSize)
        jsonObject.put("page", currPage)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<PageUtils<YztSktVO>>>(ApiConstants.POINT_GET_TG_QUERY_FRAME ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<PageUtils<YztSktVO>>>(){
            override fun onStart(request: Request<BaseRespose<PageUtils<YztSktVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<YztSktVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonDetailSkt(response.body().data.list)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<PageUtils<YztSktVO>>>?) {
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
            }
        })
    }

    override fun getSktPolygon(points: String) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<MutableList<YztSktVO>>>(ApiConstants.POINT_GET_TG_KXHZ ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<MutableList<YztSktVO>>>(){
            override fun onStart(request: Request<BaseRespose<MutableList<YztSktVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<MutableList<YztSktVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonSkt(response.body().data)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<MutableList<YztSktVO>>>?) {
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
            }
        })
    }

    override fun getQsPolygonDetail(points: String, pageSize: Int, currPage: Int) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        jsonObject.put("limit", pageSize)
        jsonObject.put("page", currPage)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<PageUtils<QsVO>>>(ApiConstants.POINT_GET_QS_QUERY_FRAME ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<PageUtils<QsVO>>>(){
            override fun onStart(request: Request<BaseRespose<PageUtils<QsVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<QsVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonDetailQs(response.body().data.list)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<PageUtils<QsVO>>>?) {
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
            }
        })
    }
    override fun getQsPolygon(points: String) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<MutableList<QsVO>>>(ApiConstants.POINT_GET_QS_KXHZ ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<MutableList<QsVO>>>(){
            override fun onStart(request: Request<BaseRespose<MutableList<QsVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<MutableList<QsVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonQs(response.body().data)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<MutableList<QsVO>>>?) {
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
            }
        })
    }

    override fun getGhPolygonDetail(points: String, pageSize: Int, currPage: Int) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        jsonObject.put("limit", pageSize)
        jsonObject.put("page", currPage)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<PageUtils<GhVO>>>(ApiConstants.POINT_GET_GH_QUERY_FRAME ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<PageUtils<GhVO>>>(){
            override fun onStart(request: Request<BaseRespose<PageUtils<GhVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<PageUtils<GhVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonDetailGh(response.body().data.list)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<PageUtils<GhVO>>>?) {
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
            }
        })
    }
    override fun getGhPolygon(points: String) {
        var jsonObject = JSONObject()
        jsonObject.put("point", points)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<MutableList<GhVO>>>(ApiConstants.POINT_GET_GH_KXHZ ).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<MutableList<GhVO>>>(){
            override fun onStart(request: Request<BaseRespose<MutableList<GhVO>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<MutableList<GhVO>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onPolygonGh(response.body().data)
                            }else{
                                mView?.showErrorTip("")
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
            override fun onError(response: Response<BaseRespose<MutableList<GhVO>>>?) {
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
            }
        })
    }

    override fun getBcjcGetTdly(code: String,year: String) {
        var jsonObject = JSONObject()
        jsonObject.put("code", code)
        if (!year.equals("")){
            jsonObject.put("year", year)
        }
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        OkGo.post<BaseRespose<BcTdlyEntity>>(ApiConstants.BCJC_GETTDLY).upJson(jsonObject).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<BcTdlyEntity>>(){
            override fun onStart(request: Request<BaseRespose<BcTdlyEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<BcTdlyEntity>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.onBcjcGetTdly(response.body().data)
                            }else{
                                mView?.onBcjcGetTdly(BcTdlyEntity())
                            }
                        }else{
                            mView?.onBcjcGetTdly(BcTdlyEntity())
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
                        mView?.onBcjcGetTdly(BcTdlyEntity())
                        mView?.showErrorTip("查询失败")
                    }
                }else{
                    mView?.onBcjcGetTdly(BcTdlyEntity())
                    mView?.showErrorTip("查询失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<BaseRespose<BcTdlyEntity>>?) {
                super.onError(response)
                mView?.onBcjcGetTdly(BcTdlyEntity())
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
            }
        })
    }
    override fun getBcjcTdlySave(bcTdlyEntity : BcTdlyEntity) {

        OkGo.post<BaseRespose<String>>(ApiConstants.BCJC_TDLYSAVE).upJson(Gson().toJson(bcTdlyEntity)).execute(object ://APPLY_BY_POINT1
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
                            mView?.onBcjcTdlySave("添加成功")
                        }else{
                            mView?.onBcjcTdlySave("添加失败")
                        }
                    }else{
                        mView?.onBcjcTdlySave("添加失败")
                    }
                }else{
                    mView?.onBcjcTdlySave("添加失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                mView.onBcjcTdlySave("添加失败")
                /*if (response!=null){
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
                }*/
            }
        })
    }
    override fun getBcjcTdlyUpdate(bcTdlyEntity : BcTdlyEntity) {
        val toJson = Gson().toJson(bcTdlyEntity)
        OkGo.post<BaseRespose<String>>(ApiConstants.BCJC_TDLY_UPDATE).upJson(toJson).execute(object ://APPLY_BY_POINT1
                BaseNet<BaseRespose<String>>(){
            override fun onStart(request: Request<BaseRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<BaseRespose<String>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (body!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onBcjcTdlyUpdate("修改成功")
                        }else{
                            mView?.onBcjcTdlyUpdate(body.getMsg())
                        }
                    }else{
                        mView?.onBcjcTdlyUpdate("修改失败")
                    }
                }else{
                    mView?.onBcjcTdlyUpdate("修改失败")
                }
                mView!!.stopLoading()
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                mView.onBcjcTdlyUpdate("修改失败")
                /*if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals(""))
                                mView.onBcjcTdlyUpdate(response.exception.message!!)
                            else
                                mView.onBcjcTdlyUpdate("修改失败")
                        }else{
                            mView.onBcjcTdlyUpdate("修改失败")
                        }
                    }else{
                        mView.onBcjcTdlyUpdate("修改失败")
                    }
                }*/
            }
        })
    }

    override fun getBcjcYears() {
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
                            mView.onBcjcYears(json.data)
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