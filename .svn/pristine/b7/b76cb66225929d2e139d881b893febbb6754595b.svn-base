package com.jymj.zhglxt.zjd.bean.cygl;

/**
 * Created by ${lc} on 2021/8/27.
 */
public enum EnterpriseTypeEnum {
    zero("央企",1),
    one("国企",2),
    two("民营",3),
    three("外资",4),;
    // 成员变量
    private String name;
    private Integer index;
    // 构造方法
    private EnterpriseTypeEnum(String name, Integer index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(Integer index) {
        for (EnterpriseTypeEnum s : EnterpriseTypeEnum.values()) {
            if (s.getIndex()==index) {
                return s.name;
            }
        }
        return "";
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (EnterpriseTypeEnum s : EnterpriseTypeEnum.values()) {
            if (s.getName().equals(name)) {
                return s.getIndex();
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
    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }
}
