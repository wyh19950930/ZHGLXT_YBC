package com.setsuna.common.base;

import android.content.Context;

/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
//    public RxManager mRxManager=new RxManager();
    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    }
    public void onDestroy() {
//        mRxManager.clear();
    }
}
