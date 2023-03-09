package com.jymj.zhglxt.zjd.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.*
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.act_ydlr.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import java.lang.Double
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.jymj.zhglxt.app.AppApplication
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.bean.enums.HuTypeEnum
import com.jymj.zhglxt.bean.enums.NationEnum
import com.jymj.zhglxt.bean.enums.SexEnum
import com.jymj.zhglxt.bean.enums.SociarateEnum
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.rjhj.bean.yl.*
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.widget.zdybj.TimeLineLayout
import com.jymj.zhglxt.widget.zdybj.bean.TimeLineBean
import com.jymj.zhglxt.zjd.bean.zjdyd.*
import com.jymj.zhglxt.zjd.contract.YdlrActContract
import com.jymj.zhglxt.zjd.presenter.YdlrActPresenter
import kotlinx.android.synthetic.main.fragment_zjd_fj.*

class YdlrActivity: BaseActivity<YdlrActPresenter, YdlrActContract.Model>(), YdlrActContract.View, AMapLocationListener {

    var jtcyxxAdapter: BaseQuickAdapter<ZhaiEntity, BaseViewHolder>? = null//家庭成员信息
    var fwxxAdapter: BaseQuickAdapter<ApplyFwEntity, BaseViewHolder>? = null//房屋信息
    var envirorUpPopu: CommenPop? = null//房屋信息弹出框
    var applyEntity:ArrayList<ApplyLandEntity>? = null//ApplyEntity()
    var zhaiEntities =ArrayList<ZhaiEntity>()//户籍人员信息集合
    var applyFwEntitys =ArrayList<ApplyFwEntity>()//房屋信息集合


//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    internal var tdtSta = TDTTileProvider(TDTTileProvider.STA, GetFileUtil.getSDCardPath()!! + "BMS/map/tdt/sta/")
    internal var opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true)
            .memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath()!! + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    internal var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, GetFileUtil.getSDCardPath()!! + "BMS/map/tdt/note/")
    internal var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(GetFileUtil.getSDCardPath()!! + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    var tdtStaNomal = TDTTileProvider(TDTTileProvider.NORMAL, GetFileUtil.getSDCardPath() + "BMS/map/tdt/staNomal/")
    var opt_tdtStaNomal = TileOverlayOptions().tileProvider(tdtStaNomal).diskCacheEnabled(false)
            .memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/staNomal/").diskCacheSize(10 * 1024)
    internal var tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, GetFileUtil.getSDCardPath()!! + "BMS/map/tdt/staNote/")
    internal var opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false)
            .memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath()!! + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    val tdtOsm = TDTOSMTileProvider("", GetFileUtil.getSDCardPath() + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true)
            .memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

    private var aMap: AMap? = null
    private var  addPolyline: Polyline? = null//高亮显示
    private var mPolylineOptions: PolylineOptions? = null
    var spbList = ArrayList<ApplyLandFile>()//审核//ApplyFileEntity
    var shhList = ArrayList<ApplyLandFile>()//审批
    var spList = ArrayList<ApplyLandFile>()//驳回/通过
    var ysList = ArrayList<ApplyLandFile>()//验收
    var qqList = ArrayList<ApplyLandFile>()//确权
    var fhsqAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null//申请

    var scwjAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null//审核
    var shhAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null//审批
    var spAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null//驳回/通过
    var ysAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null//验收
    var qqAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null//确权
    var applyFileList = ArrayList<ApplyLandFile>()
    var fhqsFileList = ArrayList<ApplyLandFile>()
    private var yuanSptype = 1
    var fhCount = 1//分户数量
    var sqUpdate = true//申请
    var xchsUpdate = true//现场核实
    var spUpdate = true//审批
    var ysUpdate = true//验收

    override fun getLayoutId(): Int {
        return R.layout.act_ydlr
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    private val fhTitles = ArrayList<String>()
    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_ydlr_act_dt?.onCreate(intent.extras)
        initAMap()
        //slv_act_ydlr

        var metric = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(metric)
        var height = metric.heightPixels   // 屏幕高度（像素）
        supl_act_ydlr.panelHeight = (height*0.65).toInt()//(height*0.7).toInt()//DisplayUtil.dip2px(50f)
        supl_act_ydlr.setScrollableView(slv_act_ydlr)
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//AppCache.getInstance().proID.toInt()  ,legends4L,1  AppCache.getInstance().proID.toInt()
        for (l in mLayers) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {//底图
                    l.isCheck = true
                }
                "翻建" -> {
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
                "翻建" -> {
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

        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
        location()
        applyEntity = intent.getSerializableExtra("applyEntity") as ArrayList<ApplyLandEntity>//获取传递过来的翻建实体
        if (applyEntity!=null){
            if (applyEntity!!.size>0){
                if (applyEntity!!.get(0).zhaiEntities.size==0&&applyEntity!!.get(0).sptype!=0){
                    mPresenter.getApplyLandGetHuji(applyEntity!!.get(0).ylId,applyEntity!!.get(0).id)
                }
            }
        }
        ic_act_ydlr_back.setOnClickListener {//返回
            finish()
        }

        iv_act_ydlr_fhzm.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("分户证明") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("分户证明") + 400)
                                    }
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
        iv_act_ydlr_qszm.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("权属证明") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("权属证明") + 400)
                                    }
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

        iv_act_ydlr_fjcns.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("翻建承诺书") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("翻建承诺书") + 400)
                                    }
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
        iv_act_ydlr_sqb.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("申请表") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("申请表") + 400)
                                    }
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
        iv_act_ydlr_sfz.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("身份证") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("身份证") + 400)
                                    }
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
        iv_act_ydlr_hkb.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("户口本") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("户口本") + 400)
                                    }
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
        iv_act_ydlr_fwzp.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("房屋照片") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("房屋照片") + 400)
                                    }
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
        iv_act_ydlr_xcgg.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("现场公告") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("现场公告") + 400)
                                    }
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
        iv_act_ydlr_qzyjb.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("签字意见表") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("签字意见表") + 400)
                                    }
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
        iv_act_ydlr_wzt.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("位置图") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("位置图") + 400)
                                    }
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
        iv_act_ydlr_slyj.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("四邻意见") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("四邻意见") + 400)
                                    }
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
        iv_act_ydlr_ghxkspb.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("通过申请表") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("通过申请表") + 400)
                                    }
                                    p0!!.dismiss()
                                }
                            }
                    )
                    .setNegativeButton("取消", null)
                    .show()

        }
        iv_act_ydlr_ysxczp.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("验收现场照片") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("验收现场照片") + 400)
                                    }
                                    p0!!.dismiss()
                                }
                            }
                    )
                    .setNegativeButton("取消", null)
                    .show()

        }
        iv_act_ydlr_yszl.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("验收资料") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("验收资料") + 400)
                                    }
                                    p0!!.dismiss()
                                }
                            }
                    )
                    .setNegativeButton("取消", null)
                    .show()

        }
        iv_act_ydlr_bhsqb.setOnClickListener {
            AlertDialog.Builder(this@YdlrActivity)
                    .setTitle("请选择上传途径")
                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                            object : DialogInterface.OnClickListener {
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    if (p1 == 0) {
                                        PhotoPicker.builder()
                                                .setPhotoCount(1)
                                                .setShowCamera(true)
                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                .start(this@YdlrActivity!!, ApplyFileEnum.getIndex("驳回申请表") + 300)
                                    } else {
                                        selectFile(ApplyFileEnum.getIndex("驳回申请表") + 400)
                                    }
                                    p0!!.dismiss()
                                }
                            }
                    )
                    .setNegativeButton("取消", null)
                    .show()

        }
        ll_act_ydlr_hzxx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_ydlr_hzxx1.isShown){
                    ll_act_ydlr_hzxx1.visibility = View.GONE
                }else{
                    ll_act_ydlr_hzxx1.visibility = View.VISIBLE
                }
            }
        })
        ll_act_ydlr_jtcyxx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_act_ydlr_jtcyxx1.isShown){
                        ll_act_ydlr_jtcyxx1.visibility = View.GONE
                    }else{
                        ll_act_ydlr_jtcyxx1.visibility = View.VISIBLE
                    }
                }else{
                    if (SingleOnClickUtil.isFastClick()){
                        initJcyxx(ZhaiEntity(),0)
                        CommenPop.backgroundAlpha(0.5f, this@YdlrActivity)
                        envirorUpPopu!!.showAtLocation(ll_act_ydlr, Gravity.CENTER, 0, 0)
                    }

                }
            }
        })
        ll_act_ydlr_xzjdjfwqk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_ydlr_xzjdjfwqk1.isShown){
                    ll_act_ydlr_xzjdjfwqk1.visibility = View.GONE
                }else{
                    ll_act_ydlr_xzjdjfwqk1.visibility = View.VISIBLE
                }
            }
        })
        ll_act_ydlr_nsqzjdjjfqk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_ydlr_nsqzjdjjfqk1.isShown){
                    ll_act_ydlr_nsqzjdjjfqk1.visibility = View.GONE
                }else{
                    ll_act_ydlr_nsqzjdjjfqk1.visibility = View.VISIBLE
                }
            }
        })
        ll_act_ydlr_fwxx.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_act_ydlr_fwxx1.isShown){
                        ll_act_ydlr_fwxx1.visibility = View.GONE
                    }else{
                        ll_act_ydlr_fwxx1.visibility = View.VISIBLE
                    }
                }else{
                    if (SingleOnClickUtil.isFastClick()){
                        initPopuEnvironUp(ApplyFwEntity(),0)
                        CommenPop.backgroundAlpha(0.5f, this@YdlrActivity)
                        envirorUpPopu!!.showAtLocation(ll_act_ydlr, Gravity.CENTER, 0, 0)

                    }

                }
            }
        })
        /*ll_act_ydlr_nsqzjdjjfqk.setOnClickListener {//拟申请宅基地及建房情况
            if (ll_act_ydlr_nsqzjdjjfqk1.isShown){
                ll_act_ydlr_nsqzjdjjfqk1.visibility = View.GONE
            }else{
                ll_act_ydlr_nsqzjdjjfqk1.visibility = View.VISIBLE
            }
        }*/
        ll_act_ydlr_fwxx.setOnClickListener {//房屋信息
            if (ll_act_ydlr_fwxx1.isShown){
                ll_act_ydlr_fwxx1.visibility = View.GONE
            }else{
                ll_act_ydlr_fwxx1.visibility = View.VISIBLE
            }
        }
        /*ll_act_ydlr_fwxxupfile.setOnClickListener {//房屋信息下边的上传文件
            if (ll_act_ydlr_fwxxupfile1.isShown){
                ll_act_ydlr_fwxxupfile1.visibility = View.GONE
            }else{
                ll_act_ydlr_fwxxupfile1.visibility = View.VISIBLE
            }
        }*/


    }
    fun getCenter(center: String): LatLng {//将坐标转换成地图识别的类型
        if (center != null) {
            val points = center.substring(6, center.length - 1).split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (points != null && points.size > 1) {
                val converter = CoordinateConverter()
                // CoordType.GPS 待转换坐标类型
                converter.from(CoordinateConverter.CoordType.GPS)
                val sl = LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]))
                // sourceLatLng待转换坐标点 LatLng类型
                converter.coord(sl)
                val latLng = converter.convert()
                return sl
            } else {
                return LatLng(0.0, 0.0)
            }
        }

        return LatLng(0.0, 0.0)
    }
    private fun kuangGeomentLine(dataGeometry: String) {//在地图上框选 高亮显示房屋边界
        if (addPolyline!=null){
            addPolyline?.remove()
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
                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                        }

                        if (mPolylineOptions == null)
//                            mPolygon!!.remove()
                            mPolylineOptions = PolylineOptions()
                        mPolylineOptions!!.getPoints().clear()
                        mPolylineOptions!!.addAll(latList)
                        mPolylineOptions!!.color(Color.argb(255, 0, 0, 255))

                        s = 1
                        addPolyline = aMap!!.addPolyline(mPolylineOptions)

                    }
                }else{
                    val latList = getLatList(split[i])
                    if (i == 0){
                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                    }

                    if (mPolylineOptions == null)
//                            mPolygon!!.remove()
                        mPolylineOptions = PolylineOptions()
                    mPolylineOptions!!.getPoints().clear()
                    mPolylineOptions!!.addAll(latList)
                    mPolylineOptions!!.color(Color.argb(255, 0, 0, 255))
                    s = 1
                    addPolyline = aMap!!.addPolyline(mPolylineOptions)
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

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_ydlr_act_dt?.map
        }
        setUpMap()
    }
    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {
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
                    aMap!!.addTileOverlay(opt_tdtStaNomal)
                    aMap!!.addTileOverlay(opt_tdtnN)
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
//                    aMap!!.addTileOverlay(opt_tdtStaNomal)
//                    aMap!!.addTileOverlay(opt_tdtnN)
                }
                //                aMap.addTileOverlay(opt_cj);
            } else {
                if (l.isCheck)
                    aMap!!.addTileOverlay(l.opt)
            }*/
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() {
        //设置默认定位按钮是否显示
        aMap?.uiSettings?.isMyLocationButtonEnabled = false
        aMap?.uiSettings?.isScaleControlsEnabled = false
        aMap?.uiSettings?.isZoomControlsEnabled = false
//        aMap?.setMapLanguage(AMap.CHINESE)
        aMap?.mapType = AMap.MAP_TYPE_NORMAL

        aMap?.isMyLocationEnabled = false// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap!!.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            override fun onCameraChangeFinish(p0: CameraPosition?) {

            }

            override fun onCameraChange(p0: CameraPosition?) {
                if (p0!!.zoom>18.5){
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(18.5f))
                }
            }
        })
    }
    var mListener: LocationSource.OnLocationChangedListener? = null
    var mLocationClient : AMapLocationClient? =null
    var mLocationOption : AMapLocationClientOption? =null
    var aMapLocation: AMapLocation? = null//定位信息
    //调用定位的方法
    private fun location() {
        //初始化定位
        mLocationClient = AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient!!.setLocationListener(this);
        //初始化定位参数
        mLocationOption = AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.
        //设置是否返回地址信息（默认返回地址信息）
// mLocationOption!!.setNeedAddress(true);
        //设置是否只定位一次,默认为false
// mLocationOption!!.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
// mLocationOption!!.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption!!.setMockEnable(false);
        mLocationOption!!.setLocationCacheEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption!!.setInterval(1000);
        //给定位客户端对象设置定位参数
        mLocationClient!!.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient!!.startLocation();
    }
    var isFirstLoc = true
    override fun onLocationChanged(aMapLocation: AMapLocation?) {
        if (aMapLocation != null) {
            this.aMapLocation = aMapLocation
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                var df =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                var date = Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                /*if (isFirstLoc) {
                    isFirstLoc = false;
                    //设置缩放级别
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f));
                    //将地图移动到定位点
                    val model = PositionUtil.gcj_To_Gps84(aMapLocation.latitude, aMapLocation.longitude)
                    val point = LatLng(model.wgLat, model.wgLon)
                    aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(point));//LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())
                    aMap?.addMarker(MarkerOptions().position(point))
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener!!.onLocationChanged(aMapLocation);
                    //添加图钉
                    // aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    var buffer = StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Toast.makeText(activity!!.getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();

                }*/


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
// Toast.makeText(activity!!.getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    //获取RadioGroup里边选中的值
    private fun getRadioGroup(radioGroup:RadioGroup):String{
        val count: Int = radioGroup.getChildCount()

        for (i in 0 until count) {
            try {
                val rb = radioGroup.getChildAt(i) as RadioButton
                if (rb.isChecked) {
                    return  rb.text.toString()
//                Toast.makeText(this@ServiceRequestActivity, "选中" + rb.tag.toString(), Toast.LENGTH_SHORT).show()
                    break
                }
            }catch (ex:Exception){

            }

        }
        return ""
    }

    override fun initDatas() {
       /* object:Thread(){
            override fun run() {
                super.run()
                sleep(700)

            }
        }.start()*/
        /*fhTitles.add("分户一")
        fhTitles.add("分户二")
        for (i in 0..fhTitles.size - 1) {
            tab_ydlr_fh.addTab(tab_ydlr_fh.newTab().setText(fhTitles[i]))
        }*/

        if (applyEntity!=null){
            if (applyEntity!!.size>0){
                tv_ydlr_add_fh.setOnClickListener { //ApplyLandEntity
                    val content = TextView(this)
                    content.text = "是否添加？"
                    SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("添加分户")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                var applyLandEntity = ApplyLandEntity()
                                applyLandEntity.household = applyEntity!!.size+1
                                applyLandEntity.ylEntity.objectid = applyEntity!!.get(0).ylEntity.objectid
                                applyLandEntity.objectid = applyEntity!!.get(0).objectid
                                applyEntity!!.add(applyLandEntity)
                                setTab()
                                sweetAlertDialog.dismiss()
                            }
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                setTab()
                if (applyEntity!!.get(0).household==0){//
                    tll_act_ydlr_detail.visibility = View.GONE
                    applyEntity!!.get(0).household = 1   //添加时走
                    val arrayList = ArrayList<Int>()
                    arrayList.add(1)
                    arrayList.add(2)
                    arrayList.add(3)
                    arrayList.add(4)
                    arrayList.add(5)
                    aMap!!.setOnMapLoadedListener(object:AMap.OnMapLoadedListener{//地图加载完成  如果没有创建完成弹框会报错
                        override fun onMapLoaded() {
                        slv_act_ydlr.smoothScrollTo(0,0)
                            PopuPointUtils.initPopuSelectNum(this@YdlrActivity,ll_act_ydlr,arrayList,object: PopuPointUtils.OnSelectNumLinster{
                                override fun onClick(num: Int) {
                                    fhCount = num
                                    if (num==1){//隐藏分户证明
                                        ll_act_ydlr_fhzm.visibility = View.GONE
                                    }else{//显示分户证明
                                        ll_act_ydlr_fhzm.visibility = View.VISIBLE
                                        for (i in 0..num-1){
                                            if (i!=0){
                                                var applyLandEntity = ApplyLandEntity()
                                                applyLandEntity.household = i+1//applyEntity!!.size+1
                                                applyLandEntity.ylEntity.objectid = applyEntity!!.get(0).ylEntity.objectid
                                                applyLandEntity.objectid = applyEntity!!.get(0).objectid
                                                applyEntity!!.add(applyLandEntity)
                                                setTab()
                                            }

                                        }
                                    }

                                }
                            })
                        }
                    })
                    tv_act_ydlr_hzxx.visibility = View.GONE
                    selectShow("申请",View.GONE)

                }else{
                    if (applyEntity!!.size>1){///添加后走
                        ll_act_ydlr_fhtab.visibility = View.VISIBLE
                    }else{
                        ll_act_ydlr_fhtab.visibility = View.GONE
                    }

                }
                if (applyEntity!!.get(0).zhaiEntity!=null){
                    val zhaiEntities1 = applyEntity!!.get(0).zhaiEntities
                    for (i in zhaiEntities1){
                        if (i.socialrelat==1){
                            applyEntity!!.get(0).zhaiEntity = i
                        }
                    }
                }

                initApply(applyEntity!!.get(0))//页面赋值
                refreshList(applyEntity!!.get(0))
                tab_ydlr_fh.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }
                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (position!=-1){
                            saveDetail(applyEntity!!.get(position))
                        }
                        position = tab!!.position
//                        applyEntity!!.get(tab!!.position).household = tab!!.position+1
                        initApply(applyEntity!!.get(tab!!.position))//页面赋值
                        refreshList(applyEntity!!.get(tab!!.position))
                    }
                })
            }
        }

    }
    var position = 0
    fun setTab(){
//        tab_ydlr_fh.removeAllViewsInLayout()
        tab_ydlr_fh.removeAllTabs()
        for (i in 0..applyEntity!!.size - 1) {
            tab_ydlr_fh.addTab(tab_ydlr_fh.newTab().setText("分户 ${i+1}"))
        }


    }
    private var fhzmList = ArrayList<ApplyLandFile>()//分户证明
    private var qszmList = ArrayList<ApplyLandFile>()//权属证明
    private var hkbList = ArrayList<ApplyLandFile>()//户口本
    private var fjcnsList = ArrayList<ApplyLandFile>()//翻建承诺书
    private var sqbList = ArrayList<ApplyLandFile>()//申请表
    private var sfzList = ArrayList<ApplyLandFile>()//身份证

    private var fwzpList = ArrayList<ApplyLandFile>()//房屋照片
    private var xcggList = ArrayList<ApplyLandFile>()//现场公告
    private var qzyjbList = ArrayList<ApplyLandFile>()//签字意见表
    private var wztList = ArrayList<ApplyLandFile>()//位置图

    private var ghxkspbList = ArrayList<ApplyLandFile>()//规划许可审批表
    private var ysxczpList = ArrayList<ApplyLandFile>()//验收现场照片
    private var yszlList = ArrayList<ApplyLandFile>()//验收资料

    private var bhspbList = ArrayList<ApplyLandFile>()//驳回审批表

    var fhzmAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var qszmAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var hkbAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var fjcnsAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var sqbAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var sfzAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var fwzpAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var xcggAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var wztAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var qzyjbAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var ghxkspbAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var bhspbAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var ysxczpAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null
    var yszlAdapter: BaseQuickAdapter<ApplyLandFile, BaseViewHolder>? = null

    fun refreshList(applyEntity:ApplyLandEntity ){
        fhzmList.clear()
        qszmList.clear()
        hkbList.clear()
        fjcnsList.clear()
        sqbList.clear()
        sfzList.clear()
        fwzpList.clear()
        xcggList.clear()
        qzyjbList.clear()
        wztList.clear()
        ghxkspbList.clear()
        ysxczpList.clear()
        yszlList.clear()
        bhspbList.clear()
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("分户证明")){
                fhzmList.add(i)
            }
        }
        if (this.applyEntity!!.get(0).sptype!=0){
            if (fhzmList.size==0&&this.applyEntity!!.get(0).sptype>0){
                ll_act_ydlr_fhzm.visibility = View.GONE
            }else{
                ll_act_ydlr_fhzm.visibility = View.VISIBLE
            }
        }

        rlv_act_ydlr_fhzm.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        fhzmAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, fhzmList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("分户证明"))
                                &&(applyEntity.sptype==0||applyEntity.sptype==1))){
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (sqUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }


                iv_item_ydlr_zl_delete.setOnClickListener {
                    val content = TextView(this@YdlrActivity)
                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getApplyFileDelete(item.id,fhzmList,helper.layoutPosition)

                                sweetAlertDialog.dismiss()}
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
//                            .start(activity!!)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs()
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
                if (AppCache.getInstance().duties == 1){
                    iv_item_ydlr_zl_delete.visibility = View.GONE

                }
            }
        }
        rlv_act_ydlr_fhzm.adapter = fhzmAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("权属证明")){
                qszmList.add(i)
            }
        }
        rlv_act_ydlr_qszm.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        qszmAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, qszmList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("权属证明"))
                                &&(applyEntity.sptype==0||applyEntity.sptype==1))){
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (sqUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }


                iv_item_ydlr_zl_delete.setOnClickListener {
                    val content = TextView(this@YdlrActivity)
                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getApplyFileDelete(item.id,qszmList,helper.layoutPosition)

                                sweetAlertDialog.dismiss()}
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
//                            .start(activity!!)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
                if (AppCache.getInstance().duties == 1){
                    iv_item_ydlr_zl_delete.visibility = View.GONE

                }
            }
        }
        rlv_act_ydlr_qszm.adapter = qszmAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("户口本")){
                hkbList.add(i)
            }
        }
        rlv_act_ydlr_hkb.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        hkbAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, hkbList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("户口本"))
                                &&(applyEntity.sptype==0||applyEntity.sptype==1))){
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (sqUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }


                iv_item_ydlr_zl_delete.setOnClickListener {
                    val content = TextView(this@YdlrActivity)
                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getApplyFileDelete(item.id,hkbList,helper.layoutPosition)

                                sweetAlertDialog.dismiss()}
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
//                            .start(activity!!)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
                if (AppCache.getInstance().duties == 1){
                    iv_item_ydlr_zl_delete.visibility = View.GONE

                }
            }
        }
        rlv_act_ydlr_hkb.adapter = hkbAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("翻建承诺书")){
                fjcnsList.add(i)
            }
        }
        rlv_act_ydlr_fjcns.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        fjcnsAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, fjcnsList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("翻建承诺书"))
                                &&(applyEntity.sptype==0||applyEntity.sptype==1))){
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (sqUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }


                iv_item_ydlr_zl_delete.setOnClickListener {
                    val content = TextView(this@YdlrActivity)
                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getApplyFileDelete(item.id,fjcnsList,helper.layoutPosition)

                                sweetAlertDialog.dismiss()}
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
//                            .start(activity!!)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
                if (AppCache.getInstance().duties == 1){
                    iv_item_ydlr_zl_delete.visibility = View.GONE

                }
            }
        }
        rlv_act_ydlr_fjcns.adapter = fjcnsAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("申请表")){
                sqbList.add(i)
            }
        }
        rlv_act_ydlr_sqb.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        sqbAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, sqbList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("申请表"))
                                &&(applyEntity.sptype==0||applyEntity.sptype==1))){
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (sqUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }


                iv_item_ydlr_zl_delete.setOnClickListener {
                    val content = TextView(this@YdlrActivity)
                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getApplyFileDelete(item.id,sqbList,helper.layoutPosition)

                                sweetAlertDialog.dismiss()}
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
//                            .start(activity!!)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
                if (AppCache.getInstance().duties == 1){
                    iv_item_ydlr_zl_delete.visibility = View.GONE

                }
            }
        }
        rlv_act_ydlr_sqb.adapter = sqbAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("身份证")){
                sfzList.add(i)
            }
        }
        rlv_act_ydlr_sfz.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        sfzAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, sfzList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("身份证"))
                                &&(applyEntity.sptype==0||applyEntity.sptype==1))){
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (sqUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }


                iv_item_ydlr_zl_delete.setOnClickListener {
                    val content = TextView(this@YdlrActivity)
                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getApplyFileDelete(item.id,sfzList,helper.layoutPosition)

                                sweetAlertDialog.dismiss()}
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
//                            .start(activity!!)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
                if (AppCache.getInstance().duties == 1){
                    iv_item_ydlr_zl_delete.visibility = View.GONE

                }
            }
        }
        rlv_act_ydlr_sfz.adapter = sfzAdapter
        //审核
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("房屋照片")){
                fwzpList.add(i)
            }
        }
        rlv_act_ydlr_fwzp.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        fwzpAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, fwzpList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("房屋照片"))
                                &&(applyEntity.sptype==2))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (xchsUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=2){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,fwzpList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_fwzp.adapter = fwzpAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("现场公告")){
                xcggList.add(i)
            }
        }
        rlv_act_ydlr_xcgg.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        xcggAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, xcggList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("现场公告"))
                                &&(applyEntity.sptype==2))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (xchsUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=2){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,xcggList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_xcgg.adapter = xcggAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("位置图")){
                wztList.add(i)
            }
        }
        rlv_act_ydlr_wzt.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        wztAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, wztList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("位置图"))
                                &&(applyEntity.sptype==2))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (xchsUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=2){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,wztList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_wzt.adapter = wztAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("签字意见表")){
                qzyjbList.add(i)
            }
        }
        rlv_act_ydlr_qzyjb.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        qzyjbAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, qzyjbList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("签字意见表"))
                                &&(applyEntity.sptype==3))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (spUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=3){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,qzyjbList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_qzyjb.adapter = qzyjbAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("通过申请表")){
                ghxkspbList.add(i)
            }
        }
        rlv_act_ydlr_ghxkspb.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        ghxkspbAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, ghxkspbList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("通过申请表"))
                                &&(applyEntity.sptype==3))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (spUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=3){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,ghxkspbList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_ghxkspb.adapter = ghxkspbAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("驳回申请表")){
                bhspbList.add(i)
            }
        }
        rlv_act_ydlr_bhsqb.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        bhspbAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, bhspbList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("驳回申请表"))
                                &&(applyEntity.sptype==3))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=3){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,bhspbList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_bhsqb.adapter = bhspbAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("验收现场照片")){
                ysxczpList.add(i)
            }
        }
        rlv_act_ydlr_ysxczp.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        ysxczpAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, ysxczpList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("验收现场照片"))
                                &&(applyEntity.sptype==4))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (ysUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=4){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,ysxczpList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_ysxczp.adapter = ysxczpAdapter
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("验收资料")){
                yszlList.add(i)
            }
        }
        rlv_act_ydlr_yszl.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        yszlAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, yszlList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("验收资料"))
                                &&(applyEntity.sptype==4))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    if (ysUpdate)
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=4){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,yszlList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_yszl.adapter = yszlAdapter


        fhqsFileList.clear()
        spbList.clear()
        shhList.clear()
        spList.clear()
        ysList.clear()
        qqList.clear()
        //rlv_act_ydlr_fhqs
        //申请
        /*for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("权属证明")||i.cltype==ApplyFileEnum.getIndex("分户证明")
                    ||i.cltype==ApplyFileEnum.getIndex("翻建承诺书")||i.cltype==ApplyFileEnum.getIndex("申请表")
                    ||i.cltype==ApplyFileEnum.getIndex("身份证")||i.cltype==ApplyFileEnum.getIndex("户口本")){
                fhqsFileList.add(i)
            }
        }
        rlv_act_ydlr_fhqs.layoutManager = GridLayoutManager(this,3)
        fhsqAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, fhqsFileList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                *//*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*//*
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("分户证明")||item.cltype==ApplyFileEnum.getIndex("权属证明")
                                ||item.cltype==ApplyFileEnum.getIndex("翻建承诺书")||item.cltype==ApplyFileEnum.getIndex("申请表")
                                ||item.cltype==ApplyFileEnum.getIndex("身份证")||item.cltype==ApplyFileEnum.getIndex("户口本"))
                                &&(applyEntity.sptype==0||applyEntity.sptype==1))){
                    et_item_ydlr_zl_remark.isEnabled = true
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }


                iv_item_ydlr_zl_delete.setOnClickListener {
                    val content = TextView(this@YdlrActivity)
                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getApplyFileDelete(item.id,fhqsFileList,helper.layoutPosition)

                                sweetAlertDialog.dismiss()}
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()
                }
                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
//                            .start(activity!!)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
                if (AppCache.getInstance().duties == 1){
                    iv_item_ydlr_zl_delete.visibility = View.GONE

                }
            }
        }
        rlv_act_ydlr_fhqs.adapter = fhsqAdapter*/


        //审核
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("房屋照片")||i.cltype==ApplyFileEnum.getIndex("现场公告")
                    ||i.cltype==ApplyFileEnum.getIndex("签字意见表")||i.cltype==ApplyFileEnum.getIndex("位置图")
                    ||i.cltype==ApplyFileEnum.getIndex("四邻意见")){
                spbList.add(i)
            }
        }
        rlv_act_ydlr_sczl.layoutManager = GridLayoutManager(this,3)

        scwjAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, spbList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图")
                                ||item.cltype==ApplyFileEnum.getIndex("四邻意见"))
                                &&(applyEntity.sptype==2))){//||applyEntity.sptype==2
                    et_item_ydlr_zl_remark.isEnabled = true
                    iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                }else{
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    et_item_ydlr_zl_remark.isEnabled = false
                }

                if (applyEntity.sptype<=2){
//                    et_item_ydlr_zl_remark.isEnabled = false
                    iv_item_ydlr_zl_delete.setOnClickListener {
                        val content = TextView(this@YdlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,fhqsFileList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }
                }

                val imageFile = isImageFile(s1)
                if (imageFile){//判断如果是图片的情况下
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp.setOnClickListener {
                        var selectedPhotos = ArrayList<String>()
                        selectedPhotos.add(s1)
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(0)
                                .setShowDeleteButton(false)
                                .start(this@YdlrActivity)//context!!
                    }
                }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                    Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                            .into(iv_item_ydlr_zl_tp!!)
                    iv_item_ydlr_zl_tp!!.setOnClickListener {
                        var path = item.path
                        var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs();
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""
                        val substring = time+item.fileName//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@YdlrActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }
                    }
                }
                /*if (item!!.path.equals("")) {
                    iv_item_ydlr_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                    ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))&&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@YdlrActivity)
                                    .setTitle("请选择上传途径")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    ToastUtils.showShort("" + p1)
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@YdlrActivity!!, helper.layoutPosition + 300)
                                                    } else {
                                                        selectFile(helper.layoutPosition + 400)
                                                    }
                                                    p0!!.dismiss()
                                                }

                                                *//* public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }*//*
                                            }
                                    )
                                    .setNegativeButton("取消", null)
                                    .show();

                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                    ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))&&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                        iv_item_ydlr_zl_delete.setOnClickListener {
                            val content = TextView(this@YdlrActivity)
                            content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,spbList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//判断如果是图片的情况下
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@YdlrActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp!!.setOnClickListener {
                            var path = item.path
                            var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                            if (!storageDir.exists()) {
                                storageDir.mkdirs();
                            }
                            val l = System.currentTimeMillis()
                            val time = l.toString() + ""
                            val substring = time+item.fileName//.substring(time.length - 6, time.length)
                            val file = File(storageDir.path + "/" + substring)
                            if (file.exists()) {
                                FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@YdlrActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                    }

                                    override fun downloadProgress(progress: Progress?) {

                                    }

                                    override fun onFinish() {
                                        super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                    }

                                    override fun onError(response: Response<File>) {
                                        super.onError(response)
                                        LoadingDialog.cancelDialogForLoading()
                                    }
                                })
                            }
                        }
                    }

                }*/

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_sczl.adapter = scwjAdapter
        //审批
        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("房屋照片")||i.cltype==ApplyFileEnum.getIndex("现场公告")
                    ||i.cltype==ApplyFileEnum.getIndex("签字意见表")||i.cltype==ApplyFileEnum.getIndex("位置图")){
                shhList.add(i)
            }
        }
        rlv_act_ydlr_sp.layoutManager = GridLayoutManager(this,3)
        shhAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, shhList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_ydlr_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                    ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))&&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@YdlrActivity)
                                    .setTitle("请选择上传途径")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@YdlrActivity!!, helper.layoutPosition + 300)
                                                    } else {
                                                        selectFile(helper.layoutPosition + 400)
                                                    }
                                                    p0!!.dismiss()
                                                }

                                                /* public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }*/
                                            }
                                    )
                                    .setNegativeButton("取消", null)
                                    .show();

                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                    ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))
                                    &&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                        iv_item_ydlr_zl_delete.setOnClickListener {
                            val content = TextView(this@YdlrActivity)
                            content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,shhList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//判断如果是图片的情况下
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@YdlrActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp!!.setOnClickListener {
                            var path = item.path
                            var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                            if (!storageDir.exists()) {
                                storageDir.mkdirs();
                            }
                            val l = System.currentTimeMillis()
                            val time = l.toString() + ""
                            val substring = time+item.fileName//.substring(time.length - 6, time.length)
                            val file = File(storageDir.path + "/" + substring)
                            if (file.exists()) {
                                FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@YdlrActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                    }

                                    override fun downloadProgress(progress: Progress?) {

                                    }

                                    override fun onFinish() {
                                        super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                    }

                                    override fun onError(response: Response<File>) {
                                        super.onError(response)
                                        LoadingDialog.cancelDialogForLoading()
                                    }
                                })
                            }
                        }
                    }

                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_sp.adapter = shhAdapter
        //通过/驳回

        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("驳回申请表")||i.cltype==ApplyFileEnum.getIndex("通过申请表")
                    ||i.cltype==ApplyFileEnum.getIndex("验收现场照片")||i.cltype==ApplyFileEnum.getIndex("验收资料")){
                spList.add(i)
            }
        }
        rlv_act_ydlr_tgbh.layoutManager = GridLayoutManager(this,3)
        spAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, spList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_ydlr_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                    ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))&&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@YdlrActivity)
                                    .setTitle("请选择上传途径")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@YdlrActivity!!, helper.layoutPosition + 300)
                                                    } else {
                                                        selectFile(helper.layoutPosition + 400)
                                                    }
                                                    p0!!.dismiss()
                                                }

                                                /* public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }*/
                                            }
                                    )
                                    .setNegativeButton("取消", null)
                                    .show();

                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }


                } else {
                    if ((item.cltype==ApplyFileEnum.getIndex("驳回申请表")&&applyEntity.sptype==5)||((item.cltype==ApplyFileEnum.getIndex("通过申请表")
                                    ||item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))&&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                        iv_item_ydlr_zl_delete.setOnClickListener {
                            val content = TextView(this@YdlrActivity)
                            content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,spList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//判断如果是图片的情况下
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@YdlrActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp!!.setOnClickListener {
                            var path = item.path
                            var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                            if (!storageDir.exists()) {
                                storageDir.mkdirs();
                            }
                            val l = System.currentTimeMillis()
                            val time = l.toString() + ""
                            val substring = time+item.fileName//.substring(time.length - 6, time.length)
                            val file = File(storageDir.path + "/" + substring)
                            if (file.exists()) {
                                FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@YdlrActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                    }

                                    override fun downloadProgress(progress: Progress?) {

                                    }

                                    override fun onFinish() {
                                        super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                    }

                                    override fun onError(response: Response<File>) {
                                        super.onError(response)
                                        LoadingDialog.cancelDialogForLoading()
                                    }
                                })
                            }
                        }
                    }

                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_tgbh.adapter = spAdapter
        //验收

        //确权

        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("确权登记")){
                qqList.add(i)
            }
        }
        rlv_act_ydlr_qq.layoutManager = GridLayoutManager(this,3)
        qqAdapter = object : BaseQuickAdapter<ApplyLandFile, BaseViewHolder>(R.layout.item_ydlr_zl, qqList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyLandFile?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_ydlr_zl_bz, ApplyFileEnum.getName(item!!.cltype))//步骤名称
                        ?.setText(R.id.et_item_ydlr_zl_remark, item!!.remark)//备注
                val iv_item_ydlr_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_tp)
                val iv_item_ydlr_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_ydlr_zl_delete)
                val et_item_ydlr_zl_remark = helper.getView<EditText>(R.id.et_item_ydlr_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_ydlr_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_ydlr_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                    ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))&&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@YdlrActivity)
                                    .setTitle("请选择上传途径")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"选项1","选项2","选项3","选项4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("相册", "文件"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@YdlrActivity!!, helper.layoutPosition + 300)
                                                    } else {
                                                        selectFile(helper.layoutPosition + 400)
                                                    }
                                                    p0!!.dismiss()
                                                }

                                                /* public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }*/
                                            }
                                    )
                                    .setNegativeButton("取消", null)
                                    .show();

                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("房屋照片")||item.cltype==ApplyFileEnum.getIndex("现场公告")
                                    ||item.cltype==ApplyFileEnum.getIndex("签字意见表")||item.cltype==ApplyFileEnum.getIndex("位置图"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("验收现场照片")||item.cltype==ApplyFileEnum.getIndex("验收资料"))&&applyEntity.sptype==4)){
                        et_item_ydlr_zl_remark.isEnabled = true
                        iv_item_ydlr_zl_delete.visibility = View.VISIBLE
                        iv_item_ydlr_zl_delete.setOnClickListener {
                            val content = TextView(this@YdlrActivity)
                            content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("删除${ApplyFileEnum.getName(item!!.cltype)}资料")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,qqList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_ydlr_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//判断如果是图片的情况下
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@YdlrActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_ydlr_zl_tp!!)
                        iv_item_ydlr_zl_tp!!.setOnClickListener {
                            var path = item.path
                            var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                            if (!storageDir.exists()) {
                                storageDir.mkdirs();
                            }
                            val l = System.currentTimeMillis()
                            val time = l.toString() + ""
                            val substring = time+item.fileName//.substring(time.length - 6, time.length)
                            val file = File(storageDir.path + "/" + substring)
                            if (file.exists()) {
                                FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@YdlrActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@YdlrActivity, File(storageDir.path + "/" + substring))
                                    }

                                    override fun downloadProgress(progress: Progress?) {

                                    }

                                    override fun onFinish() {
                                        super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                    }

                                    override fun onError(response: Response<File>) {
                                        super.onError(response)
                                        LoadingDialog.cancelDialogForLoading()
                                    }
                                })
                            }
                        }
                    }

                }

                et_item_ydlr_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_ydlr_zl_remark.text.toString().equals("")){
                            item.remark = et_item_ydlr_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_ydlr_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_ydlr_qq.adapter = qqAdapter
        if (AppCache.getInstance().duties == 1){
//            ll_act_ydlr_addfhqs.visibility = View.GONE
            ycydsq("申请",View.GONE)
//            ll_act_ydlr_addshfile.visibility = View.GONE
            ycydsq("添加",View.GONE)
//            ll_act_ydlr_addshfile1.visibility = View.GONE
            ycydsq("审核",View.GONE)
//            ll_act_ydlr_addshfile2.visibility = View.GONE
            ll_act_ydlr_jtcyxx.setIsVisibility(View.GONE)
            ll_act_ydlr_fwxx.setIsVisibility(View.GONE)
//            tv_act_ydlr_jtcyxx_add.visibility = View.GONE
//            tv_act_ydlr_fwxx_add.visibility = View.GONE

        }



    }
    fun ycydsq(name:String,isShow:Int){//name  isShow
        if (name.equals("添加")){
            iv_act_ydlr_hkb.visibility = isShow
            iv_act_ydlr_fjcns.visibility = isShow
            iv_act_ydlr_sqb.visibility = isShow
            iv_act_ydlr_sfz.visibility = isShow
        }else if (name.equals("申请")){
            iv_act_ydlr_fhzm.visibility = isShow
            iv_act_ydlr_qszm.visibility = isShow
            iv_act_ydlr_hkb.visibility = isShow
            iv_act_ydlr_fjcns.visibility = isShow
            iv_act_ydlr_sqb.visibility = isShow
            iv_act_ydlr_sfz.visibility = isShow

        }else if (name.equals("审核")){
            iv_act_ydlr_fwzp.visibility = isShow
            iv_act_ydlr_xcgg.visibility = isShow
            iv_act_ydlr_wzt.visibility = isShow

        }else if (name.equals("审批")){
            iv_act_ydlr_qzyjb.visibility = isShow
            iv_act_ydlr_ghxkspb.visibility = isShow
            iv_act_ydlr_bhsqb.visibility = isShow

        }else if (name.equals("验收")){
            iv_act_ydlr_ysxczp.visibility = isShow
            iv_act_ydlr_yszl.visibility = isShow

        }
    }


    //判断是否是图片   刷新
    fun isImageFile(filePath: String?): Boolean {
        if (filePath!=null){// webp,bmp,jpg,png,tif,gif,apng
//            val split = filePath!!.split(".")
            if (filePath.endsWith(".png")||filePath.endsWith(".jpg")||filePath.endsWith(".jpeg") || filePath.endsWith(".BMP")
                    || filePath.endsWith(".GIF")){
                return true
            }
        }
        return false
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

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public fun showInput( et:EditText) {
        et.requestFocus();
        var imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }
    var xsymkz = 0//用于控制当前显示的步骤
    var ydlrBz = ""
    private fun initApply(fjBean: ApplyLandEntity){//给页面赋值
        zhaiEntities.clear()//清除家庭成员信息
        applyFwEntitys.clear()//清除房屋信息
        if (fjBean.sptype!=0){
            zhaiEntities.addAll(fjBean.zhaiEntities)///添加所有家庭成员信息
        }
        applyFwEntitys.addAll(fjBean.applyFwEntities)//添加所有已有的房屋信息
        applyFileList!!.clear()//清除文件数据
//        fhqsFileList!!.clear()//清除文件数据
        xsymkz = ApplyTypeNumEnum.getIndex(YdlrTypeEnum.getName(fjBean.sptype))
        et_act_ydlr_ss.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,et_act_ydlr_ss.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    et_act_ydlr_ss.setText(data)
                }
            })
        }
        /*if (fjBean.sptype>=2){
            ll_act_ydlr_jtcyxx.setIsVisibility(View.GONE)
//            tv_act_ydlr_jtcyxx_add.visibility = View.GONE
            et_act_ydlr_zjdmj.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_fwmj.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_qszsh.setInputType(InputType.TYPE_NULL)
            rb_act_ydlr_bl.isEnabled = false
            et_act_ydlr_xzjdczqk_bl.setInputType(InputType.TYPE_NULL)
            rb_act_ydlr_tgcjt.isEnabled = false
            rb_act_ydlr_qt.isEnabled = false
            et_act_ydlr_xzjdczqk_qt.setInputType(InputType.TYPE_NULL)

            et_act_ydlr_nsqzjdjjfqk_zjdmj.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_fjzdmj.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_zjdgd.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_hkszd.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_dz.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_nz.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_xz.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_bz.setInputType(InputType.TYPE_NULL)
            rb_act_ydlr_nsqzjdjjfqk_jsyd.isEnabled = false
            rb_act_ydlr_nsqzjdjjfqk_wlyd.isEnabled = false
            rb_act_ydlr_nsqzjdjjfqk_nyd.isEnabled = false
            rb_act_ydlr_nsqzjdjjfqk_yzfj.isEnabled = false
            rb_act_ydlr_nsqzjdjjfqk_gkj.isEnabled = false
            rb_act_ydlr_nsqzjdjjfqk_yzxj.isEnabled = false
            rb_act_ydlr_nsqzjdjjfqk_fhxj.isEnabled = false
            rb_act_ydlr_nsqzjdjjfqk_qt.isEnabled = false
            et_act_ydlr_nsqzjdjjfqk_tybztjbh.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nsqzjdjjfqk_sjtcydsjbz.setInputType(InputType.TYPE_NULL)
            ll_act_ydlr_fwxx.setIsVisibility(View.GONE)
//            tv_act_ydlr_fwxx_add.visibility = View.GONE
//            ll_act_ydlr_addshfile.visibility = View.GONE
            ycydsq("添加",View.GONE)
        }
        if (fjBean.sptype>=3){
            et_act_ydlr_jing.setInputType(InputType.TYPE_NULL)
            et_act_ydlr_nzz.setInputType(InputType.TYPE_NULL)
//            ll_act_ydlr_addshfile1.visibility = View.GONE
            ycydsq("审核",View.GONE)
//            ll_act_ydlr_addshfile2.visibility = View.GONE
        }*/
        //性别
        var sexList = ArrayList<String>()
        val values = SexEnum.values()
        for (i in values){
            sexList.add(i.getName())
        }
        val sexAdapter = ArrayAdapter(this ,R.layout.simple_spinner_item1,sexList)
        sexAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_act_ydlr_sex.adapter = sexAdapter
        //民族
        var mzList = ArrayList<String>()
        val mzValues = NationEnum.values()
        for (i in mzValues){
            mzList.add(i.getName())
        }
        val mzAdapter = ArrayAdapter(this ,R.layout.simple_spinner_item1,mzList)
        mzAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_act_ydlr_mz.adapter = mzAdapter

        val zhaiEntity = fjBean.zhaiEntity //zhaiEntitie

        et_act_ydlr_name.setText(zhaiEntity.housecount)
        sp_act_ydlr_sex.setSelection(sexList.indexOf(zhaiEntity.getSexText()),true)
        et_act_ydlr_sex.setText(zhaiEntity.getSexText())


        et_act_ydlr_ss.setText(zhaiEntity.birthday.toString())//zhaiEntity.age.toString()
        sp_act_ydlr_mz.setSelection(mzList.indexOf(zhaiEntity.nationText),true)
        et_act_ydlr_mz.setText(zhaiEntity.nationText)
        et_act_ydlr_iphone.setText(zhaiEntity.phone)
        et_act_ydlr_idcard.setText(zhaiEntity.idCard)
        et_act_ydlr_hkszd.setText(zhaiEntity.hjdz)
        /*if (zhaiEntity.huType==0){
            rg_act_ydlr_whk.isChecked=true
            rg_act_ydlr_ny.isChecked=false
            rg_act_ydlr_fny.isChecked=false
        }else*/ if (zhaiEntity.huType==1){
//            rg_act_ydlr_whk.isChecked=false
            rg_act_ydlr_ny.isChecked=true
            rg_act_ydlr_fny.isChecked=false
        }else if (zhaiEntity.huType==2){
//            rg_act_ydlr_whk.isChecked=false
            rg_act_ydlr_ny.isChecked=false
            rg_act_ydlr_fny.isChecked=true
        }


        val ylEntity = fjBean.ylEntity
        et_act_ydlr_jing.setText(fjBean.headTitanic.toString())///京
        et_act_ydlr_nzz.setText(fjBean.titanic.toString())///农宅字
        et_act_ydlr_zjdmj.setText(fjBean.ylArea.toString())///ylEntity
        et_act_ydlr_fwmj.setText(fjBean.ylJzmj.toString())///ylEntity
        et_act_ydlr_qszsh.setText(fjBean.qszsh.toString())///ylEntity
//        et_act_ydlr_qszsh.setText(ylEntity.qszsh.toString())//********************************现宅基地处置情况*******************************************
        if (CzqkintEnum.getName(fjBean.czqkint).equals("保留")){///ylEntity
            rb_act_ydlr_bl.isChecked=true
            rb_act_ydlr_tgcjt.isChecked=false
            rb_act_ydlr_qt.isChecked=false
            et_act_ydlr_xzjdczqk_bl.setText(fjBean.czqk)///ylEntity
            et_act_ydlr_xzjdczqk_qt.setText("")
        }else
            if (CzqkintEnum.getName(fjBean.czqkint).equals("退给村集体")){///ylEntity
            rb_act_ydlr_bl.isChecked=false
            rb_act_ydlr_tgcjt.isChecked=true
            rb_act_ydlr_qt.isChecked=false
            et_act_ydlr_xzjdczqk_bl.setText("")
            et_act_ydlr_xzjdczqk_qt.setText("")
        }else
                if (CzqkintEnum.getName(fjBean.czqkint).equals("其他")){///ylEntity
            rb_act_ydlr_bl.isChecked=false
            rb_act_ydlr_tgcjt.isChecked=false
            rb_act_ydlr_qt.isChecked=true
            et_act_ydlr_xzjdczqk_qt.setText(fjBean.czqk)///ylEntity
            et_act_ydlr_xzjdczqk_bl.setText("")
        }
        et_act_ydlr_nsqzjdjjfqk_zjdmj.setText(fjBean.zjdArea.toString())
        et_act_ydlr_nsqzjdjjfqk_fjzdmj.setText(fjBean.fjzdArea.toString())
        et_act_ydlr_nsqzjdjjfqk_zjdgd.setText(fjBean.jdgd.toString())
        et_act_ydlr_nsqzjdjjfqk_hkszd.setText(fjBean.address.toString())
        et_act_ydlr_nsqzjdjjfqk_dz.setText(fjBean.east.toString())
        et_act_ydlr_nsqzjdjjfqk_nz.setText(fjBean.south.toString())
        et_act_ydlr_nsqzjdjjfqk_xz.setText(fjBean.west.toString())
        et_act_ydlr_nsqzjdjjfqk_bz.setText(fjBean.north.toString())
        et_act_ydlr_nsqzjdjjfqk_tybztjbh.setText(fjBean.xybztjbh)
        et_act_ydlr_nsqzjdjjfqk_sjtcydsjbz.setText(fjBean.ftysjbz)
        if (LandTypeEnum.getName(fjBean.landtype).equals("建设用地")){
            rb_act_ydlr_nsqzjdjjfqk_jsyd.isChecked =true
            rb_act_ydlr_nsqzjdjjfqk_wlyd.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_nyd.isChecked =false
        }else
            if (LandTypeEnum.getName(fjBean.landtype).equals("未利用地")){
            rb_act_ydlr_nsqzjdjjfqk_jsyd.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_wlyd.isChecked =true
            rb_act_ydlr_nsqzjdjjfqk_nyd.isChecked =false
        }else
                if (LandTypeEnum.getName(fjBean.landtype).equals("农用地(耕地、林地、草地、其他)")){
            rb_act_ydlr_nsqzjdjjfqk_jsyd.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_wlyd.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_nyd.isChecked =true
        }
        if (HouseTypeEnum.getName(fjBean.jflx).equals("原址翻建")){
            rb_act_ydlr_nsqzjdjjfqk_yzfj.isChecked =true
            rb_act_ydlr_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_qt.isChecked =false
        }else
            if (HouseTypeEnum.getName(fjBean.jflx).equals("改扩建")){
            rb_act_ydlr_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_gkj.isChecked =true
            rb_act_ydlr_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_qt.isChecked =false
        }else
                if (HouseTypeEnum.getName(fjBean.jflx).equals("易址新建")){
            rb_act_ydlr_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_yzxj.isChecked =true
            rb_act_ydlr_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_qt.isChecked =false
        }else
                    if (HouseTypeEnum.getName(fjBean.jflx).equals("分户新建")){
            rb_act_ydlr_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_fhxj.isChecked =true
            rb_act_ydlr_nsqzjdjjfqk_qt.isChecked =false
        }else
                        if (HouseTypeEnum.getName(fjBean.jflx).equals("其他")){
            rb_act_ydlr_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_ydlr_nsqzjdjjfqk_qt.isChecked =true
        }

        rlv_act_ydlr_jtcyxx.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        jtcyxxAdapter = object : BaseQuickAdapter<ZhaiEntity, BaseViewHolder>(R.layout.item_ydlr_act_jtcyxx, zhaiEntities) {
            override fun convert(helper: BaseViewHolder?, item: ZhaiEntity?) {
                if (item!=null){
                    helper?.setText(R.id.tv_item_ydlr_act_xm, item?.housecount)
                            ?.setText(R.id.tv_item_ydlr_act_nl, item?.age.toString())
                            ?.setText(R.id.tv_item_ydlr_act_yhzgx, item?.socialrelatText)
                            ?.setText(R.id.tv_item_ydlr_act_yxsfzjh, item?.idCard)
                            ?.setText(R.id.tv_item_ydlr_act_hkszd, item?.hjdz)
                            ?.setText(R.id.tv_item_ydlr_act_hkxz, item?.huTypeText)
                    val ll_item_ydlr_act_jtcyxx = helper?.getView<LinearLayout>(R.id.ll_item_ydlr_act_jtcyxx)
                    if (item!!.fjhz==1){
                        ll_item_ydlr_act_jtcyxx?.visibility = View.GONE
                    }else{
                        ll_item_ydlr_act_jtcyxx?.visibility = View.VISIBLE
                    }
                    if (sqUpdate){
                        helper?.itemView?.setOnClickListener {
//                    initPopuEnvironUp("")//修改
                            if (SingleOnClickUtil.isFastClick()){
                                initJcyxx(item!!,1)
                                CommenPop.backgroundAlpha(0.5f, this@YdlrActivity)
                                envirorUpPopu!!.showAtLocation(ll_act_ydlr, Gravity.CENTER, 0, 0)
                            }

                        }
                        helper?.itemView?.setOnLongClickListener(object : OnItemClickListener, View.OnLongClickListener {
                            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

                            }

                            override fun onLongClick(p0: View?): Boolean {
                                if (AppCache.getInstance().duties != 1){
                                    val content = TextView(this@YdlrActivity)
                                    content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                                    SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                            .setTitleText("删除家庭成员信息")
                                            .setCustomView(content)
                                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                                if (item.id==0){
                                                    zhaiEntities.removeAt(helper.layoutPosition)
                                                    notifyDataSetChanged()
                                                }else{
                                                    mPresenter.getApplyDeleteHuji(item.id,helper.layoutPosition)
                                                }

                                                sweetAlertDialog.dismiss()}
                                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                            .show()
                                }
                                return false
                            }
                        })
                    }

                }

            }

        }
        rlv_act_ydlr_jtcyxx.adapter = jtcyxxAdapter

        rlv_act_ydlr_fwxx.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        fwxxAdapter = object : BaseQuickAdapter<ApplyFwEntity, BaseViewHolder>(R.layout.item_ydlr_act_fwxx, applyFwEntitys) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFwEntity?) {
                helper?.setText(R.id.tv_item_ydlr_act_fwmj, item?.area.toString())
                        ?.setText(R.id.tv_item_ydlr_act_fwcs, item?.fwcs.toString())
                        ?.setText(R.id.tv_item_ydlr_act_fwgd, item?.fwgd.toString())
                if (sqUpdate) {//helper?.itemView
                    helper!!.getView<LinearLayout>(R.id.ll_item_ydlr_act_fwxx)?.setOnClickListener {
                        if (SingleOnClickUtil.isFastClick()) {
                            initPopuEnvironUp(item!!, 1)//修改
                            CommenPop.backgroundAlpha(0.5f, this@YdlrActivity)
                            envirorUpPopu!!.showAtLocation(ll_act_ydlr, Gravity.CENTER, 0, 0)
                        }
                    }
                    helper!!.getView<LinearLayout>(R.id.ll_item_ydlr_act_fwxx)?.setOnLongClickListener(object : OnItemClickListener, View.OnLongClickListener {
                        override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

                        }

                        override fun onLongClick(p0: View?): Boolean {
                            if (AppCache.getInstance().duties != 1){
                                val content = TextView(this@YdlrActivity)
                                content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                                SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                        .setTitleText("删除房屋信息")
                                        .setCustomView(content)
                                        .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                            if (item?.id == 0) {
                                                applyFwEntitys.removeAt(helper.layoutPosition)
                                                notifyDataSetChanged()
                                            } else {
                                                mPresenter.getApplyDeleteFjfw(item!!.id, helper.layoutPosition)
                                            }
                                            sweetAlertDialog.dismiss()
                                        }
                                        .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                        .show()
                            }

                            return false
                        }
                    })
                }
                /*helper?.itemView?.setOnClickListener {
                    initPopuEnvironUp(item!!,1)//修改
                    CommenPop.backgroundAlpha(0.5f, this@ydlrActivity)
                    envirorUpPopu!!.showAtLocation(ll_act_ydlr, Gravity.CENTER, 0, 0) }
                helper?.itemView?.setOnLongClickListener(object : OnItemClickListener, View.OnLongClickListener {
                    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

                    }

                    override fun onLongClick(p0: View?): Boolean {
                        val content = TextView(this@ydlrActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@ydlrActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除房屋信息")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    if (item?.id ==0){
                                        applyFwEntitys.removeAt(helper.layoutPosition)
                                        notifyDataSetChanged()
                                    }else{
                                        mPresenter.getApplyDeleteFjfw(item!!.id,helper.layoutPosition)
                                    }
                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                        return false
                    }
                })*/
            }

        }
        rlv_act_ydlr_fwxx.adapter = fwxxAdapter
        var sptypeList = ArrayList<String>()
        sptypeList.add("申请")
        sptypeList.add("现场核实")
//        sptypeList.add("审批")
        sptypeList.add("审批")
       /* if (fjBean.sptype==4){
            sptypeList.add("通过")
            tv_ydrl_act_tgbh.setText("通过")
        }else if (fjBean.sptype==5){
            sptypeList.add("驳回")
            tv_ydrl_act_tgbh.setText("驳回")
        }else if (fjBean.sptype==6){
            sptypeList.add("通过")
            tv_ydrl_act_tgbh.setText("通过")
        }else{
            sptypeList.add("通过/驳回")
            tv_ydrl_act_tgbh.setText("通过/驳回")
        }*/
        sptypeList.add("验收")
        var selectCount = 0
        if (xsymkz==0){
            selectCount = 0
            tll_act_ydlr_detail.setValueText(sptypeList,xsymkz,xsymkz)
        }else if (xsymkz==3){//通过
            selectCount = 3
            tll_act_ydlr_detail.setValueText(sptypeList,3,3)
        }else if (xsymkz==4){//驳回
            selectCount = 2
            tll_act_ydlr_detail.setValueText(sptypeList,2,2)
        }else if (xsymkz==7){//验收(完成)
            selectCount = 2
            tll_act_ydlr_detail.setValueText(sptypeList,2,2)
        }else if (xsymkz==5){//验收(完成)
            selectCount = 3
            tll_act_ydlr_detail.setValueText(sptypeList,3,3)
        }else{
            selectCount = xsymkz-1
            tll_act_ydlr_detail.setValueText(sptypeList,xsymkz-1,xsymkz-1)
        }
        tll_act_ydlr_detail.setOnTimeLineLister(object:TimeLineLayout.OnTimeLineLister{
            override fun onClick(timeLineBean: TimeLineBean?, position: Int) {

                if (timeLineBean?.selected ==1){
                    sqUpdate = false
                    xchsUpdate = false
                    spUpdate = false
                    ysUpdate = false
                    val sptypeString = timeLineBean?.name
                    tv_ydlr_act_xyb.visibility = View.GONE
                    tv_ydlr_act_tg.visibility = View.GONE
                    tv_ydlr_act_delete.visibility = View.GONE
                    ll_act_ydlr_buts.visibility = View.GONE
                    selectShow("申请",View.GONE)
                    selectShow("现场核实",View.GONE)
                    selectShow("审批",View.GONE)
                    selectShow("验收",View.GONE)
                    tv_act_ydlr_update.setText("修改")
                    if (sptypeString.equals("申请")){
                        ydlrBz = "申请"
                        ll_ydlr_addsq.visibility = View.VISIBLE
                        if (xsymkz != 0){//非分户状态
                            ll_ydlr_sqb.visibility = View.VISIBLE
                        }

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_sh.visibility = View.GONE
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//完成
//                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else
                        if (sptypeString.equals("现场核实")){
                        ydlrBz = "现场核实"
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.GONE
                        ll_ydlr_sh.visibility = View.VISIBLE

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//完成
//                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else
                            if (sptypeString.equals("审批")){//sptypeString.equals("通过")||sptypeString.equals("驳回")||sptypeString.equals("通过/驳回")
                        ydlrBz = "审批"
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.GONE//申请表
                        ll_ydlr_sh.visibility = View.GONE

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.VISIBLE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//完成
//                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else
                                if (sptypeString.equals("验收")){
                        ydlrBz = "验收"
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.GONE
                        ll_ydlr_sh.visibility = View.GONE

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.VISIBLE//完成
//                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权


                    }
                    if (sptypeList.get(selectCount).equals(sptypeString)){//判断是否是选择最后一步
                        showBut(fjBean)
                        if (fjBean.sptype!=5){
                            ll_act_ydlr_buts.visibility = View.VISIBLE
                        }
                        tv_act_ydlr_update.visibility = View.GONE
                        tv_act_ydlr_update.setText("修改")

                        if (fjBean.sptype!=6&&fjBean.sptype!=5){
                            if (sptypeString.equals("申请")){
                                sqUpdate = true
                                selectShow("申请",View.VISIBLE)
                            }else if (sptypeString.equals("现场核实")){
                                xchsUpdate = true
                                selectShow("现场核实",View.VISIBLE)
                            }else if (sptypeString.equals("审批")){

                                spUpdate = true
                                selectShow("审批",View.VISIBLE)
                            }else if (sptypeString.equals("验收")){
                                ysUpdate = true
                                selectShow("验收",View.VISIBLE)
                            }
                        }
                    }else{
                            if (fjBean.sptype<=3){//如果到审批之后的步骤不需要显示修改按钮
                                tv_act_ydlr_update.visibility = View.VISIBLE
                                if (sptypeString.equals("")&&fjBean.sptype==1){

                                }
                            }else{
                                ll_act_ydlr_buts.visibility = View.GONE
                            }

                    }

                }
            }
        })
        tv_act_ydlr_update.setOnClickListener {
            if (tv_act_ydlr_update.text.toString().equals("修改")){
                tv_act_ydlr_update.setText("取消")
                sqUpdate = true
                xchsUpdate = true
                spUpdate = true
                ysUpdate = true
                selectShow("申请",View.VISIBLE)
                selectShow("现场核实",View.VISIBLE)
                selectShow("审批",View.VISIBLE)
                selectShow("验收",View.VISIBLE)
                ll_act_ydlr_buts.visibility = View.VISIBLE
//                tv_act_ydlr_hzxx.visibility = View.VISIBLE
//                tv_act_ydlr_xzjdjfwqk.visibility = View.VISIBLE
//                tv_act_ydlr_nsqzjdjjfqk.visibility = View.VISIBLE

            }else{
                tv_act_ydlr_update.setText("修改")
                sqUpdate = false
                xchsUpdate = false
                spUpdate = false
                ysUpdate = false
                selectShow("申请",View.GONE)
                selectShow("现场核实",View.GONE)
                selectShow("审批",View.GONE)
                selectShow("验收",View.GONE)
                ll_act_ydlr_buts.visibility = View.GONE
//                tv_act_ydlr_hzxx.visibility = View.GONE
//                tv_act_ydlr_xzjdjfwqk.visibility = View.GONE
//                tv_act_ydlr_nsqzjdjjfqk.visibility = View.GONE

            }
        }

        mtl_act_ydlr_jtxx_upfile.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (ll_act_ydlr_jtxx_upfile.isShown){
                    ll_act_ydlr_jtxx_upfile.visibility = View.GONE
                }else{
                    ll_act_ydlr_jtxx_upfile.visibility = View.VISIBLE
                }
            }
        })
        mtl_act_ydlr_sh_upfile.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (ll_ydlr_act_sczl.isShown){
                    ll_ydlr_act_sczl.visibility = View.GONE
                }else{
                    ll_ydlr_act_sczl.visibility = View.VISIBLE
                }
            }
        })


//        sptypeList.add("验收")
//        sptypeList.add("确权")
        //ApplyTypeEnum
        if (fjBean.sptype<2){//判断如果没有文号就隐藏文号  否则显示********************************
            trl_act_ydlr_wh.visibility = View.GONE
        }else{
            trl_act_ydlr_wh.visibility = View.VISIBLE
            et_act_ydlr_jing.setText(fjBean.headTitanic)
            et_act_ydlr_nzz.setText(fjBean.titanic)

        }
        showBut(fjBean)
        if (fjBean.sptype>1){//默认必须显示资料模块
            ll_ydlr_act_sczl.visibility = View.VISIBLE
        }else{
            ll_ydlr_act_sczl.visibility = View.VISIBLE
        }

        /*tlv_ydlr_sjz.setPointStrings(sptypeList, sptypeList.indexOf(YdlrTypeEnum.getName(fjBean.sptype))+1f)//设置进度轴
        tlv_ydlr_sjz.setOnStepChangedListener(object : TimeLineView.OnStepChangedListener {//
            override fun onchanged(view: TimeLineView?, step: Int, stepStr: String?) {//xsymkz
                if (step>=0&&step<sptypeList.size&&step<=xsymkz){//xsymkz
                    val sptypeString = sptypeList.get(step)
                    if (sptypeString.equals("申请")){
                        ll_ydlr_addsq.visibility = View.VISIBLE
                        ll_ydlr_sqb.visibility = View.GONE//申请表

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else if (sptypeString.equals("审核")){
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.VISIBLE//申请表

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else if (sptypeString.equals("审批")){
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.GONE//申请表

                        ll_ydlr_sp.visibility = View.VISIBLE//审批
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else if (sptypeString.equals("通过")||sptypeString.equals("驳回")||sptypeString.equals("通过/驳回")){
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.GONE//申请表

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.VISIBLE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else if (sptypeString.equals("验收")){
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.GONE//申请表

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.VISIBLE//验收
                        ll_ydlr_qq.visibility = View.GONE//确权
                    }else if (sptypeString.equals("确权")){
                        ll_ydlr_addsq.visibility = View.GONE
                        ll_ydlr_sqb.visibility = View.GONE//申请表

                        ll_ydlr_sp.visibility = View.GONE//审批
                        ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                        ll_ydlr_ys.visibility = View.GONE//验收
                        ll_ydlr_qq.visibility = View.VISIBLE//确权
                    }

                }
            }

        })*/
        selectShow("申请",View.GONE)
        selectShow("现场核实",View.GONE)
        selectShow("审批",View.GONE)
        selectShow("验收",View.GONE)
        if (xsymkz==0){
            ydlrBz = "申请"
            ll_act_ydlr_addfhqs.visibility = View.VISIBLE
            ycydsq("申请",View.VISIBLE)
            ll_act_ydlr_addfhqs3.visibility = View.GONE//占位隐藏
            ll_ydlr_sh.visibility = View.GONE
            ll_act_ydlr_addshfile.visibility = View.GONE
            ycydsq("添加",View.GONE)
            ll_ydlr_addsq.visibility = View.VISIBLE
//            ll_act_ydlr_tg.visibility = View.GONE
            ycydsq("审批",View.GONE)
//            ll_act_ydlr_bh.visibility = View.GONE

            selectShow("申请",View.VISIBLE)//这个时候写的控制
            ll_ydlr_sqb.visibility = View.GONE//申请表
            ll_ydlr_sp.visibility = View.GONE//审批
            ll_ydlr_sh.visibility = View.GONE
            ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
            ll_ydlr_ys.visibility = View.GONE//完成
            ll_ydlr_qq.visibility = View.GONE//确权
        }else
            if (xsymkz==1){
//            et_act_ydlr_sex.visibility = View.VISIBLE//隐藏显示性别的输入框
//            sp_act_ydlr_sex.visibility = View.GONE//显示选择性别的弹框
//            et_act_ydlr_mz.visibility = View.VISIBLE//隐藏显示民族的输入框
//            sp_act_ydlr_mz.visibility = View.GONE//显示选择民族的弹框
//            ll_act_ydlr_addfhqs.visibility = View.VISIBLE
//            ll_act_ydlr_addshfile.visibility = View.VISIBLE
            ycydsq("申请",View.VISIBLE)
            ll_ydlr_addsq.visibility = View.VISIBLE
            ll_ydlr_sqb.visibility = View.VISIBLE//申请表
//            ll_act_ydlr_tg.visibility = View.GONE
            ycydsq("审批",View.GONE)
//            ll_act_ydlr_bh.visibility = View.GONE

                selectShow("申请",View.VISIBLE)
            ll_ydlr_sh.visibility = View.GONE
            ll_ydlr_sp.visibility = View.GONE//审批
            ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
            ll_ydlr_ys.visibility = View.GONE//完成
//            ll_ydlr_ys.visibility = View.GONE//验收
            ll_ydlr_qq.visibility = View.GONE//确权
        }else
            if (xsymkz==2){

//            et_act_ydlr_sex.visibility = View.VISIBLE
//            sp_act_ydlr_sex.visibility = View.GONE
//            et_act_ydlr_mz.visibility = View.VISIBLE
//            sp_act_ydlr_mz.visibility = View.GONE
//            ll_act_ydlr_addfhqs.visibility = View.GONE
                ycydsq("申请",View.GONE)
                ll_ydlr_addsq.visibility = View.GONE
//                ll_act_ydlr_tg.visibility = View.GONE
                ycydsq("审批",View.GONE)
//                ll_act_ydlr_bh.visibility = View.GONE

                selectShow("现场核实",View.VISIBLE)
            ll_ydlr_sqb.visibility = View.GONE//申请表
            ll_ydlr_sh.visibility = View.VISIBLE
            ll_ydlr_sp.visibility = View.GONE//审批
            ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                ll_ydlr_ys.visibility = View.GONE//完成
//            ll_ydlr_ys.visibility = View.GONE//验收
            ll_ydlr_qq.visibility = View.GONE//确权
        }else
                if (xsymkz==3){
                    tv_act_ydlr_nzz1.visibility = View.VISIBLE
                    ll_act_ydlr_bhsqb.visibility = View.GONE
//                    et_act_ydlr_sex.visibility = View.VISIBLE
//                    sp_act_ydlr_sex.visibility = View.GONE
//            et_act_ydlr_mz.visibility = View.VISIBLE
//            sp_act_ydlr_mz.visibility = View.GONE
//            ll_act_ydlr_addfhqs.visibility = View.GONE
                ycydsq("申请",View.GONE)
            ll_ydlr_addsq.visibility = View.GONE
//                ll_act_ydlr_bh.visibility = View.GONE
//                ll_act_ydlr_tg.visibility = View.VISIBLE
                ycydsq("审批",View.GONE)


                    selectShow("验收",View.VISIBLE)
            ll_ydlr_sqb.visibility = View.GONE//申请表
                ll_ydlr_sh.visibility = View.GONE
            ll_ydlr_sp.visibility = View.GONE//审批
            ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                ll_ydlr_ys.visibility = View.VISIBLE//完成
//            ll_ydlr_ys.visibility = View.GONE//验收
            ll_ydlr_qq.visibility = View.GONE//确权


        } else
                    if (xsymkz==4){
                        tv_act_ydlr_nzz1.visibility = View.VISIBLE
//            et_act_ydlr_sex.visibility = View.VISIBLE
//            sp_act_ydlr_sex.visibility = View.GONE
//            ll_act_ydlr_qzyjb.visibility = View.GONE
//            ll_act_ydlr_tgsqb.visibility = View.GONE
//            et_act_ydlr_mz.visibility = View.VISIBLE
//            sp_act_ydlr_mz.visibility = View.GONE
//            ll_act_ydlr_addfhqs.visibility = View.GONE
                ycydsq("申请",View.GONE)
            ll_ydlr_addsq.visibility = View.GONE
//                ll_act_ydlr_bh.visibility = View.VISIBLE
//                ll_act_ydlr_tg.visibility = View.GONE
                ycydsq("审批",View.GONE)

                        ll_act_ydlr_buts.visibility = View.GONE
//                        selectShow("审批",View.VISIBLE)
            ll_ydlr_sqb.visibility = View.GONE//申请表
                ll_ydlr_sh.visibility = View.GONE
            ll_ydlr_sp.visibility = View.GONE//审批
            ll_ydlr_tgbh.visibility = View.VISIBLE//通过/驳回
                ll_ydlr_ys.visibility = View.GONE//完成
//            ll_ydlr_ys.visibility = View.GONE//验收
            ll_ydlr_qq.visibility = View.GONE//确权

        }else
                        if (xsymkz==7){
                            tv_act_ydlr_nzz1.visibility = View.VISIBLE
//                            et_act_ydlr_sex.visibility = View.VISIBLE
//                            sp_act_ydlr_sex.visibility = View.GONE
//                            et_act_ydlr_mz.visibility = View.VISIBLE
//                            sp_act_ydlr_mz.visibility = View.GONE
//            ll_act_ydlr_addfhqs.visibility = View.GONE
                            ycydsq("申请",View.GONE)
                            ll_ydlr_addsq.visibility = View.GONE
//                ll_act_ydlr_bh.visibility = View.VISIBLE
//                ll_act_ydlr_tg.visibility = View.GONE
                            ycydsq("审批",View.VISIBLE)


                            selectShow("审批",View.VISIBLE)
                            ll_ydlr_sqb.visibility = View.GONE//申请表
                            ll_ydlr_sh.visibility = View.GONE
                            ll_ydlr_sp.visibility = View.GONE//审批
                            ll_ydlr_tgbh.visibility = View.VISIBLE//通过/驳回
                            ll_ydlr_ys.visibility = View.GONE//完成
//            ll_ydlr_ys.visibility = View.GONE//验收
                            ll_ydlr_qq.visibility = View.GONE//确权

                        }else
                    if (xsymkz==5){
                        tv_act_ydlr_nzz1.visibility = View.VISIBLE
//            et_act_ydlr_sex.visibility = View.VISIBLE
//            sp_act_ydlr_sex.visibility = View.GONE
//            et_act_ydlr_mz.visibility = View.VISIBLE
//            sp_act_ydlr_mz.visibility = View.GONE
//            ll_act_ydlr_addfhqs.visibility = View.GONE
                ycydsq("申请",View.GONE)
            ll_ydlr_addsq.visibility = View.GONE
//                ll_act_ydlr_bh.visibility = View.GONE
//                ll_act_ydlr_tg.visibility = View.GONE
                ycydsq("审批",View.GONE)

//                        selectShow("验收",View.VISIBLE)
                        selectShow("验收",View.GONE)
            ll_ydlr_sqb.visibility = View.GONE//申请表
                ll_ydlr_sh.visibility = View.GONE
            ll_ydlr_sp.visibility = View.GONE//审批
            ll_ydlr_tgbh.visibility = View.GONE//通过/驳回
                ll_ydlr_ys.visibility = View.VISIBLE//完成
//            ll_ydlr_ys.visibility = View.GONE//验收
            ll_ydlr_qq.visibility = View.GONE//确权

        }else
                        if (xsymkz==6){
                            tv_act_ydlr_nzz1.visibility = View.VISIBLE
                            tv_ydlr_act_sure.visibility = View.GONE

                            ycydsq("验收",View.GONE)//确权
                            ll_act_ydlr_bhsqb.visibility = View.GONE
                        }
        applyFileList!!.addAll(fjBean.applyLandFiles)//将已有的文件放入集合
        val hashMap = HashMap<Int, Int>()//使用 hashmap 统计各个类型分别有了多少个资料
        for (i in ApplyFileEnum.values()){//默认赋值为零
            hashMap.put(i.index,0)
        }
        for (i in applyFileList!!){
            hashMap.put(i.cltype, hashMap.get(i.cltype)!!.toInt()+1)
        }
        /*if (fjBean.sptype==2){//判断如果是审批
            for (i in 0..3- (hashMap.get(ApplyFileEnum.getIndex("房屋照片"))!!.toInt())){
                val applyFileEntity1 = ApplyLandFile()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("房屋照片")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("现场公告"))!!.toInt()<1){
                val applyFileEntity1 = ApplyLandFile()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("现场公告")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("签字意见表"))!!.toInt()<1){
                val applyFileEntity1 = ApplyLandFile()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("签字意见表")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("位置图"))!!.toInt()<1){
                val applyFileEntity1 = ApplyLandFile()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("位置图")
                applyFileList!!.add(applyFileEntity1)
            }
        }
        if (fjBean.sptype==4){
            for (i in 0..3- (hashMap.get(ApplyFileEnum.getIndex("验收现场照片"))!!.toInt())){
                val applyFileEntity1 = ApplyLandFile()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("验收现场照片")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("验收资料"))!!.toInt()<1){
                val applyFileEntity1 = ApplyLandFile()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("验收资料")
                applyFileList!!.add(applyFileEntity1)
            }
        }*/
        /*if (fjBean.sptype!=2&&fjBean.sptype!=4){//判断只有处于审批和验收状态下显示修改按钮
            tv_ydlr_act_sure.setText("修改")//添加
            tv_ydlr_act_sure.visibility = View.GONE
            tv_ydlr_act_xyb.visibility = View.GONE
            tv_ydlr_act_delete.visibility = View.GONE
        }else{
            tv_ydlr_act_sure.setText("修改")
            tv_ydlr_act_sure.visibility = View.VISIBLE
            tv_ydlr_act_xyb.visibility = View.GONE
            tv_ydlr_act_delete.visibility = View.GONE
        }*/

        tv_ydlr_act_delete.setOnClickListener {//是否删除当前翻建信息
            val content = TextView(this@YdlrActivity)
            content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
            SweetAlertDialog(this@YdlrActivity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("删除用地信息")
                    .setCustomView(content)
                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                        mPresenter.getApplyDelete(fjBean.id)//ylEntity  fjBean.objectid
                        sweetAlertDialog.dismiss()}
                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                    .show()
        }
        tv_ydlr_act_sure.setOnClickListener {///确定修改  目前手机端只允许修改  现场拍照
            if (SingleOnClickUtil.isFastClick()){
                saveDetail(fjBean)
                if (fjBean.sptype==0){//添加

                    if (fhCount>1&&fhzmList.size==0){
                        ToastUtils.showShort("请上传分户证明")
                    }else{
                        for (i in 0..applyEntity!!.size-1){
                            yuanSptype = applyEntity!!.get(i).sptype
                            if (i!=0){
//                            applyEntity!!.get(i).zhaiEntities = applyEntity!!.get(0).zhaiEntities
                                applyEntity!!.get(i).ylEntity = applyEntity!!.get(0).ylEntity
                                applyEntity!!.get(i).objectid = applyEntity!!.get(0).ylEntity.objectid
                            }
                            applyEntity!!.get(i).household = i+1
                            applyEntity!!.get(i).sptype = 1
                        }
                        mPresenter.getApplySave(applyEntity!!)//ApplyLandEntity
                    }
                }else{//修改
                    for (i in applyEntity!!.get(0).zhaiEntities){
                        i.household = applyEntity!!.get(0).household
                    }
                    mPresenter.getApplyUpdate(applyEntity!!)
                }
            }
        }
        tv_ydlr_act_tg.setOnClickListener {///确定修改  目前手机端只允许修改  现场拍照
            if (SingleOnClickUtil.isFastClick()){
                saveDetail(fjBean)
                for (i in applyEntity!!){
                    i.sptype = 5
                    yuanSptype = i.sptype
                    for (f in i.zhaiEntities){
                        f.household = i.household
                    }
                }
                mPresenter.getApplyUpdate(applyEntity!!)
            }
        }
        tv_ydlr_act_xyb.setOnClickListener {///确定修改  目前手机端只允许修改  现场拍照
            if (SingleOnClickUtil.isFastClick()){
                saveDetail(fjBean)
                for (i in applyEntity!!){
                    yuanSptype = i.sptype
                    /*if (tv_ydlr_act_xyb.text.toString().equals("通过")){

                    }else{*/
                        /*if (i.sptype==2){//中间步骤去除（3）
                            i.sptype = 4
                        }else */if (i.sptype==4){//如果是通过直接到完成  跳过驳回（5）
                            i.sptype = 6
                        }else{
                            i.sptype = i.sptype+1
                        }
//                    }

                    for (f in i.zhaiEntities){
                        f.household = i.household
                    }
                }
                mPresenter.getApplyUpdate(applyEntity!!)
               /* if (fjBean.sptype==0){//添加
                    for (i in applyEntity!!){
                        i.sptype = 2
                    }
                    mPresenter.getApplySave(applyEntity!!)//ApplyLandEntity
                }else{//修改

                }*/
            }
        }

        if (fjBean!=null&&fjBean.geometry != null && !fjBean.geometry.equals("")) {//地图上高亮显示  ylEntity
            if (!fjBean.geometry.equals("")) {
                kuangGeomentLine(fjBean.geometry)

            }

        }
        bt_ydlr_act_dt.setOnClickListener {//点击跳转高德地图 或者百度
            val center1 = getCenter(fjBean.center)//ylEntity
            if (AppInstalledUtils.isAppInstalled(this, "com.autonavi.minimap")){//跳转高德地图
                var intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                //获取本地存储的百度经纬度
                //获取本地存储的百度经纬度

                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
//                            val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84转火星
                val transform = PositionUtil.wgs84togcj02(center1.longitude, center1.latitude)//84转火星  center1.latitude
//                            val point = LatLng(model.wgLat, model.wgLon)
                val uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + aMapLocation!!.latitude  + "&slon=" + aMapLocation!!.longitude + "&sname=" + "自己位置" + "&did=BGVIS2&dlat=" + transform[0].toString() + "&dlon=" + transform[1].toString() + "&dname=" + "目的地" + "&dev=0&t=0")
                intent.data = uri
                //启动该页面即可
                startActivity(intent)
            }else if (AppInstalledUtils.isAppInstalled(this, "com.baidu.BaiduMap")){//跳转百度地图
                var intent = Intent()
                intent.data = Uri.parse("baidumap://map/direction?region=目的地&destination=" + center1.latitude.toString() + "," + center1.longitude.toString() + "&mode=driving" +//+ "&destination=" + ""
                        "&coord_type=wgs84")
                startActivity(intent)
            }else{
                ToastUtils.showShort("请安装百度地图或高德地图")

            }

        }
        //禁止修改
        tv_act_ydlr_hzxx.setOnClickListener {  }
        tv_act_ydlr_xzjdjfwqk.setOnClickListener {  }
        tv_act_ydlr_nsqzjdjjfqk.setOnClickListener {  }
        tv_act_ydlr_nzz1.setOnClickListener {  }
        if (AppCache.getInstance().duties == 1){
            tv_ydlr_act_sure.visibility = View.GONE
            tv_ydlr_act_xyb.visibility = View.GONE
            tv_ydlr_act_tg.visibility = View.GONE
            tv_ydlr_act_delete.visibility = View.GONE


        }
    }

    private fun showBut(fjBean: ApplyLandEntity){
        if (fjBean.sptype!=0){
            tv_ydlr_act_sure.setText("保存")//修改
            if (fjBean.sptype==1){
                tv_ydlr_act_xyb.visibility = View.VISIBLE
//                tv_ydlr_act_delete.visibility = View.VISIBLE
            }else if (fjBean.sptype==2){
                tv_ydlr_act_xyb.setText("下一步")
                tv_ydlr_act_xyb.visibility = View.VISIBLE//下一步
//                tv_ydlr_act_delete.visibility = View.VISIBLE//删除
            }else if (fjBean.sptype==3){
                tv_ydlr_act_xyb.setText("通过")
                tv_ydlr_act_xyb.visibility = View.VISIBLE//下一步
                tv_ydlr_act_tg.visibility = View.VISIBLE//驳回
//                tv_ydlr_act_delete.visibility = View.VISIBLE//删除
            }else if (fjBean.sptype==4){
                tv_ydlr_act_xyb.setText("完成")
                tv_ydlr_act_xyb.visibility = View.VISIBLE
//                ll_act_ydlr_tg.visibility = View.GONE
                ycydsq("审批",View.GONE)
//                ll_act_ydlr_bh.visibility = View.VISIBLE

            }else if (fjBean.sptype==5){
//                ll_act_ydlr_tg.visibility = View.GONE
                ycydsq("审批",View.GONE)
//                ll_act_ydlr_bh.visibility = View.VISIBLE

            }else if (fjBean.sptype==6){
                ycydsq("验收",View.GONE)
                tv_ydlr_act_sure.visibility = View.GONE
            }
        }else{
            tv_ydlr_act_sure.setText("保存")
        }
    }

    private fun selectShow(name:String,isShow:Int){//View.VISIBLE 表示可以修改   View.GONE 表示禁止修改
        if (name.equals("申请")){
            //上传图片
            iv_act_ydlr_fhzm.visibility = isShow
            iv_act_ydlr_qszm.visibility = isShow
            iv_act_ydlr_hkb.visibility = isShow
            iv_act_ydlr_fjcns.visibility = isShow
            iv_act_ydlr_sqb.visibility = isShow
            iv_act_ydlr_sfz.visibility = isShow
            //添加房屋按钮
            ll_act_ydlr_fwxx.setIsVisibility(isShow)
            ll_act_ydlr_jtcyxx.setIsVisibility(isShow)
            //控制覆盖物
            if (isShow==View.VISIBLE){
//                ToastUtils.showShort("隐藏")
                tv_act_ydlr_hzxx.visibility = View.GONE//
                tv_act_ydlr_xzjdjfwqk.visibility = View.GONE
                tv_act_ydlr_nsqzjdjjfqk.visibility = View.GONE
            }else{
//                ToastUtils.showShort("显示")
                tv_act_ydlr_hzxx.visibility = View.VISIBLE//
                tv_act_ydlr_xzjdjfwqk.visibility = View.VISIBLE
                tv_act_ydlr_nsqzjdjjfqk.visibility = View.VISIBLE
            }
//            jtcyxxAdapter?.setNewData(zhaiEntities)
            rlv_act_ydlr_jtcyxx.adapter = jtcyxxAdapter
            rlv_act_ydlr_fwxx.adapter = fwxxAdapter

            rlv_act_ydlr_fhzm.adapter = fhzmAdapter
            rlv_act_ydlr_qszm.adapter = qszmAdapter
            rlv_act_ydlr_hkb.adapter = hkbAdapter
            rlv_act_ydlr_fjcns.adapter = fjcnsAdapter
            rlv_act_ydlr_sqb.adapter = sqbAdapter
            rlv_act_ydlr_sfz.adapter = sfzAdapter


        }
        if (name.equals("现场核实")){
            if (isShow==View.VISIBLE){
                tv_act_ydlr_nzz1.visibility = View.GONE
            }else{
                tv_act_ydlr_nzz1.visibility = View.VISIBLE
            }

            iv_act_ydlr_fwzp.visibility = isShow
            iv_act_ydlr_xcgg.visibility = isShow
            iv_act_ydlr_wzt.visibility = isShow
            rlv_act_ydlr_fwzp.adapter = fwzpAdapter
            rlv_act_ydlr_xcgg.adapter = xcggAdapter
            rlv_act_ydlr_wzt.adapter = wztAdapter
        }
        if (name.equals("审批")){
            iv_act_ydlr_qzyjb.visibility = isShow
            iv_act_ydlr_ghxkspb.visibility = isShow
            rlv_act_ydlr_qzyjb.adapter = qzyjbAdapter
            rlv_act_ydlr_ghxkspb.adapter = ghxkspbAdapter
        }
        if (name.equals("验收")){
            iv_act_ydlr_ysxczp.visibility = isShow
            iv_act_ydlr_yszl.visibility = isShow
            rlv_act_ydlr_ysxczp.adapter = ysxczpAdapter
            rlv_act_ydlr_yszl.adapter = yszlAdapter
        }


    }

    private fun saveDetail(fjBean: ApplyLandEntity){

        /*if (et_act_ydlr_name.text.toString().equals("")){
            ToastUtils.showShort("请输入申请人名称")
            return
        }
        if (et_act_ydlr_ss.text.toString().equals("")){
            ToastUtils.showShort("请输入申请人年龄")
            return
        }
        if (et_act_ydlr_iphone.text.toString().equals("")){
            ToastUtils.showShort("请输入申请人手机号")
            return
        }
        if (et_act_ydlr_idcard.text.toString().equals("")){
            ToastUtils.showShort("请输入申请人身份证号")
            return
        }
        if (et_act_ydlr_hkszd.text.toString().equals("")){
            ToastUtils.showShort("请输入申请人户口所在地")
            return
        }*/
        val zhaiEntitie = fjBean.zhaiEntity//zhaiEntitie
        zhaiEntitie.fjhz=1
        zhaiEntitie.housecount  = et_act_ydlr_name.text.toString()//姓名
        zhaiEntitie.sex  = SexEnum.getIndex(sp_act_ydlr_sex.selectedItem.toString())//性别
        if (!et_act_ydlr_ss.text.toString().equals(""))
            zhaiEntitie.birthday  = et_act_ydlr_ss.text.toString()//出生日期
//            zhaiEntitie.age  = et_act_ydlr_ss.text.toString().toInt()//年龄
        zhaiEntitie.nation  = NationEnum.getIndex(sp_act_ydlr_mz.selectedItem.toString())//民族
        zhaiEntitie.phone  = et_act_ydlr_iphone.text.toString()//手机号
        zhaiEntitie.idCard  = et_act_ydlr_idcard.text.toString()//身份证号
        zhaiEntitie.hjdz  = et_act_ydlr_hkszd.text.toString()//户口所在地
        val radioGroup = getRadioGroup(rg_act_ydlr_hkxz)
        zhaiEntitie.huType  = HuTypeEnum.getIndex(radioGroup)//户口性质
        fjBean.zhaiEntity = zhaiEntitie//zhaiEntitie
        fjBean.zhaiEntities.clear()
        fjBean.zhaiEntities.addAll(zhaiEntities)//.addAll(zhaiEntities) //户籍人员信息  zhaiEntities
        fjBean.applyFwEntities.clear()
        for (i in applyFwEntitys){
            fjBean.applyFwEntities.add(i)
        }
//        fjBean.applyFwEntities.addAll(applyFwEntitys)//.addAll(applyFwEntitys)//房屋信息集合  applyFwEntities
        if (!et_act_ydlr_jing.text.toString().equals(""))
            fjBean.headTitanic = et_act_ydlr_jing.text.toString()//京
        if (!et_act_ydlr_nzz.text.toString().equals(""))
            fjBean.titanic = et_act_ydlr_nzz.text.toString()//农宅字
        if (!et_act_ydlr_zjdmj.text.toString().equals("")&&!et_act_ydlr_zjdmj.text.toString().startsWith("."))
            fjBean.ylArea = et_act_ydlr_zjdmj.text.toString().toDouble()//面积  fjBean.ylEntity.area   fjBean.ylArea
        if (!et_act_ydlr_fwmj.text.toString().equals("")&&!et_act_ydlr_fwmj.text.toString().startsWith("."))
            fjBean.ylJzmj = et_act_ydlr_fwmj.text.toString().toDouble()//面积  fjBean.ylEntity.jianzhuArea    fjBean.ylJzmj
        fjBean.qszsh = et_act_ydlr_qszsh.text.toString()//面积  fjBean.ylEntity
        fjBean.czqkint = CzqkintEnum.getIndex(getRadioGroup(rg_act_ydlr_xzjdczqk))//面积  fjBean.ylEntity
        if (getRadioGroup(rg_act_ydlr_xzjdczqk).equals("保留")){
            fjBean.czqk=et_act_ydlr_xzjdczqk_bl.text.toString()//fjBean.ylEntity
        }else if (getRadioGroup(rg_act_ydlr_xzjdczqk).equals("其他")){
            fjBean.czqk=et_act_ydlr_xzjdczqk_qt.text.toString()//fjBean.ylEntity
        }
        if (!et_act_ydlr_nsqzjdjjfqk_zjdmj.text.toString().equals("")&&!et_act_ydlr_nsqzjdjjfqk_zjdmj.text.toString().startsWith("."))
            fjBean.zjdArea = et_act_ydlr_nsqzjdjjfqk_zjdmj.text.toString().toDouble()//宅基地面积
        if (!et_act_ydlr_nsqzjdjjfqk_fjzdmj.text.toString().equals("")&&!et_act_ydlr_nsqzjdjjfqk_fjzdmj.text.toString().startsWith("."))
            fjBean.fjzdArea = et_act_ydlr_nsqzjdjjfqk_fjzdmj.text.toString().toDouble()//房基占地面积
        if (!et_act_ydlr_nsqzjdjjfqk_zjdgd.text.toString().equals("")&&!et_act_ydlr_nsqzjdjjfqk_zjdgd.text.toString().startsWith("."))
            fjBean.jdgd = et_act_ydlr_nsqzjdjjfqk_zjdgd.text.toString().toDouble()//基底高度
        fjBean.address = et_act_ydlr_nsqzjdjjfqk_hkszd.text.toString()//地址
        fjBean.east = et_act_ydlr_nsqzjdjjfqk_dz.text.toString()//东
        fjBean.south = et_act_ydlr_nsqzjdjjfqk_nz.text.toString()//南
        fjBean.west = et_act_ydlr_nsqzjdjjfqk_xz.text.toString()//西
        fjBean.north = et_act_ydlr_nsqzjdjjfqk_bz.text.toString()//北
        fjBean.landtype = LandTypeEnum.getIndex(getRadioGroup(rg_act_ydlr_nsqzjdjjfqk_dl))//地类
        fjBean.jflx = HouseTypeEnum.getIndex(getRadioGroup(rg_act_ydlr_nsqzjdjjfqk_jflx))//建房类型
        fjBean.xybztjbh = et_act_ydlr_nsqzjdjjfqk_tybztjbh.text.toString()//选用的通用标准图集编号
        fjBean.ftysjbz = et_act_ydlr_nsqzjdjjfqk_sjtcydsjbz.text.toString()//非通用设计图采用的设计标准
        fjBean.applyLandFiles.clear()
        var applyLandFileList = ArrayList<ApplyLandFile>()
        //fhzmList
        applyLandFileList.addAll(fhzmList)
        applyLandFileList.addAll(qszmList)
        applyLandFileList.addAll(hkbList)
        applyLandFileList.addAll(fjcnsList)
        applyLandFileList.addAll(sqbList)
        applyLandFileList.addAll(sfzList)

        applyLandFileList.addAll(fwzpList)
        applyLandFileList.addAll(xcggList)
        applyLandFileList.addAll(qzyjbList)
        applyLandFileList.addAll(wztList)

        applyLandFileList.addAll(ghxkspbList)
        applyLandFileList.addAll(ysxczpList)
        applyLandFileList.addAll(yszlList)

        applyLandFileList.addAll(bhspbList)
        /*applyLandFileList.addAll(fhqsFileList)
        applyLandFileList.addAll(spbList)
        applyLandFileList.addAll(shhList)
        applyLandFileList.addAll(spList)
        applyLandFileList.addAll(ysList)
        applyLandFileList.addAll(qqList)*/

        fjBean.applyLandFiles.addAll(applyLandFileList)//.addAll(applyFileList)

    }
    private fun initJcyxx(zhaiEntity: ZhaiEntity,isAdd:Int) {//家庭成员信息  0添加  1修改

        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_ydlr_act_jtcyxx, ll_act_ydlr)
        val contentView2: View = envirorUpPopu!!.getContentView()
        val tv_pop_ydlr_act_name = contentView2.findViewById<EditText>(R.id.tv_pop_ydlr_act_name)
        val tv_pop_ydlr_act_age = contentView2.findViewById<EditText>(R.id.tv_pop_ydlr_act_age)
        val sp_pop_ydlr_act_yhzgx = contentView2.findViewById<Spinner>(R.id.sp_pop_ydlr_act_yhzgx)
        val tv_pop_ydlr_act_yxsfzjh = contentView2.findViewById<EditText>(R.id.tv_pop_ydlr_act_yxsfzjh)
        val tv_pop_ydlr_act_hkszd = contentView2.findViewById<EditText>(R.id.tv_pop_ydlr_act_hkszd)
        val sp_pop_ydlr_act_hkxz = contentView2.findViewById<Spinner>(R.id.sp_pop_ydlr_act_hkxz)
        val bt_pop_ydlr_act_jtcyxx_sure = contentView2.findViewById<Button>(R.id.bt_pop_ydlr_act_jtcyxx_sure)
        val bt_pop_ydlr_act_jtcyxx_clear = contentView2.findViewById<Button>(R.id.bt_pop_ydlr_act_jtcyxx_clear)

        //与户主关系
        var yhzgxList = ArrayList<String>()
        val values = SociarateEnum.values()
        for (i in values){
            yhzgxList.add(i.getName())
        }
        val yhzgxAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,yhzgxList)
        yhzgxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_pop_ydlr_act_yhzgx.adapter = yhzgxAdapter
        //户口性质
        var hkxzList = ArrayList<String>()
        val hkxzValues = HuTypeEnum.values()
        for (i in hkxzValues){
            hkxzList.add(i.getName())
        }
        val hkxzAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,hkxzList)
        hkxzAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_pop_ydlr_act_hkxz.adapter = hkxzAdapter

        tv_pop_ydlr_act_name.setText(zhaiEntity.housecount)
        tv_pop_ydlr_act_age.setText(zhaiEntity.age.toString())
        tv_pop_ydlr_act_yxsfzjh.setText(zhaiEntity.idCard)
        tv_pop_ydlr_act_hkszd.setText(zhaiEntity.hjdz)
        sp_pop_ydlr_act_yhzgx.setSelection(yhzgxList.indexOf(zhaiEntity.socialrelatText),true)
        sp_pop_ydlr_act_hkxz.setSelection(hkxzList.indexOf(zhaiEntity.huTypeText),true)


        bt_pop_ydlr_act_jtcyxx_clear.setOnClickListener {
            envirorUpPopu?.dismiss()
        }
        bt_pop_ydlr_act_jtcyxx_sure.setOnClickListener {
            val name = tv_pop_ydlr_act_name.text.toString().trim()
            val age = tv_pop_ydlr_act_age.text.toString().trim()
            val yxsfzjh = tv_pop_ydlr_act_yxsfzjh.text.toString().trim()
            val hkszd = tv_pop_ydlr_act_hkszd.text.toString().trim()
            val yhzgx = sp_pop_ydlr_act_yhzgx.selectedItem.toString().trim()
            val hkxz = sp_pop_ydlr_act_hkxz.selectedItem.toString().trim()
            zhaiEntity.housecount = name
            if (!age.equals(""))
                zhaiEntity.age = age.toInt()
            zhaiEntity.idCard = yxsfzjh
            zhaiEntity.hjdz = hkszd
            zhaiEntity.socialrelat = SociarateEnum.getIndex(yhzgx)
            zhaiEntity.huType = HuTypeEnum.getIndex(hkxz)
            if (isAdd==0)//判断如果是否添加
            zhaiEntities.add(zhaiEntity)
            jtcyxxAdapter?.notifyDataSetChanged()
            envirorUpPopu?.dismiss()
        }
        if (AppCache.getInstance().duties == 1){
            bt_pop_ydlr_act_jtcyxx_sure.visibility = View.GONE
        }
//        fwxxAdapter
    }
    private fun initPopuEnvironUp(applyFwEntity: ApplyFwEntity,isAdd:Int) {//房屋信息  0添加  1修改

        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_ydlr_act_fwxx, ll_act_ydlr)
        val contentView2: View = envirorUpPopu!!.getContentView()
        val tv_pop_ydlr_act_fwmj = contentView2.findViewById<TextView>(R.id.tv_pop_ydlr_act_fwmj)
        val tv_pop_ydlr_act_fwcs = contentView2.findViewById<TextView>(R.id.tv_pop_ydlr_act_fwcs)
        val tv_pop_ydlr_act_fwgd = contentView2.findViewById<TextView>(R.id.tv_pop_ydlr_act_fwgd)
        val bt_pop_ydlr_act_sure = contentView2.findViewById<Button>(R.id.bt_pop_ydlr_act_sure)
        val bt_pop_ydlr_act_clear = contentView2.findViewById<Button>(R.id.bt_pop_ydlr_act_clear)
        tv_pop_ydlr_act_fwmj.setText(applyFwEntity.area.toString())
        tv_pop_ydlr_act_fwcs.setText(applyFwEntity.fwcs.toString())
        tv_pop_ydlr_act_fwgd.setText(applyFwEntity.fwgd.toString())

        bt_pop_ydlr_act_clear.setOnClickListener {
            envirorUpPopu?.dismiss()
        }
        if (AppCache.getInstance().duties == 1){
            bt_pop_ydlr_act_sure.visibility = View.GONE
        }
        bt_pop_ydlr_act_sure.setOnClickListener {
            val fwmj = tv_pop_ydlr_act_fwmj.text.toString().trim()
            val fwcs = tv_pop_ydlr_act_fwcs.text.toString().trim()
            val fwgd = tv_pop_ydlr_act_fwgd.text.toString().trim()
            if (!fwmj.equals("")&&!fwmj.startsWith(".")){
                applyFwEntity.area = fwmj.toFloat()
            }
            if (!fwcs.equals("")&&!fwcs.startsWith(".")){
                applyFwEntity.fwcs = fwcs.toInt()
            }
            if (!fwgd.equals("")&&!fwgd.startsWith(".")){
                applyFwEntity.fwgd = fwgd.toFloat()
            }
            if (isAdd==0)
            applyFwEntitys.add(applyFwEntity)
            fwxxAdapter?.notifyDataSetChanged()
            envirorUpPopu?.dismiss()
        }
//        fwxxAdapter
    }
private var alertDialog3:AlertDialog? = null
    override fun returnApplyLandGetHuji(message: List<ZhaiEntity>) {
        if (message!=null){
            if (message.size>0){
                val items = ArrayList<String>()//arrayOf("多选1", "多选2", "多选3", "多选4")
                for (i in message){
                    items.add(i.housecount)
                }
                val alertBuilder = AlertDialog.Builder(this)
                alertBuilder.setTitle("请选择家庭成员信息")
                /**
                 *第一个参数:弹出框的消息集合，一般为字符串集合
                 * 第二个参数：默认被选中的，布尔类数组
                 * 第三个参数：勾选事件监听
                 */
                /**
                 * 第一个参数:弹出框的消息集合，一般为字符串集合
                 * 第二个参数：默认被选中的，布尔类数组
                 * 第三个参数：勾选事件监听
                 */
                alertBuilder.setMultiChoiceItems(items.toTypedArray(), null) { dialogInterface, i, isChecked ->
                    if (isChecked) {
                        message.get(i).isCheck = isChecked
//                Toast.makeText(this, "选择" + items[i], Toast.LENGTH_SHORT).show()
                    } else {
                        message.get(i).isCheck = isChecked
//                Toast.makeText(this, "取消选择" + items[i], Toast.LENGTH_SHORT).show()
                    }
                }
                alertBuilder.setPositiveButton("确定") { dialogInterface, i ->
                    for (i in message){
                        if (i.isCheck){
                            zhaiEntities.add(i)
                        }
                    }
//                    jtcyxxAdapter?.setNewData(zhaiEntities)
                    jtcyxxAdapter?.notifyDataSetChanged()
                    alertDialog3!!.dismiss() }

                alertBuilder.setNegativeButton("取消") { dialogInterface, i ->
                    for (i in message){
                        i.isCheck = false
                    }
                    alertDialog3!!.dismiss() }


                alertDialog3 = alertBuilder.create()
                alertDialog3?.show()
            }
        }

    }

    override fun returnApplyByPoint(message: ApplyEntity) {

    }

    override fun returnApplySave(message: String) {
        AppCache.getInstance().isZjdUpdate = 1
        ToastUtils.showShort("添加成功")
        finish()
    }

    override fun returnApplyUpdate(message: String) {
        AppCache.getInstance().isZjdUpdate = 1
        ToastUtils.showShort("修改成功")
        finish()
    }
    override fun returnApplyDelete(message: String) {
        AppCache.getInstance().isZjdUpdate = 1
        ToastUtils.showShort("删除成功")
        finish()
    }

    override fun returnApplyDeleteHuji(message: String, position: Int) {//户籍人口删除
        if (zhaiEntities!=null&&zhaiEntities.size>0&&jtcyxxAdapter!=null) {
            zhaiEntities.removeAt(position)
            jtcyxxAdapter?.notifyDataSetChanged()
        }
    }

    override fun returnApplyDeleteFjfw(message: String, position: Int) {//房屋删除
        if (applyFwEntitys!=null&&applyFwEntitys.size>0&&fwxxAdapter!=null){
            applyFwEntitys.removeAt(position)
            fwxxAdapter?.notifyDataSetChanged()
        }
    }

    override fun returnApplyFileDelete(message: String, applyEntityList: ArrayList<ApplyLandFile>, view: Int) {//applyFileList
        ToastUtils.showShort("删除成功")
        if (applyEntityList!=null&& applyEntityList!!.size>0){
            applyEntityList.removeAt(view)
  /*          applyEntityList!!.get(view).ylId = null
            applyEntityList!!.get(view).id = null
            applyEntityList!!.get(view).url = null
            applyEntityList!!.get(view).path = null
            applyEntityList!!.get(view).remark = null
            applyEntityList!!.get(view).fileName = null*/
            if (fhzmAdapter!=null){
                fhzmAdapter!!.notifyDataSetChanged()
                qszmAdapter!!.notifyDataSetChanged()
                hkbAdapter!!.notifyDataSetChanged()
                fjcnsAdapter!!.notifyDataSetChanged()
                sqbAdapter!!.notifyDataSetChanged()
                sfzAdapter!!.notifyDataSetChanged()
                fwzpAdapter!!.notifyDataSetChanged()
                xcggAdapter!!.notifyDataSetChanged()
                wztAdapter!!.notifyDataSetChanged()
                qzyjbAdapter!!.notifyDataSetChanged()
                ghxkspbAdapter!!.notifyDataSetChanged()
                bhspbAdapter!!.notifyDataSetChanged()
                ysxczpAdapter!!.notifyDataSetChanged()
                yszlAdapter!!.notifyDataSetChanged()
            }
        }

    }




    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        if (applyEntity!=null){
            for (i in applyEntity!!){
                i.sptype = yuanSptype
            }
        }

        ToastUtils.showShort(msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {//requestCode
            if (requestCode>400){//-400
                var isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                if (!isSdCardExist) {
                    ToastUtils.showShort("SD卡不可用,请检查")
                    return
                }
                if (data!=null){
                    var uri = data!!.getData()
                    if (uri!=null){
                        val realPathFromURI = FileUtilFjxc1.getPath(this, uri)
                        upFile1(File(realPathFromURI),requestCode-400)
                    }

                }
            }else{
                var photos: ArrayList<String>? = null

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    for (i in photos){
                        val file = File(i)
                        val name = file.name
                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        val file1: File = compressImages(bitmap,file.name)
                        upFile1(file1,requestCode-300);
                    }
//                    selectedPhotos.addAll(photos)
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
       /* while (baos.toByteArray().size / 1024 > 1024) { //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset() //重置baos即清空baos
            options -= 10 //每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) //这里压缩options%，把压缩后的数据存放到baos中
            val length = baos.toByteArray().size.toLong()
        }*/
//        val format = SimpleDateFormat("yyyyMMddHHmmss")
//        val date = Date(System.currentTimeMillis())
//        val filename = format.format(date)
        //        File file = new File(Environment.getExternalStorageDirectory(), fileName + ".png");
        val file1 = File(GetFileUtil.getSDCardPath() + "jymj/tzrjhj/pic/")
        if (!file1.isDirectory) {
            file1.mkdirs()
        }
        val file = File(GetFileUtil.getSDCardPath()+"jymj/tzrjhj/pic/1"+fileName)
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
    private fun upFile1( file: File,type:Int) {//上传文件与图片
        Log.e("upFile1scwj",type.toString())
        val request = OkGo.post<BaseRespose<ApplyFileEntity>>(ApiConstants.APPLY_LAND_UPLOAD_FILE )//APPLY_FILE_UPLOAD_FILE
                .isMultipart(true)
//        request.params("type", 1)//1
        request.params("cltype", type)//file.name
        request.params("file", file)//file.name
        request.execute(object : BaseNet<BaseRespose<ApplyFileEntity>>() {//BaseRespose<PjEnviorFileEntity>
        override fun onStart(request: Request<BaseRespose<ApplyFileEntity>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            LoadingDialog.showDialogForLoading(this@YdlrActivity)
        }
            override fun onSuccess(response: Response<BaseRespose<ApplyFileEntity>>) {
                val body = response.body()
//                applyFileList
                if (body.getCode()==0){
                    val applyLandFile = ApplyLandFile()
                    applyLandFile.cltype = type
                    applyLandFile.fileName = body.data.fileName
                    applyLandFile.ylId = body.data.ylId
                    applyLandFile.url = body.data.path
                    applyLandFile.path = body.data.path
                    applyLandFile.id = body.data.id

                    if (fhzmList!=null&&(type==ApplyFileEnum.getIndex("分户证明"))){
                        fhzmList.add(applyLandFile)
                        fhzmAdapter?.notifyDataSetChanged()
                    }
                    if (qszmList!=null&&(type==ApplyFileEnum.getIndex("权属证明"))){
                        qszmList.add(applyLandFile)
                        qszmAdapter?.notifyDataSetChanged()
                    }
                    if (fjcnsList!=null&&(type==ApplyFileEnum.getIndex("翻建承诺书"))){
                        fjcnsList.add(applyLandFile)
                        fjcnsAdapter?.notifyDataSetChanged()
                    }
                    if (sqbList!=null&&(type==ApplyFileEnum.getIndex("申请表"))){
                        sqbList.add(applyLandFile)
                        sqbAdapter?.notifyDataSetChanged()
                    }
                    if (sfzList!=null&&(type==ApplyFileEnum.getIndex("身份证"))){
                        sfzList.add(applyLandFile)
                        sfzAdapter?.notifyDataSetChanged()
                    }
                    if (hkbList!=null&&(type==ApplyFileEnum.getIndex("户口本"))){
                        hkbList.add(applyLandFile)
                        hkbAdapter?.notifyDataSetChanged()
                    }
                    if (fwzpList!=null&&(type==ApplyFileEnum.getIndex("房屋照片"))){
                        fwzpList.add(applyLandFile)
                        fwzpAdapter?.notifyDataSetChanged()
                    }
                    if (xcggList!=null&&(type==ApplyFileEnum.getIndex("现场公告"))){
                        xcggList.add(applyLandFile)
                        xcggAdapter?.notifyDataSetChanged()
                    }
                    if (wztList!=null&&(type==ApplyFileEnum.getIndex("位置图"))){
                        wztList.add(applyLandFile)
                        wztAdapter?.notifyDataSetChanged()
                    }
                    if (qzyjbList!=null&&(type==ApplyFileEnum.getIndex("签字意见表"))){
                        qzyjbList.add(applyLandFile)
                        qzyjbAdapter?.notifyDataSetChanged()
                    }
                    if (ghxkspbList!=null&&(type==ApplyFileEnum.getIndex("通过申请表"))){
                        ghxkspbList.add(applyLandFile)
                        ghxkspbAdapter?.notifyDataSetChanged()
                    }
                    if (bhspbList!=null&&(type==ApplyFileEnum.getIndex("驳回申请表"))){
                        bhspbList.add(applyLandFile)
                        bhspbAdapter?.notifyDataSetChanged()
                    }
                    if (ysxczpList!=null&&(type==ApplyFileEnum.getIndex("验收现场照片"))){
                        ysxczpList.add(applyLandFile)
                        ysxczpAdapter?.notifyDataSetChanged()
                    }
                    if (yszlList!=null&&(type==ApplyFileEnum.getIndex("验收资料"))){
                        yszlList.add(applyLandFile)
                        yszlAdapter?.notifyDataSetChanged()
                    }

                    /*if (fhqsFileList!=null&&(type==ApplyFileEnum.getIndex("分户证明")
                                    ||type==ApplyFileEnum.getIndex("权属证明")||type==ApplyFileEnum.getIndex("翻建承诺书")
                                    ||type==ApplyFileEnum.getIndex("申请表")
                                    ||type==ApplyFileEnum.getIndex("身份证") ||type==ApplyFileEnum.getIndex("户口本")
                                    )){
                        fhqsFileList.add(applyLandFile)
                        fhsqAdapter?.notifyDataSetChanged()
                    }*/
                    /*if (spbList!=null&&(type==ApplyFileEnum.getIndex("房屋照片") ||type==ApplyFileEnum.getIndex("现场公告")
                                    ||type==ApplyFileEnum.getIndex("签字意见表") ||type==ApplyFileEnum.getIndex("位置图")
                                    ||type==ApplyFileEnum.getIndex("四邻意见"))){
                        spbList.add(applyLandFile)
                        scwjAdapter?.notifyDataSetChanged()
                    }*/
                    /*if (spList!=null&&(type==ApplyFileEnum.getIndex("驳回申请表")||(type==ApplyFileEnum.getIndex("通过申请表")
                                    ||type==ApplyFileEnum.getIndex("验收现场照片")||type==ApplyFileEnum.getIndex("验收资料")))){
                        spList.add(applyLandFile)
                        spAdapter?.notifyDataSetChanged()
                    }*/

                    if (spbList!=null&& spbList!!.size>0){//applyFileList
                        for (i in 0..spbList!!.size-1){
                            val get = spbList!!.get(i)
                            if (i==type){
                                get.fileName = body.data.fileName
                                get.ylId = body.data.ylId
//                                get.update = body.data.update
                                get.url = body.data.path
                                get.path = body.data.path
                                get.id = body.data.id
//                                get.ylId = body.data.ylId
                                if (scwjAdapter!=null)
                                    scwjAdapter!!.notifyItemChanged(i)
                            }
                        }
                    }
                    if (shhList!=null&& shhList!!.size>0){//applyFileList
                        for (i in 0..shhList!!.size-1){
                            val get = shhList!!.get(i)
                            if (i==type){
                                get.fileName = body.data.fileName
                                get.ylId = body.data.ylId
//                                get.update = body.data.update
                                get.url = body.data.path
                                get.path = body.data.path
                                get.id = body.data.id
//                                get.ylId = body.data.ylId
                                if (shhAdapter!=null)
                                    shhAdapter!!.notifyItemChanged(i)
                            }
                        }
                    }
                    if (spList!=null&& spList!!.size>0){//applyFileList
                        for (i in 0..spList!!.size-1){
                            val get = spList!!.get(i)
                            if (i==type){
                                get.fileName = body.data.fileName
                                get.ylId = body.data.ylId
//                                get.update = body.data.update
                                get.url = body.data.path
                                get.path = body.data.path
                                get.id = body.data.id
//                                get.ylId = body.data.ylId
                                if (spAdapter!=null)
                                    spAdapter!!.notifyItemChanged(i)
                            }
                        }
                    }
                    if (ysList!=null&& ysList!!.size>0){//applyFileList
                        for (i in 0..ysList!!.size-1){
                            val get = ysList!!.get(i)
                            if (i==type){
                                get.fileName = body.data.fileName
                                get.ylId = body.data.ylId
//                                get.update = body.data.update
                                get.url = body.data.path
                                get.path = body.data.path
                                get.id = body.data.id
//                                get.ylId = body.data.ylId
                                if (ysAdapter!=null)
                                    ysAdapter!!.notifyItemChanged(i)
                            }
                        }
                    }
                    if (qqList!=null&& qqList!!.size>0){//applyFileList
                        for (i in 0..qqList!!.size-1){
                            val get = qqList!!.get(i)
                            if (i==type){
                                get.fileName = body.data.fileName
                                get.ylId = body.data.ylId
//                                get.update = body.data.update
                                get.url = body.data.path
                                get.path = body.data.path
                                get.id = body.data.id
//                                get.ylId = body.data.ylId
                                if (qqAdapter!=null)
                                    qqAdapter!!.notifyItemChanged(i)
                            }
                        }
                    }

                }else{
                    ToastUtils.showShort("上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<ApplyFileEntity>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
//                ToastUtils.showShort(response.exception.message)
//                ToastUtils.showShort("上传失败")
            }
        })
    }

}