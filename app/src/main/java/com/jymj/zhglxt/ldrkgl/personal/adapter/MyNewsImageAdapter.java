package com.jymj.zhglxt.ldrkgl.personal.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.ldrkgl.personal.bean.FlowMassageFile;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPreview;

/**
 * @package com.jymj.projectmanager.ui.main.adapter
 * @fileName CzlbAdapter
 * @date 2019/3/717:09
 * @name qzw
 */

/**
 * 图例
 */
public class MyNewsImageAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FlowMassageFile> list;
//    private List<String> list;
    private OnMyNewsItemImageClick onMyNewsItemClick;
    public MyNewsImageAdapter(Context context, List<FlowMassageFile> list) {
        this.context = context;
        this.list=list;
    }

    public void setOnMyNewsItemClick(OnMyNewsItemImageClick onMyNewsItemClick) {
        this.onMyNewsItemClick = onMyNewsItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(context).inflate(R.layout.item_my_news_image_list, parent,false);
        View inflate = View.inflate(context,R.layout.item_my_news_image_list,null);
        return new MyNewsImageHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyNewsImageHolder myCtViewHolder = (MyNewsImageHolder) holder;
        FlowMassageFile flowMassageFile = list.get(position);

        Glide.with(context).load(flowMassageFile.getPath())
                        .into(myCtViewHolder.iv_item_my_news_image);
        myCtViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> stringList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    stringList.add(list.get(i).getPath());
                }
                PhotoPreview.builder()
                        .setPhotos(stringList)
                        .setCurrentItem(position)
                        .setShowDeleteButton(false)
                        .start((Activity)context);//context!!
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyNewsImageHolder extends RecyclerView.ViewHolder{


        private ImageView iv_item_my_news_image;//图片

        public MyNewsImageHolder(View itemView) {
            super(itemView);
            iv_item_my_news_image = itemView.findViewById(R.id.iv_item_my_news_image);

        }
    }
    public interface OnMyNewsItemImageClick{
        void onClick(String pjtaskFile);
    }
}
