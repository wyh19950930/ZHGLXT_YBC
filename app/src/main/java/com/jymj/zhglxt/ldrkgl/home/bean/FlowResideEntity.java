package com.jymj.zhglxt.ldrkgl.home.bean;

import java.io.Serializable;

/**
 * Created by ${lc} on 2022/2/17.
 */
public class FlowResideEntity implements Serializable {
    private static final long serialVersionUID = -9088323590939792264L;
    private Long id;
    private Long ylId;//	yl---objectid
    private Long flowId;//	flow_info---id
    private String lkyjqDate;//	离开原籍日期起
    private String lkyjzDate;//	离开原籍日期止
    private String ljqDate;//	来京日期起
    private String ljzDate;//	来京日期止
    private String resideqDate;//	来现居住地日期起
    private String residezDate;//	来现居住地日期止
    private Integer reside;//	居住类型
    private String djbvh;//	登记表序号
    private Integer ljcause;//	来京原因
    private Integer verify;//	是否待核实
    private Integer workstatus;//	目前就业状况
    private Integer motor;//	机动车
    private Integer electriccar;//	电动车
    private String createDate;//	创建时间
    private String updateDate;//	修改时间
    private Integer inflows;//	1 流入  2 流出

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getLkyjqDate() {
        return lkyjqDate==null?"2000-01-01":lkyjqDate;
    }

    public void setLkyjqDate(String lkyjqDate) {
        this.lkyjqDate = lkyjqDate;
    }

    public String getLkyjzDate() {
        return lkyjzDate==null?"2000-01-01":lkyjzDate;
    }

    public void setLkyjzDate(String lkyjzDate) {
        this.lkyjzDate = lkyjzDate;
    }

    public String getLjqDate() {
        return ljqDate==null?"2000-01-01":ljqDate;
    }

    public void setLjqDate(String ljqDate) {
        this.ljqDate = ljqDate;
    }

    public String getLjzDate() {
        return ljzDate==null?"2000-01-01":ljzDate;
    }

    public void setLjzDate(String ljzDate) {
        this.ljzDate = ljzDate;
    }

    public String getResideqDate() {
        return resideqDate==null?"2000-01-01":resideqDate;
    }

    public void setResideqDate(String resideqDate) {
        this.resideqDate = resideqDate;
    }

    public String getResidezDate() {
        return residezDate==null?"2000-01-01":residezDate;
    }

    public void setResidezDate(String residezDate) {
        this.residezDate = residezDate;
    }

    public Integer getReside() {
        return reside == null?0:reside;
    }

    public void setReside(Integer reside) {
        this.reside = reside;
    }

    public String getDjbvh() {
        return djbvh==null?"":djbvh;
    }

    public void setDjbvh(String djbvh) {
        this.djbvh = djbvh;
    }

    public Integer getLjcause() {
        return ljcause == null?0:ljcause;
    }

    public void setLjcause(Integer ljcause) {
        this.ljcause = ljcause;
    }

    public Integer getVerify() {
        return verify == null?0:verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Integer getWorkstatus() {
        return workstatus == null?0:workstatus;
    }

    public void setWorkstatus(Integer workstatus) {
        this.workstatus = workstatus;
    }

    public Integer getMotor() {
        return motor == null?0:motor;
    }

    public void setMotor(Integer motor) {
        this.motor = motor;
    }

    public Integer getElectriccar() {
        return electriccar == null?0:electriccar;
    }

    public void setElectriccar(Integer electriccar) {
        this.electriccar = electriccar;
    }

    public String getCreateDate() {
        return createDate==null?"2000-01-01":createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate==null?"2000-01-01":updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getInflows() {
        return inflows;
    }

    public void setInflows(Integer inflows) {
        this.inflows = inflows;
    }
}
