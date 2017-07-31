package com.example.marianflanagan.paws.booking;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marianflanagan.paws.R;

public class BookingSummaryActivity extends AppCompatActivity {
    private static String TIME = "time";
    private static String DATE = "date";
    private static String DOG = "dog";
    private static String SITTER = "sitter";

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


        TextView v3 = (TextView) findViewById(R.id.bookingPet);
        v3.setText(getIntent().getStringExtra(DOG));
        TextView v = (TextView) findViewById(R.id.bookingDate);
        v.setText(getIntent().getStringExtra(DATE));
        TextView v1 = (TextView) findViewById(R.id.bookingTime);
        v1.setText(getIntent().getStringExtra(TIME));
        TextView v2 = (TextView) findViewById(R.id.bookingSitter);
        v2.setText(getIntent().getStringExtra(SITTER));

        Button b = (Button) findViewById(R.id.bookingButton);
        b.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     //TODO FIREBASE SAVE
                                 }
                             }
        );

    }
}
