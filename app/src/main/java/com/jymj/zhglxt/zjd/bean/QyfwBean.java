package com.jymj.zhglxt.zjd.bean;

/**
 * 区域范围
 */
public class QyfwBean {

    public String quName;//区
    public String zhenName;//镇
    public String cunName;//村
    public String quCode;//区
    public String zhenCode;//镇
    public String cunCode;//村

    public int quPosition;
    public int zhenPosition;
    public int cunPosition;

    public QyfwBean() {
    }

    public QyfwBean(String quName, String zhenName, String cunName) {
        this.quName = quName;
        this.zhenName = zhenName;
        this.cunName = cunName;
    }

    public String getQuName() {
        return quName==null?"":quName;
    }

    public void setQuName(String quName) {
        this.quName = quName;
    }

    public String getZhenName() {
        return zhenName==null?"":zhenName;
    }

    public void setZhenName(String zhenName) {
        this.zhenName = zhenName;
    }

    public String getCunName() {
        return cunName==null?"":cunName;
    }

    public void setCunName(String cunName) {
        this.cunName = cunName;
    }

    public int getQuPosition() {
        return quPosition;
    }

    public void setQuPosition(int quPosition) {
        this.quPosition = quPosition;
    }

    public int getZhenPosition() {
        return zhenPosition;
    }

    public void setZhenPosition(int zhenPosition) {
        this.zhenPosition = zhenPosition;
    }

    public int getCunPosition() {
        return cunPosition;
    }

    public void setCunPosition(int cunPosition) {
        this.cunPosition = cunPosition;
    }

    public String getQuCode() {
        return quCode;
    }

    public void setQuCode(String quCode) {
        this.quCode = quCode;
    }

    public String getZhenCode() {
        return zhenCode;
    }

    public void setZhenCode(String zhenCode) {
        this.zhenCode = zhenCode;
    }

    public String getCunCode() {
        return cunCode;
    }

    public void setCunCode(String cunCode) {
        this.cunCode = cunCode;
    }
}
