package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ljj on 2017/5/3.
 * 用地类型
 */
public enum TypeEnum {
    one("宅基地", 1),
    zero("其他", 0),
    two("非宅", 2),
    three("国有",3);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    TypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (TypeEnum s : TypeEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return null;
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
