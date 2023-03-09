package com.jymj.zhglxt.rjhj.presenter

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.contract.RjhjContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.BccyjgEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposeDate
import org.json.JSONObject
import java.math.BigDecimal

/**
 * @Author:         Mr.haozi
 * @CreateDate:     2022/3/9 14:10
 */
class RjhjPresenter : RjhjContract.Companion.Presenter(){
    override fun getBcjcUpdateCyjg(edtity: List<BccyjgEntity>) {
        mModel.getBchcUpdateCyjg(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBcjcUpdateCyjg(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    //返回全部
    override fun getBcjcCyjgAll(code: String, years: List<String>) {

        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("years", years)
        var ss = "{\"code\":${Gson().toJson(code)},\"years\":${Gson().toJson(years)}}"
        val encrypt = AesEncryptUtils.encrypt(ss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"

//        mModel.getBcjcCyjg(code,years)0
        OkGo.post<String> (ApiConstants.BCJC_GETSAVE).upJson(sss).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<HashMap<String,ArrayList<BccyjgEntity>>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<HashMap<String,ArrayList<BccyjgEntity>>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBchcCyjgAll(json.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getBcjcCyjg(code: String, years: List<String>) {
        mModel.getBcjcCyjg(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<HashMap<String,ArrayList<BccyjgEntity>>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<HashMap<String,ArrayList<BccyjgEntity>>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            if (json.data!=null){
                                val zycpList = json.data.get("zycp")
                                val czList = json.data.get("cz")
                                val xnbcjyList = json.data.get("xnbcjy")
                                val xnwljyList = json.data.get("xnwljy")
                                val arrayList1 = ArrayList<BccyjgEntity>()
                                if (zycpList!=null)
                                arrayList1.addAll(zycpList!!)
                                if (czList!=null)
                                arrayList1.addAll(czList!!)
                                if (xnbcjyList!=null)
                                arrayList1.addAll(xnbcjyList!!)
                                if (xnwljyList!=null)
                                arrayList1.addAll(xnwljyList!!)
                                if (arrayList1.size==0){
                                    val arrayList = ArrayList<BccyjgEntity>()
                                    arrayList.add(BccyjgEntity())
                                    val hashMap = HashMap<String, ArrayList<BccyjgEntity>>()
                                    hashMap.put("zycp",arrayList)
                                    hashMap.put("cz",arrayList)
                                    hashMap.put("xnbcjy",arrayList)
                                    hashMap.put("xnwljy",arrayList)
                                    mView.returnBchcCyjg(hashMap)
                                }else{
                                    mView.returnBchcCyjg(json.data)
                                }
                            }else{
                                val arrayList = ArrayList<BccyjgEntity>()
                                arrayList.add(BccyjgEntity())
                                val hashMap = HashMap<String, ArrayList<BccyjgEntity>>()
                                hashMap.put("zycp",arrayList)
                                hashMap.put("cz",arrayList)
                                hashMap.put("xnbcjy",arrayList)
                                hashMap.put("xnwljy",arrayList)
                                mView.returnBchcCyjg(hashMap)
                            }
                        }
                        500 -> {
                            val arrayList = ArrayList<BccyjgEntity>()
                            arrayList.add(BccyjgEntity())
                            val hashMap = HashMap<String, ArrayList<BccyjgEntity>>()
                            hashMap.put("zycp",arrayList)
                            hashMap.put("cz",arrayList)
                            hashMap.put("xnbcjy",arrayList)
                            hashMap.put("xnwljy",arrayList)
                            mView.returnBchcCyjg(hashMap)
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            val arrayList = ArrayList<BccyjgEntity>()
                            arrayList.add(BccyjgEntity())
                            val hashMap = HashMap<String, ArrayList<BccyjgEntity>>()
                            hashMap.put("zycp",arrayList)
                            hashMap.put("cz",arrayList)
                            hashMap.put("xnbcjy",arrayList)
                            hashMap.put("xnwljy",arrayList)
                            mView.returnBchcCyjg(hashMap)
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                val arrayList = ArrayList<BccyjgEntity>()
                arrayList.add(BccyjgEntity())
                val hashMap = HashMap<String, ArrayList<BccyjgEntity>>()
                hashMap.put("zycp",arrayList)
                hashMap.put("cz",arrayList)
                hashMap.put("xnbcjy",arrayList)
                hashMap.put("xnwljy",arrayList)
                mView.showErrorTip(response?.exception?.message)
                mView.returnBchcCyjg(hashMap)
            }

        })
    }

    override fun getBcjcAddCyjg(edtity: List<BccyjgEntity>) {
        mModel.getBchcAddCyjg(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBcjcAddCyjg(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getBcjcYears() {
        mModel.getBcjcYears().execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<String>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<String>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBcjcYears(json.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getHbjcList(roleid: Int, limit: Int, page: Int, code: String, hjzzsj: Int, date: String, no: Int, zdwtCha: Int, hjzzlx: Int) {
        mModel.getHbjcList(roleid, limit, page, code, hjzzsj, date, no, zdwtCha, hjzzlx).execute(
                object : BaseNet<String>() {//EnviorListEntity

            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: EnviorListEntity = Gson().fromJson(decrypt, object : TypeToken<EnviorListEntity?>() {}.type)
                    when (json.code) {
                        0 -> {
                            if (json.data!=null){
                                mView.returnHbjcList(json.data.list,roleid,json.data.totalCount)
                            }
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }
        })
    }

    override fun getEnviorSupvsQueryPoint(point: String) {
        mModel.getEnviorSupvsQueryPoint(point).execute(object ://RjhjPointBean
                BaseNet<String>(){//BaseRespose<WtlrBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<WtlrBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<WtlrBean>?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null){
                            mView.returnEnviorSupvsQueryPoint(json!!.data)
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
                if (response?.message()!=null){
                    mView.showErrorTip(response?.exception.message)
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

        })
    }

    override fun getHbLr(enviorSupvsEntity: PjEnviorSupvsEntity){
        mModel.getHbLr(enviorSupvsEntity).execute(object :
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
//                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onHbLr("添加成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
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

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception.message!=null){
                        if (!response.exception.message.equals(""))
                            mView.showErrorTip(response.exception.message)
                        else
                            mView.showErrorTip("添加失败")
                    }else{
                        mView.showErrorTip("添加失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    //获取村庄
    override fun getQueryCun(name:String, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText) {
        mModel.getQueryCun(name).execute(object ://httpParams  .upJson("{\"name\":\""+name+"\"}")
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
    override fun getCountCyCode(code: String,hjzzlx: Int,gddwid: String,date: String,
                                roleid: Int,hjzzsj: Int,no: Int,zdwtCha: Int) {//countByCode
        mModel.getCountCyCode(code,hjzzlx,gddwid,date,roleid,hjzzsj,no,zdwtCha).execute(object : BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {//BaseRespose<EnviorCunBean.DataBean>
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<EnviorCunBean.DataBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<EnviorCunBean.DataBean>?>() {}.type)
                    if (json.code == 0){
                        if (json.data != null){
                            mView.returnCountCyCode(json.data)
                        }else {
                            mView.returnCountCyCode(EnviorCunBean.DataBean())
                            mView.showErrorTip("数据为空")
                        }
                    }
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

    override fun getHbjcZl(date:String,startDate:String,endDate:String,code:String){
       mModel.getHbjcZl(date,startDate,endDate,code).execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: EnviorCunBean = Gson().fromJson(decrypt, object : TypeToken<EnviorCunBean?>() {}.type)

//                    val decrypt = AesEncryptUtils.decrypt(response.body())
//                                T fromJson = new Gson().fromJson(s, T);
//                    val json: EnviorCunBean = Gson().fromJson(body, object : TypeToken<EnviorCunBean?>() {}.type)
                    if (json.data != null) {
                        val data = json.data
                        mView.onHbjcZl(data)
                    }else{
                        mView.showErrorTip("暂无数据")
                    }
                }else{
                    mView.showErrorTip("网络错误")
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

    override fun getRjhjjcPoint(point: String) {
        mModel.getRjhjjcPoint(point).execute(object ://RjhjPointBean
                BaseNet<String>(){//RjhjPointBean
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: RjhjPointBean = Gson().fromJson(decrypt, object : TypeToken<RjhjPointBean?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null||json!!.pointRecordEntity!=null){
                            mView.returnRjhjjcPoint(json)
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
                if (response?.message()!=null){
                    mView.showErrorTip(response?.exception.message)
                }else{
                    mView.showErrorTip("请求失败")
                }
            }

        })
    }

    override fun getDdUpdate(enviorSupvsEntity: PointRecordEntity){
        mModel.getDdUpdate(enviorSupvsEntity).execute(object :
                BaseNet<String>(){//BaseRespose<String>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
//                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onDdLr("修改成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
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

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("修改失败")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getDdLr(enviorSupvsEntity: PointRecordEntity){
        mModel.getDdLr(enviorSupvsEntity).execute(object :
                BaseNet<String>(){//BaseRespose<String>`
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
//                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onDdLr("添加成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
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

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("网络错误")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getDdDetel(ids:String){
        mModel.getDdDetel(ids).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.onDdLr("删除成功")
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("删除失败")
                    }
                }else{
                    mView?.showErrorTip("删除失败")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("网络错误")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    override fun getHbjcListCode(roleid: Int,limit:Int,page:Int, code:String,hjzzsj:Int,date:String,point:String,zdwtCha:Int,hjzzlx:Int){
        mModel.getHbjcListCode(roleid,limit,page,code,hjzzsj,date,point,zdwtCha,hjzzlx).execute(object :
                BaseNet<String>(){//EnviorListEntity
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }
            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: EnviorListEntity = Gson().fromJson(decrypt, object : TypeToken<EnviorListEntity?>() {}.type)
                    if (json.code==0){
                        if (json!!.data!=null){
                            mView.returnHbjcListCode(json.data.list,roleid,json.data.totalCount)
                        }else {
                            mView.showErrorTip("数据为空")
                            mView.stopLoading()
                        }
                    }else{
                        mView.showErrorTip(json.msg)
                        mView.stopLoading()
                    }
                }
//                mView.stopLoading()
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
                mView.stopLoading()
            }
        })
    }

    override fun getDdList(userId:String,page:Int){//limit page xzqmc  code
        mModel.getDdList(userId,page).execute(object :
                BaseNet<String>(){//BaseRespose<PageUtils<PointRecordEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: BaseRespose<PageUtils<PointRecordEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<PointRecordEntity>>?>() {}.type)
                        if (json.code==0 ){
                            mView?.onDdList(json.data.list)
                        }else{
                            mView?.showErrorTip(json.getMsg())
                        }
                    }else{
                        mView?.showErrorTip("添加失败")
                    }
                }else{
                    mView?.showErrorTip("请求出错")
                }
                mView!!.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        mView.showErrorTip(response.exception.message)
                    }else{
                        mView.showErrorTip("网络错误")
                    }
                }
//                mView.showErrorTip("网络错误")
            }

        })
    }
    //1  区  2镇  3 村  4 最小
    override fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage:Int, code:String, hjzzlx:Int) {//type  xmin  xmax  ymin  ymax
        mModel.getEnviorsByxy(type,xmin,xmax,ymin,ymax,isImage,code,hjzzlx).execute(
                object : BaseNet<String>(){//BaseRespose<List<EnviorFileFhEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<EnviorFileFhEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<EnviorFileFhEntity>>?>() {}.type)

                    if (json.code == 0){
                        if (json.data != null){
                            mView.returnEnviorFileQueryList(json.data,type,xmin,xmax,ymin,ymax,isImage)
                        }else {
                            mView.showErrorTip("数据为空")
                        }
                    }
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