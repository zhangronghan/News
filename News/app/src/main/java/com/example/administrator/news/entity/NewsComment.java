package com.example.administrator.news.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewsComment extends BmobObject{
    String username;
    String newsTitle;
    String MyComment;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getMyComment() {
        return MyComment;
    }

    public void setMyComment(String myComment) {
        MyComment = myComment;
    }
}
