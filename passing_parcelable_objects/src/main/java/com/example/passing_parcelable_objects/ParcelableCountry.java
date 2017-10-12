package com.example.passing_parcelable_objects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */
public class ParcelableCountry implements Parcelable {

    private static final String TAG = "ParcelableCountry";
    private Country country;

    public Country getCountry() {
        Log.i(TAG, "in getCountry() - getting country object");
        return country;
    }

    public ParcelableCountry(Country country) {
//        creates a parcelable object containing the country object
        Log.i(TAG, "in constructor - constructing the ParcelableCountry object");
        this.country = country;
    }

    private ParcelableCountry(Parcel in) {
//        creates a country object from the contents of the parcel
        Log.i(TAG, "in constructor - construct Country object from the parcel");
        country = new Country();
        country.setName(in.readString());
        country.setCapital(in.readString());
        country.setPopulation(in.readInt());
    }

    @Override
    public int describeContents() {
        Log.i(TAG, "in describeContents()");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        Log.i(TAG, "in writeToParcel() - flattens the object into the parcel");
        parcel.writeString(country.getName());
        parcel.writeString(country.getCapital());
        parcel.writeInt(country.getPopulation());
    }

    /*creates an instance of our ParcelableCountry class from the received parcel*/
    public static final Creator<ParcelableCountry> CREATOR = new Creator<ParcelableCountry>() {

        @Override
        public ParcelableCountry createFromParcel(Parcel source) {
            Log.i(TAG, "in createFromParcel() - receives the parcel and creates a ParcelableCountry object");
            return new ParcelableCountry(source);
        }

        @Override
        public ParcelableCountry[] newArray(int size) {
            Log.i(TAG, "in Creator - in newArray()");
            return new ParcelableCountry[size];
        }
    };
}
