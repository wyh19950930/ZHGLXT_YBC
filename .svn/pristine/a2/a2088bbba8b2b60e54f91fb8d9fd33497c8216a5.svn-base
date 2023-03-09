package com.jymj.zhglxt.rjhj.bean.enums;

/**
 * Created by ${lc} on 2020/11/25. 环境整治二级分类
 */
public enum HjzzejEnum {
    zero("村庄整体环境", 101),
    one("12345投诉未处理", 102),
    two("垃圾收集设施状态", 103),
    three("垃圾分类未执行", 104),
    four("垃圾未处理", 105),
    five("路面及周边乱倒乱扔垃圾", 106),
    sixs("生活污水直排溢流",107),
    six("排水沟堵塞,黑臭水体", 108),
    seven("公厕未开放", 109),
    eight("公厕设施损坏", 110),
    ten("公厕管理", 111),
    eleven("乱堆乱放、垃圾秸秆焚烧和卫生死角", 112),
    elvens("违规广告和残损破旧牌匾标识、乱贴乱画乱挂现象", 113),
    twelve("私搭乱建状态", 114),
    thirteen("村庄环境小问题" , 115),
    eighteen("浴室未开放", 201),
    eighteenm("管理人数不满足2-3人", 202),
    eighteens("管护制度", 203),
    eighteenls("冬季取暖设备、淋浴设施、上下水、洗手池等设施损坏", 204),
    nineteen("卫生清扫不及时，洗浴间地面脏污、淋浴设施、储物柜等设施脏乱差、乱堆杂物", 205),
    nineteens("安全管理", 206),
    nineteenls("管护制度", 301),
    twenty("管护队伍", 302),
    twentyone("专业运输处理", 303),
    twentyones("清掏服务质量", 304),
    twentyonels("管护制度", 401),
    twentytwo("无管护队伍", 402),
    twentythree("修剪、除草、补植情况；浇水施肥、病虫害防治；绿地卫生、设施情况存在不合格", 403),
    twentyfour("无养护记录", 404),
    twentyfive("无管护制度", 501),
    twentysix("无管护队伍", 502),
    twentyseven("亮灯率不足98%", 503),
    twentyeight("亮灯时长不达标", 504),
    twentynine("灯杆及灯杆基础周边、灯盘等外露设施卫生有污垢", 505),
    thirty("巡检维修", 506);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private HjzzejEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

   /* // 普通方法
    public static String getName(int index) {
        for (HjzzejEnum s : HjzzejEnum.values()) {
            if (s.getIndex() == index) {
                return s.name;
            }
        }
        return null;
    }*/
    // 普通方法
    public static Integer getIndex(String name, Integer index) {
        for (HjzzejEnum s : HjzzejEnum.values()) {
            String inde = s.getIndex()+"";
            String substring = inde.substring(0, 1);
            if (s.getName().equals(name)&&inde.equals(index.toString())) {
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
        for (HjzzejEnum s : HjzzejEnum.values()) {
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
