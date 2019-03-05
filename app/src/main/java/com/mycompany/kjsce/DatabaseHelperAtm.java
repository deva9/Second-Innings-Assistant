package com.mycompany.kjsce;

/**
 * Created by MS COMPUTERS on 10/1/2016.
 */import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Acer_Owner on 30-09-2016.
 */
public class DatabaseHelperAtm extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Selfatm.db";
    public static final String TABLE_NAME = "self_tableatm";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ATMNO";
    public static final String COL_3 = "ATMPASS";
    //public static final String COL_4 = "CVCCODE";
    //public static final String COL_5 = "EXPIRY";

    public DatabaseHelperAtm(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,ATMNO TEXT,ATMPASS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String atmno, String atmpass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,atmno);
        contentValues.put(COL_3,atmpass);
        //contentValues.put(COL_4,cvccode);
        //contentValues.put(COL_5,expiry);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAlldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }











}
