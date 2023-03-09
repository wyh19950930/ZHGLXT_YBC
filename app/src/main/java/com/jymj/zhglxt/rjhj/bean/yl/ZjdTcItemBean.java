package com.jymj.zhglxt.rjhj.bean.yl;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/5/15 18:08
 */
public class ZjdTcItemBean {

    /**
     * msg : null
     * code : 0
     * data : {"id":3,"ylId":92367,"sptype":2,"sqDate":"2020-05-15","sqName":"黄健杰","xyDate":null,"baDate":null,"zxDate":null,"dcDate":"2020-05-15","remark":"null镇政府调查备注：123\n","jjphone":"18301179628","isdelete":false,"iphone":"18301179628","sptypeText":"镇政府调查","zjdTcFileIds":null,"zjdFileEntityList":[{"id":88,"key":14,"keyText":"申请","path":"zjdTcFile\\14\\20200515105352805.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":89,"key":11,"keyText":"申请","path":"zjdTcFile\\11\\20200515105359570.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":90,"key":12,"keyText":"申请","path":"zjdTcFile\\12\\20200515105404836.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":91,"key":13,"keyText":"申请","path":"zjdTcFile\\13\\20200515105409376.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":92,"key":110,"keyText":"申请","path":"zjdTcFile\\110\\20200515105421737.png","update":1589472000000,"filename":"奥迪.png","status":2,"remark":"null","type":1,"zjdId":3}],"ylEntity":{"gid":94287,"area":403,"jianzhuArea":262,"name":"周少全","remark":null,"mph":"北京市大兴区黄村镇东芦城村芦星东街19号","ylType":1,"ylTypeText":"宅基地","legent":1,"isidle":0,"isidleText":"否","xzqmc":"东芦城村","objectid":92367,"pzmj":null,"bh":"391","ysbh":null,"pzbm":null,"pzwh":null,"pzsj":null,"pzyt":null,"qydj":null,"rk":3,"qsxz":0,"qsxzText":"其他","syqlx":0,"syqlxText":"其他","tdly":null,"tdlyText":null,"hasoccupy":null,"hasoccupyText":null,"cqly":0,"cqlyText":"其他","code":"110115015008","zhaiid":null,"zhaiName":null,"hzmc":"周广志","yllegent":3,"center":"POINT(116.294173886742 39.7508359627691)","tsqk":null,"rjl":0.4,"dlr":null,"dlrname":null,"geometry":"MULTIPOLYGON(((116.294252951126 39.750953789098,116.294282858987 39.7507315548427,116.294100452246 39.7507150082086,116.294060808878 39.75093991407,116.294252951126 39.750953789098)))","nongcount":3,"feinongcount":0,"hucount":1,"apptype":null,"apptypeText":null,"sqtype":0,"lztype":0,"tctype":2,"qsxzList":null,"syqlxList":null,"cqlyList":null,"ldArea":0.6,"ldRk":0.30000000000000004,"ldQdfs":0.4,"ldRjl":0.65,"ldDjgx":0.3},"bhDate":null,"counts":null}
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
         * id : 3
         * ylId : 92367
         * sptype : 2
         * sqDate : 2020-05-15
         * sqName : 黄健杰
         * xyDate : null
         * baDate : null
         * zxDate : null
         * dcDate : 2020-05-15
         * remark : null镇政府调查备注：123

         * jjphone : 18301179628
         * isdelete : false
         * iphone : 18301179628
         * sptypeText : 镇政府调查
         * zjdTcFileIds : null
         * zjdFileEntityList : [{"id":88,"key":14,"keyText":"申请","path":"zjdTcFile\\14\\20200515105352805.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":89,"key":11,"keyText":"申请","path":"zjdTcFile\\11\\20200515105359570.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":90,"key":12,"keyText":"申请","path":"zjdTcFile\\12\\20200515105404836.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":91,"key":13,"keyText":"申请","path":"zjdTcFile\\13\\20200515105409376.png","update":1589472000000,"filename":"奥迪.png","status":1,"remark":"null","type":1,"zjdId":3},{"id":92,"key":110,"keyText":"申请","path":"zjdTcFile\\110\\20200515105421737.png","update":1589472000000,"filename":"奥迪.png","status":2,"remark":"null","type":1,"zjdId":3}]
         * ylEntity : {"gid":94287,"area":403,"jianzhuArea":262,"name":"周少全","remark":null,"mph":"北京市大兴区黄村镇东芦城村芦星东街19号","ylType":1,"ylTypeText":"宅基地","legent":1,"isidle":0,"isidleText":"否","xzqmc":"东芦城村","objectid":92367,"pzmj":null,"bh":"391","ysbh":null,"pzbm":null,"pzwh":null,"pzsj":null,"pzyt":null,"qydj":null,"rk":3,"qsxz":0,"qsxzText":"其他","syqlx":0,"syqlxText":"其他","tdly":null,"tdlyText":null,"hasoccupy":null,"hasoccupyText":null,"cqly":0,"cqlyText":"其他","code":"110115015008","zhaiid":null,"zhaiName":null,"hzmc":"周广志","yllegent":3,"center":"POINT(116.294173886742 39.7508359627691)","tsqk":null,"rjl":0.4,"dlr":null,"dlrname":null,"geometry":"MULTIPOLYGON(((116.294252951126 39.750953789098,116.294282858987 39.7507315548427,116.294100452246 39.7507150082086,116.294060808878 39.75093991407,116.294252951126 39.750953789098)))","nongcount":3,"feinongcount":0,"hucount":1,"apptype":null,"apptypeText":null,"sqtype":0,"lztype":0,"tctype":2,"qsxzList":null,"syqlxList":null,"cqlyList":null,"ldArea":0.6,"ldRk":0.30000000000000004,"ldQdfs":0.4,"ldRjl":0.65,"ldDjgx":0.3}
         * bhDate : null
         * counts : null
         */

        private int id;
        private int ylId;
        private int sptype;
        private String sqDate;
        private String sqName;
        private Object xyDate;
        private Object baDate;
        private Object zxDate;
        private String dcDate;
        private String remark;
        private String jjphone;
        private boolean isdelete;
        private String iphone;
        private String sptypeText;
        private Object zjdTcFileIds;
        private YlEntityBean ylEntity;
        private Object bhDate;
        private Object counts;
        private List<ZjdFileEntity> zjdFileEntityList;

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

        public Object getXyDate() {
            return xyDate;
        }

        public void setXyDate(Object xyDate) {
            this.xyDate = xyDate;
        }

        public Object getBaDate() {
            return baDate;
        }

        public void setBaDate(Object baDate) {
            this.baDate = baDate;
        }

        public Object getZxDate() {
            return zxDate;
        }

        public void setZxDate(Object zxDate) {
            this.zxDate = zxDate;
        }

        public String getDcDate() {
            return dcDate==null?"":dcDate;
        }

        public void setDcDate(String dcDate) {
            this.dcDate = dcDate;
        }

        public String getRemark() {
            return remark==null?"":remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getJjphone() {
            return jjphone==null?"":jjphone;
        }

        public void setJjphone(String jjphone) {
            this.jjphone = jjphone;
        }

        public boolean isIsdelete() {
            return isdelete;
        }

        public void setIsdelete(boolean isdelete) {
            this.isdelete = isdelete;
        }

        public String getIphone() {
            return iphone==null?"":iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public String getSptypeText() {
            return sptypeText;
        }

        public void setSptypeText(String sptypeText) {
            this.sptypeText = sptypeText;
        }

        public Object getZjdTcFileIds() {
            return zjdTcFileIds;
        }

        public void setZjdTcFileIds(Object zjdTcFileIds) {
            this.zjdTcFileIds = zjdTcFileIds;
        }

        public YlEntityBean getYlEntity() {
            return ylEntity == null? new YlEntityBean():ylEntity;
        }

        public void setYlEntity(YlEntityBean ylEntity) {
            this.ylEntity = ylEntity;
        }

        public Object getBhDate() {
            return bhDate;
        }

        public void setBhDate(Object bhDate) {
            this.bhDate = bhDate;
        }

        public Object getCounts() {
            return counts;
        }

        public void setCounts(Object counts) {
            this.counts = counts;
        }

        public List<ZjdFileEntity> getZjdFileEntityList() {
            return zjdFileEntityList;
        }

        public void setZjdFileEntityList(List<ZjdFileEntity> zjdFileEntityList) {
            this.zjdFileEntityList = zjdFileEntityList;
        }

        public static class YlEntityBean {
            /**
             * gid : 94287
             * area : 403
             * jianzhuArea : 262
             * name : 周少全
             * remark : null
             * mph : 北京市大兴区黄村镇东芦城村芦星东街19号
             * ylType : 1
             * ylTypeText : 宅基地
             * legent : 1
             * isidle : 0
             * isidleText : 否
             * xzqmc : 东芦城村
             * objectid : 92367
             * pzmj : null
             * bh : 391
             * ysbh : null
             * pzbm : null
             * pzwh : null
             * pzsj : null
             * pzyt : null
             * qydj : null
             * rk : 3
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
             * code : 110115015008
             * zhaiid : null
             * zhaiName : null
             * hzmc : 周广志
             * yllegent : 3
             * center : POINT(116.294173886742 39.7508359627691)
             * tsqk : null
             * rjl : 0.4
             * dlr : null
             * dlrname : null
             * geometry : MULTIPOLYGON(((116.294252951126 39.750953789098,116.294282858987 39.7507315548427,116.294100452246 39.7507150082086,116.294060808878 39.75093991407,116.294252951126 39.750953789098)))
             * nongcount : 3
             * feinongcount : 0
             * hucount : 1
             * apptype : null
             * apptypeText : null
             * sqtype : 0
             * lztype : 0
             * tctype : 2
             * qsxzList : null
             * syqlxList : null
             * cqlyList : null
             * ldArea : 0.6
             * ldRk : 0.30000000000000004
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
            private Object pzmj;
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
                return xzqmc == null?"":xzqmc;
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

            public Object getPzmj() {
                return pzmj;
            }

            public void setPzmj(Object pzmj) {
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
                return cqlyText==null?"":cqlyText;
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
                return hzmc== null?"":hzmc;
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

            public LatLng getCenter() {

                if (center != null) {
                    String[] points = center.substring(6, center.length() - 1).split(" ");
                    if (points != null && points.length > 1) {
                        CoordinateConverter converter = new CoordinateConverter();
                        // CoordType.GPS 待转换坐标类型
                        converter.from(CoordinateConverter.CoordType.GPS);
                        LatLng sl = new LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]));
                        // sourceLatLng待转换坐标点 LatLng类型
                        converter.coord(sl);
                        LatLng latLng = converter.convert();
                        return sl;
                    } else {
                        return new LatLng(0, 0);
                    }
                }

                return new LatLng(0, 0);
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
                return geometry == null?"":geometry;
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

        public static class ZjdFileEntityListBean {
            /**
             * id : 88
             * key : 14
             * keyText : 申请
             * path : zjdTcFile\14\20200515105352805.png
             * update : 1589472000000
             * filename : 奥迪.png
             * status : 1
             * remark : null
             * type : 1
             * zjdId : 3
             */

            private int id;
            private int key;
            private String keyText;
            private String path;
            private long update;
            private String filename;
            private int status;
            private String remark;
            private int type;
            private int zjdId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
            }

            public String getKeyText() {
                return keyText==null?"":keyText;
            }

            public void setKeyText(String keyText) {
                this.keyText = keyText;
            }

            public String getPath() {
                return path==null?"":path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public long getUpdate() {
                return update;
            }

            public void setUpdate(long update) {
                this.update = update;
            }

            public String getFilename() {
                return filename==null?"":filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRemark() {
                return remark==null?"":remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getZjdId() {
                return zjdId;
            }

            public void setZjdId(int zjdId) {
                this.zjdId = zjdId;
            }
        }
    }
}
