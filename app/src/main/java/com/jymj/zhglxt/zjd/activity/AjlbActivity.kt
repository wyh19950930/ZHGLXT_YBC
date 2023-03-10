package com.jymj.zhglxt.zjd.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.pickerview.ProvinceBean
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.util.GetJsonDataUtil
import com.jymj.zhglxt.util.JsjbPickerUtil
import com.jymj.zhglxt.util.PopuPointUtils
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean
import com.jymj.zhglxt.zjd.bean.jsjb.LightBean
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEntity
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEnumBean
import com.jymj.zhglxt.zjd.contract.AjlbActContract
import com.jymj.zhglxt.zjd.contract.FjxcActContract
import com.jymj.zhglxt.zjd.presenter.AjlbActPresenter
import com.jymj.zhglxt.zjd.presenter.FjxcActPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_ajlb.*
import kotlinx.android.synthetic.main.activity_ajlb_search.*
import org.json.JSONArray

class AjlbActivity : BaseActivity<AjlbActPresenter, AjlbActContract.Model>(), AjlbActContract.View {

    var limit = 20
    var page = 1
    var title = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_ajlb
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        ic_act_ajlb_back.setOnClickListener {
            finish()
        }
        ic_act_ajlb_search.setOnClickListener {//??????
            val intent = Intent(this, AjlbSearchActivity::class.java)
            startActivity(intent)
        }
        tv_act_ajlb_search_title.setOnClickListener {
            title = cet_act_ajlb_search_title.text.toString()
            page = 1
            mPresenter.getOpinionGetList(page,limit,title,qutype)//  ??????
        }

    }
    var linearLayoutManager: LinearLayoutManager? = null
    var qutype = -1
    override fun initDatas() {
        qutype = intent.getIntExtra("qutype", -1)
        mPresenter.getEnumGetOpinion()//??????????????????
        mPresenter.getLightGetList()//??????????????????
        mPresenter.getSysXzqList(AppCache.getInstance().code,3)
        initJsonData()//?????????????????????

    }

    override fun returnFanJianDetail(renovated: Renovated) {//?????????????????????
        
    }
    var opinionentitys = ArrayList<OpinionEntity>()
    override fun returnOpinionGetList(renovated: List<OpinionEntity>) {//??????????????????
        if (page==1){
            opinionentitys.clear()
        }
        opinionentitys.addAll(renovated)
//        rlv_act_ajlb.setItemViewCacheSize(opinionentitys.size)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_act_ajlb.layoutManager = linearLayoutManager
        var firstVisibleItem = 0
        if (linearLayoutManager!=null){
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        rlv_act_ajlb.adapter = object:BaseQuickAdapter<OpinionEntity,BaseViewHolder>(R.layout.item_act_ajlb,opinionentitys){
            override fun convert(helper: BaseViewHolder?, item: OpinionEntity?) {//JsjbPickerUtil  SendEnum
//                helper!!.setIsRecyclable(false)
                helper!!.setText(R.id.tv_item_act_ajlb_title, item!!.xzqmc+item.title)
                        .setText(R.id.tv_item_act_ajlb_xysj, "????????????:"+item.overdue)//????????????
                        .setText(R.id.tv_item_act_ajlb_gdbh, "????????????:"+item.casebh)//
                        .setText(R.id.tv_item_act_ajlb_ajlx, "????????????:"+
                                JsjbPickerUtil.getString(JsjbPickerUtil.opinionEnumBean.SendEnum,item.sendType))//
                val tvItemActAjlbJjaj = helper.getView<ImageView>(R.id.tv_item_act_ajlb_jjaj)//icon_ajlb_jjaj urgentCase
                if (item.urgentCase == 0){//??????
                    if (item.overdue.equals("?????????")){
                        helper.setTextColor(R.id.tv_item_act_ajlb_xysj,Color.RED)
                        tvItemActAjlbJjaj.visibility = View.VISIBLE
                        tvItemActAjlbJjaj.setImageResource(R.drawable.icon_ajlb_yqaj)
                    }else{
                        tvItemActAjlbJjaj.visibility = View.VISIBLE
                        tvItemActAjlbJjaj.setImageResource(R.drawable.icon_ajlb_ptaj)
                    }
                }else if (item.urgentCase == 1){//??????
                    helper.setTextColor(R.id.tv_item_act_ajlb_title,Color.RED)
                    tvItemActAjlbJjaj.visibility = View.VISIBLE
                    tvItemActAjlbJjaj.setImageResource(R.drawable.icon_ajlb_jjaj)
                }


                val selectLight = PopuPointUtils.setSelectLight(item.municipalLight)
                val selectLight1 = PopuPointUtils.setSelectLight(item.districtLight)
                val iv_item_act_ajlb_zsd = helper.getView<ImageView>(R.id.iv_item_act_ajlb_zsd)
                val iv_item_act_ajlb_zsd1 = helper.getView<ImageView>(R.id.iv_item_act_ajlb_zsd1)
                val iv_item_act_ajlb_quzsd = helper.getView<ImageView>(R.id.iv_item_act_ajlb_quzsd)
                val iv_item_act_ajlb_quzsd1 = helper.getView<ImageView>(R.id.iv_item_act_ajlb_quzsd1)
                if (selectLight==null){//Color.parseColor("#FFF0F0F0")
                    var gradientDrawable = GradientDrawable();
                    gradientDrawable.setShape(GradientDrawable.OVAL);
                    gradientDrawable.setColor(Color.WHITE);
                    gradientDrawable.setStroke(2,Color.parseColor("#FFF0F0F0"));
                    gradientDrawable.setSize(50,50);
                    iv_item_act_ajlb_zsd.setBackground(gradientDrawable);
                }else{

                    val gradientDrawable = GradientDrawable()
                    gradientDrawable.shape = GradientDrawable.OVAL
                    gradientDrawable.setColor(Color.WHITE)
                    gradientDrawable.setStroke(2, Color.parseColor(selectLight.getColor()))
                    gradientDrawable.setSize(50, 50)
                    iv_item_act_ajlb_zsd.setBackground(gradientDrawable)
                    val gradientDrawable1 = GradientDrawable()
                    gradientDrawable1.shape = GradientDrawable.OVAL
                    gradientDrawable1.setColor(Color.parseColor(selectLight.getColor()))
                    gradientDrawable1.setStroke(10, Color.parseColor(selectLight.getColor()))
                    gradientDrawable1.setSize(50, 50)
                    iv_item_act_ajlb_zsd1.setBackground(gradientDrawable1)
                }
                if (selectLight1==null){//Color.parseColor("#FFF0F0F0")
                    var gradientDrawable = GradientDrawable()
                    gradientDrawable.setShape(GradientDrawable.OVAL)
                    gradientDrawable.setColor(Color.WHITE)
                    gradientDrawable.setStroke(2,Color.parseColor("#FFF0F0F0"))
                    gradientDrawable.setSize(50,50)
                    iv_item_act_ajlb_quzsd.setBackground(gradientDrawable)
                }else{

                    val gradientDrawable = GradientDrawable()
                    gradientDrawable.shape = GradientDrawable.OVAL
                    gradientDrawable.setColor(Color.WHITE)
                    gradientDrawable.setStroke(2, Color.parseColor(selectLight1.getColor()))
                    gradientDrawable.setSize(50, 50)
                    iv_item_act_ajlb_quzsd.setBackground(gradientDrawable)
                    val gradientDrawable1 = GradientDrawable()
                    gradientDrawable1.shape = GradientDrawable.OVAL
                    gradientDrawable1.setColor(Color.parseColor(selectLight1.getColor()))
                    gradientDrawable1.setStroke(10, Color.parseColor(selectLight1.getColor()))
                    gradientDrawable1.setSize(50, 50)
                    iv_item_act_ajlb_quzsd1.setBackground(gradientDrawable1)
                }
                helper!!.itemView.setOnClickListener {
                    val intent = Intent(this@AjlbActivity, AjDetailActivity::class.java)
                    intent.putExtra("opinionEntity",item)
                    startActivity(intent)
                }
            }
        }
        rlv_act_ajlb.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&opinionentitys.size==page*limit) {
                    if (opinionentitys.size !=0 && opinionentitys.size%limit == 0){
                        page++
                        mPresenter.getOpinionGetList(page,limit,title,qutype)//?????????????????????  ??????
//                        rlv_act_ajlb.adapter!!.notifyDataSetChanged()
//                        mPresenter.getCxldry(stringExtra,mphName,mphName,limit,page)
                    }else{
                        ToastUtils.showShort("??????????????????")
                    }
                }
            }
        })
        if (page!=1){
            rlv_act_ajlb.scrollToPosition(firstVisibleItem+1)//(page-1)*limit
        }

    }
    override fun returnEnumGetOpinion(renovated: OpinionEnumBean) {
        JsjbPickerUtil.setOpinionEnumBean(renovated)
        mPresenter.getOpinionGetList(page,limit,title,qutype)//?????????????????????  ??????
//        JsjbPickerUtil.setActivity(this)
    }
    override fun returnLightGetList(renovated: List<LightBean>) {
        PopuPointUtils.setLightItems(renovated)
        tv_act_ajlb_zsd.setOnClickListener {//?????????
            PopuPointUtils.initPopuZsdzj(this,ll_act_ajlb)
        }
    }
    override fun returnSysXzqList(renovated: List<SysXzqEntity>) {
        PopuPointUtils.setSysXzqEntities(renovated)
    }
    //????????????????????????
    private fun initJsonData() {
        var options1Items = ArrayList<ProvinceBean>()//???
        var options2Items = ArrayList<ArrayList<String>>()//???
        var options3Items = ArrayList<ArrayList<ArrayList<String>>>()//???
        val jsonData = GetJsonDataUtil().getJson(this, "city.json")
        val province = parseData(jsonData)
        //???????????????
        options1Items = province

        for (i in 0..province.size - 1) {
            val CityList = ArrayList<String>()
            val Province_AreaList = ArrayList<ArrayList<String>>()
            val city = province.get(i).children
            for (c in 0..city.size - 1) {
                val CityName = city.get(c).value
                CityList.add(CityName)//????????????
                val City_AreaList = ArrayList<String>()//??????????????????????????????


                //??????????????????????????????????????????????????????????????????null ?????????????????????????????????????????????
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
                Province_AreaList.add(City_AreaList)//??????????????????????????????

            }
            /**
             * ??????????????????
             */
            options2Items.add(CityList)

            /**
             * ??????????????????
             */
            options3Items.add(Province_AreaList)
        }
        JsjbPickerUtil.setOptions1Items(options1Items)
        JsjbPickerUtil.setOptions2Items(options2Items)
        JsjbPickerUtil.setOptions3Items(options3Items)

    }
    //?????????json????????????
    private fun parseData(result: String): ArrayList<ProvinceBean> {
        val detail = ArrayList<ProvinceBean>()
        val data = JSONArray(result)
        val gson = Gson()
        for (i in 0..data.length() - 1) {
            val entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceBean::class.java)
            detail.add(entity)
        }
        return detail
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
        if (AppCache.getInstance().isJsjb){
            page = 1
            mPresenter.getOpinionGetList(page,limit,title,qutype)//  ??????
            AppCache.getInstance().isJsjb = false
        }
    }

}
