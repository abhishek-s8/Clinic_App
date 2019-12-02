package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Employee;
import com.example.clinicapp.DataBase.DataBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWorkingHourEnd extends AppCompatActivity {
    private CalendarView calendarView;
    private int yearStart;
    private int year1 = 0;
    private int monthStart;
    private int month1 = 0;
    private int dayOfMonthStart;
    private int dayOfMonth1 = 0;
    private String userName;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_working_hour_end);
        DataBase dataBase = new DataBase(this);
        Intent intent = getIntent();
        yearStart = intent.getIntExtra("year", 0);
        monthStart = intent.getIntExtra("month", 0);
        dayOfMonthStart = intent.getIntExtra("dayOfMonth", 0);
        userName = intent.getStringExtra("userName");
        employee = dataBase.getEmployee(userName);

        calendarView = (CalendarView)findViewById(R.id.calendarView2);
        if(yearStart == 0){
            calendarView.setMinDate(new Date().getTime());
            calendarView.setDate(new Date().getTime());
        }
        else {
            long currentTime = intToLongTime(yearStart, monthStart, dayOfMonthStart);
            calendarView.setMinDate(currentTime);
            calendarView.setDate(currentTime);
        }
        calendarView.setDate(new Date().getTime());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                Toast.makeText(AddWorkingHourEnd.this, "Your working hour end by " + month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_LONG).show();
                year1 = year;
                month1 = month;
                dayOfMonth1 = dayOfMonth;
            }
        });
    }

    public void SetEndDate(View view){
        if(yearStart == 0 || year1 == 0){
            Toast.makeText(AddWorkingHourEnd.this, "You didn't choose a date, please try again.", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            String monthSS = twoDigits(String.valueOf(monthStart));
            String daySS = twoDigits(String.valueOf(dayOfMonthStart));
            String monthES = twoDigits(String.valueOf(month1));
            String dayES = twoDigits(String.valueOf(dayOfMonth1));

            String workingTimeStart = String.valueOf(yearStart) + monthSS + daySS;
            String workingTimeEnd = String.valueOf(year1) + monthES + dayES;
            DataBase dataBase = new DataBase(this);
            boolean success = dataBase.addWorkingHour(workingTimeStart, workingTimeEnd, userName);

            if(success){
                Toast.makeText(AddWorkingHourEnd.this, "Success!!!", Toast.LENGTH_LONG).show();
                dataBase.addWorkingHour2Clinic(userName, employee.getNameOfClinic());
                finish();
            }
            else{
                Toast.makeText(AddWorkingHourEnd.this, "This working hour had been added", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private long intToLongTime(int year, int month, int dayOfMonth){
        String currentTime = month + "-" + dayOfMonth + "-" + year;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = formatter.parse(currentTime);
        } catch (ParseException e) {
            date = null;
        }
        if(date == null){
            return new Date().getTime();
        }
        else{
            return date.getTime();
        }
    }

    private String twoDigits(String s){
        if(s.length() < 2){
            StringBuilder mss = new StringBuilder(s);
            mss.insert(0, "0");
            s = mss.toString();
        }
        return s;
    }
}
