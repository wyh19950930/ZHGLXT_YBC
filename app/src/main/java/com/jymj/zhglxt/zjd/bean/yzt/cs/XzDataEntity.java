package com.jymj.zhglxt.zjd.bean.yzt.cs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by ljj on 2018/3/25.
 */
public class XzDataEntity implements Serializable {
    //人口数
    private Integer ztrk;
    //户数
    private Integer hs;
    //农
    private Integer ncount;
    //非农
    private Integer feincount;
    //经济组织成员
    private Integer jjzz;

    //宅基地面积（公顷）
    private BigDecimal zhaimj;
    //住宅建筑面积（万平米）
    private BigDecimal zhaijzmj;
    //宅基地个数（宗）
    private Integer zhaicount;
    //现状建筑物其他(宗数)
    private Integer feicount;
    //现状建筑物其他(占地面积)
    private BigDecimal feimj;
    //现状建筑物其他(建筑面积)
    private BigDecimal feijzmj;

    //集体物业个数（宗）
    private Integer jtwycount;
    //集体物业 占地面积（公顷）
    private BigDecimal jtwyzdmj;
    //集体物业  建筑面积（万平米）
    private BigDecimal jtwyjzmj;

    //现状建筑物合计(宗数)
    private Integer hjcount;
    //现状建筑物合计(占地面积)
    private BigDecimal hjzdmj;
    //现状建筑物合计(建筑面积)
    private BigDecimal hjjzmj;

    //土现
    //建设用地
    private BigDecimal jsydmj;
    //农用地
    private BigDecimal nydmj;
    //其中耕地
    private BigDecimal gdmj;
    //未利用地
    private BigDecimal wlydmj;
    //土现合计
    private BigDecimal txhj;
    //棚改基数
    private String pgjs = "人均50㎡";
    //棚改规划
    private BigDecimal pggh;
    //自主上楼基数
    private String zzsljs = "宅基地建筑面积1:1.1";
    //自主上楼规划
    private BigDecimal zzslgh;
    //美丽乡村基数
    private String mlxcjs = "人均50㎡";
    //美丽乡村规划
    private BigDecimal mlxcgh;
    //平衡资金单价
    private BigDecimal phBill;
    //地价区位目标值
    private String mbdjqw;
    //现值房价目标值
    private BigDecimal mbxzfj;
    //基准地价目标值
    private BigDecimal mbjzdj;
    //可实现地价目标值--平衡资金
    private BigDecimal mbksxdj;
    //地价区位比准值
    private String bzdjqw;
    //现值房价比准值
    private BigDecimal bzxzfj;
    //基准地价比准值
    private BigDecimal bzjzdj;
    //可实现地价比准值
    private BigDecimal bzksxdj;
//国有建设用地面积
    private BigDecimal gyjsydmj;
    //国有农业用地面积
    private BigDecimal gynydmj;
    //国有耕地面积
    private BigDecimal gygdmj;
    //国有未利用地面积
    private BigDecimal gywlydmj;
    //国有土现合计
    private BigDecimal gytxhj;
    //整体改造基数
    private String ztgzjs = "村庄用地的40%";
    //整体改造建筑面积
    private BigDecimal ztgzgh;
    //整体改造用地面积
    private BigDecimal ztgzzdgh;

    //征地模式用地面积
    private BigDecimal pgzdgh;
    //宅基地换房用地面积
    private BigDecimal zzslzdgh;
    //宅院置换用地面积
    private BigDecimal mlxczdgh;

    public BigDecimal getPgzdgh() {
        return pgzdgh==null?new BigDecimal(0):pgzdgh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setPgzdgh(BigDecimal pgzdgh) {
        this.pgzdgh = pgzdgh;
    }

    public BigDecimal getZzslzdgh() {
        return zzslzdgh==null?new BigDecimal(0):zzslzdgh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setZzslzdgh(BigDecimal zzslzdgh) {
        this.zzslzdgh = zzslzdgh;
    }

    public BigDecimal getMlxczdgh() {
        return mlxczdgh==null?new BigDecimal(0):mlxczdgh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setMlxczdgh(BigDecimal mlxczdgh) {
        this.mlxczdgh = mlxczdgh;
    }

    public String getZtgzjs() {
        return ztgzjs;
    }

    public void setZtgzjs(String ztgzjs) {
        this.ztgzjs = ztgzjs;
    }

    public BigDecimal getZtgzgh() {
        return ztgzgh==null?new BigDecimal(0):ztgzgh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setZtgzgh(BigDecimal ztgzgh) {
        this.ztgzgh = ztgzgh;
    }

    public BigDecimal getZtgzzdgh() {
        return ztgzzdgh==null?new BigDecimal(0):ztgzzdgh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setZtgzzdgh(BigDecimal ztgzzdgh) {
        this.ztgzzdgh = ztgzzdgh;
    }

    public BigDecimal getPhBill() {
        return phBill==null?new BigDecimal(0):phBill.setScale(1, RoundingMode.HALF_UP);
    }

    public void setPhBill(BigDecimal phBill) {
        this.phBill = phBill;
    }

    public String getMbdjqw() {
        return mbdjqw;
    }

    public void setMbdjqw(String mbdjqw) {
        this.mbdjqw = mbdjqw;
    }

    public BigDecimal getMbxzfj() {
        return mbxzfj==null?new BigDecimal(0):mbxzfj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setMbxzfj(BigDecimal mbxzfj) {
        this.mbxzfj = mbxzfj;
    }

    public BigDecimal getMbjzdj() {
        return mbjzdj==null?new BigDecimal(0):mbjzdj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setMbjzdj(BigDecimal mbjzdj) {
        this.mbjzdj = mbjzdj;
    }

    public BigDecimal getMbksxdj() {
        return mbksxdj==null?new BigDecimal(0):mbksxdj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setMbksxdj(BigDecimal mbksxdj) {
        this.mbksxdj = mbksxdj;
    }

    public String getBzdjqw() {
        return bzdjqw;
    }

    public void setBzdjqw(String bzdjqw) {
        this.bzdjqw = bzdjqw;
    }

    public BigDecimal getBzxzfj() {
        return bzxzfj==null?new BigDecimal(0):bzxzfj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setBzxzfj(BigDecimal bzxzfj) {
        this.bzxzfj = bzxzfj;
    }

    public BigDecimal getBzjzdj() {
        return bzjzdj==null?new BigDecimal(0):bzjzdj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setBzjzdj(BigDecimal bzjzdj) {
        this.bzjzdj = bzjzdj;
    }

    public BigDecimal getBzksxdj() {
        return bzksxdj==null?new BigDecimal(0):bzksxdj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setBzksxdj(BigDecimal bzksxdj) {
        this.bzksxdj = bzksxdj;
    }

    public Integer getJtwycount() {
        return jtwycount==null?0:jtwycount;
    }

    public void setJtwycount(Integer jtwycount) {
        this.jtwycount = jtwycount;
    }

    public BigDecimal getJtwyzdmj() {
        return jtwyzdmj==null?new BigDecimal(0):jtwyzdmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setJtwyzdmj(BigDecimal jtwyzdmj) {
        this.jtwyzdmj = jtwyzdmj;
    }

    public BigDecimal getJtwyjzmj() {
        return jtwyjzmj==null?new BigDecimal(0):jtwyjzmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setJtwyjzmj(BigDecimal jtwyjzmj) {
        this.jtwyjzmj = jtwyjzmj;
    }

    public Integer getZtrk() {
        return ztrk==null?0:ztrk;
    }

    public void setZtrk(Integer ztrk) {
        this.ztrk = ztrk;
    }

    public Integer getHs() {
        return hs==null?0:hs;
    }

    public void setHs(Integer hs) {
        this.hs = hs;
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

    public Integer getJjzz() {
        return jjzz==null?0:jjzz;
    }

    public void setJjzz(Integer jjzz) {
        this.jjzz = jjzz;
    }

    public Integer getFeicount() {
        return feicount==null?0:feicount;
    }

    public void setFeicount(Integer feicount) {
        this.feicount = feicount;
    }

    public Integer getHjcount() {
        return hjcount==null?0:hjcount;
    }

    public void setHjcount(Integer hjcount) {
        this.hjcount = hjcount;
    }

    public BigDecimal getHjzdmj() {
        return hjzdmj==null?new BigDecimal(0):hjzdmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setHjzdmj(BigDecimal hjzdmj) {
        this.hjzdmj = hjzdmj;
    }

    public BigDecimal getHjjzmj() {
        return hjjzmj==null?new BigDecimal(0):hjjzmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setHjjzmj(BigDecimal hjjzmj) {
        this.hjjzmj = hjjzmj;
    }

    public BigDecimal getJsydmj() {
        return jsydmj==null?new BigDecimal(0):jsydmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setJsydmj(BigDecimal jsydmj) {
        this.jsydmj = jsydmj;
    }

    public BigDecimal getNydmj() {
        return nydmj==null?new BigDecimal(0):nydmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setNydmj(BigDecimal nydmj) {
        this.nydmj = nydmj;
    }

    public BigDecimal getGdmj() {
        return gdmj==null?new BigDecimal(0):gdmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setGdmj(BigDecimal gdmj) {
        this.gdmj = gdmj;
    }

    public BigDecimal getWlydmj() {
        return wlydmj==null?new BigDecimal(0):wlydmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setWlydmj(BigDecimal wlydmj) {
        this.wlydmj = wlydmj;
    }

    public BigDecimal getTxhj() {
        return txhj==null?new BigDecimal(0):txhj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setTxhj(BigDecimal txhj) {
        this.txhj = txhj;
    }

    public String getPgjs() {
        return pgjs;
    }

    public void setPgjs(String pgjs) {
        this.pgjs = pgjs;
    }

    public BigDecimal getPggh() {
        return pggh==null?new BigDecimal(0):pggh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setPggh(BigDecimal pggh) {
        this.pggh = pggh;
    }

    public String getZzsljs() {
        return zzsljs;
    }

    public void setZzsljs(String zzsljs) {
        this.zzsljs = zzsljs;
    }

    public BigDecimal getZzslgh() {
        return zzslgh==null?new BigDecimal(0):zzslgh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setZzslgh(BigDecimal zzslgh) {
        this.zzslgh = zzslgh;
    }

    public String getMlxcjs() {
        return mlxcjs;
    }

    public void setMlxcjs(String mlxcjs) {
        this.mlxcjs = mlxcjs;
    }

    public BigDecimal getMlxcgh() {
        return mlxcgh==null?new BigDecimal(0):mlxcgh.setScale(1, RoundingMode.HALF_UP);
    }

    public void setMlxcgh(BigDecimal mlxcgh) {
        this.mlxcgh = mlxcgh;
    }

    public BigDecimal getZhaimj() {
        return zhaimj==null?new BigDecimal(0):zhaimj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setZhaimj(BigDecimal zhaimj) {
        this.zhaimj = zhaimj;
    }

    public BigDecimal getZhaijzmj() {
        return zhaijzmj==null?new BigDecimal(0):zhaijzmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setZhaijzmj(BigDecimal zhaijzmj) {
        this.zhaijzmj = zhaijzmj;
    }

    public Integer getZhaicount() {
        return zhaicount==null?0:zhaicount;
    }

    public void setZhaicount(Integer zhaicount) {
        this.zhaicount = zhaicount;
    }

    public BigDecimal getFeimj() {
        return feimj==null?new BigDecimal(0):feimj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setFeimj(BigDecimal feimj) {
        this.feimj = feimj;
    }

    public BigDecimal getFeijzmj() {
        return feijzmj==null?new BigDecimal(0):feijzmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setFeijzmj(BigDecimal feijzmj) {
        this.feijzmj = feijzmj;
    }

    public BigDecimal getGyjsydmj() {
        return gyjsydmj==null?new BigDecimal(0):gyjsydmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setGyjsydmj(BigDecimal gyjsydmj) {
        this.gyjsydmj = gyjsydmj;
    }

    public BigDecimal getGynydmj() {
        return gynydmj==null?new BigDecimal(0):gynydmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setGynydmj(BigDecimal gynydmj) {
        this.gynydmj = gynydmj;
    }

    public BigDecimal getGygdmj() {
        return gygdmj==null?new BigDecimal(0):gygdmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setGygdmj(BigDecimal gygdmj) {
        this.gygdmj = gygdmj;
    }

    public BigDecimal getGywlydmj() {
        return gywlydmj==null?new BigDecimal(0):gywlydmj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setGywlydmj(BigDecimal gywlydmj) {
        this.gywlydmj = gywlydmj;
    }

    public BigDecimal getGytxhj() {
        return gytxhj==null?new BigDecimal(0):gytxhj.setScale(1, RoundingMode.HALF_UP);
    }

    public void setGytxhj(BigDecimal gytxhj) {
        this.gytxhj = gytxhj;
    }
}
