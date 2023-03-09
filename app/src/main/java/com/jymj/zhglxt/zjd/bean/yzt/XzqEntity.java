package com.jymj.zhglxt.zjd.bean.yzt;

import java.util.List;

public class XzqEntity {
    
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }
    
    public void setData(List<DataBean> data) {
        this.data = data;
    }
    
    public static class DataBean {
        /**
         * center : POINT(116.705987472119 39.8629222727289)
         * code : 110112105201
         * cusCount : 0
         * feiNongCount : 0
         * fzhaijzmj : 0.6
         * fzhaimj : 1.1
         * geometry : null
         * gyjzmj : 0.2
         * gysscount : 0
         * gyssjzmj : 0.0
         * gyssmj : 0.0
         * gyzdmj : 0.6
         * huCount : 0
         * lfjzmj : 0.0
         * lfzdmj : 0.0
         * name : 北许场村
         * nongCount : 0
         * orderNum : null
         * parentId : 110
         * parentName : 张家湾镇
         * perms : null
         * qiyeCount : 3
         * qiyecount : 3
         * type : 3
         * typeName : 村
         * xzqId : 63
         * zhaiCount : 1
         * zhaijzmj : 0.1
         * zhaizd1 : 0
         * zhaizd2 : 0
         * zhaizd3 : 5
         * zhaizdmj : 0.1
         */
        
        private String center;
        private String code;
        private int cusCount;
        private int feiNongCount;
        private double fzhaijzmj;
        private double fzhaimj;
        private Object geometry;
        private double gyjzmj;
        private int gysscount;
        private double gyssjzmj;
        private double gyssmj;
        private double gyzdmj;
        private int huCount;
        private double lfjzmj;
        private double lfzdmj;
        private String name;
        private int nongCount;
        private Object orderNum;
        private int parentId;
        private String parentName;
        private Object perms;
        private int qiyeCount;
        private int qiyecount;
        private int type;
        private String typeName;
        private int xzqId;
        private int zhaiCount;
        private double zhaijzmj;
        private int zhaizd1;
        private int zhaizd2;
        private int zhaizd3;
        private double zhaizdmj;
        private boolean isChecked=false;
    
        public boolean isChecked() {
            return isChecked;
        }
    
        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    
        public String getCenter() {
            return center;
        }
        
        public void setCenter(String center) {
            this.center = center;
        }
        
        public String getCode() {
            return code;
        }
        
        public void setCode(String code) {
            this.code = code;
        }
        
        public int getCusCount() {
            return cusCount;
        }
        
        public void setCusCount(int cusCount) {
            this.cusCount = cusCount;
        }
        
        public int getFeiNongCount() {
            return feiNongCount;
        }
        
        public void setFeiNongCount(int feiNongCount) {
            this.feiNongCount = feiNongCount;
        }
        
        public double getFzhaijzmj() {
            return fzhaijzmj;
        }
        
        public void setFzhaijzmj(double fzhaijzmj) {
            this.fzhaijzmj = fzhaijzmj;
        }
        
        public double getFzhaimj() {
            return fzhaimj;
        }
        
        public void setFzhaimj(double fzhaimj) {
            this.fzhaimj = fzhaimj;
        }
        
        public Object getGeometry() {
            return geometry;
        }
        
        public void setGeometry(Object geometry) {
            this.geometry = geometry;
        }
        
        public double getGyjzmj() {
            return gyjzmj;
        }
        
        public void setGyjzmj(double gyjzmj) {
            this.gyjzmj = gyjzmj;
        }
        
        public int getGysscount() {
            return gysscount;
        }
        
        public void setGysscount(int gysscount) {
            this.gysscount = gysscount;
        }
        
        public double getGyssjzmj() {
            return gyssjzmj;
        }
        
        public void setGyssjzmj(double gyssjzmj) {
            this.gyssjzmj = gyssjzmj;
        }
        
        public double getGyssmj() {
            return gyssmj;
        }
        
        public void setGyssmj(double gyssmj) {
            this.gyssmj = gyssmj;
        }
        
        public double getGyzdmj() {
            return gyzdmj;
        }
        
        public void setGyzdmj(double gyzdmj) {
            this.gyzdmj = gyzdmj;
        }
        
        public int getHuCount() {
            return huCount;
        }
        
        public void setHuCount(int huCount) {
            this.huCount = huCount;
        }
        
        public double getLfjzmj() {
            return lfjzmj;
        }
        
        public void setLfjzmj(double lfjzmj) {
            this.lfjzmj = lfjzmj;
        }
        
        public double getLfzdmj() {
            return lfzdmj;
        }
        
        public void setLfzdmj(double lfzdmj) {
            this.lfzdmj = lfzdmj;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getNongCount() {
            return nongCount;
        }
        
        public void setNongCount(int nongCount) {
            this.nongCount = nongCount;
        }
        
        public Object getOrderNum() {
            return orderNum;
        }
        
        public void setOrderNum(Object orderNum) {
            this.orderNum = orderNum;
        }
        
        public int getParentId() {
            return parentId;
        }
        
        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
        
        public String getParentName() {
            return parentName;
        }
        
        public void setParentName(String parentName) {
            this.parentName = parentName;
        }
        
        public Object getPerms() {
            return perms;
        }
        
        public void setPerms(Object perms) {
            this.perms = perms;
        }
        
        public int getQiyeCount() {
            return qiyeCount;
        }
        
        public void setQiyeCount(int qiyeCount) {
            this.qiyeCount = qiyeCount;
        }
        
        public int getQiyecount() {
            return qiyecount;
        }
        
        public void setQiyecount(int qiyecount) {
            this.qiyecount = qiyecount;
        }
        
        public int getType() {
            return type;
        }
        
        public void setType(int type) {
            this.type = type;
        }
        
        public String getTypeName() {
            return typeName;
        }
        
        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
        
        public int getXzqId() {
            return xzqId;
        }
        
        public void setXzqId(int xzqId) {
            this.xzqId = xzqId;
        }
        
        public int getZhaiCount() {
            return zhaiCount;
        }
        
        public void setZhaiCount(int zhaiCount) {
            this.zhaiCount = zhaiCount;
        }
        
        public double getZhaijzmj() {
            return zhaijzmj;
        }
        
        public void setZhaijzmj(double zhaijzmj) {
            this.zhaijzmj = zhaijzmj;
        }
        
        public int getZhaizd1() {
            return zhaizd1;
        }
        
        public void setZhaizd1(int zhaizd1) {
            this.zhaizd1 = zhaizd1;
        }
        
        public int getZhaizd2() {
            return zhaizd2;
        }
        
        public void setZhaizd2(int zhaizd2) {
            this.zhaizd2 = zhaizd2;
        }
        
        public int getZhaizd3() {
            return zhaizd3;
        }
        
        public void setZhaizd3(int zhaizd3) {
            this.zhaizd3 = zhaizd3;
        }
        
        public double getZhaizdmj() {
            return zhaizdmj;
        }
        
        public void setZhaizdmj(double zhaizdmj) {
            this.zhaizdmj = zhaizdmj;
        }
    }
}
