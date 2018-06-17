package com.android.newsapp;

/**
 * Created by user on 6/10/2018.
 */

public class News {

    private String category;
    private String title;
    private long timeInMilliseconds;
    private String url;

    public News (String category, String newsTitle, long timeInMilliseconds, String url){

        this.category = category;
        this.title = newsTitle;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
