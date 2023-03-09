package com.jymj.zhglxt.rjhj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.api.ApiConstants;
import com.jymj.zhglxt.rjhj.bean.PjEnviorSupvsEntity;
import com.setsuna.common.baseapp.AppCache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @package com.jymj.landrenovation.ui.main.adapter
 * @fileName EnvironmentalAdapter
 * @date 2019/1/308:49
 * @name qzw
 */
public class EnvironmentalAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<PjEnviorSupvsEntity> list;
    private OnClickEnvironLister onClickEnvironLister;
    private int qutype;

    public EnvironmentalAdapter(Context mContext, List<PjEnviorSupvsEntity> list, int type) {
        this.mContext = mContext;
        this.list = list;
        this.qutype = type;
    }

    public void setOnClickEnvironLister(OnClickEnvironLister onClickEnvironLister) {
        this.onClickEnvironLister = onClickEnvironLister;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_table_environmental, parent, false);
        return new EnvironmentalView(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EnvironmentalView environmentalView = (EnvironmentalView) holder;
        PjEnviorSupvsEntity pjEnviorSupvsEntity = list.get(position);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        qutype = pjEnviorSupvsEntity.getQutype();
        if (qutype!=1&&qutype!=0){
            environmentalView.yujing_environmental.setVisibility(View.VISIBLE);
            if (qutype!=5){//判断如果问题状态在销账不显示预警
                environmentalView.yujing_sfcq_environmental.setVisibility(View.VISIBLE);
            }
        }else {
            environmentalView.yujing_environmental.setVisibility(View.GONE);
            environmentalView.zt_environmental.setVisibility(View.GONE);
            environmentalView.yujing_sfcq_environmental.setVisibility(View.GONE);
        }
        if (AppCache.getInstance().getType()!= ApiConstants.RJHJ_WYLR){
            environmentalView.sfws_environmental.setVisibility(View.GONE);
        }

        if (AppCache.getInstance().getType() ==ApiConstants.RJHJ_WY){
            environmentalView.xm_environmental.setVisibility(View.GONE);
        }else {
            environmentalView.xm_environmental.setVisibility(View.VISIBLE);
        }
        if (AppCache.getInstance().getType() ==ApiConstants.RJHJ_WYLR){
            environmentalView.zt_environmental.setVisibility(View.VISIBLE);
//            environmentalView.yujing_environmental.setVisibility(View.VISIBLE);
            environmentalView.xm_environmental.setVisibility(View.VISIBLE);
            environmentalView.yujing_sfcq_environmental.setVisibility(View.GONE);
            environmentalView.yujing_environmental.setVisibility(View.GONE);

        }

        Date dateString = null;
        if (qutype == 0||qutype == 1||qutype == 2){
            environmentalView.xm_environmental.setText(pjEnviorSupvsEntity.getJlr());
            if (!pjEnviorSupvsEntity.getJltime().equals("")){
                try {
                    dateString = simpleDateFormat.parse(pjEnviorSupvsEntity.getJltime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                environmentalView.time_environmental.setText(simpleDateFormat.format(dateString));//记录时间
            }else{
//                environmentalView.time_environmental.setText("");
            }

        }else if (qutype == 3){
            environmentalView.xm_environmental.setText(pjEnviorSupvsEntity.getJlr());
//            environmentalView.time_environmental.setText(pjEnviorSupvsEntity.getKstime());
//            environmentalView.time_environmental.setText(pjEnviorSupvsEntity.getJltime());
            /*if (!pjEnviorSupvsEntity.getKstime().equals("请选择时间")){
                try {
                    dateString = simpleDateFormat.parse(pjEnviorSupvsEntity.getKstime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                environmentalView.time_environmental.setText(simpleDateFormat.format(dateString));//处理时间
            }else{
                environmentalView.time_environmental.setText("");
            }*/
        }else if (qutype == 4){
            environmentalView.xm_environmental.setText(pjEnviorSupvsEntity.getJlr());
            if (!pjEnviorSupvsEntity.getJltime().equals("")){
                try {
                    dateString = simpleDateFormat.parse(pjEnviorSupvsEntity.getJltime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                environmentalView.time_environmental.setText(simpleDateFormat.format(dateString));//处理时间  simpleDateFormat.format(dateString)
            }else{
//                environmentalView.time_environmental.setText("");
            }
        }else if (qutype == 5){
            environmentalView.xm_environmental.setText(pjEnviorSupvsEntity.getJlr());

            if (!pjEnviorSupvsEntity.getJltime().equals("")){
                try {
                    dateString = simpleDateFormat.parse(pjEnviorSupvsEntity.getJltime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                environmentalView.time_environmental.setText(simpleDateFormat.format(dateString));//验收时间
            }else{
//                environmentalView.time_environmental.setText("");
            }
        }
        if (!pjEnviorSupvsEntity.getJltime().equals("")){
            try {
                dateString = simpleDateFormat.parse(pjEnviorSupvsEntity.getJltime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            environmentalView.time_environmental.setText(simpleDateFormat.format(dateString));//处理时间  simpleDateFormat.format(dateString)
        }else{
            environmentalView.time_environmental.setText("");
        }
        if (pjEnviorSupvsEntity.getZdwtText().equals("是")){
            //Html.fromHtml("<font color= #02A2FF>" +T +"</font>"+pjEnviorSupvsEntity.getId())
            environmentalView.bh_environmental.setText(Html.fromHtml("<font color= #ff6000>T</font>"+pjEnviorSupvsEntity.getNo()));//"T"+pjEnviorSupvsEntity.getId()
        }else {
            environmentalView.bh_environmental.setText(pjEnviorSupvsEntity.getNo().toString());
        }

        if (!pjEnviorSupvsEntity.getBh().equals("")){
            environmentalView.xzq_environmental.setText(pjEnviorSupvsEntity.getXzqmc()+"-"+pjEnviorSupvsEntity.getBh());
        }else {
            environmentalView.xzq_environmental.setText(pjEnviorSupvsEntity.getXzqmc());
        }

        environmentalView.wz_environmental.setText(pjEnviorSupvsEntity.getWz());
        environmentalView.xzq_environmental_wtlx.setText(pjEnviorSupvsEntity.getHjzzsjText());
        environmentalView.zrdw_environmental.setText(pjEnviorSupvsEntity.getZrdw());
        environmentalView.zt_environmental.setText(pjEnviorSupvsEntity.getQutypeText());
        if (pjEnviorSupvsEntity.getCounts()>0){
            environmentalView.yujing_environmental.setText(pjEnviorSupvsEntity.getCounts().toString());//驳回次数
//            environmentalView.yujing_environmental.setTextColor(Color.RED);
        }else {
            environmentalView.yujing_environmental.setText("");//驳回次数
        }
        if (pjEnviorSupvsEntity.getMonwarnin()==0){
            environmentalView.yujing_sfcq_environmental.setText("正常");
            environmentalView.yujing_sfcq_environmental.setTextColor(Color.BLACK);
        }else {
            environmentalView.yujing_sfcq_environmental.setText("超期");
            environmentalView.yujing_sfcq_environmental.setTextColor(Color.RED);
        }



        if (pjEnviorSupvsEntity.getQutype()==0){
            environmentalView.sfws_environmental.setText("待完善");
            environmentalView.sfws_environmental.setTextColor(Color.RED);
        }else {
            environmentalView.sfws_environmental.setText("已完善");
            environmentalView.sfws_environmental.setTextColor(Color.parseColor("#41C3FD"));
        }

        environmentalView.tvTableEnvironmental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEnvironLister.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class EnvironmentalView extends RecyclerView.ViewHolder {

        private final LinearLayout tvTableEnvironmental;
        private final TextView bh_environmental;
        private final TextView xzq_environmental;
        private final TextView xzq_environmental_wtlx;
        private final TextView wz_environmental;
        private final TextView zrdw_environmental;
        private final TextView zt_environmental;
        private final TextView xm_environmental;
        private final TextView time_environmental;
        private final TextView yujing_environmental;
        private final TextView yujing_sfcq_environmental;
        private final TextView sfws_environmental;

        public EnvironmentalView(View itemView) {
            super(itemView);

            tvTableEnvironmental = itemView.findViewById(R.id.ll_table_environmental);
            bh_environmental = itemView.findViewById(R.id.bh_environmental);
            xzq_environmental = itemView.findViewById(R.id.xzq_environmental);
            xzq_environmental_wtlx = itemView.findViewById(R.id.xzq_environmental_wtlx);
            wz_environmental = itemView.findViewById(R.id.wz_environmental);
            zrdw_environmental = itemView.findViewById(R.id.zrdw_environmental);
            zt_environmental = itemView.findViewById(R.id.zt_environmental);
            xm_environmental = itemView.findViewById(R.id.xm_environmental);
            time_environmental = itemView.findViewById(R.id.time_environmental);
            yujing_environmental = itemView.findViewById(R.id.yujing_environmental);
            yujing_sfcq_environmental = itemView.findViewById(R.id.yujing_sfcq_environmental);
            sfws_environmental = itemView.findViewById(R.id.sfws_environmental);

        }
    }
    public interface OnClickEnvironLister{
        void onClick(int position);
        void onDeleteClick(int position);
    }
}
