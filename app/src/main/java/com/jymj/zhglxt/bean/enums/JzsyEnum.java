package com.jymj.zhglxt.bean.enums;


/**
 * Created by ljj on 2018/9/26. 居住事由
 */
public enum JzsyEnum  {
    two("务工", 0),
    nine("务农", 1),
    three("经商", 2),
    four("学习培训", 3),
    five("婚嫁", 4),
    one("随迁", 5),
    oq("治病疗养", 6),
    ow("投靠亲友", 7),
    or("录聘用", 8),
    ot("服务", 9);
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
    private JzsyEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (JzsyEnum s : JzsyEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (JzsyEnum s : JzsyEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }
}
