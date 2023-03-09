package com.jymj.zhglxt.bean.enums;


/**
 * Created by ljj on 2017/5/3. 性别
 */
public enum SexEnum  {
    /*one("男", 0),
    two("女", 1),*/
    one("男", 1),
    two("女", 2);
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
    private SexEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (SexEnum s : SexEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (SexEnum s : SexEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }
}
