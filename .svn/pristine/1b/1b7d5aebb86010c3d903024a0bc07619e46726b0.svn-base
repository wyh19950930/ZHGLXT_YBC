package com.jymj.zhglxt.zjd.adapter.yzt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.yzt.PjProBean;

import java.math.BigDecimal;
import java.util.List;

public class CtglXRlvAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<PjProBean.DataBean.ListBean> mList;
    private OnClickLister onClickLister;
    private OnDetClickLister onDetClickLister;

    public CtglXRlvAdapter(Context context, List<PjProBean.DataBean.ListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public OnClickLister getOnClickLister() {
        return onClickLister;
    }

    public void setOnClickLister(OnClickLister onClickLister) {
        this.onClickLister = onClickLister;
    }

    public OnDetClickLister getOnDetClickLister() {
        return onDetClickLister;
    }

    public void setOnDetClickLister(OnDetClickLister onDetClickLister) {
        this.onDetClickLister = onDetClickLister;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_ctglpq_xm, viewGroup, false);

        return new YztListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        YztListHolder viewholder = (YztListHolder) viewHolder;
        PjProBean.DataBean.ListBean data = mList.get(i);
        viewholder.item_ctglpq_xm_mc.setText(data.getName());
        viewholder.item_ctglpq_xm_cddw.setText(data.getImpUnit());
        //viewholder.setText(R.id.item_ctglpq_xm_gm, data.getProjScale().toString());
        if (data.getProjScale().compareTo(new BigDecimal(10000)) > 0) {
            viewholder.item_ctglpq_xm_gm.setText(data.getProjScale().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡");
        } else {
            viewholder.item_ctglpq_xm_gm.setText(data.getProjScale().toString() + "㎡");
        }

        viewholder.item_ctglpq_xm_ttzt.setText(data.getProjSpeedText());
        if (data.getProjPay()==1){
            viewholder.item_ctglpq_xm_zfqk.setText("已支付");
        }else {
            viewholder.item_ctglpq_xm_zfqk.setText("未支付");
        }
        viewholder.item_ctglpq_xm_zfje.setText(data.getProjAmount().toString());

        viewholder.item_ctglpq_xm_cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onClick(mList.get(i));
            }
        });
        viewholder.item_ctglpq_xm_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetClickLister.onDetClick(mList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class YztListHolder extends RecyclerView.ViewHolder {


        private final TextView item_ctglpq_xm_mc;
        private final TextView item_ctglpq_xm_cddw;
        private final TextView item_ctglpq_xm_gm;
        private final TextView item_ctglpq_xm_ttzt;
        private final TextView item_ctglpq_xm_zfqk;
        private final TextView item_ctglpq_xm_zfje;
        private final TextView item_ctglpq_xm_cz;
        private final TextView item_ctglpq_xm_sc;

        public YztListHolder(@NonNull View itemView) {
            super(itemView);

            item_ctglpq_xm_mc = itemView.findViewById(R.id.item_ctglpq_xm_mc);
            item_ctglpq_xm_cddw = itemView.findViewById(R.id.item_ctglpq_xm_cddw);
            item_ctglpq_xm_gm = itemView.findViewById(R.id.item_ctglpq_xm_gm);
            item_ctglpq_xm_ttzt = itemView.findViewById(R.id.item_ctglpq_xm_ttzt);
            item_ctglpq_xm_zfqk = itemView.findViewById(R.id.item_ctglpq_xm_zfqk);
            item_ctglpq_xm_zfje = itemView.findViewById(R.id.item_ctglpq_xm_zfje);
            item_ctglpq_xm_cz = itemView.findViewById(R.id.item_ctglpq_xm_cz);
            item_ctglpq_xm_sc = itemView.findViewById(R.id.item_ctglpq_xm_sc);
        }
    }
    public interface OnClickLister{
        void onClick(PjProBean.DataBean.ListBean analysisEnty);
    }
    public interface OnDetClickLister{
        void onDetClick(PjProBean.DataBean.ListBean analysisEnty);
    }
}
