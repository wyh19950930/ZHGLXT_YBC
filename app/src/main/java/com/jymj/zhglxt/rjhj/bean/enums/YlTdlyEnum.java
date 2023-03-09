package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ljj on 2017/11/15.
 * 土地来源
 */
public enum YlTdlyEnum {
    one("祖产", 1),
    two("土改分配", 2),
    three("批准拨用",3),
    four("异地搬迁",4),
    five("其他",0),
    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    YlTdlyEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (YlTdlyEnum s : YlTdlyEnum.values()) {
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
