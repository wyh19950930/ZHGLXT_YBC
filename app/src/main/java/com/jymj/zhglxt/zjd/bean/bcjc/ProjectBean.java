package com.jymj.zhglxt.zjd.bean.bcjc;

public class ProjectBean {

    public String name;
    public String project;
    public String money;
    public String year;
    public String month;
    public String day;
    public String beizhu;

    public ProjectBean(String name, String project, String money, String year, String month, String day, String beizhu) {
        this.name = name;
        this.project = project;
        this.money = money;
        this.year = year;
        this.month = month;
        this.day = day;
        this.beizhu = beizhu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
