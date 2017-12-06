package com.example.fragments_configuration_changes_and_retaining_objects;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by clive on 20-May-14.
 * www.101apps.co.za
 */
public class SavedFragment extends Fragment {

    // data object we want to save
    private MyDataObject data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(MyDataObject data) {
        this.data = data;
    }

    public MyDataObject getData() {
        return data;
    }
}
