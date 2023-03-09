package com.jymj.zhglxt.rjhj.bean.yl;

import java.io.Serializable;

/**
 * Created by dl on 2019/10/15.
 */
public class ZjdFileEntity implements Serializable {
    private Integer id;//
    private Integer key;//	文件类型
    private String keyText;
    private String path;//
    private String update;//	上传时间
    private String filename;//
    private Integer status;//	流程状态
    private String remark;//	备注
    private Integer type;//1退出 2流转 3申请
    private Integer zjdId;

    public Integer getZjdId() {
        return zjdId;
    }

    public void setZjdId(Integer zjdId) {
        this.zjdId = zjdId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        if(key!=null){
            return ZjdSqFileEnum.getName(key);
        }
        return "";
    }

    public void setKey(Integer key) {
        this.key = key;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKeyText(){
        if(getType()!=null && getType()==1){
            return ZjdTcEnum.getName(getType());
        }else if(getType()!=null && getType()==2){
            return ZjdLzEnum.getName(getType());
        }
        return null;
    }
}
