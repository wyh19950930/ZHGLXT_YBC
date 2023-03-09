package com.jymj.zhglxt.util

import android.app.Activity
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.model.BitmapDescriptorFactory
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.Marker
import com.amap.api.maps2d.model.MarkerOptions
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity
import com.jymj.zhglxt.zjd.bean.RltBean
import com.setsuna.common.baseapp.AppCache
import java.math.BigDecimal

object RltUtil {


    //出租使用   var markers = ArrayList<Marker>()//所有聚合的点位//res: Int, enviorSupvsEntity: XzqInfoEntity
    fun setMarker(rltBean: RltBean, markers: ArrayList<Marker>, activity:Activity, aMap: AMap) { //,int mid  j: Int

        val mInflater = LayoutInflater.from(activity)
        val view1: View = mInflater.inflate(R.layout.item_marker1, null)
        val rlMarker = view1.findViewById<RelativeLayout>(R.id.rlt_item_marker)
        val ivItemMarker = view1.findViewById<ImageView>(R.id.iv_item_marker1)
        val itemMarkerPosition = view1.findViewById<TextView>(R.id.item_marker_position1)
        val countTv = view1.findViewById<TextView>(R.id.item_marker_position2)
        ivItemMarker.setImageResource(rltBean.res)
        val para = ivItemMarker.getLayoutParams()
        val counts = rltBean.count
        if (counts==0){
            para.height = 0
            para.width = 0
            rlMarker.visibility = View.INVISIBLE
        }else if (counts < 100) {
            para.height = 120
            para.width = 120
            rlMarker.visibility = View.VISIBLE
        }else if (counts < 1000) {
            para.height = 140
            para.width = 140
            rlMarker.visibility = View.VISIBLE
        }else if (counts < 10000) {
            para.height = 160
            para.width = 160
            rlMarker.visibility = View.VISIBLE
        }else if (counts > 10000) {
            para.height = 180
            para.width = 180
            rlMarker.visibility = View.VISIBLE
        }
        ivItemMarker.setLayoutParams(para)
        itemMarkerPosition.text = rltBean.xzqmc
        countTv.text = counts.toString()
        val bitmap = convertViewToBitmap(view1)
        //绘制marker
        if (rltBean.zoom < 13&&rltBean.type.toString().equals("2")) {
            val addMarker1 = aMap!!.addMarker(MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.center))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true))
            markers.add(addMarker1)
        }
        else if (rltBean.zoom < 16&&rltBean.type.toString().equals("3")) {//15
            val addMarker1 = aMap!!.addMarker(MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.center))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true))
            markers.add(addMarker1)
        }
        else if (rltBean.zoom < 13&&rltBean.type.toString().equals("0")) {//15
            val addMarker1 = aMap!!.addMarker(MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.center))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true))
            markers.add(addMarker1)
        }
        else if (rltBean.zoom < 16&&rltBean.type.toString().equals("1")) {//15
            val addMarker1 = aMap!!.addMarker(MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.center))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true))
            markers.add(addMarker1)
        }else{
        }

        aMap!!.setOnMarkerClickListener { marker ->
            true
        }
    }

    fun convertViewToBitmap(view: View): Bitmap {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }

    fun getCenter(center: String): LatLng {
        if (center != null) {
            if (!center.equals("")) {
                val points = center.substring(6, center.length - 1).split(" ").toTypedArray()
                return if (points != null && points.size > 1) {
                    val converter = CoordinateConverter()
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS)
                    val sl = LatLng(points[1].toDouble(), points[0].toDouble())
                    // sourceLatLng待转换坐标点 LatLng类型
                    converter.coord(sl)
                    val latLng = converter.convert()
                    sl
                } else {
                    LatLng(0.0, 0.0)
                }
            } else {
                LatLng(0.0, 0.0)
            }
        }
        return LatLng(0.0, 0.0)
    }

}