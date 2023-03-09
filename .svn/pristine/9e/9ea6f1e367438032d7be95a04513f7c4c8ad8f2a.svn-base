package com.jymj.zhglxt.zjd.activity

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.zjd.bean.fj.FlxcEnum
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.fj.RenovatedFile
import com.jymj.zhglxt.zjd.bean.fj.RenovatedRejectedDto
import com.jymj.zhglxt.zjd.contract.FjxcActContract
import com.jymj.zhglxt.zjd.presenter.FjxcActPresenter
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_fjxc.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import org.greenrobot.eventbus.EventBus
import java.io.*
import java.lang.Double
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FjxcActivity : BaseActivity<FjxcActPresenter, FjxcActContract.Model>(), FjxcActContract.View, AMapLocationListener {

    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private var filetype = 1
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

    private var aMap: AMap? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    private var  addPolyline: Polyline? = null//高亮显示
    private var mPolylineOptions: PolylineOptions? = null
    private var renovatedFileList = ArrayList<RenovatedFile>()//文件列表
    private var renovatedWjFileList = ArrayList<RenovatedFile>()//文件列表
    private var envirorUpPopu: CommenPop? = null
    private var schxtpFj:BaseQuickAdapter<RenovatedFile, BaseViewHolder>? = null//翻建图片
    private var schxtpFj1:BaseQuickAdapter<RenovatedFile, BaseViewHolder>? = null//翻建图片
    private var wjfileAdapter:BaseQuickAdapter<RenovatedFile, BaseViewHolder>? = null//翻建文件
    private var addFileAdapter:BaseQuickAdapter<RenovatedFile, BaseViewHolder>? = null//翻建文件

    override fun getLayoutId(): Int {
        return R.layout.activity_fjxc
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {

    }

    override fun initDatas() {
        if (AppCache.getInstance().type==1||AppCache.getInstance().type==4){//判断如果是内业和巡查显示删除
            acib_fjxc_delete.visibility = View.VISIBLE
        }else{
            acib_fjxc_delete.visibility = View.GONE
            but_act_fjxc_xf.visibility = View.GONE
        }
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_fjxc_detail?.onCreate(intent.extras)
        initAMap()
        //nsv_act_fjxc  supl_fjxc_detail_act
        var metric = DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        var height = metric.heightPixels;   // 屏幕高度（像素）
        supl_fjxc_detail_act.panelHeight = (height*0.65).toInt()//(height*0.7).toInt()//DisplayUtil.dip2px(50f)
        supl_fjxc_detail_act.setScrollableView(nsv_act_fjxc)

        val fjid = intent.getLongExtra("id", -1)//翻建的id用于请求详情接口
        mPresenter.getFanJianDetail(fjid)
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//AppCache.getInstance().proID.toInt()  ,legends4L,1  AppCache.getInstance().proID.toInt()
        for (l in mLayers) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {//底图
                    l.isCheck = true
                }
                "房屋巡查" -> {
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
                "房屋巡查" -> {
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
        location()

    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_fjxc_detail?.map
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
            } else {
                if (l.isCheck)
                    aMap!!.addTileOverlay(l.opt)
            }
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
        aMap!!.setOnCameraChangeListener(object :AMap.OnCameraChangeListener {
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
    override fun onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        super.onResume()
        map_fjxc_detail.onResume()
    }



    override fun onPause() {
        super.onPause()
        map_fjxc_detail.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_fjxc_detail.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun backToFront(view: View) {//返回上一页
        finish()
    }


    override fun returnFanJianDetail(renovated: Renovated) {
        if (renovated.qutype!=1L){
            acib_fjxc_delete.visibility = View.GONE
        }
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WY){//如果是镇角色隐藏录入人
            ll_act_fjxc_detail_xclr.visibility = View.GONE
        }
        if ((AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF)&&renovated.qutype==3L){//目前只有内业可以修改
            but_act_fjxc_zgupdate.visibility = View.VISIBLE
        }
        if (renovated.qutype==1L||renovated.qutype==2L){
        }else if (renovated.qutype==3L){
            ll_act_fjxc_detail_zg.visibility =View.VISIBLE
            ll_act_fjxc_detail_zg1.visibility =View.VISIBLE
        }
        else if (renovated.qutype==4L){
            ll_act_fjxc_detail_zg.visibility =View.VISIBLE
            ll_act_fjxc_detail_zg1.visibility =View.VISIBLE
            ll_act_fjxc_detail_zgwj.visibility =View.VISIBLE
        } else if (renovated.qutype==5L){
            ll_act_fjxc_detail_zg.visibility =View.VISIBLE
            ll_act_fjxc_detail_zg1.visibility =View.VISIBLE
            ll_act_fjxc_detail_zgwj.visibility =View.VISIBLE
        }
        if (renovated.renovatedRejectedDtos.size>0){
            ll_act_fjxc_detail_bhjh.visibility = View.VISIBLE
            tv_fhxc_bhjl.setText("驳回记录(${renovated.renovatedRejectedDtos.size})次")
        }
        val ylEntity = renovated!!.ylEntity
        if (ylEntity!=null){
            val center1 = getCenter(ylEntity!!.center)
            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center1, 17f, 0f, 0f)))
            if (!ylEntity.geometry.equals("")){
//                kuangGeoment(ylEntity.geometry)
                kuangGeomentLine(ylEntity.geometry)//删除之前的   高亮显示翻建点查的院落
            }
            butFjxcTzGddt.setOnClickListener {
                if (AppInstalledUtils.isAppInstalled(this, "com.autonavi.minimap")){//跳转高德地图
                    var intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    //获取本地存储的百度经纬度
                    //获取本地存储的百度经纬度
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
                    /*lon = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lon", String("4.9E-324")) as String?)
                    lat = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lat", String("4.9E-324")) as String?)
                    if (lon !== 4.9E-324 && lat !== 4.9E-324) {
                        intent.data = Uri.parse("baidumap://map/direction?region=湖南&origin=" + lat.toString() + "," + lon.toString() + "&destination=" + address.toString() + "&mode=driving")
                    } else {
                        intent.data = Uri.parse("baidumap://map/direction?region=湖南&origin=" + MyUrl.getAddressMessage().toString() + "&destination=" + address.toString() + "&mode=driving")
                    }*/
                    /*val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)
                    val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84转火星
                    val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)*/


                    intent.data = Uri.parse("baidumap://map/direction?region=目的地&destination=" + center1.latitude.toString() + "," + center1.longitude.toString() + "&mode=driving" +//+ "&destination=" + ""
                            "&coord_type=wgs84")
                    startActivity(intent)
                }else{
                    ToastUtils.showShort("请安装百度地图或高德地图")

                }

            }

            et_act_fjxc_detail_zhen.setText(ylEntity.townname)
            et_act_fjxc_detail_cun.setText(ylEntity.xzqmc)
            et_act_fjxc_detail_hzmc.setText(ylEntity.hzmc)
            et_act_fjxc_detail_mph.setText(ylEntity.mph)
            et_act_fjxc_detail_remark.setText(renovated.remark)
            et_act_fjxc_detail_lrtime.setText(renovated.jltime)
            et_act_fjxc_detail_lrr.setText(renovated.jlr)
            et_act_fjxc_detail_sjh.setText(renovated.jlr)

            et_act_fjxc_detail_wtms.setText(renovated.zgtypeText)
            et_act_fjxc_detail_zgremark.setText(renovated.zgbz)
            et_act_fjxc_detail_zgtime.setText(renovated.zgtime)
            et_act_fjxc_detail_zglrr.setText(renovated.zgr)
            et_act_fjxc_detail_zgrhm.setText(renovated.zgphone)


        }
        if (AppCache.getInstance().type!=5){
            if (renovated.qutype==1L){
                but_act_fjxc_xf.visibility = View.VISIBLE
            }else if (renovated.qutype==2L){
                but_act_fjxc_zg.visibility = View.VISIBLE
            }else if (renovated.qutype==3L){
                but_act_fjxc_zgcl.visibility = View.VISIBLE
            }else if (renovated.qutype==4L){
                but_act_fjxc_xz.visibility = View.VISIBLE
                but_act_fjxc_bh.visibility = View.VISIBLE
            }
        }

        but_act_fjxc_xf.setOnClickListener {
            initPopuFanJian(renovated)
            CommenPop.backgroundAlpha(0.5f, this)
            envirorUpPopu!!.showAtLocation(ll_act_fjxc_detail, Gravity.CENTER, 0, 0)
        }
        for (i in renovated.renovatedFiles){
            if (i.filetype==1){
                renovatedFileList.add(i)
            }
        }
        rlv_act_fjxc_detail_xctp.layoutManager = GridLayoutManager(this,3)
        schxtpFj = object : BaseQuickAdapter<RenovatedFile, BaseViewHolder>(R.layout.item_teng_tui_photo, renovatedFileList) {
            override fun convert(helper: BaseViewHolder?, item: RenovatedFile?) {
                val view = helper!!.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                val pic: String = item!!.path
                val s1 = pic.replace("\\", "/")
                Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view)
                helper!!.setText(R.id.tv_teng_photo_name, item.name)
                if (AppCache.getInstance().type==5||AppCache.getInstance().duties==1){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }else{
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                }
                if (renovated.qutype!=1L){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@FjxcActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            OkGo.post<String> (ApiConstants.YL_DELETE_FILE).upJson("[${item.id}]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(this@FjxcActivity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                        if (json.code==0){
                                            renovatedFileList.removeAt(position)
                                            notifyDataSetChanged()
                                            if (schxtpFj!=null){
                                                schxtpFj!!.notifyDataSetChanged()
                                            }
//                                            mView.returnDeleteFileEnvironmental(json)
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("确定")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("网络错误")
                                }

                            })
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
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in renovatedFileList){
                        val pic =i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(this@FjxcActivity!!)


                }
            }
        }
        rlv_act_fjxc_detail_xctp.adapter = schxtpFj
        for (i in renovated.renovatedFiles){
            if (i.filetype==3){
                renovatedWjFileList.add(i)
            }
        }
        rlv_act_fjxc_detail_zgwj.layoutManager = GridLayoutManager(this,3)

        addFileAdapter = object : BaseQuickAdapter<RenovatedFile, BaseViewHolder>(R.layout.item_fjxc_zg, renovatedWjFileList) {
            override fun convert(helper: BaseViewHolder?, item: RenovatedFile?) {
//                var file = File(item.)
                val tvAddFile = helper!!.getView<TextView>(R.id.tv_add_file)
                val tv_fjxc_zg_name = helper!!.getView<TextView>(R.id.tv_fjxc_zg_name)
                val ivAddFile = helper!!.getView<ImageView>(R.id.iv_add_file)
                val iv_fjxc_zg_delete = helper!!.getView<ImageView>(R.id.iv_fjxc_zg_delete)
//                GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.url))
                if (item!!.path.endsWith(".png")||item!!.path.endsWith(".jpg")||
                        item!!.path.endsWith(".jpeg") || item!!.path.endsWith(".BMP") || item!!.path.endsWith(".GIF")){
                    Glide.with(this@FjxcActivity).load(item!!.path).into(ivAddFile)//applicationContext
                }else{
                    Glide.with(applicationContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path))).into(ivAddFile)
                }
                tv_fjxc_zg_name.setText(item.name)
                val url = item!!.path
                val split = url.split("/")
                var name = ""
                if (split.size>0){
                    name = split.get(split.size-1)
                }
                helper.itemView.setOnClickListener {
                    if (item!!.path.endsWith(".png")||item!!.path.endsWith(".jpg")||item!!.path.endsWith(".jpeg") || item!!.path.endsWith(".BMP")
                            || item!!.path.endsWith(".GIF")){
                        var pathList = ArrayList<String>()
                        pathList.add(item.path)
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@FjxcActivity)
                    }else{
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.path))
                        startActivity(intent)
                    }

                   /* var storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jymj/tzrjhj/pic");
                    if (!storageDir.exists()) {
                        storageDir.mkdirs();
                    }

                    val file = File(storageDir.path + "/" + name)
                    if (file.exists()) {
                        FileUtilsFjxc.openFile(this@FjxcActivity, File(storageDir.path + "/" + name))
                    } else {
                        LoadingDialog.showDialogForLoading(this@FjxcActivity)
                        OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), name) {
                            override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                super.onStart(request)
                            }

                            override fun onSuccess(response: Response<File>) {
                                LoadingDialog.cancelDialogForLoading()
                                FileUtilsFjxc.openFile(this@FjxcActivity, File(storageDir.path + "/" + name))
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
                    }*/
                }
                iv_fjxc_zg_delete.visibility = View.GONE
                iv_fjxc_zg_delete.setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@FjxcActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            OkGo.post<String> (ApiConstants.YL_DELETE_FILE).upJson("[${item.id}]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(this@FjxcActivity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose <*> = Gson().fromJson(cash, object : TypeToken<BaseRespose <*>?>() {}.type)
                                        if (json.code==0){
                                            renovatedWjFileList.removeAt(position)
                                            notifyDataSetChanged()

//                                            mView.returnDeleteFileEnvironmental(json)
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("确定")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("网络错误")
                                }

                            })
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
                if (AppCache.getInstance().duties == 1){
                    iv_fjxc_zg_delete.visibility = View.GONE
                }

                /*if (selectedFiles.size == helper!!.adapterPosition + 1) {
                    tvAddFile.visibility = View.VISIBLE
                    ivAddFile.visibility = View.GONE
                } else {
                    tvAddFile.visibility = View.GONE
                    ivAddFile.visibility = View.VISIBLE

                }*/
               /* tvAddFile.setOnClickListener {
                    selectFile()
                }
*/
            }


        }
        rlv_act_fjxc_detail_zgwj.adapter = addFileAdapter

        acib_fjxc_delete.setOnClickListener {
            val content = TextView(this)
            if (AppCache.getInstance().userId.toInt() !=renovated.userId.toInt()){
                content.text = "是否删除？(此点位非本账号添加)"
            }else{
                content.text = "是否删除？"
            }

//                FileUtils.openFile()//AppConstant.getFileProvider()
            SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("删除固定点位")
                    .setCustomView(content)
                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                        mPresenter.getFanJianDelete(renovated)
                        sweetAlertDialog.dismiss()}
                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                    .show()

        }
        ll_act_fjxc_detail_xcxx.setOnClickListener {
            if (ll_act_fjxc_detail_xcxx1.isShown){
                ll_act_fjxc_detail_xcxx1.visibility = View.GONE
            }else{
                ll_act_fjxc_detail_xcxx1.visibility = View.VISIBLE
            }
        }
        ll_act_fjxc_detail_zg.setOnClickListener {
            if (ll_act_fjxc_detail_zg1.isShown){
                ll_act_fjxc_detail_zg1.visibility = View.GONE
            }else{
                ll_act_fjxc_detail_zg1.visibility = View.VISIBLE
            }
        }
        tv_fhxc_bhjl.setOnClickListener {
            if (rlv_act_fjxc_detail_bh.isShown){
                rlv_act_fjxc_detail_bh.visibility = View.GONE
            }else{
                rlv_act_fjxc_detail_bh.visibility = View.VISIBLE
            }
        }
        rlv_act_fjxc_detail_bh.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rlv_act_fjxc_detail_bh.adapter = object :BaseQuickAdapter<RenovatedRejectedDto,BaseViewHolder>(R.layout.item_fjxc_bhjl,renovated.renovatedRejectedDtos){
            override fun convert(helper: BaseViewHolder?, item: RenovatedRejectedDto?) {
                val tv_item_fjxc_bhcs = helper!!.getView<TextView>(R.id.tv_item_fjxc_bhjl_bhcs)
                val et_item_bhxx_wtms = helper!!.getView<TextView>(R.id.et_item_fjxc_bhjl_bhxx_wtms)
                val et_item_bhxx_bhsj = helper!!.getView<TextView>(R.id.et_item_fjxc_bhjl_bhxx_bhsj)
                val et_item_bhxx_bhr = helper!!.getView<TextView>(R.id.et_item_fjxc_bhjl_bhxx_bhr)
                val rlv_item_bhxx_fjxc = helper!!.getView<RecyclerView>(R.id.rlv_item_bhxx_fjxc_bhjl)
                val ll_item_fjxc_bhcs = helper!!.getView<LinearLayout>(R.id.ll_item_fjxc_bhjl_bhcs)
                tv_item_fjxc_bhcs.setText("第${helper.layoutPosition+1}次驳回")

                et_item_bhxx_wtms.setText(item!!.bhbz)
                et_item_bhxx_bhsj.setText(item!!.bhtime)
                et_item_bhxx_bhr.setText(item!!.bhr)
                rlv_item_bhxx_fjxc.layoutManager = GridLayoutManager(this@FjxcActivity,3)

                var addFileAdapter1 = object : BaseQuickAdapter<RenovatedFile, BaseViewHolder>(R.layout.item_fjxc_zg, item!!.renovatedFiles) {
                    override fun convert(helper: BaseViewHolder?, item1: RenovatedFile?) {
//                var file = File(item.)
                        val tvAddFile = helper!!.getView<TextView>(R.id.tv_add_file)
                        val tv_fjxc_zg_name = helper!!.getView<TextView>(R.id.tv_fjxc_zg_name)
                        val ivAddFile = helper!!.getView<ImageView>(R.id.iv_add_file)
                        val iv_fjxc_zg_delete = helper!!.getView<ImageView>(R.id.iv_fjxc_zg_delete)
                        iv_fjxc_zg_delete.visibility = View.GONE
                        Glide.with(applicationContext).load(item1!!.path).into(ivAddFile)
                        tv_fjxc_zg_name.setText(item1.name)
                        val url = item1!!.path
                        val split = url.split("/")
                        var name = ""
                        if (split.size>0){
                            name = split.get(split.size-1)
                        }
                        helper.itemView.setOnClickListener {
                            var pathList = ArrayList<String>()
                            for (i in item!!.renovatedFiles){
                                val pic =i.path
                                val s1 = pic.replace("\\", "/")
                                pathList.add(s1)
                            }
                            PhotoPreview.builder()
                                    .setPhotos(pathList)
                                    .setCurrentItem(helper.adapterPosition)
                                    .setShowDeleteButton(false)
                                    .start(this@FjxcActivity!!)


                        }


                    }


                }
                rlv_item_bhxx_fjxc.adapter = addFileAdapter1
                tv_item_fjxc_bhcs.setOnClickListener {
                    if (ll_item_fjxc_bhcs.isShown){
                        ll_item_fjxc_bhcs.visibility = View.GONE
                    }else{
                        ll_item_fjxc_bhcs.visibility = View.VISIBLE
                    }
                }
            }

        }

        but_act_fjxc_zgupdate.setOnClickListener {//修改
            initPopuZgUpdate(renovated)
            CommenPop.backgroundAlpha(0.5f, this)
            envirorUpPopu!!.showAtLocation(ll_act_fjxc_detail, Gravity.CENTER, 0, 0)
        }
        but_act_fjxc_zg.setOnClickListener {//整改
            initPopuZgUpdate(renovated)
            CommenPop.backgroundAlpha(0.5f, this)
            envirorUpPopu!!.showAtLocation(ll_act_fjxc_detail, Gravity.CENTER, 0, 0)
        }
        but_act_fjxc_zgcl.setOnClickListener {//上传文件
            initPopuZgUpFile(renovated)
            CommenPop.backgroundAlpha(0.5f, this)
            envirorUpPopu!!.showAtLocation(ll_act_fjxc_detail, Gravity.CENTER, 0, 0)
        }
        but_act_fjxc_xz.setOnClickListener {
            val content = TextView(this)
            content.text = "是否销账?"

//                FileUtils.openFile()//AppConstant.getFileProvider()
            SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("是否销账")
                    .setCustomView(content)
                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                        renovated.qutype = 5
                        mPresenter.getFanJianUpdate(renovated)
                        sweetAlertDialog.dismiss()}
                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                    .show()
        }
        but_act_fjxc_bh.setOnClickListener {
            initPopuBh(renovated)
            CommenPop.backgroundAlpha(0.5f, this)
            envirorUpPopu!!.showAtLocation(ll_act_fjxc_detail, Gravity.CENTER, 0, 0)
        }
        if (AppCache.getInstance().duties == 1){
            acib_fjxc_delete.visibility = View.GONE
            but_act_fjxc_xf.visibility = View.GONE
            but_act_fjxc_zg.visibility = View.GONE
            but_act_fjxc_zgupdate.visibility = View.GONE
            but_act_fjxc_zgcl.visibility = View.GONE
            but_act_fjxc_bh.visibility = View.GONE
            but_act_fjxc_xz.visibility = View.GONE

        }
//        ToastUtils.showShort(Gson().toJson(renovated))
    }
    private fun initPopuBh(message: Renovated) {
//        renovatedFileList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_fan_jian_bh, ll_act_fjxc_detail)
        val tv_bh_fan_jian: View = envirorUpPopu!!.getContentView()
        val tv_bhyy_fan_jian = tv_bh_fan_jian.findViewById<EditText>(R.id.tv_bhyy_fan_jian)
        val bt_close_fan_jian_xz = tv_bh_fan_jian.findViewById<Button>(R.id.bt_close_fan_jian_xz)
        val bt_upload_fan_jian_xz = tv_bh_fan_jian.findViewById<Button>(R.id.bt_upload_fan_jian_xz)
        bt_close_fan_jian_xz.setOnClickListener {
            envirorUpPopu!!.dismiss()
        }
        bt_upload_fan_jian_xz.setOnClickListener {
            message.qutype = 100
            message.shbz = tv_bhyy_fan_jian.text.toString()
            message.bhyj = tv_bhyy_fan_jian.text.toString()
            mPresenter.getFanJianUpdate(message)
        }

    }
    private fun initPopuZgUpFile(message: Renovated) {
//        renovatedFileList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_fan_jian_zgupfile, ll_act_fjxc_detail)
        val contentView2: View = envirorUpPopu!!.getContentView()
        val bt_close_fan_jian_zg_upfile = contentView2.findViewById<Button>(R.id.bt_close_fan_jian_zg_upfile)//关闭
        val bt_upload_fan_jian_zg_upfeile = contentView2.findViewById<Button>(R.id.bt_upload_fan_jian_zg_upfeile)///处理

        bt_upload_fan_jian_zg_upfeile.setOnClickListener {
//            renovatedFileList.addAll(renovatedWjFileList)
            message.renovatedFiles = renovatedWjFileList
            message.qutype = 4
            message.filetype = 3
            mPresenter.getFanJianUpdate(message)
        }
        bt_close_fan_jian_zg_upfile.setOnClickListener {
            envirorUpPopu!!.dismiss()
        }
    }

    private fun initPopuZgUpdate(message: Renovated) {
//        renovatedFileList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_fan_jian_zg, ll_act_fjxc_detail)
        val contentView2: View = envirorUpPopu!!.getContentView()
        val sp_pop_fjxc_zg = contentView2.findViewById<Spinner>(R.id.sp_pop_fjxc_zg)
        val tv_cun_fan_jian_remark = contentView2.findViewById<EditText>(R.id.tv_cun_fan_jian_remark)
        val bt_close_fan_jian_zg = contentView2.findViewById<Button>(R.id.bt_close_fan_jian_zg)
        val bt_upload_fan_jian_zg = contentView2.findViewById<Button>(R.id.bt_upload_fan_jian_zg)
        val rlv_fan_zgupfile = contentView2.findViewById<RecyclerView>(R.id.rlv_fan_zgupfile)
        val tv_fan_zg_upfile_jia = contentView2.findViewById<TextView>(R.id.tv_fan_zg_upfile_jia)
        val zgList = ArrayList<String>();//处理期限
        zgList.add("有翻建手续（上传文件 PDF 文件命名）")
        zgList.add("修缮维修(上传照片，举证)")
        zgList.add("符合规划及建房条件，责令补办手续(上传文件PDF  文件命名")
        zgList.add("不符合规划及建房条件，限期拆除(拆除后照片)")
        zgList.add("挂账处理(其他)")
        val zgAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,zgList)
        zgAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_pop_fjxc_zg.adapter = zgAdapter
        rlv_fan_zgupfile.layoutManager = GridLayoutManager(this,3)

        wjfileAdapter=object:BaseQuickAdapter<RenovatedFile,BaseViewHolder>(R.layout.item_file_up,renovatedWjFileList){
            override fun convert(helper: BaseViewHolder?, item: RenovatedFile?) {

                val ivAddFile = helper!!.getView<ImageView>(R.id.iv_file_up_zgfj)
                val iv_file_fjzg_delet = helper!!.getView<ImageView>(R.id.iv_file_fjzg_delet)
                val et_file_up_zgfj = helper!!.getView<EditText>(R.id.et_file_up_zgfj)
                Glide.with(this@FjxcActivity).load(item!!.path).into(ivAddFile)//applicationContext
                ivAddFile.setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in renovatedWjFileList){
                        val pic =i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }

                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(this@FjxcActivity!!)

                }
                et_file_up_zgfj.setText(item.name)
                et_file_up_zgfj.addTextChangedListener(object :TextWatcher{
                    override fun afterTextChanged(p0: Editable?) {
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        renovatedWjFileList.get(helper.adapterPosition).name = et_file_up_zgfj.text.toString()
                    }
                })
                iv_file_fjzg_delet.setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@FjxcActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            OkGo.post<String> (ApiConstants.YL_DELETE_FILE).upJson("[${item.id}]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(this@FjxcActivity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose <*> = Gson().fromJson(cash, object : TypeToken<BaseRespose <*>?>() {}.type)
                                        if (json.code==0){
                                            renovatedWjFileList.removeAt(position)
                                            notifyDataSetChanged()
                                            if (addFileAdapter!=null){
                                                addFileAdapter!!.notifyDataSetChanged()
                                            }
//                                            mView.returnDeleteFileEnvironmental(json)
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("确定")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("网络错误")
                                }

                            })
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
            }

        }
        rlv_fan_zgupfile.adapter = wjfileAdapter
        tv_fan_zg_upfile_jia.setOnClickListener {
            selectFile()
        }
        if (message.zgtype!=0L){
            sp_pop_fjxc_zg.setSelection((message.zgtype-1).toInt(),true)
        }
        tv_cun_fan_jian_remark.setText(message.zgbz)
        bt_close_fan_jian_zg.setOnClickListener {
            envirorUpPopu!!.dismiss()
        }
        bt_upload_fan_jian_zg.setOnClickListener {
//            message.qutype = 3
            message.renovatedFiles = renovatedWjFileList
            message.qutype = 4
            message.filetype = 3
            message.zgtype = FlxcEnum.getIndex(sp_pop_fjxc_zg.selectedItem.toString()).toLong()
            message.zgbz = tv_cun_fan_jian_remark.text.toString()
            mPresenter.getFanJianUpdate(message)
        }


    }
    private fun initPopuFanJian(message: Renovated) {
//        renovatedFileList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_fan_jian, ll_act_fjxc_detail)
        val contentView2: View = envirorUpPopu!!.getContentView()
//        val contentView2 = LayoutInflater.from(this.getParent()).inflate(R.layout.product_package, null);
//        tvUpDateEnviron = contentView2.findViewById(R.id.tv_up_date_environmental2)
        val bt_close_fan_jian  = contentView2.findViewById<Button>(R.id.bt_close_fan_jian)//关闭
        val bt_delete_fan_jian = contentView2.findViewById<Button>(R.id.bt_delete_fan_jian)//删除
        val bt_lsbc_fan_jian = contentView2.findViewById<Button>(R.id.bt_lsbc_fan_jian)//临时保存
        val bt_upload_fan_jian = contentView2.findViewById<Button>(R.id.bt_upload_fan_jian)//确定
        val tv_zhen_fan_jian  = contentView2.findViewById<TextView>(R.id.tv_zhen_fan_jian)//镇名
        val tv_cun_fan_jian  = contentView2.findViewById<TextView>(R.id.tv_cun_fan_jian)//村名
        val tv_hzmc_fan_jian = contentView2.findViewById<TextView>(R.id.tv_hzmc_fan_jian)//户主名称
        val tv_mph_fan_jian  = contentView2.findViewById<TextView>(R.id.tv_mph_fan_jian)//门牌号
        val tv_zdmj_fan_jian = contentView2.findViewById<TextView>(R.id.tv_zdmj_fan_jian)//占地面积
        val tv_remark_fan_jian= contentView2.findViewById<EditText>(R.id.tv_remark_fan_jian)//备注
        val rlv_fan_jian_upfile= contentView2.findViewById<RecyclerView>(R.id.rlv_fan_jian_upfile)//图片
        val rlv_fan_jian_upfile1= contentView2.findViewById<RecyclerView>(R.id.rlv_fan_jian_upfile1)//图片

        rlv_fan_jian_upfile.layoutManager = GridLayoutManager(this,3)
        rlv_fan_jian_upfile1.layoutManager = GridLayoutManager(this,3)
//        renovatedFileList.addAll(message.renovatedFiles)
        schxtpFj1 = object : BaseQuickAdapter<RenovatedFile, BaseViewHolder>(R.layout.item_teng_tui_photo, renovatedFileList) {
            override fun convert(helper: BaseViewHolder?, item: RenovatedFile?) {
                val view = helper!!.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                val pic: String = item!!.path
                val s1 = pic.replace("\\", "/")
                Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view)
                helper!!.setText(R.id.tv_teng_photo_name, item.name)
                if (AppCache.getInstance().type==5){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }else{
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                }
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@FjxcActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            OkGo.post<String> (ApiConstants.YL_DELETE_FILE).upJson("[${item.id}]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(this@FjxcActivity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose <*> = Gson().fromJson(cash, object : TypeToken<BaseRespose <*>?>() {}.type)
                                        if (json.code==0){
                                            renovatedFileList.removeAt(position)
                                            notifyDataSetChanged()
                                            if (schxtpFj1!=null){
                                                schxtpFj1!!.notifyDataSetChanged()
                                            }
//                                            mView.returnDeleteFileEnvironmental(json)
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("确定")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("网络错误")
                                }

                            })
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
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in renovatedFileList){
                        val pic =i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(this@FjxcActivity!!)


                }
            }
        }
        rlv_fan_jian_upfile.adapter = schxtpFj
        var pathList = ArrayList<String>()
        var photoAdapter = PhotoAdapter(this, pathList)
        rlv_fan_jian_upfile1.adapter = photoAdapter
        rlv_fan_jian_upfile1.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
//                            .start(activity!!)
                            .start(this@FjxcActivity,224)
                } else {
                   /* PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@FjxcActivity,20)//context!!*/
                }
            }
        }))

        val ylEntity = message.ylEntity
        if (ylEntity!=null){
            tv_zhen_fan_jian.setText(ylEntity.townname)
            tv_cun_fan_jian.setText(ylEntity.xzqmc)
            tv_hzmc_fan_jian.setText(ylEntity.hzmc)
            tv_mph_fan_jian.setText(ylEntity.mph)
            tv_zdmj_fan_jian.setText(ylEntity.area.toString()+"㎡")
            tv_remark_fan_jian.setText(message.remark)
            if (!ylEntity.geometry.equals("")){
//                kuangGeoment(ylEntity.geometry)
                kuangGeomentLine(ylEntity.geometry)//删除之前的   高亮显示翻建点查的院落
            }
        }
        if (message.id!=-1L){
            bt_delete_fan_jian.visibility = View.VISIBLE
        }else{
            bt_delete_fan_jian.visibility = View.GONE
        }
        if (AppCache.getInstance().type==5){
            bt_upload_fan_jian.visibility = View.GONE
            bt_lsbc_fan_jian.visibility = View.GONE
            bt_delete_fan_jian.visibility = View.GONE
            rlv_fan_jian_upfile1.visibility = View.GONE
        }
        bt_delete_fan_jian.visibility = View.GONE
        bt_lsbc_fan_jian.visibility = View.GONE
        bt_upload_fan_jian.setOnClickListener {
            //TimeUtil.getDateAfter(0).toString()
            message.xftime = TimeUtil.getDateAfter(0).toString()
//            message.zhen = message.ylEntity.townname
//            message.xzqmc = message.ylEntity.xzqmc
//            message.code = message.ylEntity.code
//            message.ylId = message.ylEntity.gid.toLong()
            message.remark = tv_remark_fan_jian.text.toString()
            message.qutype = 2
            message.filetype = 1
            message.renovatedFiles = renovatedFileList
            mPresenter.getFanJianUpdate(message)
        }
        bt_lsbc_fan_jian.setOnClickListener {
            message.zhen = message.ylEntity.townname
            message.xzqmc = message.ylEntity.xzqmc
            message.code = message.ylEntity.code
            message.ylId = message.ylEntity.gid.toLong()
            message.remark = tv_remark_fan_jian.text.toString()
            message.qutype = 1
            message.filetype = 1
            message.renovatedFiles = renovatedFileList
            mPresenter.getFanJianUpdate(message)
        }
        bt_delete_fan_jian.setOnClickListener {
            LoadingDialog.cancelDialogForLoading()
            val content = TextView(this)
            if (AppCache.getInstance().userId.toInt() !=message.userId.toInt()){
                content.text = "是否删除？(此点位非本账号添加)"
            }else{
                content.text = "是否删除？"
            }

//                FileUtils.openFile()//AppConstant.getFileProvider()
            SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("删除翻建点位")
                    .setCustomView(content)
                    .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                        mPresenter.getFanJianDelete(message)
                        sweetAlertDialog.dismiss()}
                    .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                    .show()

        }
        bt_close_fan_jian.setOnClickListener {
            envirorUpPopu?.dismiss()
        }


    }
    /**
     * 通过手机选择文件
     */
    fun selectFile() {
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)//ACTION_GET_CONTENT
        intent.setType("*/*")
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(Intent.createChooser(intent, "选择文件上传"), 345);
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "请安装一个文件管理器.", Toast.LENGTH_SHORT).show();
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==224){
            var photos: ArrayList<String>? = null
//                selectedPhotos.clear()

            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
            }
            if (photos != null) {
                for (i in photos){
                    val file = File(i)
                    val name = file.name
                    val bitmap = IOHelper.loadBitmap(file.path,true)
                    val file1: File = compressImages(bitmap, name)
                    upFileFj(file1)
                }
            }
        }else if (requestCode==345){
            /*var photos: ArrayList<String>? = null
            if (data != null) {
                photos = data.getStringArrayListExtra(Environment.MEDIA_MOUNTED)
            }*/
            var isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
            if (!isSdCardExist) {
                ToastUtils.showShort("SD卡不可用,请检查")
                return
            }
            if (data!=null){
                var uri = data!!.getData()
                val realPathFromURI = FileUtilFjxc1.getPath(this, uri)
                upFileFjWj(File(realPathFromURI))
            }
        }
    }
    private fun upFileFj( file2: File) {//翻建上传图片
        val decodeFile = BitmapFactory.decodeFile(file2.path)
//        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(context, decodeFile,  cunMing, 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(decodeFile)
        val request = OkGo.post<BaseRespose<RenovatedFile>>(ApiConstants.YL_UPLOAD_FILE)
                .isMultipart(true)

        request.params("file", file)
        request.execute(object : BaseNet<BaseRespose<RenovatedFile>>() {
            override fun onStart(request: Request<BaseRespose<RenovatedFile>, out Request<Any, Request<*, *>>>?) {//renovatedFileList
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@FjxcActivity)
            }
            override fun onSuccess(response: Response<BaseRespose<RenovatedFile>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (body.getCode()==0){
                    renovatedFileList.add(body.data)
                    if (schxtpFj1!=null){
                        schxtpFj1!!.setNewData(renovatedFileList)
                        schxtpFj1!!.notifyDataSetChanged()
                    }
                    if (schxtpFj!=null){
                        schxtpFj!!.setNewData(renovatedFileList)
                        schxtpFj!!.notifyDataSetChanged()
                    }

                }else{
                    ToastUtils.showShort(file.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<RenovatedFile>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("上传失败")
            }
        })
    }
    private fun upFileFjWj( file2: File) {//翻建上传文件
        /*val decodeFile = BitmapFactory.decodeFile(file2.path)
//        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(context, decodeFile,  cunMing, 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(decodeFile)*/
        val request = OkGo.post<BaseRespose<RenovatedFile>>(ApiConstants.YL_UPLOAD_FILE)
                .isMultipart(true)
//        MultipartBody
/*        request.params("type", 1)//1
        request.params("filetype", 1)//1
        request.params("msid", -1)*/
//        request.upFile(file)  file.name
        request.params("file", file2)
        request.execute(object : BaseNet<BaseRespose<RenovatedFile>>() {
            override fun onStart(request: Request<BaseRespose<RenovatedFile>, out Request<Any, Request<*, *>>>?) {//renovatedFileList
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@FjxcActivity)
            }
            override fun onSuccess(response: Response<BaseRespose<RenovatedFile>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (body.getCode()==0){
                    renovatedWjFileList.add(body.data)
                    if (wjfileAdapter!=null){
                        wjfileAdapter!!.notifyDataSetChanged()
                    }
                   /* if (schxtpFj1!=null){
                        schxtpFj1!!.setNewData(renovatedFileList)
                        schxtpFj1!!.notifyDataSetChanged()
                    }
                    if (schxtpFj!=null){
                        schxtpFj!!.setNewData(renovatedFileList)
                        schxtpFj!!.notifyDataSetChanged()
                    }*/

                }else{
                    ToastUtils.showShort(file2.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<RenovatedFile>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("上传失败")
            }
        })
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

    override fun returnFanJianDelete(renovated: String) {
        AppCache.getInstance().isZjdUpdate = 1
        finish()
    }

    override fun returnFanJianUpdate(renovated: String) {
        AppCache.getInstance().isZjdUpdate = 1
        finish()
    }

    fun getCenter(center: String): LatLng {
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
    private fun kuangGeomentLine(dataGeometry: String) {
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
//                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
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

                    }
                }else{
                    val latList = getLatList(split[i])
                    if (i == 0){
//                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
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

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        if (msg!=null){
            ToastUtils.showShort(msg)
        }else{
            ToastUtils.showShort("请求失败")
        }

        LoadingDialog.cancelDialogForLoading()
    }
}
