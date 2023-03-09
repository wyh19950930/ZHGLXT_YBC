package com.jymj.zhglxt.rjhj.bean.yl.cg;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;

import java.util.List;

public class CgDetail {
    private int cgCount;
    private List<CgListBean> cgList;
    
    public int getCgCount() {
        return cgCount;
    }
    
    public void setCgCount(int cgCount) {
        this.cgCount = cgCount;
    }
    
    public List<CgListBean> getCgList() {
        return cgList;
    }
    
    public void setCgList(List<CgListBean> cgList) {
        this.cgList = cgList;
    }
    
    public static class CgListBean {
        private Integer gid;
        private String landName;
        //用地性质
        private String ydxz;
        //容积率
        private double rjl;
        //分期
        private String fq;
        //中心点
        private String center;
        //用地编码
        private String yddm;
        //建筑规模
        private double jzgm;
        private double area;
        private String geometry;
        public LatLng getCenter() {
            if (center != null) {
                String[] points = center.substring(6, center.length() - 1).split(" ");
                if (points != null && points.length > 1) {
                    CoordinateConverter converter = new CoordinateConverter();
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS);
                    LatLng sl=new LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]));
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
        
        public String getFq() {
            return fq;
        }
        
        public void setFq(String fq) {
            this.fq = fq;
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
        
        public double getJzgm() {
            return jzgm;
        }
        
        public void setJzgm(double jzgm) {
            this.jzgm = jzgm;
        }
        
        public String getLandName() {
            return landName;
        }
        
        public void setLandName(String landName) {
            this.landName = landName;
        }
        
        public double getRjl() {
            return rjl;
        }
        
        public void setRjl(double rjl) {
            this.rjl = rjl;
        }
        
        public String getYddm() {
            return yddm;
        }
        
        public void setYddm(String yddm) {
            this.yddm = yddm;
        }
        
        public String getYdxz() {
            return ydxz;
        }
        
        public void setYdxz(String ydxz) {
            this.ydxz = ydxz;
        }
    }
}
