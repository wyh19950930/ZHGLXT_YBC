package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2017/4/6.
 * 房屋用途
 */
public enum FwytEnum {
    zero("住宅",10),
    one("公共设施",22),
    two("仓储",27),
    three("园林绿化",54),
    four("办公",60),
    five("其他",0);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    FwytEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (FwytEnum s : FwytEnum.values()) {
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
