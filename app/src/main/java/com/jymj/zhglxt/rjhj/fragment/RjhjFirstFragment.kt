package com.jymj.zhglxt.rjhj.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.mpandroidchart.LineChartEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.contract.RjhjFirstContract
import com.jymj.zhglxt.login.model.RjhjFirstModel
import com.jymj.zhglxt.login.presenter.RjhjFirstPresenter
import com.jymj.zhglxt.rjhj.activity.RjhjZglpmActivity
import com.jymj.zhglxt.rjhj.bean.BaobiaoBean
import com.jymj.zhglxt.rjhj.bean.TownCountEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.SwitchTimeEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.TownTypeEntity
import com.jymj.zhglxt.rjhj.bean.zhenfirst.WtlxfbZztBean
import com.jymj.zhglxt.ui.activity.MainActivity
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.widget.mpandroidchart.NewMarkerView
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.FragmentUtils
import com.setsuna.common.commonutils.StringUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_rjhj_first.*
import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class RjhjFirstFragment : BaseFragment<RjhjFirstPresenter, RjhjFirstModel>(), RjhjFirstContract.View, AMap.OnMapClickListener {

    private var mFormat: DecimalFormat = DecimalFormat("#,###.##")
    private var mPolygonOptions: PolygonOptions? = null
    private var position = 1//0
    private var aMap: AMap? = null
    private var mMyLocationStyle: MyLocationStyle? = null
    private var mPolygon: Polygon? = null
    private var pointStr = ""
    private var point: LatLng? = null
    private var type = 1//(1 一个月   2 三个月   3 一年)
    var townCountEntityList = ArrayList<TownCountEntity>()

    override fun lazyLoad() {

    }

    override fun getLayoutResource(): Int {
        return R.layout.frag_rjhj_first
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }
    val sdCardPath = AppMenus.getInstance().cardPath
    override fun initViews() {

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_frag_rjhj_first_zhen.onCreate(activity!!.intent.extras)
        initAMap()
        setAMap()

        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        ll_frag_rjhj_first_jyy.setOnClickListener {//近一月
            view_frag_rjhj_first_jyy.visibility = View.VISIBLE
            view_frag_rjhj_first_jyj.visibility = View.GONE
            view_frag_rjhj_first_jyn.visibility = View.GONE
            type = 1
            mPresenter.getRjhjjcMap(AppCache.getInstance().code,type)
            mPresenter.getTownType(AppCache.getInstance().code,type)
        }
        ll_frag_rjhj_first_jyj.setOnClickListener {//近一季
            view_frag_rjhj_first_jyy.visibility = View.GONE
            view_frag_rjhj_first_jyj.visibility = View.VISIBLE
            view_frag_rjhj_first_jyn.visibility = View.GONE
            type = 2
            mPresenter.getRjhjjcMap(AppCache.getInstance().code,type)
            mPresenter.getTownType(AppCache.getInstance().code,type)
        }
        ll_frag_rjhj_first_jyn.setOnClickListener {//近一年
            view_frag_rjhj_first_jyy.visibility = View.GONE
            view_frag_rjhj_first_jyj.visibility = View.GONE
            view_frag_rjhj_first_jyn.visibility = View.VISIBLE
            type = 3
            mPresenter.getRjhjjcMap(AppCache.getInstance().code,type)
            mPresenter.getTownType(AppCache.getInstance().code,type)
        }


    }

    override fun initDatas() {
        /*mPresenter.getQueryImproveRate(AppCache.getInstance().code)
        mPresenter.getRjhjjcMap(AppCache.getInstance().code,type)
        mPresenter.getTownType(AppCache.getInstance().code,type)*/

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){
            //TODO 不可见操作
        } else {
            //TODO 可见操作
        }
    }
    override fun returnDcylxx(msg: YlFolwEntity) {

    }

    override fun returnQueryImproveRate(cash: SwitchTimeEntity) {
        val year = cash.year
        val season = cash.season
        val month = cash.month

        if (lcivp_frag_rjhj_first_jyn!=null&&year!=null){
            tv_frag_rjhj_first_jynzs.setText(year.total.toString())
            tv_frag_rjhj_first_jynzgl.setText(year.rate.multiply(BigDecimal(100))
                    .setScale(0,RoundingMode.HALF_UP).stripTrailingZeros().toPlainString())
            var values1 = ArrayList<Entry>()
            var values2 = ArrayList<Entry>()
            val homeEntities = year.list
            val size = homeEntities.size - 1
            for (i in size downTo 0) {//homeEntities.size-1 downTo 0   //集合倒序遍历
                val yoyListEntity = homeEntities.get(i)
                val zthj = yoyListEntity!!.total
                val gcwt = yoyListEntity!!.done
                if (zthj != null) {
                    var f = 0f
                    var g = 0f
                    try {
                        f = java.lang.Float.parseFloat(zthj.toString())
                        g = java.lang.Float.parseFloat(gcwt.toString())
                    } catch (e: Exception) {
                        e.printStackTrace()
                        f = 0f
                        g = 0f
                    }

                    val entry1 = Entry((size - i + 1).toFloat(), f)
                    val entry2 = Entry((size - i + 1).toFloat(), g)
                    values1.add(entry1)
                    values2.add(entry2)
                }
            }

            val labels = arrayOf("整改", "未整改")
            val drawables = arrayOf<Drawable>(ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_zi)!!, ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_tianlan)!!
                    , ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_cheng)!!, ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_red)!!
                    , ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_green)!!)
            val callDurationColors = intArrayOf(Color.parseColor("#64B0ff"), Color.parseColor("#FF935A"))
            var homeEntitys = ArrayList<SwitchTimeEntity.ListBean>()
            for (i in homeEntities.size - 1 downTo 0) {
                homeEntitys.add(homeEntities.get(i))
            }
            if (lcivp_frag_rjhj_first_jyn != null){
                updateLinehart1(homeEntitys, lcivp_frag_rjhj_first_jyn, callDurationColors, drawables, "", values1, values2, labels)
            }
        }
        if (lcivp_frag_rjhj_first_zhen_jyj!=null&&season!=null){

            tv_frag_rjhj_first_jyjzs.setText(season.total.toString())
            tv_frag_rjhj_first_jyjzgl.setText(season.rate.multiply(BigDecimal(100))
                    .setScale(0,RoundingMode.HALF_UP).stripTrailingZeros().toPlainString())
            var values1 = ArrayList<Entry>()
            var values2 = ArrayList<Entry>()
            val homeEntities = season.list
            val size = homeEntities.size - 1
            for (i in size downTo 0) {//homeEntities.size-1 downTo 0   //集合倒序遍历
                val yoyListEntity = homeEntities.get(i)
                val zthj = yoyListEntity!!.total
                val gcwt = yoyListEntity!!.done
                if (zthj != null) {
                    var f = 0f
                    var g = 0f
                    try {
                        f = java.lang.Float.parseFloat(zthj.toString())
                        g = java.lang.Float.parseFloat(gcwt.toString())
                    } catch (e: Exception) {
                        e.printStackTrace()
                        f = 0f
                        g = 0f
                    }

                    val entry1 = Entry((size - i + 1).toFloat(), f)
                    val entry2 = Entry((size - i + 1).toFloat(), g)
                    values1.add(entry1)
                    values2.add(entry2)
                }
            }

            val labels = arrayOf("整改", "未整改")
            val drawables = arrayOf<Drawable>(ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_zi)!!, ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_tianlan)!!
                    , ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_cheng)!!, ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_red)!!
                    , ContextCompat.getDrawable(activity!!, R.drawable.chart_thisyear_green)!!)
            val callDurationColors = intArrayOf(Color.parseColor("#64B0ff"), Color.parseColor("#FF935A"))
            var homeEntitys = ArrayList<SwitchTimeEntity.ListBean>()
            for (i in homeEntities.size - 1 downTo 0) {
                homeEntitys.add(homeEntities.get(i))
            }
            if (lcivp_frag_rjhj_first_zhen_jyj != null){
                updateLinehart(homeEntitys, lcivp_frag_rjhj_first_zhen_jyj, callDurationColors, drawables, "", values1, values2, labels)
            }
        }
        if (pie_frag_rjhj_first_jyy!=null&&month!=null){
            tv_frag_rjhj_first_jyyzs.setText(month.total.toString())
            tv_frag_rjhj_first_jyyzgl.setText(month.rate.multiply(BigDecimal(100))
                    .setScale(0,RoundingMode.HALF_UP).stripTrailingZeros().toPlainString())
            val list = month.list
            if (list.size>0){
                val bhgsCount = ArrayList<Float>()
                val bhgsName = ArrayList<String>()
                bhgsCount.add(list.get(0).total.toFloat())
                bhgsCount.add(list.get(0).done.toFloat())
                bhgsName.add("")//已处理
                bhgsName.add("")//未处理
                if (pie_frag_rjhj_first_jyy != null) {
                    rkinitPie(pie_frag_rjhj_first_jyy, " ", bhgsCount, bhgsName)
                }

            }

        }
    }


    override fun returnByPoint(cash: TownCountEntity) {
        addPolygonMarker(point!!, cash)
    }

    override fun returnTownType(cash: TownTypeEntity) {

        if (bar_frag_rjhj_first_wtlxfb != null){
            var homeEntitys = ArrayList<WtlxfbZztBean>()
            homeEntitys.add(WtlxfbZztBean("垃圾",cash.ljwt,cash.ljwtzgs,cash.ljwtzgl,
                    cash.optimalljwt,cash.optimalljwtzgl,cash.differenceljwt,cash.differenceljwtzgl))
            homeEntitys.add(WtlxfbZztBean("污水",cash.wswt,cash.wswtzgs,cash.wswtzgl,
                    cash.optimalwswt,cash.optimalwswtzgl,cash.differencewswt,cash.differencewswtzgl))
            homeEntitys.add(WtlxfbZztBean("公厕",cash.gcwt,cash.gcwtzgs,cash.gcwtzgl,
                    cash.optimalgcwt,cash.optimalgcwtzgl,cash.differencegcwt,cash.differencegcwtzgl))
            homeEntitys.add(WtlxfbZztBean("村容",cash.crwt,cash.crwtzgs,cash.crwtzgl,
                    cash.optimalcrwt,cash.optimalcrwtzgl,cash.differencecrwt,cash.differencecrwtzgl))
//            bar_frag_rjhj_first_wtlxfb.setOnChartValueSelectedListener(this);
            bar_frag_rjhj_first_wtlxfb.getDescription().setEnabled(false)
            bar_frag_rjhj_first_wtlxfb.setMaxVisibleValueCount(40)
            // 扩展现在只能分别在x轴和y轴
            bar_frag_rjhj_first_wtlxfb.setPinchZoom(false)
            bar_frag_rjhj_first_wtlxfb.setDrawGridBackground(false)
            bar_frag_rjhj_first_wtlxfb.setDrawBarShadow(false)
            bar_frag_rjhj_first_wtlxfb.setDrawValueAboveBar(false)
            bar_frag_rjhj_first_wtlxfb.setHighlightFullBarEnabled(false)
            bar_frag_rjhj_first_wtlxfb.setScaleEnabled(false)
            bar_frag_rjhj_first_wtlxfb.setDoubleTapToZoomEnabled(false)

            // 改变y标签的位置
            var leftAxis = bar_frag_rjhj_first_wtlxfb.getAxisLeft()
            leftAxis.setValueFormatter(object:ValueFormatter(){})
            leftAxis.setAxisMinimum(0f);
            bar_frag_rjhj_first_wtlxfb.getAxisRight().setEnabled(false)
            var xLabels = bar_frag_rjhj_first_wtlxfb.getXAxis()
            xLabels.setPosition(XAxis.XAxisPosition.BOTTOM)
            xLabels.setGranularity(1f)
            xLabels.setLabelCount(homeEntitys.size)
            xLabels.setLabelRotationAngle(45f)
            xLabels.valueFormatter = object: ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()

                    return if (index < 0 || index >= homeEntitys.size) {
                        ""
                    } else {
                        homeEntitys.get(index).name
                    }
//                    return super.getFormattedValue(value)
                }
            }
            val mMatrix = Matrix()
            mMatrix.postScale(0.5f, 1f)
            bar_frag_rjhj_first_wtlxfb.viewPortHandler.refresh(mMatrix, bar_frag_rjhj_first_wtlxfb, false)


            var l = bar_frag_rjhj_first_wtlxfb.getLegend()
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
            l.setDrawInside(false)
            l.setFormSize(8f)
            l.setFormToTextSpace(4f)
            l.setXEntrySpace(6f)
            val legend = bar_frag_rjhj_first_wtlxfb.legend
            legend.isWordWrapEnabled = true
            legend.isEnabled = false
            legend.setForm(Legend.LegendForm.NONE)
            val arrayList = ArrayList<ArrayList<Float>>()
            for (i in 0..homeEntitys.size-1){//for (i in homeEntities.size-1 downTo 0){
                val yoyListEntity = homeEntitys.get(i)
                val arrayList1 = ArrayList<Float>()
                val zthj = yoyListEntity!!.wts
                arrayList1.add(zthj.toFloat())
                arrayList.add(arrayList1)
            }
            setData(arrayList,homeEntitys)//homeEntities
        }
    }

    override fun returnRjhjjcMap(cash: List<TownCountEntity>) {
        townCountEntityList.clear()
        townCountEntityList.addAll(cash)
        aMap!!.clear()
        if (cash.size>0){
            val center: LatLng = getCenter(cash.get(cash.size/2).center)//POINT(116.71524809952827 39.81719970703125)
            aMap!!.addGroundOverlay(GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.bstc)).position(center, 1000000f, 1000000f)//设置ground覆盖物的锚点比例，默认为0.5f，水平和垂直方向都居中对齐
                    .zIndex(0f))
            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center, 12f, 0f, 0f)))
        }

        position = 0
        tv_act_fist_dq_zhen?.setTextColor(Color.WHITE)
        tv_act_fist_qb_zhen?.setTextColor(Color.BLACK)
        tv_act_fist_dq_zhen.setBackgroundResource(R.drawable.blue_button_background)
        tv_act_fist_qb_zhen.setBackgroundColor(Color.parseColor("#00ffffff"))
        for (i in townCountEntityList){
            kuangGeoment(i)
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
        /*var townCountEntitys = ArrayList<TownCountEntity>()
        for (i in 0..cash.size-1){
            if (i<8){
                townCountEntitys.add(cash.get(i))
            }
        }*/
        rlv_frag_rjhj_first_zhen_pm?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rlv_frag_rjhj_first_zhen_pm?.adapter = object : BaseQuickAdapter<TownCountEntity, BaseViewHolder>(R.layout.item_firstpage_zgl_list, cash) {
            override fun convert(helper: BaseViewHolder?, item: TownCountEntity?) {
                var zgl = BigDecimal.ZERO
                helper!!.setText(R.id.firstpage_item_zgl_pm, (helper.adapterPosition + 1).toString())
                        .setText(R.id.firstpage_item_zgl_zm, item!!.xzqmc)
                        .setText(R.id.firstpage_item_zgl_fs, item!!.total.toString())
                        .setText(R.id.firstpage_item_zgl_cl, item!!.yzg.toString())//wts  clwtsfirstpage_item_pm_fens
                        .setText(R.id.firstpage_item_zgl_fens, item!!.zgl.stripTrailingZeros().toPlainString()+"%")//item!!.monaverage.stripTrailingZeros().toPlainString()
               helper.itemView.setOnClickListener {//跳转问题列表页
                   AppCache.getInstance().rjhjCode = item.code
                   AppCache.getInstance().rjhjCenter = item.center
                   FragmentUtils.replaceFragment(activity!!.supportFragmentManager, RjhjFragment(),R.id.fl_content,false)
               }
            }
        }
        tv_frag_rjhj_first_ckgdzgl.setOnClickListener {
            val intent = Intent(activity, RjhjZglpmActivity::class.java)
            intent.putExtra("townCountEntity",cash as Serializable)
            startActivity(intent)
        }
    }

    //初始化
    private fun setData(stringList:ArrayList<ArrayList<Float>>,homeEntityList:List<WtlxfbZztBean>) {
        var yVals1 = ArrayList<BarEntry>()
        for (i in 0..stringList.size-1){
            val get = stringList.get(i)
            yVals1.add( BarEntry(i.toFloat(), floatArrayOf(get.get(0))))
        }

        var set1: BarDataSet? =null;

        if (bar_frag_rjhj_first_wtlxfb.getData() != null &&
                bar_frag_rjhj_first_wtlxfb.getData().getDataSetCount() > 0) {
            set1 =  bar_frag_rjhj_first_wtlxfb.getData().getDataSetByIndex(0) as BarDataSet
            set1.setValues(yVals1)
            bar_frag_rjhj_first_wtlxfb.getData().notifyDataChanged()
            bar_frag_rjhj_first_wtlxfb.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")//三年级一班期末考试
            set1.setColors(getColors()!!.toList())
            set1.setStackLabels( arrayOf("整体环境", "公厕问题", "污水问题", "垃圾问题", "村容问题"))

            var dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            var data = BarData(dataSets);//MyValueFormatter
            data.setValueFormatter(object:ValueFormatter(){})
            data.setValueTextColor(Color.WHITE)

            bar_frag_rjhj_first_wtlxfb.setData(data)
        }


        val markerView = NewMarkerView(activity, R.layout.custom_marker_view_layout)
        markerView.setCallBack(NewMarkerView.CallBack { x, value ->
            val index = x.toInt()
            if (index < 0) {
                return@CallBack
            }
            if (index > stringList.size) {
                return@CallBack
            }
            var textTemp = ""

            if (index <= stringList.size) {
                if (!StringUtils.isEmpty(textTemp)) {
                }
                val get = homeEntityList.get(index)
                val get1 = stringList.get(index)
                textTemp = get.name//+"(${(get1.get(0)+get1.get(1)+get1.get(2)+get1.get(3)+get1.get(4)).toInt()})"
                textTemp += "\n"
                textTemp += "总数量:" + get.wts+"个"
                textTemp += "\n"
                textTemp += "整改率:" + get.zgl+"%"
                textTemp += "\n"
                textTemp += "最优村:" + get.zyc+" "+get.zyczgl+"%"
                textTemp += "\n"
                textTemp += "最差村:" + get.zcc+" "+get.zcczgl+"%"
            }
            markerView.tvContent.text = textTemp
        })
        markerView.setChartView(bar_frag_rjhj_first_wtlxfb)
        bar_frag_rjhj_first_wtlxfb.setMarker(markerView)
        bar_frag_rjhj_first_wtlxfb.data.setDrawValues(false)

        bar_frag_rjhj_first_wtlxfb.setFitBars(true);
        bar_frag_rjhj_first_wtlxfb.invalidate();
    }

    private fun getColors(): IntArray? {
        val stacksize = 4
        //有尽可能多的颜色每项堆栈值
        val colors = IntArray(stacksize)
        colors[0] = Color.parseColor("#FFCB80")
        colors[1] = Color.parseColor("#9EFEFA")
        colors[2] = Color.parseColor("#9DFFBD")
        colors[3] = Color.parseColor("#C5A3FF")
        /*for (i in colors.indices) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS.get(i)//MATERIAL_COLORS
        }*/
        return colors
    }





    // 添加多边形的边界点marker
    private fun addPolygonMarker(latlng: LatLng, rjhjPointBean: TownCountEntity) {
        clearAllMarker()
        //小猫 自定义 Marker
        var viewCat = LayoutInflater.from(activity).inflate(R.layout.pop_first_map1, null)
        var tv_marker: LinearLayout = viewCat.findViewById(R.id.tv_pop_first_map1)
        var tv_pop_first_map1_name: TextView = viewCat.findViewById(R.id.tv_pop_first_map1_name)
        var tv_pop_first_map1_all: TextView = viewCat.findViewById(R.id.tv_pop_first_map1_all)
        var tv_pop_first_map1_yz: TextView = viewCat.findViewById(R.id.tv_pop_first_map1_yz)
        var tv_pop_first_map1_zb: TextView = viewCat.findViewById(R.id.tv_pop_first_map1_zb)
        val zhen = rjhjPointBean.xzqmc
        //把实体里面的Id单独抽取出来
        var dataBean = TownCountEntity()
        for (i in townCountEntityList) {
            if (i.xzqmc.equals(zhen)) {
                dataBean = i
            }
        }
        tv_pop_first_map1_name.setText("${zhen}")
        tv_pop_first_map1_all.setText("全部 ${dataBean!!.total}")
        tv_pop_first_map1_yz.setText("严重 ${dataBean!!.zdwt}")
        tv_pop_first_map1_zb.setText("${dataBean!!.zgl.stripTrailingZeros().toPlainString()}%")
        tv_marker?.setOnClickListener {
            clearAllMarker()
        }
        var markerOption = MarkerOptions().position(latlng)
                .draggable(false)
                .icon(BitmapDescriptorFactory.fromView(viewCat))
        //将数据添加到地图上
        var marker = aMap!!.addMarker(markerOption)
        aMap!!.setOnMarkerClickListener(object : AMap.OnMarkerClickListener {
            override fun onMarkerClick(p0: Marker?): Boolean {
                clearAllMarker()
                return false
            }
        })
    }

    private fun clearAllMarker() {
        val markerList = aMap?.getMapScreenMarkers()
        for (m in markerList!!) {
            m.remove()
        }
    }

    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_rjhj_first_zhen.getMap()
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
        aMap!!.uiSettings.isMyLocationButtonEnabled = false // 设置默认定位按钮是否显示
        aMap!!.uiSettings.isZoomGesturesEnabled = true // 禁止缩放
        aMap!!.uiSettings.isScrollGesturesEnabled = true // 禁止移动
        aMap!!.isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mMyLocationStyle!!.showMyLocation(true)
        val child = map_frag_rjhj_first_zhen.getChildAt(0) as ViewGroup //地图框架  隐藏logo
        child.getChildAt(2).visibility = View.GONE //隐藏logo
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
        val center: LatLng = getCenter(AppCache.getInstance().loginCenter)//
//        getXzqList("", 2)
        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center, 12f, 0f, 0f)))
    }

    fun getCenter(center: String): LatLng {
        if (center != null) {
            if (!center.equals("")) {
                val points = center.substring(6, center.length - 1).split(" ").toTypedArray()
                return if (points != null && points.size > 1) {//POINT(116.714621976342 39.6720138368058)
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
    //高亮显示
    private fun kuangGeoment(dataGeometry: BaobiaoBean) {//TownCountEntity

        if (!dataGeometry.equals("")) {
            var zs = 0;
//            clear
            var substring = dataGeometry.code!!.substring(0, dataGeometry.code!!.length - 5)
            val replac = substring.replace("ZM", "")
            val replace = replac.replace("MULTIPOLYGON(((", "")
            val replace1 = replace.replace("POLYGON((", "")
            var split = replace1.split("),(")
            var s = 0
            for (i in 0..split.size - 1) {
                mPolygonOptions = PolygonOptions()
                val latList = getLatList(split[i])
                mPolygonOptions!!.getPoints().clear()
                mPolygonOptions!!.addAll(latList)

                if (position == 0) {
                    zs = dataGeometry.wcl
                } else {
                    zs = dataGeometry.zj
                }

                if (!dataGeometry.isShow) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 154, 154, 154)).visible(true)
                } else if (zs == 0) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 255, 255, 255)).visible(true) // 多边形的填充色  , 43, 43, 43
                } else if (zs < 5) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 255, 225, 219)).visible(true) // 多边形的填充色
                } else if (zs < 15) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 255, 196, 179)).visible(true) // 多边形的填充色
                } else if (zs < 30) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 255, 153, 133)).visible(true) // 多边形的填充色
                } else if (zs < 50) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 245, 117, 103)).visible(true) // 多边形的填充色
                } else if (zs < 80) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 230, 69, 70)).visible(true) // 多边形的填充色
                } else if (zs < 100) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 184, 9, 9)).visible(true) // 多边形的填充色
                } else if (zs >= 100) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 138, 8, 8)).visible(true) // 多边形的填充色
                }
                s = 1
                mPolygon = aMap!!.addPolygon(mPolygonOptions!!.zIndex(11f))
                if (dataGeometry.isShow)
                    aMap!!.addText(TextOptions().text(dataGeometry.name + "(" + zs + ")").position(getCenter(dataGeometry.center)).fontColor(Color.parseColor("#663300")).fontSize(20).zIndex(-1f))
            }
        }
    }
    //高亮显示
    private fun kuangGeoment(dataGeometry: TownCountEntity) {

        if (!dataGeometry.equals("")) {
            var zs = 0;
//            clear
            var substring = dataGeometry.geometry!!.substring(0, dataGeometry.geometry!!.length - 5)
            val replac = substring.replace("ZM", "")
            val replace = replac.replace("MULTIPOLYGON(((", "")
            val replace1 = replace.replace("POLYGON((", "")
            var split = replace1.split("),(")
            var s = 0
            for (i in 0..split.size - 1) {
                mPolygonOptions = PolygonOptions()
                val latList = getLatList(split[i])
                mPolygonOptions!!.getPoints().clear()
                mPolygonOptions!!.addAll(latList)

                if (position == 0) {
                    zs = dataGeometry.wzg
                } else {
                    zs = dataGeometry.total
                }
                if (!dataGeometry.isShow) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 154, 154, 154)).visible(true)
                } else if (zs == 0) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 255, 255, 255)).visible(true) // 多边形的填充色  , 43, 43, 43
                } else if (zs < 51) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 252, 243, 127)).visible(true) // 多边形的填充色
                } else if (zs < 151) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 242, 193, 54)).visible(true) // 多边形的填充色
                } else if (zs < 501) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 216, 131, 46)).visible(true) // 多边形的填充色
                } else if (zs > 500) {
                    mPolygonOptions!!.strokeWidth(2f) // 多边形的边框
                            .strokeColor(Color.argb(255, 43, 43, 43)) // 边框颜色
                            .fillColor(Color.argb(255, 156, 87, 34)).visible(true) // 多边形的填充色
                }
                s = 1
                val markerOptions = MarkerOptions()
                markerOptions.anchor(0f, 0f) //点标记的锚点

                val bitmap = BitmapFactory.decodeResource(resources,
                        R.drawable.ic_point)
                markerOptions.icon(BitmapDescriptorFactory
                        .fromBitmap(bitmap))
                markerOptions.position(getCenter(dataGeometry.center))
                if (zs==0){
//                    val growMarker = aMap!!.addMarker(markerOptions)//在地图上显示图片
                    /*aMap!!.addGroundOverlay(GroundOverlayOptions().image(BitmapDescriptorFactory
                            .fromBitmap(bitmap)).position(getCenter(dataGeometry.center),10f))*/
                }
                mPolygon = aMap!!.addPolygon(mPolygonOptions!!.zIndex(11f))

                if (dataGeometry.isShow)
                    aMap!!.addText(TextOptions().text(dataGeometry.xzqmc + "(" + zs + ")").position(getCenter(dataGeometry.center)).fontColor(Color.parseColor("#663300")).fontSize(16).zIndex(0f))
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
     * 双平滑曲线传入数据，添加markview，添加实体类单位
     *
     * @param yoyList
     * @param realList
     * @param lineChart
     * @param colors
     * @param drawables
     * @param unit
     * @param values2
     * @param values1
     * @param labels
     */
    //集合倒序遍历 倒序显示行政区
    private fun updateLinehart(homeList: java.util.ArrayList<SwitchTimeEntity.ListBean>, lineChart: LineChart, colors: IntArray, drawables: Array<Drawable>,
                               unit: String, values1: ArrayList<Entry>, values2: ArrayList<Entry>, labels: Array<String>) {
        val entries = arrayOfNulls<ArrayList<Entry>>(2)
        entries[0] = values1
        entries[1] = values2
        val lineChartEntity = LineChartEntity(lineChart, entries, labels, colors, Color.parseColor("#ffffff"), 12f)
        lineChartEntity.drawCircle(false)
        lineChart.setScaleMinima(1.0f, 1.0f)
        lineChart.getXAxis().setLabelRotationAngle(0f)
        lineChart.getAxisLeft().gridColor = Color.parseColor("#bed0fe")
        //        toggleFilled(lineChartEntity, drawables, colors)
        //隐藏x轴描述
        var description = Description()
        description.setEnabled(false)
        lineChart.setDescription(description)
        val legend = lineChart.legend
        legend.isWordWrapEnabled = true
        legend.isEnabled = false
        legend.setForm(Legend.LegendForm.NONE)
//        lineChart.line
        lineChart.setGridBackgroundColor(Color.parseColor("#bed0fe"))//Color.WHITE
        var maxy = 10f
        for (i in entries) {
            for (s in i!!) {
                if (maxy <= s.y) {
                    maxy = s.y + 3
                }
            }

        }
        val axisLeft = lineChart.axisLeft
        axisLeft.axisMinimum = 0f
        axisLeft.axisMaximum = maxy
        /**
         * 这里切换平滑曲线或者折现图
         */
        //        lineChartEntity.setLineMode(LineDataSet.Mode.CUBIC_BEZIER);

        var xAxis = lineChart.getXAxis()
        xAxis.setLabelRotationAngle(30f) //标签倾斜
        xAxis.setDrawLabels(false)

        lineChart.axisLeft.setDrawLabels(false)

        lineChartEntity.setLineMode(LineDataSet.Mode.HORIZONTAL_BEZIER)
        lineChartEntity.initLegend(Legend.LegendForm.CIRCLE, 12f, Color.parseColor("#ffffff"))
        lineChartEntity.updateLegendOrientation(Legend.LegendVerticalAlignment.TOP, Legend.LegendHorizontalAlignment.RIGHT, Legend.LegendOrientation.HORIZONTAL)

        lineChartEntity.setAxisFormatter(
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val monthStr = mFormat.format((value - 1).toDouble())
                        val toInt = value.toInt()
                        return if (monthStr.contains(".") && toInt < 1) {
                            ""
                        } else {
                            if (homeList.size >= toInt && toInt > 0) {
                                ""
                                //homeList[toInt - 1].xzqmc//toInt - 1
                            } else {
                                ""
                            }

                        }
                    }
                },
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return mFormat.format(value.toDouble()) + unit
                    }
                })

        lineChartEntity.setDataValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return mFormat.format(value.toDouble()) + unit

            }
        })
        lineChart.data.setDrawValues(false)
    }


    private fun updateLinehart1(homeList: java.util.ArrayList<SwitchTimeEntity.ListBean>, lineChart: LineChart, colors: IntArray, drawables: Array<Drawable>,
                                unit: String, values1: ArrayList<Entry>, values2: ArrayList<Entry>, labels: Array<String>) {
        val entries = arrayOfNulls<ArrayList<Entry>>(2)
        entries[0] = values1
        entries[1] = values2
        val lineChartEntity = LineChartEntity(lineChart, entries, labels, colors, Color.parseColor("#ffffff"), 12f)
        lineChartEntity.drawCircle(false)
        lineChart.setScaleMinima(1.0f, 1.0f)
        lineChart.getXAxis().setLabelRotationAngle(0f)
        lineChart.getAxisLeft().gridColor = Color.parseColor("#d7c3ff")
        //        toggleFilled(lineChartEntity, drawables, colors)
        //隐藏x轴描述
        var description = Description()
        description.setEnabled(false)
        lineChart.setDescription(description)
        val legend = lineChart.legend
        legend.isWordWrapEnabled = true
        legend.isEnabled = false
        legend.setForm(Legend.LegendForm.NONE)
//        lineChart.line
        lineChart.setGridBackgroundColor(Color.parseColor("#d7c3ff"))//Color.WHITE
        var maxy = 10f
        for (i in entries) {
            for (s in i!!) {
                if (maxy <= s.y) {
                    maxy = s.y + 3
                }
            }

        }
        val axisLeft = lineChart.axisLeft
        axisLeft.axisMinimum = 0f
        axisLeft.axisMaximum = maxy
        /**
         * 这里切换平滑曲线或者折现图
         */
        //        lineChartEntity.setLineMode(LineDataSet.Mode.CUBIC_BEZIER);

        var xAxis = lineChart.getXAxis()
        xAxis.setLabelRotationAngle(30f) //标签倾斜
        xAxis.setDrawLabels(false)

        lineChart.axisLeft.setDrawLabels(false)

        lineChartEntity.setLineMode(LineDataSet.Mode.HORIZONTAL_BEZIER)
        lineChartEntity.initLegend(Legend.LegendForm.CIRCLE, 12f, Color.parseColor("#ffffff"))
        lineChartEntity.updateLegendOrientation(Legend.LegendVerticalAlignment.TOP, Legend.LegendHorizontalAlignment.RIGHT, Legend.LegendOrientation.HORIZONTAL)

        lineChartEntity.setAxisFormatter(
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val monthStr = mFormat.format((value - 1).toDouble())
                        val toInt = value.toInt()
                        return if (monthStr.contains(".") && toInt < 1) {
                            ""
                        } else {
                            if (homeList.size >= toInt && toInt > 0) {
                                ""
                                //homeList[toInt - 1].xzqmc//toInt - 1
                            } else {
                                ""
                            }

                        }
                    }
                },
                object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return mFormat.format(value.toDouble()) + unit
                    }
                })

        lineChartEntity.setDataValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return mFormat.format(value.toDouble()) + unit

            }
        })
        lineChart.data.setDrawValues(false)
    }

    //人口饼状图
    private fun rkinitPie(pie: PieChart, title: String, datas: java.util.ArrayList<Float>, lables: java.util.ArrayList<String>) {
        pie.setDrawEntryLabels(false)
        pie.setUsePercentValues(false)
        pie.description.isEnabled = false
        pie.setExtraOffsets(5f, 10f, 5f, 5f)

        pie.dragDecelerationFrictionCoef = 0.95f

        pie.setExtraOffsets(3f, 0f, 3f, 0f)//设置

        pie.isDrawHoleEnabled = true
        pie.setHoleColor(Color.parseColor("#BEE3FE"))//Color.WHITE

        pie.setTransparentCircleColor(Color.WHITE)
        pie.setTransparentCircleAlpha(110)

        pie.holeRadius = 86f
        pie.transparentCircleRadius = 61f

        pie.setDrawCenterText(true)

        pie.centerText = title
        pie.setCenterTextSize(20f)
        pie.rotationAngle = 0f
        // enable rotation of the chart by touch
        pie.isRotationEnabled = true
        pie.isHighlightPerTapEnabled = true
        pie.setEntryLabelColor(Color.BLACK)
        setRKData(pie, datas, lables)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    private fun setRKData(pie: PieChart, datas: List<Float>, lables: List<String>) {
        val entries = java.util.ArrayList<PieEntry>()

        var zs = 0f
        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
            zs = zs+datas[i]
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.valueFormatter = object: ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                val format = DecimalFormat("###.#")
                val format1 = DecimalFormat("###")
                return ""//format1.format(value)+"(${format.format(value/zs*100)}%)"//super.getFormattedValue(value)
            }
        }

        val colors = java.util.ArrayList<Int>()
        colors.add(Color.argb(199, 60, 178, 239))
        colors.add(Color.argb(199, 174, 253, 202))
        colors.add(Color.argb(199, 255, 240, 101))
        colors.add(Color.argb(199, 143, 166, 188))
        colors.add(Color.argb(199, 201, 85, 1))
        colors.add(Color.argb(199, 255, 191, 191))

        dataSet.colors = colors

        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        dataSet.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

        val data = com.github.mikephil.charting.data.PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data

        // undo all highlights
        pie.highlightValues(null)

        pie.invalidate()
    }

    override fun onMapClick(p0: LatLng?) {
        point = p0
        pointStr = "POINT(" + p0!!.longitude + " " + p0.latitude + ")"
        mPresenter.getByPoint(pointStr,type)
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
    override fun onResume() {
        super.onResume()
        if (map_frag_rjhj_first_zhen != null) {
            map_frag_rjhj_first_zhen.onResume()
            MainActivity.mainTvFirst!!.visibility = View.VISIBLE//显示侧滑菜单按钮
            MainActivity.mainTvBack!!.visibility = View.GONE//隐藏返回人居环境首页按钮
            MainActivity.ibActMain!!.setImageResource(R.drawable.rjhj_first_map)
            MainActivity.tvTitle!!.setText("人居环境首页")
            MainActivity.ibActMain!!.setOnClickListener {//页面切换
                AppCache.getInstance().rjhjCode = AppCache.getInstance().code
                AppCache.getInstance().rjhjCenter = AppCache.getInstance().loginCenter
                FragmentUtils.replaceFragment(activity!!.supportFragmentManager, RjhjFragment(),R.id.fl_content,false)//LdglFragment//跳转问题列表页
            }
            mPresenter.getQueryImproveRate(AppCache.getInstance().code)
            mPresenter.getRjhjjcMap(AppCache.getInstance().code,type)
            mPresenter.getTownType(AppCache.getInstance().code,type)
        }
    }

    override fun onPause() {
        super.onPause()
        if (map_frag_rjhj_first_zhen != null) {
            map_frag_rjhj_first_zhen.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (map_frag_rjhj_first_zhen != null) {
            map_frag_rjhj_first_zhen!!.onDestroy()
        }
    }

}