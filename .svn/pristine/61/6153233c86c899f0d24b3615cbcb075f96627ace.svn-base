package com.jymj.zhglxt.personal.activity

import com.jymj.zhglxt.R
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.baseapp.AppCache
import kotlinx.android.synthetic.main.activity_personal_data.*

class PersonalDataActivity : BaseActivity<BasePresenter<*,*>, BaseModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_personal_data
    }

    override fun initPresenter() {
//        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_personal_data_back.setOnClickListener {//关闭当前页面
            finish()
        }
        ll_act_personal_data_head.setOnClickListener {}
        ll_act_personal_data_name.setOnClickListener {}
        ll_act_personal_data_phone.setOnClickListener {}
        ll_act_personal_data_lgy.setOnClickListener {}


    }

    override fun initDatas() {
        tv_act_personal_name.setText(AppCache.getInstance().name)
        tv_act_personal_phone.setText(AppCache.getInstance().phone)
        tv_act_personal_lgy.setText(AppCache.getInstance().zmmc)

    }

}
