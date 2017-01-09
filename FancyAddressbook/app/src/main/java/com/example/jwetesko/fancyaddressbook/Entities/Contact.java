package com.example.jwetesko.fancyaddressbook.Entities;

/**
 * Created by jwetesko on 08.01.17.
 */

public class Contact {

    int ID;
    String name;
    String number;

    public Contact(int ID, String name, String number) {
        this.ID = ID;
        this.name = name;
        this.number = number;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void getName(String name) {
        this.name = name;
    }

    public void PhoneNumber(String number) {
        this.number = number;
    }
}
