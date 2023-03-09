package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.widget.zdybj.bean.TimeLineBean;

import java.util.ArrayList;


/**
 * @package com.jymj.htz1.widget
 * @fileName MeTitle
 * @date 2019/1/2314:44
 * @name qzw
 * 自定义时间轴控件
 */

public class TimeLineLayout extends LinearLayout {
    private RecyclerView tlv_time_line;
    private Context context;
    private OnTimeLineLister onTimeLineLister;
    BaseQuickAdapter<TimeLineBean, BaseViewHolder> baseQuickAdapter = null;
    ArrayList<TimeLineBean> timeLineBeans = new ArrayList<>();
    boolean attr_isshow_right = true;


    public TimeLineLayout(Context context) {
        this(context,null);
    }

    public TimeLineLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }


    public TimeLineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getValues(attrs);
    }

    public void setOnTimeLineLister(OnTimeLineLister onTimeLineLister) {
        this.onTimeLineLister = onTimeLineLister;
    }

    //初始化控件
    private void init(final Context context){
        Log.i("", "init: ");
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_time_line, this);
        tlv_time_line = findViewById(R.id.tlv_time_line);
    }
    public void getValues(AttributeSet attrs){
       /* Log.i("", "getValues: ");*/
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.attrs_info);//attr_ischeck

        attr_isshow_right = array.getBoolean(R.styleable.attrs_info_attr_ischeck,true);

        array.recycle();
    }

    /**
     *
     * @param names  各状态数据名称
     * @param end   目前状态  索引
     * @param select 选中状态  索引
     */
    public void setValueText(ArrayList<String> names,int end,int select){
        timeLineBeans.clear();
        for (int i = 0; i < names.size(); i++) {
            TimeLineBean timeLineBean = new TimeLineBean(names.get(i));
            if (i<=end){
                timeLineBean.setSelected(1);
            }
            if (i==select){
                timeLineBean.setSelect(1);
            }
            timeLineBeans.add(timeLineBean);
        }
        tlv_time_line.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        baseQuickAdapter = new BaseQuickAdapter<TimeLineBean, BaseViewHolder>(R.layout.item_time_line, timeLineBeans) {
            @Override
            protected void convert(BaseViewHolder helper, TimeLineBean item) {
                if (helper.getAdapterPosition() == 0) {
                    helper.setVisible(R.id.view_item_time_line_start, false);
                }
                if (helper.getAdapterPosition() == timeLineBeans.size() - 1) {
                    helper.setVisible(R.id.view_item_time_line_end, false);
                }
                helper.setText(R.id.tv_item_time_line_name,item.getName());
                ImageView ivItemTimeLineName = helper.getView(R.id.iv_item_time_line_name);
                if (item.getSelected()==1){
                    ivItemTimeLineName.setImageResource(R.drawable.progress_green);
                }else {
                    ivItemTimeLineName.setImageResource(R.drawable.progress_grey);
                }
                if (item.getSelect()==1){
                    ivItemTimeLineName.setImageResource(R.drawable.progress_blue);
                }
                helper.getView(R.id.rl_item_time_line).setOnClickListener(new OnClickListener() {//helper.itemView  iv_item_time_line_name
                    @Override
                    public void onClick(View v) {
                        if (attr_isshow_right){
                            setRefresh(helper.getAdapterPosition());//数据刷新
                            if (onTimeLineLister!=null)
                                onTimeLineLister.onClick(item,helper.getAdapterPosition());//回调
                        }
                    }
                });
            }
        };
        tlv_time_line.setAdapter(baseQuickAdapter);

    }
    //数据刷新
    private void setRefresh(int select){
        if (select>=0&&select<timeLineBeans.size()){
            TimeLineBean timeLineBean = timeLineBeans.get(select);
            if (timeLineBean.getSelected()==1){//当前是否是已 经过状态
                for (int i = 0; i < timeLineBeans.size(); i++) {
                    timeLineBeans.get(i).setSelect(0);
                }
                timeLineBean.setSelect(1);
            }else {

            }
        }

        if (baseQuickAdapter!=null){//
            baseQuickAdapter.notifyDataSetChanged();
        }

    }

    //点击回调
    public interface OnTimeLineLister{
        void onClick(TimeLineBean timeLineBean,int position);
    }
    /*public void setMyLayout(int resId,String name){
        myItemId.setImageResource(resId);
        myItemTv.setText(name);

    }
    public void addMyLayout(int resId, String name, String yin){
        myItemId.setImageResource(resId);
        myItemTv.setText(name);
        myItemTvYin.setVisibility(View.VISIBLE);
        myItemTvYin.setText(yin);
    }
    public TextView setNotIcon(String name, String yin){
        myItemTv.setText(name);
        myItemId.setVisibility(View.GONE);
        myItemTvYin.setVisibility(View.VISIBLE);
        myItemTvYin.setText(yin);
        return myItemTvYin;
    }*/

}
