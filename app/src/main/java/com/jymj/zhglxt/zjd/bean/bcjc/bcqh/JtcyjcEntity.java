package com.jymj.zhglxt.zjd.bean.bcjc.bcqh;

import com.jymj.zhglxt.widget.zdybj.ZdyTextLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lc on 2022/11/7. 家庭成员监测表
 */
public class JtcyjcEntity {
    private Long id;
    private String zhen;
    private String xzqmc;
    private String code;
    private Long userId;
    private String username;//	填表人
    private String sfname;//	受访姓名
    private String sfphone;//	受访电话
    private String jtdzzhen;//	家庭住址镇
    private String jtdzxzqmc;//	家庭住址村
    private String mph;//	门牌号
    private String years;//	年
    private Integer sorting;//	排序
    private String createTime;
    private String updateTime;
    private Integer process;

    private List<Long> jtcyjbqkids;//删除的家庭成员
    private List<JtcyjbqkEntity> jtcyjbqkEntities;//家庭成员基本情况
    private Jtjyccqk jtjyccqk;//家庭土地经营及财产情况
    private JtsrzcqkEntity jtsrzcqkEntity;//家庭收入支出情况
    private JtshbzqkEntity jtshbzqkEntity;//家庭社会保障与财政需求情况

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZhen() {
        return zhen==null?"": zhen;
    }

    public void setZhen(String zhen) {
        this.zhen = zhen;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username==null?"":username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSfname() {
        return sfname==null?"":sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public String getSfphone() {
        return sfphone==null?"":sfphone;
    }

    public void setSfphone(String sfphone) {
        this.sfphone = sfphone;
    }

    public String getJtdzzhen() {
        return jtdzzhen==null?"":jtdzzhen;
    }

    public void setJtdzzhen(String jtdzzhen) {
        this.jtdzzhen = jtdzzhen;
    }

    public String getJtdzxzqmc() {
        return jtdzxzqmc==null?"":jtdzxzqmc;
    }

    public void setJtdzxzqmc(String jtdzxzqmc) {
        this.jtdzxzqmc = jtdzxzqmc;
    }

    public String getMph() {
        return mph==null?"":mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public String getYears() {
        return years==null?"":years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public String getCreateTime() {
        return createTime==null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime==null?"":updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getProcess() {
        return process == null?1:process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public List<Long> getJtcyjbqkids() {
        if (jtcyjbqkids == null){
            jtcyjbqkids = new ArrayList<Long>();
        }
        return jtcyjbqkids;
    }

    public void setJtcyjbqkids(List<Long> jtcyjbqkids) {
        this.jtcyjbqkids = jtcyjbqkids;
    }

    public List<JtcyjbqkEntity> getJtcyjbqkEntities() {
        if (jtcyjbqkEntities == null){
            jtcyjbqkEntities = new ArrayList<>();
        }
        return jtcyjbqkEntities;
    }

    public void setJtcyjbqkEntities(List<JtcyjbqkEntity> jtcyjbqkEntities) {
        this.jtcyjbqkEntities = jtcyjbqkEntities;
    }

    public Jtjyccqk getJtjyccqk() {
        if (jtjyccqk == null){
            jtjyccqk = new Jtjyccqk();
        }
        return jtjyccqk;
    }

    public void setJtjyccqk(Jtjyccqk jtjyccqk) {
        this.jtjyccqk = jtjyccqk;
    }

    public JtsrzcqkEntity getJtsrzcqkEntity() {
        if (jtsrzcqkEntity == null){
            jtsrzcqkEntity = new JtsrzcqkEntity();
        }
        return jtsrzcqkEntity;
    }

    public void setJtsrzcqkEntity(JtsrzcqkEntity jtsrzcqkEntity) {
        this.jtsrzcqkEntity = jtsrzcqkEntity;
    }

    public JtshbzqkEntity getJtshbzqkEntity() {
        if (jtshbzqkEntity == null){
            jtshbzqkEntity = new JtshbzqkEntity();
        }
        return jtshbzqkEntity;
    }

    public void setJtshbzqkEntity(JtshbzqkEntity jtshbzqkEntity) {
        this.jtshbzqkEntity = jtshbzqkEntity;
    }
}
