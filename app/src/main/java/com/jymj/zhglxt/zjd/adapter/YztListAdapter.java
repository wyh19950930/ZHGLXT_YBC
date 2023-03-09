package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.AnalysisEnty;

import java.util.List;

public class YztListAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<AnalysisEnty> analysisEntyList;
    private OnClickLister onClickLister;

    public YztListAdapter(Context context, List<AnalysisEnty> analysisEntyList) {
        this.context = context;
        this.analysisEntyList = analysisEntyList;
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_yzt_list, viewGroup, false);

        return new YztListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        YztListHolder yztListHolder = (YztListHolder) viewHolder;
        yztListHolder.tvYztListXh.setText((i+1)+"");
        yztListHolder.tvYztListXmm.setText(analysisEntyList.get(i).getCreateTime());
        yztListHolder.tvYztListDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onDeleteClick(analysisEntyList.get(i));
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onClick(analysisEntyList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return analysisEntyList.size();
    }
    public class YztListHolder extends RecyclerView.ViewHolder {

        private final TextView tvYztListXh;
        private final TextView tvYztListXmm;
        private final TextView tvYztListDelete;

        public YztListHolder(@NonNull View itemView) {
            super(itemView);
            tvYztListXh = itemView.findViewById(R.id.tv_yzt_list_xh);
            tvYztListXmm = itemView.findViewById(R.id.tv_yzt_list_xmm);
            tvYztListDelete = itemView.findViewById(R.id.tv_yzt_list_delete);


        }
    }
    public interface OnClickLister{
        void onClick(AnalysisEnty analysisEnty);
        void onDeleteClick(AnalysisEnty analysisEnty);
    }
}
