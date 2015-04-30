package com.noname.batanbensigar.transientcontacts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class AppCallReceiver extends BroadcastReceiver {

    private String incomingNumber;
    static long start_time, end_time;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            start_time = System.currentTimeMillis();

            //Check if the number is new and need to be inserted
            // TODO: develop this into full blown implementation --> ContactsManager.isNewContact(incomingNumber);
        } else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_IDLE)
                || intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            end_time = System.currentTimeMillis();
            long total_time = end_time - start_time;
            if (total_time > 1000) {
                // This code will execute when the call is disconnected and if the call duration is over 1 sec
                Intent appIntent = new Intent(context, CallEndActivity.class);
                appIntent.putExtra("ContactNumber", incomingNumber);
                appIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(appIntent);
            }
        }
    }
}