package com.jymj.zhglxt.zjd.bean.cygl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/4/28 10:14
 */
public class CyyqZztEntity {
    private KeyValueEntity keyValueEntity;
    private List<EnterpriseVoEntity> enterpriseVoEntities;
    private List<IndustryEntity> industryEntities;
    private List<BusinessEntity> businessEntities;
    private List<RegisteredEntity> registeredEntities;

    public KeyValueEntity getKeyValueEntity() {
        return keyValueEntity;
    }

    public void setKeyValueEntity(KeyValueEntity keyValueEntity) {
        this.keyValueEntity = keyValueEntity;
    }

    public List<EnterpriseVoEntity> getEnterpriseVoEntities() {
        return enterpriseVoEntities==null?new ArrayList<>():enterpriseVoEntities;
    }

    public void setEnterpriseVoEntities(List<EnterpriseVoEntity> enterpriseVoEntities) {
        this.enterpriseVoEntities = enterpriseVoEntities;
    }

    public List<IndustryEntity> getIndustryEntities() {
        return industryEntities==null?new ArrayList<>():industryEntities;
    }

    public void setIndustryEntities(List<IndustryEntity> industryEntities) {
        this.industryEntities = industryEntities;
    }

    public List<BusinessEntity> getBusinessEntities() {
        return businessEntities==null?new ArrayList<>():businessEntities;
    }

    public void setBusinessEntities(List<BusinessEntity> businessEntities) {
        this.businessEntities = businessEntities;
    }

    public List<RegisteredEntity> getRegisteredEntities() {
        return registeredEntities==null?new ArrayList<>():registeredEntities;
    }

    public void setRegisteredEntities(List<RegisteredEntity> registeredEntities) {
        this.registeredEntities = registeredEntities;
    }
}
