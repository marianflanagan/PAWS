package com.example.marianflanagan.paws.customer;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.Dog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPets extends AppCompatActivity {

    private FloatingActionButton floatingbtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("dogs");

        myRef.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Dog dog = postSnapshot.getValue(Dog.class);
                    if (mAuth.getCurrentUser().getUid().equals(dog.getUserId())) {
                        //TODO ADD DOGS TO LIST

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("brian", databaseError.getMessage());
            }
        });

        floatingbtn = (FloatingActionButton) findViewById(R.id.floatingtbn);

        //method to move to pet details activity


        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPets.this, PetDetails.class);
                startActivity(i);
            }
        });


    }
}
