package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.api.ApiConstants;
import com.jymj.zhglxt.zjd.bean.wy.RentEntity;
import com.setsuna.common.baseapp.AppCache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @package com.jymj.landrenovation.ui.main.adapter
 * @fileName EnvironmentalAdapter
 * @date 2019/1/308:49
 * @name qzw
 */
public class WyFzListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<RentEntity> list;
    private OnClickEnvironLister onClickEnvironLister;

    public WyFzListAdapter(Context mContext, List<RentEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void setOnClickEnvironLister(OnClickEnvironLister onClickEnvironLister) {
        this.onClickEnvironLister = onClickEnvironLister;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_frag_wy_tab_fz, parent, false);
        return new EnvironmentalView(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EnvironmentalView holder1 = (EnvironmentalView) holder;
        RentEntity item = list.get(position);

        holder1.tv_item_wy_tab_fz_cqr.setText(item.getCqr());
        holder1.tv_item_wy_tab_fz_mph.setText(item.getMph());
        holder1.tv_item_wy_tab_fz_area.setText(item.getArea().toString());
        holder1.tv_item_wy_tab_fz_time.setText(item.getRenDate());
        holder1.tv_item_wy_tab_fz_status.setText("共"+item.getRoomToatl()+"间/修"+item.getRoomUse()+"间");
        if (item.getRoomToatl() == item.getRoomUse()){
            holder1.tv_item_wy_tab_fz_status.setBackgroundColor(Color.parseColor("#0EA10A"));
        }else{
            holder1.tv_item_wy_tab_fz_status.setBackgroundColor(Color.parseColor("#ECA00E"));
        }
        holder1.ll_item_wy_tab_fz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEnvironLister.onClick(position);
            }
        });

        holder1.tv_item_wy_tab_fz_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEnvironLister.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class EnvironmentalView extends RecyclerView.ViewHolder {

        private final LinearLayout ll_item_wy_tab_fz;
        private final TextView tv_item_wy_tab_fz_cqr;
        private final TextView tv_item_wy_tab_fz_mph;
        private final TextView tv_item_wy_tab_fz_area;
        private final TextView tv_item_wy_tab_fz_time;
        private final TextView tv_item_wy_tab_fz_status;
        private final TextView tv_item_wy_tab_fz_del;

        public EnvironmentalView(View itemView) {
            super(itemView);

            ll_item_wy_tab_fz = itemView.findViewById(R.id.ll_item_wy_tab_fz);
            tv_item_wy_tab_fz_cqr = itemView.findViewById(R.id.tv_item_wy_tab_fz_cqr);
            tv_item_wy_tab_fz_mph = itemView.findViewById(R.id.tv_item_wy_tab_fz_mph);
            tv_item_wy_tab_fz_area = itemView.findViewById(R.id.tv_item_wy_tab_fz_area);
            tv_item_wy_tab_fz_time = itemView.findViewById(R.id.tv_item_wy_tab_fz_time);
            tv_item_wy_tab_fz_status = itemView.findViewById(R.id.tv_item_wy_tab_fz_status);
            tv_item_wy_tab_fz_del = itemView.findViewById(R.id.tv_item_wy_tab_fz_del);

        }
    }
    public interface OnClickEnvironLister{
        void onClick(int position);
        void onDeleteClick(int position);
    }
}
