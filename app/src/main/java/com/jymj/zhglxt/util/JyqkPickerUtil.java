package com.jymj.zhglxt.util;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jymj.zhglxt.bean.enums.IndustryEnum;
import com.jymj.zhglxt.bean.enums.IsHaveEnum;
import com.jymj.zhglxt.bean.enums.IsTrueEnum;
import com.jymj.zhglxt.bean.enums.NationEnum;
import com.jymj.zhglxt.rjhj.bean.yl.enums.RegistraEnum;
import com.jymj.zhglxt.widget.zdybj.MeTitleLayout;
import com.jymj.zhglxt.widget.zdybj.ZdyEditLayout;
import com.jymj.zhglxt.widget.zdybj.ZdyTextLayout;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseGxEnum;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseTypeEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.JyztEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.SccgEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.ScczEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.ScmjEnum;

import java.util.ArrayList;
import java.util.List;

public class JyqkPickerUtil {
    public static OptionsPickerView nationPickerView=null;
    //初始化选择器
    public static void initNationPickerView(Activity activity, String type, ZdyTextLayout textView) {
        String value = textView.getValue();
        nationPickerView = new OptionsPickerBuilder(activity, new OnOptionsSelectListener(){
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (type.equals("经营状态")) {
                    textView.setText(getJyzt().get(options1));
                }else if (type.equals("有无地下空间")) {
                    textView.setText(getYwdxkj().get(options1));
                }else if (type.equals("企业类型")) {
                    textView.setText(getQylx().get(options1));
                }else if (type.equals("是否")) {
                    textView.setText(getSf().get(options1));
                }else if (type.equals("注册类型")) {
                    textView.setText(getZclx().get(options1));
                }else if (type.equals("行业类型")) {
                    textView.setText(getHylx().get(options1));
                }else if (type.equals("高新企业")) {
                    textView.setText(getGxqy().get(options1));
                }else if (type.equals("用地权属")) {
                    textView.setText(getYdqs().get(options1));
                }else if (type.equals("首层承重")) {
                    textView.setText(getSccz().get(options1));
                }else if (type.equals("首层层高")) {
                    textView.setText(getSccg().get(options1));
                }else if (type.equals("首层面积")) {
                    textView.setText(getScmj().get(options1));
                }
            }
        } )
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(type)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setContentTextSize(20)
                .isDialog(true)
                .build();//<Any>
        if (type.equals("经营状态")) {
            nationPickerView.setPicker(getJyzt());//一级选择器
            nationPickerView.setSelectOptions(getJyzt().indexOf(value));
        }else if (type.equals("有无地下空间")) {
            nationPickerView.setPicker(getYwdxkj());//一级选择器
            nationPickerView.setSelectOptions(getYwdxkj().indexOf(value));
        }else if (type.equals("企业类型")) {
            nationPickerView.setPicker(getQylx());//一级选择器
            nationPickerView.setSelectOptions(getQylx().indexOf(value));
        }else if (type.equals("是否")) {
            nationPickerView.setPicker(getSf());//一级选择器
            nationPickerView.setSelectOptions(getSf().indexOf(value));
        }else if (type.equals("注册类型")) {
            nationPickerView.setPicker(getZclx());//一级选择器
            nationPickerView.setSelectOptions(getZclx().indexOf(value));
        }else if (type.equals("行业类型")) {
            nationPickerView.setPicker(getHylx());//一级选择器
            nationPickerView.setSelectOptions(getHylx().indexOf(value));
        }else if (type.equals("高新企业")) {
            nationPickerView.setPicker(getGxqy());//一级选择器
            nationPickerView.setSelectOptions(getGxqy().indexOf(value));
        }else if (type.equals("用地权属")) {
            nationPickerView.setPicker(getYdqs());//一级选择器
            nationPickerView.setSelectOptions(getYdqs().indexOf(value));
        }else if (type.equals("首层承重")) {
            nationPickerView.setPicker(getSccz());//一级选择器
            nationPickerView.setSelectOptions(getSccz().indexOf(value));
        }else if (type.equals("首层层高")) {
            nationPickerView.setPicker(getSccg());//一级选择器
            nationPickerView.setSelectOptions(getSccg().indexOf(value));
        }else if (type.equals("首层面积")) {
            nationPickerView.setPicker(getScmj());//一级选择器
            nationPickerView.setSelectOptions(getScmj().indexOf(value));
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = nationPickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        nationPickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        nationPickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        nationPickerView.show();
    }
    //初始化选择器
    public static void initPickerView(Activity activity,String type, List<String> stringList, String textView, OnPickerLinerar onPickerLinerar) {
//        String value = textView.getText().toString();
        nationPickerView = new OptionsPickerBuilder(activity, new OnOptionsSelectListener(){
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (stringList.size()>0){
//                    textView.setText(stringList.get(options1));
                    onPickerLinerar.onClick(stringList.get(options1));
                }
            }
        } )
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(type)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setContentTextSize(20)
                .isDialog(true)
                .build();//<Any>
        nationPickerView.setPicker(stringList);//一级选择器
        nationPickerView.setSelectOptions(stringList.indexOf(textView));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = nationPickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        nationPickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        nationPickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        nationPickerView.show();
    }
    public interface OnPickerLinerar{
        void onClick(String name);
    }
    private static List<String> getJyzt(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < JyztEnum.values().length; i++) {
            strings.add(JyztEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getYwdxkj(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < IsHaveEnum.values().length; i++) {
            strings.add(IsHaveEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getQylx(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < EnterpriseTypeEnum.values().length; i++) {
            strings.add(EnterpriseTypeEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getSf(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < IsTrueEnum.values().length; i++) {
            strings.add(IsTrueEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getZclx(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < RegistraEnum.values().length; i++) {
            strings.add(RegistraEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getHylx(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < IndustryEnum.values().length; i++) {
            strings.add(IndustryEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getGxqy(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < EnterpriseGxEnum.values().length; i++) {
            strings.add(EnterpriseGxEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getYdqs(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("国有");
        strings.add("集体");
        strings.add("国有-集体");
        return strings;
    }
    private static List<String> getSccz(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < ScczEnum.values().length; i++) {
            strings.add(ScczEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getSccg(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < SccgEnum.values().length; i++) {
            strings.add(SccgEnum.values()[i].getName());
        }
        return strings;
    }
    private static List<String> getScmj(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < ScmjEnum.values().length; i++) {
            strings.add(ScmjEnum.values()[i].getName());
        }
        return strings;
    }
}