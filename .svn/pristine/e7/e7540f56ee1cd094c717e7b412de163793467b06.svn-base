package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.contract.RjhjFirstContract
import com.jymj.zhglxt.rjhj.bean.TownCountEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.SwitchTimeEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.TownTypeEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class RjhjFirstPresenter : RjhjFirstContract.Companion.Presenter() {

    override fun getDcylxx(point: String, objectid: Long) {

        mModel.getDcylxx(point, objectid).execute(object : BaseNet<BaseRespose<YlFolwEntity>>() {
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
                            mView.returnDcylxx(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
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

        })
    }

    //切换月季年
    override fun getQueryImproveRate(code: String) {

        mModel.getQueryImproveRate(code).execute(object ://RjhjPointBean
                BaseNet<SwitchTimeEntity>(){//

            override fun onStart(request: Request<SwitchTimeEntity, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<SwitchTimeEntity>?) {
                val cash = response?.body()
                if (cash!=null){
                    mView.returnQueryImproveRate(cash)
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<SwitchTimeEntity>?) {
                super.onError(response)
                mView.showErrorTip("请求失败")
            }

        })
    }

    //点击地图村点查
    override fun getByPoint(code: String, type: Int) {
        mModel.getByPoint(code,type).execute(object ://RjhjPointBean
                BaseNet<BaseRespose<TownCountEntity>>(){//
        override fun onStart(request: Request<BaseRespose<TownCountEntity>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
//                mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<BaseRespose<TownCountEntity>>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            mView.returnByPoint(cash.data)
                        }else {
//                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
//                        mView.showErrorTip(cash.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<TownCountEntity>>?) {
                super.onError(response)
                if (response?.message()!=null){
//                    mView.showErrorTip(response?.exception.message)
                }else{
//                    mView.showErrorTip("请求失败")
                }
            }

        })
    }
    override fun getRjhjjcMap(code: String,type: Int) {//code , type
        mModel.getRjhjjcMap(code,type).execute(object ://RjhjPointBean
                BaseNet<BaseRespose<List<TownCountEntity>>>(){//
        override fun onStart(request: Request<BaseRespose<List<TownCountEntity>>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<BaseRespose<List<TownCountEntity>>>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            mView.returnRjhjjcMap(cash.data)
                        }else {
//                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
//                        mView.showErrorTip(cash.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<List<TownCountEntity>>>?) {
                super.onError(response)
                if (response?.message()!=null){
//                    mView.showErrorTip(response?.exception.message)
                }else{
//                    mView.showErrorTip("请求失败")
                }
            }

        })
    }
    //问题分类柱状图
    override fun getTownType(code: String, type: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("code", code)
        jsonObject.put("type", type)
        OkGo.post<BaseRespose<TownTypeEntity>>(ApiConstants.TOWN_GETTOWNTYPE).upJson(jsonObject).execute(object ://BaseRespose<List<TownTypeEntity>>
                BaseNet<BaseRespose<TownTypeEntity>>(){//
        override fun onStart(request: Request<BaseRespose<TownTypeEntity>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("数据加载中...")
        }

            override fun onSuccess(response: Response<BaseRespose<TownTypeEntity>>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            mView.returnTownType(cash.data)
                        }else {
                            //                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        //                        mView.showErrorTip(cash.msg)
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<TownTypeEntity>>?) {
                super.onError(response)
                if (response?.message()!=null){
//                    mView.showErrorTip(response?.exception.message)
                }else{
//                    mView.showErrorTip("请求失败")
                }
            }

        })
    }

}