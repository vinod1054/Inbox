package com.example.vinod.myapplication.model;

/**
 * Created by vinod on 30/9/15.
 */
public class NavDrawerItem {

        private boolean showNotify;
        private String title;

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NavDrawerItem() {

        }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }
}
