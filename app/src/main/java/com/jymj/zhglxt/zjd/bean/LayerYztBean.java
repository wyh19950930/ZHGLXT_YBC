package com.jymj.zhglxt.zjd.bean;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/3/23 14:34
 */
public class LayerYztBean {
    private String name;
    private boolean isCheck;
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LayerYztBean(String name, boolean isCheck, int type) {
        this.name = name;
        this.isCheck = isCheck;
        this.type = type;
    }
}
