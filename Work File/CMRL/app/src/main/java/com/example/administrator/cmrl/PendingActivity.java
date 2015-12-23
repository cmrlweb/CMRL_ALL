package com.example.administrator.cmrl;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cmrl.helper.Pendingtasks;
import com.example.administrator.cmrl.helper.SQLiteHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PendingActivity extends AppCompatActivity {

    private static final String TAG = EquipmentActivity.class.getSimpleName();
    private List<TextView> TextViewList = new ArrayList<TextView>();
    private List<Button> ButtonList = new ArrayList<Button>();
    private Button gback;
    private String ASSETCODE;
    private String CheckBoxValues;
    private SQLiteHandler db;
    private int taskdbsize;
    private TableLayout tlinner;
    private List<Tasks> alltasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing DB
        db = new SQLiteHandler(getApplicationContext());
        taskdbsize = db.gettaskrows();

        //Creating a table layout
        tlinner = (TableLayout) findViewById(R.id.tlinner);
        tlinner.setStretchAllColumns(true);

        //Get all Tasks and add it in the table
        alltasks = db.getallTasks();
        for(int i=0;i<taskdbsize;i++)
        {
            Tasks singletask;
            singletask = alltasks.get(i);
            tlinner.addView(createOneFullRow(i,singletask.getAssetcode()));
        }
        //End of Creating Table Layout.


        //Getting Values from Old Intent if we get it by any chance then.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ASSETCODE = extras.getString("ASSETCODE");
            CheckBoxValues = extras.getString("CheckBoxValues");
            Log.v(TAG,CheckBoxValues);
            Log.v(TAG, ASSETCODE);
            createtask(ASSETCODE,CheckBoxValues);
        }

        //onclick listeners for goback button
        gback = (Button) findViewById(R.id.btngoback);
        gback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //CheckButton Listeners.
        for(int i=0;i<ButtonList.size();i++)
        {
            final int finalI = i;
            ButtonList.get(i).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Tasks singletask = alltasks.get(finalI);

                    //Sending assetcode to a new intent having the Option to check whether its sent.
                    Intent check = new Intent(PendingActivity.this,CheckTaskActivity.class);
                    check.putExtra("ASSETCODE",singletask.getAssetcode());
                    startActivityForResult(check,5);
                }
            });
        }
    }

    private void createtask(String ASSETCODE, String checkBoxValues) {
        Tasks singletask = new Tasks();
        singletask.setAssetcode(ASSETCODE);
        singletask.setMessage("Base Internet Failed");
        singletask.setINTERNET(0);
        singletask.setSMS(0);
        singletask.setCheckBox(checkBoxValues);
        singletask.setChsize(checkBoxValues.length());
        db.addtasks(singletask);
    }

    private TableRow createOneFullRow(int rowid,String assetcode) {
        TableRow tableRow = new TableRow(this);
        //tableRow.setPadding(0, 10, 0, 0);
        Button Checkbutton = new Button(this);
        Checkbutton.setText("Check");
        tableRow.addView(editText(rowid, assetcode));
        tableRow.addView(Checkbutton);
        ButtonList.add(rowid, Checkbutton);
        return tableRow;
    }

    private TextView editText(int rowid,String assetcode) {
        TextView editText = new TextView(this);
        editText.setId(Integer.valueOf(rowid));
        editText.setText(assetcode);
        editText.setMaxWidth(50);
        TextViewList.add(editText);
        return editText;
    }
}
