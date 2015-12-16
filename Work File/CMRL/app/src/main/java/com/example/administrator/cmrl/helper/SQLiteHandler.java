package com.example.administrator.cmrl.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrator.cmrl.Tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "CMRL";

    // Login table name
    private static final String TABLE_USER = "user";

    //Tasks Table name
    private static final String TABLE_TASK = "tasks";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";

    //Tasks Table colomn Names
    private static final String KEY_PID = "id";
    private static final String KEY_ASSETCODE = "assetcode";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_INTERNET = "internet";
    private static final String KEY_SMS = "smssent";
    private static final String KEY_CSIZE = "chsize";
    private static final String KEY_CBOX = "checkbox";

    //Creating Strings required
    private static final String CREATE_LOGIN_TABLE = "CREATE TABLE "
            + TABLE_USER + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT UNIQUE,"
            + KEY_UID + " TEXT"
            + ")";

    private static final String CREATE_TASK_TABLE = "CREATE TABLE "
            + TABLE_TASK + "("
            + KEY_PID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ASSETCODE + " TEXT UNIQUE,"
            + KEY_MESSAGE + " TEXT,"
            + KEY_INTERNET + " INTEGER,"
            + KEY_SMS + " INTEGER,"
            + KEY_CSIZE + " INTEGER,"
            + KEY_CBOX + " TEXT"
            + ")";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_TASK_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */

    public void addUser(String name, String email, String uid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Email


        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }



    //Creating add Tasks Function
    public boolean addtasks(Tasks singletask)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Get Task Details
        String assetcode = singletask.getAssetcode();
        String message  = singletask.getMessage();
        int internet = singletask.getINTERNET();
        int sms = singletask.getSMS();
        int size = singletask.getChsize();
        String cbox = singletask.getCheckBox();

        //Put it into the db;
        ContentValues values = new ContentValues();
        values.put(KEY_ASSETCODE,assetcode);
        values.put(KEY_MESSAGE,message);
        values.put(KEY_INTERNET,internet);
        values.put(KEY_SMS,sms);
        values.put(KEY_CSIZE,size);
        values.put(KEY_CBOX, cbox);

        long id = db.insert(TABLE_TASK, null, values);
        db.close();

        Log.d(TAG, "New Task into SQLITE with id : " + id);

        return false;
    }

    //Delete Tasks
    public int deletetasks(String assetcode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause ="assetcode = ?";
        String[] whereArgs = new String[]{assetcode};
        int deletereturn;
        // Delete the row
        deletereturn = db.delete(TABLE_TASK,whereclause,whereArgs);
        db.close();
        return deletereturn;
    }

    //Get one task Detail
    public Tasks gettaskDetail(String assetcode)
    {
        Tasks singletask = new Tasks();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK+" WHERE " + KEY_ASSETCODE + " = "+"'"+assetcode+"'";
        Log.v(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        singletask.setAssetcode(cursor.getString(cursor.getColumnIndex(KEY_ASSETCODE)));
        singletask.setMessage(cursor.getString(cursor.getColumnIndex(KEY_MESSAGE)));
        singletask.setINTERNET(cursor.getInt(cursor.getColumnIndex(KEY_INTERNET)));
        singletask.setSMS(cursor.getInt(cursor.getColumnIndex(KEY_SMS)));
        singletask.setChsize(cursor.getInt(cursor.getColumnIndex(KEY_CSIZE)));
        singletask.setCheckBox(cursor.getString(cursor.getColumnIndex(KEY_CBOX)));

        return singletask;
    }

    //Retreiving all Tasks
    public List<Tasks> getallTasks()
    {
        List<Tasks> alltasks = new ArrayList<Tasks>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_TASK;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Tasks singletask = new Tasks();
                singletask.setAssetcode(cursor.getString(cursor.getColumnIndex(KEY_ASSETCODE)));
                singletask.setMessage(cursor.getString(cursor.getColumnIndex(KEY_MESSAGE)));
                singletask.setINTERNET(cursor.getInt(cursor.getColumnIndex(KEY_INTERNET)));
                singletask.setSMS(cursor.getInt(cursor.getColumnIndex(KEY_SMS)));
                singletask.setChsize(cursor.getInt(cursor.getColumnIndex(KEY_CSIZE)));
                singletask.setCheckBox(cursor.getString(cursor.getColumnIndex(KEY_CBOX)));

                // adding to list
                alltasks.add(singletask);
            } while (cursor.moveToNext());
        }
        return alltasks;
    }

    public int updatetask(Tasks singletask)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_INTERNET,singletask.getINTERNET());
        values.put(KEY_SMS,singletask.getSMS());
        return db.update(TABLE_TASK, values, KEY_ASSETCODE + " = ?",new String[]{singletask.getAssetcode()});
    }

    public int gettaskrows()
    {
        String countQuery = "SELECT  * FROM " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}