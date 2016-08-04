package com.lihao.haogenews.model;

/**
 * Created by Administrator on 2016/8/2.
 */
public class PicBean {

    private String url;
    private int width;
    private int height;
    private int id;
    private String title;

    public PicBean(String url, String width, String height, String id, String title) {
        this.url = url;
        this.height = Integer.parseInt(height);
        this.width = Integer.parseInt(width);
        this.id = Integer.parseInt(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "url:" + url + "; id:" + id + "; title:" + title;
    }
}
