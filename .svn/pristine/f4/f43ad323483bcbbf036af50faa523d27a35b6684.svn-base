package com.jymj.zhglxt.zjd.bean.qyzt;

/**
 * Created by lc on 2022/5/30.首层承重
 */
public enum ScczEnum {
    zero("小于等于500kg/㎡",0),
    one("500-800kg/㎡",1),
    two("大于800kg/㎡",2),
    three("其他",3),;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    ScczEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ScczEnum s : ScczEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (ScczEnum s : ScczEnum.values()) {
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
