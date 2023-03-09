package com.jymj.zhglxt.rjhj.bean;

import java.util.ArrayList;
import java.util.List;

public class BaobiaoBean {
    private String name;//标签名称  行政区名称
    private String code;//行政区Code
    private String center;//行政区Code
    private int index;//索引
    private int groupColor;
    private boolean isCheck=false;//是否选中
    private boolean ishShow=false;//是否展开
    private List<BaobiaoBean> bbList;
    private int num=1;//
    private Integer wcl;
    private Integer zj;
    private boolean isShow=true;

    public BaobiaoBean() {
    }

    public BaobiaoBean(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public BaobiaoBean(String name, int index, boolean isCheck) {
        this.name = name;
        this.index = index;
        this.isCheck = isCheck;
    }

    public BaobiaoBean(String name, String center, String code) {
        this.name = name;
        this.center = center;
        this.code = code;
    }

    public BaobiaoBean(String name, int index, boolean isCheck, boolean ishShow, List<BaobiaoBean> bbList) {
        this.name = name;
        this.index = index;
        this.isCheck = isCheck;
        this.ishShow = ishShow;
        this.bbList = bbList;
    }

    public BaobiaoBean(Integer wcl, Integer zj) {
        this.wcl = wcl;
        this.zj = zj;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getWcl() {
        return wcl==null?0:wcl;
    }

    public void setWcl(int wcl) {
        this.wcl = wcl;
    }

    public int getZj() {
        return zj==null?0:zj;
    }

    public void setZj(int zj) {
        this.zj = zj;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getGroupColor() {
        return groupColor;
    }

    public void setGroupColor(int groupColor) {
        this.groupColor = groupColor;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getChildCount() {
        return bbList.size();
    }

    public BaobiaoBean getChildItem(int index) {
        return bbList.get(index);
    }

    public void toggle() {
        this.isCheck = !this.isCheck;
    }

    public boolean isIshShow() {
        return ishShow;
    }

    public void setIshShow(boolean ishShow) {
        this.ishShow = ishShow;
    }

    public List<BaobiaoBean> getBbList() {
        return bbList==null?new ArrayList<>():bbList;
    }

    public void setBbList(List<BaobiaoBean> bbList) {
        this.bbList = bbList;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
