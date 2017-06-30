package com.example.administrator.news.activity;


import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.news.R;
import com.example.administrator.news.base.BaseActivity;
import com.example.administrator.news.cn.sharesdk.onekeyshare.OnekeyShare;
import com.example.administrator.news.entity.MyData;
import com.example.administrator.news.entity.NewsComment;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2017/6/28.
 */

public class mainWebActivity extends BaseActivity {
    private WebView mWebView;
    private String Url;
    private String title;
    private Toolbar mToolbar;
    private EditText edtComment;
    private ImageView mIvShare;
    private TextView mTvSend;
    private ProgressBar mProgressBar;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_web_content;
    }

    @Override
    public void initData() {
        Url=getIntent().getStringExtra(MyData.WEB_URL);
        title=getIntent().getStringExtra(MyData.NEWS_TITLE);
        mWebView.loadUrl(Url);
        initToolBar();

        initSaveComment();

    }

    private void initSaveComment() {
        NewsComment comment=new NewsComment();
        comment.setMyComment(edtComment.getText().toString());
//        comment.setNewsTitle();

    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);

        mToolbar.setTitle("ToolBar");
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //================Toolbar右上角弹出菜单(begin)=======================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_popup_item,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_collect) {
            showToast("收藏");
            return true;
        }
        switch (item.getItemId()){
            case R.id.item_collect:
                showToast("收藏");
            break;

            case R.id.item_share:
                showToast("分享");
            break;

            case R.id.item_dark:
                showToast("夜间模式");
            break;

        }

        return super.onOptionsItemSelected(item);
    }
    //================Toolbar右上角弹出菜单(end)=========================

    @Override
    public void initLIstener() {

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                mWebView.loadUrl(s);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        edtComment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    // 此处为得到焦点时的处理内容
                    mIvShare.setVisibility(View.GONE);
                    mTvSend.setVisibility(View.VISIBLE);
                } else {
                    // 此处为失去焦点时的处理内容
                    mIvShare.setVisibility(View.VISIBLE);
                    mTvSend.setVisibility(View.GONE);
                }
            }
        });

        mIvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnekeyShare oks=new OnekeyShare();
                oks.setTitle(title);
                oks.setTitleUrl(Url);
                oks.show(mainWebActivity.this);
            }
        });

    }

    @Override
    public void initViews() {
        mWebView= (WebView) findViewById(R.id.web_view);
        mToolbar= (Toolbar) findViewById(R.id.tool_bar);
        edtComment = (EditText) findViewById(R.id.edt_comment);
        mIvShare = (ImageView) findViewById(R.id.iv_share);
        mTvSend = (TextView) findViewById(R.id.tv_send);
        mProgressBar= (ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
    }

}
