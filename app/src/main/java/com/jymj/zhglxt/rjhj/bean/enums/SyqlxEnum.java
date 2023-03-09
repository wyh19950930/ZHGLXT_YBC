package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ljj on 2017/11/15.
 * 使用权类型
 */
public enum SyqlxEnum {
    one("划拨", 11),
    two("出让",12),
    three("入股",13),
    four("租赁",14),
    five("授权经营",15),
    six("荒地拍卖",21),
    seven("拨用宅基地",22),
    eight("拨用企业用地",23),
    nine("农用地承包",24),
    ten("集体土地入股",25),
    eleven("乡镇农民集体所有权",31),
    Twelve("村农民集体所有权",32),
    thirteen("乡镇集体建设用地土地使用权",41),
    fourteen("村集体企事业单位建设用地土地使用权",42),
    fifteen("其他",0);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    SyqlxEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (SyqlxEnum s : SyqlxEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return null;
    }
    public static int getIndex(String name) {
        for (SyqlxEnum s : SyqlxEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
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
