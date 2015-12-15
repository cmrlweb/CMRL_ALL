package com.example.administrator.cmrl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cmrl.helper.Pendingtasks;

import org.w3c.dom.Text;

import java.util.HashMap;

public class CheckTaskActivity extends AppCompatActivity {

    private static final String TAG = EquipmentActivity.class.getSimpleName();
    private String ASSETCODE;
    private String Message;
    private String internet;
    private String sms;
    private int ACVAL;
    private int chkinternet;
    private int chksms;
    private TextView ACNAME;
    private TextView ACMessage;
    private Button ACInternet;
    private Button ACSMS;
    private Pendingtasks tasksdb = Pendingtasks.getHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ASSETCODE = extras.getString("ASSETCODE");
            ACVAL = extras.getInt("ACVAL");
            fillvalue();
        }

        ACNAME = (TextView) findViewById(R.id.acname);
        ACMessage = (TextView) findViewById(R.id.acmessage);
        ACInternet = (Button) findViewById(R.id.btninternet);
        ACSMS = (Button) findViewById(R.id.btnsms);

        ACInternet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(isNetworkAvailable())
                {
                    chkinternet = 1;
                    changevalue();
                }
                else
                {
                    Toast.makeText(CheckTaskActivity.this, "No Network Yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ACSMS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                SendSMS smser = new SendSMS(ASSETCODE);
                //smser.send("8144087395");
                chksms =1;
                changevalue();
            }
        });

    }

    private void fillvalue()
    {
        ACNAME = (TextView) findViewById(R.id.acname);
        ACMessage = (TextView) findViewById(R.id.acmessage);
        ACInternet = (Button) findViewById(R.id.btninternet);
        ACSMS = (Button) findViewById(R.id.btnsms);

        HashMap<String,String> task = tasksdb.gettaskDetails(ASSETCODE);

        Message = task.get("MESSAGE");
        internet = task.get("INTERNET");
        sms = task.get("SMS");

        ACNAME.setText(ASSETCODE);
        ACMessage.setText(Message);

        chkinternet = Integer.parseInt(internet);
        chksms = Integer.parseInt(sms);

        if(chkinternet == 1)
        {
            ACInternet.setBackgroundColor(Color.GRAY);
            ACInternet.setClickable(false);
        }
        else
        {
            ACInternet.setClickable(true);
        }
        if(chksms == 1)
        {
            ACSMS.setBackgroundColor(Color.GRAY);
            ACSMS.setClickable(false);
        }
        else
        {
            ACSMS.setClickable(true);
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void changevalue()
    {
        if(chkinternet == 1 || chksms == 1) {
            if(chkinternet == 1 && chksms ==1)
            {
                int deletechk = tasksdb.deletetasks(ASSETCODE);
            }
            else {
                tasksdb.updatetasks(ASSETCODE,chkinternet,chksms);
            }
        }
    }
}
