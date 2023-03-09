package com.jymj.zhglxt.zjd.activity.zjdgl

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.zxing.integration.android.IntentIntegrator
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.login.activity.LoginActivity
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.zjd.adapter.WyTzRoomFicAdapter
import com.jymj.zhglxt.zjd.bean.wy.*
import com.jymj.zhglxt.zjd.contract.PropertyRzxxContract
import com.jymj.zhglxt.zjd.presenter.PropertyRzxxPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppManager
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_property_tz.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.collections.ArrayList

/**
 * 退租activity
 */
class PropertyTzActivity : BaseActivity<PropertyRzxxPresenter, BaseModel>(), PropertyRzxxContract.View {
    override fun returnZlFileDetel(message: String, position: Int) {

    }


    private var xsqPicAdapter: WyTzRoomFicAdapter? = null
    var xsqList = ArrayList<HousingFile>()
    private var selectedPhotos = ArrayList<String>()
    private var uploadEntity = WithdrawalLeaseDto() //返租信息修改或添加

    override fun getLayoutId(): Int {
        return R.layout.activity_property_tz
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        val string = SimpleDateFormat("yyyy-MM-dd").format(Date()).toString()
        val yczEntity = intent.getSerializableExtra("yczEntity") as LeaseDto
        uploadEntity.roomId = yczEntity.roomId
        uploadEntity.fjbh = yczEntity.roomNo
        tv_act_ppt_cz_tz_fjbh.text = yczEntity.roomNo
        tv_act_ppt_cz_tz_name.text = yczEntity.czr
        tv_act_ppt_cz_tz_zlje.text = yczEntity.zlje
        val between = Period.between(LocalDate.parse(string), LocalDate.parse(yczEntity.endDate))
        tv_act_ppt_cz_tz_syhtts.text = between.years.toString() + "年" + between.months.toString() + "月" + between.days.toString() + "天"
        tv_act_ppt_cz_tz_time.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this, tv_act_ppt_cz_tz_time.text.toString(), object : TimePickerUtil.OnTimePickerLister {
                override fun onClick(data: String?) {
                    tv_act_ppt_cz_tz_time.text = data
                }
            })
        }

        rlv_at_property_cz_tz_yspic.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        xsqPicAdapter = WyTzRoomFicAdapter(this, xsqList)
        rlv_at_property_cz_tz_yspic.adapter = xsqPicAdapter


        xsqPicAdapter!!.setOnClickLister(object : WyTzRoomFicAdapter.OnQyglFileLinear {

            override fun onClick(enterpriseFileEntity: HousingFile?, position: Int) {
                if (enterpriseFileEntity == null) {
                    if (SingleOnClickUtil1.isFastClick()) {
                        PhotoPicker.builder()
                                .setPhotoCount(PhotoAdapter.MAX)
                                .setShowCamera(true)
                                .setPreviewEnabled(false)
                                .setSelected(selectedPhotos)
//                            .start(activity!!)
                                .start(this@PropertyTzActivity!!, 233)
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
                            .start(this@PropertyTzActivity!!)
                }
            }

            override fun onDeleteClick(enterpriseFileEntity: HousingFile?, position: Int) {
                // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                var builder = AlertDialog.Builder(this@PropertyTzActivity)
                // 设置Title的图标
                builder.setIcon(R.mipmap.ic_launcher)
                // 设置Title的内容
                builder.setTitle("弹出警告框")
                // 设置Content来显示一个信息
                builder.setMessage("确定删除吗？")
                // 设置一个PositiveButton
                builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        uploadEntity.tzysImgList.removeAt(position)
                        xsqList.removeAt(position)
                        xsqPicAdapter!!.setmList(xsqList)
                        xsqPicAdapter!!.notifyDataSetChanged()
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

        iv_at_property_cz_tz_finish.setOnClickListener {
            finish()
        }

        bt_act_ppt_cz_tz_upload.setOnClickListener {
            if (tv_act_ppt_cz_tz_time.text.toString().equals("")) {
                ToastUtils.showShort("请选择退房时间")
                return@setOnClickListener
            }
            if (tv_act_ppt_cz_tz_reason.text.toString().equals("")) {
                ToastUtils.showShort("请输入退房原因")
                return@setOnClickListener
            }
            if (uploadEntity.tzysImgList.size==0){
                ToastUtils.showShort("请上传退租验收照片")
                return@setOnClickListener
            }
            uploadEntity.tfrq = tv_act_ppt_cz_tz_time.text.toString()
            uploadEntity.zlje = tv_act_ppt_cz_tz_zlje.text.toString()
            uploadEntity.syts = tv_act_ppt_cz_tz_syhtts.text.toString()
            uploadEntity.tfyy = tv_act_ppt_cz_tz_reason.text.toString()
            mPresenter.getPropertyWithdrawalLeaseInfo(uploadEntity)
        }
    }

    override fun initDatas() {
    }

    override fun returnWyPoint(entity: PropertyDto) {
    }

    override fun returnPropertyAddLeaseInfo(msg: String) {
    }

    override fun returnPropertyRenewalLeaseInfo(msg: String) {
    }

    override fun returnPropertyWithdrawalLeaseInfo(msg: String) {
        ToastUtils.showShort(msg)
        /*val intentmain = Intent(this, PropertyActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intentmain)*/
        AppManager.getAppManager().returnToActivity(PropertyActivity::class.java)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {//&&requestCode != 66
            if (requestCode == 233) {
                var photos: ArrayList<String>? = null
//                selectedPhotos.clear()

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    for (i in photos) {
                        val file = File(i)
                        val name = file.name
                        val bitmap = IOHelper.loadBitmap(file.path, true)
                        val file1: File = compressImages(bitmap, name)
                        uploadHtFile(-1, file1)
                    }
                }
            }
        }
    }

    //上传房屋图片
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
//        request.params("isLease", isLease)//file  file.name
        request.params("file", file)//file  file.name
        request.execute(object : BaseNet<BaseRespose<HousingFile>>() {
            //BaseRespose<HousingFile>
            override fun onStart(request: Request<BaseRespose<HousingFile>, out Request<Any, Request<*, *>>>?) {//HousingFile
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@PropertyTzActivity)
            }

            override fun onSuccess(response: Response<BaseRespose<HousingFile>>) {
//                super.onSuccess(response)
                val body = response.body()

                if (body.getCode() == 0) {
                    if (body.data != null) {
                        uploadEntity.tzysImgList.add(body.data)
                        xsqList.add(body.data)
                        xsqPicAdapter!!.setmList(xsqList)
                        xsqPicAdapter!!.notifyDataSetChanged()
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
}
