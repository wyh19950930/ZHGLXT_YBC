package com.jymj.zhglxt.zjd.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.R.string.confirm
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.home.activity.CzlbActivity
import com.jymj.zhglxt.home.activity.YllbActivity
import com.jymj.zhglxt.ldrkgl.home.activity.LdrkDetailActivity
import com.jymj.zhglxt.ldrkgl.home.adapter.CzqkAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.HylxTlAdapter
import com.jymj.zhglxt.ldrkgl.home.adapter.YllbAdapter
import com.jymj.zhglxt.ldrkgl.home.bean.*
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.ZjdLdrkContract
import com.jymj.zhglxt.login.model.ZjdLdrkModel
import com.jymj.zhglxt.login.presenter.ZjdLdrkPresenter
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.EnableLinearLayout
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ScreenUtils
import com.setsuna.common.commonutils.TimeUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjd_ldrk.*
import kotlinx.android.synthetic.main.fragment_zjd_yd.*
import java.text.DecimalFormat

/**
 * 百村监测-人口变动fragment
 */

class ZjdLdrkFragment: BaseFragment<ZjdLdrkPresenter, ZjdLdrkModel>(), ZjdLdrkContract.View {

    private var yearUpPopu: CommenPop? = null
    private var rejectUpPopu: CommenPop? = null
    private var addRkbdqkUpPopu: CommenPop? = null
    private var addldlbdqkUpPopu: CommenPop? = null
    private var addwcwgbdqkUpPopu: CommenPop? = null
    private var addcgbjbqkUpPopu: CommenPop? = null

    var mUploadPopu:CommenPop? = null
    var sptypeList = ArrayList<String>()



    val colors = java.util.ArrayList<Int>()//行业分布图例颜色  NestedScrollView
    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ZjdLdrkFragment {
            return ZjdLdrkFragment().apply {
                //                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
        
    }

    override fun getLayoutResource(): Int {
        return R.layout.frag_zjd_ldrk
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        initTuli()//初始化图例
        if (AppCache.getInstance().type == ApiConstants.LDRK_LGB|| AppCache.getInstance().type == ApiConstants.LDRK_ZZF){
            tv_frag_ldgl_czqk.setText("村庄宅房人统计列表")
            tv_frag_ldgl_cm.visibility = View.GONE
//            mPresenter.getCxczqk("","")//0728注
        }else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY){
            tv_frag_ldgl_czqk.setText("村庄出租房统计列表")
//            tv_frag_ldgl_cm.visibility = View.VISIBLE
            tv_frag_ldgl_cm.setText(AppCache.getInstance().xzqName)
//            mPresenter.getCxldry(AppCache.getInstance().code,"","")//0728注
        }

        if (AppCache.getInstance().type==1){//如果是百村账号隐藏选择时间
            mtl_frag_ldgl_rkbdtime.visibility = View.GONE
        }

        tv_frag_ldgl_ckgd.setOnClickListener {
            //查看更多列表
            if (SingleOnClickUtil1.isFastClick()) {
                if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF) {//镇
                    val intent = Intent(activity, CzlbActivity::class.java)
                    startActivity(intent)
                } else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY) {//村
                    val intent = Intent(activity, YllbActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        /*tv_frag_ldgl_xzsj.setOnClickListener {
            TimePickerUtil.initTimePickerViewNyr(activity,tv_frag_ldgl_xzsj.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    //时间筛选
                    tv_frag_ldgl_xzsj.setText(data)
                }
            })
        }*/

        ll_frag_rkjy_cgbjbqk_whcd.setOnClickListener {
            TdlyUtils.setTextPop(activity,tv_frag_rkjy_cgbjbqk_whcd.text.toString(),tv_frag_rkjy_cgbjbqk_whcd)
        }

        mtl_frag_ldgl_rkhjqk.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_ldgl_rkhjqk.isShown){
                        ll_frag_ldgl_rkhjqk.visibility = View.GONE
                        mtl_frag_ldgl_rkhjqk.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_ldgl_rkhjqk.visibility = View.VISIBLE
                        mtl_frag_ldgl_rkhjqk.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        mtl_frag_ldgl_rknlqk.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_ldgl_rknlqk.isShown){
                        ll_frag_ldgl_rknlqk.visibility = View.GONE
                        mtl_frag_ldgl_rknlqk.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_ldgl_rknlqk.visibility = View.VISIBLE
                        mtl_frag_ldgl_rknlqk.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        mtl_frag_ldgl_ldlbdfl1.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_ldgl_ldlbdfl1.isShown){
                        ll_frag_ldgl_ldlbdfl1.visibility = View.GONE
                        mtl_frag_ldgl_ldlbdfl1.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_ldgl_ldlbdfl1.visibility = View.VISIBLE
                        mtl_frag_ldgl_ldlbdfl1.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        mtl_frag_ldgl_ldlrknlqk.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_ldgl_ldlrknlqk.isShown){
                        ll_frag_ldgl_ldlrknlqk.visibility = View.GONE
                        mtl_frag_ldgl_ldlrknlqk.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_ldgl_ldlrknlqk.visibility = View.VISIBLE
                        mtl_frag_ldgl_ldlrknlqk.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        mtl_frag_ldgl_ldlrkcyqk.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_ldgl_ldlrkcyqk.isShown){
                        ll_frag_ldgl_ldlrkcyqk.visibility = View.GONE
                        mtl_frag_ldgl_ldlrkcyqk.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_ldgl_ldlrkcyqk.visibility = View.VISIBLE
                        mtl_frag_ldgl_ldlrkcyqk.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        mtl_frag_ldgl_lncqwc.setOnTitleListLister(object: MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")){
                    if (ll_frag_ldgl_lncqwcrybd.isShown){
                        ll_frag_ldgl_lncqwcrybd.visibility = View.GONE
                        mtl_frag_ldgl_lncqwc.setImageView(R.drawable.item_title_right)
                    }else{
                        ll_frag_ldgl_lncqwcrybd.visibility = View.VISIBLE
                        mtl_frag_ldgl_lncqwc.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })


    }
    var split = ""
    override fun initDatas() {
//        mPresenter.getJbqktj()//查询首页基本情况/行业类型统计
        val dateAfter = TimeUtil.getDateAfter(0)
//        split = dateAfter.split("-")[0]
        if (AppCache.getInstance().type == 4){
            ll_frag_zjd_ldrk_gzjdd.visibility = View.VISIBLE
        }else{
            ll_frag_zjd_ldrk_gzjdd.visibility = View.GONE
        }


    }
    private fun initTuli() {

        colors.add(Color.argb(199, 255, 102, 15))
        colors.add(Color.argb(199, 64, 158, 255))
        colors.add(Color.argb(199, 128, 143, 238))
        colors.add(Color.argb(199, 46, 201, 250))
        colors.add(Color.argb(199, 138, 149, 153))

    }

    private val yearsList = ArrayList<String>()
    private val yearsListNull = ArrayList<String>()

    private fun yearPopup(yearList:List<String>){
        yearUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_years, sv_frag_ldgl_up)
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
            mPresenter.getBchcRkbdqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBchcLdlbdqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBchcWcwgbdqk(AppCache.getInstance().code,yearsList)
            mPresenter.getBcjcGetInwcry(AppCache.getInstance().code,yearsList)
            if (yearsList.size>0){
                mPresenter.getBcjcCgbqk(AppCache.getInstance().code,yearsList.get(0))
            }
        }
        close.setOnClickListener {
            yearUpPopu!!.dismiss()
        }
    }

    var rkjyUploadType = 0//判断是否保存
    var ldlbdUploadType = 0
    var wcwgUploadType = 0
    var rkjyEntity = BcrkbdqkEntity()
    var rkjyEntity1978 = BcrkbdqkEntity()
    var ldlbdEntity = BcldlbdqkEntity()
    var wcwgEntity = BcwcjybdqkEntity()
    var bccgbqkEntity = BccgbqkEntity()

    var bcrkbdqkEntitysId = 0L
    var bcrkbdqkEntitysId1978 = 0L
    var bcldlbdqkEntitysId = 0L
    var bcwcjybdqkEntitysId = 0L

    var bcrkbdqkProcess = 1//人口变动表格进度
    var bcrkbdqkProcess1978 = 1//人口变动表格进度
    var bcldlbdqkProcess = 1//劳动力变动表格进度
    var bcwcjybdqkProcess = 1//外出务工人员变动表格进度
    override fun returnBcjcYears(msg: List<String>) {
        yearsList.clear()
        if (AppCache.getInstance().duties!=1&&AppCache.getInstance().type!=1){
            yearsList.add(msg.get(0))
            split = yearsList.get(0).substring(0,yearsList.get(0).length-1)
        }
        mPresenter.getBchcRkbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcLdlbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcWcwgbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcGetInwcry(AppCache.getInstance().code,yearsList)
        if (yearsList.size>0){
            mPresenter.getBcjcCgbqk(AppCache.getInstance().code,yearsList.get(0))
        }

        mPresenter.getBchcRkbdqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcLdlbdqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBchcWcwgbdqkAll(AppCache.getInstance().code,yearsListNull)
        yearPopup(msg)
        mtl_frag_ldgl_rkbdtime.addText = msg.get(0)

        mtl_frag_ldgl_rkbdtime.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("添加")){

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        mtl_frag_ldgl_rkbdtime.addText = msg.get(options1)
                        split = msg.get(options1).substring(0,msg.get(options1).length-1)
                        yearsList.clear()
                        yearsList.add(mtl_frag_ldgl_rkbdtime.addText)
                        mPresenter.getBchcRkbdqk(AppCache.getInstance().code,yearsList)
                        mPresenter.getBchcLdlbdqk(AppCache.getInstance().code,yearsList)
                        mPresenter.getBchcWcwgbdqk(AppCache.getInstance().code,yearsList)
                        mPresenter.getBcjcGetInwcry(AppCache.getInstance().code,yearsList)
                        if (yearsList.size>0){
                            mPresenter.getBcjcCgbqk(AppCache.getInstance().code,yearsList.get(0))
                        }
                        mPresenter.getBchcRkbdqkAll(AppCache.getInstance().code,yearsListNull)
                        mPresenter.getBchcLdlbdqkAll(AppCache.getInstance().code,yearsListNull)
                        mPresenter.getBchcWcwgbdqkAll(AppCache.getInstance().code,yearsListNull)
//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(mtl_frag_ldgl_rkbdtime.addText))
                    pvOptions.setPicker(msg)

                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                    params.leftMargin = 0
                    params.rightMargin = 0
                    val contentContainer = pvOptions.getDialogContainerLayout()
                    contentContainer.setLayoutParams(params)
                    pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                    pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                    pvOptions.show()

                    /*TimePickerUtil.initTimePickerViewNyr(activity,mtl_frag_ldgl_rkbdtime.addText,object: TimePickerUtil.OnTimePickerLister{
                        override fun onClick(data: String?) {
                            //时间筛选
                            mtl_frag_ldgl_rkbdtime.addText = data
                        }
                    })*/
                    /*if (yearUpPopu!=null) {
                        CommenPop.backgroundAlpha(0.5f, activity)
                        yearUpPopu!!.showAtLocation(sv_frag_ldgl_up, Gravity.CENTER, 0, 0)
                    }*/
                }
            }
        })

        /*if (AppCache.getInstance().type == 4){
            tv_frag_rkjy_rkbdqk_add.text = "添加/修改"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_rkjy_rkbdqk_add.visibility = View.GONE
        }else{
            tv_frag_rkjy_rkbdqk_add.text = "操作"
        }*/
        //添加人口变动情况表
        tv_frag_rkjy_rkbdqk_add.setOnClickListener {
            if (tv_frag_rkjy_rkbdqk_add.text.toString().equals("操作")){

            addRkbdqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_rkbdqk, sv_frag_ldgl_up)
            val contentView: View = addRkbdqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_rkbdqk_spr)
            val tv_pop_add_rkbdqk = contentView.findViewById<TextView>(R.id.tv_pop_add_rkbdqk)
            val et_qchjrkhs = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_qchjrkhs)
            val et_qchjrkrs = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_qchjrkrs)
            val et_nyhjh = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_nyhjh)
            val et_nyhjr = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_nyhjr)
            val et_xydy30 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_xydy30)
            val et_dydy60 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_dydy60)
            val et_bchjczrk = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_bchjczrk)
            val et_wlczrk = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_wlczrk)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_rkbdqk_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_rkbdqk_confirm)//确定/下一步/通过
            val reject = contentView.findViewById<TextView>(R.id.pop_add_rkbdqk_reject)//驳回
            val close = contentView.findViewById<TextView>(R.id.pop_add_rkbdqk_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_rkbdqk_ell)
            val enableLl_1978 = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_rkbdqk_ell_1978)

            val rkbdqkll_1978 = contentView.findViewById<LinearLayout>(R.id.ll_pop_add_rkbdqk_1978)
            val rkbdqk_1978 = contentView.findViewById<TextView>(R.id.tv_pop_add_rkbdqk_1978)
            val qchjrkhs_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_qchjrkhs_1978)
            val qchjrkrs_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_qchjrkrs_1978)
            val nyhjh_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_nyhjh_1978)
            val nyhjr_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_nyhjr_1978)
            val xydy30_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_xydy30_1978)
            val dydy60_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_dydy60_1978)
            val bchjczrk_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_bchjczrk_1978)
            val wlczrk_1978 = contentView.findViewById<EditText>(R.id.pop_add_rkbdqk_et_wlczrk_1978)
                rkbdqk_1978.setText("1978年")
                rkbdqkll_1978.visibility = View.VISIBLE
            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addRkbdqkUpPopu!!.showAtLocation(sv_frag_ldgl_up, Gravity.CENTER, 0, 0)
//                addRkbdqkUpPopu.getMaxAvailableHeight()

                tv_pop_add_rkbdqk.setText(mtl_frag_ldgl_rkbdtime.addText)//msg.get(0)
                for (i in bcrkbdqkEntitys){
                    if (mtl_frag_ldgl_rkbdtime.addText.equals(i.years)){//msg.get(0)
                        et_qchjrkhs.setText(i.qchs)
                        et_qchjrkrs.setText(i.qcrk)
                        et_nyhjh.setText(i.nyhjh)
                        et_nyhjr.setText(i.nyhjr)
                        et_xydy30.setText(i.nlss)
                        et_dydy60.setText(i.nlls)
                        et_bchjczrk.setText(i.bcczrk)
                        et_wlczrk.setText(i.wlczrk)
                        bcrkbdqkEntitysId = i.id
                        bcrkbdqkProcess = i.process
                        if (AppCache.getInstance().type == 4){
                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            enableLl_1978.setNoClick(false)
                            when(bcrkbdqkProcess){
                                1->{
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }
                                2->{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }else{
                            enableLl.setNoClick(true)
                            enableLl_1978.setNoClick(true)
                            if (AppCache.getInstance().type == 3){
                                et_czrname.setText(i.zhenname)
                                if (bcrkbdqkProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else
                                if (AppCache.getInstance().type == 2){
                                    et_czrname.setText(i.qvname)
                                    if (bcrkbdqkProcess == 3){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
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
                        et_qchjrkhs.setText("")
                        et_qchjrkrs.setText("")
                        et_nyhjh.setText("")
                        et_nyhjr.setText("")
                        et_xydy30.setText("")
                        et_dydy60.setText("")
                        et_bchjczrk.setText("")
                        et_wlczrk.setText("")
                        et_czrname.setText("")
                        bcrkbdqkEntitysId = 0L
                        bcrkbdqkProcess = 0
                        if (AppCache.getInstance().type == 4){
                            enableLl.setNoClick(false)
                            enableLl_1978.setNoClick(false)
//                            confirm.visibility = View.VISIBLE
                            reject.visibility = View.VISIBLE
                        }else{
                            enableLl.setNoClick(true)
                            enableLl_1978.setNoClick(true)
                            confirm.visibility = View.GONE
                            reject.visibility = View.GONE
                        }

                    }

                }
                for (i in bcrkbdqkEntitys){
                    if ("1978年".equals(i.years)){//msg.get(0)
                        qchjrkhs_1978.setText(i.qchs)
                        qchjrkrs_1978.setText(i.qcrk)
                        nyhjh_1978.setText(i.nyhjh)
                        nyhjr_1978.setText(i.nyhjr)
                        xydy30_1978.setText(i.nlss)
                        dydy60_1978.setText(i.nlls)
                        bchjczrk_1978.setText(i.bcczrk)
                        wlczrk_1978.setText(i.wlczrk)
                        if (i.process == 4){
                            rkbdqkll_1978.visibility = View.GONE
                        }else{
                            bcrkbdqkEntitysId1978 = i.id
                            bcrkbdqkProcess1978 = i.process
                        }
                        break
                    }else{
                        qchjrkhs_1978.setText("")
                        qchjrkrs_1978.setText("")
                        nyhjh_1978.setText("")
                        nyhjr_1978.setText("")
                        xydy30_1978.setText("")
                        dydy60_1978.setText("")
                        bchjczrk_1978.setText("")
                        wlczrk_1978.setText("")
                        bcrkbdqkEntitysId1978 = 0
                        bcrkbdqkProcess1978 = 0

                    }
                }

                /*tv_pop_add_rkbdqk.setOnClickListener {
                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_rkbdqk.setText(msg.get(options1))
                        for (i in bcrkbdqkEntitys){
                            if (msg.get(options1).equals(i.years)){
                                et_qchjrkhs.setText(i.qchs)
                                et_qchjrkrs.setText(i.qcrk)
                                et_nyhjh.setText(i.nyhjh)
                                et_nyhjr.setText(i.nyhjr)
                                et_xydy30.setText(i.nlss)
                                et_dydy60.setText(i.nlls)
                                et_bchjczrk.setText(i.bcczrk)
                                et_wlczrk.setText(i.wlczrk)
                                bcrkbdqkEntitysId = i.id
                                bcrkbdqkProcess = i.process
                                if (AppCache.getInstance().type == 4){
                                    et_czrname.setText(i.cunname)
                                    enableLl.setNoClick(false)
                                    when(bcrkbdqkProcess){
                                        1->{
//                                            confirm.visibility = View.VISIBLE
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
                                        if (bcrkbdqkProcess == 2){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else if (AppCache.getInstance().type == 2){
                                        et_czrname.setText(i.qvname)
                                        if (bcrkbdqkProcess == 3){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
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
                                et_qchjrkhs.setText("")
                                et_qchjrkrs.setText("")
                                et_nyhjh.setText("")
                                et_nyhjr.setText("")
                                et_xydy30.setText("")
                                et_dydy60.setText("")
                                et_bchjczrk.setText("")
                                et_wlczrk.setText("")
                                et_czrname.setText("")
                                bcrkbdqkEntitysId = 0L
                                bcrkbdqkProcess = 0
                                if (AppCache.getInstance().type == 4){
                                    enableLl.setNoClick(false)
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    enableLl.setNoClick(true)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }

                            }
                        }
//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_rkbdqk.text.toString()))
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
                /*2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {
                        val entity = BcrkbdqkEntity()
                        entity.qchs = et_qchjrkhs.text.toString()
                        entity.qcrk = et_qchjrkrs.text.toString()
                        entity.nyhjh = et_nyhjh.text.toString()
                        entity.nyhjr = et_nyhjr.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.bcczrk = et_bchjczrk.text.toString()
                        entity.wlczrk = et_wlczrk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.years = tv_pop_add_rkbdqk.text.toString()
                        entity.process = 4
                        entity.id = bcrkbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateRkbdqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        val entity = BcrkbdqkEntity()
                        entity.qchs = et_qchjrkhs.text.toString()
                        entity.qcrk = et_qchjrkrs.text.toString()
                        entity.nyhjh = et_nyhjh.text.toString()
                        entity.nyhjr = et_nyhjr.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.bcczrk = et_bchjczrk.text.toString()
                        entity.wlczrk = et_wlczrk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.years = tv_pop_add_rkbdqk.text.toString()
                        entity.process = 1
                        entity.id = bcrkbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateRkbdqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        val entity = BcrkbdqkEntity()
                        entity.qchs = et_qchjrkhs.text.toString()
                        entity.qcrk = et_qchjrkrs.text.toString()
                        entity.nyhjh = et_nyhjh.text.toString()
                        entity.nyhjr = et_nyhjr.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.bcczrk = et_bchjczrk.text.toString()
                        entity.wlczrk = et_wlczrk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.years = tv_pop_add_rkbdqk.text.toString()
                        entity.process = 3
                        entity.id = bcrkbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateRkbdqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        val entity = BcrkbdqkEntity()
                        entity.qchs = et_qchjrkhs.text.toString()
                        entity.qcrk = et_qchjrkrs.text.toString()
                        entity.nyhjh = et_nyhjh.text.toString()
                        entity.nyhjr = et_nyhjr.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.bcczrk = et_bchjczrk.text.toString()
                        entity.wlczrk = et_wlczrk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.years = tv_pop_add_rkbdqk.text.toString()
                        entity.process = 1
                        entity.id = bcrkbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateRkbdqk(entity)
                        }
                    }
                }*/
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        val entity = BcrkbdqkEntity()
                        entity.qchs = et_qchjrkhs.text.toString()
                        entity.qcrk = et_qchjrkrs.text.toString()
                        entity.nyhjh = et_nyhjh.text.toString()
                        entity.nyhjr = et_nyhjr.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.bcczrk = et_bchjczrk.text.toString()
                        entity.wlczrk = et_wlczrk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.years = tv_pop_add_rkbdqk.text.toString()
                        entity.process = 1
                        val entity1 = BcrkbdqkEntity()
                        entity1.qchs = qchjrkhs_1978.text.toString()
                        entity1.qcrk = qchjrkrs_1978.text.toString()
                        entity1.nyhjh = nyhjh_1978.text.toString()
                        entity1.nyhjr = nyhjr_1978.text.toString()
                        entity1.nlss = xydy30_1978.text.toString()
                        entity1.nlls = dydy60_1978.text.toString()
                        entity1.bcczrk = bchjczrk_1978.text.toString()
                        entity1.wlczrk = wlczrk_1978.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.years = rkbdqk_1978.text.toString()
                        entity1.process = 1
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }/*else if (TdlyUtils.setEditToast(et_qchjrkhs,et_qchjrkrs,et_nyhjh,et_nyhjr,et_xydy30,et_dydy60,et_bchjczrk,et_wlczrk)){
                        }*/else{
                            if (rkbdqkll_1978.visibility == View.VISIBLE){
                                entity.cunname = et_czrname.text.toString()
                                entity1.cunname = et_czrname.text.toString()
                                val arrayList = ArrayList<BcrkbdqkEntity>()
                                if (bcrkbdqkEntitysId == 0L){
                                    rkjyEntity = entity
                                    arrayList.add(entity)
                                }else{
                                    entity.id = bcrkbdqkEntitysId
                                    rkjyEntity = entity
                                    arrayList.add(entity)
                                }
                                if (bcrkbdqkEntitysId1978 == 0L){
                                    rkjyEntity1978 = entity1
                                    arrayList.add(entity1)
                                }else{
                                    entity1.id = bcrkbdqkEntitysId1978
                                    rkjyEntity1978 = entity1
                                    arrayList.add(entity1)
                                }
                                mPresenter.getBcjcAddRkbdqk(arrayList)

                            }else{
                                entity.cunname = et_czrname.text.toString()
                                if (bcrkbdqkEntitysId == 0L){
                                    rkjyEntity = entity
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(entity)
                                    mPresenter.getBcjcAddRkbdqk(arrayList)
                                }else{
                                    entity.id = bcrkbdqkEntitysId
                                    rkjyEntity = entity
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(entity)
                                    mPresenter.getBcjcAddRkbdqk(arrayList)
                                }

                            }
                        }

                    }
                    //村 下一步
                   /* confirm.setOnClickListener {
                        val entity = BcrkbdqkEntity()
                        entity.qchs = et_qchjrkhs.text.toString()
                        entity.qcrk = et_qchjrkrs.text.toString()
                        entity.nyhjh = et_nyhjh.text.toString()
                        entity.nyhjr = et_nyhjr.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.bcczrk = et_bchjczrk.text.toString()
                        entity.wlczrk = et_wlczrk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.years = tv_pop_add_rkbdqk.text.toString()
                        entity.process = 2
                        val entity1 = BcrkbdqkEntity()
                        entity1.qchs = qchjrkhs_1978.text.toString()
                        entity1.qcrk = qchjrkrs_1978.text.toString()
                        entity1.nyhjh = nyhjh_1978.text.toString()
                        entity1.nyhjr = nyhjr_1978.text.toString()
                        entity1.nlss = xydy30_1978.text.toString()
                        entity1.nlls = dydy60_1978.text.toString()
                        entity1.bcczrk = bchjczrk_1978.text.toString()
                        entity1.wlczrk = wlczrk_1978.text.toString()
                        entity1.code = AppCache.getInstance().code
                        entity1.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity1.years = rkbdqk_1978.text.toString()
                        entity1.process = 4
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (TdlyUtils.setEditToast(et_qchjrkhs,et_qchjrkrs,et_nyhjh,et_nyhjr,et_xydy30,et_dydy60,et_bchjczrk,et_wlczrk)){
                        }else{
                            if (rkbdqkll_1978.visibility == View.VISIBLE){
                                if (!TdlyUtils.setEditToast(qchjrkhs_1978,qchjrkrs_1978,nyhjh_1978,nyhjr_1978,xydy30_1978,dydy60_1978,bchjczrk_1978,wlczrk_1978)){
                                    entity.cunname = et_czrname.text.toString()
                                    entity1.cunname = et_czrname.text.toString()
                                    if (bcrkbdqkEntitysId == 0L){
                                        rkjyEntity = entity
                                        val arrayList = ArrayList<BcrkbdqkEntity>()
                                        arrayList.add(entity)
                                        arrayList.add(entity1)
                                        mPresenter.getBcjcAddRkbdqk(arrayList)
                                    }else{
                                        entity.id = bcrkbdqkEntitysId
                                        rkjyEntity = entity
                                        mPresenter.getBcjcUpdateRkbdqk(entity)
                                    }
                                }

                            }else{
                                entity.cunname = et_czrname.text.toString()
                                if (bcrkbdqkEntitysId == 0L){
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(entity)
                                    mPresenter.getBcjcAddRkbdqk(arrayList)
                                }else{
                                    entity.id = bcrkbdqkEntitysId
                                    mPresenter.getBcjcUpdateRkbdqk(entity)
                                }
                            }
                        }
                    }*/
                }
            }

            close.setOnClickListener {
                addRkbdqkUpPopu!!.dismiss()
            }
            }
        }
        /*if (AppCache.getInstance().type == 4){
            tv_frag_rkjy_ldlbdqk_add.text = "操作"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_rkjy_ldlbdqk_add.visibility = View.GONE
        }else{
            tv_frag_rkjy_ldlbdqk_add.text = "操作"
        }*/
        //添加劳动力变动情况表
        tv_frag_rkjy_ldlbdqk_add.setOnClickListener {
            if (tv_frag_rkjy_ldlbdqk_add.text.toString().equals("操作")){

            addldlbdqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_ldlbdqk, sv_frag_ldgl_up)
            val contentView: View = addldlbdqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_ldlbdqk_spr)
            val tv_pop_add_ldlbdqk = contentView.findViewById<TextView>(R.id.tv_pop_add_ldlbdqk)
            val et_sjjyldlzs = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_sjjyldlzs)
            val et_xydy30 = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_xydy30)
            val et_dydy60 = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_dydy60)
            val et_yc = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_yc)
            val et_ec = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_ec)
            val et_sc = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_sc)
            val et_jtjy = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_jtjy)
            val et_cjtjy = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_cjtjy)
            val et_cjtjjzz = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_cjtjjzz)
            val et_wcwg = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_wcwg)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_ldlbdqk_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_ldlbdqk_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_ldlbdqk_confirm)//确定/下一步/通过
            val reject = contentView.findViewById<TextView>(R.id.pop_add_ldlbdqk_reject)//驳回
            val close = contentView.findViewById<TextView>(R.id.pop_add_ldlbdqk_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_ldlbdqk_ell)

            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addldlbdqkUpPopu!!.showAtLocation(sv_frag_ldgl_up, Gravity.CENTER, 0, 0)


                tv_pop_add_ldlbdqk.setText(mtl_frag_ldgl_rkbdtime.addText)//msg.get(0)
                for (i in bcldlbdqkEntitys){
                    if (mtl_frag_ldgl_rkbdtime.addText.equals(i.years)){//msg.get(0)
                        et_sjjyldlzs.setText(i.ldlzs)
                        et_xydy30.setText(i.nlss)
                        et_dydy60.setText(i.nlls)
                        et_yc.setText(i.cyyc)
                        et_ec.setText(i.cyec)
                        et_sc.setText(i.sysc)
                        et_jtjy.setText(i.jtjy)
                        et_cjtjy.setText(i.cjtjy)
                        et_cjtjjzz.setText(i.cjtjjzz)
                        et_wcwg.setText(i.wclg)
                        bcldlbdqkEntitysId = i.id
                        bcldlbdqkProcess = i.process
                        if (AppCache.getInstance().type == 4){
                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bcldlbdqkProcess){
                                1->{
//                                    confirm.visibility = View.VISIBLE
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
                                if (bcldlbdqkProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
                                et_czrname.setText(i.qvname)
                                if (bcldlbdqkProcess == 3){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
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
                        et_sjjyldlzs.setText("")
                        et_xydy30.setText("")
                        et_dydy60.setText("")
                        et_yc.setText("")
                        et_ec.setText("")
                        et_sc.setText("")
                        et_jtjy.setText("")
                        et_cjtjy.setText("")
                        et_cjtjjzz.setText("")
                        et_wcwg.setText("")
                        bcldlbdqkEntitysId = 0L
                        bcldlbdqkProcess = 0
                        if (AppCache.getInstance().type == 4){
                            enableLl.setNoClick(false)
//                            confirm.visibility = View.VISIBLE
                            reject.visibility = View.VISIBLE
                        }else{
                            enableLl.setNoClick(true)
                            confirm.visibility = View.GONE
                            reject.visibility = View.GONE
                        }
                    }
                }
                /*tv_pop_add_ldlbdqk.setOnClickListener {

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_ldlbdqk.setText(msg.get(options1))

                        for (i in bcldlbdqkEntitys){
                            if (msg.get(options1).equals(i.years)){
                                et_sjjyldlzs.setText(i.ldlzs)
                                et_xydy30.setText(i.nlss)
                                et_dydy60.setText(i.nlls)
                                et_yc.setText(i.cyyc)
                                et_ec.setText(i.cyec)
                                et_sc.setText(i.sysc)
                                et_jtjy.setText(i.jtjy)
                                et_cjtjy.setText(i.cjtjy)
                                et_cjtjjzz.setText(i.cjtjjzz)
                                et_wcwg.setText(i.wclg)
                                bcldlbdqkEntitysId = i.id
                                bcldlbdqkProcess = i.process
                                if (AppCache.getInstance().type == 4){
                                    et_czrname.setText(i.cunname)
                                    enableLl.setNoClick(false)
                                    when(bcldlbdqkProcess){
                                        1->{
//                                            confirm.visibility = View.VISIBLE
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
                                        if (bcldlbdqkProcess == 2){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else if (AppCache.getInstance().type == 2){
                                        et_czrname.setText(i.qvname)
                                        if (bcldlbdqkProcess == 3){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
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
                                et_sjjyldlzs.setText("")
                                et_xydy30.setText("")
                                et_dydy60.setText("")
                                et_yc.setText("")
                                et_ec.setText("")
                                et_sc.setText("")
                                et_jtjy.setText("")
                                et_cjtjy.setText("")
                                et_cjtjjzz.setText("")
                                et_wcwg.setText("")
                                bcldlbdqkEntitysId = 0L
                                bcldlbdqkProcess = 0
                                if (AppCache.getInstance().type == 4){
                                    enableLl.setNoClick(false)
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    enableLl.setNoClick(true)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }


//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_ldlbdqk.text.toString()))
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
                        val entity = BcldlbdqkEntity()
                        entity.ldlzs = et_sjjyldlzs.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.cyyc = et_yc.text.toString()
                        entity.cyec = et_ec.text.toString()
                        entity.sysc = et_sc.text.toString()
                        entity.jtjy = et_jtjy.text.toString()
                        entity.cjtjy = et_cjtjy.text.toString()
                        entity.cjtjjzz = et_cjtjjzz.text.toString()
                        entity.wclg = et_wcwg.text.toString()
                        entity.years = tv_pop_add_ldlbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bcldlbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateLdlbdqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        val entity = BcldlbdqkEntity()
                        entity.ldlzs = et_sjjyldlzs.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.cyyc = et_yc.text.toString()
                        entity.cyec = et_ec.text.toString()
                        entity.sysc = et_sc.text.toString()
                        entity.jtjy = et_jtjy.text.toString()
                        entity.cjtjy = et_cjtjy.text.toString()
                        entity.cjtjjzz = et_cjtjjzz.text.toString()
                        entity.wclg = et_wcwg.text.toString()
                        entity.years = tv_pop_add_ldlbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcldlbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateLdlbdqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        val entity = BcldlbdqkEntity()
                        entity.ldlzs = et_sjjyldlzs.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.cyyc = et_yc.text.toString()
                        entity.cyec = et_ec.text.toString()
                        entity.sysc = et_sc.text.toString()
                        entity.jtjy = et_jtjy.text.toString()
                        entity.cjtjy = et_cjtjy.text.toString()
                        entity.cjtjjzz = et_cjtjjzz.text.toString()
                        entity.wclg = et_wcwg.text.toString()
                        entity.years = tv_pop_add_ldlbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bcldlbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateLdlbdqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        val entity = BcldlbdqkEntity()
                        entity.ldlzs = et_sjjyldlzs.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.cyyc = et_yc.text.toString()
                        entity.cyec = et_ec.text.toString()
                        entity.sysc = et_sc.text.toString()
                        entity.jtjy = et_jtjy.text.toString()
                        entity.cjtjy = et_cjtjy.text.toString()
                        entity.cjtjjzz = et_cjtjjzz.text.toString()
                        entity.wclg = et_wcwg.text.toString()
                        entity.years = tv_pop_add_ldlbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcldlbdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateLdlbdqk(entity)
                        }
                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        val entity = BcldlbdqkEntity()
                        entity.ldlzs = et_sjjyldlzs.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.cyyc = et_yc.text.toString()
                        entity.cyec = et_ec.text.toString()
                        entity.sysc = et_sc.text.toString()
                        entity.jtjy = et_jtjy.text.toString()
                        entity.cjtjy = et_cjtjy.text.toString()
                        entity.cjtjjzz = et_cjtjjzz.text.toString()
                        entity.wclg = et_wcwg.text.toString()
                        entity.years = tv_pop_add_ldlbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }/*else if (TdlyUtils.setEditToast(et_sjjyldlzs,et_xydy30,et_dydy60,et_yc,et_ec,et_sc,et_jtjy,et_cjtjy,et_cjtjjzz,et_wcwg)){
                        }*/else{
                            entity.cunname = et_czrname.text.toString()
                            if (bcldlbdqkEntitysId == 0L){
                                ldlbdEntity = entity
                                mPresenter.getBcjcAddLdlbdqk(entity)
                            }else{
                                entity.id = bcldlbdqkEntitysId
                                ldlbdEntity = entity
                                mPresenter.getBcjcUpdateLdlbdqk(entity)
                            }
                        }

                    }
                    //村 下一步
                    confirm.setOnClickListener {
                        val entity = BcldlbdqkEntity()
                        entity.ldlzs = et_sjjyldlzs.text.toString()
                        entity.nlss = et_xydy30.text.toString()
                        entity.nlls = et_dydy60.text.toString()
                        entity.cyyc = et_yc.text.toString()
                        entity.cyec = et_ec.text.toString()
                        entity.sysc = et_sc.text.toString()
                        entity.jtjy = et_jtjy.text.toString()
                        entity.cjtjy = et_cjtjy.text.toString()
                        entity.cjtjjzz = et_cjtjjzz.text.toString()
                        entity.wclg = et_wcwg.text.toString()
                        entity.years = tv_pop_add_ldlbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (TdlyUtils.setEditToast(et_sjjyldlzs,et_xydy30,et_dydy60,et_yc,et_ec,et_sc,et_jtjy,et_cjtjy,et_cjtjjzz,et_wcwg)){
                        }else{
                            entity.cunname = et_czrname.text.toString()
                            if (bcldlbdqkEntitysId == 0L){
                                mPresenter.getBcjcAddLdlbdqk(entity)
                            }else{
                                entity.id = bcldlbdqkEntitysId
                                mPresenter.getBcjcUpdateLdlbdqk(entity)
                            }
                        }
                    }
                }
            }

            close.setOnClickListener {
                addldlbdqkUpPopu!!.dismiss()
            }
            }
        }

        /*if (AppCache.getInstance().type == 4){
            tv_frag_rkjy_wcwgbdqk_add.text = "操作"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_rkjy_wcwgbdqk_add.visibility = View.GONE
        }else{
            tv_frag_rkjy_wcwgbdqk_add.text = "操作"
        }*/
        //添加外出务工情况表
        tv_frag_rkjy_wcwgbdqk_add.setOnClickListener {
            if (tv_frag_rkjy_wcwgbdqk_add.text.toString().equals("操作")){

            addwcwgbdqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_wcwgbdqk, sv_frag_ldgl_up)
            val contentView: View = addwcwgbdqkUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_wcwgbdqk_spr)
            val tv_pop_add_wcwgbdqk = contentView.findViewById<TextView>(R.id.tv_pop_add_wcwgbdqk)
            val et_scwcwg = contentView.findViewById<EditText>(R.id.pop_add_wcwgbdqk_et_scwcwg)
            val et_bdjy = contentView.findViewById<EditText>(R.id.pop_add_wcwgbdqk_et_bdjy)
            val et_xwqn = contentView.findViewById<EditText>(R.id.pop_add_wcwgbdqk_et_xwqn)
            val et_qwsn = contentView.findViewById<EditText>(R.id.pop_add_wcwgbdqk_et_qwsn)
            val et_wss = contentView.findViewById<EditText>(R.id.pop_add_wcwgbdqk_et_wss)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_wcwgbdqk_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_wcwgbdqk_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_wcwgbdqk_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_wcwgbdqk_confirm)//确定/下一步/通过
            val reject = contentView.findViewById<TextView>(R.id.pop_add_wcwgbdqk_reject)
            val close = contentView.findViewById<TextView>(R.id.pop_add_wcwgbdqk_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_wcwgbdqk_ell)

            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addwcwgbdqkUpPopu!!.showAtLocation(sv_frag_ldgl_up, Gravity.CENTER, 0, 0)


                tv_pop_add_wcwgbdqk.setText(mtl_frag_ldgl_rkbdtime.addText)//msg.get(0)
                for (i in bcwcjybdqkEntitys){
                    if (mtl_frag_ldgl_rkbdtime.addText.equals(i.years)){//msg.get(0)
                        et_scwcwg.setText(i.cqwgzs)
//                        et_bdjy.setText(i.bdjy)
                        et_xwqn.setText(i.xwqn)
                        et_qwsn.setText(i.qwsn)
                        et_wss.setText(i.wss)
                        bcwcjybdqkEntitysId = i.id
                        bcwcjybdqkProcess = i.process
                        if (AppCache.getInstance().type == 4){
                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bcwcjybdqkProcess){
                                1->{
//                                    confirm.visibility = View.VISIBLE
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
                                if (bcwcjybdqkProcess == 2){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
                                et_czrname.setText(i.qvname)
                                if (bcwcjybdqkProcess == 3){
//                                    confirm.visibility = View.VISIBLE
//                                    reject.visibility = View.VISIBLE
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
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
                        et_scwcwg.setText("")
//                        et_bdjy.setText("")
                        et_xwqn.setText("")
                        et_qwsn.setText("")
                        et_wss.setText("")
                        bcwcjybdqkEntitysId = 0L
                        bcwcjybdqkProcess = 0
                        if (AppCache.getInstance().type == 4){
                            enableLl.setNoClick(false)
//                            confirm.visibility = View.VISIBLE
                            reject.visibility = View.VISIBLE
                        }else{
                            enableLl.setNoClick(true)
                            confirm.visibility = View.GONE
                            reject.visibility = View.GONE
                        }
                    }
                }
                /*tv_pop_add_wcwgbdqk.setOnClickListener {

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_wcwgbdqk.setText(msg.get(options1))

                        for (i in bcwcjybdqkEntitys){
                            if (msg.get(0).equals(i.years)){
                                et_scwcwg.setText(i.cqwgzs)
                                et_bdjy.setText(i.bdjy)
                                et_xwqn.setText(i.xwqn)
                                et_qwsn.setText(i.qwsn)
                                et_wss.setText(i.wss)
                                bcwcjybdqkEntitysId = i.id
                                bcwcjybdqkProcess = i.process
                                if (AppCache.getInstance().type == 4){
                                    et_czrname.setText(i.cunname)
                                    enableLl.setNoClick(false)
                                    when(bcwcjybdqkProcess){
                                        1->{
//                                            confirm.visibility = View.VISIBLE
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
                                        if (bcwcjybdqkProcess == 2){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else if (AppCache.getInstance().type == 2){
                                        et_czrname.setText(i.qvname)
                                        if (bcwcjybdqkProcess == 3){
//                                            confirm.visibility = View.VISIBLE
//                                            reject.visibility = View.VISIBLE
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
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
                                et_scwcwg.setText("")
                                et_bdjy.setText("")
                                et_xwqn.setText("")
                                et_qwsn.setText("")
                                et_wss.setText("")
                                bcwcjybdqkEntitysId = 0L
                                bcwcjybdqkProcess = 0
                                if (AppCache.getInstance().type == 4){
                                    enableLl.setNoClick(false)
//                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    enableLl.setNoClick(true)
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }
                        }


//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_wcwgbdqk.text.toString()))
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
                        val entity = BcwcjybdqkEntity()
                        entity.cqwgzs = et_scwcwg.text.toString()
//                        entity.bdjy = et_bdjy.text.toString()
                        entity.xwqn = et_xwqn.text.toString()
                        entity.qwsn = et_qwsn.text.toString()
                        entity.wss = et_wss.text.toString()
                        entity.years = tv_pop_add_wcwgbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bcwcjybdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateWcwgbdqk(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        val entity = BcwcjybdqkEntity()
                        entity.cqwgzs = et_scwcwg.text.toString()
//                        entity.bdjy = et_bdjy.text.toString()
                        entity.xwqn = et_xwqn.text.toString()
                        entity.qwsn = et_qwsn.text.toString()
                        entity.wss = et_wss.text.toString()
                        entity.years = tv_pop_add_wcwgbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcwcjybdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateWcwgbdqk(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        val entity = BcwcjybdqkEntity()
                        entity.cqwgzs = et_scwcwg.text.toString()
//                        entity.bdjy = et_bdjy.text.toString()
                        entity.xwqn = et_xwqn.text.toString()
                        entity.qwsn = et_qwsn.text.toString()
                        entity.wss = et_wss.text.toString()
                        entity.years = tv_pop_add_wcwgbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bcwcjybdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateWcwgbdqk(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        val entity = BcwcjybdqkEntity()
                        entity.cqwgzs = et_scwcwg.text.toString()
//                        entity.bdjy = et_bdjy.text.toString()
                        entity.xwqn = et_xwqn.text.toString()
                        entity.qwsn = et_qwsn.text.toString()
                        entity.wss = et_wss.text.toString()
                        entity.years = tv_pop_add_wcwgbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcwcjybdqkEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (et_reject.text.toString().equals("")){
                            ToastUtils.showShort("请输入驳回原因！")
                            ll_reject.visibility = View.VISIBLE
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            val bcrejectedEntity = BcrejectedEntity()
                            bcrejectedEntity.content = et_reject.text.toString()
                            entity.bcrejectedEntities.add(bcrejectedEntity)
                            ll_reject.visibility = View.GONE
                            mPresenter.getBcjcUpdateWcwgbdqk(entity)
                        }
                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        val entity = BcwcjybdqkEntity()
                        entity.cqwgzs = et_scwcwg.text.toString()
//                        entity.bdjy = et_bdjy.text.toString()
                        entity.xwqn = et_xwqn.text.toString()
                        entity.qwsn = et_qwsn.text.toString()
                        entity.wss = et_wss.text.toString()
                        entity.years = tv_pop_add_wcwgbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                            if (et_czrname.text.toString().equals("")){
                                ToastUtils.showShort("请输入操作员姓名！")
                            }/*else if (TdlyUtils.setEditToast(et_scwcwg,et_xwqn,et_qwsn,et_wss)){//,et_bdjy
                            }*/else{
                                entity.cunname = et_czrname.text.toString()
                                if (bcwcjybdqkEntitysId==0L){
                                    wcwgEntity = entity
                                    mPresenter.getBcjcAddWcwgbdqk(entity)
                                }else{
                                    entity.id = bcwcjybdqkEntitysId
                                    wcwgEntity = entity
                                    mPresenter.getBcjcUpdateWcwgbdqk(entity)
                                }

                            }


                    }
                    //村 下一步
                    confirm.setOnClickListener {
                        val entity = BcwcjybdqkEntity()
                        entity.cqwgzs = et_scwcwg.text.toString()
//                        entity.bdjy = et_bdjy.text.toString()
                        entity.xwqn = et_xwqn.text.toString()
                        entity.qwsn = et_qwsn.text.toString()
                        entity.wss = et_wss.text.toString()
                        entity.years = tv_pop_add_wcwgbdqk.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else if (TdlyUtils.setEditToast(et_scwcwg,et_xwqn,et_qwsn,et_wss)){//,et_bdjy
                        }else{
                            entity.cunname = et_czrname.text.toString()
                            if (bcwcjybdqkEntitysId == 0L){
                                mPresenter.getBcjcAddWcwgbdqk(entity)
                            }else{
                                entity.id = bcwcjybdqkEntitysId
                                mPresenter.getBcjcUpdateWcwgbdqk(entity)
                            }
                        }
                    }
                }
            }

            close.setOnClickListener {
                addwcwgbdqkUpPopu!!.dismiss()
            }
            }

        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            tv_frag_rkjy_cgbjbqkqk_add.text = "操作"
        }else {
            tv_frag_rkjy_cgbjbqkqk_add.visibility = View.GONE
        }



        //提交上报or通过
        bt_act_rkjy_xyb.setOnClickListener {
            when(AppCache.getInstance().type){
                2->{
                    uploadOrRejectData(1,4)
                }
                3->{
                    uploadOrRejectData(1,3)
                }
                4->{
                    uploadOrRejectData(1,2)
                }
            }
        }

        //驳回
        bt_act_rkjy_bh.setOnClickListener {
            when(AppCache.getInstance().type){
                2->{
                    uploadOrRejectData(2,4)
                }
                3->{
                    uploadOrRejectData(2,3)
                }
                4->{
                    uploadOrRejectData(2,2)
                }
            }
        }
    }

    //提交上报or通过or驳回逻辑方法
    /**
     * type 1下一步2驳回
     * process 2村3镇4区
     */
    fun uploadOrRejectData(type:Int,process:Int){

        mUploadPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject, view)//pop_point
        val contentView = mUploadPopu?.getContentView()
        val tv_bcjc_reject_title = contentView?.findViewById<TextView>(R.id.tv_bcjc_reject_title)//标题
        val et_pop_bcjc_reject_clr = contentView?.findViewById<EditText>(R.id.et_pop_bcjc_reject_clr)//处理人
        val pop_bcjc_reject_et_reject = contentView?.findViewById<EditText>(R.id.pop_bcjc_reject_et_reject)//驳回原因
        val ll_pop_bcjc_reject_bhyy = contentView?.findViewById<LinearLayout>(R.id.ll_pop_bcjc_reject_bhyy)//驳回原因
        val pop_bcjc_reject_confirm = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_confirm)//确定
        val pop_bcjc_reject_close = contentView?.findViewById<TextView>(R.id.pop_bcjc_reject_close)//取消


        pop_bcjc_reject_close?.setOnClickListener {
            mUploadPopu?.dismiss()
        }
        CommenPop.backgroundAlpha(0.5f, activity)
        mUploadPopu?.showAtLocation(view, Gravity.CENTER, 0, 0)
        if (type == 1){
            if (AppCache.getInstance().type == 4){
                tv_bcjc_reject_title?.setText("提交上报")
            }else{
                tv_bcjc_reject_title?.setText("通过")
            }
            ll_pop_bcjc_reject_bhyy?.visibility = View.GONE
        }else{
                tv_bcjc_reject_title?.setText("驳回")
            ll_pop_bcjc_reject_bhyy?.visibility = View.VISIBLE
        }


        pop_bcjc_reject_confirm!!.setOnClickListener {

            if (et_pop_bcjc_reject_clr!!.text.toString().equals("")){
                ToastUtils.showShort("请输入处理人")
            }else{
                if (type == 2){
                    if (pop_bcjc_reject_et_reject!!.text.toString().equals("")){
                        ToastUtils.showShort("请输入驳回原因")
                        return@setOnClickListener
                    }
                }//rkjyEntity
                if (TdlyUtils.setObjectToast(rkjyEntity.qchs,rkjyEntity.qcrk,rkjyEntity.nyhjh,rkjyEntity.nyhjr,rkjyEntity.nlss,rkjyEntity.nlls
                                ,rkjyEntity.bcczrk,rkjyEntity.wlczrk)){
                    ToastUtils.showShort("请完善人口变动表信息")
                }else if (TdlyUtils.setObjectToast(ldlbdEntity.ldlzs,ldlbdEntity.nlss,ldlbdEntity.cyyc,ldlbdEntity.cyec,ldlbdEntity.sysc
                                ,ldlbdEntity.jtjy,ldlbdEntity.cjtjy,ldlbdEntity.cjtjjzz,ldlbdEntity.wclg)){
                    ToastUtils.showShort("请完善劳动力变动情况表信息")
                }else if (TdlyUtils.setObjectToast(wcwgEntity.cqwgzs,wcwgEntity.xwqn,wcwgEntity.qwsn,wcwgEntity.wss)){
                    ToastUtils.showShort("请完善劳外出务工人员就业结构变动表信息")
                }else if (TdlyUtils.setObjectToast(bccgbqkEntity.csjmc,bccgbqkEntity.csjsjh
                                ,bccgbqkEntity.csjwhcd ,bccgbqkEntity.csjnl,bccgbqkEntity.csjrz,bccgbqkEntity.czrmc
                                ,bccgbqkEntity.czrsjh,bccgbqkEntity.czrwhcd,bccgbqkEntity.czrnl,bccgbqkEntity.czrrz,bccgbqkEntity.cszmc
                                ,bccgbqkEntity.cszsjh,bccgbqkEntity.cszwhcd,bccgbqkEntity.csznl,bccgbqkEntity.cszrz)){
                    ToastUtils.showShort("请完善村干部基本信息")
                }else{
                    if (TdlyUtils.setObjectToast(rkjyEntity1978.qchs,rkjyEntity1978.qcrk,rkjyEntity1978.nyhjh,rkjyEntity1978.nyhjr,rkjyEntity1978.nlss,rkjyEntity1978.nlls
                                    ,rkjyEntity1978.bcczrk,rkjyEntity1978.wlczrk)&&bcrkbdqkEntitysId1978!=0L){
                        ToastUtils.showShort("请完善人口变动表1978信息")
                    }else{
                        when(AppCache.getInstance().type){
                            2->{
                                rkjyEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                                ldlbdEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                                wcwgEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                                bccgbqkEntity.qvname = et_pop_bcjc_reject_clr.text.toString()
                                rkjyEntity.id = bcrkbdqkEntitysId
//                        rkjyEntity1978.id = bcrkbdqkEntitysId1978
                                ldlbdEntity.id = bcldlbdqkEntitysId
                                wcwgEntity.id = bcwcjybdqkEntitysId
                                if (type == 1){
                                    rkjyEntity.process = process
                                    ldlbdEntity.process = process
                                    wcwgEntity.process = process
                                    bccgbqkEntity.process = process
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(rkjyEntity)
                                    /*if (rkjyEntity1978.id!=0L){
                                        rkjyEntity1978.process = 4
                                        arrayList.add(rkjyEntity1978)
                                    }*/
                                    mPresenter.getBcjcAddRkbdqk(arrayList)
                                    mPresenter.getBcjcUpdateLdlbdqk(ldlbdEntity)
                                    mPresenter.getBcjcUpdateWcwgbdqk(wcwgEntity)
                                    mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                                }else{
                                    rkjyEntity.process = 1
                                    ldlbdEntity.process = 1
                                    wcwgEntity.process = 1
                                    bccgbqkEntity.process = 1
                                    val bcrejectedEntity = BcrejectedEntity()
                                    bcrejectedEntity.content = pop_bcjc_reject_et_reject!!.text.toString()
                                    rkjyEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    ldlbdEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    wcwgEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    bccgbqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(rkjyEntity)
                                    mPresenter.getBcjcAddRkbdqk(arrayList)
//                                    mPresenter.getBcjcUpdateRkbdqk(rkjyEntity)
                                    mPresenter.getBcjcUpdateLdlbdqk(ldlbdEntity)
                                    mPresenter.getBcjcUpdateWcwgbdqk(wcwgEntity)
                                    mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                                }
                            }
                            3->{
                                rkjyEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                                rkjyEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                                ldlbdEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                                wcwgEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                                bccgbqkEntity.zhenname = et_pop_bcjc_reject_clr.text.toString()
                                rkjyEntity.id = bcrkbdqkEntitysId
//                        rkjyEntity1978.id = bcrkbdqkEntitysId1978
                                ldlbdEntity.id = bcldlbdqkEntitysId
                                wcwgEntity.id = bcwcjybdqkEntitysId
                                if (type == 1){
                                    rkjyEntity.process = process
                                    ldlbdEntity.process = process
                                    wcwgEntity.process = process
                                    bccgbqkEntity.process = process
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(rkjyEntity)
                                    /*if (rkjyEntity1978.id!=0L){
                                        rkjyEntity1978.process = 4
                                        arrayList.add(rkjyEntity1978)
                                    }*/
                                    mPresenter.getBcjcAddRkbdqk(arrayList)
                                    mPresenter.getBcjcUpdateLdlbdqk(ldlbdEntity)
                                    mPresenter.getBcjcUpdateWcwgbdqk(wcwgEntity)
                                    mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                                }else{
                                    rkjyEntity.process = 1
                                    ldlbdEntity.process = 1
                                    wcwgEntity.process = 1
                                    bccgbqkEntity.process = 1
                                    val bcrejectedEntity = BcrejectedEntity()
                                    bcrejectedEntity.content = pop_bcjc_reject_et_reject!!.text.toString()
                                    rkjyEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    ldlbdEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    wcwgEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    bccgbqkEntity.bcrejectedEntities.add(bcrejectedEntity)
                                    val arrayList = ArrayList<BcrkbdqkEntity>()
                                    arrayList.add(rkjyEntity)
                                    mPresenter.getBcjcAddRkbdqk(arrayList)
//                                    mPresenter.getBcjcUpdateRkbdqk(rkjyEntity)
                                    mPresenter.getBcjcUpdateLdlbdqk(ldlbdEntity)
                                    mPresenter.getBcjcUpdateWcwgbdqk(wcwgEntity)
                                    mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                                }
                            }
                            4->{
                                rkjyEntity.process = process
                                rkjyEntity1978.process = 4
                                ldlbdEntity.process = process
                                wcwgEntity.process = process
                                bccgbqkEntity.process = process
                                rkjyEntity.id = bcrkbdqkEntitysId
                                rkjyEntity1978.id = bcrkbdqkEntitysId1978
                                ldlbdEntity.id = bcldlbdqkEntitysId
                                wcwgEntity.id = bcwcjybdqkEntitysId
                                rkjyEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                                rkjyEntity1978.cunname = et_pop_bcjc_reject_clr.text.toString()
                                ldlbdEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                                wcwgEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                                bccgbqkEntity.cunname = et_pop_bcjc_reject_clr.text.toString()
                                val arrayList = ArrayList<BcrkbdqkEntity>()
                                arrayList.add(rkjyEntity)
                                if (rkjyEntity1978.id!=0L&&rkjyEntity1978.process != 4){
                                    rkjyEntity1978.process = 4
                                    arrayList.add(rkjyEntity1978)
                                }
                                mPresenter.getBcjcAddRkbdqk(arrayList)
                                if (bcldlbdqkEntitysId==0L){
                                    mPresenter.getBcjcAddLdlbdqk(ldlbdEntity)
                                }else{
                                    mPresenter.getBcjcUpdateLdlbdqk(ldlbdEntity)
                                }
                                if (bcwcjybdqkEntitysId==0L){
                                    mPresenter.getBcjcAddWcwgbdqk(wcwgEntity)
                                }else{
                                    mPresenter.getBcjcUpdateWcwgbdqk(wcwgEntity)
                                }
                                mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                            }
                        }
                    }

                }
            }


        }
    }

    fun cgbPopup(type:Int,bccgbqkEntity:BccgbqkEntity){

        addcgbjbqkUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_cgbjbqk, sv_frag_ldgl_up)//sv_frag_ldgl_up
        val contentView: View = addcgbjbqkUpPopu!!.getContentView()
        val cgbjbqk_tv = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_tv)
        val et_csjname = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_csjname)
        val et_csjphone = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_csjphone)
        val tv_csjwhcd = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_tv_csjwhcd)
        val sp_csjwhcd = contentView.findViewById<Spinner>(R.id.pop_add_cgbjbqk_sp_csjwhcd)
        val ll_csjwhcd = contentView.findViewById<LinearLayout>(R.id.ll_add_cgbjbqk_sp_csjwhcd)
        val et_csjage = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_csjage)
        val et_csjtime = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_et_csjtime)
        val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_czrname)
        val et_czrphone = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_czrphone)
        val sp_czrwhcd = contentView.findViewById<Spinner>(R.id.pop_add_cgbjbqk_sp_czrwhcd)
        val et_czrage = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_czrage)
        val et_czrtime = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_et_czrtime)
        val et_cszname = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_cgfhzsszname)
        val et_cszphone = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_cgfhzsszphone)
        val sp_cszwhcd = contentView.findViewById<Spinner>(R.id.pop_add_cgbjbqk_sp_cgfhzsszwhcd)
        val et_cszage = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_cgfhzsszage)
        val et_csztime = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_et_cgfhzssztime)

        val et_dyrs = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_dyrs)
        val et_50dyrs = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_50dyrs)
        val sp_isdysj = contentView.findViewById<Spinner>(R.id.pop_add_cgbjbqk_sp_isdysj)
        val ll_dwmc = contentView.findViewById<LinearLayout>(R.id.pop_add_cgbjbqk_ll_dwmc)
        val et_dwmc = contentView.findViewById<EditText>(R.id.pop_add_cgbjbqk_et_dwmc)
        val confirm = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_confirm)
        val commit = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_commit)
        val close = contentView.findViewById<TextView>(R.id.pop_add_cgbjbqk_close)
        val ll_czr = contentView.findViewById<LinearLayout>(R.id.pop_add_cgbjbqk_ll_czr)
        val ll_cgfhzssz = contentView.findViewById<LinearLayout>(R.id.pop_add_cgbjbqk_ll_cgfhzssz)
        val dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.margin_zdkd)- ScreenUtils.dpToPx(activity,60f).toInt()
        et_csjtime.setOnClickListener {
            TimePickerUtil.initTimePickerViewZhi(activity,et_csjtime.text.toString(),object:TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    et_csjtime.setText(data)
                }
            })
        }
        et_czrtime.setOnClickListener {
            TimePickerUtil.initTimePickerViewZhi(activity,et_czrtime.text.toString(),object:TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    et_czrtime.setText(data)
                }
            })
        }
        et_csztime.setOnClickListener {
            TimePickerUtil.initTimePickerViewZhi(activity,et_csztime.text.toString(),object:TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    et_csztime.setText(data)
                }
            })
        }
        sp_csjwhcd.dropDownWidth = dimensionPixelSize/4
        sp_czrwhcd.dropDownWidth = dimensionPixelSize/4
        sp_cszwhcd.dropDownWidth = dimensionPixelSize/4
        sp_isdysj.dropDownWidth = dimensionPixelSize/5*2
        CommenPop.backgroundAlpha(0.5f, activity)
        addcgbjbqkUpPopu!!.showAtLocation(sv_frag_ldgl_up, Gravity.CENTER, 0, 0)


        tv_csjwhcd.setOnClickListener {
            TdlyUtils.setTextToast(tv_csjwhcd.text.toString())
    }

        close.setOnClickListener {
            addcgbjbqkUpPopu!!.dismiss()
        }

        if (type == 1){
            ll_czr.visibility = View.GONE
            ll_cgfhzssz.visibility = View.GONE
            cgbjbqk_tv.text = "一肩挑干部"
        }else{
            ll_czr.visibility = View.VISIBLE
            ll_cgfhzssz.visibility = View.VISIBLE
            cgbjbqk_tv.text = "村书记"
        }


        val whcd = ArrayList<String>()
        whcd.add("1")
        whcd.add("2")
        whcd.add("3")
        whcd.add("4")
        val sprAdapter1 = com.jymj.zhglxt.widget.SpinnerAdapter(activity)
        sp_csjwhcd.adapter = sprAdapter1
        sprAdapter1.setDatas(whcd)
        val sprAdapter2 = com.jymj.zhglxt.widget.SpinnerAdapter(activity)
        sp_czrwhcd.adapter = sprAdapter2
        sprAdapter2.setDatas(whcd)
        val sprAdapter3 = com.jymj.zhglxt.widget.SpinnerAdapter(activity)
        sp_cszwhcd.adapter = sprAdapter3
        sprAdapter3.setDatas(whcd)
        val issj = ArrayList<String>()
        issj.add("A")
        issj.add("B")
        val sprAdapter4 = com.jymj.zhglxt.widget.SpinnerAdapter(activity)
        sp_isdysj.adapter = sprAdapter4
        sprAdapter4.setDatas(issj)
        sp_isdysj.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    ll_dwmc.visibility = View.VISIBLE
                }else{
                    ll_dwmc.visibility = View.GONE
                }
            }
        }


        if (bccgbqkEntity.id!=null){
            et_csjname.setText(bccgbqkEntity.csjmc)
            et_czrname.setText(bccgbqkEntity.czrmc)
            et_cszname.setText(bccgbqkEntity.cszmc)

            et_csjphone.setText(bccgbqkEntity.csjsjh)
            et_czrphone.setText(bccgbqkEntity.czrsjh)
            et_cszphone.setText(bccgbqkEntity.cszsjh)

            sp_csjwhcd.setSelection(whcd.indexOf(bccgbqkEntity.csjwhcd.toString()),true)
            sp_czrwhcd.setSelection(whcd.indexOf(bccgbqkEntity.czrwhcd.toString()),true)
            sp_cszwhcd.setSelection(whcd.indexOf(bccgbqkEntity.cszwhcd.toString()),true)

            et_csjage.setText(bccgbqkEntity.csjnl.toString())
            et_czrage.setText(bccgbqkEntity.czrnl.toString())
            et_cszage.setText(bccgbqkEntity.csznl.toString())

            et_csjtime.setText(bccgbqkEntity.csjrz)
            et_czrtime.setText(bccgbqkEntity.czrrz)
            et_csztime.setText(bccgbqkEntity.cszrz)

            et_dyrs.setText(bccgbqkEntity.dyrs.toString())
            et_50dyrs.setText(bccgbqkEntity.dyrs50.toString())
            sp_isdysj.setSelection(issj.indexOf(bccgbqkEntity.sfydysj.toString()),true)
            et_dwmc.setText(bccgbqkEntity.dwmc.toString())

        }

        commit.setOnClickListener {
//            val entity = BccgbqkEntity()
            /*bccgbqkEntity.csjmc = et_csjname.text.toString()
            bccgbqkEntity.csjsjh = et_csjphone.text.toString()
            bccgbqkEntity.csjwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals("")){
                    bccgbqkEntity.csjnl = et_csjage.text.toString().toInt()
                }
            bccgbqkEntity.csjrz = et_csjtime.text.toString()

            bccgbqkEntity.czrmc = et_czrname.text.toString()
            bccgbqkEntity.czrsjh = et_czrphone.text.toString()
            bccgbqkEntity.czrwhcd = sp_czrwhcd.selectedItem.toString().toInt()
                if (!et_czrage.text.toString().equals("")){
                    bccgbqkEntity.czrnl = et_czrage.text.toString().toInt()
                }
            bccgbqkEntity.czrrz = et_czrtime.text.toString()

            bccgbqkEntity.cszmc = et_cszname.text.toString()
            bccgbqkEntity.cszsjh = et_cszphone.text.toString()
            bccgbqkEntity.cszwhcd = sp_cszwhcd.selectedItem.toString().toInt()
                if (!et_cszage.text.toString().equals("")){
                    bccgbqkEntity.csznl = et_cszage.text.toString().toInt()
                }
            bccgbqkEntity.cszrz = et_csztime.text.toString()
            if (!et_dyrs.text.toString().equals("")){
                bccgbqkEntity.dyrs = et_dyrs.text.toString().toInt()
            }
            if (!et_50dyrs.text.toString().equals("")){
                bccgbqkEntity.dyrs50 = et_50dyrs.text.toString().toInt()
            }
            bccgbqkEntity.sfydysj = sp_isdysj.selectedItem.toString()
            bccgbqkEntity.dwmc = et_dwmc.text.toString()
            bccgbqkEntity.code = AppCache.getInstance().code
            bccgbqkEntity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
            bccgbqkEntity.process = 4
            mPresenter.getBcjcAddCgbqk(bccgbqkEntity)*/

            if (type == 1){
                bccgbqkEntity.csjmc = et_csjname.text.toString()
                bccgbqkEntity.csjsjh = et_csjphone.text.toString()
                bccgbqkEntity.csjwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals(""))
                    bccgbqkEntity.csjnl = et_csjage.text.toString().toInt()
                bccgbqkEntity.csjrz = et_csjtime.text.toString()

                bccgbqkEntity.czrmc = et_csjname.text.toString()
                bccgbqkEntity.czrsjh = et_csjphone.text.toString()
                bccgbqkEntity.czrwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals(""))
                    bccgbqkEntity.czrnl = et_csjage.text.toString().toInt()
                bccgbqkEntity.czrrz = et_csjtime.text.toString()

                bccgbqkEntity.cszmc = et_csjname.text.toString()
                bccgbqkEntity.cszsjh = et_csjphone.text.toString()
                bccgbqkEntity.cszwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals(""))
                    bccgbqkEntity.csznl = et_csjage.text.toString().toInt()
                bccgbqkEntity.cszrz = et_csjtime.text.toString()

            }else{
                bccgbqkEntity.csjmc = et_csjname.text.toString()
                bccgbqkEntity.csjsjh = et_csjphone.text.toString()
                bccgbqkEntity.csjwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals("")){
                    bccgbqkEntity.csjnl = et_csjage.text.toString().toInt()
                }
                bccgbqkEntity.csjrz = et_csjtime.text.toString()

                bccgbqkEntity.czrmc = et_czrname.text.toString()
                bccgbqkEntity.czrsjh = et_czrphone.text.toString()
                bccgbqkEntity.czrwhcd = sp_czrwhcd.selectedItem.toString().toInt()
                if (!et_czrage.text.toString().equals("")){
                    bccgbqkEntity.czrnl = et_czrage.text.toString().toInt()
                }
                bccgbqkEntity.czrrz = et_czrtime.text.toString()

                bccgbqkEntity.cszmc = et_cszname.text.toString()
                bccgbqkEntity.cszsjh = et_cszphone.text.toString()
                bccgbqkEntity.cszwhcd = sp_cszwhcd.selectedItem.toString().toInt()
                if (!et_cszage.text.toString().equals("")){
                    bccgbqkEntity.csznl = et_cszage.text.toString().toInt()
                }
                bccgbqkEntity.cszrz = et_csztime.text.toString()

            }
            if (!et_dyrs.text.toString().equals("")){
                bccgbqkEntity.dyrs = et_dyrs.text.toString().toInt()
            }
            if (!et_50dyrs.text.toString().equals("")){
                bccgbqkEntity.dyrs50 = et_50dyrs.text.toString().toInt()
            }
            bccgbqkEntity.process = 1
            bccgbqkEntity.sfydysj = sp_isdysj.selectedItem.toString()
            bccgbqkEntity.dwmc = et_dwmc.text.toString()
            bccgbqkEntity.years = mtl_frag_ldgl_rkbdtime.addText//TimeUtils.getNowString().split("-")[0]+"年"
            bccgbqkEntity.code = AppCache.getInstance().code
            bccgbqkEntity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()

            if (type==1){
                if (!TdlyUtils.setEditToast(et_csjname,et_csjphone,et_csjage,et_csjtime,et_dyrs,et_50dyrs)){
                    if (sp_isdysj.selectedItem.toString().equals("A")){
                        if (!et_dwmc.text.toString().equals("")){
                            if (bccgbqkEntity.id!=null){
                                mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                            }else{
                                mPresenter.getBcjcAddCgbqk(bccgbqkEntity)
                            }
                        }else{
                            ToastUtils.showShort("请输入单位名称")
                        }
                    }else{
                        if (bccgbqkEntity.id!=null){
                            mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                        }else{
                            mPresenter.getBcjcAddCgbqk(bccgbqkEntity)
                        }
                    }
                }
            }else{
                if (!TdlyUtils.setEditToast(et_csjname,et_csjphone,et_csjage,et_csjtime,et_czrname,et_czrphone,et_czrage
                                ,et_czrtime,et_cszname,et_cszphone,et_cszage,et_csztime,et_dyrs,et_50dyrs)){//,et_dwmc
                    if (bccgbqkEntity.id!=null){
                        mPresenter.getBchcUpdateCgbqk(bccgbqkEntity)
                    }else{
                        mPresenter.getBcjcAddCgbqk(bccgbqkEntity)
                    }
                }
            }

        }
        confirm.setOnClickListener {//commit
            val entity = BccgbqkEntity()
            if (type == 1){
                entity.csjmc = et_csjname.text.toString()
                entity.csjsjh = et_csjphone.text.toString()
                entity.csjwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals(""))
                entity.csjnl = et_csjage.text.toString().toInt()
                entity.csjrz = et_csjtime.text.toString()

                entity.czrmc = et_csjname.text.toString()
                entity.czrsjh = et_csjphone.text.toString()
                entity.czrwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals(""))
                entity.czrnl = et_csjage.text.toString().toInt()
                entity.czrrz = et_csjtime.text.toString()

                entity.cszmc = et_csjname.text.toString()
                entity.cszsjh = et_csjphone.text.toString()
                entity.cszwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals(""))
                entity.csznl = et_csjage.text.toString().toInt()
                entity.cszrz = et_csjtime.text.toString()
            }else{
                entity.csjmc = et_csjname.text.toString()
                entity.csjsjh = et_csjphone.text.toString()
                entity.csjwhcd = sp_csjwhcd.selectedItem.toString().toInt()
                if (!et_csjage.text.toString().equals("")){
                    entity.csjnl = et_csjage.text.toString().toInt()
                }
                entity.csjrz = et_csjtime.text.toString()

                entity.czrmc = et_czrname.text.toString()
                entity.czrsjh = et_czrphone.text.toString()
                entity.czrwhcd = sp_czrwhcd.selectedItem.toString().toInt()
                if (!et_czrage.text.toString().equals("")){
                    entity.czrnl = et_czrage.text.toString().toInt()
                }
                entity.czrrz = et_czrtime.text.toString()

                entity.cszmc = et_cszname.text.toString()
                entity.cszsjh = et_cszphone.text.toString()
                entity.cszwhcd = sp_cszwhcd.selectedItem.toString().toInt()
                if (!et_cszage.text.toString().equals("")){
                    entity.csznl = et_cszage.text.toString().toInt()
                }
                entity.cszrz = et_csztime.text.toString()
            }
            if (!et_dyrs.text.toString().equals("")){
                entity.dyrs = et_dyrs.text.toString().toInt()
            }
            if (!et_50dyrs.text.toString().equals("")){
                entity.dyrs50 = et_50dyrs.text.toString().toInt()
            }
            entity.sfydysj = sp_isdysj.selectedItem.toString()
            entity.dwmc = et_dwmc.text.toString()
            entity.code = AppCache.getInstance().code
            entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
//            mPresenter.getBcjcAddCgbqk(entity)
            if (type==1){
                if (!TdlyUtils.setEditToast(et_csjname,et_csjphone,et_csjage,et_csjtime,et_dyrs,et_50dyrs)){//,et_dwmc
                    mPresenter.getBcjcAddCgbqk(entity)
                }
            }else{
                if (!TdlyUtils.setEditToast(et_csjname,et_csjphone,et_csjage,et_csjtime,et_czrname,et_czrphone,et_czrage
                                ,et_czrtime,et_cszname,et_cszphone,et_cszage,et_csztime,et_dyrs,et_50dyrs)){//,et_dwmc
                    mPresenter.getBcjcAddCgbqk(entity)
                }
            }

        }
    }


    override fun returnBcjcGetInwcry(msg: List<BcwcjybdqkEntity>) {
        if (msg!=null){
            var strings = ArrayList<String>()
            strings.add("村外乡内")
            strings.add("乡外区内")
            strings.add("区外市内")
            strings.add("外省市")
//            val callDurationColors = intArrayOf(Color.parseColor("#5B9BD5"), Color.parseColor("#ED7D31"))
            val callDurationColors = ArrayList<Int>()
//            val labels = arrayOf("收入", "支出")
            val labels = ArrayList<String>()
            for (i in ColorTemplate.VORDIPLOM_COLORS.indices) {
                callDurationColors.add(ColorTemplate.VORDIPLOM_COLORS.get(i)) //MATERIAL_COLORS
            }
            for (i in ColorTemplate.COLORFUL_COLORS.indices) {
                callDurationColors.add(ColorTemplate.COLORFUL_COLORS.get(i)) //MATERIAL_COLORS
            }
            for (i in ColorTemplate.PASTEL_COLORS.indices) {
                callDurationColors.add(ColorTemplate.PASTEL_COLORS.get(i)) //MATERIAL_COLORS
            }
            for (f in msg){
                labels.add(f.years)
            }
            val arrayList = arrayOfNulls<ArrayList<Entry>>(labels.size)
//            for (i in strings.indices){

//                arrayList.add(Entry(i.toFloat()+0.5f))

                for (i in msg.indices){
                    val f = msg.get(i)
                    labels.add(f.years)
                    var arrayList1 = ArrayList<Entry>()
                    arrayList1.add(Entry(0.toFloat()+0.5f,f.bdjy.toFloat(),"村外乡内"))
                    arrayList1.add(Entry(1.toFloat()+0.5f,f.xwqn.toFloat(),"乡外区内"))
                    arrayList1.add(Entry(2.toFloat()+0.5f,f.qwsn.toFloat(),"区外市内"))
                    arrayList1.add(Entry(3.toFloat()+0.5f,f.wss.toFloat(),"外省市"))
                    arrayList[i] = arrayList1

                }
//            }
            if (msg.size>0){
                if (AppCache.getInstance().type!=4)
                ll_frag_ldgl_lncqwc.visibility = View.VISIBLE
                PieTdxgUtil.updateLinehart1(activity!!,bc_frag_ldgl_lncqwc,callDurationColors.toIntArray(),"",arrayList,labels.toTypedArray(),strings)

            }else{
                ll_frag_ldgl_lncqwc.visibility = View.GONE
            }

            /*val strings = ArrayList<String>()//
            val colors = ArrayList<Int>()//颜色
            val chartDataMap = LinkedHashMap<String, List<Float>>()//年
            colors.add(Color.parseColor("#5CA3E5"))
            var floatList = ArrayList<Float>()
            for (i in msg){
                strings.add(i.years)
                floatList.add(getStringToFloat(i.cqwgzs))
            }
            if (strings.size>0){
                ll_frag_ldgl_lncqwc.visibility = View.VISIBLE
            }else{
                ll_frag_ldgl_lncqwc.visibility = View.GONE
            }
            chartDataMap.put("历年长期外出务工人员列表",floatList)
//            PieTdxgUtil.setZzt(strings,colors,chartDataMap,bc_frag_ldgl_lncqwc)
//            PieTdxgUtil.zjdSpBar(activity!!,floatList,strings,bc_frag_ldgl_lncqwc)
            if (msg.size>0){
                ll_frag_ldgl_lncqwc.visibility = View.VISIBLE
                PieTdxgUtil.setZztDs(activity!!,bc_frag_ldgl_lncqwc,msg)
            }else{
                ll_frag_ldgl_lncqwc.visibility = View.GONE
            }*/
        }else{
            ll_frag_ldgl_lncqwc.visibility = View.GONE
        }
    }

    override fun returnBchcWcwgbdqk(msg: List<BcwcjybdqkEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addwcwgbdqkUpPopu!=null){
            addwcwgbdqkUpPopu!!.dismiss()
        }
        if (msg!=null){
            if (msg.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = msg.get(0)

                when(get.process){
                    1->{
                        tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    2->{
                        tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    3->{
                        tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    4->{
                        tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                }
                tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,get.process-1,get.process-1)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_rkjy_wcwgbdqk_add.visibility = View.VISIBLE
                    tv_frag_rkjy_wcwgbdqk_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_rkjy_wcwgbdqk_add.visibility = View.VISIBLE
                        tv_frag_rkjy_wcwgbdqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_rkjy_wcwgbdqk_add.visibility = View.VISIBLE
                            tv_frag_rkjy_wcwgbdqk_add.setText("操作")
                        }else{
                            if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_rkjy_wcwgbdqk_add.setText("已上报")
                            }else{
                                tv_frag_rkjy_wcwgbdqk_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_rkjy_wcwgbdqk_add.visibility = View.GONE
                            }
                        }
            }else if (AppCache.getInstance().type==4){
                tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,-1,-1)
                tv_frag_rkjy_wcwgbdqk_add.visibility = View.VISIBLE
            }else if (AppCache.getInstance().type!=4){
                tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_rkjy_wcwgbdqk_add.visibility = View.GONE
                }
                tv_frag_rkjy_wcwgbdqk_add.setText("未上报")
            }

            rlv_frag_rkjy_wcwgbdqk.layoutManager = object:LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            rlv_frag_rkjy_wcwgbdqk.isFocusable = false
            rlv_frag_rkjy_wcwgbdqk.adapter = object :BaseQuickAdapter<BcwcjybdqkEntity, BaseViewHolder>(R.layout.item_bcjc_wcwgbdqk,msg){
                override fun convert(helper: BaseViewHolder?, item: BcwcjybdqkEntity    ?) {
                    helper!!.setText(R.id.item_bcjc_wcwgbdqk_year,item!!.years)
                            .setText(R.id.item_bcjc_wcwgbdqk_cqwcwgzs,item.cqwgzs)
                            .setText(R.id.item_bcjc_wcwgbdqk_bdjy,item.bdjy)
                            .setText(R.id.item_bcjc_wcwgbdqk_xwqn,item.xwqn)
                            .setText(R.id.item_bcjc_wcwgbdqk_qwsn,item.qwsn)
                            .setText(R.id.item_bcjc_wcwgbdqk_wss,item.wss)

                    val years = helper.getView<TextView>(R.id.item_bcjc_wcwgbdqk_year)
                    if (item.bcrejectedEntities.size>0){
                        tv_frag_zjd_ldrk_gzjdbh.visibility = View.VISIBLE
                        iv_frag_zjd_ldrk_gzjdbh.visibility = View.VISIBLE
                        tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#1B96EE"))
                        /*years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true*/
                    }else{
                        tv_frag_zjd_ldrk_gzjdbh.visibility = View.GONE
                        iv_frag_zjd_ldrk_gzjdbh.visibility = View.GONE
                        tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                        /*years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false*/
                    }
                    tv_frag_zjd_ldrk_gzjdbh.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }
                }

            }
            //tv_frag_ldgl_wwgryjy
           /* if (msg.size>0){
                tv_frag_ldgl_wwgryjy.setText(msg.get(0).years+"外出务工人员就业区域")

                var bhgsCount2 = ArrayList<Float>()
                var bhgsName2 = ArrayList<String>()
                val colors2 = ArrayList<Int>()//
                colors2.add(Color.parseColor("#5B9BD5"))
                colors2.add(Color.parseColor("#ED7D31"))
                colors2.add(Color.parseColor("#A5A5A5"))
                colors2.add(Color.parseColor("#FFC000"))
                bhgsCount2.add(getStringToFloat(msg.get(0).bdjy))
                bhgsCount2.add(getStringToFloat(msg.get(0).xwqn))
                bhgsCount2.add(getStringToFloat(msg.get(0).qwsn))
                bhgsCount2.add(getStringToFloat(msg.get(0).wss))
                bhgsName2.add("村外乡内")
                bhgsName2.add("乡外区内")
                bhgsName2.add("区外市内")
                bhgsName2.add("外省市")
                val s = getStringToFloat(msg.get(0).bdjy) + getStringToFloat(msg.get(0).xwqn) +
                        getStringToFloat(msg.get(0).qwsn) + getStringToFloat(msg.get(0).wss)
                if (s<=0f){
                    ll_frag_ldgl_wwgryjy.visibility = View.GONE
                }else{
                    ll_frag_ldgl_wwgryjy.visibility = View.VISIBLE
                }
                PieTdxgUtil.rkinitPie1(pie_frag_ldgl_wwgryjy, "", bhgsCount2, bhgsName2, colors2)//人口就业情况
            }else{
                ll_frag_ldgl_wwgryjy.visibility = View.GONE
            }*/

        }else{
            ToastUtils.showShort("该年份暂无数据")
            ll_frag_ldgl_wwgryjy.visibility = View.GONE
        }
    }

    override fun returnBcjcAddWcwgbdqk(msg: String) {

        ToastUtils.showShort(msg)
        if (addwcwgbdqkUpPopu!=null){
            addwcwgbdqkUpPopu!!.dismiss()
        }
        mPresenter.getBchcWcwgbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcWcwgbdqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcGetInwcry(AppCache.getInstance().code,yearsList)
    }
    override fun returnBchcLdlbdqk(msg: List<BcldlbdqkEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addldlbdqkUpPopu!=null){
            addldlbdqkUpPopu!!.dismiss()
        }
        if (msg!=null){
            rlv_frag_rkjy_ldlbdqk.layoutManager = object:LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            rlv_frag_rkjy_ldlbdqk.isFocusable = false
            rlv_frag_rkjy_ldlbdqk.adapter = object :BaseQuickAdapter<BcldlbdqkEntity, BaseViewHolder>(R.layout.item_bcjc_ldlbdqk,msg){
                override fun convert(helper: BaseViewHolder?, item: BcldlbdqkEntity?) {
                    helper!!.setText(R.id.item_bcjc_ldlbdqk_year,item!!.years)
                            .setText(R.id.item_bcjc_ldlbdqk_sjhjjyldlzs,item.ldlzs)
                            .setText(R.id.item_bcjc_ldlbdqk_xydy30,item.nlss)
                            .setText(R.id.item_bcjc_ldlbdqk_dydy60,item.nlls)
                            .setText(R.id.item_bcjc_ldlbdqk_yc,item.cyyc)
                            .setText(R.id.item_bcjc_ldlbdqk_ec,item.cyec)
                            .setText(R.id.item_bcjc_ldlbdqk_sc,item.sysc)
                            .setText(R.id.item_bcjc_ldlbdqk_jtjy,item.jtjy)
                            .setText(R.id.item_bcjc_ldlbdqk_cjtjy,item.cjtjy)
                            .setText(R.id.item_bcjc_ldlbdqk_cjtjjzz,item.cjtjjzz)
                            .setText(R.id.item_bcjc_ldlbdqk_wcwg,item.wclg)

                    /*val years = helper.getView<TextView>(R.id.item_bcjc_ldlbdqk_year)
                    if (item.bcrejectedEntities.size>0){
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
            if (msg.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = msg.get(0)
                tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,get.process-1,get.process-1)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_rkjy_ldlbdqk_add.visibility = View.VISIBLE
                    tv_frag_rkjy_ldlbdqk_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_rkjy_ldlbdqk_add.visibility = View.VISIBLE
                        tv_frag_rkjy_ldlbdqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_rkjy_ldlbdqk_add.visibility = View.VISIBLE
                            tv_frag_rkjy_ldlbdqk_add.setText("操作")
                        }else{
                            if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_rkjy_ldlbdqk_add.setText("已上报")
                            }else{
                                tv_frag_rkjy_ldlbdqk_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_rkjy_ldlbdqk_add.visibility = View.GONE
                            }
                        }
            }else
                if (AppCache.getInstance().type==4){
                    tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,-1,-1)
                tv_frag_rkjy_ldlbdqk_add.visibility = View.VISIBLE
            }else
                    if (AppCache.getInstance().type!=4){
                        tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,-1,-1)
                        if (AppCache.getInstance().type==1){
                            tv_frag_rkjy_ldlbdqk_add.visibility = View.GONE
                        }
                        tv_frag_rkjy_ldlbdqk_add.setText("未上报")
            }
            if (msg.size>0){

                var bhgsCount = ArrayList<Float>()
                var bhgsName = ArrayList<String>()
                val colors = java.util.ArrayList<Int>()//
                colors.add(Color.parseColor("#5B9BD5"))
                colors.add(Color.parseColor("#ED7D31"))
                colors.add(Color.parseColor("#A5A5A5"))
                bhgsCount.add(getStringToFloat(msg.get(0).nlss))
                bhgsCount.add(getStringToFloat(msg.get(0).nlls))
                bhgsCount.add(getStringToFloat(msg.get(0).ldlzs)-getStringToFloat(msg.get(0).nlss)-getStringToFloat(msg.get(0).nlls))
                bhgsName.add("≤30岁")
                bhgsName.add("≥60岁")
                bhgsName.add("30-60岁")
                var bhgsCount1 = ArrayList<Float>()
                var bhgsName1 = ArrayList<String>()
                val colors1 = java.util.ArrayList<Int>()//
                colors1.add(Color.parseColor("#5B9BD5"))
                colors1.add(Color.parseColor("#ED7D31"))
                colors1.add(Color.parseColor("#A5A5A5"))
                bhgsCount1.add(getStringToFloat(msg.get(0).cyyc))
                bhgsCount1.add(getStringToFloat(msg.get(0).cyec))
                bhgsCount1.add(getStringToFloat(msg.get(0).sysc))
                bhgsName1.add("一产")
                bhgsName1.add("二产")
                bhgsName1.add("三产")
                if (getStringToFloat(msg.get(0).ldlzs)<=0){
                    ll_frag_ldgl_ldlbdfl.visibility = View.GONE
                }else{
                    if (AppCache.getInstance().type!=4)
                    ll_frag_ldgl_ldlbdfl.visibility = View.VISIBLE
                }

                PieTdxgUtil.pieRlv(pie_frag_ldgl_ldlrknlqk, "", bhgsCount, bhgsName, colors,rlv_frag_zjd_ldgl_ldlrknlqk,activity!!)//人口年龄情况
                PieTdxgUtil.pieRlv(pie_frag_ldgl_ldlrkcyqk, "", bhgsCount1, bhgsName1, colors1,rlv_frag_zjd_ldgl_ldlrkcyqk,activity!!)//人口产业情况
//                PieTdxgUtil.rkinitPie1(pie_frag_ldgl_ldlrknlqk, "", bhgsCount, bhgsName, colors)//人口年龄情况
//                PieTdxgUtil.rkinitPie1(pie_frag_ldgl_ldlrkcyqk, "", bhgsCount1, bhgsName1, colors1)//人口产业情况
                var bhgsCount2 = ArrayList<Float>()
                var bhgsName2 = ArrayList<String>()
                val colors2 = java.util.ArrayList<Int>()//
                colors2.add(Color.parseColor("#5B9BD5"))
                colors2.add(Color.parseColor("#ED7D31"))
                colors2.add(Color.parseColor("#A5A5A5"))
                colors2.add(Color.parseColor("#FFC000"))
                bhgsCount2.add(getStringToFloat(msg.get(0).jtjy))
                bhgsCount2.add(getStringToFloat(msg.get(0).cjtjy))
                bhgsCount2.add(getStringToFloat(msg.get(0).cjtjjzz))
                bhgsCount2.add(getStringToFloat(msg.get(0).wclg))
                bhgsName2.add("家庭经营")
                bhgsName2.add("村集体经营")
                bhgsName2.add("村集体经济组织(非企业)")
                bhgsName2.add("外出务工(1年中≥6个月)")
                val s = getStringToFloat(msg.get(0).jtjy) + getStringToFloat(msg.get(0).cjtjy) +
                        getStringToFloat(msg.get(0).cjtjjzz) + getStringToFloat(msg.get(0).wclg)
                if (s<=0f){
                    ll_frag_ldgl_ldlbdfl1.visibility = View.GONE
                    mtl_frag_ldgl_ldlbdfl1.visibility = View.GONE
                }else{
                    if (AppCache.getInstance().type!=4)
                    mtl_frag_ldgl_ldlbdfl1.visibility = View.VISIBLE
//                    ll_frag_ldgl_ldlbdfl1.visibility = View.VISIBLE
                }
//                PieTdxgUtil.rkinitPie1(pie_frag_ldgl_ldlrkjyqk, "", bhgsCount2, bhgsName2, colors2)//人口就业情况
                PieTdxgUtil.pieRlv(pie_frag_ldgl_ldlrkjyqk, "", bhgsCount2, bhgsName2, colors2,rlv_frag_zjd_ldgl_ldlrkjyqk,activity!!)//人口就业情况
            }else{
                mtl_frag_ldgl_ldlbdfl1.visibility = View.GONE
                ll_frag_ldgl_ldlbdfl.visibility = View.GONE
                ll_frag_ldgl_ldlbdfl1.visibility = View.GONE
            }
            //ll_frag_ldgl_ldlbdfl1


        }else{
            ToastUtils.showShort("该年份暂无数据")
            ll_frag_ldgl_ldlbdfl.visibility = View.GONE
            ll_frag_ldgl_ldlbdfl1.visibility = View.GONE
        }
    }

    override fun returnBcjcAddLdlbdqk(msg: String) {
        ToastUtils.showShort(msg)
        if (addldlbdqkUpPopu!=null){
            addldlbdqkUpPopu!!.dismiss()
        }
        mPresenter.getBchcLdlbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcLdlbdqkAll(AppCache.getInstance().code,yearsListNull)
    }
    override fun returnBchcRkbdqk(msg: List<BcrkbdqkEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addRkbdqkUpPopu!=null){
            addRkbdqkUpPopu!!.dismiss()
        }
        var rkbdqkRejectList = ArrayList<BcrejectedEntity>()
        if (msg!=null){//&&msg.size>0

            rlv_frag_rkjy_rkbdqk.layoutManager = object:LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            rlv_frag_rkjy_rkbdqk.isFocusable = false
            rlv_frag_rkjy_rkbdqk.adapter = object :BaseQuickAdapter<BcrkbdqkEntity, BaseViewHolder>(R.layout.item_bcjc_rkbdqk,msg){
                override fun convert(helper: BaseViewHolder?, item: BcrkbdqkEntity?) {
                    helper!!.setText(R.id.item_bcjc_rkbdqk_year,item!!.years)
                            .setText(R.id.item_bcjc_rkbdqk_qchs,item.qchs)
                            .setText(R.id.item_bcjc_rkbdqk_qcrs,item.qcrk)
                            .setText(R.id.item_bcjc_rkbdqk_nyhjh,item.nyhjh)
                            .setText(R.id.item_bcjc_rkbdqk_nyhjr,item.nyhjr)
                            .setText(R.id.item_bcjc_rkbdqk_xydy30,item.nlss)
                            .setText(R.id.item_bcjc_rkbdqk_dydy60,item.nlls)
                            .setText(R.id.item_bcjc_rkbdqk_bcczrk,item.bcczrk)
                            .setText(R.id.item_bcjc_rkbdqk_wlczrk,item.wlczrk)

                    /*val years = helper.getView<TextView>(R.id.item_bcjc_rkbdqk_year)
                    if (item.bcrejectedEntities.size>0){
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
            var get: BcrkbdqkEntity? = null
            for (i in msg){
                if ((split+"年").equals(i.years)){
                    get = i
                }
            }
            if (get!=null){//tv_frag_tdqs_zhengdqk_add   msg.size>0

//                tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,get.process-1,get.process-1)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_rkjy_rkbdqk_add.visibility = View.VISIBLE
                    tv_frag_rkjy_rkbdqk_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_rkjy_rkbdqk_add.visibility = View.VISIBLE
                        tv_frag_rkjy_rkbdqk_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_rkjy_rkbdqk_add.visibility = View.VISIBLE
                            tv_frag_rkjy_rkbdqk_add.setText("操作")
                        }else{
//                            tv_frag_rkjy_rkbdqk_add.visibility = View.GONE
                            if (get.process>TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_rkjy_rkbdqk_add.setText("已上报")
                            }else{
                                tv_frag_rkjy_rkbdqk_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_rkjy_rkbdqk_add.visibility = View.GONE
                            }
                        }
            }else if (AppCache.getInstance().type==4){
                tv_frag_rkjy_rkbdqk_add.visibility = View.VISIBLE
                tv_frag_rkjy_rkbdqk_add.setText("操作")
//                tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,-1,-1)
            }else if (AppCache.getInstance().type!=4){
//                tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_rkjy_rkbdqk_add.visibility = View.GONE
                }
                tv_frag_rkjy_rkbdqk_add.setText("未上报")
            }
            if (msg.size>0){

                var bhgsCount = ArrayList<Float>()
                var bhgsName = ArrayList<String>()
                val colors = java.util.ArrayList<Int>()//
                colors.add(Color.parseColor("#5B9BD5"))
                colors.add(Color.parseColor("#ED7D31"))
                bhgsCount.add(getStringToFloat(msg.get(0).bcczrk))
                bhgsCount.add(getStringToFloat(msg.get(0).qcrk)-getStringToFloat(msg.get(0).bcczrk))
                bhgsName.add("农业户籍")
                bhgsName.add("非农业户籍")
                var bhgsCount1 = ArrayList<Float>()
                var bhgsName1 = ArrayList<String>()
                val colors1 = java.util.ArrayList<Int>()//
                colors1.add(Color.parseColor("#5B9BD5"))
                colors1.add(Color.parseColor("#ED7D31"))
                colors1.add(Color.parseColor("#A5A5A5"))
                bhgsCount1.add(getStringToFloat(msg.get(0).nlss))
                bhgsCount1.add(getStringToFloat(msg.get(0).nlls))
                bhgsCount1.add(getStringToFloat(msg.get(0).qcrk)-getStringToFloat(msg.get(0).nlss)-getStringToFloat(msg.get(0).nlls))
                bhgsName1.add("≤30岁")
                bhgsName1.add("≥60岁")
                bhgsName1.add("30-60岁")
                if (getStringToFloat(msg.get(0).qcrk)<=0){
                    ll_frag_ldgl_rkbdfl.visibility = View.GONE
                }else{
                    if (AppCache.getInstance().type!=4)
                    ll_frag_ldgl_rkbdfl.visibility = View.VISIBLE
                }

                PieTdxgUtil.pieRlv(pie_frag_ldgl_hjrkqkhj, "", bhgsCount, bhgsName, colors,rlv_frag_zjd_ldgl_hjrkqkhj,activity!!)//户籍划分人口
                PieTdxgUtil.pieRlv(pie_frag_ldgl_hjrkqknl, "", bhgsCount1, bhgsName1, colors1,rlv_frag_zjd_ldgl_hjrkqknl,activity!!)//户籍划分人口
//                PieTdxgUtil.rkinitPie1(pie_frag_ldgl_hjrkqkhj, "", bhgsCount, bhgsName, colors)//户籍划分人口
//                PieTdxgUtil.rkinitPie1(pie_frag_ldgl_hjrkqknl, "", bhgsCount1, bhgsName1, colors1)//年龄划分人口
            }else{
                ll_frag_ldgl_rkbdfl.visibility = View.GONE
            }

//            rkinitPie2(pie_frag_ldgl_hjrkqkhj, "", bhgsCount, bhgsName)//户籍划分人口
//            rkinitPie2(pie_frag_ldgl_hjrkqknl, "", bhgsCount1, bhgsName1)//年龄划分人口
        }else{
            ToastUtils.showShort("该年份暂无数据")
            ll_frag_ldgl_rkbdfl.visibility = View.GONE
        }

    }
    fun getStringToFloat(string:String):Float{
        if (string.equals("")){
            return 0f
        }else{
            return string.toFloat()
        }
    }

    //添加人口变动情况回调
    override fun returnBcjcAddRkbdqk(msg: String) {
        rkjyUploadType = 1
        ToastUtils.showShort(msg)
        if (addRkbdqkUpPopu!=null){
            addRkbdqkUpPopu!!.dismiss()
        }
        mPresenter.getBchcRkbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcRkbdqkAll(AppCache.getInstance().code,yearsListNull)
    }

    //添加村干部情况回调
    override fun returnBcjcAddCgbqk(msg: String) {
        ToastUtils.showShort(msg)
        if (addcgbjbqkUpPopu!=null){
            addcgbjbqkUpPopu!!.dismiss()
        }
        if (yearsList.size>0){
            mPresenter.getBcjcCgbqk(AppCache.getInstance().code,yearsList.get(0))
        }
    }
    //获取村干部情况回调
    override fun returnBcjcCgbqk(msg: List<BccgbqkEntity>) {
        bccgbqkEntity = BccgbqkEntity()
        if (msg!=null&&msg.size>0){
            val entity = msg.get(0)
            bccgbqkEntity = entity
            when(entity.process){
                1->{
                    tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#1B96EE"))
                    tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                }
                2->{
                    tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                    tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                }
                3->{
                    tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                    tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                }
                4->{
                    tv_frag_zjd_ldrk_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                    tv_frag_zjd_ldrk_gzjdtjcg.setTextColor(Color.parseColor("#1B96EE"))
                    tv_frag_zjd_ldrk_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                }
            }


            if (entity.process==4){
//                tv_frag_rkjy_cgbjbqkqk_add.setText("已提交")
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1){
//                    tv_frag_rkjy_cgbjbqkqk_add.visibility = View.VISIBLE
                }
//                tv_frag_rkjy_cgbjbqkqk_add.visibility = View.GONE
            }else{
//                tv_frag_rkjy_cgbjbqkqk_add.setText("已提交")
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1){
//                    tv_frag_rkjy_cgbjbqkqk_add.visibility = View.VISIBLE
                }
            }
            if (AppCache.getInstance().type==4||(AppCache.getInstance().type!=4&&entity.process>1)){
                tv_frag_rkjy_csj_name.text = entity.csjmc
                tv_frag_rkjy_czr_name.text = entity.czrmc
                tv_frag_rkjy_csz_name.text = entity.cszmc
                tv_frag_rkjy_csj_phone.text = entity.csjsjh
                tv_frag_rkjy_czr_phone.text = entity.czrsjh
                tv_frag_rkjy_csz_phone.text = entity.cszsjh
                tv_frag_rkjy_csj_whcd.text = entity.csjwhcd.toString()
                tv_frag_rkjy_czr_whcd.text = entity.czrwhcd.toString()
                tv_frag_rkjy_csz_whcd.text = entity.cszwhcd.toString()
                tv_frag_rkjy_csj_age.text = entity.csjnl.toString()
                tv_frag_rkjy_czr_age.text = entity.czrnl.toString()
                tv_frag_rkjy_csz_age.text = entity.csznl.toString()
                tv_frag_rkjy_csj_time.text = entity.csjrz
                tv_frag_rkjy_czr_time.text = entity.czrrz
                tv_frag_rkjy_csz_time.text = entity.cszrz
                et_frag_rkjy_dyrs.setText(entity.dyrs.toString())
                et_frag_rkjy_dyrs50.setText(entity.dyrs50.toString())
                et_frag_rkjy_isdysj.setText(entity.sfydysj)
                et_frag_rkjy_dwmc.setText(entity.dwmc)
            }

            if (AppCache.getInstance().type==4&&bccgbqkEntity.process<2){
                tv_frag_rkjy_cgbjbqkqk_add.visibility = View.VISIBLE
            }else{
                tv_frag_rkjy_cgbjbqkqk_add.visibility = View.GONE
            }


            //添加村干部
            tv_frag_rkjy_cgbjbqkqk_add.setOnClickListener {
//                if (entity.process!=4){
                    cgbPopup(2,entity)
//                }
            }

        }else{
            tv_frag_rkjy_csj_name.text = ""
            tv_frag_rkjy_czr_name.text = ""
            tv_frag_rkjy_csz_name.text = ""
            tv_frag_rkjy_csj_phone.text = ""
            tv_frag_rkjy_czr_phone.text = ""
            tv_frag_rkjy_csz_phone.text = ""
            tv_frag_rkjy_csj_whcd.text = ""
            tv_frag_rkjy_czr_whcd.text = ""
            tv_frag_rkjy_csz_whcd.text = ""
            tv_frag_rkjy_csj_age.text = ""
            tv_frag_rkjy_czr_age.text = ""
            tv_frag_rkjy_csz_age.text = ""
            tv_frag_rkjy_csj_time.text = ""
            tv_frag_rkjy_czr_time.text = ""
            tv_frag_rkjy_csz_time.text = ""
            et_frag_rkjy_dyrs.setText("")
            et_frag_rkjy_dyrs50.setText("")
            et_frag_rkjy_isdysj.setText("")
            et_frag_rkjy_dwmc.setText("")
            if (AppCache.getInstance().type!=4){
                tv_frag_rkjy_cgbjbqkqk_add.visibility = View.GONE
            }else{
                //添加村干部
                tv_frag_rkjy_cgbjbqkqk_add.setOnClickListener {

                    val content = TextView(activity)
                    content.text = ""//干部一肩挑
//                FileUtils.openFile()//AppConstant.getFileProvider()
                    SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("是否村书记、村主任、村股份合作社社长“一肩挑”？")
                            .setCustomView(content)
                            .setConfirmButton("是") { sweetAlertDialog ->
                                cgbPopup(1,BccgbqkEntity())
                                sweetAlertDialog.dismiss()}
                            .setCancelButton("否") { sweetAlertDialog ->
                                cgbPopup(2,BccgbqkEntity())
                                sweetAlertDialog.dismiss() }
                            .show()
                }
            }
        }

    }



    var bcrkbdqkEntitys = ArrayList<BcrkbdqkEntity>()
    override fun returnBchcRkbdqkAll(msg: List<BcrkbdqkEntity>) {
        bcrkbdqkEntitys.clear()
        bcrkbdqkEntitys.addAll(msg)
        if (msg.size == 0){
            bt_act_rkjy_xyb.visibility = View.GONE
            bt_act_rkjy_bh.visibility = View.GONE
            return
        }
        for (i in msg){
            if (i.years.contains(split)){

                //人口就业
                rkjyEntity.qchs = i.qchs
                rkjyEntity.qcrk = i.qcrk
                rkjyEntity.nyhjh = i.nyhjh
                rkjyEntity.nyhjr = i.nyhjr
                rkjyEntity.nlss = i.nlss
                rkjyEntity.nlls = i.nlls
                rkjyEntity.bcczrk = i.bcczrk
                rkjyEntity.wlczrk = i.wlczrk
                rkjyEntity.code = AppCache.getInstance().code
                rkjyEntity.xzqmc = i.xzqmc
                rkjyEntity.years = i.years
                bcrkbdqkEntitysId = i.id
                bcrkbdqkProcess = i.process
            }
            if ("1978年".equals(i.years)){//msg.get(0)
                rkjyEntity1978.qchs = i.qchs
                rkjyEntity1978.qcrk = i.qcrk
                rkjyEntity1978.nyhjh = i.nyhjh
                rkjyEntity1978.nyhjr = i.nyhjr
                rkjyEntity1978.nlss = i.nlss
                rkjyEntity1978.nlls = i.nlls
                rkjyEntity1978.bcczrk = i.bcczrk
                rkjyEntity1978.wlczrk = i.wlczrk
                rkjyEntity1978.code = AppCache.getInstance().code
                rkjyEntity1978.xzqmc = i.xzqmc
                rkjyEntity1978.years = i.years
                if (bcrkbdqkProcess1978 != 4){
                    bcrkbdqkEntitysId1978 = i.id
                    bcrkbdqkProcess1978 = i.process
                }
            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_act_rkjy_xyb.text = "提交上报"
            if (bcrkbdqkProcess == 1&&bcldlbdqkProcess == 1&&bcwcjybdqkProcess == 1){
                bt_act_rkjy_xyb.visibility = View.VISIBLE
            }else{
                bt_act_rkjy_xyb.visibility = View.GONE
            }
        }else{
            bt_act_rkjy_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bcrkbdqkProcess == 2&&bcldlbdqkProcess == 2&&bcwcjybdqkProcess == 2){
                    bt_act_rkjy_xyb.visibility = View.VISIBLE
                    bt_act_rkjy_bh.visibility = View.VISIBLE
                }else{
                    bt_act_rkjy_xyb.visibility = View.GONE
                    bt_act_rkjy_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bcrkbdqkProcess == 3&&bcldlbdqkProcess == 3&&bcwcjybdqkProcess == 3){
                    bt_act_rkjy_xyb.visibility = View.VISIBLE
                    bt_act_rkjy_bh.visibility = View.VISIBLE
                }else{
                    bt_act_rkjy_xyb.visibility = View.GONE
                    bt_act_rkjy_bh.visibility = View.GONE
                }
            }else{
                bt_act_rkjy_xyb.visibility = View.GONE
                bt_act_rkjy_bh.visibility = View.GONE
            }
        }

    }

    var bcldlbdqkEntitys = ArrayList<BcldlbdqkEntity>()
    override fun returnBchcLdlbdqkAll(msg: List<BcldlbdqkEntity>) {
        bcldlbdqkEntitys.clear()
        bcldlbdqkEntitys.addAll(msg)
        if (msg.size == 0){
            bt_act_rkjy_xyb.visibility = View.GONE
            bt_act_rkjy_bh.visibility = View.GONE
            return
        }
        //劳动力变动
        for (i in msg){
            if (i.years.contains(split)){

                ldlbdEntity.ldlzs = i.ldlzs
                ldlbdEntity.nlss = i.nlss
                ldlbdEntity.nlls = i.nlls
                ldlbdEntity.cyyc = i.cyyc
                ldlbdEntity.cyec = i.cyec
                ldlbdEntity.sysc = i.sysc
                ldlbdEntity.jtjy = i.jtjy
                ldlbdEntity.cjtjy = i.cjtjy
                ldlbdEntity.cjtjjzz = i.cjtjjzz
                ldlbdEntity.wclg = i.wclg
                ldlbdEntity.years = i.years
                ldlbdEntity.code = AppCache.getInstance().code
                ldlbdEntity.xzqmc = i.xzqmc
                bcldlbdqkEntitysId = i.id
                bcldlbdqkProcess = i.process
            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_act_rkjy_xyb.text = "提交上报"
            if (bcldlbdqkProcess == 1&&bcrkbdqkProcess==1&&bcwcjybdqkProcess==1){
                bt_act_rkjy_xyb.visibility = View.VISIBLE
            }else{
                bt_act_rkjy_xyb.visibility = View.GONE
            }
        }else{
            bt_act_rkjy_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bcldlbdqkProcess == 2&&bcrkbdqkProcess==2&&bcwcjybdqkProcess==2){
                    bt_act_rkjy_xyb.visibility = View.VISIBLE
                    bt_act_rkjy_bh.visibility = View.VISIBLE
                }else{
                    bt_act_rkjy_xyb.visibility = View.GONE
                    bt_act_rkjy_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bcldlbdqkProcess == 3&&bcrkbdqkProcess==3&&bcwcjybdqkProcess==3){
                    bt_act_rkjy_xyb.visibility = View.VISIBLE
                    bt_act_rkjy_bh.visibility = View.VISIBLE
                }else{
                    bt_act_rkjy_xyb.visibility = View.GONE
                    bt_act_rkjy_bh.visibility = View.GONE
                }
            }else{
                bt_act_rkjy_xyb.visibility = View.GONE
                bt_act_rkjy_bh.visibility = View.GONE
            }
        }
    }
    var bcwcjybdqkEntitys = ArrayList<BcwcjybdqkEntity>()
    override fun returnBchcWcwgbdqkAll(msg: List<BcwcjybdqkEntity>) {
        bcwcjybdqkEntitys.clear()
        bcwcjybdqkEntitys.addAll(msg)
        if (msg.size == 0){
            bt_act_rkjy_xyb.visibility = View.GONE
            bt_act_rkjy_bh.visibility = View.GONE
            return
        }
        //外出务工
        for (i in msg){
            if (i.years.contains(split)){

                wcwgEntity.cqwgzs = i.cqwgzs
                wcwgEntity.bdjy = i.bdjy
                wcwgEntity.xwqn = i.xwqn
                wcwgEntity.qwsn = i.qwsn
                wcwgEntity.wss = i.wss
                wcwgEntity.years = i.years
                wcwgEntity.code = AppCache.getInstance().code
                wcwgEntity.xzqmc = i.xzqmc
                bcwcjybdqkEntitysId = i.id
                bcwcjybdqkProcess = i.process
            }
        }

        if (AppCache.getInstance().type == 4&&AppCache.getInstance().duties!=1){
            bt_act_rkjy_xyb.text = "提交上报"
            if (bcwcjybdqkProcess == 1&&bcrkbdqkProcess==1&&bcldlbdqkProcess==1){
                bt_act_rkjy_xyb.visibility = View.VISIBLE
            }else{
                bt_act_rkjy_xyb.visibility = View.GONE
            }
        }else{
            bt_act_rkjy_xyb.text = "通过"
            if (AppCache.getInstance().type == 3&&AppCache.getInstance().duties!=1){
                if (bcwcjybdqkProcess == 2&&bcrkbdqkProcess==2&&bcldlbdqkProcess==2){
                    bt_act_rkjy_xyb.visibility = View.VISIBLE
                    bt_act_rkjy_bh.visibility = View.VISIBLE
                }else{
                    bt_act_rkjy_xyb.visibility = View.GONE
                    bt_act_rkjy_bh.visibility = View.GONE
                }
            }else if (AppCache.getInstance().type == 2&&AppCache.getInstance().duties!=1){
                if (bcwcjybdqkProcess == 3&&bcrkbdqkProcess==3&&bcldlbdqkProcess==3){
                    bt_act_rkjy_xyb.visibility = View.VISIBLE
                    bt_act_rkjy_bh.visibility = View.VISIBLE
                }else{
                    bt_act_rkjy_xyb.visibility = View.GONE
                    bt_act_rkjy_bh.visibility = View.GONE
                }
            }else{
                bt_act_rkjy_xyb.visibility = View.GONE
                bt_act_rkjy_bh.visibility = View.GONE
            }
        }
    }


    //人口变动情况修改、下一步、驳回、通过回调
    override fun returnBcjcUpdateRkbdqk(msg: String) {
        rkjyUploadType = 1
        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){ //addRkbdqkUpPopu
            mUploadPopu!!.dismiss()
        }
        mPresenter.getBchcRkbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcRkbdqkAll(AppCache.getInstance().code,yearsListNull)
    }

    //劳动力变动情况修改、下一步、驳回、通过回调
    override fun returnBcjcUpdateLdlbdqk(msg: String) {
        ldlbdUploadType = 1
        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){ //addldlbdqkUpPopu
            mUploadPopu!!.dismiss()
        }
        mPresenter.getBchcLdlbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcLdlbdqkAll(AppCache.getInstance().code,yearsListNull)
    }

    //外出务工人员变动情况修改、下一步、驳回、通过回调
    override fun returnBcjcUpdateWcwgbdqk(msg: String) {
        wcwgUploadType = 1
        ToastUtils.showShort(msg)
        if (mUploadPopu!=null){ //addwcwgbdqkUpPopu
            mUploadPopu!!.dismiss()
        }
        mPresenter.getBchcWcwgbdqk(AppCache.getInstance().code,yearsList)
        mPresenter.getBchcWcwgbdqkAll(AppCache.getInstance().code,yearsListNull)
        mPresenter.getBcjcGetInwcry(AppCache.getInstance().code,yearsList)
    }


    override fun returnUser(user: User) {
        
    }

    override fun changeUser() {
        
    }

    override fun changeUser(msg: List<IndustryEntity>) {
        
    }

    override fun returnJbqktj(msg: FirstTjBean) {
        if (rlv_frag_ldgl_hyfbqk!=null){
            if (msg.xzqInfo != null) {
                /*tv_frag_ldgl_zjdcz.setText("" + msg.xzqInfo.zhaicount)
                tv_frag_ldgl_fjcz.setText("" + msg.xzqInfo.roomcount)
                tv_frag_ldgl_ldrk.setText("" + msg.xzqInfo.flowcount)*/
            }
            var legendList = ArrayList<LegendBean>()
            /*for (i in 0..IndustryEnum.values().size-1){
                legendList.add(LegendBean(IndustryEnum.values().get(i).getName(),colors.get(i)))
            }*/
            var colorList = ArrayList<Int>()//颜色
            val bhgsCount = ArrayList<Float>()//数量
            val bhgsName = ArrayList<String>()//类型
            var qtCount = 0f
            for (i in 0..msg.industry.size - 1) {
                if (i < 4 && !msg.industry.get(i).industryText.equals("其他")) {
                    bhgsCount.add(msg.industry.get(i).count.toFloat())
                    bhgsName.add(msg.industry.get(i).industryText)
                    colorList.add(colors.get(i))
                    legendList.add(LegendBean(msg.industry.get(i).industryText, msg.industry.get(i).count, colors.get(i)))
                } else {
                    qtCount = qtCount + msg.industry.get(i).count
                }
            }
            if (msg.industry.size > 4) {
                bhgsCount.add(qtCount)
                bhgsName.add("其他")
                colorList.add(colors.get(4))
                legendList.add(LegendBean("其他", qtCount.toInt(), colors.get(4)))
            }
            setPieHyfbqk(bhgsCount, bhgsName, colorList)//行业分布饼状图

            rlv_frag_ldgl_hyfbqk.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)//图例显示
            val hylxTlAdapter = HylxTlAdapter(activity, legendList)
            rlv_frag_ldgl_hyfbqk.adapter = hylxTlAdapter//LegendBean
            if (legendList.size==0){
                ll_frag_ldgl_ldrkhylx.visibility = View.GONE
            }else{
                ll_frag_ldgl_ldrkhylx.visibility = View.VISIBLE
            }
        }
    }
    //设置行业分布情况饼状图
    private fun setPieHyfbqk(bhgsCount: ArrayList<Float>, bhgsName: ArrayList<String>, colors: List<Int>) {
        if (pie_frag_ldgl_hyfbqk != null) {
            rkinitPie(pie_frag_ldgl_hyfbqk, "", bhgsCount, bhgsName, colors)//总数
        }
    }
    //问题统计饼状图
    private fun rkinitPie(pie: PieChart, title: String, datas: java.util.ArrayList<Float>, lables: java.util.ArrayList<String>, colors: List<Int>) {
        pie.setUsePercentValues(false)
        pie.description.isEnabled = false
        pie.setExtraOffsets(5f, 10f, 5f, 5f)

        pie.dragDecelerationFrictionCoef = 0.95f

        pie.setExtraOffsets(10f, 0f, 10f, 0f)

        pie.isDrawHoleEnabled = true
        pie.setHoleColor(Color.WHITE)

        pie.setTransparentCircleColor(Color.WHITE)
        pie.setTransparentCircleAlpha(110)

        pie.holeRadius = 58f
        pie.transparentCircleRadius = 61f

        pie.setDrawCenterText(true)

        pie.centerText = title
        pie.setCenterTextSize(20f)
        pie.rotationAngle = 0f
        // enable rotation of the chart by touch
        pie.isRotationEnabled = true
        pie.isHighlightPerTapEnabled = true
        pie.setEntryLabelColor(Color.BLACK)
        setRKData(pie, datas, lables, colors)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }
    private fun setRKData(pie: PieChart, datas: List<Float>, lables: List<String>, colors: List<Int>) {
        val entries = java.util.ArrayList<PieEntry>()

        var zs = 0f
        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
            zs = zs + datas[i]
        }
        val dataSet = PieDataSet(entries, "")//Election Results
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val format = DecimalFormat("###.#")
                val format1 = DecimalFormat("###")
                return "${format.format(value / zs * 100)}%"//super.getFormattedValue(value)  format1.format(value)+
            }
        }
        // add a lot of colors


        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = com.github.mikephil.charting.data.PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data
        // undo all highlights
        pie.highlightValues(null)
        pie.invalidate()
    }


    override fun returnDcylxx(msg: YlFolwEntity) {
        
    }

    override fun returnCxczqk(msg: List<XzqInfoEntity>) {
        if (rlv_frag_ldgl_czqk!=null){
            tv_frag_ldgl_czqk1.setText("共有${msg.size}个村")
            //显示少数村庄列表
            var czqkLsit = ArrayList<XzqInfoEntity>()
            for (i in msg.indices) {
                if (i < 5) {
                    czqkLsit.add(msg.get(i))
                }
            }
            if (msg.size<5){
                tv_frag_ldgl_ckgd.visibility = View.GONE
            }
            if (msg.isNotEmpty()){
                include_frag_ldgl_zwsj.visibility = View.GONE
            }
            rlv_frag_ldgl_czqk.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            val zlglAdapter = CzqkAdapter(activity, czqkLsit)
            rlv_frag_ldgl_czqk.adapter = zlglAdapter
            zlglAdapter.setOnItemClick(object : CzqkAdapter.OnItemClick {
                override fun onClick(pjtaskFile: XzqInfoEntity?) {
                    if (SingleOnClickUtil1.isFastClick()){
                        val intent = Intent(activity, YllbActivity::class.java)
                        intent.putExtra("code", pjtaskFile?.code)
                        intent.putExtra("xzqmc", pjtaskFile?.xzqmc)
                        startActivity(intent)
                    }
                }
            })
        }
    }

    override fun returnCxldry(msg: List<YlFolwEntity>, total: Long) {
        if (rlv_frag_ldgl_czqk!=null){
            tv_frag_ldgl_czqk1.setText("共有${total}户")
            //显示少数人口列表
            var czqkLsit = ArrayList<YlFolwEntity>()
            for (i in msg.indices) {
                if (i < 5) {
                    czqkLsit.add(msg.get(i))
                }
            }
            if (msg.size<5){
                tv_frag_ldgl_ckgd.visibility = View.GONE
            }
            if (msg.isNotEmpty()){
                include_frag_ldgl_zwsj.visibility = View.GONE
            }
            rlv_frag_ldgl_czqk.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            val zlglAdapter = YllbAdapter(activity, czqkLsit)
            rlv_frag_ldgl_czqk.adapter =zlglAdapter
            zlglAdapter.setOnItemClick(object: YllbAdapter.OnYLLBItemClick{

                override fun onClick(pjtaskFile: YlFolwEntity?) {//点查
                    if (SingleOnClickUtil.isFastClick()){
                        val intent = Intent(activity, LdrkDetailActivity::class.java)
                        intent.putExtra("ylId", pjtaskFile?.objectid)
                        startActivity(intent)
//                    mPresenter.getDcylxx("", pjtaskFile!!.objectid)
                    }
                }
            })
        }
    }

    override fun returnSmewmcyl(msg: YlFolwEntity) {
        
    }

    override fun returnRlt(msg: List<XzqInfoEntity>, type: Int) {
        
    }

    override fun returnWclCount(count: Int) {
        
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

    //驳回弹出框
    fun rejectUpPopup(list :ArrayList<BcrejectedEntity>){
        rejectUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject_list, sv_frag_ldgl_up)
        val contentView: View = rejectUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_reject_list_rlv)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_reject_list_close)
        CommenPop.backgroundAlpha(0.5f, activity)
        rejectUpPopu!!.showAtLocation(sv_frag_ldgl_up, Gravity.CENTER, 0, 0)
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

    fun diaoyong(){
        if (supl_frag_zjdgl1!=null&&sv_frag_ldgl_up!=null){
            supl_frag_zjdgl1?.setScrollableView(sv_frag_ldgl_up)

//设置默认滚动到顶部
            sv_frag_ldgl_up.post(object: Runnable {
                override fun run() {
                    // TODO Auto-generated method stub
                    sv_frag_ldgl_up.fullScroll(ScrollView.FOCUS_UP)

                }
            })
            mPresenter.getBcjcYears()//百村监测获取年
            sptypeList.clear()
            sptypeList.add("上报")
            sptypeList.add("乡镇审核")
            sptypeList.add("区级确认")
            sptypeList.add("提交成功")
            tll_frag_zjd_ldrk_rkbd.setValueText(sptypeList,-1,-1)
            /*
            tll_frag_zjd_rkjy_ldlbdqk.setValueText(sptypeList,-1,-1)
            tll_frag_zjd_rkjy_wcwg.setValueText(sptypeList,-1,-1)*/
        }
        /*mPresenter.getJbqktj()//统计//0728注
        if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF){
            mPresenter.getCxczqk("","")//0728注
        }else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY){
            mPresenter.getCxldry(AppCache.getInstance().code,"","")//0728注
        }*/

    }
    override fun onResume() {
        super.onResume()
        if (userVisibleHint&&AppCache.getInstance().isDtymlb){
            AppCache.getInstance().isCzxq = false
            AppCache.getInstance().isFwlb = false
            AppCache.getInstance().isCzlb = false
            AppCache.getInstance().isDtymlb = false
//            mPresenter.getJbqktj()//统计//0728注
            /*if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF){
//                mPresenter.getCxczqk("","")//0728注
            }else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY){
//                mPresenter.getCxldry(AppCache.getInstance().code,"","")//0728注
            }*/
            /*mPresenter.getJbqktj()//统计
            if (AppCache.getInstance().type == ApiConstants.LDRK_LGB||AppCache.getInstance().type == ApiConstants.LDRK_ZZF){
                mPresenter.getCxczqk("","")
            }else if (AppCache.getInstance().type == ApiConstants.LDRK_LGY){
                mPresenter.getCxldry(AppCache.getInstance().code,"","")
            }*/
            /*if (AppMenus.getInstance().menuBeans.toString().contains("消息查看")){
                mPresenter.getWclCount()//获取未处理数量
            }*/
        }
    }

}