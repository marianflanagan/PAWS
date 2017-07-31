package com.example.marianflanagan.paws.pet_sitter;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marianflanagan.paws.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by marianflanagan
 * Student ID - x16119444
 */

public class PetSitterRegistration extends AppCompatActivity {

    private EditText et_email, et_password;
    private Button btn_register;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstaneState) {
        super.onCreate(savedInstaneState);
        setContentView(R.layout.activity_pet_sitter_registration);

        mAuth = FirebaseAuth.getInstance();


        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }

    public void createAccount () {
        mAuth.createUserWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String TAG;
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(PetSitterRegistration.this, R.string.success_message,
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(PetSitterRegistration.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            Log.e("test", task.getException().getMessage());
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }


}

