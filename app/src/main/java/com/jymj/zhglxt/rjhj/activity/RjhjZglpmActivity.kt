package com.jymj.zhglxt.rjhj.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.home.activity.LdglFragment
import com.jymj.zhglxt.rjhj.bean.TownCountEntity
import com.jymj.zhglxt.ui.activity.MainActivity
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.commonutils.FragmentUtils
import kotlinx.android.synthetic.main.activity_rjhj_zglpm.*

/**
 * 人居环境  整改率排名页面
 */
class RjhjZglpmActivity : BaseActivity<BasePresenter<*,*>,BaseModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_rjhj_zglpm
    }

    override fun initPresenter() {
    }

    override fun initViews() {
        tv_act_rjhj_zglpm_back.setOnClickListener {
            finish()
        }
    }

    override fun initDatas() {
        val list = intent.getSerializableExtra("townCountEntity") as List<TownCountEntity>
        rlv_act_rjhj_zglpm?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_act_rjhj_zglpm?.adapter = object : BaseQuickAdapter<TownCountEntity, BaseViewHolder>(R.layout.item_firstpage_zgl_list, list) {
            override fun convert(helper: BaseViewHolder?, item: TownCountEntity?) {
                helper!!.setText(R.id.firstpage_item_zgl_pm, (helper.adapterPosition + 1).toString())
                        .setText(R.id.firstpage_item_zgl_zm, item!!.xzqmc)
                        .setText(R.id.firstpage_item_zgl_fs, item!!.total.toString())
                        .setText(R.id.firstpage_item_zgl_cl, item!!.yzg.toString())//wts  clwtsfirstpage_item_pm_fens
                        .setText(R.id.firstpage_item_zgl_fens, item!!.zgl.stripTrailingZeros().toPlainString()+"%")//item!!.monaverage.stripTrailingZeros().toPlainString()
                helper.itemView.setOnClickListener {//跳转问题列表页
                    FragmentUtils.addFragment(MainActivity.mActivity!!.supportFragmentManager, LdglFragment(),R.id.fl_content)
                }
            }
        }

    }

}
