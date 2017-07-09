package com.example.administrator.news.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toast mToast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        initViews();
        initListener();
        initData();

    }


    public abstract int getLayoutRes();

    /** 初始化数据 */
    public abstract void initData();

    /**设置控件的监听器 */
    public abstract void initListener();

    /**  查找布局中的子控件 */
    public abstract void initViews();

    /** 吐丝 */
    public void showToast(String msg){
        if(mToast == null){
            mToast=Toast.makeText(this,"",Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }


}
