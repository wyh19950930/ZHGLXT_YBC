package com.jymj.zhglxt.xm.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.model.LatLng
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mikephil.charting.charts.PieChart
import com.jymj.zhglxt.R
import com.jymj.zhglxt.app.AppApplication
import com.jymj.zhglxt.util.AppInstalledUtils
import com.jymj.zhglxt.util.DensityUtil
import com.jymj.zhglxt.util.PositionUtil
import com.jymj.zhglxt.widget.popup.CommonPopupWindow
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout2
import com.jymj.zhglxt.xm.adapter.XmFirstListBqAdapter
import com.jymj.zhglxt.xm.bean.BcProjectEntity
import com.jymj.zhglxt.xm.bean.BcProjectFile
import com.jymj.zhglxt.xm.contract.XmIssueContract
import com.jymj.zhglxt.xm.contract.XmTzDetailContract
import com.jymj.zhglxt.xm.presenter.XmIssuePresenter
import com.jymj.zhglxt.xm.presenter.XmTzDetailPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_xm_tz_detail.*
import me.iwf.photopicker.PhotoPreview
import java.text.DecimalFormat

class XmTzDetailActivity : BaseActivity<XmTzDetailPresenter, XmTzDetailContract.Model>(), XmTzDetailContract.View {

    var popupWindow : CommonPopupWindow ?= null
    var isCollection = false//是否感兴趣

    override fun getLayoutId(): Int {
        return R.layout.activity_xm_tz_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        val xmId = intent.getLongExtra("id", 0L)
        mPresenter.getXmList(xmId)

        if (AppCache.getInstance().duties == 1){
//            ll_act_xm_tz_detail_bottom.visibility = View.VISIBLE
            iv_act_xmtzdetail_more.visibility = View.GONE
            iv_act_xmtzdetail_share.visibility = View.VISIBLE
        }else{
//            ll_act_xm_tz_detail_bottom.visibility = View.GONE
            iv_act_xmtzdetail_more.visibility = View.VISIBLE
            iv_act_xmtzdetail_share.visibility = View.GONE
        }

        iv_act_xmtzdetail_back.setOnClickListener {
            finish()
        }
        tv_act_xm_tz_detail_wx.setOnClickListener {
            ToastUtils.showShort("微信")
        }
        tv_act_xm_tz_detail_kf.setOnClickListener {
            ToastUtils.showShort("客服")
        }
        ll_act_xm_tz_detail_gxq.setOnClickListener {
            if (isCollection){
                mPresenter.getProjectDeleteCollection(xmId)
            }else{
                mPresenter.getProjectSaveCollection(xmId)
            }
//            ToastUtils.showShort("感兴趣")
        }
        iv_act_xmtzdetail_more.setOnClickListener {
            popupWindow = object : CommonPopupWindow.Builder(this) {}
                    .setBackGroundLevel(0.8f)
                    .setView(R.layout.pop_xm_tz_detail_more)
                    .setWidthAndHeight(DensityUtil.dp2px(this, 120f),
                            DensityUtil.dp2px(this, 120f))
                    .setAnimationStyle(R.style.pop_anim)
                    .setViewOnclickListener(object :CommonPopupWindow.ViewInterface{
                        override fun getChildView(view: View?, layoutResId: Int) {
                            val tz_detail_bj = view!!.findViewById<LinearLayout>(R.id.pop_xm_tz_detail_bj)
                            val tz_detail_sc = view.findViewById<LinearLayout>(R.id.pop_xm_tz_detail_sc)

                            tz_detail_bj.setOnClickListener {
                                val intent = Intent(this@XmTzDetailActivity,XmIssueActivity::class.java)
                                intent.putExtra("id",xmId)
                                startActivity(intent)
                                popupWindow!!.dismiss()
                            }
                            tz_detail_sc.setOnClickListener {
//                                ToastUtils.showShort
                                val content = TextView(this@XmTzDetailActivity)
                                content.text = "是否删除？"
                                SweetAlertDialog(this@XmTzDetailActivity, SweetAlertDialog.NORMAL_TYPE)
                                        .setTitleText("用地删除")
                                        .setCustomView(content)
                                        .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->

                                            mPresenter.getXmDelete(xmId)
                                            sweetAlertDialog.dismiss()}
                                        .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                        .show()
                                popupWindow!!.dismiss()
                            }
                        }
                    })
                    .setOutsideTouchable(true)
                    .create()
            popupWindow!!.showAsDropDown(iv_act_xmtzdetail_more,30,-50)//后两个参数是显示坐标



        }

        mtl_act_xm_tz_detail_xmyd.setOnTitleListLister(object :MeTitleLayout2.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_xm_tz_detail_xmyd.isShown){
                    ll_act_xm_tz_detail_xmyd.visibility = View.GONE
                }else{
                    ll_act_xm_tz_detail_xmyd.visibility = View.VISIBLE
                }
            }

        })

        mtl_act_xm_tz_detail_xmjs.setOnTitleListLister(object :MeTitleLayout2.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_xm_tz_detail_xmjs.isShown){
                    ll_act_xm_tz_detail_xmjs.visibility = View.GONE
                }else{
                    ll_act_xm_tz_detail_xmjs.visibility = View.VISIBLE
                }
            }

        })

    }

    override fun initDatas() {

    }

    override fun returnXmList(message: List<BcProjectEntity>) {
        if (message.size>0){
            val bcProjectEntity = message.get(0)

            ll_act_xm_tz_detail_xmwz.setOnClickListener {//点击跳转高德地图 或者百度
                val center1 = getCenter(bcProjectEntity.location)//ylEntity
                if (AppInstalledUtils.isAppInstalled(this, "com.autonavi.minimap")){//跳转高德地图
                    var intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    //获取本地存储的百度经纬度
                    //获取本地存储的百度经纬度

                    val model = PositionUtil.gcj_To_Gps84(39.76906, 116.33841)//自己维度经度
//                            val transform = PositionUtil.transform(center1.latitude, center1.longitude)//84转火星
                    val transform = PositionUtil.wgs84togcj02(center1.longitude, center1.latitude)//目的地经度纬度
//                            val point = LatLng(model.wgLat, model.wgLon)
                    if (!AppCache.getInstance().zijiLocation.equals("")){
                        val center = getCenter(AppCache.getInstance().zijiLocation)
                        val uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + center.latitude.toString()  + "&slon=" + center.longitude.toString() +
                                "&sname=" + "自己位置" + "&did=BGVIS2&dlat=" + transform[0].toString() + "&dlon=" +
                                transform[1].toString() + "&dname=" + "目的地" + "&dev=0&t=0")//数字替换自己纬度经度
                        intent.data = uri
                        //启动该页面即可
                        startActivity(intent)

                    }
                }else if (AppInstalledUtils.isAppInstalled(this, "com.baidu.BaiduMap")){//跳转百度地图
                    var intent = Intent()
                    intent.data = Uri.parse("baidumap://map/direction?region=目的地&destination=" + center1.latitude.toString() +
                            "," + center1.longitude.toString() + "&mode=driving" +//+ "&destination=" + ""
                            "&coord_type=wgs84")
                    startActivity(intent)
                }else{
                    ToastUtils.showShort("请安装百度地图或高德地图")

                }

            }
            tv_act_xm_tz_title.setText(bcProjectEntity.title)
            tv_act_xm_tz_detail_title.setText(bcProjectEntity.title)
            rlv_act_xm_tz_bz.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
            rlv_act_xm_tz_bz.setAdapter(XmFirstListBqAdapter(this, bcProjectEntity.getProjectLxList()))
            tv_act_xm_tz_content.setText(bcProjectEntity.content)
            var chuling = DecimalFormat("###################.###########");
            tv_act_xm_tz_ydgm.setText(chuling.format(bcProjectEntity.ydgm).toString()+"亩")
            tv_act_xm_tz_jzgm.setText(chuling.format(bcProjectEntity.jzgm).toString()+"㎡")
            tv_act_xm_tz_cdmj.setText("直接服务的非建设空间面积"+chuling.format(bcProjectEntity.cdmj).toString()+"亩")
            tv_act_xm_tz_tzgm.setText(chuling.format(bcProjectEntity.tzgm1).toString()+" - "+chuling.format(bcProjectEntity.tzgm2).toString()+"万元")
            val imgList = ArrayList<String>()
            val imgTitleList = ArrayList<String>()
            for (i in bcProjectEntity.projectFiles){
                imgList.add(i.url)
                imgTitleList.add(i.sorting.toString())
            }
            banner_act_xm_tz_detail.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                    .setDelayTime(6000)
                    .setImageLoader(NetViewHolder())
                    .setBannerTitles(imgTitleList)
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .setImages(imgList).setOnBannerListener(object:OnBannerListener{
                        override fun OnBannerClick(position: Int) {
                            PhotoPreview.builder()
                                    .setPhotos(imgList)
                                    .setCurrentItem(position)
                                    .setShowDeleteButton(false)
                                    .start(this@XmTzDetailActivity)
//                            ToastUtils.showShort("点击第"+position+"张")
                        }
                    })
            banner_act_xm_tz_detail.start()
            tv_act_xm_tz_xzq.setText(bcProjectEntity.xzq)
            tv_act_xm_tz_xxdz.setText(bcProjectEntity.zhen+""+bcProjectEntity.xzqmc)
            /*必须在设置Adapter之前，否则可能不会产生效果。*/
//            rlv_act_xm_tz_xmjs.isNestedScrollingEnabled = false
            rlv_act_xm_tz_xmjs.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            /*rlv_act_xm_tz_xmjs.layoutManager = object:LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }*/
//            ToastUtils.showShort("${bcProjectEntity.projectFiles1.size}")
            rlv_act_xm_tz_xmjs.adapter = object:BaseQuickAdapter<BcProjectFile,BaseViewHolder>
            (R.layout.item_xm_tz_detail_image,bcProjectEntity.projectFiles1){
                override fun convert(helper: BaseViewHolder?, item: BcProjectFile?) {
                    val ivActXmTzDetail = helper!!.getView<ImageView>(R.id.iv_act_xm_tz_detail)

                    val notesFile = item!!.url
                    val options = RequestOptions().error(R.drawable.shopping_mall_banner)//.transforms(RoundedCorners(30))//图片圆角为30
                    Glide.with(this@XmTzDetailActivity).load(notesFile) //图片地址
                            .apply(options)
                            .into(ivActXmTzDetail)
                }
            }
            isCollection = bcProjectEntity.isCollection
            if (bcProjectEntity.isCollection){
                iv_act_xm_tz_detail_xin.setImageResource(R.drawable.xm_tz_gxq_icon_shi)
            }else{
                iv_act_xm_tz_detail_xin.setImageResource(R.drawable.xm_tz_gxq_icon)
            }

        }
    }

    override fun returnXmDelete(message: String) {
        AppCache.getInstance().isIssueXm = true
        ToastUtils.showShort(message)
        finish()
    }
    override fun returnProjectSaveCollection(message: String) {
        isCollection = true
        iv_act_xm_tz_detail_xin.setImageResource(R.drawable.xm_tz_gxq_icon_shi)
        ToastUtils.showShort("收藏成功")
    }

    override fun returnProjectDeleteCollection(message: String) {
        isCollection = false
        AppCache.getInstance().isQxSc = true
        iv_act_xm_tz_detail_xin.setImageResource(R.drawable.xm_tz_gxq_icon)
        ToastUtils.showShort("取消收藏成功")
    }

    override fun returnAddUser(message: String) {
    }

    fun getCenter(center: String): LatLng {
        if (center != null) {
            if (!center.equals("")) {
                val points = center.substring(6, center.length - 1).split(" ").toTypedArray()
                return if (points != null && points.size > 1) {
                    val converter = CoordinateConverter()
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS)
                    val sl = LatLng(points[1].toDouble(), points[0].toDouble())
                    // sourceLatLng待转换坐标点 LatLng类型
                    converter.coord(sl)
                    val latLng = converter.convert()
                    sl
                } else {
                    LatLng(0.0, 0.0)
                }
            } else {
                LatLng(0.0, 0.0)
            }
        }
        return LatLng(0.0, 0.0)
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


     class NetViewHolder : ImageLoader() {
         override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
             if (!(context as Activity).isDestroyed&&path!=null&&imageView!=null){
                 Glide.with(context).load(path).into(imageView!!)
             }
         }
    }
}
