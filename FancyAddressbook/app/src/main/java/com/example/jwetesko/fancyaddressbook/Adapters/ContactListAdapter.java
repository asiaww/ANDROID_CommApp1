package com.example.jwetesko.fancyaddressbook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jwetesko.fancyaddressbook.Entities.Contact;
import com.example.jwetesko.fancyaddressbook.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.view.View.GONE;

/**
 * Created by jwetesko on 09.01.17.
 */

public class ContactListAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contacts = new ArrayList<>();

    public ContactListAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return this.contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View contactRow;

        if (convertView == null) {
            contactRow = LayoutInflater.from(context).inflate(R.layout.contact_list_row, parent, false);
        } else {
            contactRow = convertView;
        }

        bindContactsToView(getItem(position), contactRow);
        return contactRow;
    }

    private void bindContactsToView(Contact contact, View commentRow) {

        TextView contactNameTV = (TextView) commentRow.findViewById(R.id.contact_name);
        contactNameTV.setText(contact.getName());

        TextView contactNumberTV = (TextView) commentRow.findViewById(R.id.contact_number);
        contactNumberTV.setText(contact.getNumber());
    }
}

