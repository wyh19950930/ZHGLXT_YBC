package com.jymj.zhglxt.zjd.bean.zjdyd;

/**
 * Created by ${lc} on 2021/8/4.
 */
public enum CzqkintEnum {
    zero("保留", 1),
    one("退给村集体", 2),
    two("其他", 3);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private CzqkintEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (CzqkintEnum s : CzqkintEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (CzqkintEnum s : CzqkintEnum.values()) {
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
