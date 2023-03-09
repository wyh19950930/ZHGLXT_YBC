package com.jymj.zhglxt.zjd.bean.xm;


/**
 * Created by lc on 2022/8/3.
 */
public class ProjectDto {
    private Long id;//项目id
    private String name;//项目名称
    private String xmlb;//	项目类别
    private String tzzt;//	投资主体
    private String tzfs;//	投资方式

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXmlb() {
        return xmlb;
    }

    public void setXmlb(String xmlb) {
        this.xmlb = xmlb;
    }

    public String getTzzt() {
        return tzzt;
    }

    public void setTzzt(String tzzt) {
        this.tzzt = tzzt;
    }

    public String getTzfs() {
        return tzfs;
    }

    public void setTzfs(String tzfs) {
        this.tzfs = tzfs;
    }
}
