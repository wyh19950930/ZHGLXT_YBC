package com.jymj.zhglxt.zjd.bean.zjdyd;

/**
 * Created by ${lc} on 2021/8/4.
 */
public enum LandTypeEnum {
    zero("建设用地", 0),
    one("未利用地", 1),
    two("农用地(耕地、林地、草地、其他)", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private LandTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (LandTypeEnum s : LandTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (LandTypeEnum s : LandTypeEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
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
