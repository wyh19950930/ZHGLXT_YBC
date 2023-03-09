package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ljj on 2018/9/10.
 */
public enum ApplyChildEnum {
    zero("审批通过，开始翻建吧！", 70),
    one("开始施工，完善施工信息！", 71),
    two("记录施工进度", 72),
    nine("施工完成，提交完工照片！", 73),
    three("已完成备案", 74),
    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ApplyChildEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ApplyChildEnum s : ApplyChildEnum.values()) {
            if (s.getIndex()==index) {
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
