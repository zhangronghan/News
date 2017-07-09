package com.example.administrator.news.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.news.Listener.OnItemClickListener;
import com.example.administrator.news.R;
import com.example.administrator.news.entity.NewsInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyCollectViewHolder> implements View.OnClickListener{
    private List<NewsInfo> mInfoList;
    private int[] arr={Color.argb(200,255,0,0),Color.argb(120,255,0,0),
            Color.argb(200,0,0,255),Color.argb(150,0,255,0),Color.argb(100,50,50,50)};
    //设置点击Item事件
    private OnItemClickListener mOnItemClickListener=null;

    public MyCollectAdapter(List<NewsInfo> mInfoList){
        this.mInfoList =mInfoList;
    }

    @Override
    public MyCollectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect,parent,false);
        MyCollectViewHolder myViewHolder=new MyCollectViewHolder(view);

        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyCollectViewHolder holder, int position) {
        NewsInfo mNewsInfo=mInfoList.get(position);
        holder.tvTitle.setText(mNewsInfo.getNewsTitle());
        holder.tvUrl.setText(mNewsInfo.getNewsUrl());

        initHeader(holder,mNewsInfo);
        holder.itemView.setTag(position);
    }

    private void initHeader(MyCollectViewHolder holder, NewsInfo mNewsInfo) {
        String firstLetter=mNewsInfo.getNewsTitle().substring(0,1);
        holder.tvHeader.setText(firstLetter);
        GradientDrawable myGrad= (GradientDrawable) holder.tvHeader.getBackground();
        int num= ((int) (Math.random()*10))%arr.length;
        for(int i=0;i<arr.length ;i++){
            if(num==i){
                myGrad.setColor(arr[i]);
                return ;
            }
        }

    }


    @Override
    public int getItemCount() {
        return mInfoList ==null ? 0 : mInfoList.size();
    }



    public void refreshData(List<NewsInfo> newsInfos) {
        mInfoList=newsInfos;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener !=null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    //暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    class MyCollectViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHeader;
        public TextView tvTitle;
        public TextView tvUrl;

        public MyCollectViewHolder(View itemView) {
            super(itemView);

            tvHeader= (TextView) itemView.findViewById(R.id.tv_head);
            tvTitle= (TextView) itemView.findViewById(R.id.tv_title);
            tvUrl= (TextView) itemView.findViewById(R.id.tv_url);
        }

    }

}
