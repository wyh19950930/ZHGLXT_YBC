package com.jymj.zhglxt.rjhj.bean.yl;

import java.io.Serializable;

/**
 * Created by ljj on 2018/8/30.
 */
public class ApplyLogEntity implements Serializable {
    private Integer id;
    private String bhdate;
    private String bhyj;
    private Integer appId;
    private Integer sptype;
    private String sptypeText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBhdate() {
        return bhdate;
    }

    public void setBhdate(String bhdate) {
        this.bhdate = bhdate;
    }

    public String getBhyj() {
        return bhyj;
    }

    public void setBhyj(String bhyj) {
        this.bhyj = bhyj;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getSptype() {
        return sptype;
    }

    public void setSptype(Integer sptype) {
        this.sptype = sptype;
    }

    private String getSptypeText(){
        if(getSptype() !=null){
            return ApplyEnum.getName(getSptype());
        }
        return null;
    }
}
