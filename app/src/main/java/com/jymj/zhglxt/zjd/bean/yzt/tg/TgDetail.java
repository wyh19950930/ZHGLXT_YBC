package com.jymj.zhglxt.zjd.bean.yzt.tg;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;

import java.util.List;

public class TgDetail {
    private int tgCount;
    private List<TgEntityListBean> tgEntityList;
    
    public int getTgCount() {
        return tgCount;
    }
    
    public void setTgCount(int tgCount) {
        this.tgCount = tgCount;
    }
    
    public List<TgEntityListBean> getTgEntityList() {
        return tgEntityList;
    }
    
    public void setTgEntityList(List<TgEntityListBean> tgEntityList) {
        this.tgEntityList = tgEntityList;
    }
    
    public static class TgEntityListBean {
        /**
         * area : 2.0
         * area1 : 2.0
         * code : 110112105236
         * geometry : null
         * gid : 754
         * landName : 基本农田
         * xzqmc : 三间房
         */
        
        private double area;
        private double area1;
        private String code;
        private String geometry;
        private int gid;
        private String landName;
        private String xzqmc;
        //中心点
        private String center;
        
        public LatLng getCenter() {
            if (center != null) {
                String[] points = center.substring(6, center.length() - 1).split(" ");
                if (points != null && points.length > 1) {
                    CoordinateConverter converter = new CoordinateConverter();
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS);
                    LatLng sl = new LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]));
                    // sourceLatLng待转换坐标点 LatLng类型
                    converter.coord(sl);
                    LatLng latLng = converter.convert();
                    return sl;
                } else {
                    return new LatLng(0, 0);
                }
            }
            
            return new LatLng(0, 0);
        }
        
        public void setCenter(String center) {
            this.center = center;
        }
        
        public double getArea() {
            return area;
        }
        
        public void setArea(double area) {
            this.area = area;
        }
        
        public double getArea1() {
            return area1;
        }
        
        public void setArea1(double area1) {
            this.area1 = area1;
        }
        
        public String getCode() {
            return code;
        }
        
        public void setCode(String code) {
            this.code = code;
        }
        
        public String getGeometry() {
            return geometry;
        }
        
        public void setGeometry(String geometry) {
            this.geometry = geometry;
        }
        
        public int getGid() {
            return gid;
        }
        
        public void setGid(int gid) {
            this.gid = gid;
        }
        
        public String getLandName() {
            return landName;
        }
        
        public void setLandName(String landName) {
            this.landName = landName;
        }
        
        public String getXzqmc() {
            return xzqmc;
        }
        
        public void setXzqmc(String xzqmc) {
            this.xzqmc = xzqmc;
        }
    }
}
