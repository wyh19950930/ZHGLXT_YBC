package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ljj on 2017/11/15.
 * 权属性质
 */
public enum QsxzEnum {
    one("国有土地使用权", 20),
    zero("其他", 0),
    two("国有建设用地使用权", 21),
    three("国有农用地使用权",22),
    four("集体土地所有权",30),
    five("村民小组",31),
    six("乡（镇）集体经济组织",32),
    seven("其他农民集体经济组织",34),
    eight("集体土地使用权",40),
    nine("乡（镇）集体建设用地使用权",41),
    ten("村集体建设用地使用权",42),
    eleven("宅基地使用权",43),
    twieve("集体农用地使用权",44);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    QsxzEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (QsxzEnum s : QsxzEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return null;
    }
    public static int getIndex(String name) {
        for (QsxzEnum s : QsxzEnum.values()) {
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
