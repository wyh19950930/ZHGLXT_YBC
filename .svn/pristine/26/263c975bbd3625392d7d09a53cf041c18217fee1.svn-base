package com.jymj.zhglxt.bean.enums;

/**
 * Created by ljj on 2018/9/27. 政治面貌
 */
public enum ZzmmEnum {
    one("中共党员", 0),
    two("中共预备党员", 1),
    ni("共青团员", 2),
    three("无党派民主人士", 3),
    four("群众", 4),
    five("民建会员", 5),
    six("民进会员", 6),
    seven("农工党党员", 7),
    eight("致公党党员", 8),
    nine("九三学社社员", 9),
    ten("台盟盟员", 10),
    eleven("民革会员", 11),
    twelve("民盟盟员", 12),;
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
    private ZzmmEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ZzmmEnum s : ZzmmEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (ZzmmEnum s : ZzmmEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }
}
