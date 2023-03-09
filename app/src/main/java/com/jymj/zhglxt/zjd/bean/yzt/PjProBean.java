package com.jymj.zhglxt.zjd.bean.yzt;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/6/24 14:51
 */
public class PjProBean {

    /**
     * msg : null
     * code : 0
     * data : {"totalCount":1,"pageSize":999,"totalPage":1,"currPage":1,"list":[{"id":1307,"xzqId":null,"createPsn":null,"createDate":null,"remark":"测试","orderId":1,"impUnit":"1111","planFw":"111","build_content":null,"projGeo":null,"projCoor":null,"projDeclare":null,"projScale":15,"scaleCycle":null,"projInvest":null,"xzgdmj":null,"msid":1,"scalestartdate":null,"scaleenddate":null,"parentid":null,"projSpeed":1,"projPay":1,"projAmount":null,"name":"项目名","ids":null,"pids":null}]}
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
         * totalCount : 1
         * pageSize : 999
         * totalPage : 1
         * currPage : 1
         * list : [{"id":1307,"xzqId":null,"createPsn":null,"createDate":null,"remark":"测试","orderId":1,"impUnit":"1111","planFw":"111","build_content":null,"projGeo":null,"projCoor":null,"projDeclare":null,"projScale":15,"scaleCycle":null,"projInvest":null,"xzgdmj":null,"msid":1,"scalestartdate":null,"scaleenddate":null,"parentid":null,"projSpeed":1,"projPay":1,"projAmount":null,"name":"项目名","ids":null,"pids":null}]
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
            private int id;
            private Integer xzqId;
            private Integer createPsn;
            private String createDate;
            private String remark;	//简介
            private Integer orderId;
            private String impUnit;//承担单位
            private String planFw;	//项目涉及乡镇、行政村
            private String build_content;//项目四至
            private Integer projGeo;//地貌类型
            private String projCoor;//项目坐标
            private Integer projDeclare;//项目类型
            private BigDecimal projScale;//项目建设规模 单位亩
            private String scaleCycle;//建设工期
            private BigDecimal projInvest;	//投资估算  计划投资额
            private BigDecimal xzgdmj;//新增耕地面积 单位亩
            private Integer msid;//图层外键（位置）--片区
            private String scalestartdate;//工期开始日期
            private String scaleenddate;//工期结束日期
            private Integer parentid;
            private Integer projSpeed;	//项目进度步骤
            private String projSpeedText;
            private Integer projPay;//支付情况  1-已支付 0-未支付
            private BigDecimal projAmount;//支付金额 单位万元
            private String name;
            private String cqr;//产权人或承租人
            private String qyName;//企业名称
            private BigDecimal htmj	;//合同租地面积
            private BigDecimal jzmj;//实测建筑面积
            private int cqz;//有无产权证 0无  1有
            private String mobile;//联系电话
            private BigDecimal cost;
            private Integer[] ids;
            private Integer[] pids;

            public String getCqr() {
                return cqr;
            }

            public void setCqr(String cqr) {
                this.cqr = cqr;
            }

            public String getQyName() {
                return qyName;
            }

            public void setQyName(String qyName) {
                this.qyName = qyName;
            }

            public BigDecimal getHtmj() {
                return htmj;
            }

            public void setHtmj(BigDecimal htmj) {
                this.htmj = htmj;
            }

            public BigDecimal getJzmj() {
                return jzmj;
            }

            public void setJzmj(BigDecimal jzmj) {
                this.jzmj = jzmj;
            }

            public int getCqz() {
                return cqz;
            }

            public void setCqz(int cqz) {
                this.cqz = cqz;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public BigDecimal getCost() {
                return cost;
            }

            public void setCost(BigDecimal cost) {
                this.cost = cost;
            }

            public String getProjSpeedText() {
                return getProjSpeed()!=null? ProjspeedEnum.getName(getProjSpeed()):null;
            }

            public void setProjSpeedText(String projSpeedText) {
                this.projSpeedText = projSpeedText;
            }

            /*public void setProjSpeedText(String projSpeedText) {
                            this.projSpeedText = projSpeedText;
                        }
                        private String getProjSpeedText(){
                            return getProjSpeed()!=null? ProjspeedEnum.getName(getProjSpeed()):null;
                        }*/
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer[] getPids() {
                return pids;
            }

            public void setPids(Integer[] pids) {
                this.pids = pids;
            }

            public Integer[] getIds() {
                return ids;
            }

            public void setIds(Integer[] ids) {
                this.ids = ids;
            }

            public Integer getProjPay() {
                return projPay;
            }

            public void setProjPay(Integer projPay) {
                this.projPay = projPay;
            }

            public BigDecimal getProjAmount() {
                return projAmount == null ? new BigDecimal(0):projAmount;
            }

            public void setProjAmount(BigDecimal projAmount) {
                this.projAmount = projAmount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Integer getXzqId() {
                return xzqId;
            }

            public void setXzqId(Integer xzqId) {
                this.xzqId = xzqId;
            }

            public Integer getCreatePsn() {
                return createPsn;
            }

            public void setCreatePsn(Integer createPsn) {
                this.createPsn = createPsn;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public Integer getOrderId() {
                return orderId;
            }

            public void setOrderId(Integer orderId) {
                this.orderId = orderId;
            }

            public String getImpUnit() {
                return impUnit;
            }

            public void setImpUnit(String impUnit) {
                this.impUnit = impUnit;
            }

            public String getPlanFw() {
                return planFw;
            }

            public void setPlanFw(String planFw) {
                this.planFw = planFw;
            }

            public String getBuild_content() {
                return build_content;
            }

            public void setBuild_content(String build_content) {
                this.build_content = build_content;
            }

            public Integer getProjGeo() {
                return projGeo;
            }

            public void setProjGeo(Integer projGeo) {
                this.projGeo = projGeo;
            }

            public String getProjCoor() {
                return projCoor;
            }

            public void setProjCoor(String projCoor) {
                this.projCoor = projCoor;
            }

            public Integer getProjDeclare() {
                return projDeclare;
            }

            public void setProjDeclare(Integer projDeclare) {
                this.projDeclare = projDeclare;
            }

            public BigDecimal getProjScale() {
                return projScale;
            }

            public void setProjScale(BigDecimal projScale) {
                this.projScale = projScale;
            }

            public String getScaleCycle() {
                return scaleCycle;
            }

            public void setScaleCycle(String scaleCycle) {
                this.scaleCycle = scaleCycle;
            }

            public BigDecimal getProjInvest() {
                return projInvest;
            }

            public void setProjInvest(BigDecimal projInvest) {
                this.projInvest = projInvest;
            }

            public BigDecimal getXzgdmj() {
                return xzgdmj;
            }

            public void setXzgdmj(BigDecimal xzgdmj) {
                this.xzgdmj = xzgdmj;
            }

            public Integer getMsid() {
                return msid;
            }

            public void setMsid(Integer msid) {
                this.msid = msid;
            }

            public String getScalestartdate() {
                return scalestartdate;
            }

            public void setScalestartdate(String scalestartdate) {
                this.scalestartdate = scalestartdate;
            }

            public String getScaleenddate() {
                return scaleenddate;
            }

            public void setScaleenddate(String scaleenddate) {
                this.scaleenddate = scaleenddate;
            }

            public Integer getParentid() {
                return parentid;
            }

            public void setParentid(Integer parentid) {
                this.parentid = parentid;
            }

            public Integer getProjSpeed() {
                return projSpeed;
            }

            public void setProjSpeed(Integer projSpeed) {
                this.projSpeed = projSpeed;
            }


        }
    }
}
