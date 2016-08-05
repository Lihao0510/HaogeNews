package com.lihao.haogenews.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

import com.lihao.haogenews.R;
import com.lihao.haogenews.utils.Constants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private long firstTime = 0L;
    private FrameLayout mFrameLayout;
    private SwipeRefreshLayout sr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        loadLolNewsFragment();
    }

    private void loadMainFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment()).commit();
    }

    private void loadLolNewsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new LoLFragment()).commit();
    }

    private void loadPicFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new PicFragment()).commit();
    }

    private void loadJiongtuFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new JiongtuFragment()).commit();
    }

    private void loadWallPaperFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new WallPaperFragment()).commit();
    }

    public void initViews() {

        mFrameLayout = (FrameLayout) findViewById(R.id.fl_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(mFrameLayout, "                        暂时没有可用程序!", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorSnakeBar));
                snackbar.show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        sr.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMainFragment();
                sr.setRefreshing(false);
            }
        });
    }

    public void setSwipeEnable(boolean enable) {
        sr.setEnabled(enable);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                //Toast.makeText(this, "再按一次退出程序!", Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(mFrameLayout, "                        再按一次退出程序!", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorSnakeBar));
                snackbar.show();
                firstTime = secondTime;
            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_warning) {
            Log.d(Constants.APP_NAME, "设置!");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("作者 : 李昊");
            builder.setMessage("QQ:591353643\n" +
                    "邮箱:lihao19910510@sina.com\n" +
                    "本程序仅供学习交流\n" +
                    "所使用资源均来自互联网\n" +
                    "若本程序侵犯了您的权益\n" +
                    "请联系本人,我会立刻修改相关内容");
            builder.setPositiveButton("确定", null);
            builder.show();
            return true;
        } else if (id == R.id.action_settings) {
            Log.d(Constants.APP_NAME, "其他!");
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Log.d(Constants.APP_NAME, "第一个模块!");
            loadMainFragment();
            getSupportActionBar().setTitle("今日头条");
        } else if (id == R.id.nav_gallery) {
            Log.d(Constants.APP_NAME, "第二个模块!");
            loadLolNewsFragment();
            getSupportActionBar().setTitle("LOL新闻");
        } else if (id == R.id.nav_slideshow) {
            Log.d(Constants.APP_NAME, "第三个模块!");
            loadPicFragment();
            getSupportActionBar().setTitle("COS画廊");
        } else if (id == R.id.nav_manage) {
            Log.d(Constants.APP_NAME, "第四个模块!");
            loadJiongtuFragment();
            getSupportActionBar().setTitle("LOL囧图");
        } else if (id == R.id.nav_share) {
            Log.d(Constants.APP_NAME, "第五个模块!");
            loadWallPaperFragment();
            getSupportActionBar().setTitle("精美壁纸");
        } else if (id == R.id.nav_send) {
            Log.d(Constants.APP_NAME, "第六个模块!");
        } else if (id == R.id.nav_other) {
            Log.d(Constants.APP_NAME, "第七个模块!");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
