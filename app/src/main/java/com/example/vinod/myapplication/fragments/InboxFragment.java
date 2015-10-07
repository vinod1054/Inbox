package com.example.vinod.myapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vinod.myapplication.R;
import com.example.vinod.myapplication.activity.Chat;
import com.example.vinod.myapplication.adapter.InboxRowAdapter;
import com.example.vinod.myapplication.model.InboxRow;
import com.example.vinod.myapplication.service.ReadSms;
import com.example.vinod.myapplication.touchhelper.OnStartDragListener;
import com.example.vinod.myapplication.touchhelper.RecyclerItemClickListener;
import com.example.vinod.myapplication.touchhelper.SimpleItemTouchHelperCallback;

import java.util.List;


public class InboxFragment extends android.support.v4.app.Fragment implements OnStartDragListener {
    public List<InboxRow> data;
    public Context context;
    public Intent chatActivity;
    Uri uriSMSURI = Uri.parse("content://sms/inbox");
    final String[] projection = new String[] {"address","date","body"};
    Cursor cur;
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    private ItemTouchHelper mItemTouchHelper;


    public InboxFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        cur = context.getContentResolver().query(uriSMSURI, projection, "address LIKE '%%'", null,"date desc");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.inoxlist);
        ReadSms readSms = new ReadSms(getActivity());
        List<InboxRow> data =readSms.getSmsList(cur);
        InboxRowAdapter adapter = new InboxRowAdapter(getContext(),data,this,recyclerView,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        chatActivity = new Intent(getContext(), Chat.class);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void startChatActivity(){
        startActivity(chatActivity);

    }

}
