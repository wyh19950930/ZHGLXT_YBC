package com.jymj.zhglxt.bean.pickerview;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/2/18 10:58
 */
public class ProvinceBean implements IPickerViewData {

    private int id;
    private String label;
    private String value;
    private List<CityBean> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<CityBean> getChildren() {
        return children;
    }

    public void setChildren(List<CityBean> children) {
        this.children = children;
    }

/*实现 IPickerViewData 接口，
        这个用来显示在PickerView上面的字符串，
        PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。*/

    @Override
    public String getPickerViewText() {
        return this.value;
    }
}
