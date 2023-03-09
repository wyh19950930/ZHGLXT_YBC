package com.jymj.zhglxt.zjd.bean.jsjb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lc} on 2020/4/28.
 */
public class SysDepartEntity {
    private Integer id;
    private String name;//部门名称
    private Integer orderNum;//部门优先级
    private String fullname;//	部门全称
    private Integer parentid;//部门上级
    private String text;//备注
    private String bh;//部门编号
    private Integer keyid;
    private String code;
    private List<SysDepartEntity> children = new ArrayList<>();

    public List<SysDepartEntity> getChildren() {
        if (children==null){
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<SysDepartEntity> children) {
        this.children = children;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getFullname() {
        return fullname==null?"":fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getText() {
        return text==null?"":text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBh() {
        return bh==null?"":bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }
}
