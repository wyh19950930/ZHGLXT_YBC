package com.jymj.zhglxt.zjd.bean.wy;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 退租信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-05-27
 */
public class WithdrawalLeaseDto {

    private Long roomId;

    private String fjbh;

    private String zlje;

    private String syts;

    private String tfrq;

    private String tfyy;

    private List<HousingFile> tzysImgList;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getFjbh() {
        return fjbh;
    }

    public void setFjbh(String fjbh) {
        this.fjbh = fjbh;
    }

    public String getZlje() {
        return zlje;
    }

    public void setZlje(String zlje) {
        this.zlje = zlje;
    }

    public String getSyts() {
        return syts;
    }

    public void setSyts(String syts) {
        this.syts = syts;
    }

    public String getTfrq() {
        return tfrq;
    }

    public void setTfrq(String tfrq) {
        this.tfrq = tfrq;
    }

    public String getTfyy() {
        return tfyy;
    }

    public void setTfyy(String tfyy) {
        this.tfyy = tfyy;
    }

    public List<HousingFile> getTzysImgList() {
        if (tzysImgList == null){
            tzysImgList = new ArrayList<>();
        }
        return tzysImgList;
    }

    public void setTzysImgList(List<HousingFile> tzysImgList) {
        this.tzysImgList = tzysImgList;
    }
}
