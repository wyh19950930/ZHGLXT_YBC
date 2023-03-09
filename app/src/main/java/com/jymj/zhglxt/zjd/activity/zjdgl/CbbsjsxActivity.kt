package com.jymj.zhglxt.zjd.activity.zjdgl

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.rjhj.bean.CjEntity
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter
import com.jymj.zhglxt.zjd.bean.QyfwBean
import com.jymj.zhglxt.zjd.bean.SxsxBean
import com.jymj.zhglxt.zjd.bean.bcjc.*
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean
import com.jymj.zhglxt.zjd.bean.wy.HousingRoom
import com.jymj.zhglxt.zjd.contract.AddUserContract
import com.jymj.zhglxt.zjd.contract.CbbsjsxContract
import com.jymj.zhglxt.zjd.presenter.AddUserPresenter
import com.jymj.zhglxt.zjd.presenter.CbbsjsxPresenter
import com.mcxtzhang.swipemenulib.SwipeMenuLayout
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.TimeUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration
import kotlinx.android.synthetic.main.activity_cbbsjsx.*
import kotlinx.android.synthetic.main.activity_property_add_fz.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class CbbsjsxActivity : BaseActivity<CbbsjsxPresenter, CbbsjsxContract.Model>(), CbbsjsxContract.View {

    var tcList1 = ArrayList<String>()// 时间范围 - 选中的时间
    var tcList2 = ArrayList<String>()// 表格筛选 - 选中的表格
    var tcList3 = ArrayList<String>()// 表格筛选 - 选中的表格
    var cbbsjsxQyfwValue: BaseQuickAdapter<QyfwBean, BaseViewHolder>? = null
    var cbbsjsxSxsxValue: BaseQuickAdapter<SxsxBean, BaseViewHolder>? = null
    val qyfwList = ArrayList<QyfwBean>()
    val sxsxList = ArrayList<SxsxBean>()
    var quList = ArrayList<String>()
    var zhenList = ArrayList<String>()
    var cunList = ArrayList<String>()

    var codeList =  ArrayList<String>()
    var years =  ArrayList<String>()
    var sxDtoList =  ArrayList<ReportSxDto>()
    var tableList =  ArrayList<String>()
    var tableList1 =  ArrayList<String>()


    override fun getLayoutId(): Int {
        return R.layout.activity_cbbsjsx
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mPresenter.getBcjcYears()///获取时间范围
        mPresenter.getXzqZhen()///请求行政区 树
        mPresenter.getBcjcGetOptions()///获取问卷类型
        iv_act_cbbsjsx_back.setOnClickListener {
            finish()
        }
        //搜索筛选
        mtl_act_cbbsjsx_srzc.setOnTitleListLister(object : MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")) {
                    if (ll_act_cbbsjsx_srzc.isShown) {
                        ll_act_cbbsjsx_srzc.visibility = View.GONE
                        mtl_act_cbbsjsx_srzc.setImageView(R.drawable.item_title_right)
                    } else {
                        ll_act_cbbsjsx_srzc.visibility = View.VISIBLE
                        mtl_act_cbbsjsx_srzc.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        //时间范围
        mtl_act_cbbsjsx_sjfw.setOnTitleListLister(object : MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")) {
                    if (ll_act_cbbsjsx_sjfw.isShown) {
                        ll_act_cbbsjsx_sjfw.visibility = View.GONE
                        mtl_act_cbbsjsx_sjfw.setImageView(R.drawable.item_title_right)
                    } else {
                        ll_act_cbbsjsx_sjfw.visibility = View.VISIBLE
                        mtl_act_cbbsjsx_sjfw.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })
        //区域范围
        mtl_act_cbbsjsx_qyfw.setOnTitleListLister(object : MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")) {
                    if (ll_act_cbbsjsx_qyfw.isShown) {
                        ll_act_cbbsjsx_qyfw.visibility = View.GONE
                        mtl_act_cbbsjsx_qyfw.setImageView(R.drawable.item_title_right)
                    } else {
                        ll_act_cbbsjsx_qyfw.visibility = View.VISIBLE
                        mtl_act_cbbsjsx_qyfw.setImageView(R.drawable.item_title_down)

                    }
                } else if (name.equals("添加")) {
                    if (xzqList.size>0){
                        if (xzqCunList.size == 0) {
                            qyfwList.add(QyfwBean())
                            cbbsjsxQyfwValue!!.setNewData(qyfwList)
                            cbbsjsxQyfwValue!!.notifyDataSetChanged()
                        } else {
                            ToastUtils.showShort("搜索结果已选择村名")
                        }
                    }else{
                        ToastUtils.showShort("正在加载...")
                    }
                }
            }
        })
        //属性筛查
        mtl_act_cbbsjsx_sxsc.setOnTitleListLister(object : MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")) {
                    if (ll_act_cbbsjsx_sxsc.isShown) {
                        ll_act_cbbsjsx_sxsc.visibility = View.GONE
                        mtl_act_cbbsjsx_sxsc.setImageView(R.drawable.item_title_right)
                    } else {
                        ll_act_cbbsjsx_sxsc.visibility = View.VISIBLE
                        mtl_act_cbbsjsx_sxsc.setImageView(R.drawable.item_title_down)

                    }
                } else if (name.equals("添加")) {
                    val sxsxBean = SxsxBean()

                   /* val sxsc = sxscList.get(0)
                    sxsxBean!!.setSxName(sxsc.getSxName())
                    sxsxBean!!.setSxdw(sxsc.getSxdw())
                    sxsxBean!!.setSxSllx(sxsc.getSxSllx())
                    sxsxBean!!.setSxTj(bjList.get(0).getSxTj())*/

                    sxsxList.add(SxsxBean())
                    cbbsjsxSxsxValue!!.setNewData(sxsxList)
                    cbbsjsxSxsxValue!!.notifyDataSetChanged()
                }
            }
        })
        //表格筛查
        mtl_act_cbbsjsx_bgsc.setOnTitleListLister(object : MeTitleLayout.OnTitleListLister {
            override fun onClick(name: String?) {
                if (name.equals("点击")) {
                    if (ll_act_cbbsjsx_bgsc.isShown) {
                        ll_act_cbbsjsx_bgsc.visibility = View.GONE
                        mtl_act_cbbsjsx_bgsc.setImageView(R.drawable.item_title_right)
                    } else {
                        ll_act_cbbsjsx_bgsc.visibility = View.VISIBLE
                        mtl_act_cbbsjsx_bgsc.setImageView(R.drawable.item_title_down)

                    }
                }
            }
        })

        var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo/"+AppCache.getInstance().name;
        val allDataFileName = listFileSortByModifyTime(filePath1)
        if (allDataFileName.size > 0) {
            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
        }
        tv_act_cbbsjsx_dclb.setOnClickListener {
            val allDataFileName1 = listFileSortByModifyTime(filePath1)
            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName1, object : PopuTjfxUtils.OnBgListLinear {
                override fun onClick(bgName: String?) {
                    var path = filePath1 + "/" + bgName
                    try {
                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                    } catch (e: Exception) {
                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                    }
                }

            })
        }

        rlv_act_cbbsjsx_qyfw.layoutManager = LinearLayoutManager(this@CbbsjsxActivity, LinearLayoutManager.VERTICAL, false)

        rlv_act_cbbsjsx_qyfw.isItemViewSwipeEnabled = false// 侧滑删除，默认关闭
        val itemDecoration = DefaultItemDecoration(Color.parseColor("#eeeeee"))
        rlv_act_cbbsjsx_qyfw.addItemDecoration(itemDecoration)
        cbbsjsxQyfwValue = object : BaseQuickAdapter<QyfwBean, BaseViewHolder>(R.layout.item_cbbsjsx_qyfw_layout, qyfwList) {
            override fun convert(helper: BaseViewHolder?, item: QyfwBean?) {
                val tvItemCbbsjsxQyfwQu = helper!!.getView<TextView>(R.id.tv_item_cbbsjsx_qyfw_qu)
                val tvItemCbbsjsxQyfwZhen = helper.getView<TextView>(R.id.tv_item_cbbsjsx_qyfw_zhen)
                val tvItemCbbsjsxQyfwCun = helper.getView<TextView>(R.id.tv_item_cbbsjsx_qyfw_cun)
                val ll_item_cbbsjsx_qyfw_qu = helper.getView<LinearLayout>(R.id.ll_item_cbbsjsx_qyfw_qu)
                val ll_item_cbbsjsx_qyfw_zhen = helper.getView<LinearLayout>(R.id.ll_item_cbbsjsx_qyfw_zhen)
                val ll_item_cbbsjsx_qyfw_cun = helper.getView<LinearLayout>(R.id.ll_item_cbbsjsx_qyfw_cun)
                val menuLayout = helper!!.getView<SwipeMenuLayout>(R.id.swlt_tv_item_cbbsjsx_qyfw)
                tvItemCbbsjsxQyfwQu.setText(xzqList.get(item!!.quPosition).name)
                tvItemCbbsjsxQyfwZhen.setText(xzqList.get(item!!.quPosition).children.get(item!!.zhenPosition).name)
                tvItemCbbsjsxQyfwCun.setText(xzqList.get(item!!.quPosition).children.get(item!!.zhenPosition).children.get(item.cunPosition).name)
                tvItemCbbsjsxQyfwQu.setOnClickListener {
                    CbbsjsxXzqPop.showPopupWindow(ll_item_cbbsjsx_qyfw_qu, this@CbbsjsxActivity, xzqList,
                            object : CbbsjsxXzqPop.OnCbbsjsxXzqLinear {
                        override fun onClick(positiobn: Int) {
                            item!!.quPosition = positiobn
                            item!!.quCode = xzqList.get(positiobn).code
                            tvItemCbbsjsxQyfwQu.setText(xzqList.get(positiobn).name)
                            item!!.zhenPosition = 0
                            tvItemCbbsjsxQyfwZhen.setText(xzqList.get(positiobn).children.get(0).name)
                            item!!.cunPosition = 0
                            tvItemCbbsjsxQyfwCun.setText(xzqList.get(positiobn).children.get(0).children.get(0).name)

                            /*if (item!!.quPosition!=-1){
                                xzqList.get(item!!.quPosition).isCheck = false
                            }
                            item!!.quPosition = positiobn
                            xzqList.get(positiobn).isCheck = true
                            tvItemCbbsjsxQyfwQu.setText(xzqList.get(positiobn).name)
                            selectXzqList(0,xzqList.get(positiobn).children)
                            tvItemCbbsjsxQyfwZhen.setText(xzqList.get(positiobn).children.get(0).name)
                            selectXzqList(0,xzqList.get(positiobn).children.get(0).children)
                            tvItemCbbsjsxQyfwCun.setText(xzqList.get(positiobn).children.get(0).children.get(0).name)*/
                        }
                    })

                }
                tvItemCbbsjsxQyfwZhen.setOnClickListener {
                    if (item!!.getQuPosition() != -1)
                        CbbsjsxXzqPop.showPopupWindow(ll_item_cbbsjsx_qyfw_zhen, this@CbbsjsxActivity, xzqList.get(item!!.quPosition).children,
                                object : CbbsjsxXzqPop.OnCbbsjsxXzqLinear {
                            override fun onClick(positiobn: Int) {
                                item!!.zhenPosition = positiobn
                                item!!.zhenCode = xzqList.get(item.quPosition).children.get(positiobn).code
                                tvItemCbbsjsxQyfwZhen.setText(xzqList.get(item.quPosition).children.get(positiobn).name)
                                item!!.cunPosition = 0
                                tvItemCbbsjsxQyfwCun.setText(xzqList.get(item.quPosition).children.get(positiobn).children.get(0).name)
                            }
                        })

                }
                tvItemCbbsjsxQyfwCun.setOnClickListener {
                    if (item!!.getQuPosition() != -1 && item!!.getZhenPosition() != -1)
                        CbbsjsxXzqPop.showPopupWindow(ll_item_cbbsjsx_qyfw_cun, this@CbbsjsxActivity, xzqList.get(item!!.quPosition).children.get(item!!.zhenPosition).children,
                                object : CbbsjsxXzqPop.OnCbbsjsxXzqLinear {
                            override fun onClick(positiobn: Int) {
                                item!!.cunPosition = positiobn
                                item!!.cunCode = xzqList.get(item.quPosition).children.get(item.zhenPosition).children.get(positiobn).code
                                tvItemCbbsjsxQyfwCun.setText(xzqList.get(item.quPosition).children.get(item.zhenPosition).children.get(positiobn).name)
                            }
                        })

                }


                helper!!.getView<TextView>(R.id.tv_item_cbbsjsx_qyfw_det).setOnClickListener {
                    qyfwList.removeAt(helper.layoutPosition)
                    notifyDataSetChanged()
                }


            }
        }

        rlv_act_cbbsjsx_qyfw.adapter = cbbsjsxQyfwValue
        rlv_act_cbbsjsx_sxsc.layoutManager = LinearLayoutManager(this@CbbsjsxActivity, LinearLayoutManager.VERTICAL, false)

        rlv_act_cbbsjsx_sxsc.isItemViewSwipeEnabled = false// 侧滑删除，默认关闭
        val itemDecoration1 = DefaultItemDecoration(Color.parseColor("#eeeeee"))
        rlv_act_cbbsjsx_sxsc.addItemDecoration(itemDecoration1)
        cbbsjsxSxsxValue = object : BaseQuickAdapter<SxsxBean, BaseViewHolder>(R.layout.item_cbbsjsx_sxsx_layout, sxsxList) {
            override fun convert(helper: BaseViewHolder?, item: SxsxBean?) {
                //swlt_tv_item_cbbsjsx_qyfw
                //tv_item_cbbsjsx_qyfw_det
                val tv_item_cbbsjsx_sxsx_sxmc = helper!!.getView<TextView>(R.id.tv_item_cbbsjsx_sxsx_sxmc)
                val tv_item_cbbsjsx_sxsc_tj = helper!!.getView<TextView>(R.id.tv_item_cbbsjsx_sxsc_tj)
                val tv_item_cbbsjsx_sxsx_sl = helper!!.getView<TextView>(R.id.tv_item_cbbsjsx_sxsx_sl)
                val et_item_cbbsjsx_sxsx_sl = helper!!.getView<EditText>(R.id.et_item_cbbsjsx_sxsx_sl)
                val tv_item_cbbsjsx_sxsx_time = helper!!.getView<TextView>(R.id.tv_item_cbbsjsx_sxsx_time)
                val ll_item_cbbsjsx_sxsx_sxmc = helper!!.getView<LinearLayout>(R.id.ll_item_cbbsjsx_sxsx_sxmc)
                val ll_item_cbbsjsx_sxsc_tj = helper!!.getView<LinearLayout>(R.id.ll_item_cbbsjsx_sxsc_tj)
                tv_item_cbbsjsx_sxsx_sxmc.setText(item!!.getSxName())
                tv_item_cbbsjsx_sxsc_tj.setText(item.sxTj)
                et_item_cbbsjsx_sxsx_sl.setText(item.sxSl)

                tv_item_cbbsjsx_sxsx_sxmc.setOnClickListener {

                    CbbsjsxXzqPop.showPopupWindowSxmc(ll_item_cbbsjsx_sxsx_sxmc, this@CbbsjsxActivity, sxscList,
                            object : CbbsjsxXzqPop.OnCbbsjsxXzqLinear {
                        override fun onClick(positiobn: Int) {
                            val sxsc = sxscList.get(positiobn)
                            item!!.setSxName(sxsc.getSxName())
                            item!!.setSxdw(sxsc.getSxdw())
                            item!!.setSxSllx(sxsc.getSxSllx())
                            item!!.setSxTj("")
                            item!!.setSxSl("")
                            tv_item_cbbsjsx_sxsx_sxmc.setText(sxsc.getSxName())
                            tv_item_cbbsjsx_sxsc_tj.setText("")
                            et_item_cbbsjsx_sxsx_sl.setText("")
                            tv_item_cbbsjsx_sxsx_sl.setText(sxsc.getSxdw())
                            if (sxsc.getSxName().equals("集体经济组织成立时间")) {
                                tv_item_cbbsjsx_sxsc_tj.setOnClickListener {
                                    CbbsjsxXzqPop.showPopupWindowSxbj(ll_item_cbbsjsx_sxsc_tj, this@CbbsjsxActivity,
                                            timeBjList, object : CbbsjsxXzqPop.OnCbbsjsxXzqLinear {
                                        override fun onClick(positiobn: Int) {
                                            item!!.setSxTj(timeBjList.get(positiobn).getSxTj())
                                            tv_item_cbbsjsx_sxsc_tj.setText(timeBjList.get(positiobn).getSxTj())
                                        }
                                    })
                                }
                            } else {
                                tv_item_cbbsjsx_sxsc_tj.setOnClickListener {
                                    CbbsjsxXzqPop.showPopupWindowSxbj(ll_item_cbbsjsx_sxsc_tj, this@CbbsjsxActivity, bjList, object : CbbsjsxXzqPop.OnCbbsjsxXzqLinear {
                                        override fun onClick(positiobn: Int) {
                                            item!!.setSxTj(bjList.get(positiobn).getSxTj())
                                            tv_item_cbbsjsx_sxsc_tj.setText(bjList.get(positiobn).getSxTj())
                                        }
                                    })
                                }
                            }
                            if (sxsc.sxSllx.equals("整数")) {
                                et_item_cbbsjsx_sxsx_sl.visibility = View.VISIBLE
                                tv_item_cbbsjsx_sxsx_time.visibility = View.GONE
//                                et_item_cbbsjsx_sxsx_sl .setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                                et_item_cbbsjsx_sxsx_sl.setInputType(InputType.TYPE_CLASS_NUMBER);
                                et_item_cbbsjsx_sxsx_sl.setOnClickListener {
                                }

                                et_item_cbbsjsx_sxsx_sl.addTextChangedListener(object : TextWatcher {
                                    override fun afterTextChanged(p0: Editable?) {
                                        if (!et_item_cbbsjsx_sxsx_sl.text.toString().equals("")) {
                                            item!!.setSxSl(et_item_cbbsjsx_sxsx_sl.text.toString())
                                        } else {
                                            item!!.setSxSl("0")
                                        }
                                    }

                                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                    }

                                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                    }
                                })
                            } else
                                if (sxsc.sxSllx.equals("小数")) {
                                    et_item_cbbsjsx_sxsx_sl.visibility = View.VISIBLE
                                    tv_item_cbbsjsx_sxsx_time.visibility = View.GONE
//                                et_item_cbbsjsx_sxsx_sl .setKeyListener(DigitsKeyListener.getInstance("0123456789."))
                                    //InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                                    et_item_cbbsjsx_sxsx_sl.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL);
//                                et_item_cbbsjsx_sxsx_sl.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL//InputType.TYPE_CLASS_NUMBER ;
//                                mEditText.setKeyListener(DigitsKeyListener.getInstance("123456789."));
//                                et_item_cbbsjsx_sxsx_sl.inputType =InputType.TYPE_CLASS_NUMBER;InputType.TYPE_NUMBER_FLAG_DECIMAL
                                    et_item_cbbsjsx_sxsx_sl.setOnClickListener {
                                    }

                                    et_item_cbbsjsx_sxsx_sl.addTextChangedListener(object : TextWatcher {
                                        override fun afterTextChanged(p0: Editable?) {
                                            val toString = et_item_cbbsjsx_sxsx_sl.text.toString()
                                            val split = toString.split(".")
                                            if (split.size > 1) {
                                                if (split[1].length > 2) {
                                                    item!!.setSxSl(split[0] + "." + split[1].substring(0, 2))
                                                    val length = toString.length

                                                    et_item_cbbsjsx_sxsx_sl.getText().delete(length - 1, length);
//                                                et_item_cbbsjsx_sxsx_sl.setText(split[0]+"."+split[1].substring(0,2))
                                                } else {
                                                    item!!.setSxSl(et_item_cbbsjsx_sxsx_sl.text.toString())
                                                }
                                            } else {
                                                if (!et_item_cbbsjsx_sxsx_sl.text.toString().equals("")) {
                                                    item!!.setSxSl(et_item_cbbsjsx_sxsx_sl.text.toString())
                                                } else {
                                                    item!!.setSxSl("")
                                                }
                                            }


                                        }

                                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                        }

                                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                        }
                                    })
                                } else
                                    if (sxsc.sxSllx.equals("年")) {
//                                et_item_cbbsjsx_sxsx_sl.inputType = InputType.TYPE_CLASS_NUMBER
                                    et_item_cbbsjsx_sxsx_sl.visibility = View.GONE
                                    tv_item_cbbsjsx_sxsx_time.visibility = View.VISIBLE
                                    tv_item_cbbsjsx_sxsx_time.setOnClickListener {

                                        TimePickerUtil.initTimePickerViewN(this@CbbsjsxActivity,
                                                tv_item_cbbsjsx_sxsx_time.text.toString(),object: TimePickerUtil.OnTimePickerLister{
                                            override fun onClick(data: String?) {

                                                item!!.setSxSl(data)
                                                tv_item_cbbsjsx_sxsx_time.setText(data)
                                            }
                                        })
                                        /*val arrayList = ArrayList<String>()
                                        arrayList.addAll(yearList)

                                        //条件选择器
                                        val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(this@CbbsjsxActivity, OnOptionsSelectListener { options1, option2, options3, v ->
                                            //                        setCheck(quList,options1)

                                            et_item_cbbsjsx_sxsx_sl.setText(arrayList.get(options1))
                                            item.setSxSl(arrayList.get(options1))


//                        getZhenList(xzqList,quList.get(options1).code)
                                        })
                                                .isDialog(true)
                                                .isAlphaGradient(true)
                                                .build<String>()
                                        pvOptions.setSelectOptions(arrayList.indexOf(et_item_cbbsjsx_sxsx_sl.toString()))
                                        pvOptions.setPicker(arrayList)

                                        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
                                        params.leftMargin = 0
                                        params.rightMargin = 0
                                        val contentContainer = pvOptions.getDialogContainerLayout()
                                        contentContainer.setLayoutParams(params)
                                        pvOptions.getDialog().getWindow()!!.setGravity(Gravity.BOTTOM)
                                        pvOptions.getDialog().getWindow()!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                                        pvOptions.show()*/
                                    }

                                    et_item_cbbsjsx_sxsx_sl.addTextChangedListener(object : TextWatcher {
                                        override fun afterTextChanged(p0: Editable?) {
                                            if (!et_item_cbbsjsx_sxsx_sl.text.toString().equals("")) {
                                                item!!.setSxSl(et_item_cbbsjsx_sxsx_sl.text.toString())
                                            } else {
                                                item!!.setSxSl("")
                                            }
                                        }

                                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                        }

                                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                        }
                                    })
                                }

//                            notifyDataSetChanged()
                        }
                    })

                }

//                val menuLayout = helper!!.getView<SwipeMenuLayout>(R.id.swlt_tv_item_cbbsjsx_sxsx)
                helper!!.getView<TextView>(R.id.tv_item_cbbsjsx_sxsx_det).setOnClickListener {
                    sxsxList.removeAt(helper.layoutPosition)
                    notifyDataSetChanged()
                }


            }
        }

        rlv_act_cbbsjsx_sxsc.adapter = cbbsjsxSxsxValue

        //重置筛选条件
        but_act_cbbsjsx_czsctj.setOnClickListener {
            codeList.clear()
            years.clear()
            sxDtoList.clear()
            tableList.clear()
            et_act_user_list_search.setText("")
            xzqCunList.clear()//搜索结果   村名
            if (cunValue != null) {
                cunValue!!.notifyDataSetChanged()
            }
            for (i in sjqjList) {
                i.isCheck = false
            }
            if (sjqjapterxl != null) {
                sjqjapterxl!!.notifyDataSetChanged()
            }
            qyfwList.clear()//区域范围
            if (cbbsjsxQyfwValue != null) {
                cbbsjsxQyfwValue!!.notifyDataSetChanged()
            }
            sxsxList.clear()//属性筛查
            if (cbbsjsxSxsxValue != null) {
                cbbsjsxSxsxValue!!.notifyDataSetChanged()
            }
            for (i in arrayList1) {
                i.isCheck = false
            }
            if (bgsc2Value != null) {
                rlv_act_cbbsjsx_bgsc2.layoutManager = LinearLayoutManager(this@CbbsjsxActivity, LinearLayoutManager.VERTICAL, false)
                val arrayList = ArrayList<String>()
                arrayList.add("")

                for (i in arrayList2) {
                    i.isCheck = false
                }
                arrayList2.clear()//表格筛查
                bgsc2Value = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_cbbsjsx_layout, arrayList) {
                    override fun convert(helper: BaseViewHolder?, item: String?) {
                        val item_cbbsjsx_layout2 = helper!!.getView<TagCloudLayout>(R.id.item_cbbsjsx_layout2)
                        mAdapterx2 = TagBaseAdapter(mContext, arrayList2) //createData(mListxl, xl)

                        item_cbbsjsx_layout2.setAdapter(mAdapterx2)
                        item_cbbsjsx_layout2.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
                            override fun itemClick(position2: Int) {
                                changeState2(arrayList2, position2)
                                mAdapterx2!!.notifyDataSetChanged()
                            }
                        })

                    }

                }
                rlv_act_cbbsjsx_bgsc2.adapter = bgsc2Value
            }
            if (mAdapterxl != null) {
                mAdapterxl!!.notifyDataSetChanged()
            }


        }
        //开始筛选
        but_act_cbbsjsx_kssc.setOnClickListener {
            //            xzqCunList   村名
//            sjqjList   时间范围  true
//            qyfwList   区域范围
//            sxsxList   属性筛选
//            arrayList2   表格筛选  true
            val arrayList = ArrayList<String>()
            for (i in sjqjList) {
                if (i.isCheck) {
                    arrayList.add(i.text)
                }
            }
            val arrayList1 = ArrayList<String>()
            for (i in arrayList2) {
                if (i.isCheck) {
                    arrayList1.add(i.text)
                }
            }
            if (xzqCunList.size == 0 && qyfwList.size == 0) {
                ToastUtils.showShort("请选择行政区")
            } else if (arrayList.size == 0) {
                ToastUtils.showShort("请选择时间范围")
            } /*else if (sxsxList.size == 0) {
                ToastUtils.showShort("请选择属性筛查")
            } */else if (arrayList1.size == 0) {
                ToastUtils.showShort("请选择表格筛查")
            } else {
                codeList.clear()
                years.clear()
                sxDtoList.clear()
                tableList.clear()
                tableList1.clear()
                //时间范围
                if (Gson().toJson(arrayList).contains("全部")){
                    for (i in sjqjList) {
                        if (!i.text.equals("全部")) {
                            years.add(i.text)
                        }
                    }
                }else{
                    years.addAll(arrayList)
                }
                //搜索范围
                if (xzqCunList.size>0){
                    for (i in xzqCunList){
                        codeList.add(i.code)
                    }
                }
                //行政区范围
                if (qyfwList.size>0){
                    for (i in qyfwList){
                        if (i.getQuPosition()==0){
                            codeList.add("100")
                        }else{
                            if (i.getZhenPosition()==0){
                                codeList.add(i.getQuCode())
                            }else{
                                if (i.cunPosition==0){
                                    codeList.add(i.getZhenCode())
                                }else{
                                    codeList.add(i.getCunCode())

                                }

                            }
                        }
                    }
                }
                //属性集合
                if (sxsxList.size>0){//sxDtoList
                    for (i in sxsxList){
                        val reportSxDto = ReportSxDto(i.getSxName(),i.sxTjInt,i.sxSl)
                        sxDtoList.add(reportSxDto)
                    }
                }
                //表格集合
                if (arrayList1.size>0){
                    tableList.addAll(arrayList1)
                    for (i in arrayList1){
                        /*if (i.equals("土地征占情况")){
                            tableList1.add("土地征地情况")
                            tableList1.add("土地占地情况")
                        }else{
                            tableList1.add(i)
                        }*/
                        tableList1.add(i)
                    }
                }

                initPopuXzbb(arrayList, arrayList1)
            }


        }


    }//

    override fun initDatas() {
        //请求村名
        var cunEdit = ""
        et_act_user_list_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val toString = s.toString()
                if (!toString.equals(cunEdit)){
                    if (qyfwList.size > 0 && !toString.equals("")) {
                        ToastUtils.showShort("已在区域范围添加行政区")
                        et_act_user_list_search.setText("")
                    } else {
                        mPresenter.getQueryCun(toString)
                    }
                }
                cunEdit = toString
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        initBgsx()

    }

    private fun selectXzqList(positon: Int, xzqqList: List<SysXzqEntity>) {
        for (i in xzqqList.indices) {
            xzqqList.get(i).isCheck = false
            if (positon == i) {
                xzqqList.get(i).isCheck = true
            }
        }
    }

    val sxscList = ArrayList<SxsxBean>()
    val bjList = ArrayList<SxsxBean>()
    val timeBjList = ArrayList<SxsxBean>()
    //初始化表格
//    var bgsc1Value: BaseQuickAdapter<String, BaseViewHolder>? = null
    var bgsc2Value: BaseQuickAdapter<String, BaseViewHolder>? = null
    val arrayList2 = ArrayList<JsjbBean>()
    val arrayList1 = ArrayList<JsjbBean>()
    var mAdapterxl: TagBaseAdapter? = null
    var mAdapterx2: TagBaseAdapter? = null
    private fun initBgsx() {
        var titleList = ArrayList<List<JsjbBean>>()// 表格名称
        val stringList = ArrayList<JsjbBean>()
        stringList.add(JsjbBean(0, "人口变动情况", false))
        stringList.add(JsjbBean(1, "劳动力变动情况表", false))
        stringList.add(JsjbBean(2, "外出务工人员就业结构变动情况表", false))
        stringList.add(JsjbBean(3, "村干部基本情况", false))
        titleList.add(stringList)
        val stringList1 = ArrayList<JsjbBean>()
        stringList1.add(JsjbBean(4, "村集体土地资源情况", false))
        stringList1.add(JsjbBean(5, "村域内2000年以来土地征占情况", false))
//        stringList1.add(JsjbBean(6, "占地情况", false))
        titleList.add(stringList1)
        val stringList2 = ArrayList<JsjbBean>()
        stringList2.add(JsjbBean(7, "产业变动情况", false))
        titleList.add(stringList2)
        val stringList3 = ArrayList<JsjbBean>()
        stringList3.add(JsjbBean(8, "村集体经济组织治理及资产经营情况", false))//集体资产情况
        titleList.add(stringList3)
        val stringList4 = ArrayList<JsjbBean>()
        stringList4.add(JsjbBean(9, "村经济总收入情况", false))
        stringList4.add(JsjbBean(10, "村集体经济组织主要收入情况", false))
        stringList4.add(JsjbBean(11, "村集体经济组织主要支出情况", false))
        titleList.add(stringList4)
        val stringList5 = ArrayList<JsjbBean>()
        stringList5.add(JsjbBean(12, "村集体经济运行情况监测问卷", false))
        titleList.add(stringList5)

        //rvl_act_cbbsjsx_bgsc1

//        rlv_act_cbbsjsx_bgsc1.layoutManager = LinearLayoutManager(this@CbbsjsxActivity, LinearLayoutManager.VERTICAL, false)
        val arrayList11 = ArrayList<String>()
        arrayList11.add("")


//                arrayList2.clear()
        arrayList1.add(JsjbBean(0, "人口就业", false))//i
        arrayList1.add(JsjbBean(1, "土地利用", false))//i
        arrayList1.add(JsjbBean(2, "产业结构", false))//i
        arrayList1.add(JsjbBean(3, "资产经营", false))//i
        arrayList1.add(JsjbBean(4, "收入支出", false))//i
        arrayList1.add(JsjbBean(5, "发展意愿", false))//i
        mAdapterxl = TagBaseAdapter(mContext, arrayList1) //createData(mListxl, xl)
        tcl_act_cbbsjsx_bgsc1.setAdapter(mAdapterxl)
        tcl_act_cbbsjsx_bgsc1.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
            override fun itemClick(position1: Int) {
                for (i in titleList.get(position1)) {
                    if (Gson().toJson(arrayList2).contains(i.text)) {
                        i.isCheck = false
                        tcList3.remove(i.text)
                        arrayList2.remove(i)
                    } else {
                        arrayList2.add(i)
                    }
                }
                rlv_act_cbbsjsx_bgsc2.layoutManager = LinearLayoutManager(this@CbbsjsxActivity, LinearLayoutManager.VERTICAL, false)
                val arrayList = ArrayList<String>()
                arrayList.add("")
                bgsc2Value = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_cbbsjsx_layout, arrayList) {
                    override fun convert(helper: BaseViewHolder?, item: String?) {
                        val item_cbbsjsx_layout2 = helper!!.getView<TagCloudLayout>(R.id.item_cbbsjsx_layout2)
                        mAdapterx2 = TagBaseAdapter(mContext, arrayList2) //createData(mListxl, xl)

                        item_cbbsjsx_layout2.setAdapter(mAdapterx2)
                        item_cbbsjsx_layout2.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
                            override fun itemClick(position2: Int) {
                                changeState2(arrayList2, position2)
                                mAdapterx2!!.notifyDataSetChanged()
                            }
                        })

                    }

                }
                rlv_act_cbbsjsx_bgsc2.adapter = bgsc2Value
                changeState1(arrayList1, position1)
                mAdapterxl!!.notifyDataSetChanged()


            }
        })




        sxscList.add(SxsxBean(0, "全村户籍人口", "", "整数"))
        sxscList.add(SxsxBean(1, "本村户籍常住人口", "", "整数"))
        sxscList.add(SxsxBean(2, "实际户籍就业劳动力总数", "", "整数"))
        sxscList.add(SxsxBean(3, "长期外出务工人员总数", "", "整数"))
        sxscList.add(SxsxBean(4, "农用地", "亩", "小数"))
        sxscList.add(SxsxBean(5, "耕地", "亩", "小数"))
        sxscList.add(SxsxBean(6, "园地", "亩", "小数"))
        sxscList.add(SxsxBean(7, "林地", "亩", "小数"))
        sxscList.add(SxsxBean(8, "设施农用地", "亩", "小数"))
        sxscList.add(SxsxBean(9, "建设用地", "亩", "小数"))
        sxscList.add(SxsxBean(10, "居住用地", "亩", "小数"))
        sxscList.add(SxsxBean(11, "集体经营性建设用地", "亩", "小数"))
        sxscList.add(SxsxBean(12, "土地总面积", "亩", "小数"))
        sxscList.add(SxsxBean(13, "农村住宅用地出租", "宗", "整数"))
        sxscList.add(SxsxBean(14, "征地面积", "亩", "小数"))
        sxscList.add(SxsxBean(15, "公益性征地", "亩", "小数"))
        sxscList.add(SxsxBean(16, "开发建设征地", "亩", "小数"))
        sxscList.add(SxsxBean(17, "占地面积", "亩", "小数"))
        sxscList.add(SxsxBean(18, "基础设施占地", "亩", "小数"))
        sxscList.add(SxsxBean(19, "军队政府占地", "亩", "小数"))
        sxscList.add(SxsxBean(20, "开发区占地", "亩", "小数"))
        sxscList.add(SxsxBean(21, "第一产业收入", "万元", "小数"))
        sxscList.add(SxsxBean(22, "种植业收入", "万元", "小数"))
        sxscList.add(SxsxBean(23, "林果业收入", "万元", "小数"))
        sxscList.add(SxsxBean(24, "畜牧业收入", "万元", "小数"))
        sxscList.add(SxsxBean(25, "渔业收入", "万元", "小数"))
        sxscList.add(SxsxBean(26, "第二产业收入", "万元", "小数"))
        sxscList.add(SxsxBean(27, "第三产业收入", "万元", "小数"))
        sxscList.add(SxsxBean(28, "集体经济组织成立时间", "年", "年"))//年
        sxscList.add(SxsxBean(29, "总资产", "万元", "小数"))
        sxscList.add(SxsxBean(30, "净资产", "万元", "小数"))
        sxscList.add(SxsxBean(31, "经营性收入", "万元", "小数"))
        sxscList.add(SxsxBean(32, "利润总额", "万元", "小数"))
        sxscList.add(SxsxBean(33, "净利润", "万元", "小数"))
        sxscList.add(SxsxBean(34, "股金分红总额", "万元", "小数"))
        sxscList.add(SxsxBean(35, "福利分红总额", "万元", "小数"))
        sxscList.add(SxsxBean(36, "村经济总收入", "万元", "小数"))
        sxscList.add(SxsxBean(37, "主营业务收入", "万元", "小数"))
        sxscList.add(SxsxBean(38, "其他业务收入", "万元", "小数"))
        sxscList.add(SxsxBean(39, "投资收益", "万元", "小数"))
        sxscList.add(SxsxBean(40, "补贴收入", "万元", "小数"))
        sxscList.add(SxsxBean(41, "营业外收入", "万元", "小数"))

        bjList.add(SxsxBean(0, ">"))
        bjList.add(SxsxBean(1, "<"))
        bjList.add(SxsxBean(2, "="))
        bjList.add(SxsxBean(3, "≥"))
        bjList.add(SxsxBean(4, "≤"))
        timeBjList.add(SxsxBean(0, "之前"))
        timeBjList.add(SxsxBean(1, "之后"))


    }

    override fun returnUserInfo(message: SysUserEntity) {

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

    override fun returnAddUser(message: String) {

    }

    override fun returnUpdateUser(message: String) {
    }

    //    var tclbValue: BaseQuickAdapter<String, BaseViewHolder>? = null
    var sjqjapterxl: TagBaseAdapter? = null
    val sjqjList = ArrayList<JsjbBean>()
    val yearList = ArrayList<String>()
    override fun returnBcjcYears(msg: List<String>) {
        sjqjList.clear()
        yearList.clear()
        yearList.addAll(msg)
        sjqjList.add(JsjbBean(0, "全部", false))//i
//        sjqjList.add(JsjbBean(0, "2021年", false))//i
        for (i in 0..msg.size - 1) {
            sjqjList.add(JsjbBean(i + 1, msg.get(i), false))//i
        }
        sjqjapterxl = TagBaseAdapter(mContext, sjqjList) //createData(mListxl, xl)

        tcl_act_cbbsjsx_tclb.setAdapter(sjqjapterxl)
        tcl_act_cbbsjsx_tclb.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
            override fun itemClick(position1: Int) {
                changeState(sjqjList, position1)
                sjqjapterxl!!.notifyDataSetChanged()
            }
        })

    }

    private var xzqCunList = ArrayList<SysXzqEntity>()
    //請求村
    var cunValue: BaseQuickAdapter<SysXzqEntity, BaseViewHolder>? = null

    override fun returnQueryCun(message: ArrayList<SysXzqEntity>) {
//        ToastUtils.showShort(Gson().toJson(message))
        //ArrayAdapter<String>
        val arrayList = ArrayList<String>()
        for (i in message){
            arrayList.add(i.name)
        }
        var adapter = ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,arrayList);

        et_act_user_list_search.setAdapter(adapter);
        et_act_user_list_search.setOnItemClickListener { parent, view, position, id ->
            et_act_user_list_search.setText("")
            if (!Gson().toJson(xzqCunList).contains(message.get(position).code)) {
                xzqCunList.add(message.get(position))
            }
            rlv_act_cbbsjsx_ssjg.layoutManager = GridLayoutManager(this@CbbsjsxActivity, 4)

            cunValue = object : BaseQuickAdapter<SysXzqEntity, BaseViewHolder>(R.layout.item_cbbsjsx_cun_layout, xzqCunList) {
                override fun convert(helper: BaseViewHolder?, item: SysXzqEntity?) {
                    helper!!.setText(R.id.tv_item_cbbsjsx_cunname, item!!.name)
                    val delete = helper.getView<ImageView>(R.id.tv_item_cbbsjsx_cun_delete)
                    if (mtl_act_cbbsjsx_qyfw.isShown){
                        mtl_act_cbbsjsx_qyfw.visibility = View.GONE
                        ll_act_cbbsjsx_qyfw.visibility = View.GONE
                    }
                    delete.setOnClickListener {
                        xzqCunList.removeAt(helper.adapterPosition)
                        if (xzqCunList.size==0){
                            mtl_act_cbbsjsx_qyfw.visibility = View.VISIBLE
                            ll_act_cbbsjsx_qyfw.visibility = View.VISIBLE
                        }
                        notifyDataSetChanged()
                    }

                }
            }
            rlv_act_cbbsjsx_ssjg.adapter = cunValue

        }
        //设置分隔符
        et_act_user_list_search.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer());
        // 显示下拉框
        et_act_user_list_search.showDropDown();

        /*CbbsjsxXzqPop.showPopupWindowCun(et_act_user_list_search, this, message, object : CbbsjsxXzqPop.OnCbbsjsxXzqLinear {
            override fun onClick(positiobn: Int) {
                et_act_user_list_search.setText("")
                if (!Gson().toJson(xzqCunList).contains(message.get(positiobn).code)) {
                    xzqCunList.add(message.get(positiobn))

                }
                rlv_act_cbbsjsx_ssjg.layoutManager = GridLayoutManager(this@CbbsjsxActivity, 4)

                cunValue = object : BaseQuickAdapter<SysXzqEntity, BaseViewHolder>(R.layout.item_cbbsjsx_cun_layout, xzqCunList) {
                    override fun convert(helper: BaseViewHolder?, item: SysXzqEntity?) {
                        helper!!.setText(R.id.tv_item_cbbsjsx_cunname, item!!.name)
                        val delete = helper.getView<ImageView>(R.id.tv_item_cbbsjsx_cun_delete)
                        if (mtl_act_cbbsjsx_qyfw.isShown){
                            mtl_act_cbbsjsx_qyfw.visibility = View.GONE
                            ll_act_cbbsjsx_qyfw.visibility = View.GONE
                        }
                        delete.setOnClickListener {
                            xzqCunList.removeAt(helper.adapterPosition)
                            if (xzqCunList.size==0){
                                mtl_act_cbbsjsx_qyfw.visibility = View.VISIBLE
                                ll_act_cbbsjsx_qyfw.visibility = View.VISIBLE
                            }
                            notifyDataSetChanged()
                        }

                    }
                }
                rlv_act_cbbsjsx_ssjg.adapter = cunValue

            }
        })*/

    }

    var xzqList = ArrayList<SysXzqEntity>()
    override fun returnXzqZhen(message: List<SysXzqEntity>) {
        xzqList.clear()
        val sysXzqEntity = SysXzqEntity()
        sysXzqEntity.name = "全部"
        xzqList.add(sysXzqEntity)
        if (message.size > 0) {
            xzqList.addAll(message.get(0).children)
        }
        for (i in xzqList) {
            val sysXzqEntity = SysXzqEntity()
            sysXzqEntity.name = "全部"
            i.children.add(0, sysXzqEntity)
        }
        for (i in xzqList) {
            for (f in i.children) {
                val sysXzqEntity = SysXzqEntity()
                sysXzqEntity.name = "全部"
                f.children.add(0, sysXzqEntity)
            }
        }
        /*val toJson = Gson().toJson(xzqList)
        ToastUtils.showShort("")*/

    }

    override fun returnBcjcGetReprot(message: HashMap<String, java.util.ArrayList<Object>>) {//BcjcBbBean

        //            ToastUtils.showShort("下载")
        var filePath = AppMenus.getInstance().cardPath + "/AndroidExcelDemo/"+AppCache.getInstance().name;
        var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo/"+AppCache.getInstance().name;
        var file = File(filePath)
        if (!file.exists()) {
            file.mkdirs()
        }
        var excelFileName = "/demo.xls";
//        var title = arrayOf("姓名", "年龄", "男孩")
        val title = java.util.ArrayList<String>()
        title.add("")//姓名
//        title.add("年龄")
//        title.add("男孩")
        var sheetName = "demoSheetName";
       /* var demoBeanList = ArrayList<ProjectBean>();
        var demoBean1 = ProjectBean("张三", "10", "33", "33", "33", "33", "4156");
        var demoBean2 = ProjectBean("小红", "12", "54", "54", "54", "4156", "54");
        var demoBean3 = ProjectBean("李四", "18", "485", "485", "485", "4156", "54");
        var demoBean4 = ProjectBean("王香", "13", "4156", "4156", "4156", "4156", "4156");
        demoBeanList.add(demoBean1);
        demoBeanList.add(demoBean2);
        demoBeanList.add(demoBean3);
        demoBeanList.add(demoBean4);*/
        for (i in tableList.indices) {//excelFileName    表名  当前时间  后缀
            val bgName = tableList.get(i)
//            var demoBeanList = ArrayList<BcjcBbBean>()
            var get = message.get(bgName)
            var filePath2 = filePath1 + "/" + bgName + TimeUtils.getNowString() + ".xls";//.split(" ")[0]
            ExcelUtil.initExcel(this, filePath2, title) //, arrayOf(sheetName)
            if (bgName.contains("人口变动情况")) {
                ExcelUtil.writeObjListToExcel1(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                    }
                });
            } else if (bgName.contains("劳动力变动情况表")) {
                ExcelUtil.writeObjListToExcelLdlbdqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                    }
                });
            } else if (bgName.contains("外出务工人员就业结构变动情况表")) {
                ExcelUtil.writeObjListToExcelWcwgryjyjgbdqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })


                        }
                    }
                });
            } else if (bgName.contains("村干部基本情况")) {
                ExcelUtil.writeObjListToExcelCgbjbqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } else if (bgName.contains("村集体土地资源情况")) {
                ExcelUtil.writeObjListToExcelCjttdzyqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } else if (bgName.contains("村域内2000年以来土地征占情况")) {
                ExcelUtil.writeObjListToExcelTdzzqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } else if (bgName.contains("产业变动情况")) {
                ExcelUtil.writeObjListToExcelCjtcybd(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } else if (bgName.contains("村集体经济组织治理及资产经营情况")) {//集体资产情况
                ExcelUtil.writeObjListToExcelJtzcqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } else if (bgName.contains("村经济总收入情况")) {
                ExcelUtil.writeObjListToExcelCjjzsrqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } else if (bgName.contains("村集体经济组织主要收入情况")) {
                ExcelUtil.writeObjListToExcelCjtjjzzzysrqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } else if (bgName.contains("村集体经济组织主要支出情况")) {
                ExcelUtil.writeObjListToExcelCjtjjzzzyzcqk(get, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            }else if (bgName.contains("村集体经济运行情况监测问卷")) {
                ExcelUtil.writeObjListToExcelFzyy(get,optionsEntitys,years, filePath2, this, object : ExcelUtil.OnOpenFileLinear {
                    //writeObjListToExcel
                    override fun onOpenFile() {
                        if (i == tableList.size - 1) {//最后一个
                            tv_act_cbbsjsx_dclb.visibility = View.VISIBLE
//                            val stringList = ArrayList<String>()
                            val allDataFileName = listFileSortByModifyTime(filePath1)
                            PopuTjfxUtils.getInstance().initPopuBg(this@CbbsjsxActivity, ll_act_cbbsjsx, allDataFileName, object : PopuTjfxUtils.OnBgListLinear {
                                override fun onClick(bgName: String?) {
                                    var path = filePath1 + "/" + bgName
                                    try {
                                        FileUtilsFjxc.openFile(this@CbbsjsxActivity, File(path))
                                    } catch (e: Exception) {
                                        Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            })

                        }
                        /*try {
        //                    startActivity(intent);

        //                    var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
                    FileUtilsFjxc.openFile(this@CbbsjsxActivity,File(filePath))
                        } catch ( e:Exception) {
                            Toast.makeText(mContext, "设备中没有安装支持该格式的程序", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            }

        }



    }

    var optionsEntitys = ArrayList<OptionsEntity>()
    override fun returnBcjcGetOptions(message: List<OptionsEntity>) {
        optionsEntitys.addAll(message)

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

    //    选择状态改变
    private fun changeState1(list: List<JsjbBean>, position: Int) {
        if (list[position].isCheck()) {
            list[position].setCheck(false)
            tcList2.remove(list[position].text)
        } else {
            tcList2.add(list[position].text)
            list[position].setCheck(true)
        }
    }

    //    选择状态改变
    private fun changeState2(list: List<JsjbBean>, position: Int) {
        if (list[position].isCheck()) {
            list[position].setCheck(false)
            tcList3.remove(list[position].text)
        } else {
            tcList3.add(list[position].text)
            list[position].setCheck(true)
        }
    }

    //    选择状态改变
    private fun changeState3(list: List<JsjbBean>, position: Int) {
        for (i in list.indices) {
            list.get(i).isCheck = false
            if (i == position) {
                list.get(i).isCheck = true
            }
        }

    }

    var mXzbbPopu: CommenPop? = null
    //下载报表

//            xzqCunList   村名
//            arrayList   时间范围  true
//            qyfwList   区域范围
//            sxsxList   属性筛选
//            arrayList1   表格筛选  true
    /**
     * arrayList  : 时间范围
     * arrayList1 ：表格筛选
     */
    private fun initPopuXzbb(arrayList: ArrayList<String>, arrayList1: ArrayList<String>) {

        mXzbbPopu = CommenPop.getNormalPopu(this, R.layout.pop_xzbb, ll_act_cbbsjsx)//pop_point
        val contentView = mXzbbPopu!!.getContentView()
        val iv_pop_xzbb_error = contentView.findViewById<ImageView>(R.id.iv_pop_xzbb_error)//关闭弹窗
        val tv_pop_xzbb_sbject = contentView.findViewById<TextView>(R.id.tv_pop_xzbb_sbject)//主体
        val tv_pop_xzbb_download = contentView.findViewById<TextView>(R.id.tv_pop_xzbb_download)//导出excel路径
        val tv_pop_xzbb_cancel = contentView.findViewById<TextView>(R.id.tv_pop_xzbb_cancel)//取消
        val ll_pop_xzbb_download = contentView.findViewById<LinearLayout>(R.id.ll_pop_xzbb_download)//调用导出excel的方法
        tv_pop_xzbb_download.visibility = View.GONE
        tv_pop_xzbb_download.setOnClickListener {
            ToastUtils.showShort("无法直接打开")
            /*var filePath = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
            FileUtil.openFile(this,File(filePath));*/

        }
//        tv_pop_xzbb_sbject
        //"<b>${item.getXzqmc()}:</b> ${item.getUname()}"
        var html = ""
        if (xzqCunList.size > 0) {
            html += "<b>搜索</b><br/>"
            for (i in xzqCunList.indices) {
                if (i != xzqCunList.size - 1) {
                    html += xzqCunList.get(i).name + "、"
                } else {
                    html += xzqCunList.get(i).name + "<br/>"
                }
            }
            html += "<br/>"
        }
        if (qyfwList.size > 0) {
            html += "<b>行政区范围</b><br/>"
            for (i in qyfwList) {
                html = html + xzqList.get(i.getQuPosition()).name + " - " +
                        xzqList.get(i.getQuPosition()).children.get(i.getZhenPosition()).name + " - " +
                        xzqList.get(i.getQuPosition()).children.get(i.getZhenPosition()).children.get(i.getCunPosition()).name + "<br/>"
            }
            html += "<br/>"

        }
        if (arrayList.size > 0) {
            html += "<b>时间范围</b><br/>"
            for (i in arrayList.indices) {
                if (i != arrayList.size - 1) {
                    html += arrayList.get(i) + "、"
                } else {
                    html += arrayList.get(i) + "<br/>"
                }
            }
            html += "<br/>"

        }
        if (sxsxList.size > 0) {
            html += "<b>属性筛选</b><br/>"
            for (i in sxsxList) {
                html = html + i.getSxName() + " " + i.getSxTj() + " " + i.getSxSl() + i.getSxdw() + "<br/>"
            }
            html += "<br/>"
        }
        if (arrayList1.size > 0) {
            html += "<b>表格筛选</b><br/>"
            for (i in arrayList1.indices) {
                if (i != arrayList1.size - 1) {
                    html += arrayList1.get(i) + "、"
                } else {
                    html += arrayList1.get(i) + "<br/>"
                }
            }
        }
        tv_pop_xzbb_sbject.setText(Html.fromHtml(html))

        iv_pop_xzbb_error.setOnClickListener {
            mXzbbPopu!!.dismiss()
        }
        tv_pop_xzbb_cancel.setOnClickListener {
            mXzbbPopu!!.dismiss()
        }
        ll_pop_xzbb_download.setOnClickListener {
            val reportDto = ReportDto(codeList,years,sxDtoList,tableList1)//tableList
            mPresenter.getBcjcGetReprot(reportDto)

            var filePath1 = AppMenus.getInstance().cardPath + "/AndroidExcelDemo";
            tv_pop_xzbb_download.visibility = View.VISIBLE
            tv_pop_xzbb_download.setText("excel已导出至：" + filePath1);

        }
        CommenPop.backgroundAlpha(0.5f, this)
        mXzbbPopu!!.showAtLocation(ll_act_cbbsjsx, Gravity.CENTER, 0, 0)
    }

    public fun getAllDataFileName(folderPath: String): ArrayList<String> {
        var fileList = ArrayList<String>();//ArrayList<String>
        var file = File(folderPath);//File
        var tempList = file.listFiles();//File[]
        for (i in tempList.indices) {
            if (tempList[i].isFile()) {
                System.out.println("文     件：" + tempList[i].getName());
                var fileName = tempList[i].getName();
                if (fileName.endsWith(".xls")) {    //  根据自己的需要进行类型筛选
                    fileList.add(fileName);
                }
            }
        }
        return fileList;
    }

    /**
     * 获取目录下所有文件(按时间排序)
     *
     * @param path
     * @return
     */
    public fun listFileSortByModifyTime(path: String): List<String> {
        var list = getFiles(path, ArrayList<File>());
        if (list != null && list.size > 0) {
            Collections.sort(list, object :Comparator < File > {
                override fun compare(file: File?, newFile: File?): Int {
                    if (file!!.lastModified() > newFile!!.lastModified()) {
                        return -1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        var stringList = ArrayList<String>()
        for (i in list){
            stringList.add(i.name)
        }
        return stringList;
    }

    /**
     *
     * 获取目录下所有文件
     *
     * @param realpath
     * @param files
     * @return
     */
    public fun getFiles(realpath: String, files: ArrayList<File>): List<File> {
        var realFile = File(realpath);
        if (realFile.isDirectory()) {
            var subfiles = realFile.listFiles();
            for (file in subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    if (file.name.endsWith(".xls")){
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }


}
