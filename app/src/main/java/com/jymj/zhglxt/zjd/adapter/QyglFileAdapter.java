package com.jymj.zhglxt.zjd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.util.GetFileIco;
import com.jymj.zhglxt.zjd.activity.PDFActivity;
import com.jymj.zhglxt.zjd.bean.cygl.EnterpriseFileEntity;
import com.jymj.zhglxt.zjd.bean.yzt.frame_mx.FrameYLMXBean;
import com.setsuna.common.baseapp.AppCache;
import com.setsuna.common.commonutils.FileUtils;

import java.util.List;

public class QyglFileAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<EnterpriseFileEntity> mList;
    private OnQyglFileLinear onClickLister;
    private boolean isUpdate;

    public QyglFileAdapter(Context context, List<EnterpriseFileEntity> mList, boolean isUpdate) {
        this.context = context;
        this.mList = mList;
        this.isUpdate = isUpdate;
    }
    public void setOnClickLister(OnQyglFileLinear onClickLister) {
        this.onClickLister = onClickLister;
    }

    public void setmList(List<EnterpriseFileEntity> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_qi_ye_photo, parent, false);
        return new QyglFileHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QyglFileHolder mholder = (QyglFileHolder) holder;
        if (position < mList.size()){
            EnterpriseFileEntity enterpriseFileEntity = mList.get(position);
            if (enterpriseFileEntity.getName().equals("")){
                mholder.tvItemQiYePhotoName.setText(enterpriseFileEntity.getFilename());
            }else {
                mholder.tvItemQiYePhotoName.setText(enterpriseFileEntity.getName());
            }
            if (AppCache.getInstance().getDuties()==0&&isUpdate){
                mholder.ivItemQiYeDelete.setVisibility(View.VISIBLE);
            }else {
                mholder.ivItemQiYeDelete.setVisibility(View.GONE);
            }
            int icoId = GetFileIco.getIcoId(FileUtils.getFileExtension(enterpriseFileEntity.getUrl()));
            Glide.with(context).load(icoId).into(mholder.ivItemQiYePhoto);

            mholder.ivItemQiYePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    onClickLister.onClick(enterpriseFileEntity,position);
                    String pic = enterpriseFileEntity.getUrl();//ApiConstants.BASE_URL + "tdzFile/" + item.folderid + "/" + item.path.trim()//YLPoint.ylList.get(0).tdzmc
                    Intent intent = new Intent(context, PDFActivity.class);
                    intent.putExtra("pdf", pic.trim());
                    context.startActivity(intent);
                }
            });
            mholder.ivItemQiYeDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickLister.onDeleteClick(enterpriseFileEntity,position);
                }
            });
        }else if (AppCache.getInstance().getDuties()==0){
            mholder.ivItemQiYePhoto.setImageResource(R.drawable.icon_up_file);
            mholder.tvItemQiYePhotoName.setText("");
            mholder.ivItemQiYeDelete.setVisibility(View.GONE);
            mholder.ivItemQiYePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickLister.onClick(null,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (isUpdate){
            if (AppCache.getInstance().getDuties()==1){//?????????????????????????????????????????????
                return mList.size();
            }else {
                return mList.size()+1;
            }
        }else {
            return mList.size();
        }
    }
    public class QyglFileHolder extends RecyclerView.ViewHolder {
        ImageView ivItemQiYePhoto;
        TextView tvItemQiYePhotoName;
        ImageView ivItemQiYeDelete;
        public QyglFileHolder(@NonNull View itemView) {
            super(itemView);
            ivItemQiYePhoto = itemView.findViewById(R.id.iv_item_qi_ye_photo);
            tvItemQiYePhotoName = itemView.findViewById(R.id.tv_item_qi_ye_photo_name);
            ivItemQiYeDelete = itemView.findViewById(R.id.iv_item_qi_ye_delete);

        }
    }
    public interface OnQyglFileLinear{
        void onClick(EnterpriseFileEntity enterpriseFileEntity,int position);
        void onDeleteClick(EnterpriseFileEntity enterpriseFileEntity,int position);
    }


}
