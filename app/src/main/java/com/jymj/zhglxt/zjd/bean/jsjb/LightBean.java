package com.jymj.zhglxt.zjd.bean.jsjb;

public class LightBean {

    public int id;
    public int type;//1:市  2:区
    public String color;
    public String content;
    public String createDate;
    public String updateDate;
    public boolean isCheck;

    public LightBean(String color, String content) {
        this.color = color;
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getColor() {
        return color==null?"":color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContent() {
        return content==null?"":content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate==null?"":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate==null?"":updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
