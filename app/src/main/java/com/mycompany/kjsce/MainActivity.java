package com.mycompany.kjsce;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> listOfName;
    double lat = 19.12;
    double lon = 72.80;
    String uriBegin = "geo:" + lat + "," + lon;
    String uriBegi = "google.navigation";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final AutoCompleteTextView tv = (AutoCompleteTextView) findViewById(R.id.autotext);
        String[] MyList = {
                "Hospitals",
                "Restaurants",
                "School",
                "Park",
                "Gym",
                "Garden",
                "Club",
                "Clinic",
                "College",
                "Gymkhana",
                "Pool"
        };

        final List<String> myList = new ArrayList<String>(Arrays.asList(MyList));
        listOfName = new ArrayAdapter<String>(this, R.layout.listitem, R.id.list_item_forecast_textview, myList);
        tv.setAdapter(listOfName);
        tv.setThreshold(1);
        //tv.setAdapter(listOfName);
        //registerForContextMenu(listView);

        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String namez = listOfName.getItem(i);
                String na=(String)adapterView.getItemAtPosition(i);
                tv.setText(na);
                //Toast.makeText(MainActivity.this, na, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void maps(View view) {
        EditText editText = (EditText) findViewById(R.id.autotext);
        String loc = editText.getText().toString();
        String encodedQuery = Uri.encode(loc);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=10";
        Uri uri = Uri.parse(uriString);
        Intent mapintent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        //mapintent.setPackage("com.google.android.maps");
        //if (mapintent.resolveActivity(getPackageManager()) != null){
        startActivity(mapintent);
    }

    public void navigate(View view) {
        EditText editText = (EditText) findViewById(R.id.autotext);
        String loc = editText.getText().toString();
        String encodedQuery = Uri.encode(loc);
        String uriString = uriBegi + ":q=" + encodedQuery + "&z=10";
        Uri uri = Uri.parse(uriString);
        Intent mapintent1 = new Intent(android.content.Intent.ACTION_VIEW, uri);
        //mapintent.setPackage("com.google.android.maps");
        //if (mapintent.resolveActivity(getPackageManager()) != null){
        startActivity(mapintent1);
    }

}
