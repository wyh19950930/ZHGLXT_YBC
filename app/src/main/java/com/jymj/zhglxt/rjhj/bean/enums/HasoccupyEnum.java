package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ljj on 2017/12/14.
 */
public enum HasoccupyEnum {
    one("完全", 1),
    two("部分",2),
    three("无",3),
    four("其他",0);

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    HasoccupyEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (HasoccupyEnum s : HasoccupyEnum.values()) {
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
