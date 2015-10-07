package com.example.vinod.myapplication.touchhelper;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by vinod on 3/10/15.
 */
public class ImageOnTouchListener implements View.OnTouchListener {

    public static int touched=0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        touched ^= 1;
        return false;
    }
}
