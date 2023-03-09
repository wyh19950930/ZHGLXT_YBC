package com.jymj.zhglxt.bean.enums;

/**
 * Created by ljj on 2017/4/6. 社会关系
 */
public enum SociarateEnum  {
    zero("其他", 0),
    one("户主", 1),
    two("之妻", 2),
    three("之夫", 3),
    four("之父", 4),
    five("之母", 5),
    six("之子", 6),
    seven("之女", 7),
    eight("之儿媳", 8),
    nine("之孙女", 9),
    ten("之孙", 10),
    eleven("之兄", 11),
    teq("之妹", 12),
    te("之外孙", 13),
    ter("之外孙女", 14),
    teu("之岳母", 15),
    tey("之岳父", 16),
    teg("之女婿", 17),
    teh("之姐", 18),
    tel("之嫂", 19),
    tex("之侄", 20),
    tep("之侄女", 21),
    tej("之祖父", 22),
    tet("之孙媳", 23),
    pl("之曾孙", 24),
    pr("之弟", 25),
    pt("之弟媳", 26),
    ct("之祖母", 27),
    pp("之曾孙女", 28),;

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
    private SociarateEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (SociarateEnum s : SociarateEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (SociarateEnum s : SociarateEnum.values()) {
            if (s.getName().equals(name)) {
                return s.getIndex();
            }
        }
        return 0;
    }
}
