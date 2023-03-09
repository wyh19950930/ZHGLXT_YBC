package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.widget.zdybj.bean.TimeLineBean;
import com.jymj.zhglxt.widget.zdybj.bean.TimeLineXmBean;
import com.jymj.zhglxt.widget.zdybj.bean.TimeLineXmItem;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @package com.jymj.htz1.widget
 * @fileName MeTitle
 * @date 2019/1/2314:44
 * @name qzw
 * 自定义时间轴控件
 */

public class TimeLineLayoutXM extends LinearLayout {
    private RecyclerView tlv_time_line;
    private Context context;
    private OnTimeLineLister onTimeLineLister;
    BaseQuickAdapter<TimeLineXmBean, BaseViewHolder> baseQuickAdapter = null;
    ArrayList<TimeLineXmBean> timeLineBeans = new ArrayList<>();


    public TimeLineLayoutXM(Context context) {
        this(context,null);
    }

    public TimeLineLayoutXM(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }


    public TimeLineLayoutXM(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
       /* Log.i("", "getValues: ");
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.attrs_info);
        tv_act_my_item_key.setText(key);
        et_act_my_item_value.setHint(hint);
        array.recycle();*/
    }

    /**
     *
     * @param names  各状态数据名称
     * @param end   目前状态  索引
     * @param select 选中状态  索引
     */
    public void setValueText(ArrayList<TimeLineXmItem> names, int end, int select){
        timeLineBeans.clear();
        for (int i = 0; i < names.size(); i++) {
            TimeLineXmBean timeLineBean = new TimeLineXmBean(names.get(i).getName(),names.get(i).getTime());
            if (i<=end){
                timeLineBean.setSelected(1);
            }
            if (i==select){
                timeLineBean.setSelect(1);
            }
            timeLineBeans.add(timeLineBean);
        }
        tlv_time_line.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        baseQuickAdapter = new BaseQuickAdapter<TimeLineXmBean, BaseViewHolder>(R.layout.item_time_line_xm, timeLineBeans) {
            @Override
            protected void convert(BaseViewHolder helper, TimeLineXmBean item) {
                if (helper.getAdapterPosition() == 0) {
                    helper.setVisible(R.id.view_item_time_line_xm_start, false);
                }
                if (helper.getAdapterPosition() == timeLineBeans.size() - 1) {
                    helper.setVisible(R.id.view_item_time_line_xm_end, false);
                }
                helper.setText(R.id.tv_item_time_line_xm_name,item.getName());
                helper.setText(R.id.tv_item_time_line_xm_time,item.getTime());
                Button ivItemTimeLineName = helper.getView(R.id.iv_item_time_line_xm_name);
                if (item.getSelected()==1){
                    ivItemTimeLineName.setBackgroundResource(R.drawable.bt_actiive_xmjz_green);
                }else {
                    helper.getView(R.id.view_item_time_line_xm_start).setBackgroundColor(Color.parseColor("#DDDDDD"));
                    ivItemTimeLineName.setBackgroundResource(R.drawable.bt_actiive_xmjz_grey);
                    helper.getView(R.id.view_item_time_line_xm_end).setBackgroundColor(Color.parseColor("#DDDDDD"));
                }
                if (item.getSelect()==1){
                    ivItemTimeLineName.setBackgroundResource(R.drawable.bt_actiive_xmjz_red);
                    helper.getView(R.id.view_item_time_line_xm_end).setBackgroundColor(Color.parseColor("#DDDDDD"));
                }
                helper.getView(R.id.rl_item_time_line_xm).setOnClickListener(new OnClickListener() {//helper.itemView  iv_item_time_line_xm_name
                    @Override
                    public void onClick(View v) {
                        /*setRefresh(helper.getAdapterPosition());//数据刷新
                        if (onTimeLineLister!=null)
                        onTimeLineLister.onClick(item,helper.getAdapterPosition());//回调*/
                    }
                });
            }
        };
        tlv_time_line.setAdapter(baseQuickAdapter);

    }
    //数据刷新
    private void setRefresh(int select){
        if (select>=0&&select<timeLineBeans.size()){
            TimeLineXmBean timeLineBean = timeLineBeans.get(select);
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
        void onClick(TimeLineBean timeLineBean, int position);
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
