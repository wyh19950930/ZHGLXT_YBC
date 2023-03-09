package com.jymj.zhglxt.zjd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity
import com.jymj.zhglxt.util.JyqkPickerUtil
import com.jymj.zhglxt.util.PopJyqkUtils
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.jymj.zhglxt.util.TimePickerUtil
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBusiness
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseEntity
import com.jymj.zhglxt.zjd.contract.QyglDetailActContract
import com.jymj.zhglxt.zjd.presenter.QyglDetailActPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_czdw_detail.*
import kotlinx.android.synthetic.main.activity_czdw_detail.*
import kotlinx.android.synthetic.main.activity_qygl_detail.*

class CzdwDetailActivity : BaseActivity<QyglDetailActPresenter, QyglDetailActContract.Model>(), QyglDetailActContract.View {

    var enterpriseEntity: EnterpriseEntity? = null
    var stringList = ArrayList<String>()
    var month = ""
//    var enterpriseEntity: EnterpriseEntity? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_czdw_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }
    override fun initViews() {
        enterpriseEntity = intent.getSerializableExtra("enterpriseEntity") as EnterpriseEntity
        var isUpdate = intent.getBooleanExtra("isUpdate",true)
        if (!isUpdate){
            tv_act_czdw_detail_delete.visibility = View.GONE//删除承租单位
            mtl_act_czdw_detail_zcqk.setIsVisibility(View.GONE)//修改注册情况
            but_act_czdw_detail_add.visibility = View.GONE//添加经营情况

        }
        mPresenter.getBusiness(enterpriseEntity!!.id, month)
        if (enterpriseEntity!=null){
            zel_act_czdw_detail_xzqymc1.setText(enterpriseEntity!!.qyname)
            zel_act_czdw_detail_zcqk.setText(enterpriseEntity!!.zcdz)
            zel_act_czdw_detail_sjjydz.setText(enterpriseEntity!!.sjjydz)
            ztl_act_czdw_detail_rzrq.setText(enterpriseEntity!!.rzdate)
            ztl_act_czdw_detail_yyzzzcrq.setText(enterpriseEntity!!.zcdate)
            zel_act_czdw_detail_swdjzcd.setText(enterpriseEntity!!.swzcdz)
            ztl_act_czdw_detail_qylx.setText(enterpriseEntity!!.qylxText)
            ztl_act_czdw_detail_hylx.setText(enterpriseEntity!!.hylxText)
            zel_act_czdw_detail_xzlxr.setText(enterpriseEntity!!.xzlxr)
            ztl_act_czdw_detail_sfzz.setText(if (enterpriseEntity!!.czqk==0)"否" else "是")
            ztl_act_czdw_detail_zclx.setText(enterpriseEntity!!.zclxText)
            zel_act_czdw_detail_zczj.setText(enterpriseEntity!!.zczj)
            zel_act_czdw_detail_lxdh.setText(enterpriseEntity!!.lxdh)
            ztl_act_czdw_detail_gxqy.setText(enterpriseEntity!!.gxqyText)
            val enterpriseBusinesse = enterpriseEntity!!.enterpriseBusinesse
            if (enterpriseBusinesse!=null){
                zel_act_czdw_detail_lrze.setText(enterpriseBusinesse.lrze.toString())
                zel_act_czdw_detail_ss.setText(enterpriseBusinesse.hj.toString())
                zel_act_czdw_detail_yysr.setText(enterpriseBusinesse.yysr.toString())
                zel_act_czdw_detail_cz.setText(enterpriseBusinesse.cz.toString())
                ztl_act_czdw_detail_jyzt.setText(enterpriseBusinesse.jyztText.toString())
                ztl_act_czdw_detail_bdygsl.setText(enterpriseBusinesse.bdgrs.toString())
                ztl_act_czdw_detail_wdygsl.setText(enterpriseBusinesse.wdgrs.toString())
                ztl_act_czdw_detail_jyrkjzqy1.setText(enterpriseBusinesse.dwss.toString())
                ztl_act_czdw_detail_jyrkjzqy2.setText(enterpriseBusinesse.bc.toString())
                ztl_act_czdw_detail_jyrkjzqy3.setText(enterpriseBusinesse.qt.toString())
            }
        }
        
        iv_act_czdw_detail_back.setOnClickListener {
            finish()
        }
        mtl_act_czdw_detail_zcqk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_act_czdw_detail_zcqk.isShown){
                        ll_act_czdw_detail_zcqk.visibility = View.GONE
                    }else{
                        ll_act_czdw_detail_zcqk.visibility = View.VISIBLE
                    }
                }else{//修改
                    PopJyqkUtils.initCzdwPoint(this@CzdwDetailActivity,ll_act_czdw_detail,enterpriseEntity,object: PopJyqkUtils.OnCzdwLinear{
                        override fun onClick(enterpriseBusiness: EnterpriseEntity?) {
                            zel_act_czdw_detail_xzqymc1.setText(enterpriseBusiness!!.qyname)
                            zel_act_czdw_detail_zcqk.setText(enterpriseBusiness!!.zcdz)
                            zel_act_czdw_detail_sjjydz.setText(enterpriseBusiness!!.sjjydz)
                            ztl_act_czdw_detail_rzrq.setText(enterpriseBusiness!!.rzdate)
                            ztl_act_czdw_detail_yyzzzcrq.setText(enterpriseBusiness!!.zcdate)
                            zel_act_czdw_detail_swdjzcd.setText(enterpriseBusiness!!.swzcdz)
                            ztl_act_czdw_detail_qylx.setText(enterpriseBusiness!!.qylxText)
                            ztl_act_czdw_detail_hylx.setText(enterpriseBusiness!!.hylxText)
                            zel_act_czdw_detail_xzlxr.setText(enterpriseBusiness!!.xzlxr)
                            ztl_act_czdw_detail_sfzz.setText(if (enterpriseBusiness!!.czqk==0)"否" else "是")
                            ztl_act_czdw_detail_zclx.setText(enterpriseBusiness!!.zclxText)
                            zel_act_czdw_detail_zczj.setText(enterpriseBusiness!!.zczj)
                            zel_act_czdw_detail_lxdh.setText(enterpriseBusiness!!.lxdh)
                            ztl_act_czdw_detail_gxqy.setText(enterpriseBusiness!!.gxqyText)
                            mPresenter.getSaveEnterprise(enterpriseBusiness!!)//修改注册情况
                        }
                    })
                }
            }
        })
        but_act_czdw_detail_add.setOnClickListener {
            PopJyqkUtils.initPopuPoint(this,ll_act_czdw_detail,object: PopJyqkUtils.OnJyqkLinear{
                override fun onClick(enterpriseBusiness: EnterpriseBusiness?) {
                    enterpriseBusiness!!.msid = enterpriseEntity!!.id
                    mPresenter.getSaveBusiness(enterpriseBusiness!!)//添加经营情况
//                    ToastUtils.showShort(Gson().toJson(enterpriseBusiness))
                }
            })
        }
        tv_act_czdw_detail_delete.setOnClickListener {
            val content = TextView(this)
            content.text = "是否删除？"
            SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("用地删除")
                    .setCustomView(content)
                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->

                        mPresenter.getEnterDeleteEnterprise(enterpriseEntity!!.id)
                        sweetAlertDialog.dismiss()}
                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                    .show()
        }
        /*ztl_act_czdw_detail_rzrq.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,ztl_act_czdw_detail_rzrq.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    //时间筛选
                    ztl_act_czdw_detail_rzrq.setText(data)
                }
            })
        }*/
        /*ztl_act_czdw_detail_yyzzzcrq.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,ztl_act_czdw_detail_yyzzzcrq.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) { //时间筛选
                    ztl_act_czdw_detail_yyzzzcrq.setText(data)
                }
            })
        }*/
        tv_act_czdw_detail_time.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()){
                JyqkPickerUtil.initPickerView(this@CzdwDetailActivity,"经营历史列表",stringList,tv_act_czdw_detail_time.text.toString(),object:JyqkPickerUtil.OnPickerLinerar{
                    override fun onClick(name: String?) {
                        month = name!!
                        tv_act_czdw_detail_time.setText(name)
                        mPresenter.getBusiness(enterpriseEntity!!.id, month)
                    }
                })
            }
        }

        /*ztl_act_czdw_detail_qylx.setOnClickListener {
            JyqkPickerUtil.initNationPickerView(this, "企业类型", ztl_act_czdw_detail_qylx)
        }
        ztl_act_czdw_detail_hylx.setOnClickListener {
            JyqkPickerUtil.initNationPickerView(this, "行业类型", ztl_act_czdw_detail_hylx)
        }
        ztl_act_czdw_detail_sfzz.setOnClickListener {
            JyqkPickerUtil.initNationPickerView(this, "是否", ztl_act_czdw_detail_sfzz)
        }
        ztl_act_czdw_detail_zclx.setOnClickListener {
            JyqkPickerUtil.initNationPickerView(this, "注册类型", ztl_act_czdw_detail_zclx)
        }
        ztl_act_czdw_detail_gxqy.setOnClickListener {
            JyqkPickerUtil.initNationPickerView(this, "高新企业", ztl_act_czdw_detail_gxqy)
        }*/

    }

    override fun initDatas() {
        
    }
    override fun returnBusiness(message: List<EnterpriseBusiness>,month:String) {
        if (month.equals("")){
            stringList.clear()
            for (i in message){
                if (!stringList.contains(i.createDate))
                stringList.add(i.createDate)
            }
        }
        if (message.size>0){
            val enterpriseBisiness = message.get(0)
            zel_act_czdw_detail_lrze.setText(enterpriseBisiness.lrze.toString())
            zel_act_czdw_detail_ss.setText(enterpriseBisiness.hj.toString())
            zel_act_czdw_detail_yysr.setText(enterpriseBisiness.yysr.toString())
            zel_act_czdw_detail_cz.setText(enterpriseBisiness.cz.toString())
            ztl_act_czdw_detail_jyzt.setText(enterpriseBisiness.jyztText.toString())
            ztl_act_czdw_detail_bdygsl.setText(enterpriseBisiness.bdgrs.toString())
            ztl_act_czdw_detail_wdygsl.setText(enterpriseBisiness.wdgrs.toString())
            ztl_act_czdw_detail_jyrkjzqy1.setText(enterpriseBisiness.dwss.toString())
            ztl_act_czdw_detail_jyrkjzqy2.setText(enterpriseBisiness.bc.toString())
            ztl_act_czdw_detail_jyrkjzqy3.setText(enterpriseBisiness.qt.toString())
        }
    }

    override fun returnSaveOrUpdate(message: String) {

    }

    override fun returnSaveEnterprise(message: String) {
//        ToastUtils.showShort("修改成功")
        AppCache.getInstance().isQyxqUpdate = 1
    }

    override fun returnSaveBusiness(message: EnterpriseBusiness) {
        month = ""
        mPresenter.getBusiness(enterpriseEntity!!.id, month)//id
        /*if (message!=null){
            zel_act_czdw_detail_lrze.setText(message.lrze.toString())
            zel_act_czdw_detail_ss.setText(message.hj.toString())
            zel_act_czdw_detail_yysr.setText(message.yysr.toString())
            zel_act_czdw_detail_cz.setText(message.cz.toString())
            ztl_act_czdw_detail_jyzt.setText(message.jyztText.toString())
            ztl_act_czdw_detail_bdygsl.setText(message.bdgrs.toString())
            ztl_act_czdw_detail_wdygsl.setText(message.wdgrs.toString())
            ztl_act_czdw_detail_jyrkjzqy1.setText(message.dwss.toString())
            ztl_act_czdw_detail_jyrkjzqy2.setText(message.bc.toString())
            ztl_act_czdw_detail_jyrkjzqy3.setText(message.qt.toString())
        }*/
        AppCache.getInstance().isQyxqUpdate = 1
    }

    override fun returnEnterDeleteFile(message: String) {

    }

    override fun returnEnterDelete(message: String) {


    }

    override fun returnEnterQueryInfo(message: EnterpriseBasisEntity) {

    }

    override fun returnDeleteEnterprise(message: String) {
        ToastUtils.showShort(message)
        AppCache.getInstance().isQyxqUpdate = 1
        finish()
    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }
}
