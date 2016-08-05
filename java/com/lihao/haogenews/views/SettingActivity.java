package com.lihao.haogenews.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;

import com.lihao.haogenews.R;

/**
 * Created by Administrator on 2016/8/5.
 */
public class SettingActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    private AppCompatCheckBox showPicSwitcher;
    private AppCompatCheckBox downLoadSwitcher;
    private SharedPreferences mSp;
    private CoordinatorLayout mLayout;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        initViews();
        mToolbar.setTitle("设置选项");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        mSp = getSharedPreferences("user_setting", MODE_PRIVATE);
        mEditor = mSp.edit();
        mLayout = (CoordinatorLayout) findViewById(R.id.coo_layout);
        mToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        showPicSwitcher = (AppCompatCheckBox) findViewById(R.id.switch_pic);
        downLoadSwitcher = (AppCompatCheckBox) findViewById(R.id.switch_down);
        showPicSwitcher.setChecked(mSp.getBoolean("showpic", true));
        downLoadSwitcher.setChecked(mSp.getBoolean("download", true));
        showPicSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEditor.putBoolean("showpic", isChecked);
                mEditor.commit();
                Snackbar snackbar = Snackbar.make(mLayout, "                        重新启动应用程序生效!", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorSnakeBar));
                snackbar.show();
            }
        });
        downLoadSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEditor.putBoolean("download", isChecked);
                mEditor.commit();
                Snackbar snackbar = Snackbar.make(mLayout, "                        重新启动应用程序生效!", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorSnakeBar));
                snackbar.show();
            }
        });
    }
}
