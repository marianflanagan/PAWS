package com.example.marianflanagan.paws.customer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.Booking;
import com.example.marianflanagan.paws.model.Dog;

import java.util.ArrayList;

/**
 * Created by marianflanagan on 31/07/2017.
 */

class CustomBookingsAdapter extends ArrayAdapter<Booking> {
    private ArrayList<Booking> values;

    public CustomBookingsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Booking> objects) {
        super(context, resource, objects);
        this.values = objects;
    }

    public int getCount() {
        return values.size();
    }

    public Booking getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_booking_summary_content, null);
        }

        Booking p = getItem(position);

        if (p != null) {
            final String bookingPet = p.getDogId();
            final String bookingDate = p.getDate();
            final String bookingTime = p.getTime();
            final String bookingSitter = p.getSitterId();

            TextView bookingPetTextView = (TextView) v.findViewById(R.id.bookingPet);
            bookingPetTextView.setText(bookingPet);
            TextView bookingDateTextView = (TextView) v.findViewById(R.id.bookingDate);
            bookingDateTextView.setText(bookingDate);
            TextView bookingTimeTextView = (TextView) v.findViewById(R.id.bookingTime);
            bookingTimeTextView.setText(bookingTime);
            TextView bookingSitterTextView = (TextView) v.findViewById(R.id.bookingSitter);
            bookingSitterTextView.setText(bookingSitter);
        }

        return v;
    }
}
