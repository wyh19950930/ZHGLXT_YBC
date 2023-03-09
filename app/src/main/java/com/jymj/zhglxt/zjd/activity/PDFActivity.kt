package com.jymj.zhglxt.zjd.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jymj.zhglxt.R
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import es.voghdev.pdfviewpager.library.util.FileUtil
import kotlinx.android.synthetic.main.activity_pdf.*


class PDFActivity : AppCompatActivity() , DownloadFile.Listener {
    var remotePDFViewPager: RemotePDFViewPager? =null
    var ivActPdf: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
        val stringExtra = intent.getStringExtra("pdf")//iv_act_pdf
        val split = stringExtra.split(".")
        ivActPdf = findViewById<ImageView>(R.id.iv_act_pdf)
        if (split.size>1){
            if (split[split.size-1].equals("pdf")){
                ivActPdf!!.visibility = View.GONE
                pdfViewPager.visibility = View.VISIBLE
                LoadingDialog.showDialogForLoading(this)
                val listener: DownloadFile.Listener = this
                pdfViewPager.offscreenPageLimit = 10
                remotePDFViewPager = RemotePDFViewPager(this, stringExtra, listener)
                remotePDFViewPager!!.setId(R.id.pdfViewPager)
            }else{
                ivActPdf!!.visibility = View.VISIBLE
                pdfViewPager.visibility = View.GONE
                Glide.with(this).load(stringExtra).into(iv_act_pdf)
            }
        }

        /*iv_pdf_back.setOnClickListener {
            finish()
        }*/

//        remotePDFViewPager!!.requestDisallowInterceptTouchEvent(true)
        updateLayout()
        findViewById<ImageView>(R.id.iv_pdf_back)!!.setOnClickListener {
            finish()
        }

    }

    override fun onSuccess(url: String?, destinationPath: String?) {
        LoadingDialog.cancelDialogForLoading()
        var adapter = PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url))
        pdfViewPager.setAdapter(adapter)
    }

    override fun onFailure(e: Exception?) {
        LoadingDialog.cancelDialogForLoading()
        ToastUtils.showShort("加载失败")
    }
    /*更新视图*/
    private fun updateLayout() {
        /*pdf_root.removeAllViewsInLayout()
        pdf_root.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)*/
    }

    override fun onProgressUpdate(progress: Int, total: Int) {

    }
}
