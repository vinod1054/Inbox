package com.example.vinod.myapplication.touchhelper;

/**
 * Created by vinod on 2/10/15.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    GestureDetector mGestureDetector;
    public static boolean longclick=false;
    public RecyclerItemClickListener(final Context context, OnItemClickListener listener) {
        mListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                longclick=true;
                Log.v("OnLongPress", "Long Click");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.v("SingleClick", "OnSingleTapUp");
                return true;
            }
        });
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        Log.v("Imageouched : ",ImageOnTouchListener.touched+"");
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e) && ImageOnTouchListener.touched==0) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        if(longclick){
            longclick=false;
            mListener.onItemLongClick(childView,view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }
}