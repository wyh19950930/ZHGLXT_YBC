package com.jymj.zhglxt.rjhj.fragment


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.*
import com.amap.api.maps2d.model.*
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.basemessagesystem.ui.views.LegendEntity

import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.login.model.RjhjModel
import com.jymj.zhglxt.rjhj.activity.HBJCDetailActivity
import com.jymj.zhglxt.rjhj.adapter.EnvironmentalAdapter
import com.jymj.zhglxt.rjhj.adapter.HbAdapter
import com.jymj.zhglxt.rjhj.adapter.RightArrowAdapter
import com.jymj.zhglxt.rjhj.adapter.VideoAdapter
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.bean.enums.*
import com.jymj.zhglxt.rjhj.contract.RjhjContract
import com.jymj.zhglxt.rjhj.presenter.RjhjPresenter
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.BubbleView
import com.jymj.zhglxt.widget.RecyclerViewNoBugLinearLayoutManager
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.zjd.bean.bcjc.BccyjgEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.lzy.okgo.utils.HttpUtils
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.fragment_rjhj.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import org.json.JSONObject
import java.io.*
import java.math.BigDecimal
import java.net.URISyntaxException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *??? ???????????????????????????
 */
class RjhjFragment : BaseFragment<RjhjPresenter, RjhjModel>(),RjhjContract.View ,AMap.OnMapClickListener, AMapLocationListener {
    override fun returnBcjcUpdateCyjg(msg: String) {

    }

    override fun returnBchcCyjgAll(msg: HashMap<String, ArrayList<BccyjgEntity>>) {

    }

    override fun returnBcjcYears(msg: List<String>) {
    }

    override fun returnBcjcAddCyjg(msg: String) {
    }

    override fun returnBchcCyjg(msg: HashMap<String,ArrayList<BccyjgEntity>>) {
    }


    //    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private var aMap: AMap? = null
    private var mPolygon: Polygon? = null
    private val mLatLngs = ArrayList<LatLng>()
    private var mMyLocationStyle: MyLocationStyle? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var mPolylineOptions: PolylineOptions? = null
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
    var aMapLocation: AMapLocation? = null//????????????
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

    private val IMAGE_REQUEST_CODE = 1
    var mListener: LocationSource.OnLocationChangedListener? = null
    var mLocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    private var pointStr = ""
    private var addMarker: Marker? = null
    private var onCameraChange: LatLng? = null
    val MODE_NORMAL = 100//???????????????
    val POINT_CLICK = 201//???????????????
    val POLYGON_CLICK = 202//???????????????
    val POINT_TIANJIA = 301
    val MODE_POINT = 1001
    val MODE_ADD = 1002
    val MODE_RJHJ = 1044//????????????
    val MODE_RJHJJC = 1045//??????????????????
    val MODE_RJHJLR = 1045//??????????????????
    val MODE_RJHJFj = 1047//????????????????????????
    private var mLatLng: LatLng? = null
    private var click_mode = POINT_CLICK//MODE_NORMAL
    private var mode: Int = MODE_RJHJ
    private var ifxstp=0  //1???????????? 2???????????????
    val colors = java.util.ArrayList<Int>()//????????????????????????
    var markers = ArrayList<Marker>()//?????????????????????
    private var  addPolyline: Polyline? = null//????????????
    var pointMarker: Marker? = null
    private var cunMing = "" //??????  ??????????????????????????????
    private var selectedPhotos = ArrayList<String>()
    private val selectedVideos = ArrayList<String>()
    private val videoIdList = ArrayList<Int>()
    private val fileIdList = ArrayList<Int>()
    private var typeLx = 1//?????????????????????????????????  ???????????????????????????????????????
    private var PjEnviorFileList = ArrayList<PjEnviorFileEntity>()
    private var sjfl = ""
    private var envirorUpPopu: CommenPop? = null//????????????
    private var tvUpDateEnviron: TextView? = null
    private var tvUploadMeetingSummary: Button? = null
    private var bt_temporary_environmental: Button? = null
    private var photoAdapter: PhotoAdapter? = null//???????????????
    private var schxtp:BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null//
    private var videoAdapter: VideoAdapter? = null
    private var uploadDate: String = ""
    private var isls=0//????????????????????????????????? 0 ?????????   1 ??????????????????
    private val enviorListEntity = ArrayList<PjEnviorSupvsEntity>()//??????????????????
    private var isShow = 0////?????????????????????????????????  0????????????  ??????????????????(1????????????????????????  2??????????????????????????????)  ????????????(3?????????????????? 4??????????????????)
    private var renovatedFileList = ArrayList<RenovatedFile>()//????????????
    private var gcImageURL1 = ""
    private var gcImageURL2 = ""
    private var dingDianUpPopu: CommenPop? = null
    private var gddwPage = 1//????????????????????????????????????
    private val gddwList = ArrayList<PointRecordEntity>()//???????????????????????????
    private var gcImageType = 1
    private val mCustomItems = arrayOf("????????????", "????????????")
    var SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().path + "/tzrjhjCamera/"// ????????????
    private var cameraPath: String? = null
    private val TAKE_PHOTO_REQUEST = 5
    private var hbjcList: List<PjEnviorSupvsEntity>? = null
    var linearLayoutManager:RecyclerViewNoBugLinearLayoutManager? = null
    private var environmentalAdapter1 : EnvironmentalAdapter ?=null
    private var adapterFlag = 0
    private var hbjcPosition = 0
    private var isImage=2 //0 ???????????????  1????????????  2????????????
    private var path: String? = null

    private var lastSelect = 1
    private var page = 1
    private var limit = 20
    private var cunCode = ""
    private var hjzzsjCha = 0//??????????????????
    private var zdwtCha = -1//??????????????????????????????
    private var date =""//????????????
    private var startDate = ""
    private var endDate = ""
    private var code ="";//???
    private var bh = 0//????????????
    var tabString = arrayOf("????????????", "????????????")
    var timePickerView: TimePickerView? = null//???????????????
    var optionPickerView: OptionsPickerView<Any>? = null//???????????????

    companion object {
        /**
         * ?????????????????????????????????????????????????????????
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): RjhjFragment {
            return RjhjFragment().apply {
                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_rjhj
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//???????????????

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_frag_rjhj_dt.onCreate(activity!!.intent.extras)//???????????????
        initAMap()//???????????????
        setAMap()//???????????????
        initTuli()//????????????
        initTimePickerView()//
//        MainActivity.ibActMain!!.setImageResource(R.drawable.tc_kong)//????????????????????????
        //????????????????????????
       /* MainActivity.ibActMain!!.setOnClickListener {

        }*/
        cunCode = AppCache.getInstance().rjhjCode
        for (i in tabString) {
            tlt_frag_rjhj.addTab(tlt_frag_rjhj.newTab().setText(i))
        }//????????????", "????????????
        tlt_frag_rjhj.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text!!.equals("????????????")){
                    mode = MODE_RJHJ
                    ll_frag_rjhj_but.visibility = View.VISIBLE//????????????????????????
                    ll_frag_gddw_but.visibility = View.GONE//????????????????????????
                    tv_frag_rjhj_add.visibility = View.GONE//?????????????????????
                    ll_frag_rjhj_list.visibility = View.VISIBLE//
                    ll_frag_gddw_list.visibility = View.GONE//

                    page = 1
                    enviorListEntity.clear()
                    adapterFlag = 0
                    mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
                    mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)

                }else if (tab!!.text!!.equals("????????????")){
                    mode = MODE_RJHJJC
                    ll_frag_rjhj_but.visibility = View.GONE//??????????????????
                    ll_frag_gddw_but.visibility = View.VISIBLE//??????????????????
                    tv_frag_rjhj_add.visibility = View.VISIBLE//?????????????????????
                    ll_frag_rjhj_list.visibility = View.GONE//
                    ll_frag_gddw_list.visibility = View.VISIBLE//

                    gddwPage = 1
                    gddwList.clear()
                    mPresenter.getDdList(AppCache.getInstance().userId.toString(),gddwPage)//????????????????????????
                    clearMap()

                }
//                ToastUtils.showShort(tab!!.position.toString())
            }
        })
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){//????????????

            rl_frag_rjhj_wxf.visibility = View.VISIBLE//?????????
            rl_frag_rjhj_xc.visibility = View.VISIBLE//??????
            rl_frag_rjhj_zg.visibility = View.GONE//??????
            rl_frag_rjhj_sh.visibility = View.GONE//??????
            rl_frag_rjhj_xz.visibility = View.GONE//??????
            lastSelect = 0
            view_frag_rjhj_wxf_tab.visibility = View.VISIBLE//?????????
            view_frag_rjhj_xc_tab.visibility = View.GONE//??????
            view_frag_rjhj_zg_tab.visibility = View.GONE//??????
            view_frag_rjhj_sh_tab.visibility = View.GONE//??????
            view_frag_rjhj_xz_tab.visibility = View.GONE//??????


            environmental_tab_sfws.visibility = View.VISIBLE
            environmental_tab_zt.visibility = View.VISIBLE

        }else if (AppCache.getInstance().type==ApiConstants.RJHJ_WY){//??????
            rl_frag_rjhj_wxf.visibility = View.GONE//?????????
            rl_frag_rjhj_xc.visibility = View.GONE//??????
            rl_frag_rjhj_zg.visibility = View.VISIBLE//??????
            rl_frag_rjhj_sh.visibility = View.GONE//??????
            rl_frag_rjhj_xz.visibility = View.GONE//??????
            lastSelect = 3
            view_frag_rjhj_wxf_tab.visibility = View.GONE//?????????
            view_frag_rjhj_xc_tab.visibility = View.GONE//??????
            view_frag_rjhj_zg_tab.visibility = View.VISIBLE//??????
            view_frag_rjhj_sh_tab.visibility = View.GONE//??????
            view_frag_rjhj_xz_tab.visibility = View.GONE//??????
        }else if (AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF){//?????????
            rl_frag_rjhj_wxf.visibility = View.GONE//?????????
            rl_frag_rjhj_xc.visibility = View.VISIBLE//??????
            rl_frag_rjhj_zg.visibility = View.VISIBLE//??????
            rl_frag_rjhj_sh.visibility = View.VISIBLE//??????
            rl_frag_rjhj_xz.visibility = View.VISIBLE//??????
            lastSelect = 1
            view_frag_rjhj_wxf_tab.visibility = View.GONE//?????????
            view_frag_rjhj_xc_tab.visibility = View.VISIBLE//??????
            view_frag_rjhj_zg_tab.visibility = View.GONE//??????
            view_frag_rjhj_sh_tab.visibility = View.GONE//??????
            view_frag_rjhj_xz_tab.visibility = View.GONE//??????
        }
        enviorListEntity.clear()
        mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
        mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        //?????????
        rl_frag_rjhj_wxf.setOnClickListener {
            view_frag_rjhj_wxf_tab.visibility = View.VISIBLE//?????????
            view_frag_rjhj_xc_tab.visibility = View.GONE//??????
            view_frag_rjhj_zg_tab.visibility = View.GONE//??????
            view_frag_rjhj_sh_tab.visibility = View.GONE//??????
            view_frag_rjhj_xz_tab.visibility = View.GONE//??????
            lastSelect = 0
            page = 1
            enviorListEntity.clear()
            adapterFlag = 0
            mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        }
        //??????
        rl_frag_rjhj_xc.setOnClickListener {
            view_frag_rjhj_wxf_tab.visibility = View.GONE//?????????
            view_frag_rjhj_xc_tab.visibility = View.VISIBLE//??????
            view_frag_rjhj_zg_tab.visibility = View.GONE//??????
            view_frag_rjhj_sh_tab.visibility = View.GONE//??????
            view_frag_rjhj_xz_tab.visibility = View.GONE//??????
            lastSelect = 1
            page = 1
            enviorListEntity.clear()
            adapterFlag = 0
            mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        }
        //??????
        rl_frag_rjhj_zg.setOnClickListener {
            view_frag_rjhj_wxf_tab.visibility = View.GONE//?????????
            view_frag_rjhj_xc_tab.visibility = View.GONE//??????
            view_frag_rjhj_zg_tab.visibility = View.VISIBLE//??????
            view_frag_rjhj_sh_tab.visibility = View.GONE//??????
            view_frag_rjhj_xz_tab.visibility = View.GONE//??????
            lastSelect = 3
            page = 1
            enviorListEntity.clear()
            adapterFlag = 0
            mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        }
        //??????
        rl_frag_rjhj_sh.setOnClickListener {
            view_frag_rjhj_wxf_tab.visibility = View.GONE//?????????
            view_frag_rjhj_xc_tab.visibility = View.GONE//??????
            view_frag_rjhj_zg_tab.visibility = View.GONE//??????
            view_frag_rjhj_sh_tab.visibility = View.VISIBLE//??????
            view_frag_rjhj_xz_tab.visibility = View.GONE//??????
            lastSelect = 4
            page = 1
            enviorListEntity.clear()
            adapterFlag = 0
            mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        }
        //??????
        rl_frag_rjhj_xz.setOnClickListener {
            view_frag_rjhj_wxf_tab.visibility = View.GONE//?????????
            view_frag_rjhj_xc_tab.visibility = View.GONE//??????
            view_frag_rjhj_zg_tab.visibility = View.GONE//??????
            view_frag_rjhj_sh_tab.visibility = View.GONE//??????
            view_frag_rjhj_xz_tab.visibility = View.VISIBLE//??????
            lastSelect = 5
            page = 1
            enviorListEntity.clear()
            adapterFlag = 0
            mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        }
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WY){//??????????????????????????????????????????????????????????????????????????????
            tlt_frag_rjhj.visibility = View.GONE
        }
        //??????????????????
        iv_frag_gddw_add.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                isShow = 3
                val point1 = LatLng(onCameraChange!!.latitude, onCameraChange!!.longitude)//onCameraChange!!.latitude, onCameraChange!!.longitude
                pointStr = "POINT(" + point1.longitude + " " + point1.latitude + ")"
                mPresenter.getRjhjjcPoint(pointStr)
//            mPresenter.getEnviorSupvsQueryPoint(pointStr)
                //??????marker
                if (addMarker!=null){
                    addMarker!!.remove()
                }
                val draggable = MarkerOptions()
                        .position(point1)
                        .draggable(false)

                addMarker = aMap!!.addMarker(draggable)
            }
        }
        //??????????????????
        iv_frag_gddw_update.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                isShow = 4
                val point1 = LatLng(onCameraChange!!.latitude, onCameraChange!!.longitude)
                pointStr = "POINT(" + point1.longitude + " " + point1.latitude + ")"
                mPresenter.getRjhjjcPoint(pointStr)
//            mPresenter.getEnviorSupvsQueryPoint(pointStr)
                //??????marker
                if (addMarker!=null){
                    addMarker!!.remove()
                }
                val draggable = MarkerOptions()
                        .position(point1)
                        .draggable(false)

                addMarker = aMap!!.addMarker(draggable)
            }
        }
        //??????
        iv_frag_rjhj_location.setOnClickListener {
            //??????
            if (aMapLocation != null) {
                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//????????????  ???????????? ??? 2000
                val point = LatLng(model.wgLat, model.wgLon)
//            val point = LatLng(myLocation.latitude, myLocation.longitude)
                Log.e("????????????", model.wgLat.toString() + "+" + model.wgLon.toString())
                if (point.latitude > 0 && point.longitude > 0) {//point.longitude
                    aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude,
                            point.longitude)))
                    pointMarker = aMap?.addMarker(MarkerOptions().position(point))

                    var pointString = "POINT(" + point.longitude + " " + point.latitude + ")"
                    //????????????
                } else {
                    Thread(object : Runnable {
                        override fun run() {
                            try {
                                Thread.sleep(2000); // ??????3???
                            } catch (e: Exception) {
                                e.printStackTrace();
                            }
                            val model1 = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
                            val point1 = LatLng(model1.wgLat, model1.wgLon)
//            val point = LatLng(myLocation.latitude, myLocation.longitude)
                            Log.e("????????????", model1.wgLat.toString() + "+" + model1.wgLon.toString())
                            if (point1.latitude > 0 && point1.longitude > 0) {
                                aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point1.latitude,
                                        point1.longitude)))
                                pointMarker = aMap?.addMarker(MarkerOptions().position(point1))

                                var pointString1 = "POINT(" + point1.longitude + " " + point1.latitude + ")"
                                //????????????
                            } else {
                                Looper.prepare()
                                ToastUtils.showShort("")
                                Looper.loop()
                            }
                        }
                    }).start();
                }
            }

        }
        aMap!!.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            //???????????????????????????????????????
            override fun onCameraChangeFinish(p0: CameraPosition?) {
                onCameraChange = p0!!.target
                val visibleRegion = aMap!!.projection.visibleRegion
                val farLeft = visibleRegion.farLeft //???????????????????????????  position1
//               val farLeft = PositionUtil.gcj_To_Gps84(position1.latitude, position1.longitude)
                val farRight = visibleRegion.farRight //???????????????????????????
                val nearLeft = visibleRegion.nearLeft //???????????????????????????
                val nearright = visibleRegion.nearRight //???????????????????????????position2
                val zoom = aMap!!.cameraPosition.zoom
                if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF) {
                    if (code.length==9){
                        /*if (zoom<9){
                            mPresenter.getEnviorsByxy("1", BigDecimal(farLeft.longitude),BigDecimal(nearright.longitude),BigDecimal(nearright.latitude),BigDecimal(farLeft.latitude),isImage)
                        }else */if (zoom<13){
                            mPresenter.getEnviorsByxy("2", BigDecimal(farLeft.longitude),BigDecimal(nearright.longitude),BigDecimal(nearright.latitude),BigDecimal(farLeft.latitude),isImage,code,1)
                        }else if (zoom<15.5){//15
                            mPresenter.getEnviorsByxy("3", BigDecimal(farLeft.longitude),BigDecimal(nearright.longitude),BigDecimal(nearright.latitude),BigDecimal(farLeft.latitude),isImage,code,1)
                        }else{
                            mPresenter.getEnviorsByxy("4", BigDecimal(farLeft.longitude),BigDecimal(nearright.longitude),BigDecimal(nearright.latitude),BigDecimal(farLeft.latitude),isImage,code,1)
                        }
                    }
                }
                if (pointMarker!=null){
                    pointMarker!!.remove()
                }
                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//????????????  ???????????? ??? 2000
                val point = LatLng(model.wgLat, model.wgLon)
                pointMarker = aMap?.addMarker(MarkerOptions().position(point))
            }
            override fun onCameraChange(p0: CameraPosition?) {
                if (p0!!.zoom > 18.5) {
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(18.5f))
                }
                onCameraChange = p0!!.target
            }
        })
        //????????????
        ll_frag_rjhj_sjtime.setOnClickListener {
            timePickerView!!.show()
        }
        //????????????
        ll_frag_rjhj_czmc.setOnClickListener {
            initoptionPickerView("????????????")
        }
        //????????????
        ll_frag_rjhj_wtlx.setOnClickListener {
            initoptionPickerView("????????????")
        }
        //????????????????????????
        ll_frag_rjhj_zdwt.setOnClickListener {
            initoptionPickerView("????????????")
        }
        //???????????????????????????????????????
        iv_frag_rjhj_point.setOnClickListener {
            isShow = 2
            if (aMapLocation!=null){

//                try {
                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
                var point: LatLng? = null
                if (AppMenus.getInstance().textCenter.equals("")){
                    point = LatLng(model.wgLat, model.wgLon)
                }else{
                    point = getCenter(AppMenus.getInstance().textCenter)
                }

                aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude, point.longitude)))
                aMap?.addMarker(MarkerOptions().position(point))
                pointStr = "POINT(" + point.longitude + " " + point.latitude + ")"
                mPresenter.getRjhjjcPoint(pointStr)/////////////////////////

            }
        }
        //???????????????????????????
        iv_frag_rjhj_clear.setOnClickListener {
            clearMap()
        }
        //????????????
        iv_frag_rjhj_location.setOnClickListener {
            if (aMapLocation != null){
                val model= PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//????????????  ???????????? ??? 2000
                val point = LatLng(model.wgLat, model.wgLon)
//            val point = LatLng(myLocation.latitude, myLocation.longitude)
                Log.e("????????????",model.wgLat.toString()+"+"+ model.wgLon.toString())
                if (point.latitude>0&&point.longitude>0){//point.longitude
                    aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude,
                            point.longitude)))
                    aMap?.addMarker(MarkerOptions().position(point))

                    var pointString = "POINT("+point.longitude+" "+point.latitude+")"
                    if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR){
                        if (mode == MODE_RJHJ){//pointString
                            ifxstp = 1
                            mPresenter.getEnviorSupvsQueryPoint(pointString)//"POINT(116.846966 39.797217)"  pointString
                        }
                    }
                }else{
                    Thread(object: Runnable {
                        override fun run() {
                            try {
                                Thread.sleep(2000); // ??????3???
                            } catch ( e :InterruptedException) {
                                e.printStackTrace();
                            }
                            val model1= PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
                            val point1 = LatLng(model1.wgLat, model1.wgLon)
//            val point = LatLng(myLocation.latitude, myLocation.longitude)
                            Log.e("????????????",model1.wgLat.toString()+"+"+ model1.wgLon.toString())
                            if (point1.latitude>0&&point1.longitude>0){
                                aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point1.latitude,
                                        point1.longitude)))
                                aMap?.addMarker(MarkerOptions().position(point1))

                                var pointString1 = "POINT("+point1.longitude+" "+point1.latitude+")"
                                if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR){
                                    if (mode == MODE_RJHJ){//pointString
                                        ifxstp = 1
                                        mPresenter.getEnviorSupvsQueryPoint(pointString1)//"POINT(116.846966 39.797217)"  pointString
                                    }
                                }
                            }else{
                                Looper.prepare()
                                ToastUtils.showShort("")
                                Looper.loop()
                            }
                        }
                    }).start();
                    try {

                    }finally {

                    }

                }


            }
        }
//        MainActivity.tvTitle!!.setText("????????????")
        if (AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type== ApiConstants.LDRK_ZZF){//???????????????????????????????????????
//            MainActivity.mainTvFirst!!.visibility = View.GONE//????????????????????????
//            MainActivity.mainTvBack!!.visibility = View.VISIBLE//????????????????????????????????????
            tlt_frag_rjhj.visibility = View.VISIBLE//??????tab??????
            /*MainActivity.mainTvBack!!.setOnClickListener {
                AppCache.getInstance().rjhjCode = ""
                AppCache.getInstance().rjhjCenter = ""
                FragmentUtils.replaceFragment(activity!!.supportFragmentManager, MainActivity.rjhjFirstFragment!!,R.id.fl_content,false)
            }*/
        }else{
            AppCache.getInstance().rjhjCode = AppCache.getInstance().code
            AppCache.getInstance().rjhjCenter = AppCache.getInstance().loginCenter
        }
        if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){//?????????????????????????????????????????????
            tlt_frag_rjhj.visibility = View.VISIBLE//??????tab??????
            ll_frag_rjhj_point.visibility = View.VISIBLE//??????????????????
            view_frag_rjhj_point_clear.visibility = View.VISIBLE//???????????????????????????
        }
        //????????????
        tv_act_czlb_search.setOnClickListener {
            val bhString = cet_frag_rjhj_cm_lgy.text.toString()//bhString
            if (bhString.equals("")){
                bh = 0
            }else{
                bh = bhString.toInt()
            }
            page = 1
            enviorListEntity.clear()
            mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        }
        //????????????
        iv_frag_rjhj_reset.setOnClickListener {
            tv_frag_rjhj_sjtime.setText("")
            cet_frag_rjhj_cm_lgy.setText("")
            tv_frag_rjhj_czmc.setText("????????????")
            tv_frag_rjhj_wtlx.setText("????????????")
            tv_frag_rjhj_zdwt.setText("????????????")
            tv_frag_rjhj_czmc.setTextColor(Color.parseColor("#333333"))
            tv_frag_rjhj_wtlx.setTextColor(Color.parseColor("#333333"))
            tv_frag_rjhj_zdwt.setTextColor(Color.parseColor("#333333"))
            page = 1
            cunCode = AppCache.getInstance().code
            date = ""
            bh = 0
            zdwtCha = -1
            hjzzsjCha = 0
            enviorListEntity.clear()
            mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        }

    }

        override fun initDatas() {
            getXzqList(AppCache.getInstance().code,3)
            //????????????????????????
            Thread(object : Runnable {
                override fun run() {
                    try {
                        Thread.sleep(1500); // ??????3???
                        val point = getCenter(AppCache.getInstance().rjhjCenter)
                        if (AppCache.getInstance().rjhjCode.length == 9) {
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
    override fun onMapClick(p0: LatLng?) {
        when (click_mode) {
            POINT_CLICK -> {
                queryByPoint(p0!!)
                mLatLng = p0
            }
            POLYGON_CLICK -> queryByPolygon(p0!!)
        }
    }

    private fun queryByPoint(latLng: LatLng) {
        if (SingleOnClickUtil.isFastClick()){

            if (addMarker!=null){
                addMarker!!.remove()
            }
            val markerOptions = MarkerOptions()
            addMarker = aMap!!.addMarker(markerOptions.position(latLng))

            pointStr = "POINT(" + latLng.longitude + " " + latLng.latitude + ")"

            when (mode) {
               /* MODE_POINT->{//??????
                    mPresenter.getMap("", pointStr)
                }
                MODE_ADD->{//??????
                    mPresenter.getWfxcCha("",pointStr ,"3")
                }
                MODE_RJHJFj->{//??????
                    mPresenter.getFanJian(pointStr)//pointStr  POINT(116.884128170077 39.7386618006633)
                }*/
                MODE_RJHJ -> {
                    if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){
                        if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){//?????????????????????????????????????????????????????????
                            isShow = 1
                            mPresenter.getRjhjjcPoint(pointStr)
                        }else{
                            isShow = 2
//                        mPresenter.getRjhjjcPoint(pointStr)
                            /* if (rjhj_bt_point.isActivated){
                                mPresenter.getRjhjPoint(pointStr)
                            }else{
                            }*/
                        }
                    }
                }
                MODE_RJHJJC -> {
                    mPresenter.getRjhjjcPoint(pointStr)
                }
            }
        }
    }
    private fun queryByPolygon(latLng: LatLng) {
        mLatLngs.add(latLng)
        if (mPolygon != null) mPolygon!!.remove()
        mPolygonOptions!!.points.clear()
        mPolygonOptions!!.addAll(mLatLngs)
        mPolygonOptions!!.strokeWidth(5f) // ??????????????????
                .strokeColor(Color.argb(255, 255, 1, 1)) // ????????????
                .fillColor(Color.argb(50, 177, 152, 229)) // ?????????????????????
        if (mPolygonOptions!!.points.size <= 1) {
            aMap!!.addMarker(MarkerOptions().position(latLng))
        } else {
            clearAllMarker()
        }
        mPolygon = aMap!!.addPolygon(mPolygonOptions)
    }
    //?????????????????????
    private fun location() {
        //???????????????
        mLocationClient = AMapLocationClient(activity!!.getApplicationContext())
        //????????????????????????
        mLocationClient!!.setLocationListener(this)
        //?????????????????????
        mLocationOption = AMapLocationClientOption()
        //????????????????????? Hight_Accuracy ??????????????????Battery_Saving ?????????????????????Device_Sensors??????????????????
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
//        mLocationOption.
        //????????????????????????????????????????????????????????????
// mLocationOption!!.setNeedAddress(true);
        //???????????????????????????,?????????false
        mLocationOption!!.setOnceLocation(false)
        //????????????3s???????????????????????????????????????
//??????setOnceLocationLatest(boolean b)?????????true??????????????????SDK???????????????3s?????????????????????????????????????????????????????????true???setOnceLocation(boolean b)????????????????????????true???????????????????????????false???
        mLocationOption!!.setOnceLocationLatest(true)
        //????????????????????????WIFI????????????????????????
// mLocationOption!!.setWifiActiveScan(true);
        //??????????????????????????????,?????????false????????????????????????
        mLocationOption!!.setMockEnable(false)
        mLocationOption!!.setLocationCacheEnable(false)
        //??????????????????,????????????,?????????2000ms
// mLocationOption!!.setInterval(2000);
        //??????????????????????????????????????????
        mLocationClient!!.setLocationOption(mLocationOption)
        //????????????
        mLocationClient!!.startLocation()
//        mLocationClient!!.stopLocation()
    }

    var isFirstLoc = true
    var mLocMarker:Marker? = null
    override fun onLocationChanged(p0: AMapLocation?) {
        if (p0 != null) {
            this.aMapLocation = p0
            val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
            val point = LatLng(model.wgLat, model.wgLon)
            if (mLocMarker==null){
                val mInflater = LayoutInflater.from(context)
                val view1: View = mInflater.inflate(R.layout.item_image_zhizhen, null)
                val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_image_zhizhen)
                ivItemMarker.setImageResource(R.drawable.ic__zhizhen)
                var bitmap = convertViewToBitmap(view1)
                mLocMarker = aMap?.addMarker(MarkerOptions().position(point))//.icon(BitmapDescriptorFactory.fromBitmap(bitmap)))//.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
//                mLocMarker!!.setRotateAngle(zhiZhenFx)
            }

            if (aMapLocation!!.getErrorCode() == 0) {
                //?????????????????????????????????????????????
                aMapLocation!!.getLocationType();//????????????????????????????????????????????????????????????????????????????????????
                aMapLocation!!.getLatitude();//????????????
                aMapLocation!!.getLongitude();//????????????
                aMapLocation!!.getAccuracy();//??????????????????
                var df =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                var date = Date(aMapLocation!!.getTime());
                df.format(date);//????????????
                aMapLocation!!.getAddress();//???????????????option?????????isNeedAddress???false??????????????????????????????????????????????????????????????????GPS??????????????????????????????
                aMapLocation!!.getCountry();//????????????
                aMapLocation!!.getProvince();//?????????
                aMapLocation!!.getCity();//????????????
                aMapLocation!!.getDistrict();//????????????
                aMapLocation!!.getStreet();//????????????
                aMapLocation!!.getStreetNum();//?????????????????????
                aMapLocation!!.getCityCode();//????????????
                aMapLocation!!.getAdCode();//????????????

                // ???????????????????????????????????????????????????????????????????????????????????????????????????
                //     isFirstLoc = false;
                if (isFirstLoc) {
                    isFirstLoc = false;
                    //??????????????????
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f));
                    //???????????????????????????
                    val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
                    val point = LatLng(model.wgLat, model.wgLon)
                    aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(point));//LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())
                    aMap?.addMarker(MarkerOptions().position(point))
                    //?????????????????? ??????????????????????????????????????????
                    mListener!!.onLocationChanged(aMapLocation);
                    //????????????
                    // aMap.addMarker(getMarkerOptions(amapLocation));
                    // ??????????????????
                    var buffer = StringBuffer();
                    buffer.append(aMapLocation!!.getCountry() + ""
                            + aMapLocation!!.getProvince() + ""
                            + aMapLocation!!.getCity() + ""
                            + aMapLocation!!.getProvince() + ""
                            + aMapLocation!!.getDistrict() + ""
                            + aMapLocation!!.getStreet() + ""
                            + aMapLocation!!.getStreetNum())
                    Toast.makeText(activity!!.getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();


                }


            } else {
                //??????????????????ErrCode???????????????errInfo???????????????????????????????????????
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation!!.getErrorCode() + ", errInfo:"
                        + aMapLocation!!.getErrorInfo());
// Toast.makeText(activity!!.getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
            }
        }
    }

    private fun initTuli() {

        //????????????
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!
        for (i in mLayers) {//??????
            i.isCheck = false
            when (i.name) {
                "?????????" -> {
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
//                            aMap!!.animateCamera(CameraUpdateFfragory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
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
//                        aMap!!.animateCamera(CameraUpdateFfragory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))
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
    /**
     * ?????????AMap??????
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_frag_rjhj_dt.getMap()
        }
        setUpMap()
    }

    /**
     * ????????????amap?????????
     */
    private fun setUpMap() { // ???????????????????????????
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
    }

    /**
     * ????????????
     */
    private fun setAMap() { //????????????????????????????????????
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false // ???????????????
        aMap!!.uiSettings.isZoomControlsEnabled = false
        aMap!!.setMapLanguage(AMap.CHINESE)
        aMap!!.setOnMapClickListener(this)
        mPolygonOptions = PolygonOptions()
//        aMap!!.addTileOverlay(opt_tdtnN)
        location()
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
        for (m in markers) {//???????????????????????????
            m.remove()
        }
        /*val markerList = aMap!!.mapScreenMarkers
        for (m in markerList) {
            m.remove()
        }*/
    }

    fun getCenter(center: String): LatLng {
        if (center != null) {
            if (!center.equals("")) {
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
            } else {
                LatLng(0.0, 0.0)
            }
        }
        return LatLng(0.0, 0.0)
    }
    override fun onResume() {
        super.onResume()
        map_frag_rjhj_dt.onResume()
        if (AppCache.getInstance().isList==1){
            AppCache.getInstance().isList = 0
            if (AppCache.getInstance().isUpdateWt == 1){
                page = 1
                enviorListEntity.clear()
                mPresenter.getHbjcList(lastSelect,limit,page,cunCode,hjzzsjCha,date,bh,zdwtCha,1)
                mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
                AppCache.getInstance().isUpdateWt = 0
            }else{

            }

        }
    }

    override fun onStop() {
        super.onStop()
        if (mLocationClient != null)
            mLocationClient!!.stopLocation()
    }

    override fun onPause() {
        super.onPause()
        map_frag_rjhj_dt.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        map_frag_rjhj_dt.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map_frag_rjhj_dt.onSaveInstanceState(outState)
    }
    override fun returnHbjcList(cash: List<PjEnviorSupvsEntity>, roleid: Int, totalCount: Int) {
        enviorListEntity.addAll(cash)
        if (enviorListEntity.size>0&&rlv_frag_rjhj_wt!=null){
            if (enviorListEntity.get(0).qutype == 0||enviorListEntity.get(0).qutype == 1){
                environmental_tab_yj.visibility = View.GONE
                environmental_tab_bhcs.visibility = View.GONE
            }else{
                if (enviorListEntity.get(0).qutype != 5){//????????????????????????????????????????????????
                    environmental_tab_yj.visibility = View.VISIBLE
                }else{
                    environmental_tab_yj.visibility = View.GONE
                }
                environmental_tab_bhcs.visibility = View.VISIBLE
            }
            if (AppCache.getInstance().type==ApiConstants.RJHJ_WYLR){
                environmental_tab_bhcs.visibility = View.GONE
                environmental_tab_yj.visibility = View.GONE
            }
        }

        /*if (environmental_tab_name!=null)
            if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR){
                environmental_tab_name.visibility = View.GONE
            }else{
                environmental_tab_name.visibility = View.VISIBLE
            }*/
        if (tv_frag_rjhj_wxf!=null){
            hbjcList = enviorListEntity//recy_environmental_show

            linearLayoutManager = RecyclerViewNoBugLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)//LinearLayoutManager
            rlv_frag_rjhj_wt!!.layoutManager = linearLayoutManager

            rlv_frag_rjhj_wt.setHasFixedSize(false)
            if (adapterFlag==0){

                environmentalAdapter1 = EnvironmentalAdapter(context, enviorListEntity, lastSelect)
                rlv_frag_rjhj_wt!!.adapter = environmentalAdapter1
                adapterFlag = 1
            }
            environmentalAdapter1!!.setOnClickEnvironLister(object : EnvironmentalAdapter.OnClickEnvironLister {
                override fun onClick(position: Int) {
                    if (SingleOnClickUtil.isFastClick()){
                        this@RjhjFragment.hbjcPosition = position//??????????????????
                        val intent = Intent(activity, HBJCDetailActivity::class.java)
                        intent.putExtra("pjenvior", enviorListEntity.get(position))
                        intent.putExtra("code", enviorListEntity.get(position).code)//selectCode
                        startActivity(intent)
                    }
                }

                override fun onDeleteClick(position: Int) {
                    var dialog = SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    dialog?.titleText = "???????????????"
                    dialog?.confirmText = "??????"
                    dialog?.cancelText = "??????"
                    dialog?.showCancelButton(true)
                    dialog?.showContentText(false)
                    dialog?.show()
                    dialog?.setConfirmClickListener {
                        val encrypt = "[" + enviorListEntity.get(position).id + "]"//{"ids":}
//                    var sss = "{\"requestData\":\"" + encrypt + "\"}"
                        OkGo.post<String>(ApiConstants.ENVIORSUPVSDELETES).upJson(encrypt).execute(object :
                                BaseNet<String>() {
                            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                super.onStart(request)
                                LoadingDialog.showDialogForLoading(activity)
                            }
                            override fun onSuccess(response: Response<String>?) {
                                val cash = response?.body()
                                if (cash != null) {
//                                val decrypt = AesEncryptUtils.decrypt(cash)
                                    val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                    if (json.code == 0) {
                                        limit = 100
                                        page = 1
                                        enviorListEntity.clear()
                                        mPresenter.getHbjcList(lastSelect, limit,page,cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
//                                    }
                                        mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
                                        ToastUtils.showShort("????????????")
                                        dialog.dismiss()
                                    } else {
                                        ToastUtils.showShort("????????????")
                                        dialog.dismiss()
                                    }
                                } else {
                                    ToastUtils.showShort("????????????")
                                    dialog.dismiss()
                                }
                            }
                            override fun onFinish() {
                                super.onFinish()
                                LoadingDialog.cancelDialogForLoading()
                            }
                            override fun onError(response: Response<String>?) {
                                super.onError(response)
                                ToastUtils.showShort("????????????")
                                dialog.dismiss()
                            }
                        })//rjhj_sllv
                    }
                }
            })
            rlv_frag_rjhj_wt.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                    val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                    if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&enviorListEntity.size==page*limit) {
                        if (enviorListEntity.size !=0 && enviorListEntity.size%limit == 0){
                            page++
                            mPresenter.getHbjcList(lastSelect,limit,page,cunCode,hjzzsjCha,date,bh,zdwtCha,1)
                            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
                        }else{
                            ToastUtils.showShort("??????????????????")
                        }
                    }

                }
            })
            if (page!=1){
                rlv_frag_rjhj_wt.scrollToPosition((page-1)*limit)
            }
            aMap!!.clear()
            addOverLayer(mLayers)
            if ((hbjcList!=null&&code.length==12&&isImage==0)||(AppCache.getInstance().type!=ApiConstants.RJHJ_NY||AppCache.getInstance().type!=ApiConstants.LDRK_ZZF)){
                for (i in 0..hbjcList!!.size-1){
                    val location = hbjcList!!.get(i).location//POINT(116.372901 39.714748)
                    if (!location.equals("")){
                        val points = location.substring(6, location.length - 1).split(" ").toTypedArray()
                        if (points!=null){
                            if (points.size>1){
                                val sl = LatLng(points[1].toDouble(), points[0].toDouble())
                                if (hbjcList!!.get(i).monwarnin==0){
                                    if (hbjcList!!.get(i).zdwt==1){
                                        setMarker(location,sl.latitude,sl.longitude,R.drawable.ic_hbjc_dcl_zs)
                                    }else{
                                        setMarker(location,sl.latitude,sl.longitude,R.drawable.ic_hbjc_dcl_ls)
                                    }
                                }else{
                                    setMarker(location,sl.latitude,sl.longitude,R.drawable.ic_hbjc_dcl_hs)
                                }
                            }
                        }
                    }
                }
            }
            var stringList = ArrayList<String>()
            for(i in 0..enviorListEntity.size-1){
                stringList.add("")
            }
            //enviorListEntity
            //RightArrowAdapter
            rlv_frag_rjhj_jt.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            rlv_frag_rjhj_jt.adapter = RightArrowAdapter(activity,stringList)

        }

        LoadingDialog.cancelDialogForLoading()
    }

    fun setMarker( j: String, wd: Double, jd: Double, res: Int) { //,int mid  j: Int
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_marker, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position)
        ivItemMarker.setImageResource(res)
        itemMarkerPosition.text = j.toString() + ""
        val bitmap = convertViewToBitmap(view1)
        view1.setOnClickListener { Toast.makeText(context, "" + itemMarkerPosition.text.toString(), Toast.LENGTH_SHORT).show() }
        //??????marker
        aMap!!.addMarker(MarkerOptions()
                .position(LatLng(wd, jd))
                .snippet(j.toString() + "") //                .period(mid)//??????markerID
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .draggable(true))
        aMap!!.setOnMarkerClickListener { marker ->
            val type = AppCache.getInstance().type
            val snippet = marker.snippet
//            val i = snippet.toInt()
            mPresenter.getHbjcListCode(lastSelect,limit,1,code,hjzzsjCha,date,snippet,zdwtCha,1)//cunCode
            mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
            true
        }
    }
    override fun returnEnviorSupvsQueryPoint(ylPointEntity: WtlrBean) {
        if (ylPointEntity!=null){
            val pjEnviorSupvsEntities = ylPointEntity.getPjEnviorSupvsEntities()
            if (pjEnviorSupvsEntities.size>0){
                if (ll_envionmental_wtlist!=null||pop_gctype == 1)
                    ll_envionmental_wtlist!!.visibility =View.VISIBLE
                if (rlv_envionmental_wtlist!=null){
                    rlv_envionmental_wtlist!!.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)//tv_rjhj_wtlist
                    rlv_envionmental_wtlist!!.adapter = object: BaseQuickAdapter<PjEnviorSupvsEntity, BaseViewHolder>(R.layout.item_rjhj_wtlist,pjEnviorSupvsEntities){
                        override fun convert(helper: BaseViewHolder?, item: PjEnviorSupvsEntity?) {
                            helper!!.setText(R.id.tv_rjhj_wtlist, item!!.hjzzsjText)
                            helper.itemView.setOnClickListener {
                                if (SingleOnClickUtil.isFastClick()){//??????????????????
                                    val intent = Intent(activity, HBJCDetailActivity::class.java)
                                    intent.putExtra("pjenvior", item)
                                    startActivity(intent)
                                }

                            }
                        }

                    }

                }
                if (AppCache.getInstance().type == ApiConstants.RJHJ_WYLR){
                    if (mode == MODE_RJHJ&&ifxstp==1){
                        val arrayList = ArrayList<String>()
                        val arrayListWt = ArrayList<String>()
                        for (i in pjEnviorSupvsEntities){
                            for (s in i.pjEnviorFileEntities){
                                val path = s.path
                                val replace = path.replace("\\", "/")
                                val imagePath = ApiConstants.RELEASE_URL + replace
                                arrayList.add(imagePath)
                                arrayListWt.add(i.hjzzsjText)
                            }

                        }
                        val center1 = getCenter(pjEnviorSupvsEntities.get(0).location)
                        setImagesMarker(arrayList,arrayListWt,center1)
                        ifxstp = 0
                    }
                }

            }else{
                if (ll_envionmental_wtlist!=null&&pop_gctype == 0){
                    ll_envionmental_wtlist!!.visibility =View.GONE
                }else{
                    if (ll_envionmental_wtlist!=null){
                        ll_envionmental_wtlist!!.visibility =View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onHbLr(ylPointEntity: String) {

        bt_temporary_environmental!!.isEnabled = true
        tvUploadMeetingSummary!!.isEnabled = true

        ToastUtils.showShort("????????????")
        page = 1
        enviorListEntity.clear()
        mPresenter.getHbjcList(lastSelect,limit,page,cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
        mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
        /*if (AppCache.getInstance().type!=ApiConstants.RJHJ_WYLR)
            mPresenter.getHbjcZl(date,startDate,endDate,code)*/
        if (envirorUpPopu != null) {
            envirorUpPopu!!.dismiss()
        }
    }

    override fun returnCountCyCode(message: EnviorCunBean.DataBean) {//???????????????????????????
        if (tv_frag_rjhj_wxf!=null){
            tv_frag_rjhj_wxf.text = message.object6.toString()//???????????????
            tv_frag_rjhj_xc.text = message.object1.toString()//????????????
            tv_frag_rjhj_zg.text = message.object2.toString()//????????????
            tv_frag_rjhj_sh.text = message.object3.toString()//????????????
            tv_frag_rjhj_xz.text = message.object4.toString()//????????????
        }
    }

    override fun onHbjcZl(ylPointEntity: List<EnviorCunBean.DataBean>) {
    }

    var ylPointEntity: RjhjPointBean? = null
    override fun returnRjhjjcPoint(ylPointEntity: RjhjPointBean) {
        this.ylPointEntity = ylPointEntity
        /*if (ylPointEntity.data!=null){
                if (ylPointEntity.data.situation.equals("")){*/
        if (isShow==1&&ylPointEntity.pointRecordEntity!=null){
            if (ylPointEntity.pointRecordEntity.name == 6||ylPointEntity.pointRecordEntity.name == 7){
                typeLx = 3
            }else{
                typeLx = 1
            }

            initPopuEnvironUp(PjEnviorSupvsEntity(), 1,ylPointEntity)
            CommenPop.backgroundAlpha(0.5f, activity)
            envirorUpPopu!!.showAtLocation(supl_frag_rjhj, Gravity.CENTER, 0, 0)
            mPresenter.getEnviorSupvsQueryPoint(pointStr)
            isShow = 0
        }else

            if (isShow==2){
                typeLx = 3
                ylPointEntity.pointRecordEntity = null
                initPopuEnvironUp(PjEnviorSupvsEntity(), 1,ylPointEntity)
                CommenPop.backgroundAlpha(0.5f, activity)
                envirorUpPopu!!.showAtLocation(supl_frag_rjhj, Gravity.CENTER, 0, 0)
                mPresenter.getEnviorSupvsQueryPoint(pointStr)
                isShow = 0
            }else
                if (isShow==3){
                    initPopuDingDian(PointRecordEntity())
                    CommenPop.backgroundAlpha(0.5f, activity)
                    dingDianUpPopu!!.showAtLocation(supl_frag_rjhj, Gravity.CENTER, 0, 0)
                    isShow = 0
                }else
                    if (isShow==4){
                        if (ylPointEntity.pointRecordEntity!=null){
                            initPopuDingDian(ylPointEntity.pointRecordEntity)
                            CommenPop.backgroundAlpha(0.5f, activity)
                            dingDianUpPopu!!.showAtLocation(supl_frag_rjhj, Gravity.CENTER, 0, 0)
                        }else{
                            ToastUtils.showShort("??????????????????????????????")
                        }

                        isShow = 0
                    } else{
                        ToastUtils.showShort("?????????")
                    }
    }

    var image_ddgcmp_environmental: ImageView? = null
    var image_ddgcwg_environmental: ImageView? = null
    var image_ddgcmp_del_environmental: TextView? = null
    var image_ddgcwg_del_environmental: TextView? = null
    fun initPopuDingDian(pointRecordEntity:PointRecordEntity){
        renovatedFileList.clear()
        gcImageURL1 = ""
        gcImageURL2 = ""
        dingDianUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_ding_dian, supl_frag_rjhj)
        val contentView2: View = dingDianUpPopu!!.getContentView()
        val sp_ddmc_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddmc_environmental)// ??????????????????
        val et_ddmc_environmental = contentView2.findViewById<EditText>(R.id.et_ddmc_environmental)// ??????????????????
        val tv_gddw_title = contentView2.findViewById<TextView>(R.id.tv_gddw_title)//??????
        val et_gddwbh_environmental = contentView2.findViewById<EditText>(R.id.et_gddwbh_environmental)//??????
        val et_gddwwz_environmental = contentView2.findViewById<EditText>(R.id.et_gddwwz_environmental)//??????
        val et_ddremark_environmental = contentView2.findViewById<EditText>(R.id.et_ddremark_environmental)//??????
        val et_ddlrr_environmental = contentView2.findViewById<EditText>(R.id.et_ddlrr_environmental)//?????????  id
        val et_ddkfsj_environmental = contentView2.findViewById<EditText>(R.id.et_ddkfsj_environmental)//????????????
        val bt_ddadd_environmental = contentView2.findViewById<Button>(R.id.bt_ddadd_environmental)//??????
        val bt_dddelete_environmental = contentView2.findViewById<Button>(R.id.bt_dddelete_environmental)//??????
        val ll_gc_isshow = contentView2.findViewById<LinearLayout>(R.id.ll_gc_isshow)//
        val et_ddxzq_environmental = contentView2.findViewById<EditText>(R.id.et_ddxzq_environmental)//??????
        val et_ddcun_environmental = contentView2.findViewById<EditText>(R.id.et_ddcun_environmental)//??????
        val et_ddarea_environmental = contentView2.findViewById<EditText>(R.id.et_ddarea_environmental)//??????
        val et_dddws_environmental = contentView2.findViewById<EditText>(R.id.et_dddws_environmental)//?????????
        val sp_ddleibie_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddleibie_environmental)//?????? ??????/??????
        val sp_ddiswsgw_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddiswsgw_environmental)//???????????????????????? ??? ???
        val et_ddjcny_environmental = contentView2.findViewById<EditText>(R.id.et_ddjcny_environmental)//????????????
        val et_ddjscb_environmental = contentView2.findViewById<EditText>(R.id.et_ddjscb_environmental)//????????????
        val sp_ddyxqk_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddyxqk_environmental)//???????????? ?????? ?????????
        val et_ddkfdate_environmental = contentView2.findViewById<EditText>(R.id.et_ddkfdate_environmental)//????????????
        val et_ddkfsc_environmental = contentView2.findViewById<EditText>(R.id.et_ddkfsc_environmental)//????????????
        val et_ddyssj_environmental = contentView2.findViewById<EditText>(R.id.et_ddyssj_environmental)//????????????
        val sp_ddgczt_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddgczt_environmental)//????????????
        val sp_ddgcsx_environmental = contentView2.findViewById<Spinner>(R.id.sp_ddgcsx_environmental)//????????????
        val ll_ding_tp = contentView2.findViewById<LinearLayout>(R.id.ll_ding_tp)//
        image_ddgcmp_environmental = contentView2.findViewById<ImageView>(R.id.image_ddgcmp_environmental)//????????????
        image_ddgcwg_environmental = contentView2.findViewById<ImageView>(R.id.image_ddgcwg_environmental)//????????????
        image_ddgcmp_del_environmental = contentView2.findViewById<TextView>(R.id.image_ddgcmp_del_environmental)//??????????????????
        image_ddgcwg_del_environmental = contentView2.findViewById<TextView>(R.id.image_ddgcwg_del_environmental)//??????????????????




        image_ddgcmp_del_environmental!!.setOnClickListener {
            if (renovatedFileList.size>0){
                for (i in 0..renovatedFileList.size-1){
                    if (renovatedFileList.get(i).filetype.toInt() == 1){
//                        renovatedFileList.removeAt(i)
                        delGcImage(1,i,renovatedFileList)
                    }
                }
            }
        }
        image_ddgcwg_del_environmental!!.setOnClickListener {
            if (renovatedFileList.size>0){
                for (i in 0..renovatedFileList.size-1){
                    if (renovatedFileList.get(i).filetype.toInt() == 2){
//                        renovatedFileList.removeAt(i)
                        delGcImage(2,i,renovatedFileList)
                    }
                }
            }
        }

        image_ddgcmp_environmental!!.setOnClickListener {
            if (gcImageURL1.equals("")){
                gcImageType = 1
                showDialogCustom()
            }else{
                var pathList = ArrayList<String>()
                pathList.add(gcImageURL1)
                PhotoPreview.builder()
                        .setPhotos(pathList)

                        .setShowDeleteButton(false)//.setCurrentItem(helper.adapterPosition)
                        .start(context as Activity)

            }
        }
        image_ddgcwg_environmental!!.setOnClickListener {
            if (gcImageURL2.equals("")){
                gcImageType = 2
                showDialogCustom()
            }else{
                var pathList = ArrayList<String>()
                pathList.add(gcImageURL2)
                PhotoPreview.builder()
                        .setPhotos(pathList)

                        .setShowDeleteButton(false)//.setCurrentItem(helper.adapterPosition)
                        .start(context as Activity)
            }
        }
        var ddList = ArrayList<String>()
        /*ddList.add("???????????????????????????")
        ddList.add("??????????????????")
        ddList.add("????????????")
        ddList.add("????????????????????????")
        ddList.add("????????????")*/
        ddList.add("???????????????")
//        if (pointRecordEntity.id!=-1)
        ddList.add("????????????")
        ddList.add("????????????")
        ddList.add("????????????")
//        if (pointRecordEntity.id!=-1)
        ddList.add("????????????????????????")
        ddList.add("???????????????")
        ddList.add("????????????")
        val ddAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,ddList)
        ddAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddmc_environmental.adapter = ddAdapter

        //??????
        var leibieList = ArrayList<String>()
        leibieList.add("??????")
        leibieList.add("??????")

        val leibieAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,leibieList)
        leibieAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddleibie_environmental.adapter = leibieAdapter

        //????????????????????????
        var iswsgwList = ArrayList<String>()
        iswsgwList.add("???")
        iswsgwList.add("???")
        val iswsgwAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,iswsgwList)
        iswsgwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddiswsgw_environmental.adapter = iswsgwAdapter

        //????????????
        var yxqkList = ArrayList<String>()
        yxqkList.add("??????")
        yxqkList.add("?????????")
        val yxqkAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,yxqkList)
        yxqkAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddyxqk_environmental.adapter = yxqkAdapter

        //????????????
        var gcztList = ArrayList<String>()
        gcztList.add("??????")
        gcztList.add("?????????")
        gcztList.add("??????")
        val gcztAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,gcztList)
        gcztAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddgczt_environmental.adapter = gcztAdapter

        //????????????
        var gcsxList = ArrayList<String>()
        gcsxList.add("??????")
        gcsxList.add("??????")
        val gcsxAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,gcsxList)
        gcsxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_ddgcsx_environmental.adapter = gcsxAdapter

        if (pointRecordEntity.id!=-1){
            et_ddmc_environmental.visibility=View.VISIBLE
            sp_ddmc_environmental.visibility=View.GONE
            et_ddmc_environmental.setText(HjzzflEnum.getName(pointRecordEntity.name.toInt()))
            if (AppCache.getInstance().type!=5){//?????????????????????????????????????????????   ?????????????????????
                bt_dddelete_environmental.visibility =View.VISIBLE
            }

            tv_gddw_title.setText("????????????")
            et_gddwbh_environmental.setText(pointRecordEntity.bh)
            et_gddwwz_environmental.setText(pointRecordEntity.wz)
            et_ddremark_environmental.setText(pointRecordEntity.remark)
            et_ddkfsj_environmental.setText(pointRecordEntity.kfsj)

            sp_ddmc_environmental.setSelection(ddList.indexOf(HjzzflEnum.getName(pointRecordEntity.name.toInt())), true)
            et_ddxzq_environmental.setText(pointRecordEntity.zhen)
            et_ddcun_environmental.setText(pointRecordEntity.xzqmc)
            if (HjzzflEnum.getName(pointRecordEntity.name.toInt()).equals("????????????")||
                    HjzzflEnum.getName(pointRecordEntity.name.toInt()).equals("????????????????????????")){
//                bt_dddelete_environmental.visibility = View.GONE
            }
            if (sp_ddmc_environmental.selectedItem.toString().equals("????????????")){
                ll_ding_tp.visibility = View.VISIBLE
                et_ddarea_environmental.setText(pointRecordEntity.area.toString())
                et_dddws_environmental.setText(pointRecordEntity.dws.toString())
                sp_ddleibie_environmental.setSelection(leibieList.indexOf(pointRecordEntity.category) ,true)
                sp_ddiswsgw_environmental.setSelection(iswsgwList.indexOf(pointRecordEntity.jrwsgw) , true)
                et_ddjcny_environmental.setText(pointRecordEntity.jcny)
                et_ddjscb_environmental.setText(pointRecordEntity.jscb.toString())
                et_ddkfdate_environmental.setText(pointRecordEntity.kfrq.toString())
                et_ddkfsc_environmental.setText(pointRecordEntity.kfsc.toString())
                et_ddyssj_environmental.setText(pointRecordEntity.yssj.toString())
                sp_ddyxqk_environmental.setSelection(yxqkList.indexOf(pointRecordEntity.yxqk) , true)
                sp_ddgczt_environmental.setSelection(gcztList.indexOf(pointRecordEntity.gczt) , true)
                sp_ddgcsx_environmental.setSelection(gcsxList.indexOf(pointRecordEntity.gcsx) , true)
                renovatedFileList = pointRecordEntity.files as ArrayList<RenovatedFile>
                if (renovatedFileList.size>0){

                    for (i in 0..renovatedFileList.size-1){
                        if (renovatedFileList.get(i).filetype.toInt() == 1){
                            gcImageURL1 = renovatedFileList.get(i).url
                            Glide.with(activity!!).load(renovatedFileList.get(i).url).into(image_ddgcmp_environmental!!)
                            image_ddgcmp_del_environmental!!.visibility = View.VISIBLE
                        }else{
                            gcImageURL2 = renovatedFileList.get(i).url
                            Glide.with(activity!!).load(renovatedFileList.get(i).url).into(image_ddgcwg_environmental!!)
                            image_ddgcwg_del_environmental!!.visibility = View.VISIBLE
                        }
                    }
                }
            }else{
                et_ddmc_environmental.visibility=View.GONE
                sp_ddmc_environmental.visibility=View.VISIBLE
                ll_ding_tp.visibility = View.GONE
            }

            bt_dddelete_environmental.setOnClickListener {
                LoadingDialog.cancelDialogForLoading()
                val content = TextView(activity)
                content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("??????????????????")
                        .setCustomView(content)
                        .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                            mPresenter.getDdDetel(pointRecordEntity.id.toString())
                            sweetAlertDialog.dismiss()}
                        .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                        .show()
            }
//            et_ddlrr_environmental.setText(pointRecordEntity.lrr)
        }else{
            tv_gddw_title.setText("????????????")
            et_ddlrr_environmental.setText(AppCache.getInstance().userId.toString())
            pointRecordEntity.location = pointStr
            pointRecordEntity.lrr = et_ddlrr_environmental.text.toString().toInt()
        }
        if (AppCache.getInstance().type == 5){//?????????????????????
            image_ddgcmp_del_environmental!!.visibility = View.GONE
            image_ddgcwg_del_environmental!!.visibility = View.GONE
        }

        if (sp_ddmc_environmental.selectedItem.toString().equals("????????????")){
            ll_gc_isshow.visibility = View.VISIBLE
            ll_ding_tp.visibility = View.VISIBLE
            if (AppCache.getInstance().type ==1||AppCache.getInstance().type ==5){//?????????????????????
                et_ddarea_environmental.visibility = View.VISIBLE
                et_dddws_environmental.visibility = View.VISIBLE
                sp_ddleibie_environmental.visibility = View.VISIBLE
                sp_ddiswsgw_environmental.visibility = View.VISIBLE
                et_ddjcny_environmental.visibility = View.VISIBLE
                et_ddjscb_environmental.visibility = View.VISIBLE
                sp_ddyxqk_environmental.visibility = View.VISIBLE
                et_ddkfdate_environmental.visibility = View.VISIBLE
                et_ddkfsc_environmental.visibility = View.VISIBLE
                et_ddyssj_environmental.visibility = View.VISIBLE
            }
        }else{
            ll_gc_isshow.visibility = View.GONE
            ll_ding_tp.visibility = View.GONE
        }
        //??????spinner????????????????????????
        sp_ddmc_environmental.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

//                ToastUtils.showShort(sp_ddmc_environmental.selectedItem.toString())

                if (sp_ddmc_environmental.selectedItem.toString().equals("????????????")){
                    ll_gc_isshow.visibility = View.VISIBLE
                    ll_ding_tp.visibility = View.VISIBLE
                }else{
                    ll_gc_isshow.visibility = View.GONE
                    ll_ding_tp.visibility = View.GONE
                }
            }
        }

        et_ddjcny_environmental.setOnClickListener {
            setTime(et_ddjcny_environmental)
        }
        et_ddkfdate_environmental.setOnClickListener {
            setTime(et_ddkfdate_environmental)
        }
        et_ddyssj_environmental.setOnClickListener {
            setTime(et_ddyssj_environmental)
        }
        bt_ddadd_environmental.setOnClickListener {
            if (AppCache.getInstance().type==5){//?????????????????????

                dingDianUpPopu!!.dismiss()
            }else{
                //            val pointRecordEntity = PointRecordEntity()
                if (et_ddarea_environmental.text.toString().trim().startsWith(".")||et_ddjscb_environmental.text.toString().trim().startsWith(".")){
                    ToastUtils.showShort("????????????????????????/????????????")
                    return@setOnClickListener
                }
                pointRecordEntity.name = HjzzflEnum.getIndex(sp_ddmc_environmental.selectedItem.toString())
                pointRecordEntity.bh = et_gddwbh_environmental.text.toString()
                pointRecordEntity.wz = et_gddwwz_environmental.text.toString()
                pointRecordEntity.remark = et_ddremark_environmental.text.toString()
                pointRecordEntity.kfsj = et_ddkfsj_environmental.text.toString()
                pointRecordEntity.zhen = et_ddxzq_environmental.text.toString()
                if (pointRecordEntity.name==3&&(AppCache.getInstance().type==ApiConstants.RJHJ_NY||AppCache.getInstance().type==ApiConstants.LDRK_ZZF)){
                    if (et_ddarea_environmental.text.toString().trim().equals("")){
                        pointRecordEntity.area = BigDecimal.ZERO
                    }else{
                        pointRecordEntity.area = BigDecimal(et_ddarea_environmental.text.trim().toString())
                    }
                    if (et_dddws_environmental.text.toString().trim().equals("")){
                        pointRecordEntity.dws = 0
                    }else{
                        pointRecordEntity.dws = et_dddws_environmental.text.toString().toInt()
                    }
                    pointRecordEntity.category = sp_ddleibie_environmental.selectedItem.toString()
                    pointRecordEntity.jrwsgw = sp_ddiswsgw_environmental.selectedItem.toString()
                    pointRecordEntity.jcny = et_ddjcny_environmental.text.toString()
                    if (et_ddjscb_environmental.text.toString().trim().equals("")){
                        pointRecordEntity.jscb = BigDecimal.ZERO
                    }else{
                        pointRecordEntity.jscb = BigDecimal(et_ddjscb_environmental.text.toString())
                    }
                    pointRecordEntity.yxqk = sp_ddyxqk_environmental.selectedItem.toString()
                    pointRecordEntity.kfrq = et_ddkfdate_environmental.text.toString()
                    pointRecordEntity.kfsc = et_ddkfsc_environmental.text.toString()
                    pointRecordEntity.yssj = et_ddyssj_environmental.text.toString()
                }

                if (pointRecordEntity.name==3){
                    pointRecordEntity.gczt= sp_ddgczt_environmental.selectedItem.toString()
                    pointRecordEntity.gcsx= sp_ddgcsx_environmental.selectedItem.toString()
                }

                pointRecordEntity.files= renovatedFileList //????????????

                if (pointRecordEntity.id!=-1){
                    mPresenter.getDdUpdate(pointRecordEntity)
                }else{
                    mPresenter.getDdLr(pointRecordEntity)

                }
            }


        }
    }


    private fun showDialogCustom() {
        //???????????????
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("????????????")
        builder.setItems(mCustomItems, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                //??????
                //??????????????????????????????????????????
                val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            } else if (which == 1) {
                // ????????????????????????????????????
                val state = Environment.getExternalStorageState()
                if (state == Environment.MEDIA_MOUNTED) {
                    cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg"
                    val intent = Intent()
                    // ???????????????????????????Action
                    intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                    val out_file_path = SAVED_IMAGE_DIR_PATH
                    val dir = File(out_file_path)
                    if (!dir.exists()) {
                        dir.mkdirs()
                    } // ????????????????????????Uri??????
                    val uri = Uri.fromFile(File(cameraPath))
                    // ??????????????????????????????????????????????????????????????????
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    startActivityForResult(intent, TAKE_PHOTO_REQUEST)
                } else {
                    ToastUtils.showShort("?????????????????????SD???")
                }
            }
        })
        builder.setCancelable(true)//setCanceledOnTouchOutside
        val alertDialog = builder.create()
        alertDialog.show()
        //???????????????????????????????????????????????????
        alertDialog.setCanceledOnTouchOutside(true)
    }

    //??????????????????
    private fun delGcImage(type : Int,id:Int,renovatedFileList : ArrayList<RenovatedFile>){
        var position = id
        // ??????AlertDialog.Builder????????????????????????????????????AlertDialog?????????
        var builder = AlertDialog.Builder(activity)
        // ??????Title?????????
        builder.setIcon(R.mipmap.ic_launcher)
        // ??????Title?????????
        builder.setTitle("???????????????")
        // ??????Content?????????????????????
        builder.setMessage("??????????????????")
        // ????????????PositiveButton
        builder.setPositiveButton("??????", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

                OkGo.post<String> (ApiConstants.ENVIORFILE_DELETE_FILE).upJson("[${renovatedFileList.get(id).id}]").execute(object :
                        BaseNet<String>(){
                    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                        super.onStart(request)
                        LoadingDialog.showDialogForLoading(activity)
                    }

                    override fun onSuccess(response: Response<String>?) {
                        val cash = response?.body()
                        if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                            val json: BaseRespose <*> = Gson().fromJson(cash, object : TypeToken<BaseRespose <*>?>() {}.type)
                            if (json.code==0){
                                renovatedFileList.removeAt(position)
                                ToastUtils.showShort("????????????")
                                if (type == 1){
                                    gcImageURL1 = ""
                                    image_ddgcmp_del_environmental!!.visibility = View.GONE
                                    Glide.with(activity!!).load(R.drawable.add_image).into(image_ddgcmp_environmental!!)
                                }else{
                                    gcImageURL2 = ""
                                    image_ddgcwg_del_environmental!!.visibility = View.GONE
                                    Glide.with(activity!!).load(R.drawable.add_image).into(image_ddgcwg_environmental!!)
                                }
//
                            }else{
                                ToastUtils.showShort(json.getMsg())
                            }

                        } else {
                            ToastUtils.showShort("????????????")
                        }
                    }

                    override fun onFinish() {
                        super.onFinish()
                        LoadingDialog.cancelDialogForLoading()
                    }

                    override fun onError(response: Response<String>?) {
                        super.onError(response)
                        ToastUtils.showShort("????????????")
                    }

                })
            }
        })
        // ????????????NegativeButton
        builder.setNegativeButton("??????", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.dismiss()
            }
        })
        builder.show()
    }
    override fun returnQueryCun(message: List<CjEntity>, pjContract: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText) {
        if (rlv_xzxzq!=null){
            if (message.size==1){
                rlv_xzxzq.visibility = View.GONE
                pjContract.wz = message.get(0).xzqmc
                pjContract.xzqmc = message.get(0).xzqmc
                pjContract.code = message.get(0).code
                cunMing = message.get(0).xzqmc
                tvWzenvironmental.setText("")
                tvWzenvironmental.hint = message.get(0).zhen+" "+message.get(0).xzqmc
            }else if (message.size>0){
                val data = ylPointEntity!!.getData()
                for ( i in message){
                    if (i.code.equals(data.code)){
                        pjContract.wz = i.xzqmc
                        pjContract.xzqmc = i.xzqmc
                        pjContract.code = i.code
                        cunMing = i.xzqmc
                        tvWzenvironmental.setText("")
                        tvWzenvironmental.hint = i.zhen+" "+i.xzqmc
                    }
                }
                rlv_xzxzq.visibility = View.VISIBLE
                rlv_xzxzq.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
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
                            cunMing = item.xzqmc
                        }
                    }
                }

            } else{
                rlv_xzxzq.visibility = View.GONE
                ToastUtils.showShort("????????????")
            }
        }
    }

    override fun onDdLr(enviorSupvsEntity: String) {
        ToastUtils.showShort(enviorSupvsEntity)
//        aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
        gddwPage = 1
        gddwList.clear()
        mPresenter.getDdList(AppCache.getInstance().userId.toString(),gddwPage)//????????????????????????
        if (dingDianUpPopu!=null){
            dingDianUpPopu!!.dismiss()
        }
        aMap!!.clear()
        addOverLayer(mLayers)
    }

    override fun onDdList(message: List<PointRecordEntity>) {
        gddwList.addAll(message)
        rlv_rjhj_fj_list.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        val gddwValue = object : BaseQuickAdapter<PointRecordEntity, BaseViewHolder>(R.layout.item_rjhj_gddw_list, gddwList) {
            override fun convert(helper: BaseViewHolder?, item: PointRecordEntity?) {
                helper!!.setText(R.id.item_rjhj_gddw_xh, (helper.adapterPosition + 1).toString())
                        .setText(R.id.item_rjhj_gddw_xzq, item!!.xzqmc)//item_rjhj_gddw_update
                        .setText(R.id.item_rjhj_gddw_wtlx, HjzzflEnum.getName(item.name.toInt()))
                        .setText(R.id.item_rjhj_gddw_bh, item!!.bh)
                        .setText(R.id.item_rjhj_gddw_wz, item!!.wz)
                        .setText(R.id.item_rjhj_gddw_lrr, item!!.lrrtext)
                        .setText(R.id.item_rjhj_gddw_kfsj, item!!.kfsj)
                        .setText(R.id.item_rjhj_gddw_remark, item!!.remark)
//                val i = (helper!!.adapterPosition + 1) * 20
                helper.getView<TextView>(R.id.item_rjhj_gddw_update).setOnClickListener {
                    initPopuDingDian(item)
                    CommenPop.backgroundAlpha(0.5f, activity)
                    dingDianUpPopu!!.showAtLocation(supl_frag_rjhj, Gravity.CENTER, 0, 0)
                }

                helper.getView<TextView>(R.id.item_rjhj_gddw_cz).setOnClickListener {
                    LoadingDialog.cancelDialogForLoading()
                    val content = TextView(activity)
                    content.text = "???????????????"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("??????????????????")
                            .setCustomView(content)
                            .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                mPresenter.getDdDetel(item.id.toString())
                                sweetAlertDialog.dismiss()
                            }
                            .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                            .show()


                }
                helper.itemView.setOnClickListener {

                    if (!item!!.location.equals("")) {
                        isShow = 4
                        mPresenter.getRjhjjcPoint(item.location)
                        val center1 = getCenter(item.location)
                        val addMarker1 = aMap?.addMarker(MarkerOptions().position(center1))
                        aMap!!.animateCamera(CameraUpdateFactory.changeLatLng(center1))
                        /*try {
                            aMap!!.clear()
                            addOverLayer(layers)

                            val addMarker1 = aMap?.addMarker(MarkerOptions().position(center1))
                            aMap!!.animateCamera(CameraUpdateFactory.changeLatLng(center1))
                        } finally {
                            //??????????????????????????????,??????animateCamera??????????????????
                            Handler().postDelayed(Runnable {
                                kotlin.run {
                                    aMap!!.animateCamera(CameraUpdateFactory.changeLatLng(center1))
                                }
                                *//* public void run() {
                                     //??????????????????
                                 }*//*
                            }, 600);
                            Handler().postDelayed(Runnable {
                                kotlin.run {
                                    aMap!!.animateCamera(CameraUpdateFactory.changeLatLng(center1))
                                }
                                *//* public void run() {
                                     //??????????????????
                                 }*//*
                            }, 900);

                        }*/

                    }

                }

            }
        }
        rlv_rjhj_fj_list.adapter = gddwValue
        rlv_rjhj_fj_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager

                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&gddwList.size==gddwPage*50) {
                    gddwPage++
                    mPresenter.getDdList(AppCache.getInstance().userId.toString(),gddwPage)
//                    ToastUtils.showShort("??????????????????")
                }else{
//                    ToastUtils.showShort("??????????????????")
                }
            }
        })
        rlv_rjhj_fj_list.scrollToPosition((gddwPage-1)*20)
        var stringList = ArrayList<String>()
        for(i in 0..gddwList.size-1){
            stringList.add("")
        }
        //enviorListEntity
        //RightArrowAdapter
        rlv_frag_rjhj_jt1.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rlv_frag_rjhj_jt1.adapter = RightArrowAdapter(activity,stringList)
    }

    override fun returnHbjcListCode(ylPointEntity: List<PjEnviorSupvsEntity>, roleid: Int, totalCount: Int) {
        if (ylPointEntity.size==1){//??????????????????
            val intent = Intent(activity, HBJCDetailActivity::class.java)
            intent.putExtra("pjenvior", ylPointEntity!!.get(0))
            startActivity(intent)
        }else if (ylPointEntity.size>1){
            val arrayList = ArrayList<String>()
//            val arrayListOf = arrayOf()<
            for (i in ylPointEntity){
                arrayList.add(i.hjzzsjText)
            }
            val alertBuilder = AlertDialog.Builder(activity)

            alertBuilder.setTitle("????????????????????????")
            alertBuilder.setSingleChoiceItems(arrayList.toArray(arrayOfNulls(arrayList.size)), 0) { dialogInterface, i ->
                val intent = Intent(activity, HBJCDetailActivity::class.java)//??????????????????
                intent.putExtra("pjenvior", ylPointEntity!!.get(i))
                startActivity(intent)
                dialogInterface!!.dismiss()
            }


            alertBuilder.setNegativeButton("??????") { dialogInterface, i ->
                dialogInterface!!.dismiss()
            }
            alertBuilder.show()

        }
    }

    override fun returnEnviorFileQueryList(message: List<EnviorFileFhEntity>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage: Int) {
        clearAllMarker()
        if (isImage == 1){
            for (i in message){
                val zoom = aMap!!.cameraPosition.zoom
                if (zoom<9&&type.equals("1")){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom<13&&type.equals("2")&&zoom>=9){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }else if (zoom>=15.5&&type.equals("4")&&zoom>=15.5){
                    setImageMarker(i,type,xmin,xmax,ymin,ymax)
                }
            }

        }else if (isImage==2){
            for (i in message){
                val zoom = aMap!!.cameraPosition.zoom
                var location : LatLng? =null
                if (code.length==9){
                    if (zoom<13&&type.equals("2")){
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<15.5&&type.equals("3")){//15
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<20&&type.equals("4")){//15
                        if (!i.location.equals("")){
                            location = getCenter(i.location)
                            if (i.monwarnin==0){
                                if (i.zdwt==1){
                                    setMarker(i.location, location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_zs)
                                }else{
                                    setMarker(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_ls)
                                }
                            }else{
                                setMarker(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_hs)
                            }
                        }/*else{
                            location =
                        }*/
                    }
                }
                if (code.equals("")||code.equals("110112")){
                    if (zoom<9&&type.equals("1")){
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<13&&type.equals("2")){
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (zoom<15.5&&type.equals("3")){//15
                        setWtMarker(i,type,xmin,xmax,ymin,ymax)
                    }else if (type.equals("4")){
                        if (!i.location.equals("")){
                            location = getCenter(i.location)
                            if (i.monwarnin==0){
                                if (i.zdwt==1){
                                    setMarker(i.location, location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_zs)
                                }else{
                                    setMarker(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_ls)
                                }
                            }else{
                                setMarker(i.location,location!!.latitude,location.longitude,R.drawable.ic_hbjc_dcl_hs)
                            }
                        }

                    }
                }
            }
        }
    }

    fun setImageMarker(enviorSupvsEntity : EnviorFileFhEntity,type:String,
                       xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal) { //,int mid  ??????????????????
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_image_marker, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_image_marker)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_image_marker_position)
        val path = enviorSupvsEntity.path
        val replace = path.replace("\\", "/")
        val imagePath = ApiConstants.RELEASE_URL + replace
        itemMarkerPosition.text = enviorSupvsEntity.counts.toString()
        val location = enviorSupvsEntity.location
        if (!location.equals("")){
            val center1 = getCenter(location)
            //??????marker
            Glide.with(this).load(imagePath)//
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            //????????????
                            ivItemMarker.setImageDrawable(resource);
//                        textView.setText(list.get(I).getGasName() + "");
                            var bitmap = convertViewToBitmap(view1);
                            val zoom = aMap!!.cameraPosition.zoom
                            if (zoom < 9 && type.equals("1")) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom < 12 && type.equals("2") && zoom >= 9) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom < 15 && type.equals("3") && zoom >= 12) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            } else if (zoom >= 15 && type.equals("4") && zoom >= 15) {
                                aMap!!.addMarker(MarkerOptions()
                                        .position(center1)
                                        .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                        .draggable(true))
                            }


                        }

                        /*@Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //????????????
                            imageView.setImageDrawable(resource);
                            textView.setText(list.get(I).getGasName() + "");
                            Bitmap bitmap = convertViewToBitmap(view);
                            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .position..;
                            aMap.addMarker(markerOption);
                        }*/
                    });
        }
        aMap!!.setOnMarkerClickListener { marker ->
            val snippet = marker.snippet//???????????????????????????
            /*val intent = Intent(activity, ImageListActivity::class.java)
            intent.putExtra("snippet",snippet)
            intent.putExtra("sjfl",sjfl)
            startActivity(intent)*/
            true
        }
    }


    fun setWtMarker(enviorSupvsEntity : EnviorFileFhEntity,type:String,
                    xmin: BigDecimal,xmax: BigDecimal,ymin: BigDecimal,ymax: BigDecimal) { //,int mid
        if (aMap!=null){

        }
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.view_marker, null)

        val textView = view1!!.findViewById<View>(R.id.bv_view_marker) as BubbleView
        val counts = enviorSupvsEntity.counts
        if (counts<50){
            textView.setColor(Color.argb(255, 255, 225, 219))
        }else if (counts<150){
            textView.setColor(Color.argb(255, 255, 196, 179))
        }else if (counts<300){
            textView.setColor(Color.argb(255, 255, 153, 133))
        } else if (counts<600){
            textView.setColor(Color.argb(255, 245, 117, 103))
        }else if (counts<1000){
            textView.setColor(Color.argb(255, 230, 69, 70))
        }else if (counts<10000){
            textView.setColor(Color.argb(255, 184, 9, 9))
        }else if (counts>10000){
            textView.setColor(Color.argb(255, 138, 8, 8))
        }
//        textView.setTextColor(Color.BLACK)
        textView.setText(enviorSupvsEntity.name+":"+counts)
        var bitmap = convertViewToBitmap(view1)

        val zoom = aMap!!.cameraPosition.zoom
        val location = enviorSupvsEntity.location
        if (!location.equals("")){
            val center1 = getCenter(location)
            if (code.length==9){
                if (zoom<13&&type.equals("2")){
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){//15
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }
            }
            if (code.equals("")||code.equals("110112")){
                if (zoom<9&&type.equals("1")){
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }else if (zoom<13&&type.equals("2")&&zoom>=9){
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }else if (zoom<15.5&&type.equals("3")&&zoom>=13){//15
                    aMap!!.addMarker(MarkerOptions()
                            .position(center1)
                            .snippet(type + "-" + xmin + "-" + xmax + "-" + ymin + "-" + ymax + "-" + enviorSupvsEntity.name + "-" + enviorSupvsEntity.location) //                .period(mid)//??????markerID
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .draggable(true))
                }
            }

            aMap!!.setOnMarkerClickListener { marker ->
                val position = marker.position
                aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(position, zoom+5f, 0f, 0f)))

                true
            }
        }


    }

    var rlv_envionmental_wtlist: RecyclerView? = null
    var ll_envionmental_wtlist: LinearLayout? = null
    var pop_gctype = 0 //????????????????????????????????????

    private fun initPopuEnvironUp(pjContract: PjEnviorSupvsEntity?, state: Int,xzqmc:RjhjPointBean) {
        pjContract!!.zjwj = 0//??????????????????????????????
        selectedPhotos.clear()
        selectedVideos.clear()
        PjEnviorFileList.clear()
        videoIdList.clear()
        fileIdList.clear()
        envirorUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_environmental_up2, supl_frag_rjhj)
        val contentView2: View = envirorUpPopu!!.getContentView()
//        val contentView2 = LayoutInflater.from(this.getParent()).inflate(R.layout.product_package, null);
        tvUpDateEnviron = contentView2.findViewById(R.id.tv_up_date_environmental2)
        //        EditText tvBhEnvironmental = contentView2.findViewById(R.id.tv_bh_environmental);
        val tv_environ_up_bt = contentView2.findViewById<TextView>(R.id.tv_environ_up_bt2)
        val tvNameEnvironmental = contentView2.findViewById<EditText>(R.id.tv_name_environmental2)
        //        EditText tvNameSection = contentView2.findViewById(R.id.tv_name_section);
        val tvJlrPhone = contentView2.findViewById<EditText>(R.id.tv_jlr_phone2)
        val tvLrwtEnvironmental = contentView2.findViewById<EditText>(R.id.tv_lrwt_environmental2)//????????????
        val tvZrdwEnvironmental = contentView2.findViewById<EditText>(R.id.tv_zrdw_environmental2)
        val tvWtztEnvironmental = contentView2.findViewById<Spinner>(R.id.tv_wtzt_environmental2)
        val tvJlrEnvironmental = contentView2.findViewById<EditText>(R.id.tv_jlr_environmental2)
        val tvRemarkEnvironmental = contentView2.findViewById<EditText>(R.id.tv_remark_environmental2)
        val butClearMeetingSummary = contentView2.findViewById<Button>(R.id.bt_clear_environmental2)
        tvUploadMeetingSummary = contentView2.findViewById<Button>(R.id.bt_upload_environmental2)
        val llWtztEnvironmental = contentView2.findViewById<LinearLayout>(R.id.ll_wtzt_environmental2)
        val tvWzenvironmental = contentView2.findViewById<EditText>(R.id.tv_wz_environmental2)
        val tv_xfzgyq_environmental = contentView2.findViewById<EditText>(R.id.tv_xfzgyq_environmental)//????????????
        val tv_clshijian_environmental = contentView2.findViewById<EditText>(R.id.tv_clshijian_environmental)//????????????
        val tv_xfclsj_environmental = contentView2.findViewById<EditText>(R.id.tv_xfclsj_environmental)//????????????
        val sp_clqx_environmental = contentView2.findViewById<Spinner>(R.id.sp_clqx_environmental)
        val tv_xfjy_environmental = contentView2.findViewById<EditText>(R.id.tv_xfjy_environmental)
        val rlvPopVideoAdd = contentView2.findViewById<RecyclerView>(R.id.rlv_pop_video_add2)
        val rlvPopEnvironmental: RecyclerView = contentView2.findViewById(R.id.recy_pop_environmental2)
        val recy_pop_environmental2_scxs: RecyclerView = contentView2.findViewById(R.id.recy_pop_environmental2_scxs)//??????????????????
        val rlvPopVideoadd: RecyclerView = contentView2.findViewById(R.id.rlv_pop_video_add2)
        val rlv_xzxzq: RecyclerView = contentView2.findViewById(R.id.rlv_xzxzq2)//???????????????

        val sp_cncw_environmental = contentView2.findViewById<Spinner>(R.id.sp_cncw_environmental2)//????????????
        val sp_sfgddw_environmental = contentView2.findViewById<Spinner>(R.id.sp_sfgddw_environmental2)//??????????????????
        val sp_sfzdwt_environmental = contentView2.findViewById<Spinner>(R.id.sp_sfzdwt_environmental2)//????????????
        val sp_jcssgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_jcssgh_environmental2)//??????????????????
        val sp_tynysyxwh_environmental = contentView2.findViewById<Spinner>(R.id.sp_tynysyxwh_environmental2)//???????????????????????????
        val sp_hcqtgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_hcqtgh_environmental2)//??????????????????
        val sp_lhyh_environmental = contentView2.findViewById<Spinner>(R.id.sp_lhyh_environmental2)//????????????
        val sp_jnlduyxwh_environmental = contentView2.findViewById<Spinner>(R.id.sp_jnlduyxwh_environmental2)//????????????????????????
        val sp_wttj_environmental = contentView2.findViewById<Spinner>(R.id.sp_wttj_environmental2)//????????????
        val sp_shljzl_environmental = contentView2.findViewById<Spinner>(R.id.sp_shljzl_environmental2)//??????????????????
        val sp_shwszl_environmental = contentView2.findViewById<Spinner>(R.id.sp_shwszl_environmental2)//??????????????????
        val sp_gcgh_environmental = contentView2.findViewById<Spinner>(R.id.sp_gcgh_environmental2)//????????????
        val sp_sdlj_environmental = contentView2.findViewById<Spinner>(R.id.sp_sdlj_environmental2)//????????????
        val sp_hjzz_environmental = contentView2.findViewById<Spinner>(R.id.sp_hjzz_environmental2)//????????????(????????????)
//        val sp_ejfl_environmental = contentView2.findViewById<Spinner>(R.id.sp_ejfl_environmental2)//????????????
        val sp_sjfl_environmental = contentView2.findViewById<Spinner>(R.id.sp_sjfl_environmental2)//????????????
        val tv_ejfl_environmental = contentView2.findViewById<TextView>(R.id.tv_ejfl_environmental2)
//        val tv_sjfl_environmental = contentView2.findViewById<TextView>(R.id.tv_sjfl_environmental)
        val ll_sjfl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_sjfl_environmental2)
        val ll_ejfl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_ejfl_environmental2)

        val ll_gddw_envuronmental = contentView2.findViewById<LinearLayout>(R.id.ll_gddw_envuronmental2)
        val ll_fgddw_envuronmental = contentView2.findViewById<LinearLayout>(R.id.ll_fgddw_envuronmental2)
        val ll_tynysyxwh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_tynysyxwh_environmental2)
        val ll_hcqtgh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_hcqtgh_environmental2)
        val ll_lhyh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_lhyh_environmental2)
        val ll_jnldyxwh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_jnldyxwh_environmental2)
        val ll_shljzl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_shljzl_environmental2)
        val ll_shwszl_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_shwszl_environmental2)
        val ll_gcgh_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_gcgh_environmental2)
        val ll_sdlj_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_sdlj_environmental2)

        val ll_xfzgyq_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_xfzgyq_environmental)//????????????
        val ll_clshijian_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_clshijian_environmental)//????????????
        val ll_clsj_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_clsj_environmental)//????????????
        val ll_xfjy_environmental = contentView2.findViewById<LinearLayout>(R.id.ll_xfjy_environmental)//????????????
        bt_temporary_environmental = contentView2.findViewById<Button>(R.id.bt_temporary_environmental2)
        val et_wtlx_gddw = contentView2.findViewById<TextView>(R.id.et_wtlx_gddw)
        ll_envionmental_wtlist = contentView2.findViewById<LinearLayout>(R.id.ll_envionmental_wtlist)//????????????????????????????????????
        rlv_envionmental_wtlist = contentView2.findViewById<RecyclerView>(R.id.rlv_envionmental_wtlist)//????????????????????????????????????
        val rlt_envionmental_wtlist = contentView2.findViewById<LinearLayout>(R.id.rlt_envionmental_wtlist)//????????????????????????????????????
        val rlv_envionmental_wttv = contentView2.findViewById<TextView>(R.id.rlv_envionmental_wttv)//????????????????????????????????????
        val ll_envionmental_gcbh = contentView2.findViewById<LinearLayout>(R.id.ll_envionmental_gcbh)//????????????
        val tv_envionmental_gcbh = contentView2.findViewById<EditText>(R.id.tv_envionmental_gcbh)//????????????
        val ll_envionmental_gcimage = contentView2.findViewById<LinearLayout>(R.id.ll_envionmental_gcimage)//????????????
        val image_envionmental_gcimage_mp = contentView2.findViewById<ImageView>(R.id.image_envionmental_gcimage_mp)//????????????
        val image_envionmental_gcimage_wg = contentView2.findViewById<ImageView>(R.id.image_envionmental_gcimage_wg)//????????????
        val recy_envionmental_gcimage = contentView2.findViewById<RecyclerView>(R.id.recy_envionmental_gcimage)//????????????

        rlv_envionmental_wttv.setOnClickListener {
            if (rlv_envionmental_wttv.text.toString().equals("??????")){
                rlt_envionmental_wtlist.visibility = View.GONE
                rlv_envionmental_wttv.text = "??????"
            }else{
                rlt_envionmental_wtlist.visibility = View.VISIBLE
                rlv_envionmental_wttv.text = "??????"
            }

        }
        if (typeLx==1){
            ll_xfzgyq_environmental.visibility = View.GONE
            ll_clshijian_environmental.visibility = View.GONE
            ll_clsj_environmental.visibility = View.GONE
            ll_xfjy_environmental.visibility = View.GONE
        }else{
            ll_xfzgyq_environmental.visibility = View.VISIBLE
            ll_clshijian_environmental.visibility = View.VISIBLE
            ll_clsj_environmental.visibility = View.VISIBLE
            ll_xfjy_environmental.visibility = View.VISIBLE
        }



//        tv_xfzgyq_environmental.setText(pjContract.zgyq)//????????????
//        tv_xfjy_environmental.setText(pjContract.xfjy)//????????????


        if (xzqmc.pointRecordEntity!=null){
            pjContract.wz = xzqmc.pointRecordEntity.xzqmc
            pjContract.xzqmc = xzqmc.pointRecordEntity.xzqmc
            pjContract.code = xzqmc.pointRecordEntity.code
            cunMing = xzqmc.pointRecordEntity.xzqmc
            tvWzenvironmental.setText(xzqmc.pointRecordEntity.xzqmc)
            tvWzenvironmental.isEnabled = false
            if (xzqmc.pointRecordEntity.name == 3){//????????????
                if(!xzqmc.pointRecordEntity.bh.equals("")){
//                    pop_gctype = 1
//                    ll_envionmental_wtlist!!.visibility = View.VISIBLE
//                    ll_envionmental_gcbh.visibility = View.VISIBLE
                    tv_envionmental_gcbh.setText(xzqmc.pointRecordEntity.bh)
                }else{
//                    pop_gctype = 0
                }
                if (xzqmc.pointRecordEntity.files.size>0){
                    pop_gctype = 1
//                    ll_envionmental_wtlist!!.visibility = View.VISIBLE
                    ll_envionmental_gcimage.visibility = View.VISIBLE
                    /*for (i in 0..xzqmc.pointRecordEntity.files.size-1){
                        if (xzqmc.pointRecordEntity.files.get(i).filetype.toInt() == 1){
                            Glide.with(activity!!).load(xzqmc.pointRecordEntity.files.get(i).url).into(image_envionmental_gcimage_mp!!)
                        }
                        if (xzqmc.pointRecordEntity.files.get(i).filetype.toInt() == 2){
                            Glide.with(activity!!).load(xzqmc.pointRecordEntity.files.get(i).url).into(image_envionmental_gcimage_wg!!)
                        }
                    }*/

                    recy_envionmental_gcimage!!.layoutManager = GridLayoutManager(activity,2)
                    var gddwaAdapter = object : BaseQuickAdapter<RenovatedFile, BaseViewHolder>(R.layout.item_teng_tui_photo, xzqmc.pointRecordEntity.files) {
                        override fun convert(helper: BaseViewHolder, item: RenovatedFile) {
                            val iv_teng_photo = helper.getView<ImageView>(R.id.iv_teng_photo)

                            val pic: String = item.url
                            val s1 = pic.replace("\\", "/")
                            Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE) //                        .override(200,200)
                                    .into(iv_teng_photo)
                            if (item.filetype == 1){
                                helper.setText(R.id.tv_teng_photo_name,"????????????")
                            }else{
                                helper.setText(R.id.tv_teng_photo_name,"????????????")
                            }
                            helper.itemView.setOnClickListener {
                                var pathList = ArrayList<String>()
                                for (i in xzqmc.pointRecordEntity.files){
                                    val pic = i.url
                                    val s1 = pic.replace("\\", "/")
                                    pathList.add(s1)
                                }
                                PhotoPreview.builder()
                                        .setPhotos(pathList)
                                        .setCurrentItem(helper.adapterPosition)
                                        .setShowDeleteButton(false)
                                        .start(this.mContext as Activity)
                            }
                        }
                    }
                    recy_envionmental_gcimage.adapter = gddwaAdapter
                }else{
                    pop_gctype = 0
                }

            }
        }else{
            tvWzenvironmental.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (!tvWzenvironmental.text.toString().equals("")&&xzqmc.pointRecordEntity==null){
                        mPresenter.getQueryCun(tvWzenvironmental.text.toString(), pjContract!!,rlv_xzxzq,tvWzenvironmental)
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
        }

        photoAdapter = PhotoAdapter(context, selectedPhotos)
        rlvPopEnvironmental.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        recy_pop_environmental2_scxs.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvPopEnvironmental.adapter = photoAdapter

        schxtp = object : BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, PjEnviorFileList) {
            override fun convert(helper: BaseViewHolder?, item: PjEnviorFileEntity?) {
                val view = helper!!.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                val pic: String = ApiConstants.BASE_URL + item!!.path
                val s1 = pic.replace("\\", "/")
                Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view)
                helper!!.setText(R.id.tv_teng_photo_name, item.name)
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // ??????AlertDialog.Builder????????????????????????????????????AlertDialog?????????
                    var builder = AlertDialog.Builder(activity)
                    // ??????Title?????????
                    builder.setIcon(R.mipmap.ic_launcher)
                    // ??????Title?????????
                    builder.setTitle("???????????????")
                    // ??????Content?????????????????????
                    builder.setMessage("??????????????????")
                    // ????????????PositiveButton
                    builder.setPositiveButton("??????", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            OkGo.post<String> (ApiConstants.ENVIORFILEDELETE).upJson("[${item.id}]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(activity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
//                    val decrypt = AesEncryptUtils.decrypt(cash)
                                        val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                        if (json.code==0){
                                            PjEnviorFileList.removeAt(position)
                                            notifyDataSetChanged()
//                                            mView.returnDeleteFileEnvironmental(json)
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("??????")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("????????????")
                                }

                            })
                        }
                    })
                    // ????????????NegativeButton
                    builder.setNegativeButton("??????", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog!!.dismiss()
                        }
                    })
                    builder.show()
                }
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in PjEnviorFileList){
                        val pic = ApiConstants.BASE_URL + i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(activity!!)


                }
            }
        }
        recy_pop_environmental2_scxs.adapter = schxtp
        videoAdapter = VideoAdapter(context, selectedVideos)
        rlvPopVideoadd.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvPopVideoadd.adapter = videoAdapter

        rlvPopEnvironmental.addOnItemTouchListener(RecyclerItemClickListener(activity, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(context!!, this@RjhjFragment)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(context!!, this@RjhjFragment,20)//context!!
                }
            }
        }))

        rlvPopVideoadd.addOnItemTouchListener(RecyclerItemClickListener(activity, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (videoAdapter!!.getItemViewType(position) === VideoAdapter.TYPE_ADD) {
//                    video()
                } else {
                    val intent = Intent(Intent.ACTION_VIEW)
                    val path = selectedVideos.get(position)//????????????????????????
                    val file = File(path)
                    val uri = Uri.fromFile(file)
                    intent.setDataAndType(Uri.parse(path), "video/*")
                    startActivity(intent)
                }
            }
        }))

        val hjzzList = ArrayList<BaobiaoBean>()//????????????
        hjzzList.add(BaobiaoBean("??????????????????",1))
        hjzzList.add(BaobiaoBean("???????????????",2))
        hjzzList.add(BaobiaoBean("????????????",3))
        hjzzList.add(BaobiaoBean("????????????",4))
        hjzzList.add(BaobiaoBean("????????????",5))
        hjzzList.add(BaobiaoBean("????????????????????????",6))
        hjzzList.add(BaobiaoBean("???????????????",7))
        hjzzList.add(BaobiaoBean("????????????",8))
        val hjzzAdapter = HbAdapter(activity ,hjzzList)
        sp_hjzz_environmental.adapter = hjzzAdapter
        val valuesSjfl = HjzzsjEnum.values()//????????????
        val sjflList = ArrayList<BaobiaoBean>()//????????????
        var hjzzName = ""
        if (xzqmc.pointRecordEntity!=null){//???????????????????????????
            hjzzName = HjzzflEnum.getName(xzqmc.pointRecordEntity.name)
            pjContract!!.gddwid = xzqmc.pointRecordEntity.id
        }else{
            hjzzName = "??????????????????"
        }
        tv_environ_up_bt.setText(hjzzName)
        if (hjzzName.equals("??????????????????")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "1")
        }else if (hjzzName.equals("???????????????")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "2")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("????????????")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "3")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        } else if (hjzzName.equals("????????????")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "4")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("????????????")){
            ll_ejfl_environmental.visibility = View.VISIBLE
            ll_sjfl_environmental.visibility = View.GONE
            addNewSjfl(valuesSjfl,sjflList, "5")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("????????????????????????")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "6")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else if (hjzzName.equals("???????????????")){
            ll_ejfl_environmental.visibility = View.GONE
            ll_sjfl_environmental.visibility = View.VISIBLE
            addNewSjfl(valuesSjfl,sjflList, "7")
            if (sjflList.size>0)
                et_wtlx_gddw.setText(sjflList.get(0).name)
        }else{
            if (pjContract!!.hjzzej!=-1){
                addSjfl(valuesSjfl, sjflList,pjContract!!.hjzzejText)
            }else{
                addSjfl(valuesSjfl, sjflList,"101")
            }
        }
        for ( i in 0..sjflList.size-1){
            if (i == 0){
                sjflList.get(i).isCheck=true
            }
        }
        et_wtlx_gddw.setOnClickListener {
            //            et_wtlx_gddw.setInputType(InputType.TYPE_NULL); // ???????????????
            initPopWttck(sjflList,et_wtlx_gddw)
        }
        val sjflAdapter = HbAdapter(activity ,sjflList)
        sp_sjfl_environmental.adapter = sjflAdapter


        if (pjContract!!.hjzzyjfl!=-1){
            val baobiaoBean = BaobiaoBean(HjzzflEnum.getName(pjContract!!.hjzzyjfl), pjContract!!.hjzzyjfl)
            var index=0
            for (i in 0..hjzzList.size-1){
                if (Gson().toJson(hjzzList.get(i)).equals(Gson().toJson(baobiaoBean))){
                    index = i
                }
            }
            sp_hjzz_environmental.setSelection(index,true)
        }
        if (xzqmc.pointRecordEntity!=null){//tv_environ_up_bt
            val baobiaoBean = BaobiaoBean(HjzzflEnum.getName(xzqmc.pointRecordEntity.name), xzqmc.pointRecordEntity.name)

            var index=0
            for (i in 0..hjzzList.size-1){
                if (Gson().toJson(hjzzList.get(i)).equals(Gson().toJson(baobiaoBean))){
                    index = i
                }
            }
            sp_hjzz_environmental.setSelection(index,true)
            tvRemarkEnvironmental.setText(xzqmc!!.pointRecordEntity!!.remark)//point
            tv_environ_up_bt.setText(hjzzList.get(index).name)
            if (hjzzList.get(index).name.equals("??????????????????")){
//                ll_ejfl_environmental.visibility = View.GONE
            }

        }else{

            tv_environ_up_bt.setText("??????????????????")
        }


        val cncwList = ArrayList<String>();//????????????
        cncwList.add("??????")
        cncwList.add("??????")
        val cncwAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,cncwList)
        cncwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_cncw_environmental.adapter = cncwAdapter
        val cnwText = pjContract!!.cnwText
        val indexOf = cncwList.indexOf(cnwText)
        if (xzqmc.getCzfw()==1){
            sp_cncw_environmental.setSelection(0,true)
        }else{
            sp_cncw_environmental.setSelection(1,true)
        }

        val sfgddwList = ArrayList<String>();//??????????????????
        sfgddwList.add("???")
        sfgddwList.add("???")
//        sfgddwList.add("??????")
        val sfgddwAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,sfgddwList)
        sfgddwAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_sfgddw_environmental.adapter = sfgddwAdapter
        val gddwText = pjContract!!.gddwText
        val indexOfgddw = sfgddwList.indexOf(gddwText)
        sp_sfzdwt_environmental.setSelection(indexOfgddw,true)
        val sfzdwtAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,sfgddwList)
        sfzdwtAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_sfzdwt_environmental.adapter = sfzdwtAdapter
        val zdwtText = pjContract!!.zdwtText
        val indexOfzdwt = sfgddwList.indexOf(zdwtText)
        if (pjContract.zdwt==-1){
            sp_sfzdwt_environmental.setSelection(1,true)
        }else{
            sp_sfzdwt_environmental.setSelection(indexOfzdwt,true)
        }
        tv_clshijian_environmental.setText(CalendarUtil.DATE_FORMAT_DATE.format(Date()))
        if (sp_sfzdwt_environmental.selectedItemPosition==0){
            tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(7))
        }else{
            tv_xfclsj_environmental.setText(TimeUtil.getDateAfter(5))
        }

        tv_clshijian_environmental.setOnClickListener {//??????????????????
            setTime(tv_clshijian_environmental)
        }
        tv_xfclsj_environmental.setOnClickListener {//??????????????????
            setTime(tv_xfclsj_environmental)
        }

        val clqxList = ArrayList<String>();//????????????
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
        val jclqxAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,clqxList)
        jclqxAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_clqx_environmental.adapter = jclqxAdapter
        if (sp_sfzdwt_environmental.selectedItemPosition==0){
            sp_clqx_environmental.setSelection(4,true)
        }else{
            sp_clqx_environmental.setSelection(2,true)
        }
        sp_sfzdwt_environmental.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

        sp_sfgddw_environmental.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
        if (xzqmc.pointRecordEntity!=null){
            sp_sfgddw_environmental.setSelection(0,true)
        }else{
            sp_sfgddw_environmental.setSelection(1,true)
        }

        val jcssghList = ArrayList<String>();//??????????????????
        jcssghList.add("???????????????")
        jcssghList.add("????????????")
        jcssghList.add("????????????")
        jcssghList.add("????????????")
        val jcssghAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,jcssghList)
        jcssghAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        sp_jcssgh_environmental.adapter = jcssghAdapter
        sp_jcssgh_environmental.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
        if (state == 0) { //            llWtztEnvironmental.visibility=View.VISIBLE
            llWtztEnvironmental.visibility = View.GONE
            rlvPopEnvironmental.visibility = View.GONE
        } else {
            rlvPopEnvironmental.visibility = View.VISIBLE
        }
        if (xzqmc.data!=null){
            tvWzenvironmental.setText(xzqmc.data.xzqmc)
        }else if (xzqmc.pointRecordEntity!=null){
            tvWzenvironmental.setText(xzqmc.pointRecordEntity.xzqmc)
        }
        if (pjContract != null && state == 0) { //            tvBhEnvironmental.setText(pjContract.getProjcode());
            tvLrwtEnvironmental.setText(pjContract.questions)
            tvWtztEnvironmental.setSelection(pjContract.qutype - 1, true)
            tvJlrEnvironmental.setText(pjContract.jlr)
            tvJlrPhone.setText(pjContract.jlrtel)
            tvWzenvironmental.setText(pjContract.wz)
            tvRemarkEnvironmental.setText(pjContract.remark)
            tvZrdwEnvironmental.setText(pjContract.zrdw)
        } else {
        }
        tvJlrEnvironmental.setText(AppCache.getInstance().name)
        tvJlrPhone.setText(AppCache.getInstance().phone)
        if (pjContract.jltime.equals("")){
            uploadDate = CalendarUtil.DATE_FORMAT_DATE.format(Date())
            tvUpDateEnviron!!.setText(uploadDate)
        }else{
            tvUpDateEnviron!!.setText(pjContract.jltime)
        }

        tvUpDateEnviron!!.setOnClickListener(View.OnClickListener { setTime(tvUpDateEnviron!!) })
        //????????????
        bt_temporary_environmental!!.setOnClickListener {
            if (pjContract.wz.equals("")){
                ToastUtils.showShort("??????????????????")
                return@setOnClickListener
            }
            LoadingDialog.showDialogForLoading(activity)
            isls = 0
            bt_temporary_environmental!!.isEnabled = false
            tvUploadMeetingSummary!!.isEnabled = false

            pjContract!!.questions = tvLrwtEnvironmental.text.toString()
            pjContract!!.zrdw = tvZrdwEnvironmental.text.toString()
            if (xzqmc!!.pointRecordEntity!=null){
                pjContract!!.location = xzqmc!!.pointRecordEntity.location
            }else{
                pjContract!!.location = pointStr//"POINT(116.69999599456787 39.97817516326904)"//pointStr
            }

            pjContract!!.jlrtel = tvJlrPhone.text.toString()
            pjContract!!.jlr = tvJlrEnvironmental.text.toString()
            pjContract!!.remark = tvRemarkEnvironmental.text.toString()
            if (sp_cncw_environmental.isShown){
                val cnwString = sp_cncw_environmental.selectedItem.toString()
                pjContract.cnw = CnwEnum.getIndex(cnwString)
            }
            val sfgddwString = sp_sfgddw_environmental.selectedItem.toString()
            pjContract.gddw = IsTrueEnum.getIndex(sfgddwString)
            if (sp_sfzdwt_environmental.isShown){
                val sfzdwtString = sp_sfzdwt_environmental.selectedItem.toString()
                pjContract.zdwt = IsTrueEnum.getIndex(sfzdwtString)
            }
            pjContract.hjzzyjfl = HjzzflEnum.getIndex(tv_environ_up_bt.text.toString())//hjzzList.get(sp_hjzz_environmental.selectedItemPosition).name

            if (ll_sjfl_environmental.isShown){
                /*pjContract.hjzzsj = HjzzsjEnum.getIndex(sjflList.get(sp_sjfl_environmental.selectedItemPosition).name,
                        sjflList.get(sp_sjfl_environmental.selectedItemPosition).index)*/
                pjContract.hjzzfls = arrayOf(HjzzsjEnum.getIndex(sjflList.get(sp_sjfl_environmental.selectedItemPosition).name,
                        sjflList.get(sp_sjfl_environmental.selectedItemPosition).index))
            }else{

                val arrayList = ArrayList<Int>()
                for (i in sjflList){
                    if (i.isCheck){
                        for ( f in 0..i.num-1){
                            arrayList.add(i.index)
                        }

                    }
                }
                pjContract.hjzzfls = arrayList.toTypedArray()

            }
            if (typeLx==1){
                pjContract!!.qutype = 0
            }else{
                pjContract!!.qutype = 0
                pjContract.zgyq = tv_xfzgyq_environmental.text.toString()
                val format = SimpleDateFormat("yyyy-MM-dd")//HHmmss
                val date = Date(System.currentTimeMillis())
                val filename = format.format(date)
                pjContract.kstime=tv_clshijian_environmental.text.toString()//filename   ///
                pjContract.qxtime=tv_xfclsj_environmental.text.toString()//TimeUtil.getDateAfter(sp_clqx_environmental.selectedItem.toString().toInt()+1)
                pjContract.xfjy = tv_xfjy_environmental.text.toString()
            }
            pjContract.jltime = tvUpDateEnviron!!.getText().toString()

            LoadingDialog.showDialogForLoading(activity)

            val fileIdList = ArrayList<Int>()
            fileIdList.addAll(videoIdList)
            for (i in PjEnviorFileList!!){
                fileIdList.add(i.id)
            }
            val strings = arrayOfNulls<Int>(fileIdList.size)
            val integers: Array<Int> = fileIdList.toArray<Int>(strings)

            pjContract.fileIds = integers
            LoadingDialog.cancelDialogForLoading()
            mPresenter.getHbLr(pjContract)

        }

        //????????????
        tvUploadMeetingSummary!!.setOnClickListener(View.OnClickListener {

            if (pjContract.wz.equals("")){
                ToastUtils.showShort("??????????????????")
                return@OnClickListener
            }
            LoadingDialog.showDialogForLoading(activity)
            isls = 1
            bt_temporary_environmental!!.isEnabled = false
            tvUploadMeetingSummary!!.isEnabled = false

            /*if (tvLrwtEnvironmental.text.toString().length == 0) { //"???????????????"
                ToastUtils.showShort("???????????????")
            } else */
            if (tvJlrEnvironmental.text.toString().length == 0) { //"???????????????"
                ToastUtils.showShort("??????????????????")
            }/*else if (tv_xfzgyq_environmental.text.toString().equals("")){
                ToastUtils.showShort("?????????????????????")
            }*/else {
                pjContract!!.questions = tvLrwtEnvironmental.text.toString()
                pjContract!!.zrdw = tvZrdwEnvironmental.text.toString()//???????????? 116.35698630592977 39.763910845715266  116.34465369407023 39.76122315428473
                val myLocation = aMap!!.myLocation//116.357132,39.767957   ??????:116.350798 39.76261  ?????????:116.3442884 39.7609968  ????????????:116.350068,39.762279
                val gps = PositionUtil.gcj_To_Gps84(myLocation.latitude, myLocation.longitude)
                if (xzqmc!!.pointRecordEntity!=null){
                    pjContract!!.location = xzqmc!!.pointRecordEntity.location
                }else{
                    pjContract!!.location = pointStr//"POINT(116.69999599456787 39.97817516326904)"//pointStr
                }
                pjContract!!.jlrtel = tvJlrPhone.text.toString()
//                pjContract!!.qutype = EnviorSupvsEnum.getIndex(tvWtztEnvironmental.selectedItem.toString())

                pjContract!!.jlr = tvJlrEnvironmental.text.toString()
                pjContract!!.remark = tvRemarkEnvironmental.text.toString()
//                pjContract!!.wz = tvWzenvironmental.text.toString()
                //????????????:sp_cncw_environmental ??????????????????:sp_sfgddw_environmental ??????????????????:sp_sfzdwt_environmental ??????????????????:sp_jcssgh_environmental ???????????????????????????:sp_tynysyxwh_environmental
                //??????????????????:sp_hcqtgh_environmental ????????????:sp_lhyh_environmental ????????????????????????:sp_jnlduyxwh_environmental ????????????:sp_wttj_environmental ??????????????????:sp_shljzl_environmental
                //??????????????????:sp_shwszl_environmental ????????????:sp_gcgh_environmental ????????????:sp_sdlj_environmental
                if (sp_cncw_environmental.isShown){
                    val cnwString = sp_cncw_environmental.selectedItem.toString()
                    pjContract.cnw = CnwEnum.getIndex(cnwString)
                }
//                if (sp_sfgddw_environmental.isShown){
                val sfgddwString = sp_sfgddw_environmental.selectedItem.toString()
                pjContract.gddw = IsTrueEnum.getIndex(sfgddwString)
//                }
                if (sp_sfzdwt_environmental.isShown){
                    val sfzdwtString = sp_sfzdwt_environmental.selectedItem.toString()
                    pjContract.zdwt = IsTrueEnum.getIndex(sfzdwtString)
                }
                if (sp_jcssgh_environmental.isShown){
                    val jcssghString = sp_jcssgh_environmental.selectedItem.toString()
                    val index = HjzzflEnum.getIndex(jcssghString)
                    pjContract.hjzzyjfl = index
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
                if (sp_wttj_environmental.isShown){
                    val wttjString = sp_wttj_environmental.selectedItem.toString()
                    val index = HjzzflEnum.getIndex(wttjString)
                    pjContract.hjzzyjfl = index
                }
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
                pjContract.hjzzyjfl = HjzzflEnum.getIndex(tv_environ_up_bt.text.toString())//hjzzList.get(sp_hjzz_environmental.selectedItemPosition).name

                if (ll_sjfl_environmental.isShown){
                    pjContract.hjzzfls = arrayOf(HjzzsjEnum.getIndex(sjflList.get(sp_sjfl_environmental.selectedItemPosition).name,
                            sjflList.get(sp_sjfl_environmental.selectedItemPosition).index))
                }else{

                    val arrayList = ArrayList<Int>()
                    for (i in sjflList){
                        if (i.isCheck){
                            for ( f in 0..i.num-1){
                                arrayList.add(i.index)
                            }
                        }
                    }
                    pjContract.hjzzfls = arrayList.toTypedArray()

                }


                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                try {
                    val dateString = simpleDateFormat.parse(tvUpDateEnviron!!.getText().toString())
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                pjContract.jltime = tvUpDateEnviron!!.getText().toString()
                if (typeLx==1||sjflList.get(sp_sjfl_environmental.selectedItemPosition).name.equals("??????????????????")){
                    pjContract!!.qutype = 1//1//??????
                }else{
                    pjContract!!.qutype = 3//3//??????
                    pjContract.zgyq = tv_xfzgyq_environmental.text.toString()
                    val format = SimpleDateFormat("yyyy-MM-dd")//HHmmss
                    val date = Date(System.currentTimeMillis())
                    val filename = format.format(date)
                    pjContract.kstime=tv_clshijian_environmental.text.toString()//filename   ///
                    pjContract.qxtime=tv_xfclsj_environmental.text.toString()//TimeUtil.getDateAfter(sp_clqx_environmental.selectedItem.toString().toInt()+1)
                    pjContract.xfjy = tv_xfjy_environmental.text.toString()
                }


                LoadingDialog.showDialogForLoading(activity)
//            mPresenter.getQueryCun(tvWzenvironmental.text.toString(),pjContract)
                val intList = java.util.ArrayList<Int>()
                var se = 0
                val fileIdList = ArrayList<Int>()
                fileIdList.addAll(videoIdList)
                for (i in PjEnviorFileList!!){
                    fileIdList.add(i.id)
                }
                val strings = arrayOfNulls<Int>(fileIdList.size)
                val integers: Array<Int> = fileIdList.toArray<Int>(strings)

                pjContract.fileIds = integers
                LoadingDialog.cancelDialogForLoading()
                mPresenter.getHbLr(pjContract)
//                }

            }
        })
        butClearMeetingSummary.setOnClickListener {
            photoAdapter!!.clearPics()
//            clearUpdate(tvNameEnvironmental, tvNameEnvironmental, tvLrwtEnvironmental, tvJlrEnvironmental, tvWzenvironmental, tvRemarkEnvironmental)
            if (envirorUpPopu!=null){
                envirorUpPopu!!.dismiss()
            }
        }
    }


    private fun initPopWttck(baobiaoList: ArrayList<BaobiaoBean>,et_wtlx_gddw:TextView) {
        var envirorUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_rjhj_wtxzk, supl_frag_rjhj)
        val contentView2: View = envirorUpPopu!!.getContentView()

        val findViewById = contentView2.findViewById<RecyclerView>(R.id.rlv_wtxzk)

        val but_wtxzk_qd = contentView2.findViewById<Button>(R.id.but_wtxzk_qd)
        findViewById.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        findViewById.adapter = object:BaseQuickAdapter<BaobiaoBean,BaseViewHolder>(R.layout.item_wtxzk,baobiaoList){
            override fun convert(helper: BaseViewHolder?, item: BaobiaoBean?) {
                helper!!.setText(R.id.cb_wtxzk, item!!.name)
                val et_wtxzk_gwtsl = helper.getView<EditText>(R.id.et_wtxzk_gwtsl)
                et_wtxzk_gwtsl.setText(item.num.toString())
//                item.num = 1
                et_wtxzk_gwtsl.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        if (!et_wtxzk_gwtsl.text.toString().equals("")){
                            item.num = et_wtxzk_gwtsl.text.toString().toInt()
                        }
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
                /*if (item!!.name.equals("?????????????????????")){
                    helper!!.setTextColor(R.id.cb_wtxzk, Color.RED)
                }*/
                val view = helper.getView<CheckBox>(R.id.cb_wtxzk)
                view.isChecked = item.isCheck
                view.setOnClickListener {
                    item.isCheck = view.isChecked
//                    notifyDataSetChanged()
                }
            }

        }
        but_wtxzk_qd.setOnClickListener {
            var s = ""
            for ( i in baobiaoList){
                if (i.isCheck){
                    if (s.equals("")){
                        s = i.name
                    }else{
                        s=s+"???"+i.name
                    }
                }
            }
            et_wtlx_gddw.setText(s)
            envirorUpPopu.dismiss()
        }
        // ????????????????????????
        var lp = activity!!.getWindow().getAttributes()
        lp.alpha = 0.7f
        activity!!.getWindow().setAttributes(lp)
        envirorUpPopu.setOnDismissListener(object :PopupWindow.OnDismissListener {

            override fun onDismiss() {
                var s = ""
                for ( i in baobiaoList){
                    if (i.isCheck){
                        if (s.equals("")){
                            s = i.name
                        }else{
                            s=s+"???"+i.name
                        }
                    }
                }
                et_wtlx_gddw.setText(s)
                var lp = activity!!.getWindow().getAttributes();
                lp.alpha = 1f;
                activity!!.getWindow().setAttributes(lp);
            }
        })
        CommenPop.backgroundAlpha(0.5f, activity)
        envirorUpPopu!!.showAtLocation(supl_frag_rjhj, Gravity.CENTER, 0, 0)

    }

    fun setImagesMarker(image:ArrayList<String>,imageWt:ArrayList<String>,latLng: LatLng){//??????????????????????????????
        val mInflater = LayoutInflater.from(context)
        val view1: View = mInflater.inflate(R.layout.item_image_marker, null)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_image_marker)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_image_marker_position)
        itemMarkerPosition.text = image.size.toString()
        if (image.size>0){
            //??????marker
            Glide.with(this).load(image.get(0))//
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            //????????????
                            ivItemMarker.setImageDrawable(resource)
//                        textView.setText(list.get(I).getGasName() + "");
                            var bitmap = convertViewToBitmap(view1)
                            aMap!!.addMarker(MarkerOptions()
                                    .position(latLng)
                                    .snippet("") //                .period(mid)//??????markerID
                                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .draggable(true))

                        }

                        /*@Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //????????????
                            imageView.setImageDrawable(resource);
                            textView.setText(list.get(I).getGasName() + "");
                            Bitmap bitmap = convertViewToBitmap(view);
                            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                    .position..;
                            aMap.addMarker(markerOption);
                        }*/
                    })
            aMap!!.setOnMarkerClickListener { marker ->//????????????
                val snippet = marker.snippet
                /*val intent = Intent(activity, ImageListActivity::class.java)
                intent.putExtra("snippet","??????")
                intent.putExtra("sjfl", sjfl)
                intent.putExtra("image", image)
                intent.putExtra("imageWt", imageWt)
                startActivity(intent)*/
                /*val snippet = marker.snippet
                val intent = Intent(activity, ImageListActivity::class.java)
                intent.putExtra("snippet",snippet)
                startActivity(intent)*/
                true
            }
        }


    }

    fun convertViewToBitmap(view: View): Bitmap {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }
    private fun addSjfl(values: Array<HjzzsjEnum>, sjflList: ArrayList<BaobiaoBean>,index:String) {
        for (i in values) {
            val subSequence = i.index.toString().subSequence(0, 3)
            if (subSequence.equals(index)) {
//                if (i.getName().contains(" ")){
                sjflList.add(BaobiaoBean(i.getName(),i.index))
//                }
            }
        }
    }
    private fun addNewSjfl(values: Array<HjzzsjEnum>, sjflList: ArrayList<BaobiaoBean>,index:String) {
        for (i in values) {
            val subSequence = i.index.toString().subSequence(0, 1)
            if (subSequence.equals(index)) {
//                if (i.getName().contains(" ")){
                sjflList.add(BaobiaoBean(i.getName(),i.index))
//                }
            }
        }
    }

    private fun setTime(editText: TextView) {//????????????  EditText
        val year = Calendar.getInstance()[Calendar.YEAR]
        val month = Calendar.getInstance()[Calendar.MONTH]
        val day = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
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
            editText.setText(nian+"-"+yue+"-"+ri)
        }, year, month, day)
        val datePicker = datePickerDialog.datePicker
        datePicker.maxDate = System.currentTimeMillis()+1000 ///< ???????????????????????????
        datePickerDialog.show()
    }

    //????????????????????????
    private fun initTimePickerView() {
        val df = SimpleDateFormat("yyyy-MM-dd")// HH:mm:ss
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate.set(1900, 1, 1)
        val endDate = Calendar.getInstance()
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH))
        timePickerView = TimePickerBuilder(activity, object : OnTimeSelectListener {
            @SuppressLint("SetTextI18n")
            override fun onTimeSelect(date1: Date?, v: View?) {
                tv_frag_rjhj_sjtime.text = getTime(date1!!)
                date = getTime(date1!!)
                page = 1
                enviorListEntity.clear()
                mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
                mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
            }
        })
                .setTimeSelectChangeListener(object : OnTimeSelectChangeListener {
                    override fun onTimeSelectChanged(date: Date?) {

                    }
                })
                .isDialog(true)
                .setType(booleanArrayOf(true, true, true, false, false, false))
                .setCancelColor(Color.parseColor("#999999"))
                .setItemVisibleCount(3)
                .setLineSpacingMultiplier(2.0f)
                .setDate(selectedDate)
                .setRangDate(startDate,endDate)
                .isAlphaGradient(true)
                .build()
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = timePickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        timePickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        timePickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }


    //??????????????????
    private fun initoptionPickerView(type: String) {
        var cunList = ArrayList<String>()
        //sysXzqEntityList
        for (i in sysXzqEntityList){
            cunList.add(i.name)
        }
        optionPickerView = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, options2, options3, v ->
            //?????????????????????????????????????????????
            if (type.equals("????????????")) {
                tv_frag_rjhj_wtlx.text = getWtlxData()[options1]
                tv_frag_rjhj_wtlx.setTextColor(Color.parseColor("#4CA2FE"))
                page = 1
                hjzzsjCha = sjwtIntList.get(options1)
                enviorListEntity.clear()
                mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
                mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
            } else if (type.equals("????????????")) {
                tv_frag_rjhj_zdwt.text = getZdwtData()[options1]
                tv_frag_rjhj_zdwt.setTextColor(Color.parseColor("#4CA2FE"))
                page = 1
                zdwtCha = options1 - 1
                enviorListEntity.clear()
                mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
                mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
            }else if (type.equals("????????????")) {
                tv_frag_rjhj_czmc.text = sysXzqEntityList.get(options1).name
                tv_frag_rjhj_czmc.setTextColor(Color.parseColor("#4CA2FE"))
                page = 1
                cunCode = sysXzqEntityList.get(options1).code
                enviorListEntity.clear()
                mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
                mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
            }
        })
                .setSubmitText("??????")//??????????????????
                .setCancelText("??????")//??????????????????
                .setTitleText(type)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //???????????????????????????
                .setOutSideCancelable(true)//????????????dismiss default true
                .setContentTextSize(20)
                .isDialog(true)
                .build<Any>()
        if (type.equals("????????????")) {
            optionPickerView!!.setPicker(getWtlxData() as List<Any>?)//???????????????
        } else if (type.equals("????????????")) {
            optionPickerView!!.setPicker(getZdwtData() as List<Any>?)//???????????????
        }else if (type.equals("????????????")) {
            optionPickerView!!.setPicker(cunList as List<Any>?)//???????????????
        }

        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = optionPickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        optionPickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        optionPickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        optionPickerView!!.show()
    }
    val sjwtIntList = ArrayList<Int>()
    //????????????
    fun getWtlxData(): ArrayList<String> {
        val sjwtList = ArrayList<String>()
        val values = HjzzsjEnum.values()
        sjwtIntList.clear()
        sjwtIntList.add(0)
        sjwtList.add("????????????")
        for (i in values){
            val substring = i.index.toString().substring(0, 1)
            if (substring.equals("1")||substring.equals("3")||substring.equals("7")){
                sjwtList.add(i.getName()+" - "+ HjzzflEnum.getName(substring.toInt()))
//                sjwtList.add(i.getName())
                sjwtIntList.add(i.index)
            }
        }
        return sjwtList
    }

    //????????????
    fun getZdwtData():ArrayList<String>{
        val zdwtList = ArrayList<String>()
        zdwtList.add("??????")
        zdwtList.add("???")
        zdwtList.add("???")
        return zdwtList
    }


    //??????????????????
    private fun getTime(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")// HH:mm:ss
        return format.format(date)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) // && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)
        Log.e("requestCode",""+requestCode)
        if (resultCode == Activity.RESULT_OK) {//&&requestCode != 66
            if (requestCode==233){
                var photos: ArrayList<String>? = null
                selectedPhotos.clear()

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    for (i in photos){
                        val file = File(i)
                        val name = file.name
                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        val file1: File = compressImages(bitmap, name)
                        upFile1(file1);
                    }
//                    selectedPhotos.addAll(photos)
                }
//                if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
            }/*else if (requestCode==224){
                var photos: ArrayList<String>? = null

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    for (i in photos){
                        val file = File(i)
                        val name = file.name
                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        val file1: File = compressImages(bitmap, name)
                        upFileFj(file1);
                    }
                }
            }*/else if(requestCode == 66){
                var uri = data!!.getData()
                if (uri != null){
                    selectedVideos.add(getRealPathFromURI(uri))
                }
                if (videoAdapter != null) videoAdapter!!.notifyDataSetChanged()
                if (selectedVideos.size >= 1){
                    var sdf = SimpleDateFormat("dd-MM-yyyy");
                    var c = Calendar.getInstance();
                    var date = sdf.format(c.getTime());//??????
                    var path= GetFileUtil.getSDCardPath()+"jymj/tzrjhj/video/"//+date+".mp4"
                    object :Thread() {
                        override fun run() {
                            super.run()
                            HttpUtils.runOnUiThread(object : Runnable {
                                override fun run() {
                                    LoadingDialog.showDialogForLoading(activity)
                                }

                            })
                            var s = 0
                            var file=File(path)
                            if (!file.exists()){
                                file.mkdirs()
                            }
                            try {
                                /**
                                 * ????????????
                                 * ???????????????:?????????????????????
                                 * ???????????????:??????????????????????????????
                                 */
                              /*  var comPressPath = SiliCompressor.with(getActivity()).compressVideo(getRealPathFromURI(uri!!), path,1280,
                                        720,
                                        2000000);//Video
                                file = File(comPressPath)
*/
                            } catch ( e: URISyntaxException) {
                                s=1
                                e.printStackTrace();
                            }finally {
                                if (s!=1){
                                    HttpUtils.runOnUiThread(object : Runnable {
                                        override fun run() {
                                            LoadingDialog.showDialogForLoading(activity)
//                                            upVideo(file)
                                        }

                                    })
                                }
                            }



                        }
                    }.start();
                }
            }else if (requestCode==20){
                var photoLists = data!!.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (photoLists != null ) {//&& !photoLists.isEmpty()
                    selectedPhotos = photoLists
                    if (photoAdapter!=null){
                        photoAdapter!!.setNewData(selectedPhotos)
                    }
                }
//                    mAddPicture.setPaths(mImagePaths);
            }else if (requestCode==TAKE_PHOTO_REQUEST){
                if (resultCode == Activity.RESULT_CANCELED) {
                    ToastUtils.showShort("???????????????")
                    return
                }

                path = cameraPath
//                GlideUtils.getGlideUtils().load(context, cameraPath, compileIvHead);
                if (path != null) {
                    val name = File(path).name
                    val bitmap = IOHelper.loadBitmap(File(path).path,true)
                    val file1: File = compressImages(bitmap, name)
                    uplodeEwmGc(file1,gcImageType)
                }
            }else if (requestCode==IMAGE_REQUEST_CODE){
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        val selectedImage = data!!.getData() //??????????????????????????????Uri
                        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                        val cursor = activity!!.getContentResolver().query(selectedImage!!, //getContentResolver
                                filePathColumn, null, null, null)//???????????????????????????Uri???????????????
                        cursor!!.moveToFirst()
                        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                        path = cursor.getString(columnIndex)  //??????????????????No Cannected Devices
                        cursor.close()

                        //                        compileIvHead.setImageBitmap(bitmap);
                        if (path != null) {
                            /*if (gcImageType == 1){
                                Glide.with(this).load(File(path)).into(image_ddgcmp_environmental!!)
                            }else{
                                Glide.with(this).load(File(path)).into(image_ddgcwg_environmental!!)
                            }*/
                            val name = File(path).name
                            val bitmap = IOHelper.loadBitmap(File(path).path,true)
                            val file1: File = compressImages(bitmap, name)

                            uplodeEwmGc(file1,gcImageType)
                        }
                    } catch (e: Exception) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace()
                    }

                }
            }

        }
    }
    /**
     * ????????????????????????????????????
     *
     * @return
     */
    fun uplodeEwmGc(file: File,gcImageType: Int) {
        val httpParams = HttpParams()

        httpParams.put("file", file)
        httpParams.put("type", gcImageType)
        OkGo.post<BaseRespose<RenovatedFile>>(ApiConstants.ENVIORFILE_UPLOAD_FILE)
                .params(httpParams)
                .execute(object : BaseNet<BaseRespose<RenovatedFile>>() {
                    override fun onStart(request: Request<BaseRespose<RenovatedFile>, out Request<*, *>?>?) {
                        super.onStart(request)
                        if (!LoadingDialog.isShowing()) LoadingDialog.showDialogForLoading(activity)
                    }

                    override fun onSuccess(response: Response<BaseRespose<RenovatedFile>>) {
                        val body = response.body()
                        if (body.data != null) {
                            renovatedFileList.add(body.data)
                            if (gcImageType == 1){
                                Glide.with(activity!!).load(body.data.url).into(image_ddgcmp_environmental!!)
                                image_ddgcmp_del_environmental!!.visibility = View.VISIBLE
                                gcImageURL1 = body.data.url
                            }else{
                                Glide.with(activity!!).load(body.data.url).into(image_ddgcwg_environmental!!)
                                image_ddgcwg_del_environmental!!.visibility = View.VISIBLE
                                gcImageURL2 = body.data.url
                            }
                        }

                        LoadingDialog.cancelDialogForLoading()
                    }

                    override fun onError(response: Response<BaseRespose<RenovatedFile>>) {
                        super.onError(response)
                        ToastUtils.showShort(response.exception.message)
                        LoadingDialog.cancelDialogForLoading()
                    }
                })
    }
    private fun upFile1( file2: File) {
        val decodeFile = BitmapFactory.decodeFile(file2.path)
//        PhotoBitmapUtils.amendRotatePhoto(file2.path,activity)
//        val decodeFile = IOHelper.loadBitmap(file2.path,true)
//        pjContract.wz
        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(context, decodeFile,  cunMing, 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(drawTextToRightBottom)
        val request = OkGo.post<BaseRespose<PjEnviorFileEntity>>(ApiConstants.ENVIORFILEUPLOAD)
                .isMultipart(true)
//        request.params("pid", AppCache.getInstance().getProid())
        request.params("type", 1)
        request.params("filetype", 1)
        request.params("msid", -1)
        request.params(file.name, file)//file
        request.execute(object : BaseNet<BaseRespose<PjEnviorFileEntity>>() {//BaseRespose<PjEnviorFileEntity>
        override fun onStart(request: Request<BaseRespose<PjEnviorFileEntity>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            LoadingDialog.showDialogForLoading(activity)
        }
            override fun onSuccess(response: Response<BaseRespose<PjEnviorFileEntity>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (body.getCode()==0){
                    PjEnviorFileList.add(body.data)
                    schxtp!!.setNewData(PjEnviorFileList)
                    schxtp!!.notifyDataSetChanged()
                }else{
                    ToastUtils.showShort(file.name+"????????????")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<PjEnviorFileEntity>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
            }
        })
    }
    fun getRealPathFromURI(contentUri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity!!.managedQuery(contentUri, proj, null, null, null)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }
    /**
     * ??????????????????????????????
     *
     * @param bitmap
     */
    fun compressImages(bitmap: Bitmap, fileName: String): File {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos) //???????????????????????????100????????????????????????????????????????????????baos???
        var options = 100
        while (baos.toByteArray().size / 1024 > 1024) { //?????????????????????????????????????????????500kb,??????????????????
            baos.reset() //??????baos?????????baos
            options -= 10 //???????????????10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) //????????????options%?????????????????????????????????baos???
            val length = baos.toByteArray().size.toLong()
        }
//        val format = SimpleDateFormat("yyyyMMddHHmmss")
//        val date = Date(System.currentTimeMillis())
//        val filename = format.format(date)
        //        File file = new File(Environment.getExternalStorageDirectory(), fileName + ".png");
        val file1 = File(GetFileUtil.getSDCardPath() + "jymj/tzrjhj/pic/")
        if (!file1.isDirectory) {
            file1.mkdirs()
        }
        val file = File(GetFileUtil.getSDCardPath()+"jymj/tzrjhj/pic/1"+fileName)
//        val file = File(Environment.getExternalStorageDirectory(), fileName)
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

    //??????
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
    val sysXzqEntityList = ArrayList<SysXzqEntity>()
    fun getXzqList(code:String,type:Int){
        sysXzqEntityList.clear()
       /* val httpParams1 = JSONObject()
        httpParams1.put("code",code)
        httpParams1.put("type",type)
        val encrypt = AesEncryptUtils.encrypt(httpParams1.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).upJson(sss.toString()).execute(
                object : BaseNet<String>(){//BaseRespose<ArrayList<SysXzqEntity>>
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                LoadingDialog.showDialogForLoading(activity)
            }
            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body!=null){
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<ArrayList<SysXzqEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<ArrayList<SysXzqEntity>>?>() {}.type)
                    val data = json.data
                    if (data != null){
                        if (data.size>0){
                            val sysXzqEntity = SysXzqEntity()
                            sysXzqEntity.name = "??????"
                            sysXzqEntity.code = AppCache.getInstance().code
                            sysXzqEntity.center = AppCache.getInstance().loginCenter
                            sysXzqEntityList.add(sysXzqEntity)
                            sysXzqEntityList.addAll(data)
                        }
                    }
                }else{
//                    SnackbarUtils.with(rjhj_cl_map_act).setMessage("????????????????????????").show()
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
            }
        })*/

    }

}
