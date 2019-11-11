package com.example.clinicapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddService extends AppCompatActivity {

    private RadioButton doctor;
    private RadioButton staff;
    private RadioButton nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_service);
    }

    public void finishClick(View view)
    {
        doctor = (RadioButton)findViewById(R.id.doctorBtn);
        staff = (RadioButton)findViewById(R.id.staffBtn);
        nurse = (RadioButton)findViewById(R.id.nurseBtn);

        Clinic clinic = new Clinic();

        EditText userInput = (EditText)findViewById(R.id.addText);

        if(userInput.getText().toString().equals(""))
        {
            Toast.makeText(AddService.this,"No Service Was Entered", Toast.LENGTH_LONG).show();
            finish();
        }
        else if(nurse.isChecked())
        {

            boolean success = clinic.addService(userInput.getText().toString()," by nurse");

            if(success)
            {
                Toast.makeText(AddService.this,"Complete",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(AddService.this,"This service has exist",Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else if(doctor.isChecked())
        {
            boolean success = clinic.addService(userInput.getText().toString(), " by doctor");

            if(success)
            {
                Toast.makeText(AddService.this,"Complete",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(AddService.this, "This service has exist", Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else if(staff.isChecked())
        {
            boolean success = clinic.addService(userInput.getText().toString(), " by staff");

            if(success)
            {
                Toast.makeText(AddService.this, "Complete",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(AddService.this,"This service has exist", Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(AddService.this,"A role was NOT chosen",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void cancelClick(View view)
    {
        finish();
    }
}
