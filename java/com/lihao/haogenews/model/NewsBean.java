package com.lihao.haogenews.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.lihao.haogenews.utils.ArrayUtil;

/**
 * Created by Administrator on 2016/7/29.
 */
public class NewsBean implements Parcelable {

    public String title;
    public String picUrl;
    public String url;
    public String content;

    public String getContent() {
        return content;
    }

    public NewsBean() {

    }

    public NewsBean(String title, String picUrl, String url, String content) {
        this.title = title;
        this.picUrl = picUrl;
        this.url = ArrayUtil.getSubUrl(url);
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Title:" + title + "; Url:" + url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(picUrl);
    }

    public static final Parcelable.Creator<NewsBean> CREATOR = new Parcelable.Creator<NewsBean>() {

        @Override
        public NewsBean createFromParcel(Parcel source) {
            NewsBean newsBean = new NewsBean();
            newsBean.title = source.readString();
            newsBean.url = source.readString();
            newsBean.picUrl = source.readString();
            return newsBean;
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };
}
