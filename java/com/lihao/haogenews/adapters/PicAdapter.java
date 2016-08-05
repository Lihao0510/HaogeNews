package com.lihao.haogenews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lihao.haogenews.R;
import com.lihao.haogenews.model.PicBean;
import com.lihao.haogenews.views.PicBrowseActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class PicAdapter extends RecyclerView.Adapter<PicAdapter.MyViewHolder> {

    private Context mContext;
    private List<PicBean> mList;
    private int mWidth;

    public PicAdapter(Context context, List<PicBean> list) {
        mContext = context;
        mList = list;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth() * 5 / 11;
        Log.d("Lihao", "Adapter初始化完成!");
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pic_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d("Lihao", "Glide开始加载!" + mList.get(position).getUrl());
        int width = mList.get(position).getWidth();
        int height = mList.get(position).getHeight();
        holder.textView.setText(mList.get(position).getTitle());
        Glide.with(mContext).load(mList.get(position).getUrl()).override(mWidth, mWidth * height / width).dontAnimate().
                fitCenter().placeholder(R.drawable.loading).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,"点击了第"+position+"张图片",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, PicBrowseActivity.class);
                intent.putExtra("id", mList.get(position).getId());
                intent.putExtra("title",mList.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });
        Log.d("Lihao", "Glide加载完成!" + mList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        Log.d("Lihao", mList.size() + "getItemCount!");
        return mList.size();

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.pic_img);
            textView = (TextView) itemView.findViewById(R.id.pic_title);
        }
    }
}
