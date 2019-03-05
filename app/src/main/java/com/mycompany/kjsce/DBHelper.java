package com.mycompany.kjsce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;// Table name
    public static final String DATABASE_NAME = "mydb";
    public static final String TABLE_NAME = "HOME";

    public static final String _LAT = "LAT";
    public static final String _ID = "_id";
    public static final String _LON = "LON";
    public static final String _FLAG = "FLAG";
    String buildSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            _LAT + "TEXT, " + _LON + " TEXT, " + _FLAG + " TEXT)";


    /*private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;
    private Context con;
    private DBHelper dbh;*/
    private static final String TAG = DBHelper.class.getSimpleName();

    // this is a wrapper class. that means, from outside world, anyone will communicate with PersonDatabaseHelper,
    // but under the hood actually DatabaseOpenHelper class will perform database CRUD operations
    public DBHelper(Context aContext) {
        super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create your tables here
        Log.d(TAG, "onCreate SQL: " + buildSQL);;
        sqLiteDatabase.execSQL(buildSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Database schema upgrade code goes here
        String buildS = "DROP TABLE IF EXISTS " + TABLE_NAME;
        Log.d(TAG, "onUpgrade SQL: " + buildS);
        sqLiteDatabase.execSQL(buildS);       // drop previous table
        onCreate(sqLiteDatabase);               // create the table from the beginning
    }
}