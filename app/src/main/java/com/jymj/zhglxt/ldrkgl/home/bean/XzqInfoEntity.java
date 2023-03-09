package com.jymj.zhglxt.ldrkgl.home.bean;

import java.io.Serializable;

/**
 * Created by ${lc} on 2022/2/18. 村庄列表
 */

public class XzqInfoEntity implements Serializable{
    private static final long serialVersionUID = 5103909279207093770L;

    private String lgyname;//流管员姓名
    private String xzqmc;//村名
    private String code;//编码
    private String center;//中心点
    private String geometry;//坐标
    private int counts;//数量
    private int zhaicount;//出租宅基地数
    private int roomcount;//已出租房间数
    private int roomTotal;//房间数
    private int flowcount;//流动人口数

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLgyname() {
        return lgyname==null?"":lgyname;
    }

    public void setLgyname(String lgyname) {
        this.lgyname = lgyname;
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

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public int getZhaicount() {
        return zhaicount;
    }

    public void setZhaicount(int zhaicount) {
        this.zhaicount = zhaicount;
    }

    public int getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(int roomcount) {
        this.roomcount = roomcount;
    }

    public int getFlowcount() {
        return flowcount;
    }

    public void setFlowcount(int flowcount) {
        this.flowcount = flowcount;
    }

    public int getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(int roomTotal) {
        this.roomTotal = roomTotal;
    }
}
