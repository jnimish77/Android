package com.ee5453.utsa_shuttle_server;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ClientMainActivity();
        new ServerMainActivity();


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

public class ClientMainActivity {

    LocationManager lm;
    Location l;
    String provider;

    private Socket client;
    private PrintWriter printwriter;
    private EditText textField;
    private Button button;
    private double lat;
    private double lon;
    TextView textView;
    public void client() {
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria c = new Criteria();

        provider = lm.getBestProvider(c, false);
        l = lm.getLastKnownLocation(provider);
        if (l != null) {
            lat = l.getLatitude();
            lon = l.getLongitude();
            textView.setText(""+lat);
            textView.setText(""+lon);
            SendMessage sendMessage = new SendMessage();
            sendMessage.execute();
        }
        // textField = (EditText) findViewById(R.id.editText1); // reference to the text field
        // button = (Button) findViewById(R.id.button1); // reference to the send button

        // Button press event listener
       /* button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                messsage = textField.getText().toString(); // get the text message on the text field
                textField.setText(""); // Reset the text field to blank
                SendMessage sendMessageTask = new SendMessage();
                sendMessageTask.execute();
            }
        });*/

    }


    private class SendMessage extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {
            try {

                client = new Socket("10.0.2.2", 4444); // connect to the server
                printwriter = new PrintWriter(client.getOutputStream(), true);
                printwriter.write(String.valueOf(lat));
                printwriter.write(String.valueOf(lon));// write the message to output stream

                printwriter.flush();
                printwriter.close();
                client.close(); // closing the connection

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
class ServerMainActivity {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private double lat;
    private double lon;
    private String lats;
    private String longs;


    public void server() {
        try {
            serverSocket = new ServerSocket(4444); // Server socket
            System.out.println("Server started listen on port: 4444");
        } catch (IOException e1) {
            System.out.println("Could not listen on port: 4444");
            e1.printStackTrace();
        }


        while (true) {
            try {

                clientSocket = serverSocket.accept(); // accept the client connection
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader); // get the client message
                lats = bufferedReader.readLine();
                longs= bufferedReader.readLine();
                System.out.println(String.valueOf(lats));
                System.out.println(String.valueOf(longs));
                inputStreamReader.close();
                clientSocket.close();

            } catch (IOException ex) {
                System.out.println("Problem in message reading");
            }
        }

    }}




