package com.jymj.zhglxt.zjd.bean.wy;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lc on 2022/5/26. 出租 返租 列表实体
 */
public class RentEntity {
    private String cqr;//产权人
    private String mph;//门牌号
    private BigDecimal area;//面积
    private String renDate;//时间
    private Integer roomToatl;//总房间数
    private Integer roomUse;//修缮房间/出租房间

    private String geometry;
    private String center;

    public String getCqr() {
        return cqr;
    }

    public void setCqr(String cqr) {
        this.cqr = cqr;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getRenDate() {
        return renDate;
    }

    public void setRenDate(String renDate) {
        this.renDate = renDate;
    }

    public Integer getRoomToatl() {
        return roomToatl == null?0:roomToatl;
    }

    public void setRoomToatl(Integer roomToatl) {
        this.roomToatl = roomToatl;
    }

    public Integer getRoomUse() {
        return roomUse == null?0:roomUse;
    }

    public void setRoomUse(Integer roomUse) {
        this.roomUse = roomUse;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
