package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by CL on 2017/7/24. 是 否
 */
public enum IsTrueEnum {
    one("是", 1),
    two("否", 0),
    three("创城", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private IsTrueEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "是";
        }
        for (IsTrueEnum s : IsTrueEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (IsTrueEnum s : IsTrueEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return -1;
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
