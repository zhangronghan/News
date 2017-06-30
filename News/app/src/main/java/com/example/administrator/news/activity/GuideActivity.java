package com.example.administrator.news.activity;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.news.R;
import com.example.administrator.news.base.BaseActivity;

/**
 * Created by Administrator on 2017/6/26.
 */
public class GuideActivity extends BaseActivity {
    private ImageView mIvImage;
    private Button mBtnTouch;
    private int index=0;
    private MediaPlayer mMediaPlayer;
    private boolean isStopAnimation=false;

    private int[] imageArray=new int[]{
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7
    };

    @Override
    public int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    public void initData() {
        startAnimation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        mMediaPlayer=MediaPlayer.create(this,R.raw.new_version);

        mMediaPlayer.setLooping(true);           // 循环播放
        mMediaPlayer.setVolume(1f,1f);       // 左声道音量 右声道音量
        mMediaPlayer.start();
    }

    @Override
    public void initLIstener() {
        mBtnTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                isStopAnimation=true;
                finish();
            }
        });
    }

    @Override
    public void initViews() {
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mBtnTouch = (Button) findViewById(R.id.btn_touch);
    }

    //开始动画
    private void startAnimation() {
        index++;
        index=index % imageArray.length;
        mIvImage.setImageResource(imageArray[index]);

        mIvImage.setScaleX(1.0f);
        mIvImage.setScaleY(1.0f);

        mIvImage.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(3000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(!isStopAnimation){
                            startAnimation();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mMediaPlayer !=null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer=null;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!isStopAnimation){
            startAnimation();
        }
    }
}
