package com.mobitel.nalaka.piccon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * User: nalakap
 * Date: 8/15/13
 * Time: 6:00 PM
 * Project :ContactPicker
 * Company : Mobitel(Pvt)Ltd
 */
public class ContactResult extends Activity {
    /*
    start cord for contact picker native activity
     */
    final int PICK_CONTACTS = 24;
    String pickedNumber;
    String pickedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);
        relativeLayout.setLayoutParams(rlp);
        //set black background while user pick a contact
        setContentView(relativeLayout);

        //start activity to pick contact
        Intent in = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(in, PICK_CONTACTS);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACTS & resultCode == RESULT_OK) {
            Uri contactData = data.getData();
            ContentResolver cr = getContentResolver();
            Cursor c = managedQuery(contactData,
                    new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
            if (c.moveToFirst()) {
                String id = c.getString(c
                        .getColumnIndex(ContactsContract.Contacts._ID));
                pickedName = c
                        .getString(c
                                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                // get all phone numbers for that contact id
                Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?" , new String[]{id}, null);

                ArrayList<String> contactNumbers = new ArrayList<String>();
                while (phones.moveToNext()) {
                    String number = phones.getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    number = number.replace("-", "");
                    contactNumbers.add(number);
                }

                phones.close();


                if (contactNumbers.size() > 1) {
                    // oh we got list of numbers related to this contact ,witch one you want

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    final CharSequence nums[] = contactNumbers
                            .toArray(new CharSequence[contactNumbers.size()]);
                    builder.setItems(nums,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    pickedNumber = nums[which].toString();
                                    putAndExit();
                                }
                            });
                    builder.setCancelable(false);
                    builder.setTitle("Tap the Number to Select");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    // simply add this number to the number field
                    if (!contactNumbers.isEmpty()) {
                        pickedNumber = contactNumbers.remove(0);
                    } else {
                        pickedNumber = "";
                    }

                    putAndExit();

                }

            }

        }


    }

    /*
    put retrieved data into intent and finish the activity
     */
    private void putAndExit() {
        Intent result = new Intent();
        Bundle b = new Bundle();

        b.putString("name", pickedName);
        b.putString("number", pickedNumber);

        result.putExtras(b);

        setResult(RESULT_OK, result);
        finish();
    }
}

