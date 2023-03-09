package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2017/5/3.
 * 建筑年代
 */
public enum BuildageEnum {
    one("1982年前", 1),
    two("1982年后", 2),
    zero("其他",0);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    BuildageEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (BuildageEnum s : BuildageEnum.values()) {
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
