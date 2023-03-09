package com.jymj.zhglxt.xm.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jymj.zhglxt.R
import com.jymj.zhglxt.xm.adapter.XmFirstListAdapter
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.contract.ScListContract
import com.jymj.zhglxt.xm.contract.XmTzDetailContract
import com.jymj.zhglxt.xm.presenter.ScListPresenter
import com.jymj.zhglxt.xm.presenter.XmTzDetailPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_xm_sc_list.*
import kotlinx.android.synthetic.main.fragment_xm_first.*

class ScListActivity : BaseActivity<ScListPresenter, ScListContract.Model>(), ScListContract.View {

    private var page = 1
    private var limit = 20
    private var xmlb = 1
    private var name = ""
    override fun getLayoutId(): Int {
        return R.layout.activity_xm_sc_list
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_xm_sc_list_back.setOnClickListener {
            finish()
        }
    }

    override fun initDatas() {
        mPresenter.getProjectGetCallection(page,limit,xmlb,name)
        et_act_xm_sc_list_search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                name = s.toString()
                page = 1
                mPresenter.getProjectGetCallection(page,limit,xmlb,name)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        et_act_xm_sc_list_search.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event!!.getAction() == KeyEvent.ACTION_DOWN){
                    page = 1
                    mPresenter.getProjectGetCallection(page,limit,xmlb,name)
                    //隐藏软键盘
                    val imm= this@ScListActivity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    if(imm.isActive()){
                        imm.hideSoftInputFromWindow(v!!.getApplicationWindowToken(), 0)
                    }
                    // 对应逻辑操作
                    return true
                }
                return false

            }
        })

    }

    var linearLayoutManager: LinearLayoutManager? = null
    var lists = ArrayList<BcProjectEntity>()

    override fun returnProjectGetCallection(message: List<BcProjectEntity>) {

        if (page == 1){
            lists.clear()
        }

        var firstVisibleItem = 0
        if (linearLayoutManager != null && page != 1) {
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }

        lists.addAll(message)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_act_sc_list.layoutManager = linearLayoutManager
        val adapter = XmFirstListAdapter(this,lists)
        rlv_act_sc_list.adapter = adapter
        adapter.setOnXmFirstListItemClick(object: XmFirstListAdapter.OnXmFirstListItemClick{
            override fun onClick(position: Int) {
                val intent = Intent(this@ScListActivity,XmTzDetailActivity::class.java)
                intent.putExtra("id",lists.get(position).id)
                startActivity(intent)
            }

            override fun onClickLjcl(pjtaskFile: BcProjectEntity?) {

            }

        })

        rlv_act_sc_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&lists.size==page*limit) {
                    if (lists.size !=0 && lists.size%limit == 0){
                        page++
                        mPresenter.getProjectGetCallection(page,limit,xmlb,name)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_act_sc_list.scrollToPosition(firstVisibleItem+1)//(page-1)*limit+
        }

    }

    override fun onResume() {
        super.onResume()
        if (AppCache.getInstance().isQxSc){
            page = 1
            mPresenter.getProjectGetCallection(page,limit,xmlb,name)
            AppCache.getInstance().isQxSc = false

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
