package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ljj on 2018/11/23.
 */
public enum ProjDeclareTextEnum {
    one("政府投资",1),
    two("基础设施", 11),
    nine("社会事业", 12),
    three("其他", 13),
    one2("政府补助",1),
    four("财政补贴类", 21),
    five("政策性金融", 22),
    six("开发性金融", 23),
    one3("社会投资",1),
    seven("股权类", 31),
    eight("债权类", 32),
    night("收益权类", 33),
    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ProjDeclareTextEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ProjDeclareTextEnum s : ProjDeclareTextEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
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
