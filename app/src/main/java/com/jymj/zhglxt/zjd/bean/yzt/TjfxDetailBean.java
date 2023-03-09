package com.jymj.zhglxt.zjd.bean.yzt;

import com.jymj.zhglxt.zjd.bean.yzt.cj.CJEntity;

import java.util.ArrayList;
import java.util.List;

public class TjfxDetailBean {

    public TdlyCountVo tdly2018;//土地利用2018
    public TdlyCountVo tdly2009;//土地利用2009
    public TdlyCountVo tdly2020;//土地利用2020
    public List<TjfxStBean> gh;//规划 2  name area
    public List<TjfxStBean> qs;//权属 2
    public List<TjfxStBean> feiZhai;//非宅 3 counts area jzmj
    public List<TjfxStBean> zhai;//宅 3
    public List<TjfxStBean> lh;//绿化 2
    public List<TjfxStBean> skt;//三块田 2
    public TjfxStBean cctt;//拆除腾退 3
    public CJEntity cjEntity;//村界

    public TdlyCountVo getTdly2018() {
        return tdly2018;
    }

    public void setTdly2018(TdlyCountVo tdly2018) {
        this.tdly2018 = tdly2018;
    }

    public TdlyCountVo getTdly2009() {
        return tdly2009;
    }

    public void setTdly2009(TdlyCountVo tdly2009) {
        this.tdly2009 = tdly2009;
    }

    public TdlyCountVo getTdly2020() {
        return tdly2020;
    }

    public void setTdly2020(TdlyCountVo tdly2020) {
        this.tdly2020 = tdly2020;
    }

    public List<TjfxStBean> getGh() {
        return gh==null?new ArrayList<>():gh;
    }

    public void setGh(List<TjfxStBean> gh) {
        this.gh = gh;
    }

    public List<TjfxStBean> getQs() {
        return qs==null?new ArrayList<>():qs;
    }

    public void setQs(List<TjfxStBean> qs) {
        this.qs = qs;
    }

    public List<TjfxStBean> getFeiZhai() {
        return feiZhai==null?new ArrayList<>():feiZhai;
    }

    public void setFeiZhai(List<TjfxStBean> feiZhai) {
        this.feiZhai = feiZhai;
    }

    public List<TjfxStBean> getZhai() {
        return zhai==null?new ArrayList<>():zhai;
    }

    public void setZhai(List<TjfxStBean> zhai) {
        this.zhai = zhai;
    }

    public List<TjfxStBean> getLh() {
        return lh==null?new ArrayList<>():lh;
    }

    public void setLh(List<TjfxStBean> lh) {
        this.lh = lh;
    }

    public List<TjfxStBean> getSkt() {
        return skt==null?new ArrayList<>():skt;
    }

    public void setSkt(List<TjfxStBean> skt) {
        this.skt = skt;
    }

    public TjfxStBean getCctt() {
        return cctt;
    }

    public void setCctt(TjfxStBean cctt) {
        this.cctt = cctt;
    }

    public CJEntity getCjEntity() {
        return cjEntity;
    }

    public void setCjEntity(CJEntity cjEntity) {
        this.cjEntity = cjEntity;
    }
}
