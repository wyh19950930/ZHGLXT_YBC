package com.jymj.zhglxt.bean.enums;


/**
 * Created by ${lc} on 2022/2/17.
 */
public enum IsHaveEnum  {
    one("有", 1),
    two("无", 0);
    // 成员变量
    private String name;
    private int index;

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

    // 构造方法
    private IsHaveEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (IsHaveEnum s : IsHaveEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }

}
