package com.jymj.zhglxt.xm.helper;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.ui.activity.MainActivity;
import com.jymj.zhglxt.xm.bean.BcProjectFile;

import java.util.Collections;
import java.util.List;

public class MyItemTouchHelper  extends ItemTouchHelper.Callback{
    private List<BcProjectFile> pictureBeans;
    private BaseQuickAdapter<BcProjectFile, BaseViewHolder> recycleViewAdapter;
    Activity activity;
    public MyItemTouchHelper(Activity activity, List<BcProjectFile> pictureBeans, BaseQuickAdapter<BcProjectFile, BaseViewHolder> recycleViewAdapter) {
        Log.d("dddd","into MyItemTouchHelper");
        this.pictureBeans = pictureBeans;
        this.recycleViewAdapter = recycleViewAdapter;
        this.activity = activity;
    }

    public void setPictureBeans(List<BcProjectFile> pictureBeans,BaseQuickAdapter<BcProjectFile, BaseViewHolder> recycleViewAdapter) {
        this.pictureBeans = pictureBeans;
        this.recycleViewAdapter = recycleViewAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        final int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
        //得到当拖拽的viewHolder的Position
        int fromPosition = viewHolder.getAdapterPosition();
        //拿到当前拖拽到的item的viewHolder
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(pictureBeans, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(pictureBeans, i, i - 1);
            }
        }
        recycleViewAdapter.notifyItemMoved(fromPosition, toPosition);
//        recycleViewAdapter.notifyDataSetChanged();
        Log.d("dddd"," fromPosition = "+fromPosition+" toPosition"+toPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    /**
     * 长按选中Item时修改颜色
     *
     * @param viewHolder
     * @param actionState
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
        //viewHolder.itemView.setBackground(getDrawable(R.drawable.card_drag_selected));
        //}
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 手指松开的时候还原颜色
     * @param recyclerView
     * @param viewHolder
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        //viewHolder.itemView.setBackground(getDrawable(R.drawable.card));
    }

    /**
     * 重写拖拽不可用
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
}
