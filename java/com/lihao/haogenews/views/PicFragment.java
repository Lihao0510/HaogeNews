package com.lihao.haogenews.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lihao.haogenews.R;
import com.lihao.haogenews.adapters.PicAdapter;
import com.lihao.haogenews.model.PicBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class PicFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PicAdapter mAdapter;
    private List<PicBean> mList;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.pic_recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new PicAdapter(mActivity,mList);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        ((MainActivity) mActivity).setSwipeEnable(false);
        JsonObjectRequest picRequest = new JsonObjectRequest(Request.Method.GET, "http://box.dwstatic.com/apiAlbum.php?action=l&albumsTag=beautifulWoman&p=1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray picArray = response.getJSONArray("data");
                    for (int i = 0; i < picArray.length(); i++) {
                        JSONObject picObj = picArray.getJSONObject(i);
                        mList.add(new PicBean(picObj.getString("coverUrl"), picObj.getString("coverWidth"), picObj.getString("coverHeight"), picObj.getString("galleryId")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
