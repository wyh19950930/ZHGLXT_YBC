package com.jymj.zhglxt.rjhj.bean.yl;

/**
 * Created by ljj on 2018/9/10.
 */
public enum ApplyFileEnum {
    /*one("翻建申请书", 1),
    two("四邻图", 2), //测绘图
    three("户口本", 3),
    four("身份证", 4),
    //nine("宅基地示意图", 5),
    five("四邻协议", 6),
    six("村两委会签署意见表", 7),
    seven("村民宅基地翻建公告", 8),
    ten("村民翻建宅基地承诺书", 9),
    tey("村委会关于本村宅基地翻建申请", 10),
    y1("院内(前)", 11),
    y2("院内(后)", 12),
    y3("院外(前)", 13),
    y4("院外(后)", 14),
    nine("宅基地审批通知书", 19),
    jj("施工人身份证", 20),
    jq("施工方营业执照", 21),
    jt("施工中照片", 22),
    zero("其他", 0),*/
    one("翻建承诺书", 1),
    two("申请表", 2), //测绘图
    three("身份证", 3),
    four("户口本", 4),
    nine("房屋照片", 5),
    five("现场公告", 6),
    six("签字意见表", 7),
    seven("通过申请表", 8),
    ten("驳回申请表", 9),
    tey("验收现场照片", 10),
    y1("验收资料", 11),
    y2("确权登记", 12),
    y3("位置图", 13),
    y4("分户证明", 14),
    y5("权属证明", 15),
    y6("四邻意见", 16),
    zero("其他", 0);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ApplyFileEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ApplyFileEnum s : ApplyFileEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return "";
    }
    public static int getIndex(String name) {
        for (ApplyFileEnum s : ApplyFileEnum.values()) {
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
