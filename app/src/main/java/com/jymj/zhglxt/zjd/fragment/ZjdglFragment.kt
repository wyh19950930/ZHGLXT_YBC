package com.jymj.zhglxt.zjd.fragment

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.jymj.zhglxt.R
import com.jymj.zhglxt.zjd.bean.fj.FjBean
import com.jymj.zhglxt.zjd.contract.ZjdglContract
import com.jymj.zhglxt.zjd.presenter.ZjdglPresenter
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.commonwidget.LoadingDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.*
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.zxing.integration.android.IntentIntegrator
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.activity.LdrkDetailActivity
import com.jymj.zhglxt.ldrkgl.home.activity.ScanActivity
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.model.ZjdglModel
import com.jymj.zhglxt.rjhj.activity.HBJCDetailActivity
import com.jymj.zhglxt.rjhj.adapter.HbAdapter
import com.jymj.zhglxt.rjhj.adapter.VideoAdapter
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.bean.enums.*
import com.jymj.zhglxt.rjhj.bean.yl.ApplyCountEntity
import com.jymj.zhglxt.rjhj.bean.yl.YztPointEntity
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyDetail
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.ui.fragment.HomePageFragment.Companion.ivFragHomepageRight
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.BubbleView
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.widget.tablayout.DropdownTabLayout
import com.jymj.zhglxt.widget.tablayout.TestViewPagerAdapter
import com.jymj.zhglxt.widget.tablayout.TxTabTitleData
import com.jymj.zhglxt.widget.zdybj.bean.TuliColorBean
import com.jymj.zhglxt.zjd.activity.FjxcActivity
import com.jymj.zhglxt.zjd.activity.QyglDetailActivity
import com.jymj.zhglxt.zjd.activity.YdlrActivity
import com.jymj.zhglxt.zjd.activity.yzt.TjfxActivity
import com.jymj.zhglxt.zjd.activity.zjdgl.BaseDataActivity
import com.jymj.zhglxt.zjd.activity.zjdgl.CbbsjsxActivity
import com.jymj.zhglxt.zjd.activity.zjdgl.ImageListActivity
import com.jymj.zhglxt.zjd.adapter.SectionsPagerAdapter
import com.jymj.zhglxt.zjd.bean.*
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.fj.FwglJhBean
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.tdxg.GhVO
import com.jymj.zhglxt.zjd.bean.tdxg.QsVO
import com.jymj.zhglxt.zjd.bean.tdxg.TdlyVO
import com.jymj.zhglxt.zjd.bean.tdxg.YztSktVO
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.yzt.User1
import com.jymj.zhglxt.zjd.bean.yzt.cs.CSEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.fragment.cy.ZjdCyyqFragment
import com.jymj.zhglxt.zjd.fragment.cy.ZjdDkfFragment
import com.jymj.zhglxt.zjd.fragment.cy.ZjdNyyqFragment
import com.jymj.zhglxt.zjd.fragment.cy.ZjdWlFragment
import com.jymj.zhglxt.zjd.fragment.shzl.*
import com.jymj.zhglxt.zjd.fragment.tx.ZjdTdghFragment
import com.jymj.zhglxt.zjd.fragment.tx.ZjdTdlyFragment
import com.jymj.zhglxt.zjd.fragment.tx.ZjdTdqsFragment
import com.jymj.zhglxt.zjd.fragment.tx.ZjdTdsyFragment
import com.jymj.zhglxt.zjd.fragment.yzt.YztDcFragment
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.lzy.okgo.utils.HttpUtils
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.*
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_tjfx.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.item_cwtd_include.*
import kotlinx.android.synthetic.main.item_yzt_rbt_include.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URISyntaxException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class ZjdglFragment: BaseFragment<ZjdglPresenter, ZjdglModel>(), ZjdglContract.View, AMap.OnMapClickListener
        , AMapLocationListener, View.OnClickListener
        ,DropdownTabLayout.OnTabSelectedListener{

    //    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
//    private val VEC_URL = "https://c.tile.openstreetmap.org/%d/%d/%d.png"//%d
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"//%d
    private var aMap: AMap? = null
    private var mPolygon: Polygon? = null
    var aMapLocation: AMapLocation? = null//定位信息
    private val mLatLngs = ArrayList<LatLng>()
    var markers = ArrayList<Marker>()//所有聚合的点位
    private var mPolygonOptions: PolygonOptions? = null
    private var mMyLocationStyle: MyLocationStyle? = null
    var mListener: LocationSource.OnLocationChangedListener? = null
    var mLocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    var pointMarker: Marker? = null
    private var addMarker: Marker? = null
    private var onCameraChange: LatLng? = null
    private var pointStr = ""
    var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/note/")//tdt
    var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)

    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    //天地图
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/tdt/osmKong1/")//底图
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/osmKong1/").diskCacheSize(10 * 1024)

    private var mLatLng: LatLng? = null
    val MODE_NORMAL = 100
    val POINT_CLICK = 201//点查
    val POLYGON_CLICK = 202//框查
    val MODE_YL = 1039//院落点查
    val MODE_YZT = 1040//一张图
    val MODE_YD = 1041//用地 //默认宅基地首页
    val MODE_ZJD1 = 10411//宅基地 点查
    val MODE_ZJD2 = 10412//宅基地 添加
    val MODE_FJ = 1042//翻建
    val MODE_XC = 1043//巡查
    val MODE_CZ = 1044//出租 现 物业
    val MODE_NYYQ = 1045//农业园区  //默认产业首页
    val MODE_CYYQ = 1046//产业园区
    val MODE_WL = 1047//文旅
    val MODE_DKF= 1048//待开发
    val MODE_TDLY = 1054//土地利用  //默认土现首页
    val MODE_KJGH = 1055//空间规划
    val MODE_TDGH = 1049//土地规划
    val MODE_TDQS = 1050//土地权属
    val MODE_LDRK = 1051//流动人口  //默认社会治理首页
    val MODE_RJHJ_RJHJ = 10521//人居环境
    val MODE_RJHJ_GDDW = 10522//固定点位
    val MODE_WWJ = 1053//无违建
    val MODE_YQGL = 1056//舆情管理  12345

    val MODE_FW = 1057//房屋
    var TDLY_POINT_TYPE = 2 //土地利用点查 type 1:  type 2: //2009  type 3:  //2018 type 4: //2020
    private var typeLx = 1//如果是普通问题直接下发  如果是公厕问题需要内业审核
    private var isShow = 0////设置是否弹出问题弹出框  0不能进入  人居环境模块(1添加固定点位问题  2普通环境整治问题录入)  固定点位(3添加固定点位 4修改固定点位)

    private var mode: Int = MODE_YL//MODE_NORMAL  暂时默认用地
    private var click_mode: Int = MODE_NORMAL
    private var zjd_click_mode: Int = MODE_NORMAL
    var valueTc: BaseQuickAdapter<LayerEntity, BaseViewHolder>? = null
    private var renovatedFileList = ArrayList<RenovatedFile>()//巡查文件列表
    private var envirorUpPopu: CommenPop? = null//巡查弹出框
    private var schxtpFj:BaseQuickAdapter<RenovatedFile, BaseViewHolder>? = null//巡查弹出框图片
    private var selectedPhotos = ArrayList<String>()
    private val selectedVideos = ArrayList<String>()
    private var PjEnviorFileList = ArrayList<PjEnviorFileEntity>()
    private val videoIdList = ArrayList<Int>()
    private val fileIdList = ArrayList<Int>()
    private val sb = StringBuilder()
    private var isImage=2 //0 不显示图片  1显示图片  2显示点位
    private var gcImageURL1 = ""
    private var gcImageURL2 = ""
    private var gcImageType = 1
    private var dingDianUpPopu: CommenPop? = null
    private var path: String? = null
    private var ifxstp = 0  //1显示图片 2不显示图片

    private var yztListAdapter : BaseQuickAdapter<LayerYztBean,BaseViewHolder>? = null//右侧菜单一张图adapter
    private var sjListAdapter : BaseQuickAdapter<LayerSjBean,BaseViewHolder>? = null//右侧菜单事件adapter

    val layerSjList = ArrayList<LayerSjBean>()
    val layerYztList = ArrayList<LayerYztBean>()
    public val mLayers = ArrayList<LayerEntity>()
    public val mLayers1 = ArrayList<LayerEntity>()
    public var legendsYL: ArrayList<LegendEntity>? = null
    private var isYzt = true
    private var cunCode = ""
    private var appName =""


    override fun lazyLoad() {

    }

    override fun getLayoutResource(): Int {
        return R.layout.frag_zjdgl
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//添加天地图  VEC_URL, "tdt1-vec"  tdt-vecosm43
        //https://c-ssl.duitang.com/uploads/item/202004/09/20200409032441_mhYLN.jpeg
//        http://39.105.122.55:69/M00/00/0F/fwAAAWLHoXGALGpTAAAJXEOX02U600.jpg
        val parse = Uri.parse("android.resource://" + activity!!.getPackageName() + "/" + R.drawable.bl_login)
        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL
//        MapsInitializer.replaceURL("https://c-ssl.duitang.com/uploads/item/202004/09/20200409032441_mhYLN.jpeg", "tdt-kbt")//VEC_URL
        map_frag_zjdgl.onCreate(activity!!.intent.extras)
        appName = AppUtils.getAppName()
//        map_frag_zjdgl.invalidateDrawable(activity!!.getDrawable(R.drawable.bl_login))
        if (AppCache.getInstance().cunCode.length==15){
            cunCode = AppCache.getInstance().cunCode
//            rl_frag_zjdgl_yzt.visibility = View.VISIBLE
//            rl_frag_zjdgl_yd.visibility = View.VISIBLE
//            rl_frag_zjdgl_zjd.visibility = View.VISIBLE
//            rl_frag_zjdgl_cy.visibility = View.VISIBLE
//            rl_frag_zjdgl_shzl.visibility = View.VISIBLE
//            onClick(bt_frag_zjdgl_shzl)
            ivFragHomepageRight?.visibility = View.GONE
            HomePageFragment.tvFragHomepageLeft?.visibility = View.GONE
            HomePageFragment.tvFragHomepageLeft?.setText(AppCache.getInstance().xzqName)

        }
        HomePageFragment.tvFragHomepageLeftBack!!.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                rl_frag_zjdgl_yd.visibility = View.GONE
                rl_frag_zjdgl_zjd.visibility = View.GONE
                rl_frag_zjdgl_cy.visibility = View.GONE
                rl_frag_zjdgl_shzl.visibility = View.GONE
                if (AppCache.getInstance().type == 1||AppCache.getInstance().type == 2||AppCache.getInstance().type == 3)
                rl_frag_zjdgl_czsjsx.visibility = View.VISIBLE
                HomePageFragment.tvFragHomepageLeftBack?.visibility = View.GONE
                onClick(bt_frag_zjdgl_yzt)

            }
        }
        if (AppCache.getInstance().type == 1||AppCache.getInstance().type == 2||AppCache.getInstance().type == 3){
            rl_frag_zjdgl_czsjsx.visibility = View.VISIBLE
            bt_frag_zjdgl_czsjsx.setOnClickListener {
                var intent = Intent(activity, CbbsjsxActivity::class.java)//跳转菜单
                startActivity(intent)
            }
        }
        initAMap()
        setAMap()

        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        Thread(object : Runnable {
            override fun run() {
                try {
//                    Thread.sleep(900); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法

                    PopuPointUtils.initPopuPoint(activity,supl_frag_zjdgl)//初始化点查弹出框
                    setDrawerRightData()//初始化右侧侧滑
                    initTuCeng()//初始化图层控制器（）
                    location()//初始化定位
                    //默认选中
                    bt_frag_zjdgl_yzt.isActivated = true
//        MainActivity.tvTitle?.text = "宅基地管理"//村级流动人口管理
                    //点击定位
                    bt_frag_zjdgl_location.setOnClickListener {
                        //定位
                        if (aMapLocation != null) {
                            val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//坐标转换  火星坐标 转 2000
                            val point = LatLng(model.wgLat, model.wgLon)
//            val point = LatLng(myLocation.latitude, myLocation.longitude)
                            if (point.latitude > 0 && point.longitude > 0) {//point.longitude
                                aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude,
                                        point.longitude)))
                                pointMarker = aMap?.addMarker(MarkerOptions().position(point))

                                var pointString = "POINT(" + point.longitude + " " + point.latitude + ")"
                                //定位请求
                                if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR){
                                    if (mode == MODE_RJHJ_RJHJ){//pointString
                                        ifxstp = 1
                                        mPresenter.getEnviorSupvsQueryPoint(pointString,2)//"POINT(116.846966 39.797217)"  pointString
                                    }
                                }
                            } else {
                                Thread(object : Runnable {
                                    override fun run() {
                                        try {
                                            Thread.sleep(2000); // 延时3秒
                                        } catch (e: Exception) {
                                            e.printStackTrace();
                                        }
                                        val model1 = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
                                        val point1 = LatLng(model1.wgLat, model1.wgLon)
                                        //            val point = LatLng(myLocation.latitude, myLocation.longitude)
                                        if (point1.latitude > 0 && point1.longitude > 0) {
                                            aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point1.latitude,
                                                    point1.longitude)))
                                            pointMarker = aMap?.addMarker(MarkerOptions().position(point1))

                                            var pointString1 = "POINT(" + point1.longitude + " " + point1.latitude + ")"
                                            //定位请求
                                            if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR){
                                                if (mode == MODE_RJHJ_RJHJ){//pointString
                                                    ifxstp = 1
                                                    mPresenter.getEnviorSupvsQueryPoint(pointString1,2)//"POINT(116.846966 39.797217)"  pointString
                                                }
                                            }
                                        } else {
                                            Looper.prepare()
                                            ToastUtils.showShort("")
                                            Looper.loop()
                                        }
                                    }
                                }).start();
                            }
                        }
                    }
                    aMap?.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
                        //这个应该是地图的滑动监听吧
                        override fun onCameraChangeFinish(p0: CameraPosition?) {//调用热力图的地方
                            onCameraChange = p0!!.target
                            AppCache.getInstance().zijiLocation = "POINT(" + onCameraChange!!.longitude + " " + onCameraChange!!.latitude + ")"
                            if (aMap!=null){
                                val visibleRegion = aMap!!.projection.visibleRegion
                                val farLeft = visibleRegion.farLeft //可视区域的左上角。  position1
                                val nearright = visibleRegion.nearRight //可视区域的右下角。position2
                                val zoom = aMap!!.cameraPosition.zoom
//                    Log.e("onCameraChangeFinish",zoom.toString())
                                val code = AppCache.getInstance().code
                                if (mode == MODE_YL){//  &&code.equals("100110")
                                    if (zoom > 12) {//15
                                        mPresenter.getYzt(AppCache.getInstance().cunCode, 4, BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude))
                                    } else {
                                        clearAllMarker()
                                    }
                                }
                                if (pointMarker!=null){
                                    pointMarker!!.remove()
                                }
                                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//坐标转换  火星坐标 转 2000
                                val point = LatLng(model.wgLat, model.wgLon)
                                pointMarker = aMap?.addMarker(MarkerOptions().position(point))
                            }
                        }
                        override fun onCameraChange(p0: CameraPosition?) {
                            onCameraChange = p0!!.target
                            AppCache.getInstance().zijiLocation = "POINT(" + onCameraChange!!.longitude + " " + onCameraChange!!.latitude + ")"
                        }
                    })
                    //点击事件选中
                    bt_frag_zjdgl_point.setOnClickListener {//点击事件
                        if (bt_frag_zjdgl_point.isActivated){
                            /* if (mode == MODE_YZT||mode == MODE_YL||mode == MODE_TDQS||mode == MODE_NYYQ||mode == MODE_CYYQ
                                     ||mode == MODE_WL||mode == MODE_DKF||mode == MODE_KJGH||mode == MODE_TDLY||mode == MODE_TDGH){//这些模块默认是显示框选搜索的所以需要显示出来
                                 rl_frag_zjdgl_search.visibility = View.VISIBLE
                             }*/
                            bt_frag_zjdgl_point.isActivated = false
                            bt_frag_zjdgl_kuang.isActivated = false
                            bt_frag_zjdgl_gddw_add.isActivated = false
                            click_mode = MODE_NORMAL//选中点击事件
                            zjd_click_mode = MODE_NORMAL//选中点击事件

                        }else{
                            rl_frag_zjdgl_search.visibility = View.GONE
                            bt_frag_zjdgl_point.isActivated = true
                            bt_frag_zjdgl_kuang.isActivated = false
                            bt_frag_zjdgl_gddw_add.isActivated = false
                            click_mode = POINT_CLICK
                            zjd_click_mode = MODE_ZJD1
                        }
                    }
                    bt_frag_zjdgl_gddw_add.setOnClickListener {

                        if (bt_frag_zjdgl_gddw_add.isActivated){
                            bt_frag_zjdgl_gddw_add.isActivated = false
                            bt_frag_zjdgl_point.isActivated = false
                            click_mode = MODE_NORMAL//选中点击事件
                            zjd_click_mode = MODE_NORMAL//选中点击事件
                        }else{
                            bt_frag_zjdgl_gddw_add.isActivated = true
                            bt_frag_zjdgl_point.isActivated = false
                            click_mode = POINT_CLICK
                            zjd_click_mode = MODE_ZJD2
                        }
                    }
                    //定位设备位置 并调用添加弹框
                    bt_frag_zjdgl_add.setOnClickListener {
                        if (aMap!=null){
                            if (mode == MODE_RJHJ_RJHJ){
                                isShow = 2
                                if (aMapLocation!=null){
//                try {
                                    val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
                                    val point = LatLng(model.wgLat, model.wgLon)
                                    aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude, point.longitude)))
                                    aMap?.addMarker(MarkerOptions().position(point))
                                    pointStr = "POINT(" + point.longitude + " " + point.latitude + ")"
                                    mPresenter.getRjhjjcPoint(pointStr)/////////////////////////

                                }
                            }else if (mode == MODE_RJHJ_GDDW){
                                isShow = 3
                                val point1 = LatLng(onCameraChange!!.latitude, onCameraChange!!.longitude)
                                pointStr = "POINT(" + point1.longitude + " " + point1.latitude + ")"
                                mPresenter.getRjhjjcPoint(pointStr)
//            mPresenter.getEnviorSupvsQueryPoint(pointStr)
                                //绘制marker
                                if (addMarker!=null){
                                    addMarker!!.remove()
                                }
                                val draggable = MarkerOptions()
                                        .position(point1)
                                        .draggable(false)

                                addMarker = aMap!!.addMarker(draggable)
                            }else if (mode == MODE_RJHJ_RJHJ){

                            }
                        }

                    }
                    bt_frag_zjdgl_update.setOnClickListener {
                        isShow = 4
                        val point1 = LatLng(onCameraChange!!.latitude, onCameraChange!!.longitude)
                        pointStr = "POINT(" + point1.longitude + " " + point1.latitude + ")"
                        mPresenter.getRjhjjcPoint(pointStr)
//            mPresenter.getEnviorSupvsQueryPoint(pointStr)
                        //绘制marker
                        if (addMarker!=null){
                            addMarker!!.remove()
                        }
                        val draggable = MarkerOptions()
                                .position(point1)
                                .draggable(false)
                        addMarker = aMap!!.addMarker(draggable)
                    }
                    //固定点位修改
                    rl_frag_zjdgl_update.setOnClickListener {
                        isShow = 4
                        val point1 = LatLng(onCameraChange!!.latitude, onCameraChange!!.longitude)
                        pointStr = "POINT(" + point1.longitude + " " + point1.latitude + ")"
                        mPresenter.getRjhjjcPoint(pointStr)
//            mPresenter.getEnviorSupvsQueryPoint(pointStr)
                        //绘制marker
                        if (addMarker!=null){
                            addMarker!!.remove()
                        }
                        val draggable = MarkerOptions()
                                .position(point1)
                                .draggable(false)

                        addMarker = aMap!!.addMarker(draggable)
                    }
                    //选中框查按钮
                    bt_frag_zjdgl_kuang.setOnClickListener {
                        if (bt_frag_zjdgl_kuang.isActivated){//未选中
                            rl_frag_zjdgl_search.visibility = View.VISIBLE
                            bt_frag_zjdgl_point.isActivated = false
                            bt_frag_zjdgl_kuang.isActivated = false
                        }else{
                            rl_frag_zjdgl_search.visibility = View.VISIBLE
                            bt_frag_zjdgl_point.isActivated = false
                            bt_frag_zjdgl_kuang.isActivated = true
                            click_mode = POLYGON_CLICK
                        }
                    }
                    //查询按钮按钮
                    bt_frag_zjdgl_search.setOnClickListener {
                        onSearch()
                    }
                    //测算按钮按钮
                    bt_suan_ctglmk.setOnClickListener {
                        sb.delete(0, sb.length)
                        //拼接框选点击的坐标
                        sb.append("POLYGON((")
                        for (i in mLatLngs.indices) {
                            if (i == mLatLngs.size - 1) {
                                sb.append(mLatLngs[i].longitude).append(" ").append(mLatLngs[i].latitude).append(",")
                                sb.append(mLatLngs[0].longitude).append(" ").append(mLatLngs[0].latitude).append("))")
                            } else {
                                sb.append(mLatLngs[i].longitude).append(" ").append(mLatLngs[i].latitude).append(",")
                            }
                        }
                        PopuPointUtils.initPopuTdkf(activity,supl_frag_zjdgl,sysXzqEntity, sb.toString(),object:PopuPointUtils.OnPopuTdkfLinster {
                            override fun onClick(cscsBean: CscsBean?) {
                                mPresenter.getSysCeSuna(cscsBean!!)
                                mPresenter.getSysCeSuna2(cscsBean)
//                    ToastUtils.showShort(Gson().toJson(cscsBean))
                            }
                        })
                    }
                    //清除图层覆盖物
                    bt_frag_zjdgl_clear.setOnClickListener {
                        aMap!!.clear()//清除地图上所有覆盖物（包括图层）
                        aMap!!.postInvalidate()
                        clearMap()//可以清除框选的点位
//            clearLayer()//清空图层选中
                        tabTc("99",2)//展示图层
//            addOverLayer(mLayers)
                    }
                    //统计分析
                    bt_frag_zjdgl_tjfx.setOnClickListener {
                        val intent = Intent(activity, TjfxActivity::class.java)
                        startActivity(intent)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()

        bt_frag_zjdgl_yzt.setOnClickListener(this)
        bt_frag_zjdgl_zjd.setOnClickListener(this)
        bt_frag_zjdgl_cy.setOnClickListener(this)
        bt_frag_zjdgl_yd.setOnClickListener(this)
        bt_frag_zjdgl_shzl.setOnClickListener(this)

    }

    override fun initDatas() {

        moveCenter()//延时定位到项目镇
        supl_frag_zjdgl1 = supl_frag_zjdgl

        if (AppCache.getInstance().type==4&&appName.equals("黄山店")){
            rl_frag_zjdgl_yd.visibility = View.VISIBLE
            rl_frag_zjdgl_zjd.visibility = View.VISIBLE
            rl_frag_zjdgl_cy.visibility = View.VISIBLE
            rl_frag_zjdgl_shzl.visibility = View.VISIBLE
            ivFragHomepageRight?.visibility = View.VISIBLE
            rl_frag_zjdgl_czsjsx.visibility = View.GONE
            onClick(bt_frag_zjdgl_shzl)
        }
        ll_frag_layer_yxt.setOnClickListener {
            /*if (iv_frag_layer_yxt.isActivated){
                iv_frag_layer_yxt.isActivated = false
                iv_frag_layer_yxt.visibility = View.GONE
//                tv__frag_layer_yxt.setTextColor(Color.parseColor("#333333"))
            }else{
                iv_frag_layer_yxt.isActivated = true
                iv_frag_layer_yxt.visibility = View.VISIBLE
//                tv__frag_layer_yxt.setTextColor(Color.parseColor("#4CA2FE"))
            }*/
            tabTc("影像图",2)
        }
        ll_frag_layer_dt.setOnClickListener {
            tabTc("底图",2)

        }
//            tabTc("黄山店图",2)
            ll_frag_layer_tdt.setOnClickListener {
                tabTc("天地图",2)

        }
        ll_frag_layer_tdlyxz.setOnClickListener {
            /*if (iv_frag_layer_tdlyxz.isActivated){
                iv_frag_layer_tdlyxz.isActivated = false
                iv_frag_layer_tdlyxz.visibility = View.GONE
//                tv__frag_layer_tdlyxz.setTextColor(Color.parseColor("#333333"))
            }else{
                iv_frag_layer_tdlyxz.isActivated = true
                iv_frag_layer_tdlyxz.visibility = View.VISIBLE
//                tv__frag_layer_tdlyxz.setTextColor(Color.parseColor("#4CA2FE"))
            }*/
            tabTc("土地利用2018",2)//土地利用2009
        }
        ll_frag_layer_gtgh.setOnClickListener {
            /*if (iv_frag_layer_gtgh.isActivated){
                iv_frag_layer_gtgh.isActivated = false
                iv_frag_layer_gtgh.visibility = View.GONE
//                tv__frag_layer_gtgh.setTextColor(Color.parseColor("#333333"))
            }else{
                iv_frag_layer_gtgh.isActivated = true
                iv_frag_layer_gtgh.visibility = View.VISIBLE
//                tv__frag_layer_gtgh.setTextColor(Color.parseColor("#4CA2FE"))
            }*/
            tabTc("规划",2)
        }
        ll_frag_layer_jjfb.setOnClickListener {
            /* if (iv_frag_layer_jjfb.isActivated){
                 iv_frag_layer_jjfb.isActivated = false
                 iv_frag_layer_jjfb.visibility = View.GONE
             }else{
                 iv_frag_layer_jjfb.isActivated = true
                 iv_frag_layer_jjfb.visibility = View.VISIBLE
             }*/
            tabTc("基准地价",2)
        }
        ll_frag_layer_cyfz.setOnClickListener {
            /*if (iv_frag_layer_cyfz.isActivated){
                iv_frag_layer_cyfz.isActivated = false
                iv_frag_layer_cyfz.visibility = View.GONE
            }else{
                iv_frag_layer_cyfz.isActivated = true
                iv_frag_layer_cyfz.visibility = View.VISIBLE
            }*/
            tabTc("产业园区",2)
        }
        mPresenter.getSysXzqQueryXzqList(AppCache.getInstance().code,4)

        if (AppCache.getInstance().code.equals("110111009022")){
            tv__frag_layer_gtgh.setText("村庄规划")
        }
        if (!AppCache.getInstance().cunCode.contains("100110")){
            ll_frag_layer_jjfb.visibility = View.INVISIBLE
        }

        //底部图例
        iv_frag_zjdgl_tuli.setOnClickListener {
            id_frag_zjdgl_tuli.visibility = View.VISIBLE
            iv_frag_zjdgl_tuli.visibility = View.GONE
        }
        iv_frag_zjdgl_suo_fang.setOnClickListener {
            id_frag_zjdgl_tuli.visibility = View.GONE
            iv_frag_zjdgl_tuli.visibility = View.VISIBLE
        }
        /*Thread(object : Runnable {
            override fun run() {
                try {
//                    Thread.sleep(1100); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()*/

    }


    private fun tabTc(tuCe:String,type:Int) {//type 1 清 2 不清 3 选中  , type:Int
        val stringList = ArrayList<String>()//TabLayerMapBean

        for (i in mLayers ){//layerList
            if (i.num == 1&&tuCe.equals("")){
                i.isCheck = false
            }

            if (type == 1){
                if (i.num == 1){
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }else{
                        i.isCheck = false
                    }
                }else if (i.num == 2){
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }
                }
            }else if (type == 2){
                if (i.name.equals(tuCe)) {
                    i.isCheck = !i.isCheck
                }
            }else if (type == 3){
                if (i.name.equals(tuCe)) {
                    i.isCheck = true
                }
            }
                if (i.isCheck) {
                    stringList.add(i.name)
            }
        }
        for (i in mLayers1 ){//layerList
            if (i.num == 1&&tuCe.equals("")){
                i.isCheck = false
            }

            if (type == 1){
                if (i.num == 1){
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }else{
                        i.isCheck = false
                    }
                }else if (i.num == 2){
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }
                }
            }else if (type == 2){
                if (i.name.equals(tuCe)) {
                    i.isCheck = !i.isCheck
                }
            }else if (type == 3){
                if (i.name.equals(tuCe)) {
                    i.isCheck = true
                }
            }
                if (i.isCheck) {
                    stringList.add(i.name)
            }
        }

        /*for (i in mLayers){
            if (Gson().toJson(layerList).contains("\"name\":\"${i.name}\"")){
                i.isCheck = false
            }
            if (stringList.contains(i.name)){
                i.isCheck = true
            }
        }*/
        if (aMap!=null){
            aMap!!.clear()

            var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
            var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
            var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
            var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
            aMap?.addTileOverlay(opt_dk);
            addOverLayer(mLayers)//问题在这里
            addOverLayer(mLayers1)//问题在这里
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
        }

    }
    //清除图层  只选中默认的镇界和村界
    private fun clearLayer(type : Boolean){
//        tv__frag_layer_gtgh.setTextColor(Color.parseColor("#333333"))
        for (i in mLayers){//layerList
            if (type&&(i.name=="影像图"||i.name=="底图"||i.name=="天地图")){
                i.isCheck = false
            }
            if (i.name!="影像图"&&i.name!="底图"&&i.name!="天地图"){
                i.isCheck = false
            }

                if (i.name=="镇界"||i.name=="村界"){
                    i.isCheck = true
                }
        }
        for (i in mLayers1){//layerList
            if (type&&(i.name=="影像图"||i.name=="底图"||i.name=="天地图")){
                i.isCheck = false
            }
            if (i.name!="影像图"&&i.name!="底图"&&i.name!="天地图"){
                i.isCheck = false
            }

                if (i.name=="镇界"||i.name=="村界"){
                    i.isCheck = true
                }
        }
    }
    //点击事件-监听
    override fun onClick(v: View?) {

        supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        tl_frag_zjd.visibility = View.GONE//隐藏布局上方tab 切换小模块
        tl_frag_cy.visibility = View.GONE//隐藏布局上方tab 切换小模块
        tl_frag_tx.visibility = View.GONE//隐藏布局上方tab 切换小模块
        tl_frag_shzl.visibility = View.GONE//隐藏布局上方tab 切换小模块
        supl_frag_zjdgl.setScrollableView(null)
        HomePageFragment.tvFragHomepageLeft!!.visibility = View.GONE//隐藏左上角扫描二维码按钮
        rl_frag_zjdgl_point.visibility = View.GONE//隐藏右侧工具按钮 点查
        rl_frag_zjdgl_gddw_add.visibility = View.GONE
        rl_frag_zjdgl_add.visibility = View.GONE//隐藏右侧工具按钮 添加（暂时主要用于人居环境模块）
        rl_frag_zjdgl_update.visibility = View.GONE//隐藏右侧工具按钮 修改（暂时主要用于人居环境模块）
        rl_frag_zjdgl_kuang.visibility = View.GONE//隐藏右侧工具按钮 框查
        rl_frag_zjdgl_search.visibility = View.GONE//隐藏右侧工具按钮 框查搜索
        rl_frag_zjdgl_clear.visibility = View.GONE//隐藏右侧工具按钮 清除覆盖物
        rl_frag_zjdgl_tjfx.visibility = View.GONE//统计分析
        rl_suan_ctglmk.visibility = View.GONE//测算按钮

        id_frag_zjdgl_tuli.visibility = View.GONE
        tv_frag_zjgl_rjhj_add.visibility = View.GONE
        ll_frag_zjdgl_sjyzt.visibility = View.GONE//隐藏侧滑里边的事件与一张图
//        ll_frag_cwtd_lntz.visibility = View.GONE
        bt_frag_zjdgl_point.isActivated = false
        bt_frag_zjdgl_gddw_add.isActivated = false
        bt_frag_zjdgl_kuang.isActivated = false
        /*if (v!!.isActivated){//用于判断是否可以取消选中
            v!!.isActivated = false
        }else{*/
            bt_frag_zjdgl_yzt.isActivated = false//一张图
            bt_frag_zjdgl_zjd.isActivated = false//宅基地
            bt_frag_zjdgl_cy.isActivated = false//产业
            bt_frag_zjdgl_yd.isActivated = false//土现
            bt_frag_zjdgl_shzl.isActivated = false//社会治理
        for (i in layerSjList){//清除右侧项目选中状态
            i.isCheck = false
        }
        if (sjListAdapter!=null)
        sjListAdapter!!.notifyDataSetChanged()
        v!!.isActivated = true
        isYzt = false
        click_mode = MODE_NORMAL
        clearLayer(true)
        tlcl_frag_zjdgl_tl.visibility = View.GONE
//        }
        when(v){
            bt_frag_zjdgl_yzt->{//一张图
                isYzt = true
                rl_frag_zjdgl_clear.visibility = View.GONE
                HomePageFragment.tvFragHomepageTitle!!.text = ""//一张图
                mode = MODE_YL//MODE_YL//MODE_YZT
                if (AppCache.getInstance().cunCode.length!=15){
                    HomePageFragment.tvFragHomepageLeft!!.visibility = View.VISIBLE
                }
                item_yzt_rgp.clearCheck()//清除右侧菜单一张图所有按钮
                AppCache.getInstance().code = AppCache.getInstance().cunCode
                AppCache.getInstance().xzCenter = AppCache.getInstance().loginCenter
                initTuli()
                initTcYzt()
                showHintViewPager(vp_frag_yzt)
                zjdYztDcFrag.diaoyong()
            }
            bt_frag_zjdgl_zjd->{//宅基地
                rl_frag_zjdgl_clear.visibility = View.VISIBLE
                if (AppCache.getInstance().cunCode.length!=15){
                    HomePageFragment.tvFragHomepageLeftBack!!.visibility = View.VISIBLE
                }
                HomePageFragment.tvFragHomepageTitle!!.text = "房屋资产管理"//宅基地管理
                tl_frag_zjd.visibility = View.VISIBLE//上方tab
//                tl_frag_zjd.getTabAt(1)?.select()
                AppCache.getInstance().code = cunCode
                initTuli()
                if (tl_frag_zjd.tabCount>0){
                    tl_frag_zjd.getTabAt(0)?.select()
                }
                showHintViewPager(ll_frag_zjd)
            }
            bt_frag_zjdgl_cy->{//产业
                rl_frag_zjdgl_clear.visibility = View.VISIBLE
                rl_frag_zjdgl_clear.visibility = View.GONE

                if (AppCache.getInstance().cunCode.length!=15){
                    HomePageFragment.tvFragHomepageLeftBack!!.visibility = View.VISIBLE
                }
                HomePageFragment.tvFragHomepageTitle!!.text = "产业发展管理"//园区管理  空间结构
                tl_frag_cy.visibility = View.VISIBLE
//                tl_frag_cy.getTabAt(1)?.select()
                AppCache.getInstance().code = cunCode
                initTuli()
                if (tl_frag_cy.tabCount>0){
                    tl_frag_cy.getTabAt(0)?.select()
                }
                showHintViewPager(ll_frag_cy)

            }
            bt_frag_zjdgl_yd->{//土现

                rl_frag_zjdgl_clear.visibility = View.VISIBLE

                if (AppCache.getInstance().cunCode.length!=15){
                    HomePageFragment.tvFragHomepageLeftBack!!.visibility = View.VISIBLE
                }
                HomePageFragment.tvFragHomepageTitle!!.text = "土地资源管理"
                tl_frag_tx.visibility = View.VISIBLE
//                tl_frag_tx.getTabAt(1)?.select()
                AppCache.getInstance().code = cunCode
                initTuli()
                if (tl_frag_tx.tabCount>0){
                    tl_frag_tx.getTabAt(0)?.select()
                }
                showHintViewPager(ll_frag_tx)
//                showHintViewPager(vp_frag_yzt)
            }
            bt_frag_zjdgl_shzl->{//社区治理
                rl_frag_zjdgl_clear.visibility = View.GONE

                if (AppCache.getInstance().cunCode.length!=15){
                    HomePageFragment.tvFragHomepageLeftBack!!.visibility = View.VISIBLE
                }
                HomePageFragment.tvFragHomepageTitle!!.text = "集体经济监测"//社会治理
                tl_frag_shzl.visibility = View.VISIBLE
//                tl_frag_shzl.getTabAt(1)?.select()
                AppCache.getInstance().code = cunCode
                initTuli()
                if (tl_frag_shzl.tabCount>0){
                    tl_frag_shzl.getTabAt(0)?.select()
                }
                showHintViewPager(ll_frag_shzl)

            }
        }
    }

    private fun showHintViewPager(view:View) {//用于控制显示那个模块
        vp_frag_yzt.visibility = View.GONE
        ll_frag_zjd.visibility = View.GONE
        ll_frag_cy.visibility = View.GONE
        ll_frag_tx.visibility = View.GONE
        ll_frag_shzl.visibility = View.GONE
        view.visibility = View.VISIBLE
    }

    //初始化图层控制器
    private fun initTuCeng() {
        /*rlv_frag_zjdgl_tc.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        valueTc = object : BaseQuickAdapter<LayerEntity, BaseViewHolder>(R.layout.item_recy_map, mLayers) {
            override fun convert(helper: BaseViewHolder, item: LayerEntity) {
                helper.itemView.visibility = if (item.isShow) View.VISIBLE else View.GONE
                if (!item.isShow) {
                    return
                }
                if (item.name != "宅翻建" && item.name != "流动人口") {
                    val lp: ViewGroup.LayoutParams
                    lp = helper.getView<ConstraintLayout>(R.id.cb_item_cl).getLayoutParams()
                    lp.height = ScreenUtils.dpToPx(activity, 40f).toInt()
                    helper.getView<ConstraintLayout>(R.id.cb_item_cl).setLayoutParams(lp)
                    helper.getView<ConstraintLayout>(R.id.cb_item_cl).visibility = View.VISIBLE
                    helper.setChecked(R.id.cb_item_map, item.isCheck)
                            .setText(R.id.tv_item_map, item.name)
                    val view = helper.getView<CheckBox>(R.id.cb_item_map)
                    view.isClickable = false
                    helper.itemView.setOnClickListener {
                        helper.setChecked(R.id.cb_item_map, !item.isCheck)
                        item.isCheck = !item.isCheck
                        aMap!!.clear()
                        addOverLayer(mLayers)//暂时没用到
                    }
                } else {
                    val lp: ViewGroup.LayoutParams
                    lp = helper.getView<ConstraintLayout>(R.id.cb_item_cl).getLayoutParams()
                    lp.height = ScreenUtils.dpToPx(activity, 0f).toInt()
                    helper.getView<ConstraintLayout>(R.id.cb_item_cl).setLayoutParams(lp)
                    val view = helper.getView<ConstraintLayout>(R.id.cb_item_cl)
                    helper.itemView.visibility = View.GONE
                }
            }
        }
        rlv_frag_zjdgl_tc!!.adapter = valueTc*/
        //点击图层切换
        ivFragHomepageRight?.setOnClickListener {
            /*if (rlv_frag_zjdgl_tc.isShown) {
                rlv_frag_zjdgl_tc.visibility = View.GONE
            } else {
                rlv_frag_zjdgl_tc.visibility = View.VISIBLE
            }*/
            if (dl_frag_zjdgl.isDrawerOpen(Gravity.RIGHT)){
                dl_frag_zjdgl?.closeDrawer(Gravity.RIGHT)
            }else{
                dl_frag_zjdgl?.openDrawer(Gravity.RIGHT)
            }
        }
    }

    //一张图选中图层
    private fun initTcYzt(){
//        rl_frag_zjdgl_kuang.visibility = View.VISIBLE//右侧工具：框
//        rl_frag_zjdgl_search.visibility = View.GONE//右侧工具：搜索
        clearLayer(false)
        /*tabTc("院落",2)
        tabTc("拆除腾退",2)
        tabTc("绿化",2)
        tabTc("水域",2)
        tabTc("道路",2)
        tabTc("大棚",2)
        tabTc("非宅",2)
        tabTc("农业园区面",2)*/
//        tabTc("三块田",2)
//        tabTc("天地图",3)
//        tabTc("底图",3)
//        tabTc("一张图新",2)
//        tabTc("院落",2)
        if (AppCache.getInstance().code.length!=15){
            tabTc("区界",2)
        }
        if (AppCache.getInstance().code.equals("100110")){
//            tabTc("房屋",2)
        }
    }

    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {


        for (l in layers) {
            when (l.name) {
                "影像图" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtSta)
                        aMap!!.addTileOverlay(opt_tdtStaN)
                        iv_frag_layer_yxt.isActivated = true
                        iv_frag_layer_yxt.visibility = View.VISIBLE
                    }else{//opt_tdtOsm
                        iv_frag_layer_yxt.isActivated = false
                        iv_frag_layer_yxt.visibility = View.GONE
                        /*aMap!!.addGroundOverlay(GroundOverlayOptions()
                                .image(BitmapDescriptorFactory.fromResource(R.drawable.hsd))
                                .position(getCenter(AppCache.getInstance().loginCenter),10f)
                                )*/
                        /*.positionFromBounds(LatLngBounds(
                                LatLng(44.415, 8.937),
                                LatLng(44.42, 8.942)))*/
                    }
                }//opt_tdtn
                "底图" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtOsm)
                        iv_frag_layer_dt.isActivated = true
                        iv_frag_layer_dt.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_dt.isActivated = false
                        iv_frag_layer_dt.visibility = View.GONE
                    }
                }
                "天地图" -> {
                    if (l.isCheck) {
//                        ToastUtils.showShort("天地图")
                        aMap!!.addTileOverlay(opt_tdtn)
                        aMap!!.addTileOverlay(opt_tdtnN)
                        iv_frag_layer_tdt.isActivated = true
                        iv_frag_layer_tdt.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_tdt.isActivated = false
                        iv_frag_layer_tdt.visibility = View.GONE
                    }
                }
                "产业园区" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                        iv_frag_layer_cyfz.isActivated = true
                        iv_frag_layer_cyfz.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_cyfz.isActivated = false
                        iv_frag_layer_cyfz.visibility = View.GONE
                    }
                }
                "基准地价" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                        iv_frag_layer_jjfb.isActivated = true
                        iv_frag_layer_jjfb.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_jjfb.isActivated = false
                        iv_frag_layer_jjfb.visibility = View.GONE
                    }
                }
                "土地利用2018" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                        iv_frag_layer_tdlyxz.isActivated = true
                        iv_frag_layer_tdlyxz.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_tdlyxz.isActivated = false
                        iv_frag_layer_tdlyxz.visibility = View.GONE
                    }
                }
                /*"土现" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                        iv_frag_layer_tdlyxz.isActivated = true
                        iv_frag_layer_tdlyxz.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_tdlyxz.isActivated = false
                        iv_frag_layer_tdlyxz.visibility = View.GONE
                    }
                }*/
                "经济分布" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                        iv_frag_layer_jjfb.isActivated = true
                        iv_frag_layer_jjfb.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_jjfb.isActivated = false
                        iv_frag_layer_jjfb.visibility = View.GONE
                    }
                }
                "规划" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                        iv_frag_layer_gtgh.isActivated = true
                        iv_frag_layer_gtgh.visibility = View.VISIBLE
                    }else{
                        iv_frag_layer_gtgh.isActivated = false
                        iv_frag_layer_gtgh.visibility = View.GONE
                    }
                }
                "院落" -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
                else -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
            }
            /*if (l.name.contains("拆违腾地")){
                if (l.name.equals("拆违腾地2017年台账")&&!l.isCheck){
                    item_cwtd_lnxz_2017tz.isChecked =false
                }else if (l.name.equals("拆违腾地2018年台账")&&!l.isCheck){
                    item_cwtd_lnxz_2018tz.isChecked =false
                }else if (l.name.equals("拆违腾地2019年台账")&&!l.isCheck){
                    item_cwtd_lnxz_2019tz.isChecked =false
                }else if (l.name.equals("拆违腾地2020年台账")&&!l.isCheck){
                    item_cwtd_lnxz_2020tz.isChecked =false
                }
            }*/
        }

        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
    }
    //移动到镇/村中心点
    var isOne = true
    private fun moveCenter() {
        aMap!!.setOnMapLoadedListener(object:AMap.OnMapLoadedListener{//监听加载完成
            override fun onMapLoaded() {
            if (isOne){
                isOne = false
                setYztPager()//一张图
                seTabZjdPager()//宅基地管理
                seTabCyPager()//产业管理
                seTabTxPager()//土现管理
                seTabShzlPager()//社会治理
//                initTuli()//图层

                if (AppCache.getInstance().cunCode.length==15){
                    onClick(bt_frag_zjdgl_shzl)
                }else{
                    initTuli()//图层  classicmug  shengding
                }
            }
            Thread(object : Runnable {
                override fun run() {

                    try {
//                        Thread.sleep(1300); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法

                        Thread.sleep(1500); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法
                        if (AppCache.getInstance().type == 1) {
                            var point = getCenter("POINT(116.397128 39.916527)")
                            aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 9.5f, 0f, 0f)))
                        } else if (AppCache.getInstance().type == 2) {
                            var point = getCenter(AppCache.getInstance().loginCenter)
                            aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 10.5f, 0f, 0f)))
                        } else if (AppCache.getInstance().type == 3){
                            var point = getCenter(AppCache.getInstance().loginCenter)
                            aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 11.5f, 0f, 0f)))
                        } else if (AppCache.getInstance().type == 4){
                            var point = getCenter(AppCache.getInstance().loginCenter)
                            aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 12.5f, 0f, 0f)))
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }).start()
            }
        })
        /*Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(3000); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法
            setYztPager()//一张图
            seTabZjdPager()//宅基地管理
            seTabCyPager()//产业管理
            seTabTxPager()//土现管理
            seTabShzlPager()//社会治理
            initTuli()//图层
            val point = getCenter(AppCache.getInstance().loginCenter)
            if (AppCache.getInstance().code.length == 9) {
                aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 12f, 0f, 0f)))
            } else {
                aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 14f, 0f, 0f)))
            }

//                    aMap?.addMarker(MarkerOptions().position(point))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()*/
    }
    //添加图层
    private fun initTuli() {
        legendsYL = ArrayList<LegendEntity>()
//        legends4L = ArrayList<LegendEntity>()
        mLayers.clear()
        mLayers1.clear()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//AppCache.getInstance().proID.toInt()  ,legends4L,1  AppCache.getInstance().proID.toInt()

        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        addOverLayer(mLayers)
        addOverLayer(mLayers1)

    }
    private fun onSearch() {
        if (mLatLngs.size > 2) {
            /**
             * 将sb（拼接的所有经纬度）清空
             */

            sb.delete(0, sb.length)
            //拼接框选点击的坐标
            sb.append("POLYGON((")
            for (i in mLatLngs.indices) {
                if (i == mLatLngs.size - 1) {
                    sb.append(mLatLngs[i].longitude).append(" ").append(mLatLngs[i].latitude).append(",")
                    sb.append(mLatLngs[0].longitude).append(" ").append(mLatLngs[0].latitude).append("))")
                } else {
                    sb.append(mLatLngs[i].longitude).append(" ").append(mLatLngs[i].latitude).append(",")
                }
            }
            when(mode){
                MODE_YL ->{ //mPresenter.getYewuFrame(sb.toString())//业务框查
                    mPresenter.getFrameJcxx(sb.toString())
                }
                MODE_TDLY ->{//searchPage
                    zjdTdlyFrag.getSearch(sb.toString())
                }
                MODE_TDGH ->{//searchPage
                    zjdTdlyFrag.getSearchTG(sb.toString())
                }
                MODE_TDQS ->{//searchPage
                    zjdTdlyFrag.getSearchQS(sb.toString())
                }
                MODE_KJGH ->{//searchPage
                    zjdTdlyFrag.getSearchKJGH(sb.toString())
                }
            }
        }else{
            ToastUtils.showShort("至少选择两个以上的点位")
        }
    }
    var ceSuna: CSEntity? = null
    var ceSuna2: CSEntity? = null
    override fun returnSysCeSuna(renovated: CSEntity,cscsBean: CscsBean) {
        ceSuna = renovated
        if (ceSuna!=null&&ceSuna2!=null){
            var ceShi = Intent(activity, BaseDataActivity::class.java)
            ceShi.putExtra("ceSuna",ceSuna)
            ceShi.putExtra("ceSuna2",ceSuna2)
            startActivity(ceShi)
            ceSuna = null
            ceSuna2 = null
        }

    }

    override fun returnSysCeSuna2(renovated: CSEntity) {
        ceSuna2 = renovated
        if (ceSuna!=null&&ceSuna2!=null){
            var ceShi = Intent(activity, BaseDataActivity::class.java)
            ceShi.putExtra("ceSuna",ceSuna)
            ceShi.putExtra("ceSuna2",ceSuna2)
            startActivity(ceShi)
            ceSuna = null
            ceSuna2 = null
        }
    }

    override fun returnPointGetYl(result: YLEntity) {
        if (result!=null){
            kuangGeomentLine(result.geometry)
            PopuPointUtils.initPointGetYl(activity,supl_frag_zjdgl,result)
        }
    }

    override fun returnFanJian(fjBean: FjBean) {//巡查点查
        if (!isYzt){
            if (fjBean.id!=-1L){
                AppCache.getInstance().isFjList = 1//FjxcActivity
                val intent = Intent(activity, FjxcActivity::class.java)
                intent.putExtra("id", fjBean.id)
                startActivity(intent)
            }else{
                if (fjBean.qutype==1L||fjBean.qutype==0L){
                    initPopuFanJian(fjBean)
                    CommenPop.backgroundAlpha(0.5f, activity)
                    envirorUpPopu!!.showAtLocation(supl_frag_zjdgl, Gravity.CENTER, 0, 0)
                }else{
//                ToastUtils.showShort("此问题状态已下发")
                }
            }
        }else{
            if (fjBean.id!=-1L){
                AppCache.getInstance().isFjList = 1//FjxcActivity
                val intent = Intent(activity, FjxcActivity::class.java)
                intent.putExtra("id", fjBean.id)
                startActivity(intent)
            }else{
                ToastUtils.showShort("此点位暂无数据")
            }
        }

    }
    var sysXzqEntity = ArrayList<SysXzqEntity>()
    override fun returnSysXzqQueryXzqList(renovated: ArrayList<SysXzqEntity>) {
        sysXzqEntity.addAll(renovated)
        if (sysXzqEntity.size>0){
            val get = sysXzqEntity.get(0)
            AppCache.getInstance().xzqZhenName = get.parentName

        }
        HomePageFragment.tvFragHomepageLeft!!.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                PopuTjfxUtils.getInstance().initPopuPoint(activity,supl_frag_zjdgl,renovated, HomePageFragment.tvFragHomepageLeft!!.text.toString(),
                        object: PopuTjfxUtils.OnTjfxLinear{
                            override fun onClick(sysXzqEntities: MutableList<SysXzqEntity>?) {
                                var string = ""
                                for (i in sysXzqEntities!!){
                                    if (i.isCheck){
                                        string = string+i.name//+","
//                            if (!HomePageFragment.tvFragHomepageLeft!!.text.toString().equals(string)){
                                        cunCode = i.code
                                        AppCache.getInstance().code = cunCode
//                            initTuli()
                                        if (AppCache.getInstance().type==1||appName.equals("黄山店")){
//                                            onClick(bt_frag_zjdgl_yd)   ||appName.equals("黄山店")
                                            onClick(bt_frag_zjdgl_shzl)
                                            rl_frag_zjdgl_yd.visibility = View.VISIBLE
                                            rl_frag_zjdgl_zjd.visibility = View.VISIBLE
                                            rl_frag_zjdgl_cy.visibility = View.VISIBLE
                                            rl_frag_zjdgl_shzl.visibility = View.VISIBLE
                                            rl_frag_zjdgl_czsjsx.visibility = View.GONE

                                        }else{
                                            onClick(bt_frag_zjdgl_shzl)
                                        }
                                        if (tabLayout!=null){
                                            tabLayout?.getTabAt(tabPosition)!!.select()
                                        }
                                        /*if (!string.equals("")){
                                            string = string.substring(0,string.length-1)
                                        }*/


                                        HomePageFragment.tvFragHomepageLeft!!.setText(string)
                                        AppCache.getInstance().xzCenter = i.center
                                        var point = getCenter(i.center)
                                        aMap?.addMarker(MarkerOptions().position(point))
                                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 12.5f, 0f, 0f)))
//                            }
                                        break
                                    }
                                }

//                    onClick(bt_frag_zjdgl_yd)

//                    ToastUtils.showShort(string)
                            }
                        })

            }
        }
    }
    //出租扫描二维码
    override fun returnSmewmcyl(msg: YlFolwEntity) {
        val intent = Intent(activity, LdrkDetailActivity::class.java)
        intent.putExtra("ylId", msg.objectid)
        startActivity(intent)
    }
    //出租热力图
    override fun returnRlt(msg: List<XzqInfoEntity>, type: Int) {
//        Log.e("returnRlt",Gson().toJson(msg))
        clearAllMarker()
//        if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF) {
            for (i in msg) {

                val location = i.center
                if (!location.equals("")){
                    val center1 = getCenter(location)
                    var counts = i.flowcount
                    if (mode == MODE_CZ){
                        counts = i.roomcount
                    }
                    if (counts > 0){
                        setCzMarker(location, center1!!.latitude,center1.longitude,R.drawable.ic_hbjc_dcl_zs,i)
                    }
                }

                /*val zoom = aMap!!.cameraPosition.zoom
                 if (zoom < 16) {//type == 1 &&
//                    setWtMarker(i,type)
                    setMarker(R.drawable.ic_hbjc_dcl_zs, i)
                }else{
                     clearAllMarker()
                 }*/

            }
//        }
    }
    override fun returnYzt(msg: List<XzqInfoEntity>, type: Int) {
        clearAllMarker()
        for (i in msg) {
            val location = i.center
            if (!location.equals("")&&mode == MODE_YL){
                val center1 = getCenter(location)
                setYztMarker(location, center1!!.latitude,center1.longitude,R.drawable.ic_hbjc_dcl_zs,i)

            }
        }
    }
    override fun returnZjdRlt(msg: List<ApplyCountEntity>, type: Int) {
        clearAllMarker()
        for (i in msg) {
            val location = i.location
            if (!location.equals("")&&mode == MODE_YD){
                val center1 = getCenter(location)
                setZjdMarker(location, center1!!.latitude,center1.longitude,R.drawable.ic_hbjc_dcl_zs,i)
            }

            /*val zoom = aMap!!.cameraPosition.zoom
            if (type == 1 && zoom < 15.5) {
//                    setWtMarker(i,type)
                setMarker(R.drawable.ic_hbjc_dcl_zs, i)
            }*/

            /*val rltBean = RltBean()
            rltBean.res = R.drawable.ic_hbjc_dcl_zs
            rltBean.type = type
            rltBean.zoom = zoom
            rltBean.xzqmc = i.name
            rltBean.center = i.location
            rltBean.count = i.counts
            RltUtils.setMarker(rltBean,markers,activity!!,aMap!!)*/
            /*if (zoom<13){
                mPresenter.getZjdRlt(AppCache.getInstance().code, 1, BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude))
            }else if (zoom<15.5){//15
                mPresenter.getZjdRlt(AppCache.getInstance().code, 1, BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude))
            }else{
                mPresenter.getZjdRlt(AppCache.getInstance().code, 1, BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude))
            }*/
        }
    }
    //出租点查院落
    override fun returnDcylxx(msg: YlFolwEntity) {

        if (msg.geometry!=null){
            kuangGeomentLine(msg.geometry)
        }
        if (msg!=null)
        when (mode) {

            MODE_CZ -> {//出租
                val tvDatas = ArrayList<TextViewsEntity>()
                if (msg != null) {
                    tvDatas.add(TextViewsEntity("户名:", msg.hzmc))

                    tvDatas.add(TextViewsEntity("门牌号:", msg.mph))
                    tvDatas.add(TextViewsEntity("已出租房:", msg.dwell.toString()))
                    tvDatas.add(TextViewsEntity("居住人数:", msg.rent.toString()))
                    tvDatas.add(TextViewsEntity("机动车:", msg.motorCars.toString()))
                    tvDatas.add(TextViewsEntity("电动车:", msg.electricCars.toString()))
                    tvDatas.add(TextViewsEntity("摩托车:", msg.motorcycle.toString()))
                }
                if (!msg.xzqmc.equals("")){//如果有村名显示弹出框
                    PopuPointUtils.startPopuPoint(supl_frag_zjdgl,tvDatas,"", arrayOf(0,1,2,3,4,5,6))
                }
            }
            MODE_LDRK -> {//流动人口
                if (!isYzt){
                    val intent = Intent(activity, LdrkDetailActivity::class.java)
                    intent.putExtra("ylId", msg.objectid)
                    startActivity(intent)
                }else{
                    if (msg.qrCodeFileEntity!=null&&!isYzt){
                        val intent = Intent(activity, LdrkDetailActivity::class.java)
                        intent.putExtra("ylId", msg.objectid)
                        startActivity(intent)
                    }else{
                        ToastUtils.showShort("此点位未关联")
                    }
                }
            }
        }

    }
    //巡查热力图
    override fun returnEnviorsByxy(fjNumBean: List<FwglJhBean>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal) {
        clearAllMarker()
        for (i in fjNumBean){
            val location = i.object3
            if (!location.equals("")&&mode == MODE_XC){
                val center1 = getCenter(location)
                setXcMarker(location, center1!!.latitude,center1.longitude,R.drawable.ic_hbjc_dcl_zs,i)

            }

            /*val zoom = aMap!!.cameraPosition.zoom
            if (zoom<9&&type.equals("1")){
                */
            /*val rltBean = RltBean()
                rltBean.res = R.drawable.ic_hbjc_dcl_zs
                rltBean.type = type.toInt()
                rltBean.xzqmc = i.object1
                rltBean.center = i.object3
                rltBean.count = i.object4
                RltUtils.setMarker(rltBean,markers,activity!!,aMap!!)*//*
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }else if (zoom<13&&type.equals("2")&&zoom>=9){
               */
            /* val rltBean = RltBean()
                rltBean.res = R.drawable.ic_hbjc_dcl_zs
                rltBean.xzqmc = i.object1
                rltBean.center = i.object3
                rltBean.count = i.object4
                RltUtils.setMarker(rltBean,markers,activity!!,aMap!!)*//*
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }else if (zoom<16&&type.equals("3")&&zoom>=13){
                */
            /*val rltBean = RltBean()
                rltBean.res = R.drawable.ic_hbjc_dcl_zs
                rltBean.xzqmc = i.object1
                rltBean.center = i.object3
                rltBean.count = i.object4
                RltUtils.setMarker(rltBean,markers,activity!!,aMap!!)*//*
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }else if (zoom>=16&&type.equals("4")&&zoom>=16){
                */
            /*val rltBean = RltBean()
                rltBean.res = R.drawable.ic_hbjc_dcl_zs
                rltBean.xzqmc = i.object1
                rltBean.center = i.object3
                rltBean.count = i.object4
                RltUtils.setMarker(rltBean,markers,activity!!,aMap!!)*//*
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }*/
        }
    }
    //人居环境热力图
    override fun returnEnviorsRjhj(message: List<EnviorFileFhEntity>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage: Int) {
        clearAllMarker()
//        Log.e("returnEnviorsRjhj",Gson().toJson(message))
        if (isImage == 1){
            for (i in message){
                /*val zoom = aMap!!.cameraPosition.zoom
                if (zoom<9&&type.equals("1")){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom<13&&type.equals("2")&&zoom>=9){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom>=15.5&&type.equals("4")&&zoom>=15.5){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }*/
                val location = i.location
                if (!location.equals("")){
                    val center1 = getCenter(location)
                    setRjhjMarker(location, center1!!.latitude,center1.longitude,R.drawable.ic_hbjc_dcl_zs,i)
                }
            }

        }else if (isImage==2){
            for (i in message){
                val zoom = aMap!!.cameraPosition.zoom
                var location : LatLng? =null
                /*if (zoom<9&&type.equals("1")){
                    setWtMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom<12&&type.equals("2")&&zoom>=9){
                    setWtMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom<15&&type.equals("3")&&zoom>=12){
                    setWtMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom>=15&&type.equals("4")&&zoom>=15){
                    setWtMarker(i,type,xmin,xmax,ymin,ymax)
                }*/
                if (AppCache.getInstance().code.length==9){
                    if (zoom<13&&type.equals("2")){
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<15.5&&type.equals("3")){//15
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<20&&type.equals("4")){//15
                        if (!i.location.equals("")){
                            location = getCenter(i.location)
                            if (i.monwarnin==0){
                                if (i.zdwt==1){
                                    setMarkerRjhj(i.location, location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_zs)
                                }else{
                                    setMarkerRjhj(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_ls)
                                }
                            }else{
                                setMarkerRjhj(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_hs)
                            }
                        }/*else{
                            location =
                        }*/
                    }
                }
                if (AppCache.getInstance().code.equals("")||AppCache.getInstance().code.equals("110112")){
                    if (zoom<9&&type.equals("1")){
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<13&&type.equals("2")){
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<15.5&&type.equals("3")){//15
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (type.equals("4")){
                        if (!i.location.equals("")){
                            location = getCenter(i.location)
                            if (i.monwarnin==0){
                                if (i.zdwt==1){
                                    setMarkerRjhj(i.location, location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_zs)
                                }else{
                                    setMarkerRjhj(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_ls)
                                }
                            }else{
                                setMarkerRjhj(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_hs)
                            }
                        }

                    }
                }
            }
        }
    }
    fun setMarkerRjhj( j: String, wd: Double, jd: Double, res: Int) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_marker, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position)
        ivItemMarker.setImageResource(res)
        itemMarkerPosition.text = j.toString() + ""
        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(context, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
                .position(LatLng(wd, jd))
                .snippet(j.toString() + "") //                .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->
            val type = AppCache.getInstance().type
            val snippet = marker.snippet
            if (isYzt){
//                shzlRjhjFrag.setWtdw1(snippet)
            }else{
//                shzlRjhjFrag.setWtdw(snippet)
            }
            true
        }
    }

    override fun returnHbjcListCode(ylPointEntity: List<PjEnviorSupvsEntity>, roleid: Int, totalCount: Int) {
        if (ylPointEntity.size==1){
            val intent = Intent(activity, HBJCDetailActivity::class.java)
            intent.putExtra("pjenvior", ylPointEntity!!.get(0))
            startActivity(intent)
        }else if (ylPointEntity.size>1){
            val arrayList = ArrayList<String>()
//            val arrayListOf = arrayOf()<
            for (i in ylPointEntity){
                arrayList.add(i.hjzzsjText)
            }
            val alertBuilder = AlertDialog.Builder(activity)

            alertBuilder.setTitle("此点位有多个问题")
            alertBuilder.setSingleChoiceItems(arrayList.toArray(arrayOfNulls(arrayList.size)), 0) { dialogInterface, i ->
                val intent = Intent(activity, HBJCDetailActivity::class.java)
                intent.putExtra("pjenvior", ylPointEntity!!.get(i))
                startActivity(intent)
                dialogInterface!!.dismiss()
            }


            alertBuilder.setNegativeButton("取消") { dialogInterface, i ->
                dialogInterface!!.dismiss()
            }
            alertBuilder.show()

        }
    }
    override fun returnApplyByPoint(fjBean: List<ApplyLandEntity>) {
//        Log.e("fjBean","${Gson().toJson(fjBean)}")
        if (zjd_click_mode==MODE_ZJD2&&activity!=null){
            if (activity!=null&&fjBean.size==1){
                if (fjBean.get(0).ylEntity!=null&&!fjBean.get(0).ylEntity.geometry.equals("")){
                    kuangGeomentLine(fjBean.get(0).ylEntity.geometry)
                }
                val intent = Intent(activity, YdlrActivity::class.java)
                intent.putExtra("applyEntity", fjBean as Serializable)
                startActivity(intent)
            }else{
                val arrayList = ArrayList<String>()
                for (i in fjBean){
                    arrayList.add(i.name)
                }
                val alertBuilder = AlertDialog.Builder(activity)
                alertBuilder.setTitle("此点位已分户")
                alertBuilder.setSingleChoiceItems(arrayList.toArray(arrayOfNulls(arrayList.size)), 0) { dialogInterface, i ->
                    var applyLandEntitys= ArrayList<ApplyLandEntity>()
                    applyLandEntitys.add(fjBean.get(i))
                    val intent = Intent(activity, YdlrActivity::class.java)
                    intent.putExtra("applyEntity", applyLandEntitys as Serializable)
                    startActivity(intent)
                    dialogInterface!!.dismiss()
                }
                alertBuilder.setNegativeButton("取消") { dialogInterface, i ->
                    dialogInterface!!.dismiss()
                }
                alertBuilder.show()
            }
        }else if (zjd_click_mode==MODE_ZJD1&&activity!=null){
            if (fjBean!!.get(0).sptype==0){
                if (fjBean.get(0).ylEntity!=null&&!fjBean.get(0).ylEntity.geometry.equals("")){
                    kuangGeomentLine(fjBean.get(0).ylEntity.geometry)
                    PopuPointUtils.startPopuZjdPoint(supl_frag_zjdgl,fjBean.get(0).ylEntity,fjBean.get(0).zhaiEntities)
                }
            }else{
                if (activity!=null&&fjBean.size==1){
                    if (fjBean.get(0).ylEntity!=null&&!fjBean.get(0).ylEntity.geometry.equals("")){
                        kuangGeomentLine(fjBean.get(0).ylEntity.geometry)
                    }

                    val intent = Intent(activity, YdlrActivity::class.java)
                    intent.putExtra("applyEntity", fjBean as Serializable)
                    startActivity(intent)
                }else{
                    val arrayList = ArrayList<String>()
                    for (i in fjBean){
                        arrayList.add(i.name)
                    }
                    val alertBuilder = AlertDialog.Builder(activity)
                    alertBuilder.setTitle("此点位已分户")
                    alertBuilder.setSingleChoiceItems(arrayList.toArray(arrayOfNulls(arrayList.size)), 0) { dialogInterface, i ->
                        var applyLandEntitys= ArrayList<ApplyLandEntity>()
                        applyLandEntitys.add(fjBean.get(i))
                        val intent = Intent(activity, YdlrActivity::class.java)
                        intent.putExtra("applyEntity", applyLandEntitys as Serializable)
                        startActivity(intent)
                        dialogInterface!!.dismiss()
                    }
                    alertBuilder.setNegativeButton("取消") { dialogInterface, i ->
                        dialogInterface!!.dismiss()
                    }
                    alertBuilder.show()
                }
            }
        }
    }
    override fun returnYztByPoint(yztPointEntity: YztPointEntity) {//一张图点查
        if (mode == MODE_YL){// List<YLEntity> ylList;//院落
            aMap!!.clear()

            var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
            var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
            var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
            var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
            aMap?.addTileOverlay(opt_dk);
            addOverLayer(mLayers)
            addOverLayer(mLayers1)
//            addOverLayer(mLayers1)
            if (yztPointEntity!=null){
                if (yztPointEntity.yzt.size>0){
                    for (i in yztPointEntity.yzt) {
                        kuangGeomentLine(i.geometry)
                    }
                }
                if (yztPointEntity.tdly.size>0){
                    for (i in yztPointEntity.tdly) {
                        kuangGeomentLine(i.geometry)
                    }
                }
                /*if (yztPointEntity.yztfz!=null){
                    kuangGeomentLine(yztPointEntity.yztfz.geometry)
                }
                if (yztPointEntity.yztCt!=null&&yztPointEntity.yztCt.size>0){
                    kuangGeomentLine(yztPointEntity.yztCt.get(0).geometry)
                }
                if (yztPointEntity.yztSy.size>0){
                    for (i in yztPointEntity.yztSy){
                        kuangGeomentLine(i.geometry)
                    }
                }
                if (yztPointEntity.yztLh!=null&&yztPointEntity.yztLh.size>0){
                    kuangGeomentLine(yztPointEntity.yztLh.get(0).geometry)
                }
                if (yztPointEntity.yztcctt!=null){
                    kuangGeomentLine(yztPointEntity.yztcctt.geometry)
                }*/
                /*if (yztPointEntity.yl!=null){
                    val dataGeometry = yztPointEntity.yl.geometry
                    kuangGeomentLine(dataGeometry)
                }*/

            }
            if (yztPointEntity.ylList.size>0||yztPointEntity.yzt.size>0||yztPointEntity.enterprise!=null){
//                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(235f)
//                zjdYztDcFrag.setYztYlDc(yztPointEntity)
                if (yztPointEntity.ylList.size>0){
                    if (yztPointEntity.ylList.get(0).fl2.equals("宅基地")){
                        val dataGeometry = yztPointEntity.ylList.get(0).geometry
                        kuangGeomentLine(dataGeometry)
                    }
                }

                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                zjdYztDcFrag.setYztYlDc(yztPointEntity)
                zjdYztDcFrag.setData(yztPointEntity)
            }else{
//                ToastUtils.showShort("此处无院落")
            }
        }else if (mode == MODE_TDLY){//土地利用  tdLYEntity
            if (yztPointEntity.tdLYEntity.size>0){
                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                for (i in yztPointEntity.tdLYEntity) {
                    kuangGeomentLine(i.geometry)
                }
            }else{
                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
            zjdYztDcFrag.txData(yztPointEntity)

        }else if (mode == MODE_CYYQ){//产业园区
            val enterprise = yztPointEntity.enterprise
            if (enterprise!=null){
                val dataGeometry = enterprise.geometry
                kuangGeomentLine(dataGeometry)
//                PopuPointUtils.startPopuQyxxPoint(supl_frag_zjdgl,cash)

                val intent = Intent(activity, QyglDetailActivity::class.java)//QyglDetailView
                intent.putExtra("enterpriseBasisEntity", enterprise)
                startActivity(intent)
            }else{
                ToastUtils.showShort("暂无数据！")
            }
            /*if (yztPointEntity.ylList.size>0){

                val dataGeometry = yztPointEntity.ylList.get(0).geometry
                kuangGeomentLine(dataGeometry)
            }
            if (yztPointEntity.feiZhaiList!=null&&yztPointEntity.feiZhaiList.size>0){
                PopuPointUtils.startPopuQyxxPoint(supl_frag_zjdgl,yztPointEntity)
            }else{
                ToastUtils.showShort("暂无数据！")
            }*/

        }
    }
    //框查
    override fun onYewuFrame(entity: YeWuFrameBean.DataBean) {
        if (mode == MODE_YL){
//            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
//            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        }
    }

    //产业园区点查
    override fun returnQyglPoint(cash: EnterpriseBasisEntity) {
            if (cash!=null){
                val dataGeometry = cash.geometry
                kuangGeomentLine(dataGeometry)
//                PopuPointUtils.startPopuQyxxPoint(supl_frag_zjdgl,cash)

                val intent = Intent(activity, QyglDetailActivity::class.java)//QyglDetailView
                intent.putExtra("enterpriseBasisEntity", cash)
                startActivity(intent)
            }else{
                ToastUtils.showShort("暂无数据！")
            }
    }

    //框查
    override fun onFrameJcxx(isSave: FrameJcxxBean.DataBean) {
        if (mode == MODE_YL){
            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            zjdYztDcFrag.setYztYlKc(isSave)

        }
    }
    override fun onPointTx(result: TdlyVO) {
        if (result!=null){
//            Log.e("onPointTx",Gson().toJson(result))
            val tvDatas = ArrayList<TextViewsEntity>()
            if (result != null) {
                if (!result.xzqmc.equals(""))
                tvDatas.add(TextViewsEntity("村名:", result.xzqmc))
                if (!result.dlmc.equals(""))
                tvDatas.add(TextViewsEntity("地类名称:", result.elx+"(${result.dlmc})"))
                /*if (!result.dlbm.equals(""))
                tvDatas.add(TextViewsEntity("地类编码:", result.dlbm))*/
                /*if (!result.qsdwmc.equals(""))
                tvDatas.add(TextViewsEntity("权属名称:", result.qsdwmc))
                if (!result.qsxz.equals(""))
                tvDatas.add(TextViewsEntity("权属性质:", result.qs))*/
                if (result.area.compareTo(BigDecimal(10000))> 0){
                    tvDatas.add(TextViewsEntity("面积:", result.area.divide(BigDecimal(10000),2,RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                }else{
                    tvDatas.add(TextViewsEntity("面积:", result.area.toInt().toString()+"㎡"))//(公顷).setScale(2,RoundingMode.HALF_UP)
                }
//            tvDatas.add(TextViewsEntity("权属单位名称:", result.qsdwmc))
            }
            if (!result.dlmc.equals("")){//如果有村名显示弹出框
                kuangGeomentLine(result.geometry)
                if (mode == MODE_TDQS){//土地权属
                    PopuPointUtils.startPopuPoint(supl_frag_zjdgl,tvDatas,"", arrayOf(0,1,2,3,4))
                }else{
                    PopuPointUtils.startPopuPoint(supl_frag_zjdgl,tvDatas,result.elx, arrayOf(0,1,2,3,4))
                }
            }
        }
    }
    override fun onPointTxSkt(tdlyEntity: YztSktVO) {
        if (tdlyEntity!=null){
//            Log.e("onPointTxSkt",Gson().toJson(tdlyEntity))
            val tvDatas = ArrayList<TextViewsEntity>()
            if (tdlyEntity != null) {
                if (!tdlyEntity.xzqmc.equals(""))
                tvDatas.add(TextViewsEntity("村名:", tdlyEntity.xzqmc))
                if (!tdlyEntity.skt.equals(""))
                tvDatas.add(TextViewsEntity("名称:", tdlyEntity.skt))//三块田
                if (tdlyEntity.area.compareTo(BigDecimal(10000))> 0){
                    tvDatas.add(TextViewsEntity("面积:", tdlyEntity.area.divide(BigDecimal(10000),2,RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                }else{
                    tvDatas.add(TextViewsEntity("面积:", tdlyEntity.area.toInt().toString()+"㎡"))//(公顷).setScale(2,RoundingMode.HALF_UP)
                }
               /* tvDatas.add(TextViewsEntity("地类编码:", tdlyEntity.dlbm))
                tvDatas.add(TextViewsEntity("地类名称:", tdlyEntity.dlmc))
                tvDatas.add(TextViewsEntity("一级类:", tdlyEntity.yjfl))
                tvDatas.add(TextViewsEntity("二级类:", tdlyEntity.ejfl))*/
//            tvDatas.add(TextViewsEntity("权属单位名称:", result.qsdwmc))
            }
            if (!tdlyEntity.skt.equals("")){//如果有村名显示弹出框
                kuangGeomentLine(tdlyEntity.geometry)
                PopuPointUtils.startPopuPoint(supl_frag_zjdgl,tvDatas,tdlyEntity.skt, arrayOf(0,1,2,3,4))//,5,6
            }
        }
    }

    override fun onPointQs(tdlyEntity: QsVO) {
        if (tdlyEntity!=null){
            val tvDatas = ArrayList<TextViewsEntity>()
            if (tdlyEntity != null) {
                if (!tdlyEntity.xzqmc.equals(""))
                tvDatas.add(TextViewsEntity("村名:", tdlyEntity.xzqmc))
                if (!tdlyEntity.qs.equals(""))
                tvDatas.add(TextViewsEntity("权属:", tdlyEntity.qs.toString()))//(公顷)
                if (!tdlyEntity.qsdwmc.equals(""))
                tvDatas.add(TextViewsEntity("权属单位名称:", tdlyEntity.qsdwmc))
                if (tdlyEntity.area.compareTo(BigDecimal(10000))> 0){
                    tvDatas.add(TextViewsEntity("面积:", tdlyEntity.area.divide(BigDecimal(10000),2,RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                }else{
                    tvDatas.add(TextViewsEntity("面积:", tdlyEntity.area.setScale(2,RoundingMode.HALF_UP).toString()+"㎡"))//(公顷)
                }
//                tvDatas.add(TextViewsEntity("地类编码:", tdlyEntity.dlbm))
//                tvDatas.add(TextViewsEntity("地类名称:", tdlyEntity.dlmc))
//                tvDatas.add(TextViewsEntity("种植属性名称:", tdlyEntity.zzsxmc))
                //zzsxmc
            }
            if (!tdlyEntity.qs.equals("")){//如果有村名显示弹出框
                kuangGeomentLine(tdlyEntity.geometry)
                PopuPointUtils.startPopuPoint(supl_frag_zjdgl,tvDatas,"", arrayOf(0,1,2,3,4,5,6))//,5,6
            }
        }
    }

    override fun onPointKjgh(tdlyEntity: GhVO) {
        if (tdlyEntity!=null){
            val tvDatas = ArrayList<TextViewsEntity>()
            if (tdlyEntity != null) {
                if (!tdlyEntity.xzqmc.equals(""))
                tvDatas.add(TextViewsEntity("村名:", tdlyEntity.xzqmc))
//                tvDatas.add(TextViewsEntity("分区代码:", tdlyEntity.fqdm))
                if (!tdlyEntity.ghmc.equals(""))
                tvDatas.add(TextViewsEntity("规划名称:", tdlyEntity.ghmc))
                if (tdlyEntity.ydmj.compareTo(BigDecimal(10000))> 0){
                    tvDatas.add(TextViewsEntity("用地面积:", tdlyEntity.ydmj.divide(BigDecimal(10000),2,RoundingMode.HALF_UP).toString()+"公顷"))//(公顷)
                }else{
                    tvDatas.add(TextViewsEntity("用地面积:", tdlyEntity.ydmj.toInt().toString()+"㎡"))//(公顷).setScale(2,RoundingMode.HALF_UP)
                }
//                tvDatas.add(TextViewsEntity("规划期限:", tdlyEntity.ghqx))
            }
            if (!tdlyEntity.ghmc.equals("")){//如果有村名显示弹出框
                kuangGeomentLine(tdlyEntity.geometry)
//                ToastUtils.showShort(""+tdlyEntity.geometry)
                PopuPointUtils.startPopuPoint(supl_frag_zjdgl,tvDatas,tdlyEntity.ghmc, arrayOf(0,1,2,3,4))//,5,6
            }
        }
    }

    override fun returnFanJianAdd(message: String) {
        zjdXcFrag.getData()//刷新列表
        if (envirorUpPopu!=null){
            aMap?.clear()
            tabTc("999",2)
//            addOverLayer(mLayers)//mlayers
            ToastUtils.showShort(message)
            envirorUpPopu?.dismiss()
        }
    }

    override fun returnFanJianDelete(message: String) {
        zjdXcFrag.getData()//刷新列表
        if (envirorUpPopu!=null){
            aMap?.clear()
            tabTc("999",2)
            ToastUtils.showShort(message)
            envirorUpPopu?.dismiss()
        }
    }
    var ylPointEntity: WtlrBean? = null
    override fun returnRjhjjcPoint(ylPointEntity: WtlrBean) {
        this.ylPointEntity = ylPointEntity
        if (isShow==1&&ylPointEntity.pointRecordEntity!=null){
            if (ylPointEntity.pointRecordEntity.name == 6||ylPointEntity.pointRecordEntity.name == 7){
                typeLx = 3
            }else{
                typeLx = 1
            }
            initPopuEnvironUp(PjEnviorSupvsEntity(), 1,ylPointEntity)
            CommenPop.backgroundAlpha(0.5f, activity)
            envirorUpPopu!!.showAtLocation(supl_frag_zjdgl, Gravity.CENTER, 0, 0)
            mPresenter.getEnviorSupvsQueryPoint(pointStr,1)
            isShow = 0
        }else
            if (isShow==2){
                typeLx = 3
                ylPointEntity.pointRecordEntity = null
                initPopuEnvironUp(PjEnviorSupvsEntity(), 1,ylPointEntity)
                CommenPop.backgroundAlpha(0.5f, activity)
                envirorUpPopu!!.showAtLocation(supl_frag_zjdgl, Gravity.CENTER, 0, 0)
                mPresenter.getEnviorSupvsQueryPoint(pointStr,2)
                isShow = 0
            }else
                if (isShow==3){
                    initPopuDingDian(PointRecordEntity())
                    CommenPop.backgroundAlpha(0.5f, activity)
                    dingDianUpPopu!!.showAtLocation(supl_frag_zjdgl, Gravity.CENTER, 0, 0)
                    isShow = 0
                }else
                    if (isShow==4){
                        if (ylPointEntity.pointRecordEntity!=null){
                            initPopuDingDian(ylPointEntity.pointRecordEntity)
                            CommenPop.backgroundAlpha(0.5f, activity)
                            dingDianUpPopu!!.showAtLocation(supl_frag_zjdgl, Gravity.CENTER, 0, 0)
                        }else{
                            ToastUtils.showShort("选中位置没有固定点位")
                        }

                        isShow = 0
                    }else{
                        ToastUtils.showShort("未选中")
                    }
    }
    override fun onHbLr(ylPointEntity: String) {
        bt_temporary_environmental!!.isEnabled = true
        tvUploadMeetingSummary!!.isEnabled = true

        ToastUtils.showShort("添加成功")
        shzlRjhjFrag.shzlRjhjTab()//人居环境列表刷新
        if (envirorUpPopu != null) {
            envirorUpPopu!!.dismiss()
        }
    }

    override fun onDdLr(enviorSupvsEntity: String) {
        ToastUtils.showShort(enviorSupvsEntity)
//        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
        shzlRjhjFrag.shzlGddwTab()//固定点位列表刷新
        if (dingDianUpPopu!=null){
            dingDianUpPopu!!.dismiss()
        }
        aMap!!.clear()
        tabTc("999",2)
    }

    override fun onDdDetel(message: String) {
    }

    override fun returnQueryCun(message: List<CjEntity>, pjContract: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText) {
        if (rlv_xzxzq!=null){
            if (message.size==1){
                rlv_xzxzq.visibility = View.GONE
                pjContract.wz = message.get(0).xzqmc
                pjContract.xzqmc = message.get(0).xzqmc
                pjContract.code = message.get(0).code
                cunMing = message.get(0).xzqmc
                tvWzenvironmental.setText("")
                tvWzenvironmental.hint = message.get(0).zhen+" "+message.get(0).xzqmc
            }else if (message.size>0){
                val data = ylPointEntity!!.getCjEntity()
                for ( i in message){
                    if (i.code.equals(data.code)){
                        pjContract.wz = i.xzqmc
                        pjContract.xzqmc = i.xzqmc
                        pjContract.code = i.code
                        cunMing = i.xzqmc
                        tvWzenvironmental.setText("")
                        tvWzenvironmental.hint = i.zhen+" "+i.xzqmc
                    }
                }
                rlv_xzxzq.visibility = View.VISIBLE
                rlv_xzxzq.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                rlv_xzxzq.adapter = object:BaseQuickAdapter<CjEntity,BaseViewHolder>(R.layout.item_xuan_xzqmc,message){
                    override fun convert(helper: BaseViewHolder?, item: CjEntity?) {
                        helper!!.setText(R.id.tv_xuan_xzq, item!!.zhen+" "+item!!.xzqmc)
                        helper.itemView.setOnClickListener {
                            tvWzenvironmental.setText("")
                            tvWzenvironmental.hint = item!!.zhen+" "+item!!.xzqmc
                            pjContract.wz = item.xzqmc
                            pjContract.xzqmc = item.xzqmc
                            pjContract.code = item.code
                            rlv_xzxzq.visibility = View.GONE
                            cunMing = item.xzqmc
                        }
                    }
                }

            } else{
                rlv_xzxzq.visibility = View.GONE
                ToastUtils.showShort("无此村名")
            }
        }
    }

    override fun returnEnviorSupvsQueryPoint(ylPointEntity: WtlrBean) {
        if (ylPointEntity!=null){
            val pjEnviorSupvsEntities = ylPointEntity.getPjEnviorSupvsEntities()
            if (pjEnviorSupvsEntities.size>0){
                if (ll_envionmental_wtlist!=null||pop_gctype == 1)
                    ll_envionmental_wtlist!!.visibility =View.VISIBLE
                if (rlv_envionmental_wtlist!=null){
                    rlv_envionmental_wtlist!!.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)//tv_rjhj_wtlist
                    rlv_envionmental_wtlist!!.adapter = object: BaseQuickAdapter<PjEnviorSupvsEntity, BaseViewHolder>(R.layout.item_rjhj_wtlist,pjEnviorSupvsEntities){
                        override fun convert(helper: BaseViewHolder?, item: PjEnviorSupvsEntity?) {
                            helper!!.setText(R.id.tv_rjhj_wtlist, item!!.hjzzsjText)
                            helper.itemView.setOnClickListener {
                                if (SingleOnClickUtil.isFastClick()){//跳转问题详情
                                    val intent = Intent(activity, HBJCDetailActivity::class.java)
                                    intent.putExtra("pjenvior", item)
                                    startActivity(intent)
                                }

                            }
                        }

                    }

                }
                if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR){
                    if (mode == MODE_RJHJ_RJHJ&&ifxstp==1){
                        val arrayList = ArrayList<String>()
                        val arrayListWt = ArrayList<String>()
                        for (i in pjEnviorSupvsEntities){
                            for (s in i.pjEnviorFileEntities){
                                val path = s.path
                                val replace = path.replace("\\", "/")
                                val imagePath = ApiConstants.RELEASE_URL + replace
                                arrayList.add(imagePath)
                                arrayListWt.add(i.hjzzsjText)
                            }

                        }
                        val center1 = getCenter(pjEnviorSupvsEntities.get(0).location)
                        setImagesMarker(arrayList,arrayListWt,center1)
                        ifxstp = 0
                    }
                }

            }else{
                if (ll_envionmental_wtlist!=null&&pop_gctype == 0){
                    ll_envionmental_wtlist!!.visibility =View.GONE
                }else{
                    if (ll_envionmental_wtlist!=null){
                        ll_envionmental_wtlist!!.visibility =View.VISIBLE
                    }
                }
            }
        }
    }

    fun setImagesMarker(image:ArrayList<String>,imageWt:ArrayList<String>,latLng: LatLng){//显示附近所有图片集合
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_image_marker, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_image_marker)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_image_marker_position)
        itemMarkerPosition.text = image.size.toString()
        if (image.size>0){
            //绘制marker
            Glide.with(this).load(image.get(0))
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            //展示图片
                            ivItemMarker.setImageDrawable(resource)
//                        textView.setText(list.get(I).getGasName() + "");
                            var bitmap = convertViewToBitmap(view1)
                            aMap!!.addMarker(MarkerOptions()
                                    .position(latLng)
                                    .snippet("") //                .period(mid)//添加markerID
                                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .draggable(true))

                        }
                        /*@Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //展示图片
                            imageView.setImageDrawable(resource);
                            textView.setText(list.get(I).getGasName() + "");
                            Bitmap bitmap = convertViewToBitmap(view);
                            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .position..;
                            aMap.addMarker(markerOption);
                        }*/
                    })
            aMap!!.setOnMarkerClickListener { marker ->
                val snippet = marker.snippet
                val intent = Intent(activity, ImageListActivity::class.java)
                intent.putExtra("snippet","问题")
                intent.putExtra("sjfl", "")
                intent.putExtra("image", image)
                intent.putExtra("imageWt", imageWt)
                startActivity(intent)
                /*val snippet = marker.snippet
                val intent = Intent(activity, ImageListActivity::class.java)
                intent.putExtra("snippet",snippet)
                startActivity(intent)*/
                true
            }
        }


        /*val path = enviorSupvsEntity.path
        val replace = path.replace("\\", "/")
        val imagePath = ApiConstants.BASE_URL + replace
        itemMarkerPosition.text = enviorSupvsEntity.counts.toString()
        val location = enviorSupvsEntity.location
        if (!location.equals("")){
            val center1 = getCenter(location)
            //绘制marker
            GlideApp.with(this).load(imagePath).//
            centerCrop().override(100, 100).placeholder(R.drawable.loading)
                    .error(R.drawable.error_center_x)
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            //展示图片
                            ivItemMarker.setImageDrawable(resource);
//                        textView.setText(list.get(I).getGasName() + "");
                            var bitmap = convertViewToBitmap(view1);
                            val zoom = aMap!!.cameraPosition.zoom
                            if (zoom < 9 && type.equals("1")) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom < 12 && type.equals("2") && zoom >= 9) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom < 15 && type.equals("3") && zoom >= 12) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom >= 15 && type.equals("4") && zoom >= 15) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            }


                        }

                        */
        /*@Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //展示图片
                            imageView.setImageDrawable(resource);
                            textView.setText(list.get(I).getGasName() + "");
                            Bitmap bitmap = convertViewToBitmap(view);
                            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .position..;
                            aMap.addMarker(markerOption);
                        }*//*
                    });
        }*/

    }
    var rlv_envionmental_wtlist: RecyclerView? = null
    var ll_envionmental_wtlist: LinearLayout? = null
    var pop_gctype = 0 //判断弹框展示隐藏按钮状态
    private var tvUpDateEnviron: TextView? = null
    private var tvUploadMeetingSummary: Button? = null
    private var bt_temporary_environmental: Button? = null
    private var cunMing = "" //村名  用于控制图片上的水印
    private var photoAdapter: PhotoAdapter? = null//图片九宫格
    private var schxtp:BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null//
    private var videoAdapter: VideoAdapter? = null
    private var uploadDate: String = ""
    private var isls=0//判断是否是临时添加问题 0 是临时   1 是可下发状态

    private fun initPopuEnvironUp(pjContract: PjEnviorSupvsEntity?, state: Int,xzqmc:WtlrBean) {//RjhjPointBean
        pjContract!!.zjwj = 0//设置直接完结字段为空
        selectedPhotos.clear()
        selectedVideos.clear()
        PjEnviorFileList.clear()
        videoIdList.clear()
        fileIdList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_environmental_up2, supl_frag_zjdgl)
        val contentView2: View = envirorUpPopu!!.getContentView()
//        val contentView2 = LayoutInflater.from(this.getParent()).inflate(R.layout.product_package, null);
        tvUpDateEnviron = contentView2.findViewById(R.id.tv_up_date_environmental2)
        //        EditText tvBhEnvironmental = contentView2.findViewById(R.id.tv_bh_environmental);
        val tv_environ_up_bt = contentView2.findViewById<TextView>(R.id.tv_environ_up_bt2)
        val tvNameEnvironmental = contentView2.findViewById<EditText>(R.id.tv_name_environmental2)
        //        EditText tvNameSection = contentView2.findViewById(R.id.tv_name_section);
        val tvJlrPhone = contentView2.findViewById<EditText>(R.id.tv_jlr_phone2)
        val tvLrwtEnvironmental = contentView2.findViewById<EditText>(R.id.tv_lrwt_environmental2)//问题描述
        val tvZrdwEnvironmental = contentView2.findViewById<EditText>(R.id.tv_zrdw_environmental2)
        val tvWtztEnvironmental = contentView2.findViewById<Spinner>(R.id.tv_wtzt_environmental2)
        val tvJlrEnvironmental = contentView2.findViewById<EditText>(R.id.tv_jlr_environmental2)
        val tvRemarkEnvironmental = contentView2.findViewById<EditText>(R.id.tv_remark_environmental2)
        val butClearMeetingSummary = contentView2.findViewById<Button>(R.id.bt_clear_environmental2)
        tvUploadMeetingSummary = contentView2.findViewById<Button>(R.id.bt_upload_environmental2)
        val llWtztEnvironmental = contentView2.findViewById<LinearLayout>(R.id.ll_wtzt_environmental2)
        val tvWzenvironmental = contentView2.findViewById<EditText>(R.id.tv_wz_environmental2)
        val tv_xfzgyq_environmental = contentView2.findViewById<EditText>(R.id.tv_xfzgyq_environmental)//整改要求
        val tv_clshijian_environmental = contentView2.findViewById<EditText>(R.id.tv_clshijian_environmental)//处理时间
        val tv_xfclsj_environmental = contentView2.findViewById<EditText>(R.id.tv_xfclsj_environmental)//处理期限
        val sp_clqx_environmental = contentView2.findViewById<Spinner>(R.id.sp_clqx_environmental)
        val tv_xfjy_environmental = contentView2.findViewById<EditText>(R.id.tv_xfjy_environmental)
        val rlvPopVideoAdd = contentView2.findViewById<RecyclerView>(R.id.rlv_pop_video_add2)
        val rlvPopEnvironmental: RecyclerView = contentView2.findViewById(R.id.recy_pop_environmental2)
        val recy_pop_environmental2_scxs: RecyclerView = contentView2.findViewById(R.id.recy_pop_environmental2_scxs)//上传图片回显
        val rlvPopVideoadd: RecyclerView = contentView2.findViewById(R.id.rlv_pop_video_add2)
        val rlv_xzxzq: RecyclerView = contentView2.findViewById(R.id.rlv_xzxzq2)//选择行政区

        val sp_cncw_environmental = contentView2.findViewById<Spinner>(R.id.sp_cncw_environmental2)//村内村外
        val sp_sfgddw_environmental = contentView2.findViewById<Spinner>(R.id.sp_sfgddw_environmental2)//是否固定点位
        val sp_sfzdwt_environmental = contentView2.findViewById<Spinner>(R.id.sp_sfzdwt_environmental2)//突出问题
        val sp_jcssgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_jcssgh_environmental2)//基础设施管护
        val sp_tynysyxwh_environmental = contentView2.findViewById<Spinner>(R.id.sp_tynysyxwh_environmental2)//太阳能浴室运行维护
        val sp_hcqtgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_hcqtgh_environmental2)//户厕清掏管护
        val sp_lhyh_environmental = contentView2.findViewById<Spinner>(R.id.sp_lhyh_environmental2)//绿化养护
        val sp_jnlduyxwh_environmental = contentView2.findViewById<Spinner>(R.id.sp_jnlduyxwh_environmental2)//节能路灯运行维护
        val sp_wttj_environmental = contentView2.findViewById<Spinner>(R.id.sp_wttj_environmental2)//问题统计
        val sp_shljzl_environmental = contentView2.findViewById<Spinner>(R.id.sp_shljzl_environmental2)//生活垃圾治理
        val sp_shwszl_environmental = contentView2.findViewById<Spinner>(R.id.sp_shwszl_environmental2)//生活污水治理
        val sp_gcgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_gcgh_environmental2)//公厕管护
        val sp_sdlj_environmental = contentView2.findViewById<Spinner>(R.id.sp_sdlj_environmental2)//私搭乱建
        val sp_hjzz_environmental = contentView2.findViewById<Spinner>(R.id.sp_hjzz_environmental2)//环境整治(一级分类)
//        val sp_ejfl_environmental = contentView2.findViewById<Spinner>(R.id.sp_ejfl_environmental2)//二级分类
        val sp_sjfl_environmental = contentView2.findViewById<Spinner>(R.id.sp_sjfl_environmental2)//三级分类
        val tv_ejfl_environmental = contentView2.findViewById<TextView>(R.id.tv_ejfl_environmental2)
//        val tv_sjfl_environmental = contentView2.findViewById<TextView>(R.id.tv_sjfl_environmental)
        val ll_sjfl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_sjfl_environmental2)
        val ll_ejfl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_ejfl_environmental2)

        val ll_gddw_envuronmental = contentView2.findViewById<LinearLayout>(R.id.ll_gddw_envuronmental2)
        val ll_fgddw_envuronmental = contentView2.findViewById<LinearLayout>(R.id.ll_fgddw_envuronmental2)
        val ll_tynysyxwh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_tynysyxwh_environmental2)
        val ll_hcqtgh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_hcqtgh_environmental2)
        val ll_lhyh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_lhyh_environmental2)
        val ll_jnldyxwh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_jnldyxwh_environmental2)
        val ll_shljzl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_shljzl_environmental2)
        val ll_shwszl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_shwszl_environmental2)
        val ll_gcgh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_gcgh_environmental2)
        val ll_sdlj_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_sdlj_environmental2)

        val ll_xfzgyq_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_xfzgyq_environmental)//整改问题
        val ll_clshijian_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_clshijian_environmental)//整改问题
        val ll_clsj_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_clsj_environmental)//整改问题
        val ll_xfjy_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_xfjy_environmental)//整改问题
        bt_temporary_environmental = contentView2.findViewById<Button>(R.id.bt_temporary_environmental2)
        val et_wtlx_gddw = contentView2.findViewById<TextView>(R.id.et_wtlx_gddw)
        ll_envionmental_wtlist = contentView2.findViewById<LinearLayout>(R.id.ll_envionmental_wtlist)//显示此点位存在的问题列表
        rlv_envionmental_wtlist = contentView2.findViewById<RecyclerView>(R.id.rlv_envionmental_wtlist)//显示此点位存在的问题列表
        val rlt_envionmental_wtlist = contentView2.findViewById<LinearLayout>(R.id.rlt_envionmental_wtlist)//显示此点位存在的问题列表
        val rlv_envionmental_wttv = contentView2.findViewById<TextView>(R.id.rlv_envionmental_wttv)//显示此点位存在的问题列表
        val ll_envionmental_gcbh = contentView2.findViewById<LinearLayout>(R.id.ll_envionmental_gcbh)//公厕编号
        val tv_envionmental_gcbh = contentView2.findViewById<EditText>(R.id.tv_envionmental_gcbh)//公厕编号
        val ll_envionmental_gcimage = contentView2.findViewById<LinearLayout>(R.id.ll_envionmental_gcimage)//公厕图片
        val image_envionmental_gcimage_mp = contentView2.findViewById<ImageView>(R.id.image_envionmental_gcimage_mp)//公厕图片
        val image_envionmental_gcimage_wg = contentView2.findViewById<ImageView>(R.id.image_envionmental_gcimage_wg)//公厕图片
        val recy_envionmental_gcimage = contentView2.findViewById<RecyclerView>(R.id.recy_envionmental_gcimage)//公厕图片

        rlv_envionmental_wttv.setOnClickListener {
            if (rlv_envionmental_wttv.text.toString().equals("收起")){
                rlt_envionmental_wtlist.visibility = View.GONE
                rlv_envionmental_wttv.text = "展开"
            }else{
                rlt_envionmental_wtlist.visibility = View.VISIBLE
                rlv_envionmental_wttv.text = "收起"
            }

        }
        if (typeLx==1){
            ll_xfzgyq_environmental.visibility = View.GONE
            ll_clshijian_environmental.visibility = View.GONE
            ll_clsj_environmental.visibility = View.GONE
            ll_xfjy_environmental.visibility = View.GONE
        }else{
            ll_xfzgyq_environmental.visibility = View.VISIBLE
            ll_clshijian_environmental.visibility = View.VISIBLE
            ll_clsj_environmental.visibility = View.VISIBLE
            ll_xfjy_environmental.visibility = View.VISIBLE
        }



//        tv_xfzgyq_environmental.setText(pjContract.zgyq)//整改要求
//        tv_xfjy_environmental.setText(pjContract.xfjy)//下发建议


        if (xzqmc.pointRecordEntity!=null){
            pjContract.wz = xzqmc.pointRecordEntity.xzqmc
            pjContract.xzqmc = xzqmc.pointRecordEntity.xzqmc
            pjContract.code = xzqmc.pointRecordEntity.code
            cunMing = xzqmc.pointRecordEntity.xzqmc
            tvWzenvironmental.setText(xzqmc.pointRecordEntity.xzqmc)
            tvWzenvironmental.isEnabled = false
            if (xzqmc.pointRecordEntity.name == 3){//公厕判断
                if(!xzqmc.pointRecordEntity.bh.equals("")){
//                    pop_gctype = 1
//                    ll_envionmental_wtlist!!.visibility = View.VISIBLE
//                    ll_envionmental_gcbh.visibility = View.VISIBLE
                    tv_envionmental_gcbh.setText(xzqmc.pointRecordEntity.bh)
                }else{
//                    pop_gctype = 0
                }
                if (xzqmc.pointRecordEntity.files.size>0){
                    pop_gctype = 1
//                    ll_envionmental_wtlist!!.visibility = View.VISIBLE
                    ll_envionmental_gcimage.visibility = View.VISIBLE
                    /*for (i in 0..xzqmc.point.files.size-1){
                        if (xzqmc.point.files.get(i).filetype.toInt() == 1){
                            Glide.with(activity!!).load(xzqmc.point.files.get(i).url).into(image_envionmental_gcimage_mp!!)
                        }
                        if (xzqmc.point.files.get(i).filetype.toInt() == 2){
                            Glide.with(activity!!).load(xzqmc.point.files.get(i).url).into(image_envionmental_gcimage_wg!!)
                        }
                    }*/

                    recy_envionmental_gcimage!!.layoutManager = GridLayoutManager(activity,2)
                    var gddwaAdapter = object : BaseQuickAdapter<RenovatedFile, BaseViewHolder>(R.layout.item_teng_tui_photo, xzqmc.pointRecordEntity.files) {
                        override fun convert(helper: BaseViewHolder, item: RenovatedFile) {
                            val iv_teng_photo = helper.getView<ImageView>(R.id.iv_teng_photo)

                            val pic: String = item.url
                            val s1 = pic.replace("\\", "/")
                            Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                                    .into(iv_teng_photo)
                            if (item.filetype == 1){
                                helper.setText(R.id.tv_teng_photo_name,"公厕名牌")
                            }else{
                                helper.setText(R.id.tv_teng_photo_name,"公厕外观")
                            }
                            helper.itemView.setOnClickListener {
                                var pathList = ArrayList<String>()
                                for (i in xzqmc.pointRecordEntity.files){
                                    val pic = i.url
                                    val s1 = pic.replace("\\", "/")
                                    pathList.add(s1)
                                }
                                PhotoPreview.builder()
                                        .setPhotos(pathList)
                                        .setCurrentItem(helper.adapterPosition)
                                        .setShowDeleteButton(false)
                                        .start(this.mContext as Activity)
                            }
                        }
                    }
                    recy_envionmental_gcimage.adapter = gddwaAdapter
                }else{
                    pop_gctype = 0
                }

            }
        }else{
            tvWzenvironmental.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (!tvWzenvironmental.text.toString().equals("")&&xzqmc.pointRecordEntity==null){
                        mPresenter.getQueryCun(tvWzenvironmental.text.toString(), pjContract!!,rlv_xzxzq,tvWzenvironmental)
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
        }

        photoAdapter = PhotoAdapter(context, selectedPhotos)
        rlvPopEnvironmental.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_environmental2_scxs.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvPopEnvironmental.adapter = photoAdapter

        schxtp = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, PjEnviorFileList) {
            override fun convert(helper: BaseViewHolder?, item: PjEnviorFileEntity?) {
                val view = helper!!.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                val pic: String = item!!.url//ApiConstants.BASE_URL + item!!.path
                val s1 = pic.replace("\\", "/")
                Glide.with(mContext).load(pic)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view)
                helper!!.setText(R.id.tv_teng_photo_name, item.name)
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(activity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            OkGo.post<String> (ApiConstants.ENVIORFILEDELETE).upJson("[${item.id}]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(activity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                        if (json.code==0){
                                            PjEnviorFileList.removeAt(position)
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
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in PjEnviorFileList){
                        val pic = i.url//ApiConstants.BASE_URL + i.path
//                        val s1 = pic.replace("\\", "/")
                        pathList.add(pic)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(activity!!)


                }
            }
        }
        recy_pop_environmental2_scxs.adapter = schxtp
        videoAdapter = VideoAdapter(context, selectedVideos)
        rlvPopVideoadd.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvPopVideoadd.adapter = videoAdapter

        rlvPopEnvironmental.addOnItemTouchListener(RecyclerItemClickListener(activity, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(context!!, this@ZjdglFragment)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(context!!, this@ZjdglFragment,20)//context!!
                }
            }
        }))

        rlvPopVideoadd.addOnItemTouchListener(RecyclerItemClickListener(activity, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (videoAdapter!!.getItemViewType(position) === VideoAdapter.TYPE_ADD) {
//                    video()
                } else {
                    val intent = Intent(Intent.ACTION_VIEW)
                    val path = selectedVideos.get(position)//该路径可以自定义
                    val file = File(path)
                    val uri = Uri.fromFile(file)
                    intent.setDataAndType(Uri.parse(path), "video/*")
                    startActivity(intent)
                }
            }
        }))

        val hjzzList = ArrayList<BaobiaoBean>()//一级分类
        hjzzList.add(BaobiaoBean("人居环境检查",1))
        hjzzList.add(BaobiaoBean("太阳能浴室",2))
        hjzzList.add(BaobiaoBean("公厕管护",3))
        hjzzList.add(BaobiaoBean("绿化养护",4))
        hjzzList.add(BaobiaoBean("节能路灯",5))
        hjzzList.add(BaobiaoBean("农村大集环境检查",6))
        hjzzList.add(BaobiaoBean("垃圾暂存点",7))
        hjzzList.add(BaobiaoBean("户厕管护",8))
        val hjzzAdapter = HbAdapter(activity ,hjzzList)
        sp_hjzz_environmental.adapter = hjzzAdapter
        val valuesSjfl = HjzzsjEnum.values()//三级分类
        val sjflList = ArrayList<BaobiaoBean>()//三级分类
        var hjzzName = ""
        if (xzqmc.pointRecordEntity!=null){//判断是否是固定点位
            hjzzName = HjzzflEnum.getName(xzqmc.pointRecordEntity.name)
            pjContract!!.gddwid = xzqmc.pointRecordEntity.id
        }else{
            hjzzName = "人居环境检查"
        }
        tv_environ_up_bt.setText(hjzzName)
        if (hjzzName.equals("人居环境检查")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "1")
        }else if (hjzzName.equals("太阳能浴室")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "2")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("公厕管护")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "3")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        } else if (hjzzName.equals("绿化养护")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "4")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("节能路灯")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "5")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("农村大集环境检查")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "6")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("垃圾暂存点")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "7")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else{
            if (pjContract!!.hjzzej!=-1){
                addSjfl(valuesSjfl, sjflList,pjContract!!.hjzzejText)
            }else{
                addSjfl(valuesSjfl, sjflList,"101")
            }
        }
        for ( i in 0..sjflList.size-1){
            if (i == 0){
                sjflList.get(i).isCheck=true
            }
        }
        et_wtlx_gddw.setOnClickListener {
            //            et_wtlx_gddw.setInputType(InputType.TYPE_NULL); // 关闭软键盘
            initPopWttck(sjflList,et_wtlx_gddw)
        }
        val sjflAdapter = HbAdapter(activity ,sjflList)
        sp_sjfl_environmental.adapter = sjflAdapter


        if (pjContract!!.hjzzyjfl!=-1){
            val baobiaoBean = BaobiaoBean(HjzzflEnum.getName(pjContract!!.hjzzyjfl), pjContract!!.hjzzyjfl)
            var index=0
            for (i in 0..hjzzList.size-1){
                if (Gson().toJson(hjzzList.get(i)).equals(Gson().toJson(baobiaoBean))){
                    index = i
                }
            }
            sp_hjzz_environmental.setSelection(index,true)
        }
        if (xzqmc.pointRecordEntity!=null){//tv_environ_up_bt
            val baobiaoBean = BaobiaoBean(HjzzflEnum.getName(xzqmc.pointRecordEntity.name), xzqmc.pointRecordEntity.name)

            var index=0
            for (i in 0..hjzzList.size-1){
                if (Gson().toJson(hjzzList.get(i)).equals(Gson().toJson(baobiaoBean))){
                    index = i
                }
            }
            sp_hjzz_environmental.setSelection(index,true)
            tvRemarkEnvironmental.setText(xzqmc!!.pointRecordEntity!!.remark)//point
            tv_environ_up_bt.setText(hjzzList.get(index).name)
            if (hjzzList.get(index).name.equals("公厕管护考核")){
//                ll_ejfl_environmental.visibility = View.GONE
            }

        }else{

            tv_environ_up_bt.setText("人居环境检查")
        }


        val cncwList = ArrayList<String>();//村内村外
        cncwList.add("村内")
        cncwList.add("村外")
        val cncwAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,cncwList)
        cncwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_cncw_environmental.adapter = cncwAdapter
        val cnwText = pjContract!!.cnwText
        val indexOf = cncwList.indexOf(cnwText)
        if (xzqmc.getCzfw()==1){
            sp_cncw_environmental.setSelection(0,true)
        }else{
            sp_cncw_environmental.setSelection(1,true)
        }

        val sfgddwList = ArrayList<String>();//是否固定点位
        sfgddwList.add("是")
        sfgddwList.add("否")
//        sfgddwList.add("创城")
        val sfgddwAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,sfgddwList)
        sfgddwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_sfgddw_environmental.adapter = sfgddwAdapter
        val gddwText = pjContract!!.gddwText
        val indexOfgddw = sfgddwList.indexOf(gddwText)
        sp_sfzdwt_environmental.setSelection(indexOfgddw,true)
        val sfzdwtAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,sfgddwList)
        sfzdwtAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_sfzdwt_environmental.adapter = sfzdwtAdapter
        val zdwtText = pjContract!!.zdwtText
        val indexOfzdwt = sfgddwList.indexOf(zdwtText)
        if (pjContract.zdwt==-1){
            sp_sfzdwt_environmental.setSelection(1,true)
        }else{
            sp_sfzdwt_environmental.setSelection(indexOfzdwt,true)
        }
        tv_clshijian_environmental.setText(CalendarUtil.DATE_FORMAT_DATE.format(Date()))
        if (sp_sfzdwt_environmental.selectedItemPosition==0){
            tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(7))
        }else{
            tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(5))
        }

        tv_clshijian_environmental.setOnClickListener {//选择处理时间
            setTime(tv_clshijian_environmental)
        }
        tv_xfclsj_environmental.setOnClickListener {//选择期限时间
            setTime(tv_xfclsj_environmental)
        }

        val clqxList = ArrayList<String>();//处理期限
        clqxList.add("1")
        clqxList.add("2")
        clqxList.add("3")
        clqxList.add("4")
        clqxList.add("5")
        clqxList.add("6")
        clqxList.add("7")
        clqxList.add("8")
        clqxList.add("9")
        clqxList.add("10")
        clqxList.add("11")
        clqxList.add("12")
        clqxList.add("13")
        clqxList.add("14")
        clqxList.add("15")
        val jclqxAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,clqxList)
        jclqxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_clqx_environmental.adapter = jclqxAdapter
        if (sp_sfzdwt_environmental.selectedItemPosition==0){
            sp_clqx_environmental.setSelection(4,true)
        }else{
            sp_clqx_environmental.setSelection(2,true)
        }
        sp_sfzdwt_environmental.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2==0){
                    sp_clqx_environmental.setSelection(4,true)
                }else{
                    sp_clqx_environmental.setSelection(2,true)
                }
                tv_clshijian_environmental.setText(TimeUtil.getDateAfter(0).toString())
                if (p2==0){
                    tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(7))
                }else{
                    tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(5))
                }
            }
        }

        sp_sfgddw_environmental.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {//ll_gddw_envuronmental
                if (p2==0){
                    ll_gddw_envuronmental.visibility = View.VISIBLE
                    ll_fgddw_envuronmental.visibility = View.GONE
                }else{
                    ll_gddw_envuronmental.visibility = View.GONE
                    ll_fgddw_envuronmental.visibility = View.VISIBLE
                }
            }
        }
        if (xzqmc.pointRecordEntity!=null){
            sp_sfgddw_environmental.setSelection(0,true)
        }else{
            sp_sfgddw_environmental.setSelection(1,true)
        }

        val jcssghList = ArrayList<String>();//基础设施管护
        jcssghList.add("太阳能浴室")
        jcssghList.add("公厕管护")
        jcssghList.add("绿化养护")
        jcssghList.add("节能路灯")
        val jcssghAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,jcssghList)
        jcssghAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        sp_jcssgh_environmental.adapter = jcssghAdapter
        sp_jcssgh_environmental.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2==0){
                    ll_tynysyxwh_environmental.visibility = View.VISIBLE
                    ll_hcqtgh_environmental.visibility = View.GONE
                    ll_lhyh_environmental.visibility = View.GONE
                    ll_jnldyxwh_environmental.visibility = View.GONE
                }else if (p2==1){
                    ll_tynysyxwh_environmental.visibility = View.GONE
                    ll_hcqtgh_environmental.visibility = View.VISIBLE
                    ll_lhyh_environmental.visibility = View.GONE
                    ll_jnldyxwh_environmental.visibility = View.GONE
                }else if (p2==2){
                    ll_tynysyxwh_environmental.visibility = View.GONE
                    ll_hcqtgh_environmental.visibility = View.GONE
                    ll_lhyh_environmental.visibility = View.VISIBLE
                    ll_jnldyxwh_environmental.visibility = View.GONE
                }else if (p2==3){
                    ll_tynysyxwh_environmental.visibility = View.GONE
                    ll_hcqtgh_environmental.visibility = View.GONE
                    ll_lhyh_environmental.visibility = View.GONE
                    ll_jnldyxwh_environmental.visibility = View.VISIBLE
                }
            }
        }
        val hjzzyjflText = pjContract!!.hjzzyjflText
        val indexOfhjzzyjfl = jcssghList.indexOf(hjzzyjflText)
        if (indexOfhjzzyjfl!=-1){
            sp_jcssgh_environmental.setSelection(indexOfhjzzyjfl,true)
        }
        if (state == 0) { //            llWtztEnvironmental.visibility=View.VISIBLE
            llWtztEnvironmental.visibility = View.GONE
            rlvPopEnvironmental.visibility = View.GONE
        } else {
            rlvPopEnvironmental.visibility = View.VISIBLE
        }
        if (xzqmc.cjEntity!=null){
            tvWzenvironmental.setText(xzqmc.cjEntity.xzqmc)
        }else if (xzqmc.pointRecordEntity!=null){
            tvWzenvironmental.setText(xzqmc.pointRecordEntity.xzqmc)
        }
        if (pjContract != null && state == 0) { //            tvBhEnvironmental.setText(pjContract.getProjcode());
            tvLrwtEnvironmental.setText(pjContract.questions)
            tvWtztEnvironmental.setSelection(pjContract.qutype - 1, true)
            tvJlrEnvironmental.setText(pjContract.jlr)
            tvJlrPhone.setText(pjContract.jlrtel)
            tvWzenvironmental.setText(pjContract.wz)
            tvRemarkEnvironmental.setText(pjContract.remark)
            tvZrdwEnvironmental.setText(pjContract.zrdw)
        } else {
        }
        tvJlrEnvironmental.setText(AppCache.getInstance().name)
        tvJlrPhone.setText(AppCache.getInstance().phone)
        if (pjContract.jltime.equals("")){
            uploadDate = CalendarUtil.DATE_FORMAT_DATE.format(Date())
            tvUpDateEnviron!!.setText(uploadDate)
        }else{
            tvUpDateEnviron!!.setText(pjContract.jltime)
        }

        tvUpDateEnviron!!.setOnClickListener(View.OnClickListener { setTime(tvUpDateEnviron!!) })
        //暂时保存
        bt_temporary_environmental!!.setOnClickListener {
            if (pjContract.wz.equals("")){
                ToastUtils.showShort("村名不能为空")
                return@setOnClickListener
            }
            LoadingDialog.showDialogForLoading(activity)
            isls = 0
            bt_temporary_environmental!!.isEnabled = false
            tvUploadMeetingSummary!!.isEnabled = false

            pjContract!!.questions = tvLrwtEnvironmental.text.toString()
            pjContract!!.zrdw = tvZrdwEnvironmental.text.toString()
            if (xzqmc!!.pointRecordEntity!=null){
                pjContract!!.location = xzqmc!!.pointRecordEntity.location
            }else{
                pjContract!!.location = pointStr//"POINT(116.69999599456787 39.97817516326904)"//pointStr
            }

            pjContract!!.jlrtel = tvJlrPhone.text.toString()
            pjContract!!.jlr = tvJlrEnvironmental.text.toString()
            pjContract!!.remark = tvRemarkEnvironmental.text.toString()
            if (sp_cncw_environmental.isShown){
                val cnwString = sp_cncw_environmental.selectedItem.toString()
                pjContract.cnw = CnwEnum.getIndex(cnwString)
            }
            val sfgddwString = sp_sfgddw_environmental.selectedItem.toString()
            pjContract.gddw = IsTrueEnum.getIndex(sfgddwString)
            if (sp_sfzdwt_environmental.isShown){
                val sfzdwtString = sp_sfzdwt_environmental.selectedItem.toString()
                pjContract.zdwt = IsTrueEnum.getIndex(sfzdwtString)
            }
            pjContract.hjzzyjfl = HjzzflEnum.getIndex(tv_environ_up_bt.text.toString())//hjzzList.get(sp_hjzz_environmental.selectedItemPosition).name

            if (ll_sjfl_environmental.isShown){
                /*pjContract.hjzzsj = HjzzsjEnum.getIndex(sjflList.get(sp_sjfl_environmental.selectedItemPosition).name,
                        sjflList.get(sp_sjfl_environmental.selectedItemPosition).index)*/
                pjContract.hjzzfls = arrayOf(HjzzsjEnum.getIndex(sjflList.get(sp_sjfl_environmental.selectedItemPosition).name,
                        sjflList.get(sp_sjfl_environmental.selectedItemPosition).index))
            }else{

                val arrayList = ArrayList<Int>()
                for (i in sjflList){
                    if (i.isCheck){
                        for ( f in 0..i.num-1){
                            arrayList.add(i.index)
                        }

                    }
                }
                pjContract.hjzzfls = arrayList.toTypedArray()

            }
            if (typeLx==1){
                pjContract!!.qutype = 0
            }else{
                pjContract!!.qutype = 0
                pjContract.zgyq = tv_xfzgyq_environmental.text.toString()
                val format = SimpleDateFormat("yyyy-MM-dd")//HHmmss
                val date = Date(System.currentTimeMillis())
                val filename = format.format(date)
                pjContract.kstime=tv_clshijian_environmental.text.toString()//filename   ///
                pjContract.qxtime=tv_xfclsj_environmental.text.toString()//TimeUtil.getDateAfter(sp_clqx_environmental.selectedItem.toString().toInt()+1)
                pjContract.xfjy = tv_xfjy_environmental.text.toString()
            }
            pjContract.jltime = tvUpDateEnviron!!.getText().toString()

            LoadingDialog.showDialogForLoading(activity)

            val fileIdList = ArrayList<Int>()
            fileIdList.addAll(videoIdList)
            for (i in PjEnviorFileList!!){
                fileIdList.add(i.id)
            }
            val strings = arrayOfNulls<Int>(fileIdList.size)
            val integers: Array<Int> = fileIdList.toArray<Int>(strings)

            pjContract.fileIds = integers
            LoadingDialog.cancelDialogForLoading()
            mPresenter.getHbLr(pjContract)

        }

        //全部上传
        tvUploadMeetingSummary!!.setOnClickListener(View.OnClickListener {

            if (pjContract.wz.equals("")){
                ToastUtils.showShort("村名不能为空")
                return@OnClickListener
            }
            LoadingDialog.showDialogForLoading(activity)
            isls = 1
            bt_temporary_environmental!!.isEnabled = false
            tvUploadMeetingSummary!!.isEnabled = false

            /*if (tvLrwtEnvironmental.text.toString().length == 0) { //"请填写标题"
                ToastUtils.showShort("请输入问题")
            } else */
            if (tvJlrEnvironmental.text.toString().length == 0) { //"请填写标题"
                ToastUtils.showShort("请输入记录人")
            }/*else if (tv_xfzgyq_environmental.text.toString().equals("")){
                ToastUtils.showShort("请填写整改要求")
            }*/else {
                pjContract!!.questions = tvLrwtEnvironmental.text.toString()
                pjContract!!.zrdw = tvZrdwEnvironmental.text.toString()//责任单位 116.35698630592977 39.763910845715266  116.34465369407023 39.76122315428473
//                val myLocation = aMap!!.myLocation//116.357132,39.767957   百度:116.350798 39.76261  天地图:116.3442884 39.7609968  高德地图:116.350068,39.762279
//                val gps = PositionUtil.gcj_To_Gps84(myLocation.latitude, myLocation.longitude)
                if (xzqmc!!.pointRecordEntity!=null){
                    pjContract!!.location = xzqmc!!.pointRecordEntity.location
                }else{
                    pjContract!!.location = pointStr//"POINT(116.69999599456787 39.97817516326904)"//pointStr
                }
                pjContract!!.jlrtel = tvJlrPhone.text.toString()
//                pjContract!!.qutype = EnviorSupvsEnum.getIndex(tvWtztEnvironmental.selectedItem.toString())

                pjContract!!.jlr = tvJlrEnvironmental.text.toString()
                pjContract!!.remark = tvRemarkEnvironmental.text.toString()
//                pjContract!!.wz = tvWzenvironmental.text.toString()
                //村内村外:sp_cncw_environmental 是否固定点位:sp_sfgddw_environmental 是否突出问题:sp_sfzdwt_environmental 基础设施管护:sp_jcssgh_environmental 太阳能浴室运行维护:sp_tynysyxwh_environmental
                //户厕清掏管护:sp_hcqtgh_environmental 绿化养护:sp_lhyh_environmental 节能路灯运行维护:sp_jnlduyxwh_environmental 问题统计:sp_wttj_environmental 生活垃圾治理:sp_shljzl_environmental
                //生活污水治理:sp_shwszl_environmental 公厕管护:sp_gcgh_environmental 私搭乱建:sp_sdlj_environmental
                if (sp_cncw_environmental.isShown){
                    val cnwString = sp_cncw_environmental.selectedItem.toString()
                    pjContract.cnw = CnwEnum.getIndex(cnwString)
                }
//                if (sp_sfgddw_environmental.isShown){
                val sfgddwString = sp_sfgddw_environmental.selectedItem.toString()
                pjContract.gddw = IsTrueEnum.getIndex(sfgddwString)
//                }
                if (sp_sfzdwt_environmental.isShown){
                    val sfzdwtString = sp_sfzdwt_environmental.selectedItem.toString()
                    pjContract.zdwt = IsTrueEnum.getIndex(sfzdwtString)
                }
                if (sp_jcssgh_environmental.isShown){
                    val jcssghString = sp_jcssgh_environmental.selectedItem.toString()
                    val index = HjzzflEnum.getIndex(jcssghString)
                    pjContract.hjzzyjfl = index
                }
                if (sp_tynysyxwh_environmental.isShown){
                    val jcssghString = sp_tynysyxwh_environmental.selectedItem.toString()
                    pjContract.yswh = YswhEnum.getIndex(jcssghString)
                }
                if (sp_hcqtgh_environmental.isShown){
                    val hcqtghString = sp_hcqtgh_environmental.selectedItem.toString()
                    pjContract.hcqtgh = HcqtghEnum.getIndex(hcqtghString)
                }
                if (sp_lhyh_environmental.isShown){
                    val lhyhString = sp_lhyh_environmental.selectedItem.toString()
                    pjContract.lhyh = LhyhEnum.getIndex(lhyhString)
                }
                if (sp_jnlduyxwh_environmental.isShown){
                    val jnlduyxwhString = sp_jnlduyxwh_environmental.selectedItem.toString()
                    pjContract.ldwh = Ldwhenum.getIndex(jnlduyxwhString)
                }
                if (sp_wttj_environmental.isShown){
                    val wttjString = sp_wttj_environmental.selectedItem.toString()
                    val index = HjzzflEnum.getIndex(wttjString)
                    pjContract.hjzzyjfl = index
                }
                if (sp_shljzl_environmental.isShown){
                    val shljzlString = sp_shljzl_environmental.selectedItem.toString()
                    pjContract.shljzl = ShljzlEnum.getIndex(shljzlString)
                }
                if (sp_shwszl_environmental.isShown){
                    val shwszlString = sp_shwszl_environmental.selectedItem.toString()
                    pjContract.shwszl = ShwszlEnum.getIndex(shwszlString)
                }
                if (sp_gcgh_environmental.isShown){
                    val gcghString = sp_gcgh_environmental.selectedItem.toString()
                    pjContract.gcgh = GcghEunm.getIndex(gcghString)
                }
                if (sp_sdlj_environmental.isShown){
                    val sdljString = sp_sdlj_environmental.selectedItem.toString()
                    pjContract.sdlj = SdljEnum.getIndex(sdljString)
                }
                pjContract.hjzzyjfl = HjzzflEnum.getIndex(tv_environ_up_bt.text.toString())//hjzzList.get(sp_hjzz_environmental.selectedItemPosition).name

                if (ll_sjfl_environmental.isShown){
                    pjContract.hjzzfls = arrayOf(HjzzsjEnum.getIndex(sjflList.get(sp_sjfl_environmental.selectedItemPosition).name,
                            sjflList.get(sp_sjfl_environmental.selectedItemPosition).index))
                }else{

                    val arrayList = ArrayList<Int>()
                    for (i in sjflList){
                        if (i.isCheck){
                            for ( f in 0..i.num-1){
                                arrayList.add(i.index)
                            }
                        }
                    }
                    pjContract.hjzzfls = arrayList.toTypedArray()

                }


                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                try {
                    val dateString = simpleDateFormat.parse(tvUpDateEnviron!!.getText().toString())
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                pjContract.jltime = tvUpDateEnviron!!.getText().toString()
                if (typeLx==1||sjflList.get(sp_sjfl_environmental.selectedItemPosition).name.equals("村内暂无问题")){
                    pjContract!!.qutype = 1//1//下发
                }else{
                    pjContract!!.qutype = 3//3//下发
                    pjContract.zgyq = tv_xfzgyq_environmental.text.toString()
                    val format = SimpleDateFormat("yyyy-MM-dd")//HHmmss
                    val date = Date(System.currentTimeMillis())
                    val filename = format.format(date)
                    pjContract.kstime=tv_clshijian_environmental.text.toString()//filename   ///
                    pjContract.qxtime=tv_xfclsj_environmental.text.toString()//TimeUtil.getDateAfter(sp_clqx_environmental.selectedItem.toString().toInt()+1)
                    pjContract.xfjy = tv_xfjy_environmental.text.toString()
                }


                LoadingDialog.showDialogForLoading(activity)
//            mPresenter.getQueryCun(tvWzenvironmental.text.toString(),pjContract)
                val intList = java.util.ArrayList<Int>()
                var se = 0
                val fileIdList = ArrayList<Int>()
                fileIdList.addAll(videoIdList)
                for (i in PjEnviorFileList!!){
                    fileIdList.add(i.id)
                }
                val strings = arrayOfNulls<Int>(fileIdList.size)
                val integers: Array<Int> = fileIdList.toArray<Int>(strings)

                pjContract.fileIds = integers
                LoadingDialog.cancelDialogForLoading()
                mPresenter.getHbLr(pjContract)
//                }

            }
        })
        butClearMeetingSummary.setOnClickListener {
            photoAdapter!!.clearPics()
//            clearUpdate(tvNameEnvironmental, tvNameEnvironmental, tvLrwtEnvironmental, tvJlrEnvironmental, tvWzenvironmental, tvRemarkEnvironmental)
            if (envirorUpPopu!=null){
                envirorUpPopu!!.dismiss()
            }
        }
    }

    private fun initPopWttck(baobiaoList: ArrayList<BaobiaoBean>,et_wtlx_gddw:TextView) {
        var envirorUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_rjhj_wtxzk, supl_frag_zjdgl)
        val contentView2: View = envirorUpPopu!!.getContentView()

        val findViewById = contentView2.findViewById<RecyclerView>(R.id.rlv_wtxzk)

        val but_wtxzk_qd = contentView2.findViewById<Button>(R.id.but_wtxzk_qd)
        findViewById.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        findViewById.adapter = object:BaseQuickAdapter<BaobiaoBean,BaseViewHolder>(R.layout.item_wtxzk,baobiaoList){
            override fun convert(helper: BaseViewHolder?, item: BaobiaoBean?) {
                helper!!.setText(R.id.cb_wtxzk, item!!.name)
                val et_wtxzk_gwtsl = helper.getView<EditText>(R.id.et_wtxzk_gwtsl)
                et_wtxzk_gwtsl.setText(item.num.toString())
//                item.num = 1
                et_wtxzk_gwtsl.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_wtxzk_gwtsl.text.toString().equals("")){
                            item.num = et_wtxzk_gwtsl.text.toString().toInt()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                /*if (item!!.name.equals("公厕未正常开放")){
                    helper!!.setTextColor(R.id.cb_wtxzk, Color.RED)
                }*/
                val view = helper.getView<CheckBox>(R.id.cb_wtxzk)
                view.isChecked = item.isCheck
                view.setOnClickListener {
                    item.isCheck = view.isChecked
//                    notifyDataSetChanged()
                }
            }

        }
        but_wtxzk_qd.setOnClickListener {
            var s = ""
            for ( i in baobiaoList){
                if (i.isCheck){
                    if (s.equals("")){
                        s = i.name
                    }else{
                        s=s+"、"+i.name
                    }
                }
            }
            et_wtlx_gddw.setText(s)
            envirorUpPopu.dismiss()
        }
        // 设置背景颜色变暗
        var lp = activity!!.getWindow().getAttributes()
        lp.alpha = 0.7f
        activity!!.getWindow().setAttributes(lp)
        envirorUpPopu.setOnDismissListener(object :PopupWindow.OnDismissListener {

            override fun onDismiss() {
                var s = ""
                for ( i in baobiaoList){
                    if (i.isCheck){
                        if (s.equals("")){
                            s = i.name
                        }else{
                            s=s+"、"+i.name
                        }
                    }
                }
                et_wtlx_gddw.setText(s)
                var lp = activity!!.getWindow().getAttributes();
                lp.alpha = 1f;
                activity!!.getWindow().setAttributes(lp);
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        envirorUpPopu!!.showAtLocation(supl_frag_zjdgl, Gravity.CENTER, 0, 0)

    }
    private fun addSjfl(values: Array<HjzzsjEnum>, sjflList: ArrayList<BaobiaoBean>,index:String) {
        for (i in values) {
            val subSequence = i.index.toString().subSequence(0, 3)
            if (subSequence.equals(index)) {
//                if (i.getName().contains(" ")){
                sjflList.add(BaobiaoBean(i.getName(),i.index))
//                }
            }
        }
    }

    private fun addNewSjfl(values: Array<HjzzsjEnum>, sjflList: ArrayList<BaobiaoBean>,index:String) {
        for (i in values) {
            val subSequence = i.index.toString().subSequence(0, 1)
            if (subSequence.equals(index)) {
//                if (i.getName().contains(" ")){
                sjflList.add(BaobiaoBean(i.getName(),i.index))
//                }
            }
        }
    }
    var image_ddgcmp_environmental: ImageView? = null
    var image_ddgcwg_environmental: ImageView? = null
    var image_ddgcmp_del_environmental: TextView? = null
    var image_ddgcwg_del_environmental: TextView? = null
    fun initPopuDingDian(pointRecordEntity:PointRecordEntity){
        renovatedFileList.clear()
        gcImageURL1 = ""
        gcImageURL2 = ""
        dingDianUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_ding_dian, supl_frag_zjdgl)
        val contentView2: View = dingDianUpPopu!!.getContentView()
        val sp_ddmc_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddmc_environmental)// 固定点位名称
        val et_ddmc_environmental = contentView2.findViewById<EditText>(R.id.et_ddmc_environmental)// 固定点位名称
        val tv_gddw_title = contentView2.findViewById<TextView>(R.id.tv_gddw_title)//编号
        val et_gddwbh_environmental = contentView2.findViewById<EditText>(R.id.et_gddwbh_environmental)//编号
        val et_gddwwz_environmental = contentView2.findViewById<EditText>(R.id.et_gddwwz_environmental)//位置
        val et_ddremark_environmental = contentView2.findViewById<EditText>(R.id.et_ddremark_environmental)//备注
        val et_ddlrr_environmental = contentView2.findViewById<EditText>(R.id.et_ddlrr_environmental)//录入人  id
        val et_ddkfsj_environmental = contentView2.findViewById<EditText>(R.id.et_ddkfsj_environmental)//开放时间
        val bt_ddadd_environmental = contentView2.findViewById<Button>(R.id.bt_ddadd_environmental)//确定
        val bt_dddelete_environmental = contentView2.findViewById<Button>(R.id.bt_dddelete_environmental)//删除
        val ll_gc_isshow = contentView2.findViewById<LinearLayout>(R.id.ll_gc_isshow)//
        val et_ddxzq_environmental = contentView2.findViewById<EditText>(R.id.et_ddxzq_environmental)//乡镇
        val et_ddcun_environmental = contentView2.findViewById<EditText>(R.id.et_ddcun_environmental)//乡镇
        val et_ddarea_environmental = contentView2.findViewById<EditText>(R.id.et_ddarea_environmental)//面积
        val et_dddws_environmental = contentView2.findViewById<EditText>(R.id.et_dddws_environmental)//吨位数
        val sp_ddleibie_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddleibie_environmental)//类别 二类/三类
        val sp_ddiswsgw_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddiswsgw_environmental)//是否接入污水管网 是 否
        val et_ddjcny_environmental = contentView2.findViewById<EditText>(R.id.et_ddjcny_environmental)//建成年月
        val et_ddjscb_environmental = contentView2.findViewById<EditText>(R.id.et_ddjscb_environmental)//建设成本
        val sp_ddyxqk_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddyxqk_environmental)//运行情况 开放 未开放
        val et_ddkfdate_environmental = contentView2.findViewById<EditText>(R.id.et_ddkfdate_environmental)//开放日期
        val et_ddkfsc_environmental = contentView2.findViewById<EditText>(R.id.et_ddkfsc_environmental)//开放时长
        val et_ddyssj_environmental = contentView2.findViewById<EditText>(R.id.et_ddyssj_environmental)//验收时间
        val sp_ddgczt_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddgczt_environmental)//公厕状态
        val sp_ddgcsx_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddgcsx_environmental)//公厕属性
        val ll_ding_tp = contentView2.findViewById<LinearLayout>(R.id.ll_ding_tp)//
        image_ddgcmp_environmental = contentView2.findViewById<ImageView>(R.id.image_ddgcmp_environmental)//公厕名牌
        image_ddgcwg_environmental = contentView2.findViewById<ImageView>(R.id.image_ddgcwg_environmental)//公厕外观
        image_ddgcmp_del_environmental = contentView2.findViewById<TextView>(R.id.image_ddgcmp_del_environmental)//公厕名牌删除
        image_ddgcwg_del_environmental = contentView2.findViewById<TextView>(R.id.image_ddgcwg_del_environmental)//公厕外观删除




        image_ddgcmp_del_environmental!!.setOnClickListener {
            if (renovatedFileList.size>0){
                for (i in 0..renovatedFileList.size-1){
                    if (renovatedFileList.get(i).filetype.toInt() == 1){
//                        renovatedFileList.removeAt(i)
                        delGcImage(1,i,renovatedFileList)
                    }
                }
            }
        }
        image_ddgcwg_del_environmental!!.setOnClickListener {
            if (renovatedFileList.size>0){
                for (i in 0..renovatedFileList.size-1){
                    if (renovatedFileList.get(i).filetype.toInt() == 2){
//                        renovatedFileList.removeAt(i)
                        delGcImage(2,i,renovatedFileList)
                    }
                }
            }
        }

        image_ddgcmp_environmental!!.setOnClickListener {
            if (gcImageURL1.equals("")){
                gcImageType = 1
                showDialogCustom()
            }else{
                var pathList = ArrayList<String>()
                pathList.add(gcImageURL1)
                PhotoPreview.builder()
                        .setPhotos(pathList)

                        .setShowDeleteButton(false)//.setCurrentItem(helper.adapterPosition)
                        .start(context as Activity)

            }
        }
        image_ddgcwg_environmental!!.setOnClickListener {
            if (gcImageURL2.equals("")){
                gcImageType = 2
                showDialogCustom()
            }else{
                var pathList = ArrayList<String>()
                pathList.add(gcImageURL2)
                PhotoPreview.builder()
                        .setPhotos(pathList)
                        .setShowDeleteButton(false)//.setCurrentItem(helper.adapterPosition)
                        .start(context as Activity)
            }
        }
        var ddList = ArrayList<String>()
        /*ddList.add("太阳能浴室运行维护")
        ddList.add("户厕清掏管护")
        ddList.add("绿化养护")
        ddList.add("节能路灯运行维护")
        ddList.add("公厕管护")*/
        ddList.add("太阳能浴室")
//        if (pointRecordEntity.id!=-1)
        ddList.add("公厕管护")
        ddList.add("绿化养护")
        ddList.add("节能路灯")
//        if (pointRecordEntity.id!=-1)
        ddList.add("农村大集环境检查")
        ddList.add("垃圾暂存点")
        ddList.add("户厕管护")
        val ddAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,ddList)
        ddAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddmc_environmental.adapter = ddAdapter

        //类别
        var leibieList = ArrayList<String>()
        leibieList.add("二类")
        leibieList.add("三类")

        val leibieAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,leibieList)
        leibieAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddleibie_environmental.adapter = leibieAdapter

        //是否接入污水管网
        var iswsgwList = ArrayList<String>()
        iswsgwList.add("是")
        iswsgwList.add("否")
        val iswsgwAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,iswsgwList)
        iswsgwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddiswsgw_environmental.adapter = iswsgwAdapter

        //运行情况
        var yxqkList = ArrayList<String>()
        yxqkList.add("开放")
        yxqkList.add("未开放")
        val yxqkAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,yxqkList)
        yxqkAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddyxqk_environmental.adapter = yxqkAdapter

        //公厕状态
        var gcztList = ArrayList<String>()
        gcztList.add("在施")
        gcztList.add("未完工")
        gcztList.add("完工")
        val gcztAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,gcztList)
        gcztAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddgczt_environmental.adapter = gcztAdapter

        //公厕属性
        var gcsxList = ArrayList<String>()
        gcsxList.add("新建")
        gcsxList.add("改造")
        val gcsxAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,gcsxList)
        gcsxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddgcsx_environmental.adapter = gcsxAdapter

        if (pointRecordEntity.id!=-1){
            et_ddmc_environmental.visibility=View.VISIBLE
            sp_ddmc_environmental.visibility=View.GONE
            et_ddmc_environmental.setText(HjzzflEnum.getName(pointRecordEntity.name.toInt()))
            if (AppCache.getInstance().type!=5){//判断非领导状态显示删除固定点位   这里需要做判断
                bt_dddelete_environmental.visibility =View.VISIBLE
            }

            tv_gddw_title.setText("点位查看")
            et_gddwbh_environmental.setText(pointRecordEntity.bh)
            et_gddwwz_environmental.setText(pointRecordEntity.wz)
            et_ddremark_environmental.setText(pointRecordEntity.remark)
            et_ddkfsj_environmental.setText(pointRecordEntity.kfsj)

            sp_ddmc_environmental.setSelection(ddList.indexOf(HjzzflEnum.getName(pointRecordEntity.name.toInt())), true)
            et_ddxzq_environmental.setText(pointRecordEntity.zhen)
            et_ddcun_environmental.setText(pointRecordEntity.xzqmc)
            if (HjzzflEnum.getName(pointRecordEntity.name.toInt()).equals("公厕管护")||
                    HjzzflEnum.getName(pointRecordEntity.name.toInt()).equals("农村大集环境检查")){
//                bt_dddelete_environmental.visibility = View.GONE
            }
            if (sp_ddmc_environmental.selectedItem.toString().equals("公厕管护")){
                ll_ding_tp.visibility = View.VISIBLE
                et_ddarea_environmental.setText(pointRecordEntity.area.toString())
                et_dddws_environmental.setText(pointRecordEntity.dws.toString())
                sp_ddleibie_environmental.setSelection(leibieList.indexOf(pointRecordEntity.category) ,true)
                sp_ddiswsgw_environmental.setSelection(iswsgwList.indexOf(pointRecordEntity.jrwsgw) , true)
                et_ddjcny_environmental.setText(pointRecordEntity.jcny)
                et_ddjscb_environmental.setText(pointRecordEntity.jscb.toString())
                et_ddkfdate_environmental.setText(pointRecordEntity.kfrq.toString())
                et_ddkfsc_environmental.setText(pointRecordEntity.kfsc.toString())
                et_ddyssj_environmental.setText(pointRecordEntity.yssj.toString())
                sp_ddyxqk_environmental.setSelection(yxqkList.indexOf(pointRecordEntity.yxqk) , true)
                sp_ddgczt_environmental.setSelection(gcztList.indexOf(pointRecordEntity.gczt) , true)
                sp_ddgcsx_environmental.setSelection(gcsxList.indexOf(pointRecordEntity.gcsx) , true)
                renovatedFileList = pointRecordEntity.files as ArrayList<RenovatedFile>
                if (renovatedFileList.size>0){
                    for (i in 0..renovatedFileList.size-1){
                        if (renovatedFileList.get(i).filetype.toInt() == 1){//renovatedFileList.get(i).url
                            gcImageURL1 = renovatedFileList.get(i).path
                            Glide.with(activity!!).load(renovatedFileList.get(i).path).into(image_ddgcmp_environmental!!)
                            image_ddgcmp_del_environmental!!.visibility = View.VISIBLE
                        }else{
                            gcImageURL2 = renovatedFileList.get(i).path
                            Glide.with(activity!!).load(renovatedFileList.get(i).path).into(image_ddgcwg_environmental!!)
                            image_ddgcwg_del_environmental!!.visibility = View.VISIBLE
                        }
                    }
                }
            }else{
                et_ddmc_environmental.visibility=View.GONE
                sp_ddmc_environmental.visibility=View.VISIBLE
                ll_ding_tp.visibility = View.GONE
            }

            bt_dddelete_environmental.setOnClickListener {
                LoadingDialog.cancelDialogForLoading()
                val content = TextView(activity)
                content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("删除固定点位")
                        .setCustomView(content)
                        .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                            mPresenter.getDdDetel(pointRecordEntity.id.toString())
                            sweetAlertDialog.dismiss()}
                        .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                        .show()
            }
//            et_ddlrr_environmental.setText(pointRecordEntity.lrr)
        }else{
            tv_gddw_title.setText("点位添加")
            et_ddlrr_environmental.setText(AppCache.getInstance().userId.toString())
            pointRecordEntity.location = pointStr
            pointRecordEntity.lrr = et_ddlrr_environmental.text.toString().toInt()
        }
        if (AppCache.getInstance().type == 5){//这里需要做判断
            image_ddgcmp_del_environmental!!.visibility = View.GONE
            image_ddgcwg_del_environmental!!.visibility = View.GONE
        }

        if (sp_ddmc_environmental.selectedItem.toString().equals("公厕管护")){
            ll_gc_isshow.visibility = View.VISIBLE
            ll_ding_tp.visibility = View.VISIBLE
            if (AppCache.getInstance().type ==1||AppCache.getInstance().type ==5){//这里需要做判断
                et_ddarea_environmental.visibility = View.VISIBLE
                et_dddws_environmental.visibility = View.VISIBLE
                sp_ddleibie_environmental.visibility = View.VISIBLE
                sp_ddiswsgw_environmental.visibility = View.VISIBLE
                et_ddjcny_environmental.visibility = View.VISIBLE
                et_ddjscb_environmental.visibility = View.VISIBLE
                sp_ddyxqk_environmental.visibility = View.VISIBLE
                et_ddkfdate_environmental.visibility = View.VISIBLE
                et_ddkfsc_environmental.visibility = View.VISIBLE
                et_ddyssj_environmental.visibility = View.VISIBLE
            }
        }else{
            ll_gc_isshow.visibility = View.GONE
            ll_ding_tp.visibility = View.GONE
        }
        //切换spinner显示隐藏部分内容
        sp_ddmc_environmental.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

//                ToastUtils.showShort(sp_ddmc_environmental.selectedItem.toString())

                if (sp_ddmc_environmental.selectedItem.toString().equals("公厕管护")){
                    ll_gc_isshow.visibility = View.VISIBLE
                    ll_ding_tp.visibility = View.VISIBLE
                }else{
                    ll_gc_isshow.visibility = View.GONE
                    ll_ding_tp.visibility = View.GONE
                }
            }
        }

        et_ddjcny_environmental.setOnClickListener {
            setTime(et_ddjcny_environmental)
        }
        et_ddkfdate_environmental.setOnClickListener {
            setTime(et_ddkfdate_environmental)
        }
        et_ddyssj_environmental.setOnClickListener {
            setTime(et_ddyssj_environmental)
        }
        bt_ddadd_environmental.setOnClickListener {
            if (AppCache.getInstance().duties==1){//这里需要做判断

                dingDianUpPopu!!.dismiss()
            }else{
                //            val pointRecordEntity = PointRecordEntity()
                if (et_ddarea_environmental.text.toString().trim().startsWith(".")||et_ddjscb_environmental.text.toString().trim().startsWith(".")){
                    ToastUtils.showShort("请输入正确的面积/建设成本")
                    return@setOnClickListener
                }
                pointRecordEntity.name = HjzzflEnum.getIndex(sp_ddmc_environmental.selectedItem.toString())
                pointRecordEntity.bh = et_gddwbh_environmental.text.toString()
                pointRecordEntity.wz = et_gddwwz_environmental.text.toString()
                pointRecordEntity.remark = et_ddremark_environmental.text.toString()
                pointRecordEntity.kfsj = et_ddkfsj_environmental.text.toString()
                pointRecordEntity.zhen = et_ddxzq_environmental.text.toString()
                if (pointRecordEntity.name==3&&(AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF)){
                    if (et_ddarea_environmental.text.toString().trim().equals("")){
                        pointRecordEntity.area = BigDecimal.ZERO
                    }else{
                        pointRecordEntity.area = BigDecimal(et_ddarea_environmental.text.trim().toString())
                    }
                    if (et_dddws_environmental.text.toString().trim().equals("")){
                        pointRecordEntity.dws = 0
                    }else{
                        pointRecordEntity.dws = et_dddws_environmental.text.toString().toInt()
                    }
                    pointRecordEntity.category = sp_ddleibie_environmental.selectedItem.toString()
                    pointRecordEntity.jrwsgw = sp_ddiswsgw_environmental.selectedItem.toString()
                    pointRecordEntity.jcny = et_ddjcny_environmental.text.toString()
                    if (et_ddjscb_environmental.text.toString().trim().equals("")){
                        pointRecordEntity.jscb = BigDecimal.ZERO
                    }else{
                        pointRecordEntity.jscb = BigDecimal(et_ddjscb_environmental.text.toString())
                    }
                    pointRecordEntity.yxqk = sp_ddyxqk_environmental.selectedItem.toString()
                    pointRecordEntity.kfrq = et_ddkfdate_environmental.text.toString()
                    pointRecordEntity.kfsc = et_ddkfsc_environmental.text.toString()
                    pointRecordEntity.yssj = et_ddyssj_environmental.text.toString()
                }

                if (pointRecordEntity.name==3){
                    pointRecordEntity.gczt= sp_ddgczt_environmental.selectedItem.toString()
                    pointRecordEntity.gcsx= sp_ddgcsx_environmental.selectedItem.toString()
                }

                pointRecordEntity.files= renovatedFileList //图片集合

                if (pointRecordEntity.id!=-1){
                    mPresenter.getDdUpdate(pointRecordEntity)
                }else{
                    mPresenter.getDdLr(pointRecordEntity)

                }

            }


        }
    }

    private val mCustomItems = arrayOf("本地相册", "相机拍照")
    private val IMAGE_REQUEST_CODE = 1
    private val TAKE_PHOTO_REQUEST = 5
//    var SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().path + "/tzrjhjCamera/"// 拍照路径
    var SAVED_IMAGE_DIR_PATH = AppMenus.getInstance().cardPath+"BMS/map/pic/"// 拍照路径
    private var cameraPath: String? = null
    private fun showDialogCustom() {
        //创建对话框
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("请选择：")
        builder.setItems(mCustomItems, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                //相册
                //在这里跳转到手机系统相册里面
                val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            } else if (which == 1) {
                // 指定相机拍摄照片保存地址
                val state = Environment.getExternalStorageState()
                if (state == Environment.MEDIA_MOUNTED) {
                    cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg"
                    val intent = Intent()
                    // 指定开启系统相机的Action
                    intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                    val out_file_path = SAVED_IMAGE_DIR_PATH
                    val dir = File(out_file_path)
                    if (!dir.exists()) {
                        dir.mkdirs()
                    } // 把文件地址转换成Uri格式
                    val tempFile = File(cameraPath)
                    if (tempFile.exists()){
                        tempFile.delete();
                    }
                    tempFile.createNewFile();
                    val uri = Uri.fromFile(tempFile)
                    // 设置系统相机拍摄照片完成后图片文件的存放地址
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    startActivityForResult(intent, TAKE_PHOTO_REQUEST)
                } else {
                    ToastUtils.showShort("请确认已经插入SD卡")
                }
            }
        })
        builder.setCancelable(true)//setCanceledOnTouchOutside
        val alertDialog = builder.create()
        alertDialog.show()
        //设置点击弹出框的其他地方弹出框消失
        alertDialog.setCanceledOnTouchOutside(true)
    }
    //公厕图片删除
    private fun delGcImage(type : Int,id:Int,renovatedFileList : ArrayList<RenovatedFile>){
        var position = id
        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        var builder = AlertDialog.Builder(activity)
        // 设置Title的图标
        builder.setIcon(R.mipmap.ic_launcher)
        // 设置Title的内容
        builder.setTitle("弹出警告框")
        // 设置Content来显示一个信息
        builder.setMessage("确定删除吗？")
        // 设置一个PositiveButton
        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

                OkGo.post<String> (ApiConstants.ENVIORFILE_DELETE_FILE).upJson("[${renovatedFileList.get(id).id}]").execute(object :
                        BaseNet<String>(){
                    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                        super.onStart(request)
                        LoadingDialog.showDialogForLoading(activity)
                    }

                    override fun onSuccess(response: Response<String>?) {
                        val cash = response?.body()
                        if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                            val json: BaseRespose <*> = Gson().fromJson(cash, object : TypeToken<BaseRespose <*>?>() {}.type)
                            if (json.code==0){
                                renovatedFileList.removeAt(position)
                                ToastUtils.showShort("删除成功")
                                if (type == 1){
                                    gcImageURL1 = ""
                                    image_ddgcmp_del_environmental!!.visibility = View.GONE
                                    Glide.with(activity!!).load(R.drawable.add_image).into(image_ddgcmp_environmental!!)
                                }else{
                                    gcImageURL2 = ""
                                    image_ddgcwg_del_environmental!!.visibility = View.GONE
                                    Glide.with(activity!!).load(R.drawable.add_image).into(image_ddgcwg_environmental!!)
                                }
//
                            }else{
                                ToastUtils.showShort(json.getMsg())
                            }

                        } else {
                            ToastUtils.showShort("网络错误")
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
    private fun setTime(editText: TextView) {//选择时间  EditText
        val year = Calendar.getInstance()[Calendar.YEAR]
        val month = Calendar.getInstance()[Calendar.MONTH]
        val day = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
            var nian = ""
            var yue = ""
            var ri = ""
            nian = i.toString() + ""
            yue = if (i1 < 9) {
                "0" + (i1 + 1)
            } else {
                "" + (i1 + 1)
            }
            ri = if (i2 < 10) {
                "0$i2"
            } else {
                "" + i2
            }
            editText.setText(nian+"-"+yue+"-"+ri)
        }, year, month, day)
        val datePicker = datePickerDialog.datePicker
        datePicker.maxDate = System.currentTimeMillis()+1000 ///< 设置日期的上限日期
        datePickerDialog.show()
    }
    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

    private fun initPopuFanJian(message: FjBean) {
        renovatedFileList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_fan_jian, supl_frag_zjdgl)
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
        rlv_fan_jian_upfile.layoutManager = GridLayoutManager(activity,3)
        rlv_fan_jian_upfile1.layoutManager = GridLayoutManager(activity,3)
        renovatedFileList.addAll(message.renovatedFiles)
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
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(activity)
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
                                    LoadingDialog.showDialogForLoading(activity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                        if (json.code==0){
                                            renovatedFileList.removeAt(position)
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
                            .start(activity!!)


                }
            }
        }
        rlv_fan_jian_upfile.adapter = schxtpFj
        var pathList = ArrayList<String>()
        var photoAdapter = PhotoAdapter(context, pathList)
        rlv_fan_jian_upfile1.adapter = photoAdapter
        rlv_fan_jian_upfile1.addOnItemTouchListener(RecyclerItemClickListener(activity, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(context!!, this@ZjdglFragment,224)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(context!!, this@ZjdglFragment,20)//context!!
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
        if (AppCache.getInstance().duties==1){//AppCache.getInstance().type==5||
            bt_upload_fan_jian.visibility = View.GONE
            bt_lsbc_fan_jian.visibility = View.GONE
            bt_delete_fan_jian.visibility = View.GONE
            rlv_fan_jian_upfile1.visibility = View.GONE
        }
        bt_upload_fan_jian.setOnClickListener {
            //TimeUtil.getDateAfter(0).toString()
            if (renovatedFileList.size>0){
                message.xftime = TimeUtil.getDateAfter(0).toString()
                message.zhen = message.ylEntity.townname
                message.xzqmc = message.ylEntity.xzqmc
                message.code = message.ylEntity.code
                message.ylId = message.ylEntity.objectid.toLong()//gid
                message.remark = tv_remark_fan_jian.text.toString()
                message.qutype = 2
                message.filetype = 1
                message.renovatedFiles = renovatedFileList
                mPresenter.getFanJianAdd(message)
            }else{
                ToastUtils.showShort("请上传照片")
            }
        }
        bt_lsbc_fan_jian.setOnClickListener {
            message.zhen = message.ylEntity.townname
            message.xzqmc = message.ylEntity.xzqmc
            message.code = message.ylEntity.code
            message.ylId = message.ylEntity.objectid.toLong()
            message.remark = tv_remark_fan_jian.text.toString()
            message.qutype = 1
            message.filetype = 1
            message.renovatedFiles = renovatedFileList
            mPresenter.getFanJianAdd(message)
        }
        bt_delete_fan_jian.setOnClickListener {
            LoadingDialog.cancelDialogForLoading()
            val content = TextView(activity)
            if (AppCache.getInstance().userId.toInt() !=message.userId.toInt()){
                content.text = "是否删除？(此点位非本账号添加)"
            }else{
                content.text = "是否删除？"
            }

//                FileUtils.openFile()//AppConstant.getFileProvider()
            SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("删除固定点位")
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

    fun setImageMarker(enviorSupvsEntity : EnviorFileFhEntity,type:String,
                       xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal) { //,int mid  显示聚合图片
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_image_marker, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_image_marker)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_image_marker_position)
        val path = enviorSupvsEntity.path
        val replace = path.replace("\\", "/")
        val imagePath = ApiConstants.RELEASE_URL + replace
        itemMarkerPosition.text = enviorSupvsEntity.counts.toString()
        val location = enviorSupvsEntity.location
        if (!location.equals("")){
            val center1 = getCenter(location)
            //绘制marker
            Glide.with(this).load(imagePath)//
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            //展示图片
                            ivItemMarker.setImageDrawable(resource);
//                        textView.setText(list.get(I).getGasName() + "");
                            var bitmap = convertViewToBitmap(view1);
                            val zoom = aMap!!.cameraPosition.zoom
                            if (zoom < 9 && type.equals("1")) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom < 12 && type.equals("2") && zoom >= 9) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom < 15 && type.equals("3") && zoom >= 12) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom >= 15 && type.equals("4") && zoom >= 15) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            }


                        }

                        /*@Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //展示图片
                            imageView.setImageDrawable(resource);
                            textView.setText(list.get(I).getGasName() + "");
                            Bitmap bitmap = convertViewToBitmap(view);
                            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .position..;
                            aMap.addMarker(markerOption);
                        }*/
                    });
        }
        aMap!!.setOnMarkerClickListener { marker ->
            val snippet = marker.snippet
            val intent = Intent(activity, ImageListActivity::class.java)
            intent.putExtra("snippet",snippet)
            intent.putExtra("sjfl","")
            startActivity(intent)
            true
        }
    }
    fun setWtMarker(enviorSupvsEntity : EnviorFileFhEntity,type:String,
                    xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal) { //,int mid
        if (aMap!=null){

        }
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.view_marker, null)

        val textView = view1!!.findViewById<View>(R.id.bv_view_marker) as BubbleView
        val counts = enviorSupvsEntity.counts
        if (counts<50){
            textView.setColor(Color.argb(255, 255, 225, 219))
        }else if (counts<150){
            textView.setColor(Color.argb(255, 255, 196, 179))
        }else if (counts<300){
            textView.setColor(Color.argb(255, 255, 153, 133))
        } else if (counts<600){
            textView.setColor(Color.argb(255, 245, 117, 103))
        }else if (counts<1000){
            textView.setColor(Color.argb(255, 230, 69, 70))
        }else if (counts<10000){
            textView.setColor(Color.argb(255, 184, 9, 9))
        }else if (counts>10000){
            textView.setColor(Color.argb(255, 138, 8, 8))
        }
//        textView.setTextColor(Color.BLACK)
        textView.setText(enviorSupvsEntity.name+":"+counts)
        var bitmap = convertViewToBitmap(view1)

        val zoom = aMap!!.cameraPosition.zoom
        val location = enviorSupvsEntity.location
        if (!location.equals("")){
            val center1 = getCenter(location)
            if (AppCache.getInstance().code.length==9){
                if (zoom<13&&type.equals("2")){
                    val addMarker1 = aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                    markers.add(addMarker1)
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){//15
                    val addMarker1 = aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                    markers.add(addMarker1)
                }
            }
            if (AppCache.getInstance().code.equals("")||AppCache.getInstance().code.equals("110112")){
                if (zoom<9&&type.equals("1")){
                    val addMarker1 = aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                    markers.add(addMarker1)

                }else if (zoom<13&&type.equals("2")&&zoom>=9){
                    val addMarker1 = aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                    markers.add(addMarker1)
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){//15
                    val addMarker1 = aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                    markers.add(addMarker1)
                }
            }

            aMap!!.setOnMarkerClickListener { marker ->
                val position = marker.position
                aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(position, zoom+5f, 0f, 0f)))

                true
            }
        }


    }
    //巡查所使用
    fun setWtMarker(enviorSupvsEntity : FwglJhBean,type:String,
                    xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal) { //,int mid
        if (aMap!=null){

        }
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.view_marker, null)

        val textView = view1!!.findViewById<View>(R.id.bv_view_marker) as BubbleView
        val counts = enviorSupvsEntity.object4
        if (counts<50){
            textView.setColor(Color.argb(255, 255, 225, 219))
        }else if (counts<150){
            textView.setColor(Color.argb(255, 255, 196, 179))
        }else if (counts<300){
            textView.setColor(Color.argb(255, 255, 153, 133))
        } else if (counts<600){
            textView.setColor(Color.argb(255, 245, 117, 103))
        }else if (counts<1000){
            textView.setColor(Color.argb(255, 230, 69, 70))
        }else if (counts<10000){
            textView.setColor(Color.argb(255, 184, 9, 9))
        }else if (counts>10000){
            textView.setColor(Color.argb(255, 138, 8, 8))
        }
//        textView.setTextColor(Color.BLACK)
        textView.setText(enviorSupvsEntity.object1+":"+counts)
        var bitmap = convertViewToBitmap(view1);

        val zoom = aMap!!.cameraPosition.zoom
        val location = enviorSupvsEntity.object3
        val code = AppCache.getInstance().code
        if (!location.equals("")){
            val center1 = getCenter(location)
            if (code.length==9){
                if (zoom<13&&type.equals("2")){
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.object1 + "-" + enviorSupvsEntity.object3) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){//15
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.object1 + "-" + enviorSupvsEntity.object3) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }
            }
            if (code.equals("")||code.equals("110112")){
                if (zoom<9&&type.equals("1")){
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.object1 + "-" + enviorSupvsEntity.object3) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }else if (zoom<13&&type.equals("2")&&zoom>=9){
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.object1 + "-" + enviorSupvsEntity.object3) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){//15
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.object1 + "-" + enviorSupvsEntity.object3) //                .period(mid)//添加markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }
            }
            /*if (zoom < 9 && type.equals("1")) {
                aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            } else if (zoom < 12 && type.equals("2") && zoom >= 9) {
                aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            } else if (zoom < 15 && type.equals("3") && zoom >= 12) {
                aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            } else if (zoom >= 15 && type.equals("4") && zoom >= 15) {
                aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            }*/

            aMap!!.setOnMarkerClickListener { marker ->
                val position = marker.position
                aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(position, zoom+5f, 0f, 0f)))
                val snippet = marker.snippet
                val intent = Intent(activity, ImageListActivity::class.java)
                intent.putExtra("snippet",snippet)
                startActivity(intent)
                true
            }
        }


    }
    //出租使用
    fun setMarker(res: Int, enviorSupvsEntity: XzqInfoEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(activity)
        val view1: View = mInflater.inflate(R.layout.item_marker1, null)
        val rlMarker = view1.findViewById<RelativeLayout>(R.id.rlt_item_marker)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        val countTv = view1.findViewById<TextView>(R.id.item_marker_position2)
        ivItemMarker.setImageResource(res)

        val para = ivItemMarker.getLayoutParams()
        var counts = enviorSupvsEntity.flowcount
        /*if (mode==MODE_CZ){//出租房间数
            counts = enviorSupvsEntity.roomcount
        }*/
        if (counts==0){
            para.height = 0
            para.width = 0
            rlMarker.visibility = View.INVISIBLE
        }else if (counts < 100) {
            para.height = 120
            para.width = 120
            rlMarker.visibility = View.VISIBLE
        }else if (counts < 1000) {
            para.height = 140
            para.width = 140
            rlMarker.visibility = View.VISIBLE
        }else if (counts < 10000) {
            para.height = 160
            para.width = 160
            rlMarker.visibility = View.VISIBLE
        }else if (counts > 10000) {
            para.height = 180
            para.width = 180
            rlMarker.visibility = View.VISIBLE
        }
        ivItemMarker.setLayoutParams(para)

        itemMarkerPosition.text = enviorSupvsEntity.xzqmc
        countTv.text = counts.toString()


        val bitmap = convertViewToBitmap(view1)
//        view1.setOnClickListener { Toast.makeText(this, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
//                .position(LatLng(wd, jd))
                .position(getCenter(enviorSupvsEntity.center))
                .snippet("") //                .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->
            true
        }
    }
    //宅基地使用
    fun setMarker(res: Int, enviorSupvsEntity: ApplyCountEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(activity)
        val view1: View = mInflater.inflate(R.layout.item_marker1, null)
        val rlMarker = view1.findViewById<RelativeLayout>(R.id.rlt_item_marker)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        val countTv = view1.findViewById<TextView>(R.id.item_marker_position2)
        ivItemMarker.setImageResource(res)

        val para = ivItemMarker.getLayoutParams()
        var counts = enviorSupvsEntity.counts
        if (counts==0){
            para.height = 0
            para.width = 0
            rlMarker.visibility = View.INVISIBLE
        }else if (counts < 100) {
            para.height = 120
            para.width = 120
            rlMarker.visibility = View.VISIBLE
        }else if (counts < 1000) {
            para.height = 140
            para.width = 140
            rlMarker.visibility = View.VISIBLE
        }else if (counts < 10000) {
            para.height = 160
            para.width = 160
            rlMarker.visibility = View.VISIBLE
        }else if (counts > 10000) {
            para.height = 180
            para.width = 180
            rlMarker.visibility = View.VISIBLE
        }
        ivItemMarker.setLayoutParams(para)

        itemMarkerPosition.text = enviorSupvsEntity.name
        countTv.text = counts.toString()


        val bitmap = convertViewToBitmap(view1)
//        view1.setOnClickListener { Toast.makeText(this, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
//                .position(LatLng(wd, jd))
                .position(getCenter(enviorSupvsEntity.location))
                .snippet("") //                .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->
            true
        }
    }


    //新巡查热力图
    fun setXcMarker( j: String, wd: Double, jd: Double, res: Int, mapEntity: FwglJhBean) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_marker2, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        ivItemMarker.setImageResource(res)

        val para = ivItemMarker.getLayoutParams()
            val counts = mapEntity.object4

        if (counts==0){
            para.height = 0
            para.width = 0
            ivItemMarker.visibility = View.INVISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 10) {
            para.height = 120
            para.width = 120
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 100) {
            para.height = 140
            para.width = 140
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 1000) {
            para.height = 160
            para.width = 160
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts > 1000) {
            para.height = 180
            para.width = 180
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }

        if (mapEntity.object4<=0){
            view1.visibility = View.GONE
        }else{
            view1.visibility = View.VISIBLE
            ivItemMarker.setImageDrawable(activity!!.getDrawable(R.drawable.ic_hbjc_dcl_zs))
        }
            itemMarkerPosition.text=mapEntity.object1+mapEntity.object4

        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(context, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
                .position(LatLng(wd, jd))
                .snippet(j.toString() + "") //                .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->

            true
        }
    }
    //新人居环境热力图
    fun setRjhjMarker( j: String, wd: Double, jd: Double, res: Int, mapEntity: EnviorFileFhEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_marker2, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        ivItemMarker.setImageResource(res)

        val para = ivItemMarker.getLayoutParams()
        val counts = mapEntity.counts

        if (counts==0){
            para.height = 0
            para.width = 0
            ivItemMarker.visibility = View.INVISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 10) {
            para.height = 120
            para.width = 120
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 100) {
            para.height = 140
            para.width = 140
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 1000) {
            para.height = 160
            para.width = 160
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts > 1000) {
            para.height = 180
            para.width = 180
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }

        if (mapEntity.counts<=0){
            view1.visibility = View.GONE
        }else{
            view1.visibility = View.VISIBLE
            ivItemMarker.setImageDrawable(activity!!.getDrawable(R.drawable.ic_hbjc_dcl_zs))
        }
        itemMarkerPosition.text=mapEntity.name+mapEntity.counts

        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(context, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
                .position(LatLng(wd, jd))
                .snippet(j.toString() + "") //                .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->

            true
        }
    }
    //新出租热力图
    fun setCzMarker( j: String, wd: Double, jd: Double, res: Int, mapEntity: XzqInfoEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_marker2, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        ivItemMarker.setImageResource(res)

        val para = ivItemMarker.getLayoutParams()
        var counts = mapEntity.flowcount
        if (mode == MODE_CZ){
            counts = mapEntity.roomcount
        }
        if (counts==0){
            para.height = 0
            para.width = 0
            ivItemMarker.visibility = View.INVISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 100) {
            para.height = 120
            para.width = 120
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 1000) {
            para.height = 140
            para.width = 140
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 10000) {
            para.height = 160
            para.width = 160
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts > 10000) {
            para.height = 180
            para.width = 180
            ivItemMarker.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }

        if (counts<=0){
            view1.visibility = View.GONE
        }else{
            view1.visibility = View.VISIBLE
            ivItemMarker.setImageDrawable(activity!!.getDrawable(R.drawable.ic_hbjc_dcl_zs))
        }
        itemMarkerPosition.text=mapEntity.xzqmc+counts

        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(context, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        if (counts>0){
            val addMarker1 = aMap!!.addMarker(MarkerOptions()
                    .position(LatLng(wd, jd))
                    .snippet(j.toString() + "") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true))
            markers.add(addMarker1)
        }
        aMap!!.setOnMarkerClickListener { marker ->

            true
        }
    }
    //新宅基地热力图
    fun setZjdMarker( j: String, wd: Double, jd: Double, res: Int, mapEntity: ApplyCountEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_marker2, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        ivItemMarker.setImageResource(res)

        val para = ivItemMarker.getLayoutParams()
        val counts = mapEntity.counts

        if (counts==0){
            para.height = 0
            para.width = 0
            view1.visibility = View.INVISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 100) {
            para.height = 120
            para.width = 120
            view1.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 1000) {
            para.height = 140
            para.width = 140
            view1.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts < 10000) {
            para.height = 160
            para.width = 160
            view1.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }else if (counts > 10000) {
            para.height = 180
            para.width = 180
            view1.visibility = View.VISIBLE
            ivItemMarker.setLayoutParams(para)
        }

        if (mapEntity.counts<=0){
            view1.visibility = View.GONE
        }else{
            view1.visibility = View.VISIBLE
            ivItemMarker.setImageDrawable(activity!!.getDrawable(R.drawable.ic_hbjc_dcl_zs))
        }
        itemMarkerPosition.text=mapEntity.name+mapEntity.counts

        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(context, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
                .position(LatLng(wd, jd))
                .snippet(j.toString() + "") //                .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->

            true
        }
    }
    //新宅基地热力图
    fun setYztMarker( j: String, wd: Double, jd: Double, res: Int, mapEntity: XzqInfoEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_marker2, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        val bv_view_marker = view1.findViewById<BubbleView>(R.id.bv_view_marker)
        val rl_item_marker2_yuan = view1.findViewById<RelativeLayout>(R.id.rl_item_marker2_yuan)
        if (mapEntity.code.length != 9){
            ivItemMarker.setImageResource(res)
        }

        val para = ivItemMarker.getLayoutParams()
        val counts = mapEntity.counts
        if (mapEntity.code.length!=15){
            bv_view_marker.visibility = View.GONE
            rl_item_marker2_yuan.visibility = View.VISIBLE
            /*if (counts==0){
           para.height = 0
           para.width = 0
           view1.visibility = View.INVISIBLE
           ivItemMarker.setLayoutParams(para)
       }else */if (counts < 3) {
                para.height = 110
                para.width = 110
                view1.visibility = View.VISIBLE
                ivItemMarker.setLayoutParams(para)
            }else if (counts < 6) {
                para.height = 130
                para.width = 130
                view1.visibility = View.VISIBLE
                ivItemMarker.setLayoutParams(para)
            }else if (counts < 8) {
                para.height = 140
                para.width = 140
                view1.visibility = View.VISIBLE
                ivItemMarker.setLayoutParams(para)
            }else if (counts < 10) {
                para.height = 150
                para.width = 150
                view1.visibility = View.VISIBLE
                ivItemMarker.setLayoutParams(para)
            }else if (counts >= 10) {
                para.height = 160
                para.width = 160
                view1.visibility = View.VISIBLE
                ivItemMarker.setLayoutParams(para)
            }

        }else{
            bv_view_marker.visibility = View.VISIBLE
            rl_item_marker2_yuan.visibility = View.GONE
            bv_view_marker.setColor(Color.argb(255, 11, 163, 251))
            bv_view_marker.setText(mapEntity.xzqmc)
        }

        /*if (mapEntity.counts<=0){
            view1.visibility = View.GONE
        }else{*/
            view1.visibility = View.VISIBLE
            ivItemMarker.setImageDrawable(activity!!.getDrawable(R.drawable.ic_hbjc_dcl_zs))
//        }
        itemMarkerPosition.setTextColor(Color.WHITE)
        /*if (mapEntity.counts<=1){
            itemMarkerPosition.text=mapEntity.xzqmc
        }else{*/
            itemMarkerPosition.text=mapEntity.xzqmc+"\n"+mapEntity.counts
//        }

        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(context, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
                .position(LatLng(wd, jd))
                .snippet(mapEntity.code + "-" + mapEntity.xzqmc) // j.toString()               .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->
            val position = marker.position
            val snippet = marker.snippet
            val split = snippet.split("-")
            if (split[0].length==15){//村
                cunCode = split[0]
//                AppCache.getInstance().code = cunCode
//                rl_frag_zjdgl_yzt.visibility = View.VISIBLE   ||appName.equals("黄山店")
                if (AppCache.getInstance().type==1||appName.equals("黄山店")){
                    rl_frag_zjdgl_yd.visibility = View.VISIBLE
                    rl_frag_zjdgl_zjd.visibility = View.VISIBLE
                    rl_frag_zjdgl_cy.visibility = View.VISIBLE
                    rl_frag_zjdgl_shzl.visibility = View.VISIBLE
                    rl_frag_zjdgl_czsjsx.visibility = View.GONE
                    onClick(bt_frag_zjdgl_shzl)
                }else{
                    onClick(bt_frag_zjdgl_shzl)
                }
                HomePageFragment.tvFragHomepageLeft?.setText(split[1])
//                initTcYzt()
//                ToastUtils.showShort(snippet)
            }else if (split[0].length==6){//市

                aMap?.addMarker(MarkerOptions().position(marker.position))
                aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(marker.position, 10.5f, 0f, 0f)))
            }else if (split[0].length==9){//区
                aMap?.addMarker(MarkerOptions().position(marker.position))
                aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(marker.position, 10.8f, 0f, 0f)))
            }else if (split[0].length==12){//镇
                aMap?.addMarker(MarkerOptions().position(marker.position))
                aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(marker.position, 13f, 0f, 0f)))
            }
            true
        }
    }
    fun convertViewToBitmap(view: View): Bitmap {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }

    companion object {

        private var aMapa: AMap? = null
        private var  addPolyline: Polyline? = null//高亮显示
        private var mPolylineOptions: PolylineOptions? = null
        var supl_frag_zjdgl1: SlidingUpPanelLayout? = null
        var polylines = ArrayList<Polyline>()
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
                            addPolyline = aMapa!!.addPolyline(mPolylineOptions)
                            polylines.add(addPolyline!!)
                        }
                    }else{
                        val latList = getLatList(split[i])
                        if (i == 0){
//                        aMap!!.animateCamera(CameraUpdateFfragory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
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
                        addPolyline = aMapa!!.addPolyline(mPolylineOptions)
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
    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_zjdgl.getMap()
            aMapa = aMap
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
        aMap!!.uiSettings.logoPosition = -1

    }
    /**
     * 地图方法
     */
    private fun setAMap() { //设置默认定位按钮是否显示
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false // 设置比例尺
        aMap!!.uiSettings.isZoomControlsEnabled = false
        aMap!!.setMapLanguage(AMap.CHINESE)
        aMap!!.setOnMapClickListener(this)
        mPolygonOptions = PolygonOptions()
//        aMap!!.addTileOverlay(opt_tdtnN)

//        aMap!!.// = 18f

    }

    private fun clearMap() {
//        clearAllMarker()
        mLatLngs.clear()
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
        val markerList = aMap!!.mapScreenMarkers
        for (m in markerList) {
            m.remove()
        }
        mPolygon?.remove()
    }

    private fun clearAllMarker() {
        for (m in markers) {//清除所有热力图点位
            m.remove()
        }
        /*val markerList = aMap!!.mapScreenMarkers
        for (m in markerList) {
            m.remove()
        }*/
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

    var tabLayout: TabLayout? = null
    var tabPosition = 0
    val zjdYztDcFrag = YztDcFragment.newInstance(0)
    private fun setYztPager(){
        var tabList = ArrayList<String>()
        tabList.add("点查")
//        tabList.add("框查")
        var fragList = ArrayList<Fragment>()
        fragList.add(Fragment())//zjdYztDcFrag
        val sectionsPagerAdapter = SectionsPagerAdapter(fragList,tabList, childFragmentManager)//创建viewpager切换activity!!.supportFragmentManager
        vp_frag_yzt.adapter = sectionsPagerAdapter
        vp_frag_yzt.offscreenPageLimit = 0

    }
    val zjdYdFrag = ZjdYdFragment.newInstance(0)
//    val zjdFjFrag = ZjdFjFragment.newInstance(0)
    val zjdXcFrag = ZjdXcFragment.newInstance(0)
    val shzlLdrkFrag = ShzlLdrkFragment.newInstance(0)
    //给tab赋值，关联viewpager  宅基地
    private fun seTabZjdPager() {
        var tabList = ArrayList<String>()
        tabList.add("宅基地")
//        tabList.add("翻建")
        tabList.add("房屋")//巡查
        tabList.add("物业")
//        tl_frag_zjd.addTab(tl_frag_zjd.newTab().setText("宅基地"))
//        tl_frag_zjd.addTab(tl_frag_zjd.newTab().setText("巡查"))
//        tl_frag_zjd.addTab(tl_frag_zjd.newTab().setText("物业"))
        var fragList = ArrayList<Fragment>()
        fragList.add(Fragment())//zjdYdFrag
//        fragList.add(zjdFjFrag)
        fragList.add(Fragment())//zjdXcFrag
        fragList.add(Fragment())//shzlLdrkFrag
        val sectionsPagerAdapter = SectionsPagerAdapter(fragList,tabList, childFragmentManager)//创建viewpager切换
//        val sectionsPagerAdapter = TestViewPagerAdapter(childFragmentManager,fragList)//创建viewpager切换activity!!.supportFragmentManager
        vp_frag_zjd.adapter = sectionsPagerAdapter
        tl_frag_zjd.setupWithViewPager(vp_frag_zjd)
        vp_frag_zjd.offscreenPageLimit = fragList.size
        tl_frag_zjd.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectZjd(tab)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectZjd(tab)
            }
        })
    }

    val zjdCyyqFrag = ZjdCyyqFragment.newInstance(0)
    val zjdNyyqFrag = ZjdNyyqFragment.newInstance(0)
    val zjdWlFrag = ZjdWlFragment.newInstance(0)
    val zjdDkfFrag = ZjdDkfFragment.newInstance(0)
    //给tab赋值，关联viewpager  产业
    private fun seTabCyPager() {
        var tabList = ArrayList<String>()
        /*tabList.add("城镇社区")//产业园区
        tabList.add("农村社区")//文旅
        tabList.add("产业园区")//农业园区
        tabList.add("农业园区")//待开发*/
        tabList.add("产业地图")
        tabList.add("园区经营")
        tabList.add("产业招商")
//        tabList.add("待开发")
//        tl_frag_cy.addTab(tl_frag_cy.newTab().setText("产业园区"))
//        tl_frag_cy.addTab(tl_frag_cy.newTab().setText("农业园区"))
//        tl_frag_cy.addTab(tl_frag_cy.newTab().setText("待开发"))
        var fragList = ArrayList<Fragment>()
        fragList.add(Fragment())//zjdCyyqFrag
        fragList.add(Fragment())//zjdWlFrag
        fragList.add(Fragment())//zjdNyyqFrag
//        fragList.add(Fragment())//zjdDkfFrag
        val sectionsPagerAdapter = SectionsPagerAdapter(fragList,tabList, childFragmentManager)//创建viewpager切换
//        val sectionsPagerAdapter = TestViewPagerAdapter(childFragmentManager,fragList)//创建viewpager切换activity!!.supportFragmentManager
        vp_frag_cy.adapter = sectionsPagerAdapter
        tl_frag_cy.setupWithViewPager(vp_frag_cy)
        vp_frag_cy.offscreenPageLimit = tabList.size
        tl_frag_cy.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectCy(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectCy(tab)
            }
        })


    }
    //给tab赋值，关联viewpager  土现
    val zjdTdlyFrag = ZjdTdlyFragment.newInstance(0)
    val zjdTdsyFrag = ZjdTdsyFragment.newInstance(0)
    val zjdTdqsFrag = ZjdTdqsFragment.newInstance(0)
    val zjdTdghFrag = ZjdTdghFragment.newInstance(0)
    private fun seTabTxPager() {
        tl_frag_tx.setOnTabSelectedListener(this)
        var tabList = ArrayList<String>()
        tabList.add("土地利用现状")//土地利用
        tabList.add("三块田")//土地规划
//        tabList.add("土地权属")
        tabList.add("村庄规划")
        /*tl_frag_tx.addTab(tl_frag_tx.newTab().setText("土地利用"))
        tl_frag_tx.addTab(tl_frag_tx.newTab().setText("三块田"))
        tl_frag_tx.addTab(tl_frag_tx.newTab().setText("土地权属"))*/
//        tl_frag_tx.addTab(tl_frag_tx.newTab().setText("空间规划"))
        var fragList = ArrayList<Fragment>()
//        fragList.add(zjdTdlyFrag)
        fragList.add(Fragment())
        fragList.add(zjdTdsyFrag)
//        fragList.add(zjdTdqsFrag)
        fragList.add(zjdTdghFrag)
        val sectionsPagerAdapter = SectionsPagerAdapter(fragList,tabList, childFragmentManager)//创建viewpager切换activity!!.supportFragmentManager
//        val sectionsPagerAdapter = TestViewPagerAdapter(childFragmentManager,fragList)//创建viewpager切换activity!!.supportFragmentManager
        vp_frag_tx.adapter = sectionsPagerAdapter
//        tl_frag_tx.setUpTitle(TxTabTitleData().titleList)
        tl_frag_tx.setupWithViewPager(vp_frag_tx)
        vp_frag_tx.offscreenPageLimit = 2
        tl_frag_tx.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectTx(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                /*val viewById = tab!!.view.findViewById(R.id.tab_text) as TextView
                viewById.setTextColor(Color.parseColor("#333333"))*/
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectTx(tab)
            }

        })
        zjdTdlyFrag.setOnZjdTdlyLister(object: ZjdTdlyFragment.OnZjdTdlyLister{
            override fun onClickTdly(timeLineBean: TdlyVO, position: Int) {
                clearAllMarker()
                aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(getCenter(timeLineBean.center), 19f))
                aMap?.addMarker(MarkerOptions().position(getCenter(timeLineBean.center)))
                queryByPoint(getCenter(timeLineBean.center))
            }
            override fun onClickYztSkt(timeLineBean: YztSktVO, position: Int) {
                clearAllMarker()
                aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(getCenter(timeLineBean.center), 19f))
                aMap?.addMarker(MarkerOptions().position(getCenter(timeLineBean.center)))
                queryByPoint(getCenter(timeLineBean.center))
            }

            override fun onClickQs(timeLineBean: QsVO, position: Int) {
                clearAllMarker()
                aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(getCenter(timeLineBean.center), 19f))
                aMap?.addMarker(MarkerOptions().position(getCenter(timeLineBean.center)))
                queryByPoint(getCenter(timeLineBean.center))
            }

            override fun onClickGh(timeLineBean: GhVO, position: Int) {
                clearAllMarker()
                aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(getCenter(timeLineBean.center), 19f))
                aMap?.addMarker(MarkerOptions().position(getCenter(timeLineBean.center)))
                queryByPoint(getCenter(timeLineBean.center))
            }

        })
    }

    var shzlGddwMarker1: Marker? = null
    val zjdLdrkFrag = ZjdLdrkFragment.newInstance(0)
    val shzlRjhjFrag = ShzlRjhjFragment.newInstance(0)
    val shzlWfxcFrag = ShzlWfxcFragment.newInstance(0)
    val shzlYqglFrag = ShzlYqglFragment.newInstance(0)
    val shzlWjdcFrag = ShzlWjdcFragment.newInstance(0)
    val bcqhFrag = BcqhFragment.newInstance(0)
    //给tab赋值，关联viewpager  社会治理
    private fun seTabShzlPager() {
        shzlRjhjFrag.setOnClickShzlLister(object:ShzlRjhjFragment.OnClickShzlLister{
            override fun onClick(position: String) {
                isShow = 4
                mPresenter.getRjhjjcPoint(position)
                 val center1 = getCenter(position)
                shzlGddwMarker1?.remove()
                 shzlGddwMarker1 = aMap?.addMarker(MarkerOptions().position(center1))
                 aMap!!.animateCamera(CameraUpdateFactory.changeLatLng(center1))
            }
        })
        var tabList = ArrayList<String>()
        tabList.add("人口就业")//流动人口
        tabList.add("土地利用")//
//        tabList.add("土地权属")//
        tabList.add("产业结构")//人居环境
        tabList.add("资产经营")//无违建
        tabList.add("收入支出")//接诉即办
        tabList.add("发展意愿")
        tabList.add("监测户")//百村千户

        /*tl_frag_shzl.addTab(tl_frag_shzl.newTab().setText("流动人口"))
        tl_frag_shzl.addTab(tl_frag_shzl.newTab().setText("人居环境"))
        tl_frag_shzl.addTab(tl_frag_shzl.newTab().setText("无违建"))
        tl_frag_shzl.addTab(tl_frag_shzl.newTab().setText("接诉即办"))*/
        var fragList = ArrayList<Fragment>()
        fragList.add(zjdLdrkFrag)//zjdLdrkFrag
        fragList.add(zjdTdlyFrag)//zjdTdlyFrag
        fragList.add(shzlRjhjFrag)//shzlRjhjFrag
        fragList.add(shzlWfxcFrag)//shzlWfxcFrag
        fragList.add(shzlYqglFrag)//shzlYqglFrag
        fragList.add(shzlWjdcFrag)//shzlWjdcFrag
        fragList.add(bcqhFrag)//shzlWjdcFrag

        val sectionsPagerAdapter = SectionsPagerAdapter(fragList,tabList, childFragmentManager)//创建viewpager切换
//        val sectionsPagerAdapter = TestViewPagerAdapter(childFragmentManager,fragList)//创建viewpager切换activity!!.supportFragmentManager
        vp_frag_shzl.adapter = sectionsPagerAdapter
        tl_frag_shzl.setupWithViewPager(vp_frag_shzl)
        vp_frag_shzl.offscreenPageLimit = fragList.size
        tl_frag_shzl.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
//                Log.e("tl_frag_shzl","${tab!!.text.toString()}1")
                selectShzl(tab)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                Log.e("tl_frag_shzl","${tab!!.text.toString()}2")
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Log.e("tl_frag_shzl","${tab!!.text.toString()}3")
                selectShzl(tab)
            }
        })
        /*tlt_frag_zjdgl_rjhj.addTab(tlt_frag_zjdgl_rjhj.newTab().setText("人居环境"))
        tlt_frag_zjdgl_rjhj.addTab(tlt_frag_zjdgl_rjhj.newTab().setText("固定点位"))
        val linearLayout = tlt_frag_zjdgl_rjhj.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        linearLayout.dividerPadding = 16//设置分隔器两端的填充
        linearLayout.dividerDrawable = ContextCompat.getDrawable(activity!!,
                R.drawable.tablayout_divider_vetical)
        tlt_frag_zjdgl_rjhj.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text?.equals("人居环境")!!){      //人居环境小标签
                    mode = MODE_RJHJ_RJHJ
                    click_mode = POINT_CLICK

                    rl_frag_zjdgl_add.visibility = View.VISIBLE
                    rl_frag_zjdgl_update.visibility = View.GONE
                    tv_frag_zjgl_rjhj_add.visibility = View.GONE
                    shzlRjhjFrag.shzlRjhjTab()
                    //这里需要调用列表切换  shzlRjhjFrag
                }else if (tab?.text?.equals("固定点位")!!){ //固定点位小标签
                    mode = MODE_RJHJ_GDDW
                    click_mode = MODE_NORMAL
                    rl_frag_zjdgl_add.visibility = View.VISIBLE
                    rl_frag_zjdgl_update.visibility = View.VISIBLE
                    tv_frag_zjgl_rjhj_add.visibility = View.VISIBLE
                    shzlRjhjFrag.shzlGddwTab()
                }
            }
        })*/

    }


    private fun selectZjd(tab: TabLayout.Tab?) {
        tabLayout = tl_frag_zjd
        tabPosition = tab!!.position
        vp_frag_zjd.requestLayout()

        bt_frag_zjdgl_point.isActivated = false
        bt_frag_zjdgl_gddw_add.isActivated = false
        bt_frag_zjdgl_kuang.isActivated = false
        supl_frag_zjdgl1?.setScrollableView(null)
        ll_frag_shzl_rjhj.visibility = View.GONE
        rl_frag_zjdgl_point.visibility = View.GONE
        rl_frag_zjdgl_gddw_add.visibility = View.GONE
        rl_frag_zjdgl_add.visibility = View.GONE
        rl_frag_zjdgl_update.visibility = View.GONE
        rl_frag_zjdgl_kuang.visibility = View.GONE
        rl_frag_zjdgl_search.visibility = View.GONE
        tlcl_frag_zjdgl_tl.visibility = View.GONE
        rl_frag_zjdgl_clear.visibility = View.GONE
        click_mode = MODE_NORMAL
        if (tab!!.text.toString().equals("宅基地")) {
            rl_frag_zjdgl_clear.visibility = View.VISIBLE
            /*if (!AppCache.getInstance().code.equals("110111009022")){
                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                rl_frag_zjdgl_gddw_add.visibility = View.VISIBLE
            }else{*/
                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
//            }

            zjdYdFrag.diaoyong()
            mode = MODE_YD
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            val arrayList = ArrayList<TuliColorBean>()
            arrayList.add(TuliColorBean("未交房", Color.parseColor("#E0E0E0")))//FF5E7A
            arrayList.add(TuliColorBean("已交房", Color.parseColor("#5EFF94")))
            arrayList.add(TuliColorBean("正在改造", Color.parseColor("#5ECAFF")))
            arrayList.add(TuliColorBean("已完成改造", Color.parseColor("#FFE45E")))
            tlcl_frag_zjdgl_tl.setData(arrayList)
            if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                tlcl_frag_zjdgl_tl.visibility = View.GONE
            }else{
                tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
            }
            rl_frag_zjdgl_point.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
//            tabTc("天地图", 3)
            tabTc("影像图", 3)
            tabTc("院落", 1)

        } else if (tab!!.text.toString().equals("翻建")) {
            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
//            zjdFjFrag.diaoyong()
            mode = MODE_FJ
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            rl_frag_zjdgl_point.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
//            tabTc("天地图", 3)
            tabTc("影像图", 3)
            tabTc("翻建", 1)

        } else if (tab!!.text.toString().equals("巡查")) {
            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            zjdXcFrag.getData()
            mode = MODE_XC
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            rl_frag_zjdgl_point.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
//            tabTc("天地图", 3)
            tabTc("影像图", 3)
            tabTc("房屋巡查", 1)

        }else if (tab!!.text.toString().equals("房屋")) {
            rl_frag_zjdgl_clear.visibility = View.GONE
//            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            zjdXcFrag.getData()
            mode = MODE_FW
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
//            tabTc("天地图", 3)
            tabTc("影像图", 3)
            tabTc("新院落", 2)
            tabTc("房屋", 2)

        } else if (tab!!.text.toString().equals("物业")) {
            rl_frag_zjdgl_clear.visibility = View.VISIBLE
//            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            shzlLdrkFrag.diaoyong()
            mode = MODE_CZ
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            rl_frag_zjdgl_point.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
//            tabTc("天地图", 3)
            tabTc("影像图", 3)
            tabTc("物业", 1)

        }
    }
    private fun selectCy(tab: TabLayout.Tab?) {
        tabLayout = tl_frag_cy
        tabPosition = tab!!.position
        vp_frag_cy.requestLayout()
        bt_frag_zjdgl_point.isActivated = false
        bt_frag_zjdgl_gddw_add.isActivated = false
        bt_frag_zjdgl_kuang.isActivated = false
        supl_frag_zjdgl1?.setScrollableView(null)
        ll_frag_shzl_rjhj.visibility = View.GONE
        rl_frag_zjdgl_point.visibility = View.GONE
        rl_frag_zjdgl_gddw_add.visibility = View.GONE
        rl_frag_zjdgl_add.visibility = View.GONE
        rl_frag_zjdgl_update.visibility = View.GONE
        rl_frag_zjdgl_kuang.visibility = View.GONE
        rl_frag_zjdgl_search.visibility = View.GONE
        rl_frag_zjdgl_clear.visibility = View.GONE
        click_mode = MODE_NORMAL
        supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED

        if (tab!!.text.toString().equals("产业地图")) {//社区
            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            mode = MODE_NYYQ
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            zjdNyyqFrag.diaoyong()
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
            tabTc("", 1)
            tabTc("规划",2)
//            tabTc("天地图", 3)
            /*tabTc("村庄规划", 3)
            tabTc("城镇社区", 2)
            tabTc("农村社区新", 2)*/
//            tabTc("农业园区面", 2)
            val arrayList = ArrayList<TuliColorBean>()

            arrayList.add(TuliColorBean("村民住宅用地", Color.parseColor("#FFE5B2")))
            arrayList.add(TuliColorBean("村庄小学用地", Color.parseColor("#CB66F0")))
            arrayList.add(TuliColorBean("村庄产业用地", Color.parseColor("#FEB3B3")))
            arrayList.add(TuliColorBean("村庄公共服务设施用地", Color.parseColor("#FFC866")))
            arrayList.add(TuliColorBean("村庄市政公用设施用地", Color.parseColor("#CB66F0")))
            arrayList.add(TuliColorBean("村庄道路与交通设施用地", Color.parseColor("#CB8866")))
            arrayList.add(TuliColorBean("水域", Color.parseColor("#ABECFF")))
            arrayList.add(TuliColorBean("农业用地", Color.parseColor("#FFF3CF")))
            arrayList.add(TuliColorBean("林业用地", Color.parseColor("#A9CB66")))
            arrayList.add(TuliColorBean("农村道路", Color.parseColor("#CCA966")))
            arrayList.add(TuliColorBean("其他非建设用地", Color.parseColor("#F0F066")))
            arrayList.add(TuliColorBean("景观游憩绿地", Color.parseColor("#94F066")))
            arrayList.add(TuliColorBean("生态保护用地", Color.parseColor("#94F066")))
            arrayList.add(TuliColorBean("待深入研究用地", Color.parseColor("#E0E0E0")))
            arrayList.add(TuliColorBean("公路用地", Color.parseColor("#FFFFFF")))
            arrayList.add(TuliColorBean("其他用地", Color.parseColor("#F0F066")))
            tlcl_frag_zjdgl_tl.setData(arrayList)
            if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                tlcl_frag_zjdgl_tl.visibility = View.GONE
            }else{
                tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
            }

        } else
            if (tab!!.text.toString().equals("园区经营")) {//已实施
            /*supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED*/
            zjdCyyqFrag.diaoyong()
            mode = MODE_CYYQ
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
                tabTc("产业园区新", 2)
                tabTc("城镇社区", 2)
                tabTc("农村社区新",2)
                tabTc("农业园区超新", 2)

//            tabTc("天地图", 3)
            /*tabTc("村庄规划", 3)
                tabTc("产业园区新", 1)*/
//            tabTc("企业", 2)
                val arrayList = ArrayList<TuliColorBean>()

                arrayList.add(TuliColorBean("产业园区", Color.parseColor("#75C3EE")))
                arrayList.add(TuliColorBean("城镇社区", Color.parseColor("#E0E0E0")))
                arrayList.add(TuliColorBean("农村社区", Color.parseColor("#FF9066")))
                arrayList.add(TuliColorBean("农业园区", Color.parseColor("#F2FF66")))
                tlcl_frag_zjdgl_tl.setData(arrayList)
                if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                    tlcl_frag_zjdgl_tl.visibility = View.GONE
                }else{
                    tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
                }

        } else
                if (tab!!.text.toString().equals("产业招商")) {//未实施
                    /*supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                    supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED*/
            mode = MODE_WL
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            zjdWlFrag.diaoyong()
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
//            tabTc("天地图", 3)
            tabTc("村庄规划", 3)
//            tabTc("产业园区新", 1)

                    if ("100110111009022".contains(AppCache.getInstance().code)){

                        var bounds = LatLngBounds.Builder()//设置显示在屏幕中的地图地理范围,地图中点显示为两个点的中点
                                .include(LatLng(39.72662, 115.792115))
                                .include(LatLng(39.727991, 115.894637))
                                .include(LatLng(39.652204, 115.897227))
                                .include(LatLng(39.651061, 115.794781)).build();//LatLngBounds

                        aMap!!.addGroundOverlay(GroundOverlayOptions()
                                .anchor(10.0f, 10.0f)//设置ground覆盖物的锚点比例，默认为0.5f，水平和垂直方向都居中对齐
                                .transparency(0.2f)//设置覆盖物的透明度，范围：0.0~1.0
                                .zIndex(-1f)//设置覆盖物的层次，zIndex值越大越在上层；
                                .image(BitmapDescriptorFactory.fromResource(R.drawable.new_hsd1))//覆盖物图片  hsd  R.drawable.hsd
                                .positionFromBounds(bounds));
                    }
        } else
                    if (tab!!.text.toString().equals("待开发")) {
                        /*supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED*/
            mode = MODE_DKF
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            zjdDkfFrag.diaoyong()
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearLayer(false)
            clearMap()
            //                    tabTc("农业园区")
//            tabTc("天地图", 3)
            tabTc("村庄规划", 3)
            tabTc("农业园区超新", 2)//农业园区新村
            tabTc("农业园区新村", 2)//农业园区新村

        }
    }
    private fun selectTx(tab: TabLayout.Tab?) {
        tabLayout = tl_frag_tx
        tabPosition = tab!!.position
//        val viewById = tab!!.view.findViewById(R.id.tab_text) as TextView
//        viewById.setTextColor(Color.WHITE)
        vp_frag_tx.requestLayout()
        bt_frag_zjdgl_point.isActivated = false
        bt_frag_zjdgl_gddw_add.isActivated = false
        bt_frag_zjdgl_kuang.isActivated = false
        supl_frag_zjdgl1?.setScrollableView(null)
        supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)
        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        ll_frag_shzl_rjhj.visibility = View.GONE
        rl_frag_zjdgl_point.visibility = View.GONE
        rl_frag_zjdgl_gddw_add.visibility = View.GONE
        rl_frag_zjdgl_add.visibility = View.GONE
        rl_frag_zjdgl_update.visibility = View.GONE
        rl_frag_zjdgl_kuang.visibility = View.GONE
        rl_frag_zjdgl_search.visibility = View.GONE
        tlcl_frag_zjdgl_tl.visibility = View.GONE
        rl_frag_zjdgl_clear.visibility = View.GONE
        click_mode = MODE_NORMAL
        if (tab!!.text.toString().equals("土地利用现状")) {//土地利用

            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(0f)//50f
            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED//ANCHORED
            mode = MODE_TDLY
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            zjdTdlyFrag.diaoyong()
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearMap()
            clearLayer(false)
            tabTc("底图", 3)
//            tabTc("村界", 2)
            /*if (AppCache.getInstance().code.contains("100110")){//100村 选择2018图层
                TDLY_POINT_TYPE = 3
            }
            when (TDLY_POINT_TYPE) {
                2 -> {
                    tabTc("土地利用2009", 1)
                }
                3 -> {
                    tabTc("土地利用2018", 1)
                }
                4 -> {
                    tabTc("土地利用2020", 1)
                }
            }

            val arrayList = ArrayList<TuliColorBean>()
            arrayList.add(TuliColorBean("耕地", Color.parseColor("#FFFF96")))
            arrayList.add(TuliColorBean("园地", Color.parseColor("#F5D228")))
            arrayList.add(TuliColorBean("林地", Color.parseColor("#288C00")))
            arrayList.add(TuliColorBean("设施农用地", Color.parseColor("#F7DCC5")))
            arrayList.add(TuliColorBean("建设用地", Color.parseColor("#FFBEBE")))
            arrayList.add(TuliColorBean("其他用地", Color.parseColor("#E1E1E1")))
            tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
            tlcl_frag_zjdgl_tl.setData(arrayList)*/

            //                    Log.e("mLayers",Gson().toJson(mLayers))

        } else
            if (tab!!.text.toString().equals("三块田")) {
                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            zjdTdsyFrag.diaoyong()
            mode = MODE_TDGH
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            rl_frag_zjdgl_point.visibility = View.VISIBLE
                rl_frag_zjdgl_clear.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
            tabTc("三块田", 1)
            val arrayList = ArrayList<TuliColorBean>()
            arrayList.add(TuliColorBean("永久基本农田", Color.parseColor("#FFFD76")))
            arrayList.add(TuliColorBean("耕地保有量储备区", Color.parseColor("#FFCB64")))
            arrayList.add(TuliColorBean("永久基本农田储备区", Color.parseColor("#CDFCAC")))
            tlcl_frag_zjdgl_tl.setData(arrayList)
                if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                    tlcl_frag_zjdgl_tl.visibility = View.GONE
                }else{
                    tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
                }

            //                    iv_frag_layer_tdlyxz.isActivated = true
            //                    iv_frag_layer_tdlyxz.visibility = View.VISIBLE
            //                    tv__frag_layer_tdlyxz.setTextColor(Color.parseColor("#4CA2FE"))
            //                    iv_frag_layer_gtgh.isActivated = false
            //                    iv_frag_layer_gtgh.visibility = View.GONE
            //                    tv__frag_layer_gtgh.setTextColor(Color.parseColor("#333333"))

        } else
                if (tab!!.text.toString().equals("土地权属")) {
                    supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                    supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            zjdTdqsFrag.diaoyong()
            mode = MODE_TDQS
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
            tabTc("权属", 1)
                    val arrayList = ArrayList<TuliColorBean>()
                    arrayList.add(TuliColorBean("国有", Color.parseColor("#FFC2C2")))
                    arrayList.add(TuliColorBean("集体", Color.parseColor("#C2DDFF")))
                    tlcl_frag_zjdgl_tl.setData(arrayList)
                    if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                        tlcl_frag_zjdgl_tl.visibility = View.GONE
                    }else{
                        tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
                    }
            //                    iv_frag_layer_gtgh.isActivated = true
            //                    iv_frag_layer_gtgh.visibility = View.VISIBLE
            //                    tv__frag_layer_gtgh.setTextColor(Color.parseColor("#4CA2FE"))
            //                    iv_frag_layer_tdlyxz.isActivated = false
            //                    iv_frag_layer_tdlyxz.visibility = View.GONE
            //                    tv__frag_layer_tdlyxz.setTextColor(Color.parseColor("#333333"))
        } else
                    if (tab!!.text.toString().equals("空间规划")) {
                        supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            zjdTdghFrag.diaoyong()
            mode = MODE_KJGH
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
            tabTc("规划", 1)

        } else if (tab!!.text.toString().equals("村庄规划")) {
                        supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            zjdTdghFrag.diaoyong()
            mode = MODE_KJGH
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
            rl_frag_zjdgl_point.visibility = View.VISIBLE
                        rl_frag_zjdgl_clear.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
            tabTc("规划", 1)
                        val arrayList = ArrayList<TuliColorBean>()

                        arrayList.add(TuliColorBean("村民住宅用地", Color.parseColor("#FFE5B2")))
                        arrayList.add(TuliColorBean("村庄小学用地", Color.parseColor("#CB66F0")))
                        arrayList.add(TuliColorBean("村庄产业用地", Color.parseColor("#FEB3B3")))
                        arrayList.add(TuliColorBean("村庄公共服务设施用地", Color.parseColor("#FFC866")))
                        arrayList.add(TuliColorBean("村庄市政公用设施用地", Color.parseColor("#CB66F0")))
                        arrayList.add(TuliColorBean("村庄道路与交通设施用地", Color.parseColor("#CB8866")))
                        arrayList.add(TuliColorBean("水域", Color.parseColor("#ABECFF")))
                        arrayList.add(TuliColorBean("农业用地", Color.parseColor("#FFF3CF")))
                        arrayList.add(TuliColorBean("林业用地", Color.parseColor("#A9CB66")))
                        arrayList.add(TuliColorBean("农村道路", Color.parseColor("#CCA966")))
                        arrayList.add(TuliColorBean("其他非建设用地", Color.parseColor("#F0F066")))
                        arrayList.add(TuliColorBean("景观游憩绿地", Color.parseColor("#94F066")))
                        arrayList.add(TuliColorBean("生态保护用地", Color.parseColor("#94F066")))
                        arrayList.add(TuliColorBean("待深入研究用地", Color.parseColor("#E0E0E0")))
                        arrayList.add(TuliColorBean("公路用地", Color.parseColor("#FFFFFF")))
                        arrayList.add(TuliColorBean("其他用地", Color.parseColor("#F0F066")))
                        tlcl_frag_zjdgl_tl.setData(arrayList)
                        if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                            tlcl_frag_zjdgl_tl.visibility = View.GONE
                        }else{
                            tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
                        }
        }
    }
    private fun selectShzl(tab: TabLayout.Tab?) {
        tabLayout = tl_frag_shzl
        tabPosition = tab!!.position
        vp_frag_shzl.requestLayout()
        bt_frag_zjdgl_point.isActivated = false
        bt_frag_zjdgl_gddw_add.isActivated = false
        bt_frag_zjdgl_kuang.isActivated = false
        supl_frag_zjdgl1?.setScrollableView(null)
        tv_frag_zjgl_rjhj_add.visibility = View.GONE
        ll_frag_shzl_rjhj.visibility = View.GONE
        rl_frag_zjdgl_point.visibility = View.GONE
        rl_frag_zjdgl_clear.visibility = View.GONE
        rl_frag_zjdgl_gddw_add.visibility = View.GONE
        rl_frag_zjdgl_add.visibility = View.GONE
        rl_frag_zjdgl_update.visibility = View.GONE
        rl_frag_zjdgl_kuang.visibility = View.GONE
        rl_frag_zjdgl_search.visibility = View.GONE
        tv_frag_zjgl_rjhj_add.visibility = View.GONE
//        ll_frag_cwtd_lntz.visibility = View.GONE
        tlcl_frag_zjdgl_tl.visibility = View.GONE
//        tlt_frag_zjdgl_rjhj.getTabAt(0)!!.select()
        click_mode = MODE_NORMAL
        /*tabList.add("人口就业")//流动人口
        tabList.add("产业结构")//人居环境
        tabList.add("资产经营")//无违建
        tabList.add("收入支出")*/
        val appName = AppUtils.getAppName()
        if (tab!!.text.toString().equals("人口就业")) {
            if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){
               /* var params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
                params.setMargins(0, 100, 0, 0);
                ll_frag_zjdgl_hd.setLayoutParams(params);*/

                supl_frag_zjdgl.anchorPoint = 1f
                supl_frag_zjdgl.isTouchEnabled = false
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }else{
                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }
            zjdLdrkFrag.diaoyong()
            mode = MODE_LDRK
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图

            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
//            tabTc("流动人口", 1)
            tabTc("院落", 1)
        } else
            if (tab!!.text.toString().equals("产业结构")) {
                if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){

                    supl_frag_zjdgl.anchorPoint = 1f
                    supl_frag_zjdgl.isTouchEnabled = false
                    supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                }else{

                    supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                    supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                }
            shzlRjhjFrag.diaoyong()
            rl_frag_zjdgl_add.visibility = View.GONE
//            ll_frag_shzl_rjhj.visibility = View.VISIBLE
            mode = MODE_RJHJ_RJHJ
            click_mode = POINT_CLICK
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            rl_frag_zjdgl_add.visibility = View.VISIBLE
            if (view_frag_shzl_gddw.isShown) {
                tv_frag_zjgl_rjhj_add.visibility = View.VISIBLE
            }
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
//            tabTc("人居环境", 1)
            tabTc("院落", 1)
        } else
                if (tab!!.text.toString().equals("资产经营")) {
                    if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){

                        supl_frag_zjdgl.anchorPoint = 1f
                        supl_frag_zjdgl.isTouchEnabled = false
                        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                    }else{

                        supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                        supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                    }
            shzlWfxcFrag.diaoyong()
            mode = MODE_WWJ
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            rl_frag_zjdgl_point.visibility = View.VISIBLE
//            ll_frag_cwtd_lntz.visibility = View.VISIBLE
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
            tabTc("权属集体", 2)
            tabTc("房屋", 2)
//            tabTc("院落", 2)
            val arrayList = ArrayList<TuliColorBean>()
            arrayList.add(TuliColorBean("未分类", Color.parseColor("#EE00B6")))
            arrayList.add(TuliColorBean("限期整治类", Color.parseColor("#FC5700")))
            arrayList.add(TuliColorBean("持续整治类", Color.parseColor("#FFA600")))
            arrayList.add(TuliColorBean("数据纠正", Color.parseColor("#FCEBC0")))
            arrayList.add(TuliColorBean("待完善类", Color.parseColor("#F4FB60")))
            arrayList.add(TuliColorBean("不纳入创建类", Color.parseColor("#DD9DC0")))
            arrayList.add(TuliColorBean("有证类", Color.parseColor("#9BAADC")))
            arrayList.add(TuliColorBean("宅基地", Color.parseColor("#71B9FF")))
            arrayList.add(TuliColorBean("初核有证类", Color.parseColor("#9AD9C4")))
            arrayList.add(TuliColorBean("初核待完善类", Color.parseColor("#B3D69C")))
//            tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
            tlcl_frag_zjdgl_tl.setData(arrayList)
        } else
                    if (tab!!.text.toString().equals("收入支出")) {
                        if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){

                            supl_frag_zjdgl.anchorPoint = 1f
                            supl_frag_zjdgl.isTouchEnabled = false
                            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                        }else{

                            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                        }
            shzlYqglFrag.diaoyong()
            mode = MODE_YQGL
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            rl_frag_zjdgl_point.visibility = View.VISIBLE//显示点查控制按钮
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
//            tabTc("无违建巡查", 1)//无违建巡查
            tabTc("院落", 1)//无违建巡查
        } else
                        if (tab!!.text.toString().equals("土地利用")) {
                            if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){

                                supl_frag_zjdgl.anchorPoint = 1f
                                supl_frag_zjdgl.isTouchEnabled = false
                                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                            }else{

                                supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                                supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                            }

                        mode = MODE_TDLY
                        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
                        zjdTdlyFrag.diaoyong()
                        rl_frag_zjdgl_point.visibility = View.VISIBLE
                            rl_frag_zjdgl_clear.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
                        aMap!!.clear()
                        clearMap()
                        clearLayer(false)
//            tabTc("天地图", 3)
                        if (AppCache.getInstance().code.contains("100110")){//100村 选择2018图层
                            TDLY_POINT_TYPE = 3
                        }
                        when (TDLY_POINT_TYPE) {
                            2 -> {
                                tabTc("土地利用2009", 1)
                            }
                            3 -> {
                                tabTc("土地利用2018", 1)
                            }
                            4 -> {
                                tabTc("土地利用2020", 1)
                            }
                        }

                        val arrayList = ArrayList<TuliColorBean>()
                        arrayList.add(TuliColorBean("耕地", Color.parseColor("#FFFF96")))
                        arrayList.add(TuliColorBean("园地", Color.parseColor("#F5D228")))
                        arrayList.add(TuliColorBean("林地", Color.parseColor("#288C00")))
                        arrayList.add(TuliColorBean("设施农用地", Color.parseColor("#F7DCC5")))
                        arrayList.add(TuliColorBean("建设用地", Color.parseColor("#FFBEBE")))
                        arrayList.add(TuliColorBean("其他用地", Color.parseColor("#E1E1E1")))
                        tlcl_frag_zjdgl_tl.setData(arrayList)
                            if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                                tlcl_frag_zjdgl_tl.visibility = View.GONE
                            }else{
                                tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
                            }
                        //                    Log.e("mLayers",Gson().toJson(mLayers))

                    }else
                            if (tab!!.text.toString().equals("土地权属")) {
                                if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){

                                    supl_frag_zjdgl.anchorPoint = 1f
                                    supl_frag_zjdgl.isTouchEnabled = false
                                    supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                                }else{

                                    supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                                    supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                                }
                                zjdTdqsFrag.diaoyong()
                                mode = MODE_TDQS
                                aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
                                rl_frag_zjdgl_point.visibility = View.VISIBLE
//            rl_frag_zjdgl_kuang.visibility = View.VISIBLE
//            rl_frag_zjdgl_search.visibility = View.VISIBLE
                                aMap!!.clear()
                                clearMap()
                                clearLayer(false)
//            tabTc("天地图", 3)
                                tabTc("权属", 1)
                                val arrayList = ArrayList<TuliColorBean>()
                                arrayList.add(TuliColorBean("国有", Color.parseColor("#FFC2C2")))
                                arrayList.add(TuliColorBean("集体", Color.parseColor("#C2DDFF")))
                                tlcl_frag_zjdgl_tl.setData(arrayList)
                                if (AppCache.getInstance().type == 4&&!appName.equals("黄山店")){
                                    tlcl_frag_zjdgl_tl.visibility = View.GONE
                                }else{
                                    tlcl_frag_zjdgl_tl.visibility = View.VISIBLE
                                }
                                //                    iv_frag_layer_gtgh.isActivated = true
                                //                    iv_frag_layer_gtgh.visibility = View.VISIBLE
                                //                    tv__frag_layer_gtgh.setTextColor(Color.parseColor("#4CA2FE"))
                                //                    iv_frag_layer_tdlyxz.isActivated = false
                                //                    iv_frag_layer_tdlyxz.visibility = View.GONE
                                //                    tv__frag_layer_tdlyxz.setTextColor(Color.parseColor("#333333"))
                            }else
                    if (tab!!.text.toString().equals("发展意愿")) {
                        if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){

                            supl_frag_zjdgl.anchorPoint = 1f
                            supl_frag_zjdgl.isTouchEnabled = false
                            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                        }else{

                            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                        }
            shzlWjdcFrag.diaoyong()
            mode = MODE_YQGL
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            rl_frag_zjdgl_point.visibility = View.VISIBLE//显示点查控制按钮
            aMap!!.clear()
            clearMap()
            clearLayer(false)
//            tabTc("天地图", 3)
            tabTc("无违建巡查", 1)//无违建巡查
        }else if (tab!!.text.toString().equals("监测户")) {
                        if (AppCache.getInstance().type==4&&!appName.equals("黄山店")){

                            supl_frag_zjdgl.anchorPoint = 1f
                            supl_frag_zjdgl.isTouchEnabled = false
                            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                        }else{

                            supl_frag_zjdgl.panelHeight = DisplayUtil.dip2px(50f)
                            supl_frag_zjdgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                        }
                            bcqhFrag.diaoyong()
                            mode = MODE_YQGL
                            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))//移动地图
//            rl_frag_zjdgl_point.visibility = View.VISIBLE//显示点查控制按钮
                            aMap!!.clear()
                            clearMap()
                            clearLayer(false)
//            tabTc("天地图", 3)
//            tabTc("无违建巡查", 1)//无违建巡查
                            tabTc("院落", 1)//无违建巡查
                        }
    }
    //下拉tablayout回调
    override fun selected(tabPosition: Int, listPosition: Int) {
        aMap!!.clear()
        clearMap()
        clearLayer(false)
        when(listPosition){
            0->{
//                tabTc("天地图",3)
                tabTc("土地利用2009",1)
                TDLY_POINT_TYPE = 2
            }
            1->{
//                tabTc("天地图",3)
                tabTc("土地利用2018",1)
                TDLY_POINT_TYPE = 3
            }
            2->{
//                tabTc("天地图",3)
                tabTc("土地利用2020",1)
                TDLY_POINT_TYPE = 4
            }
        }
//        ToastUtils.showShort("点击了TAB$tabPosition，Position$listPosition")
    }
    //点查地图
    override fun onMapClick(p0: LatLng?) {
        rlv_frag_zjdgl_tc.visibility = View.GONE
        when (click_mode) {
            POINT_CLICK -> {
                queryByPoint(p0!!)
                mLatLng = p0
            }
            POLYGON_CLICK -> {
                queryByPolygon(p0!!)
            }
        }
    }
    private fun queryByPoint(latLng: LatLng) {
        if (SingleOnClickUtil.isFastClick()){
            if (addMarker!=null){
                addMarker!!.remove()
            }
            val markerOptions = MarkerOptions()
            addMarker = aMap!!.addMarker(markerOptions.position(latLng))
            pointStr = "POINT(" + latLng.longitude + " " + latLng.latitude + ")"
            when (mode) {
                MODE_YZT->{//一张图
                    mPresenter.getYztByPoint(pointStr)
                }
                MODE_YL->{//院落
                    mPresenter.getYztByPoint(pointStr)
                }
                MODE_YD->{//用地
//                    mPresenter.getApplyByPoint(pointStr)
                    mPresenter.getPointGetYl(pointStr)
                }
                MODE_FJ->{//翻建
                    mPresenter.getApplyByPoint(pointStr)
                }
                MODE_XC->{//巡查
                    mPresenter.getFanJian(pointStr)
                }
                MODE_CZ -> {//物业（原出租）
                    mPresenter.getPointGetYl(pointStr)
//                    mPresenter.getDcylxx(pointStr,null)
//                    mPresenter.getWyPoint(pointStr,0)
                }
                MODE_NYYQ -> {//农业园区
                    mPresenter.getNyyqPoint(pointStr)
                }
                MODE_CYYQ -> {//产业园区
                    mPresenter.getCyyqPoint(pointStr)
//                    mPresenter.getYztByPoint(pointStr)
                }
                MODE_WL -> {//文旅
                }
                MODE_DKF -> {//待开发
                    mPresenter.getDkfPoint(pointStr)
                }
                MODE_TDLY -> {//土地利用
                    mPresenter.getTxPoint(pointStr,TDLY_POINT_TYPE)
                }
                MODE_KJGH -> {//空间规划
                    mPresenter.getKjghPoint(pointStr)
                }
                MODE_TDQS -> {//土地权属
//                    mPresenter.getTdqsPoint(pointStr)
                    mPresenter.getTxPoint(pointStr,3)//4
                }
                MODE_TDGH -> {//土地规划
                    mPresenter.getTdghPoint(pointStr)
                }
                MODE_LDRK -> {//流动人口
                    mPresenter.getDcylxx(pointStr,null)
                }
                MODE_RJHJ_RJHJ -> {//人居环境
                    isShow = 1
                    mPresenter.getRjhjjcPoint(pointStr)
                }
                MODE_RJHJ_GDDW -> {//固定点位
                }
                MODE_WWJ -> {//无违建
                    mPresenter.getWwjPoint(pointStr)
                }
                MODE_FW -> {//房屋  暂时没有接口
//                    mPresenter.getWwjPoint(pointStr)

                }
            }
        }
    }
    private fun queryByPolygon(latLng: LatLng) {
        mLatLngs.add(latLng)
        if (mPolygon != null) mPolygon!!.remove()
        mPolygonOptions!!.points.clear()
        mPolygonOptions!!.addAll(mLatLngs)
        mPolygonOptions!!.strokeWidth(5f) // 多边形的边框
                .strokeColor(Color.argb(255, 255, 1, 1)) // 边框颜色
                .fillColor(Color.argb(50, 177, 152, 229)) // 多边形的填充色
        if (mPolygonOptions!!.points.size <= 1) {
            aMap!!.addMarker(MarkerOptions().position(latLng))
        } else {
            clearAllMarker()
        }
        /*val draggable = MarkerOptions().position(latLng)//.draggable(true)
        aMap!!.addMarker(draggable)*/
        mPolygon = aMap!!.addPolygon(mPolygonOptions)
    }

    //调用定位的方法
    private fun location() {
        //初始化定位
        mLocationClient = AMapLocationClient(activity?.getApplicationContext())
        //设置定位回调监听
        mLocationClient!!.setLocationListener(this)
        //初始化定位参数
        mLocationOption = AMapLocationClientOption()
        //设置定位模式为 Hight_Accuracy 高精度模式，Battery_Saving 为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving)
        //设置是否返回地址信息（默认返回地址信息）
        // mLocationOption!!.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption!!.setOnceLocation(false)
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption!!.setOnceLocationLatest(true)
        //设置是否强制刷新WIFI，默认为强制刷新
        // mLocationOption!!.setWififragiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption!!.setMockEnable(false)
        mLocationOption!!.setLocationCacheEnable(false)
        //设置定位间隔,单位毫秒,默认为2000ms
        // mLocationOption!!.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient!!.setLocationOption(mLocationOption)
        //启动定位
        mLocationClient!!.startLocation()
//        mLocationClient!!.stopLocation()
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
                var df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                // isFirstLoc = false;
                if (isFirstLoc) {
                    isFirstLoc = false;
                    //设置缩放级别
//                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f));

                    //将地图移动到定位点
                    /*val model = PositionUtil.gcj_To_Gps84(aMapLocation.latitude, aMapLocation.longitude)
                    val point = LatLng(model.wgLat, model.wgLon)
                    aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(point));//LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())
                    aMap?.addMarker(MarkerOptions().position(point))*/
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
                            + aMapLocation.getStreetNum())
//                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();

                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
// Toast.makeText(fragivity!!.getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }
    override fun onResume() {
        super.onResume()
        map_frag_zjdgl?.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_frag_zjdgl?.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        map_frag_zjdgl?.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 获取解析结果  二维码扫描结果
        var result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);//IntentResult
        if (result!=null){

        }
        if (mode == MODE_LDRK){//流动人口回调
            if (result != null) {
                if (result.getContents() == null) {
                    ToastUtils.showShort("取消扫描")
                } else {
                    if (result.getContents() != null) {
                        val split = result.getContents().split("id=")
                        if (split.size > 1) {
                            mPresenter.getSmewmcyl(split[1])
                        } else {
                            mPresenter.getSmewmcyl(result.getContents())
                        }
                    } else {
                        ToastUtils.showShort("扫描二维码错误")
                    }

//                Toast.makeText(this, "扫描内容:" + result.getContents(), Toast.LENGTH_LONG).show();
//                Log.e("feartttt","${result.getBarcodeImagePath()}")
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
        if (mode == MODE_XC){
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
                        upFileFj(file1);
                    }
//                    selectedPhotos.addAll(photos)
                }
//                if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
            }
        }
        if (mode == MODE_RJHJ_RJHJ||mode == MODE_RJHJ_GDDW){
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
                }else if(requestCode == 66){
                    var uri = data!!.getData()
                    if (uri != null){
                        selectedVideos.add(getRealPathFromURI(uri))
                    }
                    if (videoAdapter != null) videoAdapter!!.notifyDataSetChanged()
                    if (selectedVideos.size >= 1){
                        var sdf = SimpleDateFormat("dd-MM-yyyy");
                        var c = Calendar.getInstance();
                        var date = sdf.format(c.getTime());//视频
                        var path= AppMenus.getInstance().cardPath+"jymj/tzrjhj/video/"//+date+".mp4"
                        object :Thread() {
                            override fun run() {
                                super.run()
                                HttpUtils.runOnUiThread(object : Runnable {
                                    override fun run() {
                                        LoadingDialog.showDialogForLoading(activity)
                                    }

                                })
                                var s = 0
                                var file=File(path)
                                if (!file.exists()){
                                    file.mkdirs()
                                }
                                try {
                                    /**
                                     * 视频压缩
                                     * 第一个参数:视频源文件路径
                                     * 第二个参数:压缩后视频保存的路径
                                     */
                                    /*  var comPressPath = SiliCompressor.with(getActivity()).compressVideo(getRealPathFromURI(uri!!), path,1280,
                                              720,
                                              2000000);//Video
                                      file = File(comPressPath)
      */
                                } catch ( e: URISyntaxException) {
                                    s=1
                                    e.printStackTrace();
                                }finally {
                                    if (s!=1){
                                        HttpUtils.runOnUiThread(object : Runnable {
                                            override fun run() {
                                                LoadingDialog.showDialogForLoading(activity)
//                                            upVideo(file)
                                            }

                                        })
                                    }
                                }



                            }
                        }.start();
                    }
                }else if (requestCode==20){
                    var photoLists = data!!.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    if (photoLists != null ) {//&& !photoLists.isEmpty()
                        selectedPhotos = photoLists
                        if (photoAdapter!=null){
                            photoAdapter!!.setNewData(selectedPhotos)
                        }
                    }
//                    mAddPicture.setPaths(mImagePaths);
                }else if (requestCode==TAKE_PHOTO_REQUEST){
                    if (resultCode == Activity.RESULT_CANCELED) {
                        ToastUtils.showShort("取消了拍照")
                        return
                    }

                    path = cameraPath
//                GlideUtils.getGlideUtils().load(context, cameraPath, compileIvHead);
                    if (path != null) {
                        val name = File(path).name
                        val bitmap = IOHelper.loadBitmap(File(path).path,true)
                        val file1: File = compressImages(bitmap, name)
                        uplodeEwmGc(file1,gcImageType)
                    }
                }else if (requestCode==IMAGE_REQUEST_CODE){
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            val selectedImage = data!!.getData() //获取系统返回的照片的Uri
                            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                            val cursor = activity!!.getContentResolver().query(selectedImage!!, //getContentResolver
                                    filePathColumn, null, null, null)//从系统表中查询指定Uri对应的照片
                            cursor!!.moveToFirst()
                            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                            path = cursor.getString(columnIndex)  //获取照片路径No Cannected Devices
                            cursor.close()

                            //                        compileIvHead.setImageBitmap(bitmap);
                            if (path != null) {
                                /*if (gcImageType == 1){
                                    Glide.with(this).load(File(path)).into(image_ddgcmp_environmental!!)
                                }else{
                                    Glide.with(this).load(File(path)).into(image_ddgcwg_environmental!!)
                                }*/
                                val name = File(path).name
                                val bitmap = IOHelper.loadBitmap(File(path).path,true)
                                val file1: File = compressImages(bitmap, name)

                                uplodeEwmGc(file1,gcImageType)
                            }
                        } catch (e: Exception) {
                            // TODO Auto-generatedcatch block
                            e.printStackTrace()
                        }

                    }
                }

            }
        }

    }
    /**
     * 固定点位公厕添加上传图片
     *
     * @return
     */
    fun uplodeEwmGc(file: File,gcImageType: Int) {
        val httpParams = HttpParams()
        httpParams.put("file", file)
        httpParams.put("type", gcImageType)
        OkGo.post<BaseRespose<RenovatedFile>>(ApiConstants.ENVIORFILE_UPLOAD_FILE)
                .params(httpParams)
                .execute(object : BaseNet<BaseRespose<RenovatedFile>>() {
                    override fun onStart(request: Request<BaseRespose<RenovatedFile>, out Request<*, *>?>?) {
                        super.onStart(request)
                        if (!LoadingDialog.isShowing()) LoadingDialog.showDialogForLoading(activity)
                    }

                    override fun onSuccess(response: Response<BaseRespose<RenovatedFile>>) {
                        val body = response.body()
                        if (body.data != null) {
                            renovatedFileList.add(body.data)
                            if (gcImageType == 1){//body.data.url
                                Glide.with(activity!!).load(body.data.path).into(image_ddgcmp_environmental!!)
                                image_ddgcmp_del_environmental!!.visibility = View.VISIBLE
                                gcImageURL1 = body.data.path
                            }else{
                                Glide.with(activity!!).load(body.data.path).into(image_ddgcwg_environmental!!)
                                image_ddgcwg_del_environmental!!.visibility = View.VISIBLE
                                gcImageURL2 = body.data.path
                            }
                        }

                        LoadingDialog.cancelDialogForLoading()
                    }

                    override fun onError(response: Response<BaseRespose<RenovatedFile>>) {
                        super.onError(response)
                        ToastUtils.showShort(response.exception.message)
                        LoadingDialog.cancelDialogForLoading()
                    }
                })
    }

    private fun upFile1( file2: File) {
        val decodeFile = BitmapFactory.decodeFile(file2.path)
//        PhotoBitmapUtils.amendRotatePhoto(file2.path,activity)
//        val decodeFile = IOHelper.loadBitmap(file2.path,true)
//        pjContract.wz
        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(context, decodeFile,  cunMing, 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(drawTextToRightBottom)
        val request = OkGo.post<BaseRespose<PjEnviorFileEntity>>(ApiConstants.ENVIORFILEUPLOAD)
                .isMultipart(true)
//        request.params("pid", AppCache.getInstance().getProid())
        request.params("type", 1)
        request.params("filetype", 1)
        request.params("msid", -1)
        request.params("file", file)//file  file.name
        request.execute(object : BaseNet<BaseRespose<PjEnviorFileEntity>>() {//BaseRespose<PjEnviorFileEntity>
        override fun onStart(request: Request<BaseRespose<PjEnviorFileEntity>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            LoadingDialog.showDialogForLoading(activity)
        }
            override fun onSuccess(response: Response<BaseRespose<PjEnviorFileEntity>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (body.getCode()==0){
                    PjEnviorFileList.add(body.data)
                    schxtp!!.setNewData(PjEnviorFileList)
                    schxtp!!.notifyDataSetChanged()
                }else{
                    ToastUtils.showShort(file.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<PjEnviorFileEntity>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
            }
        })
    }
    fun getRealPathFromURI(contentUri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity!!.managedQuery(contentUri, proj, null, null, null)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }
    private fun upFileFj( file2: File) {//翻建上传图片
        val decodeFile = BitmapFactory.decodeFile(file2.path)
//        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(context, decodeFile,  cunMing, 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(decodeFile)
        val request = OkGo.post<BaseRespose<RenovatedFile>>(ApiConstants.YL_UPLOAD_FILE)
                .isMultipart(true)
//        MultipartBody
/*        request.params("type", 1)//1
        request.params("filetype", 1)//1
        request.params("msid", -1)*/
//        request.upFile(file)  file.name
        request.params("file", file)
        request.execute(object : BaseNet<BaseRespose<RenovatedFile>>() {
            override fun onStart(request: Request<BaseRespose<RenovatedFile>, out Request<Any, Request<*, *>>>?) {//renovatedFileList
                super.onStart(request)
                LoadingDialog.showDialogForLoading(activity)
            }
            override fun onSuccess(response: Response<BaseRespose<RenovatedFile>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (body.getCode()==0){
                    renovatedFileList.add(body.data)
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

    var radioId = 0
    //添加右侧抽屉菜单数据
    private fun setDrawerRightData(){
//        rlv_frag_layer_sj.layoutManager = GridLayoutManager(activity,4)
//        rlv_frag_layer_yzt.layoutManager = GridLayoutManager(activity,4)
        /*val layerSjBean1 = LayerSjBean("住有所居",false,R.drawable.menu_sj_zysj)
        val layerSjBean2 = LayerSjBean("产业发展",false,R.drawable.menu_sj_cyfz)
        val layerSjBean3 = LayerSjBean("基础设施",false,R.drawable.menu_sj_jcss)
        val layerSjBean4 = LayerSjBean("拆除腾退",false,R.drawable.menu_sj_cctt)
        val layerSjBean5 = LayerSjBean("生态建设",false,R.drawable.menu_sj_stjs)
        val layerSjBean6 = LayerSjBean("公共服务",false,R.drawable.menu_sj_ggfw)

        layerSjList.add(layerSjBean4)
        layerSjList.add(layerSjBean5)
        layerSjList.add(layerSjBean6)
        layerSjList.add(layerSjBean1)
        layerSjList.add(layerSjBean2)
        layerSjList.add(layerSjBean3)

        sjListAdapter = object : BaseQuickAdapter<LayerSjBean,BaseViewHolder>(R.layout.item_layer_sj,layerSjList){
            override fun convert(helper: BaseViewHolder?, item: LayerSjBean?) {
                helper!!.setText(R.id.tv_item_layer_sj,item!!.name)
                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(item.id))

                helper.itemView.setOnClickListener {
                    when(item.name){
                        "住有所居"->{
                            if (!item.isCheck){
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_zysj_w))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#4CA2FE"))
                                item.isCheck = true
                            }else{
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_zysj))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "产业发展"->{
                            if (!item.isCheck){
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_cyfz_w))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#4CA2FE"))
                                item.isCheck = true
                            }else{
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_cyfz))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "基础设施"->{
                            if (!item.isCheck){
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_jcss_w))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#4CA2FE"))
                                item.isCheck = true
                            }else{
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_jcss))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "拆除腾退"->{
                            tabTc("拆除腾退",2)
                            if (!item.isCheck){
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_cctt_w))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#4CA2FE"))
                                item.isCheck = true
                            }else{
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_cctt))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "生态建设"->{
                            if (!item.isCheck){
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_stjs_w))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#4CA2FE"))
                                item.isCheck = true
                            }else{
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_stjs))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "公共服务"->{
                            if (!item.isCheck){
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_ggfw_w))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#4CA2FE"))
                                item.isCheck = true
                            }else{
                                helper.setImageDrawable(R.id.iv_item_layer_sj,activity!!.getDrawable(R.drawable.menu_sj_ggfw))
                                helper.setTextColor(R.id.tv_item_layer_sj, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                    }
                }

            }

        }*/
//        rlv_frag_layer_sj.adapter = sjListAdapter

        item_yzt_rgp.setOnCheckedChangeListener { group, checkedId ->
            clearLayer(false)
            rl_frag_zjdgl_point.visibility = View.VISIBLE
            rl_frag_zjdgl_clear.visibility = View.VISIBLE
            rl_frag_zjdgl_kuang.visibility = View.GONE//右侧工具：框
            rl_frag_zjdgl_search.visibility = View.GONE//右侧工具：搜索
            mode = MODE_NORMAL
                when(checkedId){
                    R.id.item_yzt_rbt_xp->{//宅基地
                        if (item_yzt_rbt_xp.isChecked){
                            mode = MODE_YD
                            zjd_click_mode = MODE_ZJD1
                            tabTc("院落",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_fj->{//翻建

                    }
                    R.id.item_yzt_rbt_xc->{//巡查
                        if (item_yzt_rbt_xc.isChecked){
                            mode = MODE_XC
                            tabTc("房屋巡查",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_cz->{//出租
                        if (item_yzt_rbt_cz.isChecked){
                            mode = MODE_CZ
                            tabTc("流动人口",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_cyyq->{//产业园区
                        tabTc("",1)
                        if (item_yzt_rbt_cyyq.isChecked){
                            mode = MODE_CYYQ
                            tabTc("城镇社区",2)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_wl->{//农业园区
                        if (item_yzt_rbt_wl.isChecked){
                            mode = MODE_NYYQ
                            tabTc("农业园区新",2)
                            tabTc("农业园区超新",2)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_dkf->{//待开发
                        if (item_yzt_rbt_dkf.isChecked){
                            mode = MODE_DKF
                            tabTc("农村社区新",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_nyyq->{//产业园区
                        tabTc("",1)
                        if (item_yzt_rbt_nyyq.isChecked){
                            mode = MODE_NYYQ
                            tabTc("产业园区新",2)
//                            tabTc("产业园区",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_tdly->{//土地利用
                        if (item_yzt_rbt_tdly.isChecked){
                            mode = MODE_TDLY
                            TDLY_POINT_TYPE = 2
                            tabTc("土地利用2018",1)//土地利用2009
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_skt->{//土地使用
                        if (item_yzt_rbt_skt.isChecked){
                            mode = MODE_TDGH
                            tabTc("三块田",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_tdqs->{//土地权属
                        if (item_yzt_rbt_tdqs.isChecked){
                            mode = MODE_TDQS
                            tabTc("权属",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_kjgh->{//空间规划
                        if (item_yzt_rbt_kjgh.isChecked){
                            mode = MODE_KJGH
                            tabTc("规划",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_ldrk->{//流动人口
                        if (item_yzt_rbt_ldrk.isChecked){
                            mode = MODE_LDRK
                            tabTc("流动人口",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_rjhj->{//人居环境
                        if (item_yzt_rbt_rjhj.isChecked){
                            rl_frag_zjdgl_point.visibility = View.GONE
                            rl_frag_zjdgl_clear.visibility = View.GONE
                            mode = MODE_RJHJ_RJHJ
                            tabTc("人居环境",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_wfxc->{//违法巡查
//                        tabTc("",1)
                        if (item_yzt_rbt_wfxc.isChecked){
                            mode = MODE_WWJ
                            tabTc("无违建巡查",1)
                            tabTc("企业",2)
                            tabTc("院落",2)
                        }else{
                            initTcYzt()
                        }
                    }
                    R.id.item_yzt_rbt_12345->{//12345

                    }
                    R.id.item_yzt_rbt_lvhua->{//绿化
                        if (item_yzt_rbt_lvhua.isChecked){
                            tabTc("绿化",1)
                        }else{
                            initTcYzt()
                        }
                    }
                    -1->{
                        rl_frag_zjdgl_point.visibility = View.GONE
                        rl_frag_zjdgl_clear.visibility = View.GONE
                        rl_frag_zjdgl_kuang.visibility = View.GONE//右侧工具：框
                        rl_frag_zjdgl_search.visibility = View.GONE//右侧工具：搜索
//                        ToastUtils.showShort("吐司")
                        mode = MODE_YL
                        initTcYzt()
                    }
                }


        }

       /* item_cwtd_lnxz_2017tz.setOnClickListener {
            tabTc("拆违腾地2017年台账",2)
        }
        item_cwtd_lnxz_2018tz.setOnClickListener {
            tabTc("拆违腾地2018年台账",2)
        }
        item_cwtd_lnxz_2019tz.setOnClickListener {
            tabTc("拆违腾地2019年台账",2)
        }
        item_cwtd_lnxz_2020tz.setOnClickListener {
            tabTc("拆违腾地2020年台账",2)
        }*/
        val layerYztBean1 = LayerYztBean("新批", true, 1)
        val layerYztBean2 = LayerYztBean("翻建", false, 1)
        val layerYztBean3 = LayerYztBean("巡查", false, 1)
        val layerYztBean4 = LayerYztBean("出租", false, 1)
        val layerYztBean5 = LayerYztBean("农业园区", false, 2)
        val layerYztBean6 = LayerYztBean("产业园区", false, 2)
        val layerYztBean7 = LayerYztBean("文旅", false, 2)
        val layerYztBean8 = LayerYztBean("待开发", false, 2)
        val layerYztBean9 = LayerYztBean("土地规划", false, 3)
        val layerYztBean10 = LayerYztBean("土地权属", false, 3)
        val layerYztBean11 = LayerYztBean("基准地价", false, 3)
        val layerYztBean12 = LayerYztBean("房屋交易", false, 3)
        val layerYztBean13 = LayerYztBean("流动人口", false, 4)
        val layerYztBean14 = LayerYztBean("人居环境", false, 4)
        val layerYztBean15 = LayerYztBean("违法巡查", false, 4)

        layerYztList.add(layerYztBean1)
        layerYztList.add(layerYztBean2)
        layerYztList.add(layerYztBean3)
        layerYztList.add(layerYztBean4)
        layerYztList.add(layerYztBean5)
        layerYztList.add(layerYztBean6)
        layerYztList.add(layerYztBean7)
        layerYztList.add(layerYztBean8)
        layerYztList.add(layerYztBean9)
        layerYztList.add(layerYztBean10)
        layerYztList.add(layerYztBean11)
        layerYztList.add(layerYztBean12)
        layerYztList.add(layerYztBean13)
        layerYztList.add(layerYztBean14)
        layerYztList.add(layerYztBean15)

        yztListAdapter = object : BaseQuickAdapter<LayerYztBean,BaseViewHolder>(R.layout.item_layer_yzt,layerYztList){
            override fun convert(helper: BaseViewHolder?, item: LayerYztBean?) {
                helper!!.setText(R.id.tv_item_layer_yzt,item!!.name)
                if (item.isCheck) {
                    helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                    when(item.type){
                        1->{
                            helper.setBackgroundRes(R.id.tv_item_layer_yzt, R.drawable.bt_actiive_glzjd)
                        }
                        2->{
                            helper.setBackgroundRes(R.id.tv_item_layer_yzt, R.drawable.bt_actiive_glcy)
                        }
                        3->{
                            helper.setBackgroundRes(R.id.tv_item_layer_yzt, R.drawable.bt_actiive_gltd)
                        }
                        4->{
                            helper.setBackgroundRes(R.id.tv_item_layer_yzt, R.drawable.bt_actiive_glshza)
                        }
                    }
                }else{

                    helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                    helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                }


                helper.itemView.setOnClickListener {
                    when(item.name){
                        "新批"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glzjd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }

                        "翻建"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glzjd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                            tabTc("翻建",2)
                        }
                        "巡查"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glzjd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "出租"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glzjd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "农业园区"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glcy)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "产业园区"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glcy)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "文旅"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glcy)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "待开发"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glcy)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "土地规划"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_gltd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "土地权属"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_gltd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "基准地价"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_gltd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "房屋交易"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_gltd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                        "流动人口"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glshza)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                            tabTc("流动人口",1)
                        }
                        "人居环境"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glshza)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                            tabTc("人居环境",1)
                        }
                        "违法巡查"->{
                            if (!item.isCheck){
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_glshza)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#ffffff"))
                                item.isCheck = true
                            }else{
                                helper.setBackgroundRes(R.id.tv_item_layer_yzt,R.drawable.bt_actiive_dddddd)
                                helper.setTextColor(R.id.tv_item_layer_yzt, Color.parseColor("#333333"))
                                item.isCheck = false
                            }
                        }
                    }
                }

            }

        }
//        rlv_frag_layer_yzt.adapter = yztListAdapter




    }

    //农业园区点查
    override fun returnNyglPoint(cash: List<YztNyyqEntity>) {
        if (cash!=null&&cash.size>0){
            val dataGeometry = cash.get(0).geometry
            kuangGeomentLine(dataGeometry)
            PopuPointUtils.startPopuNyyqPoint(supl_frag_zjdgl,cash.get(0))
        }else{
            ToastUtils.showShort("暂无数据！")
        }
    }
    //待开发点查
    override fun returnDkfPoint(cash: List<DkfVO>) {
        if (cash!=null&&cash.size>0){
            val dataGeometry = cash.get(0).geometry
            kuangGeomentLine(dataGeometry)
            PopuPointUtils.startPopuDkfPoint(supl_frag_zjdgl,cash.get(0))
        }else{
            ToastUtils.showShort("暂无数据！")
        }
    }
    //无违建点查
    override fun returnWwjPoint(entity: WwjEntity) {
        if (entity!=null){
            val dataGeometry = entity.geometry
            kuangGeomentLine(dataGeometry)
            PopuPointUtils.startPopuWwjPoint(supl_frag_zjdgl,entity)
        }else{
            ToastUtils.showShort("暂无数据！")
        }
    }

}