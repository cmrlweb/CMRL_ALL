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
import android.widget.Toast;

import com.example.administrator.cmrl.helper.Pendingtasks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PendingActivity extends AppCompatActivity {

    private static final String TAG = EquipmentActivity.class.getSimpleName();
    private List<EditText> editTextList = new ArrayList<EditText>();
    private List<Button> ButtonList = new ArrayList<Button>();
    private Button gback;
    private String ASSETCODE;
    private ProgressDialog pDialog;
    private List<CheckBox> CheckBoxList = new ArrayList<CheckBox>();
    private Pendingtasks tasksdb = Pendingtasks.getHelper(this);
    private int taskdbsize;
    private List<String> ALLAC;
    private JobScheduler mJobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        //Getting Values from Old Intent if we get it by any chance then.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pDialog.setMessage("Adding Pending Task.");
            showDialog();
            ASSETCODE = extras.getString("ASSET_CODE");
            CheckBoxList = (ArrayList<CheckBox>) getIntent().getSerializableExtra("CheckBoxList");
            Log.v(TAG, ASSETCODE);
            //Creating an SQL Table.
            createtask(ASSETCODE, CheckBoxList);
        }

        //onclick listeners for goback button
        gback = (Button) findViewById(R.id.btngoback);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llpinner);
        linearLayout.addView(tableLayout());
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
                    ALLAC = tasksdb.getalltasks();
                    String assetcodecheck = ALLAC.get(finalI);
                    //Sending assetcode to a new intent having the Option to check whether its sent.
                    Intent check = new Intent(PendingActivity.this,CheckTaskActivity.class);
                    check.putExtra("ASSETCODE",assetcodecheck);
                    check.putExtra("ACVAL",finalI);
                    ALLAC.clear();
                    startActivityForResult(check,4);
                }
            });
        }
    }

    private TableLayout tableLayout() {
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setStretchAllColumns(true);
        taskdbsize = tasksdb.getrows();
        ALLAC = tasksdb.getalltasks();
        for(int i=0;i<taskdbsize;i++)
        {
            String CAC = ALLAC.get(i);
            tableLayout.addView(createOneFullRow(i,CAC));
        }
        ALLAC.clear();
        return tableLayout;
    }

    private TableRow createOneFullRow(int rowid,String assetcode) {
        TableRow tableRow = new TableRow(this);
        tableRow.setPadding(0, 10, 0, 0);
        Button Checkbutton = new Button(this);
        Checkbutton.setText("Check");
        tableRow.addView(editText(rowid, assetcode));
        tableRow.addView(Checkbutton);
        ButtonList.add(rowid, Checkbutton);
        return tableRow;
    }

    private EditText editText(int rowid,String assetcode) {
        EditText editText = new EditText(this);
        editText.setId(Integer.valueOf(rowid));
        editText.setHint(assetcode);
        editTextList.add(editText);
        return editText;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createtask(String ASSETCODE,List<CheckBox> CheckBoxList)
    {
        String Message ="Base Internet Failed.";
        String CheckBoxValues= new String();
        Character[] CheckBoxArray = new Character[100];
        //Seperate Checbox Values and convert it into a string.
        for(int i=0;i<CheckBoxList.size();i++)
        {
            CheckBox cb = new CheckBox(this);
            cb = (CheckBox) CheckBoxList.get(i);

            if(cb.isChecked())
                CheckBoxArray[i]='1';

            else
                CheckBoxArray[i]='0';
        }

        boolean taskcreated = tasksdb.addtasks(ASSETCODE,Message,0,0);

        //Creating a Job Here itself.
        mJobScheduler = (JobScheduler) getSystemService( Context.JOB_SCHEDULER_SERVICE );
        JobInfo.Builder builder = new JobInfo.Builder( 1,new ComponentName( getPackageName(),JobSchedule.class.getName() ) );
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPeriodic(2000);
        builder.setMinimumLatency(100);
        if( mJobScheduler.schedule( builder.build() ) <= 0 ) {
            Toast.makeText(PendingActivity.this, "Problem With adding Job Scheduler . Fix it Manually.", Toast.LENGTH_SHORT).show();
        }


        hideDialog();
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
