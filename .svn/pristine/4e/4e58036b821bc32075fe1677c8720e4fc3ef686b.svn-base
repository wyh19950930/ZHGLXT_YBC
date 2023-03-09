package com.jymj.zhglxt.zjd.activity.zjdgl

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.zxing.integration.android.IntentIntegrator
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.activity.ScanActivity
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.FlowAfterEntity
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.rjhj.bean.YLEntity
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.jymj.zhglxt.zjd.activity.PDFActivity
import com.jymj.zhglxt.zjd.adapter.QyglFileAdapter
import com.jymj.zhglxt.zjd.adapter.WyFzRoomFicAdapter
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseFileEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.wy.*
import com.jymj.zhglxt.zjd.contract.PropertyAddFzContract
import com.jymj.zhglxt.zjd.presenter.PropertyAddFzPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.mcxtzhang.swipemenulib.SwipeMenuLayout
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.AppUtils
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.yanzhenjie.recyclerview.SwipeMenu
import com.yanzhenjie.recyclerview.SwipeMenuCreator
import com.yanzhenjie.recyclerview.SwipeMenuItem
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration
import kotlinx.android.synthetic.main.activity_property_add_fz.*
import kotlinx.android.synthetic.main.activity_property_add_fz.view.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import java.math.BigDecimal

class PropertyAddFzActivity : BaseActivity<PropertyAddFzPresenter,BaseModel>(),PropertyAddFzContract.View {
    override fun returnRoomFileDetel(message: String, enterpriseFileEntity: HousingRoomFile,type:Int) {
        ToastUtils.showShort(message)
        when(type){
            1->{
                xsqList.remove(enterpriseFileEntity)
                uploadEntity!!.housingRooms.get(roomType).housingRoomFiles.remove(enterpriseFileEntity)
                xsqPicAdapter!!.setmList(xsqList)
                xsqPicAdapter!!.notifyDataSetChanged()
            }
            2->{
                xshList.remove(enterpriseFileEntity)
                uploadEntity!!.housingRooms.get(roomType).housingRoomFiles.remove(enterpriseFileEntity)
                xshPicAdapter!!.setmList(xshList)
                xshPicAdapter!!.notifyDataSetChanged()
            }
        }

    }

    override fun returnHtYlFileDetel(message: String,position:Int) {
        ToastUtils.showShort(message)
        val indexOf = uploadEntity.housingFiles.indexOf(zjStrList.get(position))
        uploadEntity.housingFiles.set(indexOf,HousingFile(uploadEntity.housingFiles.get(indexOf).type,""))

        zjStrList.get(position).url = ""
        if (xgzjAdapter != null) {
            xgzjAdapter!!.setNewData(zjStrList)
            xgzjAdapter!!.notifyDataSetChanged()
        }
    }

    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"//%d
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
    private var onCameraChange: LatLng? = null
    private var pointStr = ""
    private var legendsYL: ArrayList<LegendEntity>? = null
    private var legends4L: ArrayList<LegendEntity>? = null
    private val mLayers = ArrayList<LayerEntity>()
    private val mLayers1 = ArrayList<LayerEntity>()
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


    private var  addPolyline: Polyline? = null//高亮显示
    private var mPolylineOptions: PolylineOptions? = null
    private var mLatLng: LatLng? = null
    
    private var initRoomPopup: CommenPop? = null

    private var objectIdType = 0
    private var htFileType = 0
    private var removeHtFileType = -1//删除合同临时type
    private var uploadEntity = HousingEntity() //返租信息修改或添加
    private var fjDataList = ArrayList<HousingRoom>() //返租-房源信息修改或添加

    var housingRoomAdapter: BaseQuickAdapter<HousingRoom, BaseViewHolder>? = null
    private var xgzjAdapter: BaseQuickAdapter<HousingFile, BaseViewHolder>? = null
    private var uploadType = -1
    private var uploadIsType = false
    private var adapterPosition = 0
    private var rzxxUploadPopup: CommenPop? = null
    var SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().path + "/zhglxtCamera/"// 拍照路径
    private var cameraPath: String? = null
    private var path: String? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_property_add_fz
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")//添加天地图  VEC_URL, "tdt1-vec"

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_act_pptaddfz.onCreate(this.intent.extras)
        initAMap()
        setAMap()

        supl_act_pptaddfz.panelHeight = DisplayUtil.dip2px(50f)
        supl_act_pptaddfz.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

        rlv_act_pptaddfz_room_list.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rlv_act_pptaddfz_room_list.isItemViewSwipeEnabled = false// 侧滑删除，默认关闭
        val itemDecoration = DefaultItemDecoration(Color.parseColor("#eeeeee"))
        rlv_act_pptaddfz_room_list.addItemDecoration(itemDecoration)
        /*rlv_act_pptaddfz_room_list.setSwipeMenuCreator(object :SwipeMenuCreator{
            override fun onCreateMenu(leftMenu: SwipeMenu?, rightMenu: SwipeMenu?, position: Int) {
                val swipeMenuItem = SwipeMenuItem(this@PropertyAddFzActivity)
                swipeMenuItem.setBackground(R.color.red)
                        .setText("删除")
                        .setWidth(200)
                        .setHeight(RecyclerView.LayoutParams.MATCH_PARENT)
                        .setTextColor(Color.WHITE)
                rightMenu!!.addMenuItem(swipeMenuItem)
            }
        })*/

        ll_act_property_add_fz_add.setOnClickListener {

            initRoomPopup(-1, HousingRoom())

//            startActivity(Intent(this,PropertyFzRoomPicActivity::class.java))
        }

        iv_act_ppt_add_fz_fzht.setOnClickListener {
            if (iv_act_ppt_add_fz_fzht_close.visibility == View.VISIBLE){
                for (i in 0..uploadEntity.housingFiles.size-1){
                    if (uploadEntity.housingFiles.get(i).type==1){
                        var path = uploadEntity.housingFiles.get(i).url
                        LoadingDialog.showDialogForLoading(this)
//                val docChild = adapter?.getItem(position) as DocChild?
                        val storageDir = File(Environment.getExternalStorageDirectory().absolutePath + "/JYMJManager/download/files")
                        if (!storageDir.exists()) {
                            storageDir.mkdirs()
                        }
                        OkGo.get<File>(path).execute(object : FileCallback(storageDir.path, uploadEntity.housingFiles.get(i).filename) {
                            override fun onSuccess(response: Response<File>) {
                                LoadingDialog.cancelDialogForLoading()
                                FileUtils.openFile(this@PropertyAddFzActivity, File(storageDir.path + "/" + uploadEntity.housingFiles.get(i).filename), AppUtils.getAppPackageName()+"_file")
                            }

                            override fun downloadProgress(progress: Progress?) {
//                                Log.e("downloadProgress","${progress!!.status}")
                            }

                            override fun onFinish() {
                                super.onFinish()
                            }

                            override fun onError(response: Response<File>) {
                                super.onError(response)
                                LoadingDialog.cancelDialogForLoading()
                            }
                        })


                    }
                }
            }else{
                selectFile(234)
            }
            htFileType = 1
        }
        iv_act_ppt_add_fz_xsht.setOnClickListener {
            if (iv_act_ppt_add_fz_xsht_close.visibility == View.VISIBLE){
                for (i in 0..uploadEntity.housingFiles.size-1){
                    if (uploadEntity.housingFiles.get(i).type==2){
                        FileUtilsFjxc.openFile(this, File(uploadEntity.housingFiles.get(i).url))
                    }
                }
            }else{
                selectFile(234)
            }
            htFileType = 2
        }
        iv_act_ppt_add_fz_yl1.setOnClickListener {
            if (iv_act_ppt_add_fz_yl1_close.visibility == View.VISIBLE){
                for (i in 0..uploadEntity.housingFiles.size-1){
                    if (uploadEntity.housingFiles.get(i).type==3){
                        FileUtilsFjxc.openFile(this, File(uploadEntity.housingFiles.get(i).url))
                    }
                }
            }else{
                selectFile(234)
            }
            htFileType = 3
        }
        iv_act_ppt_add_fz_yl2.setOnClickListener {
            if (iv_act_ppt_add_fz_yl2_close.visibility == View.VISIBLE){
                for (i in 0..uploadEntity.housingFiles.size-1){
                    if (uploadEntity.housingFiles.get(i).type==4){
                        FileUtilsFjxc.openFile(this, File(uploadEntity.housingFiles.get(i).url))
                    }
                }
            }else{
                selectFile(234)
            }
            htFileType = 4
        }
    }
    var zjStrList = ArrayList<HousingFile>()
    override fun initDatas() {

        rlv_act_ppt_add_fz_htyl.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)

        val propertyDto = intent.getSerializableExtra("PropertyDto") as PropertyDto
        if (propertyDto!=null){
            if (propertyDto.yl!=null){
                val ylEntity = propertyDto.yl
                moveCenter(ylEntity)
                tabTc("流动人口",1)
                objectIdType = ylEntity.objectid
                tv_act_pptaddfz_mph.text = ylEntity.mph
                tv_act_pptaddfz_cqr.text = ylEntity.hzmc
                tv_act_pptaddfz_jzmj.setText(ylEntity.jianzhuArea.toString())
                tv_act_pptaddfz_jzcs.text = ylEntity.jzcs.toString()
                when(ylEntity.jzjg){
                    1->{
                        tv_act_pptaddfz_jzjg.setText("简易房")
                    }
                    2->{
                        tv_act_pptaddfz_jzjg.setText("砖木结构")
                    }
                    3->{
                        tv_act_pptaddfz_jzjg.setText("钢筋混泥土结构")
                    }
                    4->{
                        tv_act_pptaddfz_jzjg.setText("钢结构")
                    }
                }

                uploadEntity.ylId = ylEntity.objectid.toLong()
            }

            if (propertyDto.qrCode!=null){
                uploadType = 1

                ll_act_ppt_add_fz_smewm.visibility = View.GONE
                iv_act_ppt_add_fz_ewmfh.visibility = View.VISIBLE

                Glide.with(mContext).load(propertyDto.qrCode.path)
                        .into(iv_act_ppt_add_fz_ewmfh)
            }

            if (propertyDto.housing!=null){
                uploadEntity = propertyDto.housing

                tv_act_pptaddfz_jcsj.text = uploadEntity.jcsj
                tv_act_pptaddfz_fjs.text = uploadEntity.fjs.toString()//房间数
                tv_act_pptaddfz_zqq.text = uploadEntity.startDate//租期
                tv_act_pptaddfz_zqz.text = uploadEntity.endDate//租期
                tv_act_pptaddfz_fzje.setText(uploadEntity.rentMoney)//返租金额
                tv_act_pptaddfz_remark.setText(uploadEntity.remark)
                if (uploadEntity.housingFiles!=null&&uploadEntity.housingFiles.size>0){
                    zjStrList.add(HousingFile(0, ""))
                    zjStrList.add(HousingFile(1, ""))
                    zjStrList.add(HousingFile(2, ""))
                    zjStrList.add(HousingFile(3, ""))
//                    zjStrList = uploadEntity.housingFiles as ArrayList<HousingFile>
                    for (i in zjStrList.indices){
                        for (f in uploadEntity.housingFiles){

                            if (zjStrList.get(i).type==f.type){
                                zjStrList.set(i,f)
                            }
                        }
                    }

                    /*val list = uploadEntity.housingFiles
                    for (i in 0..list.size-1){
                        when (list.get(i).type){
                            1->{
                                Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(list.get(i).url)))
                                        .into(iv_act_ppt_add_fz_fzht!!)
                                iv_act_ppt_add_fz_fzht_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_fzht.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_fzht.visibility = View.GONE
                            }
                            2->{
                                Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(list.get(i).url)))
                                        .into(iv_act_ppt_add_fz_xsht!!)
                                iv_act_ppt_add_fz_xsht_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_xsht.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_xsht.visibility = View.GONE
                            }
                            3->{
                                Glide.with(mContext).load(list.get(i).url)
                                        .into(iv_act_ppt_add_fz_yl1)
                                iv_act_ppt_add_fz_yl1_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_yl1.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_yl1.visibility = View.GONE
                            }
                            4->{
                                Glide.with(mContext).load(list.get(i).url)
                                        .into(iv_act_ppt_add_fz_yl2)
                                iv_act_ppt_add_fz_yl2_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_yl2.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_yl2.visibility = View.GONE
                            }
                        }
                    }*/
                }else{
                    zjStrList.add(HousingFile(0, ""))
                    zjStrList.add(HousingFile(1, ""))
                    zjStrList.add(HousingFile(2, ""))
                    zjStrList.add(HousingFile(3, ""))
                }
                xgzjAdapter = object : BaseQuickAdapter<HousingFile, BaseViewHolder>(R.layout.item_wy_cz_czxx_file_list, zjStrList) {
                    override fun convert(helper: BaseViewHolder?, item: HousingFile?) {
                        when (item!!.type) {
                            0 -> {
                                helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "返租合同")
                                        .setText(R.id.tv_wy_cz_czxx_file_title2, "返租合同")
                            }
                            1 -> {
                                helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "修缮合同")
                                        .setText(R.id.tv_wy_cz_czxx_file_title2, "修缮合同")
                            }
                            2 -> {
                                helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "院落照片1")
                                        .setText(R.id.tv_wy_cz_czxx_file_title2, "院落照片1")
                            }
                            3 -> {
                                helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "院落照片2")
                                        .setText(R.id.tv_wy_cz_czxx_file_title2, "院落照片2")
                            }
                        }


                        val title1 = helper!!.getView<TextView>(R.id.tv_wy_cz_czxx_file_title1)
                        val close = helper!!.getView<ImageView>(R.id.iv_wy_cz_czxx_file_close)
                        val rlt = helper.getView<RelativeLayout>(R.id.rlt_wy_cz_czxx_file)
                        if (!item.url.equals("")) {
                            close.visibility = View.VISIBLE
                            rlt.visibility = View.VISIBLE
                            title1.visibility = View.GONE
                        } else {
                            close.visibility = View.GONE
                            rlt.visibility = View.GONE
                            title1.visibility = View.VISIBLE
                        }

                        Glide.with(this@PropertyAddFzActivity).load(item.url).into(
                                helper.getView<ImageView>(R.id.iv_wy_cz_czxx_file))
                        helper.itemView.setOnClickListener {
                            adapterPosition = helper.adapterPosition
                            if (!item.url.equals("")){
                                val url = item.url
                                val intent = Intent(this@PropertyAddFzActivity, PDFActivity::class.java)
                                intent.putExtra("pdf",url.trim())
                                startActivity(intent)
                            }else{
                                addRoomPicPopup()
                            }
                        }
                        close.setOnClickListener {
                            initDeletePopup(helper.adapterPosition)
                        }
                    }
                }
                rlv_act_ppt_add_fz_htyl.adapter = xgzjAdapter


                if (uploadEntity.housingRooms!=null&&uploadEntity.housingRooms.size>0){
                    fjDataList = uploadEntity.housingRooms as ArrayList<HousingRoom>
                    housingRoomAdapter = object : BaseQuickAdapter<HousingRoom, BaseViewHolder>(R.layout.item_wy_fz_room_list,uploadEntity.housingRooms) {
                        override fun convert(helper: BaseViewHolder, item: HousingRoom) {

                            helper.setText(R.id.tv_item_wy_fz_room_list, item.bh)
                            when(item.repair){
                                1->{
                                    helper.getView<ImageView>(R.id.iv_item_wy_fz_room_list).setBackgroundResource(R.drawable.room_item_dxs_icon)
                                }
                                2->{
                                    helper.getView<ImageView>(R.id.iv_item_wy_fz_room_list).setBackgroundResource(R.drawable.room_item_yxs_icon)
                                }
                            }

                            helper.getView<LinearLayout>(R.id.ll_item_wy_fz_room_list).setOnClickListener {
                                initRoomPopup(helper.adapterPosition,item)
                            }
                            helper.getView<Button>(R.id.bt_item_wy_fz_room_list).setOnClickListener {
                                initRoomPicPopup(item.bh,helper.adapterPosition,item.housingRoomFiles)
                            }
                            val menuLayout = helper.getView<SwipeMenuLayout>(R.id.swlt_item_wy_fz_room_list)
                            helper.getView<TextView>(R.id.tv_item_wy_fz_room_list_det).setOnClickListener {
                                initDeleteRoomPopup(helper.adapterPosition,menuLayout)
                            }
                        }

                    }
                    rlv_act_pptaddfz_room_list.adapter = housingRoomAdapter
                }
            }
        }

        ll_act_ppt_add_fz_smewm.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                var intentIntegrator = IntentIntegrator(this);//ScanActivity
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                intentIntegrator.setBarcodeImageEnabled(true)//设置是否保存图片
                intentIntegrator.setBeepEnabled(false)//设置扫码成功后的提示音是否显示
                intentIntegrator.setOrientationLocked(false)//该方法用于设置方向锁
                intentIntegrator.setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
                intentIntegrator.setCaptureActivity(ScanActivity::class.java) // 设置自定义的activity
                intentIntegrator.initiateScan() // 开始扫描
            }
        }


        iv_act_ppt_add_fz_fzht_close.setOnClickListener {
            for (i in 0..uploadEntity.housingFiles.size-1){
                if (uploadEntity.housingFiles.get(i).type==1){
                    removeHtFileType = i
                }
            }
            if (removeHtFileType!=-1){
                uploadEntity.housingFiles.removeAt(removeHtFileType)
                iv_act_ppt_add_fz_fzht_close.visibility = View.GONE
                rlt_act_ppt_add_fz_fzht.visibility = View.GONE
                tv_act_ppt_add_fz_fzht.visibility = View.VISIBLE
                Glide.with(mContext).load(R.drawable.icon_up_file)
                        .into(iv_act_ppt_add_fz_fzht)
                removeHtFileType = -1
            }
        }
        iv_act_ppt_add_fz_xsht_close.setOnClickListener {
            for (i in 0..uploadEntity.housingFiles.size-1){
                if (uploadEntity.housingFiles.get(i).type==2){
                    removeHtFileType = i
                }
            }
            if (removeHtFileType!=-1){
                uploadEntity.housingFiles.removeAt(removeHtFileType)
                iv_act_ppt_add_fz_xsht_close.visibility = View.GONE
                rlt_act_ppt_add_fz_xsht.visibility = View.GONE
                tv_act_ppt_add_fz_xsht.visibility = View.VISIBLE
                Glide.with(mContext).load(R.drawable.icon_up_file)
                        .into(iv_act_ppt_add_fz_xsht)
                removeHtFileType = -1
            }
        }
        iv_act_ppt_add_fz_yl1_close.setOnClickListener {
            for (i in 0..uploadEntity.housingFiles.size-1){
                if (uploadEntity.housingFiles.get(i).type==3){
                    removeHtFileType = i
                }
            }
            if (removeHtFileType!=-1){
                uploadEntity.housingFiles.removeAt(removeHtFileType)
                iv_act_ppt_add_fz_yl1_close.visibility = View.GONE
                rlt_act_ppt_add_fz_yl1.visibility = View.GONE
                tv_act_ppt_add_fz_yl1.visibility = View.VISIBLE
                Glide.with(mContext).load(R.drawable.icon_up_file)
                        .into(iv_act_ppt_add_fz_yl1)
                removeHtFileType = -1
            }
        }
        iv_act_ppt_add_fz_yl2_close.setOnClickListener {
            for (i in 0..uploadEntity.housingFiles.size-1){
                if (uploadEntity.housingFiles.get(i).type==4){
                    removeHtFileType = i
                }
            }
            if (removeHtFileType!=-1){
                uploadEntity.housingFiles.removeAt(removeHtFileType)
                iv_act_ppt_add_fz_yl2_close.visibility = View.GONE
                rlt_act_ppt_add_fz_yl2.visibility = View.GONE
                tv_act_ppt_add_fz_yl2.visibility = View.VISIBLE
                Glide.with(mContext).load(R.drawable.icon_up_file)
                        .into(iv_act_ppt_add_fz_yl2)
                removeHtFileType = -1
            }
        }


        //选择建成时间
        tv_act_pptaddfz_jcsj.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,tv_act_pptaddfz_jcsj.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    tv_act_pptaddfz_jcsj.text = data
                }
            })
        }
        //选择租期开始时间
        tv_act_pptaddfz_zqq.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,tv_act_pptaddfz_zqq.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    tv_act_pptaddfz_zqq.text = data
                }
            })
        }
        //选择租期结束时间
        tv_act_pptaddfz_zqz.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,tv_act_pptaddfz_zqz.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    tv_act_pptaddfz_zqz.text = data
                }
            })
        }
        //提交修改全部数据
        bt_act_pptfzadd_upload.setOnClickListener {
            if (uploadType == -1){
                ToastUtils.showShort("请先绑定二维码！")
                return@setOnClickListener
            }


            uploadEntity.mph = tv_act_pptaddfz_mph.text.toString()
            uploadEntity.name = tv_act_pptaddfz_cqr.text.toString()
            uploadEntity.jcsj = tv_act_pptaddfz_jcsj.text.toString()
            uploadEntity.jzmj = tv_act_pptaddfz_jzmj.text.toString().toBigDecimal()
            uploadEntity.jzcs = tv_act_pptaddfz_jzcs.text.toString()
            uploadEntity.jzjg = tv_act_pptaddfz_jzjg.text.toString()
            uploadEntity.fjs = uploadEntity.housingRooms.size
            if (tv_act_pptaddfz_zqq.text.toString().equals("")){
                ToastUtils.showShort("请选择租期开始时间！")
                return@setOnClickListener
            }
            if (tv_act_pptaddfz_zqz.text.toString().equals("")){
                ToastUtils.showShort("请选择租期结束时间！")
                return@setOnClickListener
            }
            uploadEntity.startDate = tv_act_pptaddfz_zqq.text.toString()
            uploadEntity.endDate = tv_act_pptaddfz_zqz.text.toString()
            if (tv_act_pptaddfz_fzje.text.toString().equals("")){
                ToastUtils.showShort("请输入返租金额！")
                return@setOnClickListener
            }
            uploadEntity.rentMoney = tv_act_pptaddfz_fzje.text.toString()
            uploadEntity.remark = tv_act_pptaddfz_remark.text.toString()

            /*if (iv_act_ppt_add_fz_fzht_close.visibility == View.GONE){
                ToastUtils.showShort("请上传返租合同！")
                return@setOnClickListener
            }else if (iv_act_ppt_add_fz_xsht_close.visibility == View.GONE){
                ToastUtils.showShort("请上传修缮合同！")
                return@setOnClickListener
            }else if (iv_act_ppt_add_fz_yl1_close.visibility == View.GONE){
                ToastUtils.showShort("请上传两张院落照片！")
                return@setOnClickListener
            }else if (iv_act_ppt_add_fz_yl2_close.visibility == View.GONE){
                ToastUtils.showShort("请上传两张院落照片！")
                return@setOnClickListener
            }else */if (uploadEntity.housingRooms.size==0){
                ToastUtils.showShort("请上传房间信息！")
                return@setOnClickListener
            }else if (uploadEntity.housingFiles.size <= 3) {
                ToastUtils.showShort("请上传完整合同或院落照片！")
            }else{
            for (i in uploadEntity.housingFiles.indices){
                if (uploadEntity.housingFiles.get(i).url.equals("")){
                    uploadIsType = false
                }else{
                    uploadIsType = true
                }

            }
            if (!uploadIsType){
                    ToastUtils.showShort("请上传完整相关证件！")
                    return@setOnClickListener
                }
                mPresenter.getPropertySaveOrUpdate(uploadEntity)
            }
        }

    }
    //删除院落合同或者照片
    fun initDeletePopup(position: Int) {
        rzxxUploadPopup = CommenPop.getNormalPopu(this, R.layout.pop_delete, ll_act_ppt_aff_fz_top)
        val contentView = rzxxUploadPopup!!.contentView
        val confirm = contentView.findViewById<TextView>(R.id.pop_delete_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_delete_close)
        CommenPop.backgroundAlpha(0.5f, this)
        rzxxUploadPopup!!.showAtLocation(ll_act_ppt_aff_fz_top, Gravity.CENTER, 0, 0)
        confirm.setOnClickListener {

            val indexOf = uploadEntity.housingFiles.indexOf(zjStrList.get(position))
            mPresenter.getHtYlFileDetel(uploadEntity.housingFiles.get(indexOf).id,position)

            rzxxUploadPopup!!.dismiss()
        }
        close.setOnClickListener {
            rzxxUploadPopup!!.dismiss()
        }

    }


    //删除房间
    fun initDeleteRoomPopup(position:Int,swipeMenuLayout: SwipeMenuLayout) {
        initRoomPopup = CommenPop.getNormalPopu(this, R.layout.pop_delete, ll_act_ppt_aff_fz_top)
        val contentView = initRoomPopup!!.contentView
        val confirm = contentView.findViewById<TextView>(R.id.pop_delete_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_delete_close)
        CommenPop.backgroundAlpha(0.5f, this)
        initRoomPopup!!.showAtLocation(ll_act_ppt_aff_fz_top, Gravity.CENTER, 0, 0)
        confirm.setOnClickListener {
//            fjDataList.removeAt(position)
            uploadEntity.housingRooms.removeAt(position)
            if (housingRoomAdapter!=null){
                housingRoomAdapter!!.setNewData(uploadEntity.housingRooms)
                housingRoomAdapter!!.notifyDataSetChanged()
            }
            initRoomPopup!!.dismiss()
            swipeMenuLayout.quickClose()
        }
        close.setOnClickListener {
            initRoomPopup!!.dismiss()
            swipeMenuLayout.quickClose()
        }

    }
    /**
     * 初始化AMap对象
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_act_pptaddfz.getMap()
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
    private fun tabTc(tuCe:String,type:Int) {//type 1 清 2 不清  , type:Int
        val stringList = ArrayList<String>()//TabLayerMapBean

        for (i in mLayers ){//layerList
            if (i.num == 1&&tuCe.equals("")){
                i.isCheck = false
            }

            if (type == 1){
                if (i.num == 1){
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }else{
                        i.isCheck = false
                    }
                }else if (i.num == 2){
                    if (i.name.equals(tuCe)) {
                        i.isCheck = !i.isCheck
                    }
                }
            }else if (type == 2){
                if (i.name.equals(tuCe)) {
                    i.isCheck = !i.isCheck
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
        if (aMap!=null){
            aMap!!.clear()
            addOverLayer(mLayers)//问题在这里
            aMap!!.animateCamera(CameraUpdateFactory.scrollBy(1f, 0f))
        }

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
    //添加图层
    private fun initTuli() {
        //地图图层
        legendsYL = ArrayList<LegendEntity>()
        legends4L = ArrayList<LegendEntity>()
        LayerInit.initMain(AppCache.getInstance().code, mLayers, mLayers1!!, legendsYL!!)//, legends4L!!
        addOverLayer(mLayers)
    }
    //移动到镇/村中心点
    private fun moveCenter(ylEntity:YLEntity) {
        initTuli()//图层
        Thread(object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(1000); // 延时1.7秒  这个可能需要根据代码的多少有所调整/找到解决定位的方法
                    val point = RltUtil.getCenter(ylEntity.center1)
                        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 17f, 0f, 0f)))
                    if (!ylEntity.geometry.equals("")){
                        kuangGeomentLine(ylEntity.geometry)//删除之前的   高亮显示翻建点查的院落
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }).start()
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

    fun initRoomPopup(index:Int,housingRoom: HousingRoom){
        initRoomPopup = CommenPop.getNormalPopu(this, R.layout.pop_ppt_fz_add_room, ll_act_ppt_aff_fz_top)
        val contentView = initRoomPopup!!.contentView
        val edittext = contentView.findViewById<EditText>(R.id.pop_ppt_fz_add_room_fjbh)
        val confirm = contentView.findViewById<TextView>(R.id.pop_ppt_fz_add_room_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_ppt_fz_add_room_close)
        CommenPop.backgroundAlpha(0.5f, this)
        initRoomPopup!!.showAtLocation(ll_act_ppt_aff_fz_top, Gravity.CENTER, 0, 0)
        var data = HousingRoom()
        if (index!=-1&&housingRoom!=null){
            data = housingRoom
            edittext.setText(data.bh)

        }
        close.setOnClickListener {
            initRoomPopup!!.dismiss()
        }
        confirm.setOnClickListener {

            if (edittext.text.toString().equals("")){
                ToastUtils.showShort("请输入房间编号")
            }else{
                data.bh = edittext.text.toString()
                data.repair = 1
                if (index!=-1&&housingRoom!=null){
                    if (data.housingRoomFiles!=null&&data.housingRoomFiles.size>0){
                        for (i in  0..data.housingRoomFiles.size-1){
                            if (data.housingRoomFiles.get(i).type==2){
                                data.repair = 2
                            }
                        }
                    }
//                    fjDataList.add(index,data)
//                    fjDataList.removeAt(index+1)
                    uploadEntity.housingRooms.add(index,data)
                    uploadEntity.housingRooms.removeAt(index+1)
                }else{
//                    fjDataList.add(data)
                    uploadEntity.housingRooms.add(data)
                }
                if (housingRoomAdapter!=null){
                    housingRoomAdapter!!.setNewData(uploadEntity.housingRooms)
                    housingRoomAdapter!!.notifyDataSetChanged()
                }else{
                    housingRoomAdapter = object : BaseQuickAdapter<HousingRoom, BaseViewHolder>(R.layout.item_wy_fz_room_list,uploadEntity.housingRooms) {
                        override fun convert(helper: BaseViewHolder, item: HousingRoom) {

                            helper.setText(R.id.tv_item_wy_fz_room_list, item.bh)
                            when(item.repair){
                                1->{
                                    helper.getView<ImageView>(R.id.iv_item_wy_fz_room_list).setBackgroundResource(R.drawable.room_item_dxs_icon)
                                }
                                2->{
                                    helper.getView<ImageView>(R.id.iv_item_wy_fz_room_list).setBackgroundResource(R.drawable.room_item_yxs_icon)
                                }
                            }
                            
                            helper.getView<LinearLayout>(R.id.ll_item_wy_fz_room_list).setOnClickListener {
                                initRoomPopup(helper.adapterPosition,item)
                            }
                            helper.getView<Button>(R.id.bt_item_wy_fz_room_list).setOnClickListener {
                                initRoomPicPopup(item.bh,helper.adapterPosition,item.housingRoomFiles)
                            }
                            val menuLayout = helper.getView<SwipeMenuLayout>(R.id.swlt_item_wy_fz_room_list)
                            helper.getView<TextView>(R.id.tv_item_wy_fz_room_list_det).setOnClickListener {
                                initDeleteRoomPopup(helper.adapterPosition,menuLayout)
                            }

                        }

                    }
                    rlv_act_pptaddfz_room_list.adapter = housingRoomAdapter
                }
                initRoomPopup!!.dismiss()
                tv_act_pptaddfz_fjs.text = uploadEntity.housingRooms.size.toString()//房间数
            }
        }
    }
    var xsqList = ArrayList<HousingRoomFile>()
    var xshList = ArrayList<HousingRoomFile>()
    private var xsqPicAdapter: WyFzRoomFicAdapter? = null
    private var xshPicAdapter: WyFzRoomFicAdapter? = null
    private var isPoint = false
    private var selectedPhotos = ArrayList<String>()
    var roomType = 0 //房间索引
    var roomPicType = 0 //房间修缮前、后分类 用于回显刷新适配器
    fun initRoomPicPopup(name:String,index:Int,housingRoomFile: List<HousingRoomFile>){
        isPoint = true
        roomType = index
        xsqList.clear()
        xshList.clear()

        initRoomPopup = CommenPop.getNormalPopu(this, R.layout.pop_ppt_fz_add_room_pic, ll_act_ppt_aff_fz_top)
        val contentView = initRoomPopup!!.contentView
        val title = contentView.findViewById<TextView>(R.id.tv_pop_ppt_add_room_pic_title)
        val confirm = contentView.findViewById<TextView>(R.id.bt_pop_ppt_add_room_pic_confirm)
        val close = contentView.findViewById<TextView>(R.id.bt_pop_ppt_add_room_pic_close)
        val xsqRlv = contentView.findViewById<RecyclerView>(R.id.rlv_pop_ppt_add_room_pic_xsq)
        val xshRlv = contentView.findViewById<RecyclerView>(R.id.rlv_pop_ppt_add_room_pic_xsh)
        CommenPop.backgroundAlpha(0.5f, this)
        initRoomPopup!!.showAtLocation(ll_act_ppt_aff_fz_top, Gravity.CENTER, 0, 0)
        xsqRlv.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        xshRlv.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)

        title.text = name

        if (housingRoomFile!=null&&housingRoomFile.size>0){
            for (i in 0..housingRoomFile.size-1){
                if (housingRoomFile.get(i).type == 1){
                    xsqList.add(housingRoomFile.get(i))
                }
                if (housingRoomFile.get(i).type == 2){
                    xshList.add(housingRoomFile.get(i))
                }
            }
        }
        xsqPicAdapter = WyFzRoomFicAdapter(this, xsqList)
        xsqRlv.adapter = xsqPicAdapter
        xshPicAdapter = WyFzRoomFicAdapter(this, xshList)
        xshRlv.adapter = xshPicAdapter

        xsqPicAdapter!!.setOnClickLister(object:WyFzRoomFicAdapter.OnQyglFileLinear{
            override fun onClick(enterpriseFileEntity: HousingRoomFile?, position:Int) {
                if (enterpriseFileEntity == null) {
                    roomPicType = 1
                    isPoint = false
                    if (SingleOnClickUtil1.isFastClick()){
                        PhotoPicker.builder()
                                .setPhotoCount(PhotoAdapter.MAX)
                                .setShowCamera(true)
                                .setPreviewEnabled(false)
                                .setSelected(selectedPhotos)
//                            .start(activity!!)
                                .start(this@PropertyAddFzActivity!!,233)
                    }

                } else {
                    var pathList = ArrayList<String>()
                    for (i in xsqList) {
                        val pic = i.path//ApiConstants.BASE_URL + i.path
                        pathList.add(pic)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(position)
                            .setShowDeleteButton(false)
                            .start(this@PropertyAddFzActivity!!)
                }
            }

            override fun onDeleteClick(enterpriseFileEntity: HousingRoomFile?,position:Int) {
                // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                var builder = AlertDialog.Builder(this@PropertyAddFzActivity)
                // 设置Title的图标
                builder.setIcon(R.mipmap.ic_launcher)
                // 设置Title的内容
                builder.setTitle("弹出警告框")
                // 设置Content来显示一个信息
                builder.setMessage("确定删除吗？")
                // 设置一个PositiveButton
                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        isPoint = false
                        mPresenter.getRoomFileDetel(enterpriseFileEntity!!.id,enterpriseFileEntity,1)
                    }
                })
                // 设置一个NegativeButton
                builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog!!.dismiss()
                    }
                })
                builder.show()
            }
        })
        xshPicAdapter!!.setOnClickLister(object:WyFzRoomFicAdapter.OnQyglFileLinear{
            override fun onClick(enterpriseFileEntity: HousingRoomFile?, position:Int) {
                if (enterpriseFileEntity == null) {
                    roomPicType = 2
                    isPoint = false
                    if (SingleOnClickUtil1.isFastClick()){
                        PhotoPicker.builder()
                                .setPhotoCount(PhotoAdapter.MAX)
                                .setShowCamera(true)
                                .setPreviewEnabled(false)
                                .setSelected(selectedPhotos)
//                            .start(activity!!)
                                .start(this@PropertyAddFzActivity!!,233)
                    }

                } else {
                    var pathList = ArrayList<String>()
                    for (i in xshList) {
                        val pic = i.path//ApiConstants.BASE_URL + i.path
                        pathList.add(pic)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(position)
                            .setShowDeleteButton(false)
                            .start(this@PropertyAddFzActivity!!)
                }
            }

            override fun onDeleteClick(enterpriseFileEntity: HousingRoomFile?,position:Int) {
                // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                var builder = AlertDialog.Builder(this@PropertyAddFzActivity)
                // 设置Title的图标
                builder.setIcon(R.mipmap.ic_launcher)
                // 设置Title的内容
                builder.setTitle("弹出警告框")
                // 设置Content来显示一个信息
                builder.setMessage("确定删除吗？")
                // 设置一个PositiveButton
                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        isPoint = false
                        mPresenter.getRoomFileDetel(enterpriseFileEntity!!.id,enterpriseFileEntity,2)
                    }
                })
                // 设置一个NegativeButton
                builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog!!.dismiss()
                    }
                })
                builder.show()
            }
        })
        confirm.setOnClickListener {
            initRoomPopup!!.dismiss()
        }
        close.setOnClickListener {
            initRoomPopup!!.dismiss()
        }
    }

    /**
     * 通过手机选择文件
     */
    fun selectFile(requestCode:Int) {
//        var intent = Intent(Intent.ACTION_GET_CONTENT)
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.setType("*/*")
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(Intent.createChooser(intent, "选择文件上传"), requestCode);
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "请安装一个文件管理器.", Toast.LENGTH_SHORT).show();
        }

    }
    fun addRoomPicPopup() {
        rzxxUploadPopup = CommenPop.getNormalPopu(this, R.layout.pop_upload_status, ll_act_ppt_aff_fz_top)
        val contentView = rzxxUploadPopup!!.contentView
        val camera = contentView.findViewById<LinearLayout>(R.id.pop_upload_status_camera)
        val album = contentView.findViewById<LinearLayout>(R.id.pop_upload_status_album)
        val close = contentView.findViewById<TextView>(R.id.pop_wy_cz_rzry_close)
        CommenPop.backgroundAlpha(0.5f, this)
        rzxxUploadPopup!!.showAtLocation(ll_act_ppt_aff_fz_top, Gravity.CENTER, 0, 0)
        close.setOnClickListener {
            rzxxUploadPopup!!.dismiss()
        }
        album.setOnClickListener {
            val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
            rzxxUploadPopup!!.dismiss()
        }
        camera.setOnClickListener {
            cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg"
            val intent = Intent()
            // 指定开启系统相机的Action
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            val out_file_path = SAVED_IMAGE_DIR_PATH
            val dir = File(out_file_path)
            if (!dir.exists()) {
                dir.mkdirs()
            } // 把文件地址转换成Uri格式
//            val uri = Uri.fromFile(File(cameraPath))
            val uri = FileProvider.getUriForFile(this, "com.jymj.bzczzhglxt_file", File(cameraPath));

            // 设置系统相机拍摄照片完成后图片文件的存放地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(intent, 2)
            rzxxUploadPopup!!.dismiss()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {//&&requestCode != 66
            if (requestCode==233){
                var photos: ArrayList<String>? = null
//                selectedPhotos.clear()

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    for (i in photos){
                        val file = File(i)
                        val name = file.name
                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        val file1: File = compressImages(bitmap, name)
                        uploadRoomFile(roomPicType,file1)
                    }
                }
            }else if (requestCode==234){
                var isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                if (!isSdCardExist) {
                    ToastUtils.showShort("SD卡不可用,请检查")
                    return
                }
                if (data!=null){
                    var uri = data!!.getData()
                    if (uri!=null){
                        val realPathFromURI = FileUtilFjxc1.getPath(this, uri)
                        uploadHtFile(htFileType,File(realPathFromURI))
//                        iv_act_ppt_frp_xsq1_pic.setImageBitmap(BitmapFactory.decodeStream(FileInputStream(realPathFromURI)))
                    }

                }
            }else if (requestCode==1){
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        val selectedImage = data!!.getData() //获取系统返回的照片的Uri
                        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                        val cursor = this@PropertyAddFzActivity.getContentResolver().query(selectedImage, //getContentResolver
                                filePathColumn, null, null, null)//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst()
                        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                        path = cursor.getString(columnIndex)  //获取照片路径No Cannected Devices

                        cursor.close()
                        val options1 = RequestOptions()
                                .centerCrop()
                                .placeholder(R.mipmap.ic_launcher)

                        uploadHtFile(adapterPosition, File(path))
                    } catch (e: Exception) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace()
                    }

                }
            }else if (requestCode==2){
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    if (resultCode == RESULT_CANCELED) {
                        ToastUtils.showShort("取消了拍照")
                        return
                    }
                    val options = RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher)

                    path = cameraPath
                    uploadHtFile(adapterPosition, File(cameraPath))

                }
            }else{
                // 获取解析结果  二维码扫描结果
                var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);//IntentResult
                if (result != null) {
                    if (result.getContents() == null) {
                        ToastUtils.showShort("取消扫描")
                    } else {
                        if (result.getContents()!=null){
                            val split = result.getContents().split("id=")
                            if (split.size>1){
                                mPresenter.getEwmsmZjdBd(objectIdType.toLong(),split[1])
                            }else{
                                mPresenter.getEwmsmZjdBd(objectIdType.toLong(),result.getContents())
                            }
                        }else{
                            ToastUtils.showShort("扫描二维码错误")
                        }
                    }
                }
            }
        }
    }

    //上传院落合同等
    private fun uploadHtFile(type:Int,file2: File) {
        val decodeFile = BitmapFactory.decodeFile(file2.path)
        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(this, decodeFile,  "", 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(drawTextToRightBottom)
        val request = OkGo.post<BaseRespose<HousingFile>>(ApiConstants.PROPERTY_UPLOADFILE)
                .isMultipart(true)
//        request.params("pid", AppCache.getInstance().getProid())
        /* if (isXh){//xh
 //            request.params("gid", id)
         }*/
        request.params("type", type)
//        request.params("isLease", isLease)//file  file.name
        request.params("file", file)//file  file.name
        request.execute(object : BaseNet<BaseRespose<HousingFile>>() {//BaseRespose<HousingFile>
        override fun onStart(request: Request<BaseRespose<HousingFile>, out Request<Any, Request<*, *>>>?) {//HousingFile
            super.onStart(request)
            LoadingDialog.showDialogForLoading(this@PropertyAddFzActivity)
        }
            override fun onSuccess(response: Response<BaseRespose<HousingFile>>) {
//                super.onSuccess(response)
                val body = response.body()

                if (body.getCode()==0){
                    if (body.data!=null){
                        uploadEntity!!.housingFiles.add(body.data)
                        zjStrList.set(adapterPosition,body.data)

                        xgzjAdapter!!.setNewData(zjStrList)
                        xgzjAdapter!!.notifyDataSetChanged()
                        /*when(htFileType){
                            1->{
                                Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(body.data.url)))
                                        .into(iv_act_ppt_add_fz_fzht!!)
                                iv_act_ppt_add_fz_fzht_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_fzht.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_fzht.visibility = View.GONE
                            }
                            2->{
                                Glide.with(mContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(body.data.url)))
                                        .into(iv_act_ppt_add_fz_xsht!!)
                                iv_act_ppt_add_fz_xsht_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_xsht.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_xsht.visibility = View.GONE
                            }
                            3->{
                                Glide.with(mContext).load(body.data.url)
                                        .into(iv_act_ppt_add_fz_yl1)
                                iv_act_ppt_add_fz_yl1_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_yl1.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_yl1.visibility = View.GONE
                            }
                            4->{
                                Glide.with(mContext).load(body.data.url)
                                        .into(iv_act_ppt_add_fz_yl2)
                                iv_act_ppt_add_fz_yl2_close.visibility = View.VISIBLE
                                rlt_act_ppt_add_fz_yl2.visibility = View.VISIBLE
                                tv_act_ppt_add_fz_yl2.visibility = View.GONE
                            }
                        }*/
                    }

                }else{
                    ToastUtils.showShort(file.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<HousingFile>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
            }
        })
    }
    //上传各房屋图片
    private fun uploadRoomFile(type:Int,file2: File) {
        val decodeFile = BitmapFactory.decodeFile(file2.path)
        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(this, decodeFile,  "", 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(drawTextToRightBottom)
        val request = OkGo.post<BaseRespose<HousingRoomFile>>(ApiConstants.PROPERTY_UPLOADROOMFILE)
                .isMultipart(true)
//        request.params("pid", AppCache.getInstance().getProid())
        /* if (isXh){//xh
 //            request.params("gid", id)
         }*/
        request.params("type", type)
//        request.params("isLease", isLease)//file  file.name
        request.params("file", file)//file  file.name
        request.execute(object : BaseNet<BaseRespose<HousingRoomFile>>() {//BaseRespose<HousingFile>
        override fun onStart(request: Request<BaseRespose<HousingRoomFile>, out Request<Any, Request<*, *>>>?) {//HousingFile
            super.onStart(request)
            LoadingDialog.showDialogForLoading(this@PropertyAddFzActivity)
        }
            override fun onSuccess(response: Response<BaseRespose<HousingRoomFile>>) {
//                super.onSuccess(response)
                val body = response.body()

                if (body.getCode()==0){
                    if (body.data!=null){
                        uploadEntity!!.housingRooms.get(roomType).housingRoomFiles.add(body.data)

                        if (type == 1){
                            xsqList.add(body.data)
                            xsqPicAdapter!!.setmList(xsqList)
                            xsqPicAdapter!!.notifyDataSetChanged()
                        }else{
                            xshList.add(body.data)
                            xshPicAdapter!!.setmList(xshList)
                            xshPicAdapter!!.notifyDataSetChanged()

                            uploadEntity!!.housingRooms.get(roomType).repair = 2

                            if (housingRoomAdapter!=null){
                                housingRoomAdapter!!.setNewData(uploadEntity!!.housingRooms)
                                housingRoomAdapter!!.notifyDataSetChanged()
                            }
                        }
                    }

                }else{
                    ToastUtils.showShort(file.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<HousingRoomFile>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
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
//        val format = SimpleDateFormat("yyyyMMddHHmmss")
//        val date = Date(System.currentTimeMillis())
//        val filename = format.format(date)
        //        File file = new File(Environment.getExternalStorageDirectory(), fileName + ".png");
        val file1 = File(AppMenus.getInstance().cardPath + "BMS/map/pic/")
        if (!file1.isDirectory) {
            file1.mkdirs()
        }
        val file = File(AppMenus.getInstance().cardPath+"BMS/map/pic/1"+fileName)
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
    
    override fun onResume() {
        super.onResume()
        map_act_pptaddfz.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        map_act_pptaddfz.onPause()
    }
    override fun returnWwjPoint(entity: WwjEntity) {
    }
    override fun returnEwmsmZjdBd(msg: YlFolwEntity) {
        if (msg!=null){
            if (msg!!.qrCodeFileEntity!=null){
                ll_act_ppt_add_fz_smewm.visibility = View.GONE
                iv_act_ppt_add_fz_ewmfh.visibility = View.VISIBLE
                Glide.with(mContext).load(msg!!.qrCodeFileEntity.path)
                        .into(iv_act_ppt_add_fz_ewmfh)
                uploadType = 1
            }
        }
    }


    override fun returnPropertySaveOrUpdate(msg: String) {
        ToastUtils.showShort(msg)
        finish()
    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        if (msg!=null){
            ToastUtils.showShort(msg)
        }else{
            ToastUtils.showShort("请求失败")
        }

        LoadingDialog.cancelDialogForLoading()
    }

}
