package com.example.administrator.news.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.MyNewsViewpagerAdapter;
import com.example.administrator.news.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class NewFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private MyNewsViewpagerAdapter mViewpagerAdapter;
    private List<Fragment> mFragmentList=new ArrayList<>();
    private String[] mTitles=new String[]{"头条","社会","科技","财经","体育","汽车"};
    // 新闻类别类别id：
    public final String[] channelIds = new String[] {
            "T1348647909107",   // 头条
            "T1348648037603",   // 社会
            "T1348649580692",   // 科技
            "T1348648756099",   // 财经
            "T1348649079062",   // 体育
            "T1348654060988",   // 汽车
    };


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_new;
    }

    @Override
    public void initData() {
        for(int i=0;i<mTitles.length;i++){
            cItemFragment fragment=new cItemFragment();
            fragment.setChannId(channelIds[i]);
            mFragmentList.add(fragment);
        }

        mViewpagerAdapter=new MyNewsViewpagerAdapter(getChildFragmentManager(),mFragmentList,mTitles);
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(mViewpagerAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initViews() {
        mTabLayout= (TabLayout) super.rootView.findViewById(R.id.tabLayout);
        mViewpager= (ViewPager) super.rootView.findViewById(R.id.viewpager);
    }
}
