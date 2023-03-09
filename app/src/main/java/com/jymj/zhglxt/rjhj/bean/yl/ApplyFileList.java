package com.jymj.zhglxt.rjhj.bean.yl;

import java.util.List;

public class ApplyFileList {
    private String name;
    private List<ApplyFileEntity> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApplyFileEntity> getList() {
        return list;
    }

    public void setList(List<ApplyFileEntity> list) {
        this.list = list;
    }
}
