package com.jymj.zhglxt.util;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.jymj.zhglxt.bean.enums.IndustryEnum;
import com.jymj.zhglxt.bean.enums.IsHaveEnum;
import com.jymj.zhglxt.bean.enums.IsTrueEnum;
import com.jymj.zhglxt.bean.pickerview.ProvinceBean;
import com.jymj.zhglxt.rjhj.bean.yl.enums.RegistraEnum;
import com.jymj.zhglxt.widget.zdybj.ZdyTextLayout;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseGxEnum;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseTypeEnum;
import com.jymj.zhglxt.zjd.bean.jsjb.JsjbEnumBean;
import com.jymj.zhglxt.zjd.bean.jsjb.LightBean;
import com.jymj.zhglxt.zjd.bean.jsjb.OpinionEnumBean;
import com.jymj.zhglxt.zjd.bean.qyzt.JyztEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.SccgEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.ScczEnum;
import com.jymj.zhglxt.zjd.bean.qyzt.ScmjEnum;
import com.setsuna.common.commonutils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class JsjbPickerUtil {
    public static OptionsPickerView nationPickerView=null;
//    public static Activity activity;
    public static OpinionEnumBean opinionEnumBean;
    public static List<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();//省
    public static List<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();//市
    public static List<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();//县

    public static OpinionEnumBean getOpinionEnumBean() {
        return opinionEnumBean;
    }
    public static void setOpinionEnumBean(OpinionEnumBean opinionEnumBean) {
        JsjbPickerUtil.opinionEnumBean = opinionEnumBean;
    }
    public static void setOptions1Items(List<ProvinceBean> options1Items) {
        JsjbPickerUtil.options1Items = options1Items;
    }
    public static void setOptions2Items(List<ArrayList<String>> options2Items) {
        JsjbPickerUtil.options2Items = options2Items;
    }
    public static void setOptions3Items(List<ArrayList<ArrayList<String>>> options3Items) {
        JsjbPickerUtil.options3Items = options3Items;
    }


    //初始化选择器
    public static void initNationPickerView(Activity activity,String value, String type, OnJsjbLinear textView) {
//        String value = textView.getValue();
        nationPickerView = new OptionsPickerBuilder(activity, new OnOptionsSelectListener(){
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                textView.onClick(getTypeList(type).get(options1));
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
        nationPickerView.setPicker(selectString(getTypeList(type)));//一级选择器
        nationPickerView.setSelectOptions(selectString(getTypeList(type)).indexOf(value));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = nationPickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        nationPickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        nationPickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        nationPickerView.show();
    }
    public static void initHjdzPickerView(Activity activity,String value, String type, OnJsjbHjdzLinear onJsjbHjdzLinear){
//        String[] split = value.split("-");
        OptionsPickerView nationPickerView = new OptionsPickerBuilder(activity, new OnOptionsSelectListener(){
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                onJsjbHjdzLinear.onClick(options1Items.get(options1).getPickerViewText(),
                        options2Items.get(options1).get(options2),
                        options3Items.get(options1).get(options2).get(options3));
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
        nationPickerView.setPicker(options1Items);//一级选择器
        nationPickerView.setPicker(options1Items,
                options2Items);//二级选择器
        nationPickerView.setPicker(options1Items,
                options2Items,
                options3Items);//三级选择器  设置数据
//        nationPickerView.setSelectOptions();//选择
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = nationPickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        nationPickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        nationPickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        nationPickerView.show();
    }
    public static void initSelectPickerView(Activity activity,String value, String type, List<String> strings, OnSelectLinear textView) {
//        String value = textView.getValue();
        OptionsPickerView nationPickerView = new OptionsPickerBuilder(activity, new OnOptionsSelectListener(){
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (strings.size()>0){
                    textView.onClick(strings.get(options1));
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
        nationPickerView.setPicker(strings);//一级选择器
        nationPickerView.setSelectOptions(strings.indexOf(value));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = nationPickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        nationPickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        nationPickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        nationPickerView.show();
    }

    private static List<String> selectString(List<JsjbEnumBean> jsjbEnumBeans){
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < jsjbEnumBeans.size(); i++) {
            strings.add(jsjbEnumBeans.get(i).getName());
        }
        return strings;
    }
    public static String getString(List<JsjbEnumBean> jsjbEnumBeans,int index){
        String name ="暂无";
        for (int i = 0; i < jsjbEnumBeans.size(); i++) {
            if (jsjbEnumBeans.get(i).getIndex()==index){
                name = jsjbEnumBeans.get(i).getName();
            }
        }
        return name;
    }
    public static  List<JsjbEnumBean> getTypeList(String type){
        if (type.equals("来电主体")) {
            return opinionEnumBean.getCallSubjectEnum();
        }else if (type.equals("市级来源")) {
            return opinionEnumBean.getCitySourceEnum();
        }else if (type.equals("所属部门")) {
            return opinionEnumBean.getDepartmentEnum();
        }else if (type.equals("区问题分类")) {
            return opinionEnumBean.getDistrictProblemEnum();
        }else if (type.equals("问题分类")) {
            return opinionEnumBean.getProblemTypeEnum();
        }else if (type.equals("派单类型")) {
            return opinionEnumBean.getSendEnum();
        }else if (type.equals("来源渠道")) {
            return opinionEnumBean.getSourcesEnum();
        }else if (type.equals("加急案件")) {
            return opinionEnumBean.getUrgentCaseEnum();
        }else if (type.equals("工单类型")) {
            return opinionEnumBean.getWorkOrderEnum();
        }else if (type.equals("工单标签")) {
            return opinionEnumBean.getWorkLabelEnum();
        }else if (type.equals("工单属性")) {
            return opinionEnumBean.getWorkAttributeEnum();
        }else if (type.equals("关键字")) {
            return opinionEnumBean.getKeywordEnum();
        }else if (type.equals("不稳定因素")) {
            return opinionEnumBean.getUnstableEnum();
        }else if (type.equals("是否")) {
            return opinionEnumBean.getIsTrueEnum();
        }else if (type.equals("剩余时间")) {
            return opinionEnumBean.getTimeRemainingEnum();
        }
        return new ArrayList<>();
    }

    public interface OnJsjbLinear{
        void onClick(JsjbEnumBean jsjbEnumBean);
    }
    public interface OnJsjbHjdzLinear{
        void onClick(String sheng,String shi,String xian);
    }
    public interface OnSelectLinear{
        void onClick(String sheng);
    }



}