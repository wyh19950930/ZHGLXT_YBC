package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity;
import com.jymj.zhglxt.widget.zdybj.bean.TuliColorBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @package com.jymj.htz1.widget
 * @fileName MeTitle
 * @date 2019/1/2314:44
 * @name qzw
 * 自定义输入框 图例
 */

public class TuLiColorLayout extends LinearLayout {
    private RecyclerView rlv_layout_tuli_color_list;
    private Context context;

    public TuLiColorLayout(Context context) {
        this(context,null);
    }
    public TuLiColorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }
    public TuLiColorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getValues(attrs);
    }


    //初始化控件
    private void init(final Context context){
        Log.i("", "init: ");
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_tuli_color_list, this);
        rlv_layout_tuli_color_list = findViewById(R.id.rlv_layout_tuli_color_list);
    }
    //获取布局里边传值
    public void getValues(AttributeSet attrs){

    }

    public void setData(List<TuliColorBean> tuliColorBeans){//item_tuli_list_layout
        rlv_layout_tuli_color_list.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rlv_layout_tuli_color_list.setAdapter(new BaseQuickAdapter<TuliColorBean,BaseViewHolder>(R.layout.item_tuli_list_layout,tuliColorBeans) {
            @Override
            protected void convert(BaseViewHolder helper, TuliColorBean item) {
                helper.setText(R.id.tv_tuli_list_layout,item.getName());
//                ImageView ivTuliListLayout = helper.getView(R.id.iv_tuli_list_layout);
                TextView tvTuliListLayout = helper.getView(R.id.tv_tuli_list_layout);
                if (item.getTuLiColor()>0){//图片
                    tvTuliListLayout.setBackgroundResource(item.getTuLiColor());
                }else {//颜色
                    tvTuliListLayout.setBackgroundColor(item.getTuLiColor());
                }
//                Log.e("ivTuliListLayout",""+item.getTuLiColor());
            }
        });
    }

}
