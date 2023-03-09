package com.jymj.zhglxt.rjhj.bean;

import java.io.Serializable;

public class PjRoleEnviorEntity implements Serializable {
    private Integer id;
    private Integer pjid;//pj_proj主键
    private Integer eid;//pj_envior主键 *
    private Integer roleid;//sys_role角色id
    private Integer takeroleid;//sys_role角色id--被推送角色 *
    private Integer type;//
    private String pushtime;//下发/推送 时间
    private String taketime;//接收时间
    private String puname;//推送人姓名 *
    private String putel;//推送人电话 *
    private String remark;//备注/建议/叮嘱

    @Override
    public String toString() {
        return "PjRoleEnviorEntity{" +
                "id=" + id +
                ", pjid=" + pjid +
                ", eid=" + eid +
                ", roleid=" + roleid +
                ", takeroleid=" + takeroleid +
                ", pushtime=" + pushtime +
                ", taketime=" + taketime +
                ", puname='" + puname + '\'' +
                ", putel='" + putel + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTakeroleid() {
        return takeroleid;
    }

    public void setTakeroleid(Integer takeroleid) {
        this.takeroleid = takeroleid;
    }

    public String getPuname() {
        return puname;
    }

    public void setPuname(String puname) {
        this.puname = puname;
    }

    public String getPutel() {
        return putel;
    }

    public void setPutel(String putel) {
        this.putel = putel;
    }

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

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getPushtime() {
        return pushtime;
    }

    public void setPushtime(String pushtime) {
        this.pushtime = pushtime;
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PjRoleEnviorEntity(Integer id, Integer pjid, Integer eid, Integer roleid, Integer takeroleid, String pushtime, String taketime, String puname, String putel, String remark) {
        this.id = id;
        this.pjid = pjid;
        this.eid = eid;
        this.roleid = roleid;
        this.takeroleid = takeroleid;
        this.pushtime = pushtime;
        this.taketime = taketime;
        this.puname = puname;
        this.putel = putel;
        this.remark = remark;
    }

    public PjRoleEnviorEntity() {
    }
}
