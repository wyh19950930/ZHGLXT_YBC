package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.SysXzqEntity;

import java.util.List;

public class MainSpinnerAdapter extends BaseAdapter {
    private List<SysXzqEntity> list;
    private Context context;

    public MainSpinnerAdapter(List<SysXzqEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<SysXzqEntity> getList() {
        return list;
    }

    public void setList(List<SysXzqEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SysXzqEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater= LayoutInflater.from(context);
        convertView=_LayoutInflater.inflate(R.layout.item_spinner, null);
        if(convertView!=null) {
            TextView textView=(TextView)convertView.findViewById(R.id.tv_spinner);
            textView.setText(list.get(position).getName());
            /*if (list.get(position).getParentName().equals("")){
                textView.setText(list.get(position).getName());
            }else {
                textView.setText(list.get(position).getParentName()+"  "+list.get(position).getName());
            }*/

        }
        return convertView;
    }

}
