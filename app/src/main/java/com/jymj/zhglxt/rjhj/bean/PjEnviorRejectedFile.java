package com.jymj.zhglxt.rjhj.bean;


import com.jymj.zhglxt.rjhj.bean.enums.EnviorSupvsEnum;

import java.io.Serializable;

public class PjEnviorRejectedFile implements Serializable {
    private Integer id;
    private String name;
    private String path;
    private String url;
    private String entime;//上传时间
    private Integer enpsn;//上传人
    private Integer filetype;//文件类型(envior--qutype项目状态文件)
    private String filetypeText;
    private Integer msid;//pj_envior_supvs主键(外键)
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url==null?"":url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PjEnviorFileEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", entime=" + entime +
                ", enpsn=" + enpsn +
                ", filetype=" + filetype +
                ", filetypeText='" + filetypeText + '\'' +
                ", msid=" + msid +
                '}';
    }

    public String getFiletypeText() {
        if(filetype!=null){
            return EnviorSupvsEnum.getName(filetype);
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public Integer getMsid() {
        return msid;
    }

    public void setMsid(Integer msid) {
        this.msid = msid;
    }

    public PjEnviorRejectedFile(Integer id, String name, String path, Integer pid, String entime, Integer enpsn, String jlrname, String jlrtel, Integer filetype, String filetypeText, Integer msid) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.entime = entime;
        this.enpsn = enpsn;
        this.filetype = filetype;
        this.filetypeText = filetypeText;
        this.msid = msid;
    }

    public PjEnviorRejectedFile() {
    }
}
