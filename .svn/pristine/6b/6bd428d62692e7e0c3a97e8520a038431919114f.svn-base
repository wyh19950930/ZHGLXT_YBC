package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.公厕管护
 */
public enum GcghEunm {
    zero("公厕设施",1),
    one("公厕卫生、制度上墙", 2),
    ;

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private GcghEunm(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "公厕设施";
        }
        for (GcghEunm s : GcghEunm.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (GcghEunm s : GcghEunm.values()) {
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
