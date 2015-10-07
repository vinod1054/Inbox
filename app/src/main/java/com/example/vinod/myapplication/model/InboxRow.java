package com.example.vinod.myapplication.model;

/**
 * Created by vinod on 30/9/15.
 */
public class InboxRow {
    String title;
    String subTiltle;
    int icon;
    String time;
    int rand;

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public InboxRow(){

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public InboxRow(String title,String subTiltle, int icon,String time){
        this.title=title;
        this.subTiltle=subTiltle;

        this.icon=icon;
        this.time=time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTiltle() {
        return subTiltle;
    }

    public void setSubTiltle(String subTiltle) {
        this.subTiltle = subTiltle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
