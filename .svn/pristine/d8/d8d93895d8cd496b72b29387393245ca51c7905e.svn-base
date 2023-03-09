package com.jymj.zhglxt.zjd.activity.yzt

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.app.AppApplication
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.bean.SuccessBean
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.rjhj.bean.PjProjEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.widget.TimeLineView
import com.jymj.zhglxt.zjd.bean.yzt.PjInfoBean
import com.jymj.zhglxt.zjd.bean.yzt.PjProjEntitys
import com.jymj.zhglxt.zjd.bean.yzt.PjProjFileEnty
import com.jymj.zhglxt.zjd.bean.yzt.ProjspeedEnum
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ImageUtils
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_ct_detail.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import org.json.JSONObject
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CtDetailActivity : AppCompatActivity(), AMap.OnMapClickListener, LocationSource {

    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)

    val tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/note/")
    val opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)

    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

    private var aMap: AMap? = null
    private var legendsYL: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()

    private var mPolygonOptions: PolygonOptions? = null
    private var mPolygon: Polygon? = null
    val list: MutableList<PjProjFileEnty> = ArrayList<PjProjFileEnty>()
    var pjProjFileEnty: PjInfoBean = PjInfoBean()
    var baseQuickAdapter: BaseQuickAdapter<PjProjFileEnty, BaseViewHolder>? = null
    var idd: Int = 0;
    var stepUpPopu: CommenPop? = null
    var photoAdapter: PhotoAdapter? = null
    private var selectedPhotos = ArrayList<String>()
    val steps = ArrayList<String>()
    var dialog: SweetAlertDialog? = null
    var mAddPop: CommenPop? = null
//    var mPhotoAdapter: PhotoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ct_detail)
        //天地图
        /*MainActivity.tvTitle?.setText("基本信息")
        MainActivity.bt_map!!.visibility = View.GONE*/
//        MapsInitializer.setApiKey("c114a893046db43fa28d1ea9bf4166f8");
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        map_ct_detail!!.onCreate(intent.extras)
        //初始化AMap对象
        initAMap()
        //初始化地图
        setAMap()
        initStep()
        iv_ct_detail_back.setOnClickListener {
            finish()
        }
//        initTlv()
        idd = intent.getIntExtra("id", 0)
        //URL_PJPROJ_PJINFO
        getCtDetail(idd)
        tv_ct_detail_update.setOnClickListener {
            //            initPopuStepUp()
            addPop(pjProjFileEnty.pjProjEntity)
            CommenPop.backgroundAlpha(0.5f, this)
            mAddPop!!.showAtLocation(ll_ct_detail, Gravity.CENTER, 0, 0)
        }
        legendsYL = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!
        //添加图层
        for (l in mLayers) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {
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
                "拆腾片区" -> {
                    l.isCheck = true
                }
                "拆腾企业" -> {
                    l.isCheck = true
                }
            }
        }
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
    }

    //高亮显示
    private fun kuangGeoment(dataGeometry: String) {
        if (!dataGeometry.equals("")) {

//            clear
            var substring = dataGeometry!!.substring(0, dataGeometry!!.length - 5)
            val replace = substring.replace("MULTIPOLYGON(((", "")
            val replace1 = replace.replace("POLYGON((", "")
            /*val replace = substring.replace("(", "")
                val replace1 = replace.replace(")", "")*/
            var split = replace1.split("),(")
            /*if (split.size < 2) {
                split = replace1.split("),(")
            }*/
            var s = 0
            for (i in 0..split.size - 1) {
                mPolygonOptions = PolygonOptions()
                val latList = getLatList(split[i])
                if (s == 0) {
//                    if (gaoLiang==0){
                    aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                    /* }else{
                         aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 14f, 0f, 0f)))
                     }*/

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
                    val latLng = LatLng(replace4.toDouble(), replace3.toDouble())
                    list.add(latLng)
                }

            }
        }
        return list
    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_ct_detail!!.map
        }
        setUpMap()
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() {
        val myLocationStyle = MyLocationStyle()
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)////定位一次，且将视角移动到地图中心点。
        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap!!.setMyLocationStyle(myLocationStyle)//设置定位蓝点的Style
        aMap!!.isMyLocationEnabled = true// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.showMyLocation(true)
    }

    /**
     * 初始化地图
     */
    private fun setAMap() {
        //设置默认定位按钮是否显示
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false// 设置比例尺
        aMap!!.uiSettings.isZoomControlsEnabled = false//设置缩放取消
        aMap!!.setMapLanguage(AMap.CHINESE)
        aMap!!.setOnMapClickListener(this)
        aMap!!.setLocationSource(this)
        /* aMap!!.setOnMapLoadedListener {
             hideView(recy_map_activity!!)
             if (!isLogin) {
                 showLogin()
             } else {
 //                val username = SPUtils.getSharedStringData(this@MainActivity, "username")
 //                val pwd = SPUtils.getSharedStringData(this@MainActivity, "pwd")
 //                val deviceid = SPUtils.getSharedStringData(this@MainActivity, "deviceId")
                 //"867778033217244"
 //                mPresenter.login(username, pwd, deviceid)
             }
         }*/
        mPolygonOptions = PolygonOptions()
        //添加瓦片
//        aMap!!.addTileOverlay(opt_tdtnN)
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

                else -> if (l.isCheck) {
                    aMap!!.addTileOverlay(l.opt)
                }
            }
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
    }

    override fun onResume() {
        super.onResume()
        map_ct_detail!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_ct_detail!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_ct_detail?.onDestroy()//地图销毁
    }

    //
    private fun addPop(dataBean: PjProjEntity) {
        selectedPhotos.clear()

        mAddPop = CommenPop.getNormalPopu(this, R.layout.pop_ctglpq_add, ll_ct_detail)
        val contentView: View = mAddPop!!.getContentView()
        val xmmc_et = contentView.findViewById<EditText>(R.id.et_ctglpq_add_xmmc)
        val cddw_et = contentView.findViewById<EditText>(R.id.et_ctglpq_add_cddw)
        val jsgm_et = contentView.findViewById<EditText>(R.id.et_ctglpq_add_jsgm)
        val ttzt_spr = contentView.findViewById<Spinner>(R.id.et_ctglpq_add_ttzt)//
        val zfzt_spr = contentView.findViewById<Spinner>(R.id.et_ctglpq_add_zfzt)
        val zfje_et = contentView.findViewById<EditText>(R.id.et_ctglpq_add_zfje)
        val et_ctglpq_add_dabh = contentView.findViewById<EditText>(R.id.et_ctglpq_add_dabh)//档案编号
        val et_ctglpq_add_bdzb = contentView.findViewById<EditText>(R.id.et_ctglpq_add_bdzb)//标段组别
        val et_ctglpq_add_qs = contentView.findViewById<EditText>(R.id.et_ctglpq_add_qs)//权属
        val et_ctglpq_add_cqrhczr = contentView.findViewById<EditText>(R.id.et_ctglpq_add_cqrhczr)//产权人或承租人
        val et_ctglpq_add_qymc = contentView.findViewById<EditText>(R.id.et_ctglpq_add_qymc)//企业名称
        val tv_ctglpq_add_htqx = contentView.findViewById<TextView>(R.id.tv_ctglpq_add_htqx)//合同期限开始
        val tv_ctglpq_add_htqx1 = contentView.findViewById<TextView>(R.id.tv_ctglpq_add_htqx1)//合同期限结束
        val et_ctglpq_add_htzdmj = contentView.findViewById<EditText>(R.id.et_ctglpq_add_htzdmj)//合同租地面积
        val et_ctglpq_add_scjzmj = contentView.findViewById<EditText>(R.id.et_ctglpq_add_scjzmj)//合同建筑面积
        val et_ctglpq_add_ywcq = contentView.findViewById<Spinner>(R.id.et_ctglpq_add_ywcq)//有无产权
        val et_ctglpq_add_lxdh = contentView.findViewById<EditText>(R.id.et_ctglpq_add_lxdh)//联系电话

        val add_btn = contentView.findViewById<Button>(R.id.but_ctglpq_add_sure)
        val rlv_add: RecyclerView = contentView.findViewById(R.id.rlv_ctglpq_add)
        var uploadDate = CalendarUtil.DEFAULT_DATE_FORMAT.format(Date())
        tv_ctglpq_add_htqx!!.setText(uploadDate)
        tv_ctglpq_add_htqx1!!.setText(uploadDate)
        tv_ctglpq_add_htqx.setOnClickListener {
            setTime(tv_ctglpq_add_htqx!!)
        }
        tv_ctglpq_add_htqx1.setOnClickListener {
            setTime(tv_ctglpq_add_htqx1!!)
        }
        /* tv_ct_detail_xmmc.setText(json.data.pjProjEntity.name)
         tv_ct_detail_cddw.setText(json.data.pjProjEntity.impUnit)
         tv_ct_detail_xmgm.setText(json.data.pjProjEntity.projScale.toString())
         tv_ct_detail_ttzt.setText(steps.get(json.data.pjProjEntity.projSpeed))
         tv_ct_detail_zfqk.setText("")
         tv_ct_detail_zfje.setText("")*/


        photoAdapter = PhotoAdapter(this, selectedPhotos)
        rlv_add.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlv_add.adapter = photoAdapter
        rlv_add.addOnItemTouchListener(RecyclerItemClickListener(this, RecyclerItemClickListener.OnItemClickListener { view, position ->
            if (photoAdapter!!.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                PhotoPicker.builder()
                        .setPhotoCount(PhotoAdapter.MAX)
                        .setShowCamera(true)
                        .setPreviewEnabled(false)
                        .setSelected(selectedPhotos)
                        .start(this)//, Activity.RESULT_FIRST_USER
            } else {
                PhotoPreview.builder()
                        .setPhotos(selectedPhotos)
                        .setCurrentItem(position)
                        .start(this)//, Activity.RESULT_FIRST_USER
            }
        }))
        val ttztAdapter = com.jymj.zhglxt.widget.SpinnerAdapter(this)
        val zfztAdapter = com.jymj.zhglxt.widget.SpinnerAdapter(this)
        val ywcqAdapter = com.jymj.zhglxt.widget.SpinnerAdapter(this)
        val ttztList = java.util.ArrayList<String>()
        val zfztList = java.util.ArrayList<String>()
        val ywcqList = java.util.ArrayList<String>()
        ttztList.add("未动工")
        ttztList.add("入户清登")
        ttztList.add("交房")
        ttztList.add("挑顶")
        ttztList.add("拆腾")
        ttztList.add("场地清平")
        ttztList.add("销账")

        zfztList.add("未支付")
        zfztList.add("已支付")

        ywcqList.add("无")
        ywcqList.add("有")
        ttzt_spr.adapter = ttztAdapter
        zfzt_spr.adapter = zfztAdapter
        et_ctglpq_add_ywcq.adapter = ywcqAdapter
        ttztAdapter.setDatas(ttztList)
        zfztAdapter.setDatas(zfztList)
        ywcqAdapter.setDatas(ywcqList)


        add_btn.setOnClickListener {
            if (!tv_ctglpq_add_htqx.text.toString().equals("开始时间")&&!tv_ctglpq_add_htqx1.text.toString().equals("结束时间")){
                var dateFormat = SimpleDateFormat("yyyy-MM-dd");
                var date1 = dateFormat.parse(tv_ctglpq_add_htqx.text.toString());//开始时间

                var date2 = dateFormat.parse(tv_ctglpq_add_htqx1.text.toString());//结束时间

                if (date1.time>date2.time){
                    ToastUtils.showShort("开始时间不能大于结束时间")
                    return@setOnClickListener
                }
            }
            if (!xmmc_et.text.toString().equals("")
                    && !cddw_et.text.toString().equals("")
                    && !jsgm_et.text.toString().equals("")
                    && !zfje_et.text.toString().equals("")
                    && !et_ctglpq_add_bdzb.text.toString().equals("")
                    && !et_ctglpq_add_scjzmj.text.toString().equals("")
                    && !et_ctglpq_add_htzdmj.text.toString().equals("")) {
                val i2 = intArrayOf(dataBean.id)
                val pjProBean = PjProjEntitys()
                //pjProBean.setId(dataBean.getCtQyEntity().getId());
                pjProBean.id = dataBean.id
                pjProBean.msid = dataBean.msid
                pjProBean.name = xmmc_et.text.toString()
                pjProBean.impUnit = cddw_et.text.toString()
                pjProBean.projScale = BigDecimal(jsgm_et.text.toString())
                val s = ttztList[ttzt_spr.selectedItemPosition]
                pjProBean.projSpeed = ProjspeedEnum.getIndex(s)
                if (zfztList[zfzt_spr.selectedItemPosition] == "已支付") {
                    pjProBean.projPay = 1
                } else {
                    pjProBean.projPay = 0
                }
                pjProBean.projAmount = BigDecimal(zfje_et.text.toString())
                pjProBean.dabh = et_ctglpq_add_dabh.text.toString()
                pjProBean.projGeo = et_ctglpq_add_bdzb.text.toString().toInt()
                pjProBean.projCoor = et_ctglpq_add_qs.text.toString()
                pjProBean.cqr = et_ctglpq_add_cqrhczr.text.toString()
                pjProBean.qyName = et_ctglpq_add_qymc.text.toString()
                if (!tv_ctglpq_add_htqx.text.toString().equals("开始时间")){
                    pjProBean.scalestartdate = tv_ctglpq_add_htqx.text.toString()
                }
                if (!tv_ctglpq_add_htqx1.text.toString().equals("结束时间")){
                    pjProBean.scaleenddate = tv_ctglpq_add_htqx1.text.toString()
                }
                pjProBean.htmj = BigDecimal(et_ctglpq_add_htzdmj.text.toString())
                pjProBean.jzmj = BigDecimal(et_ctglpq_add_scjzmj.text.toString())
                pjProBean.cqz = et_ctglpq_add_ywcq.selectedItemPosition
                pjProBean.mobile = et_ctglpq_add_lxdh.text.toString()
                if (selectedPhotos.size > 0) {
                    val index = ProjspeedEnum.getIndex(s)
                    var se = 0
                    for (i in selectedPhotos.indices) {
                        se++
//                        uplodeUpdate(File(selectedPhotos[i]), index, pjProBean)
//                        val replace = selectedPhotos[i].replace("\\", "/")
                        upFile(File(selectedPhotos[i]), ArrayList<Int>(), se, 99, ProjspeedEnum.getIndex(s))

                    }
                }
//                var sss: String? = null
              /*  try {
                    val encrypt = AesEncryptUtils.encrypt(Gson().toJson(pjProBean)) //"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
                    sss = "{\"requestData\":\"$encrypt\"}"
                } catch (e: Exception) {
                    e.printStackTrace()
                }*/
                val encrypt = AesEncryptUtils.encrypt(Gson().toJson(pjProBean)) //"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
//                sss = "{"requestData":"$encrypt"}"
                var sss = "{\"requestData\":\"" + encrypt + "\"}"
               /* OkGo.post<String>(ApiConstants.URL_PJPROJUPDATE).upJson(Gson().toJson(pjProBean)).execute(object : BaseNet<String>() {
                    override fun onStart(request: Request<String, out Request<*, *>?>?) {
                        super.onStart(request)
                    }

                    override fun onSuccess(response: Response<String>) {
                        val body = response.body()
                        if (body != null) {
                            var decrypt: String? = null
                            try {
                                decrypt = AesEncryptUtils.decrypt(response.body())
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val json = Gson().fromJson<SuccessBean>(body, object : TypeToken<SuccessBean?>() {}.type)
                            if (json.code == 0) {
                                getCtDetail(idd)
                                ToastUtils.showShort("修改成功！")
                                mAddPop!!.dismiss()
                            } else {
                                ToastUtils.showShort("修改失败！")
                            }
                        }
                    }

                    override fun onError(response: Response<String>) {
                        super.onError(response)
                    }
                })*/
            } else {
                ToastUtils.showShort("请填写内容")
            }
        }
        xmmc_et.setText(dataBean.name)
        cddw_et.setText(dataBean.impUnit)
        jsgm_et.setText(dataBean.projScale.toString())
        ttzt_spr.setSelection(dataBean.projSpeed - 1)
        zfzt_spr.setSelection(dataBean.projPay)//setProjPay
        zfje_et.setText(dataBean.projAmount.toString())//setProjPay
        et_ctglpq_add_dabh.setText(dataBean.dabh.toString())//setProjPay
        et_ctglpq_add_bdzb.setText(dataBean.projGeo.toString())//setProjPay
        et_ctglpq_add_qs.setText(dataBean.projCoor.toString())//setProjPay
        et_ctglpq_add_cqrhczr.setText(dataBean.cqr.toString())//setProjPay
        et_ctglpq_add_qymc.setText(dataBean.qyName.toString())//setProjPay
        tv_ctglpq_add_htqx.setText(dataBean.scalestartdate.toString())//setProjPay
        tv_ctglpq_add_htqx1.setText(dataBean.scaleenddate.toString())//setProjPay
        et_ctglpq_add_htzdmj.setText(dataBean.htmj.toString())//setProjPay
        et_ctglpq_add_scjzmj.setText(dataBean.jzmj.toString())//setProjPay
        et_ctglpq_add_ywcq.setSelection(dataBean.cqz)//setProjPay
        et_ctglpq_add_lxdh.setText(dataBean.mobile.toString())//setProjPay

        tv_ct_detail_zt.setText(ttztList[ttzt_spr.selectedItemPosition] + "现场照片")
    }

    private fun setTime(editText: TextView) {
        val year = Calendar.getInstance()[Calendar.YEAR]
        val month = Calendar.getInstance()[Calendar.MONTH]
        val day = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
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
//            val format: String = CalendarUtil.DATE_FORMAT_HOUR_SE.format(Date()) //$format"  $format"
            editText.text = "$i-$yue-$ri"
//            uploadDate = "$i-$yue-$ri"
        }, year, month, day)
        datePicker.show()
    }

    private fun initStep() {
        steps.add("未动工")
        steps.add("入户")
        steps.add("交房")
        steps.add("挑顶")
        steps.add("拆除")
        steps.add("场清地平")
        steps.add("销账")
    }

    private fun getCtDetail(id: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"

       /* OkGo.post<String>(ApiConstants.URL_PJPROJ_PJINFO).upJson(toJson).execute(object : BaseNet<String>() {
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())


                    val json: BaseRespose<PjInfoBean> = Gson().fromJson(body, object : TypeToken<BaseRespose<PjInfoBean>?>() {}.type)
                    if (json.code == 0) {
                        for (i in json.data.ctQyEntities) {
                            kuangGeoment(i.geom)
                        }
                        *//*tv_ct_detail_xmmc.setText(json.data.pjProjEntity.name)
                        tv_ct_detail_cddw.setText(json.data.pjProjEntity.impUnit)
                        tv_ct_detail_xmgm.setText(json.data.pjProjEntity.projScale.toString())
                        tv_ct_detail_ttzt.setText(steps.get(json.data.pjProjEntity.projSpeed-1))
                        tv_ct_detail_zfje.setText(json.data.pjProjEntity.projAmount.toString())//projAmount*//*
                        tv1_ct_detail_xmmc.setText(json.data.pjProjEntity.name)
                        tv1_ct_detail_cm.setText(json.data.pjProjEntity.planFw)
                        tv1_ct_detail_cddw.setText(json.data.pjProjEntity.impUnit)
//                        tv1_ct_detail_zfqk.setText(json.data.pjProjEntity.impUnit)
                        tv1_ct_detail_dabh.setText(json.data.pjProjEntity.dabh)
                        tv1_ct_detail_bhzb.setText(json.data.pjProjEntity.projGeoText)
                        tv1_ct_detail_qs.setText(json.data.pjProjEntity.projCoor)
                        tv1_ct_detail_cqr.setText(json.data.pjProjEntity.cqr)
                        tv1_ct_detail_qymc.setText(json.data.pjProjEntity.qyName)
                        tv1_ct_detail_chtqx.setText(json.data.pjProjEntity.scalestartdate + " - " + json.data.pjProjEntity.scaleenddate)
                        if (json.data.pjProjEntity.htmj.toDouble() > 10000) {
                            val divide = json.data.pjProjEntity.htmj.divide(BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP)
                            tv1_ct_detail_htzdmj.setText(divide.toString() + "万㎡")
                        } else {
                            tv1_ct_detail_htzdmj.setText(json.data.pjProjEntity.htmj.toString() + "㎡")
                        }
                        if (json.data.pjProjEntity.projScale.toDouble() > 10000) {
                            val divide = json.data.pjProjEntity.projScale.divide(BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP)
                            tv1_ct_detail_sczzd.setText(divide.toString() + "万㎡")
                        } else {
                            tv1_ct_detail_sczzd.setText(json.data.pjProjEntity.projScale.toString() + "㎡")
                        }
                        if (json.data.pjProjEntity.jzmj.toDouble() > 10000) {
                            val divide = json.data.pjProjEntity.jzmj.divide(BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP)
                            tv1_ct_detail_scjzmj.setText(divide.toString() + "万㎡")
                        } else {
                            tv1_ct_detail_scjzmj.setText(json.data.pjProjEntity.jzmj.toString() + "㎡")
                        }
                        if (json.data.pjProjEntity.cqz == 0) {
                            tv1_ct_detail_ywcqz.setText("无")
                        } else {
                            tv1_ct_detail_ywcqz.setText("有")
                        }

                        tv1_ct_detail_lxdh.setText(json.data.pjProjEntity.mobile)
                        if (json.data.pjProjEntity.projPay == 1) {
//                            tv_ct_detail_zfqk.setText("已支付")
                            tv1_ct_detail_zfqk.setText("已支付")
                        } else {
//                            tv_ct_detail_zfqk.setText("未支付")
                            tv1_ct_detail_zfqk.setText("未支付")
                        }





                        list.clear()
                        pjProjFileEnty = json.data
                        rlv_teng_see_photo.layoutManager = GridLayoutManager(this@CtDetailActivity, 3)
//                        val list: MutableList<PjProjFileEnty> = ArrayList<PjProjFileEnty>()
                        for (j in 0..pjProjFileEnty.pjProjFileEnties.size - 1) {
                            if (pjProjFileEnty.pjProjFileEnties.get(j).projSpeed == json.data.pjProjEntity.projSpeed) {
                                list.add(pjProjFileEnty.pjProjFileEnties.get(j))
                            }
                        }
                        baseQuickAdapter = object : BaseQuickAdapter<PjProjFileEnty, BaseViewHolder>(R.layout.item_teng_tui_photo, list) {
                            override fun convert(helper: BaseViewHolder, item: PjProjFileEnty) {
                                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                                //                AppApplication.getGlideUrl(s1)
                                val pic: String = ApiConstants.BASE_URL + item.picPath
                                val s1 = pic.replace("\\", "/")
                                Glide.with(mContext).load(AppApplication.getGlideUrl(s1))
                                        .into(view)
                                helper.setText(R.id.tv_teng_photo_name, item.picName)
                                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                                    //                                    Toast.makeText(this@CtDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                                    var builder = AlertDialog.Builder(this@CtDetailActivity)
// 设置Title的图标
                                    builder.setIcon(R.mipmap.ic_launcher)
// 设置Title的内容
                                    builder.setTitle("弹出警告框")
// 设置Content来显示一个信息
                                    builder.setMessage("确定删除吗？")
// 设置一个PositiveButton
                                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                        *//* @Override
                                         public void onClick(DialogInterface dialog, int which)
                                         {
                                             Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                                         }*//*

                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            deletePic(item.id)
                                        }
                                    });
// 设置一个NegativeButton
                                    builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                                        *//*  @Override
                                          public void onClick(DialogInterface dialog, int which)
                                          {
                                              Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                                          }*//*

                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            dialog!!.dismiss()
                                        }
                                    });
                                    builder.show()

                                }
                                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {

                                    val pic = ApiConstants.BASE_URL + list[helper.adapterPosition].picPath
                                    val s1 = pic.replace("\\", "/")
                                    var pathList = ArrayList<String>()
                                    pathList.add(s1)
                                    PhotoPreview.builder()
                                            .setPhotos(pathList)
                                            .setCurrentItem(0)
//                            .start(activity!!)
                                            .start(this@CtDetailActivity,20)//context!!
                                }
                            }
                        }
                        rlv_teng_see_photo.adapter = baseQuickAdapter
                        initTlv()
                    }

                }

            }
        })*/
    }

    private fun deletePic(id: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"

        OkGo.post<String>(ApiConstants.URL_PJPROJFILE_DELETE).upJson(jsonObject).execute(object : BaseNet<String>() {
            override fun onSuccess(response: Response<String>?) {
                val body = response!!.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())


                    val json: BaseRespose<PjInfoBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PjInfoBean>?>() {}.type)
                    if (json.code == 0) {
                        Toast.makeText(this@CtDetailActivity, "删除成功", Toast.LENGTH_SHORT).show()
                        getCtDetail(idd)
                    }
                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                Toast.makeText(this@CtDetailActivity, "删除失败", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initTlv() {


        tlv_tent_tui.setPointStrings(steps, pjProjFileEnty.pjProjEntity.projSpeed.toFloat())//pjProjFileEnty.pjProjEntity.projSpeed.toFloat()
        tlv_tent_tui.setOnStepChangedListener(object : TimeLineView.OnStepChangedListener {
            override fun onchanged(view: TimeLineView?, step: Int, stepStr: String?) {

                if (step < pjProjFileEnty.pjProjEntity.projSpeed.toInt()) {//pjProjFileEnty.pjProjEntity.projSpeed.toInt()
                    tv_ct_detail_zt.setText(stepStr + "现场照片")
//                    Toast.makeText(this@CtDetailActivity, step.toString() + "  " + stepStr, Toast.LENGTH_SHORT).show()
                    list.clear()
                    for (j in 0..pjProjFileEnty.pjProjFileEnties.size - 1) {
                        if (pjProjFileEnty.pjProjFileEnties.get(j).projSpeed == (step + 1)) {
                            list.add(pjProjFileEnty.pjProjFileEnties.get(j))
                        }
                    }
                    baseQuickAdapter!!.setNewData(list)
                    baseQuickAdapter!!.notifyDataSetChanged()
                }
            }
        })


    }

    private fun initPopuStepUp() {//建筑面积
        //建筑面积
        selectedPhotos.clear()

        stepUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_ct_detail, ll_ct_detail)
//        stepUpPopu!!.isFocusable=false
        //        stepUpPopu!!.isFocusable=false
        val contentView2: View = stepUpPopu!!.getContentView()
        val tvPopCtDetailJdb: TextView = contentView2.findViewById(R.id.tv_pop_ct_detail_jdb)
        val butMapQc: Button = contentView2.findViewById(R.id.but_map_qc)
        val butMapTj: Button = contentView2.findViewById(R.id.but_map_tj)
        val recyPopMapPhoto: RecyclerView = contentView2.findViewById(R.id.recy_pop_map_photo)
        tvPopCtDetailJdb.setText(steps.get(pjProjFileEnty.pjProjEntity.projSpeed - 1))
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        recyPopMapPhoto.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recyPopMapPhoto.adapter = photoAdapter
        recyPopMapPhoto.addOnItemTouchListener(RecyclerItemClickListener(this,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(this@CtDetailActivity)
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(this@CtDetailActivity)
                        }
                    }
                }))
        butMapTj.setOnClickListener {
            if (selectedPhotos.size > 0) {
                dialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .showCancelButton(false)
                        .showContentText(false)
                        .setTitleText("正在上传")
//                dialog?.setCancelable(false)
                //                dialog?.setCancelable(false)
                dialog!!.show()
                val intList: ArrayList<Int> = ArrayList<Int>()
                var se = 0
                for (i in selectedPhotos) {
                    se++
                    val file = File(i)
                    var newPath = ""
                    var success = false
                    //我感觉问题应该是出在这里  判断当前是否压缩成功
                    newPath = AppMenus.getInstance().cardPath.toString() + "JYMJManager/uploader/Pictures/" + file.name
                    success = ImageUtils.saveExifAndCompressByQuality(i, newPath, 1000, true)
                    if (success) {
                        val file2 = File(newPath)
                        upFile(file2, intList, se, 99, 1)
                    } else {
                        val file3 = File(i)
                        upFile(file3, intList, se, 99, 1)
                        /*   dialog .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog .setTitleText("上传失败");
                            dialog .setConfirmText("确定");
                            return ;*/
                    }
                }

            }
        }
        butMapQc.setOnClickListener {
            if (stepUpPopu != null)
                stepUpPopu!!.dismiss()
        }
        CommenPop.backgroundAlpha(0.5f, this)
        stepUpPopu!!.showAtLocation(ll_ct_detail, Gravity.CENTER, 0, 0)

    }

    private fun upFile(file2: File, intList: ArrayList<Int>, se: Int, se1: Int, projspeed: Int) {
        val request = OkGo.post<String>(ApiConstants.URL_PJPROJFILE_UPLOAD)
                .isMultipart(true)
        //        request.params("pid", AppCache.getInstance().getProID());
        request.params("projSpeed", projspeed)//pjProjFileEnty.pjProjEntity.projSpeed
        request.params("pjid", pjProjFileEnty.pjProjEntity.id)
        //        request.params("entime", tvUpDate!!.text.toString())
        request.params(file2.name, file2)
        request.execute(object : BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<*, *>?>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>) {
                val body = response.body()
//                intList.add(body.data.toInt())
                if (se == selectedPhotos.size || se1 == selectedPhotos.size) {
                    if (stepUpPopu != null)
                        stepUpPopu!!.dismiss()
                    ToastUtils.showShort("上传成功")
                    /*dialog!!.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                    dialog!!.titleText = "上传成功"
                    dialog!!.confirmText = "确定"*/
                    getCtDetail(idd)
                    /* pjContract.fileIds=intList.toTypedArray()
                    if (isUpdata==0){
                        mPresenter.undateEnvironmental(pjContract)
                    }else if (isUpdata==1){
                        mPresenter.addEnvironmental(pjContract)
                    }else{
                        mPresenter.getEnvironmental(AppCache.getInstance().getProID(),1,limit,lastSelect)
                    }*/
//                    mPresenter.getEnvironmental(AppCache.getInstance().proID,1,limit,lastSelect)
                }
            }

            override fun onError(response: Response<String>) {
                super.onError(response)
                if (stepUpPopu != null)
                    stepUpPopu!!.dismiss()
                ToastUtils.showShort("上传失败")
                /*dialog!!.changeAlertType(SweetAlertDialog.ERROR_TYPE)
                dialog!!.titleText = "上传失败"
                dialog!!.contentText = response.exception.message
                dialog!!.confirmText = "确定"*/
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            var photos: ArrayList<String>? = null
            selectedPhotos.clear()
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
            }
            if (photos != null) {
                selectedPhotos.addAll(photos)
            }
            photoAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onMapClick(p0: LatLng?) {

    }

    override fun deactivate() {

    }

    override fun activate(p0: LocationSource.OnLocationChangedListener?) {
    }


}
