package com.mycompany.kjsce;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Lostf extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener locationListener;
    Button b;
    String uriBegi = "google.navigation";
    double lati, longi;
    TextView t1, t2, t3, t4;
    static final String LATT = "latt";
    static final String LONGG = "longg";
    static final String CD = "cd";
    public static final String MyPREFERENCES = "MyPrefs";
    String glat,glon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostf);
        Intent getint=getIntent();
        glat=getint.getStringExtra("LATIT");
        glon=getint.getStringExtra("LONGI");
        t1 = (TextView) findViewById(R.id.txt1);
        t2 = (TextView) findViewById(R.id.txt2);
        t3 = (TextView) findViewById(R.id.txt3);
        t4 = (TextView) findViewById(R.id.txt4);
        t3.setText(glat);
        t4.setText(glon);
        b=(Button)findViewById(R.id.button7);
        SharedPreferences sp = getApplicationContext().getSharedPreferences(MyPREFERENCES, 0);
        String provn=sp.getString(CD,"Default");
        String puser = sp.getString(LATT,"Default");
        String ppwd = sp.getString(LONGG,"Default");
        if (!puser.equals("Default") && !ppwd.equals("Default")) {
            //if (sp.getString(KEY_USERNAME,"").equals("")){
            t3.setText(puser);
            t4.setText(ppwd);
        }


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //location.getLatitude();
                lati = location.getLatitude();
                longi = location.getLongitude();
                Toast.makeText(getApplicationContext(), "Latitude:" + lati + "\nLongitude:" + longi, Toast.LENGTH_LONG).show();
                t1.setText(lati + "");
                t2.setText(longi + "");
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }
            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
                return;
            } else {//configureButton();
            }
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saving as home", Toast.LENGTH_LONG).show();
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 0, locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, locationListener);
                String a = t1.getText().toString().trim();
                String b = t2.getText().toString().trim();
                //dbc.insertcords(a,b,"FLAG");
                t3 = (TextView) findViewById(R.id.txt3);
                t4 = (TextView) findViewById(R.id.txt4);
                t3.setText(a);
                t4.setText(b);
                /*SharedPreferences sp = getSharedPreferences(MyPREFERENCES, 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(LATT,a);
                editor.putString(LONGG,b);
                editor.putString(CD,"none");
                editor.putBoolean(b, true);
                editor.commit();*/
            }
        });
    }

    // private void configureButton() {}
    public void lostumlost(View view) {
        /*Cursor c = dbc.getemail();
        String[] from = new String[]{DBHelper._LAT, DBHelper._LON, DBHelper._FLAG};
        String lt=c.getString(1);
        String lnt=c.getString(2);*/
        String uriString = uriBegi + ":q=" + t3.getText().toString().trim() + "," + t4.getText().toString().trim() + "&z=10";
        //String buriString = uriBegi + ":q=" + lt + "," + lnt + "&z=10";
        Uri uri = Uri.parse(uriString);
        Intent mapintent1 = new Intent(android.content.Intent.ACTION_VIEW, uri);
        //mapintent.setPackage("com.google.android.maps");
        //if (mapintent.resolveActivity(getPackageManager()) != null){
        startActivity(mapintent1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){}
                //configureButton();
        }
    }
}
