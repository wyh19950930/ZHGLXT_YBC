package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameFZMXBean;

import java.text.DecimalFormat;
import java.util.List;

public class FrameFzmxAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FrameFZMXBean.DataBean.YlEntityListBean> mList;
    private OnClickLister onClickLister;
    private YztListHolder yztListHolder;

    public FrameFzmxAdapter(Context context, List<FrameFZMXBean.DataBean.YlEntityListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public OnClickLister getOnClickLister() {
        return onClickLister;
    }

    public void setOnClickLister(OnClickLister onClickLister) {
        this.onClickLister = onClickLister;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_frame_fzmx, viewGroup, false);

        return new YztListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        YztListHolder yztListHolder = (YztListHolder) viewHolder;
        DecimalFormat df =  new DecimalFormat("#.00");
        yztListHolder.item_frame_fzmx_cm.setText(mList.get(i).getXzqmc());
        yztListHolder.item_frame_fzmx_hzcm.setText(mList.get(i).getName());//getHzmc
        yztListHolder.item_frame_txmx_hylx.setText(mList.get(i).getYlTypeText());

        if (mList.get(i).getArea() > 10000.0) {
            yztListHolder.item_frame_txmx_zdmj.setText(df.format((mList.get(i).getArea() / 10000.0)).toString() + "万㎡");
        } else {
            yztListHolder.item_frame_txmx_zdmj.setText(mList.get(i).getArea() + "㎡");
        }
        if (mList.get(i).getJianzhuArea() > 10000.0) {
            yztListHolder.item_frame_txmx_jzmj.setText(df.format((mList.get(i).getJianzhuArea() / 10000.0)).toString() + "万㎡");
        } else {
            yztListHolder.item_frame_txmx_jzmj.setText(mList.get(i).getJianzhuArea() + "㎡");
        }

        /*yztListHolder.item_frame_txmx_jzmj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onDeleteClick(mList.get(i));
            }
        });*/
        yztListHolder.item_frame_fzmx_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onClick(mList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class YztListHolder extends RecyclerView.ViewHolder {

        private final LinearLayout item_frame_fzmx_top;
        private final TextView item_frame_fzmx_cm;
        private final TextView item_frame_fzmx_hzcm;
        private final TextView item_frame_txmx_hylx;
        private final TextView item_frame_txmx_zdmj;
        private final TextView item_frame_txmx_jzmj;

        public YztListHolder(@NonNull View itemView) {
            super(itemView);
            item_frame_fzmx_top = itemView.findViewById(R.id.item_frame_fzmx_top);
            item_frame_fzmx_cm = itemView.findViewById(R.id.item_frame_fzmx_cm);
            item_frame_fzmx_hzcm = itemView.findViewById(R.id.item_frame_fzmx_hzcm);
            item_frame_txmx_hylx = itemView.findViewById(R.id.item_frame_txmx_hylx);
            item_frame_txmx_zdmj = itemView.findViewById(R.id.item_frame_txmx_zdmj);
            item_frame_txmx_jzmj = itemView.findViewById(R.id.item_frame_txmx_jzmj);
        }
    }
    public interface OnClickLister{
        void onClick(FrameFZMXBean.DataBean.YlEntityListBean analysisEnty);
    }
}
