package com.setsuna.common.basebean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by setsuna
 * on 2016.09.9:47
 */
public class BaseMessageRespose<T> implements Serializable {
    public int code;
    public String msg;

    public T proj;

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", proj=" + proj +
                '}';
    }
}
