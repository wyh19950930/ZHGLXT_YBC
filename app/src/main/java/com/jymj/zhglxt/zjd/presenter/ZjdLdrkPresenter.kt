package com.jymj.zhglxt.login.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.ZjdLdrkContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.bcjc.BccgbqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcldlbdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcrkbdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import java.math.BigDecimal

/**
 * Created by setsuna on 18-3-23.
 */
class ZjdLdrkPresenter : ZjdLdrkContract.Companion.Presenter() {
    override fun getBcjcUpdateWcwgbdqk(edtity: BcwcjybdqkEntity) {
        mModel.getBchcUpdateWcwgbdqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
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
                            mView.returnBcjcUpdateWcwgbdqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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

    override fun getBcjcUpdateLdlbdqk(edtity: BcldlbdqkEntity) {
        mModel.getBchcUpdateLdlbdqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
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
                            mView.returnBcjcUpdateLdlbdqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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

    override fun getBcjcUpdateRkbdqk(edtity: BcrkbdqkEntity) {
        mModel.getBchcUpdateRkbdqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
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
                            mView.returnBcjcUpdateRkbdqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    override fun getBchcRkbdqkAll(code: String, years: List<String>) {
        mModel.getBchcRkbdqk(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BcrkbdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcrkbdqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBchcRkbdqkAll(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    override fun getBchcLdlbdqkAll(code: String, years: List<String>) {
        mModel.getBchcLdlbdqk(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BcldlbdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcldlbdqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBchcLdlbdqkAll(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    override fun getBchcWcwgbdqkAll(code: String, years: List<String>) {
        mModel.getBchcWcwgbdqk(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BcwcjybdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcwcjybdqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBchcWcwgbdqkAll(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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

    override fun getBcjcCgbqk(code: String,year: String) {
        mModel.getBchcCgbqk(code,year).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BccgbqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BccgbqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBcjcCgbqk(json.data)
                        }
                        500 -> {
                            val arrayList = ArrayList<BccgbqkEntity>()
//                            arrayList.add(BccgbqkEntity())
                            mView.returnBcjcCgbqk(arrayList)
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
                        }
                        else -> {
                            val arrayList = ArrayList<BccgbqkEntity>()
//                            arrayList.add(BccgbqkEntity())
                            mView.returnBcjcCgbqk(arrayList)
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
                val arrayList = ArrayList<BccgbqkEntity>()
//                arrayList.add(BccgbqkEntity())
                mView.returnBcjcCgbqk(arrayList)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getBcjcAddCgbqk(edtity: BccgbqkEntity) {
        mModel.getBchcAddCgbqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
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
                            mView.returnBcjcAddCgbqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    override fun getBchcUpdateCgbqk(edtity: BccgbqkEntity) {
        mModel.getBchcUpdateCgbqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
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
                            mView.returnBcjcAddCgbqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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

    override fun getBcjcGetInwcry(code: String, years: List<String>) {
        mModel.getBcjcGetInwcry(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BcwcjybdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcwcjybdqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnBcjcGetInwcry(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    override fun getBchcWcwgbdqk(code: String, years: List<String>) {
        mModel.getBchcWcwgbdqk(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BcwcjybdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcwcjybdqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            if (json.data!=null){
                                if (json.data.size==0){
                                    val arrayList = ArrayList<BcwcjybdqkEntity>()
                                    arrayList.add(BcwcjybdqkEntity())
                                    mView.returnBchcWcwgbdqk(arrayList)
                                }else{
                                    mView.returnBchcWcwgbdqk(json.data)
                                }
                            }else{
                                val arrayList = ArrayList<BcwcjybdqkEntity>()
                                arrayList.add(BcwcjybdqkEntity())
                                mView.returnBchcWcwgbdqk(arrayList)
                            }
                        }
                        500 -> {
                            val arrayList = ArrayList<BcwcjybdqkEntity>()
                            arrayList.add(BcwcjybdqkEntity())
                            mView.returnBchcWcwgbdqk(arrayList)
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
                        }
                        else -> {
                            val arrayList = ArrayList<BcwcjybdqkEntity>()
                            arrayList.add(BcwcjybdqkEntity())
                            mView.returnBchcWcwgbdqk(arrayList)
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
                val arrayList = ArrayList<BcwcjybdqkEntity>()
                arrayList.add(BcwcjybdqkEntity())
                mView.returnBchcWcwgbdqk(arrayList)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getBcjcAddWcwgbdqk(edtity: BcwcjybdqkEntity) {
        mModel.getBchcAddWcwgbdqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
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
                            mView.returnBcjcAddWcwgbdqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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

    override fun getBchcLdlbdqk(code: String, years: List<String>) {
        mModel.getBchcLdlbdqk(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BcldlbdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcldlbdqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            if (json.data!=null){
                                if (json.data.size==0){
                                    val arrayList = ArrayList<BcldlbdqkEntity>()
                                    arrayList.add(BcldlbdqkEntity())
                                    mView.returnBchcLdlbdqk(arrayList)
                                }else{
                                    mView.returnBchcLdlbdqk(json.data)
                                }
                            }else{
                                val arrayList = ArrayList<BcldlbdqkEntity>()
                                arrayList.add(BcldlbdqkEntity())
                                mView.returnBchcLdlbdqk(arrayList)
                            }
                        }
                        500 -> {
                            val arrayList = ArrayList<BcldlbdqkEntity>()
                            arrayList.add(BcldlbdqkEntity())
                            mView.returnBchcLdlbdqk(arrayList)
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
                        }
                        else -> {
                            val arrayList = ArrayList<BcldlbdqkEntity>()
                            arrayList.add(BcldlbdqkEntity())
                            mView.returnBchcLdlbdqk(arrayList)
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
                val arrayList = ArrayList<BcldlbdqkEntity>()
                arrayList.add(BcldlbdqkEntity())
                mView.returnBchcLdlbdqk(arrayList)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getBcjcAddLdlbdqk(edtity: BcldlbdqkEntity) {
        mModel.getBchcAddLdlbdqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>
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
                            mView.returnBcjcAddLdlbdqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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

    override fun getBchcRkbdqk(code: String, years: List<String>) {
        mModel.getBchcRkbdqk(code,years).execute(object : BaseNet<String>() {//BaseRespose<Int>
        override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("正在登录。。。")
        }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<BcrkbdqkEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<BcrkbdqkEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            if (json.data!=null){
                                if (json.data.size==0){
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(BcrkbdqkEntity())
                                    mView.returnBchcRkbdqk(arrayList)
                                }else{
                                    mView.returnBchcRkbdqk(json.data)
                                }
                            }else{
                                val arrayList = ArrayList<BcrkbdqkEntity>()
                                arrayList.add(BcrkbdqkEntity())
                                mView.returnBchcRkbdqk(arrayList)
                            }
                        }
                        500 -> {
                            val arrayList = ArrayList<BcrkbdqkEntity>()
                            arrayList.add(BcrkbdqkEntity())
                            mView.returnBchcRkbdqk(arrayList)
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
                        }
                        else -> {
                            val arrayList = ArrayList<BcrkbdqkEntity>()
                            arrayList.add(BcrkbdqkEntity())
                            mView.returnBchcRkbdqk(arrayList)
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
                val arrayList = ArrayList<BcrkbdqkEntity>()
                arrayList.add(BcrkbdqkEntity())
                mView.returnBchcRkbdqk(arrayList)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getBcjcAddRkbdqk(edtity: List<BcrkbdqkEntity>) {//BcrkbdqkEntity
        mModel.getBchcAddRkbdqk(edtity).execute(object : BaseNet<String>() {//BaseRespose<Int>

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
                            mView.returnBcjcAddRkbdqk(json.msg)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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


    //未处理
    override fun getWclCount() {
        mModel.getWclCount().execute(object : BaseNet<String>() {//BaseRespose<Int>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<Int> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<Int>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnWclCount(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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

    //热力图  code  type  xmax  xmin  ymax  ymin
    override fun getRlt(code:String, type: Int, xmax:BigDecimal, xmin:BigDecimal, ymax:BigDecimal, ymin:BigDecimal) {
       /* mModel.getRlt(code,type,xmax.setScale(5,RoundingMode.HALF_UP),xmin.setScale(5,RoundingMode.HALF_UP)
                ,ymax.setScale(5,RoundingMode.HALF_UP),ymin.setScale(5,RoundingMode.HALF_UP)).execute(object : BaseNet<BaseRespose<List<XzqInfoEntity>>>() {
            override fun onStart(request: Request<BaseRespose<List<XzqInfoEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<List<XzqInfoEntity>>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnRlt(body.data,type)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip(body.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<List<XzqInfoEntity>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    */
    }
    //扫描二维码查找院落  qrcode
    override fun getSmewmcyl(qrcode:String) {
        /*mModel.getSmewmcyl(qrcode).execute(object : BaseNet<BaseRespose<YlFolwEntity>>() {
            override fun onStart(request: Request<BaseRespose<YlFolwEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<YlFolwEntity>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnSmewmcyl(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip(body.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<YlFolwEntity>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })*/

    }
    //流动人员列表   code:登陆账号所关联的code  name:人员名称   xzqmc:村名
    override fun getCxldry(code:String,name:String,xzqmc:String) {
        mModel.getCxldry(code,name,xzqmc).execute(object : BaseNet<String>() {//BaseRespose<PageUtils<YlFolwEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<YlFolwEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<YlFolwEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnCxldry(json.data.list,json.data.total)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    //查询村庄情况列表  lgyname:流管员名称   xzqmc:村名
    override fun getCxczqk(lgyname:String,xzqmc:String) {
        mModel.getCxczqk(lgyname,xzqmc).execute(object : BaseNet<String>() {//BaseRespose<PageUtils<XzqInfoEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<XzqInfoEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<XzqInfoEntity>>?>() {}.type)

                    when (json.code) {
                        0 -> {
                            mView.returnCxczqk(json.data.list)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    //点查院落信息
    override fun getDcylxx(point:String, objectId: Long?) {
        mModel.getDcylxx(point, objectId).execute(object : BaseNet<String>() {//BaseRespose<YlFolwEntity>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<YlFolwEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YlFolwEntity>?>() {}.type)

                    when (json.code) {
                        0 -> {
                            mView.returnDcylxx(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
                mView.showErrorTip("")//response?.exception?.message
            }

        })
    }
    //查询首页基本情况统计
    override fun getJbqktj() {
        mModel.getJbqktj(AppCache.getInstance().code).execute(object : BaseNet<String>() {//BaseRespose<FirstTjBean>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<FirstTjBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<FirstTjBean>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnJbqktj(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
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
    //查询首页行业类型统计
    override fun getHylxtj() {//code: String
        mModel.getHylxtj(AppCache.getInstance().code).execute(object : BaseNet<String>() {//BaseRespose<List<IndustryEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<List<IndustryEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<IndustryEntity>>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.changeUser(json.data)
                        }
                        500 -> {
                            mView.showErrorTip(json.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
                    }
                }
//                mView.changeUser(response?.body()?.msg!!)
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

    override fun getUserRequest(uName: String, uPwd: String, deviceId: String) {
        val params = mModel.login(uName, uPwd, deviceId)
        //LoginBean
        OkGo.post<LoginBean>(ApiConstants.LOGIN_URL).params(params).execute(object : BaseNet<LoginBean>() {
            override fun onStart(request: Request<LoginBean, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }

            override fun onSuccess(response: Response<LoginBean>?) {
                val loginBean = response?.body()
                if (loginBean != null) {
                    when (loginBean.code) {
                        0 -> {
                            AppCache.getInstance().token=loginBean.token
                            val user = loginBean.user
                            if (user != null) {
                                mView.returnUser(user)
                                //queryByCunEnviorSupvs
                                var sss="{}"
                                /*OkGo.post<String>(ApiConstants.queryByCunEnviorSupvs).upJson(sss).execute(object : BaseNet<String>(){
                                    override fun onSuccess(response: Response<String>?) {
                                        ToastUtils.showShort("")
                                    }
                                })*/
                            }
                        }
                        505 -> {
                            mView.showErrorTip(loginBean.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip(loginBean.msg)
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
            }
//java.net.UnknownServiceException: CLEARTEXT communication to 192.168.3.14 not permitted by network security policy
            override fun onFinish() {
                super.onFinish()
                mView.showLoading("")
            }
            override fun onError(response: Response<LoginBean>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }
        })
    }

}