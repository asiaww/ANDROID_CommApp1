package com.example.jwetesko.fancyaddressbook.Entities;

import java.util.ArrayList;

/**
 * Created by jwetesko on 08.01.17.
 */

public class ContactList {
    ArrayList<Contact> contactList;

    public ContactList(ArrayList<Contact> contacts) {
        this.contactList = contacts;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }
}
