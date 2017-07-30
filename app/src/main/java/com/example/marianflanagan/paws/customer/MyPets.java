package com.example.marianflanagan.paws.customer;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MyPets extends AppCompatActivity {

    private static final String TAG = "MyPets";

    private FloatingActionButton floatingbtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ListView list_pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("dogs");

        list_pets = (ListView) findViewById(R.id.list_pets);

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
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MyPets.this,
                        android.R.layout.simple_list_item_1, dogNames);
                        list_pets.setAdapter(arrayAdapter);

                    }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("marian", databaseError.getMessage());
            }
        });

        floatingbtn = (FloatingActionButton) findViewById(R.id.floatingtbn);

        //method to move to pet details activity
        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPets.this, PetDetails.class);
                startActivity(intent);
            }
        });


    }
}
