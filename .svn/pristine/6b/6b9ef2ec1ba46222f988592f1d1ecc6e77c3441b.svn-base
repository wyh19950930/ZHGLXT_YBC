package com.jymj.zhglxt.zjd.bean.xm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/5/27 14:52
 */
public class XmJsqkEntity {
    private String name;
    private List<XmJsqkEntity> child = new ArrayList<>();
    private boolean check = false;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XmJsqkEntity> getChild() {
        return child == null?new ArrayList<>():child;
    }

    public void setChild(List<XmJsqkEntity> child) {
        this.child = child;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public XmJsqkEntity(String name) {
        this.name = name;
    }
}
