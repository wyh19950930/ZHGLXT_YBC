package com.jymj.zhglxt.rjhj.bean.yl.cg;

public class CgEntity {
    private Integer gid;
    private String landName;
    //用地性质
    private String ydxz;
    //容积率
    private double rjl;
    //分期
    private String fq;
    //用地编码
    private String yddm;
    //建筑规模
    private double jzgm;
    private double area;
    private String geometry;
    
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
    
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    
    public String getYdxz() {
        return ydxz;
    }
    
    public void setYdxz(String ydxz) {
        this.ydxz = ydxz;
    }
    
    public String getYddm() {
        return yddm;
    }
    
    public void setYddm(String yddm) {
        this.yddm = yddm;
    }
}
