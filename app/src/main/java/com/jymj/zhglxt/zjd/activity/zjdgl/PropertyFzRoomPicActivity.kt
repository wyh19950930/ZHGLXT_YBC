package com.jymj.zhglxt.zjd.activity.zjdgl

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.util.FileUtilFjxc1
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.IOHelper
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.contract.PropertyAddFzContract
import com.jymj.zhglxt.zjd.contract.PropertyFzRoomPicContract
import com.jymj.zhglxt.zjd.presenter.PropertyFzRoomPicPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_property_fz_room_pic.*
import me.iwf.photopicker.PhotoPicker
import java.io.*

/**
 * 新增返租 - 房间修缮图片activity
 */
class PropertyFzRoomPicActivity : BaseActivity<PropertyFzRoomPicPresenter, BaseModel>(), PropertyFzRoomPicContract.View {
    var xspic_type = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_property_fz_room_pic
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_at_property_fz_room_finish.setOnClickListener {
            finish()
        }

        //修缮前
        //视角1
        iv_act_ppt_frp_xsq1_pic.setOnClickListener {
            xspic_type = 1
            selectFile(234)
        }
        iv_act_ppt_frp_xsq1_det.setOnClickListener {
            ToastUtils.showShort("删除1")
        }
    }

    override fun initDatas() {
    }

    override fun returnWwjPoint(entity: WwjEntity) {
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
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
//                        upFile1(file1);
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
//                        upFile1(File(realPathFromURI))
                        iv_act_ppt_frp_xsq1_pic.setImageBitmap(BitmapFactory.decodeStream(FileInputStream(realPathFromURI)))
                    }

                }
            }
        }
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
        val file1 = File(AppMenus.getInstance().cardPath + "jymj/tzrjhj/pic/")
        if (!file1.isDirectory) {
            file1.mkdirs()
        }
        val file = File(AppMenus.getInstance().cardPath+"jymj/tzrjhj/pic/1"+fileName)
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
