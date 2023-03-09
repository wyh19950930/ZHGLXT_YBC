package com.jymj.zhglxt.zjd.bean.fj;

/**
 * Created by ${lc} on 2020/11/16.环境整治一级分类
 */
public enum FlxcEnum {
    zero("有翻建手续（上传文件 PDF 文件命名）", 1),
    one("修缮维修(上传照片，举证)", 2),
    two("符合规划及建房条件，责令补办手续(上传文件PDF  文件命名)", 3),
    four("不符合规划及建房条件，限期拆除(拆除后照片)", 4),
    five("挂账处理(其他)", 5);;

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private FlxcEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static Integer getIndex(String name) {
        for (FlxcEnum s : FlxcEnum.values()) {
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
        for (FlxcEnum s : FlxcEnum.values()) {
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
