package com.example.marianflanagan.paws.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.User;
import com.example.marianflanagan.paws.pet_sitter.EditPetSitterDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditCustomerDetails extends AppCompatActivity {

    private static final String TAG = "EditCustomerDetails";


    private EditText etProfileName;
    private EditText etPhone;
    private EditText etAddress;
    private Button btnCPSave;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_details);

        //declaring variables

        etProfileName = (EditText) findViewById(R.id.etProfileName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnCPSave = (Button) findViewById(R.id.btnCPSave);


        //declaring firebase objects

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");

        btnCPSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Attempting to add data to database.");
                String newName = etProfileName.getText().toString();
                String newAddress = etAddress.getText().toString();
                String newTelephone = etPhone.getText().toString();

                if (newName.equals("") || newAddress.equals("") || newTelephone.equals("")) {
                    Toast.makeText(EditCustomerDetails.this, R.string.details_validation_error_message, Toast.LENGTH_LONG).show();
                    return;
                }

                FirebaseUser currentUser = mAuth.getCurrentUser();
                String userID = currentUser.getUid();

                Toast.makeText(EditCustomerDetails.this, R.string.details_added_sucess_message, Toast.LENGTH_LONG).show();

                User user = new User(newName, newTelephone, newAddress);
                myRef.child(userID).setValue(user);
                etAddress.setText("");
                etProfileName.setText("");
                etPhone.setText("");

            }


        });
    }
}




