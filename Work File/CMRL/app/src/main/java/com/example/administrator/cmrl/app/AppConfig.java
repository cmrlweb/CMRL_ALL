package com.example.administrator.cmrl.app;

import android.util.Log;

import com.example.administrator.cmrl.R;

/**
 * Created by administrator on 27/10/15.
 */

public class AppConfig {

    public static String HOST = "192.168.202.43";
    // Server user login url
    public static String URL_LOGIN = "http://"+HOST+"/api/login";

    // Server user register url
    public static String URL_REGISTER = "#";

    public static String URL_ASSETCODE = "http://"+HOST+"/api/assetcode";

    public static String URL_POSTASSET = "http://"+HOST+"/api/postdata";

    public static String URL_SYNC = "http://"+HOST+"/api/syncdata";

    void setHostDetail(String value)
    {
        this.HOST = value;
    }
}
