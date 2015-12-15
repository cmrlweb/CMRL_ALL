package com.example.administrator.cmrl;

/**
 * Created by administrator on 10/12/15.
 */
public class Tasks {
    int id;
    String assetcode;
    String message;
    int INTERNET;
    int SMS;
    String CheckBox;

    Tasks(int rid,String assetcode,String mess,int internet,int sms,String checkBox) {
        this.id = rid;
        this.assetcode = assetcode;
        this.INTERNET = internet;
        this.message = mess;
        this.SMS = sms;
        this.CheckBox = checkBox;
    }

    Tasks(){
        this.id =0;
        this.assetcode = null;
        this.INTERNET=0;
        this.message=null;
        this.SMS = 0;
        this.CheckBox = null;
    }
}
