package com.example.marianflanagan.paws.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.Dog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PetDetails extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private EditText dogName;
    private EditText dogBreed;
    private EditText dogAge;
    private Button saveDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("dogs");

        dogName = (EditText) findViewById(R.id.etPetName);
        dogAge = (EditText) findViewById(R.id.etAge);
        dogBreed = (EditText) findViewById(R.id.etBreed);


        saveDetailsButton = (Button) findViewById(R.id.pet_details);

        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newDogName = dogName.getText().toString();
                String newDogAge = dogAge.getText().toString();
                String newDogBreed = dogBreed.getText().toString();

                if (newDogName.equals("") || newDogAge.equals("") || newDogBreed.equals("")) {
                    Toast.makeText(PetDetails.this, R.string.details_validation_error_message, Toast.LENGTH_LONG).show();
                    return;
                }

                FirebaseUser currentUser = mAuth.getCurrentUser();
                String userID = currentUser.getUid();

                Dog dog = new Dog(newDogName, newDogBreed,newDogAge ,userID);
                myRef.child(dog.getName() + newDogBreed + newDogAge ).setValue(dog);

                Toast.makeText(PetDetails.this, R.string.dog_entry_success_message, Toast.LENGTH_LONG).show();
                dogBreed.setText("");
                dogAge.setText("");
                dogName.setText("");
            }
        });
    }
}
