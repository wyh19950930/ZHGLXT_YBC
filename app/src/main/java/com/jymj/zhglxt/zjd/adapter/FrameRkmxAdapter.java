package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameRKMXBean;

import java.util.List;

public class FrameRkmxAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FrameRKMXBean.DataBean.ZhaiListBean> mList;
    private OnClickLister onClickLister;

    public FrameRkmxAdapter(Context context, List<FrameRKMXBean.DataBean.ZhaiListBean> mList) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_frame_rkmx, viewGroup, false);

        return new YztListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        YztListHolder yztListHolder = (YztListHolder) viewHolder;
        yztListHolder.item_frame_rkmx_cm.setText(mList.get(i).getXzqmc());
        yztListHolder.item_frame_rkmx_xm.setText(mList.get(i).getHousecount());
        yztListHolder.item_frame_rkmx_sex.setText(mList.get(i).getSexText());
        yztListHolder.item_frame_rkmx_age.setText(mList.get(i).getAge()+"");
        yztListHolder.item_frame_rkmx_hb.setText(mList.get(i).getHuTypeText());
        yztListHolder.item_frame_rkmx_shgx.setText(mList.get(i).getSocialrelatText());//socialrelatText
        yztListHolder.item_frame_rkmx_address.setText(mList.get(i).getHjdz());

        /*yztListHolder.item_frame_rkmx_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onDeleteClick(mList.get(i));
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onClick(mList.get(i));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class YztListHolder extends RecyclerView.ViewHolder {

        private final TextView item_frame_rkmx_cm;
        private final TextView item_frame_rkmx_xm;
        private final TextView item_frame_rkmx_sex;
        private final TextView item_frame_rkmx_age;
        private final TextView item_frame_rkmx_hb;
        private final TextView item_frame_rkmx_shgx;
        private final TextView item_frame_rkmx_address;

        public YztListHolder(@NonNull View itemView) {
            super(itemView);
            item_frame_rkmx_cm = itemView.findViewById(R.id.item_frame_rkmx_cm);
            item_frame_rkmx_xm = itemView.findViewById(R.id.item_frame_rkmx_xm);
            item_frame_rkmx_sex = itemView.findViewById(R.id.item_frame_rkmx_sex);
            item_frame_rkmx_age = itemView.findViewById(R.id.item_frame_rkmx_age);
            item_frame_rkmx_hb = itemView.findViewById(R.id.item_frame_rkmx_hb);
            item_frame_rkmx_shgx = itemView.findViewById(R.id.item_frame_rkmx_shgx);
            item_frame_rkmx_address = itemView.findViewById(R.id.item_frame_rkmx_address);


        }
    }
    public interface OnClickLister{
        void onClick(FrameRKMXBean.DataBean.ZhaiListBean analysisEnty);
        void onDeleteClick(FrameRKMXBean.DataBean.ZhaiListBean analysisEnty);
    }
}
