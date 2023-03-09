package com.jymj.zhglxt.zjd.activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.SysUserEntity
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.jymj.zhglxt.zjd.contract.UserListContract
import com.jymj.zhglxt.zjd.presenter.UserListPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_user_list.*
import java.util.*
import kotlin.collections.ArrayList

class UserListActivity : BaseActivity<UserListPresenter, UserListContract.Model>(), UserListContract.View {

    private var userName = ""
    private var code = ""
    private var type1 = 2
    private var type2 = 1
    val arrayList = ArrayList<SysXzqEntity>()
    var xzqAdapter: BaseQuickAdapter<SysXzqEntity, BaseViewHolder>? = null
    var xzqList = ArrayList<SysXzqEntity>()//全部行政区

    override fun getLayoutId(): Int {
        return R.layout.activity_user_list
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        type2 = AppCache.getInstance().type
        iv_user_list_back.setOnClickListener {
            finish()
        }
        ll_user_list_add.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()){
//                ToastUtils.showShort("添加")
                val intent = Intent(this, AddUserActivity::class.java)
                intent.putExtra("id",0)
                intent.putExtra("isAdd",true)
                intent.putExtra("code",code)
                intent.putExtra("name","")
                intent.putExtra("userName","")
                intent.putExtra("iphone","")
                intent.putExtra("password","cs123")
                intent.putExtra("idcord","")
                intent.putExtra("type",2)//type2
                intent.putExtra("xzqList",xzqList)//type2
                startActivity(intent)
            }
        }
//        mPresenter.getUserList(userName)
        /*if (AppCache.getInstance().type==1||
                AppCache.getInstance().type==5){
            tv_user_list_qu.visibility = View.VISIBLE
            tv_user_list_qu.setText(AppCache.getInstance().xzqmc)
            code = "110112"
            type1 = 2
            mPresenter.getUserQueryRy(code,userName)
            mPresenter.getXzqZhen("",type1)
        }else if(AppCache.getInstance().type==2){
            tv_user_list_zhen.visibility = View.VISIBLE
            tv_user_list_zhen.setText(AppCache.getInstance().xzqmc)
            code = AppCache.getInstance().cunCode
            type1 = 3
            mPresenter.getUserQueryRy(code,userName)
            mPresenter.getXzqZhen(code,type1)
        }else if(AppCache.getInstance().type==3){
            tv_user_list_cun.visibility = View.VISIBLE
            tv_user_list_cun.setText(AppCache.getInstance().xzqmc)
            code = AppCache.getInstance().cunCode
            mPresenter.getUserQueryRy(code,userName)
//            mPresenter.getXzqZhen(AppCache.getInstance().cunCode,3)
        }*/
        code = AppCache.getInstance().cunCode
        mPresenter.getXzqZhen(code,type1)
        rlv_act_user_list_xzq.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val sysXzqEntity = SysXzqEntity()
        sysXzqEntity.name = AppCache.getInstance().xzqName
        sysXzqEntity.code = AppCache.getInstance().cunCode
        arrayList.add(sysXzqEntity)
        xzqAdapter = object : BaseQuickAdapter<SysXzqEntity, BaseViewHolder>(R.layout.item_text_list_xzq, arrayList) {
            override fun convert(helper: BaseViewHolder?, item: SysXzqEntity?) {
                helper!!.setText(R.id.tv_item_text_list_xzqmc, item!!.name)
                val tvItemTextListJian = helper.getView<TextView>(R.id.tv_item_text_list_jian)
                if (helper.adapterPosition == arrayList.size - 1) {
                    tvItemTextListJian.visibility = View.GONE
                }else{
                    tvItemTextListJian.visibility = View.VISIBLE
                }
                helper.itemView.setOnClickListener {
                    if (SingleOnClickUtil1.isFastClick()){
                        code = item.code
//                    arrayList.add(item)
                        removeList(arrayList,helper.adapterPosition)
                        et_act_user_list_search.setText("")
                        mPresenter.getXzqZhen(code,type1)
                        xzqAdapter!!.notifyDataSetChanged()
                    }

                }

            }
        }
        rlv_act_user_list_xzq.adapter = xzqAdapter

        /*tv_user_list_qu.setOnClickListener {
            code = "110112"
            type1 = 2
            et_act_user_list_search.setText("")
            mPresenter.getXzqZhen(code,type1)
            tv_user_list_zhen.visibility = View.GONE
            tv_user_list_cun.visibility = View.GONE
            tv_user_list_qujian.visibility = View.GONE
            tv_user_list_zhenjian.visibility = View.GONE

           */
        /* mPresenter.getUserQueryRy(code,userName)
            mPresenter.getXzqZhen(code,type1)*/
        /*
        }

        tv_user_list_zhen.setOnClickListener {
            code = code.substring(0,9)
            type1 = 3
            et_act_user_list_search.setText("")
            mPresenter.getXzqZhen(code,type1)
            tv_user_list_zhenjian.visibility = View.GONE
            tv_user_list_cun.visibility = View.GONE
            *//*mPresenter.getUserQueryRy(code,userName)
            mPresenter.getXzqZhen(code,type1)*/
        /*
        }
        tv_user_list_cun.setOnClickListener {
            *//*code = code.substring(0,9)
            type1 = 3*//*
            et_act_user_list_search.setText("")
            *//*mPresenter.getUserQueryRy(code,userName)
            mPresenter.getXzqZhen(code,type1)*//*
        }*/

        et_act_user_list_search.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                userName = et_act_user_list_search.text.toString()
                if (!userName.equals("")){
                    rlv_user_list_xzq.visibility = View.GONE
                    mPresenter.getUserQueryRy(code,userName)

                }else{
                    if (code.length==15){
                        rlv_user_list_xzq.visibility = View.GONE
                    }else{
                        rlv_user_list_xzq.visibility = View.VISIBLE
                    }

                    mPresenter.getUserQueryRy(code,userName)
                }
//                mPresenter.getUserList(userName)

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        iv_user_list_up.setOnClickListener {
            scl_user_list.smoothScrollTo(0,rl_user_list_xzq.scrollY)
        }
        iv_user_list_down.setOnClickListener {
            scl_user_list.smoothScrollTo(0,rl_user_list.y.toInt())//
        }


    }

    private fun removeList(sysXzqEntitys:ArrayList<SysXzqEntity>,position:Int){
        for (i in sysXzqEntitys.indices){
            if (i>position){
                sysXzqEntitys.removeAt(position+1)
            }
        }
    }

    override fun initDatas() {
    }

    override fun onBackPressed() {
        if (arrayList.size>1){
            code = arrayList.get(arrayList.size-2).code
//                    arrayList.add(item)
            removeList(arrayList,arrayList.size-2)
            xzqAdapter!!.notifyDataSetChanged()
            mPresenter.getXzqZhen(code,type1)
        }else{
            super.onBackPressed()
        }

        /*if (code.length>9){
            code = code.substring(0,9)
            type1 = 3
            et_act_user_list_search.setText("")
            mPresenter.getXzqZhen(code,type1)
            tv_user_list_zhenjian.visibility = View.GONE
            tv_user_list_cun.visibility = View.GONE
        }else if (code.length>6){
            code = "110112"
            type1 = 2
            et_act_user_list_search.setText("")
            mPresenter.getXzqZhen(code,type1)
            tv_user_list_zhen.visibility = View.GONE
            tv_user_list_cun.visibility = View.GONE
            tv_user_list_qujian.visibility = View.GONE
            tv_user_list_zhenjian.visibility = View.GONE
        }else{
            super.onBackPressed()
        }*/
    }


    override fun returnUserList(message: List<SysUserEntity>) {
        rlv_user_list.layoutManager = object : LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rlv_user_list.adapter = object:BaseQuickAdapter<SysUserEntity,BaseViewHolder>(R.layout.item_user_list,message){
            override fun convert(helper: BaseViewHolder?, item: SysUserEntity?) {
                /*var nw=""
                if (item!!.type==1){
                    nw = "(内业)"
                }else if (item!!.type==4){
                    nw = "(外业)"
                }else{
                    nw = ""
                }*/
                helper!!.setText(R.id.tv_item_user_list_name, item!!.name)
                        .setText(R.id.tv_item_user_list_phone, item!!.username)//+nw
                helper.itemView.setOnClickListener {
//                    ToastUtils.showShort("修改")
                    if (SingleOnClickUtil1.isFastClick()){
                        val intent = Intent(this@UserListActivity, AddUserActivity::class.java)
//                        type2 = item.type
//                        code = item.code
                        intent.putExtra("id",item.userId)
                        intent.putExtra("isAdd",false)
                        intent.putExtra("code",item.code)
                        intent.putExtra("name",item.name)
                        intent.putExtra("userName",item.username)
                        intent.putExtra("iphone",item.mobile)
                        intent.putExtra("password","")
                        intent.putExtra("idcord",item.idcard)
                        intent.putExtra("type",item.type)
                        intent.putExtra("xzqList",xzqList)//type2
                        startActivity(intent)
                    }
                }

            }
        }

    }
    override fun returnXzqZhen(message: List<SysXzqEntity>, type:Int) {
        if (xzqList.size==0){
            xzqList.addAll(message)
        }
        /*syslist.addAll(message)
        syslist1.addAll(message)*/
//        ToastUtils.showShort(Gson().toJson(message))
        rlv_user_list_xzq.layoutManager = object :LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        rlv_user_list_xzq.adapter = object:BaseQuickAdapter<SysXzqEntity,BaseViewHolder>(R.layout.item_xzq_list,message.get(0).children) {//
            override fun convert(helper: BaseViewHolder?, item: SysXzqEntity?) {
                helper!!.setText(R.id.tv_item_xzq_qzc, item!!.name)
                helper.itemView.setOnClickListener {
                    if (SingleOnClickUtil1.isFastClick()&&code.length!=item.code.length){
                        code = item.code
                        arrayList.add(item)
                        mPresenter.getUserQueryRy(code,userName)
                        xzqAdapter!!.notifyDataSetChanged()
                        mPresenter.getXzqZhen(code,type1)
                    }/*else{
                        ToastUtils.showShort("你别\n" +
                                "点了")
                    }*/

                    /*if (type==2){
                        rlv_user_list_xzq.visibility = View.VISIBLE
                        type2 = 3
                        code = item.code
                        type1 = 3
                        mPresenter.getUserQueryRy(code,userName)
                        mPresenter.getXzqZhen(code,type1)
                        tv_user_list_qujian.visibility = View.VISIBLE
                        tv_user_list_zhen.visibility = View.VISIBLE
                        tv_user_list_zhen.setText(item!!.name)
                    }else if (type==3){
                        type2 = 3
                        rlv_user_list_xzq.visibility = View.GONE
                        code = item.code
                        mPresenter.getUserQueryRy(code,userName)
                        tv_user_list_zhenjian.visibility = View.VISIBLE
                        tv_user_list_cun.visibility = View.VISIBLE
                        tv_user_list_cun.setText(item!!.name)
                    }*/
                }
            }
        }

    }

    override fun returnUserQueryRy(message: ArrayList<SysXzqEntity>, type: Int) {//没有用到

    }

    override fun showLoading(title: String?) {
        if (userName.equals("")){
            LoadingDialog.showDialogForLoading(this)
        }
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

    override fun onResume() {
        super.onResume()
//        mPresenter.getUserList(userName)
        mPresenter.getUserQueryRy(code,userName)
        mPresenter.getXzqZhen(code,type1)
    }
}
