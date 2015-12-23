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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.cmrl.app.AppConfig;
import com.example.administrator.cmrl.app.AppController;
import com.example.administrator.cmrl.helper.Pendingtasks;
import com.example.administrator.cmrl.helper.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class CheckTaskActivity extends AppCompatActivity {

    private static final String TAG = EquipmentActivity.class.getSimpleName();
    private String ASSETCODE;
    private Button goback;
    private String useremail;
    private TextView ACNAME;
    private TextView ACMessage;
    private Button ACInternet;
    private Button ACSMS;
    private int chkinternet;
    private int chksms;
    private SQLiteHandler db;
    private Tasks singletask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goback = (Button) findViewById(R.id.btngoback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Initiallizing db
        db = new SQLiteHandler(getApplicationContext());
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
        useremail = user.get("email");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ASSETCODE = extras.getString("ASSETCODE");
            singletask = db.gettaskDetail(ASSETCODE);
        }

        ACNAME = (TextView) findViewById(R.id.acname);
        ACMessage = (TextView) findViewById(R.id.acmessage);
        ACInternet = (Button) findViewById(R.id.btninternet);
        ACSMS = (Button) findViewById(R.id.btnsms);

        ACNAME.setText(singletask.getAssetcode());
        ACMessage.setText(singletask.getMessage());

        if(singletask.getINTERNET() == 1)
            ACInternet.setClickable(false);
        if(singletask.getSMS() == 1)
            ACSMS.setClickable(false);


        ACInternet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(isNetworkAvailable())
                {

                    sendAssetDetails(ASSETCODE);
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
                smser.send("8144087395");
                chksms = 1;
                changevalue();
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void changevalue()
    {
        if( chkinternet== 1 || chksms == 1) {
            if(chkinternet == 1 && chksms ==1)
            {
                int deletechk = db.deletetasks(ASSETCODE);
                finish();
            }
            else {
                singletask.setINTERNET(chkinternet);
                singletask.setSMS(chksms);
                db.updatetask(singletask);

            }
        }
    }

    private void sendAssetDetails(final String ASSETCODE)
    {
        final int Chsize = singletask.getCheckBox().length();
        String CheckBoxString = singletask.getCheckBox();
        final String[] CheckBoxValue = new String[Chsize];
        for(int i=0;i<Chsize;i++)
        {
            if(CheckBoxString.charAt(i) == '1')
                CheckBoxValue[i] = "1";
            else
                CheckBoxValue[i] = "0";

        }

        //Send the values along with assetcode to the URL
        String send_asset = "Send Asset";
        StringRequest req = new StringRequest(Request.Method.POST,
                AppConfig.URL_POSTASSET,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "AssetCode Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    // Check for error node in json
                    if (!error) {
                    }
                    else{
                        Toast.makeText(CheckTaskActivity.this, "Response was bad.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v(TAG, "Error in Json Object");
                }
            }

        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "AssetDetails Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("ASSETCODE", ASSETCODE);
                params.put("email",useremail);
                params.put("Size",String.valueOf(Chsize));
                for(int i=0;i<Chsize;i++)
                {
                    params.put("CheckBox_"+i, String.valueOf(CheckBoxValue[i]));
                }
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, send_asset);
    }
}
