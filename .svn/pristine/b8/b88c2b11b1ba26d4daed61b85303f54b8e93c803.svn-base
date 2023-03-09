/*
package com.jymj.newhczglxy.zjd.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.*
import com.amap.api.maps2d.model.*
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.newhczglxy.R
import com.jymj.newhczglxy.bean.LayerEntity
import com.jymj.newhczglxy.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.newhczglxy.login.contract.ZjdYztContract
import com.jymj.newhczglxy.login.model.ZjdYztModel
import com.jymj.newhczglxy.login.presenter.ZjdYztPresenter
import com.jymj.newhczglxy.rjhj.bean.yl.YztPointEntity
import com.jymj.newhczglxy.tools.LayerInit
import com.jymj.newhczglxy.util.*
import com.jymj.newhczglxy.widget.TDTTileProvider
import com.jymj.newhczglxy.widget.TextViews
import com.jymj.newhczglxy.zjd.activity.zjdgl.BaseDataActivity
import com.jymj.newhczglxy.zjd.adapter.FragmentAdapter
import com.jymj.newhczglxy.zjd.adapter.FragmentAdapter1
import com.jymj.newhczglxy.zjd.bean.AnalysisEnty
import com.jymj.newhczglxy.zjd.bean.FrameJcxxBean
import com.jymj.newhczglxy.zjd.bean.YeWuFrameBean
import com.jymj.newhczglxy.zjd.bean.yzt.cs.CSEntity
import com.jymj.newhczglxy.zjd.bean.yzt.cs.XzDateEntity
import com.jymj.newhczglxy.zjd.bean.yzt.polygon.PolygonQueryEntity
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.frag_zjd_yzt.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ZjdYztFragment : BaseFragment<ZjdYztPresenter, ZjdYztModel>(), ZjdYztContract.View, AMap.OnMapClickListener, AMapLocationListener, CompoundButton.OnCheckedChangeListener {

    val MODE_NORMAL = 100
    val POINT_CLICK = 201//点查
    val POLYGON_CLICK = 202//框查
    val MODE_YD = 1041//用地
    val MODE_FJ = 1042//翻建
    val MODE_XC = 1043//巡查
    val MODE_CZ = 1044//出租
    private var mode: Int = MODE_YD//MODE_NORMAL  暂时默认用地
    private var click_mode: Int = MODE_NORMAL
    private var pointStr = ""
    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, GetFileUtil.getSDCardPath() + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal)
    var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, GetFileUtil.getSDCardPath() + "BMS/map/tdt/note/")
    var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, GetFileUtil.getSDCardPath() + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, GetFileUtil.getSDCardPath() + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    private val mLatLngs = ArrayList<LatLng>()
    private var mPolygon: Polygon? = null
    private var position = 0
    private val sb = StringBuilder()

    private var aMap: AMap? = null
    private var gaoLiang = 0 //判断高亮显示的范围大小  0 小范围 1 大范围
    private var mMyLocationStyle: MyLocationStyle? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    var mLocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    private var mListener: LocationSource.OnLocationChangedListener? = null;
    var aMapLocation: AMapLocation? = null//定位信息
    var pointtabString = arrayOf("点查信息")//, "业务信息"
    var framepointtabString = arrayOf("框查报告")//可行性研究报告
    private var pointJCXXFragment: PointJCXXFragment? = null
    private var framenewJCXXFragment: FrameJCXXNewFragment? = null
    private var yztListType = 1
    private var mPointPopu: CommenPop? = null
    private var recyPoint: RecyclerView? = null
    private var tvsPoint: TextViews? = null
    private var clRenTitle: ConstraintLayout? = null
    private var pointRadar: RadarChart? = null
    private var mPolygonPopu: CommenPop? = null
    private var cb_man_polygon: CheckBox? = null
    private var cb_woman_polygon: CheckBox? = null
    private var cb_sexOther_polygon: CheckBox? = null
    private var pie1_polygon: PieChart? = null
    private var pie2_polygon: PieChart? = null
    private var pie3_polygon: PieChart? = null
    private var pie4_polygon: PieChart? = null
    private var mSe0List: MutableList<PolygonQueryEntity.MaleListBean>? = null
    private var mSe1List: MutableList<PolygonQueryEntity.MaleListBean>? = null
    private var mSe2List: MutableList<PolygonQueryEntity.MaleListBean>? = null
    private var mTdkfPop: CommenPop? = null
    private var mStTdkf: Switch? = null
    private var mRecyTdkf: RecyclerView? = null
    private var mRgTdkf: RadioGroup? = null
    private var mBtOkTdkf: AppCompatButton? = null
    private var mBtCancelTdkf: AppCompatButton? = null
    private var mTvKxx: TextView? = null
    private var mLlGcl: LinearLayout? = null
    private var mLlDw: LinearLayout? = null
    private var edtBilprice: EditText? = null
    private var edtQuantitle: EditText? = null
    private var spJzmjList: Spinner? = null
    private var edt_czfw: EditText? = null
    private var edt_dlzzyw: EditText? = null
    private var edt_zzl: EditText? = null
    private var edt_zjdzdmj: EditText? = null
    private var mCesuanType = 1
    private var csEntity = CSEntity()
    private var xzDateEntity = XzDateEntity()
    private var mCunList = ArrayList<String>()
    private var mTxCunList = ArrayList<String>()


    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.frag_zjd_yzt
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    @SuppressLint("WrongConstant")
    override fun initViews() {
        MapsInitializer.replaceURL(VEC_URL, "tdt1-vec")//添加天地图
        map_frag_zjd_yzt_zjdgl.onCreate(activity!!.intent.extras)
        pointJCXXFragment = PointJCXXFragment()
        framenewJCXXFragment = FrameJCXXNewFragment()
        initAMap()
        setAMap()
        initTuli()//图层
        location()//初始化定位
        initViewPager()

        initPopuPoint()
        initPopuPolygon()
        //选中点查按钮
        bt_frag_zjd_yzt_point.setOnClickListener {
            if (bt_frag_zjd_yzt_point.isActivated){//未选中
                rl_frag_zjd_yzt_search.visibility = View.VISIBLE
                bt_frag_zjd_yzt_point.isActivated = false
            }else{
                rl_frag_zjd_yzt_search.visibility = View.GONE
                bt_frag_zjd_yzt_point.isActivated = true
                bt_frag_zjd_yzt_kuang.isActivated = false
                click_mode = POINT_CLICK
            }
        }
        //选中框查按钮
        bt_frag_zjd_yzt_kuang.setOnClickListener {
            if (bt_frag_zjd_yzt_kuang.isActivated){//未选中
                rl_frag_zjd_yzt_search.visibility = View.VISIBLE
                bt_frag_zjd_yzt_kuang.isActivated = false
            }else{
                rl_frag_zjd_yzt_search.visibility = View.VISIBLE
                bt_frag_zjd_yzt_point.isActivated = false
                bt_frag_zjd_yzt_kuang.isActivated = true
                click_mode = POLYGON_CLICK
            }
        }

        //搜索按钮
        bt_frag_zjd_yzt_search.setOnClickListener {
            onSearch()
        }
        //清除覆盖物
        bt_frag_zjd_yzt_clear.setOnClickListener {
            clearMap()
        }

    }

    private fun initViewPager() {
        //初始化点查询布局
        for (i in pointtabString) {
            point_map_tab.addTab(point_map_tab.newTab().setText(i))
        }
        var fragmentList = ArrayList<Fragment>()
        fragmentList.add(pointJCXXFragment!!)
        //        fragmentList.add(pointYWXXFragment)

        var myPageAdapter = FragmentAdapter(childFragmentManager, fragmentList, pointtabString)
        point_map_vp.adapter = myPageAdapter
        point_map_vp.offscreenPageLimit = 1
        point_map_tab.setupWithViewPager(point_map_vp)
        point_map_vp.offscreenPageLimit = 0
        dl_frag_zjd_yzt.setDrawerLockMode(DrawerLayout.FOCUS_LEFT)

        //初始化框查询布局
        for (i in framepointtabString) {
            frame_map_tab.addTab(frame_map_tab.newTab().setText(i))
        }
        var fragmentList1 = ArrayList<Fragment>()
        //        fragmentList1.add(frameJCXXFragment)
        fragmentList1.add(framenewJCXXFragment!!)
        //fragmentList1.add(frameYewuFragment)

        var myPageAdapter1 = FragmentAdapter1(childFragmentManager, fragmentList1, framepointtabString)
        frame_map_vp.adapter = myPageAdapter1
        frame_map_vp.offscreenPageLimit = 2
        frame_map_tab.setupWithViewPager(frame_map_vp)
        frame_map_vp.offscreenPageLimit = 0
        frame_toPDF.setOnClickListener {
            framenewJCXXFragment!!.outPDF()
        }
    }
    */
/**
     * 初始化点查询弹出的popWindows
     *//*

    fun initPopuPoint() {
        mPointPopu = CommenPop.getNormalPopu(activity, R.layout.pop_point, dl_frag_zjd_yzt)
        val contentView = mPointPopu?.contentView
        recyPoint = contentView?.findViewById<RecyclerView>(R.id.recy_pop_point)
        tvsPoint = contentView?.findViewById<TextViews>(R.id.tvs_pop_point)
        clRenTitle = contentView?.findViewById<ConstraintLayout>(R.id.cl_pop_point_title)
        pointRadar = contentView?.findViewById<RadarChart>(R.id.pop_point_radar)
        CommenPop.backgroundAlpha(1f, activity)
        recyPoint?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyPoint?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
    */
/**
     * 初始化框选查询弹出框
     *//*

    fun initPopuPolygon() {
        mPolygonPopu = CommenPop.getNormalPopu(activity, R.layout.pop_query_polygon, dl_frag_zjd_yzt)
        val contentViewP = mPolygonPopu?.contentView
        cb_man_polygon = contentViewP?.findViewById<CheckBox>(R.id.cb_man_polygon)
        cb_woman_polygon = contentViewP?.findViewById<CheckBox>(R.id.cb_woman_polygon)
        cb_sexOther_polygon = contentViewP?.findViewById<CheckBox>(R.id.cb_sexOther_polygon)
        pie3_polygon = contentViewP?.findViewById<PieChart>(R.id.pie_work_polygon)
        pie2_polygon = contentViewP?.findViewById<PieChart>(R.id.pie_hu_polygon)
        pie1_polygon = contentViewP?.findViewById<PieChart>(R.id.pie_mj_polygon)
        pie4_polygon = contentViewP?.findViewById<PieChart>(R.id.pie_sex_polygon)
        cb_man_polygon?.setOnCheckedChangeListener(this)
        cb_woman_polygon?.setOnCheckedChangeListener(this)
        cb_sexOther_polygon?.setOnCheckedChangeListener(this)
    }
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        var num1 = 0
        var num2 = 0
        var num3 = 0
        if (cb_man_polygon!!.isChecked) {
            if (mSe1List != null && mSe1List!!.size > 0) {
                num1 += mSe1List!!.get(0).getObject1()
                num2 += mSe1List!!.get(0).getObject2()
                num3 += mSe1List!!.get(0).getObject3()
            }
        }
        if (cb_woman_polygon!!.isChecked) {
            if (mSe2List != null && mSe2List!!.size > 0) {
                num1 += mSe2List!!.get(0).getObject1()
                num2 += mSe2List!!.get(0).getObject2()
                num3 += mSe2List!!.get(0).getObject3()
            }
        }
        if (cb_sexOther_polygon!!.isChecked) {
            if (mSe0List != null && mSe0List!!.size > 0) {
                num1 += mSe0List!!.get(0).getObject1()
                num2 += mSe0List!!.get(0).getObject2()
                num3 += mSe0List!!.get(0).getObject3()
            }
        }
        setSex(num1, num2, num3)
    }
    private fun setSex(num1: Int, num2: Int, num3: Int) {

        val maleDatas = ArrayList<Int>()
        val maleLables = ArrayList<String>()
        maleDatas.add(num1)
        maleLables.add("0-18")
        maleDatas.add(num2)
        maleLables.add("19-65")
        maleDatas.add(num3)
        maleLables.add("65+")

        initPie(pie4_polygon!!, "人口情况", maleDatas, maleLables)
    }

    */
/**
     * @TODO 框选弹出框中统计图数据的添加（还没有看懂）
     *//*

    private fun initPie(pie: PieChart, title: String, datas: ArrayList<Int>, lables: ArrayList<String>) {
        pie.setUsePercentValues(false)//不使用百分比
        pie.description.isEnabled = false//设置描述
        pie.setExtraOffsets(5f, 10f, 5f, 5f)//设置边距

        pie.dragDecelerationFrictionCoef = 0.95f//设置摩擦系数（值越小摩擦系数越大）

        pie.setExtraOffsets(20f, 0f, 20f, 0f)//设置边距

        pie.isDrawHoleEnabled = true//这个方法为true就是环形图，为false就是饼图
        pie.setHoleColor(Color.WHITE)//设置环形中间空白颜色是白色

        pie.setTransparentCircleColor(Color.WHITE)//设置半透明圆环的颜色
        pie.setTransparentCircleAlpha(110)//设置半透明圆环的透明度

        pie.holeRadius = 58f//设置半径为零
        pie.transparentCircleRadius = 61f//设置半透明圆环的半径,看着就有一种立体的感觉

        pie.setDrawCenterText(true)//设置绘制环中文字

        pie.centerText = title//设置环中的文字
        pie.setCenterTextSize(20f)//设置环中文字的大小
        pie.rotationAngle = 0f//设置旋转的角度
        // enable rotation of the chart by touch
        pie.isRotationEnabled = true//是否可以旋转
        pie.isHighlightPerTapEnabled = true//点击是否放大
        pie.setEntryLabelColor(Color.BLACK)
        setData(pie, datas, lables)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP//图例相对于图表纵向的位置
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT//图例相对于图表横向的位置
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false//是否显示图例
    }

    */
/**
     * @TODO 框选弹出框中统计图数据的添加（还没有看完）暂时跳过
     *//*

    private fun setData(pie: PieChart, datas: List<Int>, lables: List<String>) {
        val entries = ArrayList<PieEntry>()

        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f//设置饼块之间的间隔
        dataSet.selectionShift = 5f//设置饼块选中时偏离饼图中心的距离

        // add a lot of colors

        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);


        dataSet.valueLinePart1OffsetPercentage = 80f//数据连接线距图形片内部边界的距离，为百分数
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data

        // undo all highlights
        pie.highlightValues(null)

        pie.invalidate()
    }
    private fun onSearch() {
        if (mLatLngs.size > 2) {
            position = 0
            */
/**
             * 将sb（拼接的所有经纬度）清空
             *//*

            sb.delete(0, sb.length)
            //拼接框选点击的坐标
            sb.append("POLYGON((")
            for (i in mLatLngs.indices) {
                if (i == mLatLngs.size - 1) {
                    sb.append(mLatLngs[i].longitude).append(" ").append(mLatLngs[i].latitude).append(",")
                    sb.append(mLatLngs[0].longitude).append(" ").append(mLatLngs[0].latitude).append("))")
                } else {
                    sb.append(mLatLngs[i].longitude).append(" ").append(mLatLngs[i].latitude).append(",")
                }
            }
            //隐藏搜索和清空按钮
            rl_frag_zjd_yzt_search.visibility = View.GONE
            rl_frag_zjd_yzt_clear.visibility = View.GONE
            map_frame_ll.visibility = View.VISIBLE
    //            home_page_supl.panelHeight = DisplayUtil.dip2px(260f)
            supl_frag_zjd_yzt.panelHeight = DisplayUtil.dip2px(50f)//
            supl_frag_zjd_yzt.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

            mPresenter.getYewuFrame(sb.toString())//业务框查
            mPresenter.getFrameJcxx(sb.toString())

        }
    }

    override fun initDatas() {
        moveCenter()//延迟定位
        
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
    //添加图层
    private fun initTuli() {
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
                "院落" -> {
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
    }

    private fun addOverLayer(layers: List<LayerEntity>) {
        for (l in layers) {
            when (l.name) {
                "天地图" -> if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtn)
                    aMap!!.addTileOverlay(opt_tdtnN)
                } else {
                    val GET_CS = "http://39.106.20.43:8090/geoserver/hgz_dxjc/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=hgz_dxjc:zj2&styles=&viewparams=&srs=EPSG:3857&format=image%2Fpng&bbox="
                    val path_zj = GetFileUtil.getSDCardPath() + "jymj/czgz/map/tz/kb/"
                    var layer_zj = HeritageScopeTileProvider(GET_CS, path_zj)
                    var opt_zj = TileOverlayOptions().tileProvider(layer_zj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_zj).diskCacheSize(10 * 1024)
                    aMap!!.addTileOverlay(opt_zj)
                }
                "影像图" -> if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtSta)
                    aMap!!.addTileOverlay(opt_tdtStaN)
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
    //移动到镇/村中心点
    private fun moveCenter() {
        Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(1500); // 延时1.5秒
                    val point = getCenter(AppCache.getInstance().loginCenter)
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
    */
/**
     * 初始化AMap对象
     *//*

    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_zjd_yzt_zjdgl.getMap()
        }
        setUpMap()
    }
    */
/**
     * 设置一些amap的属性
     *//*

    private fun setUpMap() { // 自定义系统定位蓝点
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
    */
/**
     * 地图方法
     *//*

    private fun setAMap() { //设置默认定位按钮是否显示
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false // 设置比例尺
        aMap!!.uiSettings.isZoomControlsEnabled = false
        aMap!!.setMapLanguage(AMap.CHINESE)
        aMap!!.setOnMapClickListener(this)
        mPolygonOptions = PolygonOptions()
        aMap!!.addTileOverlay(opt_tdtnN)

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

    */
/**
     * 清理所有的Marker
     *//*

    private fun clearAllMarker() {
        val markerList = aMap?.getMapScreenMarkers()
        for (m in markerList!!) {
            m.remove()
        }
    }

    override fun returnCxczqk(msg: List<XzqInfoEntity>) {
        
    }
    override fun onYztList(isSave: List<AnalysisEnty>) {

    }
    */
/**
     *请求现状主要数据页面的数据并跳转到这个页面
     *//*

    override fun onBaseDataCe(baseData: XzDateEntity) {
        */
/* val cePop = CommenPop.getNormalPopu(this, R.layout.pop_basedata, cl_main)
         val contentView = cePop?.contentView
         val tvsBaseData = contentView?.findViewById<TextViews>(R.id.tvsBaseData)
         CommenPop.backgroundAlpha(1f, this)
         cePop?.showAtLocation(cl_main, Gravity.CENTER, 0, 0)

         val tvDatas = ArrayList<TextViewsEntity>()
         tvDatas.add(TextViewsEntity("", "单位：公顷"))
         tvDatas.add(TextViewsEntity("宅基地面积:", baseData.zhaimj.toString()))
         tvDatas.add(TextViewsEntity("住宅建筑面积:", baseData.zhaijzmj.toString()))
         tvDatas.add(TextViewsEntity("宅基地个数:", baseData.zhaicount.toString()))
         tvDatas.add(TextViewsEntity("非宅占地面积:", baseData.feimj.toString()))
         tvDatas.add(TextViewsEntity("非宅建筑面积:", baseData.feijzmj.toString()))
         tvDatas.add(TextViewsEntity("回迁安置（宅基地建筑面积1：1）:", baseData.zhaijzmj.toString()))
         tvDatas.add(TextViewsEntity("配套设施:", baseData.ptss.toString()))
         tvDatas.add(TextViewsEntity("规划用地面积:", baseData.ghydmj.toString()))
         tvDatas.add(TextViewsEntity("框选范围面积:", baseData.kxmj.toString()))
         tvDatas.add(TextViewsEntity("组团范围内耕地面积:", baseData.ztgdmj.toString()))
         tvDatas.add(TextViewsEntity("组团范围内人口:", baseData.ztrk.toString() + "人"))

         tvsBaseData?.setDatas(tvDatas)
         tvsBaseData?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))*//*

        this.xzDateEntity = baseData
        val intent = Intent(context, BaseDataActivity::class.java)
        intent.putExtra("data", baseData as Serializable)
        intent.putExtra("cs", csEntity as Serializable)
        if (csEntity.getmCList1() != null) {
            startActivity(intent)
        }
    }
    */
/**
     * 点击测后点击确定返回的开发类型的数据回调
     *//*

    override fun onCS(csEntity: CSEntity) {
        this.csEntity = csEntity
        //现状况主要数据
        // mPresenter.getBaseDataCe(sb.toString(), mCodeList.toString().substring(1, mCodeList.toString().length - 1), edtQuantitle?.text.toString(), edtBilprice?.text.toString(), spJzmjList!!.selectedItem.toString(), edt_czfw?.text.toString(), edt_dlzzyw?.text.toString(), edt_zzl?.text.toString(), edt_zjdzdmj?.text.toString())
        val intent = Intent(context, BaseDataActivity::class.java)
        intent.putExtra("data", csEntity.xzDateEntity as Serializable)
        intent.putExtra("cs", csEntity as Serializable)
        if (csEntity.getmCList1() != null) {
            startActivity(intent)
        }

    }

    override fun onYztPoint(yztPointEntity: YztPointEntity) {
        clearMap()
//        aMap!!.

        gaoLiang = 0
        pointJCXXFragment!!.fwData(yztPointEntity)
        pointJCXXFragment!!.txData(yztPointEntity)
        pointJCXXFragment!!.setData(yztPointEntity)
        supl_frag_zjd_yzt.panelHeight = DisplayUtil.dip2px(50f)//
        supl_frag_zjd_yzt.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        if (yztPointEntity.ylList.size > 0) {
            val dataGeometry = yztPointEntity.ylList.get(0).geometry
            aMap!!.clear()
            addOverLayer(mLayers)
            addOverLayer(mLayers1)
            kuangGeoment(dataGeometry)
        }
        if (yztPointEntity.yztHzEntities.size > 0) {
            aMap!!.clear()
            addOverLayer(mLayers)
            addOverLayer(mLayers1)
            for (i in yztPointEntity.yztHzEntities) {
                kuangGeoment(i.geometry)
            }
        }
    }

    override fun onYewuFrame(entity: YeWuFrameBean.DataBean) {
        framenewJCXXFragment!!.getData(entity, sb.toString())
    }

    override fun onFrameJcxx(isSave: FrameJcxxBean.DataBean) {
        framenewJCXXFragment!!.frameData(isSave)
    }
    //高亮显示
    private fun kuangGeoment(dataGeometry: String) {
        if (!dataGeometry.equals("")) {

//            clear
            var substring = dataGeometry!!.substring(0, dataGeometry!!.length - 5)
            val replac = substring.replace("ZM", "")
            val replace = replac.replace("MULTIPOLYGON(((", "")
            val replace1 = replace.replace("POLYGON((", "")
            */
/*val replace = substring.replace("(", "")
                val replace1 = replace.replace(")", "")*//*

            var split = replace1.split("),(")
//            replace1.split(")),((")
            */
/*if (split.size < 2) {
                split = replace1.split("),(")
            }*//*

            var s = 0
            for (i in 0..split.size - 1) {
                mPolygonOptions = PolygonOptions()
                val latList = getLatList(split[i])
                if (s == 0) {
                    if (gaoLiang == 0) {
                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                    } else if (gaoLiang == 2) {
                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 13f, 0f, 0f)))
                    } else {
                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 14f, 0f, 0f)))
                    }

                }

                if (mPolygon != null)
//                            mPolygon!!.remove()
                    mPolygonOptions!!.getPoints().clear()
                mPolygonOptions!!.addAll(latList)
                if (gaoLiang != 2) {
                    if (i == 0) {
                        mPolygonOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(50, 64, 64, 255)).visible(true) // 多边形的填充色
                    } else {
                        mPolygonOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(0, 0, 0, 0)).visible(true) // 多边形的填充色
                    }
                } else {
                    if (i == 0) {
                        mPolygonOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(0, 64, 64, 255)).visible(true) // 多边形的填充色
                    } else {
                        mPolygonOptions!!.strokeWidth(10f) // 多边形的边框
                                .strokeColor(Color.argb(255, 0, 0, 255)) // 边框颜色
                                .fillColor(Color.argb(0, 0, 0, 0)).visible(true) // 多边形的填充色
                    }
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
                    */
/* var converter = CoordinateConverter()
                     // CoordType.GPS 待转换坐标类型
                     converter.from(CoordinateConverter.CoordType.GPS)
                     var sl = LatLng(split1[1].toDouble(), split1[0].toDouble())
                     // sourceLatLng待转换坐标点 LatLng类型
                     converter.coord(sl)
                     var latLng = converter.convert()*//*

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
    //地图点击监听
    override fun onMapClick(p0: LatLng?) {

        when (click_mode) {
            POINT_CLICK -> {//点查询模式下地图监听
                queryByPoint(p0!!)
            }
            POLYGON_CLICK -> {//框选模式下地图监听
                queryByPolygon(p0!!)
            }
        }
    }

    */
/**
     * 展示框选查询的框
     *//*

    private fun queryByPolygon(latLng: LatLng) {
        mLatLngs.add(latLng)
        if (mPolygon != null)
            mPolygon!!.remove()
        mPolygonOptions?.points?.clear()
        mPolygonOptions?.addAll(mLatLngs)
        mPolygonOptions?.strokeWidth(5f) // 多边形的边框
                ?.strokeColor(Color.argb(255, 255, 1, 1)) // 边框颜色
                ?.fillColor(Color.argb(50, 177, 152, 229))   // 多边形的填充色
        //点数小于等于1显示marker，否则清除所有的marker
        if (mPolygonOptions?.points?.size!! <= 1) {
            aMap?.addMarker(MarkerOptions().position(latLng))
        } else {
            clearAllMarker()
        }
        mPolygon = aMap?.addPolygon(mPolygonOptions)
    }

    */
/**
     * 显示marker到指定的位置并查询选择地点的信息
     *//*

    private fun queryByPoint(latLng: LatLng) {
        clearAllMarker()
        aMap?.addMarker(MarkerOptions().position(latLng))
        pointStr = "POINT(" + latLng.longitude + " " + latLng.latitude + ")"
        mPresenter.getYztPoint(pointStr)


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

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

}*/
