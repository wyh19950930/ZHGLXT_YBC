package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by dl on 2019/10/16.
 */
public enum ZjdSqFileEnum {
    one("集体组织成员证明", 6),
    two("分户证明或异地置换证明或政府统一规划批准证明", 7),
    nin("无其他宅基地出卖、出租、赠与、转让证明", 8),
    three("无享受国家保障房证明", 9),
    four("申请书", 10),
    five("其他", 110);

    // 成员变量
    private String name;
    private int index;

    ZjdSqFileEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ZjdSqFileEnum s : ZjdSqFileEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }

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
