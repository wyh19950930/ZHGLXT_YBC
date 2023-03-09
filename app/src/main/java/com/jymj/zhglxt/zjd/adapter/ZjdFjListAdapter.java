package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.yl.ApplyEntity;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @package com.jymj.landrenovation.ui.main.adapter
 * @fileName EnvironmentalAdapter
 * @date 2019/1/308:49
 * @name qzw
 */
public class ZjdFjListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ApplyEntity> list;
    private OnClickFjxcLister onClickFjxcLister;
    private int qutype;

    public ZjdFjListAdapter(Context mContext, List<ApplyEntity> list, int type) {
        this.mContext = mContext;
        this.list = list;
        this.qutype = type;
    }

    public void setOnClickEnvironLister(OnClickFjxcLister onClickFjxcLister) {
        this.onClickFjxcLister = onClickFjxcLister;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_table_zjdfj, parent, false);
        return new FjxcView(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FjxcView environmentalView = (FjxcView) holder;
        ApplyEntity pjEnviorSupvsEntity = list.get(position);
        environmentalView.tv_item_zjdfj_bh.setText((position+1)+"");
        environmentalView.tv_item_zjdfj_cm.setText(pjEnviorSupvsEntity.getXzqmc());
        environmentalView.tv_item_zjdfj_hzmc.setText(pjEnviorSupvsEntity.getName());
        environmentalView.tv_item_zjdfj_mph.setText(pjEnviorSupvsEntity.getAddress());
//        environmentalView.tv_zjdfjgl_hb.setText(pjEnviorSupvsEntity.getSptypeText());
//        environmentalView.tv_item_zjdfj_jlsj.setText(pjEnviorSupvsEntity.getSqDate());
        environmentalView.tv_item_zjdfj_jlsj.setText(pjEnviorSupvsEntity.getMph());
        environmentalView.tv_item_zjdfj_lxfs.setText(pjEnviorSupvsEntity.getSqDate());//pjEnviorSupvsEntity.getIphone


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


        private final LinearLayout ll_table_zjdfj;
        private final TextView tv_item_zjdfj_bh;
        private final TextView tv_item_zjdfj_cm;
        private final TextView tv_item_zjdfj_hzmc;
        private final TextView tv_item_zjdfj_mph;
//        private final TextView tv_zjdfjgl_hb;
        private final TextView tv_item_zjdfj_jlsj;
        private final TextView tv_item_zjdfj_lxfs;

        public FjxcView(View itemView) {
            super(itemView);

            ll_table_zjdfj = itemView.findViewById(R.id.ll_table_zjdfj);
            tv_item_zjdfj_bh = itemView.findViewById(R.id.tv_item_zjdfj_bh);
            tv_item_zjdfj_cm = itemView.findViewById(R.id.tv_item_zjdfj_cm);
            tv_item_zjdfj_hzmc = itemView.findViewById(R.id.tv_item_zjdfj_hzmc);
            tv_item_zjdfj_mph = itemView.findViewById(R.id.tv_item_zjdfj_mph);
//            tv_zjdfjgl_hb = itemView.findViewById(R.id.tv_zjdfjgl_hb);
            tv_item_zjdfj_jlsj = itemView.findViewById(R.id.tv_item_zjdfj_jlsj);
            tv_item_zjdfj_lxfs = itemView.findViewById(R.id.tv_item_zjdfj_lxfs);

        }
    }
    public interface OnClickFjxcLister{
        void onClick(int position);
        void onDeleteClick(int position);
    }
}
