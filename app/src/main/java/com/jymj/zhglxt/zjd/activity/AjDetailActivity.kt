package com.jymj.zhglxt.zjd.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/*import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest*/
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.pickerview.ProvinceBean
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.yl.ApplyFileEnum
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout1
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout
import com.jymj.zhglxt.zjd.adapter.TagBaseAdapter
import com.jymj.zhglxt.zjd.adapter.ZsdAdapter
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseFileEntity
import com.jymj.zhglxt.zjd.bean.fj.Renovated
import com.jymj.zhglxt.zjd.bean.jsjb.*
import com.jymj.zhglxt.zjd.contract.AjDetailActContract
import com.jymj.zhglxt.zjd.presenter.AjDetailActPresenter
import com.loc.i
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_aj_detail.*
import kotlinx.android.synthetic.main.activity_qygl_detail.*
import org.json.JSONArray
import java.io.File

class AjDetailActivity : BaseActivity<AjDetailActPresenter, AjDetailActContract.Model>(), AjDetailActContract.View {

    var opinionEntity = OpinionEntity()
    var opinionEntity1 = OpinionEntity()

    override fun getLayoutId(): Int {
        return R.layout.activity_aj_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {

//        AppCache.getInstance().isJsjb = false
        ic_act_aj_detail_back.setOnClickListener {
            finish()
        }
        mtl1_act_aj_detail_jcqk.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_aj_detail_jcqk.isShown){
                    ll_act_aj_detail_jcqk.visibility = View.GONE
                }else{
                    ll_act_aj_detail_jcqk.visibility = View.VISIBLE
                }
            }
        })
        mtl1_act_aj_detail_ldrxx.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_aj_detail_ldrxx.isShown){
                    ll_act_aj_detail_ldrxx.visibility = View.GONE
                }else{
                    ll_act_aj_detail_ldrxx.visibility = View.VISIBLE
                }
            }
        })
        mtl1_act_ajcl_detail_clxx.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_ajcl_detail_clxx.isShown){
                    ll_act_ajcl_detail_clxx.visibility = View.GONE
                }else{
                    ll_act_ajcl_detail_clxx.visibility = View.VISIBLE
                }
            }
        })
        mtl1_act_ajcl_detail_pabm.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_ajcl_detail_pabm.isShown){
                    ll_act_ajcl_detail_pabm.visibility = View.GONE
                }else{
                    ll_act_ajcl_detail_pabm.visibility = View.VISIBLE
                }
            }
        })

        //????????????
        mtl1_act_ajcl_detail_tdyy.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (rlv_act_aj_detail_tdyy.isShown){
                    rlv_act_aj_detail_tdyy.visibility = View.GONE
                }else{
                    rlv_act_aj_detail_tdyy.visibility = View.VISIBLE
                }
            }
        })
        //????????????
        mtl1_act_ajcl_detail_tdbh.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (rlv_act_aj_detail_tdbh.isShown){
                    rlv_act_aj_detail_tdbh.visibility = View.GONE
                }else{
                    rlv_act_aj_detail_tdbh.visibility = View.VISIBLE
                }
            }
        })
        //????????????
        mtl1_act_ajcl_detail_ysbh.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (rlv_act_aj_detail_ysbh.isShown){
                    rlv_act_aj_detail_ysbh.visibility = View.GONE
                }else{
                    rlv_act_aj_detail_ysbh.visibility = View.VISIBLE
                }
            }
        })
        //????????????
        mtl1_act_ajcl_detail_gdbh.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (rlv_act_aj_detail_gdbh.isShown){
                    rlv_act_aj_detail_gdbh.visibility = View.GONE
                }else{
                    rlv_act_aj_detail_gdbh.visibility = View.VISIBLE
                }
            }
        })

        mtl1_act_ajcl_detail_clyj.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_ajcl_detail_clyj.isShown){
                    ll_act_ajcl_detail_clyj.visibility = View.GONE
                }else{
                    ll_act_ajcl_detail_clyj.visibility = View.VISIBLE
                }
            }
        })
        mtl1_act_ajcl_detail_rwyssq.setOnTitleListLister(object:MeTitleLayout1.OnTitleListLister{
            override fun onClick(name: String?) {
                if (ll_act_ajcl_detail_rwyssq.isShown){
                    ll_act_ajcl_detail_rwyssq.visibility = View.GONE
                }else{
                    ll_act_ajcl_detail_rwyssq.visibility = View.VISIBLE
                }
            }
        })
        mtl1_act_ajcl_detail_fjxx.setOnClickListener {
            if (rlv_act_ajcl_detail_fjxx.isShown){
                iv_act_ajcl_detail_fjxx1.setImageResource(R.drawable.back_left_icon)
                rlv_act_ajcl_detail_fjxx.visibility = View.GONE
            }else{
                iv_act_ajcl_detail_fjxx1.setImageResource(R.drawable.item_title_down)
                rlv_act_ajcl_detail_fjxx.visibility = View.VISIBLE
            }
        }
        ll_act_ajcl_detail_czwc.setOnClickListener {

            if (ll_act_ajcl_detail_czwc1.isShown){//iv_act_ajcl_detail_down
                iv_act_ajcl_detail_down.setImageResource(R.drawable.back_left_icon)
                ll_act_ajcl_detail_czwc1.visibility = View.GONE
            }else{
                iv_act_ajcl_detail_down.setImageResource(R.drawable.item_title_down)
                ll_act_ajcl_detail_czwc1.visibility = View.VISIBLE

            }
        }

        but_act_aj_detail_xf.setOnClickListener {
//            ToastUtils.showShort("??????")
            /*opinionEntity.casebh = zel_act_aj_detail_ajbh.value
            opinionEntity.callTime = zel_act_aj_detail_ldsj.value
            opinionEntity.citybh = zel_act_aj_detail_sjbh.value
            opinionEntity.citypfr = zel_act_aj_detail_spfr.value
            opinionEntity.citykeyword = zel_act_aj_detail_sgjc.value
            //?????????????????????
            opinionEntity.citizenName = zel_act_aj_detail_smxm.value
            opinionEntity.phone = zel_act_aj_detail_lxdh.value
            opinionEntity.callPhone = zel_act_aj_detail_ldhm.value
            opinionEntity.address = zel_act_aj_detail_lxdz.value
            opinionEntity.remark = zel_act_aj_detail_remark.value

            opinionEntity.territorial = zel_act_ajcl_detail_sd.value
            opinionEntity.reflecting = zel_act_ajcl_detail_bfyz.value
            opinionEntity.reflectingPhone = zel_act_ajcl_detail_bfyzdh.value
            opinionEntity.title = zel_act_ajcl_detail_bt.value
            opinionEntity.happenAddress = zel_act_ajcl_detail_fsdz.value
            opinionEntity.problemDescription = zel_act_ajcl_detail_wtms.value
            opinionEntity.municipalOpinion = zel_act_ajcl_detail_sjclyj.value*/
            opinionEntity.qutype = 1
            mPresenter.getJsjbUpdate(opinionEntity)

        }
        but_act_aj_detail_sh.setOnClickListener {
            ToastUtils.showShort("??????")
        }
        but_act_aj_detail_qs.setOnClickListener {
            opinionEntity.qutype =2
            mPresenter.getJsjbUpdate(opinionEntity)
//            ToastUtils.showShort("??????")
        }
        but_act_aj_detail_cxxf.setOnClickListener {//???
            opinionEntity.qutype = 1
            mPresenter.getJsjbUpdate(opinionEntity)
//            ToastUtils.showShort("????????????")
        }
        but_act_aj_detail_bh.setOnClickListener {
            PopuJsjbUtils().initPopuTd(this,ll_act_aj_detail,"????????????",object:PopuJsjbUtils.OnTdLinear{
                override fun onClick(sysXzqEntities: String?) {
//                    val opinionRejectedEntity = OpinionRejectedEntity()
                    val opinionRejectedEntity = tdyyList.get(0)
                    opinionRejectedEntity.type = 100
                    opinionRejectedEntity.rejected = sysXzqEntities
                    opinionRejectedEntity.through = 1
                    opinionEntity1.opinionRejectedEntities.add(opinionRejectedEntity)
                    opinionEntity1.qutype =1
                    mPresenter.getJsjbUpdate(opinionEntity1)//opinionEntity
                }
            })
        }
        but_act_aj_detail_ty.setOnClickListener {//????????????
            if (opinionEntity.opinionDelayEntities.size>0){
//                ToastUtils.showShort(TimeUtil.getDateBeforeSfm(opinionEntity.opinionDelayEntities.get(0).delay))
                opinionEntity.deadline = TimeUtil.getDateBeforeSfm(opinionEntity.opinionDelayEntities.get(0).delay)
                opinionEntity.opinionDelayEntities.get(0).through = 2
            }
            opinionEntity.qutype = 2
            mPresenter.getJsjbUpdate(opinionEntity)
        }
        but_act_aj_detail_jj.setOnClickListener {//????????????
            PopuJsjbUtils().initPopuTd(this,ll_act_aj_detail,"??????????????????",object:PopuJsjbUtils.OnTdLinear{
                override fun onClick(sysXzqEntities: String?) {
                    if (opinionEntity1.opinionDelayEntities.size>0){
                        opinionEntity1.opinionDelayEntities.get(0).rejected = sysXzqEntities
                        opinionEntity1.opinionDelayEntities.get(0).through = 1
                    }
                    opinionEntity1.qutype = 2
                    mPresenter.getJsjbUpdate(opinionEntity1)//opinionEntity
                }
            })
        }
        //????????????
        but_act_aj_detail_tjsh.setOnClickListener {
            opinionEntity.opinion = zel_act_ajcl_detail_yjxq.value
            opinionEntity.qutype = 3
            mPresenter.getJsjbUpdate(opinionEntity)
        }
        but_act_aj_detail_sqys.setOnClickListener { //????????????
            PopuJsjbUtils().initPopuSqys(this,ll_act_aj_detail,object:PopuJsjbUtils.OnSqysLinear{
                override fun onClick(sysXzqEntities: OpinionDelayEntity?) {
                    opinionEntity.opinionDelayEntities.add(sysXzqEntities)
                    opinionEntity.qutype = 6
                    mPresenter.getJsjbUpdate(opinionEntity)
                }
            })
        }
        but_act_aj_detail_qs1.setOnClickListener {
//            ToastUtils.showShort("??????")
            opinionEntity.qutype =2
            mPresenter.getJsjbUpdate(opinionEntity)
        }
        but_act_aj_detail_td.setOnClickListener {
//            ToastUtils.showShort("??????")
            PopuJsjbUtils().initPopuTd(this,ll_act_aj_detail,"????????????",object:PopuJsjbUtils.OnTdLinear{
                override fun onClick(sysXzqEntities: String?) {
//                    ToastUtils.showShort(sysXzqEntities)
                    val opinionRejectedEntity = OpinionRejectedEntity()
                    opinionRejectedEntity.type = 100
                    opinionRejectedEntity.chargeback = sysXzqEntities
                    opinionRejectedEntity.through = 0
                    opinionEntity.opinionRejectedEntities.add(opinionRejectedEntity)
                    opinionEntity.qutype = 5
                    mPresenter.getJsjbUpdate(opinionEntity)
                }
            })
        }
        but_act_aj_detail_hfgd.setOnClickListener {//??????
            opinionEntity.disposalOpinion = zel_act_ajcl_detail_czyj.value
            opinionEntity.qutype = 4
            mPresenter.getJsjbUpdate(opinionEntity)
        }
        but_act_aj_detail_hfbh.setOnClickListener {//????????????

            PopuJsjbUtils().initPopuTd(this,ll_act_aj_detail,"????????????",object:PopuJsjbUtils.OnTdLinear{
                override fun onClick(sysXzqEntities: String?) {
//                    ToastUtils.showShort(sysXzqEntities)
                    val opinionRejectedEntity = OpinionRejectedEntity()
                    opinionRejectedEntity.type = 101
                    opinionRejectedEntity.opinion = sysXzqEntities
                    opinionRejectedEntity.through = 1
                    opinionEntity1.opinionRejectedEntities.add(opinionRejectedEntity)
                    opinionEntity1.qutype = 2
                    mPresenter.getJsjbUpdate(opinionEntity1)
                }
            })
        }
        iv_act_ajcl_detail_fjxx.setOnClickListener {//???????????? ????????????
            PopuJsjbUtils().initPopuDkwj(this,ll_act_aj_detail,235)
//            ToastUtils.showShort("????????????")
        }
        iv_act_ajcl_detail_scwj.setOnClickListener {//????????????  ????????????
            PopuJsjbUtils().initPopuDkwj(this,ll_act_aj_detail,234)
//            ToastUtils.showShort("????????????")
        }

        tv_act_aj_detail_jcqk.setOnClickListener { //?????????????????????

        }
        tv_act_aj_detail_ldrxx.setOnClickListener { //????????????????????????

        }
        tv_act_aj_detail_clxx.setOnClickListener { //?????????????????????

        }
        tv_act_aj_detail_pdbm.setOnClickListener { //?????????????????????

        }
        tv_act_ajcl_detail_clyj.setOnClickListener { //????????????

        }
        tv_act_sj_detail_hfjl.setOnClickListener { //????????????

        }
        tv_act_aj_detail_rwyssq.setOnClickListener { //????????????

        }


    }

    override fun initDatas() {
        opinionEntity1 = intent.getSerializableExtra("opinionEntity") as OpinionEntity
        mPresenter.getJsjbInfo(opinionEntity1.id)
        initOnClick()//??????????????????
        mPresenter.getOpinionGetDepart()
        tl_act_aj_detail.addTab(tl_act_aj_detail.newTab().setText("????????????"))
        tl_act_aj_detail.addTab(tl_act_aj_detail.newTab().setText("????????????"))
        tl_act_aj_detail.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {//ll_act_aj_detail_ajxx  ll_act_aj_detail_ajcl
                if (tab?.text.toString().equals("????????????")){
                    ll_act_aj_detail_ajxx.visibility = View.VISIBLE
                    ll_act_aj_detail_ajcl.visibility = View.GONE
                }else{
                    ll_act_aj_detail_ajxx.visibility = View.GONE
                    ll_act_aj_detail_ajcl.visibility = View.VISIBLE
                }
//                ToastUtils.showShort(tab?.text.toString())
            }
        })


    }

    private fun initOnClick() {
        zel_act_aj_detail_ajly.setOnClickListener { //???????????? - ????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_aj_detail_ajly.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.caseSource = jsjbEnumBean!!.index
                    zel_act_aj_detail_ajly.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_aj_detail_ldsj.setOnClickListener { //????????????
            if (SingleOnClickUtil1.isFastClick())
                TimePickerUtil.initTimePickerViewNyr(this, zel_act_aj_detail_ldsj.value.toString(), object : TimePickerUtil.OnTimePickerLister {
                    override fun onClick(data: String?) { //????????????
                        opinionEntity.callTime = data
                        zel_act_aj_detail_ldsj.setText(data)
                    }
                })
        }
        zel_act_aj_detail_ldzt.setOnClickListener { //????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_aj_detail_ldzt.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.callSubject = jsjbEnumBean!!.index
                    zel_act_aj_detail_ldzt.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_aj_detail_sjly.setOnClickListener { //????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_aj_detail_sjly.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.citySource = jsjbEnumBean!!.index
                    zel_act_aj_detail_sjly.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_aj_detail_tsrqlx.setOnClickListener { //?????????????????? - ????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_aj_detail_tsrqlx.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.specialtype = jsjbEnumBean!!.index
                    zel_act_aj_detail_tsrqlx.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_aj_detail_pdlx.setOnClickListener { //????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_aj_detail_pdlx.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.sendType = jsjbEnumBean!!.index
                    zel_act_aj_detail_pdlx.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_aj_detail_gdbq.setOnClickListener { //????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_aj_detail_gdbq.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.workLabel = jsjbEnumBean!!.index
                    zel_act_aj_detail_gdbq.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_aj_detail_hjdz.setOnClickListener {
            JsjbPickerUtil.initHjdzPickerView(this,zel_act_aj_detail_hjdz.value, "????????????", object : JsjbPickerUtil.OnJsjbHjdzLinear {
                override fun onClick(sheng: String?, shi: String?, xian: String?) {
                    opinionEntity.province = sheng
                    opinionEntity.city = shi
                    opinionEntity.county = xian
                    zel_act_aj_detail_hjdz.setText(sheng + "-" + shi + "-" + xian)
                }
            })
        }


        zel_act_ajcl_detail_ajfl.setOnClickListener { //???????????? - ????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_ajfl.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.caseType = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_ajfl.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_wtfl.setOnClickListener {
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_wtfl.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.problemType = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_wtfl.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_qjwtfl.setOnClickListener {
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_qjwtfl.value, "???????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.districtProblemType = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_qjwtfl.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_bwdys.setOnClickListener { //???????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_bwdys.value, "???????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.unstable = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_bwdys.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_jjaj.setOnClickListener {
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_jjaj.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.urgentCase = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_jjaj.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_sysj.setOnClickListener {//?????????????????????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_sysj.value, "????????????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {//TimeUtil.getDateBefore(30)
//                    opinionEntity.deadline = jsjbEnumBean!!.name
                    if (jsjbEnumBean!!.name.equals("??????24??????")){
                        opinionEntity.deadline = TimeUtil.getDateBeforeSfm(1)
                    }else if (jsjbEnumBean!!.name.equals("??????48??????")){
                        opinionEntity.deadline = TimeUtil.getDateBeforeSfm(2)
                    }else if (jsjbEnumBean!!.name.equals("??????72??????")){
                        opinionEntity.deadline = TimeUtil.getDateBeforeSfm(3)
                    }
                    zel_act_ajcl_detail_sysj.setText(opinionEntity.deadline)
//                    ToastUtils.showShort(opinionEntity.deadline)
                }
            })
        }
        zel_act_ajcl_detail_qzpd.setOnClickListener { //???????????? - ??????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_qzpd.value, "??????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.mandatory = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_qzpd.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_sfss.setOnClickListener { //???????????? - ??????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_sfss.value, "??????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.verified = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_sfss.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_sfsyszc.setOnClickListener { //????????????????????? - ??????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_sfsyszc.value, "??????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.syszc = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_sfsyszc.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_sffk.setOnClickListener { //???????????? - ??????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_sffk.value, "??????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.feedback = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_sffk.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_sfyx.setOnClickListener { //???????????? - ??????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_sfyx.value, "??????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.effective = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_sfyx.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_sfmy.setOnClickListener { //???????????? - ??????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_sfmy.value, "??????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.satisfaction = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_sfmy.setText(jsjbEnumBean!!.name)
                }
            })
        }
        zel_act_ajcl_detail_sfjj.setOnClickListener { //???????????? - ??????
            JsjbPickerUtil.initNationPickerView(this,zel_act_ajcl_detail_sfjj.value, "??????", object : JsjbPickerUtil.OnJsjbLinear {
                override fun onClick(jsjbEnumBean: JsjbEnumBean?) {
                    opinionEntity.solve = jsjbEnumBean!!.index
                    zel_act_ajcl_detail_sfjj.setText(jsjbEnumBean!!.name)
                }
            })
        }

        zel_act_ajcl_detail_czsj.setOnClickListener { //????????????
            if (SingleOnClickUtil1.isFastClick())
                TimePickerUtil.initTimePickerViewNyr(this, zel_act_ajcl_detail_czsj.value.toString(), object : TimePickerUtil.OnTimePickerLister {
                    override fun onClick(data: String?) { //????????????
                        opinionEntity.callTime = data
                        zel_act_ajcl_detail_czsj.setText(data)
                    }
                })
        }

    }

    override fun returnFanJianDetail(renovated: Renovated) {

    }
    var bmList = ArrayList<SysDepartEntity>()
    //????????????
    override fun returnOpinionGetDepart(renovated: List<SysDepartEntity>) {
        bmList.clear()
        bmList.addAll(renovated)
        val arrayList = ArrayList<String>()
        for (i in renovated){
            arrayList.add(i.name)
        }
        zel_act_ajcl_detail_zzbm.setOnClickListener {
            JsjbPickerUtil.initSelectPickerView(this,zel_act_ajcl_detail_zzbm.value,"????????????",arrayList,object : JsjbPickerUtil.OnSelectLinear {
                override fun onClick(sheng: String?) {
                    if (arrayList.size>0){
                        opinionEntity.responsible = renovated.get(arrayList.indexOf(sheng)).id
                        zel_act_ajcl_detail_zzbm.setText(sheng)
                    }
                }
            })
        }

    }

    val czwcList = ArrayList<OpinionFile>()
    val fjxxList = ArrayList<OpinionFile>()
    var tdyyList = ArrayList<OpinionRejectedEntity>()//????????????
    var tdbhList = ArrayList<OpinionRejectedEntity>()//????????????
    var gdbhList = ArrayList<OpinionRejectedEntity>()//??????
    var yssqList = ArrayList<OpinionDelayEntity>()//????????????
    var ysbhList = ArrayList<OpinionDelayEntity>()//????????????
    var czwcAdapter: BaseQuickAdapter<OpinionFile, BaseViewHolder>? = null
    var fjxxAdapter: BaseQuickAdapter<OpinionFile, BaseViewHolder>? = null
    //????????????
    override fun returnJsjbInfo(renovated: OpinionEntity) {
        opinionEntity = renovated

        if (renovated!=null){
            if (renovated.opinionTypeEntities.size>0)
                opinionEntity1.opinionTypeEntities = renovated.opinionTypeEntities
            if (renovated.opinionFiles.size>0)
                opinionEntity1.opinionFiles = renovated.opinionFiles
            if (renovated.opinionDelayEntities.size>0)
                opinionEntity1.opinionDelayEntities = renovated.opinionDelayEntities
            if (renovated.opinionRejectedEntities.size>0)
                opinionEntity1.opinionRejectedEntities = renovated.opinionRejectedEntities
            mtl_frag_zjd_yqgl_ajzq.setName(renovated.title)
            if (renovated.qutype==1){//?????????
                tv_act_aj_detail_pdbm.visibility = View.VISIBLE
                tl_act_aj_detail.visibility = View.VISIBLE//??????tab
                ll_act_ajcl_xf.visibility = View.GONE
                ll_act_ajcl_sfqs.visibility = View.VISIBLE//???????????????
                ll_act_ajcl_sqys.visibility = View.GONE
                ll_act_ajcl_ytd.visibility = View.GONE
                ll_act_ajcl_sfys.visibility = View.GONE
                ll_act_ajcl_hfjl.visibility = View.GONE
                mtl_frag_zjd_yqgl_ajzq.setIvVisibility(View.VISIBLE)
                mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_dqs)
            }else
                if (renovated.qutype==2){//?????????
                    ll_act_ajcl_detail_clyj1.visibility = View.VISIBLE
                    tv_act_ajcl_detail_clyj.visibility = View.GONE
                    ll_act_ajcl_detail_fjxx1.visibility = View.VISIBLE
                    iv_act_ajcl_detail_fjxx.visibility = View.VISIBLE


                tv_act_aj_detail_pdbm.visibility = View.VISIBLE
                tl_act_aj_detail.visibility = View.VISIBLE
                ll_act_ajcl_xf.visibility = View.GONE
                ll_act_ajcl_sfqs.visibility = View.GONE
                ll_act_ajcl_sqys.visibility = View.VISIBLE//???????????????????????????
                    if (renovated.overdue.equals("?????????")){
                        but_act_aj_detail_sqys.visibility = View.VISIBLE
                    }
                ll_act_ajcl_ytd.visibility = View.GONE
                ll_act_ajcl_sfys.visibility = View.GONE
                mtl_frag_zjd_yqgl_ajzq.setIvVisibility(View.VISIBLE)
                mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_dfk)
            }else
                    if (renovated.qutype==3){//?????????  //????????????????????????
                        ll_act_ajcl_detail_clyj1.visibility = View.VISIBLE
                        ll_act_ajcl_detail_fjxx1.visibility = View.VISIBLE
                        ll_act_ajcl_detail_hfjl1.visibility = View.VISIBLE
                        tv_act_sj_detail_hfjl.visibility = View.GONE
                        iv_act_ajcl_detail_scwj.visibility = View.VISIBLE

                tv_act_aj_detail_pdbm.visibility = View.VISIBLE
                tl_act_aj_detail.visibility = View.VISIBLE
                        ll_act_ajcl_xf.visibility = View.GONE
                        ll_act_ajcl_sfqs.visibility = View.GONE
                        ll_act_ajcl_sqys.visibility = View.GONE
                        ll_act_ajcl_ytd.visibility = View.GONE
                        ll_act_ajcl_sfys.visibility = View.GONE
                        ll_act_ajcl_hfjl.visibility = View.VISIBLE
                mtl_frag_zjd_yqgl_ajzq.setIvVisibility(View.VISIBLE)
                mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_dsh)
            }else
                        if (renovated.qutype==4){//??????
                            ll_act_ajcl_detail_clyj1.visibility = View.VISIBLE
                            ll_act_ajcl_detail_fjxx1.visibility = View.VISIBLE
                            ll_act_ajcl_detail_hfjl1.visibility = View.VISIBLE

                tv_act_aj_detail_pdbm.visibility = View.VISIBLE
                tl_act_aj_detail.visibility = View.VISIBLE
                            ll_act_ajcl_xf.visibility = View.GONE
                            ll_act_ajcl_sfqs.visibility = View.GONE
                            ll_act_ajcl_sqys.visibility = View.GONE
                            ll_act_ajcl_ytd.visibility = View.GONE
                            ll_act_ajcl_sfys.visibility = View.GONE
                            ll_act_ajcl_hfjl.visibility = View.GONE
                mtl_frag_zjd_yqgl_ajzq.setIvVisibility(View.VISIBLE)
                mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_ygd)
            }else
                            if (renovated.qutype==5){//??????
                                tv_act_aj_detail_pdbm.visibility = View.GONE
                tl_act_aj_detail.visibility = View.VISIBLE
                ll_act_ajcl_xf.visibility = View.GONE
                ll_act_ajcl_sfqs.visibility = View.GONE
                ll_act_ajcl_sqys.visibility = View.GONE
                ll_act_ajcl_ytd.visibility = View.VISIBLE//?????????????????????
                ll_act_ajcl_sfys.visibility = View.GONE
                                ll_act_ajcl_hfjl.visibility = View.GONE
                mtl_frag_zjd_yqgl_ajzq.setIvVisibility(View.VISIBLE)
                mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_ytd)
            }else
                                if (renovated.qutype==6){//????????????
                tv_act_aj_detail_pdbm.visibility = View.VISIBLE
                tl_act_aj_detail.visibility = View.VISIBLE
                ll_act_ajcl_xf.visibility = View.GONE
                ll_act_ajcl_sfqs.visibility = View.GONE
                ll_act_ajcl_sqys.visibility = View.GONE
                ll_act_ajcl_ytd.visibility = View.GONE
                ll_act_ajcl_sfys.visibility = View.VISIBLE//???????????????
                                    ll_act_ajcl_hfjl.visibility = View.GONE
                mtl_frag_zjd_yqgl_ajzq.setIvVisibility(View.VISIBLE)
//                mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_yq)
                                    mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_dfk)
            }
            if (renovated.overdue.equals("?????????")){
                mtl_frag_zjd_yqgl_ajzq.setImageView(R.drawable.jsjb_yq)
            }
            zel_act_aj_detail_ajbh.setText(opinionEntity.casebh)
            zel_act_aj_detail_ajly.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.caseSource))
            zel_act_aj_detail_ldsj.setText(opinionEntity.callTime)
            zel_act_aj_detail_sjbh.setText(opinionEntity.citybh)
            zel_act_aj_detail_spfr.setText(opinionEntity.citypfr)
            zel_act_aj_detail_sgjc.setText(opinionEntity.citykeyword)
            zel_act_aj_detail_ldzt.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.callSubject))
            zel_act_aj_detail_sjly.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.citySource))
            zel_act_aj_detail_tsrqlx.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.specialtype))
            zel_act_aj_detail_pdlx.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.sendType))
            zel_act_aj_detail_gdbq.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.workLabel))
            zel_act_aj_detail_smxm.setText(opinionEntity.citizenName)
            zel_act_aj_detail_lxdh.setText(opinionEntity.phone)
            zel_act_aj_detail_ldhm.setText(opinionEntity.callPhone)
            zel_act_aj_detail_hjdz.setText(opinionEntity.province + "-" + opinionEntity.city + "-" + opinionEntity.county)
            zel_act_aj_detail_lxdz.setText(opinionEntity.address)
            zel_act_aj_detail_remark.setText(opinionEntity.remark)
            zel_act_ajcl_detail_sd.setText(opinionEntity.territorial)
            zel_act_ajcl_detail_ajfl.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.caseType))
            zel_act_ajcl_detail_bfyz.setText(opinionEntity.reflecting)
            zel_act_ajcl_detail_bfyzdh.setText(opinionEntity.reflectingPhone)
            zel_act_ajcl_detail_wtfl.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.problemType))
            zel_act_ajcl_detail_bt.setText(opinionEntity.title)
            zel_act_ajcl_detail_qjwtfl.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("???????????????"),opinionEntity.districtProblemType))
            zel_act_ajcl_detail_bwdys.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("???????????????"),opinionEntity.unstable))
            zel_act_ajcl_detail_fsdz.setText(opinionEntity.happenAddress)
            zel_act_ajcl_detail_wtms.setText(opinionEntity.problemDescription)
            zel_act_ajcl_detail_sjclyj.setText(opinionEntity.municipalOpinion)
            zel_act_ajcl_detail_jjaj.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("????????????"),opinionEntity.urgentCase))
            zel_act_ajcl_detail_sysj.setText(opinionEntity.deadline)
            zel_act_ajcl_detail_qzpd.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("??????"),opinionEntity.mandatory))
//            zel_act_ajcl_detail_zzbm.setText(opinionEntity.organizer)
//            zel_act_ajcl_detail_cbdw.setText(opinionEntity.organizer)
            zel_act_ajcl_detail_yjxq.setText(opinionEntity.opinion)

            zel_act_ajcl_detail_gdh.setText(opinionEntity.casebh)
            if (opinionEntity.recorderName.equals("")){//?????????
                zel_act_ajcl_detail_czr.visibility = View.GONE
            }
            if (opinionEntity.auditTime.equals("")){//????????????
                zel_act_ajcl_detail_czsj.visibility = View.GONE
            }
            zel_act_ajcl_detail_czr.setText(opinionEntity.recorderName)
            zel_act_ajcl_detail_sfss.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("??????"),opinionEntity.verified))
            zel_act_ajcl_detail_sfsyszc.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("??????"),opinionEntity.syszc))
            zel_act_ajcl_detail_sffk.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("??????"),opinionEntity.feedback))
            zel_act_ajcl_detail_sfyx.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("??????"),opinionEntity.effective))
            zel_act_ajcl_detail_sfmy.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("??????"),opinionEntity.satisfaction))
            zel_act_ajcl_detail_sfjj.setText(JsjbPickerUtil.getString(JsjbPickerUtil.getTypeList("??????"),opinionEntity.solve))
            zel_act_ajcl_detail_czsj.setText(opinionEntity.auditTime)
            zel_act_ajcl_detail_czyj.setText(opinionEntity.disposalOpinion)

            val arrayList1 = ArrayList<JsjbBean>()
            val typeList = JsjbPickerUtil.getTypeList("????????????")
            for (i in 0..opinionEntity.opinionTypeEntities.size-1){
                val get1 = opinionEntity.opinionTypeEntities.get(i)
                if (get1.type==1){
                    for (f in 0..typeList.size-1){
                        val get = typeList.get(f)
                        if (get1.menuId == get.index){
                            arrayList1.add(JsjbBean(arrayList1.size, typeList.get(f).name, true))
                        }
                    }
                }
            }
            val mAdapterxl = TagBaseAdapter(mContext, arrayList1) //createData(mListxl, xl)
            tcl_act_aj_detail_gdsx.setAdapter(mAdapterxl)
            tcl_act_aj_detail_gdsx.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
                override fun itemClick(position1: Int) {
                    changeState(arrayList1, position1)
                    mAdapterxl.notifyDataSetChanged()
                }
            })

            val arrayList2 = ArrayList<JsjbBean>()
            val typeGjzList = JsjbPickerUtil.getTypeList("?????????")
            for (i in 0..opinionEntity.opinionTypeEntities.size-1){
                val get1 = opinionEntity.opinionTypeEntities.get(i)
                if (get1.type==2){
                    for (f in 0..typeGjzList.size-1){
                        val get = typeGjzList.get(f)
                        if (get1.menuId == get.index){
                            arrayList2.add(JsjbBean(arrayList2.size, typeGjzList.get(f).name, true))
                        }
                    }
                }
            }
            val mAdapterx2 = TagBaseAdapter(mContext, arrayList2) //createData(mListxl, xl)
            tcl_act_ajcl_detail_gdsx.setAdapter(mAdapterx2)
            tcl_act_ajcl_detail_gdsx.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
                override fun itemClick(position1: Int) {
                    changeState(arrayList2, position1)
                    mAdapterx2.notifyDataSetChanged()
                }
            })

            val shiLightBeans = java.util.ArrayList<LightBean>()
            val quLightBeans = java.util.ArrayList<LightBean>()
            shiLightBeans.clear()
            quLightBeans.clear()
            for (i in PopuPointUtils.lightItems){
                if (opinionEntity.municipalLight==i.id){//??????
                    i.setCheck(true)
                    shiLightBeans.add(i)
                }
                if (opinionEntity.districtLight==i.id){//??????
                    i.setCheck(true)
                    quLightBeans.add(i)
                }
            }
            rlv_act_aj_detail_sjzsd.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
            rlv_act_aj_detail_sjzsd.setAdapter(ZsdAdapter(this, shiLightBeans, 2))
            rlv_act_aj_detail_qjzsd.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
            rlv_act_aj_detail_qjzsd.setAdapter(ZsdAdapter(this, quLightBeans, 2))
            //bmList
            var bmName = ""
            for (i in bmList){//??????????????????
                if (opinionEntity.responsible == i.id){
                    bmName = i.name
                }
            }
            zel_act_ajcl_detail_zzbm.setText(bmName)
            zel_act_ajcl_detail_cbdw.setText(bmName)
            //????????????
            /*rlv_act_ajcl_clyj.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_act_ajcl_clyj.adapter = object:BaseQuickAdapter<OpinionRejectedEntity,BaseViewHolder>(R.layout.item_act_ajcl_clyj,opinionEntity.opinionRejectedEntities){
                override fun convert(helper: BaseViewHolder?, item: OpinionRejectedEntity?) {
                    helper!!.setText(R.id.tv_item_ajcl_clyj_time, item!!.updateDate)
                            .setText(R.id.tv_item_ajcl_clyj_gdjd, item!!.rejected)

                }
            }*/
            //????????????
            fjxxList.clear()
            czwcList.clear()
            for (i in opinionEntity.opinionFiles){
                if (i.filetype==235){
                    fjxxList.add(i)
                }else if (i.filetype==234){
                    czwcList.add(i)
                }
            }
            rlv_act_ajcl_detail_fjxx.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            fjxxAdapter = object : BaseQuickAdapter<OpinionFile, BaseViewHolder>(R.layout.item_act_ajcl_fjxx, fjxxList) {
                override fun convert(helper: BaseViewHolder?, item: OpinionFile?) {
                    helper!!.setText(R.id.tv_item_ajcl_fjxx_name, item!!.filename)
                            .setText(R.id.tv_item_ajcl_fjxx_scdw, item!!.uploadunit)
                            .setText(R.id.tv_item_ajcl_fjxx_time, item!!.updateDate)
                            .setText(R.id.tv_item_ajcl_fjxx_size, item!!.fileSize)
                    helper.itemView.setOnClickListener {//????????????
                        var path = item.path//Environment.getExternalStorageDirectory().getAbsolutePath()
                        var uri = Uri.parse(path)
                        var intent =  Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                        /*var storageDir = File( GetFileUtil.getSDCardPath()+ "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs()
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""//time
                        val substring = item.id.toString() + item.filename//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@AjDetailActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@AjDetailActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@AjDetailActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }*/
                    }
                    if (opinionEntity.qutype == 2)
                    helper.itemView.setOnLongClickListener {
                        val content = TextView(this@AjDetailActivity)
                        content.text = "???????????????"
                        SweetAlertDialog(this@AjDetailActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("????????????")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    ToastUtils.showShort("??????")
                                    mPresenter.getJsjbDeleteFile(item.id,fjxxList,item)
//                                    mPresenter.getApplyFileDelete(item.id,qszmList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                        true
                    }

                }
            }
            rlv_act_ajcl_detail_fjxx.adapter = fjxxAdapter
            //????????????
            /*czwcList.clear()
            czwcList.addAll(opinionEntity.opinionFiles)*/
            rlv_act_ajcl_detail_czwc.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            czwcAdapter = object : BaseQuickAdapter<OpinionFile, BaseViewHolder>(R.layout.item_act_ajcl_fjxx, czwcList) {
                override fun convert(helper: BaseViewHolder?, item: OpinionFile?) {
                    helper!!.setText(R.id.tv_item_ajcl_fjxx_name, item!!.filename)
                            .setText(R.id.tv_item_ajcl_fjxx_scdw, item!!.uploadunit)
                            .setText(R.id.tv_item_ajcl_fjxx_time, item!!.createDate)
                            .setText(R.id.tv_item_ajcl_fjxx_size, item!!.fileSize)
                    helper.itemView.setOnClickListener {//????????????
                        var path = item.path//Environment.getExternalStorageDirectory().getAbsolutePath()
                        var uri = Uri.parse(path)
                        var intent =  Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                        /*var storageDir = File( GetFileUtil.getSDCardPath()+ "/jymj/tzrjhj/pic");
                        if (!storageDir.exists()) {
                            storageDir.mkdirs()
                        }
                        val l = System.currentTimeMillis()
                        val time = l.toString() + ""//time
                        val substring = item.id.toString() + item.filename//.substring(time.length - 6, time.length)
                        val file = File(storageDir.path + "/" + substring)
                        if (file.exists()) {
                            FileUtilsFjxc.openFile(this@AjDetailActivity, File(storageDir.path + "/" + substring))
                        } else {
                            LoadingDialog.showDialogForLoading(this@AjDetailActivity)
                            OkGo.get<File>(path).execute(object : FileCallback(storageDir.getPath(), substring) {
                                override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                                    super.onStart(request)
                                }

                                override fun onSuccess(response: Response<File>) {
                                    LoadingDialog.cancelDialogForLoading()
                                    FileUtilsFjxc.openFile(this@AjDetailActivity, File(storageDir.path + "/" + substring))
                                }

                                override fun downloadProgress(progress: Progress?) {

                                }

                                override fun onFinish() {
                                    super.onFinish()
//                                    LoadingDialog.showDialogForLoading(this@CheckAcceptActivity)
                                }

                                override fun onError(response: Response<File>) {
                                    super.onError(response)
                                    LoadingDialog.cancelDialogForLoading()
                                }
                            })
                        }*/
                    }
                    if (opinionEntity.qutype == 3)
                    helper.itemView.setOnLongClickListener {
                        val content = TextView(this@AjDetailActivity)
                        content.text = "???????????????"
                        SweetAlertDialog(this@AjDetailActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("????????????")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    ToastUtils.showShort("??????")
                                    mPresenter.getJsjbDeleteFile(item.id,czwcList,item)
//                                    mPresenter.getApplyFileDelete(item.id,qszmList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                        true
                    }
                    /*helper.itemView.setOnLongClickListener {
                        val content = TextView(this@AjDetailActivity)
                        content.text = "???????????????"
                        SweetAlertDialog(this@AjDetailActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("????????????")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    mPresenter.getApplyFileDelete(item.id,qszmList,helper.layoutPosition)

                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                    }*/
                }
            }
            rlv_act_ajcl_detail_czwc.adapter = czwcAdapter

            val opinionRejectedEntities = opinionEntity.opinionRejectedEntities

            for (i in opinionRejectedEntities){
                if (i.type == 100){
                    if (i.through == 0){//0 ???  1 ???
                        tdyyList.add(i)
                    }else{
                        tdbhList.add(i)
                    }
                }else if (i.type == 101){
                    gdbhList.add(i)
                }
            }
            if (tdyyList.size>0){
                if (renovated.qutype == 5){
                    ll_act_aj_detail_tdyy.visibility = View.VISIBLE
                }
            }
            if (tdbhList.size>0){
                if (renovated.qutype == 1){
                    ll_act_aj_detail_tdbh.visibility = View.VISIBLE
                }
            }
            if (gdbhList.size>0){
                if (renovated.qutype == 2){
                    ll_act_aj_detail_gdbh.visibility = View.VISIBLE
                }
            }

            rlv_act_aj_detail_tdyy.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_act_aj_detail_tdyy.adapter = object:BaseQuickAdapter<OpinionRejectedEntity, BaseViewHolder>(R.layout.item_act_ajcl_tdyy, tdyyList){
                override fun convert(helper: BaseViewHolder?, item: OpinionRejectedEntity?) {
                    helper!!.setText(R.id.tv_item_ajcl_tdyytitle,"????????????:")
                            .setText(R.id.tv_item_ajcl_time, item!!.createDate)
                            .setText(R.id.tv_item_ajcl_tdyy, item!!.chargeback)

                }
            }
            rlv_act_aj_detail_tdbh.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_act_aj_detail_tdbh.adapter = object:BaseQuickAdapter<OpinionRejectedEntity, BaseViewHolder>(R.layout.item_act_ajcl_tdyy, tdbhList){
                override fun convert(helper: BaseViewHolder?, item: OpinionRejectedEntity?) {
                    helper!!.setText(R.id.tv_item_ajcl_tdyytitle,"????????????:")
                            .setText(R.id.tv_item_ajcl_time, item!!.createDate)
                            .setText(R.id.tv_item_ajcl_tdyy, item!!.rejected)

                }
            }
            rlv_act_aj_detail_gdbh.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_act_aj_detail_gdbh.adapter = object:BaseQuickAdapter<OpinionRejectedEntity, BaseViewHolder>(R.layout.item_act_ajcl_tdyy, gdbhList){
                override fun convert(helper: BaseViewHolder?, item: OpinionRejectedEntity?) {
                    helper!!.setText(R.id.tv_item_ajcl_tdyytitle,"????????????:")
                            .setText(R.id.tv_item_ajcl_time, item!!.createDate)
                            .setText(R.id.tv_item_ajcl_tdyy, item!!.opinion)

                }
            }

            val opinionDelayEntities = opinionEntity.opinionDelayEntities
            for (i in opinionDelayEntities){
                if (i.through == 0){//0 ???  1 ???  ??????1
                    yssqList.add(i)
                }else{
                    ysbhList.add(i)
                }
            }
            if (ysbhList.size>0){
                if (renovated.qutype == 2){
                    ll_act_aj_detail_ysbh.visibility = View.VISIBLE
                }
            }
            rlv_act_aj_detail_ysbh.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rlv_act_aj_detail_ysbh.adapter = object:BaseQuickAdapter<OpinionDelayEntity, BaseViewHolder>(R.layout.item_act_ajcl_tdyy, ysbhList){
                override fun convert(helper: BaseViewHolder?, item: OpinionDelayEntity?) {
                    helper!!.setText(R.id.tv_item_ajcl_tdyytitle,"????????????:")
                            .setText(R.id.tv_item_ajcl_time, item!!.createDate)
                            .setText(R.id.tv_item_ajcl_tdyy, item!!.rejected)//cause

                }
            }
            if (yssqList.size>0){//
                if (renovated.qutype == 6){
                    ll_act_ajcl_detail_rwyssq1.visibility = View.VISIBLE
                }
                val opinionDelayEntity = yssqList.get(0)
                zel_act_ajcl_detail_ajh.setText(opinionDelayEntity.caseNumber)
                zel_act_ajcl_detail_czr1.setText("${opinionDelayEntity.name}")
                zel_act_ajcl_detail_yqts.setText("${opinionDelayEntity.delay}")
                zel_act_ajcl_detail_yy.setText(opinionDelayEntity.cause)

            }

        }
    }

    //????????????
    override fun returnJsjbDeleteFile(renovated: String,opinionFiles:ArrayList<OpinionFile>,opinionFile:OpinionFile) {//czwcAdapter
        if(czwcAdapter!=null){
            opinionFiles.remove(opinionFile)
            czwcAdapter!!.notifyDataSetChanged()
            fjxxAdapter!!.notifyDataSetChanged()
            ToastUtils.showShort("????????????")
        }
    }
    //?????? ????????????
    override fun returnJsjbSave(renovated: String) {
        AppCache.getInstance().isJsjb = true
        AppCache.getInstance().isJsjbQuan = true
        finish()
    }
    //?????? ??????
    override fun returnJsjbUpdate(renovated: String) {
        AppCache.getInstance().isJsjb = true
        AppCache.getInstance().isJsjbQuan = true
        finish()
    }
    //    ??????????????????
    private fun changeState(list: List<JsjbBean>, position: Int) {
        if (list[position].isCheck()) {
            list[position].setCheck(false)
        } else {
            list[position].setCheck(true)
        }
    }

    fun initPopuZsdzj() {
        var mPointPopu = CommenPop.getNormalPopu(this, R.layout.pop_point_rwyssq, ll_act_aj_detail)//pop_point
        val contentView = mPointPopu.getContentView()
        val iv_pop_point_rwyssq_error = contentView.findViewById(R.id.iv_pop_point_rwyssq_error) as ImageView
        val iv_pop_point_rwyssq_scwd = contentView.findViewById(R.id.iv_pop_point_rwyssq_scwd) as ImageView
        val iv_pop_point_rwyssq_scyp = contentView.findViewById(R.id.iv_pop_point_rwyssq_scyp) as ImageView
        val tv_pop_point_rwyssq = contentView.findViewById(R.id.tv_pop_point_rwyssq) as TextView

        iv_pop_point_rwyssq_scwd.setOnClickListener {

        }
        iv_pop_point_rwyssq_scyp.setOnClickListener {

        }
        CommenPop.backgroundAlpha(0.5f, this)
        mPointPopu.showAtLocation(ll_act_aj_detail, Gravity.CENTER, 0, 0)
        iv_pop_point_rwyssq_error.setOnClickListener(View.OnClickListener { mPointPopu.dismiss() })
        tv_pop_point_rwyssq.setOnClickListener(View.OnClickListener { mPointPopu.dismiss() })
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == 234) {
                var file: File? = null;
                var uri = data.getData();///Uri
                if (uri != null) {
//                    file = CommonUtils.uri2File(uri);
                    val path = FileUtilFjxc1.getPath(this, uri)
                    if (path == null) {
                        ToastUtils.showShort("?????????????????????");
                        return;
                    }
                    file = File(path)
//                    Log.e("onActivityResult",uri.path+":"+file)
                    upFile(file,requestCode)
                    // ????????????
//                    upLoadFile(file);
                }
            }else if (requestCode == 235) {
                var file: File? = null;
                var uri = data.getData();///Uri
                if (uri != null) {
//                    file = CommonUtils.uri2File(uri);
                    val path = FileUtilFjxc1.getPath(this, uri)
                    if (path == null) {
                        ToastUtils.showShort("?????????????????????");
                        return;
                    }
                    file = File(path)
//                    Log.e("onActivityResult",uri.path+":"+file)
                    upFile(file,requestCode)
                    // ????????????
//                    upLoadFile(file);
                }
            }


        }
    }


    private fun upFile( file2: File,requestCode: Int) {//        val opinionFile = OpinionFile()
//getDirSize   getFileSize
        val request = OkGo.post<BaseRespose<OpinionFile>>(ApiConstants.OPINION_UPLOAD_FILE)
                .isMultipart(true)
//        request.params("filelabel", file2.)
//        request.params("uploadunit", "")//????????????
//        request.params("fileSize", FileUtilsFjxc.getFileSize(file2))//????????????
        request.params("filetype", requestCode)
        request.params("opinionId", opinionEntity.id)
        request.params("filename", file2.name)//file  file.name
        request.params("file", file2)//file  file.name
        request.execute(object : BaseNet<BaseRespose<OpinionFile>>() {//BaseRespose<PjEnviorFileEntity>
        override fun onStart(request: Request<BaseRespose<OpinionFile>, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            LoadingDialog.showDialogForLoading(this@AjDetailActivity)
        }
            override fun onSuccess(response: Response<BaseRespose<OpinionFile>>) {
//                super.onSuccess(response)
                val body = response.body()
                if (body.getCode()==0){
                    if (requestCode == 234){
                        if (czwcList!=null){
                            czwcList.add(body.data)
                        }
                        czwcAdapter!!.setNewData(czwcList)
                        czwcAdapter!!.notifyDataSetChanged()
                    }else if (requestCode == 235){//fjxxList
                        if (fjxxList!=null){
                            fjxxList.add(body.data)
                        }
                        fjxxAdapter!!.setNewData(fjxxList)
                        fjxxAdapter!!.notifyDataSetChanged()
                    }

                }else{
                    ToastUtils.showShort(file2.name+"????????????")
                }
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<OpinionFile>>) {
                super.onError(response)
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort(response.exception.message)
            }
        })
    }

}
