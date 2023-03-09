package com.jymj.zhglxt.zjd.activity.zjdgl

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.rjhj.bean.EnviorFileFhEntity
import com.jymj.zhglxt.zjd.contract.ImageListActContract
import com.jymj.zhglxt.zjd.presenter.ImageListActPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_image_list.*
import me.iwf.photopicker.PhotoPreview

class ImageListActivity : BaseActivity<ImageListActPresenter, ImageListActContract.Model>(), ImageListActContract.View {

    private var limit = 50
    private var page = 1
    private var enviorFileList = ArrayList<EnviorFileFhEntity>()
    private var split= listOf<String>()
    var sjfl = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_image_list
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        val stringExtra = intent.getStringExtra("snippet")
        sjfl = intent.getStringExtra("sjfl")!!
        if (stringExtra.equals("问题")){
            val stringArrayExtra = intent.getStringArrayListExtra("image")as ArrayList<String>
            val imageWtArrayExtra = intent.getStringArrayListExtra("imageWt")as ArrayList<String>
            rlv_image_list_tp.layoutManager = GridLayoutManager(this,3)
//        rlv_image_list_tp.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
            var dclFileAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_teng_tui_photo, stringArrayExtra) {
                override fun convert(helper: BaseViewHolder, item: String) {
                    val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                    val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                    helper.setVisible(R.id.iv_tent_tui_delete,false)
                    Glide.with(mContext).load(item)
                            .into(view)
                    helper.setText(R.id.tv_teng_photo_name, imageWtArrayExtra.get(helper.adapterPosition))
                    helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                        /*var pathList = ArrayList<String>()
                        for (i in enviorFileList){
                            val pic = ApiConstants.BASE_URL + i.path
                            val s1 = pic.replace("\\", "/")
                            pathList.add(s1)
                        }*/
                        PhotoPreview.builder()
                                .setPhotos(stringArrayExtra)
                                .setCurrentItem(helper.adapterPosition)
                                .setShowDeleteButton(false)
                                .start(this@ImageListActivity)

                    }
                    /*if (item.filetype == 1||item.filetype == 2) {//filetype
                        //                AppApplication.getGlideUrl(s1)

                        if (AppCache.getInstance().type == 4||AppCache.getInstance().type == 1)
                            helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                        helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                            // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                            var builder = AlertDialog.Builder(this@HBJCDetailActivity)
                            // 设置Title的图标
                            builder.setIcon(R.mipmap.ic_launcher)
                            // 设置Title的内容
                            builder.setTitle("弹出警告框")
                            // 设置Content来显示一个信息
                            builder.setMessage("确定删除吗？")
                            // 设置一个PositiveButton
                            builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
    //                                deletePic(item.id)
                                    deletePjEnviorFileEntity = item
                                    mPresenter.deleteFileEnvironmental(item.id)
                                }
                            })
                            // 设置一个NegativeButton
                            builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                                *//*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*//*
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        })
                        builder.show()
                    }

                }else{
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }*/
                }
            }
            rlv_image_list_tp.adapter = dclFileAdapter

        }else{
            split = stringExtra!!.split("-")
            mPresenter.getImageList(limit,page,split[0],split[1],split[2],split[3],split[4],split[5],split[6],sjfl)
        }

        iv_image_list_back.setOnClickListener {
            finish()
        }
    }

    override fun initDatas() {
    }

    override fun returnImageList(cash: List<EnviorFileFhEntity>) {
        enviorFileList.addAll(cash)
        rlv_image_list_tp.layoutManager = GridLayoutManager(this,3)
//        rlv_image_list_tp.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        var dclFileAdapter = object : BaseQuickAdapter<EnviorFileFhEntity, BaseViewHolder>(R.layout.item_teng_tui_photo, enviorFileList) {
            override fun convert(helper: BaseViewHolder, item: EnviorFileFhEntity) {
                val view = helper.getView<ImageView>(R.id.iv_teng_photo)
                val ll_teng_tui = helper.getView<LinearLayout>(R.id.ll_teng_tui)
                val pic: String = ApiConstants.RELEASE_URL + item.path
                helper.setVisible(R.id.iv_tent_tui_delete,false)
                val s1 = pic.replace("\\", "/")
                Glide.with(mContext).load(s1)
                        /*//GlideAppAppApplication.getGlideUrl(s1)
//                            .placeholder(R.drawable.loading).error(R.drawable.loading_fail)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .override(200,200)*/
                        .into(view)
                helper.setText(R.id.tv_teng_photo_name, item.name)
                helper.getView<ImageView>(R.id.iv_teng_photo).setOnClickListener {
                    var pathList = ArrayList<String>()
                    for (i in enviorFileList){
                        val pic = ApiConstants.RELEASE_URL + i.path
                        val s1 = pic.replace("\\", "/")
                        pathList.add(s1)
                    }
                    PhotoPreview.builder()
                            .setPhotos(pathList)
                            .setCurrentItem(helper.adapterPosition)
                            .setShowDeleteButton(false)
                            .start(this@ImageListActivity)

                }
                /*if (item.filetype == 1||item.filetype == 2) {//filetype
                    //                AppApplication.getGlideUrl(s1)

                    if (AppCache.getInstance().type == 4||AppCache.getInstance().type == 1)
                        helper.getView<ImageView>(R.id.iv_tent_tui_delete).visibility = View.VISIBLE
                    helper.getView<ImageView>(R.id.iv_tent_tui_delete).setOnClickListener {
                        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        var builder = AlertDialog.Builder(this@HBJCDetailActivity)
                        // 设置Title的图标
                        builder.setIcon(R.mipmap.ic_launcher)
                        // 设置Title的内容
                        builder.setTitle("弹出警告框")
                        // 设置Content来显示一个信息
                        builder.setMessage("确定删除吗？")
                        // 设置一个PositiveButton
                        builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
//                                deletePic(item.id)
                                deletePjEnviorFileEntity = item
                                mPresenter.deleteFileEnvironmental(item.id)
                            }
                        })
                        // 设置一个NegativeButton
                        builder.setNegativeButton("取消", object : DialogInterface.OnClickListener {
                            *//*  @Override
                              public void onClick(DialogInterface dialog, int which)
                              {
                                  Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                              }*//*
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog!!.dismiss()
                            }
                        })
                        builder.show()
                    }

                }else{
                    ll_teng_tui.visibility = View.GONE
                    helper.itemView.visibility = View.GONE
                }*/
            }
        }
        rlv_image_list_tp.adapter = dclFileAdapter
        rlv_image_list_tp.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager

                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
//                        Log.i(TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&enviorFileList.size==page*limit) {
                    page++
                    mPresenter.getImageList(limit,page,split[0],split[1],split[2],split[3],split[4],split[5],split[6],sjfl)
//                    ToastUtils.showShort("滑动到底部了")
                }else{
//                    ToastUtils.showShort("滑动到底部了")
                }
//                            Log.i(TAG, "滑动到底部")
            }
        })
        rlv_image_list_tp.scrollToPosition((page-1)*limit)
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
