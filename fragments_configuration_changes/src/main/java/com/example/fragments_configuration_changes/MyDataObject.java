package com.example.fragments_configuration_changes;

import android.graphics.Bitmap;

/**
 * Created by clive on 20-May-14.
 * www.101apps.co.za
 */
public class MyDataObject {

    private String name;
    private String email;
    private String phone;
    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }


    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getEmail() {
        return email;
    }


    public void setPhone(String newPhone) {
        phone = newPhone;
    }

    public String getPhone() {
        return phone;
    }
}
