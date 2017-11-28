package com.example.drawer_navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment that displays the quote text
 * www.101apps.co.za
 */
public class QuotationFragment extends Fragment {
    public static final String ARG_QUOTE_NUMBER = "quote_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quotes, container, false);
        int arrayIndex = getArguments().getInt(ARG_QUOTE_NUMBER);

        // get the text view to display the text
        TextView textViewQuote = (TextView) rootView.findViewById(R.id.text);
        // get the quote
        String quote = getResources().getStringArray(R.array.quotes_array)[arrayIndex];

        // get the quoter
        String quoter = getResources().getStringArray(R.array.quotes_array_list)[arrayIndex];
        textViewQuote.setText(quote);

        // set the title in the action bar to the selected quoter
        getActivity().setTitle(quoter);

        return rootView;
    }
}

