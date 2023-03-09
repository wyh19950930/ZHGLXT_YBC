package com.jymj.zhglxt.login.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.view.View
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.bean.UserListBean
import com.jymj.zhglxt.login.contract.LoginContract
import com.jymj.zhglxt.login.model.LoginModel
import com.jymj.zhglxt.login.presenter.LoginPresenter
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.jymj.zhglxt.ui.activity.MainActivity
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.SPUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_login.*
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.Html
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.app.AppApplication
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.xm.activity.XmIssueActivity
import com.jymj.zhglxt.zjd.activity.zjdgl.CbbsjsxActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.commonutils.AppUtils


class LoginActivity : BaseActivity<LoginPresenter, LoginModel>(), LoginContract.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    companion object {
        /**
         * 入口
         * @param activity
         */

        fun startAction(activity: Context) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            /*activity.overridePendingTransition(R.anim.fade_in,
                    R.anim.fade_out)*/
        }
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }
    var isFirst = true
    var isUpdate = true
    override fun initViews() {
        AppMenus.getInstance().cardPath = GetFileUtil.getSDCardPath();

//        checkUpdate()

//        UpdateUtils().checkUpdate(this)//app更新
        tv_act_login_title1.setText(AppUtils.getAppName())
    }
    var intent1: Intent? = null
    override fun initDatas() {
        intent1 = Intent(this, FirstActivity::class.java)//跳转菜单

    }
    var mPointPopu:CommenPop? = null
    fun initPopuSelectNum(fwEntities: ArrayList<UserListBean>) {  //, ArrayList<Integer> nums
        //        activity = activity1;
        mPointPopu = CommenPop.getNormalPopu(this, R.layout.pop_point_select_num, act_login)//pop_point
        val contentView = mPointPopu!!.contentView
        val tv_pop_point_select_title = contentView.findViewById<TextView>(R.id.tv_pop_point_select_title)
        val tv_point_select_num_cancel = contentView.findViewById<TextView>(R.id.tv_point_select_num_cancel)
        val rlv_select_num = contentView.findViewById<RecyclerView>(R.id.rlv_select_num)
        tv_pop_point_select_title.setText(Html.fromHtml("请选择账号<font color='#409EFF'>(长按删除)</font>"))
        ///Html.fromHtml("请选择账号<font color='#409EFF'>(长按删除)</font>")
        rlv_select_num.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_select_num.adapter = object : BaseQuickAdapter<UserListBean, BaseViewHolder>(R.layout.item_select_num_layout, fwEntities) {
            override fun convert(helper: BaseViewHolder, item: UserListBean) {
                val tvItemSelectNum = helper.getView<TextView>(R.id.tv_item_select_num)
                tvItemSelectNum.setText(Html.fromHtml("<b>${item.getUname()}:</b> ${item.getXzqmc()}"))//item.getXzqmc()  item.getUname()
//                helper.setText(R.id.tv_item_select_num, "" + item.getUname())

                helper.itemView.setOnClickListener {
                    //                        onSelectNumLinster.onClick(item);
                    edt_username_act_login.text = Editable.Factory.getInstance().newEditable(item?.getUname())//普通赋值
                    edt_password_act_login.text = Editable.Factory.getInstance().newEditable(item?.getUnpwd())//普通赋值
//                    bt_login_act_login.isEnabled = false
                    val deviceId = PhoneUtil.getDeviceId(this@LoginActivity)
                    val uname = edt_username_act_login.text.toString().trim()
                    val upwd = edt_password_act_login.text.toString().trim()
                    if (uname.isEmpty() || upwd.isEmpty()) {
                        ToastUtils.showLong("请正确填写用户或密码!")
                        bt_login_act_login.isEnabled = true
                    } else {
                        bt_login_act_login.isEnabled = false
                        cb_act_login.isChecked = true
                        mPresenter.getUserRequest(uname, upwd, deviceId)

                    }
                    mPointPopu!!.dismiss()
                }
                helper.itemView.setOnLongClickListener(object : OnItemClickListener, View.OnLongClickListener {
                    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

                    }

                    override fun onLongClick(v: View?): Boolean {
                        val content = TextView(this@LoginActivity)
                        content.text = "是否删除？"
//                FileUtils.openFile()//AppConstant.getFileProvider()
                        SweetAlertDialog(this@LoginActivity, SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("删除账号信息")
                                .setCustomView(content)
                                .setConfirmButton(getString(R.string.confirm)) { sweetAlertDialog ->
                                    fwEntities.removeAt(helper.layoutPosition)
                                    SPUtils.setSharedStringData(this@LoginActivity,"userList",Gson().toJson(fwEntities))
                                    notifyDataSetChanged()
                                    sweetAlertDialog.dismiss()}
                                .setCancelButton(getString(R.string.cancel)) { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                                .show()
                        return false
                    }
                })
            }

        }

        CommenPop.backgroundAlpha(0.5f, this)
        mPointPopu!!.showAtLocation(act_login, Gravity.CENTER, 0, 0)
        tv_point_select_num_cancel.setOnClickListener { mPointPopu!!.dismiss() }
    }
    var count = 0;
    //页面加载完毕
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (isFirst){
            CheckUpdateUtil.checkUpdate(this, object:CheckUpdateUtil.OnCheckLinear{
                override fun onIsUpdate(boolean: Boolean) {
                    isFirst = false
                    isUpdate = boolean
                    checkLogin(hasFocus)
                }
            })//app更新
        }else{
            checkLogin(hasFocus)
        }

//        isUpdate = false
//        checkLogin(hasFocus)

    }

    private fun checkLogin(hasFocus: Boolean) {
        if (hasFocus && count == 0) {
            count++
            // 调用对话框部分
            val uname = SPUtils.getSharedStringData(this, "uname")
            val unpwd = SPUtils.getSharedStringData(this, "unpwd")
            val sharedBooleanData = SPUtils.getSharedBooleanData(this, "islogin")
            if (uname != null)
                edt_username_act_login.text = Editable.Factory.getInstance().newEditable(uname)//普通赋值
            if (unpwd != null)
                edt_password_act_login.text = Editable.Factory.getInstance().newEditable(unpwd)//普通赋值
            if (sharedBooleanData != null) {
                if (sharedBooleanData) {
                    val sharedBooleanData1 = SPUtils.getSharedBooleanData(this, "remember")
                    if (sharedBooleanData1 != null) {
                        cb_act_login.isChecked = sharedBooleanData1
                    }
                    if (SingleOnClickUtil.isFastClick()) {
//                        bt_login_act_login.isEnabled = false
                        val deviceId = PhoneUtil.getDeviceId(this)
                        val uname = edt_username_act_login.text.toString().trim()
                        val upwd = edt_password_act_login.text.toString().trim()
                        if (uname.isEmpty() || upwd.isEmpty()) {
                            ToastUtils.showLong("请正确填写用户或密码!")
                            bt_login_act_login.isEnabled = true
                        } else {
                            if (!isUpdate){
                                bt_login_act_login.isEnabled = false
                                mPresenter.getUserRequest(uname, upwd, deviceId)
                            }

                        }
                    }
                } else {
                    //记录以往登陆过的账号
                    val sharedStringData = SPUtils.getSharedStringData(this, "userList")
                        val arrayList = ArrayList<UserListBean>()
                        if (!sharedStringData.equals("")){
                            val gson = Gson()
                            val list:List<UserListBean> = gson.fromJson(sharedStringData, object : TypeToken<List<UserListBean>>() {}.type)
                            arrayList.addAll(list)
                        }
                        if (arrayList.size>0){
                            initPopuSelectNum(arrayList)

                        }
                }
            }
        }
    }

    override fun returnUser(user: User) {
//        UserListBean
        val sharedStringData = SPUtils.getSharedStringData(this, "userList")
        var arrayList = ArrayList<UserListBean>()
        if (!sharedStringData.equals("")){
            val gson = Gson()
            val list:List<UserListBean> = gson.fromJson(sharedStringData, object : TypeToken<List<UserListBean>>() {}.type)
            arrayList.addAll(list)
        }
        val userListBean = UserListBean()
        userListBean.setUname(edt_username_act_login.text.toString())
        userListBean.setUnpwd(edt_password_act_login.text.toString())
        userListBean.setXzqmc(user.xzqmc)
        /*if (!Gson().toJson(arrayList).contains(Gson().toJson(userListBean))){
            arrayList.add(userListBean)
        }*/

        arrayList = addUser(arrayList,userListBean)
        SPUtils.setSharedStringData(this,"userList",Gson().toJson(arrayList))
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

        SPUtils.setSharedBooleanData(this, "islogin", true)
//        bt_login_act_login.isEnabled = true
        if (cb_act_login.isChecked) {
            SPUtils.setSharedStringData(this, "uname", edt_username_act_login.text.toString())
            SPUtils.setSharedStringData(this, "unpwd", edt_password_act_login.text.toString())
            SPUtils.setSharedBooleanData(this, "remember", true)
        } else {
            SPUtils.setSharedStringData(this, "uname", "")
            SPUtils.setSharedStringData(this, "unpwd", "")
            SPUtils.setSharedBooleanData(this, "remember", false)
        }
        AppCache.getInstance().userId = user.userId
        AppCache.getInstance().code = user.code
        AppCache.getInstance().duties = user.rz
        AppCache.getInstance().cunCode = user.code
        AppCache.getInstance().xzqName = user.xzqmc
        AppCache.getInstance().name = user.username
        AppCache.getInstance().phone = user.mobile
        AppCache.getInstance().zmmc = user.typeText
        AppCache.getInstance().loginCenter = user.center
        AppCache.getInstance().xzCenter = user.center
        AppMenus.getInstance().textCenter = user.center//测试使用
        AppCache.getInstance().password = edt_password_act_login.text.toString()
        bt_login_act_login.progress = 0
//        var intent = Intent(this, CbbsjsxActivity::class.java)//跳转菜单
//        var intent = Intent(this, XmIssueActivity::class.java)//跳转菜单

//        startActivity(intent)
        startActivity(intent1)
//        finish()
        clearActivity()
    }

    fun addUser(userlist:ArrayList<UserListBean>,userListBean:UserListBean):ArrayList<UserListBean>{
        var isUpdate = false
        for (i in userlist){
            if (userListBean.getUname().equals(i.getUname())){
                isUpdate = true
                i.setXzqmc(userListBean.getXzqmc())
                i.setUnpwd(userListBean.getUnpwd())
                i.setUname(userListBean.getUname())
            }
        }
        if (!isUpdate){
            userlist.add(userListBean)
        }
        return userlist
    }

    override fun changeUser() {

    }

    override fun changeUser(msg: String) {

    }

    override fun showLoading(title: String?) {
        bt_login_act_login.progress = 50
        bt_login_act_login.loadingText = title
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        bt_login_act_login.progress = 0
        bt_login_act_login.normalText = "登录系统"
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {

        bt_login_act_login.progress = -1
        bt_login_act_login.errorText = msg
        bt_login_act_login.isEnabled = true
        if (msg.equals("'''")){
            ToastUtils.showShort("请求失败")
        }else{
            ToastUtils.showShort(msg)
        }
    }

    fun doLogin(view: View) {
        if (SingleOnClickUtil.isFastClick()){
        /*var intent = Intent(this,LdrkActivity::class.java)
            startActivity(intent)*/
        bt_login_act_login.isEnabled = false
            val deviceId = PhoneUtil.getDeviceId(this)
            val uname = edt_username_act_login.text.toString().trim()
            val upwd = edt_password_act_login.text.toString().trim()
            if (uname.isEmpty() || upwd.isEmpty()) {
                ToastUtils.showLong("请正确填写用户或密码!")
                bt_login_act_login.isEnabled = true
            } else {
                mPresenter.getUserRequest(uname, upwd, deviceId)
            }
        }
    }


}