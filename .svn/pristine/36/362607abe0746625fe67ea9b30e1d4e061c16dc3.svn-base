package com.jymj.zhglxt.zjd.activity.zjdgl

import android.view.View
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.zjd.bean.yzt.cs.MoveCost2
import com.jymj.zhglxt.zjd.bean.yzt.cs.MoveCostLevel0
import com.jymj.zhglxt.zjd.bean.yzt.cs.MoveCostLevel1
import com.jymj.zhglxt.zjd.bean.yzt.cs.MoveCostLevel2
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter

class CeSuanActivity : BaseActivity<BasePresenter<*, *>, BaseModel>() {
    private var data = ArrayList<MultiItemEntity>()


    override fun initPresenter() {
    }

    override fun initViews() {

    }

    override fun initDatas() {
        /*var list = intent.getSerializableExtra("data") as MutableList<MoveCost2>
        var xzDateEntity = intent.getSerializableExtra("xzDateEntity") as XzDateEntity
        var mCesuanType = intent.getSerializableExtra("mCesuanType") as Int
        if (mCesuanType == 3||mCesuanType == 4) {
            ll_ce_suan_fwlx.visibility = View.VISIBLE
            tv_base_data_dlszz_jzmj1.setText(xzDateEntity.dlzz34200.toString())
            tv_base_data_dlszz_xsdj1.setText("0")/////xzDateEntity.dlzz34200.toString()
            tv_base_data_dlszz_je1.setText("0")///////

            tv_base_data_dlszz_jzmj2.setText(xzDateEntity.dlzz3475n.toString())//dlzz3475n
            tv_base_data_dlszz_xsdj2.setText(xzDateEntity.dlzz3475ndj.toString())
            tv_base_data_dlszz_je2.setText(xzDateEntity.dlzz3475nje.toString())//

            tv_base_data_dlszz_jzmj3.setText(xzDateEntity.dlzz3475w.toString())
            tv_base_data_dlszz_xsdj3.setText(xzDateEntity.dlzz3475wdj.toString())
            tv_base_data_dlszz_je3.setText(xzDateEntity.dlzz3475wje.toString())

            tv_base_data_dlszz_jzmj4.setText(xzDateEntity.dlzz34xj.toString())
            tv_base_data_dlszz_xsdj4.setText("")/////xzDateEntity.zzldj.toString()
            tv_base_data_dlszz_je4.setText(xzDateEntity.dlzz34xjje.toString())

            tv_base_data_dlszz_jzmj5.setText(xzDateEntity.zzl.toString())
            tv_base_data_dlszz_xsdj5.setText(xzDateEntity.zzldj.toString())
            tv_base_data_dlszz_je5.setText(xzDateEntity.zzlje.toString())

            tv_base_data_dlszz_jzmj6.setText("")//////
            tv_base_data_dlszz_xsdj6.setText(xzDateEntity.zch.toString())/////
            tv_base_data_dlszz_je6.setText(xzDateEntity.zch.multiply(BigDecimal(100)).multiply(xzDateEntity.dlzz34200).toString()+"")////////这个暂时不对
        }else if (mCesuanType == 5){
            ll_ce_suan_fwlx2.visibility = View.VISIBLE
//            tv_base_data_bfgbf_jzmj1.setText(xzDateEntity.get)
            tv_base_data_bfgbf_jzmj1.setText(xzDateEntity.dlzz5200.toString())
            tv_base_data_bfgbf_xsdj1.setText("0")
            tv_base_data_bfgbf_je1.setText("0")

            tv_base_data_bfgbf_jzmj2.setText(xzDateEntity.dlzz575n.toString())
            tv_base_data_bfgbf_xsdj2.setText(xzDateEntity.dlzz575ndj.toString())
            tv_base_data_bfgbf_je2.setText(xzDateEntity.dlzz575nje.toString())

            tv_base_data_bfgbf_jzmj3.setText(xzDateEntity.dlzz575w.toString())
            tv_base_data_bfgbf_xsdj3.setText(xzDateEntity.dlzz575wdj.toString())//
            tv_base_data_bfgbf_je3.setText(xzDateEntity.dlzz575wje.toString())

            tv_base_data_bfgbf_jzmj4.setText(xzDateEntity.dlzz5xj.toString())
            tv_base_data_bfgbf_xsdj4.setText("0")
            tv_base_data_bfgbf_je4.setText(xzDateEntity.dlzz575wje.toString())

            tv_base_data_fhbf_jzmj1.setText(xzDateEntity.dlzz200.toString())
            tv_base_data_fhbf_xsdj1.setText("0")
            tv_base_data_fhbf_je1.setText("0")

            tv_base_data_fhbf_jzmj2.setText(xzDateEntity.dlzz75n.toString())
            tv_base_data_fhbf_xsdj2.setText(xzDateEntity.dlzz75ndj.toString())
            tv_base_data_fhbf_je2.setText(xzDateEntity.dlzz75nje.toString())

            tv_base_data_fhbf_jzmj3.setText(xzDateEntity.dlzz75w.toString())
            tv_base_data_fhbf_xsdj3.setText(xzDateEntity.dlzz75wdj.toString())
            tv_base_data_fhbf_je3.setText(xzDateEntity.dlzz75wje.toString())

            tv_base_data_fhbf_jzmj4.setText(xzDateEntity.dlzzxj.toString())
            tv_base_data_fhbf_xsdj4.setText("")
            tv_base_data_fhbf_je4.setText(xzDateEntity.dlzzxjje.toString())

            tv_base_data_hj_jzmj.setText(xzDateEntity.dlzzhj.toString())
            tv_base_data_hj_xsdj.setText("")
            tv_base_data_hj_je.setText(xzDateEntity.dlzzhjje.toString())

            tv_base_data_cmzzl_jzmj.setText(xzDateEntity.zzl.toString())
            tv_base_data_cmzzl_xsdj.setText(xzDateEntity.zzldj.toString())
            tv_base_data_cmzzl_je.setText(xzDateEntity.zzlje.toString())

            tv_base_data_cm_cjtzc_jzmj.setText("")
            tv_base_data_cm_cjtzc_xsdj.setText(xzDateEntity.zch.toString())
            tv_base_data_cm_cjtzc_je.setText(xzDateEntity.zch.multiply(BigDecimal(100)).multiply(xzDateEntity.dlzz34200).toString())///////这个暂时不对


        }
        if (mCesuanType==1){
            ll_cesuan_ms1.visibility = View.VISIBLE
        }else if (mCesuanType==2){
            ll_cesuan_ms2.visibility = View.VISIBLE
        }else if (mCesuanType==3){
            ll_cesuan_ms3.visibility = View.VISIBLE
        }else if (mCesuanType==4){
            ll_cesuan_ms4.visibility = View.VISIBLE
        }else if (mCesuanType==5){
            ll_cesuan_ms5.visibility = View.VISIBLE
        }
        dataFormat(list)

        recyCeSuan.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val ceSuanAdapter = CeSuanAdapter(this, data)
        recyCeSuan.adapter = ceSuanAdapter
        ceSuanAdapter.expandAll()
        recyCeSuan.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        clCeSuanPDF.layoutParams.height = ScreenUtils.dpToPx(this, (data.size + 1) * 41f).toInt()*/
    }

    private fun dataFormat(list: MutableList<MoveCost2>) {
        for (l in list) {
            if (l.count == "") {
                val level0 = MoveCostLevel0()
                level0.count = l.count
                level0.project = l.project
                level0.quantitie = l.quantitie
                level0.unit = l.unit
                level0.bilprice = l.bilprice
                level0.quaunit = l.quaunit
                level0.sumcost = l.sumcost
                level0.isHasChild = false
                data.add(level0)
            } else if (l.count.length <= 2) {
                var count = l.count
                val level0 = MoveCostLevel0()
                level0.count = count
                level0.project = l.project
                level0.quantitie = l.quantitie
                level0.unit = l.unit
                level0.bilprice = l.bilprice
                level0.quaunit = l.quaunit
                level0.sumcost = l.sumcost
                for (i in list) {
                    if (i.count != null) {
                        var count2 = i.count
                        val split = count2.split(".")
                        if (split.size == 2 && count2.startsWith(count)) {
                            val level1 = MoveCostLevel1()
                            level1.count = count2
                            level1.project = i.project
                            level1.quantitie = i.quantitie
                            level1.unit = i.unit
                            level1.bilprice = i.bilprice
                            level1.quaunit = i.quaunit
                            level1.sumcost = i.sumcost

                            for (s in list) {
                                if (s.count != null) {
                                    var count3 = s.count
                                    val split2 = count3.split(".")
                                    if (split2.size == 3 && count3.startsWith(count2)) {
                                        val level2 = MoveCostLevel2()
                                        level2.count = count3
                                        level2.project = s.project
                                        level2.quantitie = s.quantitie
                                        level2.unit = s.unit
                                        level2.bilprice = s.bilprice
                                        level2.quaunit = s.quaunit
                                        level2.sumcost = s.sumcost
                                        level1.isHasChild = true
                                        level1.addSubItem(level2)
                                    }
                                }
                            }
                            level0.isHasChild = true
                            level0.addSubItem(level1)
                        }
                    }
                }
                data.add(level0)
            }
        }
    }

    fun toPDF(view: View) {
      /*  val pdfPath=GetFileUtil.getSDCardPath()+"/jymj/"+AppUtils.getAppName()+"-测算表.pdf"
        val success = PDFUtil.createPdfFromView(clCeSuanPDF, clCeSuanPDF.measuredWidth, clCeSuanPDF.measuredHeight, pdfPath)
        if (success){
            val content = TextView(this@CeSuanActivity)
            content.text = "是否打开导出的文件？"
            SweetAlertDialog(this@CeSuanActivity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("打开文件")
                    .setCustomView(content)
                    .setConfirmButton("确定") { sweetAlertDialog ->
                        FileUtil.openFile(this@CeSuanActivity, File(pdfPath))
                    }
                    .setCancelButton(getString(R.string.cancel), { sweetAlertDialog ->
                        sweetAlertDialog?.dismiss()
                    })
                    .show()
        }else{
            ToastUtils.showLong("导出失败！")
        }*/
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_cesuan
    }
}
