package com.example.vinod.myapplication.service;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


import com.example.vinod.myapplication.model.InboxRow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by vinod on 30/9/15.
 */
public class ReadSms {
    Context context;

    public ReadSms(){

    }

    public ReadSms(Context context){
        this.context=context;
    }


    public List<InboxRow> getSmsList(Cursor cur){
        List<InboxRow> data=new ArrayList<InboxRow>();
        String sms = "";
        while (cur.moveToNext()){
            long timeStamp=Long.parseLong(cur.getString(1));
            String time=new TimeService().getTime(timeStamp);
            InboxRow inboxRow = new InboxRow(cur.getString(0),cur.getString(2),1,time);
            try{
                data.add(inboxRow);
            }
            catch (Exception e){
                System.out.println(e.toString());
            }

        }

        return data;
    }
}
