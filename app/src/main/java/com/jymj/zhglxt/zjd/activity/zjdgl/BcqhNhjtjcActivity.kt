package com.jymj.zhglxt.zjd.activity.zjdgl

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
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
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.enums.ZzmmEnum
import com.jymj.zhglxt.util.CbbsjsxXzqPop
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.TdlyUtils
import com.jymj.zhglxt.widget.FlowRadioGroup
import com.jymj.zhglxt.zjd.bean.QyfwBean
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtzcEntity
import com.jymj.zhglxt.zjd.bean.bcjc.BcrejectedEntity
import com.jymj.zhglxt.zjd.bean.bcjc.bcqh.*
import com.jymj.zhglxt.zjd.contract.BcqhContract
import com.jymj.zhglxt.zjd.presenter.BcqhPresenter
import com.mcxtzhang.swipemenulib.SwipeMenuLayout
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.RegexUtils
import com.setsuna.common.commonutils.ToastUtils
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration
import kotlinx.android.synthetic.main.activity_bcqh_nhjtjc.*
import kotlinx.android.synthetic.main.activity_cbbsjsx.*

class BcqhNhjtjcActivity : BaseActivity<BcqhPresenter, BaseModel>(), BcqhContract.View {

    private var addJtcyUpPopu: CommenPop? = null
    private var addJtTdjyJCcqkUpPopu: CommenPop? = null
    private var addJtSrzcqkUpPopu: CommenPop? = null
    private var addJtShbzyCzxqqkUpPopu: CommenPop? = null

    var jtcyjcEntity = JtcyjcEntity()
    var jtcyjcEntity1 = JtcyjcEntity()
    var jtcyjbqkEntity = ArrayList<JtcyjbqkEntity>()
    var jtjyccqk = Jtjyccqk()
    var jtsrzcqkEntity = JtsrzcqkEntity()
    var jtshbzqkEntity = JtshbzqkEntity()
    var bcqhCodeFlag = ""
    var sortingFlag = 0
    var yearFlag = ""
    var idFlag = 0L
    var bcqhType = 0  //领导1 查询2 添加3
    var ncpAddAdapter: BaseQuickAdapter<JtncpEntity, BaseViewHolder>? = null
    var jtncpEntities = ArrayList<JtncpEntity>()
    var xzType = false
    var cunType = false
    var tbrType = false
    var sfzxmType = false
    var sfzsjhType = false
    var jtdzxzType = false
    var jtdzcunType = false
    var mphType = false
    var processType = 1
    var conditionPickerView: OptionsPickerView<Any>? = null//选择器

    override fun getLayoutId(): Int {
        return R.layout.activity_bcqh_nhjtjc
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    var tv_yhzgx: TextView? = null
    override fun initViews() {
        iv_act_nhjtjc_back.setOnClickListener {
            finish()
        }

        et_act_nhjtjc_xz.addTextChangedListener(listener(et_act_nhjtjc_xz))
        et_act_nhjtjc_cun.addTextChangedListener(listener(et_act_nhjtjc_cun))
        et_act_nhjtjc_tbr.addTextChangedListener(listener(et_act_nhjtjc_tbr))
        et_act_nhjtjc_sfzxm.addTextChangedListener(listener(et_act_nhjtjc_sfzxm))
        et_act_nhjtjc_sfzsjh.addTextChangedListener(listener(et_act_nhjtjc_sfzsjh))
        et_act_nhjtjc_jtdzxz.addTextChangedListener(listener(et_act_nhjtjc_jtdzxz))
        et_act_nhjtjc_jtdzcun.addTextChangedListener(listener(et_act_nhjtjc_jtdzcun))
        et_act_nhjtjc_mph.addTextChangedListener(listener(et_act_nhjtjc_mph))

        et_act_nhjtjc_xz.setText(AppCache.getInstance().xzqZhenName)
        et_act_nhjtjc_cun.setText(AppCache.getInstance().xzqName)
        val intent = intent
        bcqhType = intent.getIntExtra("type", 1)
        when (bcqhType) {
            1 -> {
                bcqhCodeFlag = intent.getStringExtra("code")
                sortingFlag = intent.getIntExtra("sorting", 0)
                mPresenter.getBcqhDetail(bcqhCodeFlag, sortingFlag, 0L)
            }
            2 -> {
                yearFlag = intent.getStringExtra("year")
                idFlag = intent.getLongExtra("id", 0L)
                sortingFlag = intent.getIntExtra("sorting", 0)
                tv_act_nhjtjc_year.text = yearFlag
                mPresenter.getBcqhDetail(bcqhCodeFlag, sortingFlag, idFlag)
            }
            3 -> {
                bcqhCodeFlag = intent.getStringExtra("code")
                yearFlag = intent.getStringExtra("year")
                sortingFlag = intent.getIntExtra("sorting", 0)
                tv_act_nhjtjc_year.text = yearFlag
                mPresenter.getBcqhDetail(bcqhCodeFlag, sortingFlag, 0L)
            }
        }

        //家庭成员基本信息
        tv_act_nhjtjc_jbqk_add.setOnClickListener {
            addJtcyUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_add_nhjtjc_jbqk, ll_act_nhjtjc_top)
            val contentView: View = addJtcyUpPopu!!.getContentView()
            val et_name = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jbqk_et_name)
            val rgp_sex = contentView.findViewById<RadioGroup>(R.id.pop_add_nhjtjc_jbqk_rgp_sex)
            val et_age = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jbqk_et_age)
            val ll_qtsr = contentView.findViewById<LinearLayout>(R.id.pop_add_nhjtjc_jbqk_ll_qtsr)
            val et_qtsr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jbqk_et_qtsr)
            tv_yhzgx = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jbqk_tv_yhzgx)
            val tv_yhzgx1 = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jbqk_tv_yhzgx1)
            val rgp_sjycd = contentView.findViewById<FlowRadioGroup>(R.id.pop_add_nhjtjc_jbqk_rgp_sjycd)
            val rgp_hj = contentView.findViewById<FlowRadioGroup>(R.id.pop_add_nhjtjc_jbqk_rgp_hj)
            val rgp_cqjzd = contentView.findViewById<FlowRadioGroup>(R.id.pop_add_nhjtjc_jbqk_rgp_cqjzd)
            val rgp_yygf = contentView.findViewById<FlowRadioGroup>(R.id.pop_add_nhjtjc_jbqk_rgp_yygf)
            val rgp_zysrqd = contentView.findViewById<FlowRadioGroup>(R.id.pop_add_nhjtjc_jbqk_rgp_zysrqd)
            val rgp_stjkzk = contentView.findViewById<FlowRadioGroup>(R.id.pop_add_nhjtjc_jbqk_rgp_stjkzk)
            val jbqk_confirm = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jbqk_confirm)
            val jbqk_close = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jbqk_close)

            CommenPop.backgroundAlpha(0.5f, this)
            addJtcyUpPopu!!.showAtLocation(ll_act_nhjtjc_top, Gravity.CENTER, 0, 0)

            jbqk_close.setOnClickListener {
                addJtcyUpPopu!!.dismiss()
            }
            var entity = JtcyjbqkEntity()
            entity.sex = "男"
            entity.sjycd = "小学及以下"
            entity.hb = "农业"
            entity.cqjzd = "农村"
            entity.yygf = "成员个人股"
            entity.zysrqd = "家庭经营收入（含务农）"
            entity.stjkzk = "良好"

            //与户主关系
            tv_yhzgx!!.setOnClickListener {
                initNationPickerView("与户主关系")
            }
            tv_yhzgx!!.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s!!.contains("其他")) {
                        tv_yhzgx1.visibility = View.VISIBLE
                    } else {
                        tv_yhzgx1.visibility = View.GONE
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })

            //性别
            rgp_sex.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                    when (checkedId) {
                        R.id.pop_add_nhjtjc_jbqk_rbt_man -> {
                            entity.sex = "男"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_woman -> {
                            entity.sex = "女"
                        }
                    }
                }

            })
            //受教育程度
            rgp_sjycd.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                    when (checkedId) {
                        R.id.pop_add_nhjtjc_jbqk_rbt_sjycd_a -> {
                            entity.sjycd = "小学及以下"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_sjycd_b -> {
                            entity.sjycd = "初中"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_sjycd_c -> {
                            entity.sjycd = "高中（中专）"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_sjycd_d -> {
                            entity.sjycd = "大专及以上"
                        }
                    }
                }

            })
            //户籍
            rgp_hj.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                    when (checkedId) {
                        R.id.pop_add_nhjtjc_jbqk_rbt_hj_a -> {
                            entity.hb = "农业"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_hj_b -> {
                            entity.hb = "非农业"
                        }
                    }
                }

            })
            //长期居住地
            rgp_cqjzd.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                    when (checkedId) {
                        R.id.pop_add_nhjtjc_jbqk_rbt_cqjzd_a -> {
                            entity.cqjzd = "农村"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_cqjzd_b -> {
                            entity.cqjzd = "小城镇"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_cqjzd_c -> {
                            entity.cqjzd = "城市"
                        }
                    }
                }

            })
            //拥有股份
            rgp_yygf.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                    when (checkedId) {
                        R.id.pop_add_nhjtjc_jbqk_rbt_yygf_a -> {
                            entity.yygf = "成员个人股"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_yygf_b -> {
                            entity.yygf = "非成员个人股"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_yygf_c -> {
                            entity.yygf = "其他"
                        }
                    }
                }

            })
            //主要收入渠道
            rgp_zysrqd.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                    ll_qtsr.visibility = View.GONE
                    when (checkedId) {
                        R.id.pop_add_nhjtjc_jbqk_rbt_zysrqd_a -> {
                            entity.zysrqd = "家庭经营收入（含务农）"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_zysrqd_b -> {
                            entity.zysrqd = "工资性收入（含外出务工）"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_zysrqd_c -> {
                            entity.zysrqd = "财产性收入"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_zysrqd_d -> {
                            entity.zysrqd = "转移性收入"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_zysrqd_qt -> {
                            entity.zysrqd = "其他收入"
                            ll_qtsr.visibility = View.VISIBLE
                        }
                    }
                }

            })
            //身体健康状况
            rgp_stjkzk.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                    when (checkedId) {
                        R.id.pop_add_nhjtjc_jbqk_rbt_stjkzk_a -> {
                            entity.stjkzk = "A 良好"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_stjkzk_b -> {
                            entity.stjkzk = "B 一般"
                        }
                        R.id.pop_add_nhjtjc_jbqk_rbt_stjkzk_c -> {
                            entity.stjkzk = "C 较差"
                        }
                    }
                }

            })
            jbqk_confirm.setOnClickListener {
                val editToast = TdlyUtils.setEditToast(et_name, et_age,tv_yhzgx)
                if (!editToast) {
                    if (tv_yhzgx1.visibility == View.VISIBLE){
                        if (tv_yhzgx1.text.toString().equals("")) {
                            ToastUtils.showShort("请输入与户主其他关系")
                            return@setOnClickListener
                        } else {
                            entity.yhzgx = tv_yhzgx1.text.toString()
                        }
                    }else{
                        entity.yhzgx = tv_yhzgx!!.text.toString()
                    }
                    entity.name = et_name.text.toString()
                    if (et_age.text.toString().equals("")){
                        entity.age = 0L
                    }else{
                        entity.age = et_age.text.toString().toLong()
                    }
                    if (ll_qtsr.visibility == View.VISIBLE){
                        entity.remark = et_qtsr.text.toString()
                    }
                    jtcyjcEntity.zhen = et_act_nhjtjc_xz.text.toString()
                    jtcyjcEntity.xzqmc = et_act_nhjtjc_cun.text.toString()
                    jtcyjcEntity.username = et_act_nhjtjc_tbr.text.toString()
                    jtcyjcEntity.sfname = et_act_nhjtjc_sfzxm.text.toString()
                    jtcyjcEntity.sfphone = et_act_nhjtjc_sfzsjh.text.toString()
                    jtcyjcEntity.jtdzzhen = et_act_nhjtjc_jtdzxz.text.toString()
                    jtcyjcEntity.jtdzxzqmc = et_act_nhjtjc_jtdzcun.text.toString()
                    jtcyjcEntity.mph = et_act_nhjtjc_mph.text.toString()
                    jtcyjcEntity.code = AppCache.getInstance().code
                    jtcyjcEntity.years = yearFlag
                    jtcyjcEntity.sorting = sortingFlag
                    jtcyjcEntity.process = 1
                    jtcyjcEntity.jtcyjbqkEntities.add(entity)
                    mPresenter.getBcqhUpload(jtcyjcEntity)
                }


            }

        }
        //家庭土地经营及财产情况
        tv_act_nhjtjc_tdjyccqk_add.setOnClickListener {
            addJtTdjyJCcqkUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_add_nhjtjc_jttdjyjccqk, ll_act_nhjtjc_top)
            val contentView: View = addJtTdjyJCcqkUpPopu!!.getContentView()
            val et_cbdmj = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_cbdmj)
            val et_lz = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_lz)
            val et_zy = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_zy)
            val iv_addncp = contentView.findViewById<ImageView>(R.id.pop_add_nhjtjc_jttdjjjccqk_iv_addncp)
            val rlv_ncp = contentView.findViewById<SwipeRecyclerView>(R.id.pop_add_nhjtjc_jttdjjjccqk_rlv_ncp)
            val et_nyjx = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_nyjx)
            val et_srqc = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_srqc)
            val et_zzjzmj = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_zzjzmj)
            val et_fulzjg = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_fulzjg)
            val et_zong = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_zong)
            val et_fuzzcz = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_fuzzcz)
            val et_fuczjg = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jttdjjjccqk_et_fuczjg)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jttdjjjccqk_confirm)
            val close = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jttdjjjccqk_close)


            et_cbdmj!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_lz!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_zy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_nyjx!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_srqc!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_zzjzmj!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_fulzjg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_zong!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_fuzzcz!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);
            et_fuczjg!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED);

            CommenPop.backgroundAlpha(0.5f, this)
            addJtTdjyJCcqkUpPopu!!.showAtLocation(ll_act_nhjtjc_top, Gravity.CENTER, 0, 0)

            close.setOnClickListener {
                addJtTdjyJCcqkUpPopu!!.dismiss()
            }

            var entity = Jtjyccqk()
            if (jtcyjcEntity.jtjyccqk != null) {
                val entity1 = jtcyjcEntity.jtjyccqk
                entity = entity1
                jtncpEntities = entity1.jtncpEntities as ArrayList<JtncpEntity>
                entity.jtncpEntities = jtncpEntities
                et_cbdmj.setText(entity.cbdmj)
                et_lz.setText(entity.qzlz)
                et_zy.setText(entity.qzzy)
                et_nyjx.setText(entity.nyjxsb)
                et_srqc.setText(entity.srqc)
                et_zzjzmj.setText(entity.zzjzmj)
                et_fulzjg.setText(entity.cbdlzjg)
                et_zong.setText(entity.zzczzs)
                et_fuzzcz.setText(entity.zzcz)
                et_fuczjg.setText(entity.czjg)
            }

            rlv_ncp.layoutManager = LinearLayoutManager(this@BcqhNhjtjcActivity, LinearLayoutManager.VERTICAL, false)

            rlv_ncp.isItemViewSwipeEnabled = false// 侧滑删除，默认关闭
            val itemDecoration = DefaultItemDecoration(Color.parseColor("#eeeeee"))
            rlv_ncp.addItemDecoration(itemDecoration)
            ncpAddAdapter = object : BaseQuickAdapter<JtncpEntity, BaseViewHolder>(R.layout.item_bcqh_add_ncp, jtncpEntities) {
                override fun convert(helper: BaseViewHolder?, item: JtncpEntity?) {
                    val et_zzncp = helper!!.getView<EditText>(R.id.item_bcqh_ncqadd_et_zzncp)
                    val et_mj = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_mj)
                    val et_cb = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_cb)
                    val et_rg = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_rg)
                    val et_zz = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_zz)
                    val et_fl = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_fl)
                    val et_ny = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_ny)
                    val et_jxzy = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_jxzy)
                    val et_zxssr = helper.getView<EditText>(R.id.item_bcqh_ncqadd_et_zxssr)
                    et_zzncp!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    et_mj!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    et_cb!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    et_zz!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    et_fl!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    et_ny!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    et_jxzy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    et_zxssr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)

                    et_zzncp.setText(item!!.zyzzncp)
                    et_mj.setText(item.mj)
                    et_cb.setText(item.cb)
                    et_rg.setText(item.rg)
                    et_zz.setText(item.zz)
                    et_fl.setText(item.fl)
                    et_ny.setText(item.ny)
                    et_jxzy.setText(item.jxzy)
                    et_zxssr.setText(item.zxssr)
                    et_zzncp.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_zzncp.text.toString().equals("")) {
                                item.zyzzncp = et_zzncp.text.toString()
                            } else {
                                item.zyzzncp = ""
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_mj.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_mj.text.toString().equals("")) {
                                item.mj = et_mj.text.toString()
                            } else {
                                item.mj = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_cb.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_cb.text.toString().equals("")) {
                                item.cb = et_cb.text.toString()
                            } else {
                                item.cb = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_rg.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_rg.text.toString().equals("")) {
                                item.rg = et_rg.text.toString()
                            } else {
                                item.rg = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_zz.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_zz.text.toString().equals("")) {
                                item.zz = et_zz.text.toString()
                            } else {
                                item.zz = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_fl.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_fl.text.toString().equals("")) {
                                item.fl = et_fl.text.toString()
                            } else {
                                item.fl = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_ny.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_ny.text.toString().equals("")) {
                                item.ny = et_ny.text.toString()
                            } else {
                                item.ny = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_jxzy.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_jxzy.text.toString().equals("")) {
                                item.jxzy = et_jxzy.text.toString()
                            } else {
                                item.jxzy = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })
                    et_zxssr.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            if (!et_zxssr.text.toString().equals("")) {
                                item.zxssr = et_zxssr.text.toString()
                            } else {
                                item.zxssr = "0"
                            }
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }
                    })


                    helper!!.getView<TextView>(R.id.tv_item_bcqh_ncpadd_det).setOnClickListener {


                        val content = TextView(this@BcqhNhjtjcActivity)
                        content.text = "是否删除？"
                        SweetAlertDialog(this@BcqhNhjtjcActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除农产品信息")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    jtcyjcEntity.jtjyccqk.jtncpids.add(item.id)
                                    jtncpEntities.removeAt(helper.layoutPosition)
                                    notifyDataSetChanged()
                                    sweetAlertDialog.dismiss()
                                }
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }


                }
            }

            rlv_ncp.adapter = ncpAddAdapter

            iv_addncp.setOnClickListener {
                jtncpEntities.add(JtncpEntity())
                ncpAddAdapter!!.setNewData(jtncpEntities)
                ncpAddAdapter!!.notifyDataSetChanged()
            }

            confirm.setOnClickListener {
                val editToast = TdlyUtils.setEditToast(et_cbdmj, et_lz, et_zy, et_nyjx,
                        et_srqc, et_zzjzmj, et_fulzjg, et_fuzzcz, et_zong, et_fuczjg)
                if (!editToast) {
                    entity.jtncpEntities = jtncpEntities
                    entity.years = yearFlag
                    entity.cbdmj = et_cbdmj.text.toString()
                    entity.qzlz = et_lz.text.toString()
                    entity.qzzy = et_zy.text.toString()
                    entity.nyjxsb = et_nyjx.text.toString()
                    entity.srqc = et_srqc.text.toString()
                    entity.zzjzmj = et_zzjzmj.text.toString()
                    entity.cbdlzjg = et_fulzjg.text.toString()
                    entity.zzcz = et_fuzzcz.text.toString()
                    entity.zzczzs = et_zong.text.toString()
                    entity.czjg = et_fuczjg.text.toString()
                    jtcyjcEntity.zhen = et_act_nhjtjc_xz.text.toString()
                    jtcyjcEntity.xzqmc = et_act_nhjtjc_cun.text.toString()
                    jtcyjcEntity.username = et_act_nhjtjc_tbr.text.toString()
                    jtcyjcEntity.sfname = et_act_nhjtjc_sfzxm.text.toString()
                    jtcyjcEntity.sfphone = et_act_nhjtjc_sfzsjh.text.toString()
                    jtcyjcEntity.jtdzzhen = et_act_nhjtjc_jtdzxz.text.toString()
                    jtcyjcEntity.jtdzxzqmc = et_act_nhjtjc_jtdzcun.text.toString()
                    jtcyjcEntity.mph = et_act_nhjtjc_mph.text.toString()
                    jtcyjcEntity.code = AppCache.getInstance().code
                    jtcyjcEntity.years = yearFlag
                    jtcyjcEntity.sorting = sortingFlag
                    jtcyjcEntity.process = 1
                    jtcyjcEntity.jtjyccqk = entity

                    mPresenter.getBcqhUpload(jtcyjcEntity)
                }
            }
        }
        //家庭收入支出情况
        tv_act_nhjtjc_srzc_add.setOnClickListener {
            addJtSrzcqkUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_add_nhjtjc_jtsrzcqk, ll_act_nhjtjc_top)
            val contentView: View = addJtSrzcqkUpPopu!!.getContentView()
            val et_srhj = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_srhj)
            val et_jtjysr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_jtjysr)
            val et_gzxsr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_gzxsr)
            val et_ccxsr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_ccxsr)
            val et_zyxsr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_zyxsr)
            val et_qtsr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_qtsr)
            val et_zchj = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_zchj)
            val et_sczl = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_sczl)
            val et_shxf = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_shxf)
            val et_jy = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_jy)
            val et_yl = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_yl)
            val et_sylr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_sylr)
            val et_sb = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_sb)
            val et_zcqtsr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_zcqtsr)
            val et_fucjtjysr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_fucjtjysr)
            val et_fujtjysr = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtsrzcqk_et_fujtjysr)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jtsrzcqk_confirm)
            val close = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jtsrzcqk_close)
            et_srhj!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_jtjysr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_gzxsr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_ccxsr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_zyxsr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_qtsr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_zchj!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_sczl!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_shxf!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_jy!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_yl!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_sylr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_sb!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_zcqtsr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_fucjtjysr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_fujtjysr!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)

            CommenPop.backgroundAlpha(0.5f, this)
            addJtSrzcqkUpPopu!!.showAtLocation(ll_act_nhjtjc_top, Gravity.CENTER, 0, 0)

            close.setOnClickListener {
                addJtSrzcqkUpPopu!!.dismiss()
            }
            var entity = JtsrzcqkEntity()
            if (jtcyjcEntity.jtsrzcqkEntity != null) {
                val entity1 = jtcyjcEntity.jtsrzcqkEntity
                entity = entity1
                et_srhj.setText(entity.srhj)
                et_jtjysr.setText(entity.jtjysr)
                et_gzxsr.setText(entity.gzxsr)
                et_ccxsr.setText(entity.ccxsr)
                et_zyxsr.setText(entity.zyxsr)
                et_qtsr.setText(entity.qtsr)
                et_zchj.setText(entity.zchj)
                et_sczl.setText(entity.sczl)
                et_shxf.setText(entity.shxf)
                et_jy.setText(entity.jy)
                et_yl.setText(entity.yl)
                et_sylr.setText(entity.sylr)
                et_sb.setText(entity.sb)
                et_zcqtsr.setText(entity.qtzc)
                et_fucjtjysr.setText(entity.cjtjysr)
                et_fujtjysr.setText(entity.jtjydsr)
            }
            confirm.setOnClickListener {//, et_qtsr   , et_fujtjysr
                val editToast = TdlyUtils.setEditToast(et_srhj, et_jtjysr, et_gzxsr, et_ccxsr,
                        et_zyxsr, et_zchj, et_sczl, et_shxf, et_jy, et_yl, et_sylr, et_sb, et_zcqtsr,
                        et_fucjtjysr)
                if (!editToast) {
                    entity.years = yearFlag
                    entity.srhj = et_srhj.text.toString()
                    entity.jtjysr = et_jtjysr.text.toString()
                    entity.gzxsr = et_gzxsr.text.toString()
                    entity.ccxsr = et_ccxsr.text.toString()
                    entity.zyxsr = et_zyxsr.text.toString()
                    entity.qtsr = et_qtsr.text.toString()
                    entity.zchj = et_zchj.text.toString()
                    entity.sczl = et_sczl.text.toString()
                    entity.shxf = et_shxf.text.toString()
                    entity.jy = et_jy.text.toString()
                    entity.yl = et_yl.text.toString()
                    entity.sylr = et_sylr.text.toString()
                    entity.sb = et_sb.text.toString()
                    entity.qtzc = et_zcqtsr.text.toString()
                    entity.cjtjysr = et_fucjtjysr.text.toString()
                    entity.jtjydsr = et_fujtjysr.text.toString()

                    jtcyjcEntity.zhen = et_act_nhjtjc_xz.text.toString()
                    jtcyjcEntity.xzqmc = et_act_nhjtjc_cun.text.toString()
                    jtcyjcEntity.username = et_act_nhjtjc_tbr.text.toString()
                    jtcyjcEntity.sfname = et_act_nhjtjc_sfzxm.text.toString()
                    jtcyjcEntity.sfphone = et_act_nhjtjc_sfzsjh.text.toString()
                    jtcyjcEntity.jtdzzhen = et_act_nhjtjc_jtdzxz.text.toString()
                    jtcyjcEntity.jtdzxzqmc = et_act_nhjtjc_jtdzcun.text.toString()
                    jtcyjcEntity.mph = et_act_nhjtjc_mph.text.toString()
                    jtcyjcEntity.code = AppCache.getInstance().code
                    jtcyjcEntity.years = yearFlag
                    jtcyjcEntity.sorting = sortingFlag
                    jtcyjcEntity.process = 1
                    jtcyjcEntity.jtsrzcqkEntity = entity

                    mPresenter.getBcqhUpload(jtcyjcEntity)
                }
            }
        }
        //家庭社会保障与财政需求情况
        tv_act_nhjtjc_sbyczxq_add.setOnClickListener {
            addJtShbzyCzxqqkUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_add_nhjtjc_jtshbzyczxqqk, ll_act_nhjtjc_top)
            val contentView: View = addJtShbzyCzxqqkUpPopu!!.getContentView()
            val et_cjgygwrs = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_cjgygwrs)
            val et_hdnyzcxbt = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_hdnyzcxbt)
            val et_hzsyyfh = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_hzsyyfh)
            val et_czzgjbylbxrs = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_czzgjbylbxrs)
            val et_gxjbylbxrs = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_gxjbylbxrs)
            val et_czzgjbyanglbxrs = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_czzgjbyanglbxrs)
            val et_cxjbylbxrs = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_cxjbylbxrs)
            val et_zxyczzcxm = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_zxyczzcxm)
            val ll_czzcxm = contentView.findViewById<LinearLayout>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_ll_czzcxm)
            val et_czzcxm1 = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_czzcxm1)
            val et_zxypxxm = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_zxypxxm)
            val ll_zxypxxm = contentView.findViewById<LinearLayout>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_ll_zxypxxm)
            val et_zxypxxm1 = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_zxypxxm1)
            val et_hqzcxxzyqd = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_hqzcxxzyqd)
            val ll_hqzcxxzyqd = contentView.findViewById<LinearLayout>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_ll_hqzcxxzyqd)
            val et_hqzcxxzyqd1 = contentView.findViewById<EditText>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_et_hqzcxxzyqd1)
            val confirm = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_confirm)
            val close = contentView.findViewById<TextView>(R.id.pop_add_nhjtjc_jtshbzyczxqqk_close)
            et_cjgygwrs!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_hdnyzcxbt!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_hzsyyfh!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_czzgjbylbxrs!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_gxjbylbxrs!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_czzgjbyanglbxrs!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
            et_cxjbylbxrs!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
//            et_zxyczzcxm!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
//            et_czzcxm1!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
//            et_zxypxxm!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
//            et_zxypxxm1!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
//            et_hqzcxxzyqd!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
//            et_hqzcxxzyqd1!!.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)

            CommenPop.backgroundAlpha(0.5f, this)
            addJtShbzyCzxqqkUpPopu!!.showAtLocation(ll_act_nhjtjc_top, Gravity.CENTER, 0, 0)

            close.setOnClickListener {
                addJtShbzyCzxqqkUpPopu!!.dismiss()
            }

            et_zxypxxm.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s!!.contains("f") || s.contains("F")) {
                        ll_zxypxxm.visibility = View.VISIBLE
                    } else {
                        ll_zxypxxm.visibility = View.GONE
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
            et_zxyczzcxm.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s!!.contains("i") || s.contains("I")) {
                        ll_czzcxm.visibility = View.VISIBLE
                    } else {
                        ll_czzcxm.visibility = View.GONE
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
            et_hqzcxxzyqd.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s!!.contains("h") || s.contains("H")) {
                        ll_hqzcxxzyqd.visibility = View.VISIBLE
                    } else {
                        ll_hqzcxxzyqd.visibility = View.GONE
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
            var entity = JtshbzqkEntity()//因为new一个空的 没有id 所以每次修改都是添加
            if (jtcyjcEntity.jtshbzqkEntity != null) {
                val entity1 = jtcyjcEntity.jtshbzqkEntity
                entity = entity1
                et_cjgygwrs.setText(entity.cjgygwrs)
                et_hdnyzcxbt.setText(entity.hdnyzcbt)
                et_hzsyyfh.setText(entity.hzsfh)
                et_czzgjbylbxrs.setText(entity.czyilbxrs)
                et_gxjbylbxrs.setText(entity.cxyilbxrs)
                et_czzgjbyanglbxrs.setText(entity.czylbxrs)
                et_cxjbylbxrs.setText(entity.cxylbxrs)
                et_zxyczzcxm.setText(entity.czzc)
                et_zxypxxm.setText(entity.pxxm)
                et_hqzcxxzyqd.setText(entity.zyqd)
                if (entity.czzc.contains("i") || entity.czzc.contains("I"))    {
                    et_czzcxm1.setText(entity.czzcbz)
                    ll_czzcxm.visibility = View.VISIBLE
                }else{
                    ll_czzcxm.visibility = View.GONE
                }
                if (entity.pxxm.contains("f") || entity.pxxm.contains("F"))    {
                    et_zxypxxm1.setText(entity.pxxmbz)
                    ll_zxypxxm.visibility = View.VISIBLE
                }else{
                    ll_zxypxxm.visibility = View.GONE
                }
                if (entity.zyqd.contains("h") || entity.zyqd.contains("H")) {
                    et_hqzcxxzyqd1.setText(entity.zyqdbz)
                    ll_hqzcxxzyqd.visibility = View.VISIBLE
                } else {
                    ll_hqzcxxzyqd.visibility = View.GONE
                }
            }
            confirm.setOnClickListener {
                val editToast = TdlyUtils.setEditToast(et_cjgygwrs, et_hdnyzcxbt, et_hzsyyfh, et_czzgjbylbxrs,
                        et_gxjbylbxrs, et_czzgjbyanglbxrs, et_cxjbylbxrs, et_zxyczzcxm, et_zxypxxm, et_hqzcxxzyqd)
                if (!editToast) {
                    entity.years = yearFlag
                    entity.cjgygwrs = et_cjgygwrs.text.toString()
                    entity.hdnyzcbt = et_hdnyzcxbt.text.toString()
                    entity.hzsfh = et_hzsyyfh.text.toString()
                    entity.czyilbxrs = et_czzgjbylbxrs.text.toString()
                    entity.cxyilbxrs = et_gxjbylbxrs.text.toString()
                    entity.czylbxrs = et_czzgjbyanglbxrs.text.toString()
                    entity.cxylbxrs = et_cxjbylbxrs.text.toString()
//                    entity.czzc = et_zxyczzcxm.text.toString()
                    if (ll_czzcxm.visibility == View.VISIBLE) {
                        if (et_czzcxm1.text.toString().equals("")) {
                            ToastUtils.showShort("请输入其他财政政策项目")
                            return@setOnClickListener
                        } else {
                            entity.czzc = et_zxyczzcxm.text.toString() //+ ",F:" + et_zxypxxm1.text.toString()
                            entity.czzcbz = et_czzcxm1.text.toString()
                        }
                    } else {
                        entity.czzc = et_zxyczzcxm.text.toString()
                    }
                    if (ll_zxypxxm.visibility == View.VISIBLE) {
                        if (et_zxypxxm1.text.toString().equals("")) {
                            ToastUtils.showShort("请输入其他培训项目")
                            return@setOnClickListener
                        } else {
                            entity.pxxm = et_zxypxxm.text.toString() //+ ",F:" + et_zxypxxm1.text.toString()
                            entity.pxxmbz = et_zxypxxm1.text.toString()
                        }
                    } else {
                        entity.pxxm = et_zxypxxm.text.toString()
                    }
                    if (ll_hqzcxxzyqd.visibility == View.VISIBLE) {
                        if (et_hqzcxxzyqd1.text.toString().equals("")) {
                            ToastUtils.showShort("请输入获取政策信息的其他渠道")
                            return@setOnClickListener
                        } else {
                            entity.zyqd = et_hqzcxxzyqd.text.toString() //+ ",H:" + et_hqzcxxzyqd1.text.toString()
                            entity.zyqdbz = et_hqzcxxzyqd1.text.toString()
                        }
                    } else {
                        entity.zyqd = et_hqzcxxzyqd.text.toString()
                    }

                    jtcyjcEntity.zhen = et_act_nhjtjc_xz.text.toString()
                    jtcyjcEntity.xzqmc = et_act_nhjtjc_cun.text.toString()
                    jtcyjcEntity.username = et_act_nhjtjc_tbr.text.toString()
                    jtcyjcEntity.sfname = et_act_nhjtjc_sfzxm.text.toString()
                    jtcyjcEntity.sfphone = et_act_nhjtjc_sfzsjh.text.toString()
                    jtcyjcEntity.jtdzzhen = et_act_nhjtjc_jtdzxz.text.toString()
                    jtcyjcEntity.jtdzxzqmc = et_act_nhjtjc_jtdzcun.text.toString()
                    jtcyjcEntity.mph = et_act_nhjtjc_mph.text.toString()
                    jtcyjcEntity.code = AppCache.getInstance().code
                    jtcyjcEntity.years = yearFlag
                    jtcyjcEntity.sorting = sortingFlag
                    jtcyjcEntity.process = 1
                    jtcyjcEntity.jtshbzqkEntity = entity

                    mPresenter.getBcqhUpload(jtcyjcEntity)
                }
            }
        }

        bt_act_nhjtjc_upload.setOnClickListener {

            val editToast = TdlyUtils.setEditToast(et_act_nhjtjc_xz, et_act_nhjtjc_cun, et_act_nhjtjc_tbr, et_act_nhjtjc_sfzxm,
                    et_act_nhjtjc_sfzsjh, et_act_nhjtjc_jtdzxz, et_act_nhjtjc_jtdzcun, et_act_nhjtjc_mph)
            if (!editToast) {
                jtcyjcEntity.zhen = et_act_nhjtjc_xz.text.toString()
                jtcyjcEntity.xzqmc = et_act_nhjtjc_cun.text.toString()
                jtcyjcEntity.username = et_act_nhjtjc_tbr.text.toString()
                jtcyjcEntity.sfname = et_act_nhjtjc_sfzxm.text.toString()
                jtcyjcEntity.sfphone = et_act_nhjtjc_sfzsjh.text.toString()
                jtcyjcEntity.jtdzzhen = et_act_nhjtjc_jtdzxz.text.toString()
                jtcyjcEntity.jtdzxzqmc = et_act_nhjtjc_jtdzcun.text.toString()
                jtcyjcEntity.mph = et_act_nhjtjc_mph.text.toString()
                jtcyjcEntity.process = 4
                if (jtcyjcEntity.jtcyjbqkEntities.size==0){
                    ToastUtils.showShort("请完善家庭成员基本情况表")
                }else if (jtcyjcEntity.jtjyccqk.id==null||jtcyjcEntity.jtjyccqk.id==0L){
                    ToastUtils.showShort("请完善家庭土地经营及财产情况表")
                }else if (jtcyjcEntity.jtsrzcqkEntity.id==null||jtcyjcEntity.jtsrzcqkEntity.id==0L){
                    ToastUtils.showShort("请完善家庭收入支出情况表")
                }else if (jtcyjcEntity.jtshbzqkEntity.id==null||jtcyjcEntity.jtshbzqkEntity.id==0L){
                    ToastUtils.showShort("请完善家庭社会保障与财政需求情况表")
                }else{
                    mPresenter.getBcqhUpload(jtcyjcEntity)
                }
            }


        }

        /*if (AppCache.getInstance().name.equals("cs1")){
            tv_act_nhjtjc_bh.visibility =View.VISIBLE
            tv_act_nhjtjc_bh.setOnClickListener {
                jtcyjcEntity.process = 1
                mPresenter.getBcqhUpload(jtcyjcEntity)
            }
        }else{
            tv_act_nhjtjc_bh.visibility =View.GONE
        }*/
    }

    override fun initDatas() {
    }

    //初始化选择器
    private fun initNationPickerView(type: String) {
        conditionPickerView = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            if (type.equals("与户主关系")) {
                tv_yhzgx!!.text = getYhzgx()[options1]
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
        if (type.equals("与户主关系")) {
            conditionPickerView!!.setPicker(getYhzgx() as List<Any>?)//一级选择器
        }

        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        params.leftMargin = 0
        params.rightMargin = 0
        val contentContainer = conditionPickerView!!.dialogContainerLayout
        contentContainer.layoutParams = params
        conditionPickerView!!.dialog?.window?.setGravity(Gravity.BOTTOM)
        conditionPickerView!!.dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        conditionPickerView!!.show()
    }

    //初始化与户主关系
    private fun getYhzgx(): ArrayList<String> {
        val list = ArrayList<String>()

        list.add("户主")
        list.add("之夫")
        list.add("之妻")
        list.add("之父")
        list.add("之母")
        list.add("之子")
        list.add("之女")
        list.add("之儿媳")
        list.add("之孙")
        list.add("之孙女")
        list.add("之外孙")
        list.add("之外孙女")
        list.add("之兄")
        list.add("之弟")
        list.add("之妹")
        list.add("之姐")
        list.add("之女婿")
        list.add("之祖父")
        list.add("之祖母")
        list.add("其他")

        return list
    }

    //监听所有信息选项的状态
    private fun listener(view: View): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when (view) {
                    et_act_nhjtjc_xz -> {
                        xzType = s!!.isNotEmpty()
                    }
                    et_act_nhjtjc_cun -> {
                        cunType = s!!.isNotEmpty()
                    }
                    et_act_nhjtjc_tbr -> {
                        tbrType = s!!.isNotEmpty()
                    }
                    et_act_nhjtjc_sfzxm -> {
                        sfzxmType = s!!.isNotEmpty()
                    }
                    et_act_nhjtjc_sfzsjh -> {
                        sfzsjhType = s!!.isNotEmpty()
                    }
                    et_act_nhjtjc_jtdzxz -> {
                        jtdzxzType = s!!.isNotEmpty()
                    }
                    et_act_nhjtjc_jtdzcun -> {
                        jtdzcunType = s!!.isNotEmpty()
                    }
                    et_act_nhjtjc_mph -> {
                        mphType = s!!.isNotEmpty()
                    }

                }
                if (processType == 1) {
                    if (xzType && cunType && tbrType && sfzxmType && sfzsjhType && jtdzxzType && jtdzcunType && mphType) {
                        tv_act_nhjtjc_jbqk_add.visibility = View.VISIBLE
                        tv_act_nhjtjc_tdjyccqk_add.visibility = View.VISIBLE
                        tv_act_nhjtjc_srzc_add.visibility = View.VISIBLE
                        tv_act_nhjtjc_sbyczxq_add.visibility = View.VISIBLE
                        bt_act_nhjtjc_upload.visibility = View.VISIBLE
                    } else {
                        tv_act_nhjtjc_jbqk_add.visibility = View.GONE
                        tv_act_nhjtjc_tdjyccqk_add.visibility = View.GONE
                        tv_act_nhjtjc_srzc_add.visibility = View.GONE
                        tv_act_nhjtjc_sbyczxq_add.visibility = View.GONE
                        bt_act_nhjtjc_upload.visibility = View.GONE
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
    }

    var jtcyjbqkEntityList = ArrayList<JtcyjbqkEntity>()
    var jtjyccqkList = ArrayList<Jtjyccqk>()
    var jtsrzcqkEntityList = ArrayList<JtsrzcqkEntity>()
    var jtshbzqkEntityList = ArrayList<JtshbzqkEntity>()
    override fun returnBcqhDetail(msg: List<JtcyjcEntity>) {
        if (msg != null && msg.size > 0) {
            val entity = msg.get(0)

            if (AppCache.getInstance().type!=4){
                et_act_nhjtjc_xz.setText(entity.zhen)
                et_act_nhjtjc_cun.setText(entity.xzqmc)
            }

            et_act_nhjtjc_tbr.setText(entity.username)
            et_act_nhjtjc_sfzxm.setText(entity.sfname)
            et_act_nhjtjc_sfzsjh.setText(entity.sfphone)
            et_act_nhjtjc_jtdzxz.setText(entity.jtdzzhen)
            et_act_nhjtjc_jtdzcun.setText(entity.jtdzxzqmc)
            et_act_nhjtjc_mph.setText(entity.mph)
            if (AppCache.getInstance().type == 4) {
                tv_act_nhjtjc_year.text = entity.years
            }

            jtcyjcEntity = jtcyjcEntity1
            jtcyjcEntity = entity
            processType = jtcyjcEntity.process
            if (jtcyjcEntity.process == 4) {
                tv_act_nhjtjc_jbqk_add.visibility = View.GONE
                tv_act_nhjtjc_tdjyccqk_add.visibility = View.GONE
                tv_act_nhjtjc_srzc_add.visibility = View.GONE
                tv_act_nhjtjc_sbyczxq_add.visibility = View.GONE
                bt_act_nhjtjc_upload.visibility = View.GONE
                act_nhjtjc_ell.setNoClick(true)
            }
            jtjyccqkList.clear()
            jtsrzcqkEntityList.clear()
            jtshbzqkEntityList.clear()

            for (i in msg) {
                jtjyccqkList.add(i.jtjyccqk)
                jtsrzcqkEntityList.add(i.jtsrzcqkEntity)
                jtshbzqkEntityList.add(i.jtshbzqkEntity)
            }

            rlv_act_nhjtjc_jbqk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            rlv_act_nhjtjc_tdjyjccqk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            rlv_act_nhjtjc_tdjyjccqk_fu.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rlv_act_nhjtjc_jtsrzcqk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            rlv_act_nhjtjc_jtsrzcqk_fu.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rlv_act_nhjtjc_shbzyczxqqk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            if (msg.get(0).jtcyjbqkEntities.size == 0){
                jtcyjbqkEntityList.clear()
                jtcyjbqkEntityList.add(JtcyjbqkEntity())
            }else{
                jtcyjbqkEntityList.clear()
                jtcyjbqkEntityList = msg.get(0).jtcyjbqkEntities as ArrayList<JtcyjbqkEntity>
            }

            rlv_act_nhjtjc_jbqk.adapter = object : BaseQuickAdapter<JtcyjbqkEntity, BaseViewHolder>(R.layout.item_bcqh_jtjbqk, jtcyjbqkEntityList) {
                override fun convert(helper: BaseViewHolder?, item: JtcyjbqkEntity?) {
                    helper!!.setText(R.id.item_bcqh_jtjbqk_name, item!!.name)
                            .setText(R.id.item_bcqh_jtjbqk_sex, item.sex)
                            .setText(R.id.item_bcqh_jtjbqk_age, if (item.age == 0L) "" else item.age.toString())
                            .setText(R.id.item_bcqh_jtjbqk_yhzgx, item.yhzgx)
                            .setText(R.id.item_bcqh_jtjbqk_sjycd, item.sjycd)
                            .setText(R.id.item_bcqh_jtjbqk_hj, item.hb)
                            .setText(R.id.item_bcqh_jtjbqk_cqjzd, item.cqjzd)
                            .setText(R.id.item_bcqh_jtjbqk_yygf, item.yygf)
                            .setText(R.id.item_bcqh_jtjbqk_zysrqd, item.zysrqd+item.remark)
                            .setText(R.id.item_bcqh_jtjbqk_stjkzt, item.stjkzk)
                    if (item.stjkzk.equals("A")){
                        helper!!.setText(R.id.item_bcqh_jtjbqk_stjkzt, "良好")
                    }else if (item.stjkzk.equals("B")){
                        helper!!.setText(R.id.item_bcqh_jtjbqk_stjkzt, "一般")
                    }else if (item.stjkzk.equals("C")){
                        helper!!.setText(R.id.item_bcqh_jtjbqk_stjkzt, "较差")
                    }
                    if (item.name.equals("")||AppCache.getInstance().type!=4||(jtcyjcEntity.process!=1&&jtcyjcEntity.process!=0)){
                        helper.getView<TextView>(R.id.item_bcqh_jtjbqk_det).visibility = View.GONE
                        tv_act_bcqh_nhjtjc_delete.visibility = View.GONE
                    }else{
                        helper.getView<TextView>(R.id.item_bcqh_jtjbqk_det).visibility = View.VISIBLE
                        tv_act_bcqh_nhjtjc_delete.visibility = View.VISIBLE
                    }

                    helper.getView<TextView>(R.id.item_bcqh_jtjbqk_det).setOnClickListener {

                        val content = TextView(this@BcqhNhjtjcActivity)
                        content.text = "是否删除？"
                        SweetAlertDialog(this@BcqhNhjtjcActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除 " + item.name + " 信息")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    jtcyjcEntity.jtcyjbqkids.add(item.id)
                                    mPresenter.getBcqhUpload(jtcyjcEntity)
                                    sweetAlertDialog.dismiss()
                                }
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()


                    }

                }

            }

            rlv_act_nhjtjc_tdjyjccqk.adapter = object : BaseQuickAdapter<Jtjyccqk, BaseViewHolder>(R.layout.item_bcqh_jttdjyjccqk, jtjyccqkList) {
                override fun convert(helper: BaseViewHolder?, item: Jtjyccqk?) {
                    helper!!.setText(R.id.item_bcqh_jttdjyjccqk_year, jtcyjcEntity.years)
                            .setText(R.id.item_bcqh_jttdjyjccqk_cbdmj, item!!.cbdmj)
                            .setText(R.id.item_bcqh_jttdjyjccqk_lz, item.qzlz)
                            .setText(R.id.item_bcqh_jttdjyjccqk_zy, item.qzzy)
                            .setText(R.id.item_bcqh_jttdjyjccqk_nyjx, item.nyjxsb)
                            .setText(R.id.item_bcqh_jttdjyjccqk_srqc, item.srqc)
                            .setText(R.id.item_bcqh_jttdjyjccqk_zzjzmj, item.zzjzmj)
                    val ncp_rlv = helper.getView<RecyclerView>(R.id.item_bcqh_jttdjyjccqk_ncp_rlv)
                    ncp_rlv.layoutManager = LinearLayoutManager(this@BcqhNhjtjcActivity, LinearLayoutManager.HORIZONTAL, false)
                    if (item.jtncpEntities.size == 0){
                        val arrayList = ArrayList<JtncpEntity>()
                        arrayList.add(JtncpEntity())
                        item.jtncpEntities = arrayList
                    }
                    ncp_rlv.adapter = object : BaseQuickAdapter<JtncpEntity, BaseViewHolder>(R.layout.item_bcqh_jttdjyjccqk_ncp, item.jtncpEntities) {
                        override fun convert(helper: BaseViewHolder?, item: JtncpEntity?) {
                            helper!!.setText(R.id.item_bcqh_jttdjyjccqk_ncp, item!!.zyzzncp)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_mj, item.mj)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_cb, item.cb)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_rg, item.rg)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_zz, item.zz)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_fl, item.fl)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_ny, item.ny)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_jxzy, item.jxzy)
                                    .setText(R.id.item_bcqh_jttdjyjccqk_zxssr, item.zxssr)
                        }

                    }
                }

            }
            rlv_act_nhjtjc_tdjyjccqk_fu.adapter = object : BaseQuickAdapter<Jtjyccqk, BaseViewHolder>(R.layout.item_bcqh_jttdjyjccqk_fu, jtjyccqkList) {
                override fun convert(helper: BaseViewHolder?, item: Jtjyccqk?) {
                    helper!!.setText(R.id.item_bcqh_jttdjyjccqkfu_lzjg,jtcyjcEntity!!.years+"承包地流转价格（"+item!!.cbdlzjg+"）元/亩")
                            .setText(R.id.item_bcqh_jttdjyjccqkfu_cz,jtcyjcEntity.years+"住宅出租（"+item.zzczzs +"）宗，（"+item.zzcz+"）平方米，出租价格（"+item.czjg+"）元/平方米")
                }

            }

            rlv_act_nhjtjc_jtsrzcqk.adapter = object : BaseQuickAdapter<JtsrzcqkEntity, BaseViewHolder>(R.layout.item_bcqh_jtsrzcqk, jtsrzcqkEntityList) {
                override fun convert(helper: BaseViewHolder?, item: JtsrzcqkEntity?) {
                    helper!!.setText(R.id.item_bcqh_jtsrzcqk_year,  jtcyjcEntity.years)
                            .setText(R.id.item_bcqh_jtsrzcqk_srhj, item!!.srhj)
                            .setText(R.id.item_bcqh_jtsrzcqk_jtjysr, item.jtjysr)
                            .setText(R.id.item_bcqh_jtsrzcqk_gzxsr, item.gzxsr)
                            .setText(R.id.item_bcqh_jtsrzcqk_ccxsr, item.ccxsr)
                            .setText(R.id.item_bcqh_jtsrzcqk_zyxsr, item.zyxsr)
                            .setText(R.id.item_bcqh_jtsrzcqk_qtsr, item.qtsr)
                            .setText(R.id.item_bcqh_jtsrzcqk_zchj, item.zchj)
                            .setText(R.id.item_bcqh_jtsrzcqk_sczl, item.sczl)
                            .setText(R.id.item_bcqh_jtsrzcqk_shxf, item.shxf)
                            .setText(R.id.item_bcqh_jtsrzcqk_jy, item.jy)
                            .setText(R.id.item_bcqh_jtsrzcqk_yl, item.yl)
                            .setText(R.id.item_bcqh_jtsrzcqk_sylr, item.sylr)
                            .setText(R.id.item_bcqh_jtsrzcqk_sb, item.sb)
                            .setText(R.id.item_bcqh_jtsrzcqk_qtzc, item.qtzc)
                }

            }
            rlv_act_nhjtjc_jtsrzcqk_fu.adapter = object : BaseQuickAdapter<JtsrzcqkEntity, BaseViewHolder>(R.layout.item_bcqh_jtsrzcqk_fu, jtsrzcqkEntityList) {
                override fun convert(helper: BaseViewHolder?, item: JtsrzcqkEntity?) {
                    //，家庭经营的收入（"+item.jtjydsr+"）元
                    helper!!.setText(R.id.item_bcqh_jtsrccqkfu_sr, item!!.years+"来自村集体经营的收入（"+item.cjtjysr+"）元")

                }

            }

            rlv_act_nhjtjc_shbzyczxqqk.adapter = object : BaseQuickAdapter<JtshbzqkEntity, BaseViewHolder>(R.layout.item_bcqh_shbzyczxqqk, jtshbzqkEntityList) {
                override fun convert(helper: BaseViewHolder?, item: JtshbzqkEntity?) {
                    helper!!.setText(R.id.item_bcqh_shbzyczxqqk_year,  jtcyjcEntity.years)
                            .setText(R.id.item_bcqh_shbzyczxqqk_cjgygwrs, item!! .cjgygwrs)
                            .setText(R.id.item_bcqh_shbzyczxqqk_hdnyzcxbt, item.hdnyzcbt)
                            .setText(R.id.item_bcqh_shbzyczxqqk_hzsyyfh, item.hzsfh)
                            .setText(R.id.item_bcqh_shbzyczxqqk_cjczzgjbyilbxrs, item.czyilbxrs)
                            .setText(R.id.item_bcqh_shbzyczxqqk_cjcxjbyilbxrs, item.cxyilbxrs)
                            .setText(R.id.item_bcqh_shbzyczxqqk_cjczzgjbylbxrs, item.czylbxrs)
                            .setText(R.id.item_bcqh_shbzyczxqqk_cjcxjbylbxrs, item.cxylbxrs)
                            .setText(R.id.item_bcqh_shbzyczxqqk_zxyczzcxm, item.czzc)
                            .setText(R.id.item_bcqh_shbzyczxqqk_zxypxxm, item.pxxm)
                            .setText(R.id.item_bcqh_shbzyczxqqk_hdzcxxzyqd, item.zyqd)
                    if (item.czzc.equals("I")||item.czzc.equals("i")){
                        helper.setText(R.id.item_bcqh_shbzyczxqqk_zxyczzcxm, item.czzc+item.czzcbz)
                    }
                    if (item.pxxm.equals("F")||item.pxxm.equals("f")){
                        helper .setText(R.id.item_bcqh_shbzyczxqqk_zxypxxm, item.pxxm+item.pxxmbz)

                    }
                    if (item.zyqd.equals("H")||item.zyqd.equals("h")){
                        helper.setText(R.id.item_bcqh_shbzyczxqqk_hdzcxxzyqd, item.zyqd+item.zyqdbz)

                    }
                }

            }

        }

    }

    override fun returnBcjcjtImportData(msg: String) {

    }

    override fun returnBcqhUpload(msg: JtcyjcEntity) {
        if (msg != null)
            ToastUtils.showShort("修改成功")
        jtcyjcEntity.jtcyjbqkids.clear()
        jtcyjcEntity.jtjyccqk.jtncpids.clear()

        if (addJtcyUpPopu != null) {
            addJtcyUpPopu!!.dismiss()
        }
        if (addJtTdjyJCcqkUpPopu != null) {
            addJtTdjyJCcqkUpPopu!!.dismiss()
        }
        if (addJtSrzcqkUpPopu != null) {
            addJtSrzcqkUpPopu!!.dismiss()
        }
        if (addJtShbzyCzxqqkUpPopu != null) {
            addJtShbzyCzxqqkUpPopu!!.dismiss()
        }
        when (bcqhType) {
            2 -> {
                mPresenter.getBcqhDetail(bcqhCodeFlag, sortingFlag, idFlag)
            }
            3 -> {
                mPresenter.getBcqhDetail(bcqhCodeFlag, sortingFlag, msg.id)
            }
        }
    }

    override fun returnBcjcjtList(msg: List<JtcyjcEntity>) {

    }

    override fun returnBcjcYears(msg: List<String>) {
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }


}
