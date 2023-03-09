package com.jymj.zhglxt.ldrkgl.personal.bean;

import java.io.Serializable;

/**
 * 上传文件通用类
 * @author Lzh
 * @date 2021/7/16 15:21
 */
public class FileUploadEntity implements Serializable {
    private static final long serialVersionUID = -8651436965074593489L;
    private Long fileId;//被上传文件的id 需要单独set
    private String url;//完整url
    private String path;//完整路径
    private String dataUrl;//数据库储存url
    private String dataPath;//数据库储存路径
    private String urlHead;//url前缀
    private String pathHead;//路径前缀
    private String uploadType;//上传类型 确定上传文件的源文件夹
    private String fileName;//文件名
    private String localIp;//ip地址
//    private PathUtiles pathUtiles;


    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getUrl() {
        return url==null?"":url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path==null?"":path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDataUrl() {
        return dataUrl==null?"":dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getDataPath() {
        return dataPath==null?"":dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getUrlHead() {
        return urlHead==null?"":urlHead;
    }

    public void setUrlHead(String urlHead) {
        this.urlHead = urlHead;
    }

    public String getPathHead() {
        return pathHead==null?"":pathHead;
    }

    public void setPathHead(String pathHead) {
        this.pathHead = pathHead;
    }

    public String getUploadType() {
        return uploadType==null?"":uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getFileName() {
        return fileName==null?"":fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalIp() {
        return localIp==null?"":localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }
}
