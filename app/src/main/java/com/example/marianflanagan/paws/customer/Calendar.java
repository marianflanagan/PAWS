package com.example.marianflanagan.paws.customer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.example.marianflanagan.paws.R;

/*
 * Referencing Calendar Activity
 * @reference https://www.youtube.com/watch?v=hHjFIG0TtA0&index=27&list=PLgCYzUzKIBE8TUoCyjomGFqzTFcJ05OaC&t=5s
 *
 *
 */

public class Calendar extends AppCompatActivity {

    private static final String TAG = "Calendar";

    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy: " + date);

                Intent intent = new Intent(Calendar.this, BookingServices.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}
