package com.jymj.zhglxt.ldrkgl.home.activity

import android.content.Intent
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.google.zxing.integration.android.IntentIntegrator
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.home.adapter.RklbAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.contract.LdrkDetailContract
import com.jymj.zhglxt.login.model.LdrkDetailModel
import com.jymj.zhglxt.login.presenter.LdrkDetailPresenter
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.HeritageScopeTileProvider
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_ldrk_detail.*

class LdrkDetailActivity : BaseActivity<LdrkDetailPresenter, LdrkDetailModel>(), LdrkDetailContract.View {

//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private var aMap: AMap? = null
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

    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    private var ylFolwEntity: YlFolwEntity? =null
    private var mPopLcTips: CommenPop? = null
    private var ylId = 0L
    private var  addPolyline:Polyline? = null//????????????
    private var mPolylineOptions: PolylineOptions? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_ldrk_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//???????????????

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_act_ldrk_detail.onCreate(intent.extras)

        initAMap()
        setAMap()
        initTc()//??????
        //supl_ldrk_detail_act   slv_ldrk_detail_act

        var metric = DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        var height = metric.heightPixels;   // ????????????????????????
        supl_ldrk_detail_act.panelHeight = (height*0.65).toInt()//(height*0.7).toInt()//DisplayUtil.dip2px(50f)
        supl_ldrk_detail_act.setScrollableView(slv_ldrk_detail_act)
        ylId = intent.getLongExtra("ylId",0L)
        mPresenter.getDcylxx("",ylId)
        tv_act_ldrk_detail_back.setOnClickListener { //??????????????????
            finish()
        }


    }

    fun getCenter(center:String): LatLng {
        if (center != null) {
            if (!center.equals("")){
                val points = center.substring(6, center.length - 1).split(" ").toTypedArray()
                return if (points != null && points.size > 1) {
                    val converter = CoordinateConverter()
                    // CoordType.GPS ?????????????????????
                    converter.from(CoordinateConverter.CoordType.GPS)
                    val sl = LatLng(points[1].toDouble(), points[0].toDouble())
                    // sourceLatLng?????????????????? LatLng??????
                    converter.coord(sl)
                    val latLng = converter.convert()
                    sl
                } else {
                    LatLng(0.0, 0.0)
                }
            }else {
                LatLng(0.0, 0.0)
            }
        }
        return LatLng(0.0, 0.0)
    }

    override fun initDatas() {

    }

    private fun initTc(){
        //????????????
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!
        for (i in mLayers){//??????
            i.isCheck = false
            when (i.name) {
                "?????????" -> {//??????
                    i.isCheck = true
                }
                "????????????" -> {
                    i.isCheck = true
                }
                "??????" -> {
                    i.isCheck = true
                }
                "??????" -> {
                    i.isCheck = true
                }
            }
        }
        for (i in mLayers1){//??????
            i.isCheck = false
            when (i.name) {
                "?????????" -> {
                    i.isCheck = true
                }
                "????????????" -> {
                    i.isCheck = true
                }
                "??????" -> {
                    i.isCheck = true
                }
                "??????" -> {
                    i.isCheck = true
                }
            }
        }
        //????????????
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
            aMap = map_act_ldrk_detail.getMap()
        }
//        setUpMap()
    }
    /**
     * ????????????amap?????????
     */
   /* private fun setUpMap() { // ???????????????????????????
//        aMap!!.setLocationSource(this);
        mMyLocationStyle = MyLocationStyle()
        //??????????????????????????????????????????
        mMyLocationStyle!!.strokeWidth(3f)
        mMyLocationStyle!!.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        aMap!!.uiSettings.isMyLocationButtonEnabled = true // ????????????????????????????????????
        //        aMap.setMyLocationEnabled(true);// ?????????true??????????????????????????????????????????false??????????????????????????????????????????????????????false
// ???????????????????????????????????? ??????????????????????????????????????????????????????????????????
        aMap!!.setMyLocationStyle(mMyLocationStyle)
        aMap!!.isMyLocationEnabled = true // ?????????true?????????????????????????????????false??????????????????????????????????????????????????????false???
        mMyLocationStyle!!.showMyLocation(true)
    }*/

    /**
     * ????????????
     */
    private fun setAMap() { //????????????????????????????????????
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false // ???????????????
        aMap!!.uiSettings.isZoomControlsEnabled = false
        aMap!!.setMapLanguage(AMap.CHINESE)
        /*aMap!!.setOnMapClickListener(this)
        mPolygonOptions = PolygonOptions()*/
//        aMap!!.addTileOverlay(opt_tdtnN)

//        aMap!!.// = 18f

    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }
    override fun onResume() {
        super.onResume()
        map_act_ldrk_detail.onResume()
        if (AppCache.getInstance().isCzxq){
            mPresenter.getCxldry(AppCache.getInstance().ylId)
            mPresenter.getDcylxx("",ylId)
            AppCache.getInstance().isCzxq = false
        }
    }
    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_act_ldrk_detail.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_act_ldrk_detail.onDestroy()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // ??????????????????  ?????????????????????
        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);//IntentResult
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "????????????", Toast.LENGTH_LONG).show();
            } else {
                if (result.getContents()!=null){
                    val split = result.getContents().split("id=")
                    if (split.size>1){
                        mPresenter.getEwmsmZjdBd(ylFolwEntity!!.objectid,split[1])
                    }else{
                        mPresenter.getEwmsmZjdBd(ylFolwEntity!!.objectid,result.getContents())
                    }
                }else{
                    ToastUtils.showShort("?????????????????????")
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    //???????????????????????????
    override fun returnEwmsmZjdBd(msg: YlFolwEntity) {
//        ToastUtils.showShort("????????????")
        if (msg!=null){
            if (msg!!.qrCodeFileEntity!=null){
                ll_act_ldrk_detail_smewm.visibility = View.GONE
                iv_act_ldrk_detail_ewmfh.visibility = View.VISIBLE
                Glide.with(mContext).load(msg!!.qrCodeFileEntity.path)
                        .into(iv_act_ldrk_detail_ewmfh)
                if (AppMenus.getInstance().menuBeans.toString().contains("????????????")){
                    ll_act_ldrk_detail_message.visibility = View.VISIBLE
                }
                if (AppMenus.getInstance().menuBeans.toString().contains("??????????????????")){
                    ll_act_ldrk_detail_add.visibility = View.VISIBLE
                }
                AppCache.getInstance().isFwlb = true
                AppCache.getInstance().isCzlb = true
                AppCache.getInstance().isDtymlb = true
            }
        }
    }
    var ryList = ArrayList<FlowInfoEntity>()
    //??????????????????
    override fun returnCxldry(msg: List<FlowInfoEntity>) {
        ryList = msg as ArrayList<FlowInfoEntity>
        rlv_act_ldrk_detail_rkqk.layoutManager = object :LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val zlglAdapter = RklbAdapter(this, ryList)
        rlv_act_ldrk_detail_rkqk.adapter =zlglAdapter
        zlglAdapter.setOnItemClick(object: RklbAdapter.OnRklbItemClick{

            override fun onDetClick(pjtaskFile: FlowInfoEntity?, position: Int) {//??????
                mPopLcTips = CommenPop.getNormalPopu(this@LdrkDetailActivity, R.layout.pop_idcard_tips, rlt_act_ldrk_detail_top)
                val decorView = mPopLcTips!!.contentView
                val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                mPopLcTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                message.text = "????????????????????????"

                mPopLcTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                CommenPop.backgroundAlpha(0.5f, this@LdrkDetailActivity)
                btNo.setOnClickListener {
                    mPopLcTips!!.dismiss()
                }
                btYes.setOnClickListener {
                    mPresenter.getLcldry(listOf(pjtaskFile!!.id.toInt()))
                    ryList.removeAt(position)
                    zlglAdapter.notifyDataSetChanged()
                    mPopLcTips!!.dismiss()
                }
            }

            override fun onClick(pjtaskFile: FlowInfoEntity?) {//??????
                if (SingleOnClickUtil.isFastClick()){
                    val intent = Intent(this@LdrkDetailActivity,InformationAcquisitionActivity::class.java)
                    intent.putExtra("ldrkDetailIdCard",pjtaskFile!!.idCard)
                    intent.putExtra("ldrkDetailYlId",pjtaskFile.objectid.toString())
                    intent.putExtra("ldrkDetailMph",pjtaskFile.mph.toString())
                    startActivity(intent)
                }
            }
        })
    }

    override fun returnDcylxx(msg: YlFolwEntity) {
        ylFolwEntity = msg
        if (ylFolwEntity!!.geometry!=null){
            kuangGeomentLine(ylFolwEntity!!.geometry)
        }
        if (ylFolwEntity!=null){//??????????????????
            if (ylFolwEntity!!.qrCodeFileEntity!=null){

                ll_act_ldrk_detail_smewm.visibility = View.GONE
                iv_act_ldrk_detail_ewmfh.visibility = View.VISIBLE
                if (AppMenus.getInstance().menuBeans.toString().contains("????????????")){//???????????????????????????????????????
                    ll_act_ldrk_detail_message.visibility = View.VISIBLE
                }
                if (AppMenus.getInstance().menuBeans.toString().contains("??????????????????")){
                    ll_act_ldrk_detail_add.visibility = View.VISIBLE
                }
                Glide.with(mContext).load(ylFolwEntity!!.qrCodeFileEntity.path)
                        .into(iv_act_ldrk_detail_ewmfh)
            }
            if (ylFolwEntity!!.objectid!=null){
                mPresenter.getCxldry(ylFolwEntity!!.objectid)
                AppCache.getInstance().ylId = ylFolwEntity!!.objectid
            }
            tv_act_ldrk_detail_name.setText(ylFolwEntity!!.hzmc)
            tv_act_ldrk_detail_mph.setText(ylFolwEntity!!.mph)
            tv_act_ldrk_detail_yczf.setText(ylFolwEntity!!.dwell.toString())
            tv_act_ldrk_detail_jzrs.setText(ylFolwEntity!!.rent.toString())
            tv_act_ldrk_detail_jdc.setText(ylFolwEntity!!.motorCars.toString())
            tv_act_ldrk_detail_mtc.setText(ylFolwEntity!!.motorcycle.toString())
            tv_act_ldrk_detail_ddc.setText(ylFolwEntity!!.electricCars.toString())
            val center1 = getCenter(ylFolwEntity!!.center)
//     aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(center1));//LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())
//            aMap?.animateCamera(CameraUpdateFactory.changeLatLng(center1))
            aMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(center1,16f))
//            aMap?.addMarker(MarkerOptions().position(center1))

        }
        ll_act_ldrk_detail_smewm.setOnClickListener {//???????????????????????????
            if (SingleOnClickUtil.isFastClick()){
                var intentIntegrator = IntentIntegrator(this);//ScanActivity
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                intentIntegrator.setBarcodeImageEnabled(true)//????????????????????????
                intentIntegrator.setBeepEnabled(false)//?????????????????????????????????????????????
                intentIntegrator.setOrientationLocked(false)//??????????????????????????????
                intentIntegrator.setPrompt("????????????/???????????????????????????????????????")//?????????????????????
                intentIntegrator.setCaptureActivity(ScanActivity::class.java) // ??????????????????activity
                intentIntegrator.initiateScan() // ????????????
            }
        }
        ll_act_ldrk_detail_message.setOnClickListener { ///????????????
            if (SingleOnClickUtil.isFastClick()){
                val intent = Intent(this, SendMessageActivity::class.java)
                intent.putExtra("code", ylFolwEntity!!.code)
                startActivity(intent)
//                ToastUtils.showShort("????????????")
            }
        }

        tv_act_ldrk_detail_add.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                val intent = Intent(this,InformationAcquisitionActivity::class.java)
                intent.putExtra("InfirmationAcquisitionAddress",ylFolwEntity!!.mph)
                startActivity(intent)
            }
        }
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
//                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                        }

                        if (mPolylineOptions == null)
//                            mPolygon!!.remove()
                            mPolylineOptions = PolylineOptions()
                        mPolylineOptions!!.getPoints().clear()
                        mPolylineOptions!!.addAll(latList)
                        mPolylineOptions!!.color(Color.argb(255, 0, 0, 255))
                        /*if (i == 0) {
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(50, 64, 64, 255)) // ?????????????????????
                    } else {
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(0, 0, 0, 0)) // ?????????????????????
                    }*/
                        s = 1
                        addPolyline = aMap!!.addPolyline(mPolylineOptions)

                    }
                }else{
                    val latList = getLatList(split[i])
                    if (i == 0){
//                        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
                    }

                    if (mPolylineOptions == null)
//                            mPolygon!!.remove()
                        mPolylineOptions = PolylineOptions()
                    mPolylineOptions!!.getPoints().clear()
                    mPolylineOptions!!.addAll(latList)
                    mPolylineOptions!!.color(Color.argb(255, 0, 0, 255))
                    /*if (i == 0) {
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(50, 64, 64, 255)) // ?????????????????????
                    } else {
                        mPolylineOptions!!.strokeWidth(10f) // ??????????????????
                                .strokeColor(Color.argb(255, 0, 0, 255)) // ????????????
                                .fillColor(Color.argb(0, 0, 0, 0)) // ?????????????????????
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
                     // CoordType.GPS ?????????????????????
                     converter.from(CoordinateConverter.CoordType.GPS)
                     var sl = LatLng(split1[1].toDouble(), split1[0].toDouble())
                     // sourceLatLng?????????????????? LatLng??????
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
    override fun returnLcldry(msg: String) {
        ToastUtils.showShort(msg)
    }
}
