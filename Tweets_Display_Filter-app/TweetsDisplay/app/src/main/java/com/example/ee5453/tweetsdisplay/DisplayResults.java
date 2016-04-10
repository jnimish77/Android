package com.example.ee5453.tweetsdisplay;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class DisplayResults extends ActionBarActivity {

    Cursor cursor;
    ListView listVieww;
    SimpleCursorAdapter simpleCursorAdapter;
    String FROM[]={"user_name","status_text"};
    int TO[]={android.R.id.text1,android.R.id.text2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        listVieww=(ListView)findViewById(R.id.listView1);


        ContentResolver resolver=getContentResolver();
        cursor=resolver.query(Uri.parse("content://com.ee5453.mytwitter"),
                null,null,null,"created_at"+" DESC");

        ContentObserver observer = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Cursor tweets;
                tweets=getContentResolver().query(Uri.parse("content://com.ee5453.mytwitter"),
                        null,null,null,"created_at"+" DESC");
                simpleCursorAdapter.changeCursor(tweets);

            }


            @SuppressLint("NewApi")
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Cursor tweets;
                tweets=getContentResolver().query(Uri.parse("content://com.ee5453.mytwitter"),
                        null,null,null,"created_at"+" DESC");
                simpleCursorAdapter.changeCursor(tweets);
            }
        };
        resolver.registerContentObserver(Uri.parse("content://com.ee5453.mytwitter"),true,observer);

        simpleCursorAdapter=new SimpleCursorAdapter(getApplicationContext(),
                android.R.layout.two_line_list_item,cursor,FROM,TO);
        listVieww.setAdapter(simpleCursorAdapter);}

    /*ContentResolver resolver=getContentResolver();
    cursor=resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

    while(cursor.moveToNext()){
        cursor.getString()
    }*/


/*        ContentResolver resolver = getApplicationContext().getContentResolver();
        cursor=resolver.query(Uri.parse("content://com.ee5453.mytwitter"),
                null,null,null,"created_at"+" DESC");
        while(cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("user_name"));
            String status=cursor.getString(cursor.getColumnIndex("status_text"));
            tv.append(String.format("%s\n%s\n",name,status));
        }*/



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
