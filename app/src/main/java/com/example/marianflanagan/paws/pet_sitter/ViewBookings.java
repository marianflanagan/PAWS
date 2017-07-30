package com.example.marianflanagan.paws.pet_sitter;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class ViewBookings extends AppCompatActivity {

    private static final String TAG = "ViewBookings";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private ListView view_bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);


        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("bookings");
        view_bookings = (ListView) findViewById(R.id.view_bookings);

        //retrieving the name from Firebase for the list view
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

                //Setting the listview with name retrieved from database
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ViewBookings.this,
                        android.R.layout.simple_list_item_1, dogNames);
                view_bookings.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("marian", databaseError.getMessage());
            }
        });
    }
}




