package com.jymj.zhglxt.rjhj.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @package com.jymj.projectmanager.bean
 * @fileName SysXzqEntity
 * @date 2019/3/714:16
 * @name qzw
 */
public class SysXzqEntity implements Serializable {
    /**
     * name : 小海子村
     * orderNum : null
     * parentId : 10
     * parentName : null
     * perms : null
     * type : 3
     * typeName : 村
     * xzqId : 11
     */

    private String center;
    private String name;
    private Object orderNum;
    private int parentId;
    private String parentName;
    private Object perms;
    private int type;
    private String typeName;
    private int xzqId;
    private String sortId;//用于排序的id 在这里是城市拼音的首字母
    private String sortName;//用于排序的全拼音 这个是用于后面的排序以及搜索


    /**
     * 户数
     */
    private Integer hs;
    /**
     * 人口数
     */
    private Integer rk;
    /**
     * 宅基地数
     */
    private Integer zs;
    //占地面积
    private BigDecimal zhzdmj = new BigDecimal(0);
    //建筑面积
    private BigDecimal zhjzmj = new BigDecimal(0);
    //占地面积（总计）平米 做户均使用
    private BigDecimal zhaizdmj;
    //建筑面积（总计）平米
    private BigDecimal zhaijzmj;
    //农个数、非农个数
    private Integer ncount;
    private Integer feincount;
    private BigDecimal area;
    private Integer count4;
    private BigDecimal count4Zdmj;
    private BigDecimal count4Jzmj;
    private Integer count46;
    private BigDecimal count46Zdmj;
    private BigDecimal count46Jzmj;
    private Integer count68;
    private BigDecimal count68Zdmj;
    private BigDecimal count68Jzmj;
    private Integer count81;
    private BigDecimal count81Zdmj;
    private BigDecimal count81Jzmj;
    private Integer count1;
    private BigDecimal count1Zdmj;
    private BigDecimal count1Jzmj;

    //建筑面积户均 平米
    private BigDecimal zhjzhj;

    //占地面积户均 平米
    private BigDecimal zhzdhj;

    private BigDecimal txNydmj;

    private BigDecimal txWlydmj;

    private BigDecimal txJsydmj;

    private BigDecimal txTjsmj;
    private boolean isShow=false;
    private boolean isCheck=false;
    private boolean isChecked=false;

    //geometry
    private String geometry;
    private BigDecimal zj;
    private int position=0;

    private int cuncount;//村庄数

    private String code;
    private ArrayList<SysXzqEntity> sysXzqEntityList=new ArrayList<>();
    private ArrayList<MovecostEntity> movecostEntityList=new ArrayList<>();
    private List<SysXzqEntity> children;

    public List<SysXzqEntity> getChildren() {
        if (children==null){
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<SysXzqEntity> children) {
        this.children = children;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getCuncount() {
        return cuncount;
    }

    public void setCuncount(int cuncount) {
        this.cuncount = cuncount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public BigDecimal getSumcost() {
        return zj==null?new BigDecimal(0):zj;
    }

    public void setSumcost(BigDecimal sumcost) {
        this.zj = sumcost;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public ArrayList<SysXzqEntity> getSysXzqEntityList() {
        return sysXzqEntityList==null?new ArrayList<>():sysXzqEntityList;
    }

    public void setSysXzqEntityList(ArrayList<SysXzqEntity> sysXzqEntityList) {
        this.sysXzqEntityList = sysXzqEntityList;
    }

    public ArrayList<MovecostEntity> getMovecostEntityList() {
        return movecostEntityList==null?new ArrayList<>():movecostEntityList;
    }

    public void setMovecostEntityList(ArrayList<MovecostEntity> movecostEntityList) {
        this.movecostEntityList = movecostEntityList;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getHs() {
        return hs==null?0:hs;
    }

    public void setHs(Integer hs) {
        this.hs = hs;
    }

    public Integer getRk() {
        return rk==null?0:rk;
    }

    public void setRk(Integer rk) {
        this.rk = rk;
    }

    public Integer getZs() {
        return zs==null?0:zs;
    }

    public void setZs(Integer zs) {
        this.zs = zs;
    }

    public BigDecimal getZhzdmj() {
        return zhzdmj==null?new BigDecimal(0):zhzdmj;
    }

    public void setZhzdmj(BigDecimal zhzdmj) {
        this.zhzdmj = zhzdmj;
    }

    public BigDecimal getZhjzmj() {
        return zhjzmj==null?new BigDecimal(0):zhjzmj;
    }

    public void setZhjzmj(BigDecimal zhjzmj) {
        this.zhjzmj = zhjzmj;
    }

    public BigDecimal getZhaizdmj() {
        return zhaizdmj==null?new BigDecimal(0):zhaizdmj;
    }

    public void setZhaizdmj(BigDecimal zhaizdmj) {
        this.zhaizdmj = zhaizdmj;
    }

    public BigDecimal getZhaijzmj() {
        return zhaijzmj==null?new BigDecimal(0):zhaijzmj;
    }

    public void setZhaijzmj(BigDecimal zhaijzmj) {
        this.zhaijzmj = zhaijzmj;
    }

    public Integer getNcount() {
        return ncount==null?0:ncount;
    }

    public void setNcount(Integer ncount) {
        this.ncount = ncount;
    }

    public Integer getFeincount() {
        return feincount==null?0:feincount;
    }

    public void setFeincount(Integer feincount) {
        this.feincount = feincount;
    }

    public BigDecimal getArea() {
        return area==null?new BigDecimal(0):area.setScale(2, BigDecimal.ROUND_UP);
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getCount4() {
        return count4==null?0:count4;
    }

    public void setCount4(Integer count4) {
        this.count4 = count4;
    }

    public BigDecimal getCount4Zdmj() {
        return count4Zdmj==null?new BigDecimal(0):count4Zdmj;
    }

    public void setCount4Zdmj(BigDecimal count4Zdmj) {
        this.count4Zdmj = count4Zdmj;
    }

    public BigDecimal getCount4Jzmj() {
        return count4Jzmj==null?new BigDecimal(0):count4Jzmj;
    }

    public void setCount4Jzmj(BigDecimal count4Jzmj) {
        this.count4Jzmj = count4Jzmj;
    }

    public Integer getCount46() {
        return count46==null?0:count46;
    }

    public void setCount46(Integer count46) {
        this.count46 = count46;
    }

    public BigDecimal getCount46Zdmj() {
        return count46Zdmj==null?new BigDecimal(0):count46Zdmj;
    }

    public void setCount46Zdmj(BigDecimal count46Zdmj) {
        this.count46Zdmj = count46Zdmj;
    }

    public BigDecimal getCount46Jzmj() {
        return count46Jzmj==null?new BigDecimal(0):count46Jzmj;
    }

    public void setCount46Jzmj(BigDecimal count46Jzmj) {
        this.count46Jzmj = count46Jzmj;
    }

    public Integer getCount68() {
        return count68==null?0:count68;
    }

    public void setCount68(Integer count68) {
        this.count68 = count68;
    }

    public BigDecimal getCount68Zdmj() {
        return count68Zdmj==null?new BigDecimal(0):count68Zdmj;
    }

    public void setCount68Zdmj(BigDecimal count68Zdmj) {
        this.count68Zdmj = count68Zdmj;
    }

    public BigDecimal getCount68Jzmj() {
        return count68Jzmj==null?new BigDecimal(0):count68Jzmj;
    }

    public void setCount68Jzmj(BigDecimal count68Jzmj) {
        this.count68Jzmj = count68Jzmj;
    }

    public Integer getCount81() {
        return count81==null?0:count81;
    }

    public void setCount81(Integer count81) {
        this.count81 = count81;
    }

    public BigDecimal getCount81Zdmj() {
        return count81Zdmj==null?new BigDecimal(0):count81Zdmj;
    }

    public void setCount81Zdmj(BigDecimal count81Zdmj) {
        this.count81Zdmj = count81Zdmj;
    }

    public BigDecimal getCount81Jzmj() {
        return count81Jzmj==null?new BigDecimal(0):count81Jzmj;
    }

    public void setCount81Jzmj(BigDecimal count81Jzmj) {
        this.count81Jzmj = count81Jzmj;
    }

    public Integer getCount1() {
        return count1==null?0:count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public BigDecimal getCount1Zdmj() {
        return count1Zdmj==null?new BigDecimal(0):count1Zdmj;
    }

    public void setCount1Zdmj(BigDecimal count1Zdmj) {
        this.count1Zdmj = count1Zdmj;
    }

    public BigDecimal getCount1Jzmj() {
        return count1Jzmj==null?new BigDecimal(0):count1Jzmj;
    }

    public void setCount1Jzmj(BigDecimal count1Jzmj) {
        this.count1Jzmj = count1Jzmj;
    }

    public BigDecimal getZhjzhj() {
        return zhjzhj==null?new BigDecimal(0):zhjzhj;
    }

    public void setZhjzhj(BigDecimal zhjzhj) {
        this.zhjzhj = zhjzhj;
    }

    public BigDecimal getZhzdhj() {
        return zhzdhj==null?new BigDecimal(0):zhzdhj;
    }

    public void setZhzdhj(BigDecimal zhzdhj) {
        this.zhzdhj = zhzdhj;
    }

    public BigDecimal getTxNydmj() {
        return txNydmj==null?new BigDecimal(0):txNydmj;
    }

    public void setTxNydmj(BigDecimal txNydmj) {
        this.txNydmj = txNydmj;
    }

    public BigDecimal getTxWlydmj() {
        return txWlydmj==null?new BigDecimal(0):txWlydmj;
    }

    public void setTxWlydmj(BigDecimal txWlydmj) {
        this.txWlydmj = txWlydmj;
    }

    public BigDecimal getTxJsydmj() {
        return txJsydmj==null?new BigDecimal(0):txJsydmj;
    }

    public void setTxJsydmj(BigDecimal txJsydmj) {
        this.txJsydmj = txJsydmj;
    }

    public BigDecimal getTxTjsmj() {
        return txTjsmj==null?new BigDecimal(0):txTjsmj;
    }

    public void setTxTjsmj(BigDecimal txTjsmj) {
        this.txTjsmj = txTjsmj;
    }

    public String getGeometry() {
        return geometry==null?"":geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Object orderNum) {
        this.orderNum = orderNum;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName==null?"":parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Object getPerms() {
        return perms;
    }

    public void setPerms(Object perms) {
        this.perms = perms;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName==null?"":typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getXzqId() {
        return xzqId;
    }

    public void setXzqId(int xzqId) {
        this.xzqId = xzqId;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getSortId() {
        return sortId==null?"":sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName==null?"":sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
}
