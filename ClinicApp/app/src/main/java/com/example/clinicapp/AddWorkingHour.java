package com.example.clinicapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.CalendarView;
import android.widget.Toast;
import java.util.Date;
import android.os.Bundle;
import android.view.View;

public class AddWorkingHour extends AppCompatActivity {

    private String userName;
    private CalendarView calendarView;

    private int theDay=0;
    private int theYear=0;
    private int theMonth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_working_hour);

        calendarView=(CalendarView)findViewById(R.id.calendarView);
        calendarView.setMinDate(new Date().getTime());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                month+=1;
                Toast.makeText(AddWorkingHour.this, "Your working hour start from " + month + "/" + theDay + "/" + year, Toast.LENGTH_LONG).show();
                theYear=year;
                theMonth=month;
                theDay=day;
            }
        });

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
    }

    public void setWorkingHours(View view){
        Intent intent = new Intent(this, AddWorkingHourEnd.class);
        intent.putExtra("year", theYear);
        intent.putExtra("month", theMonth);
        intent.putExtra("dayOfMonth", theDay);
        intent.putExtra("userName", userName);
        startActivity(intent);
        finish();
    }
}
