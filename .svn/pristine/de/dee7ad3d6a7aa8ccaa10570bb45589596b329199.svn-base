package com.jymj.zhglxt.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jymj.zhglxt.R
import kotlinx.android.synthetic.main.activity_shopping_mall_web.*

class ShoppingMallWebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_mall_web)

        iv_act_shopping_mall_back.setOnClickListener {
            finish()
        }

        webview_act_shopping_mall.loadUrl("https://shop116280011.youzan.com/v2/showcase/homepage?alias=aLINd6KKkg&dc_ps=3093067207794751491.300001")
        val webSettings = webview_act_shopping_mall.settings
        webSettings.setAppCacheEnabled(false)
        webSettings.javaScriptEnabled = true

        webview_act_shopping_mall.webViewClient = object : WebViewClient(){

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

        }

    }
}
