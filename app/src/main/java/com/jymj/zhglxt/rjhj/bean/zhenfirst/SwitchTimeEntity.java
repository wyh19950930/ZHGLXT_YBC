package com.jymj.zhglxt.rjhj.bean.zhenfirst;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/1/25 9:27
 */
public class SwitchTimeEntity {

    /**
     * month : {"total":0,"rate":0,"list":[{"done":0,"index":0,"total":0}]}
     * year : {"total":28811,"rate":0.9610912498698414,"list":[{"done":3047,"index":1,"total":3144},{"done":3349,"index":2,"total":3481},{"done":4178,"index":3,"total":4302},{"done":4042,"index":4,"total":4116},{"done":4813,"index":5,"total":4856},{"done":5427,"index":6,"total":5589},{"done":0,"index":7,"total":0},{"done":0,"index":8,"total":0},{"done":0,"index":9,"total":0},{"done":0,"index":10,"total":0},{"done":0,"index":11,"total":0},{"done":0,"index":12,"total":0}]}
     * season : {"total":14250,"rate":0.9409122807017544,"list":[{"done":3047,"index":1,"total":3144},{"done":3349,"index":2,"total":3481},{"done":4178,"index":3,"total":4302}]}
     */

    private MonthBean month;
    private YearBean year;
    private SeasonBean season;

    public MonthBean getMonth() {
        return month;
    }

    public void setMonth(MonthBean month) {
        this.month = month;
    }

    public YearBean getYear() {
        return year;
    }

    public void setYear(YearBean year) {
        this.year = year;
    }

    public SeasonBean getSeason() {
        return season;
    }

    public void setSeason(SeasonBean season) {
        this.season = season;
    }
    public static class ListBean {
        /**
         * done : 0
         * index : 0
         * total : 0
         */

        private int done;
        private int index;
        private int total;

        public int getDone() {
            return done;
        }

        public void setDone(int done) {
            this.done = done;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
    public static class MonthBean {
        /**
         * total : 0
         * rate : 0
         * list : [{"done":0,"index":0,"total":0}]
         */

        private int total;
        private BigDecimal rate;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public BigDecimal getRate() {
            return rate==null? BigDecimal.ZERO:rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }


    }

    public static class YearBean {
        /**
         * total : 28811
         * rate : 0.9610912498698414
         * list : [{"done":3047,"index":1,"total":3144},{"done":3349,"index":2,"total":3481},{"done":4178,"index":3,"total":4302},{"done":4042,"index":4,"total":4116},{"done":4813,"index":5,"total":4856},{"done":5427,"index":6,"total":5589},{"done":0,"index":7,"total":0},{"done":0,"index":8,"total":0},{"done":0,"index":9,"total":0},{"done":0,"index":10,"total":0},{"done":0,"index":11,"total":0},{"done":0,"index":12,"total":0}]
         */

        private int total;
        private BigDecimal rate;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public BigDecimal getRate() {
            return rate==null? BigDecimal.ZERO:rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public List<ListBean> getList() {
            return list==null?new ArrayList<>():list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * done : 3047
             * index : 1
             * total : 3144
             */

            private int done;
            private int index;
            private int total;

            public int getDone() {
                return done;
            }

            public void setDone(int done) {
                this.done = done;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }
    }

    public static class SeasonBean {
        /**
         * total : 14250
         * rate : 0.9409122807017544
         * list : [{"done":3047,"index":1,"total":3144},{"done":3349,"index":2,"total":3481},{"done":4178,"index":3,"total":4302}]
         */

        private int total;
        private BigDecimal rate;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public BigDecimal getRate() {
            return rate==null? BigDecimal.ZERO:rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public List<ListBean> getList() {
            return list==null?new ArrayList<>():list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBeanXX {
            /**
             * done : 3047
             * index : 1
             * total : 3144
             */

            private int done;
            private int index;
            private int total;

            public int getDone() {
                return done;
            }

            public void setDone(int done) {
                this.done = done;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }
    }
}
