package com.example.marianflanagan.paws.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.marianflanagan.paws.customer.CustomerLogin;
import com.example.marianflanagan.paws.pet_sitter.PetSitterLogin;
import com.example.marianflanagan.paws.R;


public class ActivityLogin extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void btnCustomer_Click(View view) {
        Intent i = new Intent(ActivityLogin.this, CustomerLogin.class);
        startActivity(i);
    }

    public void btnPetSitter_Click(View view) {
        Intent i = new Intent(ActivityLogin.this, PetSitterLogin.class);
        startActivity(i);

    }

}

