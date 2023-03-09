package com.jymj.zhglxt.rjhj.bean.yl;


import com.jymj.zhglxt.util.TimeUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ljj on 2018/8/30.
 */
public class ApplyChildEntity implements Serializable {
    private Integer id;
    //'申请项目外键'
    private Integer appid;
    //'施工状态'
    private Integer sgtype;
    private String sgtypeText;
    //'施工方联系方式'
    private String sgphone;
    //'施工计划进场时间'
    private String sgjhjcsj;
    //'施工进场时间'
    private String sgjcsj;
    //'计划完成时间'
    private String jhwcsj;
    //'施工完成时间'
    private String sgwcdate;
    private String remark;
    //'确认施工时间'
    private String qrsgdate;

    List<ApplyChildFileEty> applyChildFileEtyList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Integer getSgtype() {
        return sgtype;
    }

    public void setSgtype(Integer sgtype) {
        this.sgtype = sgtype;
    }

    public String getSgphone() {
        return sgphone;
    }

    public void setSgphone(String sgphone) {
        this.sgphone = sgphone;
    }

    public String getSgjhjcsj() {
        String string = TimeUtil.getStringByFormat(sgjhjcsj, TimeUtil.dateFormatYMD);
        return sgjhjcsj == null ? "" : string;
    }

    public void setSgjhjcsj(String sgjhjcsj) {
        this.sgjhjcsj = sgjhjcsj;
    }

    public String getSgjcsj() {
        String string = TimeUtil.getStringByFormat(sgjcsj, TimeUtil.dateFormatYMD);
        return sgjcsj == null ? "" : string;
    }

    public void setSgjcsj(String sgjcsj) {
        this.sgjcsj = sgjcsj;
    }

    public String getJhwcsj() {
        String string = TimeUtil.getStringByFormat(jhwcsj, TimeUtil.dateFormatYMD);
        return jhwcsj == null ? "" : string;
    }

    public void setJhwcsj(String jhwcsj) {
        this.jhwcsj = jhwcsj;
    }

    public String getSgwcdate() {
        String string = TimeUtil.getStringByFormat(sgwcdate, TimeUtil.dateFormatYMD);
        return sgwcdate == null ? "" : string;
    }

    public void setSgwcdate(String sgwcdate) {
        this.sgwcdate = sgwcdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQrsgdate() {
        String string = TimeUtil.getStringByFormat(qrsgdate, TimeUtil.dateFormatYMD);
        return qrsgdate == null ? "" : string;
    }

    public void setQrsgdate(String qrsgdate) {
        this.qrsgdate = qrsgdate;
    }

    public List<ApplyChildFileEty> getApplyChildFileEtyList() {
        return applyChildFileEtyList;
    }

    public void setApplyChildFileEtyList(List<ApplyChildFileEty> applyChildFileEtyList) {
        this.applyChildFileEtyList = applyChildFileEtyList;
    }

    public String getSgtypeText(){
        if(getSgtype() !=null){
            return ApplyChildEnum.getName(getSgtype());
        }
        return null;
    }
}
