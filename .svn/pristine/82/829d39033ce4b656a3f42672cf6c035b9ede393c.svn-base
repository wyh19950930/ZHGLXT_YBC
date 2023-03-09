package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.村内外
 */
public enum CnwEnum {
    zero("村内",1),
    one("村外", 2),
    ;

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private CnwEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "村内";
        }
        for (CnwEnum s : CnwEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (CnwEnum s : CnwEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return -1;
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
