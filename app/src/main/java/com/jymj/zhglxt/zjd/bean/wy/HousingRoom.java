package com.jymj.zhglxt.zjd.bean.wy;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/5/27.房源内房屋信息
 */
public class HousingRoom implements Serializable {
    private Long id;
    private Long housId;//housing----id
    private String bh;//房间编号
    private Integer repair;//修缮情况   1 未修缮   2 已修缮
    private Integer isrent;//是否出租  1 未出租  2 已出租

    private List<HousingRoomFile> housingRoomFiles;

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

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public Integer getRepair() {
        return repair;
    }

    public void setRepair(Integer repair) {
        this.repair = repair;
    }

    public Integer getIsrent() {
        return isrent == null?1:isrent;
    }

    public void setIsrent(Integer isrent) {
        this.isrent = isrent;
    }

    public List<HousingRoomFile> getHousingRoomFiles() {
        if (housingRoomFiles == null){
            housingRoomFiles = new ArrayList<>();
        }
        return housingRoomFiles;
    }

    public void setHousingRoomFiles(List<HousingRoomFile> housingRoomFiles) {
        this.housingRoomFiles = housingRoomFiles;
    }
}
