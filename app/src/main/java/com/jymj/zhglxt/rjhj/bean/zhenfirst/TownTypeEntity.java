package com.jymj.zhglxt.rjhj.bean.zhenfirst;

import java.math.BigDecimal;

/**
 * Created by ${lc} on 2022/1/24.
 */
public class TownTypeEntity {
    private int ljwt;//垃圾问题数
    private int wswt;//污水问题数
    private int gcwt;//公厕问题数
    private int crwt;//村容问题数

    private int ljwtzgs;//垃圾问题整改
    private int wswtzgs;//污水问题整改
    private int gcwtzgs;//公厕问题整改
    private int crwtzgs;//村容问题整改

    private BigDecimal ljwtzgl;//垃圾问题整改率
    private BigDecimal wswtzgl;//污水问题整改率
    private BigDecimal gcwtzgl;//公厕问题整改率
    private BigDecimal crwtzgl;//村容问题整改率

    private String optimalljwt;//垃圾问题最优村
    private String optimalwswt;//污水问题最优村
    private String optimalgcwt;//公厕问题最优村
    private String optimalcrwt;//村容问题最优村

    private BigDecimal optimalljwtzgl;//垃圾问题最优村整改率
    private BigDecimal optimalwswtzgl;//污水问题最优村整改率
    private BigDecimal optimalgcwtzgl;//公厕问题最优村整改率
    private BigDecimal optimalcrwtzgl;//村容问题最优村整改率

    private String differenceljwt;//垃圾问题最差村
    private String differencewswt;//污水问题最差村
    private String differencegcwt;//公厕问题最差村
    private String differencecrwt;//村容问题最差村

    private BigDecimal differenceljwtzgl;//垃圾问题最差村整改率
    private BigDecimal differencewswtzgl;//污水问题最差村整改率
    private BigDecimal differencegcwtzgl;//公厕问题最差村整改率
    private BigDecimal differencecrwtzgl;//村容问题最差村整改率

    public int getLjwt() {
        return ljwt;
    }

    public void setLjwt(int ljwt) {
        this.ljwt = ljwt;
    }

    public int getWswt() {
        return wswt;
    }

    public void setWswt(int wswt) {
        this.wswt = wswt;
    }

    public int getGcwt() {
        return gcwt;
    }

    public void setGcwt(int gcwt) {
        this.gcwt = gcwt;
    }

    public int getCrwt() {
        return crwt;
    }

    public void setCrwt(int crwt) {
        this.crwt = crwt;
    }

    public int getLjwtzgs() {
        return ljwtzgs;
    }

    public void setLjwtzgs(int ljwtzgs) {
        this.ljwtzgs = ljwtzgs;
    }

    public int getWswtzgs() {
        return wswtzgs;
    }

    public void setWswtzgs(int wswtzgs) {
        this.wswtzgs = wswtzgs;
    }

    public int getGcwtzgs() {
        return gcwtzgs;
    }

    public void setGcwtzgs(int gcwtzgs) {
        this.gcwtzgs = gcwtzgs;
    }

    public int getCrwtzgs() {
        return crwtzgs;
    }

    public void setCrwtzgs(int crwtzgs) {
        this.crwtzgs = crwtzgs;
    }

    public BigDecimal getLjwtzgl() {
        return ljwtzgl==null? BigDecimal.ZERO:ljwtzgl;
    }

    public void setLjwtzgl(BigDecimal ljwtzgl) {
        this.ljwtzgl = ljwtzgl;
    }

    public BigDecimal getWswtzgl() {
        return wswtzgl==null? BigDecimal.ZERO:wswtzgl;
    }

    public void setWswtzgl(BigDecimal wswtzgl) {
        this.wswtzgl = wswtzgl;
    }

    public BigDecimal getGcwtzgl() {
        return gcwtzgl==null? BigDecimal.ZERO:gcwtzgl;
    }

    public void setGcwtzgl(BigDecimal gcwtzgl) {
        this.gcwtzgl = gcwtzgl;
    }

    public BigDecimal getCrwtzgl() {
        return crwtzgl==null? BigDecimal.ZERO:crwtzgl;
    }

    public void setCrwtzgl(BigDecimal crwtzgl) {
        this.crwtzgl = crwtzgl;
    }

    public String getOptimalljwt() {
        return optimalljwt==null?"":optimalljwt;
    }

    public void setOptimalljwt(String optimalljwt) {
        this.optimalljwt = optimalljwt;
    }

    public String getOptimalwswt() {
        return optimalwswt==null?"":optimalwswt;
    }

    public void setOptimalwswt(String optimalwswt) {
        this.optimalwswt = optimalwswt;
    }

    public String getOptimalgcwt() {
        return optimalgcwt==null?"":optimalgcwt;
    }

    public void setOptimalgcwt(String optimalgcwt) {
        this.optimalgcwt = optimalgcwt;
    }

    public String getOptimalcrwt() {
        return optimalcrwt==null?"":optimalcrwt;
    }

    public void setOptimalcrwt(String optimalcrwt) {
        this.optimalcrwt = optimalcrwt;
    }

    public BigDecimal getOptimalljwtzgl() {
        return optimalljwtzgl==null? BigDecimal.ZERO:optimalljwtzgl;
    }

    public void setOptimalljwtzgl(BigDecimal optimalljwtzgl) {
        this.optimalljwtzgl = optimalljwtzgl;
    }

    public BigDecimal getOptimalwswtzgl() {
        return optimalwswtzgl==null? BigDecimal.ZERO:optimalwswtzgl;
    }

    public void setOptimalwswtzgl(BigDecimal optimalwswtzgl) {
        this.optimalwswtzgl = optimalwswtzgl;
    }

    public BigDecimal getOptimalgcwtzgl() {
        return optimalgcwtzgl==null? BigDecimal.ZERO:optimalgcwtzgl;
    }

    public void setOptimalgcwtzgl(BigDecimal optimalgcwtzgl) {
        this.optimalgcwtzgl = optimalgcwtzgl;
    }

    public BigDecimal getOptimalcrwtzgl() {
        return optimalcrwtzgl==null? BigDecimal.ZERO:optimalcrwtzgl;
    }

    public void setOptimalcrwtzgl(BigDecimal optimalcrwtzgl) {
        this.optimalcrwtzgl = optimalcrwtzgl;
    }

    public String getDifferenceljwt() {
        return differenceljwt==null?"":differenceljwt;
    }

    public void setDifferenceljwt(String differenceljwt) {
        this.differenceljwt = differenceljwt;
    }

    public String getDifferencewswt() {
        return differencewswt==null?"":differencewswt;
    }

    public void setDifferencewswt(String differencewswt) {
        this.differencewswt = differencewswt;
    }

    public String getDifferencegcwt() {
        return differencegcwt==null?"":differencegcwt;
    }

    public void setDifferencegcwt(String differencegcwt) {
        this.differencegcwt = differencegcwt;
    }

    public String getDifferencecrwt() {
        return differencecrwt==null?"":differencecrwt;
    }

    public void setDifferencecrwt(String differencecrwt) {
        this.differencecrwt = differencecrwt;
    }

    public BigDecimal getDifferenceljwtzgl() {
        return differenceljwtzgl==null? BigDecimal.ZERO:differenceljwtzgl;
    }

    public void setDifferenceljwtzgl(BigDecimal differenceljwtzgl) {
        this.differenceljwtzgl = differenceljwtzgl;
    }

    public BigDecimal getDifferencewswtzgl() {
        return differencewswtzgl==null? BigDecimal.ZERO:differencewswtzgl;
    }

    public void setDifferencewswtzgl(BigDecimal differencewswtzgl) {
        this.differencewswtzgl = differencewswtzgl;
    }

    public BigDecimal getDifferencegcwtzgl() {
        return differencegcwtzgl==null? BigDecimal.ZERO:differencegcwtzgl;
    }

    public void setDifferencegcwtzgl(BigDecimal differencegcwtzgl) {
        this.differencegcwtzgl = differencegcwtzgl;
    }

    public BigDecimal getDifferencecrwtzgl() {
        return differencecrwtzgl==null? BigDecimal.ZERO:differencecrwtzgl;
    }

    public void setDifferencecrwtzgl(BigDecimal differencecrwtzgl) {
        this.differencecrwtzgl = differencecrwtzgl;
    }
}
