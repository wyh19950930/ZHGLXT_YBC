package com.jymj.zhglxt.rjhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jymj.zhglxt.R;

import java.util.List;

public class RightArrowAdapter extends RecyclerView.Adapter {

    private List<String> mList;
    private Context mContext;

    public RightArrowAdapter(Context pContext, List<String> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_right_arow, parent, false);
        return new RightArrowView(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class RightArrowView extends RecyclerView.ViewHolder {

        public RightArrowView(@NonNull View itemView) {
            super(itemView);
        }
    }

}
