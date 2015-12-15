package com.example.administrator.cmrl;

import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by administrator on 14/12/15.
 */
public class SendSMS {
    String assetcode;

    SendSMS(String assetcode)
    {
        this.assetcode = assetcode;
    }

    boolean send(String number)
    {
        String nowTime;
        Calendar timePresent = Calendar.getInstance();
        nowTime = timePresent.get(Calendar.DATE) + "/" +
                timePresent.get(Calendar.MONTH) + "/" +
                timePresent.get(Calendar.YEAR) + "::" +
                timePresent.get(Calendar.HOUR) + ":" +
                timePresent.get(Calendar.MINUTE);
        //Sending SMS to all the people specified.
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, "Changes Made to " + this.assetcode + " Timestamp - " + nowTime + ".", null, null);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
