package com.example.marianflanagan.paws.pet_sitter;



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
 * Referencing PetSitter Login Activity
 * @reference https://www.youtube.com/watch?v=FTmdSGBlhWA
 *
 *
 */


public class PetSitterLogin extends AppCompatActivity {

    private static final String TAG = "PetSitterLogin";

    private EditText ps_email, ps_password;
    private Button button_login, button_reg;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_sitter_login);

        ps_email = (EditText) findViewById(R.id.ps_email);
        ps_password = (EditText) findViewById(R.id.ps_password);
        button_login = (Button) findViewById(R.id.button_login);
        button_reg = (Button) findViewById(R.id.button_reg);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                    Intent intent = new Intent(PetSitterLogin.this, PetSitterProfile.class);
                    startActivity(intent);
                }
            }
        };


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ps_email.getText().toString();
                String password = ps_password.getText().toString();

                if (!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, password);
                }else {
                    toastMessage("All fields were not filled in.");
                }

            }
        });


        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetSitterLogin.this, PetSitterRegistration.class);
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