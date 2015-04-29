package com.noname.batanbensigar.transientcontacts;

import android.content.Context;
/**
 * Created by Batan Bensigar on 4/29/2015.
 */
public class ContactsManager {

    public void ContactsManager() {

    }

    public static boolean AddContact(Context context, TContact tContact) {

//      Add the contact into the Android People application
        return tContact.saveContact(context);
    }
}
