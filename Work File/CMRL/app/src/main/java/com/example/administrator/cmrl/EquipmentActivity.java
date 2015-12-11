package com.example.administrator.cmrl;


import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.TabLayout;

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
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.cmrl.app.AppConfig;
import com.example.administrator.cmrl.app.AppController;

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
    private TextView AssetName;
    private Button proceed;
    private Button goback;
    private String ASSETCODE;
    private LinearLayout IL;
    private String nowTime;
    private String keyJSON[];
    private String valJSON[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        //Getting Values from Old Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ASSETCODE = extras.getString("ASSET_CODE");
            getAssetDetails(ASSETCODE);
            Log.v(TAG,ASSETCODE);
        }


        //Going Further with Proceed
        proceed = (Button) findViewById(R.id.btnproceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EquipmentActivity.this, "Hence Going Further", Toast.LENGTH_SHORT).show();
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

    private void getAssetDetails(final String ASSETCODE)
    {

        Calendar timePresent = Calendar.getInstance();
        nowTime = timePresent.get(Calendar.DATE) + "/" +
                timePresent.get(Calendar.MONTH) + "/" +
                timePresent.get(Calendar.YEAR) + "::" +
                timePresent.get(Calendar.HOUR) + ":" +
                timePresent.get(Calendar.MINUTE);

        String asset_req = "Asset_Request";
        IL = (LinearLayout) findViewById(R.id.llInner);
        AssetCode = (TextView) findViewById(R.id.tvAssetCode);
        AssetName = (TextView) findViewById(R.id.tvAssetName);
        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        final CheckBox[] Machine = new CheckBox[20];

        for(int i=1;i<=8;i++) {
            int resID = getResources().getIdentifier("checkBox" + i, "id", getPackageName());
            Machine[i-1] = (CheckBox)findViewById(resID);

        }

        StringRequest req = new StringRequest(Request.Method.POST,
                AppConfig.URL_ASSETCODE,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "AssetCode Response: " + response);

                try {
                    JSONObject someobj = new JSONObject(response);
                    String assetcode = someobj.getString("assetcode");
                    Toast.makeText(EquipmentActivity.this,assetcode, Toast.LENGTH_SHORT).show();
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

}


/*
                        int rows=0;

                        //Getting Array of values from JSON OBJECT.
                        Iterator<String> iter = mainObj.keys();
                        while (iter.hasNext()) {

                            String key = iter.next();
                            keyJSON[rows] = key;

                            try {
                                Object value = mainObj.get(key);
                                valJSON[rows] = value.toString();
                                //Storing the values for specific Keys.

                            } catch (JSONException e) {
                                // Something went wrong!
                                Log.v(TAG,"KEY JSON OR VAL JSON Problem.");
                            }
                        }


                        AssetCode.setText(valJSON[2]);
                        AssetName.setText(valJSON[3]);
 */