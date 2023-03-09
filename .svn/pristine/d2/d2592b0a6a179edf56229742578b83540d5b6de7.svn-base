package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.环境整治一级分类
 */
public enum HjzzflEnum {
    zero("人居环境检查", 1),
    one("太阳能浴室", 2),
    two("公厕管护", 3),
    four("绿化养护", 4),
    five("节能路灯", 5),
    six("农村大集环境检查", 6),
    six1("户厕管护", 8),
    seven("垃圾暂存点", 7);;

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private HjzzflEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (HjzzflEnum s : HjzzflEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return -1;
    }

    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "";
        }
        for (HjzzflEnum s : HjzzflEnum.values()) {
            if (s.getIndex() == index) {
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
