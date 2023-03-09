package com.jymj.zhglxt.ui.activity

import android.content.Intent
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.app.HomeFunsAdapter
import com.jymj.zhglxt.home.activity.LdglFragment
import com.jymj.zhglxt.login.bean.MenuBean
import com.jymj.zhglxt.personal.activity.MeActivity
import com.jymj.zhglxt.rjhj.fragment.RjhjFirstFragment
import com.jymj.zhglxt.rjhj.fragment.RjhjFragment
import com.jymj.zhglxt.ui.fragment.Demo2Fragment
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.FragmentUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  BaseActivity<BasePresenter<*, *>, BaseModel>() {

    private var mHomeFunsAdapter: HomeFunsAdapter? = null
    var homefunsList = ArrayList<MenuBean>()
    private var isBackPressed: Boolean = false

    companion object {
        public var tvTitle: TextView? = null
        public var ibActMain: ImageView? = null
        public var mainTvFirst: ImageView? = null
        public var mainTvBack: ImageView? = null//左侧返回  用于人居环境问题列表页 返回首页
        public var mActivity: AppCompatActivity? = null
        public var rjhjFirstFragment: RjhjFirstFragment? = null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter() {
    }

    override fun initViews() {
        tvTitle = tv_title
        ibActMain = ib_act_main
        mainTvFirst = main_tv_first
        mainTvBack = main_tv_back
        mActivity = this
        rjhjFirstFragment = RjhjFirstFragment()
        val menus= AppMenus.getInstance().menus
        main_tv_first.setOnClickListener {
            dl_main?.openDrawer(Gravity.LEFT)
        }

        homefunsList.addAll(menus)

        menu_listView_l.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mHomeFunsAdapter = HomeFunsAdapter(R.layout.item_first_funs_list, homefunsList, this, supportFragmentManager, dl_main)
        menu_listView_l.adapter = mHomeFunsAdapter
        if (menus!=null&&menus.size>0){
            if (menus.get(0).name.contains("流动人口")){
                FragmentUtils.addFragment(supportFragmentManager, LdglFragment()!!,R.id.fl_content)//LdglFragment
            }else if (menus.get(0).name.contains("人居环境")){
                if (AppCache.getInstance().type == ApiConstants.LDRK_ZZF||AppCache.getInstance().type == ApiConstants.RJHJ_NY){//镇政府或内业都有首页
                    FragmentUtils.addFragment(supportFragmentManager, rjhjFirstFragment!!,R.id.fl_content)
                }else if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR||AppCache.getInstance().type == ApiConstants.RJHJ_WY){//村和外业用一个页面
                    FragmentUtils.addFragment(supportFragmentManager, RjhjFragment(),R.id.fl_content)
                }
            }else if (menus.get(0).name.contains("12345")){
                FragmentUtils.addFragment(supportFragmentManager, Demo2Fragment(),R.id.fl_content)
            }
        }
    }

    override fun initDatas() {

        ll_main_set.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()) {
                val intent = Intent(this, MeActivity::class.java)
                startActivity(intent)
            }
        }

    }
    /**
     * 监听手机自带的返回按键
     *//**//**//**/
    override fun onBackPressed() {
        if (isBackPressed) {
            isBackPressed = false
            super.onBackPressed()
        } else {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show()
            isBackPressed = true
            object : Thread() {
                override fun run() {
                    super.run()
                    try {
                        Thread.sleep(3000)
                        isBackPressed = false
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }.start()
        }
    }

}
