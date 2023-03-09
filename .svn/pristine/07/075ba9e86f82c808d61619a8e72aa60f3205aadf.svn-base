package com.jymj.basemessagesystem.ui.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Administrator on 2018/1/17 create this class
 */
class Legend : LinearLayout {
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}
    fun setDatas(datas:ArrayList<LegendEntity>){
        this.removeAllViews()
        for (d in datas){
            var tv = TextView(context)
            tv.text=d.title
            tv.setBackgroundColor(d.titleColor)
            tv.setTextColor(Color.WHITE)
            tv.gravity=Gravity.CENTER
            tv.width=0
            tv.layoutParams=LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,1f)
            this.addView(tv)
        }
    }
}