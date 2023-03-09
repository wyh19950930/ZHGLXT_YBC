package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ljj on 2018/11/23.
 */
public enum ProjGeoEnum {
    two("山区", 1),
    nine("平原", 2),

    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ProjGeoEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ProjGeoEnum s : ProjGeoEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // get set方法
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
