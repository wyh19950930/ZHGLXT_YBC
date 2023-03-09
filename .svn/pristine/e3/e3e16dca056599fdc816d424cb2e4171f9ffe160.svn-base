package com.jymj.zhglxt.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jymj.zhglxt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by setsuna on 18-3-30.
 */

public class SpinnerAdapter extends BaseAdapter {
    List<String> datas = new ArrayList<>();
    Context mContext;
    public SpinnerAdapter(Context context) {
        this.mContext = context;
    }
    
    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }
    
    @Override
    public Object getItem(int position) {
        return datas==null?null:datas.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner_list, null);
            hodler.mTextView = (TextView) convertView;
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
      /*  if (position == 0){//
            Drawable drawable = mContext.getResources().getDrawable(
                    R.drawable.ic_white_down_arrows);
            // / 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            hodler.mTextView.setCompoundDrawables(null, null, drawable, null);
        }else {
            hodler.mTextView.setCompoundDrawables(null, null, null, null);
        }*/
        
        hodler.mTextView.setText(datas.get(position));
        
        return convertView;
    }
    
    private static class ViewHodler{
        TextView mTextView;
    }
}
