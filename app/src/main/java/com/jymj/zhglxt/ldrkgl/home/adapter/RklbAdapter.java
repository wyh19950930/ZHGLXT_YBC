package com.jymj.zhglxt.ldrkgl.home.adapter;

import android.annotation.SuppressLint;
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
import com.jymj.zhglxt.ldrkgl.home.bean.FlowInfoEntity;

import java.util.List;

/**
 * @package com.jymj.projectmanager.ui.main.adapter
 * @fileName CzlbAdapter
 * @date 2019/3/717:09
 * @name qzw
 */
public class RklbAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FlowInfoEntity> list;
    private OnRklbItemClick onItemClick;
    public RklbAdapter(Context context, List<FlowInfoEntity> list) {
        this.context = context;
        this.list = list;
    }
    public void setOnItemClick(OnRklbItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LinearLayout.inflate(context, R.layout.item_inves_overview, null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rklb_list, parent,false);
        return new MyCtViewHolder(inflate);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyCtViewHolder myCtViewHolder = (MyCtViewHolder) holder;
        FlowInfoEntity xzqInfoEntity = list.get(position);
        myCtViewHolder.tv_item_rklb_ldname.setText(xzqInfoEntity.getName());
        myCtViewHolder.tv_item_rklb_iphone.setText(xzqInfoEntity.getPhone());
        myCtViewHolder.tv_item_rklb_mph.setText("门牌号: "+xzqInfoEntity.getMph());
        myCtViewHolder.tv_item_rklb_hylx.setText(xzqInfoEntity.getIndustryText());
        myCtViewHolder.tv_item_rklb_djrq.setText("截止日期: "+xzqInfoEntity.getResideqDate());
        myCtViewHolder.tv_item_rklb_hjdz.setText(xzqInfoEntity.getProvince()+xzqInfoEntity.getCity()+xzqInfoEntity.getCounty()+xzqInfoEntity.getAddress());

        if (xzqInfoEntity.getWarning()==0){//正常
            myCtViewHolder.tv_item_rklb_djrq.setTextColor(Color.parseColor("#5CB0FC"));
        }else if (xzqInfoEntity.getWarning()==1){//快超期
            myCtViewHolder.tv_item_rklb_djrq.setTextColor(Color.parseColor("#ffff00"));
        }else if (xzqInfoEntity.getWarning()==2){//超期
            myCtViewHolder.tv_item_rklb_djrq.setTextColor(Color.parseColor("#E80000"));
        }

        myCtViewHolder.ll_item_rklb_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(list.get(position));
            }
        });
      /*  myCtViewHolder.tv_item_rklb_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(list.get(position));
            }
        });
        myCtViewHolder.tv_item_rklb_lc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onDetClick(list.get(position),position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyCtViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_item_rklb_ldname;
        private TextView tv_item_rklb_iphone;
        private TextView tv_item_rklb_mph;
        private TextView tv_item_rklb_hylx;
        private TextView tv_item_rklb_djrq;
        private TextView tv_item_rklb_hjdz;
        private TextView tv_item_rklb_update;
        private TextView tv_item_rklb_lc;
        private LinearLayout ll_item_rklb_list;

        public MyCtViewHolder(View itemView) {
            super(itemView);
            tv_item_rklb_ldname = itemView.findViewById(R.id.tv_item_rklb_ldname);
            tv_item_rklb_iphone = itemView.findViewById(R.id.tv_item_rklb_iphone);
            tv_item_rklb_mph = itemView.findViewById(R.id.tv_item_rklb_mph);
            tv_item_rklb_hylx = itemView.findViewById(R.id.tv_item_rklb_hylx);
            tv_item_rklb_djrq = itemView.findViewById(R.id.tv_item_rklb_djrq);
            tv_item_rklb_hjdz = itemView.findViewById(R.id.tv_item_rklb_hjdz);
            tv_item_rklb_update = itemView.findViewById(R.id.tv_item_rklb_update);
            tv_item_rklb_lc = itemView.findViewById(R.id.tv_item_rklb_lc);
            ll_item_rklb_list = itemView.findViewById(R.id.ll_item_rklb_list);

        }
    }
    public interface OnRklbItemClick{
        void onClick(FlowInfoEntity pjtaskFile);
        void onDetClick(FlowInfoEntity pjtaskFile,int position);
    }
}
