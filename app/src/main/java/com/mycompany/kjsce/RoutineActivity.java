package com.mycompany.kjsce;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class RoutineActivity extends AppCompatActivity {
    EditText h;
    DatabaseHelperMor myDbmorn;
    DatabaseHelperAft myDbaft;
    DatabaseHelperEve myDbeve;
    DatabaseHelperNig myDbnig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        h=(EditText)findViewById(R.id.editText3);
        startService(new Intent(getBaseContext(), RoutineService.class));

        myDbmorn=new DatabaseHelperMor(this);
        myDbaft=new DatabaseHelperAft(this);
        myDbeve=new DatabaseHelperEve(this);
        myDbnig=new DatabaseHelperNig(this);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String hour1=intent.getExtras().getString("hour");

            int hour=Integer.parseInt(hour1);
          //  Log.d("TAG",""+hour);
            if(hour==16) {
                String s=viewalleve();


                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder n = new Notification.Builder(RoutineActivity.this);
                n.setContentText(s);
                n.setContentTitle("evening Pills");
                n.setSmallIcon(R.drawable.image);
                n.setDefaults(-1);
                Notification x = n.build();
                nm.notify(12, x);

            }
            if(hour==12) {
                String p=viewallaft();
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder n = new Notification.Builder(RoutineActivity.this);
                n.setContentText(p);
                n.setContentTitle("Afternoon");
                n.setSmallIcon(R.drawable.image);
                n.setDefaults(-1);
                Notification x = n.build();
                nm.notify(12, x);
            }
            if(hour==18) {
                String g=viewalleve();
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder n = new Notification.Builder(RoutineActivity.this);
                n.setContentText(g);
                n.setContentTitle("Evening Pills");
                n.setSmallIcon(R.drawable.image);
                n.setDefaults(-1);
                Notification x = n.build();
                nm.notify(12, x);
            }
            if(hour==23) {
                String w=viewallnig();
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder n = new Notification.Builder(RoutineActivity.this);
                n.setContentText(w);
                n.setContentTitle("Night Pills");
                n.setSmallIcon(R.drawable.image);
                n.setDefaults(-1);
                Notification x = n.build();
                nm.notify(12, x);
            }
        }
    };


    public void stop(View v)
    {
        stopService(new Intent(getBaseContext(), RoutineService.class));
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(
                "com.example.acer_owner.aged"));
    }

    // Method to start the service
    public void startService(View view) {
        startService(new Intent(getBaseContext(), RoutineService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), RoutineService.class));
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public String viewallmor()
    {

        Cursor res=myDbmorn.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return "nothing";
        }


        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id"+res.getString(0)+"\n");
            buffer.append("Name Of Pill:"+res.getString(1)+"\n");
            buffer.append("No. Of Pills:"+res.getString(2)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        return buffer.toString();
    }


    public String viewallnig()
    {

        Cursor res=myDbnig.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return "nothing";
        }


        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id"+res.getString(0)+"\n");
            buffer.append("Name Of Pill:"+res.getString(1)+"\n");
            buffer.append("No. Of Pills:"+res.getString(2)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        return buffer.toString();
    }

    public String viewalleve()
    {

        Cursor res=myDbeve.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return "nothing";
        }


        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id"+res.getString(0)+"\n");
            buffer.append("Name Of Pill:"+res.getString(1)+"\n");
            buffer.append("No. Of Pills:"+res.getString(2)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        showMessage("Data", buffer.toString());
        return buffer.toString();
    }

    public String viewallaft()
    {

        Cursor res=myDbaft.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return "nothing";
        }


        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id"+res.getString(0)+"\n");
            buffer.append("Name Of Pill:"+res.getString(1)+"\n");
            buffer.append("No. Of Pills:"+res.getString(2)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        return buffer.toString();
    }



}