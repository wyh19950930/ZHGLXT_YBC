package com.jymj.zhglxt.zjd.bean.yzt.frame_mx;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/7/2 16:10
 */
public class FrameYLMXBean {


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


        private String code;
        private int limit;
        private int page;
        private String points;
        private int offset;
        private Object sidx;
        private Object order;
        private int ylCount;
        private List<Integer> ylTypeList;
        private List<YlEntityListBean> ylEntityList;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public Object getSidx() {
            return sidx;
        }

        public void setSidx(Object sidx) {
            this.sidx = sidx;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
        }

        public int getYlCount() {
            return ylCount;
        }

        public void setYlCount(int ylCount) {
            this.ylCount = ylCount;
        }

        public List<Integer> getYlTypeList() {
            return ylTypeList;
        }

        public void setYlTypeList(List<Integer> ylTypeList) {
            this.ylTypeList = ylTypeList;
        }

        public List<YlEntityListBean> getYlEntityList() {
            return ylEntityList;
        }

        public void setYlEntityList(List<YlEntityListBean> ylEntityList) {
            this.ylEntityList = ylEntityList;
        }

        public static class YlEntityListBean {
            /**
             * gid : 89043
             * area : 535
             * jianzhuArea : 348
             * name : 郝淑清
             * remark : 一户多宅，与208号同一产权人
             * mph : null
             * ylType : 1
             * ylTypeText : 宅基地
             * legent : 1
             * isidle : 0
             * isidleText : 否
             * xzqmc : 鹅房村
             * objectid : 91181
             * pzmj : 0
             * bh : 0
             * ysbh : null
             * pzbm : null
             * pzwh : null
             * pzsj : null
             * pzyt : null
             * qydj : 0
             * rk : 0
             * qsxz : 0
             * qsxzText : 其他
             * syqlx : 0
             * syqlxText : 其他
             * tdly : 0
             * tdlyText : 其他
             * hasoccupy : 0
             * hasoccupyText : 其他
             * cqly : 7
             * cqlyText : null
             * code : 110115015005
             * zhaiid : 0
             * zhaiName : null
             * hzmc : null
             * yllegent : 4
             * center : POINT(116.275496385244 39.7551299366046)
             * tsqk : 1
             * rjl : 0.4
             * dlr : 0
             * dlrname : null
             * geometry : MULTIPOLYGON(((116.275571729155 39.7552312279005,116.275667078138 39.7550700496595,116.27543040425 39.7549851953682,116.275404571553 39.7550274638915,116.275400895328 39.7550260737633,116.275350023481 39.7551065930959,116.275403726595 39.7551271196599,116.275378945656 39.7551691581243,116.275376827308 39.755168476085,116.275351208266 39.7552159317494,116.275546110333 39.7552785845379,116.275571729155 39.7552312279005)))
             * nongcount : 0
             * feinongcount : 0
             * zjdcount : null
             * hjmj : null
             * hucount : 0
             * apptype : 0
             * apptypeText : null
             * sqtype : 0
             * lztype : 0
             * tctype : 0
             * qsxzList : null
             * syqlxList : null
             * cqlyList : null
             * ldArea : 0.8
             * ldRk : 0
             * ldQdfs : 0.4
             * ldRjl : 0.65
             * ldDjgx : 0
             * fdq4 : 0
             * fdq46 : 0
             * fdq68 : 0
             * fdq8 : 0
             * fdqzmj : 0
             * fdq4count : 0
             * fdq46count : 0
             * fdq68count : 0
             * fdq8count : 0
             * fdqcount : 0
             * nan : 0
             * nv : 0
             * ds : 0
             * zzcy : 0
             * applyEntity : null
             * zjdSqEntity : null
             * zjdLzEntity : null
             * zjdTcEntity : null
             * syqk : 6
             * jcnd : 1
             * jzjg : 3
             * jzcs : 2
             * czmj : null
             * czjg : null
             * yllegentText : 8分-1亩
             * tsqkText : ["一户多宅"]
             * syqkText : null
             * jcndText : 1982年前
             * jzjgText : 钢筋混凝土结构
             * jzcsText : 2层
             */

            private int gid;
            private BigDecimal area;
            private BigDecimal jianzhuArea;
            private String name;
            private String remark;
            private String mph;
            private int ylType;
            private String ylTypeText;
            private int legent;
            private int isidle;
            private String isidleText;
            private String xzqmc;
            private int objectid;
            private int pzmj;
            private String bh;
            private Object ysbh;
            private Object pzbm;
            private Object pzwh;
            private Object pzsj;
            private Object pzyt;
            private int qydj;
            private int rk;
            private int qsxz;
            private String qsxzText;
            private int syqlx;
            private String syqlxText;
            private int tdly;
            private String tdlyText;
            private int hasoccupy;
            private String hasoccupyText;
            private int cqly;
            private Object cqlyText;
            private String code;
            private int zhaiid;
            private Object zhaiName;
            private Object hzmc;
            private int yllegent;
            private String center;
            private String tsqk;
            private double rjl;
            private int dlr;
            private Object dlrname;
            private String geometry;
            private int nongcount;
            private int feinongcount;
            private Object zjdcount;
            private Object hjmj;
            private int hucount;
            private int apptype;
            private Object apptypeText;
            private int sqtype;
            private int lztype;
            private int tctype;
            private Object qsxzList;
            private Object syqlxList;
            private Object cqlyList;
            private double ldArea;
            private double ldRk;
            private double ldQdfs;
            private double ldRjl;
            private double ldDjgx;
            private int fdq4;
            private int fdq46;
            private int fdq68;
            private int fdq8;
            private int fdqzmj;
            private int fdq4count;
            private int fdq46count;
            private int fdq68count;
            private int fdq8count;
            private int fdqcount;
            private int nan;
            private int nv;
            private int ds;
            private int zzcy;
            private Object applyEntity;
            private Object zjdSqEntity;
            private Object zjdLzEntity;
            private Object zjdTcEntity;
            private int syqk;
            private int jcnd;
            private int jzjg;
            private int jzcs;
            private Object czmj;
            private Object czjg;
            private String yllegentText;
            private Object syqkText;
            private String jcndText;
            private String jzjgText;
            private String jzcsText;
            private List<String> tsqkText;
            private int ldrks;

            public int getLdrks() {
                return ldrks;
            }

            public void setLdrks(int ldrks) {
                this.ldrks = ldrks;
            }

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public BigDecimal getArea() {
                return area == null?new BigDecimal(0):area;
            }

            public void setArea(BigDecimal area) {
                this.area = area;
            }

            public BigDecimal getJianzhuArea() {
                return jianzhuArea== null?new BigDecimal(0):jianzhuArea;
            }

            public void setJianzhuArea(BigDecimal jianzhuArea) {
                this.jianzhuArea = jianzhuArea;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getMph() {
                return mph == null ? "暂无数据":mph;
            }

            public void setMph(String mph) {
                this.mph = mph;
            }

            public int getYlType() {
                return ylType;
            }

            public void setYlType(int ylType) {
                this.ylType = ylType;
            }

            public String getYlTypeText() {
                return ylTypeText;
            }

            public void setYlTypeText(String ylTypeText) {
                this.ylTypeText = ylTypeText;
            }

            public int getLegent() {
                return legent;
            }

            public void setLegent(int legent) {
                this.legent = legent;
            }

            public int getIsidle() {
                return isidle;
            }

            public void setIsidle(int isidle) {
                this.isidle = isidle;
            }

            public String getIsidleText() {
                return isidleText;
            }

            public void setIsidleText(String isidleText) {
                this.isidleText = isidleText;
            }

            public String getXzqmc() {
                return xzqmc;
            }

            public void setXzqmc(String xzqmc) {
                this.xzqmc = xzqmc;
            }

            public int getObjectid() {
                return objectid;
            }

            public void setObjectid(int objectid) {
                this.objectid = objectid;
            }

            public int getPzmj() {
                return pzmj;
            }

            public void setPzmj(int pzmj) {
                this.pzmj = pzmj;
            }

            public String getBh() {
                return bh;
            }

            public void setBh(String bh) {
                this.bh = bh;
            }

            public Object getYsbh() {
                return ysbh;
            }

            public void setYsbh(Object ysbh) {
                this.ysbh = ysbh;
            }

            public Object getPzbm() {
                return pzbm;
            }

            public void setPzbm(Object pzbm) {
                this.pzbm = pzbm;
            }

            public Object getPzwh() {
                return pzwh;
            }

            public void setPzwh(Object pzwh) {
                this.pzwh = pzwh;
            }

            public Object getPzsj() {
                return pzsj;
            }

            public void setPzsj(Object pzsj) {
                this.pzsj = pzsj;
            }

            public Object getPzyt() {
                return pzyt;
            }

            public void setPzyt(Object pzyt) {
                this.pzyt = pzyt;
            }

            public int getQydj() {
                return qydj;
            }

            public void setQydj(int qydj) {
                this.qydj = qydj;
            }

            public int getRk() {
                return rk;
            }

            public void setRk(int rk) {
                this.rk = rk;
            }

            public int getQsxz() {
                return qsxz;
            }

            public void setQsxz(int qsxz) {
                this.qsxz = qsxz;
            }

            public String getQsxzText() {
                return qsxzText;
            }

            public void setQsxzText(String qsxzText) {
                this.qsxzText = qsxzText;
            }

            public int getSyqlx() {
                return syqlx;
            }

            public void setSyqlx(int syqlx) {
                this.syqlx = syqlx;
            }

            public String getSyqlxText() {
                return syqlxText;
            }

            public void setSyqlxText(String syqlxText) {
                this.syqlxText = syqlxText;
            }

            public int getTdly() {
                return tdly;
            }

            public void setTdly(int tdly) {
                this.tdly = tdly;
            }

            public String getTdlyText() {
                return tdlyText;
            }

            public void setTdlyText(String tdlyText) {
                this.tdlyText = tdlyText;
            }

            public int getHasoccupy() {
                return hasoccupy;
            }

            public void setHasoccupy(int hasoccupy) {
                this.hasoccupy = hasoccupy;
            }

            public String getHasoccupyText() {
                return hasoccupyText;
            }

            public void setHasoccupyText(String hasoccupyText) {
                this.hasoccupyText = hasoccupyText;
            }

            public int getCqly() {
                return cqly;
            }

            public void setCqly(int cqly) {
                this.cqly = cqly;
            }

            public Object getCqlyText() {
                return cqlyText;
            }

            public void setCqlyText(Object cqlyText) {
                this.cqlyText = cqlyText;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getZhaiid() {
                return zhaiid;
            }

            public void setZhaiid(int zhaiid) {
                this.zhaiid = zhaiid;
            }

            public Object getZhaiName() {
                return zhaiName;
            }

            public void setZhaiName(Object zhaiName) {
                this.zhaiName = zhaiName;
            }

            public Object getHzmc() {
                return hzmc;
            }

            public void setHzmc(Object hzmc) {
                this.hzmc = hzmc;
            }

            public int getYllegent() {
                return yllegent;
            }

            public void setYllegent(int yllegent) {
                this.yllegent = yllegent;
            }

            public String getCenter() {
                return center;
            }

            public void setCenter(String center) {
                this.center = center;
            }

            public String getTsqk() {
                return tsqk;
            }

            public void setTsqk(String tsqk) {
                this.tsqk = tsqk;
            }

            public double getRjl() {
                return rjl;
            }

            public void setRjl(double rjl) {
                this.rjl = rjl;
            }

            public int getDlr() {
                return dlr;
            }

            public void setDlr(int dlr) {
                this.dlr = dlr;
            }

            public Object getDlrname() {
                return dlrname;
            }

            public void setDlrname(Object dlrname) {
                this.dlrname = dlrname;
            }

            public String getGeometry() {
                return geometry;
            }

            public void setGeometry(String geometry) {
                this.geometry = geometry;
            }

            public int getNongcount() {
                return nongcount;
            }

            public void setNongcount(int nongcount) {
                this.nongcount = nongcount;
            }

            public int getFeinongcount() {
                return feinongcount;
            }

            public void setFeinongcount(int feinongcount) {
                this.feinongcount = feinongcount;
            }

            public Object getZjdcount() {
                return zjdcount;
            }

            public void setZjdcount(Object zjdcount) {
                this.zjdcount = zjdcount;
            }

            public Object getHjmj() {
                return hjmj;
            }

            public void setHjmj(Object hjmj) {
                this.hjmj = hjmj;
            }

            public int getHucount() {
                return hucount;
            }

            public void setHucount(int hucount) {
                this.hucount = hucount;
            }

            public int getApptype() {
                return apptype;
            }

            public void setApptype(int apptype) {
                this.apptype = apptype;
            }

            public Object getApptypeText() {
                return apptypeText;
            }

            public void setApptypeText(Object apptypeText) {
                this.apptypeText = apptypeText;
            }

            public int getSqtype() {
                return sqtype;
            }

            public void setSqtype(int sqtype) {
                this.sqtype = sqtype;
            }

            public int getLztype() {
                return lztype;
            }

            public void setLztype(int lztype) {
                this.lztype = lztype;
            }

            public int getTctype() {
                return tctype;
            }

            public void setTctype(int tctype) {
                this.tctype = tctype;
            }

            public Object getQsxzList() {
                return qsxzList;
            }

            public void setQsxzList(Object qsxzList) {
                this.qsxzList = qsxzList;
            }

            public Object getSyqlxList() {
                return syqlxList;
            }

            public void setSyqlxList(Object syqlxList) {
                this.syqlxList = syqlxList;
            }

            public Object getCqlyList() {
                return cqlyList;
            }

            public void setCqlyList(Object cqlyList) {
                this.cqlyList = cqlyList;
            }

            public double getLdArea() {
                return ldArea;
            }

            public void setLdArea(double ldArea) {
                this.ldArea = ldArea;
            }

            public double getLdRk() {
                return ldRk;
            }

            public void setLdRk(double ldRk) {
                this.ldRk = ldRk;
            }

            public double getLdQdfs() {
                return ldQdfs;
            }

            public void setLdQdfs(double ldQdfs) {
                this.ldQdfs = ldQdfs;
            }

            public double getLdRjl() {
                return ldRjl;
            }

            public void setLdRjl(double ldRjl) {
                this.ldRjl = ldRjl;
            }

            public double getLdDjgx() {
                return ldDjgx;
            }

            public void setLdDjgx(double ldDjgx) {
                this.ldDjgx = ldDjgx;
            }

            public int getFdq4() {
                return fdq4;
            }

            public void setFdq4(int fdq4) {
                this.fdq4 = fdq4;
            }

            public int getFdq46() {
                return fdq46;
            }

            public void setFdq46(int fdq46) {
                this.fdq46 = fdq46;
            }

            public int getFdq68() {
                return fdq68;
            }

            public void setFdq68(int fdq68) {
                this.fdq68 = fdq68;
            }

            public int getFdq8() {
                return fdq8;
            }

            public void setFdq8(int fdq8) {
                this.fdq8 = fdq8;
            }

            public int getFdqzmj() {
                return fdqzmj;
            }

            public void setFdqzmj(int fdqzmj) {
                this.fdqzmj = fdqzmj;
            }

            public int getFdq4count() {
                return fdq4count;
            }

            public void setFdq4count(int fdq4count) {
                this.fdq4count = fdq4count;
            }

            public int getFdq46count() {
                return fdq46count;
            }

            public void setFdq46count(int fdq46count) {
                this.fdq46count = fdq46count;
            }

            public int getFdq68count() {
                return fdq68count;
            }

            public void setFdq68count(int fdq68count) {
                this.fdq68count = fdq68count;
            }

            public int getFdq8count() {
                return fdq8count;
            }

            public void setFdq8count(int fdq8count) {
                this.fdq8count = fdq8count;
            }

            public int getFdqcount() {
                return fdqcount;
            }

            public void setFdqcount(int fdqcount) {
                this.fdqcount = fdqcount;
            }

            public int getNan() {
                return nan;
            }

            public void setNan(int nan) {
                this.nan = nan;
            }

            public int getNv() {
                return nv;
            }

            public void setNv(int nv) {
                this.nv = nv;
            }

            public int getDs() {
                return ds;
            }

            public void setDs(int ds) {
                this.ds = ds;
            }

            public int getZzcy() {
                return zzcy;
            }

            public void setZzcy(int zzcy) {
                this.zzcy = zzcy;
            }

            public Object getApplyEntity() {
                return applyEntity;
            }

            public void setApplyEntity(Object applyEntity) {
                this.applyEntity = applyEntity;
            }

            public Object getZjdSqEntity() {
                return zjdSqEntity;
            }

            public void setZjdSqEntity(Object zjdSqEntity) {
                this.zjdSqEntity = zjdSqEntity;
            }

            public Object getZjdLzEntity() {
                return zjdLzEntity;
            }

            public void setZjdLzEntity(Object zjdLzEntity) {
                this.zjdLzEntity = zjdLzEntity;
            }

            public Object getZjdTcEntity() {
                return zjdTcEntity;
            }

            public void setZjdTcEntity(Object zjdTcEntity) {
                this.zjdTcEntity = zjdTcEntity;
            }

            public int getSyqk() {
                return syqk;
            }

            public void setSyqk(int syqk) {
                this.syqk = syqk;
            }

            public int getJcnd() {
                return jcnd;
            }

            public void setJcnd(int jcnd) {
                this.jcnd = jcnd;
            }

            public int getJzjg() {
                return jzjg;
            }

            public void setJzjg(int jzjg) {
                this.jzjg = jzjg;
            }

            public int getJzcs() {
                return jzcs;
            }

            public void setJzcs(int jzcs) {
                this.jzcs = jzcs;
            }

            public Object getCzmj() {
                return czmj;
            }

            public void setCzmj(Object czmj) {
                this.czmj = czmj;
            }

            public Object getCzjg() {
                return czjg;
            }

            public void setCzjg(Object czjg) {
                this.czjg = czjg;
            }

            public String getYllegentText() {
                return yllegentText;
            }

            public void setYllegentText(String yllegentText) {
                this.yllegentText = yllegentText;
            }

            public Object getSyqkText() {
                return syqkText;
            }

            public void setSyqkText(Object syqkText) {
                this.syqkText = syqkText;
            }

            public String getJcndText() {
                return jcndText;
            }

            public void setJcndText(String jcndText) {
                this.jcndText = jcndText;
            }

            public String getJzjgText() {
                return jzjgText;
            }

            public void setJzjgText(String jzjgText) {
                this.jzjgText = jzjgText;
            }

            public String getJzcsText() {
                return jzcsText;
            }

            public void setJzcsText(String jzcsText) {
                this.jzcsText = jzcsText;
            }

            public List<String> getTsqkText() {
                return tsqkText;
            }

            public void setTsqkText(List<String> tsqkText) {
                this.tsqkText = tsqkText;
            }
        }
    }
}
