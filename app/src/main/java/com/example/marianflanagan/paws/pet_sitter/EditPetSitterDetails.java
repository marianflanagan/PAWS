package com.example.marianflanagan.paws.pet_sitter;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.customer.PetDetails;
import com.example.marianflanagan.paws.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by marianflanagan
 * Student ID - x16119444
 */

public class EditPetSitterDetails extends AppCompatActivity {

    private static final String TAG = "EditPetSitterDetails";


    private EditText ps_name;
    private EditText ps_phone;
    private EditText ps_address;
    private Button buttonPSave;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet_sitter_details);

        //declaring variables

        ps_address = (EditText) findViewById(R.id.ps_address);
        ps_name = (EditText) findViewById(R.id.ps_name);
        ps_phone = (EditText) findViewById(R.id.ps_phone);
        buttonPSave = (Button) findViewById(R.id.buttonPSave);


        //declaring firebase objects

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("PetSitter");

        buttonPSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Attempting to add data to database.");

                String newName = ps_name.getText().toString();
                String newAddress = ps_address.getText().toString();
                String newTelephone = ps_phone.getText().toString();

                if ( newName.equals("") || newAddress.equals("") || newTelephone.equals("")) {
                    Toast.makeText(com.example.marianflanagan.paws.pet_sitter.EditPetSitterDetails.this, R.string.details_validation_error_message, Toast.LENGTH_LONG).show();
                    return;
                }

                FirebaseUser currentUser = mAuth.getCurrentUser();
                String userID = currentUser.getUid();

                Toast.makeText(EditPetSitterDetails.this, R.string.details_added_sucess_message, Toast.LENGTH_LONG).show();

                User user = new User(newName, newTelephone, newAddress);
                myRef.child(userID).setValue(user);

                ps_name.setText("");

                ps_address.setText("");

                ps_phone.setText("");

            }


        });
    }
}




