package com.jymj.zhglxt.bean.enums;

/**
 * Created by ljj on 2017/7/10.
 */

public enum NationEnum  {
    q("其他", 0),
    one("汉族", 1),
    two("壮族", 2),
    three("满族", 3),
    four("回族 ", 4),
    five("苗族 ", 5),
    six("维吾尔族", 6),
    seven("彝族", 7),
    eight("土家族", 8),
    nine("蒙古族", 9),
    ten("藏族", 10),
    eleven("布依族", 11),
    twelve("侗族", 12),
    thirteen("瑶族", 13),
    fourteen("朝鲜族", 14),
    fifteen("白族", 15),
    sixteen("哈尼族", 16),
    seventeen("哈萨克族", 17),
    eighteen("黎族", 18),
    nineteen("傣族", 19),
    twenty("畲族", 20),
    twentyone("傈僳族", 21),
    twentytwo("拉祜族", 22),
    twentythree("东乡族", 23),
    twentyfour("佤族", 24),
    twentyfive("水族", 25),
    twentysix("纳西族", 26),
    twentyseven("土族", 27),
    twentyeight("前南关村", 28),
    twentynine("羌族", 29),
    thirty("锡伯族", 30),
    thirtyone("仫佬族", 31),
    thirtytwo("柯尔克孜族", 32),
    thirtythree("达斡尔族", 33),
    thirtyfour("景颇族", 34),
    thirtyfive("撒拉族", 35),
    thirtysix("布朗族", 36),
    thirtyseven("毛南族", 37),
    thirtyeight("仡佬族", 38),
    thirtynine("塔吉克族", 39),
    forty("普米族", 40),
    fortyone("阿昌族", 41),
    fortytwo("怒族", 42),
    fortythree("鄂温克族", 43),
    fortyfour("京族", 44),
    fortyfive("基诺族", 45),
    fortysix("德昂族", 46),
    fortyseven("乌孜别克族", 47),
    fortyeight("俄罗斯族", 48),
    fortynine("裕固族", 49),
    fifty("保安族", 50),
    fiftyone("门巴族", 51),
    fiftytwo("鄂伦春族", 52),
    fiftythree("独龙族", 53),
    fiftyfour("塔塔尔族", 54),
    fiftyfive("赫哲族", 55),
    fiftysix("高山族", 56);
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
    private NationEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (NationEnum s : NationEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }

        return "";
    }

    public static int getIndex(String name) {
        for (NationEnum s : NationEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }

}
