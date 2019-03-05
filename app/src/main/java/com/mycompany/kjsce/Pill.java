package com.mycompany.kjsce;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Pill extends Activity {
    EditText n,n1,n2,n3,n4;
    Button b;
    String s,s1,s2,s3,s4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);
         n=(EditText)findViewById(R.id.name);
         n1=(EditText)findViewById(R.id.n1);
         n2=(EditText)findViewById(R.id.n2);
         n3=(EditText)findViewById(R.id.n3);
         n4=(EditText)findViewById(R.id.n4);
        b=(Button)findViewById(R.id.enter);
        /*if (checkPlayServices()) {
            gcm = RegistrationIntentService.getInstance(this);
            String regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }
        } else {
            Log.i("111", "No valid Google Play Services APK found.");
        }*/
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*String morning1 = ((EditText) findViewById(R.id.n1)).getText().toString();
                        String afternoon1 = ((EditText) findViewById(R.id.n2)).getText().toString();
                        String evening1 = ((EditText) findViewById(R.id.n3)).getText().toString();
                        String night1 = ((EditText) findViewById(R.id.n4)).getText().toString();*/
                        s=n.getText().toString();
                        s1=n1.getText().toString();
                        s2=n2.getText().toString();
                        s3=n3.getText().toString();
                        s4=n4.getText().toString();
                        int morning = Integer.parseInt(s1);
                        int afternoon = Integer.parseInt(s2);
                        int evening = Integer.parseInt(s3);
                        int night = Integer.parseInt(s4);
                        if (morning > 0) {
                            MyTask a = new MyTask();
                            a.execute();
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                        /*HashMap<String, String> hashmap1 = new HashMap<>();
                        hashmap1.put("token", "" + 1);
                        hashmap1.put("deviceId", "a");
                        String url1 = "http://localhost:8080/api/bears";
                        String result1 = "";

                        PostMethod task1 = new PostMethod(hashmap1, url1, getApplicationContext());
                        try {
                            result1 = task1.execute("").get();

                        } catch (Exception e) {

                        }*/
                            }
                        }).start();
                    }
                });

    }
    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.color.colorPrimary)
                            .setContentTitle("KJSCE")
                            .setContentText("Hello notif");
            Intent resultIntent = new Intent(Pill.this, MainActivity.class);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            Pill.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            mBuilder.setContentIntent(resultPendingIntent);
            int mNotificationId = 001;
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, mBuilder.build());

            return null;
        }
    }
}
