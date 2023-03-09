package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ljj on 2017/4/6.
 *  就业情况
 */
public enum EmpsituaEnum {
    //其他 = 0,
    //村外乡内,
    //外地就业,
    //无就业,
    //学生,
    //学龄前,
    zero("其他",0),
    one("村外乡内", 1),
    two("外地就业", 2),
    three("无就业", 3),
    four("学生", 4),
    five("学龄前", 5);

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    EmpsituaEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (EmpsituaEnum s : EmpsituaEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (EmpsituaEnum s : EmpsituaEnum.values()) {
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
