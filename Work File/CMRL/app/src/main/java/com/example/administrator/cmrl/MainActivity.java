package com.example.administrator.cmrl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.cmrl.app.AppConfig;
import com.example.administrator.cmrl.app.AppController;
import com.example.administrator.cmrl.helper.Pendingtasks;
import com.example.administrator.cmrl.helper.SQLiteHandler;
import com.example.administrator.cmrl.helper.SessionManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private Button btnqrcode;
    private Button btnPending;
    private ProgressDialog pdia;
    private Pendingtasks tasksdb = Pendingtasks.getHelper(this);

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnqrcode = (Button) findViewById(R.id.btnqrcode);
        btnPending = (Button) findViewById(R.id.btnPending);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        btnqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrintent = new Intent(MainActivity.this,QRActivity.class);
                startActivityForResult(qrintent, 0);
            }
        });

        btnPending.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Create a new Intent for all the history of works to be done.
                Intent pintent = new Intent(MainActivity.this,EquipmentActivity.class);
                startActivityForResult(pintent,1);
            }
        });


    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */


    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        //Cancelling all Requests
        AppController.getInstance().cancelPendingRequests(TAG);

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pdia.isShowing())
            pdia.show();
    }

    private void hideDialog(){
        if (pdia.isShowing()) {
            try
            {
                Thread.sleep(100);
                pdia.dismiss();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

        }

    }
}
