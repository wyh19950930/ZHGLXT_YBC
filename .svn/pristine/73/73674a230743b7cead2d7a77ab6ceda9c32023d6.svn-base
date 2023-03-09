package com.jymj.zhglxt.bean.pickerview;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/2/18 10:58
 */
public class CityBean implements IPickerViewData {

    private int id;
    private String label;
    private String value;
    private List<CountyBean> children;

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

    public List<CountyBean> getChildren() {
        return children;
    }

    public void setChildren(List<CountyBean> children) {
        this.children = children;
    }

    @Override
    public String getPickerViewText() {
        return this.value;
    }
}
