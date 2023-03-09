package com.jymj.zhglxt.zjd.activity

import android.content.Intent
import android.text.Editable
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.bean.UserListBean
import com.jymj.zhglxt.login.contract.LoginContract
import com.jymj.zhglxt.login.model.LoginModel
import com.jymj.zhglxt.login.presenter.LoginPresenter
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.jymj.zhglxt.util.PhoneUtil
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.SPUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_select_zh.*

class SelectZhActivity : BaseActivity<LoginPresenter, LoginModel>(), LoginContract.View {

    var uname=""
    var upwd = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_select_zh
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_select_zh_back.setOnClickListener {
            finish()
        }

    }

    override fun initDatas() {

        rlv_act_select_zh.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        var fwEntities = ArrayList<UserListBean>()
        fwEntities.add(UserListBean("admin","123456","黄村镇"))
        fwEntities.add(UserListBean("ybc","ybc123","100个村"))
        fwEntities.add(UserListBean("xhc","xhc123","新航程"))
        fwEntities.add(UserListBean("hsd","hsd123","黄山店村"))
        fwEntities.add(UserListBean("bzc01","123456","北臧村镇"))
        rlv_act_select_zh.adapter = object : BaseQuickAdapter<UserListBean, BaseViewHolder>(R.layout.item_select_num_layout, fwEntities) {
            override fun convert(helper: BaseViewHolder, item: UserListBean) {
                val tvItemSelectNum = helper.getView<TextView>(R.id.tv_item_select_num)
                tvItemSelectNum.setText(Html.fromHtml("<b>${item.getXzqmc()}:</b> ${item.getUname()}"))
//                helper.setText(R.id.tv_item_select_num, "" + item.getUname())

                helper.itemView.setOnClickListener {
                    //                        onSelectNumLinster.onClick(item);
                    if (SingleOnClickUtil.isFastClick()){
                        val deviceId = PhoneUtil.getDeviceId(this@SelectZhActivity)
                        uname = item.getUname()
                        upwd = item.getUnpwd()
                        if (uname.isEmpty() || upwd.isEmpty()) {
                            ToastUtils.showLong("请正确填写用户或密码!")
                        } else {
//                            activities.remove(this@SelectZhActivity)
//                            clearActivity()
                            mPresenter.getUserRequest(uname, upwd, deviceId)

                        }
                    }

                }
            }

        }

    }

    override fun returnUser(user: User) {
        clearActivity(this)
        AppCache.getInstance().type = user.type!!//用于区分用户类型
        AppMenus.getInstance().menuBeans1.clear()//防止退出登陆时 登陆其他账号权限混乱
        AppMenus.getInstance().menus.clear()//防止退出登陆时 登陆其他账号菜单混乱
        if (user.menus!=null){
            for (i in user.menus){
                if (i.parentName.contains("app")){
                    AppMenus.getInstance().menuBeans1.add(i)
                }
                if (i.parentName.contains("app菜单管理")){
                    AppMenus.getInstance().menus.add(i)
                }
            }
        }
        AppCache.getInstance().userId = user.userId
        AppCache.getInstance().code = user.code
        AppCache.getInstance().cunCode = user.code
        AppCache.getInstance().xzqName = user.xzqmc
        AppCache.getInstance().name = user.username
        AppCache.getInstance().phone = user.mobile
        AppCache.getInstance().zmmc = user.typeText
        AppCache.getInstance().loginCenter = user.center
        AppMenus.getInstance().textCenter = user.center//测试使用
        AppCache.getInstance().password = upwd
//        var intent = Intent(this, MainActivity::class.java)//跳转菜单

        var intent1 = Intent(this, FirstActivity::class.java)//跳转菜单
        startActivity(intent1)
        finish()
    }

    override fun changeUser() {
        
    }

    override fun changeUser(msg: String) {
        
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

}
