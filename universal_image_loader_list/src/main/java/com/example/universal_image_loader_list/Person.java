package com.example.universal_image_loader_list;

/**
 * Created by clive on 01-Jun-14.
 * person class
 * contains details for a person
 * www.101apps.co.za
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
