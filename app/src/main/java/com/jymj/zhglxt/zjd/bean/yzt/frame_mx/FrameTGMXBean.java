package com.jymj.zhglxt.zjd.bean.yzt.frame_mx;

import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/7/2 17:11
 */
public class FrameTGMXBean {

    private Object msg;
    private int code;
    private DataBean data;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

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
             * gid : 8519
             * xzqmc : 东芦城村
             * landName : 城镇建设用地区
             * area : 23
             * area1 : 17
             * code : 110115015008
             * type : 15
             * geometry : MULTIPOLYGON(((116.301457036215 39.7561742083353,
             * center : POINT(116.303180625741 39.7497728687409)
             */

            private int gid;
            private String xzqmc;
            private String landName;
            private double area;
            private double area1;
            private String code;
            private int type;
            private String geometry;
            private String center;

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public String getXzqmc() {
                return xzqmc == null ? "" : xzqmc;
            }

            public void setXzqmc(String xzqmc) {
                this.xzqmc = xzqmc;
            }

            public String getLandName() {
                return landName==null?"":landName;
            }

            public void setLandName(String landName) {
                this.landName = landName;
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
                return code==null?"":code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getGeometry() {
                return geometry==null?"":geometry;
            }

            public void setGeometry(String geometry) {
                this.geometry = geometry;
            }

            public String getCenter() {
                return center==null?"":center;
            }

            public void setCenter(String center) {
                this.center = center;
            }
        }
    }
}
