package com.mycompany.kjsce;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class RoutineService extends Service {
    int i=0;
    Intent intent;
    public RoutineService() {
        intent = new Intent("com.example.acer_owner.aged");
    }

    final class MyThreadClass implements Runnable
    {

        int service_id;

        MyThreadClass(int service_id)
        {
            this.service_id=service_id;
        }


        @Override
        public void run() {


            synchronized (this)
            {

                StringBuffer buffer =new StringBuffer();
                buffer.append("Atm Password:");
                while(true)
                {
                    Calendar c = Calendar.getInstance();

                    int seconds = c.get(Calendar.SECOND);
                    int minutes = c.get(Calendar.MINUTE);
                    int hour=c.get(Calendar.HOUR_OF_DAY);
                   // Log.d("TAG",String.valueOf(hour));
                    if(hour==16)
                    {
//                       Log.d("TAG","ksdfybjuiaysdgfuisdfgo");
                        Log.d("TAG","It is in");
                        //NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        intent.putExtra("hour", ""+hour);
                        sendBroadcast(intent);
                        try {
                            Thread.sleep(3600000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(hour==12)
                    {
//                       Log.d("TAG","ksdfybjuiaysdgfuisdfgo");

                        //NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        intent.putExtra("hour", hour);
                        intent.putExtra("counter", ""+1);
                        sendBroadcast(intent);
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(hour==18)
                    {
//                       Log.d("TAG","ksdfybjuiaysdgfuisdfgo");

                        //NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        intent.putExtra("hour", hour);
                        //intent.putExtra("counter", ""+1);
                        sendBroadcast(intent);
                        try {
                            Thread.sleep(3600000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(hour==23)
                    {
//                       Log.d("TAG","ksdfybjuiaysdgfuisdfgo");

                        //NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        intent.putExtra("hour", hour);
                        intent.putExtra("counter", ""+1);
                        sendBroadcast(intent);
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }



                }
            }


        }
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Thread thread=new Thread(new MyThreadClass(startId));
        thread.start();
        Calendar c = Calendar.getInstance();

        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);
        String time = hour+":"+minutes+":"+seconds;
//        Toast.makeText(this,String.valueOf(minutes), Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }


}