package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2017/5/3.
 * 建筑结构
 */
public enum BuStracEnum {
    zero("其他",0),
    one("木结构", 1),
    two("砖混结构", 2),
    three("钢筋混凝土结构", 3),
    four("钢结构", 4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    BuStracEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (BuStracEnum s : BuStracEnum.values()) {
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
