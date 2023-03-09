package com.jymj.zhglxt.ldrkgl.personal.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.contract.MyNewsContract
import com.jymj.zhglxt.login.model.MyNewsModel
import com.jymj.zhglxt.login.presenter.MyNewsPresenter
import com.jymj.zhglxt.ldrkgl.personal.adapter.MyNewsAdapter
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_my_news.*

class MyNewsActivity : BaseActivity<MyNewsPresenter, MyNewsModel>(), MyNewsContract.View {

    private var page = 1
    private var limit = 20
    var stringList = ArrayList<String>()
    override fun getLayoutId(): Int {
        return R.layout.activity_my_news
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_my_news_back.setOnClickListener {//返回上一页
            finish()
        }
    }

    override fun initDatas() {
        mPresenter.getMessageList(limit,page)


    }

    private var flowMessList = ArrayList<FlowMassageEntity>()
    var linearLayoutManager: LinearLayoutManager? = null
    override fun returnMessageList(msg: List<FlowMassageEntity>) {
        var firstVisibleItem = 0
        if (linearLayoutManager!=null&&page!=1){
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        flowMessList.addAll(msg)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_act_me_news_messlist.layoutManager = linearLayoutManager
        val myNewsAdapter = MyNewsAdapter(this, flowMessList)
        rlv_act_me_news_messlist.adapter = myNewsAdapter
        myNewsAdapter.setOnMyNewsItemClick(object :MyNewsAdapter.OnMyNewsItemClick {
            override fun onClickLjcl(pjtaskFile: FlowMassageEntity?) {
                if (SingleOnClickUtil.isFastClick()){
                    pjtaskFile?.dispose =1
                    mPresenter.getAddMessage(pjtaskFile!!)
                }
            }

            override fun onClick(pjtaskFile: String?) {
//                ToastUtils.showShort(pjtaskFile)
            }
        })
        rlv_act_me_news_messlist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&flowMessList.size==page*limit) {
                    if (flowMessList.size !=0 && flowMessList.size%limit == 0){
                        page++
                        mPresenter.getMessageList(limit,page)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_act_me_news_messlist.scrollToPosition(firstVisibleItem+1)//(page-1)*limit+
        }

        if (flowMessList.isNotEmpty()){
            include_act_my_news_zwsj.visibility = View.GONE
        }
    }
    override fun returnAddMessage(msg: String) {
        ToastUtils.showShort("处理完毕")
        flowMessList.clear()
        page = 1
        mPresenter.getMessageList(limit,page)
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
