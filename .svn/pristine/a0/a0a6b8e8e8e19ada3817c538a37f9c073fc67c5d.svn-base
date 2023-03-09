package com.jymj.zhglxt.zjd.activity.zjdgl

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.zjd.adapter.FrameFzmxAdapter
import com.jymj.zhglxt.zjd.adapter.FrameTgmxAdapter
import com.jymj.zhglxt.zjd.adapter.FrameTxmxAdapter
import com.jymj.zhglxt.zjd.adapter.FrameYlmxAdapter
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameFZMXBean
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameTGMXBean
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameTXMXBean
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameYLMXBean
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_frame_mx.*
import org.json.JSONObject
import java.util.*

class FrameMXActivity : AppCompatActivity() {

    private var aMap: AMap? = null
    private var mLayers = ArrayList<LegendEntity>()
    private var data: ApplyEntity? = null
    //    private var list = ArrayList<ZjdFileEntity>()
    private var legendsYL: ArrayList<LayerEntity>? = null
    private var legendsYL1: ArrayList<LayerEntity>? = null
    private var legendsFJ= ArrayList<LegendEntity>()
    val tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath+"/BMS/tdt/note/")
    val opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10*1024).diskCacheDir(AppMenus.getInstance().cardPath+"BMS/map/tdt/note/").diskCacheSize(10*1024)
    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)

    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private var mPolygon: Polygon? = null
    private var mPolygonOptions: PolygonOptions? = null
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)


    var page = 1
    var limit = 20
    var sbs : String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_mx)
        /*MainActivity.tvTitle?.setText("基本信息")
        MainActivity.bt_map!!.visibility = View.GONE*/
//        MapsInitializer.setApiKey("c114a893046db43fa28d1ea9bf4166f8");
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        frame_map?.onCreate(intent.extras)
        initAMap()
        setAMap("1036")


        val mxFlag = intent.getStringExtra("framemxFlag") as String
        sbs = intent.getStringExtra("framemxSbs") as String
        if (mxFlag.equals("fwmx")){
            limit = 20
            getYLMXData(sbs, 1, limit)
        }else if (mxFlag.equals("txmx")){
            limit = 20
            getTXMXData(sbs, 1, limit)
        }else if (mxFlag.equals("tgmx")){
            limit = 20
            getTGMXData(sbs, 0, limit)
        }else if (mxFlag.equals("fzmx")){
            limit = 20
            getFZMXData(sbs, 1, limit)
        }
    }

    //院落明细
    fun getYLMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("pageSize", pageSize)
        jsonObject.put("currPage", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETYlQueryFrame).upJson(jsonObject).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                frame_yl_ll.visibility = View.VISIBLE
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameYLMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameYLMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.ylEntityList.size > 0) {
                                val data = json.data.ylEntityList
                                xrl_frame_ylmx!!.layoutManager = LinearLayoutManager(this@FrameMXActivity, LinearLayoutManager.VERTICAL, false)

                                val ylmxAdapter = FrameYlmxAdapter(this@FrameMXActivity, data)
                                xrl_frame_ylmx!!.adapter = ylmxAdapter

                                ylmxAdapter.onClickLister = object : FrameYlmxAdapter.OnClickLister{
                                    override fun onClick(analysisEnty: FrameYLMXBean.DataBean.YlEntityListBean?) {
                                        aMap!!.clear()
                                        kuangGeoment(analysisEnty!!.geometry)
                                        addOverLayer(legendsYL!!)
                                        addOverLayer(legendsYL1!!)
                                    }


                                }


                                if (limit % 20 != 0 || data.size < limit) {
                                    xrl_frame_ylmx!!.setLoadingMoreEnabled(false)
                                    ToastUtils.showShort("滑动到底部了")
                                }
                                if (limit != 20) {
                                    xrl_frame_ylmx!!.scrollToPosition(data.size)
                                }

                                xrl_frame_ylmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
                                    override fun onLoadMore() {
                                        limit += 20
                                        getYLMXData(sbs, 1, limit)
                                    }

                                    override fun onRefresh() {
                                        limit = 20
                                        getYLMXData(sbs, 1, limit)
                                        xrl_frame_ylmx!!.setLoadingMoreEnabled(true)
                                    }
                                })
                                xrl_frame_ylmx!!.loadMoreComplete()
                                xrl_frame_ylmx!!.refreshComplete()

                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }
    //土现明细
    fun getTXMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("point", points)
        jsonObject.put("page", pageSize)
        jsonObject.put("limit", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETTdlyQueryFrame).upJson(sss).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                frame_tx_ll.visibility = View.VISIBLE
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameTXMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameTXMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.list.size > 0) {
                                val data = json.data.list
                                xrl_frame_txmx!!.layoutManager = LinearLayoutManager(this@FrameMXActivity, LinearLayoutManager.VERTICAL, false)

                                val ylmxAdapter = FrameTxmxAdapter(this@FrameMXActivity, data)
                                xrl_frame_txmx!!.adapter = ylmxAdapter
                                ylmxAdapter.onClickLister = object : FrameTxmxAdapter.OnClickLister{
                                    override fun onClick(analysisEnty: FrameTXMXBean.DataBean.ListBean?) {
                                        //kuangGeoment(analysisEnty!!.geometry)
                                    }

                                }
                                if (limit % 20 != 0 || data.size < limit) {
                                    xrl_frame_txmx!!.setLoadingMoreEnabled(false)
                                    ToastUtils.showShort("滑动到底部了")
                                }
                                if (limit != 20) {
                                    xrl_frame_txmx!!.scrollToPosition(data.size)
                                }

                                xrl_frame_txmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
                                    override fun onLoadMore() {
                                        limit += 20
                                        getTXMXData(sbs, 1, limit)
                                    }

                                    override fun onRefresh() {
                                        limit = 20
                                        getTXMXData(sbs, 1, limit)
                                        xrl_frame_txmx!!.setLoadingMoreEnabled(true)
                                    }
                                })
                                xrl_frame_txmx!!.loadMoreComplete()
                                xrl_frame_txmx!!.refreshComplete()
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }
                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }
    //土规明细
    fun getTGMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("pageSize", pageSize)
        jsonObject.put("currPage", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETTgQueryFrame).upJson(sss).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                frame_tg_ll.visibility = View.VISIBLE
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameTGMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameTGMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.tgEntityList.size > 0) {
                                val data = json.data.tgEntityList
                                xrl_frame_tgmx!!.layoutManager = LinearLayoutManager(this@FrameMXActivity, LinearLayoutManager.VERTICAL, false)

                                val ylmxAdapter = FrameTgmxAdapter(this@FrameMXActivity, data)
                                xrl_frame_tgmx!!.adapter = ylmxAdapter
                                ylmxAdapter.onClickLister = object : FrameTgmxAdapter.OnClickLister{
                                    override fun onClick(analysisEnty: FrameTGMXBean.DataBean.TgEntityListBean?) {
                                        aMap!!.clear()
                                        kuangGeoment(analysisEnty!!.geometry)
                                        addOverLayer(legendsYL!!)
                                        addOverLayer(legendsYL1!!)
                                    }
                                }
                                if (limit % 20 != 0 || data.size < limit) {
                                    xrl_frame_tgmx!!.setLoadingMoreEnabled(false)
                                    ToastUtils.showShort("滑动到底部了")
                                }
                                if (limit != 20) {
                                    xrl_frame_tgmx!!.scrollToPosition(data.size)
                                }

                                xrl_frame_tgmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
                                    override fun onLoadMore() {
                                        limit += 20
                                        getTGMXData(sbs, 0, limit)
                                    }

                                    override fun onRefresh() {
                                        limit = 20
                                        getTGMXData(sbs, 0, limit)
                                        xrl_frame_tgmx!!.setLoadingMoreEnabled(true)
                                    }
                                })
                                xrl_frame_tgmx!!.loadMoreComplete()
                                xrl_frame_tgmx!!.refreshComplete()
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }
    //非宅明细
    fun getFZMXData(points: String, currPage: Int, pageSize: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("points", points)
        jsonObject.put("pageSize", pageSize)
        jsonObject.put("currPage", currPage)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.GETTgqueryYlFei).upJson(sss).execute(object : BaseNet<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                frame_fz_ll.visibility = View.VISIBLE
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

//                                T fromJson = new Gson().fromJson(s, T);
                    val json: FrameFZMXBean = Gson().fromJson(decrypt, object : TypeToken<FrameFZMXBean?>() {}.type)
                    if (json!!.data != null) {
                        if (json.code == 0) {
                            if (json.data.ylEntityList.size > 0) {
                                val data = json.data.ylEntityList
                                xrl_frame_fzmx!!.layoutManager = LinearLayoutManager(this@FrameMXActivity, LinearLayoutManager.VERTICAL, false)

                                val ylmxAdapter = FrameFzmxAdapter(this@FrameMXActivity, data)
                                xrl_frame_fzmx!!.adapter = ylmxAdapter

                                ylmxAdapter.onClickLister = object : FrameFzmxAdapter.OnClickLister{
                                    override fun onClick(analysisEnty: FrameFZMXBean.DataBean.YlEntityListBean?) {
                                        aMap!!.clear()
                                        addOverLayer(legendsYL!!)
                                        addOverLayer(legendsYL1!!)
                                        kuangGeoment(analysisEnty!!.geometry)
                                    }
                                }
                                if (limit % 20 != 0 || data.size < limit) {
                                    xrl_frame_fzmx!!.setLoadingMoreEnabled(false)
                                    ToastUtils.showShort("滑动到底部了")
                                }
                                if (limit != 20) {
                                    xrl_frame_fzmx!!.scrollToPosition(data.size)
                                }

                                xrl_frame_fzmx!!.setLoadingListener(object : XRecyclerView.LoadingListener {
                                    override fun onLoadMore() {
                                        limit += 20
                                        getFZMXData(sbs, 1, limit)
                                    }

                                    override fun onRefresh() {
                                        limit = 20
                                        getFZMXData(sbs, 1, limit)
                                        xrl_frame_fzmx!!.setLoadingMoreEnabled(true)
                                    }
                                })
                                xrl_frame_fzmx!!.loadMoreComplete()
                                xrl_frame_fzmx!!.refreshComplete()
                            }
                        } else {
                            ToastUtils.showShort("数据异常")
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                ToastUtils.showShort("数据异常")
            }
        })

    }

    /**
     * 地图方法
     */
    private fun setAMap(xzqCode: String) {

        //设置默认定位按钮是否显示
        aMap?.uiSettings?.isMyLocationButtonEnabled = false
        aMap?.uiSettings?.isScaleControlsEnabled = false
        aMap?.uiSettings?.isZoomControlsEnabled = false
//        aMap?.setMapLanguage(AMap.CHINESE)
        aMap?.mapType = AMap.MAP_TYPE_NORMAL
        legendsYL = ArrayList<LayerEntity>()
        legendsYL1 = ArrayList<LayerEntity>()
        LayerInit.initMain(xzqCode, legendsYL, legendsYL1, mLayers)
//        LayerInit.initFJ(legendsFJ)
        for (l in legendsYL!!) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {
                    l.isCheck = true
                }
                "申请" -> {
                    l.isCheck = true
                }
                "宅基地" -> {
                    l.isCheck = true
                }

            }
        }
        for (l in legendsYL1!!) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {
                    l.isCheck = true
                }
                "申请" -> {
                    l.isCheck = true
                }
                "宅基地" -> {
                    l.isCheck = true
                }

            }
        }
        addOverLayer(legendsYL!!)
        addOverLayer(legendsYL1!!)
        mPolygonOptions = PolygonOptions()
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
                "宅翻建" ->  {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                    }
                }
                else -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
            }
        }
        aMap?.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
    }
    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = frame_map?.map
        }
        setUpMap()
    }

    override fun onResume() {
        super.onResume()
        frame_map?.onResume()
    }

    override fun onPause() {
        super.onPause()
        frame_map?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        frame_map?.onDestroy()
//        ActivityCache.getInstance().removeActivity(this)
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() {
        aMap?.isMyLocationEnabled = false// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }
    private fun kuangGeoment(dataGeometry: String) {
        if (!dataGeometry.equals("")) {

//            clear
            var substring = dataGeometry!!.substring(0, dataGeometry!!.length - 5)
            val replace = substring.replace("MULTIPOLYGON(((", "")
            val replace1 = replace.replace("POLYGON((", "")
            /*val replace = substring.replace("(", "")
                val replace1 = replace.replace(")", "")*/
            var split = replace1.split(")),((")
            if (split.size<2){
                split = replace1.split("),(")
            }
            var s = 0
            for (i in 0..split.size - 1) {
                val latList = getLatList(split[i])
                if (s == 0){
                    aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                }

                if (mPolygon != null)
//                            mPolygon!!.remove()
                    mPolygonOptions!!.getPoints().clear()
                mPolygonOptions!!.addAll(latList)
                if (i == 0) {
                    mPolygonOptions!!.strokeWidth(10f) // 多边形的边框
                            .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                            .fillColor(Color.argb(50, 64, 64, 255)) // 多边形的填充色
                } else {
                    mPolygonOptions!!.strokeWidth(10f) // 多边形的边框
                            .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                            .fillColor(Color.argb(0, 0, 0, 0)) // 多边形的填充色
                }
                s = 1
                mPolygon = aMap!!.addPolygon(mPolygonOptions)
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
                    val latLng = LatLng(replace4.toDouble(),replace3.toDouble())
                    list.add(latLng)
                }

            }
        }
        return list
    }

}
