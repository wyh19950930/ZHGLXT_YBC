package com.jymj.zhglxt.zjd.bean.yzt;


import java.io.Serializable;

/**
 * Created by ${lc} on 2022/4/2.
 */
public class HujiRelationEntity  implements Serializable {
    private Long id;
    private Integer zhaiid;
    private Integer parentid;
    private String relation;//关系

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid==null?0:parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getZhaiid() {
        return zhaiid==null?0:zhaiid;
    }

    public void setZhaiid(Integer zhaiid) {
        this.zhaiid = zhaiid;
    }

    public String getRelation() {
        return relation==null?"":relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
