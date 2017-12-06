package com.example.using_bitmaps_efficiently;

/**
 * Created by sargiskh on 12/6/2017.
 */

public class Person {

    private String name;
    private int phone;
    private int iconId;
    private String phrase;

    public Person(String name, int phone, int iconId, String phrase) {
        this.name = name;
        this.phone = phone;
        this.iconId = iconId;
        this.phrase = phrase;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public int getIconId() {
        return iconId;
    }

    public String getPhrase() {
        return phrase;
    }

}
