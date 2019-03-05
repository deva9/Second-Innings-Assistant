package com.mycompany.kjsce;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Songs extends AppCompatActivity {
    String JSON_STRING,json_url,l[],k[];
    JSONObject jsonObject;
    JSONArray jsonArray;
    int MAXCOUNT;
    TextView t[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        t=new TextView[15];
        t[0]=(TextView)findViewById(R.id.t1);
        t[1]=(TextView)findViewById(R.id.t2);
        t[2]=(TextView)findViewById(R.id.t3);
        t[3]=(TextView)findViewById(R.id.t4);
        t[4]=(TextView)findViewById(R.id.t5);
        t[5]=(TextView)findViewById(R.id.t6);
        t[6]=(TextView)findViewById(R.id.t7);
        t[7]=(TextView)findViewById(R.id.t8);
        t[8]=(TextView)findViewById(R.id.t9);
        t[9]=(TextView)findViewById(R.id.t10);
        t[10]=(TextView)findViewById(R.id.t11);
        t[11]=(TextView)findViewById(R.id.t12);
        t[12]=(TextView)findViewById(R.id.t13);
        t[13]=(TextView)findViewById(R.id.t14);
        t[14]=(TextView)findViewById(R.id.t15);
        BackgroundTaskSongs backgroundTaskSongs=new BackgroundTaskSongs();
        backgroundTaskSongs.execute();

    }


    class BackgroundTaskSongs extends AsyncTask<Void,Void,String> {


        @Override
        protected void onPreExecute() {
            json_url = "http://pleasework12.esy.es/getsongs.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
//editText.setText(result);
//            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            split(result);
        }
    }

    public void split(String s){

        try {
            jsonObject=new JSONObject(s);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;

            l=new String[jsonArray.length()];
            k=new String[jsonArray.length()];

            MAXCOUNT=jsonArray.length();

            while(count<jsonArray.length())
            {
                JSONObject jo=jsonArray.getJSONObject(count);
                l[count]=jo.getString("Name");
                k[count]=jo.getString("Link");
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        setsongs();

    }



    public void setsongs()
    {

        for(int j=0;j<MAXCOUNT;j++)
        {
            t[j].setText(l[j]);
        }
    }


    public void fun(View v)
    {if(v.getId()==t[0].getId())
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(k[0])));
    else if(v.getId()==t[1].getId())
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(k[1])));

    }






}