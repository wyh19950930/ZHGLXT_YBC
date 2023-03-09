package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ljj on 2018/9/10.
 */
public enum ApplyEnum {
    two("备案资料", 1),
    nine("待审核", 2),
    three("审核通过", 3),
    four("审核驳回", 5),
    five("施工", 6),
    six("待验收", 7),
    seven("验收", 8),;
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ApplyEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ApplyEnum s : ApplyEnum.values()) {
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
