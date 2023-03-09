package com.jymj.zhglxt.zjd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/6/23 20:17
 */
public class PQListBean implements Serializable {


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

        private int gid;
        private String company;
        private String xmmc;
        private String geometry;
        private int id;
        private BigDecimal projAmount; // 已支付
        private BigDecimal cost; //总金额
        private KeyValueEntityBean keyValueEntity;

        public BigDecimal getProjAmount() {
            return projAmount == null ? new BigDecimal(0) : projAmount;
        }

        public void setProjAmount(BigDecimal projAmount) {
            this.projAmount = projAmount;
        }

        public BigDecimal getCost() {
            return cost == null ? new BigDecimal(0) : cost;
        }

        public void setCost(BigDecimal cost) {
            this.cost = cost;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public String getGeometry() {
            return geometry;
        }

        public void setGeometry(String geometry) {
            this.geometry = geometry;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public KeyValueEntityBean getKeyValueEntity() {
            return keyValueEntity;
        }

        public void setKeyValueEntity(KeyValueEntityBean keyValueEntity) {
            this.keyValueEntity = keyValueEntity;
        }

        public static class KeyValueEntityBean {

            private double object1;
            private double object3;
            private double object4;
            private double object5;
            private double object7;
            private double object8;
            private String object9;

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

            public String getObject9() {
                return object9;
            }

            public void setObject9(String object9) {
                this.object9 = object9;
            }
        }
    }
}
