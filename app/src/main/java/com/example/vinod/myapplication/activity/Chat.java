package com.example.vinod.myapplication.activity;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.vinod.myapplication.R;
import com.example.vinod.myapplication.constants.constants_drawable;
import com.example.vinod.myapplication.model.InboxRow;
import com.example.vinod.myapplication.service.ReadSms;

import java.util.List;

public class Chat extends AppCompatActivity {

    private LinearLayout linearLayout;
    private String fromAddress;
    Uri uriSMSURI = Uri.parse("content://sms/inbox");
    final String[] projection = new String[] {"address","date","body"};
    Cursor cur;
    List<InboxRow> data;
    ScrollView scrollView;
    EditText editText;
    int rand=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fromAddress=getIntent().getStringExtra("address");
        try{
        rand=Integer.parseInt((String)(getIntent().getStringExtra("rand")));}
        catch (Exception e){
            Log.v("myview",e.toString());
        }
        Log.v("Colorprimary", constants_drawable.colorPrimary[8] + "");
        getWindow().setStatusBarColor(getResources().getColor(constants_drawable.colorPrimaryDark[rand]));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_chat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(fromAddress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(constants_drawable.colorPrimary[rand])));
        cur = this.getContentResolver().query(uriSMSURI, projection, "address LIKE '%" + fromAddress + "%'", null, "date asc");
        data=new ReadSms().getSmsList(cur);
        linearLayout = (LinearLayout) findViewById(R.id.chatrootlayout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        editText = (EditText) findViewById(R.id.editText);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        for(int i=0;i<data.size();i++){
            InboxRow inboxRow=data.get(i);
            TextView textView = new TextView(this);
            textView.setText(inboxRow.getSubTiltle());
            textView.setPadding(16, 16, 16, 16);

            textView.setBackgroundResource(constants_drawable.rectangles[rand]);
            textView.setTextColor(getResources().getColor(R.color.white));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 20, 30, 20);
            textView.setLayoutParams(layoutParams);
            linearLayout.addView(textView);
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
