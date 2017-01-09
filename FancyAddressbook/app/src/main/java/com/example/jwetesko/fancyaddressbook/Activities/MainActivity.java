package com.example.jwetesko.fancyaddressbook.Activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jwetesko.fancyaddressbook.Adapters.ContactListAdapter;
import com.example.jwetesko.fancyaddressbook.Entities.Contact;
import com.example.jwetesko.fancyaddressbook.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> allContacts;
    private ArrayList<Contact> allContactsObj;

    private ListView contactListView;
    private ContactListAdapter contactAdapter;

    private Context context = this;

    public static final String ACTION_NEW_MSG = "broadcastReceiver";
    public static final String SP_NEW_MESSAGE = "sharedPreferences";
    public static final String MSG_FIELD = "message";
    public static final String FANCY_PREFS = "fancySharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        Button btn = (Button) findViewById(R.id.get_contacts);

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayContacts();
                initUI();
                sendMessage(allContacts);
                putToSharedPreferences(allContacts);
            }
        });
    }

    private void initUI() {
        if (allContactsObj.size() > 0) {
            contactListView = (ListView) findViewById(R.id.contact_list);
            contactAdapter = new ContactListAdapter(allContactsObj, context);
            contactListView.setAdapter(contactAdapter);
        }
        else {
            System.out.println("No contacts loaded");
        }
    }

    private void sendMessage(ArrayList<String> msg) {
        Intent intent = new Intent();
        intent.setAction(ACTION_NEW_MSG);
        intent.putExtra(MSG_FIELD, msg);
        sendBroadcast(intent);
    }

    private void putToSharedPreferences(ArrayList<String> msg) {
        SharedPreferences sharedPrefs = getSharedPreferences(FANCY_PREFS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(msg);

        editor.putString(SP_NEW_MESSAGE, json);
        editor.apply();
    }

    private void displayContacts() {
        allContacts = new ArrayList<>();
        allContactsObj = new ArrayList<>();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = getContentResolver().query(uri, projection, null, null, null);

        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        int i = 0;
        if (people.moveToFirst()) {
            do {
                String name = people.getString(indexName);
                String number = people.getString(indexNumber);
                Contact ct = new Contact(i, name, number);

                allContacts.add(name + "--" + number);
                allContactsObj.add(ct);
                i++;
            } while (people.moveToNext());
        }
        people.close();
    }
}