package com.jymj.zhglxt.rjhj.bean.yl;


import java.io.Serializable;

/**
 * Created by ljj on 2018/8/30.
 */
public class ApplyFileEntity implements Serializable {
    private Integer id;
    //'申请id 无用
    private Integer appId;
    private Integer ylId;
    //'资料名称'
    private String fileName;
    //'文件地址'
    private String url;
    private String path;
    //'上传时间
    private String update;
    //1翻建申请书2测绘图3户口本4身份证5四邻指界同意书6村民代表大会意见7公司证明0其他
    //材料类型  1翻建申请书2测绘图3户口本4身份证5四邻指界同意书6村民代表大会意见7公司证明0其他
    private Integer cltype;
    private String cltypeText;
    //备注
    private String remark;

    //申请
    private ApplyEntity applyEntity;

    public String getPath() {
        return path==null?"":path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getYlId() {
        return ylId;
    }

    public void setYlId(Integer ylId) {
        this.ylId = ylId;
    }

    public String getFileName() {
        return fileName==null?"":fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url==null?"":url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public Integer getCltype() {
        return cltype;
    }

    public void setCltype(Integer cltype) {
        this.cltype = cltype;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ApplyEntity getApplyEntity() {
        return applyEntity;
    }

    public void setApplyEntity(ApplyEntity applyEntity) {
        this.applyEntity = applyEntity;
    }

    public String getCltypeText() {
        if (getCltype() != null) {
            return ApplyFileEnum.getName(getCltype());
        }
        return "";
    }
}
