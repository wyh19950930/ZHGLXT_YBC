package com.jymj.zhglxt.zjd.bean.zjdyd;

/**
 * Created by ${lc} on 2021/8/4.
 */
public enum HouseTypeEnum {
    zero("原址翻建", 0),
    one("改扩建", 1),
    two("易址新建", 2),
    three("分户新建", 3),
    four("其他", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private HouseTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (HouseTypeEnum s : HouseTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (HouseTypeEnum s : HouseTypeEnum.values()) {
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
