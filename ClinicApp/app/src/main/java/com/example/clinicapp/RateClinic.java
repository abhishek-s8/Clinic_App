package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.clinicapp.DataBase.DataBase;

import java.math.BigDecimal;

public class RateClinic extends AppCompatActivity {
    private double rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_clinic);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                BigDecimal b = new BigDecimal(String.valueOf(rating));
                rate = b.doubleValue();
            }
        });
    }

    public void RateClinic(View view){
        DataBase dataBase = new DataBase(this);
        EditText nameOfClinic = (EditText) findViewById(R.id.editText9);
        if(nameOfClinic.getText().toString().equals("")){
            Toast.makeText(RateClinic.this, "Fail, you haven't input anything", Toast.LENGTH_LONG).show();
        }
        else if(!dataBase.clinicExist(nameOfClinic.getText().toString())){
            Toast.makeText(RateClinic.this, "Fail, this clinic doesn't exist", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(RateClinic.this, "You rate " + rate, Toast.LENGTH_LONG).show();
            dataBase.rate(nameOfClinic.getText().toString(), rate);
        }
        dataBase.close();
        finish();
    }
}
