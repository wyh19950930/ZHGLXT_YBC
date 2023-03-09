package com.jymj.zhglxt.zjd.bean.jsjb;

public class JsjbTjBean {

    public int object1;
    public int object2;
    public int object3;
    public int object4;

    public JsjbTjBean() {
    }

    public JsjbTjBean(int object1, int object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    public int getObject1() {
        return object1;
    }

    public void setObject1(int object1) {
        this.object1 = object1;
    }
    public String getObject1Name() {
        if (object1==1){
            return "待签收";
        }else if (object1==2){
            return "待反馈";
        }else if (object1==3){
            return "待审核";
        }else if (object1==4){
            return "已归档";
        }else if (object1==5){
            return "退单";
        }else if (object1==6){
            return "延时申请";
        }else if (object1==200){
            return "逾期";
        }
        return "";
    }

    public int getObject2() {
        return object2;
    }

    public void setObject2(int object2) {
        this.object2 = object2;
    }

    public int getObject3() {
        return object3;
    }

    public void setObject3(int object3) {
        this.object3 = object3;
    }

    public int getObject4() {
        return object4;
    }

    public void setObject4(int object4) {
        this.object4 = object4;
    }
}
