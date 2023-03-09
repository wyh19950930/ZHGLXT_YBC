package com.jymj.zhglxt.xm.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.xm.bean.BcProjectLx;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.iwf.photopicker.PhotoPreview;

/**
 * @package com.jymj.projectmanager.ui.main.adapter
 * @fileName CzlbAdapter
 * @date 2019/3/717:09
 * @name qzw
 */

/**
 * 图例
 */
public class XmFirstListBqAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<BcProjectLx> list;
    public XmFirstListBqAdapter(Context context, List<BcProjectLx> list) {
        this.context = context;
        this.list=list;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(context).inflate(R.layout.item_my_news_image_list, parent,false);
        View inflate = View.inflate(context,R.layout.item_xm_first_bq,null);
        return new XmFirstListBqHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        XmFirstListBqHolder myCtViewHolder = (XmFirstListBqHolder) holder;
        BcProjectLx bcProjectLx = list.get(position);

        myCtViewHolder.bt_xm_first_bq.setText(bcProjectLx.getLx());
        myCtViewHolder.bt_xm_first_bq.setBackgroundColor(Color.parseColor(bcProjectLx.getColor().replace("#","#20")));
        myCtViewHolder.bt_xm_first_bq.setTextColor(Color.parseColor(bcProjectLx.getColor()));
        /*String lxStr = myCtViewHolder.bt_xm_first_bq.getText().toString();
        if (lxStr.equals("历史文化型")){
//            myCtViewHolder.bt_xm_first_bq.setBackgroundResource(R.drawable.bt_actiive_lswhx);
            myCtViewHolder.bt_xm_first_bq.setBackgroundColor(Color.parseColor("#33ED742E"));
            myCtViewHolder.bt_xm_first_bq.setTextColor(Color.parseColor("#ED742E"));
        }else if (lxStr.equals("旅游型")){
//            myCtViewHolder.bt_xm_first_bq.setBackgroundResource(R.drawable.bt_actiive_lyx);
            myCtViewHolder.bt_xm_first_bq.setBackgroundColor(Color.parseColor("#334CA2FE"));
            myCtViewHolder.bt_xm_first_bq.setTextColor(Color.parseColor("#4CA2FE"));
        }else {
//            myCtViewHolder.bt_xm_first_bq.setBackgroundResource(R.drawable.bt_actiive_qtx);
            myCtViewHolder.bt_xm_first_bq.setBackgroundColor(Color.parseColor("#33999999"));
            myCtViewHolder.bt_xm_first_bq.setTextColor(Color.parseColor("#999999"));
        }*/


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class XmFirstListBqHolder extends RecyclerView.ViewHolder{


        private TextView bt_xm_first_bq;

        public XmFirstListBqHolder(View itemView) {
            super(itemView);
            bt_xm_first_bq = itemView.findViewById(R.id.bt_xm_first_bq);

        }
    }

}
