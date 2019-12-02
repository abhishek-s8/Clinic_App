package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicapp.Clinics.Clients;
import com.example.clinicapp.DataBase.DataBase;

import java.util.Date;

public class MakeAppointment extends AppCompatActivity {
    private String userName;
    private Clients client;
    private int y;
    private int m;
    private int d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);

        DataBase dataBase = new DataBase(this);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        client = dataBase.getClient(userName);

        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView5);
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
        dataBase.close();
    }

    public void book(View view){
        EditText nameOfE = (EditText)findViewById(R.id.editText12);

        if(nameOfE.getText().toString().equals("")){
            Toast.makeText(MakeAppointment.this, "Your haven't choose a doctor", Toast.LENGTH_LONG).show();
            nameOfE.setText("");
            return;
        }
        else{
            for(int i = 0; i < nameOfE.getText().toString().length(); i++){
                if(!Character.isDigit(nameOfE.getText().toString().charAt(i)) && !Character.isLetter(nameOfE.getText().toString().charAt(i))){
                    Toast.makeText(MakeAppointment.this, "Invalid input", Toast.LENGTH_LONG).show();
                    nameOfE.setText("");
                    return;
                }
            }
        }

        DataBase dataBase = new DataBase(this);
        String month = twoDigits(String.valueOf(m));
        String day = twoDigits(String.valueOf(d));
        String time = String.valueOf(y) + month + day;
        dataBase.addAppointment(userName, time, nameOfE.getText().toString());
        dataBase.addAppointmentEmployee(nameOfE.getText().toString());
        Toast.makeText(MakeAppointment.this, "Your made an appointment at " + month + "/" + day + "/" + y, Toast.LENGTH_LONG).show();
        finish();

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
