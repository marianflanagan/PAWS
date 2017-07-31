package com.example.marianflanagan.paws.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.Booking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerViewBookings extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ListView bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_bookings);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Bookings");

        bookingList = (ListView) findViewById(R.id.customer_view_bookings);

        //retrieving the name from Firebase for the list view
        myRef.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Booking> list = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Booking booking = postSnapshot.getValue(Booking.class);
                    if (mAuth.getCurrentUser().getUid().equals(booking.getCustomerId())) {
                        list.add(booking);
                    }
                }

                CustomBookingsAdapter arrayAdapter = new CustomBookingsAdapter(CustomerViewBookings.this,
                        R.layout.activity_booking_summary_content, list);
                bookingList.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }
}
