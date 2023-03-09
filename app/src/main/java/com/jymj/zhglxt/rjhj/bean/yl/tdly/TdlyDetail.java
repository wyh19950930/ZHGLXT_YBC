package com.jymj.zhglxt.rjhj.bean.yl.tdly;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class TdlyDetail {
    private int tdLyCount;
    private List<TdlyListBean> tdlyList;

    public int getTdLyCount() {
        return tdLyCount;
    }

    public void setTdLyCount(int tdLyCount) {
        this.tdLyCount = tdLyCount;
    }

    public List<TdlyListBean> getTdlyList() {
        return tdlyList==null?new ArrayList<>():tdlyList;
    }

    public void setTdlyList(List<TdlyListBean> tdlyList) {
        this.tdlyList = tdlyList;
    }

    public static class TdlyListBean {
        /**
         * area : 18.2
         * area1 : 1.8
         * code : 110112105222
         * dlbm : 203
         * dlmc : 村庄
         * geometry : null
         * gid : 7483
         * qsdwmc : 马营村
         * qsxz : 32
         * xzqmc : 马营村
         */

        private double area;
        private double area1;
        private String code;
        private String dlbm;
        private String dlmc;
        private String geometry;
        private int gid;
        private String qsdwmc;
        private String qsxz;
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

        public List<LatLng> getLatList() {
            List<LatLng> list = new ArrayList<>();
            if (center != null) {
                String substring = center.substring(14, center.length() - 4);
                String[] split = substring.split(",");
                for (String lat : split) {
                    String[] split1 = lat.split(" ");
                    CoordinateConverter converter = new CoordinateConverter();
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS);
                    LatLng sl = new LatLng(Double.parseDouble(split1[1]), Double.parseDouble(split1[0]));
                    // sourceLatLng待转换坐标点 LatLng类型
                    converter.coord(sl);
                    LatLng latLng = converter.convert();

                    list.add(latLng);
                }
            }
            return list;
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

        public String getDlbm() {
            return dlbm;
        }

        public void setDlbm(String dlbm) {
            this.dlbm = dlbm;
        }

        public String getDlmc() {
            return dlmc;
        }

        public void setDlmc(String dlmc) {
            this.dlmc = dlmc;
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

        public String getQsdwmc() {
            return qsdwmc;
        }

        public void setQsdwmc(String qsdwmc) {
            this.qsdwmc = qsdwmc;
        }

        public String getQsxz() {
            return qsxz;
        }

        public void setQsxz(String qsxz) {
            this.qsxz = qsxz;
        }

        public String getXzqmc() {
            return xzqmc;
        }

        public void setXzqmc(String xzqmc) {
            this.xzqmc = xzqmc;
        }
    }
}
