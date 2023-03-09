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
import com.jymj.zhglxt.ldrkgl.home.bean.YlFolwEntity;

import java.util.List;

/**
 * @package com.jymj.projectmanager.ui.main.adapter
 * @fileName CzlbAdapter
 * @date 2019/3/717:09
 * @name qzw
 */
public class YllbAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<YlFolwEntity> list;
    private OnYLLBItemClick onItemClick;
    public YllbAdapter(Context context, List<YlFolwEntity> list) {
        this.context = context;
        this.list = list;
    }
    public void setOnItemClick(OnYLLBItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LinearLayout.inflate(context, R.layout.item_inves_overview, null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_yllb_list, parent,false);
        return new MyCtViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyCtViewHolder myCtViewHolder = (MyCtViewHolder) holder;
        YlFolwEntity xzqInfoEntity = list.get(position);
        myCtViewHolder.tv_item_yllb_cm.setText(xzqInfoEntity.getXzqmc());
        myCtViewHolder.tv_item_yllb_hzmc.setText(xzqInfoEntity.getHzmc());
        myCtViewHolder.tv_item_yllb_mph.setText(xzqInfoEntity.getMph());
        myCtViewHolder.tv_item_yllb_czf.setText(xzqInfoEntity.getDwell()+"");
        myCtViewHolder.tv_item_yllb_jzr.setText(xzqInfoEntity.getRent()+"");
        myCtViewHolder.tv_item_yllb_jdc.setText(xzqInfoEntity.getMotorCars()+"");
        myCtViewHolder.tv_item_yllb_mtc.setText(xzqInfoEntity.getMotorcycle()+"");
        myCtViewHolder.tv_item_yllb_ddc.setText(xzqInfoEntity.getElectricCars()+"");

        myCtViewHolder.ll_item_yllb_list.setOnClickListener(new View.OnClickListener() {
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

        private LinearLayout ll_item_yllb_list;
        private TextView tv_item_yllb_cm;
        private TextView tv_item_yllb_hzmc;
        private TextView tv_item_yllb_mph;
        private TextView tv_item_yllb_czf;
        private TextView tv_item_yllb_jzr;
        private TextView tv_item_yllb_jdc;
        private TextView tv_item_yllb_ddc;
        private TextView tv_item_yllb_mtc;

        public MyCtViewHolder(View itemView) {
            super(itemView);
            ll_item_yllb_list = itemView.findViewById(R.id.ll_item_yllb_list);
            tv_item_yllb_cm = itemView.findViewById(R.id.tv_item_yllb_cm);
            tv_item_yllb_hzmc = itemView.findViewById(R.id.tv_item_yllb_hzmc);
            tv_item_yllb_mph = itemView.findViewById(R.id.tv_item_yllb_mph);
            tv_item_yllb_czf = itemView.findViewById(R.id.tv_item_yllb_czf);
            tv_item_yllb_jzr = itemView.findViewById(R.id.tv_item_yllb_jzr);
            tv_item_yllb_jdc = itemView.findViewById(R.id.tv_item_yllb_jdc);
            tv_item_yllb_ddc = itemView.findViewById(R.id.tv_item_yllb_ddc);
            tv_item_yllb_mtc = itemView.findViewById(R.id.tv_item_yllb_mtc);

        }
    }
    public interface OnYLLBItemClick{
        void onClick(YlFolwEntity pjtaskFile);
    }
}
