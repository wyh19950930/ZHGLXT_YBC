package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by dl on 2019/10/14.
 */
public enum ZjdTcEnum {
    one("申请", 1),
    two("镇政府调查", 2),
    nine("签订协议", 3),
    three("农业农村局备案", 4),
    four("国土分局注销", 5),
    five("驳回",6);

    // 成员变量
    private String name;
    private int index;

    ZjdTcEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ZjdTcEnum s : ZjdTcEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return null;
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
