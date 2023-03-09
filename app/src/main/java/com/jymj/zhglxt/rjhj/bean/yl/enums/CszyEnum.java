package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2018/9/26.
 * 从事职业
 */
public enum CszyEnum {
    qq("未知",0),
    zero("公共事业", 1),
    one("农林牧渔", 2),
    two("加工制造业", 3),
    three("个体工商户", 4),
    five("建筑工程业", 5),
    sex("餐饮服务业", 6),
    ti("其他职业", 19),
    fu("失业", 20),
    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private CszyEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (CszyEnum s : CszyEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (CszyEnum s : CszyEnum.values()) {
            if (s.getName().equals(name)) {
                return s.getIndex();
            }
        }
        return 0;
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
