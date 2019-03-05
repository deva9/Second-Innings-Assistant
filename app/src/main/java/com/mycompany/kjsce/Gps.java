package com.mycompany.kjsce;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Gps extends AppCompatActivity {
    int i=0;
    private LocationManager locationManager;
    private LocationListener locationListener;
    Button b;
    String smsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        b=(Button)findViewById(R.id.button7);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                location.getLatitude();
                Toast.makeText(getApplicationContext(),"Longitude:"+String.valueOf(location.getLongitude()+"\nLatitude:"+String.valueOf(location.getLatitude())),Toast.LENGTH_LONG).show();
                smsg="Hey, I seem to be in trouble.\nI need assitance. My location co-ordinates are: \n"+"Longitude:"+String.valueOf(location.getLongitude()+"\nLatitude:"+String.valueOf(location.getLatitude()));
                sendsms(smsg);
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET
                },10);
                return;
            }
            else{
                configureButton();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configureButton();
        }
    }

    private void configureButton(){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0,locationListener);
            }
        });

    }


    public void sendsms(String s)
    {
        if(i==0) {
            i++;/*
    Uri uri = Uri.parse("smsto:9619843933");
    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
    it.putExtra("sms_body", "The SMS text");
    startActivity(it);  */
            try {
                SmsManager smsManager = SmsManager.getDefault();
                String number="9820837750";
                String message=s;
                smsManager.sendTextMessage(number, null,message, null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
            }

            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

}
