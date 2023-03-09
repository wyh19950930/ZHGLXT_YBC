package com.jymj.zhglxt.zjd.bean.yzt;

import java.math.BigDecimal;

/**
 * Created by lc on 2022/7/1.
 */
public class TdlyCountVo {
    private BigDecimal nydgd;//农用地耕地
    private BigDecimal nydyd;//农用地园地
    private BigDecimal nydld;//农用地林地
    private BigDecimal nydcd;//农用地草地
    private BigDecimal nydjttd;//农用地交通土地
    private BigDecimal nydslyd;//农用地水利及水利设施用地
    private BigDecimal nydqttd;//农用地其他土地
    private BigDecimal jsyd;//建设用地
    private BigDecimal jsydjtyd;//建设用地交通运输用地
    private BigDecimal jsydslyd;//建设用地水利及水利设施用地
    private BigDecimal jsydqttd;//建设用地其他土地
    private BigDecimal jsydsfyd;//建设用地商服用地
    private BigDecimal jsydgkyd;//建设用地工矿用地
    private BigDecimal jsydzzyd;//建设用地住宅用地
    private BigDecimal jsydggyd;//建设用地公共管理与公共服务
    private BigDecimal jsydtsyd;//建设用地特殊用地
    private BigDecimal wlydcd;//未利用地草地
    private BigDecimal wlydslyd;//未利用地水利及水利设施用地
    private BigDecimal wlydqttd;//未利用地其他土地
    private BigDecimal wlydsd;//未利用地湿地

    public BigDecimal getNydgd() {
        return nydgd==null?BigDecimal.ZERO:nydgd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setNydgd(BigDecimal nydgd) {
        this.nydgd = nydgd;
    }

    public BigDecimal getNydyd() {
        return nydyd==null?BigDecimal.ZERO:nydyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setNydyd(BigDecimal nydyd) {
        this.nydyd = nydyd;
    }

    public BigDecimal getNydld() {
        return nydld==null?BigDecimal.ZERO:nydld.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setNydld(BigDecimal nydld) {
        this.nydld = nydld;
    }

    public BigDecimal getNydcd() {
        return nydcd==null?BigDecimal.ZERO:nydcd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setNydcd(BigDecimal nydcd) {
        this.nydcd = nydcd;
    }

    public BigDecimal getNydjttd() {
        return nydjttd==null?BigDecimal.ZERO:nydjttd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setNydjttd(BigDecimal nydjttd) {
        this.nydjttd = nydjttd;
    }

    public BigDecimal getNydslyd() {
        return nydslyd==null?BigDecimal.ZERO:nydslyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setNydslyd(BigDecimal nydslyd) {
        this.nydslyd = nydslyd;
    }

    public BigDecimal getNydqttd() {
        return nydqttd==null?BigDecimal.ZERO:nydqttd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setNydqttd(BigDecimal nydqttd) {
        this.nydqttd = nydqttd;
    }

    public BigDecimal getJsyd() {
        return jsyd==null?BigDecimal.ZERO:jsyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsyd(BigDecimal jsyd) {
        this.jsyd = jsyd;
    }

    public BigDecimal getJsydjtyd() {
        return jsydjtyd==null?BigDecimal.ZERO:jsydjtyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydjtyd(BigDecimal jsydjtyd) {
        this.jsydjtyd = jsydjtyd;
    }

    public BigDecimal getJsydslyd() {
        return jsydslyd==null?BigDecimal.ZERO:jsydslyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydslyd(BigDecimal jsydslyd) {
        this.jsydslyd = jsydslyd;
    }

    public BigDecimal getJsydqttd() {
        return jsydqttd==null?BigDecimal.ZERO:jsydqttd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydqttd(BigDecimal jsydqttd) {
        this.jsydqttd = jsydqttd;
    }

    public BigDecimal getJsydsfyd() {
        return jsydsfyd==null?BigDecimal.ZERO:jsydsfyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydsfyd(BigDecimal jsydsfyd) {
        this.jsydsfyd = jsydsfyd;
    }

    public BigDecimal getJsydgkyd() {
        return jsydgkyd==null?BigDecimal.ZERO:jsydgkyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydgkyd(BigDecimal jsydgkyd) {
        this.jsydgkyd = jsydgkyd;
    }

    public BigDecimal getJsydzzyd() {
        return jsydzzyd==null?BigDecimal.ZERO:jsydzzyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydzzyd(BigDecimal jsydzzyd) {
        this.jsydzzyd = jsydzzyd;
    }

    public BigDecimal getJsydggyd() {
        return jsydggyd==null?BigDecimal.ZERO:jsydggyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydggyd(BigDecimal jsydggyd) {
        this.jsydggyd = jsydggyd;
    }

    public BigDecimal getJsydtsyd() {
        return jsydtsyd==null?BigDecimal.ZERO:jsydtsyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setJsydtsyd(BigDecimal jsydtsyd) {
        this.jsydtsyd = jsydtsyd;
    }

    public BigDecimal getWlydcd() {
        return wlydcd==null?BigDecimal.ZERO:wlydcd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setWlydcd(BigDecimal wlydcd) {
        this.wlydcd = wlydcd;
    }

    public BigDecimal getWlydslyd() {
        return wlydslyd==null?BigDecimal.ZERO:wlydslyd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setWlydslyd(BigDecimal wlydslyd) {
        this.wlydslyd = wlydslyd;
    }

    public BigDecimal getWlydqttd() {
        return wlydqttd==null?BigDecimal.ZERO:wlydqttd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setWlydqttd(BigDecimal wlydqttd) {
        this.wlydqttd = wlydqttd;
    }

    public BigDecimal getWlydsd() {
        return wlydsd==null?BigDecimal.ZERO:wlydsd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setWlydsd(BigDecimal wlydsd) {
        this.wlydsd = wlydsd;
    }
}
