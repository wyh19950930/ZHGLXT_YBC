package com.jymj.zhglxt.ldrkgl.home.bean;

import java.io.Serializable;

/**
 * Created by ${lc} on 2022/2/16.
 */
public class YlFolwEntity implements Serializable{
    private static final long serialVersionUID = 5148544266918870582L;
    private String hzmc;//户主名称
    private String townname;//镇
    private String xzqmc;//村
    private String code;//行政区编码
    private String mph;//门牌号
    private String center;//中心点
    private String geometry;//坐标
    private Long objectid;//yl_id
    private int rent;//居住人口数
    private int rents;
    private int dwell;//出租房间数
    private int motorCars;//机动车数
    private int motorcycle;//摩托车数
    private int electricCars;//电动车数

    private QRCodeFileEntity qrCodeFileEntity;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHzmc() {
        return hzmc==null?"":hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }

    public String getTownname() {
        return townname==null?"":townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
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

    public String getMph() {
        return mph==null?"":mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
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

    public Long getObjectid() {
        return objectid;
    }

    public void setObjectid(Long objectid) {
        this.objectid = objectid;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getDwell() {
        return dwell;
    }

    public void setDwell(int dwell) {
        this.dwell = dwell;
    }

    public int getMotorCars() {
        return motorCars;
    }

    public void setMotorCars(int motorCars) {
        this.motorCars = motorCars;
    }

    public int getRents() {
        return rents;
    }

    public void setRents(int rents) {
        this.rents = rents;
    }

    public int getElectricCars() {
        return electricCars;
    }

    public void setElectricCars(int electricCars) {
        this.electricCars = electricCars;
    }

    public QRCodeFileEntity getQrCodeFileEntity() {
        return qrCodeFileEntity;
    }

    public void setQrCodeFileEntity(QRCodeFileEntity qrCodeFileEntity) {
        this.qrCodeFileEntity = qrCodeFileEntity;
    }

    public int getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(int motorcycle) {
        this.motorcycle = motorcycle;
    }
}
