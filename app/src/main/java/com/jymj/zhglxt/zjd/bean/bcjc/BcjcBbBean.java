package com.jymj.zhglxt.zjd.bean.bcjc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BcjcBbBean {

    private String xzqmc;
    private List<Object> list;

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public List<Object> getList() {
        return list==null?new ArrayList<>():list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
