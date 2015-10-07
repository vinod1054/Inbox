package com.example.vinod.myapplication.service;

import android.util.Log;

import com.example.vinod.myapplication.constants.constants_strings;

import java.util.GregorianCalendar;

/**
 * Created by vinod on 4/10/15.
 */
public class TimeService {

    public String getTime(long timeStamp){
        GregorianCalendar calendar_current = new GregorianCalendar();
        GregorianCalendar calendar_previous = new GregorianCalendar();
        calendar_previous.setTimeInMillis(timeStamp);
        String time="";
        if(calendar_current.get(GregorianCalendar.YEAR)==calendar_previous.get(GregorianCalendar.YEAR) &&
                calendar_current.get(GregorianCalendar.MONTH)==calendar_previous.get(GregorianCalendar.MONTH) &&
                calendar_current.get(GregorianCalendar.DAY_OF_MONTH)==calendar_previous.get(GregorianCalendar.DAY_OF_MONTH)){
            Log.v("Time : ", "Hello");
            time+=calendar_previous.get(GregorianCalendar.HOUR)+":";
            int minutes=calendar_previous.get(GregorianCalendar.MINUTE);
            if(minutes<10)
                time+="0"+minutes+" ";
            else
                time+=minutes+" ";
            time+= constants_strings.am_ap[calendar_previous.get(GregorianCalendar.AM_PM)];
        }
        else{
            time+=(calendar_previous.get(GregorianCalendar.DAY_OF_MONTH)+" "+
                    constants_strings.months[calendar_previous.get(GregorianCalendar.MONTH)]);
            //Log.v("Date ",);
        }
        return time;
    }
}
