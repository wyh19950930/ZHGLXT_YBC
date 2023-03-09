package com.jymj.zhglxt.ldrkgl.home.bean;

public class LegendBean {
    private String name;
    private float count;
    private int color;

    public LegendBean(String name,int count,int color) {
        this.name = name;
        this.count = count;
        this.color = color;
    }
    public LegendBean(String name,float count,int color) {
        this.name = name;
        this.count = count;
        this.color = color;
    }

    public LegendBean(int count) {
        this.count = count;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
