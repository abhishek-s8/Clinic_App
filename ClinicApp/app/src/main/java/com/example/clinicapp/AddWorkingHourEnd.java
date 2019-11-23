package com.example.clinicapp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class AddWorkingHourEnd extends AppCompatActivity{

    private String userName;
    private CalendarView calendarView;

    private int yearStart;
    private int dayStart;
    private int monthStart;

    private int theYear=0;
    private int theMonth=0;
    private int theDay=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_working_hour_end);
        Intent intent=getIntent();

        monthStart=intent.getIntExtra("month",0);
        dayStart=intent.getIntExtra("day",0);
        yearStart=intent.getIntExtra("year",0);

        userName=intent.getStringExtra("userName");
        calendarView=(CalendarView)findViewById(R.id.calendarView2);

        if(yearStart==0)
        {
            calendarView.setDate(new Date().getTime());
            calendarView.setMinDate(new Date().getTime());
        }
        else {

            long currentTime=intToLongTime(yearStart,monthStart,dayStart);

            calendarView.setDate(currentTime);
            calendarView.setMinDate(currentTime);
        }

        calendarView.setDate(new Date().getTime());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view,int year,int month,int day) {

                month+=1;

                Toast.makeText(AddWorkingHourEnd.this, "Working hours end by "+month+"/"+day+"/"+year,Toast.LENGTH_LONG).show();

                theMonth = month;
                theDay = day;
                theYear = year;
            }
        });
    }

    public void SetEndDate(View view){
        if(yearStart==0||theYear==0){
            Toast.makeText(AddWorkingHourEnd.this,"Date was NOT chosen. Try again!",Toast.LENGTH_LONG).show();
            finish();
        }
        else {

            String workingTime=monthStart+"/"+dayStart+"/"+yearStart+" to "+theMonth+"/"+theDay+"/"+theYear;

            myDBHelper dataBase = new myDBHelper(this);
            boolean success = dataBase.addWorkingHour(workingTime,userName);

            if(success)
            {
                Toast.makeText(AddWorkingHourEnd.this, "Complete", Toast.LENGTH_LONG).show();

                finish();
            }
            else{
                Toast.makeText(AddWorkingHourEnd.this, "Working hour has been added", Toast.LENGTH_LONG).show();

                finish();
            }
        }
    }

    private long intToLongTime(int year,int month,int day){

        SimpleDateFormat formatter=new SimpleDateFormat("MM-dd-yyyy");
        String currentTime=month+"-"+day+"-"+year;

        Date date=null;

        try {
            date=formatter.parse(currentTime);
        } catch (ParseException e) {
            date=null;
        }
        if(date==null){
            return new Date().getTime();
        }
        else{
            return date.getTime();
        }
    }
}
