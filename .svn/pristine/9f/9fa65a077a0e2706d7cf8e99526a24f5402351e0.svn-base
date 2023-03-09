package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.zjd.bean.yzt.cs.MoveCostLevel0;
import com.jymj.zhglxt.zjd.bean.yzt.cs.MoveCostLevel1;
import com.jymj.zhglxt.zjd.bean.yzt.cs.MoveCostLevel2;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class CeSuanAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private Context context;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CeSuanAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        this.context=context;
        addItemType(0, R.layout.item_cesuan0);
        addItemType(1, R.layout.item_cesuan1);
        addItemType(2, R.layout.item_cesuan2);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case 0:
                final MoveCostLevel0 level0 = (MoveCostLevel0) item;
                helper.setText(R.id.tvCountCeSuanItem0, level0.getCount())
                        .setText(R.id.tvProjectCeSuanItem0, level0.getProject())
                        .setText(R.id.tvQuantitieCeSuanItem0, level0.getQuantitie())
                        .setText(R.id.tvUnitCeSuanItem0, level0.getQuaunit())
                        .setText(R.id.tvBilpriceCeSuanItem0, level0.getBilprice())
                        .setText(R.id.tvQuaunitCeSuanItem0, level0.getUnit())
                        .setText(R.id.tvSumcostCeSuanItem0, level0.getSumcost());
                if (level0.isHasChild()){
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, android.R.color.holo_red_light));
                }else if ((helper.getAdapterPosition()&1) != 1){
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, R.color.bg_white_gray));
                }else {
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, android.R.color.white));
                }

                if (getData().get(getData().size() - 1)==item || getData().get(getData().size() - 2)==item){
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, android.R.color.holo_orange_light));
                }
               
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (level0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case 1:
                final MoveCostLevel1 level1 = (MoveCostLevel1) item;
                if (level1.isHasChild()){
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, android.R.color.holo_green_light));
                }else if ((helper.getAdapterPosition()&1) != 1){
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, R.color.bg_white_gray));
                }else {
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, android.R.color.white));
                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (level1.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                
                helper.setText(R.id.tvCountCeSuanItem1, level1.getCount())
                        .setText(R.id.tvProjectCeSuanItem1, level1.getProject())
                        .setText(R.id.tvQuantitieCeSuanItem1, level1.getQuantitie())
                        .setText(R.id.tvUnitCeSuanItem1, level1.getQuaunit())
                        .setText(R.id.tvBilpriceCeSuanItem1, level1.getBilprice())
                        .setText(R.id.tvQuaunitCeSuanItem1, level1.getUnit())
                        .setText(R.id.tvSumcostCeSuanItem1, level1.getSumcost());
                break;
            
            case 2:
                final MoveCostLevel2 moveCost = (MoveCostLevel2) item;
                if ((helper.getAdapterPosition()&1) != 1){
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, R.color.bg_white_gray));
                }else {
                    helper.itemView.setBackgroundColor(ContextCompat.getColor
                            (context, android.R.color.white));
                }
                helper.setText(R.id.tvCountCeSuanItem2, moveCost.getCount())
                        .setText(R.id.tvProjectCeSuanItem2, moveCost.getProject())
                        .setText(R.id.tvQuantitieCeSuanItem2, moveCost.getQuantitie())
                        .setText(R.id.tvUnitCeSuanItem2, moveCost.getQuaunit())
                        .setText(R.id.tvBilpriceCeSuanItem2, moveCost.getBilprice())
                        .setText(R.id.tvQuaunitCeSuanItem2, moveCost.getUnit())
                        .setText(R.id.tvSumcostCeSuanItem2, moveCost.getSumcost());
                break;
        }
    }
    
}
