package com.lihao.haogenews.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lihao.haogenews.MyApplication;
import com.lihao.haogenews.R;
import com.lihao.haogenews.adapters.PicAdapter;
import com.lihao.haogenews.model.PicBean;
import com.lihao.haogenews.utils.ArrayUtil;
import com.lihao.haogenews.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class JiongtuFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PicAdapter mAdapter;
    private List<PicBean> mList;
    private int pageCount;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        pageCount = 1;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.pic_recyclerview);
        mList = new ArrayList<>();
        ((MainActivity) mActivity).setSwipeEnable(false);
        initData();
        //initAdapter();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*mList = new ArrayList<>();
        initData();
        initAdapter();*/
    }

    private void initAdapter() {
        Log.d("Lihao", "initAdapter");
        mAdapter = new PicAdapter(mActivity, mList);
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int[] positions = new int[manager.getSpanCount()];
                manager.findLastCompletelyVisibleItemPositions(positions);
                int position = ArrayUtil.finMaxCount(positions);
                int visibleCount = manager.getChildCount();
                int totalCount = manager.getItemCount();
                Log.d("Lihao", "position:" + position + "visibleCount:" + visibleCount + "totalCount:" + totalCount);
                if (visibleCount > 0 && position >= (totalCount - 1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    addData();
                }
            }
        });
    }

    private void addData() {
        Log.d("Lihao", "addData");

        JsonObjectRequest picRequest = new JsonObjectRequest(Request.Method.GET, Constants.JIONGTU_URL + pageCount, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    pageCount++;
                    JSONArray picArray = response.getJSONArray("data");
                    for (int i = 0; i < picArray.length(); i++) {
                        JSONObject picObj = picArray.getJSONObject(i);
                        mList.add(new PicBean(picObj.getString("coverUrl"), picObj.getString("coverWidth"), picObj.getString("coverHeight"), picObj.getString("galleryId"), picObj.getString("title")));
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Lihao", mList.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        picRequest.setTag("Lihao");
        MyApplication.getVolleyQueue().add(picRequest);
    }


    @Override
    protected void initData() {
        Log.d("Lihao", "initData");

        JsonObjectRequest picRequest = new JsonObjectRequest(Request.Method.GET, Constants.JIONGTU_URL + pageCount, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    pageCount++;
                    JSONArray picArray = response.getJSONArray("data");
                    for (int i = 0; i < picArray.length(); i++) {
                        JSONObject picObj = picArray.getJSONObject(i);
                        mList.add(new PicBean(picObj.getString("coverUrl"), picObj.getString("coverWidth"), picObj.getString("coverHeight"), picObj.getString("galleryId"), picObj.getString("title")));
                        initAdapter();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Lihao", mList.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        picRequest.setTag("Lihao");
        MyApplication.getVolleyQueue().add(picRequest);
    }
}