package com.jymj.zhglxt.bean.enums;

/**
 * Created by ${lc} on 2022/2/23.
 */

public enum EmploymentEnum  {
    one("就业", 1),
    two("学生", 2),
    tree("丧失劳动能力", 3),
    four("退休", 4),
    five("从事家务", 5),
    six("正在找工作", 6),
    seven("学龄前儿童", 7),
    eight("其他", 0);
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
    private EmploymentEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (EmploymentEnum s : EmploymentEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (EmploymentEnum s : EmploymentEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }
}
