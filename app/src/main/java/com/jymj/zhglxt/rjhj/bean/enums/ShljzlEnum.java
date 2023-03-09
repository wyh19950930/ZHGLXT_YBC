package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.生活垃圾治理
 */
public enum ShljzlEnum {
    zero("垃圾设施满冒、渗漏、污迹或破损",1),
    one("地搓站", 2),
    two("地搓站周边环境管护不到位",3),
    three("积存生活垃圾",4),
    four("建筑垃圾",5),
    five("路边及周边垃圾",6),
    ;

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ShljzlEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "垃圾设施满冒、渗漏、污迹或破损";
        }
        for (ShljzlEnum s : ShljzlEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (ShljzlEnum s : ShljzlEnum.values()) {
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
