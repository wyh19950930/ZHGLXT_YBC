package com.jymj.zhglxt.zjd.activity

import android.animation.ValueAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jymj.zhglxt.R
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity
import com.jymj.zhglxt.rjhj.bean.yl.ZhaiEntity
import com.jymj.zhglxt.util.HjgxtUtil
import com.jymj.zhglxt.widget.gxt.bean.AtmanRelation
import com.jymj.zhglxt.widget.gxt.bean.RelationParent
import com.jymj.zhglxt.widget.gxt.bean.ZhengjuBean
import com.jymj.zhglxt.zjd.bean.yzt.HujiEntity
import com.nineoldandroids.view.ViewHelper
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_hjgxt.*

/**
 * 户籍关系图
 */
class HjgxtActivity : AppCompatActivity(), View.OnClickListener {

    /*
    * 记录暂时按下的位置
    * */
    internal var fingerX = 0
    internal var fingerY = 0
    private var scale = 1f
    private var preScale = 1f// 默认前一次缩放比例为1
    private var showWhat = 3;//当前显示的几度关系

    private var moreFinger = false//多指操作
    private var mScaleGestureDetector: ScaleGestureDetector? = null;
    private var clickAtmanRelation: AtmanRelation? = null
    private var sourceList = ArrayList<AtmanRelation>()

    private val mhandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                11 -> hvScrollView.scrollTo(DisplayUtil.dip2px(2500f) - DisplayUtil.getWidth() / 2,
                        DisplayUtil.dip2px(2500f) - DisplayUtil.getHeight() / 2)
                12 -> if (scale < 0.7) {
                    tryScale(scale, 0.7f)
                } else if (scale > 1.3) {
                    tryScale(scale, 1.3f)
                }
            }
        }
    }
    
    private var zhaiList = ArrayList<HujiEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hjgxt)
        shaderImage.setListener(this)//DisplayUtil.getHeight()
        acib_act_hjgxt_back.setOnClickListener {
            finish()
        }
        zhaiList = intent.getSerializableExtra("huji") as ArrayList<HujiEntity>;
        hvScrollView.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (moreFinger) {
                mScaleGestureDetector?.onTouchEvent(motionEvent)
            }

            when (motionEvent.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    //第一个手指按下事件
                    fingerX = motionEvent.x.toInt()
                    fingerY = motionEvent.y.toInt()
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    //第二个手指按下事件

                    moreFinger = true
                }
                MotionEvent.ACTION_MOVE -> Log.e("我看看那这里的操作哈", "手指移动1111")
                MotionEvent.ACTION_UP -> {
                    if (!moreFinger && clickAtmanRelation != null) {//单指 并且是点击状态
                        val x_cha = (fingerX - motionEvent.x).toInt()
                        val y_cha = (fingerY - motionEvent.y).toInt()
                        if (Math.abs(x_cha) + Math.abs(y_cha) < 10) {
                            shaderImage.setShowCount(showWhat)
                            clickAtmanRelation = null
                        }

                    }
                    moreFinger = false
                    mhandler.sendEmptyMessage(12)//手指放开来个回弹效果
                }
            }
//                        Log.e("这个判断一点没用？", moreFinger + "==============")
            moreFinger
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hvScrollView.setOnScrollChangeListener(View.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (shaderImage.canScrollBean != null) {
                    val left_x = shaderImage.canScrollBean.left_x.x
                    val lefy_y = shaderImage.canScrollBean.left_top_y.y
                    val right_x = shaderImage.canScrollBean.rigth_x.x
                    val right_y = shaderImage.canScrollBean.right_bottom_y.y
                    var adddisX_left = 0
                    var adddisX_right = 0
                    var adddisY_left = 0
                    var adddisY_right = 0
                    if (scale >= 1) {
                        if (scale <= 1.3) {
                            adddisX_left = (left_x - (left_x * scale - left_x) / 4).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x + (right_x * scale - right_x) / 4).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y - (lefy_y * scale - lefy_y) / 4).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y + (right_y * scale - right_y) / 4).toInt() + DisplayUtil.getHeight() / 2
                        } else {
                            adddisX_left = (left_x - (left_x * 1.3 - left_x) / 4).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x + (right_x * 1.3 - right_x) / 4).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y - (lefy_y * 1.3 - lefy_y) / 4).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y + (right_y * 1.3 - right_y) / 4).toInt() + DisplayUtil.getHeight() / 2
                        }

                    } else {
                        if (scale >= 0.7) {
                            adddisX_left = (left_x + (left_x - left_x * scale) / 4.5).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x - (right_x - right_x * scale) / 4.5).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y + (lefy_y - lefy_y * scale) / 4.5).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y - (right_y - right_y * scale) / 4.5).toInt() + DisplayUtil.getHeight() / 2
                        } else {
                            adddisX_left = (left_x + (left_x - left_x * 0.7) / 4.5).toInt() - DisplayUtil.getWidth() / 2
                            adddisX_right = (right_x - (right_x - right_x * 0.7) / 4.5).toInt() + DisplayUtil.getWidth() / 2
                            adddisY_left = (lefy_y + (lefy_y - lefy_y * 0.7) / 4.5).toInt() - DisplayUtil.getHeight() / 2
                            adddisY_right = (right_y - (right_y - right_y * 0.7) / 4.5).toInt() + DisplayUtil.getHeight() / 2
                        }

                    }


                    val minX_left = DisplayUtil.dip2px( 2500f) - DisplayUtil.getWidth() / 2
                    val minY_left = DisplayUtil.dip2px( 2500f) - DisplayUtil.getHeight() / 2

                    val minX_right = DisplayUtil.dip2px( 2500f) + DisplayUtil.getWidth() / 2
                    val minY_right = DisplayUtil.dip2px( 2500f) + DisplayUtil.getHeight() / 2


                    if (adddisX_left > minX_left) {
                        adddisX_left = minX_left
                    }
                    if (adddisY_left > minY_left) {
                        adddisY_left = minY_left
                    }

                    if (adddisX_right < minX_right) {
                        adddisX_right = minX_right
                    }

                    if (adddisY_right < minY_right) {
                        adddisY_right = minY_right
                    }


                    if (scrollX <= adddisX_left && scrollY <= adddisY_left) {
                        hvScrollView.smoothScrollTo(adddisX_left, adddisY_left)
                    } else if (scrollX <= adddisX_left && scrollY >= adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_left, adddisY_right - DisplayUtil.getHeight())
                    } else if (scrollX <= adddisX_left && adddisY_left < scrollY && scrollY < adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_left, scrollY)
                    } else if (scrollX >= adddisX_right - DisplayUtil.getWidth() && scrollY <= adddisY_left) {
                        hvScrollView.smoothScrollTo(adddisX_right - DisplayUtil.getWidth(), adddisY_left)
                    } else if (scrollX >= adddisX_right - DisplayUtil.getWidth() && scrollY >= adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_right - DisplayUtil.getWidth(), adddisY_right - DisplayUtil.getHeight())
                    } else if (scrollX >= adddisX_right - DisplayUtil.getWidth() && adddisY_left < scrollY && scrollY < adddisY_right - DisplayUtil.getHeight()) {
                        hvScrollView.smoothScrollTo(adddisX_right - DisplayUtil.getWidth(), scrollY)
                    } else if (scrollY <= adddisY_left && adddisX_left < scrollX && scrollX < adddisX_right - DisplayUtil.getWidth()) {
                        hvScrollView.smoothScrollTo(scrollX, adddisY_left)
                    } else if (scrollY >= adddisY_right - DisplayUtil.getHeight() && adddisX_left < scrollX && scrollX < adddisX_right - DisplayUtil.getWidth()) {
                        hvScrollView.smoothScrollTo(scrollX, adddisY_right - DisplayUtil.getHeight())
                    }
                } else {
                    //这里是只有一个头像的情况

                }
            })
        }/*
        hvScrollView.smoothScrollTo(DisplayUtil.dip2px(2500f) - DisplayUtil.getWidth() / 2,
                DisplayUtil.dip2px(2500f) -  DisplayUtil.getHeight()/ 2)*/
        mScaleGestureDetector = ScaleGestureDetector(this,
                ScaleGestureListener())
        mhandler.sendEmptyMessageDelayed(11, 500)
        sourceList = parseData(zhaiList)
        shaderImage.setSourceList(sourceList)


    }
    fun parseData(result: List<HujiEntity>): ArrayList<AtmanRelation> {//Gson 解析
        val detail = ArrayList<AtmanRelation>()
        val atmanRelation = AtmanRelation()
        atmanRelation.group = result.get(0).zhaiid
        atmanRelation.userId = "${result.get(0).zhaiid}"
        atmanRelation.lable = "#${result.get(0).zhaiid}"
        atmanRelation.headerUrl = "starsInTheSky/0/0.png"
        atmanRelation.degree = 0
        atmanRelation.sonCount = result.size-1
        atmanRelation.nickName = result.get(0).housecount
        detail.add(atmanRelation)
        if (result.size>1){
            for (i in 1 until  result.size){
                val atmanRelation = AtmanRelation()
                atmanRelation.group = result.get(i).zhaiid
                atmanRelation.userId = "${result.get(i).zhaiid}"
                atmanRelation.lable = "#${result.get(i).zhaiid}"
                atmanRelation.headerUrl = "starsInTheSky/0/0.png"
                atmanRelation.degree = 1//1//result.get(i).hierarchy-1
                atmanRelation.sonCount = 1//1//result.get(i).hujiRelationEntities.size
                atmanRelation.nickName = result.get(i).housecount
                val arrayList = ArrayList<RelationParent>()
               /* for (f in result.get(i).hujiRelationEntities){
                    val relationParent = RelationParent()
                    relationParent.group = f.parentid//result.get(i).zhaiid
                    relationParent.pictureCount = 1//result.size
                    relationParent.relation = f.relation
                    arrayList.add(relationParent)
                }*/
                val relationParent = RelationParent()
                relationParent.group = result.get(0).zhaiid
                relationParent.pictureCount = 1//result.size
                relationParent.relation = result.get(i).socialrelatText
                arrayList.add(relationParent)
                atmanRelation.parentGroups = arrayList
                detail.add(atmanRelation)

            }
        }
        /*try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity = gson.fromJson<AtmanRelation>(data.optJSONObject(i).toString(), AtmanRelation::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
//        Log.e("detail", Gson().toJson(result))
//        Log.e("detail", Gson().toJson(detail))
        return detail
    }
    fun tryScale(current: Float, target: Float) {

        val animator_shadow = ValueAnimator.ofFloat(current, target)
        animator_shadow.duration = 200
        animator_shadow.addUpdateListener { valueAnimator ->
            scale = valueAnimator.animatedValue as Float
            preScale = scale
            ViewHelper.setScaleX(shaderImage, scale)// x方向上缩放
            ViewHelper.setScaleY(shaderImage, scale)// y方向上缩放
        }
        animator_shadow.start()

    }

    override fun onClick(view: View?) {

        when (view?.getId()) {
            R.id.image_theOne ->
                if (sourceList != null && sourceList.size > 0) {
                    val atmanRelation = view!!.getTag(R.id.image_theOne) as AtmanRelation
                    if (clickAtmanRelation != null) {
                        if (clickAtmanRelation!!.getGroup() === atmanRelation.group) {
                            var zhaiEntity = HujiEntity()
                            for (i in zhaiList){
                                if (i.zhaiid == atmanRelation.group){
                                    zhaiEntity = i
                                    break
                                }
                            }
//                            tvs_act_yzt_hjgxt
                            val tvDatas = ArrayList<TextViewsEntity>()
                            tvDatas.add(TextViewsEntity("村名:", zhaiEntity.xzqmc))
                            tvDatas.add(TextViewsEntity("姓名:", zhaiEntity.housecount))
                            tvDatas.add(TextViewsEntity("性别:", zhaiEntity.sexText))
                            tvDatas.add(TextViewsEntity("年龄:", zhaiEntity.age.toString()))
                            tvDatas.add(TextViewsEntity("户别:", zhaiEntity.huTypeText.toString()))
                            tvDatas.add(TextViewsEntity("社会关系:", zhaiEntity.socialrelatText.toString()))

                            tvs_act_yzt_hjgxt?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                            tvs_act_yzt_hjgxt?.setDatas(tvDatas)
                            ToastUtils.showShort(atmanRelation.nickName)
//                        Toast.makeText(this@YztDcF, "点击头像", Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                    clickAtmanRelation = atmanRelation
                    //得到当前所点击的item,拿出他的 子空间和父控件。
                    val clickList = java.util.ArrayList<AtmanRelation>()
                    clickList.add(atmanRelation)
                    for (i in sourceList.indices) {
                        for (v in 0 until sourceList.get(i).getParentGroups().size) {
                            if (atmanRelation.group == sourceList.get(i).getParentGroups().get(v).getGroup()) {
                                clickList.add(sourceList.get(i))
                            }
                        }

                        for (j in 0 until atmanRelation.parentGroups.size) {
                            if (atmanRelation.parentGroups[j].group == sourceList.get(i).getGroup()) {
                                clickList.add(sourceList.get(i))
                            }
                        }
                    }

                    shaderImage.setClickList(clickList, atmanRelation.degree, atmanRelation.group, atmanRelation)
                    val animation = AnimationUtils.loadAnimation(this, R.anim.scale_tip_point)
                    view!!.startAnimation(animation)
                }

            R.id.zhengju_image -> {
                val zhengjuBean = view!!.getTag() as ZhengjuBean
                ToastUtils.showShort(zhengjuBean.getGroupId3())
//                Toast.makeText(this@ReleaseActivity, "" + zhengjuBean.getGroupId3(), Toast.LENGTH_SHORT).show()
            }
        }//                Toast.makeText(ReleaseActivity.this, "点击证据:：：GroupId1=======" + zhengjuBean.getGroupId1() + "------>" + "GroupId2=======" + zhengjuBean.getGroupId2(), Toast.LENGTH_SHORT).show();
    }

    inner class ScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {

        override fun onScale(detector: ScaleGestureDetector): Boolean {

            val previousSpan = detector.previousSpan
            val currentSpan = detector.currentSpan
            if (currentSpan < previousSpan) {
                // 缩小
                // scale = preScale-detector.getScaleFactor()/3;
                scale = preScale - (previousSpan - currentSpan) / 1000
                if (scale < 0.5) {
                    scale = 0.5f
                }
            } else {
                // 放大
                // scale = preScale+detector.getScaleFactor()/3;
                scale = preScale + (currentSpan - previousSpan) / 1000
                if (scale > 1.5) {
                    scale = 1.5f
                }
            }

            // 缩放view
            ViewHelper.setScaleX(shaderImage, scale)// x方向上缩小
            ViewHelper.setScaleY(shaderImage, scale)// y方向上缩小
            return false
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            // 一定要返回true才会进入onScale()这个函数

            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            preScale = scale//记录本次缩放比例
            var thisb = 40
            if (scale >= 1.3) {
                thisb = 25
            } else if (scale < 1.3 && scale >= 1.2) {
                thisb = 30
            } else if (scale < 1.2 && scale >= 1.1) {
                thisb = 35
            } else if (scale < 1.1 && scale >= 1.0) {
                thisb = 40
            } else if (scale < 1.0 && scale >= 0.9) {
                thisb = 45
            } else if (scale < 0.9 && scale >= 0.8) {
                thisb = 50
            } else if (scale < 0.8 && scale >= 0.7) {
                thisb = 55
            } else {
                thisb = 55
            }
            if (clickAtmanRelation != null) {
                shaderImage.setHaveClickPic(thisb, false)
                //得到当前所点击的item,拿出他的 子空间和父控件。
                val clickList = java.util.ArrayList<AtmanRelation>()
                clickList.add(clickAtmanRelation!!)
                for (i in sourceList.indices) {
                    for (v in 0 until sourceList[i].parentGroups.size) {
                        if (clickAtmanRelation!!.getGroup() === sourceList[i].parentGroups[v].group) {
                            clickList.add(sourceList[i])
                        }
                    }

                    for (j in 0 until clickAtmanRelation!!.getParentGroups().size) {
                        if (clickAtmanRelation!!.getParentGroups()[j].group === sourceList[i].group) {
                            clickList.add(sourceList[i])
                        }
                    }
                }

                shaderImage.setClickListScale(clickList, clickAtmanRelation!!.getDegree(), clickAtmanRelation!!.getGroup(), clickAtmanRelation)
            } else {
                shaderImage.setHavePic(thisb)
            }

        }
    }
}
