package com.android.newsapp;

/**
 * Created by user on 6/10/2018.
 */

public class News {

    private String sectionName;
    private String title;
    private String publicationDate;
    private String url;

    public News(String newsTitle, String sectionName, String publicationDate, String url) {

        this.sectionName = sectionName;
        this.title = newsTitle;
        this.publicationDate = publicationDate;
        this.url = url;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getTitle() {
        return title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getUrl() {
        return url;
    }
}
