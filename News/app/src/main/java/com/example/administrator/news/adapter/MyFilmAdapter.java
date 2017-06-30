package com.example.administrator.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.news.R;
import com.example.administrator.news.bean.VideoBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2017/6/29.
 */

public class MyFilmAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context mContext;
    private List<VideoBean.ResultBean> mVideoList;

    public MyFilmAdapter(Context mContext,List<VideoBean.ResultBean> mVideoList){
        this.mContext=mContext;
        this.mVideoList=mVideoList;
    }

    //ViewHolder: 避免每次都findViewById查找子控件  提高列表滑动的流畅时间  不过空间会比ListView大一点 以空间换时间
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film,parent,false);
        MyViewHolder viewHolder= new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mVideoList == null ? 0 : mVideoList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final VideoBean.ResultBean dataBean=mVideoList.get(position);
        final ImageView ivPlay=holder.ivPlay;
        final ImageView ivBre=holder.ivBre;
        final JCVideoPlayer mJCVideoPlayer= holder.mJCVideoPlayer;

        holder.tvTitle.setText(dataBean.getTitle());
        holder.tvTime.setText(dataBean.getLength()/60 +"分"+dataBean.getLength() % 60+"秒");
        holder.tvCount.setText(String.valueOf(dataBean.getSizeSD()));
        Picasso.with(mContext).load(dataBean.getCover()).into(holder.ivBre);
        mJCVideoPlayer.setUp(dataBean.getMp4_url(),dataBean.getTitle());

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPlay.setVisibility(View.GONE);
                ivBre.setVisibility(View.GONE);
            }
        });

    }


}
