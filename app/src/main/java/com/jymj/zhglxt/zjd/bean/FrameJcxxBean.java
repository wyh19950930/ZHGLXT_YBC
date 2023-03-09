package com.jymj.zhglxt.zjd.bean;

import com.jymj.zhglxt.rjhj.bean.enums.HasoccupyEnum;
import com.jymj.zhglxt.rjhj.bean.enums.IsTrueEnum;
import com.jymj.zhglxt.rjhj.bean.enums.QsxzEnum;
import com.jymj.zhglxt.rjhj.bean.enums.SyqlxEnum;
import com.jymj.zhglxt.rjhj.bean.enums.TypeEnum;
import com.jymj.zhglxt.rjhj.bean.enums.YlTdlyEnum;
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEnum;
import com.jymj.zhglxt.zjd.bean.yzt.tg.TgEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2020/6/4 9:26
 */
public class FrameJcxxBean {

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

        private KeyValueEntityBean keyValueEntity;
        private List<FeizhaiBean> feizhai;
        private List<ZyjzBean> zyjz;
        private List<TgEntityListBean> tgEntityList;
        private List<TdlyEntitiesBean> tdlyEntities;
        private List<TgFhEntity> tgEntities;
        private List<YlEntityListBean> ylEntityList;
        private List<FloatFhEntity> floatFhEntities;
        private List<LdrkBeans> ldrk;

        public List<FloatFhEntity> getFloatFhEntities() {
            return floatFhEntities==null?new ArrayList<>():floatFhEntities;
        }

        public void setFloatFhEntities(List<FloatFhEntity> floatFhEntities) {
            this.floatFhEntities = floatFhEntities;
        }

        public List<LdrkBeans> getLdrk() {
            return ldrk==null?new ArrayList<>():ldrk;
        }

        public void setLdrk(List<LdrkBeans> ldrk) {
            this.ldrk = ldrk;
        }

        public List<FeizhaiBean> getFeizhai() {
            return feizhai==null?new ArrayList<>():feizhai;
        }

        public void setFeizhai(List<FeizhaiBean> feizhai) {
            this.feizhai = feizhai;
        }

        public KeyValueEntityBean getKeyValueEntity() {
            return keyValueEntity;
        }

        public void setKeyValueEntity(KeyValueEntityBean keyValueEntity) {
            this.keyValueEntity = keyValueEntity;
        }

        public List<ZyjzBean> getZyjz() {
            return zyjz==null?new ArrayList<>():zyjz;
        }

        public void setZyjz(List<ZyjzBean> zyjz) {
            this.zyjz = zyjz;
        }

        public List<TgEntityListBean> getTgEntityList() {
            return tgEntityList==null?new ArrayList<>():tgEntityList;
        }

        public void setTgEntityList(List<TgEntityListBean> tgEntityList) {
            this.tgEntityList = tgEntityList;
        }

        public List<TdlyEntitiesBean> getTdlyEntities() {
            return tdlyEntities==null?new ArrayList<>():tdlyEntities;
        }

        public void setTdlyEntities(List<TdlyEntitiesBean> tdlyEntities) {
            this.tdlyEntities = tdlyEntities;
        }

        public List<TgFhEntity> getTgEntities() {
            return tgEntities==null?new ArrayList<>():tgEntities;
        }

        public void setTgEntities(List<TgFhEntity> tgEntities) {
            this.tgEntities = tgEntities;
        }

        public List<YlEntityListBean> getYlEntityList() {
            return ylEntityList==null?new ArrayList<>():ylEntityList;
        }

        public void setYlEntityList(List<YlEntityListBean> ylEntityList) {
            this.ylEntityList = ylEntityList;
        }

        public static class LdrkBeans{
            private String object1;
            private BigDecimal object2;
            private BigDecimal object3;
            private BigDecimal object4;

            public String getObject1() {
                return object1;
            }

            public void setObject1(String object1) {
                this.object1 = object1;
            }

            public BigDecimal getObject2() {
                return object2==null?new BigDecimal(0):object2;
            }

            public void setObject2(BigDecimal object2) {
                this.object2 = object2;
            }

            public BigDecimal getObject3() {
                return object3==null?new BigDecimal(0):object3;
            }

            public void setObject3(BigDecimal object3) {
                this.object3 = object3;
            }

            public BigDecimal getObject4() {
                return object4==null?new BigDecimal(0):object4;
            }

            public void setObject4(BigDecimal object4) {
                this.object4 = object4;
            }
        }

        public static class KeyValueEntityBean {
            /**
             * object1 : 309029
             * object2 : 49720
             * object3 : 0
             * object4 : 0
             * object5 : 200883
             * object6 : 32320
             * object7 : 0
             * object8 : 0
             * object9 : null
             * object10 : null
             * industry : null
             * hylxText : null
             */

            private BigDecimal object1;
            private BigDecimal object2;
            private BigDecimal object3;
            private BigDecimal object4;
            private BigDecimal object5;
            private BigDecimal object6;
            private BigDecimal object7;
            private BigDecimal object8;
            private Object object9;
            private Object object10;
            private Object industry;
            private Object hylxText;

            public BigDecimal getObject1() {
                return object1== null ? new BigDecimal(0) :object1;
            }

            public void setObject1(BigDecimal object1) {
                this.object1 = object1;
            }

            public BigDecimal getObject2() {
                return object2== null ? new BigDecimal(0) :object2;
            }

            public void setObject2(BigDecimal object2) {
                this.object2 = object2;
            }

            public BigDecimal getObject3() {
                return object3== null ? new BigDecimal(0) :object3;
            }

            public void setObject3(BigDecimal object3) {
                this.object3 = object3;
            }

            public BigDecimal getObject4() {
                return object4== null ? new BigDecimal(0) :object4;
            }

            public void setObject4(BigDecimal object4) {
                this.object4 = object4;
            }

            public BigDecimal getObject5() {
                return object5== null ? new BigDecimal(0) :object5;
            }

            public void setObject5(BigDecimal object5) {
                this.object5 = object5;
            }

            public BigDecimal getObject6() {
                return object6== null ? new BigDecimal(0) :object6;
            }

            public void setObject6(BigDecimal object6) {
                this.object6 = object6;
            }

            public BigDecimal getObject7() {
                return object7== null ? new BigDecimal(0) :object7;
            }

            public void setObject7(BigDecimal object7) {
                this.object7 = object7;
            }

            public BigDecimal getObject8() {
                return object8== null ? new BigDecimal(0) :object8;
            }

            public void setObject8(BigDecimal object8) {
                this.object8 = object8;
            }

            public Object getObject9() {
                return object9;
            }

            public void setObject9(Object object9) {
                this.object9 = object9;
            }

            public Object getObject10() {
                return object10;
            }

            public void setObject10(Object object10) {
                this.object10 = object10;
            }

            public Object getIndustry() {
                return industry;
            }

            public void setIndustry(Object industry) {
                this.industry = industry;
            }

            public Object getHylxText() {
                return hylxText;
            }

            public void setHylxText(Object hylxText) {
                this.hylxText = hylxText;
            }
        }
        public static class FeizhaiBean {
            /**
             * object1 : 683
             * object2 : 470
             * object3 : 1
             * industry : 5
             * hylxText : 批发零售业
             */

            private BigDecimal object1;
            private BigDecimal object2;
            private int object3;
            private int industry;
            private String hylxText;

            public BigDecimal getObject1() {
                return object1 == null?new BigDecimal(0):object1;
            }

            public void setObject1(BigDecimal object1) {
                this.object1 = object1;
            }

            public BigDecimal getObject2() {
                return object2 == null?new BigDecimal(0):object2;
            }

            public void setObject2(BigDecimal object2) {
                this.object2 = object2;
            }

            public int getObject3() {
                return object3;
            }

            public void setObject3(int object3) {
                this.object3 = object3;
            }

            public int getIndustry() {
                return industry;
            }

            public void setIndustry(int industry) {
                this.industry = industry;
            }

            public String getHylxText() {
                return hylxText == null?"":hylxText;
            }

            public void setHylxText(String hylxText) {
                this.hylxText = hylxText;
            }
        }
        public static class ZyjzBean {
            /**
             * object1 : null
             * object2 : 未开发
             * object3 : 100
             * object4 : 4033.1
             * object5 : 0
             * object6 : null
             * object7 : null
             * object8 : null
             * object9 : null
             * object10 : null
             * industry : null
             * hylxText : null
             */

            private Object object1;
            private String object2;
            private double object3;
            private double object4;
            private int object5;
            private Object object6;
            private Object object7;
            private Object object8;
            private Object object9;
            private Object object10;
            private Object industry;
            private Object hylxText;

            public Object getObject1() {
                return object1;
            }

            public void setObject1(Object object1) {
                this.object1 = object1;
            }

            public String getObject2() {
                return object2;
            }

            public void setObject2(String object2) {
                this.object2 = object2;
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

            public int getObject5() {
                return object5;
            }

            public void setObject5(int object5) {
                this.object5 = object5;
            }

            public Object getObject6() {
                return object6;
            }

            public void setObject6(Object object6) {
                this.object6 = object6;
            }

            public Object getObject7() {
                return object7;
            }

            public void setObject7(Object object7) {
                this.object7 = object7;
            }

            public Object getObject8() {
                return object8;
            }

            public void setObject8(Object object8) {
                this.object8 = object8;
            }

            public Object getObject9() {
                return object9;
            }

            public void setObject9(Object object9) {
                this.object9 = object9;
            }

            public Object getObject10() {
                return object10;
            }

            public void setObject10(Object object10) {
                this.object10 = object10;
            }

            public Object getIndustry() {
                return industry;
            }

            public void setIndustry(Object industry) {
                this.industry = industry;
            }

            public Object getHylxText() {
                return hylxText;
            }

            public void setHylxText(Object hylxText) {
                this.hylxText = hylxText;
            }
        }

        public static class TgEntityListBean {
            /**
             * gid : null
             * xzqmc : null
             * landName : 城镇建设用地区
             * area : 52
             * area1 : 8897
             * code : null
             * type : null
             * geometry : null
             * center : null
             */

            private Object gid;
            private Object xzqmc;
            private String landName;
            private float area;
            private float area1;
            private Object code;
            private Object type;
            private Object geometry;
            private Object center;

            public Object getGid() {
                return gid;
            }

            public void setGid(Object gid) {
                this.gid = gid;
            }

            public Object getXzqmc() {
                return xzqmc;
            }

            public void setXzqmc(Object xzqmc) {
                this.xzqmc = xzqmc;
            }

            public String getLandName() {
                return landName;
            }

            public void setLandName(String landName) {
                this.landName = landName;
            }

            public float getArea() {
                return area;
            }

            public void setArea(float area) {
                this.area = area;
            }

            public float getArea1() {
                return area1;
            }

            public void setArea1(float area1) {
                this.area1 = area1;
            }

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getGeometry() {
                return geometry;
            }

            public void setGeometry(Object geometry) {
                this.geometry = geometry;
            }

            public Object getCenter() {
                return center;
            }

            public void setCenter(Object center) {
                this.center = center;
            }
        }

        public static class TdlyEntitiesBean {
            /**
             * gid : null
             * dlbm : null
             * dlmc : null
             * qsxz : null
             * qsdwmc : null
             * xzqmc : null
             * area : 489
             * area1 : 1311961
             * code : null
             * type : null
             * geometry : null
             * center : null
             * elx : null
             * lx : 建设用地
             * children : [{"gid":null,"dlbm":null,"dlmc":null,"qsxz":null,"qsdwmc":null,"xzqmc":null,"area":489,"area1":1311961,"code":null,"type":null,"geometry":null,"center":null,"elx":"城镇村及工矿用地","lx":"建设用地","children":null}]
             */

            private Object gid;
            private Object dlbm;
            private Object dlmc;
            private Object qsxz;
            private Object qsdwmc;
            private Object xzqmc;
            private BigDecimal area;
            private BigDecimal area1;
            private Object code;
            private Object type;
            private Object geometry;
            private Object center;
            private Object elx;
            private String lx;
            private List<ChildrenBean> children;

            public Object getGid() {
                return gid;
            }

            public void setGid(Object gid) {
                this.gid = gid;
            }

            public Object getDlbm() {
                return dlbm;
            }

            public void setDlbm(Object dlbm) {
                this.dlbm = dlbm;
            }

            public Object getDlmc() {
                return dlmc;
            }

            public void setDlmc(Object dlmc) {
                this.dlmc = dlmc;
            }

            public Object getQsxz() {
                return qsxz;
            }

            public void setQsxz(Object qsxz) {
                this.qsxz = qsxz;
            }

            public Object getQsdwmc() {
                return qsdwmc;
            }

            public void setQsdwmc(Object qsdwmc) {
                this.qsdwmc = qsdwmc;
            }

            public Object getXzqmc() {
                return xzqmc;
            }

            public void setXzqmc(Object xzqmc) {
                this.xzqmc = xzqmc;
            }

            public BigDecimal getArea() {
                return area==null?new BigDecimal(0):area;
            }

            public void setArea(BigDecimal area) {
                this.area = area;
            }

            public BigDecimal getArea1() {
                return area1;
            }

            public void setArea1(BigDecimal area1) {
                this.area1 = area1;
            }

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getGeometry() {
                return geometry;
            }

            public void setGeometry(Object geometry) {
                this.geometry = geometry;
            }

            public Object getCenter() {
                return center;
            }

            public void setCenter(Object center) {
                this.center = center;
            }

            public Object getElx() {
                return elx;
            }

            public void setElx(Object elx) {
                this.elx = elx;
            }

            public String getLx() {
                return lx;
            }

            public void setLx(String lx) {
                this.lx = lx;
            }

            public List<ChildrenBean> getChildren() {
                return children==null?new ArrayList<>():children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * gid : null
                 * dlbm : null
                 * dlmc : null
                 * qsxz : null
                 * qsdwmc : null
                 * xzqmc : null
                 * area : 489
                 * area1 : 1311961
                 * code : null
                 * type : null
                 * geometry : null
                 * center : null
                 * elx : 城镇村及工矿用地
                 * lx : 建设用地
                 * children : null
                 */

                private Object gid;
                private Object dlbm;
                private Object dlmc;
                private Object qsxz;
                private Object qsdwmc;
                private Object xzqmc;
                private BigDecimal area;
                private BigDecimal area1;
                private Object code;
                private Object type;
                private Object geometry;
                private Object center;
                private String elx;
                private String lx;
                private Object children;

                public Object getGid() {
                    return gid;
                }

                public void setGid(Object gid) {
                    this.gid = gid;
                }

                public Object getDlbm() {
                    return dlbm;
                }

                public void setDlbm(Object dlbm) {
                    this.dlbm = dlbm;
                }

                public Object getDlmc() {
                    return dlmc;
                }

                public void setDlmc(Object dlmc) {
                    this.dlmc = dlmc;
                }

                public Object getQsxz() {
                    return qsxz;
                }

                public void setQsxz(Object qsxz) {
                    this.qsxz = qsxz;
                }

                public Object getQsdwmc() {
                    return qsdwmc;
                }

                public void setQsdwmc(Object qsdwmc) {
                    this.qsdwmc = qsdwmc;
                }

                public Object getXzqmc() {
                    return xzqmc;
                }

                public void setXzqmc(Object xzqmc) {
                    this.xzqmc = xzqmc;
                }

                public BigDecimal getArea() {
                    return area==null?new BigDecimal(0):area;
                }

                public void setArea(BigDecimal area) {
                    this.area = area;
                }

                public BigDecimal getArea1() {
                    return area1;
                }

                public void setArea1(BigDecimal area1) {
                    this.area1 = area1;
                }

                public Object getCode() {
                    return code;
                }

                public void setCode(Object code) {
                    this.code = code;
                }

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

                public Object getGeometry() {
                    return geometry;
                }

                public void setGeometry(Object geometry) {
                    this.geometry = geometry;
                }

                public Object getCenter() {
                    return center;
                }

                public void setCenter(Object center) {
                    this.center = center;
                }

                public String getElx() {
                    return elx==null?"":elx;
                }

                public void setElx(String elx) {
                    this.elx = elx;
                }

                public String getLx() {
                    return lx;
                }

                public void setLx(String lx) {
                    this.lx = lx;
                }

                public Object getChildren() {
                    return children;
                }

                public void setChildren(Object children) {
                    this.children = children;
                }
            }
        }

        public static class TgFhEntity {

            private String proj;
            private List<TgEntity> tgEntities;

            public String getProj() {
                return proj;
            }

            public void setProj(String proj) {
                this.proj = proj;
            }

            public List<TgEntity> getTgEntities() {
                return tgEntities==null?new ArrayList<>():tgEntities;
            }

            public void setTgEntities(List<TgEntity> tgEntities) {
                this.tgEntities = tgEntities;
            }

        }

        public static class YlEntityListBean {
            private Integer gid;
            //面积
            private BigDecimal area;
            //建筑面积
            private BigDecimal jianzhuArea;
            //name 放企业名称 给图层显示用
            private String name;
            //备注
            private String remark;
            //门牌号
            private String mph;
            //用地类型
            private Integer ylType;
            private String ylTypeText;
            //图层颜色显示123456
            private Integer legent;
            //是否闲置
            private Integer isidle;
            private String isidleText;
            //村名
            private String xzqmc;
            //院落id
            private Integer objectid;
            //批准面积
            private BigDecimal pzmj;
            //编号
            private String bh;
            //原始编号
            private String ysbh;
            //批准部门
            private String pzbm;
            //批准文号
            private String pzwh;
            //批准时间
            private String pzsj;
            //批准用途
            private String pzyt;
            //区域地价
            private BigDecimal qydj;
            //人口
            private Integer rk;
            //权属性质
            private Integer qsxz;
            private String qsxzText;
            //使用权类型
            private Integer syqlx;
            private String syqlxText;
            //土地来源
            private Integer tdly;
            private String tdlyText;
            //是否有超占  0完全  1部分  2无
            private Integer hasoccupy;
            private String hasoccupyText;
            //产权来源
            private Integer cqly;
            private String cqlyText;
            private String code;
            //产权人
            private Integer zhaiid;
            private String zhaiName;
            //户主名称
            private String hzmc;
            //yl是否添加mph
            private Integer yllegent;
            //中心点
            private String center;
            //特殊情况（取得方式） 1其他、2买卖、3继承、4审批
            private String tsqk;
            //容积率
            private BigDecimal rjl;
            //登录人
            private Integer dlr;
            private String dlrname;
            //院落几何形状 by DL 2018年1月23日
            private String geometry;
            private Integer nongcount;
            private Integer feinongcount;
            private int zjdcount;

            //户数
            private Integer hucount;
            //'宅基地翻建状态'
            private Integer apptype;
            private String apptypeText;
            private Integer sqtype;//申请状态
            private Integer lztype;//流转状态
            private Integer tctype;//退出状态
            //无用
            private List<Integer> qsxzList;
            private List<Integer> syqlxList;
            private List<Integer> cqlyList;

            //雷达图 用地面积
            private BigDecimal ldArea;
            //雷达图 入口数
            private BigDecimal ldRk;
            //雷达图 取得方式
            private BigDecimal ldQdfs;
            //雷达图 容积率
            private BigDecimal ldRjl;
            //雷达图 地价关系
            private BigDecimal ldDjgx;

            //几分地面积
            private BigDecimal fdq4;
            private BigDecimal fdq46;
            private BigDecimal fdq68;
            private BigDecimal fdq8;
            private BigDecimal fdqzmj;
            //几分地数量
            private Integer fdq4count;
            private Integer fdq46count;
            private Integer fdq68count;
            private Integer fdq8count;
            private Integer fdqcount;

            private Integer nan;
            private Integer nv;
            private BigDecimal hjmj;
            private Integer ldrks;

            public Integer getLdrks() {
                return ldrks==null?0:ldrks;
            }

            public void setLdrks(Integer ldrks) {
                this.ldrks = ldrks;
            }

            public int getZjdcount() {
                return zjdcount;
            }

            public void setZjdcount(int zjdcount) {
                this.zjdcount = zjdcount;
            }

            public BigDecimal getHjmj() {
                return hjmj== null ? new BigDecimal(0) :hjmj;
            }

            public void setHjmj(BigDecimal hjmj) {
                this.hjmj = hjmj;
            }

            public Integer getNan() {
                return nan;
            }

            public void setNan(Integer nan) {
                this.nan = nan;
            }

            public Integer getNv() {
                return nv;
            }

            public void setNv(Integer nv) {
                this.nv = nv;
            }

            public BigDecimal getFdq4() {
                return fdq4 == null ? new BigDecimal(0) : fdq4.setScale(1,BigDecimal.ROUND_HALF_UP);
            }

            public void setFdq4(BigDecimal fdq4) {
                this.fdq4 = fdq4;
            }

            public BigDecimal getFdq46() {
                return fdq46 == null ? new BigDecimal(0) : fdq46.setScale(1,BigDecimal.ROUND_HALF_UP);
            }

            public void setFdq46(BigDecimal fdq46) {
                this.fdq46 = fdq46;
            }

            public BigDecimal getFdq68() {
                return fdq68 == null ? new BigDecimal(0) : fdq68.setScale(1,BigDecimal.ROUND_HALF_UP);
            }

            public void setFdq68(BigDecimal fdq68) {
                this.fdq68 = fdq68;
            }

            public BigDecimal getFdq8() {
                return fdq8 == null ? new BigDecimal(0) : fdq8.setScale(1,BigDecimal.ROUND_HALF_UP);
            }

            public void setFdq8(BigDecimal fdq8) {
                this.fdq8 = fdq8;
            }

            public BigDecimal getFdqzmj() {
                return getFdq4().add(getFdq46()).add(getFdq68()).add(getFdq8());
            }

            public Integer getFdq4count() {
                return fdq4count == null ? 0 :fdq4count;
            }

            public void setFdq4count(Integer fdq4count) {
                this.fdq4count = fdq4count;
            }

            public Integer getFdq46count() {
                return fdq46count== null ? 0 :fdq46count;
            }

            public void setFdq46count(Integer fdq46count) {
                this.fdq46count = fdq46count;
            }

            public Integer getFdq68count() {
                return fdq68count== null ? 0 :fdq68count;
            }

            public void setFdq68count(Integer fdq68count) {
                this.fdq68count = fdq68count;
            }

            public Integer getFdq8count() {
                return fdq8count== null ? 0 :fdq8count;
            }

            public void setFdq8count(Integer fdq8count) {
                this.fdq8count = fdq8count;
            }

            public Integer getFdqcount() {
                return getFdq4count()+getFdq46count()+getFdq68count()+getFdq8count();
            }

            public Integer getSqtype() {
                return sqtype;
            }

            public void setSqtype(Integer sqtype) {
                this.sqtype = sqtype;
            }

            public Integer getLztype() {
                return lztype;
            }

            public void setLztype(Integer lztype) {
                this.lztype = lztype;
            }

            public Integer getTctype() {
                return tctype;
            }

            public void setTctype(Integer tctype) {
                this.tctype = tctype;
            }

            public Integer getGid() {
                return gid;
            }

            public void setGid(Integer gid) {
                this.gid = gid;
            }

            public BigDecimal getArea() {
                return area== null ? new BigDecimal(0) :area;
            }

            public void setArea(BigDecimal area) {
                this.area = area;
            }

            public BigDecimal getJianzhuArea() {
                return jianzhuArea == null ? new BigDecimal(0) : jianzhuArea.setScale(2, RoundingMode.HALF_UP);
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
                return mph;
            }

            public void setMph(String mph) {
                this.mph = mph;
            }

            public Integer getYlType() {
                return ylType;
            }

            public void setYlType(Integer ylType) {
                this.ylType = ylType;
            }

            public Integer getLegent() {
                return legent;
            }

            public void setLegent(Integer legent) {
                this.legent = legent;
            }

            public Integer getIsidle() {
                return isidle;
            }

            public void setIsidle(Integer isidle) {
                this.isidle = isidle;
            }

            public String getXzqmc() {
                return xzqmc;
            }

            public void setXzqmc(String xzqmc) {
                this.xzqmc = xzqmc;
            }

            public Integer getObjectid() {
                return objectid;
            }

            public void setObjectid(Integer objectid) {
                this.objectid = objectid;
            }

            public BigDecimal getPzmj() {
                return pzmj== null ? new BigDecimal(0) :pzmj;
            }

            public void setPzmj(BigDecimal pzmj) {
                this.pzmj = pzmj;
            }

            public String getPzbm() {
                return pzbm;
            }

            public void setPzbm(String pzbm) {
                this.pzbm = pzbm;
            }

            public String getPzwh() {
                return pzwh;
            }

            public void setPzwh(String pzwh) {
                this.pzwh = pzwh;
            }

            public String getPzsj() {
                return pzsj;
            }

            public void setPzsj(String pzsj) {
                this.pzsj = pzsj;
            }

            public String getPzyt() {
                return pzyt;
            }

            public void setPzyt(String pzyt) {
                this.pzyt = pzyt;
            }

            public BigDecimal getQydj() {
                return qydj== null ? new BigDecimal(0) :qydj;
            }

            public void setQydj(BigDecimal qydj) {
                this.qydj = qydj;
            }

            public Integer getRk() {
                return rk == null ? 0 : rk;
            }

            public void setRk(Integer rk) {
                this.rk = rk;
            }

            public Integer getQsxz() {
                return qsxz;
            }

            public void setQsxz(Integer qsxz) {
                this.qsxz = qsxz;
            }

            public Integer getSyqlx() {
                return syqlx;
            }

            public void setSyqlx(Integer syqlx) {
                this.syqlx = syqlx;
            }

            public Integer getTdly() {
                return tdly;
            }

            public void setTdly(Integer tdly) {
                this.tdly = tdly;
            }

            public Integer getHasoccupy() {
                return hasoccupy;
            }

            public void setHasoccupy(Integer hasoccupy) {
                this.hasoccupy = hasoccupy;
            }

            public Integer getCqly() {
                return cqly;
            }

            public List getQsxzList() {
                return qsxzList;
            }

            public void setQsxzList(List qsxzList) {
                this.qsxzList = qsxzList;
            }

            public List getSyqlxList() {
                return syqlxList;
            }

            public void setSyqlxList(List syqlxList) {
                this.syqlxList = syqlxList;
            }

            public List getCqlyList() {
                return cqlyList;
            }

            public void setCqlyList(List cqlyList) {
                this.cqlyList = cqlyList;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setCqly(Integer cqly) {
                this.cqly = cqly;
            }

            public Integer getZhaiid() {
                return zhaiid;
            }

            public void setZhaiid(Integer zhaiid) {
                this.zhaiid = zhaiid;
            }

            public String getZhaiName() {
                return zhaiName;
            }

            public void setZhaiName(String zhaiName) {
                this.zhaiName = zhaiName;
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

            public Integer getDlr() {
                return dlr;
            }

            public void setDlr(Integer dlr) {
                this.dlr = dlr;
            }

            public String getDlrname() {
                return dlrname;
            }

            public void setDlrname(String dlrname) {
                this.dlrname = dlrname;
            }

            public Integer getYllegent() {
                return yllegent;
            }

            public void setYllegent(Integer yllegent) {
                this.yllegent = yllegent;
            }

            public String getHzmc() {
                return hzmc;
            }

            public void setHzmc(String hzmc) {
                this.hzmc = hzmc;
            }

            public Integer getNongcount() {
                return nongcount == null ? 0 : nongcount;
            }

            public void setNongcount(Integer nongcount) {
                this.nongcount = nongcount;
            }

            public Integer getFeinongcount() {
                return feinongcount == null ? 0 : feinongcount;
            }

            public void setFeinongcount(Integer feinongcount) {
                this.feinongcount = feinongcount;
            }

            public Integer getHucount() {
                return hucount == null ? 0 : hucount;
            }

            public void setHucount(Integer hucount) {
                this.hucount = hucount;
            }

            public Integer getApptype() {
                return apptype;
            }

            public void setApptype(Integer apptype) {
                this.apptype = apptype;
            }

            public String getBh() {
                return bh;
            }

            public void setBh(String bh) {
                this.bh = bh;
            }

            public String getYsbh() {
                return ysbh;
            }

            public void setYsbh(String ysbh) {
                this.ysbh = ysbh;
            }


            public String getYlTypeText() {
                if (getYlType() == null)
                    return null;
                return TypeEnum.getName(getYlType());
            }

            public String getIsidleText() {
                if (getIsidle() == null)
                    return null;
                return IsTrueEnum.getName(getIsidle());
            }

            //qsxzText  TODO: 2017/11/15
            public String getQsxzText() {
                if (getQsxz() == null)
                    return null;
                return QsxzEnum.getName(getQsxz());
            }

            // syqlyText TODO: 2017/11/15
            public String getSyqlxText() {
                if (getSyqlx() == null)
                    return null;
                return SyqlxEnum.getName(getSyqlx());
            }
            public String getApptypeText(){
                if (getApptype()== null)
                    return null;
                return ApplyEnum.getName(getApptype());
            }

            // tdlyText TODO: 2017/11/15
            public String getTdlyText() {
                if (getTdly() == null)
                    return null;
                return YlTdlyEnum.getName(getTdly());
            }

            public String getHasoccupyText() {
                if (getHasoccupy() == null)
                    return null;
                return HasoccupyEnum.getName(getTdly());
            }

            public String getGeometry() {
                return geometry;
            }

            public void setGeometry(String geometry) {
                this.geometry = geometry;
            }

            public BigDecimal getRjl() {
                return rjl == null ? new BigDecimal(0.4) : rjl;
            }

            public void setRjl(BigDecimal rjl) {
                this.rjl = rjl;
            }

            public BigDecimal getLdArea() {
                return ((area == null ? new BigDecimal(0) : area)
                        .multiply(new BigDecimal(0.0015)))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            public BigDecimal getLdRk() {
                return new BigDecimal(rk == null ? 0 : rk).multiply(new BigDecimal(0.1));
            }

            public BigDecimal getLdQdfs() {
                return new BigDecimal(0.4);
            }

            public BigDecimal getLdRjl() {
                if (area != null){
                    return ((jianzhuArea == null ? new BigDecimal(0) : jianzhuArea)
                            .divide((area == null ? new BigDecimal(0) : area),2, BigDecimal.ROUND_HALF_UP));
                }
                return null;
            }

            public BigDecimal getLdDjgx() {
                BigDecimal hCount = new BigDecimal(getHucount() == null ? 0 : getHucount());
                return hCount.multiply(new BigDecimal(0.3))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }
    }
}
