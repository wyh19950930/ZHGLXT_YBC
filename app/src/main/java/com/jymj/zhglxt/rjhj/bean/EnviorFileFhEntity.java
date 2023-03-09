package com.jymj.zhglxt.rjhj.bean;

/**
 * Created by ${lc} on 2021/1/27.
 */
public class EnviorFileFhEntity {

    /*{
        "id":4159,
            "name":"mmexport1613699196923.jpg",
            "path":"enviorFile\20210219095845635.jpg",
            "entime":"2021-02-19",
            "enpsn":478,
            "filetype":3,
            "filetypeText":"整改",
            "msid":1825,
            "type":1
    }*/
    private long id;
    private long msid;
    private Integer type;
    private Integer counts;//(数量)
    private Integer filetype;
    private String path;
    private String code;
    private String name;//名称（区 镇 村）
    private String entime;
    private Integer enpsn;
    private String filetypeText;
    private String location;//坐标点
    private Integer monwarnin;//是否逾期
    private Integer zdwt;//是否重大点位

    public Integer getMonwarnin() {
        return monwarnin==null?0:monwarnin;
    }

    public void setMonwarnin(Integer monwarnin) {
        this.monwarnin = monwarnin;
    }

    public Integer getZdwt() {
        return zdwt==null?0:zdwt;
    }

    public void setZdwt(Integer zdwt) {
        this.zdwt = zdwt;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location==null?"":location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCounts() {
        return counts==null?0:counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getPath() {
        return path==null?"":path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMsid() {
        return msid;
    }

    public void setMsid(long msid) {
        this.msid = msid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
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

    public String getFiletypeText() {
        return filetypeText;
    }

    public void setFiletypeText(String filetypeText) {
        this.filetypeText = filetypeText;
    }
}
