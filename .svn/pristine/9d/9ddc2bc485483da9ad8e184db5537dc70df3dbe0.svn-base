package com.jymj.zhglxt.zjd.fragment.shzl

import android.graphics.Color
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
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter

import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.fragment.HomePageFragment
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.TdlyUtils
import com.jymj.zhglxt.util.TimePickerUtil
import com.jymj.zhglxt.util.TimeUtil
import com.jymj.zhglxt.widget.EnableLinearLayout
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.bean.cygl.NydVo
import com.jymj.zhglxt.zjd.bean.cygl.YztNyyqEntity
import com.jymj.zhglxt.zjd.bean.shzl.WwjEntity
import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity
import com.jymj.zhglxt.zjd.bean.zjdyd.ApplyLandEntity
import com.jymj.zhglxt.zjd.contract.NyyqContract
import com.jymj.zhglxt.zjd.contract.WwjContract
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment.Companion.supl_frag_zjdgl1
import com.jymj.zhglxt.zjd.presenter.NyyqPresenter
import com.jymj.zhglxt.zjd.presenter.WwjPresenter
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.frag_zjd_ldrk.*
import kotlinx.android.synthetic.main.frag_zjdgl.*
import kotlinx.android.synthetic.main.fragment_shzl_wwj.*
import kotlinx.android.synthetic.main.fragment_zjd_yqgl.*
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * (原无违建)现 集体资产
 */
class ShzlWfxcFragment : BaseFragment<WwjPresenter, BaseModel>(), WwjContract.View{

    private var yearUpPopu: CommenPop? = null
    private var addJtzcUpPopu: CommenPop? = null
    private var rejectUpPopu: CommenPop? = null
    var sptypeList = ArrayList<String>()

    companion object {
        /**
         * 单例，返回给定节编号的此片段的新实例。
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ShzlWfxcFragment {
            return ShzlWfxcFragment().apply {
//                ToastUtils.showShort("${sectionNumber}")
            }
        }
    }
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_shzl_wwj
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }
    override fun initViews() {
        if (AppCache.getInstance().type==1){
            tv_frag_zcjy_xzsj.visibility = View.GONE
        }
        var colorList = ArrayList<Int>()//颜色
        val bhgsCount = ArrayList<Float>()//数量
        val bhgsName = ArrayList<String>()//类型

        colorList.add(Color.parseColor("#5E7DE7"))
        colorList.add(Color.parseColor("#999999"))
        colorList.add(Color.parseColor("#F7C739"))
        colorList.add(Color.parseColor("#57C6B8"))
        colorList.add(Color.parseColor("#ED8E78"))
        colorList.add(Color.parseColor("#2597D4"))
        colorList.add(Color.parseColor("#7585A2"))
        colorList.add(Color.parseColor("#8064A1"))

        bhgsName.add("未分类")
        bhgsName.add("待完善类")
        bhgsName.add("不纳入类")
        bhgsName.add("有证类")
        bhgsName.add("限期整治类")
        bhgsName.add("宅基地")
        bhgsName.add("数据纠正")
        bhgsName.add("持续治理类")

        bhgsCount.add(0f)
        bhgsCount.add(0f)
        bhgsCount.add(917f)
        bhgsCount.add(0f)
        bhgsCount.add(13378f)
        bhgsCount.add(36721f)
        bhgsCount.add(2086f)
        bhgsCount.add(5388f)

        setPieHyfbqk(bhgsCount,bhgsName,colorList)

        /*tv_frag_jtzc_xzsj.setOnClickListener {
            if (yearUpPopu!=null) {
                CommenPop.backgroundAlpha(0.5f, activity)
                yearUpPopu!!.showAtLocation(slv_frag_shzl_wwj, Gravity.CENTER, 0, 0)
            }
            */
        /*TimePickerUtil.initTimePickerViewNyr(activity,tv_frag_jtzc_xzsj.value.toString(),object: TimePickerUtil.OnTimePickerLister{
                override fun onClick(data: String?) {
                    //时间筛选
                    tv_frag_jtzc_xzsj.setText(data)
                }
            })*//*
        }*/
    }
    var split = ""
    override fun initDatas() {

        val dateAfter = TimeUtil.getDateAfter(0)
//        split = dateAfter.split("-")[0]

        if (AppCache.getInstance().type == 4){
            ll_frag_shzl_wwj_gzjdd.visibility = View.VISIBLE
        }else{
            ll_frag_shzl_wwj_gzjdd.visibility = View.GONE
        }

    }
    private val yearsList = ArrayList<String>()
    private val yearsListNull = ArrayList<String>()
    private fun yearPopup(yearList:List<String>){
        yearUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_years, slv_frag_shzl_wwj)
        val contentView: View = yearUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_years_rlv)
        val confirm = contentView.findViewById<TextView>(R.id.pop_bcjc_years_confirm)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_years_close)


        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_bcjc_years,yearList){
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
            mPresenter.getBcjcJtzc(AppCache.getInstance().code,yearsList)
        }
        close.setOnClickListener {
            yearUpPopu!!.dismiss()
        }
    }

    var bcjtzcEntitysId = 0L
    var bcjtzcEntitysProcess = 0
    override fun returnBcjcYears(msg: List<String>) {
        yearsList.clear()
        if (AppCache.getInstance().duties!=1&&AppCache.getInstance().type!=1){
            yearsList.add(msg.get(0))
            split = yearsList.get(0).substring(0,yearsList.get(0).length-1)
        }
        mPresenter.getBcjcJtzc(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcJtzcAll(AppCache.getInstance().code,yearsListNull)
        yearPopup(msg)
        tv_frag_zcjy_xzsj.addText = msg.get(0)
        /*if (AppCache.getInstance().type == 4){
            tv_frag_jtzc_add.text = "添加/修改"
        }else if(AppCache.getInstance().type == 1){
            tv_frag_jtzc_add.visibility = View.GONE
        }else{
            tv_frag_jtzc_add.text = "操作"
        }*/
        tv_frag_zcjy_xzsj.setOnTitleListLister(object:MeTitleLayout.OnTitleListLister{
            override fun onClick(name: String?) {
                if (name.equals("添加")){

                    val arrayList = ArrayList<String>()
                    arrayList.addAll(msg)

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_frag_zcjy_xzsj.addText = arrayList.get(options1)
//                tv_frag_tdly_xzsj.setText(arrayList.get(options1))
                        split = arrayList.get(options1).substring(0,arrayList.get(options1).length-1)
//                        year = arrayList.get(options1)
                        yearsList.clear()
                        yearsList.add(split+"年")
                        mPresenter.getBcjcJtzc(AppCache.getInstance().code,yearsList)
                        mPresenter.getBcjcJtzcAll(AppCache.getInstance().code,yearsListNull)

//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(arrayList.indexOf(tv_frag_zcjy_xzsj.addText))
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

        tv_frag_jtzc_add.setOnClickListener {
            if (tv_frag_jtzc_add.text.toString().equals("操作")){

            addJtzcUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_add_jtzc, slv_frag_shzl_wwj)
            val contentView: View = addJtzcUpPopu!!.getContentView()
//            val spinner = contentView.findViewById<Spinner>(R.id.pop_add_jtzc_spr)
            val tv_pop_add_jtzc = contentView.findViewById<TextView>(R.id.tv_pop_add_jtzc)
            val et_jjtjjzzmc = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jtjjzzmc)
            val et_jclsj = contentView.findViewById<TextView>(R.id.pop_add_jtzc_et_clsj)//EditText
            val rgp_iszc = contentView.findViewById<RadioGroup>(R.id.pop_add_jtzc_rgp_iszc)
            val rbt_iszc_a = contentView.findViewById<RadioButton>(R.id.pop_add_jtzc_rbt_iszc_a)
            val rbt_iszc_b = contentView.findViewById<RadioButton>(R.id.pop_add_jtzc_rbt_iszc_b)
            val rbt_iszc_c = contentView.findViewById<RadioButton>(R.id.pop_add_jtzc_rbt_iszc_c)
            val et_ggqynzcze = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_ggqynzcze)
            val et_jzcze = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jzcze)
            val et_gdzs = contentView.findViewById<TextView>(R.id.pop_add_jtzc_et_gdzs)
            val et_jtgdzs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jtgdzs)
            val et_cygrgdzs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_cygrgdzs)
            val et_60gdrs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_60gdrs)
            val et_zjtgzgdrs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_zjtgzgdrs)
            val et_fcygrgdrs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_fcygrgdrs)
            val et_gbze = contentView.findViewById<TextView>(R.id.pop_add_jtzc_et_gbze)
            val et_jtgdgb = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jtgdgb)
            val et_cygrgdgb = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_cygrgdgb)
            val et_fcygrgdgb = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_fcygrgdgb)
            val et_qtgdgb = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_qtgdgb)
            val et_jygljx = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jygljx)
            val et_jxzzc = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxzzc)
            val et_jxjyxzc = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxjyxzc)
            val et_jyxwymj = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jyxwymj)
            val et_czwymj = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_czwymj)
            val et_jxjzc = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxjzc)
            val et_jxjyxsr = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxjyxsr)
            val et_jxzyywsr = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxzyywsr)
            val et_jxqtywsr = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxqtywsr)
            val et_jxtzsy = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxtzsy)
            val et_jxlrze = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxlrze)
            val et_jxsdsfy = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxsdsfy)
            val et_jxjlr = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxjlr)
            val et_jxgffhze = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxgffhze)
            val et_jxcygrgdfhje = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxcygrgdfhje)
            val et_jxfcygrgdfhje = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxfcygrgdfhje)
            val et_jxflfpze = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxflfpze)
            val et_jxdnkdzsczc = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_jxdnkdzsczc)

            val et_bccl = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_bccl)
            val et_yjsf = contentView.findViewById<TextView>(R.id.pop_add_jtzc_et_yjsf)
            val et_yjzzs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_yjzzs)
            val et_yjtdzzs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_yjtdzzs)
            val et_yjfcs = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_yjfcs)
            val et_yjsds = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_yjsds)
            val et_qtsf = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_qtsf)
            val et_kdzsctr = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_kdzsctr)
            val et_gysyzc = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_gysyzc)
            val et_czrname = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_czrname)
            val ll_reject = contentView.findViewById<LinearLayout>(R.id.pop_add_jtzc_ll_reject)
            val et_reject = contentView.findViewById<EditText>(R.id.pop_add_jtzc_et_reject)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_jtzc_confirm)
            val reject = contentView.findViewById<TextView>(R.id.pop_add_jtzc_reject)
            val close = contentView.findViewById<TextView>(R.id.pop_add_jtzc_close)
            val enableLl = contentView.findViewById<EnableLinearLayout>(R.id.pop_add_jtzc_ell)
                et_ggqynzcze.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jzcze.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jtgdgb.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_cygrgdgb.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_fcygrgdgb.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_qtgdgb.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jygljx.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxzzc.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxjyxzc.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxjzc.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxjyxsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxzyywsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxqtywsr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxtzsy.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxlrze.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxsdsfy.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxjlr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxgffhze.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxcygrgdfhje.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxfcygrgdfhje.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxflfpze.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_jxdnkdzsczc.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_bccl.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_yjzzs.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_yjtdzzs.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_yjfcs.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_yjsds.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_qtsf.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_kdzsctr.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                et_gysyzc.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)


                et_yjzzs.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_yjsf.setText(TdlyUtils.getSum(et_yjzzs,et_yjtdzzs
                                ,et_yjfcs,et_yjsds,et_qtsf).toString())
                    }
                })
                et_yjtdzzs.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_yjsf.setText(TdlyUtils.getSum(et_yjzzs,et_yjtdzzs
                                ,et_yjfcs,et_yjsds,et_qtsf).toString())
                    }
                })
                et_yjfcs.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_yjsf.setText(TdlyUtils.getSum(et_yjzzs,et_yjtdzzs
                                ,et_yjfcs,et_yjsds,et_qtsf).toString())
                    }
                })
                et_yjsds.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_yjsf.setText(TdlyUtils.getSum(et_yjzzs,et_yjtdzzs
                                ,et_yjfcs,et_yjsds,et_qtsf).toString())
                    }
                })
                et_qtsf.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_yjsf.setText(TdlyUtils.getSum(et_yjzzs,et_yjtdzzs
                                ,et_yjfcs,et_yjsds,et_qtsf).toString())
                    }
                })

                et_jtgdzs.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_gdzs.setText(TdlyUtils.getSum(et_jtgdzs,et_cygrgdzs,et_fcygrgdrs).toString())
                    }
                })
                et_cygrgdzs.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_gdzs.setText(TdlyUtils.getSum(et_jtgdzs,et_cygrgdzs,et_fcygrgdrs).toString())
                    }
                })
                et_fcygrgdrs.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_gdzs.setText(TdlyUtils.getSum(et_jtgdzs,et_cygrgdzs,et_fcygrgdrs).toString())
                    }
                })

                et_jtgdgb.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_gbze.setText(TdlyUtils.getSum(et_jtgdgb,et_cygrgdgb,et_fcygrgdgb,et_qtgdgb).toString())
                    }
                })
                et_cygrgdgb.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_gbze.setText(TdlyUtils.getSum(et_jtgdgb,et_cygrgdgb,et_fcygrgdgb,et_qtgdgb).toString())
                    }
                })
                et_fcygrgdgb.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_gbze.setText(TdlyUtils.getSum(et_jtgdgb,et_cygrgdgb,et_fcygrgdgb,et_qtgdgb).toString())
                    }
                })
                et_qtgdgb.addTextChangedListener(object:TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        et_gbze.setText(TdlyUtils.getSum(et_jtgdgb,et_cygrgdgb,et_fcygrgdgb,et_qtgdgb).toString())
                    }
                })


            ll_reject.visibility = View.GONE
            CommenPop.backgroundAlpha(0.5f, activity)
            addJtzcUpPopu!!.showAtLocation(slv_frag_shzl_wwj, Gravity.CENTER, 0, 0)
                et_jclsj.setOnClickListener {
                    TimePickerUtil.initTimePickerViewNyr(activity,et_jclsj.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                        override fun onClick(data: String?) {
                            et_jclsj.setText(data)
                        }
                    })
                }
//                tv_pop_add_jtzc

                tv_pop_add_jtzc.setText(tv_frag_zcjy_xzsj.addText)//msg.get(0)
                for (i in bcjtzcEntitys){
                    if (tv_frag_zcjy_xzsj.addText.equals(i.years)){//msg.get(0)
                        et_jjtjjzzmc.setText(i.jjzzmc)
                        et_jclsj.setText(i.clsj)
                        et_ggqynzcze.setText(i.ggqzcze)
                        et_jzcze.setText(i.ggqjzcze)
                        et_gdzs.setText(i.gdzs)
                        et_jtgdzs.setText(i.jtgdzs)
                        et_cygrgdzs.setText(i.grgdrs)
                        et_60gdrs.setText(i.lsgdrs)
                        et_zjtgzgdrs.setText(i.jtgzgdrs)
                        et_fcygrgdrs.setText(i.fcygrgdrs)
                        et_gbze.setText(i.gbze)
                        et_jtgdgb.setText(i.jtgdgb)
                        et_cygrgdgb.setText(i.grgdgb)
                        et_fcygrgdgb.setText(i.fcygrgdgb)
                        et_qtgdgb.setText(i.qtgdgb)
                        et_jygljx.setText(i.jygljx)
                        et_jxzzc.setText(i.zzc)
                        et_jxjyxzc.setText(i.jyxzc)
//                        et_jyxwymj.setText(i.jyxwymj)
//                        et_czwymj.setText(i.czwymj)
                        et_jxjzc.setText(i.jzc)
                        et_jxjyxsr.setText(i.jyxsr)
                        et_jxzyywsr.setText(i.zyywsr)
                        et_jxqtywsr.setText(i.qtywsr)
                        et_jxtzsy.setText(i.tzsy)
                        et_jxlrze.setText(i.lrze)
                        et_jxsdsfy.setText(i.sdsfy)
                        et_jxjlr.setText(i.jlr)
                        et_jxgffhze.setText(i.gjfhze)
                        et_jxcygrgdfhje.setText(i.grgdfh)
                        et_jxfcygrgdfhje.setText(i.fcygrgdfh)
                        et_jxflfpze.setText(i.flfp)
                        et_jxdnkdzsczc.setText(i.kdzsczc)
                        et_bccl.setText(i.bccl)
                        et_yjsf.setText(i.yjsf)
                        et_yjzzs.setText(i.yjzzs)
                        et_yjtdzzs.setText(i.yjtdzzs)
                        et_yjfcs.setText(i.yjfcs)
                        et_yjsds.setText(i.yjsds)
                        et_qtsf.setText(i.qtsf)
                        et_kdzsctr.setText(i.kdzsczc)
                        et_gysyzc.setText(i.gysyzc)
                        bcjtzcEntitysId = i.id
                        bcjtzcEntitysProcess = i.process
                        if (i.ztdjzc.equals("A")){
                            if (!rbt_iszc_a.isChecked){
                                rgp_iszc.check(R.id.pop_add_jtzc_rbt_iszc_a)
                            }
                        }else if (i.ztdjzc.equals("B")){
                            if (!rbt_iszc_b.isChecked){
                                rgp_iszc.check(R.id.pop_add_jtzc_rbt_iszc_b)
                            }
                        }else if (i.ztdjzc.equals("C")){
                            if (!rbt_iszc_c.isChecked){
                                rgp_iszc.check(R.id.pop_add_jtzc_rbt_iszc_c)
                                rbt_iszc_c.isChecked = true
                            }
                        }
                        if (AppCache.getInstance().type == 4){
                            et_czrname.setText(i.cunname)
                            enableLl.setNoClick(false)
                            when(bcjtzcEntitysProcess){
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
                                if (bcjtzcEntitysProcess == 2){
                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else if (AppCache.getInstance().type == 2){
                                et_czrname.setText(i.qvname)
                                if (bcjtzcEntitysProcess == 3){
                                    confirm.visibility = View.VISIBLE
                                    reject.visibility = View.VISIBLE
                                }else{
                                    confirm.visibility = View.GONE
                                    reject.visibility = View.GONE
                                }
                            }else{
                                confirm.visibility = View.GONE
                                reject.visibility = View.GONE
                            }
                        }
                        break
                    }else{
                        et_jjtjjzzmc.setText("")
                        et_jclsj.setText("")
                        et_ggqynzcze.setText("")
                        et_jzcze.setText("")
                        et_gdzs.setText("")
                        et_jtgdzs.setText("")
                        et_cygrgdzs.setText("")
                        et_60gdrs.setText("")
                        et_zjtgzgdrs.setText("")
                        et_fcygrgdrs.setText("")
                        et_gbze.setText("")
                        et_jtgdgb.setText("")
                        et_cygrgdgb.setText("")
                        et_fcygrgdgb.setText("")
                        et_qtgdgb.setText("")
                        et_jygljx.setText("")
                        et_jxzzc.setText("")
                        et_jxjyxzc.setText("")
//                        et_jyxwymj.setText("")
//                        et_czwymj.setText("")
                        et_jxjzc.setText("")
                        et_jxjyxsr.setText("")
                        et_jxzyywsr.setText("")
                        et_jxqtywsr.setText("")
                        et_jxtzsy.setText("")
                        et_jxlrze.setText("")
                        et_jxsdsfy.setText("")
                        et_jxjlr.setText("")
                        et_jxgffhze.setText("")
                        et_jxcygrgdfhje.setText("")
                        et_jxfcygrgdfhje.setText("")
                        et_jxflfpze.setText("")
                        et_jxdnkdzsczc.setText("")
                        et_bccl.setText("")
                        et_yjsf.setText("")
                        et_yjzzs.setText("")
                        et_yjtdzzs.setText("")
                        et_yjfcs.setText("")
                        et_yjsds.setText("")
                        et_qtsf.setText("")
                        et_kdzsctr.setText("")
                        et_gysyzc.setText("")
                        rgp_iszc.clearCheck()
                        bcjtzcEntitysId = 0L
                        bcjtzcEntitysProcess = 0
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
                /*tv_pop_add_jtzc.setOnClickListener {

                    //条件选择器
                    val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(activity, OnOptionsSelectListener { options1, option2, options3, v ->
                        //                        setCheck(quList,options1)
                        tv_pop_add_jtzc.setText(msg.get(options1))

                        for (i in bcjtzcEntitys){
                            if (msg.get(options1).equals(i.years)){
                                et_jjtjjzzmc.setText(i.jjzzmc)
                                et_jclsj.setText(i.clsj)
                                et_ggqynzcze.setText(i.ggqzcze)
                                et_jzcze.setText(i.ggqjzcze)
                                et_gdzs.setText(i.gdzs)
                                et_jtgdzs.setText(i.jtgdzs)
                                et_cygrgdzs.setText(i.grgdrs)
                                et_60gdrs.setText(i.lsgdrs)
                                et_zjtgzgdrs.setText(i.jtgzgdrs)
                                et_fcygrgdrs.setText(i.fcygrgdrs)
                                et_gbze.setText(i.gbze)
                                et_jtgdgb.setText(i.jtgdgb)
                                et_cygrgdgb.setText(i.grgdgb)
                                et_fcygrgdgb.setText(i.fcygrgdgb)
                                et_qtgdgb.setText(i.qtgdgb)
                                et_jygljx.setText(i.jygljx)
                                et_jxzzc.setText(i.zzc)
                                et_jxjyxzc.setText(i.jyxzc)
                                et_jxjzc.setText(i.jzc)
                                et_jxjyxsr.setText(i.jyxsr)
                                et_jxzyywsr.setText(i.zyywsr)
                                et_jxqtywsr.setText(i.qtywsr)
                                et_jxtzsy.setText(i.tzsy)
                                et_jxlrze.setText(i.lrze)
                                et_jxsdsfy.setText(i.sdsfy)
                                et_jxjlr.setText(i.jlr)
                                et_jxgffhze.setText(i.gjfhze)
                                et_jxcygrgdfhje.setText(i.grgdfh)
                                et_jxfcygrgdfhje.setText(i.fcygrgdfh)
                                et_jxflfpze.setText(i.flfp)
                                et_jxdnkdzsczc.setText(i.kdzsczc)
                                et_bccl.setText(i.bccl)
                                et_yjsf.setText(i.yjsf)
                                et_yjzzs.setText(i.yjzzs)
                                et_yjtdzzs.setText(i.yjtdzzs)
                                et_yjfcs.setText(i.yjfcs)
                                et_yjsds.setText(i.yjsds)
                                et_qtsf.setText(i.qtsf)
                                et_kdzsctr.setText(i.kdzsczc)
                                et_gysyzc.setText(i.gysyzc)
                                bcjtzcEntitysId = i.id
                                bcjtzcEntitysProcess = i.process
                                if (i.ztdjzc.equals("A")){
                                    if (!rbt_iszc_a.isChecked){
                                        rgp_iszc.check(R.id.pop_add_jtzc_rbt_iszc_a)
                                    }
                                }else if (i.ztdjzc.equals("B")){
                                    if (!rbt_iszc_b.isChecked){
                                        rgp_iszc.check(R.id.pop_add_jtzc_rbt_iszc_b)
                                    }
                                }else if (i.ztdjzc.equals("C")){
                                    if (!rbt_iszc_c.isChecked){
                                        rgp_iszc.check(R.id.pop_add_jtzc_rbt_iszc_c)
                                        rbt_iszc_c.isChecked = true
                                    }
                                }
                                if (AppCache.getInstance().type == 4){
                                    et_czrname.setText(i.cunname)
                                    enableLl.setNoClick(false)
                                    when(bcjtzcEntitysProcess){
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
                                        if (bcjtzcEntitysProcess == 2){
                                            confirm.visibility = View.VISIBLE
                                            reject.visibility = View.VISIBLE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else if (AppCache.getInstance().type == 2){
                                        et_czrname.setText(i.qvname)
                                        if (bcjtzcEntitysProcess == 3){
                                            confirm.visibility = View.VISIBLE
                                            reject.visibility = View.VISIBLE
                                        }else{
                                            confirm.visibility = View.GONE
                                            reject.visibility = View.GONE
                                        }
                                    }else{
                                        confirm.visibility = View.GONE
                                        reject.visibility = View.GONE
                                    }
                                }
                                break
                            }else{
                                et_jjtjjzzmc.setText("")
                                et_jclsj.setText("")
                                et_ggqynzcze.setText("")
                                et_jzcze.setText("")
                                et_gdzs.setText("")
                                et_jtgdzs.setText("")
                                et_cygrgdzs.setText("")
                                et_60gdrs.setText("")
                                et_zjtgzgdrs.setText("")
                                et_fcygrgdrs.setText("")
                                et_gbze.setText("")
                                et_jtgdgb.setText("")
                                et_cygrgdgb.setText("")
                                et_fcygrgdgb.setText("")
                                et_qtgdgb.setText("")
                                et_jygljx.setText("")
                                et_jxzzc.setText("")
                                et_jxjyxzc.setText("")
                                et_jxjzc.setText("")
                                et_jxjyxsr.setText("")
                                et_jxzyywsr.setText("")
                                et_jxqtywsr.setText("")
                                et_jxtzsy.setText("")
                                et_jxlrze.setText("")
                                et_jxsdsfy.setText("")
                                et_jxjlr.setText("")
                                et_jxgffhze.setText("")
                                et_jxcygrgdfhje.setText("")
                                et_jxfcygrgdfhje.setText("")
                                et_jxflfpze.setText("")
                                et_jxdnkdzsczc.setText("")
                                et_bccl.setText("")
                                et_yjsf.setText("")
                                et_yjzzs.setText("")
                                et_yjtdzzs.setText("")
                                et_yjfcs.setText("")
                                et_yjsds.setText("")
                                et_qtsf.setText("")
                                et_kdzsctr.setText("")
                                et_gysyzc.setText("")
                                rgp_iszc.clearCheck()
                                bcjtzcEntitysId = 0L
                                bcjtzcEntitysProcess = 0
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


//                        getZhenList(xzqList,quList.get(options1).code)
                    })
                            .isDialog(true)
                            .isAlphaGradient(true)
                            .build<String>()
                    pvOptions.setSelectOptions(msg.indexOf(tv_pop_add_jtzc.text.toString()))
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
            val entity = BcjtzcEntity()
                entity.ztdjzc = "A"
            rgp_iszc.setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener{
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                    when(checkedId){
                        R.id.pop_add_jtzc_rbt_iszc_a->{
                            entity.ztdjzc = "A"
                        }
                        R.id.pop_add_jtzc_rbt_iszc_b->{
                            entity.ztdjzc = "B"
                        }
                        R.id.pop_add_jtzc_rbt_iszc_c->{
                            entity.ztdjzc = "C"
                        }
                    }
                }
            })
            when(AppCache.getInstance().type){
                2->{//区
                    confirm.text = "通过"
                    reject.text = "驳回"
                    //区 下一步
                    confirm.setOnClickListener {
                        entity.jjzzmc = et_jjtjjzzmc.text.toString()
                        entity.clsj = et_jclsj.text.toString()
                        entity.ggqzcze = et_ggqynzcze.text.toString()
                        entity.ggqjzcze = et_jzcze.text.toString()
                        entity.gdzs = et_gdzs.text.toString()
                        entity.jtgdzs = et_jtgdzs.text.toString()
                        entity.grgdrs = et_cygrgdzs.text.toString()
                        entity.lsgdrs = et_60gdrs.text.toString()
                        entity.jtgzgdrs = et_zjtgzgdrs.text.toString()
                        entity.fcygrgdrs = et_fcygrgdrs.text.toString()
                        entity.gbze = et_gbze.text.toString()
                        entity.jtgdgb = et_jtgdgb.text.toString()
                        entity.grgdgb = et_cygrgdgb.text.toString()
                        entity.fcygrgdgb = et_fcygrgdgb.text.toString()
                        entity.qtgdgb = et_qtgdgb.text.toString()
                        entity.jygljx = et_jygljx.text.toString()
                        entity.zzc = et_jxzzc.text.toString()
                        entity.jyxzc = et_jxjyxzc.text.toString()
//                        entity.jyxwymj = et_jyxwymj.text.toString()
//                        entity.czwymj = et_czwymj.text.toString()
                        entity.jzc = et_jxjzc.text.toString()
                        entity.jyxsr = et_jxjyxsr.text.toString()
                        entity.zyywsr = et_jxzyywsr.text.toString()
                        entity.qtywsr = et_jxqtywsr.text.toString()
                        entity.tzsy = et_jxtzsy.text.toString()
                        entity.lrze = et_jxlrze.text.toString()
                        entity.sdsfy = et_jxsdsfy.text.toString()
                        entity.jlr = et_jxjlr.text.toString()
                        entity.gjfhze = et_jxgffhze.text.toString()
                        entity.grgdfh = et_jxcygrgdfhje.text.toString()
                        entity.fcygrgdfh = et_jxfcygrgdfhje.text.toString()
                        entity.flfp = et_jxflfpze.text.toString()
                        entity.kdzsczc = et_jxdnkdzsczc.text.toString()
                        entity.yjsf = et_yjsf.text.toString()
                        entity.bccl = et_bccl.text.toString()
                        entity.yjzzs = et_yjzzs.text.toString()
                        entity.yjtdzzs = et_yjtdzzs.text.toString()
                        entity.yjfcs = et_yjfcs.text.toString()
                        entity.yjsds = et_yjsds.text.toString()
                        entity.qtsf = et_qtsf.text.toString()
                        entity.kdzsczc = et_kdzsctr.text.toString()
                        entity.gysyzc = et_gysyzc.text.toString()
                        entity.years = tv_pop_add_jtzc.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 4
                        entity.id = bcjtzcEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.qvname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateJtzc(entity)
                        }
                    }
                    //区 驳回
                    reject.setOnClickListener {
                        entity.jjzzmc = et_jjtjjzzmc.text.toString()
                        entity.clsj = et_jclsj.text.toString()
                        entity.ggqzcze = et_ggqynzcze.text.toString()
                        entity.ggqjzcze = et_jzcze.text.toString()
                        entity.gdzs = et_gdzs.text.toString()
                        entity.jtgdzs = et_jtgdzs.text.toString()
                        entity.grgdrs = et_cygrgdzs.text.toString()
                        entity.lsgdrs = et_60gdrs.text.toString()
                        entity.jtgzgdrs = et_zjtgzgdrs.text.toString()
                        entity.fcygrgdrs = et_fcygrgdrs.text.toString()
                        entity.gbze = et_gbze.text.toString()
                        entity.jtgdgb = et_jtgdgb.text.toString()
                        entity.grgdgb = et_cygrgdgb.text.toString()
                        entity.fcygrgdgb = et_fcygrgdgb.text.toString()
                        entity.qtgdgb = et_qtgdgb.text.toString()
                        entity.jygljx = et_jygljx.text.toString()
                        entity.zzc = et_jxzzc.text.toString()
                        entity.jyxzc = et_jxjyxzc.text.toString()
//                        entity.jyxwymj = et_jyxwymj.text.toString()
//                        entity.czwymj = et_czwymj.text.toString()
                        entity.jzc = et_jxjzc.text.toString()
                        entity.jyxsr = et_jxjyxsr.text.toString()
                        entity.zyywsr = et_jxzyywsr.text.toString()
                        entity.qtywsr = et_jxqtywsr.text.toString()
                        entity.tzsy = et_jxtzsy.text.toString()
                        entity.lrze = et_jxlrze.text.toString()
                        entity.sdsfy = et_jxsdsfy.text.toString()
                        entity.jlr = et_jxjlr.text.toString()
                        entity.gjfhze = et_jxgffhze.text.toString()
                        entity.grgdfh = et_jxcygrgdfhje.text.toString()
                        entity.fcygrgdfh = et_jxfcygrgdfhje.text.toString()
                        entity.flfp = et_jxflfpze.text.toString()
                        entity.kdzsczc = et_jxdnkdzsczc.text.toString()
                        entity.bccl = et_bccl.text.toString()
                        entity.yjsf = et_yjsf.text.toString()
                        entity.yjzzs = et_yjzzs.text.toString()
                        entity.yjtdzzs = et_yjtdzzs.text.toString()
                        entity.yjfcs = et_yjfcs.text.toString()
                        entity.yjsds = et_yjsds.text.toString()
                        entity.qtsf = et_qtsf.text.toString()
                        entity.kdzsczc = et_kdzsctr.text.toString()
                        entity.gysyzc = et_gysyzc.text.toString()
                        entity.years = tv_pop_add_jtzc.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjtzcEntitysId
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
                            mPresenter.getBcjcUpdateJtzc(entity)
                        }
                    }
                }
                3->{//镇
                    confirm.text = "下一步"
                    reject.text = "驳回"

                    //镇 下一步
                    confirm.setOnClickListener {
                        entity.jjzzmc = et_jjtjjzzmc.text.toString()
                        entity.clsj = et_jclsj.text.toString()
                        entity.ggqzcze = et_ggqynzcze.text.toString()
                        entity.ggqjzcze = et_jzcze.text.toString()
                        entity.gdzs = et_gdzs.text.toString()
                        entity.jtgdzs = et_jtgdzs.text.toString()
                        entity.grgdrs = et_cygrgdzs.text.toString()
                        entity.lsgdrs = et_60gdrs.text.toString()
                        entity.jtgzgdrs = et_zjtgzgdrs.text.toString()
                        entity.fcygrgdrs = et_fcygrgdrs.text.toString()
                        entity.gbze = et_gbze.text.toString()
                        entity.jtgdgb = et_jtgdgb.text.toString()
                        entity.grgdgb = et_cygrgdgb.text.toString()
                        entity.fcygrgdgb = et_fcygrgdgb.text.toString()
                        entity.qtgdgb = et_qtgdgb.text.toString()
                        entity.jygljx = et_jygljx.text.toString()
                        entity.zzc = et_jxzzc.text.toString()
                        entity.jyxzc = et_jxjyxzc.text.toString()
//                        entity.jyxwymj = et_jyxwymj.text.toString()
//                        entity.czwymj = et_czwymj.text.toString()
                        entity.jzc = et_jxjzc.text.toString()
                        entity.jyxsr = et_jxjyxsr.text.toString()
                        entity.zyywsr = et_jxzyywsr.text.toString()
                        entity.qtywsr = et_jxqtywsr.text.toString()
                        entity.tzsy = et_jxtzsy.text.toString()
                        entity.lrze = et_jxlrze.text.toString()
                        entity.sdsfy = et_jxsdsfy.text.toString()
                        entity.jlr = et_jxjlr.text.toString()
                        entity.gjfhze = et_jxgffhze.text.toString()
                        entity.grgdfh = et_jxcygrgdfhje.text.toString()
                        entity.fcygrgdfh = et_jxfcygrgdfhje.text.toString()
                        entity.flfp = et_jxflfpze.text.toString()
                        entity.kdzsczc = et_jxdnkdzsczc.text.toString()
                        entity.bccl = et_bccl.text.toString()
                        entity.yjsf = et_yjsf.text.toString()
                        entity.yjzzs = et_yjzzs.text.toString()
                        entity.yjtdzzs = et_yjtdzzs.text.toString()
                        entity.yjfcs = et_yjfcs.text.toString()
                        entity.yjsds = et_yjsds.text.toString()
                        entity.qtsf = et_qtsf.text.toString()
                        entity.kdzsczc = et_kdzsctr.text.toString()
                        entity.gysyzc = et_gysyzc.text.toString()
                        entity.years = tv_pop_add_jtzc.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 3
                        entity.id = bcjtzcEntitysId
                        //修改接口
                        if (et_czrname.text.toString().equals("")){
                            ToastUtils.showShort("请输入操作员姓名！")
                        }else{
                            entity.zhenname = et_czrname.text.toString()
                            mPresenter.getBcjcUpdateJtzc(entity)
                        }
                    }
                    //镇 驳回
                    reject.setOnClickListener {
                        entity.jjzzmc = et_jjtjjzzmc.text.toString()
                        entity.clsj = et_jclsj.text.toString()
                        entity.ggqzcze = et_ggqynzcze.text.toString()
                        entity.ggqjzcze = et_jzcze.text.toString()
                        entity.gdzs = et_gdzs.text.toString()
                        entity.jtgdzs = et_jtgdzs.text.toString()
                        entity.grgdrs = et_cygrgdzs.text.toString()
                        entity.lsgdrs = et_60gdrs.text.toString()
                        entity.jtgzgdrs = et_zjtgzgdrs.text.toString()
                        entity.fcygrgdrs = et_fcygrgdrs.text.toString()
                        entity.gbze = et_gbze.text.toString()
                        entity.jtgdgb = et_jtgdgb.text.toString()
                        entity.grgdgb = et_cygrgdgb.text.toString()
                        entity.fcygrgdgb = et_fcygrgdgb.text.toString()
                        entity.qtgdgb = et_qtgdgb.text.toString()
                        entity.jygljx = et_jygljx.text.toString()
                        entity.zzc = et_jxzzc.text.toString()
                        entity.jyxzc = et_jxjyxzc.text.toString()
//                        entity.jyxwymj = et_jyxwymj.text.toString()
//                        entity.czwymj = et_czwymj.text.toString()
                        entity.jzc = et_jxjzc.text.toString()
                        entity.jyxsr = et_jxjyxsr.text.toString()
                        entity.zyywsr = et_jxzyywsr.text.toString()
                        entity.qtywsr = et_jxqtywsr.text.toString()
                        entity.tzsy = et_jxtzsy.text.toString()
                        entity.lrze = et_jxlrze.text.toString()
                        entity.sdsfy = et_jxsdsfy.text.toString()
                        entity.jlr = et_jxjlr.text.toString()
                        entity.gjfhze = et_jxgffhze.text.toString()
                        entity.grgdfh = et_jxcygrgdfhje.text.toString()
                        entity.fcygrgdfh = et_jxfcygrgdfhje.text.toString()
                        entity.flfp = et_jxflfpze.text.toString()
                        entity.kdzsczc = et_jxdnkdzsczc.text.toString()
                        entity.bccl = et_bccl.text.toString()
                        entity.yjsf = et_yjsf.text.toString()
                        entity.yjzzs = et_yjzzs.text.toString()
                        entity.yjtdzzs = et_yjtdzzs.text.toString()
                        entity.yjfcs = et_yjfcs.text.toString()
                        entity.yjsds = et_yjsds.text.toString()
                        entity.qtsf = et_qtsf.text.toString()
                        entity.kdzsczc = et_kdzsctr.text.toString()
                        entity.gysyzc = et_gysyzc.text.toString()
                        entity.years = tv_pop_add_jtzc.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 1
                        entity.id = bcjtzcEntitysId
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
                            mPresenter.getBcjcUpdateJtzc(entity)
                        }
                    }
                }
                4->{//村
                    confirm.text = "下一步"
                    reject.text = "保存"
                    //村 添加和保存按钮
                    reject.setOnClickListener {
                        entity.jjzzmc = et_jjtjjzzmc.text.toString()
                        entity.clsj = et_jclsj.text.toString()
                        entity.ggqzcze = et_ggqynzcze.text.toString()
                        entity.ggqjzcze = et_jzcze.text.toString()
                        entity.gdzs = et_gdzs.text.toString()
                        entity.jtgdzs = et_jtgdzs.text.toString()
                        entity.grgdrs = et_cygrgdzs.text.toString()
                        entity.lsgdrs = et_60gdrs.text.toString()
                        entity.jtgzgdrs = et_zjtgzgdrs.text.toString()
                        entity.fcygrgdrs = et_fcygrgdrs.text.toString()
                        entity.gbze = et_gbze.text.toString()
                        entity.jtgdgb = et_jtgdgb.text.toString()
                        entity.grgdgb = et_cygrgdgb.text.toString()
                        entity.fcygrgdgb = et_fcygrgdgb.text.toString()
                        entity.qtgdgb = et_qtgdgb.text.toString()
                        entity.jygljx = et_jygljx.text.toString()
                        entity.zzc = et_jxzzc.text.toString()
                        entity.jyxzc = et_jxjyxzc.text.toString()
//                        entity.jyxwymj = et_jyxwymj.text.toString()
//                        entity.czwymj = et_czwymj.text.toString()
                        entity.jzc = et_jxjzc.text.toString()
                        entity.jyxsr = et_jxjyxsr.text.toString()
                        entity.zyywsr = et_jxzyywsr.text.toString()
                        entity.qtywsr = et_jxqtywsr.text.toString()
                        entity.tzsy = et_jxtzsy.text.toString()
                        entity.lrze = et_jxlrze.text.toString()
                        entity.sdsfy = et_jxsdsfy.text.toString()
                        entity.jlr = et_jxjlr.text.toString()
                        entity.gjfhze = et_jxgffhze.text.toString()
                        entity.grgdfh = et_jxcygrgdfhje.text.toString()
                        entity.fcygrgdfh = et_jxfcygrgdfhje.text.toString()
                        entity.flfp = et_jxflfpze.text.toString()
                        entity.kdzsczc = et_jxdnkdzsczc.text.toString()
                        entity.bccl = et_bccl.text.toString()
                        entity.yjsf = et_yjsf.text.toString()
                        entity.yjzzs = et_yjzzs.text.toString()
                        entity.yjtdzzs = et_yjtdzzs.text.toString()
                        entity.yjfcs = et_yjfcs.text.toString()
                        entity.yjsds = et_yjsds.text.toString()
                        entity.qtsf = et_qtsf.text.toString()
                        entity.kdzsczc = et_kdzsctr.text.toString()
                        entity.gysyzc = et_gysyzc.text.toString()
                        entity.years = tv_pop_add_jtzc.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()

                        entity.process = 1
                        /*val editToast = TdlyUtils.setEditToast(et_jjtjjzzmc, et_jclsj, et_ggqynzcze, et_jzcze, et_gdzs, et_jtgdzs, et_cygrgdzs, et_60gdrs, et_zjtgzgdrs, et_fcygrgdrs, et_gbze
                                , et_jtgdgb, et_cygrgdgb, et_fcygrgdgb, et_qtgdgb, et_jygljx, et_jxzzc, et_jxjyxzc, et_jxjzc, et_jxjyxsr
                                , et_jxlrze, et_jxjlr, et_jxgffhze, et_jxcygrgdfhje, et_jxfcygrgdfhje, et_jxflfpze
                                , et_bccl, et_yjsf, et_yjzzs, et_yjtdzzs, et_yjfcs, et_yjsds, et_qtsf, et_kdzsctr, et_gysyzc)
                        if (!editToast){*/
                            if (bcjtzcEntitysId==0L){
                                if (et_czrname.text.toString().equals("")){
                                    ToastUtils.showShort("请输入操作员姓名！")
                                }else{
                                    entity.cunname = et_czrname.text.toString()
                                    mPresenter.getBcjcAddJtzc(entity)
                                }
                            }else{
                                entity.id = bcjtzcEntitysId
                                //修改接口
                                if (et_czrname.text.toString().equals("")){
                                    ToastUtils.showShort("请输入操作员姓名！")
                                }else{
                                    entity.cunname = et_czrname.text.toString()
                                    mPresenter.getBcjcUpdateJtzc(entity)
                                }
                            }
//                        }
                    }
                    //村 下一步
                    confirm.setOnClickListener {
                        entity.jjzzmc = et_jjtjjzzmc.text.toString()
                        entity.clsj = et_jclsj.text.toString()
                        entity.ggqzcze = et_ggqynzcze.text.toString()
                        entity.ggqjzcze = et_jzcze.text.toString()
                        entity.gdzs = et_gdzs.text.toString()
                        entity.jtgdzs = et_jtgdzs.text.toString()
                        entity.grgdrs = et_cygrgdzs.text.toString()
                        entity.lsgdrs = et_60gdrs.text.toString()
                        entity.jtgzgdrs = et_zjtgzgdrs.text.toString()
                        entity.fcygrgdrs = et_fcygrgdrs.text.toString()
                        entity.gbze = et_gbze.text.toString()
                        entity.jtgdgb = et_jtgdgb.text.toString()
                        entity.grgdgb = et_cygrgdgb.text.toString()
                        entity.fcygrgdgb = et_fcygrgdgb.text.toString()
                        entity.qtgdgb = et_qtgdgb.text.toString()
                        entity.jygljx = et_jygljx.text.toString()
                        entity.zzc = et_jxzzc.text.toString()
                        entity.jyxzc = et_jxjyxzc.text.toString()
//                        entity.jyxwymj = et_jyxwymj.text.toString()
//                        entity.czwymj = et_czwymj.text.toString()
                        entity.jzc = et_jxjzc.text.toString()
                        entity.jyxsr = et_jxjyxsr.text.toString()
                        entity.zyywsr = et_jxzyywsr.text.toString()
                        entity.qtywsr = et_jxqtywsr.text.toString()
                        entity.tzsy = et_jxtzsy.text.toString()
                        entity.lrze = et_jxlrze.text.toString()
                        entity.sdsfy = et_jxsdsfy.text.toString()
                        entity.jlr = et_jxjlr.text.toString()
                        entity.gjfhze = et_jxgffhze.text.toString()
                        entity.grgdfh = et_jxcygrgdfhje.text.toString()
                        entity.fcygrgdfh = et_jxfcygrgdfhje.text.toString()
                        entity.flfp = et_jxflfpze.text.toString()
                        entity.kdzsczc = et_jxdnkdzsczc.text.toString()
                        entity.bccl = et_bccl.text.toString()
                        entity.yjsf = et_yjsf.text.toString()
                        entity.yjzzs = et_yjzzs.text.toString()
                        entity.yjtdzzs = et_yjtdzzs.text.toString()
                        entity.yjfcs = et_yjfcs.text.toString()
                        entity.yjsds = et_yjsds.text.toString()
                        entity.qtsf = et_qtsf.text.toString()
                        entity.kdzsczc = et_kdzsctr.text.toString()
                        entity.gysyzc = et_gysyzc.text.toString()
                        entity.years = tv_pop_add_jtzc.text.toString()
                        entity.code = AppCache.getInstance().code
                        entity.xzqmc = HomePageFragment.tvFragHomepageLeft!!.text.toString()
                        entity.process = 2

                        //修改接口
                        val editToast = TdlyUtils.setEditToast(et_jjtjjzzmc, et_jclsj, et_ggqynzcze, et_jzcze, et_gdzs, et_jtgdzs, et_cygrgdzs, et_60gdrs, et_zjtgzgdrs, et_fcygrgdrs, et_gbze
                                , et_jtgdgb, et_cygrgdgb, et_fcygrgdgb, et_qtgdgb, et_jygljx, et_jxzzc, et_jxjyxzc, et_jxjzc, et_jxjyxsr
                                , et_jxlrze, et_jxjlr, et_jxgffhze, et_jxcygrgdfhje, et_jxfcygrgdfhje, et_jxflfpze
                                , et_bccl, et_yjsf, et_yjzzs, et_yjtdzzs, et_yjfcs, et_yjsds, et_qtsf, et_kdzsctr, et_gysyzc)
                        if (!editToast){
                            if (et_czrname.text.toString().equals("")){
                                ToastUtils.showShort("请输入操作员姓名！")
                            }else{
                                if (bcjtzcEntitysId == 0L){
                                    entity.cunname = et_czrname.text.toString()
                                    mPresenter.getBcjcAddJtzc(entity)
                                }else{
                                    entity.id = bcjtzcEntitysId
                                    entity.cunname = et_czrname.text.toString()
                                    mPresenter.getBcjcUpdateJtzc(entity)
                                }
                            }
                        }
                    }
                }
            }

            close.setOnClickListener {
                addJtzcUpPopu!!.dismiss()
            }
            }

        }
    }
    var bcjtzcEntitys = ArrayList<BcjtzcEntity>()
    override fun returnBcjcJtzcAll(msg: List<BcjtzcEntity>) {
        bcjtzcEntitys.clear()
        bcjtzcEntitys.addAll(msg)
    }
    override fun returnBcjcJtzc(msg: List<BcjtzcEntity>) {
        if (yearUpPopu!=null){
            yearUpPopu!!.dismiss()
        }
        if (addJtzcUpPopu!=null){
            addJtzcUpPopu!!.dismiss()
        }
        if (msg!=null){//&&msg.size>0
            if (msg.size>0){//tv_frag_tdqs_zhengdqk_add
                val get = msg.get(0)
                when(get.process){
                    1->{
                        tv_frag_shzl_wwj_gzjddsb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_shzl_wwj_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    2->{
                        tv_frag_shzl_wwj_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_shzl_wwj_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    3->{
                        tv_frag_shzl_wwj_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdysb.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_shzl_wwj_gzjdtjcg.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                    4->{
                        tv_frag_shzl_wwj_gzjddsb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdysb.setTextColor(Color.parseColor("#F98648"))
                        tv_frag_shzl_wwj_gzjdtjcg.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_shzl_wwj_gzjdbh.setTextColor(Color.parseColor("#F98648"))
                    }
                }
                tll_frag_shzl_wwj_jtzcqk.setValueText(sptypeList,get.process-1,get.process-1)
                if (AppCache.getInstance().type==4&&AppCache.getInstance().duties!=1&&(get.process==1||get.process==0)&&((split+"年").equals(get.years)||get.years.equals(""))){
                    tv_frag_jtzc_add.visibility = View.VISIBLE
                    tv_frag_jtzc_add.setText("操作")
                }else
                    if (AppCache.getInstance().type==3&&AppCache.getInstance().duties!=1&&get.process==2&&(split+"年").equals(get.years)){
                        tv_frag_jtzc_add.visibility = View.VISIBLE
                        tv_frag_jtzc_add.setText("操作")
                    }else
                        if (AppCache.getInstance().type==2&&AppCache.getInstance().duties!=1&&get.process==3&&(split+"年").equals(get.years)){
                            tv_frag_jtzc_add.visibility = View.VISIBLE
                            tv_frag_jtzc_add.setText("操作")
                        }else{
                            if (get.process> TypeBzEnum.getName(AppCache.getInstance().type)){
                                tv_frag_jtzc_add.setText("已上报")
                            }else{
                                tv_frag_jtzc_add.setText("未上报")
                            }
                            if (AppCache.getInstance().type==1){
                                tv_frag_jtzc_add.visibility = View.GONE
                            }
                        }
            }else if (AppCache.getInstance().type==4){
                tll_frag_shzl_wwj_jtzcqk.setValueText(sptypeList,-1,-1)
                tv_frag_jtzc_add.visibility = View.VISIBLE
            }else if (AppCache.getInstance().type!=4){
                tll_frag_shzl_wwj_jtzcqk.setValueText(sptypeList,-1,-1)
                if (AppCache.getInstance().type==1){
                    tv_frag_jtzc_add.visibility = View.GONE
                }
                tv_frag_jtzc_add.setText("未上报")
            }

            rlv_frag_jtzc.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rlv_frag_jtzc.adapter = object :BaseQuickAdapter<BcjtzcEntity, BaseViewHolder>(R.layout.item_bcjc_jtzc,msg){
                override fun convert(helper: BaseViewHolder?, item: BcjtzcEntity?) {
                    helper!!.setText(R.id.item_bcjc_jtzc_year,item!!.years)
                            .setText(R.id.item_bcjc_jtzc_jtjjzzmc,item.jjzzmc)
                            .setText(R.id.item_bcjc_jtzc_clsj,item.clsj)
                            .setText(R.id.item_bcjc_jtzc_iszc,item.ztdjzc)
                            .setText(R.id.item_bcjc_jtzc_ggqynzcze,item.ggqzcze)
                            .setText(R.id.item_bcjc_jtzc_ggqjzcze,item.ggqjzcze)
                            .setText(R.id.item_bcjc_jtzc_gdzs,item.gdzs)
                            .setText(R.id.item_bcjc_jtzc_jtgdzs,item.jtgdzs)
                            .setText(R.id.item_bcjc_jtzc_cygrgdrs,item.grgdrs)
                            .setText(R.id.item_bcjc_jtzc_60gdrs,item.lsgdrs)
                            .setText(R.id.item_bcjc_jtzc_zjtgzgdrs,item.jtgzgdrs)
                            .setText(R.id.item_bcjc_jtzc_fcygrgdrs,item.fcygrgdrs)
                            .setText(R.id.item_bcjc_jtzc_gbze,item.gbze)
                            .setText(R.id.item_bcjc_jtzc_jtgdgb,item.jtgdgb)
                            .setText(R.id.item_bcjc_jtzc_cygrgdgb,item.grgdgb)
                            .setText(R.id.item_bcjc_jtzc_fcygrgdgb,item.fcygrgdgb)
                            .setText(R.id.item_bcjc_jtzc_qtgdgb,item.qtgdgb)
                            .setText(R.id.item_bcjc_jtzc_jygljx,item.jygljx)
                            .setText(R.id.item_bcjc_jtzc_zzc,item.zzc)
                            .setText(R.id.item_bcjc_jtzc_jyxzc,item.jyxzc)
                            .setText(R.id.item_bcjc_jtzc_jyxwymj,item.jyxwymj)
                            .setText(R.id.item_bcjc_jtzc_czwymj,item.czwymj)
                            .setText(R.id.item_bcjc_jtzc_jzc,item.jzc)
                            .setText(R.id.item_bcjc_jtzc_jyxsr,item.jyxsr)
                            .setText(R.id.item_bcjc_jtzc_zyywsr,item.zyywsr)
                            .setText(R.id.item_bcjc_jtzc_qtywsr,item.qtywsr)
                            .setText(R.id.item_bcjc_jtzc_tzsy,item.tzsy)
                            .setText(R.id.item_bcjc_jtzc_lrze,item.lrze)
                            .setText(R.id.item_bcjc_jtzc_sdsfy,item.sdsfy)
                            .setText(R.id.item_bcjc_jtzc_jlr,item.jlr)
                            .setText(R.id.item_bcjc_jtzc_gjfhze,item.gjfhze)
                            .setText(R.id.item_bcjc_jtzc_cygrgdfhje,item.grgdfh)
                            .setText(R.id.item_bcjc_jtzc_fcygrgdfhje,item.fcygrgdfh)
                            .setText(R.id.item_bcjc_jtzc_flfpze,item.flfp)
                            .setText(R.id.item_bcjc_jtzc_dnkdzsczc,item.kdzsczc)
                            .setText(R.id.item_bcjc_jtzc_bccl,item.bccl)
                            .setText(R.id.item_bcjc_jtzc_yjsf,item.yjsf)
                            .setText(R.id.item_bcjc_jtzc_yjzzs,item.yjzzs)
                            .setText(R.id.item_bcjc_jtzc_yjtdzzs,item.yjtdzzs)
                            .setText(R.id.item_bcjc_jtzc_yjfcs,item.yjfcs)
                            .setText(R.id.item_bcjc_jtzc_yjsds,item.yjsds)
                            .setText(R.id.item_bcjc_jtzc_qtsf,item.qtsf)
                            .setText(R.id.item_bcjc_jtzc_kdzsctr,item.kdzsczc)
                            .setText(R.id.item_bcjc_jtzc_gysyzc,item.gysyzc)

                    val years = helper.getView<TextView>(R.id.item_bcjc_jtzc_year)
                    if (item.bcrejectedEntities.size>0){
                        tv_frag_shzl_wwj_gzjdbh.setTextColor(Color.parseColor("#1B96EE"))
                        tv_frag_shzl_wwj_gzjdbh.visibility = View.VISIBLE
                        iv_frag_shzl_wwj_gzjdbh.visibility = View.VISIBLE
                        /*years.setTextColor(Color.parseColor("#ff6000"))
                        years.isEnabled = true*/
                    }else{
                        tv_frag_shzl_wwj_gzjdbh.setTextColor(Color.parseColor("#24A70E"))
                        tv_frag_shzl_wwj_gzjdbh.visibility = View.GONE
                        iv_frag_shzl_wwj_gzjdbh.visibility = View.GONE
                        /*years.setTextColor(Color.parseColor("#000000"))
                        years.isEnabled = false*/
                    }
                    tv_frag_shzl_wwj_gzjdbh.setOnClickListener {
                        rejectUpPopup(item.bcrejectedEntities as ArrayList<BcrejectedEntity>)
                    }
                }

            }
        }else{
            ToastUtils.showShort("该年份暂无数据")
        }
    }

    //驳回弹出框
    fun rejectUpPopup(list :ArrayList<BcrejectedEntity>){
        rejectUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject_list, slv_frag_shzl_wwj)
        val contentView: View = rejectUpPopu!!.getContentView()
        val recyclerView = contentView.findViewById<RecyclerView>(R.id.pop_bcjc_reject_list_rlv)
        val close = contentView.findViewById<TextView>(R.id.pop_bcjc_reject_list_close)
        CommenPop.backgroundAlpha(0.5f, activity)
        rejectUpPopu!!.showAtLocation(slv_frag_shzl_wwj, Gravity.CENTER, 0, 0)
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
    override fun returnBcjcAddJtzc(msg: String) {
        ToastUtils.showShort(msg)
        if (addJtzcUpPopu!=null){
            addJtzcUpPopu!!.dismiss()
        }
        mPresenter.getBcjcJtzc(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcJtzcAll(AppCache.getInstance().code,yearsListNull)
    }
    override fun returnBcjcUpdateJtzc(msg: String) {
        ToastUtils.showShort(msg)
        if (addJtzcUpPopu!=null){
            addJtzcUpPopu!!.dismiss()
        }
        mPresenter.getBcjcJtzc(AppCache.getInstance().code,yearsList)
        mPresenter.getBcjcJtzcAll(AppCache.getInstance().code,yearsListNull)
    }
    override fun returnWwjPoint(entity: WwjEntity) {

    }
    fun diaoyong(){
        if (supl_frag_zjdgl1!=null&&slv_frag_shzl_wwj!=null){
            supl_frag_zjdgl1?.setScrollableView(slv_frag_shzl_wwj)

            mPresenter.getBcjcYears()
            sptypeList.clear()
            sptypeList.add("上报")
            sptypeList.add("乡镇审核")
            sptypeList.add("区级确认")
            sptypeList.add("提交成功")
            tll_frag_shzl_wwj_jtzcqk.setValueText(sptypeList,-1,-1)
        }
    }

    //设置行业分布情况饼状图
    private fun setPieHyfbqk(bhgsCount: ArrayList<Float>, bhgsName: ArrayList<String>, colors: List<Int>) {
        if (pie_frag_shzl_wwj != null) {
            rkinitPie(pie_frag_shzl_wwj, "总数 336", bhgsCount, bhgsName, colors)//总数
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
        val dataSet = PieDataSet(entries, "Election Results")
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
