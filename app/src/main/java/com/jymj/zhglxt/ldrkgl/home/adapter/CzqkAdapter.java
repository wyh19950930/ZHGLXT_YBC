package com.jymj.zhglxt.ldrkgl.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.ldrkgl.home.bean.XzqInfoEntity;


import java.util.List;

/**
 * @package com.jymj.projectmanager.ui.main.adapter
 * @fileName CtBaseAdapter
 * @date 2019/3/717:09
 * @name qzw
 */
public class CzqkAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<XzqInfoEntity> list;
    private OnItemClick onItemClick;
    public CzqkAdapter(Context context, List<XzqInfoEntity> list) {
        this.context = context;
        this.list = list;
    }
    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LinearLayout.inflate(context, R.layout.item_inves_overview, null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_czqk_list, parent,false);
        return new MyCtViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyCtViewHolder myCtViewHolder = (MyCtViewHolder) holder;
        XzqInfoEntity xzqInfoEntity = list.get(position);
        myCtViewHolder.tv_item_czqk_cm.setText(xzqInfoEntity.getXzqmc());
        myCtViewHolder.tv_item_czqk_lgymc.setText("流管员："+xzqInfoEntity.getLgyname());
        myCtViewHolder.tv_item_czqk_czzjds.setText(xzqInfoEntity.getZhaicount()+"");
        myCtViewHolder.tv_item_czqk_czfjs.setText(xzqInfoEntity.getRoomcount()+"");
        myCtViewHolder.tv_item_czqk_ldrks.setText(xzqInfoEntity.getFlowcount()+"");

        myCtViewHolder.ll_item_czqk_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyCtViewHolder extends RecyclerView.ViewHolder{

        private final LinearLayout ll_item_czqk_list;
        private final TextView tv_item_czqk_cm;
        private final TextView tv_item_czqk_lgymc;
        private final TextView tv_item_czqk_czzjds;
        private final TextView tv_item_czqk_czfjs;
        private final TextView tv_item_czqk_ldrks;

        public MyCtViewHolder(View itemView) {
            super(itemView);
            ll_item_czqk_list = itemView.findViewById(R.id.ll_item_czqk_list);
            tv_item_czqk_cm = itemView.findViewById(R.id.tv_item_czqk_cm);
            tv_item_czqk_lgymc = itemView.findViewById(R.id.tv_item_czqk_lgymc);
            tv_item_czqk_czzjds = itemView.findViewById(R.id.tv_item_czqk_czzjds);
            tv_item_czqk_czfjs = itemView.findViewById(R.id.tv_item_czqk_czfjs);
            tv_item_czqk_ldrks = itemView.findViewById(R.id.tv_item_czqk_ldrks);

        }
    }
    public interface OnItemClick{
        void onClick(XzqInfoEntity pjtaskFile);
    }
}
