package com.example.administrator.news.activity;

import android.content.Intent;
import android.os.SystemClock;

import com.example.administrator.news.R;
import com.example.administrator.news.Utils.sharePreUtil;
import com.example.administrator.news.base.BaseActivity;

/**
 * Created by Administrator on 2017/6/26.
 */

public class StartActivity extends BaseActivity {
    @Override
    public int getLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                if(sharePreUtil.getFirstRun(StartActivity.this)){
                    enterGuideActivity();
                    sharePreUtil.setFirstRun(StartActivity.this,false);
                } else {
                    enterMainActivity();
                }
            }
        }).start();
    }

    private void enterMainActivity() {
        startActivity(new Intent(StartActivity.this,MainActivity.class));
        finish();
    }

    private void enterGuideActivity() {
        startActivity(new Intent(StartActivity.this,GuideActivity.class));
        finish();
    }

    @Override
    public void initLIstener() {

    }

    @Override
    public void initViews() {

    }
}
