package com.jymj.zhglxt.bean.enums;

/**
 * Created by ${lc} on 2022/2/17. 出生地
 */

public enum BirthplaceEnum  {
    one("北京", 1),
    two("原籍", 2),
    three("其他地区", 0);
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
    private BirthplaceEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (BirthplaceEnum s : BirthplaceEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }

}
