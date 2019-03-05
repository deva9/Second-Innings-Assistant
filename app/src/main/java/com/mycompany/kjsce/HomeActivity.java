package com.mycompany.kjsce;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LocationManager locationManager;
    private LocationListener locationListener;
    public final static String FLAT = "com.mycompany.kjsce.MESSAGE";
    public final static String FLON = "com.mycompany.kjsce.MESSAGE";
    Button button;
    String b;
    double lati, longi;
    static final String LATT = "latt";
    static final String LONGG = "longg";
    static final String CD = "cd";
    public static final String MyPREFERENCES = "MyPrefs";
    TextView t10,t11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = (Button) findViewById(R.id.button);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        t10=(TextView)findViewById(R.id.textView3);
        t11=(TextView)findViewById(R.id.textView6);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //location.getLatitude();
                lati = location.getLatitude();
                longi = location.getLongitude();
                Toast.makeText(getApplicationContext(), "Latitude:" + lati + "\nLongitude:" + longi, Toast.LENGTH_LONG).show();
                t10.setText(lati + "");
                t11.setText(longi + "");
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
            } else {
                //configureButton();
            }
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saving as home", Toast.LENGTH_LONG).show();
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 0, locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, locationListener);
                /*SharedPreferences sp = getSharedPreferences(MyPREFERENCES, 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(LATT,t10.getText().toString().trim());
                editor.putString(LONGG,t11.getText().toString().trim());
                editor.putString(CD, "none");
                editor.putBoolean(b, true);
                editor.commit();*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.reco) {
            // Handle the camera action
        } else if (id == R.id.meds) {
            Intent inte = new Intent(this, PillActivity.class);
            startActivity(inte);
        } else if (id == R.id.srch) {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        } else if (id == R.id.sos) {
            Intent so=new Intent(this,Gps.class);
            startActivity(so);
        } else if (id == R.id.tech) {
            Intent bo=new Intent(this,TechHelper.class);
            startActivity(bo);
        } else if (id == R.id.fina) {
            Intent fi=new Intent(this,FinancialDetails.class);
            startActivity(fi);
        } else if (id == R.id.bord) {
            Intent bo=new Intent(this,Songs.class);
            startActivity(bo);
        } else if (id == R.id.lost) {
            Intent i = new Intent(getApplicationContext(), Lostf.class);
            i.putExtra(FLAT,t10.getText().toString().trim());
            i.putExtra(FLON,t11.getText().toString().trim());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
