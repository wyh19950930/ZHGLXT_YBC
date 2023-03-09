package com.jymj.zhglxt.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.jymj.zhglxt.R
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import kotlinx.android.synthetic.main.fragment_shopping_mall.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ShoppingMallFragment : BaseFragment<BasePresenter<*, *>, BaseModel>() ,View.OnClickListener{
    override fun lazyLoad() {

    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_shopping_mall
    }

    override fun initPresenter() {
    }

    override fun initViews() {
        /*frag_shoppingmall.loadUrl("https://shop116280011.youzan.com/v2/showcase/homepage?alias=aLINd6KKkg&dc_ps=3093067207794751491.300001")
        val webSettings = frag_shoppingmall.settings
        webSettings.setAppCacheEnabled(false)
        webSettings.javaScriptEnabled = true

        frag_shoppingmall.webViewClient = object : WebViewClient(){

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

        }*/
    }

    override fun initDatas() {
    }

    override fun onClick(v: View?) {
    }


}
