package com.jymj.zhglxt.ui.fragment


import android.graphics.Color
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.View
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.jymj.basemessagesystem.ui.views.LegendEntity

import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.zjd.bean.TabLayerMapBean
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.baseapp.AppCache
import kotlinx.android.synthetic.main.fragment_demo.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DemoFragment  : BaseFragment<BasePresenter<*, *>, BaseModel>() ,View.OnClickListener ,AMap.OnMapClickListener {
    override fun onMapClick(p0: LatLng?) {

    }

    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/note/")
    var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private var aMap: AMap? = null;
    private val mLatLngs = ArrayList<LatLng>()
    private var mMyLocationStyle: MyLocationStyle? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var mPolygon: Polygon? = null
    private val arrayList = ArrayList<TabLayerMapBean>()
    private var mPolylineOptions: PolylineOptions? = null

    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    private var legendsYL: ArrayList<LegendEntity>? = null
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_demo
    }

    override fun initPresenter() {
    }

    override fun initViews() {

//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_demo.onCreate(activity!!.intent.extras)
        initAMap()
        setAMap()
        legendsYL = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!

        //????????????
        addOverLayer(mLayers)
        bt_demo.setOnClickListener {
            if (dlt_demo.isDrawerOpen(Gravity.RIGHT)){
                dlt_demo?.closeDrawer(Gravity.RIGHT)
            }else{
                dlt_demo?.openDrawer(Gravity.RIGHT)
            }
        }

    }

   
    override fun initDatas() {

        arrayList.add(TabLayerMapBean(1,"?????????",false))
        arrayList.add(TabLayerMapBean(2,"??????",false))
        arrayList.add(TabLayerMapBean(1,"??????",false))

       
        ll_demo_yxt.setOnClickListener {
            if (iv_demo_yxt.isActivated){
                iv_demo_yxt.isActivated = false
                iv_demo_yxt.visibility = View.GONE
                tv_demo_yxt.setTextColor(Color.parseColor("#333333"))
            }else{
                iv_demo_yxt.isActivated = true
                iv_demo_yxt.visibility = View.VISIBLE
                tv_demo_yxt.setTextColor(Color.parseColor("#4CA2FE"))
            }
            xzghTc("?????????")
        }

        ll_demo_tdlyxz.setOnClickListener {
            xzghTc("??????")
        }
        ll_demo_gtgh.setOnClickListener {
            xzghTc("??????")
        }
    }
    private fun xzghTc(tuCe:String){
        aMap!!.clear()
        var stringList = ArrayList<String>()
        for (i in arrayList){
            if (i.name.equals(tuCe)){
                i.isCheck = !i.isCheck
            }
            if (i.isCheck){
                stringList.add(i.name)
            }
        }

        for (i in mLayers){
            i.isCheck = false
            if (stringList.contains(i.name)){
                i.isCheck = true
            }
            /*if (i.name.equals("?????????")){
                if (i.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtSta)
                    aMap!!.addTileOverlay(opt_tdtStaN)
                } else {
                    aMap!!.addTileOverlay(opt_tdtn)
                    aMap!!.addTileOverlay(opt_tdtnN)
                }
            }else if (i.name.equals("??????????????????")){
                if (i.isCheck) {
                    val MAP_tdlyxzed = "http://139.224.52.12/geoserver/dx/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=TRUE&layers=dx:dx_tdly&styles=&viewparams=code:110115015&srs=EPSG:3857&format=image%2Fpng&bbox="
                    val path_tdlyxzed = SDCardUtils.getSDCardPath() + "jymj/zhglxt/map/tdly/"
                    val layer_tdlyxzed = HeritageScopeTileProvider(MAP_tdlyxzed, path_tdlyxzed)
                    val opt_tdlyxzed = TileOverlayOptions().tileProvider(layer_tdlyxzed).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_tdlyxzed).diskCacheSize(10 * 1024)
                    aMap!!.addTileOverlay(opt_tdlyxzed)
                }
            }else if (i.name.equals("????????????")){
                if (i.isCheck) {
                    val MAP_gtkg = "http://139.224.52.12/geoserver/dx/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=TRUE&layers=dx:dx_tg&styles=&viewparams=code:110115015&srs=EPSG:3857&format=image%2Fpng&bbox="
                    val path_gtkg = SDCardUtils.getSDCardPath() + "jymj/zhglxt/map/gtkg/"
                    val layer_gtkg = HeritageScopeTileProvider(MAP_gtkg, path_gtkg)
                    val opt_gtkg = TileOverlayOptions().tileProvider(layer_gtkg).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_gtkg).diskCacheSize(10 * 1024)
                    aMap!!.addTileOverlay(opt_gtkg)
                }
            }*/
        }

        addOverLayer(mLayers)//???????????????
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f));



    }
    override fun onClick(v: View?) {
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
    /**
     * ?????????AMap??????
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_demo.getMap()
        }
        setUpMap()
    }
    /**
     * ????????????amap?????????
     */
    private fun setUpMap() {
        // ???????????????????????????
        mMyLocationStyle = MyLocationStyle()
        //??????????????????????????????????????????
        mMyLocationStyle!!.strokeWidth(3f)
        aMap!!.getUiSettings().setMyLocationButtonEnabled(true) // ????????????????????????????????????
        //        aMap.setMyLocationEnabled(true);// ?????????true??????????????????????????????????????????false??????????????????????????????????????????????????????false
        // ???????????????????????????????????? ??????????????????????????????????????????????????????????????????
        aMap!!.setMyLocationStyle(mMyLocationStyle)
        aMap!!.setMyLocationEnabled(true) // ?????????true?????????????????????????????????false??????????????????????????????????????????????????????false???
        mMyLocationStyle!!.showMyLocation(true)
    }
    /**
     * ????????????
     */
    private fun setAMap() {
        //????????????????????????????????????
        aMap!!.getUiSettings().setMyLocationButtonEnabled(false)
        aMap!!.getUiSettings().setScaleControlsEnabled(false) // ???????????????
        aMap!!.getUiSettings().setZoomControlsEnabled(false)
        aMap!!.setMapLanguage(AMap.CHINESE)
        aMap!!.setOnMapClickListener(this)
        mPolygonOptions = PolygonOptions()
    }

    override fun onPause() {
        super.onPause()
        map_demo.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        map_demo.onDestroy()
    }
    override fun onResume() {
        super.onResume()
        map_demo.onResume()
    }


}
