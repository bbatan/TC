package com.noname.batanbensigar.transientcontacts;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class SaveContact extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_contact);

        Intent intent = getIntent();
        String sNumber = intent.getStringExtra("ContactNumber");

        TextView txtNumber = (TextView) findViewById(R.id.fld_contact_number);
        txtNumber.setText(sNumber);

        TextView txtNumberEdit = (TextView) findViewById(R.id.fld_edit_contact_number);
        txtNumberEdit.setText(sNumber);

        setOnFocusChangeListener(txtNumberEdit, R.id.img_edit_contact_number);
        setOnFocusChangeListener(findViewById(R.id.fld_edit_contact_name), R.id.img_edit_contact_name);
        setOnFocusChangeListener(findViewById(R.id.fld_edit_retention_days), R.id.img_edit_retention_days);
        setOnFocusChangeListener(findViewById(R.id.fld_delete_confirm), R.id.img_delete_confirm);
        setOnFocusChangeListener(findViewById(R.id.fld_edit_notes), R.id.img_edit_notes);

        findViewById(R.id.fab_close_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.animate().scaleX(0.01f).scaleY(0.01f);
                finish();
            }
        });

        findViewById(R.id.btn_save_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TContact tContact = new TContact();

                String sPhoneNumber = String.valueOf(((TextView) findViewById(R.id.fld_edit_contact_number)).getText());
                String sName = String.valueOf(((TextView) findViewById(R.id.fld_edit_contact_name)).getText());
                String sNotes = String.valueOf(((TextView) findViewById(R.id.fld_edit_notes)).getText());
                String sDays = String.valueOf(((TextView) findViewById(R.id.fld_edit_retention_days)).getText());
                int nDays = 0;
                if (sDays != null && sDays.length() > 0) {
                    nDays = Integer.parseInt(sDays);
                } else nDays = R.string.default_text_retention_days;

                Switch swConfirm = (Switch) findViewById(R.id.fld_delete_confirm);
                boolean bConfirm = swConfirm.isChecked();

                tContact.setPhoneNumber(sPhoneNumber);
                tContact.setFullName(sName);
                tContact.setRetentionPeriod(nDays);
                tContact.setNeedConfirmation(bConfirm);
                tContact.setNotes(sNotes);

                if (ContactsManager.AddContact(view.getContext(), tContact)) {
                    Intent intent = new Intent(SaveContact.this, ContactSaved.class);
                    intent.putExtra("ContactNumber", sPhoneNumber);
                    intent.putExtra("ContactName", sName);
                    intent.putExtra("RetentionPeriod", nDays);
                    intent.putExtra("ConfirmDelete", bConfirm);
                    intent.putExtra("Notes", sNotes);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Error saving the contact, please add the contact manually in Transient Contacts", Toast.LENGTH_SHORT);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_contact, menu);
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

    private void setOnFocusChangeListener(final View view, final int iconID) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                ImageView imgIcon = (ImageView) findViewById(iconID);
                if (hasFocus) {
                    //Do color change here
                    imgIcon.setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_IN);
                } else {
                    imgIcon.setColorFilter(getResources().getColor(R.color.md_grey_600), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
    }
}
