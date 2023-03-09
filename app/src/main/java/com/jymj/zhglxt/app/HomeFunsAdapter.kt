package com.jymj.zhglxt.app

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.home.activity.LdglFragment
import com.jymj.zhglxt.login.bean.MenuBean
import com.jymj.zhglxt.rjhj.fragment.RjhjFragment
import com.jymj.zhglxt.ui.activity.MainActivity
import com.jymj.zhglxt.ui.fragment.DemoFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.baseapp.BaseApplication
import com.setsuna.common.commonutils.FragmentUtils

class HomeFunsAdapter(layout: Int, datas: ArrayList<MenuBean>, context: MainActivity, fragmentManager: FragmentManager, drawerLayout: DrawerLayout) :
        BaseQuickAdapter<MenuBean, BaseViewHolder>(layout, datas) {
    private var context: Activity? = null
    private var data: ArrayList<MenuBean>? = null
    private var fragManager: FragmentManager? = null
    private var dl: DrawerLayout? = null

    init {
        this.data = datas
        this.context = context
        this.fragManager = fragmentManager
        this.dl = drawerLayout
    }

    //先声明一个int成员变量
    private var thisPosition: Int = 0

    //再定义一个int类型的返回值方法
    fun getthisPosition(): Int {
        return thisPosition
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    fun setThisPosition(thisPosition: Int) {
        this.thisPosition = thisPosition
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position == getthisPosition()) {
            holder.itemView.setBackgroundColor(Color.parseColor("#f5f6f7"))
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

    }

    override fun convert(helper: BaseViewHolder?, item: MenuBean?) {
        item?.isCheck = !item!!.isCheck
        if (item.name.contains("app")){
            helper!!.setText(R.id.tv_first_funs_list, item?.name.replace("app",""))
        }else{
            helper!!.setText(R.id.tv_first_funs_list, item?.name)
        }

        helper!!.addOnClickListener(R.id.background_first_funs_list)


        when (item?.name) {
            "流动人口app" -> {
                Glide.with(BaseApplication.getAppContext()).load(R.drawable.ic_launcher).into(helper.getView(R.id.iv_first_funs_list))
            }
            "人居环境app" -> {
                Glide.with(BaseApplication.getAppContext()).load(R.drawable.ic_launcher).into(helper.getView(R.id.iv_first_funs_list))
            }
            "12345app" -> {
                Glide.with(BaseApplication.getAppContext()).load(R.drawable.ic_launcher).into(helper.getView(R.id.iv_first_funs_list))
            }
        }
        //TODO 还没有看完
        if (fragManager != null) {

            helper.getView<LinearLayout>(R.id.background_first_funs_list).setOnClickListener {
                    val type = AppCache.getInstance().type
                setThisPosition(helper.layoutPosition)
                dl?.closeDrawer(Gravity.LEFT)
                when (item?.name) {
                    "流动人口app" -> {

                            FragmentUtils.replaceFragment(fragManager!!, LdglFragment(), R.id.fl_content, false)

                    }
                    "人居环境app" -> {
                        if (type == ApiConstants.LDRK_ZZF||type == ApiConstants.RJHJ_NY){//镇政府或内业都有首页
                            FragmentUtils.replaceFragment(fragManager!!, MainActivity.rjhjFirstFragment!!, R.id.fl_content, false)
                        }else if (type == ApiConstants.RJHJ_WYLR||type == ApiConstants.RJHJ_WY){//村和外业用一个页面
                            FragmentUtils.replaceFragment(fragManager!!, RjhjFragment(), R.id.fl_content, false)
                        }
                    }
                    "12345app" -> {
                        FragmentUtils.replaceFragment(fragManager!!, DemoFragment(), R.id.fl_content, false)
                    }
                }
            }
        }
    }
}