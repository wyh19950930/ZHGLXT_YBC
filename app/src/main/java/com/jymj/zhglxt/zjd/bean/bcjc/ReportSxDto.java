package com.jymj.zhglxt.zjd.bean.bcjc;

/**
 * Created by lc on 2022/10/24.
 */
public class ReportSxDto {
    private String sxname;
    private Integer sxtj;
    private String sxcounts;

    public ReportSxDto(String sxname, Integer sxtj, String sxcounts) {
        this.sxname = sxname;
        this.sxtj = sxtj;
        this.sxcounts = sxcounts;
    }

    public String getSxname() {
        return sxname;
    }

    public void setSxname(String sxname) {
        this.sxname = sxname;
    }

    public Integer getSxtj() {
        return sxtj;
    }

    public void setSxtj(Integer sxtj) {
        this.sxtj = sxtj;
    }

    public String getSxcounts() {
        return sxcounts;
    }

    public void setSxcounts(String sxcounts) {
        this.sxcounts = sxcounts;
    }
}
