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
import java.util.ArrayList;
import java.util.Date;

public class DeleteWorkingHour extends AppCompatActivity {
    private CalendarView start;
    private CalendarView end;
    private int yearStart = 0;
    private int monthStart = 0;
    private int dayOfMonthStart = 0;
    private int yearEnd = 0;
    private int monthEnd = 0;
    private int dayOfMonthEnd = 0;
    private String userName;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_working_hour);
        DataBase dataBase = new DataBase(this);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        employee = dataBase.getEmployee(userName);
        start = (CalendarView)findViewById(R.id.calendarView3);
        end = (CalendarView)findViewById(R.id.calendarView4);
        start.setMinDate(new Date().getTime());
        end.setDate(new Date().getTime());
        end.setMinDate(new Date().getTime());
        start.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                yearStart = year;
                monthStart = month;
                dayOfMonthStart = dayOfMonth;
                if(yearStart == 0){
                    end.setMinDate(new Date().getTime());
                }
                else {
                    long currentTime = intToLongTime(yearStart, monthStart, dayOfMonthStart);
                    end.setMinDate(currentTime);
                }
            }
        });
        end.setDate(new Date().getTime());
        end.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                yearEnd = year;
                monthEnd = month;
                dayOfMonthEnd = dayOfMonth;
            }
        });
    }

    public void deleteWH(View view){
        DataBase dataBase = new DataBase(this);
        String monthSS = twoDigits(String.valueOf(monthStart));
        String daySS = twoDigits(String.valueOf(dayOfMonthStart));
        String monthES = twoDigits(String.valueOf(monthEnd));
        String dayES = twoDigits(String.valueOf(dayOfMonthEnd));
        String workingTimeStart = String.valueOf(yearStart) + monthSS + daySS;
        String workingTimeEnd = String.valueOf(yearEnd) + monthES + dayES;
        System.out.println(workingTimeStart);
        System.out.println((workingTimeEnd));
        if(yearStart == 0 || yearEnd == 0){
            Toast.makeText(DeleteWorkingHour.this, "You didn't choose a date, please try again.", Toast.LENGTH_LONG).show();
            finish();
        }
        else if(Integer.parseInt(workingTimeStart) > Integer.parseInt(workingTimeEnd)){
            Toast.makeText(DeleteWorkingHour.this, "You chose a invalid date.", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            boolean success = dataBase.deleteWorkingHour(workingTimeStart, workingTimeEnd, userName);
            if(success){
                Toast.makeText(DeleteWorkingHour.this, "Complete", Toast.LENGTH_LONG).show();
                ArrayList<String> d = new ArrayList<>();
                d.add(workingTimeStart);
                d.add(workingTimeEnd);
                dataBase.deleteWorkingHourClinic(employee.getNameOfClinic(), d);
                finish();
            }
            else{
                Toast.makeText(DeleteWorkingHour.this, "This working hour doesn't exist", Toast.LENGTH_LONG).show();
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
