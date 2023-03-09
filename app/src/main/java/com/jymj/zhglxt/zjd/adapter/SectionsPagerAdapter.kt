package com.jymj.zhglxt.zjd.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(private val fragList: ArrayList<Fragment>,private val tabs: ArrayList<String>, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // 顶部有多少个tab，就会运行多少次（初次会加载第一第二，第三第四要点第三个tab才会运行）
        /*******新建Fragment******/
//        Log.d("WY+","运行第几次："+(position+1))

        /*when (tabs[position]) {
            "用地" -> {
                return ZjdYdFragment.newInstance(position + 1)
            }
            "翻建" -> {
                return ZjdFjFragment.newInstance(position + 1)
            }
            "巡查" -> {
                return ZjdXcFragment.newInstance(position + 1)
            }
            "出租" -> {
                return ZjdLdrkFragment.newInstance(position + 1)
            }
        }*/
        return fragList[position]//RjhjFragment.newInstance(position + 1)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    override fun getCount(): Int {
        // 顶部显示多少个页，不要超过TAB_TITLES的总数.
        return tabs.size
    }
}