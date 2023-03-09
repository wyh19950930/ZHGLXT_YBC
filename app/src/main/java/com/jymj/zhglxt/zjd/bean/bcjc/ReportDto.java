package com.jymj.zhglxt.zjd.bean.bcjc;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lc on 2022/10/24.
 */
public class ReportDto{
    private List<String> codeList;//code 集合
    private List<String> years;//年集合
    private List<ReportSxDto> sxDtoList;//属性集合
    private List<String> tableList;//表集合
    private Integer type;
    private BigDecimal gzsl;

    public ReportDto(List<String> codeList, List<String> years, List<ReportSxDto> sxDtoList, List<String> tableList) {
        this.codeList = codeList;
        this.years = years;
        this.sxDtoList = sxDtoList;
        this.tableList = tableList;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public List<ReportSxDto> getSxDtoList() {
        return sxDtoList;
    }

    public void setSxDtoList(List<ReportSxDto> sxDtoList) {
        this.sxDtoList = sxDtoList;
    }

    public List<String> getTableList() {
        return tableList;
    }

    public void setTableList(List<String> tableList) {
        this.tableList = tableList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getGzsl() {
        return gzsl;
    }

    public void setGzsl(BigDecimal gzsl) {
        this.gzsl = gzsl;
    }
}
