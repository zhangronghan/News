package com.example.administrator.news.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment {
    public View rootView;
    private Toast mToast;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(getLayoutRes(),container,false);
        }

        initViews();
        initListener();
        initData();

        return rootView;
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
            mToast= Toast.makeText(getActivity(),"",Toast.LENGTH_LONG);
        }
        mToast.setText(msg);
        mToast.show();
    }


}
