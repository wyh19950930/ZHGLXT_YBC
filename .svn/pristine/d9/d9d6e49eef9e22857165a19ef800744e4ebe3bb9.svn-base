package com.jymj.zhglxt.personal.activity

import android.content.Intent
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.personal.activity.MyNewsActivity
import com.jymj.zhglxt.login.activity.LoginActivity
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.MeContract
import com.jymj.zhglxt.login.model.MeModel
import com.jymj.zhglxt.login.presenter.MePresenter
import com.jymj.zhglxt.util.*
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.*
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_me.*

class MeActivity : BaseActivity<MePresenter, MeModel>(), MeContract.View {

    private var modifyPopu:CommenPop? = null
    private var clearUpPopu: CommenPop? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_me
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {//getDirSize

        iv_act_me_back.setOnClickListener {//关闭当前页面
            finish()
        }
        rl_act_me_personal_data.setOnClickListener {//跳转个人资料
            if (SingleOnClickUtil1.isFastClick()){
                val intent = Intent(this, PersonalDataActivity::class.java)
                startActivity(intent)
            }
        }
        ll_act_me_mmaq.setOnClickListener { //密码安全
            if (SingleOnClickUtil1.isFastClick()){
                initUpdatePop()
            }
        }
        ll_act_me_wdxx.setOnClickListener { //我的消息
            if (SingleOnClickUtil1.isFastClick()){
                val intent = Intent(this, MyNewsActivity::class.java)
                startActivity(intent)
            }
        }
        ll_act_me_jcgx.setOnClickListener { //检查更新
            if (SingleOnClickUtil1.isFastClick()){

                CheckUpdateUtil.checkUpdate(this)
//                checkUpdate()
            }
//            ToastUtils.showShort("检查更新")
        }
        ll_act_me_qchc.setOnClickListener { //清除缓存
            if (SingleOnClickUtil1.isFastClick()){
                initclearpop()
            }
        }
        if (!AppMenus.getInstance().menuBeans.toString().contains("消息查看")){
            ll_act_me_wdxx.visibility = View.GONE
        }
        but_act_me_out.setOnClickListener { //登出系统
            if (SingleOnClickUtil1.isFastClick()){
                SPUtils.setSharedBooleanData(this,"islogin",false)
                SPUtils.setSharedStringData(this,"unpwd","")
                SPUtils.setSharedStringData(this,"uname","")
                SPUtils.setSharedStringData(this,"deviceId","")
//            AppManager.getAppManager().finishAllActivity()
                val intentmain = Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentmain)
            }
        }
    }
    //清理缓存
    fun initclearpop(){
        clearUpPopu = CommenPop.getNormalPopu(this, R.layout.pop_setting_clear, window.decorView)
        val contentView = clearUpPopu!!.contentView
        val tv_setting_clear_map = contentView.findViewById<TextView>(R.id.tv_setting_clear_map)
        val tv_setting_clear_pic = contentView.findViewById<TextView>(R.id.tv_setting_clear_pic)
        val bt_setting_clear_video = contentView.findViewById<Button>(R.id.bt_setting_clear_video)
        val bt_setting_clear = contentView.findViewById<TextView>(R.id.bt_setting_clear)
        clearUpPopu!!.showAtLocation(contentView, Gravity.CENTER, 0, 0)
        CommenPop.backgroundAlpha(0.5f, this)
        tv_setting_clear_map.setOnClickListener {
            clearCache()
            clearUpPopu!!.dismiss()
        }
        tv_setting_clear_pic.setOnClickListener {
            clearCachePic()
            clearUpPopu!!.dismiss()
        }
        bt_setting_clear_video.setOnClickListener {
            clearCacheVideo()
            clearUpPopu!!.dismiss()
        }
        bt_setting_clear.setOnClickListener {
            clearUpPopu!!.dismiss()
        }
    }

    val fileList = ArrayList<String>()
    //清除图层缓存
    fun clearCache(){
        val path= GetFileUtil.getSDCardPath()+"BMS/map/dx_bzc/"
        DeleteExtractorTask(path,0,this).execute()

        //获取缓存大小
        val dirSize = FileUtils.getDirSize(GetFileUtil.getSDCardPath() + "BMS/map/dx_bzc/")
        tv_act_me_size.setText("")
    }
    //清除视频缓存清除图片缓存
    fun clearCachePic(){
        var path= GetFileUtil.getSDCardPath()+"BMS/map/pic/"
        DeleteExtractorTask(path,0,this).execute()

        //获取缓存大小
        val dirSize = FileUtils.getDirSize(GetFileUtil.getSDCardPath() + "BMS/map/dx_bzc/")
        tv_act_me_size.setText("")
    }
    //清除视频缓存
    fun clearCacheVideo(){
        var path= GetFileUtil.getSDCardPath()+"BMS/map/video/"
//        fileList.clear()
//        val filse = getFilse(File(path))
        DeleteExtractorTask(path,0,this).execute()
        /*LoadingDialog.showDialogForLoading(this)
        //var path= GetFileUtil.getSDCardPath()+"BMS/map/"+ApiConstants.LOGIN_NAME
        var path= GetFileUtil.getSDCardPath()+"jymj/tzrjhj/video/"
        val isDir = FileUtils.isDir(path)
        if (isDir){
            val deleteDir = FileUtils.deleteDir(path)
            if (deleteDir){
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("清除成功！")
                clearUpPopu!!.dismiss()
            }else{
                LoadingDialog.cancelDialogForLoading()
                ToastUtils.showShort("清除失败！")
                clearUpPopu!!.dismiss()
            }
        }else{
            LoadingDialog.cancelDialogForLoading()
            ToastUtils.showShort("清除成功！")
            clearUpPopu!!.dismiss()
        }*/

        //获取缓存大小
        val dirSize = FileUtils.getDirSize(GetFileUtil.getSDCardPath() + "BMS/map/dx_bzc/")
        tv_act_me_size.setText("")
    }

    override fun initDatas() {
        mPresenter.getWclCount()
        //获取当前版本号
        tv_act_me_bbh.setText(AppUtils.getAppVersionName())
        //获取缓存大小
        val dirSize = FileUtils.getDirSize(GetFileUtil.getSDCardPath() + "BMS/map/dx_bzc/")
        tv_act_me_size.setText(dirSize)

    }

    override fun returnUser(user: User) {

    }

    override fun changeUser() {

    }

    override fun changeUser(msg: String) {
        ToastUtils.showShort("修改成功")
        SPUtils.setSharedBooleanData(this,"islogin",false)
        SPUtils.setSharedStringData(this,"unpwd","")
        SPUtils.setSharedStringData(this,"uname","")
        SPUtils.setSharedStringData(this,"deviceId","")
//            AppManager.getAppManager().finishAllActivity()
        val intentmain = Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intentmain)
    }

    override fun returnWclCount(msg: Int) {
        if (msg==0){
            tv_act_me_wcls.setText("")
        }else if (msg>99){
            tv_act_me_wcls.setText("99+")
        }else if (msg>0){
            tv_act_me_wcls.setText("$msg")
        }
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
    public var tv_pop_update_pass_hqyzm: TextView? =null
    //修改密码弹出框
    private fun initUpdatePop(){
        if (modifyPopu==null){
            modifyPopu = CommenPop.getNormalPopu(this, R.layout.pop_update_password, ll_act_me)//初始化修改密码的弹出框
        }
        val contentView = modifyPopu?.contentView
        val et_pop_update_pass_sjh = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_sjh)//手机号
        val et_pop_update_pass_sryzm = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_sryzm)//验证码
        val et_pop_update_pass_newpass = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_newpass)//新密码
        val et_pop_update_pass_xmm = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_xmm)//确认新密码
        tv_pop_update_pass_hqyzm = contentView?.findViewById<TextView>(R.id.tv_pop_update_pass_hqyzm)//获取验证码
        val tv_pop_password_sure = contentView?.findViewById<TextView>(R.id.tv_pop_password_sure)
        val tv_pop_password_cancel = contentView?.findViewById<TextView>(R.id.tv_pop_password_cancel)
        et_pop_update_pass_sjh!!.setText(AppCache.getInstance().name)
        tv_pop_update_pass_hqyzm?.setOnClickListener {
            if (RegexUtils.isMobileExact(et_pop_update_pass_sjh!!.text.toString())){
                //倒计时并发送验证码
                countDown()
            }else{
                ToastUtils.showShort("请输入正确的手机号")
            }
        }
        tv_pop_password_sure?.setOnClickListener {//确定修改
            val userName = et_pop_update_pass_sjh!!.text.toString()
            val newPass = et_pop_update_pass_newpass!!.text.toString()
            val qrNewPass = et_pop_update_pass_xmm!!.text.toString()
            val deviceId = PhoneUtil.getDeviceId(this)
            if (userName.equals("")){//手机号
               ToastUtils.showShort("请输入用户名")
            }/*else if (et_pop_update_pass_sryzm!!.text.toString().equals("")){//验证码
                ToastUtils.showShort("请输入验证码")
            }*/else if (newPass.equals("")){//新密码
                ToastUtils.showShort("请输入新密码")
            }else if (!qrNewPass.equals(newPass)){//确认新密码
                ToastUtils.showShort("请输入正确的新密码")
            }else{
                mPresenter.changeUser(userName,AppCache.getInstance().password,qrNewPass,deviceId)
            }
//            modifyPopu?.dismiss()
        }
        tv_pop_password_cancel?.setOnClickListener {//取消修改
            modifyPopu?.dismiss()
        }
        CommenPop.backgroundAlpha(0.5f, this)//设置弹出框外透明度
        modifyPopu?.showAtLocation(ll_act_me, Gravity.CENTER, 0, 0)//弹出弹出框
    }
    //倒计时计时器
    fun countDown(){
         var timer = object: CountDownTimer(60000, 1000) {
             override fun onFinish() {
                 tv_pop_update_pass_hqyzm!!.setEnabled(true)
                 tv_pop_update_pass_hqyzm!!.setText("重新获取")
             }

             override fun onTick(millisUntilFinished: Long) {
                 tv_pop_update_pass_hqyzm!!.setEnabled(false)
                 tv_pop_update_pass_hqyzm!!.setText("已发送(" + millisUntilFinished / 1000 + ")")
             }
        }.start();
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getWclCount()//获取未处理数
    }

}
