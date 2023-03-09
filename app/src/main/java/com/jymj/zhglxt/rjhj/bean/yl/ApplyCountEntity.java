package com.jymj.zhglxt.rjhj.bean.yl;

import java.io.Serializable;

/**
 * Created by ${lc} on 2021/8/16.
 */
public class ApplyCountEntity implements Serializable {
    private String code;
    private String name;
    private String location;
    private int counts;

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location==null?"":location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
