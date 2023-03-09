package com.jymj.zhglxt.bean.enums;

/**
 * Created by ljj on 2018/11/5. 人员状态
 */
public enum RyztEnum {
    one("正常", 1),
    two("迁出", 2),
    nine("迁入", 3),
    three("入狱", 4),
    four("死亡", 5),
    five("注销", 6),
    six("分户", 7),;
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private RyztEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (RyztEnum s : RyztEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (RyztEnum s : RyztEnum.values()) {
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
