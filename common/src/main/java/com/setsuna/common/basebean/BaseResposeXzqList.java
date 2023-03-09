package com.setsuna.common.basebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * des:封装服务器返回数据
 * Created by setsuna
 * on 2016.09.9:47
 */
public class BaseResposeXzqList<T> implements Serializable {
    public Integer code;
    public String msg;

    public List<T> xzqList;

    public Integer getCode() {
        return code==null?-1:code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg==null?"":msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getXzqList() {
        return xzqList==null?new ArrayList<>():xzqList;
    }

    public void setXzqList(List<T> xzqList) {
        this.xzqList = xzqList;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + xzqList +
                '}';
    }
}
