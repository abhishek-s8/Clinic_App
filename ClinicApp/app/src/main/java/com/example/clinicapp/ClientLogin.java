package com.example.clinicapp;

import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ClientLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_login);

        Intent var = getIntent();
        String name = var.getStringExtra("Name");

        TextView textView = (TextView)findViewById(R.id.textView5);
        textView.setText("Welcome "+name+"! You are logged in as patient.");
    }
}
