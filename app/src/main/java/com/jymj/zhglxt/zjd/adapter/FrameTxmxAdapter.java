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
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameTXMXBean;

import java.text.DecimalFormat;
import java.util.List;

public class FrameTxmxAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FrameTXMXBean.DataBean.ListBean> mList;
    private OnClickLister onClickLister;

    public FrameTxmxAdapter(Context context, List<FrameTXMXBean.DataBean.ListBean> mList) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_frame_txmx, viewGroup, false);

        return new YztListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        YztListHolder yztListHolder = (YztListHolder) viewHolder;
        DecimalFormat df =  new DecimalFormat("#.00");
        yztListHolder.item_frame_txmx_cm.setText(mList.get(i).getXzqmc());
        yztListHolder.item_frame_txmx_dlmc.setText(mList.get(i).getDlmc());
        yztListHolder.item_frame_txmx_dlbm.setText(mList.get(i).getDlbm());
        if (mList.get(i).getArea1() > 10000.0) {
            yztListHolder.item_frame_txmx_area.setText(df.format((mList.get(i).getArea1() / 10000.0)).toString() + "万㎡");
        } else {
            yztListHolder.item_frame_txmx_area.setText(mList.get(i).getArea1() + "㎡");
        }

        /*yztListHolder.item_frame_txmx_ejfl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onDeleteClick(mList.get(i));
            }
        });*/
        yztListHolder.item_frame_txmx_top.setOnClickListener(new View.OnClickListener() {
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

        private final LinearLayout item_frame_txmx_top;
        private final TextView item_frame_txmx_cm;
        private final TextView item_frame_txmx_dlmc;
        private final TextView item_frame_txmx_dlbm;
        private final TextView item_frame_txmx_area;

        public YztListHolder(@NonNull View itemView) {
            super(itemView);
            item_frame_txmx_top = itemView.findViewById(R.id.item_frame_txmx_top);
            item_frame_txmx_cm = itemView.findViewById(R.id.item_frame_txmx_cm);
            item_frame_txmx_dlmc = itemView.findViewById(R.id.item_frame_txmx_dlmc);
            item_frame_txmx_dlbm = itemView.findViewById(R.id.item_frame_txmx_dlbm);
            item_frame_txmx_area = itemView.findViewById(R.id.item_frame_txmx_area);
        }
    }
    public interface OnClickLister{
        void onClick(FrameTXMXBean.DataBean.ListBean analysisEnty);
    }
}
