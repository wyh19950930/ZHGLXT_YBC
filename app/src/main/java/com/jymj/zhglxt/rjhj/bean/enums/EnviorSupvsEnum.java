package com.jymj.zhglxt.rjhj.bean.enums;


public enum EnviorSupvsEnum {
//    zer("待完善",1),
    zero("巡查",1),//巡查，考核
    one("下发", 2),
    two("整改",3),
    three("待审核",4),
    four("销账",5)
    ;

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private EnviorSupvsEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (EnviorSupvsEnum s : EnviorSupvsEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (EnviorSupvsEnum s : EnviorSupvsEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 1;
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
