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
import com.jymj.zhglxt.api.ApiConstants
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
import com.jymj.zhglxt.ui.activity.MainActivity
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_ldgl.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LdglFragment : BaseFragment<HomePresenter, HomeModel>(), HomeContract.View, AMap.OnMapClickListener, AMapLocationListener, View.OnClickListener {

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
    var aMapLocation: AMapLocation? = null//定位信息
    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/note/")
    var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)


    var mListener: LocationSource.OnLocationChangedListener? = null
    var mLocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    private var pointStr = ""
    private var addMarker: Marker? = null
    private var onCameraChange: LatLng? = null
    val MODE_NORMAL = 100//正常模式下
    private var click_mode = MODE_NORMAL
    val POINT_CLICK = 201//点查询模式
    val POLYGON_CLICK = 202//框选模式下
    val colors = java.util.ArrayList<Int>()//行业分布图例颜色
    var markers = ArrayList<Marker>()//所有聚合的点位
    private var  addPolyline: Polyline? = null//高亮显示
    var pointMarker: Marker? = null

    override fun lazyLoad() {

    }
    override fun getLayoutResource(): Int {
        return R.layout.frag_ldgl
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//添加天地图

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_frag_ldgl_dt.onCreate(activity!!.intent.extras)
//        ll_frag_rjhj_djgl.panelState = SlidingUpPanelLayout.PanelState.ANCHORED  //COLLAPSED关闭  ANCHORED展开
        initAMap()
        setAMap()
//        mPresenter.getSmewmcyl("0006")

        initTuli()
        if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF){
//            Mainfragivity.tvTitle?.text = "北臧村镇房屋出租流动人口管理"//镇级流动人口管理
            tv_frag_ldgl_czqk.setText("村庄宅房人统计列表")
            tv_frag_ldgl_cm.visibility = View.GONE
            mPresenter.getCxczqk("","")
        }else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY){
            tv_frag_ldgl_czqk.setText("村庄出租房统计列表")
            tv_frag_ldgl_cm.visibility = View.VISIBLE
            tv_frag_ldgl_cm.setText(AppCache.getInstance().xzqName)
            mPresenter.getCxldry(AppCache.getInstance().code,"","")
        }
        MainActivity.tvTitle?.text = AppCache.getInstance().xzqName+"房屋出租流动人口管理"//村级流动人口管理
        MainActivity.ibActMain?.setImageResource(R.drawable.qrcode_home)


        //点击定位
        iv_frag_ldgl_location.setOnClickListener {
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
        //调用二维码扫描
        MainActivity.ibActMain?.setOnClickListener {//iv_frag_ldgl_smewm
            if (SingleOnClickUtil1.isFastClick()) {
                // 创建IntentIntegrator对象
                var intentIntegrator = IntentIntegrator.forSupportFragment(this)//Scanfragivity//IntentIntegrator(activity)
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                intentIntegrator.setBarcodeImageEnabled(false)//设置是否保存图片
                intentIntegrator.setBeepEnabled(true)//设置扫码成功后的提示音是否显示
                intentIntegrator.setOrientationLocked(false)//该方法用于设置方向锁
                intentIntegrator.setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
                intentIntegrator.setCaptureActivity(ScanActivity::class.java) // 设置自定义的fragivity
                intentIntegrator.initiateScan() // 开始扫描
            }
        }
        //点击跳转个人中心
        iv_frag_ldgl_head.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()) {
                val intent = Intent(activity, MeActivity::class.java)
                startActivity(intent)
            }
        }
        aMap!!.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            //这个应该是地图的滑动监听吧
            override fun onCameraChangeFinish(p0: CameraPosition?) {
                onCameraChange = p0!!.target
                val visibleRegion = aMap!!.projection.visibleRegion
                val farLeft = visibleRegion.farLeft //可视区域的左上角。  position1
//               val farLeft = PositionUtil.gcj_To_Gps84(position1.latitude, position1.longitude)
                val farRight = visibleRegion.farRight //可视区域的右上角。
                val nearLeft = visibleRegion.nearLeft //可视区域的左下角。
                val nearright = visibleRegion.nearRight //可视区域的右下角。position2
                val zoom = aMap!!.cameraPosition.zoom
                if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF) {
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
                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//坐标转换  火星坐标 转 2000
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
        //延时定位到项目镇
        Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(1500); // 延时3秒
                    val point = getCenter(AppCache.getInstance().loginCenter)
                    /*aMap?.animateCamera(CameraUpdateFfragory.changeLatLng(LatLng(point.latitude,
                            point.longitude)),15)*/
                    if (AppCache.getInstance().code.length == 9) {
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 13.5f, 0f, 0f)))
                    } else {
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 15f, 0f, 0f)))
                    }
                    aMap?.addMarker(MarkerOptions().position(point))

//        mPresenter.getHylxtj()//查询首页行业类型统计
                    mPresenter.getJbqktj()//查询首页基本情况/行业类型统计
                    if (AppMenus.getInstance().menuBeans.toString().contains("消息查看")){
                        mPresenter.getWclCount()//获取未处理数量
                        tv_frag_ldgl_red.visibility = View.VISIBLE
                    }else{
                        tv_frag_ldgl_red.visibility = View.GONE
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()
        tv_frag_ldgl_ckgd.setOnClickListener {
            //查看更多列表
            if (SingleOnClickUtil1.isFastClick()) {
                if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF) {//镇
                    val intent = Intent(activity, CzlbActivity::class.java)
                    startActivity(intent)
                } else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY) {//村
                    val intent = Intent(activity, YllbActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        ll_frag_ldgl_point.setOnClickListener(this)
        ll_frag_ldgl_clear.setOnClickListener(this)
        /*ll_frag_ldgl_point.setOnClickListener {
            val point1 = LatLng(onCameraChange!!.latitude, onCameraChange!!.longitude)
            pointStr = "POINT(" + point1.longitude + " " + point1.latitude + ")"
            //绘制marker
            if (addMarker!=null){
                addMarker!!.remove()
            }
            val draggable = MarkerOptions()
                    .position(point1)
                    .draggable(false)

            addMarker = aMap!!.addMarker(draggable)
            mPresenter.getDcylxx(pointStr)

            *//*initPopuDingDian()
            CommenPop.backgroundAlpha(0.5f, fragivity)
            dingDianUpPopu!!.showAtLocation(ll_frag_rjhj, Gravity.CENTER, 0, 0)*//*
        }*/
    }

    override fun returnUser(user: User) {
    }

    override fun changeUser() {
    }

    override fun changeUser(msg: List<IndustryEntity>) {
    }
    //设置行业分布情况饼状图
    private fun setPieHyfbqk(bhgsCount: ArrayList<Float>, bhgsName: ArrayList<String>, colors: List<Int>) {
        if (pie_frag_ldgl_hyfbqk != null) {
            rkinitPie(pie_frag_ldgl_hyfbqk, "", bhgsCount, bhgsName, colors)//总数
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
        var colorList = ArrayList<Int>()//颜色
        val bhgsCount = ArrayList<Float>()//数量
        val bhgsName = ArrayList<String>()//类型
        var qtCount = 0f
        for (i in 0..msg.industry.size - 1) {
            if (i < 4 && !msg.industry.get(i).industryText.equals("其他")) {
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
            bhgsName.add("其他")
            colorList.add(colors.get(4))
            legendList.add(LegendBean("其他", qtCount.toInt(), colors.get(4)))
        }
        setPieHyfbqk(bhgsCount, bhgsName, colorList)//行业分布饼状图

        rlv_frag_ldgl_hyfbqk.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)//图例显示
        val hylxTlAdapter = HylxTlAdapter(activity, legendList)
        rlv_frag_ldgl_hyfbqk.adapter = hylxTlAdapter//LegendBean
        if (legendList.size==0){
            ll_frag_ldgl_ldrkhylx.visibility = View.GONE
        }else{
            ll_frag_ldgl_ldrkhylx.visibility = View.VISIBLE
        }
    }
    //点查院落返回的
    override fun returnDcylxx(msg: YlFolwEntity) {
        if (msg.geometry!=null){
            kuangGeomentLine(msg.geometry)
        }
        val intent = Intent(activity, LdrkDetailActivity::class.java)
        intent.putExtra("ylId", msg.objectid)
        startActivity(intent)
    }
    //查询村庄情况列表
    override fun returnCxczqk(msg: List<XzqInfoEntity>) {
        tv_frag_ldgl_czqk1.setText("共有${msg.size}个村")
        //显示少数村庄列表
        var czqkLsit = ArrayList<XzqInfoEntity>()
        for (i in msg.indices) {
            if (i < 5) {
                czqkLsit.add(msg.get(i))
            }
        }
        if (msg.size<5){
            tv_frag_ldgl_ckgd.visibility = View.GONE
            include_frag_ldgl_zwsj.visibility = View.VISIBLE
        }else{
            tv_frag_ldgl_ckgd.visibility = View.VISIBLE
            include_frag_ldgl_zwsj.visibility = View.GONE
        }

        rlv_frag_ldgl_czqk.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val zlglAdapter = CzqkAdapter(activity, czqkLsit)
        rlv_frag_ldgl_czqk.adapter = zlglAdapter
        zlglAdapter.setOnItemClick(object : CzqkAdapter.OnItemClick {
            override fun onClick(pjtaskFile: XzqInfoEntity?) {
                if (SingleOnClickUtil1.isFastClick()){
                    val intent = Intent(activity, YllbActivity::class.java)
                    intent.putExtra("code", pjtaskFile?.code)
                    intent.putExtra("xzqmc", pjtaskFile?.xzqmc)
                    startActivity(intent)
                }
            }
        })
    }
    //查询流动人口情况列表
    override fun returnCxldry(msg: List<YlFolwEntity>, total: Long) {

        tv_frag_ldgl_czqk1.setText("共有${total}户")
        //显示少数人口列表
        var czqkLsit = ArrayList<YlFolwEntity>()
        for (i in msg.indices) {
            if (i < 5) {
                czqkLsit.add(msg.get(i))
            }
        }
        if (msg.size<5){
            tv_frag_ldgl_ckgd.visibility = View.GONE
            include_frag_ldgl_zwsj.visibility = View.VISIBLE
        }else{
            tv_frag_ldgl_ckgd.visibility = View.VISIBLE
            include_frag_ldgl_zwsj.visibility = View.GONE
        }
        rlv_frag_ldgl_czqk.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val zlglAdapter = YllbAdapter(activity, czqkLsit)
        rlv_frag_ldgl_czqk.adapter =zlglAdapter
        zlglAdapter.setOnItemClick(object: YllbAdapter.OnYLLBItemClick{

            override fun onClick(pjtaskFile: YlFolwEntity?) {//点查
                if (SingleOnClickUtil.isFastClick()){
                    val intent = Intent(activity, LdrkDetailActivity::class.java)
                    intent.putExtra("ylId", pjtaskFile?.objectid)
                    startActivity(intent)
//                    mPresenter.getDcylxx("", pjtaskFile!!.objectid)
                }
            }
        })
    }
    //扫描二维码查找院落
    override fun returnSmewmcyl(msg: YlFolwEntity) {
        val intent = Intent(activity, LdrkDetailActivity::class.java)
        intent.putExtra("ylId", msg.objectid)
        startActivity(intent)
    }
    //热力图返回
    override fun returnRlt(msg: List<XzqInfoEntity>, type: Int) {
        clearAllMarker()
        if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF) {
            for (i in msg) {
                val zoom = aMap!!.cameraPosition.zoom
                if (type == 1 && zoom < 15) {
//                    setWtMarker(i,type)
                    setMarker(R.drawable.ic_hbjc_dcl_zs, i)
                }
            }
        }
    }

    fun setMarker(res: Int, enviorSupvsEntity: XzqInfoEntity) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(activity)
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

    fun convertViewToBitmap(view: View): Bitmap {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
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

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

    override fun onMapClick(p0: LatLng?) {
        when (click_mode) {
            POINT_CLICK -> {//点查询模式下地图监听
                if (SingleOnClickUtil.isFastClick()){
                    queryByPoint(p0!!)
                }
            }
            POLYGON_CLICK -> {//框选模式下地图监听
//                queryByPolygon(latLng)
            }
        }
    }
    /**
     * 显示marker到指定的位置并查询选择地点的信息
     */
    private fun queryByPoint(latLng: LatLng) {
//        clearAllMarker()
        pointStr = "POINT(" + latLng.longitude + " " + latLng.latitude + ")"
        //绘制marker
        if (addMarker != null) {
            addMarker!!.remove()
        }
        val draggable = MarkerOptions()
                .position(latLng)
                .draggable(false)

        addMarker = aMap!!.addMarker(draggable)
        mPresenter.getDcylxx(pointStr,null)

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
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
//        mLocationOption.
        //设置是否返回地址信息（默认返回地址信息）
// mLocationOption!!.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption!!.setOnceLocation(false)
        //获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
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
//     isFirstLoc = false;
                if (isFirstLoc) {
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
                            + aMapLocation.getStreetNum())
//                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();

                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
// Toast.makeText(fragivity!!.getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }
    /**
     * 设置当前选中的背景以及字体是否变色
     */
    override fun onClick(v: View?) {
        switchRefragive(v)
        when (v!!.id) {
            R.id.ll_frag_ldgl_point -> {//点查按钮
                if (v.isActivated) {//选中
                    iv_frag_ldgl_point.setImageResource(R.drawable.point_blue)
                    click_mode = POINT_CLICK
                } else {//未选中
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
     * 设置当前选中的背景以及字体是否变色
     */
    private fun switchRefragive(bt: View?) {

        bt?.isActivated = !bt!!.isActivated
    }

    //问题统计饼状图
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
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_ldgl_dt.getMap()
        }
        setUpMap()
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() { // 自定义系统定位蓝点
//        aMap!!.setLocationSource(this);
        mMyLocationStyle = MyLocationStyle()
        //自定义精度范围的圆形边框宽度
        mMyLocationStyle!!.strokeWidth(3f)
        mMyLocationStyle!!.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        aMap!!.uiSettings.isMyLocationButtonEnabled = true // 设置默认定位按钮是否显示
        //        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap!!.setMyLocationStyle(mMyLocationStyle)
        aMap!!.isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
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

    override fun onResume() {
        super.onResume()
        map_frag_ldgl_dt.onResume()
        mPresenter.getJbqktj()//统计
        if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF){
            mPresenter.getCxczqk("","")
        }else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY){
            mPresenter.getCxldry(AppCache.getInstance().code,"","")
        }
        if (AppMenus.getInstance().menuBeans.toString().contains("消息查看")){
            mPresenter.getWclCount()//获取未处理数量
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

    override fun onDestroyView() {
        super.onDestroyView()
        map_frag_ldgl_dt.onDestroy()
    }

    private fun initTuli() {
        //行业分布图例
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
        //地图图层
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!
        for (i in mLayers) {//房屋
            i.isCheck = false
            when (i.name) {
                "天地图" -> {
                    i.isCheck = true
                }
                "流动人口" -> {
                    i.isCheck = true
                }
                "镇界" -> {
                    i.isCheck = true
                }
                "村界" -> {
                    i.isCheck = true
                }
            }
        }
        //添加图层
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
                "底图" ->{
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtOsm)
                    }
                }
                "天地图" ->{
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtn)
                        aMap!!.addTileOverlay(opt_tdtnN)
                    }
                }
                "影像图" ->{
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtSta)
                        aMap!!.addTileOverlay(opt_tdtStaN)
                    }
                }
                "院落" -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
                else -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
            }
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
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
                        addPolyline = aMap!!.addPolyline(mPolylineOptions)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 获取解析结果  二维码扫描结果
        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);//IntentResult
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
}