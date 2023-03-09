package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout;
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbBean;
import com.setsuna.common.commonutils.ToastUtils;

import java.util.List;

public class TagBaseAdapter extends BaseAdapter {

    private Context mContext;
    private Boolean isShowDelete = false;
    private List<JsjbBean> mList;//JsjbBean
    private OnXzqClickLinear onXzqClickLinear;

    public TagBaseAdapter(Context context, List<JsjbBean> list) {
        mContext = context;
        mList = list;
    }
    public void setOnXzqClickLinear(OnXzqClickLinear onXzqClickLinear) {
        this.onXzqClickLinear = onXzqClickLinear;
    }
    public void setmList(List<JsjbBean> mList, TagCloudLayout tagCloudLayout) {
        this.mList = mList;
        notifyDataSetChanged();
        if (mList.size()==0){
            tagCloudLayout.setVisibility(View.GONE);
        }else {
            tagCloudLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setShowDelete(Boolean showDelete) {
        isShowDelete = showDelete;
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
            holder.iv_item_flow_delete = (ImageView) convertView.findViewById(R.id.iv_item_flow_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String text = getItem(position).getText();
        holder.tagBtn.setText(text);
        Boolean state = getItem(position).isCheck();
        Boolean isShow = getItem(position).isDeleteShow();
        if (state) {//bg_flow_normal
//            onXzqClickLinear.onClick(getItem(position).get_id(),position,true);
            String color = getItem(position).getColor();
            holder.tagBtn.setBackgroundColor(Color.parseColor(color.replace("#","#30")));
            holder.tagBtn.setTextColor(Color.parseColor(color));
//            holder.tagBtn.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bt_actiive_01ba76));
//            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
//            onXzqClickLinear.onClick(getItem(position).get_id(),position,false);
            /*holder.tagBtn.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bt_actiive_cccccc));
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.light_gray));//flow_color  gray*/
            holder.tagBtn.setBackgroundColor(Color.parseColor("#30999999"));
            holder.tagBtn.setTextColor(Color.parseColor("#999999"));
        }

       /* holder.iv_item_flow_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onXzqClickLinear.onDeleteClick(position);
            }
        });*/
        return convertView;
    }

    static class ViewHolder {
        TextView tagBtn;
        ImageView iv_item_flow_delete;
    }

    public interface OnXzqClickLinear{
//        void onClick(int code, int position, Boolean isCheck);
        void onDeleteClick(int position);
        void onClick(int position);
    }
}
