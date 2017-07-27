package com.example.marianflanagan.paws.customer;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marianflanagan.paws.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 * Referencing Customer Login Activity
 * @reference https://www.youtube.com/watch?v=FTmdSGBlhWA
 *
 *
 */


public class CustomerLogin extends AppCompatActivity {

    private static final String TAG = "CustomerLogin";

    private EditText customerEmail, customerPassword;
    private Button btnCLogin, btnCReg;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        customerEmail = (EditText) findViewById(R.id.customerEmail);
        customerPassword = (EditText) findViewById(R.id.customerPassword);
        btnCLogin = (Button) findViewById(R.id.btnCLogin);
        btnCReg = (Button) findViewById(R.id.btnCReg);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                    Intent intent = new Intent(CustomerLogin.this, CustomerProfile.class);
                    startActivity(intent);
                }
            }
        };

        btnCLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = customerEmail.getText().toString();
                String password = customerPassword.getText().toString();

                if (!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, password);
                }else {
                    toastMessage("All fields were not filled in.");
                }

            }
        });


        btnCReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLogin.this, CustomerRegistration.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    //Method for Login Toast Message

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}