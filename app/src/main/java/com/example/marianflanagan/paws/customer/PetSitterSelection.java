package com.example.marianflanagan.paws.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.booking.BookingSummaryActivity;
import com.example.marianflanagan.paws.model.PetSitter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PetSitterSelection extends AppCompatActivity {
    private static String TIME = "time";
    private static String DATE = "date";
    private static String DOG = "dog";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ListView petSitter;

    private String selectedDate;
    private String selectedTime;
    private String selectedDog;

    public static void init(Context context,String dog, String date, String time) {
        Intent intent = new Intent(context, PetSitterSelection.class);
        intent.putExtra(DATE, date);
        intent.putExtra(TIME, time);
        intent.putExtra(DOG, dog);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_sitter_selection);

        selectedDate = getIntent().getStringExtra(DATE);
        selectedTime = getIntent().getStringExtra(TIME);
        selectedDog = getIntent().getStringExtra(DOG);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("PetSitter");

        petSitter = (ListView) findViewById(R.id.pet_sitter);

        //retrieving the name from Firebase for the list view
        myRef.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> petSitterNames = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PetSitter sitter = postSnapshot.getValue(PetSitter.class);
                    petSitterNames.add(sitter.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(PetSitterSelection.this,
                        android.R.layout.simple_list_item_1, petSitterNames);
                petSitter.setAdapter(arrayAdapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("marian", databaseError.getMessage());
            }

        });

        petSitter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                BookingSummaryActivity.init(v.getContext(), selectedDate, selectedTime, selectedDog,
                        petSitter.getAdapter().getItem(position).toString());
            }
        });

    }
}
