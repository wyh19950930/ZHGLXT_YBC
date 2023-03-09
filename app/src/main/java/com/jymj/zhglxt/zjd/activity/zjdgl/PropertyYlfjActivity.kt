package com.jymj.zhglxt.zjd.activity.zjdgl

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.rjhj.bean.CjEntity
import com.jymj.zhglxt.rjhj.bean.YLEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.util.RltUtil
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.zjd.bean.wy.*
import com.jymj.zhglxt.zjd.contract.PropertyYlfjContract
import com.jymj.zhglxt.zjd.presenter.PropertyYlfjPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration
import kotlinx.android.synthetic.main.activity_property_ylfj.*

/**
 * 房屋出租item-院落房间（该页面）
 */

class PropertyYlfjActivity : BaseActivity<PropertyYlfjPresenter, BaseModel>(), PropertyYlfjContract.View {

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
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)

    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)


    private var addPolyline: Polyline? = null//高亮显示
    private var mPolylineOptions: PolylineOptions? = null
    private var mLatLng: LatLng? = null

    private var ylIdType = 0
    private var inittenantPopup: CommenPop? = null
    private var roomIdFlag: Long = 0
    private var housIdFlag = 0L
    override fun getLayoutId(): Int {
        return R.layout.activity_property_ylfj
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//添加天地图  VEC_URL, "tdt1-vec"
//        MapsInitializer.replaceURL("https://c-ssl.duitang.com/uploads/item/202004/09/20200409032441_mhYLN.jpeg", "tdt-vecosm1")//VEC_URL

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_act_ppt_yjfj.onCreate(this.intent.extras)
        initAMap()
        setAMap()

        supl_act_ppt_ylfj.panelHeight = DisplayUtil.dip2px(50f)
        supl_act_ppt_ylfj.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

        val propertyDto = intent.getSerializableExtra("PropertyDto") as PropertyDto
        rlv_actppt_ylfj_fjbh.layoutManager = StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL)
        rlv_actppt_ylfj_fwzp.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        if (propertyDto.yl != null) {
            val ylEntity = propertyDto.yl
            moveCenter(ylEntity)
            tabTc("底图", 2)
            tabTc("流动人口", 1)
            ylIdType = propertyDto.yl.objectid
        }
        if (propertyDto.qrCode != null) {
            Glide.with(this).load(propertyDto.qrCode.path).into(iv_act_ppt_add_fz_ewmfh)
        }
        if (propertyDto.housing != null) {
            val housing = propertyDto.housing
            tv_actppt_ylfj_mph.text = housing.mph
            tv_actppt_ylfj_cqr.text = housing.name
            tv_actppt_ylfj_jcsj.text = housing.jcsj
            tv_actppt_ylfj_jzmj.text = housing.jzmj.toString()

            if (housing.housingFiles != null && housing.housingFiles.size > 0) {
                for (i in 0..housing.housingFiles.size - 1) {
                    if (housing.housingFiles.get(i).type == 3) {
                        Glide.with(this).load(housing.housingFiles.get(i).url).into(iv_actppt_ylfj_sj1)
                    }
                    if (housing.housingFiles.get(i).type == 4) {
                        Glide.with(this).load(housing.housingFiles.get(i).url).into(iv_actppt_ylfj_sj2)
                    }
                }
            }
            val roomList = ArrayList<HousingRoom>()
            var refreshType = -1
            if (housing.housingRooms != null && housing.housingRooms.size > 0) {
                for (i in 0..housing.housingRooms.size - 1) {
                    if (housing.housingRooms.get(i).repair == 2) {
                        roomList.add(housing.housingRooms.get(i))
                    }
                }

                rlv_actppt_ylfj_fjbh.adapter = object : BaseQuickAdapter<HousingRoom, BaseViewHolder>(R.layout.item_wy_fz_fjbh_list, roomList) {
                    override fun convert(helper: BaseViewHolder?, item: HousingRoom?) {
                        helper!!.setText(R.id.tv_item_wy_fz_fjbh_list, item!!.bh)
                        if (item.isrent == 1) {
                            helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setBackgroundResource(R.drawable.bt_actiive_wy_fz_room_unchecked)
                            helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setTextColor(Color.parseColor("#999999"))
                        } else {
                            helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setBackgroundResource(R.drawable.bt_actiive_wy_fz_room_full)
                            helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setTextColor(Color.parseColor("#12C483"))
                        }

                        helper.itemView.setOnClickListener {
                            tv_actppt_ylfj_lszk.visibility = View.VISIBLE
                            housIdFlag = item.id

                            if (item.isrent == 2) {
                                bt_actppt_ylfj_newadd.visibility = View.GONE
                                initYczRoom(item.id)
                            } else {
                                roomIdFlag = item.id
                                refreshType = helper.adapterPosition
                                notifyDataSetChanged()
                                bt_actppt_ylfj_newadd.visibility = View.VISIBLE
                            }
                            var imageList = ArrayList<HousingRoomFile>()
                            for (i in 0..item.housingRoomFiles.size - 1) {
                                if (item.housingRoomFiles.get(i).type == 2) {
                                    imageList.add(item.housingRoomFiles.get(i))
                                }
                            }

                            rlv_actppt_ylfj_fwzp.adapter = object : BaseQuickAdapter<HousingRoomFile, BaseViewHolder>(R.layout.item_wy_cz_fjzp_list, imageList) {
                                override fun convert(helper: BaseViewHolder?, item: HousingRoomFile?) {
                                    Glide.with(this@PropertyYlfjActivity).load(item!!.url).into(helper!!.getView(R.id.iv_item_wy_cz_fjzp))
                                }
                            }

                        }
                        if (refreshType == helper.adapterPosition) {
                            helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setBackgroundResource(R.drawable.bt_actiive_wy_fz_room_selected)
                            helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setTextColor(Color.parseColor("#37A7FA"))
                        } else {
                            if (item.isrent == 1) {
                                helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setBackgroundResource(R.drawable.bt_actiive_wy_fz_room_unchecked)
                                helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setTextColor(Color.parseColor("#999999"))
                            } else {
                                helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setBackgroundResource(R.drawable.bt_actiive_wy_fz_room_full)
                                helper.getView<TextView>(R.id.tv_item_wy_fz_fjbh_list).setTextColor(Color.parseColor("#12C483"))
                            }
                        }
                    }
                }

            }

        }

        bt_actppt_ylfj_newadd.setOnClickListener {
            if (ylIdType != 0) {
                mPresenter.getFlowGetInfo(ylIdType.toLong())
            }
        }

        tv_actppt_ylfj_lszk.setOnClickListener {
            if (housIdFlag != 0L) {
                val intent = Intent(this,PropertyLszkActivity::class.java)
                intent.putExtra("housIdFlag",housIdFlag)
                startActivity(intent)
            }
        }

    }

    override fun initDatas() {
    }

    private fun initTenantPop(entity: List<FlowInfoEntity>) {
        inittenantPopup = CommenPop.getNormalPopu(this, R.layout.pop_wy_cz_rzry_list, ll_act_ppt_yjfj_top)
        val contentView = inittenantPopup!!.contentView
        val rzryRlv = contentView.findViewById<RecyclerView>(R.id.pop_wy_cz_rzry_rlv)
        val close = contentView.findViewById<TextView>(R.id.pop_wy_cz_rzry_close)
        CommenPop.backgroundAlpha(0.5f, this)
        inittenantPopup!!.showAtLocation(ll_act_ppt_yjfj_top, Gravity.CENTER, 0, 0)

        rzryRlv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = DefaultItemDecoration(Color.parseColor("#eeeeee"))
        rzryRlv.addItemDecoration(itemDecoration)
        rzryRlv.adapter = object : BaseQuickAdapter<FlowInfoEntity, BaseViewHolder>(R.layout.item_wy_cz_rzry_list, entity) {
            override fun convert(helper: BaseViewHolder?, item: FlowInfoEntity?) {
                helper!!.setText(R.id.tv_item_wy_cz_rzry_name, item!!.name)
                        .setText(R.id.tv_item_wy_cz_rzry_tel, item.phone)

                helper.itemView.setOnClickListener {
                    val intent = Intent(this@PropertyYlfjActivity, PropertyRzxxActivity::class.java)
                    intent.putExtra("rzxxType", 1)
                    intent.putExtra("rzxxEntity", item)
                    intent.putExtra("rzxxRoomIdFlag", roomIdFlag)
                    startActivity(intent)
                    inittenantPopup!!.dismiss()
                }
            }
        }

        close.setOnClickListener {
            inittenantPopup!!.dismiss()
        }
    }

    //已出租弹框
    private fun initYczRoom(roomId: Long) {
        inittenantPopup = CommenPop.getNormalPopu(this, R.layout.pop_delete, ll_act_ppt_yjfj_top)
        val contentView = inittenantPopup!!.contentView
        val tips = contentView.findViewById<TextView>(R.id.pop_delete_tips)
        val confirm = contentView.findViewById<TextView>(R.id.pop_delete_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_delete_close)
        tips.text = "该房间已出租，是否查看？"
        CommenPop.backgroundAlpha(0.5f, this)
        inittenantPopup!!.showAtLocation(ll_act_ppt_yjfj_top, Gravity.CENTER, 0, 0)

        confirm.setOnClickListener {
            mPresenter.getLeaseInfo(roomId)
            inittenantPopup!!.dismiss()
        }
        close.setOnClickListener {
            inittenantPopup!!.dismiss()
        }
    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_act_ppt_yjfj.getMap()
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

    private fun tabTc(tuCe: String, type: Int) {//type 1 清 2 不清  , type:Int
        val stringList = ArrayList<String>()//TabLayerMapBean

        for (i in mLayers) {//layerList
            if (i.num == 1 && tuCe.equals("")) {
                i.isCheck = false
            }

            if (type == 1) {
                if (i.num == 1) {
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    } else {
                        i.isCheck = false
                    }
                } else if (i.num == 2) {
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }
                }
            } else if (type == 2) {
                if (i.name.equals(tuCe)) {
                    i.isCheck = !i.isCheck
                }
            }
            if (i.isCheck) {
                stringList.add(i.name)
            }
        }
        for (i in mLayers1) {//layerList
            if (i.num == 1 && tuCe.equals("")) {
                i.isCheck = false
            }

            if (type == 1) {
                if (i.num == 1) {
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    } else {
                        i.isCheck = false
                    }
                } else if (i.num == 2) {
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }
                }
            } else if (type == 2) {
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
        if (aMap != null) {
            aMap!!.clear()
            addOverLayer(mLayers)//问题在这里
            addOverLayer(mLayers1)//问题在这里
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
    private fun moveCenter(ylEntity: YLEntity) {
        initTuli()//图层
        Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(1000); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法
                    val point = RltUtil.getCenter(ylEntity.center1)
                    aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 17f, 0f, 0f)))
                    if (!ylEntity.geometry.equals("")) {
                        kuangGeomentLine(ylEntity.geometry)//删除之前的   高亮显示翻建点查的院落
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()
    }

    private fun kuangGeomentLine(dataGeometry: String) {
        if (addPolyline != null) {
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
            if (split.size < 2) {
                split = replace1.split("),(")
            }
            var s = 0
            for (i in 0..split.size - 1) {
                if (split[i].contains("),(")) {
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
                } else {
                    val latList = getLatList(split[i])
                    if (i == 0) {
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

    override fun returnWyPoint(entity: PropertyDto) {
    }

    override fun returnFlowGetInfo(entity: List<FlowInfoEntity>) {
        if (entity != null && entity.size > 0) {
            initTenantPop(entity)
        }
    }

    //
    override fun returnHistoryTenant(entity: List<HousingLeaseEntity>) {

    }

    override fun onResume() {
        super.onResume()
        map_act_ppt_yjfj.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_act_ppt_yjfj.onPause()
    }

    override fun returnLeaseInfo(entity: LeaseDto) {
        if (entity != null) {
            val intent = Intent(this, PropertyRzxxActivity::class.java)
            intent.putExtra("rzxxType", 2)
            intent.putExtra("yczEntity", entity)
            startActivity(intent)
        }

    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        if (msg != null) {
            ToastUtils.showShort(msg)
        } else {
            ToastUtils.showShort("请求失败")
        }

        LoadingDialog.cancelDialogForLoading()
    }


}
