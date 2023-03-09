package com.jymj.zhglxt.zjd.activity.zjdgl

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.activity.InformationAcquisitionActivity
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.zjd.activity.PDFActivity
import com.jymj.zhglxt.zjd.bean.wy.HousingFile
import com.jymj.zhglxt.zjd.bean.wy.LeaseDto
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.bean.wy.WyFileIsUpload
import com.jymj.zhglxt.zjd.contract.PropertyRzxxContract
import com.jymj.zhglxt.zjd.contract.PropertyYlfjContract
import com.jymj.zhglxt.zjd.presenter.PropertyRzxxPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.mcxtzhang.swipemenulib.SwipeMenuLayout
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.baseapp.AppManager
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_property_rzxx.*
import java.io.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period

/**
 * 入住信息activity
 */
class PropertyRzxxActivity : BaseActivity<PropertyRzxxPresenter, BaseModel>(), PropertyRzxxContract.View {


    private var rzxxUploadPopup: CommenPop? = null
    var SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().path + "/zhglxtCamera/"// 拍照路径
    private var cameraPath: String? = null
    private var path: String? = null
    private var xgzjAdapter: BaseQuickAdapter<HousingFile, BaseViewHolder>? = null
    private var adapterPosition = 0
    private var uploadEntity = LeaseDto()//提交入住信息
    private var roomIdFlag: Long = 0
    private var housingFilesList = ArrayList<HousingFile>()

    private var idCardFlag = ""
    private var mphFlag = ""
    private var objectidFlag = ""
    private var uploadIsType = false

    override fun getLayoutId(): Int {
        return R.layout.activity_property_rzxx
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        rlv_act_wy_cz_rzxx_xgzj.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        val rzxxType = intent.getIntExtra("rzxxType", 0)
        if (rzxxType == 1) {

            ll_act_ppt_cz_rzxx_upload.visibility = View.GONE
            bt_act_ppt_cz_rzxx_upload.visibility = View.VISIBLE

            val flowInfoEntity = intent.getSerializableExtra("rzxxEntity") as FlowInfoEntity
            val rzxxRoomIdFlag = intent.getLongExtra("rzxxRoomIdFlag", 0L)
            if (flowInfoEntity != null) {
                tv_act_ppt_cz_rzxx_czr.text = flowInfoEntity.name
                uploadEntity.flowInfoId = flowInfoEntity.id
            }
            if (rzxxRoomIdFlag != 0L) {
                uploadEntity.roomId = rzxxRoomIdFlag
            }
            idCardFlag = flowInfoEntity.idCard
            mphFlag = flowInfoEntity.mph
            objectidFlag = flowInfoEntity.objectid.toString()
        } else if (rzxxType == 2) {

            ll_act_ppt_cz_rzxx_upload.visibility = View.VISIBLE
            bt_act_ppt_cz_rzxx_upload.visibility = View.GONE
            val yczEntity = intent.getSerializableExtra("yczEntity") as LeaseDto
            if (yczEntity != null) {
                tv_act_ppt_cz_rzxx_czr.text = yczEntity.czr
                tv_act_ppt_cz_rzxx_czmj.setText(yczEntity.czmj)
                tv_act_ppt_cz_rzxx_zlqx.text = yczEntity.zlqx
                tv_act_ppt_cz_rzxx_zlje.setText(yczEntity.zlje)
                tv_act_ppt_cz_rzxx_zffs.text = yczEntity.zffs
                tv_act_ppt_cz_rzxx_qzrq.text = yczEntity.startDate
                tv_act_ppt_cz_rzxx_tzrq.text = yczEntity.endDate
                tv_act_ppt_cz_rzxx_remark.setText(yczEntity.bz)
                uploadEntity.roomId = yczEntity.roomId
                uploadEntity.flowInfoId = yczEntity.flowInfoId

                if (yczEntity.housingFiles != null && yczEntity.housingFiles.size > 0) {
//                    housingFilesList = yczEntity.housingFiles as ArrayList<HousingFile>
                    uploadEntity.housingFiles = yczEntity.housingFiles as ArrayList<HousingFile>
                }

                bt_act_ppt_cz_rzxx_tz_upload.setOnClickListener {
                    val intent = Intent(this,PropertyTzActivity::class.java)
                    intent.putExtra("yczEntity",yczEntity)
                    startActivity(intent)
                }
                idCardFlag = yczEntity.idCard
                mphFlag = yczEntity.mph
                objectidFlag = yczEntity.objectId.toString()
            }
        }

        ll_act_ppt_cz_rzxx_czrxx.setOnClickListener {
            val intent = Intent(this, InformationAcquisitionActivity::class.java)
            intent.putExtra("ldrkDetailIdCard",idCardFlag)
            intent.putExtra("ldrkDetailYlId",objectidFlag)
            intent.putExtra("ldrkDetailMph",mphFlag)
            startActivity(intent)
        }


    }

    var zjStrList = ArrayList<HousingFile>()
    override fun initDatas() {

        if (uploadEntity.housingFiles != null && uploadEntity.housingFiles.size > 0) {
            zjStrList.add(HousingFile(0, ""))
            zjStrList.add(HousingFile(1, ""))
            zjStrList.add(HousingFile(2, ""))
            zjStrList.add(HousingFile(3, ""))
            zjStrList.add(HousingFile(4, ""))
            zjStrList.add(HousingFile(5, ""))
            zjStrList.add(HousingFile(6, ""))
            zjStrList.add(HousingFile(7, ""))
//            zjStrList = uploadEntity.housingFiles as ArrayList<HousingFile>
            for (i in zjStrList.indices){
                for (f in uploadEntity.housingFiles){
                    if (zjStrList.get(i).type == f.type){
                        zjStrList.set(i,f)
                    }
                }
            }
        } else {
            zjStrList.add(HousingFile(0, ""))
            zjStrList.add(HousingFile(1, ""))
            zjStrList.add(HousingFile(2, ""))
            zjStrList.add(HousingFile(3, ""))
            zjStrList.add(HousingFile(4, ""))
            zjStrList.add(HousingFile(5, ""))
            zjStrList.add(HousingFile(6, ""))
            zjStrList.add(HousingFile(7, ""))
        }

        xgzjAdapter = object : BaseQuickAdapter<HousingFile, BaseViewHolder>(R.layout.item_wy_cz_czxx_file_list, zjStrList) {
            override fun convert(helper: BaseViewHolder?, item: HousingFile?) {
                when (item!!.type) {
                    0 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "身份证正面")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "身份证正面")
                    }
                    1 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "身份证反面")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "身份证反面")
                    }
                    2 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "居住证正面")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "居住证正面")
                    }
                    3 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "居住证反面")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "居住证反面")
                    }
                    4 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "合同复印件")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "合同复印件")
                    }
                    5 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "支付凭证")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "支付凭证")
                    }
                    6 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "入住前验收照片1")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "入住前验收照片1")
                    }
                    7 -> {
                        helper!!.setText(R.id.tv_wy_cz_czxx_file_title1, "入住前验收照片2")
                                .setText(R.id.tv_wy_cz_czxx_file_title2, "入住前验收照片2")
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

                Glide.with(this@PropertyRzxxActivity).load(item.url).into(
                        helper.getView<ImageView>(R.id.iv_wy_cz_czxx_file))
                helper.itemView.setOnClickListener {
                    adapterPosition = helper.adapterPosition
                    if (!item.url.equals("")){
                        val url = item.url
                        val intent = Intent(this@PropertyRzxxActivity, PDFActivity::class.java)
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
        rlv_act_wy_cz_rzxx_xgzj.adapter = xgzjAdapter
        //选择租期开始时间
        tv_act_ppt_cz_rzxx_qzrq.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this, tv_act_ppt_cz_rzxx_qzrq.text.toString(), object : TimePickerUtil.OnTimePickerLister {
                override fun onClick(data: String?) {
                    tv_act_ppt_cz_rzxx_qzrq.text = data
                }
            })
        }
        //停租时间
        tv_act_ppt_cz_rzxx_tzrq.setOnClickListener {
            if (tv_act_ppt_cz_rzxx_qzrq.text.toString().equals("")) {
                ToastUtils.showShort("请选择起租日期！")
                return@setOnClickListener
            }
            TimePickerUtil.initTimePickerViewNyr(this, tv_act_ppt_cz_rzxx_tzrq.text.toString(), object : TimePickerUtil.OnTimePickerLister {
                override fun onClick(data: String?) {
                    tv_act_ppt_cz_rzxx_tzrq.text = data
                    val between = Period.between(LocalDate.parse(tv_act_ppt_cz_rzxx_qzrq.text.toString()), LocalDate.parse(tv_act_ppt_cz_rzxx_tzrq.text.toString()))
                    tv_act_ppt_cz_rzxx_zlqx.text = between.years.toString() + "年" + between.months.toString() + "月" + between.days.toString() + "天"
                }
            })
        }
        bt_act_ppt_cz_rzxx_upload.setOnClickListener {
            uploadData(1)
        }
        bt_act_ppt_cz_rzxx_xz_upload.setOnClickListener {
            uploadData(2)
        }

    }

    private fun uploadData(type:Int){
        if (tv_act_ppt_cz_rzxx_czmj.text.toString().equals("")) {
            ToastUtils.showShort("请填写出租面积！")
        } else if (tv_act_ppt_cz_rzxx_zlje.text.toString().equals("")) {
            ToastUtils.showShort("请填写租赁金额！")
        }else if (tv_act_ppt_cz_rzxx_qzrq.text.toString().equals("")) {
            ToastUtils.showShort("请选择起租日期！")
        }else if (tv_act_ppt_cz_rzxx_tzrq.text.toString().equals("")) {
            ToastUtils.showShort("请选择停租日期！")
        }else if (uploadEntity.housingFiles.size <= 7) {
            ToastUtils.showShort("请上传完整相关证件！")
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
                return
            }

            val between = Period.between(LocalDate.parse(tv_act_ppt_cz_rzxx_qzrq.text.toString()), LocalDate.parse(tv_act_ppt_cz_rzxx_tzrq.text.toString()))

            uploadEntity.czr = tv_act_ppt_cz_rzxx_czr.text.toString()
            uploadEntity.czmj = tv_act_ppt_cz_rzxx_czmj.text.toString()
            uploadEntity.zlqx = between.years.toString() + "年" + between.months.toString() + "月" + between.days.toString() + "天"
            uploadEntity.zffs = tv_act_ppt_cz_rzxx_zffs.text.toString()
            uploadEntity.zlje = tv_act_ppt_cz_rzxx_zlje.text.toString()
            uploadEntity.bz = tv_act_ppt_cz_rzxx_remark.text.toString()
            uploadEntity.startDate = tv_act_ppt_cz_rzxx_qzrq.text.toString()
            uploadEntity.endDate = tv_act_ppt_cz_rzxx_tzrq.text.toString()
            if (type == 1){
                mPresenter.getPropertyAddLeaseInfo(uploadEntity)
            }else{
                mPresenter.getPropertyRenewalLeaseInfo(uploadEntity)
            }
        }

    }

    fun addRoomPicPopup() {
        rzxxUploadPopup = CommenPop.getNormalPopu(this, R.layout.pop_upload_status, ll_act_ppt_rzxx)
        val contentView = rzxxUploadPopup!!.contentView
        val camera = contentView.findViewById<LinearLayout>(R.id.pop_upload_status_camera)
        val album = contentView.findViewById<LinearLayout>(R.id.pop_upload_status_album)
        val close = contentView.findViewById<TextView>(R.id.pop_wy_cz_rzry_close)
        CommenPop.backgroundAlpha(0.5f, this)
        rzxxUploadPopup!!.showAtLocation(ll_act_ppt_rzxx, Gravity.CENTER, 0, 0)
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

    fun initDeletePopup(position: Int) {
        rzxxUploadPopup = CommenPop.getNormalPopu(this, R.layout.pop_delete, ll_act_ppt_rzxx)
        val contentView = rzxxUploadPopup!!.contentView
        val confirm = contentView.findViewById<TextView>(R.id.pop_delete_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_delete_close)
        CommenPop.backgroundAlpha(0.5f, this)
        rzxxUploadPopup!!.showAtLocation(ll_act_ppt_rzxx, Gravity.CENTER, 0, 0)
        confirm.setOnClickListener {

            val indexOf = uploadEntity.housingFiles.indexOf(zjStrList.get(position))
            mPresenter.getZlFileDetel(uploadEntity.housingFiles.get(indexOf).id,position)

            rzxxUploadPopup!!.dismiss()
        }
        close.setOnClickListener {
            rzxxUploadPopup!!.dismiss()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        val selectedImage = data!!.getData() //获取系统返回的照片的Uri
                        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                        val cursor = this@PropertyRzxxActivity.getContentResolver().query(selectedImage, //getContentResolver
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
            }
            2 -> {
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
            }
        }
    }

    //上传院落合同等
    private fun uploadHtFile(type: Int, file2: File) {
        val decodeFile = BitmapFactory.decodeFile(file2.path)
        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(this, decodeFile, "", 42, Color.WHITE, 10, 10)

        val file = BitMapToFile.getFile(drawTextToRightBottom)
        val request = OkGo.post<BaseRespose<HousingFile>>(ApiConstants.PROPERTY_UPLOADFILE)
                .isMultipart(true)
//        request.params("pid", AppCache.getInstance().getProid())
        /* if (isXh){//xh
 //            request.params("gid", id)
         }*/
        request.params("type", type)
        request.params("isLease", 1)//file  file.name
        request.params("file", file)//file  file.name
        request.execute(object : BaseNet<BaseRespose<HousingFile>>() {
            //BaseRespose<HousingFile>
            override fun onStart(request: Request<BaseRespose<HousingFile>, out Request<Any, Request<*, *>>>?) {//HousingFile
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@PropertyRzxxActivity)
            }

            override fun onSuccess(response: Response<BaseRespose<HousingFile>>) {
//                super.onSuccess(response)
                val body = response.body()

                if (body.getCode() == 0) {
                    if (body.data != null) {
                        uploadEntity.housingFiles.add(body.data)
                        zjStrList.set(adapterPosition,body.data)
                        xgzjAdapter!!.setNewData(zjStrList)
                        xgzjAdapter!!.notifyDataSetChanged()
                    }

                } else {
                    ToastUtils.showShort(file.name + "上传失败")
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
        val file = File(AppMenus.getInstance().cardPath + "BMS/map/pic/1" + fileName)
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

    override fun returnPropertyAddLeaseInfo(msg: String) {
        ToastUtils.showShort(msg)
        /*val intent = Intent(this, PropertyActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)*/

        AppManager.getAppManager().returnToActivity(PropertyActivity::class.java)
    }
    override fun returnPropertyRenewalLeaseInfo(msg: String) {
        ToastUtils.showShort(msg)
        /*val intent = Intent(this, PropertyActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)*/

        AppManager.getAppManager().returnToActivity(PropertyActivity::class.java)
    }

    override fun returnPropertyWithdrawalLeaseInfo(msg: String) {

    }

    override fun returnWyPoint(entity: PropertyDto) {
    }

    override fun returnZlFileDetel(message: String, position: Int) {
        ToastUtils.showShort(message)
        val indexOf = uploadEntity.housingFiles.indexOf(zjStrList.get(position))
        uploadEntity.housingFiles.set(indexOf,HousingFile(uploadEntity.housingFiles.get(indexOf).type,""))

        zjStrList.get(position).url = ""
        if (xgzjAdapter != null) {
            xgzjAdapter!!.setNewData(zjStrList)
            xgzjAdapter!!.notifyDataSetChanged()
        }
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
