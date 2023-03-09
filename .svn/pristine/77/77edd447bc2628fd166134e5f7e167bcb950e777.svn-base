package com.jymj.zhglxt.rjhj.bean.yl;

import com.jymj.zhglxt.api.ApiConstants;
import com.jymj.zhglxt.util.TimeUtil;

import java.io.Serializable;

/**
 * Created by ljj on 2018/8/30.
 */
public class ApplyChildFileEty implements Serializable {
    private Integer id;
    //'申请项目外键'
    private Integer appid;
    //'施工状态'
    private Integer sgtype;
    private String sgtypeText;
    //'文件类型'
    private Integer filetype;
    private String filetypeText;
    //'文件名称'
    private String filename;
    //'文件地址'
    private String fileurl;
    //'上传时间'
    private String update;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Integer getSgtype() {
        return sgtype;
    }

    public void setSgtype(Integer sgtype) {
        this.sgtype = sgtype;
    }

    public Integer getFiletype() {
        return filetype==null?0:filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        if (fileurl==null){
            return null;
        }else {
            fileurl=fileurl.replaceAll("\\\\","/");
            return ApiConstants.BASE_URL+fileurl;
        }
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getUpdate() {
        String string = TimeUtil.getStringByFormat(update, TimeUtil.dateFormatYMD);
        return update == null ? "" : string;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String getSgtypeText(){
        if(getSgtype() !=null){
            return ApplyChildEnum.getName(getSgtype());
        }
        return null;
    }

    public String getFiletypeText() {
        if(getFiletype()!=null){
            return ApplyFileEnum.getName(getFiletype());
        }
        return null;
    }
}
