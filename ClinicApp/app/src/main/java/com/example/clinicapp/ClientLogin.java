package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ClientLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_login);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        TextView textView = (TextView)findViewById(R.id.textView5);
        TextView welcome = (TextView)findViewById(R.id.textView20);
        welcome.setText("Welcome "+ name + "!");
        textView.setText("You are logged in as patient");
    }
}
