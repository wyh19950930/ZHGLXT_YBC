package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.yl.FloatPopulat;

import java.util.List;

public class LdrkSelectAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<FloatPopulat> floatPopulatList;
    private OnLdrkSelectLinear onLdrkSelectLinear;

    public LdrkSelectAdapter(Context mContext, List<FloatPopulat> floatPopulatList) {
        this.mContext = mContext;
        this.floatPopulatList = floatPopulatList;
    }
    public void deleteList(FloatPopulat floatPopulat){
        floatPopulatList.remove(floatPopulat);
        notifyDataSetChanged();
    }

    public void setOnLdrkSelectLinear(OnLdrkSelectLinear onLdrkSelectLinear) {
        this.onLdrkSelectLinear = onLdrkSelectLinear;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_ldrk_select, viewGroup, false);

        return new LdrkSelectViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LdrkSelectViewHolder ldrkSelectViewHolder = (LdrkSelectViewHolder) viewHolder;
        FloatPopulat floatPopulat = floatPopulatList.get(i);
        ldrkSelectViewHolder.tvLdrkSelectXm.setText(floatPopulat.getName());
        ldrkSelectViewHolder.tv_ldrk_select_xb.setText(floatPopulat.getSexText());
        ldrkSelectViewHolder.tv_ldrk_select_hzxm.setText(floatPopulat.getFdname());
        ldrkSelectViewHolder.tv_ldrk_select_jzdz.setText(floatPopulat.getJzdz());
        ldrkSelectViewHolder.tv_ldrk_select_szc.setText(floatPopulat.getXzqmc());
        ldrkSelectViewHolder.tv_ldrk_select_hylx.setText(floatPopulat.getCszyText());
        ldrkSelectViewHolder.tv_ldrk_select_gzdz.setText(floatPopulat.getGzdw());
        ldrkSelectViewHolder.tv_ldrk_select_whcd.setText(floatPopulat.getWhcdText());
        ldrkSelectViewHolder.tv_ldrk_select_sfzh.setText(floatPopulat.getIdCard());
        ldrkSelectViewHolder.tv_ldrk_select_jzzh.setText(floatPopulat.getJzzhm());
        if (!floatPopulat.isLrlc()){
            ldrkSelectViewHolder.ll_ldrk_select_top.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }else {
            ldrkSelectViewHolder.ll_ldrk_select_top.setBackgroundColor(Color.parseColor("#ffffff"));
        }

       /* ldrkSelectViewHolder.tv_ldrk_select_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLdrkSelectLinear.onUpdateClick(floatPopulat);
            }
        });*/
        ldrkSelectViewHolder.tv_ldrk_select_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onLdrkSelectLinear.onDeleteClick(floatPopulat,i);
            }
        });
        ldrkSelectViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLdrkSelectLinear.onClick(floatPopulat,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return floatPopulatList.size();
    }
    public class LdrkSelectViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvLdrkSelectXm;
        private final TextView tv_ldrk_select_xb;
        private final TextView tv_ldrk_select_hzxm;
        private final TextView tv_ldrk_select_jzdz;
        private final TextView tv_ldrk_select_szc;
        private final TextView tv_ldrk_select_hylx;
        private final TextView tv_ldrk_select_gzdz;
        private final TextView tv_ldrk_select_whcd;
        private final TextView tv_ldrk_select_sfzh;
        private final TextView tv_ldrk_select_jzzh;
//        private final TextView tv_ldrk_select_update;
        private final TextView tv_ldrk_select_delete;
        private final LinearLayout ll_ldrk_select_top;

        public LdrkSelectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLdrkSelectXm = itemView.findViewById(R.id.tv_ldrk_select_xm);
            tv_ldrk_select_xb = itemView.findViewById(R.id.tv_ldrk_select_xb);
            tv_ldrk_select_hzxm = itemView.findViewById(R.id.tv_ldrk_select_hzxm);
            tv_ldrk_select_jzdz = itemView.findViewById(R.id.tv_ldrk_select_jzdz);
            tv_ldrk_select_szc = itemView.findViewById(R.id.tv_ldrk_select_szc);
            tv_ldrk_select_hylx = itemView.findViewById(R.id.tv_ldrk_select_hylx);
            tv_ldrk_select_gzdz = itemView.findViewById(R.id.tv_ldrk_select_gzdz);
            tv_ldrk_select_whcd = itemView.findViewById(R.id.tv_ldrk_select_whcd);
            tv_ldrk_select_sfzh = itemView.findViewById(R.id.tv_ldrk_select_sfzh);
            tv_ldrk_select_jzzh = itemView.findViewById(R.id.tv_ldrk_select_jzzh);
//            tv_ldrk_select_update = itemView.findViewById(R.id.tv_ldrk_select_update);
            tv_ldrk_select_delete = itemView.findViewById(R.id.tv_ldrk_select_delete);
            ll_ldrk_select_top = itemView.findViewById(R.id.ll_ldrk_select_top);
        }
    }
    public interface OnLdrkSelectLinear{
        void onDeleteClick(FloatPopulat floatPopulat, int position);
        void onClick(FloatPopulat floatPopulat, int position);
    }
}
