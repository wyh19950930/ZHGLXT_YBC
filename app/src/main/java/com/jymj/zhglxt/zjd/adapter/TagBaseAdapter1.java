package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean;

import java.util.List;

public class TagBaseAdapter1 extends BaseAdapter {

    private Context mContext;
    private List<JsjbBean> mList;//JsjbBean
    private OnXzqClickLinear onXzqClickLinear;

    public TagBaseAdapter1(Context context, List<JsjbBean> list) {
        mContext = context;
        mList = list;
    }
    public void setOnXzqClickLinear(OnXzqClickLinear onXzqClickLinear) {
        this.onXzqClickLinear = onXzqClickLinear;
    }
    public void setmList(List<JsjbBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public JsjbBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//onXzqClickLinear
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_flow, null);
            holder = new ViewHolder();
            holder.tagBtn = (TextView) convertView.findViewById(R.id.item_flow_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String text = getItem(position).getText();
        holder.tagBtn.setText(text);
        Boolean state = getItem(position).isCheck();
        if (state) {//bg_flow_normal
//            onXzqClickLinear.onClick(getItem(position).get_id(),position,true);
            holder.tagBtn.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bt_actiive_01ba76));
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
//            onXzqClickLinear.onClick(getItem(position).get_id(),position,false);
            holder.tagBtn.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bt_actiive_cccccc));
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.light_gray));//flow_color  gray
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tagBtn;
    }

    public interface OnXzqClickLinear{
        void onClick(int code, int position, Boolean isCheck);
    }
}
