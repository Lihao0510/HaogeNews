package com.lihao.haogenews.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.lihao.haogenews.R;

/**
 * Created by Administrator on 2016/7/28.
 */
public class SplashActivity extends Activity {

    private ImageView splashImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initViews();
        initImage();
    }

    private void initImage() {
        // TODO Auto-generated method stub
        ScaleAnimation splashScale = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        splashScale.setFillAfter(true);
        splashScale.setDuration(3333);
        splashScale.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                Log.d("Lihao", "开始缩放!");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                Log.d("Lihao", "结束缩放!");
                startActivity();
            }

        });
        splashImageView.startAnimation(splashScale);
    }

    private void startActivity() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        // TODO Auto-generated method stub
        splashImageView = (ImageView) findViewById(R.id.splash_imageView);
    }
}
