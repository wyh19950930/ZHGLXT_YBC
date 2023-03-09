package com.jymj.zhglxt.xm.fragment


import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

import com.jymj.zhglxt.R
import com.jymj.zhglxt.xm.activity.XmIssueActivity
import com.jymj.zhglxt.xm.activity.XmMapActivity
import com.jymj.zhglxt.xm.activity.XmTzDetailActivity
import com.jymj.zhglxt.xm.adapter.XmFirstListAdapter
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.contract.XmFirstContract
import com.jymj.zhglxt.xm.contract.XmIssueContract
import com.jymj.zhglxt.xm.presenter.XmFirstPresenter
import com.jymj.zhglxt.xm.presenter.XmIssuePresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_my_news.*
import kotlinx.android.synthetic.main.fragment_xm_first.*
import kotlinx.android.synthetic.main.fragment_xm_first.include_act_my_news_zwsj

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class XmFirstFragment : BaseFragment<XmFirstPresenter, XmFirstContract.Model>(), XmFirstContract.View {

    var tabString = arrayOf("游", "娱", "居", "食")
    private var page = 1
    private var limit = 20
    private var xmlb = 1
    private var name = ""

    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_xm_first
    }

    override fun initPresenter() {//
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        tv_frag_frist_zsissue.setOnClickListener {//招商地图
            val intent = Intent(activity, XmMapActivity::class.java)
            startActivity(intent)
        }
        if (AppCache.getInstance().duties==1){
            tv_frag_frist_issue.visibility = View.GONE
        }else{
            tv_frag_frist_issue.visibility = View.VISIBLE
        }
        tv_frag_frist_issue.setOnClickListener {
            val  intent = Intent(activity,XmIssueActivity::class.java)
            startActivity(intent)
        }
        tv_frag_frist_address.text = AppCache.getInstance().xzqName
    }

    override fun initDatas() {
        for (i in tabString) {
            tab_frag_frist.addTab(tab_frag_frist.newTab().setText(i))
        }
        for (i in 0 until tab_frag_frist.tabCount) {
            tab_frag_frist.getTabAt(i)?.setCustomView(R.layout.tab_layout_text)
            val tv: TextView? = tab_frag_frist.getTabAt(i)?.customView?.findViewById(R.id.tab_text)
            tv?.text = tab_frag_frist.getTabAt(i)?.text
            tv?.setTextColor(resources.getColor(R.color.hui))
            tv?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        }

        val tv: TextView? = tab_frag_frist.getTabAt(0)?.customView?.findViewById(R.id.tab_text)
        tv?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        tv?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        tv?.setTextColor(resources.getColor(R.color.black))



        tab_frag_frist.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tv: TextView? = tab?.customView?.findViewById(R.id.tab_text)
                tv?.isSelected = false
                tv?.text = tab?.text
                tv?.setTextColor(resources.getColor(R.color.hui))
                tv?.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                tv?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                tv?.invalidate()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                xmlb = tab!!.position+1
                page = 1
                mPresenter.getXmList(page,limit,xmlb,name)

                val tv: TextView? = tab?.customView?.findViewById(R.id.tab_text)
                tv?.isSelected = true
                tv?.text = tab?.text
                tv?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                tv?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                tv?.setTextColor(resources.getColor(R.color.black))
                tv?.invalidate()


            }
        })

        et_frag_frist_search.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                name = s.toString()
                page = 1
                mPresenter.getXmList(page,limit,xmlb,name)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        et_frag_frist_search.setOnKeyListener(object :View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event!!.getAction() == KeyEvent.ACTION_DOWN){
                    page = 1
                    mPresenter.getXmList(page,limit,xmlb,name)
                    //隐藏软键盘
                    val imm= activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    if(imm.isActive()){
                        imm.hideSoftInputFromWindow(v!!.getApplicationWindowToken(), 0)
                    }
                    // 对应逻辑操作
                    return true
                }
                return false

            }
        })

        mPresenter.getXmList(page,limit,xmlb,name)
    }

    var lists = ArrayList<BcProjectEntity>()

    var linearLayoutManager: LinearLayoutManager? = null
    override fun returnXmList(message: List<BcProjectEntity>) {
        if (page == 1){
            lists.clear()
        }

            var firstVisibleItem = 0
            if (linearLayoutManager != null && page != 1) {
                firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
            }

            lists.addAll(message)
            linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            rlv_frag_xm_first.layoutManager = linearLayoutManager
            val adapter = XmFirstListAdapter(activity,lists)
            rlv_frag_xm_first.adapter = adapter
            adapter.setOnXmFirstListItemClick(object:XmFirstListAdapter.OnXmFirstListItemClick{
                override fun onClick(position: Int) {
                    val intent = Intent(activity,XmTzDetailActivity::class.java)
                    intent.putExtra("id",lists.get(position).id)
                    startActivity(intent)
                }

                override fun onClickLjcl(pjtaskFile: BcProjectEntity?) {

                }

            })
            rlv_frag_xm_first.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                    val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                    if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&lists.size==page*limit) {
                        if (lists.size !=0 && lists.size%limit == 0){
                            page++
                            mPresenter.getXmList(page,limit,xmlb,name)
                        }else{
                            ToastUtils.showShort("滑动到底部了")
                        }
                    }
                }
            })

            if (page!=1){
                rlv_frag_xm_first.scrollToPosition(firstVisibleItem+1)//(page-1)*limit+
            }

            if (lists.isNotEmpty()){
                include_act_my_news_zwsj.visibility = View.GONE
            }else{
                include_act_my_news_zwsj.visibility = View.VISIBLE
            }

    }

    override fun onResume() {
        super.onResume()
        if (AppCache.getInstance().isIssueXm){
            page = 1
            mPresenter.getXmList(page,limit,xmlb,name)
            AppCache.getInstance().isIssueXm = false

        }

    }
    override fun returnAddUser(message: String) {
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }


}
