package com.jymj.zhglxt.zjd.bean.wy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/5/27. 房源信息实体
 */
public class HousingEntity implements Serializable {
    private Long id;
    private Long ylId;//	yl----objectid
    private String mph;//	门牌号
    private String name;//	产权人名称
    private String jcsj;//	建成时间
    private String jzjg;//	建筑结构
    private BigDecimal jzmj;//	建筑面积
    private String jzcs;//	建筑层数
    private String startDate;//	开始时间
    private String endDate;//	结束时间
    private String rentMoney;//	租金
    private Integer fjs;//	房间数
    private String remark;//	备注

    private List<HousingFile> housingFiles;
    private List<HousingRoom> housingRooms;//房间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYlId() {
        return ylId;
    }

    public void setYlId(Long ylId) {
        this.ylId = ylId;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj;
    }

    public String getJzjg() {
        return jzjg;
    }

    public void setJzjg(String jzjg) {
        this.jzjg = jzjg;
    }

    public BigDecimal getJzmj() {
        return jzmj;
    }

    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }

    public String getJzcs() {
        return jzcs;
    }

    public void setJzcs(String jzcs) {
        this.jzcs = jzcs;
    }


    public String getRentMoney() {
        return rentMoney;
    }

    public void setRentMoney(String rentMoney) {
        this.rentMoney = rentMoney;
    }

    public Integer getFjs() {
        return fjs;
    }

    public void setFjs(Integer fjs) {
        this.fjs = fjs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<HousingRoom> getHousingRooms() {
        if (housingRooms == null){
            housingRooms = new ArrayList<>();
        }
        return housingRooms;
    }

    public void setHousingRooms(List<HousingRoom> housingRooms) {
        this.housingRooms = housingRooms;
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
}
