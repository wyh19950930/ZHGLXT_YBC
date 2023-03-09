package com.jymj.zhglxt.zjd.bean.qyzt;

/**
 * Created by ${lc} on 2021/12/2.
 */
public enum JyztEnum {
    zero("正常营业",1),
    one("停产停业",2),
    two("闲置",3),
    three("未投产",4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private JyztEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (JyztEnum s : JyztEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static int getIndex(String name) {
        for (JyztEnum s : JyztEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 1;
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
