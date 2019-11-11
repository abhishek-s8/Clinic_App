package com.example.clinicapp;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class EmployeeLogin extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_login);

        Intent temp = getIntent();
        String name = temp.getStringExtra("Name");

        TextView textView = (TextView)findViewById(R.id.textView5);
        textView.setText("Welcome" +name+"! You are logged in as employee.");
    }
}
