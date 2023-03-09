package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.绿化养护
 */
public enum LhyhEnum {
    zero("管护制度", 1),
    one("地搓站", 2),
    two("日常管护内容", 3),
    four("养护记录", 4),
    ;

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private LhyhEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "管护制度";
        }
        for (LhyhEnum s : LhyhEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (LhyhEnum s : LhyhEnum.values()) {
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
