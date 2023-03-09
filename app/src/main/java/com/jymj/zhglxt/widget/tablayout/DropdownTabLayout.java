package com.jymj.zhglxt.widget.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.jymj.zhglxt.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by jiaojing on 2017/12/11.
 */

public class DropdownTabLayout extends TabLayout {
    private String TAG = getClass().getSimpleName();
    private Context mContext;

    public DropdownTabLayout(Context context) {
        this(context, null);
    }

    public DropdownTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    private SparseArray sparseArray = new SparseArray<Integer>();

    public void setUpTitle( List<List<String>> titleList){
        for (int i=0; i< titleList.size(); i++){
//            addTab(newTab().setText(titleList.get(i).get(0)));//默认=取第一个作为首次加载显示的tab名字
            getTabAt(i).setCustomView(getTabView(i, titleList.get(i)));
            sparseArray.put(i, 0);
        }
    }

  /*  @Override
    public void selectTab(@Nullable Tab tab) {
        super.selectTab(tab);

        TextView viewById = tab.view.findViewById(R.id.tab_text);
        viewById.setTextColor(Color.WHITE);
    }*/


    /**
     * 得到自定义的tabItem
     * @param position
     * @return
     */
    public View getTabView(final int position, final List<String> titleList){
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_tab, null);
        TextView tabText = view.findViewById(R.id.tab_text);
//        tabText.setTextColor(Color.parseColor("#999999"));
//        tabText.setTextColor(getResources().getColor(R.color.cb_text_gray));
        tabText.setText(titleList.get(0));
        View tab_img = view.findViewById(R.id.tab_img);
        if(titleList.size() == 1) {
            tab_img.setVisibility(GONE);
        }else {
            tabText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DropdownTabLayout.this.getTabAt(position).isSelected()){
                        showPopupwindow(tabText,position,view, titleList);
                    }else {
                        DropdownTabLayout.this.getTabAt(position).select();
                    }
                }
            });
        }



        return view;
    }
    public void setTextViewColor(final int position){

    }

    @Override
    public boolean isSelected() {

        return super.isSelected();
    }

    private PopupWindow popupWindow;
    private void showPopupwindow(final TextView tabText, final int tabPosition, View v, final List<String> titleList) {
        if(popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }else {
            WindowManager wm  = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.choose_popupwindow, null);
            RecyclerView listview = (RecyclerView) inflate.findViewById(R.id.listview);
            listview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            listview.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_dance_category,titleList) {
                @Override
                protected void convert(BaseViewHolder helper, String item) {
                    helper.setText(R.id.dance_name,item);
                    ImageView ivSelected = helper.getView(R.id.iv_selected);
                    if(helper.getAdapterPosition() == (int)sparseArray.get(tabPosition)) {
                        ivSelected.setVisibility(View.VISIBLE);
                    }else{
                        ivSelected.setVisibility(View.INVISIBLE);
                    }
                    helper.itemView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            sparseArray.setValueAt(tabPosition, helper.getAdapterPosition());
                            DropdownTabLayout.this.getTabAt(tabPosition).select();
//                            mViewPager.setCurrentItem(tabPosition);
                            tabText.setText(titleList.get(helper.getAdapterPosition()));

                            onTabSelectedListener.selected(tabPosition, helper.getAdapterPosition());

                            //关闭窗口
                            popupWindow.dismiss();
                        }
                    });
                }


            });
            /*listview.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return titleList.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    View view;
                    if(convertView == null) {
                        view = LayoutInflater.from(mContext).inflate(R.layout.item_dance_category,null);
                    }else {
                        view = convertView;
                    }
                    final TextView name= (TextView) view.findViewById(R.id.dance_name);

                    final ImageView ivSelected= (ImageView) view.findViewById(R.id.iv_selected);
                    name.setText(titleList.get(position));

                    if(position == (int)sparseArray.get(tabPosition)) {
                        ivSelected.setVisibility(View.VISIBLE);
                    }else{
                        ivSelected.setVisibility(View.INVISIBLE);
                    }


                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sparseArray.setValueAt(tabPosition, position);
                            DropdownTabLayout.this.getTabAt(tabPosition).select();
//                            mViewPager.setCurrentItem(tabPosition);
                            tabText.setText(titleList.get(position));

                            onTabSelectedListener.selected(tabPosition, position);

                            //关闭窗口
                            popupWindow.dismiss();

                        }
                    });

                    return view;
                }
            });*/

            popupWindow = new PopupWindow(inflate, wm.getDefaultDisplay().getWidth(), ViewPager.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);

            int[] location = new int[2];
            v.getLocationOnScreen(location);
//            int x = wm.getDefaultDisplay().getWidth()/4 * 2;
            int x = 0;
            int y = location[1] + v.getHeight();
            popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, x, y);
        }
    }


    private ViewPager mViewPager;
    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        mViewPager = viewPager;
    }

    public interface OnTabSelectedListener{
        void selected(int tabPosition, int listPosition);
    }
    private OnTabSelectedListener onTabSelectedListener;

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener){
        this.onTabSelectedListener = onTabSelectedListener;
    }



}
