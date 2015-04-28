package com.noname.batanbensigar.transientcontacts;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class CallEndActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_end);

        Intent intent = getIntent();
        String sNumber =  intent.getStringExtra("ContactNumber");
        TextView txtNumber = (TextView) findViewById(R.id.fld_contact_number);
        txtNumber.setText(sNumber);

        ImageButton btnYes = (ImageButton) findViewById(R.id.btn_add_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtNumber = (TextView) findViewById(R.id.fld_contact_number);
                String sNumber = String.valueOf(txtNumber.getText());

                Intent intent = new Intent(CallEndActivity.this, SaveContact.class);
                intent.putExtra("ContactNumber",sNumber);
                startActivity(intent);
            }
        });

        ImageButton btnNo = (ImageButton) findViewById(R.id.btn_add_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_end, menu);
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
