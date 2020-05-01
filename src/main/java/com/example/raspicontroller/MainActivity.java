package com.example.raspicontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    //UI Element
    Button btnUp,btnDown,btnLeft,btnRight,btnA,btnB,btnStart,btnSelect,btnConnect;

    EditText txtAddress;
    Socket myAppSocket = null;
    public static String wifiModuleIp = "";
    public static int wifiModulePort = 0;
    public static String CMD = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnConnect = (Button) findViewById(R.id.connect);

        txtAddress = (EditText) findViewById(R.id.ipAddress);
        btnConnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getIPandPort();
            }
        });
        /*btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CMD = "1";//UP
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
            }
        });*/
        btnUp.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD="1";//UP
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD="5";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });
        btnDown.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD="2";//DOWN
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD="6";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });
        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD = "3";//LEFT
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD = "7";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });
        btnRight.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD="4";//RIGHT
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD="8";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });
        btnA.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD="A";//A
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD="E";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });
        btnB.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD="B";//B
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD="F";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });
        btnSelect.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD="C";//UP
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD="G";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });
        btnStart.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    CMD="D";//UP
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    CMD="H";
                }
                Socket_AsyncTask command = new Socket_AsyncTask();
                command.execute();
                return true;
            }
        });

    }
    public void getIPandPort()
    {
        String iPandPort = txtAddress.getText().toString();
        Log.d("MYTEST","IP String: "+ iPandPort);
        String temp[]= iPandPort.split(":");
        wifiModuleIp = temp[0];
        wifiModulePort = Integer.valueOf(temp[1]);
        Log.d("MY TEST","IP:" +wifiModuleIp);
        Log.d("MY TEST","PORT:"+wifiModulePort);
    }
    public class Socket_AsyncTask extends AsyncTask<Void,Void,Void>
    {
        Socket socket;

        @Override
        protected Void doInBackground(Void... params){
            try{
                InetAddress inetAddress = InetAddress.getByName(MainActivity.wifiModuleIp);
                socket = new java.net.Socket(inetAddress,MainActivity.wifiModulePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes(CMD);
                dataOutputStream.close();
                socket.close();
            }catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
            return null;
        }
    }
}
