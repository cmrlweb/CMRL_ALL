package com.example.administrator.cmrl;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import android.support.design.widget.TabLayout;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.cmrl.app.AppConfig;
import com.example.administrator.cmrl.app.AppController;
import com.example.administrator.cmrl.SendSMS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentActivity extends AppCompatActivity {

    private static final String TAG = EquipmentActivity.class.getSimpleName();
    private TextView AssetCode;
    private Button proceed;
    private Button goback;
    private String ASSETCODE;
    private LinearLayout IL;
    private String nowTime;
    private String CheckBoxValues;
    private List<CheckBox> CheckBoxList = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_equipment);


            //Getting Values from Old Intent
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                ASSETCODE = extras.getString("ASSET_CODE");
                Log.v(TAG,ASSETCODE);
            }

            //If Internet is not there
            final Intent jintent = new Intent(EquipmentActivity.this,PendingActivity.class);

            //Going Further with Proceed
            proceed = (Button) findViewById(R.id.btnproceed);
            proceed.setVisibility(View.VISIBLE);
            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ButtonText = proceed.getText().toString();
                    if(ButtonText.equals("Fetch Data")){
                        if(isNetworkAvailable()) {
                            Toast.makeText(EquipmentActivity.this, "Fetching CheckBoxes.", Toast.LENGTH_SHORT).show();
                            getAssetDetails(ASSETCODE);
                            proceed.setText("Save Data");
                        }
                        else
                        {
                            Toast.makeText(EquipmentActivity.this, "Network Not Available.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        if(isNetworkAvailable()) {
                            sendAssetDetails(ASSETCODE);
                            SendSMS smser = new SendSMS(ASSETCODE);
                            //smser.send("8144087395");
                        }
                        else
                        {
                            final int Chsize = CheckBoxList.size();
                            Log.v(TAG,String.valueOf(Chsize));
                            for(int i=0;i<Chsize;i++) {
                                CheckBox cb = new CheckBox(getApplicationContext());
                                cb = CheckBoxList.get(i);
                                if (cb.isChecked()) {
                                    CheckBoxValues += "1";
                                } else {
                                    CheckBoxValues += "0";
                                }
                            }

                            Log.v(TAG,CheckBoxValues);

                            jintent.putExtra("ASSETCODE",ASSETCODE);
                            jintent.putExtra("CheckBoxValues", CheckBoxValues);
                            startActivityForResult(jintent,4);
                        }
                        proceed.setVisibility(View.GONE);
                        proceed.setText("Fetch Data");
                    }

                }
            });

            //Returning Back to previous Intent
            goback = (Button) findViewById(R.id.btngoback);
            goback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getAssetDetails(final String ASSETCODE)
    {

        Calendar timePresent = Calendar.getInstance();
        nowTime = timePresent.get(Calendar.DATE) + "/" +
                timePresent.get(Calendar.MONTH) + "/" +
                timePresent.get(Calendar.YEAR) + "::" +
                timePresent.get(Calendar.HOUR) + ":" +
                timePresent.get(Calendar.MINUTE);

        String asset_req = "Asset_Request";
        IL = (LinearLayout) findViewById(R.id.llFinner);
        AssetCode = (TextView) findViewById(R.id.tvAssetCode);

        StringRequest req = new StringRequest(Request.Method.POST,
                AppConfig.URL_ASSETCODE,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "AssetCode Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    JSONArray MachineDesc =jObj.getJSONArray("MachineDesc");
                    JSONArray Value =jObj.getJSONArray("Machvalue");

                    // Check for error node in json
                    if (!error) {
                        for(int i=0;i < MachineDesc.length(); i++)
                        {
                            CheckBox cb = new CheckBox(getApplicationContext());
                            String Machinefunc = MachineDesc.getJSONObject(i).getString("name");
                            cb.setText(Machinefunc);
                            Log.v(TAG, Machinefunc);

                            if(Value.getJSONObject(i).getInt("name") != 0)
                            {
                                cb.setChecked(true);
                            }
                            cb.setTextColor(Color.BLACK);
                            IL.addView(cb);
                            CheckBoxList.add(cb);
                        }
                        AssetCode.setText(ASSETCODE);
                    }
                    else{
                        Toast.makeText(EquipmentActivity.this, "Response was bad.", Toast.LENGTH_SHORT).show();
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
                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, asset_req);
    }

    private void sendAssetDetails(final String ASSETCODE)
    {
        //Extract 0 or 1 for the CheckBox First.
        final int Chsize = CheckBoxList.size();
        final int CheckBoxValue[]= new int[Chsize];
        for(int i=0;i<Chsize;i++) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb = CheckBoxList.get(i);
            if (cb.isChecked()) {
                CheckBoxValue[i] = 0;
                CheckBoxValues += "1";
            } else {
                CheckBoxValue[i] = 1;
                CheckBoxValues += "0";
            }
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
                        Toast.makeText(EquipmentActivity.this, "Response was bad.", Toast.LENGTH_SHORT).show();
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