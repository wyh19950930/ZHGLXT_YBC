package com.jymj.zhglxt.zjd.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R.drawable.point
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.HousingEntity
import com.jymj.zhglxt.zjd.bean.wy.HousingRoomFile
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.PropertyAddFzContract
import com.jymj.zhglxt.zjd.contract.WwjContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

class PropertyAddFzPresenter : PropertyAddFzContract.Companion.Presenter(){
    override fun getPropertySaveOrUpdate(entity: HousingEntity) {
        val encrypt = AesEncryptUtils.encrypt(Gson().toJson(entity).toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.PROPERTY_SAVEORUPDATE).upJson(sss).execute(object ://
                BaseNet<String>(){//BaseRespose<List<ZhaiEntity>>
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
                        if (json.code==0){//&& response.body().data.ylEntity!=null
                            mView?.returnPropertySaveOrUpdate("修改成功")//json.data
                        }else{
                            mView?.showErrorTip(json.getMsg())
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

            override fun onError(response: Response<String>?) {
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

    override fun getEwmsmZjdBd(objectid: Long, qrcode: String) {
        val jsonObject = JSONObject()
        jsonObject.put("objectid", objectid)
        jsonObject.put("qrcode", qrcode)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.FLOW_SAVE_ZHAIRQ).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<YlFolwEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<YlFolwEntity>?>() {}.type)
                    when (json.code) {
                        0 -> {
                            mView.returnEwmsmZjdBd(json.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(json.msg)
                        }
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

    override fun getWwjPoint(point: String) {
        val jsonObject = JSONObject()
        jsonObject.put("point", point)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String> (ApiConstants.WUWEIJIAN_CLICKQUERY).upJson(sss).execute(object ://EnviorListEntity
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json: BaseRespose<WwjEntity> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<WwjEntity>?>() {}.type)
                    if (json.code==0){
                        if (json.data!=null){
                            mView.returnWwjPoint(json.data)
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

    //删除合同或院落照片
    override fun getHtYlFileDetel(ids:Long,position:Int){
//        val idss = "{\"ids\":[" + ids.toInt() + "]}"
        val idss = "[" + ids.toInt() + "]"

        val encrypt = AesEncryptUtils.encrypt(idss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.PROPERTY_DELETEFILEBYID).upJson(sss).execute(object :
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
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnHtYlFileDetel("删除成功",position)
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
                /*val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            if (cash.data.list.size>0){
                                mView.returnRjhjjcList(cash.data.list)
                            }else{
                                mView.showErrorTip("数据加载出错")
                            }
                        }else {
                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                    }
                }*/

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

    //删除房间照片
    override fun getRoomFileDetel(ids:Long, enterpriseFileEntity: HousingRoomFile, type:Int){
//        val idss = "{\"ids\":[" + ids.toInt() + "]}"
        val idss = "[" + ids.toInt() + "]"

        val encrypt = AesEncryptUtils.encrypt(idss.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.PROPERTY_DELETEROOMFILE).upJson(sss).execute(object :
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
                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            mView?.returnRoomFileDetel("删除成功",enterpriseFileEntity,type)
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
                /*val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    if (cash.code==0){
                        if (cash!!.data!=null){
                            if (cash.data.list.size>0){
                                mView.returnRjhjjcList(cash.data.list)
                            }else{
                                mView.showErrorTip("数据加载出错")
                            }
                        }else {
                            mView.showErrorTip("数据加载出错")
                        }
                    }else{
                        mView.showErrorTip(cash.msg)
                    }
                }*/

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
}