package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.私搭乱建
 */
public enum SdljEnum {
    zero("存量私搭乱建",1),
    one("新增私搭乱建", 2),
    ;

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private SdljEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "存量私搭乱建";
        }
        for (SdljEnum s : SdljEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (SdljEnum s : SdljEnum.values()) {
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
