/*
package com.jymj.newhczglxy.zjd.fragment.yzt


import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.jymj.basemessagesystem.ui.adapter.CgDetailAdapter
import com.jymj.basemessagesystem.ui.adapter.PolygonDetailAdapter
import com.jymj.basemessagesystem.ui.adapter.TgDetailAdapter
import com.jymj.basemessagesystem.ui.adapter.TxDetailAdapter

import com.jymj.hczzhglxt.R
import com.jymj.hczzhglxt.bean.*
import com.jymj.hczzhglxt.bean.cg.CgDetail
import com.jymj.hczzhglxt.bean.cg.CgEntity
import com.jymj.hczzhglxt.bean.cg.CgPolygonEntity
import com.jymj.hczzhglxt.bean.cj.CJEntity
import com.jymj.hczzhglxt.bean.cs.*
import com.jymj.hczzhglxt.bean.envior.PjEnviorSupvsEntity
import com.jymj.hczzhglxt.bean.feizhai.EmpsituaEnum
import com.jymj.hczzhglxt.bean.feizhai.HuTypeEnum
import com.jymj.hczzhglxt.bean.illegal.GhFhEntity
import com.jymj.hczzhglxt.bean.illegal.IllegalEntity
import com.jymj.hczzhglxt.bean.ithy.IthyEntity
import com.jymj.hczzhglxt.bean.ithy.LthyDetailEntity
import com.jymj.hczzhglxt.bean.ldrk.LdrkPointBean
import com.jymj.hczzhglxt.bean.nyd.ZzyNydEntity
import com.jymj.hczzhglxt.bean.polygon.PolygonDetailEntity
import com.jymj.hczzhglxt.bean.polygon.PolygonQueryEntity
import com.jymj.hczzhglxt.bean.sys.SysKxXzqData
import com.jymj.hczzhglxt.bean.tdly.TdlyDetail
import com.jymj.hczzhglxt.bean.tdly.TdlyEntity
import com.jymj.hczzhglxt.bean.tg.TGCunEntity
import com.jymj.hczzhglxt.bean.tg.TgDetail
import com.jymj.hczzhglxt.bean.tg.TgEntity
import com.jymj.hczzhglxt.bean.tg.TgPolygonEntity
import com.jymj.hczzhglxt.bean.tx.TxCunEntity
import com.jymj.hczzhglxt.bean.tx.TxPolygonEntity
import com.jymj.hczzhglxt.bean.yewu.YeWuFrameBean
import com.jymj.hczzhglxt.bean.yl.YLEntity
import com.jymj.hczzhglxt.bean.yl.YLPointEntity
import com.jymj.hczzhglxt.bean.yzt.YztCountEntity
import com.jymj.hczzhglxt.ui.main.base.BBaseFragment
import com.jymj.hczzhglxt.ui.main.contract.MainContract
import com.jymj.hczzhglxt.ui.main.model.MainModel
import com.jymj.hczzhglxt.ui.main.presenter.MainPresenter
import com.jymj.hczzhglxt.widget.MyBottomSheetDialog
import com.jymj.newhczglxy.R
import com.jymj.newhczglxy.rjhj.bean.PjEnviorSupvsEntity
import com.jymj.newhczglxy.rjhj.bean.YLEntity
import com.jymj.newhczglxy.rjhj.bean.yl.IllegalEntity
import com.jymj.newhczglxy.rjhj.bean.yl.LdrkPointBean
import com.jymj.newhczglxy.rjhj.bean.yl.YztPointEntity
import com.jymj.newhczglxy.rjhj.bean.yl.cg.CgDetail
import com.jymj.newhczglxy.rjhj.bean.yl.cg.CgEntity
import com.jymj.newhczglxy.rjhj.bean.yl.cg.CgPolygonEntity
import com.jymj.newhczglxy.rjhj.bean.yl.tdly.TdlyDetail
import com.jymj.newhczglxy.rjhj.bean.yl.tdly.TdlyEntity
import com.jymj.newhczglxy.rjhj.bean.yl.yzt.YztCountEntity
import com.jymj.newhczglxy.widget.MyBottomSheetDialog
import com.jymj.newhczglxy.zjd.bean.*
import com.jymj.newhczglxy.zjd.bean.yzt.cs.*
import com.jymj.newhczglxy.zjd.bean.yzt.polygon.PolygonDetailEntity
import com.jymj.newhczglxy.zjd.bean.yzt.polygon.PolygonQueryEntity
import com.jymj.newhczglxy.zjd.bean.yzt.CTGLPOINTBean
import com.jymj.newhczglxy.zjd.bean.yzt.XzqEntity
import com.jymj.newhczglxy.zjd.bean.yzt.cj.CJEntity
import com.jymj.newhczglxy.zjd.bean.yzt.illegal.GhFhEntity
import com.jymj.newhczglxy.zjd.bean.yzt.ithy.IthyEntity
import com.jymj.newhczglxy.zjd.bean.yzt.ithy.LthyDetailEntity
import com.jymj.newhczglxy.zjd.bean.yzt.layerListBean
import com.jymj.newhczglxy.zjd.bean.yzt.nyd.ZzyNydEntity
import com.jymj.newhczglxy.zjd.bean.yzt.tg.TGCunEntity
import com.jymj.newhczglxy.zjd.bean.yzt.tg.TgDetail
import com.jymj.newhczglxy.zjd.bean.yzt.tg.TgEntity
import com.jymj.newhczglxy.zjd.bean.yzt.tg.TgPolygonEntity
import com.jymj.newhczglxy.zjd.bean.yzt.tx.TxCunEntity
import com.jymj.newhczglxy.zjd.bean.yzt.tx.TxPolygonEntity
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.fragment_frame_jcxx.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

*/
/**
 * A simple [Fragment] subclass.
 * ??????????????????fragment
 *//*

class FrameJCXXFragment : BaseFragment<MainPresenter, MainModel>(), MainContract.View {
    override fun onCtglZhen(string: CTGLZhenBean.DataBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCtglPqList(string: List<PQListBean.DataBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFrameJcxx(isSave: FrameJcxxBean.DataBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onYewuFrame(entity: YeWuFrameBean.DataBean) {
    }


    private var mSe0List: MutableList<PolygonQueryEntity.MaleListBean>? = null
    private var mSe1List: MutableList<PolygonQueryEntity.MaleListBean>? = null
    private var mSe2List: MutableList<PolygonQueryEntity.MaleListBean>? = null
    private var botDialog: MyBottomSheetDialog? = null
    private var recyView: RecyclerView? = null
    private var count = 1
    private var mCurrentCounter = 0
    private var position = 0
    private var fwqsdatas: MutableList<YLEntity>? = null
    private var detailAdapter: PolygonDetailAdapter? = null
    private var txDetailAdapter: TxDetailAdapter? = null
    private var tgDetailAdapter: TgDetailAdapter? = null
    private var cgDetailAdapter: CgDetailAdapter? = null
    private var detailEntity: PolygonDetailEntity? = null
    private var deTdlyEntity: TdlyDetail? = null
    private var tgDetailEntity: TgDetail? = null
    private var cgDetailEntity: CgDetail? = null
    private var sbs: String? = null
    private var txdatas: MutableList<TdlyDetail.TdlyListBean>? = null
    private var tgsdatas: MutableList<TgDetail.TgEntityListBean>? = null
    private var cgdatas: MutableList<CgDetail.CgListBean>? = null
    private var ylFlag: Int = 0
    private var txFlag: Int = 0
    private var tgFlag: Int = 0
    private var cgFlag: Int = 0

    //????????????
    fun fwData(polygonQueryEntity: PolygonQueryEntity) {
        if (polygonQueryEntity != null) {
            frame_fw_ll.visibility = View.VISIBLE
            mSe0List = polygonQueryEntity.se0List
            mSe1List = polygonQueryEntity.se1List
            mSe2List = polygonQueryEntity.se2List
            val empList = polygonQueryEntity.empList
            val huTypeList = polygonQueryEntity.huTypeList
            val ylList = polygonQueryEntity.ylList

            var num1 = 0
            var num2 = 0
            var num3 = 0
            if (mSe0List != null && mSe0List!!.isNotEmpty()) {
                num1 += mSe0List!![0].object1
                num2 += mSe0List!![0].object2
                num3 += mSe0List!![0].object3
            }
            if (mSe1List != null && mSe1List!!.isNotEmpty()) {
                num1 += mSe1List!![0].object1
                num2 += mSe1List!![0].object2
                num3 += mSe1List!![0].object3
            }
            if (mSe2List != null && mSe2List!!.isNotEmpty()) {
                num1 += mSe2List!![0].object1
                num2 += mSe2List!![0].object2
                num3 += mSe2List!![0].object3
            }

            setSex(num1, num2, num3)

            if (empList != null) {
                //????????????????????????
                val empDatas = ArrayList<Int>()
                val empLables = ArrayList<String>()
                for (m in empList) {
                    empDatas.add(m.object2)
                    empLables.add(EmpsituaEnum.getName(m.object1))
                }
                initPie(pie_work_polygon!!, "????????????", empDatas, empLables)
            }
            if (huTypeList != null) {
                //????????????????????????
                val huDatas = ArrayList<Int>()
                val huLables = ArrayList<String>()
                for (m in huTypeList) {
                    huDatas.add(m.object2)
                    huLables.add(HuTypeEnum.getName(m.object1))
                }
                initPie(pie_hu_polygon!!, "????????????", huDatas, huLables)
            }
            if (ylList != null && ylList.size > 0) {
                val ylDatas = ArrayList<Int>()
                val ylLables = ArrayList<String>()
                ylDatas.add(ylList[0].object1)
                ylLables.add("3?????????")
                ylDatas.add(ylList[0].object2)
                ylLables.add("3???-4???")
                ylDatas.add(ylList[0].object3)
                ylLables.add("4?????????")

                initPie(pie_mj_polygon!!, "???????????????", ylDatas, ylLables)
            }
        } else {
            frame_fw_ll.visibility = View.GONE
        }

    }

    //??????????????????
    fun fwMxData(polygonDetailEntity: PolygonDetailEntity, sb: String) {
        detailEntity = polygonDetailEntity
        sbs = sb
    }

    //????????????????????????
    fun showfwMxData(polygonDetailEntity: PolygonDetailEntity, sb: String) {
        count++
        val ylEntityList = polygonDetailEntity?.ylEntityList
        if (ylEntityList != null) {
            fwqsdatas?.addAll(ylEntityList)
        }
        if (detailAdapter == null) {
            if (ylEntityList != null) {
                mCurrentCounter = ylEntityList.size
            }
            detailAdapter = PolygonDetailAdapter(R.layout.item_fwqs_detail, ylEntityList)
            recyView?.adapter = detailAdapter
            detailAdapter?.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
                mPresenter.getFwqsPolygonDetail(sb.toString(), 20, count)
            }, recyView)
            detailAdapter?.disableLoadMoreIfNotFullPage()
            //?????????????????????
            detailAdapter?.setOnItemChildClickListener { adapter, view, position ->
                botDialog?.dismiss()
            }
            //????????????????????????????????????????????? ???????????????????????????
            detailAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

                    botDialog?.dismiss()
                }

            })
        } else {
            if (mCurrentCounter >= polygonDetailEntity?.ylCount!!) {
                //????????????????????????
                detailAdapter?.loadMoreEnd()
            } else {
                //????????????????????????
                detailAdapter?.addData(ylEntityList!!)
                mCurrentCounter = detailAdapter?.data?.size!!
                detailAdapter?.loadMoreComplete()
            }
        }
    }

    //????????????
    fun txData(list: MutableList<TxPolygonEntity>) {
        if (list.size > 0) {
            if (list[0] != null) {
                frame_tx_pchart.visibility = View.VISIBLE
                val ylDatas = ArrayList<Int>()
                val ylLables = ArrayList<String>()

                ylDatas.add(list[0].jsydmj.toInt())
                ylLables.add("????????????")
                ylDatas.add(list[0].nydmj.toInt())
                ylLables.add("?????????")
                ylDatas.add(list[0].tjsmj.toInt())
                ylLables.add("?????????")
                ylDatas.add(list[0].wlydmj.toInt())
                ylLables.add("?????????")

                initPie(frame_tx_pchart!!, "????????????", ylDatas, ylLables)
            }
        } else {
            frame_tx_pchart.visibility = View.GONE
        }
    }

    //??????????????????
    fun txMxData(tdlyDetail: TdlyDetail, sb: String) {
        deTdlyEntity = tdlyDetail
        sbs = sb
    }

    //????????????????????????
    fun showtxMxData(tdlyDetail: TdlyDetail, sb: String) {
        count++
        val ylEntityList = tdlyDetail?.tdlyList
        if (ylEntityList != null) {
            txdatas?.addAll(ylEntityList)
        }
        if (txDetailAdapter == null) {
            if (ylEntityList != null) {
                mCurrentCounter = ylEntityList.size
            }
            txDetailAdapter = TxDetailAdapter(R.layout.item_fwqs_detail, ylEntityList)
            recyView?.adapter = txDetailAdapter
            txDetailAdapter?.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
                mPresenter.getTxPolygonDetail(sb.toString(), 20, count)
            }, recyView)
            txDetailAdapter?.disableLoadMoreIfNotFullPage()
            txDetailAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    botDialog?.dismiss()
                }

            })
        } else {
            if (mCurrentCounter >= tdlyDetail?.tdLyCount!!) {
                //????????????????????????
                txDetailAdapter?.loadMoreEnd()
            } else {
                //????????????????????????
                txDetailAdapter?.addData(ylEntityList!!)
                mCurrentCounter = txDetailAdapter?.data?.size!!
                txDetailAdapter?.loadMoreComplete()
            }
        }
    }

    //????????????
    fun tgData(list: MutableList<TgPolygonEntity>) {
        if (list.size > 0) {
            frame_tg_pchart.visibility = View.VISIBLE
            val indDatas = ArrayList<Int>()
            val indLables = ArrayList<String>()
            for (m in list) {
                indDatas.add(m.object2.toInt())
                indLables.add(m.object1)
            }
            initPie(frame_tg_pchart!!, "????????????", indDatas, indLables)
        } else {
            frame_tg_pchart.visibility = View.GONE
        }
    }

    //??????????????????
    fun tgMxData(tgDetail: TgDetail, sb: String) {
        tgDetailEntity = tgDetail
        sbs = sb
    }

    //????????????????????????
    fun showtgMxData(tgDetail: TgDetail, sb: String) {
        count++
        val ylEntityList = tgDetail?.tgEntityList
        if (ylEntityList != null) {
            tgsdatas?.addAll(ylEntityList)
        }
        if (tgDetailAdapter == null) {
            if (ylEntityList != null) {
                mCurrentCounter = ylEntityList.size
            }
            tgDetailAdapter = TgDetailAdapter(R.layout.item_fwqs_detail, ylEntityList)
            recyView?.adapter = tgDetailAdapter
            tgDetailAdapter?.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
                mPresenter.getTgPolygonDetail(sb.toString(), 20, count)
            }, recyView)
            tgDetailAdapter?.disableLoadMoreIfNotFullPage()
            tgDetailAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    botDialog?.dismiss()
                }

            })
        } else {
            if (mCurrentCounter >= tgDetail?.tgCount!!) {
                //????????????????????????
                tgDetailAdapter?.loadMoreEnd()
            } else {
                //????????????????????????
                tgDetailAdapter?.addData(ylEntityList!!)
                mCurrentCounter = tgDetailAdapter?.data?.size!!
                tgDetailAdapter?.loadMoreComplete()
            }
        }
    }

    //????????????
    fun cgData(list: MutableList<CgPolygonEntity>) {
        if (list.size > 0) {
            frame_cg_pchart.visibility = View.VISIBLE
            val indDatas = ArrayList<Int>()
            val indLables = ArrayList<String>()
            for (m in list) {
                indDatas.add(m.object2.toInt())
                indLables.add(m.object1)
            }
            initPie(frame_cg_pchart!!, "????????????", indDatas, indLables)
        } else {
            frame_cg_pchart.visibility = View.GONE
        }
    }

    //??????????????????
    fun cgMxData(cgDetail: CgDetail, sb: String) {
        cgDetailEntity = cgDetail
        sbs = sb
    }

    fun showcgMxData(cgDetail: CgDetail, sb: String) {
        count++
        val ylEntityList = cgDetail?.cgList
        if (ylEntityList != null) {
            cgdatas?.addAll(ylEntityList)
        }
        if (cgDetailAdapter == null) {
            if (ylEntityList != null) {
                mCurrentCounter = ylEntityList.size
            }
            cgDetailAdapter = CgDetailAdapter(R.layout.item_fwqs_detail, ylEntityList)
            recyView?.adapter = cgDetailAdapter
            //????????????
            cgDetailAdapter?.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
                mPresenter.getCgPolygonDetail(sb.toString(), 20, count)
            }, recyView)
            cgDetailAdapter?.disableLoadMoreIfNotFullPage()
            cgDetailAdapter?.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    botDialog?.dismiss()
                }

            })
        } else {
            if (mCurrentCounter >= cgDetail?.cgCount!!) {
                //????????????????????????
                cgDetailAdapter?.loadMoreEnd()
            } else {
                //????????????????????????
                cgDetailAdapter?.addData(ylEntityList!!)
                mCurrentCounter = cgDetailAdapter?.data?.size!!
                cgDetailAdapter?.loadMoreComplete()
            }
        }
    }

    private fun setSex(num1: Int, num2: Int, num3: Int) {

        val maleDatas = ArrayList<Int>()
        val maleLables = ArrayList<String>()
        maleDatas.add(num1)
        maleLables.add("0-18")
        maleDatas.add(num2)
        maleLables.add("19-65")
        maleDatas.add(num3)
        maleLables.add("65+")

        initPie(pie_sex_polygon!!, "????????????", maleDatas, maleLables)
    }


    private fun initPie(pie: PieChart, title: String, datas: ArrayList<Int>, lables: ArrayList<String>) {
        pie.setUsePercentValues(false)//??????????????????
        pie.description.isEnabled = false//????????????
        pie.setExtraOffsets(5f, 10f, 5f, 5f)//????????????

        pie.dragDecelerationFrictionCoef = 0.95f//???????????????????????????????????????????????????

        pie.setExtraOffsets(20f, 0f, 20f, 0f)//????????????

        pie.isDrawHoleEnabled = true//???????????????true?????????????????????false????????????
        pie.setHoleColor(Color.WHITE)//???????????????????????????????????????

        pie.setTransparentCircleColor(Color.WHITE)//??????????????????????????????
        pie.setTransparentCircleAlpha(110)//?????????????????????????????????

        pie.holeRadius = 58f//??????????????????
        pie.transparentCircleRadius = 61f//??????????????????????????????,?????????????????????????????????

        pie.setDrawCenterText(true)//????????????????????????

        pie.centerText = title//?????????????????????
        pie.setCenterTextSize(20f)//???????????????????????????
        pie.rotationAngle = 0f//?????????????????????
        // enable rotation of the chart by touch
        pie.isRotationEnabled = true//??????????????????
        pie.isHighlightPerTapEnabled = true//??????????????????
        pie.setEntryLabelColor(Color.BLACK)
        setData(pie, datas, lables)

        pie.animateY(1400, Easing.EaseInOutQuad)
        // pie.spin(2000, 0, 360);

        val l = pie.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP//????????????????????????????????????
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT//????????????????????????????????????
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false//??????????????????
    }

    private fun setData(pie: PieChart, datas: List<Int>, lables: List<String>) {
        val entries = ArrayList<PieEntry>()

        for (i in datas.indices) {
            entries.add(PieEntry(datas[i].toFloat(), lables[i]))
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f//???????????????????????????
        dataSet.selectionShift = 5f//????????????????????????????????????????????????

        // add a lot of colors

        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);


        dataSet.valueLinePart1OffsetPercentage = 80f//???????????????????????????????????????????????????????????????
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        pie.data = data

        // undo all highlights
        pie.highlightValues(null)

        pie.invalidate()
    }

    */
/**
     * ?????????????????????????????????adapter
     *//*

    fun initBottomDialog() {
        botDialog = MyBottomSheetDialog(context!!, R.style.AlertDialogCustom)//R.style.CustomDialog  activity!!.applicationContext
        val botView = LayoutInflater.from(activity).inflate(R.layout.list_polygon_detail, null) as ConstraintLayout
        recyView = botView?.getChildAt(0) as RecyclerView
        recyView?.itemAnimator = DefaultItemAnimator()
        recyView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        botDialog?.setContentView(botView)
        botDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)  //??????????????????????????????????????????
        botDialog?.setCancelable(true)
        botDialog?.setCanceledOnTouchOutside(true)
//        botDialog!!.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG)
        frame_ylxx_mx.setOnClickListener {
            botDialog?.show()
            botDialog!!.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(500f))
            botDialog!!.getWindow().setGravity(Gravity.BOTTOM)
            recyView?.scrollToPosition(position)
        }
        frame_txxx_mx.setOnClickListener {
            botDialog?.show()
            botDialog!!.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(500f))
            botDialog!!.getWindow().setGravity(Gravity.BOTTOM)
            recyView?.scrollToPosition(position)
        }

        botDialog?.setOnShowListener {

            //            map?.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, map.height / 2)
        }
        botDialog?.setOnDismissListener {
//??????????????????
//            map?.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_frame_jcxx
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        initBottomDialog()
    }

    override fun initDatas() {

        frame_ylxx_mx.setOnClickListener {
            if (detailEntity != null) {
                showfwMxData(detailEntity!!, sbs!!)
                botDialog?.show()
                botDialog!!.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(500f))
                botDialog!!.getWindow().setGravity(Gravity.BOTTOM)
                recyView?.scrollToPosition(position)
            } else {
                ToastUtils.showShort("??????????????????")
            }
        }
        frame_txxx_mx.setOnClickListener {
            if (deTdlyEntity != null) {
                showtxMxData(deTdlyEntity!!, sbs!!)
                botDialog?.show()
                botDialog!!.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(500f))
                botDialog!!.getWindow().setGravity(Gravity.BOTTOM)
                recyView?.scrollToPosition(position)
            } else {
                ToastUtils.showShort("??????????????????")
            }
        }
        frame_tgxx_mx.setOnClickListener {
            if (tgDetailEntity != null) {
                showtgMxData(tgDetailEntity!!, sbs!!)
                botDialog?.show()

                botDialog!!.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(500f))
                botDialog!!.getWindow().setGravity(Gravity.BOTTOM)
                recyView?.scrollToPosition(position)
            } else {
                ToastUtils.showShort("??????????????????")
            }
        }
        frame_cgxx_mx.setOnClickListener {
            if (cgDetailEntity != null) {
                showcgMxData(cgDetailEntity!!, sbs!!)
                botDialog?.show()
                botDialog!!.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(500f))
                recyView?.scrollToPosition(position)
            } else {
                ToastUtils.showShort("??????????????????")
            }
        }

        frame_jcxx_yl_iv.setOnClickListener {
            if (ylFlag == 0) {
                frame_fw_ll.visibility = View.GONE
                ylFlag = 1
            } else {
                frame_fw_ll.visibility = View.VISIBLE
                ylFlag = 0
            }
        }
        frame_txxx_yl_iv.setOnClickListener {
            if (txFlag == 0) {
                frame_jcxx_tx_ll.visibility = View.GONE
                txFlag = 1
            } else {
                frame_jcxx_tx_ll.visibility = View.VISIBLE
                txFlag = 0
            }
        }

        frame_tgxx_yl_iv.setOnClickListener {
            if (tgFlag == 0) {
                frame_jcxx_tg_ll.visibility = View.GONE
                tgFlag = 1
            } else {
                frame_jcxx_tg_ll.visibility = View.VISIBLE
                tgFlag = 0
            }
        }
        frame_cgxx_yl_iv.setOnClickListener {
            if (cgFlag == 0) {
                frame_jcxx_cg_ll.visibility = View.GONE
                cgFlag = 1
            } else {
                frame_jcxx_cg_ll.visibility = View.VISIBLE
                cgFlag = 0
            }
        }

    }

    override fun onPointFwqs(ylPointEntity: YLPointEntity) {
    }

    override fun onYztHoOwner(yztCountEntity: YztCountEntity) {
    }

    override fun onYztPoint(yztPointEntity: YztPointEntity) {

    }

    override fun onCtglPoint(yztPointEntity: CTGLPOINTBean.DataBean) {

    }

    override fun onPolygonFwqs(polygonQueryEntity: PolygonQueryEntity) {
    }

    override fun onPolygonDetailFwqs(polygonDetailEntity: PolygonDetailEntity) {
        showfwMxData(polygonDetailEntity, sbs!!)
    }

    override fun onPointTx(tdlyEntity: TdlyEntity) {
    }

    override fun onPolygonTx(list: MutableList<TxPolygonEntity>) {
    }

    override fun onPolygonDetailTx(tdlyDetail: TdlyDetail) {
        showtxMxData(tdlyDetail, sbs!!)
    }

    override fun onPointCg(cgEntity: CgEntity) {
    }

    override fun onPolygonCg(list: MutableList<CgPolygonEntity>) {
    }

    override fun onPolygonDetailCg(cgDetail: CgDetail) {
        showcgMxData(cgDetail, sbs!!)
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
        showtgMxData(tgDetail, sbs!!)
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
    }

    override fun onWfxcDelete(zzyNydEntity: String) {
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

    }

    override fun onYztDelete(isSave: String) {
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }
}
*/
