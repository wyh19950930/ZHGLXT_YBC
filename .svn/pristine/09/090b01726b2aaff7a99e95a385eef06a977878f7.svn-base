package com.jymj.zhglxt.zjd.bean.bcjc;

/**
 * Created by ${lc} on 2020/11/16.环境整治一级分类
 */

public enum TypeBzEnum {
    zero(4, 1),
    one(3, 2),
    two(2, 3),
    three(1, 4),
    ;

    // 成员变量
    private int name;
    private int index;

    // 构造方法
    private TypeBzEnum(int name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    /*public static Integer getIndex(String name) {
        for (ZhuShiEnum s : ZhuShiEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return -1;
    }*/

    // 普通方法
    public static int getName(int index) {
        /*if (index==-1){
            return "";
        }*/
        for (TypeBzEnum s : TypeBzEnum.values()) {
            if (s.getIndex() == index ) {
                return s.name;
            }
        }
        return 0;
    }


    // get set 方法
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
