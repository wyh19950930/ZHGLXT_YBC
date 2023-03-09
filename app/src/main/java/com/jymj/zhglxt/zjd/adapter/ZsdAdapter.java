package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.fj.Renovated;
import com.jymj.zhglxt.zjd.bean.jsjb.LightBean;
import com.setsuna.common.baseapp.AppCache;

import java.util.List;

/**
 * @package com.jymj.landrenovation.ui.main.adapter
 * @fileName EnvironmentalAdapter
 * @date 2019/1/308:49
 * @name qzw
 */
public class ZsdAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<LightBean> list;
    private OnClickZsdLister onClickFjxcLister;
    private int qutype;//是否可以点击 1 可以点击 2 不可以点击

    public ZsdAdapter(Context mContext, List<LightBean> list, int type) {
        this.mContext = mContext;
        this.list = list;
        this.qutype = type;
    }

    public void setOnClickEnvironLister(OnClickZsdLister onClickFjxcLister) {
        this.onClickFjxcLister = onClickFjxcLister;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zsd_list_layout, parent, false);
        return new ZsdViewHolder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZsdViewHolder environmentalView = (ZsdViewHolder) holder;
        LightBean pjEnviorSupvsEntity = list.get(position);
        environmentalView.cb_item_zsd_list.setText(pjEnviorSupvsEntity.getContent());
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(Color.WHITE);
        gradientDrawable.setStroke(2,Color.parseColor(pjEnviorSupvsEntity.getColor()));
        gradientDrawable.setSize(50,50);
        environmentalView.iv_item_zsd_list.setBackground(gradientDrawable);
        GradientDrawable gradientDrawable1 = new GradientDrawable();
        gradientDrawable1.setShape(GradientDrawable.OVAL);
        gradientDrawable1.setColor(Color.parseColor(pjEnviorSupvsEntity.getColor()));
        gradientDrawable1.setStroke(10,Color.parseColor(pjEnviorSupvsEntity.getColor()));
        gradientDrawable1.setSize(50,50);
        environmentalView.iv_item_zsd_list1.setBackground(gradientDrawable1);
        if (pjEnviorSupvsEntity.isCheck()){
            environmentalView.iv_item_zsd_list1.setVisibility(View.VISIBLE);
        }else {
            environmentalView.iv_item_zsd_list1.setVisibility(View.GONE);
        }
        if (qutype==1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!pjEnviorSupvsEntity.isCheck){
                        for (int i = 0; i < list.size(); i++) {
                            LightBean lightBean = list.get(i);
                            lightBean.setCheck(false);
                        }
                    }
                    pjEnviorSupvsEntity.setCheck(!pjEnviorSupvsEntity.isCheck);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ZsdViewHolder extends RecyclerView.ViewHolder {

        private final TextView cb_item_zsd_list;
        private final ImageView iv_item_zsd_list;
        private final ImageView iv_item_zsd_list1;

        public ZsdViewHolder(View itemView) {
            super(itemView);
            cb_item_zsd_list = itemView.findViewById(R.id.cb_item_zsd_list);
            iv_item_zsd_list = itemView.findViewById(R.id.iv_item_zsd_list);
            iv_item_zsd_list1 = itemView.findViewById(R.id.iv_item_zsd_list1);

        }
    }
    public interface OnClickZsdLister{
        void onClick(int position);
    }
}
