package com.jymj.zhglxt.rjhj.bean.yl.enums;

/**
 * Created by ljj on 2017/4/6.
 * 经营范围
 */
public enum ScopeBusEnum {
    zero("其他",0),
    one("模板模架租赁", 1),
    two("销售", 2),
    three("门窗", 3),
    four("银、铜基钎料",4),
    five("采暖炉",5),
    six("制造加工",6),
    senven("纸业",7),
    eight("再生资源",8),
    nine("塑料装饰",9),
    ten("水泵技术开发装备",10),
    a("铝板加工销售",11),
    b("轮胎修理",12),
    c("灯具材料",13),
    d("供热煤气",14)
    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    ScopeBusEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ScopeBusEnum s : ScopeBusEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
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
