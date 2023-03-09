package com.jymj.zhglxt.rjhj.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.Gravity
import android.view.View
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.login.model.RjhjHbDetailsModel
import com.jymj.zhglxt.rjhj.adapter.VideoAdapter
import com.jymj.zhglxt.rjhj.contract.RjhjHbDetailsContract
import com.jymj.zhglxt.rjhj.presenter.RjhjHbDetailsPresenter
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.setsuna.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_hbjcdetail.*
import kotlinx.android.synthetic.main.activity_rjhj_hb_details.*
import me.iwf.photopicker.PhotoPicker
import java.io.*
import kotlin.collections.ArrayList

class RjhjHbDetailsActivity : BaseActivity<RjhjHbDetailsPresenter, RjhjHbDetailsModel>(), RjhjHbDetailsContract.View {
    private var selectedPhotos = ArrayList<String>()
    private var photoAdapter:PhotoAdapter? = null

    private var filetype = 1
    private var selectedPhotos_new = ArrayList<String>()
    private val selectedVideos = ArrayList<String>()
    private val videoIdList = ArrayList<Int>()
    private val fileIdList = ArrayList<Int>()
    private var videoAdapter: VideoAdapter? = null
    var envirorUpPopu: CommenPop? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_rjhj_hb_details
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        isShowWidget()

        //巡查修改按钮
        bt_act_rjhj_hbdetails_update.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                filetype = 1

                CommenPop.backgroundAlpha(0.5f, this)
                envirorUpPopu!!.showAtLocation(hbjcdetail_top, Gravity.CENTER, 0, 0)
            }
        }

    }

    override fun initDatas() {
        //展示图片
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        /*rlv_act_rjhj_hbdetails_xc_upload_image.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlv_act_rjhj_hbdetails_xc_upload_image.adapter = photoAdapter
        rlv_act_rjhj_hbdetails_xc_upload_image.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@RjhjHbDetailsActivity!!,233)//, this@RJHJFragment
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@RjhjHbDetailsActivity,20)//context!!
                }
            }
        }))

*/
    }

    //显示隐藏控件操作
    fun isShowWidget(){
        ll_act_rjhj_hbdetails_xxxx.setOnClickListener {
            ll_act_rjhj_hbdetails_xxxx_message.visibility = if (ll_act_rjhj_hbdetails_xxxx_message.visibility == View.VISIBLE) View.GONE else View.VISIBLE

            if (ll_act_rjhj_hbdetails_xxxx_message.visibility == View.VISIBLE) iv_act_rjhj_hbdetails_xxxx.setBackgroundResource(R.drawable.back_down_icon) else iv_act_rjhj_hbdetails_xxxx.setBackgroundResource(R.drawable.back_right_icon)

        }
        //巡查进度按钮
        ll_act_rjhj_hbdetails_xc.setOnClickListener {
            bt_act_rjhj_hbdetails_update.visibility = View.VISIBLE
            bt_act_rjhj_hbdetails_xf.visibility = View.VISIBLE
            bt_act_rjhj_hbdetails_zg.visibility = View.GONE
            bt_act_rjhj_hbdetails_th.visibility = View.GONE
            bt_act_rjhj_hbdetails_wj.visibility = View.GONE

            inlcude_act_rjhj_hbdetails_xc.visibility = View.VISIBLE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.GONE
        }
        ll_act_rjhj_hbdetails_zg.setOnClickListener {
            bt_act_rjhj_hbdetails_update.visibility = View.GONE
            bt_act_rjhj_hbdetails_xf.visibility = View.GONE
            bt_act_rjhj_hbdetails_zg.visibility = View.VISIBLE
            bt_act_rjhj_hbdetails_th.visibility = View.GONE
            bt_act_rjhj_hbdetails_wj.visibility = View.GONE

            inlcude_act_rjhj_hbdetails_xc.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.VISIBLE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.GONE
        }
        ll_act_rjhj_hbdetails_sh.setOnClickListener {
            bt_act_rjhj_hbdetails_update.visibility = View.GONE
            bt_act_rjhj_hbdetails_xf.visibility = View.GONE
            bt_act_rjhj_hbdetails_zg.visibility = View.GONE
            bt_act_rjhj_hbdetails_th.visibility = View.VISIBLE
            bt_act_rjhj_hbdetails_wj.visibility = View.VISIBLE

            inlcude_act_rjhj_hbdetails_xc.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.VISIBLE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.GONE
        }
        ll_act_rjhj_hbdetails_xz.setOnClickListener {
            bt_act_rjhj_hbdetails_update.visibility = View.GONE
            bt_act_rjhj_hbdetails_xf.visibility = View.GONE
            bt_act_rjhj_hbdetails_zg.visibility = View.GONE
            bt_act_rjhj_hbdetails_th.visibility = View.GONE
            bt_act_rjhj_hbdetails_wj.visibility = View.GONE

            inlcude_act_rjhj_hbdetails_xc.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_zg.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_sh.visibility = View.GONE
            inlcude_act_rjhj_hbdetails_xz.visibility = View.VISIBLE
        }
    }


    override fun returnWclCount(msg: Int) {
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
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

}
