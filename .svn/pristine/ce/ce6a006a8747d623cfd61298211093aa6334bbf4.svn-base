package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2017/4/6.
 * 注册情况
 */
public enum RegistraEnum {
//    zero("其他",0),
    one("本地注册", 1),
    two("异地注册", 2),
    three("无证无照", 3);
//    four("闲置",4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    RegistraEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (RegistraEnum s : RegistraEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (RegistraEnum s : RegistraEnum.values()) {
            if (s.getName().equals(name)) {
                return s.getIndex();
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
