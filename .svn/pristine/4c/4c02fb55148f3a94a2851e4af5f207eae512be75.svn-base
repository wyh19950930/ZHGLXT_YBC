package com.jymj.zhglxt.zjd.bean.bcjc;

/**
 * Created by ${lc} on 2020/11/16.环境整治一级分类
 */

public enum TypeLxEnum {
    zero("村", 1),
    one("镇", 2),
    two("区", 3),
    three("市", 4),
    ;

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private TypeLxEnum(String name, int index) {
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
    public static String getName(int index) {
        /*if (index==-1){
            return "";
        }*/
        for (TypeLxEnum s : TypeLxEnum.values()) {
            if (s.getIndex() == index ) {
                return s.name;
            }
        }
        return "";
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
