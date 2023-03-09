package com.jymj.zhglxt.zjd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.contract.YllbContract
import com.jymj.zhglxt.login.model.YllbModel
import com.jymj.zhglxt.login.presenter.YllbPresenter
import com.jymj.zhglxt.util.PopuPointUtils
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter1
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter2
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean
import com.jymj.zhglxt.zjd.contract.AjlbActContract
import com.jymj.zhglxt.zjd.contract.AjlbSearchActContract
import com.jymj.zhglxt.zjd.presenter.AjlbActPresenter
import com.jymj.zhglxt.zjd.presenter.AjlbSearchActPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_ajlb_search.*

class AjlbSearchActivity : BaseActivity<AjlbSearchActPresenter, AjlbSearchActContract.Model>(), AjlbSearchActContract.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_ajlb_search
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_ajlb_search_back.setOnClickListener {
            finish()
        }

    }

    override fun initDatas() {
        //*****************************分割线**********************************
        val arrayList1 = ArrayList<JsjbBean>()
        for (i in 0..PopuPointUtils.sysXzqEntities.size-1){
            arrayList1.add(JsjbBean(i,PopuPointUtils.sysXzqEntities.get(i).name,false))
        }
        val mAdapterxl = TagBaseAdapter(mContext, arrayList1) //createData(mListxl, xl)

        tcl_act_ajlb_search_sd.setAdapter(mAdapterxl)
        tcl_act_ajlb_search_sd.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
            override fun itemClick(position1: Int) {
                changeState(arrayList1, position1)
                mAdapterxl.notifyDataSetChanged()
            }
        })

        val arrayList2 = ArrayList<JsjbBean>()
        arrayList2.add(JsjbBean(0,"停车位",false))
        arrayList2.add(JsjbBean(1,"村务工作",false))
        arrayList2.add(JsjbBean(2,"强制拆迁",false))
        arrayList2.add(JsjbBean(3,"无照经营",false))
        val mAdapterx2 = TagBaseAdapter1(mContext, arrayList2) //createData(mListxl, xl)
        tcl_act_ajlb_search_ajlx.setAdapter(mAdapterx2)
        tcl_act_ajlb_search_ajlx.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
            override fun itemClick(position1: Int) {
                changeState(arrayList2, position1)
                mAdapterx2.notifyDataSetChanged()
            }
        })

        val arrayList3 = ArrayList<JsjbBean>()
        arrayList3.add(JsjbBean(0,"待签收",false))
        arrayList3.add(JsjbBean(1,"退单",false))
        arrayList3.add(JsjbBean(2,"待审核",false))
        arrayList3.add(JsjbBean(3,"待反馈",false))
        arrayList3.add(JsjbBean(4,"已归档",false))
        arrayList3.add(JsjbBean(5,"逾期",false))
        val mAdapterx3 = TagBaseAdapter2(mContext, arrayList3) //createData(mListxl, xl)
        tcl_act_ajlb_search_zt.setAdapter(mAdapterx3)
        tcl_act_ajlb_search_zt.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
            override fun itemClick(position1: Int) {
                changeState(arrayList3, position1)
                mAdapterx3.notifyDataSetChanged()
            }
        })

        tv_act_ajlb_search.setOnClickListener {
            var cunName = ""
            var ajlx = ""
            var zt = ""
            for (i in arrayList1){
                if (i.isCheck){
                    cunName += i.text+"、"
                }
            }
            for (i in arrayList2){
                if (i.isCheck){
                    ajlx += i.text+"、"
                }
            }
            for (i in arrayList3){
                if (i.isCheck){
                    zt += i.text+"、"
                }
            }
            val toString = cet_act_ajlb_search_ajmc.text.toString()
            ToastUtils.showShort("属地："+cunName+"类型："+ajlx+"状态："+zt)
        }
    }
    //    选择状态改变
    private fun changeState(list: List<JsjbBean>, position: Int) {
        if (list[position].isCheck()) {
            list[position].setCheck(false)
        } else {
            list[position].setCheck(true)
        }
    }
    override fun returnFanJianDetail(renovated: Renovated) {
        
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
