package com.jymj.zhglxt.ui.fragment


import android.content.Intent
import android.os.CountDownTimer
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jymj.zhglxt.BuildConfig

import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.ldrkgl.personal.activity.MyNewsActivity
import com.jymj.zhglxt.login.activity.LoginActivity
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.MeContract
import com.jymj.zhglxt.login.model.MeModel
import com.jymj.zhglxt.login.presenter.MePresenter
import com.jymj.zhglxt.personal.activity.PersonalDataActivity
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.jymj.zhglxt.ui.activity.ShoppingMallWebActivity
import com.jymj.zhglxt.util.*
import com.jymj.zhglxt.xm.activity.ScListActivity
import com.jymj.zhglxt.xm.activity.XmTzDetailActivity
import com.jymj.zhglxt.zjd.activity.SelectZhActivity
import com.jymj.zhglxt.zjd.activity.UserListActivity
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.*
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_me.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class MeFragment : BaseFragment<MePresenter, MeModel>(), MeContract.View {
    private var modifyPopu: CommenPop? = null
    private var clearUpPopu: CommenPop? = null
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_me
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {//SelectZhActivity
        var versionName = BuildConfig.VERSION_NAME
        tv_act_me_bbh.setText("v ${versionName}版本")//设置版本号
        /*if (AppCache.getInstance().cunCode.contains("110111009022")){
            ll_act_me_sczq.visibility = View.VISIBLE
        }*/

        val appName = AppUtils.getAppName()
        if (appName.equals("百村监测")){
            tv_act_me_sc.visibility = View.GONE
        }
        val uname = SPUtils.getSharedStringData(activity, "uname")
        if (uname.equals("ly001")){
            ll_act_me_qhzh.visibility = View.VISIBLE
            ll_act_me_mmaq.visibility = View.GONE
        }else{
            ll_act_me_qhzh.visibility = View.GONE
            ll_act_me_mmaq.visibility = View.VISIBLE
        }
        rl_act_me_personal_data.setOnClickListener {//跳转个人资料
            if (SingleOnClickUtil1.isFastClick()){
                val intent = Intent(activity, PersonalDataActivity::class.java)
                startActivity(intent)
            }
        }
        ll_act_me_mmaq.setOnClickListener { //密码安全
            /*val appPackageName = AppUtils.getAppPackageName()
            ToastUtils.showShort(appPackageName)*/
            if (SingleOnClickUtil1.isFastClick()){
                initUpdatePop()
            }
        }
        ll_act_me_wdxx.setOnClickListener { //我的消息
            if (SingleOnClickUtil1.isFastClick()){
                val intent = Intent(activity, MyNewsActivity::class.java)
                startActivity(intent)
            }
        }
        if (!AppCache.getInstance().name.equals("cs1"))
        ll_act_me_yhlb.visibility = View.GONE
        ll_act_me_yhlb.setOnClickListener { //用户列表
            if (SingleOnClickUtil1.isFastClick()){
                val intent = Intent(activity, UserListActivity::class.java)
                startActivity(intent)
            }
        }
        tv_act_me_sc.setOnClickListener {//我的收藏
            val intent = Intent(activity, ScListActivity::class.java)
            startActivity(intent)
        }
        ll_act_me_jcgx.setOnClickListener { //检查更新
            if (SingleOnClickUtil1.isFastClick()){

                CheckUpdateUtil.checkUpdate(activity!!)
//                checkUpdate()
            }
//            ToastUtils.showShort("检查更新")
        }
        ll_act_me_qchc.setOnClickListener { //清除缓存
            if (SingleOnClickUtil1.isFastClick()){
                initclearpop()
            }
        }
        ll_act_me_qhzh.setOnClickListener {//账号切换
            if (SingleOnClickUtil1.isFastClick()){
                var intent = Intent(activity, SelectZhActivity::class.java)
                startActivity(intent)
//                activity?.finish()
            }
        }
        if (!AppMenus.getInstance().menuBeans.toString().contains("消息查看")){
            ll_act_me_wdxx.visibility = View.GONE
        }
        but_act_me_out.setOnClickListener { //登出系统
            if (SingleOnClickUtil1.isFastClick()){
                SPUtils.setSharedBooleanData(activity,"islogin",false)
                SPUtils.setSharedStringData(activity,"unpwd","")
                SPUtils.setSharedStringData(activity,"uname","")
                SPUtils.setSharedStringData(activity,"deviceId","")
//            AppManager.getAppManager().finishAllActivity()
                val intentmain = Intent(activity, LoginActivity::class.java)//.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentmain)
            }
        }
        tv_act_me_size.setText(FileUtils.getDirSize(AppMenus.getInstance().cardPath + "BMS/map/"))

        iv_act_me_shoppingmall.setOnClickListener { //商城入口
            val intent = Intent(activity,ShoppingMallWebActivity::class.java)
            startActivity(intent)

        }



    }

    override fun initDatas() {
    }

    override fun returnUser(user: User) {
    }

    override fun changeUser() {
    }

    override fun changeUser(msg: String) {
        ToastUtils.showShort(msg)
        modifyPopu?.dismiss()
    }

    override fun returnWclCount(msg: Int) {
    }
    //清理缓存
    fun initclearpop(){
        clearUpPopu = CommenPop.getNormalPopu(activity, R.layout.pop_setting_clear, activity!!.window.decorView)
        val contentView = clearUpPopu!!.contentView
        val tv_setting_clear_map = contentView.findViewById<TextView>(R.id.tv_setting_clear_map)
        val tv_setting_clear_pic = contentView.findViewById<TextView>(R.id.tv_setting_clear_pic)
        val tv_setting_clear_baobiao = contentView.findViewById<TextView>(R.id.tv_setting_clear_baobiao)
        val bt_setting_clear_video = contentView.findViewById<Button>(R.id.bt_setting_clear_video)
        val bt_setting_clear = contentView.findViewById<TextView>(R.id.bt_setting_clear)
        clearUpPopu!!.showAtLocation(contentView, Gravity.CENTER, 0, 0)
        CommenPop.backgroundAlpha(0.5f, activity)
        if (AppCache.getInstance().type==1||AppCache.getInstance().type==2||AppCache.getInstance().type==3){
            tv_setting_clear_baobiao.visibility = View.VISIBLE
        }else{
            tv_setting_clear_baobiao.visibility = View.GONE
        }
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
        tv_setting_clear_baobiao.setOnClickListener {
            clearCacheBbwj()
            clearUpPopu!!.dismiss()
        }
        bt_setting_clear.setOnClickListener {
            clearUpPopu!!.dismiss()
        }
    }

    val fileList = ArrayList<String>()
    //清除图层缓存
    fun clearCache(){
        val path= AppMenus.getInstance().cardPath+"BMS/map/"//zhglxt/
        DeleteExtractorTask(path,0,activity).execute()
        //获取缓存大小
//        val dirSize = FileUtils.getDirSize(AppMenus.getInstance().cardPath + "BMS/map/dx_bzc/")
        tv_act_me_size.setText("")
    }
    //清除视频缓存清除图片缓存
    fun clearCachePic(){
        var path= AppMenus.getInstance().cardPath+"BMS/map/pic/"
        DeleteExtractorTask(path,0,activity).execute()
        //获取缓存大小
//        val dirSize = FileUtils.getDirSize(AppMenus.getInstance().cardPath + "BMS/map/dx_bzc/")
        tv_act_me_size.setText("")
    }
    //清除报表文件缓存
    fun clearCacheBbwj(){
//        var path= AppMenus.getInstance().cardPath+"BMS/map/pic/"
        var path = AppMenus.getInstance().cardPath + "/AndroidExcelDemo/"+AppCache.getInstance().name;
        DeleteExtractorTask(path,0,activity).execute()

        //获取缓存大小
//        val dirSize = FileUtils.getDirSize(AppMenus.getInstance().cardPath + "BMS/map/dx_bzc/")
        tv_act_me_size.setText("")
    }
    //清除视频缓存
    fun clearCacheVideo(){
        var path= AppMenus.getInstance().cardPath+"BMS/map/video/"
//        fileList.clear()
//        val filse = getFilse(File(path))
        DeleteExtractorTask(path,0,activity).execute()
        /*LoadingDialog.showDialogForLoading(activity)
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
//        val dirSize = FileUtils.getDirSize(AppMenus.getInstance().cardPath + "BMS/map/dx_bzc/")
        tv_act_me_size.setText("")
    }

    public var tv_pop_update_pass_hqyzm: TextView? =null
    //修改密码弹出框
    private fun initUpdatePop(){
        if (modifyPopu==null){
            modifyPopu = CommenPop.getNormalPopu(activity, R.layout.pop_update_password, ll_act_me)//初始化修改密码的弹出框
        }
        val contentView = modifyPopu?.contentView
        val et_pop_update_pass_sjh = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_sjh)//手机号
//        val et_pop_update_pass_sryzm = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_sryzm)//验证码
        val et_pop_update_pass_newpass = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_newpass)//新密码
        val et_pop_update_pass_xmm = contentView?.findViewById<EditText>(R.id.et_pop_update_pass_xmm)//确认新密码
        tv_pop_update_pass_hqyzm = contentView?.findViewById<TextView>(R.id.tv_pop_update_pass_hqyzm)//获取验证码
        val tv_pop_password_sure = contentView?.findViewById<TextView>(R.id.tv_pop_password_sure)
        val tv_pop_password_cancel = contentView?.findViewById<TextView>(R.id.tv_pop_password_cancel)
        et_pop_update_pass_sjh!!.setText(AppCache.getInstance().name)
        tv_pop_update_pass_hqyzm?.setOnClickListener {
            if (RegexUtils.isMobileExact(et_pop_update_pass_sjh.text.toString())){
                //倒计时并发送验证码
                countDown()
            }else{
                ToastUtils.showShort("请输入正确的手机号")
            }
        }
        tv_pop_password_sure?.setOnClickListener {//确定修改
            val userName = et_pop_update_pass_sjh.text.toString()
            val newPass = et_pop_update_pass_newpass!!.text.toString()
            val qrNewPass = et_pop_update_pass_xmm!!.text.toString()
            val deviceId = PhoneUtil.getDeviceId(activity)
            if (userName.equals("")){//手机号
                ToastUtils.showShort("请输入用户名")
            }/*else if (et_pop_update_pass_sryzm!!.text.toString().equals("")){//验证码
                ToastUtils.showShort("请输入验证码")
            }*/else if (newPass.equals("")){//新密码
                ToastUtils.showShort("请输入新密码")
            }else if (!qrNewPass.equals(newPass)){//确认新密码
                ToastUtils.showShort("请输入正确的新密码")
            }else{
                mPresenter.changeUser(userName, AppCache.getInstance().password,qrNewPass,deviceId)
            }
//            modifyPopu?.dismiss()
        }
        tv_pop_password_cancel?.setOnClickListener {//取消修改
            modifyPopu?.dismiss()
        }
        CommenPop.backgroundAlpha(0.5f, activity)//设置弹出框外透明度
        modifyPopu?.showAtLocation(ll_act_me, Gravity.CENTER, 0, 0)//弹出弹出框
    }
    //倒计时计时器
    fun countDown(){
        object: CountDownTimer(60000, 1000) {
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

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

}
