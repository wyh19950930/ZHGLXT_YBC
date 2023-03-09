package com.jymj.zhglxt.zjd.bean.yzt.cs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by ljj on 2020/5/13.
 */
public class XzDateEntity implements Serializable {
    //基础数据：
    private Integer rk;//人口
    private Integer cuncount;
    private Integer zs;//宗数
    private BigDecimal czfw;//改造范围
    private BigDecimal zhzdmj;//宅占地面积
    private BigDecimal zhhjzd;//宅宗均占地面积
    private BigDecimal zhjzmj;//宅建筑面积
    private Integer count4;
    private BigDecimal count4zd;
    private BigDecimal count4jz;
    private Integer count46;
    private BigDecimal count46zd;
    private BigDecimal count46jz;
    private Integer count68;
    private BigDecimal count68zd;
    private BigDecimal count68jz;
    private Integer count81;
    private BigDecimal count81zd;
    private BigDecimal count81jz;
    private Integer count112;
    private BigDecimal count112zd;
    private BigDecimal count112jz;
    private Integer count12;
    private BigDecimal count12zd;
    private BigDecimal count12jz;
    private BigDecimal wjfmj;//未建房面积

    //回购：
    private BigDecimal zch;//村名自筹
    private BigDecimal zzl;//自住楼 面积 单位 万㎡
    private BigDecimal zzldj; //单价
    private BigDecimal zzlje; //金额
    //第三第四测算回购
    private BigDecimal dlzz34200;//200㎡ 面积 单位 万㎡
    private BigDecimal dlzz3475n;//75%以内
    private BigDecimal dlzz3475ndj; //单价
    private BigDecimal dlzz3475nje;//75%以内 金额  万元
    private BigDecimal dlzz3475w;//75%以外
    private BigDecimal dlzz3475wdj;//单价
    private BigDecimal dlzz3475wje;//75%以外 金额
    private BigDecimal dlzz34xj;//小计
    private BigDecimal dlzz34xjje;//小计 金额
    //第五测算不分户 <4分
    private BigDecimal dlzz5200;//200㎡ 面积 单位 万㎡
    private BigDecimal dlzz575n;//75%以内
    private BigDecimal dlzz575ndj;
    private BigDecimal dlzz575nje;//75%以内 金额  万元
    private BigDecimal dlzz575w;//75%以外
    private BigDecimal dlzz575wdj;
    private BigDecimal dlzz575wje;//75%以外 金额
    private BigDecimal dlzz5xj;//小计
    private BigDecimal dlzz5xjje;//小计 金额
    //第五测算 分户 >4分
    private BigDecimal dlzz200;//200㎡
    private BigDecimal dlzz75n;//75%以内
    private BigDecimal dlzz75ndj;
    private BigDecimal dlzz75nje;//75%以内 金额  万元
    private BigDecimal dlzz75w;//75%以外
    private BigDecimal dlzz75wdj;//75%以外 单价
    private BigDecimal dlzz75wje;//75%以外 金额  万元
    private BigDecimal dlzzxj;//小计
    private BigDecimal dlzzxjje;//小计 金额  万元

    private BigDecimal dlzzhj;//独立住宅--合计
    private BigDecimal dlzzhjje;//独立住宅--合计 金额

    //结果
    private String ff1="每户退1/3";
    private BigDecimal ff1ghmj;//用地公顷
    private BigDecimal ff1hjmj;//户均
    private String ff2="宅院75%权益面积";
    private BigDecimal ff2ghmj;
    private BigDecimal ff2hjmj;
    private String ff3="户均200㎡";
    private BigDecimal ff3ghmj;
    private BigDecimal ff3hjmj;
    private String ff4="人均50㎡";
    private BigDecimal ff4ghmj;
    private BigDecimal ff4hjmj;
    private String ff5="分户";
    private BigDecimal ff5ghmj;
    private BigDecimal ff5hjmj;
    private String ff51="村庄用地40%";
    private BigDecimal ff51ghmj;
    private BigDecimal ff51hjmj;

    private BigDecimal dlss;
    private BigDecimal phzj12;
    private BigDecimal phzj34;
    private BigDecimal phzj5;
    private BigDecimal sy1;
    private BigDecimal sy2;
    private BigDecimal sy3;
    private BigDecimal sy4;
    private BigDecimal sy5;

    public BigDecimal getDlss() {
        return dlss;
    }

    public void setDlss(BigDecimal dlss) {
        this.dlss = dlss;
    }

    public BigDecimal getPhzj12() {
        return phzj12;
    }

    public void setPhzj12(BigDecimal phzj12) {
        this.phzj12 = phzj12;
    }

    public BigDecimal getPhzj34() {
        return phzj34;
    }

    public void setPhzj34(BigDecimal phzj34) {
        this.phzj34 = phzj34;
    }

    public BigDecimal getPhzj5() {
        return phzj5;
    }

    public void setPhzj5(BigDecimal phzj5) {
        this.phzj5 = phzj5;
    }

    public BigDecimal getSy1() {
        return sy1;
    }

    public void setSy1(BigDecimal sy1) {
        this.sy1 = sy1;
    }

    public BigDecimal getSy2() {
        return sy2;
    }

    public void setSy2(BigDecimal sy2) {
        this.sy2 = sy2;
    }

    public BigDecimal getSy3() {
        return sy3;
    }

    public void setSy3(BigDecimal sy3) {
        this.sy3 = sy3;
    }

    public BigDecimal getSy4() {
        return sy4;
    }

    public void setSy4(BigDecimal sy4) {
        this.sy4 = sy4;
    }

    public BigDecimal getSy5() {
        return sy5;
    }

    public void setSy5(BigDecimal sy5) {
        this.sy5 = sy5;
    }

    public Integer getRk() {
        return rk==null?1:rk;
    }

    public void setRk(Integer rk) {
        this.rk = rk;
    }

    public Integer getCuncount() {
        return cuncount==null?1:cuncount;
    }

    public void setCuncount(Integer cuncount) {
        this.cuncount = cuncount;
    }

    public Integer getZs() {
        return zs==null?0:zs;
    }

    public void setZs(Integer zs) {
        this.zs = zs;
    }

    public BigDecimal getCzfw() {
        return czfw==null?new BigDecimal(0):czfw.setScale(2,RoundingMode.HALF_UP);
    }

    public void setCzfw(BigDecimal czfw) {
        this.czfw = czfw;
    }

    public BigDecimal getZhzdmj() {
        return zhzdmj==null?new BigDecimal(0):zhzdmj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setZhzdmj(BigDecimal zhzdmj) {
        this.zhzdmj = zhzdmj;
    }

    public BigDecimal getZhhjzd() {
        if(getZs()!=0){
            return getZhzdmj().divide(new BigDecimal(getZs()),2, RoundingMode.HALF_UP);
        }
        return zhhjzd==null?new BigDecimal(0):zhhjzd.setScale(2,RoundingMode.HALF_UP);
    }

    public void setZhhjzd(BigDecimal zhhjzd) {
        this.zhhjzd = zhhjzd;
    }

    public BigDecimal getZhjzmj() {
        return zhjzmj==null?new BigDecimal(0):zhjzmj.setScale(2,RoundingMode.HALF_UP);//.multiply(new BigDecimal(3))
    }

    public void setZhjzmj(BigDecimal zhjzmj) {
        this.zhjzmj = zhjzmj;
    }

    public Integer getCount4() {
        return count4==null?0:count4;
    }

    public void setCount4(Integer count4) {
        this.count4 = count4;
    }

    public BigDecimal getCount4zd() {
        return count4zd==null?new BigDecimal(0):count4zd.setScale(2,RoundingMode.HALF_UP);
    }

    public void setCount4zd(BigDecimal count4zd) {
        this.count4zd = count4zd;
    }

    public BigDecimal getCount4jz() {
        return count4jz==null?new BigDecimal(0):count4jz.setScale(2,RoundingMode.HALF_UP);//.multiply(new BigDecimal(3))
    }

    public void setCount4jz(BigDecimal count4jz) {
        this.count4jz = count4jz;
    }

    public Integer getCount46() {
        return count46==null?0:count46;
    }

    public void setCount46(Integer count46) {
        this.count46 = count46;
    }

    public BigDecimal getCount46zd() {
        return count46zd==null?new BigDecimal(0):count46zd.setScale(2,RoundingMode.HALF_UP);
    }

    public void setCount46zd(BigDecimal count46zd) {
        this.count46zd = count46zd;
    }

    public BigDecimal getCount46jz() {
        return count46jz==null?new BigDecimal(0):count46jz.setScale(2,RoundingMode.HALF_UP);//.multiply(new BigDecimal(3))
    }

    public void setCount46jz(BigDecimal count46jz) {
        this.count46jz = count46jz;
    }

    public Integer getCount68() {
        return count68==null?0:count68;
    }

    public void setCount68(Integer count68) {
        this.count68 = count68;
    }

    public BigDecimal getCount68zd() {
        return count68zd==null?new BigDecimal(0):count68zd.setScale(2,RoundingMode.HALF_UP);
    }

    public void setCount68zd(BigDecimal count68zd) {
        this.count68zd = count68zd;
    }

    public BigDecimal getCount68jz() {
        return count68jz==null?new BigDecimal(0):count68jz.setScale(2,RoundingMode.HALF_UP);//.multiply(new BigDecimal(3))
    }

    public void setCount68jz(BigDecimal count68jz) {
        this.count68jz = count68jz;
    }

    public Integer getCount81() {
        return count81==null?0:count81;
    }

    public void setCount81(Integer count81) {
        this.count81 = count81;
    }

    public BigDecimal getCount81zd() {
        return count81zd==null?new BigDecimal(0):count81zd.setScale(2,RoundingMode.HALF_UP);
    }

    public void setCount81zd(BigDecimal count81zd) {
        this.count81zd = count81zd;
    }

    public BigDecimal getCount81jz() {
        return count81jz==null?new BigDecimal(0):count81jz.setScale(2,RoundingMode.HALF_UP);//.multiply(new BigDecimal(3))
    }

    public void setCount81jz(BigDecimal count81jz) {
        this.count81jz = count81jz;
    }

    public Integer getCount112() {
        return count112==null?0:count112;
    }

    public void setCount112(Integer count112) {
        this.count112 = count112;
    }

    public BigDecimal getCount112zd() {
        return count112zd==null?new BigDecimal(0):count112zd.setScale(2,RoundingMode.HALF_UP);
    }

    public void setCount112zd(BigDecimal count112zd) {
        this.count112zd = count112zd;
    }

    public BigDecimal getCount112jz() {
        return count112jz==null?new BigDecimal(0):count112jz.setScale(2,RoundingMode.HALF_UP);//.multiply(new BigDecimal(3))
    }

    public void setCount112jz(BigDecimal count112jz) {
        this.count112jz = count112jz;
    }

    public Integer getCount12() {
        return count12==null?0:count12;
    }

    public void setCount12(Integer count12) {
        this.count12 = count12;
    }

    public BigDecimal getCount12zd() {
        return count12zd==null?new BigDecimal(0):count12zd.setScale(2,RoundingMode.HALF_UP);
    }

    public void setCount12zd(BigDecimal count12zd) {
        this.count12zd = count12zd;
    }

    public BigDecimal getCount12jz() {
        return count12jz==null?new BigDecimal(0):count12jz.setScale(2,RoundingMode.HALF_UP);//.multiply(new BigDecimal(3))
    }

    public void setCount12jz(BigDecimal count12jz) {
        this.count12jz = count12jz;
    }

    public BigDecimal getWjfmj() {
        return wjfmj==null?new BigDecimal(0):wjfmj.setScale(2, RoundingMode.HALF_UP);
    }

    public void setWjfmj(BigDecimal wjfmj) {
        this.wjfmj = wjfmj;
    }

    public BigDecimal getZch() {
        return zch==null?new BigDecimal(0):zch.setScale(2,RoundingMode.HALF_UP);
    }

    public void setZch(BigDecimal zch) {
        this.zch = zch;
    }

    public BigDecimal getZzl() {
        return zzl==null?new BigDecimal(0):zzl.setScale(2,RoundingMode.HALF_UP);
    }

    public void setZzl(BigDecimal zzl) {
        this.zzl = zzl;
    }

    public BigDecimal getDlzz34200() {
        //200*宗数/10000
        return new BigDecimal(200*getZs()).divide(new BigDecimal(10000),2, RoundingMode.HALF_UP);
    }

    public void setDlzz34200(BigDecimal dlzz34200) {
        this.dlzz34200 = dlzz34200;
    }

    public BigDecimal getDlzz3475n() {
        return dlzz3475n==null?new BigDecimal(0):dlzz3475n.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz3475n(BigDecimal dlzz3475n) {
        this.dlzz3475n = dlzz3475n;
    }

    public BigDecimal getDlzz3475w() {
        //(300-200)*4宗数/1000+300*46宗数/10000-46占地面积75%
        return dlzz3475w==null?new BigDecimal(0):dlzz3475w.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz3475w(BigDecimal dlzz3475w) {
        this.dlzz3475w = dlzz3475w;
    }

    public BigDecimal getDlzz34xj() {
        return dlzz34xj==null?new BigDecimal(0):dlzz34xj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz34xj(BigDecimal dlzz34xj) {
        this.dlzz34xj = dlzz34xj;
    }

    public BigDecimal getDlzz5200() {
        //4宗数*200/10000
        return new BigDecimal(getCount4()*200).divide(new BigDecimal(10000),2, RoundingMode.HALF_UP);
    }

    public void setDlzz5200(BigDecimal dlzz5200) {
        this.dlzz5200 = dlzz5200;
    }

    public BigDecimal getDlzz575n() {
        return dlzz575n==null?new BigDecimal(0):dlzz575n.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz575n(BigDecimal dlzz575n) {
        this.dlzz575n = dlzz575n;
    }

    public BigDecimal getDlzz575w() {
        return dlzz575w==null?new BigDecimal(0):dlzz575w.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz575w(BigDecimal dlzz575w) {
        this.dlzz575w = dlzz575w;
    }

    public BigDecimal getDlzz5xj() {
        return getDlzz5200().add(getDlzz575n()).add(getDlzz575w()).setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz5xj(BigDecimal dlzz5xj) {
        this.dlzz5xj = dlzz5xj;
    }

    public BigDecimal getDlzz200() {
        //48占地面积75% + 8以上宗数*2*200/10000
        BigDecimal a48 = getCount46zd().add(getCount68zd()).multiply(new BigDecimal(0.75));
        BigDecimal a8 = new BigDecimal((getCount81()+getCount112()+getCount12())*2*200)
                .divide(new BigDecimal(10000),2, RoundingMode.HALF_UP);
        //a48.add(a8)
        return dlzz200==null?new BigDecimal(0):dlzz200.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz200(BigDecimal dlzz200) {
        this.dlzz200 = dlzz200;
    }

    public BigDecimal getDlzz75n() {
        return dlzz75n==null?new BigDecimal(0):dlzz75n.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz75n(BigDecimal dlzz75n) {
        this.dlzz75n = dlzz75n;
    }

    public BigDecimal getDlzz75w() {
        return dlzz75w==null?new BigDecimal(0):dlzz75w.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz75w(BigDecimal dlzz75w) {
        this.dlzz75w = dlzz75w;
    }

    public BigDecimal getDlzzxj() {
        return getDlzz200().add(getDlzz75n()).add(getDlzz75w()).setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzzxj(BigDecimal dlzzxj) {
        this.dlzzxj = dlzzxj;
    }

    public BigDecimal getDlzzhj() {
        return getDlzz5xj().add(getDlzzxj()).setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzzhj(BigDecimal dlzzhj) {
        this.dlzzhj = dlzzhj;
    }

    public String getFf1() {
        return ff1;
    }

    public void setFf1(String ff1) {
        this.ff1 = ff1;
    }

    public BigDecimal getFf1ghmj() {
        //宅占地面积*2/3
        return ff1ghmj==null?new BigDecimal(0):ff1ghmj.setScale(2,RoundingMode.HALF_UP);//getZhzdmj().multiply(new BigDecimal(0.6667)).setScale(2,RoundingMode.HALF_UP)
    }

    public void setFf1ghmj(BigDecimal ff1ghmj) {
        this.ff1ghmj = ff1ghmj;
    }

    public BigDecimal getFf1hjmj() {
        /*if(getZs()!=0){
            return getFf1ghmj().multiply(new BigDecimal(10000)).divide(new BigDecimal(getZs()),2,RoundingMode.HALF_UP);
        }*/
        return ff1hjmj==null?new BigDecimal(0):ff1hjmj.setScale(2,RoundingMode.HALF_UP);//new BigDecimal(0)
    }

    public void setFf1hjmj(BigDecimal ff1hjmj) {
        this.ff1hjmj = ff1hjmj;
    }

    public String getFf2() {
        return ff2;
    }

    public void setFf2(String ff2) {
        this.ff2 = ff2;
    }

    public BigDecimal getFf2ghmj() {
        //4分以下占地面积75%+4分以上宗数*0.02
       /* getCount4zd().multiply(new BigDecimal(0.75)).
                add((new BigDecimal(getCount12()+getCount46()+getCount68()+getCount81()+getCount112())
                        .multiply(new BigDecimal(0.02)))).setScale(2,RoundingMode.HALF_UP)*/
        return ff2ghmj==null?new BigDecimal(0):ff2ghmj.setScale(2,RoundingMode.HALF_UP);//
    }

    public void setFf2ghmj(BigDecimal ff2ghmj) {
        this.ff2ghmj = ff2ghmj;
    }

    public BigDecimal getFf2hjmj() {
        /*if(getZs()!=0){
            return getFf2ghmj().multiply(new BigDecimal(10000)).divide(new BigDecimal(getZs()),2,RoundingMode.HALF_UP);
        }*/
        return ff2hjmj==null?new BigDecimal(0):ff2hjmj.setScale(2,RoundingMode.HALF_UP);//new BigDecimal(0)
    }

    public void setFf2hjmj(BigDecimal ff2hjmj) {
        this.ff2hjmj = ff2hjmj;
    }

    public String getFf3() {
        return ff3;
    }

    public void setFf3(String ff3) {
        this.ff3 = ff3;
    }

    public BigDecimal getFf3ghmj() {
        //宗数*200/10000
        return new BigDecimal(getZs()*200).divide(new BigDecimal(10000),2, RoundingMode.HALF_UP);
    }

    public void setFf3ghmj(BigDecimal ff3ghmj) {
        this.ff3ghmj = ff3ghmj;
    }

    public BigDecimal getFf3hjmj() {
        /*if(getZs()!=0){
            return getFf3ghmj().multiply(new BigDecimal(10000)).divide(new BigDecimal(getZs()),2,RoundingMode.HALF_UP);
        }*/
        return ff3hjmj==null?new BigDecimal(0):ff3hjmj.setScale(2,RoundingMode.HALF_UP);// new BigDecimal(0)
    }

    public void setFf3hjmj(BigDecimal ff3hjmj) {
        this.ff3hjmj = ff3hjmj;
    }

    public String getFf5() {
        return ff5;
    }

    public void setFf5(String ff5) {
        this.ff5 = ff5;
    }

    public BigDecimal getFf5ghmj() {
        //8分以下占地面积75%+8分以上宗数*0.04
        /*(getCount4zd().add(getCount46zd()).add(getCount68zd())).multiply(new BigDecimal(0.75)).
                add(new BigDecimal(getCount81()+getCount112()+getCount12()).multiply(new BigDecimal(0.04))).setScale(2,RoundingMode.HALF_UP)*/
        return ff5ghmj==null?new BigDecimal(0):ff5ghmj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setFf5ghmj(BigDecimal ff5ghmj) {
        this.ff5ghmj = ff5ghmj;
    }

    public BigDecimal getFf5hjmj() {
        /*if(getZs()!=0){
            return getFf5ghmj().multiply(new BigDecimal(10000)).divide(new BigDecimal(getZs()),2,RoundingMode.HALF_UP);
        }*/
        return ff5hjmj==null?new BigDecimal(0):ff5hjmj.setScale(2,RoundingMode.HALF_UP);//new BigDecimal(0)
    }

    public void setFf5hjmj(BigDecimal ff5hjmj) {
        this.ff5hjmj = ff5hjmj;
    }

    public String getFf51() {
        return ff51;
    }

    public void setFf51(String ff51) {
        this.ff51 = ff51;
    }

    public BigDecimal getFf51ghmj() {
        //改造范围40%
        return ff51ghmj==null?new BigDecimal(0):ff51ghmj.setScale(2,RoundingMode.HALF_UP);//getCzfw().multiply(new BigDecimal(0.4)).setScale(2,RoundingMode.HALF_UP)
    }

    public void setFf51ghmj(BigDecimal ff51ghmj) {
        this.ff51ghmj = ff51ghmj;
    }

    public BigDecimal getFf51hjmj() {
        /*if(getZs()!=0){
            return getFf51ghmj().multiply(new BigDecimal(10000)).divide(new BigDecimal(getZs()),2,RoundingMode.HALF_UP);
        }*/
        return ff51hjmj==null?new BigDecimal(0):ff51hjmj.setScale(2,RoundingMode.HALF_UP);//new BigDecimal(0)
    }

    public void setFf51hjmj(BigDecimal ff51hjmj) {
        this.ff51hjmj = ff51hjmj;
    }

    public String getFf4() {
        return ff4;
    }

    public void setFf4(String ff4) {
        this.ff4 = ff4;
    }

    public BigDecimal getFf4ghmj() {
        return new BigDecimal(getRk()*50).divide(new BigDecimal(10000),2, RoundingMode.HALF_UP);
    }

    public void setFf4ghmj(BigDecimal ff4ghmj) {
        this.ff4ghmj = ff4ghmj;
    }

    public BigDecimal getFf4hjmj() {
        /*if (getZs()!=0) {
            return getFf4ghmj().multiply(new BigDecimal(10000)).divide(new BigDecimal(getZs()),2,RoundingMode.HALF_UP);
        }*/
        return ff4hjmj==null?new BigDecimal(0):ff4hjmj.setScale(2,RoundingMode.HALF_UP);//new BigDecimal(0)
    }

    public void setFf4hjmj(BigDecimal ff4hjmj) {
        this.ff4hjmj = ff4hjmj;
    }

    public BigDecimal getZzldj() {
        return zzldj==null?new BigDecimal(5500):zzldj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setZzldj(BigDecimal zzldj) {
        this.zzldj = zzldj;
    }

    public BigDecimal getZzlje() {
        return zzlje==null?new BigDecimal(0):zzlje.setScale(2,RoundingMode.HALF_UP);//getZzl().multiply(getZzldj()).setScale(2,RoundingMode.HALF_UP)
    }

    public void setZzlje(BigDecimal zzlje) {
        this.zzlje = zzlje;
    }

    public BigDecimal getDlzz3475ndj() {
        return dlzz3475ndj==null?new BigDecimal(6500):dlzz3475ndj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz3475ndj(BigDecimal dlzz3475ndj) {
        this.dlzz3475ndj = dlzz3475ndj;
    }

    public BigDecimal getDlzz3475nje() {
        return dlzz3475nje==null?new BigDecimal(0):dlzz3475nje.setScale(2,RoundingMode.HALF_UP);//getDlzz3475n().multiply(getDlzz3475ndj()).setScale(2,RoundingMode.HALF_UP)
    }

    public void setDlzz3475nje(BigDecimal dlzz3475nje) {
        this.dlzz3475nje = dlzz3475nje;
    }

    public BigDecimal getDlzz3475wdj() {
        return dlzz3475wdj==null?new BigDecimal(8500):dlzz3475wdj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz3475wdj(BigDecimal dlzz3475wdj) {
        this.dlzz3475wdj = dlzz3475wdj;
    }

    public BigDecimal getDlzz3475wje() {
        return dlzz3475wje==null?new BigDecimal(0):dlzz3475wje.setScale(2,RoundingMode.HALF_UP);//getDlzz3475w().multiply(getDlzz3475wdj()).setScale(2,RoundingMode.HALF_UP
    }

    public void setDlzz3475wje(BigDecimal dlzz3475wje) {
        this.dlzz3475wje = dlzz3475wje;
    }

    public BigDecimal getDlzz34xjje() {
        return getDlzz3475nje().add(getDlzz3475wje()).setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz34xjje(BigDecimal dlzz34xjje) {
        this.dlzz34xjje = dlzz34xjje;
    }

    public BigDecimal getDlzz575ndj() {
        return dlzz575ndj==null?new BigDecimal(6500):dlzz575ndj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz575ndj(BigDecimal dlzz575ndj) {
        this.dlzz575ndj = dlzz575ndj;
    }

    public BigDecimal getDlzz575nje() {
        return dlzz575nje==null?new BigDecimal(0):dlzz575nje.setScale(2,RoundingMode.HALF_UP);//getDlzz575n().multiply(getDlzz575ndj()).setScale(2,RoundingMode.HALF_UP)
    }

    public void setDlzz575nje(BigDecimal dlzz575nje) {
        this.dlzz575nje = dlzz575nje;
    }

    public BigDecimal getDlzz575wdj() {
        return dlzz575wdj==null?new BigDecimal(8500):dlzz575wdj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz575wdj(BigDecimal dlzz575wdj) {
        this.dlzz575wdj = dlzz575wdj;
    }

    public BigDecimal getDlzz575wje() {
        return dlzz575wje==null?new BigDecimal(0):dlzz575wje.setScale(2,RoundingMode.HALF_UP);//getDlzz575w().multiply(getDlzz575wdj()).setScale(2,RoundingMode.HALF_UP)
    }

    public void setDlzz575wje(BigDecimal dlzz575wje) {
        this.dlzz575wje = dlzz575wje;
    }

    public BigDecimal getDlzz5xjje() {
        return getDlzz575nje().add(getDlzz575wje()).setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz5xjje(BigDecimal dlzz5xjje) {
        this.dlzz5xjje = dlzz5xjje;
    }

    public BigDecimal getDlzz75ndj() {
        return dlzz75ndj==null?new BigDecimal(6500):dlzz75ndj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz75ndj(BigDecimal dlzz75ndj) {
        this.dlzz75ndj = dlzz75ndj;
    }

    public BigDecimal getDlzz75nje() {
        return dlzz75nje==null?new BigDecimal(0):dlzz75nje.setScale(2,RoundingMode.HALF_UP);//getDlzz75n().multiply(getDlzz75ndj()).setScale(2,RoundingMode.HALF_UP)
    }

    public void setDlzz75nje(BigDecimal dlzz75nje) {
        this.dlzz75nje = dlzz75nje;
    }

    public BigDecimal getDlzz75wdj() {
        return dlzz75wdj==null?new BigDecimal(8500):dlzz75wdj.setScale(2,RoundingMode.HALF_UP);
    }

    public void setDlzz75wdj(BigDecimal dlzz75wdj) {
        this.dlzz75wdj = dlzz75wdj;
    }

    public BigDecimal getDlzz75wje() {
        return dlzz75wje==null?new BigDecimal(0):dlzz75wje.setScale(2,RoundingMode.HALF_UP);//getDlzz75w().multiply(getDlzz75wdj()).setScale(2,RoundingMode.HALF_UP)
    }

    public void setDlzz75wje(BigDecimal dlzz75wje) {
        this.dlzz75wje = dlzz75wje;
    }

    public BigDecimal getDlzzxjje() {
        return getDlzz75nje().add(getDlzz75wje());
    }

    public void setDlzzxjje(BigDecimal dlzzxjje) {
        this.dlzzxjje = dlzzxjje;
    }

    public BigDecimal getDlzzhjje() {
        return getDlzz5xjje().add(getDlzzxjje());
    }

    public void setDlzzhjje(BigDecimal dlzzhjje) {
        this.dlzzhjje = dlzzhjje;
    }
}
