package com.jymj.zhglxt.zjd.activity.yqgl

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.jymj.zhglxt.R
import com.jymj.zhglxt.R.string.update
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.ApiConstants.*
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyEntity
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.zjd.bean.cygl.NydHtEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydHtFileEntity
import com.jymj.zhglxt.zjd.bean.cygl.NydZjEntity
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.fj.RenovatedFile
import com.jymj.zhglxt.zjd.bean.yzt.nyd.ZzynydHtFileEntity
import com.jymj.zhglxt.zjd.bean.yzt.nyd.ZzynydZjEntity
import com.jymj.zhglxt.zjd.contract.NyyqActContract
import com.jymj.zhglxt.zjd.presenter.NyyqActPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Progress
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.setsuna.common.compressorutils.FileUtil
import kotlinx.android.synthetic.main.activity_nyyq_detail.*
import me.iwf.photopicker.PhotoPreview
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.math.BigDecimal
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NyyqDetailActivity : BaseActivity<NyyqActPresenter, BaseModel>(), NyyqActContract.View{

    private var commenPop: CommenPop? = null
    var addFileAdapter: BaseQuickAdapter<String, BaseViewHolder>? = null
    private val selectedFiles = java.util.ArrayList<String>()
    var nydIdType = 0
    var updateZjxx = 0
    var cancelButton: SweetAlertDialog? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_nyyq_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        val intent = intent
        val nydId = intent.getLongExtra("nydId",0)
        nydIdType = nydId.toInt()
        mPresenter.getNyyqDetail(nydId.toInt())

        iv_at_nnyqdetail_finish.setOnClickListener {
            finish()
        }
        ll_map_zzynydxx.setOnClickListener {//农用地信息
            if (ll_map_point_nydxx1.isShown){
                iv_map_zzynydxx.setImageResource(R.drawable.ic_yxjtz)
                ll_map_point_nydxx1.visibility = View.GONE
            }else{
                iv_map_zzynydxx.setImageResource(R.drawable.ic_yxjtx)
                ll_map_point_nydxx1.visibility = View.VISIBLE
            }
        }
        ll_map_xzxx.setOnClickListener { //现状信息
            if (ll_map_point_xzxx.isShown){
                iv_map_xzxx.setImageResource(R.drawable.ic_yxjtz)
                ll_map_point_xzxx.visibility = View.GONE
            }else{
                iv_map_xzxx.setImageResource(R.drawable.ic_yxjtx)
                ll_map_point_xzxx.visibility = View.VISIBLE
            }
        }
        ll_map_point_htxx1.setOnClickListener { //合同信息
            if (ll_map_point_htxx2.isShown){
                iv_map_point_htxx.setImageResource(R.drawable.ic_yxjtz)
                ll_map_point_htxx2.visibility = View.GONE
            }else{
                iv_map_point_htxx.setImageResource(R.drawable.ic_yxjtx)
                ll_map_point_htxx2.visibility = View.VISIBLE
            }
        }
        ll_map_point_zjxx_1.setOnClickListener { //租金信息
            if (ll_map_point_zjxx2.isShown){
                iv_map_zjxx.setImageResource(R.drawable.ic_yxjtz)
                ll_map_point_zjxx2.visibility = View.GONE
            }else{
                iv_map_zjxx.setImageResource(R.drawable.ic_yxjtx)
                ll_map_point_zjxx2.visibility = View.VISIBLE
            }
        }


    }

    override fun initDatas() {
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

    //修改/添加合同信息
    fun initHtxx(zzynydHtEntity: NydHtEntity,gid:Long) {
        commenPop = CommenPop.getNormalPopu(this, R.layout.pop_htxx_update, ll_ac_nyyqdetail_top)
        CommenPop.backgroundAlpha(0.5F, this)
        val contentView = commenPop!!.contentView

        commenPop!!.showAtLocation(ll_ac_nyyqdetail_top, Gravity.CENTER, 0, 0)
        val etHtxxCzf = contentView.findViewById<EditText>(R.id.et_htxx_czf)//承租方
        val etHtxxHtzl = contentView.findViewById<EditText>(R.id.et_htxx_htzl)//合同种类
        val etHtxxTdyt = contentView.findViewById<EditText>(R.id.et_htxx_tdyt)//土地用途
        val etHtxxZlwz = contentView.findViewById<EditText>(R.id.et_htxx_zlwz)//坐落位置
        val etHtxxQdsj = contentView.findViewById<TextView>(R.id.et_htxx_qdsj)//签订时间
        val etHtxxHyqx = contentView.findViewById<TextView>(R.id.et_htxx_htqx)//合同期限 开始
        val etHtxxHtqx1 = contentView.findViewById<TextView>(R.id.et_htxx_htqx1)//合同期限 结束
        val etHtxxZq = contentView.findViewById<EditText>(R.id.et_htxx_zq)//租期(年)
        val etHtxxCzmj = contentView.findViewById<EditText>(R.id.et_htxx_czmj)//承租面积
        val etHtxxDswqsgx = contentView.findViewById<EditText>(R.id.et_htxx_dswqsgx)//地上物权属关系
        val etHtxxRemark = contentView.findViewById<EditText>(R.id.et_htxx_remark)//备注
        val popHttxBtn = contentView.findViewById<Button>(R.id.pop_htxx_btn)//备注
        val rlvHtxxUpdate = contentView.findViewById<RecyclerView>(R.id.rlv_htxx_update)//备注
        etHtxxCzf.setText(zzynydHtEntity.czf)
        etHtxxHtzl.setText(zzynydHtEntity.htzl)
        etHtxxTdyt.setText(zzynydHtEntity.tdyt)
        etHtxxZlwz.setText(zzynydHtEntity.zlwz)
        etHtxxQdsj.setText(zzynydHtEntity.qdtime)
        etHtxxHyqx.setText(zzynydHtEntity.htstar)
        etHtxxHtqx1.setText(zzynydHtEntity.htend)
        etHtxxZq.setText(zzynydHtEntity.zq)
        etHtxxCzmj.setText(zzynydHtEntity.czmj.toString())
        etHtxxDswqsgx.setText(zzynydHtEntity.dswqs)
        etHtxxRemark.setText(zzynydHtEntity.remark)
        etHtxxQdsj.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,etHtxxQdsj.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    etHtxxQdsj.setText(data)
                }
            })
        }
        etHtxxHyqx.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,etHtxxHyqx.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    etHtxxHyqx.setText(data)
                }
            })
        }
        etHtxxHtqx1.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,etHtxxHtqx1.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    etHtxxHtqx1.setText(data)
                }
            })
        }
        isContains()
        addFileAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_add1, selectedFiles) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                var file = File(item)
                val tvAddFile = helper!!.getView<TextView>(R.id.tv_add_file)
                val ivAddFile = helper!!.getView<ImageView>(R.id.iv_add_file)
                /*if (item.equals("")) {

                }else{

                }*/

                if (selectedFiles.size == helper!!.adapterPosition + 1) {
                    tvAddFile.visibility = View.VISIBLE
                    ivAddFile.visibility = View.GONE
                } else {
                    tvAddFile.visibility = View.GONE
                    ivAddFile.visibility = View.VISIBLE
                    Glide.with(applicationContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item))).into(ivAddFile)//fitCenter().
                }
                tvAddFile.setOnClickListener {
                    selectFile()
                }

            }

        }
        rlvHtxxUpdate.layoutManager = GridLayoutManager(this, 3)
        rlvHtxxUpdate.adapter = addFileAdapter
        popHttxBtn.setOnClickListener {
            if (isDateOneBigger1(etHtxxHyqx.text.toString(), etHtxxHtqx1.text.toString())) {//!  默认通过
                Toast.makeText(this, "合同期限开始时间不能大于结束时间", Toast.LENGTH_SHORT).show()
            } else {
                zzynydHtEntity.czf = etHtxxCzf.text.toString()
                zzynydHtEntity.htzl = etHtxxHtzl.text.toString()
                zzynydHtEntity.tdyt = etHtxxTdyt.text.toString()
                zzynydHtEntity.zlwz = etHtxxZlwz.text.toString()
                zzynydHtEntity.qdtime = etHtxxQdsj.text.toString()
                zzynydHtEntity.htstar = etHtxxHyqx.text.toString()
                zzynydHtEntity.htend = etHtxxHtqx1.text.toString()
                zzynydHtEntity.zq = etHtxxZq.text.toString()
                if (!etHtxxCzmj.text.toString().equals("")&&!etHtxxCzmj.text.toString().startsWith(".")){
                    zzynydHtEntity.czmj = BigDecimal(etHtxxCzmj.text.toString())
                }else{
                    ToastUtils.showShort("合同面积有问题")
                }
                zzynydHtEntity.dswqs = etHtxxDswqsgx.text.toString()
                zzynydHtEntity.remark = etHtxxRemark.text.toString()
                zzynydHtEntity.msid = gid
                if (selectedFiles.size > 0) {
                    var se1 = 0
                    selectedFiles.removeAt(selectedFiles.size - 1)
                    for (i in selectedFiles) {
                        se1++
                        val file = File(i)
                        upFile(file)
                    }
                }
                mPresenter.getNyyqDetailUploadHt(zzynydHtEntity)
            }

        }
        commenPop!!.setOnDismissListener(object : PopupWindow.OnDismissListener {
            override fun onDismiss() {
                CommenPop.backgroundAlpha(1F, this@NyyqDetailActivity)
            }
        });

    }

    //修改/添加租金信息
    fun initZjxx(zzynydHtEntity: NydZjEntity, bh: String) {
        commenPop = CommenPop.getNormalPopu(this, R.layout.pop_zjxx_update, ll_ac_nyyqdetail_top)
        CommenPop.backgroundAlpha(0.5F, this)
        val contentView = commenPop!!.contentView

        commenPop!!.showAtLocation(ll_ac_nyyqdetail_top, Gravity.CENTER, 0, 0)
        val etZjxxBh = contentView.findViewById<EditText>(R.id.et_zjxx_bh)//编号
        val etZjxxNzj = contentView.findViewById<EditText>(R.id.et_zjxx_nzj)//年租金
        val etZjxxPjyszj = contentView.findViewById<EditText>(R.id.et_zjxx_pjyszj)//平均应收租金
        val etZjxxZjze = contentView.findViewById<EditText>(R.id.et_zjxx_zjze)//租金总额
        val etZjxxFksj = contentView.findViewById<TextView>(R.id.et_htxx_fksj)//付款时间
        val etZjxxFkfs = contentView.findViewById<EditText>(R.id.et_htxx_fkfs)//付款方式
        val popZjxxBtn = contentView.findViewById<Button>(R.id.pop_zjxx_btn)
//        etZjxxBh.isEnabled = false
        etZjxxBh.setText(bh)
        etZjxxNzj.setText(zzynydHtEntity.nzj.toString())
        etZjxxPjyszj.setText(zzynydHtEntity.nyszj.toString())
        etZjxxZjze.setText(zzynydHtEntity.zjze.toString())
        etZjxxFksj.setText(zzynydHtEntity.fktime.toString())
        etZjxxFkfs.setText(zzynydHtEntity.fkfs.toString())

        etZjxxFksj.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(this,etZjxxFksj.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    etZjxxFksj.setText(data)
                }
            })
        }

        popZjxxBtn.setOnClickListener {
            zzynydHtEntity.bh = etZjxxBh.text.toString()
            zzynydHtEntity.nzj = BigDecimal(etZjxxNzj.text.toString())
            zzynydHtEntity.nyszj = BigDecimal(etZjxxPjyszj.text.toString())
            zzynydHtEntity.zjze = BigDecimal(etZjxxZjze.text.toString())
            zzynydHtEntity.fktime = etZjxxFksj.text.toString()
            zzynydHtEntity.fkfs = etZjxxFkfs.text.toString()
            zzynydHtEntity.msid = nydIdType.toLong()

            mPresenter.getNyyqDetailUploadZj(zzynydHtEntity)

        }

        commenPop!!.setOnDismissListener(object : PopupWindow.OnDismissListener {
            override fun onDismiss() {
                CommenPop.backgroundAlpha(1F, this@NyyqDetailActivity)
            }
        });

    }

    private fun isContains() {
        var count = -1
        if (selectedFiles.size != 0)
            for (i: Int in 0..selectedFiles.size - 1) {

                if (selectedFiles.get(i).equals("")) {
                    if (i != selectedFiles.size - 1) {
                        count = i
                    }
                }
            }
        /*if (selectedPhotos.size==0){
            selectedPhotos.add("")
        }*/
        if (count != -1) {
            selectedFiles.removeAt(count)
            selectedFiles.add("")
        }
        if (selectedFiles.size == 0) {
            var fileList = java.util.ArrayList<String>()
            fileList.addAll(selectedFiles)
            fileList.add("")
            selectedFiles.addAll(fileList)
        }
    }
    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return t

     */
    public fun isDateOneBigger1(str1: String, str2: String): Boolean {
        var isBigger = false
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        if (!str1.equals("")&&!str2.equals("")){
            var dt1: Date? = null
            var dt2: Date? = null
            try {
                dt1 = sdf.parse(str1)
                dt2 = sdf.parse(str2)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            if (dt1!!.getTime() > dt2!!.getTime()) {
                isBigger = false
            } else if (dt1.getTime() < dt2.getTime()) {
                isBigger = true
            }
        }

        return isBigger
    }
    /**
     * 通过手机选择文件
     */
    fun selectFile() {
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(Intent.createChooser(intent, "选择文件上传"), 345)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "请安装一个文件管理器.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun upFile( file2: File) {//翻建上传图片
        val decodeFile = BitmapFactory.decodeFile(file2.path)
//        val drawTextToRightBottom = ImageUtil.drawTextToRightBottom(context, decodeFile,  cunMing, 42, Color.WHITE, 10, 10)

//        val file = BitMapToFile.getFile(decodeFile)
        val request = OkGo.post<BaseRespose<RenovatedFile>>(NYD_UPLOADFILE)
                .isMultipart(true)
        request.params("gid", nydIdType)
        request.params("file", file2)
        request.execute(object : BaseNet<BaseRespose<RenovatedFile>>() {
            override fun onStart(request: com.lzy.okgo.request.base.Request<BaseRespose<RenovatedFile>, out com.lzy.okgo.request.base.Request<Any, com.lzy.okgo.request.base.Request<*, *>>>?) {//renovatedFileList
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@NyyqDetailActivity)
            }
            override fun onSuccess(response: com.lzy.okgo.model.Response<BaseRespose<RenovatedFile>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (body.getCode()==0){
                    Toast.makeText(this@NyyqDetailActivity, "上传成功", Toast.LENGTH_SHORT).show()
                    commenPop!!.dismiss()
                    mPresenter.getNyyqDetail(nydIdType)//重新请求点查 刷新数据
                    selectedFiles.clear()
                    isContains()

                }else{
                    ToastUtils.showShort(file2.name+"上传失败")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: com.lzy.okgo.model.Response<BaseRespose<RenovatedFile>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("上传失败")
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 345) {//345选择文件的请求码
            if (data != null) {
                if (resultCode == -1) {
                    if (data.getData() == null) {
                        return;
                    }
                    var isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
                    if (!isSdCardExist) {
                        ToastUtils.showShort("SD卡不可用,请检查");
                        return;
                    }
                    var uri = data!!.getData()
                    val realPathFromURI = FileUtilFjxc1.getPath(this, uri)
//                    val realPathFromURI = FileUtil.getPath(mContext, uri)
//                    var path1 = Path(this,uri)
//                    var file= File(URI(uri.toString()));
//                    var file=File(path);
//                    upFile(file);
                    selectedFiles.add(realPathFromURI)
                    isContains()
                    addFileAdapter!!.setNewData(selectedFiles)
                    addFileAdapter!!.notifyDataSetChanged()

//                    upFile(checkDataEntity!!, tvRemarkMeetingSummary!!, tvUpDate!!, file, ArrayList<Int>(), 1)
                    /*initPopuStepUp(checkDataEntity!!)
                    CommenPop.backgroundAlpha(0.5f, this@CheckAcceptActivity)
                    stepUpPopu?.showAtLocation(ll_act_check_accept, Gravity.CENTER, 0, 0)*/
//                    showCustomToast("文件上传成功");
                }
            }
        }
    }

    override fun returnNyyqDetail(entity: YztNyyqEntity) {
        if (entity!=null){
            tv_nydxx_cm.text = entity.xzqmc
            tv_nydxx_czr.text = entity.dwmc
            tv_nydxx_fl.text = entity.lx
            tv_nydxx_zm.text = entity.ydmj.toString()+"亩"

            rlv_map_point_xzxx.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_map_point_xzxx.adapter = object : BaseQuickAdapter<TdlyEntity, BaseViewHolder>(R.layout.item_nyyq_detail_xzxx, entity.tdlyEntities) {
                override fun convert(helper: BaseViewHolder?, item: TdlyEntity?) {
                    helper!!.setText(R.id.tv_nyyq_detail_xz_cm, item!!.xzqmc)
                            .setText(R.id.tv_nyyq_detail_xz_yjfl, item.lx)
                            .setText(R.id.tv_nyyq_detail_xz_ejfl, item.dlmc)
                            .setText(R.id.tv_nyyq_detail_xz_area, item.area.toString())

                }
            }
            //合同信息
            if (entity.nydHtEntitie!=null){
                tv_htxx_czf.text = entity.nydHtEntitie.czf
                tv_htxx_htzl.text = entity.nydHtEntitie.htzl
                tv_htxx_tdyt.text = entity.nydHtEntitie.tdyt
                tv_htxx_zlwz.text = entity.nydHtEntitie.zlwz
                tv_htxx_qdsj.text = entity.nydHtEntitie.qdtime
                tv_htxx_htqx.text = entity.nydHtEntitie.htstar + " - " + entity.nydHtEntitie.htend
                tv_htxx_zq.text = entity.nydHtEntitie.zq
                tv_htxx_czmj.text = entity.nydHtEntitie.czmj.toString()
                tv_htxx_dswqsgx.text = entity.nydHtEntitie.dswqs
                tv_htxx_remark.text = entity.nydHtEntitie.remark
            }
            //合同文件信息
            rlv_map_htwj.layoutManager = GridLayoutManager(this, 3)
            rlv_map_htwj.adapter = object : BaseQuickAdapter<NydHtFileEntity, BaseViewHolder>(R.layout.item_check_see, entity.nydHtFileEntities) {
                override fun convert(helper: BaseViewHolder?, item: NydHtFileEntity?) {
                    val image = helper!!.getView<ImageView>(R.id.iv_check_see)
                    val linear = helper!!.getView<TextView>(R.id.tv_check_name)

                    linear.setText(item!!.filename)
//                        var s1 = pic.replace("\\", "/")
                    var pic = BASE_URL + "nydHtFile/" + item?.path
                    if (isImageFile(pic)){
                        Glide.with(applicationContext).load(pic).into(image)
                    }else{
                        Glide.with(applicationContext).load(GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.filename))).into(image)
                    }

                    helper.getView<ImageView>(R.id.iv_check_delete).setOnClickListener {
                        //                            Toast.makeText(this@MainActivity, "删除" + item.id, Toast.LENGTH_SHORT).show()
                        var idList = java.util.ArrayList<Int>()
                        val add = idList.add(item.id)

                        cancelButton = SweetAlertDialog(this@NyyqDetailActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("是否确定删除?")
//                                    .setCustomView(this.mContext)
                                .setConfirmButton("确定") { sweetAlertDialog ->
                                    mPresenter.getFileDelete(item.id)
                                    sweetAlertDialog?.dismiss()
                                }
                                .setCancelButton("取消", { sweetAlertDialog ->
                                    sweetAlertDialog?.dismiss()
                                })
                        cancelButton!!.show()


                    }
                    image.setOnClickListener {
                        var pic = BASE_URL + "nydHtFile/" + item?.path
                        if (isImageFile(pic)){
                            var pathList = ArrayList<String>()
                            pathList.add(pic)
                            PhotoPreview.builder()
                                    .setPhotos(pathList)
                                    .setCurrentItem(helper.adapterPosition)
                                    .setShowDeleteButton(false)
                                    .start(this@NyyqDetailActivity)
                        }else{
                            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(pic))
                            startActivity(intent)
                        }
                    }

                }
            }

            but_htxx_update.setOnClickListener {
                initHtxx(entity.nydHtEntitie,entity.gid)

            }

            //租金信息
            rlv_map_point_zjxx.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rlv_map_point_zjxx.adapter = object : BaseQuickAdapter<NydZjEntity, BaseViewHolder>(R.layout.item_act_zjxx, entity.nydZjEntities) {
                override fun convert(helper: BaseViewHolder?, item: NydZjEntity?) {
                    helper!!.setText(R.id.tv_zjxx_bm, item!!.bh)
                            .setText(R.id.tv_zjxx_nzj, item!!.nzj.toString())
                            .setText(R.id.tv_zjxx_pjyszj, item!!.nyszj.toString())
                            .setText(R.id.tv_zjxx_zjze, item!!.zjze.toString())
                            .setText(R.id.tv_zjxx_fksj, item!!.fktime.toString())
                            .setText(R.id.tv_zjxx_fkfs, item!!.fkfs.toString())
                    helper.itemView.setOnClickListener {
                        updateZjxx = 1
                        initZjxx(item, item.bh)
                    }
                    helper.getView<TextView>(R.id.tv_zjxx_delete).setOnClickListener {
                        //删除
                        cancelButton = SweetAlertDialog(this@NyyqDetailActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("是否确定删除?")
//                                    .setCustomView(this.mContext)
                                .setConfirmButton("确定") { sweetAlertDialog ->
                                    var list = java.util.ArrayList<Int>()
                                    list.add(item.id)
                                    mPresenter.getNyyqZjDelete(item.id)
                                    sweetAlertDialog?.dismiss()
                                }
                                .setCancelButton("取消", { sweetAlertDialog ->
                                    sweetAlertDialog?.dismiss()
                                })
                        cancelButton!!.show()

                    }
                }
            }
            but_zjxx_add.setOnClickListener {
                //添加
                updateZjxx = 0
                initZjxx(NydZjEntity(), "")
            }
        }
    }
    //判断是否是图片   刷新
    fun isImageFile(filePath: String?): Boolean {
        if (filePath!=null){// webp,bmp,jpg,png,tif,gif,apng
//            val split = filePath!!.split(".")
            if (filePath.endsWith(".png")||filePath.endsWith(".jpg")||filePath.endsWith(".jpeg") || filePath.endsWith(".BMP")
                    || filePath.endsWith(".GIF")){
                return true
            }
        }
        return false
    }
    override fun returnNyyqZjDelete(msg: String) {
        ToastUtils.showShort("删除成功")
        mPresenter.getNyyqDetail(nydIdType)
    }

    override fun returnNyyqDetailUploadZj(msg: NydZjEntity) {
        ToastUtils.showShort("保存成功")
        commenPop!!.dismiss()
        mPresenter.getNyyqDetail(nydIdType)
    }

    override fun returnFileDelete(msg: String) {
        ToastUtils.showShort("删除成功")
        mPresenter.getNyyqDetail(nydIdType)
    }

    override fun returnNyyqDetailUploadHt(msg: NydHtEntity) {
        ToastUtils.showShort("保存成功")
        commenPop!!.dismiss()
        mPresenter.getNyyqDetail(nydIdType)
    }

}
