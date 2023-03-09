package com.jymj.zhglxt.zjd.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @package com.jymj.projectmanager.ui.main.adapter
 * @fileName FragmentPagerAdapter
 * @date 2019/3/610:25
 * @name qzw
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList;
    private String[] titleList;
//, ArrayList<String> titleList

/*
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
*/



    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] titleList) {
        super(fm);
        this.fragmentList=list;
        this.titleList=titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }

  /*  @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return super.isViewFromObject(view, object);
//        return view ==((Fragment) object).getView();
    }*/
}
