package com.jymj.zhglxt.bean.enums;


/**
 * Created by ljj on 2018/9/26. 文化程度
 */
public enum WhcdEnum {
    one("研究生及以上", 0),
    two("大学本科", 1),
    nine("大学专科", 2),
    three("高中", 3),
    four("初中", 4),
    five("小学", 5),
    six("不识字", 6),;
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
    private WhcdEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (WhcdEnum s : WhcdEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (WhcdEnum s : WhcdEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }
}
