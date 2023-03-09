package com.jymj.zhglxt.ldrkgl.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.api.AppMenus;
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageEntity;

import java.util.List;

/**
 * @package com.jymj.projectmanager.ui.main.adapter
 * @fileName CzlbAdapter
 * @date 2019/3/717:09
 * @name qzw
 */

/**
 * 图例
 */
public class MyNewsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FlowMassageEntity> list;
    private OnMyNewsItemClick onMyNewsItemClick;
    public MyNewsAdapter(Context context, List<FlowMassageEntity> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnMyNewsItemClick(OnMyNewsItemClick onMyNewsItemClick) {
        this.onMyNewsItemClick = onMyNewsItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LinearLayout.inflate(context, R.layout.item_inves_overview, null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_my_news_list, parent,false);
        return new MyNewsHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyNewsHolder myCtViewHolder = (MyNewsHolder) holder;
        FlowMassageEntity flowMassageEntity = list.get(position);
        myCtViewHolder.tv_item_my_news_name.setText(flowMassageEntity.getName());
        myCtViewHolder.tv_item_my_news_time.setText(flowMassageEntity.getCreateDate());
        myCtViewHolder.tv_item_my_news_title.setText(flowMassageEntity.getTitle());
        myCtViewHolder.tv_item_my_news_detail.setText(flowMassageEntity.getContent());

        if (flowMassageEntity.getDispose()==0){//待处理
            myCtViewHolder.iv_item_news_dcl.setImageResource(R.drawable.dcl_my_news);
            myCtViewHolder.tv_item_my_news_cl.setVisibility(View.VISIBLE);
        }else {//已处理
            myCtViewHolder.iv_item_news_dcl.setImageResource(R.drawable.ycl_my_news);
            myCtViewHolder.tv_item_my_news_cl.setVisibility(View.GONE);
        }
        myCtViewHolder.tv_item_my_news_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyNewsItemClick.onClickLjcl(list.get(position));
            }
        });
        myCtViewHolder.ll_item_my_news_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyNewsItemClick.onClick("点击第"+position+"条");
            }
        });
        myCtViewHolder.rlv_item_my_news_image.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        myCtViewHolder.rlv_item_my_news_image.setAdapter(new MyNewsImageAdapter(context,flowMassageEntity.getFlowMassageFiles()));
        if (!AppMenus.getInstance().getMenuBeans().toString().contains("消息修改")){
            myCtViewHolder.tv_item_my_news_cl.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyNewsHolder extends RecyclerView.ViewHolder{


        private LinearLayout ll_item_my_news_list;
        private TextView tv_item_my_news_name;//名称
        private TextView tv_item_my_news_time;//时间
        private ImageView iv_item_news_dcl;//待处理/已处理
        private TextView tv_item_my_news_title;//标题
        private TextView tv_item_my_news_detail;//详细内容
        private RecyclerView rlv_item_my_news_image;//展示图片
        private TextView tv_item_my_news_cl;//处理  已处理隐藏  未处理显示

        public MyNewsHolder(View itemView) {
            super(itemView);
            ll_item_my_news_list = itemView.findViewById(R.id.ll_item_my_news_list);
            tv_item_my_news_name = itemView.findViewById(R.id.tv_item_my_news_name);
            tv_item_my_news_time = itemView.findViewById(R.id.tv_item_my_news_time);
            iv_item_news_dcl = itemView.findViewById(R.id.iv_item_news_dcl);
            tv_item_my_news_title = itemView.findViewById(R.id.tv_item_my_news_title);
            tv_item_my_news_detail = itemView.findViewById(R.id.tv_item_my_news_detail);
            rlv_item_my_news_image = itemView.findViewById(R.id.rlv_item_my_news_image);
            tv_item_my_news_cl = itemView.findViewById(R.id.tv_item_my_news_cl);

        }
    }
    public interface OnMyNewsItemClick{
        void onClick(String pjtaskFile);
        void onClickLjcl(FlowMassageEntity pjtaskFile);
    }
}
