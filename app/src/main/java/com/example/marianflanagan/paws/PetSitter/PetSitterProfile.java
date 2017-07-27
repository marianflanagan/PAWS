package com.example.marianflanagan.paws.PetSitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marianflanagan.paws.Customer.BookingServices;
import com.example.marianflanagan.paws.Customer.CustomerLogin;
import com.example.marianflanagan.paws.Customer.CustomerProfile;
import com.example.marianflanagan.paws.Customer.EditCustomerDetails;
import com.example.marianflanagan.paws.Customer.MyPets;
import com.example.marianflanagan.paws.Home.ActivityLogin;
import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.model.PetSitter;
import com.example.marianflanagan.paws.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by marianflanagan on 27/07/2017.
 */

public class PetSitterProfile extends AppCompatActivity{

    private static final String TAG = "PetSitterProfile";


    private TextView tvName;
    private ImageView ps_pic;
    private Button btn_pet_sitter;
    private Button bookings;
    private Button btnPSLogout;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_sitter_profile);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("PetSitter");

        tvName = (TextView) findViewById(R.id.tvName);
        ps_pic = (ImageView) findViewById(R.id.ps_pic);
        btn_pet_sitter = (Button) findViewById(R.id.btn_pet_sitter);
        bookings = (Button) findViewById(R.id.bookings);
        btnPSLogout = (Button) findViewById(R.id.btnPSLogout);

        myRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null) {
                    tvName.setText(String.format(user.getName()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btn_pet_sitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PetSitterProfile.this, EditPetSitterDetails.class);
                startActivity(i);
            }
        });

        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PetSitterProfile.this, ViewBookings.class);
                startActivity(i);
            }
        });


        btnPSLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMsg("Signing Out");
                Intent i = new Intent(PetSitterProfile.this, ActivityLogin.class);
                startActivity(i);


            }
        });
    }


    //Method for Logout Toast Message

    private void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}




