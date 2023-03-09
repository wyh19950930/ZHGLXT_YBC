package com.jymj.zhglxt.rjhj.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.login.model.RjhjDwckModel
import com.jymj.zhglxt.rjhj.contract.RjhjDwckContract
import com.jymj.zhglxt.rjhj.presenter.RjhjDwckPresenter
import com.jymj.zhglxt.util.GetFileUtil
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_rjhj_dwck.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*

/*
* 点位查看activity
*/
class RjhjDwckActivity : BaseActivity<RjhjDwckPresenter, RjhjDwckModel>(), RjhjDwckContract.View {
    private var selectedPhotos = ArrayList<String>()
    private var photoAdapter:PhotoAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_rjhj_dwck
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        tv_act_rjhj_dwck_back.setOnClickListener {
            finish()
        }

        //公厕管护隐藏选项
        if (tv_act_rjhj_dwck_name.text.toString().equals("公厕管护")){
            ll_act_rjhj_dwck_gczt.visibility = View.GONE
            view_act_rjhj_dwck_gczt.visibility = View.GONE
            ll_act_rjhj_dwck_gcsx.visibility = View.GONE
            view_act_rjhj_dwck_gcsx.visibility = View.GONE
        }

        bt_act_rjhj_dwck_confirm.setOnClickListener {
            var intent = Intent(this,RjhjHbDetailsActivity::class.java)
            startActivity(intent)
        }


    }

    override fun initDatas() {

        //展示图片
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        rlv_act_rjhj_dwck_upload_image.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlv_act_rjhj_dwck_upload_image.adapter = photoAdapter
        rlv_act_rjhj_dwck_upload_image.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@RjhjDwckActivity!!,233)//, this@RJHJFragment
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@RjhjDwckActivity,20)//context!!
                }
            }
        }))


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("requestCode",""+requestCode)
        if (resultCode == Activity.RESULT_OK) {//&&requestCode != 66
            if (requestCode==233){
                var photos: ArrayList<String>? = null
                selectedPhotos.clear()

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    /* for (i in photos){
                         val file = File(i)
                         val name = file.name
                         val bitmap = IOHelper.loadBitmap(file.path,true)
                         val file1: File = compressImages(bitmap, name)
                         upFile1(file1);
                     }*/
                    selectedPhotos.addAll(photos)
                }
                if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
            }else if (requestCode==20){
                var photoLists = data!!.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (photoLists != null ) {//&& !photoLists.isEmpty()
                    selectedPhotos = photoLists
                    /*for (i in photoLists){
                        selectedPhotos.remove(i)
                    }*/
                    if (photoAdapter!=null){
                        photoAdapter!!.setNewData(selectedPhotos)
//                            photoAdapter!!.notifyDataSetChanged()
                    }
                }
//                    mAddPicture.setPaths(mImagePaths);
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
    }

    override fun returnWclCount(msg: Int) {

    }

}
