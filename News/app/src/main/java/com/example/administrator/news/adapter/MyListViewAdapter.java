package com.example.administrator.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.news.R;
import com.example.administrator.news.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MyListViewAdapter extends BaseAdapter {
    private Context context;
    private List<NewsBean.ResultBean> mResultBeen;
    //列表项类型
    //一张图片
    private final int LIST_ITEM_1_IMAGE=0;
    //三张图片
    private final int LIST_ITEM_3_IMAGE=1;


    public MyListViewAdapter(Context context,List<NewsBean.ResultBean> mResultBeen){
        this.context=context;
        this.mResultBeen=mResultBeen;
    }

    @Override
    public int getCount() {
        return mResultBeen == null ? 0 : mResultBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsBean.ResultBean info= mResultBeen.get(position);

        int itemType=getItemViewType(position);

        /*一张图片*/
        if(itemType == LIST_ITEM_1_IMAGE){
            if(convertView == null){
                convertView= LayoutInflater.from(context).inflate(R.layout.list_item_01,parent,false);
            }
            ImageView ivImage= (ImageView) convertView.findViewById(R.id.iv_icon);
            TextView tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
            TextView tvSource= (TextView) convertView.findViewById(R.id.tv_source);
            TextView tvComment= (TextView) convertView.findViewById(R.id.tv_comment);

            Picasso.with(convertView.getContext()).load(info.getImgsrc()).into(ivImage);
            tvTitle.setText(info.getTitle());
            tvSource.setText(info.getSource());
            tvComment.setText(info.getReplyCount() +"跟帖");
        }else {
            /*三张图片*/
            if(convertView == null){
                convertView= LayoutInflater.from(context).inflate(R.layout.list_item_02,parent,false);
            }
            TextView tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
            TextView tvComment= (TextView) convertView.findViewById(R.id.tv_comment);
            ImageView imageView01= (ImageView) convertView.findViewById(R.id.iv_image1);
            ImageView imageView02= (ImageView) convertView.findViewById(R.id.iv_image2);
            ImageView imageView03= (ImageView) convertView.findViewById(R.id.iv_image3);

            tvTitle.setText(info.getTitle());
            tvComment.setText(info.getReplyCount() +"跟帖");
            try{
                Picasso.with(convertView.getContext()).load(info.getImgsrc()).into(imageView01);
                Picasso.with(convertView.getContext()).load(info.getImgextra().get(0).getImgsrc()).into(imageView02);
                Picasso.with(convertView.getContext()).load(info.getImgextra().get(1).getImgsrc()).into(imageView03);
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        return convertView;
    }

     //获取Item的类型
    //getViewTypeCount
    //getItemViewType 返回列表项属于哪一种类型的Item
    @Override
    public int getItemViewType(int position) {
        NewsBean.ResultBean item=mResultBeen.get(position);
        if(item.getImgextra() ==null || item.getImgextra().size()==0){
            return LIST_ITEM_1_IMAGE;
        } else {
            return LIST_ITEM_3_IMAGE;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 重置列表的所有的数据，并刷新列表显示
     * @param resultBean
     */
    public void getDataToListView(List<NewsBean.ResultBean> resultBean) {
        mResultBeen=resultBean;
        notifyDataSetChanged();
    }

    /** 追加数据，并刷新列表显示 */
    public void addDataToListView(List<NewsBean.ResultBean> resultBean) {
        this.mResultBeen.addAll(resultBean);
        notifyDataSetChanged();
    }
}
