package com.jymj.zhglxt.zjd.activity.zjdgl

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity
import com.jymj.zhglxt.zjd.bean.wy.HousingLeaseEntity
import com.jymj.zhglxt.zjd.bean.wy.LeaseDto
import com.jymj.zhglxt.zjd.bean.wy.PropertyDto
import com.jymj.zhglxt.zjd.contract.PropertyYlfjContract
import com.jymj.zhglxt.zjd.presenter.PropertyYlfjPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration
import kotlinx.android.synthetic.main.activity_property_lszk.*

/**
 * 历史租客
 */
class PropertyLszkActivity : BaseActivity<PropertyYlfjPresenter, BaseModel>(), PropertyYlfjContract.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_property_lszk
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        rlv_at_property_lszk_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = DefaultItemDecoration(Color.parseColor("#eeeeee"))
        rlv_at_property_lszk_list.addItemDecoration(itemDecoration)
        val longExtra = intent.getLongExtra("housIdFlag", 0L)
        if (longExtra != 0L) {
            mPresenter.getHistoryTenant(longExtra)
        }
    }

    override fun initDatas() {
        iv_at_property_lszk_finish.setOnClickListener {
            finish()
        }
    }

    override fun returnWyPoint(entity: PropertyDto) {
    }

    override fun returnFlowGetInfo(entity: List<FlowInfoEntity>) {
    }

    //历史租客
    override fun returnHistoryTenant(entity: List<HousingLeaseEntity>) {
        if (entity != null && entity.size > 0) {
            rlv_at_property_lszk_list.adapter = object : BaseQuickAdapter<HousingLeaseEntity, BaseViewHolder>(R.layout.item_wy_cz_lszk_list, entity) {
                override fun convert(helper: BaseViewHolder?, item: HousingLeaseEntity?) {
                    helper!!.setText(R.id.tv_item_wy_cz_lszk_name, item!!.czr)
                            .setText(R.id.tv_item_wy_cz_lszk_time, item.startDate.substring(0,10)+"——"+item.endDate.substring(0,10))
                }
            }

        }
    }

    override fun returnLeaseInfo(entity: LeaseDto) {
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

}
