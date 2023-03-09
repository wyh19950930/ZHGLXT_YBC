package com.jymj.zhglxt.zjd.activity.yzt

import android.content.Intent
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.util.PopuTjfxUtils
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean
import com.jymj.zhglxt.zjd.bean.yzt.TjfxDetailBean
import com.jymj.zhglxt.zjd.bean.yzt.layerListBean
import com.jymj.zhglxt.zjd.contract.TjfxActContract
import com.jymj.zhglxt.zjd.presenter.TjfxActPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_tjfx.*

class TjfxActivity : BaseActivity<TjfxActPresenter, TjfxActContract.Model>(), TjfxActContract.View {

    val arrayList1 = ArrayList<JsjbBean>()
    val qtList = ArrayList<layerListBean>()
    val sysXzqEntitys = ArrayList<SysXzqEntity>()

    var cunCode = ""
    var tcList = ArrayList<Int>()
    var tcList1 = ArrayList<Int>()

    override fun getLayoutId(): Int {
        return R.layout.activity_tjfx
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        ic_act_tjfx_back.setOnClickListener {
            finish()
        }
        tv_act_tjfx_yjfx.setOnClickListener {
            val cjString = et_act_tjfx_cj.text.toString()//村界
            val qzcsjString = et_act_qygl_detail_qzcsj.text.toString()//其他图层
            var tcString = ""//图层
            for (i in 0..arrayList1.size-1){
                val get = arrayList1.get(i)
                if (get.isCheck){
                    tcString += get.text+","
                }
            }
            if (!tcString.equals("")){
                tcString = tcString.substring(0,tcString.length-1)
            }
//            ToastUtils.showShort("村界：$cjString\n其他图层：$qzcsjString\n图层：$tcString")
            if (tcList1.size==0){
                ToastUtils.showShort("请选择图层")
            }else if (cunCode.equals("")){
                ToastUtils.showShort("请选择村界")
            }else{
                val intent = Intent(this, TjfxDetailActivity::class.java)
                intent.putExtra("cun",cjString)
                intent.putExtra("cunCode",cunCode)
                intent.putExtra("tcList1",tcList1)
                startActivity(intent)
            }

        }
    }

    override fun initDatas() {
        mPresenter.getSysXzqQueryXzqList(AppCache.getInstance().code,3)
        mPresenter.getSysXzqList()

    }

    override fun returnFanJianDetail(renovated: Renovated) {
        
    }

    override fun returnSysXzqQueryXzqList(renovated: ArrayList<SysXzqEntity>) {
        ll_act_tjfx_qylx.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()){
                PopuTjfxUtils.getInstance().initPopuPoint(this,ll_act_tjfx,renovated,et_act_tjfx_cj.text.toString(),object: PopuTjfxUtils.OnTjfxLinear{
                    override fun onClick(sysXzqEntities: MutableList<SysXzqEntity>?) {
                        var string = ""
                        for (i in sysXzqEntities!!){
                            if (i.isCheck){
                                string = string+i.name+","
                                cunCode = i.code
                            }
                        }
                        if (!string.equals("")){
                            string = string.substring(0,string.length-1)
                        }
                        et_act_tjfx_cj.setText(string)
//                    ToastUtils.showShort(string)
                    }
                })
            }
        }
    }
    override fun returnSysXzqList(renovated: List<layerListBean>) {
        arrayList1.clear()
        /*arrayList1.add(JsjbBean(0, "2009", false))
        arrayList1.add(JsjbBean(1, "2018", false))
        arrayList1.add(JsjbBean(2, "2020", false))*/
        for (i in 0..renovated.size-1){
            if (renovated.get(i).yztshow==2){
                qtList.add(renovated.get(i))
            }else if (renovated.get(i).yztshow==1){
                arrayList1.add(JsjbBean(renovated.get(i).id, renovated.get(i).title, false))//i
            }
        }
        val mAdapterxl = TagBaseAdapter(mContext, arrayList1) //createData(mListxl, xl)

        tcl_act_tjfx_tclb.setAdapter(mAdapterxl)
        tcl_act_tjfx_tclb.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
            override fun itemClick(position1: Int) {
                changeState(arrayList1, position1)
                mAdapterxl.notifyDataSetChanged()
            }
        })

        ll_act_tjfx_qttc.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()){
                PopuTjfxUtils.getInstance().initPopuQttc(this,ll_act_tjfx,qtList,object: PopuTjfxUtils.OnQttcLinear{
                    override fun onClick(sysXzqEntities: MutableList<layerListBean>?) {
                        tcList.clear()
                        var string = ""
                        for (i in sysXzqEntities!!){
                            if (i.isCheck){
                                tcList.add(i.id)
                                string = string+i.title+","
                            }
                        }
                        if (!string.equals("")){
                            string = string.substring(0,string.length-1)
                        }
                        et_act_qygl_detail_qzcsj.setText(string)
//                    ToastUtils.showShort(string)
                    }
                })
            }
        }
    }

    override fun returnTjfx(renovated: TjfxDetailBean) {

    }
    //    选择状态改变
    private fun changeState(list: List<JsjbBean>, position: Int) {
        if (list[position].isCheck()) {
            list[position].setCheck(false)
            tcList1.remove(list[position].id)
        } else {
            tcList1.add(list[position].id)
            list[position].setCheck(true)
        }
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