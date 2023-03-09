package com.jymj.zhglxt.zjd.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.zjd.contract.QyglDetailActContract
import com.jymj.zhglxt.zjd.presenter.QyglDetailActPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_qygl_detail.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import com.loc.r
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Environment
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.*
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.bean.enums.IndustryEnum
import com.jymj.zhglxt.bean.enums.IsTrueEnum
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.rjhj.bean.PjEnviorFileEntity
import com.jymj.zhglxt.rjhj.bean.yl.enums.RegistraEnum
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.widget.zdybj.ZdyEditLayout
import com.jymj.zhglxt.widget.zdybj.ZdyTextLayout
import com.jymj.zhglxt.zjd.adapter.QyglFileAdapter
import com.jymj.zhglxt.zjd.bean.cygl.*
import com.jymj.zhglxt.zjd.bean.qyzt.SccgEnum
import com.jymj.zhglxt.zjd.bean.qyzt.ScczEnum
import com.jymj.zhglxt.zjd.bean.qyzt.ScmjEnum
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.DisplayUtil
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fragment_zjd_fj.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import java.math.BigDecimal

class QyglDetailActivity : BaseActivity<QyglDetailActPresenter, QyglDetailActContract.Model>(), QyglDetailActContract.View {

    //    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    internal var tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath!! + "BMS/map/tdt/sta/")
    internal var opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true)
            .memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath!! + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    internal var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath!! + "BMS/map/tdt/note/")
    internal var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath!! + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    var tdtStaNomal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNomal/")
    var opt_tdtStaNomal = TileOverlayOptions().tileProvider(tdtStaNomal).diskCacheEnabled(false)
            .memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNomal/").diskCacheSize(10 * 1024)
    internal var tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath!! + "BMS/map/tdt/staNote/")
    internal var opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false)
            .memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath!! + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    private var aMap: AMap? = null
    private var mPolygon: Polygon? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var mMyLocationStyle: MyLocationStyle? = null
    private var id = 0L
    private var jyqkid = 0L
    private var xh = ""
    var polylines = ArrayList<Polyline>()
    private var mPolylineOptions: PolylineOptions? = null
    private var  addPolyline: Polyline? = null//高亮显示
    private var stringList = ArrayList<String>()
    private var isUpdate = false
    private var month=""

    override fun getLayoutId(): Int {
        return R.layout.activity_qygl_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//添加天地图

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_qygl_detail_act_dt.onCreate(intent.extras)
        initAMap()
        setAMap()
        var metric = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(metric)
        var height = metric.heightPixels   // 屏幕高度（像素）
        supl_qygl_detail_act.panelHeight = (height*0.65).toInt()//(height*0.7).toInt()//DisplayUtil.dip2px(50f)
        supl_qygl_detail_act.setScrollableView(slv_act_qygl_detail)

//        supl_qygl_detail_act.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        val enterpriseBasisEntity = intent.getSerializableExtra("enterpriseBasisEntity") as EnterpriseBasisEntity
        id = enterpriseBasisEntity.gid

        mPresenter.getEnterQueryInfo(id)
        tl_act_qygl_detail.addTab(tl_act_qygl_detail.newTab().setText("基本信息"))
        tl_act_qygl_detail.addTab(tl_act_qygl_detail.newTab().setText("企业信息"))
        tl_act_qygl_detail.addTab(tl_act_qygl_detail.newTab().setText("承租单位"))
        val linearlayout = tl_act_qygl_detail.getChildAt(0) as LinearLayout
        linearlayout.showDividers=LinearLayout.SHOW_DIVIDER_MIDDLE
        linearlayout.dividerDrawable = (getDrawable(R.drawable.layout_divider_vertical))
        linearlayout.dividerPadding=15//(15)
        tl_act_qygl_detail.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.text.toString()){
                   "基本信息" ->{
                       supl_qygl_detail_act.setScrollableView(slv_act_qygl_detail)
                       slv_act_qygl_detail.visibility = View.VISIBLE
                       slv_act_qygl_detail1.visibility = View.GONE
                       ll_act_qygl_detail_czdw.visibility = View.GONE
                       but_act_qygl_detail_addczdw.visibility = View.GONE
                   }
                   "企业信息" ->{
                       supl_qygl_detail_act.setScrollableView(slv_act_qygl_detail1)
                       slv_act_qygl_detail.visibility = View.GONE
                       slv_act_qygl_detail1.visibility = View.VISIBLE
                       ll_act_qygl_detail_czdw.visibility = View.GONE
                       but_act_qygl_detail_addczdw.visibility = View.GONE
                   }
                   "承租单位" ->{
                       supl_qygl_detail_act.setScrollableView(rlv_act_qygl_detail_czdwmc)
                       slv_act_qygl_detail.visibility = View.GONE
                       slv_act_qygl_detail1.visibility = View.GONE
                       ll_act_qygl_detail_czdw.visibility = View.VISIBLE
                       if (isUpdate){
                           but_act_qygl_detail_addczdw.visibility = View.VISIBLE
                       }
                   }
                }
            }
        })
        ic_act_qygl_detail_back.setOnClickListener {
            finish()
        }
        zel_act_qygl_detail_ydqs.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()){
                JyqkPickerUtil.initNationPickerView(this, "用地权属", zel_act_qygl_detail_ydqs)
            }
        }
        zel_act_qygl_detail_ywdxkj.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "有无地下空间", zel_act_qygl_detail_ywdxkj)
        }
        zel_act_qygl_detail_sffqjs.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "是否", zel_act_qygl_detail_sffqjs)
        }
        zel_act_qygl_detail_zrqk.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "是否", zel_act_qygl_detail_zrqk)
        }
        ztl_act_qygl_detail_qylx.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "企业类型", ztl_act_qygl_detail_qylx)
        }
        ztl_act_qygl_detail_hylx.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "行业类型", ztl_act_qygl_detail_hylx)
        }
        ztl_act_qygl_detail_sfzz.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "是否", ztl_act_qygl_detail_sfzz)
        }
        ztl_act_qygl_detail_zclx.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "注册类型", ztl_act_qygl_detail_zclx)
        }
        ztl_act_qygl_detail_gxqy.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "高新企业", ztl_act_qygl_detail_gxqy)
        }
        zel_act_qygl_detail_sccz.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "首层承重", zel_act_qygl_detail_sccz)
        }
        zel_act_qygl_detail_sccg.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "首层层高", zel_act_qygl_detail_sccg)
        }
        zel_act_qygl_detail_scmj.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            JyqkPickerUtil.initNationPickerView(this, "首层面积", zel_act_qygl_detail_scmj)
        }
        mtl_act_qygl_detail_jcqk.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_qygl_detail_jcqk.isShown){
                    ll_act_qygl_detail_jcqk.visibility = View.GONE
                }else{
                    ll_act_qygl_detail_jcqk.visibility = View.VISIBLE
                }
            }
        })
        mtl_act_qygl_detail_fwqk.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_qygl_detail_fwqk.isShown){
                    ll_act_qygl_detail_fwqk.visibility = View.GONE
                }else{
                    ll_act_qygl_detail_fwqk.visibility = View.VISIBLE
                }
            }
        })
        mtl_act_qygl_detail_ydqk.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("添加")){
                    if (SingleOnClickUtil1.isFastClick())
                        initYdqkPoint(EnterpriseInfoEntity(),0)
                        /*PopJyqkUtils.initYdqkPoint(this@QyglDetailActivity,EnterpriseInfoEntity(),ll_act_qygl_detail,object: PopJyqkUtils.OnYdqkLinear{
                            override fun onClick(enterpriseBusiness: EnterpriseInfoEntity?) {
                                enter!!.enterpriseInfoEntities.add(enterpriseBusiness!!)
                                ydqkAdapter!!.notifyDataSetChanged()
//                                mPresenter.getSaveBusiness(enterpriseBusiness!!)
                            }
                        })*/
                }else{
                    if (ll_act_qygl_detail_ydqk.isShown){
                        ll_act_qygl_detail_ydqk.visibility = View.GONE
                    }else{
                        ll_act_qygl_detail_ydqk.visibility = View.VISIBLE
                    }
                }
            }
        })
        mtl_act_qygl_detail_scxgzj.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_qygl_detail_scxgzj.isShown){
                    ll_act_qygl_detail_scxgzj.visibility = View.GONE
                }else{
                    ll_act_qygl_detail_scxgzj.visibility = View.VISIBLE
                }
            }
        })
        mtl_act_qygl_detail_zcqk.setOnTitleListLister(object :MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_qygl_detail_zcqk.isShown){
                    ll_act_qygl_detail_zcqk.visibility = View.GONE
                }else{
                    ll_act_qygl_detail_zcqk.visibility = View.VISIBLE
                }
            }
        })
        ztl_act_qygl_detail_rzrq.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            TimePickerUtil.initTimePickerViewNyr(this,ztl_act_qygl_detail_rzrq.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) { //时间筛选
                    ztl_act_qygl_detail_rzrq.setText(data)
                }
            })
        }
        ztl_act_qygl_detail_yyzzzcrq.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick())
            TimePickerUtil.initTimePickerViewNyr(this,ztl_act_qygl_detail_yyzzzcrq.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) { //时间筛选
                    ztl_act_qygl_detail_yyzzzcrq.setText(data)
                }
            })
        }
        tv_act_qygl_detail_time.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()){
                JyqkPickerUtil.initPickerView(this,"经营历史列表",stringList,tv_act_qygl_detail_time.text.toString(),object:JyqkPickerUtil.OnPickerLinerar{
                    override fun onClick(name: String?) {
                        month = name!!
                        tv_act_qygl_detail_time.setText(month)
                        mPresenter.getBusiness(jyqkid, month)//id
                    }
                })
            }

            /*TimePickerUtil.initTimePickerViewNy(this,tv_act_qygl_detail_time.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    //时间筛选
                    tv_act_qygl_detail_time.setText(data)
                    mPresenter.getBusiness(jyqkid, data!!)//id
                }
            })*/
        }
        but_act_qygl_detail_add.setOnClickListener {//添加
            if (SingleOnClickUtil1.isFastClick())
            PopJyqkUtils.initPopuPoint(this,ll_act_qygl_detail,object: PopJyqkUtils.OnJyqkLinear{
                override fun onClick(enterpriseBusiness: EnterpriseBusiness?) {
//                    Log.e("PopJyqkUtils",Gson().toJson(enter!!.enterpriseEntity))
                    if (enter!!.enterpriseEntity.id==null){
                        enter!!.enterpriseEntity.enterpriseBusinesse = enterpriseBusiness
                        zel_act_qygl_detail_lrze.setText(enterpriseBusiness!!.lrze.toString())
                        zel_act_qygl_detail_ss.setText(enterpriseBusiness.hj.toString())
                        zel_act_qygl_detail_yysr.setText(enterpriseBusiness.yysr.toString())
                        zel_act_qygl_detail_cz.setText(enterpriseBusiness.cz.toString())
                        ztl_act_qygl_detail_jyzt.setText(enterpriseBusiness.jyztText.toString())
                        ztl_act_qygl_detail_bdygsl.setText(enterpriseBusiness.bdgrs.toString())
                        ztl_act_qygl_detail_wdygsl.setText(enterpriseBusiness.wdgrs.toString())
                        ztl_act_qygl_detail_jyrkjzqy1.setText(enterpriseBusiness.dwss.toString())
                        ztl_act_qygl_detail_jyrkjzqy2.setText(enterpriseBusiness.bc.toString())
                        ztl_act_qygl_detail_jyrkjzqy3.setText(enterpriseBusiness.qt.toString())
                    }else{
                        enterpriseBusiness!!.msid = enter!!.enterpriseEntity.id//id
                        mPresenter.getSaveBusiness(enterpriseBusiness!!)
                    }
                }
            })
        }
        but_act_qygl_detail_addczdw.setOnClickListener {//添加承租单位
            if (SingleOnClickUtil1.isFastClick())
            PopJyqkUtils.initCzdwPoint(this,ll_act_qygl_detail,EnterpriseEntity(),object: PopJyqkUtils.OnCzdwLinear{
                override fun onClick(enterpriseBusiness: EnterpriseEntity?) {
                    enterpriseBusiness!!.bh = xh
                    enterpriseBusiness!!.qyId = enter!!.gid.toInt()
                    enterpriseBusiness!!.parent = 0
//                    Log.e("initCzdwPoint",Gson().toJson(enterpriseBusiness))
                    mPresenter.getSaveEnterprise(enterpriseBusiness!!)
//                    ToastUtils.showShort(Gson().toJson(enterpriseBusiness))
                }
            })
        }

        mtl_act_qygl_detail_ydqk.setIsVisibility(View.GONE)
        tv_act_qygl_detail_update.setOnClickListener {
            if (tv_act_qygl_detail_update.text.toString().equals("修改")){
                tv_act_qygl_detail_update.setText("取消")
                tv_act_qygl_detail_sure.visibility = View.VISIBLE//显示确定
                view_act_qygl_detail_jcqk.visibility = View.GONE
                view_act_qygl_detail_fwqk.visibility = View.GONE
                view_act_qygl_detail_zcqk.visibility = View.GONE
                view_act_qygl_detail_jyqk.visibility = View.GONE
                mtl_act_qygl_detail_ydqk.setIsVisibility(View.VISIBLE)
                but_act_qygl_detail_add.visibility = View.VISIBLE
                if (tl_act_qygl_detail.selectedTabPosition==2)
                but_act_qygl_detail_addczdw.visibility = View.VISIBLE
                isUpdate = true

            }else{
                tv_act_qygl_detail_update.setText("修改")
                tv_act_qygl_detail_sure.visibility = View.GONE
                view_act_qygl_detail_jcqk.visibility = View.VISIBLE
                view_act_qygl_detail_fwqk.visibility = View.VISIBLE
                view_act_qygl_detail_zcqk.visibility = View.VISIBLE
                view_act_qygl_detail_jyqk.visibility = View.VISIBLE
                mtl_act_qygl_detail_ydqk.setIsVisibility(View.GONE)
                but_act_qygl_detail_add.visibility = View.GONE
                but_act_qygl_detail_addczdw.visibility = View.GONE
                isUpdate = false

            }
            ydqkAdapter!!.notifyDataSetChanged()
//            qyYeAdapter!!.notifyDataSetChanged()
            qyYeAdapter = QyglFileAdapter(this,enterpriseFileList,isUpdate)
            rlv_act_qygl_detail_src.adapter = qyYeAdapter
            val arrayOf = arrayOf("房屋产权证", "建设工程规划许可证", "其他证件")//"土地使用证",
            qyYeAdapter!!.setOnClickLister(object:QyglFileAdapter.OnQyglFileLinear{
                override fun onClick(enterpriseFileEntity: EnterpriseFileEntity?,position:Int) {
                    if (enterpriseFileEntity == null) {
                        isPoint = false
                        if (SingleOnClickUtil1.isFastClick())
                            AlertDialog.Builder(this@QyglDetailActivity)
                                    .setTitle("请选择文件类型")//请选择上传途径
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf, 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    name = arrayOf[p1]
                                                    isXh = true
                                                    selectFile(234)
                                                    p0!!.dismiss()
                                                }
                                            }
                                    )
                                    .setNegativeButton("取消", null)
                                    .show()
                    } else {
                        var pathList = ArrayList<String>()
                        for (i in enterpriseFileList) {
                            val pic = i.url//ApiConstants.BASE_URL + i.path
                            pathList.add(pic)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(position)
                                .setShowDeleteButton(false)
                                .start(this@QyglDetailActivity!!)
                    }
                }

                override fun onDeleteClick(enterpriseFileEntity: EnterpriseFileEntity?,position:Int) {
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@QyglDetailActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            isPoint = false
                            filePosition = position
                            mPresenter.getEnterDeleteFile(enterpriseFileEntity!!.id)

                        }
                    })
                    // 设置一个NegativeButton
                    builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog!!.dismiss()
                        }
                    })
                    builder.show()
                }
            })
        }
        view_act_qygl_detail_jcqk.setOnClickListener {  }
        view_act_qygl_detail_fwqk.setOnClickListener {  }
        view_act_qygl_detail_zcqk.setOnClickListener {  }
        view_act_qygl_detail_jyqk.setOnClickListener {  }


    }

    override fun initDatas() {

    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_qygl_detail_act_dt.getMap()
        }
        setUpMap()
    }
    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() { // 自定义系统定位蓝点
        mMyLocationStyle = MyLocationStyle()
        //自定义精度范围的圆形边框宽度
        mMyLocationStyle!!.strokeWidth(3f)
        mMyLocationStyle!!.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        aMap!!.uiSettings.isMyLocationButtonEnabled = true // 设置默认定位按钮是否显示
        aMap!!.uiSettings.isZoomGesturesEnabled = true
        aMap!!.uiSettings.isScrollGesturesEnabled = true
//        aMap!!.uiSettings.setZoomInByScreenCenter(false)
        //        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap!!.setMyLocationStyle(mMyLocationStyle)
        aMap!!.isMyLocationEnabled = false // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mMyLocationStyle!!.showMyLocation(true)


    }
    /**
     * 地图方法
     */
    private fun setAMap() { //设置默认定位按钮是否显示
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false // 设置比例尺
        aMap!!.uiSettings.isZoomControlsEnabled = false
        aMap!!.setMapLanguage(AMap.CHINESE)
//        aMap!!.setOnMapClickListener(this)
        mPolygonOptions = PolygonOptions()
//        aMap!!.addTileOverlay(opt_tdtnN)

//        aMap!!.// = 18f

        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//AppCache.getInstance().proID.toInt()  ,legends4L,1  AppCache.getInstance().proID.toInt()
        for (l in mLayers) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {//底图
                    l.isCheck = true
                }
                "工业区" -> {
                    l.isCheck = true
                }
                "企业" -> {
                    l.isCheck = true
                }
                "镇界" -> {
                    l.isCheck = true
                }
                "村界" -> {
                    l.isCheck = true
                }
            }
        }
        for (l in mLayers1) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {
                    l.isCheck = true
                }
                "工业区" -> {
                    l.isCheck = true
                }
                "企业" -> {
                    l.isCheck = true
                }
                "镇界" -> {
                    l.isCheck = true
                }
                "村界" -> {
                    l.isCheck = true
                }
            }
        }
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
    }
    override fun returnDeleteEnterprise(message: String) {

    }

    override fun returnBusiness(message: List<EnterpriseBusiness>,month:String) {
        if (month.equals("")){
//            Log.e("returnBusiness",month+":"+Gson().toJson(message))
            stringList.clear()
            for (i in message){
                if (!stringList.contains(i.createDate)){
                    stringList.add(i.createDate)
                }
            }
        }

        if (message.size>0){
            val enterpriseBisiness = message.get(0)
            zel_act_qygl_detail_lrze.setText(enterpriseBisiness.lrze.toString())
            zel_act_qygl_detail_ss.setText(enterpriseBisiness.hj.toString())
            zel_act_qygl_detail_yysr.setText(enterpriseBisiness.yysr.toString())
            zel_act_qygl_detail_cz.setText(enterpriseBisiness.cz.toString())
            ztl_act_qygl_detail_jyzt.setText(enterpriseBisiness.jyztText.toString())
            ztl_act_qygl_detail_bdygsl.setText(enterpriseBisiness.bdgrs.toString())
            ztl_act_qygl_detail_wdygsl.setText(enterpriseBisiness.wdgrs.toString())
            ztl_act_qygl_detail_jyrkjzqy1.setText(enterpriseBisiness.dwss.toString())
            ztl_act_qygl_detail_jyrkjzqy2.setText(enterpriseBisiness.bc.toString())
            ztl_act_qygl_detail_jyrkjzqy3.setText(enterpriseBisiness.qt.toString())

        }
    }

    override fun returnSaveOrUpdate(message: String) {
//        mPresenter.getEnterQueryInfo(id)

        ToastUtils.showShort(message)
        if (isUpdateYd){
            finish()
        }
    }

    override fun returnSaveEnterprise(message: String) {
        mPresenter.getEnterQueryInfo(id)
        ToastUtils.showShort(message)
    }

    override fun returnSaveBusiness(enterpriseBusinesse: EnterpriseBusiness) {
        month = ""
        mPresenter.getBusiness(jyqkid, month)//id
        /*if (enterpriseBusinesse!=null){
            zel_act_qygl_detail_lrze.setText(enterpriseBusinesse.lrze.toString())
            zel_act_qygl_detail_ss.setText(enterpriseBusinesse.hj.toString())
            zel_act_qygl_detail_yysr.setText(enterpriseBusinesse.yysr.toString())
            zel_act_qygl_detail_cz.setText(enterpriseBusinesse.cz.toString())
            ztl_act_qygl_detail_jyzt.setText(enterpriseBusinesse.jyztText.toString())
            ztl_act_qygl_detail_bdygsl.setText(enterpriseBusinesse.bdgrs.toString())
            ztl_act_qygl_detail_wdygsl.setText(enterpriseBusinesse.wdgrs.toString())
            ztl_act_qygl_detail_jyrkjzqy1.setText(enterpriseBusinesse.dwss.toString())
            ztl_act_qygl_detail_jyrkjzqy2.setText(enterpriseBusinesse.bc.toString())
            ztl_act_qygl_detail_jyrkjzqy3.setText(enterpriseBusinesse.qt.toString())
        }*/
//        mPresenter.getEnterQueryInfo(id)
    }

    override fun returnEnterDeleteFile(message: String) {
//        mPresenter.getEnterQueryInfo(id)
        if (isPoint){
            enterFiles.removeAt(filePosition)
            qyYeAdapter1!!.setmList(enterFiles)
        }else{
            enterpriseFileList.removeAt(filePosition)
            qyYeAdapter!!.setmList(enterpriseFileList)
        }

    }

    override fun returnEnterDelete(message: String) {
        mPresenter.getEnterQueryInfo(id)
    }
    var enter: EnterpriseBasisEntity? = null
    private var enterpriseFileList = ArrayList<EnterpriseFileEntity>()
//    private var qyYeAdapter: BaseQuickAdapter<EnterpriseFileEntity, BaseViewHolder>? = null
    private var qyYeAdapter: QyglFileAdapter? = null
    private var filePosition = 0
    private var name = ""
    private var isXh = true;
    private var isUpdateYd = false;
    private var enterpriseInfoEntity: EnterpriseInfoEntity? = null
    var ydqkAdapter: BaseQuickAdapter<EnterpriseInfoEntity, BaseViewHolder>? = null
    override fun returnEnterQueryInfo(message: EnterpriseBasisEntity) {
        enter = message
       /* if (!message.center.equals("")){
            ToastUtils.showShort(message.center)
            val point = getCenter(message.center)
            aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 15.5f, 0f, 0f)))
        }*/
        if (!message.geometry.equals("")){
            kuangGeomentLine(message.geometry)
        }
//        Log.e("returnEnterQueryInfo",Gson().toJson(message))
        if (message!=null){
            if (message.enterpriseEntity.id!=null){
                jyqkid = message.enterpriseEntity.id
                month = ""
                mPresenter.getBusiness(jyqkid, month)//id
            }
            xh = message.bh
            zel_act_qygl_detail_bh.setText(message.bh)
            zel_act_qygl_detail_xzqymc.setText(message.name)
            zel_act_qygl_detail_xztxsyr.setText(message.tdsyr)
            zel_act_qygl_detail_ydqs.setText(message.qs)
            zel_act_qygl_detail_zdmj.setText(message.zdmj.toString())
            zel_act_qygl_detail_jzmj.setText(message.jzmj.toString())
            val enterpriseInfoEntitie = message.enterpriseInfoEntitie
            zel_act_qygl_detail_yzzdmj.setText(enterpriseInfoEntitie.tdzmj.toString())
            rlv_act_qygl_detail_src.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
            enterpriseFileList.addAll(message.enterpriseFileEntities)
            /*for (i in message.enterpriseInfoEntities){//enterpriseFileList
                enterpriseFileList.addAll(i.enterpriseFileEntities)
            }*/
            qyYeAdapter = QyglFileAdapter(this,enterpriseFileList,isUpdate)
            rlv_act_qygl_detail_src.adapter = qyYeAdapter
            val arrayOf = arrayOf("房屋产权证", "建设工程规划许可证", "其他证件")//"土地使用证",
            qyYeAdapter!!.setOnClickLister(object:QyglFileAdapter.OnQyglFileLinear{
                override fun onClick(enterpriseFileEntity: EnterpriseFileEntity?,position:Int) {
                    if (enterpriseFileEntity == null) {
                        isPoint = false
                        if (SingleOnClickUtil1.isFastClick())
                        AlertDialog.Builder(this@QyglDetailActivity)
                                .setTitle("请选择文件类型")//请选择上传途径
                                .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                                .setSingleChoiceItems(arrayOf, 0,
                                        object : DialogInterface.OnClickListener {
                                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                                name = arrayOf[p1]
                                                isXh = true
                                                    selectFile(234)
                                                p0!!.dismiss()
                                            }
                                        }
                                )
                                .setNegativeButton("取消", null)
                                .show()
                    } else {
                        var pathList = ArrayList<String>()
                        for (i in enterpriseFileList) {
                            val pic = i.url//ApiConstants.BASE_URL + i.path
                            pathList.add(pic)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(position)
                                .setShowDeleteButton(false)
                                .start(this@QyglDetailActivity!!)
                    }
                }

                override fun onDeleteClick(enterpriseFileEntity: EnterpriseFileEntity?,position:Int) {
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@QyglDetailActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            isPoint = false
                            filePosition = position
                            mPresenter.getEnterDeleteFile(enterpriseFileEntity!!.id)

                        }
                    })
                    // 设置一个NegativeButton
                    builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog!!.dismiss()
                        }
                    })
                    builder.show()
                }
            })
            val enterpriseBuilding = message.enterpriseBuilding
            if (enterpriseBuilding!=null){
                zel_act_qygl_detail_fwcqdw.setText(enterpriseBuilding.fwcqdw)
                zel_act_qygl_detail_yjjzmj.setText(enterpriseBuilding.yjjzmj.toString())
                zel_act_qygl_detail_ywdxkj.setText(enterpriseBuilding.dxkj.toString())
                zel_act_qygl_detail_kzjzmj.setText(enterpriseBuilding.kzjzmj.toString())
                zel_act_qygl_detail_kzlymj.setText(enterpriseBuilding.xzlymj.toString())
                zel_act_qygl_detail_kzcfjzmj.setText(enterpriseBuilding.kzcfjzmj.toString())
                zel_act_qygl_detail_sffqjs.setText(enterpriseBuilding.fqjsText)
                zel_act_qygl_detail_wjjzmj.setText(enterpriseBuilding.wjjzmj.toString())
//                zel_act_qygl_detail_kzcfjsqk.setText(enterpriseBuilding.kzcfjsqk.toString())
                zel_act_qygl_detail_sccz.setText(enterpriseBuilding.scczText.toString())
                zel_act_qygl_detail_sccg.setText(enterpriseBuilding.sccgText.toString())
                zel_act_qygl_detail_scmj.setText(enterpriseBuilding.scmjText.toString())
                zel_act_qygl_detail_zrqk.setText(enterpriseBuilding.zrqkText.toString())
            }
            rlv_act_qygl_detail_ydqk.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            ydqkAdapter = object : BaseQuickAdapter<EnterpriseInfoEntity, BaseViewHolder>(R.layout.item_text_list_ydqk, enter!!.enterpriseInfoEntities) {
                override fun convert(helper: BaseViewHolder?, item: EnterpriseInfoEntity?) {
                    helper!!.setText(R.id.tv_qygl_item_list_xh, "${helper.adapterPosition + 1}")
                            .setText(R.id.tv_qygl_item_list_tdsyr, item!!.tdsyr)
                            .setText(R.id.tv_qygl_item_list_tdxz, item!!.tdxz)
                            .setText(R.id.tv_qygl_item_list_qsqk, item!!.qs)
                            .setText(R.id.tv_qygl_item_list_tdqdnx, item!!.tdqddate)
                            .setText(R.id.tv_qygl_item_list_tdsynx, item!!.tdsynx)
                            .setText(R.id.tv_qygl_item_list_tdjg, item!!.tdjg)
                            .setText(R.id.tv_qygl_item_list_tdzmj, item!!.tdzmj.toString())
                            .setText(R.id.tv_qygl_item_list_tdqdfs, item!!.tdqdfs)
                    if (tv_act_qygl_detail_update.text.toString().equals("修改")){
                        helper.getView<TextView>(R.id.tv_qygl_item_list_delete).visibility = View.GONE
                        tv_act_qygl_detail_cz.visibility = View.GONE
                    }else{
                        helper.getView<TextView>(R.id.tv_qygl_item_list_delete).visibility = View.VISIBLE
                        tv_act_qygl_detail_cz.visibility = View.VISIBLE

                    }
                    helper.itemView.setOnClickListener {
                        if (SingleOnClickUtil1.isFastClick())
                            initYdqkPoint(item,helper.adapterPosition)
                            /*PopJyqkUtils.initYdqkPoint(this@QyglDetailActivity,item,ll_act_qygl_detail,object: PopJyqkUtils.OnYdqkLinear{
                                override fun onClick(enterpriseBusiness: EnterpriseInfoEntity?) {
                                    enter!!.enterpriseInfoEntities.set(helper.adapterPosition,enterpriseBusiness)
//                                    enter!!.enterpriseInfoEntities.add(enterpriseBusiness!!)
                                    ydqkAdapter!!.notifyDataSetChanged()
//                                mPresenter.getSaveBusiness(enterpriseBusiness!!)
                                }
                            })*/
                    }
                    helper.getView<TextView>(R.id.tv_qygl_item_list_wjsc).setOnClickListener {
                        if (SingleOnClickUtil1.isFastClick())
                            AlertDialog.Builder(this@QyglDetailActivity)
                                    .setTitle("请选择文件类型")//请选择上传途径
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf, 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    enterpriseInfoEntity = item
                                                    name = arrayOf[p1]
                                                    isXh = false
                                                    selectFile(234)
                                                    p0!!.dismiss()
                                                }
                                                /* public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }*/
                                            }
                                    )
                                    .setNegativeButton("取消", null)
                                    .show()
                    }
                    helper.getView<TextView>(R.id.tv_qygl_item_list_delete).setOnClickListener {
                        val content = TextView(this@QyglDetailActivity)
                        content.text = "是否删除？"
                        SweetAlertDialog(this@QyglDetailActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("用地删除")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    enter!!.enterpriseInfoEntities.removeAt(helper.adapterPosition)
                                    notifyDataSetChanged()
                                    message.infoIds.add(item.id)
                                    isUpdateYd = false
                                    mPresenter.getSaveOrUpdate(message)
                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }
            }
            rlv_act_qygl_detail_ydqk.adapter = ydqkAdapter
            val enterpriseEntity = message.enterpriseEntity
            if (enterpriseEntity!=null){
                zel_act_qygl_detail_xzqymc1.setText(enterpriseEntity.qyname)
                zel_act_qygl_detail_zcqk.setText(enterpriseEntity.zcdz)
                zel_act_qygl_detail_sjjydz.setText(enterpriseEntity.sjjydz)
                ztl_act_qygl_detail_rzrq.setText(enterpriseEntity.rzdate)
                ztl_act_qygl_detail_yyzzzcrq.setText(enterpriseEntity.zcdate)
                zel_act_qygl_detail_swdjzcd.setText(enterpriseEntity.swzcdz)
                ztl_act_qygl_detail_qylx.setText(enterpriseEntity.qylxText)
                ztl_act_qygl_detail_hylx.setText(enterpriseEntity.hylxText)
                zel_act_qygl_detail_xzlxr.setText(enterpriseEntity.xzlxr)
                ztl_act_qygl_detail_sfzz.setText(if (enterpriseEntity.czqk==0)"否" else "是")
                ztl_act_qygl_detail_zclx.setText(enterpriseEntity.zclxText)
                zel_act_qygl_detail_zczj.setText(enterpriseEntity.zczj)
                zel_act_qygl_detail_lxdh.setText(enterpriseEntity.lxdh)
                ztl_act_qygl_detail_gxqy.setText(enterpriseEntity.gxqyText)
                val enterpriseBusinesse = enterpriseEntity.enterpriseBusinesse
                if (enterpriseBusinesse!=null){
                    zel_act_qygl_detail_lrze.setText(enterpriseBusinesse.lrze.toString())
                    zel_act_qygl_detail_ss.setText(enterpriseBusinesse.hj.toString())
                    zel_act_qygl_detail_yysr.setText(enterpriseBusinesse.yysr.toString())
                    zel_act_qygl_detail_cz.setText(enterpriseBusinesse.cz.toString())
                    ztl_act_qygl_detail_jyzt.setText(enterpriseBusinesse.jyztText.toString())
                    ztl_act_qygl_detail_bdygsl.setText(enterpriseBusinesse.bdgrs.toString())
                    ztl_act_qygl_detail_wdygsl.setText(enterpriseBusinesse.wdgrs.toString())
                    ztl_act_qygl_detail_jyrkjzqy1.setText(enterpriseBusinesse.dwss.toString())
                    ztl_act_qygl_detail_jyrkjzqy2.setText(enterpriseBusinesse.bc.toString())
                    ztl_act_qygl_detail_jyrkjzqy3.setText(enterpriseBusinesse.qt.toString())
                }
            }
            val enterpriseEntities = message.enterpriseEntities
            rlv_act_qygl_detail_czdwmc.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_act_qygl_detail_czdwmc.adapter = object:BaseQuickAdapter<EnterpriseEntity,BaseViewHolder>(R.layout.item_qygl_detail_czdwmc,enterpriseEntities){
                override fun convert(helper: BaseViewHolder?, item: EnterpriseEntity?) {
                    helper!!.setText(R.id.tv_item_qygl_detail_czdw_qymc, item!!.qyname)
                            .setText(R.id.tv_item_qygl_detail_czdw_lxr, item!!.xzlxr)
                            .setText(R.id.tv_item_qygl_detail_czdw_zclx, item!!.zcdz)

                    helper!!.itemView.setOnClickListener {
                        if (SingleOnClickUtil1.isFastClick()){
                            val intent = Intent(this@QyglDetailActivity, CzdwDetailActivity::class.java)
                            intent.putExtra("enterpriseEntity",item)
                            startActivity(intent)
                        }
                    }
                }
            }

        }
        tv_act_qygl_detail_sure.setOnClickListener {
            enter!!.bh = zel_act_qygl_detail_bh.value
            enter!!.name = zel_act_qygl_detail_xzqymc.value
            enter!!.tdsyr = zel_act_qygl_detail_xztxsyr.value
            enter!!.qs = zel_act_qygl_detail_ydqs.value
            enter!!.zdmj = if (zel_act_qygl_detail_zdmj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_zdmj.value)
            enter!!.jzmj = if (zel_act_qygl_detail_jzmj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_jzmj.value)
            enter!!.enterpriseInfoEntitie.tdzmj = if (zel_act_qygl_detail_yzzdmj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_yzzdmj.value)
            enter!!.enterpriseBuilding.fwcqdw = zel_act_qygl_detail_fwcqdw.value
            enter!!.enterpriseBuilding.yjjzmj = if (zel_act_qygl_detail_yjjzmj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_yjjzmj.value)
            enter!!.enterpriseBuilding.dxkj = zel_act_qygl_detail_ywdxkj.value
            enter!!.enterpriseBuilding.kzjzmj = if (zel_act_qygl_detail_kzjzmj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_kzjzmj.value)
            enter!!.enterpriseBuilding.xzlymj = if (zel_act_qygl_detail_kzlymj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_kzlymj.value)
            enter!!.enterpriseBuilding.kzcfjzmj = if (zel_act_qygl_detail_kzcfjzmj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_kzcfjzmj.value)
            enter!!.enterpriseBuilding.fqjs = IsTrueEnum.getIndex(zel_act_qygl_detail_sffqjs.value)//if (zel_act_qygl_detail_sffqjs.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_sffqjs.value)
            enter!!.enterpriseBuilding.wjjzmj = if (zel_act_qygl_detail_wjjzmj.value.equals("")) BigDecimal.ZERO else BigDecimal(zel_act_qygl_detail_wjjzmj.value)
//            message.enterpriseBuilding.kzcfjsqk = zel_act_qygl_detail_kzcfjsqk.value
            enter!!.enterpriseBuilding.sccz = ScczEnum.getIndex(zel_act_qygl_detail_sccz.value)
            enter!!.enterpriseBuilding.sccg = SccgEnum.getIndex(zel_act_qygl_detail_sccg.value)
            enter!!.enterpriseBuilding.scmj = ScmjEnum.getIndex(zel_act_qygl_detail_scmj.value)
            enter!!.enterpriseBuilding.zrqk = IsTrueEnum.getIndex(zel_act_qygl_detail_zrqk.value)

            enter!!.enterpriseEntity.parent = 1
            enter!!.enterpriseEntity.qyname = zel_act_qygl_detail_xzqymc1.value
            enter!!.enterpriseEntity.zcdz = zel_act_qygl_detail_zcqk.value
            enter!!.enterpriseEntity.sjjydz = zel_act_qygl_detail_sjjydz.value
            enter!!.enterpriseEntity.rzdate = ztl_act_qygl_detail_rzrq.value
            enter!!.enterpriseEntity.zcdate = ztl_act_qygl_detail_yyzzzcrq.value
            enter!!.enterpriseEntity.swzcdz = zel_act_qygl_detail_swdjzcd.value
            enter!!.enterpriseEntity.qylx = EnterpriseTypeEnum.getIndex(ztl_act_qygl_detail_qylx.value)
            enter!!.enterpriseEntity.hylx = IndustryEnum.getIndex(ztl_act_qygl_detail_hylx.value)
            enter!!.enterpriseEntity.xzlxr = zel_act_qygl_detail_xzlxr.value
            enter!!.enterpriseEntity.czqk = IsTrueEnum.getIndex(ztl_act_qygl_detail_sfzz.value)
            enter!!.enterpriseEntity.zclx = RegistraEnum.getIndex(ztl_act_qygl_detail_zclx.value)
            enter!!.enterpriseEntity.zczj = zel_act_qygl_detail_zczj.value
            enter!!.enterpriseEntity.lxdh = zel_act_qygl_detail_lxdh.value
            enter!!.enterpriseEntity.gxqy = EnterpriseGxEnum.getIndex(ztl_act_qygl_detail_gxqy.value)

            isUpdateYd = true
            mPresenter.getSaveOrUpdate(enter!!)

        }
    }
    /**
     * 通过手机选择文件
     */
    fun selectFile(requestCode:Int) {
//        var intent = Intent(Intent.ACTION_GET_CONTENT)
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.setType("*/*")
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(Intent.createChooser(intent, "选择文件上传"), requestCode);
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "请安装一个文件管理器.", Toast.LENGTH_SHORT).show();
        }
    }

    private var mPointPopu: CommenPop? = null
    private var enterFiles = ArrayList<EnterpriseFileEntity>()
    private var qyYeAdapter1: QyglFileAdapter? = null
    private var isPoint = false

    fun initYdqkPoint(enterpriseInfoEntity: EnterpriseInfoEntity,position:Int) {
        isPoint = true
        enterFiles.clear()
        mPointPopu = CommenPop.getNormalPopu(this, R.layout.pop_ydqk, ll_act_qygl_detail)//pop_point
        val contentView = mPointPopu?.getContentView()
        val zel_pop_ydqk_tdsyr = contentView?.findViewById<ZdyEditLayout>(R.id.zel_pop_ydqk_tdsyr)//土地使用人
        val zel_pop_ydqk_tdxz =  contentView?.findViewById<ZdyEditLayout>(R.id.zel_pop_ydqk_tdxz)//土地性质
        val ztl_pop_ydqk_qsqk =  contentView?.findViewById<ZdyTextLayout>(R.id.ztl_pop_ydqk_qsqk)//权属情况
        val zel_pop_ydqk_tdqdnd =  contentView?.findViewById<ZdyTextLayout>(R.id.zel_pop_ydqk_tdqdnd)//土地取得年限
        val zel_pop_ydqk_tdsyqdnx =  contentView?.findViewById<ZdyTextLayout>(R.id.zel_pop_ydqk_tdsyqdnx)//土地剩余使用年限
        val zel_pop_ydqk_tdjg =  contentView?.findViewById<ZdyEditLayout>(R.id.zel_pop_ydqk_tdjg)//土地价格
        val zel_pop_ydqk_tdzmj =  contentView?.findViewById<ZdyEditLayout>(R.id.zel_pop_ydqk_tdzmj)//土地证面积
        val zel_pop_ydqk_tdqdfs =  contentView?.findViewById<ZdyEditLayout>(R.id.zel_pop_ydqk_tdqdfs)//土地取得方式
        val rlv_pop_ydqk_zjsc =  contentView?.findViewById<RecyclerView>(R.id.rlv_pop_ydqk_zjsc)//土地证文件
        val view_pop_ydqk =  contentView?.findViewById<View>(R.id.view_pop_ydqk)//

        val but_pop_ydqk_sure =  contentView?.findViewById<TextView>(R.id.but_pop_ydqk_sure)
        val but_pop_ydqk_close =  contentView?.findViewById<TextView>(R.id.but_pop_ydqk_close)
        rlv_pop_ydqk_zjsc?.layoutManager = GridLayoutManager(this,3)
        if (isUpdate){
            but_pop_ydqk_sure!!.visibility = View.VISIBLE
            view_pop_ydqk!!.visibility = View.GONE
        }else{
            but_pop_ydqk_sure!!.visibility = View.GONE
            view_pop_ydqk!!.visibility = View.VISIBLE
        }
        view_pop_ydqk.setOnClickListener {  }
//        enterpriseInfoEntity.enterpriseFileEntities
        enterFiles.addAll(enterpriseInfoEntity.enterpriseFileEntities)
//        Log.e("initYdqkPoint",Gson().toJson(enterFiles))
        qyYeAdapter1 = QyglFileAdapter(this,enterFiles,isUpdate)
        qyYeAdapter1!!.setOnClickLister(object:QyglFileAdapter.OnQyglFileLinear{
            override fun onClick(enterpriseFileEntity: EnterpriseFileEntity?,position:Int) {
                if (enterpriseFileEntity == null) {
                    if (SingleOnClickUtil1.isFastClick()){
                        this@QyglDetailActivity.enterpriseInfoEntity = enterpriseInfoEntity
                        name = "土地使用证"
                        isXh = false
                        selectFile(234)
                    }

                } else {
                    var pathList = ArrayList<String>()
                    for (i in enterpriseFileList) {
                        val pic = i.url//ApiConstants.BASE_URL + i.path
                        pathList.add(pic)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(position)
                            .setShowDeleteButton(false)
                            .start(this@QyglDetailActivity!!)
                }
            }

            override fun onDeleteClick(enterpriseFileEntity: EnterpriseFileEntity?,position:Int) {
                // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                var builder = AlertDialog.Builder(this@QyglDetailActivity)
                // 设置Title的图标
                builder.setIcon(R.mipmap.ic_launcher)
                // 设置Title的内容
                builder.setTitle("弹出警告框")
                // 设置Content来显示一个信息
                builder.setMessage("确定删除吗？")
                // 设置一个PositiveButton
                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        filePosition = position
                        mPresenter.getEnterDeleteFile(enterpriseFileEntity!!.id)
                    }
                })
                // 设置一个NegativeButton
                builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog!!.dismiss()
                    }
                })
                builder.show()
            }
        })
        rlv_pop_ydqk_zjsc?.adapter = qyYeAdapter1
                /*object:BaseQuickAdapter<EnterpriseFileEntity,BaseViewHolder>(R.layout.item_text_list_layout,enterpriseInfoEntity.enterpriseFileEntities){
            override fun convert(helper: BaseViewHolder?, item: EnterpriseFileEntity?) {

            }
        }*/
        zel_pop_ydqk_tdsyr?.setText(enterpriseInfoEntity.tdsyr)
        zel_pop_ydqk_tdxz?.setText(enterpriseInfoEntity.tdxz)
        ztl_pop_ydqk_qsqk?.setText(enterpriseInfoEntity.qs)
        zel_pop_ydqk_tdqdnd?.setText(enterpriseInfoEntity.tdqddate)
        zel_pop_ydqk_tdsyqdnx?.setText(enterpriseInfoEntity.tdsynx)
        zel_pop_ydqk_tdjg?.setText(enterpriseInfoEntity.tdjg)
        zel_pop_ydqk_tdzmj?.setText(enterpriseInfoEntity.tdzmj.toString())
        zel_pop_ydqk_tdqdfs?.setText(enterpriseInfoEntity.tdqdfs)
        zel_pop_ydqk_tdqdnd?.setOnClickListener(View.OnClickListener { TimePickerUtil.initTimePickerViewNyr(this, zel_pop_ydqk_tdqdnd.getValue()) { data -> zel_pop_ydqk_tdqdnd.setText(data) } })
        zel_pop_ydqk_tdsyqdnx?.setOnClickListener(View.OnClickListener { TimePickerUtil.initTimePickerViewNyr(this, zel_pop_ydqk_tdsyqdnx.getValue()) { data -> zel_pop_ydqk_tdsyqdnx.setText(data) } })
        ztl_pop_ydqk_qsqk?.setOnClickListener(View.OnClickListener { JyqkPickerUtil.initNationPickerView(this, "用地权属", ztl_pop_ydqk_qsqk) })
        but_pop_ydqk_sure?.setOnClickListener(View.OnClickListener {
            enterpriseInfoEntity.tdsyr = zel_pop_ydqk_tdsyr?.getValue()
            enterpriseInfoEntity.tdxz = zel_pop_ydqk_tdxz?.getValue()
            enterpriseInfoEntity.qs = ztl_pop_ydqk_qsqk?.getValue()
            enterpriseInfoEntity.tdqddate = zel_pop_ydqk_tdqdnd?.getValue()
            enterpriseInfoEntity.tdsynx = zel_pop_ydqk_tdsyqdnx?.getValue()
            enterpriseInfoEntity.tdjg = zel_pop_ydqk_tdjg?.getValue()
            if (zel_pop_ydqk_tdzmj?.getValue() != "") {
                enterpriseInfoEntity.tdzmj = BigDecimal(zel_pop_ydqk_tdzmj!!.getValue())
            }
            enterpriseInfoEntity.tdqdfs = zel_pop_ydqk_tdqdfs!!.getValue()
//            onJyqkLinear.onClick(enterpriseInfoEntity)
            enterpriseInfoEntity.enterpriseFileEntities.clear()
            enterpriseInfoEntity.enterpriseFileEntities.addAll(enterFiles)
            if (enterpriseInfoEntity.id==null){
                enter!!.enterpriseInfoEntities.add(enterpriseInfoEntity!!)
                ydqkAdapter!!.notifyDataSetChanged()
            }else{
                enter!!.enterpriseInfoEntities.set(position,enterpriseInfoEntity)
//                                    enter!!.enterpriseInfoEntities.add(enterpriseBusiness!!)
                ydqkAdapter!!.notifyDataSetChanged()
            }
            mPointPopu?.dismiss()
        })
        but_pop_ydqk_close?.setOnClickListener(View.OnClickListener { mPointPopu?.dismiss() })
        CommenPop.backgroundAlpha(0.5f, this)
        mPointPopu?.showAtLocation(ll_act_qygl_detail, Gravity.CENTER, 0, 0)

    }

    private var selectedPhotos = ArrayList<String>()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {//&&requestCode != 66
            if (requestCode==233){
                var photos: ArrayList<String>? = null
                selectedPhotos.clear()

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    for (i in photos){
                        val file = File(i)
                        val name = file.name
                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        val file1: File = compressImages(bitmap, name)
                        upFile1(file1);
                    }
                }
            }else if (requestCode==234){
                var isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                if (!isSdCardExist) {
                    ToastUtils.showShort("SD卡不可用,请检查")
                    return
                }
                if (data!=null){
                    var uri = data!!.getData()
                    if (uri!=null){
                        val realPathFromURI = FileUtilFjxc1.getPath(this, uri)
                        upFile1(File(realPathFromURI))
                    }

                }
            }
        }
    }
    /**
     * 压缩图片（质量压缩）
     *
     * @param bitmap
     */
    fun compressImages(bitmap: Bitmap, fileName: String): File {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos) //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 1024) { //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset() //重置baos即清空baos
            options -= 10 //每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) //这里压缩options%，把压缩后的数据存放到baos中
            val length = baos.toByteArray().size.toLong()
        }
//        val format = SimpleDateFormat("yyyyMMddHHmmss")
//        val date = Date(System.currentTimeMillis())
//        val filename = format.format(date)
        //        File file = new File(Environment.getExternalStorageDirectory(), fileName + ".png");
        val file1 = File(AppMenus.getInstance().cardPath + "jymj/tzrjhj/pic/")
        if (!file1.isDirectory) {
            file1.mkdirs()
        }
        val file = File(AppMenus.getInstance().cardPath+"jymj/tzrjhj/pic/1"+fileName)
//        val file = File(Environment.getExternalStorageDirectory(), fileName)
        try {
            val fos = FileOutputStream(file)
            try {
                fos.write(baos.toByteArray())
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        recycleBitmap(bitmap)
        return file
    }
    //释放
    fun recycleBitmap(vararg bitmaps: Bitmap?) {
        if (bitmaps == null) {
            return
        }
        for (bm in bitmaps) {
            if (null != bm && !bm.isRecycled) {
                bm.recycle()
            }
        }
    }

    private fun upFile1( file2: File) {
        val decodeFile = BitmapFactory.decodeFile(file2.path)
        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(this, decodeFile,  "", 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(drawTextToRightBottom)
        val request = OkGo.post<BaseRespose<EnterpriseFileEntity>>(ApiConstants.ENTERPRISE_UPLOAD_FILE)
                .isMultipart(true)
//        request.params("pid", AppCache.getInstance().getProid())
       /* if (isXh){//xh
//            request.params("gid", id)
        }*/
        request.params("bh", xh)
        request.params("name", name)//file  file.name
        request.params("file", file)//file  file.name
        request.execute(object : BaseNet<BaseRespose<EnterpriseFileEntity>>() {//BaseRespose<PjEnviorFileEntity>
            override fun onStart(request: Request<BaseRespose<EnterpriseFileEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@QyglDetailActivity)
            }
            override fun onSuccess(response: Response<BaseRespose<EnterpriseFileEntity>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (enterpriseInfoEntity!=null&&!isXh){
                    enterpriseInfoEntity!!.ids.add(body.data.id)
                }
                if (body.getCode()==0){
                    if (isPoint){
                        enterFiles.add(body.data)
                        qyYeAdapter1!!.setmList(enterFiles)
                        qyYeAdapter1!!.notifyDataSetChanged()
                    }else{
                        enterpriseFileList.add(body.data)
                        qyYeAdapter!!.setmList(enterpriseFileList)
                        qyYeAdapter!!.notifyDataSetChanged()
                    }
                }else{
                    ToastUtils.showShort(file.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<EnterpriseFileEntity>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
            }
        })
    }

    fun kuangGeomentLine(dataGeometry: String) {
        /*if (addPolyline!=null){
            addPolyline?.remove()
        }*/
        for (i in polylines){
            i.remove()
        }
        if (!dataGeometry.equals("")) {
//            clear
            var substring = dataGeometry!!.substring(0, dataGeometry!!.length - 5)
            val rep = substring.replace("POINT ZM (", "")
            val repl = rep.replace("MULTILINESTRING", "")

            val repla = repl.replace("MULTILINESTRING ZM ((", "")
            val replac = repla.replace("MULTIPOLYGON ZM ", "")
            val replace = replac.replace("MULTIPOLYGON(((", "")
            val replace1 = replace.replace("POLYGON((", "")
            /*val replace = substring.replace("(", "")
                val replace1 = replace.replace(")", "")*/
            var split = replace1.split(")),((")
            if (split.size<2){
                split = replace1.split("),(")
            }
            var s = 0
            for (i in 0..split.size - 1) {
                if (split[i].contains("),(")){
                    val split1 = split[i].split("),(")
                    for (i in 0..split1.size - 1) {
                        val latList = getLatList(split1[i])
                        if (i == 0) {
//                            aMap!!.animateCamera(CameraUpdateFfragory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                            aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 15.5f, 0f, 0f)))
                        }

                        if (mPolylineOptions == null)
//                            mPolygon!!.remove()
                            mPolylineOptions = PolylineOptions()
                        mPolylineOptions!!.getPoints().clear()
                        mPolylineOptions!!.addAll(latList)
                        mPolylineOptions!!.color(Color.argb(255, 0, 0, 255))
                        /*if (i == 0) {
                        mPolylineOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(50, 64, 64, 255)) // 多边形的填充色
                    } else {
                        mPolylineOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(0, 0, 0, 0)) // 多边形的填充色
                    }*/
                        s = 1
                        addPolyline = aMap!!.addPolyline(mPolylineOptions)
                        polylines.add(addPolyline!!)
                    }
                }else{
                    val latList = getLatList(split[i])
                    if (i == 0){
                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 15.5f, 0f, 0f)))
                    }

                    if (mPolylineOptions == null)
//                            mPolygon!!.remove()
                        mPolylineOptions = PolylineOptions()
                    mPolylineOptions!!.getPoints().clear()
                    mPolylineOptions!!.addAll(latList)
                    mPolylineOptions!!.color(Color.argb(255, 0, 0, 255))
                    /*if (i == 0) {
                        mPolylineOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(50, 64, 64, 255)) // 多边形的填充色
                    } else {
                        mPolylineOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(0, 0, 0, 0)) // 多边形的填充色
                    }*/
                    s = 1
                    addPolyline = aMap!!.addPolyline(mPolylineOptions)
                    polylines.add(addPolyline!!)
                }

            }
        }
    }
    fun getLatList(multip: String): List<LatLng> {
        var list = java.util.ArrayList<LatLng>()
        if (multip != null) {
//            val replace2 = replace1.replace("MULTIPOLYGON ZM ", "")
            var split = multip.split(",")

            for (lat in split) {
                if (!lat.equals("")) {
                    var split1 = lat.split(" ")
                    /* var converter = CoordinateConverter()
                     // CoordType.GPS 待转换坐标类型
                     converter.from(CoordinateConverter.CoordType.GPS)
                     var sl = LatLng(split1[1].toDouble(), split1[0].toDouble())
                     // sourceLatLng待转换坐标点 LatLng类型
                     converter.coord(sl)
                     var latLng = converter.convert()*/
                    val replace = split1[0].replace("(", "")
                    val replace2 = split1[1].replace("(", "")
                    val replace3 = replace.replace(")", "")
                    val replace4 = replace2.replace(")", "")
                    val latLng = LatLng(replace4.toDouble(), replace3.toDouble())
                    list.add(latLng)
                }

            }
        }
        return list
    }

    fun getCenter(center: String): LatLng {
        if (center != null) {
            if (!center.equals("")) {
                val points = center.substring(6, center.length - 1).split(" ").toTypedArray()
                return if (points != null && points.size > 1) {
                    val converter = CoordinateConverter()
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS)
                    val sl = LatLng(points[1].toDouble(), points[0].toDouble())
                    // sourceLatLng待转换坐标点 LatLng类型
                    converter.coord(sl)
                    val latLng = converter.convert()
                    sl
                } else {
                    LatLng(0.0, 0.0)
                }
            } else {
                LatLng(0.0, 0.0)
            }
        }
        return LatLng(0.0, 0.0)
    }
    override fun onResume() {
        super.onResume()
        if (map_qygl_detail_act_dt!=null)
        map_qygl_detail_act_dt.onResume()
        if ( AppCache.getInstance().isQyxqUpdate == 1){
            mPresenter.getEnterQueryInfo(id)
            AppCache.getInstance().isQyxqUpdate == 0
        }

    }

    override fun onPause() {
        super.onPause()
        if (map_qygl_detail_act_dt!=null)
        map_qygl_detail_act_dt.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (map_qygl_detail_act_dt!=null)
        map_qygl_detail_act_dt.onDestroy()
    }
    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {
        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        for (l in layers) {
            if (l.name == "天地图"){
                if (l.isCheck){
                    aMap!!.addTileOverlay(opt_tdtStaNomal)
                    aMap!!.addTileOverlay(opt_tdtnN)
                }
            }else if (l.name == "影像图"){
                if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtSta)
                    aMap!!.addTileOverlay(opt_tdtStaN)
                }
            }else if (l.name == "底图"){
                if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtOsm)
                }
            }else {
                if (l.isCheck)
                    aMap!!.addTileOverlay(l.opt)
            }
            /*if (l.name == "天地图") {
                if (l.isCheck) {
                    *//*aMap!!.addTileOverlay(opt_tdtStaNomal)
                    aMap!!.addTileOverlay(opt_tdtnN)*//*
                }else{
                    val GET_CS = "http://39.106.20.43:8090/geoserver/hgz_dxjc/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=hgz_dxjc:zj2&styles=&viewparams=&srs=EPSG:3857&format=image%2Fpng&bbox="
                    val path_zj = GetFileUtil.getSDCardPath() + "jymj/czgz/map/tz/kb/"
                    var layer_zj: HeritageScopeTileProvider = HeritageScopeTileProvider(GET_CS, path_zj)
                    var opt_zj = TileOverlayOptions().tileProvider(layer_zj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_zj).diskCacheSize(10 * 1024)
                    aMap!!.addTileOverlay(opt_zj)
                }
            }else if (l.name == "影像图") {

                if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtSta)
                    aMap!!.addTileOverlay(opt_tdtStaN)
                } else {
                    aMap!!.addTileOverlay(opt_tdtOsm)
                    *//*aMap!!.addTileOverlay(opt_tdtStaNomal)
                    aMap!!.addTileOverlay(opt_tdtnN)*//*
                }
                //                aMap.addTileOverlay(opt_cj);
            } else {
                if (l.isCheck)
                    aMap!!.addTileOverlay(l.opt)
            }*/
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
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
