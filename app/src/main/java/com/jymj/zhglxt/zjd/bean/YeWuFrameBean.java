package com.jymj.zhglxt.zjd.bean;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/5/22 10:07
 */
public class YeWuFrameBean {

    /**
     * msg : null
     * code : 0
     * data : {"envior":{"ongoingCount":0,"completeCount":0,"rejectedCount":null},"tczjd":{"ongoingCount":0,"completeCount":0,"rejectedCount":0},"zjdillegal":{"ongoingCount":0,"completeCount":0,"rejectedCount":null},"tdillegal":{"ongoingCount":0,"completeCount":0,"rejectedCount":null},"lzzjd":{"ongoingCount":0,"completeCount":0,"rejectedCount":0},"sqzjd":{"ongoingCount":0,"completeCount":0,"rejectedCount":0},"fjzjd":{"ongoingCount":0,"completeCount":0,"rejectedCount":0}}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg==null?"":msg;
    }

    public void setMsg(String msg) {
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
         * envior : {"ongoingCount":0,"completeCount":0,"rejectedCount":null}
         * tczjd : {"ongoingCount":0,"completeCount":0,"rejectedCount":0}
         * zjdillegal : {"ongoingCount":0,"completeCount":0,"rejectedCount":null}
         * tdillegal : {"ongoingCount":0,"completeCount":0,"rejectedCount":null}
         * lzzjd : {"ongoingCount":0,"completeCount":0,"rejectedCount":0}
         * sqzjd : {"ongoingCount":0,"completeCount":0,"rejectedCount":0}
         * fjzjd : {"ongoingCount":0,"completeCount":0,"rejectedCount":0}
         */

        private EnviorBean envior;
        private TczjdBean tczjd;
        private ZjdillegalBean zjdillegal;
        private TdillegalBean tdillegal;
        private LzzjdBean lzzjd;
        private SqzjdBean sqzjd;
        private FjzjdBean fjzjd;

        public EnviorBean getEnvior() {
            return envior;
        }

        public void setEnvior(EnviorBean envior) {
            this.envior = envior;
        }

        public TczjdBean getTczjd() {
            return tczjd;
        }

        public void setTczjd(TczjdBean tczjd) {
            this.tczjd = tczjd;
        }

        public ZjdillegalBean getZjdillegal() {
            return zjdillegal;
        }

        public void setZjdillegal(ZjdillegalBean zjdillegal) {
            this.zjdillegal = zjdillegal;
        }

        public TdillegalBean getTdillegal() {
            return tdillegal;
        }

        public void setTdillegal(TdillegalBean tdillegal) {
            this.tdillegal = tdillegal;
        }

        public LzzjdBean getLzzjd() {
            return lzzjd;
        }

        public void setLzzjd(LzzjdBean lzzjd) {
            this.lzzjd = lzzjd;
        }

        public SqzjdBean getSqzjd() {
            return sqzjd==null?new SqzjdBean():sqzjd;
        }

        public void setSqzjd(SqzjdBean sqzjd) {
            this.sqzjd = sqzjd;
        }

        public FjzjdBean getFjzjd() {
            return fjzjd;
        }

        public void setFjzjd(FjzjdBean fjzjd) {
            this.fjzjd = fjzjd;
        }

        public static class EnviorBean {
            /**
             * ongoingCount : 0
             * completeCount : 0
             * rejectedCount : null
             */

            private Integer ongoingCount;
            private Integer completeCount;
            private Integer rejectedCount;

            public Integer getOngoingCount() {
                return ongoingCount==null?0:ongoingCount;
            }

            public void setOngoingCount(Integer ongoingCount) {
                this.ongoingCount = ongoingCount;
            }

            public Integer getCompleteCount() {
                return completeCount==null?0:completeCount;
            }

            public void setCompleteCount(Integer completeCount) {
                this.completeCount = completeCount;
            }

            public Integer getRejectedCount() {
                return rejectedCount==null?0:rejectedCount;
            }

            public void setRejectedCount(Integer rejectedCount) {
                this.rejectedCount = rejectedCount;
            }
        }

        public static class TczjdBean {
            /**
             * ongoingCount : 0
             * completeCount : 0
             * rejectedCount : 0
             */

            private Integer ongoingCount;
            private Integer completeCount;
            private Integer rejectedCount;

            public Integer getOngoingCount() {
                return ongoingCount==null?0:ongoingCount;
            }

            public void setOngoingCount(Integer ongoingCount) {
                this.ongoingCount = ongoingCount;
            }

            public Integer getCompleteCount() {
                return completeCount==null?0:completeCount;
            }

            public void setCompleteCount(Integer completeCount) {
                this.completeCount = completeCount;
            }

            public Integer getRejectedCount() {
                return rejectedCount==null?0:rejectedCount;
            }

            public void setRejectedCount(Integer rejectedCount) {
                this.rejectedCount = rejectedCount;
            }
        }

        public static class ZjdillegalBean {
            /**
             * ongoingCount : 0
             * completeCount : 0
             * rejectedCount : null
             */

            private Integer ongoingCount;
            private Integer completeCount;
            private Integer rejectedCount;

            public Integer getOngoingCount() {
                return ongoingCount==null?0:ongoingCount;
            }

            public void setOngoingCount(Integer ongoingCount) {
                this.ongoingCount = ongoingCount;
            }

            public Integer getCompleteCount() {
                return completeCount==null?0:completeCount;
            }

            public void setCompleteCount(Integer completeCount) {
                this.completeCount = completeCount;
            }

            public Integer getRejectedCount() {
                return rejectedCount==null?0:rejectedCount;
            }

            public void setRejectedCount(Integer rejectedCount) {
                this.rejectedCount = rejectedCount;
            }
        }

        public static class TdillegalBean {
            /**
             * ongoingCount : 0
             * completeCount : 0
             * rejectedCount : null
             */

            private Integer ongoingCount;
            private Integer completeCount;
            private Integer rejectedCount;

            public Integer getOngoingCount() {
                return ongoingCount==null?0:ongoingCount;
            }

            public void setOngoingCount(Integer ongoingCount) {
                this.ongoingCount = ongoingCount;
            }

            public Integer getCompleteCount() {
                return completeCount==null?0:completeCount;
            }

            public void setCompleteCount(Integer completeCount) {
                this.completeCount = completeCount;
            }

            public Integer getRejectedCount() {
                return rejectedCount==null?0:rejectedCount;
            }

            public void setRejectedCount(Integer rejectedCount) {
                this.rejectedCount = rejectedCount;
            }
        }

        public static class LzzjdBean {
            /**
             * ongoingCount : 0
             * completeCount : 0
             * rejectedCount : 0
             */

            private Integer ongoingCount;
            private Integer completeCount;
            private Integer rejectedCount;

            public Integer getOngoingCount() {
                return ongoingCount==null?0:ongoingCount;
            }

            public void setOngoingCount(Integer ongoingCount) {
                this.ongoingCount = ongoingCount;
            }

            public Integer getCompleteCount() {
                return completeCount==null?0:completeCount;
            }

            public void setCompleteCount(Integer completeCount) {
                this.completeCount = completeCount;
            }

            public Integer getRejectedCount() {
                return rejectedCount==null?0:rejectedCount;
            }

            public void setRejectedCount(Integer rejectedCount) {
                this.rejectedCount = rejectedCount;
            }
        }

        public static class SqzjdBean {
            /**
             * ongoingCount : 0
             * completeCount : 0
             * rejectedCount : 0
             */

            private Integer ongoingCount;
            private Integer completeCount;
            private Integer rejectedCount;

            public Integer getOngoingCount() {
                return ongoingCount==null?0:ongoingCount;
            }

            public void setOngoingCount(Integer ongoingCount) {
                this.ongoingCount = ongoingCount;
            }

            public Integer getCompleteCount() {
                return completeCount==null?0:completeCount;
            }

            public void setCompleteCount(Integer completeCount) {
                this.completeCount = completeCount;
            }

            public Integer getRejectedCount() {
                return rejectedCount==null?0:rejectedCount;
            }

            public void setRejectedCount(Integer rejectedCount) {
                this.rejectedCount = rejectedCount;
            }
        }

        public static class FjzjdBean {
            /**
             * ongoingCount : 0
             * completeCount : 0
             * rejectedCount : 0
             */

            private Integer ongoingCount;
            private Integer completeCount;
            private Integer rejectedCount;

            public Integer getOngoingCount() {
                return ongoingCount==null?0:ongoingCount;
            }

            public void setOngoingCount(Integer ongoingCount) {
                this.ongoingCount = ongoingCount;
            }

            public Integer getCompleteCount() {
                return completeCount==null?0:completeCount;
            }

            public void setCompleteCount(Integer completeCount) {
                this.completeCount = completeCount;
            }

            public Integer getRejectedCount() {
                return rejectedCount==null?0:rejectedCount;
            }

            public void setRejectedCount(Integer rejectedCount) {
                this.rejectedCount = rejectedCount;
            }
        }
    }
}
