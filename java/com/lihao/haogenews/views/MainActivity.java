package com.lihao.haogenews.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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
        loadMainFragment();


    }

    private void loadMainFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment()).commit();
    }

    private void loadLolNewsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new LoLFragment()).commit();
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

    public void setSwipeEnable(boolean enable){
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
        if (id == R.id.action_settings) {
            Log.d(Constants.APP_NAME, "设置!");
            return true;
        } else if (id == R.id.action_warning) {
            Log.d(Constants.APP_NAME, "其他!");
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
        } else if (id == R.id.nav_gallery) {
            Log.d(Constants.APP_NAME, "第二个模块!");
            loadLolNewsFragment();
        } else if (id == R.id.nav_slideshow) {
            Log.d(Constants.APP_NAME, "第三个模块!");
        } else if (id == R.id.nav_manage) {
            Log.d(Constants.APP_NAME, "第四个模块!");
        } else if (id == R.id.nav_share) {
            Log.d(Constants.APP_NAME, "第五个模块!");
        } else if (id == R.id.nav_send) {
            Log.d(Constants.APP_NAME, "第六个模块!");
        }else if (id == R.id.nav_other) {
            Log.d(Constants.APP_NAME, "第七个模块!");
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
