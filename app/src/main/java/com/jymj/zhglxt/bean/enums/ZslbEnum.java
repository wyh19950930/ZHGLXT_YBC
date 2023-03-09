package com.jymj.zhglxt.bean.enums;


/**
 * Created by ljj on 2018/9/27. 住所类别
 */
public enum ZslbEnum {
    one("自购房屋", 1),
    two("租赁房屋", 2),
    ni("寄宿", 3),
    three("借住", 4),
    four("单位宿舍", 5),
    five("建筑工地", 6),
    six("工作场所", 7),
    seven("学校内部", 8),
    ti("其他", 0),;
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
    private ZslbEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ZslbEnum s : ZslbEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (ZslbEnum s : ZslbEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }
}
