package com.example.nimish.lifecycle_project;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = "Lifecycle_Activity Display";
    public static int createCount=0;
    public static  int startCount=0;
    public static  int restartCount=0;
    public static  int resumeCount=0;
    public static  int pauseCount=0;
    public static int stopCount=0;
    public static int destroyCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCount++;
        Log.d(TAG,"Activity created: " + createCount);
        if(createCount>1){
            TextView tv= (TextView) findViewById(R.id.assigntextviewid);
            tv.setText("Create Count:" + createCount);
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startCount++;
        Log.d(TAG,"Activity started: " + startCount);
        if(startCount>1){
            TextView tv1= (TextView) findViewById(R.id.textView);
            tv1.setText("Start Count:" + startCount);
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        restartCount++;
        Log.d(TAG,"Activity Restarted: " + restartCount);
        if(restartCount>1){
            TextView tv2= (TextView) findViewById(R.id.textView2);
            tv2.setText("Restart Count:" + restartCount);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // super.onStart();
        // super.onStop();
        //  destroyCount++;
        resumeCount++;
        // stopCount++;
        // startCount++;
        Log.d(TAG,"Activity resumed: " + resumeCount);
        //Log.d(TAG,"Activity stopped: " + stopCount);
        // Log.d(TAG,"Activity started: " + startCount);


        if (resumeCount>1) {
            TextView tv3 = (TextView) findViewById(R.id.textView3);
            // TextView tv2 = (TextView) findViewById(R.id.textView);
            // TextView tv3 = (TextView) findViewById(R.id.assigntextviewid);
            // TextView tv4 = (TextView) findViewById(R.id.textView3);
            //tv.setText("Start Count: " + startCount);
            // tv2.setText("Stop Count: " + stopCount);
            tv3.setText("Resume Count: " + resumeCount);
            //tv4.setText("Destroy Count: " +destroyCount);
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        pauseCount++;
        Log.d(TAG,"Activity Paused: " + pauseCount);
        if(pauseCount>1){
            TextView tv4= (TextView) findViewById(R.id.textView4);
            tv4.setText("Pause Count:" + pauseCount);
        }

    }

    @Override
    protected void onStop() {
        //super.onStart();
        super.onStop();
        stopCount++;
        Log.d(TAG,"Activity stopped: " + stopCount);
        if(stopCount>1){
            TextView tv5= (TextView) findViewById(R.id.textView5);
            tv5.setText("Stop Count:" + stopCount);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyCount++;
        Log.d(TAG,"Activity destroyed: " + destroyCount);

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
