package com.jymj.zhglxt.rjhj.bean;


import com.jymj.zhglxt.rjhj.bean.enums.CnwEnum;
import com.jymj.zhglxt.rjhj.bean.enums.EnviorSupvsEnum;
import com.jymj.zhglxt.rjhj.bean.enums.GcghEunm;
import com.jymj.zhglxt.rjhj.bean.enums.HcqtghEnum;
import com.jymj.zhglxt.rjhj.bean.enums.HjzzejEnum;
import com.jymj.zhglxt.rjhj.bean.enums.HjzzflEnum;
import com.jymj.zhglxt.rjhj.bean.enums.HjzzsjEnum;
import com.jymj.zhglxt.rjhj.bean.enums.IsTrueEnum;
import com.jymj.zhglxt.rjhj.bean.enums.Ldwhenum;
import com.jymj.zhglxt.rjhj.bean.enums.LhyhEnum;
import com.jymj.zhglxt.rjhj.bean.enums.SdljEnum;
import com.jymj.zhglxt.rjhj.bean.enums.ShljzlEnum;
import com.jymj.zhglxt.rjhj.bean.enums.ShwszlEnum;
import com.jymj.zhglxt.rjhj.bean.enums.YswhEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PjEnviorSupvsEntity implements Serializable {

    private Integer id;
    private Integer no;
    private String projname;//项目名称
    private String zrdw;//责任单位
    private String questions;//问题情况
    private String remark;//记录备注
    private String jlr;//记录人
    private String jltime;//记录时间
    private Integer enpsn;//记录人id
    private Integer qutype;//问题状态 1巡查,考核  2下发 3整改  4待审核  5销账 //问题状态 1待处理 2整改中 3验收完成
    private String qutypeText;
    private String location;//位置（定位）
    private String ksname;//处理人姓名
    private String kstel;//处理人电话
    private String cgname;//验收人姓名
    private String cgtel;	//验收人电话
    private String jlrtel;//记录人电话
    private String wz;//位置（行政区名称等）
    private String ksbz;//处理备注
    private String cgbz;//验收备注
    private String zgyq;//整改要求
    private String qxtime;//期限范围--整改期限
    private String kstime;//处理时间
    private String cgtime;//验收时间
    private String cgpj;//验收评价
    private String kscf;//处理处罚
    private String xzqmc;//行政区名称
    private Integer warning;//是否超过处理时间
    private Integer monwarnin;//0正常 1 超期
    private String code;
    private List<PjEnviorFileEntity> pjEnviorFileEntities;
    private Integer[] fileIds;
    private Integer shljzl;//	生活垃圾治理
    private String shljzlText;
    private Integer shwszl;//	生活污水治理
    private String shwszlText;
    private Integer gcgh;//公厕管护
    private String gcghText;
    private Integer sdlj;//私搭乱建
    private String sdljText;
    private Integer yswh;//太阳能浴室运行维护
    private String yswhText;
    private Integer hcqtgh;//	户厕清掏管护
    private String hcqtghText;
    private Integer lhyh;//绿化养护
    private String lhyhText;
    private Integer ldwh;//节能路灯维护
    private String ldwhText;
    private Integer cnw;//是否村内外
    private String cnwText;
    private Integer gddw;//是否固定点位
    private String gddwText;
    private Integer zdwt;//是否重大问题
    private String zdwtText;
   /* private Integer hjzzyjfl;//环境整治一级分类
    private String hjzzyjflText;*/
    private Integer counts;//驳回次数
    private Integer jsry;//接收人员
    private String xfjy;//下发建议
    private String xfsj;//下发时间
    private List<PjEnviorRejectedEntity> enviorRejectedEntities;
    private Integer hjzzyjfl;//环境整治一级分类
    private Integer hjzzej;//环境整治二级分类
    private Integer hjzzsj;//环境整治三级分类
    private Integer[] hjzzfls= new Integer[0];
    private String comment;//内业驳回 外业返回的备注
    private Integer zjwj ;//1 直接完结  判断是否是直接完结的
    private Integer gddwid ;//固定点位id
    private String bh ;//固定点位编号
    private String rejectYj ;//驳回意见
    private String rejectBz ;//驳回备注
    private String endtime ;//完结时间

    public String getEndtime() {
        return endtime==null?"":endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getNo() {
        return no==null?0:no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getRejectYj() {
        return rejectYj==null?"":rejectYj;
    }

    public void setRejectYj(String rejectYj) {
        this.rejectYj = rejectYj;
    }

    public String getRejectBz() {
        return rejectBz==null?"":rejectBz;
    }

    public void setRejectBz(String rejectBz) {
        this.rejectBz = rejectBz;
    }

    public String getBh() {
        return bh==null?"":bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public Integer getGddwid() {
        return gddwid==null?0:gddwid;
    }

    public void setGddwid(Integer gddwid) {
        this.gddwid = gddwid;
    }

    public Integer getZjwj() {
        return zjwj==null?0:zjwj;
    }

    public void setZjwj(Integer zjwj) {
        this.zjwj = zjwj;
    }

    public String getComment() {
        return comment==null?"":comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer[] getHjzzfls() {
        return hjzzfls;
    }

    public void setHjzzfls(Integer[] hjzzfls) {
        this.hjzzfls = hjzzfls;
    }

    public String getHjzzejText(){
        if (getHjzzej() != null){
            return HjzzejEnum.getName(getHjzzej());
        }
        return null;
    }
    public String getHjzzsjText(){
        if (getHjzzsj() != null){
            return HjzzsjEnum.getName(getHjzzsj());
        }
        return null;
    }
    public Integer getHjzzej() {
        return hjzzej==null?-1:hjzzej;
    }

    public void setHjzzej(Integer hjzzej) {
        this.hjzzej = hjzzej;
    }

    public Integer getHjzzsj() {
        return hjzzsj==null?-1:hjzzsj;
    }

    public void setHjzzsj(Integer hjzzsj) {
        this.hjzzsj = hjzzsj;
    }

    public Integer getMonwarnin() {
        return monwarnin==null?0:monwarnin;
    }

    public void setMonwarnin(Integer monwarnin) {
        this.monwarnin = monwarnin;
    }

    public Integer getCounts() {
        return counts==null?0:counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getJsry() {
        return jsry==null?0:jsry;
    }

    public void setJsry(Integer jsry) {
        this.jsry = jsry;
    }

    public String getXfjy() {
        return xfjy==null?"":xfjy;
    }

    public void setXfjy(String xfjy) {
        this.xfjy = xfjy;
    }

    public String getXfsj() {
        return xfsj==null?"":xfsj;
    }

    public void setXfsj(String xfsj) {
        this.xfsj = xfsj;
    }

    public List<PjEnviorRejectedEntity> getEnviorRejectedEntities() {
        return enviorRejectedEntities==null?new ArrayList<>():enviorRejectedEntities;
    }

    public void setEnviorRejectedEntities(List<PjEnviorRejectedEntity> enviorRejectedEntities) {
        this.enviorRejectedEntities = enviorRejectedEntities;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZdwtText(){
        if (getZdwt() != null){
            return IsTrueEnum.getName(getZdwt());
        }
        return null;
    }

    public String getGddwText(){
        if (getGddw() != null){
            return IsTrueEnum.getName(getGddw());
        }
        return null;
    }

    public String getCnwText(){
        if (getCnw() != null){
            return CnwEnum.getName(getCnw());
        }
        return null;
    }

    public String getLdwhText(){
        if (getLdwh() != null){
            return Ldwhenum.getName(getLdwh());
        }
        return null;
    }

    public String getLhyhText(){
        if (getLhyh() != null){
            return LhyhEnum.getName(getLhyh());
        }
        return null;
    }

    public String getHcqtghText(){
        if (getHcqtgh() != null){
            return HcqtghEnum.getName(getHcqtgh());
        }
        return null;
    }

    public String getYswhText(){
        if (getYswh() != null){
            return YswhEnum.getName(getYswh());
        }
        return null;
    }


    public String getHjzzyjflText(){
        if (getHjzzyjfl() != null){
            return HjzzflEnum.getName(getHjzzyjfl());
        }
        return null;
    }

    public String getSdljText(){
        if (getSdlj() != null){
            return SdljEnum.getName(getSdlj());
        }
        return null;
    }

    public String getGcghText(){
        if (getGcgh() != null){
            return GcghEunm.getName(getGcgh());
        }
        return null;
    }

    public String getShwszlText(){
        if (getShwszl() != null){
            return ShwszlEnum.getName(getShwszl());
        }
        return null;
    }

    public Integer getHjzzyjfl() {
        return hjzzyjfl==null?-1:hjzzyjfl;
    }

    public void setHjzzyjfl(Integer hjzzyjfl) {
        this.hjzzyjfl = hjzzyjfl;
    }

    public Integer getCnw() {
        return cnw==null?-1:cnw;
    }

    public void setCnw(Integer cnw) {
        this.cnw = cnw;
    }

    public Integer getGddw() {
        return gddw==null?-1:gddw;
    }

    public void setGddw(Integer gddw) {
        this.gddw = gddw;
    }

    public Integer getZdwt() {
        return zdwt==null?-1:zdwt;
    }

    public void setZdwt(Integer zdwt) {
        this.zdwt = zdwt;
    }

    public String getShljzlText(){
        if (getShljzl() != null){
            return ShljzlEnum.getName(getShljzl());
        }
        return null;
    }

    public Integer getShljzl() {
        return shljzl==null?-1:shljzl;
    }

    public void setShljzl(Integer shljzl) {
        this.shljzl = shljzl;
    }

    public Integer getShwszl() {
        return shwszl==null?-1:shwszl;
    }

    public void setShwszl(Integer shwszl) {
        this.shwszl = shwszl;
    }

    public Integer getGcgh() {
        return gcgh==null?-1:gcgh;
    }

    public void setGcgh(Integer gcgh) {
        this.gcgh = gcgh;
    }

    public Integer getSdlj() {
        return sdlj==null?-1:sdlj;
    }

    public void setSdlj(Integer sdlj) {
        this.sdlj = sdlj;
    }

    public Integer getYswh() {
        return yswh==null?-1:yswh;
    }

    public void setYswh(Integer yswh) {
        this.yswh = yswh;
    }

    public Integer getHcqtgh() {
        return hcqtgh==null?-1:hcqtgh;
    }

    public void setHcqtgh(Integer hcqtgh) {
        this.hcqtgh = hcqtgh;
    }

    public Integer getLhyh() {
        return lhyh==null?-1:lhyh;
    }

    public void setLhyh(Integer lhyh) {
        this.lhyh = lhyh;
    }

    public Integer getLdwh() {
        return ldwh==null?-1:ldwh;
    }

    public void setLdwh(Integer ldwh) {
        this.ldwh = ldwh;
    }



    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public Integer[] getFileIds() {
        return fileIds;
    }

    public void setFileIds(Integer[] fileIds) {
        this.fileIds = fileIds;
    }

    public Integer getWarning() {
        return warning;
    }

    public void setWarning(Integer warning) {
        this.warning = warning;
    }

    public List<PjEnviorFileEntity> getPjEnviorFileEntities() {
        return pjEnviorFileEntities == null?new ArrayList<>():pjEnviorFileEntities;
    }

    public void setPjEnviorFileEntities(List<PjEnviorFileEntity> pjEnviorFileEntities) {
        this.pjEnviorFileEntities = pjEnviorFileEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjname() {
        return projname == null?"":projname;
    }

    public void setProjname(String projname) {
        this.projname = projname;
    }

    public String getZrdw() {
        return zrdw == null?"":zrdw;
    }

    public void setZrdw(String zrdw) {
        this.zrdw = zrdw;
    }

    public String getQuestions() {
        return questions == null?"":questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getRemark() {
        return remark == null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJlr() {
        return jlr == null?"":jlr;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr;
    }

    public String getJltime() {
        return jltime == null?"":jltime;
    }

    public void setJltime(String jltime) {
        this.jltime = jltime;
    }

    public Integer getEnpsn() {
        return enpsn;
    }

    public void setEnpsn(Integer enpsn) {
        this.enpsn = enpsn;
    }

    public Integer getQutype() {
        return qutype==null?1:qutype;
    }

    public void setQutype(Integer qutype) {
        this.qutype = qutype;
    }

    public String getQutypeText() {
        if (getQutype() != null){
            return EnviorSupvsEnum.getName(getQutype());
        }
        return null;
    }


    public String getLocation() {
        return location==null?"":location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKsname() {
        return ksname== null?"":ksname;
    }

    public void setKsname(String ksname) {
        this.ksname = ksname;
    }

    public String getKstel() {
        return kstel == null?"":kstel;
    }

    public void setKstel(String kstel) {
        this.kstel = kstel;
    }

    public String getCgname() {
        return cgname== null?"":cgname;
    }

    public void setCgname(String cgname) {
        this.cgname = cgname;
    }

    public String getCgtel() {
        return cgtel == null?"":cgtel;
    }

    public void setCgtel(String cgtel) {
        this.cgtel = cgtel;
    }

    public String getJlrtel() {
        return jlrtel == null?"":jlrtel;
    }

    public void setJlrtel(String jlrtel) {
        this.jlrtel = jlrtel;
    }

    public String getWz() {
        return wz== null?"":wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public String getKsbz() {
        return ksbz == null?"":ksbz;
    }

    public void setKsbz(String ksbz) {
        this.ksbz = ksbz;
    }

    public String getCgbz() {
        return cgbz == null?"":cgbz;
    }

    public void setCgbz(String cgbz) {
        this.cgbz = cgbz;
    }

    public String getZgyq() {
        return zgyq == null?"":zgyq;
    }

    public void setZgyq(String zgyq) {
        this.zgyq = zgyq;
    }

    public String getQxtime() {
        return qxtime == null?"":qxtime;//请选择时间
    }

    public void setQxtime(String qxtime) {
        this.qxtime = qxtime;
    }

    public String getKstime() {
        return kstime == null?"":kstime;
    }//请选择时间

    public void setKstime(String kstime) {
        this.kstime = kstime;
    }

    public String getCgtime() {
        return cgtime == null?"":cgtime;
    }

    public void setCgtime(String cgtime) {
        this.cgtime = cgtime;
    }

    public String getCgpj() {
        return cgpj == null?"":cgpj;
    }

    public void setCgpj(String cgpj) {
        this.cgpj = cgpj;
    }

    public String getKscf() {
        return kscf == null?"":kscf;
    }

    public void setKscf(String kscf) {
        this.kscf = kscf;
    }
}
