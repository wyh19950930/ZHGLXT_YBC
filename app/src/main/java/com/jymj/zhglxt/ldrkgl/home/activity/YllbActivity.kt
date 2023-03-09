package com.jymj.zhglxt.home.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ldrkgl.home.activity.LdrkDetailActivity
import com.jymj.zhglxt.ldrkgl.home.adapter.YllbAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.contract.YllbContract
import com.jymj.zhglxt.login.model.YllbModel
import com.jymj.zhglxt.login.presenter.YllbPresenter
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.jymj.zhglxt.widget.ClearEditText
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_yllb.*


class YllbActivity : BaseActivity<YllbPresenter, YllbModel>(), YllbContract.View {

    var mphName = ""
    var stringExtra = ""
    var limit = 20
    var page = 1
    private var mPopLcTips: CommenPop? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_yllb
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        val code = intent.getStringExtra("code")
        val xzqmc = intent.getStringExtra("xzqmc")
        if (code==null){
            stringExtra = AppCache.getInstance().code
        }else{
            stringExtra = code
        }
        AppCache.getInstance().fwRefresh = 0//默认刷新等于0  不刷新
        if (xzqmc!=null){
            tv_act_yllb_title.setText(xzqmc+"出租房列表")
        }
        iv_act_yllb_back.setOnClickListener {//返回上一页
            finish()
        }
        tv_act_yllb_search.setOnClickListener {//搜索
            mphName = cet_act_yllb_cm_lgy.text.toString()
            page = 1
            flowInfoList.clear()
            mPresenter.getCxldry(stringExtra,mphName,mphName,limit,page)
        }

        cet_act_yllb_cm_lgy.setOnDeleteTextClick(object : ClearEditText.OnDeleteTextClick {
            override fun onClick() {
                mphName = cet_act_yllb_cm_lgy.text.toString()
                flowInfoList.clear()
                mPresenter.getCxldry(stringExtra,mphName,mphName,limit,page)
            }
        })
    }

    override fun initDatas() {
        mPresenter.getCxldry(stringExtra,mphName,mphName,limit,page)

    }
    private var flowInfoList = ArrayList<YlFolwEntity>()
    var linearLayoutManager: LinearLayoutManager? = null
    override fun returnCxldry(msg: List<YlFolwEntity>) {
        var firstVisibleItem = 0
        if (linearLayoutManager!=null){
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        flowInfoList.addAll(msg)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_act_yllb.layoutManager = linearLayoutManager
        val zlglAdapter = YllbAdapter(this, flowInfoList)
        rlv_act_yllb.adapter =zlglAdapter
        zlglAdapter.setOnItemClick(object:YllbAdapter.OnYLLBItemClick{

            override fun onClick(pjtaskFile: YlFolwEntity?) {//点查
                if (SingleOnClickUtil.isFastClick()){
                    AppCache.getInstance().fwRefresh=1
                    val intent = Intent(this@YllbActivity, LdrkDetailActivity::class.java)
                    intent.putExtra("ylId", pjtaskFile?.objectid)
                    startActivity(intent)
//                    mPresenter.getDcylxx("", pjtaskFile!!.objectid)
                }
            }
        })
        rlv_act_yllb.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&flowInfoList.size==page*limit) {
                    if (flowInfoList.size !=0 && flowInfoList.size%limit == 0){
                        page++
                        mPresenter.getCxldry(stringExtra,mphName,mphName,limit,page)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_act_yllb.scrollToPosition(firstVisibleItem+1)//(page-1)*limit
        }

        if (flowInfoList.isNotEmpty()){
            include_act_yllb_zwsj.visibility = View.GONE
        }

    }

    override fun returnDcylxx(msg: YlFolwEntity) {

        /*AppCache.getInstance().fwRefresh=1

        val intent = Intent(this@YllbActivity, LdrkDetailActivity::class.java)
        intent.putExtra("ylId",msg.objectid)
        startActivity(intent)*/
    }

    override fun returnLcldry(msg: String) {
        ToastUtils.showShort(msg)
    }
    override fun onResume() {
        super.onResume()
        if (AppCache.getInstance().isFwlb){
            page = 1
            flowInfoList.clear()
            mPresenter.getCxldry(stringExtra,mphName,mphName,limit,page)
            AppCache.getInstance().isFwlb = false
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
