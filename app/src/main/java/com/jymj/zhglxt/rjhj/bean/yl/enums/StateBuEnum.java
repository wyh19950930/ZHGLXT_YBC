package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2017/4/6.
 * 经营状态
 */
public enum StateBuEnum {
    one("正常经营", 1),
    zero("其他", 0),
    two("闲置院落", 2),
    tree("停产停业",3),
    four("腾退",4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    StateBuEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (StateBuEnum s : StateBuEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // get set 方法
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
