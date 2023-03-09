package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2018/9/26.
 * 居住事由
 */
public enum DjfzqkEnum {
    two("登记类", 1),
    nine("居住证", 2)
    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private DjfzqkEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (DjfzqkEnum s : DjfzqkEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (DjfzqkEnum s : DjfzqkEnum.values()) {
            if (s.getName().equals(name)) {
                return s.getIndex();
            }
        }
        return 99;
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
