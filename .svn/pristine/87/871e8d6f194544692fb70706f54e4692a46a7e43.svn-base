package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by dl on 2019/10/16.
 */
public enum ZjdSqEnum {
    one("申请", 1),
    two("镇政府审核", 2),
    nine("农业农村局备案", 3),
    three("规自委确权", 4),
    four("申请驳回", 5);

    // 成员变量
    private String name;
    private int index;

    ZjdSqEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ZjdSqEnum s : ZjdSqEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
