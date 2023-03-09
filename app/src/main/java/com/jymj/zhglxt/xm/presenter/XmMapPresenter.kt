package com.jymj.zhglxt.xm.presenter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.PageUtils
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.PointAndTypeEntity
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.bean.CjVO
import com.jymj.zhglxt.xm.bean.ProjectCountVO
import com.jymj.zhglxt.xm.contract.XmIssueContract
import com.jymj.zhglxt.xm.contract.XmMapContract
import com.jymj.zhglxt.zjd.bean.fj.FwglJhBean
import com.jymj.zhglxt.zjd.contract.AddUserContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseResposeUser
import com.setsuna.common.commonutils.ToastUtils
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.ArrayList

class XmMapPresenter : XmMapContract.Companion.Presenter(){

    override fun getByPoint(point: String) {//获取点位

        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        OkGo.post<BaseRespose<List<BcProjectEntity>>>(ApiConstants.PROJECT_GET_PROJECT_BYPOINT).upJson(jsonObject).execute(object ://
                BaseNet<BaseRespose<List<BcProjectEntity>>>(){
            override fun onStart(request: Request<BaseRespose<List<BcProjectEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<BaseRespose<List<BcProjectEntity>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){//&& response.body().data.ylEntity!=null
                            if (body.data!=null){
                                mView?.returnByPoint(body.data)
                            }else{
//                                ToastUtils.showShort("请选择项目点击")
                            }

                        }else{
                            mView?.showErrorTip(body.getMsg())
                        }
                    }else{
//                        mView?.showErrorTip("请选择项目点击")
                    }
                }else{
//                    mView?.showErrorTip("请选择项目点击")
                }
                mView!!.stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<List<BcProjectEntity>>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception!=null){
                        if (response.exception.message!=null){
                            if (!response.exception.message.equals("")){
//                                mView.showErrorTip("请选择项目点击")//response.exception.message
                            } else{
//                                mView.showErrorTip("请选择项目点击")
                            }
                        }else{
//                            mView.showErrorTip("请选择项目点击")
                        }

                    }else{
//                        mView.showErrorTip("请选择项目点击")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    }

    override fun getEnviorsByxy(type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, code:String) {//type  xmin  xmax  ymin  ymax
        val httpParams = JSONObject()
        httpParams.put("type",type.toInt())
        httpParams.put("xmax",xmax.toString())
        httpParams.put("xmin",xmin.toString())
        httpParams.put("ymin",ymin.toString())
        httpParams.put("ymax",ymax.toString())
        httpParams.put("code",code.toString())
//        if (!code.equals("")&&!code.equals("110112")&&code.length==9)//BaseRespose<List<FwglJhBean>>
        val toInt = type.toInt()
        val pointAndTypeEntity = PointAndTypeEntity()
        if (AppCache.getInstance().type==2&&toInt<2){
            pointAndTypeEntity.type = 2
        }else if (AppCache.getInstance().type==3&&toInt<3){
            pointAndTypeEntity.type = 3
        }else if (AppCache.getInstance().type==4&&toInt<4){
            pointAndTypeEntity.type = 4
        }else{
            pointAndTypeEntity.type = type.toInt()
        }
        pointAndTypeEntity.xmax = xmax.setScale(6, RoundingMode.HALF_UP)
        pointAndTypeEntity.xmin = xmin.setScale(6, RoundingMode.HALF_UP)
        pointAndTypeEntity.ymin = ymin.setScale(6, RoundingMode.HALF_UP)
        pointAndTypeEntity.ymax = ymax.setScale(6, RoundingMode.HALF_UP)

        pointAndTypeEntity.code = code//.upJson(Gson().toJson(pointAndTypeEntity))   .params(httpParams)
//        if (!code.equals("")&&!code.equals("110112")&&code.length==9)
        val toJson = Gson().toJson(pointAndTypeEntity)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.PROJECT_GET_BY_XY).upJson(sss).execute(
                object : BaseNet<String>(){//BaseRespose<List<FwglJhBean>>
                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                    super.onStart(request)
                }

                    override fun onSuccess(response: Response<String>?) {
                        val body = response?.body()
                        if (body != null){
                            val decrypt = AesEncryptUtils.decrypt(response.body())
                            val json: BaseRespose<List<ProjectCountVO>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<List<ProjectCountVO>>?>() {}.type)
                            if (json.code == 0){
                                if (json.data != null){
                                    mView.returnEnviorsByxy(json.data,type,xmax,xmin,ymin,ymax)
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