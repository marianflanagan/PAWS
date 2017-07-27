package com.example.marianflanagan.paws.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marianflanagan.paws.home.ActivityLogin;
import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
 * Referencing Calendar Activity
 * @reference https://www.youtube.com/watch?v=xt9elnnUGRw&t=3s
 *
 *
 */


public class CustomerProfile extends AppCompatActivity {

    private static final String TAG = "CustomerProfile";

    private TextView textViewPets;
    private TextView tvclick;
    private ImageView profile_pic;
    private Button btn_details;
    private Button btn_bookings;
    private Button btnClogout;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");

        textViewPets = (TextView) findViewById(R.id.textViewPets);
        tvclick = (TextView) findViewById(R.id.tvclick);
        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        btn_details = (Button) findViewById(R.id.btn_details);
        btn_bookings = (Button) findViewById(R.id.btn_bookings);
        btnClogout = (Button) findViewById(R.id.btnClogout);

        myRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null) {
                    textViewPets.setText(String.format("%s's pets", user.getName()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        textViewPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPetsScreen();
            }

        });

        tvclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPetsScreen();
            }
        });

        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerProfile.this, EditCustomerDetails.class);
                startActivity(i);
            }
        });

        btn_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerProfile.this, BookingServices.class);
                startActivity(i);
            }
        });


        btnClogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMsg("Signing Out");
                Intent i = new Intent(CustomerProfile.this, ActivityLogin.class);
                startActivity(i);


            }
        });
    }

    private void goToPetsScreen() {
        Intent i = new Intent(CustomerProfile.this, MyPets.class);
        startActivity(i);
    }
    //Method for Logout Toast Message

    private void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}








