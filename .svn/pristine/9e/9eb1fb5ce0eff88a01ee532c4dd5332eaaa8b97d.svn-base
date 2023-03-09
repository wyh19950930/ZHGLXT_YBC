package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.TextViewsEntity;
import com.jymj.zhglxt.widget.zdybj.bean.TextListBean;
import com.jymj.zhglxt.widget.zdybj.bean.TimeLineBean;
import com.setsuna.common.commonutils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @package com.jymj.htz1.widget
 * @fileName MeTitle
 * @date 2019/1/2314:44
 * @name qzw
 * 自定义输入框
 */

public class TextListLayout extends LinearLayout {
    private RecyclerView rlv_layout_text_list;
    private Context context;
    private OnTextListLister onTextListLister;

    public TextListLayout(Context context) {
        this(context,null);
    }
    public TextListLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }
    public TextListLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getValues(attrs);
    }

    public void setOnTextListLister(OnTextListLister onTextListLister) {
        this.onTextListLister = onTextListLister;
    }

    //初始化控件
    private void init(final Context context){
        Log.i("", "init: ");
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_text_list, this);
        rlv_layout_text_list = findViewById(R.id.rlv_layout_text_list);
    }
    //获取布局里边传值
    public void getValues(AttributeSet attrs){
       /* Log.i("", "getValues: ");
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.attrs_info);
        String key=array.getString(R.styleable.attrs_info_attr_key);
        String hint=array.getString(R.styleable.attrs_info_attr_hint);
//        String value=array.getString(R.styleable.attrs_info_attr_value);
        tv_act_my_item_key.setText(key);
        et_act_my_item_value.setHint(hint);
        *//*String text=array.getString(R.styleable.attrs_info_attr_text);
        meTitleTvRight.setText(text);*//*
        array.recycle();*/
    }

    /**
     *
     * @param values //数据集合
     * @param integers 一行单条的数据索引集合  必须把所有带条的筛选选出来
     */
    LinearLayoutManager linearLayoutManager = null;

    //给输入框赋值  索引
    public void setValueTexts(ArrayList<TextViewsEntity> values, Integer[] integers){
        ArrayList<Integer> resultList= new ArrayList<>(Arrays.asList(integers));
        ArrayList<List<TextViewsEntity>> objects = new ArrayList<>();
        ArrayList<TextViewsEntity> textListBeans = null;
        for (int i = 0; i < values.size(); i++) {
//            if (!values.get(i).getValue().equals("")){
                if (resultList.contains(i)){
                    textListBeans = new ArrayList<>();
                    textListBeans.add(values.get(i));
                    objects.add(textListBeans);
                    textListBeans = null;
                }else {
                    if (textListBeans==null)
                        textListBeans = new ArrayList<>();
                    textListBeans.add(values.get(i));
                    if (textListBeans.size()==2){
                        objects.add(textListBeans);
                        textListBeans = null;
                    }
                }
//            }

        }
        /*Log.e("*****************",new Gson().toJson(resultList));
        Log.e("*****************",new Gson().toJson(values));
        Log.e("*****************",new Gson().toJson(objects));*/
//        if (linearLayoutManager==null){
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;//super.canScrollVertically()
                }
            };
//        }
        rlv_layout_text_list.setLayoutManager(linearLayoutManager);
        rlv_layout_text_list.setAdapter(new BaseQuickAdapter<List<TextViewsEntity>, BaseViewHolder>(R.layout.item_text_list_layout, objects) {
            @Override
            protected void convert(BaseViewHolder helper, List<TextViewsEntity> item) {
                MeItemLayout milTextListLayout1 = helper.getView(R.id.mil_text_list_layout1);
                LinearLayout llTextListLayout = helper.getView(R.id.ll_text_list_layout);
                MeItemLayout milTextListLayout2 = helper.getView(R.id.mil_text_list_layout2);
                View viewItemTextListLayout = helper.getView(R.id.view_item_text_list_layout);
                MeItemLayout milTextListLayout3 = helper.getView(R.id.mil_text_list_layout3);
                milTextListLayout1.setFocusable1();
                milTextListLayout2.setFocusable1();
                milTextListLayout3.setFocusable1();
                if (item.size()==1){
                    TextViewsEntity textListBean = item.get(0);
                    milTextListLayout1.setVisibility(View.VISIBLE);
                    llTextListLayout.setVisibility(View.GONE);
                    milTextListLayout1.setValueKey(textListBean.getTitle());
                    milTextListLayout1.setValueText(textListBean.getValue());
                    //onTextListLister
                    milTextListLayout1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onTextListLister!=null)
                            onTextListLister.onClick(textListBean,helper.getAdapterPosition());
                        }
                    });
                }else {
                    /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);*/
                    LinearLayout.LayoutParams params = (LayoutParams) viewItemTextListLayout.getLayoutParams();
                    if (helper.getAdapterPosition()==0){
                        params.setMargins(0,10,0,0);
                        viewItemTextListLayout.setLayoutParams(params);
                    }else {
                        int inft = helper.getAdapterPosition() + 1;
                        if (objects.get(helper.getAdapterPosition()-1).size()==1){
                            params.setMargins(0,10,0,0);
                            viewItemTextListLayout.setLayoutParams(params);
                        } else if (inft<=objects.size()){
                            if (inft==objects.size()){
                                params.setMargins(0,0,0,20);
                                viewItemTextListLayout.setLayoutParams(params);
                            }else if (objects.get(inft).size()==1||inft==objects.size()){
                                params.setMargins(0,0,0,20);
                                viewItemTextListLayout.setLayoutParams(params);
                            }

//                            ToastUtils.showShort("");
                        }

                    }
                    TextViewsEntity textListBean = item.get(0);
                    TextViewsEntity textListBean1 = item.get(1);
                    milTextListLayout1.setVisibility(View.GONE);
                    llTextListLayout.setVisibility(View.VISIBLE);

                    milTextListLayout2.setValueKey(textListBean.getTitle());
                    milTextListLayout2.setValueText(textListBean.getValue());
                    milTextListLayout3.setValueKey(textListBean1.getTitle());
                    milTextListLayout3.setValueText(textListBean1.getValue());
                    //onTextListLister.onClick(textListBean,helper.getAdapterPosition());
                    milTextListLayout2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onTextListLister!=null)
                            onTextListLister.onClick(textListBean,helper.getAdapterPosition());
                        }
                    });
                    milTextListLayout3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onTextListLister!=null)
                            onTextListLister.onClick(textListBean1,helper.getAdapterPosition());
                        }
                    });
                }


            }
        });

    }
    //点击回调
    public interface OnTextListLister{
        void onClick(TextViewsEntity timeLineBean,int position);
    }
}
