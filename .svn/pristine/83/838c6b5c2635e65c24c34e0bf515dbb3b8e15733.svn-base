package com.jymj.zhglxt.rjhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.rjhj.bean.BaobiaoBean;

import java.util.List;

public class HbAdapter extends BaseAdapter {

    private List<BaobiaoBean> mList;
    private Context mContext;

    public HbAdapter(Context pContext, List<BaobiaoBean> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 下面是重要代码
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.item_hb_adapter, null);
        if(convertView!=null)
        {
            TextView _TextView1=(TextView)convertView.findViewById(R.id.tv_hb_adapter);
            /*if (mList.get(position).getName().equals("村内暂无问题")||mList.get(position).getName().equals("公厕未正常开放")){
//                mList.get(position).getName().equals("公厕未正常开放")
                _TextView1.setTextColor(Color.RED);
            }*/
            _TextView1.setText(mList.get(position).getName());
        }
        return convertView;
    }

}
