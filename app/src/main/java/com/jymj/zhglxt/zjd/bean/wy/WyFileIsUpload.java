package com.jymj.zhglxt.zjd.bean.wy;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/6/8 14:50
 */
public class WyFileIsUpload {
    private int type;
    private String url;
    private boolean isShow;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public WyFileIsUpload(int type, String url, boolean isShow) {
        this.type = type;
        this.url = url;
        this.isShow = isShow;
    }
}
