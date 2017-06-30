package com.example.administrator.news.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.MyFilmAdapter;
import com.example.administrator.news.base.BaseFragment;
import com.example.administrator.news.base.URLManager;
import com.example.administrator.news.bean.VideoBean;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Administrator on 2017/6/27.
 */

public class FilmFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private VideoBean mVideoBean;
    private MyFilmAdapter mAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_film;
    }

    @Override
    public void initData() {
        getDataFromServer();

    }



    @Override
    public void initListener() {

    }

    @Override
    public void initViews() {
        mRecyclerView= (RecyclerView) super.rootView.findViewById(R.id.recyclerView);

    }

    private void initRecyclerView() {
        mAdapter=new MyFilmAdapter(getActivity(),mVideoBean.getResult());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void getDataFromServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = URLManager.getFlimUrl();
                    HttpUtils utils = new HttpUtils();
                    utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String json = responseInfo.result;
                            json = json.replace("V9LG4B3A0", "result");
                            System.out.println("-------video" + json);
                            Gson gson = new Gson();
                            mVideoBean=gson.fromJson(json,VideoBean.class);

                            initRecyclerView();
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


}
