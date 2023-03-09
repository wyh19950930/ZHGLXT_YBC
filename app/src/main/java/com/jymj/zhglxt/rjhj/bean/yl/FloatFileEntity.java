package com.jymj.zhglxt.rjhj.bean.yl;

import java.io.Serializable;

/**
 * Created by ${lc} on 2020/7/13.
 */
public class FloatFileEntity implements Serializable {
    private Integer id;
    private String path;//路径
    private String update;//上传时间
    private String filename;//名称
    private Integer floatId;
    private String remark;	//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getFloatId() {
        return floatId;
    }

    public void setFloatId(Integer floatId) {
        this.floatId = floatId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
