package com.mycompany.kjsce;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dhanashree on 30/9/16.
 */
public class PillActivity extends Activity {
    String name;
    int morning,evening,afternoon,night,totalpillsatonce;
    DatabaseHelperMor myDbmor;
    DatabaseHelperAft myDbaft;
    DatabaseHelperEve myDbeve;
    DatabaseHelperNig myDbnig;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill2);
        /*if (checkPlayServices()) {
            gcm = RegistrationIntentService.getInstance(this);
            String regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }
        } else {
            Log.i("111", "No valid Google Play Services APK found.");
        }*/

        myDbmor=new DatabaseHelperMor(this);
        myDbaft=new DatabaseHelperAft(this);
        myDbeve=new DatabaseHelperEve(this);
        myDbnig=new DatabaseHelperNig(this);
        ((Button)findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String morning1=((EditText)findViewById(R.id.n1)).getText().toString();
                String afternoon1=((EditText)findViewById(R.id.n2)).getText().toString();
                String evening1=((EditText)findViewById(R.id.n3)).getText().toString();
                String night1=((EditText)findViewById(R.id.n4)).getText().toString();
                name=((EditText)findViewById(R.id.name)).getText().toString();
                String totalpillsatonce1=((EditText)findViewById(R.id.howmany)).getText().toString();
                morning=Integer.parseInt(morning1);
                afternoon=Integer.parseInt(afternoon1);
                evening=Integer.parseInt(evening1);
                night=Integer.parseInt(night1);
                totalpillsatonce=Integer.parseInt(totalpillsatonce1);
                if(morning>0)
                {

                    setdatamor();


                }
                if(afternoon>0)
                {
                    setdataaft();
                }
                if(evening>0)
                {
                    setdataeve();
                }
                if(night>0)
                {
                    setdatanig();
                }
                int totalpills=morning+afternoon+evening+night;
                if(totalpills>=totalpillsatonce)
                {
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification.Builder n = new Notification.Builder(PillActivity.this);
                    n.setContentText(name+" is over.\n Please order it");
                    n.setContentTitle("You need to buy your pills");
                    n.setSmallIcon(R.drawable.image);
                    n.setDefaults(-1);
                    Notification x = n.build();
                    nm.notify(12, x);
                }
                else
                {

                }
            }
        });

    }


    public void setdatamor()
    {
        boolean isInserted=myDbmor.insertData(name,String.valueOf(morning));
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewallmor()
    {

        Cursor res=myDbmor.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
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
    }


    public void setdataaft()
    {
        boolean isInserted=myDbaft.insertData(name, String.valueOf(afternoon));
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewallaft()
    {

        Cursor res=myDbaft.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
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
    }


    public void setdataeve()
    {
        boolean isInserted=myDbeve.insertData(name, String.valueOf(evening));
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewalleve()
    {

        Cursor res=myDbeve.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
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
    }

    public void setdatanig()
    {
        boolean isInserted=myDbnig.insertData(name, String.valueOf(afternoon));
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewallnig()
    {

        Cursor res=myDbnig.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
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
    }




    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



    public void see(View v)
    {
        viewallmor();
        viewallaft();
        viewalleve();
        viewallnig();
    }






}