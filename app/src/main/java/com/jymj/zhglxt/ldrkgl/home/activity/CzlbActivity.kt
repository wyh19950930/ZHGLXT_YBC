package com.jymj.zhglxt.home.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ldrkgl.home.adapter.CzqkAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.FirstTjBean
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.login.contract.CzlbContract
import com.jymj.zhglxt.login.model.CzlbModel
import com.jymj.zhglxt.login.presenter.CzlbPresenter
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.jymj.zhglxt.widget.ClearEditText
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_czlb.*

/**
 * 流动人口村庄列表
 */

class CzlbActivity : BaseActivity<CzlbPresenter, CzlbModel>(), CzlbContract.View {
    override fun returnJbqktj(msg: FirstTjBean) {

    }

    private var position = 0
    private var cnLgy = ""
    override fun getLayoutId(): Int {
        return R.layout.activity_czlb
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

        iv_act_czlb_back.setOnClickListener { //返回
            finish()
        }
        tv_act_czlb_search.setOnClickListener {//搜索
            cnLgy = cet_act_czlb_cm_lgy.text.toString()
            mPresenter.getCxczqk(cnLgy,cnLgy)
        }
        cet_act_czlb_cm_lgy.setOnDeleteTextClick(object : ClearEditText.OnDeleteTextClick {
            override fun onClick() {
                cnLgy = cet_act_czlb_cm_lgy.text.toString()
                mPresenter.getCxczqk(cnLgy,cnLgy)
            }
        })
    }

    override fun initDatas() {
        mPresenter.getCxczqk(cnLgy,cnLgy)
    }

    //显示村庄列表
    override fun returnCxczqk(msg: List<XzqInfoEntity>) {
        rlv_act_czlb.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val zlglAdapter = CzqkAdapter(this, msg)
        rlv_act_czlb.adapter =zlglAdapter
        zlglAdapter.setOnItemClick(object: CzqkAdapter.OnItemClick{
            override fun onClick(pjtaskFile: XzqInfoEntity?) {
                if (SingleOnClickUtil1.isFastClick()){
                    position = msg.indexOf(pjtaskFile)
                    val intent = Intent(this@CzlbActivity, YllbActivity::class.java)
                    intent.putExtra("code", pjtaskFile?.code)
                    intent.putExtra("xzqmc", pjtaskFile?.xzqmc)
                    startActivity(intent)
                }
            }
        })
        rlv_act_czlb.scrollToPosition(position)

        if (msg.isNotEmpty()){
            include_act_czlb_zwsj.visibility = View.GONE
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

    override fun onResume() {
        super.onResume()
        if (AppCache.getInstance().isCzlb){
            AppCache.getInstance().isCzlb = false
            mPresenter.getCxczqk(cnLgy,cnLgy)
        }
    }

}
