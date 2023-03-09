package com.jymj.zhglxt.zjd.activity.zjdgl

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.home.activity.YllbActivity
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.rjhj.adapter.RightArrowAdapter
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.util.RltUtil.getCenter
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.jymj.zhglxt.widget.RecyclerViewNoBugLinearLayoutManager
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.zjd.adapter.WyCzListAdapter
import com.jymj.zhglxt.zjd.adapter.WyFzListAdapter
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.wy.RentEntity
import com.jymj.zhglxt.zjd.contract.PropertyAddFzContract
import com.jymj.zhglxt.zjd.contract.PropertyContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.presenter.PropertyPresenter
import com.loc.bh
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.act_property_fwcz_include.*
import kotlinx.android.synthetic.main.act_property_fwfz_include.*
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.frag_zjdgl.*

/*
* 物业activity
* */
class PropertyActivity : BaseActivity<PropertyPresenter, BaseModel>(), PropertyContract.View, AMap.OnMapClickListener
        , AMapLocationListener {

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
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/note/")
    var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)

    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

    private var mLatLng: LatLng? = null

    private val tabTitles = arrayListOf<String>("房屋返租", "房屋出租")//, "下发"  , "待审批"  , "待处理"
    private var envirorUpPopu: CommenPop? = null

    val MODE_NORMAL = 100
    val POINT_CLICK = 201//点查
    val POLYGON_CLICK = 202//框查
    private var click_mode: Int = MODE_NORMAL
    var stringExtra = ""
    var limit = 20
    var page = 1
    var mphName = ""
    var cqrName = ""
    var jumpType = -1
    override fun getLayoutId(): Int {
        return R.layout.activity_property
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//添加天地图  VEC_URL, "tdt1-vec"

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_act_property.onCreate(this.intent.extras)

        if (AppCache.getInstance().wyCode==null||AppCache.getInstance().wyCode.equals("")){
            stringExtra = AppCache.getInstance().code
        }else{
            stringExtra = AppCache.getInstance().wyCode
        }
        if (AppCache.getInstance().wyXzqmc!=null||!AppCache.getInstance().wyXzqmc.equals("")){
            if (AppCache.getInstance().wyXzqmc.equals("总计")){
                iv_at_property_title.text = "全部"
            }else{
                iv_at_property_title.text = AppCache.getInstance().wyXzqmc
            }
        }

        initAMap()
        setAMap()

        supl_act_property.panelHeight = DisplayUtil.dip2px(50f)
        supl_act_property.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

        for (i in 0..tabTitles.size - 1) {
            tl_act_property.addTab(tl_act_property.newTab().setText(tabTitles[i]))
        }
        tl_act_property.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.text){
                    "房屋返租"->{
                        act_property_fwfz_include.visibility = View.VISIBLE
                        act_property_fwcz_include.visibility = View.GONE
                        mPresenter.getWyFzList(stringExtra,cqrName,mphName,limit,page)
                    }
                    "房屋出租"->{
                        act_property_fwfz_include.visibility = View.GONE
                        act_property_fwcz_include.visibility = View.VISIBLE
                        mPresenter.getWyCzList(stringExtra,cqrName,mphName,limit,page)
                    }
                }
            }
        })

        tv_act_wy_fz_search.setOnClickListener {
            val bhString = et_act_wy_fz_search.text.toString()//bhString
            page = 1
            mPresenter.getWyFzList(stringExtra,bhString,bhString,limit,page)
        }
        tv_act_wy_cz_search.setOnClickListener {
            val bhString = et_act_wy_cz_search.text.toString()//bhString
            page = 1
            mPresenter.getWyCzList(stringExtra,bhString,bhString,limit,page)
        }


    }

    override fun initDatas() {
        moveCenter()
        tabTc("流动人口",1)

        iv_at_property_finish.setOnClickListener {
            finish()
        }
        bt_property_point.setOnClickListener {

            if (bt_property_point.isActivated){
                bt_property_point.isActivated = false
                click_mode = MODE_NORMAL
            }else{
                bt_property_point.isActivated = true
                click_mode = POINT_CLICK
            }

        }

        mPresenter.getWyFzList(stringExtra,cqrName,mphName,limit,page)

    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_act_property.getMap()
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
    override fun onMapClick(p0: LatLng?) {
        when (click_mode) {
            POINT_CLICK -> {
                queryByPoint(p0!!)
                mLatLng = p0
            }
            POLYGON_CLICK -> {
//                queryByPolygon(p0!!)
            }
        }
    }

    private fun queryByPoint(latLng: LatLng) {
        if (SingleOnClickUtil.isFastClick()) {
            if (addMarker != null) {
                addMarker!!.remove()
            }
            val markerOptions = MarkerOptions()
            addMarker = aMap!!.addMarker(markerOptions.position(latLng))
            pointStr = "POINT(" + latLng.longitude + " " + latLng.latitude + ")"
            jumpType = -1
            mPresenter.getWyPoint(pointStr,0)
        }
    }

    override fun onLocationChanged(p0: AMapLocation?) {
    }

    private fun tabTc(tuCe:String,type:Int) {//type 1 清 2 不清  , type:Int
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
            addOverLayer(mLayers)//问题在这里
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
        }

    }
    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {
        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
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
    //添加图层
    private fun initTuli() {
        //地图图层
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!
        addOverLayer(mLayers)
    }
    //移动到镇/村中心点
    private fun moveCenter() {
        initTuli()//图层
        Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(1000); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法
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
        }).start()
    }

    override fun onResume() {
        super.onResume()
        map_act_property.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_act_property.onPause()
    }

    //物业点查
    override fun returnWyPoint(entity: PropertyDto) {
        if (entity!=null) {
            if (jumpType == 1) {
                val intent = Intent(this, PropertyAddFzActivity::class.java)
                intent.putExtra("PropertyDto",entity)
                startActivity(intent)
            } else if (jumpType == 2){
                val intent = Intent(this, PropertyYlfjActivity::class.java)
                intent.putExtra("PropertyDto",entity)
                startActivity(intent)
            }else {
                addRoomPopup(entity)
                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(ll_property_top, Gravity.CENTER, 0, 0)
            }
        }
    }

    fun addRoomPopup(entity: PropertyDto){
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_wy_point, ll_property_top)
        val contentView = envirorUpPopu!!.contentView
        val mph = contentView.findViewById<TextView>(R.id.tv_wy_point_mph)
        val cqr = contentView.findViewById<TextView>(R.id.tv_wy_point_cqr)
        val add = contentView.findViewById<TextView>(R.id.tv_wy_point_add)
        val tip = contentView.findViewById<TextView>(R.id.tv_wy_point_tip)
        val close1 = contentView.findViewById<TextView>(R.id.tv_wy_point_close1)
        val close2 = contentView.findViewById<TextView>(R.id.tv_wy_point_close2)
        val fz = contentView.findViewById<TextView>(R.id.tv_wy_point_fz)
        val cz = contentView.findViewById<TextView>(R.id.tv_wy_point_cz)
        val ll_add = contentView.findViewById<LinearLayout>(R.id.ll_wy_point_add)
        val ll_fzcz = contentView.findViewById<LinearLayout>(R.id.ll_wy_point_fzcz)

        if (entity.yl!=null){
            mph.text = entity.yl.mph
            cqr.text = entity.yl.hzmc
        }

        if (entity.housing!=null){
            ll_add.visibility = View.GONE
            tip.visibility = View.GONE
            ll_fzcz.visibility = View.VISIBLE
        }else{
            ll_add.visibility = View.VISIBLE
            tip.visibility = View.VISIBLE
            ll_fzcz.visibility = View.GONE
        }
        close1.setOnClickListener {
            envirorUpPopu!!.dismiss()
        }
        close2.setOnClickListener {
            envirorUpPopu!!.dismiss()
        }
        add.setOnClickListener {
            val intent = Intent(this, PropertyAddFzActivity::class.java)
            intent.putExtra("PropertyDto",entity)
            startActivity(intent)
            envirorUpPopu!!.dismiss()
        }
        fz.setOnClickListener {
            val intent = Intent(this, PropertyAddFzActivity::class.java)
            intent.putExtra("PropertyDto",entity)
            startActivity(intent)
            envirorUpPopu!!.dismiss()
        }
        cz.setOnClickListener {
            val intent = Intent(this, PropertyYlfjActivity::class.java)
            intent.putExtra("PropertyDto",entity)
            startActivity(intent)
            envirorUpPopu!!.dismiss()
        }
    }


    private var cadapterFzFlag = 0
    private var fzAdapter: WyFzListAdapter?=null
    private var fzList = ArrayList<RentEntity>()
    override fun returnWyFzList(entity: List<RentEntity>) {
        if (page == 1){
            fzList.clear()
        }
        fzList.addAll(entity)
        if (fzList!=null&&fzList.size>0){

            rlv_ppt_tab_fz.layoutManager = RecyclerViewNoBugLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_ppt_tab_fz.setHasFixedSize(false)
            if (cadapterFzFlag == 0){
                fzAdapter = WyFzListAdapter(this,fzList)
                rlv_ppt_tab_fz!!.adapter = fzAdapter
                cadapterFzFlag = 1
            }
            fzAdapter!!.setOnClickEnvironLister(object:WyFzListAdapter.OnClickEnvironLister{
                override fun onClick(position: Int) {
                    jumpType = 1
                    mPresenter.getWyPoint(fzList.get(position).center,0)
                }
                override fun onDeleteClick(position: Int) {
                    ToastUtils.showShort("删除")
                }
            })
            rlv_ppt_tab_fz.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                    val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                    if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&fzList.size==page*limit) {
                        if (fzList.size !=0 && fzList.size%limit == 0){
                            page++
                            mPresenter.getWyFzList(stringExtra,cqrName,mphName,limit,page)
                        }else{
                            ToastUtils.showShort("滑动到底部了")
                        }
                    }

                }
            })
            if (page!=1){
                rlv_ppt_tab_fz.scrollToPosition((page-1)*limit)
            }

            var fjs = 0
            var stringList = ArrayList<String>()
            for(i in 0..fzList.size-1){
                stringList.add("")
                fjs = fjs+fzList.get(i).roomToatl
            }
            rlv_ppt_tab_fz_item.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
            rlv_ppt_tab_fz_item.adapter = RightArrowAdapter(this,stringList)
            tv_act_pptfz_ylzs.text = stringList.size.toString()
            tv_act_pptfz_fjs.text = fjs.toString()


        }
    }


    private var cadapterCzFlag = 0
    private var czAdapter:WyCzListAdapter?=null
    private var czList = ArrayList<RentEntity>()
    override fun returnWyCzList(entity: List<RentEntity>) {
        if (page == 1){
            czList.clear()
        }
        czList.addAll(entity)
        if (czList!=null&&czList.size>0){
            rlv_ppt_tab_cz.layoutManager = RecyclerViewNoBugLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_ppt_tab_cz.setHasFixedSize(false)
            if (cadapterCzFlag == 0){
                czAdapter = WyCzListAdapter(this,czList)
                rlv_ppt_tab_cz!!.adapter = czAdapter
                cadapterCzFlag = 1
            }

            czAdapter!!.setOnClickEnvironLister(object :WyCzListAdapter.OnClickEnvironLister{
                override fun onClick(position: Int) {
                    jumpType = 2
                    mPresenter.getWyPoint(fzList.get(position).center,0)
                }
            })

            rlv_ppt_tab_cz.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                    val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                    if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&czList.size==page*limit) {
                        if (czList.size !=0 && czList.size%limit == 0){
                            page++
                            mPresenter.getWyCzList(stringExtra,cqrName,mphName,limit,page)
                        }else{
                            ToastUtils.showShort("滑动到底部了")
                        }
                    }

                }
            })
            if (page!=1){
                rlv_ppt_tab_cz.scrollToPosition((page-1)*limit)
            }
            var fjs = 0
            var stringList = ArrayList<String>()
            for(i in 0..czList.size-1){
                stringList.add("")
                fjs = fjs+czList.get(i).roomUse
            }
            rlv_ppt_tab_cz_item.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
            rlv_ppt_tab_cz_item.adapter = RightArrowAdapter(this,stringList)
            tv_act_pptcz_ylzs.text = stringList.size.toString()
            tv_act_pptcz_fjs.text = fjs.toString()
        }
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
