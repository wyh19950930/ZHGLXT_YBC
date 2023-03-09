package com.jymj.zhglxt.rjhj.bean.yl;

import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/5/15 16:09
 */
public class ZjdFjItemBean {

    /**
     * msg : null
     * code : 0
     * data : {"id":20,"userId":506,"ylId":91856,"ylEntity":{"gid":93776,"area":263,"jianzhuArea":171,"name":"赵胜利","remark":"一院多门牌，406户籍也在此","mph":"北京市大兴区黄村镇西芦城村丰收巷8号","ylType":1,"ylTypeText":"宅基地","legent":1,"isidle":0,"isidleText":"否","xzqmc":"西芦城村","objectid":91856,"pzmj":null,"bh":"124","ysbh":null,"pzbm":null,"pzwh":null,"pzsj":null,"pzyt":null,"qydj":null,"rk":0,"qsxz":0,"qsxzText":"其他","syqlx":0,"syqlxText":"其他","tdly":null,"tdlyText":null,"hasoccupy":null,"hasoccupyText":null,"cqly":0,"cqlyText":"其他","code":"110115015006","zhaiid":null,"zhaiName":null,"hzmc":"赵红雨","yllegent":1,"center":"POINT(116.290562123088 39.7513478558907)","tsqk":"6","rjl":0.4,"dlr":null,"dlrname":null,"geometry":null,"nongcount":3,"feinongcount":0,"hucount":1,"apptype":2,"apptypeText":"待审核","sqtype":0,"lztype":0,"tctype":0,"qsxzList":null,"syqlxList":null,"cqlyList":null,"ldArea":0.39,"ldRk":0,"ldQdfs":0.4,"ldRjl":0.65,"ldDjgx":0.3},"sptype":2,"sqDate":1589472000000,"spdate":null,"spcontent":null,"spjzmj":130,"ysdate":null,"ysUserId":null,"name":"黄健杰","iphone":"18301179628","jjphone":"18301179628","dspdate":1589472000000,"sgdate":null,"ishave":false,"spUserId":null,"remark":null,"dysdate":null,"applyFileList":[{"id":183,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152150838.png","update":1589472000000,"cltype":1,"cltypeText":"翻建申请书","remark":null,"applyEntity":null},{"id":184,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152156865.png","update":1589472000000,"cltype":2,"cltypeText":"四邻图","remark":null,"applyEntity":null},{"id":185,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152201315.png","update":1589472000000,"cltype":3,"cltypeText":"户口本","remark":null,"applyEntity":null},{"id":186,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152207334.png","update":1589472000000,"cltype":4,"cltypeText":"身份证","remark":null,"applyEntity":null},{"id":187,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152211710.png","update":1589472000000,"cltype":11,"cltypeText":"院内(前)","remark":null,"applyEntity":null},{"id":188,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152217545.png","update":1589472000000,"cltype":12,"cltypeText":"院内(后)","remark":null,"applyEntity":null},{"id":189,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152222436.png","update":1589472000000,"cltype":13,"cltypeText":"院外(前)","remark":null,"applyEntity":null},{"id":190,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152228113.png","update":1589472000000,"cltype":14,"cltypeText":"院外(后)","remark":null,"applyEntity":null},{"id":191,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152233404.png","update":1589472000000,"cltype":6,"cltypeText":"四邻协议","remark":null,"applyEntity":null},{"id":192,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152239267.png","update":1589472000000,"cltype":7,"cltypeText":"村两委会签署意见表","remark":null,"applyEntity":null},{"id":193,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152243950.png","update":1589472000000,"cltype":8,"cltypeText":"村民宅基地翻建公告","remark":null,"applyEntity":null},{"id":194,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152249177.png","update":1589472000000,"cltype":9,"cltypeText":"村民翻建宅基地承诺书","remark":null,"applyEntity":null},{"id":195,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152254420.png","update":1589472000000,"cltype":10,"cltypeText":"村委会关于本村宅基地翻建申请","remark":null,"applyEntity":null}],"applyChildEntity":null,"applyLogEntityList":[],"delete":false}
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
         * id : 20
         * userId : 506
         * ylId : 91856
         * ylEntity : {"gid":93776,"area":263,"jianzhuArea":171,"name":"赵胜利","remark":"一院多门牌，406户籍也在此","mph":"北京市大兴区黄村镇西芦城村丰收巷8号","ylType":1,"ylTypeText":"宅基地","legent":1,"isidle":0,"isidleText":"否","xzqmc":"西芦城村","objectid":91856,"pzmj":null,"bh":"124","ysbh":null,"pzbm":null,"pzwh":null,"pzsj":null,"pzyt":null,"qydj":null,"rk":0,"qsxz":0,"qsxzText":"其他","syqlx":0,"syqlxText":"其他","tdly":null,"tdlyText":null,"hasoccupy":null,"hasoccupyText":null,"cqly":0,"cqlyText":"其他","code":"110115015006","zhaiid":null,"zhaiName":null,"hzmc":"赵红雨","yllegent":1,"center":"POINT(116.290562123088 39.7513478558907)","tsqk":"6","rjl":0.4,"dlr":null,"dlrname":null,"geometry":null,"nongcount":3,"feinongcount":0,"hucount":1,"apptype":2,"apptypeText":"待审核","sqtype":0,"lztype":0,"tctype":0,"qsxzList":null,"syqlxList":null,"cqlyList":null,"ldArea":0.39,"ldRk":0,"ldQdfs":0.4,"ldRjl":0.65,"ldDjgx":0.3}
         * sptype : 2
         * sqDate : 1589472000000
         * spdate : null
         * spcontent : null
         * spjzmj : 130
         * ysdate : null
         * ysUserId : null
         * name : 黄健杰
         * iphone : 18301179628
         * jjphone : 18301179628
         * dspdate : 1589472000000
         * sgdate : null
         * ishave : false
         * spUserId : null
         * remark : null
         * dysdate : null
         * applyFileList : [{"id":183,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152150838.png","update":1589472000000,"cltype":1,"cltypeText":"翻建申请书","remark":null,"applyEntity":null},{"id":184,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152156865.png","update":1589472000000,"cltype":2,"cltypeText":"四邻图","remark":null,"applyEntity":null},{"id":185,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152201315.png","update":1589472000000,"cltype":3,"cltypeText":"户口本","remark":null,"applyEntity":null},{"id":186,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152207334.png","update":1589472000000,"cltype":4,"cltypeText":"身份证","remark":null,"applyEntity":null},{"id":187,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152211710.png","update":1589472000000,"cltype":11,"cltypeText":"院内(前)","remark":null,"applyEntity":null},{"id":188,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152217545.png","update":1589472000000,"cltype":12,"cltypeText":"院内(后)","remark":null,"applyEntity":null},{"id":189,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152222436.png","update":1589472000000,"cltype":13,"cltypeText":"院外(前)","remark":null,"applyEntity":null},{"id":190,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152228113.png","update":1589472000000,"cltype":14,"cltypeText":"院外(后)","remark":null,"applyEntity":null},{"id":191,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152233404.png","update":1589472000000,"cltype":6,"cltypeText":"四邻协议","remark":null,"applyEntity":null},{"id":192,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152239267.png","update":1589472000000,"cltype":7,"cltypeText":"村两委会签署意见表","remark":null,"applyEntity":null},{"id":193,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152243950.png","update":1589472000000,"cltype":8,"cltypeText":"村民宅基地翻建公告","remark":null,"applyEntity":null},{"id":194,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152249177.png","update":1589472000000,"cltype":9,"cltypeText":"村民翻建宅基地承诺书","remark":null,"applyEntity":null},{"id":195,"appId":20,"ylId":91856,"fileName":"奥迪.png","url":"applyFile\\20\\20200515152254420.png","update":1589472000000,"cltype":10,"cltypeText":"村委会关于本村宅基地翻建申请","remark":null,"applyEntity":null}]
         * applyChildEntity : null
         * applyLogEntityList : []
         * delete : false
         */

        private int id;
        private int userId;
        private int ylId;
        private YLEntity ylEntity;
        private Integer sptype;
        private String sqDate;
        private Object spdate;
        private String spcontent;
        private BigDecimal spjzmj;
        private Object ysdate;
        private Object ysUserId;
        private String name;
        private String iphone;
        private String jjphone;
        private String dspdate;
        private Object sgdate;
        private boolean ishave;
        private Object spUserId;
        private String remark;
        private Object dysdate;
        //申请资料
        private List<ApplyFileEntity> applyFileList;
        //施工中子项目
        private ApplyChildEntity applyChildEntity;
        //驳回信息
        private List<ApplyLogEntity> applyLogEntityList;
        private boolean delete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getYlId() {
            return ylId;
        }

        public void setYlId(int ylId) {
            this.ylId = ylId;
        }

        public YLEntity getYlEntity() {
            return ylEntity == null? new YLEntity():ylEntity;
        }

        public void setYlEntity(YLEntity ylEntity) {
            this.ylEntity = ylEntity;
        }

        public int getSptype() {
            return sptype;
        }

        public void setSptype(int sptype) {
            this.sptype = sptype;
        }

        public String getSqDate() {
            return sqDate;
        }

        public void setSqDate(String sqDate) {
            this.sqDate = sqDate;
        }

        public Object getSpdate() {
            return spdate;
        }

        public void setSpdate(Object spdate) {
            this.spdate = spdate;
        }

        public String getSpcontent() {
            return spcontent;
        }

        public void setSpcontent(String spcontent) {
            this.spcontent = spcontent;
        }

        public BigDecimal getSpjzmj() {
            return spjzmj == null?new BigDecimal(0):spjzmj;
        }

        public void setSpjzmj(BigDecimal spjzmj) {
            this.spjzmj = spjzmj;
        }
        public Object getYsdate() {
            return ysdate;
        }

        public void setYsdate(Object ysdate) {
            this.ysdate = ysdate;
        }

        public Object getYsUserId() {
            return ysUserId;
        }

        public void setYsUserId(Object ysUserId) {
            this.ysUserId = ysUserId;
        }

        public String getName() {
            return name == null?"":name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIphone() {
            return iphone==null?"":iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public String getJjphone() {
            return jjphone == null?"":jjphone;
        }

        public void setJjphone(String jjphone) {
            this.jjphone = jjphone;
        }

        public String getDspdate() {
            return dspdate;
        }

        public void setDspdate(String dspdate) {
            this.dspdate = dspdate;
        }

        public Object getSgdate() {
            return sgdate;
        }

        public void setSgdate(Object sgdate) {
            this.sgdate = sgdate;
        }

        public boolean isIshave() {
            return ishave;
        }

        public void setIshave(boolean ishave) {
            this.ishave = ishave;
        }

        public Object getSpUserId() {
            return spUserId;
        }

        public void setSpUserId(Object spUserId) {
            this.spUserId = spUserId;
        }

        public String getRemark() {
            return remark==null?"":remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getDysdate() {
            return dysdate;
        }

        public void setDysdate(Object dysdate) {
            this.dysdate = dysdate;
        }

        public ApplyChildEntity getApplyChildEntity() {
            return applyChildEntity;
        }

        public void setApplyChildEntity(ApplyChildEntity applyChildEntity) {
            this.applyChildEntity = applyChildEntity;
        }

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
        }

        public List<ApplyFileEntity> getApplyFileList() {
            return applyFileList;
        }

        public void setApplyFileList(List<ApplyFileEntity> applyFileList) {
            this.applyFileList = applyFileList;
        }

        public List<ApplyLogEntity> getApplyLogEntityList() {
            return applyLogEntityList;
        }

        public void setApplyLogEntityList(List<ApplyLogEntity> applyLogEntityList) {
            this.applyLogEntityList = applyLogEntityList;
        }

        public static class YlEntityBean {
            /**
             * gid : 93776
             * area : 263
             * jianzhuArea : 171
             * name : 赵胜利
             * remark : 一院多门牌，406户籍也在此
             * mph : 北京市大兴区黄村镇西芦城村丰收巷8号
             * ylType : 1
             * ylTypeText : 宅基地
             * legent : 1
             * isidle : 0
             * isidleText : 否
             * xzqmc : 西芦城村
             * objectid : 91856
             * pzmj : null
             * bh : 124
             * ysbh : null
             * pzbm : null
             * pzwh : null
             * pzsj : null
             * pzyt : null
             * qydj : null
             * rk : 0
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
             * hzmc : 赵红雨
             * yllegent : 1
             * center : POINT(116.290562123088 39.7513478558907)
             * tsqk : 6
             * rjl : 0.4
             * dlr : null
             * dlrname : null
             * geometry : null
             * nongcount : 3
             * feinongcount : 0
             * hucount : 1
             * apptype : 2
             * apptypeText : 待审核
             * sqtype : 0
             * lztype : 0
             * tctype : 0
             * qsxzList : null
             * syqlxList : null
             * cqlyList : null
             * ldArea : 0.39
             * ldRk : 0
             * ldQdfs : 0.4
             * ldRjl : 0.65
             * ldDjgx : 0.3
             */

            private int gid;
            private int area;
            private int jianzhuArea;
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
            private String tsqk;
            private double rjl;
            private Object dlr;
            private Object dlrname;
            private Object geometry;
            private int nongcount;
            private int feinongcount;
            private int hucount;
            private int apptype;
            private String apptypeText;
            private int sqtype;
            private int lztype;
            private int tctype;
            private Object qsxzList;
            private Object syqlxList;
            private Object cqlyList;
            private double ldArea;
            private int ldRk;
            private double ldQdfs;
            private double ldRjl;
            private double ldDjgx;

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public int getArea() {
                return area;
            }

            public void setArea(int area) {
                this.area = area;
            }

            public int getJianzhuArea() {
                return jianzhuArea;
            }

            public void setJianzhuArea(int jianzhuArea) {
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
                return mph;
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

            public Object getPzmj() {
                return pzmj;
            }

            public void setPzmj(Object pzmj) {
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
                return code;
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
                return hzmc;
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

            public Object getGeometry() {
                return geometry;
            }

            public void setGeometry(Object geometry) {
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

            public int getApptype() {
                return apptype;
            }

            public void setApptype(int apptype) {
                this.apptype = apptype;
            }

            public String getApptypeText() {
                return apptypeText;
            }

            public void setApptypeText(String apptypeText) {
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

            public int getLdRk() {
                return ldRk;
            }

            public void setLdRk(int ldRk) {
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

        public static class ApplyFileListBean {
            /**
             * id : 183
             * appId : 20
             * ylId : 91856
             * fileName : 奥迪.png
             * url : applyFile\20\20200515152150838.png
             * update : 1589472000000
             * cltype : 1
             * cltypeText : 翻建申请书
             * remark : null
             * applyEntity : null
             */

            private int id;
            private int appId;
            private int ylId;
            private String fileName;
            private String url;
            private long update;
            private int cltype;
            private String cltypeText;
            private Object remark;
            private Object applyEntity;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAppId() {
                return appId;
            }

            public void setAppId(int appId) {
                this.appId = appId;
            }

            public int getYlId() {
                return ylId;
            }

            public void setYlId(int ylId) {
                this.ylId = ylId;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public long getUpdate() {
                return update;
            }

            public void setUpdate(long update) {
                this.update = update;
            }

            public int getCltype() {
                return cltype;
            }

            public void setCltype(int cltype) {
                this.cltype = cltype;
            }

            public String getCltypeText() {
                return cltypeText;
            }

            public void setCltypeText(String cltypeText) {
                this.cltypeText = cltypeText;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getApplyEntity() {
                return applyEntity;
            }

            public void setApplyEntity(Object applyEntity) {
                this.applyEntity = applyEntity;
            }
        }
    }
}
