package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.fj.Renovated;
import com.setsuna.common.baseapp.AppCache;

import java.util.List;

/**
 * @package com.jymj.landrenovation.ui.main.adapter
 * @fileName EnvironmentalAdapter
 * @date 2019/1/308:49
 * @name qzw
 */
public class FjxcAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Renovated> list;
    private OnClickFjxcLister onClickFjxcLister;
    private int qutype;

    public FjxcAdapter(Context mContext, List<Renovated> list, int type) {
        this.mContext = mContext;
        this.list = list;
        this.qutype = type;
    }

    public void setOnClickEnvironLister(OnClickFjxcLister onClickFjxcLister) {
        this.onClickFjxcLister = onClickFjxcLister;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_table_fjxc, parent, false);
        return new FjxcView(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FjxcView environmentalView = (FjxcView) holder;
        Renovated pjEnviorSupvsEntity = list.get(position);
        environmentalView.tv_item_fjxc_bh.setText(pjEnviorSupvsEntity.getBh());
        environmentalView.tv_item_fjxc_cm.setText(pjEnviorSupvsEntity.getXzqmc());
        environmentalView.tv_item_fjxc_hzmc.setText(pjEnviorSupvsEntity.getHzmc());
        environmentalView.tv_item_fjxc_mph.setText(pjEnviorSupvsEntity.getMph());
        if (pjEnviorSupvsEntity.getWaring()==1){
            environmentalView.tv_item_fjxc_yj.setText("逾期");
            environmentalView.tv_item_fjxc_yj.setTextColor(Color.RED);
        }else {
            environmentalView.tv_item_fjxc_yj.setText("正常");
            environmentalView.tv_item_fjxc_yj.setTextColor(Color.GREEN);
        }

        if (AppCache.getInstance().getType()==2){
            environmentalView.tv_item_fjxc_jlr.setVisibility(View.GONE);
        }
        environmentalView.tv_item_fjxc_jlr.setText(pjEnviorSupvsEntity.getJlr());
        environmentalView.tv_item_fjxc_jlsj.setText(pjEnviorSupvsEntity.getJltime());
        if (pjEnviorSupvsEntity.getCounts()>0){
            environmentalView.tv_item_fjxc_bhcs.setText(pjEnviorSupvsEntity.getCounts().toString());
        }else {
            environmentalView.tv_item_fjxc_bhcs.setText("");
        }

        environmentalView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFjxcLister.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class FjxcView extends RecyclerView.ViewHolder {


        private final LinearLayout ll_table_fjxc;
        private final TextView tv_item_fjxc_bh;
        private final TextView tv_item_fjxc_cm;
        private final TextView tv_item_fjxc_hzmc;
        private final TextView tv_item_fjxc_mph;
        private final TextView tv_item_fjxc_yj;
        private final TextView tv_item_fjxc_jlr;
        private final TextView tv_item_fjxc_jlsj;
        private final TextView tv_item_fjxc_bhcs;

        public FjxcView(View itemView) {
            super(itemView);

            ll_table_fjxc = itemView.findViewById(R.id.ll_table_fjxc);
            tv_item_fjxc_bh = itemView.findViewById(R.id.tv_item_fjxc_bh);
            tv_item_fjxc_cm = itemView.findViewById(R.id.tv_item_fjxc_cm);
            tv_item_fjxc_hzmc = itemView.findViewById(R.id.tv_item_fjxc_hzmc);
            tv_item_fjxc_mph = itemView.findViewById(R.id.tv_item_fjxc_mph);
            tv_item_fjxc_yj = itemView.findViewById(R.id.tv_item_fjxc_yj);
            tv_item_fjxc_jlr = itemView.findViewById(R.id.tv_item_fjxc_jlr);
            tv_item_fjxc_jlsj = itemView.findViewById(R.id.tv_item_fjxc_jlsj);
            tv_item_fjxc_bhcs = itemView.findViewById(R.id.tv_item_fjxc_bhcs);

        }
    }
    public interface OnClickFjxcLister{
        void onClick(int position);
        void onDeleteClick(int position);
    }
}
