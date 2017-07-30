package com.example.marianflanagan.paws.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marianflanagan.paws.R;
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

    private static final String TAG = "PetSitterSelection";


    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ListView pet_sitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_sitter_selection);


        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("petSitter");

        pet_sitter = (ListView) findViewById(R.id.pet_sitter);

        //retrieving the name from Firebase for the list view
        myRef.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> petSitterNames = new ArrayList<String>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PetSitter sitter = postSnapshot.getValue(PetSitter.class);
                    if (mAuth.getCurrentUser().getUid().equals(sitter.getUserId())){
                        petSitterNames.add(sitter.getName());
                    }
                }

                //Setting the listview with name retrieved from database
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PetSitterSelection.this,
                        android.R.layout.simple_list_item_1, petSitterNames);
                        pet_sitter.setAdapter(arrayAdapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("marian", databaseError.getMessage());
            }

        });

    }
}
