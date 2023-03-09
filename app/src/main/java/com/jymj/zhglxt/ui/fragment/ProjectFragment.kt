package com.jymj.zhglxt.ui.fragment

import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.View

import com.jymj.zhglxt.R
import kotlinx.android.synthetic.main.fragment_project.*
import com.jymj.zhglxt.xm.fragment.XmJbqkFragment
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.commonutils.FragmentUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProjectFragment : BaseFragment<BasePresenter<*, *>, BaseModel>() ,View.OnClickListener{
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_project
    }

    override fun initPresenter() {
    }
    var xmJbqkFragment: XmJbqkFragment? = null
    override fun initViews() {

        iv_frag_project_right.setOnClickListener {
            dl_frag_project?.openDrawer(Gravity.RIGHT)
        }
        xmJbqkFragment = XmJbqkFragment()
        FragmentUtils.addFragment(childFragmentManager, xmJbqkFragment!!, R.id.ll_frag_project_fl_content)

    }
    fun setOnClickXz(){
        xmJbqkFragment!!.setVisible()
    }
    override fun initDatas() {
    }

    override fun onClick(v: View?) {
    }

}
