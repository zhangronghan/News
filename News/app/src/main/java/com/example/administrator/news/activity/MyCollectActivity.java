package com.example.administrator.news.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.news.Listener.OnItemClickListener;
import com.example.administrator.news.R;
import com.example.administrator.news.adapter.MyCollectAdapter;
import com.example.administrator.news.base.BaseActivity;
import com.example.administrator.news.entity.MyData;
import com.example.administrator.news.entity.NewsInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/7/7.
 */

public class MyCollectActivity extends BaseActivity{
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private MyCollectAdapter mMyCollectAdapter;
    private List<NewsInfo> mNewsInfos=new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.mycollect_activity;
    }

    @Override
    public void initData() {
        getDataFromBmob();

    }

    //从bmob中获取数据
    private void getDataFromBmob() {
        BmobQuery<NewsInfo> bmobQuery=new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<NewsInfo>() {
            @Override
            public void done(List<NewsInfo> list, BmobException e) {
                if(e==null){
                    if(list!=null){
                        mNewsInfos=list;
                        mMyCollectAdapter.refreshData(mNewsInfos);
                    } else {
                        Log.e("AAA","数据为空");
                    }
                } else {
                    Log.e("AAA","查询失败");
                }
            }
        });

    }

    private void initRecyclerView() {
        mMyCollectAdapter=new MyCollectAdapter(mNewsInfos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyCollectAdapter);
    }

    @Override
    public void initListener() {

        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.web_back1);
        mToolBar.setTitle("我的收藏");
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mMyCollectAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(MyCollectActivity.this,mainWebActivity.class);
                intent.putExtra(MyData.WEB_URL,mNewsInfos.get(position).getNewsUrl());
                intent.putExtra(MyData.NEWS_TITLE,mNewsInfos.get(position).getNewsTitle());
                startActivity(intent);
            }
        });

    }


    //================Toolbar右上角弹出菜单(begin)=======================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_collect_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //================Toolbar右上角弹出菜单(end)=======================\

    @Override
    public void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        initRecyclerView();
    }


}
