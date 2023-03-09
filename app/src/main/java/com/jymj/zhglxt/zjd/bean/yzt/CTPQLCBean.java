package com.jymj.zhglxt.zjd.bean.yzt;

import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/6/24 11:49
 */
public class CTPQLCBean {

    /**
     * msg : null
     * code : 0
     * data : [{"object1":1,"object2":40},{"object1":6,"object2":436.3}]
     */

    private Object msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * object1 : 1
         * object2 : 40
         */

        private int object1;
        private double object2;

        public int getObject1() {
            return object1;
        }

        public void setObject1(int object1) {
            this.object1 = object1;
        }

        public double getObject2() {
            return object2;
        }

        public void setObject2(double object2) {
            this.object2 = object2;
        }
    }
}
