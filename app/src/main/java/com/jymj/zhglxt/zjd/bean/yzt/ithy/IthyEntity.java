package com.jymj.zhglxt.zjd.bean.yzt.ithy;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;

import java.io.Serializable;
import java.math.BigDecimal;

public class IthyEntity implements Serializable {
    private Integer gid;
    /**
     *两规一级分类
     */
    private String lgyjfl;
    /**
     * 两规二级分类
     */
    private String lgejfl;
    private BigDecimal area;
    /**
     * 权属性质
     */
    private String qsxz;
    private String xzqmc;//行政区名称
    private String code;
    private String center;
    private BigDecimal type;

    @Override
    public String toString() {
        return "IthyEntity{" +
                "gid=" + gid +
                ", lgyjfl='" + lgyjfl + '\'' +
                ", lgejfl='" + lgejfl + '\'' +
                ", area=" + area +
                ", qsxz='" + qsxz + '\'' +
                ", xzqmc='" + xzqmc + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                '}';
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getLgyjfl() {
        return lgyjfl==null?"":lgyjfl;
    }

    public void setLgyjfl(String lgyjfl) {
        this.lgyjfl = lgyjfl;
    }

    public String getLgejfl() {
        return lgejfl==null?"":lgejfl;
    }

    public void setLgejfl(String lgejfl) {
        this.lgejfl = lgejfl;
    }

    public BigDecimal getArea() {
        return area==null?new BigDecimal(0):area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getQsxz() {
        return qsxz==null?"":qsxz;
    }

    public void setQsxz(String qsxz) {
        this.qsxz = qsxz;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getType() {
        return type==null?new BigDecimal(0):type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public IthyEntity() {
    }

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

    public IthyEntity(Integer gid, String lgyjfl, String lgejfl, BigDecimal area, String qsxz, String xzqmc, String code, BigDecimal type) {
        this.gid = gid;
        this.lgyjfl = lgyjfl;
        this.lgejfl = lgejfl;
        this.area = area;
        this.qsxz = qsxz;
        this.xzqmc = xzqmc;
        this.code = code;
        this.type = type;
    }
}
