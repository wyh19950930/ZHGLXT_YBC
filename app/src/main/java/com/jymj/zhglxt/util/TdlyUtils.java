package com.jymj.zhglxt.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.app.AppApplication;
import com.jymj.zhglxt.widget.BubblePopupWindow;
import com.jymj.zhglxt.zjd.bean.TdlyTextEnum;
import com.jymj.zhglxt.zjd.bean.ZhuShiEnum;
import com.setsuna.common.commonutils.ToastUtils;

import java.math.BigDecimal;

public class TdlyUtils {

    public static BigDecimal getSum(EditText... eds){
        BigDecimal bigDecimal = BigDecimal.ZERO;
        for (int i = 0; i < eds.length; i++) {
            EditText editText = eds[i];
            if (!editText.getText().toString().equals("")){
                try {
                    bigDecimal = bigDecimal.add(new BigDecimal(editText.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bigDecimal;
    }
    public static void setTextPop(Context activity, String name, View view){
        BubblePopupWindow leftTopWindow = new BubblePopupWindow(activity);
        View bubbleView = LayoutInflater.from(activity).inflate(R.layout.layout_popup_view, null);
        TextView tvContent = (TextView) bubbleView.findViewById(R.id.tvContent);
        tvContent.setText(name+":"+ TdlyTextEnum.getName(name));

        leftTopWindow.setBubbleView(bubbleView); // 设置气泡内容
        leftTopWindow.show(view, Gravity.TOP, 0); // 显示弹窗 Gravity.BOTTOM
    }
    public static void setTextToast(String name){
        ToastUtils.showLong(name+":"+ TdlyTextEnum.getName(name));

    }

    public static boolean setEditToast(TextView... eds){
        boolean isEdit = false;
        for (int i = 0; i < eds.length; i++) {
            TextView edt = eds[i];
            if (edt.getText().toString().equals("")){
                ToastUtils.showShort(edt.getHint().toString());
                isEdit = true;
                break;
            }else {
                isEdit = false;
            }
        }
        return isEdit;
    }
    public static boolean setObjectToast(Object... objects){
        boolean isEdit = false;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i]==null||objects[i].toString().length()==0){
                isEdit = true;
                break;
            }
        }
        return isEdit;
    }

}
