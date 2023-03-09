package com.jymj.zhglxt.zjd.bean.wy;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 入住（租赁）信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-05-27
 */
public class LeaseDto implements Serializable {

    private Long id;

    private Long flowInfoId;

    private Long roomId;

    private String roomNo;//房间编号

    private String czr;//承租人

    private String czmj;//出租面积

    private String zlqx;//租赁期限

    private String zlje;//支付金额

    private String zffs;//支付方式

    private String bz;//备注
    private String startDate;//起租时间
    private String endDate;//停租时间

    private String mph;

    private String idCard;

    private Integer objectId;

    private List<HousingFile> housingFiles;

    public String getMph() {
        return mph == null?"":mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public String getIdCard() {
        return idCard == null?"":idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getObjectId() {
        return objectId == null?0:objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlowInfoId() {
        return flowInfoId;
    }

    public void setFlowInfoId(Long flowInfoId) {
        this.flowInfoId = flowInfoId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getCzmj() {
        return czmj;
    }

    public void setCzmj(String czmj) {
        this.czmj = czmj;
    }

    public String getZlqx() {
        return zlqx;
    }

    public void setZlqx(String zlqx) {
        this.zlqx = zlqx;
    }

    public String getZlje() {
        return zlje;
    }

    public void setZlje(String zlje) {
        this.zlje = zlje;
    }

    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<HousingFile> getHousingFiles() {
        if (housingFiles==null){
            housingFiles = new ArrayList<>();
        }
        return housingFiles;
    }

    public void setHousingFiles(List<HousingFile> housingFiles) {
        this.housingFiles = housingFiles;
    }
}
