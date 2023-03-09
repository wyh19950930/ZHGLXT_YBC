package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ljj on 2019/10/14.
 */
public enum ZjdLzEnum {
    one("申请", 1),
    two("审核", 2),
    nine("备案", 3),
    three("缮证颁证", 4),
    four("驳回", 5);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ZjdLzEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ZjdLzEnum s : ZjdLzEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }

    // get set方法
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
