package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ljj on 2018/9/10.
 */
public enum ApplyTypeNumEnum {
    one("申请", 1),
    two("审核", 2), //需要上传审批的内容
    three("通过", 3),//仅用于查看审批  因手机端不做操作所以不用显示下一步
    four("驳回", 4),//当前步骤需要上传验收资料所以需要提前显示验收
    five("验收", 5),//当前步骤需要上传验收资料所以需要提前显示验收
    six("确权", 6),//当前步骤需要上传验收资料所以需要提前显示验收
    six1("审批", 7),
    zero("其他", 0);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ApplyTypeNumEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ApplyTypeNumEnum s : ApplyTypeNumEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (ApplyTypeNumEnum s : ApplyTypeNumEnum.values()) {
            if (s.getName().equals(name)) {
                return s.index;
            }
        }
        return 0;
    }

    // get set方法
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
