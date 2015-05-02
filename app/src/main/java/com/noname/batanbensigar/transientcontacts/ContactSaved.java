package com.noname.batanbensigar.transientcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ContactSaved extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_saved);

        Intent intent = getIntent();
        String sNumber = intent.getStringExtra("ContactNumber");
        ((TextView) findViewById(R.id.lbl_saved_contact_number)).setText(sNumber);
        String sName = intent.getStringExtra("ContactName");
        ((TextView) findViewById(R.id.lbl_saved_contact_name)).setText(sName);

        int nDays = intent.getIntExtra("RetentionPeriod", 0);
        if (nDays > 0) {
            String retentionText = "";
            retentionText = "saved for " + nDays + " day(s)";
            ((TextView) findViewById(R.id.lbl_saved_days)).setText(retentionText);
        }else {
            ((TextView) findViewById(R.id.lbl_saved_days)).setText("not saved after today");
        }
        boolean bConfirm = intent.getBooleanExtra("ConfirmDelete", true);
        if (bConfirm) ((TextView) findViewById(R.id.lbl_saved_confirmation)).setText("deleted after confirmation");
        else ((TextView) findViewById(R.id.lbl_saved_confirmation)).setText("deleted without confirmation");

        String sNote = intent.getStringExtra("Notes");
        ((TextView) findViewById(R.id.lbl_saved_notes)).setText(sNote);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_saved, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
