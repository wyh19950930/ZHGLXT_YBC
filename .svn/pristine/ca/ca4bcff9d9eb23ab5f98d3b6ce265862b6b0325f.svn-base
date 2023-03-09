package com.jymj.zhglxt.ui.fragment


import android.graphics.Color
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter

import com.jymj.zhglxt.R
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.fragment_homepage.*
import com.setsuna.common.commonutils.FragmentUtils
import java.text.DecimalFormat


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomePageFragment : BaseFragment<BasePresenter<*,*>, BaseModel>() ,View.OnClickListener{

   /* var layersType = 0
    var fragmentTransaction:FragmentTransaction?=null
    var zjdglFragment= ZjdglFragment()
    var demo2Fragment=MeFragment()*/

    companion object {

        public var tvFragHomepageTitle: TextView? = null
        public var ivFragHomepageLeft: ImageView? = null
        public var tvFragHomepageLeft: TextView? = null
        public var tvFragHomepageLeftBack: TextView? = null
        public var ivFragHomepageRight: ImageView? = null
        public var rlFragJomepageAllIcon: RelativeLayout? = null
    }
    override fun onClick(v: View?) {
        switchReactive(v)
        /*if (v!!.isActivated){
            when(v){
                bt_homepage_zjd->{
                    tv_frag_homepage_title.setText("宅基地管理")//zjdglFragment
                    FragmentUtils.replaceFragment(childFragmentManager,ZjdglFragment() , R.id.flt_homepage, false)

                }
                bt_homepage_cy->{
                    tv_frag_homepage_title.setText("产业发展")//demo2Fragment
                    FragmentUtils.replaceFragment(childFragmentManager,DemoFragment() , R.id.flt_homepage, false)

                }
            }
        }*/
    }

    override fun lazyLoad() {

    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_homepage
    }

    override fun initPresenter() {
    }

    override fun initViews() {
    }

    override fun initDatas() {
        bt_homepage_zjd.setOnClickListener(this)
        bt_homepage_cy.setOnClickListener(this)
        bt_homepage_yd.setOnClickListener(this)

        tvFragHomepageTitle = tv_frag_homepage_title
        ivFragHomepageLeft = iv_frag_homepage_left
        tvFragHomepageLeft = tv_frag_homepage_left
        tvFragHomepageLeftBack = tv_frag_homepage_left_back
        ivFragHomepageRight = iv_frag_homepage_right
        rlFragJomepageAllIcon = rl_all_icon_container
        bt_homepage_layer.setOnClickListener {
           /* if (layersType == 0){
                bt_homepage_layer.setImageResource(R.drawable.layers_w_icon)
                layersType = 1
            }else{
                bt_homepage_layer.setImageResource(R.drawable.layers_icon)
                layersType = 0
            }
            showLayers()*/
        }

        FragmentUtils.replaceFragment(childFragmentManager,ZjdglFragment() , R.id.flt_homepage, false)//zjdglFragment
        /*if (AppCache.getInstance().type == ApiConstants.RJHJ_NY||AppCache.getInstance().type == ApiConstants.LDRK_ZZF){
        }*/
//        FragmentUtils.replaceFragment(childFragmentManager, ZjdYztFragment() , R.id.flt_homepage, false)//zjdglFragment


    }


    /**
     * 设置当前选中的背景以及字体是否变色
     */
    private fun switchReactive(bt: View?) {
        if (bt!!.isActivated){
//            FragmentUtils.replaceFragment(childFragmentManager, ZjdYztFragment() , R.id.flt_homepage, false)//zjdglFragment
            bt!!.isActivated = !bt!!.isActivated
        }else{
            bt_homepage_zjd.isActivated = false
            bt_homepage_cy.isActivated = false
            bt_homepage_yd.isActivated = false
            bt!!.isActivated = true
        }
    }




}
