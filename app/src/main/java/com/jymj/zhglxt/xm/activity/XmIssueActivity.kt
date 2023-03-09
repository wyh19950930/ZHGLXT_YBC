package com.jymj.zhglxt.xm.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.*
import com.amap.api.maps2d.model.Marker
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener
import com.jymj.zhglxt.rjhj.adapter.HbAdapter
import com.jymj.zhglxt.rjhj.adapter.VideoAdapter
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.bean.enums.*
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.ColorBar
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.bean.BcProjectFile
import com.jymj.zhglxt.xm.bean.BcProjectLx
import com.jymj.zhglxt.xm.bean.CjVO
import com.jymj.zhglxt.xm.contract.XmIssueContract
import com.jymj.zhglxt.xm.helper.MyItemTouchHelper
import com.jymj.zhglxt.xm.presenter.XmIssuePresenter
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter
import com.jymj.zhglxt.zjd.bean.SxsxBean
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean
import com.jymj.zhglxt.zjd.contract.AddUserContract
import com.jymj.zhglxt.zjd.presenter.AddUserPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_tjfx.*
import kotlinx.android.synthetic.main.activity_xm_issue.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_bcqh.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*
import java.lang.Exception
import java.math.BigDecimal
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class XmIssueActivity : BaseActivity<XmIssuePresenter, XmIssueContract.Model>(), XmIssueContract.View {

    var tcList1 = ArrayList<String>()//选中的标签
    var selectedPhotos = ArrayList<String>()//  好像没啥大用
    var selectedPhotos1 = ArrayList<String>()//   好像没啥大用
    var photoAdapter: PhotoAdapter? = null//项目图片的上传图片
    var photoAdapter1: PhotoAdapter? = null//项目介绍的上传图片
    val notesFileList = ArrayList<BcProjectFile>()//项目图片的数据源
    val notesFileList1 = ArrayList<BcProjectFile>()//项目介绍的数据源
    var schxtp: BaseQuickAdapter<BcProjectFile, BaseViewHolder>? = null//项目图片的适配器
    var schxtp1: BaseQuickAdapter<BcProjectFile, BaseViewHolder>? = null//项目介绍的适配器
    var mAdapterxl: TagBaseAdapter? = null
    var bcProjectEntity = BcProjectEntity()//详情实体
    var point = ""//项目的坐标
    var xzq = ""//区
    var zhen = ""//镇
    var cun = ""//村 xzqmc
    var code = ""//村code
//    var type = 1
    var xmType = 0//项目类型
    var arrayList1 = ArrayList<JsjbBean>()//标签数据源
    var typeList = ArrayList<String>()//项目类型数据源

    var maxPhoto = 0//项目图片最大排名
    var maxPhoto1 = 0//项目介绍最大排名
    var mItemTouchHelper: ItemTouchHelper? = null
    var myItemTouchHelper: MyItemTouchHelper? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_xm_issue
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }
    override fun initViews() {
        //AppCache.getInstance().xzCenter
        iv_xm_issue_back.setOnClickListener {
            finish()
        }

        et_act_xm_issue_content.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                et_act_xm_issue_count.setText("${et_act_xm_issue_content.text.toString().length}/500")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        ll_act_xm_issue_tjwz.setOnClickListener {
            val intent = Intent(this, SearchDzActivity::class.java)
            startActivity(intent)
//            ToastUtils.showShort("添加位置")
        }
        iv_xm_issue_sure.setOnClickListener {
            val xmName = et_act_xm_issue_xmname.text.toString()
            val ydgm = et_act_xm_issue_ydgm.text.toString()
            val jzgm = et_act_xm_issue_jzgm.text.toString()
            val cdmj = et_act_xm_issue_cdmj.text.toString()
            val tzgmMin = et_act_xm_issue_tzgmmin.text.toString()
            val tzgmMax = et_act_xm_issue_tzgmmax.text.toString()
            if (notesFileList.size==0){
                ToastUtils.showShort("请上传项目图片")
            }else if (notesFileList1.size==0){
                ToastUtils.showShort("请上传项目介绍")
            }else if (xmType==0){
                ToastUtils.showShort("请选择项目类型")
            }else if (xmName.equals("")){
                ToastUtils.showShort("请输入项目名称")
            }else if (et_act_xm_issue_title.text.toString().equals("")){
                ToastUtils.showShort("请输入标题")
            }else if (tcList1.size==0){
                ToastUtils.showShort("请选择标签")
            }else if (point.equals("")){
                ToastUtils.showShort("请选择地址")
            }else{
                //et_act_xm_issue_xmname       ydgm jzgm cdmj tzgmMin tzgmMax
                val arrayList = ArrayList<BcProjectLx>()
                for (i in tcList1){
                    val bcProjectLx = BcProjectLx()
                    bcProjectLx.lx = i//i
                    for (f in arrayList1){
                        if (f.label.equals(i)){
                            bcProjectLx.color = f.color//i
                            break
                        }
                    }
                    arrayList.add(bcProjectLx)
                }
                bcProjectEntity.projectLxList = arrayList
                bcProjectEntity.xzq = xzq
                bcProjectEntity.zhen = zhen
                bcProjectEntity.xzqmc = cun
                bcProjectEntity.code = code
                bcProjectEntity.location = point
                try {
                    bcProjectEntity.ydgm = BigDecimal(ydgm)
                }catch (e:Exception){
                    bcProjectEntity.ydgm = BigDecimal.ZERO
                }
                try {
                    bcProjectEntity.jzgm = BigDecimal(jzgm)
                }catch (e:Exception){
                    bcProjectEntity.jzgm = BigDecimal.ZERO
                }
                try {
                    bcProjectEntity.cdmj = BigDecimal(cdmj)
                }catch (e:Exception){
                    bcProjectEntity.cdmj = BigDecimal.ZERO
                }
                try {
                    bcProjectEntity.tzgm1 = BigDecimal(tzgmMin)
                }catch (e:Exception){
                    bcProjectEntity.tzgm1 = BigDecimal.ZERO
                }
                try {
                    bcProjectEntity.tzgm2 = BigDecimal(tzgmMax)
                }catch (e:Exception){
                    bcProjectEntity.tzgm2 = BigDecimal.ZERO
                }

                bcProjectEntity.content = et_act_xm_issue_title.text.toString()
                bcProjectEntity.title = xmName
                bcProjectEntity.xmlx = xmType
                for (i in notesFileList.indices){
                    val get = notesFileList.get(i)
                    get.sorting = i+1
                }
                for (i in notesFileList1.indices){
                    val get = notesFileList1.get(i)
                    get.sorting = i+1
                }
                bcProjectEntity.projectFiles.clear()
                bcProjectEntity.projectFiles.addAll(notesFileList)
                bcProjectEntity.projectFiles.addAll(notesFileList1)
//                bcProjectEntity.projectFiles1=notesFileList1
                mPresenter.getAddXmIssue(bcProjectEntity)
            }


        }
        typeList.add("游")
        typeList.add("娱")
        typeList.add("居")
        typeList.add("食")
        ll_act_xm_issue_xzlx.setOnClickListener {//选择类型

            val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
                //                        setCheck(quList,options1)
                tv_act_xm_issue_xmtype.setText(typeList.get(options1))
                xmType = typeList.indexOf(typeList.get(options1))+1

//                        getZhenList(xzqList,quList.get(options1).code)
            })
                    .isDialog(true)
                    .isAlphaGradient(true)
                    .build<String>()
            pvOptions.setSelectOptions(typeList.indexOf(tv_act_xm_issue_xmtype.text.toString()))
            pvOptions.setPicker(typeList)
            pvOptions.setTitleText("项目类型")
            pvOptions.findViewById(R.id.rv_topbar).setBackgroundResource(R.drawable.bt_actiive_shi_ban_white)


            val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
            params.leftMargin = 0
            params.rightMargin = 0
            val contentContainer = pvOptions.getDialogContainerLayout()
            contentContainer.setLayoutParams(params)
            pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
            pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            pvOptions.show()
        }

    }

    override fun initDatas() {
        mPresenter.getProjectGetLabel()
        val id = intent.getLongExtra("id", -1);
        if (id!=-1L){
            mPresenter.getProject(id)
        }

        tv_act_xm_issue_bj.setOnClickListener {
            val tvActXmIssueBj = tv_act_xm_issue_bj.text.toString()
            if (tvActXmIssueBj.equals("编辑")){
                tv_act_xm_issue_bj.setText("取消")
                tcl_act_xm_issue_bq.setShowDelete(true)
                tv_act_xm_issue_add.visibility = View.VISIBLE
            }else{
                tv_act_xm_issue_bj.setText("编辑")
                tcl_act_xm_issue_bq.setShowDelete(false)
                tv_act_xm_issue_add.visibility = View.GONE
            }
        }
        tv_act_xm_issue_add.setOnClickListener {
            initPopuEnvironUp()
            CommenPop.backgroundAlpha(0.5f, this)
            envirorUpPopu!!.showAtLocation(ll_act_xm_issue, Gravity.CENTER, 0, 0)

        }
        rlv_act_xm_issue_see.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rlv_act_xm_issue_add.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        rlv_act_xm_issue_add.adapter = photoAdapter
        rlv_act_xm_issue_add.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@XmIssueActivity)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@XmIssueActivity,20)//context!!
                }
            }
        }))
        schxtp = object : BaseQuickAdapter<BcProjectFile, BaseViewHolder>(R.layout.item_teng_tui_photo, notesFileList) {
            override fun convert(helper: BaseViewHolder?, item: BcProjectFile?) {
                val view = helper!!.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                val pic: String = item!!.url//ApiConstants.BASE_URL + item!!.path
                val s1 = pic.replace("\\", "/")

                /*Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view)*/
//                        .error(R.drawable.shopping_mall_banner)
                val options = RequestOptions().transforms(RoundedCorners(30))//图片圆角为30
                Glide.with(this@XmIssueActivity).load(s1) //图片地址
                        .apply(options)
                        .into(view)

                helper!!.setText(R.id.tv_teng_photo_name, "${item.sorting}")//item.filename  item.sorting
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@XmIssueActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            var sss = "{\"requestData\":\"[" + item.id + "]\"}"//encrypt
                            OkGo.post<String> (ApiConstants.PROJECT_DELETE_FILEBYID).upJson("[" + item.id + "]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(this@XmIssueActivity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
                                        val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                        if (json.code==0){
                                            notesFileList.removeAt(position)
                                            notifyDataSetChanged()
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("确定")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("网络错误")
                                }

                            })
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
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in notesFileList){
                        val pic = i.url//ApiConstants.BASE_URL + i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(this@XmIssueActivity!!)


                }
            }
        }
        rlv_act_xm_issue_see.adapter = schxtp

        rlv_act_xm_issue_xmjssee.layoutManager = object:GridLayoutManager(this,3){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }//LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rlv_act_xm_issue_xmjsadd.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        photoAdapter1 = PhotoAdapter(this, selectedPhotos1)
        rlv_act_xm_issue_xmjsadd.adapter = photoAdapter1

        rlv_act_xm_issue_xmjsadd.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter1!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos1)
//                            .start(activity!!)
                            .start(this@XmIssueActivity,234)
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos1)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@XmIssueActivity,20)//context!!
                }
            }
        }))

        schxtp1 = object : BaseQuickAdapter<BcProjectFile, BaseViewHolder>(R.layout.item_teng_tui_photo, notesFileList1) {
            override fun convert(helper: BaseViewHolder?, item: BcProjectFile?) {
                val view = helper!!.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                val pic: String = item!!.url//ApiConstants.BASE_URL + item!!.path
                val s1 = pic.replace("\\", "/")

                /*Glide.with(mContext).load(s1)//GlideAppAppApplication.getGlideUrl(s1)
                        .into(view)*/
                //.error(R.drawable.shopping_mall_banner)
                val options = RequestOptions().transforms(RoundedCorners(30))//图片圆角为30
                Glide.with(this@XmIssueActivity).load(s1) //图片地址
                        .apply(options)
                        .into(view)
                helper!!.setText(R.id.tv_teng_photo_name, "${helper.adapterPosition+1}")//item.filename   item.sorting  item.sorting
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                    var position = helper.adapterPosition
                    // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    var builder = AlertDialog.Builder(this@XmIssueActivity)
                    // 设置Title的图标
                    builder.setIcon(R.mipmap.ic_launcher)
                    // 设置Title的内容
                    builder.setTitle("弹出警告框")
                    // 设置Content来显示一个信息
                    builder.setMessage("确定删除吗？")
                    // 设置一个PositiveButton
                    builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            var sss = "{\"requestData\":\"[" + item.id + "]\"}"//encrypt
                            OkGo.post<String> (ApiConstants.PROJECT_DELETE_FILEBYID).upJson("[" + item.id + "]").execute(object :
                                    BaseNet<String>(){
                                override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                    LoadingDialog.showDialogForLoading(this@XmIssueActivity)
                                }

                                override fun onSuccess(response: Response<String>?) {
                                    val cash = response?.body()
                                    if (cash != null) {
                                        val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                        if (json.code==0){
                                            notesFileList1.removeAt(position)
                                            notifyDataSetChanged()
                                        }else{
                                            ToastUtils.showShort(json.getMsg())
                                        }

                                    } else {
                                        ToastUtils.showShort("确定")
                                    }
                                }

                                override fun onFinish() {
                                    super.onFinish()
                                    LoadingDialog.cancelDialogForLoading()
                                }

                                override fun onError(response: Response<String>?) {
                                    super.onError(response)
                                    ToastUtils.showShort("网络错误")
                                }

                            })
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
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in notesFileList1){
                        val pic = i.url//ApiConstants.BASE_URL + i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(this@XmIssueActivity!!)


                }
            }
        }
        rlv_act_xm_issue_xmjssee.adapter = schxtp1
        //添加拖拽事件
        myItemTouchHelper = MyItemTouchHelper(this, notesFileList, schxtp)
        mItemTouchHelper = ItemTouchHelper(myItemTouchHelper!!)
        mItemTouchHelper!!.attachToRecyclerView(rlv_act_xm_issue_xmjssee)

    }

    override fun returnAddUser(message: String) {
        ToastUtils.showShort(message)
        AppCache.getInstance().isIssueXm = true
        finish()
    }
    override fun returnByPoint(message: CjVO,point: String) {

    }

    override fun returnProject(message: BcProjectEntity) {
        bcProjectEntity = message
        for (i in bcProjectEntity.projectFiles){
            if (maxPhoto<i.sorting){
                maxPhoto = i.sorting
            }
            notesFileList.add(i)
        }
        for (i in bcProjectEntity.projectFiles1){
            if (maxPhoto1<i.sorting){
                maxPhoto1 = i.sorting
            }
            notesFileList1.add(i)
        }
        schxtp?.notifyDataSetChanged()
        schxtp1?.notifyDataSetChanged()
        myItemTouchHelper!!.setPictureBeans(notesFileList1,schxtp1)
        xmType = bcProjectEntity.xmlx
        tv_act_xm_issue_xmtype.setText(typeList.get(bcProjectEntity.xmlx-1))
        et_act_xm_issue_xmname.setText(bcProjectEntity.title)
        et_act_xm_issue_title.setText(bcProjectEntity.content)
        for (i in bcProjectEntity.projectLxList){
            tcList1.add(i.lx)
        }
        for (f in arrayList1){
            if (tcList1.contains(f.text)){
                f.isCheck = true
            }
        }
        mAdapterxl?.setmList(arrayList1,tcl_act_xm_issue_bq)
        mAdapterxl?.notifyDataSetChanged()
//        tcl_act_xm_issue_bq.setAdapter(mAdapterxl)
//        mAdapterxl?.notifyDataSetChanged()
        point = bcProjectEntity.location
        xzq = bcProjectEntity.xzq
        zhen = bcProjectEntity.zhen
        cun = bcProjectEntity.xzqmc
        code = bcProjectEntity.code
        tv_act_xm_issue.setText("${xzq+zhen+cun}")
        et_act_xm_issue_ydgm.setText("${bcProjectEntity.ydgm}")
        et_act_xm_issue_jzgm.setText("${bcProjectEntity.jzgm}")
        et_act_xm_issue_cdmj.setText("${bcProjectEntity.cdmj}")
        et_act_xm_issue_tzgmmin.setText("${bcProjectEntity.tzgm1}")
        et_act_xm_issue_tzgmmax.setText("${bcProjectEntity.tzgm2}")


    }

    override fun returnProjectGetLabel(message: List<JsjbBean>) {
        arrayList1.clear()
        arrayList1.addAll(message)
//        arrayList1.add(JsjbBean(1,"历史文化型",false))
//        arrayList1.add(JsjbBean(2,"旅游型",false))
//        arrayList1.add(JsjbBean(3,"其他型A",false))
//        arrayList1.add(JsjbBean(4,"其他型B",false))
        if (mAdapterxl==null){
            mAdapterxl = TagBaseAdapter(mContext, arrayList1) //createData(mListxl, xl)
            tcl_act_xm_issue_bq.setAdapter(mAdapterxl)
            tcl_act_xm_issue_bq.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
                override fun itemClick(position1: Int) {
                    changeState(arrayList1, position1)
                    mAdapterxl!!.notifyDataSetChanged()
                }
            })
            tcl_act_xm_issue_bq.setItemDeleteListener(object:TagCloudLayout.TagItemDeleteListener{
                override fun itemDeleteClick(position: Int) {
//                ToastUtils.showShort("删除")
//                var position = helper.adapterPosition

                    val item = arrayList1.get(position)//
                    if (AppCache.getInstance().userId == item.userId||AppCache.getInstance().type==1){

                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@XmIssueActivity)
                        // 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
                        // 设置Title的内容
                        builder.setTitle("弹出警告框")
                        // 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
                        // 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                OkGo.post<String> (ApiConstants.PROJECT_DELETE_LABEL).upJson("[" + item.id + "]").execute(object :
                                        BaseNet<String>(){
                                    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                        super.onStart(request)
                                        LoadingDialog.showDialogForLoading(this@XmIssueActivity)
                                    }

                                    override fun onSuccess(response: Response<String>?) {
                                        val cash = response?.body()
                                        if (cash != null) {
                                            val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                                            if (json.code==0){
                                                tcList1.remove(item.label)
                                                /*arrayList1.removeAt(position)
                                                mAdapterxl!!.setmList(arrayList1,tcl_act_xm_issue_bq)
                                                mAdapterxl!!.notifyDataSetChanged()*/

                                                mPresenter.getProjectGetLabel()
                                            }else{
                                                ToastUtils.showShort(json.getMsg())
                                            }

                                        } else {
                                            ToastUtils.showShort("确定")
                                        }
                                    }

                                    override fun onFinish() {
                                        super.onFinish()
//                                LoadingDialog.cancelDialogForLoading()
                                    }

                                    override fun onError(response: Response<String>?) {
                                        super.onError(response)
                                        ToastUtils.showShort("网络错误")
                                    }

                                })
                            }
                        })
                        // 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        })
                        builder.show()
                    }else{
                        ToastUtils.showShort("您无删除权限删除他人添加标签")
                    }


                }
            })
        }else{
            mAdapterxl?.setmList(arrayList1,tcl_act_xm_issue_bq)
            mAdapterxl?.notifyDataSetChanged()
        }
    }

    override fun returnSaveLabel(message: String) {
        if (message.equals("添加成功")){

            mPresenter.getProjectGetLabel()
            /*arrayList1.add(jsjbBean)
            mAdapterxl?.setmList(arrayList1,tcl_act_xm_issue_bq)
            mAdapterxl?.notifyDataSetChanged()*/
        }
    }

    var envirorUpPopu: CommenPop? = null

    var jsjbBean = JsjbBean()
    private fun initPopuEnvironUp() {//RjhjPointBean

        envirorUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_xm_add_bq, supl_frag_zjdgl)
        val contentView2: View = envirorUpPopu!!.getContentView()
        jsjbBean = JsjbBean()
        var cb_act_xm_issue_bq = contentView2.findViewById<ColorBar>(R.id.cb_act_xm_issue_bq)
        var pop_xm_add_bq_name = contentView2.findViewById<EditText>(R.id.pop_xm_add_bq_name)
        var tv_pop_xm_add_bq_ys = contentView2.findViewById<TextView>(R.id.tv_pop_xm_add_bq_ys)
        var pop_xm_add_commit = contentView2.findViewById<TextView>(R.id.pop_xm_add_commit)//提交
        var pop_xm_add_close = contentView2.findViewById<TextView>(R.id.pop_xm_add_close)//取消
        cb_act_xm_issue_bq.setOnColorChangerListener(
                object: ColorBar.ColorChangeListener {
                    override fun colorChange(color: Int) {
                        var hex = Integer.toHexString(color)
                        val replaceFirst = hex.replaceFirst("ff", "#")
//                        ToastUtils.showShort("${replaceFirst}")
                        tv_pop_xm_add_bq_ys.setBackgroundColor(Color.parseColor(replaceFirst.replace("#","#30")))
                        tv_pop_xm_add_bq_ys.setTextColor(Color.parseColor(replaceFirst))
                        jsjbBean.color = replaceFirst
                    }
                }
        )
        pop_xm_add_commit.setOnClickListener {
            val name = pop_xm_add_bq_name.text.toString()
            var isAdd = true
            for (i in arrayList1){
                if (name.equals(i.label)){
                    isAdd = false
                    break
                }
            }
            if (name.equals("")){
                ToastUtils.showShort("请输入标签")
            }else if (!isAdd){
                ToastUtils.showShort("请勿添加重复标签")
            }else{
                jsjbBean.label = name
                mPresenter.getSaveLabel(jsjbBean)
                envirorUpPopu!!.dismiss()
            }
        }
        pop_xm_add_close.setOnClickListener {
            envirorUpPopu!!.dismiss()
        }



    }


//设置屏幕背景透明效果

    private fun setBackgroundAlpha(activity: Activity, alpha: Float) {

        val lp = activity.window.attributes

        lp.alpha = alpha

        activity.window.attributes = lp

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

    override fun onResume() {
        super.onResume()
        val cjVO = AppMenus.getInstance().cjVO
        if (cjVO!=null){
            tv_act_xm_issue.setText("${cjVO.xzq+cjVO.zhen+cjVO.xzqmc}")
            point = AppMenus.getInstance().cjVO.point
            xzq = AppMenus.getInstance().cjVO.xzq
            zhen = AppMenus.getInstance().cjVO.zhen
            cun = AppMenus.getInstance().cjVO.xzqmc
            code = AppMenus.getInstance().cjVO.code
            AppMenus.getInstance().cjVO = null
        }

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
                    for (i in photos.indices){
                        val file = File(photos.get(i))
                        val name = file.name

                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        val file1: File = compressImages(bitmap, name)
                        upFile1(file1,1,(maxPhoto+i+1));//file1
                    }
                    maxPhoto = maxPhoto+photos.size
//                    selectedPhotos.addAll(photos)
                }
//                if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
            }else if (requestCode==234){
                var photos: ArrayList<String>? = null
                selectedPhotos1.clear()

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    for (i in photos.indices){
                        val file = File(photos.get(i))
                        val name = file.name

                        val bitmap = IOHelper.loadBitmap(file.path,true)
                        val file1: File = compressImages(bitmap, name)
                        upFile1(file1,2,(maxPhoto1+i+1));//file1
                    }
                    maxPhoto1 = maxPhoto1+photos.size
//                    selectedPhotos.addAll(photos)
                }
//                if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
            }
        }
    }

    var comparator = object: Comparator<BcProjectFile> {
        override fun compare(o1: BcProjectFile?, o2: BcProjectFile?): Int {
            //排序规则，按照价格由大到小顺序排列("<"),按照价格由小到大顺序排列(">"),
            if(o1!!.sorting > o2!!.sorting)
                return 1;
            else {
                return -1;
            }
        }
    };

    private fun upFile1( file2: File,type: Int,sorting: Int) {

        val request = OkGo.post<BaseRespose<BcProjectFile>>(ApiConstants.PROJECT_UPLOAD_FILE)
                .isMultipart(true)
        /*request.params("type", 1)
        request.params("filetype", 1)
        request.params("msid", -1)*/
        request.params("type", type)//file  file2.name
        request.params("sorting", sorting)//file  file2.name
        request.params("file", file2)//file  file2.name
        request.execute(object : BaseNet<BaseRespose<BcProjectFile>>() {//BaseRespose<PjEnviorFileEntity>
            override fun onStart(request: Request<BaseRespose<BcProjectFile>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@XmIssueActivity)
            }
            override fun onSuccess(response: Response<BaseRespose<BcProjectFile>>) {
//                super.onSuccess(response)
                val body = response.body()
//                ToastUtils.showShort(body)
//                val json: BaseRespose <PjEnviorFileEntity> = Gson().fromJson(body, object : TypeToken<BaseRespose<PjEnviorFileEntity>?>() {}.type)
                if (body.getCode()==0){

                    if (type==1){
                        notesFileList.add(body.data)
                        //这里就会自动根据规则进行排序
                        Collections.sort(notesFileList, comparator);
                        schxtp!!.setNewData(notesFileList)
                        schxtp!!.notifyDataSetChanged()
                    }else{
                        notesFileList1.add(body.data)
                        //这里就会自动根据规则进行排序
                        Collections.sort(notesFileList1, comparator);
                        schxtp1!!.setNewData(notesFileList1)
                        schxtp1!!.notifyDataSetChanged()
                        myItemTouchHelper!!.setPictureBeans(notesFileList1,schxtp1)

                    }
                }else{
                    ToastUtils.showShort(file2.name+"上传失败")
                }
            }
            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }
            override fun onError(response: Response<BaseRespose<BcProjectFile>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
//                ToastUtils.showShort(response.exception.message)
//                ToastUtils.showShort("上传失败")
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
        while (baos.toByteArray().size / 1024 > 1536) { //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset() //重置baos即清空baos
            options -= 5 //每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) //这里压缩options%，把压缩后的数据存放到baos中
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

    //    选择状态改变
    private fun changeState(list: List<JsjbBean>, position: Int) {
        if (list[position].isCheck()) {
            list[position].setCheck(false)
            tcList1.remove(list[position].text)
        } else {
            tcList1.add(list[position].text)
            list[position].setCheck(true)
        }
    }
}
