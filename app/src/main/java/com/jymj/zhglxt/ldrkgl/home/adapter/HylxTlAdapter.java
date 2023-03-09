package com.jymj.zhglxt.ldrkgl.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.ldrkgl.home.bean.LegendBean;

import java.text.DecimalFormat;
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
public class HylxTlAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<LegendBean> list;
    private DecimalFormat format = new DecimalFormat("##################.##");
    public HylxTlAdapter(Context context, List<LegendBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LinearLayout.inflate(context, R.layout.item_inves_overview, null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_hylxtl_list, parent,false);
        return new MyCtViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyCtViewHolder myCtViewHolder = (MyCtViewHolder) holder;
        LegendBean legendBean = list.get(position);
        myCtViewHolder.tv_item_hylxt_color.setBackgroundColor(legendBean.getColor());
        myCtViewHolder.tv_item_hylxt_name.setText(legendBean.getName());
        myCtViewHolder.tv_item_hylxt_count.setText(format.format(legendBean.getCount())+"");
        myCtViewHolder.tv_item_hylxt_count.setTextColor(legendBean.getColor());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyCtViewHolder extends RecyclerView.ViewHolder{

        private View tv_item_hylxt_color;//图例颜色
        private TextView tv_item_hylxt_name;//图例名字
        private TextView tv_item_hylxt_count;//数量

        public MyCtViewHolder(View itemView) {
            super(itemView);
            tv_item_hylxt_color = itemView.findViewById(R.id.tv_item_hylxt_color);
            tv_item_hylxt_name = itemView.findViewById(R.id.tv_item_hylxt_name);
            tv_item_hylxt_count = itemView.findViewById(R.id.tv_item_hylxt_count);

        }
    }
}
