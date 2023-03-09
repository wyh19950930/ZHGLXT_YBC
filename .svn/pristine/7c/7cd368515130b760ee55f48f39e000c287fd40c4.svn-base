package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.节能路灯维护
 */
public enum Ldwhenum {
    zero("管护制度", 1),
    one("管护队伍", 2),
    two("亮灯率", 3),
    four("亮灯时长", 4),
    five("设施保持完好", 5),
    three("维修及时性", 6),
    ;

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Ldwhenum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "管护制度";
        }
        for (Ldwhenum s : Ldwhenum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (Ldwhenum s : Ldwhenum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return -1;
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
