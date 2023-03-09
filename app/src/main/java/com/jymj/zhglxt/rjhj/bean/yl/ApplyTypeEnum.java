package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ljj on 2018/9/10.
 */
public enum ApplyTypeEnum {
    one("申请表", 1),
    two("审核", 2), //测绘图
    three("审批", 3),
    four("通过", 4),
    nine("驳回", 5),
    five("验收", 6),
    six("确权", 7),
    zero("其他", 0);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ApplyTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ApplyTypeEnum s : ApplyTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (ApplyTypeEnum s : ApplyTypeEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
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
