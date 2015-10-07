package com.example.vinod.myapplication.touchhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.example.vinod.myapplication.adapter.InboxRowAdapter;

/**
 * Created by vinod on 3/10/15.
 */
public class ImageClickListener implements View.OnClickListener {
    public static int imageprocess=0;

    private Context context;
    private InboxRowAdapter.MyViewHolder viewHolder;

    public ImageClickListener(Context context, InboxRowAdapter.MyViewHolder myViewHolder){
        this.context=context;
        viewHolder=myViewHolder;
    }
    @Override
    public void onClick(View dialog) {
        Toast.makeText(context,"Image View",Toast.LENGTH_LONG).show();
    }
}
