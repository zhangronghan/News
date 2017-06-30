package com.example.administrator.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.news.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2017/6/29.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivBre;
    public ImageView ivPlay;
    public TextView tvTitle;
    public TextView tvTime;
    public TextView tvCount;
    public JCVideoPlayer mJCVideoPlayer;

    public MyViewHolder(View itemView) {
        super(itemView);

        ivBre= (ImageView) itemView.findViewById(R.id.iv_bre);
        ivPlay= (ImageView) itemView.findViewById(R.id.iv_play);
        tvTitle= (TextView) itemView.findViewById(R.id.tv_title);
        tvTime= (TextView) itemView.findViewById(R.id.tv_time);
        tvCount= (TextView) itemView.findViewById(R.id.tv_count);
        mJCVideoPlayer= (JCVideoPlayer) itemView.findViewById(R.id.videoPlayer);
    }
}
