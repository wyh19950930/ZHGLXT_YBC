package com.jymj.zhglxt.xm.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.util.HeritageScopeTileProvider1
import com.jymj.zhglxt.util.PositionUtil
import com.jymj.zhglxt.widget.BubbleView
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.bean.CjVO
import com.jymj.zhglxt.xm.bean.ProjectCountVO
import com.jymj.zhglxt.xm.contract.XmIssueContract
import com.jymj.zhglxt.xm.contract.XmMapContract
import com.jymj.zhglxt.xm.presenter.XmIssuePresenter
import com.jymj.zhglxt.xm.presenter.XmMapPresenter
import com.jymj.zhglxt.zjd.bean.fj.FwglJhBean
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_xm_map.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import java.math.BigDecimal

class XmMapActivity : BaseActivity<XmMapPresenter, XmMapContract.Model>(), XmMapContract.View, AMap.OnMapClickListener {

    public val mLayers = ArrayList<LayerEntity>()
    public val mLayers1 = ArrayList<LayerEntity>()
    public var legendsYL: ArrayList<LegendEntity>? = null
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

    private var aMap: AMap? = null
    private var mPolygon: Polygon? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var mMyLocationStyle: MyLocationStyle? = null
    private val mLatLngs = ArrayList<LatLng>()
    private var markers = ArrayList<Marker>()//所有聚合的点位
    private val sdCardPath = AppMenus.getInstance().cardPath
    private var addMarker: Marker? = null
    private var pointStr = ""
    private var onCameraChange: LatLng? = null
    private var pointMarker: Marker? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_xm_map
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL
//        MapsInitializer.replaceURL("https://c-ssl.duitang.com/uploads/item/202004/09/20200409032441_mhYLN.jpeg", "tdt-kbt")//VEC_URL
        map_act_xm.onCreate(intent.extras)
        initAMap()
        setAMap()
        iv_xm_map_back.setOnClickListener {
            finish()
        }

    }

    override fun initDatas() {
        initTuli()
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

        aMap?.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            //这个应该是地图的滑动监听吧
            override fun onCameraChangeFinish(p0: CameraPosition?) {//调用热力图的地方
                onCameraChange = p0!!.target
                if (aMap!=null){
                    val visibleRegion = aMap!!.projection.visibleRegion
                    val farLeft = visibleRegion.farLeft //可视区域的左上角。  position1
                    val nearright = visibleRegion.nearRight //可视区域的右下角。position2
                    val zoom = aMap!!.cameraPosition.zoom
                    val code = AppCache.getInstance().cunCode
                    if (zoom<9){
                        mPresenter.getEnviorsByxy("1", BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude),code)
                    }else if (zoom<11){
                        mPresenter.getEnviorsByxy("2", BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude),code)
                    }else if (zoom<13){//15
                        mPresenter.getEnviorsByxy("3", BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude),code)
                    }else if (zoom<15.5){//15
                        mPresenter.getEnviorsByxy("4", BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude),code)
                    }else if (zoom>15.5){//15
                        mPresenter.getEnviorsByxy("5", BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude),code)
                    }else{
                        clearAllMarker()
//                                mPresenter.getEnviorsByxy("4", BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude),isImage,code)
                    }
                    if (pointMarker!=null){
                        pointMarker!!.remove()
                    }
                    /*val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//坐标转换  火星坐标 转 2000
                    val point = LatLng(model.wgLat, model.wgLon)
                    pointMarker = aMap?.addMarker(MarkerOptions().position(point))*/
                }
            }
            override fun onCameraChange(p0: CameraPosition?) {
                /* if (p0!!.zoom > 18.65&&aMap!=null) {
                     aMap!!.moveCamera(CameraUpdateFactory.zoomTo(18.65f))
                 }*/
                onCameraChange = p0!!.target
            }
        })

    }

    //添加图层
    private fun initTuli() {
        legendsYL = ArrayList<LegendEntity>()
//        legends4L = ArrayList<LegendEntity>()
        mLayers.clear()
        mLayers1.clear()
        //100110115005013
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//AppCache.getInstance().proID.toInt()  ,legends4L,1  AppCache.getInstance().proID.toInt()
                    //http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&layers=bcjcxt%3Abc_project&bbox=116.312568%2C39.62693%2C116.448875%2C39.837558&width=497&height=768&srs=EPSG%3A4527&styles=&format=application/openlayers#toggle
        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        for (i in mLayers){
            i.isCheck = false
            if (i.name.equals("天地图")){
                i.isCheck = true
            }else if (i.name.equals("村界")){
                i.isCheck = true
            }else if (i.name.equals("镇界")){
                i.isCheck = true
            }else if (i.name.equals("新项目")){
                i.isCheck = true
            }
        }
        for (i in mLayers1){
            i.isCheck = false
            if (i.name.equals("天地图")){
                i.isCheck = true
            }else if (i.name.equals("村界")){
                i.isCheck = true
            }else if (i.name.equals("镇界")){
                i.isCheck = true
            }else if (i.name.equals("新项目")){
                i.isCheck = true
            }
        }
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
        /*var MAP_PROJECT = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=TRUE&layers=bcjcxt:bc_project&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
//        var MAP_PROJECT = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&layers=bcjcxt%3Abc_project&bbox=116.312568%2C39.62693%2C116.448875%2C39.837558&width=497&height=768&srs=EPSG%3A4527&styles=&format=application/openlayers";
                         //http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&layers=bcjcxt%3Abc_project&bbox=116.312568%2C39.62693%2C116.448875%2C39.837558&width=497&height=768&srs=EPSG%3A4527&styles=&format=application/openlayers
        val layer_xzglcj = HeritageScopeTileProvider1(MAP_PROJECT)
        var opt_project = TileOverlayOptions().tileProvider(layer_xzglcj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_project);*/
       /* var MAP_DK1 = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:project&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk1 = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai111/";
        var layer_dk1 = HeritageScopeTileProvider(MAP_DK1, path_dk1);
        var opt_dk1 = TileOverlayOptions().tileProvider(layer_dk1).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk1).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk1);*/

    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_act_xm.getMap()
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


    private fun addOverLayer(layers: List<LayerEntity>) {


        for (l in layers) {
            when (l.name) {
                "影像图" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtSta)
                        aMap!!.addTileOverlay(opt_tdtStaN)
                    }
                }//opt_tdtn
                "底图" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtOsm)
                    }
                }
                "天地图" -> {
                    if (l.isCheck) {
//                        ToastUtils.showShort("天地图")
                        aMap!!.addTileOverlay(opt_tdtn)
                        aMap!!.addTileOverlay(opt_tdtnN)
                    }
                }
                "产业园区" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                    }
                }
                "基准地价" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                    }
                }
                "土地利用2018" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                    }
                }
                "经济分布" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                    }
                }
                "规划" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
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

    override fun returnByPoint(message: List<BcProjectEntity>) {
        if (message.size>0){
            val intent = Intent(this,XmTzDetailActivity::class.java)
            intent.putExtra("id",message.get(0).id)
            startActivity(intent)
        }
    }

    override fun returnEnviorsByxy(fjNumBean: List<ProjectCountVO>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal) {

        clearAllMarker()
        for (i in fjNumBean){
            val zoom = aMap!!.cameraPosition.zoom
            var location : LatLng? =null
            /*val location = i.location
            if (!location.equals("")){
                val center1 = getCenter(location)
                setXcMarker(location, center1!!.latitude,center1.longitude,R.drawable.ic_hbjc_dcl_zs,i)

            }*/
            if (zoom<9&&type.equals("1")){
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }else if (zoom<11&&type.equals("2")){
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }else if (zoom<13&&type.equals("3")){//15
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }else if (zoom<15.5&&type.equals("4")){//15
                setWtMarker(i,type,xmin,xmax,ymin,ymax)
            }else if (type.equals("5")){
                if (!i.location.equals("")){
                    location = getCenter(i.location)
                    if (i.xmlx==1){
                        setMarker(i.location, location!!.latitude,location.longitude,R.drawable.icon_xm_you,i.id)
                    }else if (i.xmlx==2){
                        setMarker(i.location, location!!.latitude,location.longitude,R.drawable.icon_xm_yu,i.id)
                    }else if (i.xmlx==3){
                        setMarker(i.location, location!!.latitude,location.longitude,R.drawable.icon_xm_ju,i.id)
                    }else if (i.xmlx==4){
                        setMarker(i.location, location!!.latitude,location.longitude,R.drawable.icon_xm_shi,i.id)
                    }

                }

            }
        }

    }


    fun setWtMarker(enviorSupvsEntity : ProjectCountVO,type:String,
                    xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal) { //,int mid
        if (aMap!=null){

        }
        val mInflater = LayoutInflater.from(this)
        val view1: View = mInflater.inflate(R.layout.view_marker, null)

        val textView = view1!!.findViewById<View>(R.id.bv_view_marker) as BubbleView
        val counts = enviorSupvsEntity.counts
        if (counts<50){
            textView.setColor(Color.argb(255, 31, 163, 229))//255, 225, 219
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
        var location = ""
        if (type.equals("1")){
            textView.setText(AppCache.getInstance().xzqName+":"+counts)
            location = "POINT(116.397128 39.916527)"
        }else{
            textView.setText(enviorSupvsEntity.xzqmc+":"+counts)
            location = enviorSupvsEntity.location
        }
        var bitmap = convertViewToBitmap(view1)

        val zoom = aMap!!.cameraPosition.zoom
        if (!location.equals("")){
            var addMarker1: Marker? = null
            val center1 = getCenter(location)
            if (zoom<9&&type.equals("1")){
                addMarker1 = aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.xzqmc + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            }else if (zoom<11&&type.equals("2")&&zoom>=9){
                addMarker1 = aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.xzqmc + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            }else if (zoom<13&&type.equals("3")&&zoom>=11){//15
                addMarker1 = aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.xzqmc + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            }else if (zoom<15.5&&type.equals("4")&&zoom>=13){//15
                addMarker1 = aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.xzqmc + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            }else if (zoom>15.5&&type.equals("5")){//15
                addMarker1 = aMap!!.addMarker(MarkerOptions()
                        .position(center1)
                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.xzqmc + "-" + enviorSupvsEntity.location) //                .period(mid)//添加markerID
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .draggable(true))
            }
            markers.add(addMarker1!!)

            aMap!!.setOnMarkerClickListener { marker ->
                val position = marker.position
                aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(position, zoom+5f, 0f, 0f)))

                true
            }
        }


    }

    fun setMarker( j: String, wd: Double, jd: Double, res: Int, id: Long) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(this)
        val view1: View = mInflater.inflate(R.layout.item_marker, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position)
        ivItemMarker.setImageResource(res)
        itemMarkerPosition.text = j.toString() + ""
        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(this, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //绘制marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
                .position(LatLng(wd, jd))
                .snippet(id.toString() + "") //                .period(mid)//添加markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        markers.add(addMarker1)
        aMap!!.setOnMarkerClickListener { marker ->
            val type = AppCache.getInstance().type
            val snippet = marker.snippet
//            val i = snippet.toInt()

            val intent = Intent(this,XmTzDetailActivity::class.java)
            intent.putExtra("id",snippet.toLong())
            startActivity(intent)
//            mPresenter.getHbjcListCode(lastSelect,limit,1,code,hjzzsjCha,date,snippet,zdwtCha,1)//cunCode
//            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)

            true
        }
    }

    //新巡查热力图
    fun setXcMarker( j: String, wd: Double, jd: Double, res: Int, mapEntity: ProjectCountVO) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(this)
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
            ivItemMarker.setImageDrawable(this!!.getDrawable(R.drawable.ic_hbjc_dcl_zs))
        }
        itemMarkerPosition.text=mapEntity.xzqmc+mapEntity.counts

        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(this, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
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

    fun convertViewToBitmap(view: View): Bitmap {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }
    override fun onMapClick(p0: LatLng?) {
        /*if (addMarker!=null){
            addMarker!!.remove()
        }
        val markerOptions = MarkerOptions()
        addMarker = aMap!!.addMarker(markerOptions.position(p0))
        pointStr = "POINT(" + p0!!.longitude + " " + p0.latitude + ")"
        mPresenter.getByPoint(pointStr)*/
    }

    override fun onResume() {
        super.onResume()
        map_act_xm?.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_act_xm?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_act_xm?.onDestroy()
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
