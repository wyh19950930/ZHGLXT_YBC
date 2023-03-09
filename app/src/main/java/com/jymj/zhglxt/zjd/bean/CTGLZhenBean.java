package com.jymj.zhglxt.zjd.bean;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/6/24 15:58
 * 拆腾镇概况
 */
public class CTGLZhenBean {


    /**
     * msg : null
     * code : 0
     * data : {"object1":279.2,"object3":2028.3,"object4":2307.5,"object5":279.2,"object7":2307.5,"object8":2.307548236E7}
     */

    private Object msg;
    private int code;
    private DataBean data;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * object1 : 279.2
         * object3 : 2028.3
         * object4 : 2307.5
         * object5 : 279.2
         * object7 : 2307.5
         * object8 : 2.307548236E7
         */

        private double object1;
        private double object3;
        private double object4;
        private double object5;
        private double object7;
        private double object8;

        public double getObject1() {
            return object1;
        }

        public void setObject1(double object1) {
            this.object1 = object1;
        }

        public double getObject3() {
            return object3;
        }

        public void setObject3(double object3) {
            this.object3 = object3;
        }

        public double getObject4() {
            return object4;
        }

        public void setObject4(double object4) {
            this.object4 = object4;
        }

        public double getObject5() {
            return object5;
        }

        public void setObject5(double object5) {
            this.object5 = object5;
        }

        public double getObject7() {
            return object7;
        }

        public void setObject7(double object7) {
            this.object7 = object7;
        }

        public double getObject8() {
            return object8;
        }

        public void setObject8(double object8) {
            this.object8 = object8;
        }
    }
}
