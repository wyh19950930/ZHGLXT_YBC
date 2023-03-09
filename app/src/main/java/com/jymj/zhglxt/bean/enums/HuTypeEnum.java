package com.jymj.zhglxt.bean.enums;


/**
 * Created by ljj on 2017/4/6. 户别
 */
public enum HuTypeEnum  {
    zero("无户口", 0),
    one("农户", 1),
    two("非农户", 2);
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
    private HuTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static int getIndex(String name) {
        for (HuTypeEnum s : HuTypeEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }

    // 普通方法
    public static String getName(int index) {
        for (HuTypeEnum s : HuTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
}
