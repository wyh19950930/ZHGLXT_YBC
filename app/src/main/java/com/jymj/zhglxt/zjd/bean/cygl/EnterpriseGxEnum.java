package com.jymj.zhglxt.zjd.bean.cygl;

/**
 * Created by ${lc} on 2021/8/27.
 */
public enum EnterpriseGxEnum {
    zero("国高新",1),
    one("村高新",2),
    two("其他",3);
    // 成员变量
    private String name;
    private Integer index;
    // 构造方法
    private EnterpriseGxEnum(String name, Integer index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(Integer index) {
        for (EnterpriseGxEnum s : EnterpriseGxEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (EnterpriseGxEnum s : EnterpriseGxEnum.values()) {
            if (s.getName().equals(name)) {
                return s.getIndex();
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
    public Integer getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
