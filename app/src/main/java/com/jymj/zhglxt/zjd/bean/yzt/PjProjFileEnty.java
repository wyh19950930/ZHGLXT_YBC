package com.jymj.zhglxt.zjd.bean.yzt;

import java.io.Serializable;

/**
 * Created by ljj on 2020/6/23.
 */
public class PjProjFileEnty implements Serializable {
    private Integer id;//主键
    private Integer pjid;//pj_proj---主键
    private Integer projSpeed;//步骤
    private String picName;//图片名称
    private String picPath;//图片路径
    private String remark;//备注
    private String entime;//上传时间
    private Integer enpsn;//上传用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPjid() {
        return pjid;
    }

    public void setPjid(Integer pjid) {
        this.pjid = pjid;
    }

    public Integer getProjSpeed() {
        return projSpeed;
    }

    public void setProjSpeed(Integer projSpeed) {
        this.projSpeed = projSpeed;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEntime() {
        return entime;
    }

    public void setEntime(String entime) {
        this.entime = entime;
    }

    public Integer getEnpsn() {
        return enpsn;
    }

    public void setEnpsn(Integer enpsn) {
        this.enpsn = enpsn;
    }
}
