package com.example.administrator.cmrl.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class Pendingtasks extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CMRLMAIN";

    //table name
    private static final String TABLE_USER = "pending";

    //Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ASSETCODE = "assetcode";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_INTERNET = "internet";
    private static final String KEY_SMS = "smssent";


    public Pendingtasks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ASSETCODE + " TEXT UNIQUE,"
                + KEY_MESSAGE + " TEXT," + KEY_INTERNET + " INTEGER,"
                + KEY_SMS + " INTEGER" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    public boolean addtasks(String assetcode,String message,int internet,int sms)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ASSETCODE,assetcode);
        values.put(KEY_MESSAGE,message);
        values.put(KEY_INTERNET,internet);
        values.put(KEY_SMS,sms);

        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "New Task into SQLITE with id : " + id);

        return Boolean.parseBoolean(null);
    }

    public int deletetasks(String assetcode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause ="assetcode = ?";
        String[] whereArgs = new String[]{assetcode};
        int deletereturn;
        // Delete the row
        deletereturn = db.delete(TABLE_USER,whereclause,whereArgs);
        db.close();
        return deletereturn;
    }

    public int getrows()
    {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public HashMap<String, String> gettaskDetails(String assetcode)
    {
        HashMap<String, String> task= new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER+" WHERE assetcode = "+assetcode;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() >0)
        {
            task.put("ASSETCODE",cursor.getString(1));
            task.put("MESSAGE",cursor.getString(2));
            task.put("INTERNET",cursor.getString(3));
            task.put("SMS",cursor.getString(4));
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching Task from Sqlite: " + task.toString());

        return task;
    }
}
