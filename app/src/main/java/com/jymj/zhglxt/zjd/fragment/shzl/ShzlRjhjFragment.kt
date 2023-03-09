package com.jymj.zhglxt.zjd.fragment.shzl


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter
import com.jymj.zhglxt.login.model.RjhjModel
import com.jymj.zhglxt.rjhj.activity.HBJCDetailActivity
import com.jymj.zhglxt.rjhj.adapter.EnvironmentalAdapter
import com.jymj.zhglxt.rjhj.adapter.RightArrowAdapter
import com.jymj.zhglxt.rjhj.adapter.VideoAdapter
import com.jymj.zhglxt.rjhj.bean.*
import com.jymj.zhglxt.rjhj.bean.enums.HjzzflEnum
import com.jymj.zhglxt.rjhj.bean.enums.HjzzsjEnum
import com.jymj.zhglxt.rjhj.contract.RjhjContract
import com.jymj.zhglxt.rjhj.presenter.RjhjPresenter
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.EnableLinearLayout
import com.jymj.zhglxt.widget.RecyclerViewNoBugLinearLayoutManager
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.zjd.bean.bcjc.BccyjgEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcrejectedEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity
import com.jymj.zhglxt.zjd.bean.bcjc.TypeBzEnum
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.lzy.okgo.utils.HttpUtils
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjd_ldrk.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_shzlrjhj.*
import kotlinx.android.synthetic.main.fragment_shzlrjhj.environmental_tab_yj
import kotlinx.android.synthetic.main.fragment_zjd_fj.*
import kotlinx.android.synthetic.main.pop_add_cyjg.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import org.json.JSONObject
import java.io.*
import java.math.BigDecimal
import java.net.URISyntaxException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 *百村监测-产业结构fragment
 */
class ShzlRjhjFragment : BaseFragment<RjhjPresenter, RjhjModel>(), RjhjContract.View {


    private var cunMing = "" //村名  用于控制图片上的水印
    private var selectedPhotos = ArrayList<String>()
    private val selectedVideos = ArrayList<String>()
    private val videoIdList = ArrayList<Int>()
    private val fileIdList = ArrayList<Int>()
    private var typeLx = 1//如果是普通问题直接下发  如果是公厕问题需要内业审核
    private var PjEnviorFileList = ArrayList<PjEnviorFileEntity>()
    private var sjfl = ""
    private var envirorUpPopu: CommenPop? = null//问题弹框
    private var tvUpDateEnviron: TextView? = null
    private var tvUploadMeetingSummary: Button? = null
    private var bt_temporary_environmental: Button? = null
    private var photoAdapter: PhotoAdapter? = null//图片九宫格
    private var schxtp: BaseQuickAdapter<PjEnviorFileEntity, BaseViewHolder>? = null//
    private var videoAdapter: VideoAdapter? = null
    private var uploadDate: String = ""
    private var isls=0//判断是否是临时添加问题 0 是临时   1 是可下发状态
    private val enviorListEntity = ArrayList<PjEnviorSupvsEntity>()//全局刷新实体
    private var isShow = 0////设置是否弹出问题弹出框  0不能进入  人居环境模块(1添加固定点位问题  2普通环境整治问题录入)  固定点位(3添加固定点位 4修改固定点位)
    private var renovatedFileList = ArrayList<RenovatedFile>()//文件列表
    private var gcImageURL1 = ""
    private var gcImageURL2 = ""
    private var dingDianUpPopu: CommenPop? = null
    private var gddwPage = 1//控制固定点位列表分页加载
    private val gddwList = ArrayList<PointRecordEntity>()//存放固定点位的集合
    private var gcImageType = 1
    private val mCustomItems = arrayOf("本地相册", "相机拍照")
    var SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().path + "/tzrjhjCamera/"// 拍照路径
    private var cameraPath: String? = null
    private val TAKE_PHOTO_REQUEST = 5
    private var hbjcList: List<PjEnviorSupvsEntity>? = null
    var linearLayoutManager: RecyclerViewNoBugLinearLayoutManager? = null
    private var environmentalAdapter1 : EnvironmentalAdapter?=null
    private var adapterFlag = 0
    private var hbjcPosition = 0
    private var isImage=2 //0 不显示图片  1显示图片  2显示点位
    private var path: String? = null
    private var lastSelect = 1
    private var page = 1
    private var limit = 20
    private var cunCode = ""
    private var hjzzsjCha = 0//问题枚举选项
    private var zdwtCha = -1//重大问题选项控制参数
    private var date =""//选择时间
    private var startDate = ""
    private var endDate = ""
    private var code ="";//镇
    private var bh = 0//编号参数
    var tabString = arrayOf("人居环境", "固定点位")
    var timePickerView: TimePickerView? = null//时间选择器
    var optionPickerView: OptionsPickerView<Any>? = null//条件选择器
    val sysXzqEntityList = ArrayList<SysXzqEntity>()
    private var pointStr = ""
    private val IMAGE_REQUEST_CODE = 1
    private var yearUpPopu: CommenPop? = null
    private var addCyjgUpPopu: CommenPop? = null
    private var rejectUpPopu: CommenPop? = null
    var sptypeList = ArrayList<String>()
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ShzlRjhjFragment {
            return ShzlRjhjFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_shzlrjhj
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        //时间选择
        ll_frag_shzlrjhj_sjtime.setOnClickListener {
            TimePickerUtil.initTimePickerView(activity,object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    tv_frag_shzlrjhj_sjtime.setText(data)
                    date = data!!
                    page = 1
                    mPresenter.getHbjcList(lastSelect,limit,page, cunCode,hjzzsjCha,date,bh,zdwtCha,1)//AppCache.getInstance().type.toString(),
                    mPresenter.getCountCyCode(cunCode,1,"",date,lastSelect,hjzzsjCha,bh,zdwtCha)
                }
            })
//            timePickerView!!.show()
        }
        ll_frag_shzlrhj_drcy.setOnClickListener {
            TdlyUtils.setTextPop(activity,"第二产业",tv_frag_shzlrhj_drcy)
        }
        ll_frag_shzlrjhj_sr.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_shzlrjhj_sr.text.toString(),tv_frag_shzlrjhj_sr)
        }
        ll_frag_shzlrhj_dscy.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_shzlrhj_dscy.text.toString(),tv_frag_shzlrhj_dscy)
        }
        tv_frag_shzlrhj_scxfw.setOnClickListener {
            TdlyUtils.setTextPop(activity,"生产性服务",tv_frag_shzlrhj_scxfw)
        }
        tv_frag_shzlrhj_shxfw.setOnClickListener {
            TdlyUtils.setTextPop(activity,"生活性服务",tv_frag_shzlrhj_shxfw)
        }

        cunCode = AppCache.getInstance().rjhjCode


        /*tv_frag_cyjg_xzsj.setOnClickListener {
            if (yearUpPopu!=null) {
                CommenPop.backgroundAlpha(0.5f, activity)
                yearUpPopu!!.showAtLocation(ll_frag_shzlrjhj_top, Gravity.CENTER, 0, 0)
            }
                *//*TimePickerUtil.initTimePickerViewNyr(activity,tv_frag_cyjg_xzsj.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    //时间筛选
                    tv_frag_cyjg_xzsj.setText(data)
                }
            })*//*
        }*/

    }

    fun setWtdw1(snippet:String){

//        mPresenter.getHbjcListCode(-1,30,1,AppCache.getInstance().cunCode,0,"",snippet,-1,1)//cunCode
    }

     fun shzlGddwTab() {
        ll_frag_shzlrjhj_list.visibility = View.GONE//
        ll_frag_shzlrjhj_gddw_list.visibility = View.VISIBLE//
//        mPresenter.getDdList(AppCache.getInstance().userId.toString(), gddwPage)//查询固定点位列表
    }

     fun shzlRjhjTab() {
        ll_frag_shzlrjhj_list.visibility = View.VISIBLE//
        ll_frag_shzlrjhj_gddw_list.visibility = View.GONE//


    }
    var split = ""
    override fun initDatas() {
        val dateAfter = TimeUtil.getDateAfter(0)
//        split = dateAfter.split("-")[0]
        mtl_frag_cyjg_cpmc.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_cyjg_cpmc.isShown){
                        ll_frag_cyjg_cpmc.visibility = View.GONE
                        mtl_frag_cyjg_cpmc.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_cyjg_cpmc.visibility = View.VISIBLE
                        mtl_frag_cyjg_cpmc.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        mtl_frag_cyjg_ghyczqkxx.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_cyjg_ghyczqkxx.isShown){
                        ll_frag_cyjg_ghyczqkxx.visibility = View.GONE
                        mtl_frag_cyjg_ghyczqkxx.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_cyjg_ghyczqkxx.visibility = View.VISIBLE
                        mtl_frag_cyjg_ghyczqkxx.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })

        if (AppCache.getInstance().type == 4){
            ll_frag_cyjg_gzjdd.visibility = View.VISIBLE
        }else{
            ll_frag_cyjg_gzjdd.visibility = View.GONE
        }

    }

    private val yearsList = ArrayList<String>()
    private val yearsListNull = ArrayList<String>()
    private fun yearPopup(yearList:List<String>){
        yearUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_years, ll_frag_shzlrjhj_top)
        val contentView: View = yearUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_years_rlv)
        val confirm = contentView.findViewById<TextView>(R.id.pop_bcjc_years_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_years_close)


        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter = object :BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_bcjc_years,yearList){
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.item_bcjc_years_tv,item)
                val checkBox = helper.getView<CheckBox>(R.id.item_bcjc_years_cb)
                checkBox.setOnClickListener {
                    if (checkBox.isChecked){
                        yearsList.add(item!!)
                    }else{
                        yearsList.remove(item)
                    }
                }

            }


        }

        confirm.setOnClickListener {
            mPresenter.getBcjcCyjg(AppCache.getInstance().code,yearsList)
        }
        close.setOnClickListener {
            yearUpPopu!!.dismiss()
        }
    }

    private val uploadList = ArrayList<BccyjgEntity>()
    var hashMapId1 = 0L
    var hashMapId2 = 0L
    var hashMapId3 = 0L
    var hashMapId4 = 0L
    var hashMapProcess = 1//产业结构表格进度

    var et_cz_dycy: EditText? = null
    var et_cz_zzy:EditText? = null
    var et_cz_ly:EditText? = null
    var et_cz_dscy:EditText? = null
    var et_xnbc_dycy: EditText? = null
    var et_xnbc_zzy:EditText? = null
    var et_xnbc_ly:EditText? = null
    var et_xnbc_dscy:EditText? = null
    var et_xnwl_dycy: EditText? = null
    var et_xnwl_zzy:EditText? = null
    var et_xnwl_ly:EditText? = null
    var et_xnwl_dscy:EditText? = null


    var et_cz_ls:EditText? = null
    var et_cz_sc:EditText? = null
    var et_cz_lg:EditText? = null
    var et_cz_sg:EditText? = null
    var et_cz_my:EditText? = null
    var et_cz_yy:EditText? = null
    var et_cz_decy:EditText? = null
    var et_cz_ms:EditText? = null
    var et_cz_fd:EditText? = null
    var et_cz_qt:EditText? = null
    var et_cz_scxfw:EditText? = null
    var et_cz_shxfw:EditText? = null


    var et_xnbc_ls:EditText? = null
    var et_xnbc_sc:EditText? = null
    var et_xnbc_lg:EditText? = null
    var et_xnbc_sg:EditText? = null
    var et_xnbc_my:EditText? = null
    var et_xnbc_yy:EditText? = null
    var et_xnbc_decy:EditText? = null
    var et_xnbc_ms:EditText? = null
    var et_xnbc_fd:EditText? = null
    var et_xnbc_qt:EditText? = null
    var et_xnbc_scxfw:EditText? = null
    var et_xnbc_shxfw:EditText? = null

    var et_xnwl_ls:EditText? = null
    var et_xnwl_sc:EditText? = null
    var et_xnwl_lg:EditText? = null
    var et_xnwl_sg:EditText? = null
    var et_xnwl_my:EditText? = null
    var et_xnwl_yy:EditText? = null
    var et_xnwl_decy:EditText? = null
    var et_xnwl_ms:EditText? = null
    var et_xnwl_fd:EditText? = null
    var et_xnwl_qt:EditText? = null
    var et_xnwl_scxfw:EditText? = null
    var et_xnwl_shxfw:EditText? = null

    override fun returnBcjcYears(msg: List<String>) {
        yearsList.clear()
        if (AppCache.getInstance().duties!=1&&AppCache.getInstance().type!=1){
            yearsList.add(msg.get(0))
            split = yearsList.get(0).substring(0,yearsList.get(0).length-1)
        }
        mPresenter.getBcjcCyjg(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCyjgAll(AppCache.getInstance().cunCode,yearsListNull)
        yearPopup(msg)
        tv_frag_cyjg_xzsj.addText = msg.get(0)
        /*if (AppCache.getInstance().type == 4){
            tv_frag_cyjg_add.text = "操作"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_cyjg_add.visibility = View.GONE
        }else{
            tv_frag_cyjg_add.text = "操作"
        }*/


        tv_frag_cyjg_xzsj.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {

                if (name.equals("添加")){

                    val arrayList = ArrayList<String>()
                    arrayList.addAll(msg)

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_frag_cyjg_xzsj.addText = arrayList.get(options1)
//                tv_frag_tdly_xzsj.setText(arrayList.get(options1))
                        split = arrayList.get(options1).substring(0,arrayList.get(options1).length-1)
//                        year = arrayList.get(options1)
                        yearsList.clear()
                        yearsList.add(split+"年")
                        mPresenter.getBcjcCyjg(AppCache.getInstance().code,yearsList)
                        mPresenter.getBcjcCyjgAll(AppCache.getInstance().cunCode,yearsListNull)

//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(arrayList.indexOf(tv_frag_cyjg_xzsj.addText))
                    pvOptions.setPicker(arrayList)

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
        })

        tv_frag_cyjg_add.setOnClickListener {
            if (tv_frag_cyjg_add.text.toString().equals("操作")){

            addCyjgUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_cyjg, ll_frag_shzlrjhj_top)
            val contentView: View = addCyjgUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_cyjg_spr)
            val tv_pop_add_cyjg = contentView.findViewById<TextView>(R.id.tv_pop_add_cyjg)
            val tv_pop_add_cyjg_sr = contentView.findViewById<TextView>(R.id.tv_pop_add_cyjg_sr)
                val et_zycp_dycy = contentView.findViewById<TextView>(R.id.pop_add_cyjg_et_zycp_dycy)
                val et_zycp_zzy = contentView.findViewById<TextView>(R.id.pop_add_cyjg_et_zycp_zzy)
            val et_zycp_ls = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_ls)
            val et_zycp_sc = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_sc)
                val et_zycp_ly = contentView.findViewById<TextView>(R.id.pop_add_cyjg_et_zycp_ly)
            val et_zycp_lg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_lg)
            val et_zycp_sg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_sg)
            val et_zycp_my = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_my)
            val et_zycp_yy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_yy)
            val et_zycp_decy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_decy)
                val et_zycp_dscy = contentView.findViewById<TextView>(R.id.pop_add_cyjg_et_zycp_dscy)
            val et_zycp_ms = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_ms)
            val et_zycp_fd = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_fd)
                val et_zycp_qt = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_qt)
            val et_zycp_scxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_scxfw)
            val et_zycp_shxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_zycp_shxfw)

            et_cz_dycy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_dycy)
            et_cz_zzy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_zzy)
            et_cz_ls = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_ls)
            et_cz_sc = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_sc)
            et_cz_ly = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_ly)
            et_cz_lg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_lg)
            et_cz_sg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_sg)
            et_cz_my = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_my)
            et_cz_yy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_yy)
            et_cz_decy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_decy)
            et_cz_dscy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_dscy)
            et_cz_ms = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_ms)
            et_cz_fd = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_fd)
            et_cz_qt = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_qt)
            et_cz_scxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_scxfw)
            et_cz_shxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_cz_shxfw)


            et_xnbc_dycy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_dycy)
            et_xnbc_zzy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_zzy)
            et_xnbc_ls = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_ls)
            et_xnbc_sc = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_sc)
            et_xnbc_ly = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_ly)
            et_xnbc_lg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_lg)
            et_xnbc_sg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_sg)
            et_xnbc_my = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_my)
            et_xnbc_yy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_yy)
            et_xnbc_decy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_decy)
            et_xnbc_dscy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_dscy)
            et_xnbc_ms = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_ms)
            et_xnbc_fd = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_fd)
            et_xnbc_qt = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_qt)
            et_xnbc_scxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_scxfw)
            et_xnbc_shxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnbc_shxfw)

            et_xnwl_dycy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_dycy)
            et_xnwl_zzy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_zzy)
            et_xnwl_ls = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_ls)
            et_xnwl_sc = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_sc)
            et_xnwl_ly = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_ly)
            et_xnwl_lg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_lg)
            et_xnwl_sg = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_sg)
            et_xnwl_my = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_my)
            et_xnwl_yy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_yy)
            et_xnwl_decy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_decy)
            et_xnwl_dscy = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_dscy)
            et_xnwl_ms = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_ms)
            et_xnwl_fd = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_fd)
            et_xnwl_qt = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_qt)
            et_xnwl_scxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_scxfw)
            et_xnwl_shxfw = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_xnwl_shxfw)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_czrname)
                val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_cyjg_ll_reject)
                val et_reject = contentView.findViewById<EditText>(R.id.pop_add_cyjg_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_cyjg_confirm)//确定/下一步/通过
            val reject = contentView.findViewById<TextView>(R.id.pop_add_cyjg_reject)
            val close = contentView.findViewById<TextView>(R.id.pop_add_cyjg_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_cyjg_ell)

                /*et_cz_ls!!.addTextChangedListener(listener(et_cz_ls!!))
                et_cz_sc!!.addTextChangedListener(listener(et_cz_sc!!))
                et_cz_lg!!.addTextChangedListener(listener(et_cz_lg!!))
                et_cz_sg!!.addTextChangedListener(listener(et_cz_sg!!))
                et_cz_my!!.addTextChangedListener(listener(et_cz_my!!))
                et_cz_yy!!.addTextChangedListener(listener(et_cz_yy!!))
                et_cz_decy!!.addTextChangedListener(listener(et_cz_decy!!))
                et_cz_ms!!.addTextChangedListener(listener(et_cz_ms!!))
                et_cz_fd!!.addTextChangedListener(listener(et_cz_fd!!))

                et_xnbc_ls!!.addTextChangedListener(listener(et_xnbc_ls!!))
                et_xnbc_sc!!.addTextChangedListener(listener(et_xnbc_sc!!))
                et_xnbc_lg!!.addTextChangedListener(listener(et_xnbc_lg!!))
                et_xnbc_sg!!.addTextChangedListener(listener(et_xnbc_sg!!))
                et_xnbc_my!!.addTextChangedListener(listener(et_xnbc_my!!))
                et_xnbc_yy!!.addTextChangedListener(listener(et_xnbc_yy!!))
                et_xnbc_decy!!.addTextChangedListener(listener(et_xnbc_decy!!))
                et_xnbc_ms!!.addTextChangedListener(listener(et_xnbc_ms!!))
                et_xnbc_fd!!.addTextChangedListener(listener(et_xnbc_fd!!))

                et_xnwl_ls!!.addTextChangedListener(listener(et_xnwl_ls!!))
                et_xnwl_sc!!.addTextChangedListener(listener(et_xnwl_sc!!))
                et_xnwl_lg!!.addTextChangedListener(listener(et_xnwl_lg!!))
                et_xnwl_sg!!.addTextChangedListener(listener(et_xnwl_sg!!))
                et_xnwl_my!!.addTextChangedListener(listener(et_xnwl_my!!))
                et_xnwl_yy!!.addTextChangedListener(listener(et_xnwl_yy!!))
                et_xnwl_decy!!.addTextChangedListener(listener(et_xnwl_decy!!))
                et_xnwl_ms!!.addTextChangedListener(listener(et_xnwl_ms!!))
                et_xnwl_fd!!.addTextChangedListener(listener(et_xnwl_fd!!))*/

                et_cz_dycy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_zzy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_ly!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_dscy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_dycy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_zzy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_ly!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_dscy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_dycy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_zzy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_ly!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_dscy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_ls!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_sc!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_lg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_sg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_my!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_yy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_decy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_ms!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_fd!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_qt!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_scxfw!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_cz_shxfw!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_ls!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_sc!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_lg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_sg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_my!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_yy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_decy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_ms!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_fd!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_qt!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_scxfw!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnbc_shxfw!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_ls!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_sc!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_lg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_sg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_my!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_yy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_decy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_ms!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_fd!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_qt!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_scxfw!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
                et_xnwl_shxfw!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);


            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addCyjgUpPopu!!.showAtLocation(ll_frag_shzlrjhj_top, Gravity.CENTER, 0, 0)

//                tv_pop_add_cyjg

                tv_pop_add_cyjg.setText(tv_frag_cyjg_xzsj.addText)//msg.get(0)
                val zycpList = hashMap.get("zycp")
                val czList = hashMap.get("cz")
                val xnbcjyList = hashMap.get("xnbcjy")
                val xnwljyList = hashMap.get("xnwljy")
                if (zycpList!=null){
                    for (i in zycpList!!){
                        if (tv_frag_cyjg_xzsj.addText.equals(i.years)){//msg.get(0)
//                            et_zycp_dycy.setText(i.dycy)
//                            et_zycp_zzy.setText(i.zzy)
                            et_zycp_ls.setText(i.zzyls)
                            et_zycp_sc.setText(i.zzysc)
//                            et_zycp_ly.setText(i.ly)
                            et_zycp_lg.setText(i.lylg)
                            et_zycp_sg.setText(i.lysg)
                            et_zycp_my.setText(i.my)
                            et_zycp_yy.setText(i.yy)
                            et_zycp_decy.setText(i.decy)
//                            et_zycp_dscy.setText(i.dscy)
                            et_zycp_ms.setText(i.dscms)
                            et_zycp_fd.setText(i.dscfd)
//                            et_zycp_qt.setText(i.dscqt)
                            et_zycp_scxfw.setText(i.scfw)
                            et_zycp_shxfw.setText(i.shfw)
                            hashMapId1 = i.id
                            hashMapProcess = i.process
                            if (AppCache.getInstance().type == 4){
                                et_czrname.setText(i.cunname)
                                enableLl.setNoClick(false)
                                when(hashMapProcess){
                                    1->{
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }
                                    2->{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }else{
                                enableLl.setNoClick(true)
                                if (AppCache.getInstance().type == 3){
                                    et_czrname.setText(i.zhenname)
                                    if (hashMapProcess == 2){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else if (AppCache.getInstance().type == 2){
                                    et_czrname.setText(i.qvname)
                                    if (hashMapProcess == 3){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else{
                                    et_czrname.setText(i.qvname)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                            break
                        }else{
//                            et_zycp_dycy.setText("")
//                            et_zycp_zzy.setText("")
                            et_zycp_ls.setText("")
                            et_zycp_sc.setText("")
//                            et_zycp_ly.setText("")
                            et_zycp_lg.setText("")
                            et_zycp_sg.setText("")
                            et_zycp_my.setText("")
                            et_zycp_yy.setText("")
                            et_zycp_decy.setText("")
//                            et_zycp_dscy.setText("")
                            et_zycp_ms.setText("")
                            et_zycp_fd.setText("")
//                            et_zycp_qt.setText("")
                            et_zycp_scxfw.setText("")
                            et_zycp_shxfw.setText("")
                            hashMapId1 = 0L
                            hashMapProcess = 0
                            if (AppCache.getInstance().type == 4){
                                enableLl.setNoClick(false)
                                confirm.visibility = View.VISIBLE
                                reject.visibility = View.VISIBLE
                            }else{
                                enableLl.setNoClick(true)
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                    }
                }
                if (czList!=null){
                    for (i in czList!!){
                        if (tv_frag_cyjg_xzsj.addText.equals(i.years)){//msg.get(0)
                            et_cz_dycy!!.setText(i.dycy)
                            et_cz_zzy!!.setText(i.zzy)
                            et_cz_ls!!.setText(i.zzyls)
                            et_cz_sc!!.setText(i.zzysc)
                            et_cz_ly!!.setText(i.ly)
                            et_cz_lg!!.setText(i.lylg)
                            et_cz_sg!!.setText(i.lysg)
                            et_cz_my!!.setText(i.my)
                            et_cz_yy!!.setText(i.yy)
                            et_cz_decy!!.setText(i.decy)
                            et_cz_dscy!!.setText(i.dscy)
                            et_cz_ms!!.setText(i.dscms)
                            et_cz_fd!!.setText(i.dscfd)
//                            et_cz_qt.setText(i.dscqt)
                            et_cz_scxfw!!.setText(i.scfw)
                            et_cz_shxfw!!.setText(i.shfw)
                            hashMapId2 = i.id
                            hashMapProcess = i.process
                            if (AppCache.getInstance().type == 4){
                                et_czrname.setText(i.cunname)
                                enableLl.setNoClick(false)
                                when(hashMapProcess){
                                    1->{
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }
                                    2->{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }else{
                                enableLl.setNoClick(true)
                                if (AppCache.getInstance().type == 3){
                                    et_czrname.setText(i.zhenname)
                                    if (hashMapProcess == 2){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else if (AppCache.getInstance().type == 2){
                                    et_czrname.setText(i.qvname)
                                    if (hashMapProcess == 3){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else{
                                    et_czrname.setText(i.qvname)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                            break
                        }else{
                            et_cz_dycy!!.setText("")
                            et_cz_zzy!!.setText("")
                            et_cz_ls!!.setText("")
                            et_cz_sc!!.setText("")
                            et_cz_ly!!.setText("")
                            et_cz_lg!!.setText("")
                            et_cz_sg!!.setText("")
                            et_cz_my!!.setText("")
                            et_cz_yy!!.setText("")
                            et_cz_decy!!.setText("")
                            et_cz_dscy!!.setText("")
                            et_cz_ms!!.setText("")
                            et_cz_fd!!.setText("")
//                            et_cz_qt.setText("")
                            et_cz_scxfw!!.setText("")
                            et_cz_shxfw!!.setText("")
                            hashMapId2 = 0L
                            hashMapProcess = 0
                            if (AppCache.getInstance().type == 4){
                                enableLl.setNoClick(false)
                                confirm.visibility = View.VISIBLE
                                reject.visibility = View.VISIBLE
                            }else{
                                enableLl.setNoClick(true)
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                    }
                }
                if (xnbcjyList!=null){
                    for (i in xnbcjyList!!){
                        if (tv_frag_cyjg_xzsj.addText.equals(i.years)){//msg.get(0)
                            et_xnbc_dycy!!.setText(i.dycy)
                            et_xnbc_zzy!!.setText(i.zzy)
                            et_xnbc_ls!!.setText(i.zzyls)
                            et_xnbc_sc!!.setText(i.zzysc)
                            et_xnbc_ly!!.setText(i.ly)
                            et_xnbc_lg!!.setText(i.lylg)
                            et_xnbc_sg!!.setText(i.lysg)
                            et_xnbc_my!!.setText(i.my)
                            et_xnbc_yy!!.setText(i.yy)
                            et_xnbc_decy!!.setText(i.decy)
                            et_xnbc_dscy!!.setText(i.dscy)
                            et_xnbc_ms!!.setText(i.dscms)
                            et_xnbc_fd!!.setText(i.dscfd)
//                            et_xnbc_qt.setText(i.dscqt)
                            et_xnbc_scxfw!!.setText(i.scfw)
                            et_xnbc_shxfw!!.setText(i.shfw)
                            hashMapId3 = i.id
                            hashMapProcess = i.process
                            if (AppCache.getInstance().type == 4){
                                et_czrname.setText(i.cunname)
                                enableLl.setNoClick(false)
                                when(hashMapProcess){
                                    1->{
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }
                                    2->{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }else{
                                enableLl.setNoClick(true)
                                if (AppCache.getInstance().type == 3){
                                    et_czrname.setText(i.zhenname)
                                    if (hashMapProcess == 2){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else if (AppCache.getInstance().type == 2){
                                    et_czrname.setText(i.qvname)
                                    if (hashMapProcess == 3){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else{
                                    et_czrname.setText(i.qvname)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                            break
                        }else{
                            et_xnbc_dycy!!.setText("")
                            et_xnbc_zzy!!.setText("")
                            et_xnbc_ls!!.setText("")
                            et_xnbc_sc!!.setText("")
                            et_xnbc_ly!!.setText("")
                            et_xnbc_lg!!.setText("")
                            et_xnbc_sg!!.setText("")
                            et_xnbc_my!!.setText("")
                            et_xnbc_yy!!.setText("")
                            et_xnbc_decy!!.setText("")
                            et_xnbc_dscy!!.setText("")
                            et_xnbc_ms!!.setText("")
                            et_xnbc_fd!!.setText("")
//                            et_xnbc_qt.setText("")
                            et_xnbc_scxfw!!.setText("")
                            et_xnbc_shxfw!!.setText("")
                            hashMapId3 = 0L
                            hashMapProcess = 0
                            if (AppCache.getInstance().type == 4){
                                enableLl.setNoClick(false)
                                confirm.visibility = View.VISIBLE
                                reject.visibility = View.VISIBLE
                            }else{
                                enableLl.setNoClick(true)
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                    }
                }
                if (xnwljyList!=null){
                    for (i in xnwljyList!!){
                        if (tv_frag_cyjg_xzsj.addText.equals(i.years)){//msg.get(0)
                            et_xnwl_dycy!!.setText(i.dycy)
                            et_xnwl_zzy!!.setText(i.zzy)
                            et_xnwl_ls!!.setText(i.zzyls)
                            et_xnwl_sc!!.setText(i.zzysc)
                            et_xnwl_ly!!.setText(i.ly)
                            et_xnwl_lg!!.setText(i.lylg)
                            et_xnwl_sg!!.setText(i.lysg)
                            et_xnwl_my!!.setText(i.my)
                            et_xnwl_yy!!.setText(i.yy)
                            et_xnwl_decy!!.setText(i.decy)
                            et_xnwl_dscy!!.setText(i.dscy)
                            et_xnwl_ms!!.setText(i.dscms)
                            et_xnwl_fd!!.setText(i.dscfd)
//                            et_xnwl_qt.setText(i.dscqt)
                            et_xnwl_scxfw!!.setText(i.scfw)
                            et_xnwl_shxfw!!.setText(i.shfw)
                            hashMapId4 = i.id
                            hashMapProcess = i.process
                            if (AppCache.getInstance().type == 4){
                                et_czrname.setText(i.cunname)
                                enableLl.setNoClick(false)
                                when(hashMapProcess){
                                    1->{
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }
                                    2->{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }else{
                                enableLl.setNoClick(true)
                                if (AppCache.getInstance().type == 3){
                                    et_czrname.setText(i.zhenname)
                                    if (hashMapProcess == 2){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else if (AppCache.getInstance().type == 2){
                                    et_czrname.setText(i.qvname)
                                    if (hashMapProcess == 3){
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }else{
                                    et_czrname.setText(i.qvname)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                            break
                        }else{
                            et_xnwl_dycy!!.setText("")
                            et_xnwl_zzy!!.setText("")
                            et_xnwl_ls!!.setText("")
                            et_xnwl_sc!!.setText("")
                            et_xnwl_ly!!.setText("")
                            et_xnwl_lg!!.setText("")
                            et_xnwl_sg!!.setText("")
                            et_xnwl_my!!.setText("")
                            et_xnwl_yy!!.setText("")
                            et_xnwl_decy!!.setText("")
                            et_xnwl_dscy!!.setText("")
                            et_xnwl_ms!!.setText("")
                            et_xnwl_fd!!.setText("")
//                            et_xnwl_qt.setText("")
                            et_xnwl_scxfw!!.setText("")
                            et_xnwl_shxfw!!.setText("")
                            hashMapId4 = 0L
                            hashMapProcess = 0
                            if (AppCache.getInstance().type == 4){
                                enableLl.setNoClick(false)
                                confirm.visibility = View.VISIBLE
                                reject.visibility = View.VISIBLE
                            }else{
                                enableLl.setNoClick(true)
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                    }
                }
                /*tv_pop_add_cyjg.setOnClickListener {

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_cyjg.setText(msg.get(options1))

                        if (zycpList!=null){
                            for (i in zycpList!!){
                                if (msg.get(options1).equals(i.years)){
                                    et_zycp_dycy.setText(i.dycy)
                                    et_zycp_zzy.setText(i.zzy)
                                    et_zycp_ls.setText(i.zzyls)
                                    et_zycp_sc.setText(i.zzysc)
                                    et_zycp_ly.setText(i.ly)
                                    et_zycp_lg.setText(i.lylg)
                                    et_zycp_sg.setText(i.lysg)
                                    et_zycp_my.setText(i.my)
                                    et_zycp_yy.setText(i.yy)
                                    et_zycp_decy.setText(i.decy)
                                    et_zycp_dscy.setText(i.dscy)
                                    et_zycp_scxfw.setText(i.scfw)
                                    et_zycp_shxfw.setText(i.shfw)
                                    hashMapId1 = i.id
                                    hashMapProcess = i.process
                                    if (AppCache.getInstance().type == 4){
                                        et_czrname.setText(i.cunname)
                                        enableLl.setNoClick(false)
                                        when(hashMapProcess){
                                            1->{
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }
                                            2->{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }
                                    }else{
                                        enableLl.setNoClick(true)
                                        if (AppCache.getInstance().type == 3){
                                            et_czrname.setText(i.zhenname)
                                            if (hashMapProcess == 2){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else if (AppCache.getInstance().type == 2){
                                            et_czrname.setText(i.qvname)
                                            if (hashMapProcess == 3){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else{
                                            et_czrname.setText(i.qvname)
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                    break
                                }else{
                                    et_zycp_dycy.setText("")
                                    et_zycp_zzy.setText("")
                                    et_zycp_ls.setText("")
                                    et_zycp_sc.setText("")
                                    et_zycp_ly.setText("")
                                    et_zycp_lg.setText("")
                                    et_zycp_sg.setText("")
                                    et_zycp_my.setText("")
                                    et_zycp_yy.setText("")
                                    et_zycp_decy.setText("")
                                    et_zycp_dscy.setText("")
                                    et_zycp_scxfw.setText("")
                                    et_zycp_shxfw.setText("")
                                    hashMapId1 = 0L
                                    hashMapProcess = 0
                                    if (AppCache.getInstance().type == 4){
                                        enableLl.setNoClick(false)
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        enableLl.setNoClick(true)
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }
                        }
                        if (czList!=null){
                            for (i in czList!!){
                                if (msg.get(options1).equals(i.years)){
                                    et_cz_dycy.setText(i.dycy)
                                    et_cz_zzy.setText(i.zzy)
                                    et_cz_ls.setText(i.zzyls)
                                    et_cz_sc.setText(i.zzysc)
                                    et_cz_ly.setText(i.ly)
                                    et_cz_lg.setText(i.lylg)
                                    et_cz_sg.setText(i.lysg)
                                    et_cz_my.setText(i.my)
                                    et_cz_yy.setText(i.yy)
                                    et_cz_decy.setText(i.decy)
                                    et_cz_dscy.setText(i.dscy)
                                    et_cz_scxfw.setText(i.scfw)
                                    et_cz_shxfw.setText(i.shfw)
                                    hashMapId2 = i.id
                                    hashMapProcess = i.process
                                    if (AppCache.getInstance().type == 4){
                                        et_czrname.setText(i.cunname)
                                        enableLl.setNoClick(false)
                                        when(hashMapProcess){
                                            1->{
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }
                                            2->{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }
                                    }else{
                                        enableLl.setNoClick(true)
                                        if (AppCache.getInstance().type == 3){
                                            et_czrname.setText(i.zhenname)
                                            if (hashMapProcess == 2){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else if (AppCache.getInstance().type == 2){
                                            et_czrname.setText(i.qvname)
                                            if (hashMapProcess == 3){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else{
                                            et_czrname.setText(i.qvname)
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                    break
                                }else{
                                    et_cz_dycy.setText("")
                                    et_cz_zzy.setText("")
                                    et_cz_ls.setText("")
                                    et_cz_sc.setText("")
                                    et_cz_ly.setText("")
                                    et_cz_lg.setText("")
                                    et_cz_sg.setText("")
                                    et_cz_my.setText("")
                                    et_cz_yy.setText("")
                                    et_cz_decy.setText("")
                                    et_cz_dscy.setText("")
                                    et_cz_scxfw.setText("")
                                    et_cz_shxfw.setText("")
                                    hashMapId2 = 0L
                                    hashMapProcess = 0
                                    if (AppCache.getInstance().type == 4){
                                        enableLl.setNoClick(false)
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        enableLl.setNoClick(true)
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }
                        }
                        if (xnbcjyList!=null){
                            for (i in xnbcjyList!!){
                                if (msg.get(options1).equals(i.years)){
                                    et_xnbc_dycy.setText(i.dycy)
                                    et_xnbc_zzy.setText(i.zzy)
                                    et_xnbc_ls.setText(i.zzyls)
                                    et_xnbc_sc.setText(i.zzysc)
                                    et_xnbc_ly.setText(i.ly)
                                    et_xnbc_lg.setText(i.lylg)
                                    et_xnbc_sg.setText(i.lysg)
                                    et_xnbc_my.setText(i.my)
                                    et_xnbc_yy.setText(i.yy)
                                    et_xnbc_decy.setText(i.decy)
                                    et_xnbc_dscy.setText(i.dscy)
                                    et_xnbc_scxfw.setText(i.scfw)
                                    et_xnbc_shxfw.setText(i.shfw)
                                    hashMapId3 = i.id
                                    hashMapProcess = i.process
                                    if (AppCache.getInstance().type == 4){
                                        et_czrname.setText(i.cunname)
                                        enableLl.setNoClick(false)
                                        when(hashMapProcess){
                                            1->{
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }
                                            2->{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }
                                    }else{
                                        enableLl.setNoClick(true)
                                        if (AppCache.getInstance().type == 3){
                                            et_czrname.setText(i.zhenname)
                                            if (hashMapProcess == 2){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else if (AppCache.getInstance().type == 2){
                                            et_czrname.setText(i.qvname)
                                            if (hashMapProcess == 3){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else{
                                            et_czrname.setText(i.qvname)
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                    break
                                }else{
                                    et_xnbc_dycy.setText("")
                                    et_xnbc_zzy.setText("")
                                    et_xnbc_ls.setText("")
                                    et_xnbc_sc.setText("")
                                    et_xnbc_ly.setText("")
                                    et_xnbc_lg.setText("")
                                    et_xnbc_sg.setText("")
                                    et_xnbc_my.setText("")
                                    et_xnbc_yy.setText("")
                                    et_xnbc_decy.setText("")
                                    et_xnbc_dscy.setText("")
                                    et_xnbc_scxfw.setText("")
                                    et_xnbc_shxfw.setText("")
                                    hashMapId3 = 0L
                                    hashMapProcess = 0
                                    if (AppCache.getInstance().type == 4){
                                        enableLl.setNoClick(false)
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        enableLl.setNoClick(true)
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }
                        }
                        if (xnwljyList!=null){
                            for (i in xnwljyList!!){
                                if (msg.get(options1).equals(i.years)){
                                    et_xnwl_dycy.setText(i.dycy)
                                    et_xnwl_zzy.setText(i.zzy)
                                    et_xnwl_ls.setText(i.zzyls)
                                    et_xnwl_sc.setText(i.zzysc)
                                    et_xnwl_ly.setText(i.ly)
                                    et_xnwl_lg.setText(i.lylg)
                                    et_xnwl_sg.setText(i.lysg)
                                    et_xnwl_my.setText(i.my)
                                    et_xnwl_yy.setText(i.yy)
                                    et_xnwl_decy.setText(i.decy)
                                    et_xnwl_dscy.setText(i.dscy)
                                    et_xnwl_scxfw.setText(i.scfw)
                                    et_xnwl_shxfw.setText(i.shfw)
                                    hashMapId4 = i.id
                                    hashMapProcess = i.process
                                    if (AppCache.getInstance().type == 4){
                                        et_czrname.setText(i.cunname)
                                        enableLl.setNoClick(false)
                                        when(hashMapProcess){
                                            1->{
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }
                                            2->{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }
                                    }else{
                                        enableLl.setNoClick(true)
                                        if (AppCache.getInstance().type == 3){
                                            et_czrname.setText(i.zhenname)
                                            if (hashMapProcess == 2){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else if (AppCache.getInstance().type == 2){
                                            et_czrname.setText(i.qvname)
                                            if (hashMapProcess == 3){
                                                confirm.visibility = View.VISIBLE
                                                reject.visibility = View.VISIBLE
                                            }else{
                                                confirm.visibility = View.GONE
                                                reject.visibility = View.GONE
                                            }
                                        }else{
                                            et_czrname.setText(i.qvname)
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }
                                    break
                                }else{
                                    et_xnwl_dycy.setText("")
                                    et_xnwl_zzy.setText("")
                                    et_xnwl_ls.setText("")
                                    et_xnwl_sc.setText("")
                                    et_xnwl_ly.setText("")
                                    et_xnwl_lg.setText("")
                                    et_xnwl_sg.setText("")
                                    et_xnwl_my.setText("")
                                    et_xnwl_yy.setText("")
                                    et_xnwl_decy.setText("")
                                    et_xnwl_dscy.setText("")
                                    et_xnwl_scxfw.setText("")
                                    et_xnwl_shxfw.setText("")
                                    hashMapId4 = 0L
                                    hashMapProcess = 0
                                    if (AppCache.getInstance().type == 4){
                                        enableLl.setNoClick(false)
                                        confirm.visibility = View.VISIBLE
                                        reject.visibility = View.VISIBLE
                                    }else{
                                        enableLl.setNoClick(true)
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                            }
                        }


//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_cyjg.text.toString()))
                    pvOptions.setPicker(msg)

                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                    params.leftMargin = 0
                    params.rightMargin = 0
                    val contentContainer = pvOptions.getDialogContainerLayout()
                    contentContainer.setLayoutParams(params)
                    pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                    pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    pvOptions.show()

                }*/
            when(AppCache.getInstance().type){
                2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {
                        uploadList.clear()
                        val entity1 = BccyjgEntity()
//                        entity1.dycy = et_zycp_dycy.text.toString()
//                        entity1.zzy = et_zycp_zzy.text.toString()
                        entity1.zzyls = et_zycp_ls.text.toString()
                        entity1.zzysc = et_zycp_sc.text.toString()
//                        entity1.ly = et_zycp_ly.text.toString()
                        entity1.lylg = et_zycp_lg.text.toString()
                        entity1.lysg = et_zycp_sg.text.toString()
                        entity1.my = et_zycp_my.text.toString()
                        entity1.yy = et_zycp_yy.text.toString()
                        entity1.decy = et_zycp_decy.text.toString()
//                        entity1.dscy = et_zycp_dscy.text.toString()
                        entity1.dscms = et_zycp_ms.text.toString()
                        entity1.dscfd = et_zycp_fd.text.toString()
//                        entity1.dscqt = et_zycp_qt.text.toString()
                        entity1.scfw = et_zycp_scxfw.text.toString()
                        entity1.shfw = et_zycp_shxfw.text.toString()
                        entity1.lx = 1
                        entity1.years = tv_pop_add_cyjg.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.process = 4
                        if (hashMapId1 != 0L){
                            entity1.id =  hashMapId1
                        }
                        uploadList.add(entity1)
                        val entity2 = BccyjgEntity()
                        entity2.dycy = et_cz_dycy!!.text.toString()
                        entity2.zzy = et_cz_zzy!!.text.toString()
                        entity2.zzyls = et_cz_ls!!.text.toString()
                        entity2.zzysc = et_cz_sc!!.text.toString()
                        entity2.ly = et_cz_ly!!.text.toString()
                        entity2.lylg = et_cz_lg!!.text.toString()
                        entity2.lysg = et_cz_sg!!.text.toString()
                        entity2.my = et_cz_my!!.text.toString()
                        entity2.yy = et_cz_yy!!.text.toString()
                        entity2.decy = et_cz_decy!!.text.toString()
                        entity2.dscy = et_cz_dscy!!.text.toString()
                        entity2.dscms = et_cz_ms!!.text.toString()
                        entity2.dscfd = et_cz_fd!!.text.toString()
//                        entity2.dscqt = et_cz_qt.text.toString()
                        entity2.scfw = et_cz_scxfw!!.text.toString()
                        entity2.shfw = et_cz_shxfw!!.text.toString()
                        entity2.lx = 2
                        entity2.years = tv_pop_add_cyjg.text.toString()
                        entity2.code = AppCache.getInstance().code
                        entity2.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity2.process = 4
                        if (hashMapId2 != 0L){
                            entity2.id =  hashMapId2
                        }
                        uploadList.add(entity2)
                        val entity3 = BccyjgEntity()
                        entity3.dycy = et_xnbc_dycy!!.text.toString()
                        entity3.zzy = et_xnbc_zzy!!.text.toString()
                        entity3.zzyls = et_xnbc_ls!!.text.toString()
                        entity3.zzysc = et_xnbc_sc!!.text.toString()
                        entity3.ly = et_xnbc_ly!!.text.toString()
                        entity3.lylg = et_xnbc_lg!!.text.toString()
                        entity3.lysg = et_xnbc_sg!!.text.toString()
                        entity3.my = et_xnbc_my!!.text.toString()
                        entity3.yy = et_xnbc_yy!!.text.toString()
                        entity3.decy = et_xnbc_decy!!.text.toString()
                        entity3.dscy = et_xnbc_dscy!!.text.toString()
                        entity3.dscms = et_xnbc_ms!!.text.toString()
                        entity3.dscfd = et_xnbc_fd!!.text.toString()
//                        entity3.dscqt = et_xnbc_qt.text.toString()
                        entity3.scfw = et_xnbc_scxfw!!.text.toString()
                        entity3.shfw = et_xnbc_shxfw!!.text.toString()
                        entity3.lx = 3
                        entity3.years = tv_pop_add_cyjg.text.toString()
                        entity3.code = AppCache.getInstance().code
                        entity3.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity3.process = 4
                        if (hashMapId3 != 0L){
                            entity3.id =  hashMapId3
                        }
                        uploadList.add(entity3)
                        val entity4 = BccyjgEntity()
                        entity4.dycy = et_xnwl_dycy!!.text.toString()
                        entity4.zzy = et_xnwl_zzy!!.text.toString()
                        entity4.zzyls = et_xnwl_ls!!.text.toString()
                        entity4.zzysc = et_xnwl_sc!!.text.toString()
                        entity4.ly = et_xnwl_ly!!.text.toString()
                        entity4.lylg = et_xnwl_lg!!.text.toString()
                        entity4.lysg = et_xnwl_sg!!.text.toString()
                        entity4.my = et_xnwl_my!!.text.toString()
                        entity4.yy = et_xnwl_yy!!.text.toString()
                        entity4.decy = et_xnwl_decy!!.text.toString()
                        entity4.dscy = et_xnwl_dscy!!.text.toString()
                        entity4.dscms = et_xnwl_ms!!.text.toString()
                        entity4.dscfd = et_xnwl_fd!!.text.toString()
//                        entity4.dscqt = et_xnwl_qt.text.toString()
                        entity4.scfw = et_xnwl_scxfw!!.text.toString()
                        entity4.shfw = et_xnwl_shxfw!!.text.toString()
                        entity4.lx = 4
                        entity4.years = tv_pop_add_cyjg.text.toString()
                        entity4.code = AppCache.getInstance().code
                        entity4.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity4.process = 4
                        if (hashMapId4 != 0L){
                            entity4.id =  hashMapId4
                        }
                        uploadList.add(entity4)
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            uploadList.get(0).cunname = et_czrname.text.toString()
                            uploadList.get(1).cunname = et_czrname.text.toString()
                            uploadList.get(2).cunname = et_czrname.text.toString()
                            uploadList.get(3).cunname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCyjg(uploadList)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        uploadList.clear()
                        val entity1 = BccyjgEntity()
//                        entity1.dycy = et_zycp_dycy.text.toString()
//                        entity1.zzy = et_zycp_zzy.text.toString()
                        entity1.zzyls = et_zycp_ls.text.toString()
                        entity1.zzysc = et_zycp_sc.text.toString()
//                        entity1.ly = et_zycp_ly.text.toString()
                        entity1.lylg = et_zycp_lg.text.toString()
                        entity1.lysg = et_zycp_sg.text.toString()
                        entity1.my = et_zycp_my.text.toString()
                        entity1.yy = et_zycp_yy.text.toString()
                        entity1.decy = et_zycp_decy.text.toString()
//                        entity1.dscy = et_zycp_dscy.text.toString()
                        entity1.dscms = et_zycp_ms.text.toString()
                        entity1.dscfd = et_zycp_fd.text.toString()
//                        entity1.dscqt = et_zycp_qt.text.toString()
                        entity1.scfw = et_zycp_scxfw.text.toString()
                        entity1.shfw = et_zycp_shxfw.text.toString()
                        entity1.lx = 1
                        entity1.years = tv_pop_add_cyjg.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.process = 1
                        if (hashMapId1 != 0L){
                            entity1.id =  hashMapId1
                        }
                        uploadList.add(entity1)
                        val entity2 = BccyjgEntity()
                        entity2.dycy = et_cz_dycy!!.text.toString()
                        entity2.zzy = et_cz_zzy!!.text.toString()
                        entity2.zzyls = et_cz_ls!!.text.toString()
                        entity2.zzysc = et_cz_sc!!.text.toString()
                        entity2.ly = et_cz_ly!!.text.toString()
                        entity2.lylg = et_cz_lg!!.text.toString()
                        entity2.lysg = et_cz_sg!!.text.toString()
                        entity2.my = et_cz_my!!.text.toString()
                        entity2.yy = et_cz_yy!!.text.toString()
                        entity2.decy = et_cz_decy!!.text.toString()
                        entity2.dscy = et_cz_dscy!!.text.toString()
                        entity2.dscms = et_cz_ms!!.text.toString()
                        entity2.dscfd = et_cz_fd!!.text.toString()
//                        entity2.dscqt = et_cz_qt.text.toString()
                        entity2.scfw = et_cz_scxfw!!.text.toString()
                        entity2.shfw = et_cz_shxfw!!.text.toString()
                        entity2.lx = 2
                        entity2.years = tv_pop_add_cyjg.text.toString()
                        entity2.code = AppCache.getInstance().code
                        entity2.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity2.process = 1
                        if (hashMapId2 != 0L){
                            entity2.id =  hashMapId2
                        }
                        uploadList.add(entity2)
                        val entity3 = BccyjgEntity()
                        entity3.dycy = et_xnbc_dycy!!.text.toString()
                        entity3.zzy = et_xnbc_zzy!!.text.toString()
                        entity3.zzyls = et_xnbc_ls!!.text.toString()
                        entity3.zzysc = et_xnbc_sc!!.text.toString()
                        entity3.ly = et_xnbc_ly!!.text.toString()
                        entity3.lylg = et_xnbc_lg!!.text.toString()
                        entity3.lysg = et_xnbc_sg!!.text.toString()
                        entity3.my = et_xnbc_my!!.text.toString()
                        entity3.yy = et_xnbc_yy!!.text.toString()
                        entity3.decy = et_xnbc_decy!!.text.toString()
                        entity3.dscy = et_xnbc_dscy!!.text.toString()
                        entity3.dscms = et_xnbc_ms!!.text.toString()
                        entity3.dscfd = et_xnbc_fd!!.text.toString()
//                        entity3.dscqt = et_xnbc_qt.text.toString()
                        entity3.scfw = et_xnbc_scxfw!!.text.toString()
                        entity3.shfw = et_xnbc_shxfw!!.text.toString()
                        entity3.lx = 3
                        entity3.years = tv_pop_add_cyjg.text.toString()
                        entity3.code = AppCache.getInstance().code
                        entity3.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity3.process = 1
                        if (hashMapId3 != 0L){
                            entity3.id =  hashMapId3
                        }
                        uploadList.add(entity3)
                        val entity4 = BccyjgEntity()
                        entity4.dycy = et_xnwl_dycy!!.text.toString()
                        entity4.zzy = et_xnwl_zzy!!.text.toString()
                        entity4.zzyls = et_xnwl_ls!!.text.toString()
                        entity4.zzysc = et_xnwl_sc!!.text.toString()
                        entity4.ly = et_xnwl_ly!!.text.toString()
                        entity4.lylg = et_xnwl_lg!!.text.toString()
                        entity4.lysg = et_xnwl_sg!!.text.toString()
                        entity4.my = et_xnwl_my!!.text.toString()
                        entity4.yy = et_xnwl_yy!!.text.toString()
                        entity4.decy = et_xnwl_decy!!.text.toString()
                        entity4.dscy = et_xnwl_dscy!!.text.toString()
                        entity4.dscms = et_xnwl_ms!!.text.toString()
                        entity4.dscfd = et_xnwl_fd!!.text.toString()
//                        entity4.dscqt = et_xnwl_qt.text.toString()
                        entity4.scfw = et_xnwl_scxfw!!.text.toString()
                        entity4.shfw = et_xnwl_shxfw!!.text.toString()
                        entity4.lx = 4
                        entity4.years = tv_pop_add_cyjg.text.toString()
                        entity4.code = AppCache.getInstance().code
                        entity4.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity4.process = 1
                        if (hashMapId4 != 0L){
                            entity4.id =  hashMapId4
                        }
                        uploadList.add(entity4)
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            uploadList.get(0).zhenname = et_czrname.text.toString()
                            uploadList.get(1).zhenname = et_czrname.text.toString()
                            uploadList.get(2).zhenname = et_czrname.text.toString()
                            uploadList.get(3).zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            uploadList.get(0).bcrejectedEntities.add(bcrejectedEntity)
                            uploadList.get(1).bcrejectedEntities.add(bcrejectedEntity)
                            uploadList.get(2).bcrejectedEntities.add(bcrejectedEntity)
                            uploadList.get(3).bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCyjg(uploadList)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        uploadList.clear()
                        val entity1 = BccyjgEntity()
//                        entity1.dycy = et_zycp_dycy.text.toString()
//                        entity1.zzy = et_zycp_zzy.text.toString()
                        entity1.zzyls = et_zycp_ls.text.toString()
                        entity1.zzysc = et_zycp_sc.text.toString()
//                        entity1.ly = et_zycp_ly.text.toString()
                        entity1.lylg = et_zycp_lg.text.toString()
                        entity1.lysg = et_zycp_sg.text.toString()
                        entity1.my = et_zycp_my.text.toString()
                        entity1.yy = et_zycp_yy.text.toString()
                        entity1.decy = et_zycp_decy.text.toString()
//                        entity1.dscy = et_zycp_dscy.text.toString()
                        entity1.dscms = et_zycp_ms.text.toString()
                        entity1.dscfd = et_zycp_fd.text.toString()
//                        entity1.dscqt = et_zycp_qt.text.toString()
                        entity1.scfw = et_zycp_scxfw.text.toString()
                        entity1.shfw = et_zycp_shxfw.text.toString()
                        entity1.lx = 1
                        entity1.years = tv_pop_add_cyjg.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.process = 3
                        if (hashMapId1 != 0L){
                            entity1.id =  hashMapId1
                        }
                        uploadList.add(entity1)
                        val entity2 = BccyjgEntity()
                        entity2.dycy = et_cz_dycy!!.text.toString()
                        entity2.zzy = et_cz_zzy!!.text.toString()
                        entity2.zzyls = et_cz_ls!!.text.toString()
                        entity2.zzysc = et_cz_sc!!.text.toString()
                        entity2.ly = et_cz_ly!!.text.toString()
                        entity2.lylg = et_cz_lg!!.text.toString()
                        entity2.lysg = et_cz_sg!!.text.toString()
                        entity2.my = et_cz_my!!.text.toString()
                        entity2.yy = et_cz_yy!!.text.toString()
                        entity2.decy = et_cz_decy!!.text.toString()
                        entity2.dscy = et_cz_dscy!!.text.toString()
                        entity2.dscms = et_cz_ms!!.text.toString()
                        entity2.dscfd = et_cz_fd!!.text.toString()
//                        entity2.dscqt = et_cz_qt.text.toString()
                        entity2.scfw = et_cz_scxfw!!.text.toString()
                        entity2.shfw = et_cz_shxfw!!.text.toString()
                        entity2.lx = 2
                        entity2.years = tv_pop_add_cyjg.text.toString()
                        entity2.code = AppCache.getInstance().code
                        entity2.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity2.process = 3
                        if (hashMapId2 != 0L){
                            entity2.id =  hashMapId2
                        }
                        uploadList.add(entity2)
                        val entity3 = BccyjgEntity()
                        entity3.dycy = et_xnbc_dycy!!.text.toString()
                        entity3.zzy = et_xnbc_zzy!!.text.toString()
                        entity3.zzyls = et_xnbc_ls!!.text.toString()
                        entity3.zzysc = et_xnbc_sc!!.text.toString()
                        entity3.ly = et_xnbc_ly!!.text.toString()
                        entity3.lylg = et_xnbc_lg!!.text.toString()
                        entity3.lysg = et_xnbc_sg!!.text.toString()
                        entity3.my = et_xnbc_my!!.text.toString()
                        entity3.yy = et_xnbc_yy!!.text.toString()
                        entity3.decy = et_xnbc_decy!!.text.toString()
                        entity3.dscy = et_xnbc_dscy!!.text.toString()
                        entity3.dscms = et_xnbc_ms!!.text.toString()
                        entity3.dscfd = et_xnbc_fd!!.text.toString()
//                        entity3.dscqt = et_xnbc_qt.text.toString()
                        entity3.scfw = et_xnbc_scxfw!!.text.toString()
                        entity3.shfw = et_xnbc_shxfw!!.text.toString()
                        entity3.lx = 3
                        entity3.years = tv_pop_add_cyjg.text.toString()
                        entity3.code = AppCache.getInstance().code
                        entity3.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity3.process = 3
                        if (hashMapId3 != 0L){
                            entity3.id =  hashMapId3
                        }
                        uploadList.add(entity3)
                        val entity4 = BccyjgEntity()
                        entity4.dycy = et_xnwl_dycy!!.text.toString()
                        entity4.zzy = et_xnwl_zzy!!.text.toString()
                        entity4.zzyls = et_xnwl_ls!!.text.toString()
                        entity4.zzysc = et_xnwl_sc!!.text.toString()
                        entity4.ly = et_xnwl_ly!!.text.toString()
                        entity4.lylg = et_xnwl_lg!!.text.toString()
                        entity4.lysg = et_xnwl_sg!!.text.toString()
                        entity4.my = et_xnwl_my!!.text.toString()
                        entity4.yy = et_xnwl_yy!!.text.toString()
                        entity4.decy = et_xnwl_decy!!.text.toString()
                        entity4.dscy = et_xnwl_dscy!!.text.toString()
                        entity4.dscms = et_xnwl_ms!!.text.toString()
                        entity4.dscfd = et_xnwl_fd!!.text.toString()
//                        entity4.dscqt = et_xnwl_qt.text.toString()
                        entity4.scfw = et_xnwl_scxfw!!.text.toString()
                        entity4.shfw = et_xnwl_shxfw!!.text.toString()
                        entity4.lx = 4
                        entity4.years = tv_pop_add_cyjg.text.toString()
                        entity4.code = AppCache.getInstance().code
                        entity4.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity4.process = 3
                        if (hashMapId4 != 0L){
                            entity4.id =  hashMapId4
                        }
                        uploadList.add(entity4)
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            uploadList.get(0).cunname = et_czrname.text.toString()
                            uploadList.get(1).cunname = et_czrname.text.toString()
                            uploadList.get(2).cunname = et_czrname.text.toString()
                            uploadList.get(3).cunname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateCyjg(uploadList)
                        }

                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        uploadList.clear()
                        val entity1 = BccyjgEntity()
//                        entity1.dycy = et_zycp_dycy.text.toString()
//                        entity1.zzy = et_zycp_zzy.text.toString()
                        entity1.zzyls = et_zycp_ls.text.toString()
                        entity1.zzysc = et_zycp_sc.text.toString()
//                        entity1.ly = et_zycp_ly.text.toString()
                        entity1.lylg = et_zycp_lg.text.toString()
                        entity1.lysg = et_zycp_sg.text.toString()
                        entity1.my = et_zycp_my.text.toString()
                        entity1.yy = et_zycp_yy.text.toString()
                        entity1.decy = et_zycp_decy.text.toString()
//                        entity1.dscy = et_zycp_dscy.text.toString()
                        entity1.dscms = et_zycp_ms.text.toString()
                        entity1.dscfd = et_zycp_fd.text.toString()
//                        entity1.dscqt = et_zycp_qt.text.toString()
                        entity1.scfw = et_zycp_scxfw.text.toString()
                        entity1.shfw = et_zycp_shxfw.text.toString()
                        entity1.lx = 1
                        entity1.years = tv_pop_add_cyjg.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.process = 1
                        if (hashMapId1 != 0L){
                            entity1.id =  hashMapId1
                        }
                        uploadList.add(entity1)
                        val entity2 = BccyjgEntity()
                        entity2.dycy = et_cz_dycy!!.text.toString()
                        entity2.zzy = et_cz_zzy!!.text.toString()
                        entity2.zzyls = et_cz_ls!!.text.toString()
                        entity2.zzysc = et_cz_sc!!.text.toString()
                        entity2.ly = et_cz_ly!!.text.toString()
                        entity2.lylg = et_cz_lg!!.text.toString()
                        entity2.lysg = et_cz_sg!!.text.toString()
                        entity2.my = et_cz_my!!.text.toString()
                        entity2.yy = et_cz_yy!!.text.toString()
                        entity2.decy = et_cz_decy!!.text.toString()
                        entity2.dscy = et_cz_dscy!!.text.toString()
                        entity2.dscms = et_cz_ms!!.text.toString()
                        entity2.dscfd = et_cz_fd!!.text.toString()
//                        entity2.dscqt = et_cz_qt.text.toString()
                        entity2.scfw = et_cz_scxfw!!.text.toString()
                        entity2.shfw = et_cz_shxfw!!.text.toString()
                        entity2.lx = 2
                        entity2.years = tv_pop_add_cyjg.text.toString()
                        entity2.code = AppCache.getInstance().code
                        entity2.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity2.process = 1
                        if (hashMapId2 != 0L){
                            entity2.id =  hashMapId2
                        }
                        uploadList.add(entity2)
                        val entity3 = BccyjgEntity()
                        entity3.dycy = et_xnbc_dycy!!.text.toString()
                        entity3.zzy = et_xnbc_zzy!!.text.toString()
                        entity3.zzyls = et_xnbc_ls!!.text.toString()
                        entity3.zzysc = et_xnbc_sc!!.text.toString()
                        entity3.ly = et_xnbc_ly!!.text.toString()
                        entity3.lylg = et_xnbc_lg!!.text.toString()
                        entity3.lysg = et_xnbc_sg!!.text.toString()
                        entity3.my = et_xnbc_my!!.text.toString()
                        entity3.yy = et_xnbc_yy!!.text.toString()
                        entity3.decy = et_xnbc_decy!!.text.toString()
                        entity3.dscy = et_xnbc_dscy!!.text.toString()
                        entity3.dscms = et_xnbc_ms!!.text.toString()
                        entity3.dscfd = et_xnbc_fd!!.text.toString()
//                        entity3.dscqt = et_xnbc_qt.text.toString()
                        entity3.scfw = et_xnbc_scxfw!!.text.toString()
                        entity3.shfw = et_xnbc_shxfw!!.text.toString()
                        entity3.lx = 3
                        entity3.years = tv_pop_add_cyjg.text.toString()
                        entity3.code = AppCache.getInstance().code
                        entity3.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity3.process = 1
                        if (hashMapId3 != 0L){
                            entity3.id =  hashMapId3
                        }
                        uploadList.add(entity3)
                        val entity4 = BccyjgEntity()
                        entity4.dycy = et_xnwl_dycy!!.text.toString()
                        entity4.zzy = et_xnwl_zzy!!.text.toString()
                        entity4.zzyls = et_xnwl_ls!!.text.toString()
                        entity4.zzysc = et_xnwl_sc!!.text.toString()
                        entity4.ly = et_xnwl_ly!!.text.toString()
                        entity4.lylg = et_xnwl_lg!!.text.toString()
                        entity4.lysg = et_xnwl_sg!!.text.toString()
                        entity4.my = et_xnwl_my!!.text.toString()
                        entity4.yy = et_xnwl_yy!!.text.toString()
                        entity4.decy = et_xnwl_decy!!.text.toString()
                        entity4.dscy = et_xnwl_dscy!!.text.toString()
                        entity4.dscms = et_xnwl_ms!!.text.toString()
                        entity4.dscfd = et_xnwl_fd!!.text.toString()
//                        entity4.dscqt = et_xnwl_qt.text.toString()
                        entity4.scfw = et_xnwl_scxfw!!.text.toString()
                        entity4.shfw = et_xnwl_shxfw!!.text.toString()
                        entity4.lx = 4
                        entity4.years = tv_pop_add_cyjg.text.toString()
                        entity4.code = AppCache.getInstance().code
                        entity4.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity4.process = 1
                        if (hashMapId4 != 0L){
                            entity4.id =  hashMapId4
                        }
                        uploadList.add(entity4)

                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            uploadList.get(0).zhenname = et_czrname.text.toString()
                            uploadList.get(1).zhenname = et_czrname.text.toString()
                            uploadList.get(2).zhenname = et_czrname.text.toString()
                            uploadList.get(3).zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            uploadList.get(0).bcrejectedEntities.add(bcrejectedEntity)
                            uploadList.get(1).bcrejectedEntities.add(bcrejectedEntity)
                            uploadList.get(2).bcrejectedEntities.add(bcrejectedEntity)
                            uploadList.get(3).bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateCyjg(uploadList)
                        }

                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        uploadList.clear()
                        val entity1 = BccyjgEntity()
//                        entity1.dycy = et_zycp_dycy.text.toString()
//                        entity1.zzy = et_zycp_zzy.text.toString()
                        entity1.zzyls = et_zycp_ls.text.toString()
                        entity1.zzysc = et_zycp_sc.text.toString()
//                        entity1.ly = et_zycp_ly.text.toString()
                        entity1.lylg = et_zycp_lg.text.toString()
                        entity1.lysg = et_zycp_sg.text.toString()
                        entity1.my = et_zycp_my.text.toString()
                        entity1.yy = et_zycp_yy.text.toString()
                        entity1.decy = et_zycp_decy.text.toString()
//                        entity1.dscy = et_zycp_dscy.text.toString()
                        entity1.dscms = et_zycp_ms.text.toString()
                        entity1.dscfd = et_zycp_fd.text.toString()
//                        entity1.dscqt = et_zycp_qt.text.toString()
                        entity1.scfw = et_zycp_scxfw.text.toString()
                        entity1.shfw = et_zycp_shxfw.text.toString()
                        entity1.lx = 1
                        entity1.years = tv_pop_add_cyjg.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.process = 1
                        if (hashMapId1 != 0L){
                        entity1.id =  hashMapId1
                    }
                        uploadList.add(entity1)
                        val entity2 = BccyjgEntity()
                        entity2.dycy = et_cz_dycy!!.text.toString()
                        entity2.zzy = et_cz_zzy!!.text.toString()
                        entity2.zzyls = et_cz_ls!!.text.toString()
                        entity2.zzysc = et_cz_sc!!.text.toString()
                        entity2.ly = et_cz_ly!!.text.toString()
                        entity2.lylg = et_cz_lg!!.text.toString()
                        entity2.lysg = et_cz_sg!!.text.toString()
                        entity2.my = et_cz_my!!.text.toString()
                        entity2.yy = et_cz_yy!!.text.toString()
                        entity2.decy = et_cz_decy!!.text.toString()
                        entity2.dscy = et_cz_dscy!!.text.toString()
                        entity2.dscms = et_cz_ms!!.text.toString()
                        entity2.dscfd = et_cz_fd!!.text.toString()
//                        entity2.dscqt = et_cz_qt.text.toString()
                        entity2.scfw = et_cz_scxfw!!.text.toString()
                        entity2.shfw = et_cz_shxfw!!.text.toString()
                        entity2.lx = 2
                        entity2.years = tv_pop_add_cyjg.text.toString()
                        entity2.code = AppCache.getInstance().code
                        entity2.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity2.process = 1
                        if (hashMapId2 != 0L){
                            entity2.id =  hashMapId2
                        }
                        uploadList.add(entity2)
                        val entity3 = BccyjgEntity()
                        entity3.dycy = et_xnbc_dycy!!.text.toString()
                        entity3.zzy = et_xnbc_zzy!!.text.toString()
                        entity3.zzyls = et_xnbc_ls!!.text.toString()
                        entity3.zzysc = et_xnbc_sc!!.text.toString()
                        entity3.ly = et_xnbc_ly!!.text.toString()
                        entity3.lylg = et_xnbc_lg!!.text.toString()
                        entity3.lysg = et_xnbc_sg!!.text.toString()
                        entity3.my = et_xnbc_my!!.text.toString()
                        entity3.yy = et_xnbc_yy!!.text.toString()
                        entity3.decy = et_xnbc_decy!!.text.toString()
                        entity3.dscy = et_xnbc_dscy!!.text.toString()
                        entity3.dscms = et_xnbc_ms!!.text.toString()
                        entity3.dscfd = et_xnbc_fd!!.text.toString()
//                        entity3.dscqt = et_xnbc_qt.text.toString()
                        entity3.scfw = et_xnbc_scxfw!!.text.toString()
                        entity3.shfw = et_xnbc_shxfw!!.text.toString()
                        entity3.lx = 3
                        entity3.years = tv_pop_add_cyjg.text.toString()
                        entity3.code = AppCache.getInstance().code
                        entity3.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity3.process = 1
                        if (hashMapId3 != 0L){
                            entity3.id =  hashMapId3
                        }
                        uploadList.add(entity3)
                        val entity4 = BccyjgEntity()
                        entity4.dycy = et_xnwl_dycy!!.text.toString()
                        entity4.zzy = et_xnwl_zzy!!.text.toString()
                        entity4.zzyls = et_xnwl_ls!!.text.toString()
                        entity4.zzysc = et_xnwl_sc!!.text.toString()
                        entity4.ly = et_xnwl_ly!!.text.toString()
                        entity4.lylg = et_xnwl_lg!!.text.toString()
                        entity4.lysg = et_xnwl_sg!!.text.toString()
                        entity4.my = et_xnwl_my!!.text.toString()
                        entity4.yy = et_xnwl_yy!!.text.toString()
                        entity4.decy = et_xnwl_decy!!.text.toString()
                        entity4.dscy = et_xnwl_dscy!!.text.toString()
                        entity4.dscms = et_xnwl_ms!!.text.toString()
                        entity4.dscfd = et_xnwl_fd!!.text.toString()
//                        entity4.dscqt = et_xnwl_qt.text.toString()
                        entity4.scfw = et_xnwl_scxfw!!.text.toString()
                        entity4.shfw = et_xnwl_shxfw!!.text.toString()
                        entity4.lx = 4
                        entity4.years = tv_pop_add_cyjg.text.toString()
                        entity4.code = AppCache.getInstance().code
                        entity4.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity4.process = 1
                        if (hashMapId4 != 0L){
                            entity4.id =  hashMapId4
                        }
                        uploadList.add(entity4)
                        if (hashMapId1 == 0L){
                            if (et_czrname.text.toString().equals("")){
                                ToastUtils.showShort("请输入操作员姓名！")
                            }/*else if (TdlyUtils.setEditToast(et_zycp_ls,et_zycp_sc,et_zycp_lg,et_zycp_sg,et_zycp_my,et_zycp_yy,et_zycp_decy,et_zycp_ms,et_zycp_fd
                                            ,et_cz_dycy,et_cz_zzy,et_cz_ls,et_cz_sc,et_cz_ly,et_cz_lg,et_cz_sg,et_cz_my,et_cz_yy,et_cz_decy,et_cz_dscy,et_cz_ms,et_cz_fd
                                            ,et_xnbc_dycy,et_xnbc_zzy,et_xnbc_ls,et_xnbc_sc,et_xnbc_ly,et_xnbc_lg,et_xnbc_sg,et_xnbc_my,et_xnbc_yy,et_xnbc_decy,et_xnbc_dscy,et_xnbc_ms,et_xnbc_fd
                                            ,et_xnwl_dycy,et_xnwl_zzy,et_xnwl_ls,et_xnwl_sc,et_xnwl_ly,et_xnwl_lg,et_xnwl_sg,et_xnwl_my,et_xnwl_yy,et_xnwl_decy,et_xnwl_dscy,et_xnwl_ms,et_xnwl_fd
                                            )){
                            }*/else{
                                uploadList.get(0).cunname = et_czrname.text.toString()
                                uploadList.get(1).cunname = et_czrname.text.toString()
                                uploadList.get(2).cunname = et_czrname.text.toString()
                                uploadList.get(3).cunname = et_czrname.text.toString()
                                mPresenter.getBcjcAddCyjg(uploadList)
                            }
                        }else{
                            //修改接口
                            uploadList.get(0).id = hashMapId1
                            uploadList.get(1).id = hashMapId2
                            uploadList.get(2).id = hashMapId3
                            uploadList.get(3).id = hashMapId4
                            if (et_czrname.text.toString().equals("")){
                                ToastUtils.showShort("请输入操作员姓名！")
                            }/*else if (TdlyUtils.setEditToast(et_zycp_ls,et_zycp_sc,et_zycp_lg,et_zycp_sg,et_zycp_my,et_zycp_yy,et_zycp_decy,et_zycp_ms,et_zycp_fd
                                            ,et_cz_dycy,et_cz_zzy,et_cz_ls,et_cz_sc,et_cz_ly,et_cz_lg,et_cz_sg,et_cz_my,et_cz_yy,et_cz_decy,et_cz_dscy,et_cz_ms,et_cz_fd
                                            ,et_xnbc_dycy,et_xnbc_zzy,et_xnbc_ls,et_xnbc_sc,et_xnbc_ly,et_xnbc_lg,et_xnbc_sg,et_xnbc_my,et_xnbc_yy,et_xnbc_decy,et_xnbc_dscy,et_xnbc_ms,et_xnbc_fd
                                            ,et_xnwl_dycy,et_xnwl_zzy,et_xnwl_ls,et_xnwl_sc,et_xnwl_ly,et_xnwl_lg,et_xnwl_sg,et_xnwl_my,et_xnwl_yy,et_xnwl_decy,et_xnwl_dscy,et_xnwl_ms,et_xnwl_fd
                                    )){
                            }*/else{
                                uploadList.get(0).cunname = et_czrname.text.toString()
                                uploadList.get(1).cunname = et_czrname.text.toString()
                                uploadList.get(2).cunname = et_czrname.text.toString()
                                uploadList.get(3).cunname = et_czrname.text.toString()
                                mPresenter.getBcjcUpdateCyjg(uploadList)
                            }
                        }
                    }
                    //村 下一步
                    confirm.setOnClickListener {
                        uploadList.clear()
                        val entity1 = BccyjgEntity()
//                        entity1.dycy = et_zycp_dycy.text.toString()
//                        entity1.zzy = et_zycp_zzy.text.toString()
                        entity1.zzyls = et_zycp_ls.text.toString()
                        entity1.zzysc = et_zycp_sc.text.toString()
//                        entity1.ly = et_zycp_ly.text.toString()
                        entity1.lylg = et_zycp_lg.text.toString()
                        entity1.lysg = et_zycp_sg.text.toString()
                        entity1.my = et_zycp_my.text.toString()
                        entity1.yy = et_zycp_yy.text.toString()
                        entity1.decy = et_zycp_decy.text.toString()
//                        entity1.dscy = et_zycp_dscy.text.toString()
                        entity1.dscms = et_zycp_ms.text.toString()
                        entity1.dscfd = et_zycp_fd.text.toString()
//                        entity1.dscqt = et_zycp_qt.text.toString()
                        entity1.scfw = et_zycp_scxfw.text.toString()
                        entity1.shfw = et_zycp_shxfw.text.toString()
                        entity1.lx = 1
                        entity1.years = tv_pop_add_cyjg.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.process = 2
                        if (hashMapId1 != 0L){
                            entity1.id =  hashMapId1
                        }
                        uploadList.add(entity1)
                        val entity2 = BccyjgEntity()
                        entity2.dycy = et_cz_dycy!!.text.toString()
                        entity2.zzy = et_cz_zzy!!.text.toString()
                        entity2.zzyls = et_cz_ls!!.text.toString()
                        entity2.zzysc = et_cz_sc!!.text.toString()
                        entity2.ly = et_cz_ly!!.text.toString()
                        entity2.lylg = et_cz_lg!!.text.toString()
                        entity2.lysg = et_cz_sg!!.text.toString()
                        entity2.my = et_cz_my!!.text.toString()
                        entity2.yy = et_cz_yy!!.text.toString()
                        entity2.decy = et_cz_decy!!.text.toString()
                        entity2.dscy = et_cz_dscy!!.text.toString()
                        entity2.dscms = et_cz_ms!!.text.toString()
                        entity2.dscfd = et_cz_fd!!.text.toString()
//                        entity2.dscqt = et_cz_qt.text.toString()
                        entity2.scfw = et_cz_scxfw!!.text.toString()
                        entity2.shfw = et_cz_shxfw!!.text.toString()
                        entity2.lx = 2
                        entity2.years = tv_pop_add_cyjg.text.toString()
                        entity2.code = AppCache.getInstance().code
                        entity2.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity2.process = 2
                        if (hashMapId2 != 0L){
                            entity2.id =  hashMapId2
                        }
                        uploadList.add(entity2)
                        val entity3 = BccyjgEntity()
                        entity3.dycy = et_xnbc_dycy!!.text.toString()
                        entity3.zzy = et_xnbc_zzy!!.text.toString()
                        entity3.zzyls = et_xnbc_ls!!.text.toString()
                        entity3.zzysc = et_xnbc_sc!!.text.toString()
                        entity3.ly = et_xnbc_ly!!.text.toString()
                        entity3.lylg = et_xnbc_lg!!.text.toString()
                        entity3.lysg = et_xnbc_sg!!.text.toString()
                        entity3.my = et_xnbc_my!!.text.toString()
                        entity3.yy = et_xnbc_yy!!.text.toString()
                        entity3.decy = et_xnbc_decy!!.text.toString()
                        entity3.dscy = et_xnbc_dscy!!.text.toString()
                        entity3.dscms = et_xnbc_ms!!.text.toString()
                        entity3.dscfd = et_xnbc_fd!!.text.toString()
//                        entity3.dscqt = et_xnbc_qt.text.toString()
                        entity3.scfw = et_xnbc_scxfw!!.text.toString()
                        entity3.shfw = et_xnbc_shxfw!!.text.toString()
                        entity3.lx = 3
                        entity3.years = tv_pop_add_cyjg.text.toString()
                        entity3.code = AppCache.getInstance().code
                        entity3.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity3.process = 2
                        if (hashMapId3 != 0L){
                            entity3.id =  hashMapId3
                        }
                        uploadList.add(entity3)
                        val entity4 = BccyjgEntity()
                        entity4.dycy = et_xnwl_dycy!!.text.toString()
                        entity4.zzy = et_xnwl_zzy!!.text.toString()
                        entity4.zzyls = et_xnwl_ls!!.text.toString()
                        entity4.zzysc = et_xnwl_sc!!.text.toString()
                        entity4.ly = et_xnwl_ly!!.text.toString()
                        entity4.lylg = et_xnwl_lg!!.text.toString()
                        entity4.lysg = et_xnwl_sg!!.text.toString()
                        entity4.my = et_xnwl_my!!.text.toString()
                        entity4.yy = et_xnwl_yy!!.text.toString()
                        entity4.decy = et_xnwl_decy!!.text.toString()
                        entity4.dscy = et_xnwl_dscy!!.text.toString()
                        entity4.dscms = et_xnwl_ms!!.text.toString()
                        entity4.dscfd = et_xnwl_fd!!.text.toString()
//                        entity4.dscqt = et_xnwl_qt.text.toString()
                        entity4.scfw = et_xnwl_scxfw!!.text.toString()
                        entity4.shfw = et_xnwl_shxfw!!.text.toString()
                        entity4.lx = 4
                        entity4.years = tv_pop_add_cyjg.text.toString()
                        entity4.code = AppCache.getInstance().code
                        entity4.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity4.process = 2
                        if (hashMapId4 != 0L){
                            entity4.id =  hashMapId4
                        }
                        uploadList.add(entity4)

                        if (hashMapId1 == 0L){
                            if (et_czrname.text.toString().equals("")){
                                ToastUtils.showShort("请输入操作员姓名！")
                            }else if (TdlyUtils.setEditToast(et_zycp_ls,et_zycp_sc,et_zycp_lg,et_zycp_sg,et_zycp_my,et_zycp_yy,et_zycp_decy,et_zycp_ms,et_zycp_fd
                                            ,et_cz_dycy,et_cz_zzy,et_cz_ls,et_cz_sc,et_cz_ly,et_cz_lg,et_cz_sg,et_cz_my,et_cz_yy,et_cz_decy,et_cz_dscy,et_cz_ms,et_cz_fd
                                            ,et_xnbc_dycy,et_xnbc_zzy,et_xnbc_ls,et_xnbc_sc,et_xnbc_ly,et_xnbc_lg,et_xnbc_sg,et_xnbc_my,et_xnbc_yy,et_xnbc_decy,et_xnbc_dscy,et_xnbc_ms,et_xnbc_fd
                                            ,et_xnwl_dycy,et_xnwl_zzy,et_xnwl_ls,et_xnwl_sc,et_xnwl_ly,et_xnwl_lg,et_xnwl_sg,et_xnwl_my,et_xnwl_yy,et_xnwl_decy,et_xnwl_dscy,et_xnwl_ms,et_xnwl_fd
                                    )){
                            }else{
                                uploadList.get(0).cunname = et_czrname.text.toString()
                                uploadList.get(1).cunname = et_czrname.text.toString()
                                uploadList.get(2).cunname = et_czrname.text.toString()
                                uploadList.get(3).cunname = et_czrname.text.toString()
                                mPresenter.getBcjcAddCyjg(uploadList)
                            }
                        }else{
                            //修改接口
                            uploadList.get(0).id = hashMapId1
                            uploadList.get(1).id = hashMapId2
                            uploadList.get(2).id = hashMapId3
                            uploadList.get(3).id = hashMapId4
                            if (et_czrname.text.toString().equals("")){
                                ToastUtils.showShort("请输入操作员姓名！")
                            }else if (TdlyUtils.setEditToast(et_zycp_ls,et_zycp_sc,et_zycp_lg,et_zycp_sg,et_zycp_my,et_zycp_yy,et_zycp_decy,et_zycp_ms,et_zycp_fd
                                            ,et_cz_dycy,et_cz_zzy,et_cz_ls,et_cz_sc,et_cz_ly,et_cz_lg,et_cz_sg,et_cz_my,et_cz_yy,et_cz_decy,et_cz_dscy,et_cz_ms,et_cz_fd
                                            ,et_xnbc_dycy,et_xnbc_zzy,et_xnbc_ls,et_xnbc_sc,et_xnbc_ly,et_xnbc_lg,et_xnbc_sg,et_xnbc_my,et_xnbc_yy,et_xnbc_decy,et_xnbc_dscy,et_xnbc_ms,et_xnbc_fd
                                            ,et_xnwl_dycy,et_xnwl_zzy,et_xnwl_ls,et_xnwl_sc,et_xnwl_ly,et_xnwl_lg,et_xnwl_sg,et_xnwl_my,et_xnwl_yy,et_xnwl_decy,et_xnwl_dscy,et_xnwl_ms,et_xnwl_fd
                                    )){
                            }else{
                                uploadList.get(0).cunname = et_czrname.text.toString()
                                uploadList.get(1).cunname = et_czrname.text.toString()
                                uploadList.get(2).cunname = et_czrname.text.toString()
                                uploadList.get(3).cunname = et_czrname.text.toString()
                                mPresenter.getBcjcUpdateCyjg(uploadList)
                            }
                        }
                    }
                }
            }

            close.setOnClickListener {
                addCyjgUpPopu!!.dismiss()
            }
            }
        }
    }

    /*private fun listener(view: View): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when (view.id) {
                    R.id.pop_add_cyjg_et_cz_ls -> {//
                        et_cz_dycy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc,et_cz_lg
                                ,et_cz_sg,et_cz_my,et_cz_yy).toString())
                        et_cz_zzy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                    }
                    R.id.pop_add_cyjg_et_cz_sc -> {//
                        et_cz_dycy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc,et_cz_lg
                                ,et_cz_sg,et_cz_my,et_cz_yy).toString())
                        et_cz_zzy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc).toString())
                    }
                    R.id.pop_add_cyjg_et_cz_lg -> {//
                        et_cz_dycy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc,et_cz_lg
                                ,et_cz_sg,et_cz_my,et_cz_yy).toString())
                        et_cz_ly!!.setText(TdlyUtils.getSum(et_cz_lg,et_cz_sg).toString())
                    }
                    R.id.pop_add_cyjg_et_cz_sg -> {//
                        et_cz_dycy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc,et_cz_lg
                                ,et_cz_sg,et_cz_my,et_cz_yy).toString())
                        et_cz_ly!!.setText(TdlyUtils.getSum(et_cz_lg,et_cz_sg).toString())
                    }
                    R.id.pop_add_cyjg_et_cz_my -> {//
                        et_cz_dycy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc,et_cz_lg
                                ,et_cz_sg,et_cz_my,et_cz_yy).toString())
                    }
                    R.id.pop_add_cyjg_et_cz_yy -> {//
                        et_cz_dycy!!.setText(TdlyUtils.getSum(et_cz_ls,et_cz_sc,et_cz_lg
                                ,et_cz_sg,et_cz_my,et_cz_yy).toString())
                    }
                    R.id.pop_add_cyjg_et_cz_ms -> {//
                        et_cz_dscy!!.setText(TdlyUtils.getSum(et_cz_ms,et_cz_fd).toString())
                    }
                    R.id.pop_add_cyjg_et_cz_fd -> {//
                        et_cz_dscy!!.setText(TdlyUtils.getSum(et_cz_ms,et_cz_fd).toString())
                    }


                    R.id.pop_add_cyjg_et_xnbc_ls -> {//
                        et_xnbc_dycy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc,et_xnbc_lg
                                ,et_xnbc_sg,et_xnbc_my,et_xnbc_yy).toString())
                        et_xnbc_zzy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                    }
                    R.id.pop_add_cyjg_et_xnbc_sc -> {//
                        et_xnbc_dycy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc,et_xnbc_lg
                                ,et_xnbc_sg,et_xnbc_my,et_xnbc_yy).toString())
                        et_xnbc_zzy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc).toString())
                    }
                    R.id.pop_add_cyjg_et_xnbc_lg -> {//
                        et_xnbc_dycy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc,et_xnbc_lg
                                ,et_xnbc_sg,et_xnbc_my,et_xnbc_yy).toString())
                        et_xnbc_ly!!.setText(TdlyUtils.getSum(et_xnbc_lg,et_xnbc_sg).toString())
                    }
                    R.id.pop_add_cyjg_et_xnbc_sg -> {//
                        et_xnbc_dycy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc,et_xnbc_lg
                                ,et_xnbc_sg,et_xnbc_my,et_xnbc_yy).toString())
                        et_xnbc_ly!!.setText(TdlyUtils.getSum(et_xnbc_lg,et_xnbc_sg).toString())
                    }
                    R.id.pop_add_cyjg_et_xnbc_my -> {//
                        et_xnbc_dycy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc,et_xnbc_lg
                                ,et_xnbc_sg,et_xnbc_my,et_xnbc_yy).toString())
                    }
                    R.id.pop_add_cyjg_et_xnbc_yy -> {//
                        et_xnbc_dycy!!.setText(TdlyUtils.getSum(et_xnbc_ls,et_xnbc_sc,et_xnbc_lg
                                ,et_xnbc_sg,et_xnbc_my,et_xnbc_yy).toString())
                    }
                    R.id.pop_add_cyjg_et_xnbc_ms -> {//
                        et_xnbc_dscy!!.setText(TdlyUtils.getSum(et_xnbc_ms,et_xnbc_fd).toString())
                    }
                    R.id.pop_add_cyjg_et_xnbc_fd -> {//
                        et_xnbc_dscy!!.setText(TdlyUtils.getSum(et_xnbc_ms,et_xnbc_fd).toString())
                    }


                    R.id.pop_add_cyjg_et_xnwl_ls -> {//
                        et_xnwl_dycy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc,et_xnwl_lg
                                ,et_xnwl_sg,et_xnwl_my,et_xnwl_yy).toString())
                        et_xnwl_zzy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc).toString())
//                            tdly_gd_zdmj = s!!.isNotEmpty()
                    }
                    R.id.pop_add_cyjg_et_xnwl_sc -> {//
                        et_xnwl_dycy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc,et_xnwl_lg
                                ,et_xnwl_sg,et_xnwl_my,et_xnwl_yy).toString())
                        et_xnwl_zzy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc).toString())
                    }
                    R.id.pop_add_cyjg_et_xnwl_lg -> {//
                        et_xnwl_dycy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc,et_xnwl_lg
                                ,et_xnwl_sg,et_xnwl_my,et_xnwl_yy).toString())
                        et_xnwl_ly!!.setText(TdlyUtils.getSum(et_xnwl_lg,et_xnwl_sg).toString())
                    }
                    R.id.pop_add_cyjg_et_xnwl_sg -> {//
                        et_xnwl_dycy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc,et_xnwl_lg
                                ,et_xnwl_sg,et_xnwl_my,et_xnwl_yy).toString())
                        et_xnwl_ly!!.setText(TdlyUtils.getSum(et_xnwl_lg,et_xnwl_sg).toString())
                    }
                    R.id.pop_add_cyjg_et_xnwl_my -> {//
                        et_xnwl_dycy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc,et_xnwl_lg
                                ,et_xnwl_sg,et_xnwl_my,et_xnwl_yy).toString())
                    }
                    R.id.pop_add_cyjg_et_xnwl_yy -> {//
                        et_xnwl_dycy!!.setText(TdlyUtils.getSum(et_xnwl_ls,et_xnwl_sc,et_xnwl_lg
                                ,et_xnwl_sg,et_xnwl_my,et_xnwl_yy).toString())
                    }
                    R.id.pop_add_cyjg_et_xnwl_ms -> {//
                        et_xnwl_dscy!!.setText(TdlyUtils.getSum(et_xnwl_ms,et_xnwl_fd).toString())
                    }
                    R.id.pop_add_cyjg_et_xnwl_fd -> {//
                        et_xnwl_dscy!!.setText(TdlyUtils.getSum(et_xnwl_ms,et_xnwl_fd).toString())
                    }

                }


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }*/

    override fun returnBcjcUpdateCyjg(msg: String) {
        ToastUtils.showShort(msg)
        uploadList.clear()
        if (addCyjgUpPopu!=null){
            addCyjgUpPopu!!.dismiss()
        }
        mPresenter.getBcjcCyjg(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCyjgAll(AppCache.getInstance().code,yearsListNull)
    }
    override fun returnHbjcList(cash: List<PjEnviorSupvsEntity>, roleid: Int, totalCount: Int) {
    }

    override fun returnEnviorSupvsQueryPoint(ylPointEntity: WtlrBean) {
    }

    override fun onHbLr(ylPointEntity: String) {
    }

    override fun returnQueryCun(message: List<CjEntity>, pjEnviorSupvsEntity: PjEnviorSupvsEntity, rlv_xzxzq: RecyclerView, tvWzenvironmental: EditText) {
    }

    override fun returnCountCyCode(message: EnviorCunBean.DataBean) {

    }

    override fun onHbjcZl(ylPointEntity: List<EnviorCunBean.DataBean>) {
    }

    override fun returnRjhjjcPoint(ylPointEntity: RjhjPointBean) {
    }

    override fun onDdLr(enviorSupvsEntity: String) {
    }

    override fun returnHbjcListCode(ylPointEntity: List<PjEnviorSupvsEntity>, roleid: Int, totalCount: Int) {

    }

    override fun onDdList(message: List<PointRecordEntity>) {

    }
    private var onClickShzl: OnClickShzlLister? = null
    fun setOnClickShzlLister(onClickShzl:OnClickShzlLister){
        this.onClickShzl = onClickShzl
    }
    interface OnClickShzlLister {
        fun onClick(position: String)
    }

    override fun returnEnviorFileQueryList(message: List<EnviorFileFhEntity>, type: String, xmin: BigDecimal, xmax: BigDecimal, ymin: BigDecimal, ymax: BigDecimal, isImage: Int) {
    }

    private fun setTime(editText: TextView) {//选择时间  EditText
        val year = Calendar.getInstance()[Calendar.YEAR]
        val month = Calendar.getInstance()[Calendar.MONTH]
        val day = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
            var nian = ""
            var yue = ""
            var ri = ""
            nian = i.toString() + ""
            yue = if (i1 < 9) {
                "0" + (i1 + 1)
            } else {
                "" + (i1 + 1)
            }
            ri = if (i2 < 10) {
                "0$i2"
            } else {
                "" + i2
            }
            editText.setText(nian+"-"+yue+"-"+ri)
        }, year, month, day)
        val datePicker = datePickerDialog.datePicker
        datePicker.maxDate = System.currentTimeMillis()+1000 ///< 设置日期的上限日期
        datePickerDialog.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) // && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)

    }

    var hashMap = HashMap<String, ArrayList<BccyjgEntity>>()
    override fun returnBchcCyjgAll(msg: HashMap<String, ArrayList<BccyjgEntity>>) {
        hashMap.clear()
        hashMap = msg
    }

    override fun returnBchcCyjg(msg: HashMap<String,ArrayList<BccyjgEntity>>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addCyjgUpPopu!=null){
            addCyjgUpPopu!!.dismiss()
        }
        if (msg!=null){
            rlv_frag_cyjg_zycp.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_cyjg_cz.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_cyjg_xnbcjy.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_cyjg_xnwbjy.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

            val zycpList = msg.get("zycp")
            val czList = msg.get("cz")
            val xnbcjyList = msg.get("xnbcjy")
            val xnwljyList = msg.get("xnwljy")

            rlv_frag_cyjg_zycp.adapter = object :BaseQuickAdapter<BccyjgEntity, BaseViewHolder>(R.layout.item_bcjc_cyjg,zycpList){
                override fun convert(helper: BaseViewHolder?, item: BccyjgEntity?) {
                    helper!!.setText(R.id.item_bcjc_cyjg_year,item!!.years)
                            .setText(R.id.item_bcjc_cyjg_dycy,"--")//item.dycy
                            .setText(R.id.item_bcjc_cyjg_zzy,"--")//item.zzy
                            .setText(R.id.item_bcjc_cyjg_ls,item.zzyls)
                            .setText(R.id.item_bcjc_cyjg_sc,item.zzysc)
                            .setText(R.id.item_bcjc_cyjg_ly,"--")//item.ly
                            .setText(R.id.item_bcjc_cyjg_lg,item.lylg)
                            .setText(R.id.item_bcjc_cyjg_sg,item.lysg)
                            .setText(R.id.item_bcjc_cyjg_my,item.my)
                            .setText(R.id.item_bcjc_cyjg_yy,item.yy)
                            .setText(R.id.item_bcjc_cyjg_decy,item.decy)
                            .setText(R.id.item_bcjc_cyjg_dscy,"--")//item.dscy
                            .setText(R.id.item_bcjc_cyjg_dscyms,item.dscms)
                            .setText(R.id.item_bcjc_cyjg_dscyfd,item.dscfd)
                            .setText(R.id.item_bcjc_cyjg_dscyqt,item.dscqt)
                            .setText(R.id.item_bcjc_cyjg_scxfw,item.scfw)
                            .setText(R.id.item_bcjc_cyjg_shxfw,item.shfw)
                    val years = helper.getView<TextView>(R.id.item_bcjc_cyjg_year)
                    if (item.bcrejectedEntities.size>0){
//                        years.setTextColor(Color.parseColor("#ff6000"))
//                        years.isEnabled = true
                        tv_frag_cyjg_gzjdbh.visibility = View.VISIBLE
                        iv_frag_cyjg_gzjdbh.visibility = View.VISIBLE
                        tv_frag_cyjg_gzjdbh.setTextColor(Color.parseColor("#1B96EE"))
                    }else{
//                        years.setTextColor(Color.parseColor("#000000"))
//                        years.isEnabled = false
                        tv_frag_cyjg_gzjdbh.visibility = View.GONE
                        iv_frag_cyjg_gzjdbh.visibility = View.GONE
                        tv_frag_cyjg_gzjdbh.setTextColor(Color.parseColor("#24A70E"))
                    }
                    tv_frag_cyjg_gzjdbh.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }
                }


            }
            rlv_frag_cyjg_cz.adapter = object :BaseQuickAdapter<BccyjgEntity, BaseViewHolder>(R.layout.item_bcjc_cyjg,czList){
                override fun convert(helper: BaseViewHolder?, item: BccyjgEntity?) {
                    helper!!.setText(R.id.item_bcjc_cyjg_year,item!!.years)
                            .setText(R.id.item_bcjc_cyjg_dycy,item.dycy)
                            .setText(R.id.item_bcjc_cyjg_zzy,item.zzy)
                            .setText(R.id.item_bcjc_cyjg_ls,item.zzyls)
                            .setText(R.id.item_bcjc_cyjg_sc,item.zzysc)
                            .setText(R.id.item_bcjc_cyjg_ly,item.ly)
                            .setText(R.id.item_bcjc_cyjg_lg,item.lylg)
                            .setText(R.id.item_bcjc_cyjg_sg,item.lysg)
                            .setText(R.id.item_bcjc_cyjg_my,item.my)
                            .setText(R.id.item_bcjc_cyjg_yy,item.yy)
                            .setText(R.id.item_bcjc_cyjg_decy,item.decy)
                            .setText(R.id.item_bcjc_cyjg_dscy,item.dscy)
                            .setText(R.id.item_bcjc_cyjg_dscyms,item.dscms)
                            .setText(R.id.item_bcjc_cyjg_dscyfd,item.dscfd)
                            .setText(R.id.item_bcjc_cyjg_dscyqt,item.dscqt)
                            .setText(R.id.item_bcjc_cyjg_scxfw,item.scfw)
                            .setText(R.id.item_bcjc_cyjg_shxfw,item.shfw)
                    val years = helper.getView<TextView>(R.id.item_bcjc_cyjg_year)
                    /*if (item.bcrejectedEntities.size>0){
                        years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true
                    }else{
                        years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false
                    }
                    years.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }*/
                }


            }
            rlv_frag_cyjg_xnbcjy.adapter = object :BaseQuickAdapter<BccyjgEntity, BaseViewHolder>(R.layout.item_bcjc_cyjg,xnbcjyList){
                override fun convert(helper: BaseViewHolder?, item: BccyjgEntity?) {
                    helper!!.setText(R.id.item_bcjc_cyjg_year,item!!.years)
                            .setText(R.id.item_bcjc_cyjg_dycy,item.dycy)
                            .setText(R.id.item_bcjc_cyjg_zzy,item.zzy)
                            .setText(R.id.item_bcjc_cyjg_ls,item.zzyls)
                            .setText(R.id.item_bcjc_cyjg_sc,item.zzysc)
                            .setText(R.id.item_bcjc_cyjg_ly,item.ly)
                            .setText(R.id.item_bcjc_cyjg_lg,item.lylg)
                            .setText(R.id.item_bcjc_cyjg_sg,item.lysg)
                            .setText(R.id.item_bcjc_cyjg_my,item.my)
                            .setText(R.id.item_bcjc_cyjg_yy,item.yy)
                            .setText(R.id.item_bcjc_cyjg_decy,item.decy)
                            .setText(R.id.item_bcjc_cyjg_dscy,item.dscy)
                            .setText(R.id.item_bcjc_cyjg_dscyms,item.dscms)
                            .setText(R.id.item_bcjc_cyjg_dscyfd,item.dscfd)
                            .setText(R.id.item_bcjc_cyjg_dscyqt,item.dscqt)
                            .setText(R.id.item_bcjc_cyjg_scxfw,item.scfw)
                            .setText(R.id.item_bcjc_cyjg_shxfw,item.shfw)
                    val years = helper.getView<TextView>(R.id.item_bcjc_cyjg_year)
                    /*if (item.bcrejectedEntities.size>0){
                        years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true
                    }else{
                        years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false
                    }
                    years.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }*/
                }


            }
            rlv_frag_cyjg_xnwbjy.adapter = object :BaseQuickAdapter<BccyjgEntity, BaseViewHolder>(R.layout.item_bcjc_cyjg,xnwljyList){
                override fun convert(helper: BaseViewHolder?, item: BccyjgEntity?) {
                    helper!!.setText(R.id.item_bcjc_cyjg_year,item!!.years)
                            .setText(R.id.item_bcjc_cyjg_dycy,item.dycy)
                            .setText(R.id.item_bcjc_cyjg_zzy,item.zzy)
                            .setText(R.id.item_bcjc_cyjg_ls,item.zzyls)
                            .setText(R.id.item_bcjc_cyjg_sc,item.zzysc)
                            .setText(R.id.item_bcjc_cyjg_ly,item.ly)
                            .setText(R.id.item_bcjc_cyjg_lg,item.lylg)
                            .setText(R.id.item_bcjc_cyjg_sg,item.lysg)
                            .setText(R.id.item_bcjc_cyjg_my,item.my)
                            .setText(R.id.item_bcjc_cyjg_yy,item.yy)
                            .setText(R.id.item_bcjc_cyjg_decy,item.decy)
                            .setText(R.id.item_bcjc_cyjg_dscy,item.dscy)
                            .setText(R.id.item_bcjc_cyjg_dscyms,item.dscms)
                            .setText(R.id.item_bcjc_cyjg_dscyfd,item.dscfd)
                            .setText(R.id.item_bcjc_cyjg_dscyqt,item.dscqt)
                            .setText(R.id.item_bcjc_cyjg_scxfw,item.scfw)
                            .setText(R.id.item_bcjc_cyjg_shxfw,item.shfw)
                    val years = helper.getView<TextView>(R.id.item_bcjc_cyjg_year)
                    /*if (item.bcrejectedEntities.size>0){
                        years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true
                    }else{
                        years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false
                    }
                    years.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }*/
                }


            }
            var bccyjgList = ArrayList<BccyjgEntity>()
            if (zycpList!=null&&zycpList.size>0){
                bccyjgList = zycpList;
            }else if (czList!=null&&czList.size>0){
                bccyjgList = czList;
            }else if (xnbcjyList!=null&&xnbcjyList.size>0){
                bccyjgList = xnbcjyList;
            }else if (xnwljyList!=null&&xnwljyList.size>0){
                bccyjgList = xnwljyList;
            }
            if (bccyjgList.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = bccyjgList.get(0)
                when(get.process){
                    1->{
                        tv_frag_cyjg_gzjddsb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_cyjg_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    2->{
                        tv_frag_cyjg_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_cyjg_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    3->{
                        tv_frag_cyjg_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_cyjg_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    4->{
                        tv_frag_cyjg_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_cyjg_gzjdtjcg.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_cyjg_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                }
                tll_frag_zjd_cyjg_ghyczqk.setValueText(sptypeList,get.process-1,get.process-1)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_cyjg_add.visibility = View.VISIBLE
                    tv_frag_cyjg_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_cyjg_add.visibility = View.VISIBLE
                        tv_frag_cyjg_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_cyjg_add.visibility = View.VISIBLE
                            tv_frag_cyjg_add.setText("操作")
                        }else{
                            if (get.process> TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_cyjg_add.setText("已上报")
                            }else{
                                tv_frag_cyjg_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_cyjg_add.visibility = View.GONE
                            }
                        }
            }else if (AppCache.getInstance().type==4){
                tll_frag_zjd_cyjg_ghyczqk.setValueText(sptypeList,-1,-1)
                tv_frag_cyjg_add.visibility = View.VISIBLE
            }else if (AppCache.getInstance().type!=4){
                tll_frag_zjd_cyjg_ghyczqk.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_cyjg_add.visibility = View.GONE
                }
                tv_frag_cyjg_add.setText("未上报")
            }
            if (czList!!.size>0){
                tv_frag_cyjg_ghyczqk.setText(czList!!.get(0).years+"各行业产值情况")

                var bhgsCount2 = ArrayList<Float>()
                var bhgsName2 = ArrayList<String>()
                val colors2 = ArrayList<Int>()
                colors2.add(Color.parseColor("#5B9BD5"))
                colors2.add(Color.parseColor("#4472C4"))
                colors2.add(Color.parseColor("#70AD47"))
                bhgsCount2.add(PieTdxgUtil.getStringToFloat(czList.get(0).dycy))
                bhgsCount2.add(PieTdxgUtil.getStringToFloat(czList.get(0).decy))
                bhgsCount2.add(PieTdxgUtil.getStringToFloat(czList.get(0).dscy))
                bhgsName2.add("第一产业")
                bhgsName2.add("第二产业")
                bhgsName2.add("第三产业")
                val s = PieTdxgUtil.getStringToFloat(czList.get(0).dycy)+
                        PieTdxgUtil.getStringToFloat(czList.get(0).decy) + PieTdxgUtil.getStringToFloat(czList.get(0).dscy)
                if (s<=0f){
                    ll_frag_cyjg_ghyczqk.visibility = View.GONE
                }else{
                    if (AppCache.getInstance().type!=4)
                    ll_frag_cyjg_ghyczqk.visibility = View.VISIBLE
                }
//                PieTdxgUtil.rkinitPie1(pie_frag_cyjg_ghyczqk, "", bhgsCount2, bhgsName2, colors2)//人口就业情况
                PieTdxgUtil.pieRlv(pie_frag_cyjg_ghyczqk, "", bhgsCount2, bhgsName2, colors2,rlv_frag_zjd_cyjg_ghyczqk,activity!!)//人口就业情况

                pie_frag_cyjg_ghyczqk.setOnChartValueSelectedListener(object: OnChartValueSelectedListener {
                    override fun onNothingSelected() {

                    }

                    override fun onValueSelected(e: Entry?, h: Highlight?) {
                        val pieEntry = e as PieEntry
//                    ToastUtils.showShort(pieEntry?.value.toString())
                        if (pieEntry?.label.toString().equals("第一产业")){
                            ll_frag_cyjg_ghyczqkxx.visibility = View.VISIBLE
                            mtl_frag_cyjg_ghyczqkxx.visibility = View.VISIBLE

                        }else{
                            ll_frag_cyjg_ghyczqkxx.visibility = View.GONE
                            mtl_frag_cyjg_ghyczqkxx.visibility = View.GONE

                        }
                    }
                })
                var bhgsCount3 = ArrayList<Float>()
                var bhgsName3 = ArrayList<String>()
                val colors3 = ArrayList<Int>()
                colors3.add(Color.parseColor("#5B9BD5"))
                colors3.add(Color.parseColor("#ED7D31"))
                colors3.add(Color.parseColor("#A5A5A5"))
                colors3.add(Color.parseColor("#FFC000"))
                bhgsCount3.add(PieTdxgUtil.getStringToFloat(czList.get(0).zzy))
                bhgsCount3.add(PieTdxgUtil.getStringToFloat(czList.get(0).ly))
                bhgsCount3.add(PieTdxgUtil.getStringToFloat(czList.get(0).my))
                bhgsCount3.add(PieTdxgUtil.getStringToFloat(czList.get(0).yy))
                bhgsName3.add("种植业")
                bhgsName3.add("林业")
                bhgsName3.add("牧业")
                bhgsName3.add("渔业")
//                PieTdxgUtil.rkinitPie1(pie_frag_cyjg_ghyczqkxx, "", bhgsCount3, bhgsName3, colors3)
                PieTdxgUtil.pieRlv(pie_frag_cyjg_ghyczqkxx, "", bhgsCount3, bhgsName3, colors3,rlv_frag_zjd_cyjg_ghyczqkxx,activity!!)


            }else{
                ll_frag_cyjg_ghyczqk.visibility = View.GONE
            }
        }else{
            ToastUtils.showShort("该年份暂无数据")
            ll_frag_cyjg_ghyczqk.visibility = View.GONE
        }
    }
    //驳回弹出框
    fun rejectUpPopup(list :ArrayList<BcrejectedEntity>){
        rejectUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject_list, ll_frag_shzlrjhj_top)
        val contentView: View = rejectUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_reject_list_rlv)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_reject_list_close)
        CommenPop.backgroundAlpha(0.5f, activity)
        rejectUpPopu!!.showAtLocation(ll_frag_shzlrjhj_top, Gravity.CENTER, 0, 0)
        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = object :BaseQuickAdapter<BcrejectedEntity, BaseViewHolder>(R.layout.item_bcjc_reject_rlv_item,list){
            override fun convert(helper: BaseViewHolder?, item: BcrejectedEntity?) {
                helper!!.setText(R.id.pop_bcjc_reject_reason,item!!.content)
                        .setText(R.id.pop_bcjc_reject_year,item.years)
            }


        }
        close.setOnClickListener {
            rejectUpPopu!!.dismiss()
        }
    }
    override fun returnBcjcAddCyjg(msg: String) {
        ToastUtils.showShort(msg)
        uploadList.clear()
        if (addCyjgUpPopu!=null){
            addCyjgUpPopu!!.dismiss()
        }
        mPresenter.getBcjcCyjg(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcCyjgAll(AppCache.getInstance().code,yearsListNull)
    }


    //时间格式转换
    private fun getTime(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")// HH:mm:ss
        return format.format(date)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser&&isResumed){//可见
            //TODO now it's visible to user
            onResume()
        } else if (!isVisibleToUser) {//不可见
            //TODO now it's invisible to user
            onPause()
        }
    }
    fun diaoyong(){
        if (supl_frag_zjdgl1!=null&&sv_frag_shzlrjhj_up!=null){
            supl_frag_zjdgl1?.setScrollableView(sv_frag_shzlrjhj_up)

            mPresenter.getBcjcYears()
            sptypeList.clear()
            sptypeList.add("上报")
            sptypeList.add("乡镇审核")
            sptypeList.add("区级确认")
            sptypeList.add("提交成功")
            tll_frag_zjd_cyjg_ghyczqk.setValueText(sptypeList,-1,-1)
        }
    }
    override fun onResume() {
        super.onResume()

    }
    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }



}
