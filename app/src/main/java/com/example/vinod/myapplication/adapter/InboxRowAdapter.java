package com.example.vinod.myapplication.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinod.myapplication.R;
import com.example.vinod.myapplication.constants.constants_drawable;
import com.example.vinod.myapplication.fragments.InboxFragment;
import com.example.vinod.myapplication.model.InboxRow;
import com.example.vinod.myapplication.touchhelper.ImageOnTouchListener;
import com.example.vinod.myapplication.touchhelper.ItemTouchHelperAdapter;
import com.example.vinod.myapplication.touchhelper.ItemTouchHelperViewHolder;
import com.example.vinod.myapplication.touchhelper.OnStartDragListener;
import com.example.vinod.myapplication.touchhelper.RecyclerItemClickListener;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by vinod on 30/9/15.
 */
public class InboxRowAdapter extends RecyclerView.Adapter<InboxRowAdapter.MyViewHolder> implements ItemTouchHelperAdapter {
    List<InboxRow> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private final OnStartDragListener mDragStartListener;
    public SparseBooleanArray selectedItems = new SparseBooleanArray();
    RecyclerView recyclerView;
    public boolean checkable=false;
    public InboxFragment inboxFragment;
    public void toggleSelection(int position){

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(data, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public InboxRowAdapter(final Context context, final List<InboxRow> data, OnStartDragListener dragStartListener, RecyclerView recyclerView, final InboxFragment inboxFragment) {
        this.mDragStartListener = dragStartListener;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.recyclerView=recyclerView;
        this.inboxFragment=inboxFragment;
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        if(checkable){
                            selectedItems.put(position, true);
                            view.setSelected(true);
                        }
                        else {
                            inboxFragment.chatActivity.putExtra("address", data.get(position).getTitle());
                            Log.v("Rnad", data.get(position).getRand() + "");
                            inboxFragment.chatActivity.putExtra("rand", data.get(position).getRand() + "");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    inboxFragment.startChatActivity();
                                }
                            }, 150);

                        }
                        Log.v("OnItemCLick","OnItemClick");
                    }
                    public void onItemLongClick(View view, int position) {
//                        // TODO Handle item click
//                        checkable=true;
//                        selectedItems.put(position, true);
//                        view.setSelected(true);
//                        Log.v("OnItemLongCLick", "OnItemLongClick");
                    }
                })
        );
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.inbox_row, parent, false);
        view.setSelected(false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        InboxRow current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.subTitle.setText(current.getSubTiltle());
        holder.time.setText(current.getTime());
        holder.myBackground.setSelected(selectedItems.get(position, false));
        Log.v("myviewimage", data.get(position).getTitle().charAt(0) + "");
        holder.imageView.setText(data.get(position).getTitle().charAt(0) + "");
        int rand=new Random().nextInt(constants_drawable.circles.length);
        data.get(position).setRand(rand);
        holder.imageView.setBackgroundResource(constants_drawable.circles[rand]);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        @Override
        public void onItemSelected() {
           // itemView.setBackgroundColor(Color.LTGRAY);
            Log.v("OnItemSlected","OnItemSelected");
        }

        @Override
        public void onItemClear() {
           // itemView.setBackgroundColor(0);
            Log.v("OnItemClear","OnItemClear");
        }

        TextView title;
        TextView subTitle;
        TextView time;
        TextView imageView;
        FrameLayout myBackground;
        Boolean background=false;
        public MyViewHolder(View itemView) {
            super(itemView);
            myBackground=(FrameLayout)itemView.findViewById(R.id.item);
            itemView.setSelected(false);
            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subtitle);
            time = (TextView) itemView.findViewById(R.id.time);
            imageView = (TextView) itemView.findViewById(R.id.contact_icon);
            imageView.getParent().requestDisallowInterceptTouchEvent(true);
            imageView.setOnTouchListener(new ImageOnTouchListener());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"ImageView",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
