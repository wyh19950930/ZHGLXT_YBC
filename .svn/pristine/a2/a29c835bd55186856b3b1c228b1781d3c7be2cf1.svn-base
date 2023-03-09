package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jymj.zhglxt.R;

import androidx.annotation.Nullable;


/**
 * @package com.jymj.htz1.widget
 * @fileName MeTitle
 * @date 2019/1/2314:44
 * @name qzw
 * 自定义输入框
 */

public class ZdyTextLayout_1 extends LinearLayout {
    private TextView tv_zdy_text_key;
    private ImageView iv_zdy_text_time;
    private TextView tv_zdy_text_value;
    private ImageView iv_zdy_text_arrows;
    private Context context;

    public ZdyTextLayout_1(Context context) {
        this(context,null);
    }
    public ZdyTextLayout_1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }
    public ZdyTextLayout_1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getValues(attrs);
    }
    //初始化控件
    private void init(final Context context){
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_zdy_text_1, this);
        tv_zdy_text_key = inflate.findViewById(R.id.tv_zdy_text_key_1);
        iv_zdy_text_time = inflate.findViewById(R.id.iv_zdy_text_time_1);
        tv_zdy_text_value = inflate.findViewById(R.id.tv_zdy_text_value_1);
        iv_zdy_text_arrows = inflate.findViewById(R.id.iv_zdy_text_arrows_1);
    }
    //获取布局里边传值
    public void getValues(AttributeSet attrs){
//        Log.i("", "getValues: ");
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.attrs_info);
        String key=array.getString(R.styleable.attrs_info_attr_key);
        String hint=array.getString(R.styleable.attrs_info_attr_hint);
        int time=array.getResourceId(R.styleable.attrs_info_attr_time,0);
        int arrows=array.getResourceId(R.styleable.attrs_info_attr_arrows,0);
        tv_zdy_text_key.setText(key);
        tv_zdy_text_value.setHint(hint);
        if (time==0){
            iv_zdy_text_time.setVisibility(View.GONE);
        }else {
            iv_zdy_text_time.setImageResource(time);
        }
        if (arrows==0){
            iv_zdy_text_arrows.setVisibility(View.GONE);
        }else {
            iv_zdy_text_arrows.setImageResource(arrows);
        }
        array.recycle();
    }
    //获取输入的值
    public String getValue(){
        return tv_zdy_text_value.getText().toString();
    }
    public void setText(String text){
        tv_zdy_text_value.setText(text);
    }

}
