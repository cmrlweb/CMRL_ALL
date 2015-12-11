package com.example.administrator.cmrl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class PendingActivity extends AppCompatActivity {

    private List<EditText> editTextList = new ArrayList<EditText>();
    private List<Button> ButtonList = new ArrayList<Button>();
    private Button gback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gback = (Button) findViewById(R.id.btngoback);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llpinner);

        gback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private TableLayout tableLayout(int count) {
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setStretchAllColumns(true);
        int NoRows = count;

        for(int i=0;i<NoRows;i++)
        {

        }
        return tableLayout;
    }

    private TableRow createOneFullRow(int rowid,String assetcode) {
        TableRow tableRow = new TableRow(this);
        tableRow.setPadding(0, 10, 0, 0);
        Button Checkbutton = new Button(this);
        tableRow.addView(editText(rowid,assetcode));
        tableRow.addView(Checkbutton);
        ButtonList.add(rowid,Checkbutton);
        return tableRow;
    }

    private EditText editText(int rowid,String assetcode) {
        EditText editText = new EditText(this);
        editText.setId(Integer.valueOf(rowid));
        editText.setHint(assetcode);
        editTextList.add(editText);
        return editText;
    }
}
