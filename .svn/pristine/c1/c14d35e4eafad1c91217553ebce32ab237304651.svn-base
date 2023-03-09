package com.jymj.zhglxt.zjd.activity.zjdgl

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.MapsInitializer
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.ApiConstants.BASE_URL
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.bean.SuccessBean
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.*
import com.jymj.zhglxt.tools.LayerInit
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_tcgl_detail.*
import me.iwf.photopicker.PhotoPreview
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class TcglDetailActivity : BaseActivity<BasePresenter<*, *>, BaseModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_tcgl_detail
    }

    override fun initPresenter() {
    }

    private var aMap: AMap? = null
    private var mLayers = ArrayList<LegendEntity>()
    private var data: ApplyEntity? = null
    //    private var list = ArrayList<ApplyFileEntity>()
    private var legendsYL: ArrayList<LayerEntity>? = null
    private var legendsYL1: ArrayList<LayerEntity>? = null
    private var legendsFJ = ArrayList<LegendEntity>()
    val tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "/BMS/tdt/note/")
    val opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)

    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/zhglxt/osmKong1/")
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(GetFileUtil.getSDCardPath() + "BMS/map/zhglxt/osmKong/").diskCacheSize(10 * 1024)

//    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    private val VEC_URL = "https://a.tile.openstreetmap.fr/osmfr/%d/%d/%d.png"
    private val list1 = ArrayList<ApplyTcGlFileList>()
    private var mode = 0
    private var flag = 0
    private val PASS = 4
    private val BACK = 5
    private var dialog: SweetAlertDialog? = null
    private var normalPopu: CommenPop? = null
    private val mCustomItems = arrayOf("本地相册")//, "相机拍照"
    private var cameraPath: String? = null
    private var path: String? = null
    private val TAKE_PHOTO_REQUEST = 5
    private val IMAGE_REQUEST_CODE = 1
    var SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().path + "/zjdCamera/"// 拍照路径
    private var imgurl: String? = ""
    private var detailData: ZjdTcItemBean.DataBean? = null
    private var pop_sqgl_photo: ImageView? = null

    private var mPolygon: Polygon? = null
    private var mPolygonOptions: PolygonOptions? = null


    override fun initViews() {

        /*MainActivity.tvTitle?.setText("基本信息")
        MainActivity.bt_map!!.visibility = View.GONE*/
//        MapsInitializer.setApiKey("c114a893046db43fa28d1ea9bf4166f8");
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")

        MapsInitializer.replaceURL("ATH", "tdt-kbt54")//VEC_URL        map_tcgl_check_info?.onCreate(intent.extras)
        initAMap()
        setAMap("1036")

    }

    fun backToFront(view: View) {
        finish()
    }

    override fun initDatas() {
//        ActivityCache.getInstance().addActivity(this)
        val id = intent.extras.getInt("applyId")
        val sptype = intent.extras.getInt("sptype")
        //val applyEntity = intent.extras.getSerializable("zjdTcEntity")as ZjdTcEntity
        flag = sptype + 1
        mode = sptype + 1
        if (sptype == 0 || sptype == 1 || sptype == 2 || sptype == 3 || sptype == 4) {
            cl_tcgl_bottom?.visibility = View.VISIBLE
        } else {
            cl_tcgl_bottom?.visibility = View.GONE
//            ib_tc_delete?.visibility= View.GONE
        }
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.EXIT_INFO)//ApiConstants.APPLY_INFO//exit/info
//                .upJson(id.toString())
                .upJson(jsonObject)
                .execute(object : BaseNet<String>() {
                    override fun onSuccess(response: Response<String>?) {
                        val string = response?.body()
                        if (string != null) {//applyEntity
                            val decrypt = AesEncryptUtils.decrypt(string)
                            val applyEntitys = Gson().fromJson(decrypt, ZjdTcItemBean::class.java)
                            val applyEntity = applyEntitys.data

                            aMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(applyEntity?.ylEntity?.center, 19f))
                            aMap!!.addMarker(MarkerOptions().position(applyEntity?.ylEntity?.center))
                            kuangGeoment(applyEntity?.ylEntity?.geometry!!)
                            tcgl_info_cm?.text = "村名:" + applyEntity.ylEntity.xzqmc//村名
                            tcgl_info_hz?.text = "户主:" + applyEntity.ylEntity.hzmc//户主
                            tcgl_info_mph?.text = "门牌号:" + applyEntity.ylEntity.mph//门牌号
                            tcgl_info_area?.text = "面积:" + applyEntity.ylEntity.area.toString()//面积
                            tcgl_info_name?.text = "申请人姓名:" + applyEntity.sqName//申请人姓名
                            tcgl_info_sqrdh?.text = "申请人电话:" + applyEntity.iphone//申请人电话
                            tcgl_info_jjlxdh.text = "紧急联系电话:" + applyEntity.jjphone//紧急联系电话
                            tcgl_info_bzyj?.text = "备注意见:" + applyEntity.remark//备注意见
                            initSWeetAlert(applyEntity)
                            initDialog(applyEntity)
                            bt_tcgl_pass?.setOnClickListener {
                                mode = flag
                                /*dialog!!.setConfirmText("通过")
                            dialog!!.show()*/
                                CommenPop.backgroundAlpha(0.5F, this@TcglDetailActivity)
                                normalPopu!!.showAtLocation(tcgl_top, Gravity.CENTER, 0, 0)
                            }
                            bt_tcgl_back?.setOnClickListener {
                                mode = 5
                                /*dialog!!.setConfirmText("驳回")
                                dialog!!.show()*/
                                CommenPop.backgroundAlpha(0.5F, this@TcglDetailActivity)
                                normalPopu!!.showAtLocation(tcgl_top, Gravity.CENTER, 0, 0)
                            }
                            formatData(applyEntity)

                            recy_tcgl_check_info?.layoutManager = LinearLayoutManager(this@TcglDetailActivity, LinearLayoutManager.VERTICAL, false)
                            recy_tcgl_check_info?.addItemDecoration(SpacesItemDecoration(20))
                            recy_tcgl_check_info?.adapter = object : BaseQuickAdapter<ApplyTcGlFileList, BaseViewHolder>(R.layout.item_check_info, list1) {
                                override fun convert(helper: BaseViewHolder?, item: ApplyTcGlFileList?) {
                                    helper?.setText(R.id.tv_check_name, item?.name)
                                    val recyclerView = helper?.getView<RecyclerView>(R.id.recy_check_item)

                                    recyclerView?.layoutManager = GridLayoutManager(this@TcglDetailActivity, 2)
//                                    recyclerView?.addItemDecoration(SpacesItemDecoration(20))
                                    recyclerView?.adapter = object : BaseQuickAdapter<ZjdFileEntity, BaseViewHolder>(R.layout.item_home_frag, item?.list) {
                                        override fun convert(helper: BaseViewHolder?, item1: ZjdFileEntity?) {
                                            helper?.setText(R.id.tv_item_home_frag, item1?.key)
                                            val path = BASE_URL + item1?.path
                                            Glide.with(this@TcglDetailActivity).load(getGlideUrl(path!!))/*.centerCrop()
                                                    .placeholder(R.drawable.loading_progress)
                                                    .error(R.drawable.rect_error)*/
                                                    .into(helper?.getView(R.id.imgv_item_home_frag)!!)
                                            helper!!.getView<ImageView>(R.id.imgv_item_home_frag).setOnClickListener(View.OnClickListener {
                                                val arrayList = ArrayList<String>()
                                                val path = BASE_URL + item?.list!!.get(helper.adapterPosition)
                                                arrayList.add(path)
                                                PhotoPreview.builder()
                                                        .setPhotos(arrayList)
                                                        .setCurrentItem(0)
//                            .start(activity!!)
                                                        .start(this@TcglDetailActivity,20)//context!!
                                            })
                                        }
                                    }

                                }

                            }
                        } else {
                            ToastUtils.showShort("数据为空")
                        }
                    }

                    override fun onError(response: Response<String>?) {
                        super.onError(response)
                        if (response?.exception!=null){
                            ToastUtils.showShort(response?.exception?.message)
                        }
                    }
                })
        ib_tc_delete.setOnClickListener {
            AlertDialog.Builder(this).setTitle("确定删除吗")
                    .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            delete()
                        }
                    })
                    .setNegativeButton("取消", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {

                        }

                    }).show();
        }
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
            if (split.size < 2) {
                split = replace1.split("),(")
            }
            var s = 0
            for (i in 0..split.size - 1) {
                val latList = getLatList(split[i])
                if (s == 0) {
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
                    val latLng = LatLng(replace4.toDouble(), replace3.toDouble())
                    list.add(latLng)
                }

            }
        }
        return list
    }


    private fun delete() {
        /*val encrypt = AesEncryptUtils.encrypt("[" + detailData!!.id + "]")
        val sss = "{\"requestData\":\"" + encrypt + "\"}"*/
        val httpParams = JSONObject()
        httpParams.put("ids",detailData!!.id)
        val encrypt = AesEncryptUtils.encrypt(httpParams.toString())
        val sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.TCGL_DELETE_URL)
//                .upJson("[" + detailData!!.id + "]")
                .upJson(httpParams)
                .execute(object : BaseNet<String>() {
                    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                        super.onStart(request)
                        if (!LoadingDialog.isShowing())
                            LoadingDialog.showDialogForLoading(this@TcglDetailActivity)
                    }

                    override fun onSuccess(response: Response<String>?) {
                        val body = response!!.body()
                        if (body != null ) {
                            val decrypt = AesEncryptUtils.decrypt(body)
                            val json = Gson().fromJson(decrypt, SuccessBean::class.java)
                            when(json.code) {
                                0 -> {
                                    finish()
                                    ToastUtils.showShort("删除成功")
                                }
                                500 ->{
                                    ToastUtils.showShort(json.msg)
                                }
                            }
                        }
                    }

                    override fun onFinish() {
                        super.onFinish()
                        LoadingDialog.cancelDialogForLoading()
                    }

                    override fun onError(response: Response<String>?) {
                        super.onError(response)
                        if (response?.exception!=null){
                            ToastUtils.showShort(response?.exception?.message)
                        }
                    }
                })
    }

    private fun initDialog(applyEntity: ZjdTcItemBean.DataBean) {
        detailData = applyEntity
        normalPopu = CommenPop.getNormalPopu(this, R.layout.pop_sqgl_activity, tcgl_top)
        val contentView = normalPopu!!.contentView
        val pop_sqgl_remark = contentView.findViewById<EditText>(R.id.pop_sqgl_remark)
        pop_sqgl_photo = contentView.findViewById<ImageView>(R.id.pop_sqgl_photo)
        val pop_sqgl_delete_photo = contentView.findViewById<ImageButton>(R.id.pop_sqgl_delete_photo)
        val pop_sqgl_photo_name = contentView.findViewById<TextView>(R.id.pop_sqgl_photo_name)
        val pop_sqgl_btn = contentView.findViewById<Button>(R.id.pop_sqgl_btn)
        if (flag == 2) {
            pop_sqgl_photo_name.text = ""
        } else if (flag == 3) {
            pop_sqgl_photo_name.text = "《自愿退出宅基地协议》"
        } else if (flag == 4) {
            pop_sqgl_photo_name.text = ""
        } else if (flag == 5) {
            pop_sqgl_photo_name.text = ""
        }

        pop_sqgl_photo!!.setOnClickListener {

            showDialogCustom()

        }
        pop_sqgl_btn!!.setOnClickListener {

            /*if (pop_sqgl_remark.text.toString().isEmpty()){

            }else{*/
            val date = Date()

            val time = date.toLocaleString()

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            val sim = dateFormat.format(date)

            val httpParams = JSONObject()

            httpParams.put("id", applyEntity.id)
            httpParams.put("ylid", applyEntity.ylId)
            httpParams.put("sptype", if (mode != 7) mode else 6)
            httpParams.put("remark", pop_sqgl_remark.text.toString())
            //httpParams.put("dcDate", "")
            val encrypt = AesEncryptUtils.encrypt(httpParams.toString())
            val sss = "{\"requestData\":\"" + encrypt + "\"}"
            OkGo.post<String>(ApiConstants.TCGL_next_exit_URL)
                    .upJson(sss)
                    .execute(object : BaseNet<String>() {
                        override fun onSuccess(response: Response<String>?) {
                            val baseMessage = response?.body()
                            if (baseMessage != null) {
                                val decrypt = AesEncryptUtils.decrypt(baseMessage)
                                val json = Gson().fromJson(decrypt, SuccessBean::class.java)
                                when(json.code){
                                    0 ->{
                                        ToastUtils.showShort("审批完成")
                                        normalPopu!!.dismiss()
                                    }
                                    500->{
                                        ToastUtils.showShort(json.msg)
                                    }
                                }

                            }
                        }

                        override fun onError(response: Response<String>?) {
                            super.onError(response)
                            if (response?.exception!=null){
                                ToastUtils.showShort(response?.exception?.message)
                            }
                        }

                    })
            //}

        }

    }

    private fun showDialogCustom() {
        //创建对话框
        val builder = AlertDialog.Builder(this)
        builder.setTitle("请选择：")
        builder.setItems(mCustomItems, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                //相册
                //在这里跳转到手机系统相册里面
                val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            } else if (which == 1) {
                // 指定相机拍摄照片保存地址
                val state = Environment.getExternalStorageState()
                if (state == Environment.MEDIA_MOUNTED) {
                    cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".jpg"
                    val intent = Intent()
                    // 指定开启系统相机的Action
                    intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                    val out_file_path = SAVED_IMAGE_DIR_PATH
                    val dir = File(out_file_path)
                    if (!dir.exists()) {
                        dir.mkdirs()
                    } // 把文件地址转换成Uri格式
                    val uri = Uri.fromFile(File(cameraPath))
                    // 设置系统相机拍摄照片完成后图片文件的存放地址
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    startActivityForResult(intent, TAKE_PHOTO_REQUEST)
                } else {
                    Toast.makeText(this, "请确认已经插入SD卡",
                            Toast.LENGTH_LONG).show()
                }
            }
        })
        builder.setCancelable(true)//setCanceledOnTouchOutside
        val alertDialog = builder.create()
        alertDialog.show()
        //设置点击弹出框的其他地方弹出框消失
        alertDialog.setCanceledOnTouchOutside(true)
    }

    fun getGlideUrl(url: String): GlideUrl {
//        if (mGlideUrl == null) {
        val allCookie = OkGo.getInstance().cookieJar.cookieStore.allCookie
        val builder = LazyHeaders.Builder()
        val token = AppCache.getInstance().token
        if (token != null) {
            if (token.contains("Bearer ")) {//判断token是否包含Bearer
                builder.addHeader("Authorization", token);
            } else {
                builder.addHeader("Authorization", "Bearer " + token);
            }
        }
//            builder.addHeader("Authorization", AppCache.getInstance().token)
        if (AppCache.getInstance().token != null) {
            if (AppCache.getInstance().token.contains("Bearer ")) {//判断token是否包含Bearer
//                request.headers("Authorization", token)
                builder.addHeader("Authorization", AppCache.getInstance().token)
            } else {
                builder.addHeader("Authorization", "Bearer " + AppCache.getInstance().token)
//                request.headers("Authorization", "Bearer $token")
            }
        }
        builder.addHeader("Cookie") {
            val sb = StringBuilder()
            for (c in allCookie) {
                sb.append(c.name()).append("=").append(c.value()).append(";domain=").append(c.domain())
            }
            sb.toString()
        }
        return GlideUrl(url, builder.build())
//        return mGlideUrl
    }

    private fun initSWeetAlert(applyEntity: ZjdTcItemBean.DataBean) {
        val edt = EditText(this)
        edt.hint = "无建议"
        edt.gravity = Gravity.CENTER_VERTICAL
        dialog = SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setCustomView(edt)
                .setTitleText("审批")
                .setCancelText("取消")
                .setCancelClickListener {
                    it.dismiss()
                }
                .setConfirmClickListener {
                    it.dismiss()
                    if (edt.text.toString().isEmpty()) {
                        edt.error = "必须填写"
                    } else {
                        OkGo.post<BaseRespose<String>>(ApiConstants.APPLY_CHECK)
                                .params("applyId", applyEntity.id)
                                .params("sptype", if (mode == PASS) 4 else 5)
                                .params("ylId", applyEntity.ylEntity.gid)
                                .execute(object : BaseNet<BaseRespose<String>>() {
                                    override fun onSuccess(response: Response<BaseRespose<String>>?) {
//                                        AppCache.getInstance().deleteFJ=true
                                        ToastUtils.showShort("审批完成")
//                                        ActivityCache.getInstance().removeAllActivity()
                                    }

                                    override fun onError(response: Response<BaseRespose<String>>?) {
                                        super.onError(response)
                                        if (response?.exception!=null){
                                            ToastUtils.showShort(response?.exception?.message)
                                        }
                                    }

                                })
                        var logEntity = ApplyLogEntity()
                        logEntity.bhyj = edt.text.toString()
                        logEntity.sptype = if (mode == PASS) 4 else 5
                        OkGo.post<BaseRespose<String>>(ApiConstants.APPLY_LOG)
                                .upJson(Gson().toJson(logEntity))
                                .execute(object : BaseNet<BaseRespose<String>>() {
                                    override fun onSuccess(response: Response<BaseRespose<String>>?) {

                                    }

                                    override fun onError(response: Response<BaseRespose<String>>?) {
                                        super.onError(response)
                                        if (response?.exception!=null){
                                            ToastUtils.showShort(response?.exception?.message)
                                        }
                                    }

                                })

                    }
                }
        dialog!!.setCancelable(false)
    }

    private fun formatData(entity: ZjdTcItemBean.DataBean) {
        val fileList = entity.zjdFileEntityList
        val applyFileEntity1 = ApplyTcGlFileList()
        applyFileEntity1.name = "申请"
        val applyFileList1 = ArrayList<ZjdFileEntity>()
        for (f in fileList) {
            if (f.status == 1) {
                applyFileList1.add(f)
            }
        }
        applyFileEntity1.list = applyFileList1

        val applyFileEntity2 = ApplyTcGlFileList()
        applyFileEntity2.name = "镇政府调查"
        val applyFileList2 = ArrayList<ZjdFileEntity>()
        for (f in fileList) {
            if (f.status == 2) {
                applyFileList2.add(f)
            }
        }
        applyFileEntity2.list = applyFileList2

        val applyFileEntity3 = ApplyTcGlFileList()
        applyFileEntity3.name = "签订协议"
        val applyFileList3 = ArrayList<ZjdFileEntity>()
        for (f in fileList) {
            if (f.status == 3) {
                applyFileList3.add(f)
            }
        }
        applyFileEntity3.list = applyFileList3

        val applyFileEntity4 = ApplyTcGlFileList()
        applyFileEntity4.name = "农业农村局备案"
        val applyFileList4 = ArrayList<ZjdFileEntity>()
        for (f in fileList) {
            if (f.status == 4) {
                applyFileList4.add(f)
            }
        }
        applyFileEntity4.list = applyFileList4


        val applyFileEntity5 = ApplyTcGlFileList()
        applyFileEntity5.name = "国土分局注销"
        val applyFileList5 = ArrayList<ZjdFileEntity>()
        for (f in fileList) {
            if (f.status == 5) {
                applyFileList5.add(f)
            }
        }
        applyFileEntity5.list = applyFileList5

        list1.add(applyFileEntity1)
        list1.add(applyFileEntity2)
        list1.add(applyFileEntity3)
        list1.add(applyFileEntity4)
        list1.add(applyFileEntity5)
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
        addOverLayer(legendsYL!!)
        for (l in legendsYL!!) {
            l.isCheck = false
            when (l.name) {
                "天地图" -> {
                    l.isCheck = true
                }
                "宅翻建" -> {
                    l.isCheck = true
                }
                "村界" -> {
                    l.isCheck = true
                }
            }
        }
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
                "宅翻建" -> {
                    legend_tcgl_check_info!!.setDatas(legendsFJ!!)
                    legend_tcgl_check_info!!.visibility = View.VISIBLE
                    aMap!!.addTileOverlay(l.opt)
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
            aMap = map_tcgl_check_info?.map
        }
        setUpMap()
    }

    override fun onResume() {
        super.onResume()
        map_tcgl_check_info?.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_tcgl_check_info?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_tcgl_check_info?.onDestroy()
//        ActivityCache.getInstance().removeActivity(this)
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() {
        aMap?.isMyLocationEnabled = false// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            TAKE_PHOTO_REQUEST -> {
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "取消了拍照", Toast.LENGTH_LONG).show()
                    return
                }

                path = cameraPath
                val file = File(path)
                Glide.with(this).load(File(cameraPath)).into(pop_sqgl_photo!!)
                //                GlideUtils.getGlideUtils().load(context, cameraPath, compileIvHead);
                if (path != null) {
                    uplodeEwm(file, flag)
                }
            }

            IMAGE_REQUEST_CODE//这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
            -> if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                try {
                    val selectedImage = data!!.data //获取系统返回的照片的Uri
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = this.contentResolver.query(selectedImage, //getContentResolver
                            filePathColumn, null, null, null)//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    path = cursor.getString(columnIndex)  //获取照片路径No Cannected Devices
                    cursor.close()
                    val file = File(path)
                    Glide.with(this).load(File(path)).into(pop_sqgl_photo!!)
                    //                        compileIvHead.setImageBitmap(bitmap);
                    if (path != null) {
                        uplodeEwm(file, flag)
                    }
                } catch (e: Exception) {
                    // TODO Auto-generatedcatch block
                    e.printStackTrace()
                }

            }
        }
    }

    fun uplodeEwm(file: File, flag: Int) {
        val httpParams = HttpParams()
        httpParams.put("status", flag)
        if (flag == 2) {
            httpParams.put("key", 6)
        } else if (flag == 3) {
            httpParams.put("key", 6)
        } else if (flag == 4) {
            httpParams.put("key", 31)
        } else if (flag == 5) {
            httpParams.put("key", 6)
        }
        httpParams.put("remark", "")
        httpParams.put("zjdId", detailData!!.id)
        httpParams.put(file.name, file)

        OkGo.post<String>(ApiConstants.TCGL_upload_img_URL)
                .params(httpParams)
                .execute(object : BaseNet<String>() {
                    override fun onSuccess(response: Response<String>?) {
                        ToastUtils.showShort("上传成功")
                    }

                    override fun onError(response: Response<String>?) {
                        super.onError(response)
                        if (response?.exception!=null){
                            ToastUtils.showShort(response?.exception?.message)
                        }
                    }
                })

    }
}
