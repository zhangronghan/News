package com.example.administrator.news.activity;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.administrator.news.MyView.MyViewPager;
import com.example.administrator.news.R;
import com.example.administrator.news.adapter.MyMainViewpagerAdapter;
import com.example.administrator.news.base.BaseActivity;
import com.example.administrator.news.fragment.FilmFragment;
import com.example.administrator.news.fragment.NewFragment;
import com.example.administrator.news.fragment.ReachFragment;
import com.example.administrator.news.fragment.ReadFragment;
import com.example.administrator.news.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private MyViewPager mViewpager;
    private RadioGroup mRadioGroup;
    private MyMainViewpagerAdapter mViewpagerAdapter;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mFragmentList.add(new NewFragment());
        mFragmentList.add(new FilmFragment());
        mFragmentList.add(new ReadFragment());
        mFragmentList.add(new ReachFragment());
        mFragmentList.add(new SettingFragment());

        mViewpager.setAdapter(mViewpagerAdapter);
        mViewpager.setOffscreenPageLimit(5);


    }



    @Override
    public void initLIstener() {
        /**点击radioButton改变viewpager*/
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tab_new:
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.tab_film:
                        mViewpager.setCurrentItem(1);
                        break;
                    case R.id.tab_read:
                        mViewpager.setCurrentItem(2);
                        break;
                    case R.id.tab_rearch:
                        mViewpager.setCurrentItem(3);
                        break;
                    case R.id.tab_setting:
                        mViewpager.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });

        /*滑动viewpager改变radioButton*/
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.tab_new);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.tab_film);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.tab_read);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.tab_rearch);
                        break;
                    case 4:
                        mRadioGroup.check(R.id.tab_setting);
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initNavigationView();

        initToolBar();


    }

    /**初始化ToolBar*/
    private void initToolBar() {
        //使用ToolBar代替ActionBar
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("广交院");
        mToolbar.setTitleTextColor(Color.WHITE);

        //显示标题栏左上角的返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mNavigationView);
            }
        });


    }


    //=============================ToolBar右上角弹出菜单(begin)===============================
    //创建右上角弹出菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu_top_right,menu);
        return true;
    }

    //右上角弹出菜单点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popup1:
                showToast("popup1");
            break;

            case R.id.popup2:
                showToast("popup2");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //=============================ToolBar右上角弹出菜单(end)===============================



    /**侧滑菜单，点击监听事件*/
    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_1:
                        showToast("1");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.item_2:
                        showToast("2");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.item_3:
                        showToast("3");
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.item_4:
                        showToast("4");
                        mDrawerLayout.closeDrawers();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void initViews() {
        mViewpager = (MyViewPager) findViewById(R.id.viewpager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar= (Toolbar) findViewById(R.id.tool_bar);

        mViewpagerAdapter=new MyMainViewpagerAdapter(getSupportFragmentManager(),mFragmentList);
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,0,0);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
    }


}
