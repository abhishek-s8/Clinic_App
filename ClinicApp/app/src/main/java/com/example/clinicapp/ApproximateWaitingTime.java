package com.example.clinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.example.clinicapp.DataBase.DataBase;

import java.util.Date;

public class ApproximateWaitingTime extends AppCompatActivity {
    private String userName;
    private int y;
    private int m;
    private int d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approximate_waiting_time);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView6);
        calendarView.setMinDate(new Date().getTime());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                y = year;
                m = month;
                d = dayOfMonth;
            }
        });
    }

    public void WaitingTime(View view){
        String message = "";
        String month = twoDigits(String.valueOf(m));
        String day = twoDigits(String.valueOf(d));
        String time = String.valueOf(y) + month + day;
        DataBase dataBase = new DataBase(this);
        if(dataBase.getAppointment(userName) != null){
            String doctorName = dataBase.getAppointment(userName).get(1);
            String clinicName = dataBase.getAppointment(userName).get(2);
            message = "Sorry, you have already made a appointment with " + doctorName + " of " + clinicName;
        }
        else if(!dataBase.checkIfAvailable(time)){
            message = "Sorry, there is no doctor working at that date";
        }
        else {
            int waitingTime = dataBase.ApproximateWaitingTime(time);
            if (waitingTime >= 480) {
                message = "Sorry, you can make an appointment at the date you choose";
            }
            else if(waitingTime == 0){
                message = "Congratulations, you will be the first patient at the date you choose";
            }
            else {
                message = "Your approximate waiting time is " + waitingTime + " minutes";
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your approximate waiting time");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){}
        });
        AlertDialog b = builder.create();
        b.show();
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
