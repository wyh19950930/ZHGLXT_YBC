package com.jymj.zhglxt.bean.enums;

/**
 * Created by ljj on 2018/9/27. 宗教信仰
 */
public enum ZjxyEnum {
    one("无宗教信仰", 0),
    two("佛教", 10),
    ni("道教", 30),
    three("天主教", 40),
    four("基督教", 50),
    five("伊斯兰教", 70),
    ti("其他", 99),;
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ZjxyEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ZjxyEnum s : ZjxyEnum.values()) {
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
