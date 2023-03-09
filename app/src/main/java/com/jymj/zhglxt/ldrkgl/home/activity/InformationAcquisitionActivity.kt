package com.jymj.zhglxt.ldrkgl.home.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.R.id.*
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.enums.*
import com.jymj.zhglxt.bean.pickerview.ProvinceBean
import com.jymj.zhglxt.ldrkgl.home.bean.FlowAfterEntity
import com.jymj.zhglxt.ldrkgl.home.bean.FlowCarEntity
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.login.contract.XxcjContract
import com.jymj.zhglxt.login.model.XxcjModel
import com.jymj.zhglxt.login.presenter.XxcjPresenter
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.GetJsonDataUtil
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.RegexUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_information_acquisition.*
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/*
* 信息采集activity
* */
class InformationAcquisitionActivity : BaseActivity<XxcjPresenter, XxcjModel>(), XxcjContract.View {

    var progressBarType1 = 0
    var progressBarType2 = 20
    var progressBarType3 = 0
    var progressBarType4 = 0
    var progressBarType5 = 20

    var xxcj_jbxx_tab = false
    var xxcj_jzxx_tab = false
    var xxcj_jyjsbxx_tab = false
    var xxcj_clxx_tab = false

    var xxcj_jbxx_name = false
    var xxcj_jbxx_sfzhm = false
    var xxcj_csrq_year = false
    var xxcj_csrq_mouth = false
    var xxcj_csrq_day = false
    var xxcj_zzmm = false
    var xxcj_jycd = false
    var xxcj_jbxx_phone = false
    var xxcj_nation = false
    var xxcj_hjdz_province = false
    var xxcj_hjdz_city = false
    var xxcj_hjdz_county = false
    var xxcj_jbxx_xxaddress = false
    var xxcj_hylx = false

    var xxcj_lkyjrqq_year = false
    var xxcj_lkyjrqz_year = false
    var xxcj_ljrqq_year = false
    var xxcj_ljrqz_year = false
    var xxcj_lxzzdrqq_year = false
    var xxcj_lxzzdrqz_year = false
    var xxcj_jzxx_fwdjbxh = false
    var xxcj_jzxx_jzlx = false
    var xxcj_jzxx_ljyy = false

    var xxcj_jyjsbxx_mqzk = false

    var xxcj_clxx_jdcsl = false
    var xxcj_clxx_ddcsl = false

    var timePickerView: TimePickerView? = null//时间选择器
    var optionPickerView: OptionsPickerView<Any>? = null//省市县选择器
    var nationPickerView: OptionsPickerView<Any>? = null//民族选择器
    var jycdPickerView: OptionsPickerView<Any>? = null//教育程度选择器
    var hylxPickerView: OptionsPickerView<Any>? = null//行业类型选择器
    var csrqTime: String = ""
    var lkyjrqqTime: String = ""
    var lkyjrqzTime: String = ""
    var ljrqqTime: String = ""
    var ljrqzTime: String = ""
    var lxzzzrqqTime: String = ""
    var lxzzzrqzTime: String = ""
    var timePickerViewType = "出生日期"
    var optionPickerViewType = "基本信息"
    private val detail = ArrayList<ProvinceBean>()
    private var options1Items = ArrayList<ProvinceBean>()//省
    private var options2Items = ArrayList<ArrayList<String>>()//市
    private var options3Items = ArrayList<ArrayList<ArrayList<String>>>()//县

    ////
    var isHaveIdCardType = 1//有无身份证 1有 0无
    var sexType = 1 //性别 男1 女2 其他0
    var zzmmType = 1 //政治面貌 党员1 预备党员2 团员3 民主党派4 群众5 其他0
    var hjlbType = 1 //户籍类别 农1 非农2 其他0
    var hyzkType = 1 //婚姻状况 未婚1 已婚2 丧偶3 离婚4 再婚5 未达法定年龄6 不详0
    var birthplaceType = 1 //出生地 北京1 原籍2 其他地区0
    var liveCardType = 1 //居住证件 无0 登记卡1 居住证2
    var maritalCardType = 1 //婚育证明 无0 原籍证明1 本市证明2

    var jthlrType = 1 //家庭户流入 是1 否2
    var hzType = 1 //户主 是1 否0
    var jzxxIsHsType = 1 //居住信息是否待核实 是1 否0

    private var mPopJtxxCyAdd: CommenPop? = null
    private var mPopClxxAdd: CommenPop? = null
    private var mPopIdCardTips: CommenPop? = null
    private var idsList = ArrayList<Int>()
    private var idsCarList = ArrayList<Int>()

    var flowAfterEntityAdapter: BaseQuickAdapter<FlowAfterEntity, BaseViewHolder>? = null
    var flowCarEntityAdapter: BaseQuickAdapter<FlowCarEntity, BaseViewHolder>? = null

    var idType: Long = 0
    var idResideType: Long = 0
    var idAfterType: Long = 0
    private var mPopLcTips: CommenPop? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_information_acquisition
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        initJsonData()
        getNation()
        //初始化多个选择器
        initTimePickerView()
        initOptionsPickerView()

        initView()
    }

    override fun initDatas() {

        val ldrkIdCard = intent.getStringExtra("ldrkDetailIdCard")
        val ldrkYlId = intent.getStringExtra("ldrkDetailYlId")
        val ldrkMph = intent.getStringExtra("ldrkDetailMph")
        if (ldrkIdCard != null && !ldrkIdCard.equals("")) {
            if (ldrkYlId != null && !ldrkYlId.equals("")) {
                tv_xxcj_jbxx_zjddz.text = ldrkMph
                mPresenter.getFlowGetInfo(ldrkIdCard, ldrkYlId.toLong())
            }
        }else{
            et_xxcj_jbxx_sfzhm.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (RegexUtils.isIDCard18(s.toString())) {
//                    mPresenter.getFlowGetInfo("156113126541")
                        mPresenter.getFlowGetInfo(s.toString(), AppCache.getInstance().ylId)//输入完身份证号后调用接口查询是否有之前的记录
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }


        setProgressBarData(progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5)

    }

    private fun setProgressBarData(progress: Int) {
        xxcj_progressbar.progress = progress
        tv_xxcj_progressbar.text = progress.toString() + "%"
        if (progress == 100) {
            tv_act_xxcj_upload.setTextColor(Color.parseColor("#409EFF"))
        } else {
            tv_act_xxcj_upload.setTextColor(Color.parseColor("#CCCCCC"))
        }
    }
    override fun returnLcldry(msg: String) {
        ToastUtils.showShort("流出成功")
        /*AppCache.getInstance().refreshType = 1
        AppCache.getInstance().fwRefresh = 1*/
        AppCache.getInstance().isCzxq = true
        AppCache.getInstance().isFwlb = true
        AppCache.getInstance().isCzlb = true
        AppCache.getInstance().isDtymlb = true
        finish()
    }

    //控件操作
    private fun initView() {


        if (AppMenus.getInstance().menuBeans.toString().contains("流动人口添加")){
            tv_act_xxcj_upload.visibility = View.VISIBLE
        }else{
            tv_act_xxcj_upload.visibility = View.GONE
        }
        //基本信息
        et_xxcj_jbxx_name.addTextChangedListener(listener(et_xxcj_jbxx_name))
        et_xxcj_jbxx_sfzhm.addTextChangedListener(listener(et_xxcj_jbxx_sfzhm))
        et_xxcj_csrq_year.addTextChangedListener(listener(et_xxcj_csrq_year))
        et_xxcj_csrq_mouth.addTextChangedListener(listener(et_xxcj_csrq_mouth))
        et_xxcj_csrq_day.addTextChangedListener(listener(et_xxcj_csrq_day))
        tv_xxcj_zzmm.addTextChangedListener(listener(tv_xxcj_zzmm))
        tv_xxcj_jycd.addTextChangedListener(listener(tv_xxcj_jycd))
        et_xxcj_jbxx_phone.addTextChangedListener(listener(et_xxcj_jbxx_phone))
        tv_xxcj_nation.addTextChangedListener(listener(tv_xxcj_nation))
        tv_xxcj_hjdz_province.addTextChangedListener(listener(tv_xxcj_hjdz_province))
        tv_xxcj_hjdz_city.addTextChangedListener(listener(tv_xxcj_hjdz_city))
        tv_xxcj_hjdz_county.addTextChangedListener(listener(tv_xxcj_hjdz_county))
        et_xxcj_jbxx_xxaddress.addTextChangedListener(listener(et_xxcj_jbxx_xxaddress))
        tv_xxcj_hylx.addTextChangedListener(listener(tv_xxcj_hylx))


        //居住信息
        et_xxcj_lkyjrqq_year.addTextChangedListener(listener(et_xxcj_lkyjrqq_year))
        et_xxcj_lkyjrqz_year.addTextChangedListener(listener(et_xxcj_lkyjrqz_year))
        et_xxcj_ljrqq_year.addTextChangedListener(listener(et_xxcj_ljrqq_year))
        et_xxcj_ljrqz_year.addTextChangedListener(listener(et_xxcj_ljrqz_year))
        et_xxcj_lxzzdrqq_year.addTextChangedListener(listener(et_xxcj_lxzzdrqq_year))
        et_xxcj_lxzzdrqz_year.addTextChangedListener(listener(et_xxcj_lxzzdrqz_year))
        et_xxcj_jzxx_fwdjbxh.addTextChangedListener(listener(et_xxcj_jzxx_fwdjbxh))
        tv_xxcj_jzxx_jzlx.addTextChangedListener(listener(tv_xxcj_jzxx_jzlx))
        tv_xxcj_jzxx_ljyy.addTextChangedListener(listener(tv_xxcj_jzxx_ljyy))

        //就业及社保信息
        tv_xxcj_jyjsbxx_mqzk.addTextChangedListener(listener(tv_xxcj_jyjsbxx_mqzk))

        //车辆信息
        /*tv_xxcj_clxx_jdcsl.addTextChangedListener(listener(tv_xxcj_clxx_jdcsl))
        tv_xxcj_clxx_ddcsl.addTextChangedListener(listener(tv_xxcj_clxx_ddcsl))*/

        /*tv_xxcj_jbxx_tab.addTextChangedListener(listener(tv_xxcj_jbxx_tab))
        tv_xxcj_jzxx_tab.addTextChangedListener(listener(tv_xxcj_jzxx_tab))
        tv_xxcj_jyjsbxx_tab.addTextChangedListener(listener(tv_xxcj_jyjsbxx_tab))
        tv_xxcj_clxx_tab.addTextChangedListener(listener(tv_xxcj_clxx_tab))*/


        rlv_xxcj_jtxx_add.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_xxcj_clxx_add.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val mph = intent.getStringExtra("InfirmationAcquisitionAddress")
        if (mph != null && !mph.equals("")) {
            tv_xxcj_jbxx_zjddz.text = mph
        }

        iv_act_xxcj_back.setOnClickListener {
            finish()
        }

        ll_xxcj_jbxx_title.setOnClickListener {

            if (ll_xxcj_jbxx_message.isShown) {
                ll_xxcj_jbxx_message.visibility = View.GONE
                iv_xxcj_jbxx_tab.setImageDrawable(getDrawable(R.drawable.back_right_icon))
            } else {
                ll_xxcj_jbxx_message.visibility = View.VISIBLE
                iv_xxcj_jbxx_tab.setImageDrawable(getDrawable(R.drawable.back_down_icon))
            }
        }
        ll_xxcj_jtxx_title.setOnClickListener {
            if (ll_xxcj_jtxx_message.isShown) {
                ll_xxcj_jtxx_message.visibility = View.GONE
                iv_xxcj_jtxx_tab.setImageDrawable(getDrawable(R.drawable.back_right_icon))
            } else {
                ll_xxcj_jtxx_message.visibility = View.VISIBLE
                iv_xxcj_jtxx_tab.setImageDrawable(getDrawable(R.drawable.back_down_icon))
            }
        }
        ll_xxcj_jzxx_title.setOnClickListener {
            if (ll_xxcj_jzxx_message.isShown) {
                ll_xxcj_jzxx_message.visibility = View.GONE
                iv_xxcj_jzxx_tab.setImageDrawable(getDrawable(R.drawable.back_right_icon))
            } else {
                ll_xxcj_jzxx_message.visibility = View.VISIBLE
                iv_xxcj_jzxx_tab.setImageDrawable(getDrawable(R.drawable.back_down_icon))
            }
        }
        ll_xxcj_jyjsbxx_title.setOnClickListener {
            if (ll_xxcj_jyjsbxx_message.isShown) {
                ll_xxcj_jyjsbxx_message.visibility = View.GONE
                iv_xxcj_jyjsbxx_tab.setImageDrawable(getDrawable(R.drawable.back_right_icon))
            } else {
                ll_xxcj_jyjsbxx_message.visibility = View.VISIBLE
                iv_xxcj_jyjsbxx_tab.setImageDrawable(getDrawable(R.drawable.back_down_icon))
            }
        }
        ll_xxcj_clxx_title.setOnClickListener {
            if (ll_xxcj_clxx_message.isShown) {
                ll_xxcj_clxx_message.visibility = View.GONE
                iv_xxcj_clxx_tab.setImageDrawable(getDrawable(R.drawable.back_right_icon))
            } else {
                ll_xxcj_clxx_message.visibility = View.VISIBLE
                iv_xxcj_clxx_tab.setImageDrawable(getDrawable(R.drawable.back_down_icon))
            }
        }
        //基本信息出生日期选择
        ll_xxcj_csrq.setOnClickListener {
            timePickerViewType = "出生日期"
            initTimePickerView()
            timePickerView!!.show()
        }
        //基本信息政治面貌选择
        ll_xxcj_zzmm.setOnClickListener {
            initNationPickerView("政治面貌")
        }
        //基本信息教育程度选择
        ll_xxcj_jycd.setOnClickListener {
            initNationPickerView("教育程度")
        }
        //基本信息省市区选择
        ll_xxcj_hjdz.setOnClickListener {
            optionPickerViewType = "基本信息"
            optionPickerView!!.show()
        }
        //基本信息民族选择
        ll_xxcj_nation.setOnClickListener {
            initNationPickerView("民族选择")
        }
        //基本信息行业类型选择
        ll_xxcj_hylx.setOnClickListener {
            initNationPickerView("行业类型")
        }
        //基本信息选择有无身份证
        rgp_xxcj_jbxx_idcard.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jbxx_idcard_yes -> {//身份证 有
                        isHaveIdCardType = 1
                    }
                    R.id.rbt_xxcj_jbxx_idcard_no -> {//身份证 无
                        isHaveIdCardType = 0
                    }

                }
            }
        })
        //基本信息选择性别
        rgp_xxcj_jbxx_sex.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jbxx_sex_man -> {//性别 男
                        sexType = 0
                    }
                    R.id.rbt_xxcj_jbxx_sex_woman -> {//性别 女
                        sexType = 1
                    }

                }
            }
        })

        //基本信息选择户籍类别
        rgp_xxcj_jbxx_hjlb.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jbxx_hjlb_whk -> {//无
                        hjlbType = 0
                    }
                    R.id.rbt_xxcj_jbxx_hjlb_n -> {//农
                        hjlbType = 1
                    }
                    R.id.rbt_xxcj_jbxx_hjlb_fn -> {//非农
                        hjlbType = 2
                    }

                }
            }
        })
        //基本信息选择婚姻状况
        rgp_xxcj_jbxx_hyzk.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jbxx_hyzk_wh -> {//未婚
                        hyzkType = 0
                    }
                    R.id.rbt_xxcj_jbxx_hyzk_yh -> {//已婚
                        hyzkType = 1
                    }
                    R.id.rbt_xxcj_jbxx_hyzk_lh -> {//离婚
                        hyzkType = 3
                    }
                    R.id.rbt_xxcj_jbxx_hyzk_so -> {//丧偶
                        hyzkType = 2
                    }
                }
            }
        })
        //基本信息选择出生地
        rgp_xxcj_jbxx_csd.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jbxx_csd_bj -> {//北京
                        birthplaceType = 1
                    }
                    R.id.rbt_xxcj_jbxx_csd_yj -> {//原籍
                        birthplaceType = 2
                    }
                    R.id.rbt_xxcj_jbxx_csd_qt -> {//其他地区
                        birthplaceType = 0
                    }
                }
            }
        })
        //基本信息选择居住证件
        rgp_xxcj_jbxx_jzzj.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jbxx_jzzj_wu -> {//无
                        liveCardType = 0
                    }
                    R.id.rbt_xxcj_jbxx_jzzj_djk -> {//登记卡
                        liveCardType = 1
                    }
                    R.id.rbt_xxcj_jbxx_jzzj_jzz -> {//居住证
                        liveCardType = 2
                    }
                }
            }
        })
        //基本信息选择婚育证明
        rgp_xxcj_jbxx_hyzm.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jbxx_hyzm_wu -> {//无
                        maritalCardType = 0
                    }
                    R.id.rbt_xxcj_jbxx_hyzm_yjzm -> {//原籍证明
                        maritalCardType = 1
                    }
                    R.id.rbt_xxcj_jbxx_hyzm_bszm -> {//本市证明
                        maritalCardType = 2
                    }
                }
            }
        })

        //家庭信息选择家庭户流入
        rgp_xxcj_jtxx_jthlr.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jtxx_jthlr_yes -> {
                        jthlrType = 1
                    }
                    R.id.rbt_xxcj_jtxx_jthlr_no -> {
                        jthlrType = 0
                    }
                }
            }
        })
        //家庭信息选择户主
        rgp_xxcj_jtxx_hz.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jtxx_hz_yes -> {
                        hzType = 1
                    }
                    R.id.rbt_xxcj_jtxx_hz_no -> {
                        hzType = 0
                    }
                }
            }
        })
        //家庭信息添加其他人员信息
        ll_xxcj_jtxx_add.setOnClickListener {
            initPopJtxxCyAdd(-1,FlowAfterEntity())
        }
        //车辆信息添加
        ll_xxcj_clxx_add.setOnClickListener {
            initPopClxxAdd(-1,FlowCarEntity())
        }


        //居住信息是否待核实
        rgp_xxcj_jzxx_isdhs.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_xxcj_jzxx_isdhs_yes -> {
                        jzxxIsHsType = 1
                    }
                    R.id.rbt_xxcj_jzxx_isdhs_no -> {
                        jzxxIsHsType = 0
                    }
                }
            }
        })

        //居住信息选择离开原籍日期起
        ll_xxcj_jzxx_lkyjrqq.setOnClickListener {
            timePickerViewType = "离开原籍日期起"
            initTimePickerView()
            timePickerView!!.show()
        }
        //居住信息选择离开原籍日期止
        ll_xxcj_jzxx_lkyjrqz.setOnClickListener {
            timePickerViewType = "离开原籍日期止"
            initTimePickerView()
            timePickerView!!.show()
        }
        //居住信息选择来京日期起
        ll_xxcj_jzxx_ljrqq.setOnClickListener {
            timePickerViewType = "来京日期起"
            initTimePickerView()
            timePickerView!!.show()
        }
        //居住信息选择来京日期止
        ll_xxcj_jzxx_ljrqz.setOnClickListener {
            timePickerViewType = "来京日期止"
            initTimePickerView()
            timePickerView!!.show()
        }
        //居住信息选择来现在住地日期起
        ll_xxcj_jzxx_lxzzdrqq.setOnClickListener {
            timePickerViewType = "来现在住地日期起"
            initTimePickerView()
            timePickerView!!.show()
        }
        //居住信息选择来现在住地日期止
        ll_xxcj_jzxx_lxzzdrqz.setOnClickListener {
            timePickerViewType = "来现在住地日期止"
            initTimePickerView()
            timePickerView!!.show()
        }
        //居住信息居住类型选择器
        ll_xxcj_jzxx_jzlx.setOnClickListener {
            initNationPickerView("居住类型")
        }
        //居住信息来京原因选择器
        ll_xxcj_jzxx_ljyy.setOnClickListener {
            initNationPickerView("来京原因")
        }
        //就业及社保信息目前状况选择器
        ll_xxcj_jyjsbxx_mqzk.setOnClickListener {
            initNationPickerView("目前状况")
        }


        //最终提交
        tv_act_xxcj_upload.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                if (tv_act_xxcj_upload.text.toString().equals("修改")){
                    tv_act_xxcj_upload.text = "提交"
                    view_xxcj_jbxx_message.visibility = View.GONE
                    view_xxcj_jtxx_message.visibility = View.GONE
                    view_xxcj_jzxx_message.visibility = View.GONE
                    view_xxcj_jyjsbxx_message.visibility = View.GONE
                    view_xxcj_clxx_message.visibility = View.GONE
                }else{
                    if (progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5 == 100) {
                        uoloadXxcj()
                    }else if (!RegexUtils.isIDCard18(et_xxcj_jbxx_sfzhm.text.toString())) {
                        ToastUtils.showShort("请输入正确的身份证号码")
                    } else if (!RegexUtils.isMobileExact(et_xxcj_jbxx_phone.text.toString())){
                        ToastUtils.showShort("请输入正确的手机号码")
                    } else {
                        ToastUtils.showShort("请完善所有信息后才能提交！")
                    }
                }

            }
        }
    }

    private fun uoloadXxcj() {
        mPopIdCardTips = CommenPop.getNormalPopu(this, R.layout.pop_idcard_tips, ll_xxcj_act_top)
        val decorView = mPopIdCardTips!!.contentView
        val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
        val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
        val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
        mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
        message.text = "是否提交所填信息？"
        CommenPop.backgroundAlpha(0.5f, this)
        btNo.setOnClickListener {
            mPopIdCardTips!!.dismiss()
        }
        btYes.setOnClickListener {
            var flowInfoEntity = FlowInfoEntity()

            flowInfoEntity.name = et_xxcj_jbxx_name.text.toString()//姓名
            flowInfoEntity.haveCard = isHaveIdCardType//有无身份证
            if (RegexUtils.isIDCard18(et_xxcj_jbxx_sfzhm.text.toString())) {
                flowInfoEntity.idCard = et_xxcj_jbxx_sfzhm.text.toString()//身份证号码
            } else {
                ToastUtils.showShort("请输入正确的身份证号码")
                return@setOnClickListener
            }
            if (RegexUtils.isMobileExact(et_xxcj_jbxx_phone.text.toString())) {
                flowInfoEntity.phone = et_xxcj_jbxx_phone.text.toString()//身份证号码
            } else {
                ToastUtils.showShort("请输入正确的手机号码")
                return@setOnClickListener
            }
            flowInfoEntity.sex = sexType//性别
            //出生日期
            flowInfoEntity.birthDate = et_xxcj_csrq_year.text.toString().substring(0, et_xxcj_csrq_year.text.toString().length - 1) + "-" +
                    et_xxcj_csrq_mouth.text.toString().substring(0, et_xxcj_csrq_mouth.text.toString().length - 1) + "-" +
                    et_xxcj_csrq_day.text.toString().substring(0, et_xxcj_csrq_day.text.toString().length - 1)
            flowInfoEntity.political = ZzmmEnum.getIndex(tv_xxcj_zzmm.text.toString())//政治面貌
            flowInfoEntity.education = WhcdEnum.getIndex(tv_xxcj_jycd.text.toString())//文化程度
            flowInfoEntity.hjtype = hjlbType//户籍类别
            flowInfoEntity.marriage = hyzkType//婚姻状况
//            flowInfoEntity.phone = et_xxcj_jbxx_phone.text.toString()//联系电话
            flowInfoEntity.national = NationEnum.getIndex(tv_xxcj_nation.text.toString())//民族
            flowInfoEntity.province = tv_xxcj_hjdz_province.text.toString()//省
            flowInfoEntity.city = tv_xxcj_hjdz_city.text.toString()//市
            flowInfoEntity.county = tv_xxcj_hjdz_county.text.toString()//县
            flowInfoEntity.address = et_xxcj_jbxx_xxaddress.text.toString()//详细地址
            flowInfoEntity.birthplace = birthplaceType//出生地
            flowInfoEntity.liveCard = liveCardType//居住证件
            flowInfoEntity.maritalCard = maritalCardType//婚育证明
            flowInfoEntity.industry = IndustryEnum.getIndex(tv_xxcj_hylx.text.toString())//从事行业
            flowInfoEntity.familyInflows = jthlrType//是否家庭户流入
            flowInfoEntity.householder = hzType//是否户主
            flowInfoEntity.foreign = tv_xxcj_jtxx_bhwlrks.text.toString().substring(0, tv_xxcj_jtxx_bhwlrks.text.toString().length - 1).toInt()//外来人口数
            flowInfoEntity.menAge16 = tv_xxcj_jtxx_16manrs.text.toString().substring(0, tv_xxcj_jtxx_16manrs.text.toString().length - 1).toInt()//16以下男人数
            flowInfoEntity.womenAge16 = tv_xxcj_jtxx_16womanrs.text.toString().substring(0, tv_xxcj_jtxx_16womanrs.text.toString().length - 1).toInt()//16以下女人数
            flowInfoEntity.ids = idsList
            flowInfoEntity.carIds = idsCarList
            flowInfoEntity.flowAfterEntities = flowAfterEntityType//家庭信息其他人员
            flowInfoEntity.flowCarEntities = flowCarEntityType//车辆信息
            if (idType.toInt() != 0) {
                flowInfoEntity.id = idType
            }
            flowInfoEntity.flowResideEntity.lkyjqDate = et_xxcj_lkyjrqq_year.text.toString()/*.substring(0,et_xxcj_lkyjrqq_year.text.toString().length-1)+"-"+
            et_xxcj_lkyjrqq_mouth.text.toString().substring(0,et_xxcj_lkyjrqq_mouth.text.toString().length-1)+"-"+
            et_xxcj_lkyjrqq_day.text.toString().substring(0,et_xxcj_lkyjrqq_day.text.toString().length-1)*///离开原籍日期起
            flowInfoEntity.flowResideEntity.lkyjzDate = et_xxcj_lkyjrqz_year.text.toString()//离开原籍日期止
            flowInfoEntity.flowResideEntity.ljqDate = et_xxcj_ljrqq_year.text.toString()//来京日期起
            flowInfoEntity.flowResideEntity.ljzDate = et_xxcj_ljrqz_year.text.toString()//来京日期止
            flowInfoEntity.flowResideEntity.resideqDate = et_xxcj_lxzzdrqq_year.text.toString()//来现居住地日期起
            flowInfoEntity.flowResideEntity.residezDate = et_xxcj_lxzzdrqz_year.text.toString()//来现居住地日期止
            flowInfoEntity.flowResideEntity.djbvh = et_xxcj_jzxx_fwdjbxh.text.toString()//登记表序号
            flowInfoEntity.flowResideEntity.reside = ZslbEnum.getIndex(tv_xxcj_jzxx_jzlx.text.toString())//居住类型
            flowInfoEntity.flowResideEntity.ljcause = JzsyEnum.getIndex(tv_xxcj_jzxx_ljyy.text.toString())//来京原因
            flowInfoEntity.flowResideEntity.verify = jzxxIsHsType//是否待核实
            flowInfoEntity.flowResideEntity.workstatus = EmploymentEnum.getIndex(tv_xxcj_jyjsbxx_mqzk.text.toString())//就业状况
            /*if (tv_xxcj_clxx_jdcsl.text.toString().equals("无")){
                flowInfoEntity.flowResideEntity.motor = 0//机动车数量
            }else{
                flowInfoEntity.flowResideEntity.motor = tv_xxcj_clxx_jdcsl.text.toString().toInt()//机动车数量
            }
            if (tv_xxcj_clxx_ddcsl.text.toString().equals("无")){
                flowInfoEntity.flowResideEntity.electriccar = 0
            }else{
                flowInfoEntity.flowResideEntity.electriccar = tv_xxcj_clxx_ddcsl.text.toString().toInt()
            }*/
            flowInfoEntity.flowResideEntity.ylId = AppCache.getInstance().ylId
            flowInfoEntity.flowResideEntity.id = idResideType
            mPresenter.getFlowUpload(flowInfoEntity)

        }
    }

    //初始化时间选择器
    private fun initTimePickerView() {

        val df = SimpleDateFormat("yyyy-MM-dd")// HH:mm:ss
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate.set(1900, 1, 1)
        val endDate = Calendar.getInstance()
        if (timePickerViewType.equals("出生日期")||timePickerViewType.equals("离开原籍日期起")||timePickerViewType.equals("离开原籍日期止")
                ||timePickerViewType.equals("来京日期起")||timePickerViewType.equals("来现在住地日期起")) {
            endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH))
        }else{
            endDate.set(2100, 1, 1)
        }
        timePickerView = TimePickerBuilder(this, object : OnTimeSelectListener {
            @SuppressLint("SetTextI18n")
            override fun onTimeSelect(date: Date?, v: View?) {
                if (timePickerViewType.equals("出生日期")) {
                    csrqTime = getTime(date!!)
                    et_xxcj_csrq_year.text = csrqTime.substring(0, 4) + "年"
                    et_xxcj_csrq_mouth.text = csrqTime.substring(5, 7) + "月"
                    et_xxcj_csrq_day.text = csrqTime.substring(8, 10) + "日"
                } else if (timePickerViewType.equals("离开原籍日期起")) {
                    lkyjrqqTime = getTime(date!!)
                    et_xxcj_lkyjrqq_year.text = lkyjrqqTime.substring(0, 10) /*+ "年"
                    et_xxcj_lkyjrqq_mouth.text = lkyjrqqTime.substring(5, 7) + "月"
                    et_xxcj_lkyjrqq_day.text = lkyjrqqTime.substring(8, 10) + "日"*/
                } else if (timePickerViewType.equals("离开原籍日期止")) {
                    lkyjrqzTime = getTime(date!!)

                    if (lkyjrqqTime.compareTo(lkyjrqzTime)>0){
                        ToastUtils.showShort("截止日期必须大于等于起止日期！")
                    }else{
                        et_xxcj_lkyjrqz_year.text = lkyjrqzTime.substring(0, 10)
                    }
                } else if (timePickerViewType.equals("来京日期起")) {
                    ljrqqTime = getTime(date!!)
                    et_xxcj_ljrqq_year.text = ljrqqTime.substring(0, 10)
                } else if (timePickerViewType.equals("来京日期止")) {
                    ljrqzTime = getTime(date!!)
                    if (ljrqqTime.compareTo(ljrqzTime)>=0){
                        ToastUtils.showShort("截止日期必须大于起止日期！")
                    }else{
                        et_xxcj_ljrqz_year.text = ljrqzTime.substring(0, 10)
                    }
                } else if (timePickerViewType.equals("来现在住地日期起")) {
                    lxzzzrqqTime = getTime(date!!)
                    et_xxcj_lxzzdrqq_year.text = lxzzzrqqTime.substring(0, 10)
                } else if (timePickerViewType.equals("来现在住地日期止")) {
                    lxzzzrqzTime = getTime(date!!)
                    if (lxzzzrqqTime.compareTo(lxzzzrqzTime)>=0){
                        ToastUtils.showShort("截止日期必须大于起止日期！")
                    }else{
                        et_xxcj_lxzzdrqz_year.text = lxzzzrqzTime.substring(0, 10)
                    }
                }
            }
        })
                .setTimeSelectChangeListener(object : OnTimeSelectChangeListener {
                    override fun onTimeSelectChanged(date: Date?) {

                    }
                })
                .isDialog(true)
                .setType(booleanArrayOf(true, true, true, false, false, false))
                .setItemVisibleCount(3)
                .setLineSpacingMultiplier(2.0f)
                .setDate(selectedDate)
                .setRangDate(startDate,endDate)
                .isAlphaGradient(true)
                .build()
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = timePickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        timePickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        timePickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

    }

    //时间格式转换
    private fun getTime(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")// HH:mm:ss
        return format.format(date)
    }

    //初始化省市县选择器
    private fun initOptionsPickerView() {
        optionPickerView = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            if (optionPickerViewType.equals("基本信息")) {
                tv_xxcj_hjdz_province.text = options1Items[options1].pickerViewText
                tv_xxcj_hjdz_city.text = options2Items[options1][options2]
                tv_xxcj_hjdz_county.text = options3Items[options1][options2][options3]
            } else if (optionPickerViewType.equals("新增成员户籍地址")) {
                popProvince!!.text = options1Items[options1].pickerViewText
                popCity!!.text = options2Items[options1][options2]
                popCounty!!.text = options3Items[options1][options2][options3]
            }
        })
                .isDialog(true)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setContentTextSize(20)
                .build<Any>()
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = optionPickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        optionPickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        optionPickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        optionPickerView!!.setPicker(options1Items as List<Any>?)//一级选择器
        optionPickerView!!.setPicker(options1Items as List<Any>?,
                options2Items as List<List<Any>>?)//二级选择器
        optionPickerView!!.setPicker(options1Items as List<Any>?,
                options2Items as List<List<Any>>?,
                options3Items as List<List<List<Any>>>?)//三级选择器

    }

    //初始化省市县数据
    private fun initJsonData() {
        val jsonData = GetJsonDataUtil().getJson(this, "city.json")
        val province = parseData(jsonData)
        //获取省数据
        options1Items = province

        for (i in 0..province.size - 1) {
            val CityList = ArrayList<String>()
            val Province_AreaList = ArrayList<ArrayList<String>>()
            val city = province.get(i).children
            for (c in 0..city.size - 1) {
                val CityName = city.get(c).value
                CityList.add(CityName)//添加城市
                val City_AreaList = ArrayList<String>()//该城市的所有地区列表


                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                val county = province.get(i).children.get(c).children
                if (county == null
                        || county.size == 0) {
                    City_AreaList.add("");
                } else {
//                        City_AreaList.addAll(listOf(jsonBean.get(i).children.get(c).children.get(j).value))

                    for (j in 0..county.size - 1) {
                        City_AreaList.add(county.get(j).value)
                    }
                }
                Province_AreaList.add(City_AreaList)//添加该省所有地区数据

            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList)

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList)
        }

    }

    //省市区json数据转换
    private fun parseData(result: String): ArrayList<ProvinceBean> {
        val data = JSONArray(result)
        val gson = Gson()
        for (i in 0..data.length() - 1) {
            val entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceBean::class.java)
            detail.add(entity)
        }

        return detail
    }

    //初始化选择器
    private fun initNationPickerView(type: String) {
        nationPickerView = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            if (type.equals("民族选择")) {
                tv_xxcj_nation.text = getNation()[options1]
            } else if (type.equals("政治面貌")) {
                tv_xxcj_zzmm.text = getZzmm()[options1]
            } else if (type.equals("教育程度")) {
                tv_xxcj_jycd.text = getJycd()[options1]
            } else if (type.equals("行业类型")) {
                tv_xxcj_hylx.text = getHylx()[options1]
            } else if (type.equals("居住类型")) {
                tv_xxcj_jzxx_jzlx.text = getJzlx()[options1]
            } else if (type.equals("来京原因")) {
                tv_xxcj_jzxx_ljyy.text = getLjyy()[options1]
            } else if (type.equals("目前状况")) {
                tv_xxcj_jyjsbxx_mqzk.text = getMqzk()[options1]
            } else if (type.equals("机动车数量")) {
                tv_xxcj_clxx_jdcsl.text = getClsl()[options1]
            } else if (type.equals("电动车数量")) {
                tv_xxcj_clxx_ddcsl.text = getClsl()[options1]
            } else if (type.equals("新增成员从事行业")) {
                popTvCshy!!.text = getHylx()[options1]
            } else if (type.equals("新增成员文化程度")) {
                popTvWhcd!!.text = getJycd()[options1]
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(type)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setContentTextSize(20)
                .isDialog(true)
                .build<Any>()
        if (type.equals("民族选择")) {
            nationPickerView!!.setPicker(getNation() as List<Any>?)//一级选择器
        } else if (type.equals("政治面貌")) {
            nationPickerView!!.setPicker(getZzmm() as List<Any>?)//一级选择器
        } else if (type.equals("教育程度")) {
            nationPickerView!!.setPicker(getJycd() as List<Any>?)//一级选择器
        } else if (type.equals("行业类型")) {
            nationPickerView!!.setPicker(getHylx() as List<Any>?)//一级选择器
        } else if (type.equals("居住类型")) {
            nationPickerView!!.setPicker(getJzlx() as List<Any>?)//一级选择器
        } else if (type.equals("来京原因")) {
            nationPickerView!!.setPicker(getLjyy() as List<Any>?)//一级选择器
        } else if (type.equals("目前状况")) {
            nationPickerView!!.setPicker(getMqzk() as List<Any>?)//一级选择器
        } else if (type.equals("机动车数量")) {
            nationPickerView!!.setPicker(getClsl() as List<Any>?)//一级选择器
        } else if (type.equals("电动车数量")) {
            nationPickerView!!.setPicker(getClsl() as List<Any>?)//一级选择器
        } else if (type.equals("新增成员从事行业")) {
            nationPickerView!!.setPicker(getHylx() as List<Any>?)//一级选择器
        } else if (type.equals("新增成员文化程度")) {
            nationPickerView!!.setPicker(getJycd() as List<Any>?)//一级选择器
        }

        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = nationPickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        nationPickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        nationPickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        nationPickerView!!.show()
    }

    //初始化民族数据
    private fun getNation(): ArrayList<String> {

        val list = ArrayList<String>()

        for (i in 0..NationEnum.values().size - 1) {

            list.add(NationEnum.values().get(i).getName())
        }

        return list
    }
    //初始化政治面貌数据
    private fun getZzmm(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 0..ZzmmEnum.values().size - 1) {

            list.add(ZzmmEnum.values().get(i).getName())
        }

        return list
    }
    //初始化教育程度数据
    private fun getJycd(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 0..WhcdEnum.values().size - 1) {

            list.add(WhcdEnum.values().get(i).getName())
        }

        return list
    }

    //初始化行业类型数据
    private fun getHylx(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 0..IndustryEnum.values().size - 1) {

            list.add(IndustryEnum.values().get(i).getName())
        }

        return list
    }

    //初始化居住类型数据
    private fun getJzlx(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 0..ZslbEnum.values().size - 1) {

            list.add(ZslbEnum.values().get(i).getName())
        }

        return list
    }

    //初始化来京原因数据
    private fun getLjyy(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 0..JzsyEnum.values().size - 1) {

            list.add(JzsyEnum.values().get(i).getName())
        }

        return list
    }

    //初始化目前状况数据
    private fun getMqzk(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 0..EmploymentEnum.values().size - 1) {

            list.add(EmploymentEnum.values().get(i).getName())
        }

        return list
    }

    //初始化车辆数量数据
    private fun getClsl(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("无")
        for (i in 1..9 - 1) {
            list.add(i.toString())
        }

        return list
    }


    var popProvince: TextView? = null
    var popCity: TextView? = null
    var popCounty: TextView? = null
    var popTvCshy: TextView? = null
    var popTvWhcd: TextView? = null
    var flowAfterEntityType = ArrayList<FlowAfterEntity>()
    var flowAfterSexManType = 0
    var flowAfterSexWoManType = 0
    //家庭信息添加其他成员弹出框
    private fun initPopJtxxCyAdd(index:Int,flowAfterData:FlowAfterEntity) {
        mPopJtxxCyAdd = CommenPop.getNormalPopu(this, R.layout.pop_xxcj_jtxx_add, ll_xxcj_act_top)
        val decorView = mPopJtxxCyAdd!!.contentView
        val name = decorView.findViewById<EditText>(R.id.edt_jtxxqt_name)
        val rgpSex = decorView.findViewById<RadioGroup>(R.id.rgp_jtxxqt_sex)
        val man = decorView.findViewById<RadioButton>(R.id.rbt_jtxxqt_sex_man)
        val woman = decorView.findViewById<RadioButton>(R.id.rbt_jtxxqt_sex_woman)
        val age = decorView.findViewById<EditText>(R.id.edt_jtxxqt_age)
        val idCard = decorView.findViewById<EditText>(R.id.edt_jtxxqt_idcard)
        val llHjdz = decorView.findViewById<LinearLayout>(R.id.ll_jtxxqt_hjdz)
        popProvince = decorView.findViewById<TextView>(R.id.tv_jtxxqt_hjdz_province)
        popCity = decorView.findViewById<TextView>(R.id.tv_jtxxqt_hjdz_city)
        popCounty = decorView.findViewById<TextView>(R.id.tv_jtxxqt_hjdz_county)
        val address = decorView.findViewById<EditText>(R.id.edt_jtxxqt_address)
        val llCshy = decorView.findViewById<LinearLayout>(R.id.ll_jtxxqt_cshy)
        popTvCshy = decorView.findViewById<TextView>(R.id.tv_jtxxqt_cshy)
        val llWhcd = decorView.findViewById<LinearLayout>(R.id.ll_jtxxqt_whcd)
        popTvWhcd = decorView.findViewById<TextView>(R.id.tv_jtxxqt_whcd)
        val btYes = decorView.findViewById<TextView>(R.id.bt_jtxxqt_yes)
        val btNo = decorView.findViewById<TextView>(R.id.bt_jtxxqt_no)

        mPopJtxxCyAdd!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
        CommenPop.backgroundAlpha(0.5f, this)

        btNo.setOnClickListener {
            mPopJtxxCyAdd!!.dismiss()
        }
        var flowAfterEntity = FlowAfterEntity()
        flowAfterEntity.sex = 1

        if (index!=-1&&flowAfterData!=null){
            flowAfterEntity = flowAfterData
            name.setText(flowAfterEntity.name)
            age.setText(flowAfterEntity.age.toString())
            idCard.setText(flowAfterEntity.idCard)
            popProvince!!.text = flowAfterEntity.province
            popCity!!.text = flowAfterEntity.city
            popCounty!!.text = flowAfterEntity.county
            address.setText(flowAfterEntity.address)
            popTvCshy!!.text = IndustryEnum.getName(flowAfterEntity.industry)
            popTvWhcd!!.text = WhcdEnum.getName(flowAfterEntity.education)

            when(flowAfterEntity.sex){
                1->{
                    man.isChecked = true
                }
                2->{
                    woman.isChecked = true
                }
            }
        }

        rgpSex.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_jtxxqt_sex_man -> {
                        flowAfterEntity.sex = 1
                    }
                    R.id.rbt_jtxxqt_sex_woman -> {
                        flowAfterEntity.sex = 2
                    }
                }
            }
        })

        llHjdz.setOnClickListener {
            optionPickerViewType = "新增成员户籍地址"
            optionPickerView!!.show()
        }

        llCshy.setOnClickListener {
            initNationPickerView("新增成员从事行业")
        }
        llWhcd.setOnClickListener {
            initNationPickerView("新增成员文化程度")
        }
        btYes.setOnClickListener {
            if (name.text.toString().equals("")) {
                ToastUtils.showShort("请输入姓名！")
                return@setOnClickListener
            }
            if (age.text.toString().equals("")) {
                ToastUtils.showShort("请输入年龄！")
                return@setOnClickListener
            }
            if (idCard.text.toString().equals("")) {
                ToastUtils.showShort("请输入身份证号码！")
                return@setOnClickListener
            } else if (!RegexUtils.isIDCard18(idCard.text.toString())) {
                ToastUtils.showShort("请输入正确的身份证号码！")
                return@setOnClickListener
            }
            if (popProvince!!.text.toString().equals("省")
                    && popCity!!.text.toString().equals("市")
                    && popCounty!!.text.toString().equals("县")) {
                ToastUtils.showShort("请选择户籍地址！")
                return@setOnClickListener
            }
            if (address.text.toString().equals("")) {
                ToastUtils.showShort("请输入详细地址！")
                return@setOnClickListener
            }
            if (popTvCshy!!.text.toString().equals("请选择")) {
                ToastUtils.showShort("请选择从事行业！")
                return@setOnClickListener
            }
            if (popTvWhcd!!.text.toString().equals("请选择")) {
                ToastUtils.showShort("请选择文化程度！")
                return@setOnClickListener
            }
            flowAfterEntity.name = name.text.toString()
            flowAfterEntity.age = age.text.toString().toInt()
            flowAfterEntity.idCard = idCard.text.toString()
            flowAfterEntity.province = popProvince!!.text.toString()
            flowAfterEntity.city = popCity!!.text.toString()
            flowAfterEntity.county = popCounty!!.text.toString()
            flowAfterEntity.address = address.text.toString()
            flowAfterEntity.industry = IndustryEnum.getIndex(popTvCshy!!.text.toString())
            flowAfterEntity.education = WhcdEnum.getIndex(popTvWhcd!!.text.toString())

            if (index!=-1&&flowAfterData!=null){
                flowAfterEntityType.add(index,flowAfterEntity)
                flowAfterEntityType.removeAt(index+1)
            }else{
                flowAfterEntityType.add(flowAfterEntity)
            }
            if (flowAfterEntityAdapter != null) {
                flowAfterEntityAdapter!!.setNewData(flowAfterEntityType)
                flowAfterEntityAdapter!!.notifyDataSetChanged()
            } else {
                flowAfterEntityAdapter = object : BaseQuickAdapter<FlowAfterEntity, BaseViewHolder>(R.layout.item_xxcj_jtxxqt_list, flowAfterEntityType) {
                    override fun convert(helper: BaseViewHolder?, item: FlowAfterEntity?) {
                        helper!!.setText(R.id.item_xxcj_jcqt_name, item!!.name)
                                .setText(R.id.item_xxcj_jcqt_age, item.age.toString())
                                .setText(R.id.item_xxcj_jcqt_education, WhcdEnum.getName(item.education))
                                .setText(R.id.item_xxcj_jcqt_idcard, item.idCard)
                                .setText(R.id.item_xxcj_jcqt_industry, IndustryEnum.getName(item.industry))
                                .setText(R.id.item_xxcj_jcqt_address, item.address)
                                .setText(R.id.item_xxcj_jcqt_birthday, item.registerDate)
                        when (item.sex) {
                            1 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_jcqt_sex).setImageResource(R.drawable.sex_man_icon)
                            }
                            2 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_jcqt_sex).setImageResource(R.drawable.sex_woman_icon)
                            }
                        }
                        if (item.id != null) {
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_lc).visibility = View.VISIBLE
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_delete).visibility = View.GONE
                        } else {
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_lc).visibility = View.GONE
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_delete).visibility = View.VISIBLE
                        }

                        //删除
                        helper.getView<TextView>(R.id.item_xxcj_jcqt_delete).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否删除该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowAfterEntityType.removeAt(helper.adapterPosition)
                                flowAfterEntityAdapter!!.notifyDataSetChanged()
                                mPopIdCardTips!!.dismiss()
                                for (i in 0..flowAfterEntityType.size - 1) {
                                    if (flowAfterEntityType.get(i).age < 16) {
                                        if (flowAfterEntityType.get(i).sex == 1) {
                                            flowAfterSexManType = flowAfterSexManType + 1
                                        } else if (flowAfterEntityType.get(i).sex == 2) {
                                            flowAfterSexWoManType = flowAfterSexWoManType + 1
                                        }
                                    }
                                }
                                tv_xxcj_jtxx_bhwlrks.text = flowAfterEntityType.size.toString() + "人"
                                tv_xxcj_jtxx_16manrs.text = flowAfterSexManType.toString() + "人"
                                tv_xxcj_jtxx_16womanrs.text = flowAfterSexWoManType.toString() + "人"
                                flowAfterSexManType = 0
                                flowAfterSexWoManType = 0
                            }
                        }
                        //流出
                        helper.getView<TextView>(R.id.item_xxcj_jcqt_lc).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否流出该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowAfterEntityType.removeAt(helper.adapterPosition)
                                flowAfterEntityAdapter!!.notifyDataSetChanged()
                                idsList.add(item.id.toInt())
                                mPopIdCardTips!!.dismiss()

                                for (i in 0..flowAfterEntityType.size - 1) {
                                    if (flowAfterEntityType.get(i).age < 16) {
                                        if (flowAfterEntityType.get(i).sex == 1) {
                                            flowAfterSexManType = flowAfterSexManType + 1
                                        } else if (flowAfterEntityType.get(i).sex == 2) {
                                            flowAfterSexWoManType = flowAfterSexWoManType + 1
                                        }
                                    }
                                }
                                tv_xxcj_jtxx_bhwlrks.text = flowAfterEntityType.size.toString() + "人"
                                tv_xxcj_jtxx_16manrs.text = flowAfterSexManType.toString() + "人"
                                tv_xxcj_jtxx_16womanrs.text = flowAfterSexWoManType.toString() + "人"
                                flowAfterSexManType = 0
                                flowAfterSexWoManType = 0

                            }
                        }

                        //修改
                        helper.getView<TextView>(R.id.item_xxcj_jcqt_update).setOnClickListener {
                            initPopJtxxCyAdd(helper.adapterPosition,item)
                        }


                    }

                }

                rlv_xxcj_jtxx_add.adapter = flowAfterEntityAdapter
            }
            mPopJtxxCyAdd!!.dismiss()
            tv_xxcj_jtxx_bhwlrks.text = flowAfterEntityType.size.toString() + "人"
            for (i in 0..flowAfterEntityType.size - 1) {
                if (flowAfterEntityType.get(i).age < 16) {
                    if (flowAfterEntityType.get(i).sex == 1) {
                        flowAfterSexManType = flowAfterSexManType + 1
                    } else if (flowAfterEntityType.get(i).sex == 2) {
                        flowAfterSexWoManType = flowAfterSexWoManType + 1
                    }
                }
            }
            tv_xxcj_jtxx_16manrs.text = flowAfterSexManType.toString() + "人"
            tv_xxcj_jtxx_16womanrs.text = flowAfterSexWoManType.toString() + "人"
            flowAfterSexManType = 0
            flowAfterSexWoManType = 0
        }
    }

    var flowCarEntityType = ArrayList<FlowCarEntity>()
    var flowCarLxddcType = 0
    var flowCarLxjdcType = 0
    var flowCarLxmtcType = 0
    //车辆信息添加车辆弹出框
    private fun initPopClxxAdd(index:Int,flowCarData:FlowCarEntity) {
        mPopClxxAdd = CommenPop.getNormalPopu(this, R.layout.pop_xxcj_clxx_add, ll_xxcj_act_top)
        val decorView = mPopClxxAdd!!.contentView
        val rgp_clxxadd_lx = decorView.findViewById<RadioGroup>(R.id.rgp_clxxadd_lx)
        val rbt_clxxadd_lx_ddc = decorView.findViewById<RadioButton>(R.id.rbt_clxxadd_lx_ddc)
        val rbt_clxxadd_lx_mtc = decorView.findViewById<RadioButton>(R.id.rbt_clxxadd_lx_mtc)
        val rbt_clxxadd_lx_jdc = decorView.findViewById<RadioButton>(R.id.rbt_clxxadd_lx_jdc)
        val edt_clxxadd_pp = decorView.findViewById<EditText>(R.id.edt_clxxadd_pp)
        val edt_clxxadd_cph = decorView.findViewById<EditText>(R.id.edt_clxxadd_cph)
        val edt_clxxadd_cz = decorView.findViewById<EditText>(R.id.edt_clxxadd_cz)
        val btYes = decorView.findViewById<TextView>(R.id.bt_clxxadd_yes)
        val btNo = decorView.findViewById<TextView>(R.id.bt_clxxadd_no)

        mPopClxxAdd!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
        CommenPop.backgroundAlpha(0.5f, this)

        btNo.setOnClickListener {
            mPopClxxAdd!!.dismiss()
        }
        var flowCarEntity = FlowCarEntity()
        flowCarEntity.carType = 0

        if (index!=-1&&flowCarData!=null){
            flowCarEntity = flowCarData
            edt_clxxadd_pp.setText(flowCarEntity.brand)
            edt_clxxadd_cph.setText(flowCarEntity.carNumber)
            edt_clxxadd_cz.setText(flowCarEntity.carOwner)

            when(flowCarEntity.carType){
                0->{
                    rbt_clxxadd_lx_ddc.isChecked = true
                }
                1->{
                    rbt_clxxadd_lx_jdc.isChecked = true
                }
                2->{
                    rbt_clxxadd_lx_mtc.isChecked = true
                }
            }
        }

        rgp_clxxadd_lx.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.rbt_clxxadd_lx_ddc -> {
                        flowCarEntity.carType = 0
                    }
                    R.id.rbt_clxxadd_lx_jdc -> {
                        flowCarEntity.carType = 1
                    }
                    R.id.rbt_clxxadd_lx_mtc-> {
                        flowCarEntity.carType = 2
                    }
                }
            }
        })


        btYes.setOnClickListener {
            if (edt_clxxadd_pp.text.toString().equals("")) {
                ToastUtils.showShort("请输入品牌！")
                return@setOnClickListener
            }
            if (edt_clxxadd_cph.text.toString().equals("")) {
                ToastUtils.showShort("请输入车牌号！")
                return@setOnClickListener
            }
            if (edt_clxxadd_cz.text.toString().equals("")) {
                ToastUtils.showShort("请输入车主！")
                return@setOnClickListener
            }

            flowCarEntity.brand = edt_clxxadd_pp.text.toString()
            flowCarEntity.carNumber = edt_clxxadd_cph.text.toString()
            flowCarEntity.carOwner = edt_clxxadd_cz.text.toString()


            if (index!=-1&&flowCarData!=null){
                flowCarEntityType.add(index,flowCarData)
                flowCarEntityType.removeAt(index+1)
            }else{
                flowCarEntityType.add(flowCarEntity)
            }
            if (flowCarEntityAdapter != null) {
                flowCarEntityAdapter!!.setNewData(flowCarEntityType)
                flowCarEntityAdapter!!.notifyDataSetChanged()
            } else {
                flowCarEntityAdapter = object : BaseQuickAdapter<FlowCarEntity, BaseViewHolder>(R.layout.item_xxcj_clxxadd_list, flowCarEntityType) {
                    override fun convert(helper: BaseViewHolder?, item: FlowCarEntity?) {
                        helper!!.setText(R.id.item_xxcj_clxxadd_name, item!!.carOwner)
                                .setText(R.id.item_xxcj_clxxadd_pp, item.brand)
                                .setText(R.id.item_xxcj_clxxadd_cph, item.carNumber)
                        when (item.carType) {
                            0 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_clxxadd_lx).setImageResource(R.drawable.xxcj_ddc_icon)
                            }
                            1 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_clxxadd_lx).setImageResource(R.drawable.xxcj_jdc_icon)
                            }
                            2 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_clxxadd_lx).setImageResource(R.drawable.xxcj_mtc_icon)
                            }
                        }
                        if (item.id != null) {
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete_lc).visibility = View.VISIBLE
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete).visibility = View.GONE
                        } else {
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete_lc).visibility = View.GONE
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete).visibility = View.VISIBLE
                        }

                        //删除
                        helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否删除该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowCarEntityType.removeAt(helper.adapterPosition)
                                flowCarEntityAdapter!!.notifyDataSetChanged()
                                mPopIdCardTips!!.dismiss()

                                for (i in 0..flowCarEntityType.size - 1) {
                                    if (flowCarEntityType.get(i).carType == 0) {
                                        flowCarLxddcType = flowCarLxddcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 1) {
                                        flowCarLxjdcType = flowCarLxjdcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 2) {
                                        flowCarLxmtcType = flowCarLxmtcType + 1
                                    }
                                }
                                tv_xxcj_clxx_ddcsl.text = flowCarLxddcType.toString() + "辆"
                                tv_xxcj_clxx_jdcsl.text = flowCarLxjdcType.toString() + "辆"
                                tv_xxcj_clxx_mtcsl.text = flowCarLxmtcType.toString() + "辆"
                                flowCarLxddcType = 0
                                flowCarLxjdcType = 0
                                flowCarLxmtcType = 0
                            }
                        }
                        //流出
                        helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete_lc).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否删除该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowCarEntityType.removeAt(helper.adapterPosition)
                                flowCarEntityAdapter!!.notifyDataSetChanged()
                                idsCarList.add(item.id.toInt())
                                mPopIdCardTips!!.dismiss()

                                for (i in 0..flowCarEntityType.size - 1) {
                                    if (flowCarEntityType.get(i).carType == 0) {
                                        flowCarLxddcType = flowCarLxddcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 1) {
                                        flowCarLxjdcType = flowCarLxjdcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 2) {
                                        flowCarLxmtcType = flowCarLxmtcType + 1
                                    }
                                }
                                tv_xxcj_clxx_ddcsl.text = flowCarLxddcType.toString() + "辆"
                                tv_xxcj_clxx_jdcsl.text = flowCarLxjdcType.toString() + "辆"
                                tv_xxcj_clxx_mtcsl.text = flowCarLxmtcType.toString() + "辆"
                                flowCarLxddcType = 0
                                flowCarLxjdcType = 0
                                flowCarLxmtcType = 0
                            }
                        }
                        //修改
                        helper.getView<TextView>(R.id.item_xxcj_clxxadd_update).setOnClickListener {
                            initPopClxxAdd(helper.adapterPosition,item)
                        }

                    }

                }

                rlv_xxcj_clxx_add.adapter = flowCarEntityAdapter
            }
            mPopClxxAdd!!.dismiss()
            for (i in 0..flowCarEntityType.size - 1) {
                if (flowCarEntityType.get(i).carType == 0) {
                    flowCarLxddcType = flowCarLxddcType + 1
                }else if (flowCarEntityType.get(i).carType == 1) {
                    flowCarLxjdcType = flowCarLxjdcType + 1
                }else if (flowCarEntityType.get(i).carType == 2) {
                    flowCarLxmtcType = flowCarLxmtcType + 1
                }
            }
            tv_xxcj_clxx_ddcsl.text = flowCarLxddcType.toString() + "辆"
            tv_xxcj_clxx_jdcsl.text = flowCarLxjdcType.toString() + "辆"
            tv_xxcj_clxx_mtcsl.text = flowCarLxmtcType.toString() + "辆"
            flowCarLxddcType = 0
            flowCarLxjdcType = 0
            flowCarLxmtcType = 0
        }
    }

    //输入身份证号查询之前是否有内容
    override fun returnFlowGetInfo(entity: FlowInfoEntity) {
        flowAfterEntityType.clear()
        if (entity != null) {

            if (entity.id!=null){
                if (AppMenus.getInstance().menuBeans.toString().contains("流动人口删除")){
                    tv_act_xxcj_delete.visibility = View.VISIBLE
                }
                if (AppMenus.getInstance().menuBeans.toString().contains("流动人口修改")){
                    tv_act_xxcj_upload.text = "修改"
                    tv_act_xxcj_upload.visibility = View.VISIBLE
                    view_xxcj_jbxx_message.visibility = View.VISIBLE
                    view_xxcj_jtxx_message.visibility = View.VISIBLE
                    view_xxcj_jzxx_message.visibility = View.VISIBLE
                    view_xxcj_jyjsbxx_message.visibility = View.VISIBLE
                    view_xxcj_clxx_message.visibility = View.VISIBLE
                }else{
                    tv_act_xxcj_upload.visibility = View.GONE
                }
                tv_act_xxcj_delete.setOnClickListener {
                    mPopLcTips = CommenPop.getNormalPopu(this, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                    val decorView = mPopLcTips!!.contentView
                    val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                    val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                    val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                    mPopLcTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                    message.text = "是否流出该信息？"

                    mPopLcTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                    CommenPop.backgroundAlpha(0.5f, this)
                    btNo.setOnClickListener {
                        mPopLcTips!!.dismiss()
                    }
                    btYes.setOnClickListener {
                        mPresenter.getLcldry(listOf(entity!!.id.toInt()))
                        mPopLcTips!!.dismiss()
                    }
                }
            }
            idType = entity.id
            /*mPopIdCardTips = CommenPop.getNormalPopu(this, R.layout.pop_idcard_tips, ll_xxcj_act_top)
            val decorView = mPopIdCardTips!!.contentView
            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
            CommenPop.backgroundAlpha(0.5f, this)
            btNo.setOnClickListener {
                mPopIdCardTips!!.dismiss()
            }
            btYes.setOnClickListener {
                mPopIdCardTips!!.dismiss()*/

            et_xxcj_jbxx_name.setText(entity.name)//姓名
            val ldrkIdCard = intent.getStringExtra("ldrkDetailIdCard")
            //区分 是上页人员列表点击进来的就赋值 否则添加进来不赋值
            if (ldrkIdCard!=null&&!ldrkIdCard.equals("")){
                et_xxcj_jbxx_sfzhm.setText(entity.idCard)//身份证号
            }
            //有无身份证 有1 无0
            when (entity.haveCard) {
                1 -> {
                    rbt_xxcj_jbxx_idcard_yes.isChecked = true
                }
                0 -> {
                    rbt_xxcj_jbxx_idcard_no.isChecked = true
                }
            }
            //性别 男1 女2 其他0
            when (entity.haveCard) {
                0 -> {
                    rbt_xxcj_jbxx_sex_man.isChecked = true
                }
                1 -> {
                    rbt_xxcj_jbxx_sex_woman.isChecked = true
                }
            }
            et_xxcj_csrq_year.text = entity.birthDate.substring(0, 4) + "年"//年
            et_xxcj_csrq_mouth.text = entity.birthDate.substring(5, 7) + "月"//月
            et_xxcj_csrq_day.text = entity.birthDate.substring(8, 10) + "日"//日

            tv_xxcj_zzmm.text = ZzmmEnum.getName(entity.political) //政治面貌
            tv_xxcj_jycd.text = WhcdEnum.getName(entity.education) //教育程度
            //户籍类别 农1 非农2 其他0
            when (entity.hjtype) {
                0 -> {
                    rbt_xxcj_jbxx_hjlb_whk.isChecked = true
                }
                1 -> {
                    rbt_xxcj_jbxx_hjlb_n.isChecked = true
                }
                2 -> {
                    rbt_xxcj_jbxx_hjlb_fn.isChecked = true
                }
            }
            //婚姻状况 未婚1 已婚2 丧偶3 离婚4 再婚5 未达法定年龄6 不详0
            when (entity.marriage) {
                0 -> {
                    rbt_xxcj_jbxx_hyzk_wh.isChecked = true
                }
                1 -> {
                    rbt_xxcj_jbxx_hyzk_yh.isChecked = true
                }
                2 -> {
                    rbt_xxcj_jbxx_hyzk_so.isChecked = true
                }
                3 -> {
                    rbt_xxcj_jbxx_hyzk_lh.isChecked = true
                }
            }
            et_xxcj_jbxx_phone.setText(entity.phone)//联系电话
            tv_xxcj_nation.text = NationEnum.getName(entity.national)//民族
            tv_xxcj_hjdz_province.text = entity.province//省
            tv_xxcj_hjdz_city.text = entity.city//市
            tv_xxcj_hjdz_county.text = entity.county//县
            et_xxcj_jbxx_xxaddress.setText(entity.address)//详细地址
            //出生地 北京1 原籍2 其他地区0
            when (entity.birthplace) {
                1 -> {
                    rbt_xxcj_jbxx_csd_bj.isChecked = true
                }
                2 -> {
                    rbt_xxcj_jbxx_csd_yj.isChecked = true
                }
                0 -> {
                    rbt_xxcj_jbxx_csd_qt.isChecked = true
                }
            }
            //居住证件 无0 登记卡1 居住证2
            when (entity.liveCard) {
                0 -> {
                    rbt_xxcj_jbxx_jzzj_wu.isChecked = true
                }
                1 -> {
                    rbt_xxcj_jbxx_jzzj_djk.isChecked = true
                }
                2 -> {
                    rbt_xxcj_jbxx_jzzj_jzz.isChecked = true
                }
            }
            //婚育证明 无0 原籍证明1 本市证明2
            when (entity.maritalCard) {
                0 -> {
                    rbt_xxcj_jbxx_hyzm_wu.isChecked = true
                }
                1 -> {
                    rbt_xxcj_jbxx_hyzm_yjzm.isChecked = true
                }
                2 -> {
                    rbt_xxcj_jbxx_hyzm_bszm.isChecked = true
                }
            }
            tv_xxcj_hylx.text = IndustryEnum.getName(entity.industry)//行业类型

            //家庭户流入 是1 否2
            when (entity.familyInflows) {
                1 -> {
                    rbt_xxcj_jtxx_jthlr_yes.isChecked = true
                }
                2 -> {
                    rbt_xxcj_jtxx_jthlr_no.isChecked = true
                }
            }
            //户主 是1 否0
            when (entity.householder) {
                1 -> {
                    rbt_xxcj_jtxx_hz_yes.isChecked = true
                }
                0 -> {
                    rbt_xxcj_jtxx_hz_no.isChecked = true
                }
            }
            if (entity.flowResideEntities != null && entity.flowResideEntities.size > 0) {

                val flowResideEntity = entity.flowResideEntities.get(0)
                idResideType = flowResideEntity.id

                //离开原籍日期起
                et_xxcj_lkyjrqq_year.text = flowResideEntity.lkyjqDate.substring(0, 10)
                lkyjrqqTime = flowResideEntity.lkyjqDate.substring(0, 10)
                /*+ "年"
                    et_xxcj_lkyjrqq_mouth.text = flowResideEntity.lkyjqDate.substring(5, 7) + "月"
                    et_xxcj_lkyjrqq_day.text = flowResideEntity.lkyjqDate.substring(8, 10) + "日"*/
                //离开原籍日期止
                et_xxcj_lkyjrqz_year.text = flowResideEntity.lkyjzDate.substring(0, 10)
                lkyjrqzTime = flowResideEntity.lkyjzDate.substring(0, 10)
                //来京日期起
                et_xxcj_ljrqq_year.text = flowResideEntity.ljqDate.substring(0, 10)
                ljrqqTime = flowResideEntity.ljqDate.substring(0, 10)
                //来京日期止
                et_xxcj_ljrqz_year.text = flowResideEntity.ljzDate.substring(0, 10)
                ljrqzTime = flowResideEntity.ljzDate.substring(0, 10)
                //来现居住地日期起
                et_xxcj_lxzzdrqq_year.text = flowResideEntity.resideqDate.substring(0, 10)
                lxzzzrqqTime = flowResideEntity.resideqDate.substring(0, 10)
                //来现居住地日期止
                et_xxcj_lxzzdrqz_year.text = flowResideEntity.residezDate.substring(0, 10)
                lxzzzrqzTime = flowResideEntity.residezDate.substring(0, 10)
                et_xxcj_jzxx_fwdjbxh.setText(flowResideEntity.djbvh)//房屋登记表序号
                tv_xxcj_jzxx_jzlx.text = ZslbEnum.getName(flowResideEntity.reside) //居住类型
                tv_xxcj_jzxx_ljyy.text = JzsyEnum.getName(flowResideEntity.ljcause)//来京原因
                //居住信息是否待核实 是1 否0
                when (flowResideEntity.verify) {
                    1 -> {
                        rbt_xxcj_jzxx_isdhs_yes.isChecked = true
                    }
                    0 -> {
                        rbt_xxcj_jzxx_isdhs_no.isChecked = true
                    }
                }
                tv_xxcj_jyjsbxx_mqzk.text = EmploymentEnum.getName(flowResideEntity.workstatus)//目前就业状况
                /*if (flowResideEntity.motor.toString().equals("0")){
                    tv_xxcj_clxx_jdcsl.text = "无"//机动车数量
                }else{
                    tv_xxcj_clxx_jdcsl.text = flowResideEntity.motor.toString()//机动车数量
                }
                if (flowResideEntity.electriccar.toString().equals("0")){
                    tv_xxcj_clxx_ddcsl.text = "无"//机动车数量
                }else{
                    tv_xxcj_clxx_ddcsl.text = flowResideEntity.electriccar.toString()//电动车数量
                }*/


            }
            if (entity.flowAfterEntities != null && entity.flowAfterEntities.size > 0) {
                flowAfterEntityType.addAll(entity.flowAfterEntities)
                tv_xxcj_jtxx_bhwlrks.text = flowAfterEntityType.size.toString() + "人"
                for (i in 0..flowAfterEntityType.size - 1) {
                    if (flowAfterEntityType.get(i).age < 16) {
                        if (flowAfterEntityType.get(i).sex == 1) {
                            flowAfterSexManType = flowAfterSexManType + 1
                        } else if (flowAfterEntityType.get(i).sex == 2) {
                            flowAfterSexWoManType = flowAfterSexWoManType + 1
                        }
                    }
                }
                tv_xxcj_jtxx_16manrs.text = flowAfterSexManType.toString() + "人"
                tv_xxcj_jtxx_16womanrs.text = flowAfterSexWoManType.toString() + "人"
                flowAfterSexManType = 0
                flowAfterSexWoManType = 0

                flowAfterEntityAdapter = object : BaseQuickAdapter<FlowAfterEntity, BaseViewHolder>(R.layout.item_xxcj_jtxxqt_list, flowAfterEntityType) {
                    override fun convert(helper: BaseViewHolder?, item: FlowAfterEntity?) {
                        helper!!.setText(R.id.item_xxcj_jcqt_name, item!!.name)
                                .setText(R.id.item_xxcj_jcqt_age, item.age.toString())
                                .setText(R.id.item_xxcj_jcqt_education, WhcdEnum.getName(item.education))
                                .setText(R.id.item_xxcj_jcqt_idcard, item.idCard)
                                .setText(R.id.item_xxcj_jcqt_industry, IndustryEnum.getName(item.industry))
                                .setText(R.id.item_xxcj_jcqt_address, item.address)
                                .setText(R.id.item_xxcj_jcqt_birthday, item.registerDate)
                        when (item.sex) {
                            1 -> {
                                helper.getView<ImageView>(item_xxcj_jcqt_sex).setImageResource(R.drawable.sex_man_icon)
                            }
                            2 -> {
                                helper.getView<ImageView>(item_xxcj_jcqt_sex).setImageResource(R.drawable.sex_woman_icon)
                            }
                        }
                        if (item.id != null) {
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_lc).visibility = View.VISIBLE
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_delete).visibility = View.GONE
                        } else {
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_lc).visibility = View.GONE
                            helper.getView<TextView>(R.id.item_xxcj_jcqt_delete).visibility = View.VISIBLE
                        }
                        //删除
                        helper.getView<TextView>(R.id.item_xxcj_jcqt_delete).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否删除该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowAfterEntityType.removeAt(helper.adapterPosition)
                                flowAfterEntityAdapter!!.notifyDataSetChanged()
                                mPopIdCardTips!!.dismiss()

                                for (i in 0..flowAfterEntityType.size - 1) {
                                    if (flowAfterEntityType.get(i).age < 16) {
                                        if (flowAfterEntityType.get(i).sex == 1) {
                                            flowAfterSexManType = flowAfterSexManType + 1
                                        } else if (flowAfterEntityType.get(i).sex == 2) {
                                            flowAfterSexWoManType = flowAfterSexWoManType + 1
                                        }
                                    }
                                }
                                tv_xxcj_jtxx_bhwlrks.text = flowAfterEntityType.size.toString() + "人"
                                tv_xxcj_jtxx_16manrs.text = flowAfterSexManType.toString() + "人"
                                tv_xxcj_jtxx_16womanrs.text = flowAfterSexWoManType.toString() + "人"
                                flowAfterSexManType = 0
                                flowAfterSexWoManType = 0

                            }
                        }
                        //流出
                        helper.getView<TextView>(R.id.item_xxcj_jcqt_lc).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否流出该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowAfterEntityType.removeAt(helper.adapterPosition)
                                flowAfterEntityAdapter!!.notifyDataSetChanged()
                                idsList.add(item.id.toInt())
                                mPopIdCardTips!!.dismiss()

                                for (i in 0..flowAfterEntityType.size - 1) {
                                    if (flowAfterEntityType.get(i).age < 16) {
                                        if (flowAfterEntityType.get(i).sex == 1) {
                                            flowAfterSexManType = flowAfterSexManType + 1
                                        } else if (flowAfterEntityType.get(i).sex == 2) {
                                            flowAfterSexWoManType = flowAfterSexWoManType + 1
                                        }
                                    }
                                }
                                tv_xxcj_jtxx_bhwlrks.text = flowAfterEntityType.size.toString() + "人"
                                tv_xxcj_jtxx_16manrs.text = flowAfterSexManType.toString() + "人"
                                tv_xxcj_jtxx_16womanrs.text = flowAfterSexWoManType.toString() + "人"
                                flowAfterSexManType = 0
                                flowAfterSexWoManType = 0
                            }
                        }
                        //修改
                        helper.getView<TextView>(R.id.item_xxcj_jcqt_update).setOnClickListener {
                            initPopJtxxCyAdd(helper.adapterPosition,item)
                        }
                    }

                }

                rlv_xxcj_jtxx_add.adapter = flowAfterEntityAdapter
            }
            if (entity.flowCarEntities != null && entity.flowCarEntities.size > 0){
                flowCarEntityType.addAll(entity.flowCarEntities)
                for (i in 0..flowCarEntityType.size - 1) {
                    if (flowCarEntityType.get(i).carType == 0) {
                        flowCarLxddcType = flowCarLxddcType + 1
                    }else if (flowCarEntityType.get(i).carType == 1) {
                        flowCarLxjdcType = flowCarLxjdcType + 1
                    }else if (flowCarEntityType.get(i).carType == 2) {
                        flowCarLxmtcType = flowCarLxmtcType + 1
                    }
                }
                tv_xxcj_clxx_ddcsl.text = flowCarLxddcType.toString() + "辆"
                tv_xxcj_clxx_jdcsl.text = flowCarLxjdcType.toString() + "辆"
                tv_xxcj_clxx_mtcsl.text = flowCarLxmtcType.toString() + "辆"
                flowCarLxddcType = 0
                flowCarLxjdcType = 0
                flowCarLxmtcType = 0

                flowCarEntityAdapter = object : BaseQuickAdapter<FlowCarEntity, BaseViewHolder>(R.layout.item_xxcj_clxxadd_list, flowCarEntityType) {
                    override fun convert(helper: BaseViewHolder?, item: FlowCarEntity?) {
                        helper!!.setText(R.id.item_xxcj_clxxadd_name, item!!.carOwner)
                                .setText(R.id.item_xxcj_clxxadd_pp, item.brand)
                                .setText(R.id.item_xxcj_clxxadd_cph, item.carNumber)
                        when (item.carType) {
                            0 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_clxxadd_lx).setImageResource(R.drawable.xxcj_ddc_icon)
                            }
                            1 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_clxxadd_lx).setImageResource(R.drawable.xxcj_jdc_icon)
                            }
                            2 -> {
                                helper.getView<ImageView>(R.id.item_xxcj_clxxadd_lx).setImageResource(R.drawable.xxcj_mtc_icon)
                            }
                        }
                        if (item.id != null) {
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete_lc).visibility = View.VISIBLE
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete).visibility = View.GONE
                        } else {
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete_lc).visibility = View.GONE
                            helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete).visibility = View.VISIBLE
                        }

                        //删除
                        helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否删除该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowCarEntityType.removeAt(helper.adapterPosition)
                                flowCarEntityAdapter!!.notifyDataSetChanged()
                                mPopIdCardTips!!.dismiss()

                                for (i in 0..flowCarEntityType.size - 1) {
                                    if (flowCarEntityType.get(i).carType == 0) {
                                        flowCarLxddcType = flowCarLxddcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 1) {
                                        flowCarLxjdcType = flowCarLxjdcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 2) {
                                        flowCarLxmtcType = flowCarLxmtcType + 1
                                    }
                                }
                                tv_xxcj_clxx_ddcsl.text = flowCarLxddcType.toString() + "辆"
                                tv_xxcj_clxx_jdcsl.text = flowCarLxjdcType.toString() + "辆"
                                tv_xxcj_clxx_mtcsl.text = flowCarLxmtcType.toString() + "辆"
                                flowCarLxddcType = 0
                                flowCarLxjdcType = 0
                                flowCarLxmtcType = 0
                            }
                        }
                        //流出
                        helper.getView<TextView>(R.id.item_xxcj_clxxadd_delete_lc).setOnClickListener {
                            mPopIdCardTips = CommenPop.getNormalPopu(this@InformationAcquisitionActivity, R.layout.pop_idcard_tips, ll_xxcj_act_top)
                            val decorView = mPopIdCardTips!!.contentView
                            val message = decorView.findViewById<TextView>(R.id.bt_idcard_tips_message)
                            val btYes = decorView.findViewById<TextView>(R.id.bt_idcard_tips_yes)
                            val btNo = decorView.findViewById<TextView>(R.id.bt_idcard_tips_no)
                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            message.text = "是否删除该信息？"

                            mPopIdCardTips!!.showAtLocation(decorView, Gravity.CENTER, 0, 0)
                            CommenPop.backgroundAlpha(0.5f, this@InformationAcquisitionActivity)
                            btNo.setOnClickListener {
                                mPopIdCardTips!!.dismiss()
                            }
                            btYes.setOnClickListener {
                                flowCarEntityType.removeAt(helper.adapterPosition)
                                flowCarEntityAdapter!!.notifyDataSetChanged()
                                idsCarList.add(item.id.toInt())
                                mPopIdCardTips!!.dismiss()

                                for (i in 0..flowCarEntityType.size - 1) {
                                    if (flowCarEntityType.get(i).carType == 0) {
                                        flowCarLxddcType = flowCarLxddcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 1) {
                                        flowCarLxjdcType = flowCarLxjdcType + 1
                                    }else if (flowCarEntityType.get(i).carType == 2) {
                                        flowCarLxmtcType = flowCarLxmtcType + 1
                                    }
                                }
                                tv_xxcj_clxx_ddcsl.text = flowCarLxddcType.toString() + "辆"
                                tv_xxcj_clxx_jdcsl.text = flowCarLxjdcType.toString() + "辆"
                                tv_xxcj_clxx_mtcsl.text = flowCarLxmtcType.toString() + "辆"
                                flowCarLxddcType = 0
                                flowCarLxjdcType = 0
                                flowCarLxmtcType = 0
                            }
                        }

                        //修改
                        helper.getView<TextView>(R.id.item_xxcj_clxxadd_update).setOnClickListener {
                            initPopClxxAdd(helper.adapterPosition,item)
                        }


                    }

                }

                rlv_xxcj_clxx_add.adapter = flowCarEntityAdapter

            }

//            }

        }else{
            ToastUtils.showShort("暂无录入")
        }

    }

    override fun returnFlowUpload(entity: String) {
        ToastUtils.showShort("提交成功")
        if (mPopIdCardTips != null) {
            mPopIdCardTips!!.dismiss()
        }
        /*AppCache.getInstance().refreshType = 1
        AppCache.getInstance().fwRefresh = 1*/
        AppCache.getInstance().isCzxq = true
        AppCache.getInstance().isFwlb = true
        AppCache.getInstance().isCzlb = true
        AppCache.getInstance().isDtymlb = true
        finish()
    }

    //监听所有信息选项的状态
    private fun listener(view: View): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when (view) {
                    et_xxcj_jbxx_name -> {//基本信息姓名
                        xxcj_jbxx_name = s!!.isNotEmpty()
                    }
                    et_xxcj_jbxx_sfzhm -> {//基本信息身份证号
                        if (RegexUtils.isIDCard18(s)){
                            etil_xxcj_jbxx_sfzhm.isErrorEnabled = false
                            xxcj_jbxx_sfzhm = true
                        }else{
                            etil_xxcj_jbxx_sfzhm.error = "请输入正确的身份证号码"
                            xxcj_jbxx_sfzhm = false
                        }
                    }
                    et_xxcj_csrq_year -> {
                        xxcj_csrq_year = s.toString() != "年"
                    }
                    et_xxcj_csrq_mouth -> {
                        xxcj_csrq_mouth = s.toString() != "月"
                    }
                    et_xxcj_csrq_day -> {
                        xxcj_csrq_day = s.toString() != "日"
                    }
                    tv_xxcj_zzmm -> {//政治面貌
                        xxcj_zzmm = s.toString() != "请选择"
                    }
                    tv_xxcj_jycd -> {//教育程度
                        xxcj_jycd = s.toString() != "请选择"
                    }
                    et_xxcj_jbxx_phone -> {//电话号码
                        if (RegexUtils.isMobileExact(s)){
                            etil_xxcj_jbxx_phone.isErrorEnabled = false
                            xxcj_jbxx_phone = true
                        }else{
                            etil_xxcj_jbxx_phone.error = "请输入正确的手机号码"
                            xxcj_jbxx_phone = false
                        }

                    }
                    tv_xxcj_nation -> {
                        xxcj_nation = s.toString() != "请选择"
                    }
                    tv_xxcj_hjdz_province -> {
                        xxcj_hjdz_province = s.toString() != "省"
                    }
                    tv_xxcj_hjdz_city -> {
                        xxcj_hjdz_city = s.toString() != "市"
                    }
                    tv_xxcj_hjdz_county -> {
                        xxcj_hjdz_county = s.toString() != "县"
                    }
                    et_xxcj_jbxx_xxaddress -> {
                        xxcj_jbxx_xxaddress = s!!.isNotEmpty()
                    }
                    tv_xxcj_hylx -> {//基本信息行业类型
                        xxcj_hylx = s.toString() != "请选择"
                    }

                    ////////////////////居住信息/////////////////////
                    et_xxcj_lkyjrqq_year -> {
                        xxcj_lkyjrqq_year = s.toString() != "请选择"
                    }
                    et_xxcj_lkyjrqz_year -> {
                        xxcj_lkyjrqz_year = s.toString() != "请选择"
                    }
                    et_xxcj_ljrqq_year -> {
                        xxcj_ljrqq_year = s.toString() != "请选择"
                    }
                    et_xxcj_ljrqz_year -> {
                        xxcj_ljrqz_year = s.toString() != "请选择"
                    }
                    et_xxcj_lxzzdrqq_year -> {
                        xxcj_lxzzdrqq_year = s.toString() != "请选择"
                    }
                    et_xxcj_lxzzdrqz_year -> {
                        xxcj_lxzzdrqz_year = s.toString() != "请选择"
                    }
                    et_xxcj_jzxx_fwdjbxh -> {
                        xxcj_jzxx_fwdjbxh = s!!.isNotEmpty()
                    }
                    tv_xxcj_jzxx_jzlx -> {
                        xxcj_jzxx_jzlx = s.toString() != "请选择"
                    }
                    tv_xxcj_jzxx_ljyy -> {
                        xxcj_jzxx_ljyy = s.toString() != "请选择"
                    }
                    //////////////////就业及社保信息//////////////////
                    tv_xxcj_jyjsbxx_mqzk -> {
                        xxcj_jyjsbxx_mqzk = s.toString() != "请选择"
                    }
                    ///////////////////车辆信息////////////////////
                    /*tv_xxcj_clxx_jdcsl -> {
                        xxcj_clxx_jdcsl = s.toString() != "无"
                    }
                    tv_xxcj_clxx_ddcsl -> {
                        xxcj_clxx_ddcsl = s.toString() != "无"
                    }*/
                    //待完善
                    tv_xxcj_jbxx_tab -> {
                        xxcj_jbxx_tab = s.toString() != "待完善"
                    }
                    tv_xxcj_jzxx_tab -> {
                        xxcj_jzxx_tab = s.toString() != "待完善"
                    }
                    tv_xxcj_jyjsbxx_tab -> {
                        xxcj_jyjsbxx_tab = s.toString() != "待完善"
                    }
                    tv_xxcj_clxx_tab -> {
                        xxcj_clxx_tab = s.toString() != "待完善"
                    }
                }
                if (xxcj_jbxx_name && xxcj_jbxx_sfzhm && xxcj_csrq_year && xxcj_csrq_mouth && xxcj_csrq_day && xxcj_jycd&&xxcj_zzmm
                        && xxcj_jbxx_phone && xxcj_nation && xxcj_hjdz_province && xxcj_hjdz_city && xxcj_hjdz_county
                        && xxcj_jbxx_xxaddress && xxcj_hylx) {
                    tv_xxcj_jbxx_tab.text = "已完善"
                    tv_xxcj_jbxx_tab.setTextColor(Color.parseColor("#409EFF"))
                    progressBarType1 = 20
                    setProgressBarData(progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5)
                } else {
                    tv_xxcj_jbxx_tab.text = "待完善"
                    tv_xxcj_jbxx_tab.setTextColor(Color.parseColor("#999999"))
                    progressBarType1 = 0
                    setProgressBarData(progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5)
                }
                if (xxcj_lkyjrqq_year && xxcj_lkyjrqz_year && xxcj_ljrqq_year && xxcj_ljrqz_year && xxcj_lxzzdrqq_year
                        && xxcj_lxzzdrqz_year && xxcj_jzxx_fwdjbxh && xxcj_jzxx_jzlx && xxcj_jzxx_ljyy) {
                    tv_xxcj_jzxx_tab.text = "已完善"
                    tv_xxcj_jzxx_tab.setTextColor(Color.parseColor("#409EFF"))
                    progressBarType3 = 20
                    setProgressBarData(progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5)
                } else {
                    tv_xxcj_jzxx_tab.text = "待完善"
                    tv_xxcj_jzxx_tab.setTextColor(Color.parseColor("#999999"))
                    progressBarType3 = 0
                    setProgressBarData(progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5)
                }
                if (xxcj_jyjsbxx_mqzk) {
                    tv_xxcj_jyjsbxx_tab.text = "已完善"
                    tv_xxcj_jyjsbxx_tab.setTextColor(Color.parseColor("#409EFF"))
                    progressBarType4 = 20
                    setProgressBarData(progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5)
                }
                /*if (xxcj_clxx_jdcsl && xxcj_clxx_ddcsl) {
                    tv_xxcj_clxx_tab.text = "已完善"
                    tv_xxcj_clxx_tab.setTextColor(Color.parseColor("#409EFF"))
                    progressBarType5 = 20
                    setProgressBarData(progressBarType1 + progressBarType2 + progressBarType3 + progressBarType4 + progressBarType5)
                }*/


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
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

}
