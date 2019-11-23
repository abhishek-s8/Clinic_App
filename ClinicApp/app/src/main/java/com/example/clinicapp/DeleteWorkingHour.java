package com.example.clinicapp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import java.util.Date;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.CalendarView;
import android.widget.Toast;

public class DeleteWorkingHour extends AppCompatActivity{

    private String userName;
    private CalendarView start;
    private CalendarView end;

    private int yearStart=0;
    private int monthStart=0;
    private int dayStart=0;

    private int yearEnd=0;
    private int monthEnd=0;
    private int dayEnd=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_working_hour);

        Intent intent = getIntent();

        userName = intent.getStringExtra("userName");
        start = (CalendarView)findViewById(R.id.calendarView3);
        end = (CalendarView)findViewById(R.id.calendarView4);

        start.setMinDate(new Date().getTime());
        end.setMinDate(new Date().getTime());

        start.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view,int year,int month,int day) {

                month+=1;

                monthStart=month;
                dayStart=day;
                yearStart=year;
            }
        });

        end.setDate(new Date().getTime());

        if(yearStart==0){
            end.setMinDate(new Date().getTime());
        }
        else {

            long currentTime=intToLongTime(yearStart,monthStart,dayStart);
            end.setMinDate(currentTime);
        }

        end.setDate(new Date().getTime());

        end.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view,int year,int month,int day) {

                month += 1;

                dayEnd = day;
                yearEnd = year;
                monthEnd = month;
            }
        });
    }

    public void deleteWH(View view){

        if(yearStart==0||yearEnd==0){
            Toast.makeText(DeleteWorkingHour.this,"No date was chosen. Try again.",Toast.LENGTH_LONG).show();
            finish();
        }
        else {

            String workingTime=monthStart+"/"+dayStart+"/"+yearStart+" to "+monthEnd+"/"+dayEnd+"/"+yearEnd;
            myDBHelper dataBase = new myDBHelper(this);

            boolean success = dataBase.deleteWorkingHour(workingTime, userName);

            if(success){

                Toast.makeText(DeleteWorkingHour.this, "Complete", Toast.LENGTH_LONG).show();

                finish();
            }
            else{

                Toast.makeText(DeleteWorkingHour.this, "Working Hour does NOT exist",Toast.LENGTH_LONG).show();

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
