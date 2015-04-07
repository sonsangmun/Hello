package com.example.smson.hello.parsing.xml;

/**
 * Created by sangmun on 2015-03-30.
 */
public class AndroidPubInfo {
    String title;
    String url;

    public AndroidPubInfo() {
        title = "";
        url = "";
    }

    public AndroidPubInfo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
