package com.jymj.zhglxt.rjhj.activity

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.*
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.*
import com.amap.api.maps2d.model.CameraPosition
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions
import com.amap.api.maps2d.model.TileOverlayOptions
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//import com.iceteck.silicompressorr.SiliCompressor
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.app.AppApplication
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.rjhj.adapter.HbAdapter
import com.jymj.zhglxt.rjhj.adapter.VideoAdapter
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.bean.enums.*
import com.jymj.zhglxt.rjhj.contract.EnvironmentalActContract
import com.jymj.zhglxt.rjhj.presenter.EnvironmentalActPresenter
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.lzy.okgo.utils.HttpUtils
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.baseapp.AppManager
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.act_rjhj_hbdetails_bh_include.*
import kotlinx.android.synthetic.main.act_rjhj_hbdetails_sh_include.*
import kotlinx.android.synthetic.main.act_rjhj_hbdetails_xc_include.*
import kotlinx.android.synthetic.main.act_rjhj_hbdetails_zg_include.*
import kotlinx.android.synthetic.main.activity_hbjcdetail.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.*
import java.lang.Double
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HBJCDetailActivity : BaseActivity<EnvironmentalActPresenter, EnvironmentalActContract.Model>(), EnvironmentalActContract.View, AMapLocationListener {
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private var filetype = 1
//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    internal var tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath!! + "BMS/map/tdt/sta/")
    internal var opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true)
            .memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath!! + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    internal var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath!! + "BMS/map/tdt/note/")
    internal var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath!! + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    var tdtStaNomal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNomal/")
    var opt_tdtStaNomal = TileOverlayOptions().tileProvider(tdtStaNomal).diskCacheEnabled(false)
            .memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNomal/").diskCacheSize(10 * 1024)
    internal var tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath!! + "BMS/map/tdt/staNote/")
    internal var opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false)
            .memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath!! + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

    private var aMap: AMap? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    private var serializableExtra: PjEnviorSupvsEntity? = null
    val steps = ArrayList<String>()
    var dclFileAdapter: BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null
    var dclFileAdapter1: BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null
    var xfFileAdapter: BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null
    var zgzFileAdapter: BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null
    var yswcFileAdapter: BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null
    private var selectedPhotos = ArrayList<String>()
    private var selectedPhotos_new = ArrayList<String>()
    private val selectedVideos = ArrayList<String>()
    private val videoIdList = ArrayList<Int>()
    private val fileIdList = ArrayList<Int>()
    private var photoAdapter: PhotoAdapter? = null
    private var videoAdapter: VideoAdapter? = null
    var envirorUpPopu: CommenPop? = null
    private var tvUpDateEnviron: TextView? = null
    private var uploadDate: String? = null
    private var msid = 0
    private var qqtype = 0
    private var ll_bqxx_type = 0
    private var ll_rjdl_type = 0
    private var ll_rjhjhp_type = 0
    private var ll_rjriver_type = 0
    val userList = ArrayList<String>()
    var sysUserEntityList = ArrayList<SysUserEntity>()
    private var dclFileList = ArrayList<PjEnviorFileEntity>()
    private var deletePjEnviorFileEntity: PjEnviorFileEntity? = null
    private var yswc_FileList = ArrayList<PjEnviorFileEntity>()
    private var zgz_FileList = ArrayList<PjEnviorFileEntity>()
    private var xf_FileList = ArrayList<PjEnviorFileEntity>()
    private var dcl_FileList = ArrayList<PjEnviorFileEntity>()
    private var hbjcEntity: PjEnviorSupvsEntity? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_hbjcdetail
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        /*MainActivity.tvTitle?.setText("基本信息")
        MainActivity.bt_map!!.visibility = View.GONE*/
//        MapsInitializer.setApiKey("c114a893046db43fa28d1ea9bf4166f8");
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_hbjcdetail?.onCreate(intent.extras)
        EventBus.getDefault().register(this)
        initAMap()
        initStep()
        //supl_hbjc_detail_act  slv_hbjc_detail_act

        var metric = DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        var height = metric.heightPixels;   // 屏幕高度（像素）
        supl_hbjc_detail_act.panelHeight = (height*0.56).toInt()//(height*0.7).toInt()//DisplayUtil.dip2px(50f)
        supl_hbjc_detail_act.setScrollableView(slv_hbjc_detail_act)
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        serializableExtra = intent.getSerializableExtra("pjenvior") as PjEnviorSupvsEntity?
        val code = serializableExtra!!.code//intent.getStringExtra("code")
        mPresenter.getRjhjPoint(serializableExtra!!.location)
        AppCache.getInstance().isList = 1
        msid = serializableExtra!!.id

        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)
        for (l in mLayers) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {//底图
                    l.isCheck = true
                }
                "固定点位" -> {
                    l.isCheck = true
                }
                "人居环境" -> {
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
                "天地图" -> {
                    l.isCheck = true
                }
                "固定点位" -> {
                    l.isCheck = true
                }
                "人居环境" -> {
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
        addOverLayer(mLayers)
        addOverLayer(mLayers1)
        location()
        //        addOverLayer(mLayers1)
        //hbjcdetail_dsh_bh_btn
        mPresenter.infoEnvironmental(serializableExtra!!.id.toString())
        //待处理修改按钮
        hbjcdetail_dcl_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                filetype = 3
                initPopuXfUp(serializableExtra!!)
                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(hbjcdetail_top, Gravity.CENTER, 0, 0)
            }

        }
        hbjcdetail_update_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                filetype = 1
                /*if (serializableExtra!!.hjzzyjfl==1){
                    filetype = 1
                }else{
                    filetype = 3
                }*/

                initPopuEnvironUp(serializableExtra)
                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(hbjcdetail_top, Gravity.CENTER, 0, 0)
            }
        }
        //整改中修改按钮
        hbjcdetail_xf_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                filetype = 4
                initPopuZGZUp(serializableExtra)
                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(hbjcdetail_top, Gravity.CENTER, 0, 0)
            }

        }
        /*hbjcdetail_ch_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){//驳回
                mPresenter.getFallBackQutype(serializableExtra!!.id,"1","")
            }
        }*/
        hbjcdetail_nyth_btn.setOnClickListener {//内业退回
            if (SingleOnClickUtil.isFastClick()){
                initPopuEnvironNyth()
                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(hbjcdetail_top, Gravity.CENTER, 0, 0)
            }
        }
        hbjcdetail_cxbh_btn.setOnClickListener {//撤销驳回
            if (SingleOnClickUtil.isFastClick()){
                serializableExtra!!.qutype = 102
                mPresenter.addEnvironmental(serializableExtra!!)
            }
        }

        //驳回
        hbjcdetail_dsh_bh_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                filetype = 100
                initPopuYSWCUp(serializableExtra,hbjcdetail_dsh_bh_btn)
                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(hbjcdetail_top, Gravity.CENTER, 0, 0)
            }

        }
        hbjcdetail_xz_bh_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
//                filetype = 101
                filetype = 3
                serializableExtra!!.qutype = 3
                mPresenter.addEnvironmental(serializableExtra!!)
                /*initPopuYSWCUp(serializableExtra)
                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(hbjcdetail_top, Gravity.CENTER, 0, 0)*/
            }

        }

        hbjcdetail_dsh_xz_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                filetype = 5
                serializableExtra!!.qutype = 5
                mPresenter.addEnvironmental(serializableExtra!!)

            }

        }

        //没有问题的直接验收完成修改按钮
        hbjcdetail_zjys_btn.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                filetype = 5
                serializableExtra!!.zjwj=1
                serializableExtra!!.qutype = 5
                mPresenter.addEnvironmental(serializableExtra!!)

            }

        }

    }
    private fun video() {

        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        //设置视频录制的最长时间
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10)
//                intent.
        //设置视频录制的画质
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
        startActivityForResult(intent, 66)

    }

    var mListener: LocationSource.OnLocationChangedListener? = null
    var mLocationClient : AMapLocationClient? =null
    var mLocationOption : AMapLocationClientOption? =null
    var aMapLocation: AMapLocation? = null//定位信息
    //调用定位的方法
    private fun location() {
        //初始化定位
        mLocationClient = AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient!!.setLocationListener(this);
        //初始化定位参数
        mLocationOption = AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.
        //设置是否返回地址信息（默认返回地址信息）
// mLocationOption!!.setNeedAddress(true);
        //设置是否只定位一次,默认为false
// mLocationOption!!.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
// mLocationOption!!.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption!!.setMockEnable(false);
        mLocationOption!!.setLocationCacheEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption!!.setInterval(1000);
        //给定位客户端对象设置定位参数
        mLocationClient!!.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient!!.startLocation();
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
                var df =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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



            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
// Toast.makeText(activity!!.getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    override fun initDatas() {
    }

    private fun initStep() {
        /*steps.add("待处理")
        steps.add("整改中")
        steps.add("验收完成")*/
        steps.add("巡查")
//        steps.add("下发")
        steps.add("整改")
        steps.add("待审核")
        steps.add("待审批")
        steps.add("销账")
    }

    private fun initTlv(qutype: Int) {
        if (qutype==0||qutype==1){
            iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_grey)
            iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_grey)
            iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            ll_act_rjhjhbdetails_xc.isEnabled = true
            ll_act_rjhjhbdetails_zg.isEnabled = false
            ll_act_rjhjhbdetails_sh.isEnabled = false
            ll_act_rjhjhbdetails_xz.isEnabled = false
        }else if (qutype==3){
            iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_grey)
            iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            ll_act_rjhjhbdetails_xc.isEnabled = true
            ll_act_rjhjhbdetails_zg.isEnabled = true
            ll_act_rjhjhbdetails_sh.isEnabled = false
            ll_act_rjhjhbdetails_xz.isEnabled = false
        }else if (qutype==4){
            iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            ll_act_rjhjhbdetails_xc.isEnabled = true
            ll_act_rjhjhbdetails_zg.isEnabled = true
            ll_act_rjhjhbdetails_sh.isEnabled = true
            ll_act_rjhjhbdetails_xz.isEnabled = false
        }else if (qutype==5){
            iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_green)
            iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_green)
            ll_act_rjhjhbdetails_xc.isEnabled = true
            ll_act_rjhjhbdetails_zg.isEnabled = true
            ll_act_rjhjhbdetails_sh.isEnabled = true
            ll_act_rjhjhbdetails_xz.isEnabled = true
        }

        ll_act_rjhjhbdetails_xc.setOnClickListener {
            filetype = 1
            inlcude_act_rjhj_hbdetails_xc.visibility = View.VISIBLE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.GONE
            if (qutype == 0 || qutype == 1) {
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_grey)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_grey)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            }else if (qutype==3){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_grey)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            }else if (qutype==4){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            }else if (qutype==5){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_green)
            }
        }
        ll_act_rjhjhbdetails_zg.setOnClickListener {
            inlcude_act_rjhj_hbdetails_xc.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.VISIBLE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.GONE
            filetype = 3
            if (qutype==3){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_grey)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            }else if (qutype==4){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            }else if (qutype==5){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_green)
            }
        }
        ll_act_rjhjhbdetails_sh.setOnClickListener {
            inlcude_act_rjhj_hbdetails_xc.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.VISIBLE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.GONE
            filetype = 4
            if (qutype==4){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_grey)
            }else if (qutype==5){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_blue)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_green)
            }
        }
        ll_act_rjhjhbdetails_xz.setOnClickListener {
            inlcude_act_rjhj_hbdetails_xc.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.VISIBLE
            filetype = 5
           if (qutype==5){
                iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_green)
                iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_blue)
            }
        }


    }



    override fun returnInfoEnvironmental(updateEntity: RjhjInfoBean) {

        serializableExtra = updateEntity.enviorSupvsEntity
        hbjcdetail_dcl_rlv.layoutManager = GridLayoutManager(this, 3)
        hbjcdetail_xf_rlv.layoutManager = GridLayoutManager(this, 3)
        hbjcdetail_zgz_rlv.layoutManager = GridLayoutManager(this, 3)
        hbjcdetail_yswc_rlv.layoutManager = GridLayoutManager(this, 3)

        dclFileList = ArrayList<PjEnviorFileEntity>()
        for (i in serializableExtra!!.pjEnviorFileEntities){
            if (i.filetype == 1){//||i.filetype == 2||i.filetype == 50
                dclFileList.add(i)
            }
        }
        dcl_FileList.addAll(dclFileList)
        dclFileAdapter = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, dclFileList) {
            override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                if (item.filetype == 1) {//filetype  ||item.filetype == 2||item.filetype == 50
                    //                AppApplication.getGlideUrl(s1)
                    val pic: String = item.url//ApiConstants.BASE_URL + item.path
                    val s1 = pic.replace("\\", "/")
                    Glide.with(mContext).load(s1)
                            //GlideAppAppApplication.getGlideUrl(s1)
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                            .into(view)
                    helper.setText(R.id.tv_teng_photo_name, item.name)
                    if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR||(AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF))
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@HBJCDetailActivity)
                        // 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
                        // 设置Title的内容
                        builder.setTitle("弹出警告框")
                        // 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
                        // 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
//                                deletePic(item.id)
                                deletePjEnviorFileEntity = item
                                mPresenter.deleteFileEnvironmental(item.id)
                            }
                        })
                        // 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            /*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*/
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        })
                        builder.show()
                    }
                    helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                        var pathList = ArrayList<String>()
                        for (i in dclFileList){
                            val pic = item.url//ApiConstants.BASE_URL + i.path
                            val s1 = pic.replace("\\", "/")
                            pathList.add(s1)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@HBJCDetailActivity)

//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                        }else{
//                            val intent = Intent(Intent.ACTION_VIEW);
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }

                    }
                }else{
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }
//                if (AppCache.getInstance().type == 4)
                if (AppCache.getInstance().name.equals(serializableExtra!!.jlr)){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                }else{
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }
                if (AppCache.getInstance().duties == 1){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }
            }
        }
        hbjcdetail_dcl_rlv.adapter = dclFileAdapter
        var xfFileList = ArrayList<PjEnviorFileEntity>()
        for (i in serializableExtra!!.pjEnviorFileEntities){
            if (i.filetype == 3){
                xfFileList.add(i)
            }
        }
        xf_FileList.addAll(xfFileList)
        xfFileAdapter = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, xfFileList) {
            override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                if (item.filetype == 3) {
                    //                AppApplication.getGlideUrl(s1)
                    val pic: String = item.url//ApiConstants.BASE_URL + item.path
                    val s1 = pic.replace("\\", "/")
                    Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                            .into(view)
                    helper.setText(R.id.tv_teng_photo_name, item.name)
                    if (AppCache.getInstance().type!=3||AppCache.getInstance().type!=4){
                        helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                    }
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                        //                                    Toast.makeText(this@HBJCDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@HBJCDetailActivity)
                        // 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
                        // 设置Title的内容
                        builder.setTitle("弹出警告框")
                        // 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
                        // 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            /* @Override
                             public void onClick(DialogInterface dialog, int which)
                             {
                                 Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                             }*/

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                //deletePic(item.id)
                            }
                        });
// 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            /*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*/

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        });
                        builder.show()

                    }
                    helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                        var pathList = ArrayList<String>()
                        for (i in xfFileList){
                            val pic = item.url//ApiConstants.BASE_URL + i.path
                            val s1 = pic.replace("\\", "/")
                            pathList.add(s1)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@HBJCDetailActivity)
//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                           /* val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
//                            intent.putExtra("url", s1)
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                        hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                startActivity(intent, options)
//                            } else {
//                                startActivity(intent)
//                                overridePendingTransition(R.anim.fade_in,
//                                        R.anim.fade_out)
//                            }*/
//                        }else{
////                             val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
//                            /*intent.setDataAndType(uri, type);
//                            startActivity(intent)*/
//                            val intent = Intent(Intent.ACTION_VIEW);
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }

                }
                }else{
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }
                if (AppCache.getInstance().duties == 1){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }
            }
        }
        hbjcdetail_xf_rlv.adapter = xfFileAdapter
        var zgzFileList = ArrayList<PjEnviorFileEntity>()
        for (i in serializableExtra!!.pjEnviorFileEntities){
            if (i.filetype == 3){
                zgzFileList.add(i)
            }
        }
        zgz_FileList.addAll(zgzFileList)
        zgzFileAdapter = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, zgzFileList) {
            override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                if (item.filetype == 3) {
                    //                AppApplication.getGlideUrl(s1)
                    val pic: String = item.url//ApiConstants.BASE_URL + item.path
                    val s1 = pic.replace("\\", "/")
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                            .into(view)
                    helper.setText(R.id.tv_teng_photo_name, item.name)
                    /*if (AppCache.getInstance().type==5||AppCache.getInstance().type==1){//菜单控制
                        helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                    }*/
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                        //                                    Toast.makeText(this@HBJCDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@HBJCDetailActivity)
// 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
// 设置Title的内容
                        builder.setTitle("弹出警告框")
// 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
// 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            /* @Override
                             public void onClick(DialogInterface dialog, int which)
                             {
                                 Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                             }*/

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                //deletePic(item.id)
                            }
                        });
// 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            /*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*/

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        });
                        builder.show()

                    }
                    helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                        var pathList = ArrayList<String>()
                        for (i in zgzFileList){
                            val pic = item.url//ApiConstants.BASE_URL + i.path
                            val s1 = pic.replace("\\", "/")
                            pathList.add(s1)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@HBJCDetailActivity)
//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                           /* val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
//                            intent.putExtra("url", s1)
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                        hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                startActivity(intent, options)
//                            } else {
//                                startActivity(intent)
//                                overridePendingTransition(R.anim.fade_in,
//                                        R.anim.fade_out)
//                            }*/
//                        }else{
////                            val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
////                            startActivity(intent)
//                            val intent = Intent(Intent.ACTION_VIEW)
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }
                    }
                }else{
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }
                if (AppCache.getInstance().duties == 1){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }

            }
        }
        hbjcdetail_zgz_rlv.adapter = zgzFileAdapter
        var yswcFileList = ArrayList<PjEnviorFileEntity>()
        for (i in serializableExtra!!.pjEnviorFileEntities){
            if (i.filetype == 5||i.filetype == 4){
                yswcFileList.add(i)
            }
        }
        yswc_FileList.addAll(yswcFileList)
        yswcFileAdapter = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, yswcFileList) {
            override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                if (item.filetype == 5||item.filetype == 4) {
                    //                AppApplication.getGlideUrl(s1)
                    val pic: String = item.url//ApiConstants.BASE_URL + item.path
                    val s1 = pic.replace("\\", "/")
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                            .into(view)
                    helper.setText(R.id.tv_teng_photo_name, item.name)
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                        //                                    Toast.makeText(this@HBJCDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@HBJCDetailActivity)
// 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
// 设置Title的内容
                        builder.setTitle("弹出警告框")
// 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
// 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            /* @Override
                             public void onClick(DialogInterface dialog, int which)
                             {
                                 Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                             }*/

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                //deletePic(item.id)
                            }
                        });
// 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            /*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*/

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        });
                        builder.show()

                    }
                    helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                        var pathList = ArrayList<String>()
                        for (i in yswcFileList){
                            val pic = item.url//ApiConstants.BASE_URL + i.path
                            val s1 = pic.replace("\\", "/")
                            pathList.add(s1)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@HBJCDetailActivity)
//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                            /*val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
//                            intent.putExtra("url", s1)
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                        hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                startActivity(intent, options)
//                            } else {
//                                startActivity(intent)
//                                overridePendingTransition(R.anim.fade_in,
//                                        R.anim.fade_out)
//                            }*/
//                        }else{
////                            val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
////                            startActivity(intent)
//                            val intent = Intent(Intent.ACTION_VIEW);
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }
                    }
                }else{
                    /*remove(helper.layoutPosition)
                    notifyDataSetChanged()*/
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }
                if (AppCache.getInstance().duties == 1){
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.GONE
                }
            }
        }
        hbjcdetail_yswc_rlv.adapter = yswcFileAdapter
        var dshbh = ArrayList<PjEnviorRejectedEntity>()
        var yshbh = ArrayList<PjEnviorRejectedEntity>()
        for (i in updateEntity.enviorRejectedEntities){
            if (i.type == 1){
                dshbh.add(i)
            }
            if (i.type == 2){
                yshbh.add(i)
            }
        }
        if (dshbh.size>0){
            ll_hbjcdetail_bhxx.visibility = View.VISIBLE
            tv_hbjcdetail_bhcs.setText("驳回信息(驳回"+dshbh.size+"次)")
            hbjcdetail_bhxx_rlv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            hbjcdetail_bhxx_rlv.adapter = object :BaseQuickAdapter<PjEnviorRejectedEntity,BaseViewHolder>(R.layout.item_hbjc_bhxx,dshbh){
                override fun convert(helper: BaseViewHolder?, item: PjEnviorRejectedEntity?) {
                    val et_item_bhxx_clr = helper!!.getView<TextView>(R.id.et_item_bhxx_clr)
                    val et_item_bhxx_clrdh = helper!!.getView<TextView>(R.id.et_item_bhxx_clrdh)
                    val et_item_bhxx_clsj = helper!!.getView<TextView>(R.id.et_item_bhxx_clsj)
                    val et_item_bhxx_clbz = helper!!.getView<TextView>(R.id.et_item_bhxx_clbz)
                    val tv_item_bhxx_bhcs = helper!!.getView<TextView>(R.id.tv_item_bhxx_bhcs)
                    val ll_item_bhxx_bhcs = helper!!.getView<LinearLayout>(R.id.ll_item_bhxx_bhcs)
                    val et_item_bhxx_bhpj = helper!!.getView<TextView>(R.id.et_item_bhxx_bhpj)
                    val et_item_bhxx_bhsj = helper!!.getView<TextView>(R.id.et_item_bhxx_bhsj)
                    val et_item_bhxx_bhr = helper!!.getView<TextView>(R.id.et_item_bhxx_bhr)
                    val et_item_bhxx_bhrdh = helper!!.getView<TextView>(R.id.et_item_bhxx_bhrdh)
                    val et_item_bhxx_remark = helper!!.getView<TextView>(R.id.et_item_bhxx_bhremark)
                    val rlv_item_bhxx_clwj = helper!!.getView<RecyclerView>(R.id.rlv_item_bhxx_clwj)
                    val rlv_item_bhxx_xgwj = helper!!.getView<RecyclerView>(R.id.rlv_item_bhxx_xgwj)
                    tv_item_bhxx_bhcs.setText("第"+(helper.adapterPosition+1)+"次")
                    et_item_bhxx_clr.setText(item!!.ksname)
                    et_item_bhxx_clrdh.setText(item!!.kstel)
                    et_item_bhxx_clsj.setText(item!!.cltime)
                    et_item_bhxx_clbz.setText(item!!.ksbz)
                    et_item_bhxx_bhpj.setText(item!!.bhyj)
                    et_item_bhxx_bhsj.setText(item!!.bhtime)
                    et_item_bhxx_bhr.setText(item!!.bhr)
                    et_item_bhxx_bhrdh.setText(item!!.bhtel)
                    et_item_bhxx_remark.setText(item!!.bhbz)
                    tv_item_bhxx_bhcs.setOnClickListener {
                        if (ll_item_bhxx_bhcs.isShown){
                            ll_item_bhxx_bhcs.visibility = View.GONE
                        }else{
                            ll_item_bhxx_bhcs.visibility = View.VISIBLE
                        }
                    }
//                    rlv_item_bhxx_xgwj.layoutManager = LinearLayoutManager(this@HBJCDetailActivity,LinearLayoutManager.VERTICAL,false)
                    rlv_item_bhxx_clwj.layoutManager = object :GridLayoutManager(this@HBJCDetailActivity,3){
                        override fun canScrollVertically(): Boolean {
                            return false
                        }
                    }
                    rlv_item_bhxx_xgwj.layoutManager = object :GridLayoutManager(this@HBJCDetailActivity,3){
                        override fun canScrollVertically(): Boolean {
                            return false
                        }
                    }
                    var clwjList = ArrayList<PjEnviorRejectedFile>()
                    var bhwjList = ArrayList<PjEnviorRejectedFile>()
                    for (i in item!!.enviorRejectedFiles){
                        if (i.filetype==3){
                            clwjList.add(i)
                        }
                    }
                    for (i in item!!.enviorRejectedFiles){
                        if (i.filetype==4||i.filetype==100){
                            bhwjList.add(i)
                        }
                    }
                    rlv_item_bhxx_clwj.adapter = object : BaseQuickAdapter<PjEnviorRejectedFile, BaseViewHolder>(R.layout.item_teng_tui_photo, clwjList) {
                        override fun convert(helper: BaseViewHolder, item: PjEnviorRejectedFile) {
                            val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                            val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                            //                AppApplication.getGlideUrl(s1)
                            val pic: String = item.url//ApiConstants.BASE_URL + item.path
                            val s1 = pic.replace("\\", "/")
                            Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                                    .into(view)
                            helper.setText(R.id.tv_teng_photo_name, item.name)
                            helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility=View.GONE
                            helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                                //                                    Toast.makeText(this@HBJCDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                                // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                                var builder = AlertDialog.Builder(this@HBJCDetailActivity)
// 设置Title的图标
                                builder.setIcon(R.mipmap.ic_launcher)
// 设置Title的内容
                                builder.setTitle("弹出警告框")
// 设置Content来显示一个信息
                                builder.setMessage("确定删除吗？")
// 设置一个PositiveButton
                                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                    /* @Override
                                     public void onClick(DialogInterface dialog, int which)
                                     {
                                         Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                                     }*/

                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        //deletePic(item.id)
                                    }
                                });
// 设置一个NegativeButton
                                builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                                    /*  @Override
                                      public void onClick(DialogInterface dialog, int which)
                                      {
                                          Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                                      }*/

                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        dialog!!.dismiss()
                                    }
                                });
                                builder.show()

                            }
                            helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                                var pathList = ArrayList<String>()
                                for (i in clwjList){
                                    val pic = item.url//ApiConstants.BASE_URL + i.path
                                    val s1 = pic.replace("\\", "/")
                                    pathList.add(s1)
                                }
                                PhotoPreview.builder()
                                        .setPhotos(pathList)
                                        .setCurrentItem(helper.adapterPosition)
                                        .setShowDeleteButton(false)
                                        .start(this@HBJCDetailActivity)
//                                if (item.name.contains(".png")||item.name.contains(".jpg")){
//                                    val pic = ApiConstants.BASE_URL + item.path
//                                    val s1 = pic.replace("\\", "/")
//                                    var pathList = ArrayList<String>()
//                                    pathList.add(s1)
//                                    PhotoPreview.builder()
//                                            .setPhotos(pathList)
//                                            .setCurrentItem(0)
//                                            .setShowDeleteButton(false)
//                                            .start(this@HBJCDetailActivity)
//                                   /* val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
////                                    val pic = ApiConstants.BASE_URL + serializableExtra!!.pjEnviorFileEntities[helper.adapterPosition].path
//                                    intent.putExtra("url", s1)
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                        val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                                hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                        startActivity(intent, options)
//                                    } else {
//                                        startActivity(intent)
//                                        overridePendingTransition(R.anim.fade_in,
//                                                R.anim.fade_out)
//                                    }*/
//                                }else{
////                            val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
////                            startActivity(intent)
//                                    val intent = Intent(Intent.ACTION_VIEW);
//                                    val type = "video/* ";
//
//                                    val uri = Uri.parse(s1)
//                                    intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                                    startActivity(intent)
//                                }
                            }
                        }

                    }
                    rlv_item_bhxx_xgwj.adapter = object : BaseQuickAdapter<PjEnviorRejectedFile, BaseViewHolder>(R.layout.item_teng_tui_photo, bhwjList) {
                        override fun convert(helper: BaseViewHolder, item: PjEnviorRejectedFile) {
                            val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                            val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                            //                AppApplication.getGlideUrl(s1)
                            val pic: String = item.url//ApiConstants.BASE_URL + item.path
                            val s1 = pic.replace("\\", "/")
                            Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                                    .into(view)
                            helper.setText(R.id.tv_teng_photo_name, item.name)
                            helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                                //                                    Toast.makeText(this@HBJCDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                                // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                                var builder = AlertDialog.Builder(this@HBJCDetailActivity)
// 设置Title的图标
                                builder.setIcon(R.mipmap.ic_launcher)
// 设置Title的内容
                                builder.setTitle("弹出警告框")
// 设置Content来显示一个信息
                                builder.setMessage("确定删除吗？")
// 设置一个PositiveButton
                                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                    /* @Override
                                     public void onClick(DialogInterface dialog, int which)
                                     {
                                         Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                                     }*/

                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        //deletePic(item.id)
                                    }
                                });
// 设置一个NegativeButton
                                builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                                    /*  @Override
                                      public void onClick(DialogInterface dialog, int which)
                                      {
                                          Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                                      }*/

                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        dialog!!.dismiss()
                                    }
                                });
                                builder.show()

                            }
                            helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                                var pathList = ArrayList<String>()
                                for (i in bhwjList){
                                    val pic = item.url//ApiConstants.BASE_URL + i.path
                                    val s1 = pic.replace("\\", "/")
                                    pathList.add(s1)
                                }
                                PhotoPreview.builder()
                                        .setPhotos(pathList)
                                        .setCurrentItem(helper.adapterPosition)
                                        .setShowDeleteButton(false)
                                        .start(this@HBJCDetailActivity)
//                                if (item.name.contains(".png")||item.name.contains(".jpg")){
//                                    val pic = ApiConstants.BASE_URL + item.path
//                                    val s1 = pic.replace("\\", "/")
//                                    var pathList = ArrayList<String>()
//                                    pathList.add(s1)
//                                    PhotoPreview.builder()
//                                            .setPhotos(pathList)
//                                            .setCurrentItem(0)
//                                            .setShowDeleteButton(false)
//                                            .start(this@HBJCDetailActivity)
//                                    /*val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
////                                    val pic = ApiConstants.BASE_URL + serializableExtra!!.pjEnviorFileEntities[helper.adapterPosition].path
//                                    intent.putExtra("url", s1)
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                        val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                                hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                        startActivity(intent, options)
//                                    } else {
//                                        startActivity(intent)
//                                        overridePendingTransition(R.anim.fade_in,
//                                                R.anim.fade_out)
//                                    }*/
//                                }else{
////                            val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
////                            startActivity(intent)
//                                    val intent = Intent(Intent.ACTION_VIEW);
//                                    val type = "video/* ";
//
//                                    val uri = Uri.parse(s1)
//                                    intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                                    startActivity(intent)
//                                }
                            }
                        }

                    }
                }}

        }else{
            ll_hbjcdetail_bhxx.visibility = View.GONE
        }

        if (serializableExtra!!.location != null && !serializableExtra!!.location.equals("")) {
            /*aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(getCenter(serializableExtra!!.location), 16f, 0f, 0f)))
            aMap!!.addMarker(MarkerOptions().position(getCenter(serializableExtra!!.location)))*/
            if (!serializableExtra!!.location.equals("")) {

                /*aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(getCenter(item!!.location), 16f, 0f, 0f)))
                aMap!!.addMarker(MarkerOptions().position(getCenter(item!!.location)))*/
                val center1 = getCenter(serializableExtra!!.location)
                try {
                    aMap?.addMarker(MarkerOptions().position(center1))
                    butTzGddt.setOnClickListener {
                        if (AppInstalledUtils.isAppInstalled(this, "com.autonavi.minimap")){//跳转高德地图
                            var intent = Intent()
                            intent.action = Intent.ACTION_VIEW
                            intent.addCategory(Intent.CATEGORY_DEFAULT)
                            //获取本地存储的百度经纬度
                            //获取本地存储的百度经纬度
                           /* lon = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lon", String("4.9E-324")) as String)
                            lat = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lat", String("4.9E-324")) as String)
                            var point: MyLatLngPoint? = null
                            if (lon !== 4.9E-324 && lat !== 4.9E-324) {
                                point = MyLatLngPoint(lat, lon)
                                point = CoordMath.bd2gcj(point)
                            } else {
                                lat = 28.208915
                                lon = 112.985274
                                point = MyLatLngPoint(lat, lon)
                            }
                            var point1: MyLatLngPoint? = null
                            if (shopLat !== 0 && shopLon !== 0) {
                                point1 = MyLatLngPoint(shopLat, shopLon)
                                point1 = CoordMath.bd2gcj(point1)
                            }*/
                            val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
//                            val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84转火星
                            val transform = PositionUtil.wgs84togcj02(center1.longitude, center1.latitude)//84转火星  center1.latitude
//                            val point = LatLng(model.wgLat, model.wgLon)
                            val uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + aMapLocation!!.latitude  + "&slon=" + aMapLocation!!.longitude + "&sname=" + "自己位置" + "&did=BGVIS2&dlat=" + transform[0].toString() + "&dlon=" + transform[1].toString() + "&dname=" + "目的地" + "&dev=0&t=0")
                            intent.data = uri
                            //启动该页面即可
                            startActivity(intent)
                        }else if (AppInstalledUtils.isAppInstalled(this, "com.baidu.BaiduMap")){//跳转百度地图
                            var intent = Intent()
                            /*lon = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lon", String("4.9E-324")) as String?)
                            lat = java.lang.Double.valueOf(SPUtils.get(MyApplication.getContext(), "lat", String("4.9E-324")) as String?)
                            if (lon !== 4.9E-324 && lat !== 4.9E-324) {
                                intent.data = Uri.parse("baidumap://map/direction?region=湖南&origin=" + lat.toString() + "," + lon.toString() + "&destination=" + address.toString() + "&mode=driving")
                            } else {
                                intent.data = Uri.parse("baidumap://map/direction?region=湖南&origin=" + MyUrl.getAddressMessage().toString() + "&destination=" + address.toString() + "&mode=driving")
                            }*/
                            /*val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)
                            val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84转火星
                            val model = PositionUtil.marsTobaidu(aMapLocation!!.latitude, aMapLocation!!.longitude)*/


                            intent.data = Uri.parse("baidumap://map/direction?region=目的地&destination=" + center1.latitude.toString() + "," + center1.longitude.toString() + "&mode=driving" +//+ "&destination=" + ""
                                    "&coord_type=wgs84")
                            startActivity(intent)
                        }else{
                            ToastUtils.showShort("请安装百度地图或高德地图")

                        }

                    }
//                    aMap!!.animateCamera(CameraUpdateFactory.changeLatLng(center1))
                    aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center1, 16f, 0f, 0f)))
                }finally {

                    //然后可以移动到定位点,使用animateCamera就有动画效果
                    Handler().postDelayed(Runnable {
                        kotlin.run {
                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center1, 16f, 0f, 0f)))
                        }
                        /* public void run() {
                             //要执行的任务
                         }*/
                    }, 600);

                    Handler().postDelayed(Runnable {
                        kotlin.run {
                            aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center1, 16f, 0f, 0f)))
                        }
                        /* public void run() {
                             //要执行的任务
                         }*/
                    }, 900);

                }

            }

        }
        /*if (AppCache.getInstance().type!=4&&serializableExtra!!.qutype==1){
            filetype = 2
        }else{
            filetype = serializableExtra!!.qutype
        }*/
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){
            hbjcdetail_dcl_btn.visibility = View.GONE
            hbjcdetail_nyth_btn.visibility = View.GONE
        }
        filetype = serializableExtra!!.qutype
        /*if (serializableExtra!!.qutype==0){
            filetype = 1
        }else if (serializableExtra!!.qutype!=1){
            filetype = serializableExtra!!.qutype-1
        }else{
            filetype = serializableExtra!!.qutype
        }*/
        /*if (serializableExtra!!.qutype==0||serializableExtra!!.qutype==1){
            filetype = 1
        }else if (serializableExtra!!.qutype == 2){
            filetype = 4
        }else if (serializableExtra!!.qutype == 3){
            filetype = 2
        }else if (serializableExtra!!.qutype == 4){
            filetype = 3
        }else if (serializableExtra!!.qutype == 5){
            filetype = 5
        }*/

        initTlv(filetype)
        et_hbjcdetail_wz.setText(serializableExtra!!.cnwText)
        et_hbjcdetail_sfzdwt.setText(serializableExtra!!.zdwtText)
        et_hbjcdetail_sfgddw.setText(serializableExtra!!.gddwText)
        val hjzzyjflText = serializableExtra!!.hjzzyjflText.toString()

        et_hbjcdetail_hjzz.setText(hjzzyjflText)
        val hjzzej = serializableExtra!!.getHjzzej()

        val hjzzsj = serializableExtra!!.getHjzzsj()
        if (hjzzsj!=-1){
            ll_hbjcdetail_hjzzsjfl.visibility = View.VISIBLE
            et_hbjcdetail_hjzzsjfl.setText(serializableExtra!!.hjzzsjText)
        }

        et_hbjcdetail_zgyq.setText(serializableExtra!!.zgyq)

        et_hbjcdetail_clsj.setText(serializableExtra!!.kstime)
        et_hbjcdetail_clqx.setText(serializableExtra!!.qxtime)
        et_hbjcdetail_xfjy.setText(serializableExtra!!.xfjy)


        if (!serializableExtra!!.comment.equals("")&&AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){
            ll_hbjcdetail_nebz.visibility = View.VISIBLE
            et_hbjcdetail_nybhyy.setText(serializableExtra!!.comment)
        }
        et_hbjcdetail_xzq.setText(serializableExtra!!.xzqmc)
        et_hbjcdetail_address.setText(serializableExtra!!.wz)
        et_hbjcdetail_problem.setText(serializableExtra!!.questions)
        et_hbjcdetail_jlr.setText(serializableExtra!!.jlr)
        et_hbjcdetail_phone.setText(serializableExtra!!.jlrtel)
        et_hbjcdetail_time.setText(serializableExtra!!.jltime)
        et_hbjcdetail_jlremark.setText(serializableExtra!!.remark)

        et_hbjcdetail_zgyq.setText(serializableExtra!!.zgyq)
        et_hbjcdetail_clr.setText(serializableExtra!!.ksname)
        et_hbjcdetail_clr_phone.setText(serializableExtra!!.kstel)
        et_hbjcdetail_clremark.setText(serializableExtra!!.ksbz)

        et_hbjcdetail_yspj.setText(serializableExtra!!.cgpj)
        et_hbjcdetail_ysr.setText(serializableExtra!!.cgname)
        et_hbjcdetail_ysdh.setText(serializableExtra!!.cgtel)
        et_hbjcdetail_ystime.setText(serializableExtra!!.cgtime)
        et_hbjcdetail_ysremark.setText(serializableExtra!!.cgbz)

        if (filetype == 0||filetype == 1) {//待处理  || serializableExtra!!.qutype==50
            inlcude_act_rjhj_hbdetails_xc.visibility = View.VISIBLE
            iv_act_rjhj_hbdetails_xc.setImageResource(R.drawable.progress_blue)

            hbjcdetail_update_btn.visibility = View.VISIBLE
            hbjcdetail_dcl_btn.visibility = View.VISIBLE
            hbjcdetail_nyth_btn.visibility = View.VISIBLE

            hbjcdetail_xf_btn.visibility = View.GONE

            hbjcdetail_dsh_bh_btn.visibility = View.GONE
        }else if (filetype == 3) {
            inlcude_act_rjhj_hbdetails_zg.visibility = View.VISIBLE
            iv_act_rjhj_hbdetails_zg.setImageResource(R.drawable.progress_blue)

            hbjcdetail_update_btn.visibility = View.GONE
            hbjcdetail_dcl_btn.visibility = View.GONE
            hbjcdetail_nyth_btn.visibility = View.GONE

            hbjcdetail_xf_btn.visibility = View.VISIBLE

            hbjcdetail_dsh_bh_btn.visibility = View.GONE
        } else if (filetype == 4){
            inlcude_act_rjhj_hbdetails_sh.visibility = View.VISIBLE
            iv_act_rjhj_hbdetails_sh.setImageResource(R.drawable.progress_blue)

            hbjcdetail_update_btn.visibility = View.GONE
            hbjcdetail_dcl_btn.visibility = View.GONE
            hbjcdetail_nyth_btn.visibility = View.GONE

            hbjcdetail_xf_btn.visibility = View.GONE

            hbjcdetail_dsh_bh_btn.visibility = View.VISIBLE
            hbjcdetail_dsh_xz_btn.visibility = View.VISIBLE


        }else if (filetype == 5){
            inlcude_act_rjhj_hbdetails_xz.visibility = View.VISIBLE
            iv_act_rjhj_hbdetails_xz.setImageResource(R.drawable.progress_blue)

            hbjcdetail_update_btn.visibility = View.GONE
            hbjcdetail_dcl_btn.visibility = View.GONE
            hbjcdetail_nyth_btn.visibility = View.GONE

            hbjcdetail_xf_btn.visibility = View.GONE

            hbjcdetail_dsh_bh_btn.visibility = View.GONE
            hbjcdetail_xz_bh_btn.visibility = View.GONE
            hbjcdetail_dsh_xz_btn.visibility = View.GONE
        }



        if ((AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF)&& serializableExtra!!.qutype==3){
//            hbjcdetail_ch_btn.visibility = View.VISIBLE
            hbjcdetail_cxbh_btn.visibility = View.VISIBLE
        }
        if ((AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF)&& serializableExtra!!.qutype>1){

            hbjcdetail_update_btn.visibility = View.GONE
            hbjcdetail_dcl_btn.visibility = View.GONE
        }
        if (AppCache.getInstance().type == ApiConstants.RJHJ_WY){// 判断如果是村镇隐藏外业名称、联系电话、驳回和验收按钮
            ll_hbjcdetail_jlr.visibility = View.GONE
            ll_hbjcdetail_phone.visibility = View.GONE

        }
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){
            hbjcdetail_xf_btn.visibility = View.GONE
            hbjcdetail_dsh_bh_btn.visibility = View.GONE
//            hbjcdetail_yswc_btn.visibility = View.GONE
            if (AppCache.getInstance().name.equals(serializableExtra!!.jlr)){
                hbjcdetail_update_btn.visibility = View.VISIBLE
            }else{
                hbjcdetail_update_btn.visibility = View.GONE
            }

        }
        if ((AppCache.getInstance().type==ApiConstants.RJHJ_NY||
                        AppCache.getInstance().type==ApiConstants.LDRK_ZZF)){//判断如果是内业显示修改按钮
//            hbjcdetail_xf_btn.visibility = View.GONE
            if (filetype == 1){
                hbjcdetail_zjys_btn.visibility = View.VISIBLE
            }
        }
        if (AppCache.getInstance().duties == 1){//AppCache.getInstance().type == 5||//菜单控制
            hbjcdetail_update_btn.visibility = View.GONE
            hbjcdetail_dcl_btn.visibility = View.GONE
            hbjcdetail_nyth_btn.visibility = View.GONE
            hbjcdetail_zjys_btn.visibility = View.GONE
//            hbjcdetail_ch_btn.visibility = View.GONE
            hbjcdetail_cxbh_btn.visibility = View.GONE
            hbjcdetail_xf_btn.visibility = View.GONE
            hbjcdetail_dsh_bh_btn.visibility = View.GONE
//            hbjcdetail_yswc_btn.visibility = View.GONE
            hbjcdetail_dsh_xz_btn.visibility = View.GONE
            hbjcdetail_xz_bh_btn.visibility = View.GONE
//            hbjcdetail_xz_btn.visibility = View.GONE
        }

    }

    override fun returnRjhjPoint(ylPointEntity: RjhjBean) {

    }

    override fun returnQueryCun(message: List<CjEntity>, pjContract: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText) {
        if (rlv_xzxzq!=null){
            if (message.size==1){
                rlv_xzxzq.visibility = View.GONE
                pjContract.wz = message.get(0).xzqmc
                pjContract.xzqmc = message.get(0).xzqmc
                pjContract.code = message.get(0).code
                tvWzenvironmental.setText("")
                tvWzenvironmental.hint = message.get(0).zhen+" "+message.get(0).xzqmc
            }else if (message.size>0){

                rlv_xzxzq.visibility = View.VISIBLE
                rlv_xzxzq.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                rlv_xzxzq.adapter = object:BaseQuickAdapter<CjEntity,BaseViewHolder>(R.layout.item_xuan_xzqmc,message){
                    override fun convert(helper: BaseViewHolder?, item: CjEntity?) {
                        helper!!.setText(R.id.tv_xuan_xzq, item!!.zhen+" "+item!!.xzqmc)
                        helper.itemView.setOnClickListener {
                            tvWzenvironmental.setText("")
                            tvWzenvironmental.hint = item!!.zhen+" "+item!!.xzqmc
                            pjContract.wz = item.xzqmc
                            pjContract.xzqmc = item.xzqmc
                            pjContract.code = item.code
                            rlv_xzxzq.visibility = View.GONE
                        }
                    }
                }

            } else{
                rlv_xzxzq.visibility = View.GONE
                ToastUtils.showShort("无此村名")
            }
        }
        var s=""
        var code = ""
        var cunmign = ""
        var a =false
        for ( i in message){
            s= s+i.xzqmc+"、"
            if (i.xzqmc.equals(pjContract.wz)){
                a = true
                cunmign = i.xzqmc
                code = i.code
            }
        }
        if (a){

        }else{
            ToastUtils.showShort("位置是否是："+s)
        }

    }

    override fun returnFallBackQutype(message: String) {
        ToastUtils.showShort(message)
        AppCache.getInstance().isUpdateWt = 1
        finish()
    }

    //跳转拨打电话
    fun callPhone( phoneNum:String) {
        val split = phoneNum.split("、")
        /* val list = ArrayList<String>();
         val array = String[list.size];

 list.toArray(array);*/
        if (split.size==1){
            var intent = Intent(Intent.ACTION_DIAL);
            var data = Uri.parse("tel:" + phoneNum);
            intent.setData(data);
            startActivity(intent);
        }else{
            AlertDialog.Builder(this)
                    .setTitle("请选择拨出号码")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setSingleChoiceItems(split.toTypedArray(), 0,
                            object : DialogInterface.OnClickListener {

                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    var intent = Intent(Intent.ACTION_DIAL);
                                    var data = Uri.parse("tel:" + split.get(p1));
                                    intent.setData(data);
                                    startActivity(intent);
                                    p0!!.dismiss()
                                }
                            }
                    ) .setNegativeButton("取消", null)
                    .show();
        }
        /*.setPositiveButton(object :OnClic)
        .setNegativeButton("取消", null)*/

    }

    private fun setTime(editText: TextView) {
        var year = Calendar.getInstance().get(Calendar.YEAR)//[Calendar.YEAR];
        var month = Calendar.getInstance().get(Calendar.MONTH)//[Calendar.MONTH];
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)//[Calendar.DAY_OF_MONTH];
        if (!editText.text.toString().equals("")){
            val toString = editText.text.toString()
            val split = toString.split("-")
            year = split[0].toInt()
            month = split[1].toInt()-1
            day = split[2].toInt()

        }
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            var nian = ""
            var yue = ""
            var ri = ""
            nian = year.toString() + ""
            if (month < 9) {
                yue = "0" + (month + 1)
            } else {
                yue = "" + (month + 1)
            }
            if (dayOfMonth < 10) {
                ri = "0" + dayOfMonth;
            } else {
                ri = "" + dayOfMonth;
            }

//            val format: String = CalendarUtil.DATE_FORMAT_HOUR_SE.format(Date()) //$format"  $format"
//                editText.setText("$i-$yue-$ri");
            editText.setText(nian + "-" + yue + "-" + ri);
            uploadDate = nian + "-" + yue + "-" + ri

            //            uploadDate = "$i-$yue-$ri"
        }, year, month, day)
//        val datePicker = datePickerDialog.datePicker
//        datePicker.maxDate = System.currentTimeMillis()+1000 ///< 设置日期的上限日期
        datePickerDialog.show()
    }


    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_hbjcdetail?.map
        }
        setUpMap()
    }

    val sdCardPath = AppMenus.getInstance().cardPath
    private fun addOverLayer(layers: List<LayerEntity>) {

        var MAP_DK = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=FALSE&layers=bcjcxt:bcjc_kongbai&styles=&srs=EPSG:3857&format=image%2Fpng&bbox=";
        var path_dk = sdCardPath + "BMS/map/zhglxt/bcjc_kongbai/";
        var layer_dk = HeritageScopeTileProvider(MAP_DK, path_dk);
        var opt_dk = TileOverlayOptions().tileProvider(layer_dk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_dk).diskCacheSize(10 * 1024);
        aMap?.addTileOverlay(opt_dk);
        for (l in layers) {
            if (l.name == "天地图"){
                if (l.isCheck){
                    aMap!!.addTileOverlay(opt_tdtStaNomal)
                    aMap!!.addTileOverlay(opt_tdtnN)
                }
            }else if (l.name == "影像图"){
                if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtSta)
                    aMap!!.addTileOverlay(opt_tdtStaN)
                }
            }else if (l.name == "底图"){
                if (l.isCheck) {
                    aMap!!.addTileOverlay(opt_tdtOsm)
                }
            } else {
                if (l.isCheck)
                    aMap!!.addTileOverlay(l.opt)
            }
        }
        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() {
        //设置默认定位按钮是否显示
        aMap?.uiSettings?.isMyLocationButtonEnabled = false
        aMap?.uiSettings?.isScaleControlsEnabled = false
        aMap?.uiSettings?.isZoomControlsEnabled = false
//        aMap?.setMapLanguage(AMap.CHINESE)
        aMap?.mapType = AMap.MAP_TYPE_NORMAL

        aMap?.isMyLocationEnabled = false// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap!!.setOnCameraChangeListener(object :AMap.OnCameraChangeListener {
            override fun onCameraChangeFinish(p0: CameraPosition?) {

            }

            override fun onCameraChange(p0: CameraPosition?) {
                if (p0!!.zoom>18.5){
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(18.5f))
                }
            }
        })
    }

    override fun onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        super.onResume()
        map_hbjcdetail.onResume()
    }



    override fun onPause() {
        super.onPause()
        map_hbjcdetail.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_hbjcdetail.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun getCenter(center: String): LatLng {
        if (center != null) {
            val points = center.substring(6, center.length - 1).split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            if (points != null && points.size > 1) {
                val converter = CoordinateConverter()
                // CoordType.GPS 待转换坐标类型
                converter.from(CoordinateConverter.CoordType.GPS)
                val sl = LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]))
                // sourceLatLng待转换坐标点 LatLng类型
                converter.coord(sl)
                val latLng = converter.convert()
                return sl
            } else {
                return LatLng(0.0, 0.0)
            }
        }

        return LatLng(0.0, 0.0)
    }


    override fun returnEnvironmental(cash: List<PjEnviorSupvsEntity>) {
    }

    override fun returnAddEnvironmental(cash: String) {
        ToastUtils.showShort(cash)
        if (envirorUpPopu!=null){
            envirorUpPopu!!.dismiss()
        }
        AppCache.getInstance().isUpdateWt = 1
        finish()
    }

    override fun returnTuiEnvironmental(cash: BaseRespose<String>) {
    }

    override fun returnDeleteEnvironmental(cash: BaseRespose<*>) {
    }

    override fun returnDeletesEnvironmental(cash: BaseRespose<*>) {
    }

    override fun returnDeleteFileEnvironmental(cash: BaseRespose<*>) {
        dclFileList.removeAt(dclFileList.indexOf(deletePjEnviorFileEntity))
        if (dcl_FileList!=null)
        dcl_FileList.removeAt(dcl_FileList.indexOf(deletePjEnviorFileEntity))
        ToastUtils.showShort("删除成功")
        dclFileAdapter!!.setNewData(dclFileList)
        if (dclFileAdapter1!=null)
        dclFileAdapter1!!.setNewData(dcl_FileList)
//        mPresenter.getRjhjPoint(serializableExtra!!.location)
    }

    override fun returnUpdateEnvironmental(cash: BaseRespose<*>) {
    }

    override fun returnUploadEnvironmental(cash: BaseRespose<*>) {
    }

    override fun returnListEnvironmental(updateEntity: PjEnviorSupvsEntity) {
    }

    override fun returnRyEnvironmental(updateEntity: List<SysUserEntity>) {
        sysUserEntityList.addAll(updateEntity)//= updateEntity as ArrayList<SysUserEntity>
        for (i in updateEntity){
            userList.add(i.name)
        }
        var userName=""
        for (i in sysUserEntityList){
            if (i.userId.toInt()==serializableExtra!!.jsry){
                userName = i.name
            }

        }

    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
        fileIdList.clear()
        selectedPhotos.clear()
        selectedPhotos_new.clear()
        selectedVideos.clear()
        videoIdList.clear()
    }


    fun backToFront(view: View) {
//        finish()//继承了基类再改
        AppManager.getAppManager().finishActivity(this)
    }
    private fun initPopuEnvironNyth() {//内业退回
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_environmental_nyth, hbjcdetail_top)
        val contentView2: View = envirorUpPopu!!.getContentView()
        val tv_thyy_environmental = contentView2.findViewById<EditText>(R.id.tv_thyy_environmental)
        val bt_nyth_environmental = contentView2.findViewById<Button>(R.id.bt_nyth_environmental)

        bt_nyth_environmental.setOnClickListener {
            mPresenter.getFallBackQutype(serializableExtra!!.id,"0",tv_thyy_environmental.text.toString())
        }
    }
    //修改待处理弹框
    private fun initPopuEnvironUp(pjContract: PjEnviorSupvsEntity?) {
        selectedPhotos.clear()
        selectedPhotos_new.clear()
        selectedVideos.clear()
        videoIdList.clear()
        fileIdList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_environmental_up, hbjcdetail_top)
        val contentView2: View = envirorUpPopu!!.getContentView()
        tvUpDateEnviron = contentView2.findViewById(R.id.tv_up_date_environmental)
        //        EditText tvBhEnvironmental = contentView2.findViewById(R.id.tv_bh_environmental);
        val tv_environ_up_bt = contentView2.findViewById<TextView>(R.id.tv_environ_up_bt)
        val tvNameEnvironmental = contentView2.findViewById<EditText>(R.id.tv_name_environmental)
        //        EditText tvNameSection = contentView2.findViewById(R.id.tv_name_section);
        val tvJlrPhone = contentView2.findViewById<EditText>(R.id.tv_jlr_phone)
        val tvLrwtEnvironmental = contentView2.findViewById<EditText>(R.id.tv_lrwt_environmental)
        val tvZrdwEnvironmental = contentView2.findViewById<EditText>(R.id.tv_zrdw_environmental)
        val tvWtztEnvironmental = contentView2.findViewById<Spinner>(R.id.tv_wtzt_environmental)
        val tvJlrEnvironmental = contentView2.findViewById<EditText>(R.id.tv_jlr_environmental)
        val tvRemarkEnvironmental = contentView2.findViewById<EditText>(R.id.tv_remark_environmental)
        val butClearMeetingSummary = contentView2.findViewById<Button>(R.id.bt_clear_environmental)
        val tvUploadMeetingSummary = contentView2.findViewById<Button>(R.id.bt_upload_environmental)
        val llWtztEnvironmental = contentView2.findViewById<LinearLayout>(R.id.ll_wtzt_environmental)
        val tvWzenvironmental = contentView2.findViewById<EditText>(R.id.tv_wz_environmental)
        val tv_xfzgyq_environmental = contentView2.findViewById<EditText>(R.id.tv_xfzgyq_environmental)//整改要求
        val tv_clshijian_environmental = contentView2.findViewById<EditText>(R.id.tv_clshijian_environmental)//处理时间
        val tv_xfclsj_environmental = contentView2.findViewById<EditText>(R.id.tv_xfclsj_environmental)//处理期限
        val sp_clqx_environmental = contentView2.findViewById<Spinner>(R.id.sp_clqx_environmental)
        val tv_xfjy_environmental = contentView2.findViewById<EditText>(R.id.tv_xfjy_environmental)
       val tv_pop_environmental_xgwj = contentView2.findViewById<TextView>(R.id.tv_pop_environmental_xgwj)
       val recy_pop_environmental_xgwj1 = contentView2.findViewById<RecyclerView>(R.id.recy_pop_environmental_xgwj) //巡查修改相关文件
        val rlvPopEnvironmental: RecyclerView = contentView2.findViewById(R.id.recy_pop_environmental)

        val rlvPopVideoadd: RecyclerView = contentView2.findViewById(R.id.rlv_pop_video_add)
        val rlv_xzxzq: RecyclerView = contentView2.findViewById(R.id.rlv_xzxzq)//选择行政区

        val sp_cncw_environmental = contentView2.findViewById<Spinner>(R.id.sp_cncw_environmental)//村内村外
        val sp_sfgddw_environmental = contentView2.findViewById<Spinner>(R.id.sp_sfgddw_environmental)//是否固定点位
        val sp_sfzdwt_environmental = contentView2.findViewById<Spinner>(R.id.sp_sfzdwt_environmental)//是否重大问题
        val sp_jcssgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_jcssgh_environmental)//基础设施管护
        val sp_tynysyxwh_environmental = contentView2.findViewById<Spinner>(R.id.sp_tynysyxwh_environmental)//太阳能浴室运行维护
        val sp_hcqtgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_hcqtgh_environmental)//户厕清掏管护
        val sp_lhyh_environmental = contentView2.findViewById<Spinner>(R.id.sp_lhyh_environmental)//绿化养护
        val sp_jnlduyxwh_environmental = contentView2.findViewById<Spinner>(R.id.sp_jnlduyxwh_environmental)//节能路灯运行维护
        val sp_wttj_environmental = contentView2.findViewById<Spinner>(R.id.sp_wttj_environmental)//问题统计
        val sp_shljzl_environmental = contentView2.findViewById<Spinner>(R.id.sp_shljzl_environmental)//生活垃圾治理
        val sp_shwszl_environmental = contentView2.findViewById<Spinner>(R.id.sp_shwszl_environmental)//生活污水治理
        val sp_gcgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_gcgh_environmental)//公厕管护
        val sp_sdlj_environmental = contentView2.findViewById<Spinner>(R.id.sp_sdlj_environmental)//私搭乱建
        val sp_hjzz_environmental = contentView2.findViewById<Spinner>(R.id.sp_hjzz_environmental)//环境整治(一级分类)
        val sp_ejfl_environmental = contentView2.findViewById<Spinner>(R.id.sp_ejfl_environmental)//二级分类
        val sp_sjfl_environmental = contentView2.findViewById<Spinner>(R.id.sp_sjfl_environmental)//三级分类
        val tv_ejfl_environmental = contentView2.findViewById<TextView>(R.id.tv_ejfl_environmental)
//        val tv_sjfl_environmental = contentView2.findViewById<TextView>(R.id.tv_sjfl_environmental)
        val ll_sjfl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_sjfl_environmental)
        val ll_ejfl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_ejfl_environmental)

        val ll_gddw_envuronmental = contentView2.findViewById<LinearLayout>(R.id.ll_gddw_envuronmental)
        val ll_fgddw_envuronmental = contentView2.findViewById<LinearLayout>(R.id.ll_fgddw_envuronmental)
        val ll_tynysyxwh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_tynysyxwh_environmental)
        val ll_hcqtgh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_hcqtgh_environmental)
        val ll_lhyh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_lhyh_environmental)
        val ll_jnldyxwh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_jnldyxwh_environmental)
        val ll_shljzl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_shljzl_environmental)
        val ll_shwszl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_shwszl_environmental)
        val ll_gcgh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_gcgh_environmental)
        val ll_sdlj_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_sdlj_environmental)

        val ll_xfzgyq_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_xfzgyq_environmental)//整改问题
        val ll_clshijian_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_clshijian_environmental)//整改问题
        val ll_clsj_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_clsj_environmental)//整改问题
        val ll_xfjy_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_xfjy_environmental)//整改问题

        val ll_jlr_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_jlr_environmental)
        val ll_jlr_phone = contentView2.findViewById<LinearLayout>(R.id.ll_jlr_phone)

        val bt_temporary_environmental = contentView2.findViewById<Button>(R.id.bt_temporary_environmental)
        bt_temporary_environmental.visibility = View.GONE
        if (pjContract!!.hjzzyjfl==1||pjContract!!.hjzzyjfl==6||pjContract!!.hjzzyjfl==7){
            ll_xfzgyq_environmental.visibility = View.VISIBLE
            ll_clshijian_environmental.visibility = View.VISIBLE
            ll_clsj_environmental.visibility = View.VISIBLE
            ll_xfjy_environmental.visibility = View.VISIBLE
        }else{
            ll_xfzgyq_environmental.visibility = View.GONE
            ll_clshijian_environmental.visibility = View.GONE
            ll_clsj_environmental.visibility = View.GONE
            ll_xfjy_environmental.visibility = View.GONE
        }

        /*tv_clshijian_environmental.setText(CalendarUtil.DATE_FORMAT_DATE.format(Date()))
        if (sp_sfzdwt_environmental.selectedItemPosition==0){
            tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(5))
        }else{
            tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(3))
        }*/
        tv_clshijian_environmental.setText(pjContract!!.kstime)
        tv_xfclsj_environmental.setText(pjContract!!.qxtime)

        tv_clshijian_environmental.setOnClickListener {//选择处理时间
            setTime(tv_clshijian_environmental)
        }
        tv_xfclsj_environmental.setOnClickListener {//选择期限时间
            setTime(tv_xfclsj_environmental)
        }

        val clqxList = ArrayList<String>();//处理期限
        clqxList.add("1")
        clqxList.add("2")
        clqxList.add("3")
        clqxList.add("4")
        clqxList.add("5")
        clqxList.add("6")
        clqxList.add("7")
        clqxList.add("8")
        clqxList.add("9")
        clqxList.add("10")
        clqxList.add("11")
        clqxList.add("12")
        clqxList.add("13")
        clqxList.add("14")
        clqxList.add("15")
        val jclqxAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,clqxList)
        jclqxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_clqx_environmental.adapter = jclqxAdapter


        sp_clqx_environmental.setSelection(14,true)


        tv_xfzgyq_environmental.setText(pjContract!!.zgyq)//整改要求
        tv_xfjy_environmental.setText(pjContract.xfjy)//下发建议

        tvWzenvironmental.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!tvWzenvironmental.text.toString().equals(""))
                    mPresenter.getQueryCun(tvWzenvironmental.text.toString(), pjContract!!,rlv_xzxzq,tvWzenvironmental)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WY){//隐藏手机号和记录人
            ll_jlr_environmental.visibility = View.GONE
            ll_jlr_phone.visibility = View.GONE
        }
        recy_pop_environmental_xgwj1.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvPopEnvironmental.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvPopEnvironmental.adapter = photoAdapter

        videoAdapter = VideoAdapter(this, selectedVideos)
        rlvPopVideoadd.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvPopVideoadd.adapter = videoAdapter

        rlvPopEnvironmental.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity,20)//, this
                }
            }
        }))

        rlvPopVideoadd.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (videoAdapter!!.getItemViewType(position) === VideoAdapter.TYPE_ADD) {
                    video()
                } else {
                    //ToastUtils.showShort(selectedVideos.get(position))
                    val intent = Intent(Intent.ACTION_VIEW)
                    val path = selectedVideos.get(position)//该路径可以自定义
                    val file = File(path)
                    val uri = Uri.fromFile(file)
                    intent.setDataAndType(Uri.parse(path), "video/*")
                    startActivity(intent)

                }
            }
        }))
        dclFileAdapter1 = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, dcl_FileList) {
            override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                if (item.filetype == 1) {//filetype  ||item.filetype == 2||item.filetype == 50
                    //                AppApplication.getGlideUrl(s1)
                    val pic: String = item.url//ApiConstants.BASE_URL + item.path
                    val s1 = pic.replace("\\", "/")
                    Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                            .into(view)
                    helper.setText(R.id.tv_teng_photo_name, item.name)
                    if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR||(AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF))
                        helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@HBJCDetailActivity)
                        // 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
                        // 设置Title的内容
                        builder.setTitle("弹出警告框")
                        // 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
                        // 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
//                                deletePic(item.id)
                                deletePjEnviorFileEntity = item
                                mPresenter.deleteFileEnvironmental(item.id)
                            }
                        })
                        // 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            /*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*/
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        })
                        builder.show()
                    }
                    helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                        var pathList = ArrayList<String>()
                        for (i in dcl_FileList){
                            val pic = item.url//ApiConstants.BASE_URL + i.path
                            val s1 = pic.replace("\\", "/")
                            pathList.add(s1)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@HBJCDetailActivity)

//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                        }else{
//                            val intent = Intent(Intent.ACTION_VIEW);
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }

                    }
                }else{
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }
            }
        }
        recy_pop_environmental_xgwj1.adapter = dclFileAdapter1
        if (dcl_FileList!=null&&dcl_FileList.size>0){
            /*tv_pop_environmental_xgwj.visibility = View.VISIBLE
            recy_pop_environmental_xgwj1.visibility = View.VISIBLE*/

        }

        val hjzzList = ArrayList<BaobiaoBean>()//一级分类
        hjzzList.add(BaobiaoBean("人居环境检查",1))
        hjzzList.add(BaobiaoBean("太阳能浴室",2))
        hjzzList.add(BaobiaoBean("公厕管护",3))
        hjzzList.add(BaobiaoBean("绿化养护",4))
        hjzzList.add(BaobiaoBean("节能路灯",5))
        hjzzList.add(BaobiaoBean("农村大集环境检查",6))
        hjzzList.add(BaobiaoBean("垃圾暂存点",7))
        hjzzList.add(BaobiaoBean("户厕管护",8))
//        val hjzzAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,hjzzList)
        val hjzzAdapter = HbAdapter(this ,hjzzList)

//        hjzzAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)//simple_spinner_dropdown_item
        sp_hjzz_environmental.adapter = hjzzAdapter
//        val valuesEjfl = HjzzejEnum.values()//二级分类
        val valuesSjfl = HjzzsjEnum.values()//三级分类
//        val ejflList = ArrayList<BaobiaoBean>()//二级分类
        val sjflList = ArrayList<BaobiaoBean>()//三级分类
        /*if (pjContract!!.hjzzyjfl!=-1){
            addEjfl(valuesEjfl, ejflList,pjContract!!.hjzzyjfl.toString())//pjContract!!.hjzzyjflText
        }else{
            addEjfl(valuesEjfl, ejflList,"1")
        }*/
        var hjzzName = pjContract!!.hjzzyjflText
        if (hjzzName.equals("人居环境检查")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "1")
        }else if (hjzzName.equals("太阳能浴室")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "2")
        }else if (hjzzName.equals("公厕管护")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "3")
        } else if (hjzzName.equals("绿化养护")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "4")
        }else if (hjzzName.equals("节能路灯")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "5")
        } else if (hjzzName.equals("公厕管护")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "3")
        }else if (hjzzName.equals("农村大集环境检查")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "6")
        }else if (hjzzName.equals("垃圾暂存点")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "7")
        }else if (hjzzName.equals("户厕管护")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "8")
        }else{
            if (pjContract!!.hjzzej!=-1){
                addSjfl(valuesSjfl, sjflList,pjContract!!.hjzzejText)
            }else{
                addSjfl(valuesSjfl, sjflList,"101")
            }
        }

//        val ejflAdapter = HbAdapter(this ,ejflList)

//        sp_ejfl_environmental.adapter = ejflAdapter
        val sjflAdapter = HbAdapter(this ,sjflList)

        sp_sjfl_environmental.adapter = sjflAdapter
        if (!pjContract.hjzzsjText.equals("")){
//            tv_sjfl_environmental.setText(pjContract.hjzzyjflText)
//            sp_sjfl_environmental.setSelection(HjzzsjEnum.getIndex(), true)
            if (pjContract!!.hjzzsj != -1) {
                val baobiaoBean = BaobiaoBean(HjzzsjEnum.getName(pjContract!!.hjzzsj), pjContract!!.hjzzsj)

//            val indexHjzzej = ejflList.indexOf()
                var index = 0
                for (i in 0..sjflList.size - 1) {
                    if (Gson().toJson(sjflList.get(i)).equals(Gson().toJson(baobiaoBean))) {
                        index = i
                    }
                }
                sp_sjfl_environmental.setSelection(index, true)//indexHjzzej
            }
        }

        if (pjContract!!.hjzzyjfl!=-1){
            val baobiaoBean = BaobiaoBean(HjzzflEnum.getName(pjContract!!.hjzzyjfl), pjContract!!.hjzzyjfl)
//            val indexHjzzfl = hjzzList.indexOf(baobiaoBean)
            var index=0
            for (i in 0..hjzzList.size-1){
                if (Gson().toJson(hjzzList.get(i)).equals(Gson().toJson(baobiaoBean))){
                    index = i
                }
            }
//            indexHjzzfl
            sp_hjzz_environmental.setSelection(index,true)
            tv_environ_up_bt.setText(hjzzList.get(index).name)
        }


        val cncwList = ArrayList<String>();//村内村外
        cncwList.add("村内")
        cncwList.add("村外")
        val cncwAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,cncwList)
        cncwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_cncw_environmental.adapter = cncwAdapter
        val cnwText = pjContract!!.cnwText
        val indexOf = cncwList.indexOf(cnwText)
        sp_cncw_environmental.setSelection(indexOf,true)
        val sfgddwList = ArrayList<String>();//是否固定点位
        sfgddwList.add("是")
        sfgddwList.add("否")
        sfgddwList.add("创城")
        val sfgddwAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,sfgddwList)
        sfgddwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        sfgddwAdapter.setDatas(sfgddwList)
        sp_sfgddw_environmental.adapter = sfgddwAdapter
        val gddwText = pjContract!!.gddwText
        val indexOfgddw = sfgddwList.indexOf(gddwText)
        sp_sfgddw_environmental.setSelection(indexOfgddw,true)
        val sfzdwtAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,sfgddwList)
        sfzdwtAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        sfgddwAdapter.setDatas(sfgddwList)
        sp_sfzdwt_environmental.adapter = sfzdwtAdapter
        val zdwtText = pjContract!!.zdwtText
        val indexOfzdwt = sfgddwList.indexOf(zdwtText)
        sp_sfzdwt_environmental.setSelection(indexOfzdwt,true)
        sp_sfzdwt_environmental.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2==0){
                    sp_clqx_environmental.setSelection(4,true)
                }else{
                    sp_clqx_environmental.setSelection(2,true)
                }
                tv_clshijian_environmental.setText(TimeUtil.getDateAfter(0).toString())
                if (p2==0){
                    tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(7))
                }else{
                    tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(5))
                }
            }
        }
        if (sp_sfgddw_environmental.selectedItem.toString().equals("是")){
            ll_gddw_envuronmental.visibility = View.VISIBLE
            ll_fgddw_envuronmental.visibility = View.GONE
        }else{
            ll_gddw_envuronmental.visibility = View.GONE
            ll_fgddw_envuronmental.visibility = View.VISIBLE
        }
        sp_sfgddw_environmental.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {//ll_gddw_envuronmental
                if (p2==0){
                    ll_gddw_envuronmental.visibility = View.VISIBLE
                    ll_fgddw_envuronmental.visibility = View.GONE
                }else{
                    ll_gddw_envuronmental.visibility = View.GONE
                    ll_fgddw_envuronmental.visibility = View.VISIBLE
                }
            }
        }

        val jcssghList = ArrayList<String>();//基础设施管护
        jcssghList.add("太阳能浴室运行维护")
        jcssghList.add("公厕管护考核")
        jcssghList.add("绿化养护")
        jcssghList.add("节能路灯运行维护")
        jcssghList.add("农村大集")
        jcssghList.add("垃圾暂存点")
        val jcssghAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,jcssghList)
        jcssghAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        jcssghAdapter.setDatas(jcssghList)
        sp_jcssgh_environmental.adapter = jcssghAdapter
        sp_jcssgh_environmental.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2==0){
                    ll_tynysyxwh_environmental.visibility = View.VISIBLE
                    ll_hcqtgh_environmental.visibility = View.GONE
                    ll_lhyh_environmental.visibility = View.GONE
                    ll_jnldyxwh_environmental.visibility = View.GONE
                }else if (p2==1){
                    ll_tynysyxwh_environmental.visibility = View.GONE
                    ll_hcqtgh_environmental.visibility = View.VISIBLE
                    ll_lhyh_environmental.visibility = View.GONE
                    ll_jnldyxwh_environmental.visibility = View.GONE
                }else if (p2==2){
                    ll_tynysyxwh_environmental.visibility = View.GONE
                    ll_hcqtgh_environmental.visibility = View.GONE
                    ll_lhyh_environmental.visibility = View.VISIBLE
                    ll_jnldyxwh_environmental.visibility = View.GONE
                }else if (p2==3){
                    ll_tynysyxwh_environmental.visibility = View.GONE
                    ll_hcqtgh_environmental.visibility = View.GONE
                    ll_lhyh_environmental.visibility = View.GONE
                    ll_jnldyxwh_environmental.visibility = View.VISIBLE
                }
            }
        }
        val hjzzyjflText = pjContract!!.hjzzyjflText
        val indexOfhjzzyjfl = jcssghList.indexOf(hjzzyjflText)
        if (indexOfhjzzyjfl!=-1){
            sp_jcssgh_environmental.setSelection(indexOfhjzzyjfl,true)
        }
        val tynysyxwhList = ArrayList<String>();//太阳能浴室运行维护
        tynysyxwhList.add("专人管理")
        tynysyxwhList.add("管护制度")
        tynysyxwhList.add("浴室设施保持")
        tynysyxwhList.add("浴室环境卫生")
        tynysyxwhList.add("技术管理")
        val jtynysyxwhAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,tynysyxwhList)
        jtynysyxwhAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        jtynysyxwhAdapter.setDatas(tynysyxwhList)
        sp_tynysyxwh_environmental.adapter = jtynysyxwhAdapter
        val yswhText = pjContract!!.yswhText
        val indexOfyswh = tynysyxwhList.indexOf(yswhText)
        sp_tynysyxwh_environmental.setSelection(indexOfyswh,true)
        val hcqtghList = ArrayList<String>();//户厕清掏管护
        hcqtghList.add("管护制度")
        hcqtghList.add("管护队伍")
        hcqtghList.add("专业运输处理")
        hcqtghList.add("清掏及时性")//
        val hcqtghAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,hcqtghList)
        hcqtghAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        hcqtghAdapter.setDatas(hcqtghList)
        sp_hcqtgh_environmental.adapter = hcqtghAdapter
        val hcqtghText = pjContract!!.hcqtghText
        val indexOfhcqtgh = hcqtghList.indexOf(hcqtghText)
        sp_hcqtgh_environmental.setSelection(indexOfhcqtgh,true)
        val lhyhList = ArrayList<String>();//绿化养护
        lhyhList.add("管护制度")
        lhyhList.add("地搓站")
        lhyhList.add("日常管护内容")
        lhyhList.add("养护记录")
        val lhyhAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,lhyhList)
        lhyhAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        lhyhAdapter.setDatas(lhyhList)
        sp_lhyh_environmental.adapter = lhyhAdapter
        val lhyhText = pjContract!!.lhyhText
        val indexOflhyh = lhyhList.indexOf(lhyhText)
        sp_lhyh_environmental.setSelection(indexOflhyh,true)
        val jnldyxwhList = ArrayList<String>();//节能路灯维护
        jnldyxwhList.add("管护制度")
        jnldyxwhList.add("管护队伍")
        jnldyxwhList.add("亮灯率")
        jnldyxwhList.add("亮灯时长")
        jnldyxwhList.add("设施保持完好")
        jnldyxwhList.add("维修及时性")
        val jnldwhAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,jnldyxwhList)
        jnldwhAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        jnldwhAdapter.setDatas(jnldyxwhList)
        sp_jnlduyxwh_environmental.adapter = jnldwhAdapter
        val ldwhText = pjContract!!.ldwhText
        val indexOfldwh = jnldyxwhList.indexOf(ldwhText)
        sp_jnlduyxwh_environmental.setSelection(indexOfldwh,true)

        val wttjList = ArrayList<String>();//问题统计
        wttjList.add("生活垃圾治理")
        wttjList.add("生活污水治理")
        wttjList.add("公厕管护考核")
        wttjList.add("私搭乱建")
        wttjList.add("乱堆乱放")
        wttjList.add("乱贴乱画")
        val wttjAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,wttjList)
        wttjAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        jnldwhAdapter.setDatas(jnldyxwhList)
        sp_wttj_environmental.adapter = wttjAdapter
        val hjzzyjflText1 = pjContract!!.hjzzyjflText
        val indexOfwttj = wttjList.indexOf(hjzzyjflText1)
        sp_wttj_environmental.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2==0){
                    ll_shljzl_environmental.visibility = View.VISIBLE
                    ll_shwszl_environmental.visibility = View.GONE
                    ll_gcgh_environmental.visibility = View.GONE
                    ll_sdlj_environmental.visibility = View.GONE
                }else if (p2==1){
                    ll_shljzl_environmental.visibility = View.GONE
                    ll_shwszl_environmental.visibility = View.VISIBLE
                    ll_gcgh_environmental.visibility = View.GONE
                    ll_sdlj_environmental.visibility = View.GONE
                }else if (p2==2){
                    ll_shljzl_environmental.visibility = View.GONE
                    ll_shwszl_environmental.visibility = View.GONE
                    ll_gcgh_environmental.visibility = View.VISIBLE
                    ll_sdlj_environmental.visibility = View.GONE
                }else if (p2==3){
                    ll_shljzl_environmental.visibility = View.GONE
                    ll_shwszl_environmental.visibility = View.GONE
                    ll_gcgh_environmental.visibility = View.GONE
                    ll_sdlj_environmental.visibility = View.VISIBLE
                }else{
                    ll_shljzl_environmental.visibility = View.GONE
                    ll_shwszl_environmental.visibility = View.GONE
                    ll_gcgh_environmental.visibility = View.GONE
                    ll_sdlj_environmental.visibility = View.GONE
                }
            }
        }
        if (indexOfwttj!=-1){
            sp_wttj_environmental.setSelection(indexOfwttj,true)
        }
        val shljzlList = ArrayList<String>()//生活垃圾治理
        shljzlList.add("垃圾设施满冒、渗漏、污迹或破损")
        shljzlList.add("地搓站")
        shljzlList.add("地搓站周边环境管护不到位")
        shljzlList.add("积存生活垃圾")
        shljzlList.add("建筑垃圾")
        shljzlList.add("路边及周边垃圾")
        val shljzlAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,shljzlList)
        shljzlAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        jnldwhAdapter.setDatas(jnldyxwhList)
        sp_shljzl_environmental.adapter = shljzlAdapter
        val shljzlText = pjContract!!.shljzlText
        val indexOfshljzl = shljzlList.indexOf(shljzlText)
        sp_shljzl_environmental.setSelection(indexOfshljzl,true)

        val shwszlList = ArrayList<String>()//生活污水治理
        shwszlList.add("污水直排")
        shwszlList.add("黑臭水体")
        val shwszlAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,shwszlList)
        shwszlAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        jnldwhAdapter.setDatas(jnldyxwhList)
        sp_shwszl_environmental.adapter = shwszlAdapter
        val shwszlText = pjContract!!.shwszlText
        val indexOfshwszl = shwszlList.indexOf(shwszlText)
        sp_shwszl_environmental.setSelection(indexOfshwszl,true)
        val gcghList = ArrayList<String>()//公测管护
        gcghList.add("公厕设施")
        gcghList.add("公厕卫生、制度上墙")
        val gcghAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,gcghList)
        gcghAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        jnldwhAdapter.setDatas(jnldyxwhList)
        sp_gcgh_environmental.adapter = gcghAdapter
        val gcghText = pjContract!!.gcghText
        val indexOfgcgh = gcghList.indexOf(gcghText)
        sp_gcgh_environmental.setSelection(indexOfgcgh,true)
        val sdljList = ArrayList<String>()//私搭乱建
        sdljList.add("存量私搭乱建")
        sdljList.add("新增私搭乱建")
        val sdljAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,sdljList)
        sdljAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        jnldwhAdapter.setDatas(jnldyxwhList)
        sp_sdlj_environmental.adapter = sdljAdapter
        val sdljText = pjContract!!.sdljText
        val indexOfsdlj = sdljList.indexOf(sdljText)
        sp_sdlj_environmental.setSelection(indexOfsdlj,true)

        val list: MutableList<String> = ArrayList()
        list.add("待处理")
        list.add("整改中")
        list.add("验收完毕")
        list.add("验收完毕")
        list.add("验收完毕")
        var adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list)

        if (pjContract != null) { //            tvBhEnvironmental.setText(pjContract.getProjcode());
//            tvNameEnvironmental.setText(pjContract.projname)
            tvLrwtEnvironmental.setText(pjContract.questions)
            if (pjContract.qutype==50){
                tvWtztEnvironmental.setSelection(pjContract.qutype - 1, true)
            }else{
                tvWtztEnvironmental.setSelection(pjContract.qutype - 1, true)
            }

            //            tvWtztEnvironmental.setText(pjContract.getTypeText())
            tvJlrEnvironmental.setText(pjContract.jlr)
            //            tvNameSection.setText(pjContract.getProjbd());
            tvJlrPhone.setText(pjContract.jlrtel)
            tvWzenvironmental.setHint(pjContract.wz)
            tvRemarkEnvironmental.setText(pjContract.remark)
            tvZrdwEnvironmental.setText(pjContract.zrdw)
        } else {

        }
        if (pjContract.jltime.equals("")){
            uploadDate = CalendarUtil.DATE_FORMAT_DATE.format(Date())
            tvUpDateEnviron!!.setText(uploadDate)
        }else{
            if (pjContract.hjzzyjfl==1||pjContract.hjzzyjfl==7){
                uploadDate = CalendarUtil.DATE_FORMAT_DATE.format(Date())
                tvUpDateEnviron!!.setText(uploadDate)
            }else{
                tvUpDateEnviron!!.setText(pjContract.jltime)
            }

        }


        tvUpDateEnviron!!.setOnClickListener(View.OnClickListener { setTime(tvUpDateEnviron!!) })
        tvUploadMeetingSummary.setOnClickListener(View.OnClickListener {
            if (pjContract.wz.equals("")){
                ToastUtils.showShort("村名不能为空")
                return@OnClickListener
            }
            LoadingDialog.showDialogForLoading(this)
            bt_temporary_environmental.isEnabled = false
            tvUploadMeetingSummary.isEnabled = false
            /*if (tvLrwtEnvironmental.text.toString().length == 0) { //"请填写标题"
            ToastUtils.showShort("请输入问题")
        } else if (tvJlrEnvironmental.text.toString().length == 0) { //"请填写标题"
            ToastUtils.showShort("请输入记录人")
        } *//*else if (tvZrdwEnvironmental.text.toString().length == 0) {
            ToastUtils.showShort("请输入责任单位")
        }*//* else {*/
            pjContract!!.questions = tvLrwtEnvironmental.text.toString()
            pjContract!!.zrdw = tvZrdwEnvironmental.text.toString()//责任单位
            pjContract!!.jlrtel = tvJlrPhone.text.toString()
            //EnviorSupvsEnum.getIndex(tvWtztEnvironmental.selectedItem.toString())
            pjContract!!.jlr = tvJlrEnvironmental.text.toString()
            pjContract!!.remark = tvRemarkEnvironmental.text.toString()
//            pjContract!!.wz = tvWzenvironmental.text.toString()

            if (sp_cncw_environmental.isShown){
                val cnwString = sp_cncw_environmental.selectedItem.toString()
                pjContract.cnw = CnwEnum.getIndex(cnwString)
            }
            if (sp_sfgddw_environmental.isShown){
                val sfgddwString = sp_sfgddw_environmental.selectedItem.toString()
                pjContract.gddw = IsTrueEnum.getIndex(sfgddwString)
            }
            if (sp_sfzdwt_environmental.isShown){
                val sfzdwtString = sp_sfzdwt_environmental.selectedItem.toString()
                pjContract.zdwt = IsTrueEnum.getIndex(sfzdwtString)
            }
            if (ll_gddw_envuronmental.isShown){
                val jcssghString = sp_jcssgh_environmental.selectedItem.toString()
                pjContract.hjzzyjfl = HjzzflEnum.getIndex(jcssghString)
            }
            if (sp_tynysyxwh_environmental.isShown){
                val jcssghString = sp_tynysyxwh_environmental.selectedItem.toString()
                pjContract.yswh = YswhEnum.getIndex(jcssghString)
            }
            if (sp_hcqtgh_environmental.isShown){
                val hcqtghString = sp_hcqtgh_environmental.selectedItem.toString()
                pjContract.hcqtgh = HcqtghEnum.getIndex(hcqtghString)
            }
            if (sp_lhyh_environmental.isShown){
                val lhyhString = sp_lhyh_environmental.selectedItem.toString()
                pjContract.lhyh = LhyhEnum.getIndex(lhyhString)
            }
            if (sp_jnlduyxwh_environmental.isShown){
                val jnlduyxwhString = sp_jnlduyxwh_environmental.selectedItem.toString()
                pjContract.ldwh = Ldwhenum.getIndex(jnlduyxwhString)
            }
            /*if (ll_fgddw_envuronmental.isShown){
                val wttjString = sp_wttj_environmental.selectedItem.toString()
                pjContract.hjzzyjfl = HjzzflEnum.getIndex(wttjString)
            }*/
            if (sp_shljzl_environmental.isShown){
                val shljzlString = sp_shljzl_environmental.selectedItem.toString()
                pjContract.shljzl = ShljzlEnum.getIndex(shljzlString)
            }
            if (sp_shwszl_environmental.isShown){
                val shwszlString = sp_shwszl_environmental.selectedItem.toString()
                pjContract.shwszl = ShwszlEnum.getIndex(shwszlString)
            }
            if (sp_gcgh_environmental.isShown){
                val gcghString = sp_gcgh_environmental.selectedItem.toString()
                pjContract.gcgh = GcghEunm.getIndex(gcghString)
            }
            if (sp_sdlj_environmental.isShown){
                val sdljString = sp_sdlj_environmental.selectedItem.toString()
                pjContract.sdlj = SdljEnum.getIndex(sdljString)
            }
//            if (pjContract!!.qutype!=50){
                if ((pjContract.hjzzyjfl!=1&&pjContract.hjzzyjfl!=6&&pjContract.hjzzyjfl!=7)||sjflList.get(sp_sjfl_environmental.selectedItemPosition).name.equals("村内暂无问题")){
                    pjContract!!.qutype = 1
                }else{
                    pjContract!!.qutype = 3
                    pjContract.zgyq = tv_xfzgyq_environmental.text.toString()
                    val format = SimpleDateFormat("yyyy-MM-dd")//HHmmss
                    val date = Date(System.currentTimeMillis())
                    val filename = format.format(date)
                    pjContract.kstime=tv_clshijian_environmental.text.toString()//filename   ///
                    pjContract.qxtime=tv_xfclsj_environmental.text.toString()
                    pjContract.xfjy = tv_xfjy_environmental.text.toString()
                }
//            }

            pjContract.hjzzyjfl = HjzzflEnum.getIndex(hjzzList.get(sp_hjzz_environmental.selectedItemPosition).name)
//            if (ll_ejfl_environmental.isShown)
            /*pjContract.hjzzej = HjzzejEnum.getIndex(ejflList.get(sp_ejfl_environmental.selectedItemPosition).name,
                    ejflList.get(sp_ejfl_environmental.selectedItemPosition).index)*/
                if (ll_sjfl_environmental.isShown){
                    pjContract.hjzzsj = HjzzsjEnum.getIndex(sjflList.get(sp_sjfl_environmental.selectedItemPosition).name,
                            sjflList.get(sp_sjfl_environmental.selectedItemPosition).index)
                }
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            try {
                val dateString = simpleDateFormat.parse(tvUpDateEnviron!!.getText().toString())
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            pjContract.jltime = tvUpDateEnviron!!.getText().toString()


            LoadingDialog.showDialogForLoading(this)

            val intList = java.util.ArrayList<Int>()
            var se = 0

                /*LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("至少上传2张图片")*/
                val strings = arrayOfNulls<Int>(videoIdList.size)
                val integers: Array<Int> = videoIdList.toArray<Int>(strings)

                pjContract.fileIds = integers
                mPresenter.addEnvironmental(pjContract)
                hbjcEntity = pjContract
//            }
            object :Thread() {
                override fun run() {
                    super.run()
                    Thread.sleep(3000);//休眠3秒
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            if (bt_temporary_environmental!=null){
                                bt_temporary_environmental.isEnabled = true
                                tvUploadMeetingSummary.isEnabled = true
                            }
                        }

                    })

                }
            }.start()
            //}
        })
        butClearMeetingSummary.setOnClickListener {
            photoAdapter!!.clearPics()
            if (envirorUpPopu!=null){
                envirorUpPopu!!.dismiss()
            }
//            clearUpdate(tvNameEnvironmental, tvNameEnvironmental, tvLrwtEnvironmental, tvJlrEnvironmental, tvWzenvironmental, tvRemarkEnvironmental)
        }
    }
    //二级分类
    private fun addEjfl(values: Array<HjzzejEnum>, ejflList: ArrayList<BaobiaoBean>,index:String) {
        for (i in values) {
            val subSequence = i.index.toString().subSequence(0, 1)
            if (subSequence.equals(index)) {
                ejflList.add(BaobiaoBean(i.getName(),i.index))
            }
        }
    }
    //三级分类
    private fun addSjfl(values: Array<HjzzsjEnum>, sjflList: ArrayList<BaobiaoBean>,index:String) {
        for (i in values) {
            val subSequence = i.index.toString().subSequence(0, 3)
            if (subSequence.equals(index)) {
                /*if (serializableExtra!!.qutype==50){
                    if (i.getName().contains(" ")){
                        sjflList.add(BaobiaoBean(i.getName(),i.index))
                    }
                }else{
                    if (!i.getName().contains(" ")){*/
                        sjflList.add(BaobiaoBean(i.getName(),i.index))
                    /*}
                }*/
            }
        }
    }
    private fun addNewSjfl(values: Array<HjzzsjEnum>, sjflList: ArrayList<BaobiaoBean>,index:String) {
        for (i in values) {
            val subSequence = i.index.toString().subSequence(0, 1)
            if (subSequence.equals(index)) {
               /* if (serializableExtra!!.qutype==50){
                    if (i.getName().contains(" ")){
                        sjflList.add(BaobiaoBean(i.getName(),i.index))
                    }
                }else{
                    if (!i.getName().contains(" ")){*/
                        sjflList.add(BaobiaoBean(i.getName(),i.index))
                  /*  }
                }*/

            }
        }
    }

    private fun upVideo(file2: File) {
        val request = OkGo.post<BaseRespose<String>>(ApiConstants.ENVIORFILEUPLOAD)
                .isMultipart(true)

        request.params("type", 2)//1
        if (filetype==4){
            request.params("filetype", 3)//1
        }else if (filetype==100){
            request.params("filetype", 4)//1
        }else if (filetype==5){
            request.params("filetype", 4)//1
        }else{
            request.params("filetype", filetype)//1
        }
        request.params("msid", msid)
        request.params(file2.name, file2)
        request.execute(object : BaseNet<BaseRespose<String>>() {
            override fun onStart(request: Request<BaseRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@HBJCDetailActivity)
            }
            override fun onSuccess(response: Response<BaseRespose<String>>) {
//                super.onSuccess(response)
                val body = response.body()
//                intList.add(Integer.valueOf(body.data))
                if (body.code == 0) {
                    videoIdList.add(Integer.valueOf(body.data))
                    ToastUtils.showShort("上传成功")
                }
            }

            override fun onError(response: Response<BaseRespose<String>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("上传失败")
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }
        })
    }
    private fun initPopuXfUp(pjContract: PjEnviorSupvsEntity){//下发
        selectedPhotos.clear()
        selectedPhotos_new.clear()
        selectedVideos.clear()
        videoIdList.clear()
        fileIdList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_environmental_xf, hbjcdetail_top)
        val contentView2: View = envirorUpPopu!!.getContentView()
        var tv_zgyq_environmental = contentView2.findViewById<EditText>(R.id.tv_zgyq_environmental)
        var tv_jsry_environmental = contentView2.findViewById<Spinner>(R.id.tv_jsry_environmental)
        var sp_clqx_environmental = contentView2.findViewById<Spinner>(R.id.sp_clqx_environmental)
        var tv_clshijian_environmental = contentView2.findViewById<EditText>(R.id.tv_clshijian_environmental)
        var tv_clsj_environmental = contentView2.findViewById<EditText>(R.id.tv_clsj_environmental)
        var tv_xfjy_environmental = contentView2.findViewById<EditText>(R.id.tv_xfjy_environmental)
        var recy_pop_environmental_xf = contentView2.findViewById<RecyclerView>(R.id.recy_pop_environmental_xf)
        var rlv_pop_video_add_xf = contentView2.findViewById<RecyclerView>(R.id.rlv_pop_video_add_xf)
        var bt_clear_environmental = contentView2.findViewById<Button>(R.id.bt_clear_environmental_xf)//关闭
        var bt_upload_environmental = contentView2.findViewById<Button>(R.id.bt_upload_environmental_xf)//确定

        val clqxList = ArrayList<String>();//处理期限
        clqxList.add("1")
        clqxList.add("2")
        clqxList.add("3")
        clqxList.add("4")
        clqxList.add("5")
        clqxList.add("6")
        clqxList.add("7")
        clqxList.add("8")
        clqxList.add("9")
        clqxList.add("10")
        clqxList.add("11")
        clqxList.add("12")
        clqxList.add("13")
        clqxList.add("14")
        clqxList.add("15")
        val jclqxAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,clqxList)
        jclqxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_clqx_environmental.adapter = jclqxAdapter
        sp_clqx_environmental.setSelection(14,true)

        photoAdapter = PhotoAdapter(this, selectedPhotos)
        videoAdapter = VideoAdapter(this, selectedVideos)
        recy_pop_environmental_xf.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_environmental_xf.adapter = photoAdapter
        recy_pop_environmental_xf.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity,20)//, this
                }
            }
        }))

        rlv_pop_video_add_xf.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlv_pop_video_add_xf.adapter = videoAdapter
        rlv_pop_video_add_xf.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (videoAdapter!!.getItemViewType(position) === VideoAdapter.TYPE_ADD) {
                    video()
                } else {
                    //ToastUtils.showShort(selectedVideos.get(position))
                    val intent = Intent(Intent.ACTION_VIEW)
                    val path = selectedVideos.get(position)//该路径可以自定义
                    val file = File(path)
                    val uri = Uri.fromFile(file)
                    intent.setDataAndType(Uri.parse(path), "video/*")
                    startActivity(intent)

                }
            }
        }))
        val format = SimpleDateFormat("yyyy-MM-dd")//HHmmss
        val date = Date(System.currentTimeMillis())
        val filename = format.format(date)
        tv_clshijian_environmental.setText(filename)
        /*tv_clshijian_environmental.setOnClickListener {
            setTime(tv_clshijian_environmental)
        }*/
        tv_clsj_environmental.setOnClickListener {
            setTime(tv_clsj_environmental)
        }

        val jsryhAdapter = ArrayAdapter(this ,android.R.layout.simple_spinner_item,userList)
        jsryhAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        tv_zgyq_environmental.setText(pjContract.zgyq)
        if (!pjContract.kstime.equals("")){
            /*val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val parse = simpleDateFormat.parse(tvUpDateEnviron!!.getText().toString())
            val format = simpleDateFormat.format(parse)
            tv_clsj_environmental.setText (format)*/
        }
        tv_xfjy_environmental.setText(pjContract.xfjy)
//        tv_jsry_environmental.adapter = jsryhAdapter

        if (!pjContract.jsry.equals("")){//sysUserEntityList
            var userName=""
            for (i in sysUserEntityList){
                if (pjContract.jsry == i.userId.toInt()){
                    userName = i.name
                }
            }
            val indexOfhcqtgh = userList.indexOf(userName)
//            tv_jsry_environmental.setSelection(indexOfhcqtgh,true)
        }

        bt_clear_environmental.setOnClickListener {
            if (envirorUpPopu!=null){
                envirorUpPopu!!.dismiss()
            }
        }
        bt_upload_environmental.setOnClickListener {
            bt_upload_environmental.isEnabled =false
            if (tv_zgyq_environmental.text.toString().equals("")){
                ToastUtils.showShort("请填写整改要求")
            }/*else if (tv_clsj_environmental.text.toString().equals("")){
                ToastUtils.showShort("请填写期限")
            }*/else{
                bt_upload_environmental.isEnabled = false
                pjContract!!.qutype = 3
                pjContract.zgyq = tv_zgyq_environmental.text.toString()
//                sysUserEntityList.get(tv_jsry_environmental.selectedItemPosition)
//                pjContract.jsry = sysUserEntityList.get(tv_jsry_environmental.selectedItemPosition).userId.toInt()//tv_jsry_environmental.selectedItem.toString()
                val format = SimpleDateFormat("yyyy-MM-dd")//HHmmss
                val date = Date(System.currentTimeMillis())
                val filename = format.format(date)
               /* if (!tv_clsj_environmental.text.toString().equals("")){
                    pjContract.qxtime =  filename+" - "+ tv_clsj_environmental.text.toString()
                }*/
//                pjContract.kstime=filename   ///
                //pjContract.kstime=tv_clshijian_environmental.text.toString()
//                pjContract.qxtime=tv_clsj_environmental.text.toString()
//                pjContract.qxtime=TimeUtil.getDateAfter(sp_clqx_environmental.selectedItem.toString().toInt()+1)
                pjContract.xfjy = tv_xfjy_environmental.text.toString()
                mPresenter.addEnvironmental(pjContract)


            }
            object :Thread() {
                override fun run() {
                    super.run()
                    Thread.sleep(3000);//休眠3秒
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            if (bt_upload_environmental!=null){
                                bt_upload_environmental.isEnabled = true
                            }
                        }
                    })
                }
            }.start();
        }
    }
    //修改整改中弹框
    private fun initPopuZGZUp(pjContract: PjEnviorSupvsEntity?) {
        selectedPhotos.clear()
        selectedPhotos_new.clear()
        selectedVideos.clear()
        videoIdList.clear()
        fileIdList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_hbjczgz_up, hbjcdetail_top)
        val contentView2: View = envirorUpPopu!!.getContentView()

        val tv_clr_zgz = contentView2.findViewById<EditText>(R.id.tv_clr_zgz)
        val tv_clr_phone_zgz = contentView2.findViewById<EditText>(R.id.tv_clr_phone_zgz)
        val tv_remark_zgz = contentView2.findViewById<EditText>(R.id.tv_remark_zgz)
        val tv_pop_environmental_xgwj = contentView2.findViewById<TextView>(R.id.tv_pop_environmental_xgwj)
        val recy_pop_environmental_xgwj = contentView2.findViewById<RecyclerView>(R.id.recy_pop_environmental_xgwj)
        val recy_pop_environmental_xf = contentView2.findViewById<RecyclerView>(R.id.recy_pop_environmental_zg)
        val rlv_pop_video_add_xf = contentView2.findViewById<RecyclerView>(R.id.rlv_pop_video_add_zg)


        val bt_upload_zgz = contentView2.findViewById<Button>(R.id.bt_upload_zgz)
        val bt_clear_zgz = contentView2.findViewById<Button>(R.id.bt_clear_zgz)

        photoAdapter = PhotoAdapter(this, selectedPhotos)
        recy_pop_environmental_xgwj.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_environmental_xf.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_environmental_xf.adapter = photoAdapter

        videoAdapter = VideoAdapter(this, selectedVideos)
        rlv_pop_video_add_xf.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlv_pop_video_add_xf.adapter = videoAdapter

        recy_pop_environmental_xf.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity,20)//, this
                }
            }
        }))

        rlv_pop_video_add_xf.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (videoAdapter!!.getItemViewType(position) === VideoAdapter.TYPE_ADD) {
                    video()
                } else {
                    //ToastUtils.showShort(selectedVideos.get(position))
                    val intent = Intent(Intent.ACTION_VIEW)
                    val path = selectedVideos.get(position)//该路径可以自定义
                    val file = File(path)
                    val uri = Uri.fromFile(file)
                    intent.setDataAndType(Uri.parse(path), "video/*")
                    startActivity(intent)
                }
            }
        }))

        if (xf_FileList!=null&&xf_FileList.size>0){
//            tv_pop_environmental_xgwj.visibility = View.VISIBLE
        }
        recy_pop_environmental_xgwj.visibility = View.VISIBLE
        zgzFileAdapter = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, xf_FileList) {
            override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                if (item.filetype == 3) {
                    //                AppApplication.getGlideUrl(s1)
                    val pic: String = item.url//ApiConstants.BASE_URL + item.path
                    val s1 = pic.replace("\\", "/")
                    Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                            .into(view)
                    helper.setText(R.id.tv_teng_photo_name, item.name)
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@HBJCDetailActivity)
                        // 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
                        // 设置Title的内容
                        builder.setTitle("弹出警告框")
                        // 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
                        // 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            /* @Override
                             public void onClick(DialogInterface dialog, int which)
                             {
                                 Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                             }*/
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                var position = helper.adapterPosition//item.id.toString()+""
                                OkGo.post<String> (ApiConstants.ENVIORFILEDELETE).upJson("[${item.id}]").execute(object :
                                        BaseNet<String>(){
                                    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                        LoadingDialog.showDialogForLoading(this@HBJCDetailActivity)
                                    }

                                    override fun onSuccess(response: Response<String>?) {
                                        val cash = response?.body()
                                        if (cash != null) {
                                            //val decrypt = AesEncryptUtils.decrypt(cash)
                                            val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                            if (json.code==0){
                                                xf_FileList.removeAt(position)
                                                notifyDataSetChanged()
                                                //mView.returnDeleteFileEnvironmental(json)
                                            }else{
                                                ToastUtils.showShort(json.getMsg())
                                            }

                                        } else {
                                            ToastUtils.showShort("确定")
                                        }
                                    }

                                    override fun onFinish() {
                                        super.onFinish()
                                        LoadingDialog.cancelDialogForLoading()
                                    }

                                    override fun onError(response: Response<String>?) {
                                        super.onError(response)
                                        ToastUtils.showShort("网络错误")
                                    }

                                })
                            }
                        });
                        // 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            /*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*/

                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        });
                        builder.show()
                    }
                    helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                        var pathList = ArrayList<String>()
                        for (i in xf_FileList){
                            val pic = i.url//ApiConstants.BASE_URL + i.path
                            val s1 = pic.replace("\\", "/")
                            pathList.add(s1)
                        }
                        PhotoPreview.builder()
                                .setPhotos(pathList)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@HBJCDetailActivity)
//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                           /* val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
//                            intent.putExtra("url", s1)
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                        hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                startActivity(intent, options)
//                            } else {
//                                startActivity(intent)
//                                overridePendingTransition(R.anim.fade_in,
//                                        R.anim.fade_out)
//                            }*/
//                        }else{
////                            val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
////                            startActivity(intent)
//                            val intent = Intent(Intent.ACTION_VIEW)
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }
                    }
                }else{
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }
            }
        }
        recy_pop_environmental_xgwj.adapter = zgzFileAdapter

       /* val recy_pop_zgz: RecyclerView = contentView2.findViewById(R.id.recy_pop_zgz)
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        recy_pop_zgz.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_zgz.adapter = photoAdapter
        recy_pop_zgz.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
                            .start(this@HBJCDetailActivity!!)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(this@HBJCDetailActivity!!)
                }
            }
        }))*/



        if (pjContract != null) {
            //            tvBhEnvironmental.setText(pjContract.getProjcode());
//            tvNameEnvironmental.setText(pjContract.projname)
            /*tv_zgyq_zgz.setText(pjContract.zgyq)
            tv_chufa_zgz.setText(pjContract.kscf)*/
            //            tvNameSection.setText(pjContract.getProjbd());
            if (pjContract.ksname.equals("")){
                tv_clr_zgz.setText(AppCache.getInstance().name)
            }else{
                tv_clr_zgz.setText(pjContract.ksname)
            }
            if (pjContract.kstel.equals("")){
                tv_clr_phone_zgz.setText(AppCache.getInstance().mobile)
            }else{
                tv_clr_phone_zgz.setText(pjContract.kstel)
            }
            tv_remark_zgz.setText(pjContract.ksbz)


        } else {

        }
        uploadDate = CalendarUtil.DEFAULT_DATE_FORMAT.format(Date())

        bt_upload_zgz.setOnClickListener(View.OnClickListener {
            bt_upload_zgz.isEnabled =false
        /*if (tv_clr_zgz.text.toString().length == 0) { //"请填写标题"
            ToastUtils.showShort("请输入处理人")
        } else if (tv_clr_phone_zgz.text.toString().length == 0) { //"请填写标题"
            ToastUtils.showShort("请输入处理人电话")
        } else if (tv_remark_zgz.text.toString().length == 0) {
            ToastUtils.showShort("请输入备注")
        } else {*/
                pjContract!!.kstel = tv_clr_phone_zgz.text.toString()
                pjContract!!.ksname = tv_clr_zgz.text.toString()
                pjContract!!.ksbz = tv_remark_zgz.text.toString()
                pjContract!!.qutype = 4

                //LoadingDialog.showDialogForLoading(this, "正在上传", true)

                val intList = java.util.ArrayList<Int>()
                var se = 0
                /*if (selectedPhotos.size > 0) {

                    for (i in selectedPhotos) {
                        se++
                        val file = File(i)
                        var newPath = ""
                        var success = true
                        *//*newPath = GetFileUtil.getSDCardPath().toString() + "JYMJManager/uploader/Pictures/" + file.name
                        success = ImageUtils.saveExifAndCompressByQuality(i, newPath, 1000, true)*//*
                        val name = file.name
//                        val bitmap = BitmapFactory.decodeFile(file.path)
                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        //Bitmap comp = comp(bitmap);
                        //Bitmap comp = comp(bitmap);
                        val file1: File = compressImages(bitmap, name)
                        if (success) {
//                        val file2 = File(newPath)
//                            , 2
                            upFile1(pjContract, EditText(this), EditText(this), file1, intList, se, 99)
                        } else {
                            LoadingDialog.cancelDialogForLoading()
                            return@OnClickListener
                        }
                    }
//                AppCache.getInstance().setAdd(false)
                } else {*/
                    /*LoadingDialog.cancelDialogForLoading()
                    ToastUtils.showShort("至少上传2张图片")*/
                    val strings = arrayOfNulls<Int>(videoIdList.size)
                    val integers: Array<Int> = videoIdList.toArray<Int>(strings)

                    pjContract.fileIds = integers
                    mPresenter.addEnvironmental(pjContract)
                    hbjcEntity = pjContract
//                }
            object :Thread() {
                override fun run() {
                    super.run()
                    Thread.sleep(3000);//休眠3秒
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            if (bt_upload_zgz!=null){
                                bt_upload_zgz.isEnabled = true
                            }
                        }

                    })


                }
            }.start();
//            }

        //}
        })
        bt_clear_zgz.setOnClickListener {
            if (envirorUpPopu!=null){
                envirorUpPopu!!.dismiss()
            }
        }
    }

    //修改验收完成弹框
    private fun initPopuYSWCUp(pjContract: PjEnviorSupvsEntity?,view:View) {
        selectedPhotos.clear()
        selectedPhotos_new.clear()
        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_hbjcyswc_up, hbjcdetail_top)
        val contentView2: View = envirorUpPopu!!.getContentView()

        val tv_yspj_yswc = contentView2.findViewById<EditText>(R.id.tv_yspj_yswc)
        val tv_ysr_yswc = contentView2.findViewById<EditText>(R.id.tv_ysr_yswc)
        val tv_remark_yswc = contentView2.findViewById<EditText>(R.id.tv_remark_yswc)

        val tv_ysdate_yswc = contentView2.findViewById<TextView>(R.id.tv_ysdate_yswc)
        val tv_ysr_phone_yswc = contentView2.findViewById<TextView>(R.id.tv_ysr_phone_yswc)


        val bt_upload_yswc = contentView2.findViewById<Button>(R.id.bt_upload_yswc)
        val bt_clear_yswc = contentView2.findViewById<Button>(R.id.bt_clear_yswc)


        val tv_pop_environmental_xgwj = contentView2.findViewById<TextView>(R.id.tv_pop_environmental_xgwj)
        val recy_pop_environmental_xgwj: RecyclerView = contentView2.findViewById(R.id.recy_pop_environmental_xgwj)
        val recy_pop_environmental_ys: RecyclerView = contentView2.findViewById(R.id.recy_pop_environmental_ys)
        val rlv_pop_video_add_ys: RecyclerView = contentView2.findViewById(R.id.rlv_pop_video_add_ys)
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        recy_pop_environmental_xgwj.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_environmental_ys.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_environmental_ys.adapter = photoAdapter

        videoAdapter = VideoAdapter(this, selectedVideos)
        rlv_pop_video_add_ys.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlv_pop_video_add_ys.adapter = videoAdapter

        recy_pop_environmental_ys.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@HBJCDetailActivity,20)//, this
                }
            }
        }))

        rlv_pop_video_add_ys.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (videoAdapter!!.getItemViewType(position) === VideoAdapter.TYPE_ADD) {
                    video()
                } else {
                    //ToastUtils.showShort(selectedVideos.get(position))
                    val intent = Intent(Intent.ACTION_VIEW)
                    val path = selectedVideos.get(position)//该路径可以自定义
                    val file = File(path)
                    val uri = Uri.fromFile(file)
                    intent.setDataAndType(Uri.parse(path), "video/*")
                    startActivity(intent)
                }
            }
        }))

        if (yswc_FileList!=null&&yswc_FileList.size>0){
            tv_pop_environmental_xgwj.visibility = View.VISIBLE
            recy_pop_environmental_xgwj.visibility = View.VISIBLE

            yswcFileAdapter = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, yswc_FileList) {
                override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                    val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                    val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                    if (item.filetype == 5||item.filetype == 4) {
                        //                AppApplication.getGlideUrl(s1)
                        val pic: String = item.url//ApiConstants.BASE_URL + item.path
                        val s1 = pic.replace("\\", "/")
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                                .into(view)
                        helper.setText(R.id.tv_teng_photo_name, item.name)
                        helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                            //                                    Toast.makeText(this@HBJCDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                            // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                            var builder = AlertDialog.Builder(this@HBJCDetailActivity)
// 设置Title的图标
                            builder.setIcon(R.mipmap.ic_launcher)
// 设置Title的内容
                            builder.setTitle("弹出警告框")
// 设置Content来显示一个信息
                            builder.setMessage("确定删除吗？")
// 设置一个PositiveButton
                            builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                /* @Override
                                 public void onClick(DialogInterface dialog, int which)
                                 {
                                     Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                                 }*/

                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    //deletePic(item.id)
                                }
                            });
// 设置一个NegativeButton
                            builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                                /*  @Override
                                  public void onClick(DialogInterface dialog, int which)
                                  {
                                      Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                                  }*/

                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    dialog!!.dismiss()
                                }
                            });
                            builder.show()

                        }
                        helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                            var pathList = ArrayList<String>()
                            for (i in dclFileList){
                                val pic = item.url//ApiConstants.BASE_URL + i.path
                                val s1 = pic.replace("\\", "/")
                                pathList.add(s1)
                            }
                            PhotoPreview.builder()
                                    .setPhotos(pathList)
                                    .setCurrentItem(helper.adapterPosition)
                                    .setShowDeleteButton(false)
                                    .start(this@HBJCDetailActivity)
//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                            /*val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
//                            intent.putExtra("url", s1)
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                        hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                startActivity(intent, options)
//                            } else {
//                                startActivity(intent)
//                                overridePendingTransition(R.anim.fade_in,
//                                        R.anim.fade_out)
//                            }*/
//                        }else{
////                            val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
////                            startActivity(intent)
//                            val intent = Intent(Intent.ACTION_VIEW);
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }
                        }
                    }else{
                        /*remove(helper.layoutPosition)
                        notifyDataSetChanged()*/
                        ll_teng_tui.visibility = View.GONE
                        helper.itemView.visibility = View.GONE
                    }
                }
            }
            recy_pop_environmental_xgwj.adapter = yswcFileAdapter
        }else if (zgz_FileList!= null&&zgz_FileList.size>0){
            tv_pop_environmental_xgwj.visibility = View.VISIBLE
            recy_pop_environmental_xgwj.visibility = View.VISIBLE
            zgzFileAdapter = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, zgz_FileList) {
                override fun convert(helper: BaseViewHolder, item: PjEnviorFileEntity) {
                    val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                    val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                    if (item.filetype == 3) {
                        //                AppApplication.getGlideUrl(s1)
                        val pic: String = item.url//ApiConstants.BASE_URL + item.path
                        val s1 = pic.replace("\\", "/")
                        Glide.with(mContext).load(AppApplication.getGlideUrl(s1))//GlideApp
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                                .into(view)
                        helper.setText(R.id.tv_teng_photo_name, item.name)
                        helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                            //                                    Toast.makeText(this@HBJCDetailActivity, "删除", Toast.LENGTH_SHORT).show()
                            // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                            var builder = AlertDialog.Builder(this@HBJCDetailActivity)
// 设置Title的图标
                            builder.setIcon(R.mipmap.ic_launcher)
// 设置Title的内容
                            builder.setTitle("弹出警告框")
// 设置Content来显示一个信息
                            builder.setMessage("确定删除吗？")
// 设置一个PositiveButton
                            builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                /* @Override
                                 public void onClick(DialogInterface dialog, int which)
                                 {
                                     Toast.makeText(MainActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                                 }*/

                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    //deletePic(item.id)
                                }
                            });
// 设置一个NegativeButton
                            builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                                /*  @Override
                                  public void onClick(DialogInterface dialog, int which)
                                  {
                                      Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                                  }*/

                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    dialog!!.dismiss()
                                }
                            });
                            builder.show()

                        }
                        helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                            var pathList = ArrayList<String>()
                            for (i in zgz_FileList){
                                val pic = item.url//ApiConstants.BASE_URL + i.path
                                val s1 = pic.replace("\\", "/")
                                pathList.add(s1)
                            }
                            PhotoPreview.builder()
                                    .setPhotos(pathList)
                                    .setCurrentItem(helper.adapterPosition)
                                    .setShowDeleteButton(false)
                                    .start(this@HBJCDetailActivity)
//                        if (item.name.contains(".png")||item.name.contains(".jpg")){
//                            val pic = ApiConstants.BASE_URL + item.path
//                            val s1 = pic.replace("\\", "/")
//                            var pathList = ArrayList<String>()
//                            pathList.add(s1)
//                            PhotoPreview.builder()
//                                    .setPhotos(pathList)
//                                    .setCurrentItem(0)
//                                    .setShowDeleteButton(false)
//                                    .start(this@HBJCDetailActivity)
//                           /* val intent = Intent(this@HBJCDetailActivity, BigPicActivity::class.java)
//                            intent.putExtra("url", s1)
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                val options = ActivityOptions.makeSceneTransitionAnimation(this@HBJCDetailActivity,
//                                        hbjcdetail_dcl_rlv, "pic_local").toBundle()
//                                startActivity(intent, options)
//                            } else {
//                                startActivity(intent)
//                                overridePendingTransition(R.anim.fade_in,
//                                        R.anim.fade_out)
//                            }*/
//                        }else{
////                            val intent = Intent(Intent.ACTION_VIEW);
////                            val type = "video/* ";
////
////                            val uri = Uri.parse(s1)
////                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
////                            startActivity(intent)
//                            val intent = Intent(Intent.ACTION_VIEW)
//                            val type = "video/* ";
//
//                            val uri = Uri.parse(s1)
//                            intent.setData(uri)
////                            intent.setDataAndType(uri, type);
//                            startActivity(intent)
//                        }
                        }
                    }else{
                        ll_teng_tui.visibility = View.GONE
                        helper.itemView.visibility = View.GONE
                    }
                }
            }
            recy_pop_environmental_xgwj.adapter = zgzFileAdapter
        }



        if (pjContract != null) { //            tvBhEnvironmental.setText(pjContract.getProjcode());
//            tvNameEnvironmental.setText(pjContract.projname)
            if (view==hbjcdetail_dsh_bh_btn){//view==hbjcdetail_yswc_btn||
                tv_yspj_yswc.setText(pjContract.cgpj)
                tv_remark_yswc.setText(pjContract.cgbz)//tv_remark_yswc
            }else{
                tv_yspj_yswc.setText(pjContract.rejectYj)
                tv_remark_yswc.setText(pjContract.rejectBz)//tv_remark_yswc
            }

            tv_ysr_yswc.setText(pjContract.cgname)
            tv_ysr_phone_yswc.setText(pjContract.cgtel)
            //            tvNameSection.setText(pjContract.getProjbd());
        } else {

        }
        tv_ysr_yswc.setText(AppCache.getInstance().name)
        tv_ysr_phone_yswc.setText(AppCache.getInstance().mobile)
        if (pjContract!!.cgtime.equals("")){
            uploadDate = CalendarUtil.DEFAULT_DATE_FORMAT.format(Date())
            tv_ysdate_yswc!!.setText(uploadDate)
        }else{
            tv_ysdate_yswc!!.setText(pjContract!!.cgtime)
        }

        tv_ysdate_yswc!!.setOnClickListener(View.OnClickListener { setTime(tv_ysdate_yswc!!) })

        bt_upload_yswc.setOnClickListener(View.OnClickListener {
            bt_upload_yswc.isEnabled = false
            /*if (tv_yspj_yswc.text.toString().length == 0) { //"请填写标题"
            ToastUtils.showShort("请输入验收评价")
        } else if (tv_ysr_yswc.text.toString().length == 0) { //"请填写标题"
            ToastUtils.showShort("请输入验收人")
        } else {*/
            if (view==hbjcdetail_dsh_bh_btn){//view==hbjcdetail_yswc_btn||
                pjContract!!.cgpj = tv_yspj_yswc.text.toString()
                pjContract!!.cgbz = tv_remark_yswc.text.toString()
            }else {
                pjContract!!.rejectYj = tv_yspj_yswc.text.toString()
                pjContract!!.rejectBz = tv_remark_yswc.text.toString()
            }

            pjContract!!.cgname = tv_ysr_yswc.text.toString()
            pjContract!!.cgtel = tv_ysr_phone_yswc.text.toString()
            pjContract!!.qutype = filetype//5
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

            pjContract.cgtime = tv_ysdate_yswc!!.getText().toString()

            //LoadingDialog.showDialogForLoading(this, "正在上传", true)

            val intList = java.util.ArrayList<Int>()
            var se = 0
            /*if (selectedPhotos.size > 0) {

                for (i in selectedPhotos) {
                    se++
                    val file = File(i)
                    var newPath = ""
                    var success = true
                    *//*newPath = GetFileUtil.getSDCardPath().toString() + "JYMJManager/uploader/Pictures/" + file.name
                    success = ImageUtils.saveExifAndCompressByQuality(i, newPath, 1000, true)*//*
                    val name = file.name
//                    val bitmap = BitmapFactory.decodeFile(file.path)
                    val bitmap = IOHelper.loadBitmap(file.path,true)
                    //Bitmap comp = comp(bitmap);
                    //Bitmap comp = comp(bitmap);
                    val file1: File = compressImages(bitmap, name)
                    if (success) {
//                        val file2 = File(newPath)
//                        , 3
                        upFile1(pjContract, EditText(this), tv_ysdate_yswc!!, file1, intList, se, 99)
                    } else {
                        LoadingDialog.cancelDialogForLoading()
                        return@OnClickListener
                    }
                }
//                AppCache.getInstance().setAdd(false)
            } else {*/
                /*LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("至少上传2张图片")*/
                val strings = arrayOfNulls<Int>(videoIdList.size)
                val integers: Array<Int> = videoIdList.toArray<Int>(strings)

                pjContract.fileIds = integers
                mPresenter.addEnvironmental(pjContract)
                hbjcEntity = pjContract
//            }
            object :Thread() {
                override fun run() {
                    super.run()
                    Thread.sleep(3000);//休眠3秒
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            if (bt_upload_yswc!=null){
                                bt_upload_yswc.isEnabled = true
                            }
                        }

                    })


                }
            }.start();

        //}
        })
        bt_clear_yswc.setOnClickListener {
            photoAdapter!!.clearPics()
            tv_yspj_yswc.setText("")
            tv_ysr_yswc.setText("")
            tv_remark_yswc.setText("")
            if (envirorUpPopu!=null){
                envirorUpPopu!!.dismiss()
            }
        }
    }

//, filetype: Int
    private fun upFile1(pjContract: PjEnviorSupvsEntity, tvUpDate: TextView, file2: File) {//, intList: ArrayList<Int>, se: Int, se1: Int
    var file: File? =  null
    if (filetype==1){//||pjContract.qutype==50
        val decodeFile = BitmapFactory.decodeFile(file2.path)

        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(this, decodeFile, serializableExtra!!.xzqmc, 42, Color.WHITE, 10, 10)
        file = BitMapToFile.getFile(drawTextToRightBottom)

    }else{
        file = file2
    }
    val request = OkGo.post<BaseRespose<PjEnviorFileEntity>>(ApiConstants.ENVIORFILEUPLOAD)
            .isMultipart(true)
//        request.params("pid", AppCache.getInstance().getProid())
        request.params("type", 1)//1
//    if (pjContract.qutype!=50){
        if (filetype==4){
            request.params("filetype", 3)//1
        }else if (filetype==100){
            request.params("filetype", 4)//1
        }else if (filetype==5){
            request.params("filetype", 4)//1
        }else{
            request.params("filetype", filetype)//1
        }
//    }else{
//        request.params("filetype", 50)//1
//    }

        request.params("msid", pjContract.id)
        request.params("file", file)//file!!.name
        request.execute(object : BaseNet<BaseRespose<PjEnviorFileEntity>>() {

            override fun onSuccess(response: Response<BaseRespose<PjEnviorFileEntity>>) {
//                super.onSuccess(response)//abstract member cannot be accessed directly
                val body = response.body()
                        if (body.code == 0){
                            if (body.data != null){
                                if (filetype == 1){
                                    dcl_FileList.add(body.data)
                                    dclFileList.add(body.data)

                                    if (dclFileAdapter!=null){
                                        dclFileAdapter!!.setNewData(dclFileList)
                                        dclFileAdapter!!.notifyDataSetChanged()
                                    }
                                    if (dclFileAdapter1!=null){
                                        dclFileAdapter1!!.setNewData(dcl_FileList)
                                        dclFileAdapter1!!.notifyDataSetChanged()
                                    }
                                }else if (filetype==4){
                                    xf_FileList.add(body.data)
                                    if (zgzFileAdapter!=null)
                                        zgzFileAdapter!!.setNewData(xf_FileList)
                                }

                            }

                        }

                /*intList.add(Integer.valueOf(body.data))
                if (intList.size == selectedPhotos.size || se1 == selectedPhotos.size) {
                    *//*val strings = arrayOfNulls<Int>(intList.size)
                    val integers: Array<Int> = intList.toArray<Int>(strings)
                    pjContract.fileIds = integers*//*
                    fileIdList.addAll(videoIdList)
//                    fileIdList.addAll(intList)
                    for (i in intList){
                        fileIdList.add(i)
                    }
                    val strings = arrayOfNulls<Int>(fileIdList.size)
                    val integers: Array<Int> = fileIdList.toArray<Int>(strings)

                    pjContract.fileIds = integers

                    //mPresenter.addEnvironmental(pjContract)
                    ToastUtils.showShort("上传成功")
                    hbjcEntity = pjContract
                }*/
            }

            override fun onError(response: Response<BaseRespose<PjEnviorFileEntity>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("上传失败")
            }
        })
    }

    /**
     * 压缩图片（质量压缩）
     *
     * @param bitmap
     */
    fun compressImages(bitmap: Bitmap, fileName: String): File {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos) //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 1024) { //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset() //重置baos即清空baos
            options -= 10 //每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) //这里压缩options%，把压缩后的数据存放到baos中
            val length = baos.toByteArray().size.toLong()
        }
        val format = SimpleDateFormat("yyyyMMddHHmmss")
        val date = Date(System.currentTimeMillis())
        val filename = format.format(date)
        //        File file = new File(Environment.getExternalStorageDirectory(), fileName + ".png");
        val file = File(Environment.getExternalStorageDirectory(), fileName)
        try {
            val fos = FileOutputStream(file)
            try {
                fos.write(baos.toByteArray())
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        recycleBitmap(bitmap)
        return file
    }

    //释放
    fun recycleBitmap(vararg bitmaps: Bitmap?) {
        if (bitmaps == null) {
            return
        }
        for (bm in bitmaps) {
            if (null != bm && !bm.isRecycled) {
                bm.recycle()
            }
        }
    }

    private fun clearUpdate(tvBhEnvironmental: EditText, tvNameEnvironmental: EditText, tvLrwtEnvironmental: EditText, tvJlrEnvironmental: EditText, tvWzenvironmental: EditText, tvRemarkEnvironmental: EditText) {
//        tvUpDateEnviron!!.text = ""
        tvBhEnvironmental.setText("")
        tvNameEnvironmental.setText("")
        tvLrwtEnvironmental.setText("")
        tvJlrEnvironmental.setText("")
        tvWzenvironmental.setText("")
        tvRemarkEnvironmental.setText("")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) // && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode==233){
                var photos: ArrayList<String>? = null
                selectedPhotos.clear()
                selectedPhotos_new.clear()
                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
//                    selectedPhotos.addAll(photos)
                    selectedPhotos_new.addAll(photos)
                    for (i in selectedPhotos_new) {

                        val file = File(i)
                        var newPath = ""
                        var success = true
                        /*newPath = GetFileUtil.getSDCardPath().toString() + "JYMJManager/uploader/Pictures/" + file.name
                        success = ImageUtils.saveExifAndCompressByQuality(i, newPath, 1000, true)*/
                        val name = file.name
//                    val bitmap = BitmapFactory.decodeFile(file.path)
                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        //Bitmap comp = comp(bitmap);
                        //Bitmap comp = comp(bitmap);
                        val file1: File = compressImages(bitmap, name)
                        if (success) {
//                        val file2 = File(newPath)
                            //EnviorSupvsEnum.getIndex(tvWtztEnvironmental.selectedItem.toString())
                            upFile1(serializableExtra!!,EditText(this), file1)//, intList, se, 99
                        } else {
                            LoadingDialog.cancelDialogForLoading()
                            break
                        }
                    }
                }
                if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
            }else if(requestCode == 66){
//                            selectedVideos.clear()
                var uri = data!!.getData()

                if (uri != null){
                    //selectedVideos.add(uri.path+".mp4")
                    selectedVideos.add(getRealPathFromURI(uri))

                }
                if (videoAdapter != null) videoAdapter!!.notifyDataSetChanged()

                if (selectedVideos.size >= 1){

//                    for (i in selectedVideos) {

                        /*val file = File(getRealPathFromURI(uri))
                        upVideo(file)*/
                        var sdf = SimpleDateFormat("dd-MM-yyyy");
                        var c = Calendar.getInstance();
                        var date = sdf.format(c.getTime());
                        var path= AppMenus.getInstance().cardPath+"jymj/tzrjhj/map/video/"//+date+".mp4"
                        /*try {

                        }*/
                        object :Thread() {
                            override fun run() {
                                super.run()
                                HttpUtils.runOnUiThread(object : Runnable {
                                    override fun run() {
                                        LoadingDialog.showDialogForLoading(this@HBJCDetailActivity)
                                    }

                                })
                                var s = 0
                                var file=File(path)
                                if (!file.exists()){
                                    file.mkdirs()
                                }
                                LoadingDialog.showDialogForLoading(this@HBJCDetailActivity)
                                upVideo(file)
                                /*try {
                                    *//**
                                     * 视频压缩
                                     * 第一个参数:视频源文件路径
                                     * 第二个参数:压缩后视频保存的路径
                                     *//*
                                    var comPressPath = SiliCompressor.with(this@HBJCDetailActivity).compressVideo(uri?.let { getRealPathFromURI(it) }, path,1280,
                                            720,
                                            2000000);//Video
                                    file = File(comPressPath)

                                } catch ( e: URISyntaxException) {
                                    s=1
                                    e.printStackTrace();
                                }finally {
                                    if (s!=1){
                                        HttpUtils.runOnUiThread(object : Runnable {
                                            override fun run() {
                                                LoadingDialog.showDialogForLoading(this@HBJCDetailActivity)
                                                upVideo(file)
                                            }

                                        })
                                    }
                                }*/



                            }
                        }.start();

//                    }
                }
            }else if (requestCode==20){//ArrayList<String>
                var photoLists = data!!.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (photoLists != null ) {//&& !photoLists.isEmpty()
                    selectedPhotos = photoLists
                    /*for (i in photoLists){
                        selectedPhotos.remove(i)
                    }*/
                    if (photoAdapter!=null){
                        photoAdapter!!.setNewData(selectedPhotos)
//                        photoAdapter!!.notifyDataSetChanged()
                    }
                }
//                    mAddPicture.setPaths(mImagePaths);
            }

        }
    }

    fun getRealPathFromURI(contentUri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = managedQuery(contentUri, proj, null, null, null)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventData(event: String) {
        if (event.equals("修改成功")){
            if (hbjcEntity != null) {
                et_hbjcdetail_address.setText(hbjcEntity!!.wz)
                et_hbjcdetail_problem.setText(hbjcEntity!!.questions)
                et_hbjcdetail_jlr.setText(hbjcEntity!!.jlr)
                et_hbjcdetail_phone.setText(hbjcEntity!!.jlrtel)
                et_hbjcdetail_time.setText(hbjcEntity!!.jltime)
                et_hbjcdetail_jlremark.setText(hbjcEntity!!.remark)

                et_hbjcdetail_zgyq.setText(hbjcEntity!!.zgyq)
                et_hbjcdetail_clr.setText(hbjcEntity!!.ksname)
                et_hbjcdetail_clr_phone.setText(hbjcEntity!!.kstel)
                et_hbjcdetail_clremark.setText(hbjcEntity!!.ksbz)

                et_hbjcdetail_yspj.setText(hbjcEntity!!.cgpj)
                et_hbjcdetail_ysr.setText(hbjcEntity!!.cgname)
                et_hbjcdetail_ystime.setText(hbjcEntity!!.cgtime)
                et_hbjcdetail_ysremark.setText(hbjcEntity!!.cgbz)
            }
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        EventBus.getDefault().post("返回刷新")
        return super.onKeyDown(keyCode, event)
    }

}
