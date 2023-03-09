package com.jymj.zhglxt.xm.fragment


import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.maps2d.*
import com.amap.api.maps2d.model.*
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.jymj.basemessagesystem.ui.views.LegendEntity

import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.widget.zdybj.bean.TimeLineXmItem
import com.jymj.zhglxt.xm.contract.XmJbqkContract
import com.jymj.zhglxt.xm.presenter.XmJbqkPresenter
import com.jymj.zhglxt.zjd.bean.cygl.DkfVO
import com.jymj.zhglxt.zjd.bean.tdxg.GhVO
import com.jymj.zhglxt.zjd.bean.xm.*
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.polylines
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.frag_xm_homepage_include.*
import kotlinx.android.synthetic.main.frag_xm_jbqk_jsqk_1_include.*
import kotlinx.android.synthetic.main.frag_xm_jbqk_jsqk_2_include.*
import kotlinx.android.synthetic.main.frag_xm_jbqk_jsqk_3_include.*
import kotlinx.android.synthetic.main.frag_xm_jbqk_jsqk_4_include.*
import kotlinx.android.synthetic.main.frag_xm_jbqk_slideup_include.*
import kotlinx.android.synthetic.main.fragment_xm_jbqk.*
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * 项目模块基本情况fragment
 */
class XmJbqkFragment : BaseFragment<XmJbqkPresenter, BaseModel>(), XmJbqkContract.View, AMap.OnMapClickListener {

    //    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
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
    private var addMarker1: Marker? = null
    private var onCameraChange: LatLng? = null
    private var pointStr = ""
    /*private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()*/
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


    var checkType = 3
    val MODE_NORMAL = 100
    private var click_mode: Int = MODE_NORMAL
    val POINT_CLICK = 201//点查
    val POLYGON_CLICK = 202//框查
    private var mLatLng: LatLng? = null
    public val mLayers = ArrayList<LayerEntity>()
    public val mLayers1 = ArrayList<LayerEntity>()
    public var legendsYL = ArrayList<LegendEntity>()

    private var mPolylineOptions: PolylineOptions? = null
    private var addPolyline: Polyline? = null//高亮显示

    private var tabTitles = arrayOf("一季度", "二季度", "三季度", "四季度")
    private var addProjectUpPopu: CommenPop? = null//添加项目弹出框
//    private val delayedLoad = SubstepDelayedLoad()
    var conditionPickerView: OptionsPickerView<Any>? = null//条件选择器
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_xm_jbqk
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {

//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//添加天地图
//        MapsInitializer.replaceURL("https://c-ssl.duitang.com/uploads/item/202004/09/20200409032441_mhYLN.jpeg", "tdt-kbt")//VEC_URL
        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL
        map_frag_xmjbqk.onCreate(activity!!.intent.extras)
        /*delayedLoad
                .delayed(100)
                .run(object: Runnable {
                    override fun run() {

                    }
                })
                .delayed(200)
                .run(object: Runnable {
                    override fun run() {

                    }
                })*/
        initAMap()
        setAMap()
        splt_frag_xm_top.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

//        legendsYL = ArrayList<LegendEntity>()
//        legends4L = ArrayList<LegendEntity>()


        //点击事件选中
        bt_frag_xm_point.setOnClickListener {
            //点击事件
            if (bt_frag_xm_point.isActivated) {

                bt_frag_xm_point.isActivated = false

                click_mode = MODE_NORMAL//选中点击事件

            } else {

                bt_frag_xm_point.isActivated = true
                click_mode = POINT_CLICK

            }
        }

        for (i in tabTitles) {
            tab_frag_xm_jbqk_jsqk.addTab(tab_frag_xm_jbqk_jsqk.newTab().setText(i))
        }
        val linearLayout = tab_frag_xm_jbqk_jsqk.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        linearLayout.dividerDrawable = ContextCompat.getDrawable(activity!!, R.drawable.tablayout_split_line)
        linearLayout.dividerPadding = 30

        val sptypeList = ArrayList<TimeLineXmItem>()
        sptypeList.add(TimeLineXmItem("项目立项", ""))//2000-00-00
        sptypeList.add(TimeLineXmItem("EPC招标", ""))
        sptypeList.add(TimeLineXmItem("手续办理", ""))
        sptypeList.add(TimeLineXmItem("施工阶段", ""))
        sptypeList.add(TimeLineXmItem("合同签订", ""))
        tll_frag_xm_jbqk_xmjz.setValueText(sptypeList, 2, 2)


        tv_frag_homepage_addxm.setOnClickListener {
            frag_xm_homepage_include.visibility = View.GONE
            frag_xm_jbqk_slideup_include.visibility = View.VISIBLE
        }

        tv_frag_xm_jbqk_fhzl.setOnClickListener {
            frag_xm_homepage_include.visibility = View.VISIBLE
            frag_xm_jbqk_slideup_include.visibility = View.GONE
        }

        bt_frag_point_add.setOnClickListener {
            if (gidList.size == 0) {
                ToastUtils.showShort("请至少选择一个项目！")
            } else {
                addProjectPop()
            }
        }


        et_xm_jsqk_1_bdcqz.addTextChangedListener(listener(et_xm_jsqk_1_bdcqz))
        et_xm_jsqk_2_bdcqz.addTextChangedListener(listener(et_xm_jsqk_2_bdcqz))
        et_xm_jsqk_3_bdcqz.addTextChangedListener(listener(et_xm_jsqk_3_bdcqz))
        et_xm_jsqk_4_bdcqz.addTextChangedListener(listener(et_xm_jsqk_4_bdcqz))
        et_xm_jsqk_1_ydghxkz.addTextChangedListener(listener(et_xm_jsqk_1_ydghxkz))
        et_xm_jsqk_2_ydghxkz.addTextChangedListener(listener(et_xm_jsqk_2_ydghxkz))
        et_xm_jsqk_3_ydghxkz.addTextChangedListener(listener(et_xm_jsqk_3_ydghxkz))
        et_xm_jsqk_4_ydghxkz.addTextChangedListener(listener(et_xm_jsqk_4_ydghxkz))
        et_xm_jsqk_1_ggz.addTextChangedListener(listener(et_xm_jsqk_1_ggz))
        et_xm_jsqk_2_ggz.addTextChangedListener(listener(et_xm_jsqk_2_ggz))
        et_xm_jsqk_3_ggz.addTextChangedListener(listener(et_xm_jsqk_3_ggz))
        et_xm_jsqk_4_ggz.addTextChangedListener(listener(et_xm_jsqk_4_ggz))
        et_xm_jsqk_1_tfgc.addTextChangedListener(listener(et_xm_jsqk_1_tfgc))
        et_xm_jsqk_2_tfgc.addTextChangedListener(listener(et_xm_jsqk_2_tfgc))
        et_xm_jsqk_3_tfgc.addTextChangedListener(listener(et_xm_jsqk_3_tfgc))
        et_xm_jsqk_4_tfgc.addTextChangedListener(listener(et_xm_jsqk_4_tfgc))
        et_xm_jsqk_1_htqd.addTextChangedListener(listener(et_xm_jsqk_1_htqd))
        et_xm_jsqk_2_htqd.addTextChangedListener(listener(et_xm_jsqk_2_htqd))
        et_xm_jsqk_3_htqd.addTextChangedListener(listener(et_xm_jsqk_3_htqd))
        et_xm_jsqk_4_htqd.addTextChangedListener(listener(et_xm_jsqk_4_htqd))
//msg: "Authentication failed for token submission [org.apache.shiro.authc.UsernamePasswordToken - szqzw, rememberMe=false].
// Possible unexpected error? (Typical or expected login exceptions should extend from AuthenticationException)."
        mlt_frag_xm_homepage_jsqk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_frag_xm_homepage_jsqk.isShown){
                    ll_frag_xm_homepage_jsqk.visibility = View.GONE
                }else{
                    ll_frag_xm_homepage_jsqk.visibility = View.VISIBLE
                }
            }
        })
        mlt_frag_xm_homepage_tzqk.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_frag_xm_homepage_tzqk.isShown){
                    ll_frag_xm_homepage_tzqk.visibility = View.GONE
                }else{
                    ll_frag_xm_homepage_tzqk.visibility = View.VISIBLE
                }
            }
        })

    }

    override fun initDatas() {
        moveCenter()//延时定位到项目镇

        mPresenter.getPorjectList(AppCache.getInstance().cunCode)
        mPresenter.getPorjectStatistics(AppCache.getInstance().cunCode)

    }


    private fun addProjectPop() {
        addProjectUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_project, splt_frag_xm_top)
        val contentView = addProjectUpPopu!!.contentView
        val et_xmnc = contentView.findViewById<EditText>(R.id.pop_add_project_et_xmnc)
        val et_xmlb = contentView.findViewById<EditText>(R.id.pop_add_project_et_xmlb)
        val et_ydmj = contentView.findViewById<EditText>(R.id.pop_add_project_et_ydmj)
        val et_zdmj = contentView.findViewById<EditText>(R.id.pop_add_project_et_zdmj)
        val et_dsw = contentView.findViewById<EditText>(R.id.pop_add_project_et_dsw)
        val et_dzrk = contentView.findViewById<EditText>(R.id.pop_add_project_et_dzrk)
        val et_tzzt = contentView.findViewById<EditText>(R.id.pop_add_project_et_tzzt)
        val spinner = contentView.findViewById<Spinner>(R.id.pop_add_project_spr_tzfs)
        val et_zjxq = contentView.findViewById<EditText>(R.id.pop_add_project_et_zjxq)
        val confirm = contentView.findViewById<TextView>(R.id.pop_add_project_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_add_project_close)

        CommenPop.backgroundAlpha(0.5f, activity)
        addProjectUpPopu!!.showAtLocation(splt_frag_xm_top, Gravity.CENTER, 0, 0)

        val sprList = ArrayList<String>()
        sprList.add("政府投资")
        sprList.add("政府补助")
        sprList.add("社会投资")
        val sprAdapter = com.jymj.zhglxt.widget.SpinnerAdapter(activity)
        spinner.adapter = sprAdapter
        sprAdapter.setDatas(sprList)

        confirm.setOnClickListener {
            val entity = ProjectEntity()
            entity.name = et_xmnc.text.toString()
            entity.xmlb = et_xmlb.text.toString()
            entity.area = et_ydmj.text.toString().toBigDecimal()
            entity.zdmj = et_zdmj.text.toString().toBigDecimal()
            entity.dsw = et_dsw.text.toString().toBigDecimal()
            entity.dzrk = et_dzrk.text.toString().toInt()
            entity.tzzt = et_tzzt.text.toString()
            entity.tzfs = spinner.selectedItem.toString()
            entity.zjxq = et_zjxq.text.toString().toBigDecimal()
            entity.code = AppCache.getInstance().cunCode
            entity.ghid.addAll(gidList)

            if (et_xmnc.text.toString().equals("")) {
                ToastUtils.showShort("请输入项目名称！")
            } else {
                mPresenter.getPorjectSave(entity)
            }

        }

        close.setOnClickListener {
            addProjectUpPopu!!.dismiss()
        }

    }

    private fun tabTc(tuCe: String, type: Int) {//type 1 清 2 不清 3 选中  , type:Int
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
            } else if (type == 3) {
                if (i.name.equals(tuCe)) {
                    i.isCheck = true
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
            } else if (type == 3) {
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
        if (aMap != null) {
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

    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {

        for (l in layers) {
            when (l.name) {
                "影像图" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(opt_tdtSta)
                        aMap!!.addTileOverlay(opt_tdtStaN)
                    }
                }
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
                "土现" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                    }
                }
                "经济分布" -> {
                    if (l.isCheck) {
                        aMap!!.addTileOverlay(l.opt)
                    }
                }
                "项目" -> {//规划
                    if (l.isCheck) {
                        if ("100110111009022".contains(AppCache.getInstance().code)){
//                        aMap!!.addTileOverlay(l.opt)   Honor


                            /*var bounds = LatLngBounds.Builder()//设置显示在屏幕中的地图地理范围,地图中点显示为两个点的中点
                                    .include(LatLng(39.7317, 115.793663))
                                    .include(LatLng(39.7328, 115.903936))
                                    .include(LatLng(39.6510, 115.795362))
                                    .include(LatLng(39.6519, 115.90509)).build();//LatLngBounds*/

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
//                                    .image(BitmapDescriptorFactory.fromAsset(""))//覆盖物图片  hsd  R.drawable.hsd
//                                    .image(BitmapDescriptorFactory.fromPath("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F041621122252%2F210416122252-1-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670916836&t=3c156e8abb723ea98fcbf0e11c0cd20e"))//覆盖物图片  hsd  R.drawable.hsd
                                    .positionFromBounds(bounds));
                        }
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
    private fun moveCenter() {
        aMap!!.setOnMapLoadedListener(object : AMap.OnMapLoadedListener {
            //监听加载完成
            override fun onMapLoaded() {
                Thread(object : Runnable {
                    override fun run() {
                        try {
                            Thread.sleep(2000); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法

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
                    val point = getCenter(AppCache.getInstance().loginCenter)
                    if (AppCache.getInstance().loginCenter.length == 9) {
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 13.5f, 0f, 0f)))
                    } else {
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 15f, 0f, 0f)))
                    }
                    aMap?.addMarker(MarkerOptions().position(point))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()*/
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

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_xmjbqk.getMap()
        }
        setUpMap()
    }

    override fun onMapClick(p0: LatLng?) {
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
        if (SingleOnClickUtil.isFastClick()) {
            if (addMarker != null) {
                addMarker!!.remove()
            }
            val markerOptions = MarkerOptions()
            addMarker = aMap!!.addMarker(markerOptions.position(latLng))
            pointStr = "POINT(" + latLng.longitude + " " + latLng.latitude + ")"

//            mPresenter.getDkfPoint(pointStr)
            mPresenter.getKjghPoint(pointStr)

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
        mPolygon = aMap!!.addPolygon(mPolygonOptions)
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() { // 自定义系统定位蓝点
        mMyLocationStyle = MyLocationStyle()
        //自定义精度范围的圆形边框宽度
        mMyLocationStyle!!.strokeWidth(3f)
        mMyLocationStyle!!.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW)
        aMap!!.uiSettings.isMyLocationButtonEnabled = true // 设置默认定位按钮是否显示
        //        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap!!.setMyLocationStyle(mMyLocationStyle)
        aMap!!.isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
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


    override fun onResume() {
        super.onResume()
        map_frag_xmjbqk.onResume()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

    }
    fun setVisible(){
        // 相当于onResume()方法--获取焦点
        if (aMap!=null)
        aMap!!.clear()
        clearMap()
        mLayers.clear()
        mLayers1.clear()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//AppCache.getInstance().proID.toInt()  ,legends4L,1  AppCache.getInstance().proID.toInt()
        for (l in mLayers) {
            l.isCheck = false
            when (l.name) {
               /* "天地图" -> {//底图
                    l.isCheck = true
                }*/
                "项目" -> {
                    l.isCheck = true
                }
              /*  "规划" -> {
                    l.isCheck = true
                }*/
                "区界" -> {
                    l.isCheck = true
                }
                "镇界" -> {
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
               /* "天地图" -> {//底图
                    l.isCheck = true
                }*/
                "项目" -> {
                    l.isCheck = true
                }
                /*"规划" -> {
                    l.isCheck = true
                }*/
                "区界" -> {
                    l.isCheck = true
                }
                "镇界" -> {
                    l.isCheck = true
                }
                "村界" -> {
                    l.isCheck = true
                }
            }
        }

        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
//        tabTc("天地图", 3)
//        tabTc("村界", 2)
        /*tabTc("项目", 2)
        tabTc("规划", 2)
        tabTc("镇界", 2)
        tabTc("村界", 2)*/
        mPresenter.getXmList()
        Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(500); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法

                    var point: LatLng? = null
                    if (!AppCache.getInstance().xzCenter.equals("")){// 6  9  12  15
                        point = getCenter(AppCache.getInstance().xzCenter)
                    }else{
                        point = getCenter(AppCache.getInstance().loginCenter)
                    }
                    if (AppCache.getInstance().code.length==6&&point!=null){
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 9.5f, 0f, 0f)))
                    }
                    else if (AppCache.getInstance().code.length==9&&point!=null){
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 10.5f, 0f, 0f)))
                    }
                    else if (AppCache.getInstance().code.length==12&&point!=null){
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 11.5f, 0f, 0f)))
                    }
                    else if (AppCache.getInstance().code.length==15&&point!=null){
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 12.5f, 0f, 0f)))
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_frag_xmjbqk.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        map_frag_xmjbqk.onDestroy()
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

    fun kuangGeomentLine(dataGeometry: String) {
        /*if (addPolyline!=null){
            addPolyline?.remove()
        }*/
        for (i in polylines) {
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
                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 17.5f, 0f, 0f)))
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
                        polylines.add(addPolyline!!)
                    }
                } else {
                    val latList = getLatList(split[i])
                    if (i == 0) {
                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 14.5f, 0f, 0f)))
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

    //初始化选择器
    private fun initNationPickerView(type: String) {
        conditionPickerView = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            if (type.equals("投资方式")) {
                tv_xm_jbqk_tzfs.text = getTzfsData()[options1]
            } else if (type.equals("项目立项1")) {
                tv_xm_jsqk_1_xmlx.text = getLxOrEpcData()[options1]
            } else if (type.equals("项目立项2")) {
                tv_xm_jsqk_2_xmlx.text = getLxOrEpcData()[options1]
            } else if (type.equals("项目立项3")) {
                tv_xm_jsqk_3_xmlx.text = getLxOrEpcData()[options1]
            } else if (type.equals("项目立项4")) {
                tv_xm_jsqk_4_xmlx.text = getLxOrEpcData()[options1]
            } else if (type.equals("EPC招标1")) {
                tv_xm_jsqk_1_epc.text = getLxOrEpcData()[options1]
            } else if (type.equals("EPC招标2")) {
                tv_xm_jsqk_2_epc.text = getLxOrEpcData()[options1]
            } else if (type.equals("EPC招标3")) {
                tv_xm_jsqk_3_epc.text = getLxOrEpcData()[options1]
            } else if (type.equals("EPC招标4")) {
                tv_xm_jsqk_4_epc.text = getLxOrEpcData()[options1]
            } else if (type.equals("手续办理1")) {
                tv_xm_jsqk_1_sxbl.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("手续办理2")) {
                tv_xm_jsqk_2_sxbl.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("手续办理3")) {
                tv_xm_jsqk_3_sxbl.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("手续办理4")) {
                tv_xm_jsqk_4_sxbl.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("施工阶段1")) {
                tv_xm_jsqk_1_sgjd.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("施工阶段2")) {
                tv_xm_jsqk_2_sgjd.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("施工阶段3")) {
                tv_xm_jsqk_3_sgjd.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("施工阶段4")) {
                tv_xm_jsqk_4_sgjd.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("合同签订1")) {
                tv_xm_jsqk_1_htqd.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("合同签订2")) {
                tv_xm_jsqk_2_htqd.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("合同签订3")) {
                tv_xm_jsqk_3_htqd.text = getSxSgHtEpcData()[options1]
            } else if (type.equals("合同签订4")) {
                tv_xm_jsqk_4_htqd.text = getSxSgHtEpcData()[options1]
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(type)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setContentTextSize(20)
                .isDialog(true)
                .build<Any>()
        if (type.equals("投资方式")) {
            conditionPickerView!!.setPicker(getTzfsData() as List<Any>?)//一级选择器
        } else if (type.equals("项目立项1") || type.equals("项目立项2") || type.equals("项目立项3") || type.equals("项目立项4") ||
                type.equals("EPC招标1") || type.equals("EPC招标2") || type.equals("EPC招标3") || type.equals("EPC招标4")) {
            conditionPickerView!!.setPicker(getLxOrEpcData() as List<Any>?)//一级选择器
        } else if (type.equals("手续办理1") || type.equals("手续办理2") || type.equals("手续办理3") || type.equals("手续办理4") ||
                type.equals("施工阶段1") || type.equals("施工阶段2") || type.equals("施工阶段3") || type.equals("施工阶段4") ||
                type.equals("合同签订1") || type.equals("合同签订2") || type.equals("合同签订3") || type.equals("合同签订4")) {
            conditionPickerView!!.setPicker(getSxSgHtEpcData() as List<Any>?)//一级选择器
        }

        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = conditionPickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        conditionPickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        conditionPickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        conditionPickerView!!.show()
    }

    //初始化投资方式数据
    private fun getTzfsData(): ArrayList<String> {

        val list = ArrayList<String>()

        list.add("政府投资")
        list.add("政府补助")
        list.add("社会投资")

        return list
    }

    //初始化项目立项和EPC招标状态选择数据
    private fun getLxOrEpcData(): ArrayList<String> {

        val list = ArrayList<String>()

        list.add("未开始")
        list.add("已完成")

        return list
    }

    //初始化手续办理、施工阶段和合同签订状态选择数据
    private fun getSxSgHtEpcData(): ArrayList<String> {

        val list = ArrayList<String>()

        list.add("未开始")
        list.add("办理中")
        list.add("已完成")

        return list
    }

    override fun returnDkfPoint(cash: List<DkfVO>) {
        if (cash != null && cash.size > 0) {
            val dataGeometry = cash.get(0).geometry
            kuangGeomentLine(dataGeometry)
            PopuPointUtils.startPopuDkfPoint(splt_frag_xm_top, cash.get(0))
        } else {
            ToastUtils.showShort("暂无数据！")
        }
    }

    private val gidList = ArrayList<Long>()

    override fun onPointKjgh(tdlyEntity: GhVO) {
        if (tdlyEntity != null) {
            if (!tdlyEntity.ghmc.equals("")) {//如果有村名显示弹出框
                kuangGeomentLine(tdlyEntity.geometry)
            }
            if (tdlyEntity.gid != null) {
                gidList.add(tdlyEntity.gid)
            }
        }
    }

    override fun returnPorjectSave(msg: String) {
        gidList.clear()
        ToastUtils.showShort("项目添加成功")
        if (addProjectUpPopu != null) {
            addProjectUpPopu!!.dismiss()
        }
        mPresenter.getPorjectList(AppCache.getInstance().cunCode)
        mPresenter.getPorjectStatistics(AppCache.getInstance().cunCode)
    }

    override fun returnPorjectList(msg: List<BcXmfwEntity>) {
        var jdStr = ""
        var type = ""
//        if (msg != null && msg.size > 0) {
        val arrayList = ArrayList<BcXmfwEntity>()
        if (msg.size>0){
            val get = msg.get(0)

            tv_frag_xm_homepage_zxmfw_ydmj.setText(get.area.toString())
            tv_frag_xm_homepage_jsqk_ydmj.setText(get.ydgm.toString())
            tv_frag_xm_homepage_jsqk_zjzmj.setText(get.jzgm.toString())

        }
        tv_frag_xm_jbqk_fhzl1.setOnClickListener {

            frag_xm_homepage_include.visibility = View.VISIBLE
            frag_xm_jbqk_slideup_include.visibility = View.GONE
        }
        for (i in msg.indices){
            if (i!=0){
                arrayList.add(msg.get(i))
            }
        }
        rlv_frag_xm_homepage_xmlb.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            rlv_frag_xm_homepage_xmlb.adapter = object : BaseQuickAdapter<BcXmfwEntity, BaseViewHolder>(R.layout.item_project_list, arrayList) {
                override fun convert(helper: BaseViewHolder?, item: BcXmfwEntity?) {


                    helper!!.setText(R.id.item_project_list_xmmc, item!!.xmmc)
                            .setText(R.id.item_project_list_xmlx, item.xmlx)
                            .setText(R.id.item_project_list_ydgm, item.ydgm.toString())
                            .setText(R.id.item_project_list_jzgm, item.jzgm.toString())
                            .setText(R.id.item_project_list_zjly, item.zjly)
//                            .setText(R.id.item_project_list_ztze, item.zjxq.toString())
//                            .setText(R.id.item_project_list_jz, jdStr + "（" + type + "）")



                    helper.itemView.setOnClickListener {
//                        mPresenter.getPorjectInfo(item.id)
//                        kuangGeomentLine(item.geometry)
                        //center

                        addMarker1?.remove()
                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(getCenter(item.center), 17.5f, 0f, 0f)))
                        val markerOptions = MarkerOptions()
                        addMarker1 = aMap!!.addMarker(markerOptions.position(getCenter(item.center)))

                        frag_xm_homepage_include.visibility = View.GONE
                        frag_xm_jbqk_slideup_include.visibility = View.VISIBLE
                        if (item.xmmc.equals("幽岚山童话森林")){
                            iv_frag_xm_suod.visibility = View.VISIBLE
                            iv_frag_xm_dbg.visibility = View.GONE
                            iv_frag_xm_yh.visibility = View.GONE
                            iv_frag_xm_yzy.visibility = View.GONE
                            iv_frag_xm_thsl.visibility = View.GONE
                            iv_frag_xm_yfy.visibility = View.GONE


                        }else if (item.xmmc.equals("坡峰岭景区")){

                            iv_frag_xm_suod.visibility = View.GONE
                            iv_frag_xm_dbg.visibility = View.VISIBLE
                            iv_frag_xm_yh.visibility = View.GONE
                            iv_frag_xm_yzy.visibility = View.GONE
                            iv_frag_xm_thsl.visibility = View.GONE
                            iv_frag_xm_yfy.visibility = View.GONE
                        }else if (item.xmmc.equals("迎风峪沟")){

                            iv_frag_xm_suod.visibility = View.GONE
                            iv_frag_xm_dbg.visibility = View.GONE
                            iv_frag_xm_yh.visibility = View.VISIBLE
                            iv_frag_xm_yzy.visibility = View.GONE
                            iv_frag_xm_thsl.visibility = View.GONE
                            iv_frag_xm_yfy.visibility = View.GONE
                        }else if (item.xmmc.equals("鸭子峪沟")){

                            iv_frag_xm_suod.visibility = View.GONE
                            iv_frag_xm_dbg.visibility = View.GONE
                            iv_frag_xm_yh.visibility = View.GONE
                            iv_frag_xm_yzy.visibility = View.VISIBLE
                            iv_frag_xm_thsl.visibility = View.GONE
                            iv_frag_xm_yfy.visibility = View.GONE
                        }else if (item.xmmc.equals("大北沟")){

                            iv_frag_xm_suod.visibility = View.GONE
                            iv_frag_xm_dbg.visibility = View.GONE
                            iv_frag_xm_yh.visibility = View.GONE
                            iv_frag_xm_yzy.visibility = View.GONE
                            iv_frag_xm_thsl.visibility = View.VISIBLE
                            iv_frag_xm_yfy.visibility = View.GONE
                        }else if (item.xmmc.equals("云湖片区")){

                            iv_frag_xm_suod.visibility = View.GONE
                            iv_frag_xm_dbg.visibility = View.GONE
                            iv_frag_xm_yh.visibility = View.GONE
                            iv_frag_xm_yzy.visibility = View.GONE
                            iv_frag_xm_thsl.visibility = View.GONE
                            iv_frag_xm_yfy.visibility = View.VISIBLE
                        }

                    }

                }
            }
//        }
    }

    var quarterFlag = 0//当前季度

    //项目详情
    override fun returnPorjectInfo(msg: ProjectEntity) {
        if (msg != null) {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            quarterFlag = if (month%3==0){
                month/3
            }else{
                month/3+1
            }
            val nian = tv_frag_xm_jsqk_year.text.toString().substring(0, 4).toInt()
            if (nian != year){
                ll_xm_jsqk_xmlx_1.setNoClick(true)//禁止该布局下的点击事件
                ll_xm_jsqk_xmlx_2.setNoClick(true)//禁止该布局下的点击事件
                ll_xm_jsqk_xmlx_3.setNoClick(true)//禁止该布局下的点击事件
                ll_xm_jsqk_xmlx_4.setNoClick(true)//禁止该布局下的点击事件
                ll_xm_jsqk_xmlx_1.alpha = 0.3f
                ll_xm_jsqk_xmlx_2.alpha = 0.3f
                ll_xm_jsqk_xmlx_3.alpha = 0.3f
                ll_xm_jsqk_xmlx_4.alpha = 0.3f
            }else{
                when(quarterFlag){
                    1->{
                        ll_xm_jsqk_xmlx_1.setNoClick(false)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_2.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_3.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_4.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_1.alpha = 1f
                        ll_xm_jsqk_xmlx_2.alpha = 0.3f
                        ll_xm_jsqk_xmlx_3.alpha = 0.3f
                        ll_xm_jsqk_xmlx_4.alpha = 0.3f
                    }
                    2->{
                        ll_xm_jsqk_xmlx_1.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_2.setNoClick(false)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_3.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_4.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_1.alpha = 0.3f
                        ll_xm_jsqk_xmlx_2.alpha = 1f
                        ll_xm_jsqk_xmlx_3.alpha = 0.3f
                        ll_xm_jsqk_xmlx_4.alpha = 0.3f
                    }
                    3->{
                        ll_xm_jsqk_xmlx_1.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_2.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_3.setNoClick(false)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_4.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_1.alpha = 0.3f
                        ll_xm_jsqk_xmlx_2.alpha = 0.3f
                        ll_xm_jsqk_xmlx_3.alpha = 1f
                        ll_xm_jsqk_xmlx_4.alpha = 0.3f
                    }
                    4->{
                        ll_xm_jsqk_xmlx_1.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_2.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_3.setNoClick(true)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_4.setNoClick(false)//禁止该布局下的点击事件
                        ll_xm_jsqk_xmlx_1.alpha = 0.3f
                        ll_xm_jsqk_xmlx_2.alpha = 0.3f
                        ll_xm_jsqk_xmlx_3.alpha = 0.3f
                        ll_xm_jsqk_xmlx_4.alpha = 1f
                    }
                }
            }
            //////////////////////该写按步骤点击这块儿了///////////////////////////
            if (tv_xm_jsqk_3_xmlx.text.equals("未完成")){
                ll_xm_jsqk_3_epc.isEnabled = false
            }


            frag_xm_homepage_include.visibility = View.GONE
            frag_xm_jbqk_slideup_include.visibility = View.VISIBLE

            tv_xm_jbqk_xmmc.setText(msg.name)
            tv_xm_jbqk_xmlx.setText(msg.xmlb)
            tv_xm_jbqk_ydmj.setText(msg.area.toString())
            tv_xm_jbqk_zdmj.setText(msg.zdmj.toString())
            tv_xm_jbqk_dsw.setText(msg.dsw.toString())
            tv_xm_jbqk_dzrk.setText(msg.dzrk.toString())
            tv_xm_jbqk_tzzt.setText(msg.tzzt.toString())
            tv_xm_jbqk_tzfs.setText(msg.tzfs.toString())
            tv_xm_jbqk_zjxq.setText(msg.zjxq.toString())
            xmjdzdmjStr = msg.xmjdzdmj
            xnjddswStr = msg.xnjddsw
            xmjddzrkStr = msg.xmjddzrk

            pbr_frag_xm_xmjz_xdmj.setCurrentValues(msg.xmjdzdmj.toFloat())
            tv_frag_xm_xmjz_zdmj.text = msg.zdmj.toString() + "万㎡"
//                pbr_frag_xm_xmjz_xdmj.setUnit(msg.zdmj.toString())
            pbr_frag_xm_xmjz_dsw.setCurrentValues(msg.xnjddsw.toFloat())
            tv_frag_xm_xmjz_dsw.text = msg.zdmj.toString() + "万㎡"
//                pbr_frag_xm_xmjz_dsw.setUnit(msg.dsw.toString())
            pbr_frag_xm_xmjz_dzrk.setCurrentValues(msg.xmjddzrk.toFloat())
            tv_frag_xm_xmjz_dzrk.text = msg.zdmj.toString() + "人"
//                pbr_frag_xm_xmjz_dzrk.setUnit(msg.dzrk.toString())

            //给四季度数据赋值
            if (msg.projectQuarterEntities != null && msg.projectQuarterEntities.size > 0) {
                val list = msg.projectQuarterEntities
                for (i in 0..list.size - 1) {
                    when (list.get(i).quarter) {
                        1 -> {
                            when (list.get(i).xmlx) {//项目立项
                                1 -> {
                                    tv_xm_jsqk_1_xmlx.text = "未开始"
                                    view_xm_jsqk_1_xmlx.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_1_xmlx.text = "已完成"
                                    view_xm_jsqk_1_xmlx.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).epczb) {//epc招标
                                1 -> {
                                    tv_xm_jsqk_1_epc.text = "未开始"
                                    view_xm_jsqk_1_epc.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_1_epc.text = "已完成"
                                    view_xm_jsqk_1_epc.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sxbl) {//手续办理
                                1 -> {
                                    tv_xm_jsqk_1_sxbl.text = "未开始"
                                    view_xm_jsqk_1_sxbl.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_1_sxbl.text = "办理中"
                                    view_xm_jsqk_1_sxbl.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_1_sxbl.text = "已完成"
                                    view_xm_jsqk_1_sxbl.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sgjd) {//施工阶段
                                1 -> {
                                    tv_xm_jsqk_1_sgjd.text = "未开始"
                                    view_xm_jsqk_1_sgjd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_1_sgjd.text = "办理中"
                                    view_xm_jsqk_1_sgjd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_1_sgjd.text = "已完成"
                                    view_xm_jsqk_1_sgjd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).htqd) {//合同签订
                                1 -> {
                                    tv_xm_jsqk_1_htqd.text = "未开始"
                                    view_xm_jsqk_1_htqd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_1_htqd.text = "办理中"
                                    view_xm_jsqk_1_htqd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_1_htqd.text = "已完成"
                                    view_xm_jsqk_1_htqd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            et_xm_jsqk_1_bdcqz.setText(list.get(i).bdcqz)
                            et_xm_jsqk_1_ydghxkz.setText(list.get(i).ydghxkz)
                            et_xm_jsqk_1_ggz.setText(list.get(i).ggz)
                            et_xm_jsqk_1_sgxkz.setText(list.get(i).sgxkz)
                            et_xm_jsqk_1_tfgc.setText(list.get(i).tfgc)
                            et_xm_jsqk_1_htqd.setText(list.get(i).htqd)
                        }
                        2 -> {

                            when (list.get(i).xmlx) {//项目立项
                                1 -> {
                                    tv_xm_jsqk_2_xmlx.text = "未开始"
                                    view_xm_jsqk_2_xmlx.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_2_xmlx.text = "已完成"
                                    view_xm_jsqk_2_xmlx.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).epczb) {//epc招标
                                1 -> {
                                    tv_xm_jsqk_2_epc.text = "未开始"
                                    view_xm_jsqk_2_epc.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_2_epc.text = "已完成"
                                    view_xm_jsqk_2_epc.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sxbl) {//手续办理
                                1 -> {
                                    tv_xm_jsqk_2_sxbl.text = "未开始"
                                    view_xm_jsqk_2_sxbl.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_2_sxbl.text = "办理中"
                                    view_xm_jsqk_2_sxbl.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_2_sxbl.text = "已完成"
                                    view_xm_jsqk_2_sxbl.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sgjd) {//施工阶段
                                1 -> {
                                    tv_xm_jsqk_2_sgjd.text = "未开始"
                                    view_xm_jsqk_2_sgjd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_2_sgjd.text = "办理中"
                                    view_xm_jsqk_2_sgjd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_2_sgjd.text = "已完成"
                                    view_xm_jsqk_2_sgjd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).htqd) {//合同签订
                                1 -> {
                                    tv_xm_jsqk_2_htqd.text = "未开始"
                                    view_xm_jsqk_2_htqd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_2_htqd.text = "办理中"
                                    view_xm_jsqk_2_htqd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_2_htqd.text = "已完成"
                                    view_xm_jsqk_2_htqd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            et_xm_jsqk_2_bdcqz.setText(list.get(i).bdcqz)
                            et_xm_jsqk_2_ydghxkz.setText(list.get(i).ydghxkz)
                            et_xm_jsqk_2_ggz.setText(list.get(i).ggz)
                            et_xm_jsqk_2_sgxkz.setText(list.get(i).sgxkz)
                            et_xm_jsqk_2_tfgc.setText(list.get(i).tfgc)
                            et_xm_jsqk_2_htqd.setText(list.get(i).htqd)
                        }
                        3 -> {

                            when (list.get(i).xmlx) {//项目立项
                                1 -> {
                                    tv_xm_jsqk_3_xmlx.text = "未开始"
                                    view_xm_jsqk_3_xmlx.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_3_xmlx.text = "已完成"
                                    view_xm_jsqk_3_xmlx.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).epczb) {//epc招标
                                1 -> {
                                    tv_xm_jsqk_3_epc.text = "未开始"
                                    view_xm_jsqk_3_epc.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_3_epc.text = "已完成"
                                    view_xm_jsqk_3_epc.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sxbl) {//手续办理
                                1 -> {
                                    tv_xm_jsqk_3_sxbl.text = "未开始"
                                    view_xm_jsqk_3_sxbl.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_3_sxbl.text = "办理中"
                                    view_xm_jsqk_3_sxbl.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_3_sxbl.text = "已完成"
                                    view_xm_jsqk_3_sxbl.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sgjd) {//施工阶段
                                1 -> {
                                    tv_xm_jsqk_3_sgjd.text = "未开始"
                                    view_xm_jsqk_3_sgjd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_3_sgjd.text = "办理中"
                                    view_xm_jsqk_3_sgjd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_3_sgjd.text = "已完成"
                                    view_xm_jsqk_3_sgjd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).htqd) {//合同签订
                                1 -> {
                                    tv_xm_jsqk_3_htqd.text = "未开始"
                                    view_xm_jsqk_3_htqd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_3_htqd.text = "办理中"
                                    view_xm_jsqk_3_htqd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_3_htqd.text = "已完成"
                                    view_xm_jsqk_3_htqd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            et_xm_jsqk_3_bdcqz.setText(list.get(i).bdcqz)
                            et_xm_jsqk_3_ydghxkz.setText(list.get(i).ydghxkz)
                            et_xm_jsqk_3_ggz.setText(list.get(i).ggz)
                            et_xm_jsqk_3_sgxkz.setText(list.get(i).sgxkz)
                            et_xm_jsqk_3_tfgc.setText(list.get(i).tfgc)
                            et_xm_jsqk_3_htqd.setText(list.get(i).htqd)
                        }
                        4 -> {

                            when (list.get(i).xmlx) {//项目立项
                                1 -> {
                                    tv_xm_jsqk_4_xmlx.text = "未开始"
                                    view_xm_jsqk_4_xmlx.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_4_xmlx.text = "已完成"
                                    view_xm_jsqk_4_xmlx.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).epczb) {//epc招标
                                1 -> {
                                    tv_xm_jsqk_4_epc.text = "未开始"
                                    view_xm_jsqk_4_epc.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                3 -> {
                                    tv_xm_jsqk_4_epc.text = "已完成"
                                    view_xm_jsqk_4_epc.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sxbl) {//手续办理
                                1 -> {
                                    tv_xm_jsqk_4_sxbl.text = "未开始"
                                    view_xm_jsqk_4_sxbl.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_4_sxbl.text = "办理中"
                                    view_xm_jsqk_4_sxbl.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_4_sxbl.text = "已完成"
                                    view_xm_jsqk_4_sxbl.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).sgjd) {//施工阶段
                                1 -> {
                                    tv_xm_jsqk_4_sgjd.text = "未开始"
                                    view_xm_jsqk_4_sgjd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_4_sgjd.text = "办理中"
                                    view_xm_jsqk_4_sgjd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_4_sgjd.text = "已完成"
                                    view_xm_jsqk_4_sgjd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            when (list.get(i).htqd) {//合同签订
                                1 -> {
                                    tv_xm_jsqk_4_htqd.text = "未开始"
                                    view_xm_jsqk_4_htqd.setBackgroundResource(R.drawable.shape_ring_wks)
                                }
                                2 -> {
                                    tv_xm_jsqk_4_htqd.text = "办理中"
                                    view_xm_jsqk_4_htqd.setBackgroundResource(R.drawable.shape_ring_blz)
                                }
                                3 -> {
                                    tv_xm_jsqk_4_htqd.text = "已完成"
                                    view_xm_jsqk_4_htqd.setBackgroundResource(R.drawable.shape_ring_ywc)
                                }
                            }
                            et_xm_jsqk_4_bdcqz.setText(list.get(i).bdcqz)
                            et_xm_jsqk_4_ydghxkz.setText(list.get(i).ydghxkz)
                            et_xm_jsqk_4_ggz.setText(list.get(i).ggz)
                            et_xm_jsqk_4_sgxkz.setText(list.get(i).sgxkz)
                            et_xm_jsqk_4_tfgc.setText(list.get(i).tfgc)
                            et_xm_jsqk_4_htqd.setText(list.get(i).htqd)
                        }
                    }
                }
            }


            //修改数据
            //投资方式
            tv_xm_jbqk_tzfs.setOnClickListener {
                initNationPickerView("投资方式")
            }
            //项目进展
            rlt_xm_xmjz.setOnClickListener {
                xmjdPopup()
            }

            //建设情况
            tab_frag_xm_jbqk_jsqk.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {


                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {



                    when (tab!!.text.toString()) {
                        "一季度" -> {
                            frag_xm_jbqk_jsqk_1_include.visibility = View.VISIBLE
                            frag_xm_jbqk_jsqk_2_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_3_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_4_include.visibility = View.GONE
                        }
                        "二季度" -> {
                            frag_xm_jbqk_jsqk_1_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_2_include.visibility = View.VISIBLE
                            frag_xm_jbqk_jsqk_3_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_4_include.visibility = View.GONE
                        }
                        "三季度" -> {
                            frag_xm_jbqk_jsqk_1_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_2_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_3_include.visibility = View.VISIBLE
                            frag_xm_jbqk_jsqk_4_include.visibility = View.GONE
                        }
                        "四季度" -> {
                            frag_xm_jbqk_jsqk_1_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_2_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_3_include.visibility = View.GONE
                            frag_xm_jbqk_jsqk_4_include.visibility = View.VISIBLE
                        }
                    }
                }
            })

            //第一季度
            //项目立项
            ll_xm_jsqk_1_xmlx.setOnClickListener {
                initNationPickerView("项目立项1")
            }
            ll_xm_jsqk_2_xmlx.setOnClickListener {
                initNationPickerView("项目立项2")
            }
            ll_xm_jsqk_3_xmlx.setOnClickListener {
                initNationPickerView("项目立项3")
            }
            ll_xm_jsqk_4_xmlx.setOnClickListener {
                initNationPickerView("项目立项4")
            }
            //EPC招标
            ll_xm_jsqk_1_epc.setOnClickListener {
                initNationPickerView("EPC招标1")
            }
            ll_xm_jsqk_2_epc.setOnClickListener {
                initNationPickerView("EPC招标2")
            }
            ll_xm_jsqk_3_epc.setOnClickListener {
                initNationPickerView("EPC招标3")
            }
            ll_xm_jsqk_4_epc.setOnClickListener {
                initNationPickerView("EPC招标4")
            }
            //手续办理
            ll_xm_jsqk_1_sxbl.setOnClickListener {
                initNationPickerView("手续办理1")
            }
            ll_xm_jsqk_2_sxbl.setOnClickListener {
                initNationPickerView("手续办理2")
            }
            ll_xm_jsqk_3_sxbl.setOnClickListener {
                initNationPickerView("手续办理3")
            }
            ll_xm_jsqk_4_sxbl.setOnClickListener {
                initNationPickerView("手续办理4")
            }
            //施工阶段
            ll_xm_jsqk_1_sgjd.setOnClickListener {
                initNationPickerView("施工阶段1")
            }
            ll_xm_jsqk_2_sgjd.setOnClickListener {
                initNationPickerView("施工阶段2")
            }
            ll_xm_jsqk_3_sgjd.setOnClickListener {
                initNationPickerView("施工阶段3")
            }
            ll_xm_jsqk_4_sgjd.setOnClickListener {
                initNationPickerView("施工阶段4")
            }
            //合同签订
            ll_xm_jsqk_1_htqd.setOnClickListener {
                initNationPickerView("合同签订1")
            }
            ll_xm_jsqk_2_htqd.setOnClickListener {
                initNationPickerView("合同签订2")
            }
            ll_xm_jsqk_3_htqd.setOnClickListener {
                initNationPickerView("合同签订3")
            }
            ll_xm_jsqk_4_htqd.setOnClickListener {
                initNationPickerView("合同签订4")
            }

            //提交数据
            bt_frag_xm_jbqk_save.setOnClickListener {
                val entity = ProjectEntity()
                entity.name = tv_xm_jbqk_xmmc.text.toString()
                entity.xmlb = tv_xm_jbqk_xmlx.text.toString()
                entity.area = tv_xm_jbqk_ydmj.text.toString().toBigDecimal()
                entity.zdmj = tv_xm_jbqk_zdmj.text.toString().toBigDecimal()
                entity.dsw = tv_xm_jbqk_dsw.text.toString().toBigDecimal()
                entity.dzrk = tv_xm_jbqk_dzrk.text.toString().toInt()
                entity.tzzt = tv_xm_jbqk_tzzt.text.toString()
                entity.tzfs = tv_xm_jbqk_tzfs.text.toString()
                entity.zjxq = tv_xm_jbqk_zjxq.text.toString().toBigDecimal()
                entity.xmjdzdmj = xmjdzdmjStr
                entity.xnjddsw = xnjddswStr
                entity.xmjddzrk = xmjddzrkStr
                entity.code = AppCache.getInstance().cunCode
                //第一季度
                val projectQuarterEntity1 = ProjectQuarterEntity()
                projectQuarterEntity1.quarter = 1
                projectQuarterEntity1.year = tv_frag_xm_jsqk_year.text.toString()
                projectQuarterEntity1.xmlx = if (tv_xm_jsqk_1_xmlx.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity1.epczb = if (tv_xm_jsqk_1_epc.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity1.sxbl = if (tv_xm_jsqk_1_sxbl.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_1_sxbl.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity1.sgjd = if (tv_xm_jsqk_1_sgjd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_1_sgjd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity1.htqd = if (tv_xm_jsqk_1_htqd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_1_htqd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity1.bdcqz = et_xm_jsqk_1_bdcqz.text.toString()
                projectQuarterEntity1.ydghxkz = et_xm_jsqk_1_ydghxkz.text.toString()
                projectQuarterEntity1.ggz = et_xm_jsqk_1_ggz.text.toString()
                projectQuarterEntity1.sgxkz = et_xm_jsqk_1_sgxkz.text.toString()
                projectQuarterEntity1.tfgc = et_xm_jsqk_1_tfgc.text.toString()
                projectQuarterEntity1.httext = et_xm_jsqk_1_htqd.text.toString()
                if (tv_xm_jsqk_1_xmlx.text.toString().equals("未开始")) {
                    projectQuarterEntity1.progress = 0
                } else {
                    projectQuarterEntity1.progress = 1
                    if (tv_xm_jsqk_1_epc.text.toString().equals("未开始")) {
                        projectQuarterEntity1.progress = 1
                    } else {
                        projectQuarterEntity1.progress = 2
                        if (tv_xm_jsqk_1_sxbl.text.toString().equals("未开始")) {
                            projectQuarterEntity1.progress = 2
                        } else {
                            projectQuarterEntity1.progress = 3
                            if (tv_xm_jsqk_1_sgjd.text.toString().equals("未开始")) {
                                projectQuarterEntity1.progress = 3
                            } else {
                                projectQuarterEntity1.progress = 4
                                if (tv_xm_jsqk_1_htqd.text.toString().equals("未开始")) {
                                    projectQuarterEntity1.progress = 4
                                } else {
                                    projectQuarterEntity1.progress = 5

                                }
                            }
                        }
                    }
                }
                //第二季度
                val projectQuarterEntity2 = ProjectQuarterEntity()
                projectQuarterEntity2.quarter = 1
                projectQuarterEntity2.year = tv_frag_xm_jsqk_year.text.toString()
                projectQuarterEntity2.xmlx = if (tv_xm_jsqk_2_xmlx.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity2.epczb = if (tv_xm_jsqk_2_epc.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity2.sxbl = if (tv_xm_jsqk_2_sxbl.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_2_sxbl.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity2.sgjd = if (tv_xm_jsqk_2_sgjd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_2_sgjd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity2.htqd = if (tv_xm_jsqk_2_htqd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_2_htqd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity2.bdcqz = et_xm_jsqk_2_bdcqz.text.toString()
                projectQuarterEntity2.ydghxkz = et_xm_jsqk_2_ydghxkz.text.toString()
                projectQuarterEntity2.ggz = et_xm_jsqk_2_ggz.text.toString()
                projectQuarterEntity2.sgxkz = et_xm_jsqk_2_sgxkz.text.toString()
                projectQuarterEntity2.tfgc = et_xm_jsqk_2_tfgc.text.toString()
                projectQuarterEntity2.httext = et_xm_jsqk_2_htqd.text.toString()
                if (tv_xm_jsqk_2_xmlx.text.toString().equals("未开始")) {
                    projectQuarterEntity2.progress = 0
                } else {
                    projectQuarterEntity2.progress = 1
                    if (tv_xm_jsqk_2_epc.text.toString().equals("未开始")) {
                        projectQuarterEntity2.progress = 1
                    } else {
                        projectQuarterEntity2.progress = 2
                        if (tv_xm_jsqk_2_sxbl.text.toString().equals("未开始")) {
                            projectQuarterEntity2.progress = 2
                        } else {
                            projectQuarterEntity2.progress = 3
                            if (tv_xm_jsqk_2_sgjd.text.toString().equals("未开始")) {
                                projectQuarterEntity2.progress = 3
                            } else {
                                projectQuarterEntity2.progress = 4
                                if (tv_xm_jsqk_2_htqd.text.toString().equals("未开始")) {
                                    projectQuarterEntity2.progress = 4
                                } else {
                                    projectQuarterEntity2.progress = 5

                                }
                            }
                        }
                    }
                }
                //第三季度
                val projectQuarterEntity3= ProjectQuarterEntity()
                projectQuarterEntity3.quarter = 1
                projectQuarterEntity3.year = tv_frag_xm_jsqk_year.text.toString()
                projectQuarterEntity3.xmlx = if (tv_xm_jsqk_3_xmlx.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity3.epczb = if (tv_xm_jsqk_3_epc.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity3.sxbl = if (tv_xm_jsqk_3_sxbl.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_3_sxbl.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity3.sgjd = if (tv_xm_jsqk_3_sgjd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_3_sgjd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity3.htqd = if (tv_xm_jsqk_3_htqd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_3_htqd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity3.bdcqz = et_xm_jsqk_3_bdcqz.text.toString()
                projectQuarterEntity3.ydghxkz = et_xm_jsqk_3_ydghxkz.text.toString()
                projectQuarterEntity3.ggz = et_xm_jsqk_3_ggz.text.toString()
                projectQuarterEntity3.sgxkz = et_xm_jsqk_3_sgxkz.text.toString()
                projectQuarterEntity3.tfgc = et_xm_jsqk_3_tfgc.text.toString()
                projectQuarterEntity3.httext = et_xm_jsqk_3_htqd.text.toString()
                if (tv_xm_jsqk_3_xmlx.text.toString().equals("未开始")) {
                    projectQuarterEntity3.progress = 0
                } else {
                    projectQuarterEntity3.progress = 1
                    if (tv_xm_jsqk_3_epc.text.toString().equals("未开始")) {
                        projectQuarterEntity3.progress = 1
                    } else {
                        projectQuarterEntity3.progress = 2
                        if (tv_xm_jsqk_3_sxbl.text.toString().equals("未开始")) {
                            projectQuarterEntity3.progress = 2
                        } else {
                            projectQuarterEntity3.progress = 3
                            if (tv_xm_jsqk_3_sgjd.text.toString().equals("未开始")) {
                                projectQuarterEntity3.progress = 3
                            } else {
                                projectQuarterEntity3.progress = 4
                                if (tv_xm_jsqk_3_htqd.text.toString().equals("未开始")) {
                                    projectQuarterEntity3.progress = 4
                                } else {
                                    projectQuarterEntity3.progress = 5

                                }
                            }
                        }
                    }
                }
                //第四季度
                val projectQuarterEntity4 = ProjectQuarterEntity()
                projectQuarterEntity4.quarter = 1
                projectQuarterEntity4.year = tv_frag_xm_jsqk_year.text.toString()
                projectQuarterEntity4.xmlx = if (tv_xm_jsqk_4_xmlx.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity4.epczb = if (tv_xm_jsqk_4_epc.text.toString().equals("未开始")) 1 else 3
                projectQuarterEntity4.sxbl = if (tv_xm_jsqk_4_sxbl.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_4_sxbl.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity4.sgjd = if (tv_xm_jsqk_4_sgjd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_4_sgjd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity4.htqd = if (tv_xm_jsqk_4_htqd.text.toString().equals("未开始")) 1 else if (tv_xm_jsqk_4_htqd.text.toString().equals("办理中")) 2 else 3
                projectQuarterEntity4.bdcqz = et_xm_jsqk_4_bdcqz.text.toString()
                projectQuarterEntity4.ydghxkz = et_xm_jsqk_4_ydghxkz.text.toString()
                projectQuarterEntity4.ggz = et_xm_jsqk_4_ggz.text.toString()
                projectQuarterEntity4.sgxkz = et_xm_jsqk_4_sgxkz.text.toString()
                projectQuarterEntity4.tfgc = et_xm_jsqk_4_tfgc.text.toString()
                projectQuarterEntity4.httext = et_xm_jsqk_4_htqd.text.toString()
                if (tv_xm_jsqk_4_xmlx.text.toString().equals("未开始")) {
                    projectQuarterEntity4.progress = 0
                } else {
                    projectQuarterEntity4.progress = 1
                    if (tv_xm_jsqk_4_epc.text.toString().equals("未开始")) {
                        projectQuarterEntity4.progress = 1
                    } else {
                        projectQuarterEntity4.progress = 2
                        if (tv_xm_jsqk_4_sxbl.text.toString().equals("未开始")) {
                            projectQuarterEntity4.progress = 2
                        } else {
                            projectQuarterEntity4.progress = 3
                            if (tv_xm_jsqk_4_sgjd.text.toString().equals("未开始")) {
                                projectQuarterEntity4.progress = 3
                            } else {
                                projectQuarterEntity4.progress = 4
                                if (tv_xm_jsqk_4_htqd.text.toString().equals("未开始")) {
                                    projectQuarterEntity4.progress = 4
                                } else {
                                    projectQuarterEntity4.progress = 5

                                }
                            }
                        }
                    }
                }
                when(quarterFlag){
                    1->{
                        entity.projectQuarterEntities.add(projectQuarterEntity1)
                    }
                    2->{
                        entity.projectQuarterEntities.add(projectQuarterEntity2)
                    }
                    3->{
                        entity.projectQuarterEntities.add(projectQuarterEntity3)
                    }
                    4->{
                        entity.projectQuarterEntities.add(projectQuarterEntity4)
                    }
                }
                //调用接口
                mPresenter.getPorjectUpdate(entity)

            }


        }
    }


    var xmjdzdmjStr = BigDecimal(0)
    var xnjddswStr = BigDecimal(0)
    var xmjddzrkStr = BigDecimal(0)
    //项目进展圆环进度修改
    private fun xmjdPopup() {
        addProjectUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_update_project_progress, splt_frag_xm_top)
        val contentView = addProjectUpPopu!!.contentView
        val et_zdmj = contentView.findViewById<EditText>(R.id.pop_update_project_et_zdmj)
        val et_dsw = contentView.findViewById<EditText>(R.id.pop_update_project_et_dsw)
        val et_dzrk = contentView.findViewById<EditText>(R.id.pop_update_project_et_dzrk)
        val confirm = contentView.findViewById<TextView>(R.id.pop_update_project_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_update_project_close)
        CommenPop.backgroundAlpha(0.5f, activity)
        addProjectUpPopu!!.showAtLocation(splt_frag_xm_top, Gravity.CENTER, 0, 0)

        confirm.setOnClickListener {
            if (et_zdmj.text.toString().equals("")) {
                ToastUtils.showShort("请输入征地面积百分比")
            } else if (et_dsw.text.toString().equals("")) {
                ToastUtils.showShort("请输入地上物百分比")
            } else if (et_dzrk.text.toString().equals("")) {
                ToastUtils.showShort("请输入待转人口百分比")
            } else {
                if (et_zdmj.text.toString().toBigDecimal().compareTo(BigDecimal(100)) == 1) {
                    ToastUtils.showShort("征地面积百分比不能大于百分之百")
                } else if (et_dsw.text.toString().toBigDecimal().compareTo(BigDecimal(100)) == 1) {
                    ToastUtils.showShort("地上物百分比不能大于百分之百")
                } else if (et_dzrk.text.toString().toBigDecimal().compareTo(BigDecimal(100)) == 1) {
                    ToastUtils.showShort("待转人口百分比不能大于百分之百")
                } else {
                    ToastUtils.showShort("修改成功")
                    pbr_frag_xm_xmjz_xdmj.setCurrentValues(et_zdmj.text.toString().toFloat())
                    pbr_frag_xm_xmjz_dsw.setCurrentValues(et_dsw.text.toString().toFloat())
                    pbr_frag_xm_xmjz_dzrk.setCurrentValues(et_dzrk.text.toString().toFloat())
                    xmjdzdmjStr = et_zdmj.text.toString().toBigDecimal()
                    xnjddswStr = et_dsw.text.toString().toBigDecimal()
                    xmjddzrkStr = et_dzrk.text.toString().toBigDecimal()
                    addProjectUpPopu!!.dismiss()
                }
            }
        }

        close.setOnClickListener {
            addProjectUpPopu!!.dismiss()
        }

    }

    var xm_jsqk_1_bdcqz = false
    var xm_jsqk_2_bdcqz = false
    var xm_jsqk_3_bdcqz = false
    var xm_jsqk_4_bdcqz = false
    var xm_jsqk_1_ydghxkz = false
    var xm_jsqk_2_ydghxkz = false
    var xm_jsqk_3_ydghxkz = false
    var xm_jsqk_4_ydghxkz = false
    var xm_jsqk_1_ggz = false
    var xm_jsqk_2_ggz = false
    var xm_jsqk_3_ggz = false
    var xm_jsqk_4_ggz = false
    var xm_jsqk_1_sgxkz = false
    var xm_jsqk_2_sgxkz = false
    var xm_jsqk_3_sgxkz = false
    var xm_jsqk_4_sgxkz = false
    var xm_jsqk_1_tfgc = false
    var xm_jsqk_2_tfgc = false
    var xm_jsqk_3_tfgc = false
    var xm_jsqk_4_tfgc = false
    var xm_jsqk_1_htqd = false
    var xm_jsqk_2_htqd = false
    var xm_jsqk_3_htqd = false
    var xm_jsqk_4_htqd = false

    //监听所有信息选项的状态
    private fun listener(view: View): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when (view) {
                    et_xm_jsqk_1_bdcqz -> {//不动产权证1
                        xm_jsqk_1_bdcqz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_2_bdcqz -> {//不动产权证2
                        xm_jsqk_2_bdcqz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_3_bdcqz -> {//不动产权证3
                        xm_jsqk_3_bdcqz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_4_bdcqz -> {//不动产权证4
                        xm_jsqk_4_bdcqz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_1_ydghxkz -> {//用地规划许可证1
                        xm_jsqk_1_ydghxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_2_ydghxkz -> {//用地规划许可证2
                        xm_jsqk_2_ydghxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_3_ydghxkz -> {//用地规划许可证3
                        xm_jsqk_3_ydghxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_4_ydghxkz -> {//用地规划许可证4
                        xm_jsqk_4_ydghxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_1_ggz -> {//工规证1
                        xm_jsqk_1_ggz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_2_ggz -> {//工规证2
                        xm_jsqk_2_ggz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_3_ggz -> {//工规证3
                        xm_jsqk_3_ggz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_4_ggz -> {//工规证4
                        xm_jsqk_4_ggz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_1_sgxkz -> {//施工许可证1
                        xm_jsqk_1_sgxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_2_sgxkz -> {//施工许可证2
                        xm_jsqk_2_sgxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_3_sgxkz -> {//施工许可证3
                        xm_jsqk_3_sgxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_4_sgxkz -> {//施工许可证4
                        xm_jsqk_4_sgxkz = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_1_tfgc -> {//土方工程1
                        xm_jsqk_1_tfgc = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_2_tfgc -> {//土方工程2
                        xm_jsqk_2_tfgc = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_3_tfgc -> {//土方工程3
                        xm_jsqk_3_tfgc = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_4_tfgc -> {//土方工程4
                        xm_jsqk_4_tfgc = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_1_htqd -> {//合同签订1
                        xm_jsqk_1_htqd = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_2_htqd -> {//合同签订2
                        xm_jsqk_2_htqd = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_3_htqd -> {//合同签订3
                        xm_jsqk_3_htqd = s!!.isNotEmpty()
                    }
                    et_xm_jsqk_4_htqd -> {//合同签订4
                        xm_jsqk_4_htqd = s!!.isNotEmpty()
                    }

                }

                if (xm_jsqk_1_bdcqz || xm_jsqk_1_ydghxkz || xm_jsqk_1_ggz || xm_jsqk_1_sgxkz) {
                    tv_xm_jsqk_1_sxbl.text = "办理中"
                } else {
                    tv_xm_jsqk_1_sxbl.text = "未开始"
                }
                if (xm_jsqk_1_tfgc) {
                    tv_xm_jsqk_1_sgjd.text = "办理中"
                } else {
                    tv_xm_jsqk_1_sgjd.text = "未开始"
                }
                if (xm_jsqk_1_htqd) {
                    tv_xm_jsqk_1_htqd.text = "办理中"
                } else {
                    tv_xm_jsqk_1_htqd.text = "未开始"
                }

                if (xm_jsqk_2_bdcqz || xm_jsqk_2_ydghxkz || xm_jsqk_2_ggz || xm_jsqk_2_sgxkz) {
                    tv_xm_jsqk_2_sxbl.text = "办理中"
                } else {
                    tv_xm_jsqk_2_sxbl.text = "未开始"
                }
                if (xm_jsqk_2_tfgc) {
                    tv_xm_jsqk_2_sgjd.text = "办理中"
                } else {
                    tv_xm_jsqk_2_sgjd.text = "未开始"
                }
                if (xm_jsqk_2_htqd) {
                    tv_xm_jsqk_2_htqd.text = "办理中"
                } else {
                    tv_xm_jsqk_2_htqd.text = "未开始"
                }

                if (xm_jsqk_3_bdcqz || xm_jsqk_3_ydghxkz || xm_jsqk_3_ggz || xm_jsqk_3_sgxkz) {
                    tv_xm_jsqk_3_sxbl.text = "办理中"
                } else {
                    tv_xm_jsqk_3_sxbl.text = "未开始"
                }
                if (xm_jsqk_3_tfgc) {
                    tv_xm_jsqk_3_sgjd.text = "办理中"
                } else {
                    tv_xm_jsqk_3_sgjd.text = "未开始"
                }
                if (xm_jsqk_3_htqd) {
                    tv_xm_jsqk_3_htqd.text = "办理中"
                } else {
                    tv_xm_jsqk_3_htqd.text = "未开始"
                }

                if (xm_jsqk_4_bdcqz || xm_jsqk_4_ydghxkz || xm_jsqk_4_ggz || xm_jsqk_4_sgxkz) {
                    tv_xm_jsqk_4_sxbl.text = "办理中"
                } else {
                    tv_xm_jsqk_4_sxbl.text = "未开始"
                }
                if (xm_jsqk_4_tfgc) {
                    tv_xm_jsqk_4_sgjd.text = "办理中"
                } else {
                    tv_xm_jsqk_4_sgjd.text = "未开始"
                }
                if (xm_jsqk_4_htqd) {
                    tv_xm_jsqk_4_htqd.text = "办理中"
                } else {
                    tv_xm_jsqk_4_htqd.text = "未开始"
                }


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }

    //项目修改成功回调
    override fun returnPorjectUpdate(msg: String) {
        ToastUtils.showShort("项目修改成功")
        mPresenter.getPorjectList(AppCache.getInstance().cunCode)
        mPresenter.getPorjectStatistics(AppCache.getInstance().cunCode)
        frag_xm_homepage_include.visibility = View.VISIBLE
        frag_xm_jbqk_slideup_include.visibility = View.GONE
    }

    //首页统计
    override fun returnPorjectStatistics(msg: CountVo) {

        if (msg!=null){
            if (msg.jsqk!=null&&msg.jsqk.size>0){
                val jsqk = msg.jsqk
                for (i in 0..jsqk.size-1){
                    when(jsqk.get(i).name){
                        "总计"->{
                            tv_frag_xm_homepage_jsqk_ydmj.text = jsqk.get(i).area.toString()
                            tv_frag_xm_homepage_jsqk_jzmj.text = jsqk.get(i).jsmj.toString()
                        }
                        "工业用地"->{
                            tv_frag_xm_homepage_jsqk_gyyd.text = jsqk.get(i).area.toString()
                        }
                        "住宅混合公建用地"->{
                            tv_frag_xm_homepage_jsqk_zzhhyd.text = jsqk.get(i).area.toString()
                        }
                        "一类居住用地"->{
                            tv_frag_xm_homepage_jsqk_yljzyd.text = jsqk.get(i).area.toString()
                        }
                        "二类居住用地"->{
                            tv_frag_xm_homepage_jsqk_eljzyd.text = jsqk.get(i).area.toString()
                        }
                        "综合性商业金融服务业用地"->{
                            tv_frag_xm_homepage_jsqk_syjryd.text = jsqk.get(i).area.toString()
                        }
                        "其他类多功能用地"->{
                            tv_frag_xm_homepage_jsqk_qtldgnyd.text = jsqk.get(i).area.toString()
                        }
                        "其他"->{
                            tv_frag_xm_homepage_jsqk_qtyd.text = jsqk.get(i).area.toString()
                        }
                    }
                }

                if (msg.tzqk!=null){
                    val tzqk = msg.tzqk
                    tv_frag_xm_homepage_tzqk_ztze.text = tzqk.object1.toString()
                    tv_frag_xm_homepage_tzqk_zftz.text = tzqk.object2.toString()
                    tv_frag_xm_homepage_tzqk_zfbz.text = tzqk.object3.toString()
                    tv_frag_xm_homepage_tzqk_shtz.text = tzqk.object4.toString()
                }
            }
        }


    }


}
