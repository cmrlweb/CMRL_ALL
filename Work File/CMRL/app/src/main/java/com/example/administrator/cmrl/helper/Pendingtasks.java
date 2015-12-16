package com.example.administrator.cmrl.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
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
    private static final String KEY_CSIZE = "chsize";
    private static final String KEY_CBOX = "checkbox";
    private static Pendingtasks instance;
    private ArrayList<String> ALLAC;

    public Pendingtasks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ASSETCODE + " TEXT UNIQUE,"
                + KEY_MESSAGE + " TEXT," + KEY_INTERNET + " INTEGER,"
                + KEY_SMS + " INTEGER," + KEY_CSIZE + " INTEGER," +KEY_CBOX + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
        Log.d(TAG, "Database tables created");
    }

    public static synchronized Pendingtasks getHelper(Context context)
    {
        if (instance == null)
            instance = new Pendingtasks(context);

        return instance;
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    public boolean addtasks(String assetcode,String message,int internet,int sms,int size,String cbox)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ASSETCODE,assetcode);
        values.put(KEY_MESSAGE,message);
        values.put(KEY_INTERNET,internet);
        values.put(KEY_SMS,sms);
        values.put(KEY_CSIZE,size);
        values.put(KEY_CBOX,cbox);

        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "New Task into SQLITE with id : " + id);

        return false;
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
            task.put("ASSETCODE",cursor.getString(cursor.getColumnIndex(KEY_ASSETCODE)));
            task.put("MESSAGE",cursor.getString(cursor.getColumnIndex(KEY_MESSAGE)));
            task.put("INTERNET",cursor.getString(cursor.getColumnIndex(KEY_INTERNET)));
            task.put("SMS",cursor.getString(cursor.getColumnIndex(KEY_SMS)));
            task.put("CSIZE",cursor.getString(cursor.getColumnIndex(KEY_CSIZE)));
            task.put("CBOX",cursor.getString(cursor.getColumnIndex(KEY_CBOX)));
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching Task from Sqlite: " + task.toString());

        return task;
    }

    public ArrayList<String> getalltasks()
    {
        ALLAC = new ArrayList<String>();
        ALLAC.clear();
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() >0)
        {
            while(cursor.isAfterLast() == false)
            {
                String assetcode = cursor.getString(cursor.getColumnIndex(KEY_ASSETCODE));
                ALLAC.add(assetcode);
            }
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching com.example.administrator.cmrl.Tasks from Sqlite: ");
        return ALLAC;
    }

    public void updatetasks(String assetcode,int internet,int sms)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE "+TABLE_USER+" SET internet = "+internet+", smssent = "+sms+" WHERE assetcode = "+ assetcode;
        db.execSQL(strSQL);
        db.close();
    }
}
