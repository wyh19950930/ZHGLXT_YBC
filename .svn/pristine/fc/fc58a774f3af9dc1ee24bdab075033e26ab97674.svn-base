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
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameTGMXBean;

import java.text.DecimalFormat;
import java.util.List;

public class FrameTgmxAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FrameTGMXBean.DataBean.TgEntityListBean> mList;
    private OnClickLister onClickLister;

    public FrameTgmxAdapter(Context context, List<FrameTGMXBean.DataBean.TgEntityListBean> mList) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_frame_tgmx, viewGroup, false);

        return new YztListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        YztListHolder yztListHolder = (YztListHolder) viewHolder;
        DecimalFormat df =  new DecimalFormat("#.00");
        yztListHolder.item_frame_tgmx_cm.setText(mList.get(i).getXzqmc());
        yztListHolder.item_frame_tgmx_lx.setText(mList.get(i).getLandName());

        /*if (mList.get(i).getArea() > 10000.0) {
            yztListHolder.item_frame_tgmx_zdmj.setText(df.format((mList.get(i).getArea() / 10000.0)).toString() + "万㎡");
        } else {
            yztListHolder.item_frame_tgmx_zdmj.setText(mList.get(i).getArea() + "㎡");
        }*/
        if (mList.get(i).getArea1() > 10000.0) {
            yztListHolder.item_frame_tgmx_zdmj.setText(df.format((mList.get(i).getArea1() / 10000.0)).toString() + "万㎡");
        } else {
            yztListHolder.item_frame_tgmx_zdmj.setText(mList.get(i).getArea1() + "㎡");
        }

        /*yztListHolder.item_frame_tgmx_jzmj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onDeleteClick(mList.get(i));
            }
        });*/
        yztListHolder.item_frame_tgmx_top.setOnClickListener(new View.OnClickListener() {
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

        private final LinearLayout item_frame_tgmx_top;
        private final TextView item_frame_tgmx_cm;
        private final TextView item_frame_tgmx_lx;
        private final TextView item_frame_tgmx_zdmj;
        private final TextView item_frame_tgmx_jzmj;

        public YztListHolder(@NonNull View itemView) {
            super(itemView);
            item_frame_tgmx_top = itemView.findViewById(R.id.item_frame_tgmx_top);
            item_frame_tgmx_cm = itemView.findViewById(R.id.item_frame_tgmx_cm);
            item_frame_tgmx_lx = itemView.findViewById(R.id.item_frame_tgmx_lx);
            item_frame_tgmx_zdmj = itemView.findViewById(R.id.item_frame_tgmx_zdmj);
            item_frame_tgmx_jzmj = itemView.findViewById(R.id.item_frame_tgmx_jzmj);
        }
    }
    public interface OnClickLister{
        void onClick(FrameTGMXBean.DataBean.TgEntityListBean analysisEnty);
    }
}
