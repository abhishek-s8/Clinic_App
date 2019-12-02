package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmployeeORClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_orclient);
    }

    public void clientOnClick(View view){
        Intent intent = new Intent(this, SignupClient.class);
        startActivity(intent);
        finish();
    }

    public void employeeOnClick(View view){
        Intent intent = new Intent(this, SignupEmployee.class);
        startActivity(intent);
        finish();
    }
}
