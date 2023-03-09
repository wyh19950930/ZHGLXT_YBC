package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.util.SingleOnClickUtil;
import com.jymj.zhglxt.zjd.activity.QyglDetailActivity;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseBasisEntity;
import com.jymj.zhglxt.zjd.fragment.ZjdglFragment;
import com.setsuna.common.commonutils.ToastUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class QyglDetailAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<EnterpriseBasisEntity> list;


    public QyglDetailAdapter(Context mContext, List<EnterpriseBasisEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_qygl_detail_act, viewGroup, false);
        return new QyglDetailView(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, int i) {
        QyglDetailView qyglDetailView = (QyglDetailView) viewHolder;
        EnterpriseBasisEntity enterpriseBasisEntity = list.get(i);
        qyglDetailView.tv_item_qygl_detail_qymc.setText(enterpriseBasisEntity.getBh());
        qyglDetailView.tv_item_qygl_detail_qylx.setText(enterpriseBasisEntity.getName());
        qyglDetailView.tv_item_qygl_detail_zcdz.setText(enterpriseBasisEntity.getQs());//getZcdz
        qyglDetailView.tv_item_qygl_detail_zcrq.setText(enterpriseBasisEntity.getZdmj().toString());
        qyglDetailView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SingleOnClickUtil.isFastClick()){
                    Intent intent = new Intent(mContext, QyglDetailActivity.class);//QyglDetailView
                    intent.putExtra("enterpriseBasisEntity",enterpriseBasisEntity);
                    mContext.startActivity(intent);
                }
//                ToastUtils.showShort(enterpriseBasisEntity.getGid().toString());
                /*Intent intent = new Intent(mContext, QyglAddActivity.class);
                intent.putExtra("id",enterpriseBasisEntity.getGid());
                mContext.startActivity(intent);*/

//                ZjdglFragment.Companion.kuangGeomentLine(enterpriseBasisEntity.getGeom());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class QyglDetailView extends RecyclerView.ViewHolder{

        private final TextView tv_item_qygl_detail_qymc;
        private final TextView tv_item_qygl_detail_qylx;
        private final TextView tv_item_qygl_detail_zcdz;
        private final TextView tv_item_qygl_detail_zcrq;

        public QyglDetailView(@NonNull @androidx.annotation.NonNull View itemView) {
            super(itemView);
            tv_item_qygl_detail_qymc = itemView.findViewById(R.id.tv_item_qygl_detail_qymc);
            tv_item_qygl_detail_qylx = itemView.findViewById(R.id.tv_item_qygl_detail_qylx);
            tv_item_qygl_detail_zcdz = itemView.findViewById(R.id.tv_item_qygl_detail_zcdz);
            tv_item_qygl_detail_zcrq = itemView.findViewById(R.id.tv_item_qygl_detail_zcrq);

        }
    }
}
