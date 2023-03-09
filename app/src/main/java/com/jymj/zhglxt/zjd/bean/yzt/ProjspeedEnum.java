package com.jymj.zhglxt.zjd.bean.yzt;

/**
 * Created by ljj on 2020/6/22.
 * 拆腾项目步骤
 */
public enum ProjspeedEnum {
    one("未动工", 1),
    two("入户清登", 2),
    three("交房",3),
    four("挑顶",4),
    five("拆腾",5),
    six("场地清平",6),
    seven("销账",7),
    ;
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ProjspeedEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ProjspeedEnum s : ProjspeedEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (ProjspeedEnum s : ProjspeedEnum.values()) {
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
