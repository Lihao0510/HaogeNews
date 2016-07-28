package com.lihao.haogenews.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihao.haogenews.utils.Constants;

/**
 * Created by Administrator on 2016/7/28.
 */
public abstract class BaseFragment extends Fragment{
    protected Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
        Log.d(Constants.APP_NAME,"A Fragment has bean Destroyed!!!");
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
