package com.jymj.zhglxt.zjd.bean.bcjc.bcqh;

import java.util.Date;

/**
 * Created by lc on 2022/11/7.家庭财政需求情况
 */
public class JtczxqqkEntity {
    private Long id;
    private Long jtjcId;
    private Long jtshbzId;//	bcjtshbzqk----id
    private Integer options;//	选项
    private Integer type;//	类型
    private Integer sorting;//	排序
    private String remark;//	备注
    private String createTime;
    private String updateTime;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJtjcId() {
        return jtjcId;
    }

    public void setJtjcId(Long jtjcId) {
        this.jtjcId = jtjcId;
    }

    public Long getJtshbzId() {
        return jtshbzId;
    }

    public void setJtshbzId(Long jtshbzId) {
        this.jtshbzId = jtshbzId;
    }

    public Integer getOptions() {
        return options;
    }

    public void setOptions(Integer options) {
        this.options = options;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
