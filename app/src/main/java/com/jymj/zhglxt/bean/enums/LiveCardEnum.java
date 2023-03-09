package com.jymj.zhglxt.bean.enums;


/**
 * Created by ${lc} on 2022/2/17. 居住证件
 */
public enum LiveCardEnum  {
    one("无", 0),
    two("登记卡", 1),
    three("居住证", 2);
    // 成员变量
    private String name;
    private int index;

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

    // 构造方法
    private LiveCardEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (LiveCardEnum s : LiveCardEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }

}
