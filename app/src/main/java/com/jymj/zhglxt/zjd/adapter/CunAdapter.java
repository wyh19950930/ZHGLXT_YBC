package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity;
import com.jymj.zhglxt.util.WordUtil;

import java.util.ArrayList;

public class CunAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SysXzqEntity> datasource; //源数据
    private ArrayList<SysXzqEntity> showdata;//显示的数据（符合搜索条件的数据）
    private OnCunLister onCunLister;
    public CunAdapter(Context context){
        this.context = context;
    }

    public void setOnCunLister(OnCunLister onCunLister) {
        this.onCunLister = onCunLister;
    }

    @Override
    public int getCount() {
        if(showdata == null){
            return 0;
        }
        return showdata.size();
    }
    @Override
    public Object getItem(int arg0) {
        if(showdata != null){
            return showdata.get(arg0);
        }
        return null;
    }
    @Override
    public long getItemId(int arg0) {
        return 0;
    }
    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        ItemViewCache cache = null;
        if(view == null){
            cache = new ItemViewCache();
            view = LayoutInflater.from(context).inflate(R.layout.item_cun, null);
            cache.name = (TextView)view.findViewById(R.id.tv_item_cun_name);
            view.setTag(cache);
        }else{
            cache = (ItemViewCache)view.getTag();
        }
        cache.name.setText(showdata.get(position).getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCunLister.onClick(showdata.get(position),position);
            }
        });
        return view;
    }
    //缓存类，重用以达到优化列表的效果
    public class ItemViewCache{
        public TextView name;
        public TextView number;
    }
    public void setDataSource(ArrayList<SysXzqEntity> datasource){
        this.datasource = datasource;
        showdata = (ArrayList<SysXzqEntity>)datasource.clone();
        this.notifyDataSetInvalidated();
    }
    //对源数据进行搜索
    public void searchData(String word){
        if(word == null || word == ""){
            this.showdata = (ArrayList<SysXzqEntity>)this.datasource.clone();
        }else{
            dataFilter(word);
        }
        this.notifyDataSetInvalidated();
    }

    private void dataFilter(String word){
        showdata.clear();
        int listsize = datasource.size();
        for(int i = 0;i < listsize; i++){
            SysXzqEntity user = datasource.get(i);
            if(user.getName().contains(word)){
                showdata.add(user);
            }else if(WordUtil.getSpells(user.getName()).contains(word.toLowerCase())){
                showdata.add(user);
            }
        }
    }
    public interface OnCunLister{
        void onClick(SysXzqEntity dataBean, int point);
    }
}
