package com.jymj.zhglxt.zjd.activity

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ldrkgl.base.BaseNet
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.rjhj.bean.SysXzqFhEntity
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.jymj.zhglxt.zjd.contract.AddUserContract
import com.jymj.zhglxt.zjd.presenter.AddUserPresenter
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_add_user.*
import java.util.regex.Pattern


class AddUserActivity : BaseActivity<AddUserPresenter, AddUserContract.Model>(), AddUserContract.View {

    private var sysUserList = ArrayList<SysXzqFhEntity>()//
//    private var zhenList = ArrayList<SysXzqEntity>()//当前用户下的所有镇集合
//    private var cunList = ArrayList<SysXzqEntity>()//所选镇下的所有村
    private var isCun = 0//是否第一次请求

    private var userId = 0//用户_id
    private var type1 = 2//
    private var code1 = ""//行政区编码
    private var isAdd = true//是否添加
    private var xzqList = ArrayList<SysXzqEntity>()
    private var quList = ArrayList<SysXzqEntity>()
    private var zhenList = ArrayList<SysXzqEntity>()
    private var cunList = ArrayList<SysXzqEntity>()


    override fun getLayoutId(): Int {
        return R.layout.activity_add_user
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        isAdd = intent.getBooleanExtra("isAdd",false)
        var xzqList1 = intent.getSerializableExtra("xzqList") as List<SysXzqEntity>
        xzqList.addAll(xzqList1)
        if (AppCache.getInstance().cunCode.length==15){
            box_add_user_bc.visibility = View.GONE
            box_add_user_qu.visibility = View.GONE
            box_add_user_zhen.visibility = View.GONE
        }
        if (AppCache.getInstance().cunCode.length==12){
            box_add_user_bc.visibility = View.GONE
            box_add_user_qu.visibility = View.GONE
        }
        if (AppCache.getInstance().cunCode.length==9){
            box_add_user_bc.visibility = View.GONE
        }
        getQuList(xzqList,AppCache.getInstance().cunCode)
        if (quList.size==0){
            ll_add_user_qu.visibility =View.GONE
            getZhenList(xzqList,AppCache.getInstance().cunCode)
        }
        if (zhenList.size==0){
            ll_add_user_zhen.visibility =View.GONE
            getCunList(xzqList,AppCache.getInstance().cunCode)
        }
        if (isAdd){
            iv_delete_user.visibility = View.GONE
//            type1 = intent.getIntExtra("type",2)
            code1 = intent.getStringExtra("code").toString()


        }else{
            userId = intent.getLongExtra("id",0).toInt()
            mPresenter.getUserInfo(userId.toString())
        }
        box_add_user_bc.setOnClickListener {
            type1 = 1
            box_add_user_bc.isChecked = true
            box_add_user_qu.isChecked = false
            box_add_user_zhen.isChecked = false
            box_add_user_cun.isChecked = false
            ll_add_user_qu.visibility = View.GONE
            ll_add_user_zhen.visibility = View.GONE
            ll_add_user_cun.visibility = View.GONE
        }
        box_add_user_qu.setOnClickListener {
            type1 = 2
            box_add_user_bc.isChecked = false
            box_add_user_qu.isChecked = true
            box_add_user_zhen.isChecked = false
            box_add_user_cun.isChecked = false
//            if (AppCache.getInstance().cunCode.length<9)
            ll_add_user_qu.visibility = View.VISIBLE
            ll_add_user_zhen.visibility = View.GONE
            ll_add_user_cun.visibility = View.GONE
        }
        box_add_user_zhen.setOnClickListener {
            type1 = 3
            box_add_user_bc.isChecked = false
            box_add_user_qu.isChecked = false
            box_add_user_zhen.isChecked = true
            box_add_user_cun.isChecked = false
//            if (AppCache.getInstance().cunCode.length<12){
            if (AppCache.getInstance().type<2)
                ll_add_user_qu.visibility = View.VISIBLE
                ll_add_user_zhen.visibility = View.VISIBLE
//            }
            ll_add_user_cun.visibility = View.GONE
        }
        box_add_user_cun.setOnClickListener {
            type1 = 4
            box_add_user_bc.isChecked = false
            box_add_user_qu.isChecked = false
            box_add_user_zhen.isChecked = false
            box_add_user_cun.isChecked = true
            if (AppCache.getInstance().type<2)
                ll_add_user_qu.visibility = View.VISIBLE
            if (AppCache.getInstance().type<3)
                ll_add_user_zhen.visibility = View.VISIBLE
            ll_add_user_cun.visibility = View.VISIBLE
        }

        iv_add_user_back.setOnClickListener {
            finish()
        }
        et_add_user_save.setOnClickListener {//添加
            sysUserList.clear()
            val name = et_add_user_name.text.toString()
            val phone = et_add_user_phone.text.toString()
            val passWord = et_add_user_password.text.toString()
            val idCard = et_add_user_id_card.text.toString()
            val zhiWu = et_add_user_zw.text.toString()
            val userName = et_add_user_username.text.toString()
            if (userName.equals("")){
                ToastUtils.showShort("请输入名称")
            }/*else if (!checkPhoneNum(phone)){
                ToastUtils.showShort("请输入正确的手机号")
            }*/
            /*else if (!box_add_user_zhen.isChecked&&!box_add_user_cun.isChecked){
                ToastUtils.showShort("请选择类型")
            }*//*else if (sysUserList.size==0){
                ToastUtils.showShort("请选择行政区")
            }*/else{
                val sysUserEntity = SysUserEntity()
                if (userName.equals("")){
                    sysUserEntity.username= phone
                }else{
                    sysUserEntity.username= userName
                }
                if (rb_add_user_sfld_shi.isChecked){
                    sysUserEntity.rz = 1
                }else if (rb_add_user_dcy.isChecked){
                    sysUserEntity.rz = 2
                }else{
                    sysUserEntity.rz = 0
                }
               /* if (rb_add_user_sfld_fou.isChecked){

                }*/
                if (rb_add_user_sfzc_shi.isChecked){//1  正常
                    sysUserEntity.status = 1
                }else{
                    sysUserEntity.status = 0
                }
                /*if (rb_add_user_sfld_fou.isChecked){
                    sysUserEntity.duties = 0
                }*/

                sysUserEntity.mobile=phone
                sysUserEntity.name =name
                sysUserEntity.idcard = idCard
                sysUserEntity.position = zhiWu
                /*if (passWord.equals("")){
                    sysUserEntity.password ="cs123"
                }else{*/
                    sysUserEntity.password =passWord
//                }
                if (box_add_user_bc.isChecked){
                    sysUserEntity.type=1
                    val sysXzqFhEntity = SysXzqFhEntity()
                    sysXzqFhEntity.xzqId = xzqList.get(0).xzqId.toLong()
                    sysUserList.add(sysXzqFhEntity)
                    sysUserEntity.xzqmc = xzqList.get(0).name
                    sysUserEntity.code = xzqList.get(0).code

                    setSysXzqFhEntity(xzqList)//给 sysUserList 赋值
                    sysUserEntity.xzqs=sysUserList
                    val arrayList = ArrayList<Long>()
                    for (i in sysUserList){
                        arrayList.add(i.xzqId)
                    }
                    sysUserEntity.xzqIds = arrayList
                }
                if (box_add_user_qu.isChecked){
                    sysUserEntity.type=2
                    for (i in quList){
                        if (i.isCheck){
                            val sysXzqFhEntity = SysXzqFhEntity()
                            sysXzqFhEntity.xzqId = i.xzqId.toLong()
                            sysUserList.add(sysXzqFhEntity)
                            setSysXzqFhEntity(i.children)//给 sysUserList 赋值
                            sysUserEntity.xzqmc = i.name
                            sysUserEntity.code = i.code
                        }
                    }

                    sysUserEntity.xzqs=sysUserList
                    val arrayList = ArrayList<Long>()
                    for (i in sysUserList){
                        arrayList.add(i.xzqId)
                    }
                    sysUserEntity.xzqIds = arrayList
                }
                if (box_add_user_zhen.isChecked){
                    sysUserEntity.type=3
                    for (i in zhenList){
                        if (i.isCheck){
                            val sysXzqFhEntity = SysXzqFhEntity()
                            sysXzqFhEntity.xzqId = i.xzqId.toLong()
                            sysUserList.add(sysXzqFhEntity)
                            setSysXzqFhEntity(i.children)//给 sysUserList 赋值
                            sysUserEntity.xzqmc = i.name
                            sysUserEntity.code = i.code
                        }
                    }
                    sysUserEntity.xzqs=sysUserList
                    val arrayList = ArrayList<Long>()
                    for (i in sysUserList){
                        arrayList.add(i.xzqId)
                    }
                    sysUserEntity.xzqIds = arrayList
                }
                if (box_add_user_cun.isChecked){
                    sysUserEntity.type=4
                    for (i in cunList){
                        if (i.isCheck){
                            val sysXzqFhEntity = SysXzqFhEntity()
                            sysXzqFhEntity.xzqId = i.xzqId.toLong()
                            sysUserList.add(sysXzqFhEntity)
                            sysUserEntity.xzqmc = i.name
                            sysUserEntity.code = i.code
                        }
                    }
                    sysUserEntity.xzqs=sysUserList
                    val arrayList = ArrayList<Long>()
                    for (i in sysUserList){
                        arrayList.add(i.xzqId)
                    }
                    sysUserEntity.xzqIds = arrayList
                }
                /*if (box_add_user_wy.isChecked){
                    sysUserEntity.type=4
                    sysUserEntity.xzqmc = "通州区"
                    sysUserEntity.code = "110112"
                }
                if (box_add_user_ny.isChecked){
                    sysUserEntity.type=1
                    sysUserEntity.xzqmc = "通州区"
                    sysUserEntity.code = "110112"
                }*/

                if (isAdd){
                    mPresenter.getAddUser(sysUserEntity)
                }else{
                    sysUserEntity.userId = userId.toLong()
                    mPresenter.getAddUser(sysUserEntity)
                }

                //type
            }
        }
        iv_delete_user.setOnClickListener {
            var dialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            dialog?.titleText = "是否删除？"
            dialog?.confirmText = "确认"
            dialog?.cancelText = "取消"
            dialog?.showCancelButton(true)
            dialog?.showContentText(false)
            dialog?.show()
            dialog?.setConfirmClickListener {
                val encrypt = "{\"userIds\":" + userId + "}"
//                val encrypt = "[\"" + userId + "\"]"
//                    var sss = "{\"requestData\":\"" + encrypt + "\"}"
                /*val httpParams = HttpParams()
                httpParams.put("userIds","[\" + userId + \"]")*/
                OkGo.post<String>(ApiConstants.SYS_USER_DELETE).upJson(encrypt).execute(object ://删除用户
                        BaseNet<String>() {
                    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                        super.onStart(request)
                        LoadingDialog.showDialogForLoading(this@AddUserActivity)
                    }
                    override fun onSuccess(response: Response<String>?) {
                        val cash = response?.body()
                        if (cash != null) {
//                                val decrypt = AesEncryptUtils.decrypt(cash)
                            val json: BaseRespose<*> = Gson().fromJson(cash, object : TypeToken<BaseRespose<*>?>() {}.type)
                            if (json.code == 0) {
                                ToastUtils.showShort("删除成功")
                                dialog.dismiss()
                                finish()
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
                        dialog.dismiss()
                    }
                })
            }
        }
        et_add_user_qu.setOnClickListener {
            hintKeyBoard()
            if (SingleOnClickUtil.isFastClick()){
            var zhenStringList = ArrayList<String>()
            for (i in quList){
                zhenStringList.add(i.name)
            }
            //条件选择器
            val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
                /*//返回的分别是三个级别的选中位置
                val tx: String = (options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText())
                tvOptions.setText(tx)*/
//                zhenList.get(options1).isCheck=true
                setCheck(quList,options1)
                et_add_user_qu.setText(quList.get(options1).name)
//                getXzqList(zhenList.get(options1).code,3);
                getZhenList(xzqList,quList.get(options1).code)
            }).build<String>()
            pvOptions.setSelectOptions(zhenStringList.indexOf(et_add_user_qu.text.toString()))
            pvOptions.setPicker(zhenStringList)
            pvOptions.show()
            }
        }
        et_add_user_zhen.setOnClickListener {
            hintKeyBoard()
            if (SingleOnClickUtil.isFastClick()){
            var zhenStringList = ArrayList<String>()
            for (i in zhenList){
                zhenStringList.add(i.name)
            }
            //条件选择器
            val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
                /*//返回的分别是三个级别的选中位置
                val tx: String = (options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText())
                tvOptions.setText(tx)*/
//                zhenList.get(options1).isCheck=true
                setCheck(zhenList,options1)
                et_add_user_zhen.setText(zhenList.get(options1).name)
//                getXzqList(zhenList.get(options1).code,3);
                getCunList(xzqList,zhenList.get(options1).code)
            }).build<String>()
            pvOptions.setSelectOptions(zhenStringList.indexOf(et_add_user_zhen.text.toString()))
            pvOptions.setPicker(zhenStringList)
            pvOptions.show()
            }
        }
        et_add_user_cun.setOnClickListener {
            hintKeyBoard()
            if (SingleOnClickUtil.isFastClick()) {
                var cunStringList = ArrayList<String>()
                for (i in cunList) {
                    cunStringList.add(i.name)
                }
                val pvOptions: OptionsPickerView<String> = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
                    /*//返回的分别是三个级别的选中位置
                val tx: String = (options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText())
                tvOptions.setText(tx)*/
//                cunList.get(options1).isCheck=true
                    setCheck(cunList, options1)
                    et_add_user_cun.setText(cunList.get(options1).name)
                }).build<String>()
                pvOptions.setSelectOptions(cunStringList.indexOf(et_add_user_cun.text.toString()))
                pvOptions.setPicker(cunStringList)
                pvOptions.show()
            }
        }

    }
    private fun setSysXzqFhEntity(xzList:List<SysXzqEntity>){
        for (i in xzList){
            val sysXzqFhEntity = SysXzqFhEntity()
            sysXzqFhEntity.xzqId = i.xzqId.toLong()
            sysUserList.add(sysXzqFhEntity)
            if (i.children.size>0){
                setSysXzqFhEntity(i.children)
            }
        }
    }

    public fun hintKeyBoard() {
        //拿到InputMethodManager
        var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //如果window上view获取焦点 && view不为空
        if(imm.isActive()&&getCurrentFocus()!=null){
            //拿到view的token 不为空
            if (getCurrentFocus()?.getWindowToken() !=null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(getCurrentFocus()?.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    fun setCheck(list:ArrayList<SysXzqEntity>,position:Int){
        for (i in 0..list.size-1){
            if (i==position){
                list.get(i).isCheck=true
            }else{
                list.get(i).isCheck=false
            }
        }
    }
    /***
     * 手机号码检测
     */
    fun checkPhoneNum(num: String): Boolean{
        if (num.equals("")){
            return false
        }else{
            val regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"
            val p = Pattern.compile(regExp)
            val m = p.matcher(num)
            return m.matches()
        }

    }

    override fun initDatas() {

    }

    override fun returnAddUser(message: String) {
        ToastUtils.showShort(message)
        finish()
    }

    override fun returnUpdateUser(message: String) {
        ToastUtils.showShort(message)
        finish()
    }

    private fun getQuList(xzq:List<SysXzqEntity>,code: String) {//区9   镇12  村15
        quList.clear()
        for (i in xzq){
            /*if (i.code.equals(code)){
                i.isCheck = true
            }else{
                i.isCheck = false
            }*/
            if (i.code.length==9&&i.code.contains(code)){
                quList.add(i)
                /*if (code.length==9){
                    if (i.code.equals(code)){
                        i.isCheck = true
                        et_add_user_qu.setText(i.name)
                        getZhenList(xzq,i.code)
                    }else{
                        i.isCheck = false
                    }
                }else{*/
                    if (quList.size==1){
                        i.isCheck = true
                        et_add_user_qu.setText(quList.get(0).name)
                        getZhenList(xzq,quList.get(0).code)
                    }else{
                        i.isCheck = false
                    }
//                }

            }else{
                if (i.children.size>0){
                    getQuList(i.children,code)
                }
            }
        }
    }
    private fun getZhenList(xzq:List<SysXzqEntity>,code: String) {//区9   镇12  村15
        zhenList.clear()
        for (i in xzq){
            /*if (i.code.equals(code)){
                i.isCheck = true
            }else{
                i.isCheck = false
            }*/
            if (i.code.length==12&&i.code.contains(code)){
                zhenList.add(i)
                /*if (code.length==12){
                    if (i.code.equals(code)){
                        et_add_user_zhen.setText(i.name)
                        getCunList(xzq,i.code)
                        i.isCheck = true
                    }else{
                        i.isCheck = false
                    }
                }else{*/
                    if (zhenList.size==1){
                        et_add_user_zhen.setText(zhenList.get(0).name)
                        getCunList(xzq,zhenList.get(0).code)
                        i.isCheck = true
                    }else{
                        i.isCheck = false
                    }
//                }

            }else{
                if (i.children.size>0&&zhenList.size==0){
                    getZhenList(i.children,code)
                }
            }
        }
    }
    private fun getCunList(xzq:List<SysXzqEntity>,code: String) {//区9   镇12  村15
        cunList.clear()
        for (i in xzq){
            /*if (i.code.equals(code)){
                i.isCheck = true
            }else{
                i.isCheck = false
            }*/
            if (i.code.length==15&&i.code.contains(code)){
                cunList.add(i)
                /*if (code.length==15){
                    if (i.code.equals(code)){
                        et_add_user_cun.setText(i.name)
                        i.isCheck = true
                    }else{
                        i.isCheck = false
                    }
                }else{*/
                    if (cunList.size==1){
                        et_add_user_cun.setText(cunList.get(0).name)
                        i.isCheck = true
                    }else{
                        i.isCheck = false
                    }
//                }

            }else{
                if (i.children.size>0&&cunList.size==0){
                    getCunList(i.children,code)
                }
            }
        }
    }


    override fun returnUserInfo(message: SysUserEntity) {
        if (message!=null){
            code1 = message.code
            if (message.rz == 1){
                rb_add_user_sfld_shi.isChecked = true
            }else if (message.rz == 2){
                rb_add_user_dcy.isChecked = true
            }else{
                rb_add_user_sfld_fou.isChecked = true
            }
            if (message.status == 1){
                rb_add_user_sfzc_shi.isChecked = true
            }else{
                rb_add_user_sfzc_fou.isChecked = true
            }
            et_add_user_name.setText(message.name)        //设置用户名称
            et_add_user_phone.setText(message.mobile)     //设置手机号
            et_add_user_password.setText("")//设置密码
            et_add_user_zw.setText(message.position)//设置职务名称
            et_add_user_id_card.setText(message.idcard)   //设置身份证号
            et_add_user_username.setText(message.username)//设置用户昵称
            val userType = message.type
            if (AppCache.getInstance().type==userType){//相同等级的用户隐藏删除按钮
                iv_delete_user.visibility = View.GONE
            }
            if (message.type==1){
                box_add_user_bc.isChecked = true
                box_add_user_qu.isChecked = false
                box_add_user_zhen.isChecked = false
                box_add_user_cun.isChecked = false
                ll_add_user_qu.visibility = View.GONE
                ll_add_user_zhen.visibility = View.GONE
                ll_add_user_cun.visibility = View.GONE
                et_add_user_qu.setText(message.xzqmc)
//                setCheck(xzqList,position:Int)
            }
            if (message.type==2){
                box_add_user_bc.isChecked = false
                box_add_user_qu.isChecked = true
                box_add_user_zhen.isChecked = false
                box_add_user_cun.isChecked = false
                ll_add_user_qu.visibility = View.VISIBLE
                ll_add_user_zhen.visibility = View.GONE
                ll_add_user_cun.visibility = View.GONE
//                getQuList(xzqList,message.code.substring(0,message.code.length-3))
                for (i in quList){
                    if (i.code.equals(message.code)){
                        i.isCheck = true
                        et_add_user_qu.setText(i.name)
                        getZhenList(xzqList,i.code)
                    }else{
                        i.isCheck = false
                    }
                }
            }
            if (message.type==3){
                box_add_user_bc.isChecked = false
                box_add_user_qu.isChecked = false
                box_add_user_zhen.isChecked = true
                box_add_user_cun.isChecked = false
                if (AppCache.getInstance().type<2)
                ll_add_user_qu.visibility = View.VISIBLE
                ll_add_user_zhen.visibility = View.VISIBLE
                ll_add_user_cun.visibility = View.GONE
//                getZhenList(xzqList,message.code.substring(0,message.code.length-3))
                for (i in quList){
                    if (i.code.equals(message.code.substring(0,message.code.length-3))){
                        i.isCheck = true
                        et_add_user_qu.setText(i.name)
                        getZhenList(xzqList,i.code)
                    }else{
                        i.isCheck = false
                    }
                }
                for (i in zhenList){
                    if (i.code.equals(message.code)){
                        i.isCheck = true
                        et_add_user_zhen.setText(i.name)
                        getCunList(xzqList,i.code)
                    }else{
                        i.isCheck = false
                    }
                }
//                getZhenList(xzqList,message.code)
            }
            if (message.type==4){
                box_add_user_bc.isChecked = false
                box_add_user_qu.isChecked = false
                box_add_user_zhen.isChecked = false
                box_add_user_cun.isChecked = true
                if (AppCache.getInstance().type<2)
                ll_add_user_qu.visibility = View.VISIBLE
                if (AppCache.getInstance().type<3)
                ll_add_user_zhen.visibility = View.VISIBLE
                ll_add_user_cun.visibility = View.VISIBLE
//                getCunList(xzqList,message.code.substring(0,message.code.length-3))
                for (i in quList){
                    if (i.code.equals(message.code.substring(0,message.code.length-6))){
                        i.isCheck = true
                        et_add_user_qu.setText(i.name)
                        getZhenList(xzqList,i.code)
                    }else{
                        i.isCheck = false
                    }
                }
                for (i in zhenList){
                    if (i.code.equals(message.code.substring(0,message.code.length-3))){
                        i.isCheck = true
                        et_add_user_zhen.setText(i.name)
                        getCunList(xzqList,i.code)
                    }else{
                        i.isCheck = false
                    }
                }
                for (i in cunList){
                    if (i.code.equals(message.code)){
                        i.isCheck = true
                        et_add_user_cun.setText(i.name)
                    }else{
                        i.isCheck = false
                    }
                }
//                getCunList(xzqList,message.code)
            }


            /*if (userType==1){
                box_add_user_ny.visibility = View.VISIBLE
                box_add_user_zhen.visibility = View.VISIBLE
                box_add_user_cun.visibility = View.VISIBLE
                box_add_user_wy.visibility = View.VISIBLE
                *//*box_add_user_ny.visibility = View.VISIBLE
                box_add_user_zhen.visibility = View.GONE
                box_add_user_cun.visibility = View.GONE
                box_add_user_wy.visibility = View.GONE*//*
                iv_delete_user.visibility = View.GONE//隐藏删除按钮
                box_add_user_ny.visibility = View.GONE//隐藏内业选择
                box_add_user_zhen.visibility = View.GONE//隐藏镇选择
                box_add_user_cun.visibility = View.GONE//隐藏村选择
                box_add_user_wy.visibility = View.GONE//隐藏外业选择
                box_add_user_lx.visibility = View.VISIBLE//显示类型只有内业和外业显示
                box_add_user_lx.setText("内业")
                et_add_user_save.visibility = View.GONE//隐藏添加按钮

                ll_add_user_zhen.visibility = View.GONE//隐藏镇选择
                ll_add_user_cun.visibility = View.GONE//隐藏村选择
                box_add_user_ny.isChecked = true//选择内业
                box_add_user_zhen.isChecked = false
                box_add_user_cun.isChecked = false
                box_add_user_wy.isChecked = false
            }else if (userType==2){
                box_add_user_ny.visibility = View.GONE
                box_add_user_zhen.visibility = View.VISIBLE
                box_add_user_cun.visibility = View.VISIBLE
                box_add_user_wy.visibility = View.GONE


                ll_add_user_zhen.visibility = View.VISIBLE//显示镇选择
                ll_add_user_cun.visibility = View.GONE//显示隐藏选择
                box_add_user_ny.isChecked = false
                box_add_user_zhen.isChecked = true//选择镇
                box_add_user_cun.isChecked = false
                box_add_user_wy.isChecked = false
                if (AppCache.getInstance().type==2){
                    iv_delete_user.visibility = View.GONE//隐藏添加按钮
                    et_add_user_save.visibility = View.GONE//隐藏添加按钮
                }
            }else if (userType==3){
                box_add_user_ny.visibility = View.GONE
                box_add_user_zhen.visibility = View.VISIBLE
                box_add_user_cun.visibility = View.VISIBLE
                box_add_user_wy.visibility = View.GONE


                ll_add_user_zhen.visibility = View.VISIBLE//显示镇选择
                ll_add_user_cun.visibility = View.VISIBLE//显示村选择
                box_add_user_ny.isChecked = false
                box_add_user_zhen.isChecked = false
                box_add_user_cun.isChecked = true//选择村
                box_add_user_wy.isChecked = false

                if (AppCache.getInstance().type==2){
                    box_add_user_zhen.visibility = View.GONE
                }
            }else if (userType==4){
                box_add_user_ny.visibility = View.GONE
                box_add_user_zhen.visibility = View.VISIBLE
                box_add_user_cun.visibility = View.VISIBLE
                box_add_user_wy.visibility = View.VISIBLE

                iv_delete_user.visibility = View.GONE//隐藏删除按钮
                */
            /*box_add_user_ny.visibility = View.GONE//隐藏内业选择
                box_add_user_zhen.visibility = View.GONE//隐藏镇选择
                box_add_user_cun.visibility = View.GONE//隐藏村选择
                box_add_user_wy.visibility = View.GONE//隐藏外业选择
                box_add_user_lx.visibility = View.VISIBLE//显示类型只有内业和外业显示
                box_add_user_lx.setText("外业")*//*

                ll_add_user_zhen.visibility = View.GONE//隐藏镇选择
                ll_add_user_cun.visibility = View.GONE//隐藏村选择
                box_add_user_ny.isChecked = false
                box_add_user_zhen.isChecked = false
                box_add_user_cun.isChecked = false
                box_add_user_wy.isChecked = true//选择外业
//                getXzqList("",2)//substring
                getZhenList(xzqList,"")
            }*/
            /*if (userType!=4 && userType!=1){
                type1 = message.type
                if (code1.length==6){
//                    getXzqList("",2)
                    getZhenList(xzqList,"")
                    box_add_user_ny.isChecked = false
                    box_add_user_zhen.isChecked = true
                    box_add_user_cun.isChecked = false
                    box_add_user_wy.isChecked = false
                    ll_add_user_zhen.visibility = View.GONE//显示镇选择
                    ll_add_user_cun.visibility = View.GONE//隐藏村选择
                }else if (code1.length==9){
                    getXzqList("",2)//code1
                    getXzqList(code1,3)
                    box_add_user_ny.isChecked = false
                    box_add_user_zhen.isChecked = true
                    box_add_user_cun.isChecked = false//选择村
                    box_add_user_wy.isChecked = false
                    ll_add_user_zhen.visibility = View.VISIBLE//显示镇选择
                    ll_add_user_cun.visibility = View.GONE//隐藏村选择
                }else if (code1.length==12){
                    val substring = code1.substring(0, 9)
                    getXzqList("",2)//substring
                    getXzqList(substring,3)
                    box_add_user_ny.isChecked = false
                    box_add_user_zhen.isChecked = false
                    box_add_user_cun.isChecked = true//选择村
                    box_add_user_wy.isChecked = false
                    ll_add_user_zhen.visibility = View.VISIBLE//显示镇选择
                    ll_add_user_cun.visibility = View.VISIBLE//显示村选择
                }
            }*/

            /*if (AppCache.getInstance().type==1&&userType!=1){//box_add_user_zhen
                box_add_user_wy.visibility = View.VISIBLE
            }*/
            /*if (AppCache.getInstance().duties == 1){
                iv_delete_user.visibility = View.GONE
                et_add_user_save.visibility = View.GONE
            }*/

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
    fun getXzqList(code:String,type:Int){
        val httpParams1 = HttpParams()
        httpParams1.put("code",code)
        httpParams1.put("type",type)
        /*OkGo.post<BaseRespose<java.util.ArrayList<SysXzqEntity>>>(ApiConstants.SYS_XZQ_QUERY_XZQ_LIST).params(httpParams1).execute(object : BaseNet<BaseRespose<java.util.ArrayList<SysXzqEntity>>>(){
            override fun onStart(request: Request<BaseRespose<java.util.ArrayList<SysXzqEntity>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                LoadingDialog.showDialogForLoading(this@AddUserActivity)
            }
            override fun onSuccess(response: Response<BaseRespose<java.util.ArrayList<SysXzqEntity>>>?) {
                val body = response!!.body()
                if (response!=null){
                    if (response.body()!=null){
                        if (body.code==0 ){
                            if (body.data!=null){
                                if (type==2){
                                    zhenList=body.data
                                    et_add_user_zhen.setText(body.data.get(0).name)

                                    if (code1.length==6){
                                        if (isCun==0){
                                            getXzqList(zhenList.get(0).code,3)
                                            isCun = 1
                                        }
                                    }else{
                                        if (isCun==0){
                                            val substring = code1.substring(0, 9)
                                            getXzqList(substring,3)
                                            isCun = 1
                                        }
                                    }
                                    val name = getName(zhenList, code1)
                                    if (!name.equals("")){
                                        et_add_user_zhen.setText(name)
                                    }else{
                                        zhenList.get(0).isCheck = true
                                    }
                                }
                                if (type==3){
                                    cunList.clear()
                                    cunList=body.data
                                    et_add_user_cun.setText(body.data.get(0).name)

                                    val name = getName(cunList, code1)
                                    if (!name.equals("")){
                                        et_add_user_cun.setText(name)
                                    }else{
                                        cunList.get(0).isCheck = true
                                    }
                                }
                            }
                        }else{
                            ToastUtils.showShort(body.getMsg())
                        }
                    }else{
                        ToastUtils.showShort("查询失败")
                    }
                }else{
                    ToastUtils.showShort("查询失败")
                }
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onFinish() {
                super.onFinish()
                LoadingDialog.cancelDialogForLoading()
            }

            override fun onError(response: Response<BaseRespose<java.util.ArrayList<SysXzqEntity>>>?) {
                super.onError(response)
                if (response!=null){
                    if (response.exception.message!=null){
                        if (!response.exception.message.equals(""))
                            ToastUtils.showShort(response.exception.message)
                        else
                            ToastUtils.showShort("查询失败")
                    }else{
                        ToastUtils.showShort("查询失败")
                    }

                }
//                mView.showErrorTip("网络错误")
            }

        })
    */
    }
    private fun getName(ses:List<SysXzqEntity>,code:String):String{
        var name = ""
        for (i in ses){
            i.isCheck = false
            if (code.contains(i.code)){
                i.isCheck = true
                name = i.name
            }
        }
        return name
    }


}
