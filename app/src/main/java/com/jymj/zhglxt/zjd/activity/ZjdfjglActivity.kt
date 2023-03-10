package com.jymj.zhglxt.zjd.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.jymj.zhglxt.rjhj.bean.yl.*
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.widget.TimeLineView
import com.jymj.zhglxt.zjd.bean.zjdyd.CzqkintEnum
import com.jymj.zhglxt.zjd.bean.zjdyd.HouseTypeEnum
import com.jymj.zhglxt.zjd.bean.zjdyd.LandTypeEnum
import com.jymj.zhglxt.zjd.contract.ZjdfjglActContract
import com.jymj.zhglxt.zjd.presenter.ZjdfjglActPresenter
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
import kotlinx.android.synthetic.main.act_zjdfjgl.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import java.lang.Double
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ZjdfjglActivity: BaseActivity<ZjdfjglActPresenter, ZjdfjglActContract.Model>(), ZjdfjglActContract.View, AMapLocationListener {

    var stringList = ArrayList<String>()//????????????????????????
    var stringList1 = ArrayList<String>()//??????????????????
    var jtcyxxAdapter: BaseQuickAdapter<ZhaiEntity, BaseViewHolder>? = null//??????????????????
    var fwxxAdapter: BaseQuickAdapter<ApplyFwEntity, BaseViewHolder>? = null//????????????
    var envirorUpPopu: CommenPop? = null//?????????????????????
    var applyEntity = ApplyEntity()
    var zhaiEntities =ArrayList<ZhaiEntity>()//????????????????????????
    var applyFwEntitys =ArrayList<ApplyFwEntity>()//??????????????????


//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
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


    private var aMap: AMap? = null
    private var  addPolyline: Polyline? = null//????????????
    private var mPolylineOptions: PolylineOptions? = null
    var spbList = ArrayList<ApplyFileEntity>()//??????
    var shhList = ArrayList<ApplyFileEntity>()//??????
    var spList = ArrayList<ApplyFileEntity>()//??????/??????
    var ysList = ArrayList<ApplyFileEntity>()//??????
    var qqList = ArrayList<ApplyFileEntity>()//??????
    var scwjAdapter: BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>? = null//??????
    var shhAdapter: BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>? = null//??????
    var spAdapter: BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>? = null//??????/??????
    var ysAdapter: BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>? = null//??????
    var qqAdapter: BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>? = null//??????
    var applyFileList: ArrayList<ApplyFileEntity>? = null

    override fun getLayoutId(): Int {
        return R.layout.act_zjdfjgl
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_zjdfjgl_act_dt?.onCreate(intent.extras)
        initAMap()
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//AppCache.getInstance().proID.toInt()  ,legends4L,1  AppCache.getInstance().proID.toInt()
        for (l in mLayers) {
            l.isCheck = false
            when (l.name) {
                "?????????" -> {
                    l.isCheck = true
                }
                "??????" -> {
                    l.isCheck = true
                }
                "??????" -> {
                    l.isCheck = true
                }
                "??????" -> {
                    l.isCheck = true
                }
            }
        }
        for (l in mLayers1) {
            l.isCheck = false
            when (l.name) {
                "?????????" -> {
                    l.isCheck = true
                }
                "??????" -> {
                    l.isCheck = true
                }
                "??????" -> {
                    l.isCheck = true
                }
                "??????" -> {
                    l.isCheck = true
                }
            }
        }
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
        location()

        applyEntity = intent.getSerializableExtra("applyEntity") as ApplyEntity//?????????????????????????????????
        ic_act_zjdfjgl_back.setOnClickListener {//??????
            finish()
        }
        if (applyEntity.sptype!=2&&applyEntity.sptype!=4){//????????????????????????????????????????????????????????????
            tv_zjdfjgl_act_sure.setText("??????")//??????
            tv_zjdfjgl_act_sure.visibility = View.GONE
            tv_zjdfjgl_act_xyb.visibility = View.GONE
            tv_zjdfjgl_act_delete.visibility = View.GONE
        }else{
            tv_zjdfjgl_act_sure.setText("??????")
            tv_zjdfjgl_act_sure.visibility = View.VISIBLE
            tv_zjdfjgl_act_xyb.visibility = View.GONE
            tv_zjdfjgl_act_delete.visibility = View.GONE
        }
        if (applyEntity.titanic.equals("")){//???????????????????????????????????????  ????????????
            trl_act_zjdfjgl_wh.visibility = View.GONE
        }else{
            trl_act_zjdfjgl_wh.visibility = View.VISIBLE
            et_act_zjdfjgl_jing.setText(applyEntity.headTitanic)
            et_act_zjdfjgl_nzz.setText(applyEntity.titanic)

        }


        if (applyEntity.sptype>1){//??????????????????????????????
            ll_zjdfjgl_act_sczl.visibility = View.VISIBLE
        }else{
            ll_zjdfjgl_act_sczl.visibility = View.VISIBLE
        }
        tv_zjdfjgl_act_delete.setOnClickListener {//??????????????????????????????
            val content = TextView(this@ZjdfjglActivity)
            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("??????????????????")
                    .setCustomView(content)
                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                        mPresenter.getApplyDelete(applyEntity.getYLEntity().objectid)
                        sweetAlertDialog.dismiss()}
                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                    .show()
        }
        tv_zjdfjgl_act_sure.setOnClickListener {///????????????  ??????????????????????????????  ????????????
            if (SingleOnClickUtil.isFastClick()){
                /*if (et_act_zjdfjgl_name.text.toString().equals("")){
                    ToastUtils.showShort("????????????????????????")
                    return@setOnClickListener
                }
                if (et_act_zjdfjgl_ss.text.toString().equals("")){
                    ToastUtils.showShort("????????????????????????")
                    return@setOnClickListener
                }
                if (et_act_zjdfjgl_iphone.text.toString().equals("")){
                    ToastUtils.showShort("???????????????????????????")
                    return@setOnClickListener
                }
                if (et_act_zjdfjgl_idcard.text.toString().equals("")){
                    ToastUtils.showShort("??????????????????????????????")
                    return@setOnClickListener
                }
                if (et_act_zjdfjgl_hkszd.text.toString().equals("")){
                    ToastUtils.showShort("?????????????????????????????????")
                    return@setOnClickListener
                }
                val zhaiEntitie = applyEntity.zhaiEntitie
                zhaiEntitie.fjhz=1
                zhaiEntitie?.housecount  = et_act_zjdfjgl_name.text.toString()//??????
                zhaiEntitie?.sex  = SexEnum.getIndex(sp_act_zjdfjgl_sex.selectedItem.toString())//??????
                sp_act_zjdfjgl_sex.setEnabled(false);
                if (!et_act_zjdfjgl_ss.text.toString().equals(""))
                    zhaiEntitie?.age  = et_act_zjdfjgl_ss.text.toString().toInt()//??????
                zhaiEntitie?.nation  = NationEnum.getIndex(sp_act_zjdfjgl_mz.selectedItem.toString())//??????
                sp_act_zjdfjgl_mz.setEnabled(false);
                zhaiEntitie?.phone  = et_act_zjdfjgl_iphone.text.toString()//?????????
                zhaiEntitie?.idCard  = et_act_zjdfjgl_idcard.text.toString()//????????????
                zhaiEntitie?.hjdz  = et_act_zjdfjgl_hkszd.text.toString()//???????????????
                val radioGroup = getRadioGroup(rg_act_zjdfjgl_hkxz)
                zhaiEntitie?.huType  = HuTypeEnum.getIndex(radioGroup)//????????????
                applyEntity.zhaiEntitie =zhaiEntitie
                applyEntity.zhaiEntities = zhaiEntities//??????????????????
                applyEntity.applyFwEntities = applyFwEntitys//??????????????????
                if (!et_act_zjdfjgl_zjdmj.text.toString().equals("")&&!et_act_zjdfjgl_zjdmj.text.toString().startsWith("."))
                    applyEntity.getYLEntity().area = et_act_zjdfjgl_zjdmj.text.toString().toDouble()//??????
                if (!et_act_zjdfjgl_fwmj.text.toString().equals("")&&!et_act_zjdfjgl_fwmj.text.toString().startsWith("."))
                    applyEntity.getYLEntity().jianzhuArea = et_act_zjdfjgl_fwmj.text.toString().toDouble()//??????
                applyEntity.getYLEntity()?.qszsh = et_act_zjdfjgl_qszsh.text.toString()//??????
                applyEntity.getYLEntity()?.czqkint = CzqkintEnum.getIndex(getRadioGroup(rg_act_zjdfjgl_xzjdczqk))//??????
                if (getRadioGroup(rg_act_zjdfjgl_xzjdczqk).equals("??????")){
                    applyEntity.getYLEntity()?.czqk=et_act_zjdfjgl_xzjdczqk_bl.text.toString()
                }else if (getRadioGroup(rg_act_zjdfjgl_xzjdczqk).equals("??????")){

                    applyEntity.getYLEntity()?.czqk=et_act_zjdfjgl_xzjdczqk_qt.text.toString()
                }
                if (!et_act_zjdfjgl_nsqzjdjjfqk_zjdmj.text.toString().equals("")&&!et_act_zjdfjgl_nsqzjdjjfqk_zjdmj.text.toString().startsWith("."))
                    applyEntity.zjdArea = et_act_zjdfjgl_nsqzjdjjfqk_zjdmj.text.toString().toFloat()//???????????????
                if (!et_act_zjdfjgl_nsqzjdjjfqk_fjzdmj.text.toString().equals("")&&!et_act_zjdfjgl_nsqzjdjjfqk_fjzdmj.text.toString().startsWith("."))
                    applyEntity.fjzdArea = et_act_zjdfjgl_nsqzjdjjfqk_fjzdmj.text.toString().toFloat()//??????????????????
                if (!et_act_zjdfjgl_nsqzjdjjfqk_zjdgd.text.toString().equals("")&&!et_act_zjdfjgl_nsqzjdjjfqk_zjdgd.text.toString().startsWith("."))
                    applyEntity.jdgd = et_act_zjdfjgl_nsqzjdjjfqk_zjdgd.text.toString().toFloat()//????????????
                applyEntity.address = et_act_zjdfjgl_nsqzjdjjfqk_hkszd.text.toString()//??????
                applyEntity.east = et_act_zjdfjgl_nsqzjdjjfqk_dz.text.toString()//???
                applyEntity.south = et_act_zjdfjgl_nsqzjdjjfqk_nz.text.toString()//???
                applyEntity.west = et_act_zjdfjgl_nsqzjdjjfqk_xz.text.toString()//???
                applyEntity.north = et_act_zjdfjgl_nsqzjdjjfqk_bz.text.toString()//???
                applyEntity.landtype = LandTypeEnum.getIndex(getRadioGroup(rg_act_zjdfjgl_nsqzjdjjfqk_dl))//??????
                applyEntity.jflx = HouseTypeEnum.getIndex(getRadioGroup(rg_act_zjdfjgl_nsqzjdjjfqk_jflx))//????????????
                applyEntity.xybztjbh = et_act_zjdfjgl_nsqzjdjjfqk_tybztjbh.text.toString()//?????????????????????????????????
                applyEntity.ftysjbz = et_act_zjdfjgl_nsqzjdjjfqk_sjtcydsjbz.text.toString()//???????????????????????????????????????*/

                applyEntity.applyFileList = applyFileList
                if (applyEntity.sptype==0){//??????
                    applyEntity.sptype = 2
                    mPresenter.getApplySave(applyEntity!!)
                }else{//??????
                    mPresenter.getApplyUpdate(applyEntity!!)
                }
            }


        }

        if (applyEntity!!.getYLEntity()!=null&&applyEntity!!.getYLEntity().geometry != null && !applyEntity!!.getYLEntity().geometry.equals("")) {//?????????????????????
            if (!applyEntity!!.getYLEntity().geometry.equals("")) {
                kuangGeomentLine(applyEntity!!.getYLEntity().geometry)
//                val center1 = getCenter(serializableExtra!!.location)

                /*try {
                    aMap?.addMarker(MarkerOptions().position(center1))
                    butTzGddt.setOnClickListener {
                        if (AppInstalledUtils.isAppInstalled(this, "com.autonavi.minimap")){//??????????????????
                            var intent = Intent()
                            intent.action = Intent.ACTION_VIEW
                            intent.addCategory(Intent.CATEGORY_DEFAULT)
                            //????????????????????????????????????
                            //????????????????????????????????????
                            */
                /* lon = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lon", String("4.9E-324")) as String)
                             lat = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lat", String("4.9E-324")) as String)
                             var point: MyLatLngPoint? = null
                             if (lon !== 4.9E-324 && lat !== 4.9E-324) {
                                 point = MyLatLngPoint(lat, lon)
                                 point = CoordMath.bd2gcj(point)
                             } else {
                                 lat = 28.208915
                                 lon = 112.985274
                                 point = MyLatLngPoint(lat, lon)
                             }
                             var point1: MyLatLngPoint? = null
                             if (shopLat !== 0 && shopLon !== 0) {
                                 point1 = MyLatLngPoint(shopLat, shopLon)
                                 point1 = CoordMath.bd2gcj(point1)
                             }*/
                /*
                            val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
//                            val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84?????????
                            val transform = PositionUtil.wgs84togcj02(center1.longitude, center1.latitude)//84?????????  center1.latitude
//                            val point = LatLng(model.wgLat, model.wgLon)
                            val uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + aMapLocation!!.latitude  + "&slon=" + aMapLocation!!.longitude + "&sname=" + "????????????" + "&did=BGVIS2&dlat=" + transform[0].toString() + "&dlon=" + transform[1].toString() + "&dname=" + "?????????" + "&dev=0&t=0")
                            intent.data = uri
                            //?????????????????????
                            startActivity(intent)
                        }else if (AppInstalledUtils.isAppInstalled(this, "com.baidu.BaiduMap")){//??????????????????
                            var intent = Intent()
                            *//*lon = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lon", String("4.9E-324")) as String?)
                            lat = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lat", String("4.9E-324")) as String?)
                            if (lon !== 4.9E-324 && lat !== 4.9E-324) {
                                intent.data = Uri.parse("baidumap://map/direction?region=??????&origin=" + lat.toString() + "," + lon.toString() + "&destination=" + address.toString() + "&mode=driving")
                            } else {
                                intent.data = Uri.parse("baidumap://map/direction?region=??????&origin=" + MyUrl.getAddressMessage().toString() + "&destination=" + address.toString() + "&mode=driving")
                            }*//*
                            *//*val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)
                            val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84?????????
                            val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)*/
                /*


                            intent.data = Uri.parse("baidumap://map/direction?region=?????????&destination=" + center1.latitude.toString() + "," + center1.longitude.toString() + "&mode=driving" +//+ "&destination=" + ""
                                    "&coord_type=wgs84")
                            startActivity(intent)
                        }else{
                            ToastUtils.showShort("????????????????????????????????????")

                        }

                    }
//                    aMap!!.animateCamera(CameraUpdateFactory.changeLatLng(center1))
                    aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center1, 16f, 0f, 0f)))
                }finally {

                    //??????????????????????????????,??????animateCamera??????????????????
                    *//*Handler().postDelayed(Runnable {
                        kotlin.run {
                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center1, 16f, 0f, 0f)))
                        }
                        *//**//* public void run() {
                             //??????????????????
                         }*//**/
                /*
                    }, 600);

                    Handler().postDelayed(Runnable {
                        kotlin.run {
                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center1, 16f, 0f, 0f)))
                        }
                        *//**//* public void run() {
                             //??????????????????
                         }*//**//*
                    }, 900);*//*

                }*/

            }

        }
        bt_zjdfjgl_act_dt.setOnClickListener {//???????????????????????? ????????????
            val center1 = getCenter(applyEntity!!.getYLEntity().center1)
            if (AppInstalledUtils.isAppInstalled(this, "com.autonavi.minimap")){//??????????????????
                var intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                //????????????????????????????????????
                //????????????????????????????????????

                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
//                            val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84?????????
                val transform = PositionUtil.wgs84togcj02(center1.longitude, center1.latitude)//84?????????  center1.latitude
//                            val point = LatLng(model.wgLat, model.wgLon)
                val uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + aMapLocation!!.latitude  + "&slon=" + aMapLocation!!.longitude + "&sname=" + "????????????" + "&did=BGVIS2&dlat=" + transform[0].toString() + "&dlon=" + transform[1].toString() + "&dname=" + "?????????" + "&dev=0&t=0")
                intent.data = uri
                //?????????????????????
                startActivity(intent)
            }else if (AppInstalledUtils.isAppInstalled(this, "com.baidu.BaiduMap")){//??????????????????
                var intent = Intent()
                /*lon = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lon", String("4.9E-324")) as String?)
                lat = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lat", String("4.9E-324")) as String?)
                if (lon !== 4.9E-324 && lat !== 4.9E-324) {
                    intent.data = Uri.parse("baidumap://map/direction?region=??????&origin=" + lat.toString() + "," + lon.toString() + "&destination=" + address.toString() + "&mode=driving")
                } else {
                    intent.data = Uri.parse("baidumap://map/direction?region=??????&origin=" + MyUrl.getAddressMessage().toString() + "&destination=" + address.toString() + "&mode=driving")
                }*/
                /*val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)
                val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84?????????
                val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)*/


                intent.data = Uri.parse("baidumap://map/direction?region=?????????&destination=" + center1.latitude.toString() + "," + center1.longitude.toString() + "&mode=driving" +//+ "&destination=" + ""
                        "&coord_type=wgs84")
                startActivity(intent)
            }else{
                ToastUtils.showShort("????????????????????????????????????")

            }

        }
        if (AppCache.getInstance().duties == 1){
            tv_zjdfjgl_act_sure.visibility = View.GONE
            tv_zjdfjgl_act_xyb.visibility = View.GONE
            tv_zjdfjgl_act_delete.visibility = View.GONE
            tv_zjdfjgl_act_fwxx_add.visibility = View.GONE
            tv_zjdfjgl_act_jtcyxx_add.visibility = View.GONE

        }

    }
    fun getCenter(center: String): LatLng {//???????????????????????????????????????
        if (center != null) {
            val points = center.substring(6, center.length - 1).split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (points != null && points.size > 1) {
                val converter = CoordinateConverter()
                // CoordType.GPS ?????????????????????
                converter.from(CoordinateConverter.CoordType.GPS)
                val sl = LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]))
                // sourceLatLng?????????????????? LatLng??????
                converter.coord(sl)
                val latLng = converter.convert()
                return sl
            } else {
                return LatLng(0.0, 0.0)
            }
        }

        return LatLng(0.0, 0.0)
    }
    private fun kuangGeomentLine(dataGeometry: String) {//?????????????????? ????????????????????????
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
                     // CoordType.GPS ?????????????????????
                     converter.from(CoordinateConverter.CoordType.GPS)
                     var sl = LatLng(split1[1].toDouble(), split1[0].toDouble())
                     // sourceLatLng?????????????????? LatLng??????
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
     * ?????????AMap??????
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_zjdfjgl_act_dt?.map
        }
        setUpMap()
    }
    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {
        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        for (l in layers) {
            if (l.name == "?????????"){
                if (l.isCheck){
                    aMap!!.addTileOverlay(opt_tdtStaNomal)
                    aMap!!.addTileOverlay(opt_tdtnN)
                }
            }else if (l.name == "?????????"){
                if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtSta)
                    aMap!!.addTileOverlay(opt_tdtStaN)
                }
            }else if (l.name == "??????"){
                if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtOsm)
                }
            } else {
                if (l.isCheck)
                    aMap!!.addTileOverlay(l.opt)
            }
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
    }

    /**
     * ????????????amap?????????
     */
    private fun setUpMap() {
        //????????????????????????????????????
        aMap?.uiSettings?.isMyLocationButtonEnabled = false
        aMap?.uiSettings?.isScaleControlsEnabled = false
        aMap?.uiSettings?.isZoomControlsEnabled = false
//        aMap?.setMapLanguage(AMap.CHINESE)
        aMap?.mapType = AMap.MAP_TYPE_NORMAL

        aMap?.isMyLocationEnabled = false// ?????????true?????????????????????????????????false??????????????????????????????????????????????????????false???
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
    var aMapLocation: AMapLocation? = null//????????????
    //?????????????????????
    private fun location() {
        //???????????????
        mLocationClient = AMapLocationClient(getApplicationContext());
        //????????????????????????
        mLocationClient!!.setLocationListener(this);
        //?????????????????????
        mLocationOption = AMapLocationClientOption();
        //?????????????????????Hight_Accuracy??????????????????Battery_Saving?????????????????????Device_Sensors??????????????????
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.
        //????????????????????????????????????????????????????????????
// mLocationOption!!.setNeedAddress(true);
        //???????????????????????????,?????????false
// mLocationOption!!.setOnceLocation(false);
        //????????????????????????WIFI????????????????????????
// mLocationOption!!.setWifiActiveScan(true);
        //??????????????????????????????,?????????false????????????????????????
        mLocationOption!!.setMockEnable(false);
        mLocationOption!!.setLocationCacheEnable(false);
        //??????????????????,????????????,?????????2000ms
        mLocationOption!!.setInterval(1000);
        //??????????????????????????????????????????
        mLocationClient!!.setLocationOption(mLocationOption);
        //????????????
        mLocationClient!!.startLocation();
    }
    var isFirstLoc = true
    override fun onLocationChanged(aMapLocation: AMapLocation?) {
        if (aMapLocation != null) {
            this.aMapLocation = aMapLocation
            if (aMapLocation.getErrorCode() == 0) {
                //?????????????????????????????????????????????
                aMapLocation.getLocationType();//????????????????????????????????????????????????????????????????????????????????????
                aMapLocation.getLatitude();//????????????
                aMapLocation.getLongitude();//????????????
                aMapLocation.getAccuracy();//??????????????????
                var df =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                var date = Date(aMapLocation.getTime());
                df.format(date);//????????????
                aMapLocation.getAddress();//???????????????option?????????isNeedAddress???false??????????????????????????????????????????????????????????????????GPS??????????????????????????????
                aMapLocation.getCountry();//????????????
                aMapLocation.getProvince();//?????????
                aMapLocation.getCity();//????????????
                aMapLocation.getDistrict();//????????????
                aMapLocation.getStreet();//????????????
                aMapLocation.getStreetNum();//?????????????????????
                aMapLocation.getCityCode();//????????????
                aMapLocation.getAdCode();//????????????

                // ???????????????????????????????????????????????????????????????????????????????????????????????????
                /*if (isFirstLoc) {
                    isFirstLoc = false;
                    //??????????????????
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f));
                    //???????????????????????????
                    val model = PositionUtil.gcj_To_Gps84(aMapLocation.latitude, aMapLocation.longitude)
                    val point = LatLng(model.wgLat, model.wgLon)
                    aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(point));//LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())
                    aMap?.addMarker(MarkerOptions().position(point))
                    //?????????????????? ??????????????????????????????????????????
                    mListener!!.onLocationChanged(aMapLocation);
                    //????????????
                    // aMap.addMarker(getMarkerOptions(amapLocation));
                    //??????????????????
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
                //??????????????????ErrCode???????????????errInfo???????????????????????????????????????
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
// Toast.makeText(activity!!.getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
            }
        }
    }

    //??????RadioGroup??????????????????
    private fun getRadioGroup(radioGroup:RadioGroup):String{
        val count: Int = radioGroup.getChildCount()

        for (i in 0 until count) {
            try {
                val rb = radioGroup.getChildAt(i) as RadioButton
                if (rb.isChecked) {
                    return  rb.text.toString()
//                Toast.makeText(this@ServiceRequestActivity, "??????" + rb.tag.toString(), Toast.LENGTH_SHORT).show()
                    break
                }
            }catch (ex:Exception){

            }

        }
        return ""
    }

    override fun initDatas() {


        initApply(applyEntity!!)//????????????
        //????????????

        applyFileList = ArrayList<ApplyFileEntity>()//??????????????????
        /*val applyFileEntity = ApplyFileEntity()
        applyFileEntity.cltype = ApplyFileEnum.getIndex("?????????")
        applyFileList!!.add(applyFileEntity)
        val applyFileEntity2 = ApplyFileEntity()
        applyFileEntity2.cltype = ApplyFileEnum.getIndex("?????????")
        applyFileList!!.add(applyFileEntity2)
        val applyFileEntity3 = ApplyFileEntity()
        applyFileEntity3.cltype = ApplyFileEnum.getIndex("???????????????")
        applyFileList!!.add(applyFileEntity3)
//        val applyFileList2 = ArrayList<ApplyFileEntity>()//?????????
        val applyFileEntity4 = ApplyFileEntity()
        applyFileEntity4.cltype = ApplyFileEnum.getIndex("??????????????????????????????")
        applyFileList!!.add(applyFileEntity4)
        val applyFileEntity5 = ApplyFileEntity()
        applyFileEntity5.cltype = ApplyFileEnum.getIndex("???????????????????????????")
        applyFileList!!.add(applyFileEntity5)
        val applyFileEntity6 = ApplyFileEntity()
        applyFileEntity6.cltype = ApplyFileEnum.getIndex("??????(???)")
        applyFileList!!.add(applyFileEntity6)
        val applyFileEntity7 = ApplyFileEntity()
        applyFileEntity7.cltype = ApplyFileEnum.getIndex("??????(???)")
        applyFileList!!.add(applyFileEntity7)
        val applyFileEntity8 = ApplyFileEntity()
        applyFileEntity8.cltype = ApplyFileEnum.getIndex("??????(???)")
        applyFileList!!.add(applyFileEntity8)
        val applyFileEntity9 = ApplyFileEntity()
        applyFileEntity9.cltype = ApplyFileEnum.getIndex("??????(???)")
        applyFileList!!.add(applyFileEntity9)*/

        /*if (applyFileList!=null&&applyEntity.applyFileList.size>0){
            for (f in applyFileList!!){
                for (i in applyEntity.applyFileList){
                    if (i.cltype==f.cltype){
                        f.appId = i.appId
                        f.applyEntity = i.applyEntity
                        f.fileName = i.fileName
                        f.id = i.id
                        f.remark = i.remark
                        f.update = i.update
                        f.path = i.path
                        f.ylId = i.ylId
                    }
                }
            }

        }*/
        applyFileList!!.addAll(applyEntity.applyFileList)//??????????????????????????????
        val hashMap = HashMap<Int, Int>()//?????? hashmap ?????????????????????????????????????????????
        for (i in ApplyFileEnum.values()){//??????????????????
            hashMap.put(i.index,0)
        }
        for (i in applyFileList!!){
            hashMap.put(i.cltype, hashMap.get(i.cltype)!!.toInt()+1)
        }
        if (applyEntity.sptype==2){//?????????????????????
            for (i in 0..3- (hashMap.get(ApplyFileEnum.getIndex("????????????"))!!.toInt())){
                val applyFileEntity1 = ApplyFileEntity()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("????????????")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("????????????"))!!.toInt()<1){
                val applyFileEntity1 = ApplyFileEntity()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("????????????")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("???????????????"))!!.toInt()<1){
                val applyFileEntity1 = ApplyFileEntity()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("???????????????")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("?????????"))!!.toInt()<1){
                val applyFileEntity1 = ApplyFileEntity()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("?????????")
                applyFileList!!.add(applyFileEntity1)
            }
        }
        if (applyEntity.sptype==4){
            for (i in 0..3- (hashMap.get(ApplyFileEnum.getIndex("??????????????????"))!!.toInt())){
                val applyFileEntity1 = ApplyFileEntity()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("??????????????????")
                applyFileList!!.add(applyFileEntity1)
            }
            if (hashMap.get(ApplyFileEnum.getIndex("????????????"))!!.toInt()<1){
                val applyFileEntity1 = ApplyFileEntity()
                applyFileEntity1.cltype = ApplyFileEnum.getIndex("????????????")
                applyFileList!!.add(applyFileEntity1)
            }
        }

        /*rlv_act_zjdfjgl_sczl.layoutManager = GridLayoutManager(this,3)
        scwjAdapter = object : BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>(R.layout.item_zjdfjgl_zl, applyFileList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFileEntity?) {
                *//*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*//*
                helper?.setText(R.id.tv_item_zjdfjgl_zl_bz, ApplyFileEnum.getName(item!!.cltype))//????????????
                        ?.setText(R.id.et_item_zjdfjgl_zl_remark, item!!.remark)//??????
                val iv_item_zjdfjgl_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_tp)
                val iv_item_zjdfjgl_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_delete)
                val et_item_zjdfjgl_zl_remark = helper.getView<EditText>(R.id.et_item_zjdfjgl_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_zjdfjgl_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_zjdfjgl_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@ZjdfjglActivity)
                                    .setTitle("?????????????????????")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"??????1","??????2","??????3","??????4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("??????", "??????"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    ToastUtils.showShort("" + p1)
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@ZjdfjglActivity!!, helper.layoutPosition + 300)
                                                    } else {
                                                        selectFile(helper.layoutPosition + 400)
                                                    }
                                                    p0!!.dismiss()
                                                }

                                                */
        /* public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }*/
        /*
                                            }
                                    )
                                    .setNegativeButton("??????", null)
                                    .show();

                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_delete.visibility = View.VISIBLE
                        iv_item_zjdfjgl_zl_delete.setOnClickListener {
                            val content = TextView(this@ZjdfjglActivity)
                            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("??????${ApplyFileEnum.getName(item!!.cltype)}??????")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//?????????????????????????????????
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@ZjdfjglActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp!!.setOnClickListener {
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
                                FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@ZjdfjglActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
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

                et_item_zjdfjgl_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_zjdfjgl_zl_remark.text.toString().equals("")){
                            item.remark = et_item_zjdfjgl_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_zjdfjgl_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_zjdfjgl_sczl.adapter = scwjAdapter*/
                refreshList()
    }

    fun refreshList(){
        //??????

        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("???????????????")||i.cltype==ApplyFileEnum.getIndex("?????????")
                    ||i.cltype==ApplyFileEnum.getIndex("?????????")||i.cltype==ApplyFileEnum.getIndex("?????????")){
                spbList.add(i)
            }
        }
        rlv_act_zjdfjgl_sczl.layoutManager = GridLayoutManager(this,3)
        scwjAdapter = object : BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>(R.layout.item_zjdfjgl_zl, spbList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFileEntity?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_zjdfjgl_zl_bz, ApplyFileEnum.getName(item!!.cltype))//????????????
                        ?.setText(R.id.et_item_zjdfjgl_zl_remark, item!!.remark)//??????
                val iv_item_zjdfjgl_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_tp)
                val iv_item_zjdfjgl_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_delete)
                val et_item_zjdfjgl_zl_remark = helper.getView<EditText>(R.id.et_item_zjdfjgl_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {//add_image
                    iv_item_zjdfjgl_zl_tp.setImageResource(R.drawable.icon_up_file)
                    iv_item_zjdfjgl_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@ZjdfjglActivity)
                                    .setTitle("?????????????????????")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"??????1","??????2","??????3","??????4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("??????", "??????"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    ToastUtils.showShort("" + p1)
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@ZjdfjglActivity!!, helper.layoutPosition + 300)
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
                                    .setNegativeButton("??????", null)
                                    .show();

                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_delete.visibility = View.VISIBLE
                        iv_item_zjdfjgl_zl_delete.setOnClickListener {
                            val content = TextView(this@ZjdfjglActivity)
                            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("??????${ApplyFileEnum.getName(item!!.cltype)}??????")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,spbList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//?????????????????????????????????
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@ZjdfjglActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp!!.setOnClickListener {
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
                                FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@ZjdfjglActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
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

                et_item_zjdfjgl_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_zjdfjgl_zl_remark.text.toString().equals("")){
                            item.remark = et_item_zjdfjgl_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                if (AppCache.getInstance().duties == 1){
                    iv_item_zjdfjgl_zl_delete.visibility = View.GONE
                }
//                et_item_zjdfjgl_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_zjdfjgl_sczl.adapter = scwjAdapter
        //??????

        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("????????????")||i.cltype==ApplyFileEnum.getIndex("????????????")
                    ||i.cltype==ApplyFileEnum.getIndex("???????????????")||i.cltype==ApplyFileEnum.getIndex("?????????")){
                shhList.add(i)
            }
        }
        rlv_act_zjdfjgl_sp.layoutManager = GridLayoutManager(this,3)
        shhAdapter = object : BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>(R.layout.item_zjdfjgl_zl, shhList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFileEntity?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_zjdfjgl_zl_bz, ApplyFileEnum.getName(item!!.cltype))//????????????
                        ?.setText(R.id.et_item_zjdfjgl_zl_remark, item!!.remark)//??????
                val iv_item_zjdfjgl_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_tp)
                val iv_item_zjdfjgl_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_delete)
                val et_item_zjdfjgl_zl_remark = helper.getView<EditText>(R.id.et_item_zjdfjgl_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_zjdfjgl_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_zjdfjgl_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@ZjdfjglActivity)
                                    .setTitle("?????????????????????")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"??????1","??????2","??????3","??????4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("??????", "??????"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    ToastUtils.showShort("" + p1)
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@ZjdfjglActivity!!, helper.layoutPosition + 300)
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
                                    .setNegativeButton("??????", null)
                                    .show();

                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_delete.visibility = View.VISIBLE
                        iv_item_zjdfjgl_zl_delete.setOnClickListener {
                            val content = TextView(this@ZjdfjglActivity)
                            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("??????${ApplyFileEnum.getName(item!!.cltype)}??????")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,shhList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//?????????????????????????????????
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@ZjdfjglActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp!!.setOnClickListener {
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
                                FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@ZjdfjglActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
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

                et_item_zjdfjgl_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_zjdfjgl_zl_remark.text.toString().equals("")){
                            item.remark = et_item_zjdfjgl_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_zjdfjgl_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_zjdfjgl_sp.adapter = shhAdapter
        //??????/??????

        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("???????????????")||i.cltype==ApplyFileEnum.getIndex("???????????????")){
                spList.add(i)
            }
        }
        rlv_act_zjdfjgl_tgbh.layoutManager = GridLayoutManager(this,3)
        spAdapter = object : BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>(R.layout.item_zjdfjgl_zl, spList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFileEntity?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_zjdfjgl_zl_bz, ApplyFileEnum.getName(item!!.cltype))//????????????
                        ?.setText(R.id.et_item_zjdfjgl_zl_remark, item!!.remark)//??????
                val iv_item_zjdfjgl_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_tp)
                val iv_item_zjdfjgl_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_delete)
                val et_item_zjdfjgl_zl_remark = helper.getView<EditText>(R.id.et_item_zjdfjgl_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_zjdfjgl_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_zjdfjgl_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@ZjdfjglActivity)
                                    .setTitle("?????????????????????")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"??????1","??????2","??????3","??????4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("??????", "??????"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    ToastUtils.showShort("" + p1)
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@ZjdfjglActivity!!, helper.layoutPosition + 300)
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
                                    .setNegativeButton("??????", null)
                                    .show();

                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_delete.visibility = View.VISIBLE
                        iv_item_zjdfjgl_zl_delete.setOnClickListener {
                            val content = TextView(this@ZjdfjglActivity)
                            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("??????${ApplyFileEnum.getName(item!!.cltype)}??????")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,spList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//?????????????????????????????????
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@ZjdfjglActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp!!.setOnClickListener {
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
                                FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@ZjdfjglActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
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

                et_item_zjdfjgl_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_zjdfjgl_zl_remark.text.toString().equals("")){
                            item.remark = et_item_zjdfjgl_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_zjdfjgl_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_zjdfjgl_tgbh.adapter = spAdapter
        //??????

        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("??????????????????")||i.cltype==ApplyFileEnum.getIndex("????????????")){
                ysList.add(i)
            }
        }
        rlv_act_zjdfjgl_ys.layoutManager = GridLayoutManager(this,3)
        ysAdapter = object : BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>(R.layout.item_zjdfjgl_zl, ysList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFileEntity?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_zjdfjgl_zl_bz, ApplyFileEnum.getName(item!!.cltype))//????????????
                        ?.setText(R.id.et_item_zjdfjgl_zl_remark, item!!.remark)//??????
                val iv_item_zjdfjgl_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_tp)
                val iv_item_zjdfjgl_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_delete)
                val et_item_zjdfjgl_zl_remark = helper.getView<EditText>(R.id.et_item_zjdfjgl_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_zjdfjgl_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_zjdfjgl_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@ZjdfjglActivity)
                                    .setTitle("?????????????????????")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"??????1","??????2","??????3","??????4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("??????", "??????"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    ToastUtils.showShort("" + p1)
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@ZjdfjglActivity!!, helper.layoutPosition + 300)
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
                                    .setNegativeButton("??????", null)
                                    .show();

                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_delete.visibility = View.VISIBLE
                        iv_item_zjdfjgl_zl_delete.setOnClickListener {
                            val content = TextView(this@ZjdfjglActivity)
                            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("??????${ApplyFileEnum.getName(item!!.cltype)}??????")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,ysList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//?????????????????????????????????
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@ZjdfjglActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp!!.setOnClickListener {
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
                                FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@ZjdfjglActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
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

                et_item_zjdfjgl_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_zjdfjgl_zl_remark.text.toString().equals("")){
                            item.remark = et_item_zjdfjgl_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_zjdfjgl_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_zjdfjgl_ys.adapter = ysAdapter
        //??????

        for (i in applyFileList!!){
            if (i.cltype==ApplyFileEnum.getIndex("????????????")){
                qqList.add(i)
            }
        }
        rlv_act_zjdfjgl_qq.layoutManager = GridLayoutManager(this,3)
        qqAdapter = object : BaseQuickAdapter<ApplyFileEntity, BaseViewHolder>(R.layout.item_zjdfjgl_zl, qqList) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFileEntity?) {
                /*if (item!!.cltype==1){
                    helper!!.getView<LinearLayout>(R.id.item_ll_zdfjgl_zl).visibility = View.GONE
                }*/
                helper?.setText(R.id.tv_item_zjdfjgl_zl_bz, ApplyFileEnum.getName(item!!.cltype))//????????????
                        ?.setText(R.id.et_item_zjdfjgl_zl_remark, item!!.remark)//??????
                val iv_item_zjdfjgl_zl_tp = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_tp)
                val iv_item_zjdfjgl_zl_delete = helper!!.getView<ImageView>(R.id.iv_item_zjdfjgl_zl_delete)
                val et_item_zjdfjgl_zl_remark = helper.getView<EditText>(R.id.et_item_zjdfjgl_zl_remark)
                val pic: String = item!!.path//ApiConstants.BASE_URL +
                val s1 = pic.replace("\\", "/")
                if (item!!.path.equals("")) {
                    iv_item_zjdfjgl_zl_tp.setImageResource(R.drawable.add_image)
                    iv_item_zjdfjgl_zl_delete.visibility = View.GONE
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            AlertDialog.Builder(this@ZjdfjglActivity)
                                    .setTitle("?????????????????????")
                                    .setIcon(R.mipmap.ic_launcher)//String[] {"??????1","??????2","??????3","??????4"}  android.R.drawable.ic_dialog_info
                                    .setSingleChoiceItems(arrayOf("??????", "??????"), 0,
                                            object : DialogInterface.OnClickListener {
                                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                                    ToastUtils.showShort("" + p1)
                                                    if (p1 == 0) {
                                                        PhotoPicker.builder()
                                                                .setPhotoCount(1)
                                                                .setShowCamera(true)
                                                                .setPreviewEnabled(false)
//                            .start(activity!!)
                                                                .start(this@ZjdfjglActivity!!, helper.layoutPosition + 300)
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
                                    .setNegativeButton("??????", null)
                                    .show();

                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }


                } else {
                    if (((item.cltype==ApplyFileEnum.getIndex("????????????")||item.cltype==ApplyFileEnum.getIndex("????????????")
                                    ||item.cltype==ApplyFileEnum.getIndex("???????????????")||item.cltype==ApplyFileEnum.getIndex("?????????"))&&applyEntity.sptype==2)
                            ||((item.cltype==ApplyFileEnum.getIndex("??????????????????")||item.cltype==ApplyFileEnum.getIndex("????????????"))&&applyEntity.sptype==4)){
                        et_item_zjdfjgl_zl_remark.isEnabled = true
                        iv_item_zjdfjgl_zl_delete.visibility = View.VISIBLE
                        iv_item_zjdfjgl_zl_delete.setOnClickListener {
                            val content = TextView(this@ZjdfjglActivity)
                            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("??????${ApplyFileEnum.getName(item!!.cltype)}??????")
                                    .setCustomView(content)
                                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                        mPresenter.getApplyFileDelete(item.id,qqList,helper.layoutPosition)

                                        sweetAlertDialog.dismiss()}
                                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                    .show()
                        }
                    }else{
                        et_item_zjdfjgl_zl_remark.isEnabled = false
                    }

                    val imageFile = isImageFile(s1)
                    if (imageFile){//?????????????????????????????????
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp.setOnClickListener {
                            var selectedPhotos = ArrayList<String>()
                            selectedPhotos.add(s1)
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(0)
                                    .setShowDeleteButton(false)
//                            .start(activity!!)
                                    .start(this@ZjdfjglActivity)//context!!
                        }
                    }else{//GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                        Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path)))//GlideApp
                                .into(iv_item_zjdfjgl_zl_tp!!)
                        iv_item_zjdfjgl_zl_tp!!.setOnClickListener {
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
                                FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
                            } else {
                                LoadingDialog.showDialogForLoading(this@ZjdfjglActivity)
                                OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                    override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                    }

                                    override fun onSuccess(response: Response<File>) {
                                        LoadingDialog.cancelDialogForLoading()
                                        FileUtilsFjxc.openFile(this@ZjdfjglActivity, File(storageDir.path + "/" + substring))
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

                et_item_zjdfjgl_zl_remark.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_item_zjdfjgl_zl_remark.text.toString().equals("")){
                            item.remark = et_item_zjdfjgl_zl_remark.text.toString()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
//                et_item_zjdfjgl_zl_remark.addTextChangedListener()
            }
        }
        rlv_act_zjdfjgl_qq.adapter = qqAdapter




    }




    //?????????????????????   ??????
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
     * ????????????????????????
     */
    fun selectFile(requestCode:Int) {
//        var intent = Intent(Intent.ACTION_GET_CONTENT)
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.setType("*/*")
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(Intent.createChooser(intent, "??????????????????"), requestCode);
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "??????????????????????????????.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * ????????????
     *
     * @param et ????????????
     */
    public fun showInput( et:EditText) {
        et.requestFocus();
        var imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }
    var xsymkz = 0//?????????????????????????????????
    private fun initApply(fjBean: ApplyEntity){//???????????????
        zhaiEntities.clear()//????????????????????????
        applyFwEntitys.clear()//??????????????????
        zhaiEntities.addAll(fjBean.zhaiEntities)///??????????????????????????????
        applyFwEntitys.addAll(fjBean.applyFwEntities)//?????????????????????????????????
        xsymkz = ApplyTypeNumEnum.getIndex(ApplyTypeEnum.getName(fjBean.sptype))
        /*var zhaiEntity:ZhaiEntity = ZhaiEntity()
        for (i in zhaiEntities){
            if (i.socialrelatText.equals("??????")){
                zhaiEntity = i
            }
        }*/

        //??????
        var sexList = ArrayList<String>()
        val values = SexEnum.values()
        for (i in values){
            sexList.add(i.getName())
        }
        val sexAdapter = ArrayAdapter(this ,R.layout.simple_spinner_item1,sexList)
        sexAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_act_zjdfjgl_sex.adapter = sexAdapter
        //??????
        var mzList = ArrayList<String>()
        val mzValues = NationEnum.values()
        for (i in mzValues){
            mzList.add(i.getName())
        }
        val mzAdapter = ArrayAdapter(this ,R.layout.simple_spinner_item1,mzList)
        mzAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_act_zjdfjgl_mz.adapter = mzAdapter

        val zhaiEntity = fjBean.zhaiEntitie

        et_act_zjdfjgl_name.setText(zhaiEntity.housecount)
        sp_act_zjdfjgl_sex.setSelection(sexList.indexOf(zhaiEntity.getSexText()),true)
        et_act_zjdfjgl_sex.setText(zhaiEntity.getSexText())


//        et_act_zjdfjgl_sex.setText(zhaiEntity.getSexText())
        et_act_zjdfjgl_ss.setText(zhaiEntity.age.toString())
//        et_act_zjdfjgl_mz.setText(zhaiEntity.nationText)
        sp_act_zjdfjgl_mz.setSelection(mzList.indexOf(zhaiEntity.nationText),true)
        et_act_zjdfjgl_mz.setText(zhaiEntity.nationText)
        et_act_zjdfjgl_iphone.setText(zhaiEntity.phone)
        et_act_zjdfjgl_idcard.setText(zhaiEntity.idCard)
        et_act_zjdfjgl_hkszd.setText(zhaiEntity.hjdz)
        if (zhaiEntity.huType==0){
            rg_act_zjdfjgl_whk.isChecked=true
            rg_act_zjdfjgl_ny.isChecked=false
            rg_act_zjdfjgl_fny.isChecked=false
        }else if(zhaiEntity.huType==1){
            rg_act_zjdfjgl_whk.isChecked=false
            rg_act_zjdfjgl_ny.isChecked=true
            rg_act_zjdfjgl_fny.isChecked=false
        }else if(zhaiEntity.huType==2){
            rg_act_zjdfjgl_whk.isChecked=false
            rg_act_zjdfjgl_ny.isChecked=false
            rg_act_zjdfjgl_fny.isChecked=true
        }


        val ylEntity = fjBean.getYLEntity()
        et_act_zjdfjgl_zjdmj.setText(ylEntity.area.toString())
        et_act_zjdfjgl_fwmj.setText(ylEntity.jianzhuArea.toString())
        et_act_zjdfjgl_qszsh.setText(ylEntity.qszsh.toString())
//        et_act_zjdfjgl_qszsh.setText(ylEntity.qszsh.toString())//********************************????????????????????????*******************************************
        if (CzqkintEnum.getName(ylEntity.czqkint).equals("??????")){
            rb_act_zjdfjgl_bl.isChecked=true
            rb_act_zjdfjgl_tgcjt.isChecked=false
            rb_act_zjdfjgl_qt.isChecked=false
            et_act_zjdfjgl_xzjdczqk_bl.setText(ylEntity.czqk)
        }else if (CzqkintEnum.getName(ylEntity.czqkint).equals("???????????????")){
            rb_act_zjdfjgl_bl.isChecked=false
            rb_act_zjdfjgl_tgcjt.isChecked=true
            rb_act_zjdfjgl_qt.isChecked=false
        }else if (CzqkintEnum.getName(ylEntity.czqkint).equals("??????")){
            rb_act_zjdfjgl_bl.isChecked=false
            rb_act_zjdfjgl_tgcjt.isChecked=false
            rb_act_zjdfjgl_qt.isChecked=true
            et_act_zjdfjgl_xzjdczqk_qt.setText(ylEntity.czqk)
        }
        et_act_zjdfjgl_nsqzjdjjfqk_zjdmj.setText(fjBean.zjdArea.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_fjzdmj.setText(fjBean.fjzdArea.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_zjdgd.setText(fjBean.jdgd.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_hkszd.setText(fjBean.address.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_dz.setText(fjBean.east.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_nz.setText(fjBean.south.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_xz.setText(fjBean.west.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_bz.setText(fjBean.north.toString())
        et_act_zjdfjgl_nsqzjdjjfqk_tybztjbh.setText(fjBean.xybztjbh)
        et_act_zjdfjgl_nsqzjdjjfqk_sjtcydsjbz.setText(fjBean.ftysjbz)
        if (LandTypeEnum.getName(fjBean.landtype).equals("????????????")){
            rb_act_zjdfjgl_nsqzjdjjfqk_jsyd.isChecked =true
            rb_act_zjdfjgl_nsqzjdjjfqk_wlyd.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_nyd.isChecked =false
        }else if (LandTypeEnum.getName(fjBean.landtype).equals("????????????")){
            rb_act_zjdfjgl_nsqzjdjjfqk_jsyd.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_wlyd.isChecked =true
            rb_act_zjdfjgl_nsqzjdjjfqk_nyd.isChecked =false
        }else if (LandTypeEnum.getName(fjBean.landtype).equals("?????????(?????????????????????????????????)")){
            rb_act_zjdfjgl_nsqzjdjjfqk_jsyd.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_wlyd.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_nyd.isChecked =true
        }
        if (HouseTypeEnum.getName(fjBean.jflx).equals("????????????")){
            rb_act_zjdfjgl_nsqzjdjjfqk_yzfj.isChecked =true
            rb_act_zjdfjgl_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_qt.isChecked =false
        }else if (HouseTypeEnum.getName(fjBean.jflx).equals("?????????")){
            rb_act_zjdfjgl_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_gkj.isChecked =true
            rb_act_zjdfjgl_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_qt.isChecked =false
        }else if (HouseTypeEnum.getName(fjBean.jflx).equals("????????????")){
            rb_act_zjdfjgl_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_yzxj.isChecked =true
            rb_act_zjdfjgl_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_qt.isChecked =false
        }else if (HouseTypeEnum.getName(fjBean.jflx).equals("????????????")){
            rb_act_zjdfjgl_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_fhxj.isChecked =true
            rb_act_zjdfjgl_nsqzjdjjfqk_qt.isChecked =false
        }else if (HouseTypeEnum.getName(fjBean.jflx).equals("??????")){
            rb_act_zjdfjgl_nsqzjdjjfqk_yzfj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_gkj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_yzxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_fhxj.isChecked =false
            rb_act_zjdfjgl_nsqzjdjjfqk_qt.isChecked =true
        }



        rlv_act_zjdfjgl_jtcyxx.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        jtcyxxAdapter = object : BaseQuickAdapter<ZhaiEntity, BaseViewHolder>(R.layout.item_zjdfjgl_act_jtcyxx, zhaiEntities) {
            override fun convert(helper: BaseViewHolder?, item: ZhaiEntity?) {
                if (item!=null){
                    helper?.setText(R.id.tv_item_zjdfjgl_act_xm, item?.housecount)
                            ?.setText(R.id.tv_item_zjdfjgl_act_nl, item?.age.toString())
                            ?.setText(R.id.tv_item_zjdfjgl_act_yhzgx, item?.socialrelatText)
                            ?.setText(R.id.tv_item_zjdfjgl_act_yxsfzjh, item?.idCard)
                            ?.setText(R.id.tv_item_zjdfjgl_act_hkszd, item?.hjdz)
                            ?.setText(R.id.tv_item_zjdfjgl_act_hkxz, item?.huTypeText)
                    val ll_item_zjdfjgl_act_jtcyxx = helper?.getView<LinearLayout>(R.id.ll_item_zjdfjgl_act_jtcyxx)
                    if (item!!.fjhz==1){
                        ll_item_zjdfjgl_act_jtcyxx?.visibility = View.GONE
                    }else{
                        ll_item_zjdfjgl_act_jtcyxx?.visibility = View.VISIBLE
                    }

                    /*helper?.itemView?.setOnClickListener {
//                    initPopuEnvironUp("")//??????
                        initJcyxx(item!!,1)
                        CommenPop.backgroundAlpha(0.5f, this@ZjdfjglActivity)
                        envirorUpPopu!!.showAtLocation(ll_act_zjdfjgl, Gravity.CENTER, 0, 0) }
                    helper?.itemView?.setOnLongClickListener(object : OnItemClickListener, View.OnLongClickListener {
                        override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

                        }

                        override fun onLongClick(p0: View?): Boolean {
                            val content = TextView(this@ZjdfjglActivity)
                            content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                            SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("????????????????????????")
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
                            return false
                        }
                    })*/
                }


            }

        }
        rlv_act_zjdfjgl_jtcyxx.adapter = jtcyxxAdapter
        tv_zjdfjgl_act_jtcyxx_add.setOnClickListener {//????????????????????????
            if (SingleOnClickUtil.isFastClick()){
                initJcyxx(ZhaiEntity(),0)
                CommenPop.backgroundAlpha(0.5f, this@ZjdfjglActivity)
                envirorUpPopu!!.showAtLocation(ll_act_zjdfjgl, Gravity.CENTER, 0, 0)
            }

        }
        //List<ApplyFwEntity> applyFwEntities

        rlv_act_zjdfjgl_fwxx.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        fwxxAdapter = object : BaseQuickAdapter<ApplyFwEntity, BaseViewHolder>(R.layout.item_zjdfjgl_act_fwxx, applyFwEntitys) {
            override fun convert(helper: BaseViewHolder?, item: ApplyFwEntity?) {
                helper?.setText(R.id.tv_item_zjdfjgl_act_fwmj, item?.area.toString())
                        ?.setText(R.id.tv_item_zjdfjgl_act_fwcs, item?.fwcs.toString())
                        ?.setText(R.id.tv_item_zjdfjgl_act_fwgd, item?.fwgd.toString())
                /*helper?.itemView?.setOnClickListener {
                    initPopuEnvironUp(item!!,1)//??????
                    CommenPop.backgroundAlpha(0.5f, this@ZjdfjglActivity)
                    envirorUpPopu!!.showAtLocation(ll_act_zjdfjgl, Gravity.CENTER, 0, 0) }
                helper?.itemView?.setOnLongClickListener(object : OnItemClickListener, View.OnLongClickListener {
                    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

                    }

                    override fun onLongClick(p0: View?): Boolean {
                        val content = TextView(this@ZjdfjglActivity)
                        content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@ZjdfjglActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("??????????????????")
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
        rlv_act_zjdfjgl_fwxx.adapter = fwxxAdapter
        tv_zjdfjgl_act_fwxx_add.setOnClickListener {//??????????????????
            if (SingleOnClickUtil.isFastClick()){
                initPopuEnvironUp(ApplyFwEntity(),0)
                CommenPop.backgroundAlpha(0.5f, this@ZjdfjglActivity)
                envirorUpPopu!!.showAtLocation(ll_act_zjdfjgl, Gravity.CENTER, 0, 0)

            }
        }
        var sptypeList = ArrayList<String>()
        sptypeList.add("?????????")
        sptypeList.add("??????")
        sptypeList.add("??????")
        if (fjBean.sptype==4){
            sptypeList.add("??????")
        }else if (fjBean.sptype==5){
            sptypeList.add("??????")
        }else{
            sptypeList.add("??????/??????")
        }
        sptypeList.add("??????")
        sptypeList.add("??????")
        //ApplyTypeEnum

        tlv_zjdfjgl_sjz.setPointStrings(sptypeList, sptypeList.indexOf(ApplyTypeEnum.getName(fjBean.sptype))+1f)//???????????????
        tlv_zjdfjgl_sjz.setOnStepChangedListener(object : TimeLineView.OnStepChangedListener {//
            override fun onchanged(view: TimeLineView?, step: Int, stepStr: String?) {//xsymkz
                if (step>=0&&step<sptypeList.size&&step<=xsymkz){
                    val sptypeString = sptypeList.get(step)
                    if (sptypeString.equals("?????????")||sptypeString.equals("??????")){
                        ll_zjdfjgl_sqb.visibility = View.VISIBLE//?????????
                        ll_zjdfjgl_sp.visibility = View.GONE//??????
                        ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
                        ll_zjdfjgl_ys.visibility = View.GONE//??????
                        ll_zjdfjgl_qq.visibility = View.GONE//??????
                    }else if (sptypeString.equals("??????")){
                        ll_zjdfjgl_sqb.visibility = View.GONE//?????????
                        ll_zjdfjgl_sp.visibility = View.VISIBLE//??????
                        ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
                        ll_zjdfjgl_ys.visibility = View.GONE//??????
                        ll_zjdfjgl_qq.visibility = View.GONE//??????
                    }else if (sptypeString.equals("??????")||sptypeString.equals("??????")||sptypeString.equals("??????/??????")){
                        ll_zjdfjgl_sqb.visibility = View.GONE//?????????
                        ll_zjdfjgl_sp.visibility = View.GONE//??????
                        ll_zjdfjgl_tgbh.visibility = View.VISIBLE//??????/??????
                        ll_zjdfjgl_ys.visibility = View.GONE//??????
                        ll_zjdfjgl_qq.visibility = View.GONE//??????
                    }else if (sptypeString.equals("??????")){
                        ll_zjdfjgl_sqb.visibility = View.GONE//?????????
                        ll_zjdfjgl_sp.visibility = View.GONE//??????
                        ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
                        ll_zjdfjgl_ys.visibility = View.VISIBLE//??????
                        ll_zjdfjgl_qq.visibility = View.GONE//??????
                    }else if (sptypeString.equals("??????")){
                        ll_zjdfjgl_sqb.visibility = View.GONE//?????????
                        ll_zjdfjgl_sp.visibility = View.GONE//??????
                        ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
                        ll_zjdfjgl_ys.visibility = View.GONE//??????
                        ll_zjdfjgl_qq.visibility = View.VISIBLE//??????
                    }

                }




            }

        })

        if (xsymkz==0||xsymkz==1){
            ll_zjdfjgl_sqb.visibility = View.VISIBLE//?????????
            ll_zjdfjgl_sp.visibility = View.GONE//??????
            ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
            ll_zjdfjgl_ys.visibility = View.GONE//??????
            ll_zjdfjgl_qq.visibility = View.GONE//??????
        }else if (xsymkz==2){
            ll_zjdfjgl_sqb.visibility = View.GONE//?????????
            ll_zjdfjgl_sp.visibility = View.VISIBLE//??????
            ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
            ll_zjdfjgl_ys.visibility = View.GONE//??????
            ll_zjdfjgl_qq.visibility = View.GONE//??????
        }else if (xsymkz==3){
            ll_zjdfjgl_sqb.visibility = View.GONE//?????????
            ll_zjdfjgl_sp.visibility = View.GONE//??????
            ll_zjdfjgl_tgbh.visibility = View.VISIBLE//??????/??????
            ll_zjdfjgl_ys.visibility = View.GONE//??????
            ll_zjdfjgl_qq.visibility = View.GONE//??????
        }else if (xsymkz==4){
            ll_zjdfjgl_sqb.visibility = View.GONE//?????????
            ll_zjdfjgl_sp.visibility = View.GONE//??????
            ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
            ll_zjdfjgl_ys.visibility = View.VISIBLE//??????
            ll_zjdfjgl_qq.visibility = View.GONE//??????
        }else if (xsymkz==5){
            ll_zjdfjgl_sqb.visibility = View.GONE//?????????
            ll_zjdfjgl_sp.visibility = View.GONE//??????
            ll_zjdfjgl_tgbh.visibility = View.GONE//??????/??????
            ll_zjdfjgl_ys.visibility = View.GONE//??????
            ll_zjdfjgl_qq.visibility = View.VISIBLE//??????
        }


    }
    private fun initJcyxx(zhaiEntity: ZhaiEntity,isAdd:Int) {//??????????????????  0??????  1??????

        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_zjdfjgl_act_jtcyxx, ll_act_zjdfjgl)
        val contentView2: View = envirorUpPopu!!.getContentView()
        val tv_pop_zjdfjgl_act_name = contentView2.findViewById<EditText>(R.id.tv_pop_zjdfjgl_act_name)
        val tv_pop_zjdfjgl_act_age = contentView2.findViewById<EditText>(R.id.tv_pop_zjdfjgl_act_age)
        val sp_pop_zjdfjgl_act_yhzgx = contentView2.findViewById<Spinner>(R.id.sp_pop_zjdfjgl_act_yhzgx)
        val tv_pop_zjdfjgl_act_yxsfzjh = contentView2.findViewById<EditText>(R.id.tv_pop_zjdfjgl_act_yxsfzjh)
        val tv_pop_zjdfjgl_act_hkszd = contentView2.findViewById<EditText>(R.id.tv_pop_zjdfjgl_act_hkszd)
        val sp_pop_zjdfjgl_act_hkxz = contentView2.findViewById<Spinner>(R.id.sp_pop_zjdfjgl_act_hkxz)
        val bt_pop_zjdfjgl_act_jtcyxx_sure = contentView2.findViewById<Button>(R.id.bt_pop_zjdfjgl_act_jtcyxx_sure)
        val bt_pop_zjdfjgl_act_jtcyxx_clear = contentView2.findViewById<Button>(R.id.bt_pop_zjdfjgl_act_jtcyxx_clear)

        //???????????????
        var yhzgxList = ArrayList<String>()
        val values = SociarateEnum.values()
        for (i in values){
            yhzgxList.add(i.getName())
        }
        val yhzgxAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,yhzgxList)
        yhzgxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_pop_zjdfjgl_act_yhzgx.adapter = yhzgxAdapter
        //????????????
        var hkxzList = ArrayList<String>()
        val hkxzValues = HuTypeEnum.values()
        for (i in hkxzValues){
            hkxzList.add(i.getName())
        }
        val hkxzAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,hkxzList)
        hkxzAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_pop_zjdfjgl_act_hkxz.adapter = hkxzAdapter

        tv_pop_zjdfjgl_act_name.setText(zhaiEntity.housecount)
        tv_pop_zjdfjgl_act_age.setText(zhaiEntity.age.toString())
        tv_pop_zjdfjgl_act_yxsfzjh.setText(zhaiEntity.idCard)
        tv_pop_zjdfjgl_act_hkszd.setText(zhaiEntity.hjdz)
        sp_pop_zjdfjgl_act_yhzgx.setSelection(yhzgxList.indexOf(zhaiEntity.socialrelatText),true)
        sp_pop_zjdfjgl_act_hkxz.setSelection(hkxzList.indexOf(zhaiEntity.huTypeText),true)


        bt_pop_zjdfjgl_act_jtcyxx_clear.setOnClickListener {
            envirorUpPopu?.dismiss()
        }
        if (AppCache.getInstance().duties == 1){
            bt_pop_zjdfjgl_act_jtcyxx_sure.visibility = View.GONE
        }
        bt_pop_zjdfjgl_act_jtcyxx_sure.setOnClickListener {
            val name = tv_pop_zjdfjgl_act_name.text.toString().trim()
            val age = tv_pop_zjdfjgl_act_age.text.toString().trim()
            val yxsfzjh = tv_pop_zjdfjgl_act_yxsfzjh.text.toString().trim()
            val hkszd = tv_pop_zjdfjgl_act_hkszd.text.toString().trim()
            val yhzgx = sp_pop_zjdfjgl_act_yhzgx.selectedItem.toString().trim()
            val hkxz = sp_pop_zjdfjgl_act_hkxz.selectedItem.toString().trim()
            zhaiEntity.housecount = name
            if (!age.equals(""))
                zhaiEntity.age = age.toInt()
            zhaiEntity.idCard = yxsfzjh
            zhaiEntity.hjdz = hkszd
            zhaiEntity.socialrelat = SociarateEnum.getIndex(yhzgx)
            zhaiEntity.huType = HuTypeEnum.getIndex(hkxz)
            if (isAdd==0)//????????????????????????
            zhaiEntities.add(zhaiEntity)
            jtcyxxAdapter?.notifyDataSetChanged()
            envirorUpPopu?.dismiss()
        }
//        fwxxAdapter
    }
    private fun initPopuEnvironUp(applyFwEntity: ApplyFwEntity,isAdd:Int) {//????????????  0??????  1??????

        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_zjdfjgl_act_fwxx, ll_act_zjdfjgl)
        val contentView2: View = envirorUpPopu!!.getContentView()
        val tv_pop_zjdfjgl_act_fwmj = contentView2.findViewById<TextView>(R.id.tv_pop_zjdfjgl_act_fwmj)
        val tv_pop_zjdfjgl_act_fwcs = contentView2.findViewById<TextView>(R.id.tv_pop_zjdfjgl_act_fwcs)
        val tv_pop_zjdfjgl_act_fwgd = contentView2.findViewById<TextView>(R.id.tv_pop_zjdfjgl_act_fwgd)
        val bt_pop_zjdfjgl_act_sure = contentView2.findViewById<Button>(R.id.bt_pop_zjdfjgl_act_sure)
        val bt_pop_zjdfjgl_act_clear = contentView2.findViewById<Button>(R.id.bt_pop_zjdfjgl_act_clear)
        tv_pop_zjdfjgl_act_fwmj.setText(applyFwEntity.area.toString())
        tv_pop_zjdfjgl_act_fwcs.setText(applyFwEntity.fwcs.toString())
        tv_pop_zjdfjgl_act_fwgd.setText(applyFwEntity.fwgd.toString())

        bt_pop_zjdfjgl_act_clear.setOnClickListener {
            envirorUpPopu?.dismiss()
        }
        bt_pop_zjdfjgl_act_sure.setOnClickListener {
            val fwmj = tv_pop_zjdfjgl_act_fwmj.text.toString().trim()
            val fwcs = tv_pop_zjdfjgl_act_fwcs.text.toString().trim()
            val fwgd = tv_pop_zjdfjgl_act_fwgd.text.toString().trim()
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
    override fun returnApplyByPoint(message: ApplyEntity) {

    }

    override fun returnApplySave(message: String) {
        AppCache.getInstance().isZjdUpdate=1
        ToastUtils.showShort("????????????")
        finish()
    }

    override fun returnApplyUpdate(message: String) {
        AppCache.getInstance().isZjdUpdate=1
        ToastUtils.showShort("????????????")
        finish()
    }
    override fun returnApplyDelete(message: String) {
        AppCache.getInstance().isZjdUpdate=1
        ToastUtils.showShort("????????????")
        finish()
    }

    override fun returnApplyDeleteHuji(message: String, position: Int) {//??????????????????
        if (zhaiEntities!=null&&zhaiEntities.size>0&&jtcyxxAdapter!=null) {
            zhaiEntities.removeAt(position)
            jtcyxxAdapter?.notifyDataSetChanged()
        }
    }

    override fun returnApplyDeleteFjfw(message: String, position: Int) {//????????????
        if (applyFwEntitys!=null&&applyFwEntitys.size>0&&fwxxAdapter!=null){
            applyFwEntitys.removeAt(position)
            fwxxAdapter?.notifyDataSetChanged()
        }
    }

    override fun returnApplyFileDelete(message: String, applyEntityList: ArrayList<ApplyFileEntity>, view: Int) {//applyFileList
        ToastUtils.showShort("????????????")
        if (applyEntityList!=null&& applyEntityList!!.size>0){
            applyEntityList!!.get(view).ylId=null
            applyEntityList!!.get(view).id=null
            applyEntityList!!.get(view).url=null
            applyEntityList!!.get(view).path=null
            applyEntityList!!.get(view).remark=null
            applyEntityList!!.get(view).fileName=null
            /*scwjAdapter!!.notifyItemChanged(view)
            shhAdapter!!.notifyItemChanged(view)
            spAdapter!!.notifyItemChanged(view)
            ysAdapter!!.notifyItemChanged(view)
            qqAdapter!!.notifyItemChanged(view)*/
//            scwjAdapter!!.notifyDataSetChanged()
            if (scwjAdapter!=null){
                scwjAdapter!!.notifyDataSetChanged()
                shhAdapter!!.notifyDataSetChanged()
                spAdapter!!.notifyDataSetChanged()
                ysAdapter!!.notifyDataSetChanged()
                qqAdapter!!.notifyDataSetChanged()
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
        ToastUtils.showShort(msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {//requestCode
            if (requestCode>400){//-400
                var isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                if (!isSdCardExist) {
                    ToastUtils.showShort("SD????????????,?????????")
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
     * ??????????????????????????????
     *
     * @param bitmap
     */
    fun compressImages(bitmap: Bitmap, fileName: String): File {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos) //???????????????????????????100????????????????????????????????????????????????baos???
        var options = 100
       /* while (baos.toByteArray().size / 1024 > 1024) { //?????????????????????????????????????????????500kb,??????????????????
            baos.reset() //??????baos?????????baos
            options -= 10 //???????????????10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) //????????????options%?????????????????????????????????baos???
            val length = baos.toByteArray().size.toLong()
        }*/
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

    //??????
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
    private fun upFile1( file: File,type:Int) {//?????????????????????
        val request = OkGo.post<BaseRespose<ApplyFileEntity>>(ApiConstants.APPLY_FILE_UPLOAD_FILE)
                .isMultipart(true)
//        request.params("type", 1)//1
        request.params("cltype", type)//file.name
        request.params("file", file)//file.name
        request.execute(object : BaseNet<BaseRespose<ApplyFileEntity>>() {//BaseRespose<PjEnviorFileEntity>
        override fun onStart(request: Request<BaseRespose<ApplyFileEntity>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            LoadingDialog.showDialogForLoading(this@ZjdfjglActivity)
        }
            override fun onSuccess(response: Response<BaseRespose<ApplyFileEntity>>) {
                val body = response.body()
                applyFileList
                if (body.getCode()==0){
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
                    ToastUtils.showShort("????????????")
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
//                ToastUtils.showShort("????????????")
            }
        })
    }

}