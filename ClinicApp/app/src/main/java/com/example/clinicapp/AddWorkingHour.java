package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Date;

public class AddWorkingHour extends AppCompatActivity {
    private CalendarView calendarView;
    private int year1 = 0;
    private int month1 = 0;
    private int dayOfMonth1 = 0;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_working_hour);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setMinDate(new Date().getTime());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                Toast.makeText(AddWorkingHour.this, "Your working hour start from " + month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_LONG).show();
                year1 = year;
                month1 = month;
                dayOfMonth1 = dayOfMonth;
            }
        });
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
    }

    public void setWorkingHours(View view){
        Intent intent = new Intent(this, AddWorkingHourEnd.class);
        intent.putExtra("year", year1);
        intent.putExtra("month", month1);
        intent.putExtra("dayOfMonth", dayOfMonth1);
        intent.putExtra("userName", userName);
        startActivity(intent);
        finish();
    }
}
