package com.example.mrmilk.activities.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrmilk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    View view;
    TextView textView;
    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order, container, false);
        textView = view.findViewById(R.id.orderTxt);
        textView.setText(("Order Fragment"));

        return view;
    }
}
