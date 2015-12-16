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
    int Chsize;
    String CheckBox;

    public Tasks() {

    }

    public void setTasks(int rid,String assetcode,String mess,int internet,int sms,int Chsize,String checkBox) {
        this.id = rid;
        this.assetcode = assetcode;
        this.INTERNET = internet;
        this.message = mess;
        this.SMS = sms;
        this.Chsize = Chsize;
        this.CheckBox = checkBox;
    }


    //Getting All The Details
    public int getId() {
        return this.id;
    }

    public String getAssetcode() {
        return this.assetcode;
    }

    public String getMessage()
    {
        return this.message;
    }

    public int getINTERNET() {
        return this.INTERNET;
    }
    public int getSMS() {
        return this.SMS;
    }

    public int getChsize(){
        return this.Chsize;
    }

    public String getCheckBox() {
        return CheckBox;
    }

    //Get Ends Here

    //Set Starts here

    public void setId(int id) {
        this.id = id;
    }

    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setINTERNET(int INTERNET) {
        this.INTERNET = INTERNET;
    }

    public void setSMS(int SMS) {
        this.SMS = SMS;
    }

    public void setChsize(int chsize) {
        this.Chsize = chsize;
    }

    public void setCheckBox(String checkBox) {
        this.CheckBox = checkBox;
    }

    //Set Ends Here.
}
