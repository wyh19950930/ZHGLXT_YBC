package com.jymj.zhglxt.zjd.fragment

import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.data.RadarEntry
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.ldrkgl.home.activity.LdrkDetailActivity
import com.jymj.zhglxt.rjhj.bean.PjEnviorSupvsEntity
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.rjhj.bean.yl.*
import com.jymj.zhglxt.rjhj.bean.yl.cg.CgEntity
import com.jymj.zhglxt.rjhj.bean.yl.tdly.TdlyEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.jymj.zhglxt.util.GetFileIco
import com.jymj.zhglxt.widget.InitYLRadar
import com.jymj.zhglxt.zjd.activity.PDFActivity
import com.jymj.zhglxt.zjd.bean.YLPointEntity
import com.jymj.zhglxt.zjd.bean.yzt.tg.TgEntity
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.baseapp.BaseApplication
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.FileUtils
import com.setsuna.common.commonutils.ScreenUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.fragment_point_jcxx.*
import org.json.JSONObject
import java.math.BigDecimal
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * 业务点查询基础信息
 */
class PointJCXXFragment : BaseFragment<BasePresenter<*,*>,BaseModel>() {
    //Fragment()
    private var pointYLPointEntity: YLPointEntity? = null //点查房屋
    private var pointTdlyEntity: TdlyEntity? = null //点查土现
    private var pointTgEntity: TgEntity? = null //点查土规
    private var pointCgEntity: CgEntity? = null //点查城规

    override fun lazyLoad() {

    }
    override fun getLayoutResource(): Int {
        return R.layout.fragment_point_jcxx
    }
    override fun initPresenter() {
    }
    override fun initViews() {
    }
    override fun initDatas() {
        recyPoint?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyPoint?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
    fun fwData(YLPoint: YztPointEntity) {
//        if (YLPoint.yztFhEntity != null) {
        //rlv_jcxx_zjxx
        if (pointRadar!=null){
            if (YLPoint.ylList.size > 0) {//YLPoint.yztFhEntity.ylEntities
                if (YLPoint.ylList.get(0).fl2.equals("宅基地")){//getcqlyText

                    pointRadar.visibility = View.VISIBLE
                    tvsPoint.visibility = View.VISIBLE
                    point_ylxx.visibility = View.VISIBLE
//                    ll_jcxx_qyxx.visibility = View.GONE
                    val radarData = ArrayList<RadarEntry>()
                    //雷达图的数据
                    radarData.add(RadarEntry(YLPoint.ylList.get(0)!!.ldArea))
                    radarData.add(RadarEntry(YLPoint.ylList.get(0)!!.ldRk))
                    //radarData.add(RadarEntry(YLPoint.ylEntity!!.ldQdfs))
                    radarData.add(RadarEntry(YLPoint.ylList.get(0)!!.ldRjl))
                    radarData.add(RadarEntry(YLPoint.ylList.get(0)!!.ldDjgx))
//            showView(pointRadar as View)
                    //向雷达图里添加数据
                    InitYLRadar.getInstance().creatRadar(context, pointRadar, radarData)
                    point_ylxx.setOnClickListener {
                        if (tvsPoint.visibility == View.GONE) {
                            tvsPoint.visibility = View.VISIBLE
                            pointRadar.visibility = View.VISIBLE
                            hsv.visibility = View.VISIBLE
                            /* if (radarData.size > 0) {
                                 hsv.visibility = View.VISIBLE
                             }*/

                        } else {
                            tvsPoint.visibility = View.GONE
                            pointRadar.visibility = View.GONE
                            hsv.visibility = View.GONE
//                        hsv.visibility = View.GONE
                            /* if (radarData.size == 0) {
                                 hsv.visibility = View.GONE
                             }*/
                        }
                    }
                    val df = DecimalFormat("#.00")
                    //院落数据（在雷达图上边）
                    val tvDatas = ArrayList<TextViewsEntity>()
                    tvDatas.add(TextViewsEntity("村名:", YLPoint.ylList.get(0)?.xzqmc))
                    tvDatas.add(TextViewsEntity("门牌号:", YLPoint.ylList.get(0)?.mph))
                    tvDatas.add(TextViewsEntity("户主:", YLPoint.ylList.get(0)?.hzmc))
                    tvDatas.add(TextViewsEntity("一级分类:", YLPoint.ylList.get(0)?.ylTypeText))
                    tvDatas.add(TextViewsEntity("二级分类:", YLPoint.ylList.get(0)?.fl2))

                    if (YLPoint.ylList.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                        tvDatas.add(TextViewsEntity("占地面积:", YLPoint.ylList.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                    }else{
                        tvDatas.add(TextViewsEntity("占地面积:", YLPoint.ylList.get(0)?.area.toString()+"㎡"))
                    }
                    if (YLPoint.ylList.get(0)?.jianzhuArea!!.compareTo(BigDecimal(10000))>0){
                        tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.ylList.get(0)?.jianzhuArea!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                    }else{
                        tvDatas.add(TextViewsEntity("建筑面积:", YLPoint.ylList.get(0)?.jianzhuArea.toString()+"㎡"))
                    }

                    tvDatas.add(TextViewsEntity("人口:", YLPoint.ylList.get(0)?.rk.toString()))
//                tvDatas.add(TextViewsEntity("流动人口:", YLPoint.ylList.get(0)?.ldRk.toString()))
                    tvDatas.add(TextViewsEntity("农业人口:", YLPoint.ylList.get(0)?.nongcount.toString()))
                    tvDatas.add(TextViewsEntity("非农人口:", YLPoint.ylList.get(0)?.feinongcount.toString()))
                    tvDatas.add(TextViewsEntity("户数:", YLPoint.ylList.get(0)?.hucount.toString()))
                    tvDatas.add(TextViewsEntity("特殊情况:", YLPoint.ylList.get(0)?.remark))
                    tvDatas.add(TextViewsEntity("流动人口:", YLPoint.ylList.get(0)?.ldrks.toString()))
                    //tvDatas.add(TextViewsEntity("流动面积:", YLPoint.ylList.get(0)?.ldArea.toString()))

                    tvsPoint?.setDatas(tvDatas)
                    tvsPoint?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
                }else{
                    point_ylxx.visibility = View.GONE
                    pointRadar.visibility = View.GONE
                    tvsPoint.visibility = View.GONE
                }
            } else {
//                ll_jcxx_qyxx.visibility = View.GONE
                point_ylxx.visibility = View.GONE
                pointRadar.visibility = View.GONE
                tvsPoint.visibility = View.GONE
            }
            if (YLPoint.feiZhaiList.size>0){
                ll_jcxx_qyxx.visibility = View.VISIBLE
                ll_jcxx_zjxx.visibility = View.GONE
                tv_jcxx_qyxx.setText("产业用地信息")//YLPoint.feiZhaiList.get(0).industry
                /*if (tab_jcxx_qyxx.tabCount==0){

                }*/
                tab_jcxx_qyxx.removeAllTabs()
                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("用地管理"))
                tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("企业经营"))
                if (YLPoint.landinfo.size>0){
                    ll_jcxx_qyxx2.visibility = View.VISIBLE
                    tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("证件信息"))
                }
                tv_jcxx_cm.setText(Html.fromHtml("<b>村名:</b>"+YLPoint.feiZhaiList.get(0).cunName))//xzqmc
                tv_jcxx_qs.setText(Html.fromHtml("<b>权属:</b>"+YLPoint.feiZhaiList.get(0).tdxz))
                tv_jcxx_zdmj.setText(Html.fromHtml("<b>占地面积(㎡):</b>"+YLPoint.feiZhaiList.get(0).zdmj.toString()))
                tv_jcxx_jzmj.setText(Html.fromHtml("<b>建筑面积(㎡):</b>"+YLPoint.feiZhaiList.get(0).jzmj.toString()))
                tv_jcxx_name.setText(Html.fromHtml("<b>名称:</b>"+YLPoint.feiZhaiList.get(0).qiyeName.toString()))
                tv_jcxx_zjxh.setText(Html.fromHtml("<b>证件序号:</b>"+""))//YLPoint.feiZhaiList.get(0).ylId.toString()
                tv_jcxx_remark.setText(Html.fromHtml("<b>备注:</b>"+YLPoint.feiZhaiList.get(0).text.toString()))

                tv_jcxx_qyxx3_qymc.setText(Html.fromHtml("<b>企业名称:</b>"+YLPoint.feiZhaiList.get(0).qiyeName.toString()))
                tv_jcxx_qyxx3_yyzz.setText(Html.fromHtml("<b>营业执照:</b>"+YLPoint.feiZhaiList.get(0).yyzzh.toString()))
                tv_jcxx_qyxx3_tdsyr.setText(Html.fromHtml("<b>土地使用人:</b>"+YLPoint.feiZhaiList.get(0).tdsyr.toString()))
                tv_jcxx_qyxx3_dswsyqr.setText(Html.fromHtml("<b>地上物所有权人:</b>"+YLPoint.feiZhaiList.get(0).dswsyq.toString()))
                tv_jcxx_qyxx3_fwjcnd.setText(Html.fromHtml("<b>房屋建成年代:</b>"+YLPoint.feiZhaiList.get(0).fwjcnd.toString()))
                tv_jcxx_qyxx3_ydqx.setText(Html.fromHtml("<b>用地期限:</b>"+YLPoint.feiZhaiList.get(0).ydqx.toString()))
                tv_jcxx_qyxx3_zcdz.setText(Html.fromHtml("<b>注册地址:</b>"+YLPoint.feiZhaiList.get(0).zcdz.toString()))
                tv_jcxx_qyxx3_sfss.setText(Html.fromHtml("<b>是否上市:</b>"+YLPoint.feiZhaiList.get(0).ifss.toString()))
                tv_jcxx_qyxx3_frxm.setText(Html.fromHtml("<b>法人姓名:</b>"+YLPoint.feiZhaiList.get(0).frxm.toString()))
                tv_jcxx_qyxx3_lxfs.setText(Html.fromHtml("<b>联系方式:</b>"+YLPoint.feiZhaiList.get(0).lxfs.toString()))
                tv_jcxx_qyxx3_zczj.setText(Html.fromHtml("<b>注册资金(万元):</b>"+YLPoint.feiZhaiList.get(0).zczj.toString()))
                tv_jcxx_qyxx3_wzqyzczj.setText(Html.fromHtml("<b>外资企业注册资金(万元):</b>"+YLPoint.feiZhaiList.get(0).wzzc.toString()))
                tv_jcxx_qyxx3_yqgqss.setText(Html.fromHtml("<b>央企/国企/市属:</b>"+YLPoint.feiZhaiList.get(0).qyxz.toString()))
                tv_jcxx_qyxx3_gxqy.setText(Html.fromHtml("<b>高薪企业:</b>"+YLPoint.feiZhaiList.get(0).gxqy.toString()))
                tv_jcxx_qyxx3_gsygbs.setText(Html.fromHtml("<b>公司员工(本市):</b>"+YLPoint.feiZhaiList.get(0).gsbs.toString()))
                tv_jcxx_qyxx3_gsygwq.setText(Html.fromHtml("<b>公司员工(外阜):</b>"+YLPoint.feiZhaiList.get(0).gswq.toString()))
                tv_jcxx_qyxx3_dwss.setText(Html.fromHtml("<b>就业人口居住地(单位宿舍):</b>"+YLPoint.feiZhaiList.get(0).jydw.toString()))
                tv_jcxx_qyxx3_gc.setText(Html.fromHtml("<b>就业人口居住地(各村):</b>"+YLPoint.feiZhaiList.get(0).jygc.toString()))

                tv_jcxx_qyxx3_jyzt.setText(Html.fromHtml("<b>经营状态:</b>"+YLPoint.feiZhaiList.get(0).jystatus.toString()))
                tv_jcxx_qyxx3_qyfzr.setText(Html.fromHtml("<b>企业负责人:</b>"+YLPoint.feiZhaiList.get(0).qyfzr.toString()))
                tv_jcxx_qyxx3_fzrlxfs.setText(Html.fromHtml("<b>联系方式:</b>"+YLPoint.feiZhaiList.get(0).fzrlxfs.toString()))
                tv_jcxx_qyxx3_yxdz.setText(Html.fromHtml("<b>邮箱地址:</b>"+YLPoint.feiZhaiList.get(0).yxdz.toString()))
                tv_jcxx_qyxx3_hylx.setText(Html.fromHtml("<b>行业类别:</b>"+YLPoint.feiZhaiList.get(0).industry.toString()))
                tv_jcxx_qyxx3_zycp.setText(Html.fromHtml("<b>主要产品:</b>"+YLPoint.feiZhaiList.get(0).zycp.toString()))
                tv_jcxx_qyxx3_yysr.setText(Html.fromHtml("<b>2019营业收入(万元):</b>"+YLPoint.feiZhaiList.get(0).yysr2019.toString()))
                tv_jcxx_qyxx3_gyzczz.setText(Html.fromHtml("<b>2019工业资产总值(万元):</b>"+YLPoint.feiZhaiList.get(0).cz2019.toString()))
                tv_jcxx_qyxx3_jydz.setText(Html.fromHtml("<b>经营地址:</b>"+YLPoint.feiZhaiList.get(0).jydz.toString()))
                tv_jcxx_qyxx3_symj.setText(Html.fromHtml("<b>使用面积(㎡):</b>"+YLPoint.feiZhaiList.get(0).symj.toString()))
                tv_jcxx_qyxx3_czmj.setText(Html.fromHtml("<b>出租面积(㎡):</b>"+YLPoint.feiZhaiList.get(0).czmj.toString()))
                tv_jcxx_qyxx3_shymj.setText(Html.fromHtml("<b>剩余面积(㎡):</b>"+YLPoint.feiZhaiList.get(0).yxmj.toString()))


                tv_jcxx_qyxx.setOnClickListener {
                    if (ll_jcxx_qyxx1.isShown){
                        ll_jcxx_qyxx1.visibility = View.GONE
                    }else{
                        ll_jcxx_qyxx1.visibility = View.VISIBLE
                    }


                }

            }else{
                if (YLPoint.landinfo.size>0){
                    tab_jcxx_qyxx.removeAllTabs()
                    ll_jcxx_qyxx.visibility = View.VISIBLE
                    ll_jcxx_zjxx.visibility = View.VISIBLE
                    tab_jcxx_qyxx.addTab(tab_jcxx_qyxx.newTab().setText("证件信息"))
                }else{
                    ll_jcxx_qyxx.visibility = View.GONE
                }

            }
            tab_jcxx_qyxx.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabReselected(p0: TabLayout.Tab?) {

                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                }

                override fun onTabSelected(p0: TabLayout.Tab?) {
                    if (p0!!.text.toString().equals("用地管理")){
                        ll_jcxx_qyxx2.visibility = View.VISIBLE
                        ll_jcxx_qyxx3.visibility = View.GONE
                        ll_jcxx_zjxx.visibility = View.GONE

                    }else if (p0!!.text.toString().equals("企业经营")) {
                        ll_jcxx_qyxx2.visibility = View.GONE
                        ll_jcxx_qyxx3.visibility = View.VISIBLE
                        ll_jcxx_zjxx.visibility = View.GONE
                    }else{
                        ll_jcxx_qyxx2.visibility = View.GONE
                        ll_jcxx_qyxx3.visibility = View.GONE
                        ll_jcxx_zjxx.visibility = View.VISIBLE
                    }
                }
            })
            if (YLPoint.landinfo.size>0){
                tv_jcxx_qyxzmc.setText(Html.fromHtml("<b>企业现状名称:</b>"+YLPoint.landinfo.get(0).qyname.toString()))
                tv_jcxx_tdsyz.setText(Html.fromHtml("<b>土地使用者:</b>"+YLPoint.landinfo.get(0).tdsyz.toString()))
                tv_jcxx_tdyt.setText(Html.fromHtml("<b>土地用途:</b>"+YLPoint.landinfo.get(0).tdyt.toString()))
                tv_jcxx_syqlx.setText(Html.fromHtml("<b>使用权类型:</b>"+YLPoint.landinfo.get(0).syqlx.toString()))
                tv_jcxx_syqmj.setText(Html.fromHtml("<b>使用权面积(㎡):</b>"+YLPoint.landinfo.get(0).syqmj.toString()))
                tv_jcxx_dymj.setText(Html.fromHtml("<b>独用面积(㎡):</b>"+YLPoint.landinfo.get(0).area.toString()))
                tv_jcxx_zzrq.setText(Html.fromHtml("<b>终止日期:</b>"+YLPoint.landinfo.get(0).zzdate.toString()))
                rlv_jcxx_zjxx.layoutManager = GridLayoutManager(activity,3)
                rlv_jcxx_zjxx.adapter = object : BaseQuickAdapter<LandFileEntity,BaseViewHolder>(R.layout.item_jcxx_tp,YLPoint.tdzFile){
                    override fun convert(helper: BaseViewHolder?, item: LandFileEntity?) {
                        val icoId: Int = GetFileIco.getIcoId(FileUtils.getFileExtension(item!!.path))
                        val ivItemJcxxPic = helper!!.getView<ImageView>(R.id.iv_item_jcxx_pic)
                        Glide.with(BaseApplication.getAppContext()).load(icoId)
                                .into(ivItemJcxxPic)
                        helper!!.setText(R.id.tv_item_jcxx_name,item.name)
                        helper.itemView.setOnClickListener {
                            val pic = ApiConstants.BASE_URL + "tdzFile/" + item.folderid + "/" + item.path.trim()//YLPoint.ylList.get(0).tdzmc
                            val intent = Intent(context, PDFActivity::class.java)
                            intent.putExtra("pdf", pic.trim())
                            startActivity(intent)
                        }
                    }
                }
            } //        }
            if (YLPoint.zhaiList != null) {
                recyPoint.visibility = View.VISIBLE
                clPointTitle.visibility = View.VISIBLE
                //如果数据条数大于4条设置最大高度200
                if (YLPoint.zhaiList.size > 4) {
                    recyPoint?.layoutParams?.height = ScreenUtils.dpToPx(context, 200f).toInt()
                } else {
                    recyPoint?.layoutParams?.height = RecyclerView.LayoutParams.WRAP_CONTENT
                }
                if (YLPoint.zhaiList.size>0){
                    hsv.visibility = View.VISIBLE
                    recyPoint?.adapter = object : BaseQuickAdapter<ZhaiEntity, BaseViewHolder>(R.layout.item_point_ren, YLPoint.zhaiList) {
                        override fun convert(helper: BaseViewHolder, item: ZhaiEntity) {
                            helper.setText(R.id.tvNameRenItem, item.housecount)
                                    .setText(R.id.tvSexRenItem, item.sexText)
                                    .setText(R.id.tvAgeRenItem, item.age.toString() + "")
                                    .setText(R.id.tvRelRenItem, item.socialrelatText)
                                    .setText(R.id.tvHuRenItem, item.huTypeText)
                                    .setText(R.id.tvPIDRenItem, item.idCard)
                                    .setText(R.id.tvWorkRenItem, item.empsituationText)
                        }
                    }
                }else{
                    hsv.visibility = View.GONE
                }
            } else {
                recyPoint.visibility = View.GONE
                clPointTitle.visibility = View.GONE
            }
            if (YLPoint.tdLYEntity .size>0) {
                point_tx_ll.visibility = View.VISIBLE
                point_tx_hsv.visibility = View.VISIBLE
                point_tx_zwsj.visibility = View.GONE
                tv_point_jcxx_tx.setOnClickListener {
                    if (point_tx_hsv.visibility == View.GONE) {
                        point_tx_hsv.visibility = View.VISIBLE
                    } else {
                        point_tx_hsv.visibility = View.GONE
                    }
                }

                point_tx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                point_tx_rlv.adapter = object : BaseQuickAdapter<TdlyEntity, BaseViewHolder>(R.layout.item_pointjcxx_tx_rlv, YLPoint.tdLYEntity) {
                    override fun convert(helper: BaseViewHolder, item: TdlyEntity) {
                        if (item.area1.compareTo(BigDecimal(1000))==1 ){
                            helper.setText(R.id.pointjcxx_tx_mj, ""+item.area1.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP)+"公顷")
                        }else{
                            helper.setText(R.id.pointjcxx_tx_mj, ""+item.area1+"㎡")
                        }
                        helper.setText(R.id.pointjcxx_tx_cm, item.xzqmc)
                                .setText(R.id.pointjcxx_tx_dlbm, item.dlbm)
                                .setText(R.id.pointjcxx_tx_qsxz, item.lx)
                                .setText(R.id.pointjcxx_tx_dlmc, item.dlmc)
                    }
                }
            } else {
                point_tx_ll.visibility = View.GONE
                point_tx_hsv.visibility = View.GONE
                point_tx_zwsj.visibility = View.VISIBLE
            }
            if (YLPoint.yztHzEntities.size>0) {
                point_dkxx_ll.visibility = View.VISIBLE
                point_dkxx_hsv.visibility = View.VISIBLE
                point_dkxx_zwsj.visibility = View.GONE
                tv_point_dkxx_tx.setOnClickListener {
                    if (point_dkxx_hsv.visibility == View.GONE) {
                        point_dkxx_hsv.visibility = View.VISIBLE
                    } else {
                        point_dkxx_hsv.visibility = View.GONE
                    }
                }

                point_dkxx_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                point_dkxx_rlv.adapter = object : BaseQuickAdapter<TdlyEntity, BaseViewHolder>(R.layout.item_pointjcxx_tx_rlv, YLPoint.yztHzEntities) {
                    override fun convert(helper: BaseViewHolder, item: TdlyEntity) {
                        if (item.area1.compareTo(BigDecimal(10000))==1 ){
                            helper.setText(R.id.pointjcxx_tx_mj, ""+item.area.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP)+"万公顷")
                        }else{
                            helper.setText(R.id.pointjcxx_tx_mj, ""+item.area+"公顷")
                        }
                        helper.setText(R.id.pointjcxx_tx_cm, item.xzqmc)
                                .setText(R.id.pointjcxx_tx_dlbm, item.lx)
                                .setText(R.id.pointjcxx_tx_qsxz, item.qsxz)
                                .setText(R.id.pointjcxx_tx_dlmc, item.layer)
                    }
                }
            } else {
                point_dkxx_ll.visibility = View.GONE
                /*point_dkxx_hsv.visibility = View.GONE
                point_dkxx_zwsj.visibility = View.VISIBLE*/
            }
            if (YLPoint.floatPopulats.size>0){
                ll_jcxx_ldrk.visibility = View.VISIBLE
                tv_jcxx_ldrk.setOnClickListener {
                    if (ll_jcxx_ldrk1.isShown){
                        ll_jcxx_ldrk1.visibility = View.GONE
                    }else{
                        ll_jcxx_ldrk1.visibility = View.VISIBLE
                    }
                }
                rlv_jcxx_ldrk.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                /*val ldrkSelectAdapter = LdrkSelectAdapter(context, YLPoint.floatPopulats)
                rlv_jcxx_ldrk.adapter = ldrkSelectAdapter
                ldrkSelectAdapter.setOnLdrkSelectLinear(object :LdrkSelectAdapter.OnLdrkSelectLinear{
                    override fun onDeleteClick(floatPopulat: FloatPopulat?, positon:Int) {
                        getLdrkDelete(floatPopulat!!.id)
                    }

                    override fun onClick(floatPopulat: FloatPopulat?, positon:Int) {
                        getLdrkDetail(floatPopulat!!.ylEntity.center1)
                    }
                })*/

            }else{
                ll_jcxx_ldrk.visibility = View.GONE
            }


        }

        /*if (YLPoint.tgEntity != null) {
            point_tg_hsv.visibility = View.VISIBLE
            point_tg_zwsj.visibility = View.GONE
            tv_point_jcxx_tg.setOnClickListener {
                if (point_tg_hsv.visibility == View.GONE) {
                    point_tg_hsv.visibility = View.VISIBLE
                } else {
                    point_tg_hsv.visibility = View.GONE
                }
            }

            point_tg_rlv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            point_tg_rlv.adapter = object : BaseQuickAdapter<TgEntity, BaseViewHolder>(R.layout.item_pointjcxx_tx_rlv, YLPoint.tgEntity) {
                override fun convert(helper: BaseViewHolder, item: TgEntity) {
                    helper.setText(R.id.pointjcxx_tx_cm, item.xzqmc)
                    helper.getView<TextView>(R.id.pointjcxx_tx_dlbm).visibility = View.GONE
                    helper.setText(R.id.pointjcxx_tx_mj, item.area1.toString())
                    helper.getView<TextView>(R.id.pointjcxx_tx_qsxz).visibility = View.GONE
                    helper.setText(R.id.pointjcxx_tx_dlmc, item.landName)
                }
            }
        } else {
            point_tg_hsv.visibility = View.GONE
            point_tg_zwsj.visibility = View.VISIBLE
        }*/

    }
    fun getLdrkDetail(point:String){
        val jsonObject = JSONObject()
        jsonObject.put("point", point)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
//        OkGo.post<String>(ApiConstants.FLOAT_POPULAT_LIST).upJson(sss).execute(LdrkChaNetCallBack(mView))
        OkGo.post<String>(ApiConstants.FLOAT_POPULAT_LIST).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        val decrypt = AesEncryptUtils.decrypt(response.body())
//                                T fromJson = new Gson().fromJson(s, T);
                        val json: BaseRespose<LdrkPointBean> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<LdrkPointBean>?>() {}.type)

                        if (json.code==0 ){//&& response.body().data.ylEntity!=null
                            if (json.data.ylEntity.gid!=0){
                                val intent = Intent(context, LdrkDetailActivity::class.java)
                                intent.putExtra("ldrkPointBean",json.data)
                                startActivity(intent)
                            }
                        }else{
                            showErrorTip("该条数据为空")
                        }
                    }else{
                        showErrorTip("该条数据为空")
                    }
                }else{
                    showErrorTip("该条数据为空")
                }
                stopLoading()

            }

            override fun onFinish() {
                super.onFinish()
                stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                showErrorTip("删除失败")
            }

        })
    }
    fun getLdrkDelete(id:Int) {
        val jsonObject = JSONObject()
        jsonObject.put("id","["+id+"]")
//        val substring = jsonObject.toString().substring(0, jsonObject.toString().length)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.FLOAT_POPULAT_DELETE).upJson(sss).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                showLoading("数据加载中...")
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
//                Log.e("--------",cash.toString());
                if (cash!=null){
                    val decrypt = AesEncryptUtils.decrypt(cash)
//                    val json = Gson().fromJson(decrypt, BaseRespose<LdrkBean>::class.java)
                    val json: BaseRespose<String> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<String>?>() {}.type)

                    if (json.code==0){
                        if (json!!.data!=null){
                            ToastUtils.showShort("删除成功")
                        }else {
                            showErrorTip(json.getMsg())
                        }
                    }else{
                        showErrorTip(json.getMsg())
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
                stopLoading()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                showErrorTip("删除失败")
            }

        })
    }

    fun txData(result: YztPointEntity) {
        if (point_tx_cm_tv!=null){

            if (result != null) {
                if (result.tdLYEntity.size>0){
                    pointTdlyEntity = result.tdLYEntity.get(0)
                    point_tx_cm_tv.text = pointTdlyEntity!!.xzqmc
                    point_tx_mj_tv.text = pointTdlyEntity!!.area.toString()
                    point_tx_dlbm_tv.text = pointTdlyEntity!!.dlbm
                    point_tx_qsxz_tv.text = pointTdlyEntity!!.qsxz
                    point_tx_dlmc_tv.text = pointTdlyEntity!!.dlmc
                }
            } else {
                point_tx_cm_tv.text = "xxx"
                point_tx_mj_tv.text = "xxx"
                point_tx_dlbm_tv.text = "xxx"
                point_tx_qsxz_tv.text = "xxx"
                point_tx_dlmc_tv.text = "xxx"
            }
        }
    }

    fun tgData(result: TgEntity) {
        if (result != null) {
            point_tg_cm_tv.text = result.xzqmc
            point_tg_dkbm_tv.text = result.gid.toString()
            point_tg_dlmc_tv.text = result.landName
            point_tg_mj_tv.text = result.area.toString()
        } else {
            point_tg_cm_tv.text = "xxx"
            point_tg_dkbm_tv.text = "xxx"
            point_tg_dlmc_tv.text = "xxx"
            point_tg_mj_tv.text = "xxx"
        }
    }

    fun cgData(result: CgEntity) {
        if (result != null) {
            tv_point_jcxx_cgxx.setOnClickListener {
                if (point_cg_lll.visibility == View.GONE) {
                    point_cg_lll.visibility == View.VISIBLE
                } else {
                    point_cg_lll.visibility == View.GONE
                }
            }
            point_cg_lll.visibility = View.VISIBLE
            point_cg_zwsj.visibility = View.GONE
            point_cg_dkbm_tv.text = result.gid.toString()
            point_cg_ydlx_tv.text = result.landName
            point_cg_mj_tv.text = result.area.toString()
            point_cg_rjl_tv.text = result.rjl.toString()
            point_cg_jzgm_tv.text = result.jzgm.toString()
        } else {
            point_cg_lll.visibility = View.GONE
            point_cg_zwsj.visibility = View.VISIBLE
            point_cg_dkbm_tv.text = "xxx"
            point_cg_ydlx_tv.text = "xxx"
            point_cg_mj_tv.text = "xxx"
            point_cg_rjl_tv.text = "xxx"
            point_cg_jzgm_tv.text = "xxx"
        }
    }

    fun setData(ylPointEntity: YztPointEntity) {
        if (point_zjdglxx!=null){
            if (ylPointEntity.yztFhEntity.yztdcEntities.size>0){
                ll_jcxx_dc.visibility = View.VISIBLE
                tv_jcxx_dc.setOnClickListener {
                    if (tvs_jcxx_dc.visibility==View.GONE){
                        tvs_jcxx_dc.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_dc.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("类型:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.lx))
                if (ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztdcEntities.get(0)?.area.toString()+"㎡"))
                }

                tvs_jcxx_dc?.setDatas(tvDatas)
                tvs_jcxx_dc?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_dc.visibility = View.GONE
            }
            if (ylPointEntity.yztFhEntity.yztYcEntities.size>0){
                ll_jcxx_yc.visibility = View.VISIBLE
                tv_jcxx_yc.setOnClickListener {
                    if (tvs_jcxx_yc.visibility==View.GONE){
                        tvs_jcxx_yc.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_yc.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("类型:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.lx))
                if (ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztYcEntities.get(0)?.area.toString()+"㎡"))
                }
                tvs_jcxx_yc?.setDatas(tvDatas)
                tvs_jcxx_yc?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_yc.visibility = View.GONE
            }
            if (ylPointEntity.yztFhEntity.yztSdgyEnties.size>0){
                ll_jcxx_sd.visibility = View.VISIBLE
                tv_jcxx_sd.setOnClickListener {
                    if (tvs_jcxx_sd.visibility==View.GONE){
                        tvs_jcxx_sd.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_sd.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("成交时间:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.cjrq))
                if (ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztSdgyEnties.get(0)?.area.toString()+"㎡"))
                }
                tvs_jcxx_sd?.setDatas(tvDatas)
                tvs_jcxx_sd?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_sd.visibility = View.GONE
            }
            if (ylPointEntity.yztFhEntity.yztLhEntities.size>0){//之前的绿化
                ll_jcxx_lh.visibility = View.VISIBLE
                tv_jcxx_lh.setOnClickListener {
                    if (tvs_jcxx_lh.visibility==View.GONE){
                        tvs_jcxx_lh.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_lh.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("林地名称:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.layer))
                if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
                }
                tvDatas.add(TextViewsEntity("土地现状:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.tdxz))
                tvDatas.add(TextViewsEntity("地块编号:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.dkbh))
                tvDatas.add(TextViewsEntity("备注:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.remark))

                tvs_jcxx_lh?.setDatas(tvDatas)
                tvs_jcxx_lh?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_lh.visibility = View.GONE
            }
            if (ylPointEntity.yztLh.size>0){//现在的绿化
                ll_jcxx_lh.visibility = View.VISIBLE
                tv_jcxx_lh.setOnClickListener {
                    if (tvs_jcxx_lh.visibility==View.GONE){
                        tvs_jcxx_lh.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_lh.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("项目名称:", ylPointEntity.yztLh.get(0)?.layer))
                tvDatas.add(TextViewsEntity("项目类型:", ylPointEntity.yztLh.get(0)?.lx))
                tvDatas.add(TextViewsEntity("面积:", ylPointEntity.yztLh.get(0)?.area.toString()+"亩"))
                /* if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                     tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                 }else{
                     tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
                 }*/

                tvs_jcxx_lh?.setDatas(tvDatas)
                tvs_jcxx_lh?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_lh.visibility = View.GONE
            }
            if (ylPointEntity.yztCt.size>0){//拆腾
                ll_jcxx_ct.visibility = View.VISIBLE
                tv_jcxx_ct.setOnClickListener {
                    if (tvs_jcxx_ct.visibility==View.GONE){
                        tvs_jcxx_ct.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_ct.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztCt.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("编号:", ylPointEntity.yztCt.get(0)?.layer))
                tvDatas.add(TextViewsEntity("产权人:", ylPointEntity.yztCt.get(0)?.cqr))
                tvDatas.add(TextViewsEntity("拆除状态:", ylPointEntity.yztCt.get(0)?.lx))
                tvDatas.add(TextViewsEntity("面积:", ylPointEntity.yztCt.get(0)?.area.toString()+"亩"))
                tvDatas.add(TextViewsEntity("备注:", ylPointEntity.yztCt.get(0)?.remark))
                /* if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                     tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                 }else{
                     tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
                 }*/


                tvs_jcxx_ct?.setDatas(tvDatas)
                tvs_jcxx_ct?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_ct.visibility = View.GONE
            }
            if (ylPointEntity.yztSy.size>0){//拆腾 ylPointEntity.yztSy
                ll_jcxx_sy.visibility = View.VISIBLE
                tv_jcxx_sy.setOnClickListener {
                    if (tvs_jcxx_sy.visibility==View.GONE){
                        tvs_jcxx_sy.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_sy.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztSy.get(0)?.xzqmc))
                tvDatas.add(TextViewsEntity("面积:", ylPointEntity.yztSy.get(0)?.area.toString()+"亩"))
                tvDatas.add(TextViewsEntity("现状使用情况:", ylPointEntity.yztSy.get(0)?.layer))
                /*if (ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztLhEntities.get(0)?.area.toString()+"㎡"))
                }*/


                tvs_jcxx_sy?.setDatas(tvDatas)
                tvs_jcxx_sy?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_sy.visibility = View.GONE
            }
            if (ylPointEntity.yztFhEntity.yztFkEntities.size>0){
                ll_jcxx_fk.visibility = View.VISIBLE
                ll_jcxx_fk.setOnClickListener {
                    if (tvs_jcxx_fk.visibility==View.GONE){
                        tvs_jcxx_fk.visibility = View.VISIBLE
                    }else{
                        tvs_jcxx_fk.visibility = View.GONE
                    }
                }
                val tvDatas = ArrayList<TextViewsEntity>()
                tvDatas.add(TextViewsEntity("村名:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.jfmc))
                tvDatas.add(TextViewsEntity("地块编号:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.dkbh))
                if (ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                }else{
                    tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.area.toString()+"㎡"))
                }
                tvDatas.add(TextViewsEntity("地类名称:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.dlmc))
                tvDatas.add(TextViewsEntity("分类名称:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.flmc))
                tvDatas.add(TextViewsEntity("是否整治:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.sfzz))
                tvDatas.add(TextViewsEntity("整治年份:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.zznf.toString()))
                tvDatas.add(TextViewsEntity("现状情况:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.xzqk))
                tvDatas.add(TextViewsEntity("备注:", ylPointEntity.yztFhEntity.yztFkEntities.get(0)?.remark))

                tvs_jcxx_fk?.setDatas(tvDatas)
                tvs_jcxx_fk?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

            }else{
                ll_jcxx_fk.visibility = View.GONE
            }
            if (ylPointEntity.ylList.size>0){
                if (!ylPointEntity.ylList.get(0).fl2.equals("宅基地")&&ylPointEntity.feiZhaiList.size==0){//

                    ll_jcxx_gy.visibility = View.VISIBLE
                    tv_jcxx_gy.setText(ylPointEntity.ylList.get(0).ylTypeText+"信息")
                    tv_jcxx_gy.setOnClickListener {
                        if (tvs_jcxx_gy.visibility==View.GONE){
                            tvs_jcxx_gy.visibility = View.VISIBLE
                        }else{
                            tvs_jcxx_gy.visibility = View.GONE
                        }
                    }
                    val tvDatas = ArrayList<TextViewsEntity>()
                    tvDatas.add(TextViewsEntity("名称:", ylPointEntity.ylList.get(0)?.name))
                    tvDatas.add(TextViewsEntity("村名:", ylPointEntity.ylList.get(0)?.xzqmc))
                    tvDatas.add(TextViewsEntity("一级分类:", ylPointEntity.ylList.get(0)?.ylTypeText))
                    tvDatas.add(TextViewsEntity("二级分类:", ylPointEntity.ylList.get(0)?.fl2))
                    tvDatas.add(TextViewsEntity("土地使用性质(权属):", ylPointEntity.ylList.get(0)?.qs))
//                tvDatas.add(TextViewsEntity("类型:", ylPointEntity.ylList.get(0)?.ylTypeText))
//                tvDatas.add(TextViewsEntity("产权人:", ylPointEntity.ylList.get(0)?.cqr))
                    tvDatas.add(TextViewsEntity("户主名称:", ylPointEntity.ylList.get(0)?.hzmc))
                    if (ylPointEntity.ylList.get(0)?.area!!.compareTo(BigDecimal(10000))==1){
                        tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                    }else{
                        tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area.toString()+"㎡"))
                    }
                    if (ylPointEntity.ylList.get(0)?.jianzhuArea!!.compareTo(BigDecimal(10000))==1){
                        tvDatas.add(TextViewsEntity("建筑面积:", ylPointEntity.ylList.get(0)?.jianzhuArea!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                    }else{
                        tvDatas.add(TextViewsEntity("建筑面积:", ylPointEntity.ylList.get(0)?.jianzhuArea.toString()+"㎡"))
                    }
//            tvDatas.add(TextViewsEntity("建筑面积(万㎡):", ylPointEntity.ylList.get(0)?.dlmc))
//            tvDatas.add(TextViewsEntity("证件序号:", ylPointEntity.ylList.get(0)?.bh))
                    tvDatas.add(TextViewsEntity("备注:", ylPointEntity.ylList.get(0)?.remark))

                    tvs_jcxx_gy?.setDatas(tvDatas)
                    tvs_jcxx_gy?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

                }else{
                    ll_jcxx_gy.visibility = View.GONE
                }
                /*if (ylPointEntity.ylList.get(0).ylTypeText.equals("非宅")&&ylPointEntity.feiZhaiList.size==0){

                    ll_jcxx_fz.visibility = View.VISIBLE
                    ll_jcxx_fz.setOnClickListener {
                        if (tvs_jcxx_fz.visibility==View.GONE){
                            tvs_jcxx_fz.visibility = View.VISIBLE
                        }else{
                            tvs_jcxx_fz.visibility = View.GONE
                        }
                    }
                    val tvDatas = ArrayList<TextViewsEntity>()
                    tvDatas.add(TextViewsEntity("村名:", ylPointEntity.ylList.get(0)?.xzqmc))
                    tvDatas.add(TextViewsEntity("类型:", ylPointEntity.ylList.get(0)?.ylTypeText))
                    if (ylPointEntity.ylList.get(0)?.area!!.compareTo(BigDecimal(10000))>0){
                        tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area!!.divide(BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP).toString()+"万㎡"))
                    }else{
                        tvDatas.add(TextViewsEntity("占地面积:", ylPointEntity.ylList.get(0)?.area.toString()+"㎡"))
                    }
    //            tvDatas.add(TextViewsEntity("建筑面积(万㎡):", ylPointEntity.ylList.get(0)?.dlmc))
                    tvDatas.add(TextViewsEntity("名称:", ylPointEntity.ylList.get(0)?.name))
                    tvDatas.add(TextViewsEntity("证件序号:", ylPointEntity.ylList.get(0)?.bh))
                    tvDatas.add(TextViewsEntity("备注:", ylPointEntity.ylList.get(0)?.remark))

                    tvs_jcxx_fz?.setDatas(tvDatas)
                    tvs_jcxx_fz?.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

                }else{
                    ll_jcxx_fz.visibility = View.GONE
                }*/
            }else{
                ll_jcxx_gy.visibility = View.GONE
                ll_jcxx_fz.visibility = View.GONE
            }
            var zjdglList = ArrayList<ZjdglBean>()
            if (ylPointEntity.applyEntity.size>0) {//ylPointEntity.applyEntity.id != 0
                for (i in ylPointEntity.applyEntity){
                    zjdglList.add(ZjdglBean(i.name, i.iphone, i.sptypeText))
                }
            }
            if (ylPointEntity.zjdSqEntity.size>0) {//ylPointEntity.zjdSqEntity.id != 0
                for (i in ylPointEntity.zjdSqEntity){
                    zjdglList.add(ZjdglBean(i.sqName, i.iphone, i.sptypeText))
                }
            }
            if (ylPointEntity.zjdLzEntity.size>0) {//ylPointEntity.zjdLzEntity.id != 0
                for (i in ylPointEntity.zjdLzEntity){
                    zjdglList.add(ZjdglBean(i.sqName, i.iphone, i.sptypeText))
                }
            }
            if (ylPointEntity.zjdTcEntity.size>0) {
                for (i in ylPointEntity.zjdTcEntity){
                    zjdglList.add(ZjdglBean(i.sqName, i.iphone, i.sptypeText))
                }
            }
            point_zjdglxx.setOnClickListener {
                if (ll_point_zjdglxx.visibility == View.GONE) {
                    ll_point_zjdglxx.visibility = View.VISIBLE
                } else {
                    ll_point_zjdglxx.visibility = View.GONE
                }
            }
            if (zjdglList.size == 0) {
                point_zjdglxx.visibility = View.GONE
                ll_point_zjdglxx.visibility = View.GONE
            } else {
                point_zjdglxx.visibility = View.VISIBLE
                ll_point_zjdglxx.visibility = View.VISIBLE
            }
            rlv_zjdglxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rlv_zjdglxx.adapter = object : BaseQuickAdapter<ZjdglBean, BaseViewHolder>(R.layout.item_ywxx_zjdglxx, zjdglList) {
                override fun convert(helper: BaseViewHolder?, item: ZjdglBean?) {
                    helper!!.setText(R.id.tv_ywxx_zjdglxx_dsrxm, item!!.dsrxm)
                            .setText(R.id.tv_ywxx_zjdglxx_dsrdh, item!!.dsrdh)
                            .setText(R.id.tv_ywxx_zjdglxx_qk, item!!.qk)
                }

            }
            point_hbjcxx.setOnClickListener {
                if (ll_point_hbjcxx.visibility == View.GONE) {
                    ll_point_hbjcxx.visibility = View.VISIBLE
                } else {
                    ll_point_hbjcxx.visibility = View.GONE
                }
            }
            if (ylPointEntity.pjEnviorSupvsEntity.size == 0) {
                point_hbjcxx.visibility = View.GONE
                ll_point_hbjcxx.visibility = View.GONE
            } else {
                point_hbjcxx.visibility = View.VISIBLE
                ll_point_hbjcxx.visibility = View.VISIBLE
            }
            rlv_hbjcxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rlv_hbjcxx.adapter = object : BaseQuickAdapter<PjEnviorSupvsEntity, BaseViewHolder>(R.layout.item_ywxx_hbjcxx, ylPointEntity.pjEnviorSupvsEntity) {
                override fun convert(helper: BaseViewHolder?, item: PjEnviorSupvsEntity?) {
                    helper!!.setText(R.id.tv_ywxx_hbjcxx_bh, "")//item!!.projcode
                            .setText(R.id.tv_ywxx_hbjcxx_fpbm, "")//分配问题//item!!.typeText
                            .setText(R.id.tv_ywxx_hbjcxx_jlsj, item!!.jltime)
                    if (item!!.warning == 0) {
                        helper.setText(R.id.tv_ywxx_hbjcxx_yj, "正常")
                    } else {
                        helper.setText(R.id.tv_ywxx_hbjcxx_yj, "超时")
                    }


                }
            }
            point_zjdxcxx.setOnClickListener {
                //ll_point_zjdxcxx
                if (ll_point_zjdxcxx.visibility == View.GONE) {
                    ll_point_zjdxcxx.visibility = View.VISIBLE
                } else {
                    ll_point_zjdxcxx.visibility = View.GONE
                }
            }
            if (ylPointEntity.zjdIllegal.size == 0) {
                point_zjdxcxx.visibility = View.GONE
                ll_point_zjdxcxx.visibility = View.GONE
            } else {
                point_zjdxcxx.visibility = View.VISIBLE
                ll_point_zjdxcxx.visibility = View.VISIBLE
            }
            rlv_zjdxcxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rlv_zjdxcxx.adapter = object : BaseQuickAdapter<IllegalEntity, BaseViewHolder>(R.layout.item_ywxx_zjdxcxx, ylPointEntity.zjdIllegal) {
                override fun convert(helper: BaseViewHolder?, item: IllegalEntity?) {
                    helper!!.setText(R.id.rv_ywxx_zjdxcxx_cm, item!!.xzqmc)
                            .setText(R.id.rv_ywxx_zjdxcxx_mph, item!!.bh)
                            .setText(R.id.rv_ywxx_zjdxcxx_hzmc, item!!.hzmc)
                            .setText(R.id.rv_ywxx_zjdxcxx_wfqk, item!!.situation)
                            .setText(R.id.rv_ywxx_zjdxcxx_mqzt, item!!.status)
                            .setText(R.id.rv_ywxx_zjdxcxx_remark, item!!.remark)
                }
            }
            point_tdxcxx.setOnClickListener {
                //ll_point_tdxcxx
                if (ll_point_tdxcxx.visibility == View.GONE) {
                    ll_point_tdxcxx.visibility = View.VISIBLE
                } else {
                    ll_point_tdxcxx.visibility = View.GONE
                }
            }
            if (ylPointEntity.tdIllegal.size == 0) {
                point_tdxcxx.visibility = View.GONE
                ll_point_tdxcxx.visibility = View.GONE
            } else {
                point_tdxcxx.visibility = View.VISIBLE
                ll_point_tdxcxx.visibility = View.VISIBLE
            }
            rlv_tdxcxx.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rlv_tdxcxx.adapter = object : BaseQuickAdapter<IllegalEntity, BaseViewHolder>(R.layout.item_ywxx_zjdxcxx, ylPointEntity.tdIllegal) {
                override fun convert(helper: BaseViewHolder?, item: IllegalEntity?) {
                    helper!!.setText(R.id.rv_ywxx_zjdxcxx_cm, item!!.xzqmc)
                            .setText(R.id.rv_ywxx_zjdxcxx_mph, item!!.bh)
                            .setText(R.id.rv_ywxx_zjdxcxx_hzmc, item!!.hzmc)
                            .setText(R.id.rv_ywxx_zjdxcxx_wfqk, item!!.situation)
                            .setText(R.id.rv_ywxx_zjdxcxx_mqzt, item!!.status)
                            .setText(R.id.rv_ywxx_zjdxcxx_remark, item!!.remark)
                }

            }

        }

    }

    fun showLoading(title: String?) { //正在请求数据
        LoadingDialog.showDialogForLoading(activity)
    }
    fun stopLoading() { //请求数据结束
        LoadingDialog.cancelDialogForLoading()
    }
    fun showErrorTip(msg: String?) { //请求信息失败，返回的失败原因
        ToastUtils.showShort(msg)
    }
}
