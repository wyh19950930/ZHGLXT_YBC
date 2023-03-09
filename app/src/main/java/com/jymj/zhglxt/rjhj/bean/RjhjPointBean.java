package com.jymj.zhglxt.rjhj.bean;


public class RjhjPointBean {

    public Integer code;
    public String msg;
    public YLEntity data;
    public Integer czfw;
    public PointRecordEntity pointRecordEntity;//pointRecordEntity  point

    public PointRecordEntity getPointRecordEntity() {
        return pointRecordEntity;
    }

    public void setPointRecordEntity(PointRecordEntity pointRecordEntity) {
        this.pointRecordEntity = pointRecordEntity;
    }

    public Integer getCode() {
        return code==null?0:code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg==null?"":msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public YLEntity getData() {
        return data;
    }

    public void setData(YLEntity data) {
        this.data = data;
    }

    public Integer getCzfw() {
        return czfw==null?1:czfw;
    }

    public void setCzfw(Integer czfw) {
        this.czfw = czfw;
    }
}
