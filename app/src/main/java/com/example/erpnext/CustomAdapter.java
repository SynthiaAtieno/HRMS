package com.example.erpnext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private List<String> months;


    public CustomAdapter(@NonNull Context context, int resource, List<String> months) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        this.months = months;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, parent, false);
        }

        // Get the TextView in your custom layout
        TextView textView = view.findViewById(android.R.id.text1);

        // Set the text for the TextView based on the month at the given position
        String month = months.get(position);
        if (month != null) {
            textView.setText(month);
        }

        return view;
    }
}
