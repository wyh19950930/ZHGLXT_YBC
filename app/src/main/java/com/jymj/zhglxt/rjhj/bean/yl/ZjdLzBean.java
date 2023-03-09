package com.jymj.zhglxt.rjhj.bean.yl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/5/15 17:05
 */
public class ZjdLzBean {

    /**
     * msg : null
     * code : 0
     * data : {"totalCount":1,"pageSize":20,"totalPage":1,"currPage":1,"list":[{"id":4,"ylId":91887,"sptype":4,"sptypeText":"缮证颁证","sqDate":"2020-05-14","sqName":"黄健杰","iphone":"18301179628","jjphone":"18301179628","shDate":"2020-05-14","baDate":"2020-05-15","bzDate":"2020-05-14","bhDate":null,"remark":"null审核备注：123\\r\\n备案备注：123\\r\\n缮证颁证备注：123\\r\\n","zjdFileIds":null,"ylEntity":{"gid":93807,"area":265,"jianzhuArea":172,"name":"杨建成","remark":null,"mph":"北京市大兴区黄村镇西芦城村丰收巷19号","ylType":1,"ylTypeText":"宅基地","legent":1,"isidle":0,"isidleText":"否","xzqmc":"西芦城村","objectid":91887,"pzmj":null,"bh":"111","ysbh":null,"pzbm":null,"pzwh":null,"pzsj":null,"pzyt":null,"qydj":null,"rk":6,"qsxz":0,"qsxzText":"其他","syqlx":0,"syqlxText":"其他","tdly":null,"tdlyText":null,"hasoccupy":null,"hasoccupyText":null,"cqly":0,"cqlyText":"其他","code":"110115015006","zhaiid":null,"zhaiName":null,"hzmc":"杨建成","yllegent":1,"center":"POINT(116.289279312757 39.7512130843727)","tsqk":null,"rjl":0.4,"dlr":null,"dlrname":null,"geometry":"MULTIPOLYGON(((116.289339583296 39.7513107915,116.289351468589 39.7512410028318,116.289353435447 39.7512411809821,116.289365611724 39.751170935139,116.289362161983 39.7511705788451,116.289368804699 39.7511304535756,116.289220211264 39.751115221073,116.289212348245 39.751155105781,116.289198533066 39.7512251823526,116.289188380599 39.7512949224394,116.289339583296 39.7513107915)))","nongcount":6,"feinongcount":0,"hucount":1,"apptype":null,"apptypeText":null,"sqtype":0,"lztype":4,"tctype":0,"qsxzList":null,"syqlxList":null,"cqlyList":null,"ldArea":0.4,"ldRk":0.6000000000000001,"ldQdfs":0.4,"ldRjl":0.65,"ldDjgx":0.3},"zjdFileEntities":null,"counts":null}]}
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
         * totalCount : 1
         * pageSize : 20
         * totalPage : 1
         * currPage : 1
         * list : [{"id":4,"ylId":91887,"sptype":4,"sptypeText":"缮证颁证","sqDate":"2020-05-14","sqName":"黄健杰","iphone":"18301179628","jjphone":"18301179628","shDate":"2020-05-14","baDate":"2020-05-15","bzDate":"2020-05-14","bhDate":null,"remark":"null审核备注：123\\r\\n备案备注：123\\r\\n缮证颁证备注：123\\r\\n","zjdFileIds":null,"ylEntity":{"gid":93807,"area":265,"jianzhuArea":172,"name":"杨建成","remark":null,"mph":"北京市大兴区黄村镇西芦城村丰收巷19号","ylType":1,"ylTypeText":"宅基地","legent":1,"isidle":0,"isidleText":"否","xzqmc":"西芦城村","objectid":91887,"pzmj":null,"bh":"111","ysbh":null,"pzbm":null,"pzwh":null,"pzsj":null,"pzyt":null,"qydj":null,"rk":6,"qsxz":0,"qsxzText":"其他","syqlx":0,"syqlxText":"其他","tdly":null,"tdlyText":null,"hasoccupy":null,"hasoccupyText":null,"cqly":0,"cqlyText":"其他","code":"110115015006","zhaiid":null,"zhaiName":null,"hzmc":"杨建成","yllegent":1,"center":"POINT(116.289279312757 39.7512130843727)","tsqk":null,"rjl":0.4,"dlr":null,"dlrname":null,"geometry":"MULTIPOLYGON(((116.289339583296 39.7513107915,116.289351468589 39.7512410028318,116.289353435447 39.7512411809821,116.289365611724 39.751170935139,116.289362161983 39.7511705788451,116.289368804699 39.7511304535756,116.289220211264 39.751115221073,116.289212348245 39.751155105781,116.289198533066 39.7512251823526,116.289188380599 39.7512949224394,116.289339583296 39.7513107915)))","nongcount":6,"feinongcount":0,"hucount":1,"apptype":null,"apptypeText":null,"sqtype":0,"lztype":4,"tctype":0,"qsxzList":null,"syqlxList":null,"cqlyList":null,"ldArea":0.4,"ldRk":0.6000000000000001,"ldQdfs":0.4,"ldRjl":0.65,"ldDjgx":0.3},"zjdFileEntities":null,"counts":null}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 4
             * ylId : 91887
             * sptype : 4
             * sptypeText : 缮证颁证
             * sqDate : 2020-05-14
             * sqName : 黄健杰
             * iphone : 18301179628
             * jjphone : 18301179628
             * shDate : 2020-05-14
             * baDate : 2020-05-15
             * bzDate : 2020-05-14
             * bhDate : null
             * remark : null审核备注：123\r\n备案备注：123\r\n缮证颁证备注：123\r\n
             * zjdFileIds : null
             * ylEntity : {"gid":93807,"area":265,"jianzhuArea":172,"name":"杨建成","remark":null,"mph":"北京市大兴区黄村镇西芦城村丰收巷19号","ylType":1,"ylTypeText":"宅基地","legent":1,"isidle":0,"isidleText":"否","xzqmc":"西芦城村","objectid":91887,"pzmj":null,"bh":"111","ysbh":null,"pzbm":null,"pzwh":null,"pzsj":null,"pzyt":null,"qydj":null,"rk":6,"qsxz":0,"qsxzText":"其他","syqlx":0,"syqlxText":"其他","tdly":null,"tdlyText":null,"hasoccupy":null,"hasoccupyText":null,"cqly":0,"cqlyText":"其他","code":"110115015006","zhaiid":null,"zhaiName":null,"hzmc":"杨建成","yllegent":1,"center":"POINT(116.289279312757 39.7512130843727)","tsqk":null,"rjl":0.4,"dlr":null,"dlrname":null,"geometry":"MULTIPOLYGON(((116.289339583296 39.7513107915,116.289351468589 39.7512410028318,116.289353435447 39.7512411809821,116.289365611724 39.751170935139,116.289362161983 39.7511705788451,116.289368804699 39.7511304535756,116.289220211264 39.751115221073,116.289212348245 39.751155105781,116.289198533066 39.7512251823526,116.289188380599 39.7512949224394,116.289339583296 39.7513107915)))","nongcount":6,"feinongcount":0,"hucount":1,"apptype":null,"apptypeText":null,"sqtype":0,"lztype":4,"tctype":0,"qsxzList":null,"syqlxList":null,"cqlyList":null,"ldArea":0.4,"ldRk":0.6000000000000001,"ldQdfs":0.4,"ldRjl":0.65,"ldDjgx":0.3}
             * zjdFileEntities : null
             * counts : null
             */

            private int id;
            private int ylId;
            private int sptype;
            private String sptypeText;
            private String sqDate;
            private String sqName;
            private String iphone;
            private String jjphone;
            private String shDate;
            private String baDate;
            private String bzDate;
            private Object bhDate;
            private String remark;
            private Object zjdFileIds;
            private YlEntityBean ylEntity;
            private Object zjdFileEntities;
            private Object counts;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getYlId() {
                return ylId;
            }

            public void setYlId(int ylId) {
                this.ylId = ylId;
            }

            public int getSptype() {
                return sptype;
            }

            public void setSptype(int sptype) {
                this.sptype = sptype;
            }

            public String getSptypeText() {
                return sptypeText==null?"":sptypeText;
            }

            public void setSptypeText(String sptypeText) {
                this.sptypeText = sptypeText;
            }

            public String getSqDate() {
                return sqDate==null?"":sqDate;
            }

            public void setSqDate(String sqDate) {
                this.sqDate = sqDate;
            }

            public String getSqName() {
                return sqName==null?"":sqName;
            }

            public void setSqName(String sqName) {
                this.sqName = sqName;
            }

            public String getIphone() {
                return iphone==null?"":iphone;
            }

            public void setIphone(String iphone) {
                this.iphone = iphone;
            }

            public String getJjphone() {
                return jjphone==null?"":jjphone;
            }

            public void setJjphone(String jjphone) {
                this.jjphone = jjphone;
            }

            public String getShDate() {
                return shDate==null?"":shDate;
            }

            public void setShDate(String shDate) {
                this.shDate = shDate;
            }

            public String getBaDate() {
                return baDate==null?"":baDate;
            }

            public void setBaDate(String baDate) {
                this.baDate = baDate;
            }

            public String getBzDate() {
                return bzDate==null?"":bzDate;
            }

            public void setBzDate(String bzDate) {
                this.bzDate = bzDate;
            }

            public Object getBhDate() {
                return bhDate;
            }

            public void setBhDate(Object bhDate) {
                this.bhDate = bhDate;
            }

            public String getRemark() {
                return remark==null?"":remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public Object getZjdFileIds() {
                return zjdFileIds;
            }

            public void setZjdFileIds(Object zjdFileIds) {
                this.zjdFileIds = zjdFileIds;
            }

            public YlEntityBean getYlEntity() {
                return ylEntity == null?new YlEntityBean():ylEntity;
            }

            public void setYlEntity(YlEntityBean ylEntity) {
                this.ylEntity = ylEntity;
            }

            public Object getZjdFileEntities() {
                return zjdFileEntities;
            }

            public void setZjdFileEntities(Object zjdFileEntities) {
                this.zjdFileEntities = zjdFileEntities;
            }

            public Object getCounts() {
                return counts;
            }

            public void setCounts(Object counts) {
                this.counts = counts;
            }

            public static class YlEntityBean {
                /**
                 * gid : 93807
                 * area : 265
                 * jianzhuArea : 172
                 * name : 杨建成
                 * remark : null
                 * mph : 北京市大兴区黄村镇西芦城村丰收巷19号
                 * ylType : 1
                 * ylTypeText : 宅基地
                 * legent : 1
                 * isidle : 0
                 * isidleText : 否
                 * xzqmc : 西芦城村
                 * objectid : 91887
                 * pzmj : null
                 * bh : 111
                 * ysbh : null
                 * pzbm : null
                 * pzwh : null
                 * pzsj : null
                 * pzyt : null
                 * qydj : null
                 * rk : 6
                 * qsxz : 0
                 * qsxzText : 其他
                 * syqlx : 0
                 * syqlxText : 其他
                 * tdly : null
                 * tdlyText : null
                 * hasoccupy : null
                 * hasoccupyText : null
                 * cqly : 0
                 * cqlyText : 其他
                 * code : 110115015006
                 * zhaiid : null
                 * zhaiName : null
                 * hzmc : 杨建成
                 * yllegent : 1
                 * center : POINT(116.289279312757 39.7512130843727)
                 * tsqk : null
                 * rjl : 0.4
                 * dlr : null
                 * dlrname : null
                 * geometry : MULTIPOLYGON(((116.289339583296 39.7513107915,116.289351468589 39.7512410028318,116.289353435447 39.7512411809821,116.289365611724 39.751170935139,116.289362161983 39.7511705788451,116.289368804699 39.7511304535756,116.289220211264 39.751115221073,116.289212348245 39.751155105781,116.289198533066 39.7512251823526,116.289188380599 39.7512949224394,116.289339583296 39.7513107915)))
                 * nongcount : 6
                 * feinongcount : 0
                 * hucount : 1
                 * apptype : null
                 * apptypeText : null
                 * sqtype : 0
                 * lztype : 4
                 * tctype : 0
                 * qsxzList : null
                 * syqlxList : null
                 * cqlyList : null
                 * ldArea : 0.4
                 * ldRk : 0.6000000000000001
                 * ldQdfs : 0.4
                 * ldRjl : 0.65
                 * ldDjgx : 0.3
                 */

                private int gid;
                private BigDecimal area;
                private BigDecimal jianzhuArea;
                private String name;
                private Object remark;
                private String mph;
                private int ylType;
                private String ylTypeText;
                private int legent;
                private int isidle;
                private String isidleText;
                private String xzqmc;
                private int objectid;
                private BigDecimal pzmj;
                private String bh;
                private Object ysbh;
                private Object pzbm;
                private Object pzwh;
                private Object pzsj;
                private Object pzyt;
                private Object qydj;
                private int rk;
                private int qsxz;
                private String qsxzText;
                private int syqlx;
                private String syqlxText;
                private Object tdly;
                private Object tdlyText;
                private Object hasoccupy;
                private Object hasoccupyText;
                private int cqly;
                private String cqlyText;
                private String code;
                private Object zhaiid;
                private Object zhaiName;
                private String hzmc;
                private int yllegent;
                private String center;
                private Object tsqk;
                private double rjl;
                private Object dlr;
                private Object dlrname;
                private String geometry;
                private int nongcount;
                private int feinongcount;
                private int hucount;
                private Object apptype;
                private Object apptypeText;
                private int sqtype;
                private int lztype;
                private int tctype;
                private Object qsxzList;
                private Object syqlxList;
                private Object cqlyList;
                private BigDecimal ldArea;
                private double ldRk;
                private double ldQdfs;
                private double ldRjl;
                private double ldDjgx;

                public int getGid() {
                    return gid;
                }

                public void setGid(int gid) {
                    this.gid = gid;
                }

                public BigDecimal getArea() {
                    return area==null?new BigDecimal(0):area;
                }

                public void setArea(BigDecimal area) {
                    this.area = area;
                }

                public BigDecimal getJianzhuArea() {
                    return jianzhuArea==null?new BigDecimal(0):jianzhuArea;
                }

                public void setJianzhuArea(BigDecimal jianzhuArea) {
                    this.jianzhuArea = jianzhuArea;
                }

                public String getName() {
                    return name==null?"":name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }

                public String getMph() {
                    return mph==null?"":mph;
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
                    return ylTypeText==null?"":ylTypeText;
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
                    return isidleText==null?"":isidleText;
                }

                public void setIsidleText(String isidleText) {
                    this.isidleText = isidleText;
                }

                public String getXzqmc() {
                    return xzqmc==null?"":xzqmc;
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

                public BigDecimal getPzmj() {
                    return pzmj==null?new BigDecimal(0):pzmj;
                }

                public void setPzmj(BigDecimal pzmj) {
                    this.pzmj = pzmj;
                }

                public String getBh() {
                    return bh==null?"":bh;
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

                public Object getQydj() {
                    return qydj;
                }

                public void setQydj(Object qydj) {
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
                    return qsxzText==null?"":qsxzText;
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
                    return syqlxText==null?"":syqlxText;
                }

                public void setSyqlxText(String syqlxText) {
                    this.syqlxText = syqlxText;
                }

                public Object getTdly() {
                    return tdly;
                }

                public void setTdly(Object tdly) {
                    this.tdly = tdly;
                }

                public Object getTdlyText() {
                    return tdlyText;
                }

                public void setTdlyText(Object tdlyText) {
                    this.tdlyText = tdlyText;
                }

                public Object getHasoccupy() {
                    return hasoccupy;
                }

                public void setHasoccupy(Object hasoccupy) {
                    this.hasoccupy = hasoccupy;
                }

                public Object getHasoccupyText() {
                    return hasoccupyText;
                }

                public void setHasoccupyText(Object hasoccupyText) {
                    this.hasoccupyText = hasoccupyText;
                }

                public int getCqly() {
                    return cqly;
                }

                public void setCqly(int cqly) {
                    this.cqly = cqly;
                }

                public String getCqlyText() {
                    return cqlyText;
                }

                public void setCqlyText(String cqlyText) {
                    this.cqlyText = cqlyText;
                }

                public String getCode() {
                    return code==null?"":code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public Object getZhaiid() {
                    return zhaiid;
                }

                public void setZhaiid(Object zhaiid) {
                    this.zhaiid = zhaiid;
                }

                public Object getZhaiName() {
                    return zhaiName;
                }

                public void setZhaiName(Object zhaiName) {
                    this.zhaiName = zhaiName;
                }

                public String getHzmc() {
                    return hzmc==null?"":hzmc;
                }

                public void setHzmc(String hzmc) {
                    this.hzmc = hzmc;
                }

                public int getYllegent() {
                    return yllegent;
                }

                public void setYllegent(int yllegent) {
                    this.yllegent = yllegent;
                }

                public String getCenter() {
                    return center==null?"":center;
                }

                public void setCenter(String center) {
                    this.center = center;
                }

                public Object getTsqk() {
                    return tsqk;
                }

                public void setTsqk(Object tsqk) {
                    this.tsqk = tsqk;
                }

                public double getRjl() {
                    return rjl;
                }

                public void setRjl(double rjl) {
                    this.rjl = rjl;
                }

                public Object getDlr() {
                    return dlr;
                }

                public void setDlr(Object dlr) {
                    this.dlr = dlr;
                }

                public Object getDlrname() {
                    return dlrname;
                }

                public void setDlrname(Object dlrname) {
                    this.dlrname = dlrname;
                }

                public String getGeometry() {
                    return geometry==null?"":geometry;
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

                public int getHucount() {
                    return hucount;
                }

                public void setHucount(int hucount) {
                    this.hucount = hucount;
                }

                public Object getApptype() {
                    return apptype;
                }

                public void setApptype(Object apptype) {
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

                public BigDecimal getLdArea() {
                    return ldArea==null?new BigDecimal(0):ldArea;
                }

                public void setLdArea(BigDecimal ldArea) {
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
            }
        }
    }
}
