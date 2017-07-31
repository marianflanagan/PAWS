package com.example.marianflanagan.paws.model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DogSpinAdapter extends ArrayAdapter<Dog> {
    private Context context;
    private ArrayList<Dog> values;


    public DogSpinAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Dog> objects) {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;
    }

    public int getCount() {
        return values.size();
    }

    public Dog getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(getItem(position).getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(getItem(position).getName());

        return label;
    }

}
