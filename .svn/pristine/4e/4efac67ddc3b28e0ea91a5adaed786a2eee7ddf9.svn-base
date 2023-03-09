package com.jymj.zhglxt.zjd.activity

import android.view.View
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity
import com.jymj.zhglxt.login.contract.FrameYewuContract
import com.jymj.zhglxt.login.model.FrameYewuModel
import com.jymj.zhglxt.login.presenter.FrameYewuPresenter
import com.setsuna.common.base.BaseActivity

class FrameYewuDeteil : BaseActivity<FrameYewuPresenter, FrameYewuModel>(), FrameYewuContract.View, View.OnClickListener {

    /*override fun onCtglZhen(string: CTGLZhenBean.DataBean) {
    }

    override fun onCtglPqList(string: List<PQListBean.DataBean>) {
    }

    override fun onFrameJcxx(isSave: FrameJcxxBean.DataBean) {
    }*/

    var pointss : String = ""
    var types : Int = 0
    var frameTypes : Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_frame_yewu_deteil
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {

        /*val stringExtra = intent.getIntExtra("frameFlag", 0)
        types = stringExtra
        val points = intent.getStringExtra("framePoint")
        pointss = points
        val frameType = intent.getIntExtra("frameType",-1)
        frameTypes = frameType
        recy_sqgl_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recy_fjgl_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recy_lzgl_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recy_tcgl_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recy_environmental_show.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        if (stringExtra == 1) {
            frame_yewu_det_sq_ll.visibility = View.VISIBLE
            getSpData(points)
        } else if (stringExtra == 2) {
            frame_yewu_det_fj_ll.visibility = View.VISIBLE
            getFjData(points)
        } else if (stringExtra == 3) {
            frame_yewu_det_lz_ll.visibility = View.VISIBLE
            getLzData(points)
        } else if (stringExtra == 4) {
            frame_yewu_det_tc_ll.visibility = View.VISIBLE
            getTcData(points)
        } else if (stringExtra == 5) {
            frame_yewu_det_hbqc_ll.visibility = View.VISIBLE
            getHbjcData(points, 1)
        } else if (stringExtra == 6) {
            frame_yewu_det_wfxc_ll.visibility = View.VISIBLE
            getWfxcData(points, 1)
        } else if (stringExtra == 7) {
            frame_yewu_det_wfxc_ll.visibility = View.VISIBLE
            getWfxcData(points, 2)
        }
        for (i in 0..hbjcTitles.size - 1) {
            tab_map_hbjc.addTab(tab_map_hbjc.newTab().setText(hbjcTitles[i]));
        }

        tab_map_hbjc.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                lastSelect = EnviorSupvsEnum.getIndex(hbjcTitles[tab!!.position])
                getHbjcData(points, lastSelect)

            }
        })
        initPopuStepMenu()*/
    }

    override fun initDatas() {

    }

    override fun returnSmewmcyl(msg: YlFolwEntity) {
    }
    /*override fun initDatas() {
    }

    override fun onPointFwqs(ylPointEntity: YLPointEntity) {
    }

    override fun onYztHoOwner(yztCountEntity: YztCountEntity) {

    }

    override fun onYztPoint(yztPointEntity: YztPointEntity) {

    }

    override fun onCtglPoint(yztPointEntity: CTGLPOINTBean.DataBean) {

    }

    override fun onYewuFrame(entity: YeWuFrameBean.DataBean) {
    }

    override fun onPolygonFwqs(polygonQueryEntity: PolygonQueryEntity) {
    }

    override fun onPolygonDetailFwqs(polygonDetailEntity: PolygonDetailEntity) {
    }

    override fun onPointTx(tdlyEntity: TdlyEntity) {
    }

    override fun onPolygonTx(list: MutableList<TxPolygonEntity>) {
    }

    override fun onPolygonDetailTx(tdlyDetail: TdlyDetail) {
    }

    override fun onPointCg(cgEntity: CgEntity) {
    }

    override fun onPolygonCg(list: MutableList<CgPolygonEntity>) {
    }

    override fun onPolygonDetailCg(cgDetail: CgDetail) {
    }

    override fun onPointLthy(lthyEntity: IthyEntity) {
    }

    override fun onPolygonLthy(list: MutableList<IthyEntity>) {
    }

    override fun onPolygonDetailLthy(cgDetail: LthyDetailEntity) {
    }

    override fun onPointTg(tgEntity: TgEntity) {
    }

    override fun onPolygonTg(list: MutableList<TgPolygonEntity>) {
    }

    override fun onPolygonDetailTg(tgDetail: TgDetail) {
    }

    override fun onBaseDataCe(baseDataEntity: XzDateEntity) {

    }

    override fun onBaseData(baseDataEntity: BaseDataEntity) {
    }

    override fun onCeSuan(list: MutableList<MoveCost>) {
    }

    override fun onCeSuan2(list: MutableList<MoveCost2>) {
    }

    override fun onCS(csEntity: CSEntity) {
    }

    override fun onYlByCun(list: MutableList<SysKxXzqData>) {
    }

    override fun onTxByCun(list: TxCunEntity) {
    }

    override fun onTgByCun(list: List<TGCunEntity>) {
    }

    override fun onCheckNydPoint(cjEntity: ZzyNydEntity) {
    }

    override fun onCheckNydZB(zzyNydEntity: ArrayList<CJEntity>) {
    }

    override fun onUpdateHt(zzyNydEntity: String) {
    }

    override fun onSaveZj(zzyNydEntity: String) {
    }

    override fun onUpdateZj(zzyNydEntity: String) {
    }

    override fun onDeleteZj(zzyNydEntity: String) {
    }

    override fun onDeleteFile(zzyNydEntity: String) {
    }

    override fun onQueryNydList(zzyNydEntity: List<ZzyNydEntity>) {
    }

    override fun onWfxcList(wfxcList: List<IllegalEntity>) {
    }

    override fun onWfxcSave(zzyNydEntity: String) {
    }

    override fun onWfxcCha(zzyNydEntity: YLEntity) {
    }

    override fun onLdrkCha(ldrkPointBean: LdrkPointBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onWfxcUpdate(zzyNydEntity: String) {
        if (types == 6){
            getWfxcData(pointss, 1)
        }else if (types == 7){
            getWfxcData(pointss, 2)
        }
        ToastUtils.showShort("修改成功")
    }

    override fun onWfxcDelete(zzyNydEntity: String) {
        if (types == 6){
            getWfxcData(pointss, 1)
        }else if (types == 7){
            getWfxcData(pointss, 2)
        }
        ToastUtils.showShort("删除成功")
    }

    override fun onWfxcInfo(zzyNydEntity: List<XzqEntity.DataBean>) {
    }

    override fun onGhglList(zzyNydEntity: List<GhFhEntity>) {
    }

    override fun onHbjcList(zzyNydEntity: List<PjEnviorSupvsEntity>) {
    }

    override fun onHbLr(isSave: String) {
    }

    override fun onHbCha(point: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLayerList(isSave: List<layerListBean>) {

    }

    override fun onYztList(isSave: List<AnalysisEnty>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onYztDelete(isSave: String) {
        ToastUtils.showShort("删除成功")
    }*/

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

    override fun onClick(v: View?) {
    }

    /*private val hbjcTitles = arrayListOf<String>("待处理", "整改中", "验收完成")
    private var lastSelect = 1
    private var hbjcPosition = 0
    private var stepMenuPopu: CommenPop? = null
    private val selectedPhotos = ArrayList<String>()
    private val intList = ArrayList<Int>()
    private var picPop: CommenPop? = null
    private var photoAdapter: PhotoAdapter? = null

    private fun initPopuStepMenu() {
        stepMenuPopu = CommenPop.getNormalPopu(this, R.layout.pop_step_menu, frame_yewu_det_top)
        stepMenuPopu?.animationStyle = R.style.bottom_menu_anim
        val contentView = stepMenuPopu?.contentView
        val tvPics = contentView?.findViewById<TextView>(R.id.tv_pics_step_menu)
        val tvUpPics = contentView?.findViewById<TextView>(R.id.tv_uploadpic_step_menu)
        val tvDocs = contentView?.findViewById<TextView>(R.id.tv_docs_step_menu)
        val tvUpDocs = contentView?.findViewById<TextView>(R.id.tv_uploaddoc_step_menu)
        tvPics!!.setText("上传图片")
//        tvUpPics!!.setText("查看图片")
        tvUpPics!!.setText("查看详情")
        tvUpDocs!!.setText("修改问题")
        tvDocs!!.setText("删除问题")
        tvPics?.setOnClickListener(this)
        tvUpPics?.setOnClickListener(this)
        tvDocs?.setOnClickListener(this)
        tvUpDocs?.setOnClickListener(this)
    }

    fun getSpData(point: String) {
        val jsonObject = JSONObject()
        if (point.equals("1")) {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
        } else {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
            jsonObject.put("point", point)
        }
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.SQGL_queryList_URL).upJson(jsonObject).execute(object :
                BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val baseMessage = response?.body()
                if (baseMessage != null) {
                    val decrypt = AesEncryptUtils.decrypt(baseMessage)
                    val json = Gson().fromJson(decrypt, ZjdSqBean::class.java)
                    when (json.code) {
                        0 -> {
                            if (json.data.list.size > 0) {
                                recy_sqgl_list?.adapter = object : BaseQuickAdapter<ZjdSqBean.DataBean.ListBean, BaseViewHolder>(R.layout.item_sqgl_list, json.data.list) {
                                    override fun convert(helper: BaseViewHolder, item: ZjdSqBean.DataBean.ListBean) {
                                        if (frameTypes != -1) {
                                            if (item.sptype != frameTypes) {
                                                helper.itemView.layoutParams.height = 0
                                            }
                                        }
                                        helper.setText(R.id.sqgl_item_cm, item.ylEntity.xzqmc)
                                                .setText(R.id.sqgl_item_mph, item.ylEntity.mph)
                                                .setText(R.id.sqgl_item_sqzt, item.sptypeText)
                                                .setText(R.id.sqgl_item_sqr, item.sqName)
                                                .setText(R.id.sqgl_item_sqdate, item.sqDate)
                                                .setText(R.id.sqgl_item_iphone, item.iphone)
                                                .setText(R.id.sqgl_item_remark, item.remark)

                                        helper.itemView.setOnClickListener {
                                            val intent = Intent(this@FrameYewuDeteil, SqglDetailActivity::class.java)
                                            intent.putExtra("applyId", item.id)
                                            intent.putExtra("sptype", item.sptype)
                                            startActivity(intent)
                                        }
                                    }
                                }
                            }

                        }


                    }
                } else {
                }
            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
            }

        })
    }

    fun getFjData(point: String) {
        val jsonObject = JSONObject()
        if (point.equals("1")) {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
        } else {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
            jsonObject.put("point", point)
        }
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.JBQK_APPLY_LIST_URL).upJson(sss).execute(object :
                BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val baseMessage = response?.body()
                if (baseMessage != null) {
                    val decrypt = AesEncryptUtils.decrypt(baseMessage)
                    val json = Gson().fromJson(decrypt, ZjdFjBean::class.java)
                    when (json.code) {
                        0 -> {
                            if (json.data.list.size > 0) {
                                recy_fjgl_list?.adapter = object : BaseQuickAdapter<ZjdFjBean.DataBean.ListBean, BaseViewHolder>(R.layout.item_check_list, json.data.list) {
                                    override fun convert(helper: BaseViewHolder, item: ZjdFjBean.DataBean.ListBean) {
                                        if (frameTypes != -1) {
                                            if (item.sptype != frameTypes) {
                                                helper.itemView.layoutParams.height = 0
                                            }
                                        }
                                        helper.setText(R.id.tv_fjgl_cm, item.ylEntity.xzqmc)
                                                .setText(R.id.tv_fjgl_mph, item.ylEntity.mph)
                                                .setText(R.id.tv_fjgl_sqzt, item.sptypeText)
                                                .setText(R.id.tv_fjgl_sqr, item.name)
                                                .setText(R.id.tv_fjgl_sqdate, item.sqDate)
                                                .setText(R.id.tv_fjgl_iphone, item.iphone)
                                                .setText(R.id.tv_fjgl_remark, item.remark)

                                        helper.itemView.setOnClickListener {
                                            val intent = Intent(this@FrameYewuDeteil, FjglDetailActivity::class.java)
                                            intent.putExtra("applyId", item.id)
                                            intent.putExtra("sptype", item.sptype)
                                            startActivity(intent)
                                        }
                                    }
                                }
                            }

                        }


                    }
                } else {
                }
            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
            }

        })
    }

    fun getLzData(point: String) {
        val jsonObject = JSONObject()
        if (point.equals("1")) {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
        } else {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
            jsonObject.put("point", point)
        }
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.LZGL_ZjdLzList_URL).upJson(sss).execute(object :
                BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val baseMessage = response?.body()
                if (baseMessage != null) {
                    val decrypt = AesEncryptUtils.decrypt(baseMessage)
                    val json = Gson().fromJson(decrypt, ZjdLzBean::class.java)
                    when (json.code) {
                        0 -> {
                            if (json.data.list.size > 0) {
                                recy_lzgl_list?.adapter = object : BaseQuickAdapter<ZjdLzBean.DataBean.ListBean, BaseViewHolder>(R.layout.item_lzgl_list, json.data.list) {
                                    override fun convert(helper: BaseViewHolder, item: ZjdLzBean.DataBean.ListBean) {
                                        if (frameTypes != -1) {
                                            if (item.sptype != frameTypes) {
                                                helper.itemView.layoutParams.height = 0
                                            }
                                        }


                                        helper.setText(R.id.lzgl_item_cm, item.ylEntity.xzqmc)
                                                .setText(R.id.lzgl_item_mph, item.ylEntity.mph)
                                                .setText(R.id.lzgl_item_sqzt, item.sptypeText)
                                                .setText(R.id.lzgl_item_sqr, item.sqName)
                                                .setText(R.id.lzgl_item_sqdate, item.sqDate)
                                                .setText(R.id.lzgl_item_iphone, item.iphone)
                                                .setText(R.id.lzgl_item_remark, item.remark)


                                        helper.itemView.setOnClickListener {
                                            val intent = Intent(this@FrameYewuDeteil, LzglDetailActivity::class.java)
                                            intent.putExtra("applyId", item.id)
                                            intent.putExtra("sptype", item.sptype)
                                            startActivity(intent)
                                        }
                                    }
                                }
                            }
                        }
                        else -> {
                        }
                    }
                } else {
                }
            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
            }

        })
    }

    fun getTcData(point: String) {
        val jsonObject = JSONObject()
        if (point.equals("1")) {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
        } else {
            jsonObject.put("page", 1)
            jsonObject.put("limit", 999)
            jsonObject.put("point", point)
        }
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.EXIT_QUERY_LIST).upJson(sss).execute(object ://BaseRespose<List<MoveCost>>
                BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                val cash = response?.body()
                if (cash != null) {
                    val decrypt = AesEncryptUtils.decrypt(cash)
                    val json = Gson().fromJson(decrypt, ZjdTcBean::class.java)
                    if (json.code == 0) {
                        if (json!!.data != null) {
                            recy_tcgl_list?.adapter = object : BaseQuickAdapter<ZjdTcBean.DataBean.ListBean, BaseViewHolder>(R.layout.item_tcgl, json.data.list) {
                                override fun convert(helper: BaseViewHolder, item: ZjdTcBean.DataBean.ListBean) {
                                    if (frameTypes != -1) {
                                        if (item.sptype != frameTypes) {
                                            helper.itemView.layoutParams.height = 0
                                        }
                                    }

                                    helper.setText(R.id.item_tcgl_cm, item.ylEntity.xzqmc)
                                            .setText(R.id.item_tcgl_mph, item.ylEntity.mph)
                                            .setText(R.id.item_tcgl_sqzt, item.sptypeText)
                                            .setText(R.id.item_tcgl_sqr, item.sqName)
                                            .setText(R.id.item_tcgl_sqdate, item.sqDate)
                                            .setText(R.id.item_tcgl_iphone, item.iphone)
                                            .setText(R.id.item_tcgl_remark, item.remark)
                                    helper.itemView.setOnClickListener {
                                        val intent = Intent(this@FrameYewuDeteil, TcglDetailActivity::class.java)
                                        intent.putExtra("applyId", item.id)
                                        intent.putExtra("sptype", item.sptype)
                                        startActivity(intent)
                                    }
                                }
                            }

                        } else {
                        }
                    } else {
                    }
                }

            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
            }

        })
    }

    fun getHbjcData(point: String, page: Int) {
        val jsonObject = JSONObject()
        if (point.equals("1")) {
            jsonObject.put("qutype", page)//当前页数，从1开始
            jsonObject.put("limit", 1000)//当前页数，从1开始
            jsonObject.put("page", 1)//当前页数，从1开始
        } else {
            jsonObject.put("qutype", page)//当前页数，从1开始
            jsonObject.put("limit", 1000)//当前页数，从1开始
            jsonObject.put("page", 1)//当前页数，从1开始
            jsonObject.put("point", point)//当前页数，从1开始
        }

        val toJson = Gson().toJson(jsonObject).substring(18, Gson().toJson(jsonObject).toString().length - 1)
        val encrypt = AesEncryptUtils.encrypt(toJson.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ENVIORSUPVSLIST).upJson(sss).execute(object ://BaseRespose<List<MoveCost>>
                BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                if (response != null) {
                    if (response.body() != null) {
                        val decrypt = AesEncryptUtils.decrypt(response.body())
                        val json: EnviorListEntity = Gson().fromJson(decrypt, object : TypeToken<EnviorListEntity?>() {}.type)
                        if (json.code == 0) {
                            val cash = json.data
                            recy_environmental_show!!.setPullRefreshEnabled(false)
                            recy_environmental_show!!.setLoadingMoreEnabled(false)
                            val environmentalAdapter1 = EnvironmentalAdapter(this@FrameYewuDeteil, cash.list,1)
                            environmentalAdapter1.setOnClickEnvironLister(object : EnvironmentalAdapter.OnClickEnvironLister {
                                override fun onClick(position: Int) {
                                    this@FrameYewuDeteil.hbjcPosition = position
                                    val type = AppCache.getInstance().type
                                    val intent = Intent(this@FrameYewuDeteil, HBJCDetailActivity::class.java)
                                    intent.putExtra("pjenvior", cash.list.get(position))
                                    startActivity(intent)
                                }

                                override fun onDeleteClick(position: Int) {
                                    var dialog = SweetAlertDialog(this@FrameYewuDeteil, SweetAlertDialog.WARNING_TYPE)
                                    dialog?.titleText = "是否删除？"
                                    dialog?.confirmText = "确认"
                                    dialog?.showCancelButton(true)
                                    dialog?.showContentText(false)
                                    dialog?.show()
                                    dialog?.setConfirmClickListener {
                                        val encrypt = AesEncryptUtils.encrypt("{\"ids\":[" + cash.list.get(position).id + "]}")
                                        var sss = "{\"requestData\":\"" + encrypt + "\"}"
                                        OkGo.post<String>(ApiConstants.ENVIORSUPVSDELETES).upJson(sss).execute(object :
                                                BaseNet<String>() {
                                            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                                                super.onStart(request)
                                                LoadingDialog.showDialogForLoading(this@FrameYewuDeteil)
                                            }

                                            override fun onSuccess(response: Response<String>?) {
                                                val cash = response?.body()

                                                if (cash != null) {
                                                    val decrypt = AesEncryptUtils.decrypt(cash)
                                                    val json: BaseRespose<*> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<*>?>() {}.type)
                                                    if (json.code == 0) {
                                                        getHbjcData(point, lastSelect)
                                                        ToastUtils.showShort("删除成功")
                                                        dialog.dismiss()
                                                    } else {
                                                        ToastUtils.showShort("删除失败")
                                                        dialog.dismiss()
                                                    }

                                                } else {
                                                    ToastUtils.showShort("删除失败")
                                                    dialog.dismiss()
                                                }
                                            }

                                            override fun onFinish() {
                                                super.onFinish()
                                                LoadingDialog.cancelDialogForLoading()
                                            }

                                            override fun onError(response: Response<String>?) {
                                                super.onError(response)
                                                ToastUtils.showShort("删除失败")
                                            }

                                        })
                                    }

                                }

                            })
                            recy_environmental_show!!.adapter = environmentalAdapter1
                        } else {
                        }
                    } else {
                    }
                } else {
                }
            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
            }
        })
    }

    fun getWfxcData(point: String, type: Int) {
        val jsonObject = JSONObject()

        if (point.equals("1")) {
            jsonObject.put("xzqmc", "")
            jsonObject.put("type", type)
            jsonObject.put("page", 1)
            jsonObject.put("limit", 9999)
        } else {
            jsonObject.put("xzqmc", "")
            jsonObject.put("type", type)
            jsonObject.put("point", point)
            jsonObject.put("page", 1)
            jsonObject.put("limit", 9999)
        }


        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        OkGo.post<String>(ApiConstants.ILLEGAL_LIST).upJson(sss).execute(object ://BaseRespose<List<MoveCost>>
                BaseNet<String>() {
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
            }

            override fun onSuccess(response: Response<String>?) {
                if (response != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())
                    val json: BaseRespose<PageUtils<IllegalEntity>> = Gson().fromJson(decrypt, object : TypeToken<BaseRespose<PageUtils<IllegalEntity>>?>() {}.type)
                    if (json.code == 0) {
                        if (type == 1){
                            wfxc_tv_hz.visibility = View.VISIBLE
                            wfxc_tv_mph.visibility = View.VISIBLE
                        }else{
                            wfxc_tv_hz.visibility = View.GONE
                            wfxc_tv_mph.visibility = View.GONE
                        }

                        var wfxcAdapter = WfxcAdapter(R.layout.item_wfxc, json.data.list as List<IllegalEntity>,type)
                        wfxc_rlv!!.layoutManager = LinearLayoutManager(this@FrameYewuDeteil, LinearLayoutManager.VERTICAL, false)
                        wfxc_rlv!!.setAdapter(wfxcAdapter)
                        wfxcAdapter.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
//                            ShowChoise(json.data.list.get(position))
                            var intent = Intent(this@FrameYewuDeteil,WfxcDetailActivity::class.java)
                            intent.putExtra("illegalEntity",json.data.list.get(position))
                            startActivity(intent)
                        })
                        wfxcAdapter.setOnWfxcClickLinear(object : WfxcAdapter.OnWfxcClickLinear{
                            override fun onDeleteClick(illegalEntity: IllegalEntity?) {
                                mPresenter.getWfxcDelete(illegalEntity!!.id.toString())
                            }

                            override fun onUpdateClick(illegalEntity: IllegalEntity?) {
                                showUpDatePop(illegalEntity!!)
                            }
                        })

                    } else {

                    }
                } else {
                }
            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
            }
        })
    }
    private fun ShowChoise(illegalEntity: IllegalEntity) {
        val builder = AlertDialog.Builder(this@FrameYewuDeteil, android.R.style.Theme_Holo_Light_Dialog)
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("违法查询列表操作")
        //    指定下拉列表的显示数据
        val cities = arrayOf("删除", "修改", "查看")
        //    设置一个下拉的列表选择项
        builder.setItems(cities) { dialog, which ->
            if (which == 0) {
                mPresenter.getWfxcDelete(illegalEntity.id.toString())
            } else if (which == 1) {
                showUpDatePop(illegalEntity)
            } else if (which == 2) {
                showPicPop(illegalEntity)
            }
        }
        val show = builder.show()
        show.setCanceledOnTouchOutside(true)
        show.window.setBackgroundDrawableResource(android.R.color.transparent)
    }
    private fun showUpDatePop(teskFiles: IllegalEntity) {
        selectedPhotos.clear() //清空图片集合
        intList.clear() //清空图片id集合
        picPop = CommenPop.getNormalPopu(this@FrameYewuDeteil, R.layout.pop_wfxc_update, frame_yewu_det_top)
        val contentView: View = picPop!!.getContentView()
        val recy_map = contentView.findViewById<View>(R.id.sp_wfxc_pop) as Spinner
        val butWfxcSure = contentView.findViewById<View>(R.id.but_wfxc_sure) as TextView
        val rlvWfxcPop = contentView.findViewById<View>(R.id.rlv_tpsc_wfxc_pop) as RecyclerView
        photoAdapter = PhotoAdapter(this@FrameYewuDeteil, selectedPhotos)
        rlvWfxcPop.layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        rlvWfxcPop.adapter = photoAdapter
        rlvWfxcPop.addOnItemTouchListener(RecyclerItemClickListener(this,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(this@FrameYewuDeteil, Activity.RESULT_FIRST_USER)
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(this@FrameYewuDeteil, Activity.RESULT_FIRST_USER)
                        }
                    }
                }))
        val spinnerAdapter = SpinnerAdapter(this@FrameYewuDeteil)
        recy_map.adapter = spinnerAdapter
        val stringList: MutableList<String> = java.util.ArrayList()
        stringList.add("未完成")
        stringList.add("已完成")
        spinnerAdapter.setDatas(stringList)
        recy_map.setSelection(teskFiles.statusInt)
        picPop!!.showAtLocation(contentView, Gravity.CENTER, 0, 0)
        butWfxcSure.setOnClickListener {
            if (selectedPhotos.size > 0) {
                for (i in selectedPhotos) {
                    uplodeUpdate(File(i), teskFiles, recy_map)
                }
                picPop!!.dismiss()
            } else {
                ToastUtils.showShort("请上传图片")
                //                    mPresenter.getAdd(teskFiles.getXzqmc(),teskFiles.getObjectid()+"",etWfxcAddMqzk.getText().toString(),etWfxcAddRemark.getText().toString(),intList);
            }
        }
    }
    *//**
     * 违法巡查修改上传图片
     *
     * @return
     *//*
    fun uplodeUpdate(file: File, teskFiles: IllegalEntity, recy_map: Spinner) {
        val httpParams = HttpParams()
        var substring = ""
        substring = if (file.name.length > 30) {
            val length = file.name.length
            file.name.substring(length - 20, length - 5)
        } else {
            file.name.substring(0, file.name.length - 5)
        }
        httpParams.put(file.name, file)
        OkGo.post<BaseRespose<IllegalFileEntity>>(ApiConstants.ILLEGAL_UPLOAD_FILE)
                .params(httpParams)
                .execute(object : BaseNet<BaseRespose<IllegalFileEntity>>() {
                    override fun onStart(request: Request<BaseRespose<IllegalFileEntity>, out Request<*, *>?>?) {
                        super.onStart(request)
                        if (!LoadingDialog.isShowing()) LoadingDialog.showDialogForLoading(this@FrameYewuDeteil)
                    }

                    override fun onSuccess(response: Response<BaseRespose<IllegalFileEntity>>) {
                        val body: BaseRespose<IllegalFileEntity> = response.body()
                        if (body.data != null) {
                            intList.add(body.data.getId())
                        }
                        if (selectedPhotos.size == intList.size) {
                            val selectedItem = recy_map.selectedItemPosition
                            if (selectedItem == 0) {
                                mPresenter.getWfxcUpdate(teskFiles.id.toString(), "0", intList)
                            } else if (selectedItem == 1) {
                                mPresenter.getWfxcUpdate(teskFiles.id.toString(), "10", intList)
                            }
                        } else {
                            ToastUtils.showShort("图片名太长或图片以损坏")
                        }
                        LoadingDialog.cancelDialogForLoading()
                        //                        showSuccessMsg("上传成功");
                    }

                    override fun onError(response: Response<BaseRespose<IllegalFileEntity>>) {
                        super.onError(response)
                        ToastUtils.showShort(response.getException().message)
//                        showErrorMsg(response.getException().message)
                        LoadingDialog.cancelDialogForLoading()
                    }
                })
    }
    *//***
     * 违法巡查查看图片
     * @param teskFiles
     *//*
    private fun showPicPop(teskFiles: IllegalEntity) {
        val picpicPop = CommenPop.getNormalPopu(this@FrameYewuDeteil, R.layout.pop_wfxc_pic, frame_yewu_det_top)
        val contentView = picpicPop.contentView
        val rlvPicWfxcPop = contentView.findViewById<View>(R.id.rlv_pic_wfxc_pop) as RecyclerView
        val butWfxcSure = contentView.findViewById<View>(R.id.but_wfxc_pic_sure) as TextView
        val baseQuickAdapter: BaseQuickAdapter<IllegalFileEntity, BaseViewHolder> = object : BaseQuickAdapter<IllegalFileEntity, BaseViewHolder>(R.layout.item_wfxc_pic, teskFiles.illegalFileEntityList) {
            override fun convert(helper: BaseViewHolder, item: IllegalFileEntity) {
                val view = helper.getView<ImageView>(R.id.iv_item_wfxc_pic)
                //                Glide.with(getActivity()).load(item.getPath()).load(view);
                val options = RequestOptions() //                .placeholder(R.drawable.video_default) // 正在加载中的图片  
                        .error(R.drawable.error_center_x) // 加载失败的图片  
                // 磁盘缓存策略  .diskCacheStrategy(DiskCacheStrategy.ALL)
                val UTL = ApiConstants.BASE_URL + item.path.substring(23, item.path.length)
                val UTL1 = ApiConstants.BASE_URL + "" + item.path //.substring(23,item.getPath().length())
                Glide.with(this@FrameYewuDeteil)
                        .load(UTL1) // 图片地址 
                        .apply(options) // 参数 
                        .into(view) // 需要显示的ImageView控件  
                view.setOnClickListener {
                    val intent = Intent(this@FrameYewuDeteil, BigPicActivity::class.java)
                    intent.putExtra("url", UTL1)
                    *//*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                                            imgv_item_home_frag, "pic_local").toBundle();
                                            mContext.startActivity(intent,options);
                                        }else{*//*mContext.startActivity(intent)
                    *//*   this@TcglDetailActivity!!.overridePendingTransition(R.anim.fade_in,
                                                    com.setsuna.common.R.anim.fade_out)
                                        }*//*
                }
            }
        }
        rlvPicWfxcPop.layoutManager = GridLayoutManager(this@FrameYewuDeteil, 3)
        rlvPicWfxcPop.adapter = baseQuickAdapter
        picpicPop.showAtLocation(contentView, Gravity.CENTER, 0, 0)
        butWfxcSure.setOnClickListener { picpicPop.dismiss() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) // && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)
        if (resultCode == Activity.RESULT_OK) {
            var photos: ArrayList<String>? = null
            selectedPhotos.clear()
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
            }
            if (photos != null) {
                selectedPhotos.addAll(photos)
            }
            if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
        }
    }*/

}
