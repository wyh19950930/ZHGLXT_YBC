package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jymj.zhglxt.R;


/**
 * @package com.jymj.htz1.widget
 * @fileName MeTitle
 * @date 2019/1/2314:44
 * @name qzw
 * 自定义输入框
 */

public class ZdyEditLayout extends LinearLayout {
    private TextView tv_zdy_edit_key;
    private EditText et_zdy_edit_value;
    private Context context;

    public ZdyEditLayout(Context context) {
        this(context,null);
    }
    public ZdyEditLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }
    public ZdyEditLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getValues(attrs);
    }
    //初始化控件
    private void init(final Context context){
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_zdy_edit, this);
        tv_zdy_edit_key = inflate.findViewById(R.id.tv_zdy_edit_key);
        et_zdy_edit_value = inflate.findViewById(R.id.et_zdy_edit_value);
    }
    //获取布局里边传值
    public void getValues(AttributeSet attrs){
//        Log.i("", "getValues: ");
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.attrs_info);
        String key=array.getString(R.styleable.attrs_info_attr_key);
        String hint=array.getString(R.styleable.attrs_info_attr_hint);
        String value=array.getString(R.styleable.attrs_info_attr_value);
        boolean isint=array.getBoolean(R.styleable.attrs_info_attr_isint,false);
        boolean isfloat=array.getBoolean(R.styleable.attrs_info_attr_isfloat,false);
        if (isint){
            et_zdy_edit_value.setInputType( InputType.TYPE_CLASS_NUMBER);
        }
        if (isfloat){
            et_zdy_edit_value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        tv_zdy_edit_key.setText(key);
        et_zdy_edit_value.setHint(hint);
        et_zdy_edit_value.setText(value);
        array.recycle();
    }
    //获取输入的值
    public void setText(String text){
        et_zdy_edit_value.setText(text);
    }
    //获取输入的值
    public String getValue(){
        return et_zdy_edit_value.getText().toString();
    }

}