package com.jymj.zhglxt.zjd.bean.qyzt;

/**
 * Created by lc on 2022/5/30.首层面积
 */
public enum ScmjEnum {
    zero("0-1000平方米",0),
    one("1000-2000平方米",1),
    two("2000平方米以上",2),
    three("其他",3),;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    ScmjEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ScmjEnum s : ScmjEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (ScmjEnum s : ScmjEnum.values()) {
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
