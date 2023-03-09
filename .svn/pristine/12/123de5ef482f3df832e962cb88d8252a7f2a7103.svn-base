package com.jymj.zhglxt.ldrkgl.home.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.SendMessageContract
import com.jymj.zhglxt.login.model.SendMessageModel
import com.jymj.zhglxt.login.presenter.SendMessagePresenter
import com.jymj.zhglxt.ldrkgl.personal.bean.FileUploadEntity
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity
import com.jymj.zhglxt.util.GetFileUtil
import com.jymj.zhglxt.util.IOHelper
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_send_message.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import kotlin.collections.ArrayList

class SendMessageActivity : BaseActivity<SendMessagePresenter, SendMessageModel>(), SendMessageContract.View {

    private var selectedPhotos = ArrayList<String>()
    private var photoAdapter:PhotoAdapter? = null
    private var userList = ArrayList<User>()
    private var code = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_send_message
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        code = intent.getStringExtra("code")
        iv_act_send_message_fbxx.setOnClickListener {
            finish()
        }
        et_act_send_mess_srxq.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tv_act_send_mess_zs.setText("${et_act_send_mess_srxq.text.length}/500")
            }
        })
        tv_act_send_mess_issue.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                val flowMassageEntity = FlowMassageEntity()
                val title = et_act_send_mess_title.text.toString()
                val srxq = et_act_send_mess_srxq.text.toString()
                //sender 发送消息
                flowMassageEntity.dispose = 0
                flowMassageEntity.title = title
                flowMassageEntity.content = srxq
                flowMassageEntity.code = code

                if (title.equals("")){
                    ToastUtils.showShort("请输入标题内容")
                }else if (srxq.equals("")){
                    ToastUtils.showShort("请输入详细内容")
                }/*else if (sp_act_send_mess_jsr.selectedItemPosition == 0){
                    ToastUtils.showShort("请选择接收人")
                }*/else{
//                    flowMassageEntity.recipient = userList.get(sp_act_send_mess_jsr.selectedItemPosition-1).userId
                    if (selectedPhotos.size>0){//上传完图片后调用发布接口
                        var idList = ArrayList<Long>()
                        for (i in selectedPhotos){
                            val file = File(i)
                            val name = file.name
                            val bitmap = IOHelper.loadBitmap(file.path,true)
                            val file1: File = compressImages(bitmap, name)
                            upFile1(file1,idList,flowMassageEntity);
                        }
                    }else{
                        //直接调用发布接口
                        mPresenter.getAddMessage(flowMassageEntity)
                    }
                }
            }
        }



    }

    override fun initDatas() {
//        mPresenter.getHqlgyList()//获取；流管员的列表
        //展示图片
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        rlv_act_send_message_image.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlv_act_send_message_image.adapter = photoAdapter
        rlv_act_send_message_image.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@SendMessageActivity!!,233)//, this@RJHJFragment
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@SendMessageActivity,20)//context!!
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
    //上传文件(图片)
    private fun upFile1( file2: File,idList:ArrayList<Long>,flowMassageEntity: FlowMassageEntity) {

        val request = OkGo.post<BaseRespose<FileUploadEntity>>(ApiConstants.MESSAGE_UPLOAD_FILE)
                .isMultipart(true)//file2.name
        request.params("file", file2)//idList
        request.execute(object : BaseNet<BaseRespose<FileUploadEntity>>() {//BaseRespose<PjEnviorFileEntity>
            override fun onStart(request: Request<BaseRespose<FileUploadEntity>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@SendMessageActivity)
            }
            override fun onSuccess(response: Response<BaseRespose<FileUploadEntity>>) {
                val body = response.body()
                if (body.getCode()==0){
                    if (body.data!=null){
                        idList.add(body.data.fileId)
                        if (idList.size==selectedPhotos.size){//上传完毕调用发布
                            flowMassageEntity.ids = idList.toTypedArray()
                            mPresenter.getAddMessage(flowMassageEntity)
                        }
                    }else{
                        ToastUtils.showShort(file2.name+"上传失败")
                    }
                }else{
                    ToastUtils.showShort(file2.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<FileUploadEntity>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("上传失败")
            }
        })
    }

    override fun returnEwmsmZjdBd(msg: YlFolwEntity) {

    }

    override fun returnAddMessage(msg: String) {
        ToastUtils.showShort("发布成功")
        finish()
    }
    override fun returnHqlgyList(msg: List<User>) {//recipient
        userList.addAll(msg)
        var stringList = ArrayList<String?>()
        stringList.add("未选中")
        for (i in msg){
            stringList.add(i.username)
        }
        val wtdwlbAdapter = ArrayAdapter(this ,R.layout.item_wtlx,stringList)//android.R.layout.simple_spinner_item
//                            android.R.layout.simple_spinner_item
        wtdwlbAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        sp_act_send_mess_jsr.adapter = wtdwlbAdapter
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
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
}
