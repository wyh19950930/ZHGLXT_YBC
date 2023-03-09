package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/16.太阳能浴室运行维护
 */
public enum YswhEnum {
    zero("专人管理", 1),
    one("管护制度", 2),
    two("浴室设施保持", 3),
    four("浴室环境卫生", 4),
    five("技术管理", 5),
    ;

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private YswhEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        if (index==-1){
            return "专人管理";
        }
        for (YswhEnum s : YswhEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (YswhEnum s : YswhEnum.values()) {
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
