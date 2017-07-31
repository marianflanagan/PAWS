package com.example.marianflanagan.paws.booking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marianflanagan.paws.R;
import com.example.marianflanagan.paws.customer.PetDetails;
import com.example.marianflanagan.paws.model.Booking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by marianflanagan
 * Student ID - x16119444
 */

public class BookingSummaryActivity extends AppCompatActivity {
    private static String TIME = "time";
    private static String DATE = "date";
    private static String DOG = "dog";
    private static String SITTER = "sitter";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    public static void init(Context context, String date, String time, String dog, String sitterName) {

        Intent intent = new Intent(context, BookingSummaryActivity.class);
        intent.putExtra(DATE, date);
        intent.putExtra(TIME, time);
        intent.putExtra(DOG, dog);
        intent.putExtra(SITTER, sitterName);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);

        final String bookingPet = getIntent().getStringExtra(DOG);
        final String bookingDate = getIntent().getStringExtra(DATE);
        final String bookingTime = getIntent().getStringExtra(TIME);
        final String bookingSitter = getIntent().getStringExtra(SITTER);

        TextView bookingPetTextView = (TextView) findViewById(R.id.bookingPet);
        bookingPetTextView.setText(bookingPet);
        TextView bookingDateTextView = (TextView) findViewById(R.id.bookingDate);
        bookingDateTextView.setText(bookingDate);
        TextView bookingTimeTextView = (TextView) findViewById(R.id.bookingTime);
        bookingTimeTextView.setText(bookingTime);
        TextView bookingSitterTextView = (TextView) findViewById(R.id.bookingSitter);
        bookingSitterTextView.setText(bookingSitter);

        //declaring firebase objects
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Bookings");

        Button b = (Button) findViewById(R.id.bookingButton);
        b.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     FirebaseUser currentUser = mAuth.getCurrentUser();
                                     String userID = currentUser.getUid();
                                     Booking booking = new Booking(bookingPet, userID, bookingSitter, bookingDate, bookingTime);
                                     myRef.child(booking.getCustomerId()).setValue(booking);
                                     Toast.makeText(BookingSummaryActivity.this, "Success adding booking!", Toast.LENGTH_LONG).show();
                                 }
                             }
        );

    }
}
