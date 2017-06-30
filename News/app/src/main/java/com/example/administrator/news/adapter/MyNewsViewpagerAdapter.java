package com.example.administrator.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MyNewsViewpagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> mFragmentList;

    public MyNewsViewpagerAdapter(FragmentManager fm, List<Fragment> mFragmentList,String[] titles) {
        super(fm);
        this.mFragmentList=mFragmentList;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
