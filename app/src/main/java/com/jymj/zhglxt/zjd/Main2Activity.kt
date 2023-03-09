package com.jymj.zhglxt.zjd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.MyLocationStyle
import com.amap.api.maps2d.model.PolygonOptions
import com.amap.api.maps2d.model.TileOverlayOptions
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.frag_zjdgl.*

class Main2Activity : AppCompatActivity() {

    private var aMap: AMap? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var mMyLocationStyle: MyLocationStyle? = null
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

    val sdCardPath = AppMenus.getInstance().cardPath

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
                                 //http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a
//        MapsInitializer.replaceURL("https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png", "tdt-vecosm43")//添加天地图  VEC_URL, "tdt1-vec"

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL//        MapsInitializer.replaceURL("https://c-ssl.duitang.com/uploads/item/202004/09/20200409032441_mhYLN.jpeg", "tdt-veckong")//VEC_URL
        map_frag_99999.onCreate(intent.extras)
        initAMap()
        setAMap()

        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
    }
    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_99999.getMap()
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
//        mPolygonOptions = PolygonOptions()
//        aMap!!.addTileOverlay(opt_tdtnN)

//        aMap!!.// = 18f

    }
}
