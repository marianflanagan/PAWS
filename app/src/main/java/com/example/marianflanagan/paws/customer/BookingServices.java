package com.example.marianflanagan.paws.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.Dog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*
 * Referencing Calendar Activity
 * @reference https://www.youtube.com/watch?v=hHjFIG0TtA0&index=27&list=PLgCYzUzKIBE8TUoCyjomGFqzTFcJ05OaC&t=5s
 *
 * Remainder of the class was created by Marian Flanagan
 * Student ID - x16119444
 */

public class BookingServices extends AppCompatActivity {

    private static final String TAG = "BookingServices";

    private TextView tvDate;
    private Button btnCalendar;
    private Button btnNext;
    private Spinner petSpinner;
    private Spinner spinnerTime;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_services);

        tvDate = (TextView) findViewById(R.id.tvDate);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnNext = (Button) findViewById(R.id.btnNext);
        petSpinner = (Spinner) findViewById(R.id.spinnerPet);
        spinnerTime = (Spinner)  findViewById(R.id.spinnerTime);


    //method adding times to spinner

        ArrayList<String> times = new ArrayList<>();
        times.add("8am");
        times.add("9am");
        times.add("10am");
        times.add("11am");
        times.add("12pm");
        times.add("1pm");
        times.add("2pm");
        times.add("3pm");
        times.add("4pm");
        times.add("5pm");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, times);
        spinnerTime.setAdapter(adapter);

        //method to retrieve the date information selected from the calendar activity

        Intent retrieveDate = getIntent();
        String date = retrieveDate.getStringExtra("date");
        tvDate.setText(date);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("dogs");

        myRef.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> dogNames = new ArrayList<String>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Dog dog = postSnapshot.getValue(Dog.class);
                    if (mAuth.getCurrentUser().getUid().equals(dog.getUserId())) {
                        dogNames.add(dog.getName());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookingServices.this,
                        android.R.layout.simple_spinner_item, dogNames);
                petSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("marian", databaseError.getMessage());
            }
        });

        //method to start calendar activity
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingServices.this, Calendar.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingServices.this, PetSitterSelection.class);
                startActivity(intent);
            }
        });

    }

}


