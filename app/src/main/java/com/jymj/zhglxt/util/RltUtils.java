package com.jymj.zhglxt.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.RltBean;

import java.util.ArrayList;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/4/24 16:06
 */
public class RltUtils {


    //出租使用   var markers = ArrayList<Marker>()//所有聚合的点位//res: Int, enviorSupvsEntity: XzqInfoEntity
    public static void setMarker(RltBean rltBean, ArrayList<Marker> markers, Activity activity, AMap aMap) { //,int mid  j: Int

        LayoutInflater mInflater = LayoutInflater.from(activity);
        View view1 = mInflater.inflate(R.layout.item_marker1, null);
        RelativeLayout rlMarker = view1.findViewById(R.id.rlt_item_marker);
        ImageView ivItemMarker = view1.findViewById(R.id.iv_item_marker1);
        TextView itemMarkerPosition = view1.findViewById(R.id.item_marker_position1);
        TextView countTv = view1.findViewById(R.id.item_marker_position2);
        ivItemMarker.setImageResource(rltBean.getRes());
        ViewGroup.LayoutParams para = ivItemMarker.getLayoutParams();
        int counts = rltBean.getCount();
        if (counts==0){
            para.height = 0;
            para.width = 0;
            rlMarker.setVisibility(View.INVISIBLE);
        }else if (counts < 100) {
            para.height = 120;
            para.width = 120;
            rlMarker.setVisibility(View.VISIBLE);
        }else if (counts < 1000) {
            para.height = 140;
            para.width = 140;
            rlMarker.setVisibility(View.VISIBLE);
        }else if (counts < 10000) {
            para.height = 160;
            para.width = 160;
            rlMarker.setVisibility(View.VISIBLE);
        }else if (counts > 10000) {
            para.height = 180;
            para.width = 180;
            rlMarker.setVisibility(View.VISIBLE);
        }
        ivItemMarker.setLayoutParams(para);
        itemMarkerPosition.setText(rltBean.getXzqmc());
        countTv.setText(counts+"");
        Bitmap bitmap = convertViewToBitmap(view1);
        //绘制marker
        if (rltBean.getZoom() < 13&&rltBean.getType() == 2) {
            Marker addMarker1 = aMap.addMarker(new MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.getCenter()))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true));
            markers.add(addMarker1);
        }
        else if (rltBean.getZoom() < 16&&rltBean.getType() == 3) {//15
            Marker addMarker1 = aMap.addMarker(new MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.getCenter()))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true));
            markers.add(addMarker1);
        }
        else if (rltBean.getZoom() < 13&&rltBean.getType() == 0) {//15
            Marker addMarker1 = aMap.addMarker(new MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.getCenter()))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true));
            markers.add(addMarker1);
        }
        else if (rltBean.getZoom() < 16&&rltBean.getType() == 1) {//15
            Marker addMarker1 = aMap.addMarker(new MarkerOptions()
//                .position(LatLng(wd, jd))
                    .position(getCenter(rltBean.getCenter()))
                    .snippet("") //                .period(mid)//添加markerID
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .draggable(true));
            markers.add(addMarker1);
        }else{
        }

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });

    }

    private static Bitmap convertViewToBitmap(View view){
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    private static LatLng getCenter(String center){
        if (center != null) {
            if (!center.equals("")) {
               String[] points = center.substring(6, center.length() - 1).split(" ");
                 if (points != null && points.length > 1) {
                    CoordinateConverter converter = new CoordinateConverter();
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS);
                    LatLng sl = new LatLng(Double.parseDouble(points[1]),Double.parseDouble(points[0]));
                    // sourceLatLng待转换坐标点 LatLng类型
                    converter.coord(sl);
                    LatLng latLng = converter.convert();
                    return sl;
                } else {
                   return new LatLng(0.0, 0.0);
                }
            } else {
                return new LatLng(0.0, 0.0);
            }
        }
        return new LatLng(0.0, 0.0);
    }

}
