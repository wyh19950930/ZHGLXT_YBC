package com.jymj.zhglxt.home.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.*
import com.amap.api.maps2d.model.*
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.zxing.integration.android.IntentIntegrator
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.home.activity.LdrkDetailActivity
import com.jymj.zhglxt.ldrkgl.home.activity.ScanActivity
import com.jymj.zhglxt.ldrkgl.home.adapter.CzqkAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.HylxTlAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.YllbAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.HomeContract
import com.jymj.zhglxt.login.model.HomeModel
import com.jymj.zhglxt.login.presenter.HomePresenter
import com.jymj.zhglxt.personal.activity.MeActivity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.BubbleView
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_ldgl.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LdrkActivity : BaseActivity<HomePresenter, HomeModel>(), HomeContract.View, AMap.OnMapClickListener, AMapLocationListener, View.OnClickListener {

//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private var aMap: AMap? = null
    private var mPolygon: Polygon? = null
    private val mLatLngs = ArrayList<LatLng>()
    private var mMyLocationStyle: MyLocationStyle? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var mPolylineOptions: PolylineOptions? = null
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    var aMapLocation: AMapLocation? = null//????????????
    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/note/")
    var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, GetFileUtil.getSDCardPath() + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, GetFileUtil.getSDCardPath() + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    val tdtOsm = TDTOSMTileProvider("", GetFileUtil.getSDCardPath() + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)


    var mListener: LocationSource.OnLocationChangedListener? = null
    var mLocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    private var pointStr = ""
    private var addMarker: Marker? = null
    private var onCameraChange: LatLng? = null
    val MODE_NORMAL = 100//???????????????
    private var click_mode = MODE_NORMAL
    val POINT_CLICK = 201//???????????????
    val POLYGON_CLICK = 202//???????????????
    val colors = java.util.ArrayList<Int>()//????????????????????????
    var markers = ArrayList<Marker>()//?????????????????????
    private var  addPolyline:Polyline? = null//????????????
    var pointMarker: Marker? = null

    override fun getLayoutId(): Int {
        return R.layout.frag_ldgl
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//???????????????tdt-bdkb

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_frag_ldgl_dt.onCreate(intent.extras)
//        ll_frag_rjhj_djgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED  //COLLAPSED??????  ANCHORED??????
        initAMap()
        setAMap()
//        mPresenter.getSmewmcyl("0006")

        initTuli()
        if (AppCache.getInstance().zhqx == 1){
            tv_frag_ldgl_title.setText("??????????????????????????????????????????")//????????????????????????
            tv_frag_ldgl_czqk.setText("???????????????????????????")
            tv_frag_ldgl_cm.visibility = View.GONE
            mPresenter.getCxczqk("","")
        }else if (AppCache.getInstance().zhqx == 2){
            tv_frag_ldgl_title.setText(AppCache.getInstance().xzqName+"??????????????????????????????")//????????????????????????
            tv_frag_ldgl_czqk.setText("???????????????????????????")
            tv_frag_ldgl_cm.visibility = View.VISIBLE
            tv_frag_ldgl_cm.setText(AppCache.getInstance().xzqName)
            mPresenter.getCxldry(AppCache.getInstance().code,"","")
        }


        //????????????
        iv_frag_ldgl_location.setOnClickListener {
            //??????
            if (aMapLocation != null) {
                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//????????????  ???????????? ??? 2000
                val point = LatLng(model.wgLat, model.wgLon)
//            val point = LatLng(myLocation.latitude, myLocation.longitude)
                if (point.latitude > 0 && point.longitude > 0) {//point.longitude
                    aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude,
                            point.longitude)))
                    pointMarker = aMap?.addMarker(MarkerOptions().position(point))

                    var pointString = "POINT(" + point.longitude + " " + point.latitude + ")"
                    //????????????
                } else {
                    Thread(object : Runnable {
                        override fun run() {
                            try {
                                Thread.sleep(2000); // ??????3???
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
                                //????????????
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
        //?????????????????????
        iv_frag_ldgl_smewm.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()) {
                // ??????IntentIntegrator??????
                var intentIntegrator = IntentIntegrator(this)//ScanActivity
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                intentIntegrator.setBarcodeImageEnabled(false)//????????????????????????
                intentIntegrator.setBeepEnabled(true)//?????????????????????????????????????????????
                intentIntegrator.setOrientationLocked(false)//??????????????????????????????
                intentIntegrator.setPrompt("????????????/???????????????????????????????????????")//?????????????????????
                intentIntegrator.setCaptureActivity(ScanActivity::class.java) // ??????????????????activity
                intentIntegrator.initiateScan() // ????????????
            }
        }
        //????????????????????????
        iv_frag_ldgl_head.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()) {
                val intent = Intent(this, MeActivity::class.java)
                startActivity(intent)
            }
        }
        aMap!!.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            //???????????????????????????????????????
            override fun onCameraChangeFinish(p0: CameraPosition?) {
                onCameraChange = p0!!.target
                val visibleRegion = aMap!!.projection.visibleRegion
                val farLeft = visibleRegion.farLeft //???????????????????????????  position1
//               val farLeft = PositionUtil.gcj_To_Gps84(position1.latitude, position1.longitude)
                val farRight = visibleRegion.farRight //???????????????????????????
                val nearLeft = visibleRegion.nearLeft //???????????????????????????
                val nearright = visibleRegion.nearRight //???????????????????????????position2
                val zoom = aMap!!.cameraPosition.zoom
                if (AppCache.getInstance().zhqx == 1) {
                    if (zoom < 12) {
                        clearAllMarker()
                    } else if (zoom < 15) {//15
                        mPresenter.getRlt(AppCache.getInstance().code, 1, BigDecimal(farLeft.longitude), BigDecimal(nearright.longitude), BigDecimal(nearright.latitude), BigDecimal(farLeft.latitude))
                    } else {
                        clearAllMarker()
                    }
                }
                if (pointMarker!=null){
                    pointMarker!!.remove()
                }
                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//????????????  ???????????? ??? 2000
                val point = LatLng(model.wgLat, model.wgLon)
                pointMarker = aMap?.addMarker(MarkerOptions().position(point))
            }
            override fun onCameraChange(p0: CameraPosition?) {
                if (p0!!.zoom > 18.5) {
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(18.5f))
                }
                onCameraChange = p0!!.target
            }
        })
        location()
    }

    override fun initDatas() {
        Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(1500); // ??????3???
                    val point = getCenter(AppCache.getInstance().loginCenter)
                    /*aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude,
                            point.longitude)),15)*/
                    if (AppCache.getInstance().code.length == 9) {
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 13.5f, 0f, 0f)))
                    } else {
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 15f, 0f, 0f)))
                    }

                    aMap?.addMarker(MarkerOptions().position(point))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()
//        mPresenter.getHylxtj()//??????????????????????????????
        mPresenter.getJbqktj()//????????????????????????/??????????????????
        if (AppMenus.getInstance().menuBeans.toString().contains("????????????")){
            mPresenter.getWclCount()//?????????????????????
            tv_frag_ldgl_red.visibility = View.VISIBLE
        }else{
            tv_frag_ldgl_red.visibility = View.GONE
        }
        tv_frag_ldgl_ckgd.setOnClickListener {
            //??????????????????
            if (SingleOnClickUtil1.isFastClick()) {
                if (AppCache.getInstance().zhqx == 1) {//???
                    val intent = Intent(this, CzlbActivity::class.java)
                    startActivity(intent)
                } else if (AppCache.getInstance().zhqx == 2) {//???
                    val intent = Intent(this, YllbActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        ll_frag_ldgl_point.setOnClickListener(this)
        ll_frag_ldgl_clear.setOnClickListener(this)
        /*ll_frag_ldgl_point.setOnClickListener {
            val point1 = LatLng(onCameraChange!!.latitude, onCameraChange!!.longitude)
            pointStr = "POINT(" + point1.longitude + " " + point1.latitude + ")"
            //??????marker
            if (addMarker!=null){
                addMarker!!.remove()
            }
            val draggable = MarkerOptions()
                    .position(point1)
                    .draggable(false)

            addMarker = aMap!!.addMarker(draggable)
            mPresenter.getDcylxx(pointStr)

            *//*initPopuDingDian()
            CommenPop.backgroundAlpha(0.5f, activity)
            dingDianUpPopu!!.showAtLocation(ll_frag_rjhj, Gravity.CENTER, 0, 0)*//*
        }*/
    }

    override fun onClick(v: View?) {
        switchReactive(v)
        when (v!!.id) {
            R.id.ll_frag_ldgl_point -> {//????????????
                if (v.isActivated) {//??????
                    iv_frag_ldgl_point.setImageResource(R.drawable.point_blue)
                    click_mode = POINT_CLICK
                } else {//?????????
                    iv_frag_ldgl_point.setImageResource(R.drawable.point_black)
                    click_mode = MODE_NORMAL
                }

            }
            R.id.ll_frag_ldgl_clear -> {
                clearMap()
            }
        }
    }

    /**
     * ???????????????????????????????????????????????????
     */
    private fun switchReactive(bt: View?) {

        bt?.isActivated = !bt!!.isActivated
    }

    //?????????????????????
    private fun rkinitPie(pie: PieChart, title: String, datas: java.util.ArrayList<Float>, lables: java.util.ArrayList<String>, colors: List<Int>) {
        pie.setUsePercentValues(false)
        pie.description.isEnabled = false
        pie.setExtraOffsets(5f, 10f, 5f, 5f)

        pie.dragDecelerationFrictionCoef = 0.95f

        pie.setExtraOffsets(10f, 0f, 10f, 0f)

        pie.isDrawHoleEnabled = true
        pie.setHoleColor(Color.WHITE)

        pie.setTransparentCircleColor(Color.WHITE)
        pie.setTransparentCircleAlpha(110)

        pie.holeRadius = 58f
        pie.transparentCircleRadius = 61f

        pie.setDrawCenterText(true)

        pie.centerText = title
        pie.setCenterTextSize(20f)
        pie.rotationAngle = 0f
        // enable rotation of the chart by touch
        pie.isRotationEnabled = true
        pie.isHighlightPerTapEnabled = true
        pie.setEntryLabelColor(Color.BLACK)
        setRKData(pie, datas, lables, colors)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    private fun setRKData(pie: PieChart, datas: List<Float>, lables: List<String>, colors: List<Int>) {
        val entries = java.util.ArrayList<PieEntry>()

        var zs = 0f
        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
            zs = zs + datas[i]
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val format = DecimalFormat("###.#")
                val format1 = DecimalFormat("###")
                return "${format.format(value / zs * 100)}%"//super.getFormattedValue(value)  format1.format(value)+
            }
        }
        // add a lot of colors


        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = com.github.mikephil.charting.data.PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data
        // undo all highlights
        pie.highlightValues(null)
        pie.invalidate()
    }

    /**
     * ?????????AMap??????
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_ldgl_dt.getMap()
        }
        setUpMap()
    }

    /**
     * ????????????amap?????????
     */
    private fun setUpMap() { // ???????????????????????????
//        aMap!!.setLocationSource(this);
        mMyLocationStyle = MyLocationStyle()
        //??????????????????????????????????????????
        mMyLocationStyle!!.strokeWidth(3f)
        mMyLocationStyle!!.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        aMap!!.uiSettings.isMyLocationButtonEnabled = true // ????????????????????????????????????
        //        aMap.setMyLocationEnabled(true);// ?????????true??????????????????????????????????????????false??????????????????????????????????????????????????????false
// ???????????????????????????????????? ??????????????????????????????????????????????????????????????????
        aMap!!.setMyLocationStyle(mMyLocationStyle)
        aMap!!.isMyLocationEnabled = true // ?????????true?????????????????????????????????false??????????????????????????????????????????????????????false???
        mMyLocationStyle!!.showMyLocation(true)
    }

    /**
     * ????????????
     */
    private fun setAMap() { //????????????????????????????????????
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false // ???????????????
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
        for (m in markers) {//???????????????????????????
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
                    // CoordType.GPS ?????????????????????
                    converter.from(CoordinateConverter.CoordType.GPS)
                    val sl = LatLng(points[1].toDouble(), points[0].toDouble())
                    // sourceLatLng?????????????????? LatLng??????
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
        map_frag_ldgl_dt.onResume()
        mPresenter.getJbqktj()//??????
        if (AppCache.getInstance().zhqx == 1){
            mPresenter.getCxczqk("","")
        }else if (AppCache.getInstance().zhqx == 2){
            mPresenter.getCxldry(AppCache.getInstance().code,"","")
        }
        if (AppMenus.getInstance().menuBeans.toString().contains("????????????")){
            mPresenter.getWclCount()//?????????????????????
        }
    }

    override fun onStop() {
        super.onStop()
        if (mLocationClient != null)
            mLocationClient!!.stopLocation()
    }

    override fun onPause() {
        super.onPause()
        map_frag_ldgl_dt.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_frag_ldgl_dt.onDestroy()
    }

    private fun initTuli() {
        //??????????????????
        /*for (c in ColorTemplate.VORDIPLOM_COLORS){
            colors.add(c)
        }
        for (c in ColorTemplate.COLORFUL_COLORS){
            colors.add(c)
        }*/
        colors.add(Color.argb(199, 255, 102, 15))
        colors.add(Color.argb(199, 64, 158, 255))
        colors.add(Color.argb(199, 128, 143, 238))
        colors.add(Color.argb(199, 46, 201, 250))
        colors.add(Color.argb(199, 138, 149, 153))
        //????????????
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!
        for (i in mLayers) {//??????
            i.isCheck = false
            when (i.name) {
                "?????????" -> {
                    i.isCheck = true
                }
                "????????????" -> {
                    i.isCheck = true
                }
                "??????" -> {
                    i.isCheck = true
                }
                "??????" -> {
                    i.isCheck = true
                }
            }
        }
        //????????????
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
    }
    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {
        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        /*iv_map_tuli.visibility = View.VISIBLE
        ll_map_tuli.visibility = View.GONE*/
        for (l in layers) {
            when (l.name) {
                "??????" ->{
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtOsm)
                    }
                }
                "?????????" ->{
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtn)
                        aMap!!.addTileOverlay(opt_tdtnN)
                    }
                }
                "?????????" ->{
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtSta)
                        aMap!!.addTileOverlay(opt_tdtStaN)
                    }
                }
                "??????" -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
                else -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
            }
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
    }

    override fun returnUser(user: User) {

    }

    override fun changeUser() {

    }

    override fun changeUser(msg: List<IndustryEntity>) {

    }

    //?????????????????????????????????
    private fun setPieHyfbqk(bhgsCount: ArrayList<Float>, bhgsName: ArrayList<String>, colors: List<Int>) {
        if (pie_frag_ldgl_hyfbqk != null) {
            rkinitPie(pie_frag_ldgl_hyfbqk, "", bhgsCount, bhgsName, colors)//??????
        }
    }

    override fun returnJbqktj(msg: FirstTjBean) {
        if (msg.xzqInfo != null) {
            tv_frag_ldgl_zjdcz.setText("" + msg.xzqInfo.zhaicount)
            tv_frag_ldgl_fjcz.setText("" + msg.xzqInfo.roomcount)
            tv_frag_ldgl_ldrk.setText("" + msg.xzqInfo.flowcount)
        }
        var legendList = ArrayList<LegendBean>()
        /*for (i in 0..IndustryEnum.values().size-1){
            legendList.add(LegendBean(IndustryEnum.values().get(i).getName(),colors.get(i)))
        }*/
        var colorList = ArrayList<Int>()//??????
        val bhgsCount = ArrayList<Float>()//??????
        val bhgsName = ArrayList<String>()//??????
        var qtCount = 0f
        for (i in 0..msg.industry.size - 1) {
            if (i < 4 && !msg.industry.get(i).industryText.equals("??????")) {
                bhgsCount.add(msg.industry.get(i).count.toFloat())
                bhgsName.add(msg.industry.get(i).industryText)
                colorList.add(colors.get(i))
                legendList.add(LegendBean(msg.industry.get(i).industryText, msg.industry.get(i).count, colors.get(i)))
            } else {
                qtCount = qtCount + msg.industry.get(i).count
            }
        }
        if (msg.industry.size > 4) {
            bhgsCount.add(qtCount)
            bhgsName.add("??????")
            colorList.add(colors.get(4))
            legendList.add(LegendBean("??????", qtCount.toInt(), colors.get(4)))
        }
        setPieHyfbqk(bhgsCount, bhgsName, colorList)//?????????????????????

        rlv_frag_ldgl_hyfbqk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)//????????????
        val hylxTlAdapter = HylxTlAdapter(this, legendList)
        rlv_frag_ldgl_hyfbqk.adapter = hylxTlAdapter//LegendBean
        if (legendList.size==0){
            ll_frag_ldgl_ldrkhylx.visibility = View.GONE
        }else{
            ll_frag_ldgl_ldrkhylx.visibility = View.VISIBLE
        }

    }

    //?????????????????????
    override fun returnDcylxx(msg: YlFolwEntity) {
        if (msg.geometry!=null){
            kuangGeomentLine(msg.geometry)
        }
        val intent = Intent(this, LdrkDetailActivity::class.java)
        intent.putExtra("ylId", msg.objectid)
        startActivity(intent)
    }

    //???????????????????????????
    override fun returnSmewmcyl(msg: YlFolwEntity) {
        val intent = Intent(this, LdrkDetailActivity::class.java)
        intent.putExtra("ylId", msg.objectid)
        startActivity(intent)
    }

    //????????????????????????
    override fun returnCxczqk(msg: List<XzqInfoEntity>) {
        tv_frag_ldgl_czqk1.setText("??????${msg.size}??????")
        //????????????????????????
        var czqkLsit = ArrayList<XzqInfoEntity>()
        for (i in msg.indices) {
            if (i < 5) {
                czqkLsit.add(msg.get(i))
            }
        }
        if (msg.size<5){
            tv_frag_ldgl_ckgd.visibility = View.GONE
        }
        if (msg.isNotEmpty()){
            include_frag_ldgl_zwsj.visibility = View.GONE
        }
        rlv_frag_ldgl_czqk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val zlglAdapter = CzqkAdapter(this, czqkLsit)
        rlv_frag_ldgl_czqk.adapter = zlglAdapter
        zlglAdapter.setOnItemClick(object : CzqkAdapter.OnItemClick {
            override fun onClick(pjtaskFile: XzqInfoEntity?) {
                if (SingleOnClickUtil1.isFastClick()){
                    val intent = Intent(this@LdrkActivity, YllbActivity::class.java)
                    intent.putExtra("code", pjtaskFile?.code)
                    intent.putExtra("xzqmc", pjtaskFile?.xzqmc)
                    startActivity(intent)
                }
            }
        })
    }

    //??????????????????????????????
    override fun returnCxldry(msg: List<YlFolwEntity>,total:Long) {
        tv_frag_ldgl_czqk1.setText("??????${total}???")
        //????????????????????????
        var czqkLsit = ArrayList<YlFolwEntity>()
        for (i in msg.indices) {
            if (i < 5) {
                czqkLsit.add(msg.get(i))
            }
        }
        if (msg.size<5){
            tv_frag_ldgl_ckgd.visibility = View.GONE
        }
        if (msg.isNotEmpty()){
            include_frag_ldgl_zwsj.visibility = View.GONE
        }
        rlv_frag_ldgl_czqk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val zlglAdapter = YllbAdapter(this, czqkLsit)
        rlv_frag_ldgl_czqk.adapter =zlglAdapter
        zlglAdapter.setOnItemClick(object: YllbAdapter.OnYLLBItemClick{

            override fun onClick(pjtaskFile: YlFolwEntity?) {//??????
                if (SingleOnClickUtil1.isFastClick()){
                    val intent = Intent(this@LdrkActivity, LdrkDetailActivity::class.java)
                    intent.putExtra("ylId", pjtaskFile?.objectid)
                    startActivity(intent)
//                    mPresenter.getDcylxx("", pjtaskFile!!.objectid)
                }
            }
        })
    }

    //???????????????
    override fun returnRlt(msg: List<XzqInfoEntity>, type: Int) {
        clearAllMarker()
        if (AppCache.getInstance().zhqx == 1) {
            for (i in msg) {
                val zoom = aMap!!.cameraPosition.zoom
                if (type == 1 && zoom < 15) {
//                    setWtMarker(i,type)
                    setMarker(R.drawable.ic_hbjc_dcl_zs, i)
                }
            }
        }
    }

    override fun returnWclCount(count: Int) {
        if (count == 0) {
            tv_frag_ldgl_red.visibility = View.GONE
        }else{
            tv_frag_ldgl_red.visibility = View.VISIBLE
        }
        tv_frag_ldgl_red.setText("${count}")
        if (count>99){
            tv_frag_ldgl_red.setText("99+")
        }
    }

    fun setWtMarker(enviorSupvsEntity: XzqInfoEntity, type: Int) { //,int mid
        if (aMap != null) {

        }
        val mInflater = LayoutInflater.from(this)
        val view1: View = mInflater.inflate(R.layout.view_marker, null)

        val textView = view1!!.findViewById<View>(R.id.bv_view_marker) as BubbleView
        val counts = enviorSupvsEntity.flowcount
        if (counts < 50) {
            textView.setColor(Color.argb(255, 255, 225, 219))
        } else if (counts < 150) {
            textView.setColor(Color.argb(255, 255, 196, 179))
        } else if (counts < 300) {
            textView.setColor(Color.argb(255, 255, 153, 133))
        } else if (counts < 600) {
            textView.setColor(Color.argb(255, 245, 117, 103))
        } else if (counts < 1000) {
            textView.setColor(Color.argb(255, 230, 69, 70))
        } else if (counts < 10000) {
            textView.setColor(Color.argb(255, 184, 9, 9))
        } else if (counts > 10000) {
            textView.setColor(Color.argb(255, 138, 8, 8))
        }
//        textView.setTextColor(Color.BLACK)
        textView.setText(enviorSupvsEntity.xzqmc + ":" + counts)
        var bitmap = convertViewToBitmap(view1);

        val zoom = aMap!!.cameraPosition.zoom
        val location = enviorSupvsEntity.center
        if (!location.equals("")) {
            val center1 = getCenter(location)
            if (AppCache.getInstance().zhqx == 1) {
                if (zoom < 15 && type == 1) {
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            //type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location
                            .snippet("") //                .period(mid)//??????markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                } else if (zoom < 15.5 && type.equals("3") && zoom >= 13) {//15
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            //type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location
                            .snippet("") //                .period(mid)//??????markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }
            }


            aMap!!.setOnMarkerClickListener { marker ->
                val position = marker.position
                aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(position, zoom + 5f, 0f, 0f)))

                true
            }
        }


    }


    fun setMarker(res: Int, enviorSupvsEntity: XzqInfoEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(this)
        val view1: View = mInflater.inflate(R.layout.item_marker1, null)
        val rlMarker = view1.findViewById<RelativeLayout>(R.id.rlt_item_marker)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        val countTv = view1.findViewById<TextView>(R.id.item_marker_position2)
        ivItemMarker.setImageResource(res)

        val para = ivItemMarker.getLayoutParams()
        val counts = enviorSupvsEntity.flowcount

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
        //??????marker
        val addMarker1 = aMap!!.addMarker(MarkerOptions()
//                .position(LatLng(wd, jd))
                .position(getCenter(enviorSupvsEntity.center))
                .snippet("") //                .period(mid)//??????markerID
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
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(50, 64, 64, 255)) // ?????????????????????
                    } else {
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(0, 0, 0, 0)) // ?????????????????????
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
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(50, 64, 64, 255)) // ?????????????????????
                    } else {
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(0, 0, 0, 0)) // ?????????????????????
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
    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

    override fun onMapClick(p0: LatLng?) {//getDcylxx
        when (click_mode) {
            POINT_CLICK -> {//??????????????????????????????
                if (SingleOnClickUtil.isFastClick()){
                    queryByPoint(p0!!)
                }
            }
            POLYGON_CLICK -> {//???????????????????????????
//                queryByPolygon(latLng)
            }
        }
    }

    /**
     * ??????marker????????????????????????????????????????????????
     */
    private fun queryByPoint(latLng: LatLng) {
//        clearAllMarker()
        pointStr = "POINT(" + latLng.longitude + " " + latLng.latitude + ")"
        //??????marker
        if (addMarker != null) {
            addMarker!!.remove()
        }
        val draggable = MarkerOptions()
                .position(latLng)
                .draggable(false)

        addMarker = aMap!!.addMarker(draggable)
        mPresenter.getDcylxx(pointStr,null)

    }

    //?????????????????????
    private fun location() {
        //???????????????
        mLocationClient = AMapLocationClient(getApplicationContext())
        //????????????????????????
        mLocationClient!!.setLocationListener(this)
        //?????????????????????
        mLocationOption = AMapLocationClientOption()
        //????????????????????? Hight_Accuracy ??????????????????Battery_Saving ?????????????????????Device_Sensors??????????????????
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
//        mLocationOption.
        //????????????????????????????????????????????????????????????
// mLocationOption!!.setNeedAddress(true);
        //???????????????????????????,?????????false
        mLocationOption!!.setOnceLocation(false)
        //????????????3s???????????????????????????????????????
//??????setOnceLocationLatest(boolean b)?????????true??????????????????SDK???????????????3s?????????????????????????????????????????????????????????true???setOnceLocation(boolean b)????????????????????????true???????????????????????????false???
        mLocationOption!!.setOnceLocationLatest(true)
        //????????????????????????WIFI????????????????????????
// mLocationOption!!.setWifiActiveScan(true);
        //??????????????????????????????,?????????false????????????????????????
        mLocationOption!!.setMockEnable(false)
        mLocationOption!!.setLocationCacheEnable(false)
        //??????????????????,????????????,?????????2000ms
// mLocationOption!!.setInterval(2000);
        //??????????????????????????????????????????
        mLocationClient!!.setLocationOption(mLocationOption)
        //????????????
        mLocationClient!!.startLocation()
//        mLocationClient!!.stopLocation()
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
                var df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
//     isFirstLoc = false;
                if (isFirstLoc) {
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
                            + aMapLocation.getStreetNum())
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();

                }


            } else {
                //??????????????????ErrCode???????????????errInfo???????????????????????????????????????
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
// Toast.makeText(activity!!.getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // ??????????????????  ?????????????????????
        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);//IntentResult
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "????????????", Toast.LENGTH_LONG).show();
            } else {
                if (result.getContents() != null) {
                    val split = result.getContents().split("id=")
                    if (split.size > 1) {
                        mPresenter.getSmewmcyl(split[1])
                    } else {
                        mPresenter.getSmewmcyl(result.getContents())
                    }
                } else {
                    ToastUtils.showShort("?????????????????????")
                }

//                Toast.makeText(this, "????????????:" + result.getContents(), Toast.LENGTH_LONG).show();
//                Log.e("feartttt","${result.getBarcodeImagePath()}")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}