package com.jymj.zhglxt.rjhj.bean.yl;



import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dl on 2019/9/25.
 */
public class IllegalEntity implements Serializable {
    private Integer id;//	int4	32	0	0	nextval('illegal_id_seq'::regclass)				0	-1
    private String xzqmc;//	varchar	50	0	-1		行政区名称	pg_catalog	default	0	0
    private Integer ylId;//	int4	32	0	-1		yl---objectid			0	0
    private String situation;//	varchar	255	0	-1		违法描述	pg_catalog	default	0	0
    private String entime;//	date	0	0	-1	now()	录入时间			0	0
    private Integer status;//	int2	16	0	-1	0	违法情况 0未解决 10已解决			0	0
    private String overtime;//	date	0	0	-1		解决时间			0	0
    private String enpsn;//	varchar	50	0	-1		录入人	pg_catalog	default	0	0
    private String remark;//	varchar	255	0	-1		备注	pg_catalog	default	0	0
    private String hzmc;//户主名称
    private String bh;//编号
    private String geom;
    private String statusName;
    private Integer[] ids;//图片id集合
    private List<IllegalFileEntity> illegalFileEntityList;
    private YLEntity ylEntity;
    private String center;
    private String location;//位置（定位）---用于土地巡查
    private Integer type; //1 宅基地  2  土地巡查
    private BplxEntity bplxEntity;

    public BplxEntity getBplxEntity() {
        return bplxEntity;
    }

    public void setBplxEntity(BplxEntity bplxEntity) {
        this.bplxEntity = bplxEntity;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public YLEntity getYlEntity() {
        return ylEntity==null?new YLEntity():ylEntity;
    }

    public void setYlEntity(YLEntity ylEntity) {
        this.ylEntity = ylEntity;
    }

    public List<IllegalFileEntity> getIllegalFileEntityList() {
        return illegalFileEntityList==null?new ArrayList<>():illegalFileEntityList;
    }

    public void setIllegalFileEntityList(List<IllegalFileEntity> illegalFileEntityList) {
        this.illegalFileEntityList = illegalFileEntityList;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getGeom() {
        return geom==null?"":geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getHzmc() {
        return hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public Integer getYlId() {
        return ylId;
    }

    public void setYlId(Integer ylId) {
        this.ylId = ylId;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getEntime() {
        return entime;
    }

    public void setEntime(String entime) {
        this.entime = entime;
    }
    public int getStatusInt() {
        if (status==0){
            return 0;
        }else  if (status==10){
            return 1;
        }
        return 0;
    }
    public String getStatus() {
        if (status==0){
            return "未完成";
        }else  if (status==10){
            return "已完成";
        }
        return "";
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getEnpsn() {
        return enpsn;
    }

    public void setEnpsn(String enpsn) {
        this.enpsn = enpsn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
