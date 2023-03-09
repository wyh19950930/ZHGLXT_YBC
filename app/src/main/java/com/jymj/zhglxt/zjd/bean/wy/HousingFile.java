package com.jymj.zhglxt.zjd.bean.wy;


import com.jymj.zhglxt.ldrkgl.personal.bean.FileUploadEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lc on 2022/5/27.
 */
public class HousingFile implements Serializable {

    private Long id;
    private Long housId;//	housing----id
    private String filename;
    private String path;
    private String name;//	文件名
    private Integer type;//文件类型
    private Integer isLease;//1返租 2出租

    private String url;//文件路径(全)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHousId() {
        return housId;
    }

    public void setHousId(Long housId) {
        this.housId = housId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsLease() {
        return isLease;
    }

    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HousingFile(Integer type, String url) {
        this.type = type;
        this.url = url;
    }
}
