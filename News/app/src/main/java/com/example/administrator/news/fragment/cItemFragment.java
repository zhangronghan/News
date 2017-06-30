package com.example.administrator.news.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.administrator.news.R;
import com.example.administrator.news.activity.mainWebActivity;
import com.example.administrator.news.adapter.MyListViewAdapter;
import com.example.administrator.news.base.BaseFragment;
import com.example.administrator.news.base.URLManager;
import com.example.administrator.news.bean.NewsBean;
import com.example.administrator.news.entity.MyData;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class cItemFragment extends BaseFragment {
    private String channelId;
    private ListView mListView;
    private MyListViewAdapter mAdapter;
    private SpringView mSpringView;
    private NewsBean newsData;
    private List<NewsBean.ResultBean> mResultBeen;
    private View mHeaderView;
    private int pageNo=1;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_citem;
    }

    @Override
    public void initViews() {
        mListView = (ListView) super.rootView.findViewById(R.id.listView);
        mSpringView = (SpringView) super.rootView.findViewById(R.id.springView);
        mAdapter = new MyListViewAdapter(getContext(), null);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index=position;
                if(mListView.getHeaderViewsCount()>0){
                    //列表添加了头部后，第一条新闻的索引是从1开始，所以要减1
                    index=index-1;
                }
                NewsBean.ResultBean news = newsData.getResult().get(index);
                Intent intent = new Intent(getContext(), mainWebActivity.class);
                intent.putExtra(MyData.WEB_URL, news.getUrl());
                intent.putExtra(MyData.NEWS_TITLE, news.getTitle());
                startActivity(intent);
            }
        });
        initSpringView();

    }

    @Override
    public void initData() {
        getDataFromServer(true);
    }

    private void getDataFromServer(final boolean refresh) {
        if (refresh)  // 如果是下拉刷新
            pageNo = 1;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = URLManager.getUrl(channelId,pageNo,10);
                    HttpUtils utils = new HttpUtils();
                    utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String json = responseInfo.result;
                            json = json.replace(channelId, "result");
                            System.out.println("-------json" + json);
                            Gson gson = new Gson();
                            newsData = gson.fromJson(json, NewsBean.class);
                            mResultBeen=newsData.getResult();
                            if(refresh){
                                showListView(mResultBeen);
                            } else {
                                addData(mResultBeen);
                            }
                            //实现轮播图
                            showDatas(newsData);
                            pageNo++;

                            mSpringView.onFinishFreshAndLoad();
                        }
                        @Override
                        public void onFailure(HttpException error, String msg) {
                            error.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("cItemFragment", "网络出错");
                }
            }
        }).start();
    }

    //更新列表数据
    private void showListView(List<NewsBean.ResultBean> resultBean) {
        mAdapter.getDataToListView(resultBean);
    }

    //添加数据
    public void addData(List<NewsBean.ResultBean> resultBean){
        mResultBeen.addAll(resultBean);
        mAdapter.addDataToListView(resultBean);
    }


    private void showDatas(NewsBean newsData) {
        if(mListView.getHeaderViewsCount()>0){
            mListView.removeHeaderView(mHeaderView);
        }

        if (newsData == null || newsData.getResult() == null || newsData.getResult().size() == 0) {
            System.out.println("-----没有从服务器获取数据");
            return;
        }

        //显示轮播图
        List<NewsBean.ResultBean.AdsBean> ads = newsData.getResult().get(0).getAds();

        if (ads != null && ads.size() > 0) {

            mHeaderView = View.inflate(getActivity(), R.layout.list_header, null);
            SliderLayout sliderLayout = (SliderLayout) mHeaderView.findViewById(R.id.slider_Layout);

            for (int i = 0; i < ads.size(); i++) {
                NewsBean.ResultBean.AdsBean adsBean = ads.get(i);
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView.description(adsBean.getTitle()).image(adsBean.getImgsrc());
                //添加子界面
                sliderLayout.addSlider(textSliderView);
                // 设置点击事件
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showToast(slider.getDescription());
                    }
                });
            }
            mListView.addHeaderView(mHeaderView);
        }

    }


    /**
     * 下拉刷新
     * 上拉加载
     * */
    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(getContext()));
        mSpringView.setFooter(new DefaultFooter(getContext()));

        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                showToast("更新成功");
                getDataFromServer(true);
            }

            @Override
            public void onLoadmore() {
                showToast("加载成功");
                getDataFromServer(false);
            }


        });

    }

    public String getChannId() {
        return channelId;
    }

    public void setChannId(String channelId) {
        this.channelId = channelId;
    }


}
