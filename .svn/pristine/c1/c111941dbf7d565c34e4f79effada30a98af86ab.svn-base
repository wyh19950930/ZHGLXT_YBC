package com.jymj.zhglxt.zjd.bean.qyzt;

/**
 * Created by lc on 2022/5/30.首层层高
 */
public enum SccgEnum {
    zero("0-5米",0),
    one("5-8米",1),
    two("8米以上",2),
    three("其他",3),;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    SccgEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (SccgEnum s : SccgEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (SccgEnum s : SccgEnum.values()) {
            if (s.getName().equals(name)) {
                return s.getIndex();
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
