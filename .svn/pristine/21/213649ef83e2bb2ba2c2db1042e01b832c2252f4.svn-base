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
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameYLMXBean;

import java.util.List;

public class FrameYlmxAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FrameYLMXBean.DataBean.YlEntityListBean> mList;
    private OnClickLister onClickLister;

    public FrameYlmxAdapter(Context context, List<FrameYLMXBean.DataBean.YlEntityListBean> mList) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_frame_ylmx, viewGroup, false);

        return new YztListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        YztListHolder yztListHolder = (YztListHolder) viewHolder;
        yztListHolder.item_frame_ylmx_bh.setText(mList.get(i).getBh());
        yztListHolder.item_frame_ylmx_cm.setText(mList.get(i).getXzqmc());
        yztListHolder.item_frame_ylmx_address.setText(mList.get(i).getMph());
        yztListHolder.item_frame_ylmx_hs.setText(mList.get(i).getHucount()+"");
        yztListHolder.item_frame_ylmx_zdmj.setText(mList.get(i).getArea()+"");
        yztListHolder.item_frame_ylmx_jzmj.setText(mList.get(i).getJianzhuArea()+"");
        yztListHolder.item_frame_ylmx_rk.setText(mList.get(i).getRk()+"");
        yztListHolder.item_frame_ylmx_nong.setText(mList.get(i).getNongcount()+"");
        yztListHolder.item_frame_ylmx_wlrk.setText(mList.get(i).getLdrks()+"");
        yztListHolder.item_frame_ylmx_fnong.setText(mList.get(i).getFeinongcount()+"");
        yztListHolder.item_frame_ylmx_isxz.setText(mList.get(i).getIsidleText());

        /*yztListHolder.item_frame_ylmx_isxz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onDeleteClick(mList.get(i));
            }
        });*/
        yztListHolder.item_frame_ylmx_top.setOnClickListener(new View.OnClickListener() {
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
        private final LinearLayout item_frame_ylmx_top;
        private final TextView item_frame_ylmx_bh;
        private final TextView item_frame_ylmx_cm;
        private final TextView item_frame_ylmx_address;
        private final TextView item_frame_ylmx_hs;
        private final TextView item_frame_ylmx_zdmj;
        private final TextView item_frame_ylmx_jzmj;
        private final TextView item_frame_ylmx_rk;
        private final TextView item_frame_ylmx_nong;
        private final TextView item_frame_ylmx_fnong;
        private final TextView item_frame_ylmx_wlrk;
        private final TextView item_frame_ylmx_isxz;


        public YztListHolder(@NonNull View itemView) {
            super(itemView);
            item_frame_ylmx_top = itemView.findViewById(R.id.item_frame_ylmx_top);
            item_frame_ylmx_bh = itemView.findViewById(R.id.item_frame_ylmx_bh);
            item_frame_ylmx_cm = itemView.findViewById(R.id.item_frame_ylmx_cm);
            item_frame_ylmx_address = itemView.findViewById(R.id.item_frame_ylmx_address);
            item_frame_ylmx_hs = itemView.findViewById(R.id.item_frame_ylmx_hs);
            item_frame_ylmx_zdmj = itemView.findViewById(R.id.item_frame_ylmx_zdmj);
            item_frame_ylmx_jzmj = itemView.findViewById(R.id.item_frame_ylmx_jzmj);
            item_frame_ylmx_rk = itemView.findViewById(R.id.item_frame_ylmx_rk);
            item_frame_ylmx_nong = itemView.findViewById(R.id.item_frame_ylmx_nong);
            item_frame_ylmx_fnong = itemView.findViewById(R.id.item_frame_ylmx_fnong);
            item_frame_ylmx_wlrk = itemView.findViewById(R.id.item_frame_ylmx_wlrk);
            item_frame_ylmx_isxz = itemView.findViewById(R.id.item_frame_ylmx_isxz);

        }
    }
    public interface OnClickLister{
        void onClick(FrameYLMXBean.DataBean.YlEntityListBean analysisEnty);

    }
}
